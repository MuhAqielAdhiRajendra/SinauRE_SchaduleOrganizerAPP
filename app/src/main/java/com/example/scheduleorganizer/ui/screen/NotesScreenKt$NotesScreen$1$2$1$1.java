package com.example.scheduleorganizer.ui.screen;

import android.content.Context;
import androidx.compose.material3.SnackbarHostState;
import androidx.compose.runtime.MutableState;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.example.scheduleorganizer.util.DailyNotesManager;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: NotesScreen.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.example.scheduleorganizer.ui.screen.NotesScreenKt$NotesScreen$1$2$1$1", f = "NotesScreen.kt", i = {}, l = {ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_HEIGHT}, m = "invokeSuspend", n = {}, nl = {ConstraintLayout.LayoutParams.Table.LAYOUT_WRAP_BEHAVIOR_IN_PARENT}, s = {}, v = 2)
final class NotesScreenKt$NotesScreen$1$2$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Context $context;
    final /* synthetic */ MutableState<String> $lastSaved$delegate;
    final /* synthetic */ MutableState<String> $noteText$delegate;
    final /* synthetic */ SnackbarHostState $snackbarHostState;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    NotesScreenKt$NotesScreen$1$2$1$1(Context context, SnackbarHostState snackbarHostState, MutableState<String> mutableState, MutableState<String> mutableState2, Continuation<? super NotesScreenKt$NotesScreen$1$2$1$1> continuation) {
        super(2, continuation);
        this.$context = context;
        this.$snackbarHostState = snackbarHostState;
        this.$noteText$delegate = mutableState;
        this.$lastSaved$delegate = mutableState2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new NotesScreenKt$NotesScreen$1$2$1$1(this.$context, this.$snackbarHostState, this.$noteText$delegate, this.$lastSaved$delegate, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((NotesScreenKt$NotesScreen$1$2$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                DailyNotesManager.INSTANCE.saveDailyNote(this.$context, NotesScreenKt.NotesScreen$lambda$1(this.$noteText$delegate));
                this.$lastSaved$delegate.setValue(DailyNotesManager.INSTANCE.getLastSavedDate(this.$context));
                this.label = 1;
                if (SnackbarHostState.showSnackbar$default(this.$snackbarHostState, "Catatan harian berhasil disimpan", null, false, null, this, 14, null) == coroutine_suspended) {
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
}
