package androidx.compose.ui.node;

import androidx.collection.MutableObjectIntMap;
import androidx.collection.ObjectIntMapKt;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.layout.AlignmentLine;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LookaheadLayoutCoordinates;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.LayoutDirection;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LookaheadDelegate.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000²\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u000e\b!\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0015\u0010J\u001a\u00020\u001a2\u0006\u0010K\u001a\u00020\u0019H\u0000¢\u0006\u0002\bLJ\r\u0010M\u001a\u00020NH\u0010¢\u0006\u0002\bOJ:\u0010P\u001a\u00020N2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010Q\u001a\u00020%2\u0019\u0010R\u001a\u0015\u0012\u0004\u0012\u00020T\u0012\u0004\u0012\u00020N\u0018\u00010S¢\u0006\u0002\bUH\u0004¢\u0006\u0004\bV\u0010WJ\u0017\u0010X\u001a\u00020N2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002¢\u0006\u0004\bY\u0010\u0015J\u0017\u0010Z\u001a\u00020N2\u0006\u0010\u0010\u001a\u00020\u0011H\u0000¢\u0006\u0004\b[\u0010\u0015J\b\u0010\\\u001a\u00020NH\u0014J&\u0010]\u001a\u00020^2\u0006\u00107\u001a\u0002082\f\u0010_\u001a\b\u0012\u0004\u0012\u00020\u001c0`H\u0086\b¢\u0006\u0004\ba\u0010bJ\u0010\u0010g\u001a\u00020\u001a2\u0006\u0010h\u001a\u00020\u001aH\u0016J\u0010\u0010i\u001a\u00020\u001a2\u0006\u0010h\u001a\u00020\u001aH\u0016J\u0010\u0010j\u001a\u00020\u001a2\u0006\u0010k\u001a\u00020\u001aH\u0016J\u0010\u0010l\u001a\u00020\u001a2\u0006\u0010k\u001a\u00020\u001aH\u0016J\u001f\u0010m\u001a\u00020\u00112\u0006\u0010n\u001a\u00020\u00002\u0006\u0010o\u001a\u00020\rH\u0000¢\u0006\u0004\bp\u0010qR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0016\u0010\t\u001a\u0004\u0018\u00010\u00028VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u001c\u0010\u0010\u001a\u00020\u0011X\u0096\u000e¢\u0006\u0010\n\u0002\u0010\u0016\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001c\u0010\u0017\u001a\u0010\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u001a\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u001b\u001a\u00020\u001c8PX\u0090\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001eR\u0014\u0010\u001f\u001a\u00020\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010\u000fR\u0014\u0010 \u001a\u00020!8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010#R\u0014\u0010$\u001a\u00020%8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b&\u0010'R\u0014\u0010(\u001a\u00020%8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b)\u0010'R\u0016\u0010*\u001a\u0004\u0018\u00010\u00028VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b+\u0010\u000bR\u0014\u0010,\u001a\u00020-8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b.\u0010/R\u0014\u00100\u001a\u0002018VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b2\u00103R\u0014\u00104\u001a\u0002058@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b6\u0010\u0013R\u0014\u00107\u001a\u0002088@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b9\u0010\u0013R\u0011\u0010:\u001a\u00020;¢\u0006\b\n\u0000\u001a\u0004\b<\u0010=R\u0014\u0010>\u001a\u00020?8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b@\u0010AR\"\u0010C\u001a\u0004\u0018\u00010\u001c2\b\u0010B\u001a\u0004\u0018\u00010\u001c@BX\u0082\u000e¢\u0006\b\n\u0000\"\u0004\bD\u0010ER\u001a\u0010F\u001a\b\u0012\u0004\u0012\u00020\u00190GX\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\bH\u0010IR\u0016\u0010c\u001a\u0004\u0018\u00010d8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\be\u0010f¨\u0006r"}, d2 = {"Landroidx/compose/ui/node/LookaheadDelegate;", "Landroidx/compose/ui/layout/Measurable;", "Landroidx/compose/ui/node/LookaheadCapablePlaceable;", "coordinator", "Landroidx/compose/ui/node/NodeCoordinator;", "<init>", "(Landroidx/compose/ui/node/NodeCoordinator;)V", "getCoordinator", "()Landroidx/compose/ui/node/NodeCoordinator;", "child", "getChild", "()Landroidx/compose/ui/node/LookaheadCapablePlaceable;", "hasMeasureResult", "", "getHasMeasureResult", "()Z", "position", "Landroidx/compose/ui/unit/IntOffset;", "getPosition-nOcc-ac", "()J", "setPosition--gyyYBs", "(J)V", "J", "oldAlignmentLines", "", "Landroidx/compose/ui/layout/AlignmentLine;", "", "measureResult", "Landroidx/compose/ui/layout/MeasureResult;", "getMeasureResult$ui", "()Landroidx/compose/ui/layout/MeasureResult;", "isLookingAhead", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "getLayoutDirection", "()Landroidx/compose/ui/unit/LayoutDirection;", "density", "", "getDensity", "()F", "fontScale", "getFontScale", "parent", "getParent", "layoutNode", "Landroidx/compose/ui/node/LayoutNode;", "getLayoutNode", "()Landroidx/compose/ui/node/LayoutNode;", "coordinates", "Landroidx/compose/ui/layout/LayoutCoordinates;", "getCoordinates", "()Landroidx/compose/ui/layout/LayoutCoordinates;", "size", "Landroidx/compose/ui/unit/IntSize;", "getSize-YbymL2g$ui", "constraints", "Landroidx/compose/ui/unit/Constraints;", "getConstraints-msEJaDk$ui", "lookaheadLayoutCoordinates", "Landroidx/compose/ui/layout/LookaheadLayoutCoordinates;", "getLookaheadLayoutCoordinates", "()Landroidx/compose/ui/layout/LookaheadLayoutCoordinates;", "alignmentLinesOwner", "Landroidx/compose/ui/node/AlignmentLinesOwner;", "getAlignmentLinesOwner", "()Landroidx/compose/ui/node/AlignmentLinesOwner;", "result", "_measureResult", "set_measureResult", "(Landroidx/compose/ui/layout/MeasureResult;)V", "cachedAlignmentLinesMap", "Landroidx/collection/MutableObjectIntMap;", "getCachedAlignmentLinesMap", "()Landroidx/collection/MutableObjectIntMap;", "getCachedAlignmentLine", "alignmentLine", "getCachedAlignmentLine$ui", "replace", "", "replace$ui", "placeAt", "zIndex", "layerBlock", "Lkotlin/Function1;", "Landroidx/compose/ui/graphics/GraphicsLayerScope;", "Lkotlin/ExtensionFunctionType;", "placeAt-f8xVGno", "(JFLkotlin/jvm/functions/Function1;)V", "placeSelf", "placeSelf--gyyYBs", "placeSelfApparentToRealOffset", "placeSelfApparentToRealOffset--gyyYBs$ui", "placeChildren", "performingMeasure", "Landroidx/compose/ui/layout/Placeable;", "block", "Lkotlin/Function0;", "performingMeasure-K40F9xA", "(JLkotlin/jvm/functions/Function0;)Landroidx/compose/ui/layout/Placeable;", "parentData", "", "getParentData", "()Ljava/lang/Object;", "minIntrinsicWidth", "height", "maxIntrinsicWidth", "minIntrinsicHeight", "width", "maxIntrinsicHeight", "positionIn", "ancestor", "excludingAgnosticOffset", "positionIn-iSbpLlY$ui", "(Landroidx/compose/ui/node/LookaheadDelegate;Z)J", "ui"}, k = 1, mv = {2, 1, 0}, xi = 48)
public abstract class LookaheadDelegate extends LookaheadCapablePlaceable implements Measurable {
    public static final int $stable = 0;
    private MeasureResult _measureResult;
    private final NodeCoordinator coordinator;
    private Map<AlignmentLine, Integer> oldAlignmentLines;
    private long position = IntOffset.INSTANCE.m8289getZeronOccac();
    private final LookaheadLayoutCoordinates lookaheadLayoutCoordinates = new LookaheadLayoutCoordinates(this);
    private final MutableObjectIntMap<AlignmentLine> cachedAlignmentLinesMap = ObjectIntMapKt.mutableObjectIntMapOf();

    public LookaheadDelegate(NodeCoordinator coordinator) {
        this.coordinator = coordinator;
    }

    public final NodeCoordinator getCoordinator() {
        return this.coordinator;
    }

    @Override // androidx.compose.ui.node.LookaheadCapablePlaceable
    public LookaheadCapablePlaceable getChild() {
        NodeCoordinator wrapped = this.coordinator.getWrapped();
        return wrapped != null ? wrapped.getLookaheadDelegate() : null;
    }

    @Override // androidx.compose.ui.node.LookaheadCapablePlaceable
    public boolean getHasMeasureResult() {
        return this._measureResult != null;
    }

    @Override // androidx.compose.ui.node.LookaheadCapablePlaceable
    /* JADX INFO: renamed from: getPosition-nOcc-ac, reason: from getter */
    public long getPosition() {
        return this.position;
    }

    /* JADX INFO: renamed from: setPosition--gyyYBs, reason: not valid java name */
    public void m7040setPositiongyyYBs(long j) {
        this.position = j;
    }

    @Override // androidx.compose.ui.node.LookaheadCapablePlaceable
    public MeasureResult getMeasureResult$ui() {
        MeasureResult measureResult = this._measureResult;
        if (measureResult == null) {
            InlineClassHelperKt.throwIllegalStateExceptionForNullCheck("LookaheadDelegate has not been measured yet when measureResult is requested.");
            throw new KotlinNothingValueException();
        }
        return measureResult;
    }

    @Override // androidx.compose.ui.node.LookaheadCapablePlaceable, androidx.compose.ui.layout.IntrinsicMeasureScope
    public boolean isLookingAhead() {
        return true;
    }

    @Override // androidx.compose.ui.layout.IntrinsicMeasureScope
    public LayoutDirection getLayoutDirection() {
        return this.coordinator.getLayoutDirection();
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: getDensity */
    public float get_density() {
        return this.coordinator.get_density();
    }

    @Override // androidx.compose.ui.unit.FontScaling
    /* JADX INFO: renamed from: getFontScale */
    public float get_fontScale() {
        return this.coordinator.get_fontScale();
    }

    @Override // androidx.compose.ui.node.LookaheadCapablePlaceable
    public LookaheadCapablePlaceable getParent() {
        NodeCoordinator wrappedBy = this.coordinator.getWrappedBy();
        return wrappedBy != null ? wrappedBy.getLookaheadDelegate() : null;
    }

    @Override // androidx.compose.ui.node.LookaheadCapablePlaceable, androidx.compose.ui.node.MeasureScopeWithLayoutNode
    public LayoutNode getLayoutNode() {
        return this.coordinator.getLayoutNode();
    }

    @Override // androidx.compose.ui.node.LookaheadCapablePlaceable
    public LayoutCoordinates getCoordinates() {
        return this.lookaheadLayoutCoordinates;
    }

    /* JADX INFO: renamed from: getSize-YbymL2g$ui, reason: not valid java name */
    public final long m7036getSizeYbymL2g$ui() {
        int width$iv = getWidth();
        int height$iv = getHeight();
        return IntSize.m8316constructorimpl((((long) width$iv) << 32) | (((long) height$iv) & 4294967295L));
    }

    /* JADX INFO: renamed from: getConstraints-msEJaDk$ui, reason: not valid java name */
    public final long m7035getConstraintsmsEJaDk$ui() {
        return getMeasurementConstraints();
    }

    public final LookaheadLayoutCoordinates getLookaheadLayoutCoordinates() {
        return this.lookaheadLayoutCoordinates;
    }

    @Override // androidx.compose.ui.node.LookaheadCapablePlaceable
    public AlignmentLinesOwner getAlignmentLinesOwner() {
        AlignmentLinesOwner lookaheadAlignmentLinesOwner$ui = this.coordinator.getLayoutNode().getLayoutDelegate().getLookaheadAlignmentLinesOwner$ui();
        Intrinsics.checkNotNull(lookaheadAlignmentLinesOwner$ui);
        return lookaheadAlignmentLinesOwner$ui;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void set_measureResult(MeasureResult result) {
        if (result != null) {
            int width$iv = result.getWidth();
            int height$iv = result.getHeight();
            m6847setMeasuredSizeozmzZPI(IntSize.m8316constructorimpl((((long) width$iv) << 32) | (((long) height$iv) & 4294967295L)));
        } else {
            LookaheadDelegate $this$_set__measureResult__u24lambda_u241 = this;
            $this$_set__measureResult__u24lambda_u241.m6847setMeasuredSizeozmzZPI(IntSize.INSTANCE.m8326getZeroYbymL2g());
        }
        if (!Intrinsics.areEqual(this._measureResult, result) && result != null) {
            Map<AlignmentLine, Integer> map = this.oldAlignmentLines;
            if ((!(map == null || map.isEmpty()) || !result.getAlignmentLines().isEmpty()) && !Intrinsics.areEqual(result.getAlignmentLines(), this.oldAlignmentLines)) {
                getAlignmentLinesOwner().getAlignmentLines().onAlignmentsChanged();
                LinkedHashMap linkedHashMap = this.oldAlignmentLines;
                if (linkedHashMap == null) {
                    linkedHashMap = new LinkedHashMap();
                    this.oldAlignmentLines = linkedHashMap;
                }
                linkedHashMap.clear();
                linkedHashMap.putAll(result.getAlignmentLines());
            }
        }
        this._measureResult = result;
    }

    protected final MutableObjectIntMap<AlignmentLine> getCachedAlignmentLinesMap() {
        return this.cachedAlignmentLinesMap;
    }

    public final int getCachedAlignmentLine$ui(AlignmentLine alignmentLine) {
        return this.cachedAlignmentLinesMap.getOrDefault(alignmentLine, Integer.MIN_VALUE);
    }

    @Override // androidx.compose.ui.node.LookaheadCapablePlaceable
    public void replace$ui() {
        mo6784placeAtf8xVGno(getPosition(), 0.0f, (Function1<? super GraphicsLayerScope, Unit>) null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.compose.ui.layout.Placeable
    /* JADX INFO: renamed from: placeAt-f8xVGno */
    public final void mo6784placeAtf8xVGno(long position, float zIndex, Function1<? super GraphicsLayerScope, Unit> layerBlock) {
        m7034placeSelfgyyYBs(position);
        if (getIsShallowPlacing()) {
            return;
        }
        placeChildren();
    }

    /* JADX INFO: renamed from: placeSelf--gyyYBs, reason: not valid java name */
    private final void m7034placeSelfgyyYBs(long position) {
        if (!IntOffset.m8277equalsimpl0(getPosition(), position)) {
            m7040setPositiongyyYBs(position);
            LookaheadPassDelegate lookaheadPassDelegate = getLayoutNode().getLayoutDelegate().getLookaheadPassDelegate();
            if (lookaheadPassDelegate != null) {
                lookaheadPassDelegate.notifyChildrenUsingLookaheadCoordinatesWhilePlacing();
            }
            invalidateAlignmentLinesFromPositionChange(this.coordinator);
        }
        if (!getIsPlacingForAlignment()) {
            captureRulersIfNeeded$ui(getMeasureResult$ui());
        }
    }

    /* JADX INFO: renamed from: placeSelfApparentToRealOffset--gyyYBs$ui, reason: not valid java name */
    public final void m7038placeSelfApparentToRealOffsetgyyYBs$ui(long position) {
        m7034placeSelfgyyYBs(IntOffset.m8282plusqkQi6aY(position, getApparentToRealOffset()));
    }

    protected void placeChildren() {
        getMeasureResult$ui().placeChildren();
    }

    /* JADX INFO: renamed from: performingMeasure-K40F9xA, reason: not valid java name */
    public final Placeable m7037performingMeasureK40F9xA(long constraints, Function0<? extends MeasureResult> block) {
        m6848setMeasurementConstraintsBRTryo0(constraints);
        set_measureResult(block.invoke());
        return this;
    }

    @Override // androidx.compose.ui.layout.Measured, androidx.compose.ui.layout.IntrinsicMeasurable
    public Object getParentData() {
        return this.coordinator.getParentData();
    }

    public int minIntrinsicWidth(int height) {
        NodeCoordinator wrapped = this.coordinator.getWrapped();
        Intrinsics.checkNotNull(wrapped);
        LookaheadDelegate lookaheadDelegate = wrapped.getLookaheadDelegate();
        Intrinsics.checkNotNull(lookaheadDelegate);
        return lookaheadDelegate.minIntrinsicWidth(height);
    }

    public int maxIntrinsicWidth(int height) {
        NodeCoordinator wrapped = this.coordinator.getWrapped();
        Intrinsics.checkNotNull(wrapped);
        LookaheadDelegate lookaheadDelegate = wrapped.getLookaheadDelegate();
        Intrinsics.checkNotNull(lookaheadDelegate);
        return lookaheadDelegate.maxIntrinsicWidth(height);
    }

    public int minIntrinsicHeight(int width) {
        NodeCoordinator wrapped = this.coordinator.getWrapped();
        Intrinsics.checkNotNull(wrapped);
        LookaheadDelegate lookaheadDelegate = wrapped.getLookaheadDelegate();
        Intrinsics.checkNotNull(lookaheadDelegate);
        return lookaheadDelegate.minIntrinsicHeight(width);
    }

    public int maxIntrinsicHeight(int width) {
        NodeCoordinator wrapped = this.coordinator.getWrapped();
        Intrinsics.checkNotNull(wrapped);
        LookaheadDelegate lookaheadDelegate = wrapped.getLookaheadDelegate();
        Intrinsics.checkNotNull(lookaheadDelegate);
        return lookaheadDelegate.maxIntrinsicHeight(width);
    }

    /* JADX INFO: renamed from: positionIn-iSbpLlY$ui, reason: not valid java name */
    public final long m7039positionIniSbpLlY$ui(LookaheadDelegate ancestor, boolean excludingAgnosticOffset) {
        long aggregatedOffset = IntOffset.INSTANCE.m8289getZeronOccac();
        LookaheadDelegate lookaheadDelegate = this;
        while (!Intrinsics.areEqual(lookaheadDelegate, ancestor)) {
            if (!lookaheadDelegate.getIsPlacedUnderMotionFrameOfReference() || !excludingAgnosticOffset) {
                aggregatedOffset = IntOffset.m8282plusqkQi6aY(aggregatedOffset, lookaheadDelegate.getPosition());
            }
            NodeCoordinator wrappedBy = lookaheadDelegate.coordinator.getWrappedBy();
            Intrinsics.checkNotNull(wrappedBy);
            LookaheadDelegate lookaheadDelegate2 = wrappedBy.getLookaheadDelegate();
            Intrinsics.checkNotNull(lookaheadDelegate2);
            lookaheadDelegate = lookaheadDelegate2;
        }
        return aggregatedOffset;
    }
}
