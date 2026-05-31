package androidx.compose.foundation;

import androidx.compose.foundation.interaction.FocusInteraction;
import androidx.compose.foundation.interaction.Interaction;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.ui.focus.FocusState;
import androidx.compose.ui.focus.FocusTargetModifierNode;
import androidx.compose.ui.focus.FocusTargetModifierNodeKt;
import androidx.compose.ui.focus.Focusability;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.PinnableContainer;
import androidx.compose.ui.layout.PinnableContainerKt;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNodeKt;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.GlobalPositionAwareModifierNode;
import androidx.compose.ui.node.ObserverModifierNode;
import androidx.compose.ui.node.ObserverModifierNodeKt;
import androidx.compose.ui.node.SemanticsModifierNode;
import androidx.compose.ui.node.SemanticsModifierNodeKt;
import androidx.compose.ui.node.TraversableNode;
import androidx.compose.ui.node.TraversableNodeKt;
import androidx.compose.ui.relocation.BringIntoViewModifierNodeKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.Job;

/* JADX INFO: compiled from: Focusable.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0084\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0001\u0018\u0000 <2\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u00052\u00020\u0006:\u0001<B3\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\u0016\b\u0002\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e\u0018\u00010\f¢\u0006\u0004\b\u000f\u0010\u0010J\u0006\u0010 \u001a\u00020\rJ\u0010\u0010%\u001a\u00020\u000e2\b\u0010\u0007\u001a\u0004\u0018\u00010\bJ\u0018\u0010*\u001a\u00020\u000e2\u0006\u0010+\u001a\u00020'2\u0006\u0010,\u001a\u00020'H\u0002J\f\u0010-\u001a\u00020\u000e*\u00020.H\u0016J\b\u0010/\u001a\u00020\u000eH\u0016J\b\u00100\u001a\u00020\u000eH\u0016J\u0010\u00101\u001a\u00020\u000e2\u0006\u00102\u001a\u00020\u001dH\u0016J\n\u00103\u001a\u0004\u0018\u000104H\u0002J\b\u00105\u001a\u00020\u000eH\u0002J\u0010\u00106\u001a\u00020\u000e2\u0006\u00107\u001a\u00020\rH\u0002J\b\u00108\u001a\u00020\u000eH\u0002J\u0014\u00109\u001a\u00020\u000e*\u00020\b2\u0006\u0010:\u001a\u00020;H\u0002R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e\u0018\u00010\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\u00020\rX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0014\u001a\u00020\u00158VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010!\u001a\u0004\u0018\u00010\"8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b#\u0010$R\u0011\u0010&\u001a\u00020'8F¢\u0006\u0006\u001a\u0004\b(\u0010)¨\u0006="}, d2 = {"Landroidx/compose/foundation/FocusableNode;", "Landroidx/compose/ui/node/DelegatingNode;", "Landroidx/compose/ui/node/SemanticsModifierNode;", "Landroidx/compose/ui/node/GlobalPositionAwareModifierNode;", "Landroidx/compose/ui/node/CompositionLocalConsumerModifierNode;", "Landroidx/compose/ui/node/ObserverModifierNode;", "Landroidx/compose/ui/node/TraversableNode;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "focusability", "Landroidx/compose/ui/focus/Focusability;", "onFocusChange", "Lkotlin/Function1;", "", "", "<init>", "(Landroidx/compose/foundation/interaction/MutableInteractionSource;ILkotlin/jvm/functions/Function1;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "shouldAutoInvalidate", "getShouldAutoInvalidate", "()Z", "traverseKey", "", "getTraverseKey", "()Ljava/lang/Object;", "focusedInteraction", "Landroidx/compose/foundation/interaction/FocusInteraction$Focus;", "pinnedHandle", "Landroidx/compose/ui/layout/PinnableContainer$PinnedHandle;", "globalLayoutCoordinates", "Landroidx/compose/ui/layout/LayoutCoordinates;", "focusTargetNode", "Landroidx/compose/ui/focus/FocusTargetModifierNode;", "requestFocus", "focusedBoundsObserver", "Landroidx/compose/foundation/FocusedBoundsObserverNode;", "getFocusedBoundsObserver", "()Landroidx/compose/foundation/FocusedBoundsObserverNode;", "update", "focusState", "Landroidx/compose/ui/focus/FocusState;", "getFocusState", "()Landroidx/compose/ui/focus/FocusState;", "onFocusStateChange", "previousState", "currentState", "applySemantics", "Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;", "onReset", "onObservedReadsChanged", "onGloballyPositioned", "coordinates", "retrievePinnableContainer", "Landroidx/compose/ui/layout/PinnableContainer;", "notifyObserverWhenAttached", "emitInteraction", "isFocused", "disposeInteractionSource", "emitWithFallback", "interaction", "Landroidx/compose/foundation/interaction/Interaction;", "TraverseKey", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class FocusableNode extends DelegatingNode implements SemanticsModifierNode, GlobalPositionAwareModifierNode, CompositionLocalConsumerModifierNode, ObserverModifierNode, TraversableNode {
    private final FocusTargetModifierNode focusTargetNode;
    private FocusInteraction.Focus focusedInteraction;
    private LayoutCoordinates globalLayoutCoordinates;
    private MutableInteractionSource interactionSource;
    private final Function1<Boolean, Unit> onFocusChange;
    private PinnableContainer.PinnedHandle pinnedHandle;
    private final boolean shouldAutoInvalidate;
    private static final TraverseKey TraverseKey = new TraverseKey(null);
    public static final int $stable = 8;

    public /* synthetic */ FocusableNode(MutableInteractionSource mutableInteractionSource, int i, Function1 function1, DefaultConstructorMarker defaultConstructorMarker) {
        this(mutableInteractionSource, i, function1);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private FocusableNode(MutableInteractionSource interactionSource, int focusability, Function1<? super Boolean, Unit> function1) {
        this.interactionSource = interactionSource;
        this.onFocusChange = function1;
        this.focusTargetNode = (FocusTargetModifierNode) delegate(FocusTargetModifierNodeKt.m4979FocusTargetModifierNodePYyLHbc(focusability, new FocusableNode$focusTargetNode$1(this)));
    }

    public /* synthetic */ FocusableNode(MutableInteractionSource mutableInteractionSource, int i, Function1 function1, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(mutableInteractionSource, (i2 & 2) != 0 ? Focusability.INSTANCE.m5002getAlwaysLCbbffg() : i, (i2 & 4) != 0 ? null : function1, null);
    }

    @Override // androidx.compose.ui.Modifier.Node
    public boolean getShouldAutoInvalidate() {
        return this.shouldAutoInvalidate;
    }

    /* JADX INFO: compiled from: Focusable.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Landroidx/compose/foundation/FocusableNode$TraverseKey;", "", "<init>", "()V", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
    private static final class TraverseKey {
        public /* synthetic */ TraverseKey(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private TraverseKey() {
        }
    }

    @Override // androidx.compose.ui.node.TraversableNode
    public Object getTraverseKey() {
        return TraverseKey;
    }

    public final boolean requestFocus() {
        return FocusTargetModifierNode.m4975requestFocus3ESFkO8$default(this.focusTargetNode, 0, 1, null);
    }

    private final FocusedBoundsObserverNode getFocusedBoundsObserver() {
        if (!getIsAttached()) {
            return null;
        }
        TraversableNode traversableNodeFindNearestAncestor = TraversableNodeKt.findNearestAncestor(this, FocusedBoundsObserverNode.INSTANCE);
        if (traversableNodeFindNearestAncestor instanceof FocusedBoundsObserverNode) {
            return (FocusedBoundsObserverNode) traversableNodeFindNearestAncestor;
        }
        return null;
    }

    public final void update(MutableInteractionSource interactionSource) {
        if (!Intrinsics.areEqual(this.interactionSource, interactionSource)) {
            disposeInteractionSource();
            this.interactionSource = interactionSource;
        }
    }

    public final FocusState getFocusState() {
        return this.focusTargetNode.getFocusState();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onFocusStateChange(FocusState previousState, FocusState currentState) {
        if (getIsAttached()) {
            boolean isFocused = currentState.isFocused();
            boolean wasFocused = previousState.isFocused();
            if (isFocused == wasFocused) {
                return;
            }
            Function1<Boolean, Unit> function1 = this.onFocusChange;
            if (function1 != null) {
                function1.invoke(Boolean.valueOf(isFocused));
            }
            if (isFocused) {
                BuildersKt__Builders_commonKt.launch$default(getCoroutineScope(), null, null, new C01401(null), 3, null);
                PinnableContainer pinnableContainer = retrievePinnableContainer();
                this.pinnedHandle = pinnableContainer != null ? pinnableContainer.pin() : null;
                notifyObserverWhenAttached();
            } else {
                PinnableContainer.PinnedHandle pinnedHandle = this.pinnedHandle;
                if (pinnedHandle != null) {
                    pinnedHandle.release();
                }
                this.pinnedHandle = null;
                FocusedBoundsObserverNode focusedBoundsObserver = getFocusedBoundsObserver();
                if (focusedBoundsObserver != null) {
                    focusedBoundsObserver.onFocusBoundsChanged(null);
                }
            }
            SemanticsModifierNodeKt.invalidateSemantics(this);
            emitInteraction(isFocused);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.FocusableNode$onFocusStateChange$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Focusable.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.FocusableNode$onFocusStateChange$1", f = "Focusable.kt", i = {}, l = {225}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class C01401 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C01401(Continuation<? super C01401> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return FocusableNode.this.new C01401(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C01401) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    this.label = 1;
                    if (BringIntoViewModifierNodeKt.bringIntoView$default(FocusableNode.this, null, this, 1, null) == coroutine_suspended) {
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

    /* JADX INFO: renamed from: androidx.compose.foundation.FocusableNode$applySemantics$1, reason: invalid class name */
    /* JADX INFO: compiled from: Focusable.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    static final /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function0<Boolean> {
        AnonymousClass1(Object obj) {
            super(0, obj, FocusableNode.class, "requestFocus", "requestFocus()Z", 0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final Boolean invoke() {
            return Boolean.valueOf(((FocusableNode) this.receiver).requestFocus());
        }
    }

    @Override // androidx.compose.ui.node.SemanticsModifierNode
    public void applySemantics(SemanticsPropertyReceiver $this$applySemantics) {
        SemanticsPropertiesKt.setFocused($this$applySemantics, this.focusTargetNode.getFocusState().isFocused());
        SemanticsPropertiesKt.requestFocus$default($this$applySemantics, null, new AnonymousClass1(this), 1, null);
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void onReset() {
        PinnableContainer.PinnedHandle pinnedHandle = this.pinnedHandle;
        if (pinnedHandle != null) {
            pinnedHandle.release();
        }
        this.pinnedHandle = null;
    }

    @Override // androidx.compose.ui.node.ObserverModifierNode
    public void onObservedReadsChanged() {
        PinnableContainer pinnableContainer = retrievePinnableContainer();
        if (this.focusTargetNode.getFocusState().isFocused()) {
            PinnableContainer.PinnedHandle pinnedHandle = this.pinnedHandle;
            if (pinnedHandle != null) {
                pinnedHandle.release();
            }
            this.pinnedHandle = pinnableContainer != null ? pinnableContainer.pin() : null;
        }
    }

    @Override // androidx.compose.ui.node.GlobalPositionAwareModifierNode
    public void onGloballyPositioned(LayoutCoordinates coordinates) {
        this.globalLayoutCoordinates = coordinates;
        if (this.focusTargetNode.getFocusState().isFocused()) {
            if (coordinates.isAttached()) {
                notifyObserverWhenAttached();
                return;
            }
            FocusedBoundsObserverNode focusedBoundsObserver = getFocusedBoundsObserver();
            if (focusedBoundsObserver != null) {
                focusedBoundsObserver.onFocusBoundsChanged(null);
            }
        }
    }

    private final PinnableContainer retrievePinnableContainer() {
        final Ref.ObjectRef container = new Ref.ObjectRef();
        ObserverModifierNodeKt.observeReads(this, new Function0() { // from class: androidx.compose.foundation.FocusableNode$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return FocusableNode.retrievePinnableContainer$lambda$0(container, this);
            }
        });
        return (PinnableContainer) container.element;
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [T, java.lang.Object] */
    static final Unit retrievePinnableContainer$lambda$0(Ref.ObjectRef $container, FocusableNode this$0) {
        $container.element = CompositionLocalConsumerModifierNodeKt.currentValueOf(this$0, PinnableContainerKt.getLocalPinnableContainer());
        return Unit.INSTANCE;
    }

    private final void notifyObserverWhenAttached() {
        FocusedBoundsObserverNode focusedBoundsObserver;
        if (this.globalLayoutCoordinates != null) {
            LayoutCoordinates layoutCoordinates = this.globalLayoutCoordinates;
            Intrinsics.checkNotNull(layoutCoordinates);
            if (!layoutCoordinates.isAttached() || (focusedBoundsObserver = getFocusedBoundsObserver()) == null) {
                return;
            }
            focusedBoundsObserver.onFocusBoundsChanged(this.globalLayoutCoordinates);
        }
    }

    private final void emitInteraction(boolean isFocused) {
        MutableInteractionSource interactionSource = this.interactionSource;
        if (interactionSource != null) {
            FocusInteraction.Focus oldValue = this.focusedInteraction;
            if (isFocused) {
                if (oldValue != null) {
                    emitWithFallback(interactionSource, new FocusInteraction.Unfocus(oldValue));
                    this.focusedInteraction = null;
                }
                FocusInteraction.Focus interaction = new FocusInteraction.Focus();
                emitWithFallback(interactionSource, interaction);
                this.focusedInteraction = interaction;
                return;
            }
            if (oldValue != null) {
                emitWithFallback(interactionSource, new FocusInteraction.Unfocus(oldValue));
                this.focusedInteraction = null;
            }
        }
    }

    private final void disposeInteractionSource() {
        FocusInteraction.Focus oldValue;
        MutableInteractionSource interactionSource = this.interactionSource;
        if (interactionSource != null && (oldValue = this.focusedInteraction) != null) {
            FocusInteraction.Unfocus interaction = new FocusInteraction.Unfocus(oldValue);
            interactionSource.tryEmit(interaction);
        }
        this.focusedInteraction = null;
    }

    private final void emitWithFallback(final MutableInteractionSource $this$emitWithFallback, final Interaction interaction) {
        if (getIsAttached()) {
            Job job = (Job) getCoroutineScope().getCoroutineContext().get(Job.INSTANCE);
            DisposableHandle handler = job != null ? job.invokeOnCompletion(new Function1() { // from class: androidx.compose.foundation.FocusableNode$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return FocusableNode.emitWithFallback$lambda$0($this$emitWithFallback, interaction, (Throwable) obj);
                }
            }) : null;
            BuildersKt__Builders_commonKt.launch$default(getCoroutineScope(), null, null, new C01391($this$emitWithFallback, interaction, handler, null), 3, null);
            return;
        }
        Boolean.valueOf($this$emitWithFallback.tryEmit(interaction));
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.FocusableNode$emitWithFallback$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Focusable.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.FocusableNode$emitWithFallback$1", f = "Focusable.kt", i = {}, l = {322}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class C01391 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ DisposableHandle $handler;
        final /* synthetic */ Interaction $interaction;
        final /* synthetic */ MutableInteractionSource $this_emitWithFallback;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01391(MutableInteractionSource mutableInteractionSource, Interaction interaction, DisposableHandle disposableHandle, Continuation<? super C01391> continuation) {
            super(2, continuation);
            this.$this_emitWithFallback = mutableInteractionSource;
            this.$interaction = interaction;
            this.$handler = disposableHandle;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C01391(this.$this_emitWithFallback, this.$interaction, this.$handler, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C01391) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    this.label = 1;
                    if (this.$this_emitWithFallback.emit(this.$interaction, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            DisposableHandle disposableHandle = this.$handler;
            if (disposableHandle != null) {
                disposableHandle.dispose();
            }
            return Unit.INSTANCE;
        }
    }

    static final Unit emitWithFallback$lambda$0(MutableInteractionSource $this_emitWithFallback, Interaction $interaction, Throwable it) {
        $this_emitWithFallback.tryEmit($interaction);
        return Unit.INSTANCE;
    }
}
