package androidx.compose.foundation.text.selection;

import androidx.collection.LongObjectMap;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: SelectionRegistrar.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0016\u0010\u0000\u001a\u00020\u0001*\u0004\u0018\u00010\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0000\"\u001c\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0006X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"hasSelection", "", "Landroidx/compose/foundation/text/selection/SelectionRegistrar;", "selectableId", "", "LocalSelectionRegistrar", "Landroidx/compose/runtime/ProvidableCompositionLocal;", "getLocalSelectionRegistrar", "()Landroidx/compose/runtime/ProvidableCompositionLocal;", "foundation"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class SelectionRegistrarKt {
    private static final ProvidableCompositionLocal<SelectionRegistrar> LocalSelectionRegistrar = CompositionLocalKt.compositionLocalOf$default(null, new Function0() { // from class: androidx.compose.foundation.text.selection.SelectionRegistrarKt$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return SelectionRegistrarKt.LocalSelectionRegistrar$lambda$0();
        }
    }, 1, null);

    public static final boolean hasSelection(SelectionRegistrar $this$hasSelection, long selectableId) {
        LongObjectMap<Selection> subselections;
        if ($this$hasSelection == null || (subselections = $this$hasSelection.getSubselections()) == null) {
            return false;
        }
        return subselections.containsKey(selectableId);
    }

    static final SelectionRegistrar LocalSelectionRegistrar$lambda$0() {
        return null;
    }

    public static final ProvidableCompositionLocal<SelectionRegistrar> getLocalSelectionRegistrar() {
        return LocalSelectionRegistrar;
    }
}
