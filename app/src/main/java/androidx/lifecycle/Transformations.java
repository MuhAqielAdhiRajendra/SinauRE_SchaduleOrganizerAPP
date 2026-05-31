package androidx.lifecycle;

import androidx.arch.core.util.Function;
import androidx.lifecycle.Transformations;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* JADX INFO: compiled from: Transformations.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001aB\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0003\"\u0004\b\u0001\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00030\u00012\u001c\u0010\u0004\u001a\u0018\u0012\t\u0012\u0007H\u0003¢\u0006\u0002\b\u0006\u0012\t\u0012\u0007H\u0002¢\u0006\u0002\b\u00060\u0005H\u0007\u001a8\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0003\"\u0004\b\u0001\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00030\u00012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u0002H\u00020\bH\u0007\u001aJ\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0003\"\u0004\b\u0001\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00030\u00012$\u0010\u0004\u001a \u0012\t\u0012\u0007H\u0003¢\u0006\u0002\b\u0006\u0012\u0011\u0012\u000f\u0012\u0004\u0012\u0002H\u0002\u0018\u00010\u0001¢\u0006\u0002\b\u00060\u0005H\u0007\u001a>\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0003\"\u0004\b\u0001\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00030\u00012\u0018\u0010\n\u001a\u0014\u0012\u0004\u0012\u0002H\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00010\bH\u0007\u001a\u001e\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\u0001H\u0007¨\u0006\f"}, d2 = {"map", "Landroidx/lifecycle/LiveData;", "Y", "X", "transform", "Lkotlin/Function1;", "Lkotlin/jvm/JvmSuppressWildcards;", "mapFunction", "Landroidx/arch/core/util/Function;", "switchMap", "switchMapFunction", "distinctUntilChanged", "lifecycle-livedata"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class Transformations {
    public static final <X, Y> LiveData<Y> map(LiveData<X> liveData, final Function1<X, Y> transform) {
        final MediatorLiveData result;
        Intrinsics.checkNotNullParameter(liveData, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        if (liveData.isInitialized()) {
            result = new MediatorLiveData(transform.invoke(liveData.getValue()));
        } else {
            result = new MediatorLiveData();
        }
        result.addSource(liveData, new Transformations$sam$androidx_lifecycle_Observer$0(new Function1() { // from class: androidx.lifecycle.Transformations$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Transformations.map$lambda$0(result, transform, obj);
            }
        }));
        return result;
    }

    static final Unit map$lambda$0(MediatorLiveData $result, Function1 $transform, Object x) {
        $result.setValue($transform.invoke(x));
        return Unit.INSTANCE;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Use kotlin functions, instead of outdated arch core Functions")
    public static final /* synthetic */ LiveData map(LiveData $this$map, final Function mapFunction) {
        Intrinsics.checkNotNullParameter($this$map, "<this>");
        Intrinsics.checkNotNullParameter(mapFunction, "mapFunction");
        final MediatorLiveData result = new MediatorLiveData();
        result.addSource($this$map, new Transformations$sam$androidx_lifecycle_Observer$0(new Function1() { // from class: androidx.lifecycle.Transformations$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Transformations.map$lambda$1(result, mapFunction, obj);
            }
        }));
        return result;
    }

    static final Unit map$lambda$1(MediatorLiveData $result, Function $mapFunction, Object x) {
        $result.setValue($mapFunction.apply(x));
        return Unit.INSTANCE;
    }

    public static final <X, Y> LiveData<Y> switchMap(LiveData<X> liveData, final Function1<X, LiveData<Y>> transform) {
        final MediatorLiveData result;
        LiveData<Y> liveDataInvoke;
        Intrinsics.checkNotNullParameter(liveData, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        final Ref.ObjectRef liveData2 = new Ref.ObjectRef();
        if (liveData.isInitialized() && (liveDataInvoke = transform.invoke(liveData.getValue())) != null && liveDataInvoke.isInitialized()) {
            result = new MediatorLiveData(liveDataInvoke.getValue());
        } else {
            result = new MediatorLiveData();
        }
        result.addSource(liveData, new Transformations$sam$androidx_lifecycle_Observer$0(new Function1() { // from class: androidx.lifecycle.Transformations$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Transformations.switchMap$lambda$0(transform, liveData2, result, obj);
            }
        }));
        return result;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [T, androidx.lifecycle.LiveData] */
    static final Unit switchMap$lambda$0(Function1 $transform, Ref.ObjectRef $liveData, final MediatorLiveData $result, Object value) {
        ?? r0 = (LiveData) $transform.invoke(value);
        if ($liveData.element != r0) {
            if ($liveData.element != 0) {
                T t = $liveData.element;
                Intrinsics.checkNotNull(t);
                $result.removeSource((LiveData) t);
            }
            $liveData.element = r0;
            if ($liveData.element != 0) {
                T t2 = $liveData.element;
                Intrinsics.checkNotNull(t2);
                $result.addSource((LiveData) t2, new Transformations$sam$androidx_lifecycle_Observer$0(new Function1() { // from class: androidx.lifecycle.Transformations$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return Transformations.switchMap$lambda$0$0($result, obj);
                    }
                }));
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit switchMap$lambda$0$0(MediatorLiveData $result, Object y) {
        $result.setValue(y);
        return Unit.INSTANCE;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Use kotlin functions, instead of outdated arch core Functions")
    public static final /* synthetic */ LiveData switchMap(LiveData $this$switchMap, Function switchMapFunction) {
        Intrinsics.checkNotNullParameter($this$switchMap, "<this>");
        Intrinsics.checkNotNullParameter(switchMapFunction, "switchMapFunction");
        MediatorLiveData result = new MediatorLiveData();
        result.addSource($this$switchMap, new AnonymousClass2(switchMapFunction, result));
        return result;
    }

    /* JADX INFO: Add missing generic type declarations: [X] */
    /* JADX INFO: renamed from: androidx.lifecycle.Transformations$switchMap$2, reason: invalid class name */
    /* JADX INFO: compiled from: Transformations.kt */
    @Metadata(d1 = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u0015\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u000bR\"\u0010\u0002\u001a\n\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007¨\u0006\f"}, d2 = {"androidx/lifecycle/Transformations$switchMap$2", "Landroidx/lifecycle/Observer;", "liveData", "Landroidx/lifecycle/LiveData;", "getLiveData", "()Landroidx/lifecycle/LiveData;", "setLiveData", "(Landroidx/lifecycle/LiveData;)V", "onChanged", "", "value", "(Ljava/lang/Object;)V", "lifecycle-livedata"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class AnonymousClass2<X> implements Observer<X> {
        final /* synthetic */ MediatorLiveData<Y> $result;
        final /* synthetic */ Function<X, LiveData<Y>> $switchMapFunction;
        private LiveData<Y> liveData;

        AnonymousClass2(Function<X, LiveData<Y>> function, MediatorLiveData<Y> mediatorLiveData) {
            this.$switchMapFunction = function;
            this.$result = mediatorLiveData;
        }

        public final LiveData<Y> getLiveData() {
            return this.liveData;
        }

        public final void setLiveData(LiveData<Y> liveData) {
            this.liveData = liveData;
        }

        /* JADX WARN: Type inference fix 'apply assigned field type' failed
        java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
        	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
        	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
        	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
         */
        @Override // androidx.lifecycle.Observer
        public void onChanged(X value) {
            LiveData newLiveData = (LiveData) this.$switchMapFunction.apply(value);
            if (this.liveData == newLiveData) {
                return;
            }
            if (this.liveData != null) {
                MediatorLiveData<Y> mediatorLiveData = this.$result;
                Object obj = this.liveData;
                Intrinsics.checkNotNull(obj);
                mediatorLiveData.removeSource(obj);
            }
            this.liveData = newLiveData;
            if (this.liveData != null) {
                MediatorLiveData<Y> mediatorLiveData2 = this.$result;
                Object obj2 = this.liveData;
                Intrinsics.checkNotNull(obj2);
                final MediatorLiveData<Y> mediatorLiveData3 = this.$result;
                mediatorLiveData2.addSource(obj2, new Transformations$sam$androidx_lifecycle_Observer$0(new Function1() { // from class: androidx.lifecycle.Transformations$switchMap$2$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj3) {
                        return Transformations.AnonymousClass2.onChanged$lambda$0(mediatorLiveData3, obj3);
                    }
                }));
            }
        }

        static final Unit onChanged$lambda$0(MediatorLiveData $result, Object y) {
            $result.setValue(y);
            return Unit.INSTANCE;
        }
    }

    public static final <X> LiveData<X> distinctUntilChanged(LiveData<X> liveData) {
        final MediatorLiveData outputLiveData;
        Intrinsics.checkNotNullParameter(liveData, "<this>");
        final Ref.BooleanRef firstTime = new Ref.BooleanRef();
        firstTime.element = true;
        if (liveData.isInitialized()) {
            firstTime.element = false;
            outputLiveData = new MediatorLiveData(liveData.getValue());
        } else {
            outputLiveData = new MediatorLiveData();
        }
        outputLiveData.addSource(liveData, new Transformations$sam$androidx_lifecycle_Observer$0(new Function1() { // from class: androidx.lifecycle.Transformations$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Transformations.distinctUntilChanged$lambda$0(outputLiveData, firstTime, obj);
            }
        }));
        return outputLiveData;
    }

    static final Unit distinctUntilChanged$lambda$0(MediatorLiveData $outputLiveData, Ref.BooleanRef $firstTime, Object value) {
        Object previousValue = $outputLiveData.getValue();
        if ($firstTime.element || ((previousValue == null && value != null) || (previousValue != null && !Intrinsics.areEqual(previousValue, value)))) {
            $firstTime.element = false;
            $outputLiveData.setValue(value);
        }
        return Unit.INSTANCE;
    }
}
