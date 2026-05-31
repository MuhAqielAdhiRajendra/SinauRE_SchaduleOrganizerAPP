package androidx.compose.ui.viewinterop;

import android.view.View;
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection;
import androidx.compose.ui.input.nestedscroll.NestedScrollSource;
import androidx.compose.ui.layout.LayoutCoordinatesKt;
import androidx.compose.ui.node.LayoutNode;
import androidx.savedstate.serialization.ClassDiscriminatorModeKt;
import kotlin.Metadata;

/* JADX INFO: compiled from: AndroidViewHolder.android.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000?\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\b\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0002\u001a\f\u0010\n\u001a\u00020\u000b*\u00020\u0006H\u0002\u001a\f\u0010\f\u001a\u00020\u000b*\u00020\u000bH\u0002\u001a\u0015\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0006H\u0002¢\u0006\u0002\u0010\u0010\"\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000\"\u0010\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\t*(\b\u0002\u0010\u0011\"\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0013\u0012\u0004\u0012\u00020\u00010\u00122\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0013\u0012\u0004\u0012\u00020\u00010\u0012*<\b\u0002\u0010\u0014\"\u0010\u0012\u0006\u0012\u0004\u0018\u0001`\u0015\u0012\u0004\u0012\u00020\u00010\u00122$\u0012\u001a\u0012\u0018\u0012\u0006\u0012\u0004\u0018\u00010\u0013\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0012j\u0004\u0018\u0001`\u0015\u0012\u0004\u0012\u00020\u00010\u0012¨\u0006\u0016"}, d2 = {"layoutAccordingTo", "", "Landroid/view/View;", "layoutNode", "Landroidx/compose/ui/node/LayoutNode;", "Unmeasured", "", "NoOpScrollConnection", "androidx/compose/ui/viewinterop/AndroidViewHolder_androidKt$NoOpScrollConnection$1", "Landroidx/compose/ui/viewinterop/AndroidViewHolder_androidKt$NoOpScrollConnection$1;", "toComposeOffset", "", "toComposeVelocity", "toNestedScrollSource", "Landroidx/compose/ui/input/nestedscroll/NestedScrollSource;", ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY, "(I)I", "BringIntoViewRequester", "Lkotlin/Function1;", "Landroidx/compose/ui/geometry/Rect;", "OnRequesterReady", "Landroidx/compose/ui/viewinterop/BringIntoViewRequester;", "ui"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class AndroidViewHolder_androidKt {
    private static final AndroidViewHolder_androidKt$NoOpScrollConnection$1 NoOpScrollConnection = new NestedScrollConnection() { // from class: androidx.compose.ui.viewinterop.AndroidViewHolder_androidKt$NoOpScrollConnection$1
    };
    private static final int Unmeasured = Integer.MIN_VALUE;

    /* JADX INFO: Access modifiers changed from: private */
    public static final void layoutAccordingTo(View $this$layoutAccordingTo, LayoutNode layoutNode) {
        long position = LayoutCoordinatesKt.positionInRoot(layoutNode.getCoordinates());
        int bits$iv$iv$iv = (int) (position >> 32);
        float $this$fastRoundToInt$iv = Float.intBitsToFloat(bits$iv$iv$iv);
        int $i$f$fastRoundToInt = Math.round($this$fastRoundToInt$iv);
        int bits$iv$iv$iv2 = (int) (4294967295L & position);
        float $this$fastRoundToInt$iv2 = Float.intBitsToFloat(bits$iv$iv$iv2);
        int $i$f$fastRoundToInt2 = Math.round($this$fastRoundToInt$iv2);
        $this$layoutAccordingTo.layout($i$f$fastRoundToInt, $i$f$fastRoundToInt2, $this$layoutAccordingTo.getMeasuredWidth() + $i$f$fastRoundToInt, $this$layoutAccordingTo.getMeasuredHeight() + $i$f$fastRoundToInt2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float toComposeOffset(int $this$toComposeOffset) {
        return $this$toComposeOffset * (-1.0f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float toComposeVelocity(float $this$toComposeVelocity) {
        return (-1.0f) * $this$toComposeVelocity;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int toNestedScrollSource(int type) {
        return type == 0 ? NestedScrollSource.INSTANCE.m6519getUserInputWNlRxjI() : NestedScrollSource.INSTANCE.m6518getSideEffectWNlRxjI();
    }
}
