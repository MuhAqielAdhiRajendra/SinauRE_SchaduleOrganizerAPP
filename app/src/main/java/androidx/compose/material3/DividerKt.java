package androidx.compose.material3;

import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.CanvasKt;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: Divider.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a-\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u0007¢\u0006\u0004\b\b\u0010\t\u001a-\u0010\n\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u0007¢\u0006\u0004\b\u000b\u0010\t\u001a-\u0010\f\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u0007¢\u0006\u0004\b\r\u0010\t¨\u0006\u000e"}, d2 = {"HorizontalDivider", "", "modifier", "Landroidx/compose/ui/Modifier;", "thickness", "Landroidx/compose/ui/unit/Dp;", TypedValues.Custom.S_COLOR, "Landroidx/compose/ui/graphics/Color;", "HorizontalDivider-9IZ8Weo", "(Landroidx/compose/ui/Modifier;FJLandroidx/compose/runtime/Composer;II)V", "VerticalDivider", "VerticalDivider-9IZ8Weo", "Divider", "Divider-9IZ8Weo", "material3"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class DividerKt {
    static final Unit Divider_9IZ8Weo$lambda$6(Modifier modifier, float f, long j, int i, int i2, Composer composer, int i3) {
        m2483Divider9IZ8Weo(modifier, f, j, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit HorizontalDivider_9IZ8Weo$lambda$2(Modifier modifier, float f, long j, int i, int i2, Composer composer, int i3) {
        m2484HorizontalDivider9IZ8Weo(modifier, f, j, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit VerticalDivider_9IZ8Weo$lambda$5(Modifier modifier, float f, long j, int i, int i2, Composer composer, int i3) {
        m2485VerticalDivider9IZ8Weo(modifier, f, j, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: HorizontalDivider-9IZ8Weo, reason: not valid java name */
    public static final void m2484HorizontalDivider9IZ8Weo(Modifier modifier, float thickness, long color, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        float f;
        final long color2;
        final float thickness2;
        final Modifier modifier3;
        final long color3;
        Modifier.Companion modifier4;
        final float thickness3;
        Composer $composer2 = $composer.startRestartGroup(75144485);
        ComposerKt.sourceInformation($composer2, "C(HorizontalDivider)N(modifier,thickness:c#ui.unit.Dp,color:c#ui.graphics.Color)53@2086L220,53@2036L270:Divider.kt#uh7d8r");
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
            f = thickness;
        } else if (($changed & 48) == 0) {
            f = thickness;
            $dirty |= $composer2.changed(f) ? 32 : 16;
        } else {
            f = thickness;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                color2 = color;
                int i4 = $composer2.changed(color2) ? 256 : 128;
                $dirty |= i4;
            } else {
                color2 = color;
            }
            $dirty |= i4;
        } else {
            color2 = color;
        }
        boolean z = true;
        if ($composer2.shouldExecute(($dirty & 147) != 146, $dirty & 1)) {
            $composer2.startDefaults();
            ComposerKt.sourceInformation($composer2, "51@2021L5");
            if (($changed & 1) == 0 || $composer2.getDefaultsInvalid()) {
                modifier4 = i2 != 0 ? Modifier.INSTANCE : modifier2;
                thickness3 = i3 != 0 ? DividerDefaults.INSTANCE.m2482getThicknessD9Ej5fM() : f;
                if ((i & 4) != 0) {
                    $dirty &= -897;
                    color2 = DividerDefaults.INSTANCE.getColor($composer2, 6);
                }
            } else {
                $composer2.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty &= -897;
                }
                modifier4 = modifier2;
                thickness3 = f;
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(75144485, $dirty, -1, "androidx.compose.material3.HorizontalDivider (Divider.kt:53)");
            }
            Modifier modifierM1101height3ABfNKs = SizeKt.m1101height3ABfNKs(SizeKt.fillMaxWidth$default(modifier4, 0.0f, 1, null), thickness3);
            ComposerKt.sourceInformationMarkerStart($composer2, -800586783, "CC(remember):Divider.kt#9igjgp");
            boolean z2 = ($dirty & 112) == 32;
            if (((($dirty & 896) ^ 384) <= 256 || !$composer2.changed(color2)) && ($dirty & 384) != 256) {
                z = false;
            }
            boolean invalid$iv = z2 | z;
            Object it$iv = $composer2.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = new Function1() { // from class: androidx.compose.material3.DividerKt$$ExternalSyntheticLambda3
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return DividerKt.HorizontalDivider_9IZ8Weo$lambda$1$lambda$0(thickness3, color2, (DrawScope) obj);
                    }
                };
                $composer2.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            CanvasKt.Canvas(modifierM1101height3ABfNKs, (Function1) it$iv, $composer2, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier4;
            thickness2 = thickness3;
            color3 = color2;
        } else {
            $composer2.skipToGroupEnd();
            thickness2 = f;
            modifier3 = modifier2;
            color3 = color2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.DividerKt$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return DividerKt.HorizontalDivider_9IZ8Weo$lambda$2(modifier3, thickness2, color3, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    static final Unit HorizontalDivider_9IZ8Weo$lambda$1$lambda$0(float $thickness, long $color, DrawScope $this$Canvas) {
        float f = $this$Canvas.mo432toPx0680j_4($thickness);
        float y$iv = $this$Canvas.mo432toPx0680j_4($thickness) / 2.0f;
        long v1$iv$iv = Float.floatToRawIntBits(0.0f);
        long v2$iv$iv = Float.floatToRawIntBits(y$iv);
        long jM5060constructorimpl = Offset.m5060constructorimpl((v1$iv$iv << 32) | (v2$iv$iv & 4294967295L));
        long arg0$iv = $this$Canvas.mo5887getSizeNHjbRc();
        int bits$iv$iv$iv = (int) (arg0$iv >> 32);
        float x$iv = Float.intBitsToFloat(bits$iv$iv$iv);
        float y$iv2 = $this$Canvas.mo432toPx0680j_4($thickness) / 2.0f;
        long v1$iv$iv2 = Float.floatToRawIntBits(x$iv);
        long v2$iv$iv2 = Float.floatToRawIntBits(y$iv2);
        DrawScope.m5873drawLineNGM6Ib0$default($this$Canvas, $color, jM5060constructorimpl, Offset.m5060constructorimpl((v1$iv$iv2 << 32) | (v2$iv$iv2 & 4294967295L)), f, 0, null, 0.0f, null, 0, 496, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: VerticalDivider-9IZ8Weo, reason: not valid java name */
    public static final void m2485VerticalDivider9IZ8Weo(Modifier modifier, float thickness, long color, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        float f;
        final long color2;
        final float thickness2;
        final Modifier modifier3;
        final long color3;
        Modifier.Companion modifier4;
        final float thickness3;
        Composer $composer2 = $composer.startRestartGroup(-1534852205);
        ComposerKt.sourceInformation($composer2, "C(VerticalDivider)N(modifier,thickness:c#ui.unit.Dp,color:c#ui.graphics.Color)81@3058L221,81@3008L271:Divider.kt#uh7d8r");
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
            f = thickness;
        } else if (($changed & 48) == 0) {
            f = thickness;
            $dirty |= $composer2.changed(f) ? 32 : 16;
        } else {
            f = thickness;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                color2 = color;
                int i4 = $composer2.changed(color2) ? 256 : 128;
                $dirty |= i4;
            } else {
                color2 = color;
            }
            $dirty |= i4;
        } else {
            color2 = color;
        }
        boolean z = true;
        if ($composer2.shouldExecute(($dirty & 147) != 146, $dirty & 1)) {
            $composer2.startDefaults();
            ComposerKt.sourceInformation($composer2, "79@2993L5");
            if (($changed & 1) == 0 || $composer2.getDefaultsInvalid()) {
                modifier4 = i2 != 0 ? Modifier.INSTANCE : modifier2;
                thickness3 = i3 != 0 ? DividerDefaults.INSTANCE.m2482getThicknessD9Ej5fM() : f;
                if ((i & 4) != 0) {
                    $dirty &= -897;
                    color2 = DividerDefaults.INSTANCE.getColor($composer2, 6);
                }
            } else {
                $composer2.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty &= -897;
                }
                modifier4 = modifier2;
                thickness3 = f;
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1534852205, $dirty, -1, "androidx.compose.material3.VerticalDivider (Divider.kt:81)");
            }
            Modifier modifierM1120width3ABfNKs = SizeKt.m1120width3ABfNKs(SizeKt.fillMaxHeight$default(modifier4, 0.0f, 1, null), thickness3);
            ComposerKt.sourceInformationMarkerStart($composer2, -1819179376, "CC(remember):Divider.kt#9igjgp");
            boolean z2 = ($dirty & 112) == 32;
            if (((($dirty & 896) ^ 384) <= 256 || !$composer2.changed(color2)) && ($dirty & 384) != 256) {
                z = false;
            }
            boolean invalid$iv = z2 | z;
            Object it$iv = $composer2.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = new Function1() { // from class: androidx.compose.material3.DividerKt$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return DividerKt.VerticalDivider_9IZ8Weo$lambda$4$lambda$3(thickness3, color2, (DrawScope) obj);
                    }
                };
                $composer2.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            CanvasKt.Canvas(modifierM1120width3ABfNKs, (Function1) it$iv, $composer2, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier4;
            thickness2 = thickness3;
            color3 = color2;
        } else {
            $composer2.skipToGroupEnd();
            thickness2 = f;
            modifier3 = modifier2;
            color3 = color2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.DividerKt$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return DividerKt.VerticalDivider_9IZ8Weo$lambda$5(modifier3, thickness2, color3, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    static final Unit VerticalDivider_9IZ8Weo$lambda$4$lambda$3(float $thickness, long $color, DrawScope $this$Canvas) {
        float f = $this$Canvas.mo432toPx0680j_4($thickness);
        float x$iv = $this$Canvas.mo432toPx0680j_4($thickness) / 2.0f;
        long v1$iv$iv = Float.floatToRawIntBits(x$iv);
        long v2$iv$iv = Float.floatToRawIntBits(0.0f);
        long jM5060constructorimpl = Offset.m5060constructorimpl((v1$iv$iv << 32) | (v2$iv$iv & 4294967295L));
        float x$iv2 = $this$Canvas.mo432toPx0680j_4($thickness) / 2.0f;
        long arg0$iv = $this$Canvas.mo5887getSizeNHjbRc();
        int bits$iv$iv$iv = (int) (arg0$iv & 4294967295L);
        float y$iv = Float.intBitsToFloat(bits$iv$iv$iv);
        long v1$iv$iv2 = Float.floatToRawIntBits(x$iv2);
        long v2$iv$iv2 = Float.floatToRawIntBits(y$iv);
        DrawScope.m5873drawLineNGM6Ib0$default($this$Canvas, $color, jM5060constructorimpl, Offset.m5060constructorimpl((v1$iv$iv2 << 32) | (v2$iv$iv2 & 4294967295L)), f, 0, null, 0.0f, null, 0, 496, null);
        return Unit.INSTANCE;
    }

    @Deprecated(message = "Renamed to HorizontalDivider", replaceWith = @ReplaceWith(expression = "HorizontalDivider(modifier, thickness, color)", imports = {}))
    /* JADX INFO: renamed from: Divider-9IZ8Weo, reason: not valid java name */
    public static final void m2483Divider9IZ8Weo(Modifier modifier, float thickness, long color, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        float f;
        long color2;
        final Modifier modifier3;
        final long color3;
        final float thickness2;
        Modifier.Companion modifier4;
        float thickness3;
        long color4;
        float targetThickness;
        Composer $composer2 = $composer.startRestartGroup(1562471785);
        ComposerKt.sourceInformation($composer2, "C(Divider)N(modifier,thickness:c#ui.unit.Dp,color:c#ui.graphics.Color)106@3745L78:Divider.kt#uh7d8r");
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
            f = thickness;
        } else if (($changed & 48) == 0) {
            f = thickness;
            $dirty |= $composer2.changed(f) ? 32 : 16;
        } else {
            f = thickness;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                color2 = color;
                int i4 = $composer2.changed(color2) ? 256 : 128;
                $dirty |= i4;
            } else {
                color2 = color;
            }
            $dirty |= i4;
        } else {
            color2 = color;
        }
        if (!$composer2.shouldExecute(($dirty & 147) != 146, $dirty & 1)) {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            color3 = color2;
            thickness2 = f;
        } else {
            $composer2.startDefaults();
            ComposerKt.sourceInformation($composer2, "98@3564L5");
            if (($changed & 1) != 0 && !$composer2.getDefaultsInvalid()) {
                $composer2.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty &= -897;
                }
                modifier4 = modifier2;
                thickness3 = f;
                color4 = color2;
            } else {
                if (i2 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if (i3 == 0) {
                    thickness3 = f;
                } else {
                    thickness3 = DividerDefaults.INSTANCE.m2482getThicknessD9Ej5fM();
                }
                if ((i & 4) == 0) {
                    color4 = color2;
                } else {
                    $dirty &= -897;
                    color4 = DividerDefaults.INSTANCE.getColor($composer2, 6);
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1562471785, $dirty, -1, "androidx.compose.material3.Divider (Divider.kt:99)");
            }
            if (Dp.m8155equalsimpl0(thickness3, Dp.INSTANCE.m8168getHairlineD9Ej5fM())) {
                $composer2.startReplaceGroup(-1258250053);
                ComposerKt.sourceInformation($composer2, "102@3672L7");
                ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
                ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                Object objConsume = $composer2.consume(localDensity);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                float $this$dp$iv = 1.0f / ((Density) objConsume).get_density();
                targetThickness = Dp.m8150constructorimpl($this$dp$iv);
                $composer2.endReplaceGroup();
            } else {
                $composer2.startReplaceGroup(-1258183496);
                $composer2.endReplaceGroup();
                targetThickness = thickness3;
            }
            BoxKt.Box(BackgroundKt.m286backgroundbw27NRU$default(SizeKt.m1101height3ABfNKs(SizeKt.fillMaxWidth$default(modifier4, 0.0f, 1, null), targetThickness), color4, null, 2, null), $composer2, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier4;
            thickness2 = thickness3;
            color3 = color4;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.DividerKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return DividerKt.Divider_9IZ8Weo$lambda$6(modifier3, thickness2, color3, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }
}
