package com.example.scheduleorganizer.util;

import android.content.Context;
import android.net.Uri;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.example.scheduleorganizer.data.AppDatabase;
import com.example.scheduleorganizer.data.dao.AppDao;
import com.google.gson.Gson;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;

/* JADX INFO: compiled from: BackupManager.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0016\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0086@¢\u0006\u0002\u0010\u0010J\u0016\u0010\u0011\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0086@¢\u0006\u0002\u0010\u0010R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/example/scheduleorganizer/util/BackupManager;", "", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "gson", "Lcom/google/gson/Gson;", "database", "Lcom/example/scheduleorganizer/data/AppDatabase;", "dao", "Lcom/example/scheduleorganizer/data/dao/AppDao;", "exportData", "", "uri", "Landroid/net/Uri;", "(Landroid/net/Uri;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "importData", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class BackupManager {
    public static final int $stable = 8;
    private final Context context;
    private final AppDao dao;
    private final AppDatabase database;
    private final Gson gson;

    public BackupManager(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.gson = new Gson();
        this.database = AppDatabase.INSTANCE.getDatabase(this.context);
        this.dao = this.database.appDao();
    }

    /* JADX INFO: renamed from: com.example.scheduleorganizer.util.BackupManager$exportData$2, reason: invalid class name */
    /* JADX INFO: compiled from: BackupManager.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.example.scheduleorganizer.util.BackupManager$exportData$2", f = "BackupManager.kt", i = {1, 2, 2, 3, 3, 3}, l = {28, ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_HORIZONTAL_BIAS, 30, 31}, m = "invokeSuspend", n = {"courses", "courses", "schedules", "courses", "schedules", "tasks"}, nl = {ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_HORIZONTAL_BIAS, 30, 31, 33}, s = {"L$0", "L$0", "L$1", "L$0", "L$1", "L$2"}, v = 2)
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Boolean>, Object> {
        final /* synthetic */ Uri $uri;
        Object L$0;
        Object L$1;
        Object L$2;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(Uri uri, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$uri = uri;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return BackupManager.this.new AnonymousClass2(this.$uri, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Boolean> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:24:0x0076 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:27:0x0093 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:28:0x0094  */
        /* JADX WARN: Removed duplicated region for block: B:31:0x00b5 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:32:0x00b6  */
        /* JADX WARN: Removed duplicated region for block: B:35:0x00da A[Catch: Exception -> 0x003f, TRY_LEAVE, TryCatch #0 {Exception -> 0x003f, blocks: (B:7:0x001e, B:33:0x00b7, B:35:0x00da, B:40:0x00f8, B:50:0x0106, B:51:0x0109, B:10:0x002c, B:29:0x0097, B:13:0x0035, B:25:0x0077, B:14:0x003a, B:22:0x005c, B:19:0x0046, B:36:0x00dc, B:39:0x00f2, B:45:0x00ff, B:46:0x0102, B:48:0x0104), top: B:56:0x0007, inners: #1, #2 }] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r15) {
            /*
                Method dump skipped, instruction units count: 292
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.example.scheduleorganizer.util.BackupManager.AnonymousClass2.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public final Object exportData(Uri uri, Continuation<? super Boolean> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new AnonymousClass2(uri, null), continuation);
    }

    /* JADX INFO: renamed from: com.example.scheduleorganizer.util.BackupManager$importData$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: BackupManager.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.example.scheduleorganizer.util.BackupManager$importData$2", f = "BackupManager.kt", i = {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4}, l = {ConstraintLayout.LayoutParams.Table.LAYOUT_MARGIN_BASELINE, 56, 57, 58, 59}, m = "invokeSuspend", n = {"inputStream\\1", "reader\\2", "backupData\\2", "$i$a$-use-BackupManager$importData$2$1\\1\\50\\0", "$i$a$-use-BackupManager$importData$2$1$1\\2\\51\\1", "inputStream\\1", "reader\\2", "backupData\\2", "$this$forEach\\3", "element\\3", "it\\4", "$i$a$-use-BackupManager$importData$2$1\\1\\50\\0", "$i$a$-use-BackupManager$importData$2$1$1\\2\\51\\1", "$i$f$forEach\\3\\56", "$i$a$-forEach-BackupManager$importData$2$1$1$1\\4\\70\\2", "inputStream\\1", "reader\\2", "backupData\\2", "$this$forEach\\5", "element\\5", "it\\6", "$i$a$-use-BackupManager$importData$2$1\\1\\50\\0", "$i$a$-use-BackupManager$importData$2$1$1\\2\\51\\1", "$i$f$forEach\\5\\57", "$i$a$-forEach-BackupManager$importData$2$1$1$2\\6\\72\\2", "inputStream\\1", "reader\\2", "backupData\\2", "$this$forEach\\7", "element\\7", "it\\8", "$i$a$-use-BackupManager$importData$2$1\\1\\50\\0", "$i$a$-use-BackupManager$importData$2$1$1\\2\\51\\1", "$i$f$forEach\\7\\58", "$i$a$-forEach-BackupManager$importData$2$1$1$3\\8\\74\\2", "inputStream\\1", "reader\\2", "backupData\\2", "it\\9", "$i$a$-use-BackupManager$importData$2$1\\1\\50\\0", "$i$a$-use-BackupManager$importData$2$1$1\\2\\51\\1", "$i$a$-let-BackupManager$importData$2$1$1$4\\9\\59\\2"}, nl = {56, 70, 72, 74, 59}, s = {"L$2", "L$4", "L$5", "I$0", "I$1", "L$2", "L$4", "L$5", "L$6", "L$8", "L$9", "I$0", "I$1", "I$2", "I$3", "L$2", "L$4", "L$5", "L$6", "L$8", "L$9", "I$0", "I$1", "I$2", "I$3", "L$2", "L$4", "L$5", "L$6", "L$8", "L$9", "I$0", "I$1", "I$2", "I$3", "L$1", "L$3", "L$4", "L$5", "I$0", "I$1", "I$2"}, v = 2)
    static final class C03992 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Boolean>, Object> {
        final /* synthetic */ Uri $uri;
        int I$0;
        int I$1;
        int I$2;
        int I$3;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        Object L$9;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03992(Uri uri, Continuation<? super C03992> continuation) {
            super(2, continuation);
            this.$uri = uri;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return BackupManager.this.new C03992(this.$uri, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Boolean> continuation) {
            return ((C03992) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Not initialized variable reg: 12, insn: 0x0107: MOVE (r9 I:??[OBJECT, ARRAY]) = (r12 I:??[OBJECT, ARRAY]), block:B:21:0x0105 */
        /* JADX WARN: Not initialized variable reg: 13, insn: 0x0108: MOVE (r10 I:??[OBJECT, ARRAY]) = (r13 I:??[OBJECT, ARRAY] A[D('inputStream\1' java.io.InputStream)]), block:B:21:0x0105 */
        /* JADX WARN: Not initialized variable reg: 15, insn: 0x0109: MOVE (r11 I:??[OBJECT, ARRAY]) = (r15 I:??[OBJECT, ARRAY]), block:B:21:0x0105 */
        /* JADX WARN: Removed duplicated region for block: B:39:0x01bb A[Catch: all -> 0x0394, TRY_LEAVE, TryCatch #11 {all -> 0x0394, blocks: (B:37:0x01b5, B:39:0x01bb), top: B:134:0x01b5 }] */
        /* JADX WARN: Removed duplicated region for block: B:46:0x021c A[Catch: all -> 0x038f, TRY_LEAVE, TryCatch #6 {all -> 0x038f, blocks: (B:41:0x01d1, B:46:0x021c), top: B:128:0x01d1 }] */
        /* JADX WARN: Removed duplicated region for block: B:50:0x0234 A[Catch: all -> 0x0386, TRY_LEAVE, TryCatch #3 {all -> 0x0386, blocks: (B:48:0x022e, B:50:0x0234), top: B:124:0x022e }] */
        /* JADX WARN: Removed duplicated region for block: B:57:0x028b A[Catch: all -> 0x0384, TRY_LEAVE, TryCatch #1 {all -> 0x0384, blocks: (B:52:0x0248, B:57:0x028b), top: B:121:0x0248 }] */
        /* JADX WARN: Removed duplicated region for block: B:61:0x02ab A[Catch: all -> 0x037b, TRY_LEAVE, TryCatch #14 {all -> 0x037b, blocks: (B:59:0x02a5, B:61:0x02ab), top: B:140:0x02a5 }] */
        /* JADX WARN: Removed duplicated region for block: B:68:0x030a A[Catch: all -> 0x0374, TryCatch #12 {all -> 0x0374, blocks: (B:63:0x02b5, B:68:0x030a, B:70:0x0314), top: B:136:0x02b5 }] */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:44:0x0209 -> B:45:0x0211). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:55:0x027e -> B:56:0x0287). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:66:0x02f5 -> B:67:0x0303). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r22) {
            /*
                Method dump skipped, instruction units count: 986
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.example.scheduleorganizer.util.BackupManager.C03992.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public final Object importData(Uri uri, Continuation<? super Boolean> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new C03992(uri, null), continuation);
    }
}
