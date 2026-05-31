package androidx.room.coroutines;

import androidx.sqlite.SQLiteDriver;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ConnectionPool.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\u001a\"\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u0000\u001a2\u0010\b\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u00072\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u0000¨\u0006\u000b"}, d2 = {"newSingleConnectionPool", "Landroidx/room/coroutines/ConnectionPool;", "driver", "Landroidx/sqlite/SQLiteDriver;", "fileName", "", "preparedStatementCacheSize", "", "newConnectionPool", "maxNumOfReaders", "maxNumOfWriters", "room-runtime"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class ConnectionPoolKt {
    public static /* synthetic */ ConnectionPool newSingleConnectionPool$default(SQLiteDriver sQLiteDriver, String str, int i, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            i = 25;
        }
        return newSingleConnectionPool(sQLiteDriver, str, i);
    }

    public static final ConnectionPool newSingleConnectionPool(SQLiteDriver driver, String fileName, int preparedStatementCacheSize) {
        Intrinsics.checkNotNullParameter(driver, "driver");
        Intrinsics.checkNotNullParameter(fileName, "fileName");
        return new ConnectionPoolImpl(driver, fileName, preparedStatementCacheSize);
    }

    public static /* synthetic */ ConnectionPool newConnectionPool$default(SQLiteDriver sQLiteDriver, String str, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 16) != 0) {
            i3 = 25;
        }
        return newConnectionPool(sQLiteDriver, str, i, i2, i3);
    }

    public static final ConnectionPool newConnectionPool(SQLiteDriver driver, String fileName, int maxNumOfReaders, int maxNumOfWriters, int preparedStatementCacheSize) {
        Intrinsics.checkNotNullParameter(driver, "driver");
        Intrinsics.checkNotNullParameter(fileName, "fileName");
        return new ConnectionPoolImpl(driver, fileName, maxNumOfReaders, maxNumOfWriters, preparedStatementCacheSize);
    }
}
