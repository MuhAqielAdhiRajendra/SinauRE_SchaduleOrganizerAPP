package androidx.compose.foundation.lazy.layout;

import androidx.compose.animation.core.AnimationScope;
import androidx.compose.animation.core.AnimationState;
import androidx.compose.material3.ProgressIndicatorKt;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Ref;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: LazyLayoutScrollScope.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0017\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0082\b\u001a\u0014\u0010\f\u001a\u00020\u0006*\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0000\u001a2\u0010\u0010\u001a\u00020\b*\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u0014H\u0080@¢\u0006\u0002\u0010\u0015\"\u0010\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\u0003\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\u0004\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"TargetDistance", "Landroidx/compose/ui/unit/Dp;", "F", "BoundDistance", "MinimumDistance", "DEBUG", "", "debugLog", "", "generateMsg", "Lkotlin/Function0;", "", "isItemVisible", "Landroidx/compose/foundation/lazy/layout/LazyLayoutScrollScope;", "index", "", "animateScrollToItem", "scrollOffset", "numOfItemsForTeleport", "density", "Landroidx/compose/ui/unit/Density;", "(Landroidx/compose/foundation/lazy/layout/LazyLayoutScrollScope;IIILandroidx/compose/ui/unit/Density;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "foundation"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class LazyLayoutScrollScopeKt {
    private static final boolean DEBUG = false;
    private static final float TargetDistance = Dp.m8150constructorimpl(2500);
    private static final float BoundDistance = Dp.m8150constructorimpl(ProgressIndicatorKt.CircularAnimationAdditionalRotationDelay);
    private static final float MinimumDistance = Dp.m8150constructorimpl(50);

    /* JADX INFO: renamed from: androidx.compose.foundation.lazy.layout.LazyLayoutScrollScopeKt$animateScrollToItem$1, reason: invalid class name */
    /* JADX INFO: compiled from: LazyLayoutScrollScope.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.lazy.layout.LazyLayoutScrollScopeKt", f = "LazyLayoutScrollScope.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1}, l = {177, 264}, m = "animateScrollToItem", n = {"$this$animateScrollToItem", "loop", "anim", "loops", "index", "scrollOffset", "numOfItemsForTeleport", "targetDistancePx", "boundDistancePx", "minDistancePx", "forward", "$this$animateScrollToItem", "index", "scrollOffset"}, s = {"L$0", "L$1", "L$2", "L$3", "I$0", "I$1", "I$2", "F$0", "F$1", "F$2", "I$3", "L$0", "I$0", "I$1"}, v = 1)
    static final class AnonymousClass1 extends ContinuationImpl {
        float F$0;
        float F$1;
        float F$2;
        int I$0;
        int I$1;
        int I$2;
        int I$3;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return LazyLayoutScrollScopeKt.animateScrollToItem(null, 0, 0, 0, null, this);
        }
    }

    private static final void debugLog(Function0<String> function0) {
    }

    public static final boolean isItemVisible(LazyLayoutScrollScope $this$isItemVisible, int index) {
        return index <= $this$isItemVisible.getLastVisibleItemIndex() && $this$isItemVisible.getFirstVisibleItemIndex() <= index;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00f9 A[Catch: ItemFoundInScroll -> 0x0244, TryCatch #2 {ItemFoundInScroll -> 0x0244, blocks: (B:34:0x00f5, B:36:0x00f9, B:38:0x00ff, B:50:0x0130, B:54:0x016c, B:58:0x0174), top: B:109:0x00f5 }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0016  */
    /* JADX WARN: Type inference failed for: r14v0, types: [T, androidx.compose.animation.core.AnimationState] */
    /* JADX WARN: Type inference failed for: r8v21, types: [T, androidx.compose.animation.core.AnimationState] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:69:0x01c7 -> B:107:0x01d6). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object animateScrollToItem(androidx.compose.foundation.lazy.layout.LazyLayoutScrollScope r39, int r40, int r41, int r42, androidx.compose.ui.unit.Density r43, kotlin.coroutines.Continuation<? super kotlin.Unit> r44) {
        /*
            Method dump skipped, instruction units count: 750
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.lazy.layout.LazyLayoutScrollScopeKt.animateScrollToItem(androidx.compose.foundation.lazy.layout.LazyLayoutScrollScope, int, int, int, androidx.compose.ui.unit.Density, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private static final boolean animateScrollToItem$isOvershot(boolean forward, LazyLayoutScrollScope $this_animateScrollToItem, int $index, int $scrollOffset) {
        if (forward) {
            if ($this_animateScrollToItem.getFirstVisibleItemIndex() > $index) {
                return true;
            }
            return $this_animateScrollToItem.getFirstVisibleItemIndex() == $index && $this_animateScrollToItem.getFirstVisibleItemScrollOffset() > $scrollOffset;
        }
        if ($this_animateScrollToItem.getFirstVisibleItemIndex() < $index) {
            return true;
        }
        return $this_animateScrollToItem.getFirstVisibleItemIndex() == $index && $this_animateScrollToItem.getFirstVisibleItemScrollOffset() < $scrollOffset;
    }

    /* JADX WARN: Multi-variable type inference failed */
    static final Unit animateScrollToItem$lambda$5(LazyLayoutScrollScope $this_animateScrollToItem, int $index, float $target, Ref.FloatRef $prevValue, Ref.BooleanRef $loop, boolean $forward, float $boundDistancePx, Ref.IntRef $loops, int $numOfItemsForTeleport, int $scrollOffset, Ref.ObjectRef $anim, AnimationScope $this$animateTo) {
        float coercedValue;
        if (!isItemVisible($this_animateScrollToItem, $index)) {
            if ($target > 0.0f) {
                coercedValue = RangesKt.coerceAtMost(((Number) $this$animateTo.getValue()).floatValue(), $target);
            } else {
                coercedValue = RangesKt.coerceAtLeast(((Number) $this$animateTo.getValue()).floatValue(), $target);
            }
            float delta = coercedValue - $prevValue.element;
            float consumed = $this_animateScrollToItem.scrollBy(delta);
            if (!isItemVisible($this_animateScrollToItem, $index) && !animateScrollToItem$isOvershot($forward, $this_animateScrollToItem, $index, $scrollOffset)) {
                if (!(delta == consumed)) {
                    $this$animateTo.cancelAnimation();
                    $loop.element = false;
                    return Unit.INSTANCE;
                }
                $prevValue.element += delta;
                if ($forward) {
                    if (((Number) $this$animateTo.getValue()).floatValue() > $boundDistancePx) {
                        $this$animateTo.cancelAnimation();
                    }
                } else if (((Number) $this$animateTo.getValue()).floatValue() < (-$boundDistancePx)) {
                    $this$animateTo.cancelAnimation();
                }
                if ($forward) {
                    if ($loops.element >= 2 && $index - $this_animateScrollToItem.getLastVisibleItemIndex() > $numOfItemsForTeleport) {
                        int $i$f$debugLog = $index - $numOfItemsForTeleport;
                        $this_animateScrollToItem.snapToItem($i$f$debugLog, 0);
                    }
                } else if ($loops.element >= 2 && $this_animateScrollToItem.getFirstVisibleItemIndex() - $index > $numOfItemsForTeleport) {
                    int $i$f$debugLog2 = $index + $numOfItemsForTeleport;
                    $this_animateScrollToItem.snapToItem($i$f$debugLog2, 0);
                }
            }
        }
        if (animateScrollToItem$isOvershot($forward, $this_animateScrollToItem, $index, $scrollOffset)) {
            $this_animateScrollToItem.snapToItem($index, $scrollOffset);
            $loop.element = false;
            $this$animateTo.cancelAnimation();
            return Unit.INSTANCE;
        }
        if (isItemVisible($this_animateScrollToItem, $index)) {
            int targetItemOffset = LazyLayoutScrollScope.calculateDistanceTo$default($this_animateScrollToItem, $index, 0, 2, null);
            throw new ItemFoundInScroll(targetItemOffset, (AnimationState) $anim.element);
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0057  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static final kotlin.Unit animateScrollToItem$lambda$7(float r6, kotlin.jvm.internal.Ref.FloatRef r7, androidx.compose.foundation.lazy.layout.LazyLayoutScrollScope r8, androidx.compose.animation.core.AnimationScope r9) {
        /*
            r0 = 0
            int r1 = (r6 > r0 ? 1 : (r6 == r0 ? 0 : -1))
            if (r1 <= 0) goto L15
            java.lang.Object r0 = r9.getValue()
            java.lang.Number r0 = (java.lang.Number) r0
            float r0 = r0.floatValue()
            float r0 = kotlin.ranges.RangesKt.coerceAtMost(r0, r6)
            goto L2c
        L15:
            int r1 = (r6 > r0 ? 1 : (r6 == r0 ? 0 : -1))
            if (r1 >= 0) goto L28
            java.lang.Object r0 = r9.getValue()
            java.lang.Number r0 = (java.lang.Number) r0
            float r0 = r0.floatValue()
            float r0 = kotlin.ranges.RangesKt.coerceAtLeast(r0, r6)
            goto L2c
        L28:
            r1 = 0
        L2c:
            float r1 = r7.element
            float r1 = r0 - r1
            r2 = 0
            float r2 = r8.scrollBy(r1)
            int r3 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1))
            r4 = 1
            r5 = 0
            if (r3 != 0) goto L42
            r3 = r4
            goto L43
        L42:
            r3 = r5
        L43:
            if (r3 == 0) goto L57
            java.lang.Object r3 = r9.getValue()
            java.lang.Number r3 = (java.lang.Number) r3
            float r3 = r3.floatValue()
            int r3 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r3 != 0) goto L54
            goto L55
        L54:
            r4 = r5
        L55:
            if (r4 != 0) goto L5a
        L57:
            r9.cancelAnimation()
        L5a:
            float r3 = r7.element
            float r3 = r3 + r1
            r7.element = r3
            kotlin.Unit r3 = kotlin.Unit.INSTANCE
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.lazy.layout.LazyLayoutScrollScopeKt.animateScrollToItem$lambda$7(float, kotlin.jvm.internal.Ref$FloatRef, androidx.compose.foundation.lazy.layout.LazyLayoutScrollScope, androidx.compose.animation.core.AnimationScope):kotlin.Unit");
    }
}
