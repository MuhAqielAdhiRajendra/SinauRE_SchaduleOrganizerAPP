package com.example.scheduleorganizer.ui.screen;

import android.content.Context;
import android.database.Cursor;
import android.media.RingtoneManager;
import android.net.Uri;
import androidx.compose.runtime.snapshots.SnapshotStateList;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: ProfilScreen.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.example.scheduleorganizer.ui.screen.ProfilScreenKt$ProfilScreen$1$1", f = "ProfilScreen.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
final class ProfilScreenKt$ProfilScreen$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ SnapshotStateList<Pair<String, Uri>> $alarmSounds;
    final /* synthetic */ Context $context;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ProfilScreenKt$ProfilScreen$1$1(Context context, SnapshotStateList<Pair<String, Uri>> snapshotStateList, Continuation<? super ProfilScreenKt$ProfilScreen$1$1> continuation) {
        super(2, continuation);
        this.$context = context;
        this.$alarmSounds = snapshotStateList;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ProfilScreenKt$ProfilScreen$1$1(this.$context, this.$alarmSounds, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ProfilScreenKt$ProfilScreen$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) throws Throwable {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                RingtoneManager ringtoneManager = new RingtoneManager(this.$context);
                ringtoneManager.setType(4);
                Cursor cursor = ringtoneManager.getCursor();
                while (cursor.moveToNext()) {
                    String title = cursor.getString(1);
                    if (title == null) {
                        title = "Suara Alarm";
                    }
                    Uri uri = ringtoneManager.getRingtoneUri(cursor.getPosition());
                    if (uri != null) {
                        this.$alarmSounds.add(TuplesKt.to(title, uri));
                    }
                }
                cursor.close();
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
