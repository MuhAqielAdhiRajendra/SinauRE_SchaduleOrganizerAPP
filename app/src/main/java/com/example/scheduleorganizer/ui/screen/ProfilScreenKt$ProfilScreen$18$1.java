package com.example.scheduleorganizer.ui.screen;

import androidx.compose.material3.SnackbarHostState;
import com.example.scheduleorganizer.ui.MainViewModel;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.SharedFlow;

/* JADX INFO: compiled from: ProfilScreen.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.example.scheduleorganizer.ui.screen.ProfilScreenKt$ProfilScreen$18$1", f = "ProfilScreen.kt", i = {}, l = {231}, m = "invokeSuspend", n = {}, nl = {-1}, s = {}, v = 2)
final class ProfilScreenKt$ProfilScreen$18$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ SnackbarHostState $snackbarHostState;
    final /* synthetic */ MainViewModel $viewModel;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ProfilScreenKt$ProfilScreen$18$1(MainViewModel mainViewModel, SnackbarHostState snackbarHostState, Continuation<? super ProfilScreenKt$ProfilScreen$18$1> continuation) {
        super(2, continuation);
        this.$viewModel = mainViewModel;
        this.$snackbarHostState = snackbarHostState;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ProfilScreenKt$ProfilScreen$18$1(this.$viewModel, this.$snackbarHostState, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ProfilScreenKt$ProfilScreen$18$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                SharedFlow<String> uiEvents = this.$viewModel.getUiEvents();
                final SnackbarHostState snackbarHostState = this.$snackbarHostState;
                this.label = 1;
                if (uiEvents.collect(new FlowCollector() { // from class: com.example.scheduleorganizer.ui.screen.ProfilScreenKt$ProfilScreen$18$1.1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public /* bridge */ /* synthetic */ Object emit(Object value, Continuation $completion) {
                        return emit((String) value, (Continuation<? super Unit>) $completion);
                    }

                    public final Object emit(String msg, Continuation<? super Unit> continuation) {
                        Object objShowSnackbar$default = SnackbarHostState.showSnackbar$default(snackbarHostState, msg, null, false, null, continuation, 14, null);
                        return objShowSnackbar$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objShowSnackbar$default : Unit.INSTANCE;
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
        throw new KotlinNothingValueException();
    }
}
