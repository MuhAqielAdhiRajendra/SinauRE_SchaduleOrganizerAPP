package androidx.room.util;

import androidx.sqlite.SQLiteStatement;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"androidx/room/util/SQLiteStatementUtil__StatementUtilKt", "androidx/room/util/SQLiteStatementUtil__StatementUtil_androidKt"}, k = 4, mv = {2, 1, 0}, xi = 48)
public final class SQLiteStatementUtil {
    public static final int columnIndexOf(SQLiteStatement $this$columnIndexOf, String name) {
        return SQLiteStatementUtil__StatementUtil_androidKt.columnIndexOf($this$columnIndexOf, name);
    }

    public static final int columnIndexOfCommon(SQLiteStatement $this$columnIndexOfCommon, String name) {
        return SQLiteStatementUtil__StatementUtilKt.columnIndexOfCommon($this$columnIndexOfCommon, name);
    }

    public static final int getColumnIndex(SQLiteStatement stmt, String name) {
        return SQLiteStatementUtil__StatementUtilKt.getColumnIndex(stmt, name);
    }

    public static final int getColumnIndexOrThrow(SQLiteStatement stmt, String name) {
        return SQLiteStatementUtil__StatementUtilKt.getColumnIndexOrThrow(stmt, name);
    }

    public static final SQLiteStatement wrapMappedColumns(SQLiteStatement statement, String[] columnNames, int[] mapping) {
        return SQLiteStatementUtil__StatementUtilKt.wrapMappedColumns(statement, columnNames, mapping);
    }
}
