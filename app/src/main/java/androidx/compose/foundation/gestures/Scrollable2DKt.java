package androidx.compose.foundation.gestures;

import androidx.compose.foundation.OverscrollEffect;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.unit.Velocity;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Metadata;

/* JADX INFO: compiled from: Scrollable2D.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000D\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\u001aB\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0007\u001a\u001c\u0010\u000e\u001a\u00020\u000f*\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u000fH\u0082@¢\u0006\u0004\b\u0012\u0010\u0013\"\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000\"\u0018\u0010\u0014\u001a\u00020\u0015*\u00020\u00168BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018\"\u0018\u0010\u0019\u001a\u00020\u0015*\u00020\u00168BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u0018¨\u0006\u001b"}, d2 = {"scrollable2D", "Landroidx/compose/ui/Modifier;", "state", "Landroidx/compose/foundation/gestures/Scrollable2DState;", "enabled", "", "overscrollEffect", "Landroidx/compose/foundation/OverscrollEffect;", "flingBehavior", "Landroidx/compose/foundation/gestures/FlingBehavior;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "NoOpScrollScope", "Landroidx/compose/foundation/gestures/Scroll2DScope;", "semanticsScrollBy", "Landroidx/compose/ui/geometry/Offset;", "Landroidx/compose/foundation/gestures/ScrollingLogic2D;", TypedValues.CycleType.S_WAVE_OFFSET, "semanticsScrollBy-d-4ec7I", "(Landroidx/compose/foundation/gestures/ScrollingLogic2D;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "magnitude", "", "Landroidx/compose/ui/unit/Velocity;", "getMagnitude-TH1AsA0", "(J)F", "angle", "getAngle-TH1AsA0", "foundation"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class Scrollable2DKt {
    private static final Scroll2DScope NoOpScrollScope = new Scroll2DScope() { // from class: androidx.compose.foundation.gestures.Scrollable2DKt$NoOpScrollScope$1
        @Override // androidx.compose.foundation.gestures.Scroll2DScope
        /* JADX INFO: renamed from: scrollBy-MK-Hz9U */
        public long mo474scrollByMKHz9U(long delta) {
            return delta;
        }
    };

    public static final Modifier scrollable2D(Modifier $this$scrollable2D, Scrollable2DState state, boolean enabled, OverscrollEffect overscrollEffect, FlingBehavior flingBehavior, MutableInteractionSource interactionSource) {
        return $this$scrollable2D.then(new Scrollable2DElement(state, overscrollEffect, enabled, flingBehavior, interactionSource));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /* JADX INFO: renamed from: semanticsScrollBy-d-4ec7I, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object m596semanticsScrollByd4ec7I(androidx.compose.foundation.gestures.ScrollingLogic2D r7, long r8, kotlin.coroutines.Continuation<? super androidx.compose.ui.geometry.Offset> r10) {
        /*
            boolean r0 = r10 instanceof androidx.compose.foundation.gestures.Scrollable2DKt$semanticsScrollBy$1
            if (r0 == 0) goto L14
            r0 = r10
            androidx.compose.foundation.gestures.Scrollable2DKt$semanticsScrollBy$1 r0 = (androidx.compose.foundation.gestures.Scrollable2DKt$semanticsScrollBy$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r1 = r0.label
            int r1 = r1 - r2
            r0.label = r1
            goto L19
        L14:
            androidx.compose.foundation.gestures.Scrollable2DKt$semanticsScrollBy$1 r0 = new androidx.compose.foundation.gestures.Scrollable2DKt$semanticsScrollBy$1
            r0.<init>(r10)
        L19:
            java.lang.Object r1 = r0.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r0.label
            switch(r3) {
                case 0: goto L35;
                case 1: goto L2d;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L2d:
            java.lang.Object r7 = r0.L$0
            kotlin.jvm.internal.Ref$LongRef r7 = (kotlin.jvm.internal.Ref.LongRef) r7
            kotlin.ResultKt.throwOnFailure(r1)
            goto L5c
        L35:
            kotlin.ResultKt.throwOnFailure(r1)
            kotlin.jvm.internal.Ref$LongRef r3 = new kotlin.jvm.internal.Ref$LongRef
            r3.<init>()
            androidx.compose.ui.geometry.Offset$Companion r4 = androidx.compose.ui.geometry.Offset.INSTANCE
            long r4 = r4.m5084getZeroF1C5BW0()
            r3.element = r4
            androidx.compose.foundation.MutatePriority r4 = androidx.compose.foundation.MutatePriority.Default
            androidx.compose.foundation.gestures.Scrollable2DKt$semanticsScrollBy$2 r5 = new androidx.compose.foundation.gestures.Scrollable2DKt$semanticsScrollBy$2
            r6 = 0
            r5.<init>(r8, r3, r6)
            kotlin.jvm.functions.Function2 r5 = (kotlin.jvm.functions.Function2) r5
            r0.L$0 = r3
            r6 = 1
            r0.label = r6
            java.lang.Object r7 = r7.scroll(r4, r5, r0)
            if (r7 != r2) goto L5b
            return r2
        L5b:
            r7 = r3
        L5c:
            long r8 = r7.element
            androidx.compose.ui.geometry.Offset r8 = androidx.compose.ui.geometry.Offset.m5057boximpl(r8)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.Scrollable2DKt.m596semanticsScrollByd4ec7I(androidx.compose.foundation.gestures.ScrollingLogic2D, long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getMagnitude-TH1AsA0, reason: not valid java name */
    public static final float m595getMagnitudeTH1AsA0(long $this$magnitude) {
        return (float) Math.sqrt(((float) Math.pow(Velocity.m8388getXimpl($this$magnitude), 2.0d)) + ((float) Math.pow(Velocity.m8389getYimpl($this$magnitude), 2.0d)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getAngle-TH1AsA0, reason: not valid java name */
    public static final float m594getAngleTH1AsA0(long $this$angle) {
        return (float) Math.atan2(Velocity.m8389getYimpl($this$angle), Velocity.m8388getXimpl($this$angle));
    }
}
