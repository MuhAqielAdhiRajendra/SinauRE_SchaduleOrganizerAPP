package androidx.compose.material3.internal;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.State;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;

/* JADX INFO: compiled from: BasicEdgeToEdgeDialog.android.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
final class BasicEdgeToEdgeDialog_androidKt$BasicEdgeToEdgeDialog$dialog$1$1$1 implements Function2<Composer, Integer, Unit> {
    final /* synthetic */ State<Function3<PredictiveBackState, Composer, Integer, Unit>> $currentContent$delegate;
    final /* synthetic */ State<Boolean> $currentDismissOnBackPress$delegate;
    final /* synthetic */ State<Function0<Unit>> $currentOnDismissRequest$delegate;
    final /* synthetic */ Modifier $modifier;

    /* JADX WARN: Multi-variable type inference failed */
    BasicEdgeToEdgeDialog_androidKt$BasicEdgeToEdgeDialog$dialog$1$1$1(Modifier modifier, State<Boolean> state, State<? extends Function0<Unit>> state2, State<? extends Function3<? super PredictiveBackState, ? super Composer, ? super Integer, Unit>> state3) {
        this.$modifier = modifier;
        this.$currentDismissOnBackPress$delegate = state;
        this.$currentOnDismissRequest$delegate = state2;
        this.$currentContent$delegate = state3;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
        invoke(composer, num.intValue());
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x019a  */
    /* JADX WARN: Removed duplicated region for block: B:35:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void invoke(androidx.compose.runtime.Composer r30, int r31) {
        /*
            Method dump skipped, instruction units count: 418
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.internal.BasicEdgeToEdgeDialog_androidKt$BasicEdgeToEdgeDialog$dialog$1$1$1.invoke(androidx.compose.runtime.Composer, int):void");
    }

    static final Unit invoke$lambda$1$lambda$0(SemanticsPropertyReceiver $this$semantics) {
        SemanticsPropertiesKt.dialog($this$semantics);
        return Unit.INSTANCE;
    }
}
