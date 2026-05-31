package androidx.compose.material3;

import androidx.compose.animation.AnimatedVisibilityKt;
import androidx.compose.animation.EnterExitTransitionKt;
import androidx.compose.animation.EnterTransition;
import androidx.compose.animation.ExitTransition;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.material3.internal.ProvideContentColorTextStyleKt;
import androidx.compose.material3.tokens.ExtendedFabLargeTokens;
import androidx.compose.material3.tokens.ExtendedFabMediumTokens;
import androidx.compose.material3.tokens.ExtendedFabPrimaryTokens;
import androidx.compose.material3.tokens.ExtendedFabSmallTokens;
import androidx.compose.material3.tokens.FabBaselineTokens;
import androidx.compose.material3.tokens.FabLargeTokens;
import androidx.compose.material3.tokens.FabSmallTokens;
import androidx.compose.material3.tokens.MotionSchemeKeyTokens;
import androidx.compose.material3.tokens.TypographyKeyTokens;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.composer.linkbuffer.GroupFlagsKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.unit.Dp;
import androidx.profileinstaller.ProfileVerifier;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FloatingActionButton.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000t\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001an\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u000b\u001a\u00020\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0011\u0010\u000f\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u0010H\u0007¢\u0006\u0004\b\u0011\u0010\u0012\u001a\u0086\u0001\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00162\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u000b\u001a\u00020\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0011\u0010\u000f\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u0010H\u0003¢\u0006\u0004\b\u0018\u0010\u0019\u001an\u0010\u001a\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u000b\u001a\u00020\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0011\u0010\u000f\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u0010H\u0007¢\u0006\u0004\b\u001b\u0010\u0012\u001an\u0010\u001c\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u000b\u001a\u00020\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0011\u0010\u000f\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u0010H\u0007¢\u0006\u0004\b\u001d\u0010\u0012\u001ay\u0010\u001e\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u000b\u001a\u00020\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\u001c\u0010\u000f\u001a\u0018\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020\u00010\u001f¢\u0006\u0002\b\u0010¢\u0006\u0002\b!H\u0007¢\u0006\u0004\b\"\u0010#\u001a\u008b\u0001\u0010\u001e\u001a\u00020\u00012\u0011\u0010$\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u00102\u0011\u0010%\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u00102\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010&\u001a\u00020'2\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u000b\u001a\u00020\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0007¢\u0006\u0004\b(\u0010)\u001a»\u0001\u0010\u001e\u001a\u00020\u00012\u0011\u0010$\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u00102\u0011\u0010%\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u00102\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010*\u001a\u00020\u00162\u0006\u0010+\u001a\u00020\u00162\u0006\u0010,\u001a\u00020\u00162\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010&\u001a\u00020'2\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u000b\u001a\u00020\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0003¢\u0006\u0004\b-\u0010.\u001a\r\u0010G\u001a\u00020HH\u0003¢\u0006\u0002\u0010I\u001a\r\u0010J\u001a\u00020KH\u0003¢\u0006\u0002\u0010L\"\u0010\u0010/\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0004\n\u0002\u00100\"\u0010\u00101\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0004\n\u0002\u00100\"\u0010\u00102\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0004\n\u0002\u00100\"\u0010\u00103\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0004\n\u0002\u00100\"\u0010\u00104\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0004\n\u0002\u00100\"\u000e\u00105\u001a\u000206X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0010\u00107\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0004\n\u0002\u00100\"\u0010\u00108\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0004\n\u0002\u00100\"\u0010\u00109\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0004\n\u0002\u00100\"\u0010\u0010:\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0004\n\u0002\u00100\"\u0010\u0010;\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0004\n\u0002\u00100\"\u000e\u0010<\u001a\u000206X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010=\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0004\n\u0002\u00100\"\u0010\u0010>\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0004\n\u0002\u00100\"\u0010\u0010?\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0004\n\u0002\u00100\"\u0010\u0010@\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0004\n\u0002\u00100\"\u0010\u0010A\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0004\n\u0002\u00100\"\u000e\u0010B\u001a\u000206X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010C\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0004\n\u0002\u00100\"\u0010\u0010D\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0004\n\u0002\u00100\"\u0010\u0010E\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0004\n\u0002\u00100\"\u0010\u0010F\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0004\n\u0002\u00100¨\u0006M"}, d2 = {"FloatingActionButton", "", "onClick", "Lkotlin/Function0;", "modifier", "Landroidx/compose/ui/Modifier;", "shape", "Landroidx/compose/ui/graphics/Shape;", "containerColor", "Landroidx/compose/ui/graphics/Color;", "contentColor", "elevation", "Landroidx/compose/material3/FloatingActionButtonElevation;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "content", "Landroidx/compose/runtime/Composable;", "FloatingActionButton-X-z6DiA", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/graphics/Shape;JJLandroidx/compose/material3/FloatingActionButtonElevation;Landroidx/compose/foundation/interaction/MutableInteractionSource;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "textStyle", "Landroidx/compose/ui/text/TextStyle;", "minWidth", "Landroidx/compose/ui/unit/Dp;", "minHeight", "FloatingActionButton-lF-WlFE", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/text/TextStyle;FFLandroidx/compose/ui/Modifier;Landroidx/compose/ui/graphics/Shape;JJLandroidx/compose/material3/FloatingActionButtonElevation;Landroidx/compose/foundation/interaction/MutableInteractionSource;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;III)V", "SmallFloatingActionButton", "SmallFloatingActionButton-X-z6DiA", "LargeFloatingActionButton", "LargeFloatingActionButton-X-z6DiA", "ExtendedFloatingActionButton", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/RowScope;", "Lkotlin/ExtensionFunctionType;", "ExtendedFloatingActionButton-X-z6DiA", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/graphics/Shape;JJLandroidx/compose/material3/FloatingActionButtonElevation;Landroidx/compose/foundation/interaction/MutableInteractionSource;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "text", "icon", "expanded", "", "ExtendedFloatingActionButton-ElI5-7k", "(Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;ZLandroidx/compose/ui/graphics/Shape;JJLandroidx/compose/material3/FloatingActionButtonElevation;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;II)V", "startPadding", "endPadding", "iconPadding", "ExtendedFloatingActionButton-qtIzBjc", "(Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/text/TextStyle;FFFFFLandroidx/compose/ui/Modifier;ZLandroidx/compose/ui/graphics/Shape;JJLandroidx/compose/material3/FloatingActionButtonElevation;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;III)V", "SmallExtendedFabMinimumWidth", "F", "SmallExtendedFabMinimumHeight", "SmallExtendedFabPaddingStart", "SmallExtendedFabPaddingEnd", "SmallExtendedFabIconPadding", "SmallExtendedFabTextStyle", "Landroidx/compose/material3/tokens/TypographyKeyTokens;", "MediumExtendedFabMinimumWidth", "MediumExtendedFabMinimumHeight", "MediumExtendedFabPaddingStart", "MediumExtendedFabPaddingEnd", "MediumExtendedFabIconPadding", "MediumExtendedFabTextStyle", "LargeExtendedFabMinimumWidth", "LargeExtendedFabMinimumHeight", "LargeExtendedFabPaddingStart", "LargeExtendedFabPaddingEnd", "LargeExtendedFabIconPadding", "LargeExtendedFabTextStyle", "ExtendedFabStartIconPadding", "ExtendedFabEndIconPadding", "ExtendedFabTextPadding", "ExtendedFabMinimumWidth", "extendedFabCollapseAnimation", "Landroidx/compose/animation/ExitTransition;", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/animation/ExitTransition;", "extendedFabExpandAnimation", "Landroidx/compose/animation/EnterTransition;", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/animation/EnterTransition;", "material3"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class FloatingActionButtonKt {
    private static final float SmallExtendedFabMinimumWidth = ExtendedFabSmallTokens.INSTANCE.m3830getContainerHeightD9Ej5fM();
    private static final float SmallExtendedFabMinimumHeight = ExtendedFabSmallTokens.INSTANCE.m3830getContainerHeightD9Ej5fM();
    private static final float SmallExtendedFabPaddingStart = ExtendedFabSmallTokens.INSTANCE.m3833getLeadingSpaceD9Ej5fM();
    private static final float SmallExtendedFabPaddingEnd = ExtendedFabSmallTokens.INSTANCE.m3834getTrailingSpaceD9Ej5fM();
    private static final float SmallExtendedFabIconPadding = ExtendedFabSmallTokens.INSTANCE.m3831getIconLabelSpaceD9Ej5fM();
    private static final TypographyKeyTokens SmallExtendedFabTextStyle = TypographyKeyTokens.TitleMedium;
    private static final float MediumExtendedFabMinimumWidth = ExtendedFabMediumTokens.INSTANCE.m3815getContainerHeightD9Ej5fM();
    private static final float MediumExtendedFabMinimumHeight = ExtendedFabMediumTokens.INSTANCE.m3815getContainerHeightD9Ej5fM();
    private static final float MediumExtendedFabPaddingStart = ExtendedFabMediumTokens.INSTANCE.m3818getLeadingSpaceD9Ej5fM();
    private static final float MediumExtendedFabPaddingEnd = ExtendedFabMediumTokens.INSTANCE.m3819getTrailingSpaceD9Ej5fM();
    private static final float MediumExtendedFabIconPadding = Dp.m8150constructorimpl(12);
    private static final TypographyKeyTokens MediumExtendedFabTextStyle = TypographyKeyTokens.TitleLarge;
    private static final float LargeExtendedFabMinimumWidth = ExtendedFabLargeTokens.INSTANCE.m3810getContainerHeightD9Ej5fM();
    private static final float LargeExtendedFabMinimumHeight = ExtendedFabLargeTokens.INSTANCE.m3810getContainerHeightD9Ej5fM();
    private static final float LargeExtendedFabPaddingStart = ExtendedFabLargeTokens.INSTANCE.m3813getLeadingSpaceD9Ej5fM();
    private static final float LargeExtendedFabPaddingEnd = ExtendedFabLargeTokens.INSTANCE.m3814getTrailingSpaceD9Ej5fM();
    private static final float LargeExtendedFabIconPadding = Dp.m8150constructorimpl(16);
    private static final TypographyKeyTokens LargeExtendedFabTextStyle = TypographyKeyTokens.HeadlineSmall;
    private static final float ExtendedFabStartIconPadding = Dp.m8150constructorimpl(16);
    private static final float ExtendedFabEndIconPadding = Dp.m8150constructorimpl(12);
    private static final float ExtendedFabTextPadding = Dp.m8150constructorimpl(20);
    private static final float ExtendedFabMinimumWidth = Dp.m8150constructorimpl(80);

    static final Unit ExtendedFloatingActionButton_ElI5_7k$lambda$8(Function2 function2, Function2 function22, Function0 function0, Modifier modifier, boolean z, Shape shape, long j, long j2, FloatingActionButtonElevation floatingActionButtonElevation, MutableInteractionSource mutableInteractionSource, int i, int i2, Composer composer, int i3) {
        m2556ExtendedFloatingActionButtonElI57k(function2, function22, function0, modifier, z, shape, j, j2, floatingActionButtonElevation, mutableInteractionSource, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit ExtendedFloatingActionButton_X_z6DiA$lambda$7(Function0 function0, Modifier modifier, Shape shape, long j, long j2, FloatingActionButtonElevation floatingActionButtonElevation, MutableInteractionSource mutableInteractionSource, Function3 function3, int i, int i2, Composer composer, int i3) {
        m2557ExtendedFloatingActionButtonXz6DiA(function0, modifier, shape, j, j2, floatingActionButtonElevation, mutableInteractionSource, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit ExtendedFloatingActionButton_qtIzBjc$lambda$9(Function2 function2, Function2 function22, Function0 function0, TextStyle textStyle, float f, float f2, float f3, float f4, float f5, Modifier modifier, boolean z, Shape shape, long j, long j2, FloatingActionButtonElevation floatingActionButtonElevation, MutableInteractionSource mutableInteractionSource, int i, int i2, int i3, Composer composer, int i4) {
        m2558ExtendedFloatingActionButtonqtIzBjc(function2, function22, function0, textStyle, f, f2, f3, f4, f5, modifier, z, shape, j, j2, floatingActionButtonElevation, mutableInteractionSource, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
        return Unit.INSTANCE;
    }

    static final Unit FloatingActionButton_X_z6DiA$lambda$0(Function0 function0, Modifier modifier, Shape shape, long j, long j2, FloatingActionButtonElevation floatingActionButtonElevation, MutableInteractionSource mutableInteractionSource, Function2 function2, int i, int i2, Composer composer, int i3) {
        m2559FloatingActionButtonXz6DiA(function0, modifier, shape, j, j2, floatingActionButtonElevation, mutableInteractionSource, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit FloatingActionButton_lF_WlFE$lambda$4(Function0 function0, TextStyle textStyle, float f, float f2, Modifier modifier, Shape shape, long j, long j2, FloatingActionButtonElevation floatingActionButtonElevation, MutableInteractionSource mutableInteractionSource, Function2 function2, int i, int i2, int i3, Composer composer, int i4) {
        m2560FloatingActionButtonlFWlFE(function0, textStyle, f, f2, modifier, shape, j, j2, floatingActionButtonElevation, mutableInteractionSource, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
        return Unit.INSTANCE;
    }

    static final Unit LargeFloatingActionButton_X_z6DiA$lambda$6(Function0 function0, Modifier modifier, Shape shape, long j, long j2, FloatingActionButtonElevation floatingActionButtonElevation, MutableInteractionSource mutableInteractionSource, Function2 function2, int i, int i2, Composer composer, int i3) {
        m2561LargeFloatingActionButtonXz6DiA(function0, modifier, shape, j, j2, floatingActionButtonElevation, mutableInteractionSource, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit SmallFloatingActionButton_X_z6DiA$lambda$5(Function0 function0, Modifier modifier, Shape shape, long j, long j2, FloatingActionButtonElevation floatingActionButtonElevation, MutableInteractionSource mutableInteractionSource, Function2 function2, int i, int i2, Composer composer, int i3) {
        m2562SmallFloatingActionButtonXz6DiA(function0, modifier, shape, j, j2, floatingActionButtonElevation, mutableInteractionSource, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: FloatingActionButton-X-z6DiA, reason: not valid java name */
    public static final void m2559FloatingActionButtonXz6DiA(final Function0<Unit> function0, Modifier modifier, Shape shape, long containerColor, long contentColor, FloatingActionButtonElevation elevation, MutableInteractionSource interactionSource, final Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer, final int $changed, final int i) {
        Function0<Unit> function02;
        Modifier modifier2;
        Shape shape2;
        long j;
        long contentColor2;
        FloatingActionButtonElevation elevation2;
        MutableInteractionSource mutableInteractionSource;
        Composer $composer2;
        final Modifier modifier3;
        final Shape shape3;
        final long containerColor2;
        final long contentColor3;
        final FloatingActionButtonElevation elevation3;
        final MutableInteractionSource interactionSource2;
        Shape shape4;
        long containerColor3;
        int $dirty;
        Modifier modifier4;
        int $dirty2;
        long containerColor4;
        Modifier modifier5;
        MutableInteractionSource interactionSource3;
        FloatingActionButtonElevation elevation4;
        Shape shape5;
        long containerColor5;
        long containerColor6;
        Composer $composer3 = $composer.startRestartGroup(748201188);
        ComposerKt.sourceInformation($composer3, "C(FloatingActionButton)N(onClick,modifier,shape,containerColor:c#ui.graphics.Color,contentColor:c#ui.graphics.Color,elevation,interactionSource,content)120@5970L5,118@5884L325:FloatingActionButton.kt#uh7d8r");
        int $dirty3 = $changed;
        if ((i & 1) != 0) {
            $dirty3 |= 6;
            function02 = function0;
        } else if (($changed & 6) == 0) {
            function02 = function0;
            $dirty3 |= $composer3.changedInstance(function02) ? 4 : 2;
        } else {
            function02 = function0;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty3 |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty3 |= $composer3.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                shape2 = shape;
                int i3 = $composer3.changed(shape2) ? 256 : 128;
                $dirty3 |= i3;
            } else {
                shape2 = shape;
            }
            $dirty3 |= i3;
        } else {
            shape2 = shape;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                j = containerColor;
                int i4 = $composer3.changed(j) ? 2048 : 1024;
                $dirty3 |= i4;
            } else {
                j = containerColor;
            }
            $dirty3 |= i4;
        } else {
            j = containerColor;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                contentColor2 = contentColor;
                int i5 = $composer3.changed(contentColor2) ? 16384 : 8192;
                $dirty3 |= i5;
            } else {
                contentColor2 = contentColor;
            }
            $dirty3 |= i5;
        } else {
            contentColor2 = contentColor;
        }
        if ((196608 & $changed) == 0) {
            if ((i & 32) == 0) {
                elevation2 = elevation;
                int i6 = $composer3.changed(elevation2) ? 131072 : 65536;
                $dirty3 |= i6;
            } else {
                elevation2 = elevation;
            }
            $dirty3 |= i6;
        } else {
            elevation2 = elevation;
        }
        int i7 = i & 64;
        if (i7 != 0) {
            $dirty3 |= 1572864;
            mutableInteractionSource = interactionSource;
        } else if ((1572864 & $changed) == 0) {
            mutableInteractionSource = interactionSource;
            $dirty3 |= $composer3.changed(mutableInteractionSource) ? 1048576 : 524288;
        } else {
            mutableInteractionSource = interactionSource;
        }
        if ((i & 128) != 0) {
            $dirty3 |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty3 |= $composer3.changedInstance(function2) ? 8388608 : 4194304;
        }
        if ($composer3.shouldExecute(($dirty3 & 4793491) != 4793490, $dirty3 & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "111@5554L5,112@5618L14,113@5660L31,114@5769L11");
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                Modifier.Companion modifier6 = i2 != 0 ? Modifier.INSTANCE : modifier2;
                if ((i & 4) != 0) {
                    $dirty3 &= -897;
                    shape4 = FloatingActionButtonDefaults.INSTANCE.getShape($composer3, 6);
                } else {
                    shape4 = shape2;
                }
                if ((i & 8) != 0) {
                    containerColor3 = FloatingActionButtonDefaults.INSTANCE.getContainerColor($composer3, 6);
                    $dirty3 &= -7169;
                } else {
                    containerColor3 = j;
                }
                if ((i & 16) != 0) {
                    contentColor2 = ColorSchemeKt.m2347contentColorForek8zF_U(containerColor3, $composer3, ($dirty3 >> 9) & 14);
                    $dirty = $dirty3 & (-57345);
                } else {
                    $dirty = $dirty3;
                }
                if ((i & 32) != 0) {
                    containerColor4 = containerColor3;
                    modifier4 = modifier6;
                    $dirty2 = 6;
                    $dirty &= -458753;
                    elevation2 = FloatingActionButtonDefaults.INSTANCE.m2550elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, $composer3, 24576, 15);
                } else {
                    modifier4 = modifier6;
                    $dirty2 = 6;
                    containerColor4 = containerColor3;
                }
                if (i7 != 0) {
                    modifier5 = modifier4;
                    interactionSource3 = null;
                    elevation4 = elevation2;
                    $dirty3 = $dirty;
                    shape5 = shape4;
                    containerColor5 = containerColor4;
                    containerColor6 = contentColor2;
                } else {
                    modifier5 = modifier4;
                    interactionSource3 = interactionSource;
                    elevation4 = elevation2;
                    $dirty3 = $dirty;
                    shape5 = shape4;
                    containerColor5 = containerColor4;
                    containerColor6 = contentColor2;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty3 &= -897;
                }
                if ((i & 8) != 0) {
                    $dirty3 &= -7169;
                }
                if ((i & 16) != 0) {
                    $dirty3 &= -57345;
                }
                if ((i & 32) != 0) {
                    $dirty3 &= -458753;
                    $dirty2 = 6;
                    modifier5 = modifier2;
                    shape5 = shape2;
                    interactionSource3 = mutableInteractionSource;
                    containerColor5 = j;
                    containerColor6 = contentColor2;
                    elevation4 = elevation2;
                } else {
                    $dirty2 = 6;
                    modifier5 = modifier2;
                    shape5 = shape2;
                    interactionSource3 = mutableInteractionSource;
                    containerColor5 = j;
                    containerColor6 = contentColor2;
                    elevation4 = elevation2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(748201188, $dirty3, -1, "androidx.compose.material3.FloatingActionButton (FloatingActionButton.kt:118)");
            }
            $composer2 = $composer3;
            m2560FloatingActionButtonlFWlFE(function02, TypographyKt.getValue(ExtendedFabPrimaryTokens.INSTANCE.getLabelTextFont(), $composer3, $dirty2), FabBaselineTokens.INSTANCE.m3836getContainerWidthD9Ej5fM(), FabBaselineTokens.INSTANCE.m3835getContainerHeightD9Ej5fM(), modifier5, shape5, containerColor5, containerColor6, elevation4, interactionSource3, function2, $composer2, ($dirty3 & 14) | 3456 | (($dirty3 << 9) & 57344) | (($dirty3 << 9) & 458752) | (($dirty3 << 9) & 3670016) | (($dirty3 << 9) & 29360128) | (($dirty3 << 9) & 234881024) | (1879048192 & ($dirty3 << 9)), ($dirty3 >> 21) & 14, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier5;
            shape3 = shape5;
            containerColor2 = containerColor5;
            contentColor3 = containerColor6;
            elevation3 = elevation4;
            interactionSource2 = interactionSource3;
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            shape3 = shape2;
            containerColor2 = j;
            contentColor3 = contentColor2;
            elevation3 = elevation2;
            interactionSource2 = interactionSource;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.FloatingActionButtonKt$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return FloatingActionButtonKt.FloatingActionButton_X_z6DiA$lambda$0(function0, modifier3, shape3, containerColor2, contentColor3, elevation3, interactionSource2, function2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Type inference failed for: r10v5, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r10v6 */
    /* JADX WARN: Type inference failed for: r10v7 */
    /* JADX WARN: Type inference failed for: r10v8 */
    /* JADX WARN: Type inference failed for: r10v9 */
    /* JADX INFO: renamed from: FloatingActionButton-lF-WlFE, reason: not valid java name */
    private static final void m2560FloatingActionButtonlFWlFE(final Function0<Unit> function0, final TextStyle textStyle, final float f, final float f2, Modifier modifier, Shape shape, long j, long j2, FloatingActionButtonElevation floatingActionButtonElevation, MutableInteractionSource mutableInteractionSource, final Function2<? super Composer, ? super Integer, Unit> function2, Composer composer, final int i, final int i2, final int i3) {
        Function0<Unit> function02;
        float f3;
        float f4;
        Modifier modifier2;
        Shape shape2;
        int i4;
        long jM2347contentColorForek8zF_U;
        FloatingActionButtonElevation floatingActionButtonElevation2;
        int i5;
        int i6;
        int i7;
        Composer composer2;
        final FloatingActionButtonElevation floatingActionButtonElevation3;
        final MutableInteractionSource mutableInteractionSource2;
        final Modifier modifier3;
        final Shape shape3;
        final long j3;
        final long j4;
        Modifier.Companion companion;
        int i8;
        Shape shape4;
        long containerColor;
        int i9;
        Modifier modifier4;
        boolean z;
        long j5;
        FloatingActionButtonElevation floatingActionButtonElevationM2550elevationxZ9QkE;
        MutableInteractionSource mutableInteractionSource3;
        Shape shape5;
        int i10;
        long j6;
        long j7;
        ?? r10;
        MutableInteractionSource mutableInteractionSource4;
        int i11;
        int i12;
        Composer composerStartRestartGroup = composer.startRestartGroup(121669932);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(FloatingActionButton)N(onClick,textStyle,minWidth:c#ui.unit.Dp,minHeight:c#ui.unit.Dp,modifier,shape,containerColor:c#ui.graphics.Color,contentColor:c#ui.graphics.Color,elevation,interactionSource,content)150@6953L22,155@7158L54,157@7273L330,148@6879L724:FloatingActionButton.kt#uh7d8r");
        int i13 = i;
        if ((i3 & 1) != 0) {
            i13 |= 6;
            function02 = function0;
        } else if ((i & 6) == 0) {
            function02 = function0;
            i13 |= composerStartRestartGroup.changedInstance(function02) ? 4 : 2;
        } else {
            function02 = function0;
        }
        if ((i3 & 2) != 0) {
            i13 |= 48;
        } else if ((i & 48) == 0) {
            i13 |= composerStartRestartGroup.changed(textStyle) ? 32 : 16;
        }
        if ((i3 & 4) != 0) {
            i13 |= 384;
            f3 = f;
        } else if ((i & 384) == 0) {
            f3 = f;
            i13 |= composerStartRestartGroup.changed(f3) ? 256 : 128;
        } else {
            f3 = f;
        }
        if ((i3 & 8) != 0) {
            i13 |= 3072;
            f4 = f2;
        } else if ((i & 3072) == 0) {
            f4 = f2;
            i13 |= composerStartRestartGroup.changed(f4) ? 2048 : 1024;
        } else {
            f4 = f2;
        }
        int i14 = i3 & 16;
        if (i14 != 0) {
            i13 |= 24576;
            modifier2 = modifier;
        } else if ((i & 24576) == 0) {
            modifier2 = modifier;
            i13 |= composerStartRestartGroup.changed(modifier2) ? 16384 : 8192;
        } else {
            modifier2 = modifier;
        }
        if ((196608 & i) == 0) {
            if ((i3 & 32) == 0) {
                shape2 = shape;
                int i15 = composerStartRestartGroup.changed(shape2) ? 131072 : 65536;
                i13 |= i15;
            } else {
                shape2 = shape;
            }
            i13 |= i15;
        } else {
            shape2 = shape;
        }
        if ((1572864 & i) == 0) {
            if ((i3 & 64) == 0) {
                i12 = i13;
                int i16 = composerStartRestartGroup.changed(j) ? 1048576 : 524288;
                i4 = i12 | i16;
            } else {
                i12 = i13;
            }
            i4 = i12 | i16;
        } else {
            i4 = i13;
        }
        if ((i & 12582912) == 0) {
            if ((i3 & 128) == 0) {
                jM2347contentColorForek8zF_U = j2;
                int i17 = composerStartRestartGroup.changed(jM2347contentColorForek8zF_U) ? 8388608 : 4194304;
                i4 |= i17;
            } else {
                jM2347contentColorForek8zF_U = j2;
            }
            i4 |= i17;
        } else {
            jM2347contentColorForek8zF_U = j2;
        }
        if ((i & 100663296) == 0) {
            if ((i3 & 256) == 0) {
                floatingActionButtonElevation2 = floatingActionButtonElevation;
                if (composerStartRestartGroup.changed(floatingActionButtonElevation2)) {
                    i11 = 67108864;
                }
                i4 |= i11;
            } else {
                floatingActionButtonElevation2 = floatingActionButtonElevation;
            }
            i11 = GroupFlagsKt.HasAuxSlotFlag;
            i4 |= i11;
        } else {
            floatingActionButtonElevation2 = floatingActionButtonElevation;
        }
        int i18 = i2;
        int i19 = i3 & 512;
        if (i19 != 0) {
            i4 |= 805306368;
            i5 = i19;
        } else if ((i & 805306368) == 0) {
            i5 = i19;
            i4 |= composerStartRestartGroup.changed(mutableInteractionSource) ? GroupFlagsKt.HasMovableContentFlag : GroupFlagsKt.IsMovableContentFlag;
        } else {
            i5 = i19;
        }
        if ((i3 & 1024) != 0) {
            i6 = i18 | 6;
        } else {
            if ((i2 & 6) == 0) {
                i18 |= composerStartRestartGroup.changedInstance(function2) ? 4 : 2;
            }
            i6 = i18;
        }
        int i20 = i6;
        if (composerStartRestartGroup.shouldExecute(((i4 & 306783379) == 306783378 && (i20 & 3) == 2) ? false : true, i4 & 1)) {
            composerStartRestartGroup.startDefaults();
            ComposerKt.sourceInformation(composerStartRestartGroup, "139@6428L5,140@6492L14,141@6534L31,142@6643L11");
            if ((i & 1) != 0 && !composerStartRestartGroup.getDefaultsInvalid()) {
                composerStartRestartGroup.skipToGroupEnd();
                if ((i3 & 32) != 0) {
                    i4 &= -458753;
                }
                if ((i3 & 64) != 0) {
                    i4 &= -3670017;
                }
                if ((i3 & 128) != 0) {
                    i4 &= -29360129;
                }
                if ((i3 & 256) != 0) {
                    j6 = j;
                    i7 = i20;
                    i10 = i4 & (-234881025);
                    r10 = 1;
                    floatingActionButtonElevationM2550elevationxZ9QkE = floatingActionButtonElevation2;
                    shape5 = shape2;
                    j7 = jM2347contentColorForek8zF_U;
                    mutableInteractionSource3 = mutableInteractionSource;
                } else {
                    j6 = j;
                    i7 = i20;
                    r10 = 1;
                    floatingActionButtonElevationM2550elevationxZ9QkE = floatingActionButtonElevation2;
                    shape5 = shape2;
                    j7 = jM2347contentColorForek8zF_U;
                    i10 = i4;
                    mutableInteractionSource3 = mutableInteractionSource;
                }
            } else {
                if (i14 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if ((i3 & 32) == 0) {
                    i8 = i4;
                    shape4 = shape2;
                } else {
                    int i21 = i4 & (-458753);
                    shape4 = FloatingActionButtonDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                    i8 = i21;
                }
                if ((i3 & 64) == 0) {
                    containerColor = j;
                } else {
                    containerColor = FloatingActionButtonDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                    i8 &= -3670017;
                }
                if ((i3 & 128) == 0) {
                    i9 = i8;
                } else {
                    jM2347contentColorForek8zF_U = ColorSchemeKt.m2347contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i8 >> 18) & 14);
                    i9 = i8 & (-29360129);
                }
                if ((i3 & 256) == 0) {
                    i7 = i20;
                    modifier4 = companion;
                    z = true;
                    j5 = containerColor;
                    floatingActionButtonElevationM2550elevationxZ9QkE = floatingActionButtonElevation;
                } else {
                    j5 = containerColor;
                    i7 = i20;
                    modifier4 = companion;
                    z = true;
                    floatingActionButtonElevationM2550elevationxZ9QkE = FloatingActionButtonDefaults.INSTANCE.m2550elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, composerStartRestartGroup, 24576, 15);
                    i9 &= -234881025;
                }
                if (i5 == 0) {
                    modifier2 = modifier4;
                    mutableInteractionSource3 = mutableInteractionSource;
                    shape5 = shape4;
                    i10 = i9;
                    j6 = j5;
                    j7 = jM2347contentColorForek8zF_U;
                    r10 = z;
                } else {
                    mutableInteractionSource3 = null;
                    modifier2 = modifier4;
                    shape5 = shape4;
                    i10 = i9;
                    j6 = j5;
                    j7 = jM2347contentColorForek8zF_U;
                    r10 = z;
                }
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(121669932, i10, i7, "androidx.compose.material3.FloatingActionButton (FloatingActionButton.kt:145)");
            }
            if (mutableInteractionSource3 == null) {
                composerStartRestartGroup.startReplaceGroup(-282833393);
                ComposerKt.sourceInformation(composerStartRestartGroup, "147@6835L39");
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 960707667, "CC(remember):FloatingActionButton.kt#9igjgp");
                Object objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    MutableInteractionSource MutableInteractionSource = InteractionSourceKt.MutableInteractionSource();
                    composerStartRestartGroup.updateRememberedValue(MutableInteractionSource);
                    objRememberedValue = MutableInteractionSource;
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                composerStartRestartGroup.endReplaceGroup();
                mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue;
            } else {
                composerStartRestartGroup.startReplaceGroup(960707016);
                composerStartRestartGroup.endReplaceGroup();
                mutableInteractionSource4 = mutableInteractionSource3;
            }
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 960711426, "CC(remember):FloatingActionButton.kt#9igjgp");
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                Function1 function1 = new Function1() { // from class: androidx.compose.material3.FloatingActionButtonKt$$ExternalSyntheticLambda3
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return FloatingActionButtonKt.FloatingActionButton_lF_WlFE$lambda$3$lambda$2((SemanticsPropertyReceiver) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(function1);
                objRememberedValue2 = function1;
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            final float f5 = f3;
            final float f6 = f4;
            final long j8 = j7;
            composer2 = composerStartRestartGroup;
            SurfaceKt.m3017Surfaceo_FOJdg(function02, SemanticsModifierKt.semantics$default(modifier2, false, (Function1) objRememberedValue2, r10, null), false, shape5, j6, j7, floatingActionButtonElevationM2550elevationxZ9QkE.getDefaultElevation(), floatingActionButtonElevationM2550elevationxZ9QkE.shadowElevation$material3(mutableInteractionSource4, composerStartRestartGroup, (i10 >> 21) & 112).getValue().m8164unboximpl(), null, mutableInteractionSource4, ComposableLambdaKt.rememberComposableLambda(-1779603465, r10, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$FloatingActionButton$3
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                    invoke(composer3, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer $composer, int $changed) {
                    ComposerKt.sourceInformation($composer, "C158@7364L233,158@7283L314:FloatingActionButton.kt#uh7d8r");
                    if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
                        $composer.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1779603465, $changed, -1, "androidx.compose.material3.FloatingActionButton.<anonymous> (FloatingActionButton.kt:158)");
                    }
                    long j9 = j8;
                    TextStyle textStyle2 = textStyle;
                    final float f7 = f5;
                    final float f8 = f6;
                    final Function2<Composer, Integer, Unit> function22 = function2;
                    ProvideContentColorTextStyleKt.m3452ProvideContentColorTextStyle3JVO9M(j9, textStyle2, ComposableLambdaKt.rememberComposableLambda(-1767363041, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$FloatingActionButton$3.1
                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                            invoke(composer3, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer $composer2, int $changed2) {
                            Function0<ComposeUiNode> function03;
                            ComposerKt.sourceInformation($composer2, "C159@7378L209:FloatingActionButton.kt#uh7d8r");
                            if (!$composer2.shouldExecute(($changed2 & 3) != 2, $changed2 & 1)) {
                                $composer2.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1767363041, $changed2, -1, "androidx.compose.material3.FloatingActionButton.<anonymous>.<anonymous> (FloatingActionButton.kt:159)");
                            }
                            Modifier modifier$iv = SizeKt.m1099defaultMinSizeVpY3zN4(Modifier.INSTANCE, f7, f8);
                            Alignment contentAlignment$iv = Alignment.INSTANCE.getCenter();
                            Function2<Composer, Integer, Unit> function23 = function22;
                            ComposerKt.sourceInformationMarkerStart($composer2, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
                            MeasurePolicy measurePolicy$iv = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
                            int $changed$iv$iv = (48 << 3) & 112;
                            ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
                            CompositionLocalMap localMap$iv$iv = $composer2.getCurrentCompositionLocalMap();
                            Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer2, modifier$iv);
                            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                            int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                            if (!($composer2.getApplier() instanceof Applier)) {
                                ComposablesKt.invalidApplier();
                            }
                            $composer2.startReusableNode();
                            if ($composer2.getInserting()) {
                                function03 = constructor;
                                $composer2.createNode(function03);
                            } else {
                                function03 = constructor;
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
                            int i22 = ($changed$iv$iv$iv >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer2, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                            int i23 = ((48 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer2, -339027051, "C163@7564L9:FloatingActionButton.kt#uh7d8r");
                            function23.invoke($composer2, 0);
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
                    }, $composer, 54), $composer, 384);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, composerStartRestartGroup, 54), composer2, (i10 & 14) | ((i10 >> 6) & 7168) | ((i10 >> 6) & 57344) | ((i10 >> 6) & 458752), 6, 260);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            floatingActionButtonElevation3 = floatingActionButtonElevationM2550elevationxZ9QkE;
            mutableInteractionSource2 = mutableInteractionSource3;
            modifier3 = modifier2;
            shape3 = shape5;
            j4 = j6;
            j3 = j7;
        } else {
            i7 = i20;
            composer2 = composerStartRestartGroup;
            composer2.skipToGroupEnd();
            floatingActionButtonElevation3 = floatingActionButtonElevation;
            mutableInteractionSource2 = mutableInteractionSource;
            modifier3 = modifier2;
            shape3 = shape2;
            j3 = jM2347contentColorForek8zF_U;
            j4 = j;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.FloatingActionButtonKt$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return FloatingActionButtonKt.FloatingActionButton_lF_WlFE$lambda$4(function0, textStyle, f, f2, modifier3, shape3, j4, j3, floatingActionButtonElevation3, mutableInteractionSource2, function2, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    static final Unit FloatingActionButton_lF_WlFE$lambda$3$lambda$2(SemanticsPropertyReceiver $this$semantics) {
        SemanticsPropertiesKt.m7362setRolekuIjeqM($this$semantics, Role.INSTANCE.m7343getButtono7Vup1c());
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: SmallFloatingActionButton-X-z6DiA, reason: not valid java name */
    public static final void m2562SmallFloatingActionButtonXz6DiA(final Function0<Unit> function0, Modifier modifier, Shape shape, long containerColor, long contentColor, FloatingActionButtonElevation elevation, MutableInteractionSource interactionSource, final Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer, final int $changed, final int i) {
        Function0<Unit> function02;
        Modifier modifier2;
        Shape shape2;
        long j;
        long contentColor2;
        FloatingActionButtonElevation elevation2;
        MutableInteractionSource mutableInteractionSource;
        Composer $composer2;
        final Modifier modifier3;
        final Shape shape3;
        final long containerColor2;
        final long contentColor3;
        final FloatingActionButtonElevation elevation3;
        final MutableInteractionSource interactionSource2;
        Modifier.Companion modifier4;
        Shape shape4;
        int $dirty;
        long containerColor3;
        int $dirty2;
        long containerColor4;
        Shape shape5;
        long containerColor5;
        int i2;
        MutableInteractionSource interactionSource3;
        long contentColor4;
        FloatingActionButtonElevation elevation4;
        Composer $composer3 = $composer.startRestartGroup(26608441);
        ComposerKt.sourceInformation($composer3, "C(SmallFloatingActionButton)N(onClick,modifier,shape,containerColor:c#ui.graphics.Color,contentColor:c#ui.graphics.Color,elevation,interactionSource,content)208@9786L441:FloatingActionButton.kt#uh7d8r");
        int $dirty3 = $changed;
        if ((i & 1) != 0) {
            $dirty3 |= 6;
            function02 = function0;
        } else if (($changed & 6) == 0) {
            function02 = function0;
            $dirty3 |= $composer3.changedInstance(function02) ? 4 : 2;
        } else {
            function02 = function0;
        }
        int i3 = i & 2;
        if (i3 != 0) {
            $dirty3 |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty3 |= $composer3.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                shape2 = shape;
                int i4 = $composer3.changed(shape2) ? 256 : 128;
                $dirty3 |= i4;
            } else {
                shape2 = shape;
            }
            $dirty3 |= i4;
        } else {
            shape2 = shape;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                j = containerColor;
                int i5 = $composer3.changed(j) ? 2048 : 1024;
                $dirty3 |= i5;
            } else {
                j = containerColor;
            }
            $dirty3 |= i5;
        } else {
            j = containerColor;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                contentColor2 = contentColor;
                int i6 = $composer3.changed(contentColor2) ? 16384 : 8192;
                $dirty3 |= i6;
            } else {
                contentColor2 = contentColor;
            }
            $dirty3 |= i6;
        } else {
            contentColor2 = contentColor;
        }
        if ((196608 & $changed) == 0) {
            if ((i & 32) == 0) {
                elevation2 = elevation;
                int i7 = $composer3.changed(elevation2) ? 131072 : 65536;
                $dirty3 |= i7;
            } else {
                elevation2 = elevation;
            }
            $dirty3 |= i7;
        } else {
            elevation2 = elevation;
        }
        int i8 = i & 64;
        if (i8 != 0) {
            $dirty3 |= 1572864;
            mutableInteractionSource = interactionSource;
        } else if ((1572864 & $changed) == 0) {
            mutableInteractionSource = interactionSource;
            $dirty3 |= $composer3.changed(mutableInteractionSource) ? 1048576 : 524288;
        } else {
            mutableInteractionSource = interactionSource;
        }
        if ((i & 128) != 0) {
            $dirty3 |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty3 |= $composer3.changedInstance(function2) ? 8388608 : 4194304;
        }
        if (!$composer3.shouldExecute(($dirty3 & 4793491) != 4793490, $dirty3 & 1)) {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            shape3 = shape2;
            containerColor2 = j;
            contentColor3 = contentColor2;
            elevation3 = elevation2;
            interactionSource2 = interactionSource;
        } else {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "201@9451L10,202@9520L14,203@9562L31,204@9671L11");
            if (($changed & 1) != 0 && !$composer3.getDefaultsInvalid()) {
                $composer3.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty3 &= -897;
                }
                if ((i & 8) != 0) {
                    $dirty3 &= -7169;
                }
                if ((i & 16) != 0) {
                    $dirty3 &= -57345;
                }
                if ((i & 32) != 0) {
                    $dirty3 &= -458753;
                    shape5 = shape2;
                    interactionSource3 = mutableInteractionSource;
                    containerColor5 = j;
                    contentColor4 = contentColor2;
                    elevation4 = elevation2;
                    i2 = 26608441;
                } else {
                    shape5 = shape2;
                    interactionSource3 = mutableInteractionSource;
                    containerColor5 = j;
                    contentColor4 = contentColor2;
                    elevation4 = elevation2;
                    i2 = 26608441;
                }
            } else {
                if (i3 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if ((i & 4) == 0) {
                    shape4 = shape2;
                } else {
                    $dirty3 &= -897;
                    shape4 = FloatingActionButtonDefaults.INSTANCE.getSmallShape($composer3, 6);
                }
                if ((i & 8) == 0) {
                    $dirty = $dirty3;
                    containerColor3 = j;
                } else {
                    $dirty = $dirty3 & (-7169);
                    containerColor3 = FloatingActionButtonDefaults.INSTANCE.getContainerColor($composer3, 6);
                }
                if ((i & 16) == 0) {
                    $dirty2 = $dirty;
                } else {
                    contentColor2 = ColorSchemeKt.m2347contentColorForek8zF_U(containerColor3, $composer3, ($dirty >> 9) & 14);
                    $dirty2 = $dirty & (-57345);
                }
                if ((i & 32) == 0) {
                    containerColor4 = containerColor3;
                } else {
                    containerColor4 = containerColor3;
                    $dirty2 &= -458753;
                    elevation2 = FloatingActionButtonDefaults.INSTANCE.m2550elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, $composer3, 24576, 15);
                }
                if (i8 == 0) {
                    modifier2 = modifier4;
                    shape5 = shape4;
                    $dirty3 = $dirty2;
                    containerColor5 = containerColor4;
                    i2 = 26608441;
                    interactionSource3 = interactionSource;
                    contentColor4 = contentColor2;
                    elevation4 = elevation2;
                } else {
                    modifier2 = modifier4;
                    shape5 = shape4;
                    containerColor5 = containerColor4;
                    i2 = 26608441;
                    interactionSource3 = null;
                    elevation4 = elevation2;
                    $dirty3 = $dirty2;
                    contentColor4 = contentColor2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(i2, $dirty3, -1, "androidx.compose.material3.SmallFloatingActionButton (FloatingActionButton.kt:207)");
            }
            $composer2 = $composer3;
            m2559FloatingActionButtonXz6DiA(function02, SizeKt.m1119sizeInqDBjuR0$default(modifier2, FabSmallTokens.INSTANCE.m3865getContainerWidthD9Ej5fM(), FabSmallTokens.INSTANCE.m3864getContainerHeightD9Ej5fM(), 0.0f, 0.0f, 12, null), shape5, containerColor5, contentColor4, elevation4, interactionSource3, function2, $composer2, ($dirty3 & 14) | ($dirty3 & 896) | ($dirty3 & 7168) | (57344 & $dirty3) | (458752 & $dirty3) | (3670016 & $dirty3) | (29360128 & $dirty3), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier2;
            shape3 = shape5;
            containerColor2 = containerColor5;
            contentColor3 = contentColor4;
            elevation3 = elevation4;
            interactionSource2 = interactionSource3;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.FloatingActionButtonKt$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return FloatingActionButtonKt.SmallFloatingActionButton_X_z6DiA$lambda$5(function0, modifier3, shape3, containerColor2, contentColor3, elevation3, interactionSource2, function2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: LargeFloatingActionButton-X-z6DiA, reason: not valid java name */
    public static final void m2561LargeFloatingActionButtonXz6DiA(final Function0<Unit> function0, Modifier modifier, Shape shape, long containerColor, long contentColor, FloatingActionButtonElevation elevation, MutableInteractionSource interactionSource, final Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer, final int $changed, final int i) {
        Function0<Unit> function02;
        Modifier modifier2;
        Shape shape2;
        long j;
        long contentColor2;
        FloatingActionButtonElevation elevation2;
        MutableInteractionSource mutableInteractionSource;
        Composer $composer2;
        final Modifier modifier3;
        final Shape shape3;
        final long containerColor2;
        final long contentColor3;
        final FloatingActionButtonElevation elevation3;
        final MutableInteractionSource interactionSource2;
        Modifier.Companion modifier4;
        Shape shape4;
        int $dirty;
        long containerColor3;
        int $dirty2;
        long containerColor4;
        Shape shape5;
        long containerColor5;
        int i2;
        MutableInteractionSource interactionSource3;
        long contentColor4;
        FloatingActionButtonElevation elevation4;
        Composer $composer3 = $composer.startRestartGroup(1274576261);
        ComposerKt.sourceInformation($composer3, "C(LargeFloatingActionButton)N(onClick,modifier,shape,containerColor:c#ui.graphics.Color,contentColor:c#ui.graphics.Color,elevation,interactionSource,content)263@12410L441:FloatingActionButton.kt#uh7d8r");
        int $dirty3 = $changed;
        if ((i & 1) != 0) {
            $dirty3 |= 6;
            function02 = function0;
        } else if (($changed & 6) == 0) {
            function02 = function0;
            $dirty3 |= $composer3.changedInstance(function02) ? 4 : 2;
        } else {
            function02 = function0;
        }
        int i3 = i & 2;
        if (i3 != 0) {
            $dirty3 |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty3 |= $composer3.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                shape2 = shape;
                int i4 = $composer3.changed(shape2) ? 256 : 128;
                $dirty3 |= i4;
            } else {
                shape2 = shape;
            }
            $dirty3 |= i4;
        } else {
            shape2 = shape;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                j = containerColor;
                int i5 = $composer3.changed(j) ? 2048 : 1024;
                $dirty3 |= i5;
            } else {
                j = containerColor;
            }
            $dirty3 |= i5;
        } else {
            j = containerColor;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                contentColor2 = contentColor;
                int i6 = $composer3.changed(contentColor2) ? 16384 : 8192;
                $dirty3 |= i6;
            } else {
                contentColor2 = contentColor;
            }
            $dirty3 |= i6;
        } else {
            contentColor2 = contentColor;
        }
        if ((196608 & $changed) == 0) {
            if ((i & 32) == 0) {
                elevation2 = elevation;
                int i7 = $composer3.changed(elevation2) ? 131072 : 65536;
                $dirty3 |= i7;
            } else {
                elevation2 = elevation;
            }
            $dirty3 |= i7;
        } else {
            elevation2 = elevation;
        }
        int i8 = i & 64;
        if (i8 != 0) {
            $dirty3 |= 1572864;
            mutableInteractionSource = interactionSource;
        } else if ((1572864 & $changed) == 0) {
            mutableInteractionSource = interactionSource;
            $dirty3 |= $composer3.changed(mutableInteractionSource) ? 1048576 : 524288;
        } else {
            mutableInteractionSource = interactionSource;
        }
        if ((i & 128) != 0) {
            $dirty3 |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty3 |= $composer3.changedInstance(function2) ? 8388608 : 4194304;
        }
        if (!$composer3.shouldExecute(($dirty3 & 4793491) != 4793490, $dirty3 & 1)) {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            shape3 = shape2;
            containerColor2 = j;
            contentColor3 = contentColor2;
            elevation3 = elevation2;
            interactionSource2 = interactionSource;
        } else {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "256@12075L10,257@12144L14,258@12186L31,259@12295L11");
            if (($changed & 1) != 0 && !$composer3.getDefaultsInvalid()) {
                $composer3.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty3 &= -897;
                }
                if ((i & 8) != 0) {
                    $dirty3 &= -7169;
                }
                if ((i & 16) != 0) {
                    $dirty3 &= -57345;
                }
                if ((i & 32) != 0) {
                    $dirty3 &= -458753;
                    shape5 = shape2;
                    interactionSource3 = mutableInteractionSource;
                    containerColor5 = j;
                    contentColor4 = contentColor2;
                    elevation4 = elevation2;
                    i2 = 1274576261;
                } else {
                    shape5 = shape2;
                    interactionSource3 = mutableInteractionSource;
                    containerColor5 = j;
                    contentColor4 = contentColor2;
                    elevation4 = elevation2;
                    i2 = 1274576261;
                }
            } else {
                if (i3 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if ((i & 4) == 0) {
                    shape4 = shape2;
                } else {
                    $dirty3 &= -897;
                    shape4 = FloatingActionButtonDefaults.INSTANCE.getLargeShape($composer3, 6);
                }
                if ((i & 8) == 0) {
                    $dirty = $dirty3;
                    containerColor3 = j;
                } else {
                    $dirty = $dirty3 & (-7169);
                    containerColor3 = FloatingActionButtonDefaults.INSTANCE.getContainerColor($composer3, 6);
                }
                if ((i & 16) == 0) {
                    $dirty2 = $dirty;
                } else {
                    contentColor2 = ColorSchemeKt.m2347contentColorForek8zF_U(containerColor3, $composer3, ($dirty >> 9) & 14);
                    $dirty2 = $dirty & (-57345);
                }
                if ((i & 32) == 0) {
                    containerColor4 = containerColor3;
                } else {
                    containerColor4 = containerColor3;
                    $dirty2 &= -458753;
                    elevation2 = FloatingActionButtonDefaults.INSTANCE.m2550elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, $composer3, 24576, 15);
                }
                if (i8 == 0) {
                    modifier2 = modifier4;
                    shape5 = shape4;
                    $dirty3 = $dirty2;
                    containerColor5 = containerColor4;
                    i2 = 1274576261;
                    interactionSource3 = interactionSource;
                    contentColor4 = contentColor2;
                    elevation4 = elevation2;
                } else {
                    modifier2 = modifier4;
                    shape5 = shape4;
                    containerColor5 = containerColor4;
                    i2 = 1274576261;
                    interactionSource3 = null;
                    elevation4 = elevation2;
                    $dirty3 = $dirty2;
                    contentColor4 = contentColor2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(i2, $dirty3, -1, "androidx.compose.material3.LargeFloatingActionButton (FloatingActionButton.kt:262)");
            }
            $composer2 = $composer3;
            m2559FloatingActionButtonXz6DiA(function02, SizeKt.m1119sizeInqDBjuR0$default(modifier2, FabLargeTokens.INSTANCE.m3839getContainerWidthD9Ej5fM(), FabLargeTokens.INSTANCE.m3838getContainerHeightD9Ej5fM(), 0.0f, 0.0f, 12, null), shape5, containerColor5, contentColor4, elevation4, interactionSource3, function2, $composer2, ($dirty3 & 14) | ($dirty3 & 896) | ($dirty3 & 7168) | (57344 & $dirty3) | (458752 & $dirty3) | (3670016 & $dirty3) | (29360128 & $dirty3), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier2;
            shape3 = shape5;
            containerColor2 = containerColor5;
            contentColor3 = contentColor4;
            elevation3 = elevation4;
            interactionSource2 = interactionSource3;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.FloatingActionButtonKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return FloatingActionButtonKt.LargeFloatingActionButton_X_z6DiA$lambda$6(function0, modifier3, shape3, containerColor2, contentColor3, elevation3, interactionSource2, function2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: ExtendedFloatingActionButton-X-z6DiA, reason: not valid java name */
    public static final void m2557ExtendedFloatingActionButtonXz6DiA(final Function0<Unit> function0, Modifier modifier, Shape shape, long containerColor, long contentColor, FloatingActionButtonElevation elevation, MutableInteractionSource interactionSource, final Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        Function0<Unit> function02;
        Shape shape2;
        long j;
        long contentColor2;
        FloatingActionButtonElevation elevation2;
        MutableInteractionSource mutableInteractionSource;
        Composer $composer2;
        final Modifier modifier2;
        final Shape shape3;
        final long containerColor2;
        final FloatingActionButtonElevation elevation3;
        final long contentColor3;
        final MutableInteractionSource interactionSource2;
        Modifier.Companion modifier3;
        Shape shape4;
        int $dirty;
        long containerColor3;
        int $dirty2;
        Modifier modifier4;
        long containerColor4;
        boolean z;
        FloatingActionButtonElevation elevation4;
        int i2;
        long containerColor5;
        int i3;
        long contentColor4;
        Shape shape5;
        Modifier modifier5;
        MutableInteractionSource interactionSource3;
        Composer $composer3 = $composer.startRestartGroup(1039585610);
        ComposerKt.sourceInformation($composer3, "C(ExtendedFloatingActionButton)N(onClick,modifier,shape,containerColor:c#ui.graphics.Color,contentColor:c#ui.graphics.Color,elevation,interactionSource,content)329@15450L335,321@15187L598:FloatingActionButton.kt#uh7d8r");
        int $dirty3 = $changed;
        if ((i & 1) != 0) {
            $dirty3 |= 6;
            function02 = function0;
        } else if (($changed & 6) == 0) {
            function02 = function0;
            $dirty3 |= $composer3.changedInstance(function02) ? 4 : 2;
        } else {
            function02 = function0;
        }
        int i4 = i & 2;
        if (i4 != 0) {
            $dirty3 |= 48;
        } else if (($changed & 48) == 0) {
            $dirty3 |= $composer3.changed(modifier) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                shape2 = shape;
                int i5 = $composer3.changed(shape2) ? 256 : 128;
                $dirty3 |= i5;
            } else {
                shape2 = shape;
            }
            $dirty3 |= i5;
        } else {
            shape2 = shape;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                j = containerColor;
                int i6 = $composer3.changed(j) ? 2048 : 1024;
                $dirty3 |= i6;
            } else {
                j = containerColor;
            }
            $dirty3 |= i6;
        } else {
            j = containerColor;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                contentColor2 = contentColor;
                int i7 = $composer3.changed(contentColor2) ? 16384 : 8192;
                $dirty3 |= i7;
            } else {
                contentColor2 = contentColor;
            }
            $dirty3 |= i7;
        } else {
            contentColor2 = contentColor;
        }
        if ((196608 & $changed) == 0) {
            if ((i & 32) == 0) {
                elevation2 = elevation;
                int i8 = $composer3.changed(elevation2) ? 131072 : 65536;
                $dirty3 |= i8;
            } else {
                elevation2 = elevation;
            }
            $dirty3 |= i8;
        } else {
            elevation2 = elevation;
        }
        int i9 = i & 64;
        if (i9 != 0) {
            $dirty3 |= 1572864;
            mutableInteractionSource = interactionSource;
        } else if ((1572864 & $changed) == 0) {
            mutableInteractionSource = interactionSource;
            $dirty3 |= $composer3.changed(mutableInteractionSource) ? 1048576 : 524288;
        } else {
            mutableInteractionSource = interactionSource;
        }
        if ((i & 128) != 0) {
            $dirty3 |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty3 |= $composer3.changedInstance(function3) ? 8388608 : 4194304;
        }
        if ($composer3.shouldExecute((4793491 & $dirty3) != 4793490, $dirty3 & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "314@14837L16,315@14912L14,316@14954L31,317@15063L11");
            if (($changed & 1) != 0 && !$composer3.getDefaultsInvalid()) {
                $composer3.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty3 &= -897;
                }
                if ((i & 8) != 0) {
                    $dirty3 &= -7169;
                }
                if ((i & 16) != 0) {
                    $dirty3 &= -57345;
                }
                if ((i & 32) != 0) {
                    $dirty3 &= -458753;
                    interactionSource3 = mutableInteractionSource;
                    containerColor5 = j;
                    elevation4 = elevation2;
                    i2 = 12582912;
                    z = true;
                    i3 = 1039585610;
                    contentColor4 = contentColor2;
                    modifier5 = modifier;
                    shape5 = shape2;
                } else {
                    interactionSource3 = mutableInteractionSource;
                    containerColor5 = j;
                    elevation4 = elevation2;
                    i2 = 12582912;
                    z = true;
                    i3 = 1039585610;
                    contentColor4 = contentColor2;
                    modifier5 = modifier;
                    shape5 = shape2;
                }
            } else {
                if (i4 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier;
                }
                if ((i & 4) == 0) {
                    shape4 = shape2;
                } else {
                    $dirty3 &= -897;
                    shape4 = FloatingActionButtonDefaults.INSTANCE.getExtendedFabShape($composer3, 6);
                }
                if ((i & 8) == 0) {
                    $dirty = $dirty3;
                    containerColor3 = j;
                } else {
                    $dirty = $dirty3 & (-7169);
                    containerColor3 = FloatingActionButtonDefaults.INSTANCE.getContainerColor($composer3, 6);
                }
                if ((i & 16) == 0) {
                    $dirty2 = $dirty;
                } else {
                    contentColor2 = ColorSchemeKt.m2347contentColorForek8zF_U(containerColor3, $composer3, ($dirty >> 9) & 14);
                    $dirty2 = $dirty & (-57345);
                }
                if ((i & 32) == 0) {
                    modifier4 = modifier3;
                    containerColor4 = containerColor3;
                    z = true;
                } else {
                    containerColor4 = containerColor3;
                    modifier4 = modifier3;
                    z = true;
                    $dirty2 &= -458753;
                    elevation2 = FloatingActionButtonDefaults.INSTANCE.m2550elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, $composer3, 24576, 15);
                }
                if (i9 == 0) {
                    elevation4 = elevation2;
                    i2 = 12582912;
                    $dirty3 = $dirty2;
                    containerColor5 = containerColor4;
                    i3 = 1039585610;
                    contentColor4 = contentColor2;
                    shape5 = shape4;
                    modifier5 = modifier4;
                    interactionSource3 = interactionSource;
                } else {
                    elevation4 = elevation2;
                    i2 = 12582912;
                    containerColor5 = containerColor4;
                    i3 = 1039585610;
                    contentColor4 = contentColor2;
                    shape5 = shape4;
                    modifier5 = modifier4;
                    interactionSource3 = null;
                    $dirty3 = $dirty2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(i3, $dirty3, -1, "androidx.compose.material3.ExtendedFloatingActionButton (FloatingActionButton.kt:320)");
            }
            $composer2 = $composer3;
            m2559FloatingActionButtonXz6DiA(function02, modifier5, shape5, containerColor5, contentColor4, elevation4, interactionSource3, ComposableLambdaKt.rememberComposableLambda(-1233936436, z, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$ExtendedFloatingActionButton$1
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                /* JADX WARN: Removed duplicated region for block: B:28:0x0162  */
                /* JADX WARN: Removed duplicated region for block: B:31:? A[RETURN, SYNTHETIC] */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct code enable 'Show inconsistent code' option in preferences
                */
                public final void invoke(androidx.compose.runtime.Composer r26, int r27) {
                    /*
                        Method dump skipped, instruction units count: 364
                        To view this dump change 'Code comments level' option to 'DEBUG'
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.FloatingActionButtonKt$ExtendedFloatingActionButton$1.invoke(androidx.compose.runtime.Composer, int):void");
                }
            }, $composer3, 54), $composer2, ($dirty3 & 14) | i2 | ($dirty3 & 112) | ($dirty3 & 896) | ($dirty3 & 7168) | (57344 & $dirty3) | (458752 & $dirty3) | (3670016 & $dirty3), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier2 = modifier5;
            shape3 = shape5;
            containerColor2 = containerColor5;
            contentColor3 = contentColor4;
            elevation3 = elevation4;
            interactionSource2 = interactionSource3;
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            modifier2 = modifier;
            shape3 = shape2;
            containerColor2 = j;
            elevation3 = elevation2;
            contentColor3 = contentColor2;
            interactionSource2 = interactionSource;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.FloatingActionButtonKt$$ExternalSyntheticLambda6
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return FloatingActionButtonKt.ExtendedFloatingActionButton_X_z6DiA$lambda$7(function0, modifier2, shape3, containerColor2, contentColor3, elevation3, interactionSource2, function3, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: ExtendedFloatingActionButton-ElI5-7k, reason: not valid java name */
    public static final void m2556ExtendedFloatingActionButtonElI57k(Function2<? super Composer, ? super Integer, Unit> function2, final Function2<? super Composer, ? super Integer, Unit> function22, final Function0<Unit> function0, Modifier modifier, boolean expanded, Shape shape, long containerColor, long contentColor, FloatingActionButtonElevation elevation, MutableInteractionSource interactionSource, Composer $composer, final int $changed, final int i) {
        Function0<Unit> function02;
        Modifier modifier2;
        final boolean expanded2;
        Shape shape2;
        long j;
        int i2;
        FloatingActionButtonElevation floatingActionButtonElevation;
        int i3;
        final Function2<? super Composer, ? super Integer, Unit> function23;
        Composer $composer2;
        final Shape shape3;
        long containerColor2;
        final FloatingActionButtonElevation elevation2;
        final long contentColor2;
        final MutableInteractionSource interactionSource2;
        int $dirty;
        Modifier.Companion modifier3;
        boolean expanded3;
        Shape shape4;
        long containerColor3;
        long contentColor3;
        int $dirty2;
        boolean z;
        long containerColor4;
        FloatingActionButtonElevation elevation3;
        Shape shape5;
        long contentColor4;
        MutableInteractionSource interactionSource3;
        FloatingActionButtonElevation elevation4;
        int i4;
        Composer $composer3 = $composer.startRestartGroup(-1161000600);
        ComposerKt.sourceInformation($composer3, "C(ExtendedFloatingActionButton)N(text,icon,onClick,modifier,expanded,shape,containerColor:c#ui.graphics.Color,contentColor:c#ui.graphics.Color,elevation,interactionSource)400@18910L1159,392@18647L1422:FloatingActionButton.kt#uh7d8r");
        int $dirty3 = $changed;
        if ((i & 1) != 0) {
            $dirty3 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty3 |= $composer3.changedInstance(function2) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty3 |= 48;
        } else if (($changed & 48) == 0) {
            $dirty3 |= $composer3.changedInstance(function22) ? 32 : 16;
        }
        if ((i & 4) != 0) {
            $dirty3 |= 384;
            function02 = function0;
        } else if (($changed & 384) == 0) {
            function02 = function0;
            $dirty3 |= $composer3.changedInstance(function02) ? 256 : 128;
        } else {
            function02 = function0;
        }
        int i5 = i & 8;
        if (i5 != 0) {
            $dirty3 |= 3072;
            modifier2 = modifier;
        } else if (($changed & 3072) == 0) {
            modifier2 = modifier;
            $dirty3 |= $composer3.changed(modifier2) ? 2048 : 1024;
        } else {
            modifier2 = modifier;
        }
        int i6 = i & 16;
        if (i6 != 0) {
            $dirty3 |= 24576;
            expanded2 = expanded;
        } else if (($changed & 24576) == 0) {
            expanded2 = expanded;
            $dirty3 |= $composer3.changed(expanded2) ? 16384 : 8192;
        } else {
            expanded2 = expanded;
        }
        if ((196608 & $changed) == 0) {
            if ((i & 32) == 0) {
                shape2 = shape;
                int i7 = $composer3.changed(shape2) ? 131072 : 65536;
                $dirty3 |= i7;
            } else {
                shape2 = shape;
            }
            $dirty3 |= i7;
        } else {
            shape2 = shape;
        }
        if ((1572864 & $changed) == 0) {
            if ((i & 64) == 0) {
                j = containerColor;
                int i8 = $composer3.changed(j) ? 1048576 : 524288;
                $dirty3 |= i8;
            } else {
                j = containerColor;
            }
            $dirty3 |= i8;
        } else {
            j = containerColor;
        }
        if (($changed & 12582912) == 0) {
            i2 = 12582912;
            $dirty3 |= ((i & 128) == 0 && $composer3.changed(contentColor)) ? 8388608 : 4194304;
        } else {
            i2 = 12582912;
        }
        if (($changed & 100663296) == 0) {
            if ((i & 256) == 0) {
                floatingActionButtonElevation = elevation;
                if ($composer3.changed(floatingActionButtonElevation)) {
                    i4 = 67108864;
                }
                $dirty3 |= i4;
            } else {
                floatingActionButtonElevation = elevation;
            }
            i4 = GroupFlagsKt.HasAuxSlotFlag;
            $dirty3 |= i4;
        } else {
            floatingActionButtonElevation = elevation;
        }
        int i9 = i & 512;
        if (i9 != 0) {
            $dirty3 |= 805306368;
            i3 = i9;
        } else if (($changed & 805306368) == 0) {
            i3 = i9;
            $dirty3 |= $composer3.changed(interactionSource) ? GroupFlagsKt.HasMovableContentFlag : GroupFlagsKt.IsMovableContentFlag;
        } else {
            i3 = i9;
        }
        if ($composer3.shouldExecute(($dirty3 & 306783379) != 306783378, $dirty3 & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "386@18343L16,387@18418L14,388@18460L31,389@18569L11");
            if (($changed & 1) != 0 && !$composer3.getDefaultsInvalid()) {
                $composer3.skipToGroupEnd();
                if ((i & 32) != 0) {
                    $dirty3 &= -458753;
                }
                if ((i & 64) != 0) {
                    $dirty3 &= -3670017;
                }
                if ((i & 128) != 0) {
                    $dirty3 &= -29360129;
                }
                if ((i & 256) != 0) {
                    contentColor4 = contentColor;
                    interactionSource3 = interactionSource;
                    $dirty3 &= -234881025;
                    z = true;
                    shape5 = shape2;
                    containerColor2 = j;
                    elevation4 = floatingActionButtonElevation;
                } else {
                    contentColor4 = contentColor;
                    interactionSource3 = interactionSource;
                    z = true;
                    shape5 = shape2;
                    containerColor2 = j;
                    elevation4 = floatingActionButtonElevation;
                }
            } else {
                if (i5 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier2;
                }
                if (i6 == 0) {
                    expanded3 = expanded2;
                } else {
                    expanded3 = true;
                }
                if ((i & 32) == 0) {
                    shape4 = shape2;
                } else {
                    $dirty3 &= -458753;
                    shape4 = FloatingActionButtonDefaults.INSTANCE.getExtendedFabShape($composer3, 6);
                }
                if ((i & 64) == 0) {
                    containerColor3 = j;
                } else {
                    containerColor3 = FloatingActionButtonDefaults.INSTANCE.getContainerColor($composer3, 6);
                    $dirty3 &= -3670017;
                }
                if ((i & 128) == 0) {
                    contentColor3 = contentColor;
                    $dirty2 = $dirty3;
                } else {
                    contentColor3 = ColorSchemeKt.m2347contentColorForek8zF_U(containerColor3, $composer3, ($dirty3 >> 18) & 14);
                    $dirty2 = $dirty3 & (-29360129);
                }
                if ((i & 256) == 0) {
                    z = true;
                    containerColor4 = containerColor3;
                    elevation3 = floatingActionButtonElevation;
                } else {
                    containerColor4 = containerColor3;
                    z = true;
                    elevation3 = FloatingActionButtonDefaults.INSTANCE.m2550elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, $composer3, 24576, 15);
                    $dirty2 &= -234881025;
                }
                if (i3 == 0) {
                    modifier2 = modifier3;
                    expanded2 = expanded3;
                    shape5 = shape4;
                    contentColor4 = contentColor3;
                    containerColor2 = containerColor4;
                    interactionSource3 = interactionSource;
                    elevation4 = elevation3;
                    $dirty3 = $dirty2;
                } else {
                    modifier2 = modifier3;
                    expanded2 = expanded3;
                    shape5 = shape4;
                    contentColor4 = contentColor3;
                    containerColor2 = containerColor4;
                    elevation4 = elevation3;
                    interactionSource3 = null;
                    $dirty3 = $dirty2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1161000600, $dirty3, -1, "androidx.compose.material3.ExtendedFloatingActionButton (FloatingActionButton.kt:391)");
            }
            function23 = function2;
            Modifier modifier4 = modifier2;
            $composer2 = $composer3;
            m2559FloatingActionButtonXz6DiA(function02, modifier4, shape5, containerColor2, contentColor4, elevation4, interactionSource3, ComposableLambdaKt.rememberComposableLambda(632971498, z, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.FloatingActionButtonKt$ExtendedFloatingActionButton$3
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer $composer4, int $changed2) {
                    float fM8150constructorimpl;
                    float endPadding;
                    float fM3836getContainerWidthD9Ej5fM;
                    ComposerKt.sourceInformation($composer4, "C404@19072L991:FloatingActionButton.kt#uh7d8r");
                    if (!$composer4.shouldExecute(($changed2 & 3) != 2, $changed2 & 1)) {
                        $composer4.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(632971498, $changed2, -1, "androidx.compose.material3.ExtendedFloatingActionButton.<anonymous> (FloatingActionButton.kt:401)");
                    }
                    if (expanded2) {
                        fM8150constructorimpl = FloatingActionButtonKt.ExtendedFabStartIconPadding;
                    } else {
                        fM8150constructorimpl = Dp.m8150constructorimpl(0);
                    }
                    float startPadding = fM8150constructorimpl;
                    if (expanded2) {
                        endPadding = FloatingActionButtonKt.ExtendedFabTextPadding;
                    } else {
                        endPadding = Dp.m8150constructorimpl(0);
                    }
                    Modifier.Companion companion = Modifier.INSTANCE;
                    if (expanded2) {
                        fM3836getContainerWidthD9Ej5fM = FloatingActionButtonKt.ExtendedFabMinimumWidth;
                    } else {
                        fM3836getContainerWidthD9Ej5fM = FabBaselineTokens.INSTANCE.m3836getContainerWidthD9Ej5fM();
                    }
                    Modifier modifier$iv$iv = PaddingKt.m1052paddingqDBjuR0$default(SizeKt.m1119sizeInqDBjuR0$default(companion, fM3836getContainerWidthD9Ej5fM, 0.0f, 0.0f, 0.0f, 14, null), startPadding, 0.0f, endPadding, 0.0f, 10, null);
                    Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
                    Arrangement.HorizontalOrVertical start = expanded2 ? Arrangement.INSTANCE.getStart() : Arrangement.INSTANCE.getCenter();
                    Function2<Composer, Integer, Unit> function24 = function22;
                    boolean z2 = expanded2;
                    Function2<Composer, Integer, Unit> function25 = function23;
                    ComposerKt.sourceInformationMarkerStart($composer4, 693286680, "CC(Row)P(2,1,3)99@5124L58,100@5187L130:Row.kt#2w3rfo");
                    MeasurePolicy measurePolicy$iv = RowKt.rowMeasurePolicy(start, centerVertically, $composer4, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                    int $changed$iv$iv = (384 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer4, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                    int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer4, 0);
                    CompositionLocalMap localMap$iv$iv = $composer4.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer4, modifier$iv$iv);
                    Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                    int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer4, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                    if (!($composer4.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer4.startReusableNode();
                    if ($composer4.getInserting()) {
                        $composer4.createNode(constructor);
                    } else {
                        $composer4.useNode();
                    }
                    Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4433constructorimpl($composer4);
                    Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                        $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                        $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
                    }
                    Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
                    int i10 = ($changed$iv$iv$iv >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer4, -407735110, "C101@5232L9:Row.kt#2w3rfo");
                    RowScope $this$invoke_u24lambda_u240 = RowScopeInstance.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart($composer4, -145916491, "C418@19669L6,421@19768L28,422@19821L30,423@19867L186,419@19688L365:FloatingActionButton.kt#uh7d8r");
                    function24.invoke($composer4, 0);
                    AnimatedVisibilityKt.AnimatedVisibility($this$invoke_u24lambda_u240, z2, (Modifier) null, FloatingActionButtonKt.extendedFabExpandAnimation($composer4, 0), FloatingActionButtonKt.extendedFabCollapseAnimation($composer4, 0), (String) null, ComposableLambdaKt.rememberComposableLambda(-660008666, true, new FloatingActionButtonKt$ExtendedFloatingActionButton$3$1$1(function25), $composer4, 54), $composer4, ((((384 >> 6) & 112) | 6) & 14) | 1572864, 18);
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    $composer4.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, $composer3, 54), $composer2, (($dirty3 >> 6) & 14) | i2 | (($dirty3 >> 6) & 112) | (($dirty3 >> 9) & 896) | (($dirty3 >> 9) & 7168) | (($dirty3 >> 9) & 57344) | (($dirty3 >> 9) & 458752) | (3670016 & ($dirty3 >> 9)), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier2 = modifier4;
            shape3 = shape5;
            contentColor2 = contentColor4;
            elevation2 = elevation4;
            interactionSource2 = interactionSource3;
            $dirty = $dirty3;
        } else {
            function23 = function2;
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            shape3 = shape2;
            containerColor2 = j;
            elevation2 = floatingActionButtonElevation;
            contentColor2 = contentColor;
            interactionSource2 = interactionSource;
            $dirty = $dirty3;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Function2<? super Composer, ? super Integer, Unit> function24 = function23;
            final Modifier modifier5 = modifier2;
            final boolean expanded4 = expanded2;
            final long containerColor5 = containerColor2;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.FloatingActionButtonKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return FloatingActionButtonKt.ExtendedFloatingActionButton_ElI5_7k$lambda$8(function24, function22, function0, modifier5, expanded4, shape3, containerColor5, contentColor2, elevation2, interactionSource2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: ExtendedFloatingActionButton-qtIzBjc, reason: not valid java name */
    private static final void m2558ExtendedFloatingActionButtonqtIzBjc(final Function2<? super Composer, ? super Integer, Unit> function2, final Function2<? super Composer, ? super Integer, Unit> function22, final Function0<Unit> function0, final TextStyle textStyle, final float minWidth, final float minHeight, final float startPadding, final float endPadding, final float iconPadding, Modifier modifier, boolean expanded, Shape shape, long containerColor, long contentColor, FloatingActionButtonElevation elevation, MutableInteractionSource interactionSource, Composer $composer, final int $changed, final int $changed1, final int i) {
        int i2;
        int i3;
        int $dirty;
        long j;
        int $dirty1;
        Composer $composer2;
        final Modifier modifier2;
        final boolean expanded2;
        final Shape shape2;
        final long containerColor2;
        final long contentColor2;
        final FloatingActionButtonElevation elevation2;
        final MutableInteractionSource interactionSource2;
        Modifier.Companion modifier3;
        boolean expanded3;
        int $dirty12;
        Shape shape3;
        boolean expanded4;
        long containerColor3;
        long contentColor3;
        int $dirty13;
        long containerColor4;
        boolean z;
        int $dirty14;
        FloatingActionButtonElevation elevation3;
        Modifier modifier4;
        MutableInteractionSource interactionSource3;
        FloatingActionButtonElevation elevation4;
        long contentColor4;
        long containerColor5;
        Composer $composer3 = $composer.startRestartGroup(193103278);
        ComposerKt.sourceInformation($composer3, "C(ExtendedFloatingActionButton)N(text,icon,onClick,textStyle,minWidth:c#ui.unit.Dp,minHeight:c#ui.unit.Dp,startPadding:c#ui.unit.Dp,endPadding:c#ui.unit.Dp,iconPadding:c#ui.unit.Dp,modifier,expanded,shape,containerColor:c#ui.graphics.Color,contentColor:c#ui.graphics.Color,elevation,interactionSource)463@21128L1923,452@20763L2288:FloatingActionButton.kt#uh7d8r");
        int $dirty2 = $changed;
        int $dirty15 = $changed1;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty2 |= $composer3.changedInstance(function2) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty2 |= 48;
        } else if (($changed & 48) == 0) {
            $dirty2 |= $composer3.changedInstance(function22) ? 32 : 16;
        }
        if ((i & 4) != 0) {
            $dirty2 |= 384;
        } else if (($changed & 384) == 0) {
            $dirty2 |= $composer3.changedInstance(function0) ? 256 : 128;
        }
        int i4 = 1024;
        if ((i & 8) != 0) {
            $dirty2 |= 3072;
        } else if (($changed & 3072) == 0) {
            $dirty2 |= $composer3.changed(textStyle) ? 2048 : 1024;
        }
        if ((i & 16) != 0) {
            $dirty2 |= 24576;
        } else if (($changed & 24576) == 0) {
            $dirty2 |= $composer3.changed(minWidth) ? 16384 : 8192;
        }
        if ((i & 32) != 0) {
            $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty2 |= $composer3.changed(minHeight) ? 131072 : 65536;
        }
        if ((i & 64) != 0) {
            $dirty2 |= 1572864;
        } else if (($changed & 1572864) == 0) {
            $dirty2 |= $composer3.changed(startPadding) ? 1048576 : 524288;
        }
        if ((i & 128) != 0) {
            $dirty2 |= 12582912;
        } else if ((12582912 & $changed) == 0) {
            $dirty2 |= $composer3.changed(endPadding) ? 8388608 : 4194304;
        }
        if ((i & 256) != 0) {
            $dirty2 |= 100663296;
        } else if ((100663296 & $changed) == 0) {
            $dirty2 |= $composer3.changed(iconPadding) ? 67108864 : GroupFlagsKt.HasAuxSlotFlag;
        }
        int i5 = i & 512;
        if (i5 != 0) {
            $dirty2 |= 805306368;
        } else if (($changed & 805306368) == 0) {
            $dirty2 |= $composer3.changed(modifier) ? GroupFlagsKt.HasMovableContentFlag : GroupFlagsKt.IsMovableContentFlag;
        }
        int i6 = i & 1024;
        if (i6 != 0) {
            $dirty15 |= 6;
            i2 = i6;
        } else if (($changed1 & 6) == 0) {
            i2 = i6;
            $dirty15 |= $composer3.changed(expanded) ? 4 : 2;
        } else {
            i2 = i6;
        }
        if (($changed1 & 48) == 0) {
            $dirty15 |= ((i & 2048) == 0 && $composer3.changed(shape)) ? 32 : 16;
        }
        if (($changed1 & 384) == 0) {
            if ((i & 4096) == 0) {
                i3 = i5;
                $dirty = $dirty2;
                int i7 = $composer3.changed(containerColor) ? 256 : 128;
                $dirty15 |= i7;
            } else {
                i3 = i5;
                $dirty = $dirty2;
            }
            $dirty15 |= i7;
        } else {
            i3 = i5;
            $dirty = $dirty2;
        }
        int i8 = i3;
        if (($changed1 & 3072) == 0) {
            if ((i & 8192) == 0) {
                j = contentColor;
                if ($composer3.changed(j)) {
                    i4 = 2048;
                }
            } else {
                j = contentColor;
            }
            $dirty15 |= i4;
        } else {
            j = contentColor;
        }
        if (($changed1 & 24576) == 0) {
            $dirty15 |= ((i & 16384) == 0 && $composer3.changed(elevation)) ? 16384 : 8192;
        }
        int i9 = i & 32768;
        if (i9 != 0) {
            $dirty1 = $dirty15 | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if (($changed1 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty1 = $dirty15 | ($composer3.changed(interactionSource) ? 131072 : 65536);
        } else {
            $dirty1 = $dirty15;
        }
        if ($composer3.shouldExecute((($dirty & 306783379) == 306783378 && ($dirty1 & 74899) == 74898) ? false : true, $dirty & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "446@20459L16,447@20534L14,448@20576L31,449@20685L11");
            if (($changed & 1) != 0 && !$composer3.getDefaultsInvalid()) {
                $composer3.skipToGroupEnd();
                if ((i & 2048) != 0) {
                    $dirty1 &= -113;
                }
                int $dirty16 = $dirty1;
                if ((i & 4096) != 0) {
                    $dirty16 &= -897;
                }
                if ((i & 8192) != 0) {
                    $dirty16 &= -7169;
                }
                if ((i & 16384) != 0) {
                    $dirty16 &= -57345;
                }
                modifier4 = modifier;
                expanded4 = expanded;
                shape3 = shape;
                containerColor5 = containerColor;
                elevation4 = elevation;
                interactionSource3 = interactionSource;
                contentColor4 = j;
                z = true;
                $dirty13 = $dirty16;
                $dirty14 = $dirty;
            } else {
                if (i8 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier;
                }
                if (i2 == 0) {
                    expanded3 = expanded;
                } else {
                    expanded3 = true;
                }
                Modifier modifier5 = modifier3;
                if ((i & 2048) == 0) {
                    $dirty12 = $dirty1;
                    shape3 = shape;
                } else {
                    int $dirty17 = $dirty1 & (-113);
                    shape3 = FloatingActionButtonDefaults.INSTANCE.getExtendedFabShape($composer3, 6);
                    $dirty12 = $dirty17;
                }
                if ((i & 4096) == 0) {
                    expanded4 = expanded3;
                    containerColor3 = containerColor;
                } else {
                    expanded4 = expanded3;
                    containerColor3 = FloatingActionButtonDefaults.INSTANCE.getContainerColor($composer3, 6);
                    $dirty12 &= -897;
                }
                if ((i & 8192) == 0) {
                    contentColor3 = contentColor;
                    $dirty13 = $dirty12;
                } else {
                    contentColor3 = ColorSchemeKt.m2347contentColorForek8zF_U(containerColor3, $composer3, ($dirty12 >> 6) & 14);
                    $dirty13 = $dirty12 & (-7169);
                }
                if ((i & 16384) == 0) {
                    containerColor4 = containerColor3;
                    z = true;
                    $dirty14 = $dirty;
                    elevation3 = elevation;
                } else {
                    containerColor4 = containerColor3;
                    z = true;
                    $dirty14 = $dirty;
                    elevation3 = FloatingActionButtonDefaults.INSTANCE.m2550elevationxZ9QkE(0.0f, 0.0f, 0.0f, 0.0f, $composer3, 24576, 15);
                    $dirty13 &= -57345;
                }
                if (i9 == 0) {
                    modifier4 = modifier5;
                    interactionSource3 = interactionSource;
                    elevation4 = elevation3;
                    contentColor4 = contentColor3;
                    containerColor5 = containerColor4;
                } else {
                    modifier4 = modifier5;
                    elevation4 = elevation3;
                    interactionSource3 = null;
                    contentColor4 = contentColor3;
                    containerColor5 = containerColor4;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(193103278, $dirty14, $dirty13, "androidx.compose.material3.ExtendedFloatingActionButton (FloatingActionButton.kt:451)");
            }
            boolean expanded5 = expanded4;
            $dirty = $dirty14;
            Shape shape4 = shape3;
            m2560FloatingActionButtonlFWlFE(function0, textStyle, Dp.INSTANCE.m8170getUnspecifiedD9Ej5fM(), Dp.INSTANCE.m8170getUnspecifiedD9Ej5fM(), modifier4, shape4, containerColor5, contentColor4, elevation4, interactionSource3, ComposableLambdaKt.rememberComposableLambda(-827388388, z, new FloatingActionButtonKt$ExtendedFloatingActionButton$5(expanded5, minWidth, minHeight, startPadding, endPadding, function22, iconPadding, function2), $composer3, 54), $composer3, (($dirty >> 6) & 14) | 3456 | (($dirty >> 6) & 112) | (($dirty >> 15) & 57344) | (($dirty13 << 12) & 458752) | (($dirty13 << 12) & 3670016) | (($dirty13 << 12) & 29360128) | (($dirty13 << 12) & 234881024) | (1879048192 & ($dirty13 << 12)), 6, 0);
            $composer2 = $composer3;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            elevation2 = elevation4;
            interactionSource2 = interactionSource3;
            contentColor2 = contentColor4;
            shape2 = shape4;
            containerColor2 = containerColor5;
            expanded2 = expanded5;
            modifier2 = modifier4;
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            modifier2 = modifier;
            expanded2 = expanded;
            shape2 = shape;
            containerColor2 = containerColor;
            contentColor2 = contentColor;
            elevation2 = elevation;
            interactionSource2 = interactionSource;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.FloatingActionButtonKt$$ExternalSyntheticLambda7
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return FloatingActionButtonKt.ExtendedFloatingActionButton_qtIzBjc$lambda$9(function2, function22, function0, textStyle, minWidth, minHeight, startPadding, endPadding, iconPadding, modifier2, expanded2, shape2, containerColor2, contentColor2, elevation2, interactionSource2, $changed, $changed1, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ExitTransition extendedFabCollapseAnimation(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, -56172201, "C(extendedFabCollapseAnimation)797@35053L7,800@35162L7:FloatingActionButton.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-56172201, $changed, -1, "androidx.compose.material3.extendedFabCollapseAnimation (FloatingActionButton.kt:795)");
        }
        ExitTransition exitTransitionPlus = EnterExitTransitionKt.fadeOut$default(MotionSchemeKt.value(MotionSchemeKeyTokens.FastEffects, $composer, 6), 0.0f, 2, null).plus(EnterExitTransitionKt.shrinkHorizontally$default(MotionSchemeKt.value(MotionSchemeKeyTokens.DefaultSpatial, $composer, 6), Alignment.INSTANCE.getStart(), false, null, 12, null));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return exitTransitionPlus;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final EnterTransition extendedFabExpandAnimation(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, -719787506, "C(extendedFabExpandAnimation)808@35431L7,811@35537L7:FloatingActionButton.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-719787506, $changed, -1, "androidx.compose.material3.extendedFabExpandAnimation (FloatingActionButton.kt:806)");
        }
        EnterTransition enterTransitionPlus = EnterExitTransitionKt.fadeIn$default(MotionSchemeKt.value(MotionSchemeKeyTokens.DefaultEffects, $composer, 6), 0.0f, 2, null).plus(EnterExitTransitionKt.expandHorizontally$default(MotionSchemeKt.value(MotionSchemeKeyTokens.FastSpatial, $composer, 6), Alignment.INSTANCE.getStart(), false, null, 12, null));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return enterTransitionPlus;
    }
}
