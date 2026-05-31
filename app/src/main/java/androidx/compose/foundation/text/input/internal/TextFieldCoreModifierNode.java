package androidx.compose.foundation.text.input.internal;

import androidx.compose.foundation.ScrollState;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.internal.InlineClassHelperKt;
import androidx.compose.foundation.text.contextmenu.modifier.TextContextMenuToolbarHandlerModifierKt;
import androidx.compose.foundation.text.contextmenu.modifier.TextContextMenuToolbarHandlerNode;
import androidx.compose.foundation.text.contextmenu.modifier.ToolbarRequester;
import androidx.compose.foundation.text.input.TextFieldCharSequence;
import androidx.compose.foundation.text.input.TextHighlightType;
import androidx.compose.foundation.text.input.internal.TextFieldCoreModifierNode;
import androidx.compose.foundation.text.input.internal.selection.AndroidTextFieldMagnifier_androidKt;
import androidx.compose.foundation.text.input.internal.selection.TextFieldMagnifierNode;
import androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState;
import androidx.compose.foundation.text.selection.PlatformSelectionBehaviors;
import androidx.compose.foundation.text.selection.SelectionColors;
import androidx.compose.foundation.text.selection.TextSelectionColorsKt;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNodeKt;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.DrawModifierNode;
import androidx.compose.ui.node.DrawModifierNodeKt;
import androidx.compose.ui.node.GlobalPositionAwareModifierNode;
import androidx.compose.ui.node.LayoutModifierNode;
import androidx.compose.ui.node.LayoutModifierNodeKt;
import androidx.compose.ui.node.SemanticsModifierNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.WindowInfo;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextPainter;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.unit.Constraints;
import java.util.concurrent.CancellationException;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.FlowKt;

