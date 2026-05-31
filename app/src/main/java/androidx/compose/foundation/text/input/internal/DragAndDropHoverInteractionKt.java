package androidx.compose.foundation.text.input.internal;

import androidx.compose.foundation.interaction.InteractionSource;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: DragAndDropHoverInteraction.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0017\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\u0001¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"collectIsDragAndDropHoveredAsState", "Landroidx/compose/runtime/State;", "", "Landroidx/compose/foundation/interaction/InteractionSource;", "(Landroidx/compose/foundation/interaction/InteractionSource;Landroidx/compose/runtime/Composer;I)Landroidx/compose/runtime/State;", "foundation"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class DragAndDropHoverInteractionKt {
    public static final State<Boolean> collectIsDragAndDropHoveredAsState(InteractionSource $this$collectIsDragAndDropHoveredAsState, Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, 813968744, "C(collectIsDragAndDropHoveredAsState)53@1917L34,54@1977L444,54@1956L465:DragAndDropHoverInteraction.kt#yqt8nc");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(813968744, $changed, -1, "androidx.compose.foundation.text.input.internal.collectIsDragAndDropHoveredAsState (DragAndDropHoverInteraction.kt:52)");
        }
        ComposerKt.sourceInformationMarkerStart($composer, 539918250, "CC(remember):DragAndDropHoverInteraction.kt#9igjgp");
        Object it$iv = $composer.rememberedValue();
        if (it$iv == Composer.INSTANCE.getEmpty()) {
            Object value$iv = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
            $composer.updateRememberedValue(value$iv);
            it$iv = value$iv;
        }
        MutableState isHovered = (MutableState) it$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerStart($composer, 539920580, "CC(remember):DragAndDropHoverInteraction.kt#9igjgp");
        boolean invalid$iv = ((($changed & 14) ^ 6) > 4 && $composer.changed($this$collectIsDragAndDropHoveredAsState)) || ($changed & 6) == 4;
        Object it$iv2 = $composer.rememberedValue();
        if (invalid$iv || it$iv2 == Composer.INSTANCE.getEmpty()) {
            Object value$iv2 = (Function2) new DragAndDropHoverInteractionKt$collectIsDragAndDropHoveredAsState$1$1($this$collectIsDragAndDropHoveredAsState, isHovered, null);
            $composer.updateRememberedValue(value$iv2);
            it$iv2 = value$iv2;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        EffectsKt.LaunchedEffect($this$collectIsDragAndDropHoveredAsState, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) it$iv2, $composer, $changed & 14);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return isHovered;
    }
}
