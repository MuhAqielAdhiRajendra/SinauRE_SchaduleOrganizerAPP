package androidx.room.coroutines;

import androidx.collection.LruCache;
import androidx.sqlite.SQLiteConnection;
import androidx.sqlite.SQLiteStatement;
import java.util.List;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.selects.SelectClause2;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;

/* JADX INFO: compiled from: ConnectionPoolImpl.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002:\u0002+,B!\u0012\u0006\u0010\u0003\u001a\u00020\u0001\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\u000e\u0010\u0015\u001a\u00020\u00002\u0006\u0010\u0016\u001a\u00020\nJ\u0006\u0010\u0017\u001a\u00020\u0000J\u0012\u0010\u0018\u001a\u00020\u00142\n\u0010\u0019\u001a\u00060\u001aj\u0002`\u001bJ\b\u0010\u001c\u001a\u00020\u0012H\u0016J\u0011\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 H\u0096\u0001J\t\u0010!\u001a\u00020\u001eH\u0096\u0001J\u0018\u0010\u0004\u001a\u00020\u00142\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0096A¢\u0006\u0002\u0010\"J\u0013\u0010#\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0096\u0001J\u0013\u0010$\u001a\u00020\u00142\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0096\u0001R\u000e\u0010\u0003\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0002X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0018\u00010\u000eR\u00020\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010%\u001a\u00020\u001eX\u0096\u0005¢\u0006\u0006\u001a\u0004\b%\u0010&R\"\u0010'\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010 \u0012\u0004\u0012\u00020\u00020(8\u0016X\u0097\u0005¢\u0006\u0006\u001a\u0004\b)\u0010*¨\u0006-"}, d2 = {"Landroidx/room/coroutines/ConnectionWithLock;", "Landroidx/sqlite/SQLiteConnection;", "Lkotlinx/coroutines/sync/Mutex;", "delegate", "lock", "preparedStatementCacheSize", "", "<init>", "(Landroidx/sqlite/SQLiteConnection;Lkotlinx/coroutines/sync/Mutex;I)V", "acquireCoroutineContext", "Lkotlin/coroutines/CoroutineContext;", "acquireThrowable", "", "preparedStatementCache", "Landroidx/room/coroutines/ConnectionWithLock$PreparedStatementCache;", "prepare", "Landroidx/sqlite/SQLiteStatement;", "sql", "", "close", "", "markAcquired", "context", "markReleased", "dump", "builder", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "toString", "holdsLock", "", "owner", "", "inTransaction", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "tryLock", "unlock", "isLocked", "()Z", "onLock", "Lkotlinx/coroutines/selects/SelectClause2;", "getOnLock", "()Lkotlinx/coroutines/selects/SelectClause2;", "PreparedStatementCache", "CachedStatement", "room-runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
final class ConnectionWithLock implements SQLiteConnection, Mutex {
    private CoroutineContext acquireCoroutineContext;
    private Throwable acquireThrowable;
    private final SQLiteConnection delegate;
    private final Mutex lock;
    private final PreparedStatementCache preparedStatementCache;

    @Override // kotlinx.coroutines.sync.Mutex
    public SelectClause2<Object, Mutex> getOnLock() {
        return this.lock.getOnLock();
    }

    @Override // kotlinx.coroutines.sync.Mutex
    public boolean holdsLock(Object owner) {
        Intrinsics.checkNotNullParameter(owner, "owner");
        return this.lock.holdsLock(owner);
    }

    @Override // androidx.sqlite.SQLiteConnection
    public boolean inTransaction() {
        return this.delegate.inTransaction();
    }

    @Override // kotlinx.coroutines.sync.Mutex
    public boolean isLocked() {
        return this.lock.isLocked();
    }

    @Override // kotlinx.coroutines.sync.Mutex
    public Object lock(Object obj, Continuation<? super Unit> continuation) {
        return this.lock.lock(obj, continuation);
    }

    @Override // kotlinx.coroutines.sync.Mutex
    public boolean tryLock(Object owner) {
        return this.lock.tryLock(owner);
    }

    @Override // kotlinx.coroutines.sync.Mutex
    public void unlock(Object owner) {
        this.lock.unlock(owner);
    }

    public ConnectionWithLock(SQLiteConnection delegate, Mutex lock, int preparedStatementCacheSize) {
        PreparedStatementCache preparedStatementCache;
        Intrinsics.checkNotNullParameter(delegate, "delegate");
        Intrinsics.checkNotNullParameter(lock, "lock");
        this.delegate = delegate;
        this.lock = lock;
        if (preparedStatementCacheSize > 0) {
            preparedStatementCache = new PreparedStatementCache(preparedStatementCacheSize);
        } else {
            preparedStatementCache = null;
        }
        this.preparedStatementCache = preparedStatementCache;
    }

    public /* synthetic */ ConnectionWithLock(SQLiteConnection sQLiteConnection, Mutex mutex, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(sQLiteConnection, (i2 & 2) != 0 ? MutexKt.Mutex$default(false, 1, null) : mutex, i);
    }

    @Override // androidx.sqlite.SQLiteConnection
    public SQLiteStatement prepare(String sql) {
        Intrinsics.checkNotNullParameter(sql, "sql");
        if (this.preparedStatementCache != null) {
            SQLiteStatement sQLiteStatement = this.preparedStatementCache.get(sql);
            Intrinsics.checkNotNull(sQLiteStatement);
            return new CachedStatement(sQLiteStatement);
        }
        return this.delegate.prepare(sql);
    }

    @Override // androidx.sqlite.SQLiteConnection, java.lang.AutoCloseable
    public void close() {
        PreparedStatementCache preparedStatementCache = this.preparedStatementCache;
        if (preparedStatementCache != null) {
            preparedStatementCache.evictAll();
        }
        this.delegate.close();
    }

    public final ConnectionWithLock markAcquired(CoroutineContext context) {
        Intrinsics.checkNotNullParameter(context, "context");
        ConnectionWithLock $this$markAcquired_u24lambda_u240 = this;
        $this$markAcquired_u24lambda_u240.acquireCoroutineContext = context;
        $this$markAcquired_u24lambda_u240.acquireThrowable = new Throwable();
        return this;
    }

    public final ConnectionWithLock markReleased() {
        ConnectionWithLock $this$markReleased_u24lambda_u241 = this;
        $this$markReleased_u24lambda_u241.acquireCoroutineContext = null;
        $this$markReleased_u24lambda_u241.acquireThrowable = null;
        return this;
    }

    public final void dump(StringBuilder builder) {
        Intrinsics.checkNotNullParameter(builder, "builder");
        if (this.acquireCoroutineContext != null || this.acquireThrowable != null) {
            builder.append("\t\tStatus: Acquired connection").append('\n');
            CoroutineContext it = this.acquireCoroutineContext;
            if (it != null) {
                builder.append("\t\tCoroutine: " + it).append('\n');
            }
            Throwable it2 = this.acquireThrowable;
            if (it2 != null) {
                builder.append("\t\tAcquired:").append('\n');
                Iterable $this$forEach$iv = CollectionsKt.drop(StringsKt.lines(ExceptionsKt.stackTraceToString(it2)), 1);
                for (Object element$iv : $this$forEach$iv) {
                    String line = (String) element$iv;
                    builder.append("\t\t" + line).append('\n');
                }
            }
        } else {
            builder.append("\t\tStatus: Free connection").append('\n');
        }
        if (this.preparedStatementCache != null) {
            builder.append("\t\tPrepared Statement Cache Size: " + this.preparedStatementCache.size()).append('\n');
        }
    }

    public String toString() {
        return this.delegate.toString();
    }

    /* JADX INFO: compiled from: ConnectionPoolImpl.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0082\u0004\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0011\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0002H\u0014J*\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u00032\b\u0010\u000f\u001a\u0004\u0018\u00010\u0003H\u0014¨\u0006\u0010"}, d2 = {"Landroidx/room/coroutines/ConnectionWithLock$PreparedStatementCache;", "Landroidx/collection/LruCache;", "", "Landroidx/sqlite/SQLiteStatement;", "maxSize", "", "<init>", "(Landroidx/room/coroutines/ConnectionWithLock;I)V", "create", "key", "entryRemoved", "", "evicted", "", "oldValue", "newValue", "room-runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    private final class PreparedStatementCache extends LruCache<String, SQLiteStatement> {
        public PreparedStatementCache(int maxSize) {
            super(maxSize);
        }

        public /* synthetic */ PreparedStatementCache(ConnectionWithLock connectionWithLock, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this((i2 & 1) != 0 ? 25 : i);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // androidx.collection.LruCache
        public SQLiteStatement create(String key) {
            Intrinsics.checkNotNullParameter(key, "key");
            return ConnectionWithLock.this.delegate.prepare(key);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // androidx.collection.LruCache
        public void entryRemoved(boolean evicted, String key, SQLiteStatement oldValue, SQLiteStatement newValue) {
            Intrinsics.checkNotNullParameter(key, "key");
            Intrinsics.checkNotNullParameter(oldValue, "oldValue");
            oldValue.close();
            super.entryRemoved(evicted, key, oldValue, newValue);
        }
    }

    /* JADX INFO: compiled from: ConnectionPoolImpl.kt */
    @Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\b\n\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u0003\u0010\u0004J\b\u0010\u0007\u001a\u00020\bH\u0016J\u001b\u0010\t\u001a\u00020\b2\b\b\u0001\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0096\u0001J\u001b\u0010\u000e\u001a\u00020\b2\b\b\u0001\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000fH\u0096\u0001J\u001b\u0010\u0010\u001a\u00020\b2\b\b\u0001\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0011H\u0096\u0001J\u001b\u0010\u0012\u001a\u00020\b2\b\b\u0001\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0013H\u0096\u0001J\u001b\u0010\u0014\u001a\u00020\b2\b\b\u0001\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0096\u0001J\u001b\u0010\u0015\u001a\u00020\b2\b\b\u0001\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0016H\u0096\u0001J\u0013\u0010\u0017\u001a\u00020\b2\b\b\u0001\u0010\n\u001a\u00020\u000bH\u0096\u0001J\u001b\u0010\u0018\u001a\u00020\b2\b\b\u0001\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0019H\u0096\u0001J\t\u0010\u001a\u001a\u00020\bH\u0096\u0001J\u0013\u0010\u001b\u001a\u00020\r2\b\b\u0001\u0010\n\u001a\u00020\u000bH\u0096\u0001J\u0013\u0010\u001c\u001a\u00020\u000f2\b\b\u0001\u0010\n\u001a\u00020\u000bH\u0096\u0001J\t\u0010\u001d\u001a\u00020\u000bH\u0096\u0001J\u0013\u0010\u001e\u001a\u00020\u00192\b\b\u0001\u0010\n\u001a\u00020\u000bH\u0096\u0001J\u000f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00190 H\u0096\u0001J\u0013\u0010!\u001a\u00020\u000b2\b\b\u0001\u0010\n\u001a\u00020\u000bH\u0096\u0001J\u0013\u0010\"\u001a\u00020\u00112\b\b\u0001\u0010\n\u001a\u00020\u000bH\u0096\u0001J\u0013\u0010#\u001a\u00020\u00132\b\b\u0001\u0010\n\u001a\u00020\u000bH\u0096\u0001J\u0013\u0010$\u001a\u00020\u000b2\b\b\u0001\u0010\n\u001a\u00020\u000bH\u0096\u0001J\u0013\u0010%\u001a\u00020\u00162\b\b\u0001\u0010\n\u001a\u00020\u000bH\u0096\u0001J\u0013\u0010&\u001a\u00020\u00192\b\b\u0001\u0010\n\u001a\u00020\u000bH\u0096\u0001J\u0013\u0010'\u001a\u00020\u000f2\b\b\u0001\u0010\n\u001a\u00020\u000bH\u0096\u0001J\t\u0010(\u001a\u00020\bH\u0096\u0001J\t\u0010)\u001a\u00020\u000fH\u0096\u0001R\u0011\u0010\u0002\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006*"}, d2 = {"Landroidx/room/coroutines/ConnectionWithLock$CachedStatement;", "Landroidx/sqlite/SQLiteStatement;", "delegate", "<init>", "(Landroidx/sqlite/SQLiteStatement;)V", "getDelegate", "()Landroidx/sqlite/SQLiteStatement;", "close", "", "bindBlob", "index", "", "value", "", "bindBoolean", "", "bindDouble", "", "bindFloat", "", "bindInt", "bindLong", "", "bindNull", "bindText", "", "clearBindings", "getBlob", "getBoolean", "getColumnCount", "getColumnName", "getColumnNames", "", "getColumnType", "getDouble", "getFloat", "getInt", "getLong", "getText", "isNull", "reset", "step", "room-runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    private static final class CachedStatement implements SQLiteStatement {
        private final SQLiteStatement delegate;

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

        public CachedStatement(SQLiteStatement delegate) {
            Intrinsics.checkNotNullParameter(delegate, "delegate");
            this.delegate = delegate;
        }

        public final SQLiteStatement getDelegate() {
            return this.delegate;
        }

        @Override // androidx.sqlite.SQLiteStatement, java.lang.AutoCloseable
        public void close() {
            this.delegate.reset();
            this.delegate.mo8518clearBindings();
        }
    }
}
