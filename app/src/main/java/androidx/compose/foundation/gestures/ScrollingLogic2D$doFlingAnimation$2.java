package androidx.compose.foundation.gestures;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.nestedscroll.NestedScrollSource;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;

/* JADX INFO: compiled from: Scrollable2D.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/foundation/gestures/NestedScrollScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.foundation.gestures.ScrollingLogic2D$doFlingAnimation$2", f = "Scrollable2D.kt", i = {}, l = {461}, m = "invokeSuspend", n = {}, s = {}, v = 1)
final class ScrollingLogic2D$doFlingAnimation$2 extends SuspendLambda implements Function2<NestedScrollScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ long $available;
    final /* synthetic */ Ref.LongRef $result;
    long J$0;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ ScrollingLogic2D this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ScrollingLogic2D$doFlingAnimation$2(ScrollingLogic2D scrollingLogic2D, long j, Ref.LongRef longRef, Continuation<? super ScrollingLogic2D$doFlingAnimation$2> continuation) {
        super(2, continuation);
        this.this$0 = scrollingLogic2D;
        this.$available = j;
        this.$result = longRef;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        ScrollingLogic2D$doFlingAnimation$2 scrollingLogic2D$doFlingAnimation$2 = new ScrollingLogic2D$doFlingAnimation$2(this.this$0, this.$available, this.$result, continuation);
        scrollingLogic2D$doFlingAnimation$2.L$0 = obj;
        return scrollingLogic2D$doFlingAnimation$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(NestedScrollScope nestedScrollScope, Continuation<? super Unit> continuation) {
        return ((ScrollingLogic2D$doFlingAnimation$2) create(nestedScrollScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        long j;
        Ref.LongRef longRef;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                final NestedScrollScope nestedScrollScope = (NestedScrollScope) this.L$0;
                final ScrollingLogic2D scrollingLogic2D = this.this$0;
                final long j2 = this.$available;
                ScrollScope scrollScope = new ScrollScope() { // from class: androidx.compose.foundation.gestures.ScrollingLogic2D$doFlingAnimation$2$flingScope$1
                    @Override // androidx.compose.foundation.gestures.ScrollScope
                    public float scrollBy(float pixels) {
                        long pixelsOffset = ScrollingLogic2D.doFlingAnimation_QWom1Mo$toDecomposedOffset(pixels, j2);
                        if (!Offset.m5065equalsimpl0(pixelsOffset, Offset.INSTANCE.m5084getZeroF1C5BW0()) && !((Boolean) scrollingLogic2D.isScrollableNodeAttached.invoke()).booleanValue()) {
                            throw new FlingCancellationException();
                        }
                        long consumedOffset = nestedScrollScope.mo559scrollByWithOverscrollOzD1aCk(pixelsOffset, NestedScrollSource.INSTANCE.m6518getSideEffectWNlRxjI());
                        return ScrollingLogic2D.doFlingAnimation_QWom1Mo$toMagnitudeFloat(consumedOffset);
                    }
                };
                ScrollingLogic2D scrollingLogic2D2 = this.this$0;
                long j3 = this.$available;
                Ref.LongRef longRef2 = this.$result;
                FlingBehavior $this$invokeSuspend_u24lambda_u240_u240 = scrollingLogic2D2.flingBehavior;
                float fM595getMagnitudeTH1AsA0 = Scrollable2DKt.m595getMagnitudeTH1AsA0(j3);
                this.L$0 = longRef2;
                this.J$0 = j3;
                this.label = 1;
                Object objPerformFling = $this$invokeSuspend_u24lambda_u240_u240.performFling(scrollScope, fM595getMagnitudeTH1AsA0, this);
                if (objPerformFling == coroutine_suspended) {
                    return coroutine_suspended;
                }
                $result = objPerformFling;
                j = j3;
                longRef = longRef2;
                break;
            case 1:
                j = this.J$0;
                longRef = (Ref.LongRef) this.L$0;
                ResultKt.throwOnFailure($result);
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        float resultVelocity = ((Number) $result).floatValue();
        longRef.element = ScrollingLogic2D.doFlingAnimation_QWom1Mo$toDecomposedVelocity(resultVelocity, j);
        return Unit.INSTANCE;
    }
}
