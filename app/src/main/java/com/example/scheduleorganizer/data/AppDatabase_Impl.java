package com.example.scheduleorganizer.data;

import androidx.autofill.HintConstants;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.room.InvalidationTracker;
import androidx.room.RoomMasterTable;
import androidx.room.RoomOpenDelegate;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.SQLite;
import androidx.sqlite.SQLiteConnection;
import com.example.scheduleorganizer.data.dao.AppDao;
import com.example.scheduleorganizer.data.dao.AppDao_Impl;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

/* JADX INFO: compiled from: AppDatabase_Impl.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0007\u001a\u00020\bH\u0014J\b\u0010\t\u001a\u00020\nH\u0014J\b\u0010\u000b\u001a\u00020\fH\u0016J\"\u0010\r\u001a\u001c\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000f\u0012\u000e\u0012\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000f0\u00100\u000eH\u0014J\u0016\u0010\u0011\u001a\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00130\u000f0\u0012H\u0016J*\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\u00102\u001a\u0010\u0016\u001a\u0016\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00130\u000f\u0012\u0004\u0012\u00020\u00130\u000eH\u0016J\b\u0010\u0017\u001a\u00020\u0006H\u0016R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/example/scheduleorganizer/data/AppDatabase_Impl;", "Lcom/example/scheduleorganizer/data/AppDatabase;", "<init>", "()V", "_appDao", "Lkotlin/Lazy;", "Lcom/example/scheduleorganizer/data/dao/AppDao;", "createOpenDelegate", "Landroidx/room/RoomOpenDelegate;", "createInvalidationTracker", "Landroidx/room/InvalidationTracker;", "clearAllTables", "", "getRequiredTypeConverterClasses", "", "Lkotlin/reflect/KClass;", "", "getRequiredAutoMigrationSpecClasses", "", "Landroidx/room/migration/AutoMigrationSpec;", "createAutoMigrations", "Landroidx/room/migration/Migration;", "autoMigrationSpecs", "appDao", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class AppDatabase_Impl extends AppDatabase {
    public static final int $stable = 8;
    private final Lazy<AppDao> _appDao = LazyKt.lazy(new Function0() { // from class: com.example.scheduleorganizer.data.AppDatabase_Impl$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return AppDatabase_Impl._appDao$lambda$0(this.f$0);
        }
    });

    static final AppDao_Impl _appDao$lambda$0(AppDatabase_Impl this$0) {
        return new AppDao_Impl(this$0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.room.RoomDatabase
    public RoomOpenDelegate createOpenDelegate() {
        RoomOpenDelegate _openDelegate = new RoomOpenDelegate() { // from class: com.example.scheduleorganizer.data.AppDatabase_Impl$createOpenDelegate$_openDelegate$1
            {
                super(2, "2876c9a34e9f2137f1ab20b33df37e37", "f7a8774d836eea7c3e89d84ae0c0b556");
            }

            @Override // androidx.room.RoomOpenDelegate
            public void createAllTables(SQLiteConnection connection) {
                Intrinsics.checkNotNullParameter(connection, "connection");
                SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS `courses` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `color` INTEGER NOT NULL)");
                SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS `schedules` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `title` TEXT NOT NULL, `category` TEXT NOT NULL, `time` TEXT NOT NULL, `days` TEXT NOT NULL, `isActive` INTEGER NOT NULL)");
                SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS `tasks` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `title` TEXT NOT NULL, `courseId` INTEGER, `dueDate` INTEGER NOT NULL, `isCompleted` INTEGER NOT NULL, `priority` INTEGER NOT NULL)");
                SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS `user_profile` (`id` INTEGER NOT NULL, `name` TEXT NOT NULL, `title` TEXT NOT NULL, `consistencyCount` INTEGER NOT NULL, `bestStreak` INTEGER NOT NULL, `lastLoginDate` INTEGER NOT NULL, PRIMARY KEY(`id`))");
                SQLite.execSQL(connection, RoomMasterTable.CREATE_QUERY);
                SQLite.execSQL(connection, "INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '2876c9a34e9f2137f1ab20b33df37e37')");
            }

            @Override // androidx.room.RoomOpenDelegate
            public void dropAllTables(SQLiteConnection connection) {
                Intrinsics.checkNotNullParameter(connection, "connection");
                SQLite.execSQL(connection, "DROP TABLE IF EXISTS `courses`");
                SQLite.execSQL(connection, "DROP TABLE IF EXISTS `schedules`");
                SQLite.execSQL(connection, "DROP TABLE IF EXISTS `tasks`");
                SQLite.execSQL(connection, "DROP TABLE IF EXISTS `user_profile`");
            }

            @Override // androidx.room.RoomOpenDelegate
            public void onCreate(SQLiteConnection connection) {
                Intrinsics.checkNotNullParameter(connection, "connection");
            }

            @Override // androidx.room.RoomOpenDelegate
            public void onOpen(SQLiteConnection connection) {
                Intrinsics.checkNotNullParameter(connection, "connection");
                this.this$0.internalInitInvalidationTracker(connection);
            }

            @Override // androidx.room.RoomOpenDelegate
            public void onPreMigrate(SQLiteConnection connection) {
                Intrinsics.checkNotNullParameter(connection, "connection");
                DBUtil.dropFtsSyncTriggers(connection);
            }

            @Override // androidx.room.RoomOpenDelegate
            public void onPostMigrate(SQLiteConnection connection) {
                Intrinsics.checkNotNullParameter(connection, "connection");
            }

            @Override // androidx.room.RoomOpenDelegate
            public RoomOpenDelegate.ValidationResult onValidateSchema(SQLiteConnection connection) {
                Intrinsics.checkNotNullParameter(connection, "connection");
                Map _columnsCourses = new LinkedHashMap();
                _columnsCourses.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, 1));
                _columnsCourses.put(HintConstants.AUTOFILL_HINT_NAME, new TableInfo.Column(HintConstants.AUTOFILL_HINT_NAME, "TEXT", true, 0, null, 1));
                _columnsCourses.put(TypedValues.Custom.S_COLOR, new TableInfo.Column(TypedValues.Custom.S_COLOR, "INTEGER", true, 0, null, 1));
                Set _foreignKeysCourses = new LinkedHashSet();
                Set _indicesCourses = new LinkedHashSet();
                TableInfo _infoCourses = new TableInfo("courses", _columnsCourses, _foreignKeysCourses, _indicesCourses);
                TableInfo _existingCourses = TableInfo.INSTANCE.read(connection, "courses");
                if (!_infoCourses.equals(_existingCourses)) {
                    return new RoomOpenDelegate.ValidationResult(false, "courses(com.example.scheduleorganizer.data.entity.Course).\n Expected:\n" + _infoCourses + "\n Found:\n" + _existingCourses);
                }
                Map _columnsSchedules = new LinkedHashMap();
                _columnsSchedules.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, 1));
                _columnsSchedules.put("title", new TableInfo.Column("title", "TEXT", true, 0, null, 1));
                _columnsSchedules.put("category", new TableInfo.Column("category", "TEXT", true, 0, null, 1));
                _columnsSchedules.put("time", new TableInfo.Column("time", "TEXT", true, 0, null, 1));
                _columnsSchedules.put("days", new TableInfo.Column("days", "TEXT", true, 0, null, 1));
                _columnsSchedules.put("isActive", new TableInfo.Column("isActive", "INTEGER", true, 0, null, 1));
                Set _foreignKeysSchedules = new LinkedHashSet();
                Set _indicesSchedules = new LinkedHashSet();
                TableInfo _infoSchedules = new TableInfo("schedules", _columnsSchedules, _foreignKeysSchedules, _indicesSchedules);
                TableInfo _existingSchedules = TableInfo.INSTANCE.read(connection, "schedules");
                if (!_infoSchedules.equals(_existingSchedules)) {
                    return new RoomOpenDelegate.ValidationResult(false, "schedules(com.example.scheduleorganizer.data.entity.Schedule).\n Expected:\n" + _infoSchedules + "\n Found:\n" + _existingSchedules);
                }
                Map _columnsTasks = new LinkedHashMap();
                _columnsTasks.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, 1));
                _columnsTasks.put("title", new TableInfo.Column("title", "TEXT", true, 0, null, 1));
                _columnsTasks.put("courseId", new TableInfo.Column("courseId", "INTEGER", false, 0, null, 1));
                _columnsTasks.put("dueDate", new TableInfo.Column("dueDate", "INTEGER", true, 0, null, 1));
                _columnsTasks.put("isCompleted", new TableInfo.Column("isCompleted", "INTEGER", true, 0, null, 1));
                _columnsTasks.put("priority", new TableInfo.Column("priority", "INTEGER", true, 0, null, 1));
                Set _foreignKeysTasks = new LinkedHashSet();
                Set _indicesTasks = new LinkedHashSet();
                TableInfo _infoTasks = new TableInfo("tasks", _columnsTasks, _foreignKeysTasks, _indicesTasks);
                TableInfo _existingTasks = TableInfo.INSTANCE.read(connection, "tasks");
                if (!_infoTasks.equals(_existingTasks)) {
                    return new RoomOpenDelegate.ValidationResult(false, "tasks(com.example.scheduleorganizer.data.entity.Task).\n Expected:\n" + _infoTasks + "\n Found:\n" + _existingTasks);
                }
                Map _columnsUserProfile = new LinkedHashMap();
                _columnsUserProfile.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, 1));
                _columnsUserProfile.put(HintConstants.AUTOFILL_HINT_NAME, new TableInfo.Column(HintConstants.AUTOFILL_HINT_NAME, "TEXT", true, 0, null, 1));
                _columnsUserProfile.put("title", new TableInfo.Column("title", "TEXT", true, 0, null, 1));
                _columnsUserProfile.put("consistencyCount", new TableInfo.Column("consistencyCount", "INTEGER", true, 0, null, 1));
                _columnsUserProfile.put("bestStreak", new TableInfo.Column("bestStreak", "INTEGER", true, 0, null, 1));
                _columnsUserProfile.put("lastLoginDate", new TableInfo.Column("lastLoginDate", "INTEGER", true, 0, null, 1));
                Set _foreignKeysUserProfile = new LinkedHashSet();
                Set _indicesUserProfile = new LinkedHashSet();
                TableInfo _infoUserProfile = new TableInfo("user_profile", _columnsUserProfile, _foreignKeysUserProfile, _indicesUserProfile);
                TableInfo _existingUserProfile = TableInfo.INSTANCE.read(connection, "user_profile");
                return !_infoUserProfile.equals(_existingUserProfile) ? new RoomOpenDelegate.ValidationResult(false, "user_profile(com.example.scheduleorganizer.data.entity.UserProfile).\n Expected:\n" + _infoUserProfile + "\n Found:\n" + _existingUserProfile) : new RoomOpenDelegate.ValidationResult(true, null);
            }
        };
        return _openDelegate;
    }

    @Override // androidx.room.RoomDatabase
    protected InvalidationTracker createInvalidationTracker() {
        Map _shadowTablesMap = new LinkedHashMap();
        Map _viewTables = new LinkedHashMap();
        return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "courses", "schedules", "tasks", "user_profile");
    }

    @Override // androidx.room.RoomDatabase
    public void clearAllTables() {
        super.performClear(false, "courses", "schedules", "tasks", "user_profile");
    }

    @Override // androidx.room.RoomDatabase
    protected Map<KClass<?>, List<KClass<?>>> getRequiredTypeConverterClasses() {
        Map _typeConvertersMap = new LinkedHashMap();
        _typeConvertersMap.put(Reflection.getOrCreateKotlinClass(AppDao.class), AppDao_Impl.INSTANCE.getRequiredConverters());
        return _typeConvertersMap;
    }

    @Override // androidx.room.RoomDatabase
    public Set<KClass<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecClasses() {
        Set _autoMigrationSpecsSet = new LinkedHashSet();
        return _autoMigrationSpecsSet;
    }

    @Override // androidx.room.RoomDatabase
    public List<Migration> createAutoMigrations(Map<KClass<? extends AutoMigrationSpec>, ? extends AutoMigrationSpec> autoMigrationSpecs) {
        Intrinsics.checkNotNullParameter(autoMigrationSpecs, "autoMigrationSpecs");
        List _autoMigrations = new ArrayList();
        return _autoMigrations;
    }

    @Override // com.example.scheduleorganizer.data.AppDatabase
    public AppDao appDao() {
        return this._appDao.getValue();
    }
}
