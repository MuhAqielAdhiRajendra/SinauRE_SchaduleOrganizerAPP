package com.example.scheduleorganizer.ui.component;

import android.content.Context;
import androidx.compose.material3.SnackbarHostState;
import androidx.compose.runtime.MutableState;
import com.example.scheduleorganizer.data.entity.Task;
import com.example.scheduleorganizer.ui.MainViewModel;
import com.example.scheduleorganizer.util.AlarmScheduler;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: CommonComponents.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.example.scheduleorganizer.ui.component.CommonComponentsKt$EditTaskDialog$1$1$1$1", f = "CommonComponents.kt", i = {0, 0, 0}, l = {277}, m = "invokeSuspend", n = {"updatedTask", "it\\1", "$i$a$-let-CommonComponentsKt$EditTaskDialog$1$1$1$1$1\\1\\277\\0"}, nl = {277}, s = {"L$0", "L$1", "I$0"}, v = 2)
final class CommonComponentsKt$EditTaskDialog$1$1$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Context $context;
    final /* synthetic */ long $dueTimestamp;
    final /* synthetic */ Function0<Unit> $onDismiss;
    final /* synthetic */ MutableState<Integer> $priority$delegate;
    final /* synthetic */ MutableState<Long> $selectedCourse$delegate;
    final /* synthetic */ SnackbarHostState $snackbarHostState;
    final /* synthetic */ Task $task;
    final /* synthetic */ MutableState<String> $title$delegate;
    final /* synthetic */ MainViewModel $viewModel;
    int I$0;
    Object L$0;
    Object L$1;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    CommonComponentsKt$EditTaskDialog$1$1$1$1(Task task, long j, Context context, MainViewModel mainViewModel, SnackbarHostState snackbarHostState, Function0<Unit> function0, MutableState<String> mutableState, MutableState<Long> mutableState2, MutableState<Integer> mutableState3, Continuation<? super CommonComponentsKt$EditTaskDialog$1$1$1$1> continuation) {
        super(2, continuation);
        this.$task = task;
        this.$dueTimestamp = j;
        this.$context = context;
        this.$viewModel = mainViewModel;
        this.$snackbarHostState = snackbarHostState;
        this.$onDismiss = function0;
        this.$title$delegate = mutableState;
        this.$selectedCourse$delegate = mutableState2;
        this.$priority$delegate = mutableState3;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new CommonComponentsKt$EditTaskDialog$1$1$1$1(this.$task, this.$dueTimestamp, this.$context, this.$viewModel, this.$snackbarHostState, this.$onDismiss, this.$title$delegate, this.$selectedCourse$delegate, this.$priority$delegate, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((CommonComponentsKt$EditTaskDialog$1$1$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object objShowSnackbar$default;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                Task updatedTask = Task.copy$default(this.$task, 0L, CommonComponentsKt.EditTaskDialog$lambda$1(this.$title$delegate), CommonComponentsKt.EditTaskDialog$lambda$7(this.$selectedCourse$delegate), this.$dueTimestamp, false, CommonComponentsKt.EditTaskDialog$lambda$4(this.$priority$delegate), 17, null);
                AlarmScheduler.INSTANCE.cancelTaskReminder(this.$context, this.$task.getId());
                this.$viewModel.updateTask(updatedTask);
                AlarmScheduler.INSTANCE.scheduleTaskReminder(this.$context, updatedTask);
                SnackbarHostState snackbarHostState = this.$snackbarHostState;
                if (snackbarHostState != null) {
                    String str = "Tugas '" + updatedTask.getTitle() + "' berhasil diubah";
                    this.L$0 = SpillingKt.nullOutSpilledVariable(updatedTask);
                    this.L$1 = SpillingKt.nullOutSpilledVariable(snackbarHostState);
                    this.I$0 = 0;
                    this.label = 1;
                    objShowSnackbar$default = SnackbarHostState.showSnackbar$default(snackbarHostState, str, null, false, null, this, 14, null);
                    if (objShowSnackbar$default == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
                this.$onDismiss.invoke();
                return Unit.INSTANCE;
            case 1:
                int i = this.I$0;
                ResultKt.throwOnFailure($result);
                objShowSnackbar$default = $result;
                this.$onDismiss.invoke();
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
