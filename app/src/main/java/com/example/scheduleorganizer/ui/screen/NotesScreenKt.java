package com.example.scheduleorganizer.ui.screen;

import android.content.Context;
import androidx.compose.material3.SnackbarHostState;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import com.example.scheduleorganizer.ui.MainViewModel;
import com.example.scheduleorganizer.util.DailyNotesManager;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: NotesScreen.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\u001a1\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005H\u0007¢\u0006\u0002\u0010\u0007¨\u0006\b²\u0006\n\u0010\t\u001a\u00020\nX\u008a\u008e\u0002²\u0006\n\u0010\u000b\u001a\u00020\nX\u008a\u008e\u0002²\u0006\n\u0010\f\u001a\u00020\rX\u008a\u008e\u0002"}, d2 = {"NotesScreen", "", "viewModel", "Lcom/example/scheduleorganizer/ui/MainViewModel;", "onOpenProfile", "Lkotlin/Function0;", "onOpenChat", "(Lcom/example/scheduleorganizer/ui/MainViewModel;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "app", "noteText", "", "lastSaved", "menuExpanded", ""}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class NotesScreenKt {
    static final Unit NotesScreen$lambda$11(MainViewModel mainViewModel, Function0 function0, Function0 function02, int i, Composer composer, int i2) {
        NotesScreen(mainViewModel, function0, function02, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:54:0x046d  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x047f  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0578  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void NotesScreen(final com.example.scheduleorganizer.ui.MainViewModel r79, final kotlin.jvm.functions.Function0<kotlin.Unit> r80, final kotlin.jvm.functions.Function0<kotlin.Unit> r81, androidx.compose.runtime.Composer r82, final int r83) {
        /*
            Method dump skipped, instruction units count: 1424
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.scheduleorganizer.ui.screen.NotesScreenKt.NotesScreen(com.example.scheduleorganizer.ui.MainViewModel, kotlin.jvm.functions.Function0, kotlin.jvm.functions.Function0, androidx.compose.runtime.Composer, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MutableState NotesScreen$lambda$0$0(Context $context) {
        return SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(DailyNotesManager.INSTANCE.getDailyNote($context), null, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String NotesScreen$lambda$1(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MutableState NotesScreen$lambda$3$0(Context $context) {
        return SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(DailyNotesManager.INSTANCE.getLastSavedDate($context), null, 2, null);
    }

    private static final String NotesScreen$lambda$4(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit NotesScreen$lambda$10$0$0(MutableState $noteText$delegate, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        $noteText$delegate.setValue(it);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit NotesScreen$lambda$10$1$0(CoroutineScope $coroutineScope, Context $context, SnackbarHostState $snackbarHostState, MutableState $noteText$delegate, MutableState $lastSaved$delegate) {
        BuildersKt__Builders_commonKt.launch$default($coroutineScope, null, null, new NotesScreenKt$NotesScreen$1$2$1$1($context, $snackbarHostState, $noteText$delegate, $lastSaved$delegate, null), 3, null);
        return Unit.INSTANCE;
    }
}
