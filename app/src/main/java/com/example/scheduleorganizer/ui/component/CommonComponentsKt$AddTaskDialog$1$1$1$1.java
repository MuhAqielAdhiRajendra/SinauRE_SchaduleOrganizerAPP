package com.example.scheduleorganizer.ui.component;

import android.content.Context;
import androidx.compose.material3.SnackbarHostState;
import androidx.compose.runtime.MutableState;
import com.example.scheduleorganizer.ui.MainViewModel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: CommonComponents.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.example.scheduleorganizer.ui.component.CommonComponentsKt$AddTaskDialog$1$1$1$1", f = "CommonComponents.kt", i = {1, 1, 1, 1}, l = {193, 196}, m = "invokeSuspend", n = {"newTask", "it\\1", "taskId", "$i$a$-let-CommonComponentsKt$AddTaskDialog$1$1$1$1$1\\1\\196\\0"}, nl = {194, 196}, s = {"L$0", "L$1", "J$0", "I$0"}, v = 2)
final class CommonComponentsKt$AddTaskDialog$1$1$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Context $context;
    final /* synthetic */ long $dueTimestamp;
    final /* synthetic */ Function0<Unit> $onDismiss;
    final /* synthetic */ MutableState<Integer> $priority$delegate;
    final /* synthetic */ MutableState<Long> $selectedCourse$delegate;
    final /* synthetic */ SnackbarHostState $snackbarHostState;
    final /* synthetic */ MutableState<String> $title$delegate;
    final /* synthetic */ MainViewModel $viewModel;
    int I$0;
    long J$0;
    Object L$0;
    Object L$1;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    CommonComponentsKt$AddTaskDialog$1$1$1$1(MainViewModel mainViewModel, long j, Context context, SnackbarHostState snackbarHostState, Function0<Unit> function0, MutableState<String> mutableState, MutableState<Long> mutableState2, MutableState<Integer> mutableState3, Continuation<? super CommonComponentsKt$AddTaskDialog$1$1$1$1> continuation) {
        super(2, continuation);
        this.$viewModel = mainViewModel;
        this.$dueTimestamp = j;
        this.$context = context;
        this.$snackbarHostState = snackbarHostState;
        this.$onDismiss = function0;
        this.$title$delegate = mutableState;
        this.$selectedCourse$delegate = mutableState2;
        this.$priority$delegate = mutableState3;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new CommonComponentsKt$AddTaskDialog$1$1$1$1(this.$viewModel, this.$dueTimestamp, this.$context, this.$snackbarHostState, this.$onDismiss, this.$title$delegate, this.$selectedCourse$delegate, this.$priority$delegate, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((CommonComponentsKt$AddTaskDialog$1$1$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0083  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r21) {
        /*
            Method dump skipped, instruction units count: 218
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.scheduleorganizer.ui.component.CommonComponentsKt$AddTaskDialog$1$1$1$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
