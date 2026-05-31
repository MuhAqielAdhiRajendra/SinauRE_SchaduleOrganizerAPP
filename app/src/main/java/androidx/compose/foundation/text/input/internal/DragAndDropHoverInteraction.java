package androidx.compose.foundation.text.input.internal;

import androidx.compose.foundation.interaction.Interaction;
import kotlin.Metadata;

/* JADX INFO: compiled from: DragAndDropHoverInteraction.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b`\u0018\u00002\u00020\u0001:\u0002\u0002\u0003ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0004À\u0006\u0001"}, d2 = {"Landroidx/compose/foundation/text/input/internal/DragAndDropHoverInteraction;", "Landroidx/compose/foundation/interaction/Interaction;", "Enter", "Exit", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
public interface DragAndDropHoverInteraction extends Interaction {

    /* JADX INFO: compiled from: DragAndDropHoverInteraction.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Landroidx/compose/foundation/text/input/internal/DragAndDropHoverInteraction$Enter;", "Landroidx/compose/foundation/text/input/internal/DragAndDropHoverInteraction;", "<init>", "()V", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Enter implements DragAndDropHoverInteraction {
        public static final int $stable = 0;
    }

    /* JADX INFO: compiled from: DragAndDropHoverInteraction.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Landroidx/compose/foundation/text/input/internal/DragAndDropHoverInteraction$Exit;", "Landroidx/compose/foundation/text/input/internal/DragAndDropHoverInteraction;", "enter", "Landroidx/compose/foundation/text/input/internal/DragAndDropHoverInteraction$Enter;", "<init>", "(Landroidx/compose/foundation/text/input/internal/DragAndDropHoverInteraction$Enter;)V", "getEnter", "()Landroidx/compose/foundation/text/input/internal/DragAndDropHoverInteraction$Enter;", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Exit implements DragAndDropHoverInteraction {
        public static final int $stable = 0;
        private final Enter enter;

        public Exit(Enter enter) {
            this.enter = enter;
        }

        public final Enter getEnter() {
            return this.enter;
        }
    }
}
