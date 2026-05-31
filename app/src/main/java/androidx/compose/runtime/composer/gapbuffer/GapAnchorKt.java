package androidx.compose.runtime.composer.gapbuffer;

import androidx.compose.runtime.Anchor;
import androidx.compose.runtime.ComposerKt;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;

/* JADX INFO: compiled from: GapAnchor.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0000¨\u0006\u0003"}, d2 = {"asGapAnchor", "Landroidx/compose/runtime/composer/gapbuffer/GapAnchor;", "Landroidx/compose/runtime/Anchor;", "runtime"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class GapAnchorKt {
    public static final GapAnchor asGapAnchor(Anchor $this$asGapAnchor) {
        GapAnchor gapAnchor = $this$asGapAnchor instanceof GapAnchor ? (GapAnchor) $this$asGapAnchor : null;
        if (gapAnchor != null) {
            return gapAnchor;
        }
        ComposerKt.composeRuntimeError("Inconsistent composition");
        throw new KotlinNothingValueException();
    }
}
