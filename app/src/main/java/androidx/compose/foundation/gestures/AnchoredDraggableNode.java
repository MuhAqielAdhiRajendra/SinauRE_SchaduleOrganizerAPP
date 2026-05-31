package androidx.compose.foundation.gestures;

import androidx.autofill.HintConstants;
import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.foundation.OverscrollEffect;
import androidx.compose.foundation.gestures.AnchoredDraggableNode;
import androidx.compose.foundation.gestures.DragEvent;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.nestedscroll.NestedScrollSource;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.compose.ui.unit.Velocity;
import androidx.compose.ui.unit.VelocityKt;
import androidx.core.app.NotificationCompat;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: AnchoredDraggable.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0084\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\n\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002BW\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\b\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\b\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010¢\u0006\u0004\b\u0011\u0010\u0012J\b\u0010\u001d\u001a\u00020\u001eH\u0016J\b\u0010\u001f\u001a\u00020\u001eH\u0016J\b\u0010 \u001a\u00020\u001eH\u0002J\u0012\u0010!\u001a\u00020\u001e2\b\u0010\"\u001a\u0004\u0018\u00010\u0010H\u0002JM\u0010#\u001a\u00020\u001e2=\u0010$\u001a9\b\u0001\u0012\u001f\u0012\u001d\u0012\u0013\u0012\u00110'¢\u0006\f\b(\u0012\b\b)\u0012\u0004\b\b(*\u0012\u0004\u0012\u00020\u001e0&\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001e0+\u0012\u0006\u0012\u0004\u0018\u00010,0%H\u0096@¢\u0006\u0002\u0010-J\u0017\u0010.\u001a\u00020\u001e2\u0006\u0010/\u001a\u000200H\u0016¢\u0006\u0004\b1\u00102J\u0010\u00103\u001a\u00020\u001e2\u0006\u00104\u001a\u000205H\u0016J\u0016\u00106\u001a\u0002072\u0006\u00108\u001a\u000207H\u0082@¢\u0006\u0002\u00109J\b\u0010\u000e\u001a\u00020\bH\u0016J[\u0010:\u001a\u00020\u001e2\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\b2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\b2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010¢\u0006\u0002\u0010\u0012J\u0013\u0010;\u001a\u000200*\u000207H\u0002¢\u0006\u0004\b<\u0010=J\u0013\u0010>\u001a\u00020?*\u000207H\u0002¢\u0006\u0004\b@\u0010=J\u0013\u0010A\u001a\u000207*\u00020?H\u0002¢\u0006\u0004\bB\u0010CJ\u0013\u0010A\u001a\u000207*\u000200H\u0002¢\u0006\u0004\bD\u0010CJ\u0013\u0010E\u001a\u00020?*\u00020?H\u0002¢\u0006\u0004\bF\u0010GJ\u0013\u0010E\u001a\u000200*\u000200H\u0002¢\u0006\u0004\bH\u0010GR\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\t\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0013R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u000e\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0013R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0014\u001a\u00020\u0010X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u001b\u001a\u00020\b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001c¨\u0006I"}, d2 = {"Landroidx/compose/foundation/gestures/AnchoredDraggableNode;", "T", "Landroidx/compose/foundation/gestures/DragGestureNode;", "state", "Landroidx/compose/foundation/gestures/AnchoredDraggableState;", "orientation", "Landroidx/compose/foundation/gestures/Orientation;", "enabled", "", "reverseDirection", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "overscrollEffect", "Landroidx/compose/foundation/OverscrollEffect;", "startDragImmediately", "flingBehavior", "Landroidx/compose/foundation/gestures/FlingBehavior;", "<init>", "(Landroidx/compose/foundation/gestures/AnchoredDraggableState;Landroidx/compose/foundation/gestures/Orientation;ZLjava/lang/Boolean;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/foundation/OverscrollEffect;Ljava/lang/Boolean;Landroidx/compose/foundation/gestures/FlingBehavior;)V", "Ljava/lang/Boolean;", "resolvedFlingBehavior", "getResolvedFlingBehavior", "()Landroidx/compose/foundation/gestures/FlingBehavior;", "setResolvedFlingBehavior", "(Landroidx/compose/foundation/gestures/FlingBehavior;)V", "density", "Landroidx/compose/ui/unit/Density;", "isReverseDirection", "()Z", "onAttach", "", "onDensityChange", "updateDensity", "updateFlingBehavior", "newFlingBehavior", "drag", "forEachDelta", "Lkotlin/Function2;", "Lkotlin/Function1;", "Landroidx/compose/foundation/gestures/DragEvent$DragDelta;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "dragDelta", "Lkotlin/coroutines/Continuation;", "", "(Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onDragStarted", "startedPosition", "Landroidx/compose/ui/geometry/Offset;", "onDragStarted-k-4lQ0M", "(J)V", "onDragStopped", NotificationCompat.CATEGORY_EVENT, "Landroidx/compose/foundation/gestures/DragEvent$DragStopped;", "fling", "", "velocity", "(FLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "update", "toOffset", "toOffset-tuRUvjQ", "(F)J", "toVelocity", "Landroidx/compose/ui/unit/Velocity;", "toVelocity-adjELrA", "toFloat", "toFloat-TH1AsA0", "(J)F", "toFloat-k-4lQ0M", "reverseIfNeeded", "reverseIfNeeded-AH228Gc", "(J)J", "reverseIfNeeded-MK-Hz9U", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
final class AnchoredDraggableNode<T> extends DragGestureNode {
    private Density density;
    private FlingBehavior flingBehavior;
    private Orientation orientation;
    private OverscrollEffect overscrollEffect;
    public FlingBehavior resolvedFlingBehavior;
    private Boolean reverseDirection;
    private Boolean startDragImmediately;
    private AnchoredDraggableState<T> state;

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.AnchoredDraggableNode$fling$1, reason: invalid class name */
    /* JADX INFO: compiled from: AnchoredDraggable.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.AnchoredDraggableNode", f = "AnchoredDraggable.kt", i = {1}, l = {459, 462}, m = "fling", n = {"leftoverVelocity"}, s = {"L$0"}, v = 1)
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ AnchoredDraggableNode<T> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(AnchoredDraggableNode<T> anchoredDraggableNode, Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
            this.this$0 = anchoredDraggableNode;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return this.this$0.fling(0.0f, this);
        }
    }

    public AnchoredDraggableNode(AnchoredDraggableState<T> anchoredDraggableState, Orientation orientation, boolean enabled, Boolean reverseDirection, MutableInteractionSource interactionSource, OverscrollEffect overscrollEffect, Boolean startDragImmediately, FlingBehavior flingBehavior) {
        super(AnchoredDraggableKt.AlwaysDrag, enabled, interactionSource, orientation);
        this.state = anchoredDraggableState;
        this.orientation = orientation;
        this.reverseDirection = reverseDirection;
        this.overscrollEffect = overscrollEffect;
        this.startDragImmediately = startDragImmediately;
        this.flingBehavior = flingBehavior;
    }

    public final FlingBehavior getResolvedFlingBehavior() {
        FlingBehavior flingBehavior = this.resolvedFlingBehavior;
        if (flingBehavior != null) {
            return flingBehavior;
        }
        Intrinsics.throwUninitializedPropertyAccessException("resolvedFlingBehavior");
        return null;
    }

    public final void setResolvedFlingBehavior(FlingBehavior flingBehavior) {
        this.resolvedFlingBehavior = flingBehavior;
    }

    private final boolean isReverseDirection() {
        if (this.reverseDirection == null) {
            return DelegatableNodeKt.requireLayoutDirection(this) == LayoutDirection.Rtl && this.orientation == Orientation.Horizontal;
        }
        Boolean bool = this.reverseDirection;
        Intrinsics.checkNotNull(bool);
        return bool.booleanValue();
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void onAttach() {
        updateFlingBehavior(this.flingBehavior);
    }

    @Override // androidx.compose.ui.node.DelegatableNode, androidx.compose.ui.node.PointerInputModifierNode
    public void onDensityChange() {
        onCancelPointerInput();
        if (getIsAttached()) {
            updateDensity();
        }
    }

    private final void updateDensity() {
        Density newDensity = DelegatableNodeKt.requireDensity(this);
        if (this.density == null || !Intrinsics.areEqual(this.density, newDensity)) {
            this.density = newDensity;
            updateFlingBehavior(this.flingBehavior);
        }
    }

    private final void updateFlingBehavior(FlingBehavior newFlingBehavior) {
        TargetedFlingBehavior targetedFlingBehaviorAnchoredDraggableFlingBehavior;
        if (newFlingBehavior != null) {
            targetedFlingBehaviorAnchoredDraggableFlingBehavior = newFlingBehavior;
        } else {
            AnimationSpec<Float> snapAnimationSpec = AnchoredDraggableDefaults.INSTANCE.getSnapAnimationSpec();
            Function1<Float, Float> positionalThreshold = AnchoredDraggableDefaults.INSTANCE.getPositionalThreshold();
            Density it = DelegatableNodeKt.requireDensity(this);
            this.density = it;
            targetedFlingBehaviorAnchoredDraggableFlingBehavior = AnchoredDraggableKt.anchoredDraggableFlingBehavior(this.state, it, positionalThreshold, snapAnimationSpec);
        }
        setResolvedFlingBehavior(targetedFlingBehaviorAnchoredDraggableFlingBehavior);
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.AnchoredDraggableNode$drag$2, reason: invalid class name */
    /* JADX INFO: compiled from: AnchoredDraggable.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0005H\n"}, d2 = {"<anonymous>", "", "T", "Landroidx/compose/foundation/gestures/AnchoredDragScope;", "it", "Landroidx/compose/foundation/gestures/DraggableAnchors;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.AnchoredDraggableNode$drag$2", f = "AnchoredDraggable.kt", i = {}, l = {412}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class AnonymousClass2 extends SuspendLambda implements Function3<AnchoredDragScope, DraggableAnchors<T>, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function2<Function1<? super DragEvent.DragDelta, Unit>, Continuation<? super Unit>, Object> $forEachDelta;
        private /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ AnchoredDraggableNode<T> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass2(Function2<? super Function1<? super DragEvent.DragDelta, Unit>, ? super Continuation<? super Unit>, ? extends Object> function2, AnchoredDraggableNode<T> anchoredDraggableNode, Continuation<? super AnonymousClass2> continuation) {
            super(3, continuation);
            this.$forEachDelta = function2;
            this.this$0 = anchoredDraggableNode;
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(AnchoredDragScope anchoredDragScope, DraggableAnchors<T> draggableAnchors, Continuation<? super Unit> continuation) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$forEachDelta, this.this$0, continuation);
            anonymousClass2.L$0 = anchoredDragScope;
            return anonymousClass2.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    final AnchoredDragScope $this$anchoredDrag = (AnchoredDragScope) this.L$0;
                    Function2<Function1<? super DragEvent.DragDelta, Unit>, Continuation<? super Unit>, Object> function2 = this.$forEachDelta;
                    final AnchoredDraggableNode<T> anchoredDraggableNode = this.this$0;
                    Function1<? super DragEvent.DragDelta, Unit> function1 = new Function1() { // from class: androidx.compose.foundation.gestures.AnchoredDraggableNode$drag$2$$ExternalSyntheticLambda1
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return AnchoredDraggableNode.AnonymousClass2.invokeSuspend$lambda$0(anchoredDraggableNode, $this$anchoredDrag, (DragEvent.DragDelta) obj);
                        }
                    };
                    this.label = 1;
                    if (function2.invoke(function1, this) == coroutine_suspended) {
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

        static final Unit invokeSuspend$lambda$0(final AnchoredDraggableNode this$0, final AnchoredDragScope $$this$anchoredDrag, DragEvent.DragDelta dragDelta) {
            float oneDirectionalDelta = this$0.m447toFloatk4lQ0M(this$0.m445reverseIfNeededMKHz9U(dragDelta.getDelta()));
            if (this$0.overscrollEffect == null) {
                AnchoredDragScope.dragTo$default($$this$anchoredDrag, this$0.state.newOffsetForDelta$foundation(oneDirectionalDelta), 0.0f, 2, null);
            } else {
                OverscrollEffect overscrollEffect = this$0.overscrollEffect;
                Intrinsics.checkNotNull(overscrollEffect);
                Offset.m5057boximpl(overscrollEffect.mo264applyToScrollRhakbz0(this$0.m448toOffsettuRUvjQ(oneDirectionalDelta), NestedScrollSource.INSTANCE.m6519getUserInputWNlRxjI(), new Function1() { // from class: androidx.compose.foundation.gestures.AnchoredDraggableNode$drag$2$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return AnchoredDraggableNode.AnonymousClass2.invokeSuspend$lambda$0$0(this$0, $$this$anchoredDrag, (Offset) obj);
                    }
                }));
            }
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Offset invokeSuspend$lambda$0$0(AnchoredDraggableNode this$0, AnchoredDragScope $$this$anchoredDrag, Offset deltaForDrag) {
            float dragOffset = this$0.state.newOffsetForDelta$foundation(this$0.m447toFloatk4lQ0M(deltaForDrag.m5078unboximpl()));
            long consumedDelta = this$0.m448toOffsettuRUvjQ(dragOffset - this$0.state.requireOffset());
            AnchoredDragScope.dragTo$default($$this$anchoredDrag, dragOffset, 0.0f, 2, null);
            return Offset.m5057boximpl(consumedDelta);
        }
    }

    @Override // androidx.compose.foundation.gestures.DragGestureNode
    public Object drag(Function2<? super Function1<? super DragEvent.DragDelta, Unit>, ? super Continuation<? super Unit>, ? extends Object> function2, Continuation<? super Unit> continuation) {
        Object objAnchoredDrag$default = AnchoredDraggableState.anchoredDrag$default(this.state, null, new AnonymousClass2(function2, this, null), continuation, 1, null);
        return objAnchoredDrag$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAnchoredDrag$default : Unit.INSTANCE;
    }

    @Override // androidx.compose.foundation.gestures.DragGestureNode
    /* JADX INFO: renamed from: onDragStarted-k-4lQ0M, reason: not valid java name */
    public void mo450onDragStartedk4lQ0M(long startedPosition) {
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.AnchoredDraggableNode$onDragStopped$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: AnchoredDraggable.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.AnchoredDraggableNode$onDragStopped$1", f = "AnchoredDraggable.kt", i = {}, l = {438, 440}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class C01481 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ DragEvent.DragStopped $event;
        int label;
        final /* synthetic */ AnchoredDraggableNode<T> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01481(AnchoredDraggableNode<T> anchoredDraggableNode, DragEvent.DragStopped dragStopped, Continuation<? super C01481> continuation) {
            super(2, continuation);
            this.this$0 = anchoredDraggableNode;
            this.$event = dragStopped;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C01481(this.this$0, this.$event, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C01481) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    float oneDirectionalVelocity = this.this$0.m446toFloatTH1AsA0(this.this$0.m444reverseIfNeededAH228Gc(this.$event.getVelocity()));
                    OverscrollEffect overscrollEffect = ((AnchoredDraggableNode) this.this$0).overscrollEffect;
                    AnchoredDraggableNode<T> anchoredDraggableNode = this.this$0;
                    if (overscrollEffect == null) {
                        this.label = 1;
                        if (anchoredDraggableNode.fling(oneDirectionalVelocity, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        return Unit.INSTANCE;
                    }
                    OverscrollEffect overscrollEffect2 = ((AnchoredDraggableNode) anchoredDraggableNode).overscrollEffect;
                    Intrinsics.checkNotNull(overscrollEffect2);
                    this.label = 2;
                    if (overscrollEffect2.mo263applyToFlingBMRW4eQ(this.this$0.m449toVelocityadjELrA(oneDirectionalVelocity), new C00131(this.this$0, null), this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    Unit unit = Unit.INSTANCE;
                    return Unit.INSTANCE;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return Unit.INSTANCE;
                case 2:
                    ResultKt.throwOnFailure($result);
                    Unit unit2 = Unit.INSTANCE;
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        /* JADX INFO: renamed from: androidx.compose.foundation.gestures.AnchoredDraggableNode$onDragStopped$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: AnchoredDraggable.kt */
        @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n"}, d2 = {"<anonymous>", "Landroidx/compose/ui/unit/Velocity;", "availableVelocity"}, k = 3, mv = {2, 1, 0}, xi = 48)
        @DebugMetadata(c = "androidx.compose.foundation.gestures.AnchoredDraggableNode$onDragStopped$1$1", f = "AnchoredDraggable.kt", i = {0}, l = {442}, m = "invokeSuspend", n = {"availableVelocity"}, s = {"J$0"}, v = 1)
        static final class C00131 extends SuspendLambda implements Function2<Velocity, Continuation<? super Velocity>, Object> {
            /* synthetic */ long J$0;
            int label;
            final /* synthetic */ AnchoredDraggableNode<T> this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C00131(AnchoredDraggableNode<T> anchoredDraggableNode, Continuation<? super C00131> continuation) {
                super(2, continuation);
                this.this$0 = anchoredDraggableNode;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                C00131 c00131 = new C00131(this.this$0, continuation);
                c00131.J$0 = ((Velocity) obj).getPackedValue();
                return c00131;
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Object invoke(Velocity velocity, Continuation<? super Velocity> continuation) {
                return m452invokesFctU(velocity.getPackedValue(), continuation);
            }

            /* JADX INFO: renamed from: invoke-sF-c-tU, reason: not valid java name */
            public final Object m452invokesFctU(long j, Continuation<? super Velocity> continuation) {
                return ((C00131) create(Velocity.m8379boximpl(j), continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object $result) {
                long availableVelocity;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        availableVelocity = this.J$0;
                        this.J$0 = availableVelocity;
                        this.label = 1;
                        Object objFling = this.this$0.fling(this.this$0.m446toFloatTH1AsA0(availableVelocity), this);
                        if (objFling == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        $result = objFling;
                        break;
                    case 1:
                        long availableVelocity2 = this.J$0;
                        ResultKt.throwOnFailure($result);
                        availableVelocity = availableVelocity2;
                        break;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                float consumed = ((Number) $result).floatValue();
                float currentOffset = ((AnchoredDraggableNode) this.this$0).state.requireOffset();
                float minAnchor = ((AnchoredDraggableNode) this.this$0).state.getAnchors().minPosition();
                float maxAnchor = ((AnchoredDraggableNode) this.this$0).state.getAnchors().maxPosition();
                if (currentOffset >= maxAnchor || currentOffset <= minAnchor) {
                    availableVelocity = this.this$0.m449toVelocityadjELrA(consumed);
                }
                return Velocity.m8379boximpl(availableVelocity);
            }
        }
    }

    @Override // androidx.compose.foundation.gestures.DragGestureNode
    public void onDragStopped(DragEvent.DragStopped event) {
        if (getIsAttached()) {
            BuildersKt__Builders_commonKt.launch$default(getCoroutineScope(), null, null, new C01481(this, event, null), 3, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object fling(float r11, kotlin.coroutines.Continuation<? super java.lang.Float> r12) {
        /*
            r10 = this;
            boolean r0 = r12 instanceof androidx.compose.foundation.gestures.AnchoredDraggableNode.AnonymousClass1
            if (r0 == 0) goto L14
            r0 = r12
            androidx.compose.foundation.gestures.AnchoredDraggableNode$fling$1 r0 = (androidx.compose.foundation.gestures.AnchoredDraggableNode.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r1 = r0.label
            int r1 = r1 - r2
            r0.label = r1
            goto L19
        L14:
            androidx.compose.foundation.gestures.AnchoredDraggableNode$fling$1 r0 = new androidx.compose.foundation.gestures.AnchoredDraggableNode$fling$1
            r0.<init>(r10, r12)
        L19:
            r4 = r0
            java.lang.Object r0 = r4.result
            java.lang.Object r7 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r4.label
            switch(r1) {
                case 0: goto L3b;
                case 1: goto L36;
                case 2: goto L2e;
                default: goto L25;
            }
        L25:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r0)
            throw r11
        L2e:
            java.lang.Object r11 = r4.L$0
            kotlin.jvm.internal.Ref$FloatRef r11 = (kotlin.jvm.internal.Ref.FloatRef) r11
            kotlin.ResultKt.throwOnFailure(r0)
            goto L77
        L36:
            kotlin.ResultKt.throwOnFailure(r0)
            r11 = r0
            goto L53
        L3b:
            kotlin.ResultKt.throwOnFailure(r0)
            r8 = r10
            androidx.compose.foundation.gestures.AnchoredDraggableState<T> r1 = r8.state
            boolean r1 = r1.getUsePreModifierChangeBehavior$foundation()
            if (r1 == 0) goto L54
            androidx.compose.foundation.gestures.AnchoredDraggableState<T> r1 = r8.state
            r2 = 1
            r4.label = r2
            java.lang.Object r11 = r1.settle(r11, r4)
            if (r11 != r7) goto L53
            return r7
        L53:
            return r11
        L54:
            kotlin.jvm.internal.Ref$FloatRef r1 = new kotlin.jvm.internal.Ref$FloatRef
            r1.<init>()
            r9 = r1
            r9.element = r11
            androidx.compose.foundation.gestures.AnchoredDraggableState<T> r1 = r8.state
            androidx.compose.foundation.gestures.AnchoredDraggableNode$fling$2 r2 = new androidx.compose.foundation.gestures.AnchoredDraggableNode$fling$2
            r3 = 0
            r2.<init>(r8, r9, r11, r3)
            r3 = r2
            kotlin.jvm.functions.Function3 r3 = (kotlin.jvm.functions.Function3) r3
            r4.L$0 = r9
            r2 = 2
            r4.label = r2
            r2 = 0
            r5 = 1
            r6 = 0
            java.lang.Object r11 = androidx.compose.foundation.gestures.AnchoredDraggableState.anchoredDrag$default(r1, r2, r3, r4, r5, r6)
            if (r11 != r7) goto L76
            return r7
        L76:
            r11 = r9
        L77:
            float r11 = r11.element
            java.lang.Float r11 = kotlin.coroutines.jvm.internal.Boxing.boxFloat(r11)
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.AnchoredDraggableNode.fling(float, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.AnchoredDraggableNode$fling$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: AnchoredDraggable.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0005H\n"}, d2 = {"<anonymous>", "", "T", "Landroidx/compose/foundation/gestures/AnchoredDragScope;", "it", "Landroidx/compose/foundation/gestures/DraggableAnchors;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.AnchoredDraggableNode$fling$2", f = "AnchoredDraggable.kt", i = {}, l = {473}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class C01472 extends SuspendLambda implements Function3<AnchoredDragScope, DraggableAnchors<T>, Continuation<? super Unit>, Object> {
        final /* synthetic */ Ref.FloatRef $leftoverVelocity;
        final /* synthetic */ float $velocity;
        private /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ AnchoredDraggableNode<T> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01472(AnchoredDraggableNode<T> anchoredDraggableNode, Ref.FloatRef floatRef, float f, Continuation<? super C01472> continuation) {
            super(3, continuation);
            this.this$0 = anchoredDraggableNode;
            this.$leftoverVelocity = floatRef;
            this.$velocity = f;
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(AnchoredDragScope anchoredDragScope, DraggableAnchors<T> draggableAnchors, Continuation<? super Unit> continuation) {
            C01472 c01472 = new C01472(this.this$0, this.$leftoverVelocity, this.$velocity, continuation);
            c01472.L$0 = anchoredDragScope;
            return c01472.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Ref.FloatRef floatRef;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    final AnchoredDragScope $this$anchoredDrag = (AnchoredDragScope) this.L$0;
                    final AnchoredDraggableNode<T> anchoredDraggableNode = this.this$0;
                    ScrollScope scrollScope = new ScrollScope() { // from class: androidx.compose.foundation.gestures.AnchoredDraggableNode$fling$2$scrollScope$1
                        @Override // androidx.compose.foundation.gestures.ScrollScope
                        public float scrollBy(float pixels) {
                            float newOffset = ((AnchoredDraggableNode) anchoredDraggableNode).state.newOffsetForDelta$foundation(pixels);
                            float consumed = newOffset - ((AnchoredDraggableNode) anchoredDraggableNode).state.getOffset();
                            AnchoredDragScope.dragTo$default($this$anchoredDrag, newOffset, 0.0f, 2, null);
                            return consumed;
                        }
                    };
                    FlingBehavior $this$invokeSuspend_u24lambda_u240 = this.this$0.getResolvedFlingBehavior();
                    Ref.FloatRef floatRef2 = this.$leftoverVelocity;
                    float f = this.$velocity;
                    this.L$0 = floatRef2;
                    this.label = 1;
                    Object objPerformFling = $this$invokeSuspend_u24lambda_u240.performFling(scrollScope, f, this);
                    if (objPerformFling == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    $result = objPerformFling;
                    floatRef = floatRef2;
                    break;
                case 1:
                    floatRef = (Ref.FloatRef) this.L$0;
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            floatRef.element = ((Number) $result).floatValue();
            return Unit.INSTANCE;
        }
    }

    @Override // androidx.compose.foundation.gestures.DragGestureNode
    /* JADX INFO: renamed from: startDragImmediately */
    public boolean getStartDragImmediately() {
        Boolean bool = this.startDragImmediately;
        return bool != null ? bool.booleanValue() : this.state.isAnimationRunning();
    }

    public final void update(AnchoredDraggableState<T> state, Orientation orientation, boolean enabled, Boolean reverseDirection, MutableInteractionSource interactionSource, OverscrollEffect overscrollEffect, Boolean startDragImmediately, FlingBehavior flingBehavior) {
        boolean resetPointerInputHandling;
        this.flingBehavior = flingBehavior;
        boolean resetPointerInputHandling2 = false;
        if (!Intrinsics.areEqual(this.state, state)) {
            this.state = state;
            updateFlingBehavior(flingBehavior);
            resetPointerInputHandling2 = true;
        }
        if (this.orientation != orientation) {
            this.orientation = orientation;
            resetPointerInputHandling2 = true;
        }
        if (Intrinsics.areEqual(this.reverseDirection, reverseDirection)) {
            resetPointerInputHandling = resetPointerInputHandling2;
        } else {
            this.reverseDirection = reverseDirection;
            resetPointerInputHandling = true;
        }
        this.startDragImmediately = startDragImmediately;
        this.overscrollEffect = overscrollEffect;
        DragGestureNode.update$default(this, null, enabled, interactionSource, orientation, resetPointerInputHandling, 1, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: toOffset-tuRUvjQ, reason: not valid java name */
    public final long m448toOffsettuRUvjQ(float $this$toOffset_u2dtuRUvjQ) {
        float x$iv = this.orientation == Orientation.Horizontal ? $this$toOffset_u2dtuRUvjQ : 0.0f;
        float y$iv = this.orientation == Orientation.Vertical ? $this$toOffset_u2dtuRUvjQ : 0.0f;
        float val2$iv$iv = y$iv;
        float val1$iv$iv = x$iv;
        long v1$iv$iv = Float.floatToRawIntBits(val1$iv$iv);
        long v2$iv$iv = Float.floatToRawIntBits(val2$iv$iv);
        return Offset.m5060constructorimpl((v1$iv$iv << 32) | (4294967295L & v2$iv$iv));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: toVelocity-adjELrA, reason: not valid java name */
    public final long m449toVelocityadjELrA(float $this$toVelocity_u2dadjELrA) {
        return VelocityKt.Velocity(this.orientation == Orientation.Horizontal ? $this$toVelocity_u2dadjELrA : 0.0f, this.orientation == Orientation.Vertical ? $this$toVelocity_u2dadjELrA : 0.0f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: toFloat-TH1AsA0, reason: not valid java name */
    public final float m446toFloatTH1AsA0(long $this$toFloat_u2dTH1AsA0) {
        return this.orientation == Orientation.Vertical ? Velocity.m8389getYimpl($this$toFloat_u2dTH1AsA0) : Velocity.m8388getXimpl($this$toFloat_u2dTH1AsA0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: toFloat-k-4lQ0M, reason: not valid java name */
    public final float m447toFloatk4lQ0M(long $this$toFloat_u2dk_u2d4lQ0M) {
        if (this.orientation == Orientation.Vertical) {
            int bits$iv$iv$iv = (int) (4294967295L & $this$toFloat_u2dk_u2d4lQ0M);
            return Float.intBitsToFloat(bits$iv$iv$iv);
        }
        int bits$iv$iv$iv2 = (int) ($this$toFloat_u2dk_u2d4lQ0M >> 32);
        return Float.intBitsToFloat(bits$iv$iv$iv2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: reverseIfNeeded-AH228Gc, reason: not valid java name */
    public final long m444reverseIfNeededAH228Gc(long $this$reverseIfNeeded_u2dAH228Gc) {
        return Velocity.m8394timesadjELrA($this$reverseIfNeeded_u2dAH228Gc, isReverseDirection() ? -1.0f : 1.0f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: reverseIfNeeded-MK-Hz9U, reason: not valid java name */
    public final long m445reverseIfNeededMKHz9U(long $this$reverseIfNeeded_u2dMK_u2dHz9U) {
        return Offset.m5075timestuRUvjQ($this$reverseIfNeeded_u2dMK_u2dHz9U, isReverseDirection() ? -1.0f : 1.0f);
    }
}
