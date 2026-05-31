package androidx.compose.ui.spatial;

import androidx.compose.ui.geometry.InlineClassHelperKt;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.graphics.Matrix;
import androidx.compose.ui.node.DelegatableNode;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntRect;
import androidx.compose.ui.unit.IntRectKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: RelativeLayoutBounds.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0007\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001BC\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0004\b\r\u0010\u000eJ\f\u0010&\u001a\b\u0012\u0004\u0012\u00020\u001f0'J\u000e\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020\u0000J&\u0010+\u001a\u00020)2\u0006\u0010,\u001a\u00020\u00192\u0006\u0010-\u001a\u00020\u00192\u0006\u0010.\u001a\u00020\u00192\u0006\u0010/\u001a\u00020\u0019J\u0006\u00100\u001a\u00020)J\u001d\u00101\u001a\u00020)2\u0006\u00102\u001a\u00020\u00062\u0006\u00103\u001a\u00020\u0006¢\u0006\u0004\b4\u00105J\u0013\u00106\u001a\u0002072\b\u00108\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u00109\u001a\u00020\u0019H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000fR\u0010\u0010\u0007\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000fR\u000e\u0010\b\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0010R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0011\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0014\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0013R\u0011\u0010\u0016\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0013R\u0011\u0010\u0018\u001a\u00020\u00198F¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u001c\u001a\u00020\u00198F¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001bR\u0011\u0010\u001e\u001a\u00020\u001f8F¢\u0006\u0006\u001a\u0004\b \u0010!R\u0011\u0010\"\u001a\u00020\u001f8F¢\u0006\u0006\u001a\u0004\b#\u0010!R\u0011\u0010$\u001a\u00020\u001f8F¢\u0006\u0006\u001a\u0004\b%\u0010!¨\u0006:"}, d2 = {"Landroidx/compose/ui/spatial/RelativeLayoutBounds;", "", "topLeft", "", "bottomRight", "windowOffset", "Landroidx/compose/ui/unit/IntOffset;", "screenOffset", "windowSize", "viewToWindowMatrix", "Landroidx/compose/ui/graphics/Matrix;", "node", "Landroidx/compose/ui/node/DelegatableNode;", "<init>", "(JJJJJ[FLandroidx/compose/ui/node/DelegatableNode;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "J", "[F", "positionInRoot", "getPositionInRoot-nOcc-ac", "()J", "positionInWindow", "getPositionInWindow-nOcc-ac", "positionInScreen", "getPositionInScreen-nOcc-ac", "width", "", "getWidth", "()I", "height", "getHeight", "boundsInRoot", "Landroidx/compose/ui/unit/IntRect;", "getBoundsInRoot", "()Landroidx/compose/ui/unit/IntRect;", "boundsInWindow", "getBoundsInWindow", "boundsInScreen", "getBoundsInScreen", "calculateOcclusions", "", "fractionVisibleIn", "", "viewport", "fractionVisibleInRect", "left", "top", "right", "bottom", "fractionVisibleInWindow", "fractionVisibleInWindowWithInsets", "topLeftInset", "bottomRightInset", "fractionVisibleInWindowWithInsets-E1MhUcY", "(JJ)F", "equals", "", "other", "hashCode", "ui"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class RelativeLayoutBounds {
    public static final int $stable = 8;
    private final long bottomRight;
    private final DelegatableNode node;
    private final long screenOffset;
    private final long topLeft;
    private final float[] viewToWindowMatrix;
    private final long windowOffset;
    private final long windowSize;

    public /* synthetic */ RelativeLayoutBounds(long j, long j2, long j3, long j4, long j5, float[] fArr, DelegatableNode delegatableNode, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, j2, j3, j4, j5, fArr, delegatableNode);
    }

    private RelativeLayoutBounds(long topLeft, long bottomRight, long windowOffset, long screenOffset, long windowSize, float[] viewToWindowMatrix, DelegatableNode node) {
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
        this.windowOffset = windowOffset;
        this.screenOffset = screenOffset;
        this.windowSize = windowSize;
        this.viewToWindowMatrix = viewToWindowMatrix;
        this.node = node;
    }

    /* JADX INFO: renamed from: getPositionInRoot-nOcc-ac, reason: not valid java name */
    public final long m7373getPositionInRootnOccac() {
        return IntOffset.m8272constructorimpl(this.topLeft);
    }

    /* JADX INFO: renamed from: getPositionInWindow-nOcc-ac, reason: not valid java name */
    public final long m7375getPositionInWindownOccac() {
        int x = IntOffset.m8278getXimpl(this.screenOffset) - IntOffset.m8278getXimpl(this.windowOffset);
        int y = IntOffset.m8279getYimpl(this.screenOffset) - IntOffset.m8279getYimpl(this.windowOffset);
        long xy$iv = this.topLeft;
        int l = (int) (xy$iv >> 32);
        long xy$iv2 = this.topLeft;
        int t = (int) xy$iv2;
        int x$iv = l + x;
        int y$iv = t + y;
        return IntOffset.m8272constructorimpl((((long) x$iv) << 32) | (((long) y$iv) & 4294967295L));
    }

    /* JADX INFO: renamed from: getPositionInScreen-nOcc-ac, reason: not valid java name */
    public final long m7374getPositionInScreennOccac() {
        int x = IntOffset.m8278getXimpl(this.screenOffset);
        int y = IntOffset.m8279getYimpl(this.screenOffset);
        long xy$iv = this.topLeft;
        int l = (int) (xy$iv >> 32);
        long xy$iv2 = this.topLeft;
        int t = (int) xy$iv2;
        int x$iv = l + x;
        int y$iv = t + y;
        return IntOffset.m8272constructorimpl((((long) x$iv) << 32) | (((long) y$iv) & 4294967295L));
    }

    public final int getWidth() {
        long xy$iv = this.topLeft;
        int l = (int) (xy$iv >> 32);
        long xy$iv2 = this.bottomRight;
        int r = (int) (xy$iv2 >> 32);
        return r - l;
    }

    public final int getHeight() {
        long xy$iv = this.topLeft;
        int t = (int) xy$iv;
        long xy$iv2 = this.bottomRight;
        int b = (int) xy$iv2;
        return b - t;
    }

    public final IntRect getBoundsInRoot() {
        long xy$iv = this.topLeft;
        int l = (int) (xy$iv >> 32);
        long xy$iv2 = this.topLeft;
        int t = (int) xy$iv2;
        long xy$iv3 = this.bottomRight;
        int $i$f$unpackX = (int) (xy$iv3 >> 32);
        long xy$iv4 = this.bottomRight;
        int b = (int) xy$iv4;
        return new IntRect(l, t, $i$f$unpackX, b);
    }

    public final IntRect getBoundsInWindow() {
        long xy$iv = this.topLeft;
        int l = (int) (xy$iv >> 32);
        long xy$iv2 = this.topLeft;
        int t = (int) xy$iv2;
        long xy$iv3 = this.bottomRight;
        int $i$f$unpackX = (int) (xy$iv3 >> 32);
        long xy$iv4 = this.bottomRight;
        int b = (int) xy$iv4;
        if (this.viewToWindowMatrix != null) {
            return IntRectKt.roundToIntRect(Matrix.m5564mapimpl(this.viewToWindowMatrix, new Rect(l, t, $i$f$unpackX, b)));
        }
        int x = IntOffset.m8278getXimpl(this.screenOffset) - IntOffset.m8278getXimpl(this.windowOffset);
        int y = IntOffset.m8279getYimpl(this.screenOffset) - IntOffset.m8279getYimpl(this.windowOffset);
        return new IntRect(l + x, t + y, $i$f$unpackX + x, b + y);
    }

    public final IntRect getBoundsInScreen() {
        if (this.viewToWindowMatrix != null) {
            IntRect windowRect = getBoundsInWindow();
            long offset = this.windowOffset;
            return new IntRect(windowRect.getLeft() + IntOffset.m8278getXimpl(offset), windowRect.getTop() + IntOffset.m8279getYimpl(offset), windowRect.getRight() + IntOffset.m8278getXimpl(offset), windowRect.getBottom() + IntOffset.m8279getYimpl(offset));
        }
        long xy$iv = this.topLeft;
        int l = (int) (xy$iv >> 32);
        long xy$iv2 = this.topLeft;
        int t = (int) xy$iv2;
        long xy$iv3 = this.bottomRight;
        int $i$f$unpackX = (int) (xy$iv3 >> 32);
        long xy$iv4 = this.bottomRight;
        int b = (int) xy$iv4;
        int x = IntOffset.m8278getXimpl(this.screenOffset);
        int y = IntOffset.m8279getYimpl(this.screenOffset);
        return new IntRect(l + x, t + y, $i$f$unpackX + x, b + y);
    }

    public final List<IntRect> calculateOcclusions() {
        int i$iv;
        RectManager rectManager;
        int l;
        int idIndex;
        RectManager rectManager2 = DelegatableNodeKt.requireOwner(this.node).getRectManager();
        int id = DelegatableNodeKt.requireLayoutNode(this.node).getSemanticsId();
        RectList rectList = rectManager2.getRects();
        int idIndex2 = rectList.indexOf(id);
        if (idIndex2 < 0) {
            return CollectionsKt.emptyList();
        }
        List $this$calculateOcclusions_u24lambda_u240 = CollectionsKt.createListBuilder();
        long[] items$iv = rectList.items;
        int size$iv = rectList.itemsSize;
        long destTopLeft$iv = items$iv[idIndex2];
        long destBottomRight$iv = items$iv[idIndex2 + 1];
        int i$iv2 = 0;
        while (true) {
            RectList rectList2 = rectList;
            if (i$iv2 < items$iv.length - 2 && i$iv2 < size$iv) {
                if (i$iv2 == idIndex2) {
                    i$iv2 += 3;
                    rectList = rectList2;
                } else {
                    long topLeft$iv = items$iv[i$iv2 + 0];
                    long bottomRight$iv = items$iv[i$iv2 + 1];
                    long a$iv$iv = ((destBottomRight$iv - topLeft$iv) - InlineClassHelperKt.Uint64Low32) | ((bottomRight$iv - destTopLeft$iv) - InlineClassHelperKt.Uint64Low32);
                    if (!((a$iv$iv & (-9223372034707292160L)) == 0)) {
                        i$iv = i$iv2;
                        rectManager = rectManager2;
                        l = id;
                        idIndex = idIndex2;
                    } else {
                        idIndex = idIndex2;
                        int l2 = (int) (topLeft$iv >> 32);
                        int t = (int) topLeft$iv;
                        int r = (int) (bottomRight$iv >> 32);
                        int b = (int) bottomRight$iv;
                        long meta$iv$iv = items$iv[i$iv2 + 2];
                        i$iv = i$iv2;
                        int i$iv3 = (int) meta$iv$iv;
                        int intersectingId = i$iv3 & 33554431;
                        if (rectManager2.isTargetDrawnFirst$ui(id, intersectingId)) {
                            rectManager = rectManager2;
                            l = id;
                            $this$calculateOcclusions_u24lambda_u240.add(new IntRect(l2, t, r, b));
                        } else {
                            rectManager = rectManager2;
                            l = id;
                        }
                    }
                    i$iv2 = i$iv + 3;
                    rectList = rectList2;
                    id = l;
                    idIndex2 = idIndex;
                    rectManager2 = rectManager;
                }
            }
        }
        return CollectionsKt.build($this$calculateOcclusions_u24lambda_u240);
    }

    public final float fractionVisibleIn(RelativeLayoutBounds viewport) {
        long tl = viewport.topLeft;
        long br = viewport.bottomRight;
        int $i$f$unpackX = (int) (br >> 32);
        return fractionVisibleInRect((int) (tl >> 32), (int) tl, $i$f$unpackX, (int) br);
    }

    public final float fractionVisibleInRect(int left, int top, int right, int bottom) {
        long xy$iv = this.topLeft;
        int l = (int) (xy$iv >> 32);
        int clippedLeft = Math.min(Math.max(l, left), right);
        long xy$iv2 = this.topLeft;
        int $i$f$unpackY = (int) xy$iv2;
        int clippedTop = Math.min(Math.max($i$f$unpackY, top), bottom);
        long xy$iv3 = this.bottomRight;
        int r = (int) (xy$iv3 >> 32);
        int clippedRight = Math.max(Math.min(r, right), left);
        long xy$iv4 = this.bottomRight;
        int b = (int) xy$iv4;
        int clippedBottom = Math.max(Math.min(b, bottom), top);
        int viewportArea = (right - left) * (bottom - top);
        int rectArea = (r - l) * (b - $i$f$unpackY);
        int clippedArea = Math.max((clippedRight - clippedLeft) * (clippedBottom - clippedTop), 0);
        int maxArea = Math.min(viewportArea, rectArea);
        return clippedArea / maxArea;
    }

    public final float fractionVisibleInWindow() {
        long windowSize = this.windowSize;
        return fractionVisibleInRect(0, 0, (int) (windowSize >> 32), (int) windowSize);
    }

    /* JADX INFO: renamed from: fractionVisibleInWindowWithInsets-E1MhUcY, reason: not valid java name */
    public final float m7372fractionVisibleInWindowWithInsetsE1MhUcY(long topLeftInset, long bottomRightInset) {
        long windowSize = this.windowSize;
        int x = IntOffset.m8278getXimpl(this.windowOffset);
        int y = IntOffset.m8279getYimpl(this.windowOffset);
        return fractionVisibleInRect(IntOffset.m8278getXimpl(topLeftInset) + x, IntOffset.m8279getYimpl(topLeftInset) + y, (((int) (windowSize >> 32)) + x) - IntOffset.m8278getXimpl(bottomRightInset), (((int) windowSize) + y) - IntOffset.m8279getYimpl(bottomRightInset));
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x0064  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean equals(java.lang.Object r7) {
        /*
            r6 = this;
            r0 = 1
            if (r6 != r7) goto L4
            return r0
        L4:
            r1 = 0
            if (r7 == 0) goto L7c
            java.lang.Class r2 = r6.getClass()
            java.lang.Class r3 = r7.getClass()
            if (r2 == r3) goto L12
            goto L7c
        L12:
            r2 = r7
            androidx.compose.ui.spatial.RelativeLayoutBounds r2 = (androidx.compose.ui.spatial.RelativeLayoutBounds) r2
            long r2 = r6.topLeft
            r4 = r7
            androidx.compose.ui.spatial.RelativeLayoutBounds r4 = (androidx.compose.ui.spatial.RelativeLayoutBounds) r4
            long r4 = r4.topLeft
            int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r2 == 0) goto L21
            return r1
        L21:
            long r2 = r6.bottomRight
            r4 = r7
            androidx.compose.ui.spatial.RelativeLayoutBounds r4 = (androidx.compose.ui.spatial.RelativeLayoutBounds) r4
            long r4 = r4.bottomRight
            int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r2 == 0) goto L2d
            return r1
        L2d:
            long r2 = r6.windowSize
            r4 = r7
            androidx.compose.ui.spatial.RelativeLayoutBounds r4 = (androidx.compose.ui.spatial.RelativeLayoutBounds) r4
            long r4 = r4.windowSize
            int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r2 == 0) goto L39
            return r1
        L39:
            long r2 = r6.windowOffset
            r4 = r7
            androidx.compose.ui.spatial.RelativeLayoutBounds r4 = (androidx.compose.ui.spatial.RelativeLayoutBounds) r4
            long r4 = r4.windowOffset
            boolean r2 = androidx.compose.ui.unit.IntOffset.m8277equalsimpl0(r2, r4)
            if (r2 != 0) goto L47
            return r1
        L47:
            long r2 = r6.screenOffset
            r4 = r7
            androidx.compose.ui.spatial.RelativeLayoutBounds r4 = (androidx.compose.ui.spatial.RelativeLayoutBounds) r4
            long r4 = r4.screenOffset
            boolean r2 = androidx.compose.ui.unit.IntOffset.m8277equalsimpl0(r2, r4)
            if (r2 != 0) goto L55
            return r1
        L55:
            float[] r2 = r6.viewToWindowMatrix
            r3 = r7
            androidx.compose.ui.spatial.RelativeLayoutBounds r3 = (androidx.compose.ui.spatial.RelativeLayoutBounds) r3
            float[] r3 = r3.viewToWindowMatrix
            if (r2 != 0) goto L62
            if (r3 != 0) goto L64
            r2 = r0
            goto L6a
        L62:
            if (r3 != 0) goto L66
        L64:
            r2 = r1
            goto L6a
        L66:
            boolean r2 = androidx.compose.ui.graphics.Matrix.m5559equalsimpl0(r2, r3)
        L6a:
            if (r2 != 0) goto L6d
            return r1
        L6d:
            androidx.compose.ui.node.DelegatableNode r2 = r6.node
            r3 = r7
            androidx.compose.ui.spatial.RelativeLayoutBounds r3 = (androidx.compose.ui.spatial.RelativeLayoutBounds) r3
            androidx.compose.ui.node.DelegatableNode r3 = r3.node
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual(r2, r3)
            if (r2 != 0) goto L7b
            return r1
        L7b:
            return r0
        L7c:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.spatial.RelativeLayoutBounds.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        int result = Long.hashCode(this.topLeft);
        int result2 = ((((((((result * 31) + Long.hashCode(this.bottomRight)) * 31) + Long.hashCode(this.windowSize)) * 31) + IntOffset.m8280hashCodeimpl(this.windowOffset)) * 31) + IntOffset.m8280hashCodeimpl(this.screenOffset)) * 31;
        float[] fArr = this.viewToWindowMatrix;
        return ((result2 + (fArr != null ? Matrix.m5561hashCodeimpl(fArr) : 0)) * 31) + this.node.hashCode();
    }
}
