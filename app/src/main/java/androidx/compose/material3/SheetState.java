package androidx.compose.material3;

import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.material3.SheetState;
import androidx.compose.material3.internal.AnchoredDraggableKt;
import androidx.compose.material3.internal.AnchoredDraggableState;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.runtime.saveable.SaverKt;
import androidx.compose.ui.unit.Density;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: SheetDefaults.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0011\b\u0007\u0018\u0000 L2\u00020\u0001:\u0001LBU\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\u0014\b\u0002\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00030\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\u0003¢\u0006\u0004\b\r\u0010\u000eBC\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\u0014\b\u0002\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00030\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\u0003¢\u0006\u0004\b\r\u0010\u0011J\u0006\u0010\u001e\u001a\u00020\u0006J\u000e\u0010#\u001a\u00020$H\u0086@¢\u0006\u0002\u0010%J\u000e\u0010&\u001a\u00020$H\u0086@¢\u0006\u0002\u0010%J\u000e\u0010'\u001a\u00020$H\u0086@¢\u0006\u0002\u0010%J\u000e\u0010(\u001a\u00020$H\u0086@¢\u0006\u0002\u0010%J0\u0010)\u001a\u00020$2\u0006\u0010\u001a\u001a\u00020\t2\f\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00060+2\b\b\u0002\u0010,\u001a\u00020\u0006H\u0080@¢\u0006\u0004\b-\u0010.J\u0018\u0010/\u001a\u00020$2\u0006\u0010\u001a\u001a\u00020\tH\u0080@¢\u0006\u0004\b0\u00101J\u0018\u00102\u001a\u00020$2\u0006\u0010,\u001a\u00020\u0006H\u0080@¢\u0006\u0004\b3\u00104R\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R \u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00030\u000bX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\f\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0013R\u0011\u0010\u0017\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u001a\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u0019R\u0011\u0010\u001c\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u0013R\u0011\u0010\u001d\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u0013R\u0011\u0010\u001f\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b \u0010\u0013R\u0011\u0010!\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\"\u0010\u0013R \u00105\u001a\b\u0012\u0004\u0012\u00020\u000606X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b7\u00108\"\u0004\b9\u0010:R \u0010;\u001a\b\u0012\u0004\u0012\u00020\t0<X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b=\u0010>\"\u0004\b?\u0010@R\u0014\u0010A\u001a\u00020\u00068@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bB\u0010CR \u0010D\u001a\b\u0012\u0004\u0012\u00020\u00060+X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bE\u0010F\"\u0004\bG\u0010HR \u0010I\u001a\b\u0012\u0004\u0012\u00020\u00060+X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bJ\u0010F\"\u0004\bK\u0010H¨\u0006M"}, d2 = {"Landroidx/compose/material3/SheetState;", "", "skipPartiallyExpanded", "", "positionalThreshold", "Lkotlin/Function0;", "", "velocityThreshold", "initialValue", "Landroidx/compose/material3/SheetValue;", "confirmValueChange", "Lkotlin/Function1;", "skipHiddenState", "<init>", "(ZLkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Landroidx/compose/material3/SheetValue;Lkotlin/jvm/functions/Function1;Z)V", "density", "Landroidx/compose/ui/unit/Density;", "(ZLandroidx/compose/ui/unit/Density;Landroidx/compose/material3/SheetValue;Lkotlin/jvm/functions/Function1;Z)V", "getSkipPartiallyExpanded$material3", "()Z", "getConfirmValueChange$material3", "()Lkotlin/jvm/functions/Function1;", "getSkipHiddenState$material3", "currentValue", "getCurrentValue", "()Landroidx/compose/material3/SheetValue;", "targetValue", "getTargetValue", "isVisible", "isAnimationRunning", "requireOffset", "hasExpandedState", "getHasExpandedState", "hasPartiallyExpandedState", "getHasPartiallyExpandedState", "expand", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "partialExpand", "show", "hide", "animateTo", "animationSpec", "Landroidx/compose/animation/core/FiniteAnimationSpec;", "velocity", "animateTo$material3", "(Landroidx/compose/material3/SheetValue;Landroidx/compose/animation/core/FiniteAnimationSpec;FLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "snapTo", "snapTo$material3", "(Landroidx/compose/material3/SheetValue;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "settle", "settle$material3", "(FLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "anchoredDraggableMotionSpec", "Landroidx/compose/animation/core/AnimationSpec;", "getAnchoredDraggableMotionSpec$material3", "()Landroidx/compose/animation/core/AnimationSpec;", "setAnchoredDraggableMotionSpec$material3", "(Landroidx/compose/animation/core/AnimationSpec;)V", "anchoredDraggableState", "Landroidx/compose/material3/internal/AnchoredDraggableState;", "getAnchoredDraggableState$material3", "()Landroidx/compose/material3/internal/AnchoredDraggableState;", "setAnchoredDraggableState$material3", "(Landroidx/compose/material3/internal/AnchoredDraggableState;)V", TypedValues.CycleType.S_WAVE_OFFSET, "getOffset$material3", "()F", "showMotionSpec", "getShowMotionSpec$material3", "()Landroidx/compose/animation/core/FiniteAnimationSpec;", "setShowMotionSpec$material3", "(Landroidx/compose/animation/core/FiniteAnimationSpec;)V", "hideMotionSpec", "getHideMotionSpec$material3", "setHideMotionSpec$material3", "Companion", "material3"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class SheetState {
    public static final int $stable = 0;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private AnimationSpec<Float> anchoredDraggableMotionSpec;
    private AnchoredDraggableState<SheetValue> anchoredDraggableState;
    private final Function1<SheetValue, Boolean> confirmValueChange;
    private FiniteAnimationSpec<Float> hideMotionSpec;
    private FiniteAnimationSpec<Float> showMotionSpec;
    private final boolean skipHiddenState;
    private final boolean skipPartiallyExpanded;

    /* JADX WARN: Multi-variable type inference failed */
    public SheetState(boolean skipPartiallyExpanded, final Function0<Float> function0, Function0<Float> function02, SheetValue initialValue, Function1<? super SheetValue, Boolean> function1, boolean skipHiddenState) {
        this.skipPartiallyExpanded = skipPartiallyExpanded;
        this.confirmValueChange = function1;
        this.skipHiddenState = skipHiddenState;
        if (this.skipPartiallyExpanded) {
            if (!(initialValue != SheetValue.PartiallyExpanded)) {
                throw new IllegalArgumentException("The initial value must not be set to PartiallyExpanded if skipPartiallyExpanded is set to true.".toString());
            }
        }
        if (this.skipHiddenState) {
            if (!(initialValue != SheetValue.Hidden)) {
                throw new IllegalArgumentException("The initial value must not be set to Hidden if skipHiddenState is set to true.".toString());
            }
        }
        this.anchoredDraggableMotionSpec = SheetDefaultsKt.BottomSheetAnimationSpec;
        this.anchoredDraggableState = new AnchoredDraggableState<>(initialValue, new Function1() { // from class: androidx.compose.material3.SheetState$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Function0 function03 = function0;
                ((Float) obj).floatValue();
                return Float.valueOf(((Number) function03.invoke()).floatValue());
            }
        }, function02, new Function0() { // from class: androidx.compose.material3.SheetState$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return this.f$0.anchoredDraggableMotionSpec;
            }
        }, this.confirmValueChange);
        this.showMotionSpec = AnimationSpecKt.snap$default(0, 1, null);
        this.hideMotionSpec = AnimationSpecKt.snap$default(0, 1, null);
    }

    public /* synthetic */ SheetState(boolean z, Function0 function0, Function0 function02, SheetValue sheetValue, Function1 function1, boolean z2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(z, function0, function02, (i & 8) != 0 ? SheetValue.Hidden : sheetValue, (i & 16) != 0 ? new Function1() { // from class: androidx.compose.material3.SheetState$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(SheetState._init_$lambda$0((SheetValue) obj));
            }
        } : function1, (i & 32) != 0 ? false : z2);
    }

    /* JADX INFO: renamed from: getSkipPartiallyExpanded$material3, reason: from getter */
    public final boolean getSkipPartiallyExpanded() {
        return this.skipPartiallyExpanded;
    }

    static final boolean _init_$lambda$0(SheetValue it) {
        return true;
    }

    public final Function1<SheetValue, Boolean> getConfirmValueChange$material3() {
        return this.confirmValueChange;
    }

    /* JADX INFO: renamed from: getSkipHiddenState$material3, reason: from getter */
    public final boolean getSkipHiddenState() {
        return this.skipHiddenState;
    }

    public final SheetValue getCurrentValue() {
        return this.anchoredDraggableState.getCurrentValue();
    }

    public final SheetValue getTargetValue() {
        return this.anchoredDraggableState.getTargetValue();
    }

    public final boolean isVisible() {
        return this.anchoredDraggableState.getCurrentValue() != SheetValue.Hidden;
    }

    public final boolean isAnimationRunning() {
        return this.anchoredDraggableState.isAnimationRunning();
    }

    public final float requireOffset() {
        return this.anchoredDraggableState.requireOffset();
    }

    public final boolean getHasExpandedState() {
        return this.anchoredDraggableState.getAnchors().hasAnchorFor(SheetValue.Expanded);
    }

    public final boolean getHasPartiallyExpandedState() {
        return this.anchoredDraggableState.getAnchors().hasAnchorFor(SheetValue.PartiallyExpanded);
    }

    public final Object expand(Continuation<? super Unit> continuation) {
        Object objAnimateTo$material3$default;
        return (this.confirmValueChange.invoke(SheetValue.Expanded).booleanValue() && (objAnimateTo$material3$default = animateTo$material3$default(this, SheetValue.Expanded, this.showMotionSpec, 0.0f, continuation, 4, null)) == IntrinsicsKt.getCOROUTINE_SUSPENDED()) ? objAnimateTo$material3$default : Unit.INSTANCE;
    }

    public final Object partialExpand(Continuation<? super Unit> continuation) {
        Object objAnimateTo$material3$default;
        if (this.skipPartiallyExpanded) {
            throw new IllegalStateException("Attempted to animate to partial expanded when skipPartiallyExpanded was enabled. Set skipPartiallyExpanded to false to use this function.".toString());
        }
        return (this.confirmValueChange.invoke(SheetValue.PartiallyExpanded).booleanValue() && (objAnimateTo$material3$default = animateTo$material3$default(this, SheetValue.PartiallyExpanded, this.hideMotionSpec, 0.0f, continuation, 4, null)) == IntrinsicsKt.getCOROUTINE_SUSPENDED()) ? objAnimateTo$material3$default : Unit.INSTANCE;
    }

    public final Object show(Continuation<? super Unit> continuation) {
        Object objAnimateTo$material3$default;
        SheetValue targetValue = getHasPartiallyExpandedState() ? SheetValue.PartiallyExpanded : SheetValue.Expanded;
        return (this.confirmValueChange.invoke(targetValue).booleanValue() && (objAnimateTo$material3$default = animateTo$material3$default(this, targetValue, this.showMotionSpec, 0.0f, continuation, 4, null)) == IntrinsicsKt.getCOROUTINE_SUSPENDED()) ? objAnimateTo$material3$default : Unit.INSTANCE;
    }

    public final Object hide(Continuation<? super Unit> continuation) {
        Object objAnimateTo$material3$default;
        if (this.skipHiddenState) {
            throw new IllegalStateException("Attempted to animate to hidden when skipHiddenState was enabled. Set skipHiddenState to false to use this function.".toString());
        }
        return (this.confirmValueChange.invoke(SheetValue.Hidden).booleanValue() && (objAnimateTo$material3$default = animateTo$material3$default(this, SheetValue.Hidden, this.hideMotionSpec, 0.0f, continuation, 4, null)) == IntrinsicsKt.getCOROUTINE_SUSPENDED()) ? objAnimateTo$material3$default : Unit.INSTANCE;
    }

    public static /* synthetic */ Object animateTo$material3$default(SheetState sheetState, SheetValue sheetValue, FiniteAnimationSpec finiteAnimationSpec, float f, Continuation continuation, int i, Object obj) {
        if ((i & 4) != 0) {
            f = sheetState.anchoredDraggableState.getLastVelocity();
        }
        return sheetState.animateTo$material3(sheetValue, finiteAnimationSpec, f, continuation);
    }

    public final Object animateTo$material3(SheetValue targetValue, FiniteAnimationSpec<Float> finiteAnimationSpec, float velocity, Continuation<? super Unit> continuation) throws Throwable {
        Object objAnchoredDrag$default = AnchoredDraggableState.anchoredDrag$default(this.anchoredDraggableState, targetValue, null, new SheetState$animateTo$2(this, velocity, finiteAnimationSpec, null), continuation, 2, null);
        return objAnchoredDrag$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAnchoredDrag$default : Unit.INSTANCE;
    }

    public final Object snapTo$material3(SheetValue targetValue, Continuation<? super Unit> continuation) throws Throwable {
        Object objSnapTo = AnchoredDraggableKt.snapTo(this.anchoredDraggableState, targetValue, continuation);
        return objSnapTo == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objSnapTo : Unit.INSTANCE;
    }

    public final Object settle$material3(float velocity, Continuation<? super Unit> continuation) throws Throwable {
        Object obj = this.anchoredDraggableState.settle(velocity, continuation);
        return obj == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? obj : Unit.INSTANCE;
    }

    public final AnimationSpec<Float> getAnchoredDraggableMotionSpec$material3() {
        return this.anchoredDraggableMotionSpec;
    }

    public final void setAnchoredDraggableMotionSpec$material3(AnimationSpec<Float> animationSpec) {
        this.anchoredDraggableMotionSpec = animationSpec;
    }

    public final AnchoredDraggableState<SheetValue> getAnchoredDraggableState$material3() {
        return this.anchoredDraggableState;
    }

    public final void setAnchoredDraggableState$material3(AnchoredDraggableState<SheetValue> anchoredDraggableState) {
        this.anchoredDraggableState = anchoredDraggableState;
    }

    public final float getOffset$material3() {
        return this.anchoredDraggableState.getOffset();
    }

    public final FiniteAnimationSpec<Float> getShowMotionSpec$material3() {
        return this.showMotionSpec;
    }

    public final void setShowMotionSpec$material3(FiniteAnimationSpec<Float> finiteAnimationSpec) {
        this.showMotionSpec = finiteAnimationSpec;
    }

    public final FiniteAnimationSpec<Float> getHideMotionSpec$material3() {
        return this.hideMotionSpec;
    }

    public final void setHideMotionSpec$material3(FiniteAnimationSpec<Float> finiteAnimationSpec) {
        this.hideMotionSpec = finiteAnimationSpec;
    }

    /* JADX INFO: compiled from: SheetDefaults.kt */
    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003JR\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00052\u0006\u0010\b\u001a\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\t0\u000f2\u0006\u0010\u0010\u001a\u00020\tJ@\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00052\u0006\u0010\b\u001a\u00020\t2\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\t0\u000f2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0010\u001a\u00020\tH\u0007¨\u0006\u0013"}, d2 = {"Landroidx/compose/material3/SheetState$Companion;", "", "<init>", "()V", "Saver", "Landroidx/compose/runtime/saveable/Saver;", "Landroidx/compose/material3/SheetState;", "Landroidx/compose/material3/SheetValue;", "skipPartiallyExpanded", "", "positionalThreshold", "Lkotlin/Function0;", "", "velocityThreshold", "confirmValueChange", "Lkotlin/Function1;", "skipHiddenState", "density", "Landroidx/compose/ui/unit/Density;", "material3"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Saver<SheetState, SheetValue> Saver(final boolean skipPartiallyExpanded, final Function0<Float> positionalThreshold, final Function0<Float> velocityThreshold, final Function1<? super SheetValue, Boolean> confirmValueChange, final boolean skipHiddenState) {
            return SaverKt.Saver(new Function2() { // from class: androidx.compose.material3.SheetState$Companion$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ((SheetState) obj2).getCurrentValue();
                }
            }, new Function1() { // from class: androidx.compose.material3.SheetState$Companion$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return SheetState.Companion.Saver$lambda$1(skipPartiallyExpanded, positionalThreshold, velocityThreshold, confirmValueChange, skipHiddenState, (SheetValue) obj);
                }
            });
        }

        static final SheetState Saver$lambda$1(boolean $skipPartiallyExpanded, Function0 $positionalThreshold, Function0 $velocityThreshold, Function1 $confirmValueChange, boolean $skipHiddenState, SheetValue savedValue) {
            return new SheetState($skipPartiallyExpanded, $positionalThreshold, $velocityThreshold, savedValue, $confirmValueChange, $skipHiddenState);
        }

        @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility.")
        public final /* synthetic */ Saver Saver(boolean skipPartiallyExpanded, Function1 confirmValueChange, final Density density, boolean skipHiddenState) {
            return Saver(skipPartiallyExpanded, new Function0() { // from class: androidx.compose.material3.SheetState$Companion$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return Float.valueOf(density.mo432toPx0680j_4(BottomSheetDefaults.INSTANCE.m2193getPositionalThresholdD9Ej5fM$material3()));
                }
            }, new Function0() { // from class: androidx.compose.material3.SheetState$Companion$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return Float.valueOf(density.mo432toPx0680j_4(BottomSheetDefaults.INSTANCE.m2196getVelocityThresholdD9Ej5fM$material3()));
                }
            }, confirmValueChange, skipHiddenState);
        }
    }

    public /* synthetic */ SheetState(boolean z, Density density, SheetValue sheetValue, Function1 function1, boolean z2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(z, density, (i & 4) != 0 ? SheetValue.Hidden : sheetValue, (i & 8) != 0 ? new Function1() { // from class: androidx.compose.material3.SheetState$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(SheetState._init_$lambda$7((SheetValue) obj));
            }
        } : function1, (i & 16) != 0 ? false : z2);
    }

    static final boolean _init_$lambda$7(SheetValue it) {
        return true;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility.")
    public /* synthetic */ SheetState(boolean skipPartiallyExpanded, final Density density, SheetValue initialValue, Function1 confirmValueChange, boolean skipHiddenState) {
        this(skipPartiallyExpanded, new Function0() { // from class: androidx.compose.material3.SheetState$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Float.valueOf(density.mo432toPx0680j_4(BottomSheetDefaults.INSTANCE.m2193getPositionalThresholdD9Ej5fM$material3()));
            }
        }, new Function0() { // from class: androidx.compose.material3.SheetState$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Float.valueOf(density.mo432toPx0680j_4(BottomSheetDefaults.INSTANCE.m2196getVelocityThresholdD9Ej5fM$material3()));
            }
        }, initialValue, confirmValueChange, skipHiddenState);
    }
}
