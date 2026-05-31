package androidx.compose.ui.node;

import android.os.Trace;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.runtime.tooling.ComposeToolingFlags;
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
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MeasurePassDelegate.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000º\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b'\n\u0002\u0018\u0002\n\u0002\b\u001c\n\u0002\u0010$\n\u0002\b\u000f\b\u0001\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004B\u000f\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\r\u0010b\u001a\u000200H\u0000¢\u0006\u0002\bcJ\b\u0010l\u001a\u000200H\u0016J\b\u0010m\u001a\u000200H\u0002J\b\u0010n\u001a\u000200H\u0002J\b\u0010o\u001a\u000200H\u0002J\r\u0010y\u001a\u000200H\u0000¢\u0006\u0002\bzJ\b\u0010{\u001a\u000200H\u0002J\u001d\u0010|\u001a\u0002002\u0012\u0010}\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u0002000.H\u0082\bJ\u001a\u0010~\u001a\u0002002\u0006\u0010\u007f\u001a\u00020\u0017H\u0080\b¢\u0006\u0006\b\u0080\u0001\u0010\u0081\u0001J\u001a\u0010\u0082\u0001\u001a\u00020\u00022\u0006\u0010\u007f\u001a\u00020\u0017H\u0016¢\u0006\u0006\b\u0083\u0001\u0010\u0084\u0001J\u0018\u0010\u0085\u0001\u001a\u00020\n2\u0006\u0010\u007f\u001a\u00020\u0017¢\u0006\u0006\b\u0086\u0001\u0010\u0087\u0001J\u0012\u0010\u0088\u0001\u001a\u0002002\u0007\u0010\u0089\u0001\u001a\u00020\u001bH\u0002J\u0014\u0010\u008e\u0001\u001a\u00020\f2\b\u0010\u008f\u0001\u001a\u00030\u0090\u0001H\u0096\u0002J?\u0010\u0091\u0001\u001a\u0002002\u0007\u0010\u0092\u0001\u001a\u00020(2\u0006\u0010p\u001a\u0002052\u001a\u0010\u0093\u0001\u001a\u0015\u0012\u0004\u0012\u00020/\u0012\u0004\u0012\u000200\u0018\u00010.¢\u0006\u0002\b1H\u0014¢\u0006\u0006\b\u0094\u0001\u0010\u0095\u0001J,\u0010\u0091\u0001\u001a\u0002002\u0007\u0010\u0092\u0001\u001a\u00020(2\u0006\u0010p\u001a\u0002052\u0007\u0010\u0096\u0001\u001a\u000203H\u0014¢\u0006\u0006\b\u0094\u0001\u0010\u0097\u0001J\u0012\u0010\u009b\u0001\u001a\u0002002\u0007\u0010\u009c\u0001\u001a\u00020\nH\u0016JJ\u0010\u009d\u0001\u001a\u0002002\u0007\u0010\u0092\u0001\u001a\u00020(2\u0006\u0010p\u001a\u0002052\u001a\u0010\u0093\u0001\u001a\u0015\u0012\u0004\u0012\u00020/\u0012\u0004\u0012\u000200\u0018\u00010.¢\u0006\u0002\b12\t\u0010\u0096\u0001\u001a\u0004\u0018\u000103H\u0002¢\u0006\u0006\b\u009e\u0001\u0010\u009f\u0001JJ\u0010 \u0001\u001a\u0002002\u0007\u0010\u0092\u0001\u001a\u00020(2\u0006\u0010p\u001a\u0002052\u001a\u0010\u0093\u0001\u001a\u0015\u0012\u0004\u0012\u00020/\u0012\u0004\u0012\u000200\u0018\u00010.¢\u0006\u0002\b12\t\u0010\u0096\u0001\u001a\u0004\u0018\u000103H\u0002¢\u0006\u0006\b¡\u0001\u0010\u009f\u0001J\u0007\u0010¢\u0001\u001a\u000200J\u0012\u0010£\u0001\u001a\u00020\f2\u0007\u0010¤\u0001\u001a\u00020\fH\u0016J\u0012\u0010¥\u0001\u001a\u00020\f2\u0007\u0010¤\u0001\u001a\u00020\fH\u0016J\u0012\u0010¦\u0001\u001a\u00020\f2\u0007\u0010§\u0001\u001a\u00020\fH\u0016J\u0012\u0010¨\u0001\u001a\u00020\f2\u0007\u0010§\u0001\u001a\u00020\fH\u0016J\t\u0010©\u0001\u001a\u000200H\u0002J\u0007\u0010ª\u0001\u001a\u000200J\u0007\u0010«\u0001\u001a\u00020\nJ\u0017\u0010¬\u0001\u001a\u0010\u0012\u0005\u0012\u00030\u0090\u0001\u0012\u0004\u0012\u00020\f0\u00ad\u0001H\u0016J\u001d\u0010±\u0001\u001a\u0002002\u0012\u0010}\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u0002000.H\u0016J\t\u0010²\u0001\u001a\u000200H\u0016J\t\u0010³\u0001\u001a\u000200H\u0016J\u0007\u0010´\u0001\u001a\u000200J\t\u0010µ\u0001\u001a\u000200H\u0002J\u0010\u0010¶\u0001\u001a\u0002002\u0007\u0010·\u0001\u001a\u00020\nJ\u0007\u0010¸\u0001\u001a\u000200J\u0007\u0010¹\u0001\u001a\u000200J\u000f\u0010º\u0001\u001a\u000200H\u0000¢\u0006\u0003\b»\u0001R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\f@BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001e\u0010\u0010\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\f@RX\u0096\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u000e\u0010\u0012\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0013\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0013\u0010\u0016\u001a\u0004\u0018\u00010\u00178F¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u001a\u001a\u00020\u001b8F¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001dR\u001a\u0010\u001e\u001a\u00020\u001fX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u001a\u0010$\u001a\u00020\nX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u0015\"\u0004\b&\u0010'R \u0010)\u001a\u00020(2\u0006\u0010\u000b\u001a\u00020(@BX\u0080\u000e¢\u0006\n\n\u0002\u0010,\u001a\u0004\b*\u0010+R!\u0010-\u001a\u0015\u0012\u0004\u0012\u00020/\u0012\u0004\u0012\u000200\u0018\u00010.¢\u0006\u0002\b1X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00102\u001a\u0004\u0018\u000103X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00104\u001a\u000205X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00106\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\"\u00108\u001a\u0004\u0018\u0001072\b\u0010\u000b\u001a\u0004\u0018\u000107@RX\u0096\u000e¢\u0006\b\n\u0000\u001a\u0004\b9\u0010:R\u0016\u0010;\u001a\u0004\u0018\u00010<8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b=\u0010>R\u001a\u0010?\u001a\u00020\nX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b@\u0010\u0015\"\u0004\bA\u0010'R$\u0010B\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n@@X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bB\u0010\u0015\"\u0004\bC\u0010'R\u001e\u0010D\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n@BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\bE\u0010\u0015R\u001e\u0010F\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n@BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\bG\u0010\u0015R\u000e\u0010H\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010J\u001a\u00020I2\u0006\u0010\u000b\u001a\u00020I8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bK\u0010L\"\u0004\bM\u0010NR\u0011\u0010O\u001a\u00020P8F¢\u0006\u0006\u001a\u0004\bQ\u0010RR\u0014\u0010S\u001a\u00020P8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bT\u0010RR\u0014\u0010U\u001a\u00020VX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bW\u0010XR\u0014\u0010Y\u001a\b\u0012\u0004\u0012\u00020\u00000ZX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010[\u001a\u00020\nX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\\\u0010\u0015\"\u0004\b]\u0010'R\u001a\u0010^\u001a\b\u0012\u0004\u0012\u00020\u00000_8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b`\u0010aR\u001e\u0010d\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\be\u0010\u0015R\u0010\u0010f\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0004\n\u0002\u0010,R\u001a\u0010g\u001a\b\u0012\u0004\u0012\u0002000hX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\bi\u0010jR\u0014\u0010k\u001a\b\u0012\u0004\u0012\u0002000hX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010p\u001a\u0002052\u0006\u0010\u000b\u001a\u000205@BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\bq\u0010rR\u000e\u0010s\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R!\u0010t\u001a\u0015\u0012\u0004\u0012\u00020/\u0012\u0004\u0012\u000200\u0018\u00010.¢\u0006\u0002\b1X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010u\u001a\u0004\u0018\u000103X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010v\u001a\u00020(X\u0082\u000e¢\u0006\u0004\n\u0002\u0010,R\u000e\u0010w\u001a\u000205X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010x\u001a\b\u0012\u0004\u0012\u0002000hX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u008a\u0001\u001a\u00020\f8VX\u0096\u0004¢\u0006\u0007\u001a\u0005\b\u008b\u0001\u0010\u000fR\u0016\u0010\u008c\u0001\u001a\u00020\f8VX\u0096\u0004¢\u0006\u0007\u001a\u0005\b\u008d\u0001\u0010\u000fR\u000f\u0010\u0098\u0001\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u001d\u0010\u0099\u0001\u001a\u00020\nX\u0096\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0099\u0001\u0010\u0015\"\u0005\b\u009a\u0001\u0010'R\u0019\u0010®\u0001\u001a\u0004\u0018\u00010\u00038VX\u0096\u0004¢\u0006\b\u001a\u0006\b¯\u0001\u0010°\u0001¨\u0006¼\u0001"}, d2 = {"Landroidx/compose/ui/node/MeasurePassDelegate;", "Landroidx/compose/ui/layout/Measurable;", "Landroidx/compose/ui/layout/Placeable;", "Landroidx/compose/ui/node/AlignmentLinesOwner;", "Landroidx/compose/ui/node/MotionReferencePlacementDelegate;", "layoutNodeLayoutDelegate", "Landroidx/compose/ui/node/LayoutNodeLayoutDelegate;", "<init>", "(Landroidx/compose/ui/node/LayoutNodeLayoutDelegate;)V", "relayoutWithoutParentInProgress", "", "value", "", "previousPlaceOrder", "getPreviousPlaceOrder$ui", "()I", "placeOrder", "getPlaceOrder", "measuredOnce", "placedOnce", "getPlacedOnce", "()Z", "lastConstraints", "Landroidx/compose/ui/unit/Constraints;", "getLastConstraints-DWUhwKw", "()Landroidx/compose/ui/unit/Constraints;", "layoutNode", "Landroidx/compose/ui/node/LayoutNode;", "getLayoutNode", "()Landroidx/compose/ui/node/LayoutNode;", "measuredByParent", "Landroidx/compose/ui/node/LayoutNode$UsageByParent;", "getMeasuredByParent$ui", "()Landroidx/compose/ui/node/LayoutNode$UsageByParent;", "setMeasuredByParent$ui", "(Landroidx/compose/ui/node/LayoutNode$UsageByParent;)V", "duringAlignmentLinesQuery", "getDuringAlignmentLinesQuery$ui", "setDuringAlignmentLinesQuery$ui", "(Z)V", "Landroidx/compose/ui/unit/IntOffset;", "lastPosition", "getLastPosition-nOcc-ac$ui", "()J", "J", "lastLayerBlock", "Lkotlin/Function1;", "Landroidx/compose/ui/graphics/GraphicsLayerScope;", "", "Lkotlin/ExtensionFunctionType;", "lastExplicitLayer", "Landroidx/compose/ui/graphics/layer/GraphicsLayer;", "lastZIndex", "", "parentDataDirty", "", "parentData", "getParentData", "()Ljava/lang/Object;", "lookaheadPassDelegate", "Landroidx/compose/ui/node/LookaheadPassDelegate;", "getLookaheadPassDelegate", "()Landroidx/compose/ui/node/LookaheadPassDelegate;", "isPlaced", "isPlaced$ui", "setPlaced$ui", "isPlacedByParent", "setPlacedByParent$ui", "measurePending", "getMeasurePending$ui", "layoutPending", "getLayoutPending$ui", "layoutPendingForAlignment", "Landroidx/compose/ui/node/LayoutNode$LayoutState;", "layoutState", "getLayoutState", "()Landroidx/compose/ui/node/LayoutNode$LayoutState;", "setLayoutState", "(Landroidx/compose/ui/node/LayoutNode$LayoutState;)V", "outerCoordinator", "Landroidx/compose/ui/node/NodeCoordinator;", "getOuterCoordinator", "()Landroidx/compose/ui/node/NodeCoordinator;", "innerCoordinator", "getInnerCoordinator", "alignmentLines", "Landroidx/compose/ui/node/AlignmentLines;", "getAlignmentLines", "()Landroidx/compose/ui/node/AlignmentLines;", "_childDelegates", "Landroidx/compose/runtime/collection/MutableVector;", "childDelegatesDirty", "getChildDelegatesDirty$ui", "setChildDelegatesDirty$ui", "childDelegates", "", "getChildDelegates$ui", "()Ljava/util/List;", "markDetachedFromParentLookaheadPass", "markDetachedFromParentLookaheadPass$ui", "layingOutChildren", "getLayingOutChildren", "performMeasureConstraints", "performMeasureBlock", "Lkotlin/Function0;", "getPerformMeasureBlock$ui", "()Lkotlin/jvm/functions/Function0;", "layoutChildrenBlock", "layoutChildren", "checkChildrenPlaceOrderForUpdates", "markSubtreeAsNotPlaced", "markNodeAndSubtreeAsPlaced", "zIndex", "getZIndex$ui", "()F", "onNodePlacedCalled", "placeOuterCoordinatorLayerBlock", "placeOuterCoordinatorLayer", "placeOuterCoordinatorPosition", "placeOuterCoordinatorZIndex", "placeOuterCoordinatorBlock", "onNodePlaced", "onNodePlaced$ui", "clearPlaceOrder", "forEachChildDelegate", "block", "performMeasure", "constraints", "performMeasure-BRTryo0$ui", "(J)V", "measure", "measure-BRTryo0", "(J)Landroidx/compose/ui/layout/Placeable;", "remeasure", "remeasure-BRTryo0", "(J)Z", "trackMeasurementByParent", "node", "measuredWidth", "getMeasuredWidth", "measuredHeight", "getMeasuredHeight", "get", "alignmentLine", "Landroidx/compose/ui/layout/AlignmentLine;", "placeAt", "position", "layerBlock", "placeAt-f8xVGno", "(JFLkotlin/jvm/functions/Function1;)V", "layer", "(JFLandroidx/compose/ui/graphics/layer/GraphicsLayer;)V", "needsCoordinatesUpdate", "isPlacedUnderMotionFrameOfReference", "setPlacedUnderMotionFrameOfReference", "updatePlacedUnderMotionFrameOfReference", "newMFR", "placeSelf", "placeSelf-MLgxB_4", "(JFLkotlin/jvm/functions/Function1;Landroidx/compose/ui/graphics/layer/GraphicsLayer;)V", "placeOuterCoordinator", "placeOuterCoordinator-MLgxB_4", "replace", "minIntrinsicWidth", "height", "maxIntrinsicWidth", "minIntrinsicHeight", "width", "maxIntrinsicHeight", "onIntrinsicsQueried", "invalidateParentData", "updateParentData", "calculateAlignmentLines", "", "parentAlignmentLinesOwner", "getParentAlignmentLinesOwner", "()Landroidx/compose/ui/node/AlignmentLinesOwner;", "forEachChildAlignmentLinesOwner", "requestLayout", "requestMeasure", "requestLayoutIfCoordinatesAreUsedAndNotifyChildren", "onBeforeLayoutChildren", "invalidateIntrinsicsParent", "forceRequest", "onNodeDetached", "markLayoutPending", "markMeasurePending", "markMeasurePending$ui", "ui"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class MeasurePassDelegate extends Placeable implements Measurable, AlignmentLinesOwner, MotionReferencePlacementDelegate {
    public static final int $stable = 8;
    private boolean duringAlignmentLinesQuery;
    private boolean isPlaced;
    private boolean isPlacedByParent;
    private boolean isPlacedUnderMotionFrameOfReference;
    private GraphicsLayer lastExplicitLayer;
    private Function1<? super GraphicsLayerScope, Unit> lastLayerBlock;
    private float lastZIndex;
    private boolean layingOutChildren;
    private final LayoutNodeLayoutDelegate layoutNodeLayoutDelegate;
    private boolean layoutPending;
    private boolean layoutPendingForAlignment;
    private boolean measurePending;
    private boolean measuredOnce;
    private boolean needsCoordinatesUpdate;
    private boolean onNodePlacedCalled;
    private Object parentData;
    private GraphicsLayer placeOuterCoordinatorLayer;
    private Function1<? super GraphicsLayerScope, Unit> placeOuterCoordinatorLayerBlock;
    private float placeOuterCoordinatorZIndex;
    private boolean placedOnce;
    private boolean relayoutWithoutParentInProgress;
    private float zIndex;
    private int previousPlaceOrder = Integer.MAX_VALUE;
    private int placeOrder = Integer.MAX_VALUE;
    private LayoutNode.UsageByParent measuredByParent = LayoutNode.UsageByParent.NotUsed;
    private long lastPosition = IntOffset.INSTANCE.m8289getZeronOccac();
    private boolean parentDataDirty = true;
    private final AlignmentLines alignmentLines = new LayoutNodeAlignmentLines(this);
    private final MutableVector<MeasurePassDelegate> _childDelegates = new MutableVector<>(new MeasurePassDelegate[16], 0);
    private boolean childDelegatesDirty = true;
    private long performMeasureConstraints = ConstraintsKt.Constraints$default(0, 0, 0, 0, 15, null);
    private final Function0<Unit> performMeasureBlock = new Function0<Unit>() { // from class: androidx.compose.ui.node.MeasurePassDelegate$performMeasureBlock$1
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
            this.this$0.getOuterCoordinator().mo6783measureBRTryo0(this.this$0.performMeasureConstraints);
        }
    };
    private final Function0<Unit> layoutChildrenBlock = new Function0<Unit>() { // from class: androidx.compose.ui.node.MeasurePassDelegate$layoutChildrenBlock$1
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
            this.this$0.forEachChildAlignmentLinesOwner(new Function1<AlignmentLinesOwner, Unit>() { // from class: androidx.compose.ui.node.MeasurePassDelegate$layoutChildrenBlock$1.1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(AlignmentLinesOwner alignmentLinesOwner) {
                    invoke2(alignmentLinesOwner);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(AlignmentLinesOwner it) {
                    it.getAlignmentLines().setUsedDuringParentLayout$ui(false);
                }
            });
            if (this.this$0.getInnerCoordinator().getIsPlacingForAlignment()) {
                List<LayoutNode> children$ui = this.this$0.getLayoutNode().getChildren$ui();
                int size = children$ui.size();
                for (int index$iv = 0; index$iv < size; index$iv++) {
                    Object item$iv = children$ui.get(index$iv);
                    LayoutNode it = (LayoutNode) item$iv;
                    it.getOuterCoordinator$ui().setPlacingForAlignment$ui(true);
                }
            }
            this.this$0.getInnerCoordinator().getMeasureResult$ui().placeChildren();
            if (this.this$0.getInnerCoordinator().getIsPlacingForAlignment()) {
                List<LayoutNode> children$ui2 = this.this$0.getLayoutNode().getChildren$ui();
                int size2 = children$ui2.size();
                for (int index$iv2 = 0; index$iv2 < size2; index$iv2++) {
                    Object item$iv2 = children$ui2.get(index$iv2);
                    LayoutNode it2 = (LayoutNode) item$iv2;
                    it2.getOuterCoordinator$ui().setPlacingForAlignment$ui(false);
                }
            }
            this.this$0.checkChildrenPlaceOrderForUpdates();
            this.this$0.forEachChildAlignmentLinesOwner(new Function1<AlignmentLinesOwner, Unit>() { // from class: androidx.compose.ui.node.MeasurePassDelegate$layoutChildrenBlock$1.4
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(AlignmentLinesOwner alignmentLinesOwner) {
                    invoke2(alignmentLinesOwner);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(AlignmentLinesOwner it3) {
                    it3.getAlignmentLines().setPreviousUsedDuringParentLayout$ui(it3.getAlignmentLines().getUsedDuringParentLayout());
                }
            });
        }
    };
    private long placeOuterCoordinatorPosition = IntOffset.INSTANCE.m8289getZeronOccac();
    private final Function0<Unit> placeOuterCoordinatorBlock = new Function0<Unit>() { // from class: androidx.compose.ui.node.MeasurePassDelegate$placeOuterCoordinatorBlock$1
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
            Placeable.PlacementScope scope;
            NodeCoordinator wrappedBy = this.this$0.getOuterCoordinator().getWrappedBy();
            if (wrappedBy == null || (scope = wrappedBy.getPlacementScope()) == null) {
                scope = LayoutNodeKt.requireOwner(this.this$0.getLayoutNode()).getPlacementScope();
            }
            MeasurePassDelegate measurePassDelegate = this.this$0;
            Placeable.PlacementScope $this$invoke_u24lambda_u240 = scope;
            Function1<? super GraphicsLayerScope, Unit> function1 = measurePassDelegate.placeOuterCoordinatorLayerBlock;
            GraphicsLayer layer = measurePassDelegate.placeOuterCoordinatorLayer;
            if (layer != null) {
                $this$invoke_u24lambda_u240.m6864placeWithLayeraW9wM(measurePassDelegate.getOuterCoordinator(), measurePassDelegate.placeOuterCoordinatorPosition, layer, measurePassDelegate.placeOuterCoordinatorZIndex);
            } else if (function1 == null) {
                $this$invoke_u24lambda_u240.m6855place70tqf50(measurePassDelegate.getOuterCoordinator(), measurePassDelegate.placeOuterCoordinatorPosition, measurePassDelegate.placeOuterCoordinatorZIndex);
            } else {
                $this$invoke_u24lambda_u240.m6863placeWithLayeraW9wM(measurePassDelegate.getOuterCoordinator(), measurePassDelegate.placeOuterCoordinatorPosition, measurePassDelegate.placeOuterCoordinatorZIndex, function1);
            }
        }
    };

    /* JADX INFO: compiled from: MeasurePassDelegate.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[LayoutNode.LayoutState.values().length];
            try {
                iArr[LayoutNode.LayoutState.Measuring.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[LayoutNode.LayoutState.LayingOut.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[LayoutNode.UsageByParent.values().length];
            try {
                iArr2[LayoutNode.UsageByParent.InMeasureBlock.ordinal()] = 1;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr2[LayoutNode.UsageByParent.InLayoutBlock.ordinal()] = 2;
            } catch (NoSuchFieldError e4) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    public MeasurePassDelegate(LayoutNodeLayoutDelegate layoutNodeLayoutDelegate) {
        this.layoutNodeLayoutDelegate = layoutNodeLayoutDelegate;
    }

    /* JADX INFO: renamed from: getPreviousPlaceOrder$ui, reason: from getter */
    public final int getPreviousPlaceOrder() {
        return this.previousPlaceOrder;
    }

    @Override // androidx.compose.ui.node.AlignmentLinesOwner
    public int getPlaceOrder() {
        return this.placeOrder;
    }

    public final boolean getPlacedOnce() {
        return this.placedOnce;
    }

    /* JADX INFO: renamed from: getLastConstraints-DWUhwKw */
    public final Constraints m7052getLastConstraintsDWUhwKw() {
        if (this.measuredOnce) {
            return Constraints.m8090boximpl(getMeasurementConstraints());
        }
        return null;
    }

    public final LayoutNode getLayoutNode() {
        return this.layoutNodeLayoutDelegate.getLayoutNode();
    }

    /* JADX INFO: renamed from: getMeasuredByParent$ui, reason: from getter */
    public final LayoutNode.UsageByParent getMeasuredByParent() {
        return this.measuredByParent;
    }

    public final void setMeasuredByParent$ui(LayoutNode.UsageByParent usageByParent) {
        this.measuredByParent = usageByParent;
    }

    /* JADX INFO: renamed from: getDuringAlignmentLinesQuery$ui, reason: from getter */
    public final boolean getDuringAlignmentLinesQuery() {
        return this.duringAlignmentLinesQuery;
    }

    public final void setDuringAlignmentLinesQuery$ui(boolean z) {
        this.duringAlignmentLinesQuery = z;
    }

    /* JADX INFO: renamed from: getLastPosition-nOcc-ac$ui, reason: from getter */
    public final long getLastPosition() {
        return this.lastPosition;
    }

    @Override // androidx.compose.ui.layout.Measured, androidx.compose.ui.layout.IntrinsicMeasurable
    public Object getParentData() {
        return this.parentData;
    }

    private final LookaheadPassDelegate getLookaheadPassDelegate() {
        return this.layoutNodeLayoutDelegate.getLookaheadPassDelegate();
    }

    /* JADX INFO: renamed from: isPlaced$ui, reason: from getter */
    public final boolean getIsPlaced() {
        return this.isPlaced;
    }

    public final void setPlaced$ui(boolean z) {
        this.isPlaced = z;
    }

    /* JADX INFO: renamed from: isPlacedByParent, reason: from getter */
    public final boolean getIsPlacedByParent() {
        return this.isPlacedByParent;
    }

    public final void setPlacedByParent$ui(boolean z) {
        this.isPlacedByParent = z;
    }

    /* JADX INFO: renamed from: getMeasurePending$ui, reason: from getter */
    public final boolean getMeasurePending() {
        return this.measurePending;
    }

    /* JADX INFO: renamed from: getLayoutPending$ui, reason: from getter */
    public final boolean getLayoutPending() {
        return this.layoutPending;
    }

    public final LayoutNode.LayoutState getLayoutState() {
        return this.layoutNodeLayoutDelegate.getLayoutState();
    }

    public final void setLayoutState(LayoutNode.LayoutState value) {
        this.layoutNodeLayoutDelegate.setLayoutState$ui(value);
    }

    public final NodeCoordinator getOuterCoordinator() {
        return this.layoutNodeLayoutDelegate.getOuterCoordinator();
    }

    @Override // androidx.compose.ui.node.AlignmentLinesOwner
    public NodeCoordinator getInnerCoordinator() {
        return getLayoutNode().getInnerCoordinator$ui();
    }

    @Override // androidx.compose.ui.node.AlignmentLinesOwner
    public AlignmentLines getAlignmentLines() {
        return this.alignmentLines;
    }

    /* JADX INFO: renamed from: getChildDelegatesDirty$ui, reason: from getter */
    public final boolean getChildDelegatesDirty() {
        return this.childDelegatesDirty;
    }

    public final void setChildDelegatesDirty$ui(boolean z) {
        this.childDelegatesDirty = z;
    }

    public final List<MeasurePassDelegate> getChildDelegates$ui() {
        getLayoutNode().updateChildrenIfDirty$ui();
        if (!this.childDelegatesDirty) {
            return this._childDelegates.asMutableList();
        }
        LayoutNode $this$updateChildMeasurables$iv = getLayoutNode();
        MutableVector<MeasurePassDelegate> mutableVector = this._childDelegates;
        MutableVector<LayoutNode> mutableVector2 = $this$updateChildMeasurables$iv.get_children$ui();
        Object[] content$iv$iv$iv = mutableVector2.content;
        int size$iv$iv$iv = mutableVector2.getSize();
        for (int i$iv$iv$iv = 0; i$iv$iv$iv < size$iv$iv$iv; i$iv$iv$iv++) {
            LayoutNode layoutNode$iv = (LayoutNode) content$iv$iv$iv[i$iv$iv$iv];
            int i$iv = i$iv$iv$iv;
            if (mutableVector.getSize() <= i$iv) {
                mutableVector.add(layoutNode$iv.getLayoutDelegate().getMeasurePassDelegate());
            } else {
                mutableVector.set(i$iv, layoutNode$iv.getLayoutDelegate().getMeasurePassDelegate());
            }
        }
        mutableVector.removeRange($this$updateChildMeasurables$iv.getChildren$ui().size(), mutableVector.getSize());
        this.childDelegatesDirty = false;
        return this._childDelegates.asMutableList();
    }

    public final void markDetachedFromParentLookaheadPass$ui() {
        this.layoutNodeLayoutDelegate.setDetachedFromParentLookaheadPass$ui(true);
    }

    public final boolean getLayingOutChildren() {
        return this.layingOutChildren;
    }

    public final Function0<Unit> getPerformMeasureBlock$ui() {
        return this.performMeasureBlock;
    }

    @Override // androidx.compose.ui.node.AlignmentLinesOwner
    public void layoutChildren() {
        this.layingOutChildren = true;
        getAlignmentLines().recalculateQueryOwner();
        if (this.layoutPending) {
            onBeforeLayoutChildren();
        }
        if (this.layoutPendingForAlignment || (!this.duringAlignmentLinesQuery && !getInnerCoordinator().getIsPlacingForAlignment() && this.layoutPending)) {
            this.layoutPending = false;
            LayoutNode.LayoutState oldLayoutState = getLayoutState();
            setLayoutState(LayoutNode.LayoutState.LayingOut);
            this.layoutNodeLayoutDelegate.setCoordinatesAccessedDuringPlacement(false);
            LayoutNode $this$layoutChildren_u24lambda_u240 = getLayoutNode();
            Owner owner = LayoutNodeKt.requireOwner($this$layoutChildren_u24lambda_u240);
            OwnerSnapshotObserver this_$iv = owner.getSnapshotObserver();
            Function0<Unit> function0 = this.layoutChildrenBlock;
            Function1 onChanged$iv$iv = this_$iv.onCommitAffectingLayout;
            this_$iv.observer.observeReads($this$layoutChildren_u24lambda_u240, onChanged$iv$iv, function0);
            setLayoutState(oldLayoutState);
            this.layoutPendingForAlignment = false;
        }
        if (getAlignmentLines().getUsedDuringParentLayout()) {
            getAlignmentLines().setPreviousUsedDuringParentLayout$ui(true);
        }
        if (getAlignmentLines().getDirty() && getAlignmentLines().getRequired$ui()) {
            getAlignmentLines().recalculate();
        }
        this.layingOutChildren = false;
    }

    public final void checkChildrenPlaceOrderForUpdates() {
        LayoutNode $this$checkChildrenPlaceOrderForUpdates_u24lambda_u240 = getLayoutNode();
        MutableVector<LayoutNode> mutableVector = $this$checkChildrenPlaceOrderForUpdates_u24lambda_u240.get_children$ui();
        Object[] content$iv$iv = mutableVector.content;
        int size$iv$iv = mutableVector.getSize();
        for (int i$iv$iv = 0; i$iv$iv < size$iv$iv; i$iv$iv++) {
            LayoutNode child = (LayoutNode) content$iv$iv[i$iv$iv];
            if (child.getMeasurePassDelegate$ui().previousPlaceOrder != child.getPlaceOrder$ui()) {
                $this$checkChildrenPlaceOrderForUpdates_u24lambda_u240.onZSortedChildrenInvalidated$ui();
                $this$checkChildrenPlaceOrderForUpdates_u24lambda_u240.invalidateLayer$ui();
                if (child.getPlaceOrder$ui() == Integer.MAX_VALUE) {
                    if (child.getLayoutDelegate().getDetachedFromParentLookaheadPlacement() || LayoutNodeLayoutDelegateKt.isOutMostLookaheadRoot(child)) {
                        LookaheadPassDelegate lookaheadPassDelegate$ui = child.getLookaheadPassDelegate$ui();
                        Intrinsics.checkNotNull(lookaheadPassDelegate$ui);
                        lookaheadPassDelegate$ui.markNodeAndSubtreeAsNotPlaced$ui(false);
                    }
                    child.getMeasurePassDelegate$ui().markSubtreeAsNotPlaced();
                }
            }
        }
    }

    private final void markSubtreeAsNotPlaced() {
        if (this.isPlaced) {
            this.isPlaced = false;
            LayoutNodeKt.requireOwner(getLayoutNode()).getRectManager().remove(getLayoutNode());
            LayoutNode this_$iv = getLayoutNode();
            NodeCoordinator final$iv = this_$iv.getInnerCoordinator$ui().getWrapped();
            for (NodeCoordinator delegate$iv = this_$iv.getOuterCoordinator$ui(); !Intrinsics.areEqual(delegate$iv, final$iv) && delegate$iv != null; delegate$iv = delegate$iv.getWrapped()) {
                NodeCoordinator it = delegate$iv;
                it.onUnplaced();
                it.releaseLayer();
            }
            LayoutNode this_$iv$iv = getLayoutNode();
            MutableVector<LayoutNode> mutableVector = this_$iv$iv.get_children$ui();
            Object[] content$iv$iv$iv = mutableVector.content;
            int size$iv$iv$iv = mutableVector.getSize();
            for (int i$iv$iv$iv = 0; i$iv$iv$iv < size$iv$iv$iv; i$iv$iv$iv++) {
                LayoutNode it$iv = (LayoutNode) content$iv$iv$iv[i$iv$iv$iv];
                it$iv.getMeasurePassDelegate$ui().markSubtreeAsNotPlaced();
            }
        }
    }

    private final void markNodeAndSubtreeAsPlaced() {
        boolean wasPlaced = this.isPlaced;
        this.isPlaced = true;
        LayoutNode $this$markNodeAndSubtreeAsPlaced_u24lambda_u240 = getLayoutNode();
        if (!wasPlaced) {
            $this$markNodeAndSubtreeAsPlaced_u24lambda_u240.getInnerCoordinator$ui().onPlaced();
            LayoutNodeKt.requireOwner($this$markNodeAndSubtreeAsPlaced_u24lambda_u240).getRectManager().recalculateRectIfDirty(getLayoutNode());
            if ($this$markNodeAndSubtreeAsPlaced_u24lambda_u240.getMeasurePending$ui()) {
                LayoutNode.requestRemeasure$ui$default($this$markNodeAndSubtreeAsPlaced_u24lambda_u240, true, false, false, 6, null);
            } else if ($this$markNodeAndSubtreeAsPlaced_u24lambda_u240.getLookaheadMeasurePending$ui()) {
                LayoutNode.requestLookaheadRemeasure$ui$default($this$markNodeAndSubtreeAsPlaced_u24lambda_u240, true, false, false, 6, null);
            }
        }
        NodeCoordinator final$iv = $this$markNodeAndSubtreeAsPlaced_u24lambda_u240.getInnerCoordinator$ui().getWrapped();
        for (NodeCoordinator delegate$iv = $this$markNodeAndSubtreeAsPlaced_u24lambda_u240.getOuterCoordinator$ui(); !Intrinsics.areEqual(delegate$iv, final$iv) && delegate$iv != null; delegate$iv = delegate$iv.getWrapped()) {
            NodeCoordinator it = delegate$iv;
            if (it.getLastLayerDrawingWasSkipped()) {
                it.invalidateLayer();
            }
        }
        MutableVector<LayoutNode> mutableVector = $this$markNodeAndSubtreeAsPlaced_u24lambda_u240.get_children$ui();
        Object[] content$iv$iv = mutableVector.content;
        int size$iv$iv = mutableVector.getSize();
        for (int i$iv$iv = 0; i$iv$iv < size$iv$iv; i$iv$iv++) {
            LayoutNode it2 = (LayoutNode) content$iv$iv[i$iv$iv];
            if (it2.getPlaceOrder$ui() != Integer.MAX_VALUE) {
                it2.getMeasurePassDelegate$ui().markNodeAndSubtreeAsPlaced();
                $this$markNodeAndSubtreeAsPlaced_u24lambda_u240.rescheduleRemeasureOrRelayout$ui(it2);
            }
        }
    }

    /* JADX INFO: renamed from: getZIndex$ui, reason: from getter */
    public final float getZIndex() {
        return this.zIndex;
    }

    public final void onNodePlaced$ui() {
        this.onNodePlacedCalled = true;
        LayoutNode parent = getLayoutNode().getParent$ui();
        float newZIndex = getInnerCoordinator().getZIndex();
        LayoutNode this_$iv = getLayoutNode();
        NodeCoordinator inner$iv = this_$iv.getInnerCoordinator$ui();
        for (NodeCoordinator coordinator$iv = this_$iv.getOuterCoordinator$ui(); coordinator$iv != inner$iv; coordinator$iv = ((LayoutModifierNodeCoordinator) coordinator$iv).getWrapped()) {
            Intrinsics.checkNotNull(coordinator$iv, "null cannot be cast to non-null type androidx.compose.ui.node.LayoutModifierNodeCoordinator");
            LayoutModifierNodeCoordinator it = (LayoutModifierNodeCoordinator) coordinator$iv;
            newZIndex += it.getZIndex();
        }
        if (!(newZIndex == this.zIndex)) {
            this.zIndex = newZIndex;
            if (parent != null) {
                parent.onZSortedChildrenInvalidated$ui();
            }
            if (parent != null) {
                parent.invalidateLayer$ui();
            }
        }
        if (!getInnerCoordinator().getIsPlacingForAlignment()) {
            boolean wasPlaced = this.isPlaced;
            if (!wasPlaced || getAlignmentLines().getQueried$ui()) {
                markNodeAndSubtreeAsPlaced();
            }
            if (!wasPlaced) {
                if (parent != null) {
                    parent.invalidateLayer$ui();
                }
                if (this.relayoutWithoutParentInProgress && parent != null) {
                    LayoutNode.requestRelayout$ui$default(parent, false, 1, null);
                }
            } else {
                getLayoutNode().getInnerCoordinator$ui().onPlaced();
            }
        }
        if (parent != null) {
            if (!this.relayoutWithoutParentInProgress && parent.getLayoutState$ui() == LayoutNode.LayoutState.LayingOut) {
                boolean value$iv = getPlaceOrder() == Integer.MAX_VALUE;
                if (!value$iv) {
                    InlineClassHelperKt.throwIllegalStateException("Place was called on a node which was placed already");
                }
                this.placeOrder = parent.getLayoutDelegate().getNextChildPlaceOrder();
                LayoutNodeLayoutDelegate layoutDelegate = parent.getLayoutDelegate();
                layoutDelegate.setNextChildPlaceOrder$ui(layoutDelegate.getNextChildPlaceOrder() + 1);
            }
        } else {
            this.placeOrder = 0;
        }
        layoutChildren();
    }

    public final void clearPlaceOrder() {
        boolean z = false;
        this.layoutNodeLayoutDelegate.setNextChildPlaceOrder$ui(0);
        LayoutNode this_$iv$iv = getLayoutNode();
        MutableVector<LayoutNode> mutableVector = this_$iv$iv.get_children$ui();
        int i$iv$iv$iv = 0;
        Object[] content$iv$iv$iv = mutableVector.content;
        int size$iv$iv$iv = mutableVector.getSize();
        while (i$iv$iv$iv < size$iv$iv$iv) {
            LayoutNode it$iv = (LayoutNode) content$iv$iv$iv[i$iv$iv$iv];
            MeasurePassDelegate child = it$iv.getMeasurePassDelegate$ui();
            child.previousPlaceOrder = child.getPlaceOrder();
            child.placeOrder = Integer.MAX_VALUE;
            child.isPlacedByParent = z;
            if (child.measuredByParent == LayoutNode.UsageByParent.InLayoutBlock) {
                child.measuredByParent = LayoutNode.UsageByParent.NotUsed;
            }
            i$iv$iv$iv++;
            z = false;
        }
    }

    private final void forEachChildDelegate(Function1<? super MeasurePassDelegate, Unit> block) {
        LayoutNode this_$iv = getLayoutNode();
        MutableVector<LayoutNode> mutableVector = this_$iv.get_children$ui();
        Object[] content$iv$iv = mutableVector.content;
        int size$iv$iv = mutableVector.getSize();
        for (int i$iv$iv = 0; i$iv$iv < size$iv$iv; i$iv$iv++) {
            LayoutNode it = (LayoutNode) content$iv$iv[i$iv$iv];
            block.invoke(it.getMeasurePassDelegate$ui());
        }
    }

    /* JADX INFO: renamed from: performMeasure-BRTryo0$ui */
    public final void m7054performMeasureBRTryo0$ui(long constraints) {
        boolean value$iv = getLayoutState() == LayoutNode.LayoutState.Idle;
        if (!value$iv) {
            InlineClassHelperKt.throwIllegalStateException("layout state is not idle before measure starts");
        }
        this.performMeasureConstraints = constraints;
        setLayoutState(LayoutNode.LayoutState.Measuring);
        this.measurePending = false;
        OwnerSnapshotObserver this_$iv = LayoutNodeKt.requireOwner(getLayoutNode()).getSnapshotObserver();
        LayoutNode node$iv = getLayoutNode();
        Function0<Unit> performMeasureBlock$ui = getPerformMeasureBlock$ui();
        Function1 onChanged$iv$iv = this_$iv.onCommitAffectingMeasure;
        this_$iv.observer.observeReads(node$iv, onChanged$iv$iv, performMeasureBlock$ui);
        if (getLayoutState() == LayoutNode.LayoutState.Measuring) {
            markLayoutPending();
            setLayoutState(LayoutNode.LayoutState.Idle);
        }
    }

    @Override // androidx.compose.ui.layout.Measurable
    /* JADX INFO: renamed from: measure-BRTryo0 */
    public Placeable mo6783measureBRTryo0(long constraints) throws Throwable {
        if (getLayoutNode().getIntrinsicsUsageByParent() == LayoutNode.UsageByParent.NotUsed) {
            getLayoutNode().clearSubtreeIntrinsicsUsage$ui();
        }
        if (LayoutNodeLayoutDelegateKt.isOutMostLookaheadRoot(getLayoutNode())) {
            LookaheadPassDelegate $this$measure_BRTryo0_u24lambda_u240 = getLookaheadPassDelegate();
            Intrinsics.checkNotNull($this$measure_BRTryo0_u24lambda_u240);
            $this$measure_BRTryo0_u24lambda_u240.setMeasuredByParent$ui(LayoutNode.UsageByParent.NotUsed);
            if (ComposeToolingFlags.isVerboseTracingEnabled) {
                Trace.beginSection("Compose:lookaheadMeasure");
                try {
                    $this$measure_BRTryo0_u24lambda_u240.mo6783measureBRTryo0(constraints);
                } finally {
                    Trace.endSection();
                }
            } else {
                $this$measure_BRTryo0_u24lambda_u240.mo6783measureBRTryo0(constraints);
            }
        }
        trackMeasurementByParent(getLayoutNode());
        m7055remeasureBRTryo0(constraints);
        return this;
    }

    /* JADX INFO: renamed from: remeasure-BRTryo0 */
    public final boolean m7055remeasureBRTryo0(long constraints) throws Throwable {
        long outerPreviousMeasuredSize;
        OwnerSnapshotObserver this_$iv$iv;
        Function0<Unit> performMeasureBlock$ui;
        LayoutNode layoutNode;
        Function1 onChanged$iv$iv$iv;
        LayoutNode layoutNode$iv = getLayoutNode();
        try {
            boolean value$iv = !getLayoutNode().getIsDeactivated();
            if (!value$iv) {
                try {
                    InlineClassHelperKt.throwIllegalArgumentException("measure is called on a deactivated node");
                } catch (Throwable th) {
                    e$iv = th;
                    layoutNode$iv.rethrowWithComposeStackTrace(e$iv);
                    throw new KotlinNothingValueException();
                }
            }
            Owner owner = LayoutNodeKt.requireOwner(getLayoutNode());
            LayoutNode parent = getLayoutNode().getParent$ui();
            getLayoutNode().setCanMultiMeasure$ui(getLayoutNode().getCanMultiMeasure() || (parent != null && parent.getCanMultiMeasure()));
            if (!getLayoutNode().getMeasurePending$ui()) {
                try {
                    if (Constraints.m8096equalsimpl0(getMeasurementConstraints(), constraints)) {
                        Owner.forceMeasureTheSubtree$default(owner, getLayoutNode(), false, 2, null);
                        getLayoutNode().resetSubtreeIntrinsicsUsage$ui();
                        return false;
                    }
                } catch (Throwable th2) {
                    e$iv = th2;
                    layoutNode$iv.rethrowWithComposeStackTrace(e$iv);
                    throw new KotlinNothingValueException();
                }
            }
        } catch (Throwable th3) {
            e$iv = th3;
        }
        try {
            getAlignmentLines().setUsedByModifierMeasurement$ui(false);
            forEachChildAlignmentLinesOwner(new Function1<AlignmentLinesOwner, Unit>() { // from class: androidx.compose.ui.node.MeasurePassDelegate$remeasure$1$2
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
            this.measuredOnce = true;
            outerPreviousMeasuredSize = getOuterCoordinator().mo6791getSizeYbymL2g();
            m6848setMeasurementConstraintsBRTryo0(constraints);
            boolean value$iv$iv = getLayoutState() == LayoutNode.LayoutState.Idle;
            if (!value$iv$iv) {
                InlineClassHelperKt.throwIllegalStateException("layout state is not idle before measure starts");
            }
            this.performMeasureConstraints = constraints;
            setLayoutState(LayoutNode.LayoutState.Measuring);
            this.measurePending = false;
            this_$iv$iv = LayoutNodeKt.requireOwner(getLayoutNode()).getSnapshotObserver();
            LayoutNode node$iv$iv = getLayoutNode();
            performMeasureBlock$ui = getPerformMeasureBlock$ui();
            layoutNode = node$iv$iv;
            onChanged$iv$iv$iv = this_$iv$iv.onCommitAffectingMeasure;
        } catch (Throwable th4) {
            e$iv = th4;
            layoutNode$iv.rethrowWithComposeStackTrace(e$iv);
            throw new KotlinNothingValueException();
        }
        try {
            this_$iv$iv.observer.observeReads(layoutNode, onChanged$iv$iv$iv, performMeasureBlock$ui);
            if (getLayoutState() == LayoutNode.LayoutState.Measuring) {
                markLayoutPending();
                setLayoutState(LayoutNode.LayoutState.Idle);
            }
            boolean sizeChanged = (IntSize.m8319equalsimpl0(getOuterCoordinator().mo6791getSizeYbymL2g(), outerPreviousMeasuredSize) && getOuterCoordinator().getWidth() == getWidth() && getOuterCoordinator().getHeight() == getHeight()) ? false : true;
            int width$iv = getOuterCoordinator().getWidth();
            int height$iv = getOuterCoordinator().getHeight();
            m6847setMeasuredSizeozmzZPI(IntSize.m8316constructorimpl((((long) height$iv) & 4294967295L) | (((long) width$iv) << 32)));
            return sizeChanged;
        } catch (Throwable th5) {
            e$iv = th5;
            layoutNode$iv.rethrowWithComposeStackTrace(e$iv);
            throw new KotlinNothingValueException();
        }
    }

    private final void trackMeasurementByParent(LayoutNode node) {
        LayoutNode.UsageByParent usageByParent;
        LayoutNode parent = node.getParent$ui();
        if (parent != null) {
            boolean value$iv = this.measuredByParent == LayoutNode.UsageByParent.NotUsed || node.getCanMultiMeasure();
            if (!value$iv) {
                InlineClassHelperKt.throwIllegalStateException(LayoutNodeLayoutDelegateKt.MeasuredTwiceErrorMessage);
            }
            switch (WhenMappings.$EnumSwitchMapping$0[parent.getLayoutState$ui().ordinal()]) {
                case 1:
                    usageByParent = LayoutNode.UsageByParent.InMeasureBlock;
                    break;
                case 2:
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

    @Override // androidx.compose.ui.layout.Placeable, androidx.compose.ui.layout.Measured
    public int getMeasuredWidth() {
        return getOuterCoordinator().getMeasuredWidth();
    }

    @Override // androidx.compose.ui.layout.Placeable, androidx.compose.ui.layout.Measured
    public int getMeasuredHeight() {
        return getOuterCoordinator().getMeasuredHeight();
    }

    @Override // androidx.compose.ui.layout.Measured
    public int get(AlignmentLine alignmentLine) {
        LayoutNode parent$ui = getLayoutNode().getParent$ui();
        if ((parent$ui != null ? parent$ui.getLayoutState$ui() : null) == LayoutNode.LayoutState.Measuring) {
            getAlignmentLines().setUsedDuringParentMeasurement$ui(true);
        } else {
            LayoutNode parent$ui2 = getLayoutNode().getParent$ui();
            if ((parent$ui2 != null ? parent$ui2.getLayoutState$ui() : null) == LayoutNode.LayoutState.LayingOut) {
                getAlignmentLines().setUsedDuringParentLayout$ui(true);
            }
        }
        this.duringAlignmentLinesQuery = true;
        int result = getOuterCoordinator().get(alignmentLine);
        this.duringAlignmentLinesQuery = false;
        return result;
    }

    @Override // androidx.compose.ui.layout.Placeable
    /* JADX INFO: renamed from: placeAt-f8xVGno */
    public void mo6784placeAtf8xVGno(long position, float zIndex, Function1<? super GraphicsLayerScope, Unit> layerBlock) throws Throwable {
        m7051placeSelfMLgxB_4(position, zIndex, layerBlock, null);
    }

    @Override // androidx.compose.ui.layout.Placeable
    /* JADX INFO: renamed from: placeAt-f8xVGno */
    public void mo6846placeAtf8xVGno(long position, float zIndex, GraphicsLayer layer) throws Throwable {
        m7051placeSelfMLgxB_4(position, zIndex, null, layer);
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
        boolean old = getOuterCoordinator().getIsPlacedUnderMotionFrameOfReference();
        if (newMFR != old) {
            getOuterCoordinator().setPlacedUnderMotionFrameOfReference(newMFR);
            this.needsCoordinatesUpdate = true;
        }
        setPlacedUnderMotionFrameOfReference(newMFR);
    }

    /* JADX WARN: Removed duplicated region for block: B:112:0x00c6 A[Catch: all -> 0x00d6, TryCatch #0 {all -> 0x00d6, blocks: (B:75:0x001e, B:86:0x003e, B:88:0x0044, B:89:0x0047, B:91:0x004d, B:96:0x0058, B:98:0x0062, B:101:0x0074, B:103:0x008c, B:104:0x0094, B:100:0x0068, B:105:0x00b5, B:107:0x00bb, B:110:0x00c2, B:112:0x00c6, B:113:0x00cc, B:79:0x0025, B:81:0x002e, B:83:0x0036, B:85:0x003a), top: B:124:0x001e }] */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0044 A[Catch: all -> 0x00d6, TryCatch #0 {all -> 0x00d6, blocks: (B:75:0x001e, B:86:0x003e, B:88:0x0044, B:89:0x0047, B:91:0x004d, B:96:0x0058, B:98:0x0062, B:101:0x0074, B:103:0x008c, B:104:0x0094, B:100:0x0068, B:105:0x00b5, B:107:0x00bb, B:110:0x00c2, B:112:0x00c6, B:113:0x00cc, B:79:0x0025, B:81:0x002e, B:83:0x0036, B:85:0x003a), top: B:124:0x001e }] */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0055  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0058 A[Catch: all -> 0x00d6, TryCatch #0 {all -> 0x00d6, blocks: (B:75:0x001e, B:86:0x003e, B:88:0x0044, B:89:0x0047, B:91:0x004d, B:96:0x0058, B:98:0x0062, B:101:0x0074, B:103:0x008c, B:104:0x0094, B:100:0x0068, B:105:0x00b5, B:107:0x00bb, B:110:0x00c2, B:112:0x00c6, B:113:0x00cc, B:79:0x0025, B:81:0x002e, B:83:0x0036, B:85:0x003a), top: B:124:0x001e }] */
    /* JADX INFO: renamed from: placeSelf-MLgxB_4 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void m7051placeSelfMLgxB_4(long r21, float r23, kotlin.jvm.functions.Function1<? super androidx.compose.ui.graphics.GraphicsLayerScope, kotlin.Unit> r24, androidx.compose.ui.graphics.layer.GraphicsLayer r25) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 232
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.node.MeasurePassDelegate.m7051placeSelfMLgxB_4(long, float, kotlin.jvm.functions.Function1, androidx.compose.ui.graphics.layer.GraphicsLayer):void");
    }

    /* JADX INFO: renamed from: placeOuterCoordinator-MLgxB_4 */
    private final void m7050placeOuterCoordinatorMLgxB_4(long position, float zIndex, Function1<? super GraphicsLayerScope, Unit> layerBlock, GraphicsLayer layer) {
        boolean value$iv = !getLayoutNode().getIsDeactivated();
        if (!value$iv) {
            InlineClassHelperKt.throwIllegalArgumentException("place is called on a deactivated node");
        }
        setLayoutState(LayoutNode.LayoutState.LayingOut);
        this.lastPosition = position;
        this.lastZIndex = zIndex;
        this.lastLayerBlock = layerBlock;
        this.lastExplicitLayer = layer;
        this.onNodePlacedCalled = false;
        Owner owner = LayoutNodeKt.requireOwner(getLayoutNode());
        if (this.layoutPending || !this.isPlaced) {
            getAlignmentLines().setUsedByModifierLayout$ui(false);
            this.layoutNodeLayoutDelegate.setCoordinatesAccessedDuringModifierPlacement(false);
            this.placeOuterCoordinatorLayerBlock = layerBlock;
            this.placeOuterCoordinatorPosition = position;
            this.placeOuterCoordinatorZIndex = zIndex;
            this.placeOuterCoordinatorLayer = layer;
            OwnerSnapshotObserver this_$iv = owner.getSnapshotObserver();
            LayoutNode node$iv = getLayoutNode();
            Function0<Unit> function0 = this.placeOuterCoordinatorBlock;
            Function1 onChanged$iv$iv = this_$iv.onCommitAffectingLayoutModifier;
            this_$iv.observer.observeReads(node$iv, onChanged$iv$iv, function0);
        } else {
            getOuterCoordinator().m7090placeSelfApparentToRealOffsetMLgxB_4(position, zIndex, layerBlock, layer);
            onNodePlaced$ui();
        }
        setLayoutState(LayoutNode.LayoutState.Idle);
        if (getOuterCoordinator().getIsPlacingForAlignment() && (this.layoutNodeLayoutDelegate.getCoordinatesAccessedDuringModifierPlacement() || this.layoutNodeLayoutDelegate.getCoordinatesAccessedDuringPlacement())) {
            requestLayout();
        }
        this.placedOnce = true;
    }

    public final void replace() {
        LayoutNode parent$ui;
        try {
            this.relayoutWithoutParentInProgress = true;
            boolean value$iv = this.placedOnce;
            if (!value$iv) {
                InlineClassHelperKt.throwIllegalStateException("replace called on unplaced item");
            }
            boolean value$iv2 = this.isPlaced;
            m7050placeOuterCoordinatorMLgxB_4(this.lastPosition, this.lastZIndex, this.lastLayerBlock, this.lastExplicitLayer);
            if (value$iv2 && !this.onNodePlacedCalled && (parent$ui = getLayoutNode().getParent$ui()) != null) {
                LayoutNode.requestRelayout$ui$default(parent$ui, false, 1, null);
            }
        } finally {
        }
    }

    @Override // androidx.compose.ui.layout.IntrinsicMeasurable
    public int minIntrinsicWidth(int height) {
        if (LayoutNodeLayoutDelegateKt.isOutMostLookaheadRoot(getLayoutNode())) {
            LookaheadPassDelegate lookaheadPassDelegate = getLookaheadPassDelegate();
            Intrinsics.checkNotNull(lookaheadPassDelegate);
            return lookaheadPassDelegate.minIntrinsicWidth(height);
        }
        onIntrinsicsQueried();
        return getOuterCoordinator().minIntrinsicWidth(height);
    }

    @Override // androidx.compose.ui.layout.IntrinsicMeasurable
    public int maxIntrinsicWidth(int height) {
        if (LayoutNodeLayoutDelegateKt.isOutMostLookaheadRoot(getLayoutNode())) {
            LookaheadPassDelegate lookaheadPassDelegate = getLookaheadPassDelegate();
            Intrinsics.checkNotNull(lookaheadPassDelegate);
            return lookaheadPassDelegate.maxIntrinsicWidth(height);
        }
        onIntrinsicsQueried();
        return getOuterCoordinator().maxIntrinsicWidth(height);
    }

    @Override // androidx.compose.ui.layout.IntrinsicMeasurable
    public int minIntrinsicHeight(int width) {
        if (LayoutNodeLayoutDelegateKt.isOutMostLookaheadRoot(getLayoutNode())) {
            LookaheadPassDelegate lookaheadPassDelegate = getLookaheadPassDelegate();
            Intrinsics.checkNotNull(lookaheadPassDelegate);
            return lookaheadPassDelegate.minIntrinsicHeight(width);
        }
        onIntrinsicsQueried();
        return getOuterCoordinator().minIntrinsicHeight(width);
    }

    @Override // androidx.compose.ui.layout.IntrinsicMeasurable
    public int maxIntrinsicHeight(int width) {
        if (LayoutNodeLayoutDelegateKt.isOutMostLookaheadRoot(getLayoutNode())) {
            LookaheadPassDelegate lookaheadPassDelegate = getLookaheadPassDelegate();
            Intrinsics.checkNotNull(lookaheadPassDelegate);
            return lookaheadPassDelegate.maxIntrinsicHeight(width);
        }
        onIntrinsicsQueried();
        return getOuterCoordinator().maxIntrinsicHeight(width);
    }

    private final void onIntrinsicsQueried() {
        LayoutNode.UsageByParent intrinsicsUsageByParent;
        LayoutNode.requestRemeasure$ui$default(getLayoutNode(), false, false, false, 7, null);
        LayoutNode parent = getLayoutNode().getParent$ui();
        if (parent != null && getLayoutNode().getIntrinsicsUsageByParent() == LayoutNode.UsageByParent.NotUsed) {
            LayoutNode layoutNode = getLayoutNode();
            switch (WhenMappings.$EnumSwitchMapping$0[parent.getLayoutState$ui().ordinal()]) {
                case 1:
                    intrinsicsUsageByParent = LayoutNode.UsageByParent.InMeasureBlock;
                    break;
                case 2:
                    intrinsicsUsageByParent = LayoutNode.UsageByParent.InLayoutBlock;
                    break;
                default:
                    intrinsicsUsageByParent = parent.getIntrinsicsUsageByParent();
                    break;
            }
            layoutNode.setIntrinsicsUsageByParent$ui(intrinsicsUsageByParent);
        }
    }

    public final void invalidateParentData() {
        this.parentDataDirty = true;
    }

    public final boolean updateParentData() {
        if ((getParentData() == null && getOuterCoordinator().getParentData() == null) || !this.parentDataDirty) {
            return false;
        }
        this.parentDataDirty = false;
        this.parentData = getOuterCoordinator().getParentData();
        return true;
    }

    @Override // androidx.compose.ui.node.AlignmentLinesOwner
    public Map<AlignmentLine, Integer> calculateAlignmentLines() {
        if (!this.duringAlignmentLinesQuery) {
            if (getLayoutState() == LayoutNode.LayoutState.Measuring) {
                getAlignmentLines().setUsedByModifierMeasurement$ui(true);
                if (getAlignmentLines().getDirty()) {
                    markLayoutPending();
                }
            } else {
                getAlignmentLines().setUsedByModifierLayout$ui(true);
            }
        }
        NodeCoordinator $this$calculateAlignmentLines_u24lambda_u240 = getInnerCoordinator();
        boolean previousIsPlacingForAlignment = $this$calculateAlignmentLines_u24lambda_u240.getIsPlacingForAlignment();
        $this$calculateAlignmentLines_u24lambda_u240.setPlacingForAlignment$ui(true);
        layoutChildren();
        $this$calculateAlignmentLines_u24lambda_u240.setPlacingForAlignment$ui(previousIsPlacingForAlignment);
        return getAlignmentLines().getLastCalculation();
    }

    @Override // androidx.compose.ui.node.AlignmentLinesOwner
    public AlignmentLinesOwner getParentAlignmentLinesOwner() {
        LayoutNodeLayoutDelegate layoutDelegate;
        LayoutNode parent$ui = getLayoutNode().getParent$ui();
        if (parent$ui == null || (layoutDelegate = parent$ui.getLayoutDelegate()) == null) {
            return null;
        }
        return layoutDelegate.getAlignmentLinesOwner$ui();
    }

    @Override // androidx.compose.ui.node.AlignmentLinesOwner
    public void forEachChildAlignmentLinesOwner(Function1<? super AlignmentLinesOwner, Unit> block) {
        LayoutNode this_$iv = getLayoutNode();
        MutableVector<LayoutNode> mutableVector = this_$iv.get_children$ui();
        Object[] content$iv$iv = mutableVector.content;
        int size$iv$iv = mutableVector.getSize();
        for (int i$iv$iv = 0; i$iv$iv < size$iv$iv; i$iv$iv++) {
            LayoutNode it = (LayoutNode) content$iv$iv[i$iv$iv];
            block.invoke(it.getLayoutDelegate().getAlignmentLinesOwner$ui());
        }
    }

    @Override // androidx.compose.ui.node.AlignmentLinesOwner
    public void requestLayout() {
        LayoutNode.requestRelayout$ui$default(getLayoutNode(), false, 1, null);
    }

    @Override // androidx.compose.ui.node.AlignmentLinesOwner
    public void requestMeasure() {
        LayoutNode.requestRemeasure$ui$default(getLayoutNode(), false, false, false, 7, null);
    }

    public final void requestLayoutIfCoordinatesAreUsedAndNotifyChildren() {
        if (getLayoutNode().isPlaced() && this.layoutNodeLayoutDelegate.getChildrenAccessingCoordinatesDuringPlacement() > 0) {
            LayoutNodeLayoutDelegate childLayoutDelegate = getLayoutNode().getLayoutDelegate();
            boolean accessed = childLayoutDelegate.getCoordinatesAccessedDuringPlacement() || childLayoutDelegate.getCoordinatesAccessedDuringModifierPlacement();
            if (accessed && !childLayoutDelegate.getLayoutPending$ui()) {
                LayoutNode.requestRelayout$ui$default(getLayoutNode(), false, 1, null);
            }
            LayoutNode this_$iv = getLayoutNode();
            MutableVector<LayoutNode> mutableVector = this_$iv.get_children$ui();
            Object[] content$iv$iv = mutableVector.content;
            int size$iv$iv = mutableVector.getSize();
            for (int i$iv$iv = 0; i$iv$iv < size$iv$iv; i$iv$iv++) {
                LayoutNode child = (LayoutNode) content$iv$iv[i$iv$iv];
                child.getMeasurePassDelegate$ui().requestLayoutIfCoordinatesAreUsedAndNotifyChildren();
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
            if (it.getMeasurePending$ui() && it.getMeasuredByParent$ui() == LayoutNode.UsageByParent.InMeasureBlock && LayoutNode.m7008remeasure_Sx5XlM$ui$default(it, null, 1, null)) {
                LayoutNode.requestRemeasure$ui$default(getLayoutNode(), false, false, false, 7, null);
            }
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
                    LayoutNode.requestRemeasure$ui$default(intrinsicsUsingParent, forceRequest, false, false, 6, null);
                    return;
                case 2:
                    intrinsicsUsingParent.requestRelayout$ui(forceRequest);
                    return;
                default:
                    throw new IllegalStateException("Intrinsics isn't used by the parent".toString());
            }
        }
    }

    public final void onNodeDetached() {
        this.placeOrder = Integer.MAX_VALUE;
        this.previousPlaceOrder = Integer.MAX_VALUE;
        this.isPlaced = false;
    }

    public final void markLayoutPending() {
        this.layoutPending = true;
        this.layoutPendingForAlignment = true;
    }

    public final void markMeasurePending$ui() {
        this.measurePending = true;
    }
}
