package androidx.sqlite;

import kotlin.Metadata;

/* JADX INFO: compiled from: SQLiteDriver.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH&R\u001a\u0010\u0002\u001a\u00020\u00038WX\u0096\u0004¢\u0006\f\u0012\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0002\u0010\u0006ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000bÀ\u0006\u0001"}, d2 = {"Landroidx/sqlite/SQLiteDriver;", "", "hasConnectionPool", "", "hasConnectionPool$annotations", "()V", "()Z", "open", "Landroidx/sqlite/SQLiteConnection;", "fileName", "", "sqlite"}, k = 1, mv = {2, 1, 0}, xi = 48)
public interface SQLiteDriver {
    static /* synthetic */ void hasConnectionPool$annotations() {
    }

    SQLiteConnection open(String fileName);

    default boolean hasConnectionPool() {
        return false;
    }
}
