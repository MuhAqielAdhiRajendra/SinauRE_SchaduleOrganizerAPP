package androidx.compose.ui.node;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.graphics.layer.GraphicsLayer;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.layout.AlignmentLine;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntSize;
import java.util.List;
import java.util.Map;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LookaheadPassDelegate.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000Æ\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010 \n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u0000\n\u0002\b9\b\u0001\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004:\u0002½\u0001B\u000f\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\r\u0010\u001a\u001a\u00020\u001bH\u0000¢\u0006\u0002\b\u001cJ\r\u0010\u001d\u001a\u00020\u001bH\u0000¢\u0006\u0002\b\u001eJ\r\u0010\\\u001a\u00020\u001bH\u0000¢\u0006\u0002\b]J\u001d\u0010i\u001a\u00020\u001b2\u0012\u0010j\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u001b0KH\u0082\bJ\b\u0010m\u001a\u00020\u001bH\u0016J\b\u0010p\u001a\u00020\u001bH\u0002J\u0015\u0010q\u001a\u00020\u001b2\u0006\u0010r\u001a\u00020\nH\u0000¢\u0006\u0002\bsJ\u0014\u0010t\u001a\u000e\u0012\u0004\u0012\u00020v\u0012\u0004\u0012\u00020!0uH\u0016J\u001c\u0010z\u001a\u00020\u001b2\u0012\u0010j\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u001b0KH\u0016J\b\u0010{\u001a\u00020\u001bH\u0016J\b\u0010|\u001a\u00020\u001bH\u0016J\u0006\u0010}\u001a\u00020\u001bJ\u0019\u0010~\u001a\u00020\u00012\u0006\u0010\u007f\u001a\u00020AH\u0016¢\u0006\u0006\b\u0080\u0001\u0010\u0081\u0001J\u0012\u0010\u0082\u0001\u001a\u00020\u001b2\u0007\u0010\u0083\u0001\u001a\u00020\u0017H\u0002J\u001a\u0010\u008d\u0001\u001a\u00020\u001b2\u0006\u0010\u007f\u001a\u00020AH\u0000¢\u0006\u0006\b\u008e\u0001\u0010\u008f\u0001J\u0018\u0010\u0090\u0001\u001a\u00020\n2\u0006\u0010\u007f\u001a\u00020A¢\u0006\u0006\b\u0091\u0001\u0010\u0092\u0001J@\u0010\u0093\u0001\u001a\u00020\u001b2\u0007\u0010\u0094\u0001\u001a\u00020F2\u0007\u0010\u0095\u0001\u001a\u00020I2\u001a\u0010\u0096\u0001\u001a\u0015\u0012\u0004\u0012\u00020L\u0012\u0004\u0012\u00020\u001b\u0018\u00010K¢\u0006\u0002\bMH\u0014¢\u0006\u0006\b\u0097\u0001\u0010\u0098\u0001J-\u0010\u0093\u0001\u001a\u00020\u001b2\u0007\u0010\u0094\u0001\u001a\u00020F2\u0007\u0010\u0095\u0001\u001a\u00020I2\u0007\u0010\u0099\u0001\u001a\u00020OH\u0014¢\u0006\u0006\b\u0097\u0001\u0010\u009a\u0001J\u0012\u0010\u009d\u0001\u001a\u00020\u001b2\u0007\u0010\u009e\u0001\u001a\u00020\nH\u0016JK\u0010 \u0001\u001a\u00020\u001b2\u0007\u0010\u0094\u0001\u001a\u00020F2\u0007\u0010\u0095\u0001\u001a\u00020I2\u001a\u0010\u0096\u0001\u001a\u0015\u0012\u0004\u0012\u00020L\u0012\u0004\u0012\u00020\u001b\u0018\u00010K¢\u0006\u0002\bM2\t\u0010\u0099\u0001\u001a\u0004\u0018\u00010OH\u0002¢\u0006\u0006\b¡\u0001\u0010¢\u0001J\u0013\u0010§\u0001\u001a\u00020!2\u0007\u0010¨\u0001\u001a\u00020vH\u0096\u0002J\u0012\u0010©\u0001\u001a\u00020!2\u0007\u0010ª\u0001\u001a\u00020!H\u0016J\u0012\u0010«\u0001\u001a\u00020!2\u0007\u0010ª\u0001\u001a\u00020!H\u0016J\u0012\u0010¬\u0001\u001a\u00020!2\u0007\u0010\u00ad\u0001\u001a\u00020!H\u0016J\u0012\u0010®\u0001\u001a\u00020!2\u0007\u0010\u00ad\u0001\u001a\u00020!H\u0016J\t\u0010¯\u0001\u001a\u00020\u001bH\u0002J\u0010\u0010°\u0001\u001a\u00020\u001b2\u0007\u0010±\u0001\u001a\u00020\nJ\u0007\u0010²\u0001\u001a\u00020\u001bJ\u0007\u0010³\u0001\u001a\u00020\nJ\u000f\u0010µ\u0001\u001a\u00020\u001bH\u0000¢\u0006\u0003\b¶\u0001J\t\u0010·\u0001\u001a\u00020\u001bH\u0002J\t\u0010¸\u0001\u001a\u00020\u001bH\u0002J\t\u0010¹\u0001\u001a\u00020\u001bH\u0002J\u0007\u0010º\u0001\u001a\u00020\u001bJ\u0007\u0010»\u0001\u001a\u00020\u001bJ\u0007\u0010¼\u0001\u001a\u00020\u001bR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n8B@BX\u0082\u000e¢\u0006\f\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR$\u0010\u0010\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n8B@BX\u0082\u000e¢\u0006\f\u001a\u0004\b\u0011\u0010\r\"\u0004\b\u0012\u0010\u000fR$\u0010\u0013\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n8B@BX\u0082\u000e¢\u0006\f\u001a\u0004\b\u0014\u0010\r\"\u0004\b\u0015\u0010\u000fR\u0014\u0010\u0016\u001a\u00020\u00178BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u000e\u0010\u001f\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020!X\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010\"\u001a\u00020!2\u0006\u0010\t\u001a\u00020!@PX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u001a\u0010'\u001a\u00020(X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R\u0014\u0010-\u001a\u00020.8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b/\u00100R\u0014\u00101\u001a\u0002028BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b3\u00104R$\u00106\u001a\u0002052\u0006\u0010\t\u001a\u0002058B@BX\u0082\u000e¢\u0006\f\u001a\u0004\b7\u00108\"\u0004\b9\u0010:R\u000e\u0010;\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010<\u001a\u00020\nX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b=\u0010\r\"\u0004\b>\u0010\u000fR\u000e\u0010?\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0013\u0010@\u001a\u0004\u0018\u00010A8F¢\u0006\u0006\u001a\u0004\bB\u0010CR\u0010\u0010D\u001a\u0004\u0018\u00010AX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010E\u001a\u00020FX\u0082\u000e¢\u0006\u0004\n\u0002\u0010GR\u000e\u0010H\u001a\u00020IX\u0082\u000e¢\u0006\u0002\n\u0000R!\u0010J\u001a\u0015\u0012\u0004\u0012\u00020L\u0012\u0004\u0012\u00020\u001b\u0018\u00010K¢\u0006\u0002\bMX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010N\u001a\u0004\u0018\u00010OX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010P\u001a\u00020\n8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bQ\u0010\rR\u000e\u0010R\u001a\u00020SX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010T\u001a\u0002028VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bU\u00104R\u0014\u0010V\u001a\u00020WX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bX\u0010YR\u0014\u0010Z\u001a\b\u0012\u0004\u0012\u00020\u00000[X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010^\u001a\u00020\n8F¢\u0006\u0006\u001a\u0004\b_\u0010\rR\u001a\u0010`\u001a\u00020\nX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\ba\u0010\r\"\u0004\bb\u0010\u000fR\u001a\u0010c\u001a\b\u0012\u0004\u0012\u00020\u00000d8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\be\u0010fR\u001e\u0010g\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\bh\u0010\rR\u0014\u0010k\u001a\b\u0012\u0004\u0012\u00020\u001b0lX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010n\u001a\u00020\n8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bo\u0010\rR\u0016\u0010w\u001a\u0004\u0018\u00010\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bx\u0010yR\u000f\u0010\u0084\u0001\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R'\u0010\u0086\u0001\u001a\u0005\u0018\u00010\u0085\u00012\t\u0010\t\u001a\u0005\u0018\u00010\u0085\u0001@RX\u0096\u000e¢\u0006\n\n\u0000\u001a\u0006\b\u0087\u0001\u0010\u0088\u0001R\u0011\u0010\u0089\u0001\u001a\u00020AX\u0082\u000e¢\u0006\u0004\n\u0002\u0010GR\u001d\u0010\u008a\u0001\u001a\b\u0012\u0004\u0012\u00020\u001b0lX\u0080\u0004¢\u0006\n\n\u0000\u001a\u0006\b\u008b\u0001\u0010\u008c\u0001R\u001d\u0010\u009b\u0001\u001a\u00020\nX\u0096\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u009b\u0001\u0010\r\"\u0005\b\u009c\u0001\u0010\u000fR\u0015\u0010\u009f\u0001\u001a\b\u0012\u0004\u0012\u00020\u001b0lX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010£\u0001\u001a\u00020!8VX\u0096\u0004¢\u0006\u0007\u001a\u0005\b¤\u0001\u0010$R\u0016\u0010¥\u0001\u001a\u00020!8VX\u0096\u0004¢\u0006\u0007\u001a\u0005\b¦\u0001\u0010$R\u000f\u0010´\u0001\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006¾\u0001"}, d2 = {"Landroidx/compose/ui/node/LookaheadPassDelegate;", "Landroidx/compose/ui/layout/Placeable;", "Landroidx/compose/ui/layout/Measurable;", "Landroidx/compose/ui/node/AlignmentLinesOwner;", "Landroidx/compose/ui/node/MotionReferencePlacementDelegate;", "layoutNodeLayoutDelegate", "Landroidx/compose/ui/node/LayoutNodeLayoutDelegate;", "<init>", "(Landroidx/compose/ui/node/LayoutNodeLayoutDelegate;)V", "value", "", "measurePending", "getMeasurePending", "()Z", "setMeasurePending", "(Z)V", "layoutPending", "getLayoutPending", "setLayoutPending", "layoutPendingForAlignment", "getLayoutPendingForAlignment", "setLayoutPendingForAlignment", "layoutNode", "Landroidx/compose/ui/node/LayoutNode;", "getLayoutNode", "()Landroidx/compose/ui/node/LayoutNode;", "markLayoutPending", "", "markLayoutPending$ui", "markMeasurePending", "markMeasurePending$ui", "relayoutWithoutParentInProgress", "previousPlaceOrder", "", "placeOrder", "getPlaceOrder", "()I", "setPlaceOrder$ui", "(I)V", "measuredByParent", "Landroidx/compose/ui/node/LayoutNode$UsageByParent;", "getMeasuredByParent$ui", "()Landroidx/compose/ui/node/LayoutNode$UsageByParent;", "setMeasuredByParent$ui", "(Landroidx/compose/ui/node/LayoutNode$UsageByParent;)V", "measurePassDelegate", "Landroidx/compose/ui/node/MeasurePassDelegate;", "getMeasurePassDelegate$ui", "()Landroidx/compose/ui/node/MeasurePassDelegate;", "outerCoordinator", "Landroidx/compose/ui/node/NodeCoordinator;", "getOuterCoordinator", "()Landroidx/compose/ui/node/NodeCoordinator;", "Landroidx/compose/ui/node/LayoutNode$LayoutState;", "layoutState", "getLayoutState", "()Landroidx/compose/ui/node/LayoutNode$LayoutState;", "setLayoutState", "(Landroidx/compose/ui/node/LayoutNode$LayoutState;)V", "duringAlignmentLinesQuery", "placedOnce", "getPlacedOnce$ui", "setPlacedOnce$ui", "measuredOnce", "lastConstraints", "Landroidx/compose/ui/unit/Constraints;", "getLastConstraints-DWUhwKw", "()Landroidx/compose/ui/unit/Constraints;", "lookaheadConstraints", "lastPosition", "Landroidx/compose/ui/unit/IntOffset;", "J", "lastZIndex", "", "lastLayerBlock", "Lkotlin/Function1;", "Landroidx/compose/ui/graphics/GraphicsLayerScope;", "Lkotlin/ExtensionFunctionType;", "lastExplicitLayer", "Landroidx/compose/ui/graphics/layer/GraphicsLayer;", "isPlaced", "isPlaced$ui", "_placedState", "Landroidx/compose/ui/node/LookaheadPassDelegate$PlacedState;", "innerCoordinator", "getInnerCoordinator", "alignmentLines", "Landroidx/compose/ui/node/AlignmentLines;", "getAlignmentLines", "()Landroidx/compose/ui/node/AlignmentLines;", "_childDelegates", "Landroidx/compose/runtime/collection/MutableVector;", "onApproachPlacement", "onApproachPlacement$ui", "needsToBePlacedInApproach", "getNeedsToBePlacedInApproach", "childDelegatesDirty", "getChildDelegatesDirty$ui", "setChildDelegatesDirty$ui", "childDelegates", "", "getChildDelegates$ui", "()Ljava/util/List;", "layingOutChildren", "getLayingOutChildren", "forEachChildDelegate", "block", "layoutChildrenBlock", "Lkotlin/Function0;", "layoutChildren", "detachedFromParentLookaheadPlacement", "getDetachedFromParentLookaheadPlacement", "checkChildrenPlaceOrderForUpdates", "markNodeAndSubtreeAsNotPlaced", "inLookahead", "markNodeAndSubtreeAsNotPlaced$ui", "calculateAlignmentLines", "", "Landroidx/compose/ui/layout/AlignmentLine;", "parentAlignmentLinesOwner", "getParentAlignmentLinesOwner", "()Landroidx/compose/ui/node/AlignmentLinesOwner;", "forEachChildAlignmentLinesOwner", "requestLayout", "requestMeasure", "notifyChildrenUsingLookaheadCoordinatesWhilePlacing", "measure", "constraints", "measure-BRTryo0", "(J)Landroidx/compose/ui/layout/Placeable;", "trackLookaheadMeasurementByParent", "node", "parentDataDirty", "", "parentData", "getParentData", "()Ljava/lang/Object;", "performMeasureConstraints", "performMeasureBlock", "getPerformMeasureBlock$ui", "()Lkotlin/jvm/functions/Function0;", "performMeasure", "performMeasure-BRTryo0$ui", "(J)V", "remeasure", "remeasure-BRTryo0", "(J)Z", "placeAt", "position", "zIndex", "layerBlock", "placeAt-f8xVGno", "(JFLkotlin/jvm/functions/Function1;)V", "layer", "(JFLandroidx/compose/ui/graphics/layer/GraphicsLayer;)V", "isPlacedUnderMotionFrameOfReference", "setPlacedUnderMotionFrameOfReference", "updatePlacedUnderMotionFrameOfReference", "newMFR", "layoutModifierBlock", "placeSelf", "placeSelf-MLgxB_4", "(JFLkotlin/jvm/functions/Function1;Landroidx/compose/ui/graphics/layer/GraphicsLayer;)V", "measuredWidth", "getMeasuredWidth", "measuredHeight", "getMeasuredHeight", "get", "alignmentLine", "minIntrinsicWidth", "height", "maxIntrinsicWidth", "minIntrinsicHeight", "width", "maxIntrinsicHeight", "onIntrinsicsQueried", "invalidateIntrinsicsParent", "forceRequest", "invalidateParentData", "updateParentData", "onNodePlacedCalled", "onNodePlaced", "onNodePlaced$ui", "clearPlaceOrder", "markNodeAndSubtreeAsPlaced", "onBeforeLayoutChildren", "replace", "onNodeDetached", "onAttachedToNullParent", "PlacedState", "ui"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class LookaheadPassDelegate extends Placeable implements Measurable, AlignmentLinesOwner, MotionReferencePlacementDelegate {
    public static final int $stable = 8;
    private boolean duringAlignmentLinesQuery;
    private boolean isPlacedUnderMotionFrameOfReference;
    private GraphicsLayer lastExplicitLayer;
    private Function1<? super GraphicsLayerScope, Unit> lastLayerBlock;
    private float lastZIndex;
    private boolean layingOutChildren;
    private final LayoutNodeLayoutDelegate layoutNodeLayoutDelegate;
    private Constraints lookaheadConstraints;
    private boolean measuredOnce;
    private boolean onNodePlacedCalled;
    private boolean placedOnce;
    private boolean relayoutWithoutParentInProgress;
    private int previousPlaceOrder = Integer.MAX_VALUE;
    private int placeOrder = Integer.MAX_VALUE;
    private LayoutNode.UsageByParent measuredByParent = LayoutNode.UsageByParent.NotUsed;
    private long lastPosition = IntOffset.INSTANCE.m8289getZeronOccac();
    private PlacedState _placedState = PlacedState.IsNotPlaced;
    private final AlignmentLines alignmentLines = new LookaheadAlignmentLines(this);
    private final MutableVector<LookaheadPassDelegate> _childDelegates = new MutableVector<>(new LookaheadPassDelegate[16], 0);
    private boolean childDelegatesDirty = true;
    private final Function0<Unit> layoutChildrenBlock = new Function0<Unit>() { // from class: androidx.compose.ui.node.LookaheadPassDelegate$layoutChildrenBlock$1
        {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Unit invoke() {
            invoke2();
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2() {
            this.this$0.clearPlaceOrder();
            this.this$0.forEachChildAlignmentLinesOwner(new Function1<AlignmentLinesOwner, Unit>() { // from class: androidx.compose.ui.node.LookaheadPassDelegate$layoutChildrenBlock$1.1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(AlignmentLinesOwner alignmentLinesOwner) {
                    invoke2(alignmentLinesOwner);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(AlignmentLinesOwner child) {
                    child.getAlignmentLines().setUsedDuringParentLayout$ui(false);
                }
            });
            LookaheadDelegate lookaheadDelegate = this.this$0.getInnerCoordinator().getLookaheadDelegate();
            if (lookaheadDelegate != null) {
                boolean forAlignment = lookaheadDelegate.getIsPlacingForAlignment();
                List<LayoutNode> children$ui = this.this$0.getLayoutNode().getChildren$ui();
                int size = children$ui.size();
                for (int index$iv = 0; index$iv < size; index$iv++) {
                    Object item$iv = children$ui.get(index$iv);
                    LayoutNode it = (LayoutNode) item$iv;
                    LookaheadDelegate lookaheadDelegate2 = it.getOuterCoordinator$ui().getLookaheadDelegate();
                    if (lookaheadDelegate2 != null) {
                        lookaheadDelegate2.setPlacingForAlignment$ui(forAlignment);
                    }
                }
            }
            LookaheadDelegate lookaheadDelegate3 = this.this$0.getInnerCoordinator().getLookaheadDelegate();
            Intrinsics.checkNotNull(lookaheadDelegate3);
            lookaheadDelegate3.getMeasureResult$ui().placeChildren();
            LookaheadDelegate lookaheadDelegate4 = this.this$0.getInnerCoordinator().getLookaheadDelegate();
            if (lookaheadDelegate4 != null) {
                lookaheadDelegate4.getIsPlacingForAlignment();
                List<LayoutNode> children$ui2 = this.this$0.getLayoutNode().getChildren$ui();
                int size2 = children$ui2.size();
                for (int index$iv2 = 0; index$iv2 < size2; index$iv2++) {
                    Object item$iv2 = children$ui2.get(index$iv2);
                    LayoutNode it2 = (LayoutNode) item$iv2;
                    LookaheadDelegate lookaheadDelegate5 = it2.getOuterCoordinator$ui().getLookaheadDelegate();
                    if (lookaheadDelegate5 != null) {
                        lookaheadDelegate5.setPlacingForAlignment$ui(false);
                    }
                }
            }
            this.this$0.checkChildrenPlaceOrderForUpdates();
            this.this$0.forEachChildAlignmentLinesOwner(new Function1<AlignmentLinesOwner, Unit>() { // from class: androidx.compose.ui.node.LookaheadPassDelegate$layoutChildrenBlock$1.4
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(AlignmentLinesOwner alignmentLinesOwner) {
                    invoke2(alignmentLinesOwner);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(AlignmentLinesOwner child) {
                    child.getAlignmentLines().setPreviousUsedDuringParentLayout$ui(child.getAlignmentLines().getUsedDuringParentLayout());
                }
            });
        }
    };
    private boolean parentDataDirty = true;
    private Object parentData = getMeasurePassDelegate$ui().getParentData();
    private long performMeasureConstraints = ConstraintsKt.Constraints$default(0, 0, 0, 0, 15, null);
    private final Function0<Unit> performMeasureBlock = new Function0<Unit>() { // from class: androidx.compose.ui.node.LookaheadPassDelegate$performMeasureBlock$1
        {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Unit invoke() {
            invoke2();
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2() {
            LookaheadDelegate lookaheadDelegate = this.this$0.getOuterCoordinator().getLookaheadDelegate();
            Intrinsics.checkNotNull(lookaheadDelegate);
            lookaheadDelegate.mo6783measureBRTryo0(this.this$0.performMeasureConstraints);
        }
    };
    private final Function0<Unit> layoutModifierBlock = new Function0<Unit>() { // from class: androidx.compose.ui.node.LookaheadPassDelegate$layoutModifierBlock$1
        {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Unit invoke() {
            invoke2();
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2() {
            LookaheadDelegate lookaheadDelegate;
            boolean expectsLookaheadPlacementFromParent = (LayoutNodeLayoutDelegateKt.isOutMostLookaheadRoot(this.this$0.getLayoutNode()) || this.this$0.layoutNodeLayoutDelegate.getDetachedFromParentLookaheadPlacement()) ? false : true;
            LookaheadPassDelegate lookaheadPassDelegate = this.this$0;
            Placeable.PlacementScope scope = null;
            if (expectsLookaheadPlacementFromParent) {
                NodeCoordinator wrappedBy = lookaheadPassDelegate.getOuterCoordinator().getWrappedBy();
                if (wrappedBy != null && (lookaheadDelegate = wrappedBy.getLookaheadDelegate()) != null) {
                    scope = lookaheadDelegate.getPlacementScope();
                }
            } else {
                NodeCoordinator wrappedBy2 = lookaheadPassDelegate.getOuterCoordinator().getWrappedBy();
                if (wrappedBy2 != null) {
                    scope = wrappedBy2.getPlacementScope();
                }
            }
            if (scope == null) {
                scope = LayoutNodeKt.requireOwner(this.this$0.getLayoutNode()).getPlacementScope();
            }
            LookaheadPassDelegate lookaheadPassDelegate2 = this.this$0;
            Placeable.PlacementScope $this$invoke_u24lambda_u240 = scope;
            LookaheadDelegate lookaheadDelegate2 = lookaheadPassDelegate2.getOuterCoordinator().getLookaheadDelegate();
            Intrinsics.checkNotNull(lookaheadDelegate2);
            Placeable.PlacementScope.m6849place70tqf50$default($this$invoke_u24lambda_u240, lookaheadDelegate2, lookaheadPassDelegate2.lastPosition, 0.0f, 2, null);
        }
    };

    /* JADX INFO: compiled from: LookaheadPassDelegate.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0082\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Landroidx/compose/ui/node/LookaheadPassDelegate$PlacedState;", "", "<init>", "(Ljava/lang/String;I)V", "IsPlacedInLookahead", "IsPlacedInApproach", "IsNotPlaced", "ui"}, k = 1, mv = {2, 1, 0}, xi = 48)
    private enum PlacedState {
        IsPlacedInLookahead,
        IsPlacedInApproach,
        IsNotPlaced;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

        public static EnumEntries<PlacedState> getEntries() {
            return $ENTRIES;
        }
    }

    /* JADX INFO: compiled from: LookaheadPassDelegate.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[LayoutNode.LayoutState.values().length];
            try {
                iArr[LayoutNode.LayoutState.LookaheadMeasuring.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[LayoutNode.LayoutState.Measuring.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[LayoutNode.LayoutState.LayingOut.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[LayoutNode.LayoutState.LookaheadLayingOut.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[LayoutNode.UsageByParent.values().length];
            try {
                iArr2[LayoutNode.UsageByParent.InMeasureBlock.ordinal()] = 1;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr2[LayoutNode.UsageByParent.InLayoutBlock.ordinal()] = 2;
            } catch (NoSuchFieldError e6) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    public LookaheadPassDelegate(LayoutNodeLayoutDelegate layoutNodeLayoutDelegate) {
        this.layoutNodeLayoutDelegate = layoutNodeLayoutDelegate;
    }

    private final void setMeasurePending(boolean value) {
        this.layoutNodeLayoutDelegate.setLookaheadMeasurePending$ui(value);
    }

    private final boolean getMeasurePending() {
        return this.layoutNodeLayoutDelegate.getLookaheadMeasurePending();
    }

    private final void setLayoutPending(boolean value) {
        this.layoutNodeLayoutDelegate.setLookaheadLayoutPending$ui(value);
    }

    private final boolean getLayoutPending() {
        return this.layoutNodeLayoutDelegate.getLookaheadLayoutPending();
    }

    private final void setLayoutPendingForAlignment(boolean value) {
        this.layoutNodeLayoutDelegate.setLookaheadLayoutPendingForAlignment$ui(value);
    }

    private final boolean getLayoutPendingForAlignment() {
        return this.layoutNodeLayoutDelegate.getLookaheadLayoutPendingForAlignment();
    }

    public final LayoutNode getLayoutNode() {
        return this.layoutNodeLayoutDelegate.getLayoutNode();
    }

    public final void markLayoutPending$ui() {
        setLayoutPending(true);
        setLayoutPendingForAlignment(true);
    }

    public final void markMeasurePending$ui() {
        setMeasurePending(true);
    }

    @Override // androidx.compose.ui.node.AlignmentLinesOwner
    public int getPlaceOrder() {
        return this.placeOrder;
    }

    public void setPlaceOrder$ui(int i) {
        this.placeOrder = i;
    }

    /* JADX INFO: renamed from: getMeasuredByParent$ui, reason: from getter */
    public final LayoutNode.UsageByParent getMeasuredByParent() {
        return this.measuredByParent;
    }

    public final void setMeasuredByParent$ui(LayoutNode.UsageByParent usageByParent) {
        this.measuredByParent = usageByParent;
    }

    public final MeasurePassDelegate getMeasurePassDelegate$ui() {
        return this.layoutNodeLayoutDelegate.getMeasurePassDelegate();
    }

    public final NodeCoordinator getOuterCoordinator() {
        return this.layoutNodeLayoutDelegate.getOuterCoordinator();
    }

    private final void setLayoutState(LayoutNode.LayoutState value) {
        this.layoutNodeLayoutDelegate.setLayoutState$ui(value);
    }

    private final LayoutNode.LayoutState getLayoutState() {
        return this.layoutNodeLayoutDelegate.getLayoutState();
    }

    /* JADX INFO: renamed from: getPlacedOnce$ui, reason: from getter */
    public final boolean getPlacedOnce() {
        return this.placedOnce;
    }

    public final void setPlacedOnce$ui(boolean z) {
        this.placedOnce = z;
    }

    /* JADX INFO: renamed from: getLastConstraints-DWUhwKw, reason: from getter */
    public final Constraints getLookaheadConstraints() {
        return this.lookaheadConstraints;
    }

    public final boolean isPlaced$ui() {
        return this._placedState != PlacedState.IsNotPlaced;
    }

    @Override // androidx.compose.ui.node.AlignmentLinesOwner
    public NodeCoordinator getInnerCoordinator() {
        return getLayoutNode().getInnerCoordinator$ui();
    }

    @Override // androidx.compose.ui.node.AlignmentLinesOwner
    public AlignmentLines getAlignmentLines() {
        return this.alignmentLines;
    }

    public final void onApproachPlacement$ui() {
        if (this._placedState != PlacedState.IsNotPlaced || LayoutNodeLayoutDelegateKt.isOutMostLookaheadRoot(getLayoutNode())) {
            return;
        }
        this.layoutNodeLayoutDelegate.setDetachedFromParentLookaheadPlacement$ui(true);
    }

    public final boolean getNeedsToBePlacedInApproach() {
        return LayoutNodeLayoutDelegateKt.isOutMostLookaheadRoot(getLayoutNode()) || getDetachedFromParentLookaheadPlacement();
    }

    /* JADX INFO: renamed from: getChildDelegatesDirty$ui, reason: from getter */
    public final boolean getChildDelegatesDirty() {
        return this.childDelegatesDirty;
    }

    public final void setChildDelegatesDirty$ui(boolean z) {
        this.childDelegatesDirty = z;
    }

    public final List<LookaheadPassDelegate> getChildDelegates$ui() {
        LayoutNode $this$updateChildMeasurables$iv;
        getLayoutNode().getChildren$ui();
        if (!this.childDelegatesDirty) {
            return this._childDelegates.asMutableList();
        }
        LayoutNode $this$updateChildMeasurables$iv2 = getLayoutNode();
        MutableVector<LookaheadPassDelegate> mutableVector = this._childDelegates;
        MutableVector<LayoutNode> mutableVector2 = $this$updateChildMeasurables$iv2.get_children$ui();
        int i$iv$iv$iv = 0;
        Object[] content$iv$iv$iv = mutableVector2.content;
        int size$iv$iv$iv = mutableVector2.getSize();
        while (i$iv$iv$iv < size$iv$iv$iv) {
            LayoutNode layoutNode$iv = (LayoutNode) content$iv$iv$iv[i$iv$iv$iv];
            int i$iv = i$iv$iv$iv;
            if (mutableVector.getSize() <= i$iv) {
                $this$updateChildMeasurables$iv = $this$updateChildMeasurables$iv2;
                LookaheadPassDelegate lookaheadPassDelegate = layoutNode$iv.getLayoutDelegate().getLookaheadPassDelegate();
                Intrinsics.checkNotNull(lookaheadPassDelegate);
                mutableVector.add(lookaheadPassDelegate);
            } else {
                $this$updateChildMeasurables$iv = $this$updateChildMeasurables$iv2;
                LookaheadPassDelegate lookaheadPassDelegate2 = layoutNode$iv.getLayoutDelegate().getLookaheadPassDelegate();
                Intrinsics.checkNotNull(lookaheadPassDelegate2);
                mutableVector.set(i$iv, lookaheadPassDelegate2);
            }
            i$iv$iv$iv++;
            $this$updateChildMeasurables$iv2 = $this$updateChildMeasurables$iv;
        }
        mutableVector.removeRange($this$updateChildMeasurables$iv2.getChildren$ui().size(), mutableVector.getSize());
        this.childDelegatesDirty = false;
        return this._childDelegates.asMutableList();
    }

    public final boolean getLayingOutChildren() {
        return this.layingOutChildren;
    }

    private final void forEachChildDelegate(Function1<? super LookaheadPassDelegate, Unit> block) {
        LayoutNode this_$iv = getLayoutNode();
        MutableVector<LayoutNode> mutableVector = this_$iv.get_children$ui();
        Object[] content$iv$iv = mutableVector.content;
        int size$iv$iv = mutableVector.getSize();
        for (int i$iv$iv = 0; i$iv$iv < size$iv$iv; i$iv$iv++) {
            LayoutNode it = (LayoutNode) content$iv$iv[i$iv$iv];
            LookaheadPassDelegate lookaheadPassDelegate = it.getLayoutDelegate().getLookaheadPassDelegate();
            Intrinsics.checkNotNull(lookaheadPassDelegate);
            block.invoke(lookaheadPassDelegate);
        }
    }

    @Override // androidx.compose.ui.node.AlignmentLinesOwner
    public void layoutChildren() {
        this.layingOutChildren = true;
        getAlignmentLines().recalculateQueryOwner();
        if (getLayoutPending()) {
            onBeforeLayoutChildren();
        }
        LookaheadDelegate lookaheadDelegate = getInnerCoordinator().getLookaheadDelegate();
        Intrinsics.checkNotNull(lookaheadDelegate);
        if (getLayoutPendingForAlignment() || (!this.duringAlignmentLinesQuery && !lookaheadDelegate.getIsPlacingForAlignment() && getLayoutPending())) {
            setLayoutPending(false);
            LayoutNode.LayoutState oldLayoutState = getLayoutState();
            setLayoutState(LayoutNode.LayoutState.LookaheadLayingOut);
            this.layoutNodeLayoutDelegate.setLookaheadCoordinatesAccessedDuringPlacement(false);
            OwnerSnapshotObserver observer = LayoutNodeKt.requireOwner(getLayoutNode()).getSnapshotObserver();
            LayoutNode node$iv = getLayoutNode();
            Function0<Unit> function0 = this.layoutChildrenBlock;
            Function1 onChanged$iv$iv = observer.onCommitAffectingLookahead;
            observer.observer.observeReads(node$iv, onChanged$iv$iv, function0);
            setLayoutState(oldLayoutState);
            if (this.layoutNodeLayoutDelegate.getLookaheadCoordinatesAccessedDuringPlacement() && lookaheadDelegate.getIsPlacingForAlignment()) {
                requestLayout();
            }
            setLayoutPendingForAlignment(false);
        }
        if (getAlignmentLines().getUsedDuringParentLayout()) {
            getAlignmentLines().setPreviousUsedDuringParentLayout$ui(true);
        }
        if (getAlignmentLines().getDirty() && getAlignmentLines().getRequired$ui()) {
            getAlignmentLines().recalculate();
        }
        this.layingOutChildren = false;
    }

    private final boolean getDetachedFromParentLookaheadPlacement() {
        return this.layoutNodeLayoutDelegate.getDetachedFromParentLookaheadPlacement();
    }

    public final void checkChildrenPlaceOrderForUpdates() {
        LayoutNode this_$iv$iv = getLayoutNode();
        MutableVector<LayoutNode> mutableVector = this_$iv$iv.get_children$ui();
        Object[] content$iv$iv$iv = mutableVector.content;
        int size$iv$iv$iv = mutableVector.getSize();
        for (int i$iv$iv$iv = 0; i$iv$iv$iv < size$iv$iv$iv; i$iv$iv$iv++) {
            LayoutNode it$iv = (LayoutNode) content$iv$iv$iv[i$iv$iv$iv];
            LookaheadPassDelegate child = it$iv.getLayoutDelegate().getLookaheadPassDelegate();
            Intrinsics.checkNotNull(child);
            if (child.previousPlaceOrder != child.getPlaceOrder() && child.getPlaceOrder() == Integer.MAX_VALUE) {
                child.markNodeAndSubtreeAsNotPlaced$ui(true);
            }
        }
    }

    public final void markNodeAndSubtreeAsNotPlaced$ui(boolean inLookahead) {
        if (inLookahead && getNeedsToBePlacedInApproach()) {
            return;
        }
        if (inLookahead || getNeedsToBePlacedInApproach()) {
            this._placedState = PlacedState.IsNotPlaced;
            LayoutNode this_$iv$iv = getLayoutNode();
            MutableVector<LayoutNode> mutableVector = this_$iv$iv.get_children$ui();
            Object[] content$iv$iv$iv = mutableVector.content;
            int size$iv$iv$iv = mutableVector.getSize();
            for (int i$iv$iv$iv = 0; i$iv$iv$iv < size$iv$iv$iv; i$iv$iv$iv++) {
                LayoutNode it$iv = (LayoutNode) content$iv$iv$iv[i$iv$iv$iv];
                LookaheadPassDelegate it = it$iv.getLayoutDelegate().getLookaheadPassDelegate();
                Intrinsics.checkNotNull(it);
                it.markNodeAndSubtreeAsNotPlaced$ui(true);
            }
        }
    }

    @Override // androidx.compose.ui.node.AlignmentLinesOwner
    public Map<AlignmentLine, Integer> calculateAlignmentLines() {
        if (!this.duringAlignmentLinesQuery) {
            if (getLayoutState() == LayoutNode.LayoutState.LookaheadMeasuring) {
                getAlignmentLines().setUsedByModifierMeasurement$ui(true);
                if (getAlignmentLines().getDirty()) {
                    this.layoutNodeLayoutDelegate.markLookaheadLayoutPending$ui();
                }
            } else {
                getAlignmentLines().setUsedByModifierLayout$ui(true);
            }
        }
        LookaheadDelegate lookaheadDelegate = getInnerCoordinator().getLookaheadDelegate();
        if (lookaheadDelegate != null) {
            lookaheadDelegate.setPlacingForAlignment$ui(true);
        }
        layoutChildren();
        LookaheadDelegate lookaheadDelegate2 = getInnerCoordinator().getLookaheadDelegate();
        if (lookaheadDelegate2 != null) {
            lookaheadDelegate2.setPlacingForAlignment$ui(false);
        }
        return getAlignmentLines().getLastCalculation();
    }

    @Override // androidx.compose.ui.node.AlignmentLinesOwner
    public AlignmentLinesOwner getParentAlignmentLinesOwner() {
        LayoutNodeLayoutDelegate layoutDelegate;
        LayoutNode parent$ui = getLayoutNode().getParent$ui();
        if (parent$ui == null || (layoutDelegate = parent$ui.getLayoutDelegate()) == null) {
            return null;
        }
        return layoutDelegate.getLookaheadAlignmentLinesOwner$ui();
    }

    @Override // androidx.compose.ui.node.AlignmentLinesOwner
    public void forEachChildAlignmentLinesOwner(Function1<? super AlignmentLinesOwner, Unit> block) {
        LayoutNode this_$iv = getLayoutNode();
        MutableVector<LayoutNode> mutableVector = this_$iv.get_children$ui();
        Object[] content$iv$iv = mutableVector.content;
        int size$iv$iv = mutableVector.getSize();
        for (int i$iv$iv = 0; i$iv$iv < size$iv$iv; i$iv$iv++) {
            LayoutNode it = (LayoutNode) content$iv$iv[i$iv$iv];
            AlignmentLinesOwner lookaheadAlignmentLinesOwner$ui = it.getLayoutDelegate().getLookaheadAlignmentLinesOwner$ui();
            Intrinsics.checkNotNull(lookaheadAlignmentLinesOwner$ui);
            block.invoke(lookaheadAlignmentLinesOwner$ui);
        }
    }

    @Override // androidx.compose.ui.node.AlignmentLinesOwner
    public void requestLayout() {
        LayoutNode.requestLookaheadRelayout$ui$default(getLayoutNode(), false, 1, null);
    }

    @Override // androidx.compose.ui.node.AlignmentLinesOwner
    public void requestMeasure() {
        LayoutNode.requestLookaheadRemeasure$ui$default(getLayoutNode(), false, false, false, 7, null);
    }

    public final void notifyChildrenUsingLookaheadCoordinatesWhilePlacing() {
        if (this.layoutNodeLayoutDelegate.getChildrenAccessingLookaheadCoordinatesDuringPlacement() > 0) {
            LayoutNode this_$iv = getLayoutNode();
            MutableVector<LayoutNode> mutableVector = this_$iv.get_children$ui();
            Object[] content$iv$iv = mutableVector.content;
            int size$iv$iv = mutableVector.getSize();
            for (int i$iv$iv = 0; i$iv$iv < size$iv$iv; i$iv$iv++) {
                LayoutNode child = (LayoutNode) content$iv$iv[i$iv$iv];
                LayoutNodeLayoutDelegate childLayoutDelegate = child.getLayoutDelegate();
                boolean accessed = childLayoutDelegate.getLookaheadCoordinatesAccessedDuringPlacement() || childLayoutDelegate.getLookaheadCoordinatesAccessedDuringModifierPlacement();
                if (accessed && !childLayoutDelegate.getLookaheadLayoutPending()) {
                    LayoutNode.requestLookaheadRelayout$ui$default(child, false, 1, null);
                }
                LookaheadPassDelegate lookaheadPassDelegate = childLayoutDelegate.getLookaheadPassDelegate();
                if (lookaheadPassDelegate != null) {
                    lookaheadPassDelegate.notifyChildrenUsingLookaheadCoordinatesWhilePlacing();
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x0028  */
    @Override // androidx.compose.ui.layout.Measurable
    /* JADX INFO: renamed from: measure-BRTryo0 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public androidx.compose.ui.layout.Placeable mo6783measureBRTryo0(long r4) {
        /*
            r3 = this;
            androidx.compose.ui.node.LayoutNode r0 = r3.getLayoutNode()
            androidx.compose.ui.node.LayoutNode r0 = r0.getParent$ui()
            r1 = 0
            if (r0 == 0) goto L11
            androidx.compose.ui.node.LayoutNode$LayoutState r0 = r0.getLayoutState$ui()
            goto L12
        L11:
            r0 = r1
        L12:
            androidx.compose.ui.node.LayoutNode$LayoutState r2 = androidx.compose.ui.node.LayoutNode.LayoutState.LookaheadMeasuring
            if (r0 == r2) goto L28
            androidx.compose.ui.node.LayoutNode r0 = r3.getLayoutNode()
            androidx.compose.ui.node.LayoutNode r0 = r0.getParent$ui()
            if (r0 == 0) goto L24
            androidx.compose.ui.node.LayoutNode$LayoutState r1 = r0.getLayoutState$ui()
        L24:
            androidx.compose.ui.node.LayoutNode$LayoutState r0 = androidx.compose.ui.node.LayoutNode.LayoutState.LookaheadLayingOut
            if (r1 != r0) goto L2e
        L28:
            androidx.compose.ui.node.LayoutNodeLayoutDelegate r0 = r3.layoutNodeLayoutDelegate
            r1 = 0
            r0.setDetachedFromParentLookaheadPass$ui(r1)
        L2e:
            androidx.compose.ui.node.LayoutNode r0 = r3.getLayoutNode()
            r3.trackLookaheadMeasurementByParent(r0)
            androidx.compose.ui.node.LayoutNode r0 = r3.getLayoutNode()
            androidx.compose.ui.node.LayoutNode$UsageByParent r0 = r0.getIntrinsicsUsageByParent()
            androidx.compose.ui.node.LayoutNode$UsageByParent r1 = androidx.compose.ui.node.LayoutNode.UsageByParent.NotUsed
            if (r0 != r1) goto L48
            androidx.compose.ui.node.LayoutNode r0 = r3.getLayoutNode()
            r0.clearSubtreeIntrinsicsUsage$ui()
        L48:
            r3.m7044remeasureBRTryo0(r4)
            r0 = r3
            androidx.compose.ui.layout.Placeable r0 = (androidx.compose.ui.layout.Placeable) r0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.node.LookaheadPassDelegate.mo6783measureBRTryo0(long):androidx.compose.ui.layout.Placeable");
    }

    private final void trackLookaheadMeasurementByParent(LayoutNode node) {
        LayoutNode.UsageByParent usageByParent;
        LayoutNode parent = node.getParent$ui();
        if (parent != null) {
            boolean value$iv = this.measuredByParent == LayoutNode.UsageByParent.NotUsed || node.getCanMultiMeasure();
            if (!value$iv) {
                InlineClassHelperKt.throwIllegalStateException(LayoutNodeLayoutDelegateKt.MeasuredTwiceErrorMessage);
            }
            switch (WhenMappings.$EnumSwitchMapping$0[parent.getLayoutState$ui().ordinal()]) {
                case 1:
                case 2:
                    usageByParent = LayoutNode.UsageByParent.InMeasureBlock;
                    break;
                case 3:
                case 4:
                    usageByParent = LayoutNode.UsageByParent.InLayoutBlock;
                    break;
                default:
                    throw new IllegalStateException("Measurable could be only measured from the parent's measure or layout block. Parents state is " + parent.getLayoutState$ui());
            }
            this.measuredByParent = usageByParent;
            return;
        }
        this.measuredByParent = LayoutNode.UsageByParent.NotUsed;
    }

    @Override // androidx.compose.ui.layout.Measured, androidx.compose.ui.layout.IntrinsicMeasurable
    public Object getParentData() {
        return this.parentData;
    }

    public final Function0<Unit> getPerformMeasureBlock$ui() {
        return this.performMeasureBlock;
    }

    /* JADX INFO: renamed from: performMeasure-BRTryo0$ui */
    public final void m7043performMeasureBRTryo0$ui(long constraints) {
        setLayoutState(LayoutNode.LayoutState.LookaheadMeasuring);
        setMeasurePending(false);
        this.performMeasureConstraints = constraints;
        OwnerSnapshotObserver observer = LayoutNodeKt.requireOwner(getLayoutNode()).getSnapshotObserver();
        LayoutNode node$iv = getLayoutNode();
        Function0<Unit> function0 = this.performMeasureBlock;
        Function1 onChanged$iv$iv = observer.onCommitAffectingLookaheadMeasure;
        observer.observer.observeReads(node$iv, onChanged$iv$iv, function0);
        markLayoutPending$ui();
        if (LayoutNodeLayoutDelegateKt.isOutMostLookaheadRoot(getLayoutNode())) {
            getMeasurePassDelegate$ui().markLayoutPending();
        } else {
            getMeasurePassDelegate$ui().markMeasurePending$ui();
        }
        setLayoutState(LayoutNode.LayoutState.Idle);
    }

    /* JADX INFO: renamed from: remeasure-BRTryo0 */
    public final boolean m7044remeasureBRTryo0(long constraints) throws Throwable {
        char c;
        long j;
        long lastLookaheadSize;
        LayoutNode layoutNode$iv = getLayoutNode();
        try {
            boolean value$iv = !getLayoutNode().getIsDeactivated();
            if (!value$iv) {
                InlineClassHelperKt.throwIllegalArgumentException("measure is called on a deactivated node");
            }
            LayoutNode parent = getLayoutNode().getParent$ui();
            getLayoutNode().setCanMultiMeasure$ui(getLayoutNode().getCanMultiMeasure() || (parent != null && parent.getCanMultiMeasure()));
            if (!getLayoutNode().getLookaheadMeasurePending$ui()) {
                Constraints constraints2 = this.lookaheadConstraints;
                if (constraints2 == null ? false : Constraints.m8096equalsimpl0(constraints2.getValue(), constraints)) {
                    Owner owner = getLayoutNode().getOwner();
                    if (owner != null) {
                        owner.forceMeasureTheSubtree(getLayoutNode(), true);
                    }
                    getLayoutNode().resetSubtreeIntrinsicsUsage$ui();
                    return false;
                }
            }
            this.lookaheadConstraints = Constraints.m8090boximpl(constraints);
            m6848setMeasurementConstraintsBRTryo0(constraints);
            getAlignmentLines().setUsedByModifierMeasurement$ui(false);
            forEachChildAlignmentLinesOwner(new Function1<AlignmentLinesOwner, Unit>() { // from class: androidx.compose.ui.node.LookaheadPassDelegate$remeasure$1$2
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(AlignmentLinesOwner alignmentLinesOwner) {
                    invoke2(alignmentLinesOwner);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(AlignmentLinesOwner it) {
                    it.getAlignmentLines().setUsedDuringParentMeasurement$ui(false);
                }
            });
            if (this.measuredOnce) {
                lastLookaheadSize = getMeasuredSize();
                c = ' ';
                j = 4294967295L;
            } else {
                c = ' ';
                j = 4294967295L;
                lastLookaheadSize = IntSize.m8316constructorimpl((((long) Integer.MIN_VALUE) & 4294967295L) | (((long) Integer.MIN_VALUE) << 32));
            }
            this.measuredOnce = true;
            LookaheadDelegate lookaheadDelegate = getOuterCoordinator().getLookaheadDelegate();
            boolean value$iv2 = lookaheadDelegate != null;
            if (!value$iv2) {
                InlineClassHelperKt.throwIllegalStateException("Lookahead result from lookaheadRemeasure cannot be null");
            }
            this.layoutNodeLayoutDelegate.m7025performLookaheadMeasureBRTryo0$ui(constraints);
            int width$iv = lookaheadDelegate.getWidth();
            int height$iv = lookaheadDelegate.getHeight();
            long lastLookaheadSize2 = lastLookaheadSize;
            m6847setMeasuredSizeozmzZPI(IntSize.m8316constructorimpl((((long) width$iv) << c) | (((long) height$iv) & j)));
            return (((int) (lastLookaheadSize2 >> c)) == lookaheadDelegate.getWidth() && ((int) (lastLookaheadSize2 & j)) == lookaheadDelegate.getHeight()) ? false : true;
        } catch (Throwable e$iv) {
            layoutNode$iv.rethrowWithComposeStackTrace(e$iv);
            throw new KotlinNothingValueException();
        }
    }

    @Override // androidx.compose.ui.layout.Placeable
    /* JADX INFO: renamed from: placeAt-f8xVGno */
    public void mo6784placeAtf8xVGno(long position, float zIndex, Function1<? super GraphicsLayerScope, Unit> layerBlock) throws Throwable {
        m7041placeSelfMLgxB_4(position, zIndex, layerBlock, null);
    }

    @Override // androidx.compose.ui.layout.Placeable
    /* JADX INFO: renamed from: placeAt-f8xVGno */
    public void mo6846placeAtf8xVGno(long position, float zIndex, GraphicsLayer layer) throws Throwable {
        m7041placeSelfMLgxB_4(position, zIndex, null, layer);
    }

    @Override // androidx.compose.ui.node.MotionReferencePlacementDelegate
    /* JADX INFO: renamed from: isPlacedUnderMotionFrameOfReference, reason: from getter */
    public boolean getIsPlacedUnderMotionFrameOfReference() {
        return this.isPlacedUnderMotionFrameOfReference;
    }

    public void setPlacedUnderMotionFrameOfReference(boolean z) {
        this.isPlacedUnderMotionFrameOfReference = z;
    }

    @Override // androidx.compose.ui.node.MotionReferencePlacementDelegate
    public void updatePlacedUnderMotionFrameOfReference(boolean newMFR) {
        LookaheadDelegate lookaheadDelegate;
        LookaheadDelegate lookaheadDelegate2 = getOuterCoordinator().getLookaheadDelegate();
        Boolean old = lookaheadDelegate2 != null ? Boolean.valueOf(lookaheadDelegate2.getIsPlacedUnderMotionFrameOfReference()) : null;
        if (!Intrinsics.areEqual(Boolean.valueOf(newMFR), old) && (lookaheadDelegate = getOuterCoordinator().getLookaheadDelegate()) != null) {
            lookaheadDelegate.setPlacedUnderMotionFrameOfReference(newMFR);
        }
        setPlacedUnderMotionFrameOfReference(newMFR);
    }

    /* JADX INFO: renamed from: placeSelf-MLgxB_4 */
    private final void m7041placeSelfMLgxB_4(long position, float zIndex, Function1<? super GraphicsLayerScope, Unit> layerBlock, GraphicsLayer layer) throws Throwable {
        LayoutNode layoutNode$iv = getLayoutNode();
        try {
            LayoutNode parent$ui = getLayoutNode().getParent$ui();
            if ((parent$ui != null ? parent$ui.getLayoutState$ui() : null) == LayoutNode.LayoutState.LookaheadLayingOut) {
                this.layoutNodeLayoutDelegate.setDetachedFromParentLookaheadPlacement$ui(false);
            }
            boolean value$iv = !getLayoutNode().getIsDeactivated();
            if (!value$iv) {
                InlineClassHelperKt.throwIllegalArgumentException("place is called on a deactivated node");
            }
            setLayoutState(LayoutNode.LayoutState.LookaheadLayingOut);
            this.placedOnce = true;
            this.onNodePlacedCalled = false;
            if (!IntOffset.m8277equalsimpl0(position, this.lastPosition)) {
                if (this.layoutNodeLayoutDelegate.getLookaheadCoordinatesAccessedDuringModifierPlacement() || this.layoutNodeLayoutDelegate.getLookaheadCoordinatesAccessedDuringPlacement()) {
                    setLayoutPending(true);
                }
                notifyChildrenUsingLookaheadCoordinatesWhilePlacing();
            }
            Owner owner = LayoutNodeKt.requireOwner(getLayoutNode());
            this.lastPosition = position;
            if (!getLayoutPending() && isPlaced$ui()) {
                LookaheadDelegate lookaheadDelegate = getOuterCoordinator().getLookaheadDelegate();
                Intrinsics.checkNotNull(lookaheadDelegate);
                lookaheadDelegate.m7038placeSelfApparentToRealOffsetgyyYBs$ui(position);
                onNodePlaced$ui();
            } else {
                this.layoutNodeLayoutDelegate.setLookaheadCoordinatesAccessedDuringModifierPlacement(false);
                getAlignmentLines().setUsedByModifierLayout$ui(false);
                OwnerSnapshotObserver this_$iv = owner.getSnapshotObserver();
                LayoutNode node$iv = getLayoutNode();
                Function0<Unit> function0 = this.layoutModifierBlock;
                Function1 onChanged$iv$iv = this_$iv.onCommitAffectingLayoutModifierInLookahead;
                this_$iv.observer.observeReads(node$iv, onChanged$iv$iv, function0);
            }
        } catch (Throwable th) {
            e$iv = th;
        }
        try {
            this.lastZIndex = zIndex;
        } catch (Throwable th2) {
            e$iv = th2;
            layoutNode$iv.rethrowWithComposeStackTrace(e$iv);
            throw new KotlinNothingValueException();
        }
        try {
            this.lastLayerBlock = layerBlock;
        } catch (Throwable th3) {
            e$iv = th3;
            layoutNode$iv.rethrowWithComposeStackTrace(e$iv);
            throw new KotlinNothingValueException();
        }
        try {
            this.lastExplicitLayer = layer;
            setLayoutState(LayoutNode.LayoutState.Idle);
            Unit unit = Unit.INSTANCE;
        } catch (Throwable th4) {
            e$iv = th4;
            layoutNode$iv.rethrowWithComposeStackTrace(e$iv);
            throw new KotlinNothingValueException();
        }
    }

    @Override // androidx.compose.ui.layout.Placeable, androidx.compose.ui.layout.Measured
    public int getMeasuredWidth() {
        LookaheadDelegate lookaheadDelegate = getOuterCoordinator().getLookaheadDelegate();
        Intrinsics.checkNotNull(lookaheadDelegate);
        return lookaheadDelegate.getMeasuredWidth();
    }

    @Override // androidx.compose.ui.layout.Placeable, androidx.compose.ui.layout.Measured
    public int getMeasuredHeight() {
        LookaheadDelegate lookaheadDelegate = getOuterCoordinator().getLookaheadDelegate();
        Intrinsics.checkNotNull(lookaheadDelegate);
        return lookaheadDelegate.getMeasuredHeight();
    }

    @Override // androidx.compose.ui.layout.Measured
    public int get(AlignmentLine alignmentLine) {
        LayoutNode parent$ui = getLayoutNode().getParent$ui();
        if ((parent$ui != null ? parent$ui.getLayoutState$ui() : null) == LayoutNode.LayoutState.LookaheadMeasuring) {
            getAlignmentLines().setUsedDuringParentMeasurement$ui(true);
        } else {
            LayoutNode parent$ui2 = getLayoutNode().getParent$ui();
            if ((parent$ui2 != null ? parent$ui2.getLayoutState$ui() : null) == LayoutNode.LayoutState.LookaheadLayingOut) {
                getAlignmentLines().setUsedDuringParentLayout$ui(true);
            }
        }
        this.duringAlignmentLinesQuery = true;
        LookaheadDelegate lookaheadDelegate = getOuterCoordinator().getLookaheadDelegate();
        Intrinsics.checkNotNull(lookaheadDelegate);
        int result = lookaheadDelegate.get(alignmentLine);
        this.duringAlignmentLinesQuery = false;
        return result;
    }

    @Override // androidx.compose.ui.layout.IntrinsicMeasurable
    public int minIntrinsicWidth(int height) {
        onIntrinsicsQueried();
        LookaheadDelegate lookaheadDelegate = getOuterCoordinator().getLookaheadDelegate();
        Intrinsics.checkNotNull(lookaheadDelegate);
        return lookaheadDelegate.minIntrinsicWidth(height);
    }

    @Override // androidx.compose.ui.layout.IntrinsicMeasurable
    public int maxIntrinsicWidth(int height) {
        onIntrinsicsQueried();
        LookaheadDelegate lookaheadDelegate = getOuterCoordinator().getLookaheadDelegate();
        Intrinsics.checkNotNull(lookaheadDelegate);
        return lookaheadDelegate.maxIntrinsicWidth(height);
    }

    @Override // androidx.compose.ui.layout.IntrinsicMeasurable
    public int minIntrinsicHeight(int width) {
        onIntrinsicsQueried();
        LookaheadDelegate lookaheadDelegate = getOuterCoordinator().getLookaheadDelegate();
        Intrinsics.checkNotNull(lookaheadDelegate);
        return lookaheadDelegate.minIntrinsicHeight(width);
    }

    @Override // androidx.compose.ui.layout.IntrinsicMeasurable
    public int maxIntrinsicHeight(int width) {
        onIntrinsicsQueried();
        LookaheadDelegate lookaheadDelegate = getOuterCoordinator().getLookaheadDelegate();
        Intrinsics.checkNotNull(lookaheadDelegate);
        return lookaheadDelegate.maxIntrinsicHeight(width);
    }

    private final void onIntrinsicsQueried() {
        LayoutNode.UsageByParent intrinsicsUsageByParent;
        LayoutNode.requestLookaheadRemeasure$ui$default(getLayoutNode(), false, false, false, 7, null);
        LayoutNode parent = getLayoutNode().getParent$ui();
        if (parent != null && getLayoutNode().getIntrinsicsUsageByParent() == LayoutNode.UsageByParent.NotUsed) {
            LayoutNode layoutNode = getLayoutNode();
            switch (WhenMappings.$EnumSwitchMapping$0[parent.getLayoutState$ui().ordinal()]) {
                case 2:
                    intrinsicsUsageByParent = LayoutNode.UsageByParent.InMeasureBlock;
                    break;
                case 3:
                    intrinsicsUsageByParent = LayoutNode.UsageByParent.InLayoutBlock;
                    break;
                default:
                    intrinsicsUsageByParent = parent.getIntrinsicsUsageByParent();
                    break;
            }
            layoutNode.setIntrinsicsUsageByParent$ui(intrinsicsUsageByParent);
        }
    }

    public final void invalidateIntrinsicsParent(boolean forceRequest) {
        LayoutNode intrinsicsUsingParent;
        LayoutNode parent = getLayoutNode().getParent$ui();
        LayoutNode.UsageByParent intrinsicsUsageByParent = getLayoutNode().getIntrinsicsUsageByParent();
        if (parent != null && intrinsicsUsageByParent != LayoutNode.UsageByParent.NotUsed) {
            LayoutNode parent$ui = parent;
            do {
                intrinsicsUsingParent = parent$ui;
                if (intrinsicsUsingParent.getIntrinsicsUsageByParent() != intrinsicsUsageByParent) {
                    break;
                } else {
                    parent$ui = intrinsicsUsingParent.getParent$ui();
                }
            } while (parent$ui != null);
            switch (WhenMappings.$EnumSwitchMapping$1[intrinsicsUsageByParent.ordinal()]) {
                case 1:
                    if (intrinsicsUsingParent.getLookaheadRoot() != null) {
                        LayoutNode.requestLookaheadRemeasure$ui$default(intrinsicsUsingParent, forceRequest, false, false, 6, null);
                        return;
                    } else {
                        LayoutNode.requestRemeasure$ui$default(intrinsicsUsingParent, forceRequest, false, false, 6, null);
                        return;
                    }
                case 2:
                    if (intrinsicsUsingParent.getLookaheadRoot() != null) {
                        intrinsicsUsingParent.requestLookaheadRelayout$ui(forceRequest);
                        return;
                    } else {
                        intrinsicsUsingParent.requestRelayout$ui(forceRequest);
                        return;
                    }
                default:
                    throw new IllegalStateException("Intrinsics isn't used by the parent".toString());
            }
        }
    }

    public final void invalidateParentData() {
        this.parentDataDirty = true;
    }

    public final boolean updateParentData() {
        if (getParentData() == null) {
            LookaheadDelegate lookaheadDelegate = getOuterCoordinator().getLookaheadDelegate();
            Intrinsics.checkNotNull(lookaheadDelegate);
            if (lookaheadDelegate.getParentData() == null) {
                return false;
            }
        }
        if (!this.parentDataDirty) {
            return false;
        }
        this.parentDataDirty = false;
        LookaheadDelegate lookaheadDelegate2 = getOuterCoordinator().getLookaheadDelegate();
        Intrinsics.checkNotNull(lookaheadDelegate2);
        this.parentData = lookaheadDelegate2.getParentData();
        return true;
    }

    public final void onNodePlaced$ui() {
        this.onNodePlacedCalled = true;
        LayoutNode parent = getLayoutNode().getParent$ui();
        if ((this._placedState != PlacedState.IsPlacedInLookahead && !getDetachedFromParentLookaheadPlacement()) || (this._placedState != PlacedState.IsPlacedInApproach && getDetachedFromParentLookaheadPlacement())) {
            markNodeAndSubtreeAsPlaced();
            if (this.relayoutWithoutParentInProgress && parent != null) {
                LayoutNode.requestLookaheadRelayout$ui$default(parent, false, 1, null);
            }
        }
        if (parent != null) {
            if (!this.relayoutWithoutParentInProgress && (parent.getLayoutState$ui() == LayoutNode.LayoutState.LayingOut || parent.getLayoutState$ui() == LayoutNode.LayoutState.LookaheadLayingOut)) {
                boolean value$iv = getPlaceOrder() == Integer.MAX_VALUE;
                if (!value$iv) {
                    InlineClassHelperKt.throwIllegalStateException("Place was called on a node which was placed already");
                }
                setPlaceOrder$ui(parent.getLayoutDelegate().getNextChildLookaheadPlaceOrder());
                LayoutNodeLayoutDelegate layoutDelegate = parent.getLayoutDelegate();
                layoutDelegate.setNextChildLookaheadPlaceOrder$ui(layoutDelegate.getNextChildLookaheadPlaceOrder() + 1);
            }
        } else {
            setPlaceOrder$ui(0);
        }
        layoutChildren();
    }

    public final void clearPlaceOrder() {
        this.layoutNodeLayoutDelegate.setNextChildLookaheadPlaceOrder$ui(0);
        LayoutNode this_$iv$iv = getLayoutNode();
        MutableVector<LayoutNode> mutableVector = this_$iv$iv.get_children$ui();
        Object[] content$iv$iv$iv = mutableVector.content;
        int size$iv$iv$iv = mutableVector.getSize();
        for (int i$iv$iv$iv = 0; i$iv$iv$iv < size$iv$iv$iv; i$iv$iv$iv++) {
            LayoutNode it$iv = (LayoutNode) content$iv$iv$iv[i$iv$iv$iv];
            LookaheadPassDelegate child = it$iv.getLayoutDelegate().getLookaheadPassDelegate();
            Intrinsics.checkNotNull(child);
            child.previousPlaceOrder = child.getPlaceOrder();
            child.setPlaceOrder$ui(Integer.MAX_VALUE);
            if (child.measuredByParent == LayoutNode.UsageByParent.InLayoutBlock) {
                child.measuredByParent = LayoutNode.UsageByParent.NotUsed;
            }
        }
    }

    private final void markNodeAndSubtreeAsPlaced() {
        PlacedState prevPlacedState = this._placedState;
        if (getDetachedFromParentLookaheadPlacement()) {
            this._placedState = PlacedState.IsPlacedInApproach;
        } else {
            this._placedState = PlacedState.IsPlacedInLookahead;
        }
        if (prevPlacedState != PlacedState.IsPlacedInLookahead && this.layoutNodeLayoutDelegate.getLookaheadMeasurePending()) {
            LayoutNode.requestLookaheadRemeasure$ui$default(getLayoutNode(), true, false, false, 6, null);
        }
        LayoutNode this_$iv = getLayoutNode();
        MutableVector<LayoutNode> mutableVector = this_$iv.get_children$ui();
        Object[] content$iv$iv = mutableVector.content;
        int size$iv$iv = mutableVector.getSize();
        for (int i$iv$iv = 0; i$iv$iv < size$iv$iv; i$iv$iv++) {
            LayoutNode it = (LayoutNode) content$iv$iv[i$iv$iv];
            LookaheadPassDelegate childDelegate = it.getLookaheadPassDelegate$ui();
            if (childDelegate == null) {
                throw new IllegalArgumentException("Error: Child node's lookahead pass delegate cannot be null when in a lookahead scope.".toString());
            }
            if (childDelegate.getPlaceOrder() != Integer.MAX_VALUE) {
                childDelegate.markNodeAndSubtreeAsPlaced();
                it.rescheduleRemeasureOrRelayout$ui(it);
            }
        }
    }

    private final void onBeforeLayoutChildren() {
        LayoutNode this_$iv = getLayoutNode();
        MutableVector<LayoutNode> mutableVector = this_$iv.get_children$ui();
        Object[] content$iv$iv = mutableVector.content;
        int size$iv$iv = mutableVector.getSize();
        for (int i$iv$iv = 0; i$iv$iv < size$iv$iv; i$iv$iv++) {
            LayoutNode it = (LayoutNode) content$iv$iv[i$iv$iv];
            if (it.getLookaheadMeasurePending$ui() && it.getMeasuredByParentInLookahead$ui() == LayoutNode.UsageByParent.InMeasureBlock) {
                LookaheadPassDelegate lookaheadPassDelegate = it.getLayoutDelegate().getLookaheadPassDelegate();
                Intrinsics.checkNotNull(lookaheadPassDelegate);
                Constraints constraintsM7024getLastLookaheadConstraintsDWUhwKw = it.getLayoutDelegate().m7024getLastLookaheadConstraintsDWUhwKw();
                Intrinsics.checkNotNull(constraintsM7024getLastLookaheadConstraintsDWUhwKw);
                if (lookaheadPassDelegate.m7044remeasureBRTryo0(constraintsM7024getLastLookaheadConstraintsDWUhwKw.getValue())) {
                    LayoutNode.requestLookaheadRemeasure$ui$default(getLayoutNode(), false, false, false, 7, null);
                }
            }
        }
    }

    public final void replace() {
        LayoutNode parent$ui;
        try {
            this.relayoutWithoutParentInProgress = true;
            boolean value$iv = this.placedOnce;
            if (!value$iv) {
                InlineClassHelperKt.throwIllegalStateException("replace() called on item that was not placed");
            }
            this.onNodePlacedCalled = false;
            boolean wasPlacedBefore = isPlaced$ui();
            m7041placeSelfMLgxB_4(this.lastPosition, 0.0f, this.lastLayerBlock, this.lastExplicitLayer);
            if (wasPlacedBefore && !this.onNodePlacedCalled && (parent$ui = getLayoutNode().getParent$ui()) != null) {
                LayoutNode.requestLookaheadRelayout$ui$default(parent$ui, false, 1, null);
            }
        } finally {
            this.relayoutWithoutParentInProgress = false;
        }
    }

    public final void onNodeDetached() {
        setPlaceOrder$ui(Integer.MAX_VALUE);
        this.previousPlaceOrder = Integer.MAX_VALUE;
        this._placedState = PlacedState.IsNotPlaced;
    }

    public final void onAttachedToNullParent() {
        this._placedState = PlacedState.IsPlacedInLookahead;
    }
}
