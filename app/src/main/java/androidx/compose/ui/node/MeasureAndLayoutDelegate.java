package androidx.compose.ui.node;

import android.os.Trace;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.runtime.tooling.ComposeToolingFlags;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.Owner;
import androidx.compose.ui.node.RootForTest;
import androidx.compose.ui.unit.Constraints;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MeasureAndLayoutDelegate.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b'\b\u0001\u0018\u00002\u00020\u0001:\u0001aB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0015\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020 ¢\u0006\u0004\b*\u0010+J\u0018\u0010.\u001a\u00020\t2\u0006\u0010/\u001a\u00020\u00032\b\b\u0002\u00100\u001a\u00020\tJ\u0018\u00101\u001a\u00020\t2\u0006\u0010/\u001a\u00020\u00032\b\b\u0002\u00100\u001a\u00020\tJ\u0018\u00102\u001a\u00020\t2\u0006\u0010/\u001a\u00020\u00032\b\b\u0002\u00100\u001a\u00020\tJ\u0018\u00103\u001a\u00020\t2\u0006\u0010/\u001a\u00020\u00032\b\b\u0002\u00100\u001a\u00020\tJ\u000e\u00104\u001a\u00020(2\u0006\u0010/\u001a\u00020\u0003J\u001f\u00105\u001a\u00020\t2\u0006\u0010/\u001a\u00020\u00032\b\u0010)\u001a\u0004\u0018\u00010 H\u0002¢\u0006\u0002\b6J\u001f\u00107\u001a\u00020\t2\u0006\u0010/\u001a\u00020\u00032\b\u0010)\u001a\u0004\u0018\u00010 H\u0002¢\u0006\u0002\b8J\u0018\u00109\u001a\u00020\t2\u0010\b\u0002\u0010:\u001a\n\u0012\u0004\u0012\u00020(\u0018\u00010;J\u0006\u0010<\u001a\u00020(J\u0010\u0010=\u001a\u00020(2\u0006\u0010/\u001a\u00020\u0003H\u0002J\u001d\u00109\u001a\u00020(2\u0006\u0010/\u001a\u00020\u00032\u0006\u0010)\u001a\u00020 ¢\u0006\u0004\b>\u0010?J\u0010\u0010@\u001a\u00020(2\u0006\u0010/\u001a\u00020\u0003H\u0002J\u001f\u0010A\u001a\u00020(2\u0006\u0010B\u001a\u00020\t2\f\u0010C\u001a\b\u0012\u0004\u0012\u00020(0;H\u0082\bJ\u000e\u0010D\u001a\u00020(2\u0006\u0010E\u001a\u00020\u0017J\b\u0010F\u001a\u00020(H\u0002J\u001a\u0010G\u001a\u00020\t2\u0006\u0010/\u001a\u00020\u00032\b\b\u0002\u0010H\u001a\u00020\tH\u0002J$\u0010I\u001a\u00020\t2\u0006\u0010/\u001a\u00020\u00032\b\b\u0002\u0010H\u001a\u00020\t2\b\b\u0002\u0010J\u001a\u00020\tH\u0002J\b\u0010M\u001a\u00020(H\u0002J\u0018\u0010N\u001a\u00020(2\u0006\u0010/\u001a\u00020\u00032\u0006\u0010H\u001a\u00020\tH\u0002J\u0016\u0010O\u001a\u00020(2\u0006\u0010/\u001a\u00020\u00032\u0006\u0010H\u001a\u00020\tJ\u0018\u0010P\u001a\u00020(2\u0006\u0010Q\u001a\u00020\u00032\u0006\u0010H\u001a\u00020\tH\u0002J\u0018\u0010R\u001a\u00020(2\u0006\u0010/\u001a\u00020\u00032\u0006\u0010H\u001a\u00020\tH\u0002J\u0010\u0010S\u001a\u00020(2\b\b\u0002\u0010T\u001a\u00020\tJ\u000e\u0010U\u001a\u00020(2\u0006\u0010Q\u001a\u00020\u0003J\u0014\u0010`\u001a\u00020\t*\u00020\u00032\u0006\u0010H\u001a\u00020\tH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\b\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\f\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\r\u0010\u000bR\u001a\u0010\u000e\u001a\u00020\tX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u000b\"\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\u001a\u001a\u00020\u00192\u0006\u0010\u0018\u001a\u00020\u00198F@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0014\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001e0\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001f\u001a\u0004\u0018\u00010 X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010!\u001a\u0004\u0018\u00010\"X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u0010\u0010,\u001a\u0004\u0018\u00010-X\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010K\u001a\u00020\t*\u00020\u00038BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bK\u0010LR\u0018\u0010V\u001a\u00020\t*\u00020\u00038BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bW\u0010LR\u0018\u0010X\u001a\u00020\t*\u00020\u00038BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bY\u0010LR\u0018\u0010Z\u001a\u00020\t*\u00020\u00038BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b[\u0010LR\u0018\u0010\\\u001a\u00020\t*\u00020\u00038BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b]\u0010LR\u0018\u0010^\u001a\u00020\t*\u00020\u00038BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b_\u0010L¨\u0006b"}, d2 = {"Landroidx/compose/ui/node/MeasureAndLayoutDelegate;", "", "root", "Landroidx/compose/ui/node/LayoutNode;", "<init>", "(Landroidx/compose/ui/node/LayoutNode;)V", "relayoutNodes", "Landroidx/compose/ui/node/DepthSortedSetsForDifferentPasses;", "hasPendingMeasureOrLayout", "", "getHasPendingMeasureOrLayout", "()Z", "hasPendingOnPositionedCallbacks", "getHasPendingOnPositionedCallbacks", "duringMeasureLayout", "getDuringMeasureLayout$ui", "setDuringMeasureLayout$ui", "(Z)V", "duringFullMeasureLayoutPass", "onPositionedDispatcher", "Landroidx/compose/ui/node/OnPositionedDispatcher;", "onLayoutCompletedListeners", "Landroidx/compose/runtime/collection/MutableVector;", "Landroidx/compose/ui/node/Owner$OnLayoutCompletedListener;", "value", "", "measureIteration", "getMeasureIteration", "()J", "postponedMeasureRequests", "Landroidx/compose/ui/node/MeasureAndLayoutDelegate$PostponedRequest;", "rootConstraints", "Landroidx/compose/ui/unit/Constraints;", "uncaughtExceptionHandler", "Landroidx/compose/ui/node/RootForTest$UncaughtExceptionHandler;", "getUncaughtExceptionHandler$ui", "()Landroidx/compose/ui/node/RootForTest$UncaughtExceptionHandler;", "setUncaughtExceptionHandler$ui", "(Landroidx/compose/ui/node/RootForTest$UncaughtExceptionHandler;)V", "updateRootConstraints", "", "constraints", "updateRootConstraints-BRTryo0", "(J)V", "consistencyChecker", "Landroidx/compose/ui/node/LayoutTreeConsistencyChecker;", "requestLookaheadRemeasure", "layoutNode", "forced", "requestRemeasure", "requestLookaheadRelayout", "requestRelayout", "requestOnPositionedCallback", "doLookaheadRemeasure", "doLookaheadRemeasure-sdFAvZA", "doRemeasure", "doRemeasure-sdFAvZA", "measureAndLayout", "onLayout", "Lkotlin/Function0;", "measureOnly", "remeasureLookaheadRootsInSubtree", "measureAndLayout-0kLqBqw", "(Landroidx/compose/ui/node/LayoutNode;J)V", "ensureSubtreeLookaheadReplaced", "performMeasureAndLayout", "fullPass", "block", "registerOnLayoutCompletedListener", "listener", "callOnLayoutCompletedListeners", "remeasureAndRelayoutIfNeeded", "affectsLookahead", "remeasureIfNeeded", "shouldTraceMeasure", "isUsedInMeasureOrLayout", "(Landroidx/compose/ui/node/LayoutNode;)Z", "drainPostponedMeasureRequests", "remeasureOnly", "forceMeasureTheSubtree", "onlyRemeasureIfPending", "node", "forceMeasureTheSubtreeInternal", "dispatchOnPositionedCallbacks", "forceDispatch", "onNodeDetached", "remeasureCanAffectParentSize", "getRemeasureCanAffectParentSize", "measuredByPlacedParent", "getMeasuredByPlacedParent", "canAffectPlacedParent", "getCanAffectPlacedParent", "canAffectParentInLookahead", "getCanAffectParentInLookahead", "lookaheadRemeasureCanAffectParentSize", "getLookaheadRemeasureCanAffectParentSize", "measurePending", "PostponedRequest", "ui"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class MeasureAndLayoutDelegate {
    public static final int $stable = 8;
    private final LayoutTreeConsistencyChecker consistencyChecker;
    private boolean duringFullMeasureLayoutPass;
    private boolean duringMeasureLayout;
    private final LayoutNode root;
    private Constraints rootConstraints;
    private RootForTest.UncaughtExceptionHandler uncaughtExceptionHandler;
    private final DepthSortedSetsForDifferentPasses relayoutNodes = new DepthSortedSetsForDifferentPasses(Owner.INSTANCE.getEnableExtraAssertions());
    private final OnPositionedDispatcher onPositionedDispatcher = new OnPositionedDispatcher();
    private final MutableVector<Owner.OnLayoutCompletedListener> onLayoutCompletedListeners = new MutableVector<>(new Owner.OnLayoutCompletedListener[16], 0);
    private long measureIteration = 1;
    private final MutableVector<PostponedRequest> postponedMeasureRequests = new MutableVector<>(new PostponedRequest[16], 0);

    /* JADX INFO: compiled from: MeasureAndLayoutDelegate.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

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
                iArr[LayoutNode.LayoutState.LookaheadLayingOut.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[LayoutNode.LayoutState.LayingOut.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[LayoutNode.LayoutState.Idle.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public MeasureAndLayoutDelegate(LayoutNode root) {
        LayoutTreeConsistencyChecker layoutTreeConsistencyChecker;
        this.root = root;
        if (Owner.INSTANCE.getEnableExtraAssertions()) {
            layoutTreeConsistencyChecker = new LayoutTreeConsistencyChecker(this.root, this.relayoutNodes, this.postponedMeasureRequests.asMutableList());
        } else {
            layoutTreeConsistencyChecker = null;
        }
        this.consistencyChecker = layoutTreeConsistencyChecker;
    }

    public final boolean getHasPendingMeasureOrLayout() {
        return this.relayoutNodes.isNotEmpty();
    }

    public final boolean getHasPendingOnPositionedCallbacks() {
        return this.onPositionedDispatcher.isNotEmpty();
    }

    /* JADX INFO: renamed from: getDuringMeasureLayout$ui, reason: from getter */
    public final boolean getDuringMeasureLayout() {
        return this.duringMeasureLayout;
    }

    public final void setDuringMeasureLayout$ui(boolean z) {
        this.duringMeasureLayout = z;
    }

    public final long getMeasureIteration() {
        boolean value$iv = this.duringMeasureLayout;
        if (!value$iv) {
            InlineClassHelperKt.throwIllegalArgumentException("measureIteration should be only used during the measure/layout pass");
        }
        return this.measureIteration;
    }

    /* JADX INFO: renamed from: getUncaughtExceptionHandler$ui, reason: from getter */
    public final RootForTest.UncaughtExceptionHandler getUncaughtExceptionHandler() {
        return this.uncaughtExceptionHandler;
    }

    public final void setUncaughtExceptionHandler$ui(RootForTest.UncaughtExceptionHandler uncaughtExceptionHandler) {
        this.uncaughtExceptionHandler = uncaughtExceptionHandler;
    }

    /* JADX INFO: renamed from: updateRootConstraints-BRTryo0, reason: not valid java name */
    public final void m7048updateRootConstraintsBRTryo0(long constraints) {
        Invalidation invalidation;
        Constraints constraints2 = this.rootConstraints;
        if (!(constraints2 == null ? false : Constraints.m8096equalsimpl0(constraints2.getValue(), constraints))) {
            boolean value$iv = !this.duringMeasureLayout;
            if (!value$iv) {
                InlineClassHelperKt.throwIllegalArgumentException("updateRootConstraints called while measuring");
            }
            this.rootConstraints = Constraints.m8090boximpl(constraints);
            if (this.root.getLookaheadRoot() != null) {
                this.root.markLookaheadMeasurePending$ui();
            }
            this.root.markMeasurePending$ui();
            DepthSortedSetsForDifferentPasses depthSortedSetsForDifferentPasses = this.relayoutNodes;
            LayoutNode layoutNode = this.root;
            if (this.root.getLookaheadRoot() != null) {
                invalidation = Invalidation.LookaheadMeasurement;
            } else {
                invalidation = Invalidation.Measurement;
            }
            depthSortedSetsForDifferentPasses.add(layoutNode, invalidation);
        }
    }

    public static /* synthetic */ boolean requestLookaheadRemeasure$default(MeasureAndLayoutDelegate measureAndLayoutDelegate, LayoutNode layoutNode, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return measureAndLayoutDelegate.requestLookaheadRemeasure(layoutNode, z);
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x0074  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean requestLookaheadRemeasure(androidx.compose.ui.node.LayoutNode r6, boolean r7) {
        /*
            r5 = this;
            androidx.compose.ui.node.LayoutNode r0 = r6.getLookaheadRoot()
            r1 = 1
            r2 = 0
            if (r0 == 0) goto La
            r0 = r1
            goto Lb
        La:
            r0 = r2
        Lb:
            r3 = 0
            if (r0 != 0) goto L16
            r4 = 0
            java.lang.String r4 = "Error: requestLookaheadRemeasure cannot be called on a node outside LookaheadScope"
            androidx.compose.ui.internal.InlineClassHelperKt.throwIllegalStateException(r4)
        L16:
            androidx.compose.ui.node.LayoutNode$LayoutState r0 = r6.getLayoutState$ui()
            int[] r3 = androidx.compose.ui.node.MeasureAndLayoutDelegate.WhenMappings.$EnumSwitchMapping$0
            int r0 = r0.ordinal()
            r0 = r3[r0]
            switch(r0) {
                case 1: goto Lb2;
                case 2: goto L9f;
                case 3: goto L9f;
                case 4: goto L9f;
                case 5: goto L2c;
                default: goto L26;
            }
        L26:
            kotlin.NoWhenBranchMatchedException r0 = new kotlin.NoWhenBranchMatchedException
            r0.<init>()
            throw r0
        L2c:
            boolean r0 = r6.getLookaheadMeasurePending$ui()
            if (r0 == 0) goto L37
            if (r7 != 0) goto L37
            r1 = r2
            goto Lb3
        L37:
            r6.markLookaheadMeasurePending$ui()
            r6.markMeasurePending$ui()
            boolean r0 = r6.getIsDeactivated()
            if (r0 == 0) goto L46
            r1 = r2
            goto Lb3
        L46:
            java.lang.Boolean r0 = r6.isPlacedInLookahead()
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r1)
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r3)
            if (r0 != 0) goto L5b
            boolean r0 = r5.getCanAffectParentInLookahead(r6)
            if (r0 == 0) goto L74
        L5b:
            androidx.compose.ui.node.LayoutNode r0 = r6.getParent$ui()
            if (r0 == 0) goto L69
            boolean r0 = r0.getLookaheadMeasurePending$ui()
            if (r0 != r1) goto L69
            r0 = r1
            goto L6a
        L69:
            r0 = r2
        L6a:
            if (r0 != 0) goto L74
            androidx.compose.ui.node.DepthSortedSetsForDifferentPasses r0 = r5.relayoutNodes
            androidx.compose.ui.node.Invalidation r3 = androidx.compose.ui.node.Invalidation.LookaheadMeasurement
            r0.add(r6, r3)
            goto L98
        L74:
            boolean r0 = r6.isPlaced()
            if (r0 != 0) goto L80
            boolean r0 = r5.getCanAffectPlacedParent(r6)
            if (r0 == 0) goto L98
        L80:
            androidx.compose.ui.node.LayoutNode r0 = r6.getParent$ui()
            if (r0 == 0) goto L8e
            boolean r0 = r0.getMeasurePending$ui()
            if (r0 != r1) goto L8e
            r0 = r1
            goto L8f
        L8e:
            r0 = r2
        L8f:
            if (r0 != 0) goto L98
            androidx.compose.ui.node.DepthSortedSetsForDifferentPasses r0 = r5.relayoutNodes
            androidx.compose.ui.node.Invalidation r3 = androidx.compose.ui.node.Invalidation.Measurement
            r0.add(r6, r3)
        L98:
            boolean r0 = r5.duringFullMeasureLayoutPass
            if (r0 != 0) goto L9d
            goto Lb3
        L9d:
            r1 = r2
            goto Lb3
        L9f:
            androidx.compose.runtime.collection.MutableVector<androidx.compose.ui.node.MeasureAndLayoutDelegate$PostponedRequest> r0 = r5.postponedMeasureRequests
            androidx.compose.ui.node.MeasureAndLayoutDelegate$PostponedRequest r3 = new androidx.compose.ui.node.MeasureAndLayoutDelegate$PostponedRequest
            r3.<init>(r6, r1, r7)
            r0.add(r3)
            androidx.compose.ui.node.LayoutTreeConsistencyChecker r0 = r5.consistencyChecker
            if (r0 == 0) goto Lb0
            r0.assertConsistent()
        Lb0:
            r1 = r2
            goto Lb3
        Lb2:
            r1 = r2
        Lb3:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.node.MeasureAndLayoutDelegate.requestLookaheadRemeasure(androidx.compose.ui.node.LayoutNode, boolean):boolean");
    }

    public static /* synthetic */ boolean requestRemeasure$default(MeasureAndLayoutDelegate measureAndLayoutDelegate, LayoutNode layoutNode, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return measureAndLayoutDelegate.requestRemeasure(layoutNode, z);
    }

    public final boolean requestRemeasure(LayoutNode layoutNode, boolean forced) {
        switch (WhenMappings.$EnumSwitchMapping$0[layoutNode.getLayoutState$ui().ordinal()]) {
            case 1:
            case 2:
                return false;
            case 3:
            case 4:
                this.postponedMeasureRequests.add(new PostponedRequest(layoutNode, false, forced));
                LayoutTreeConsistencyChecker layoutTreeConsistencyChecker = this.consistencyChecker;
                if (layoutTreeConsistencyChecker == null) {
                    return false;
                }
                layoutTreeConsistencyChecker.assertConsistent();
                return false;
            case 5:
                if (layoutNode.getMeasurePending$ui() && !forced) {
                    return false;
                }
                layoutNode.markMeasurePending$ui();
                if (layoutNode.getIsDeactivated()) {
                    return false;
                }
                if (!layoutNode.isPlaced() && !getCanAffectPlacedParent(layoutNode)) {
                    return false;
                }
                LayoutNode parent$ui = layoutNode.getParent$ui();
                if (!(parent$ui != null && parent$ui.getMeasurePending$ui())) {
                    this.relayoutNodes.add(layoutNode, Invalidation.Measurement);
                }
                return !this.duringFullMeasureLayoutPass;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    public static /* synthetic */ boolean requestLookaheadRelayout$default(MeasureAndLayoutDelegate measureAndLayoutDelegate, LayoutNode layoutNode, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return measureAndLayoutDelegate.requestLookaheadRelayout(layoutNode, z);
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x0072  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean requestLookaheadRelayout(androidx.compose.ui.node.LayoutNode r6, boolean r7) {
        /*
            r5 = this;
            androidx.compose.ui.node.LayoutNode$LayoutState r0 = r6.getLayoutState$ui()
            int[] r1 = androidx.compose.ui.node.MeasureAndLayoutDelegate.WhenMappings.$EnumSwitchMapping$0
            int r0 = r0.ordinal()
            r0 = r1[r0]
            r1 = 0
            switch(r0) {
                case 1: goto La0;
                case 2: goto L16;
                case 3: goto La0;
                case 4: goto L16;
                case 5: goto L16;
                default: goto L10;
            }
        L10:
            kotlin.NoWhenBranchMatchedException r0 = new kotlin.NoWhenBranchMatchedException
            r0.<init>()
            throw r0
        L16:
            boolean r0 = r6.getLookaheadMeasurePending$ui()
            if (r0 != 0) goto L23
            boolean r0 = r6.getLookaheadLayoutPending$ui()
            if (r0 == 0) goto L2e
        L23:
            if (r7 != 0) goto L2e
            androidx.compose.ui.node.LayoutTreeConsistencyChecker r0 = r5.consistencyChecker
            if (r0 == 0) goto L2c
            r0.assertConsistent()
        L2c:
            goto La8
        L2e:
            r6.markLookaheadLayoutPending$ui()
            r6.markLayoutPending$ui()
            boolean r0 = r6.getIsDeactivated()
            if (r0 == 0) goto L3c
            goto La8
        L3c:
            androidx.compose.ui.node.LayoutNode r0 = r6.getParent$ui()
            java.lang.Boolean r2 = r6.isPlacedInLookahead()
            r3 = 1
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r3)
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual(r2, r4)
            if (r2 == 0) goto L72
            if (r0 == 0) goto L5a
            boolean r2 = r0.getLookaheadMeasurePending$ui()
            if (r2 != r3) goto L5a
            r2 = r3
            goto L5b
        L5a:
            r2 = r1
        L5b:
            if (r2 != 0) goto L72
            if (r0 == 0) goto L67
            boolean r2 = r0.getLookaheadLayoutPending$ui()
            if (r2 != r3) goto L67
            r2 = r3
            goto L68
        L67:
            r2 = r1
        L68:
            if (r2 != 0) goto L72
            androidx.compose.ui.node.DepthSortedSetsForDifferentPasses r2 = r5.relayoutNodes
            androidx.compose.ui.node.Invalidation r4 = androidx.compose.ui.node.Invalidation.LookaheadPlacement
            r2.add(r6, r4)
            goto L99
        L72:
            boolean r2 = r6.isPlaced()
            if (r2 == 0) goto L99
            if (r0 == 0) goto L82
            boolean r2 = r0.getLayoutPending$ui()
            if (r2 != r3) goto L82
            r2 = r3
            goto L83
        L82:
            r2 = r1
        L83:
            if (r2 != 0) goto L99
            if (r0 == 0) goto L8f
            boolean r2 = r0.getMeasurePending$ui()
            if (r2 != r3) goto L8f
            r2 = r3
            goto L90
        L8f:
            r2 = r1
        L90:
            if (r2 != 0) goto L99
            androidx.compose.ui.node.DepthSortedSetsForDifferentPasses r2 = r5.relayoutNodes
            androidx.compose.ui.node.Invalidation r4 = androidx.compose.ui.node.Invalidation.Placement
            r2.add(r6, r4)
        L99:
            boolean r2 = r5.duringFullMeasureLayoutPass
            if (r2 != 0) goto L9f
            r1 = r3
            goto La8
        L9f:
            goto La8
        La0:
            androidx.compose.ui.node.LayoutTreeConsistencyChecker r0 = r5.consistencyChecker
            if (r0 == 0) goto La7
            r0.assertConsistent()
        La7:
        La8:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.node.MeasureAndLayoutDelegate.requestLookaheadRelayout(androidx.compose.ui.node.LayoutNode, boolean):boolean");
    }

    public static /* synthetic */ boolean requestRelayout$default(MeasureAndLayoutDelegate measureAndLayoutDelegate, LayoutNode layoutNode, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return measureAndLayoutDelegate.requestRelayout(layoutNode, z);
    }

    public final boolean requestRelayout(LayoutNode layoutNode, boolean forced) {
        switch (WhenMappings.$EnumSwitchMapping$0[layoutNode.getLayoutState$ui().ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
                LayoutTreeConsistencyChecker layoutTreeConsistencyChecker = this.consistencyChecker;
                if (layoutTreeConsistencyChecker == null) {
                    return false;
                }
                layoutTreeConsistencyChecker.assertConsistent();
                return false;
            case 5:
                LayoutNode parent = layoutNode.getParent$ui();
                boolean parentIsPlaced = parent == null || parent.isPlaced();
                if (!forced && (layoutNode.getMeasurePending$ui() || (layoutNode.getLayoutPending$ui() && layoutNode.isPlaced() == parentIsPlaced && layoutNode.isPlaced() == layoutNode.isPlacedByParent()))) {
                    LayoutTreeConsistencyChecker layoutTreeConsistencyChecker2 = this.consistencyChecker;
                    if (layoutTreeConsistencyChecker2 == null) {
                        return false;
                    }
                    layoutTreeConsistencyChecker2.assertConsistent();
                    return false;
                }
                layoutNode.markLayoutPending$ui();
                if (layoutNode.getIsDeactivated() || !layoutNode.isPlacedByParent() || !parentIsPlaced) {
                    return false;
                }
                if (!(parent != null && parent.getLayoutPending$ui())) {
                    if (!(parent != null && parent.getMeasurePending$ui())) {
                        this.relayoutNodes.add(layoutNode, Invalidation.Placement);
                    }
                }
                return !this.duringFullMeasureLayoutPass;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    public final void requestOnPositionedCallback(LayoutNode layoutNode) {
        this.onPositionedDispatcher.onNodePositioned(layoutNode);
    }

    /* JADX INFO: renamed from: doLookaheadRemeasure-sdFAvZA, reason: not valid java name */
    private final boolean m7045doLookaheadRemeasuresdFAvZA(LayoutNode layoutNode, Constraints constraints) {
        boolean lookaheadSizeChanged;
        if (layoutNode.getLookaheadRoot() == null) {
            return false;
        }
        if (constraints != null) {
            lookaheadSizeChanged = layoutNode.m7012lookaheadRemeasure_Sx5XlM$ui(constraints);
        } else {
            lookaheadSizeChanged = LayoutNode.m7007lookaheadRemeasure_Sx5XlM$ui$default(layoutNode, null, 1, null);
        }
        LayoutNode parent = layoutNode.getParent$ui();
        if (lookaheadSizeChanged && parent != null) {
            if (parent.getLookaheadRoot() == null) {
                LayoutNode.requestRemeasure$ui$default(parent, false, false, false, 3, null);
            } else if (layoutNode.getMeasuredByParentInLookahead$ui() == LayoutNode.UsageByParent.InMeasureBlock) {
                LayoutNode.requestLookaheadRemeasure$ui$default(parent, false, false, false, 3, null);
            } else if (layoutNode.getMeasuredByParentInLookahead$ui() == LayoutNode.UsageByParent.InLayoutBlock) {
                LayoutNode.requestLookaheadRelayout$ui$default(parent, false, 1, null);
            }
        }
        return lookaheadSizeChanged;
    }

    /* JADX INFO: renamed from: doRemeasure-sdFAvZA, reason: not valid java name */
    private final boolean m7046doRemeasuresdFAvZA(LayoutNode layoutNode, Constraints constraints) {
        boolean sizeChanged;
        if (constraints != null) {
            sizeChanged = layoutNode.m7013remeasure_Sx5XlM$ui(constraints);
        } else {
            sizeChanged = LayoutNode.m7008remeasure_Sx5XlM$ui$default(layoutNode, null, 1, null);
        }
        LayoutNode parent = layoutNode.getParent$ui();
        if (sizeChanged && parent != null) {
            if (layoutNode.getMeasuredByParent$ui() == LayoutNode.UsageByParent.InMeasureBlock) {
                LayoutNode.requestRemeasure$ui$default(parent, false, false, false, 3, null);
            } else if (layoutNode.getMeasuredByParent$ui() == LayoutNode.UsageByParent.InLayoutBlock) {
                LayoutNode.requestRelayout$ui$default(parent, false, 1, null);
            }
        }
        return sizeChanged;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ boolean measureAndLayout$default(MeasureAndLayoutDelegate measureAndLayoutDelegate, Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            function0 = null;
        }
        return measureAndLayoutDelegate.measureAndLayout(function0);
    }

    public final boolean measureAndLayout(Function0<Unit> onLayout) {
        LayoutNode node$iv;
        boolean relayoutNeeded$iv;
        boolean affectsLookahead$iv;
        boolean sizeChanged;
        boolean rootNodeResized = false;
        boolean value$iv$iv = this.root.isAttached();
        if (!value$iv$iv) {
            InlineClassHelperKt.throwIllegalArgumentException("performMeasureAndLayout called with unattached root");
        }
        boolean value$iv$iv2 = this.root.isPlaced();
        if (!value$iv$iv2) {
            InlineClassHelperKt.throwIllegalArgumentException("performMeasureAndLayout called with unplaced root");
        }
        boolean value$iv$iv3 = this.duringMeasureLayout;
        boolean z = true;
        if (!(!value$iv$iv3)) {
            InlineClassHelperKt.throwIllegalArgumentException("performMeasureAndLayout called during measure layout");
        }
        if (this.rootConstraints != null) {
            this.duringMeasureLayout = true;
            this.duringFullMeasureLayoutPass = true;
            try {
                if (this.relayoutNodes.isNotEmpty()) {
                    DepthSortedSetsForDifferentPasses this_$iv = this.relayoutNodes;
                    while (true) {
                        DepthSortedSet this_$iv$iv = this_$iv.lookaheadAndAncestorMeasureSet;
                        if (this_$iv$iv.isEmpty()) {
                            DepthSortedSet this_$iv$iv2 = this_$iv.lookaheadAndAncestorPlaceSet;
                            if (this_$iv$iv2.isEmpty()) {
                                DepthSortedSet this_$iv$iv3 = this_$iv.approachSet;
                                if (this_$iv$iv3.isEmpty()) {
                                    break;
                                }
                                node$iv = this_$iv.approachSet.pop();
                                relayoutNeeded$iv = true;
                                affectsLookahead$iv = false;
                            } else {
                                relayoutNeeded$iv = true;
                                node$iv = this_$iv.lookaheadAndAncestorPlaceSet.pop();
                                affectsLookahead$iv = node$iv.getLookaheadRoot() != null ? z : false;
                            }
                        } else {
                            relayoutNeeded$iv = false;
                            node$iv = this_$iv.lookaheadAndAncestorMeasureSet.pop();
                            affectsLookahead$iv = node$iv.getLookaheadRoot() != null ? z : false;
                        }
                        LayoutNode layoutNode = node$iv;
                        boolean relayoutNeeded = relayoutNeeded$iv;
                        boolean affectsLookahead = affectsLookahead$iv;
                        if (relayoutNeeded) {
                            sizeChanged = remeasureAndRelayoutIfNeeded(layoutNode, affectsLookahead);
                        } else {
                            sizeChanged = remeasureIfNeeded(layoutNode, affectsLookahead, z);
                            if (layoutNode.getLookaheadLayoutPending$ui()) {
                                this.relayoutNodes.add(layoutNode, Invalidation.LookaheadPlacement);
                            }
                            if (layoutNode.getLayoutPending$ui()) {
                                this.relayoutNodes.add(layoutNode, Invalidation.Placement);
                            }
                        }
                        if (layoutNode == this.root && sizeChanged) {
                            rootNodeResized = true;
                        }
                        z = true;
                    }
                    if (onLayout != null) {
                        onLayout.invoke();
                    }
                }
            } catch (Throwable t$iv) {
                try {
                    RootForTest.UncaughtExceptionHandler uncaughtExceptionHandler = this.uncaughtExceptionHandler;
                    if (uncaughtExceptionHandler == null) {
                        throw t$iv;
                    }
                    uncaughtExceptionHandler.onUncaughtException(t$iv);
                } finally {
                    boolean z2 = false;
                    this.duringMeasureLayout = z2;
                    this.duringFullMeasureLayoutPass = z2;
                }
            }
            LayoutTreeConsistencyChecker layoutTreeConsistencyChecker = this.consistencyChecker;
            if (layoutTreeConsistencyChecker != null) {
                layoutTreeConsistencyChecker.assertConsistent();
            }
        }
        callOnLayoutCompletedListeners();
        return rootNodeResized;
    }

    public final void measureOnly() {
        if (this.relayoutNodes.isNotEmpty()) {
            boolean value$iv$iv = this.root.isAttached();
            if (!value$iv$iv) {
                InlineClassHelperKt.throwIllegalArgumentException("performMeasureAndLayout called with unattached root");
            }
            boolean value$iv$iv2 = this.root.isPlaced();
            if (!value$iv$iv2) {
                InlineClassHelperKt.throwIllegalArgumentException("performMeasureAndLayout called with unplaced root");
            }
            boolean value$iv$iv3 = this.duringMeasureLayout;
            if (!(!value$iv$iv3)) {
                InlineClassHelperKt.throwIllegalArgumentException("performMeasureAndLayout called during measure layout");
            }
            if (this.rootConstraints != null) {
                this.duringMeasureLayout = true;
                this.duringFullMeasureLayoutPass = false;
                try {
                    if (ComposeToolingFlags.isVerboseTracingEnabled) {
                        Trace.beginSection("Compose:lookaheadRemeasure");
                        try {
                            if (this.relayoutNodes.getAffectsLookaheadMeasure()) {
                                LayoutNode lookaheadRoot = this.root.getLookaheadRoot();
                                LayoutNode layoutNode = this.root;
                                if (lookaheadRoot != null) {
                                    remeasureOnly(layoutNode, true);
                                } else {
                                    remeasureLookaheadRootsInSubtree(layoutNode);
                                }
                            }
                            Unit unit = Unit.INSTANCE;
                            Trace.endSection();
                        } finally {
                        }
                    } else if (this.relayoutNodes.getAffectsLookaheadMeasure()) {
                        LayoutNode lookaheadRoot2 = this.root.getLookaheadRoot();
                        LayoutNode layoutNode2 = this.root;
                        if (lookaheadRoot2 != null) {
                            remeasureOnly(layoutNode2, true);
                        } else {
                            remeasureLookaheadRootsInSubtree(layoutNode2);
                        }
                    }
                    if (ComposeToolingFlags.isVerboseTracingEnabled) {
                        Trace.beginSection("Compose:remeasure");
                        try {
                            remeasureOnly(this.root, false);
                            Unit unit2 = Unit.INSTANCE;
                            Trace.endSection();
                        } finally {
                        }
                    } else {
                        remeasureOnly(this.root, false);
                    }
                } catch (Throwable t$iv) {
                    try {
                        RootForTest.UncaughtExceptionHandler uncaughtExceptionHandler = this.uncaughtExceptionHandler;
                        if (uncaughtExceptionHandler == null) {
                            throw t$iv;
                        }
                        uncaughtExceptionHandler.onUncaughtException(t$iv);
                    } catch (Throwable t$iv2) {
                        this.duringMeasureLayout = false;
                        this.duringFullMeasureLayoutPass = false;
                        throw t$iv2;
                    }
                }
                this.duringMeasureLayout = false;
                this.duringFullMeasureLayoutPass = false;
                LayoutTreeConsistencyChecker layoutTreeConsistencyChecker = this.consistencyChecker;
                if (layoutTreeConsistencyChecker != null) {
                    layoutTreeConsistencyChecker.assertConsistent();
                }
            }
        }
    }

    private final void remeasureLookaheadRootsInSubtree(LayoutNode layoutNode) {
        MutableVector<LayoutNode> mutableVector = layoutNode.get_children$ui();
        Object[] content$iv$iv = mutableVector.content;
        int size$iv$iv = mutableVector.getSize();
        for (int i$iv$iv = 0; i$iv$iv < size$iv$iv; i$iv$iv++) {
            LayoutNode it = (LayoutNode) content$iv$iv[i$iv$iv];
            if (getRemeasureCanAffectParentSize(it)) {
                if (LayoutNodeLayoutDelegateKt.isOutMostLookaheadRoot(it)) {
                    remeasureOnly(it, true);
                } else {
                    remeasureLookaheadRootsInSubtree(it);
                }
            }
        }
    }

    /* JADX INFO: renamed from: measureAndLayout-0kLqBqw, reason: not valid java name */
    public final void m7047measureAndLayout0kLqBqw(LayoutNode layoutNode, long constraints) {
        if (layoutNode.getIsDeactivated()) {
            return;
        }
        boolean value$iv = !Intrinsics.areEqual(layoutNode, this.root);
        if (!value$iv) {
            InlineClassHelperKt.throwIllegalArgumentException("measureAndLayout called on root");
        }
        boolean value$iv$iv = this.root.isAttached();
        if (!value$iv$iv) {
            InlineClassHelperKt.throwIllegalArgumentException("performMeasureAndLayout called with unattached root");
        }
        boolean value$iv$iv2 = this.root.isPlaced();
        if (!value$iv$iv2) {
            InlineClassHelperKt.throwIllegalArgumentException("performMeasureAndLayout called with unplaced root");
        }
        boolean value$iv$iv3 = this.duringMeasureLayout;
        if (!(!value$iv$iv3)) {
            InlineClassHelperKt.throwIllegalArgumentException("performMeasureAndLayout called during measure layout");
        }
        if (this.rootConstraints != null) {
            this.duringMeasureLayout = true;
            this.duringFullMeasureLayoutPass = false;
            try {
                this.relayoutNodes.remove(layoutNode);
                boolean lookaheadSizeChanged = m7045doLookaheadRemeasuresdFAvZA(layoutNode, Constraints.m8090boximpl(constraints));
                if ((lookaheadSizeChanged || layoutNode.getLookaheadLayoutPending$ui()) && Intrinsics.areEqual((Object) layoutNode.isPlacedInLookahead(), (Object) true)) {
                    layoutNode.lookaheadReplace$ui();
                }
                ensureSubtreeLookaheadReplaced(layoutNode);
                m7046doRemeasuresdFAvZA(layoutNode, Constraints.m8090boximpl(constraints));
                if (layoutNode.getLayoutPending$ui() && layoutNode.isPlaced()) {
                    layoutNode.replace$ui();
                    this.onPositionedDispatcher.onNodePositioned(layoutNode);
                }
                drainPostponedMeasureRequests();
            } catch (Throwable t$iv) {
                try {
                    RootForTest.UncaughtExceptionHandler uncaughtExceptionHandler = this.uncaughtExceptionHandler;
                    if (uncaughtExceptionHandler == null) {
                        throw t$iv;
                    }
                    uncaughtExceptionHandler.onUncaughtException(t$iv);
                } catch (Throwable t$iv2) {
                    this.duringMeasureLayout = false;
                    this.duringFullMeasureLayoutPass = false;
                    throw t$iv2;
                }
            }
            this.duringMeasureLayout = false;
            this.duringFullMeasureLayoutPass = false;
            LayoutTreeConsistencyChecker layoutTreeConsistencyChecker = this.consistencyChecker;
            if (layoutTreeConsistencyChecker != null) {
                layoutTreeConsistencyChecker.assertConsistent();
            }
        }
        callOnLayoutCompletedListeners();
    }

    private final void ensureSubtreeLookaheadReplaced(LayoutNode layoutNode) {
        MutableVector<LayoutNode> mutableVector = layoutNode.get_children$ui();
        Object[] content$iv$iv = mutableVector.content;
        int size$iv$iv = mutableVector.getSize();
        for (int i$iv$iv = 0; i$iv$iv < size$iv$iv; i$iv$iv++) {
            LayoutNode it = (LayoutNode) content$iv$iv[i$iv$iv];
            if (Intrinsics.areEqual((Object) it.isPlacedInLookahead(), (Object) true) && !it.getIsDeactivated()) {
                if (this.relayoutNodes.contains(it, true)) {
                    it.lookaheadReplace$ui();
                }
                ensureSubtreeLookaheadReplaced(it);
            }
        }
    }

    private final void performMeasureAndLayout(boolean fullPass, Function0<Unit> block) {
        boolean value$iv = this.root.isAttached();
        if (!value$iv) {
            InlineClassHelperKt.throwIllegalArgumentException("performMeasureAndLayout called with unattached root");
        }
        boolean value$iv2 = this.root.isPlaced();
        if (!value$iv2) {
            InlineClassHelperKt.throwIllegalArgumentException("performMeasureAndLayout called with unplaced root");
        }
        boolean value$iv3 = this.duringMeasureLayout;
        if (!(!value$iv3)) {
            InlineClassHelperKt.throwIllegalArgumentException("performMeasureAndLayout called during measure layout");
        }
        if (this.rootConstraints != null) {
            this.duringMeasureLayout = true;
            this.duringFullMeasureLayoutPass = fullPass;
            try {
                block.invoke();
            } catch (Throwable t) {
                try {
                    RootForTest.UncaughtExceptionHandler uncaughtExceptionHandler = this.uncaughtExceptionHandler;
                    if (uncaughtExceptionHandler == null) {
                        throw t;
                    }
                    uncaughtExceptionHandler.onUncaughtException(t);
                } finally {
                    this.duringMeasureLayout = false;
                    this.duringFullMeasureLayoutPass = false;
                }
            }
            LayoutTreeConsistencyChecker layoutTreeConsistencyChecker = this.consistencyChecker;
            if (layoutTreeConsistencyChecker != null) {
                layoutTreeConsistencyChecker.assertConsistent();
            }
        }
    }

    public final void registerOnLayoutCompletedListener(Owner.OnLayoutCompletedListener listener) {
        this.onLayoutCompletedListeners.add(listener);
    }

    private final void callOnLayoutCompletedListeners() {
        MutableVector<Owner.OnLayoutCompletedListener> mutableVector = this.onLayoutCompletedListeners;
        Object[] content$iv = mutableVector.content;
        int size$iv = mutableVector.getSize();
        for (int i$iv = 0; i$iv < size$iv; i$iv++) {
            Owner.OnLayoutCompletedListener it = (Owner.OnLayoutCompletedListener) content$iv[i$iv];
            it.onLayoutComplete();
        }
        this.onLayoutCompletedListeners.clear();
    }

    static /* synthetic */ boolean remeasureAndRelayoutIfNeeded$default(MeasureAndLayoutDelegate measureAndLayoutDelegate, LayoutNode layoutNode, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        return measureAndLayoutDelegate.remeasureAndRelayoutIfNeeded(layoutNode, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean remeasureAndRelayoutIfNeeded(LayoutNode layoutNode, boolean affectsLookahead) {
        Constraints constraints;
        boolean zM7046doRemeasuresdFAvZA;
        boolean zM7045doLookaheadRemeasuresdFAvZA;
        boolean sizeChanged = false;
        if (layoutNode.getIsDeactivated()) {
            return false;
        }
        if (isUsedInMeasureOrLayout(layoutNode)) {
            if (layoutNode == this.root) {
                constraints = this.rootConstraints;
                Intrinsics.checkNotNull(constraints);
            } else {
                constraints = null;
            }
            boolean isPlacedByPlacedParent = true;
            if (affectsLookahead) {
                if (layoutNode.getLookaheadMeasurePending$ui()) {
                    if (!ComposeToolingFlags.isVerboseTracingEnabled) {
                        zM7045doLookaheadRemeasuresdFAvZA = m7045doLookaheadRemeasuresdFAvZA(layoutNode, constraints);
                    } else {
                        Trace.beginSection("Compose:lookaheadMeasure");
                        try {
                            zM7045doLookaheadRemeasuresdFAvZA = m7045doLookaheadRemeasuresdFAvZA(layoutNode, constraints);
                            Trace.endSection();
                        } finally {
                        }
                    }
                    sizeChanged = zM7045doLookaheadRemeasuresdFAvZA;
                }
                if ((sizeChanged || layoutNode.getLookaheadLayoutPending$ui()) && Intrinsics.areEqual((Object) layoutNode.isPlacedInLookahead(), (Object) true)) {
                    if (!ComposeToolingFlags.isVerboseTracingEnabled) {
                        layoutNode.lookaheadReplace$ui();
                    } else {
                        Trace.beginSection("Compose:lookaheadLayout");
                        try {
                            layoutNode.lookaheadReplace$ui();
                            Unit unit = Unit.INSTANCE;
                        } finally {
                        }
                    }
                }
            } else {
                if (layoutNode.getMeasurePending$ui()) {
                    if (!ComposeToolingFlags.isVerboseTracingEnabled) {
                        zM7046doRemeasuresdFAvZA = m7046doRemeasuresdFAvZA(layoutNode, constraints);
                    } else {
                        Trace.beginSection("Compose:measure");
                        try {
                            zM7046doRemeasuresdFAvZA = m7046doRemeasuresdFAvZA(layoutNode, constraints);
                            Trace.endSection();
                        } finally {
                        }
                    }
                    sizeChanged = zM7046doRemeasuresdFAvZA;
                }
                if (layoutNode.getLayoutPending$ui()) {
                    if (layoutNode != this.root) {
                        LayoutNode parent$ui = layoutNode.getParent$ui();
                        if (!(parent$ui != null && parent$ui.isPlaced()) || !layoutNode.isPlacedByParent()) {
                            isPlacedByPlacedParent = false;
                        }
                    }
                    if (isPlacedByPlacedParent) {
                        if (!ComposeToolingFlags.isVerboseTracingEnabled) {
                            if (layoutNode == this.root) {
                                layoutNode.place$ui(0, 0);
                            } else {
                                layoutNode.replace$ui();
                            }
                        } else {
                            Trace.beginSection("Compose:layout");
                            try {
                                if (layoutNode == this.root) {
                                    layoutNode.place$ui(0, 0);
                                } else {
                                    layoutNode.replace$ui();
                                }
                                Unit unit2 = Unit.INSTANCE;
                                Trace.endSection();
                            } finally {
                            }
                        }
                        this.onPositionedDispatcher.onNodePositioned(layoutNode);
                        LayoutTreeConsistencyChecker layoutTreeConsistencyChecker = this.consistencyChecker;
                        if (layoutTreeConsistencyChecker != null) {
                            layoutTreeConsistencyChecker.assertConsistent();
                        }
                    }
                }
            }
            drainPostponedMeasureRequests();
        }
        return sizeChanged;
    }

    static /* synthetic */ boolean remeasureIfNeeded$default(MeasureAndLayoutDelegate measureAndLayoutDelegate, LayoutNode layoutNode, boolean z, boolean z2, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        if ((i & 4) != 0) {
            z2 = false;
        }
        return measureAndLayoutDelegate.remeasureIfNeeded(layoutNode, z, z2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean remeasureIfNeeded(LayoutNode layoutNode, boolean affectsLookahead, boolean shouldTraceMeasure) {
        Constraints constraints;
        boolean zM7046doRemeasuresdFAvZA;
        boolean zM7045doLookaheadRemeasuresdFAvZA;
        boolean sizeChanged = false;
        if (layoutNode.getIsDeactivated()) {
            return false;
        }
        if (isUsedInMeasureOrLayout(layoutNode)) {
            if (layoutNode == this.root) {
                constraints = this.rootConstraints;
                Intrinsics.checkNotNull(constraints);
            } else {
                constraints = null;
            }
            if (affectsLookahead) {
                if (layoutNode.getLookaheadMeasurePending$ui()) {
                    if (!ComposeToolingFlags.isVerboseTracingEnabled || !shouldTraceMeasure) {
                        zM7045doLookaheadRemeasuresdFAvZA = m7045doLookaheadRemeasuresdFAvZA(layoutNode, constraints);
                    } else {
                        Trace.beginSection("Compose:lookaheadMeasure");
                        try {
                            zM7045doLookaheadRemeasuresdFAvZA = m7045doLookaheadRemeasuresdFAvZA(layoutNode, constraints);
                            Trace.endSection();
                        } finally {
                        }
                    }
                    sizeChanged = zM7045doLookaheadRemeasuresdFAvZA;
                }
            } else if (layoutNode.getMeasurePending$ui()) {
                if (!ComposeToolingFlags.isVerboseTracingEnabled || !shouldTraceMeasure) {
                    zM7046doRemeasuresdFAvZA = m7046doRemeasuresdFAvZA(layoutNode, constraints);
                } else {
                    Trace.beginSection("Compose:measure");
                    try {
                        zM7046doRemeasuresdFAvZA = m7046doRemeasuresdFAvZA(layoutNode, constraints);
                    } finally {
                    }
                }
                sizeChanged = zM7046doRemeasuresdFAvZA;
            }
            drainPostponedMeasureRequests();
        }
        return sizeChanged;
    }

    private final boolean isUsedInMeasureOrLayout(LayoutNode $this$isUsedInMeasureOrLayout) {
        return $this$isUsedInMeasureOrLayout.isPlaced() || $this$isUsedInMeasureOrLayout.isPlacedByParent() || getCanAffectPlacedParent($this$isUsedInMeasureOrLayout) || Intrinsics.areEqual((Object) $this$isUsedInMeasureOrLayout.isPlacedInLookahead(), (Object) true) || getCanAffectParentInLookahead($this$isUsedInMeasureOrLayout) || $this$isUsedInMeasureOrLayout.getAlignmentLinesRequired$ui();
    }

    private final void drainPostponedMeasureRequests() {
        if (this.postponedMeasureRequests.getSize() != 0) {
            MutableVector<PostponedRequest> mutableVector = this.postponedMeasureRequests;
            Object[] content$iv = mutableVector.content;
            int size$iv = mutableVector.getSize();
            for (int i$iv = 0; i$iv < size$iv; i$iv++) {
                PostponedRequest request = (PostponedRequest) content$iv[i$iv];
                if (request.getNode().isAttached()) {
                    if (!request.getIsLookahead()) {
                        LayoutNode.requestRemeasure$ui$default(request.getNode(), request.getIsForced(), false, false, 2, null);
                    } else {
                        LayoutNode.requestLookaheadRemeasure$ui$default(request.getNode(), request.getIsForced(), false, false, 2, null);
                    }
                }
            }
            this.postponedMeasureRequests.clear();
        }
    }

    private final void remeasureOnly(LayoutNode layoutNode, boolean affectsLookahead) {
        Constraints constraints;
        if (layoutNode.getIsDeactivated()) {
            return;
        }
        if (layoutNode == this.root) {
            constraints = this.rootConstraints;
            Intrinsics.checkNotNull(constraints);
        } else {
            constraints = null;
        }
        if (affectsLookahead) {
            m7045doLookaheadRemeasuresdFAvZA(layoutNode, constraints);
        } else {
            m7046doRemeasuresdFAvZA(layoutNode, constraints);
        }
    }

    public final void forceMeasureTheSubtree(LayoutNode layoutNode, boolean affectsLookahead) {
        boolean value$iv = this.duringMeasureLayout;
        if (!value$iv) {
            InlineClassHelperKt.throwIllegalStateException("forceMeasureTheSubtree should be executed during the measureAndLayout pass");
        }
        boolean value$iv2 = measurePending(layoutNode, affectsLookahead);
        if (!(!value$iv2)) {
            InlineClassHelperKt.throwIllegalArgumentException("node not yet measured");
        }
        forceMeasureTheSubtreeInternal(layoutNode, affectsLookahead);
    }

    private final void onlyRemeasureIfPending(LayoutNode node, boolean affectsLookahead) {
        if (measurePending(node, affectsLookahead)) {
            remeasureIfNeeded$default(this, node, affectsLookahead, false, 4, null);
        }
    }

    private final void forceMeasureTheSubtreeInternal(LayoutNode layoutNode, boolean affectsLookahead) {
        MutableVector<LayoutNode> mutableVector = layoutNode.get_children$ui();
        Object[] content$iv$iv = mutableVector.content;
        int size$iv$iv = mutableVector.getSize();
        for (int i$iv$iv = 0; i$iv$iv < size$iv$iv; i$iv$iv++) {
            LayoutNode child = (LayoutNode) content$iv$iv[i$iv$iv];
            if ((!affectsLookahead && getRemeasureCanAffectParentSize(child)) || (affectsLookahead && getLookaheadRemeasureCanAffectParentSize(child))) {
                if (LayoutNodeLayoutDelegateKt.isOutMostLookaheadRoot(child) && !affectsLookahead) {
                    if (child.getLookaheadMeasurePending$ui() && this.relayoutNodes.contains(child, true)) {
                        remeasureIfNeeded$default(this, child, true, false, 4, null);
                    } else {
                        forceMeasureTheSubtree(child, true);
                    }
                }
                onlyRemeasureIfPending(child, affectsLookahead);
                if (!measurePending(child, affectsLookahead)) {
                    forceMeasureTheSubtreeInternal(child, affectsLookahead);
                }
            }
        }
        onlyRemeasureIfPending(layoutNode, affectsLookahead);
    }

    public static /* synthetic */ void dispatchOnPositionedCallbacks$default(MeasureAndLayoutDelegate measureAndLayoutDelegate, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        measureAndLayoutDelegate.dispatchOnPositionedCallbacks(z);
    }

    public final void dispatchOnPositionedCallbacks(boolean forceDispatch) {
        if (forceDispatch) {
            this.onPositionedDispatcher.onRootNodePositioned(this.root);
        }
        if (!this.onPositionedDispatcher.isNotEmpty()) {
            return;
        }
        Trace.beginSection("Compose:onPositionedCallbacks");
        try {
            this.onPositionedDispatcher.dispatch();
            Unit unit = Unit.INSTANCE;
        } finally {
            Trace.endSection();
        }
    }

    public final void onNodeDetached(LayoutNode node) {
        this.relayoutNodes.remove(node);
        this.onPositionedDispatcher.remove(node);
    }

    private final boolean getRemeasureCanAffectParentSize(LayoutNode $this$remeasureCanAffectParentSize) {
        return $this$remeasureCanAffectParentSize.getMeasuredByParent$ui() == LayoutNode.UsageByParent.InMeasureBlock || $this$remeasureCanAffectParentSize.getLayoutDelegate().getAlignmentLinesOwner$ui().getAlignmentLines().getRequired$ui();
    }

    private final boolean getMeasuredByPlacedParent(LayoutNode $this$measuredByPlacedParent) {
        LayoutNode node = $this$measuredByPlacedParent;
        while (true) {
            if (node.getMeasuredByParent$ui() == LayoutNode.UsageByParent.NotUsed && !node.getLayoutDelegate().getAlignmentLinesOwner$ui().getAlignmentLines().getRequired$ui()) {
                LayoutNode parent$ui = node.getParent$ui();
                if ((parent$ui != null ? parent$ui.getLayoutState$ui() : null) != LayoutNode.LayoutState.Measuring) {
                    return false;
                }
            }
            LayoutNode parent = node.getParent$ui();
            if (parent == null) {
                return false;
            }
            if (parent.isPlaced()) {
                return true;
            }
            node = parent;
        }
    }

    private final boolean getCanAffectPlacedParent(LayoutNode $this$canAffectPlacedParent) {
        return $this$canAffectPlacedParent.getMeasurePending$ui() && getMeasuredByPlacedParent($this$canAffectPlacedParent);
    }

    private final boolean getCanAffectParentInLookahead(LayoutNode $this$canAffectParentInLookahead) {
        AlignmentLines alignmentLines;
        if (!$this$canAffectParentInLookahead.getLookaheadMeasurePending$ui()) {
            return false;
        }
        if ($this$canAffectParentInLookahead.getMeasuredByParentInLookahead$ui() == LayoutNode.UsageByParent.NotUsed) {
            AlignmentLinesOwner lookaheadAlignmentLinesOwner$ui = $this$canAffectParentInLookahead.getLayoutDelegate().getLookaheadAlignmentLinesOwner$ui();
            if (!((lookaheadAlignmentLinesOwner$ui == null || (alignmentLines = lookaheadAlignmentLinesOwner$ui.getAlignmentLines()) == null || !alignmentLines.getRequired$ui()) ? false : true)) {
                return false;
            }
        }
        return true;
    }

    private final boolean getLookaheadRemeasureCanAffectParentSize(LayoutNode $this$lookaheadRemeasureCanAffectParentSize) {
        AlignmentLines alignmentLines;
        if ($this$lookaheadRemeasureCanAffectParentSize.getMeasuredByParentInLookahead$ui() == LayoutNode.UsageByParent.InMeasureBlock) {
            return true;
        }
        AlignmentLinesOwner lookaheadAlignmentLinesOwner$ui = $this$lookaheadRemeasureCanAffectParentSize.getLayoutDelegate().getLookaheadAlignmentLinesOwner$ui();
        return lookaheadAlignmentLinesOwner$ui != null && (alignmentLines = lookaheadAlignmentLinesOwner$ui.getAlignmentLines()) != null && alignmentLines.getRequired$ui();
    }

    private final boolean measurePending(LayoutNode $this$measurePending, boolean affectsLookahead) {
        return affectsLookahead ? $this$measurePending.getLookaheadMeasurePending$ui() : $this$measurePending.getMeasurePending$ui();
    }

    /* JADX INFO: compiled from: MeasureAndLayoutDelegate.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\b\u0007\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u000b¨\u0006\f"}, d2 = {"Landroidx/compose/ui/node/MeasureAndLayoutDelegate$PostponedRequest;", "", "node", "Landroidx/compose/ui/node/LayoutNode;", "isLookahead", "", "isForced", "<init>", "(Landroidx/compose/ui/node/LayoutNode;ZZ)V", "getNode", "()Landroidx/compose/ui/node/LayoutNode;", "()Z", "ui"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class PostponedRequest {
        public static final int $stable = 8;
        private final boolean isForced;
        private final boolean isLookahead;
        private final LayoutNode node;

        public PostponedRequest(LayoutNode node, boolean isLookahead, boolean isForced) {
            this.node = node;
            this.isLookahead = isLookahead;
            this.isForced = isForced;
        }

        public final LayoutNode getNode() {
            return this.node;
        }

        /* JADX INFO: renamed from: isForced, reason: from getter */
        public final boolean getIsForced() {
            return this.isForced;
        }

        /* JADX INFO: renamed from: isLookahead, reason: from getter */
        public final boolean getIsLookahead() {
            return this.isLookahead;
        }
    }
}
