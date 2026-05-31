package androidx.compose.animation.core;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimationVector;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import java.util.concurrent.CancellationException;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: Animatable.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u0000*\u0004\b\u0000\u0010\u0001*\b\b\u0001\u0010\u0002*\u00020\u00032\u00020\u0004B;\b\u0007\u0012\u0006\u0010\u0005\u001a\u00028\u0000\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00018\u0000\u0012\b\b\u0002\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fB1\b\u0017\u0012\u0006\u0010\u0005\u001a\u00028\u0000\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00018\u0000¢\u0006\u0004\b\u000b\u0010\rJ#\u0010>\u001a\u00020?2\n\b\u0002\u0010,\u001a\u0004\u0018\u00018\u00002\n\b\u0002\u0010.\u001a\u0004\u0018\u00018\u0000¢\u0006\u0002\u0010@Je\u0010A\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010B2\u0006\u0010'\u001a\u00028\u00002\u000e\b\u0002\u0010C\u001a\b\u0012\u0004\u0012\u00028\u00000D2\b\b\u0002\u0010E\u001a\u00028\u00002'\b\u0002\u0010F\u001a!\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0000\u0012\u0004\u0012\u00020?\u0018\u00010G¢\u0006\u0002\bHH\u0086@¢\u0006\u0002\u0010IJY\u0010J\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010B2\u0006\u0010E\u001a\u00028\u00002\f\u0010C\u001a\b\u0012\u0004\u0012\u00028\u00000K2'\b\u0002\u0010F\u001a!\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0000\u0012\u0004\u0012\u00020?\u0018\u00010G¢\u0006\u0002\bHH\u0086@¢\u0006\u0002\u0010LJ]\u0010M\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010B2\u0012\u0010N\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010O2\u0006\u0010E\u001a\u00028\u00002%\u0010F\u001a!\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0000\u0012\u0004\u0012\u00020?\u0018\u00010G¢\u0006\u0002\bHH\u0082@¢\u0006\u0002\u0010PJ\u0015\u0010Q\u001a\u00028\u00002\u0006\u0010\u0017\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010RJ\b\u0010S\u001a\u00020?H\u0002J\u0016\u0010T\u001a\u00020?2\u0006\u0010'\u001a\u00028\u0000H\u0086@¢\u0006\u0002\u0010UJ\u000e\u0010V\u001a\u00020?H\u0086@¢\u0006\u0002\u0010WJ\f\u0010X\u001a\b\u0012\u0004\u0012\u00028\u00000YR\u001d\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0012\u0010\b\u001a\u0004\u0018\u00018\u0000X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0010R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R \u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0014X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0017\u001a\u00028\u00008F¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u001a\u001a\u00028\u00018F¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\u001d\u001a\u00028\u00008F¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u0019R+\u0010!\u001a\u00020 2\u0006\u0010\u001f\u001a\u00020 8F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b%\u0010&\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R+\u0010'\u001a\u00028\u00002\u0006\u0010\u001f\u001a\u00028\u00008F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b+\u0010&\u001a\u0004\b(\u0010\u0019\"\u0004\b)\u0010*R$\u0010,\u001a\u0004\u0018\u00018\u00002\b\u0010\u0017\u001a\u0004\u0018\u00018\u0000@BX\u0086\u000e¢\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b-\u0010\u0019R$\u0010.\u001a\u0004\u0018\u00018\u00002\b\u0010\u0017\u001a\u0004\u0018\u00018\u0000@BX\u0086\u000e¢\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b/\u0010\u0019R\u000e\u00100\u001a\u000201X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u00102\u001a\b\u0012\u0004\u0012\u00028\u000003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b4\u00105R\u0016\u00106\u001a\u00028\u0001X\u0082\u0004¢\u0006\n\n\u0002\u00109\u0012\u0004\b7\u00108R\u0016\u0010:\u001a\u00028\u0001X\u0082\u0004¢\u0006\n\n\u0002\u00109\u0012\u0004\b;\u00108R\u0010\u0010<\u001a\u00028\u0001X\u0082\u000e¢\u0006\u0004\n\u0002\u00109R\u0010\u0010=\u001a\u00028\u0001X\u0082\u000e¢\u0006\u0004\n\u0002\u00109¨\u0006Z"}, d2 = {"Landroidx/compose/animation/core/Animatable;", "T", "V", "Landroidx/compose/animation/core/AnimationVector;", "", "initialValue", "typeConverter", "Landroidx/compose/animation/core/TwoWayConverter;", "visibilityThreshold", "label", "", "<init>", "(Ljava/lang/Object;Landroidx/compose/animation/core/TwoWayConverter;Ljava/lang/Object;Ljava/lang/String;)V", "(Ljava/lang/Object;Landroidx/compose/animation/core/TwoWayConverter;Ljava/lang/Object;)V", "getTypeConverter", "()Landroidx/compose/animation/core/TwoWayConverter;", "Ljava/lang/Object;", "getLabel", "()Ljava/lang/String;", "internalState", "Landroidx/compose/animation/core/AnimationState;", "getInternalState$animation_core", "()Landroidx/compose/animation/core/AnimationState;", "value", "getValue", "()Ljava/lang/Object;", "velocityVector", "getVelocityVector", "()Landroidx/compose/animation/core/AnimationVector;", "velocity", "getVelocity", "<set-?>", "", "isRunning", "()Z", "setRunning", "(Z)V", "isRunning$delegate", "Landroidx/compose/runtime/MutableState;", "targetValue", "getTargetValue", "setTargetValue", "(Ljava/lang/Object;)V", "targetValue$delegate", "lowerBound", "getLowerBound", "upperBound", "getUpperBound", "mutatorMutex", "Landroidx/compose/animation/core/MutatorMutex;", "defaultSpringSpec", "Landroidx/compose/animation/core/SpringSpec;", "getDefaultSpringSpec$animation_core", "()Landroidx/compose/animation/core/SpringSpec;", "negativeInfinityBounds", "getNegativeInfinityBounds$annotations", "()V", "Landroidx/compose/animation/core/AnimationVector;", "positiveInfinityBounds", "getPositiveInfinityBounds$annotations", "lowerBoundVector", "upperBoundVector", "updateBounds", "", "(Ljava/lang/Object;Ljava/lang/Object;)V", "animateTo", "Landroidx/compose/animation/core/AnimationResult;", "animationSpec", "Landroidx/compose/animation/core/AnimationSpec;", "initialVelocity", "block", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "(Ljava/lang/Object;Landroidx/compose/animation/core/AnimationSpec;Ljava/lang/Object;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "animateDecay", "Landroidx/compose/animation/core/DecayAnimationSpec;", "(Ljava/lang/Object;Landroidx/compose/animation/core/DecayAnimationSpec;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "runAnimation", "animation", "Landroidx/compose/animation/core/Animation;", "(Landroidx/compose/animation/core/Animation;Ljava/lang/Object;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "clampToBounds", "(Ljava/lang/Object;)Ljava/lang/Object;", "endAnimation", "snapTo", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "stop", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "asState", "Landroidx/compose/runtime/State;", "animation-core"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class Animatable<T, V extends AnimationVector> {
    public static final int $stable = 8;
    private final SpringSpec<T> defaultSpringSpec;
    private final AnimationState<T, V> internalState;

    /* JADX INFO: renamed from: isRunning$delegate, reason: from kotlin metadata */
    private final MutableState isRunning;
    private final String label;
    private T lowerBound;
    private V lowerBoundVector;
    private final MutatorMutex mutatorMutex;
    private final V negativeInfinityBounds;
    private final V positiveInfinityBounds;

    /* JADX INFO: renamed from: targetValue$delegate, reason: from kotlin metadata */
    private final MutableState targetValue;
    private final TwoWayConverter<T, V> typeConverter;
    private T upperBound;
    private V upperBoundVector;
    private final T visibilityThreshold;

    private static /* synthetic */ void getNegativeInfinityBounds$annotations() {
    }

    private static /* synthetic */ void getPositiveInfinityBounds$annotations() {
    }

    public Animatable(T t, TwoWayConverter<T, V> twoWayConverter, T t2, String str) {
        AnimationVector4D animationVector4D;
        AnimationVector4D animationVector4D2;
        this.typeConverter = twoWayConverter;
        this.visibilityThreshold = t2;
        this.label = str;
        this.internalState = new AnimationState<>(this.typeConverter, t, null, 0L, 0L, false, 60, null);
        this.isRunning = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
        this.targetValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(t, null, 2, null);
        this.mutatorMutex = new MutatorMutex();
        this.defaultSpringSpec = new SpringSpec<>(0.0f, 0.0f, this.visibilityThreshold, 3, null);
        AnimationVector velocityVector = getVelocityVector();
        if (velocityVector instanceof AnimationVector1D) {
            animationVector4D = AnimatableKt.negativeInfinityBounds1D;
        } else {
            animationVector4D = velocityVector instanceof AnimationVector2D ? AnimatableKt.negativeInfinityBounds2D : velocityVector instanceof AnimationVector3D ? AnimatableKt.negativeInfinityBounds3D : AnimatableKt.negativeInfinityBounds4D;
        }
        Intrinsics.checkNotNull(animationVector4D, "null cannot be cast to non-null type V of androidx.compose.animation.core.Animatable");
        this.negativeInfinityBounds = animationVector4D;
        AnimationVector velocityVector2 = getVelocityVector();
        if (velocityVector2 instanceof AnimationVector1D) {
            animationVector4D2 = AnimatableKt.positiveInfinityBounds1D;
        } else {
            animationVector4D2 = velocityVector2 instanceof AnimationVector2D ? AnimatableKt.positiveInfinityBounds2D : velocityVector2 instanceof AnimationVector3D ? AnimatableKt.positiveInfinityBounds3D : AnimatableKt.positiveInfinityBounds4D;
        }
        Intrinsics.checkNotNull(animationVector4D2, "null cannot be cast to non-null type V of androidx.compose.animation.core.Animatable");
        this.positiveInfinityBounds = animationVector4D2;
        this.lowerBoundVector = this.negativeInfinityBounds;
        this.upperBoundVector = this.positiveInfinityBounds;
    }

    public /* synthetic */ Animatable(Object obj, TwoWayConverter twoWayConverter, Object obj2, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(obj, twoWayConverter, (i & 4) != 0 ? null : obj2, (i & 8) != 0 ? "Animatable" : str);
    }

    public final TwoWayConverter<T, V> getTypeConverter() {
        return this.typeConverter;
    }

    public final String getLabel() {
        return this.label;
    }

    public /* synthetic */ Animatable(Object obj, TwoWayConverter twoWayConverter, Object obj2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(obj, twoWayConverter, (i & 4) != 0 ? null : obj2);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility", replaceWith = @ReplaceWith(expression = "Animatable(initialValue, typeConverter, visibilityThreshold, \"Animatable\")", imports = {}))
    public /* synthetic */ Animatable(Object initialValue, TwoWayConverter typeConverter, Object visibilityThreshold) {
        this(initialValue, typeConverter, visibilityThreshold, "Animatable");
    }

    public final AnimationState<T, V> getInternalState$animation_core() {
        return this.internalState;
    }

    public final T getValue() {
        return this.internalState.getValue();
    }

    public final V getVelocityVector() {
        return (V) this.internalState.getVelocityVector();
    }

    public final T getVelocity() {
        return (T) this.typeConverter.getConvertFromVector().invoke(getVelocityVector());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setRunning(boolean z) {
        MutableState $this$setValue$iv = this.isRunning;
        $this$setValue$iv.setValue(Boolean.valueOf(z));
    }

    public final boolean isRunning() {
        State $this$getValue$iv = this.isRunning;
        return ((Boolean) $this$getValue$iv.getValue()).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setTargetValue(T t) {
        MutableState $this$setValue$iv = this.targetValue;
        $this$setValue$iv.setValue(t);
    }

    public final T getTargetValue() {
        State $this$getValue$iv = this.targetValue;
        return $this$getValue$iv.getValue();
    }

    public final T getLowerBound() {
        return this.lowerBound;
    }

    public final T getUpperBound() {
        return this.upperBound;
    }

    public final SpringSpec<T> getDefaultSpringSpec$animation_core() {
        return this.defaultSpringSpec;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void updateBounds$default(Animatable animatable, Object obj, Object obj2, int i, Object obj3) {
        if ((i & 1) != 0) {
            obj = animatable.lowerBound;
        }
        if ((i & 2) != 0) {
            obj2 = animatable.upperBound;
        }
        animatable.updateBounds(obj, obj2);
    }

    public final void updateBounds(T lowerBound, T upperBound) {
        V vInvoke;
        V vInvoke2;
        if (lowerBound == null || (vInvoke = this.typeConverter.getConvertToVector().invoke(lowerBound)) == null) {
            vInvoke = this.negativeInfinityBounds;
        }
        if (upperBound == null || (vInvoke2 = this.typeConverter.getConvertToVector().invoke(upperBound)) == null) {
            vInvoke2 = this.positiveInfinityBounds;
        }
        int size = vInvoke.getSize();
        for (int i = 0; i < size; i++) {
            boolean value$iv = vInvoke.get$animation_core(i) <= vInvoke2.get$animation_core(i);
            if (!value$iv) {
                PreconditionsKt.throwIllegalStateException("Lower bound must be no greater than upper bound on *all* dimensions. The provided lower bound: " + vInvoke + " is greater than upper bound " + vInvoke2 + " on index " + i);
            }
        }
        this.lowerBoundVector = vInvoke;
        this.upperBoundVector = vInvoke2;
        this.upperBound = upperBound;
        this.lowerBound = lowerBound;
        if (isRunning()) {
            return;
        }
        T tClampToBounds = clampToBounds(getValue());
        if (Intrinsics.areEqual(tClampToBounds, getValue())) {
            return;
        }
        this.internalState.setValue$animation_core(tClampToBounds);
    }

    public final Object animateTo(T t, AnimationSpec<T> animationSpec, T t2, Function1<? super Animatable<T, V>, Unit> function1, Continuation<? super AnimationResult<T, V>> continuation) {
        TargetBasedAnimation anim = AnimationKt.TargetBasedAnimation(animationSpec, this.typeConverter, getValue(), t, t2);
        return runAnimation(anim, t2, function1, continuation);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Object animateDecay$default(Animatable animatable, Object obj, DecayAnimationSpec decayAnimationSpec, Function1 function1, Continuation continuation, int i, Object obj2) {
        if ((i & 4) != 0) {
            function1 = null;
        }
        return animatable.animateDecay(obj, decayAnimationSpec, function1, continuation);
    }

    public final Object animateDecay(T t, DecayAnimationSpec<T> decayAnimationSpec, Function1<? super Animatable<T, V>, Unit> function1, Continuation<? super AnimationResult<T, V>> continuation) {
        DecayAnimation anim = new DecayAnimation((DecayAnimationSpec) decayAnimationSpec, (TwoWayConverter) this.typeConverter, (Object) getValue(), (AnimationVector) this.typeConverter.getConvertToVector().invoke(t));
        return runAnimation(anim, t, function1, continuation);
    }

    /* JADX INFO: renamed from: androidx.compose.animation.core.Animatable$runAnimation$2, reason: invalid class name */
    /* JADX INFO: compiled from: Animatable.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\b\b\u0001\u0010\u0003*\u00020\u0004H\n"}, d2 = {"<anonymous>", "Landroidx/compose/animation/core/AnimationResult;", "T", "V", "Landroidx/compose/animation/core/AnimationVector;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.animation.core.Animatable$runAnimation$2", f = "Animatable.kt", i = {0, 0}, l = {308}, m = "invokeSuspend", n = {"endState", "clampingNeeded"}, s = {"L$0", "L$1"}, v = 1)
    static final class AnonymousClass2 extends SuspendLambda implements Function1<Continuation<? super AnimationResult<T, V>>, Object> {
        final /* synthetic */ Animation<T, V> $animation;
        final /* synthetic */ Function1<Animatable<T, V>, Unit> $block;
        final /* synthetic */ T $initialVelocity;
        final /* synthetic */ long $startTime;
        Object L$0;
        Object L$1;
        int label;
        final /* synthetic */ Animatable<T, V> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass2(Animatable<T, V> animatable, T t, Animation<T, V> animation, long j, Function1<? super Animatable<T, V>, Unit> function1, Continuation<? super AnonymousClass2> continuation) {
            super(1, continuation);
            this.this$0 = animatable;
            this.$initialVelocity = t;
            this.$animation = animation;
            this.$startTime = j;
            this.$block = function1;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Continuation<?> continuation) {
            return new AnonymousClass2(this.this$0, this.$initialVelocity, this.$animation, this.$startTime, this.$block, continuation);
        }

        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Continuation<? super AnimationResult<T, V>> continuation) {
            return ((AnonymousClass2) create(continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Ref.BooleanRef clampingNeeded;
            AnimationState endState;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            try {
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        this.this$0.getInternalState$animation_core().setVelocityVector$animation_core(this.this$0.getTypeConverter().getConvertToVector().invoke(this.$initialVelocity));
                        this.this$0.setTargetValue(this.$animation.getTargetValue());
                        this.this$0.setRunning(true);
                        final AnimationState endState2 = AnimationStateKt.copy$default((AnimationState) this.this$0.getInternalState$animation_core(), (Object) null, (AnimationVector) null, 0L, Long.MIN_VALUE, false, 23, (Object) null);
                        final Ref.BooleanRef clampingNeeded2 = new Ref.BooleanRef();
                        Animation<T, V> animation = this.$animation;
                        long j = this.$startTime;
                        final Animatable<T, V> animatable = this.this$0;
                        final Function1<Animatable<T, V>, Unit> function1 = this.$block;
                        this.L$0 = endState2;
                        this.L$1 = clampingNeeded2;
                        this.label = 1;
                        if (SuspendAnimationKt.animate(endState2, animation, j, new Function1() { // from class: androidx.compose.animation.core.Animatable$runAnimation$2$$ExternalSyntheticLambda0
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return Animatable.AnonymousClass2.invokeSuspend$lambda$0(animatable, endState2, function1, clampingNeeded2, (AnimationScope) obj);
                            }
                        }, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        clampingNeeded = clampingNeeded2;
                        endState = endState2;
                        break;
                        break;
                    case 1:
                        clampingNeeded = (Ref.BooleanRef) this.L$1;
                        endState = (AnimationState) this.L$0;
                        ResultKt.throwOnFailure($result);
                        break;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                AnimationEndReason endReason = clampingNeeded.element ? AnimationEndReason.BoundReached : AnimationEndReason.Finished;
                this.this$0.endAnimation();
                return new AnimationResult(endState, endReason);
            } catch (CancellationException e) {
                this.this$0.endAnimation();
                throw e;
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        static final Unit invokeSuspend$lambda$0(Animatable this$0, AnimationState animationState, Function1 $block, Ref.BooleanRef $clampingNeeded, AnimationScope $this$animate) {
            SuspendAnimationKt.updateState($this$animate, this$0.getInternalState$animation_core());
            Object clamped = this$0.clampToBounds($this$animate.getValue());
            if (!Intrinsics.areEqual(clamped, $this$animate.getValue())) {
                this$0.getInternalState$animation_core().setValue$animation_core(clamped);
                animationState.setValue$animation_core(clamped);
                if ($block != null) {
                    $block.invoke(this$0);
                }
                $this$animate.cancelAnimation();
                $clampingNeeded.element = true;
            } else if ($block != null) {
                $block.invoke(this$0);
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object runAnimation(Animation<T, V> animation, T t, Function1<? super Animatable<T, V>, Unit> function1, Continuation<? super AnimationResult<T, V>> continuation) {
        long startTime = this.internalState.getLastFrameTimeNanos();
        return MutatorMutex.mutate$default(this.mutatorMutex, null, new AnonymousClass2(this, t, animation, startTime, function1, null), continuation, 1, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final T clampToBounds(T value) {
        if (Intrinsics.areEqual(this.lowerBoundVector, this.negativeInfinityBounds) && Intrinsics.areEqual(this.upperBoundVector, this.positiveInfinityBounds)) {
            return value;
        }
        V vInvoke = this.typeConverter.getConvertToVector().invoke(value);
        boolean clamped = false;
        int size = vInvoke.getSize();
        for (int i = 0; i < size; i++) {
            if (vInvoke.get$animation_core(i) < this.lowerBoundVector.get$animation_core(i) || vInvoke.get$animation_core(i) > this.upperBoundVector.get$animation_core(i)) {
                clamped = true;
                vInvoke.set$animation_core(i, RangesKt.coerceIn(vInvoke.get$animation_core(i), this.lowerBoundVector.get$animation_core(i), this.upperBoundVector.get$animation_core(i)));
            }
        }
        if (clamped) {
            return this.typeConverter.getConvertFromVector().invoke(vInvoke);
        }
        return value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void endAnimation() {
        AnimationState<T, V> animationState = this.internalState;
        animationState.getVelocityVector().reset$animation_core();
        animationState.setLastFrameTimeNanos$animation_core(Long.MIN_VALUE);
        setRunning(false);
    }

    /* JADX INFO: renamed from: androidx.compose.animation.core.Animatable$snapTo$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Animatable.kt */
    @Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\u0002\u0010\u0000\u001a\u00020\u0001H\n"}, d2 = {"<anonymous>", ""}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.animation.core.Animatable$snapTo$2", f = "Animatable.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class C01212 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
        final /* synthetic */ T $targetValue;
        int label;
        final /* synthetic */ Animatable<T, V> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01212(Animatable<T, V> animatable, T t, Continuation<? super C01212> continuation) {
            super(1, continuation);
            this.this$0 = animatable;
            this.$targetValue = t;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Continuation<?> continuation) {
            return new C01212(this.this$0, this.$targetValue, continuation);
        }

        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Continuation<? super Unit> continuation) {
            return ((C01212) create(continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    this.this$0.endAnimation();
                    Object objClampToBounds = this.this$0.clampToBounds(this.$targetValue);
                    this.this$0.getInternalState$animation_core().setValue$animation_core(objClampToBounds);
                    this.this$0.setTargetValue(objClampToBounds);
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    public final Object snapTo(T t, Continuation<? super Unit> continuation) {
        Object objMutate$default = MutatorMutex.mutate$default(this.mutatorMutex, null, new C01212(this, t, null), continuation, 1, null);
        return objMutate$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objMutate$default : Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: androidx.compose.animation.core.Animatable$stop$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Animatable.kt */
    @Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\u0002\u0010\u0000\u001a\u00020\u0001H\n"}, d2 = {"<anonymous>", ""}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.animation.core.Animatable$stop$2", f = "Animatable.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class C01222 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
        int label;
        final /* synthetic */ Animatable<T, V> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01222(Animatable<T, V> animatable, Continuation<? super C01222> continuation) {
            super(1, continuation);
            this.this$0 = animatable;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Continuation<?> continuation) {
            return new C01222(this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Continuation<? super Unit> continuation) {
            return ((C01222) create(continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    this.this$0.endAnimation();
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    public final Object stop(Continuation<? super Unit> continuation) {
        Object objMutate$default = MutatorMutex.mutate$default(this.mutatorMutex, null, new C01222(this, null), continuation, 1, null);
        return objMutate$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objMutate$default : Unit.INSTANCE;
    }

    public final State<T> asState() {
        return this.internalState;
    }
}
