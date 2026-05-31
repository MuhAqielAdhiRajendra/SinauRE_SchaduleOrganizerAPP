package com.example.scheduleorganizer.ui.component;

import android.content.Context;
import androidx.compose.material3.SnackbarHostState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.snapshots.SnapshotStateList;
import com.example.scheduleorganizer.data.entity.Schedule;
import com.example.scheduleorganizer.ui.MainViewModel;
import com.example.scheduleorganizer.util.AlarmScheduler;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
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
@DebugMetadata(c = "com.example.scheduleorganizer.ui.component.CommonComponentsKt$EditScheduleDialog$1$1$1$1", f = "CommonComponents.kt", i = {0, 0, 0}, l = {380}, m = "invokeSuspend", n = {"updatedSchedule", "it\\1", "$i$a$-let-CommonComponentsKt$EditScheduleDialog$1$1$1$1$1\\1\\380\\0"}, nl = {380}, s = {"L$0", "L$1", "I$0"}, v = 2)
final class CommonComponentsKt$EditScheduleDialog$1$1$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MutableState<String> $category$delegate;
    final /* synthetic */ Context $context;
    final /* synthetic */ Function0<Unit> $onDismiss;
    final /* synthetic */ Schedule $schedule;
    final /* synthetic */ SnapshotStateList<Integer> $selectedDays;
    final /* synthetic */ SnackbarHostState $snackbarHostState;
    final /* synthetic */ MutableState<String> $time$delegate;
    final /* synthetic */ MutableState<String> $title$delegate;
    final /* synthetic */ MainViewModel $viewModel;
    int I$0;
    Object L$0;
    Object L$1;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    CommonComponentsKt$EditScheduleDialog$1$1$1$1(Schedule schedule, SnapshotStateList<Integer> snapshotStateList, Context context, MainViewModel mainViewModel, SnackbarHostState snackbarHostState, Function0<Unit> function0, MutableState<String> mutableState, MutableState<String> mutableState2, MutableState<String> mutableState3, Continuation<? super CommonComponentsKt$EditScheduleDialog$1$1$1$1> continuation) {
        super(2, continuation);
        this.$schedule = schedule;
        this.$selectedDays = snapshotStateList;
        this.$context = context;
        this.$viewModel = mainViewModel;
        this.$snackbarHostState = snackbarHostState;
        this.$onDismiss = function0;
        this.$title$delegate = mutableState;
        this.$category$delegate = mutableState2;
        this.$time$delegate = mutableState3;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new CommonComponentsKt$EditScheduleDialog$1$1$1$1(this.$schedule, this.$selectedDays, this.$context, this.$viewModel, this.$snackbarHostState, this.$onDismiss, this.$title$delegate, this.$category$delegate, this.$time$delegate, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((CommonComponentsKt$EditScheduleDialog$1$1$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object objShowSnackbar$default;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                Schedule updatedSchedule = Schedule.copy$default(this.$schedule, 0L, CommonComponentsKt.EditScheduleDialog$lambda$1(this.$title$delegate), CommonComponentsKt.EditScheduleDialog$lambda$4(this.$category$delegate), CommonComponentsKt.EditScheduleDialog$lambda$7(this.$time$delegate), CollectionsKt.joinToString$default(CollectionsKt.sorted(this.$selectedDays), ",", null, null, 0, null, null, 62, null), false, 33, null);
                AlarmScheduler.INSTANCE.cancelScheduleReminders(this.$context, this.$schedule.getId());
                this.$viewModel.updateSchedule(updatedSchedule);
                AlarmScheduler.INSTANCE.scheduleScheduleReminders(this.$context, updatedSchedule);
                SnackbarHostState snackbarHostState = this.$snackbarHostState;
                if (snackbarHostState != null) {
                    String str = "Jadwal '" + updatedSchedule.getTitle() + "' berhasil diubah";
                    this.L$0 = SpillingKt.nullOutSpilledVariable(updatedSchedule);
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
