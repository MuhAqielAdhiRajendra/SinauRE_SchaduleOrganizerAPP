package androidx.compose.foundation;

import android.graphics.Rect;
import android.view.View;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LayoutCoordinatesKt;
import androidx.compose.ui.node.DelegatableNode_androidKt;
import androidx.compose.ui.node.GlobalPositionAwareModifierNode;
import kotlin.Metadata;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;

/* JADX INFO: compiled from: RectListNode.android.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\b!\u0018\u00002\u00020\u00012\u00020\u0002B\u001d\u0012\u0014\u0010\u0003\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0004¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0005H\u0016J\b\u0010\u0015\u001a\u00020\u0013H\u0016J\u000e\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\r0\u0017H&J\u0016\u0010\u0018\u001a\u00020\u00132\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\r0\u0017H&J\u0012\u0010\u001a\u001a\u00020\u00132\b\u0010\u001b\u001a\u0004\u0018\u00010\rH\u0002J\u0018\u0010\u001c\u001a\u00020\r2\u0006\u0010\u001d\u001a\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u0006H\u0002R(\u0010\u0003\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0004X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\bR\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\u00020\u000f8DX\u0084\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001e"}, d2 = {"Landroidx/compose/foundation/RectListNode;", "Landroidx/compose/ui/Modifier$Node;", "Landroidx/compose/ui/node/GlobalPositionAwareModifierNode;", "rect", "Lkotlin/Function1;", "Landroidx/compose/ui/layout/LayoutCoordinates;", "Landroidx/compose/ui/geometry/Rect;", "<init>", "(Lkotlin/jvm/functions/Function1;)V", "getRect", "()Lkotlin/jvm/functions/Function1;", "setRect", "androidRect", "Landroid/graphics/Rect;", "view", "Landroid/view/View;", "getView", "()Landroid/view/View;", "onGloballyPositioned", "", "coordinates", "onDetach", "currentRects", "Landroidx/compose/runtime/collection/MutableVector;", "updateRects", "rects", "replaceRect", "newRect", "calcBounds", "layoutCoordinates", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
public abstract class RectListNode extends Modifier.Node implements GlobalPositionAwareModifierNode {
    public static final int $stable = 8;
    private Rect androidRect;
    private Function1<? super LayoutCoordinates, androidx.compose.ui.geometry.Rect> rect;

    public abstract MutableVector<Rect> currentRects();

    public abstract void updateRects(MutableVector<Rect> rects);

    public RectListNode(Function1<? super LayoutCoordinates, androidx.compose.ui.geometry.Rect> function1) {
        this.rect = function1;
    }

    public Function1<LayoutCoordinates, androidx.compose.ui.geometry.Rect> getRect() {
        return this.rect;
    }

    public void setRect(Function1<? super LayoutCoordinates, androidx.compose.ui.geometry.Rect> function1) {
        this.rect = function1;
    }

    protected final View getView() {
        return DelegatableNode_androidKt.requireView(this);
    }

    @Override // androidx.compose.ui.node.GlobalPositionAwareModifierNode
    public void onGloballyPositioned(LayoutCoordinates coordinates) {
        Rect newRect;
        if (getRect() == null) {
            androidx.compose.ui.geometry.Rect boundsInRoot = LayoutCoordinatesKt.boundsInRoot(coordinates);
            newRect = new Rect(MathKt.roundToInt(boundsInRoot.getLeft()), MathKt.roundToInt(boundsInRoot.getTop()), MathKt.roundToInt(boundsInRoot.getRight()), MathKt.roundToInt(boundsInRoot.getBottom()));
        } else {
            Function1<LayoutCoordinates, androidx.compose.ui.geometry.Rect> rect = getRect();
            Intrinsics.checkNotNull(rect);
            newRect = calcBounds(coordinates, rect.invoke(coordinates));
        }
        replaceRect(newRect);
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void onDetach() {
        super.onDetach();
        replaceRect(null);
    }

    private final void replaceRect(Rect newRect) {
        MutableVector<Rect> mutableVectorCurrentRects = currentRects();
        Rect it = this.androidRect;
        if (it != null) {
            mutableVectorCurrentRects.remove(it);
        }
        boolean z = false;
        if (newRect != null && !newRect.isEmpty()) {
            z = true;
        }
        if (z) {
            mutableVectorCurrentRects.add(newRect);
        }
        updateRects(mutableVectorCurrentRects);
        this.androidRect = newRect;
    }

    private final Rect calcBounds(LayoutCoordinates layoutCoordinates, androidx.compose.ui.geometry.Rect rect) {
        LayoutCoordinates root = LayoutCoordinatesKt.findRootCoordinates(layoutCoordinates);
        long topLeft = root.mo6792localPositionOfR5De75A(layoutCoordinates, rect.m5103getTopLeftF1C5BW0());
        long topRight = root.mo6792localPositionOfR5De75A(layoutCoordinates, rect.m5104getTopRightF1C5BW0());
        long bottomLeft = root.mo6792localPositionOfR5De75A(layoutCoordinates, rect.m5096getBottomLeftF1C5BW0());
        long bottomRight = root.mo6792localPositionOfR5De75A(layoutCoordinates, rect.m5097getBottomRightF1C5BW0());
        int bits$iv$iv$iv = (int) (topLeft >> 32);
        int bits$iv$iv$iv2 = (int) (topRight >> 32);
        int bits$iv$iv$iv3 = (int) (bottomLeft >> 32);
        int bits$iv$iv$iv4 = (int) (bottomRight >> 32);
        float left = ComparisonsKt.minOf(Float.intBitsToFloat(bits$iv$iv$iv), Float.intBitsToFloat(bits$iv$iv$iv2), Float.intBitsToFloat(bits$iv$iv$iv3), Float.intBitsToFloat(bits$iv$iv$iv4));
        int bits$iv$iv$iv5 = (int) (topLeft & 4294967295L);
        int bits$iv$iv$iv6 = (int) (topRight & 4294967295L);
        int bits$iv$iv$iv7 = (int) (bottomLeft & 4294967295L);
        int bits$iv$iv$iv8 = (int) (bottomRight & 4294967295L);
        float top = ComparisonsKt.minOf(Float.intBitsToFloat(bits$iv$iv$iv5), Float.intBitsToFloat(bits$iv$iv$iv6), Float.intBitsToFloat(bits$iv$iv$iv7), Float.intBitsToFloat(bits$iv$iv$iv8));
        int bits$iv$iv$iv9 = (int) (topLeft >> 32);
        int bits$iv$iv$iv10 = (int) (topRight >> 32);
        int bits$iv$iv$iv11 = (int) (bottomLeft >> 32);
        int bits$iv$iv$iv12 = (int) (bottomRight >> 32);
        float right = ComparisonsKt.maxOf(Float.intBitsToFloat(bits$iv$iv$iv9), Float.intBitsToFloat(bits$iv$iv$iv10), Float.intBitsToFloat(bits$iv$iv$iv11), Float.intBitsToFloat(bits$iv$iv$iv12));
        int bits$iv$iv$iv13 = (int) (topLeft & 4294967295L);
        float fIntBitsToFloat = Float.intBitsToFloat(bits$iv$iv$iv13);
        int bits$iv$iv$iv14 = (int) (topRight & 4294967295L);
        float fIntBitsToFloat2 = Float.intBitsToFloat(bits$iv$iv$iv14);
        int bits$iv$iv$iv15 = (int) (bottomLeft & 4294967295L);
        float fIntBitsToFloat3 = Float.intBitsToFloat(bits$iv$iv$iv15);
        int bits$iv$iv$iv16 = (int) (bottomRight & 4294967295L);
        float bottom = ComparisonsKt.maxOf(fIntBitsToFloat, fIntBitsToFloat2, fIntBitsToFloat3, Float.intBitsToFloat(bits$iv$iv$iv16));
        return new Rect(MathKt.roundToInt(left), MathKt.roundToInt(top), MathKt.roundToInt(right), MathKt.roundToInt(bottom));
    }
}
