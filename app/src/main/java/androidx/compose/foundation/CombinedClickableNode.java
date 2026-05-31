package androidx.compose.foundation;

import android.view.KeyEvent;
import androidx.collection.LongObjectMap;
import androidx.collection.LongObjectMapKt;
import androidx.collection.MutableLongObjectMap;
import androidx.compose.foundation.CombinedClickableNode;
import androidx.compose.foundation.gestures.IndirectPointerInputDragCycleDetectorKt;
import androidx.compose.foundation.gestures.PressGestureScope;
import androidx.compose.foundation.gestures.TapGestureDetectorKt;
import androidx.compose.foundation.gestures.TapGestureDetector_androidKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.hapticfeedback.HapticFeedback;
import androidx.compose.ui.hapticfeedback.HapticFeedbackType;
import androidx.compose.ui.input.indirect.IndirectPointerEvent;
import androidx.compose.ui.input.indirect.IndirectPointerInputChange;
import androidx.compose.ui.input.key.KeyEvent_androidKt;
import androidx.compose.ui.input.pointer.PointerEvent;
import androidx.compose.ui.input.pointer.PointerEventKt;
import androidx.compose.ui.input.pointer.PointerEventPass;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.input.pointer.PointerInputEventHandler;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.input.pointer.SuspendingPointerInputModifierNode;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNodeKt;
import androidx.compose.ui.node.SemanticsModifierNodeKt;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.ViewConfiguration;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.app.NotificationCompat;
import java.util.List;
import java.util.concurrent.CancellationException;
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
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Job;

