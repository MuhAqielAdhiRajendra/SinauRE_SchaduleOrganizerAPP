package com.example.scheduleorganizer.ui.screen;

import android.content.Context;
import androidx.compose.material3.SnackbarHostState;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.snapshots.SnapshotStateMap;
import com.example.scheduleorganizer.data.entity.Schedule;
import com.example.scheduleorganizer.ui.MainViewModel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: HomeScreen.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.example.scheduleorganizer.ui.screen.HomeScreenKt$HomeScreen$8$2$1$1$1", f = "HomeScreen.kt", i = {2}, l = {200, ComposerKt.providerValuesKey, 205}, m = "invokeSuspend", n = {"result"}, nl = {ComposerKt.providerKey, ComposerKt.providerMapsKey, ComposerKt.referenceKey}, s = {"L$0"}, v = 2)
final class HomeScreenKt$HomeScreen$8$2$1$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Context $context;
    final /* synthetic */ SnapshotStateMap<Long, Boolean> $removingMap;
    final /* synthetic */ Schedule $schedule;
    final /* synthetic */ SnackbarHostState $snackbarHostState;
    final /* synthetic */ MainViewModel $viewModel;
    Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    HomeScreenKt$HomeScreen$8$2$1$1$1(SnapshotStateMap<Long, Boolean> snapshotStateMap, Schedule schedule, Context context, MainViewModel mainViewModel, SnackbarHostState snackbarHostState, Continuation<? super HomeScreenKt$HomeScreen$8$2$1$1$1> continuation) {
        super(2, continuation);
        this.$removingMap = snapshotStateMap;
        this.$schedule = schedule;
        this.$context = context;
        this.$viewModel = mainViewModel;
        this.$snackbarHostState = snackbarHostState;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new HomeScreenKt$HomeScreen$8$2$1$1$1(this.$removingMap, this.$schedule, this.$context, this.$viewModel, this.$snackbarHostState, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((HomeScreenKt$HomeScreen$8$2$1$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0093 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:17:0x009a  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r14) {
        /*
            Method dump skipped, instruction units count: 258
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.scheduleorganizer.ui.screen.HomeScreenKt$HomeScreen$8$2$1$1$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
