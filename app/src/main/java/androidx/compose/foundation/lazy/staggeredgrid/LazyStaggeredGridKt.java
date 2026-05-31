package androidx.compose.foundation.lazy.staggeredgrid;

import androidx.compose.foundation.OverscrollEffect;
import androidx.compose.foundation.ScrollableAreaKt;
import androidx.compose.foundation.gestures.FlingBehavior;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.gestures.ScrollableDefaults;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.lazy.layout.LazyLayoutBeyondBoundsModifierLocalKt;
import androidx.compose.foundation.lazy.layout.LazyLayoutKt;
import androidx.compose.foundation.lazy.layout.LazyLayoutMeasurePolicy;
import androidx.compose.foundation.lazy.layout.LazyLayoutSemanticState;
import androidx.compose.foundation.lazy.layout.LazyLayoutSemanticsKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
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
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: LazyStaggeredGrid.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000R\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0090\u0001\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\r2\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u00142\u0017\u0010\u0016\u001a\u0013\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u00010\u0017¢\u0006\u0002\b\u0019H\u0001¢\u0006\u0004\b\u001a\u0010\u001b¨\u0006\u001c"}, d2 = {"LazyStaggeredGrid", "", "state", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridState;", "orientation", "Landroidx/compose/foundation/gestures/Orientation;", "slots", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyGridStaggeredGridSlotsProvider;", "modifier", "Landroidx/compose/ui/Modifier;", "contentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "reverseLayout", "", "flingBehavior", "Landroidx/compose/foundation/gestures/FlingBehavior;", "userScrollEnabled", "overscrollEffect", "Landroidx/compose/foundation/OverscrollEffect;", "mainAxisSpacing", "Landroidx/compose/ui/unit/Dp;", "crossAxisSpacing", "content", "Lkotlin/Function1;", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridScope;", "Lkotlin/ExtensionFunctionType;", "LazyStaggeredGrid-w41Enmo", "(Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridState;Landroidx/compose/foundation/gestures/Orientation;Landroidx/compose/foundation/lazy/staggeredgrid/LazyGridStaggeredGridSlotsProvider;Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/layout/PaddingValues;ZLandroidx/compose/foundation/gestures/FlingBehavior;ZLandroidx/compose/foundation/OverscrollEffect;FFLkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;III)V", "foundation"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class LazyStaggeredGridKt {
    static final Unit LazyStaggeredGrid_w41Enmo$lambda$0(LazyStaggeredGridState lazyStaggeredGridState, Orientation orientation, LazyGridStaggeredGridSlotsProvider lazyGridStaggeredGridSlotsProvider, Modifier modifier, PaddingValues paddingValues, boolean z, FlingBehavior flingBehavior, boolean z2, OverscrollEffect overscrollEffect, float f, float f2, Function1 function1, int i, int i2, int i3, Composer composer, int i4) {
        m1284LazyStaggeredGridw41Enmo(lazyStaggeredGridState, orientation, lazyGridStaggeredGridSlotsProvider, modifier, paddingValues, z, flingBehavior, z2, overscrollEffect, f, f2, function1, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: LazyStaggeredGrid-w41Enmo, reason: not valid java name */
    public static final void m1284LazyStaggeredGridw41Enmo(final LazyStaggeredGridState state, final Orientation orientation, final LazyGridStaggeredGridSlotsProvider slots, Modifier modifier, PaddingValues contentPadding, boolean reverseLayout, FlingBehavior flingBehavior, boolean userScrollEnabled, final OverscrollEffect overscrollEffect, float mainAxisSpacing, float crossAxisSpacing, final Function1<? super LazyStaggeredGridScope, Unit> function1, Composer $composer, final int $changed, final int $changed1, final int i) {
        Modifier modifier2;
        PaddingValues paddingValues;
        boolean z;
        FlingBehavior flingBehavior2;
        OverscrollEffect overscrollEffect2;
        int i2;
        int i3;
        Composer $composer2;
        final float mainAxisSpacing2;
        final PaddingValues contentPadding2;
        final boolean reverseLayout2;
        final FlingBehavior flingBehavior3;
        final Modifier modifier3;
        final boolean userScrollEnabled2;
        final float crossAxisSpacing2;
        int $dirty;
        FlingBehavior flingBehavior4;
        float mainAxisSpacing3;
        int $dirty2;
        PaddingValues contentPadding3;
        boolean reverseLayout3;
        Modifier modifier4;
        float crossAxisSpacing3;
        FlingBehavior flingBehavior5;
        boolean userScrollEnabled3;
        Modifier.Companion companionLazyLayoutBeyondBoundsModifier;
        Composer $composer3 = $composer.startRestartGroup(-1904835166);
        ComposerKt.sourceInformation($composer3, "C(LazyStaggeredGrid)N(state,orientation,slots,modifier,contentPadding,reverseLayout,flingBehavior,userScrollEnabled,overscrollEffect,mainAxisSpacing:c#ui.unit.Dp,crossAxisSpacing:c#ui.unit.Dp,content)63@2868L55,64@2949L24,65@3021L7,67@3061L311,79@3397L60,98@4044L302,93@3875L1116:LazyStaggeredGrid.kt#fzvcnm");
        int $dirty3 = $changed;
        int $dirty1 = $changed1;
        if (($changed & 6) == 0) {
            $dirty3 |= $composer3.changed(state) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty3 |= $composer3.changed(orientation.ordinal()) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty3 |= ($changed & 512) == 0 ? $composer3.changed(slots) : $composer3.changedInstance(slots) ? 256 : 128;
        }
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty3 |= 3072;
            modifier2 = modifier;
        } else if (($changed & 3072) == 0) {
            modifier2 = modifier;
            $dirty3 |= $composer3.changed(modifier2) ? 2048 : 1024;
        } else {
            modifier2 = modifier;
        }
        int i5 = i & 16;
        if (i5 != 0) {
            $dirty3 |= 24576;
            paddingValues = contentPadding;
        } else if (($changed & 24576) == 0) {
            paddingValues = contentPadding;
            $dirty3 |= $composer3.changed(paddingValues) ? 16384 : 8192;
        } else {
            paddingValues = contentPadding;
        }
        int i6 = i & 32;
        if (i6 != 0) {
            $dirty3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            z = reverseLayout;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            z = reverseLayout;
            $dirty3 |= $composer3.changed(z) ? 131072 : 65536;
        } else {
            z = reverseLayout;
        }
        if (($changed & 1572864) == 0) {
            if ((i & 64) == 0) {
                flingBehavior2 = flingBehavior;
                int i7 = $composer3.changed(flingBehavior2) ? 1048576 : 524288;
                $dirty3 |= i7;
            } else {
                flingBehavior2 = flingBehavior;
            }
            $dirty3 |= i7;
        } else {
            flingBehavior2 = flingBehavior;
        }
        int i8 = i & 128;
        if (i8 != 0) {
            $dirty3 |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty3 |= $composer3.changed(userScrollEnabled) ? 8388608 : 4194304;
        }
        if (($changed & 100663296) == 0) {
            overscrollEffect2 = overscrollEffect;
            $dirty3 |= $composer3.changed(overscrollEffect2) ? 67108864 : GroupFlagsKt.HasAuxSlotFlag;
        } else {
            overscrollEffect2 = overscrollEffect;
        }
        int i9 = i & 512;
        if (i9 != 0) {
            $dirty3 |= 805306368;
            i2 = i9;
        } else if (($changed & 805306368) == 0) {
            i2 = i9;
            $dirty3 |= $composer3.changed(mainAxisSpacing) ? GroupFlagsKt.HasMovableContentFlag : GroupFlagsKt.IsMovableContentFlag;
        } else {
            i2 = i9;
        }
        int i10 = i & 1024;
        if (i10 != 0) {
            $dirty1 |= 6;
            i3 = i10;
        } else if (($changed1 & 6) == 0) {
            i3 = i10;
            $dirty1 |= $composer3.changed(crossAxisSpacing) ? 4 : 2;
        } else {
            i3 = i10;
        }
        if (($changed1 & 48) == 0) {
            $dirty1 |= $composer3.changedInstance(function1) ? 32 : 16;
        }
        int $dirty4 = $dirty3;
        if ($composer3.shouldExecute((($dirty3 & 306783379) == 306783378 && ($dirty1 & 19) == 18) ? false : true, $dirty4 & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "51@2361L15");
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                Modifier.Companion modifier5 = i4 != 0 ? Modifier.INSTANCE : modifier2;
                PaddingValues contentPadding4 = i5 != 0 ? PaddingKt.m1041PaddingValues0680j_4(Dp.m8150constructorimpl(0)) : paddingValues;
                boolean reverseLayout4 = i6 != 0 ? false : z;
                if ((i & 64) != 0) {
                    flingBehavior4 = ScrollableDefaults.INSTANCE.flingBehavior($composer3, 6);
                    $dirty = $dirty4 & (-3670017);
                } else {
                    $dirty = $dirty4;
                    flingBehavior4 = flingBehavior2;
                }
                boolean userScrollEnabled4 = i8 != 0 ? true : userScrollEnabled;
                mainAxisSpacing3 = i2 != 0 ? Dp.m8150constructorimpl(0) : mainAxisSpacing;
                if (i3 != 0) {
                    Modifier modifier6 = modifier5;
                    crossAxisSpacing3 = Dp.m8150constructorimpl(0);
                    boolean z2 = reverseLayout4;
                    $dirty2 = $dirty;
                    contentPadding3 = contentPadding4;
                    reverseLayout3 = z2;
                    modifier4 = modifier6;
                    flingBehavior5 = flingBehavior4;
                    userScrollEnabled3 = userScrollEnabled4;
                } else {
                    Modifier modifier7 = modifier5;
                    boolean z3 = reverseLayout4;
                    $dirty2 = $dirty;
                    contentPadding3 = contentPadding4;
                    reverseLayout3 = z3;
                    modifier4 = modifier7;
                    crossAxisSpacing3 = crossAxisSpacing;
                    flingBehavior5 = flingBehavior4;
                    userScrollEnabled3 = userScrollEnabled4;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 64) != 0) {
                    PaddingValues paddingValues2 = paddingValues;
                    $dirty2 = $dirty4 & (-3670017);
                    contentPadding3 = paddingValues2;
                    userScrollEnabled3 = userScrollEnabled;
                    mainAxisSpacing3 = mainAxisSpacing;
                    crossAxisSpacing3 = crossAxisSpacing;
                    reverseLayout3 = z;
                    flingBehavior5 = flingBehavior2;
                    modifier4 = modifier2;
                } else {
                    userScrollEnabled3 = userScrollEnabled;
                    mainAxisSpacing3 = mainAxisSpacing;
                    crossAxisSpacing3 = crossAxisSpacing;
                    contentPadding3 = paddingValues;
                    reverseLayout3 = z;
                    flingBehavior5 = flingBehavior2;
                    modifier4 = modifier2;
                    $dirty2 = $dirty4;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1904835166, $dirty2, $dirty1, "androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGrid (LazyStaggeredGrid.kt:62)");
            }
            Function0<LazyStaggeredGridItemProvider> function0RememberStaggeredGridItemProviderLambda = LazyStaggeredGridItemProviderKt.rememberStaggeredGridItemProviderLambda(state, function1, $composer3, ($dirty2 & 14) | ($dirty1 & 112));
            float crossAxisSpacing4 = crossAxisSpacing3;
            ComposerKt.sourceInformationMarkerStart($composer3, 773894976, "CC(rememberCoroutineScope)N(getContext)616@28039L68:Effects.kt#9igjgp");
            PaddingValues contentPadding5 = contentPadding3;
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
            boolean reverseLayout5 = reverseLayout3;
            int $dirty5 = $dirty2;
            float mainAxisSpacing4 = mainAxisSpacing3;
            LazyLayoutMeasurePolicy measurePolicy = LazyStaggeredGridMeasurePolicyKt.m1296rememberStaggeredGridMeasurePolicyqKj4JfE(state, function0RememberStaggeredGridItemProviderLambda, contentPadding5, reverseLayout5, orientation, mainAxisSpacing4, crossAxisSpacing4, coroutineScope, slots, graphicsContext, $composer3, ($dirty2 & 14) | (($dirty2 >> 6) & 896) | (($dirty2 >> 6) & 7168) | (($dirty2 << 9) & 57344) | (($dirty2 >> 12) & 458752) | (($dirty1 << 18) & 3670016) | (($dirty2 << 18) & 234881024));
            LazyLayoutSemanticState semanticState = LazyStaggeredGridSemanticsKt.rememberLazyStaggeredGridSemanticState(state, reverseLayout5, $composer3, ($dirty5 & 14) | (($dirty5 >> 12) & 112));
            if (userScrollEnabled3) {
                $composer3.startReplaceGroup(-1834596342);
                ComposerKt.sourceInformation($composer3, "84@3600L57");
                companionLazyLayoutBeyondBoundsModifier = LazyLayoutBeyondBoundsModifierLocalKt.lazyLayoutBeyondBoundsModifier(Modifier.INSTANCE, LazyStaggeredGridBeyondBoundsModifierKt.rememberLazyStaggeredGridBeyondBoundsState(state, $composer3, $dirty5 & 14), state.getBeyondBoundsInfo(), reverseLayout5, orientation);
                $composer3.endReplaceGroup();
            } else {
                $composer3.startReplaceGroup(-1834291488);
                $composer3.endReplaceGroup();
                companionLazyLayoutBeyondBoundsModifier = Modifier.INSTANCE;
            }
            Modifier beyondBoundsModifier = companionLazyLayoutBeyondBoundsModifier;
            boolean userScrollEnabled5 = userScrollEnabled3;
            $composer2 = $composer3;
            FlingBehavior flingBehavior6 = flingBehavior5;
            LazyLayoutKt.LazyLayout(function0RememberStaggeredGridItemProviderLambda, ScrollableAreaKt.scrollableArea(LazyLayoutSemanticsKt.lazyLayoutSemantics(modifier4.then(state.getRemeasurementModifier()).then(state.getAwaitLayoutModifier()), function0RememberStaggeredGridItemProviderLambda, semanticState, orientation, userScrollEnabled5, reverseLayout5, $composer3, ($dirty5 & 458752) | (($dirty5 << 6) & 7168) | (($dirty5 >> 9) & 57344)).then(beyondBoundsModifier).then(state.getItemAnimator$foundation().getModifier()), state, orientation, overscrollEffect2, (128 & 8) != 0 ? true : userScrollEnabled5, (128 & 16) != 0 ? false : reverseLayout5, (128 & 32) != 0 ? null : flingBehavior6, (128 & 64) != 0 ? null : state.getMutableInteractionSource(), (128 & 128) != 0 ? null : null), state.getPrefetchState(), measurePolicy, $composer2, 0, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            reverseLayout2 = reverseLayout5;
            userScrollEnabled2 = userScrollEnabled5;
            flingBehavior3 = flingBehavior6;
            modifier3 = modifier4;
            contentPadding2 = contentPadding5;
            mainAxisSpacing2 = mainAxisSpacing4;
            crossAxisSpacing2 = crossAxisSpacing4;
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            mainAxisSpacing2 = mainAxisSpacing;
            contentPadding2 = paddingValues;
            reverseLayout2 = z;
            flingBehavior3 = flingBehavior2;
            modifier3 = modifier2;
            userScrollEnabled2 = userScrollEnabled;
            crossAxisSpacing2 = crossAxisSpacing;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return LazyStaggeredGridKt.LazyStaggeredGrid_w41Enmo$lambda$0(state, orientation, slots, modifier3, contentPadding2, reverseLayout2, flingBehavior3, userScrollEnabled2, overscrollEffect, mainAxisSpacing2, crossAxisSpacing2, function1, $changed, $changed1, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }
}
