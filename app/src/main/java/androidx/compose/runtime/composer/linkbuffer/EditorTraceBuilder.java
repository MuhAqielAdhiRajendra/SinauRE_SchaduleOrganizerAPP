package androidx.compose.runtime.composer.linkbuffer;

import androidx.compose.runtime.Anchor;
import androidx.compose.runtime.composer.GroupSourceInformation;
import androidx.compose.runtime.tooling.ComposeStackTraceBuilder;
import kotlin.Metadata;

/* JADX INFO: compiled from: SlotTableEditor.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0001\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\tH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Landroidx/compose/runtime/composer/linkbuffer/EditorTraceBuilder;", "Landroidx/compose/runtime/tooling/ComposeStackTraceBuilder;", "editor", "Landroidx/compose/runtime/composer/linkbuffer/SlotTableEditor;", "<init>", "(Landroidx/compose/runtime/composer/linkbuffer/SlotTableEditor;)V", "sourceInformationOf", "Landroidx/compose/runtime/composer/GroupSourceInformation;", "anchor", "Landroidx/compose/runtime/Anchor;", "groupKeyOf", "", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class EditorTraceBuilder extends ComposeStackTraceBuilder {
    public static final int $stable = 8;
    private final SlotTableEditor editor;

    public EditorTraceBuilder(SlotTableEditor editor) {
        this.editor = editor;
    }

    @Override // androidx.compose.runtime.tooling.ComposeStackTraceBuilder
    public GroupSourceInformation sourceInformationOf(Anchor anchor) {
        return this.editor.getTable().getAddressSpace().sourceInformationOf(LinkAnchorKt.asLinkAnchor(anchor).getAddress());
    }

    @Override // androidx.compose.runtime.tooling.ComposeStackTraceBuilder
    public int groupKeyOf(Anchor anchor) {
        return this.editor.groupKey(LinkAnchorKt.asLinkAnchor(anchor).getAddress());
    }
}
