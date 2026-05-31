package androidx.compose.material3;

import androidx.compose.animation.SingleValueAnimationKt;
import androidx.compose.animation.core.AnimateAsStateKt;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.foundation.IndicationKt;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.WindowInsets;
import androidx.compose.foundation.selection.SelectableKt;
import androidx.compose.material3.internal.MappedInteractionSource;
import androidx.compose.material3.internal.ProvideContentColorTextStyleKt;
import androidx.compose.material3.tokens.MotionSchemeKeyTokens;
import androidx.compose.material3.tokens.NavigationBarTokens;
import androidx.compose.material3.tokens.NavigationBarVerticalItemTokens;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.ProvidableCompositionLocal;
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
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.layout.LayoutIdKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.OnRemeasuredModifierKt;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.IntSize;
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

/* JADX INFO: compiled from: NavigationBar.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\u008c\u0001\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\u001a_\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\u001c\u0010\u000b\u001a\u0018\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00010\f¢\u0006\u0002\b\u000e¢\u0006\u0002\b\u000fH\u0007¢\u0006\u0004\b\u0010\u0010\u0011\u001a\u0085\u0001\u0010\u0012\u001a\u00020\u0001*\u00020\r2\u0006\u0010\u0013\u001a\u00020\u00142\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00010\u00162\u0011\u0010\u0017\u001a\r\u0012\u0004\u0012\u00020\u00010\u0016¢\u0006\u0002\b\u000e2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0018\u001a\u00020\u00142\u0015\b\u0002\u0010\u0019\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0016¢\u0006\u0002\b\u000e2\b\b\u0002\u0010\u001a\u001a\u00020\u00142\b\b\u0002\u0010\u001b\u001a\u00020\u001c2\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0007¢\u0006\u0002\u0010\u001f\u001a\u007f\u0010 \u001a\u00020\u00012\u0011\u0010!\u001a\r\u0012\u0004\u0012\u00020\u00010\u0016¢\u0006\u0002\b\u000e2\u0011\u0010\"\u001a\r\u0012\u0004\u0012\u00020\u00010\u0016¢\u0006\u0002\b\u000e2\u0011\u0010\u0017\u001a\r\u0012\u0004\u0012\u00020\u00010\u0016¢\u0006\u0002\b\u000e2\u0013\u0010\u0019\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0016¢\u0006\u0002\b\u000e2\u0006\u0010\u001a\u001a\u00020\u00142\f\u0010#\u001a\b\u0012\u0004\u0012\u00020$0\u00162\f\u0010%\u001a\b\u0012\u0004\u0012\u00020$0\u0016H\u0003¢\u0006\u0002\u0010&\u001a5\u0010'\u001a\u00020(*\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020+2\b\u0010-\u001a\u0004\u0018\u00010+2\u0006\u0010.\u001a\u00020/H\u0002¢\u0006\u0004\b0\u00101\u001aM\u00102\u001a\u00020(*\u00020)2\u0006\u00103\u001a\u00020+2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020+2\b\u0010-\u001a\u0004\u0018\u00010+2\u0006\u0010.\u001a\u00020/2\u0006\u0010\u001a\u001a\u00020\u00142\u0006\u00104\u001a\u00020$H\u0002¢\u0006\u0004\b5\u00106\"\u000e\u00107\u001a\u000208X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u00109\u001a\u000208X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010:\u001a\u000208X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010;\u001a\u000208X\u0082T¢\u0006\u0002\n\u0000\"\u0010\u0010<\u001a\u00020\bX\u0082\u0004¢\u0006\u0004\n\u0002\u0010=\"\u0016\u0010>\u001a\u00020\bX\u0080\u0004¢\u0006\n\n\u0002\u0010=\u001a\u0004\b?\u0010@\"\u0016\u0010A\u001a\u00020\bX\u0080\u0004¢\u0006\n\n\u0002\u0010=\u001a\u0004\bB\u0010@\"\u0010\u0010C\u001a\u00020\bX\u0082\u0004¢\u0006\u0004\n\u0002\u0010=\"\u0016\u0010D\u001a\u00020\bX\u0080\u0004¢\u0006\n\n\u0002\u0010=\u001a\u0004\bE\u0010@\"\u0010\u0010F\u001a\u00020\bX\u0082\u0004¢\u0006\u0004\n\u0002\u0010=\"\u0016\u0010G\u001a\u00020\bX\u0080\u0004¢\u0006\n\n\u0002\u0010=\u001a\u0004\bH\u0010@\"\u001a\u0010I\u001a\b\u0012\u0004\u0012\u00020K0JX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\bL\u0010M¨\u0006N²\u0006\n\u0010O\u001a\u00020\u0005X\u008a\u0084\u0002²\u0006\n\u0010P\u001a\u00020\u0005X\u008a\u0084\u0002²\u0006\n\u0010Q\u001a\u00020RX\u008a\u008e\u0002"}, d2 = {"NavigationBar", "", "modifier", "Landroidx/compose/ui/Modifier;", "containerColor", "Landroidx/compose/ui/graphics/Color;", "contentColor", "tonalElevation", "Landroidx/compose/ui/unit/Dp;", "windowInsets", "Landroidx/compose/foundation/layout/WindowInsets;", "content", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/RowScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "NavigationBar-HsRjFd4", "(Landroidx/compose/ui/Modifier;JJFLandroidx/compose/foundation/layout/WindowInsets;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "NavigationBarItem", "selected", "", "onClick", "Lkotlin/Function0;", NavigationBarKt.IconLayoutIdTag, "enabled", NavigationBarKt.LabelLayoutIdTag, "alwaysShowLabel", "colors", "Landroidx/compose/material3/NavigationBarItemColors;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "(Landroidx/compose/foundation/layout/RowScope;ZLkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/Modifier;ZLkotlin/jvm/functions/Function2;ZLandroidx/compose/material3/NavigationBarItemColors;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;II)V", "NavigationBarItemLayout", NavigationBarKt.IndicatorRippleLayoutIdTag, NavigationBarKt.IndicatorLayoutIdTag, "alphaAnimationProgress", "", "sizeAnimationProgress", "(Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;ZLkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "placeIcon", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "iconPlaceable", "Landroidx/compose/ui/layout/Placeable;", "indicatorRipplePlaceable", "indicatorPlaceable", "constraints", "Landroidx/compose/ui/unit/Constraints;", "placeIcon-X9ElhV4", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;J)Landroidx/compose/ui/layout/MeasureResult;", "placeLabelAndIcon", "labelPlaceable", "animationProgress", "placeLabelAndIcon-zUg2_y0", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;JZF)Landroidx/compose/ui/layout/MeasureResult;", "IndicatorRippleLayoutIdTag", "", "IndicatorLayoutIdTag", "IconLayoutIdTag", "LabelLayoutIdTag", "NavigationBarHeight", "F", "NavigationBarItemHorizontalPadding", "getNavigationBarItemHorizontalPadding", "()F", "NavigationBarIndicatorToLabelPadding", "getNavigationBarIndicatorToLabelPadding", "IndicatorHorizontalPadding", "IndicatorVerticalPadding", "getIndicatorVerticalPadding", "IndicatorVerticalOffset", "NavigationBarItemToIconMinimumPadding", "getNavigationBarItemToIconMinimumPadding", "LocalNavigationBarOverride", "Landroidx/compose/runtime/ProvidableCompositionLocal;", "Landroidx/compose/material3/NavigationBarOverride;", "getLocalNavigationBarOverride", "()Landroidx/compose/runtime/ProvidableCompositionLocal;", "material3", "iconColor", "textColor", "itemWidth", ""}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class NavigationBarKt {
    private static final String IconLayoutIdTag = "icon";
    private static final float IndicatorHorizontalPadding;
    private static final String IndicatorLayoutIdTag = "indicator";
    private static final String IndicatorRippleLayoutIdTag = "indicatorRipple";
    private static final float IndicatorVerticalOffset;
    private static final float IndicatorVerticalPadding;
    private static final String LabelLayoutIdTag = "label";
    private static final ProvidableCompositionLocal<NavigationBarOverride> LocalNavigationBarOverride;
    private static final float NavigationBarItemToIconMinimumPadding;
    private static final float NavigationBarHeight = NavigationBarTokens.INSTANCE.m3986getTallContainerHeightD9Ej5fM();
    private static final float NavigationBarItemHorizontalPadding = Dp.m8150constructorimpl(8);
    private static final float NavigationBarIndicatorToLabelPadding = Dp.m8150constructorimpl(4);

    static final Unit NavigationBarItem$lambda$16(RowScope rowScope, boolean z, Function0 function0, Function2 function2, Modifier modifier, boolean z2, Function2 function22, boolean z3, NavigationBarItemColors navigationBarItemColors, MutableInteractionSource mutableInteractionSource, int i, int i2, Composer composer, int i3) {
        NavigationBarItem(rowScope, z, function0, function2, modifier, z2, function22, z3, navigationBarItemColors, mutableInteractionSource, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit NavigationBarItemLayout$lambda$23(Function2 function2, Function2 function22, Function2 function23, Function2 function24, boolean z, Function0 function0, Function0 function02, int i, Composer composer, int i2) {
        NavigationBarItemLayout(function2, function22, function23, function24, z, function0, function02, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit NavigationBar_HsRjFd4$lambda$1(Modifier modifier, long j, long j2, float f, WindowInsets windowInsets, Function3 function3, int i, int i2, Composer composer, int i3) {
        m2719NavigationBarHsRjFd4(modifier, j, j2, f, windowInsets, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: NavigationBar-HsRjFd4, reason: not valid java name */
    public static final void m2719NavigationBarHsRjFd4(Modifier modifier, long containerColor, long contentColor, float tonalElevation, WindowInsets windowInsets, final Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        long j;
        long j2;
        float f;
        WindowInsets windowInsets2;
        Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function32;
        final Modifier modifier3;
        final long containerColor2;
        final long contentColor2;
        final WindowInsets windowInsets3;
        final float tonalElevation2;
        Modifier.Companion modifier4;
        long containerColor3;
        long contentColor3;
        float tonalElevation3;
        Modifier modifier5;
        long containerColor4;
        long contentColor4;
        float tonalElevation4;
        WindowInsets windowInsets4;
        int i2;
        Composer $composer2 = $composer.startRestartGroup(1054099326);
        ComposerKt.sourceInformation($composer2, "C(NavigationBar)N(modifier,containerColor:c#ui.graphics.Color,contentColor:c#ui.graphics.Color,tonalElevation:c#ui.unit.Dp,windowInsets,content)119@5618L7,*128@5952L15:NavigationBar.kt#uh7d8r");
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
            f = tonalElevation;
        } else if (($changed & 3072) == 0) {
            f = tonalElevation;
            $dirty |= $composer2.changed(f) ? 2048 : 1024;
        } else {
            f = tonalElevation;
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
            function32 = function3;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            function32 = function3;
            $dirty |= $composer2.changedInstance(function32) ? 131072 : 65536;
        } else {
            function32 = function3;
        }
        if (!$composer2.shouldExecute((74899 & $dirty) != 74898, $dirty & 1)) {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            containerColor2 = j;
            contentColor2 = j2;
            windowInsets3 = windowInsets2;
            tonalElevation2 = f;
        } else {
            $composer2.startDefaults();
            ComposerKt.sourceInformation($composer2, "113@5304L14,114@5360L11,116@5518L12");
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
                tonalElevation4 = f;
                windowInsets4 = windowInsets2;
                i2 = 1054099326;
            } else {
                if (i3 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if ((i & 2) == 0) {
                    containerColor3 = j;
                } else {
                    containerColor3 = NavigationBarDefaults.INSTANCE.getContainerColor($composer2, 6);
                    $dirty &= -113;
                }
                if ((i & 4) == 0) {
                    contentColor3 = j2;
                } else {
                    contentColor3 = ColorSchemeKt.m2346contentColorFor4WTKRHQ(MaterialTheme.INSTANCE.getColorScheme($composer2, 6), containerColor3);
                    $dirty &= -897;
                }
                if (i6 == 0) {
                    tonalElevation3 = f;
                } else {
                    tonalElevation3 = NavigationBarDefaults.INSTANCE.m2704getElevationD9Ej5fM();
                }
                if ((i & 16) == 0) {
                    modifier5 = modifier4;
                    containerColor4 = containerColor3;
                    contentColor4 = contentColor3;
                    tonalElevation4 = tonalElevation3;
                    windowInsets4 = windowInsets2;
                    i2 = 1054099326;
                } else {
                    $dirty &= -57345;
                    modifier5 = modifier4;
                    containerColor4 = containerColor3;
                    contentColor4 = contentColor3;
                    tonalElevation4 = tonalElevation3;
                    windowInsets4 = NavigationBarDefaults.INSTANCE.getWindowInsets($composer2, 6);
                    i2 = 1054099326;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(i2, $dirty, -1, "androidx.compose.material3.NavigationBar (NavigationBar.kt:118)");
            }
            ProvidableCompositionLocal<NavigationBarOverride> providableCompositionLocal = LocalNavigationBarOverride;
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = $composer2.consume(providableCompositionLocal);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            NavigationBarOverride $this$NavigationBar_HsRjFd4_u24lambda_u240 = (NavigationBarOverride) objConsume;
            $this$NavigationBar_HsRjFd4_u24lambda_u240.NavigationBar(new NavigationBarOverrideScope(modifier5, containerColor4, contentColor4, tonalElevation4, windowInsets4, function32, null), $composer2, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier5;
            containerColor2 = containerColor4;
            contentColor2 = contentColor4;
            tonalElevation2 = tonalElevation4;
            windowInsets3 = windowInsets4;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.NavigationBarKt$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return NavigationBarKt.NavigationBar_HsRjFd4$lambda$1(modifier3, containerColor2, contentColor2, tonalElevation2, windowInsets3, function3, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final void NavigationBarItem(final RowScope $this$NavigationBarItem, final boolean selected, final Function0<Unit> function0, final Function2<? super Composer, ? super Integer, Unit> function2, Modifier modifier, boolean enabled, Function2<? super Composer, ? super Integer, Unit> function22, boolean alwaysShowLabel, NavigationBarItemColors colors, MutableInteractionSource interactionSource, Composer $composer, final int $changed, final int i) {
        Function0<Unit> function02;
        final Modifier modifier2;
        boolean enabled2;
        Function2<? super Composer, ? super Integer, Unit> function23;
        boolean alwaysShowLabel2;
        char c;
        int i2;
        final MutableInteractionSource interactionSource2;
        final boolean enabled3;
        final boolean alwaysShowLabel3;
        final Function2<? super Composer, ? super Integer, Unit> function24;
        final NavigationBarItemColors colors2;
        NavigationBarItemColors colors3;
        int $dirty;
        MutableInteractionSource interactionSource3;
        boolean alwaysShowLabel4;
        Modifier modifier3;
        Function2<? super Composer, ? super Integer, Unit> function25;
        int $dirty2;
        boolean enabled4;
        NavigationBarItemColors colors4;
        MutableInteractionSource mutableInteractionSource;
        NavigationBarItemColors colors5;
        Function2 styledLabel;
        Function0<ComposeUiNode> function03;
        Modifier modifier4;
        Composer $composer2 = $composer.startRestartGroup(974293026);
        ComposerKt.sourceInformation($composer2, "C(NavigationBarItem)N(selected,onClick,icon,modifier,enabled,label,alwaysShowLabel,colors,interactionSource)209@9556L14,211@9612L618,241@10880L33,255@11322L24,243@10919L3178:NavigationBar.kt#uh7d8r");
        int $dirty3 = $changed;
        if ((Integer.MIN_VALUE & i) != 0) {
            $dirty3 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty3 |= $composer2.changed($this$NavigationBarItem) ? 4 : 2;
        }
        if ((i & 1) != 0) {
            $dirty3 |= 48;
        } else if (($changed & 48) == 0) {
            $dirty3 |= $composer2.changed(selected) ? 32 : 16;
        }
        if ((i & 2) != 0) {
            $dirty3 |= 384;
            function02 = function0;
        } else if (($changed & 384) == 0) {
            function02 = function0;
            $dirty3 |= $composer2.changedInstance(function02) ? 256 : 128;
        } else {
            function02 = function0;
        }
        if ((i & 4) != 0) {
            $dirty3 |= 3072;
        } else if (($changed & 3072) == 0) {
            $dirty3 |= $composer2.changedInstance(function2) ? 2048 : 1024;
        }
        int i3 = i & 8;
        if (i3 != 0) {
            $dirty3 |= 24576;
            modifier2 = modifier;
        } else if (($changed & 24576) == 0) {
            modifier2 = modifier;
            $dirty3 |= $composer2.changed(modifier2) ? 16384 : 8192;
        } else {
            modifier2 = modifier;
        }
        int i4 = i & 16;
        if (i4 != 0) {
            $dirty3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            enabled2 = enabled;
        } else if ((196608 & $changed) == 0) {
            enabled2 = enabled;
            $dirty3 |= $composer2.changed(enabled2) ? 131072 : 65536;
        } else {
            enabled2 = enabled;
        }
        int i5 = i & 32;
        if (i5 != 0) {
            $dirty3 |= 1572864;
            function23 = function22;
        } else if ((1572864 & $changed) == 0) {
            function23 = function22;
            $dirty3 |= $composer2.changedInstance(function23) ? 1048576 : 524288;
        } else {
            function23 = function22;
        }
        int i6 = i & 64;
        if (i6 != 0) {
            $dirty3 |= 12582912;
            alwaysShowLabel2 = alwaysShowLabel;
            c = ' ';
        } else if (($changed & 12582912) == 0) {
            alwaysShowLabel2 = alwaysShowLabel;
            c = ' ';
            $dirty3 |= $composer2.changed(alwaysShowLabel2) ? 8388608 : 4194304;
        } else {
            alwaysShowLabel2 = alwaysShowLabel;
            c = ' ';
        }
        if (($changed & 100663296) == 0) {
            $dirty3 |= ((i & 128) == 0 && $composer2.changed(colors)) ? 67108864 : GroupFlagsKt.HasAuxSlotFlag;
        }
        int i7 = i & 256;
        if (i7 != 0) {
            $dirty3 |= 805306368;
            i2 = i7;
        } else if (($changed & 805306368) == 0) {
            i2 = i7;
            $dirty3 |= $composer2.changed(interactionSource) ? GroupFlagsKt.HasMovableContentFlag : GroupFlagsKt.IsMovableContentFlag;
        } else {
            i2 = i7;
        }
        int $dirty4 = $dirty3;
        if ($composer2.shouldExecute(($dirty3 & 306783379) != 306783378, $dirty4 & 1)) {
            $composer2.startDefaults();
            ComposerKt.sourceInformation($composer2, "203@9226L8");
            if (($changed & 1) != 0 && !$composer2.getDefaultsInvalid()) {
                $composer2.skipToGroupEnd();
                if ((i & 128) != 0) {
                    interactionSource3 = interactionSource;
                    enabled4 = enabled2;
                    alwaysShowLabel4 = alwaysShowLabel2;
                    modifier3 = modifier2;
                    function25 = function23;
                    $dirty2 = $dirty4 & (-234881025);
                    colors3 = colors;
                } else {
                    colors3 = colors;
                    interactionSource3 = interactionSource;
                    enabled4 = enabled2;
                    alwaysShowLabel4 = alwaysShowLabel2;
                    modifier3 = modifier2;
                    function25 = function23;
                    $dirty2 = $dirty4;
                }
            } else {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i4 != 0) {
                    enabled2 = true;
                }
                if (i5 != 0) {
                    function23 = null;
                }
                if (i6 != 0) {
                    alwaysShowLabel2 = true;
                }
                if ((i & 128) == 0) {
                    colors3 = colors;
                    $dirty = $dirty4;
                } else {
                    colors3 = NavigationBarItemDefaults.INSTANCE.colors($composer2, 6);
                    $dirty = $dirty4 & (-234881025);
                }
                if (i2 == 0) {
                    interactionSource3 = interactionSource;
                    alwaysShowLabel4 = alwaysShowLabel2;
                    modifier3 = modifier2;
                    function25 = function23;
                    $dirty2 = $dirty;
                    enabled4 = enabled2;
                } else {
                    interactionSource3 = null;
                    alwaysShowLabel4 = alwaysShowLabel2;
                    modifier3 = modifier2;
                    function25 = function23;
                    $dirty2 = $dirty;
                    enabled4 = enabled2;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(974293026, $dirty2, -1, "androidx.compose.material3.NavigationBarItem (NavigationBar.kt:205)");
            }
            if (interactionSource3 == null) {
                $composer2.startReplaceGroup(-224963495);
                ComposerKt.sourceInformation($composer2, "207@9378L39");
                ComposerKt.sourceInformationMarkerStart($composer2, -7256887, "CC(remember):NavigationBar.kt#9igjgp");
                colors4 = colors3;
                Object it$iv = $composer2.rememberedValue();
                if (it$iv == Composer.INSTANCE.getEmpty()) {
                    Object value$iv = InteractionSourceKt.MutableInteractionSource();
                    $composer2.updateRememberedValue(value$iv);
                    it$iv = value$iv;
                }
                mutableInteractionSource = (MutableInteractionSource) it$iv;
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endReplaceGroup();
            } else {
                colors4 = colors3;
                $composer2.startReplaceGroup(-7257538);
                $composer2.endReplaceGroup();
                mutableInteractionSource = interactionSource3;
            }
            MutableInteractionSource interactionSource4 = mutableInteractionSource;
            final FiniteAnimationSpec colorAnimationSpec = MotionSchemeKt.value(MotionSchemeKeyTokens.DefaultEffects, $composer2, 6);
            Modifier modifier5 = modifier3;
            final NavigationBarItemColors colors6 = colors4;
            Function2 styledIcon = ComposableLambdaKt.rememberComposableLambda(-876637252, true, new NavigationBarKt$NavigationBarItem$styledIcon$1(colors6, selected, enabled4, colorAnimationSpec, function25, alwaysShowLabel4, function2), $composer2, 54);
            if (function25 == null) {
                $composer2.startReplaceGroup(-224036658);
                $composer2.endReplaceGroup();
                colors5 = colors6;
                styledLabel = null;
            } else {
                $composer2.startReplaceGroup(-224036657);
                ComposerKt.sourceInformation($composer2, "*226@10326L521");
                final boolean enabled5 = enabled4;
                final Function2<? super Composer, ? super Integer, Unit> function26 = function25;
                colors5 = colors6;
                Function2 function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(802208206, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$styledLabel$1$1
                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                        invoke(composer, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer $composer3, int $changed2) {
                        ComposerKt.sourceInformation($composer3, "C227@10390L5,229@10449L198,233@10664L169:NavigationBar.kt#uh7d8r");
                        if (!$composer3.shouldExecute(($changed2 & 3) != 2, $changed2 & 1)) {
                            $composer3.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(802208206, $changed2, -1, "androidx.compose.material3.NavigationBarItem.<anonymous>.<anonymous> (NavigationBar.kt:227)");
                        }
                        TextStyle style = TypographyKt.getValue(NavigationBarTokens.INSTANCE.getLabelTextFont(), $composer3, 6);
                        ProvideContentColorTextStyleKt.m3452ProvideContentColorTextStyle3JVO9M(invoke$lambda$0(SingleValueAnimationKt.m156animateColorAsStateeuL9pac(colors6.m2716textColorWaAFU9c$material3(selected, enabled5), colorAnimationSpec, null, null, $composer3, 0, 12)), style, function26, $composer3, 0);
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
            ComposerKt.sourceInformationMarkerStart($composer2, -7208829, "CC(remember):NavigationBar.kt#9igjgp");
            Object it$iv2 = $composer2.rememberedValue();
            if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                Object value$iv2 = SnapshotIntStateKt.mutableIntStateOf(0);
                $composer2.updateRememberedValue(value$iv2);
                it$iv2 = value$iv2;
            }
            final MutableIntState itemWidth$delegate = (MutableIntState) it$iv2;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            boolean enabled6 = enabled4;
            Modifier modifierWeight$default = RowScope.weight$default($this$NavigationBarItem, SizeKt.m1100defaultMinSizeVpY3zN4$default(SelectableKt.m1340selectableO2vRcR0(modifier5, selected, interactionSource4, null, enabled4, Role.m7336boximpl(Role.INSTANCE.m7350getTabo7Vup1c()), function02), 0.0f, NavigationBarHeight, 1, null), 1.0f, false, 2, null);
            ComposerKt.sourceInformationMarkerStart($composer2, -7194694, "CC(remember):NavigationBar.kt#9igjgp");
            Object it$iv3 = $composer2.rememberedValue();
            if (it$iv3 == Composer.INSTANCE.getEmpty()) {
                Object value$iv3 = new Function1() { // from class: androidx.compose.material3.NavigationBarKt$$ExternalSyntheticLambda3
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return NavigationBarKt.NavigationBarItem$lambda$8$lambda$7(itemWidth$delegate, (IntSize) obj);
                    }
                };
                $composer2.updateRememberedValue(value$iv3);
                it$iv3 = value$iv3;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Modifier modifier$iv = OnRemeasuredModifierKt.onSizeChanged(modifierWeight$default, (Function1) it$iv3);
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
            Function2<? super Composer, ? super Integer, Unit> function27 = function25;
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
            ComposerKt.sourceInformationMarkerStart($composer2, -1565191211, "C263@11734L7,260@11504L252,269@12046L7,266@11819L249,275@12351L7,281@12645L128,288@12988L273,296@13310L404,313@13979L32,314@14049L31,307@13724L367:NavigationBar.kt#uh7d8r");
            final State<Float> stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(selected ? 1.0f : 0.0f, MotionSchemeKt.value(MotionSchemeKeyTokens.DefaultEffects, $composer2, 6), 0.0f, null, null, $composer2, 0, 28);
            final State<Float> stateAnimateFloatAsState2 = AnimateAsStateKt.animateFloatAsState(selected ? 1.0f : 0.0f, MotionSchemeKt.value(MotionSchemeKeyTokens.FastSpatial, $composer2, 6), 0.0f, null, null, $composer2, 0, 28);
            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
            boolean alwaysShowLabel5 = alwaysShowLabel4;
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = $composer2.consume(localDensity);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Density $this$NavigationBarItem_u24lambda_u2415_u24lambda_u249 = (Density) objConsume;
            int indicatorWidth = $this$NavigationBarItem_u24lambda_u2415_u24lambda_u249.mo426roundToPx0680j_4(NavigationBarVerticalItemTokens.INSTANCE.m3988getActiveIndicatorWidthD9Ej5fM());
            float x$iv = (NavigationBarItem$lambda$5(itemWidth$delegate) - indicatorWidth) / 2.0f;
            float y$iv = $this$NavigationBarItem_u24lambda_u2415_u24lambda_u249.mo432toPx0680j_4(IndicatorVerticalOffset);
            long v1$iv$iv = Float.floatToRawIntBits(x$iv);
            long v2$iv$iv = Float.floatToRawIntBits(y$iv);
            long deltaOffset = Offset.m5060constructorimpl((v1$iv$iv << c) | (v2$iv$iv & 4294967295L));
            Unit unit = Unit.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer2, 1196471708, "CC(remember):NavigationBar.kt#9igjgp");
            boolean invalid$iv = $composer2.changed(interactionSource4) | $composer2.changed(deltaOffset);
            Object value$iv4 = $composer2.rememberedValue();
            if (invalid$iv || value$iv4 == Composer.INSTANCE.getEmpty()) {
                modifier4 = modifier5;
                value$iv4 = new MappedInteractionSource(interactionSource4, deltaOffset, null);
                $composer2.updateRememberedValue(value$iv4);
            } else {
                modifier4 = modifier5;
            }
            final MappedInteractionSource offsetInteractionSource = (MappedInteractionSource) value$iv4;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Function2 indicatorRipple = ComposableLambdaKt.rememberComposableLambda(-2082182507, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItem$2$indicatorRipple$1
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer $composer3, int $changed2) {
                    ComposerKt.sourceInformation($composer3, "C291@13152L5,289@13006L241:NavigationBar.kt#uh7d8r");
                    if (!$composer3.shouldExecute(($changed2 & 3) != 2, $changed2 & 1)) {
                        $composer3.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-2082182507, $changed2, -1, "androidx.compose.material3.NavigationBarItem.<anonymous>.<anonymous> (NavigationBar.kt:289)");
                    }
                    BoxKt.Box(IndicationKt.indication(ClipKt.clip(LayoutIdKt.layoutId(Modifier.INSTANCE, "indicatorRipple"), ShapesKt.getValue(NavigationBarTokens.INSTANCE.getItemActiveIndicatorShape(), $composer3, 6)), offsetInteractionSource, RippleKt.m2847rippleH2RKhps$default(false, 0.0f, 0L, 7, null)), $composer3, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, $composer2, 54);
            NavigationBarItemColors colors7 = colors5;
            Function2 indicator = ComposableLambdaKt.rememberComposableLambda(-799524251, true, new NavigationBarKt$NavigationBarItem$2$indicator$1(stateAnimateFloatAsState, colors7), $composer2, 54);
            ComposerKt.sourceInformationMarkerStart($composer2, 1196514300, "CC(remember):NavigationBar.kt#9igjgp");
            boolean invalid$iv2 = $composer2.changed(stateAnimateFloatAsState);
            Object it$iv4 = $composer2.rememberedValue();
            if (invalid$iv2 || it$iv4 == Composer.INSTANCE.getEmpty()) {
                Object value$iv5 = new Function0() { // from class: androidx.compose.material3.NavigationBarKt$$ExternalSyntheticLambda4
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return Float.valueOf(((Number) stateAnimateFloatAsState.getValue()).floatValue());
                    }
                };
                $composer2.updateRememberedValue(value$iv5);
                it$iv4 = value$iv5;
            }
            Function0 function04 = (Function0) it$iv4;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerStart($composer2, 1196516539, "CC(remember):NavigationBar.kt#9igjgp");
            boolean invalid$iv3 = $composer2.changed(stateAnimateFloatAsState2);
            Object it$iv5 = $composer2.rememberedValue();
            if (invalid$iv3 || it$iv5 == Composer.INSTANCE.getEmpty()) {
                Object value$iv6 = new Function0() { // from class: androidx.compose.material3.NavigationBarKt$$ExternalSyntheticLambda5
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return Float.valueOf(((Number) stateAnimateFloatAsState2.getValue()).floatValue());
                    }
                };
                $composer2.updateRememberedValue(value$iv6);
                it$iv5 = value$iv6;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            NavigationBarItemLayout(indicatorRipple, indicator, styledIcon, styledLabel, alwaysShowLabel5, function04, (Function0) it$iv5, $composer2, (($dirty2 >> 9) & 57344) | 438);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            colors2 = colors7;
            interactionSource2 = interactionSource3;
            alwaysShowLabel3 = alwaysShowLabel5;
            function24 = function27;
            modifier2 = modifier4;
            enabled3 = enabled6;
        } else {
            $composer2.skipToGroupEnd();
            interactionSource2 = interactionSource;
            enabled3 = enabled2;
            alwaysShowLabel3 = alwaysShowLabel2;
            function24 = function23;
            colors2 = colors;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.NavigationBarKt$$ExternalSyntheticLambda6
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return NavigationBarKt.NavigationBarItem$lambda$16($this$NavigationBarItem, selected, function0, function2, modifier2, enabled3, function24, alwaysShowLabel3, colors2, interactionSource2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final int NavigationBarItem$lambda$5(MutableIntState $itemWidth$delegate) {
        MutableIntState $this$getValue$iv = $itemWidth$delegate;
        return $this$getValue$iv.getIntValue();
    }

    static final Unit NavigationBarItem$lambda$8$lambda$7(MutableIntState $itemWidth$delegate, IntSize it) {
        long arg0$iv = it.m8325unboximpl();
        $itemWidth$delegate.setIntValue((int) (arg0$iv >> 32));
        return Unit.INSTANCE;
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
    private static final void NavigationBarItemLayout(final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r54, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r55, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r56, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r57, final boolean r58, final kotlin.jvm.functions.Function0<java.lang.Float> r59, final kotlin.jvm.functions.Function0<java.lang.Float> r60, androidx.compose.runtime.Composer r61, final int r62) {
        /*
            Method dump skipped, instruction units count: 1251
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.NavigationBarKt.NavigationBarItemLayout(kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, boolean, kotlin.jvm.functions.Function0, kotlin.jvm.functions.Function0, androidx.compose.runtime.Composer, int):void");
    }

    static final Unit NavigationBarItemLayout$lambda$22$lambda$20$lambda$19(boolean $alwaysShowLabel, Function0 $alphaAnimationProgress, GraphicsLayerScope $this$graphicsLayer) {
        $this$graphicsLayer.setAlpha($alwaysShowLabel ? 1.0f : ((Number) $alphaAnimationProgress.invoke()).floatValue());
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: placeIcon-X9ElhV4, reason: not valid java name */
    public static final MeasureResult m2722placeIconX9ElhV4(MeasureScope $this$placeIcon_u2dX9ElhV4, final Placeable iconPlaceable, final Placeable indicatorRipplePlaceable, final Placeable indicatorPlaceable, long constraints) {
        int iM8103getMaxWidthimpl;
        if (Constraints.m8103getMaxWidthimpl(constraints) == Integer.MAX_VALUE) {
            iM8103getMaxWidthimpl = iconPlaceable.getWidth() + ($this$placeIcon_u2dX9ElhV4.mo426roundToPx0680j_4(NavigationBarItemToIconMinimumPadding) * 2);
        } else {
            iM8103getMaxWidthimpl = Constraints.m8103getMaxWidthimpl(constraints);
        }
        final int width = iM8103getMaxWidthimpl;
        final int height = ConstraintsKt.m8119constrainHeightK40F9xA(constraints, $this$placeIcon_u2dX9ElhV4.mo426roundToPx0680j_4(NavigationBarHeight));
        final int iconX = (width - iconPlaceable.getWidth()) / 2;
        final int iconY = (height - iconPlaceable.getHeight()) / 2;
        final int rippleX = (width - indicatorRipplePlaceable.getWidth()) / 2;
        final int rippleY = (height - indicatorRipplePlaceable.getHeight()) / 2;
        return MeasureScope.layout$default($this$placeIcon_u2dX9ElhV4, width, height, null, new Function1() { // from class: androidx.compose.material3.NavigationBarKt$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return NavigationBarKt.placeIcon_X9ElhV4$lambda$25(indicatorPlaceable, iconPlaceable, iconX, iconY, indicatorRipplePlaceable, rippleX, rippleY, width, height, (Placeable.PlacementScope) obj);
            }
        }, 4, null);
    }

    static final Unit placeIcon_X9ElhV4$lambda$25(Placeable $indicatorPlaceable, Placeable $iconPlaceable, int $iconX, int $iconY, Placeable $indicatorRipplePlaceable, int $rippleX, int $rippleY, int $width, int $height, Placeable.PlacementScope $this$layout) {
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
    public static final MeasureResult m2723placeLabelAndIconzUg2_y0(final MeasureScope $this$placeLabelAndIcon_u2dzUg2_y0, final Placeable labelPlaceable, final Placeable iconPlaceable, final Placeable indicatorRipplePlaceable, final Placeable indicatorPlaceable, long constraints, final boolean alwaysShowLabel, final float animationProgress) {
        float contentHeight = iconPlaceable.getHeight() + $this$placeLabelAndIcon_u2dzUg2_y0.mo432toPx0680j_4(IndicatorVerticalPadding) + $this$placeLabelAndIcon_u2dzUg2_y0.mo432toPx0680j_4(NavigationBarIndicatorToLabelPadding) + labelPlaceable.getHeight();
        final float contentVerticalPadding = RangesKt.coerceAtLeast((Constraints.m8104getMinHeightimpl(constraints) - contentHeight) / 2.0f, $this$placeLabelAndIcon_u2dzUg2_y0.mo432toPx0680j_4(IndicatorVerticalPadding));
        float height = contentHeight + (contentVerticalPadding * 2.0f);
        float unselectedIconY = alwaysShowLabel ? contentVerticalPadding : (height - iconPlaceable.getHeight()) / 2.0f;
        float iconDistance = unselectedIconY - contentVerticalPadding;
        final float offset = iconDistance * (1.0f - animationProgress);
        final float labelY = iconPlaceable.getHeight() + contentVerticalPadding + $this$placeLabelAndIcon_u2dzUg2_y0.mo432toPx0680j_4(IndicatorVerticalPadding) + $this$placeLabelAndIcon_u2dzUg2_y0.mo432toPx0680j_4(NavigationBarIndicatorToLabelPadding);
        final int containerWidth = Constraints.m8103getMaxWidthimpl(constraints) == Integer.MAX_VALUE ? iconPlaceable.getWidth() + ($this$placeLabelAndIcon_u2dzUg2_y0.mo426roundToPx0680j_4(NavigationBarItemToIconMinimumPadding) * 2) : Constraints.m8103getMaxWidthimpl(constraints);
        final int labelX = (containerWidth - labelPlaceable.getWidth()) / 2;
        final int iconX = (containerWidth - iconPlaceable.getWidth()) / 2;
        final int rippleX = (containerWidth - indicatorRipplePlaceable.getWidth()) / 2;
        final float rippleY = contentVerticalPadding - $this$placeLabelAndIcon_u2dzUg2_y0.mo432toPx0680j_4(IndicatorVerticalPadding);
        return MeasureScope.layout$default($this$placeLabelAndIcon_u2dzUg2_y0, containerWidth, MathKt.roundToInt(height), null, new Function1() { // from class: androidx.compose.material3.NavigationBarKt$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return NavigationBarKt.placeLabelAndIcon_zUg2_y0$lambda$27(indicatorPlaceable, alwaysShowLabel, animationProgress, labelPlaceable, labelX, labelY, offset, iconPlaceable, iconX, contentVerticalPadding, indicatorRipplePlaceable, rippleX, rippleY, containerWidth, $this$placeLabelAndIcon_u2dzUg2_y0, (Placeable.PlacementScope) obj);
            }
        }, 4, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0037  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static final kotlin.Unit placeLabelAndIcon_zUg2_y0$lambda$27(androidx.compose.ui.layout.Placeable r17, boolean r18, float r19, androidx.compose.ui.layout.Placeable r20, int r21, float r22, float r23, androidx.compose.ui.layout.Placeable r24, int r25, float r26, androidx.compose.ui.layout.Placeable r27, int r28, float r29, int r30, androidx.compose.ui.layout.MeasureScope r31, androidx.compose.ui.layout.Placeable.PlacementScope r32) {
        /*
            if (r17 == 0) goto L28
            r1 = r17
            r7 = 0
            int r0 = r1.getWidth()
            int r0 = r30 - r0
            int r2 = r0 / 2
            float r0 = androidx.compose.material3.NavigationBarKt.IndicatorVerticalPadding
            r8 = r31
            int r0 = r8.mo426roundToPx0680j_4(r0)
            float r0 = (float) r0
            float r9 = r26 - r0
            float r0 = r9 + r23
            int r3 = kotlin.math.MathKt.roundToInt(r0)
            r5 = 4
            r6 = 0
            r4 = 0
            r0 = r32
            androidx.compose.ui.layout.Placeable.PlacementScope.placeRelative$default(r0, r1, r2, r3, r4, r5, r6)
            goto L2a
        L28:
            r8 = r31
        L2a:
            if (r18 != 0) goto L37
            r0 = 0
            int r0 = (r19 > r0 ? 1 : (r19 == r0 ? 0 : -1))
            if (r0 != 0) goto L34
            r0 = 1
            goto L35
        L34:
            r0 = 0
        L35:
            if (r0 != 0) goto L4a
        L37:
            float r0 = r22 + r23
            int r13 = kotlin.math.MathKt.roundToInt(r0)
            r15 = 4
            r16 = 0
            r14 = 0
            r11 = r20
            r12 = r21
            r10 = r32
            androidx.compose.ui.layout.Placeable.PlacementScope.placeRelative$default(r10, r11, r12, r13, r14, r15, r16)
        L4a:
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
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.NavigationBarKt.placeLabelAndIcon_zUg2_y0$lambda$27(androidx.compose.ui.layout.Placeable, boolean, float, androidx.compose.ui.layout.Placeable, int, float, float, androidx.compose.ui.layout.Placeable, int, float, androidx.compose.ui.layout.Placeable, int, float, int, androidx.compose.ui.layout.MeasureScope, androidx.compose.ui.layout.Placeable$PlacementScope):kotlin.Unit");
    }

    static {
        float arg0$iv = NavigationBarVerticalItemTokens.INSTANCE.m3988getActiveIndicatorWidthD9Ej5fM();
        float other$iv = NavigationBarVerticalItemTokens.INSTANCE.m3990getIconSizeD9Ej5fM();
        IndicatorHorizontalPadding = Dp.m8150constructorimpl(Dp.m8150constructorimpl(arg0$iv - other$iv) / 2);
        float arg0$iv2 = NavigationBarVerticalItemTokens.INSTANCE.m3987getActiveIndicatorHeightD9Ej5fM();
        float other$iv2 = NavigationBarVerticalItemTokens.INSTANCE.m3990getIconSizeD9Ej5fM();
        IndicatorVerticalPadding = Dp.m8150constructorimpl(Dp.m8150constructorimpl(arg0$iv2 - other$iv2) / 2);
        IndicatorVerticalOffset = Dp.m8150constructorimpl(12);
        NavigationBarItemToIconMinimumPadding = Dp.m8150constructorimpl(44);
        LocalNavigationBarOverride = CompositionLocalKt.compositionLocalOf$default(null, new Function0() { // from class: androidx.compose.material3.NavigationBarKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return DefaultNavigationBarOverride.INSTANCE;
            }
        }, 1, null);
    }

    public static final float getNavigationBarItemHorizontalPadding() {
        return NavigationBarItemHorizontalPadding;
    }

    public static final float getNavigationBarIndicatorToLabelPadding() {
        return NavigationBarIndicatorToLabelPadding;
    }

    public static final float getIndicatorVerticalPadding() {
        return IndicatorVerticalPadding;
    }

    public static final float getNavigationBarItemToIconMinimumPadding() {
        return NavigationBarItemToIconMinimumPadding;
    }

    public static final ProvidableCompositionLocal<NavigationBarOverride> getLocalNavigationBarOverride() {
        return LocalNavigationBarOverride;
    }
}
