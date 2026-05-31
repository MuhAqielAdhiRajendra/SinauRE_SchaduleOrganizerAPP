package androidx.room.util;

import android.database.SQLException;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.room.PooledConnection;
import androidx.room.RoomDatabase;
import androidx.room.Transactor;
import androidx.savedstate.serialization.ClassDiscriminatorModeKt;
import androidx.sqlite.SQLite;
import androidx.sqlite.SQLiteConnection;
import androidx.sqlite.SQLiteStatement;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jdk7.AutoCloseableKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: DBUtil.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u0000D\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001aN\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042$\b\u0004\u0010\u0006\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\t\u0012\u0006\u0012\u0004\u0018\u00010\n0\u0007H\u0080H¢\u0006\u0002\u0010\u000b\u001a\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0007\u001a\u0018\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u0013H\u0007\u001a\u0015\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u0016H\u0002¢\u0006\u0002\b\u0017¨\u0006\u0018"}, d2 = {"internalPerform", "R", "Landroidx/room/RoomDatabase;", "isReadOnly", "", "inTransaction", "block", "Lkotlin/Function2;", "Landroidx/room/PooledConnection;", "Lkotlin/coroutines/Continuation;", "", "(Landroidx/room/RoomDatabase;ZZLkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "dropFtsSyncTriggers", "", "connection", "Landroidx/sqlite/SQLiteConnection;", "foreignKeyCheck", "db", "tableName", "", "processForeignKeyCheckFailure", "stmt", "Landroidx/sqlite/SQLiteStatement;", "processForeignKeyCheckFailure$DBUtil__DBUtilKt", "room-runtime"}, k = 5, mv = {2, 1, 0}, xi = 48, xs = "androidx/room/util/DBUtil")
final /* synthetic */ class DBUtil__DBUtilKt {

    /* JADX INFO: Add missing generic type declarations: [R] */
    /* JADX INFO: renamed from: androidx.room.util.DBUtil__DBUtilKt$internalPerform$2, reason: invalid class name */
    /* JADX INFO: compiled from: DBUtil.kt */
    @Metadata(d1 = {"\u0000\b\n\u0002\b\u0003\n\u0002\u0018\u0002\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "R", "transactor", "Landroidx/room/Transactor;"}, k = 3, mv = {2, 1, 0}, xi = 176)
    @DebugMetadata(c = "androidx.room.util.DBUtil__DBUtilKt$internalPerform$2", f = "DBUtil.kt", i = {0, 0, 1, 1, 2, 3}, l = {56, 57, 59, 60, ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_HEIGHT}, m = "invokeSuspend", n = {"transactor", ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY, "transactor", ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY, "transactor", "result"}, s = {"L$0", "L$1", "L$0", "L$1", "L$0", "L$0"})
    public static final class AnonymousClass2<R> extends SuspendLambda implements Function2<Transactor, Continuation<? super R>, Object> {
        final /* synthetic */ Function2<PooledConnection, Continuation<? super R>, Object> $block;
        final /* synthetic */ boolean $inTransaction;
        final /* synthetic */ boolean $isReadOnly;
        final /* synthetic */ RoomDatabase $this_internalPerform;
        /* synthetic */ Object L$0;
        Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass2(boolean z, boolean z2, RoomDatabase roomDatabase, Function2<? super PooledConnection, ? super Continuation<? super R>, ? extends Object> function2, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$inTransaction = z;
            this.$isReadOnly = z2;
            this.$this_internalPerform = roomDatabase;
            this.$block = function2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$inTransaction, this.$isReadOnly, this.$this_internalPerform, this.$block, continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Transactor transactor, Continuation<? super R> continuation) {
            return ((AnonymousClass2) create(transactor, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:25:0x007d  */
        /* JADX WARN: Removed duplicated region for block: B:30:0x009b  */
        /* JADX WARN: Removed duplicated region for block: B:33:0x00b7 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:34:0x00b8  */
        /* JADX WARN: Removed duplicated region for block: B:37:0x00c1  */
        /* JADX WARN: Removed duplicated region for block: B:43:0x00da  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r8) {
            /*
                Method dump skipped, instruction units count: 262
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.room.util.DBUtil__DBUtilKt.AnonymousClass2.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        public final Object invokeSuspend$$forInline(Object $result) {
            Transactor.SQLiteTransactionType type;
            Transactor transactor = (Transactor) this.L$0;
            if (this.$inTransaction) {
                if (this.$isReadOnly) {
                    type = Transactor.SQLiteTransactionType.DEFERRED;
                } else {
                    type = Transactor.SQLiteTransactionType.IMMEDIATE;
                }
                if (!this.$isReadOnly && !((Boolean) transactor.inTransaction(this)).booleanValue()) {
                    this.$this_internalPerform.getInvalidationTracker().sync$room_runtime(this);
                }
                Object objWithTransaction = transactor.withTransaction(type, new DBUtil__DBUtilKt$internalPerform$2$result$1(this.$block, null), this);
                if (!this.$isReadOnly && !((Boolean) transactor.inTransaction(this)).booleanValue()) {
                    this.$this_internalPerform.getInvalidationTracker().refreshAsync();
                    return objWithTransaction;
                }
                return objWithTransaction;
            }
            return this.$block.invoke(transactor, this);
        }
    }

    public static final <R> Object internalPerform(RoomDatabase $this$internalPerform, boolean isReadOnly, boolean inTransaction, Function2<? super PooledConnection, ? super Continuation<? super R>, ? extends Object> function2, Continuation<? super R> continuation) {
        return $this$internalPerform.useConnection(isReadOnly, new AnonymousClass2(inTransaction, isReadOnly, $this$internalPerform, function2, null), continuation);
    }

    public static final void dropFtsSyncTriggers(SQLiteConnection connection) {
        Intrinsics.checkNotNullParameter(connection, "connection");
        List $this$dropFtsSyncTriggers_u24lambda_u241 = CollectionsKt.createListBuilder();
        SQLiteStatement sQLiteStatementPrepare = connection.prepare("SELECT name FROM sqlite_master WHERE type = 'trigger'");
        try {
            SQLiteStatement it = sQLiteStatementPrepare;
            while (it.step()) {
                $this$dropFtsSyncTriggers_u24lambda_u241.add(it.getText(0));
            }
            Unit unit = Unit.INSTANCE;
            AutoCloseableKt.closeFinally(sQLiteStatementPrepare, null);
            Iterable existingTriggers = CollectionsKt.build($this$dropFtsSyncTriggers_u24lambda_u241);
            Iterable $this$forEach$iv = existingTriggers;
            for (Object element$iv : $this$forEach$iv) {
                String triggerName = (String) element$iv;
                if (StringsKt.startsWith$default(triggerName, "room_fts_content_sync_", false, 2, (Object) null)) {
                    SQLite.execSQL(connection, "DROP TRIGGER IF EXISTS " + triggerName);
                }
            }
        } finally {
        }
    }

    public static final void foreignKeyCheck(SQLiteConnection db, String tableName) {
        Intrinsics.checkNotNullParameter(db, "db");
        Intrinsics.checkNotNullParameter(tableName, "tableName");
        SQLiteStatement sQLiteStatementPrepare = db.prepare("PRAGMA foreign_key_check(`" + tableName + "`)");
        try {
            SQLiteStatement stmt = sQLiteStatementPrepare;
            if (!stmt.step()) {
                Unit unit = Unit.INSTANCE;
                AutoCloseableKt.closeFinally(sQLiteStatementPrepare, null);
            } else {
                String errorMsg = processForeignKeyCheckFailure$DBUtil__DBUtilKt(stmt);
                throw new SQLException(errorMsg);
            }
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                AutoCloseableKt.closeFinally(sQLiteStatementPrepare, th);
                throw th2;
            }
        }
    }

    private static final String processForeignKeyCheckFailure$DBUtil__DBUtilKt(SQLiteStatement stmt) {
        StringBuilder $this$processForeignKeyCheckFailure_u24lambda_u244 = new StringBuilder();
        int rowCount = 0;
        Map fkParentTables = new LinkedHashMap();
        do {
            if (rowCount == 0) {
                $this$processForeignKeyCheckFailure_u24lambda_u244.append("Foreign key violation(s) detected in '");
                $this$processForeignKeyCheckFailure_u24lambda_u244.append(stmt.getText(0)).append("'.\n");
            }
            String constraintIndex = stmt.getText(3);
            if (!fkParentTables.containsKey(constraintIndex)) {
                fkParentTables.put(constraintIndex, stmt.getText(2));
            }
            rowCount++;
        } while (stmt.step());
        $this$processForeignKeyCheckFailure_u24lambda_u244.append("Number of different violations discovered: ");
        $this$processForeignKeyCheckFailure_u24lambda_u244.append(fkParentTables.keySet().size()).append("\n");
        $this$processForeignKeyCheckFailure_u24lambda_u244.append("Number of rows in violation: ");
        $this$processForeignKeyCheckFailure_u24lambda_u244.append(rowCount).append("\n");
        $this$processForeignKeyCheckFailure_u24lambda_u244.append("Violation(s) detected in the following constraint(s):\n");
        for (Map.Entry entry : fkParentTables.entrySet()) {
            String key = (String) entry.getKey();
            String value = (String) entry.getValue();
            $this$processForeignKeyCheckFailure_u24lambda_u244.append("\tParent Table = ");
            $this$processForeignKeyCheckFailure_u24lambda_u244.append(value);
            $this$processForeignKeyCheckFailure_u24lambda_u244.append(", Foreign Key Constraint Index = ");
            $this$processForeignKeyCheckFailure_u24lambda_u244.append(key).append("\n");
        }
        return $this$processForeignKeyCheckFailure_u24lambda_u244.toString();
    }
}
