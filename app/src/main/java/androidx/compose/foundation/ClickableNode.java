package androidx.compose.foundation;

import android.view.KeyEvent;
import androidx.compose.foundation.gestures.IndirectPointerInputDragCycleDetectorKt;
import androidx.compose.foundation.gestures.TapGestureDetectorKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.indirect.IndirectPointerEvent;
import androidx.compose.ui.input.indirect.IndirectPointerInputChange;
import androidx.compose.ui.input.pointer.PointerEvent;
import androidx.compose.ui.input.pointer.PointerEventKt;
import androidx.compose.ui.input.pointer.PointerEventPass;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNodeKt;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.ViewConfiguration;
import androidx.compose.ui.semantics.Role;
import androidx.core.app.NotificationCompat;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Clickable.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0011\u0018\u00002\u00020\u0001BM\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e¢\u0006\u0004\b\u0010\u0010\u0011J'\u0010\u0016\u001a\u00020\u000f2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0016¢\u0006\u0004\b\u001d\u0010\u001eJ\u0018\u0010\u001f\u001a\u00020\u000f2\u0006\u0010 \u001a\u00020!2\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\u0010\u0010\"\u001a\u00020\u000f2\u0006\u0010#\u001a\u00020\u0013H\u0002J\u0010\u0010\"\u001a\u00020\u000f2\u0006\u0010#\u001a\u00020\u0015H\u0002J\u0010\u0010$\u001a\u00020\u000f2\u0006\u0010%\u001a\u00020\u0013H\u0002J\u0010\u0010$\u001a\u00020\u000f2\u0006\u0010%\u001a\u00020\u0015H\u0002J\u001f\u0010&\u001a\u00020\u000f2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u001cH\u0002¢\u0006\u0004\b'\u0010(J\u0010\u0010&\u001a\u00020\u000f2\u0006\u0010)\u001a\u00020!H\u0002J\u0010\u0010*\u001a\u00020\u000f2\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u0010\u0010*\u001a\u00020\u000f2\u0006\u0010)\u001a\u00020!H\u0002J\b\u0010+\u001a\u00020\u000fH\u0016J\b\u0010,\u001a\u00020\u000fH\u0016J\u0010\u0010-\u001a\u00020\u000f2\u0006\u0010.\u001a\u00020\u0007H\u0002JQ\u0010/\u001a\u00020\u000f2\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\b\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e¢\u0006\u0002\b0J\u0017\u00101\u001a\u00020\u00072\u0006\u0010 \u001a\u000202H\u0004¢\u0006\u0004\b3\u00104J\u0017\u00105\u001a\u00020\u00072\u0006\u0010 \u001a\u000202H\u0004¢\u0006\u0004\b6\u00104R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0002\n\u0000¨\u00067"}, d2 = {"Landroidx/compose/foundation/ClickableNode;", "Landroidx/compose/foundation/AbstractClickableNode;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "indicationNodeFactory", "Landroidx/compose/foundation/IndicationNodeFactory;", "useLocalIndication", "", "enabled", "onClickLabel", "", "role", "Landroidx/compose/ui/semantics/Role;", "onClick", "Lkotlin/Function0;", "", "<init>", "(Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/foundation/IndicationNodeFactory;ZZLjava/lang/String;Landroidx/compose/ui/semantics/Role;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "downEvent", "Landroidx/compose/ui/input/pointer/PointerInputChange;", "indirectDownEvent", "Landroidx/compose/ui/input/indirect/IndirectPointerInputChange;", "onPointerEvent", "pointerEvent", "Landroidx/compose/ui/input/pointer/PointerEvent;", "pass", "Landroidx/compose/ui/input/pointer/PointerEventPass;", "bounds", "Landroidx/compose/ui/unit/IntSize;", "onPointerEvent-H0pRuoY", "(Landroidx/compose/ui/input/pointer/PointerEvent;Landroidx/compose/ui/input/pointer/PointerEventPass;J)V", "onIndirectPointerEvent", NotificationCompat.CATEGORY_EVENT, "Landroidx/compose/ui/input/indirect/IndirectPointerEvent;", "handleDownEvent", "down", "handleUpEvent", "up", "handleNonUpEventIfNeeded", "handleNonUpEventIfNeeded-O0kMr_c", "(Landroidx/compose/ui/input/pointer/PointerEvent;J)V", "indirectPointerEvent", "checkForCancellation", "onCancelPointerInput", "onCancelIndirectPointerInput", "cancelInput", "indirectPointer", "update", "update-O2vRcR0", "onClickKeyDownEvent", "Landroidx/compose/ui/input/key/KeyEvent;", "onClickKeyDownEvent-ZmokQxo", "(Landroid/view/KeyEvent;)Z", "onClickKeyUpEvent", "onClickKeyUpEvent-ZmokQxo", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
public class ClickableNode extends AbstractClickableNode {
    public static final int $stable = 8;
    private PointerInputChange downEvent;
    private IndirectPointerInputChange indirectDownEvent;

