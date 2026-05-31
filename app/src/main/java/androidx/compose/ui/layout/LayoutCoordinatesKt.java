package androidx.compose.ui.layout;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.node.NodeCoordinator;
import kotlin.Metadata;

/* JADX INFO: compiled from: LayoutCoordinates.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\u001a\u000f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002¢\u0006\u0002\u0010\u0003\u001a\u000f\u0010\u0004\u001a\u00020\u0001*\u00020\u0002¢\u0006\u0002\u0010\u0003\u001a\u000f\u0010\u0005\u001a\u00020\u0001*\u00020\u0002¢\u0006\u0002\u0010\u0003\u001a\n\u0010\u0006\u001a\u00020\u0007*\u00020\u0002\u001a\f\u0010\b\u001a\u00020\u0007*\u00020\u0002H\u0007\u001a\u0014\u0010\b\u001a\u00020\u0007*\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\n\u001a\u000f\u0010\u000b\u001a\u00020\u0001*\u00020\u0002¢\u0006\u0002\u0010\u0003\u001a\n\u0010\f\u001a\u00020\u0007*\u00020\u0002\u001a\n\u0010\r\u001a\u00020\u0002*\u00020\u0002¨\u0006\u000e"}, d2 = {"positionInRoot", "Landroidx/compose/ui/geometry/Offset;", "Landroidx/compose/ui/layout/LayoutCoordinates;", "(Landroidx/compose/ui/layout/LayoutCoordinates;)J", "positionInWindow", "positionOnScreen", "boundsInRoot", "Landroidx/compose/ui/geometry/Rect;", "boundsInWindow", "clipBounds", "", "positionInParent", "boundsInParent", "findRootCoordinates", "ui"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class LayoutCoordinatesKt {
    public static final long positionInRoot(LayoutCoordinates $this$positionInRoot) {
        return $this$positionInRoot.mo6794localToRootMKHz9U(Offset.INSTANCE.m5084getZeroF1C5BW0());
    }

    public static final long positionInWindow(LayoutCoordinates $this$positionInWindow) {
        return $this$positionInWindow.mo6796localToWindowMKHz9U(Offset.INSTANCE.m5084getZeroF1C5BW0());
    }

    public static final long positionOnScreen(LayoutCoordinates $this$positionOnScreen) {
        return $this$positionOnScreen.mo6795localToScreenMKHz9U(Offset.INSTANCE.m5084getZeroF1C5BW0());
    }

    public static final Rect boundsInRoot(LayoutCoordinates $this$boundsInRoot) {
        return LayoutCoordinates.localBoundingBoxOf$default(findRootCoordinates($this$boundsInRoot), $this$boundsInRoot, false, 2, null);
    }

    public static /* synthetic */ Rect boundsInWindow$default(LayoutCoordinates layoutCoordinates, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        return boundsInWindow(layoutCoordinates, z);
    }

    public static final Rect boundsInWindow(LayoutCoordinates $this$boundsInWindow, boolean clipBounds) {
        float maximumValue$iv$iv;
        float $this$fastCoerceAtLeast$iv$iv;
        float maximumValue$iv$iv2;
        float maximumValue$iv$iv3;
        LayoutCoordinates root = findRootCoordinates($this$boundsInWindow);
        long arg0$iv = root.mo6791getSizeYbymL2g();
        float rootWidth = (int) (arg0$iv >> 32);
        long arg0$iv2 = root.mo6791getSizeYbymL2g();
        float rootHeight = (int) (arg0$iv2 & 4294967295L);
        Rect bounds = root.localBoundingBoxOf($this$boundsInWindow, clipBounds);
        if (!clipBounds) {
            maximumValue$iv$iv = bounds.getLeft();
        } else {
            float $this$fastCoerceIn$iv = bounds.getLeft();
            float minimumValue$iv$iv = 0.0f;
            if ($this$fastCoerceIn$iv >= 0.0f) {
                minimumValue$iv$iv = $this$fastCoerceIn$iv;
            }
            maximumValue$iv$iv = rootWidth;
            if (minimumValue$iv$iv <= maximumValue$iv$iv) {
                maximumValue$iv$iv = minimumValue$iv$iv;
            }
        }
        if (clipBounds) {
            $this$fastCoerceAtLeast$iv$iv = bounds.getTop();
            if ($this$fastCoerceAtLeast$iv$iv < 0.0f) {
                $this$fastCoerceAtLeast$iv$iv = 0.0f;
            }
            if ($this$fastCoerceAtLeast$iv$iv > rootHeight) {
                $this$fastCoerceAtLeast$iv$iv = rootHeight;
            }
        } else {
            $this$fastCoerceAtLeast$iv$iv = bounds.getTop();
        }
        if (!clipBounds) {
            maximumValue$iv$iv2 = bounds.getRight();
        } else {
            float $this$fastCoerceIn$iv2 = bounds.getRight();
            float minimumValue$iv$iv2 = 0.0f;
            if ($this$fastCoerceIn$iv2 >= 0.0f) {
                minimumValue$iv$iv2 = $this$fastCoerceIn$iv2;
            }
            maximumValue$iv$iv2 = rootWidth;
            if (minimumValue$iv$iv2 <= maximumValue$iv$iv2) {
                maximumValue$iv$iv2 = minimumValue$iv$iv2;
            }
        }
        if (!clipBounds) {
            maximumValue$iv$iv3 = bounds.getBottom();
        } else {
            float $this$fastCoerceIn$iv3 = bounds.getBottom();
            float minimumValue$iv$iv3 = 0.0f;
            if ($this$fastCoerceIn$iv3 >= 0.0f) {
                minimumValue$iv$iv3 = $this$fastCoerceIn$iv3;
            }
            maximumValue$iv$iv3 = rootHeight;
            if (minimumValue$iv$iv3 <= maximumValue$iv$iv3) {
                maximumValue$iv$iv3 = minimumValue$iv$iv3;
            }
        }
        if (!(maximumValue$iv$iv == maximumValue$iv$iv2)) {
            if (!($this$fastCoerceAtLeast$iv$iv == maximumValue$iv$iv3)) {
                float y$iv = $this$fastCoerceAtLeast$iv$iv;
                float x$iv = maximumValue$iv$iv;
                long v1$iv$iv = Float.floatToRawIntBits(x$iv);
                long v2$iv$iv = Float.floatToRawIntBits(y$iv);
                long topLeft = root.mo6796localToWindowMKHz9U(Offset.m5060constructorimpl((v1$iv$iv << 32) | (v2$iv$iv & 4294967295L)));
                float x$iv2 = maximumValue$iv$iv2;
                long v1$iv$iv2 = Float.floatToRawIntBits(x$iv2);
                long v2$iv$iv2 = (v1$iv$iv2 << 32) | (((long) Float.floatToRawIntBits(y$iv)) & 4294967295L);
                long topRight = root.mo6796localToWindowMKHz9U(Offset.m5060constructorimpl(v2$iv$iv2));
                float y$iv2 = maximumValue$iv$iv3;
                float x$iv3 = maximumValue$iv$iv2;
                long v1$iv$iv3 = Float.floatToRawIntBits(x$iv3);
                long v2$iv$iv3 = (v1$iv$iv3 << 32) | (((long) Float.floatToRawIntBits(y$iv2)) & 4294967295L);
                long bottomRight = root.mo6796localToWindowMKHz9U(Offset.m5060constructorimpl(v2$iv$iv3));
                float x$iv4 = maximumValue$iv$iv;
                long v1$iv$iv4 = Float.floatToRawIntBits(x$iv4);
                long v2$iv$iv4 = (v1$iv$iv4 << 32) | (((long) Float.floatToRawIntBits(y$iv2)) & 4294967295L);
                long bottomLeft = root.mo6796localToWindowMKHz9U(Offset.m5060constructorimpl(v2$iv$iv4));
                int bits$iv$iv$iv = (int) (topLeft >> 32);
                float topLeftX = Float.intBitsToFloat(bits$iv$iv$iv);
                int bits$iv$iv$iv2 = (int) (topRight >> 32);
                float topRightX = Float.intBitsToFloat(bits$iv$iv$iv2);
                int bits$iv$iv$iv3 = (int) (bottomLeft >> 32);
                float bottomLeftX = Float.intBitsToFloat(bits$iv$iv$iv3);
                int bits$iv$iv$iv4 = (int) (bottomRight >> 32);
                float bottomRightX = Float.intBitsToFloat(bits$iv$iv$iv4);
                float left = Math.min(topLeftX, Math.min(topRightX, Math.min(bottomLeftX, bottomRightX)));
                float right = Math.max(topLeftX, Math.max(topRightX, Math.max(bottomLeftX, bottomRightX)));
                int bits$iv$iv$iv5 = (int) (topLeft & 4294967295L);
                float topLeftY = Float.intBitsToFloat(bits$iv$iv$iv5);
                int bits$iv$iv$iv6 = (int) (topRight & 4294967295L);
                float topRightY = Float.intBitsToFloat(bits$iv$iv$iv6);
                int bits$iv$iv$iv7 = (int) (bottomLeft & 4294967295L);
                float bottomLeftY = Float.intBitsToFloat(bits$iv$iv$iv7);
                int bits$iv$iv$iv8 = (int) (bottomRight & 4294967295L);
                float bottomRightY = Float.intBitsToFloat(bits$iv$iv$iv8);
                float top = Math.min(topLeftY, Math.min(topRightY, Math.min(bottomLeftY, bottomRightY)));
                float bottom = Math.max(topLeftY, Math.max(topRightY, Math.max(bottomLeftY, bottomRightY)));
                return new Rect(left, top, right, bottom);
            }
        }
        return Rect.INSTANCE.getZero();
    }

    public static final long positionInParent(LayoutCoordinates $this$positionInParent) {
        LayoutCoordinates parentLayoutCoordinates = $this$positionInParent.getParentLayoutCoordinates();
        return parentLayoutCoordinates != null ? parentLayoutCoordinates.mo6792localPositionOfR5De75A($this$positionInParent, Offset.INSTANCE.m5084getZeroF1C5BW0()) : Offset.INSTANCE.m5084getZeroF1C5BW0();
    }

    public static final Rect boundsInParent(LayoutCoordinates $this$boundsInParent) {
        Rect rectLocalBoundingBoxOf$default;
        LayoutCoordinates parentLayoutCoordinates = $this$boundsInParent.getParentLayoutCoordinates();
        if (parentLayoutCoordinates != null && (rectLocalBoundingBoxOf$default = LayoutCoordinates.localBoundingBoxOf$default(parentLayoutCoordinates, $this$boundsInParent, false, 2, null)) != null) {
            return rectLocalBoundingBoxOf$default;
        }
        long arg0$iv = $this$boundsInParent.mo6791getSizeYbymL2g();
        long arg0$iv2 = $this$boundsInParent.mo6791getSizeYbymL2g();
        return new Rect(0.0f, 0.0f, (int) (arg0$iv >> 32), (int) (4294967295L & arg0$iv2));
    }

    public static final LayoutCoordinates findRootCoordinates(LayoutCoordinates $this$findRootCoordinates) {
        LayoutCoordinates root = $this$findRootCoordinates;
        LayoutCoordinates parent = root.getParentLayoutCoordinates();
        while (parent != null) {
            root = parent;
            parent = root.getParentLayoutCoordinates();
        }
        NodeCoordinator rootCoordinator = root instanceof NodeCoordinator ? (NodeCoordinator) root : null;
        if (rootCoordinator == null) {
            return root;
        }
        for (NodeCoordinator parentCoordinator = rootCoordinator.getWrappedBy(); parentCoordinator != null; parentCoordinator = parentCoordinator.getWrappedBy()) {
            rootCoordinator = parentCoordinator;
        }
        return rootCoordinator;
    }
}
