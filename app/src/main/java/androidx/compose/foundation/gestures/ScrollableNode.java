package androidx.compose.foundation.gestures;

import android.view.KeyEvent;
import androidx.autofill.HintConstants;
import androidx.compose.foundation.ComposeFoundationFlags;
import androidx.compose.foundation.MutatePriority;
import androidx.compose.foundation.OverscrollEffect;
import androidx.compose.foundation.gestures.DragEvent;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.relocation.BringIntoViewResponderNode;
import androidx.compose.ui.focus.FocusTargetModifierNode;
import androidx.compose.ui.focus.FocusTargetModifierNodeKt;
import androidx.compose.ui.focus.Focusability;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.key.Key;
import androidx.compose.ui.input.key.KeyEventType;
import androidx.compose.ui.input.key.KeyEvent_androidKt;
import androidx.compose.ui.input.key.KeyInputModifierNode;
import androidx.compose.ui.input.nestedscroll.NestedScrollDispatcher;
import androidx.compose.ui.input.nestedscroll.NestedScrollNodeKt;
import androidx.compose.ui.input.pointer.PointerEvent;
import androidx.compose.ui.input.pointer.PointerEventPass;
import androidx.compose.ui.input.pointer.PointerEventType;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.input.pointer.PointerType;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.SemanticsModifierNode;
import androidx.compose.ui.node.SemanticsModifierNodeKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Velocity;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.app.NotificationCompat;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendFunction;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.AdaptedFunctionReference;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: Scrollable.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000â\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0001\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004BO\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u000e\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013¢\u0006\u0004\b\u0014\u0010\u0015J\u0017\u00107\u001a\u0002082\u0006\u00109\u001a\u00020-H\u0016¢\u0006\u0004\b:\u0010;JM\u0010<\u001a\u0002082=\u0010=\u001a9\b\u0001\u0012\u001f\u0012\u001d\u0012\u0013\u0012\u00110?¢\u0006\f\b(\u0012\b\b)\u0012\u0004\b\b(@\u0012\u0004\u0012\u0002080>\u0012\n\u0012\b\u0012\u0004\u0012\u0002080.\u0012\u0006\u0012\u0004\u0018\u00010/0&H\u0096@¢\u0006\u0002\u0010AJ\u0017\u0010B\u001a\u0002082\u0006\u0010C\u001a\u00020-H\u0016¢\u0006\u0004\bD\u0010;J\u0010\u0010E\u001a\u0002082\u0006\u0010F\u001a\u00020GH\u0016J\u0017\u0010H\u001a\u0002082\u0006\u0010I\u001a\u00020JH\u0002¢\u0006\u0004\bK\u0010;J\u0017\u0010L\u001a\u0002082\u0006\u0010I\u001a\u00020JH\u0002¢\u0006\u0004\bM\u0010;J\b\u0010N\u001a\u00020\u000eH\u0016J\b\u0010O\u001a\u000208H\u0002J\b\u0010P\u001a\u000208H\u0002JN\u0010Q\u001a\u0002082\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e2\b\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013J\b\u0010R\u001a\u000208H\u0016J\b\u0010S\u001a\u000208H\u0002J\b\u0010T\u001a\u000208H\u0016J\u0017\u0010U\u001a\u00020\u000e2\u0006\u0010F\u001a\u00020VH\u0016¢\u0006\u0004\bW\u0010XJ\u0017\u0010Y\u001a\u00020\u000e2\u0006\u0010F\u001a\u00020VH\u0016¢\u0006\u0004\bZ\u0010XJ'\u0010[\u001a\u0002082\u0006\u0010\\\u001a\u00020]2\u0006\u0010^\u001a\u00020_2\u0006\u0010`\u001a\u00020aH\u0016¢\u0006\u0004\bb\u0010cJ\f\u0010d\u001a\u000208*\u00020eH\u0016J\b\u0010f\u001a\u000208H\u0002J\b\u0010g\u001a\u000208H\u0002R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0016\u001a\u00020\u000eX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020 X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\"X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020$X\u0082\u0004¢\u0006\u0002\n\u0000R@\u0010%\u001a4\u0012\u0013\u0012\u00110'¢\u0006\f\b(\u0012\b\b)\u0012\u0004\b\b(*\u0012\u0013\u0012\u00110'¢\u0006\f\b(\u0012\b\b)\u0012\u0004\b\b(+\u0012\u0004\u0012\u00020\u000e\u0018\u00010&X\u0082\u000e¢\u0006\u0002\n\u0000R.\u0010,\u001a \b\u0001\u0012\u0004\u0012\u00020-\u0012\n\u0012\b\u0012\u0004\u0012\u00020-0.\u0012\u0006\u0012\u0004\u0018\u00010/\u0018\u00010&X\u0082\u000e¢\u0006\u0004\n\u0002\u00100R\u0010\u00101\u001a\u0004\u0018\u000102X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00103\u001a\u0004\u0018\u000104X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00105\u001a\u0004\u0018\u000106X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006h"}, d2 = {"Landroidx/compose/foundation/gestures/ScrollableNode;", "Landroidx/compose/foundation/gestures/DragGestureNode;", "Landroidx/compose/ui/input/key/KeyInputModifierNode;", "Landroidx/compose/ui/node/SemanticsModifierNode;", "Landroidx/compose/foundation/gestures/OnScrollChangedDispatcher;", "state", "Landroidx/compose/foundation/gestures/ScrollableState;", "overscrollEffect", "Landroidx/compose/foundation/OverscrollEffect;", "flingBehavior", "Landroidx/compose/foundation/gestures/FlingBehavior;", "orientation", "Landroidx/compose/foundation/gestures/Orientation;", "enabled", "", "reverseDirection", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "bringIntoViewSpec", "Landroidx/compose/foundation/gestures/BringIntoViewSpec;", "<init>", "(Landroidx/compose/foundation/gestures/ScrollableState;Landroidx/compose/foundation/OverscrollEffect;Landroidx/compose/foundation/gestures/FlingBehavior;Landroidx/compose/foundation/gestures/Orientation;ZZLandroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/foundation/gestures/BringIntoViewSpec;)V", "shouldAutoInvalidate", "getShouldAutoInvalidate", "()Z", "nestedScrollDispatcher", "Landroidx/compose/ui/input/nestedscroll/NestedScrollDispatcher;", "defaultFlingBehavior", "Landroidx/compose/foundation/gestures/ScrollableDefaultFlingBehavior;", "scrollingLogic", "Landroidx/compose/foundation/gestures/ScrollingLogic;", "nestedScrollConnection", "Landroidx/compose/foundation/gestures/ScrollableNestedScrollConnection;", "focusTargetModifierNode", "Landroidx/compose/ui/focus/FocusTargetModifierNode;", "contentInViewNode", "Landroidx/compose/foundation/gestures/ContentInViewNode;", "scrollByAction", "Lkotlin/Function2;", "", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "x", "y", "scrollByOffsetAction", "Landroidx/compose/ui/geometry/Offset;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/jvm/functions/Function2;", "mouseWheelScrollingLogic", "Landroidx/compose/foundation/gestures/MouseWheelScrollingLogic;", "trackpadScrollingLogic", "Landroidx/compose/foundation/gestures/TrackpadScrollingLogic;", "scrollableContainerNode", "Landroidx/compose/foundation/gestures/ScrollableContainerNode;", "dispatchScrollDeltaInfo", "", "delta", "dispatchScrollDeltaInfo-k-4lQ0M", "(J)V", "drag", "forEachDelta", "Lkotlin/Function1;", "Landroidx/compose/foundation/gestures/DragEvent$DragDelta;", "dragDelta", "(Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onDragStarted", "startedPosition", "onDragStarted-k-4lQ0M", "onDragStopped", NotificationCompat.CATEGORY_EVENT, "Landroidx/compose/foundation/gestures/DragEvent$DragStopped;", "onWheelScrollStopped", "velocity", "Landroidx/compose/ui/unit/Velocity;", "onWheelScrollStopped-TH1AsA0", "onTrackpadScrollStopped", "onTrackpadScrollStopped-TH1AsA0", "startDragImmediately", "ensureMouseWheelScrollingLogicInitialized", "ensureTrackpadScrollingLogicInitialized", "update", "onAttach", "updateDefaultFlingBehavior", "onDensityChange", "onKeyEvent", "Landroidx/compose/ui/input/key/KeyEvent;", "onKeyEvent-ZmokQxo", "(Landroid/view/KeyEvent;)Z", "onPreKeyEvent", "onPreKeyEvent-ZmokQxo", "onPointerEvent", "pointerEvent", "Landroidx/compose/ui/input/pointer/PointerEvent;", "pass", "Landroidx/compose/ui/input/pointer/PointerEventPass;", "bounds", "Landroidx/compose/ui/unit/IntSize;", "onPointerEvent-H0pRuoY", "(Landroidx/compose/ui/input/pointer/PointerEvent;Landroidx/compose/ui/input/pointer/PointerEventPass;J)V", "applySemantics", "Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;", "setScrollSemanticsActions", "clearScrollSemanticsActions", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class ScrollableNode extends DragGestureNode implements KeyInputModifierNode, SemanticsModifierNode, OnScrollChangedDispatcher {
    public static final int $stable = 8;
    private final ContentInViewNode contentInViewNode;
    private final ScrollableDefaultFlingBehavior defaultFlingBehavior;
    private FlingBehavior flingBehavior;
    private final FocusTargetModifierNode focusTargetModifierNode;
    private MouseWheelScrollingLogic mouseWheelScrollingLogic;
    private final ScrollableNestedScrollConnection nestedScrollConnection;
    private final NestedScrollDispatcher nestedScrollDispatcher;
    private OverscrollEffect overscrollEffect;
    private Function2<? super Float, ? super Float, Boolean> scrollByAction;
    private Function2<? super Offset, ? super Continuation<? super Offset>, ? extends Object> scrollByOffsetAction;
    private ScrollableContainerNode scrollableContainerNode;
    private final ScrollingLogic scrollingLogic;
    private final boolean shouldAutoInvalidate;
    private TrackpadScrollingLogic trackpadScrollingLogic;

    public ScrollableNode(ScrollableState state, OverscrollEffect overscrollEffect, FlingBehavior flingBehavior, Orientation orientation, boolean enabled, boolean reverseDirection, MutableInteractionSource interactionSource, BringIntoViewSpec bringIntoViewSpec) {
        super(ScrollableKt.getCanDragCalculation(), enabled, interactionSource, orientation);
        this.overscrollEffect = overscrollEffect;
        this.flingBehavior = flingBehavior;
        this.nestedScrollDispatcher = new NestedScrollDispatcher();
        this.defaultFlingBehavior = Scrollable_androidKt.platformScrollableDefaultFlingBehavior();
        OverscrollEffect overscrollEffect2 = this.overscrollEffect;
        ScrollableDefaultFlingBehavior scrollableDefaultFlingBehavior = this.flingBehavior;
        this.scrollingLogic = new ScrollingLogic(state, overscrollEffect2, scrollableDefaultFlingBehavior == null ? this.defaultFlingBehavior : scrollableDefaultFlingBehavior, orientation, reverseDirection, this.nestedScrollDispatcher, this, new Function0() { // from class: androidx.compose.foundation.gestures.ScrollableNode$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(this.f$0.getIsAttached());
            }
        });
        this.nestedScrollConnection = new ScrollableNestedScrollConnection(this.scrollingLogic, enabled);
        this.focusTargetModifierNode = (FocusTargetModifierNode) delegate(FocusTargetModifierNodeKt.m4980FocusTargetModifierNodePYyLHbc$default(Focusability.INSTANCE.m5003getNeverLCbbffg(), null, 2, null));
        this.contentInViewNode = (ContentInViewNode) delegate(new ContentInViewNode(orientation, this.scrollingLogic, reverseDirection, bringIntoViewSpec, new Function0() { // from class: androidx.compose.foundation.gestures.ScrollableNode$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return FocusTargetModifierNodeKt.getFocusedRect(this.f$0.focusTargetModifierNode);
            }
        }));
        delegate(NestedScrollNodeKt.nestedScrollModifierNode(this.nestedScrollConnection, this.nestedScrollDispatcher));
        delegate(new BringIntoViewResponderNode(this.contentInViewNode));
        if (!ComposeFoundationFlags.isDelayPressesUsingGestureConsumptionEnabled) {
            this.scrollableContainerNode = (ScrollableContainerNode) delegate(new ScrollableContainerNode(enabled));
        }
    }

    @Override // androidx.compose.ui.Modifier.Node
    public boolean getShouldAutoInvalidate() {
        return this.shouldAutoInvalidate;
    }

    @Override // androidx.compose.foundation.gestures.OnScrollChangedDispatcher
    /* JADX INFO: renamed from: dispatchScrollDeltaInfo-k-4lQ0M */
    public void mo561dispatchScrollDeltaInfok4lQ0M(long delta) {
        if (getIsAttached()) {
            DelegatableNodeKt.m6952dispatchOnScrollChangedUv8p0NA(this, delta);
        }
    }

    @Override // androidx.compose.foundation.gestures.DragGestureNode
    public Object drag(Function2<? super Function1<? super DragEvent.DragDelta, Unit>, ? super Continuation<? super Unit>, ? extends Object> function2, Continuation<? super Unit> continuation) {
        ScrollingLogic $this$drag_u24lambda_u240 = this.scrollingLogic;
        Object objScroll = $this$drag_u24lambda_u240.scroll(MutatePriority.UserInput, new ScrollableNode$drag$2$1(function2, $this$drag_u24lambda_u240, null), continuation);
        return objScroll == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objScroll : Unit.INSTANCE;
    }

    @Override // androidx.compose.foundation.gestures.DragGestureNode
    /* JADX INFO: renamed from: onDragStarted-k-4lQ0M */
    public void mo450onDragStartedk4lQ0M(long startedPosition) {
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.ScrollableNode$onDragStopped$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Scrollable.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.ScrollableNode$onDragStopped$1", f = "Scrollable.kt", i = {}, l = {394}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class C01661 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ DragEvent.DragStopped $event;
        int label;
        final /* synthetic */ ScrollableNode this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01661(DragEvent.DragStopped dragStopped, ScrollableNode scrollableNode, Continuation<? super C01661> continuation) {
            super(2, continuation);
            this.$event = dragStopped;
            this.this$0 = scrollableNode;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C01661(this.$event, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C01661) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    float invertIndirectPointer = this.$event.getIsIndirectPointerEvent() ? -1.0f : 1.0f;
                    this.label = 1;
                    if (this.this$0.scrollingLogic.m616onScrollStoppedBMRW4eQ(Velocity.m8394timesadjELrA(this.$event.getVelocity(), invertIndirectPointer), false, this) == coroutine_suspended) {
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

    @Override // androidx.compose.foundation.gestures.DragGestureNode
    public void onDragStopped(DragEvent.DragStopped event) {
        BuildersKt__Builders_commonKt.launch$default(this.nestedScrollDispatcher.getCoroutineScope(), null, null, new C01661(event, this, null), 3, null);
    }

    /* JADX INFO: renamed from: onWheelScrollStopped-TH1AsA0, reason: not valid java name */
    private final void m603onWheelScrollStoppedTH1AsA0(long velocity) {
        BuildersKt__Builders_commonKt.launch$default(this.nestedScrollDispatcher.getCoroutineScope(), null, null, new ScrollableNode$onWheelScrollStopped$1(this, velocity, null), 3, null);
    }

    /* JADX INFO: renamed from: onTrackpadScrollStopped-TH1AsA0, reason: not valid java name */
    private final void m602onTrackpadScrollStoppedTH1AsA0(long velocity) {
        BuildersKt__Builders_commonKt.launch$default(this.nestedScrollDispatcher.getCoroutineScope(), null, null, new ScrollableNode$onTrackpadScrollStopped$1(this, velocity, null), 3, null);
    }

    @Override // androidx.compose.foundation.gestures.DragGestureNode
    /* JADX INFO: renamed from: startDragImmediately */
    public boolean getStartDragImmediately() {
        return this.scrollingLogic.shouldScrollImmediately();
    }

    private final void ensureMouseWheelScrollingLogicInitialized() {
        if (this.mouseWheelScrollingLogic == null) {
            this.mouseWheelScrollingLogic = new MouseWheelScrollingLogic(this.scrollingLogic, AndroidScrollable_androidKt.platformScrollConfig(this), new AnonymousClass1(this), DelegatableNodeKt.requireDensity(this));
        }
        MouseWheelScrollingLogic mouseWheelScrollingLogic = this.mouseWheelScrollingLogic;
        if (mouseWheelScrollingLogic != null) {
            mouseWheelScrollingLogic.startReceivingEvents(getCoroutineScope());
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.ScrollableNode$ensureMouseWheelScrollingLogicInitialized$1, reason: invalid class name */
    /* JADX INFO: compiled from: Scrollable.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    static final /* synthetic */ class AnonymousClass1 extends AdaptedFunctionReference implements Function2<Velocity, Continuation<? super Unit>, Object>, SuspendFunction {
        AnonymousClass1(Object obj) {
            super(2, obj, ScrollableNode.class, "onWheelScrollStopped", "onWheelScrollStopped-TH1AsA0(J)V", 4);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(Velocity velocity, Continuation<? super Unit> continuation) {
            return m604invokesFctU(velocity.getPackedValue(), continuation);
        }

        /* JADX INFO: renamed from: invoke-sF-c-tU, reason: not valid java name */
        public final Object m604invokesFctU(long p0, Continuation<? super Unit> continuation) {
            return ScrollableNode.ensureMouseWheelScrollingLogicInitialized$onWheelScrollStopped((ScrollableNode) this.receiver, p0, continuation);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final /* synthetic */ Object ensureMouseWheelScrollingLogicInitialized$onWheelScrollStopped(ScrollableNode $this$ensureMouseWheelScrollingLogicInitialized_u24onWheelScrollStopped, long p0, Continuation $completion) {
        $this$ensureMouseWheelScrollingLogicInitialized_u24onWheelScrollStopped.m603onWheelScrollStoppedTH1AsA0(p0);
        return Unit.INSTANCE;
    }

    private final void ensureTrackpadScrollingLogicInitialized() {
        if (this.trackpadScrollingLogic == null) {
            this.trackpadScrollingLogic = new TrackpadScrollingLogic(this.scrollingLogic, new C01651(this), DelegatableNodeKt.requireDensity(this));
        }
        TrackpadScrollingLogic trackpadScrollingLogic = this.trackpadScrollingLogic;
        if (trackpadScrollingLogic != null) {
            trackpadScrollingLogic.startReceivingEvents(getCoroutineScope());
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.ScrollableNode$ensureTrackpadScrollingLogicInitialized$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Scrollable.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    static final /* synthetic */ class C01651 extends AdaptedFunctionReference implements Function2<Velocity, Continuation<? super Unit>, Object>, SuspendFunction {
        C01651(Object obj) {
            super(2, obj, ScrollableNode.class, "onTrackpadScrollStopped", "onTrackpadScrollStopped-TH1AsA0(J)V", 4);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(Velocity velocity, Continuation<? super Unit> continuation) {
            return m605invokesFctU(velocity.getPackedValue(), continuation);
        }

        /* JADX INFO: renamed from: invoke-sF-c-tU, reason: not valid java name */
        public final Object m605invokesFctU(long p0, Continuation<? super Unit> continuation) {
            return ScrollableNode.ensureTrackpadScrollingLogicInitialized$onTrackpadScrollStopped((ScrollableNode) this.receiver, p0, continuation);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final /* synthetic */ Object ensureTrackpadScrollingLogicInitialized$onTrackpadScrollStopped(ScrollableNode $this$ensureTrackpadScrollingLogicInitialized_u24onTrackpadScrollStopped, long p0, Continuation $completion) {
        $this$ensureTrackpadScrollingLogicInitialized_u24onTrackpadScrollStopped.m602onTrackpadScrollStoppedTH1AsA0(p0);
        return Unit.INSTANCE;
    }

    public final void update(ScrollableState state, Orientation orientation, OverscrollEffect overscrollEffect, boolean enabled, boolean reverseDirection, FlingBehavior flingBehavior, MutableInteractionSource interactionSource, BringIntoViewSpec bringIntoViewSpec) {
        boolean shouldInvalidateSemantics;
        if (getEnabled() != enabled) {
            this.nestedScrollConnection.setEnabled(enabled);
            ScrollableContainerNode scrollableContainerNode = this.scrollableContainerNode;
            if (scrollableContainerNode != null) {
                scrollableContainerNode.update(enabled);
            }
            shouldInvalidateSemantics = true;
        } else {
            shouldInvalidateSemantics = false;
        }
        FlingBehavior resolvedFlingBehavior = flingBehavior == null ? this.defaultFlingBehavior : flingBehavior;
        boolean resetPointerInputHandling = this.scrollingLogic.update(state, orientation, overscrollEffect, reverseDirection, resolvedFlingBehavior, this.nestedScrollDispatcher);
        this.contentInViewNode.update(orientation, reverseDirection, bringIntoViewSpec);
        this.overscrollEffect = overscrollEffect;
        this.flingBehavior = flingBehavior;
        update(ScrollableKt.getCanDragCalculation(), enabled, interactionSource, this.scrollingLogic.isVertical() ? Orientation.Vertical : Orientation.Horizontal, resetPointerInputHandling);
        if (shouldInvalidateSemantics) {
            clearScrollSemanticsActions();
            SemanticsModifierNodeKt.invalidateSemantics(this);
        }
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void onAttach() {
        updateDefaultFlingBehavior();
        MouseWheelScrollingLogic mouseWheelScrollingLogic = this.mouseWheelScrollingLogic;
        if (mouseWheelScrollingLogic != null) {
            mouseWheelScrollingLogic.updateDensity(DelegatableNodeKt.requireDensity(this));
        }
        TrackpadScrollingLogic trackpadScrollingLogic = this.trackpadScrollingLogic;
        if (trackpadScrollingLogic != null) {
            trackpadScrollingLogic.updateDensity(DelegatableNodeKt.requireDensity(this));
        }
    }

    private final void updateDefaultFlingBehavior() {
        if (getIsAttached()) {
            Density density = DelegatableNodeKt.requireDensity(this);
            this.defaultFlingBehavior.updateDensity(density);
        }
    }

    @Override // androidx.compose.ui.node.DelegatableNode, androidx.compose.ui.node.PointerInputModifierNode
    public void onDensityChange() {
        onCancelPointerInput();
        updateDefaultFlingBehavior();
        MouseWheelScrollingLogic mouseWheelScrollingLogic = this.mouseWheelScrollingLogic;
        if (mouseWheelScrollingLogic != null) {
            mouseWheelScrollingLogic.updateDensity(DelegatableNodeKt.requireDensity(this));
        }
        TrackpadScrollingLogic trackpadScrollingLogic = this.trackpadScrollingLogic;
        if (trackpadScrollingLogic != null) {
            trackpadScrollingLogic.updateDensity(DelegatableNodeKt.requireDensity(this));
        }
    }

    @Override // androidx.compose.ui.input.key.KeyInputModifierNode
    /* JADX INFO: renamed from: onKeyEvent-ZmokQxo */
    public boolean mo254onKeyEventZmokQxo(KeyEvent event) {
        float xAmount;
        long scrollAmount;
        float yAmount;
        if (getEnabled() && ((Key.m6162equalsimpl0(KeyEvent_androidKt.m6482getKeyZmokQxo(event), Key.INSTANCE.m6361getPageDownEK5gGoQ()) || Key.m6162equalsimpl0(KeyEvent_androidKt.m6482getKeyZmokQxo(event), Key.INSTANCE.m6362getPageUpEK5gGoQ())) && KeyEventType.m6475equalsimpl0(KeyEvent_androidKt.m6483getTypeZmokQxo(event), KeyEventType.INSTANCE.m6479getKeyDownCS__XNY()) && !KeyEvent_androidKt.m6486isCtrlPressedZmokQxo(event))) {
            boolean zIsVertical = this.scrollingLogic.isVertical();
            ContentInViewNode contentInViewNode = this.contentInViewNode;
            if (zIsVertical) {
                long arg0$iv = contentInViewNode.m468getViewportSizeOrZeroYbymL2g$foundation();
                int viewportHeight = (int) (arg0$iv & 4294967295L);
                if (Key.m6162equalsimpl0(KeyEvent_androidKt.m6482getKeyZmokQxo(event), Key.INSTANCE.m6362getPageUpEK5gGoQ())) {
                    yAmount = viewportHeight;
                } else {
                    yAmount = -viewportHeight;
                }
                float y$iv = yAmount;
                long v1$iv$iv = Float.floatToRawIntBits(0.0f);
                long v2$iv$iv = Float.floatToRawIntBits(y$iv);
                scrollAmount = Offset.m5060constructorimpl((v1$iv$iv << 32) | (4294967295L & v2$iv$iv));
            } else {
                long arg0$iv2 = contentInViewNode.m468getViewportSizeOrZeroYbymL2g$foundation();
                int viewportWidth = (int) (arg0$iv2 >> 32);
                if (Key.m6162equalsimpl0(KeyEvent_androidKt.m6482getKeyZmokQxo(event), Key.INSTANCE.m6362getPageUpEK5gGoQ())) {
                    xAmount = viewportWidth;
                } else {
                    xAmount = -viewportWidth;
                }
                float x$iv = xAmount;
                long v1$iv$iv2 = Float.floatToRawIntBits(x$iv);
                long v2$iv$iv2 = Float.floatToRawIntBits(0.0f);
                scrollAmount = Offset.m5060constructorimpl((v1$iv$iv2 << 32) | (4294967295L & v2$iv$iv2));
            }
            BuildersKt__Builders_commonKt.launch$default(getCoroutineScope(), null, null, new ScrollableNode$onKeyEvent$1(this, scrollAmount, null), 3, null);
            return true;
        }
        return false;
    }

    @Override // androidx.compose.ui.input.key.KeyInputModifierNode
    /* JADX INFO: renamed from: onPreKeyEvent-ZmokQxo */
    public boolean mo256onPreKeyEventZmokQxo(KeyEvent event) {
        return false;
    }

    @Override // androidx.compose.foundation.gestures.DragGestureNode, androidx.compose.ui.node.PointerInputModifierNode
    /* JADX INFO: renamed from: onPointerEvent-H0pRuoY */
    public void mo255onPointerEventH0pRuoY(PointerEvent pointerEvent, PointerEventPass pass, long bounds) {
        boolean z;
        List<PointerInputChange> changes = pointerEvent.getChanges();
        int $i$f$fastAny = 0;
        int index$iv$iv = 0;
        int size = changes.size();
        while (true) {
            if (index$iv$iv < size) {
                Object item$iv$iv = changes.get(index$iv$iv);
                PointerInputChange it = (PointerInputChange) item$iv$iv;
                List<PointerInputChange> list = changes;
                int $i$f$fastAny2 = $i$f$fastAny;
                if (getCanDrag().invoke(PointerType.m6720boximpl(it.getType())).booleanValue()) {
                    z = true;
                    break;
                } else {
                    index$iv$iv++;
                    changes = list;
                    $i$f$fastAny = $i$f$fastAny2;
                }
            } else {
                z = false;
                break;
            }
        }
        if (z) {
            super.mo255onPointerEventH0pRuoY(pointerEvent, pass, bounds);
        }
        initializeGestureCoordination();
        if (getEnabled()) {
            if (pass == PointerEventPass.Initial && PointerEventType.m6590equalsimpl0(pointerEvent.getType(), PointerEventType.INSTANCE.m6605getScroll7fucELk())) {
                ensureMouseWheelScrollingLogicInitialized();
            }
            MouseWheelScrollingLogic mouseWheelScrollingLogic = this.mouseWheelScrollingLogic;
            if (mouseWheelScrollingLogic != null) {
                mouseWheelScrollingLogic.mo553onPointerEventH0pRuoY(pointerEvent, pass, bounds);
            }
            if (pass == PointerEventPass.Initial && (PointerEventType.m6590equalsimpl0(pointerEvent.getType(), PointerEventType.INSTANCE.m6599getPanStart7fucELk()) || PointerEventType.m6590equalsimpl0(pointerEvent.getType(), PointerEventType.INSTANCE.m6598getPanMove7fucELk()) || PointerEventType.m6590equalsimpl0(pointerEvent.getType(), PointerEventType.INSTANCE.m6597getPanEnd7fucELk()))) {
                ensureTrackpadScrollingLogicInitialized();
            }
            TrackpadScrollingLogic trackpadScrollingLogic = this.trackpadScrollingLogic;
            if (trackpadScrollingLogic != null) {
                trackpadScrollingLogic.mo553onPointerEventH0pRuoY(pointerEvent, pass, bounds);
            }
        }
    }

    @Override // androidx.compose.ui.node.SemanticsModifierNode
    public void applySemantics(SemanticsPropertyReceiver $this$applySemantics) {
        if (getEnabled() && (this.scrollByAction == null || this.scrollByOffsetAction == null)) {
            setScrollSemanticsActions();
        }
        Function2<? super Float, ? super Float, Boolean> function2 = this.scrollByAction;
        if (function2 != null) {
            SemanticsPropertiesKt.scrollBy$default($this$applySemantics, null, function2, 1, null);
        }
        Function2<? super Offset, ? super Continuation<? super Offset>, ? extends Object> function22 = this.scrollByOffsetAction;
        if (function22 != null) {
            SemanticsPropertiesKt.scrollByOffset($this$applySemantics, function22);
        }
    }

    private final void setScrollSemanticsActions() {
        this.scrollByAction = new Function2() { // from class: androidx.compose.foundation.gestures.ScrollableNode$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Boolean.valueOf(ScrollableNode.setScrollSemanticsActions$lambda$0(this.f$0, ((Float) obj).floatValue(), ((Float) obj2).floatValue()));
            }
        };
        this.scrollByOffsetAction = new AnonymousClass2(null);
    }

    static final boolean setScrollSemanticsActions$lambda$0(ScrollableNode this$0, float x, float y) {
        BuildersKt__Builders_commonKt.launch$default(this$0.getCoroutineScope(), null, null, new ScrollableNode$setScrollSemanticsActions$1$1(this$0, x, y, null), 3, null);
        return true;
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.ScrollableNode$setScrollSemanticsActions$2, reason: invalid class name */
    /* JADX INFO: compiled from: Scrollable.kt */
    @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n"}, d2 = {"<anonymous>", "Landroidx/compose/ui/geometry/Offset;", TypedValues.CycleType.S_WAVE_OFFSET}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.ScrollableNode$setScrollSemanticsActions$2", f = "Scrollable.kt", i = {}, l = {TypedValues.MotionType.TYPE_QUANTIZE_MOTIONSTEPS}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class AnonymousClass2 extends SuspendLambda implements Function2<Offset, Continuation<? super Offset>, Object> {
        /* synthetic */ long J$0;
        int label;

        AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = ScrollableNode.this.new AnonymousClass2(continuation);
            anonymousClass2.J$0 = ((Offset) obj).m5078unboximpl();
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(Offset offset, Continuation<? super Offset> continuation) {
            return m606invoke3MmeM6k(offset.m5078unboximpl(), continuation);
        }

        /* JADX INFO: renamed from: invoke-3MmeM6k, reason: not valid java name */
        public final Object m606invoke3MmeM6k(long j, Continuation<? super Offset> continuation) {
            return ((AnonymousClass2) create(Offset.m5057boximpl(j), continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    long offset = this.J$0;
                    this.label = 1;
                    Object objM599semanticsScrollByd4ec7I = ScrollableKt.m599semanticsScrollByd4ec7I(ScrollableNode.this.scrollingLogic, offset, this);
                    return objM599semanticsScrollByd4ec7I == coroutine_suspended ? coroutine_suspended : objM599semanticsScrollByd4ec7I;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    private final void clearScrollSemanticsActions() {
        this.scrollByAction = null;
        this.scrollByOffsetAction = null;
    }
}
