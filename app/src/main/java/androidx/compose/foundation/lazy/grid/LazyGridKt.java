package androidx.compose.foundation.lazy.grid;

import android.os.Trace;
import androidx.compose.foundation.OverscrollEffect;
import androidx.compose.foundation.ScrollableAreaKt;
import androidx.compose.foundation.gestures.FlingBehavior;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.gestures.ScrollableDefaults;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.lazy.layout.CacheWindowLogic;
import androidx.compose.foundation.lazy.layout.LazyLayoutBeyondBoundsModifierLocalKt;
import androidx.compose.foundation.lazy.layout.LazyLayoutKt;
import androidx.compose.foundation.lazy.layout.LazyLayoutMeasurePolicy;
import androidx.compose.foundation.lazy.layout.LazyLayoutSemanticState;
import androidx.compose.foundation.lazy.layout.LazyLayoutSemanticsKt;
import androidx.compose.foundation.lazy.layout.StickyItemsPlacement;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocal;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.composer.linkbuffer.GroupFlagsKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.GraphicsContext;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.unit.Dp;
import androidx.profileinstaller.ProfileVerifier;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: LazyGrid.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0092\u0001\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0088\u0001\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000b2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0017\u0010\u0016\u001a\u0013\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u00010\u0017¢\u0006\u0002\b\u0019H\u0001¢\u0006\u0002\u0010\u001a\u001aq\u0010\u001b\u001a\u00020\u001c2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001e2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010%H\u0003¢\u0006\u0002\u0010&\u001a*\u0010'\u001a\u00020\u0001*\u00020(2\u0006\u0010)\u001a\u00020*2\f\u0010+\u001a\b\u0012\u0004\u0012\u00020-0,2\u0006\u0010.\u001a\u00020/H\u0002¨\u00060"}, d2 = {"LazyGrid", "", "modifier", "Landroidx/compose/ui/Modifier;", "state", "Landroidx/compose/foundation/lazy/grid/LazyGridState;", "slots", "Landroidx/compose/foundation/lazy/grid/LazyGridSlotsProvider;", "contentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "reverseLayout", "", "isVertical", "flingBehavior", "Landroidx/compose/foundation/gestures/FlingBehavior;", "userScrollEnabled", "overscrollEffect", "Landroidx/compose/foundation/OverscrollEffect;", "verticalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Vertical;", "horizontalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Horizontal;", "content", "Lkotlin/Function1;", "Landroidx/compose/foundation/lazy/grid/LazyGridScope;", "Lkotlin/ExtensionFunctionType;", "(Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/lazy/grid/LazyGridState;Landroidx/compose/foundation/lazy/grid/LazyGridSlotsProvider;Landroidx/compose/foundation/layout/PaddingValues;ZZLandroidx/compose/foundation/gestures/FlingBehavior;ZLandroidx/compose/foundation/OverscrollEffect;Landroidx/compose/foundation/layout/Arrangement$Vertical;Landroidx/compose/foundation/layout/Arrangement$Horizontal;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;III)V", "rememberLazyGridMeasurePolicy", "Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasurePolicy;", "itemProviderLambda", "Lkotlin/Function0;", "Landroidx/compose/foundation/lazy/grid/LazyGridItemProvider;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "graphicsContext", "Landroidx/compose/ui/graphics/GraphicsContext;", "stickyItemsScrollBehavior", "Landroidx/compose/foundation/lazy/layout/StickyItemsPlacement;", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/foundation/lazy/grid/LazyGridState;Landroidx/compose/foundation/lazy/grid/LazyGridSlotsProvider;Landroidx/compose/foundation/layout/PaddingValues;ZZLandroidx/compose/foundation/layout/Arrangement$Horizontal;Landroidx/compose/foundation/layout/Arrangement$Vertical;Lkotlinx/coroutines/CoroutineScope;Landroidx/compose/ui/graphics/GraphicsContext;Landroidx/compose/foundation/lazy/layout/StickyItemsPlacement;Landroidx/compose/runtime/Composer;II)Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasurePolicy;", "keepAroundItems", "Landroidx/compose/foundation/lazy/layout/CacheWindowLogic;", "orientation", "Landroidx/compose/foundation/gestures/Orientation;", "visibleItemsList", "", "Landroidx/compose/foundation/lazy/grid/LazyGridMeasuredItem;", "measuredLineProvider", "Landroidx/compose/foundation/lazy/grid/LazyGridMeasuredLineProvider;", "foundation"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class LazyGridKt {
    static final Unit LazyGrid$lambda$0(Modifier modifier, LazyGridState lazyGridState, LazyGridSlotsProvider lazyGridSlotsProvider, PaddingValues paddingValues, boolean z, boolean z2, FlingBehavior flingBehavior, boolean z3, OverscrollEffect overscrollEffect, Arrangement.Vertical vertical, Arrangement.Horizontal horizontal, Function1 function1, int i, int i2, int i3, Composer composer, int i4) {
        LazyGrid(modifier, lazyGridState, lazyGridSlotsProvider, paddingValues, z, z2, flingBehavior, z3, overscrollEffect, vertical, horizontal, function1, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
        return Unit.INSTANCE;
    }

    public static final void LazyGrid(Modifier modifier, LazyGridState state, final LazyGridSlotsProvider slots, PaddingValues contentPadding, boolean reverseLayout, final boolean isVertical, FlingBehavior flingBehavior, final boolean userScrollEnabled, final OverscrollEffect overscrollEffect, final Arrangement.Vertical verticalArrangement, final Arrangement.Horizontal horizontalArrangement, final Function1<? super LazyGridScope, Unit> function1, Composer $composer, final int $changed, final int $changed1, final int i) {
        Modifier modifier2;
        PaddingValues paddingValues;
        boolean z;
        LazyGridState lazyGridState;
        Composer $composer2;
        final FlingBehavior flingBehavior2;
        final PaddingValues contentPadding2;
        final boolean reverseLayout2;
        final Modifier modifier3;
        Modifier.Companion modifier4;
        FlingBehavior flingBehavior3;
        int $dirty;
        PaddingValues contentPadding3;
        boolean reverseLayout3;
        Modifier.Companion companionLazyLayoutBeyondBoundsModifier;
        Composer $composer3 = $composer.startRestartGroup(708740370);
        ComposerKt.sourceInformation($composer3, "C(LazyGrid)N(modifier,state,slots,contentPadding,reverseLayout,isVertical,flingBehavior,userScrollEnabled,overscrollEffect,verticalArrangement,horizontalArrangement,content)84@4015L50,86@4091L51,88@4169L24,89@4241L7,90@4310L7,93@4351L404,126@5421L302,121@5252L1117:LazyGrid.kt#7791vq");
        int $dirty2 = $changed;
        int $dirty1 = $changed1;
        int i2 = i & 1;
        if (i2 != 0) {
            $dirty2 |= 6;
            modifier2 = modifier;
        } else if (($changed & 6) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer3.changed(modifier2) ? 4 : 2;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 48) == 0) {
            $dirty2 |= $composer3.changed(state) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty2 |= ($changed & 512) == 0 ? $composer3.changed(slots) : $composer3.changedInstance(slots) ? 256 : 128;
        }
        int i3 = i & 8;
        if (i3 != 0) {
            $dirty2 |= 3072;
            paddingValues = contentPadding;
        } else if (($changed & 3072) == 0) {
            paddingValues = contentPadding;
            $dirty2 |= $composer3.changed(paddingValues) ? 2048 : 1024;
        } else {
            paddingValues = contentPadding;
        }
        int i4 = i & 16;
        if (i4 != 0) {
            $dirty2 |= 24576;
            z = reverseLayout;
        } else if (($changed & 24576) == 0) {
            z = reverseLayout;
            $dirty2 |= $composer3.changed(z) ? 16384 : 8192;
        } else {
            z = reverseLayout;
        }
        if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty2 |= $composer3.changed(isVertical) ? 131072 : 65536;
        }
        if (($changed & 1572864) == 0) {
            $dirty2 |= ((i & 64) == 0 && $composer3.changed(flingBehavior)) ? 1048576 : 524288;
        }
        if (($changed & 12582912) == 0) {
            $dirty2 |= $composer3.changed(userScrollEnabled) ? 8388608 : 4194304;
        }
        if (($changed & 100663296) == 0) {
            $dirty2 |= $composer3.changed(overscrollEffect) ? 67108864 : GroupFlagsKt.HasAuxSlotFlag;
        }
        if (($changed & 805306368) == 0) {
            $dirty2 |= $composer3.changed(verticalArrangement) ? GroupFlagsKt.HasMovableContentFlag : GroupFlagsKt.IsMovableContentFlag;
        }
        if (($changed1 & 6) == 0) {
            $dirty1 |= $composer3.changed(horizontalArrangement) ? 4 : 2;
        }
        if (($changed1 & 48) == 0) {
            $dirty1 |= $composer3.changedInstance(function1) ? 32 : 16;
        }
        if ($composer3.shouldExecute((($dirty2 & 306783379) == 306783378 && ($dirty1 & 19) == 18) ? false : true, $dirty2 & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "72@3483L15");
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                modifier4 = i2 != 0 ? Modifier.INSTANCE : modifier2;
                PaddingValues contentPadding4 = i3 != 0 ? PaddingKt.m1041PaddingValues0680j_4(Dp.m8150constructorimpl(0)) : paddingValues;
                boolean reverseLayout4 = i4 != 0 ? false : z;
                if ((i & 64) != 0) {
                    $dirty = $dirty2 & (-3670017);
                    contentPadding3 = contentPadding4;
                    reverseLayout3 = reverseLayout4;
                    flingBehavior3 = ScrollableDefaults.INSTANCE.flingBehavior($composer3, 6);
                } else {
                    flingBehavior3 = flingBehavior;
                    $dirty = $dirty2;
                    contentPadding3 = contentPadding4;
                    reverseLayout3 = reverseLayout4;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 64) != 0) {
                    $dirty2 &= -3670017;
                }
                flingBehavior3 = flingBehavior;
                $dirty = $dirty2;
                contentPadding3 = paddingValues;
                reverseLayout3 = z;
                modifier4 = modifier2;
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(708740370, $dirty, $dirty1, "androidx.compose.foundation.lazy.grid.LazyGrid (LazyGrid.kt:83)");
            }
            Function0<LazyGridItemProvider> function0RememberLazyGridItemProviderLambda = LazyGridItemProviderKt.rememberLazyGridItemProviderLambda(state, function1, $composer3, (($dirty >> 3) & 14) | ($dirty1 & 112));
            LazyLayoutSemanticState semanticState = LazySemanticsKt.rememberLazyGridSemanticState(state, reverseLayout3, $composer3, (($dirty >> 3) & 14) | (($dirty >> 9) & 112));
            ComposerKt.sourceInformationMarkerStart($composer3, 773894976, "CC(rememberCoroutineScope)N(getContext)616@28039L68:Effects.kt#9igjgp");
            Modifier modifier5 = modifier4;
            ComposerKt.sourceInformationMarkerStart($composer3, 683736516, "CC(remember):Effects.kt#9igjgp");
            Object value$iv$iv = $composer3.rememberedValue();
            if (value$iv$iv == Composer.INSTANCE.getEmpty()) {
                value$iv$iv = EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, $composer3);
                $composer3.updateRememberedValue(value$iv$iv);
            }
            CoroutineScope coroutineScope = (CoroutineScope) value$iv$iv;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ProvidableCompositionLocal<GraphicsContext> localGraphicsContext = CompositionLocalsKt.getLocalGraphicsContext();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = $composer3.consume(localGraphicsContext);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            GraphicsContext graphicsContext = (GraphicsContext) objConsume;
            CompositionLocal<Boolean> localScrollCaptureInProgress = CompositionLocalsKt.getLocalScrollCaptureInProgress();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume2 = $composer3.consume(localScrollCaptureInProgress);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            boolean stickyHeadersEnabled = !((Boolean) objConsume2).booleanValue();
            int $dirty3 = $dirty;
            LazyLayoutMeasurePolicy measurePolicy = rememberLazyGridMeasurePolicy(function0RememberLazyGridItemProviderLambda, state, slots, contentPadding3, reverseLayout3, isVertical, horizontalArrangement, verticalArrangement, coroutineScope, graphicsContext, stickyHeadersEnabled ? StickyItemsPlacement.INSTANCE.getStickToTopPlacement() : null, $composer3, ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | ($dirty & 57344) | ($dirty & 458752) | (($dirty1 << 18) & 3670016) | (($dirty >> 6) & 29360128), 0);
            lazyGridState = state;
            PaddingValues contentPadding5 = contentPadding3;
            boolean reverseLayout5 = reverseLayout3;
            Orientation orientation = isVertical ? Orientation.Vertical : Orientation.Horizontal;
            if (userScrollEnabled) {
                $composer3.startReplaceGroup(27281635);
                ComposerKt.sourceInformation($composer3, "112@4986L48");
                companionLazyLayoutBeyondBoundsModifier = LazyLayoutBeyondBoundsModifierLocalKt.lazyLayoutBeyondBoundsModifier(Modifier.INSTANCE, LazyGridBeyondBoundsModifierKt.rememberLazyGridBeyondBoundsState(lazyGridState, $composer3, ($dirty3 >> 3) & 14), lazyGridState.getBeyondBoundsInfo(), reverseLayout5, orientation);
                $composer3.endReplaceGroup();
            } else {
                $composer3.startReplaceGroup(27577840);
                $composer3.endReplaceGroup();
                companionLazyLayoutBeyondBoundsModifier = Modifier.INSTANCE;
            }
            Modifier beyondBoundsModifier = companionLazyLayoutBeyondBoundsModifier;
            $composer2 = $composer3;
            FlingBehavior flingBehavior4 = flingBehavior3;
            LazyLayoutKt.LazyLayout(function0RememberLazyGridItemProviderLambda, ScrollableAreaKt.scrollableArea(LazyLayoutSemanticsKt.lazyLayoutSemantics(modifier5.then(lazyGridState.getRemeasurementModifier()).then(lazyGridState.getAwaitLayoutModifier()), function0RememberLazyGridItemProviderLambda, semanticState, orientation, userScrollEnabled, reverseLayout5, $composer3, (($dirty3 >> 9) & 57344) | (($dirty3 << 3) & 458752)).then(beyondBoundsModifier).then(lazyGridState.getItemAnimator$foundation().getModifier()), lazyGridState, orientation, overscrollEffect, (128 & 8) != 0 ? true : userScrollEnabled, (128 & 16) != 0 ? false : reverseLayout5, (128 & 32) != 0 ? null : flingBehavior4, (128 & 64) != 0 ? null : lazyGridState.getInternalInteractionSource(), (128 & 128) != 0 ? null : null), lazyGridState.getPrefetchState(), measurePolicy, $composer2, 0, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            reverseLayout2 = reverseLayout5;
            flingBehavior2 = flingBehavior4;
            modifier3 = modifier5;
            contentPadding2 = contentPadding5;
        } else {
            lazyGridState = state;
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            flingBehavior2 = flingBehavior;
            contentPadding2 = paddingValues;
            reverseLayout2 = z;
            modifier3 = modifier2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final LazyGridState lazyGridState2 = lazyGridState;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.foundation.lazy.grid.LazyGridKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return LazyGridKt.LazyGrid$lambda$0(modifier3, lazyGridState2, slots, contentPadding2, reverseLayout2, isVertical, flingBehavior2, userScrollEnabled, overscrollEffect, verticalArrangement, horizontalArrangement, function1, $changed, $changed1, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x003e A[PHI: r8
  0x003e: PHI (r8v3 androidx.compose.foundation.lazy.grid.LazyGridState) = (r8v1 androidx.compose.foundation.lazy.grid.LazyGridState), (r8v4 androidx.compose.foundation.lazy.grid.LazyGridState) binds: [B:100:0x003c, B:96:0x0035] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:105:0x0049  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0058 A[PHI: r13
  0x0058: PHI (r13v3 androidx.compose.foundation.lazy.grid.LazyGridSlotsProvider) = 
  (r13v1 androidx.compose.foundation.lazy.grid.LazyGridSlotsProvider)
  (r13v4 androidx.compose.foundation.lazy.grid.LazyGridSlotsProvider)
 binds: [B:110:0x0056, B:106:0x004f] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:112:0x005a  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x006d  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x0073 A[PHI: r10
  0x0073: PHI (r10v3 androidx.compose.foundation.layout.PaddingValues) = (r10v1 androidx.compose.foundation.layout.PaddingValues), (r10v4 androidx.compose.foundation.layout.PaddingValues) binds: [B:120:0x0071, B:116:0x006a] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:122:0x0075  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x0081  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x0090 A[PHI: r11
  0x0090: PHI (r11v3 boolean) = (r11v1 boolean), (r11v4 boolean) binds: [B:130:0x008e, B:126:0x0087] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:132:0x0092  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x00a7  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x00ac A[PHI: r3
  0x00ac: PHI (r3v27 boolean) = (r3v24 boolean), (r3v28 boolean) binds: [B:140:0x00aa, B:136:0x00a4] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:142:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x00ba  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x00c3  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x00c9 A[PHI: r15
  0x00c9: PHI (r15v3 androidx.compose.foundation.layout.Arrangement$Horizontal) = 
  (r15v1 androidx.compose.foundation.layout.Arrangement$Horizontal)
  (r15v4 androidx.compose.foundation.layout.Arrangement$Horizontal)
 binds: [B:150:0x00c7, B:146:0x00c0] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:152:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x00d7  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x00e0  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x00e6 A[PHI: r14
  0x00e6: PHI (r14v3 androidx.compose.foundation.layout.Arrangement$Vertical) = 
  (r14v1 androidx.compose.foundation.layout.Arrangement$Vertical)
  (r14v4 androidx.compose.foundation.layout.Arrangement$Vertical)
 binds: [B:160:0x00e4, B:156:0x00dd] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:164:0x00fb  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x0105  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x012c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static final androidx.compose.foundation.lazy.layout.LazyLayoutMeasurePolicy rememberLazyGridMeasurePolicy(kotlin.jvm.functions.Function0<? extends androidx.compose.foundation.lazy.grid.LazyGridItemProvider> r22, androidx.compose.foundation.lazy.grid.LazyGridState r23, androidx.compose.foundation.lazy.grid.LazyGridSlotsProvider r24, androidx.compose.foundation.layout.PaddingValues r25, boolean r26, boolean r27, androidx.compose.foundation.layout.Arrangement.Horizontal r28, androidx.compose.foundation.layout.Arrangement.Vertical r29, kotlinx.coroutines.CoroutineScope r30, androidx.compose.ui.graphics.GraphicsContext r31, androidx.compose.foundation.lazy.layout.StickyItemsPlacement r32, androidx.compose.runtime.Composer r33, int r34, int r35) {
        /*
            Method dump skipped, instruction units count: 307
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.lazy.grid.LazyGridKt.rememberLazyGridMeasurePolicy(kotlin.jvm.functions.Function0, androidx.compose.foundation.lazy.grid.LazyGridState, androidx.compose.foundation.lazy.grid.LazyGridSlotsProvider, androidx.compose.foundation.layout.PaddingValues, boolean, boolean, androidx.compose.foundation.layout.Arrangement$Horizontal, androidx.compose.foundation.layout.Arrangement$Vertical, kotlinx.coroutines.CoroutineScope, androidx.compose.ui.graphics.GraphicsContext, androidx.compose.foundation.lazy.layout.StickyItemsPlacement, androidx.compose.runtime.Composer, int, int):androidx.compose.foundation.lazy.layout.LazyLayoutMeasurePolicy");
    }

    public static final void keepAroundItems(CacheWindowLogic $this$keepAroundItems, Orientation orientation, List<LazyGridMeasuredItem> list, LazyGridMeasuredLineProvider measuredLineProvider) {
        Trace.beginSection("compose:lazy:cache_window:keepAroundItems");
        try {
            if ($this$keepAroundItems.hasValidBounds() && !list.isEmpty()) {
                int firstVisibleItemIndex = LazyGridItemInfoKt.lineIndex((LazyGridItemInfo) CollectionsKt.first((List) list), orientation);
                int lastVisibleItemIndex = LazyGridItemInfoKt.lineIndex((LazyGridItemInfo) CollectionsKt.last((List) list), orientation);
                for (int line = $this$keepAroundItems.getPrefetchWindowStartLine(); line < firstVisibleItemIndex; line++) {
                    measuredLineProvider.keepAround(line);
                }
                int line2 = lastVisibleItemIndex + 1;
                int prefetchWindowEndLine = $this$keepAroundItems.getPrefetchWindowEndLine();
                if (line2 <= prefetchWindowEndLine) {
                    while (true) {
                        measuredLineProvider.keepAround(line2);
                        if (line2 == prefetchWindowEndLine) {
                            break;
                        } else {
                            line2++;
                        }
                    }
                }
            }
            Unit unit = Unit.INSTANCE;
        } finally {
            Trace.endSection();
        }
    }
}
