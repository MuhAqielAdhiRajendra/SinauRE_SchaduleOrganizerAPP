package androidx.compose.ui.platform;

import android.view.View;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection;
import androidx.compose.ui.input.nestedscroll.NestedScrollSource;
import androidx.compose.ui.unit.Velocity;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Metadata;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: NestedScrollInteropConnection.android.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000>\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0001H\u0002\u001a\f\u0010\u0002\u001a\u00020\u0003*\u00020\u0001H\u0002\u001a\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0001H\u0000\u001a\f\u0010\u0006\u001a\u00020\u0001*\u00020\u0003H\u0002\u001a\f\u0010\u0007\u001a\u00020\u0001*\u00020\u0001H\u0002\u001a/\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\tH\u0002¢\u0006\u0004\b\u000f\u0010\u0010\u001a\u0013\u0010\u0011\u001a\u00020\u0003*\u00020\u0012H\u0002¢\u0006\u0004\b\u0013\u0010\u0014\u001a\u001b\u0010\u0016\u001a\u00020\u0003*\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0001H\u0002¢\u0006\u0004\b\u001b\u0010\u001c\u001a\u0017\u0010\u001d\u001a\u00020\u001e2\b\b\u0002\u0010\u001f\u001a\u00020 H\u0007¢\u0006\u0002\u0010!\"\u000e\u0010\u0015\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u0018\u0010\u0016\u001a\u00020\u0003*\u00020\t8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018¨\u0006\""}, d2 = {"ceilAwayFromZero", "", "extractIntegerPixels", "", "composeToViewOffset", TypedValues.CycleType.S_WAVE_OFFSET, "reverseAxis", "toViewVelocity", "toOffset", "Landroidx/compose/ui/geometry/Offset;", "dx", "dy", "consumed", "", "available", "toOffset-moWRBKg", "(II[IJ)J", "toViewType", "Landroidx/compose/ui/input/nestedscroll/NestedScrollSource;", "toViewType-GyEprt8", "(I)I", "ScrollingAxesThreshold", "scrollAxes", "getScrollAxes-k-4lQ0M", "(J)I", "Landroidx/compose/ui/unit/Velocity;", "minFlingVelocity", "scrollAxes-sF-c-tU", "(JF)I", "rememberNestedScrollInteropConnection", "Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;", "hostView", "Landroid/view/View;", "(Landroid/view/View;Landroidx/compose/runtime/Composer;II)Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;", "ui"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class NestedScrollInteropConnectionKt {
    private static final float ScrollingAxesThreshold = 0.5f;

    private static final float ceilAwayFromZero(float $this$ceilAwayFromZero) {
        return (float) ($this$ceilAwayFromZero >= 0.0f ? Math.ceil($this$ceilAwayFromZero) : Math.floor($this$ceilAwayFromZero));
    }

    private static final int extractIntegerPixels(float $this$extractIntegerPixels) {
        return MathKt.roundToInt($this$extractIntegerPixels);
    }

    public static final int composeToViewOffset(float offset) {
        return extractIntegerPixels(offset) * (-1);
    }

    private static final float reverseAxis(int $this$reverseAxis) {
        return $this$reverseAxis * (-1.0f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float toViewVelocity(float $this$toViewVelocity) {
        return (-1.0f) * $this$toViewVelocity;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: toOffset-moWRBKg, reason: not valid java name */
    public static final long m7312toOffsetmoWRBKg(int dx, int dy, int[] consumed, long available) {
        float overflowX;
        float overflowY;
        float offsetX;
        float offsetY;
        if (Math.abs(consumed[0]) == 0) {
            overflowX = 0.0f;
        } else {
            int bits$iv$iv$iv = (int) (available >> 32);
            overflowX = Float.intBitsToFloat(bits$iv$iv$iv) - reverseAxis(dx);
        }
        if (Math.abs(consumed[1]) == 0) {
            overflowY = 0.0f;
        } else {
            int bits$iv$iv$iv2 = (int) (available & 4294967295L);
            overflowY = Float.intBitsToFloat(bits$iv$iv$iv2) - reverseAxis(dy);
        }
        int bits$iv$iv$iv3 = (int) (available >> 32);
        if (Float.intBitsToFloat(bits$iv$iv$iv3) >= 0.0f) {
            int bits$iv$iv$iv4 = (int) (available >> 32);
            offsetX = RangesKt.coerceAtMost(reverseAxis(consumed[0]) + overflowX, Float.intBitsToFloat(bits$iv$iv$iv4));
        } else {
            int bits$iv$iv$iv5 = (int) (available >> 32);
            offsetX = RangesKt.coerceAtLeast(reverseAxis(consumed[0]) + overflowX, Float.intBitsToFloat(bits$iv$iv$iv5));
        }
        int bits$iv$iv$iv6 = (int) (available & 4294967295L);
        if (Float.intBitsToFloat(bits$iv$iv$iv6) >= 0.0f) {
            int bits$iv$iv$iv7 = (int) (available & 4294967295L);
            offsetY = RangesKt.coerceAtMost(reverseAxis(consumed[1]) + overflowY, Float.intBitsToFloat(bits$iv$iv$iv7));
        } else {
            int bits$iv$iv$iv8 = (int) (available & 4294967295L);
            offsetY = RangesKt.coerceAtLeast(reverseAxis(consumed[1]) + overflowY, Float.intBitsToFloat(bits$iv$iv$iv8));
        }
        float y$iv = offsetY;
        float x$iv = offsetX;
        long v1$iv$iv = Float.floatToRawIntBits(x$iv);
        long v2$iv$iv = Float.floatToRawIntBits(y$iv);
        return Offset.m5060constructorimpl((v1$iv$iv << 32) | (4294967295L & v2$iv$iv));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: toViewType-GyEprt8, reason: not valid java name */
    public static final int m7313toViewTypeGyEprt8(int $this$toViewType_u2dGyEprt8) {
        return NestedScrollSource.m6507equalsimpl0($this$toViewType_u2dGyEprt8, NestedScrollSource.INSTANCE.m6519getUserInputWNlRxjI()) ? 0 : 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getScrollAxes-k-4lQ0M, reason: not valid java name */
    public static final int m7310getScrollAxesk4lQ0M(long $this$scrollAxes) {
        int axes = 0;
        int bits$iv$iv$iv = (int) ($this$scrollAxes >> 32);
        if (Math.abs(Float.intBitsToFloat(bits$iv$iv$iv)) >= 0.5f) {
            axes = 0 | 1;
        }
        int bits$iv$iv$iv2 = (int) (4294967295L & $this$scrollAxes);
        if (Math.abs(Float.intBitsToFloat(bits$iv$iv$iv2)) >= 0.5f) {
            return axes | 2;
        }
        return axes;
    }

    /* JADX INFO: renamed from: scrollAxes-sF-c-tU, reason: not valid java name */
    private static final int m7311scrollAxessFctU(long $this$scrollAxes_u2dsF_u2dc_u2dtU, float minFlingVelocity) {
        int axes = 0;
        if (Math.abs(Velocity.m8388getXimpl($this$scrollAxes_u2dsF_u2dc_u2dtU)) >= minFlingVelocity) {
            axes = 0 | 1;
        }
        if (Math.abs(Velocity.m8389getYimpl($this$scrollAxes_u2dsF_u2dc_u2dtU)) >= minFlingVelocity) {
            return axes | 2;
        }
        return axes;
    }

    public static final NestedScrollConnection rememberNestedScrollInteropConnection(View hostView, Composer $composer, int $changed, int i) {
        ComposerKt.sourceInformationMarkerStart($composer, 1075877987, "C(rememberNestedScrollInteropConnection)N(hostView)277@10504L7,279@10591L7,280@10610L133:NestedScrollInteropConnection.android.kt#itgzvw");
        if ((i & 1) != 0) {
            ProvidableCompositionLocal<View> localView = AndroidCompositionLocals_androidKt.getLocalView();
            ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = $composer.consume(localView);
            ComposerKt.sourceInformationMarkerEnd($composer);
            hostView = (View) objConsume;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1075877987, $changed, -1, "androidx.compose.ui.platform.rememberNestedScrollInteropConnection (NestedScrollInteropConnection.android.kt:278)");
        }
        ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration = CompositionLocalsKt.getLocalViewConfiguration();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
        Object objConsume2 = $composer.consume(localViewConfiguration);
        ComposerKt.sourceInformationMarkerEnd($composer);
        ViewConfiguration viewConfiguration = (ViewConfiguration) objConsume2;
        ComposerKt.sourceInformationMarkerStart($composer, -1168366936, "CC(remember):NestedScrollInteropConnection.android.kt#9igjgp");
        boolean invalid$iv = $composer.changed(hostView) | $composer.changed(viewConfiguration);
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            Object value$iv = new NestedScrollInteropConnection(hostView, viewConfiguration.getMinimumFlingVelocity());
            $composer.updateRememberedValue(value$iv);
            it$iv = value$iv;
        }
        NestedScrollInteropConnection nestedScrollInteropConnection = (NestedScrollInteropConnection) it$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return nestedScrollInteropConnection;
    }
}
