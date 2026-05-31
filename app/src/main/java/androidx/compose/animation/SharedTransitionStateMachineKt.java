package androidx.compose.animation;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.RectKt;
import androidx.compose.ui.geometry.Size;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SharedTransitionStateMachine.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000@\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a\u0017\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0080\b\u001a7\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u0001H\u0002¢\u0006\u0004\b\u0010\u0010\u0011\u001a\u0018\u0010\u0012\u001a\u0004\u0018\u00010\u0013*\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0002\u001a\u0019\u0010\u001a\u001a\u00020\r*\u00020\t2\u0006\u0010\u001b\u001a\u00020\u0013H\u0000¢\u0006\u0002\u0010\u001c\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u0018\u0010\u0017\u001a\u00020\u0013*\u00020\t8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019¨\u0006\u001d"}, d2 = {"SharedTransitionDebug", "", "sharedTransitionDebug", "", "message", "Lkotlin/Function0;", "", "updateTargetData", "targetData", "Landroidx/compose/animation/TargetData;", "lookaheadSize", "Landroidx/compose/ui/geometry/Size;", "topLeft", "Landroidx/compose/ui/geometry/Offset;", "structuralOffset", "targetBoundsProviderChanged", "updateTargetData-BGTQxF0", "(Landroidx/compose/animation/TargetData;JJJZ)V", "obtainBoundsFromLastTarget", "Landroidx/compose/ui/geometry/Rect;", "Landroidx/compose/animation/SharedElement;", "lastTargetBoundsProvider", "Landroidx/compose/animation/BoundsProvider;", "targetBounds", "getTargetBounds", "(Landroidx/compose/animation/TargetData;)Landroidx/compose/ui/geometry/Rect;", "calculateOffsetFromDirectManipulation", "animatedBounds", "(Landroidx/compose/animation/TargetData;Landroidx/compose/ui/geometry/Rect;)J", "animation"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class SharedTransitionStateMachineKt {
    public static final boolean SharedTransitionDebug = false;

    public static final void sharedTransitionDebug(Function0<String> function0) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: updateTargetData-BGTQxF0, reason: not valid java name */
    public static final void m153updateTargetDataBGTQxF0(TargetData targetData, long lookaheadSize, long topLeft, long structuralOffset, boolean targetBoundsProviderChanged) {
        if (!Offset.m5065equalsimpl0(targetData.m169getTargetStructuralOffsetF1C5BW0(), structuralOffset) || !Size.m5133equalsimpl0(targetData.m168getSizeNHjbRc(), lookaheadSize) || targetBoundsProviderChanged) {
            targetData.m172setSizeuvyYCjk(lookaheadSize);
            targetData.m173setTargetStructuralOffsetk4lQ0M(structuralOffset);
            if (targetBoundsProviderChanged) {
                targetData.m171setInitialMfrOffsetk4lQ0M(Offset.m5072minusMKHz9U(Offset.m5072minusMKHz9U(topLeft, structuralOffset), Offset.m5072minusMKHz9U(targetData.m166getCurrentMfrOffsetF1C5BW0(), targetData.m167getInitialMfrOffsetF1C5BW0())));
            }
        }
        targetData.m170setCurrentMfrOffsetk4lQ0M(Offset.m5072minusMKHz9U(topLeft, structuralOffset));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Rect obtainBoundsFromLastTarget(SharedElement $this$obtainBoundsFromLastTarget, BoundsProvider lastTargetBoundsProvider) {
        boolean z;
        if (lastTargetBoundsProvider != null) {
            List<SharedElementEntry> allEntries = $this$obtainBoundsFromLastTarget.getAllEntries();
            int index$iv$iv = 0;
            int size = allEntries.size();
            while (true) {
                if (index$iv$iv < size) {
                    Object item$iv$iv = allEntries.get(index$iv$iv);
                    SharedElementEntry state = (SharedElementEntry) item$iv$iv;
                    if (Intrinsics.areEqual(state.getBoundsProvider(), lastTargetBoundsProvider)) {
                        z = true;
                        break;
                    }
                    index$iv$iv++;
                } else {
                    z = false;
                    break;
                }
            }
            if (z) {
                return lastTargetBoundsProvider.getLastBoundsInSharedTransitionScope();
            }
        }
        return null;
    }

    public static final Rect getTargetBounds(TargetData $this$targetBounds) {
        return RectKt.m5108Recttz77jQw(Offset.m5073plusMKHz9U($this$targetBounds.m167getInitialMfrOffsetF1C5BW0(), $this$targetBounds.m169getTargetStructuralOffsetF1C5BW0()), $this$targetBounds.m168getSizeNHjbRc());
    }

    public static final long calculateOffsetFromDirectManipulation(TargetData $this$calculateOffsetFromDirectManipulation, Rect animatedBounds) {
        return Offset.m5073plusMKHz9U(Offset.m5072minusMKHz9U(animatedBounds.m5103getTopLeftF1C5BW0(), $this$calculateOffsetFromDirectManipulation.m167getInitialMfrOffsetF1C5BW0()), $this$calculateOffsetFromDirectManipulation.m166getCurrentMfrOffsetF1C5BW0());
    }
}
