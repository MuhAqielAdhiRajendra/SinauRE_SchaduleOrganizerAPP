package androidx.compose.runtime.composer.linkbuffer.changelist;

import androidx.compose.runtime.Changes;
import androidx.compose.runtime.ComposerKt;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;

/* JADX INFO: compiled from: ChangeList.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0000¨\u0006\u0003"}, d2 = {"asLinkBufferChangeList", "Landroidx/compose/runtime/composer/linkbuffer/changelist/ChangeList;", "Landroidx/compose/runtime/Changes;", "runtime"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class ChangeListKt {
    public static final ChangeList asLinkBufferChangeList(Changes $this$asLinkBufferChangeList) {
        ChangeList changeList = $this$asLinkBufferChangeList instanceof ChangeList ? (ChangeList) $this$asLinkBufferChangeList : null;
        if (changeList != null) {
            return changeList;
        }
        ComposerKt.composeRuntimeError("Inconsistent composition");
        throw new KotlinNothingValueException();
    }
}
