package androidx.room;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: RoomDatabase.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u00008\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a<\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u00022\"\u0010\u0003\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0004H\u0086@¢\u0006\u0002\u0010\b\u001a<\u0010\t\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u00022\"\u0010\u0003\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0004H\u0086@¢\u0006\u0002\u0010\b\u001a$\u0010\n\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rH\u0000\u001a\u0014\u0010\u0010\u001a\u00020\u000b*\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u0012H\u0000\u001a\u0014\u0010\u0013\u001a\u00020\u000b*\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u0012H\u0000¨\u0006\u0014"}, d2 = {"useReaderConnection", "R", "Landroidx/room/RoomDatabase;", "block", "Lkotlin/Function2;", "Landroidx/room/Transactor;", "Lkotlin/coroutines/Continuation;", "", "(Landroidx/room/RoomDatabase;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "useWriterConnection", "validateMigrationsNotRequired", "", "migrationStartAndEndVersions", "", "", "migrationsNotRequiredFrom", "validateAutoMigrations", "configuration", "Landroidx/room/DatabaseConfiguration;", "validateTypeConverters", "room-runtime"}, k = 5, mv = {2, 1, 0}, xi = 48, xs = "androidx/room/RoomDatabaseKt")
final /* synthetic */ class RoomDatabaseKt__RoomDatabaseKt {

    /* JADX INFO: renamed from: androidx.room.RoomDatabaseKt__RoomDatabaseKt$useReaderConnection$1, reason: invalid class name */
    /* JADX INFO: compiled from: RoomDatabase.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.room.RoomDatabaseKt__RoomDatabaseKt", f = "RoomDatabase.kt", i = {0, 0}, l = {471, 471}, m = "useReaderConnection", n = {"$this$useReaderConnection", "block"}, s = {"L$0", "L$1"})
    static final class AnonymousClass1<R> extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return RoomDatabaseKt.useReaderConnection(null, null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.room.RoomDatabaseKt__RoomDatabaseKt$useWriterConnection$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: RoomDatabase.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.room.RoomDatabaseKt__RoomDatabaseKt", f = "RoomDatabase.kt", i = {0, 0, 1}, l = {TypedValues.PositionType.TYPE_TRANSITION_EASING, TypedValues.PositionType.TYPE_TRANSITION_EASING}, m = "useWriterConnection", n = {"$this$useWriterConnection", "block", "$this$useWriterConnection"}, s = {"L$0", "L$1", "L$0"})
    static final class C03531<R> extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C03531(Continuation<? super C03531> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return RoomDatabaseKt.useWriterConnection(null, null, this);
        }
    }

    /* JADX INFO: Add missing generic type declarations: [R] */
    /* JADX INFO: renamed from: androidx.room.RoomDatabaseKt__RoomDatabaseKt$useReaderConnection$2, reason: invalid class name */
    /* JADX INFO: compiled from: RoomDatabase.kt */
    @Metadata(d1 = {"\u0000\b\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "R", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.room.RoomDatabaseKt__RoomDatabaseKt$useReaderConnection$2", f = "RoomDatabase.kt", i = {}, l = {472}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass2<R> extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super R>, Object> {
        final /* synthetic */ Function2<Transactor, Continuation<? super R>, Object> $block;
        final /* synthetic */ RoomDatabase $this_useReaderConnection;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass2(RoomDatabase roomDatabase, Function2<? super Transactor, ? super Continuation<? super R>, ? extends Object> function2, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$this_useReaderConnection = roomDatabase;
            this.$block = function2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass2(this.$this_useReaderConnection, this.$block, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super R> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    this.label = 1;
                    Object objUseConnection = this.$this_useReaderConnection.useConnection(true, this.$block, this);
                    if (objUseConnection == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    return objUseConnection;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final <R> java.lang.Object useReaderConnection(androidx.room.RoomDatabase r7, kotlin.jvm.functions.Function2<? super androidx.room.Transactor, ? super kotlin.coroutines.Continuation<? super R>, ? extends java.lang.Object> r8, kotlin.coroutines.Continuation<? super R> r9) {
        /*
            boolean r0 = r9 instanceof androidx.room.RoomDatabaseKt__RoomDatabaseKt.AnonymousClass1
            if (r0 == 0) goto L14
            r0 = r9
            androidx.room.RoomDatabaseKt__RoomDatabaseKt$useReaderConnection$1 r0 = (androidx.room.RoomDatabaseKt__RoomDatabaseKt.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r1 = r0.label
            int r1 = r1 - r2
            r0.label = r1
            goto L19
        L14:
            androidx.room.RoomDatabaseKt__RoomDatabaseKt$useReaderConnection$1 r0 = new androidx.room.RoomDatabaseKt__RoomDatabaseKt$useReaderConnection$1
            r0.<init>(r9)
        L19:
            java.lang.Object r1 = r0.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r0.label
            switch(r3) {
                case 0: goto L3e;
                case 1: goto L31;
                case 2: goto L2c;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L2c:
            kotlin.ResultKt.throwOnFailure(r1)
            r7 = r1
            goto L73
        L31:
            java.lang.Object r7 = r0.L$1
            kotlin.jvm.functions.Function2 r7 = (kotlin.jvm.functions.Function2) r7
            java.lang.Object r8 = r0.L$0
            androidx.room.RoomDatabase r8 = (androidx.room.RoomDatabase) r8
            kotlin.ResultKt.throwOnFailure(r1)
            r3 = r1
            goto L53
        L3e:
            kotlin.ResultKt.throwOnFailure(r1)
            r0.L$0 = r7
            r0.L$1 = r8
            r3 = 1
            r0.label = r3
            r3 = 0
            java.lang.Object r3 = androidx.room.util.DBUtil.getCoroutineContext(r7, r3, r0)
            if (r3 != r2) goto L50
            return r2
        L50:
            r6 = r8
            r8 = r7
            r7 = r6
        L53:
            kotlin.coroutines.CoroutineContext r3 = (kotlin.coroutines.CoroutineContext) r3
            androidx.room.RoomExternalOperationElement r4 = androidx.room.RoomExternalOperationElement.INSTANCE
            kotlin.coroutines.CoroutineContext r4 = (kotlin.coroutines.CoroutineContext) r4
            kotlin.coroutines.CoroutineContext r3 = r3.plus(r4)
            androidx.room.RoomDatabaseKt__RoomDatabaseKt$useReaderConnection$2 r4 = new androidx.room.RoomDatabaseKt__RoomDatabaseKt$useReaderConnection$2
            r5 = 0
            r4.<init>(r8, r7, r5)
            kotlin.jvm.functions.Function2 r4 = (kotlin.jvm.functions.Function2) r4
            r0.L$0 = r5
            r0.L$1 = r5
            r5 = 2
            r0.label = r5
            java.lang.Object r7 = kotlinx.coroutines.BuildersKt.withContext(r3, r4, r0)
            if (r7 != r2) goto L73
            return r2
        L73:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.room.RoomDatabaseKt__RoomDatabaseKt.useReaderConnection(androidx.room.RoomDatabase, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Add missing generic type declarations: [R] */
    /* JADX INFO: renamed from: androidx.room.RoomDatabaseKt__RoomDatabaseKt$useWriterConnection$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: RoomDatabase.kt */
    @Metadata(d1 = {"\u0000\b\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "R", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.room.RoomDatabaseKt__RoomDatabaseKt$useWriterConnection$2", f = "RoomDatabase.kt", i = {}, l = {TypedValues.PositionType.TYPE_DRAWPATH}, m = "invokeSuspend", n = {}, s = {})
    static final class C03542<R> extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super R>, Object> {
        final /* synthetic */ Function2<Transactor, Continuation<? super R>, Object> $block;
        final /* synthetic */ RoomDatabase $this_useWriterConnection;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C03542(RoomDatabase roomDatabase, Function2<? super Transactor, ? super Continuation<? super R>, ? extends Object> function2, Continuation<? super C03542> continuation) {
            super(2, continuation);
            this.$this_useWriterConnection = roomDatabase;
            this.$block = function2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C03542(this.$this_useWriterConnection, this.$block, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super R> continuation) {
            return ((C03542) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    this.label = 1;
                    Object objUseConnection = this.$this_useWriterConnection.useConnection(false, this.$block, this);
                    if (objUseConnection == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    return objUseConnection;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0076 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final <R> java.lang.Object useWriterConnection(androidx.room.RoomDatabase r6, kotlin.jvm.functions.Function2<? super androidx.room.Transactor, ? super kotlin.coroutines.Continuation<? super R>, ? extends java.lang.Object> r7, kotlin.coroutines.Continuation<? super R> r8) {
        /*
            boolean r0 = r8 instanceof androidx.room.RoomDatabaseKt__RoomDatabaseKt.C03531
            if (r0 == 0) goto L14
            r0 = r8
            androidx.room.RoomDatabaseKt__RoomDatabaseKt$useWriterConnection$1 r0 = (androidx.room.RoomDatabaseKt__RoomDatabaseKt.C03531) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r1 = r0.label
            int r1 = r1 - r2
            r0.label = r1
            goto L19
        L14:
            androidx.room.RoomDatabaseKt__RoomDatabaseKt$useWriterConnection$1 r0 = new androidx.room.RoomDatabaseKt__RoomDatabaseKt$useWriterConnection$1
            r0.<init>(r8)
        L19:
            java.lang.Object r1 = r0.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r0.label
            switch(r3) {
                case 0: goto L45;
                case 1: goto L35;
                case 2: goto L2c;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L2c:
            java.lang.Object r6 = r0.L$0
            androidx.room.RoomDatabase r6 = (androidx.room.RoomDatabase) r6
            kotlin.ResultKt.throwOnFailure(r1)
            r7 = r1
            goto L77
        L35:
            java.lang.Object r6 = r0.L$1
            kotlin.jvm.functions.Function2 r6 = (kotlin.jvm.functions.Function2) r6
            java.lang.Object r7 = r0.L$0
            androidx.room.RoomDatabase r7 = (androidx.room.RoomDatabase) r7
            kotlin.ResultKt.throwOnFailure(r1)
            r3 = r7
            r7 = r6
            r6 = r3
            r3 = r1
            goto L57
        L45:
            kotlin.ResultKt.throwOnFailure(r1)
            r0.L$0 = r6
            r0.L$1 = r7
            r3 = 1
            r0.label = r3
            r3 = 0
            java.lang.Object r3 = androidx.room.util.DBUtil.getCoroutineContext(r6, r3, r0)
            if (r3 != r2) goto L57
            return r2
        L57:
            kotlin.coroutines.CoroutineContext r3 = (kotlin.coroutines.CoroutineContext) r3
            androidx.room.RoomExternalOperationElement r4 = androidx.room.RoomExternalOperationElement.INSTANCE
            kotlin.coroutines.CoroutineContext r4 = (kotlin.coroutines.CoroutineContext) r4
            kotlin.coroutines.CoroutineContext r3 = r3.plus(r4)
            androidx.room.RoomDatabaseKt__RoomDatabaseKt$useWriterConnection$2 r4 = new androidx.room.RoomDatabaseKt__RoomDatabaseKt$useWriterConnection$2
            r5 = 0
            r4.<init>(r6, r7, r5)
            kotlin.jvm.functions.Function2 r4 = (kotlin.jvm.functions.Function2) r4
            r0.L$0 = r6
            r0.L$1 = r5
            r5 = 2
            r0.label = r5
            java.lang.Object r7 = kotlinx.coroutines.BuildersKt.withContext(r3, r4, r0)
            if (r7 != r2) goto L77
            return r2
        L77:
            r2 = 0
            androidx.room.InvalidationTracker r3 = r6.getInvalidationTracker()
            r3.refreshAsync()
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.room.RoomDatabaseKt__RoomDatabaseKt.useWriterConnection(androidx.room.RoomDatabase, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static final void validateMigrationsNotRequired(Set<Integer> migrationStartAndEndVersions, Set<Integer> migrationsNotRequiredFrom) {
        Intrinsics.checkNotNullParameter(migrationStartAndEndVersions, "migrationStartAndEndVersions");
        Intrinsics.checkNotNullParameter(migrationsNotRequiredFrom, "migrationsNotRequiredFrom");
        if (!migrationStartAndEndVersions.isEmpty()) {
            Iterator<Integer> it = migrationStartAndEndVersions.iterator();
            while (it.hasNext()) {
                int version = it.next().intValue();
                if (migrationsNotRequiredFrom.contains(Integer.valueOf(version))) {
                    throw new IllegalArgumentException(("Inconsistency detected. A Migration was supplied to addMigration() that has a start or end version equal to a start version supplied to fallbackToDestructiveMigrationFrom(). Start version is: " + version).toString());
                }
            }
        }
    }

    public static final void validateAutoMigrations(RoomDatabase $this$validateAutoMigrations, DatabaseConfiguration configuration) {
        Intrinsics.checkNotNullParameter($this$validateAutoMigrations, "<this>");
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        Map autoMigrationSpecs = new LinkedHashMap();
        Set<KClass<? extends AutoMigrationSpec>> requiredAutoMigrationSpecClasses = $this$validateAutoMigrations.getRequiredAutoMigrationSpecClasses();
        boolean[] usedSpecs = new boolean[configuration.autoMigrationSpecs.size()];
        Iterator<KClass<? extends AutoMigrationSpec>> it = requiredAutoMigrationSpecClasses.iterator();
        while (true) {
            if (it.hasNext()) {
                KClass<? extends AutoMigrationSpec> next = it.next();
                int foundIndex = -1;
                int size = configuration.autoMigrationSpecs.size() - 1;
                if (size >= 0) {
                    while (true) {
                        int providedIndex = size;
                        size--;
                        Object provided = configuration.autoMigrationSpecs.get(providedIndex);
                        if (!next.isInstance(provided)) {
                            if (size < 0) {
                                break;
                            }
                        } else {
                            foundIndex = providedIndex;
                            usedSpecs[foundIndex] = true;
                            break;
                        }
                    }
                }
                if (!(foundIndex >= 0)) {
                    throw new IllegalArgumentException(("A required auto migration spec (" + next.getQualifiedName() + ") is missing in the database configuration.").toString());
                }
                autoMigrationSpecs.put(next, configuration.autoMigrationSpecs.get(foundIndex));
            } else {
                int size2 = configuration.autoMigrationSpecs.size() - 1;
                if (size2 >= 0) {
                    do {
                        int providedIndex2 = size2;
                        size2--;
                        if (!(providedIndex2 < usedSpecs.length && usedSpecs[providedIndex2])) {
                            throw new IllegalArgumentException("Unexpected auto migration specs found. Annotate AutoMigrationSpec implementation with @ProvidedAutoMigrationSpec annotation or remove this spec from the builder.".toString());
                        }
                    } while (size2 >= 0);
                }
                for (Migration autoMigration : $this$validateAutoMigrations.createAutoMigrations(autoMigrationSpecs)) {
                    boolean migrationExists = configuration.migrationContainer.contains(autoMigration.startVersion, autoMigration.endVersion);
                    if (!migrationExists) {
                        configuration.migrationContainer.addMigration(autoMigration);
                    }
                }
                return;
            }
        }
    }

    public static final void validateTypeConverters(RoomDatabase $this$validateTypeConverters, DatabaseConfiguration configuration) {
        Map<KClass<?>, List<KClass<?>>> map;
        boolean z;
        Intrinsics.checkNotNullParameter($this$validateTypeConverters, "<this>");
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        Map<KClass<?>, List<KClass<?>>> requiredTypeConverterClassesMap$room_runtime = $this$validateTypeConverters.getRequiredTypeConverterClassesMap$room_runtime();
        boolean[] used = new boolean[configuration.typeConverters.size()];
        for (Map.Entry<KClass<?>, List<KClass<?>>> entry : requiredTypeConverterClassesMap$room_runtime.entrySet()) {
            KClass<?> key = entry.getKey();
            for (KClass<?> kClass : entry.getValue()) {
                int foundIndex = -1;
                int size = configuration.typeConverters.size() - 1;
                if (size >= 0) {
                    while (true) {
                        int providedIndex = size;
                        size--;
                        z = true;
                        map = requiredTypeConverterClassesMap$room_runtime;
                        Object provided = configuration.typeConverters.get(providedIndex);
                        if (!kClass.isInstance(provided)) {
                            if (size < 0) {
                                break;
                            } else {
                                requiredTypeConverterClassesMap$room_runtime = map;
                            }
                        } else {
                            foundIndex = providedIndex;
                            used[foundIndex] = true;
                            break;
                        }
                    }
                } else {
                    map = requiredTypeConverterClassesMap$room_runtime;
                    z = true;
                }
                if (!(foundIndex >= 0 ? z : false)) {
                    throw new IllegalArgumentException(("A required type converter (" + kClass.getQualifiedName() + ") for " + key.getQualifiedName() + " is missing in the database configuration.").toString());
                }
                $this$validateTypeConverters.addTypeConverter$room_runtime(kClass, configuration.typeConverters.get(foundIndex));
                requiredTypeConverterClassesMap$room_runtime = map;
            }
        }
        int size2 = configuration.typeConverters.size() - 1;
        if (size2 >= 0) {
            do {
                int providedIndex2 = size2;
                size2--;
                if (!used[providedIndex2]) {
                    Object converter = configuration.typeConverters.get(providedIndex2);
                    throw new IllegalArgumentException("Unexpected type converter " + converter + ". Annotate TypeConverter class with @ProvidedTypeConverter annotation or remove this converter from the builder.");
                }
            } while (size2 >= 0);
        }
    }
}
