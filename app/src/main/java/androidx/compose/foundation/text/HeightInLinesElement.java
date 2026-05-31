package androidx.compose.foundation.text;

import androidx.compose.ui.node.ModifierNodeElement;
import androidx.compose.ui.platform.InspectorInfo;
import androidx.compose.ui.text.TextStyle;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: HeightInLinesModifier.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\b\u0010\n\u001a\u00020\u0002H\u0016J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0002H\u0016J\b\u0010\u000e\u001a\u00020\u0006H\u0016J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0096\u0002J\f\u0010\u0013\u001a\u00020\f*\u00020\u0014H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Landroidx/compose/foundation/text/HeightInLinesElement;", "Landroidx/compose/ui/node/ModifierNodeElement;", "Landroidx/compose/foundation/text/HeightInLinesNode;", "textStyle", "Landroidx/compose/ui/text/TextStyle;", "minLines", "", "maxLines", "<init>", "(Landroidx/compose/ui/text/TextStyle;II)V", "create", "update", "", "node", "hashCode", "equals", "", "other", "", "inspectableProperties", "Landroidx/compose/ui/platform/InspectorInfo;", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
final class HeightInLinesElement extends ModifierNodeElement<HeightInLinesNode> {
    private final int maxLines;
    private final int minLines;
    private final TextStyle textStyle;

    public HeightInLinesElement(TextStyle textStyle, int minLines, int maxLines) {
        this.textStyle = textStyle;
        this.minLines = minLines;
        this.maxLines = maxLines;
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    /* JADX INFO: renamed from: create */
    public HeightInLinesNode getNode() {
        return new HeightInLinesNode(this.textStyle, this.minLines, this.maxLines);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public void update(HeightInLinesNode node) {
        node.update(this.textStyle, this.minLines, this.maxLines);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public int hashCode() {
        int result = this.textStyle.hashCode();
        return (((result * 31) + this.minLines) * 31) + this.maxLines;
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof HeightInLinesElement) && Intrinsics.areEqual(this.textStyle, ((HeightInLinesElement) other).textStyle) && this.minLines == ((HeightInLinesElement) other).minLines && this.maxLines == ((HeightInLinesElement) other).maxLines;
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public void inspectableProperties(InspectorInfo $this$inspectableProperties) {
        $this$inspectableProperties.setName("heightInLines");
        $this$inspectableProperties.getProperties().set("minLines", Integer.valueOf(this.minLines));
        $this$inspectableProperties.getProperties().set("maxLines", Integer.valueOf(this.maxLines));
        $this$inspectableProperties.getProperties().set("textStyle", this.textStyle);
    }
}
