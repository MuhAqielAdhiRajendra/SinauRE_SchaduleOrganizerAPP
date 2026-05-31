package androidx.compose.foundation.layout;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.platform.InspectorInfo;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: Padding.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\n\u001a;\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0003H\u0007¢\u0006\u0004\b\u0007\u0010\b\u001a'\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u0003H\u0007¢\u0006\u0004\b\u000b\u0010\f\u001a\u001b\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\r\u001a\u00020\u0003H\u0007¢\u0006\u0004\b\u000e\u0010\u000f\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0010\u001a\u00020\u0011H\u0007\u001a;\u0010\u0012\u001a\u00020\u0001*\u00020\u00012\b\b\u0002\u0010\u0013\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0014\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0003H\u0007¢\u0006\u0004\b\u0015\u0010\b\u001a\u0015\u0010\u0016\u001a\u00020\u0011*\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u0011H\u0087\u0002\u001a\u0015\u0010\u0018\u001a\u00020\u0011*\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u0011H\u0087\u0002\u001a\u0019\u0010\u0019\u001a\u00020\u0003*\u00020\u00112\u0006\u0010\u001a\u001a\u00020\u001bH\u0007¢\u0006\u0002\u0010\u001c\u001a\u0019\u0010\u001d\u001a\u00020\u0003*\u00020\u00112\u0006\u0010\u001a\u001a\u00020\u001bH\u0007¢\u0006\u0002\u0010\u001c\u001a\u0017\u0010\u001e\u001a\u00020\u00112\u0006\u0010\r\u001a\u00020\u0003H\u0007¢\u0006\u0004\b\u001f\u0010 \u001a#\u0010\u001e\u001a\u00020\u00112\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u0003H\u0007¢\u0006\u0004\b!\u0010\"\u001a7\u0010\u001e\u001a\u00020\u00112\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0003H\u0007¢\u0006\u0004\b#\u0010$¨\u0006%"}, d2 = {"padding", "Landroidx/compose/ui/Modifier;", "start", "Landroidx/compose/ui/unit/Dp;", "top", "end", "bottom", "padding-qDBjuR0", "(Landroidx/compose/ui/Modifier;FFFF)Landroidx/compose/ui/Modifier;", "horizontal", "vertical", "padding-VpY3zN4", "(Landroidx/compose/ui/Modifier;FF)Landroidx/compose/ui/Modifier;", "all", "padding-3ABfNKs", "(Landroidx/compose/ui/Modifier;F)Landroidx/compose/ui/Modifier;", "paddingValues", "Landroidx/compose/foundation/layout/PaddingValues;", "absolutePadding", "left", "right", "absolutePadding-qDBjuR0", "plus", "other", "minus", "calculateStartPadding", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "(Landroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/ui/unit/LayoutDirection;)F", "calculateEndPadding", "PaddingValues", "PaddingValues-0680j_4", "(F)Landroidx/compose/foundation/layout/PaddingValues;", "PaddingValues-YgX7TsA", "(FF)Landroidx/compose/foundation/layout/PaddingValues;", "PaddingValues-a9UjIt4", "(FFFF)Landroidx/compose/foundation/layout/PaddingValues;", "foundation-layout"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class PaddingKt {
    /* JADX INFO: renamed from: padding-qDBjuR0$default, reason: not valid java name */
    public static /* synthetic */ Modifier m1052paddingqDBjuR0$default(Modifier modifier, float f, float f2, float f3, float f4, int i, Object obj) {
        if ((i & 1) != 0) {
            f = Dp.m8150constructorimpl(0);
        }
        int $i$f$getDp = i & 2;
        if ($i$f$getDp != 0) {
            f2 = Dp.m8150constructorimpl(0);
        }
        int $i$f$getDp2 = i & 4;
        if ($i$f$getDp2 != 0) {
            f3 = Dp.m8150constructorimpl(0);
        }
        if ((i & 8) != 0) {
            f4 = Dp.m8150constructorimpl(0);
        }
        return m1051paddingqDBjuR0(modifier, f, f2, f3, f4);
    }

    /* JADX INFO: renamed from: padding-qDBjuR0, reason: not valid java name */
    public static final Modifier m1051paddingqDBjuR0(Modifier $this$padding_u2dqDBjuR0, final float start, final float top, final float end, final float bottom) {
        return $this$padding_u2dqDBjuR0.then(new PaddingElement(start, top, end, bottom, true, new Function1() { // from class: androidx.compose.foundation.layout.PaddingKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PaddingKt.padding_qDBjuR0$lambda$0(start, top, end, bottom, (InspectorInfo) obj);
            }
        }, null));
    }

    static final Unit padding_qDBjuR0$lambda$0(float $start, float $top, float $end, float $bottom, InspectorInfo $this$PaddingElement) {
        $this$PaddingElement.setName("padding");
        $this$PaddingElement.getProperties().set("start", Dp.m8148boximpl($start));
        $this$PaddingElement.getProperties().set("top", Dp.m8148boximpl($top));
        $this$PaddingElement.getProperties().set("end", Dp.m8148boximpl($end));
        $this$PaddingElement.getProperties().set("bottom", Dp.m8148boximpl($bottom));
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: padding-VpY3zN4$default, reason: not valid java name */
    public static /* synthetic */ Modifier m1050paddingVpY3zN4$default(Modifier modifier, float f, float f2, int i, Object obj) {
        if ((i & 1) != 0) {
            f = Dp.m8150constructorimpl(0);
        }
        if ((i & 2) != 0) {
            f2 = Dp.m8150constructorimpl(0);
        }
        return m1049paddingVpY3zN4(modifier, f, f2);
    }

    /* JADX INFO: renamed from: padding-VpY3zN4, reason: not valid java name */
    public static final Modifier m1049paddingVpY3zN4(Modifier $this$padding_u2dVpY3zN4, final float horizontal, final float vertical) {
        return $this$padding_u2dVpY3zN4.then(new PaddingElement(horizontal, vertical, horizontal, vertical, true, new Function1() { // from class: androidx.compose.foundation.layout.PaddingKt$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PaddingKt.padding_VpY3zN4$lambda$0(horizontal, vertical, (InspectorInfo) obj);
            }
        }, null));
    }

    static final Unit padding_VpY3zN4$lambda$0(float $horizontal, float $vertical, InspectorInfo $this$PaddingElement) {
        $this$PaddingElement.setName("padding");
        $this$PaddingElement.getProperties().set("horizontal", Dp.m8148boximpl($horizontal));
        $this$PaddingElement.getProperties().set("vertical", Dp.m8148boximpl($vertical));
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: padding-3ABfNKs, reason: not valid java name */
    public static final Modifier m1048padding3ABfNKs(Modifier $this$padding_u2d3ABfNKs, final float all) {
        return $this$padding_u2d3ABfNKs.then(new PaddingElement(all, all, all, all, true, new Function1() { // from class: androidx.compose.foundation.layout.PaddingKt$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PaddingKt.padding_3ABfNKs$lambda$0(all, (InspectorInfo) obj);
            }
        }, null));
    }

    static final Unit padding_3ABfNKs$lambda$0(float $all, InspectorInfo $this$PaddingElement) {
        $this$PaddingElement.setName("padding");
        $this$PaddingElement.setValue(Dp.m8148boximpl($all));
        return Unit.INSTANCE;
    }

    public static final Modifier padding(Modifier $this$padding, final PaddingValues paddingValues) {
        return $this$padding.then(new PaddingValuesElement(paddingValues, new Function1() { // from class: androidx.compose.foundation.layout.PaddingKt$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PaddingKt.padding$lambda$0(paddingValues, (InspectorInfo) obj);
            }
        }));
    }

    static final Unit padding$lambda$0(PaddingValues $paddingValues, InspectorInfo $this$PaddingValuesElement) {
        $this$PaddingValuesElement.setName("padding");
        $this$PaddingValuesElement.getProperties().set("paddingValues", $paddingValues);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: absolutePadding-qDBjuR0$default, reason: not valid java name */
    public static /* synthetic */ Modifier m1047absolutePaddingqDBjuR0$default(Modifier modifier, float f, float f2, float f3, float f4, int i, Object obj) {
        if ((i & 1) != 0) {
            f = Dp.m8150constructorimpl(0);
        }
        int $i$f$getDp = i & 2;
        if ($i$f$getDp != 0) {
            f2 = Dp.m8150constructorimpl(0);
        }
        int $i$f$getDp2 = i & 4;
        if ($i$f$getDp2 != 0) {
            f3 = Dp.m8150constructorimpl(0);
        }
        if ((i & 8) != 0) {
            f4 = Dp.m8150constructorimpl(0);
        }
        return m1046absolutePaddingqDBjuR0(modifier, f, f2, f3, f4);
    }

    /* JADX INFO: renamed from: absolutePadding-qDBjuR0, reason: not valid java name */
    public static final Modifier m1046absolutePaddingqDBjuR0(Modifier $this$absolutePadding_u2dqDBjuR0, final float left, final float top, final float right, final float bottom) {
        return $this$absolutePadding_u2dqDBjuR0.then(new PaddingElement(left, top, right, bottom, false, new Function1() { // from class: androidx.compose.foundation.layout.PaddingKt$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PaddingKt.absolutePadding_qDBjuR0$lambda$0(left, top, right, bottom, (InspectorInfo) obj);
            }
        }, null));
    }

    static final Unit absolutePadding_qDBjuR0$lambda$0(float $left, float $top, float $right, float $bottom, InspectorInfo $this$PaddingElement) {
        $this$PaddingElement.setName("absolutePadding");
        $this$PaddingElement.getProperties().set("left", Dp.m8148boximpl($left));
        $this$PaddingElement.getProperties().set("top", Dp.m8148boximpl($top));
        $this$PaddingElement.getProperties().set("right", Dp.m8148boximpl($right));
        $this$PaddingElement.getProperties().set("bottom", Dp.m8148boximpl($bottom));
        return Unit.INSTANCE;
    }

    public static final PaddingValues plus(final PaddingValues $this$plus, final PaddingValues other) {
        return new PaddingValues() { // from class: androidx.compose.foundation.layout.PaddingKt.plus.1
            @Override // androidx.compose.foundation.layout.PaddingValues
            /* JADX INFO: renamed from: calculateLeftPadding-u2uoSUM */
            public float mo998calculateLeftPaddingu2uoSUM(LayoutDirection layoutDirection) {
                float arg0$iv = $this$plus.mo998calculateLeftPaddingu2uoSUM(layoutDirection);
                float other$iv = other.mo998calculateLeftPaddingu2uoSUM(layoutDirection);
                return Dp.m8150constructorimpl(arg0$iv + other$iv);
            }

            @Override // androidx.compose.foundation.layout.PaddingValues
            /* JADX INFO: renamed from: calculateTopPadding-D9Ej5fM */
            public float getTop() {
                float arg0$iv = $this$plus.getTop();
                float other$iv = other.getTop();
                return Dp.m8150constructorimpl(arg0$iv + other$iv);
            }

            @Override // androidx.compose.foundation.layout.PaddingValues
            /* JADX INFO: renamed from: calculateRightPadding-u2uoSUM */
            public float mo999calculateRightPaddingu2uoSUM(LayoutDirection layoutDirection) {
                float arg0$iv = $this$plus.mo999calculateRightPaddingu2uoSUM(layoutDirection);
                float other$iv = other.mo999calculateRightPaddingu2uoSUM(layoutDirection);
                return Dp.m8150constructorimpl(arg0$iv + other$iv);
            }

            @Override // androidx.compose.foundation.layout.PaddingValues
            /* JADX INFO: renamed from: calculateBottomPadding-D9Ej5fM */
            public float getBottom() {
                float arg0$iv = $this$plus.getBottom();
                float other$iv = other.getBottom();
                return Dp.m8150constructorimpl(arg0$iv + other$iv);
            }
        };
    }

    public static final PaddingValues minus(final PaddingValues $this$minus, final PaddingValues other) {
        return new PaddingValues() { // from class: androidx.compose.foundation.layout.PaddingKt.minus.1
            @Override // androidx.compose.foundation.layout.PaddingValues
            /* JADX INFO: renamed from: calculateLeftPadding-u2uoSUM */
            public float mo998calculateLeftPaddingu2uoSUM(LayoutDirection layoutDirection) {
                float arg0$iv = $this$minus.mo998calculateLeftPaddingu2uoSUM(layoutDirection);
                float other$iv = other.mo998calculateLeftPaddingu2uoSUM(layoutDirection);
                float arg0$iv2 = Dp.m8150constructorimpl(arg0$iv - other$iv);
                float minimumValue$iv = Dp.m8150constructorimpl(0);
                float $this$coerceAtLeast_u2dYgX7TsA$iv = Dp.m8150constructorimpl(RangesKt.coerceAtLeast(arg0$iv2, minimumValue$iv));
                return $this$coerceAtLeast_u2dYgX7TsA$iv;
            }

            @Override // androidx.compose.foundation.layout.PaddingValues
            /* JADX INFO: renamed from: calculateTopPadding-D9Ej5fM */
            public float getTop() {
                float arg0$iv = $this$minus.getTop();
                float other$iv = other.getTop();
                float arg0$iv2 = Dp.m8150constructorimpl(arg0$iv - other$iv);
                float minimumValue$iv = Dp.m8150constructorimpl(0);
                float $this$coerceAtLeast_u2dYgX7TsA$iv = Dp.m8150constructorimpl(RangesKt.coerceAtLeast(arg0$iv2, minimumValue$iv));
                return $this$coerceAtLeast_u2dYgX7TsA$iv;
            }

            @Override // androidx.compose.foundation.layout.PaddingValues
            /* JADX INFO: renamed from: calculateRightPadding-u2uoSUM */
            public float mo999calculateRightPaddingu2uoSUM(LayoutDirection layoutDirection) {
                float arg0$iv = $this$minus.mo999calculateRightPaddingu2uoSUM(layoutDirection);
                float other$iv = other.mo999calculateRightPaddingu2uoSUM(layoutDirection);
                float arg0$iv2 = Dp.m8150constructorimpl(arg0$iv - other$iv);
                float minimumValue$iv = Dp.m8150constructorimpl(0);
                float $this$coerceAtLeast_u2dYgX7TsA$iv = Dp.m8150constructorimpl(RangesKt.coerceAtLeast(arg0$iv2, minimumValue$iv));
                return $this$coerceAtLeast_u2dYgX7TsA$iv;
            }

            @Override // androidx.compose.foundation.layout.PaddingValues
            /* JADX INFO: renamed from: calculateBottomPadding-D9Ej5fM */
            public float getBottom() {
                float arg0$iv = $this$minus.getBottom();
                float other$iv = other.getBottom();
                float arg0$iv2 = Dp.m8150constructorimpl(arg0$iv - other$iv);
                float minimumValue$iv = Dp.m8150constructorimpl(0);
                float $this$coerceAtLeast_u2dYgX7TsA$iv = Dp.m8150constructorimpl(RangesKt.coerceAtLeast(arg0$iv2, minimumValue$iv));
                return $this$coerceAtLeast_u2dYgX7TsA$iv;
            }
        };
    }

    public static final float calculateStartPadding(PaddingValues $this$calculateStartPadding, LayoutDirection layoutDirection) {
        if (layoutDirection == LayoutDirection.Ltr) {
            return $this$calculateStartPadding.mo998calculateLeftPaddingu2uoSUM(layoutDirection);
        }
        return $this$calculateStartPadding.mo999calculateRightPaddingu2uoSUM(layoutDirection);
    }

    public static final float calculateEndPadding(PaddingValues $this$calculateEndPadding, LayoutDirection layoutDirection) {
        if (layoutDirection == LayoutDirection.Ltr) {
            return $this$calculateEndPadding.mo999calculateRightPaddingu2uoSUM(layoutDirection);
        }
        return $this$calculateEndPadding.mo998calculateLeftPaddingu2uoSUM(layoutDirection);
    }

    /* JADX INFO: renamed from: PaddingValues-0680j_4, reason: not valid java name */
    public static final PaddingValues m1041PaddingValues0680j_4(float all) {
        return new PaddingValues(all, all, all, all, null);
    }

    /* JADX INFO: renamed from: PaddingValues-YgX7TsA$default, reason: not valid java name */
    public static /* synthetic */ PaddingValues m1043PaddingValuesYgX7TsA$default(float f, float f2, int i, Object obj) {
        if ((i & 1) != 0) {
            f = Dp.m8150constructorimpl(0);
        }
        if ((i & 2) != 0) {
            f2 = Dp.m8150constructorimpl(0);
        }
        return m1042PaddingValuesYgX7TsA(f, f2);
    }

    /* JADX INFO: renamed from: PaddingValues-YgX7TsA, reason: not valid java name */
    public static final PaddingValues m1042PaddingValuesYgX7TsA(float horizontal, float vertical) {
        return new PaddingValues(horizontal, vertical, horizontal, vertical, null);
    }

    /* JADX INFO: renamed from: PaddingValues-a9UjIt4$default, reason: not valid java name */
    public static /* synthetic */ PaddingValues m1045PaddingValuesa9UjIt4$default(float f, float f2, float f3, float f4, int i, Object obj) {
        if ((i & 1) != 0) {
            f = Dp.m8150constructorimpl(0);
        }
        int $i$f$getDp = i & 2;
        if ($i$f$getDp != 0) {
            f2 = Dp.m8150constructorimpl(0);
        }
        int $i$f$getDp2 = i & 4;
        if ($i$f$getDp2 != 0) {
            f3 = Dp.m8150constructorimpl(0);
        }
        if ((i & 8) != 0) {
            f4 = Dp.m8150constructorimpl(0);
        }
        return m1044PaddingValuesa9UjIt4(f, f2, f3, f4);
    }

    /* JADX INFO: renamed from: PaddingValues-a9UjIt4, reason: not valid java name */
    public static final PaddingValues m1044PaddingValuesa9UjIt4(float start, float top, float end, float bottom) {
        return new PaddingValues(start, top, end, bottom, null);
    }
}
