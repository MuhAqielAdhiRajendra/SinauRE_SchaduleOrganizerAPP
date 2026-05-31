package androidx.compose.ui.spatial;

import android.os.Trace;
import androidx.collection.IntObjectMap;
import androidx.collection.IntObjectMapKt;
import androidx.collection.MutableObjectList;
import androidx.collection.ObjectList;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Actual_jvmAndAndroidKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.focus.FocusTargetModifierNode;
import androidx.compose.ui.focus.FocusTargetNode;
import androidx.compose.ui.geometry.InlineClassHelperKt;
import androidx.compose.ui.geometry.MutableRect;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.graphics.Matrix;
import androidx.compose.ui.graphics.MatrixKt;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.node.DelegatableNode;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.LayoutNodeKt;
import androidx.compose.ui.node.MeasurePassDelegate;
import androidx.compose.ui.node.NodeChain;
import androidx.compose.ui.node.NodeCoordinator;
import androidx.compose.ui.node.NodeKind;
import androidx.compose.ui.node.OwnedLayer;
import androidx.compose.ui.semantics.SemanticsInfo;
import androidx.compose.ui.unit.IntOffset;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;

/* JADX INFO: compiled from: RectManager.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\u0090\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\r\b\u0001\u0018\u00002\u00020\u0001B!\u0012\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0006\u0010\u001f\u001a\u00020\u0016J5\u0010 \u001a\u00020\u00162\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\"2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020'¢\u0006\u0004\b)\u0010*J\u0006\u0010+\u001a\u00020\u0016J\u0006\u0010,\u001a\u00020\u0016J\u000e\u0010-\u001a\u00020\u00162\u0006\u0010.\u001a\u00020\u0018J\u0006\u0010/\u001a\u00020\u0016J\u0016\u00100\u001a\u0004\u0018\u00010\u00012\f\u00101\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015J:\u00102\u001a\u0002032\u0006\u00104\u001a\u00020'2\u0006\u00105\u001a\u00020\u001d2\u0006\u00106\u001a\u00020\u001d2\u0006\u00107\u001a\u0002082\u0012\u00101\u001a\u000e\u0012\u0004\u0012\u00020:\u0012\u0004\u0012\u00020\u001609J:\u0010;\u001a\u0002032\u0006\u00104\u001a\u00020'2\u0006\u00105\u001a\u00020\u001d2\u0006\u00106\u001a\u00020\u001d2\u0006\u00107\u001a\u0002082\u0012\u00101\u001a\u000e\u0012\u0004\u0012\u00020:\u0012\u0004\u0012\u00020\u001609J\u0010\u0010<\u001a\u00020\u00162\b\u0010=\u001a\u0004\u0018\u00010\u0001J\u000e\u0010>\u001a\u00020\u00162\u0006\u0010?\u001a\u00020\u0004J\u001e\u0010@\u001a\u00020\u00162\u0006\u0010?\u001a\u00020\u00042\u0006\u0010A\u001a\u00020\u00182\u0006\u0010B\u001a\u00020\u0018J\u000e\u0010C\u001a\u00020\u00162\u0006\u0010?\u001a\u00020\u0004J\u0015\u0010D\u001a\u00020\"2\u0006\u0010?\u001a\u00020\u0004¢\u0006\u0004\bE\u0010FJ\f\u0010G\u001a\u00020\u0016*\u00020\u0004H\u0002J\u0010\u0010H\u001a\u00020\u00162\u0006\u0010?\u001a\u00020\u0004H\u0002J\u0010\u0010K\u001a\u00020\u00162\u0006\u0010?\u001a\u00020\u0004H\u0002J\u0014\u0010L\u001a\u00020\u0016*\u00020M2\u0006\u0010N\u001a\u00020JH\u0002J\f\u0010O\u001a\u00020\u0018*\u00020MH\u0002J\u0013\u0010P\u001a\u00020\"*\u00020\u0004H\u0002¢\u0006\u0004\bQ\u0010FJ\u000e\u0010R\u001a\u00020\u00162\u0006\u0010?\u001a\u00020\u0004J\u001d\u0010S\u001a\u00020\u00182\u0006\u0010T\u001a\u00020'2\u0006\u0010U\u001a\u00020'H\u0000¢\u0006\u0002\bVJ7\u0010W\u001a\u0004\u0018\u00010X2\u0006\u0010Y\u001a\u00020'2\u0006\u0010Z\u001a\u00020'2\u0006\u0010[\u001a\u00020'2\u0006\u0010\\\u001a\u00020'2\u0006\u0010]\u001a\u00020'H\u0000¢\u0006\u0002\b^J1\u0010_\u001a\u00020\u0018*\u0002082\u0006\u0010Y\u001a\u00020'2\u0006\u0010Z\u001a\u00020'2\u0006\u0010[\u001a\u00020'2\u0006\u0010\\\u001a\u00020'H\u0000¢\u0006\u0002\b`J\u0019\u0010a\u001a\u00020\u0018*\u00020\u00042\u0006\u0010b\u001a\u00020\u0004H\u0000¢\u0006\u0002\bcJ\u000e\u0010d\u001a\u00020\u00162\u0006\u0010?\u001a\u00020\u0004R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001c\u0010\r\u001a\u00020\u000e8\u0000X\u0081\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\u00150\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010I\u001a\u00020JX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006e"}, d2 = {"Landroidx/compose/ui/spatial/RectManager;", "", "layoutNodes", "Landroidx/collection/IntObjectMap;", "Landroidx/compose/ui/node/LayoutNode;", "executeDelayed", "Landroidx/compose/ui/spatial/ExecuteDelayed;", "<init>", "(Landroidx/collection/IntObjectMap;Landroidx/compose/ui/spatial/ExecuteDelayed;)V", "rects", "Landroidx/compose/ui/spatial/RectList;", "getRects", "()Landroidx/compose/ui/spatial/RectList;", "throttledCallbacks", "Landroidx/compose/ui/spatial/ThrottledCallbacks;", "getThrottledCallbacks$ui$annotations", "()V", "getThrottledCallbacks$ui", "()Landroidx/compose/ui/spatial/ThrottledCallbacks;", "callbacks", "Landroidx/collection/MutableObjectList;", "Lkotlin/Function0;", "", "isDirty", "", "isScreenOrWindowDirty", "isFragmented", "dispatchToken", "scheduledDispatchDeadline", "", "dispatchLambda", "invalidate", "updateOffsets", "screenOffset", "Landroidx/compose/ui/unit/IntOffset;", "windowOffset", "viewToWindowMatrix", "Landroidx/compose/ui/graphics/Matrix;", "windowWidth", "", "windowHeight", "updateOffsets-gTq6Wqs", "(JJ[FII)V", "resetOffsets", "dispatchCallbacks", "scheduleDebounceCallback", "ensureSomethingScheduled", "removeScheduledCallback", "registerOnChangedCallback", "callback", "registerOnRectChangedCallback", "Landroidx/compose/ui/node/DelegatableNode$RegistrationHandle;", "id", "throttleMillis", "debounceMillis", "node", "Landroidx/compose/ui/node/DelegatableNode;", "Lkotlin/Function1;", "Landroidx/compose/ui/spatial/RelativeLayoutBounds;", "registerOnGlobalLayoutCallback", "unregisterOnChangedCallback", "token", "invalidateCallbacksFor", "layoutNode", "updateFlagsFor", "focusable", "gesturable", "recalculateRectIfDirty", "getOffsetFromRectListFor", "getOffsetFromRectListFor-Bjo55l4", "(Landroidx/compose/ui/node/LayoutNode;)J", "resetHasPositionalLayerTransformationsForSubtreeIfNeeded", "insertOrUpdateTransformedNodeSubhierarchy", "cachedRect", "Landroidx/compose/ui/geometry/MutableRect;", "insertOrUpdateTransformedNode", "boundingRectInRoot", "Landroidx/compose/ui/node/NodeCoordinator;", "rect", "hasPositionalLayerTransformations", "outerToInnerOffset", "outerToInnerOffset-Bjo55l4", "remove", "isTargetDrawnFirst", "targetId", "otherId", "isTargetDrawnFirst$ui", "findFocusableNodeFromRect", "Landroidx/compose/ui/focus/FocusTargetModifierNode;", "left", "top", "right", "bottom", "containerId", "findFocusableNodeFromRect$ui", "intersects", "intersects$ui", "isDescendantOf", "container", "isDescendantOf$ui", "unsetHasCallbacksFor", "ui"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class RectManager {
    public static final int $stable = 8;
    private final MutableRect cachedRect;
    private final MutableObjectList<Function0<Unit>> callbacks;
    private final Function0<Unit> dispatchLambda;
    private Object dispatchToken;
    private final ExecuteDelayed executeDelayed;
    private boolean isDirty;
    private boolean isFragmented;
    private boolean isScreenOrWindowDirty;
    private final IntObjectMap<LayoutNode> layoutNodes;
    private final RectList rects;
    private long scheduledDispatchDeadline;
    private final ThrottledCallbacks throttledCallbacks;

    public RectManager() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    public static /* synthetic */ void getThrottledCallbacks$ui$annotations() {
    }

    public RectManager(IntObjectMap<LayoutNode> intObjectMap, ExecuteDelayed executeDelayed) {
        this.layoutNodes = intObjectMap;
        this.executeDelayed = executeDelayed;
        this.rects = new RectList();
        this.throttledCallbacks = new ThrottledCallbacks();
        this.callbacks = new MutableObjectList<>(0, 1, null);
        this.scheduledDispatchDeadline = -1L;
        this.dispatchLambda = new Function0<Unit>() { // from class: androidx.compose.ui.spatial.RectManager$dispatchLambda$1
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
                this.this$0.dispatchToken = null;
                RectManager rectManager = this.this$0;
                Trace.beginSection("OnPositionedDispatch");
                try {
                    rectManager.dispatchCallbacks();
                    Unit unit = Unit.INSTANCE;
                } finally {
                    Trace.endSection();
                }
            }
        };
        this.cachedRect = new MutableRect(0.0f, 0.0f, 0.0f, 0.0f);
    }

    public /* synthetic */ RectManager(IntObjectMap intObjectMap, ExecuteDelayUsingPostAndRemove executeDelayUsingPostAndRemove, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? IntObjectMapKt.intObjectMapOf() : intObjectMap, (i & 2) != 0 ? ExecuteDelayUsingPostAndRemove.INSTANCE : executeDelayUsingPostAndRemove);
    }

    public final RectList getRects() {
        return this.rects;
    }

    /* JADX INFO: renamed from: getThrottledCallbacks$ui, reason: from getter */
    public final ThrottledCallbacks getThrottledCallbacks() {
        return this.throttledCallbacks;
    }

    public final void invalidate() {
        this.isDirty = true;
    }

    /* JADX INFO: renamed from: updateOffsets-gTq6Wqs, reason: not valid java name */
    public final void m7367updateOffsetsgTq6Wqs(long screenOffset, long windowOffset, float[] viewToWindowMatrix, int windowWidth, int windowHeight) {
        int analysis = RectManagerKt.m7370analyzeComponents58bKbWc(viewToWindowMatrix);
        ThrottledCallbacks throttledCallbacks = this.throttledCallbacks;
        boolean z = true;
        int $this$hasNonTranslationComponents$iv = (analysis & 2) == 0 ? 1 : 0;
        if (!throttledCallbacks.m7384updateOffsetsLDcG7Xg(screenOffset, windowOffset, $this$hasNonTranslationComponents$iv != 0 ? viewToWindowMatrix : null, windowWidth, windowHeight) && !this.isScreenOrWindowDirty) {
            z = false;
        }
        this.isScreenOrWindowDirty = z;
    }

    public final void resetOffsets() {
        this.isScreenOrWindowDirty = this.throttledCallbacks.m7384updateOffsetsLDcG7Xg(IntOffset.INSTANCE.m8289getZeronOccac(), IntOffset.INSTANCE.m8289getZeronOccac(), null, 0, 0);
    }

    public final void dispatchCallbacks() {
        int i$iv;
        removeScheduledCallback();
        long currentTime = Actual_jvmAndAndroidKt.currentTimeMillis();
        boolean isDispatchGlobalCallbacks = this.isDirty || this.isScreenOrWindowDirty;
        if (this.isDirty) {
            this.isDirty = false;
            ObjectList this_$iv = this.callbacks;
            Object[] content$iv = this_$iv.content;
            int i = this_$iv._size;
            for (int i$iv2 = 0; i$iv2 < i; i$iv2++) {
                Function0 it = (Function0) content$iv[i$iv2];
                it.invoke();
            }
            RectList this_$iv2 = this.rects;
            long[] items$iv = this_$iv2.items;
            int size$iv = this_$iv2.itemsSize;
            int i$iv3 = 0;
            while (i$iv3 < items$iv.length - 2 && i$iv3 < size$iv) {
                long meta$iv = items$iv[i$iv3 + 2];
                if ((((int) (meta$iv >> 60)) & 1) == 0) {
                    i$iv = i$iv3;
                } else {
                    long topLeft$iv = items$iv[i$iv3 + 0];
                    long bottomRight$iv = items$iv[i$iv3 + 1];
                    int id = ((int) meta$iv) & 33554431;
                    i$iv = i$iv3;
                    this.throttledCallbacks.fireOnUpdatedRect(id, topLeft$iv, bottomRight$iv, currentTime);
                }
                i$iv3 = i$iv + 3;
            }
            this.rects.clearUpdated();
        }
        if (this.isScreenOrWindowDirty) {
            this.isScreenOrWindowDirty = false;
            this.throttledCallbacks.fireOnRectChangedEntries(currentTime);
        }
        if (isDispatchGlobalCallbacks) {
            this.throttledCallbacks.fireGlobalChangeEntries(currentTime);
        }
        if (this.isFragmented) {
            this.isFragmented = false;
            this.rects.defragment();
        }
        this.throttledCallbacks.triggerDebounced(currentTime);
        if (this.throttledCallbacks.getMinDebounceDeadline() > 0) {
            scheduleDebounceCallback(true);
        }
    }

    public final void scheduleDebounceCallback(boolean ensureSomethingScheduled) {
        boolean canExitEarly = (ensureSomethingScheduled && this.dispatchToken == null) ? false : true;
        long nextDeadline = this.throttledCallbacks.getMinDebounceDeadline();
        if (nextDeadline < 0 && canExitEarly) {
            return;
        }
        long currentScheduledDeadline = this.scheduledDispatchDeadline;
        if (currentScheduledDeadline == nextDeadline && canExitEarly) {
            return;
        }
        Object it = this.dispatchToken;
        if (it != null) {
            this.executeDelayed.removeDelayedExecution(it);
        }
        long currentTime = Actual_jvmAndAndroidKt.currentTimeMillis();
        long nextFrameIsh = 16 + currentTime;
        long deadline = Math.max(nextDeadline, nextFrameIsh);
        this.scheduledDispatchDeadline = deadline;
        long delay = deadline - currentTime;
        this.dispatchToken = this.executeDelayed.executeDelayed(delay, this.dispatchLambda);
    }

    public final void removeScheduledCallback() {
        Object it = this.dispatchToken;
        if (it != null) {
            this.executeDelayed.removeDelayedExecution(it);
            this.dispatchToken = null;
        }
    }

    public final Object registerOnChangedCallback(Function0<Unit> callback) {
        this.callbacks.add(callback);
        return callback;
    }

    public final DelegatableNode.RegistrationHandle registerOnRectChangedCallback(int id, long throttleMillis, long debounceMillis, DelegatableNode node, Function1<? super RelativeLayoutBounds, Unit> callback) {
        DelegatableNode.RegistrationHandle registrationHandleRegisterOnRectChanged = this.throttledCallbacks.registerOnRectChanged(id, throttleMillis, debounceMillis, node, callback);
        LayoutNode layoutNode = DelegatableNodeKt.requireLayoutNode(node.getNode());
        if (layoutNode.getAddedToRectList()) {
            this.rects.updateHasCallbacks(id, true);
        }
        invalidate();
        scheduleDebounceCallback(true);
        return registrationHandleRegisterOnRectChanged;
    }

    public final DelegatableNode.RegistrationHandle registerOnGlobalLayoutCallback(int id, long throttleMillis, long debounceMillis, DelegatableNode node, Function1<? super RelativeLayoutBounds, Unit> callback) {
        return this.throttledCallbacks.registerOnGlobalChange(id, throttleMillis, debounceMillis, node, callback);
    }

    public final void unregisterOnChangedCallback(Object token) {
        if ((TypeIntrinsics.isFunctionOfArity(token, 0) ? (Function0) token : null) == null) {
            return;
        }
        this.callbacks.remove(token);
    }

    public final void invalidateCallbacksFor(LayoutNode layoutNode) {
        if (layoutNode.getAddedToRectList()) {
            this.isDirty = true;
            this.rects.markUpdated(layoutNode.getSemanticsId());
        }
        scheduleDebounceCallback(true);
    }

    public final void updateFlagsFor(LayoutNode layoutNode, boolean focusable, boolean gesturable) {
        if (layoutNode.isAttached()) {
            this.rects.updateFlagsFor(layoutNode.getSemanticsId(), focusable, gesturable);
        }
    }

    public final void recalculateRectIfDirty(LayoutNode layoutNode) {
        long parentOuterInnerOffset;
        if (layoutNode.isPlaced() && layoutNode.getRectInParentDirty()) {
            LayoutNode parent = layoutNode.getParent$ui();
            if (parent == null || parent.getHasPositionalLayerTransformationsInOffsetFromRoot()) {
                parentOuterInnerOffset = parent == null ? IntOffset.INSTANCE.m8289getZeronOccac() : IntOffset.INSTANCE.m8288getMaxnOccac();
            } else {
                if (parent.getOuterToInnerOffsetDirty()) {
                    parent.setOuterToInnerOffsetDirty$ui(false);
                    parent.m7014setOuterToInnerOffsetgyyYBs$ui(m7365outerToInnerOffsetBjo55l4(parent));
                }
                parentOuterInnerOffset = parent.getOuterToInnerOffset();
            }
            NodeCoordinator outer = layoutNode.getOuterCoordinator$ui();
            if (!RectManagerKt.m7371isSetgyyYBs(parentOuterInnerOffset) || hasPositionalLayerTransformations(outer)) {
                insertOrUpdateTransformedNodeSubhierarchy(layoutNode);
            } else if (layoutNode.getHasPositionalLayerTransformationsInOffsetFromRoot()) {
                insertOrUpdateTransformedNodeSubhierarchy(layoutNode);
                resetHasPositionalLayerTransformationsForSubtreeIfNeeded(layoutNode);
            } else {
                long offsetFromParent = IntOffset.m8282plusqkQi6aY(parentOuterInnerOffset, outer.getPosition());
                MeasurePassDelegate delegate = layoutNode.getMeasurePassDelegate$ui();
                int width = delegate.getMeasuredWidth();
                int height = delegate.getMeasuredHeight();
                int semanticsId = layoutNode.getSemanticsId();
                if (layoutNode.getAddedToRectList()) {
                    RectList rectList = this.rects;
                    if (parent != null) {
                        rectList.moveBasedOnParentOffset(semanticsId, parent.getSemanticsId(), IntOffset.m8278getXimpl(offsetFromParent), IntOffset.m8279getYimpl(offsetFromParent), width, height);
                    } else {
                        rectList.move(semanticsId, IntOffset.m8278getXimpl(offsetFromParent), IntOffset.m8279getYimpl(offsetFromParent), IntOffset.m8278getXimpl(offsetFromParent) + width, IntOffset.m8279getYimpl(offsetFromParent) + height);
                    }
                } else {
                    layoutNode.setAddedToRectList$ui(true);
                    boolean focusable = layoutNode.getNodes().m7060hasH91voCI$ui(NodeKind.m7100constructorimpl(1024));
                    boolean gesturable = layoutNode.getNodes().m7060hasH91voCI$ui(NodeKind.m7100constructorimpl(16));
                    boolean hasCallbacks = this.throttledCallbacks.getRectChangedMap().containsKey(semanticsId);
                    RectList rectList2 = this.rects;
                    if (parent != null) {
                        rectList2.insertBasedOnParentOffset(semanticsId, parent.getSemanticsId(), IntOffset.m8278getXimpl(offsetFromParent), IntOffset.m8279getYimpl(offsetFromParent), width, height, focusable, gesturable, hasCallbacks);
                    } else {
                        RectList.insert$default(rectList2, semanticsId, IntOffset.m8278getXimpl(offsetFromParent), IntOffset.m8279getYimpl(offsetFromParent), IntOffset.m8278getXimpl(offsetFromParent) + width, IntOffset.m8279getYimpl(offsetFromParent) + height, 0, focusable, gesturable, hasCallbacks, 0, 544, null);
                    }
                }
            }
            layoutNode.setRectInParentDirty$ui(false);
            invalidate();
            scheduleDebounceCallback(true);
        }
    }

    /* JADX INFO: renamed from: getOffsetFromRectListFor-Bjo55l4, reason: not valid java name */
    public final long m7366getOffsetFromRectListForBjo55l4(LayoutNode layoutNode) {
        long topLeft = this.rects.getTopLeft(layoutNode.getSemanticsId());
        if (topLeft == Long.MAX_VALUE) {
            return IntOffset.INSTANCE.m8288getMaxnOccac();
        }
        int x$iv = (int) (topLeft >> 32);
        int y$iv = (int) topLeft;
        return IntOffset.m8272constructorimpl((((long) x$iv) << 32) | (((long) y$iv) & 4294967295L));
    }

    private final void resetHasPositionalLayerTransformationsForSubtreeIfNeeded(LayoutNode $this$resetHasPositionalLayerTransformationsForSubtreeIfNeeded) {
        if ($this$resetHasPositionalLayerTransformationsForSubtreeIfNeeded.getHasPositionalLayerTransformationsInOffsetFromRoot() && !hasPositionalLayerTransformations($this$resetHasPositionalLayerTransformationsForSubtreeIfNeeded.getOuterCoordinator$ui())) {
            $this$resetHasPositionalLayerTransformationsForSubtreeIfNeeded.setHasPositionalLayerTransformationsInOffsetFromRoot$ui(false);
            if ($this$resetHasPositionalLayerTransformationsForSubtreeIfNeeded.getOuterToInnerOffsetDirty()) {
                long it = m7365outerToInnerOffsetBjo55l4($this$resetHasPositionalLayerTransformationsForSubtreeIfNeeded);
                $this$resetHasPositionalLayerTransformationsForSubtreeIfNeeded.m7014setOuterToInnerOffsetgyyYBs$ui(it);
                $this$resetHasPositionalLayerTransformationsForSubtreeIfNeeded.setOuterToInnerOffsetDirty$ui(false);
            }
            if (IntOffset.m8277equalsimpl0($this$resetHasPositionalLayerTransformationsForSubtreeIfNeeded.getOuterToInnerOffset(), IntOffset.INSTANCE.m8288getMaxnOccac())) {
                return;
            }
            MutableVector<LayoutNode> mutableVector = $this$resetHasPositionalLayerTransformationsForSubtreeIfNeeded.get_children$ui();
            Object[] content$iv$iv = mutableVector.content;
            int size$iv$iv = mutableVector.getSize();
            for (int i$iv$iv = 0; i$iv$iv < size$iv$iv; i$iv$iv++) {
                LayoutNode it2 = (LayoutNode) content$iv$iv[i$iv$iv];
                resetHasPositionalLayerTransformationsForSubtreeIfNeeded(it2);
            }
        }
    }

    private final void insertOrUpdateTransformedNodeSubhierarchy(LayoutNode layoutNode) {
        insertOrUpdateTransformedNode(layoutNode);
        MutableVector<LayoutNode> mutableVector = layoutNode.get_children$ui();
        Object[] content$iv$iv = mutableVector.content;
        int size$iv$iv = mutableVector.getSize();
        for (int i$iv$iv = 0; i$iv$iv < size$iv$iv; i$iv$iv++) {
            LayoutNode it = (LayoutNode) content$iv$iv[i$iv$iv];
            if (it.isPlaced()) {
                insertOrUpdateTransformedNodeSubhierarchy(it);
            }
        }
    }

    private final void insertOrUpdateTransformedNode(LayoutNode layoutNode) {
        layoutNode.setHasPositionalLayerTransformationsInOffsetFromRoot$ui(true);
        NodeCoordinator coord = layoutNode.getOuterCoordinator$ui();
        MeasurePassDelegate delegate = layoutNode.getMeasurePassDelegate$ui();
        int width = delegate.getMeasuredWidth();
        int height = delegate.getMeasuredHeight();
        MutableRect rect = this.cachedRect;
        rect.set(0.0f, 0.0f, width, height);
        boundingRectInRoot(coord, rect);
        int l = (int) rect.getLeft();
        int t = (int) rect.getTop();
        int r = (int) rect.getRight();
        int b = (int) rect.getBottom();
        int id = layoutNode.getSemanticsId();
        boolean firstPlacement = !layoutNode.getAddedToRectList();
        layoutNode.setAddedToRectList$ui(true);
        if (firstPlacement || !this.rects.update(id, l, t, r, b)) {
            LayoutNode parent$ui = layoutNode.getParent$ui();
            int parentId = parent$ui != null ? parent$ui.getSemanticsId() : -1;
            RectList.insert$default(this.rects, id, l, t, r, b, parentId, layoutNode.getNodes().m7060hasH91voCI$ui(NodeKind.m7100constructorimpl(1024)), layoutNode.getNodes().m7060hasH91voCI$ui(NodeKind.m7100constructorimpl(16)), this.throttledCallbacks.getRectChangedMap().containsKey(id), 0, 512, null);
        }
        layoutNode.setRectInParentDirty$ui(false);
        invalidate();
    }

    private final void boundingRectInRoot(NodeCoordinator $this$boundingRectInRoot, MutableRect rect) {
        NodeCoordinator coordinator;
        long j;
        char c;
        NodeCoordinator coordinator2 = $this$boundingRectInRoot;
        while (coordinator2 != null) {
            LayoutNode layoutNode = coordinator2.getLayoutNode();
            if (coordinator2 != layoutNode.getOuterCoordinator$ui() || layoutNode.getHasPositionalLayerTransformationsInOffsetFromRoot()) {
                coordinator = coordinator2;
                j = 4294967295L;
                c = ' ';
            } else {
                long offset = m7366getOffsetFromRectListForBjo55l4(layoutNode);
                if (IntOffset.m8277equalsimpl0(offset, IntOffset.INSTANCE.m8288getMaxnOccac())) {
                    coordinator = coordinator2;
                    j = 4294967295L;
                    c = ' ';
                } else {
                    float x$iv$iv = IntOffset.m8278getXimpl(offset);
                    float y$iv$iv = IntOffset.m8279getYimpl(offset);
                    long v1$iv$iv$iv = Float.floatToRawIntBits(x$iv$iv);
                    long v2$iv$iv$iv = Float.floatToRawIntBits(y$iv$iv);
                    rect.m5053translatek4lQ0M(Offset.m5060constructorimpl((v1$iv$iv$iv << 32) | (v2$iv$iv$iv & 4294967295L)));
                    return;
                }
            }
            OwnedLayer layer = coordinator.getLayer();
            if (layer != null) {
                float[] matrix = layer.mo7157getUnderlyingMatrixsQKQjiQ();
                if (!MatrixKt.m5582isIdentity58bKbWc(matrix)) {
                    Matrix.m5565mapimpl(matrix, rect);
                }
            }
            long $this$toOffset_u2d_u2dgyyYBs$iv = coordinator.getPosition();
            float x$iv$iv2 = IntOffset.m8278getXimpl($this$toOffset_u2d_u2dgyyYBs$iv);
            float y$iv$iv2 = IntOffset.m8279getYimpl($this$toOffset_u2d_u2dgyyYBs$iv);
            long v1$iv$iv$iv2 = Float.floatToRawIntBits(x$iv$iv2);
            long v2$iv$iv$iv2 = Float.floatToRawIntBits(y$iv$iv2);
            rect.m5053translatek4lQ0M(Offset.m5060constructorimpl((v1$iv$iv$iv2 << c) | (v2$iv$iv$iv2 & j)));
            coordinator2 = coordinator.getWrappedBy();
        }
    }

    private final boolean hasPositionalLayerTransformations(NodeCoordinator $this$hasPositionalLayerTransformations) {
        OwnedLayer layer = $this$hasPositionalLayerTransformations.getLayer();
        return (layer == null || MatrixKt.m5582isIdentity58bKbWc(layer.mo7157getUnderlyingMatrixsQKQjiQ())) ? false : true;
    }

    /* JADX INFO: renamed from: outerToInnerOffset-Bjo55l4, reason: not valid java name */
    private final long m7365outerToInnerOffsetBjo55l4(LayoutNode $this$outerToInnerOffset_u2dBjo55l4) {
        NodeCoordinator terminator = $this$outerToInnerOffset_u2dBjo55l4.getOuterCoordinator$ui();
        long position = IntOffset.INSTANCE.m8289getZeronOccac();
        for (NodeCoordinator coordinator = $this$outerToInnerOffset_u2dBjo55l4.getInnerCoordinator$ui(); coordinator != null && coordinator != terminator; coordinator = coordinator.getWrappedBy()) {
            if (hasPositionalLayerTransformations(coordinator)) {
                return IntOffset.INSTANCE.m8288getMaxnOccac();
            }
            position = IntOffset.m8282plusqkQi6aY(position, coordinator.getPosition());
        }
        return position;
    }

    public final void remove(LayoutNode layoutNode) {
        if (layoutNode.getAddedToRectList()) {
            this.rects.remove(layoutNode.getSemanticsId());
            layoutNode.setAddedToRectList$ui(false);
            layoutNode.setRectInParentDirty$ui(true);
            invalidate();
            this.isFragmented = true;
        }
    }

    public final boolean isTargetDrawnFirst$ui(int targetId, int otherId) {
        LayoutNode nodeB;
        LayoutNode nodeA = this.layoutNodes.get(targetId);
        if (nodeA == null || (nodeB = this.layoutNodes.get(otherId)) == null || nodeA.getDepth() == 0 || nodeB.getDepth() == 0) {
            return false;
        }
        while (nodeA.getDepth() > nodeB.getDepth()) {
            LayoutNode parent$ui = nodeA.getParent$ui();
            if (parent$ui == null) {
                return false;
            }
            nodeA = parent$ui;
        }
        if (nodeA == nodeB) {
            return false;
        }
        while (nodeB.getDepth() > nodeA.getDepth()) {
            LayoutNode parent$ui2 = nodeB.getParent$ui();
            if (parent$ui2 == null) {
                return false;
            }
            nodeB = parent$ui2;
        }
        if (nodeA == nodeB) {
            return false;
        }
        LayoutNode lastParentA = nodeA;
        LayoutNode lastParentB = nodeB;
        while (nodeA != nodeB) {
            lastParentA = nodeA;
            lastParentB = nodeB;
            LayoutNode parent$ui3 = nodeA.getParent$ui();
            if (parent$ui3 == null) {
                return false;
            }
            nodeA = parent$ui3;
            LayoutNode parent$ui4 = nodeB.getParent$ui();
            if (parent$ui4 == null) {
                return false;
            }
            nodeB = parent$ui4;
        }
        return (lastParentA.getMeasurePassDelegate$ui().getZIndex() > lastParentB.getMeasurePassDelegate$ui().getZIndex() ? 1 : (lastParentA.getMeasurePassDelegate$ui().getZIndex() == lastParentB.getMeasurePassDelegate$ui().getZIndex() ? 0 : -1)) == 0 ? lastParentA.getPlaceOrder$ui() < lastParentB.getPlaceOrder$ui() : lastParentA.getMeasurePassDelegate$ui().getZIndex() < lastParentB.getMeasurePassDelegate$ui().getZIndex();
    }

    public final FocusTargetModifierNode findFocusableNodeFromRect$ui(int left, int top, int right, int bottom, int containerId) {
        int bestDepth;
        int i$iv;
        LayoutNode container;
        int bestDepth2;
        LayoutNode node;
        Object obj;
        int semanticsId;
        int i;
        LayoutNode node2;
        int i2;
        Modifier.Node node3;
        LayoutNode node4;
        int count$iv$iv$iv$iv;
        MutableVector mutableVector;
        SemanticsInfo semanticsInfoRequireSemanticsInfo;
        RectManager rectManager = this;
        LayoutNode container2 = rectManager.layoutNodes.get(containerId);
        if (container2 == null) {
            return null;
        }
        FocusTargetNode activeFocusTargetNode = LayoutNodeKt.requireOwner(container2).getFocusOwner().getActiveFocusTargetNode();
        int currentlyFocusedId = (activeFocusTargetNode == null || (semanticsInfoRequireSemanticsInfo = DelegatableNodeKt.requireSemanticsInfo(activeFocusTargetNode)) == null) ? -1 : semanticsInfoRequireSemanticsInfo.getSemanticsId();
        RectList this_$iv = rectManager.rects;
        LayoutNode container3 = container2;
        int currentlyFocusedId2 = currentlyFocusedId;
        long j = (((long) left) << 32) | (((long) top) & 4294967295L);
        Object bestTarget = null;
        long destBottomRight$iv = (((long) bottom) & 4294967295L) | (((long) right) << 32);
        long[] items$iv = this_$iv.items;
        int size$iv = this_$iv.itemsSize;
        int i$iv2 = 0;
        int bestDepth3 = Integer.MAX_VALUE;
        while (i$iv2 < items$iv.length - 2 && i$iv2 < size$iv) {
            long meta$iv$iv = items$iv[i$iv2 + 2];
            long[] items$iv2 = items$iv;
            int size$iv2 = size$iv;
            if ((((int) (meta$iv$iv >> 61)) & 1) != 0) {
                long topLeft$iv = items$iv2[i$iv2 + 0];
                long bottomRight$iv = items$iv2[i$iv2 + 1];
                long a$iv$iv = ((destBottomRight$iv - topLeft$iv) - InlineClassHelperKt.Uint64Low32) | ((bottomRight$iv - j) - InlineClassHelperKt.Uint64Low32);
                int i3 = 1;
                int $i$f$rectIntersectsRect = (a$iv$iv & (-9223372034707292160L)) == 0 ? 1 : 0;
                if ($i$f$rectIntersectsRect != 0) {
                    int bestDepth4 = bestDepth3;
                    long meta$iv$iv2 = items$iv2[i$iv2 + 2];
                    int $i$f$unpackMetaValue = (int) meta$iv$iv2;
                    int count$iv$iv$iv$iv2 = $i$f$unpackMetaValue & 33554431;
                    LayoutNode node5 = rectManager.layoutNodes.get(count$iv$iv$iv$iv2);
                    if (node5 != null) {
                        int currentlyFocusedId3 = currentlyFocusedId2;
                        if (currentlyFocusedId3 == count$iv$iv$iv$iv2 && currentlyFocusedId3 != -1) {
                            return null;
                        }
                        if (node5.getDepth() < bestDepth4) {
                            container = container3;
                            if (rectManager.isDescendantOf$ui(node5, container)) {
                                NodeChain this_$iv2 = node5.getNodes();
                                int iM7100constructorimpl = NodeKind.m7100constructorimpl(1024);
                                if ((this_$iv2.getAggregateChildKindSet() & iM7100constructorimpl) != 0) {
                                    Modifier.Node node$iv$iv$iv$iv = this_$iv2.getHead();
                                    while (true) {
                                        if (node$iv$iv$iv$iv == null) {
                                            node = node5;
                                            break;
                                        }
                                        Modifier.Node it$iv$iv$iv = node$iv$iv$iv$iv;
                                        if ((it$iv$iv$iv.getKindSet() & iM7100constructorimpl) != 0) {
                                            MutableVector mutableVector2 = null;
                                            Modifier.Node nodePop = it$iv$iv$iv;
                                            while (nodePop != null) {
                                                int semanticsId2 = count$iv$iv$iv$iv2;
                                                if (nodePop instanceof FocusTargetNode) {
                                                    obj = nodePop;
                                                    node = node5;
                                                    break;
                                                }
                                                Modifier.Node this_$iv$iv$iv$iv$iv = nodePop;
                                                int kind$iv$iv$iv$iv$iv = (this_$iv$iv$iv$iv$iv.getKindSet() & iM7100constructorimpl) != 0 ? i3 : 0;
                                                if (kind$iv$iv$iv$iv$iv != 0) {
                                                    boolean dispatchAgain$iv$iv$iv$iv = nodePop instanceof DelegatingNode;
                                                    if (dispatchAgain$iv$iv$iv$iv) {
                                                        int count$iv$iv$iv$iv3 = 0;
                                                        DelegatingNode this_$iv$iv$iv$iv$iv2 = (DelegatingNode) nodePop;
                                                        Modifier.Node node$iv$iv$iv$iv$iv = this_$iv$iv$iv$iv$iv2.getDelegate();
                                                        while (node$iv$iv$iv$iv$iv != null) {
                                                            Modifier.Node next$iv$iv$iv$iv = node$iv$iv$iv$iv$iv;
                                                            int kind$iv$iv$iv$iv$iv2 = (next$iv$iv$iv$iv.getKindSet() & iM7100constructorimpl) != 0 ? i3 : 0;
                                                            if (kind$iv$iv$iv$iv$iv2 != 0) {
                                                                count$iv$iv$iv$iv3++;
                                                                node3 = nodePop;
                                                                if (count$iv$iv$iv$iv3 == i3) {
                                                                    node3 = next$iv$iv$iv$iv;
                                                                    node4 = node5;
                                                                } else {
                                                                    if (mutableVector2 == null) {
                                                                        count$iv$iv$iv$iv = count$iv$iv$iv$iv3;
                                                                        node4 = node5;
                                                                        mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                                                    } else {
                                                                        count$iv$iv$iv$iv = count$iv$iv$iv$iv3;
                                                                        node4 = node5;
                                                                        mutableVector = mutableVector2;
                                                                    }
                                                                    if (node3 != null) {
                                                                        if (mutableVector != null) {
                                                                            mutableVector.add(node3);
                                                                        }
                                                                        node3 = null;
                                                                    }
                                                                    if (mutableVector != null) {
                                                                        mutableVector.add(next$iv$iv$iv$iv);
                                                                    }
                                                                    mutableVector2 = mutableVector;
                                                                    count$iv$iv$iv$iv3 = count$iv$iv$iv$iv;
                                                                }
                                                            } else {
                                                                node3 = nodePop;
                                                                node4 = node5;
                                                            }
                                                            node$iv$iv$iv$iv$iv = node$iv$iv$iv$iv$iv.getChild();
                                                            nodePop = node3;
                                                            node5 = node4;
                                                            i3 = 1;
                                                        }
                                                        Modifier.Node node6 = nodePop;
                                                        node2 = node5;
                                                        i2 = 1;
                                                        if (count$iv$iv$iv$iv3 == 1) {
                                                            i3 = 1;
                                                            count$iv$iv$iv$iv2 = semanticsId2;
                                                            nodePop = node6;
                                                            node5 = node2;
                                                        }
                                                    } else {
                                                        node2 = node5;
                                                        i2 = i3;
                                                    }
                                                } else {
                                                    node2 = node5;
                                                    i2 = i3;
                                                }
                                                i3 = i2;
                                                nodePop = DelegatableNodeKt.pop(mutableVector2);
                                                count$iv$iv$iv$iv2 = semanticsId2;
                                                node5 = node2;
                                            }
                                            semanticsId = count$iv$iv$iv$iv2;
                                            node = node5;
                                            i = i3;
                                        } else {
                                            semanticsId = count$iv$iv$iv$iv2;
                                            node = node5;
                                            i = i3;
                                        }
                                        int semanticsId3 = it$iv$iv$iv.getAggregateChildKindSet();
                                        if ((semanticsId3 & iM7100constructorimpl) == 0) {
                                            break;
                                        }
                                        node$iv$iv$iv$iv = node$iv$iv$iv$iv.getChild();
                                        i3 = i;
                                        count$iv$iv$iv$iv2 = semanticsId;
                                        node5 = node;
                                    }
                                } else {
                                    node = node5;
                                }
                                obj = null;
                                Object target = (FocusTargetNode) obj;
                                if (target != null) {
                                    currentlyFocusedId2 = currentlyFocusedId3;
                                    i$iv = i$iv2;
                                    bestDepth2 = bestDepth4;
                                    if (intersects$ui((DelegatableNode) target, left, top, right, bottom)) {
                                        bestTarget = target;
                                        bestDepth3 = node.getDepth();
                                    }
                                    i$iv2 = i$iv + 3;
                                    rectManager = this;
                                    container3 = container;
                                    items$iv = items$iv2;
                                    size$iv = size$iv2;
                                } else {
                                    currentlyFocusedId2 = currentlyFocusedId3;
                                    i$iv = i$iv2;
                                    bestDepth2 = bestDepth4;
                                }
                            } else {
                                currentlyFocusedId2 = currentlyFocusedId3;
                                i$iv = i$iv2;
                                bestDepth2 = bestDepth4;
                            }
                        } else {
                            currentlyFocusedId2 = currentlyFocusedId3;
                            i$iv = i$iv2;
                            bestDepth2 = bestDepth4;
                            container = container3;
                        }
                    } else {
                        i$iv = i$iv2;
                        bestDepth2 = bestDepth4;
                        container = container3;
                    }
                    bestDepth3 = bestDepth2;
                    i$iv2 = i$iv + 3;
                    rectManager = this;
                    container3 = container;
                    items$iv = items$iv2;
                    size$iv = size$iv2;
                } else {
                    bestDepth = bestDepth3;
                    i$iv = i$iv2;
                    container = container3;
                }
            } else {
                bestDepth = bestDepth3;
                i$iv = i$iv2;
                container = container3;
            }
            bestDepth3 = bestDepth;
            i$iv2 = i$iv + 3;
            rectManager = this;
            container3 = container;
            items$iv = items$iv2;
            size$iv = size$iv2;
        }
        return (FocusTargetModifierNode) bestTarget;
    }

    public final boolean intersects$ui(DelegatableNode $this$intersects, int left, int top, int right, int bottom) {
        NodeCoordinator coordinator = DelegatableNodeKt.m6955requireCoordinator64DMado($this$intersects, NodeKind.m7100constructorimpl(1024));
        LayoutNode layout = coordinator.getLayoutNode();
        if (Intrinsics.areEqual(coordinator, layout.getOuterCoordinator$ui())) {
            return true;
        }
        long localTopLeft = LayoutCoordinates.m6790localPositionOfS_NoaFU$default(layout.getOuterCoordinator$ui(), coordinator, 0L, false, 6, null);
        long topLeft = layout.getOuterCoordinator$ui().mo6794localToRootMKHz9U(localTopLeft);
        long size = coordinator.mo6791getSizeYbymL2g();
        int bits$iv$iv$iv = (int) (topLeft >> 32);
        float $this$fastRoundToInt$iv = Float.intBitsToFloat(bits$iv$iv$iv);
        int $i$f$fastRoundToInt = Math.round($this$fastRoundToInt$iv);
        int containerRight = ((int) (size >> 32)) + $i$f$fastRoundToInt;
        int bits$iv$iv$iv2 = (int) (topLeft & 4294967295L);
        float $this$fastRoundToInt$iv2 = Float.intBitsToFloat(bits$iv$iv$iv2);
        int containerTop = Math.round($this$fastRoundToInt$iv2);
        int $i$f$unpackInt2 = (int) (size & 4294967295L);
        int containerBottom = $i$f$unpackInt2 + containerTop;
        return left < containerRight && right > $i$f$fastRoundToInt && top < containerBottom && bottom > containerTop;
    }

    public final boolean isDescendantOf$ui(LayoutNode $this$isDescendantOf, LayoutNode container) {
        int ups = $this$isDescendantOf.getDepth() - container.getDepth();
        if (ups <= 0) {
            return false;
        }
        LayoutNode layoutNode = $this$isDescendantOf;
        for (int i = 0; i < ups; i++) {
            LayoutNode parent$ui = layoutNode.getParent$ui();
            if (parent$ui == null) {
                return false;
            }
            layoutNode = parent$ui;
        }
        return layoutNode == container;
    }

    public final void unsetHasCallbacksFor(LayoutNode layoutNode) {
        this.rects.updateHasCallbacks(layoutNode.getSemanticsId(), false);
    }
}
