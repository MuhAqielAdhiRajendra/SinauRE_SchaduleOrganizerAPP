package androidx.compose.animation;

import androidx.compose.animation.SharedTransitionStateMachine;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.geometry.Rect;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: SharedTransitionStateMachine.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0001\u0018\u00002\u00020\u0001B)\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tJ\u0012\u0010\u001b\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J7\u0010\u001e\u001a\u00020\u00012\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001f\u001a\u00020\u00032\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020#H\u0016¢\u0006\u0004\b%\u0010&J\u0012\u0010'\u001a\u00020\u00012\b\u0010(\u001a\u0004\u0018\u00010\u0003H\u0016J\u0010\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020\u0007H\u0016J\b\u0010,\u001a\u00020\u0001H\u0016R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u0016\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\u00118VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R/\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\u0010\u0014\u001a\u0004\u0018\u00010\u00078V@RX\u0096\u008e\u0002¢\u0006\u0012\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018¨\u0006-"}, d2 = {"Landroidx/compose/animation/ActiveMatchFoundConfigPending;", "Landroidx/compose/animation/SharedTransitionStateMachine$State;", "targetBoundsProviderBeforeConfig", "Landroidx/compose/animation/BoundsProvider;", "targetData", "Landroidx/compose/animation/TargetData;", "currentBounds", "Landroidx/compose/ui/geometry/Rect;", "<init>", "(Landroidx/compose/animation/BoundsProvider;Landroidx/compose/animation/TargetData;Landroidx/compose/ui/geometry/Rect;)V", "getTargetBoundsProviderBeforeConfig", "()Landroidx/compose/animation/BoundsProvider;", "setTargetBoundsProviderBeforeConfig", "(Landroidx/compose/animation/BoundsProvider;)V", "getTargetData", "()Landroidx/compose/animation/TargetData;", "activeMatchFound", "", "getActiveMatchFound", "()Z", "<set-?>", "getCurrentBounds", "()Landroidx/compose/ui/geometry/Rect;", "setCurrentBounds", "(Landroidx/compose/ui/geometry/Rect;)V", "currentBounds$delegate", "Landroidx/compose/runtime/MutableState;", "initializeCurrentBounds", "sharedElement", "Landroidx/compose/animation/SharedElement;", "configureActiveMatch", "targetBoundsProvider", "lookaheadSize", "Landroidx/compose/ui/geometry/Size;", "topLeft", "Landroidx/compose/ui/geometry/Offset;", "structuralOffset", "configureActiveMatch-38uP1EE", "(Landroidx/compose/animation/SharedElement;Landroidx/compose/animation/BoundsProvider;JJJ)Landroidx/compose/animation/SharedTransitionStateMachine$State;", "onMatchFound", "previousTargetBoundsProvider", "updateBounds", "", "bounds", "onVisibleContentRemovedDuringTransition", "animation"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class ActiveMatchFoundConfigPending extends SharedTransitionStateMachine.State {
    public static final int $stable = 8;

    /* JADX INFO: renamed from: currentBounds$delegate, reason: from kotlin metadata */
    private final MutableState currentBounds;
    private BoundsProvider targetBoundsProviderBeforeConfig;
    private final TargetData targetData;

    public ActiveMatchFoundConfigPending(BoundsProvider targetBoundsProviderBeforeConfig, TargetData targetData, Rect currentBounds) {
        super(null);
        this.targetBoundsProviderBeforeConfig = targetBoundsProviderBeforeConfig;
        this.targetData = targetData;
        this.currentBounds = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(currentBounds, null, 2, null);
    }

    public /* synthetic */ ActiveMatchFoundConfigPending(BoundsProvider boundsProvider, TargetData targetData, Rect rect, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(boundsProvider, (i & 2) != 0 ? null : targetData, (i & 4) != 0 ? null : rect);
    }

    public final BoundsProvider getTargetBoundsProviderBeforeConfig() {
        return this.targetBoundsProviderBeforeConfig;
    }

    public final void setTargetBoundsProviderBeforeConfig(BoundsProvider boundsProvider) {
        this.targetBoundsProviderBeforeConfig = boundsProvider;
    }

    @Override // androidx.compose.animation.SharedTransitionStateMachine.State
    public TargetData getTargetData() {
        return this.targetData;
    }

    @Override // androidx.compose.animation.SharedTransitionStateMachine.State
    public boolean getActiveMatchFound() {
        return true;
    }

    private void setCurrentBounds(Rect rect) {
        MutableState $this$setValue$iv = this.currentBounds;
        $this$setValue$iv.setValue(rect);
    }

    @Override // androidx.compose.animation.SharedTransitionStateMachine.State
    public Rect getCurrentBounds() {
        State $this$getValue$iv = this.currentBounds;
        return (Rect) $this$getValue$iv.getValue();
    }

    @Override // androidx.compose.animation.SharedTransitionStateMachine.State
    public Rect initializeCurrentBounds(SharedElement sharedElement) {
        Object it$iv;
        Rect bounds = getCurrentBounds();
        if (bounds != null) {
            return bounds;
        }
        if (getCurrentBounds() == null) {
            BoundsProvider lastTarget = this.targetBoundsProviderBeforeConfig;
            if (lastTarget == null) {
                List<SharedElementEntry> allEntries = sharedElement.getAllEntries();
                int index$iv$iv = 0;
                int size = allEntries.size();
                while (true) {
                    if (index$iv$iv < size) {
                        Object item$iv$iv = allEntries.get(index$iv$iv);
                        it$iv = item$iv$iv;
                        if (sharedElement.getEnabledEntries().contains((SharedElementEntry) it$iv)) {
                            break;
                        }
                        index$iv$iv++;
                    } else {
                        it$iv = null;
                        break;
                    }
                }
                SharedElementEntry sharedElementEntry = (SharedElementEntry) it$iv;
                lastTarget = sharedElementEntry != null ? sharedElementEntry.getBoundsProvider() : null;
            }
            Rect it = SharedTransitionStateMachineKt.obtainBoundsFromLastTarget(sharedElement, lastTarget);
            if (it != null) {
                setCurrentBounds(it);
            }
        }
        return getCurrentBounds();
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x006c A[PHI: r0
  0x006c: PHI (r0v2 'currentBounds' androidx.compose.ui.geometry.Rect) = (r0v1 'currentBounds' androidx.compose.ui.geometry.Rect), (r0v6 'currentBounds' androidx.compose.ui.geometry.Rect) binds: [B:7:0x001c, B:23:0x0061] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // androidx.compose.animation.SharedTransitionStateMachine.State
    /* JADX INFO: renamed from: configureActiveMatch-38uP1EE */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public androidx.compose.animation.SharedTransitionStateMachine.State mo34configureActiveMatch38uP1EE(androidx.compose.animation.SharedElement r15, androidx.compose.animation.BoundsProvider r16, long r17, long r19, long r21) {
        /*
            r14 = this;
            androidx.compose.animation.TargetData r0 = r14.getTargetData()
            if (r0 != 0) goto L16
            androidx.compose.animation.TargetData r1 = new androidx.compose.animation.TargetData
            long r4 = androidx.compose.ui.geometry.Offset.m5072minusMKHz9U(r19, r21)
            r8 = 0
            r2 = r17
            r6 = r21
            r1.<init>(r2, r4, r6, r8)
            r2 = r1
            goto L17
        L16:
            r2 = r0
        L17:
            androidx.compose.ui.geometry.Rect r0 = r14.getCurrentBounds()
            if (r0 != 0) goto L6c
        L1f:
            androidx.compose.animation.BoundsProvider r0 = r14.targetBoundsProviderBeforeConfig
            if (r0 != 0) goto L5d
        L24:
            java.util.List r0 = r15.getAllEntries()
            r1 = 0
            r3 = r0
            r4 = 0
            r5 = 0
            r6 = r3
            java.util.Collection r6 = (java.util.Collection) r6
            int r6 = r6.size()
        L34:
            r7 = 0
            if (r5 >= r6) goto L50
            java.lang.Object r8 = r3.get(r5)
            r9 = r8
            r10 = 0
            r11 = r9
            androidx.compose.animation.SharedElementEntry r11 = (androidx.compose.animation.SharedElementEntry) r11
            r12 = 0
            java.util.List r13 = r15.getEnabledEntries()
            boolean r11 = r13.contains(r11)
            if (r11 == 0) goto L4c
            goto L52
        L4c:
            int r5 = r5 + 1
            goto L34
        L50:
            r9 = r7
        L52:
            androidx.compose.animation.SharedElementEntry r9 = (androidx.compose.animation.SharedElementEntry) r9
            if (r9 == 0) goto L5c
        L57:
            androidx.compose.animation.BoundsProvider r0 = r9.getBoundsProvider()
            goto L5d
        L5c:
            r0 = r7
        L5d:
            androidx.compose.ui.geometry.Rect r0 = androidx.compose.animation.SharedTransitionStateMachineKt.access$obtainBoundsFromLastTarget(r15, r0)
            if (r0 != 0) goto L6c
            r3 = r17
            r5 = r19
            androidx.compose.ui.geometry.Rect r0 = androidx.compose.ui.geometry.RectKt.m5108Recttz77jQw(r5, r3)
            goto L70
        L6c:
            r3 = r17
            r5 = r19
        L70:
            r9 = 1
            r7 = r21
            androidx.compose.animation.SharedTransitionStateMachineKt.m152access$updateTargetDataBGTQxF0(r2, r3, r5, r7, r9)
            androidx.compose.animation.ActiveMatchConfigured r3 = new androidx.compose.animation.ActiveMatchConfigured
            r4 = r16
            r3.<init>(r2, r4, r0)
            androidx.compose.animation.SharedTransitionStateMachine$State r3 = (androidx.compose.animation.SharedTransitionStateMachine.State) r3
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.animation.ActiveMatchFoundConfigPending.mo34configureActiveMatch38uP1EE(androidx.compose.animation.SharedElement, androidx.compose.animation.BoundsProvider, long, long, long):androidx.compose.animation.SharedTransitionStateMachine$State");
    }

    @Override // androidx.compose.animation.SharedTransitionStateMachine.State
    public SharedTransitionStateMachine.State onMatchFound(BoundsProvider previousTargetBoundsProvider) {
        if (this.targetBoundsProviderBeforeConfig == null) {
            this.targetBoundsProviderBeforeConfig = previousTargetBoundsProvider;
        }
        return this;
    }

    @Override // androidx.compose.animation.SharedTransitionStateMachine.State
    public void updateBounds(Rect bounds) {
        setCurrentBounds(bounds);
    }

    @Override // androidx.compose.animation.SharedTransitionStateMachine.State
    public SharedTransitionStateMachine.State onVisibleContentRemovedDuringTransition() {
        return NoMatchFound.INSTANCE;
    }
}
