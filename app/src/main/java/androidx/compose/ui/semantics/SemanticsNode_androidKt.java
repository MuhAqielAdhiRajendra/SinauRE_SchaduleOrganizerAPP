package androidx.compose.ui.semantics;

import kotlin.Metadata;

/* JADX INFO: compiled from: SemanticsNode.android.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\"\u0018\u0010\u0000\u001a\u00020\u0001*\u00020\u00028@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0000\u0010\u0003¨\u0006\u0004"}, d2 = {"isAccessibilityIgnoredLink", "", "Landroidx/compose/ui/semantics/SemanticsNode;", "(Landroidx/compose/ui/semantics/SemanticsNode;)Z", "ui"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class SemanticsNode_androidKt {
    public static final boolean isAccessibilityIgnoredLink(SemanticsNode $this$isAccessibilityIgnoredLink) {
        return $this$isAccessibilityIgnoredLink.getConfig().contains(SemanticsProperties.INSTANCE.getLinkTestMarker());
    }
}
