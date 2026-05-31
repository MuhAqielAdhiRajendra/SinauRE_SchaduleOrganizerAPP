package androidx.compose.foundation.style;

import androidx.compose.foundation.interaction.FocusInteraction;
import androidx.compose.foundation.interaction.HoverInteraction;
import androidx.compose.foundation.interaction.Interaction;
import androidx.compose.foundation.interaction.PressInteraction;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.FlowCollector;

/* JADX INFO: compiled from: StyleState.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
final class MutableStyleState$processInteractions$2<T> implements FlowCollector {
    final /* synthetic */ InteractionSet<FocusInteraction.Focus> $focusedInteractions;
    final /* synthetic */ InteractionSet<HoverInteraction.Enter> $hoveredInteractions;
    final /* synthetic */ InteractionSet<PressInteraction.Press> $pressedInteractions;
    final /* synthetic */ MutableStyleState this$0;

    MutableStyleState$processInteractions$2(InteractionSet<PressInteraction.Press> interactionSet, MutableStyleState mutableStyleState, InteractionSet<HoverInteraction.Enter> interactionSet2, InteractionSet<FocusInteraction.Focus> interactionSet3) {
        this.$pressedInteractions = interactionSet;
        this.this$0 = mutableStyleState;
        this.$hoveredInteractions = interactionSet2;
        this.$focusedInteractions = interactionSet3;
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x010a  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object emit(androidx.compose.foundation.interaction.Interaction r11, kotlin.coroutines.Continuation<? super kotlin.Unit> r12) {
        /*
            Method dump skipped, instruction units count: 310
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.style.MutableStyleState$processInteractions$2.emit(androidx.compose.foundation.interaction.Interaction, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // kotlinx.coroutines.flow.FlowCollector
    public /* bridge */ /* synthetic */ Object emit(Object value, Continuation $completion) {
        return emit((Interaction) value, (Continuation<? super Unit>) $completion);
    }
}
