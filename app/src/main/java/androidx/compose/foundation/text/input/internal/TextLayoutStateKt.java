package androidx.compose.foundation.text.input.internal;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Metadata;

/* JADX INFO: compiled from: TextLayoutState.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\u001a\u001b\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0000¢\u0006\u0004\b\u0004\u0010\u0005\u001a\u001b\u0010\u0006\u001a\u00020\u0001*\u00020\u00072\u0006\u0010\b\u001a\u00020\u0001H\u0000¢\u0006\u0004\b\t\u0010\n\u001a\u001b\u0010\u000b\u001a\u00020\u0001*\u00020\u00072\u0006\u0010\b\u001a\u00020\u0001H\u0000¢\u0006\u0004\b\f\u0010\n\u001a\u001b\u0010\r\u001a\u00020\u0001*\u00020\u00072\u0006\u0010\b\u001a\u00020\u0001H\u0000¢\u0006\u0004\b\u000e\u0010\n\u001a\u0014\u0010\u000f\u001a\u00020\u0003*\u00020\u00072\u0006\u0010\u0002\u001a\u00020\u0003H\u0000¨\u0006\u0010"}, d2 = {"coerceIn", "Landroidx/compose/ui/geometry/Offset;", "rect", "Landroidx/compose/ui/geometry/Rect;", "coerceIn-3MmeM6k", "(JLandroidx/compose/ui/geometry/Rect;)J", "fromTextLayoutToCore", "Landroidx/compose/foundation/text/input/internal/TextLayoutState;", TypedValues.CycleType.S_WAVE_OFFSET, "fromTextLayoutToCore-Uv8p0NA", "(Landroidx/compose/foundation/text/input/internal/TextLayoutState;J)J", "fromDecorationToTextLayout", "fromDecorationToTextLayout-Uv8p0NA", "fromWindowToDecoration", "fromWindowToDecoration-Uv8p0NA", "fromTextLayoutToDecoration", "foundation"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class TextLayoutStateKt {
    /* JADX INFO: renamed from: coerceIn-3MmeM6k, reason: not valid java name */
    public static final long m1883coerceIn3MmeM6k(long $this$coerceIn_u2d3MmeM6k, Rect rect) {
        float xOffset;
        float yOffset;
        int bits$iv$iv$iv = (int) ($this$coerceIn_u2d3MmeM6k >> 32);
        if (Float.intBitsToFloat(bits$iv$iv$iv) < rect.getLeft()) {
            xOffset = rect.getLeft();
        } else {
            int bits$iv$iv$iv2 = (int) ($this$coerceIn_u2d3MmeM6k >> 32);
            if (Float.intBitsToFloat(bits$iv$iv$iv2) > rect.getRight()) {
                xOffset = rect.getRight();
            } else {
                int bits$iv$iv$iv3 = (int) ($this$coerceIn_u2d3MmeM6k >> 32);
                xOffset = Float.intBitsToFloat(bits$iv$iv$iv3);
            }
        }
        int bits$iv$iv$iv4 = (int) ($this$coerceIn_u2d3MmeM6k & 4294967295L);
        if (Float.intBitsToFloat(bits$iv$iv$iv4) < rect.getTop()) {
            yOffset = rect.getTop();
        } else {
            int bits$iv$iv$iv5 = (int) ($this$coerceIn_u2d3MmeM6k & 4294967295L);
            if (Float.intBitsToFloat(bits$iv$iv$iv5) > rect.getBottom()) {
                yOffset = rect.getBottom();
            } else {
                int bits$iv$iv$iv6 = (int) ($this$coerceIn_u2d3MmeM6k & 4294967295L);
                yOffset = Float.intBitsToFloat(bits$iv$iv$iv6);
            }
        }
        float y$iv = yOffset;
        float x$iv = xOffset;
        long v1$iv$iv = Float.floatToRawIntBits(x$iv);
        long v2$iv$iv = Float.floatToRawIntBits(y$iv);
        return Offset.m5060constructorimpl((v1$iv$iv << 32) | (4294967295L & v2$iv$iv));
    }

    /* JADX INFO: renamed from: fromTextLayoutToCore-Uv8p0NA, reason: not valid java name */
    public static final long m1885fromTextLayoutToCoreUv8p0NA(TextLayoutState $this$fromTextLayoutToCore_u2dUv8p0NA, long offset) {
        LayoutCoordinates textLayoutNodeCoordinates = $this$fromTextLayoutToCore_u2dUv8p0NA.getTextLayoutNodeCoordinates();
        if (textLayoutNodeCoordinates != null) {
            Offset offsetM5057boximpl = null;
            if (!textLayoutNodeCoordinates.isAttached()) {
                textLayoutNodeCoordinates = null;
            }
            if (textLayoutNodeCoordinates != null) {
                LayoutCoordinates it = $this$fromTextLayoutToCore_u2dUv8p0NA.getCoreNodeCoordinates();
                if (it != null) {
                    if (!it.isAttached()) {
                        it = null;
                    }
                    if (it != null) {
                        offsetM5057boximpl = Offset.m5057boximpl(it.mo6792localPositionOfR5De75A(textLayoutNodeCoordinates, offset));
                    }
                }
                if (offsetM5057boximpl != null) {
                    return offsetM5057boximpl.m5078unboximpl();
                }
            }
        }
        return offset;
    }

    /* JADX INFO: renamed from: fromDecorationToTextLayout-Uv8p0NA, reason: not valid java name */
    public static final long m1884fromDecorationToTextLayoutUv8p0NA(TextLayoutState $this$fromDecorationToTextLayout_u2dUv8p0NA, long offset) {
        Offset offsetM5057boximpl;
        long jMo6792localPositionOfR5De75A;
        LayoutCoordinates textLayoutNodeCoordinates = $this$fromDecorationToTextLayout_u2dUv8p0NA.getTextLayoutNodeCoordinates();
        if (textLayoutNodeCoordinates != null) {
            LayoutCoordinates decoratorNodeCoordinates = $this$fromDecorationToTextLayout_u2dUv8p0NA.getDecoratorNodeCoordinates();
            if (decoratorNodeCoordinates != null) {
                if (textLayoutNodeCoordinates.isAttached() && decoratorNodeCoordinates.isAttached()) {
                    jMo6792localPositionOfR5De75A = textLayoutNodeCoordinates.mo6792localPositionOfR5De75A(decoratorNodeCoordinates, offset);
                } else {
                    jMo6792localPositionOfR5De75A = offset;
                }
                offsetM5057boximpl = Offset.m5057boximpl(jMo6792localPositionOfR5De75A);
            } else {
                offsetM5057boximpl = null;
            }
            if (offsetM5057boximpl != null) {
                return offsetM5057boximpl.m5078unboximpl();
            }
        }
        return offset;
    }

    /* JADX INFO: renamed from: fromWindowToDecoration-Uv8p0NA, reason: not valid java name */
    public static final long m1886fromWindowToDecorationUv8p0NA(TextLayoutState $this$fromWindowToDecoration_u2dUv8p0NA, long offset) {
        LayoutCoordinates decoratorNodeCoordinates = $this$fromWindowToDecoration_u2dUv8p0NA.getDecoratorNodeCoordinates();
        if (decoratorNodeCoordinates != null) {
            if (decoratorNodeCoordinates.isAttached()) {
                return decoratorNodeCoordinates.mo6800windowToLocalMKHz9U(offset);
            }
            return offset;
        }
        return offset;
    }

    public static final Rect fromTextLayoutToDecoration(TextLayoutState $this$fromTextLayoutToDecoration, Rect rect) {
        LayoutCoordinates it;
        LayoutCoordinates textLayoutNode = $this$fromTextLayoutToDecoration.getTextLayoutNodeCoordinates();
        if (textLayoutNode != null) {
            if (!textLayoutNode.isAttached()) {
                textLayoutNode = null;
            }
            if (textLayoutNode != null && (it = $this$fromTextLayoutToDecoration.getDecoratorNodeCoordinates()) != null) {
                LayoutCoordinates decoratorNode = it.isAttached() ? it : null;
                if (decoratorNode != null) {
                    long topLeft = decoratorNode.localBoundingBoxOf(textLayoutNode, false).m5103getTopLeftF1C5BW0();
                    return rect.m5105translatek4lQ0M(topLeft);
                }
            }
            return rect;
        }
        return rect;
    }
}
