package androidx.compose.foundation.gestures;

import androidx.compose.animation.core.AnimationVector2D;
import androidx.compose.animation.core.SuspendAnimationKt;
import androidx.compose.animation.core.TwoWayConverter;
import androidx.compose.animation.core.VectorConvertersKt;
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
@DebugMetadata(c = "androidx.compose.foundation.gestures.Scrollable2DKt$semanticsScrollBy$2", f = "Scrollable2D.kt", i = {}, l = {515}, m = "invokeSuspend", n = {}, s = {}, v = 1)
final class Scrollable2DKt$semanticsScrollBy$2 extends SuspendLambda implements Function2<NestedScrollScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ long $offset;
    final /* synthetic */ Ref.LongRef $previousValue;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    Scrollable2DKt$semanticsScrollBy$2(long j, Ref.LongRef longRef, Continuation<? super Scrollable2DKt$semanticsScrollBy$2> continuation) {
        super(2, continuation);
        this.$offset = j;
        this.$previousValue = longRef;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        Scrollable2DKt$semanticsScrollBy$2 scrollable2DKt$semanticsScrollBy$2 = new Scrollable2DKt$semanticsScrollBy$2(this.$offset, this.$previousValue, continuation);
        scrollable2DKt$semanticsScrollBy$2.L$0 = obj;
        return scrollable2DKt$semanticsScrollBy$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(NestedScrollScope nestedScrollScope, Continuation<? super Unit> continuation) {
        return ((Scrollable2DKt$semanticsScrollBy$2) create(nestedScrollScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                final NestedScrollScope $this$scroll = (NestedScrollScope) this.L$0;
                TwoWayConverter<Offset, AnimationVector2D> vectorConverter = VectorConvertersKt.getVectorConverter(Offset.INSTANCE);
                Offset offsetM5057boximpl = Offset.m5057boximpl(Offset.INSTANCE.m5084getZeroF1C5BW0());
                Offset offsetM5057boximpl2 = Offset.m5057boximpl(this.$offset);
                final Ref.LongRef longRef = this.$previousValue;
                this.label = 1;
                if (SuspendAnimationKt.animate$default(vectorConverter, offsetM5057boximpl, offsetM5057boximpl2, null, null, new Function2() { // from class: androidx.compose.foundation.gestures.Scrollable2DKt$semanticsScrollBy$2$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return Scrollable2DKt$semanticsScrollBy$2.invokeSuspend$lambda$0(longRef, $this$scroll, (Offset) obj, (Offset) obj2);
                    }
                }, this, 24, null) == coroutine_suspended) {
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

    static final Unit invokeSuspend$lambda$0(Ref.LongRef $previousValue, NestedScrollScope $$this$scroll, Offset currentValue, Offset offset) {
        long delta = Offset.m5072minusMKHz9U(currentValue.m5078unboximpl(), $previousValue.element);
        long consumed = $$this$scroll.mo558scrollByOzD1aCk(delta, NestedScrollSource.INSTANCE.m6519getUserInputWNlRxjI());
        $previousValue.element = Offset.m5073plusMKHz9U($previousValue.element, consumed);
        return Unit.INSTANCE;
    }
}
