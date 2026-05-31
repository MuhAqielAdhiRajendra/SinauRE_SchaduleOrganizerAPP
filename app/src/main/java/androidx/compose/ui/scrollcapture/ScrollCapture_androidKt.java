package androidx.compose.ui.scrollcapture;

import androidx.autofill.HintConstants;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LayoutCoordinatesKt;
import androidx.compose.ui.node.NodeCoordinator;
import androidx.compose.ui.semantics.ScrollAxisRange;
import androidx.compose.ui.semantics.SemanticsActions;
import androidx.compose.ui.semantics.SemanticsConfigurationKt;
import androidx.compose.ui.semantics.SemanticsNode;
import androidx.compose.ui.semantics.SemanticsOwnerKt;
import androidx.compose.ui.semantics.SemanticsProperties;
import androidx.compose.ui.unit.IntRect;
import androidx.compose.ui.unit.IntRectKt;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.util.List;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: ScrollCapture.android.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000H\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010 \n\u0000\u001a.\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00010\u0007H\u0002\u001a!\u0010\u0017\u001a\u00020\u0001*\u00020\u00032\u0012\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00140\u0007H\u0082\b\u001a\u0012\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00030\u001a*\u00020\u0003H\u0002\"E\u0010\t\u001a/\b\u0001\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u000f\u0012\u0006\u0012\u0004\u0018\u00010\u0010\u0018\u00010\n*\u00020\u00038@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012\"\u0018\u0010\u0013\u001a\u00020\u0014*\u00020\u00038BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016¨\u0006\u001b"}, d2 = {"visitScrollCaptureCandidates", "", "fromNode", "Landroidx/compose/ui/semantics/SemanticsNode;", "depth", "", "onCandidate", "Lkotlin/Function1;", "Landroidx/compose/ui/scrollcapture/ScrollCaptureCandidate;", "scrollCaptureScrollByAction", "Lkotlin/Function2;", "Landroidx/compose/ui/geometry/Offset;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, TypedValues.CycleType.S_WAVE_OFFSET, "Lkotlin/coroutines/Continuation;", "", "getScrollCaptureScrollByAction", "(Landroidx/compose/ui/semantics/SemanticsNode;)Lkotlin/jvm/functions/Function2;", "canScrollVertically", "", "getCanScrollVertically", "(Landroidx/compose/ui/semantics/SemanticsNode;)Z", "visitDescendants", "onNode", "getChildrenForSearch", "", "ui"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class ScrollCapture_androidKt {
    static /* synthetic */ void visitScrollCaptureCandidates$default(SemanticsNode semanticsNode, int i, Function1 function1, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        visitScrollCaptureCandidates(semanticsNode, i, function1);
    }

    private static final void visitScrollCaptureCandidates(SemanticsNode fromNode, int depth, Function1<? super ScrollCaptureCandidate, Unit> function1) {
        MutableVector nodes$iv = new MutableVector(new SemanticsNode[16], 0);
        nodes$iv.addAll(nodes$iv.getSize(), (List) getChildrenForSearch(fromNode));
        while (true) {
            boolean visitChildren$iv = true;
            if (!(nodes$iv.getSize() != 0)) {
                return;
            }
            SemanticsNode node$iv = (SemanticsNode) nodes$iv.removeAt(nodes$iv.getSize() - 1);
            if (SemanticsOwnerKt.isHidden(node$iv) || node$iv.getUnmergedConfig().contains(SemanticsProperties.INSTANCE.getDisabled())) {
                visitChildren$iv = false;
            } else {
                NodeCoordinator nodeCoordinatorFindCoordinatorToGetBounds$ui = node$iv.findCoordinatorToGetBounds$ui();
                if (nodeCoordinatorFindCoordinatorToGetBounds$ui != null) {
                    LayoutCoordinates nodeCoordinates = nodeCoordinatorFindCoordinatorToGetBounds$ui.getCoordinates();
                    IntRect viewportBoundsInWindow = IntRectKt.roundToIntRect(LayoutCoordinatesKt.boundsInWindow$default(nodeCoordinates, false, 1, null));
                    if (viewportBoundsInWindow.isEmpty()) {
                        visitChildren$iv = false;
                    } else if (getCanScrollVertically(node$iv)) {
                        int candidateDepth = depth + 1;
                        function1.invoke(new ScrollCaptureCandidate(node$iv, candidateDepth, viewportBoundsInWindow, nodeCoordinates));
                        visitScrollCaptureCandidates(node$iv, candidateDepth, function1);
                        visitChildren$iv = false;
                    }
                } else {
                    InlineClassHelperKt.throwIllegalStateExceptionForNullCheck("Expected semantics node to have a coordinator.");
                    throw new KotlinNothingValueException();
                }
            }
            if (visitChildren$iv) {
                nodes$iv.addAll(nodes$iv.getSize(), (List) getChildrenForSearch(node$iv));
            }
        }
    }

    public static final Function2<Offset, Continuation<? super Offset>, Object> getScrollCaptureScrollByAction(SemanticsNode $this$scrollCaptureScrollByAction) {
        return (Function2) SemanticsConfigurationKt.getOrNull($this$scrollCaptureScrollByAction.getUnmergedConfig(), SemanticsActions.INSTANCE.getScrollByOffset());
    }

    private static final boolean getCanScrollVertically(SemanticsNode $this$canScrollVertically) {
        Function2<Offset, Continuation<? super Offset>, Object> scrollCaptureScrollByAction = getScrollCaptureScrollByAction($this$canScrollVertically);
        ScrollAxisRange verticalScrollAxisRange = (ScrollAxisRange) SemanticsConfigurationKt.getOrNull($this$canScrollVertically.getUnmergedConfig(), SemanticsProperties.INSTANCE.getVerticalScrollAxisRange());
        return (scrollCaptureScrollByAction == null || verticalScrollAxisRange == null || verticalScrollAxisRange.getMaxValue().invoke().floatValue() <= 0.0f) ? false : true;
    }

    private static final void visitDescendants(SemanticsNode $this$visitDescendants, Function1<? super SemanticsNode, Boolean> function1) {
        MutableVector nodes = new MutableVector(new SemanticsNode[16], 0);
        nodes.addAll(nodes.getSize(), (List) getChildrenForSearch($this$visitDescendants));
        while (true) {
            if (nodes.getSize() != 0) {
                SemanticsNode node = (SemanticsNode) nodes.removeAt(nodes.getSize() - 1);
                boolean visitChildren = function1.invoke(node).booleanValue();
                if (visitChildren) {
                    nodes.addAll(nodes.getSize(), (List) getChildrenForSearch(node));
                }
            } else {
                return;
            }
        }
    }

    private static final List<SemanticsNode> getChildrenForSearch(SemanticsNode $this$getChildrenForSearch) {
        return $this$getChildrenForSearch.getChildren$ui(false, false, false);
    }
}
