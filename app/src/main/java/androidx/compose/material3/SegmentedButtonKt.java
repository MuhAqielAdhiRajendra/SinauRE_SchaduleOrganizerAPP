package androidx.compose.material3;

import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.interaction.InteractionSource;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.IntrinsicKt;
import androidx.compose.foundation.layout.IntrinsicSize;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.selection.SelectableGroupKt;
import androidx.compose.material3.tokens.MotionSchemeKeyTokens;
import androidx.compose.material3.tokens.OutlinedSegmentedButtonTokens;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotIntStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.composer.linkbuffer.GroupFlagsKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.LayoutModifierKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.MultiContentMeasurePolicyKt;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Dp;
import androidx.profileinstaller.ProfileVerifier;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: SegmentedButton.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000x\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0003\u001a\u009b\u0001\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\u00042\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0013\b\u0002\u0010\u0014\u001a\r\u0012\u0004\u0012\u00020\u00010\u0015¢\u0006\u0002\b\u00162\u0011\u0010\u0017\u001a\r\u0012\u0004\u0012\u00020\u00010\u0015¢\u0006\u0002\b\u0016H\u0007¢\u0006\u0002\u0010\u0018\u001a\u0095\u0001\u0010\u0000\u001a\u00020\u0001*\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00042\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00010\u00152\u0006\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\u00042\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0013\b\u0002\u0010\u0014\u001a\r\u0012\u0004\u0012\u00020\u00010\u0015¢\u0006\u0002\b\u00162\u0011\u0010\u0017\u001a\r\u0012\u0004\u0012\u00020\u00010\u0015¢\u0006\u0002\b\u0016H\u0007¢\u0006\u0002\u0010\u001c\u001a\u0091\u0001\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\u00042\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0013\b\u0002\u0010\u0014\u001a\r\u0012\u0004\u0012\u00020\u00010\u0015¢\u0006\u0002\b\u00162\u0011\u0010\u0017\u001a\r\u0012\u0004\u0012\u00020\u00010\u0015¢\u0006\u0002\b\u0016H\u0007¢\u0006\u0002\u0010\u001d\u001a\u008b\u0001\u0010\u0000\u001a\u00020\u0001*\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00042\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00010\u00152\u0006\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\u00042\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0013\b\u0002\u0010\u0014\u001a\r\u0012\u0004\u0012\u00020\u00010\u0015¢\u0006\u0002\b\u00162\u0011\u0010\u0017\u001a\r\u0012\u0004\u0012\u00020\u00010\u0015¢\u0006\u0002\b\u0016H\u0007¢\u0006\u0002\u0010\u001e\u001aA\u0010\u001f\u001a\u00020\u00012\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010 \u001a\u00020!2\u001c\u0010\"\u001a\u0018\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00010\u0006¢\u0006\u0002\b\u0016¢\u0006\u0002\b#H\u0007¢\u0006\u0004\b$\u0010%\u001aA\u0010&\u001a\u00020\u00012\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010 \u001a\u00020!2\u001c\u0010\"\u001a\u0018\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00010\u0006¢\u0006\u0002\b\u0016¢\u0006\u0002\b#H\u0007¢\u0006\u0004\b'\u0010%\u001a;\u0010(\u001a\u00020\u00012\u0011\u0010\u0014\u001a\r\u0012\u0004\u0012\u00020\u00010\u0015¢\u0006\u0002\b\u00162\u0011\u0010\"\u001a\r\u0012\u0004\u0012\u00020\u00010\u0015¢\u0006\u0002\b\u00162\u0006\u0010\u0010\u001a\u00020\u0011H\u0003¢\u0006\u0002\u0010)\u001a\u0017\u0010*\u001a\b\u0012\u0004\u0012\u00020,0+*\u00020-H\u0003¢\u0006\u0002\u0010.\u001a\"\u0010/\u001a\u00020\n*\u00020\n2\u0006\u0010\u0003\u001a\u00020\u00042\f\u00100\u001a\b\u0012\u0004\u0012\u00020,0+H\u0002\"\u000e\u00101\u001a\u000202X\u0082T¢\u0006\u0002\n\u0000\"\u0010\u00103\u001a\u00020!X\u0082\u0004¢\u0006\u0004\n\u0002\u00104¨\u00065"}, d2 = {"SegmentedButton", "", "Landroidx/compose/material3/MultiChoiceSegmentedButtonRowScope;", "checked", "", "onCheckedChange", "Lkotlin/Function1;", "shape", "Landroidx/compose/ui/graphics/Shape;", "modifier", "Landroidx/compose/ui/Modifier;", "enabled", "colors", "Landroidx/compose/material3/SegmentedButtonColors;", "border", "Landroidx/compose/foundation/BorderStroke;", "contentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "icon", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "label", "(Landroidx/compose/material3/MultiChoiceSegmentedButtonRowScope;ZLkotlin/jvm/functions/Function1;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/ui/Modifier;ZLandroidx/compose/material3/SegmentedButtonColors;Landroidx/compose/foundation/BorderStroke;Landroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/foundation/interaction/MutableInteractionSource;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;III)V", "Landroidx/compose/material3/SingleChoiceSegmentedButtonRowScope;", "selected", "onClick", "(Landroidx/compose/material3/SingleChoiceSegmentedButtonRowScope;ZLkotlin/jvm/functions/Function0;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/ui/Modifier;ZLandroidx/compose/material3/SegmentedButtonColors;Landroidx/compose/foundation/BorderStroke;Landroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/foundation/interaction/MutableInteractionSource;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;III)V", "(Landroidx/compose/material3/MultiChoiceSegmentedButtonRowScope;ZLkotlin/jvm/functions/Function1;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/ui/Modifier;ZLandroidx/compose/material3/SegmentedButtonColors;Landroidx/compose/foundation/BorderStroke;Landroidx/compose/foundation/interaction/MutableInteractionSource;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;III)V", "(Landroidx/compose/material3/SingleChoiceSegmentedButtonRowScope;ZLkotlin/jvm/functions/Function0;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/ui/Modifier;ZLandroidx/compose/material3/SegmentedButtonColors;Landroidx/compose/foundation/BorderStroke;Landroidx/compose/foundation/interaction/MutableInteractionSource;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;III)V", "SingleChoiceSegmentedButtonRow", "space", "Landroidx/compose/ui/unit/Dp;", "content", "Lkotlin/ExtensionFunctionType;", "SingleChoiceSegmentedButtonRow-uFdPcIQ", "(Landroidx/compose/ui/Modifier;FLkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "MultiChoiceSegmentedButtonRow", "MultiChoiceSegmentedButtonRow-uFdPcIQ", "SegmentedButtonContent", "(Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/runtime/Composer;I)V", "interactionCountAsState", "Landroidx/compose/runtime/State;", "", "Landroidx/compose/foundation/interaction/InteractionSource;", "(Landroidx/compose/foundation/interaction/InteractionSource;Landroidx/compose/runtime/Composer;I)Landroidx/compose/runtime/State;", "interactionZIndex", "interactionCount", "CheckedZIndexFactor", "", "IconSpacing", "F", "material3"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class SegmentedButtonKt {
    private static final float CheckedZIndexFactor = 5.0f;
    private static final float IconSpacing = Dp.m8150constructorimpl(8);

    static final Unit MultiChoiceSegmentedButtonRow_uFdPcIQ$lambda$13(Modifier modifier, float f, Function3 function3, int i, int i2, Composer composer, int i3) {
        m2907MultiChoiceSegmentedButtonRowuFdPcIQ(modifier, f, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit SegmentedButton$lambda$1(MultiChoiceSegmentedButtonRowScope multiChoiceSegmentedButtonRowScope, boolean z, Function1 function1, Shape shape, Modifier modifier, boolean z2, SegmentedButtonColors segmentedButtonColors, BorderStroke borderStroke, PaddingValues paddingValues, MutableInteractionSource mutableInteractionSource, Function2 function2, Function2 function22, int i, int i2, int i3, Composer composer, int i4) {
        SegmentedButton(multiChoiceSegmentedButtonRowScope, z, (Function1<? super Boolean, Unit>) function1, shape, modifier, z2, segmentedButtonColors, borderStroke, paddingValues, mutableInteractionSource, (Function2<? super Composer, ? super Integer, Unit>) function2, (Function2<? super Composer, ? super Integer, Unit>) function22, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
        return Unit.INSTANCE;
    }

    static final Unit SegmentedButton$lambda$5(SingleChoiceSegmentedButtonRowScope singleChoiceSegmentedButtonRowScope, boolean z, Function0 function0, Shape shape, Modifier modifier, boolean z2, SegmentedButtonColors segmentedButtonColors, BorderStroke borderStroke, PaddingValues paddingValues, MutableInteractionSource mutableInteractionSource, Function2 function2, Function2 function22, int i, int i2, int i3, Composer composer, int i4) {
        SegmentedButton(singleChoiceSegmentedButtonRowScope, z, (Function0<Unit>) function0, shape, modifier, z2, segmentedButtonColors, borderStroke, paddingValues, mutableInteractionSource, (Function2<? super Composer, ? super Integer, Unit>) function2, (Function2<? super Composer, ? super Integer, Unit>) function22, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
        return Unit.INSTANCE;
    }

    static final Unit SegmentedButton$lambda$6(MultiChoiceSegmentedButtonRowScope multiChoiceSegmentedButtonRowScope, boolean z, Function1 function1, Shape shape, Modifier modifier, boolean z2, SegmentedButtonColors segmentedButtonColors, BorderStroke borderStroke, MutableInteractionSource mutableInteractionSource, Function2 function2, Function2 function22, int i, int i2, int i3, Composer composer, int i4) {
        SegmentedButton(multiChoiceSegmentedButtonRowScope, z, function1, shape, modifier, z2, segmentedButtonColors, borderStroke, mutableInteractionSource, function2, function22, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
        return Unit.INSTANCE;
    }

    static final Unit SegmentedButton$lambda$7(SingleChoiceSegmentedButtonRowScope singleChoiceSegmentedButtonRowScope, boolean z, Function0 function0, Shape shape, Modifier modifier, boolean z2, SegmentedButtonColors segmentedButtonColors, BorderStroke borderStroke, MutableInteractionSource mutableInteractionSource, Function2 function2, Function2 function22, int i, int i2, int i3, Composer composer, int i4) {
        SegmentedButton(singleChoiceSegmentedButtonRowScope, z, function0, shape, modifier, z2, segmentedButtonColors, borderStroke, mutableInteractionSource, function2, function22, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
        return Unit.INSTANCE;
    }

    static final Unit SegmentedButtonContent$lambda$15(Function2 function2, Function2 function22, PaddingValues paddingValues, int i, Composer composer, int i2) {
        SegmentedButtonContent(function2, function22, paddingValues, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit SingleChoiceSegmentedButtonRow_uFdPcIQ$lambda$10(Modifier modifier, float f, Function3 function3, int i, int i2, Composer composer, int i3) {
        m2908SingleChoiceSegmentedButtonRowuFdPcIQ(modifier, f, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: androidx.compose.material3.SegmentedButtonKt$SegmentedButton$1 */
    /* JADX INFO: compiled from: SegmentedButton.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    static final class AnonymousClass1 implements Function2<Composer, Integer, Unit> {
        final /* synthetic */ boolean $checked;

        AnonymousClass1(boolean z) {
            z = z;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C140@7105L13:SegmentedButton.kt#uh7d8r");
            if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1181873313, $changed, -1, "androidx.compose.material3.SegmentedButton.<anonymous> (SegmentedButton.kt:140)");
            }
            SegmentedButtonDefaults.INSTANCE.Icon(z, null, null, $composer, 3072, 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    public static final void SegmentedButton(final MultiChoiceSegmentedButtonRowScope $this$SegmentedButton, final boolean checked, final Function1<? super Boolean, Unit> function1, final Shape shape, Modifier modifier, boolean enabled, SegmentedButtonColors colors, BorderStroke border, PaddingValues contentPadding, MutableInteractionSource interactionSource, Function2<? super Composer, ? super Integer, Unit> function2, final Function2<? super Composer, ? super Integer, Unit> function22, Composer $composer, final int $changed, final int $changed1, final int i) {
        Shape shape2;
        Modifier modifier2;
        boolean z;
        SegmentedButtonColors segmentedButtonColors;
        BorderStroke border2;
        int i2;
        int i3;
        Composer $composer2;
        final PaddingValues contentPadding2;
        final Modifier modifier3;
        final boolean enabled2;
        final SegmentedButtonColors colors2;
        final MutableInteractionSource interactionSource2;
        final Function2<? super Composer, ? super Integer, Unit> function23;
        final BorderStroke border3;
        Modifier.Companion modifier4;
        boolean enabled3;
        SegmentedButtonColors colors3;
        BorderStroke border4;
        PaddingValues contentPadding3;
        MutableInteractionSource interactionSource3;
        MutableInteractionSource interactionSource4;
        SegmentedButtonColors colors4;
        Function2<? super Composer, ? super Integer, Unit> function2RememberComposableLambda;
        int $dirty;
        PaddingValues contentPadding4;
        boolean enabled4;
        MutableInteractionSource interactionSource5;
        Composer $composer3 = $composer.startRestartGroup(697872538);
        ComposerKt.sourceInformation($composer3, "C(SegmentedButton)N(checked,onCheckedChange,shape,modifier,enabled,colors,border,contentPadding,interactionSource,icon,label)147@7453L25,166@8056L101,149@7484L673:SegmentedButton.kt#uh7d8r");
        int $dirty2 = $changed;
        int $dirty1 = $changed1;
        if ((Integer.MIN_VALUE & i) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty2 |= $composer3.changed($this$SegmentedButton) ? 4 : 2;
        }
        if ((i & 1) != 0) {
            $dirty2 |= 48;
        } else if (($changed & 48) == 0) {
            $dirty2 |= $composer3.changed(checked) ? 32 : 16;
        }
        if ((i & 2) != 0) {
            $dirty2 |= 384;
        } else if (($changed & 384) == 0) {
            $dirty2 |= $composer3.changedInstance(function1) ? 256 : 128;
        }
        if ((i & 4) != 0) {
            $dirty2 |= 3072;
            shape2 = shape;
        } else if (($changed & 3072) == 0) {
            shape2 = shape;
            $dirty2 |= $composer3.changed(shape2) ? 2048 : 1024;
        } else {
            shape2 = shape;
        }
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty2 |= 24576;
            modifier2 = modifier;
        } else if (($changed & 24576) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer3.changed(modifier2) ? 16384 : 8192;
        } else {
            modifier2 = modifier;
        }
        int i5 = i & 16;
        if (i5 != 0) {
            $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            z = enabled;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            z = enabled;
            $dirty2 |= $composer3.changed(z) ? 131072 : 65536;
        } else {
            z = enabled;
        }
        if (($changed & 1572864) == 0) {
            if ((i & 32) == 0) {
                segmentedButtonColors = colors;
                int i6 = $composer3.changed(segmentedButtonColors) ? 1048576 : 524288;
                $dirty2 |= i6;
            } else {
                segmentedButtonColors = colors;
            }
            $dirty2 |= i6;
        } else {
            segmentedButtonColors = colors;
        }
        if (($changed & 12582912) == 0) {
            if ((i & 64) == 0) {
                border2 = border;
                int i7 = $composer3.changed(border2) ? 8388608 : 4194304;
                $dirty2 |= i7;
            } else {
                border2 = border;
            }
            $dirty2 |= i7;
        } else {
            border2 = border;
        }
        int i8 = i & 128;
        if (i8 != 0) {
            $dirty2 |= 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty2 |= $composer3.changed(contentPadding) ? 67108864 : GroupFlagsKt.HasAuxSlotFlag;
        }
        int i9 = i & 256;
        if (i9 != 0) {
            $dirty2 |= 805306368;
            i2 = i9;
        } else if (($changed & 805306368) == 0) {
            i2 = i9;
            $dirty2 |= $composer3.changed(interactionSource) ? GroupFlagsKt.HasMovableContentFlag : GroupFlagsKt.IsMovableContentFlag;
        } else {
            i2 = i9;
        }
        int i10 = i & 512;
        if (i10 != 0) {
            $dirty1 |= 6;
            i3 = i10;
        } else if (($changed1 & 6) == 0) {
            i3 = i10;
            $dirty1 |= $composer3.changedInstance(function2) ? 4 : 2;
        } else {
            i3 = i10;
        }
        if ((i & 1024) != 0) {
            $dirty1 |= 48;
        } else if (($changed1 & 48) == 0) {
            $dirty1 |= $composer3.changedInstance(function22) ? 32 : 16;
        }
        int $dirty12 = $dirty1;
        if ($composer3.shouldExecute(((306783379 & $dirty2) == 306783378 && ($dirty12 & 19) == 18) ? false : true, $dirty2 & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "135@6790L8,140@7079L41");
            if (($changed & 1) != 0 && !$composer3.getDefaultsInvalid()) {
                $composer3.skipToGroupEnd();
                if ((i & 32) != 0) {
                    $dirty2 &= -3670017;
                }
                if ((i & 64) != 0) {
                    int i11 = $dirty2 & (-29360129);
                    contentPadding4 = contentPadding;
                    interactionSource4 = interactionSource;
                    function2RememberComposableLambda = function2;
                    enabled4 = z;
                    $dirty = i11;
                    colors4 = segmentedButtonColors;
                } else {
                    interactionSource4 = interactionSource;
                    function2RememberComposableLambda = function2;
                    enabled4 = z;
                    colors4 = segmentedButtonColors;
                    $dirty = $dirty2;
                    contentPadding4 = contentPadding;
                }
            } else {
                if (i4 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if (i5 == 0) {
                    enabled3 = z;
                } else {
                    enabled3 = true;
                }
                if ((i & 32) == 0) {
                    colors3 = segmentedButtonColors;
                } else {
                    colors3 = SegmentedButtonDefaults.INSTANCE.colors($composer3, 6);
                    $dirty2 &= -3670017;
                }
                if ((i & 64) == 0) {
                    border4 = border2;
                } else {
                    border4 = SegmentedButtonDefaults.m2902borderStrokel07J4OM$default(SegmentedButtonDefaults.INSTANCE, colors3.m2886borderColorWaAFU9c$material3(enabled3, checked), 0.0f, 2, null);
                    $dirty2 &= -29360129;
                }
                if (i8 == 0) {
                    contentPadding3 = contentPadding;
                } else {
                    contentPadding3 = SegmentedButtonDefaults.INSTANCE.getContentPadding();
                }
                if (i2 == 0) {
                    interactionSource3 = interactionSource;
                } else {
                    interactionSource3 = null;
                }
                if (i3 == 0) {
                    Modifier modifier5 = modifier4;
                    interactionSource4 = interactionSource3;
                    colors4 = colors3;
                    border2 = border4;
                    function2RememberComposableLambda = function2;
                    $dirty = $dirty2;
                    contentPadding4 = contentPadding3;
                    enabled4 = enabled3;
                    modifier2 = modifier5;
                } else {
                    Modifier modifier6 = modifier4;
                    interactionSource4 = interactionSource3;
                    border2 = border4;
                    function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(1181873313, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SegmentedButtonKt.SegmentedButton.1
                        final /* synthetic */ boolean $checked;

                        AnonymousClass1(final boolean checked2) {
                            z = checked2;
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                            invoke(composer, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer $composer4, int $changed2) {
                            ComposerKt.sourceInformation($composer4, "C140@7105L13:SegmentedButton.kt#uh7d8r");
                            if (!$composer4.shouldExecute(($changed2 & 3) != 2, $changed2 & 1)) {
                                $composer4.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1181873313, $changed2, -1, "androidx.compose.material3.SegmentedButton.<anonymous> (SegmentedButton.kt:140)");
                            }
                            SegmentedButtonDefaults.INSTANCE.Icon(z, null, null, $composer4, 3072, 6);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, $composer3, 54);
                    colors4 = colors3;
                    $dirty = $dirty2;
                    contentPadding4 = contentPadding3;
                    enabled4 = enabled3;
                    modifier2 = modifier6;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(697872538, $dirty, $dirty12, "androidx.compose.material3.SegmentedButton (SegmentedButton.kt:142)");
            }
            if (interactionSource4 == null) {
                $composer3.startReplaceGroup(-1615180959);
                ComposerKt.sourceInformation($composer3, "144@7242L39");
                ComposerKt.sourceInformationMarkerStart($composer3, 640634049, "CC(remember):SegmentedButton.kt#9igjgp");
                Object it$iv = $composer3.rememberedValue();
                if (it$iv == Composer.INSTANCE.getEmpty()) {
                    Object value$iv = InteractionSourceKt.MutableInteractionSource();
                    $composer3.updateRememberedValue(value$iv);
                    it$iv = value$iv;
                }
                interactionSource5 = (MutableInteractionSource) it$iv;
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endReplaceGroup();
            } else {
                $composer3.startReplaceGroup(640633398);
                $composer3.endReplaceGroup();
                interactionSource5 = interactionSource4;
            }
            long containerColor = colors4.m2887containerColorWaAFU9c$material3(enabled4, checked2);
            Modifier modifier7 = modifier2;
            long contentColor = colors4.m2888contentColorWaAFU9c$material3(enabled4, checked2);
            PaddingValues contentPadding5 = contentPadding4;
            Function2<? super Composer, ? super Integer, Unit> function24 = function2RememberComposableLambda;
            SegmentedButtonColors colors5 = colors4;
            $composer2 = $composer3;
            SurfaceKt.m3016Surfaced85dljk(checked2, function1, SizeKt.m1099defaultMinSizeVpY3zN4(interactionZIndex(RowScope.weight$default($this$SegmentedButton, modifier7, 1.0f, false, 2, null), checked2, interactionCountAsState(interactionSource5, $composer3, 0)), ButtonDefaults.INSTANCE.m2217getMinWidthD9Ej5fM(), ButtonDefaults.INSTANCE.m2216getMinHeightD9Ej5fM()), enabled4, shape2, containerColor, contentColor, 0.0f, 0.0f, border2, interactionSource5, ComposableLambdaKt.rememberComposableLambda(1717860164, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SegmentedButtonKt.SegmentedButton.2
                final /* synthetic */ PaddingValues $contentPadding;
                final /* synthetic */ Function2<Composer, Integer, Unit> $icon;
                final /* synthetic */ Function2<Composer, Integer, Unit> $label;

                /* JADX WARN: Multi-variable type inference failed */
                AnonymousClass2(Function2<? super Composer, ? super Integer, Unit> function2RememberComposableLambda2, final Function2<? super Composer, ? super Integer, Unit> function222, PaddingValues contentPadding42) {
                    function2 = function2RememberComposableLambda2;
                    function2 = function222;
                    paddingValues = contentPadding42;
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer $composer4, int $changed2) {
                    ComposerKt.sourceInformation($composer4, "C167@8066L85:SegmentedButton.kt#uh7d8r");
                    if ($composer4.shouldExecute(($changed2 & 3) != 2, $changed2 & 1)) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1717860164, $changed2, -1, "androidx.compose.material3.SegmentedButton.<anonymous> (SegmentedButton.kt:167)");
                        }
                        SegmentedButtonKt.SegmentedButtonContent(function2, function2, paddingValues, $composer4, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer4.skipToGroupEnd();
                }
            }, $composer3, 54), $composer2, (($dirty >> 3) & 14) | (($dirty >> 3) & 112) | (($dirty >> 6) & 7168) | (57344 & ($dirty << 3)) | (($dirty << 6) & 1879048192), 48, 384);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            colors2 = colors5;
            enabled2 = enabled4;
            interactionSource2 = interactionSource4;
            modifier3 = modifier7;
            contentPadding2 = contentPadding5;
            function23 = function24;
            border3 = border2;
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            contentPadding2 = contentPadding;
            modifier3 = modifier2;
            enabled2 = z;
            colors2 = segmentedButtonColors;
            interactionSource2 = interactionSource;
            function23 = function2;
            border3 = border2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.SegmentedButtonKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return SegmentedButtonKt.SegmentedButton$lambda$1($this$SegmentedButton, checked2, function1, shape, modifier3, enabled2, colors2, border3, contentPadding2, interactionSource2, function23, function222, $changed, $changed1, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.SegmentedButtonKt$SegmentedButton$2 */
    /* JADX INFO: compiled from: SegmentedButton.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    static final class AnonymousClass2 implements Function2<Composer, Integer, Unit> {
        final /* synthetic */ PaddingValues $contentPadding;
        final /* synthetic */ Function2<Composer, Integer, Unit> $icon;
        final /* synthetic */ Function2<Composer, Integer, Unit> $label;

        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass2(Function2<? super Composer, ? super Integer, Unit> function2RememberComposableLambda2, final Function2 function222, PaddingValues contentPadding42) {
            function2 = function2RememberComposableLambda2;
            function2 = function222;
            paddingValues = contentPadding42;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer $composer4, int $changed2) {
            ComposerKt.sourceInformation($composer4, "C167@8066L85:SegmentedButton.kt#uh7d8r");
            if ($composer4.shouldExecute(($changed2 & 3) != 2, $changed2 & 1)) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1717860164, $changed2, -1, "androidx.compose.material3.SegmentedButton.<anonymous> (SegmentedButton.kt:167)");
                }
                SegmentedButtonKt.SegmentedButtonContent(function2, function2, paddingValues, $composer4, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                    return;
                }
                return;
            }
            $composer4.skipToGroupEnd();
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.SegmentedButtonKt$SegmentedButton$4 */
    /* JADX INFO: compiled from: SegmentedButton.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    static final class AnonymousClass4 implements Function2<Composer, Integer, Unit> {
        final /* synthetic */ boolean $selected;

        AnonymousClass4(boolean z) {
            z = z;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C220@10863L14:SegmentedButton.kt#uh7d8r");
            if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-643804033, $changed, -1, "androidx.compose.material3.SegmentedButton.<anonymous> (SegmentedButton.kt:220)");
            }
            SegmentedButtonDefaults.INSTANCE.Icon(z, null, null, $composer, 3072, 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    public static final void SegmentedButton(final SingleChoiceSegmentedButtonRowScope $this$SegmentedButton, final boolean selected, final Function0<Unit> function0, final Shape shape, Modifier modifier, boolean enabled, SegmentedButtonColors colors, BorderStroke border, PaddingValues contentPadding, MutableInteractionSource interactionSource, Function2<? super Composer, ? super Integer, Unit> function2, final Function2<? super Composer, ? super Integer, Unit> function22, Composer $composer, final int $changed, final int $changed1, final int i) {
        Shape shape2;
        Modifier modifier2;
        boolean z;
        SegmentedButtonColors segmentedButtonColors;
        BorderStroke border2;
        int i2;
        int i3;
        Composer $composer2;
        final PaddingValues contentPadding2;
        final Modifier modifier3;
        final boolean enabled2;
        final SegmentedButtonColors colors2;
        final MutableInteractionSource interactionSource2;
        final Function2<? super Composer, ? super Integer, Unit> function23;
        final BorderStroke border3;
        Modifier.Companion modifier4;
        boolean enabled3;
        SegmentedButtonColors colors3;
        BorderStroke border4;
        PaddingValues contentPadding3;
        MutableInteractionSource interactionSource3;
        MutableInteractionSource interactionSource4;
        SegmentedButtonColors colors4;
        Function2<? super Composer, ? super Integer, Unit> function2RememberComposableLambda;
        int $dirty;
        PaddingValues contentPadding4;
        boolean enabled4;
        MutableInteractionSource interactionSource5;
        Composer $composer3 = $composer.startRestartGroup(1532041126);
        ComposerKt.sourceInformation($composer3, "C(SegmentedButton)N(selected,onClick,shape,modifier,enabled,colors,border,contentPadding,interactionSource,icon,label)227@11214L25,238@11577L27,247@11859L67,229@11245L681:SegmentedButton.kt#uh7d8r");
        int $dirty2 = $changed;
        int $dirty1 = $changed1;
        if ((Integer.MIN_VALUE & i) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty2 |= $composer3.changed($this$SegmentedButton) ? 4 : 2;
        }
        if ((i & 1) != 0) {
            $dirty2 |= 48;
        } else if (($changed & 48) == 0) {
            $dirty2 |= $composer3.changed(selected) ? 32 : 16;
        }
        if ((i & 2) != 0) {
            $dirty2 |= 384;
        } else if (($changed & 384) == 0) {
            $dirty2 |= $composer3.changedInstance(function0) ? 256 : 128;
        }
        if ((i & 4) != 0) {
            $dirty2 |= 3072;
            shape2 = shape;
        } else if (($changed & 3072) == 0) {
            shape2 = shape;
            $dirty2 |= $composer3.changed(shape2) ? 2048 : 1024;
        } else {
            shape2 = shape;
        }
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty2 |= 24576;
            modifier2 = modifier;
        } else if (($changed & 24576) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer3.changed(modifier2) ? 16384 : 8192;
        } else {
            modifier2 = modifier;
        }
        int i5 = i & 16;
        if (i5 != 0) {
            $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            z = enabled;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            z = enabled;
            $dirty2 |= $composer3.changed(z) ? 131072 : 65536;
        } else {
            z = enabled;
        }
        if (($changed & 1572864) == 0) {
            if ((i & 32) == 0) {
                segmentedButtonColors = colors;
                int i6 = $composer3.changed(segmentedButtonColors) ? 1048576 : 524288;
                $dirty2 |= i6;
            } else {
                segmentedButtonColors = colors;
            }
            $dirty2 |= i6;
        } else {
            segmentedButtonColors = colors;
        }
        if (($changed & 12582912) == 0) {
            if ((i & 64) == 0) {
                border2 = border;
                int i7 = $composer3.changed(border2) ? 8388608 : 4194304;
                $dirty2 |= i7;
            } else {
                border2 = border;
            }
            $dirty2 |= i7;
        } else {
            border2 = border;
        }
        int i8 = i & 128;
        if (i8 != 0) {
            $dirty2 |= 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty2 |= $composer3.changed(contentPadding) ? 67108864 : GroupFlagsKt.HasAuxSlotFlag;
        }
        int i9 = i & 256;
        if (i9 != 0) {
            $dirty2 |= 805306368;
            i2 = i9;
        } else if (($changed & 805306368) == 0) {
            i2 = i9;
            $dirty2 |= $composer3.changed(interactionSource) ? GroupFlagsKt.HasMovableContentFlag : GroupFlagsKt.IsMovableContentFlag;
        } else {
            i2 = i9;
        }
        int i10 = i & 512;
        if (i10 != 0) {
            $dirty1 |= 6;
            i3 = i10;
        } else if (($changed1 & 6) == 0) {
            i3 = i10;
            $dirty1 |= $composer3.changedInstance(function2) ? 4 : 2;
        } else {
            i3 = i10;
        }
        if ((i & 1024) != 0) {
            $dirty1 |= 48;
        } else if (($changed1 & 48) == 0) {
            $dirty1 |= $composer3.changedInstance(function22) ? 32 : 16;
        }
        int $dirty12 = $dirty1;
        if ($composer3.shouldExecute(((306783379 & $dirty2) == 306783378 && ($dirty12 & 19) == 18) ? false : true, $dirty2 & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "215@10547L8,220@10837L42");
            if (($changed & 1) != 0 && !$composer3.getDefaultsInvalid()) {
                $composer3.skipToGroupEnd();
                if ((i & 32) != 0) {
                    $dirty2 &= -3670017;
                }
                if ((i & 64) != 0) {
                    int i11 = $dirty2 & (-29360129);
                    contentPadding4 = contentPadding;
                    interactionSource4 = interactionSource;
                    function2RememberComposableLambda = function2;
                    enabled4 = z;
                    $dirty = i11;
                    colors4 = segmentedButtonColors;
                } else {
                    interactionSource4 = interactionSource;
                    function2RememberComposableLambda = function2;
                    enabled4 = z;
                    colors4 = segmentedButtonColors;
                    $dirty = $dirty2;
                    contentPadding4 = contentPadding;
                }
            } else {
                if (i4 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if (i5 == 0) {
                    enabled3 = z;
                } else {
                    enabled3 = true;
                }
                if ((i & 32) == 0) {
                    colors3 = segmentedButtonColors;
                } else {
                    colors3 = SegmentedButtonDefaults.INSTANCE.colors($composer3, 6);
                    $dirty2 &= -3670017;
                }
                if ((i & 64) == 0) {
                    border4 = border2;
                } else {
                    border4 = SegmentedButtonDefaults.m2902borderStrokel07J4OM$default(SegmentedButtonDefaults.INSTANCE, colors3.m2886borderColorWaAFU9c$material3(enabled3, selected), 0.0f, 2, null);
                    $dirty2 &= -29360129;
                }
                if (i8 == 0) {
                    contentPadding3 = contentPadding;
                } else {
                    contentPadding3 = SegmentedButtonDefaults.INSTANCE.getContentPadding();
                }
                if (i2 == 0) {
                    interactionSource3 = interactionSource;
                } else {
                    interactionSource3 = null;
                }
                if (i3 == 0) {
                    Modifier modifier5 = modifier4;
                    interactionSource4 = interactionSource3;
                    colors4 = colors3;
                    border2 = border4;
                    function2RememberComposableLambda = function2;
                    $dirty = $dirty2;
                    contentPadding4 = contentPadding3;
                    enabled4 = enabled3;
                    modifier2 = modifier5;
                } else {
                    Modifier modifier6 = modifier4;
                    interactionSource4 = interactionSource3;
                    border2 = border4;
                    function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-643804033, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SegmentedButtonKt.SegmentedButton.4
                        final /* synthetic */ boolean $selected;

                        AnonymousClass4(final boolean selected2) {
                            z = selected2;
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                            invoke(composer, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer $composer4, int $changed2) {
                            ComposerKt.sourceInformation($composer4, "C220@10863L14:SegmentedButton.kt#uh7d8r");
                            if (!$composer4.shouldExecute(($changed2 & 3) != 2, $changed2 & 1)) {
                                $composer4.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-643804033, $changed2, -1, "androidx.compose.material3.SegmentedButton.<anonymous> (SegmentedButton.kt:220)");
                            }
                            SegmentedButtonDefaults.INSTANCE.Icon(z, null, null, $composer4, 3072, 6);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, $composer3, 54);
                    colors4 = colors3;
                    $dirty = $dirty2;
                    contentPadding4 = contentPadding3;
                    enabled4 = enabled3;
                    modifier2 = modifier6;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1532041126, $dirty, $dirty12, "androidx.compose.material3.SegmentedButton (SegmentedButton.kt:222)");
            }
            if (interactionSource4 == null) {
                $composer3.startReplaceGroup(-1579561419);
                ComposerKt.sourceInformation($composer3, "224@11001L39");
                ComposerKt.sourceInformationMarkerStart($composer3, -882237587, "CC(remember):SegmentedButton.kt#9igjgp");
                Object it$iv = $composer3.rememberedValue();
                if (it$iv == Composer.INSTANCE.getEmpty()) {
                    Object value$iv = InteractionSourceKt.MutableInteractionSource();
                    $composer3.updateRememberedValue(value$iv);
                    it$iv = value$iv;
                }
                interactionSource5 = (MutableInteractionSource) it$iv;
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endReplaceGroup();
            } else {
                $composer3.startReplaceGroup(-882238238);
                $composer3.endReplaceGroup();
                interactionSource5 = interactionSource4;
            }
            long containerColor = colors4.m2887containerColorWaAFU9c$material3(enabled4, selected2);
            Modifier modifier7 = modifier2;
            long contentColor = colors4.m2888contentColorWaAFU9c$material3(enabled4, selected2);
            SegmentedButtonColors colors5 = colors4;
            Modifier modifierM1099defaultMinSizeVpY3zN4 = SizeKt.m1099defaultMinSizeVpY3zN4(interactionZIndex(RowScope.weight$default($this$SegmentedButton, modifier7, 1.0f, false, 2, null), selected2, interactionCountAsState(interactionSource5, $composer3, 0)), ButtonDefaults.INSTANCE.m2217getMinWidthD9Ej5fM(), ButtonDefaults.INSTANCE.m2216getMinHeightD9Ej5fM());
            ComposerKt.sourceInformationMarkerStart($composer3, -882219167, "CC(remember):SegmentedButton.kt#9igjgp");
            Object it$iv2 = $composer3.rememberedValue();
            MutableInteractionSource interactionSource6 = interactionSource5;
            if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                Object value$iv2 = new Function1() { // from class: androidx.compose.material3.SegmentedButtonKt$$ExternalSyntheticLambda4
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return SegmentedButtonKt.SegmentedButton$lambda$4$lambda$3((SemanticsPropertyReceiver) obj);
                    }
                };
                $composer3.updateRememberedValue(value$iv2);
                it$iv2 = value$iv2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            boolean enabled5 = enabled4;
            PaddingValues contentPadding5 = contentPadding4;
            Function2<? super Composer, ? super Integer, Unit> function24 = function2RememberComposableLambda;
            $composer2 = $composer3;
            SurfaceKt.m3015Surfaced85dljk(selected2, function0, SemanticsModifierKt.semantics$default(modifierM1099defaultMinSizeVpY3zN4, false, (Function1) it$iv2, 1, null), enabled5, shape2, containerColor, contentColor, 0.0f, 0.0f, border2, interactionSource6, ComposableLambdaKt.rememberComposableLambda(-1208080836, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SegmentedButtonKt.SegmentedButton.6
                final /* synthetic */ PaddingValues $contentPadding;
                final /* synthetic */ Function2<Composer, Integer, Unit> $icon;
                final /* synthetic */ Function2<Composer, Integer, Unit> $label;

                /* JADX WARN: Multi-variable type inference failed */
                AnonymousClass6(Function2<? super Composer, ? super Integer, Unit> function2RememberComposableLambda2, final Function2<? super Composer, ? super Integer, Unit> function222, PaddingValues contentPadding42) {
                    function2 = function2RememberComposableLambda2;
                    function2 = function222;
                    paddingValues = contentPadding42;
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer $composer4, int $changed2) {
                    ComposerKt.sourceInformation($composer4, "C248@11869L51:SegmentedButton.kt#uh7d8r");
                    if ($composer4.shouldExecute(($changed2 & 3) != 2, $changed2 & 1)) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1208080836, $changed2, -1, "androidx.compose.material3.SegmentedButton.<anonymous> (SegmentedButton.kt:248)");
                        }
                        SegmentedButtonKt.SegmentedButtonContent(function2, function2, paddingValues, $composer4, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer4.skipToGroupEnd();
                }
            }, $composer3, 54), $composer2, (($dirty >> 3) & 14) | (($dirty >> 3) & 112) | (($dirty >> 6) & 7168) | (57344 & ($dirty << 3)) | (($dirty << 6) & 1879048192), 48, 384);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            enabled2 = enabled5;
            interactionSource2 = interactionSource4;
            modifier3 = modifier7;
            colors2 = colors5;
            function23 = function24;
            contentPadding2 = contentPadding5;
            border3 = border2;
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            contentPadding2 = contentPadding;
            modifier3 = modifier2;
            enabled2 = z;
            colors2 = segmentedButtonColors;
            interactionSource2 = interactionSource;
            function23 = function2;
            border3 = border2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.SegmentedButtonKt$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return SegmentedButtonKt.SegmentedButton$lambda$5($this$SegmentedButton, selected2, function0, shape, modifier3, enabled2, colors2, border3, contentPadding2, interactionSource2, function23, function222, $changed, $changed1, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    static final Unit SegmentedButton$lambda$4$lambda$3(SemanticsPropertyReceiver $this$semantics) {
        SemanticsPropertiesKt.m7362setRolekuIjeqM($this$semantics, Role.INSTANCE.m7348getRadioButtono7Vup1c());
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: androidx.compose.material3.SegmentedButtonKt$SegmentedButton$6 */
    /* JADX INFO: compiled from: SegmentedButton.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    static final class AnonymousClass6 implements Function2<Composer, Integer, Unit> {
        final /* synthetic */ PaddingValues $contentPadding;
        final /* synthetic */ Function2<Composer, Integer, Unit> $icon;
        final /* synthetic */ Function2<Composer, Integer, Unit> $label;

        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass6(Function2<? super Composer, ? super Integer, Unit> function2RememberComposableLambda2, final Function2 function222, PaddingValues contentPadding42) {
            function2 = function2RememberComposableLambda2;
            function2 = function222;
            paddingValues = contentPadding42;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer $composer4, int $changed2) {
            ComposerKt.sourceInformation($composer4, "C248@11869L51:SegmentedButton.kt#uh7d8r");
            if ($composer4.shouldExecute(($changed2 & 3) != 2, $changed2 & 1)) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1208080836, $changed2, -1, "androidx.compose.material3.SegmentedButton.<anonymous> (SegmentedButton.kt:248)");
                }
                SegmentedButtonKt.SegmentedButtonContent(function2, function2, paddingValues, $composer4, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                    return;
                }
                return;
            }
            $composer4.skipToGroupEnd();
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.SegmentedButtonKt$SegmentedButton$8 */
    /* JADX INFO: compiled from: SegmentedButton.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    static final class AnonymousClass8 implements Function2<Composer, Integer, Unit> {
        final /* synthetic */ boolean $checked;

        AnonymousClass8(boolean z) {
            z = z;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C264@12529L13:SegmentedButton.kt#uh7d8r");
            if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1867102712, $changed, -1, "androidx.compose.material3.SegmentedButton.<anonymous> (SegmentedButton.kt:264)");
            }
            SegmentedButtonDefaults.INSTANCE.Icon(z, null, null, $composer, 3072, 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "kept for binary compatibility")
    public static final /* synthetic */ void SegmentedButton(final MultiChoiceSegmentedButtonRowScope $this$SegmentedButton, final boolean checked, final Function1 onCheckedChange, final Shape shape, Modifier modifier, boolean enabled, SegmentedButtonColors colors, BorderStroke border, MutableInteractionSource interactionSource, Function2 icon, final Function2 label, Composer $composer, final int $changed, final int $changed1, final int i) {
        Modifier modifier2;
        boolean enabled2;
        final SegmentedButtonColors colors2;
        int i2;
        Composer $composer2;
        final BorderStroke border2;
        final MutableInteractionSource interactionSource2;
        final Function2 icon2;
        final Modifier modifier3;
        final boolean enabled3;
        BorderStroke border3;
        MutableInteractionSource interactionSource3;
        SegmentedButtonColors colors3;
        BorderStroke border4;
        int $dirty;
        Modifier modifier4;
        Composer $composer3 = $composer.startRestartGroup(2065856961);
        ComposerKt.sourceInformation($composer3, "C(SegmentedButton)N(checked,onCheckedChange,shape,modifier,enabled,colors,border,interactionSource,icon,label)267@12589L377:SegmentedButton.kt#uh7d8r");
        int $dirty2 = $changed;
        int $dirty1 = $changed1;
        if ((Integer.MIN_VALUE & i) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty2 |= $composer3.changed($this$SegmentedButton) ? 4 : 2;
        }
        if ((i & 1) != 0) {
            $dirty2 |= 48;
        } else if (($changed & 48) == 0) {
            $dirty2 |= $composer3.changed(checked) ? 32 : 16;
        }
        if ((i & 2) != 0) {
            $dirty2 |= 384;
        } else if (($changed & 384) == 0) {
            $dirty2 |= $composer3.changedInstance(onCheckedChange) ? 256 : 128;
        }
        if ((i & 4) != 0) {
            $dirty2 |= 3072;
        } else if (($changed & 3072) == 0) {
            $dirty2 |= $composer3.changed(shape) ? 2048 : 1024;
        }
        int i3 = i & 8;
        if (i3 != 0) {
            $dirty2 |= 24576;
            modifier2 = modifier;
        } else if (($changed & 24576) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer3.changed(modifier2) ? 16384 : 8192;
        } else {
            modifier2 = modifier;
        }
        int i4 = i & 16;
        if (i4 != 0) {
            $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            enabled2 = enabled;
        } else if ((196608 & $changed) == 0) {
            enabled2 = enabled;
            $dirty2 |= $composer3.changed(enabled2) ? 131072 : 65536;
        } else {
            enabled2 = enabled;
        }
        if (($changed & 1572864) == 0) {
            if ((i & 32) == 0) {
                colors2 = colors;
                int i5 = $composer3.changed(colors2) ? 1048576 : 524288;
                $dirty2 |= i5;
            } else {
                colors2 = colors;
            }
            $dirty2 |= i5;
        } else {
            colors2 = colors;
        }
        if (($changed & 12582912) == 0) {
            $dirty2 |= ((i & 64) == 0 && $composer3.changed(border)) ? 8388608 : 4194304;
        }
        int i6 = i & 128;
        if (i6 != 0) {
            $dirty2 |= 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty2 |= $composer3.changed(interactionSource) ? 67108864 : GroupFlagsKt.HasAuxSlotFlag;
        }
        int i7 = i & 256;
        if (i7 != 0) {
            $dirty2 |= 805306368;
            i2 = i7;
        } else if (($changed & 805306368) == 0) {
            i2 = i7;
            $dirty2 |= $composer3.changedInstance(icon) ? GroupFlagsKt.HasMovableContentFlag : GroupFlagsKt.IsMovableContentFlag;
        } else {
            i2 = i7;
        }
        if ((i & 512) != 0) {
            $dirty1 |= 6;
        } else if (($changed1 & 6) == 0) {
            $dirty1 |= $composer3.changedInstance(label) ? 4 : 2;
        }
        if ($composer3.shouldExecute((($dirty2 & 306783379) == 306783378 && ($dirty1 & 3) == 2) ? false : true, $dirty2 & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "260@12290L8,264@12503L41");
            if (($changed & 1) != 0 && !$composer3.getDefaultsInvalid()) {
                $composer3.skipToGroupEnd();
                if ((i & 32) != 0) {
                    $dirty2 &= -3670017;
                }
                if ((i & 64) != 0) {
                    $dirty = $dirty2 & (-29360129);
                    interactionSource2 = interactionSource;
                    icon2 = icon;
                    colors3 = colors2;
                    modifier4 = modifier2;
                    border4 = border;
                } else {
                    interactionSource2 = interactionSource;
                    icon2 = icon;
                    $dirty = $dirty2;
                    colors3 = colors2;
                    modifier4 = modifier2;
                    border4 = border;
                }
            } else {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i4 != 0) {
                    enabled2 = true;
                }
                if ((i & 32) != 0) {
                    $dirty2 &= -3670017;
                    colors2 = SegmentedButtonDefaults.INSTANCE.colors($composer3, 6);
                }
                if ((i & 64) == 0) {
                    border3 = border;
                } else {
                    border3 = SegmentedButtonDefaults.m2902borderStrokel07J4OM$default(SegmentedButtonDefaults.INSTANCE, colors2.m2886borderColorWaAFU9c$material3(enabled2, checked), 0.0f, 2, null);
                    $dirty2 &= -29360129;
                }
                if (i6 == 0) {
                    interactionSource3 = interactionSource;
                } else {
                    interactionSource3 = null;
                }
                if (i2 == 0) {
                    icon2 = icon;
                    interactionSource2 = interactionSource3;
                    colors3 = colors2;
                    border4 = border3;
                    $dirty = $dirty2;
                    modifier4 = modifier2;
                } else {
                    interactionSource2 = interactionSource3;
                    icon2 = ComposableLambdaKt.rememberComposableLambda(-1867102712, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SegmentedButtonKt.SegmentedButton.8
                        final /* synthetic */ boolean $checked;

                        AnonymousClass8(final boolean checked2) {
                            z = checked2;
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                            invoke(composer, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer $composer4, int $changed2) {
                            ComposerKt.sourceInformation($composer4, "C264@12529L13:SegmentedButton.kt#uh7d8r");
                            if (!$composer4.shouldExecute(($changed2 & 3) != 2, $changed2 & 1)) {
                                $composer4.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1867102712, $changed2, -1, "androidx.compose.material3.SegmentedButton.<anonymous> (SegmentedButton.kt:264)");
                            }
                            SegmentedButtonDefaults.INSTANCE.Icon(z, null, null, $composer4, 3072, 6);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, $composer3, 54);
                    colors3 = colors2;
                    border4 = border3;
                    $dirty = $dirty2;
                    modifier4 = modifier2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2065856961, $dirty, $dirty1, "androidx.compose.material3.SegmentedButton (SegmentedButton.kt:266)");
            }
            int i8 = (($dirty >> 27) & 14) | (($dirty1 << 3) & 112);
            boolean enabled4 = enabled2;
            SegmentedButton($this$SegmentedButton, checked2, (Function1<? super Boolean, Unit>) onCheckedChange, shape, modifier4, enabled4, colors3, border4, SegmentedButtonDefaults.INSTANCE.getContentPadding(), interactionSource2, (Function2<? super Composer, ? super Integer, Unit>) icon2, (Function2<? super Composer, ? super Integer, Unit>) label, $composer3, ($dirty & 14) | 100663296 | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (57344 & $dirty) | (458752 & $dirty) | (3670016 & $dirty) | (29360128 & $dirty) | (($dirty << 3) & 1879048192), i8, 0);
            $composer2 = $composer3;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            border2 = border4;
            colors2 = colors3;
            enabled3 = enabled4;
            modifier3 = modifier4;
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            border2 = border;
            interactionSource2 = interactionSource;
            icon2 = icon;
            modifier3 = modifier2;
            enabled3 = enabled2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.SegmentedButtonKt$$ExternalSyntheticLambda8
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return SegmentedButtonKt.SegmentedButton$lambda$6($this$SegmentedButton, checked2, onCheckedChange, shape, modifier3, enabled3, colors2, border2, interactionSource2, icon2, label, $changed, $changed1, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.SegmentedButtonKt$SegmentedButton$10 */
    /* JADX INFO: compiled from: SegmentedButton.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    static final class AnonymousClass10 implements Function2<Composer, Integer, Unit> {
        final /* synthetic */ boolean $selected;

        AnonymousClass10(boolean z) {
            z = z;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C294@13557L14:SegmentedButton.kt#uh7d8r");
            if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(61121126, $changed, -1, "androidx.compose.material3.SegmentedButton.<anonymous> (SegmentedButton.kt:294)");
            }
            SegmentedButtonDefaults.INSTANCE.Icon(z, null, null, $composer, 3072, 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "kept for binary compatibility")
    public static final /* synthetic */ void SegmentedButton(final SingleChoiceSegmentedButtonRowScope $this$SegmentedButton, final boolean selected, final Function0 onClick, final Shape shape, Modifier modifier, boolean enabled, SegmentedButtonColors colors, BorderStroke border, MutableInteractionSource interactionSource, Function2 icon, final Function2 label, Composer $composer, final int $changed, final int $changed1, final int i) {
        Modifier modifier2;
        boolean enabled2;
        final SegmentedButtonColors colors2;
        int i2;
        Composer $composer2;
        final BorderStroke border2;
        final MutableInteractionSource interactionSource2;
        final Function2 icon2;
        final Modifier modifier3;
        final boolean enabled3;
        BorderStroke border3;
        MutableInteractionSource interactionSource3;
        SegmentedButtonColors colors3;
        BorderStroke border4;
        int $dirty;
        Modifier modifier4;
        Composer $composer3 = $composer.startRestartGroup(1723786701);
        ComposerKt.sourceInformation($composer3, "C(SegmentedButton)N(selected,onClick,shape,modifier,enabled,colors,border,interactionSource,icon,label)297@13618L363:SegmentedButton.kt#uh7d8r");
        int $dirty2 = $changed;
        int $dirty1 = $changed1;
        if ((Integer.MIN_VALUE & i) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty2 |= $composer3.changed($this$SegmentedButton) ? 4 : 2;
        }
        if ((i & 1) != 0) {
            $dirty2 |= 48;
        } else if (($changed & 48) == 0) {
            $dirty2 |= $composer3.changed(selected) ? 32 : 16;
        }
        if ((i & 2) != 0) {
            $dirty2 |= 384;
        } else if (($changed & 384) == 0) {
            $dirty2 |= $composer3.changedInstance(onClick) ? 256 : 128;
        }
        if ((i & 4) != 0) {
            $dirty2 |= 3072;
        } else if (($changed & 3072) == 0) {
            $dirty2 |= $composer3.changed(shape) ? 2048 : 1024;
        }
        int i3 = i & 8;
        if (i3 != 0) {
            $dirty2 |= 24576;
            modifier2 = modifier;
        } else if (($changed & 24576) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer3.changed(modifier2) ? 16384 : 8192;
        } else {
            modifier2 = modifier;
        }
        int i4 = i & 16;
        if (i4 != 0) {
            $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            enabled2 = enabled;
        } else if ((196608 & $changed) == 0) {
            enabled2 = enabled;
            $dirty2 |= $composer3.changed(enabled2) ? 131072 : 65536;
        } else {
            enabled2 = enabled;
        }
        if (($changed & 1572864) == 0) {
            if ((i & 32) == 0) {
                colors2 = colors;
                int i5 = $composer3.changed(colors2) ? 1048576 : 524288;
                $dirty2 |= i5;
            } else {
                colors2 = colors;
            }
            $dirty2 |= i5;
        } else {
            colors2 = colors;
        }
        if (($changed & 12582912) == 0) {
            $dirty2 |= ((i & 64) == 0 && $composer3.changed(border)) ? 8388608 : 4194304;
        }
        int i6 = i & 128;
        if (i6 != 0) {
            $dirty2 |= 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty2 |= $composer3.changed(interactionSource) ? 67108864 : GroupFlagsKt.HasAuxSlotFlag;
        }
        int i7 = i & 256;
        if (i7 != 0) {
            $dirty2 |= 805306368;
            i2 = i7;
        } else if (($changed & 805306368) == 0) {
            i2 = i7;
            $dirty2 |= $composer3.changedInstance(icon) ? GroupFlagsKt.HasMovableContentFlag : GroupFlagsKt.IsMovableContentFlag;
        } else {
            i2 = i7;
        }
        if ((i & 512) != 0) {
            $dirty1 |= 6;
        } else if (($changed1 & 6) == 0) {
            $dirty1 |= $composer3.changedInstance(label) ? 4 : 2;
        }
        if ($composer3.shouldExecute((($dirty2 & 306783379) == 306783378 && ($dirty1 & 3) == 2) ? false : true, $dirty2 & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "290@13317L8,294@13531L42");
            if (($changed & 1) != 0 && !$composer3.getDefaultsInvalid()) {
                $composer3.skipToGroupEnd();
                if ((i & 32) != 0) {
                    $dirty2 &= -3670017;
                }
                if ((i & 64) != 0) {
                    $dirty = $dirty2 & (-29360129);
                    interactionSource2 = interactionSource;
                    icon2 = icon;
                    colors3 = colors2;
                    modifier4 = modifier2;
                    border4 = border;
                } else {
                    interactionSource2 = interactionSource;
                    icon2 = icon;
                    $dirty = $dirty2;
                    colors3 = colors2;
                    modifier4 = modifier2;
                    border4 = border;
                }
            } else {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i4 != 0) {
                    enabled2 = true;
                }
                if ((i & 32) != 0) {
                    $dirty2 &= -3670017;
                    colors2 = SegmentedButtonDefaults.INSTANCE.colors($composer3, 6);
                }
                if ((i & 64) == 0) {
                    border3 = border;
                } else {
                    border3 = SegmentedButtonDefaults.m2902borderStrokel07J4OM$default(SegmentedButtonDefaults.INSTANCE, colors2.m2886borderColorWaAFU9c$material3(enabled2, selected), 0.0f, 2, null);
                    $dirty2 &= -29360129;
                }
                if (i6 == 0) {
                    interactionSource3 = interactionSource;
                } else {
                    interactionSource3 = null;
                }
                if (i2 == 0) {
                    icon2 = icon;
                    interactionSource2 = interactionSource3;
                    colors3 = colors2;
                    border4 = border3;
                    $dirty = $dirty2;
                    modifier4 = modifier2;
                } else {
                    interactionSource2 = interactionSource3;
                    icon2 = ComposableLambdaKt.rememberComposableLambda(61121126, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SegmentedButtonKt.SegmentedButton.10
                        final /* synthetic */ boolean $selected;

                        AnonymousClass10(final boolean selected2) {
                            z = selected2;
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                            invoke(composer, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer $composer4, int $changed2) {
                            ComposerKt.sourceInformation($composer4, "C294@13557L14:SegmentedButton.kt#uh7d8r");
                            if (!$composer4.shouldExecute(($changed2 & 3) != 2, $changed2 & 1)) {
                                $composer4.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(61121126, $changed2, -1, "androidx.compose.material3.SegmentedButton.<anonymous> (SegmentedButton.kt:294)");
                            }
                            SegmentedButtonDefaults.INSTANCE.Icon(z, null, null, $composer4, 3072, 6);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, $composer3, 54);
                    colors3 = colors2;
                    border4 = border3;
                    $dirty = $dirty2;
                    modifier4 = modifier2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1723786701, $dirty, $dirty1, "androidx.compose.material3.SegmentedButton (SegmentedButton.kt:296)");
            }
            int i8 = (($dirty >> 27) & 14) | (($dirty1 << 3) & 112);
            boolean enabled4 = enabled2;
            SegmentedButton($this$SegmentedButton, selected2, (Function0<Unit>) onClick, shape, modifier4, enabled4, colors3, border4, SegmentedButtonDefaults.INSTANCE.getContentPadding(), interactionSource2, (Function2<? super Composer, ? super Integer, Unit>) icon2, (Function2<? super Composer, ? super Integer, Unit>) label, $composer3, ($dirty & 14) | 100663296 | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (57344 & $dirty) | (458752 & $dirty) | (3670016 & $dirty) | (29360128 & $dirty) | (($dirty << 3) & 1879048192), i8, 0);
            $composer2 = $composer3;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            border2 = border4;
            colors2 = colors3;
            enabled3 = enabled4;
            modifier3 = modifier4;
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            border2 = border;
            interactionSource2 = interactionSource;
            icon2 = icon;
            modifier3 = modifier2;
            enabled3 = enabled2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.SegmentedButtonKt$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return SegmentedButtonKt.SegmentedButton$lambda$7($this$SegmentedButton, selected2, onClick, shape, modifier3, enabled3, colors2, border2, interactionSource2, icon2, label, $changed, $changed1, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: SingleChoiceSegmentedButtonRow-uFdPcIQ */
    public static final void m2908SingleChoiceSegmentedButtonRowuFdPcIQ(Modifier modifier, float space, final Function3<? super SingleChoiceSegmentedButtonRowScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        float f;
        final Modifier modifier3;
        final float space2;
        Function0<ComposeUiNode> function0;
        Composer $composer2 = $composer.startRestartGroup(2041406825);
        ComposerKt.sourceInformation($composer2, "C(SingleChoiceSegmentedButtonRow)N(modifier,space:c#ui.unit.Dp,content)332@14963L448:SegmentedButton.kt#uh7d8r");
        int $dirty = $changed;
        int i2 = i & 1;
        if (i2 != 0) {
            $dirty |= 6;
            modifier2 = modifier;
        } else if (($changed & 6) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 4 : 2;
        } else {
            modifier2 = modifier;
        }
        int i3 = i & 2;
        if (i3 != 0) {
            $dirty |= 48;
            f = space;
        } else if (($changed & 48) == 0) {
            f = space;
            $dirty |= $composer2.changed(f) ? 32 : 16;
        } else {
            f = space;
        }
        if ((i & 4) != 0) {
            $dirty |= 384;
        } else if (($changed & 384) == 0) {
            $dirty |= $composer2.changedInstance(function3) ? 256 : 128;
        }
        int $dirty2 = $dirty;
        if ($composer2.shouldExecute(($dirty2 & 147) != 146, $dirty2 & 1)) {
            Modifier.Companion modifier4 = i2 != 0 ? Modifier.INSTANCE : modifier2;
            float space3 = i3 != 0 ? SegmentedButtonDefaults.INSTANCE.m2905getBorderWidthD9Ej5fM() : f;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2041406825, $dirty2, -1, "androidx.compose.material3.SingleChoiceSegmentedButtonRow (SegmentedButton.kt:331)");
            }
            Modifier modifier$iv = IntrinsicKt.width(SizeKt.m1100defaultMinSizeVpY3zN4$default(SelectableGroupKt.selectableGroup(modifier4), 0.0f, OutlinedSegmentedButtonTokens.INSTANCE.m4039getContainerHeightD9Ej5fM(), 1, null), IntrinsicSize.Min);
            Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.m740spacedBy0680j_4(Dp.m8150constructorimpl(-space3));
            Alignment.Vertical verticalAlignment$iv = Alignment.INSTANCE.getCenterVertically();
            ComposerKt.sourceInformationMarkerStart($composer2, 693286680, "CC(Row)P(2,1,3)99@5124L58,100@5187L130:Row.kt#2w3rfo");
            MeasurePolicy measurePolicy$iv = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer2, ((384 >> 3) & 14) | ((384 >> 3) & 112));
            int $changed$iv$iv = (384 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
            CompositionLocalMap localMap$iv$iv = $composer2.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer2, modifier$iv);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            Modifier modifier5 = modifier4;
            int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
            float space4 = space3;
            ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
            if (!($composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer2.startReusableNode();
            if ($composer2.getInserting()) {
                function0 = constructor;
                $composer2.createNode(function0);
            } else {
                function0 = constructor;
                $composer2.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4433constructorimpl($composer2);
            Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
            }
            Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
            int i4 = ($changed$iv$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, -407735110, "C101@5232L9:Row.kt#2w3rfo");
            int i5 = ((384 >> 6) & 112) | 6;
            RowScope $this$SingleChoiceSegmentedButtonRow_uFdPcIQ_u24lambda_u249 = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer2, 1183792256, "C341@15323L58,342@15396L9:SegmentedButton.kt#uh7d8r");
            ComposerKt.sourceInformationMarkerStart($composer2, 176734527, "CC(remember):SegmentedButton.kt#9igjgp");
            Object value$iv = $composer2.rememberedValue();
            if (value$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = new SingleChoiceSegmentedButtonScopeWrapper($this$SingleChoiceSegmentedButtonRow_uFdPcIQ_u24lambda_u249);
                $composer2.updateRememberedValue(value$iv);
            }
            SingleChoiceSegmentedButtonScopeWrapper scope = (SingleChoiceSegmentedButtonScopeWrapper) value$iv;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            function3.invoke(scope, $composer2, Integer.valueOf((($dirty2 >> 3) & 112) | 6));
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier5;
            space2 = space4;
        } else {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            space2 = f;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.SegmentedButtonKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return SegmentedButtonKt.SingleChoiceSegmentedButtonRow_uFdPcIQ$lambda$10(modifier3, space2, function3, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: MultiChoiceSegmentedButtonRow-uFdPcIQ */
    public static final void m2907MultiChoiceSegmentedButtonRowuFdPcIQ(Modifier modifier, float space, final Function3<? super MultiChoiceSegmentedButtonRowScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        float f;
        final Modifier modifier3;
        final float space2;
        Function0<ComposeUiNode> function0;
        Composer $composer2 = $composer.startRestartGroup(1844783038);
        ComposerKt.sourceInformation($composer2, "C(MultiChoiceSegmentedButtonRow)N(modifier,space:c#ui.unit.Dp,content)368@16414L412:SegmentedButton.kt#uh7d8r");
        int $dirty = $changed;
        int i2 = i & 1;
        if (i2 != 0) {
            $dirty |= 6;
            modifier2 = modifier;
        } else if (($changed & 6) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 4 : 2;
        } else {
            modifier2 = modifier;
        }
        int i3 = i & 2;
        if (i3 != 0) {
            $dirty |= 48;
            f = space;
        } else if (($changed & 48) == 0) {
            f = space;
            $dirty |= $composer2.changed(f) ? 32 : 16;
        } else {
            f = space;
        }
        if ((i & 4) != 0) {
            $dirty |= 384;
        } else if (($changed & 384) == 0) {
            $dirty |= $composer2.changedInstance(function3) ? 256 : 128;
        }
        int $dirty2 = $dirty;
        if ($composer2.shouldExecute(($dirty2 & 147) != 146, $dirty2 & 1)) {
            Modifier.Companion modifier4 = i2 != 0 ? Modifier.INSTANCE : modifier2;
            float space3 = i3 != 0 ? SegmentedButtonDefaults.INSTANCE.m2905getBorderWidthD9Ej5fM() : f;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1844783038, $dirty2, -1, "androidx.compose.material3.MultiChoiceSegmentedButtonRow (SegmentedButton.kt:367)");
            }
            Modifier modifier$iv = IntrinsicKt.width(SizeKt.m1100defaultMinSizeVpY3zN4$default(modifier4, 0.0f, OutlinedSegmentedButtonTokens.INSTANCE.m4039getContainerHeightD9Ej5fM(), 1, null), IntrinsicSize.Min);
            Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.m740spacedBy0680j_4(Dp.m8150constructorimpl(-space3));
            Alignment.Vertical verticalAlignment$iv = Alignment.INSTANCE.getCenterVertically();
            ComposerKt.sourceInformationMarkerStart($composer2, 693286680, "CC(Row)P(2,1,3)99@5124L58,100@5187L130:Row.kt#2w3rfo");
            MeasurePolicy measurePolicy$iv = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer2, ((384 >> 3) & 14) | ((384 >> 3) & 112));
            int $changed$iv$iv = (384 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
            CompositionLocalMap localMap$iv$iv = $composer2.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer2, modifier$iv);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            Modifier modifier5 = modifier4;
            int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
            float space4 = space3;
            ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
            if (!($composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer2.startReusableNode();
            if ($composer2.getInserting()) {
                function0 = constructor;
                $composer2.createNode(function0);
            } else {
                function0 = constructor;
                $composer2.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4433constructorimpl($composer2);
            Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
            }
            Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
            int i4 = ($changed$iv$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, -407735110, "C101@5232L9:Row.kt#2w3rfo");
            int i5 = ((384 >> 6) & 112) | 6;
            RowScope $this$MultiChoiceSegmentedButtonRow_uFdPcIQ_u24lambda_u2412 = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer2, 714807460, "C376@16739L57,377@16811L9:SegmentedButton.kt#uh7d8r");
            ComposerKt.sourceInformationMarkerStart($composer2, 2101268635, "CC(remember):SegmentedButton.kt#9igjgp");
            Object value$iv = $composer2.rememberedValue();
            if (value$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = new MultiChoiceSegmentedButtonScopeWrapper($this$MultiChoiceSegmentedButtonRow_uFdPcIQ_u24lambda_u2412);
                $composer2.updateRememberedValue(value$iv);
            }
            MultiChoiceSegmentedButtonScopeWrapper scope = (MultiChoiceSegmentedButtonScopeWrapper) value$iv;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            function3.invoke(scope, $composer2, Integer.valueOf((($dirty2 >> 3) & 112) | 6));
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier5;
            space2 = space4;
        } else {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            space2 = f;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.SegmentedButtonKt$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return SegmentedButtonKt.MultiChoiceSegmentedButtonRow_uFdPcIQ$lambda$13(modifier3, space2, function3, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final void SegmentedButtonContent(final Function2<? super Composer, ? super Integer, Unit> function2, final Function2<? super Composer, ? super Integer, Unit> function22, final PaddingValues contentPadding, Composer $composer, final int $changed) {
        Composer $composer2;
        Function0<ComposeUiNode> function0;
        Composer $composer3 = $composer.startRestartGroup(-1069265073);
        ComposerKt.sourceInformation($composer3, "C(SegmentedButtonContent)N(icon,content,contentPadding)387@16992L743:SegmentedButton.kt#uh7d8r");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changedInstance(function2) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer3.changedInstance(function22) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer3.changed(contentPadding) ? 256 : 128;
        }
        if (!$composer3.shouldExecute(($dirty & 147) != 146, $dirty & 1)) {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1069265073, $dirty, -1, "androidx.compose.material3.SegmentedButtonContent (SegmentedButton.kt:386)");
            }
            Alignment center = Alignment.INSTANCE.getCenter();
            Modifier modifier$iv$iv = PaddingKt.padding(Modifier.INSTANCE, contentPadding);
            ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
            MeasurePolicy measurePolicy$iv = BoxKt.maybeCachedBoxMeasurePolicy(center, false);
            int $changed$iv$iv = (48 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
            CompositionLocalMap localMap$iv$iv = $composer3.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer3, modifier$iv$iv);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                function0 = constructor;
                $composer3.createNode(function0);
            } else {
                function0 = constructor;
                $composer3.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4433constructorimpl($composer3);
            $composer2 = $composer3;
            Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
            }
            Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
            int i = ($changed$iv$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            int i2 = ((48 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, 314731321, "C388@17149L5,390@17293L12,391@17343L386,391@17314L415:SegmentedButton.kt#uh7d8r");
            TextStyle typography = TypographyKt.getValue(OutlinedSegmentedButtonTokens.INSTANCE.getLabelTextFont(), $composer2, 6);
            final FiniteAnimationSpec animationSpec = MotionSchemeKt.value(MotionSchemeKeyTokens.FastSpatial, $composer2, 6);
            TextKt.ProvideTextStyle(typography, ComposableLambdaKt.rememberComposableLambda(-1372614088, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SegmentedButtonKt$SegmentedButtonContent$1$1
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer $composer4, int $changed2) {
                    ComposerKt.sourceInformation($composer4, "C392@17369L24,393@17426L98,397@17538L181:SegmentedButton.kt#uh7d8r");
                    boolean invalid$iv$iv = true;
                    if (!$composer4.shouldExecute(($changed2 & 3) != 2, $changed2 & 1)) {
                        $composer4.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1372614088, $changed2, -1, "androidx.compose.material3.SegmentedButtonContent.<anonymous>.<anonymous> (SegmentedButton.kt:392)");
                    }
                    ComposerKt.sourceInformationMarkerStart($composer4, 773894976, "CC(rememberCoroutineScope)N(getContext)608@27648L68:Effects.kt#9igjgp");
                    ComposerKt.sourceInformationMarkerStart($composer4, 683737348, "CC(remember):Effects.kt#9igjgp");
                    Object it$iv$iv = $composer4.rememberedValue();
                    if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                        Object value$iv$iv = EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, $composer4);
                        $composer4.updateRememberedValue(value$iv$iv);
                        it$iv$iv = value$iv$iv;
                    }
                    CoroutineScope scope = (CoroutineScope) it$iv$iv;
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    ComposerKt.sourceInformationMarkerStart($composer4, -527175942, "CC(remember):SegmentedButton.kt#9igjgp");
                    FiniteAnimationSpec<Integer> finiteAnimationSpec = animationSpec;
                    Object it$iv = $composer4.rememberedValue();
                    if (it$iv == Composer.INSTANCE.getEmpty()) {
                        Object value$iv = new SegmentedButtonContentMeasurePolicy(scope, finiteAnimationSpec);
                        $composer4.updateRememberedValue(value$iv);
                        it$iv = value$iv;
                    }
                    SegmentedButtonContentMeasurePolicy measurePolicy = (SegmentedButtonContentMeasurePolicy) it$iv;
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    Modifier modifier$iv$iv2 = IntrinsicKt.height(Modifier.INSTANCE, IntrinsicSize.Min);
                    List listListOf = CollectionsKt.listOf((Object[]) new Function2[]{function2, function22});
                    ComposerKt.sourceInformationMarkerStart($composer4, 1399185516, "CC(Layout)P(!1,2)168@6883L62,165@6769L182:Layout.kt#80mrfh");
                    Function2<Composer, Integer, Unit> function2CombineAsVirtualLayouts = LayoutKt.combineAsVirtualLayouts(listListOf);
                    ComposerKt.sourceInformationMarkerStart($composer4, -290764973, "CC(remember):Layout.kt#9igjgp");
                    if ((((432 & 896) ^ 384) <= 256 || !$composer4.changed(measurePolicy)) && (432 & 384) != 256) {
                        invalid$iv$iv = false;
                    }
                    Object it$iv$iv2 = $composer4.rememberedValue();
                    if (invalid$iv$iv || it$iv$iv2 == Composer.INSTANCE.getEmpty()) {
                        Object value$iv$iv2 = MultiContentMeasurePolicyKt.createMeasurePolicy(measurePolicy);
                        $composer4.updateRememberedValue(value$iv$iv2);
                        it$iv$iv2 = value$iv$iv2;
                    }
                    MeasurePolicy measurePolicy$iv$iv = (MeasurePolicy) it$iv$iv2;
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    int $changed$iv$iv2 = 432 & 112;
                    ComposerKt.sourceInformationMarkerStart($composer4, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                    int compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer4, 0);
                    CompositionLocalMap localMap$iv$iv2 = $composer4.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv2 = ComposedModifierKt.materializeModifier($composer4, modifier$iv$iv2);
                    Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                    int $changed$iv$iv$iv2 = (($changed$iv$iv2 << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer4, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                    if (!($composer4.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer4.startReusableNode();
                    if ($composer4.getInserting()) {
                        $composer4.createNode(constructor2);
                    } else {
                        $composer4.useNode();
                    }
                    Composer $this$Layout_u24lambda_u240$iv$iv2 = Updater.m4433constructorimpl($composer4);
                    Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if ($this$Layout_u24lambda_u240$iv$iv2.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                        $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
                        $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash2);
                    }
                    Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv2, ComposeUiNode.INSTANCE.getSetModifier());
                    function2CombineAsVirtualLayouts.invoke($composer4, Integer.valueOf(($changed$iv$iv$iv2 >> 6) & 14));
                    $composer4.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, $composer2, 54), $composer2, 48);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.SegmentedButtonKt$$ExternalSyntheticLambda9
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return SegmentedButtonKt.SegmentedButtonContent$lambda$15(function2, function22, contentPadding, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final State<Integer> interactionCountAsState(InteractionSource $this$interactionCountAsState, Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, 281890131, "C(interactionCountAsState)460@19902L33,461@19961L499,461@19940L520:SegmentedButton.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(281890131, $changed, -1, "androidx.compose.material3.interactionCountAsState (SegmentedButton.kt:459)");
        }
        ComposerKt.sourceInformationMarkerStart($composer, 313479764, "CC(remember):SegmentedButton.kt#9igjgp");
        Object it$iv = $composer.rememberedValue();
        if (it$iv == Composer.INSTANCE.getEmpty()) {
            Object value$iv = SnapshotIntStateKt.mutableIntStateOf(0);
            $composer.updateRememberedValue(value$iv);
            it$iv = value$iv;
        }
        MutableIntState interactionCount = (MutableIntState) it$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerStart($composer, 313482118, "CC(remember):SegmentedButton.kt#9igjgp");
        boolean invalid$iv = ((($changed & 14) ^ 6) > 4 && $composer.changed($this$interactionCountAsState)) || ($changed & 6) == 4;
        Object it$iv2 = $composer.rememberedValue();
        if (invalid$iv || it$iv2 == Composer.INSTANCE.getEmpty()) {
            Object value$iv2 = (Function2) new SegmentedButtonKt$interactionCountAsState$1$1($this$interactionCountAsState, interactionCount, null);
            $composer.updateRememberedValue(value$iv2);
            it$iv2 = value$iv2;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        EffectsKt.LaunchedEffect($this$interactionCountAsState, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) it$iv2, $composer, $changed & 14);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return interactionCount;
    }

    private static final Modifier interactionZIndex(Modifier $this$interactionZIndex, final boolean checked, final State<Integer> state) {
        return LayoutModifierKt.layout($this$interactionZIndex, new Function3() { // from class: androidx.compose.material3.SegmentedButtonKt$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return SegmentedButtonKt.interactionZIndex$lambda$19(state, checked, (MeasureScope) obj, (Measurable) obj2, (Constraints) obj3);
            }
        });
    }

    static final MeasureResult interactionZIndex$lambda$19(final State $interactionCount, final boolean $checked, MeasureScope $this$layout, Measurable measurable, Constraints constraints) {
        final Placeable placeable = measurable.mo6783measureBRTryo0(constraints.getValue());
        return MeasureScope.layout$default($this$layout, placeable.getWidth(), placeable.getHeight(), null, new Function1() { // from class: androidx.compose.material3.SegmentedButtonKt$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SegmentedButtonKt.interactionZIndex$lambda$19$lambda$18($interactionCount, $checked, placeable, (Placeable.PlacementScope) obj);
            }
        }, 4, null);
    }

    static final Unit interactionZIndex$lambda$19$lambda$18(State $interactionCount, boolean $checked, Placeable $placeable, Placeable.PlacementScope $this$layout) {
        float zIndex = ((Number) $interactionCount.getValue()).floatValue() + ($checked ? CheckedZIndexFactor : 0.0f);
        $this$layout.place($placeable, 0, 0, zIndex);
        return Unit.INSTANCE;
    }
}
