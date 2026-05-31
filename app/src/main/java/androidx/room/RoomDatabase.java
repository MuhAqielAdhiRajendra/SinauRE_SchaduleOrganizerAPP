package androidx.room;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.CancellationSignal;
import android.os.Looper;
import android.util.Log;
import androidx.arch.core.executor.ArchTaskExecutor;
import androidx.autofill.HintConstants;
import androidx.room.concurrent.CloseBarrier;
import androidx.room.coroutines.RunBlockingUninterruptible_androidKt;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.support.AutoCloser;
import androidx.room.support.AutoClosingRoomOpenHelper;
import androidx.room.support.AutoClosingRoomOpenHelperFactory;
import androidx.room.support.PrePackagedCopyOpenHelper;
import androidx.room.support.PrePackagedCopyOpenHelperFactory;
import androidx.room.support.QueryInterceptorOpenHelperFactory;
import androidx.room.util.DBUtil;
import androidx.room.util.KClassUtil;
import androidx.room.util.MigrationUtil;
import androidx.sqlite.SQLiteConnection;
import androidx.sqlite.SQLiteDriver;
import androidx.sqlite.db.SimpleSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteStatement;
import androidx.sqlite.db.framework.FrameworkSQLiteOpenHelperFactory;
import androidx.sqlite.driver.SupportSQLiteConnection;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.Pair;
import kotlin.ReplaceWith;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KClass;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.ExecutorsKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;

