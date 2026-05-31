package com.example.scheduleorganizer.ui.screen;

import android.content.Context;
import androidx.compose.material3.SnackbarHostState;
import androidx.compose.runtime.snapshots.SnapshotStateMap;
import com.example.scheduleorganizer.data.entity.Task;
import com.example.scheduleorganizer.ui.MainViewModel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: TugasScreen.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.example.scheduleorganizer.ui.screen.TugasScreenKt$TugasScreen$4$2$1$1$1", f = "TugasScreen.kt", i = {2}, l = {173, 176, 178}, m = "invokeSuspend", n = {"result"}, nl = {174, 177, 179}, s = {"L$0"}, v = 2)
final class TugasScreenKt$TugasScreen$4$2$1$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Context $context;
    final /* synthetic */ SnapshotStateMap<Long, Boolean> $removingMap;
    final /* synthetic */ SnackbarHostState $snackbarHostState;
    final /* synthetic */ Task $task;
    final /* synthetic */ MainViewModel $viewModel;
    Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    TugasScreenKt$TugasScreen$4$2$1$1$1(SnapshotStateMap<Long, Boolean> snapshotStateMap, Task task, Context context, MainViewModel mainViewModel, SnackbarHostState snackbarHostState, Continuation<? super TugasScreenKt$TugasScreen$4$2$1$1$1> continuation) {
        super(2, continuation);
        this.$removingMap = snapshotStateMap;
        this.$task = task;
        this.$context = context;
        this.$viewModel = mainViewModel;
        this.$snackbarHostState = snackbarHostState;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new TugasScreenKt$TugasScreen$4$2$1$1$1(this.$removingMap, this.$task, this.$context, this.$viewModel, this.$snackbarHostState, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((TugasScreenKt$TugasScreen$4$2$1$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0093 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:17:0x009a  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r15) {
        /*
            Method dump skipped, instruction units count: 260
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.scheduleorganizer.ui.screen.TugasScreenKt$TugasScreen$4$2$1$1$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
