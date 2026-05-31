package androidx.compose.material3.carousel;

import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.foundation.gestures.ScrollScope;
import androidx.compose.foundation.lazy.layout.LazyLayoutScrollScope;
import androidx.compose.foundation.pager.PagerScrollScopeKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: CarouselState.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/foundation/gestures/ScrollScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.material3.carousel.CarouselState$animateScrollToItem$2$1", f = "CarouselState.kt", i = {}, l = {111}, m = "invokeSuspend", n = {}, s = {})
final class CarouselState$animateScrollToItem$2$1 extends SuspendLambda implements Function2<ScrollScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ AnimationSpec<Float> $animationSpec;
    final /* synthetic */ int $targetPage;
    final /* synthetic */ CarouselPagerState $this_with;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    CarouselState$animateScrollToItem$2$1(CarouselPagerState carouselPagerState, int i, AnimationSpec<Float> animationSpec, Continuation<? super CarouselState$animateScrollToItem$2$1> continuation) {
        super(2, continuation);
        this.$this_with = carouselPagerState;
        this.$targetPage = i;
        this.$animationSpec = animationSpec;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        CarouselState$animateScrollToItem$2$1 carouselState$animateScrollToItem$2$1 = new CarouselState$animateScrollToItem$2$1(this.$this_with, this.$targetPage, this.$animationSpec, continuation);
        carouselState$animateScrollToItem$2$1.L$0 = obj;
        return carouselState$animateScrollToItem$2$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(ScrollScope scrollScope, Continuation<? super Unit> continuation) {
        return ((CarouselState$animateScrollToItem$2$1) create(scrollScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                ScrollScope $this$scroll = (ScrollScope) this.L$0;
                LazyLayoutScrollScope LazyLayoutScrollScope = PagerScrollScopeKt.LazyLayoutScrollScope(this.$this_with, $this$scroll);
                CarouselPagerState carouselPagerState = this.$this_with;
                int i = this.$targetPage;
                AnimationSpec<Float> animationSpec = this.$animationSpec;
                final CarouselPagerState carouselPagerState2 = this.$this_with;
                this.label = 1;
                if (CarouselStateKt.animateScrollToPage(LazyLayoutScrollScope, carouselPagerState, i, 0.0f, animationSpec, new Function2() { // from class: androidx.compose.material3.carousel.CarouselState$animateScrollToItem$2$1$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return CarouselState$animateScrollToItem$2$1.invokeSuspend$lambda$0(carouselPagerState2, (ScrollScope) obj, ((Integer) obj2).intValue());
                    }
                }, this) == coroutine_suspended) {
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

    static final Unit invokeSuspend$lambda$0(CarouselPagerState $this_with, ScrollScope $this$animateScrollToPage, int it) {
        $this_with.updateTargetPage($this$animateScrollToPage, it);
        return Unit.INSTANCE;
    }
}