/* JADX INFO: compiled from: TextFieldCoreModifier.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000ä\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0001\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u00052\u00020\u0006Ba\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\b\u0012\u0006\u0010\u0013\u001a\u00020\u0014\u0012\u0006\u0010\u0015\u001a\u00020\u0016\u0012\u0006\u0010\u0017\u001a\u00020\u0018\u0012\b\u0010\u0019\u001a\u0004\u0018\u00010\u001a¢\u0006\u0004\b\u001b\u0010\u001cJ\b\u0010/\u001a\u000200H\u0016J`\u00101\u001a\u0002002\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aJ#\u00102\u001a\u000203*\u0002042\u0006\u00105\u001a\u0002062\u0006\u00107\u001a\u000208H\u0016¢\u0006\u0004\b9\u0010:J\f\u0010;\u001a\u000200*\u00020<H\u0016J#\u0010=\u001a\u000203*\u0002042\u0006\u00105\u001a\u0002062\u0006\u00107\u001a\u000208H\u0002¢\u0006\u0004\b>\u0010:J#\u0010?\u001a\u000203*\u0002042\u0006\u00105\u001a\u0002062\u0006\u00107\u001a\u000208H\u0002¢\u0006\u0004\b@\u0010:J'\u0010A\u001a\u00020)2\u0006\u0010B\u001a\u00020%2\u0006\u0010C\u001a\u00020)2\u0006\u0010D\u001a\u00020)H\u0002¢\u0006\u0004\bE\u0010FJ3\u0010G\u001a\u000200*\u00020H2\u0006\u0010I\u001a\u00020)2\u0006\u0010J\u001a\u00020)2\u0006\u0010B\u001a\u00020%2\u0006\u0010K\u001a\u00020LH\u0002¢\u0006\u0004\bM\u0010NJ(\u0010O\u001a\u000200*\u00020P2\u0012\u0010Q\u001a\u000e\u0012\u0004\u0012\u00020S\u0012\u0004\u0012\u00020%0R2\u0006\u0010T\u001a\u00020UH\u0002J\u0014\u0010V\u001a\u000200*\u00020P2\u0006\u0010T\u001a\u00020UH\u0002J\b\u0010W\u001a\u000200H\u0002J\u0010\u0010X\u001a\u0002002\u0006\u0010Y\u001a\u00020ZH\u0016J\f\u0010[\u001a\u000200*\u00020\\H\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u001f\u001a\u00020\b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b \u0010!R\u0010\u0010\"\u001a\u0004\u0018\u00010#X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010$\u001a\u0004\u0018\u00010%X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020'X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020)X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020)X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020,X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020.X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006]"}, d2 = {"Landroidx/compose/foundation/text/input/internal/TextFieldCoreModifierNode;", "Landroidx/compose/ui/node/DelegatingNode;", "Landroidx/compose/ui/node/LayoutModifierNode;", "Landroidx/compose/ui/node/DrawModifierNode;", "Landroidx/compose/ui/node/CompositionLocalConsumerModifierNode;", "Landroidx/compose/ui/node/GlobalPositionAwareModifierNode;", "Landroidx/compose/ui/node/SemanticsModifierNode;", "isFocused", "", "isDragHovered", "textLayoutState", "Landroidx/compose/foundation/text/input/internal/TextLayoutState;", "textFieldState", "Landroidx/compose/foundation/text/input/internal/TransformedTextFieldState;", "textFieldSelectionState", "Landroidx/compose/foundation/text/input/internal/selection/TextFieldSelectionState;", "cursorBrush", "Landroidx/compose/ui/graphics/Brush;", "writeable", "scrollState", "Landroidx/compose/foundation/ScrollState;", "orientation", "Landroidx/compose/foundation/gestures/Orientation;", "toolbarRequester", "Landroidx/compose/foundation/text/contextmenu/modifier/ToolbarRequester;", "platformSelectionBehaviors", "Landroidx/compose/foundation/text/selection/PlatformSelectionBehaviors;", "<init>", "(ZZLandroidx/compose/foundation/text/input/internal/TextLayoutState;Landroidx/compose/foundation/text/input/internal/TransformedTextFieldState;Landroidx/compose/foundation/text/input/internal/selection/TextFieldSelectionState;Landroidx/compose/ui/graphics/Brush;ZLandroidx/compose/foundation/ScrollState;Landroidx/compose/foundation/gestures/Orientation;Landroidx/compose/foundation/text/contextmenu/modifier/ToolbarRequester;Landroidx/compose/foundation/text/selection/PlatformSelectionBehaviors;)V", "cursorAnimation", "Landroidx/compose/foundation/text/input/internal/CursorAnimationState;", "showCursor", "getShowCursor", "()Z", "changeObserverJob", "Lkotlinx/coroutines/Job;", "previousSelection", "Landroidx/compose/ui/text/TextRange;", "previousCursorRect", "Landroidx/compose/ui/geometry/Rect;", "previousTextLayoutSize", "", "previousContainerSize", "textFieldMagnifierNode", "Landroidx/compose/foundation/text/input/internal/selection/TextFieldMagnifierNode;", "textContextMenuToolbarHandlerNode", "Landroidx/compose/foundation/text/contextmenu/modifier/TextContextMenuToolbarHandlerNode;", "onAttach", "", "updateNode", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "measurable", "Landroidx/compose/ui/layout/Measurable;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Measurable;J)Landroidx/compose/ui/layout/MeasureResult;", "draw", "Landroidx/compose/ui/graphics/drawscope/ContentDrawScope;", "measureVerticalScroll", "measureVerticalScroll-3p2s80s", "measureHorizontalScroll", "measureHorizontalScroll-3p2s80s", "calculateOffsetToFollow", "currSelection", "currContainerSize", "currTextLayoutSize", "calculateOffsetToFollow-8ffj60Q", "(JII)I", "updateScrollState", "Landroidx/compose/ui/unit/Density;", "containerSize", "textLayoutSize", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "updateScrollState-tIlFzwE", "(Landroidx/compose/ui/unit/Density;IIJLandroidx/compose/ui/unit/LayoutDirection;)V", "drawHighlight", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "highlight", "Lkotlin/Pair;", "Landroidx/compose/foundation/text/input/TextHighlightType;", "textLayoutResult", "Landroidx/compose/ui/text/TextLayoutResult;", "drawText", "startCursorJob", "onGloballyPositioned", "coordinates", "Landroidx/compose/ui/layout/LayoutCoordinates;", "applySemantics", "Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class TextFieldCoreModifierNode extends DelegatingNode implements LayoutModifierNode, DrawModifierNode, CompositionLocalConsumerModifierNode, GlobalPositionAwareModifierNode, SemanticsModifierNode {
    public static final int $stable = 8;
    private Job changeObserverJob;
    private CursorAnimationState cursorAnimation;
    private Brush cursorBrush;
    private boolean isDragHovered;
    private boolean isFocused;
    private Orientation orientation;
    private PlatformSelectionBehaviors platformSelectionBehaviors;
    private int previousContainerSize;
    private Rect previousCursorRect = new Rect(-1.0f, -1.0f, -1.0f, -1.0f);
    private TextRange previousSelection;
    private int previousTextLayoutSize;
    private ScrollState scrollState;
    private final TextContextMenuToolbarHandlerNode textContextMenuToolbarHandlerNode;
    private final TextFieldMagnifierNode textFieldMagnifierNode;
    private TextFieldSelectionState textFieldSelectionState;
    private TransformedTextFieldState textFieldState;
    private TextLayoutState textLayoutState;
    private ToolbarRequester toolbarRequester;
    private boolean writeable;

    public TextFieldCoreModifierNode(boolean isFocused, boolean isDragHovered, TextLayoutState textLayoutState, TransformedTextFieldState textFieldState, TextFieldSelectionState textFieldSelectionState, Brush cursorBrush, boolean writeable, ScrollState scrollState, Orientation orientation, ToolbarRequester toolbarRequester, PlatformSelectionBehaviors platformSelectionBehaviors) {
        this.isFocused = isFocused;
        this.isDragHovered = isDragHovered;
        this.textLayoutState = textLayoutState;
        this.textFieldState = textFieldState;
        this.textFieldSelectionState = textFieldSelectionState;
        this.cursorBrush = cursorBrush;
        this.writeable = writeable;
        this.scrollState = scrollState;
        this.orientation = orientation;
        this.toolbarRequester = toolbarRequester;
        this.platformSelectionBehaviors = platformSelectionBehaviors;
        this.textFieldMagnifierNode = (TextFieldMagnifierNode) delegate(AndroidTextFieldMagnifier_androidKt.textFieldMagnifierNode(this.textFieldState, this.textFieldSelectionState, this.textLayoutState, this.isFocused || this.isDragHovered));
        this.textContextMenuToolbarHandlerNode = (TextContextMenuToolbarHandlerNode) delegate(new TextContextMenuToolbarHandlerNode(this.toolbarRequester, new TextFieldCoreModifierNode$textContextMenuToolbarHandlerNode$1(this, null), new TextFieldCoreModifierNode$textContextMenuToolbarHandlerNode$2(this, null), new Function1() { // from class: androidx.compose.foundation.text.input.internal.TextFieldCoreModifierNode$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TextFieldCoreModifierNode.textContextMenuToolbarHandlerNode$lambda$0(this.f$0, (LayoutCoordinates) obj);
            }
        }));
    }

    private final boolean getShowCursor() {
        return this.writeable && (this.isFocused || this.isDragHovered) && TextFieldCoreModifierKt.isSpecified(this.cursorBrush);
    }

    static final Rect textContextMenuToolbarHandlerNode$lambda$0(TextFieldCoreModifierNode this$0, LayoutCoordinates destinationCoordinates) {
        Rect rootBounds = this$0.textFieldSelectionState.getDerivedVisibleContentBounds$foundation();
        if (rootBounds == null) {
            rootBounds = Rect.INSTANCE.getZero();
        }
        LayoutCoordinates localCoordinates = this$0.textLayoutState.getTextLayoutNodeCoordinates();
        if (localCoordinates != null) {
            return TextContextMenuToolbarHandlerModifierKt.translateRootToDestination(rootBounds, localCoordinates, destinationCoordinates);
        }
        InlineClassHelperKt.throwIllegalStateExceptionForNullCheck("Required value was null.");
        throw new KotlinNothingValueException();
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void onAttach() {
        if (this.isFocused && getShowCursor()) {
            startCursorJob();
        }
    }

    public final void updateNode(boolean isFocused, boolean isDragHovered, TextLayoutState textLayoutState, TransformedTextFieldState textFieldState, TextFieldSelectionState textFieldSelectionState, Brush cursorBrush, boolean writeable, ScrollState scrollState, Orientation orientation, ToolbarRequester toolbarRequester, PlatformSelectionBehaviors platformSelectionBehaviors) {
        boolean previousShowCursor = getShowCursor();
        boolean wasFocused = this.isFocused;
        TransformedTextFieldState previousTextFieldState = this.textFieldState;
        TextLayoutState previousTextLayoutState = this.textLayoutState;
        TextFieldSelectionState previousTextFieldSelectionState = this.textFieldSelectionState;
        ScrollState previousScrollState = this.scrollState;
        this.isFocused = isFocused;
        this.isDragHovered = isDragHovered;
        this.textLayoutState = textLayoutState;
        this.textFieldState = textFieldState;
        this.textFieldSelectionState = textFieldSelectionState;
        this.cursorBrush = cursorBrush;
        this.writeable = writeable;
        this.scrollState = scrollState;
        this.orientation = orientation;
        this.toolbarRequester = toolbarRequester;
        this.platformSelectionBehaviors = platformSelectionBehaviors;
        this.textFieldMagnifierNode.update(textFieldState, textFieldSelectionState, textLayoutState, isFocused || isDragHovered);
        this.textContextMenuToolbarHandlerNode.update(toolbarRequester);
        if (!getShowCursor()) {
            Job job = this.changeObserverJob;
            if (job != null) {
                Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
            }
            this.changeObserverJob = null;
            CursorAnimationState cursorAnimationState = this.cursorAnimation;
            if (cursorAnimationState != null) {
                cursorAnimationState.cancelAndHide();
            }
        } else if (!wasFocused || !Intrinsics.areEqual(previousTextFieldState, textFieldState) || !previousShowCursor) {
            startCursorJob();
        }
        if (!Intrinsics.areEqual(previousTextFieldState, textFieldState) || !Intrinsics.areEqual(previousTextLayoutState, textLayoutState) || !Intrinsics.areEqual(previousTextFieldSelectionState, textFieldSelectionState) || !Intrinsics.areEqual(previousScrollState, scrollState)) {
            LayoutModifierNodeKt.invalidateMeasurement(this);
        }
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    /* JADX INFO: renamed from: measure-3p2s80s */
    public MeasureResult mo67measure3p2s80s(MeasureScope $this$measure_u2d3p2s80s, Measurable measurable, long constraints) {
        if (this.orientation == Orientation.Vertical) {
            return m1856measureVerticalScroll3p2s80s($this$measure_u2d3p2s80s, measurable, constraints);
        }
        return m1855measureHorizontalScroll3p2s80s($this$measure_u2d3p2s80s, measurable, constraints);
    }

    @Override // androidx.compose.ui.node.DrawModifierNode
    public void draw(ContentDrawScope $this$draw) {
        $this$draw.drawContent();
        TextFieldCharSequence value = this.textFieldState.getVisualText();
        TextLayoutResult textLayoutResult = this.textLayoutState.getLayoutResult();
        if (textLayoutResult == null) {
            return;
        }
        Pair<TextHighlightType, TextRange> highlight = value.getHighlight();
        if (highlight != null) {
            drawHighlight($this$draw, highlight, textLayoutResult);
        }
        if (TextRange.m7567getCollapsedimpl(value.getSelection())) {
            drawText($this$draw, textLayoutResult);
            if (value.shouldShowSelection()) {
                TextFieldCoreModifier_androidKt.drawCursor(this, $this$draw, this.cursorBrush, getShowCursor(), this.cursorAnimation, this.textFieldSelectionState);
            }
        } else {
            if (value.shouldShowSelection()) {
                TextFieldCoreModifier_androidKt.m1858drawSelectionHighlightYmzfRxQ(this, $this$draw, value.getSelection(), textLayoutResult);
            }
            drawText($this$draw, textLayoutResult);
        }
        TextFieldMagnifierNode $this$draw_u24lambda_u241 = this.textFieldMagnifierNode;
        $this$draw_u24lambda_u241.draw($this$draw);
    }

    /* JADX INFO: renamed from: measureVerticalScroll-3p2s80s, reason: not valid java name */
    private final MeasureResult m1856measureVerticalScroll3p2s80s(final MeasureScope $this$measureVerticalScroll_u2d3p2s80s, Measurable measurable, long constraints) {
        long childConstraints = Constraints.m8092copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m8105getMinWidthimpl(constraints) : 0, (11 & 2) != 0 ? Constraints.m8103getMaxWidthimpl(constraints) : 0, (11 & 4) != 0 ? Constraints.m8104getMinHeightimpl(constraints) : 0, (11 & 8) != 0 ? Constraints.m8102getMaxHeightimpl(constraints) : Integer.MAX_VALUE);
        final Placeable placeable = measurable.mo6783measureBRTryo0(childConstraints);
        final int height = Math.min(placeable.getHeight(), Constraints.m8102getMaxHeightimpl(constraints));
        return MeasureScope.layout$default($this$measureVerticalScroll_u2d3p2s80s, placeable.getWidth(), height, null, new Function1() { // from class: androidx.compose.foundation.text.input.internal.TextFieldCoreModifierNode$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TextFieldCoreModifierNode.measureVerticalScroll_3p2s80s$lambda$0(this.f$0, height, placeable, $this$measureVerticalScroll_u2d3p2s80s, (Placeable.PlacementScope) obj);
            }
        }, 4, null);
    }

    static final Unit measureVerticalScroll_3p2s80s$lambda$0(TextFieldCoreModifierNode this$0, int $height, Placeable $placeable, MeasureScope $this_measureVerticalScroll, Placeable.PlacementScope $this$layout) {
        this$0.m1857updateScrollStatetIlFzwE($this$layout, $height, $placeable.getHeight(), this$0.textFieldState.getVisualText().getSelection(), $this_measureVerticalScroll.getLayoutDirection());
        Placeable.PlacementScope.placeRelative$default($this$layout, $placeable, 0, -this$0.scrollState.getValue(), 0.0f, 4, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: measureHorizontalScroll-3p2s80s, reason: not valid java name */
    private final MeasureResult m1855measureHorizontalScroll3p2s80s(final MeasureScope $this$measureHorizontalScroll_u2d3p2s80s, Measurable measurable, long constraints) {
        final Placeable placeable = measurable.mo6783measureBRTryo0(Constraints.m8092copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m8105getMinWidthimpl(constraints) : 0, (11 & 2) != 0 ? Constraints.m8103getMaxWidthimpl(constraints) : Integer.MAX_VALUE, (11 & 4) != 0 ? Constraints.m8104getMinHeightimpl(constraints) : 0, (11 & 8) != 0 ? Constraints.m8102getMaxHeightimpl(constraints) : 0));
        final int width = Math.min(placeable.getWidth(), Constraints.m8103getMaxWidthimpl(constraints));
        return MeasureScope.layout$default($this$measureHorizontalScroll_u2d3p2s80s, width, placeable.getHeight(), null, new Function1() { // from class: androidx.compose.foundation.text.input.internal.TextFieldCoreModifierNode$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TextFieldCoreModifierNode.measureHorizontalScroll_3p2s80s$lambda$0(this.f$0, width, placeable, $this$measureHorizontalScroll_u2d3p2s80s, (Placeable.PlacementScope) obj);
            }
        }, 4, null);
    }

    static final Unit measureHorizontalScroll_3p2s80s$lambda$0(TextFieldCoreModifierNode this$0, int $width, Placeable $placeable, MeasureScope $this_measureHorizontalScroll, Placeable.PlacementScope $this$layout) {
        this$0.m1857updateScrollStatetIlFzwE($this$layout, $width, $placeable.getWidth(), this$0.textFieldState.getVisualText().getSelection(), $this_measureHorizontalScroll.getLayoutDirection());
        Placeable.PlacementScope.placeRelative$default($this$layout, $placeable, -this$0.scrollState.getValue(), 0, 0.0f, 4, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: calculateOffsetToFollow-8ffj60Q, reason: not valid java name */
    private final int m1854calculateOffsetToFollow8ffj60Q(long currSelection, int currContainerSize, int currTextLayoutSize) {
        TextRange textRange = this.previousSelection;
        if (!(textRange != null && TextRange.m7568getEndimpl(currSelection) == TextRange.m7568getEndimpl(textRange.getPackedValue()))) {
            return TextRange.m7568getEndimpl(currSelection);
        }
        TextRange textRange2 = this.previousSelection;
        if (!(textRange2 != null && TextRange.m7573getStartimpl(currSelection) == TextRange.m7573getStartimpl(textRange2.getPackedValue()))) {
            return TextRange.m7573getStartimpl(currSelection);
        }
        if (currTextLayoutSize != this.previousTextLayoutSize || currContainerSize != this.previousContainerSize) {
            return TextRange.m7573getStartimpl(currSelection);
        }
        return -1;
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x008e  */
    /* JADX INFO: renamed from: updateScrollState-tIlFzwE, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void m1857updateScrollStatetIlFzwE(androidx.compose.ui.unit.Density r28, int r29, int r30, long r31, androidx.compose.ui.unit.LayoutDirection r33) {
        /*
            Method dump skipped, instruction units count: 300
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.input.internal.TextFieldCoreModifierNode.m1857updateScrollStatetIlFzwE(androidx.compose.ui.unit.Density, int, int, long, androidx.compose.ui.unit.LayoutDirection):void");
    }

    private final void drawHighlight(DrawScope $this$drawHighlight, Pair<TextHighlightType, TextRange> pair, TextLayoutResult textLayoutResult) {
        int type = pair.component1().getValue();
        long range = pair.component2().getPackedValue();
        if (TextRange.m7567getCollapsedimpl(range)) {
            return;
        }
        Path highlightPath = textLayoutResult.getPathForRange(TextRange.m7571getMinimpl(range), TextRange.m7570getMaximpl(range));
        if (TextHighlightType.m1731equalsimpl0(type, TextHighlightType.INSTANCE.m1735getHandwritingDeletePreviewsxJuwY())) {
            Brush brush = textLayoutResult.getLayoutInput().getStyle().getBrush();
            if (brush != null) {
                DrawScope.m5876drawPathGBMwjPU$default($this$drawHighlight, highlightPath, brush, 0.2f, null, null, 0, 56, null);
                return;
            }
            long $this$takeOrElse_u2dDxMtmZc$iv = textLayoutResult.getLayoutInput().getStyle().m7603getColor0d7_KjU();
            if (!($this$takeOrElse_u2dDxMtmZc$iv != 16)) {
                $this$takeOrElse_u2dDxMtmZc$iv = Color.INSTANCE.m5339getBlack0d7_KjU();
            }
            long textColor = $this$takeOrElse_u2dDxMtmZc$iv;
            long highlightBackgroundColor = Color.m5311copywmQWz5c(textColor, (14 & 1) != 0 ? Color.m5315getAlphaimpl(textColor) : Color.m5315getAlphaimpl(textColor) * 0.2f, (14 & 2) != 0 ? Color.m5319getRedimpl(textColor) : 0.0f, (14 & 4) != 0 ? Color.m5318getGreenimpl(textColor) : 0.0f, (14 & 8) != 0 ? Color.m5316getBlueimpl(textColor) : 0.0f);
            DrawScope.m5877drawPathLG529CI$default($this$drawHighlight, highlightPath, highlightBackgroundColor, 0.0f, null, null, 0, 60, null);
            return;
        }
        long highlightBackgroundColor2 = ((SelectionColors) CompositionLocalConsumerModifierNodeKt.currentValueOf(this, TextSelectionColorsKt.getLocalTextSelectionColors())).getSelectionBackgroundColor();
        DrawScope.m5877drawPathLG529CI$default($this$drawHighlight, highlightPath, highlightBackgroundColor2, 0.0f, null, null, 0, 60, null);
    }

    private final void drawText(DrawScope $this$drawText, TextLayoutResult textLayoutResult) {
        Canvas canvas = $this$drawText.getDrawContext().getCanvas();
        TextPainter.INSTANCE.paint(canvas, textLayoutResult);
    }

    private final void startCursorJob() {
        if (this.cursorAnimation == null) {
            this.cursorAnimation = new CursorAnimationState(((Boolean) CompositionLocalConsumerModifierNodeKt.currentValueOf(this, CompositionLocalsKt.getLocalCursorBlinkEnabled())).booleanValue());
            DrawModifierNodeKt.invalidateDraw(this);
        }
        this.changeObserverJob = BuildersKt__Builders_commonKt.launch$default(getCoroutineScope(), null, null, new AnonymousClass1(null), 3, null);
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.input.internal.TextFieldCoreModifierNode$startCursorJob$1, reason: invalid class name */
    /* JADX INFO: compiled from: TextFieldCoreModifier.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text.input.internal.TextFieldCoreModifierNode$startCursorJob$1", f = "TextFieldCoreModifier.kt", i = {}, l = {592}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return TextFieldCoreModifierNode.this.new AnonymousClass1(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    final Ref.IntRef sign = new Ref.IntRef();
                    sign.element = 1;
                    final TextFieldCoreModifierNode textFieldCoreModifierNode = TextFieldCoreModifierNode.this;
                    this.label = 1;
                    if (FlowKt.collectLatest(SnapshotStateKt.snapshotFlow(new Function0() { // from class: androidx.compose.foundation.text.input.internal.TextFieldCoreModifierNode$startCursorJob$1$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return Integer.valueOf(TextFieldCoreModifierNode.AnonymousClass1.invokeSuspend$lambda$0(textFieldCoreModifierNode, sign));
                        }
                    }), new AnonymousClass2(TextFieldCoreModifierNode.this, null), this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Unit.INSTANCE;
        }

        static final int invokeSuspend$lambda$0(TextFieldCoreModifierNode this$0, Ref.IntRef $sign) {
            this$0.textFieldState.getVisualText();
            boolean isWindowFocused = this$0.getIsAttached() && ((WindowInfo) CompositionLocalConsumerModifierNodeKt.currentValueOf(this$0, CompositionLocalsKt.getLocalWindowInfo())).isWindowFocused();
            int i = (isWindowFocused ? 1 : 2) * $sign.element;
            $sign.element *= -1;
            return i;
        }

        /* JADX INFO: renamed from: androidx.compose.foundation.text.input.internal.TextFieldCoreModifierNode$startCursorJob$1$2, reason: invalid class name */
        /* JADX INFO: compiled from: TextFieldCoreModifier.kt */
        @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "isWindowFocused", ""}, k = 3, mv = {2, 1, 0}, xi = 48)
        @DebugMetadata(c = "androidx.compose.foundation.text.input.internal.TextFieldCoreModifierNode$startCursorJob$1$2", f = "TextFieldCoreModifier.kt", i = {}, l = {594}, m = "invokeSuspend", n = {}, s = {}, v = 1)
        static final class AnonymousClass2 extends SuspendLambda implements Function2<Integer, Continuation<? super Unit>, Object> {
            /* synthetic */ int I$0;
            int label;
            final /* synthetic */ TextFieldCoreModifierNode this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass2(TextFieldCoreModifierNode textFieldCoreModifierNode, Continuation<? super AnonymousClass2> continuation) {
                super(2, continuation);
                this.this$0 = textFieldCoreModifierNode;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.this$0, continuation);
                anonymousClass2.I$0 = ((Number) obj).intValue();
                return anonymousClass2;
            }

            public final Object invoke(int i, Continuation<? super Unit> continuation) {
                return ((AnonymousClass2) create(Integer.valueOf(i), continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Object invoke(Integer num, Continuation<? super Unit> continuation) {
                return invoke(num.intValue(), continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object $result) {
                CursorAnimationState cursorAnimationState;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        int isWindowFocused = this.I$0;
                        if (Math.abs(isWindowFocused) == 1 && (cursorAnimationState = this.this$0.cursorAnimation) != null) {
                            this.label = 1;
                            if (cursorAnimationState.snapToVisibleAndAnimate(this) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        }
                        break;
                    case 1:
                        ResultKt.throwOnFailure($result);
                        break;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                return Unit.INSTANCE;
            }
        }
    }

    @Override // androidx.compose.ui.node.GlobalPositionAwareModifierNode
    public void onGloballyPositioned(LayoutCoordinates coordinates) {
        this.textLayoutState.setCoreNodeCoordinates(coordinates);
        this.textFieldMagnifierNode.onGloballyPositioned(coordinates);
    }

    @Override // androidx.compose.ui.node.SemanticsModifierNode
    public void applySemantics(SemanticsPropertyReceiver $this$applySemantics) {
        TextFieldMagnifierNode $this$applySemantics_u24lambda_u240 = this.textFieldMagnifierNode;
        $this$applySemantics_u24lambda_u240.applySemantics($this$applySemantics);
    }
}