/* JADX INFO: compiled from: RoomDatabase.android.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u0000\u008a\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\"\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\b&\u0018\u0000 ¢\u00012\u00020\u0001:\u000e\u009c\u0001\u009d\u0001\u009e\u0001\u009f\u0001 \u0001¡\u0001¢\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J'\u0010A\u001a\u0004\u0018\u0001HB\"\b\b\u0000\u0010B*\u00020\u00012\f\u0010C\u001a\b\u0012\u0004\u0012\u0002HB0DH\u0017¢\u0006\u0002\u0010EJ%\u0010A\u001a\u0002HB\"\b\b\u0000\u0010B*\u00020\u00012\f\u0010C\u001a\b\u0012\u0004\u0012\u0002HB0<H\u0007¢\u0006\u0002\u0010FJ!\u0010G\u001a\u00020H2\n\u0010I\u001a\u0006\u0012\u0002\b\u00030<2\u0006\u0010J\u001a\u00020\u0001H\u0000¢\u0006\u0002\bKJ\u0010\u0010L\u001a\u00020H2\u0006\u0010\u0007\u001a\u00020\bH\u0017J\u0015\u0010M\u001a\u00020\"2\u0006\u0010\u0007\u001a\u00020\bH\u0000¢\u0006\u0002\bNJ*\u0010O\u001a\b\u0012\u0004\u0012\u00020P0/2\u001a\u0010Q\u001a\u0016\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020S0D\u0012\u0004\u0012\u00020S0RH\u0017J*\u0010T\u001a\b\u0012\u0004\u0012\u00020P0/2\u001a\u0010Q\u001a\u0016\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020S0<\u0012\u0004\u0012\u00020S0RH\u0017J&\u0010U\u001a\u0004\u0018\u0001HB\"\n\b\u0000\u0010B\u0018\u0001*\u00020\u001a2\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0082\b¢\u0006\u0002\u0010VJ\u0010\u0010W\u001a\u00020\u001a2\u0006\u0010X\u001a\u00020\bH\u0015J\b\u0010Y\u001a\u00020ZH\u0015J\b\u0010[\u001a\u00020$H$J\b\u0010\\\u001a\u00020\nH\u0007J\b\u0010]\u001a\u00020\fH\u0007J\r\u0010^\u001a\u00020\fH\u0000¢\u0006\u0002\b_J\"\u0010`\u001a\u001c\u0012\b\u0012\u0006\u0012\u0002\b\u00030D\u0012\u000e\u0012\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030D0/0RH\u0015J\"\u0010a\u001a\u001c\u0012\b\u0012\u0006\u0012\u0002\b\u00030<\u0012\u000e\u0012\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030<0/0RH\u0015J\u0016\u0010e\u001a\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020S0D0fH\u0017J\u0016\u0010g\u001a\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020S0<0fH\u0017J\b\u0010h\u001a\u00020HH'J)\u0010i\u001a\u00020H2\u0006\u0010j\u001a\u00020-2\u0012\u0010k\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u000e0l\"\u00020\u000eH\u0005¢\u0006\u0002\u0010mJ\b\u0010q\u001a\u00020HH\u0016J\b\u0010r\u001a\u00020HH\u0002J\b\u0010u\u001a\u00020HH\u0017J\b\u0010v\u001a\u00020HH\u0017J@\u0010w\u001a\u0002Hx\"\u0004\b\u0000\u0010x2\u0006\u0010y\u001a\u00020-2\"\u0010z\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020|\u0012\n\u0012\b\u0012\u0004\u0012\u0002Hx0}\u0012\u0006\u0012\u0004\u0018\u00010\u00010{H\u0087@¢\u0006\u0002\u0010~J\b\u0010\u007f\u001a\u00020-H\u0007J.\u0010\u0080\u0001\u001a\u00030\u0081\u00012\u0007\u0010\u0080\u0001\u001a\u00020\u000e2\u0013\u0010\u0082\u0001\u001a\u000e\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u0001\u0018\u00010lH\u0016¢\u0006\u0003\u0010\u0083\u0001J\"\u0010\u0080\u0001\u001a\u00030\u0081\u00012\b\u0010\u0080\u0001\u001a\u00030\u0084\u00012\f\b\u0002\u0010\u0085\u0001\u001a\u0005\u0018\u00010\u0086\u0001H\u0017J\u0013\u0010\u0087\u0001\u001a\u00030\u0088\u00012\u0007\u0010\u0089\u0001\u001a\u00020\u000eH\u0016J\t\u0010\u008a\u0001\u001a\u00020HH\u0017J\t\u0010\u008b\u0001\u001a\u00020HH\u0002J\t\u0010\u008c\u0001\u001a\u00020HH\u0017J\t\u0010\u008d\u0001\u001a\u00020HH\u0002J\t\u0010\u008e\u0001\u001a\u00020HH\u0017J\u0013\u0010\u008f\u0001\u001a\u00020H2\b\u0010\u0090\u0001\u001a\u00030\u0091\u0001H\u0016J(\u0010\u008f\u0001\u001a\u0003H\u0092\u0001\"\u0005\b\u0000\u0010\u0092\u00012\u000f\u0010\u0090\u0001\u001a\n\u0012\u0005\u0012\u0003H\u0092\u00010\u0093\u0001H\u0016¢\u0006\u0003\u0010\u0094\u0001J%\u0010\u008f\u0001\u001a\u0002HB\"\u0004\b\u0000\u0010B2\u000e\u0010\u0090\u0001\u001a\t\u0012\u0004\u0012\u0002HB0\u0095\u0001H\u0002¢\u0006\u0003\u0010\u0096\u0001J\u0012\u0010\u0097\u0001\u001a\u00020H2\u0007\u0010\u0098\u0001\u001a\u00020\u0005H\u0015J\u0013\u0010\u0097\u0001\u001a\u00020H2\b\u0010\u0099\u0001\u001a\u00030\u009a\u0001H\u0005J\t\u0010\u009b\u0001\u001a\u00020-H\u0016R\u001a\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0004@\u0004X\u0085\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u0006\u0010\u0003R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082.¢\u0006\u0002\n\u0000R\u0013\u0010\r\u001a\u0004\u0018\u00010\u000e8G¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0011\u001a\u00020\u00128VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0015\u001a\u00020\u0012X\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010\u0016\u001a\u00020\u00128VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0014R\u000e\u0010\u0018\u001a\u00020\u0012X\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010\u0019\u001a\u00020\u001a8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\u001d\u001a\u00020\u001e8G¢\u0006\u0006\u001a\u0004\b\u001f\u0010 R\u000e\u0010!\u001a\u00020\"X\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010#\u001a\u00020$8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b%\u0010&R\u000e\u0010'\u001a\u00020$X\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010(\u001a\u00020)X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b*\u0010+R\u000e\u0010,\u001a\u00020-X\u0082\u000e¢\u0006\u0002\n\u0000R \u0010.\u001a\n\u0012\u0004\u0012\u000200\u0018\u00010/8\u0004@\u0004X\u0085\u000e¢\u0006\b\n\u0000\u0012\u0004\b1\u0010\u0003R\u0010\u00102\u001a\u0004\u0018\u000103X\u0082\u000e¢\u0006\u0002\n\u0000R\u0019\u00104\u001a\b\u0012\u0004\u0012\u00020\f058G¢\u0006\b\n\u0000\u001a\u0004\b6\u00107R\u0014\u00108\u001a\u00020-8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b8\u00109R\u001e\u0010:\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030<\u0012\u0004\u0012\u00020\u00010;X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010=\u001a\u00020-X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b>\u00109\"\u0004\b?\u0010@R.\u0010b\u001a\u001c\u0012\b\u0012\u0006\u0012\u0002\b\u00030<\u0012\u000e\u0012\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030<0/0R8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bc\u0010dR\u0014\u0010n\u001a\u00020-8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bn\u00109R\u0014\u0010o\u001a\u00020-8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bp\u00109R\u0014\u0010s\u001a\u00020-8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bt\u00109¨\u0006£\u0001"}, d2 = {"Landroidx/room/RoomDatabase;", "", "<init>", "()V", "mDatabase", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "getMDatabase$annotations", "configuration", "Landroidx/room/DatabaseConfiguration;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "transactionContext", "Lkotlin/coroutines/CoroutineContext;", "path", "", "getPath", "()Ljava/lang/String;", "queryExecutor", "Ljava/util/concurrent/Executor;", "getQueryExecutor", "()Ljava/util/concurrent/Executor;", "internalQueryExecutor", "transactionExecutor", "getTransactionExecutor", "internalTransactionExecutor", "openHelper", "Landroidx/sqlite/db/SupportSQLiteOpenHelper;", "getOpenHelper", "()Landroidx/sqlite/db/SupportSQLiteOpenHelper;", "driver", "Landroidx/sqlite/SQLiteDriver;", "getDriver", "()Landroidx/sqlite/SQLiteDriver;", "connectionManager", "Landroidx/room/RoomConnectionManager;", "invalidationTracker", "Landroidx/room/InvalidationTracker;", "getInvalidationTracker", "()Landroidx/room/InvalidationTracker;", "internalTracker", "closeBarrier", "Landroidx/room/concurrent/CloseBarrier;", "getCloseBarrier$room_runtime", "()Landroidx/room/concurrent/CloseBarrier;", "allowMainThreadQueries", "", "mCallbacks", "", "Landroidx/room/RoomDatabase$Callback;", "getMCallbacks$annotations", "autoCloser", "Landroidx/room/support/AutoCloser;", "suspendingTransactionContext", "Ljava/lang/ThreadLocal;", "getSuspendingTransactionContext", "()Ljava/lang/ThreadLocal;", "isThreadInSuspendingTransaction", "()Z", "typeConverters", "", "Lkotlin/reflect/KClass;", "useTempTrackingTable", "getUseTempTrackingTable$room_runtime", "setUseTempTrackingTable$room_runtime", "(Z)V", "getTypeConverter", "T", "klass", "Ljava/lang/Class;", "(Ljava/lang/Class;)Ljava/lang/Object;", "(Lkotlin/reflect/KClass;)Ljava/lang/Object;", "addTypeConverter", "", "kclass", "converter", "addTypeConverter$room_runtime", "init", "createConnectionManager", "createConnectionManager$room_runtime", "getAutoMigrations", "Landroidx/room/migration/Migration;", "autoMigrationSpecs", "", "Landroidx/room/migration/AutoMigrationSpec;", "createAutoMigrations", "unwrapOpenHelper", "(Landroidx/sqlite/db/SupportSQLiteOpenHelper;)Landroidx/sqlite/db/SupportSQLiteOpenHelper;", "createOpenHelper", "config", "createOpenDelegate", "Landroidx/room/RoomOpenDelegateMarker;", "createInvalidationTracker", "getCoroutineScope", "getQueryContext", "getTransactionContext", "getTransactionContext$room_runtime", "getRequiredTypeConverters", "getRequiredTypeConverterClasses", "requiredTypeConverterClassesMap", "getRequiredTypeConverterClassesMap$room_runtime", "()Ljava/util/Map;", "getRequiredAutoMigrationSpecs", "", "getRequiredAutoMigrationSpecClasses", "clearAllTables", "performClear", "hasForeignKeys", "tableNames", "", "(Z[Ljava/lang/String;)V", "isOpen", "isOpenInternal", "isOpenInternal$room_runtime", "close", "onClosed", "isMainThread", "isMainThread$room_runtime", "assertNotMainThread", "assertNotSuspendingTransaction", "useConnection", "R", "isReadOnly", "block", "Lkotlin/Function2;", "Landroidx/room/Transactor;", "Lkotlin/coroutines/Continuation;", "(ZLkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "inCompatibilityMode", "query", "Landroid/database/Cursor;", "args", "(Ljava/lang/String;[Ljava/lang/Object;)Landroid/database/Cursor;", "Landroidx/sqlite/db/SupportSQLiteQuery;", "signal", "Landroid/os/CancellationSignal;", "compileStatement", "Landroidx/sqlite/db/SupportSQLiteStatement;", "sql", "beginTransaction", "internalBeginTransaction", "endTransaction", "internalEndTransaction", "setTransactionSuccessful", "runInTransaction", "body", "Ljava/lang/Runnable;", "V", "Ljava/util/concurrent/Callable;", "(Ljava/util/concurrent/Callable;)Ljava/lang/Object;", "Lkotlin/Function0;", "(Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "internalInitInvalidationTracker", "db", "connection", "Landroidx/sqlite/SQLiteConnection;", "inTransaction", "JournalMode", "Builder", "MigrationContainer", "Callback", "PrepackagedDatabaseCallback", "QueryCallback", "Companion", "room-runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
public abstract class RoomDatabase {
    public static final int MAX_BIND_PARAMETER_CNT = 999;
    private boolean allowMainThreadQueries;
    private AutoCloser autoCloser;
    private DatabaseConfiguration configuration;
    private RoomConnectionManager connectionManager;
    private CoroutineScope coroutineScope;
    private Executor internalQueryExecutor;
    private InvalidationTracker internalTracker;
    private Executor internalTransactionExecutor;
    protected List<? extends Callback> mCallbacks;
    protected volatile SupportSQLiteDatabase mDatabase;
    private CoroutineContext transactionContext;
    private final CloseBarrier closeBarrier = new CloseBarrier(new RoomDatabase$closeBarrier$1(this));
    private final ThreadLocal<CoroutineContext> suspendingTransactionContext = new ThreadLocal<>();
    private final Map<KClass<?>, Object> typeConverters = new LinkedHashMap();
    private boolean useTempTrackingTable = true;

    /* JADX INFO: compiled from: RoomDatabase.android.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0000\bæ\u0080\u0001\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u000e\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0007H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\bÀ\u0006\u0001"}, d2 = {"Landroidx/room/RoomDatabase$QueryCallback;", "", "onQuery", "", "sqlQuery", "", "bindArgs", "", "room-runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public interface QueryCallback {
        void onQuery(String sqlQuery, List<? extends Object> bindArgs);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "This property is always null and will be removed in a future version.")
    protected static /* synthetic */ void getMCallbacks$annotations() {
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "This property is always null and will be removed in a future version.")
    protected static /* synthetic */ void getMDatabase$annotations() {
    }

    public abstract void clearAllTables();

    protected abstract InvalidationTracker createInvalidationTracker();

    public final Cursor query(SupportSQLiteQuery query) {
        Intrinsics.checkNotNullParameter(query, "query");
        return query$default(this, query, null, 2, null);
    }

    public final String getPath() {
        DatabaseConfiguration databaseConfiguration = this.configuration;
        DatabaseConfiguration databaseConfiguration2 = null;
        if (databaseConfiguration == null) {
            Intrinsics.throwUninitializedPropertyAccessException("configuration");
            databaseConfiguration = null;
        }
        String it = databaseConfiguration.name;
        if (it == null) {
            return null;
        }
        DatabaseConfiguration databaseConfiguration3 = this.configuration;
        if (databaseConfiguration3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("configuration");
        } else {
            databaseConfiguration2 = databaseConfiguration3;
        }
        return databaseConfiguration2.context.getDatabasePath(it).getPath();
    }

    public Executor getQueryExecutor() {
        Executor executor = this.internalQueryExecutor;
        if (executor != null) {
            return executor;
        }
        Intrinsics.throwUninitializedPropertyAccessException("internalQueryExecutor");
        return null;
    }

    public Executor getTransactionExecutor() {
        Executor executor = this.internalTransactionExecutor;
        if (executor != null) {
            return executor;
        }
        Intrinsics.throwUninitializedPropertyAccessException("internalTransactionExecutor");
        return null;
    }

    public SupportSQLiteOpenHelper getOpenHelper() {
        RoomConnectionManager roomConnectionManager = this.connectionManager;
        if (roomConnectionManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("connectionManager");
            roomConnectionManager = null;
        }
        SupportSQLiteOpenHelper supportOpenHelper$room_runtime = roomConnectionManager.getSupportOpenHelper();
        if (supportOpenHelper$room_runtime == null) {
            throw new IllegalStateException("Cannot return a SupportSQLiteOpenHelper since no SupportSQLiteOpenHelper.Factory was configured with Room.".toString());
        }
        return supportOpenHelper$room_runtime;
    }

    public final SQLiteDriver getDriver() {
        DatabaseConfiguration databaseConfiguration = this.configuration;
        if (databaseConfiguration == null) {
            Intrinsics.throwUninitializedPropertyAccessException("configuration");
            databaseConfiguration = null;
        }
        SQLiteDriver sQLiteDriver = databaseConfiguration.sqliteDriver;
        if (sQLiteDriver != null) {
            return sQLiteDriver;
        }
        throw new IllegalStateException("No SQLiteDriver was configured with Room.".toString());
    }

    public InvalidationTracker getInvalidationTracker() {
        InvalidationTracker invalidationTracker = this.internalTracker;
        if (invalidationTracker != null) {
            return invalidationTracker;
        }
        Intrinsics.throwUninitializedPropertyAccessException("internalTracker");
        return null;
    }

    /* JADX INFO: renamed from: getCloseBarrier$room_runtime, reason: from getter */
    public final CloseBarrier getCloseBarrier() {
        return this.closeBarrier;
    }

    public final ThreadLocal<CoroutineContext> getSuspendingTransactionContext() {
        return this.suspendingTransactionContext;
    }

    private final boolean isThreadInSuspendingTransaction() {
        CoroutineContext coroutineContext = this.suspendingTransactionContext.get();
        return (coroutineContext != null ? (TransactionElement) coroutineContext.get(TransactionElement.INSTANCE) : null) != null;
    }

    /* JADX INFO: renamed from: getUseTempTrackingTable$room_runtime, reason: from getter */
    public final boolean getUseTempTrackingTable() {
        return this.useTempTrackingTable;
    }

    public final void setUseTempTrackingTable$room_runtime(boolean z) {
        this.useTempTrackingTable = z;
    }

    @Deprecated(message = "No longer called by generated implementation")
    public <T> T getTypeConverter(Class<T> klass) {
        Intrinsics.checkNotNullParameter(klass, "klass");
        return (T) this.typeConverters.get(JvmClassMappingKt.getKotlinClass(klass));
    }

    public final <T> T getTypeConverter(KClass<T> klass) {
        Intrinsics.checkNotNullParameter(klass, "klass");
        T t = (T) this.typeConverters.get(klass);
        Intrinsics.checkNotNull(t, "null cannot be cast to non-null type T of androidx.room.RoomDatabase.getTypeConverter");
        return t;
    }

    public final void addTypeConverter$room_runtime(KClass<?> kclass, Object converter) {
        Intrinsics.checkNotNullParameter(kclass, "kclass");
        Intrinsics.checkNotNullParameter(converter, "converter");
        this.typeConverters.put(kclass, converter);
    }

    public void init(DatabaseConfiguration configuration) {
        SupportSQLiteOpenHelper current$iv;
        SupportSQLiteOpenHelper current$iv2;
        CoroutineContext coroutineContext;
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        this.configuration = configuration;
        this.useTempTrackingTable = configuration.getUseTempTrackingTable();
        this.connectionManager = createConnectionManager$room_runtime(configuration);
        this.internalTracker = createInvalidationTracker();
        RoomDatabaseKt.validateAutoMigrations(this, configuration);
        RoomDatabaseKt.validateTypeConverters(this, configuration);
        CoroutineScope coroutineScope = null;
        if (configuration.queryCoroutineContext != null) {
            CoroutineContext.Element element = configuration.queryCoroutineContext.get(ContinuationInterceptor.INSTANCE);
            Intrinsics.checkNotNull(element, "null cannot be cast to non-null type kotlinx.coroutines.CoroutineDispatcher");
            CoroutineDispatcher dispatcher = (CoroutineDispatcher) element;
            this.internalQueryExecutor = ExecutorsKt.asExecutor(dispatcher);
            Executor executor = this.internalQueryExecutor;
            if (executor == null) {
                Intrinsics.throwUninitializedPropertyAccessException("internalQueryExecutor");
                executor = null;
            }
            this.internalTransactionExecutor = new TransactionExecutor(executor);
            Job parentJob = (Job) configuration.queryCoroutineContext.get(Job.INSTANCE);
            this.coroutineScope = CoroutineScopeKt.CoroutineScope(configuration.queryCoroutineContext.plus(SupervisorKt.SupervisorJob(parentJob)));
            boolean zInCompatibilityMode = inCompatibilityMode();
            CoroutineScope coroutineScope2 = this.coroutineScope;
            if (zInCompatibilityMode) {
                if (coroutineScope2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("coroutineScope");
                    coroutineScope2 = null;
                }
                coroutineContext = coroutineScope2.getCoroutineContext().plus(dispatcher.limitedParallelism(1));
            } else {
                if (coroutineScope2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("coroutineScope");
                    coroutineScope2 = null;
                }
                coroutineContext = coroutineScope2.getCoroutineContext();
            }
            this.transactionContext = coroutineContext;
        } else {
            this.internalQueryExecutor = configuration.queryExecutor;
            this.internalTransactionExecutor = new TransactionExecutor(configuration.transactionExecutor);
            Executor executor2 = this.internalQueryExecutor;
            if (executor2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("internalQueryExecutor");
                executor2 = null;
            }
            this.coroutineScope = CoroutineScopeKt.CoroutineScope(ExecutorsKt.from(executor2).plus(SupervisorKt.SupervisorJob$default((Job) null, 1, (Object) null)));
            CoroutineScope coroutineScope3 = this.coroutineScope;
            if (coroutineScope3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("coroutineScope");
                coroutineScope3 = null;
            }
            CoroutineContext coroutineContext2 = coroutineScope3.getCoroutineContext();
            Executor executor3 = this.internalTransactionExecutor;
            if (executor3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("internalTransactionExecutor");
                executor3 = null;
            }
            this.transactionContext = coroutineContext2.plus(ExecutorsKt.from(executor3));
        }
        this.allowMainThreadQueries = configuration.allowMainThreadQueries;
        RoomConnectionManager roomConnectionManager = this.connectionManager;
        if (roomConnectionManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("connectionManager");
            roomConnectionManager = null;
        }
        SupportSQLiteOpenHelper openHelper$iv = roomConnectionManager.getSupportOpenHelper();
        if (openHelper$iv == null) {
            current$iv = null;
        } else {
            current$iv = openHelper$iv;
            while (true) {
                if (!(current$iv instanceof PrePackagedCopyOpenHelper)) {
                    if (current$iv instanceof DelegatingOpenHelper) {
                        current$iv = ((DelegatingOpenHelper) current$iv).getDelegate();
                    } else {
                        current$iv = null;
                        break;
                    }
                } else {
                    break;
                }
            }
        }
        PrePackagedCopyOpenHelper prePackagedCopyOpenHelper = (PrePackagedCopyOpenHelper) current$iv;
        if (prePackagedCopyOpenHelper != null) {
            prePackagedCopyOpenHelper.setDatabaseConfiguration(configuration);
        }
        RoomConnectionManager roomConnectionManager2 = this.connectionManager;
        if (roomConnectionManager2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("connectionManager");
            roomConnectionManager2 = null;
        }
        SupportSQLiteOpenHelper openHelper$iv2 = roomConnectionManager2.getSupportOpenHelper();
        if (openHelper$iv2 == null) {
            current$iv2 = null;
        } else {
            current$iv2 = openHelper$iv2;
            while (true) {
                if (!(current$iv2 instanceof AutoClosingRoomOpenHelper)) {
                    if (current$iv2 instanceof DelegatingOpenHelper) {
                        current$iv2 = ((DelegatingOpenHelper) current$iv2).getDelegate();
                    } else {
                        current$iv2 = null;
                        break;
                    }
                } else {
                    break;
                }
            }
        }
        AutoClosingRoomOpenHelper it = (AutoClosingRoomOpenHelper) current$iv2;
        if (it != null) {
            this.autoCloser = it.getAutoCloser();
            AutoCloser autoCloser$room_runtime = it.getAutoCloser();
            CoroutineScope coroutineScope4 = this.coroutineScope;
            if (coroutineScope4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("coroutineScope");
            } else {
                coroutineScope = coroutineScope4;
            }
            autoCloser$room_runtime.initCoroutineScope(coroutineScope);
            getInvalidationTracker().setAutoCloser$room_runtime(it.getAutoCloser());
        }
        if (configuration.multiInstanceInvalidationServiceIntent != null) {
            if (configuration.name == null) {
                throw new IllegalArgumentException("Required value was null.".toString());
            }
            getInvalidationTracker().initMultiInstanceInvalidation$room_runtime(configuration.context, configuration.name, configuration.multiInstanceInvalidationServiceIntent);
        }
    }

    public final RoomConnectionManager createConnectionManager$room_runtime(DatabaseConfiguration configuration) {
        RoomOpenDelegate openDelegate;
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        try {
            RoomOpenDelegateMarker roomOpenDelegateMarkerCreateOpenDelegate = createOpenDelegate();
            Intrinsics.checkNotNull(roomOpenDelegateMarkerCreateOpenDelegate, "null cannot be cast to non-null type androidx.room.RoomOpenDelegate");
            openDelegate = (RoomOpenDelegate) roomOpenDelegateMarkerCreateOpenDelegate;
        } catch (NotImplementedError e) {
            openDelegate = null;
        }
        if (openDelegate == null) {
            return new RoomConnectionManager(configuration, (Function1<? super DatabaseConfiguration, ? extends SupportSQLiteOpenHelper>) new Function1() { // from class: androidx.room.RoomDatabase$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return RoomDatabase.createConnectionManager$lambda$2(this.f$0, (DatabaseConfiguration) obj);
                }
            }, new RoomDatabase$createConnectionManager$2(this));
        }
        return new RoomConnectionManager(configuration, openDelegate, new RoomDatabase$createConnectionManager$3(this));
    }

    static final SupportSQLiteOpenHelper createConnectionManager$lambda$2(RoomDatabase this$0, DatabaseConfiguration config) {
        Intrinsics.checkNotNullParameter(config, "config");
        return this$0.createOpenHelper(config);
    }

    @Deprecated(message = "No longer implemented by generated")
    public List<Migration> getAutoMigrations(Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
        Intrinsics.checkNotNullParameter(autoMigrationSpecs, "autoMigrationSpecs");
        return CollectionsKt.emptyList();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public List<Migration> createAutoMigrations(Map<KClass<? extends AutoMigrationSpec>, ? extends AutoMigrationSpec> autoMigrationSpecs) {
        Intrinsics.checkNotNullParameter(autoMigrationSpecs, "autoMigrationSpecs");
        Map javaClassesMap = new LinkedHashMap(MapsKt.mapCapacity(autoMigrationSpecs.size()));
        Iterable $this$associateByTo$iv$iv$iv = autoMigrationSpecs.entrySet();
        for (Object element$iv$iv$iv : $this$associateByTo$iv$iv$iv) {
            Map.Entry it = (Map.Entry) element$iv$iv$iv;
            Map.Entry it$iv$iv = (Map.Entry) element$iv$iv$iv;
            javaClassesMap.put(JvmClassMappingKt.getJavaClass((KClass) it.getKey()), it$iv$iv.getValue());
        }
        return getAutoMigrations(javaClassesMap);
    }

    private final /* synthetic */ <T extends SupportSQLiteOpenHelper> T unwrapOpenHelper(SupportSQLiteOpenHelper openHelper) {
        if (openHelper == null) {
            return null;
        }
        DelegatingOpenHelper delegatingOpenHelper = (T) openHelper;
        while (true) {
            Intrinsics.reifiedOperationMarker(3, "T");
            if (delegatingOpenHelper instanceof SupportSQLiteOpenHelper) {
                return (T) delegatingOpenHelper;
            }
            if (!(delegatingOpenHelper instanceof DelegatingOpenHelper)) {
                return null;
            }
            delegatingOpenHelper = (T) delegatingOpenHelper.getDelegate();
        }
    }

    @Deprecated(message = "No longer implemented by generated")
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        Intrinsics.checkNotNullParameter(config, "config");
        throw new NotImplementedError(null, 1, null);
    }

    protected RoomOpenDelegateMarker createOpenDelegate() {
        throw new NotImplementedError(null, 1, null);
    }

    public final CoroutineScope getCoroutineScope() {
        CoroutineScope coroutineScope = this.coroutineScope;
        if (coroutineScope != null) {
            return coroutineScope;
        }
        Intrinsics.throwUninitializedPropertyAccessException("coroutineScope");
        return null;
    }

    public final CoroutineContext getQueryContext() {
        CoroutineScope coroutineScope = this.coroutineScope;
        if (coroutineScope == null) {
            Intrinsics.throwUninitializedPropertyAccessException("coroutineScope");
            coroutineScope = null;
        }
        return coroutineScope.getCoroutineContext();
    }

    public final CoroutineContext getTransactionContext$room_runtime() {
        CoroutineContext coroutineContext = this.transactionContext;
        if (coroutineContext != null) {
            return coroutineContext;
        }
        Intrinsics.throwUninitializedPropertyAccessException("transactionContext");
        return null;
    }

    protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
        return MapsKt.emptyMap();
    }

    protected Map<KClass<?>, List<KClass<?>>> getRequiredTypeConverterClasses() {
        Iterable $this$associate$iv = getRequiredTypeConverters().entrySet();
        int i = 10;
        int capacity$iv = RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault($this$associate$iv, 10)), 16);
        Map destination$iv$iv = new LinkedHashMap(capacity$iv);
        for (Object element$iv$iv : $this$associate$iv) {
            Map.Entry entry = (Map.Entry) element$iv$iv;
            Class key = (Class) entry.getKey();
            Iterable value = (List) entry.getValue();
            KClass kotlinClass = JvmClassMappingKt.getKotlinClass(key);
            Iterable $this$map$iv = value;
            Iterable $this$associate$iv2 = $this$associate$iv;
            Collection destination$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, i));
            for (Object item$iv$iv : $this$map$iv) {
                Class it = (Class) item$iv$iv;
                destination$iv$iv2.add(JvmClassMappingKt.getKotlinClass(it));
            }
            Pair pair = TuplesKt.to(kotlinClass, (List) destination$iv$iv2);
            destination$iv$iv.put(pair.getFirst(), pair.getSecond());
            $this$associate$iv = $this$associate$iv2;
            i = 10;
        }
        return destination$iv$iv;
    }

    public final Map<KClass<?>, List<KClass<?>>> getRequiredTypeConverterClassesMap$room_runtime() {
        return getRequiredTypeConverterClasses();
    }

    @Deprecated(message = "No longer implemented by generated")
    public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
        return SetsKt.emptySet();
    }

    public Set<KClass<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecClasses() {
        Iterable $this$map$iv = getRequiredAutoMigrationSpecs();
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            Class it = (Class) item$iv$iv;
            destination$iv$iv.add(JvmClassMappingKt.getKotlinClass(it));
        }
        return CollectionsKt.toSet((List) destination$iv$iv);
    }

    protected final void performClear(boolean hasForeignKeys, String... tableNames) {
        Intrinsics.checkNotNullParameter(tableNames, "tableNames");
        assertNotMainThread();
        assertNotSuspendingTransaction();
        RunBlockingUninterruptible_androidKt.runBlockingUninterruptible(new AnonymousClass1(hasForeignKeys, tableNames, null));
    }

    /* JADX INFO: renamed from: androidx.room.RoomDatabase$performClear$1, reason: invalid class name */
    /* JADX INFO: compiled from: RoomDatabase.android.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.room.RoomDatabase$performClear$1", f = "RoomDatabase.android.kt", i = {}, l = {531}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ boolean $hasForeignKeys;
        final /* synthetic */ String[] $tableNames;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(boolean z, String[] strArr, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$hasForeignKeys = z;
            this.$tableNames = strArr;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return RoomDatabase.this.new AnonymousClass1(this.$hasForeignKeys, this.$tableNames, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX INFO: renamed from: androidx.room.RoomDatabase$performClear$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: RoomDatabase.android.kt */
        @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "connection", "Landroidx/room/Transactor;"}, k = 3, mv = {2, 1, 0}, xi = 48)
        @DebugMetadata(c = "androidx.room.RoomDatabase$performClear$1$1", f = "RoomDatabase.android.kt", i = {0, 1, 2, 3, 4}, l = {532, 533, 535, 541, 542, 543}, m = "invokeSuspend", n = {"connection", "connection", "connection", "connection", "connection"}, s = {"L$0", "L$0", "L$0", "L$0", "L$0"})
        static final class C00791 extends SuspendLambda implements Function2<Transactor, Continuation<? super Unit>, Object> {
            final /* synthetic */ boolean $hasForeignKeys;
            final /* synthetic */ String[] $tableNames;
            /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ RoomDatabase this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C00791(RoomDatabase roomDatabase, boolean z, String[] strArr, Continuation<? super C00791> continuation) {
                super(2, continuation);
                this.this$0 = roomDatabase;
                this.$hasForeignKeys = z;
                this.$tableNames = strArr;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                C00791 c00791 = new C00791(this.this$0, this.$hasForeignKeys, this.$tableNames, continuation);
                c00791.L$0 = obj;
                return c00791;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Transactor transactor, Continuation<? super Unit> continuation) {
                return ((C00791) create(transactor, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Removed duplicated region for block: B:18:0x0068  */
            /* JADX WARN: Removed duplicated region for block: B:23:0x0080  */
            /* JADX WARN: Removed duplicated region for block: B:26:0x009d A[RETURN] */
            /* JADX WARN: Removed duplicated region for block: B:29:0x00ac A[RETURN] */
            /* JADX WARN: Removed duplicated region for block: B:30:0x00ad  */
            /* JADX WARN: Removed duplicated region for block: B:33:0x00b9  */
            /* JADX WARN: Removed duplicated region for block: B:39:0x00e2 A[RETURN] */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final java.lang.Object invokeSuspend(java.lang.Object r9) {
                /*
                    Method dump skipped, instruction units count: 258
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.room.RoomDatabase.AnonymousClass1.C00791.invokeSuspend(java.lang.Object):java.lang.Object");
            }

            /* JADX INFO: renamed from: androidx.room.RoomDatabase$performClear$1$1$1, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: RoomDatabase.android.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00010\u0002H\n"}, d2 = {"<anonymous>", "", "Landroidx/room/TransactionScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
            @DebugMetadata(c = "androidx.room.RoomDatabase$performClear$1$1$1", f = "RoomDatabase.android.kt", i = {0, 1, 1}, l = {537, 539}, m = "invokeSuspend", n = {"$this$withTransaction", "$this$withTransaction", "$this$forEach$iv"}, s = {"L$0", "L$0", "L$1"})
            static final class C00801 extends SuspendLambda implements Function2<TransactionScope<Unit>, Continuation<? super Unit>, Object> {
                final /* synthetic */ boolean $hasForeignKeys;
                final /* synthetic */ String[] $tableNames;
                int I$0;
                int I$1;
                private /* synthetic */ Object L$0;
                Object L$1;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C00801(boolean z, String[] strArr, Continuation<? super C00801> continuation) {
                    super(2, continuation);
                    this.$hasForeignKeys = z;
                    this.$tableNames = strArr;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    C00801 c00801 = new C00801(this.$hasForeignKeys, this.$tableNames, continuation);
                    c00801.L$0 = obj;
                    return c00801;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(TransactionScope<Unit> transactionScope, Continuation<? super Unit> continuation) {
                    return ((C00801) create(transactionScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                /* JADX WARN: Removed duplicated region for block: B:16:0x005e  */
                /* JADX WARN: Removed duplicated region for block: B:21:0x009f  */
                /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:19:0x008f -> B:20:0x0095). Please report as a decompilation issue!!! */
                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct code enable 'Show inconsistent code' option in preferences
                */
                public final java.lang.Object invokeSuspend(java.lang.Object r14) {
                    /*
                        r13 = this;
                        java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                        int r1 = r13.label
                        r2 = 1
                        switch(r1) {
                            case 0: goto L32;
                            case 1: goto L2a;
                            case 2: goto L12;
                            default: goto La;
                        }
                    La:
                        java.lang.IllegalStateException r14 = new java.lang.IllegalStateException
                        java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                        r14.<init>(r0)
                        throw r14
                    L12:
                        r1 = 0
                        r3 = 0
                        int r4 = r13.I$1
                        int r5 = r13.I$0
                        java.lang.Object r6 = r13.L$1
                        java.lang.String[] r6 = (java.lang.String[]) r6
                        java.lang.Object r7 = r13.L$0
                        androidx.room.TransactionScope r7 = (androidx.room.TransactionScope) r7
                        kotlin.ResultKt.throwOnFailure(r14)
                        r8 = r7
                        r7 = r6
                        r6 = r5
                        r5 = r4
                        r4 = r13
                        goto L95
                    L2a:
                        java.lang.Object r1 = r13.L$0
                        androidx.room.TransactionScope r1 = (androidx.room.TransactionScope) r1
                        kotlin.ResultKt.throwOnFailure(r14)
                        goto L50
                    L32:
                        kotlin.ResultKt.throwOnFailure(r14)
                        java.lang.Object r1 = r13.L$0
                        androidx.room.TransactionScope r1 = (androidx.room.TransactionScope) r1
                        boolean r3 = r13.$hasForeignKeys
                        if (r3 == 0) goto L51
                        r3 = r1
                        androidx.room.PooledConnection r3 = (androidx.room.PooledConnection) r3
                        r4 = r13
                        kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4
                        r13.L$0 = r1
                        r13.label = r2
                        java.lang.String r5 = "PRAGMA defer_foreign_keys = TRUE"
                        java.lang.Object r3 = androidx.room.TransactorKt.execSQL(r3, r5, r4)
                        if (r3 != r0) goto L50
                        return r0
                    L50:
                    L51:
                        java.lang.String[] r3 = r13.$tableNames
                        r4 = 0
                        int r5 = r3.length
                        r6 = 0
                        r7 = r1
                        r1 = r4
                        r4 = r5
                        r5 = r6
                        r6 = r3
                        r3 = r13
                    L5c:
                        if (r5 >= r4) goto L9f
                        r8 = r6[r5]
                        r9 = 0
                        r10 = r7
                        androidx.room.PooledConnection r10 = (androidx.room.PooledConnection) r10
                        java.lang.StringBuilder r11 = new java.lang.StringBuilder
                        r11.<init>()
                        java.lang.String r12 = "DELETE FROM `"
                        java.lang.StringBuilder r11 = r11.append(r12)
                        java.lang.StringBuilder r11 = r11.append(r8)
                        r12 = 96
                        java.lang.StringBuilder r11 = r11.append(r12)
                        java.lang.String r11 = r11.toString()
                        r3.L$0 = r7
                        r3.L$1 = r6
                        r3.I$0 = r5
                        r3.I$1 = r4
                        r12 = 2
                        r3.label = r12
                        java.lang.Object r8 = androidx.room.TransactorKt.execSQL(r10, r11, r3)
                        if (r8 != r0) goto L8f
                        return r0
                    L8f:
                        r8 = r7
                        r7 = r6
                        r6 = r5
                        r5 = r4
                        r4 = r3
                        r3 = r9
                    L95:
                        int r3 = r6 + 1
                        r6 = r5
                        r5 = r3
                        r3 = r4
                        r4 = r6
                        r6 = r7
                        r7 = r8
                        goto L5c
                    L9f:
                        kotlin.Unit r0 = kotlin.Unit.INSTANCE
                        return r0
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.room.RoomDatabase.AnonymousClass1.C00791.C00801.invokeSuspend(java.lang.Object):java.lang.Object");
                }
            }
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    RoomConnectionManager roomConnectionManager = RoomDatabase.this.connectionManager;
                    if (roomConnectionManager == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("connectionManager");
                        roomConnectionManager = null;
                    }
                    this.label = 1;
                    if (roomConnectionManager.useConnection(false, new C00791(RoomDatabase.this, this.$hasForeignKeys, this.$tableNames, null), this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Unit.INSTANCE;
        }
    }

    public boolean isOpen() {
        AutoCloser autoCloser = this.autoCloser;
        if (autoCloser != null) {
            return autoCloser.isActive();
        }
        RoomConnectionManager roomConnectionManager = this.connectionManager;
        if (roomConnectionManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("connectionManager");
            roomConnectionManager = null;
        }
        return roomConnectionManager.isSupportDatabaseOpen();
    }

    public final boolean isOpenInternal$room_runtime() {
        AutoCloser it = this.autoCloser;
        if (it != null) {
            SupportSQLiteDatabase delegateDatabase$room_runtime = it.getDelegateDatabase();
            if (delegateDatabase$room_runtime != null) {
                return delegateDatabase$room_runtime.isOpen();
            }
            return false;
        }
        RoomConnectionManager roomConnectionManager = this.connectionManager;
        if (roomConnectionManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("connectionManager");
            roomConnectionManager = null;
        }
        return roomConnectionManager.isSupportDatabaseOpen();
    }

    public void close() {
        this.closeBarrier.close$room_runtime();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onClosed() {
        CoroutineScope coroutineScope = this.coroutineScope;
        RoomConnectionManager roomConnectionManager = null;
        if (coroutineScope == null) {
            Intrinsics.throwUninitializedPropertyAccessException("coroutineScope");
            coroutineScope = null;
        }
        CoroutineScopeKt.cancel$default(coroutineScope, null, 1, null);
        getInvalidationTracker().stop$room_runtime();
        RoomConnectionManager roomConnectionManager2 = this.connectionManager;
        if (roomConnectionManager2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("connectionManager");
        } else {
            roomConnectionManager = roomConnectionManager2;
        }
        roomConnectionManager.close();
    }

    public final boolean isMainThread$room_runtime() {
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }

    public void assertNotMainThread() {
        if (!this.allowMainThreadQueries && isMainThread$room_runtime()) {
            throw new IllegalStateException("Cannot access database on the main thread since it may potentially lock the UI for a long period of time.".toString());
        }
    }

    public void assertNotSuspendingTransaction() {
        if (!((inCompatibilityMode() && !inTransaction() && isThreadInSuspendingTransaction()) ? false : true)) {
            throw new IllegalStateException("Cannot access database on a different coroutine context inherited from a suspending transaction.".toString());
        }
    }

    public final <R> Object useConnection(boolean isReadOnly, Function2<? super Transactor, ? super Continuation<? super R>, ? extends Object> function2, Continuation<? super R> continuation) {
        RoomConnectionManager roomConnectionManager = this.connectionManager;
        if (roomConnectionManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("connectionManager");
            roomConnectionManager = null;
        }
        return roomConnectionManager.useConnection(isReadOnly, function2, continuation);
    }

    public final boolean inCompatibilityMode() {
        RoomConnectionManager roomConnectionManager = this.connectionManager;
        if (roomConnectionManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("connectionManager");
            roomConnectionManager = null;
        }
        return roomConnectionManager.getSupportOpenHelper() != null;
    }

    public Cursor query(String query, Object[] args) {
        Intrinsics.checkNotNullParameter(query, "query");
        assertNotMainThread();
        assertNotSuspendingTransaction();
        return getOpenHelper().getWritableDatabase().query(new SimpleSQLiteQuery(query, args));
    }

    public static /* synthetic */ Cursor query$default(RoomDatabase roomDatabase, SupportSQLiteQuery supportSQLiteQuery, CancellationSignal cancellationSignal, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: query");
        }
        if ((i & 2) != 0) {
            cancellationSignal = null;
        }
        return roomDatabase.query(supportSQLiteQuery, cancellationSignal);
    }

    public Cursor query(SupportSQLiteQuery query, CancellationSignal signal) {
        Intrinsics.checkNotNullParameter(query, "query");
        assertNotMainThread();
        assertNotSuspendingTransaction();
        if (signal != null) {
            return getOpenHelper().getWritableDatabase().query(query, signal);
        }
        return getOpenHelper().getWritableDatabase().query(query);
    }

    public SupportSQLiteStatement compileStatement(String sql) {
        Intrinsics.checkNotNullParameter(sql, "sql");
        assertNotMainThread();
        assertNotSuspendingTransaction();
        return getOpenHelper().getWritableDatabase().compileStatement(sql);
    }

    @Deprecated(message = "beginTransaction() is deprecated", replaceWith = @ReplaceWith(expression = "runInTransaction(Runnable)", imports = {}))
    public void beginTransaction() {
        assertNotMainThread();
        internalBeginTransaction();
    }

    private final void internalBeginTransaction() {
        assertNotMainThread();
        SupportSQLiteDatabase database = getOpenHelper().getWritableDatabase();
        if (!database.inTransaction()) {
            getInvalidationTracker().syncBlocking$room_runtime();
        }
        if (database.isWriteAheadLoggingEnabled()) {
            database.beginTransactionNonExclusive();
        } else {
            database.beginTransaction();
        }
    }

    @Deprecated(message = "endTransaction() is deprecated", replaceWith = @ReplaceWith(expression = "runInTransaction(Runnable)", imports = {}))
    public void endTransaction() {
        internalEndTransaction();
    }

    private final void internalEndTransaction() {
        getOpenHelper().getWritableDatabase().endTransaction();
        if (!inTransaction()) {
            getInvalidationTracker().refreshVersionsAsync();
        }
    }

    @Deprecated(message = "setTransactionSuccessful() is deprecated", replaceWith = @ReplaceWith(expression = "runInTransaction(Runnable)", imports = {}))
    public void setTransactionSuccessful() {
        getOpenHelper().getWritableDatabase().setTransactionSuccessful();
    }

    static final Unit runInTransaction$lambda$10(Runnable $body) {
        $body.run();
        return Unit.INSTANCE;
    }

    public void runInTransaction(final Runnable body) {
        Intrinsics.checkNotNullParameter(body, "body");
        runInTransaction(new Function0() { // from class: androidx.room.RoomDatabase$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return RoomDatabase.runInTransaction$lambda$10(body);
            }
        });
    }

    public <V> V runInTransaction(final Callable<V> body) {
        Intrinsics.checkNotNullParameter(body, "body");
        return (V) runInTransaction(new Function0() { // from class: androidx.room.RoomDatabase$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return body.call();
            }
        });
    }

    private final <T> T runInTransaction(final Function0<? extends T> body) {
        if (inCompatibilityMode()) {
            beginTransaction();
            try {
                T tInvoke = body.invoke();
                setTransactionSuccessful();
                return tInvoke;
            } finally {
                endTransaction();
            }
        }
        return (T) DBUtil.performBlocking(this, false, true, new Function1() { // from class: androidx.room.RoomDatabase$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return RoomDatabase.runInTransaction$lambda$12(body, (SQLiteConnection) obj);
            }
        });
    }

    static final Object runInTransaction$lambda$12(Function0 $body, SQLiteConnection it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return $body.invoke();
    }

    @Deprecated(message = "No longer called by generated")
    protected void internalInitInvalidationTracker(SupportSQLiteDatabase db) {
        Intrinsics.checkNotNullParameter(db, "db");
        internalInitInvalidationTracker(new SupportSQLiteConnection(db));
    }

    protected final void internalInitInvalidationTracker(SQLiteConnection connection) {
        Intrinsics.checkNotNullParameter(connection, "connection");
        getInvalidationTracker().internalInit$room_runtime(connection);
    }

    public boolean inTransaction() {
        return isOpenInternal$room_runtime() && getOpenHelper().getWritableDatabase().inTransaction();
    }

    /* JADX INFO: compiled from: RoomDatabase.android.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0007\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\tH\u0000¢\u0006\u0002\b\nj\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u000b"}, d2 = {"Landroidx/room/RoomDatabase$JournalMode;", "", "<init>", "(Ljava/lang/String;I)V", "AUTOMATIC", "TRUNCATE", "WRITE_AHEAD_LOGGING", "resolve", "context", "Landroid/content/Context;", "resolve$room_runtime", "room-runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public enum JournalMode {
        AUTOMATIC,
        TRUNCATE,
        WRITE_AHEAD_LOGGING;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

        public static EnumEntries<JournalMode> getEntries() {
            return $ENTRIES;
        }

        public final JournalMode resolve$room_runtime(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            if (this != AUTOMATIC) {
                return this;
            }
            Object systemService = context.getSystemService("activity");
            ActivityManager manager = systemService instanceof ActivityManager ? (ActivityManager) systemService : null;
            if (manager != null && !manager.isLowRamDevice()) {
                return WRITE_AHEAD_LOGGING;
            }
            return TRUNCATE;
        }
    }

    /* JADX INFO: compiled from: RoomDatabase.android.kt */
    @Metadata(d1 = {"\u0000À\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u0015\n\u0002\b\f\b\u0016\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003B9\b\u0011\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u000e\u0010\b\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rB)\b\u0010\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u000e\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\f\u0010\u000fJ\u0016\u0010?\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010@\u001a\u00020\u0007H\u0016J\u001e\u0010?\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010@\u001a\u00020\u00072\u0006\u0010A\u001a\u00020\u0014H\u0017J\u0016\u0010B\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010C\u001a\u000207H\u0016J\u001e\u0010B\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010C\u001a\u0002072\u0006\u0010A\u001a\u00020\u0014H\u0017J\u001c\u0010D\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\f\u0010E\u001a\b\u0012\u0004\u0012\u00020:09H\u0017J$\u0010D\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\f\u0010E\u001a\b\u0012\u0004\u0012\u00020:092\u0006\u0010A\u001a\u00020\u0014H\u0017J\u0018\u0010F\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\u0010\b\u001a\u0004\u0018\u00010\u001fH\u0016J'\u0010G\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0012\u0010H\u001a\n\u0012\u0006\b\u0001\u0012\u00020J0I\"\u00020JH\u0016¢\u0006\u0002\u0010KJ\u0016\u0010L\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010M\u001a\u000201H\u0016J\u000e\u0010 \u001a\b\u0012\u0004\u0012\u00028\u00000\u0000H\u0016J\u0016\u0010N\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010\"\u001a\u00020#H\u0016J\u0016\u0010O\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010P\u001a\u00020\u0018H\u0016J\u0016\u0010Q\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010P\u001a\u00020\u0018H\u0016J\u000e\u0010R\u001a\b\u0012\u0004\u0012\u00028\u00000\u0000H\u0016J\u0016\u0010S\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010T\u001a\u00020%H\u0017J\u000e\u0010U\u001a\b\u0012\u0004\u0012\u00028\u00000\u0000H\u0017J\u0014\u0010U\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010V\u001a\u00020!J\u000e\u0010W\u001a\b\u0012\u0004\u0012\u00028\u00000\u0000H\u0017J\u0014\u0010W\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010V\u001a\u00020!J\u001a\u0010X\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\n\u0010Y\u001a\u00020Z\"\u00020.H\u0017J\"\u0010X\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010V\u001a\u00020!2\n\u0010Y\u001a\u00020Z\"\u00020.H\u0016J\u0016\u0010[\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010A\u001a\u00020\u0012H\u0016J\u001e\u0010\\\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010P\u001a\u00020\u0018H\u0016J\u001c\u0010\\\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010\n\u001a\u00020\u001a2\u0006\u0010\u0015\u001a\u00020\u0016J\u0016\u0010]\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010^\u001a\u00020\u0003H\u0016J \u0010_\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\b\u0001\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020)H\u0017J\u0014\u0010`\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010;\u001a\u00020<J\u0014\u0010a\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010\n\u001a\u00020\u001aJ\u0016\u0010b\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010c\u001a\u00020!H\u0007J\r\u0010d\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010eR\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00030\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020!X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020#X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010$\u001a\u0004\u0018\u00010%X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020'X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010(\u001a\u0004\u0018\u00010)X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020+X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010,\u001a\b\u0012\u0004\u0012\u00020.0-X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010/\u001a\b\u0012\u0004\u0012\u00020.0-X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u00100\u001a\b\u0012\u0004\u0012\u0002010\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00102\u001a\u00020!X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00103\u001a\u00020!X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00104\u001a\u00020!X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00105\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00106\u001a\u0004\u0018\u000107X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u00108\u001a\n\u0012\u0004\u0012\u00020:\u0018\u000109X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010;\u001a\u0004\u0018\u00010<X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010=\u001a\u0004\u0018\u00010\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010>\u001a\u00020!X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006f"}, d2 = {"Landroidx/room/RoomDatabase$Builder;", "T", "Landroidx/room/RoomDatabase;", "", "klass", "Lkotlin/reflect/KClass;", HintConstants.AUTOFILL_HINT_NAME, "", "factory", "Lkotlin/Function0;", "context", "Landroid/content/Context;", "<init>", "(Lkotlin/reflect/KClass;Ljava/lang/String;Lkotlin/jvm/functions/Function0;Landroid/content/Context;)V", "Ljava/lang/Class;", "(Landroid/content/Context;Ljava/lang/Class;Ljava/lang/String;)V", "callbacks", "", "Landroidx/room/RoomDatabase$Callback;", "prepackagedDatabaseCallback", "Landroidx/room/RoomDatabase$PrepackagedDatabaseCallback;", "queryCallback", "Landroidx/room/RoomDatabase$QueryCallback;", "queryCallbackExecutor", "Ljava/util/concurrent/Executor;", "queryCallbackCoroutineContext", "Lkotlin/coroutines/CoroutineContext;", "typeConverters", "queryExecutor", "transactionExecutor", "supportOpenHelperFactory", "Landroidx/sqlite/db/SupportSQLiteOpenHelper$Factory;", "allowMainThreadQueries", "", "journalMode", "Landroidx/room/RoomDatabase$JournalMode;", "multiInstanceInvalidationIntent", "Landroid/content/Intent;", "autoCloseTimeout", "", "autoCloseTimeUnit", "Ljava/util/concurrent/TimeUnit;", "migrationContainer", "Landroidx/room/RoomDatabase$MigrationContainer;", "migrationsNotRequiredFrom", "", "", "migrationStartAndEndVersions", "autoMigrationSpecs", "Landroidx/room/migration/AutoMigrationSpec;", "requireMigration", "allowDestructiveMigrationOnDowngrade", "allowDestructiveMigrationForAllTables", "copyFromAssetPath", "copyFromFile", "Ljava/io/File;", "copyFromInputStream", "Ljava/util/concurrent/Callable;", "Ljava/io/InputStream;", "driver", "Landroidx/sqlite/SQLiteDriver;", "queryCoroutineContext", "inMemoryTrackingTableMode", "createFromAsset", "databaseFilePath", "callback", "createFromFile", "databaseFile", "createFromInputStream", "inputStreamCallable", "openHelperFactory", "addMigrations", "migrations", "", "Landroidx/room/migration/Migration;", "([Landroidx/room/migration/Migration;)Landroidx/room/RoomDatabase$Builder;", "addAutoMigrationSpec", "autoMigrationSpec", "setJournalMode", "setQueryExecutor", "executor", "setTransactionExecutor", "enableMultiInstanceInvalidation", "setMultiInstanceInvalidationServiceIntent", "invalidationServiceIntent", "fallbackToDestructiveMigration", "dropAllTables", "fallbackToDestructiveMigrationOnDowngrade", "fallbackToDestructiveMigrationFrom", "startVersions", "", "addCallback", "setQueryCallback", "addTypeConverter", "typeConverter", "setAutoCloseTimeout", "setDriver", "setQueryCoroutineContext", "setInMemoryTrackingMode", "inMemory", "build", "()Landroidx/room/RoomDatabase;", "room-runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static class Builder<T extends RoomDatabase> {
        private boolean allowDestructiveMigrationForAllTables;
        private boolean allowDestructiveMigrationOnDowngrade;
        private boolean allowMainThreadQueries;
        private TimeUnit autoCloseTimeUnit;
        private long autoCloseTimeout;
        private final List<AutoMigrationSpec> autoMigrationSpecs;
        private final List<Callback> callbacks;
        private final Context context;
        private String copyFromAssetPath;
        private File copyFromFile;
        private Callable<InputStream> copyFromInputStream;
        private SQLiteDriver driver;
        private final Function0<T> factory;
        private boolean inMemoryTrackingTableMode;
        private JournalMode journalMode;
        private final KClass<T> klass;
        private final MigrationContainer migrationContainer;
        private final Set<Integer> migrationStartAndEndVersions;
        private Set<Integer> migrationsNotRequiredFrom;
        private Intent multiInstanceInvalidationIntent;
        private final String name;
        private PrepackagedDatabaseCallback prepackagedDatabaseCallback;
        private QueryCallback queryCallback;
        private CoroutineContext queryCallbackCoroutineContext;
        private Executor queryCallbackExecutor;
        private CoroutineContext queryCoroutineContext;
        private Executor queryExecutor;
        private boolean requireMigration;
        private SupportSQLiteOpenHelper.Factory supportOpenHelperFactory;
        private Executor transactionExecutor;
        private final List<Object> typeConverters;

        /* JADX WARN: Multi-variable type inference failed */
        public Builder(KClass<T> klass, String name, Function0<? extends T> function0, Context context) {
            Intrinsics.checkNotNullParameter(klass, "klass");
            Intrinsics.checkNotNullParameter(context, "context");
            this.callbacks = new ArrayList();
            this.typeConverters = new ArrayList();
            this.journalMode = JournalMode.AUTOMATIC;
            this.autoCloseTimeout = -1L;
            this.migrationContainer = new MigrationContainer();
            this.migrationsNotRequiredFrom = new LinkedHashSet();
            this.migrationStartAndEndVersions = new LinkedHashSet();
            this.autoMigrationSpecs = new ArrayList();
            this.requireMigration = true;
            this.inMemoryTrackingTableMode = true;
            this.klass = klass;
            this.context = context;
            this.name = name;
            this.factory = function0;
        }

        public Builder(Context context, Class<T> klass, String name) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(klass, "klass");
            this.callbacks = new ArrayList();
            this.typeConverters = new ArrayList();
            this.journalMode = JournalMode.AUTOMATIC;
            this.autoCloseTimeout = -1L;
            this.migrationContainer = new MigrationContainer();
            this.migrationsNotRequiredFrom = new LinkedHashSet();
            this.migrationStartAndEndVersions = new LinkedHashSet();
            this.autoMigrationSpecs = new ArrayList();
            this.requireMigration = true;
            this.inMemoryTrackingTableMode = true;
            this.klass = JvmClassMappingKt.getKotlinClass(klass);
            this.context = context;
            this.name = name;
            this.factory = null;
        }

        public Builder<T> createFromAsset(String databaseFilePath) {
            Intrinsics.checkNotNullParameter(databaseFilePath, "databaseFilePath");
            this.copyFromAssetPath = databaseFilePath;
            return this;
        }

        public Builder<T> createFromAsset(String databaseFilePath, PrepackagedDatabaseCallback callback) {
            Intrinsics.checkNotNullParameter(databaseFilePath, "databaseFilePath");
            Intrinsics.checkNotNullParameter(callback, "callback");
            Builder<T> builder = this;
            builder.prepackagedDatabaseCallback = callback;
            builder.copyFromAssetPath = databaseFilePath;
            return this;
        }

        public Builder<T> createFromFile(File databaseFile) {
            Intrinsics.checkNotNullParameter(databaseFile, "databaseFile");
            this.copyFromFile = databaseFile;
            return this;
        }

        public Builder<T> createFromFile(File databaseFile, PrepackagedDatabaseCallback callback) {
            Intrinsics.checkNotNullParameter(databaseFile, "databaseFile");
            Intrinsics.checkNotNullParameter(callback, "callback");
            Builder<T> builder = this;
            builder.prepackagedDatabaseCallback = callback;
            builder.copyFromFile = databaseFile;
            return this;
        }

        public Builder<T> createFromInputStream(Callable<InputStream> inputStreamCallable) {
            Intrinsics.checkNotNullParameter(inputStreamCallable, "inputStreamCallable");
            this.copyFromInputStream = inputStreamCallable;
            return this;
        }

        public Builder<T> createFromInputStream(Callable<InputStream> inputStreamCallable, PrepackagedDatabaseCallback callback) {
            Intrinsics.checkNotNullParameter(inputStreamCallable, "inputStreamCallable");
            Intrinsics.checkNotNullParameter(callback, "callback");
            Builder<T> builder = this;
            builder.prepackagedDatabaseCallback = callback;
            builder.copyFromInputStream = inputStreamCallable;
            return this;
        }

        public Builder<T> openHelperFactory(SupportSQLiteOpenHelper.Factory factory) {
            this.supportOpenHelperFactory = factory;
            return this;
        }

        public Builder<T> addMigrations(Migration... migrations) {
            Intrinsics.checkNotNullParameter(migrations, "migrations");
            Builder<T> builder = this;
            for (Migration migration : migrations) {
                builder.migrationStartAndEndVersions.add(Integer.valueOf(migration.startVersion));
                builder.migrationStartAndEndVersions.add(Integer.valueOf(migration.endVersion));
            }
            builder.migrationContainer.addMigrations((Migration[]) Arrays.copyOf(migrations, migrations.length));
            return this;
        }

        public Builder<T> addAutoMigrationSpec(AutoMigrationSpec autoMigrationSpec) {
            Intrinsics.checkNotNullParameter(autoMigrationSpec, "autoMigrationSpec");
            this.autoMigrationSpecs.add(autoMigrationSpec);
            return this;
        }

        public Builder<T> allowMainThreadQueries() {
            this.allowMainThreadQueries = true;
            return this;
        }

        public Builder<T> setJournalMode(JournalMode journalMode) {
            Intrinsics.checkNotNullParameter(journalMode, "journalMode");
            this.journalMode = journalMode;
            return this;
        }

        public Builder<T> setQueryExecutor(Executor executor) {
            Intrinsics.checkNotNullParameter(executor, "executor");
            Builder<T> builder = this;
            if (!(builder.queryCoroutineContext == null)) {
                throw new IllegalArgumentException("This builder has already been configured with a CoroutineContext. A RoomDatabasecan only be configured with either an Executor or a CoroutineContext.".toString());
            }
            builder.queryExecutor = executor;
            return this;
        }

        public Builder<T> setTransactionExecutor(Executor executor) {
            Intrinsics.checkNotNullParameter(executor, "executor");
            Builder<T> builder = this;
            if (!(builder.queryCoroutineContext == null)) {
                throw new IllegalArgumentException("This builder has already been configured with a CoroutineContext. A RoomDatabasecan only be configured with either an Executor or a CoroutineContext.".toString());
            }
            builder.transactionExecutor = executor;
            return this;
        }

        public Builder<T> enableMultiInstanceInvalidation() {
            Intent intent;
            Builder<T> builder = this;
            if (builder.name != null) {
                intent = new Intent(builder.context, (Class<?>) MultiInstanceInvalidationService.class);
            } else {
                intent = null;
            }
            builder.multiInstanceInvalidationIntent = intent;
            return this;
        }

        public Builder<T> setMultiInstanceInvalidationServiceIntent(Intent invalidationServiceIntent) {
            Intrinsics.checkNotNullParameter(invalidationServiceIntent, "invalidationServiceIntent");
            Builder<T> builder = this;
            builder.multiInstanceInvalidationIntent = builder.name != null ? invalidationServiceIntent : null;
            return this;
        }

        @Deprecated(message = "Replace by overloaded version with parameter to indicate if all tables should be dropped or not.", replaceWith = @ReplaceWith(expression = "fallbackToDestructiveMigration(false)", imports = {}))
        public Builder<T> fallbackToDestructiveMigration() {
            Builder<T> builder = this;
            builder.requireMigration = false;
            builder.allowDestructiveMigrationOnDowngrade = true;
            return this;
        }

        public final Builder<T> fallbackToDestructiveMigration(boolean dropAllTables) {
            Builder<T> builder = this;
            builder.requireMigration = false;
            builder.allowDestructiveMigrationOnDowngrade = true;
            builder.allowDestructiveMigrationForAllTables = dropAllTables;
            return this;
        }

        @Deprecated(message = "Replace by overloaded version with parameter to indicate if all tables should be dropped or not.", replaceWith = @ReplaceWith(expression = "fallbackToDestructiveMigrationOnDowngrade(false)", imports = {}))
        public Builder<T> fallbackToDestructiveMigrationOnDowngrade() {
            Builder<T> builder = this;
            builder.requireMigration = true;
            builder.allowDestructiveMigrationOnDowngrade = true;
            return this;
        }

        public final Builder<T> fallbackToDestructiveMigrationOnDowngrade(boolean dropAllTables) {
            Builder<T> builder = this;
            builder.requireMigration = true;
            builder.allowDestructiveMigrationOnDowngrade = true;
            builder.allowDestructiveMigrationForAllTables = dropAllTables;
            return this;
        }

        @Deprecated(message = "Replace by overloaded version with parameter to indicate if all tables should be dropped or not.", replaceWith = @ReplaceWith(expression = "fallbackToDestructiveMigrationFrom(false, startVersions)", imports = {}))
        public Builder<T> fallbackToDestructiveMigrationFrom(int... startVersions) {
            Intrinsics.checkNotNullParameter(startVersions, "startVersions");
            Builder<T> builder = this;
            for (int startVersion : startVersions) {
                builder.migrationsNotRequiredFrom.add(Integer.valueOf(startVersion));
            }
            return this;
        }

        public Builder<T> fallbackToDestructiveMigrationFrom(boolean dropAllTables, int... startVersions) {
            Intrinsics.checkNotNullParameter(startVersions, "startVersions");
            Builder<T> builder = this;
            for (int startVersion : startVersions) {
                builder.migrationsNotRequiredFrom.add(Integer.valueOf(startVersion));
            }
            builder.allowDestructiveMigrationForAllTables = dropAllTables;
            return this;
        }

        public Builder<T> addCallback(Callback callback) {
            Intrinsics.checkNotNullParameter(callback, "callback");
            this.callbacks.add(callback);
            return this;
        }

        public Builder<T> setQueryCallback(QueryCallback queryCallback, Executor executor) {
            Intrinsics.checkNotNullParameter(queryCallback, "queryCallback");
            Intrinsics.checkNotNullParameter(executor, "executor");
            Builder<T> builder = this;
            builder.queryCallback = queryCallback;
            builder.queryCallbackExecutor = executor;
            builder.queryCallbackCoroutineContext = null;
            return this;
        }

        public final Builder<T> setQueryCallback(CoroutineContext context, QueryCallback queryCallback) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(queryCallback, "queryCallback");
            Builder<T> builder = this;
            builder.queryCallback = queryCallback;
            builder.queryCallbackExecutor = null;
            builder.queryCallbackCoroutineContext = context;
            return this;
        }

        public Builder<T> addTypeConverter(Object typeConverter) {
            Intrinsics.checkNotNullParameter(typeConverter, "typeConverter");
            this.typeConverters.add(typeConverter);
            return this;
        }

        public Builder<T> setAutoCloseTimeout(long autoCloseTimeout, TimeUnit autoCloseTimeUnit) {
            Intrinsics.checkNotNullParameter(autoCloseTimeUnit, "autoCloseTimeUnit");
            Builder<T> builder = this;
            if (!(autoCloseTimeout >= 0)) {
                throw new IllegalArgumentException("autoCloseTimeout must be >= 0".toString());
            }
            builder.autoCloseTimeout = autoCloseTimeout;
            builder.autoCloseTimeUnit = autoCloseTimeUnit;
            return this;
        }

        public final Builder<T> setDriver(SQLiteDriver driver) {
            Intrinsics.checkNotNullParameter(driver, "driver");
            this.driver = driver;
            return this;
        }

        public final Builder<T> setQueryCoroutineContext(CoroutineContext context) {
            Intrinsics.checkNotNullParameter(context, "context");
            Builder<T> builder = this;
            if (!(builder.queryExecutor == null && builder.transactionExecutor == null)) {
                throw new IllegalArgumentException("This builder has already been configured with an Executor. A RoomDatabase canonly be configured with either an Executor or a CoroutineContext.".toString());
            }
            if (!(context.get(ContinuationInterceptor.INSTANCE) != null)) {
                throw new IllegalArgumentException("It is required that the coroutine context contain a dispatcher.".toString());
            }
            builder.queryCoroutineContext = context;
            return this;
        }

        public final Builder<T> setInMemoryTrackingMode(boolean inMemory) {
            this.inMemoryTrackingTableMode = inMemory;
            return this;
        }

        public T build() {
            FrameworkSQLiteOpenHelperFactory initialFactory;
            QueryInterceptorOpenHelperFactory queryInterceptorOpenHelperFactory;
            T tInvoke;
            AutoClosingRoomOpenHelperFactory it;
            PrePackagedCopyOpenHelperFactory it2;
            CoroutineDispatcher queryCallbackContext;
            CoroutineDispatcher coroutineDispatcherFrom;
            if (this.queryExecutor == null && this.transactionExecutor == null) {
                this.transactionExecutor = ArchTaskExecutor.getIOThreadExecutor();
                this.queryExecutor = this.transactionExecutor;
            } else if (this.queryExecutor != null && this.transactionExecutor == null) {
                this.transactionExecutor = this.queryExecutor;
            } else if (this.queryExecutor == null) {
                this.queryExecutor = this.transactionExecutor;
            }
            RoomDatabaseKt.validateMigrationsNotRequired(this.migrationStartAndEndVersions, this.migrationsNotRequiredFrom);
            if (this.driver == null && this.supportOpenHelperFactory == null) {
                initialFactory = new FrameworkSQLiteOpenHelperFactory();
            } else {
                SQLiteDriver sQLiteDriver = this.driver;
                SupportSQLiteOpenHelper.Factory factory = this.supportOpenHelperFactory;
                if (sQLiteDriver == null) {
                    initialFactory = factory;
                } else {
                    if (factory != null) {
                        throw new IllegalArgumentException("A RoomDatabase cannot be configured with both a SQLiteDriver and a SupportOpenHelper.Factory.");
                    }
                    initialFactory = null;
                }
            }
            boolean autoCloseEnabled = this.autoCloseTimeout > 0;
            boolean prePackagedCopyEnabled = (this.copyFromAssetPath == null && this.copyFromFile == null && this.copyFromInputStream == null) ? false : true;
            boolean queryCallbackEnabled = this.queryCallback != null;
            if (initialFactory != null) {
                SupportSQLiteOpenHelper.Factory it3 = initialFactory;
                if (!autoCloseEnabled) {
                    it = it3;
                } else {
                    if (this.name == null) {
                        throw new IllegalArgumentException("Cannot create auto-closing database for an in-memory database.".toString());
                    }
                    long j = this.autoCloseTimeout;
                    TimeUnit timeUnit = this.autoCloseTimeUnit;
                    if (timeUnit == null) {
                        throw new IllegalArgumentException("Required value was null.".toString());
                    }
                    AutoCloser autoCloser = new AutoCloser(j, timeUnit, null, 4, null);
                    it = new AutoClosingRoomOpenHelperFactory(it3, autoCloser);
                }
                if (!prePackagedCopyEnabled) {
                    it2 = it;
                } else {
                    if (this.name == null) {
                        throw new IllegalArgumentException("Cannot create from asset or file for an in-memory database.".toString());
                    }
                    int copyFromAssetPathConfig = this.copyFromAssetPath == null ? 0 : 1;
                    int copyFromFileConfig = this.copyFromFile == null ? 0 : 1;
                    int copyFromInputStreamConfig = this.copyFromInputStream == null ? 0 : 1;
                    int copyConfigurations = copyFromAssetPathConfig + copyFromFileConfig + copyFromInputStreamConfig;
                    if (!(copyConfigurations == 1)) {
                        throw new IllegalArgumentException("More than one of createFromAsset(), createFromInputStream(), and createFromFile() were called on this Builder, but the database can only be created using one of the three configurations.".toString());
                    }
                    it2 = new PrePackagedCopyOpenHelperFactory(this.copyFromAssetPath, this.copyFromFile, this.copyFromInputStream, it);
                }
                if (queryCallbackEnabled) {
                    Executor executor = this.queryCallbackExecutor;
                    if (executor == null || (coroutineDispatcherFrom = ExecutorsKt.from(executor)) == null) {
                        queryCallbackContext = this.queryCallbackCoroutineContext;
                        if (queryCallbackContext == null) {
                            throw new IllegalArgumentException("Required value was null.".toString());
                        }
                    } else {
                        queryCallbackContext = coroutineDispatcherFrom;
                    }
                    CoroutineScope CoroutineScope = CoroutineScopeKt.CoroutineScope(queryCallbackContext);
                    QueryCallback queryCallback = this.queryCallback;
                    if (queryCallback == null) {
                        throw new IllegalArgumentException("Required value was null.".toString());
                    }
                    queryInterceptorOpenHelperFactory = new QueryInterceptorOpenHelperFactory(it2, CoroutineScope, queryCallback);
                } else {
                    queryInterceptorOpenHelperFactory = it2;
                }
            } else {
                queryInterceptorOpenHelperFactory = null;
            }
            SupportSQLiteOpenHelper.Factory supportOpenHelperFactory = queryInterceptorOpenHelperFactory;
            if (supportOpenHelperFactory == null) {
                if (!(!autoCloseEnabled)) {
                    throw new IllegalArgumentException("Auto Closing Database is not supported when an SQLiteDriver is configured.".toString());
                }
                if (!(!prePackagedCopyEnabled)) {
                    throw new IllegalArgumentException("Pre-Package Database is not supported when an SQLiteDriver is configured.".toString());
                }
                if (!(!queryCallbackEnabled)) {
                    throw new IllegalArgumentException("Query Callback is not supported when an SQLiteDriver is configured.".toString());
                }
            }
            Context context = this.context;
            String str = this.name;
            MigrationContainer migrationContainer = this.migrationContainer;
            List<Callback> list = this.callbacks;
            boolean z = this.allowMainThreadQueries;
            JournalMode journalModeResolve$room_runtime = this.journalMode.resolve$room_runtime(this.context);
            Executor executor2 = this.queryExecutor;
            if (executor2 == null) {
                throw new IllegalArgumentException("Required value was null.".toString());
            }
            Executor executor3 = this.transactionExecutor;
            if (executor3 == null) {
                throw new IllegalArgumentException("Required value was null.".toString());
            }
            DatabaseConfiguration $this$build_u24lambda_u2443 = new DatabaseConfiguration(context, str, supportOpenHelperFactory, migrationContainer, list, z, journalModeResolve$room_runtime, executor2, executor3, this.multiInstanceInvalidationIntent, this.requireMigration, this.allowDestructiveMigrationOnDowngrade, this.migrationsNotRequiredFrom, this.copyFromAssetPath, this.copyFromFile, this.copyFromInputStream, this.prepackagedDatabaseCallback, this.typeConverters, this.autoMigrationSpecs, this.allowDestructiveMigrationForAllTables, this.driver, this.queryCoroutineContext);
            $this$build_u24lambda_u2443.setUseTempTrackingTable$room_runtime(this.inMemoryTrackingTableMode);
            Function0<T> function0 = this.factory;
            if (function0 == null || (tInvoke = function0.invoke()) == null) {
                tInvoke = (T) KClassUtil.findAndInstantiateDatabaseImpl$default(JvmClassMappingKt.getJavaClass((KClass) this.klass), null, 2, null);
            }
            tInvoke.init($this$build_u24lambda_u2443);
            return tInvoke;
        }
    }

    /* JADX INFO: compiled from: RoomDatabase.android.kt */
    @Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u001c\n\u0002\b\u0005\b\u0016\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J!\u0010\t\u001a\u00020\n2\u0012\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u00020\b0\u000b\"\u00020\bH\u0016¢\u0006\u0002\u0010\fJ\u0016\u0010\t\u001a\u00020\n2\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\b0\rH\u0016J\u0010\u0010\u000e\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\bH\u0007J \u0010\u0010\u001a\u001a\u0012\u0004\u0012\u00020\u0006\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\b0\u00110\u0011H\u0016J \u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\r2\u0006\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u0006H\u0016J\u0016\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00062\u0006\u0010\u0018\u001a\u00020\u0006J5\u0010\u0019\u001a\"\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\b0\u0011\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u001b\u0018\u00010\u001a2\u0006\u0010\u001c\u001a\u00020\u0006H\u0000¢\u0006\u0002\b\u001dJ5\u0010\u001e\u001a\"\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\b0\u0011\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u001b\u0018\u00010\u001a2\u0006\u0010\u001c\u001a\u00020\u0006H\u0000¢\u0006\u0002\b\u001fR&\u0010\u0004\u001a\u001a\u0012\u0004\u0012\u00020\u0006\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\b0\u00070\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Landroidx/room/RoomDatabase$MigrationContainer;", "", "<init>", "()V", "migrations", "", "", "Ljava/util/TreeMap;", "Landroidx/room/migration/Migration;", "addMigrations", "", "", "([Landroidx/room/migration/Migration;)V", "", "addMigration", "migration", "getMigrations", "", "findMigrationPath", "start", "end", "contains", "", "startVersion", "endVersion", "getSortedNodes", "Lkotlin/Pair;", "", "migrationStart", "getSortedNodes$room_runtime", "getSortedDescendingNodes", "getSortedDescendingNodes$room_runtime", "room-runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static class MigrationContainer {
        private final Map<Integer, TreeMap<Integer, Migration>> migrations = new LinkedHashMap();

        public void addMigrations(Migration... migrations) {
            Intrinsics.checkNotNullParameter(migrations, "migrations");
            for (Migration migration : migrations) {
                addMigration(migration);
            }
        }

        public void addMigrations(List<? extends Migration> migrations) {
            Intrinsics.checkNotNullParameter(migrations, "migrations");
            List<? extends Migration> $this$forEach$iv = migrations;
            for (Object element$iv : $this$forEach$iv) {
                Migration p0 = (Migration) element$iv;
                addMigration(p0);
            }
        }

        public final void addMigration(Migration migration) {
            TreeMap<Integer, Migration> treeMap;
            Intrinsics.checkNotNullParameter(migration, "migration");
            int start = migration.startVersion;
            int end = migration.endVersion;
            Map<Integer, TreeMap<Integer, Migration>> map = this.migrations;
            Integer numValueOf = Integer.valueOf(start);
            TreeMap<Integer, Migration> treeMap2 = map.get(numValueOf);
            if (treeMap2 == null) {
                treeMap = new TreeMap<>();
                map.put(numValueOf, treeMap);
            } else {
                treeMap = treeMap2;
            }
            Map $this$getOrPut$iv = treeMap;
            TreeMap<Integer, Migration> treeMap3 = (TreeMap) $this$getOrPut$iv;
            if (treeMap3.containsKey(Integer.valueOf(end))) {
                Log.w(Room.LOG_TAG, "Overriding migration " + treeMap3.get(Integer.valueOf(end)) + " with " + migration);
            }
            treeMap3.put(Integer.valueOf(end), migration);
        }

        public Map<Integer, Map<Integer, Migration>> getMigrations() {
            return this.migrations;
        }

        public List<Migration> findMigrationPath(int start, int end) {
            return MigrationUtil.findMigrationPath(this, start, end);
        }

        public final boolean contains(int startVersion, int endVersion) {
            return MigrationUtil.contains(this, startVersion, endVersion);
        }

        public final Pair<Map<Integer, Migration>, Iterable<Integer>> getSortedNodes$room_runtime(int migrationStart) {
            TreeMap<Integer, Migration> treeMap = this.migrations.get(Integer.valueOf(migrationStart));
            if (treeMap == null) {
                return null;
            }
            return TuplesKt.to(treeMap, treeMap.keySet());
        }

        public final Pair<Map<Integer, Migration>, Iterable<Integer>> getSortedDescendingNodes$room_runtime(int migrationStart) {
            TreeMap<Integer, Migration> treeMap = this.migrations.get(Integer.valueOf(migrationStart));
            if (treeMap == null) {
                return null;
            }
            return TuplesKt.to(treeMap, treeMap.descendingKeySet());
        }
    }

    /* JADX INFO: compiled from: RoomDatabase.android.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\n\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\n\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\u000b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\u000b\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\tH\u0016¨\u0006\f"}, d2 = {"Landroidx/room/RoomDatabase$Callback;", "", "<init>", "()V", "onCreate", "", "db", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "connection", "Landroidx/sqlite/SQLiteConnection;", "onDestructiveMigration", "onOpen", "room-runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static abstract class Callback {
        public void onCreate(SupportSQLiteDatabase db) {
            Intrinsics.checkNotNullParameter(db, "db");
        }

        public void onCreate(SQLiteConnection connection) {
            Intrinsics.checkNotNullParameter(connection, "connection");
            if (connection instanceof SupportSQLiteConnection) {
                onCreate(((SupportSQLiteConnection) connection).getDb());
            }
        }

        public void onDestructiveMigration(SupportSQLiteDatabase db) {
            Intrinsics.checkNotNullParameter(db, "db");
        }

        public void onDestructiveMigration(SQLiteConnection connection) {
            Intrinsics.checkNotNullParameter(connection, "connection");
            if (connection instanceof SupportSQLiteConnection) {
                onDestructiveMigration(((SupportSQLiteConnection) connection).getDb());
            }
        }

        public void onOpen(SupportSQLiteDatabase db) {
            Intrinsics.checkNotNullParameter(db, "db");
        }

        public void onOpen(SQLiteConnection connection) {
            Intrinsics.checkNotNullParameter(connection, "connection");
            if (connection instanceof SupportSQLiteConnection) {
                onOpen(((SupportSQLiteConnection) connection).getDb());
            }
        }
    }

    /* JADX INFO: compiled from: RoomDatabase.android.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\b"}, d2 = {"Landroidx/room/RoomDatabase$PrepackagedDatabaseCallback;", "", "<init>", "()V", "onOpenPrepackagedDatabase", "", "db", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "room-runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static abstract class PrepackagedDatabaseCallback {
        public void onOpenPrepackagedDatabase(SupportSQLiteDatabase db) {
            Intrinsics.checkNotNullParameter(db, "db");
        }
    }
}
