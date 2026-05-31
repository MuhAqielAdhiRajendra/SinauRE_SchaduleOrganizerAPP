package androidx.compose.foundation;

import android.view.KeyEvent;
import androidx.collection.LongObjectMap;
import androidx.collection.LongObjectMapKt;
import androidx.collection.MutableLongObjectMap;
import androidx.compose.foundation.gestures.PressGestureScope;
import androidx.compose.foundation.interaction.HoverInteraction;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.interaction.PressInteraction;
import androidx.compose.foundation.internal.InlineClassHelperKt;
import androidx.compose.ui.focus.Focusability;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.input.indirect.IndirectPointerEvent;
import androidx.compose.ui.input.indirect.IndirectPointerInputChange;
import androidx.compose.ui.input.indirect.IndirectPointerInputModifierNode;
import androidx.compose.ui.input.key.KeyEvent_androidKt;
import androidx.compose.ui.input.key.KeyInputModifierNode;
import androidx.compose.ui.input.pointer.PointerEvent;
import androidx.compose.ui.input.pointer.PointerEventPass;
import androidx.compose.ui.input.pointer.PointerEventType;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.input.pointer.SuspendingPointerInputModifierNode;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNodeKt;
import androidx.compose.ui.node.DelegatableNode;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.ObserverModifierNode;
import androidx.compose.ui.node.ObserverModifierNodeKt;
import androidx.compose.ui.node.PointerInputModifierNode;
import androidx.compose.ui.node.SemanticsModifierNode;
import androidx.compose.ui.node.SemanticsModifierNodeKt;
import androidx.compose.ui.node.TraversableNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.ViewConfiguration;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntSizeKt;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.app.NotificationCompat;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.Job;

