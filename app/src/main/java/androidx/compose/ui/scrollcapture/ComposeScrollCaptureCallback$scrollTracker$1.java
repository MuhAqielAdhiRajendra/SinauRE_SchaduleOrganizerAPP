package androidx.compose.ui.scrollcapture;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.semantics.ScrollAxisRange;
import androidx.compose.ui.semantics.SemanticsProperties;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: ComposeScrollCaptureCallback.android.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0007\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n"}, d2 = {"<anonymous>", "", "delta"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.ui.scrollcapture.ComposeScrollCaptureCallback$scrollTracker$1", f = "ComposeScrollCaptureCallback.android.kt", i = {0}, l = {89}, m = "invokeSuspend", n = {"reverseScrolling"}, s = {"Z$0"}, v = 1)
final class ComposeScrollCaptureCallback$scrollTracker$1 extends SuspendLambda implements Function2<Float, Continuation<? super Float>, Object> {
    /* synthetic */ float F$0;
    boolean Z$0;
    int label;
    final /* synthetic */ ComposeScrollCaptureCallback this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ComposeScrollCaptureCallback$scrollTracker$1(ComposeScrollCaptureCallback composeScrollCaptureCallback, Continuation<? super ComposeScrollCaptureCallback$scrollTracker$1> continuation) {
        super(2, continuation);
        this.this$0 = composeScrollCaptureCallback;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        ComposeScrollCaptureCallback$scrollTracker$1 composeScrollCaptureCallback$scrollTracker$1 = new ComposeScrollCaptureCallback$scrollTracker$1(this.this$0, continuation);
        composeScrollCaptureCallback$scrollTracker$1.F$0 = ((Number) obj).floatValue();
        return composeScrollCaptureCallback$scrollTracker$1;
    }

    public final Object invoke(float f, Continuation<? super Float> continuation) {
        return ((ComposeScrollCaptureCallback$scrollTracker$1) create(Float.valueOf(f), continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(Float f, Continuation<? super Float> continuation) {
        return invoke(f.floatValue(), continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        boolean reverseScrolling;
        long j;
        Object $result;
        float fIntBitsToFloat;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure(obj);
                float delta = this.F$0;
                Function2<Offset, Continuation<? super Offset>, Object> scrollCaptureScrollByAction = ScrollCapture_androidKt.getScrollCaptureScrollByAction(this.this$0.node);
                if (scrollCaptureScrollByAction == null) {
                    InlineClassHelperKt.throwIllegalStateExceptionForNullCheck("Required value was null.");
                    throw new KotlinNothingValueException();
                }
                reverseScrolling = ((ScrollAxisRange) this.this$0.node.getUnmergedConfig().get(SemanticsProperties.INSTANCE.getVerticalScrollAxisRange())).getReverseScrolling();
                if (reverseScrolling) {
                    delta = -delta;
                }
                float val2$iv$iv = delta;
                long v1$iv$iv = Float.floatToRawIntBits(0.0f);
                j = 4294967295L;
                long v2$iv$iv = Float.floatToRawIntBits(val2$iv$iv);
                Offset offsetM5057boximpl = Offset.m5057boximpl(Offset.m5060constructorimpl((v1$iv$iv << 32) | (v2$iv$iv & 4294967295L)));
                this.Z$0 = reverseScrolling;
                this.label = 1;
                Object objInvoke = scrollCaptureScrollByAction.invoke(offsetM5057boximpl, this);
                if (objInvoke == coroutine_suspended) {
                    return coroutine_suspended;
                }
                $result = objInvoke;
                break;
                break;
            case 1:
                $result = obj;
                boolean reverseScrolling2 = this.Z$0;
                ResultKt.throwOnFailure($result);
                reverseScrolling = reverseScrolling2;
                j = 4294967295L;
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        long arg0$iv = ((Offset) $result).m5078unboximpl();
        if (reverseScrolling) {
            int bits$iv$iv$iv = (int) (arg0$iv & j);
            fIntBitsToFloat = -Float.intBitsToFloat(bits$iv$iv$iv);
        } else {
            int bits$iv$iv$iv2 = (int) (arg0$iv & j);
            fIntBitsToFloat = Float.intBitsToFloat(bits$iv$iv$iv2);
        }
        return Boxing.boxFloat(fIntBitsToFloat);
    }
}
