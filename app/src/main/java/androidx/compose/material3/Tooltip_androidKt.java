package androidx.compose.material3;

import androidx.compose.material3.tokens.ElevationTokens;
import androidx.compose.material3.tokens.RichTooltipTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.composer.linkbuffer.GroupFlagsKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.DpSize;
import androidx.profileinstaller.ProfileVerifier;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: Tooltip.android.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000B\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\u001al\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\r2\u0011\u0010\u000f\u001a\r\u0012\u0004\u0012\u00020\u00010\u0010¢\u0006\u0002\b\u0011H\u0007¢\u0006\u0004\b\u0012\u0010\u0013\u001av\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0014\u001a\u00020\r2\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\r2\u0011\u0010\u000f\u001a\r\u0012\u0004\u0012\u00020\u00010\u0010¢\u0006\u0002\b\u0011H\u0007¢\u0006\u0004\b\u0012\u0010\u0015\u001a\u0090\u0001\u0010\u0016\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u00042\u0015\b\u0002\u0010\u0017\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0010¢\u0006\u0002\b\u00112\u0015\b\u0002\u0010\u0018\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0010¢\u0006\u0002\b\u00112\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\u0019\u001a\u00020\u001a2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\r2\u0011\u0010\u001b\u001a\r\u0012\u0004\u0012\u00020\u00010\u0010¢\u0006\u0002\b\u0011H\u0007¢\u0006\u0004\b\u001c\u0010\u001d\u001a\u009a\u0001\u0010\u0016\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u00042\u0015\b\u0002\u0010\u0017\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0010¢\u0006\u0002\b\u00112\u0015\b\u0002\u0010\u0018\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0010¢\u0006\u0002\b\u00112\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0014\u001a\u00020\r2\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\u0019\u001a\u00020\u001a2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\r2\u0011\u0010\u001b\u001a\r\u0012\u0004\u0012\u00020\u00010\u0010¢\u0006\u0002\b\u0011H\u0007¢\u0006\u0004\b\u001c\u0010\u001e¨\u0006\u001f"}, d2 = {"PlainTooltipAndroid", "", "Landroidx/compose/material3/TooltipScope;", "modifier", "Landroidx/compose/ui/Modifier;", "caretSize", "Landroidx/compose/ui/unit/DpSize;", "shape", "Landroidx/compose/ui/graphics/Shape;", "contentColor", "Landroidx/compose/ui/graphics/Color;", "containerColor", "tonalElevation", "Landroidx/compose/ui/unit/Dp;", "shadowElevation", "content", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "PlainTooltip", "(Landroidx/compose/material3/TooltipScope;Landroidx/compose/ui/Modifier;JLandroidx/compose/ui/graphics/Shape;JJFFLkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "maxWidth", "(Landroidx/compose/material3/TooltipScope;Landroidx/compose/ui/Modifier;JFLandroidx/compose/ui/graphics/Shape;JJFFLkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "RichTooltipAndroid", "title", "action", "colors", "Landroidx/compose/material3/RichTooltipColors;", "text", "RichTooltip", "(Landroidx/compose/material3/TooltipScope;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;JLandroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/RichTooltipColors;FFLkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "(Landroidx/compose/material3/TooltipScope;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;JFLandroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/RichTooltipColors;FFLkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;III)V", "material3"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class Tooltip_androidKt {
    static final Unit PlainTooltipAndroid_7QI4Sbk$lambda$0(TooltipScope tooltipScope, Modifier modifier, long j, Shape shape, long j2, long j3, float f, float f2, Function2 function2, int i, int i2, Composer composer, int i3) {
        PlainTooltip(tooltipScope, modifier, j, shape, j2, j3, f, f2, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit PlainTooltipAndroid_m9Er_Xc$lambda$1(TooltipScope tooltipScope, Modifier modifier, long j, float f, Shape shape, long j2, long j3, float f2, float f3, Function2 function2, int i, int i2, Composer composer, int i3) {
        PlainTooltip(tooltipScope, modifier, j, f, shape, j2, j3, f2, f3, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit RichTooltipAndroid_ZuUcA3Q$lambda$3(TooltipScope tooltipScope, Modifier modifier, Function2 function2, Function2 function22, long j, float f, Shape shape, RichTooltipColors richTooltipColors, float f2, float f3, Function2 function23, int i, int i2, int i3, Composer composer, int i4) {
        RichTooltip(tooltipScope, modifier, function2, function22, j, f, shape, richTooltipColors, f2, f3, function23, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
        return Unit.INSTANCE;
    }

    static final Unit RichTooltipAndroid_yDvdmqw$lambda$2(TooltipScope tooltipScope, Modifier modifier, Function2 function2, Function2 function22, long j, Shape shape, RichTooltipColors richTooltipColors, float f, float f2, Function2 function23, int i, int i2, Composer composer, int i3) {
        RichTooltip(tooltipScope, modifier, function2, function22, j, shape, richTooltipColors, f, f2, function23, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility. Use overload with maxWidth parameter.")
    public static final /* synthetic */ void PlainTooltip(final TooltipScope $this$PlainTooltipAndroid_u2d7QI4Sbk, Modifier modifier, long caretSize, Shape shape, long contentColor, long containerColor, float tonalElevation, float shadowElevation, final Function2 content, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        long caretSize2;
        Shape shape2;
        long contentColor2;
        long containerColor2;
        float tonalElevation2;
        Composer $composer2;
        final float shadowElevation2;
        final long caretSize3;
        final long contentColor3;
        final float shadowElevation3;
        final Modifier modifier3;
        final Shape shape3;
        final long containerColor3;
        int $dirty;
        Modifier modifier4;
        Shape shape4;
        float shadowElevation4;
        float tonalElevation3;
        long caretSize4;
        long contentColor4;
        long containerColor4;
        Composer $composer3 = $composer.startRestartGroup(2114904198);
        ComposerKt.sourceInformation($composer3, "C(PlainTooltipAndroid)N(modifier,caretSize:c#ui.unit.DpSize,shape,contentColor:c#ui.graphics.Color,containerColor:c#ui.graphics.Color,tonalElevation:c#ui.unit.Dp,shadowElevation:c#ui.unit.Dp,content)61@2558L377:Tooltip.android.kt#uh7d8r");
        int $dirty2 = $changed;
        if ((Integer.MIN_VALUE & i) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty2 |= ($changed & 8) == 0 ? $composer3.changed($this$PlainTooltipAndroid_u2d7QI4Sbk) : $composer3.changedInstance($this$PlainTooltipAndroid_u2d7QI4Sbk) ? 4 : 2;
        }
        int i2 = i & 1;
        if (i2 != 0) {
            $dirty2 |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer3.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 384) == 0) {
            if ((i & 2) == 0) {
                caretSize2 = caretSize;
                int i3 = $composer3.changed(caretSize2) ? 256 : 128;
                $dirty2 |= i3;
            } else {
                caretSize2 = caretSize;
            }
            $dirty2 |= i3;
        } else {
            caretSize2 = caretSize;
        }
        if (($changed & 3072) == 0) {
            if ((i & 4) == 0) {
                shape2 = shape;
                int i4 = $composer3.changed(shape2) ? 2048 : 1024;
                $dirty2 |= i4;
            } else {
                shape2 = shape;
            }
            $dirty2 |= i4;
        } else {
            shape2 = shape;
        }
        if (($changed & 24576) == 0) {
            if ((i & 8) == 0) {
                contentColor2 = contentColor;
                int i5 = $composer3.changed(contentColor2) ? 16384 : 8192;
                $dirty2 |= i5;
            } else {
                contentColor2 = contentColor;
            }
            $dirty2 |= i5;
        } else {
            contentColor2 = contentColor;
        }
        if ((196608 & $changed) == 0) {
            if ((i & 16) == 0) {
                containerColor2 = containerColor;
                int i6 = $composer3.changed(containerColor2) ? 131072 : 65536;
                $dirty2 |= i6;
            } else {
                containerColor2 = containerColor;
            }
            $dirty2 |= i6;
        } else {
            containerColor2 = containerColor;
        }
        int i7 = i & 32;
        if (i7 != 0) {
            $dirty2 |= 1572864;
            tonalElevation2 = tonalElevation;
        } else if (($changed & 1572864) == 0) {
            tonalElevation2 = tonalElevation;
            $dirty2 |= $composer3.changed(tonalElevation2) ? 1048576 : 524288;
        } else {
            tonalElevation2 = tonalElevation;
        }
        int i8 = i & 64;
        if (i8 != 0) {
            $dirty2 |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty2 |= $composer3.changed(shadowElevation) ? 8388608 : 4194304;
        }
        if ((i & 128) != 0) {
            $dirty2 |= 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty2 |= $composer3.changedInstance(content) ? 67108864 : GroupFlagsKt.HasAuxSlotFlag;
        }
        if ($composer3.shouldExecute(($dirty2 & 38347923) != 38347922, $dirty2 & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "54@2282L26,55@2352L24,56@2422L26");
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                if (i2 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if ((i & 2) != 0) {
                    caretSize2 = DpSize.INSTANCE.m8257getUnspecifiedMYxV2XQ();
                    $dirty2 &= -897;
                }
                if ((i & 4) != 0) {
                    $dirty2 &= -7169;
                    shape2 = TooltipDefaults.INSTANCE.getPlainTooltipContainerShape($composer3, 6);
                }
                if ((i & 8) != 0) {
                    contentColor2 = TooltipDefaults.INSTANCE.getPlainTooltipContentColor($composer3, 6);
                    $dirty2 &= -57345;
                }
                if ((i & 16) != 0) {
                    containerColor2 = TooltipDefaults.INSTANCE.getPlainTooltipContainerColor($composer3, 6);
                    $dirty2 &= -458753;
                }
                if (i7 != 0) {
                    tonalElevation2 = Dp.m8150constructorimpl(0);
                }
                if (i8 != 0) {
                    modifier4 = modifier2;
                    shape4 = shape2;
                    long j = contentColor2;
                    shadowElevation4 = Dp.m8150constructorimpl(0);
                    tonalElevation3 = tonalElevation2;
                    $dirty = $dirty2;
                    caretSize4 = caretSize2;
                    contentColor4 = j;
                    containerColor4 = containerColor2;
                } else {
                    $dirty = $dirty2;
                    modifier4 = modifier2;
                    shape4 = shape2;
                    long j2 = contentColor2;
                    shadowElevation4 = shadowElevation;
                    tonalElevation3 = tonalElevation2;
                    caretSize4 = caretSize2;
                    contentColor4 = j2;
                    containerColor4 = containerColor2;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 2) != 0) {
                    $dirty2 &= -897;
                }
                if ((i & 4) != 0) {
                    $dirty2 &= -7169;
                }
                if ((i & 8) != 0) {
                    $dirty2 &= -57345;
                }
                if ((i & 16) != 0) {
                    $dirty = $dirty2 & (-458753);
                    modifier4 = modifier2;
                    shape4 = shape2;
                    long j3 = contentColor2;
                    shadowElevation4 = shadowElevation;
                    tonalElevation3 = tonalElevation2;
                    caretSize4 = caretSize2;
                    contentColor4 = j3;
                    containerColor4 = containerColor2;
                } else {
                    $dirty = $dirty2;
                    modifier4 = modifier2;
                    shape4 = shape2;
                    long j4 = contentColor2;
                    shadowElevation4 = shadowElevation;
                    tonalElevation3 = tonalElevation2;
                    caretSize4 = caretSize2;
                    contentColor4 = j4;
                    containerColor4 = containerColor2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2114904198, $dirty, -1, "androidx.compose.material3.PlainTooltipAndroid (Tooltip.android.kt:61)");
            }
            long caretSize5 = caretSize4;
            TooltipKt.m3339PlainTooltipgv3ox5I($this$PlainTooltipAndroid_u2d7QI4Sbk, modifier4, TooltipDefaults.INSTANCE.m3330caretShapeEaSLcWc(caretSize4), TooltipDefaults.INSTANCE.m3332getPlainTooltipMaxWidthD9Ej5fM(), shape4, contentColor4, containerColor4, tonalElevation3, shadowElevation4, content, $composer3, ($dirty & 14) | 3072 | ($dirty & 112) | (($dirty << 3) & 57344) | (($dirty << 3) & 458752) | (($dirty << 3) & 3670016) | (($dirty << 3) & 29360128) | (($dirty << 3) & 234881024) | (($dirty << 3) & 1879048192), 0);
            $composer2 = $composer3;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier4;
            shadowElevation2 = shadowElevation4;
            shadowElevation3 = tonalElevation3;
            containerColor3 = containerColor4;
            contentColor3 = contentColor4;
            shape3 = shape4;
            caretSize3 = caretSize5;
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            shadowElevation2 = shadowElevation;
            caretSize3 = caretSize2;
            contentColor3 = contentColor2;
            shadowElevation3 = tonalElevation2;
            modifier3 = modifier2;
            shape3 = shape2;
            containerColor3 = containerColor2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.Tooltip_androidKt$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return Tooltip_androidKt.PlainTooltipAndroid_7QI4Sbk$lambda$0($this$PlainTooltipAndroid_u2d7QI4Sbk, modifier3, caretSize3, shape3, contentColor3, containerColor3, shadowElevation3, shadowElevation2, content, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility.")
    public static final /* synthetic */ void PlainTooltip(final TooltipScope $this$PlainTooltipAndroid_u2dm9Er_u2dXc, Modifier modifier, long caretSize, float maxWidth, Shape shape, long contentColor, long containerColor, float tonalElevation, float shadowElevation, final Function2 content, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        long j;
        float f;
        Shape shape2;
        long j2;
        int $dirty;
        final float tonalElevation2;
        int i2;
        Composer $composer2;
        final float shadowElevation2;
        final Modifier modifier3;
        final long caretSize2;
        final float maxWidth2;
        final Shape shape3;
        final long containerColor2;
        final long containerColor3;
        Modifier.Companion modifier4;
        long caretSize3;
        float maxWidth3;
        Shape shape4;
        long contentColor2;
        long containerColor4;
        float tonalElevation3;
        long caretSize4;
        Shape shape5;
        long containerColor5;
        int $dirty2;
        float shadowElevation3;
        float shadowElevation4;
        int $dirty3;
        Composer $composer3 = $composer.startRestartGroup(1456881596);
        ComposerKt.sourceInformation($composer3, "C(PlainTooltipAndroid)N(modifier,caretSize:c#ui.unit.DpSize,maxWidth:c#ui.unit.Dp,shape,contentColor:c#ui.graphics.Color,containerColor:c#ui.graphics.Color,tonalElevation:c#ui.unit.Dp,shadowElevation:c#ui.unit.Dp,content)105@4515L349:Tooltip.android.kt#uh7d8r");
        int $dirty4 = $changed;
        if ((Integer.MIN_VALUE & i) != 0) {
            $dirty4 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty4 |= ($changed & 8) == 0 ? $composer3.changed($this$PlainTooltipAndroid_u2dm9Er_u2dXc) : $composer3.changedInstance($this$PlainTooltipAndroid_u2dm9Er_u2dXc) ? 4 : 2;
        }
        int i3 = i & 1;
        if (i3 != 0) {
            $dirty4 |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty4 |= $composer3.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 384) == 0) {
            if ((i & 2) == 0) {
                j = caretSize;
                int i4 = $composer3.changed(j) ? 256 : 128;
                $dirty4 |= i4;
            } else {
                j = caretSize;
            }
            $dirty4 |= i4;
        } else {
            j = caretSize;
        }
        int i5 = i & 4;
        if (i5 != 0) {
            $dirty4 |= 3072;
            f = maxWidth;
        } else if (($changed & 3072) == 0) {
            f = maxWidth;
            $dirty4 |= $composer3.changed(f) ? 2048 : 1024;
        } else {
            f = maxWidth;
        }
        if (($changed & 24576) == 0) {
            if ((i & 8) == 0) {
                shape2 = shape;
                int i6 = $composer3.changed(shape2) ? 16384 : 8192;
                $dirty4 |= i6;
            } else {
                shape2 = shape;
            }
            $dirty4 |= i6;
        } else {
            shape2 = shape;
        }
        if ((196608 & $changed) == 0) {
            if ((i & 16) == 0) {
                j2 = contentColor;
                int i7 = $composer3.changed(j2) ? 131072 : 65536;
                $dirty4 |= i7;
            } else {
                j2 = contentColor;
            }
            $dirty4 |= i7;
        } else {
            j2 = contentColor;
        }
        if ((1572864 & $changed) == 0) {
            if ((i & 32) == 0) {
                $dirty3 = $dirty4;
                int i8 = $composer3.changed(containerColor) ? 1048576 : 524288;
                $dirty = $dirty3 | i8;
            } else {
                $dirty3 = $dirty4;
            }
            $dirty = $dirty3 | i8;
        } else {
            $dirty = $dirty4;
        }
        int i9 = i & 64;
        if (i9 != 0) {
            $dirty |= 12582912;
            tonalElevation2 = tonalElevation;
        } else if (($changed & 12582912) == 0) {
            tonalElevation2 = tonalElevation;
            $dirty |= $composer3.changed(tonalElevation2) ? 8388608 : 4194304;
        } else {
            tonalElevation2 = tonalElevation;
        }
        int i10 = i & 128;
        if (i10 != 0) {
            $dirty |= 100663296;
            i2 = i10;
        } else if (($changed & 100663296) == 0) {
            i2 = i10;
            $dirty |= $composer3.changed(shadowElevation) ? 67108864 : GroupFlagsKt.HasAuxSlotFlag;
        } else {
            i2 = i10;
        }
        int i11 = 805306368;
        if ((i & 256) != 0) {
            $dirty |= i11;
        } else if (($changed & 805306368) == 0) {
            i11 = $composer3.changedInstance(content) ? GroupFlagsKt.HasMovableContentFlag : GroupFlagsKt.IsMovableContentFlag;
            $dirty |= i11;
        }
        int $dirty5 = $dirty;
        if ($composer3.shouldExecute(($dirty5 & 306783379) != 306783378, $dirty5 & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "98@4239L26,99@4309L24,100@4379L26");
            if (($changed & 1) != 0 && !$composer3.getDefaultsInvalid()) {
                $composer3.skipToGroupEnd();
                if ((i & 2) != 0) {
                    $dirty5 &= -897;
                }
                if ((i & 8) != 0) {
                    $dirty5 &= -57345;
                }
                if ((i & 16) != 0) {
                    $dirty5 &= -458753;
                }
                if ((i & 32) != 0) {
                    $dirty5 &= -3670017;
                }
                modifier4 = modifier2;
                caretSize4 = j;
                maxWidth3 = f;
                shape5 = shape2;
                contentColor2 = j2;
                containerColor5 = containerColor;
                $dirty2 = $dirty5;
                shadowElevation3 = shadowElevation;
            } else {
                if (i3 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if ((i & 2) == 0) {
                    caretSize3 = j;
                } else {
                    caretSize3 = DpSize.INSTANCE.m8257getUnspecifiedMYxV2XQ();
                    $dirty5 &= -897;
                }
                if (i5 == 0) {
                    maxWidth3 = f;
                } else {
                    maxWidth3 = TooltipDefaults.INSTANCE.m3332getPlainTooltipMaxWidthD9Ej5fM();
                }
                if ((i & 8) == 0) {
                    shape4 = shape2;
                } else {
                    shape4 = TooltipDefaults.INSTANCE.getPlainTooltipContainerShape($composer3, 6);
                    $dirty5 &= -57345;
                }
                if ((i & 16) == 0) {
                    contentColor2 = j2;
                } else {
                    contentColor2 = TooltipDefaults.INSTANCE.getPlainTooltipContentColor($composer3, 6);
                    $dirty5 &= -458753;
                }
                if ((i & 32) == 0) {
                    containerColor4 = containerColor;
                } else {
                    containerColor4 = TooltipDefaults.INSTANCE.getPlainTooltipContainerColor($composer3, 6);
                    $dirty5 &= -3670017;
                }
                if (i9 == 0) {
                    tonalElevation3 = tonalElevation2;
                } else {
                    tonalElevation3 = Dp.m8150constructorimpl(0);
                }
                if (i2 == 0) {
                    tonalElevation2 = tonalElevation3;
                    caretSize4 = caretSize3;
                    shape5 = shape4;
                    containerColor5 = containerColor4;
                    $dirty2 = $dirty5;
                    shadowElevation3 = shadowElevation;
                } else {
                    tonalElevation2 = tonalElevation3;
                    $dirty2 = $dirty5;
                    shadowElevation3 = Dp.m8150constructorimpl(0);
                    caretSize4 = caretSize3;
                    shape5 = shape4;
                    containerColor5 = containerColor4;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                shadowElevation4 = shadowElevation3;
                ComposerKt.traceEventStart(1456881596, $dirty2, -1, "androidx.compose.material3.PlainTooltipAndroid (Tooltip.android.kt:104)");
            } else {
                shadowElevation4 = shadowElevation3;
            }
            int i12 = ($dirty2 & 14) | ($dirty2 & 112) | ($dirty2 & 7168) | (57344 & $dirty2) | (458752 & $dirty2) | (3670016 & $dirty2) | (29360128 & $dirty2) | (234881024 & $dirty2) | (1879048192 & $dirty2);
            long caretSize5 = caretSize4;
            float maxWidth4 = maxWidth3;
            long contentColor3 = contentColor2;
            float tonalElevation4 = tonalElevation2;
            float shadowElevation5 = shadowElevation4;
            TooltipKt.m3339PlainTooltipgv3ox5I($this$PlainTooltipAndroid_u2dm9Er_u2dXc, modifier4, TooltipDefaults.INSTANCE.m3330caretShapeEaSLcWc(caretSize4), maxWidth4, shape5, contentColor3, containerColor5, tonalElevation4, shadowElevation5, content, $composer3, i12, 0);
            $composer2 = $composer3;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier4;
            tonalElevation2 = tonalElevation4;
            shadowElevation2 = shadowElevation5;
            containerColor3 = containerColor5;
            containerColor2 = contentColor3;
            maxWidth2 = maxWidth4;
            shape3 = shape5;
            caretSize2 = caretSize5;
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            shadowElevation2 = shadowElevation;
            modifier3 = modifier2;
            caretSize2 = j;
            maxWidth2 = f;
            shape3 = shape2;
            containerColor2 = j2;
            containerColor3 = containerColor;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.Tooltip_androidKt$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return Tooltip_androidKt.PlainTooltipAndroid_m9Er_Xc$lambda$1($this$PlainTooltipAndroid_u2dm9Er_u2dXc, modifier3, caretSize2, maxWidth2, shape3, containerColor2, containerColor3, tonalElevation2, shadowElevation2, content, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility. Use overload with maxWidth parameter.")
    public static final /* synthetic */ void RichTooltip(final TooltipScope $this$RichTooltipAndroid_u2dyDvdmqw, Modifier modifier, Function2 title, Function2 action, long caretSize, Shape shape, RichTooltipColors colors, float tonalElevation, float shadowElevation, final Function2 text, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        Function2 title2;
        Function2 action2;
        long caretSize2;
        Shape shape2;
        int i2;
        RichTooltipColors colors2;
        float tonalElevation2;
        int i3;
        Composer $composer2;
        final Function2 action3;
        final Function2 action4;
        final RichTooltipColors colors3;
        final Shape shape3;
        final float shadowElevation2;
        final float shadowElevation3;
        final Modifier modifier3;
        final long caretSize3;
        float tonalElevation3;
        int $dirty;
        Modifier modifier4;
        Function2 title3;
        Function2 action5;
        RichTooltipColors colors4;
        long caretSize4;
        Shape shape4;
        float shadowElevation4;
        Composer $composer3 = $composer.startRestartGroup(-244908363);
        ComposerKt.sourceInformation($composer3, "C(RichTooltipAndroid)N(modifier,title,action,caretSize:c#ui.unit.DpSize,shape,colors,tonalElevation:c#ui.unit.Dp,shadowElevation:c#ui.unit.Dp,text)154@6569L364:Tooltip.android.kt#uh7d8r");
        int $dirty2 = $changed;
        if ((Integer.MIN_VALUE & i) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty2 |= ($changed & 8) == 0 ? $composer3.changed($this$RichTooltipAndroid_u2dyDvdmqw) : $composer3.changedInstance($this$RichTooltipAndroid_u2dyDvdmqw) ? 4 : 2;
        }
        int i4 = i & 1;
        if (i4 != 0) {
            $dirty2 |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer3.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i5 = i & 2;
        if (i5 != 0) {
            $dirty2 |= 384;
            title2 = title;
        } else if (($changed & 384) == 0) {
            title2 = title;
            $dirty2 |= $composer3.changedInstance(title2) ? 256 : 128;
        } else {
            title2 = title;
        }
        int i6 = i & 4;
        if (i6 != 0) {
            $dirty2 |= 3072;
            action2 = action;
        } else if (($changed & 3072) == 0) {
            action2 = action;
            $dirty2 |= $composer3.changedInstance(action2) ? 2048 : 1024;
        } else {
            action2 = action;
        }
        if (($changed & 24576) == 0) {
            if ((i & 8) == 0) {
                caretSize2 = caretSize;
                int i7 = $composer3.changed(caretSize2) ? 16384 : 8192;
                $dirty2 |= i7;
            } else {
                caretSize2 = caretSize;
            }
            $dirty2 |= i7;
        } else {
            caretSize2 = caretSize;
        }
        if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            if ((i & 16) == 0) {
                shape2 = shape;
                int i8 = $composer3.changed(shape2) ? 131072 : 65536;
                $dirty2 |= i8;
            } else {
                shape2 = shape;
            }
            $dirty2 |= i8;
        } else {
            shape2 = shape;
        }
        if (($changed & 1572864) == 0) {
            if ((i & 32) == 0) {
                i2 = 196608;
                colors2 = colors;
                int i9 = $composer3.changed(colors2) ? 1048576 : 524288;
                $dirty2 |= i9;
            } else {
                i2 = 196608;
                colors2 = colors;
            }
            $dirty2 |= i9;
        } else {
            i2 = 196608;
            colors2 = colors;
        }
        int i10 = i & 64;
        if (i10 != 0) {
            $dirty2 |= 12582912;
            tonalElevation2 = tonalElevation;
        } else if (($changed & 12582912) == 0) {
            tonalElevation2 = tonalElevation;
            $dirty2 |= $composer3.changed(tonalElevation2) ? 8388608 : 4194304;
        } else {
            tonalElevation2 = tonalElevation;
        }
        int i11 = i & 128;
        if (i11 != 0) {
            $dirty2 |= 100663296;
            i3 = i11;
        } else if (($changed & 100663296) == 0) {
            i3 = i11;
            $dirty2 |= $composer3.changed(shadowElevation) ? 67108864 : GroupFlagsKt.HasAuxSlotFlag;
        } else {
            i3 = i11;
        }
        if ((i & 256) != 0) {
            $dirty2 |= 805306368;
        } else if (($changed & 805306368) == 0) {
            $dirty2 |= $composer3.changedInstance(text) ? GroupFlagsKt.HasMovableContentFlag : GroupFlagsKt.IsMovableContentFlag;
        }
        if ($composer3.shouldExecute(($dirty2 & 306783379) != 306783378, $dirty2 & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "148@6318L25,149@6393L19");
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                if (i4 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i5 != 0) {
                    title2 = null;
                }
                if (i6 != 0) {
                    action2 = null;
                }
                if ((i & 8) != 0) {
                    caretSize2 = DpSize.INSTANCE.m8257getUnspecifiedMYxV2XQ();
                    $dirty2 &= -57345;
                }
                if ((i & 16) != 0) {
                    $dirty2 &= -458753;
                    shape2 = TooltipDefaults.INSTANCE.getRichTooltipContainerShape($composer3, 6);
                }
                if ((i & 32) != 0) {
                    $dirty2 &= -3670017;
                    colors2 = TooltipDefaults.INSTANCE.richTooltipColors($composer3, 6);
                }
                if (i10 != 0) {
                    tonalElevation2 = ElevationTokens.INSTANCE.m3804getLevel0D9Ej5fM();
                }
                if (i3 != 0) {
                    Function2 function2 = action2;
                    shadowElevation4 = RichTooltipTokens.INSTANCE.m4147getContainerElevationD9Ej5fM();
                    $dirty = $dirty2;
                    action5 = function2;
                    tonalElevation3 = tonalElevation2;
                    modifier4 = modifier2;
                    title3 = title2;
                    colors4 = colors2;
                    caretSize4 = caretSize2;
                    shape4 = shape2;
                } else {
                    tonalElevation3 = tonalElevation2;
                    $dirty = $dirty2;
                    modifier4 = modifier2;
                    title3 = title2;
                    action5 = action2;
                    colors4 = colors2;
                    caretSize4 = caretSize2;
                    shape4 = shape2;
                    shadowElevation4 = shadowElevation;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 8) != 0) {
                    $dirty2 &= -57345;
                }
                if ((i & 16) != 0) {
                    $dirty2 &= -458753;
                }
                if ((i & 32) != 0) {
                    $dirty = $dirty2 & (-3670017);
                    tonalElevation3 = tonalElevation2;
                    modifier4 = modifier2;
                    title3 = title2;
                    action5 = action2;
                    colors4 = colors2;
                    caretSize4 = caretSize2;
                    shape4 = shape2;
                    shadowElevation4 = shadowElevation;
                } else {
                    tonalElevation3 = tonalElevation2;
                    $dirty = $dirty2;
                    modifier4 = modifier2;
                    title3 = title2;
                    action5 = action2;
                    colors4 = colors2;
                    caretSize4 = caretSize2;
                    shape4 = shape2;
                    shadowElevation4 = shadowElevation;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-244908363, $dirty, -1, "androidx.compose.material3.RichTooltipAndroid (Tooltip.android.kt:154)");
            }
            long caretSize5 = caretSize4;
            TooltipKt.m3341RichTooltipEkvW5A0($this$RichTooltipAndroid_u2dyDvdmqw, modifier4, title3, action5, TooltipDefaults.INSTANCE.m3330caretShapeEaSLcWc(caretSize4), TooltipDefaults.INSTANCE.m3333getRichTooltipMaxWidthD9Ej5fM(), shape4, colors4, tonalElevation3, shadowElevation4, text, $composer3, ($dirty & 14) | i2 | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (($dirty << 3) & 3670016) | (($dirty << 3) & 29360128) | (($dirty << 3) & 234881024) | (($dirty << 3) & 1879048192), ($dirty >> 27) & 14, 0);
            $composer2 = $composer3;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            action4 = action5;
            shadowElevation2 = shadowElevation4;
            action3 = title3;
            shadowElevation3 = tonalElevation3;
            modifier3 = modifier4;
            colors3 = colors4;
            shape3 = shape4;
            caretSize3 = caretSize5;
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            action3 = title2;
            action4 = action2;
            colors3 = colors2;
            shape3 = shape2;
            shadowElevation2 = shadowElevation;
            shadowElevation3 = tonalElevation2;
            modifier3 = modifier2;
            caretSize3 = caretSize2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.Tooltip_androidKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return Tooltip_androidKt.RichTooltipAndroid_yDvdmqw$lambda$2($this$RichTooltipAndroid_u2dyDvdmqw, modifier3, action3, action4, caretSize3, shape3, colors3, shadowElevation3, shadowElevation2, text, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility.")
    public static final /* synthetic */ void RichTooltip(final TooltipScope $this$RichTooltipAndroid_u2dZuUcA3Q, Modifier modifier, Function2 title, Function2 action, long caretSize, float maxWidth, Shape shape, RichTooltipColors colors, float tonalElevation, float shadowElevation, final Function2 text, Composer $composer, final int $changed, final int $changed1, final int i) {
        Modifier modifier2;
        Function2 function2;
        Function2 function22;
        long caretSize2;
        int $dirty;
        float maxWidth2;
        Shape shape2;
        RichTooltipColors colors2;
        int i2;
        int i3;
        Composer $composer2;
        final float shadowElevation2;
        final RichTooltipColors colors3;
        final Function2 action2;
        final float maxWidth3;
        final long caretSize3;
        final Modifier modifier3;
        final Function2 action3;
        final Shape shape3;
        final float tonalElevation2;
        Modifier.Companion modifier4;
        Function2 title2;
        Function2 action4;
        Shape shape4;
        float tonalElevation3;
        float shadowElevation3;
        int $dirty2;
        Modifier modifier5;
        float maxWidth4;
        float tonalElevation4;
        Function2 title3;
        long caretSize4;
        Function2 action5;
        int $dirty3;
        Composer $composer3 = $composer.startRestartGroup(-905938553);
        ComposerKt.sourceInformation($composer3, "C(RichTooltipAndroid)N(modifier,title,action,caretSize:c#ui.unit.DpSize,maxWidth:c#ui.unit.Dp,shape,colors,tonalElevation:c#ui.unit.Dp,shadowElevation:c#ui.unit.Dp,text)202@8696L337:Tooltip.android.kt#uh7d8r");
        int $dirty4 = $changed;
        int $dirty1 = $changed1;
        if ((Integer.MIN_VALUE & i) != 0) {
            $dirty4 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty4 |= ($changed & 8) == 0 ? $composer3.changed($this$RichTooltipAndroid_u2dZuUcA3Q) : $composer3.changedInstance($this$RichTooltipAndroid_u2dZuUcA3Q) ? 4 : 2;
        }
        int i4 = i & 1;
        if (i4 != 0) {
            $dirty4 |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty4 |= $composer3.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i5 = i & 2;
        if (i5 != 0) {
            $dirty4 |= 384;
            function2 = title;
        } else if (($changed & 384) == 0) {
            function2 = title;
            $dirty4 |= $composer3.changedInstance(function2) ? 256 : 128;
        } else {
            function2 = title;
        }
        int i6 = i & 4;
        if (i6 != 0) {
            $dirty4 |= 3072;
            function22 = action;
        } else if (($changed & 3072) == 0) {
            function22 = action;
            $dirty4 |= $composer3.changedInstance(function22) ? 2048 : 1024;
        } else {
            function22 = action;
        }
        if (($changed & 24576) == 0) {
            if ((i & 8) == 0) {
                $dirty3 = $dirty4;
                caretSize2 = caretSize;
                int i7 = $composer3.changed(caretSize2) ? 16384 : 8192;
                $dirty = $dirty3 | i7;
            } else {
                $dirty3 = $dirty4;
                caretSize2 = caretSize;
            }
            $dirty = $dirty3 | i7;
        } else {
            caretSize2 = caretSize;
            $dirty = $dirty4;
        }
        int i8 = i & 16;
        if (i8 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            maxWidth2 = maxWidth;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            maxWidth2 = maxWidth;
            $dirty |= $composer3.changed(maxWidth2) ? 131072 : 65536;
        } else {
            maxWidth2 = maxWidth;
        }
        if (($changed & 1572864) == 0) {
            if ((i & 32) == 0) {
                shape2 = shape;
                int i9 = $composer3.changed(shape2) ? 1048576 : 524288;
                $dirty |= i9;
            } else {
                shape2 = shape;
            }
            $dirty |= i9;
        } else {
            shape2 = shape;
        }
        if (($changed & 12582912) == 0) {
            if ((i & 64) == 0) {
                colors2 = colors;
                int i10 = $composer3.changed(colors2) ? 8388608 : 4194304;
                $dirty |= i10;
            } else {
                colors2 = colors;
            }
            $dirty |= i10;
        } else {
            colors2 = colors;
        }
        int i11 = i & 128;
        if (i11 != 0) {
            $dirty |= 100663296;
            i2 = i11;
        } else if (($changed & 100663296) == 0) {
            i2 = i11;
            $dirty |= $composer3.changed(tonalElevation) ? 67108864 : GroupFlagsKt.HasAuxSlotFlag;
        } else {
            i2 = i11;
        }
        int i12 = i & 256;
        if (i12 != 0) {
            $dirty |= 805306368;
            i3 = i12;
        } else if (($changed & 805306368) == 0) {
            i3 = i12;
            $dirty |= $composer3.changed(shadowElevation) ? GroupFlagsKt.HasMovableContentFlag : GroupFlagsKt.IsMovableContentFlag;
        } else {
            i3 = i12;
        }
        if ((i & 512) != 0) {
            $dirty1 |= 6;
        } else if (($changed1 & 6) == 0) {
            $dirty1 |= $composer3.changedInstance(text) ? 4 : 2;
        }
        if ($composer3.shouldExecute((($dirty & 306783379) == 306783378 && ($dirty1 & 3) == 2) ? false : true, $dirty & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "196@8445L25,197@8520L19");
            if (($changed & 1) != 0 && !$composer3.getDefaultsInvalid()) {
                $composer3.skipToGroupEnd();
                if ((i & 8) != 0) {
                    $dirty &= -57345;
                }
                if ((i & 32) != 0) {
                    $dirty &= -3670017;
                }
                if ((i & 64) != 0) {
                    $dirty2 = $dirty & (-29360129);
                    maxWidth4 = maxWidth2;
                    modifier5 = modifier2;
                    shape4 = shape2;
                    tonalElevation4 = tonalElevation;
                    caretSize4 = caretSize2;
                    title3 = function2;
                    action5 = function22;
                    shadowElevation3 = shadowElevation;
                } else {
                    maxWidth4 = maxWidth2;
                    modifier5 = modifier2;
                    shape4 = shape2;
                    $dirty2 = $dirty;
                    tonalElevation4 = tonalElevation;
                    caretSize4 = caretSize2;
                    title3 = function2;
                    action5 = function22;
                    shadowElevation3 = shadowElevation;
                }
            } else {
                if (i4 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if (i5 == 0) {
                    title2 = function2;
                } else {
                    title2 = null;
                }
                if (i6 == 0) {
                    action4 = function22;
                } else {
                    action4 = null;
                }
                if ((i & 8) != 0) {
                    caretSize2 = DpSize.INSTANCE.m8257getUnspecifiedMYxV2XQ();
                    $dirty &= -57345;
                }
                if (i8 != 0) {
                    maxWidth2 = TooltipDefaults.INSTANCE.m3333getRichTooltipMaxWidthD9Ej5fM();
                }
                if ((i & 32) == 0) {
                    shape4 = shape2;
                } else {
                    shape4 = TooltipDefaults.INSTANCE.getRichTooltipContainerShape($composer3, 6);
                    $dirty &= -3670017;
                }
                if ((i & 64) != 0) {
                    colors2 = TooltipDefaults.INSTANCE.richTooltipColors($composer3, 6);
                    $dirty &= -29360129;
                }
                if (i2 == 0) {
                    tonalElevation3 = tonalElevation;
                } else {
                    tonalElevation3 = ElevationTokens.INSTANCE.m3804getLevel0D9Ej5fM();
                }
                if (i3 == 0) {
                    shadowElevation3 = shadowElevation;
                    $dirty2 = $dirty;
                    float f = tonalElevation3;
                    modifier5 = modifier4;
                    maxWidth4 = maxWidth2;
                    Function2 function23 = action4;
                    tonalElevation4 = f;
                    long j = caretSize2;
                    title3 = title2;
                    caretSize4 = j;
                    action5 = function23;
                } else {
                    shadowElevation3 = RichTooltipTokens.INSTANCE.m4147getContainerElevationD9Ej5fM();
                    $dirty2 = $dirty;
                    float f2 = tonalElevation3;
                    modifier5 = modifier4;
                    maxWidth4 = maxWidth2;
                    Function2 function24 = action4;
                    tonalElevation4 = f2;
                    long j2 = caretSize2;
                    title3 = title2;
                    caretSize4 = j2;
                    action5 = function24;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-905938553, $dirty2, $dirty1, "androidx.compose.material3.RichTooltipAndroid (Tooltip.android.kt:201)");
            }
            float maxWidth5 = maxWidth4;
            long caretSize5 = caretSize4;
            Shape shape5 = shape4;
            float shadowElevation4 = shadowElevation3;
            TooltipKt.m3341RichTooltipEkvW5A0($this$RichTooltipAndroid_u2dZuUcA3Q, modifier5, title3, action5, TooltipDefaults.INSTANCE.m3330caretShapeEaSLcWc(caretSize4), maxWidth5, shape5, colors2, tonalElevation4, shadowElevation4, text, $composer3, ($dirty2 & 896) | ($dirty2 & 14) | ($dirty2 & 112) | ($dirty2 & 7168) | (458752 & $dirty2) | (3670016 & $dirty2) | (29360128 & $dirty2) | (234881024 & $dirty2) | (1879048192 & $dirty2), $dirty1 & 14, 0);
            $composer2 = $composer3;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            action2 = action5;
            tonalElevation2 = tonalElevation4;
            shadowElevation2 = shadowElevation4;
            action3 = title3;
            shape3 = shape5;
            colors3 = colors2;
            modifier3 = modifier5;
            maxWidth3 = maxWidth5;
            caretSize3 = caretSize5;
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            shadowElevation2 = shadowElevation;
            colors3 = colors2;
            action2 = function22;
            maxWidth3 = maxWidth2;
            caretSize3 = caretSize2;
            modifier3 = modifier2;
            action3 = function2;
            shape3 = shape2;
            tonalElevation2 = tonalElevation;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.Tooltip_androidKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return Tooltip_androidKt.RichTooltipAndroid_ZuUcA3Q$lambda$3($this$RichTooltipAndroid_u2dZuUcA3Q, modifier3, action3, action2, caretSize3, maxWidth3, shape3, colors3, tonalElevation2, shadowElevation2, text, $changed, $changed1, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }
}
