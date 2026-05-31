package androidx.compose.ui.layout;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.node.LookaheadDelegate;
import androidx.compose.ui.node.NodeCoordinator;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntOffsetKt;
import androidx.compose.ui.unit.IntSize;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LookaheadLayoutCoordinates.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0002\b\u0001\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\"\u001a\u00020 2\u0006\u0010#\u001a\u00020 H\u0016¢\u0006\u0004\b$\u0010%J\u0017\u0010&\u001a\u00020 2\u0006\u0010'\u001a\u00020 H\u0016¢\u0006\u0004\b(\u0010%J\u0017\u0010)\u001a\u00020 2\u0006\u0010*\u001a\u00020 H\u0016¢\u0006\u0004\b+\u0010%J\u0017\u0010,\u001a\u00020 2\u0006\u0010'\u001a\u00020 H\u0016¢\u0006\u0004\b-\u0010%J\u0017\u0010.\u001a\u00020 2\u0006\u0010'\u001a\u00020 H\u0016¢\u0006\u0004\b/\u0010%J\u001f\u00100\u001a\u00020 2\u0006\u00101\u001a\u00020\u00012\u0006\u00102\u001a\u00020 H\u0016¢\u0006\u0004\b3\u00104J'\u00100\u001a\u00020 2\u0006\u00101\u001a\u00020\u00012\u0006\u00102\u001a\u00020 2\u0006\u00105\u001a\u00020\u001bH\u0016¢\u0006\u0004\b6\u00107J\u0018\u00108\u001a\u0002092\u0006\u00101\u001a\u00020\u00012\u0006\u0010:\u001a\u00020\u001bH\u0016J\u001f\u0010;\u001a\u00020<2\u0006\u00101\u001a\u00020\u00012\u0006\u0010=\u001a\u00020>H\u0016¢\u0006\u0004\b?\u0010@J\u0017\u0010A\u001a\u00020<2\u0006\u0010=\u001a\u00020>H\u0016¢\u0006\u0004\bB\u0010CJ\u0011\u0010D\u001a\u00020E2\u0006\u0010F\u001a\u00020\u0012H\u0096\u0002R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u00118VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u0016\u0010\u0015\u001a\u0004\u0018\u00010\u00018VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u0016\u0010\u0018\u001a\u0004\u0018\u00010\u00018VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u0017R\u0014\u0010\u001a\u001a\u00020\u001b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001cR\u0014\u0010\u001d\u001a\u00020\u001b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001cR\u0014\u0010\u001f\u001a\u00020 8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b!\u0010\u000f¨\u0006G"}, d2 = {"Landroidx/compose/ui/layout/LookaheadLayoutCoordinates;", "Landroidx/compose/ui/layout/LayoutCoordinates;", "lookaheadDelegate", "Landroidx/compose/ui/node/LookaheadDelegate;", "<init>", "(Landroidx/compose/ui/node/LookaheadDelegate;)V", "getLookaheadDelegate", "()Landroidx/compose/ui/node/LookaheadDelegate;", "coordinator", "Landroidx/compose/ui/node/NodeCoordinator;", "getCoordinator", "()Landroidx/compose/ui/node/NodeCoordinator;", "size", "Landroidx/compose/ui/unit/IntSize;", "getSize-YbymL2g", "()J", "providedAlignmentLines", "", "Landroidx/compose/ui/layout/AlignmentLine;", "getProvidedAlignmentLines", "()Ljava/util/Set;", "parentLayoutCoordinates", "getParentLayoutCoordinates", "()Landroidx/compose/ui/layout/LayoutCoordinates;", "parentCoordinates", "getParentCoordinates", "isAttached", "", "()Z", "introducesMotionFrameOfReference", "getIntroducesMotionFrameOfReference", "lookaheadOffset", "Landroidx/compose/ui/geometry/Offset;", "getLookaheadOffset-F1C5BW0", "screenToLocal", "relativeToScreen", "screenToLocal-MK-Hz9U", "(J)J", "localToScreen", "relativeToLocal", "localToScreen-MK-Hz9U", "windowToLocal", "relativeToWindow", "windowToLocal-MK-Hz9U", "localToWindow", "localToWindow-MK-Hz9U", "localToRoot", "localToRoot-MK-Hz9U", "localPositionOf", "sourceCoordinates", "relativeToSource", "localPositionOf-R5De75A", "(Landroidx/compose/ui/layout/LayoutCoordinates;J)J", "includeMotionFrameOfReference", "localPositionOf-S_NoaFU", "(Landroidx/compose/ui/layout/LayoutCoordinates;JZ)J", "localBoundingBoxOf", "Landroidx/compose/ui/geometry/Rect;", "clipBounds", "transformFrom", "", "matrix", "Landroidx/compose/ui/graphics/Matrix;", "transformFrom-EL8BTi8", "(Landroidx/compose/ui/layout/LayoutCoordinates;[F)V", "transformToScreen", "transformToScreen-58bKbWc", "([F)V", "get", "", "alignmentLine", "ui"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class LookaheadLayoutCoordinates implements LayoutCoordinates {
    public static final int $stable = 0;
    private final LookaheadDelegate lookaheadDelegate;

    public LookaheadLayoutCoordinates(LookaheadDelegate lookaheadDelegate) {
        this.lookaheadDelegate = lookaheadDelegate;
    }

    public final LookaheadDelegate getLookaheadDelegate() {
        return this.lookaheadDelegate;
    }

    public final NodeCoordinator getCoordinator() {
        return this.lookaheadDelegate.getCoordinator();
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    /* JADX INFO: renamed from: getSize-YbymL2g */
    public long mo6791getSizeYbymL2g() {
        LookaheadDelegate it = this.lookaheadDelegate;
        int width$iv = it.getWidth();
        int height$iv = it.getHeight();
        return IntSize.m8316constructorimpl((((long) width$iv) << 32) | (((long) height$iv) & 4294967295L));
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    public Set<AlignmentLine> getProvidedAlignmentLines() {
        return getCoordinator().getProvidedAlignmentLines();
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    public LayoutCoordinates getParentLayoutCoordinates() {
        LookaheadDelegate lookaheadDelegate;
        boolean value$iv = isAttached();
        if (!value$iv) {
            InlineClassHelperKt.throwIllegalStateException(NodeCoordinator.ExpectAttachedLayoutCoordinates);
        }
        NodeCoordinator it = getCoordinator().getLayoutNode().getOuterCoordinator$ui().getWrappedBy();
        if (it == null || (lookaheadDelegate = it.getLookaheadDelegate()) == null) {
            return null;
        }
        return lookaheadDelegate.getCoordinates();
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    public LayoutCoordinates getParentCoordinates() {
        LookaheadDelegate lookaheadDelegate;
        boolean value$iv = isAttached();
        if (!value$iv) {
            InlineClassHelperKt.throwIllegalStateException(NodeCoordinator.ExpectAttachedLayoutCoordinates);
        }
        NodeCoordinator wrappedBy = getCoordinator().getWrappedBy();
        if (wrappedBy == null || (lookaheadDelegate = wrappedBy.getLookaheadDelegate()) == null) {
            return null;
        }
        return lookaheadDelegate.getCoordinates();
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    public boolean isAttached() {
        return getCoordinator().isAttached();
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    public boolean getIntroducesMotionFrameOfReference() {
        return this.lookaheadDelegate.getIsPlacedUnderMotionFrameOfReference();
    }

    /* JADX INFO: renamed from: getLookaheadOffset-F1C5BW0, reason: not valid java name */
    private final long m6812getLookaheadOffsetF1C5BW0() {
        LookaheadDelegate it = LookaheadLayoutCoordinatesKt.getRootLookaheadDelegate(this.lookaheadDelegate);
        return Offset.m5072minusMKHz9U(mo6792localPositionOfR5De75A(it.getCoordinates(), Offset.INSTANCE.m5084getZeroF1C5BW0()), getCoordinator().mo6792localPositionOfR5De75A(it.getCoordinator(), Offset.INSTANCE.m5084getZeroF1C5BW0()));
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    /* JADX INFO: renamed from: screenToLocal-MK-Hz9U */
    public long mo6797screenToLocalMKHz9U(long relativeToScreen) {
        return Offset.m5073plusMKHz9U(getCoordinator().mo6797screenToLocalMKHz9U(relativeToScreen), m6812getLookaheadOffsetF1C5BW0());
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    /* JADX INFO: renamed from: localToScreen-MK-Hz9U */
    public long mo6795localToScreenMKHz9U(long relativeToLocal) {
        return getCoordinator().mo6795localToScreenMKHz9U(Offset.m5073plusMKHz9U(relativeToLocal, m6812getLookaheadOffsetF1C5BW0()));
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    /* JADX INFO: renamed from: windowToLocal-MK-Hz9U */
    public long mo6800windowToLocalMKHz9U(long relativeToWindow) {
        return Offset.m5073plusMKHz9U(getCoordinator().mo6800windowToLocalMKHz9U(relativeToWindow), m6812getLookaheadOffsetF1C5BW0());
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    /* JADX INFO: renamed from: localToWindow-MK-Hz9U */
    public long mo6796localToWindowMKHz9U(long relativeToLocal) {
        return getCoordinator().mo6796localToWindowMKHz9U(Offset.m5073plusMKHz9U(relativeToLocal, m6812getLookaheadOffsetF1C5BW0()));
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    /* JADX INFO: renamed from: localToRoot-MK-Hz9U */
    public long mo6794localToRootMKHz9U(long relativeToLocal) {
        return getCoordinator().mo6794localToRootMKHz9U(Offset.m5073plusMKHz9U(relativeToLocal, m6812getLookaheadOffsetF1C5BW0()));
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    /* JADX INFO: renamed from: localPositionOf-R5De75A */
    public long mo6792localPositionOfR5De75A(LayoutCoordinates sourceCoordinates, long relativeToSource) {
        return mo6793localPositionOfS_NoaFU(sourceCoordinates, relativeToSource, true);
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    /* JADX INFO: renamed from: localPositionOf-S_NoaFU */
    public long mo6793localPositionOfS_NoaFU(LayoutCoordinates sourceCoordinates, long relativeToSource, boolean includeMotionFrameOfReference) {
        if (!(sourceCoordinates instanceof LookaheadLayoutCoordinates)) {
            LookaheadDelegate rootDelegate = LookaheadLayoutCoordinatesKt.getRootLookaheadDelegate(this.lookaheadDelegate);
            long jMo6793localPositionOfS_NoaFU = mo6793localPositionOfS_NoaFU(rootDelegate.getLookaheadLayoutCoordinates(), relativeToSource, includeMotionFrameOfReference);
            long $this$toOffset_u2d_u2dgyyYBs$iv = rootDelegate.getPosition();
            float x$iv$iv = IntOffset.m8278getXimpl($this$toOffset_u2d_u2dgyyYBs$iv);
            float y$iv$iv = IntOffset.m8279getYimpl($this$toOffset_u2d_u2dgyyYBs$iv);
            long v1$iv$iv$iv = Float.floatToRawIntBits(x$iv$iv);
            long v1$iv$iv$iv2 = Float.floatToRawIntBits(y$iv$iv);
            long v2$iv$iv$iv = (v1$iv$iv$iv << 32) | (v1$iv$iv$iv2 & 4294967295L);
            long localLookaheadPos = Offset.m5072minusMKHz9U(jMo6793localPositionOfS_NoaFU, Offset.m5060constructorimpl(v2$iv$iv$iv));
            LayoutCoordinates rootDelegateCoordinates = rootDelegate.getCoordinator().getParentCoordinates();
            if (rootDelegateCoordinates == null) {
                rootDelegateCoordinates = rootDelegate.getCoordinator().getCoordinates();
            }
            long localPos = rootDelegateCoordinates.mo6793localPositionOfS_NoaFU(sourceCoordinates, Offset.INSTANCE.m5084getZeroF1C5BW0(), includeMotionFrameOfReference);
            return Offset.m5073plusMKHz9U(localLookaheadPos, localPos);
        }
        LookaheadDelegate source = ((LookaheadLayoutCoordinates) sourceCoordinates).lookaheadDelegate;
        source.getCoordinator().onCoordinatesUsed$ui();
        NodeCoordinator commonAncestor = getCoordinator().findCommonAncestor$ui(source.getCoordinator());
        LookaheadDelegate ancestor = commonAncestor.getLookaheadDelegate();
        if (ancestor != null) {
            long sourceInCommonAncestor = IntOffset.m8282plusqkQi6aY(source.m7039positionIniSbpLlY$ui(ancestor, !includeMotionFrameOfReference), IntOffsetKt.m8295roundk4lQ0M(relativeToSource));
            long lookaheadPosInAncestor = this.lookaheadDelegate.m7039positionIniSbpLlY$ui(ancestor, !includeMotionFrameOfReference);
            long $this$toOffset_u2d_u2dgyyYBs$iv2 = IntOffset.m8281minusqkQi6aY(sourceInCommonAncestor, lookaheadPosInAncestor);
            float x$iv$iv2 = IntOffset.m8278getXimpl($this$toOffset_u2d_u2dgyyYBs$iv2);
            float y$iv$iv2 = IntOffset.m8279getYimpl($this$toOffset_u2d_u2dgyyYBs$iv2);
            long v1$iv$iv$iv3 = Float.floatToRawIntBits(x$iv$iv2);
            long v1$iv$iv$iv4 = Float.floatToRawIntBits(y$iv$iv2);
            long v2$iv$iv$iv2 = (v1$iv$iv$iv3 << 32) | (v1$iv$iv$iv4 & 4294967295L);
            return Offset.m5060constructorimpl(v2$iv$iv$iv2);
        }
        LookaheadDelegate sourceRoot = LookaheadLayoutCoordinatesKt.getRootLookaheadDelegate(source);
        long sourcePosition = IntOffset.m8282plusqkQi6aY(IntOffset.m8282plusqkQi6aY(source.m7039positionIniSbpLlY$ui(sourceRoot, !includeMotionFrameOfReference), sourceRoot.getPosition()), IntOffsetKt.m8295roundk4lQ0M(relativeToSource));
        LookaheadDelegate rootDelegate2 = LookaheadLayoutCoordinatesKt.getRootLookaheadDelegate(this.lookaheadDelegate);
        long lookaheadPosition = IntOffset.m8282plusqkQi6aY(this.lookaheadDelegate.m7039positionIniSbpLlY$ui(rootDelegate2, !includeMotionFrameOfReference), rootDelegate2.getPosition());
        long $this$toOffset_u2d_u2dgyyYBs$iv3 = IntOffset.m8281minusqkQi6aY(sourcePosition, lookaheadPosition);
        float x$iv$iv3 = IntOffset.m8278getXimpl($this$toOffset_u2d_u2dgyyYBs$iv3);
        float y$iv$iv3 = IntOffset.m8279getYimpl($this$toOffset_u2d_u2dgyyYBs$iv3);
        long v1$iv$iv$iv5 = Float.floatToRawIntBits(x$iv$iv3);
        long v2$iv$iv$iv3 = Float.floatToRawIntBits(y$iv$iv3);
        long relativePosition = Offset.m5060constructorimpl((v1$iv$iv$iv5 << 32) | (v2$iv$iv$iv3 & 4294967295L));
        NodeCoordinator wrappedBy = rootDelegate2.getCoordinator().getWrappedBy();
        Intrinsics.checkNotNull(wrappedBy);
        NodeCoordinator wrappedBy2 = sourceRoot.getCoordinator().getWrappedBy();
        Intrinsics.checkNotNull(wrappedBy2);
        return wrappedBy.mo6793localPositionOfS_NoaFU(wrappedBy2, relativePosition, includeMotionFrameOfReference);
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    public Rect localBoundingBoxOf(LayoutCoordinates sourceCoordinates, boolean clipBounds) {
        return getCoordinator().localBoundingBoxOf(sourceCoordinates, clipBounds);
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    /* JADX INFO: renamed from: transformFrom-EL8BTi8 */
    public void mo6798transformFromEL8BTi8(LayoutCoordinates sourceCoordinates, float[] matrix) {
        getCoordinator().mo6798transformFromEL8BTi8(sourceCoordinates, matrix);
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    /* JADX INFO: renamed from: transformToScreen-58bKbWc */
    public void mo6799transformToScreen58bKbWc(float[] matrix) {
        getCoordinator().mo6799transformToScreen58bKbWc(matrix);
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    public int get(AlignmentLine alignmentLine) {
        return this.lookaheadDelegate.get(alignmentLine);
    }
}
