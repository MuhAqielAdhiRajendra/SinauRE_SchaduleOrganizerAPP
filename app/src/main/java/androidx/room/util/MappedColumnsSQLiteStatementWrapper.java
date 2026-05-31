package androidx.room.util;

import androidx.autofill.HintConstants;
import androidx.sqlite.SQLiteStatement;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: StatementUtil.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010 \n\u0002\b\n\b\u0000\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u000e\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u0005J\u001b\u0010\u0010\u001a\u00020\u00112\b\b\u0001\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\u0014H\u0096\u0001J\u001b\u0010\u0015\u001a\u00020\u00112\b\b\u0001\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\u0016H\u0096\u0001J\u001b\u0010\u0017\u001a\u00020\u00112\b\b\u0001\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\u0018H\u0096\u0001J\u001b\u0010\u0019\u001a\u00020\u00112\b\b\u0001\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\u001aH\u0096\u0001J\u001b\u0010\u001b\u001a\u00020\u00112\b\b\u0001\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\rH\u0096\u0001J\u001b\u0010\u001c\u001a\u00020\u00112\b\b\u0001\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\u001dH\u0096\u0001J\u0013\u0010\u001e\u001a\u00020\u00112\b\b\u0001\u0010\u0012\u001a\u00020\rH\u0096\u0001J\u001b\u0010\u001f\u001a\u00020\u00112\b\b\u0001\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\u0005H\u0096\u0001J\t\u0010 \u001a\u00020\u0011H\u0096\u0001J\t\u0010!\u001a\u00020\u0011H\u0096\u0001J\u0013\u0010\"\u001a\u00020\u00142\b\b\u0001\u0010\u0012\u001a\u00020\rH\u0096\u0001J\u0013\u0010#\u001a\u00020\u00162\b\b\u0001\u0010\u0012\u001a\u00020\rH\u0096\u0001J\t\u0010$\u001a\u00020\rH\u0096\u0001J\u0013\u0010%\u001a\u00020\u00052\b\b\u0001\u0010\u0012\u001a\u00020\rH\u0096\u0001J\u000f\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00050'H\u0096\u0001J\u0013\u0010(\u001a\u00020\r2\b\b\u0001\u0010\u0012\u001a\u00020\rH\u0096\u0001J\u0013\u0010)\u001a\u00020\u00182\b\b\u0001\u0010\u0012\u001a\u00020\rH\u0096\u0001J\u0013\u0010*\u001a\u00020\u001a2\b\b\u0001\u0010\u0012\u001a\u00020\rH\u0096\u0001J\u0013\u0010+\u001a\u00020\r2\b\b\u0001\u0010\u0012\u001a\u00020\rH\u0096\u0001J\u0013\u0010,\u001a\u00020\u001d2\b\b\u0001\u0010\u0012\u001a\u00020\rH\u0096\u0001J\u0013\u0010-\u001a\u00020\u00052\b\b\u0001\u0010\u0012\u001a\u00020\rH\u0096\u0001J\u0013\u0010.\u001a\u00020\u00162\b\b\u0001\u0010\u0012\u001a\u00020\rH\u0096\u0001J\t\u0010/\u001a\u00020\u0011H\u0096\u0001J\t\u00100\u001a\u00020\u0016H\u0096\u0001R\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\nR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\r0\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u00061"}, d2 = {"Landroidx/room/util/MappedColumnsSQLiteStatementWrapper;", "Landroidx/sqlite/SQLiteStatement;", "delegate", "columnNames", "", "", "mapping", "", "<init>", "(Landroidx/sqlite/SQLiteStatement;[Ljava/lang/String;[I)V", "[Ljava/lang/String;", "columnNameToIndexMap", "", "", "getColumnIndex", HintConstants.AUTOFILL_HINT_NAME, "bindBlob", "", "index", "value", "", "bindBoolean", "", "bindDouble", "", "bindFloat", "", "bindInt", "bindLong", "", "bindNull", "bindText", "clearBindings", "close", "getBlob", "getBoolean", "getColumnCount", "getColumnName", "getColumnNames", "", "getColumnType", "getDouble", "getFloat", "getInt", "getLong", "getText", "isNull", "reset", "step", "room-runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class MappedColumnsSQLiteStatementWrapper implements SQLiteStatement {
    private final Map<String, Integer> columnNameToIndexMap;
    private final String[] columnNames;
    private final SQLiteStatement delegate;
    private final int[] mapping;

    @Override // androidx.sqlite.SQLiteStatement
    /* JADX INFO: renamed from: bindBlob */
    public void mo8513bindBlob(int index, byte[] value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.delegate.mo8513bindBlob(index, value);
    }

    @Override // androidx.sqlite.SQLiteStatement
    public void bindBoolean(int index, boolean value) {
        this.delegate.bindBoolean(index, value);
    }

    @Override // androidx.sqlite.SQLiteStatement
    /* JADX INFO: renamed from: bindDouble */
    public void mo8514bindDouble(int index, double value) {
        this.delegate.mo8514bindDouble(index, value);
    }

    @Override // androidx.sqlite.SQLiteStatement
    public void bindFloat(int index, float value) {
        this.delegate.bindFloat(index, value);
    }

    @Override // androidx.sqlite.SQLiteStatement
    public void bindInt(int index, int value) {
        this.delegate.bindInt(index, value);
    }

    @Override // androidx.sqlite.SQLiteStatement
    /* JADX INFO: renamed from: bindLong */
    public void mo8515bindLong(int index, long value) {
        this.delegate.mo8515bindLong(index, value);
    }

    @Override // androidx.sqlite.SQLiteStatement
    /* JADX INFO: renamed from: bindNull */
    public void mo8516bindNull(int index) {
        this.delegate.mo8516bindNull(index);
    }

    @Override // androidx.sqlite.SQLiteStatement
    /* JADX INFO: renamed from: bindText */
    public void mo8517bindText(int index, String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.delegate.mo8517bindText(index, value);
    }

    @Override // androidx.sqlite.SQLiteStatement
    /* JADX INFO: renamed from: clearBindings */
    public void mo8518clearBindings() {
        this.delegate.mo8518clearBindings();
    }

    @Override // androidx.sqlite.SQLiteStatement, java.lang.AutoCloseable
    public void close() {
        this.delegate.close();
    }

    @Override // androidx.sqlite.SQLiteStatement
    public byte[] getBlob(int index) {
        return this.delegate.getBlob(index);
    }

    @Override // androidx.sqlite.SQLiteStatement
    public boolean getBoolean(int index) {
        return this.delegate.getBoolean(index);
    }

    @Override // androidx.sqlite.SQLiteStatement
    public int getColumnCount() {
        return this.delegate.getColumnCount();
    }

    @Override // androidx.sqlite.SQLiteStatement
    public String getColumnName(int index) {
        return this.delegate.getColumnName(index);
    }

    @Override // androidx.sqlite.SQLiteStatement
    public List<String> getColumnNames() {
        return this.delegate.getColumnNames();
    }

    @Override // androidx.sqlite.SQLiteStatement
    public int getColumnType(int index) {
        return this.delegate.getColumnType(index);
    }

    @Override // androidx.sqlite.SQLiteStatement
    public double getDouble(int index) {
        return this.delegate.getDouble(index);
    }

    @Override // androidx.sqlite.SQLiteStatement
    public float getFloat(int index) {
        return this.delegate.getFloat(index);
    }

    @Override // androidx.sqlite.SQLiteStatement
    public int getInt(int index) {
        return this.delegate.getInt(index);
    }

    @Override // androidx.sqlite.SQLiteStatement
    public long getLong(int index) {
        return this.delegate.getLong(index);
    }

    @Override // androidx.sqlite.SQLiteStatement
    public String getText(int index) {
        return this.delegate.getText(index);
    }

    @Override // androidx.sqlite.SQLiteStatement
    public boolean isNull(int index) {
        return this.delegate.isNull(index);
    }

    @Override // androidx.sqlite.SQLiteStatement
    public void reset() {
        this.delegate.reset();
    }

    @Override // androidx.sqlite.SQLiteStatement
    public boolean step() {
        return this.delegate.step();
    }

    public MappedColumnsSQLiteStatementWrapper(SQLiteStatement delegate, String[] columnNames, int[] mapping) {
        Intrinsics.checkNotNullParameter(delegate, "delegate");
        Intrinsics.checkNotNullParameter(columnNames, "columnNames");
        Intrinsics.checkNotNullParameter(mapping, "mapping");
        this.delegate = delegate;
        this.columnNames = columnNames;
        this.mapping = mapping;
        int i = 0;
        if (!(this.columnNames.length == this.mapping.length)) {
            throw new IllegalArgumentException("Expected columnNames.size == mapping.size".toString());
        }
        Map $this$columnNameToIndexMap_u24lambda_u242 = MapsKt.createMapBuilder();
        String[] strArr = this.columnNames;
        int index$iv = 0;
        int length = strArr.length;
        while (i < length) {
            $this$columnNameToIndexMap_u24lambda_u242.put(strArr[i], Integer.valueOf(this.mapping[index$iv]));
            i++;
            index$iv++;
        }
        int columnCount = getColumnCount();
        for (int i2 = 0; i2 < columnCount; i2++) {
            String name = getColumnName(i2);
            if (!$this$columnNameToIndexMap_u24lambda_u242.containsKey(name)) {
                $this$columnNameToIndexMap_u24lambda_u242.put(getColumnName(i2), Integer.valueOf(i2));
            }
        }
        this.columnNameToIndexMap = MapsKt.build($this$columnNameToIndexMap_u24lambda_u242);
    }

    public final int getColumnIndex(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        Integer num = this.columnNameToIndexMap.get(name);
        if (num != null) {
            return num.intValue();
        }
        return -1;
    }
}
