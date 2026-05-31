package androidx.room.util;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.room.PooledConnection;
import androidx.room.RoomDatabase;
import androidx.sqlite.SQLiteConnection;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteQuery;
import java.io.File;
import java.io.IOException;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"androidx/room/util/DBUtil__DBUtilKt", "androidx/room/util/DBUtil__DBUtil_androidKt"}, k = 4, mv = {2, 1, 0}, xi = 48)
public final class DBUtil {
    @Deprecated(message = "Use constructor", replaceWith = @ReplaceWith(expression = "CancellationSignal()", imports = {"android.os.CancellationSignal"}))
    public static final CancellationSignal createCancellationSignal() {
        return DBUtil__DBUtil_androidKt.createCancellationSignal();
    }

    public static final void dropFtsSyncTriggers(SQLiteConnection connection) {
        DBUtil__DBUtilKt.dropFtsSyncTriggers(connection);
    }

    @Deprecated(message = "Replaced by dropFtsSyncTriggers(connection: SQLiteConnection)")
    public static final void dropFtsSyncTriggers(SupportSQLiteDatabase db) {
        DBUtil__DBUtil_androidKt.dropFtsSyncTriggers(db);
    }

    public static final void foreignKeyCheck(SQLiteConnection db, String tableName) {
        DBUtil__DBUtilKt.foreignKeyCheck(db, tableName);
    }

    public static final void foreignKeyCheck(SupportSQLiteDatabase db, String tableName) {
        DBUtil__DBUtil_androidKt.foreignKeyCheck(db, tableName);
    }

    public static final Object getCoroutineContext(RoomDatabase $this$getCoroutineContext, boolean inTransaction, Continuation<? super CoroutineContext> continuation) {
        return DBUtil__DBUtil_androidKt.getCoroutineContext($this$getCoroutineContext, inTransaction, continuation);
    }

    public static final <R> Object internalPerform(RoomDatabase $this$internalPerform, boolean isReadOnly, boolean inTransaction, Function2<? super PooledConnection, ? super Continuation<? super R>, ? extends Object> function2, Continuation<? super R> continuation) {
        return DBUtil__DBUtilKt.internalPerform($this$internalPerform, isReadOnly, inTransaction, function2, continuation);
    }

    public static final <R> R performBlocking(RoomDatabase roomDatabase, boolean z, boolean z2, Function1<? super SQLiteConnection, ? extends R> function1) {
        return (R) DBUtil__DBUtil_androidKt.performBlocking(roomDatabase, z, z2, function1);
    }

    public static final <R> Object performInTransactionSuspending(RoomDatabase db, Function1<? super Continuation<? super R>, ? extends Object> function1, Continuation<? super R> continuation) {
        return DBUtil__DBUtil_androidKt.performInTransactionSuspending(db, function1, continuation);
    }

    public static final <R> Object performSuspending(RoomDatabase db, boolean isReadOnly, boolean inTransaction, Function1<? super SQLiteConnection, ? extends R> function1, Continuation<? super R> continuation) {
        return DBUtil__DBUtil_androidKt.performSuspending(db, isReadOnly, inTransaction, function1, continuation);
    }

    @Deprecated(message = "This is only used in the generated code and shouldn't be called directly.")
    public static final Cursor query(RoomDatabase db, SupportSQLiteQuery sqLiteQuery, boolean maybeCopy) {
        return DBUtil__DBUtil_androidKt.query(db, sqLiteQuery, maybeCopy);
    }

    public static final Cursor query(RoomDatabase db, SupportSQLiteQuery sqLiteQuery, boolean maybeCopy, CancellationSignal signal) {
        return DBUtil__DBUtil_androidKt.query(db, sqLiteQuery, maybeCopy, signal);
    }

    public static final int readVersion(File databaseFile) throws IOException {
        return DBUtil__DBUtil_androidKt.readVersion(databaseFile);
    }

    public static final SQLiteConnection toSQLiteConnection(SupportSQLiteDatabase db) {
        return DBUtil__DBUtil_androidKt.toSQLiteConnection(db);
    }
}