    public /* synthetic */ ClickableNode(MutableInteractionSource mutableInteractionSource, IndicationNodeFactory indicationNodeFactory, boolean z, boolean z2, String str, Role role, Function0 function0, DefaultConstructorMarker defaultConstructorMarker) {
        this(mutableInteractionSource, indicationNodeFactory, z, z2, str, role, function0);
    }

    private ClickableNode(MutableInteractionSource interactionSource, IndicationNodeFactory indicationNodeFactory, boolean useLocalIndication, boolean enabled, String onClickLabel, Role role, Function0<Unit> function0) {
        super(interactionSource, indicationNodeFactory, useLocalIndication, enabled, onClickLabel, role, function0, null);
    }

    @Override // androidx.compose.foundation.AbstractClickableNode, androidx.compose.ui.node.PointerInputModifierNode
    /* JADX INFO: renamed from: onPointerEvent-H0pRuoY */
    public void mo255onPointerEventH0pRuoY(PointerEvent pointerEvent, PointerEventPass pass, long bounds) {
        super.mo255onPointerEventH0pRuoY(pointerEvent, pass, bounds);
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
        List<PointerInputChange> changes = pointerEvent.getChanges();
        int index$iv$iv = 0;
        int size = changes.size();
        while (true) {
            if (index$iv$iv >= size) {
                break;
            }
            Object item$iv$iv = changes.get(index$iv$iv);
            PointerInputChange it = (PointerInputChange) item$iv$iv;
            if (!PointerEventKt.changedToUp(it)) {
                z = false;
                break;
            }
            index$iv$iv++;
        }
        if (z) {
            handleUpEvent(pointerEvent.getChanges().get(0));
        } else {
            m335handleNonUpEventIfNeededO0kMr_c(pointerEvent, bounds);
        }
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
            List<IndirectPointerInputChange> changes2 = event.getChanges();
            int index$iv$iv2 = 0;
            int size2 = changes2.size();
            while (true) {
                if (index$iv$iv2 >= size2) {
                    break;
                }
                Object item$iv$iv2 = changes2.get(index$iv$iv2);
                IndirectPointerInputChange it2 = (IndirectPointerInputChange) item$iv$iv2;
                if (!ClickableKt.changedToUp(it2)) {
                    z = false;
                    break;
                }
                index$iv$iv2++;
            }
            if (z) {
                handleUpEvent(event.getChanges().get(0));
                return;
            } else {
                handleNonUpEventIfNeeded(event);
                return;
            }
        }
        if (pass == PointerEventPass.Final) {
            checkForCancellation(event);
        }
    }

    private final void handleDownEvent(PointerInputChange down) {
        down.consume();
        this.downEvent = down;
        if (getEnabled()) {
            if (ComposeFoundationFlags.isDelayPressesUsingGestureConsumptionEnabled) {
                handlePressInteractionStart(down);
            } else {
                m251handlePressInteractionStart3MmeM6k(down.getPosition(), false);
            }
        }
    }

    private final void handleDownEvent(IndirectPointerInputChange down) {
        down.consume();
        this.indirectDownEvent = down;
        if (getEnabled()) {
            if (ComposeFoundationFlags.isDelayPressesUsingGestureConsumptionEnabled) {
                handlePressInteractionStart(down);
            } else {
                m251handlePressInteractionStart3MmeM6k(down.getPosition(), true);
            }
        }
    }

    private final void handleUpEvent(PointerInputChange up) {
        up.consume();
        if (getEnabled()) {
            PointerInputChange pointerInputChange = this.downEvent;
            Intrinsics.checkNotNull(pointerInputChange);
            m250handlePressInteractionRelease3MmeM6k(pointerInputChange.getPosition(), false);
            getOnClick().invoke();
        }
        this.downEvent = null;
    }

    private final void handleUpEvent(IndirectPointerInputChange up) {
        up.consume();
        if (getEnabled()) {
            IndirectPointerInputChange indirectPointerInputChange = this.indirectDownEvent;
            Intrinsics.checkNotNull(indirectPointerInputChange);
            m250handlePressInteractionRelease3MmeM6k(indirectPointerInputChange.getPosition(), true);
            getOnClick().invoke();
        }
        this.indirectDownEvent = null;
    }

    /* JADX INFO: renamed from: handleNonUpEventIfNeeded-O0kMr_c, reason: not valid java name */
    private final void m335handleNonUpEventIfNeededO0kMr_c(PointerEvent pointerEvent, long bounds) {
        boolean z;
        long touchPadding = m248getExtendedTouchPaddinghWWAJMo(bounds);
        List<PointerInputChange> changes = pointerEvent.getChanges();
        int index$iv$iv = 0;
        int size = changes.size();
        while (true) {
            if (index$iv$iv < size) {
                Object item$iv$iv = changes.get(index$iv$iv);
                PointerInputChange it = (PointerInputChange) item$iv$iv;
                z = true;
                if (it.isConsumed() || PointerEventKt.m6586isOutOfBoundsjwHxaWs(it, bounds, touchPadding)) {
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
                IndirectPointerInputChange it = (IndirectPointerInputChange) item$iv$iv;
                long position = it.getPosition();
                IndirectPointerInputChange indirectPointerInputChange = this.indirectDownEvent;
                Intrinsics.checkNotNull(indirectPointerInputChange);
                float touchSlop2 = touchSlop;
                List<IndirectPointerInputChange> list = changes;
                long distanceFromPress = Offset.m5072minusMKHz9U(position, indirectPointerInputChange.getPosition());
                boolean isOutOfBounds = Math.abs(Offset.m5066getDistanceimpl(distanceFromPress)) > touchSlop2;
                boolean isOutOfBounds2 = it.getIsConsumed() || isOutOfBounds;
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

    private final void checkForCancellation(PointerEvent pointerEvent) {
        boolean z;
        if (this.downEvent != null) {
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
        if (this.indirectDownEvent != null) {
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
        } else {
            this.downEvent = null;
        }
        handlePressInteractionCancel(indirectPointer);
    }

    /* JADX INFO: renamed from: update-O2vRcR0, reason: not valid java name */
    public final void m336updateO2vRcR0(MutableInteractionSource interactionSource, IndicationNodeFactory indicationNodeFactory, boolean useLocalIndication, boolean enabled, String onClickLabel, Role role, Function0<Unit> onClick) {
        m257updateCommonO2vRcR0(interactionSource, indicationNodeFactory, useLocalIndication, enabled, onClickLabel, role, onClick);
    }

    @Override // androidx.compose.foundation.AbstractClickableNode
    /* JADX INFO: renamed from: onClickKeyDownEvent-ZmokQxo */
    protected final boolean mo252onClickKeyDownEventZmokQxo(KeyEvent event) {
        return false;
    }

    @Override // androidx.compose.foundation.AbstractClickableNode
    /* JADX INFO: renamed from: onClickKeyUpEvent-ZmokQxo */
    protected final boolean mo253onClickKeyUpEventZmokQxo(KeyEvent event) {
        getOnClick().invoke();
        return true;
    }
}