/* JADX INFO: compiled from: Clickable.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0098\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002:\u0001^B\u007f\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u000e\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004\u0012\u000e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u000b\u0012\u0006\u0010\u0011\u001a\u00020\u000b\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014¢\u0006\u0004\b\u0015\u0010\u0016J\n\u00104\u001a\u0004\u0018\u000105H\u0016J'\u00106\u001a\u00020\u00052\u0006\u00107\u001a\u0002082\u0006\u00109\u001a\u00020:2\u0006\u0010;\u001a\u00020<H\u0016¢\u0006\u0004\b=\u0010>J\u0018\u0010?\u001a\u00020\u00052\u0006\u0010@\u001a\u00020A2\u0006\u00109\u001a\u00020:H\u0016J\u0010\u0010B\u001a\u00020\u00052\u0006\u0010C\u001a\u00020$H\u0002J\u0010\u0010B\u001a\u00020\u00052\u0006\u0010C\u001a\u00020-H\u0002J\u0018\u0010D\u001a\u00020\u00052\u0006\u0010E\u001a\u00020*2\u0006\u0010F\u001a\u00020$H\u0002J\u0018\u0010D\u001a\u00020\u00052\u0006\u0010E\u001a\u00020*2\u0006\u0010F\u001a\u00020-H\u0002J\u001f\u0010G\u001a\u00020\u00052\u0006\u00107\u001a\u0002082\u0006\u0010;\u001a\u00020<H\u0002¢\u0006\u0004\bH\u0010IJ\u0010\u0010G\u001a\u00020\u00052\u0006\u0010J\u001a\u00020AH\u0002J\b\u0010K\u001a\u00020\u0005H\u0002J\u0010\u0010L\u001a\u00020\u00052\u0006\u00107\u001a\u000208H\u0002J\u0010\u0010L\u001a\u00020\u00052\u0006\u0010J\u001a\u00020AH\u0002J\b\u0010M\u001a\u00020\u0005H\u0016J\b\u0010N\u001a\u00020\u0005H\u0016J\u0010\u0010O\u001a\u00020\u00052\u0006\u0010P\u001a\u00020\u000bH\u0002J{\u0010Q\u001a\u00020\u00052\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u000e\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00042\u000e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00042\b\u0010\f\u001a\u0004\u0018\u00010\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\u000b2\b\u0010\u0012\u001a\u0004\u0018\u00010\u00072\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014¢\u0006\u0002\bRJ\f\u0010S\u001a\u00020\u0005*\u00020TH\u0016J\u0017\u0010U\u001a\u00020\u000b2\u0006\u0010@\u001a\u00020VH\u0014¢\u0006\u0004\bW\u0010XJ\u0017\u0010Y\u001a\u00020\u000b2\u0006\u0010@\u001a\u00020VH\u0014¢\u0006\u0004\bZ\u0010XJ\b\u0010[\u001a\u00020\u0005H\u0014J\b\u0010\\\u001a\u00020\u0005H\u0016J\b\u0010]\u001a\u00020\u0005H\u0002R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001cX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001cX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010 \u001a\u00020\u000bX\u0082\u0004¢\u0006\b\n\u0000\u0012\u0004\b!\u0010\"R\u0010\u0010#\u001a\u0004\u0018\u00010$X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010%\u001a\u0004\u0018\u00010\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010&\u001a\u0004\u0018\u00010\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020*X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010,\u001a\u0004\u0018\u00010-X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010.\u001a\u0004\u0018\u00010\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010/\u001a\u0004\u0018\u00010\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00100\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00101\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00102\u001a\u00020*X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00103\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006_"}, d2 = {"Landroidx/compose/foundation/CombinedClickableNode;", "Landroidx/compose/ui/node/CompositionLocalConsumerModifierNode;", "Landroidx/compose/foundation/AbstractClickableNode;", "onClick", "Lkotlin/Function0;", "", "onLongClickLabel", "", "onLongClick", "onDoubleClick", "hapticFeedbackEnabled", "", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "indicationNodeFactory", "Landroidx/compose/foundation/IndicationNodeFactory;", "useLocalIndication", "enabled", "onClickLabel", "role", "Landroidx/compose/ui/semantics/Role;", "<init>", "(Lkotlin/jvm/functions/Function0;Ljava/lang/String;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;ZLandroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/foundation/IndicationNodeFactory;ZZLjava/lang/String;Landroidx/compose/ui/semantics/Role;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "getHapticFeedbackEnabled", "()Z", "setHapticFeedbackEnabled", "(Z)V", "longKeyPressJobs", "Landroidx/collection/MutableLongObjectMap;", "Lkotlinx/coroutines/Job;", "doubleKeyClickStates", "Landroidx/compose/foundation/CombinedClickableNode$DoubleKeyClickState;", "isSuspendingPointerInputEnabled", "isSuspendingPointerInputEnabled$annotations", "()V", "downEvent", "Landroidx/compose/ui/input/pointer/PointerInputChange;", "longPressJob", "tapJob", "isSecondTap", "longPressTriggered", "firstTapUpTime", "", "ignoreNextUp", "indirectDownEvent", "Landroidx/compose/ui/input/indirect/IndirectPointerInputChange;", "indirectLongPressJob", "indirectTapJob", "indirectIsSecondTap", "indirectLongPressTriggered", "indirectFirstTapUpTime", "indirectIgnoreNextUp", "createPointerInputNodeIfNeeded", "Landroidx/compose/ui/input/pointer/SuspendingPointerInputModifierNode;", "onPointerEvent", "pointerEvent", "Landroidx/compose/ui/input/pointer/PointerEvent;", "pass", "Landroidx/compose/ui/input/pointer/PointerEventPass;", "bounds", "Landroidx/compose/ui/unit/IntSize;", "onPointerEvent-H0pRuoY", "(Landroidx/compose/ui/input/pointer/PointerEvent;Landroidx/compose/ui/input/pointer/PointerEventPass;J)V", "onIndirectPointerEvent", NotificationCompat.CATEGORY_EVENT, "Landroidx/compose/ui/input/indirect/IndirectPointerEvent;", "handleDownEvent", "down", "handleUpEvent", "uptimeMillis", "downChange", "handleNonUpEventIfNeeded", "handleNonUpEventIfNeeded-O0kMr_c", "(Landroidx/compose/ui/input/pointer/PointerEvent;J)V", "indirectPointerEvent", "handleDeepPress", "checkForCancellation", "onCancelPointerInput", "onCancelIndirectPointerInput", "cancelInput", "indirectPointer", "update", "update-2tQrsxU", "applyAdditionalSemantics", "Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;", "onClickKeyDownEvent", "Landroidx/compose/ui/input/key/KeyEvent;", "onClickKeyDownEvent-ZmokQxo", "(Landroid/view/KeyEvent;)Z", "onClickKeyUpEvent", "onClickKeyUpEvent-ZmokQxo", "onCancelKeyInput", "onReset", "resetKeyPressState", "DoubleKeyClickState", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
final class CombinedClickableNode extends AbstractClickableNode implements CompositionLocalConsumerModifierNode {
    private final MutableLongObjectMap<DoubleKeyClickState> doubleKeyClickStates;
    private PointerInputChange downEvent;
    private long firstTapUpTime;
    private boolean hapticFeedbackEnabled;
    private boolean ignoreNextUp;
    private IndirectPointerInputChange indirectDownEvent;
    private long indirectFirstTapUpTime;
    private boolean indirectIgnoreNextUp;
    private boolean indirectIsSecondTap;
    private Job indirectLongPressJob;
    private boolean indirectLongPressTriggered;
    private Job indirectTapJob;
    private boolean isSecondTap;
    private final boolean isSuspendingPointerInputEnabled;
    private final MutableLongObjectMap<Job> longKeyPressJobs;
    private Job longPressJob;
    private boolean longPressTriggered;
    private Function0<Unit> onDoubleClick;
    private Function0<Unit> onLongClick;
    private String onLongClickLabel;
    private Job tapJob;

    public /* synthetic */ CombinedClickableNode(Function0 function0, String str, Function0 function02, Function0 function03, boolean z, MutableInteractionSource mutableInteractionSource, IndicationNodeFactory indicationNodeFactory, boolean z2, boolean z3, String str2, Role role, DefaultConstructorMarker defaultConstructorMarker) {
        this(function0, str, function02, function03, z, mutableInteractionSource, indicationNodeFactory, z2, z3, str2, role);
    }

    private static /* synthetic */ void isSuspendingPointerInputEnabled$annotations() {
    }

    private CombinedClickableNode(Function0<Unit> function0, String onLongClickLabel, Function0<Unit> function02, Function0<Unit> function03, boolean hapticFeedbackEnabled, MutableInteractionSource interactionSource, IndicationNodeFactory indicationNodeFactory, boolean useLocalIndication, boolean enabled, String onClickLabel, Role role) {
        super(interactionSource, indicationNodeFactory, useLocalIndication, enabled, onClickLabel, role, function0, null);
        this.onLongClickLabel = onLongClickLabel;
        this.onLongClick = function02;
        this.onDoubleClick = function03;
        this.hapticFeedbackEnabled = hapticFeedbackEnabled;
        this.longKeyPressJobs = LongObjectMapKt.mutableLongObjectMapOf();
        this.doubleKeyClickStates = LongObjectMapKt.mutableLongObjectMapOf();
        this.isSuspendingPointerInputEnabled = !ComposeFoundationFlags.isNonSuspendingPointerInputInCombinedClickableEnabled;
        this.firstTapUpTime = -1L;
        this.indirectFirstTapUpTime = -1L;
    }

    public final boolean getHapticFeedbackEnabled() {
        return this.hapticFeedbackEnabled;
    }

    public final void setHapticFeedbackEnabled(boolean z) {
        this.hapticFeedbackEnabled = z;
    }

    /* JADX INFO: compiled from: Clickable.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Landroidx/compose/foundation/CombinedClickableNode$DoubleKeyClickState;", "", "job", "Lkotlinx/coroutines/Job;", "<init>", "(Lkotlinx/coroutines/Job;)V", "getJob", "()Lkotlinx/coroutines/Job;", "doubleTapMinTimeMillisElapsed", "", "getDoubleTapMinTimeMillisElapsed", "()Z", "setDoubleTapMinTimeMillisElapsed", "(Z)V", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class DoubleKeyClickState {
        public static final int $stable = 8;
        private boolean doubleTapMinTimeMillisElapsed;
        private final Job job;

        public DoubleKeyClickState(Job job) {
            this.job = job;
        }

        public final Job getJob() {
            return this.job;
        }

        public final boolean getDoubleTapMinTimeMillisElapsed() {
            return this.doubleTapMinTimeMillisElapsed;
        }

        public final void setDoubleTapMinTimeMillisElapsed(boolean z) {
            this.doubleTapMinTimeMillisElapsed = z;
        }
    }

    @Override // androidx.compose.foundation.AbstractClickableNode
    public SuspendingPointerInputModifierNode createPointerInputNodeIfNeeded() {
        if (this.isSuspendingPointerInputEnabled) {
            return SuspendingPointerInputFilterKt.SuspendingPointerInputModifierNode(new AnonymousClass1());
        }
        return null;
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.CombinedClickableNode$createPointerInputNodeIfNeeded$1, reason: invalid class name */
    /* JADX INFO: compiled from: Clickable.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    static final class AnonymousClass1 implements PointerInputEventHandler {
        AnonymousClass1() {
        }

        @Override // androidx.compose.ui.input.pointer.PointerInputEventHandler
        public final Object invoke(PointerInputScope $this$SuspendingPointerInputModifierNode, Continuation<? super Unit> continuation) {
            Function1 function1;
            Function1 function12;
            if (!CombinedClickableNode.this.getEnabled() || CombinedClickableNode.this.onDoubleClick == null) {
                function1 = null;
            } else {
                final CombinedClickableNode combinedClickableNode = CombinedClickableNode.this;
                function1 = new Function1() { // from class: androidx.compose.foundation.CombinedClickableNode$createPointerInputNodeIfNeeded$1$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return CombinedClickableNode.AnonymousClass1.invoke$lambda$0(combinedClickableNode, (Offset) obj);
                    }
                };
            }
            if (!CombinedClickableNode.this.getEnabled() || CombinedClickableNode.this.onLongClick == null) {
                function12 = null;
            } else {
                final CombinedClickableNode combinedClickableNode2 = CombinedClickableNode.this;
                function12 = new Function1() { // from class: androidx.compose.foundation.CombinedClickableNode$createPointerInputNodeIfNeeded$1$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return CombinedClickableNode.AnonymousClass1.invoke$lambda$1(combinedClickableNode2, (Offset) obj);
                    }
                };
            }
            AnonymousClass3 anonymousClass3 = new AnonymousClass3(CombinedClickableNode.this, null);
            final CombinedClickableNode combinedClickableNode3 = CombinedClickableNode.this;
            Object objDetectTapGestures = TapGestureDetectorKt.detectTapGestures($this$SuspendingPointerInputModifierNode, function1, function12, anonymousClass3, new Function1() { // from class: androidx.compose.foundation.CombinedClickableNode$createPointerInputNodeIfNeeded$1$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return CombinedClickableNode.AnonymousClass1.invoke$lambda$2(combinedClickableNode3, (Offset) obj);
                }
            }, continuation);
            return objDetectTapGestures == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objDetectTapGestures : Unit.INSTANCE;
        }

        static final Unit invoke$lambda$0(CombinedClickableNode this$0, Offset it) {
            Function0 function0 = this$0.onDoubleClick;
            if (function0 != null) {
                function0.invoke();
            }
            return Unit.INSTANCE;
        }

        static final Unit invoke$lambda$1(CombinedClickableNode this$0, Offset it) {
            Function0 function0 = this$0.onLongClick;
            if (function0 != null) {
                function0.invoke();
            }
            if (this$0.getHapticFeedbackEnabled()) {
                ((HapticFeedback) CompositionLocalConsumerModifierNodeKt.currentValueOf(this$0, CompositionLocalsKt.getLocalHapticFeedback())).mo6082performHapticFeedbackCdsT49E(HapticFeedbackType.INSTANCE.m6095getLongPress5zf0vsI());
            }
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: androidx.compose.foundation.CombinedClickableNode$createPointerInputNodeIfNeeded$1$3, reason: invalid class name */
        /* JADX INFO: compiled from: Clickable.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/foundation/gestures/PressGestureScope;", TypedValues.CycleType.S_WAVE_OFFSET, "Landroidx/compose/ui/geometry/Offset;"}, k = 3, mv = {2, 1, 0}, xi = 48)
        @DebugMetadata(c = "androidx.compose.foundation.CombinedClickableNode$createPointerInputNodeIfNeeded$1$3", f = "Clickable.kt", i = {}, l = {1132}, m = "invokeSuspend", n = {}, s = {}, v = 1)
        static final class AnonymousClass3 extends SuspendLambda implements Function3<PressGestureScope, Offset, Continuation<? super Unit>, Object> {
            /* synthetic */ long J$0;
            private /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ CombinedClickableNode this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass3(CombinedClickableNode combinedClickableNode, Continuation<? super AnonymousClass3> continuation) {
                super(3, continuation);
                this.this$0 = combinedClickableNode;
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Object invoke(PressGestureScope pressGestureScope, Offset offset, Continuation<? super Unit> continuation) {
                return m339invoked4ec7I(pressGestureScope, offset.m5078unboximpl(), continuation);
            }

            /* JADX INFO: renamed from: invoke-d-4ec7I, reason: not valid java name */
            public final Object m339invoked4ec7I(PressGestureScope pressGestureScope, long j, Continuation<? super Unit> continuation) {
                AnonymousClass3 anonymousClass3 = new AnonymousClass3(this.this$0, continuation);
                anonymousClass3.L$0 = pressGestureScope;
                anonymousClass3.J$0 = j;
                return anonymousClass3.invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object $result) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        PressGestureScope $this$detectTapGestures = (PressGestureScope) this.L$0;
                        long offset = this.J$0;
                        if (this.this$0.getEnabled()) {
                            this.label = 1;
                            if (this.this$0.m249handlePressInteractiond4ec7I($this$detectTapGestures, offset, this) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
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

        static final Unit invoke$lambda$2(CombinedClickableNode this$0, Offset it) {
            if (this$0.getEnabled()) {
                this$0.getOnClick().invoke();
            }
            return Unit.INSTANCE;
        }
    }

    @Override // androidx.compose.foundation.AbstractClickableNode, androidx.compose.ui.node.PointerInputModifierNode
    /* JADX INFO: renamed from: onPointerEvent-H0pRuoY */
    public void mo255onPointerEventH0pRuoY(PointerEvent pointerEvent, PointerEventPass pass, long bounds) {
        super.mo255onPointerEventH0pRuoY(pointerEvent, pass, bounds);
        if (this.isSuspendingPointerInputEnabled) {
            return;
        }
        if (pass != PointerEventPass.Main) {
            if (pass == PointerEventPass.Final) {
                checkForCancellation(pointerEvent);
                return;
            }
            return;
        }
        boolean z = true;
        if (this.downEvent == null) {
            if (TapGestureDetectorKt.isChangedToDown$default(pointerEvent, true, false, 2, null)) {
                handleDownEvent(pointerEvent.getChanges().get(0));
                return;
            }
            return;
        }
        if (TapGestureDetector_androidKt.isDeepPress(pointerEvent)) {
            handleDeepPress();
        }
        if (this.longPressTriggered) {
            List<PointerInputChange> changes = pointerEvent.getChanges();
            int index$iv$iv = 0;
            int size = changes.size();
            while (true) {
                if (index$iv$iv >= size) {
                    break;
                }
                Object item$iv$iv = changes.get(index$iv$iv);
                PointerInputChange it = (PointerInputChange) item$iv$iv;
                if (!PointerEventKt.changedToUpIgnoreConsumed(it)) {
                    z = false;
                    break;
                }
                index$iv$iv++;
            }
            if (z) {
                PointerInputChange up = pointerEvent.getChanges().get(0);
                up.consume();
                long uptimeMillis = up.getUptimeMillis();
                PointerInputChange pointerInputChange = this.downEvent;
                Intrinsics.checkNotNull(pointerInputChange);
                handleUpEvent(uptimeMillis, pointerInputChange);
                return;
            }
            List<PointerInputChange> changes2 = pointerEvent.getChanges();
            int size2 = changes2.size();
            for (int index$iv = 0; index$iv < size2; index$iv++) {
                Object item$iv = changes2.get(index$iv);
                PointerInputChange it2 = (PointerInputChange) item$iv;
                it2.consume();
            }
            return;
        }
        List<PointerInputChange> changes3 = pointerEvent.getChanges();
        int index$iv$iv2 = 0;
        int size3 = changes3.size();
        while (true) {
            if (index$iv$iv2 >= size3) {
                break;
            }
            Object item$iv$iv2 = changes3.get(index$iv$iv2);
            PointerInputChange it3 = (PointerInputChange) item$iv$iv2;
            if (!PointerEventKt.changedToUp(it3)) {
                z = false;
                break;
            }
            index$iv$iv2++;
        }
        if (!z) {
            m337handleNonUpEventIfNeededO0kMr_c(pointerEvent, bounds);
            return;
        }
        PointerInputChange up2 = pointerEvent.getChanges().get(0);
        up2.consume();
        long uptimeMillis2 = up2.getUptimeMillis();
        PointerInputChange pointerInputChange2 = this.downEvent;
        Intrinsics.checkNotNull(pointerInputChange2);
        handleUpEvent(uptimeMillis2, pointerInputChange2);
    }

    @Override // androidx.compose.foundation.AbstractClickableNode, androidx.compose.ui.input.indirect.IndirectPointerInputModifierNode
    public void onIndirectPointerEvent(IndirectPointerEvent event, PointerEventPass pass) {
        super.onIndirectPointerEvent(event, pass);
        if (pass == PointerEventPass.Main) {
            boolean z = true;
            if (this.indirectDownEvent == null) {
                List<IndirectPointerInputChange> changes = event.getChanges();
                int index$iv$iv = 0;
                int size = changes.size();
                while (true) {
                    if (index$iv$iv < size) {
                        Object item$iv$iv = changes.get(index$iv$iv);
                        IndirectPointerInputChange it = (IndirectPointerInputChange) item$iv$iv;
                        if (IndirectPointerInputDragCycleDetectorKt.changedToDownIgnoreConsumed(it)) {
                            break;
                        } else {
                            index$iv$iv++;
                        }
                    } else {
                        z = false;
                        break;
                    }
                }
                if (z) {
                    handleDownEvent(event.getChanges().get(0));
                    return;
                }
                return;
            }
            if (this.indirectLongPressTriggered) {
                List<IndirectPointerInputChange> changes2 = event.getChanges();
                int index$iv$iv2 = 0;
                int size2 = changes2.size();
                while (true) {
                    if (index$iv$iv2 >= size2) {
                        break;
                    }
                    Object item$iv$iv2 = changes2.get(index$iv$iv2);
                    IndirectPointerInputChange it2 = (IndirectPointerInputChange) item$iv$iv2;
                    if (!ClickableKt.changedToUpIgnoreConsumed(it2)) {
                        z = false;
                        break;
                    }
                    index$iv$iv2++;
                }
                if (z) {
                    IndirectPointerInputChange up = event.getChanges().get(0);
                    up.consume();
                    long uptimeMillis = up.getUptimeMillis();
                    IndirectPointerInputChange indirectPointerInputChange = this.indirectDownEvent;
                    Intrinsics.checkNotNull(indirectPointerInputChange);
                    handleUpEvent(uptimeMillis, indirectPointerInputChange);
                    return;
                }
                List<IndirectPointerInputChange> changes3 = event.getChanges();
                int size3 = changes3.size();
                for (int index$iv = 0; index$iv < size3; index$iv++) {
                    Object item$iv = changes3.get(index$iv);
                    IndirectPointerInputChange it3 = (IndirectPointerInputChange) item$iv;
                    it3.consume();
                }
                return;
            }
            List<IndirectPointerInputChange> changes4 = event.getChanges();
            int index$iv$iv3 = 0;
            int size4 = changes4.size();
            while (true) {
                if (index$iv$iv3 >= size4) {
                    break;
                }
                Object item$iv$iv3 = changes4.get(index$iv$iv3);
                IndirectPointerInputChange it4 = (IndirectPointerInputChange) item$iv$iv3;
                if (!ClickableKt.changedToUp(it4)) {
                    z = false;
                    break;
                }
                index$iv$iv3++;
            }
            if (z) {
                IndirectPointerInputChange up2 = event.getChanges().get(0);
                up2.consume();
                long uptimeMillis2 = up2.getUptimeMillis();
                IndirectPointerInputChange indirectPointerInputChange2 = this.indirectDownEvent;
                Intrinsics.checkNotNull(indirectPointerInputChange2);
                handleUpEvent(uptimeMillis2, indirectPointerInputChange2);
                return;
            }
            handleNonUpEventIfNeeded(event);
            return;
        }
        if (pass == PointerEventPass.Final) {
            checkForCancellation(event);
        }
    }

    private final void handleDownEvent(PointerInputChange down) {
        down.consume();
        this.downEvent = down;
        if (getEnabled()) {
            Job job = this.tapJob;
            if (job != null && job.isActive()) {
                long minTime = ((ViewConfiguration) CompositionLocalConsumerModifierNodeKt.currentValueOf(this, CompositionLocalsKt.getLocalViewConfiguration())).getDoubleTapMinTimeMillis();
                if (down.getUptimeMillis() - this.firstTapUpTime < minTime) {
                    this.ignoreNextUp = true;
                    return;
                }
                this.isSecondTap = true;
                Job job2 = this.tapJob;
                if (job2 != null) {
                    Job.DefaultImpls.cancel$default(job2, (CancellationException) null, 1, (Object) null);
                }
                this.tapJob = null;
            }
            this.longPressTriggered = false;
            if (ComposeFoundationFlags.isDelayPressesUsingGestureConsumptionEnabled) {
                handlePressInteractionStart(down);
            } else {
                m251handlePressInteractionStart3MmeM6k(down.getPosition(), false);
            }
            if (this.onLongClick != null) {
                this.longPressJob = BuildersKt__Builders_commonKt.launch$default(getCoroutineScope(), null, null, new C01361(null), 3, null);
            }
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.CombinedClickableNode$handleDownEvent$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Clickable.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.CombinedClickableNode$handleDownEvent$1", f = "Clickable.kt", i = {}, l = {1273}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class C01361 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C01361(Continuation<? super C01361> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return CombinedClickableNode.this.new C01361(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C01361) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    this.label = 1;
                    if (DelayKt.delay(((ViewConfiguration) CompositionLocalConsumerModifierNodeKt.currentValueOf(CombinedClickableNode.this, CompositionLocalsKt.getLocalViewConfiguration())).getLongPressTimeoutMillis(), this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            Function0 function0 = CombinedClickableNode.this.onLongClick;
            if (function0 != null) {
                function0.invoke();
            }
            if (CombinedClickableNode.this.getHapticFeedbackEnabled()) {
                ((HapticFeedback) CompositionLocalConsumerModifierNodeKt.currentValueOf(CombinedClickableNode.this, CompositionLocalsKt.getLocalHapticFeedback())).mo6082performHapticFeedbackCdsT49E(HapticFeedbackType.INSTANCE.m6095getLongPress5zf0vsI());
            }
            CombinedClickableNode.this.longPressTriggered = true;
            Job job = CombinedClickableNode.this.tapJob;
            if (job != null) {
                Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
            }
            CombinedClickableNode.this.tapJob = null;
            CombinedClickableNode.this.longPressJob = null;
            return Unit.INSTANCE;
        }
    }

    private final void handleDownEvent(IndirectPointerInputChange down) {
        down.consume();
        this.indirectDownEvent = down;
        if (getEnabled()) {
            Job job = this.indirectTapJob;
            if (job != null && job.isActive()) {
                long minTime = ((ViewConfiguration) CompositionLocalConsumerModifierNodeKt.currentValueOf(this, CompositionLocalsKt.getLocalViewConfiguration())).getDoubleTapMinTimeMillis();
                if (down.getUptimeMillis() - this.indirectFirstTapUpTime < minTime) {
                    this.indirectIgnoreNextUp = true;
                    return;
                }
                this.indirectIsSecondTap = true;
                Job job2 = this.indirectTapJob;
                if (job2 != null) {
                    Job.DefaultImpls.cancel$default(job2, (CancellationException) null, 1, (Object) null);
                }
                this.indirectTapJob = null;
            }
            this.indirectLongPressTriggered = false;
            if (ComposeFoundationFlags.isDelayPressesUsingGestureConsumptionEnabled) {
                handlePressInteractionStart(down);
            } else {
                m251handlePressInteractionStart3MmeM6k(down.getPosition(), true);
            }
            if (this.onLongClick != null) {
                this.indirectLongPressJob = BuildersKt__Builders_commonKt.launch$default(getCoroutineScope(), null, null, new AnonymousClass2(null), 3, null);
            }
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.CombinedClickableNode$handleDownEvent$2, reason: invalid class name */
    /* JADX INFO: compiled from: Clickable.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.CombinedClickableNode$handleDownEvent$2", f = "Clickable.kt", i = {}, l = {1318}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return CombinedClickableNode.this.new AnonymousClass2(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    this.label = 1;
                    if (DelayKt.delay(((ViewConfiguration) CompositionLocalConsumerModifierNodeKt.currentValueOf(CombinedClickableNode.this, CompositionLocalsKt.getLocalViewConfiguration())).getLongPressTimeoutMillis(), this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            Function0 function0 = CombinedClickableNode.this.onLongClick;
            if (function0 != null) {
                function0.invoke();
            }
            if (CombinedClickableNode.this.getHapticFeedbackEnabled()) {
                ((HapticFeedback) CompositionLocalConsumerModifierNodeKt.currentValueOf(CombinedClickableNode.this, CompositionLocalsKt.getLocalHapticFeedback())).mo6082performHapticFeedbackCdsT49E(HapticFeedbackType.INSTANCE.m6095getLongPress5zf0vsI());
            }
            CombinedClickableNode.this.indirectLongPressTriggered = true;
            Job job = CombinedClickableNode.this.indirectTapJob;
            if (job != null) {
                Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
            }
            CombinedClickableNode.this.indirectTapJob = null;
            CombinedClickableNode.this.indirectLongPressJob = null;
            return Unit.INSTANCE;
        }
    }

    private final void handleUpEvent(long uptimeMillis, PointerInputChange downChange) {
        if (getEnabled() && !this.ignoreNextUp) {
            m250handlePressInteractionRelease3MmeM6k(downChange.getPosition(), false);
            this.firstTapUpTime = uptimeMillis;
            if (!this.longPressTriggered) {
                boolean z = this.isSecondTap;
                Function0<Unit> function0 = this.onDoubleClick;
                if (z) {
                    if (function0 != null) {
                        function0.invoke();
                    }
                } else if (function0 != null) {
                    this.tapJob = BuildersKt__Builders_commonKt.launch$default(getCoroutineScope(), null, null, new C01371(null), 3, null);
                } else {
                    getOnClick().invoke();
                }
            }
        }
        this.downEvent = null;
        this.ignoreNextUp = false;
        this.isSecondTap = false;
        Job job = this.longPressJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this.longPressJob = null;
        this.longPressTriggered = false;
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.CombinedClickableNode$handleUpEvent$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Clickable.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.CombinedClickableNode$handleUpEvent$1", f = "Clickable.kt", i = {}, l = {1344}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class C01371 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C01371(Continuation<? super C01371> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return CombinedClickableNode.this.new C01371(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C01371) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    this.label = 1;
                    if (DelayKt.delay(((ViewConfiguration) CompositionLocalConsumerModifierNodeKt.currentValueOf(CombinedClickableNode.this, CompositionLocalsKt.getLocalViewConfiguration())).getDoubleTapTimeoutMillis(), this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            CombinedClickableNode.this.getOnClick().invoke();
            CombinedClickableNode.this.tapJob = null;
            return Unit.INSTANCE;
        }
    }

    private final void handleUpEvent(long uptimeMillis, IndirectPointerInputChange downChange) {
        if (getEnabled() && !this.indirectIgnoreNextUp) {
            m250handlePressInteractionRelease3MmeM6k(downChange.getPosition(), true);
            this.indirectFirstTapUpTime = uptimeMillis;
            if (!this.indirectLongPressTriggered) {
                boolean z = this.indirectIsSecondTap;
                Function0<Unit> function0 = this.onDoubleClick;
                if (z) {
                    if (function0 != null) {
                        function0.invoke();
                    }
                } else if (function0 != null) {
                    this.indirectTapJob = BuildersKt__Builders_commonKt.launch$default(getCoroutineScope(), null, null, new C01382(null), 3, null);
                } else {
                    getOnClick().invoke();
                }
            }
        }
        this.indirectDownEvent = null;
        this.indirectIgnoreNextUp = false;
        this.indirectIsSecondTap = false;
        Job job = this.indirectLongPressJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this.indirectLongPressJob = null;
        this.indirectLongPressTriggered = false;
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.CombinedClickableNode$handleUpEvent$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Clickable.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.CombinedClickableNode$handleUpEvent$2", f = "Clickable.kt", i = {}, l = {1373}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class C01382 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C01382(Continuation<? super C01382> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return CombinedClickableNode.this.new C01382(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C01382) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    this.label = 1;
                    if (DelayKt.delay(((ViewConfiguration) CompositionLocalConsumerModifierNodeKt.currentValueOf(CombinedClickableNode.this, CompositionLocalsKt.getLocalViewConfiguration())).getDoubleTapTimeoutMillis(), this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            CombinedClickableNode.this.getOnClick().invoke();
            CombinedClickableNode.this.indirectTapJob = null;
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: handleNonUpEventIfNeeded-O0kMr_c, reason: not valid java name */
    private final void m337handleNonUpEventIfNeededO0kMr_c(PointerEvent pointerEvent, long bounds) {
        boolean z;
        long touchPadding = m248getExtendedTouchPaddinghWWAJMo(bounds);
        List<PointerInputChange> changes = pointerEvent.getChanges();
        int index$iv$iv = 0;
        int size = changes.size();
        while (true) {
            if (index$iv$iv < size) {
                Object item$iv$iv = changes.get(index$iv$iv);
                PointerInputChange change = (PointerInputChange) item$iv$iv;
                z = true;
                if (change.isConsumed() || PointerEventKt.m6586isOutOfBoundsjwHxaWs(change, bounds, touchPadding)) {
                    break;
                } else {
                    index$iv$iv++;
                }
            } else {
                z = false;
                break;
            }
        }
        if (z) {
            cancelInput(false);
        }
    }

    private final void handleNonUpEventIfNeeded(IndirectPointerEvent indirectPointerEvent) {
        boolean z;
        float touchSlop = ((ViewConfiguration) CompositionLocalConsumerModifierNodeKt.currentValueOf(this, CompositionLocalsKt.getLocalViewConfiguration())).getTouchSlop();
        List<IndirectPointerInputChange> changes = indirectPointerEvent.getChanges();
        int index$iv$iv = 0;
        int size = changes.size();
        while (true) {
            if (index$iv$iv < size) {
                Object item$iv$iv = changes.get(index$iv$iv);
                IndirectPointerInputChange change = (IndirectPointerInputChange) item$iv$iv;
                long position = change.getPosition();
                IndirectPointerInputChange indirectPointerInputChange = this.indirectDownEvent;
                Intrinsics.checkNotNull(indirectPointerInputChange);
                float touchSlop2 = touchSlop;
                List<IndirectPointerInputChange> list = changes;
                long distanceFromPress = Offset.m5072minusMKHz9U(position, indirectPointerInputChange.getPosition());
                boolean isOutOfBounds = Math.abs(Offset.m5066getDistanceimpl(distanceFromPress)) > touchSlop2;
                boolean isOutOfBounds2 = change.getIsConsumed() || isOutOfBounds;
                if (isOutOfBounds2) {
                    z = true;
                    break;
                } else {
                    index$iv$iv++;
                    touchSlop = touchSlop2;
                    changes = list;
                }
            } else {
                z = false;
                break;
            }
        }
        if (z) {
            cancelInput(true);
        }
    }

    private final void handleDeepPress() {
        if (!this.longPressTriggered && getEnabled() && this.onLongClick != null) {
            Job job = this.longPressJob;
            if (job != null) {
                Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
            }
            this.longPressJob = null;
            Function0<Unit> function0 = this.onLongClick;
            if (function0 != null) {
                function0.invoke();
            }
            if (this.hapticFeedbackEnabled) {
                ((HapticFeedback) CompositionLocalConsumerModifierNodeKt.currentValueOf(this, CompositionLocalsKt.getLocalHapticFeedback())).mo6082performHapticFeedbackCdsT49E(HapticFeedbackType.INSTANCE.m6095getLongPress5zf0vsI());
            }
            this.longPressTriggered = true;
        }
    }

    private final void checkForCancellation(PointerEvent pointerEvent) {
        boolean z;
        if (this.downEvent != null && !this.longPressTriggered) {
            List<PointerInputChange> changes = pointerEvent.getChanges();
            int index$iv$iv = 0;
            int size = changes.size();
            while (true) {
                if (index$iv$iv < size) {
                    Object item$iv$iv = changes.get(index$iv$iv);
                    PointerInputChange it = (PointerInputChange) item$iv$iv;
                    z = true;
                    if (it.isConsumed() && !Intrinsics.areEqual(it, this.downEvent)) {
                        break;
                    } else {
                        index$iv$iv++;
                    }
                } else {
                    z = false;
                    break;
                }
            }
            if (z) {
                cancelInput(false);
            }
        }
    }

    private final void checkForCancellation(IndirectPointerEvent indirectPointerEvent) {
        boolean z;
        if (this.indirectDownEvent != null && !this.indirectLongPressTriggered) {
            List<IndirectPointerInputChange> changes = indirectPointerEvent.getChanges();
            int index$iv$iv = 0;
            int size = changes.size();
            while (true) {
                z = false;
                if (index$iv$iv >= size) {
                    break;
                }
                Object item$iv$iv = changes.get(index$iv$iv);
                IndirectPointerInputChange it = (IndirectPointerInputChange) item$iv$iv;
                if (it.getIsConsumed() && !Intrinsics.areEqual(it, this.indirectDownEvent)) {
                    z = true;
                }
                if (z) {
                    z = true;
                    break;
                }
                index$iv$iv++;
            }
            if (z) {
                cancelInput(true);
            }
        }
    }

    @Override // androidx.compose.foundation.AbstractClickableNode, androidx.compose.ui.node.PointerInputModifierNode
    public void onCancelPointerInput() {
        super.onCancelPointerInput();
        cancelInput(false);
    }

    @Override // androidx.compose.ui.input.indirect.IndirectPointerInputModifierNode
    public void onCancelIndirectPointerInput() {
        cancelInput(true);
    }

    private final void cancelInput(boolean indirectPointer) {
        if (indirectPointer) {
            this.indirectDownEvent = null;
            Job job = this.indirectLongPressJob;
            if (job != null) {
                Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
            }
            this.indirectLongPressJob = null;
            Job job2 = this.indirectTapJob;
            if (job2 != null) {
                Job.DefaultImpls.cancel$default(job2, (CancellationException) null, 1, (Object) null);
            }
            this.indirectTapJob = null;
            this.indirectIsSecondTap = false;
            this.indirectLongPressTriggered = false;
            this.indirectFirstTapUpTime = -1L;
            this.indirectIgnoreNextUp = false;
        } else {
            this.downEvent = null;
            Job job3 = this.longPressJob;
            if (job3 != null) {
                Job.DefaultImpls.cancel$default(job3, (CancellationException) null, 1, (Object) null);
            }
            this.longPressJob = null;
            Job job4 = this.tapJob;
            if (job4 != null) {
                Job.DefaultImpls.cancel$default(job4, (CancellationException) null, 1, (Object) null);
            }
            this.tapJob = null;
            this.isSecondTap = false;
            this.longPressTriggered = false;
            this.firstTapUpTime = -1L;
            this.ignoreNextUp = false;
        }
        handlePressInteractionCancel(indirectPointer);
    }

    /* JADX INFO: renamed from: update-2tQrsxU, reason: not valid java name */
    public final void m338update2tQrsxU(Function0<Unit> onClick, String onLongClickLabel, Function0<Unit> onLongClick, Function0<Unit> onDoubleClick, MutableInteractionSource interactionSource, IndicationNodeFactory indicationNodeFactory, boolean useLocalIndication, boolean enabled, String onClickLabel, Role role) {
        boolean resetPointerInputHandling;
        boolean resetPointerInputHandling2 = false;
        if (!Intrinsics.areEqual(this.onLongClickLabel, onLongClickLabel)) {
            this.onLongClickLabel = onLongClickLabel;
            SemanticsModifierNodeKt.invalidateSemantics(this);
        }
        if ((this.onLongClick == null) != (onLongClick == null)) {
            disposeInteractions();
            SemanticsModifierNodeKt.invalidateSemantics(this);
            resetPointerInputHandling2 = true;
        }
        this.onLongClick = onLongClick;
        if ((this.onDoubleClick == null) != (onDoubleClick == null)) {
            resetPointerInputHandling2 = true;
        }
        this.onDoubleClick = onDoubleClick;
        if (getEnabled() == enabled) {
            resetPointerInputHandling = resetPointerInputHandling2;
        } else {
            resetPointerInputHandling = true;
        }
        m257updateCommonO2vRcR0(interactionSource, indicationNodeFactory, useLocalIndication, enabled, onClickLabel, role, onClick);
        if (resetPointerInputHandling) {
            resetPointerInputHandler();
            cancelInput(false);
            cancelInput(true);
        }
    }

    @Override // androidx.compose.foundation.AbstractClickableNode
    public void applyAdditionalSemantics(SemanticsPropertyReceiver $this$applyAdditionalSemantics) {
        if (this.onLongClick != null) {
            SemanticsPropertiesKt.onLongClick($this$applyAdditionalSemantics, this.onLongClickLabel, new Function0() { // from class: androidx.compose.foundation.CombinedClickableNode$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return Boolean.valueOf(CombinedClickableNode.applyAdditionalSemantics$lambda$0(this.f$0));
                }
            });
        }
    }

    static final boolean applyAdditionalSemantics$lambda$0(CombinedClickableNode this$0) {
        Function0<Unit> function0 = this$0.onLongClick;
        if (function0 != null) {
            function0.invoke();
            return true;
        }
        return true;
    }

    @Override // androidx.compose.foundation.AbstractClickableNode
    /* JADX INFO: renamed from: onClickKeyDownEvent-ZmokQxo */
    protected boolean mo252onClickKeyDownEventZmokQxo(KeyEvent event) {
        long keyCode = KeyEvent_androidKt.m6482getKeyZmokQxo(event);
        boolean handledByLongClick = false;
        if (this.onLongClick != null && this.longKeyPressJobs.get(keyCode) == null) {
            this.longKeyPressJobs.set(keyCode, BuildersKt__Builders_commonKt.launch$default(getCoroutineScope(), null, null, new CombinedClickableNode$onClickKeyDownEvent$1(this, null), 3, null));
            handledByLongClick = true;
        }
        DoubleKeyClickState doubleClickState = this.doubleKeyClickStates.get(keyCode);
        if (doubleClickState != null) {
            if (doubleClickState.getJob().isActive()) {
                Job.DefaultImpls.cancel$default(doubleClickState.getJob(), (CancellationException) null, 1, (Object) null);
                if (!doubleClickState.getDoubleTapMinTimeMillisElapsed()) {
                    getOnClick().invoke();
                    this.doubleKeyClickStates.remove(keyCode);
                }
            } else {
                this.doubleKeyClickStates.remove(keyCode);
            }
        }
        return handledByLongClick;
    }

    @Override // androidx.compose.foundation.AbstractClickableNode
    /* JADX INFO: renamed from: onClickKeyUpEvent-ZmokQxo */
    protected boolean mo253onClickKeyUpEventZmokQxo(KeyEvent event) {
        Function0<Unit> function0;
        long keyCode = KeyEvent_androidKt.m6482getKeyZmokQxo(event);
        boolean longClickInvoked = false;
        if (this.longKeyPressJobs.get(keyCode) != null) {
            Job it = this.longKeyPressJobs.get(keyCode);
            if (it != null) {
                if (it.isActive()) {
                    Job.DefaultImpls.cancel$default(it, (CancellationException) null, 1, (Object) null);
                } else {
                    longClickInvoked = true;
                }
            }
            this.longKeyPressJobs.remove(keyCode);
        }
        if (this.onDoubleClick != null) {
            if (this.doubleKeyClickStates.get(keyCode) == null) {
                if (!longClickInvoked) {
                    this.doubleKeyClickStates.set(keyCode, new DoubleKeyClickState(BuildersKt__Builders_commonKt.launch$default(getCoroutineScope(), null, null, new CombinedClickableNode$onClickKeyUpEvent$2(this, keyCode, null), 3, null)));
                }
            } else {
                if (!longClickInvoked && (function0 = this.onDoubleClick) != null) {
                    function0.invoke();
                }
                this.doubleKeyClickStates.remove(keyCode);
            }
        } else if (!longClickInvoked) {
            getOnClick().invoke();
        }
        return true;
    }

    @Override // androidx.compose.foundation.AbstractClickableNode
    protected void onCancelKeyInput() {
        resetKeyPressState();
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void onReset() {
        super.onReset();
        resetKeyPressState();
    }

    private final void resetKeyPressState() {
        MutableLongObjectMap<Job> mutableLongObjectMap;
        long j;
        MutableLongObjectMap<DoubleKeyClickState> mutableLongObjectMap2;
        int i;
        LongObjectMap this_$iv;
        int j$iv$iv;
        int i2;
        LongObjectMap this_$iv2;
        int i3;
        MutableLongObjectMap<Job> mutableLongObjectMap3;
        int i4;
        MutableLongObjectMap<Job> mutableLongObjectMap4 = this.longKeyPressJobs;
        int i5 = 0;
        MutableLongObjectMap<Job> this_$iv3 = mutableLongObjectMap4;
        Object[] v$iv = this_$iv3.values;
        long[] m$iv$iv = this_$iv3.metadata;
        int lastIndex$iv$iv = m$iv$iv.length - 2;
        int i$iv$iv = 0;
        if (0 <= lastIndex$iv$iv) {
            while (true) {
                long slot$iv$iv = m$iv$iv[i$iv$iv];
                j = 255;
                if ((((~slot$iv$iv) << 7) & slot$iv$iv & (-9187201950435737472L)) == -9187201950435737472L) {
                    mutableLongObjectMap = mutableLongObjectMap4;
                    i3 = i5;
                } else {
                    int bitCount$iv$iv = 8 - ((~(i$iv$iv - lastIndex$iv$iv)) >>> 31);
                    int j$iv$iv2 = 0;
                    while (j$iv$iv2 < bitCount$iv$iv) {
                        long value$iv$iv$iv = slot$iv$iv & 255;
                        if (!(value$iv$iv$iv < 128)) {
                            mutableLongObjectMap3 = mutableLongObjectMap4;
                            i4 = i5;
                        } else {
                            int index$iv$iv = (i$iv$iv << 3) + j$iv$iv2;
                            Job it = (Job) v$iv[index$iv$iv];
                            mutableLongObjectMap3 = mutableLongObjectMap4;
                            i4 = i5;
                            Job.DefaultImpls.cancel$default(it, (CancellationException) null, 1, (Object) null);
                        }
                        slot$iv$iv >>= 8;
                        j$iv$iv2++;
                        mutableLongObjectMap4 = mutableLongObjectMap3;
                        i5 = i4;
                    }
                    mutableLongObjectMap = mutableLongObjectMap4;
                    i3 = i5;
                    if (bitCount$iv$iv != 8) {
                        break;
                    }
                }
                if (i$iv$iv == lastIndex$iv$iv) {
                    break;
                }
                i$iv$iv++;
                mutableLongObjectMap4 = mutableLongObjectMap;
                i5 = i3;
            }
        } else {
            mutableLongObjectMap = mutableLongObjectMap4;
            j = 255;
        }
        mutableLongObjectMap.clear();
        MutableLongObjectMap<DoubleKeyClickState> mutableLongObjectMap5 = this.doubleKeyClickStates;
        int i6 = 0;
        MutableLongObjectMap<DoubleKeyClickState> this_$iv4 = mutableLongObjectMap5;
        Object[] v$iv2 = this_$iv4.values;
        long[] m$iv$iv2 = this_$iv4.metadata;
        int lastIndex$iv$iv2 = m$iv$iv2.length - 2;
        int i$iv$iv2 = 0;
        if (0 <= lastIndex$iv$iv2) {
            while (true) {
                long slot$iv$iv2 = m$iv$iv2[i$iv$iv2];
                mutableLongObjectMap2 = mutableLongObjectMap5;
                if ((((~slot$iv$iv2) << 7) & slot$iv$iv2 & (-9187201950435737472L)) == -9187201950435737472L) {
                    i = i6;
                    this_$iv = this_$iv4;
                } else {
                    int bitCount$iv$iv2 = 8 - ((~(i$iv$iv2 - lastIndex$iv$iv2)) >>> 31);
                    int j$iv$iv3 = 0;
                    while (j$iv$iv3 < bitCount$iv$iv2) {
                        long value$iv$iv$iv2 = slot$iv$iv2 & j;
                        if (!(value$iv$iv$iv2 < 128)) {
                            j$iv$iv = j$iv$iv3;
                            i2 = i6;
                            this_$iv2 = this_$iv4;
                        } else {
                            int index$iv$iv2 = (i$iv$iv2 << 3) + j$iv$iv3;
                            DoubleKeyClickState it2 = (DoubleKeyClickState) v$iv2[index$iv$iv2];
                            j$iv$iv = j$iv$iv3;
                            i2 = i6;
                            this_$iv2 = this_$iv4;
                            Job.DefaultImpls.cancel$default(it2.getJob(), (CancellationException) null, 1, (Object) null);
                        }
                        slot$iv$iv2 >>= 8;
                        j$iv$iv3 = j$iv$iv + 1;
                        this_$iv4 = this_$iv2;
                        i6 = i2;
                    }
                    i = i6;
                    this_$iv = this_$iv4;
                    if (bitCount$iv$iv2 != 8) {
                        break;
                    }
                }
                if (i$iv$iv2 == lastIndex$iv$iv2) {
                    break;
                }
                i$iv$iv2++;
                mutableLongObjectMap5 = mutableLongObjectMap2;
                this_$iv4 = this_$iv;
                i6 = i;
            }
        } else {
            mutableLongObjectMap2 = mutableLongObjectMap5;
        }
        mutableLongObjectMap2.clear();
    }
}
