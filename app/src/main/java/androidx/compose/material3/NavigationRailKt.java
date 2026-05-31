package androidx.compose.material3;

import androidx.compose.animation.SingleValueAnimationKt;
import androidx.compose.animation.core.AnimateAsStateKt;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.foundation.IndicationKt;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.WindowInsets;
import androidx.compose.foundation.selection.SelectableKt;
import androidx.compose.material3.internal.MappedInteractionSource;
import androidx.compose.material3.internal.ProvideContentColorTextStyleKt;
import androidx.compose.material3.tokens.MotionSchemeKeyTokens;
import androidx.compose.material3.tokens.NavigationRailBaselineItemTokens;
import androidx.compose.material3.tokens.NavigationRailCollapsedTokens;
import androidx.compose.material3.tokens.NavigationRailVerticalItemTokens;
import androidx.compose.material3.tokens.ShapeKeyTokens;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.composer.linkbuffer.GroupFlagsKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.layout.LayoutIdKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.profileinstaller.ProfileVerifier;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: NavigationRail.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\u008a\u0001\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u001aw\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052 \b\u0002\u0010\u0007\u001a\u001a\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u0001\u0018\u00010\b¢\u0006\u0002\b\n¢\u0006\u0002\b\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\u001c\u0010\u000e\u001a\u0018\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00010\b¢\u0006\u0002\b\n¢\u0006\u0002\b\u000bH\u0007¢\u0006\u0004\b\u000f\u0010\u0010\u001a\u0081\u0001\u0010\u0011\u001a\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u00132\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00010\u00152\u0011\u0010\u0016\u001a\r\u0012\u0004\u0012\u00020\u00010\u0015¢\u0006\u0002\b\n2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0017\u001a\u00020\u00132\u0015\b\u0002\u0010\u0018\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0015¢\u0006\u0002\b\n2\b\b\u0002\u0010\u0019\u001a\u00020\u00132\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0007¢\u0006\u0002\u0010\u001e\u001a\u007f\u0010\u001f\u001a\u00020\u00012\u0011\u0010 \u001a\r\u0012\u0004\u0012\u00020\u00010\u0015¢\u0006\u0002\b\n2\u0011\u0010!\u001a\r\u0012\u0004\u0012\u00020\u00010\u0015¢\u0006\u0002\b\n2\u0011\u0010\u0016\u001a\r\u0012\u0004\u0012\u00020\u00010\u0015¢\u0006\u0002\b\n2\u0013\u0010\u0018\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0015¢\u0006\u0002\b\n2\u0006\u0010\u0019\u001a\u00020\u00132\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020#0\u00152\f\u0010$\u001a\b\u0012\u0004\u0012\u00020#0\u0015H\u0003¢\u0006\u0002\u0010%\u001a5\u0010&\u001a\u00020'*\u00020(2\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020*2\b\u0010,\u001a\u0004\u0018\u00010*2\u0006\u0010-\u001a\u00020.H\u0002¢\u0006\u0004\b/\u00100\u001aM\u00101\u001a\u00020'*\u00020(2\u0006\u00102\u001a\u00020*2\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020*2\b\u0010,\u001a\u0004\u0018\u00010*2\u0006\u0010-\u001a\u00020.2\u0006\u0010\u0019\u001a\u00020\u00132\u0006\u00103\u001a\u00020#H\u0002¢\u0006\u0004\b4\u00105\"\u000e\u00106\u001a\u000207X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u00108\u001a\u000207X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u00109\u001a\u000207X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010:\u001a\u000207X\u0082T¢\u0006\u0002\n\u0000\"\u0016\u0010;\u001a\u00020<X\u0080\u0004¢\u0006\n\n\u0002\u0010?\u001a\u0004\b=\u0010>\"\u0010\u0010@\u001a\u00020<X\u0082\u0004¢\u0006\u0004\n\u0002\u0010?\"\u0016\u0010A\u001a\u00020<X\u0080\u0004¢\u0006\n\n\u0002\u0010?\u001a\u0004\bB\u0010>\"\u0016\u0010C\u001a\u00020<X\u0080\u0004¢\u0006\n\n\u0002\u0010?\u001a\u0004\bD\u0010>\"\u0016\u0010E\u001a\u00020<X\u0080\u0004¢\u0006\n\n\u0002\u0010?\u001a\u0004\bF\u0010>\"\u0010\u0010G\u001a\u00020<X\u0082\u0004¢\u0006\u0004\n\u0002\u0010?\"\u0010\u0010H\u001a\u00020<X\u0082\u0004¢\u0006\u0004\n\u0002\u0010?\"\u0010\u0010I\u001a\u00020<X\u0082\u0004¢\u0006\u0004\n\u0002\u0010?\"\u001a\u0010J\u001a\b\u0012\u0004\u0012\u00020L0KX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\bM\u0010N¨\u0006O²\u0006\n\u0010P\u001a\u00020\u0005X\u008a\u0084\u0002²\u0006\n\u0010Q\u001a\u00020\u0005X\u008a\u0084\u0002"}, d2 = {"NavigationRail", "", "modifier", "Landroidx/compose/ui/Modifier;", "containerColor", "Landroidx/compose/ui/graphics/Color;", "contentColor", "header", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/ColumnScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "windowInsets", "Landroidx/compose/foundation/layout/WindowInsets;", "content", "NavigationRail-qi6gXK8", "(Landroidx/compose/ui/Modifier;JJLkotlin/jvm/functions/Function3;Landroidx/compose/foundation/layout/WindowInsets;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "NavigationRailItem", "selected", "", "onClick", "Lkotlin/Function0;", NavigationRailKt.IconLayoutIdTag, "enabled", NavigationRailKt.LabelLayoutIdTag, "alwaysShowLabel", "colors", "Landroidx/compose/material3/NavigationRailItemColors;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "(ZLkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/Modifier;ZLkotlin/jvm/functions/Function2;ZLandroidx/compose/material3/NavigationRailItemColors;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;II)V", "NavigationRailItemLayout", NavigationRailKt.IndicatorRippleLayoutIdTag, NavigationRailKt.IndicatorLayoutIdTag, "alphaAnimationProgress", "", "sizeAnimationProgress", "(Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;ZLkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "placeIcon", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "iconPlaceable", "Landroidx/compose/ui/layout/Placeable;", "indicatorRipplePlaceable", "indicatorPlaceable", "constraints", "Landroidx/compose/ui/unit/Constraints;", "placeIcon-X9ElhV4", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;J)Landroidx/compose/ui/layout/MeasureResult;", "placeLabelAndIcon", "labelPlaceable", "animationProgress", "placeLabelAndIcon-zUg2_y0", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;JZF)Landroidx/compose/ui/layout/MeasureResult;", "IndicatorRippleLayoutIdTag", "", "IndicatorLayoutIdTag", "IconLayoutIdTag", "LabelLayoutIdTag", "NavigationRailVerticalPadding", "Landroidx/compose/ui/unit/Dp;", "getNavigationRailVerticalPadding", "()F", "F", "NavigationRailHeaderPadding", "NavigationRailItemWidth", "getNavigationRailItemWidth", "NavigationRailItemHeight", "getNavigationRailItemHeight", "NavigationRailItemVerticalPadding", "getNavigationRailItemVerticalPadding", "IndicatorHorizontalPadding", "IndicatorVerticalPaddingWithLabel", "IndicatorVerticalPaddingNoLabel", "LocalNavigationRailOverride", "Landroidx/compose/runtime/ProvidableCompositionLocal;", "Landroidx/compose/material3/NavigationRailOverride;", "getLocalNavigationRailOverride", "()Landroidx/compose/runtime/ProvidableCompositionLocal;", "material3", "iconColor", "textColor"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class NavigationRailKt {
    private static final String IconLayoutIdTag = "icon";
    private static final float IndicatorHorizontalPadding;
    private static final String IndicatorLayoutIdTag = "indicator";
    private static final String IndicatorRippleLayoutIdTag = "indicatorRipple";
    private static final float IndicatorVerticalPaddingNoLabel;
    private static final float IndicatorVerticalPaddingWithLabel;
    private static final String LabelLayoutIdTag = "label";
    private static final ProvidableCompositionLocal<NavigationRailOverride> LocalNavigationRailOverride;
    private static final float NavigationRailVerticalPadding = Dp.m8150constructorimpl(4);
    private static final float NavigationRailHeaderPadding = Dp.m8150constructorimpl(8);
    private static final float NavigationRailItemWidth = NavigationRailCollapsedTokens.INSTANCE.m4007getNarrowContainerWidthD9Ej5fM();
    private static final float NavigationRailItemHeight = NavigationRailVerticalItemTokens.INSTANCE.m4020getActiveIndicatorWidthD9Ej5fM();
    private static final float NavigationRailItemVerticalPadding = Dp.m8150constructorimpl(4);

    static final Unit NavigationRailItem$lambda$11(boolean z, Function0 function0, Function2 function2, Modifier modifier, boolean z2, Function2 function22, boolean z3, NavigationRailItemColors navigationRailItemColors, MutableInteractionSource mutableInteractionSource, int i, int i2, Composer composer, int i3) {
        NavigationRailItem(z, function0, function2, modifier, z2, function22, z3, navigationRailItemColors, mutableInteractionSource, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit NavigationRailItemLayout$lambda$18(Function2 function2, Function2 function22, Function2 function23, Function2 function24, boolean z, Function0 function0, Function0 function02, int i, Composer composer, int i2) {
        NavigationRailItemLayout(function2, function22, function23, function24, z, function0, function02, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit NavigationRail_qi6gXK8$lambda$1(Modifier modifier, long j, long j2, Function3 function3, WindowInsets windowInsets, Function3 function32, int i, int i2, Composer composer, int i3) {
        m2783NavigationRailqi6gXK8(modifier, j, j2, function3, windowInsets, function32, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: NavigationRail-qi6gXK8, reason: not valid java name */
    public static final void m2783NavigationRailqi6gXK8(Modifier modifier, long containerColor, long contentColor, Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function3, WindowInsets windowInsets, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function32, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        long j;
        long j2;
        Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function33;
        WindowInsets windowInsets2;
        Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function34;
        final Modifier modifier3;
        final long containerColor2;
        final long contentColor2;
        final WindowInsets windowInsets3;
        final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function35;
        Modifier.Companion modifier4;
        long containerColor3;
        long contentColor3;
        Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function36;
        Modifier modifier5;
        long containerColor4;
        long contentColor4;
        Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function37;
        WindowInsets windowInsets4;
        int i2;
        Composer $composer2 = $composer.startRestartGroup(331386280);
        ComposerKt.sourceInformation($composer2, "C(NavigationRail)N(modifier,containerColor:c#ui.graphics.Color,contentColor:c#ui.graphics.Color,header,windowInsets,content)127@5999L7,*136@6318L16:NavigationRail.kt#uh7d8r");
        int $dirty = $changed;
        int i3 = i & 1;
        if (i3 != 0) {
            $dirty |= 6;
            modifier2 = modifier;
        } else if (($changed & 6) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 4 : 2;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 48) == 0) {
            if ((i & 2) == 0) {
                j = containerColor;
                int i4 = $composer2.changed(j) ? 32 : 16;
                $dirty |= i4;
            } else {
                j = containerColor;
            }
            $dirty |= i4;
        } else {
            j = containerColor;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                j2 = contentColor;
                int i5 = $composer2.changed(j2) ? 256 : 128;
                $dirty |= i5;
            } else {
                j2 = contentColor;
            }
            $dirty |= i5;
        } else {
            j2 = contentColor;
        }
        int i6 = i & 8;
        if (i6 != 0) {
            $dirty |= 3072;
            function33 = function3;
        } else if (($changed & 3072) == 0) {
            function33 = function3;
            $dirty |= $composer2.changedInstance(function33) ? 2048 : 1024;
        } else {
            function33 = function3;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                windowInsets2 = windowInsets;
                int i7 = $composer2.changed(windowInsets2) ? 16384 : 8192;
                $dirty |= i7;
            } else {
                windowInsets2 = windowInsets;
            }
            $dirty |= i7;
        } else {
            windowInsets2 = windowInsets;
        }
        if ((i & 32) != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            function34 = function32;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            function34 = function32;
            $dirty |= $composer2.changedInstance(function34) ? 131072 : 65536;
        } else {
            function34 = function32;
        }
        if (!$composer2.shouldExecute((74899 & $dirty) != 74898, $dirty & 1)) {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            containerColor2 = j;
            contentColor2 = j2;
            windowInsets3 = windowInsets2;
            function35 = function33;
        } else {
            $composer2.startDefaults();
            ComposerKt.sourceInformation($composer2, "121@5706L14,122@5748L31,124@5895L12");
            if (($changed & 1) != 0 && !$composer2.getDefaultsInvalid()) {
                $composer2.skipToGroupEnd();
                if ((i & 2) != 0) {
                    $dirty &= -113;
                }
                if ((i & 4) != 0) {
                    $dirty &= -897;
                }
                if ((i & 16) != 0) {
                    $dirty &= -57345;
                }
                modifier5 = modifier2;
                containerColor4 = j;
                contentColor4 = j2;
                function37 = function33;
                windowInsets4 = windowInsets2;
                i2 = 331386280;
            } else {
                if (i3 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if ((i & 2) == 0) {
                    containerColor3 = j;
                } else {
                    containerColor3 = NavigationRailDefaults.INSTANCE.getContainerColor($composer2, 6);
                    $dirty &= -113;
                }
                if ((i & 4) == 0) {
                    contentColor3 = j2;
                } else {
                    contentColor3 = ColorSchemeKt.m2347contentColorForek8zF_U(containerColor3, $composer2, ($dirty >> 3) & 14);
                    $dirty &= -897;
                }
                if (i6 == 0) {
                    function36 = function33;
                } else {
                    function36 = null;
                }
                if ((i & 16) == 0) {
                    modifier5 = modifier4;
                    containerColor4 = containerColor3;
                    contentColor4 = contentColor3;
                    function37 = function36;
                    windowInsets4 = windowInsets2;
                    i2 = 331386280;
                } else {
                    $dirty &= -57345;
                    modifier5 = modifier4;
                    containerColor4 = containerColor3;
                    contentColor4 = contentColor3;
                    function37 = function36;
                    windowInsets4 = NavigationRailDefaults.INSTANCE.getWindowInsets($composer2, 6);
                    i2 = 331386280;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(i2, $dirty, -1, "androidx.compose.material3.NavigationRail (NavigationRail.kt:126)");
            }
            ProvidableCompositionLocal<NavigationRailOverride> providableCompositionLocal = LocalNavigationRailOverride;
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = $composer2.consume(providableCompositionLocal);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            NavigationRailOverride $this$NavigationRail_qi6gXK8_u24lambda_u240 = (NavigationRailOverride) objConsume;
            $this$NavigationRail_qi6gXK8_u24lambda_u240.NavigationRail(new NavigationRailOverrideScope(modifier5, containerColor4, contentColor4, function37, windowInsets4, function34, null), $composer2, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier5;
            containerColor2 = containerColor4;
            contentColor2 = contentColor4;
            function35 = function37;
            windowInsets3 = windowInsets4;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.NavigationRailKt$$ExternalSyntheticLambda6
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return NavigationRailKt.NavigationRail_qi6gXK8$lambda$1(modifier3, containerColor2, contentColor2, function35, windowInsets3, function32, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final void NavigationRailItem(final boolean selected, final Function0<Unit> function0, final Function2<? super Composer, ? super Integer, Unit> function2, Modifier modifier, boolean enabled, Function2<? super Composer, ? super Integer, Unit> function22, boolean alwaysShowLabel, NavigationRailItemColors colors, MutableInteractionSource interactionSource, Composer $composer, final int $changed, final int i) {
        Function0<Unit> function02;
        Function2<? super Composer, ? super Integer, Unit> function23;
        final Modifier modifier2;
        boolean enabled2;
        Function2<? super Composer, ? super Integer, Unit> function24;
        boolean alwaysShowLabel2;
        char c;
        int i2;
        final NavigationRailItemColors colors2;
        final boolean enabled3;
        final boolean alwaysShowLabel3;
        final Function2<? super Composer, ? super Integer, Unit> function25;
        final MutableInteractionSource interactionSource2;
        NavigationRailItemColors colors3;
        MutableInteractionSource interactionSource3;
        NavigationRailItemColors colors4;
        boolean enabled4;
        boolean enabled5;
        Function2<? super Composer, ? super Integer, Unit> function26;
        int $dirty;
        Modifier modifier3;
        String str;
        MutableInteractionSource interactionSource4;
        NavigationRailItemColors colors5;
        boolean enabled6;
        Function2<? super Composer, ? super Integer, Unit> function27;
        Function2 styledLabel;
        Function0<ComposeUiNode> function03;
        final Shape indicatorShape;
        Composer $composer2 = $composer.startRestartGroup(-1620317701);
        ComposerKt.sourceInformation($composer2, "C(NavigationRailItem)N(selected,onClick,icon,modifier,enabled,label,alwaysShowLabel,colors,interactionSource)213@9696L14,215@9752L618,245@11016L3249:NavigationRail.kt#uh7d8r");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty2 |= $composer2.changed(selected) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty2 |= 48;
            function02 = function0;
        } else if (($changed & 48) == 0) {
            function02 = function0;
            $dirty2 |= $composer2.changedInstance(function02) ? 32 : 16;
        } else {
            function02 = function0;
        }
        if ((i & 4) != 0) {
            $dirty2 |= 384;
            function23 = function2;
        } else if (($changed & 384) == 0) {
            function23 = function2;
            $dirty2 |= $composer2.changedInstance(function23) ? 256 : 128;
        } else {
            function23 = function2;
        }
        int i3 = i & 8;
        if (i3 != 0) {
            $dirty2 |= 3072;
            modifier2 = modifier;
        } else if (($changed & 3072) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer2.changed(modifier2) ? 2048 : 1024;
        } else {
            modifier2 = modifier;
        }
        int i4 = i & 16;
        if (i4 != 0) {
            $dirty2 |= 24576;
            enabled2 = enabled;
        } else if (($changed & 24576) == 0) {
            enabled2 = enabled;
            $dirty2 |= $composer2.changed(enabled2) ? 16384 : 8192;
        } else {
            enabled2 = enabled;
        }
        int i5 = i & 32;
        if (i5 != 0) {
            $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            function24 = function22;
        } else if ((196608 & $changed) == 0) {
            function24 = function22;
            $dirty2 |= $composer2.changedInstance(function24) ? 131072 : 65536;
        } else {
            function24 = function22;
        }
        int i6 = i & 64;
        if (i6 != 0) {
            $dirty2 |= 1572864;
            alwaysShowLabel2 = alwaysShowLabel;
            c = ' ';
        } else if (($changed & 1572864) == 0) {
            alwaysShowLabel2 = alwaysShowLabel;
            c = ' ';
            $dirty2 |= $composer2.changed(alwaysShowLabel2) ? 1048576 : 524288;
        } else {
            alwaysShowLabel2 = alwaysShowLabel;
            c = ' ';
        }
        if (($changed & 12582912) == 0) {
            $dirty2 |= ((i & 128) == 0 && $composer2.changed(colors)) ? 8388608 : 4194304;
        }
        int i7 = i & 256;
        if (i7 != 0) {
            $dirty2 |= 100663296;
            i2 = i7;
        } else if (($changed & 100663296) == 0) {
            i2 = i7;
            $dirty2 |= $composer2.changed(interactionSource) ? 67108864 : GroupFlagsKt.HasAuxSlotFlag;
        } else {
            i2 = i7;
        }
        if ($composer2.shouldExecute(($dirty2 & 38347923) != 38347922, $dirty2 & 1)) {
            $composer2.startDefaults();
            ComposerKt.sourceInformation($composer2, "207@9366L8");
            if (($changed & 1) != 0 && !$composer2.getDefaultsInvalid()) {
                $composer2.skipToGroupEnd();
                if ((i & 128) != 0) {
                    $dirty2 &= -29360129;
                }
                colors4 = colors;
                interactionSource3 = interactionSource;
                enabled4 = enabled2;
                enabled5 = alwaysShowLabel2;
                function26 = function24;
                $dirty = $dirty2;
                modifier3 = modifier2;
            } else {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i4 != 0) {
                    enabled2 = true;
                }
                if (i5 != 0) {
                    function24 = null;
                }
                if (i6 != 0) {
                    alwaysShowLabel2 = true;
                }
                if ((i & 128) == 0) {
                    colors3 = colors;
                } else {
                    colors3 = NavigationRailItemDefaults.INSTANCE.colors($composer2, 6);
                    $dirty2 &= -29360129;
                }
                if (i2 == 0) {
                    interactionSource3 = interactionSource;
                    colors4 = colors3;
                    enabled4 = enabled2;
                    enabled5 = alwaysShowLabel2;
                    function26 = function24;
                    $dirty = $dirty2;
                    modifier3 = modifier2;
                } else {
                    interactionSource3 = null;
                    enabled4 = enabled2;
                    enabled5 = alwaysShowLabel2;
                    function26 = function24;
                    colors4 = colors3;
                    $dirty = $dirty2;
                    modifier3 = modifier2;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1620317701, $dirty, -1, "androidx.compose.material3.NavigationRailItem (NavigationRail.kt:209)");
            }
            if (interactionSource3 == null) {
                $composer2.startReplaceGroup(253288608);
                ComposerKt.sourceInformation($composer2, "211@9518L39");
                ComposerKt.sourceInformationMarkerStart($composer2, 1947833250, "CC(remember):NavigationRail.kt#9igjgp");
                Object it$iv = $composer2.rememberedValue();
                str = "CC(remember):NavigationRail.kt#9igjgp";
                if (it$iv == Composer.INSTANCE.getEmpty()) {
                    Object value$iv = InteractionSourceKt.MutableInteractionSource();
                    $composer2.updateRememberedValue(value$iv);
                    it$iv = value$iv;
                }
                interactionSource4 = (MutableInteractionSource) it$iv;
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endReplaceGroup();
            } else {
                str = "CC(remember):NavigationRail.kt#9igjgp";
                $composer2.startReplaceGroup(1947832599);
                $composer2.endReplaceGroup();
                interactionSource4 = interactionSource3;
            }
            final FiniteAnimationSpec colorAnimationSpec = MotionSchemeKt.value(MotionSchemeKeyTokens.DefaultEffects, $composer2, 6);
            MutableInteractionSource interactionSource5 = interactionSource4;
            String str2 = str;
            final NavigationRailItemColors colors6 = colors4;
            Function2 styledIcon = ComposableLambdaKt.rememberComposableLambda(206057749, true, new NavigationRailKt$NavigationRailItem$styledIcon$1(colors4, selected, enabled4, colorAnimationSpec, function26, enabled5, function23), $composer2, 54);
            if (function26 == null) {
                $composer2.startReplaceGroup(254215848);
                $composer2.endReplaceGroup();
                colors5 = colors6;
                function27 = function26;
                styledLabel = null;
                enabled6 = enabled4;
            } else {
                $composer2.startReplaceGroup(254215849);
                ComposerKt.sourceInformation($composer2, "*230@10466L534");
                final boolean enabled7 = enabled4;
                final Function2<? super Composer, ? super Integer, Unit> function28 = function26;
                colors5 = colors6;
                enabled6 = enabled7;
                function27 = function28;
                Function2 function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-2056532825, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$styledLabel$1$1
                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                        invoke(composer, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer $composer3, int $changed2) {
                        ComposerKt.sourceInformation($composer3, "C231@10543L5,233@10602L198,237@10817L169:NavigationRail.kt#uh7d8r");
                        if (!$composer3.shouldExecute(($changed2 & 3) != 2, $changed2 & 1)) {
                            $composer3.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-2056532825, $changed2, -1, "androidx.compose.material3.NavigationRailItem.<anonymous>.<anonymous> (NavigationRail.kt:231)");
                        }
                        TextStyle style = TypographyKt.getValue(NavigationRailVerticalItemTokens.INSTANCE.getLabelTextFont(), $composer3, 6);
                        ProvideContentColorTextStyleKt.m3452ProvideContentColorTextStyle3JVO9M(invoke$lambda$0(SingleValueAnimationKt.m156animateColorAsStateeuL9pac(colors6.m2780textColorWaAFU9c$material3(selected, enabled7), colorAnimationSpec, null, null, $composer3, 0, 12)), style, function28, $composer3, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }

                    private static final long invoke$lambda$0(State<Color> state) {
                        Object thisObj$iv = state.getValue();
                        return ((Color) thisObj$iv).m5323unboximpl();
                    }
                }, $composer2, 54);
                $composer2.endReplaceGroup();
                styledLabel = function2RememberComposableLambda;
            }
            boolean enabled8 = enabled6;
            Modifier modifier4 = modifier3;
            Modifier modifier$iv = SizeKt.m1122widthInVpY3zN4$default(SizeKt.m1100defaultMinSizeVpY3zN4$default(SelectableKt.m1340selectableO2vRcR0(modifier4, selected, interactionSource5, null, enabled8, Role.m7336boximpl(Role.INSTANCE.m7350getTabo7Vup1c()), function02), 0.0f, NavigationRailItemHeight, 1, null), NavigationRailItemWidth, 0.0f, 2, null);
            Alignment contentAlignment$iv = Alignment.INSTANCE.getCenter();
            ComposerKt.sourceInformationMarkerStart($composer2, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
            MeasurePolicy measurePolicy$iv = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, true);
            int $changed$iv$iv = (432 << 3) & 112;
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
            int i8 = ($changed$iv$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            int i9 = ((432 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -1826939198, "C264@11812L7,261@11582L252,270@12124L7,267@11897L249,277@12430L7,283@12745L128,297@13310L237,305@13596L285,319@14147L32,320@14217L31,313@13891L368:NavigationRail.kt#uh7d8r");
            final State<Float> stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(selected ? 1.0f : 0.0f, MotionSchemeKt.value(MotionSchemeKeyTokens.DefaultEffects, $composer2, 6), 0.0f, null, null, $composer2, 0, 28);
            final State<Float> stateAnimateFloatAsState2 = AnimateAsStateKt.animateFloatAsState(selected ? 1.0f : 0.0f, MotionSchemeKt.value(MotionSchemeKeyTokens.FastSpatial, $composer2, 6), 0.0f, null, null, $composer2, 0, 28);
            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
            boolean alwaysShowLabel4 = enabled5;
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = $composer2.consume(localDensity);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Density $this$NavigationRailItem_u24lambda_u2410_u24lambda_u244 = (Density) objConsume;
            int itemWidth = $this$NavigationRailItem_u24lambda_u2410_u24lambda_u244.mo426roundToPx0680j_4(NavigationRailItemWidth);
            int indicatorWidth = $this$NavigationRailItem_u24lambda_u2410_u24lambda_u244.mo426roundToPx0680j_4(NavigationRailVerticalItemTokens.INSTANCE.m4020getActiveIndicatorWidthD9Ej5fM());
            float x$iv = (itemWidth - indicatorWidth) / 2.0f;
            long v1$iv$iv = Float.floatToRawIntBits(x$iv);
            long v2$iv$iv = Float.floatToRawIntBits(0.0f);
            long deltaOffset = Offset.m5060constructorimpl((v1$iv$iv << c) | (v2$iv$iv & 4294967295L));
            Unit unit = Unit.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer2, -474539147, str2);
            boolean invalid$iv = $composer2.changed(interactionSource5) | $composer2.changed(deltaOffset);
            Object value$iv2 = $composer2.rememberedValue();
            if (invalid$iv || value$iv2 == Composer.INSTANCE.getEmpty()) {
                value$iv2 = new MappedInteractionSource(interactionSource5, deltaOffset, null);
                $composer2.updateRememberedValue(value$iv2);
            }
            final MappedInteractionSource offsetInteractionSource = (MappedInteractionSource) value$iv2;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (function27 != null) {
                $composer2.startReplaceGroup(-1825624334);
                ComposerKt.sourceInformation($composer2, "289@13007L5");
                indicatorShape = ShapesKt.getValue(NavigationRailBaselineItemTokens.INSTANCE.getActiveIndicatorShape(), $composer2, 6);
                $composer2.endReplaceGroup();
            } else {
                $composer2.startReplaceGroup(-1825528978);
                ComposerKt.sourceInformation($composer2, "291@13076L5");
                indicatorShape = ShapesKt.getValue(ShapeKeyTokens.CornerFull, $composer2, 6);
                $composer2.endReplaceGroup();
            }
            Function2 indicatorRipple = ComposableLambdaKt.rememberComposableLambda(455696046, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItem$1$indicatorRipple$1
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer $composer3, int $changed2) {
                    ComposerKt.sourceInformation($composer3, "C298@13328L205:NavigationRail.kt#uh7d8r");
                    if (!$composer3.shouldExecute(($changed2 & 3) != 2, $changed2 & 1)) {
                        $composer3.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(455696046, $changed2, -1, "androidx.compose.material3.NavigationRailItem.<anonymous>.<anonymous> (NavigationRail.kt:298)");
                    }
                    BoxKt.Box(IndicationKt.indication(ClipKt.clip(LayoutIdKt.layoutId(Modifier.INSTANCE, "indicatorRipple"), indicatorShape), offsetInteractionSource, RippleKt.m2847rippleH2RKhps$default(false, 0.0f, 0L, 7, null)), $composer3, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, $composer2, 54);
            NavigationRailItemColors colors7 = colors5;
            Function2 indicator = ComposableLambdaKt.rememberComposableLambda(2137606782, true, new NavigationRailKt$NavigationRailItem$1$indicator$1(stateAnimateFloatAsState, colors7, indicatorShape), $composer2, 54);
            ComposerKt.sourceInformationMarkerStart($composer2, -474494379, str2);
            boolean invalid$iv2 = $composer2.changed(stateAnimateFloatAsState);
            Object it$iv2 = $composer2.rememberedValue();
            if (invalid$iv2 || it$iv2 == Composer.INSTANCE.getEmpty()) {
                Object value$iv3 = new Function0() { // from class: androidx.compose.material3.NavigationRailKt$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return Float.valueOf(((Number) stateAnimateFloatAsState.getValue()).floatValue());
                    }
                };
                $composer2.updateRememberedValue(value$iv3);
                it$iv2 = value$iv3;
            }
            Function0 function04 = (Function0) it$iv2;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerStart($composer2, -474492140, str2);
            boolean invalid$iv3 = $composer2.changed(stateAnimateFloatAsState2);
            Object it$iv3 = $composer2.rememberedValue();
            if (invalid$iv3 || it$iv3 == Composer.INSTANCE.getEmpty()) {
                Object value$iv4 = new Function0() { // from class: androidx.compose.material3.NavigationRailKt$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return Float.valueOf(((Number) stateAnimateFloatAsState2.getValue()).floatValue());
                    }
                };
                $composer2.updateRememberedValue(value$iv4);
                it$iv3 = value$iv4;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            NavigationRailItemLayout(indicatorRipple, indicator, styledIcon, styledLabel, alwaysShowLabel4, function04, (Function0) it$iv3, $composer2, (($dirty >> 6) & 57344) | 438);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier2 = modifier4;
            enabled3 = enabled8;
            colors2 = colors7;
            interactionSource2 = interactionSource3;
            alwaysShowLabel3 = alwaysShowLabel4;
            function25 = function27;
        } else {
            $composer2.skipToGroupEnd();
            colors2 = colors;
            enabled3 = enabled2;
            alwaysShowLabel3 = alwaysShowLabel2;
            function25 = function24;
            interactionSource2 = interactionSource;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.NavigationRailKt$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return NavigationRailKt.NavigationRailItem$lambda$11(selected, function0, function2, modifier2, enabled3, function25, alwaysShowLabel3, colors2, interactionSource2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x026a  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0324  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x049e  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x04bd  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0258  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0264  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static final void NavigationRailItemLayout(final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r54, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r55, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r56, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r57, final boolean r58, final kotlin.jvm.functions.Function0<java.lang.Float> r59, final kotlin.jvm.functions.Function0<java.lang.Float> r60, androidx.compose.runtime.Composer r61, final int r62) {
        /*
            Method dump skipped, instruction units count: 1251
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.NavigationRailKt.NavigationRailItemLayout(kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, boolean, kotlin.jvm.functions.Function0, kotlin.jvm.functions.Function0, androidx.compose.runtime.Composer, int):void");
    }

    static final Unit NavigationRailItemLayout$lambda$17$lambda$15$lambda$14(boolean $alwaysShowLabel, Function0 $alphaAnimationProgress, GraphicsLayerScope $this$graphicsLayer) {
        $this$graphicsLayer.setAlpha($alwaysShowLabel ? 1.0f : ((Number) $alphaAnimationProgress.invoke()).floatValue());
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: placeIcon-X9ElhV4, reason: not valid java name */
    public static final MeasureResult m2786placeIconX9ElhV4(MeasureScope $this$placeIcon_u2dX9ElhV4, final Placeable iconPlaceable, final Placeable indicatorRipplePlaceable, final Placeable indicatorPlaceable, long constraints) {
        final int width = ConstraintsKt.m8120constrainWidthK40F9xA(constraints, Math.max(iconPlaceable.getWidth(), Math.max(indicatorRipplePlaceable.getWidth(), indicatorPlaceable != null ? indicatorPlaceable.getWidth() : 0)));
        final int height = ConstraintsKt.m8119constrainHeightK40F9xA(constraints, $this$placeIcon_u2dX9ElhV4.mo426roundToPx0680j_4(NavigationRailItemHeight));
        final int iconX = (width - iconPlaceable.getWidth()) / 2;
        final int iconY = (height - iconPlaceable.getHeight()) / 2;
        final int rippleX = (width - indicatorRipplePlaceable.getWidth()) / 2;
        final int rippleY = (height - indicatorRipplePlaceable.getHeight()) / 2;
        return MeasureScope.layout$default($this$placeIcon_u2dX9ElhV4, width, height, null, new Function1() { // from class: androidx.compose.material3.NavigationRailKt$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return NavigationRailKt.placeIcon_X9ElhV4$lambda$20(indicatorPlaceable, iconPlaceable, iconX, iconY, indicatorRipplePlaceable, rippleX, rippleY, width, height, (Placeable.PlacementScope) obj);
            }
        }, 4, null);
    }

    static final Unit placeIcon_X9ElhV4$lambda$20(Placeable $indicatorPlaceable, Placeable $iconPlaceable, int $iconX, int $iconY, Placeable $indicatorRipplePlaceable, int $rippleX, int $rippleY, int $width, int $height, Placeable.PlacementScope $this$layout) {
        if ($indicatorPlaceable != null) {
            int indicatorX = ($width - $indicatorPlaceable.getWidth()) / 2;
            int indicatorY = ($height - $indicatorPlaceable.getHeight()) / 2;
            Placeable.PlacementScope.placeRelative$default($this$layout, $indicatorPlaceable, indicatorX, indicatorY, 0.0f, 4, null);
        }
        Placeable.PlacementScope.placeRelative$default($this$layout, $iconPlaceable, $iconX, $iconY, 0.0f, 4, null);
        Placeable.PlacementScope.placeRelative$default($this$layout, $indicatorRipplePlaceable, $rippleX, $rippleY, 0.0f, 4, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: placeLabelAndIcon-zUg2_y0, reason: not valid java name */
    public static final MeasureResult m2787placeLabelAndIconzUg2_y0(final MeasureScope $this$placeLabelAndIcon_u2dzUg2_y0, final Placeable labelPlaceable, final Placeable iconPlaceable, final Placeable indicatorRipplePlaceable, final Placeable indicatorPlaceable, long constraints, final boolean alwaysShowLabel, final float animationProgress) {
        float contentHeight = iconPlaceable.getHeight() + $this$placeLabelAndIcon_u2dzUg2_y0.mo432toPx0680j_4(IndicatorVerticalPaddingWithLabel) + $this$placeLabelAndIcon_u2dzUg2_y0.mo432toPx0680j_4(NavigationRailItemVerticalPadding) + labelPlaceable.getHeight();
        final float contentVerticalPadding = RangesKt.coerceAtLeast((Constraints.m8104getMinHeightimpl(constraints) - contentHeight) / 2.0f, $this$placeLabelAndIcon_u2dzUg2_y0.mo432toPx0680j_4(IndicatorVerticalPaddingWithLabel));
        float height = contentHeight + (contentVerticalPadding * 2.0f);
        float unselectedIconY = alwaysShowLabel ? contentVerticalPadding : (height - iconPlaceable.getHeight()) / 2.0f;
        float iconDistance = unselectedIconY - contentVerticalPadding;
        final float offset = iconDistance * (1.0f - animationProgress);
        final float labelY = iconPlaceable.getHeight() + contentVerticalPadding + $this$placeLabelAndIcon_u2dzUg2_y0.mo432toPx0680j_4(IndicatorVerticalPaddingWithLabel) + $this$placeLabelAndIcon_u2dzUg2_y0.mo432toPx0680j_4(NavigationRailItemVerticalPadding);
        final int width = ConstraintsKt.m8120constrainWidthK40F9xA(constraints, Math.max(iconPlaceable.getWidth(), Math.max(labelPlaceable.getWidth(), indicatorPlaceable != null ? indicatorPlaceable.getWidth() : 0)));
        final int labelX = (width - labelPlaceable.getWidth()) / 2;
        final int iconX = (width - iconPlaceable.getWidth()) / 2;
        final int rippleX = (width - indicatorRipplePlaceable.getWidth()) / 2;
        final float rippleY = contentVerticalPadding - $this$placeLabelAndIcon_u2dzUg2_y0.mo432toPx0680j_4(IndicatorVerticalPaddingWithLabel);
        return MeasureScope.layout$default($this$placeLabelAndIcon_u2dzUg2_y0, width, MathKt.roundToInt(height), null, new Function1() { // from class: androidx.compose.material3.NavigationRailKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return NavigationRailKt.placeLabelAndIcon_zUg2_y0$lambda$22(indicatorPlaceable, alwaysShowLabel, animationProgress, labelPlaceable, labelX, labelY, offset, iconPlaceable, iconX, contentVerticalPadding, indicatorRipplePlaceable, rippleX, rippleY, width, $this$placeLabelAndIcon_u2dzUg2_y0, (Placeable.PlacementScope) obj);
            }
        }, 4, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0036  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static final kotlin.Unit placeLabelAndIcon_zUg2_y0$lambda$22(androidx.compose.ui.layout.Placeable r17, boolean r18, float r19, androidx.compose.ui.layout.Placeable r20, int r21, float r22, float r23, androidx.compose.ui.layout.Placeable r24, int r25, float r26, androidx.compose.ui.layout.Placeable r27, int r28, float r29, int r30, androidx.compose.ui.layout.MeasureScope r31, androidx.compose.ui.layout.Placeable.PlacementScope r32) {
        /*
            if (r17 == 0) goto L27
            r1 = r17
            r7 = 0
            int r0 = r1.getWidth()
            int r0 = r30 - r0
            int r2 = r0 / 2
            float r0 = androidx.compose.material3.NavigationRailKt.IndicatorVerticalPaddingWithLabel
            r8 = r31
            float r0 = r8.mo432toPx0680j_4(r0)
            float r9 = r26 - r0
            float r0 = r9 + r23
            int r3 = kotlin.math.MathKt.roundToInt(r0)
            r5 = 4
            r6 = 0
            r4 = 0
            r0 = r32
            androidx.compose.ui.layout.Placeable.PlacementScope.placeRelative$default(r0, r1, r2, r3, r4, r5, r6)
            goto L29
        L27:
            r8 = r31
        L29:
            if (r18 != 0) goto L36
            r0 = 0
            int r0 = (r19 > r0 ? 1 : (r19 == r0 ? 0 : -1))
            if (r0 != 0) goto L33
            r0 = 1
            goto L34
        L33:
            r0 = 0
        L34:
            if (r0 != 0) goto L49
        L36:
            float r0 = r22 + r23
            int r13 = kotlin.math.MathKt.roundToInt(r0)
            r15 = 4
            r16 = 0
            r14 = 0
            r11 = r20
            r12 = r21
            r10 = r32
            androidx.compose.ui.layout.Placeable.PlacementScope.placeRelative$default(r10, r11, r12, r13, r14, r15, r16)
        L49:
            float r0 = r26 + r23
            int r13 = kotlin.math.MathKt.roundToInt(r0)
            r15 = 4
            r16 = 0
            r14 = 0
            r11 = r24
            r12 = r25
            r10 = r32
            androidx.compose.ui.layout.Placeable.PlacementScope.placeRelative$default(r10, r11, r12, r13, r14, r15, r16)
            float r0 = r29 + r23
            int r13 = kotlin.math.MathKt.roundToInt(r0)
            r11 = r27
            r12 = r28
            androidx.compose.ui.layout.Placeable.PlacementScope.placeRelative$default(r10, r11, r12, r13, r14, r15, r16)
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.NavigationRailKt.placeLabelAndIcon_zUg2_y0$lambda$22(androidx.compose.ui.layout.Placeable, boolean, float, androidx.compose.ui.layout.Placeable, int, float, float, androidx.compose.ui.layout.Placeable, int, float, androidx.compose.ui.layout.Placeable, int, float, int, androidx.compose.ui.layout.MeasureScope, androidx.compose.ui.layout.Placeable$PlacementScope):kotlin.Unit");
    }

    static {
        float arg0$iv = NavigationRailVerticalItemTokens.INSTANCE.m4020getActiveIndicatorWidthD9Ej5fM();
        float other$iv = NavigationRailBaselineItemTokens.INSTANCE.m4003getIconSizeD9Ej5fM();
        IndicatorHorizontalPadding = Dp.m8150constructorimpl(Dp.m8150constructorimpl(arg0$iv - other$iv) / 2);
        float arg0$iv2 = NavigationRailVerticalItemTokens.INSTANCE.m4019getActiveIndicatorHeightD9Ej5fM();
        float other$iv2 = NavigationRailBaselineItemTokens.INSTANCE.m4003getIconSizeD9Ej5fM();
        IndicatorVerticalPaddingWithLabel = Dp.m8150constructorimpl(Dp.m8150constructorimpl(arg0$iv2 - other$iv2) / 2);
        float arg0$iv3 = NavigationRailVerticalItemTokens.INSTANCE.m4020getActiveIndicatorWidthD9Ej5fM();
        float other$iv3 = NavigationRailBaselineItemTokens.INSTANCE.m4003getIconSizeD9Ej5fM();
        IndicatorVerticalPaddingNoLabel = Dp.m8150constructorimpl(Dp.m8150constructorimpl(arg0$iv3 - other$iv3) / 2);
        LocalNavigationRailOverride = CompositionLocalKt.compositionLocalOf$default(null, new Function0() { // from class: androidx.compose.material3.NavigationRailKt$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return DefaultNavigationRailOverride.INSTANCE;
            }
        }, 1, null);
    }

    public static final float getNavigationRailVerticalPadding() {
        return NavigationRailVerticalPadding;
    }

    public static final float getNavigationRailItemWidth() {
        return NavigationRailItemWidth;
    }

    public static final float getNavigationRailItemHeight() {
        return NavigationRailItemHeight;
    }

    public static final float getNavigationRailItemVerticalPadding() {
        return NavigationRailItemVerticalPadding;
    }

    public static final ProvidableCompositionLocal<NavigationRailOverride> getLocalNavigationRailOverride() {
        return LocalNavigationRailOverride;
    }
}