/* JADX INFO: compiled from: Clickable.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000Ü\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0002\b\u0004\b!\u0018\u0000 ~2\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u00052\u00020\u00062\u00020\u00072\u00020\b2\u00020\t:\u0001~BM\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u000f\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014\u0012\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016¢\u0006\u0004\b\u0018\u0010\u0019J\b\u00106\u001a\u00020\u000fH\u0002J\n\u00107\u001a\u0004\u0018\u00010%H\u0016J\f\u00108\u001a\u00020\u0017*\u000209H\u0016JS\u0010:\u001a\u00020\u00172\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f2\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016H\u0004¢\u0006\u0002\b;J\u0017\u0010<\u001a\u00020=2\u0006\u0010>\u001a\u00020?H\u0004¢\u0006\u0004\b@\u0010AJ\u0018\u0010B\u001a\u00020\u00172\u0006\u0010C\u001a\u00020D2\u0006\u0010E\u001a\u00020FH\u0016J\u0006\u0010G\u001a\u00020\u0017J\b\u0010H\u001a\u00020\u0017H\u0016J\u0006\u0010I\u001a\u00020\u0017J\b\u0010J\u001a\u00020\u0017H\u0004J\u0010\u0010K\u001a\u00020\u00172\u0006\u0010L\u001a\u00020\u000fH\u0002J\b\u0010M\u001a\u00020\u0017H\u0002J\b\u0010N\u001a\u00020\u0017H\u0002J\b\u0010O\u001a\u00020\u0017H\u0002J'\u0010P\u001a\u00020\u00172\u0006\u0010Q\u001a\u00020R2\u0006\u0010E\u001a\u00020F2\u0006\u0010S\u001a\u00020?H\u0016¢\u0006\u0004\bT\u0010UJ\b\u0010V\u001a\u00020\u0017H\u0016J\u0015\u0010W\u001a\u00020\u000f2\u0006\u0010C\u001a\u00020X¢\u0006\u0004\bY\u0010ZJ\u0017\u0010[\u001a\u00020\u000f2\u0006\u0010C\u001a\u00020XH$¢\u0006\u0004\b\\\u0010ZJ\u0017\u0010]\u001a\u00020\u000f2\u0006\u0010C\u001a\u00020XH$¢\u0006\u0004\b^\u0010ZJ\b\u0010_\u001a\u00020\u0017H\u0014J\u0015\u0010`\u001a\u00020\u000f2\u0006\u0010C\u001a\u00020X¢\u0006\u0004\ba\u0010ZJ\n\u0010d\u001a\u00020\u0017*\u000209J\u000f\u0010e\u001a\u0004\u0018\u00010\u0017H\u0004¢\u0006\u0002\u0010fJ\u0010\u0010i\u001a\u00020\u00172\u0006\u0010C\u001a\u00020jH\u0004J\u0010\u0010i\u001a\u00020\u00172\u0006\u0010C\u001a\u00020kH\u0004J\u001f\u0010i\u001a\u00020\u00172\u0006\u0010l\u001a\u0002002\u0006\u0010m\u001a\u00020\u000fH\u0004¢\u0006\u0004\bn\u0010oJ\u001f\u0010p\u001a\u00020\u00172\u0006\u0010l\u001a\u0002002\u0006\u0010m\u001a\u00020\u000fH\u0004¢\u0006\u0004\bq\u0010oJ\u0010\u0010r\u001a\u00020\u00172\u0006\u0010m\u001a\u00020\u000fH\u0004J\u001c\u0010s\u001a\u00020\u0017*\u00020t2\u0006\u0010l\u001a\u000200H\u0084@¢\u0006\u0004\bu\u0010vJ\b\u0010w\u001a\u00020\u000fH\u0002J\u0012\u0010w\u001a\u00020\u000f2\b\u0010C\u001a\u0004\u0018\u00010kH\u0002J\u0010\u0010w\u001a\u00020\u000f2\u0006\u0010C\u001a\u00020jH\u0002J\b\u0010x\u001a\u00020\u0017H\u0002J\b\u0010y\u001a\u00020\u0017H\u0002R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u001a\u001a\u00020\u000f@BX\u0084\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR*\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u00162\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016@BX\u0084\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0014\u0010\u001f\u001a\u00020\u000fX\u0086D¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u001cR\u000e\u0010!\u001a\u00020\"X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010#\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010$\u001a\u0004\u0018\u00010%X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010&\u001a\u0004\u0018\u00010'X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010(\u001a\u0004\u0018\u00010'X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010)\u001a\u0004\u0018\u00010*X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010+\u001a\u0004\u0018\u00010,X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010-\u001a\b\u0012\u0004\u0012\u00020*0.X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010/\u001a\u000200X\u0082\u000e¢\u0006\u0004\n\u0002\u00101R\u0010\u00102\u001a\u0004\u0018\u00010*X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00103\u001a\u0004\u0018\u000100X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00104\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00105\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010b\u001a\u00020\u000f8F¢\u0006\u0006\u001a\u0004\bc\u0010\u001cR\u0010\u0010g\u001a\u0004\u0018\u00010hX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010z\u001a\u00020{X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b|\u0010}¨\u0006\u007f"}, d2 = {"Landroidx/compose/foundation/AbstractClickableNode;", "Landroidx/compose/ui/node/DelegatingNode;", "Landroidx/compose/ui/node/PointerInputModifierNode;", "Landroidx/compose/ui/input/key/KeyInputModifierNode;", "Landroidx/compose/ui/node/SemanticsModifierNode;", "Landroidx/compose/ui/node/TraversableNode;", "Landroidx/compose/ui/node/CompositionLocalConsumerModifierNode;", "Landroidx/compose/ui/node/ObserverModifierNode;", "Landroidx/compose/ui/input/indirect/IndirectPointerInputModifierNode;", "Landroidx/compose/foundation/GestureConnection;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "indicationNodeFactory", "Landroidx/compose/foundation/IndicationNodeFactory;", "useLocalIndication", "", "enabled", "onClickLabel", "", "role", "Landroidx/compose/ui/semantics/Role;", "onClick", "Lkotlin/Function0;", "", "<init>", "(Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/foundation/IndicationNodeFactory;ZZLjava/lang/String;Landroidx/compose/ui/semantics/Role;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "value", "getEnabled", "()Z", "getOnClick", "()Lkotlin/jvm/functions/Function0;", "shouldAutoInvalidate", "getShouldAutoInvalidate", "focusableNode", "Landroidx/compose/foundation/FocusableNode;", "localIndicationNodeFactory", "pointerInputNode", "Landroidx/compose/ui/input/pointer/SuspendingPointerInputModifierNode;", "gestureNode", "Landroidx/compose/ui/node/DelegatableNode;", "indicationNode", "pressInteraction", "Landroidx/compose/foundation/interaction/PressInteraction$Press;", "hoverInteraction", "Landroidx/compose/foundation/interaction/HoverInteraction$Enter;", "currentKeyPressInteractions", "Landroidx/collection/MutableLongObjectMap;", "centerOffset", "Landroidx/compose/ui/geometry/Offset;", "J", "indirectPointerPressInteraction", "indirectPointerEventPressPosition", "userProvidedInteractionSource", "lazilyCreateIndication", "shouldLazilyCreateIndication", "createPointerInputNodeIfNeeded", "applyAdditionalSemantics", "Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;", "updateCommon", "updateCommon-O2vRcR0", "getExtendedTouchPadding", "Landroidx/compose/ui/geometry/Size;", "size", "Landroidx/compose/ui/unit/IntSize;", "getExtendedTouchPadding-hWWAJMo", "(J)J", "onIndirectPointerEvent", NotificationCompat.CATEGORY_EVENT, "Landroidx/compose/ui/input/indirect/IndirectPointerEvent;", "pass", "Landroidx/compose/ui/input/pointer/PointerEventPass;", "onAttach", "onObservedReadsChanged", "onDetach", "disposeInteractions", "onFocusChange", "isFocused", "recreateIndicationIfNeeded", "initializeIndicationAndInteractionSourceIfNeeded", "initializeGestureCoordination", "onPointerEvent", "pointerEvent", "Landroidx/compose/ui/input/pointer/PointerEvent;", "bounds", "onPointerEvent-H0pRuoY", "(Landroidx/compose/ui/input/pointer/PointerEvent;Landroidx/compose/ui/input/pointer/PointerEventPass;J)V", "onCancelPointerInput", "onKeyEvent", "Landroidx/compose/ui/input/key/KeyEvent;", "onKeyEvent-ZmokQxo", "(Landroid/view/KeyEvent;)Z", "onClickKeyDownEvent", "onClickKeyDownEvent-ZmokQxo", "onClickKeyUpEvent", "onClickKeyUpEvent-ZmokQxo", "onCancelKeyInput", "onPreKeyEvent", "onPreKeyEvent-ZmokQxo", "shouldMergeDescendantSemantics", "getShouldMergeDescendantSemantics", "applySemantics", "resetPointerInputHandler", "()Lkotlin/Unit;", "delayJob", "Lkotlinx/coroutines/Job;", "handlePressInteractionStart", "Landroidx/compose/ui/input/indirect/IndirectPointerInputChange;", "Landroidx/compose/ui/input/pointer/PointerInputChange;", TypedValues.CycleType.S_WAVE_OFFSET, "indirectPointer", "handlePressInteractionStart-3MmeM6k", "(JZ)V", "handlePressInteractionRelease", "handlePressInteractionRelease-3MmeM6k", "handlePressInteractionCancel", "handlePressInteraction", "Landroidx/compose/foundation/gestures/PressGestureScope;", "handlePressInteraction-d-4ec7I", "(Landroidx/compose/foundation/gestures/PressGestureScope;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "delayPressInteraction", "emitHoverEnter", "emitHoverExit", "traverseKey", "", "getTraverseKey", "()Ljava/lang/Object;", "TraverseKey", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
public abstract class AbstractClickableNode extends DelegatingNode implements PointerInputModifierNode, KeyInputModifierNode, SemanticsModifierNode, TraversableNode, CompositionLocalConsumerModifierNode, ObserverModifierNode, IndirectPointerInputModifierNode, GestureConnection {
    private long centerOffset;
    private final MutableLongObjectMap<PressInteraction.Press> currentKeyPressInteractions;
    private Job delayJob;
    private boolean enabled;
    private final FocusableNode focusableNode;
    private DelegatableNode gestureNode;
    private HoverInteraction.Enter hoverInteraction;
    private DelegatableNode indicationNode;
    private IndicationNodeFactory indicationNodeFactory;
    private Offset indirectPointerEventPressPosition;
    private PressInteraction.Press indirectPointerPressInteraction;
    private MutableInteractionSource interactionSource;
    private boolean lazilyCreateIndication;
    private IndicationNodeFactory localIndicationNodeFactory;
    private Function0<Unit> onClick;
    private String onClickLabel;
    private SuspendingPointerInputModifierNode pointerInputNode;
    private PressInteraction.Press pressInteraction;
    private Role role;
    private final boolean shouldAutoInvalidate;
    private final Object traverseKey;
    private boolean useLocalIndication;
    private MutableInteractionSource userProvidedInteractionSource;

    /* JADX INFO: renamed from: TraverseKey, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    public /* synthetic */ AbstractClickableNode(MutableInteractionSource mutableInteractionSource, IndicationNodeFactory indicationNodeFactory, boolean z, boolean z2, String str, Role role, Function0 function0, DefaultConstructorMarker defaultConstructorMarker) {
        this(mutableInteractionSource, indicationNodeFactory, z, z2, str, role, function0);
    }

    /* JADX INFO: renamed from: onClickKeyDownEvent-ZmokQxo, reason: not valid java name */
    protected abstract boolean mo252onClickKeyDownEventZmokQxo(KeyEvent event);

    /* JADX INFO: renamed from: onClickKeyUpEvent-ZmokQxo, reason: not valid java name */
    protected abstract boolean mo253onClickKeyUpEventZmokQxo(KeyEvent event);

    private AbstractClickableNode(MutableInteractionSource interactionSource, IndicationNodeFactory indicationNodeFactory, boolean useLocalIndication, boolean enabled, String onClickLabel, Role role, Function0<Unit> function0) {
        this.interactionSource = interactionSource;
        this.indicationNodeFactory = indicationNodeFactory;
        this.useLocalIndication = useLocalIndication;
        this.onClickLabel = onClickLabel;
        this.role = role;
        this.enabled = enabled;
        this.onClick = function0;
        this.focusableNode = new FocusableNode(this.interactionSource, Focusability.INSTANCE.m5004getSystemDefinedLCbbffg(), new AbstractClickableNode$focusableNode$1(this), null);
        this.currentKeyPressInteractions = LongObjectMapKt.mutableLongObjectMapOf();
        this.centerOffset = Offset.INSTANCE.m5084getZeroF1C5BW0();
        this.userProvidedInteractionSource = this.interactionSource;
        this.lazilyCreateIndication = shouldLazilyCreateIndication();
        this.traverseKey = INSTANCE;
    }

    protected final boolean getEnabled() {
        return this.enabled;
    }

    protected final Function0<Unit> getOnClick() {
        return this.onClick;
    }

    @Override // androidx.compose.ui.Modifier.Node
    public final boolean getShouldAutoInvalidate() {
        return this.shouldAutoInvalidate;
    }

    private final boolean shouldLazilyCreateIndication() {
        return this.userProvidedInteractionSource == null;
    }

    public SuspendingPointerInputModifierNode createPointerInputNodeIfNeeded() {
        return null;
    }

    public void applyAdditionalSemantics(SemanticsPropertyReceiver $this$applyAdditionalSemantics) {
    }

    /* JADX INFO: renamed from: updateCommon-O2vRcR0, reason: not valid java name */
    protected final void m257updateCommonO2vRcR0(MutableInteractionSource interactionSource, IndicationNodeFactory indicationNodeFactory, boolean useLocalIndication, boolean enabled, String onClickLabel, Role role, Function0<Unit> onClick) {
        boolean isIndicationNodeDirty = false;
        if (!Intrinsics.areEqual(this.userProvidedInteractionSource, interactionSource)) {
            disposeInteractions();
            this.userProvidedInteractionSource = interactionSource;
            this.interactionSource = interactionSource;
            isIndicationNodeDirty = true;
        }
        if (!Intrinsics.areEqual(this.indicationNodeFactory, indicationNodeFactory)) {
            this.indicationNodeFactory = indicationNodeFactory;
            isIndicationNodeDirty = true;
        }
        if (this.useLocalIndication != useLocalIndication) {
            this.useLocalIndication = useLocalIndication;
            if (useLocalIndication) {
                onObservedReadsChanged();
            }
            isIndicationNodeDirty = true;
        }
        if (this.enabled != enabled) {
            FocusableNode focusableNode = this.focusableNode;
            if (enabled) {
                delegate(focusableNode);
            } else {
                undelegate(focusableNode);
                disposeInteractions();
            }
            SemanticsModifierNodeKt.invalidateSemantics(this);
            this.enabled = enabled;
        }
        if (!Intrinsics.areEqual(this.onClickLabel, onClickLabel)) {
            this.onClickLabel = onClickLabel;
            SemanticsModifierNodeKt.invalidateSemantics(this);
        }
        if (!Intrinsics.areEqual(this.role, role)) {
            this.role = role;
            SemanticsModifierNodeKt.invalidateSemantics(this);
        }
        this.onClick = onClick;
        if (this.lazilyCreateIndication != shouldLazilyCreateIndication()) {
            this.lazilyCreateIndication = shouldLazilyCreateIndication();
            if (!this.lazilyCreateIndication && this.indicationNode == null) {
                isIndicationNodeDirty = true;
            }
        }
        if (isIndicationNodeDirty) {
            recreateIndicationIfNeeded();
        }
        this.focusableNode.update(this.interactionSource);
    }

    /* JADX INFO: renamed from: getExtendedTouchPadding-hWWAJMo, reason: not valid java name */
    protected final long m248getExtendedTouchPaddinghWWAJMo(long size) {
        long minimumTouchTargetSizeDp = ((ViewConfiguration) CompositionLocalConsumerModifierNodeKt.currentValueOf(this, CompositionLocalsKt.getLocalViewConfiguration())).mo7015getMinimumTouchTargetSizeMYxV2XQ();
        Density $this$getExtendedTouchPadding_hWWAJMo_u24lambda_u240 = DelegatableNodeKt.requireDensity(this);
        long minimumTouchTargetSize = $this$getExtendedTouchPadding_hWWAJMo_u24lambda_u240.mo433toSizeXkaWNTQ(minimumTouchTargetSizeDp);
        int bits$iv$iv$iv = (int) (minimumTouchTargetSize >> 32);
        float horizontal = Math.max(0.0f, Float.intBitsToFloat(bits$iv$iv$iv) - ((int) (size >> 32))) / 2.0f;
        int bits$iv$iv$iv2 = (int) (minimumTouchTargetSize & 4294967295L);
        float vertical = Math.max(0.0f, Float.intBitsToFloat(bits$iv$iv$iv2) - ((int) (size & 4294967295L))) / 2.0f;
        long v1$iv$iv = Float.floatToRawIntBits(horizontal);
        long v2$iv$iv = Float.floatToRawIntBits(vertical);
        return Size.m5128constructorimpl((v1$iv$iv << 32) | (v2$iv$iv & 4294967295L));
    }

    @Override // androidx.compose.ui.input.indirect.IndirectPointerInputModifierNode
    public void onIndirectPointerEvent(IndirectPointerEvent event, PointerEventPass pass) {
        initializeIndicationAndInteractionSourceIfNeeded();
        if (this.enabled) {
            initializeGestureCoordination();
        }
    }

    @Override // androidx.compose.ui.Modifier.Node
    public final void onAttach() {
        onObservedReadsChanged();
        if (!this.lazilyCreateIndication) {
            initializeIndicationAndInteractionSourceIfNeeded();
        }
        if (this.enabled) {
            delegate(this.focusableNode);
        }
    }

    @Override // androidx.compose.ui.node.ObserverModifierNode
    public void onObservedReadsChanged() {
        if (this.useLocalIndication) {
            ObserverModifierNodeKt.observeReads(this, new Function0() { // from class: androidx.compose.foundation.AbstractClickableNode$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return AbstractClickableNode.onObservedReadsChanged$lambda$0(this.f$0);
                }
            });
        }
    }

    static final Unit onObservedReadsChanged$lambda$0(AbstractClickableNode this$0) {
        Indication indication = (Indication) CompositionLocalConsumerModifierNodeKt.currentValueOf(this$0, IndicationKt.getLocalIndication());
        boolean value$iv = indication instanceof IndicationNodeFactory;
        if (!value$iv) {
            InlineClassHelperKt.throwIllegalArgumentException(ClickableKt.unsupportedIndicationExceptionMessage(indication));
        }
        IndicationNodeFactory previousFactory = this$0.localIndicationNodeFactory;
        this$0.localIndicationNodeFactory = (IndicationNodeFactory) indication;
        if (previousFactory != null && !Intrinsics.areEqual(this$0.localIndicationNodeFactory, previousFactory)) {
            this$0.recreateIndicationIfNeeded();
        }
        return Unit.INSTANCE;
    }

    @Override // androidx.compose.ui.Modifier.Node
    public final void onDetach() {
        disposeInteractions();
        if (this.userProvidedInteractionSource == null) {
            this.interactionSource = null;
        }
        DelegatableNode it = this.indicationNode;
        if (it != null) {
            undelegate(it);
        }
        this.indicationNode = null;
        DelegatableNode it2 = this.gestureNode;
        if (it2 != null) {
            undelegate(it2);
        }
        this.gestureNode = null;
    }

    protected final void disposeInteractions() {
        int $i$f$forEachValue;
        int i;
        int $i$f$forEachValue2;
        MutableInteractionSource interactionSource = this.interactionSource;
        if (interactionSource != null) {
            int i2 = 0;
            PressInteraction.Press oldValue = this.pressInteraction;
            if (oldValue != null) {
                PressInteraction.Cancel interaction = new PressInteraction.Cancel(oldValue);
                interactionSource.tryEmit(interaction);
            }
            PressInteraction.Press oldValue2 = this.indirectPointerPressInteraction;
            if (oldValue2 != null) {
                PressInteraction.Cancel interaction2 = new PressInteraction.Cancel(oldValue2);
                interactionSource.tryEmit(interaction2);
            }
            HoverInteraction.Enter oldValue3 = this.hoverInteraction;
            if (oldValue3 != null) {
                HoverInteraction.Exit interaction3 = new HoverInteraction.Exit(oldValue3);
                interactionSource.tryEmit(interaction3);
            }
            LongObjectMap this_$iv = this.currentKeyPressInteractions;
            int $i$f$forEachValue3 = 0;
            Object[] v$iv = this_$iv.values;
            long[] m$iv$iv = this_$iv.metadata;
            int lastIndex$iv$iv = m$iv$iv.length - 2;
            int i$iv$iv = 0;
            if (0 <= lastIndex$iv$iv) {
                while (true) {
                    long slot$iv$iv = m$iv$iv[i$iv$iv];
                    int i3 = i2;
                    LongObjectMap this_$iv2 = this_$iv;
                    if ((((~slot$iv$iv) << 7) & slot$iv$iv & (-9187201950435737472L)) == -9187201950435737472L) {
                        $i$f$forEachValue = $i$f$forEachValue3;
                    } else {
                        int i4 = 8;
                        int bitCount$iv$iv = 8 - ((~(i$iv$iv - lastIndex$iv$iv)) >>> 31);
                        int j$iv$iv = 0;
                        while (j$iv$iv < bitCount$iv$iv) {
                            long value$iv$iv$iv = 255 & slot$iv$iv;
                            if (!(value$iv$iv$iv < 128)) {
                                i = i4;
                                $i$f$forEachValue2 = $i$f$forEachValue3;
                            } else {
                                int index$iv$iv = (i$iv$iv << 3) + j$iv$iv;
                                i = i4;
                                PressInteraction.Press it = (PressInteraction.Press) v$iv[index$iv$iv];
                                $i$f$forEachValue2 = $i$f$forEachValue3;
                                interactionSource.tryEmit(new PressInteraction.Cancel(it));
                            }
                            slot$iv$iv >>= i;
                            j$iv$iv++;
                            i4 = i;
                            $i$f$forEachValue3 = $i$f$forEachValue2;
                        }
                        $i$f$forEachValue = $i$f$forEachValue3;
                        if (bitCount$iv$iv != i4) {
                            break;
                        }
                    }
                    if (i$iv$iv == lastIndex$iv$iv) {
                        break;
                    }
                    i$iv$iv++;
                    i2 = i3;
                    this_$iv = this_$iv2;
                    $i$f$forEachValue3 = $i$f$forEachValue;
                }
            }
        }
        this.pressInteraction = null;
        this.indirectPointerPressInteraction = null;
        this.indirectPointerEventPressPosition = null;
        this.hoverInteraction = null;
        this.currentKeyPressInteractions.clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onFocusChange(boolean isFocused) {
        LongObjectMap this_$iv;
        LongObjectMap this_$iv2;
        int i;
        if (isFocused) {
            initializeIndicationAndInteractionSourceIfNeeded();
            return;
        }
        if (this.interactionSource != null) {
            LongObjectMap this_$iv3 = this.currentKeyPressInteractions;
            int $i$f$forEachValue = 0;
            Object[] v$iv = this_$iv3.values;
            long[] m$iv$iv = this_$iv3.metadata;
            int lastIndex$iv$iv = m$iv$iv.length - 2;
            int i$iv$iv = 0;
            if (0 <= lastIndex$iv$iv) {
                while (true) {
                    long slot$iv$iv = m$iv$iv[i$iv$iv];
                    int $i$f$forEachValue2 = $i$f$forEachValue;
                    if ((((~slot$iv$iv) << 7) & slot$iv$iv & (-9187201950435737472L)) == -9187201950435737472L) {
                        this_$iv = this_$iv3;
                    } else {
                        int i2 = 8;
                        int bitCount$iv$iv = 8 - ((~(i$iv$iv - lastIndex$iv$iv)) >>> 31);
                        int j$iv$iv = 0;
                        while (j$iv$iv < bitCount$iv$iv) {
                            long value$iv$iv$iv = 255 & slot$iv$iv;
                            if (!(value$iv$iv$iv < 128)) {
                                this_$iv2 = this_$iv3;
                                i = i2;
                            } else {
                                int index$iv$iv = (i$iv$iv << 3) + j$iv$iv;
                                i = i2;
                                this_$iv2 = this_$iv3;
                                BuildersKt__Builders_commonKt.launch$default(getCoroutineScope(), null, null, new AbstractClickableNode$onFocusChange$1$1(this, (PressInteraction.Press) v$iv[index$iv$iv], null), 3, null);
                            }
                            slot$iv$iv >>= i;
                            j$iv$iv++;
                            i2 = i;
                            this_$iv3 = this_$iv2;
                        }
                        this_$iv = this_$iv3;
                        if (bitCount$iv$iv != i2) {
                            break;
                        }
                    }
                    if (i$iv$iv == lastIndex$iv$iv) {
                        break;
                    }
                    i$iv$iv++;
                    $i$f$forEachValue = $i$f$forEachValue2;
                    this_$iv3 = this_$iv;
                }
            }
            PressInteraction.Press it = this.indirectPointerPressInteraction;
            if (it != null) {
                BuildersKt__Builders_commonKt.launch$default(getCoroutineScope(), null, null, new AbstractClickableNode$onFocusChange$2$1(this, it, null), 3, null);
            }
        }
        this.currentKeyPressInteractions.clear();
        this.indirectPointerPressInteraction = null;
        onCancelKeyInput();
    }

    private final void recreateIndicationIfNeeded() {
        if (this.indicationNode != null || !this.lazilyCreateIndication) {
            DelegatableNode it = this.indicationNode;
            if (it != null) {
                undelegate(it);
            }
            this.indicationNode = null;
            initializeIndicationAndInteractionSourceIfNeeded();
        }
    }

    private final void initializeIndicationAndInteractionSourceIfNeeded() {
        if (this.indicationNode != null) {
            return;
        }
        IndicationNodeFactory indicationFactory = this.useLocalIndication ? this.localIndicationNodeFactory : this.indicationNodeFactory;
        if (indicationFactory != null) {
            IndicationNodeFactory factory = indicationFactory;
            if (this.interactionSource == null) {
                this.interactionSource = InteractionSourceKt.MutableInteractionSource();
            }
            this.focusableNode.update(this.interactionSource);
            MutableInteractionSource mutableInteractionSource = this.interactionSource;
            Intrinsics.checkNotNull(mutableInteractionSource);
            DelegatableNode node = factory.create(mutableInteractionSource);
            delegate(node);
            this.indicationNode = node;
        }
    }

    private final void initializeGestureCoordination() {
        if (ComposeFoundationFlags.isDelayPressesUsingGestureConsumptionEnabled && this.gestureNode == null) {
            this.gestureNode = delegate(GestureNodeKt.gestureNode(this));
        }
    }

    @Override // androidx.compose.ui.node.PointerInputModifierNode
    /* JADX INFO: renamed from: onPointerEvent-H0pRuoY, reason: not valid java name */
    public void mo255onPointerEventH0pRuoY(PointerEvent pointerEvent, PointerEventPass pass, long bounds) {
        SuspendingPointerInputModifierNode node;
        long $this$toOffset_u2d_u2dgyyYBs$iv = IntSizeKt.m8327getCenterozmzZPI(bounds);
        float x$iv$iv = IntOffset.m8278getXimpl($this$toOffset_u2d_u2dgyyYBs$iv);
        float y$iv$iv = IntOffset.m8279getYimpl($this$toOffset_u2d_u2dgyyYBs$iv);
        long v1$iv$iv$iv = Float.floatToRawIntBits(x$iv$iv);
        long v2$iv$iv$iv = Float.floatToRawIntBits(y$iv$iv);
        this.centerOffset = Offset.m5060constructorimpl((v1$iv$iv$iv << 32) | (v2$iv$iv$iv & 4294967295L));
        initializeIndicationAndInteractionSourceIfNeeded();
        if (this.enabled) {
            initializeGestureCoordination();
            if (pass == PointerEventPass.Main) {
                int type = pointerEvent.getType();
                if (PointerEventType.m6590equalsimpl0(type, PointerEventType.INSTANCE.m6594getEnter7fucELk())) {
                    BuildersKt__Builders_commonKt.launch$default(getCoroutineScope(), null, null, new AbstractClickableNode$onPointerEvent$1(this, null), 3, null);
                } else if (PointerEventType.m6590equalsimpl0(type, PointerEventType.INSTANCE.m6595getExit7fucELk())) {
                    BuildersKt__Builders_commonKt.launch$default(getCoroutineScope(), null, null, new AbstractClickableNode$onPointerEvent$2(this, null), 3, null);
                }
            }
        }
        if (this.pointerInputNode == null && (node = createPointerInputNodeIfNeeded()) != null) {
            this.pointerInputNode = (SuspendingPointerInputModifierNode) delegate(node);
        }
        SuspendingPointerInputModifierNode node2 = this.pointerInputNode;
        if (node2 != null) {
            node2.mo255onPointerEventH0pRuoY(pointerEvent, pass, bounds);
        }
    }

    @Override // androidx.compose.ui.node.PointerInputModifierNode
    public void onCancelPointerInput() {
        HoverInteraction.Enter oldValue;
        MutableInteractionSource interactionSource = this.interactionSource;
        if (interactionSource != null && (oldValue = this.hoverInteraction) != null) {
            HoverInteraction.Exit interaction = new HoverInteraction.Exit(oldValue);
            interactionSource.tryEmit(interaction);
        }
        this.hoverInteraction = null;
        SuspendingPointerInputModifierNode suspendingPointerInputModifierNode = this.pointerInputNode;
        if (suspendingPointerInputModifierNode != null) {
            suspendingPointerInputModifierNode.onCancelPointerInput();
        }
    }

    @Override // androidx.compose.ui.input.key.KeyInputModifierNode
    /* JADX INFO: renamed from: onKeyEvent-ZmokQxo, reason: not valid java name */
    public final boolean mo254onKeyEventZmokQxo(KeyEvent event) {
        initializeIndicationAndInteractionSourceIfNeeded();
        long keyCode = KeyEvent_androidKt.m6482getKeyZmokQxo(event);
        if (this.enabled && ClickableKt.m334isPressZmokQxo(event)) {
            boolean wasInteractionHandled = false;
            if (!this.currentKeyPressInteractions.containsKey(keyCode)) {
                PressInteraction.Press press = new PressInteraction.Press(this.centerOffset, null);
                this.currentKeyPressInteractions.set(keyCode, press);
                if (this.interactionSource != null) {
                    BuildersKt__Builders_commonKt.launch$default(getCoroutineScope(), null, null, new AbstractClickableNode$onKeyEvent$1(this, press, null), 3, null);
                }
                wasInteractionHandled = true;
            }
            return mo252onClickKeyDownEventZmokQxo(event) || wasInteractionHandled;
        }
        if (!this.enabled || !ClickableKt.m332isClickZmokQxo(event)) {
            return false;
        }
        PressInteraction.Press press2 = this.currentKeyPressInteractions.remove(keyCode);
        if (press2 != null) {
            if (this.interactionSource != null) {
                BuildersKt__Builders_commonKt.launch$default(getCoroutineScope(), null, null, new AbstractClickableNode$onKeyEvent$2(this, press2, null), 3, null);
            }
            mo253onClickKeyUpEventZmokQxo(event);
        }
        return press2 != null;
    }

    protected void onCancelKeyInput() {
    }

    @Override // androidx.compose.ui.input.key.KeyInputModifierNode
    /* JADX INFO: renamed from: onPreKeyEvent-ZmokQxo, reason: not valid java name */
    public final boolean mo256onPreKeyEventZmokQxo(KeyEvent event) {
        return false;
    }

    @Override // androidx.compose.ui.node.SemanticsModifierNode
    public final boolean getShouldMergeDescendantSemantics() {
        return true;
    }

    @Override // androidx.compose.ui.node.SemanticsModifierNode
    public final void applySemantics(SemanticsPropertyReceiver $this$applySemantics) {
        if (this.role != null) {
            Role role = this.role;
            Intrinsics.checkNotNull(role);
            SemanticsPropertiesKt.m7362setRolekuIjeqM($this$applySemantics, role.getValue());
        }
        SemanticsPropertiesKt.onClick($this$applySemantics, this.onClickLabel, new Function0() { // from class: androidx.compose.foundation.AbstractClickableNode$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(AbstractClickableNode.applySemantics$lambda$0(this.f$0));
            }
        });
        if (this.enabled) {
            FocusableNode $this$applySemantics_u24lambda_u241 = this.focusableNode;
            $this$applySemantics_u24lambda_u241.applySemantics($this$applySemantics);
        } else {
            SemanticsPropertiesKt.disabled($this$applySemantics);
        }
        applyAdditionalSemantics($this$applySemantics);
    }

    static final boolean applySemantics$lambda$0(AbstractClickableNode this$0) {
        this$0.onClick.invoke();
        return true;
    }

    protected final Unit resetPointerInputHandler() {
        SuspendingPointerInputModifierNode suspendingPointerInputModifierNode = this.pointerInputNode;
        if (suspendingPointerInputModifierNode == null) {
            return null;
        }
        suspendingPointerInputModifierNode.resetPointerInputHandler();
        return Unit.INSTANCE;
    }

    protected final void handlePressInteractionStart(IndirectPointerInputChange event) {
        MutableInteractionSource interactionSource = this.interactionSource;
        if (interactionSource != null) {
            PressInteraction.Press press = new PressInteraction.Press(event.getPosition(), null);
            if (delayPressInteraction(event)) {
                this.delayJob = BuildersKt__Builders_commonKt.launch$default(getCoroutineScope(), null, null, new AbstractClickableNode$handlePressInteractionStart$1$1(interactionSource, press, this, null), 3, null);
            } else {
                this.indirectPointerPressInteraction = press;
                BuildersKt__Builders_commonKt.launch$default(getCoroutineScope(), null, null, new AbstractClickableNode$handlePressInteractionStart$1$2(interactionSource, press, null), 3, null);
            }
        }
    }

    protected final void handlePressInteractionStart(PointerInputChange event) {
        MutableInteractionSource interactionSource = this.interactionSource;
        if (interactionSource != null) {
            PressInteraction.Press press = new PressInteraction.Press(event.getPosition(), null);
            if (delayPressInteraction(event)) {
                this.delayJob = BuildersKt__Builders_commonKt.launch$default(getCoroutineScope(), null, null, new AbstractClickableNode$handlePressInteractionStart$2$1(interactionSource, press, this, null), 3, null);
            } else {
                this.pressInteraction = press;
                BuildersKt__Builders_commonKt.launch$default(getCoroutineScope(), null, null, new AbstractClickableNode$handlePressInteractionStart$2$2(interactionSource, press, null), 3, null);
            }
        }
    }

    /* JADX INFO: renamed from: handlePressInteractionStart-3MmeM6k, reason: not valid java name */
    protected final void m251handlePressInteractionStart3MmeM6k(long offset, boolean indirectPointer) {
        boolean zDelayPressInteraction;
        MutableInteractionSource interactionSource = this.interactionSource;
        if (interactionSource != null) {
            PressInteraction.Press press = new PressInteraction.Press(offset, null);
            if (ComposeFoundationFlags.isDelayPressesUsingGestureConsumptionEnabled) {
                zDelayPressInteraction = delayPressInteraction((PointerInputChange) null);
            } else {
                zDelayPressInteraction = delayPressInteraction();
            }
            boolean shouldDelayPress = zDelayPressInteraction;
            if (shouldDelayPress) {
                this.delayJob = BuildersKt__Builders_commonKt.launch$default(getCoroutineScope(), null, null, new AbstractClickableNode$handlePressInteractionStart$3$1(interactionSource, press, indirectPointer, this, null), 3, null);
                return;
            }
            if (indirectPointer) {
                this.indirectPointerPressInteraction = press;
            } else {
                this.pressInteraction = press;
            }
            BuildersKt__Builders_commonKt.launch$default(getCoroutineScope(), null, null, new AbstractClickableNode$handlePressInteractionStart$3$2(interactionSource, press, null), 3, null);
        }
    }

    /* JADX INFO: renamed from: handlePressInteractionRelease-3MmeM6k, reason: not valid java name */
    protected final void m250handlePressInteractionRelease3MmeM6k(long offset, boolean indirectPointer) {
        MutableInteractionSource interactionSource = this.interactionSource;
        if (interactionSource != null) {
            Job job = this.delayJob;
            boolean z = false;
            if (job != null && job.isActive()) {
                z = true;
            }
            if (z) {
                Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
                BuildersKt__Builders_commonKt.launch$default(getCoroutineScope(), null, null, new AbstractClickableNode$handlePressInteractionRelease$1$1(job, offset, interactionSource, null), 3, null);
            } else {
                PressInteraction.Press interaction = indirectPointer ? this.indirectPointerPressInteraction : this.pressInteraction;
                if (interaction != null) {
                    PressInteraction.Press it = interaction;
                    BuildersKt__Builders_commonKt.launch$default(getCoroutineScope(), null, null, new AbstractClickableNode$handlePressInteractionRelease$1$2$1(it, interactionSource, null), 3, null);
                }
            }
            if (indirectPointer) {
                this.indirectPointerPressInteraction = null;
            } else {
                this.pressInteraction = null;
            }
        }
    }

    protected final void handlePressInteractionCancel(boolean indirectPointer) {
        final MutableInteractionSource interactionSource = this.interactionSource;
        if (interactionSource != null) {
            Job job = this.delayJob;
            boolean z = false;
            if (job != null && job.isActive()) {
                z = true;
            }
            if (z) {
                Job job2 = this.delayJob;
                if (job2 != null) {
                    Job.DefaultImpls.cancel$default(job2, (CancellationException) null, 1, (Object) null);
                }
            } else {
                PressInteraction.Press interaction = indirectPointer ? this.indirectPointerPressInteraction : this.pressInteraction;
                if (interaction != null) {
                    PressInteraction.Press it = interaction;
                    final PressInteraction.Cancel endInteraction = new PressInteraction.Cancel(it);
                    Job job3 = (Job) getCoroutineScope().getCoroutineContext().get(Job.INSTANCE);
                    DisposableHandle handler = job3 != null ? job3.invokeOnCompletion(new Function1() { // from class: androidx.compose.foundation.AbstractClickableNode$$ExternalSyntheticLambda1
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return AbstractClickableNode.handlePressInteractionCancel$lambda$0$0$0(interactionSource, endInteraction, (Throwable) obj);
                        }
                    }) : null;
                    BuildersKt__Builders_commonKt.launch$default(getCoroutineScope(), null, null, new AbstractClickableNode$handlePressInteractionCancel$1$1$1(interactionSource, endInteraction, handler, null), 3, null);
                }
            }
            if (indirectPointer) {
                this.indirectPointerPressInteraction = null;
            } else {
                this.pressInteraction = null;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit handlePressInteractionCancel$lambda$0$0$0(MutableInteractionSource $interactionSource, PressInteraction.Cancel $endInteraction, Throwable it) {
        $interactionSource.tryEmit($endInteraction);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: handlePressInteraction-d-4ec7I, reason: not valid java name */
    protected final Object m249handlePressInteractiond4ec7I(PressGestureScope $this$handlePressInteraction_u2dd_u2d4ec7I, long offset, Continuation<? super Unit> continuation) {
        Object objCoroutineScope;
        MutableInteractionSource interactionSource = this.interactionSource;
        if (interactionSource != null && (objCoroutineScope = CoroutineScopeKt.coroutineScope(new AbstractClickableNode$handlePressInteraction$2$1($this$handlePressInteraction_u2dd_u2d4ec7I, offset, interactionSource, this, null), continuation)) == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            return objCoroutineScope;
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean delayPressInteraction() {
        return ClickableKt.hasScrollableContainer(this) || Clickable_androidKt.isComposeRootInScrollableContainer(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean delayPressInteraction(PointerInputChange event) {
        boolean hasInterestedParent;
        if (event == null) {
            hasInterestedParent = GestureNodeKt.getParentGestureConnection(this) != null;
        } else {
            hasInterestedParent = ClickableKt.hasInterestedParent(this, event);
        }
        return hasInterestedParent || Clickable_androidKt.isComposeRootInScrollableContainer(this);
    }

    private final boolean delayPressInteraction(IndirectPointerInputChange event) {
        return ClickableKt.hasInterestedParent(this, event) || Clickable_androidKt.isComposeRootInScrollableContainer(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void emitHoverEnter() {
        if (this.hoverInteraction == null) {
            HoverInteraction.Enter interaction = new HoverInteraction.Enter();
            MutableInteractionSource interactionSource = this.interactionSource;
            if (interactionSource != null) {
                BuildersKt__Builders_commonKt.launch$default(getCoroutineScope(), null, null, new AbstractClickableNode$emitHoverEnter$1$1(interactionSource, interaction, null), 3, null);
            }
            this.hoverInteraction = interaction;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void emitHoverExit() {
        HoverInteraction.Enter oldValue = this.hoverInteraction;
        if (oldValue != null) {
            HoverInteraction.Exit interaction = new HoverInteraction.Exit(oldValue);
            MutableInteractionSource interactionSource = this.interactionSource;
            if (interactionSource != null) {
                BuildersKt__Builders_commonKt.launch$default(getCoroutineScope(), null, null, new AbstractClickableNode$emitHoverExit$1$1$1(interactionSource, interaction, null), 3, null);
            }
            this.hoverInteraction = null;
        }
    }

    @Override // androidx.compose.ui.node.TraversableNode
    public Object getTraverseKey() {
        return this.traverseKey;
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.AbstractClickableNode$TraverseKey, reason: from kotlin metadata */
    /* JADX INFO: compiled from: Clickable.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Landroidx/compose/foundation/AbstractClickableNode$TraverseKey;", "", "<init>", "()V", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
