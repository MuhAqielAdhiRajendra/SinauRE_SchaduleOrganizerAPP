package androidx.compose.foundation.style;

import androidx.compose.ui.node.ModifierNodeElement;
import androidx.compose.ui.platform.InspectorInfo;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: StyleModifier.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\b\u0081\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0019\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\b\u0010\r\u001a\u00020\u0002H\u0016J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0002H\u0016J\f\u0010\u0011\u001a\u00020\u000f*\u00020\u0012H\u0016J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0096\u0002J\b\u0010\u0017\u001a\u00020\u0018H\u0016J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0006HÆ\u0003J\u001f\u0010\u001b\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\n\u0010\u001c\u001a\u00020\u001dHÖ\u0081\u0004R\u0013\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u001e"}, d2 = {"Landroidx/compose/foundation/style/StyleElement;", "Landroidx/compose/ui/node/ModifierNodeElement;", "Landroidx/compose/foundation/style/StyleOuterNode;", "styleState", "Landroidx/compose/foundation/style/StyleState;", "style", "Landroidx/compose/foundation/style/Style;", "<init>", "(Landroidx/compose/foundation/style/StyleState;Landroidx/compose/foundation/style/Style;)V", "getStyleState", "()Landroidx/compose/foundation/style/StyleState;", "getStyle", "()Landroidx/compose/foundation/style/Style;", "create", "update", "", "node", "inspectableProperties", "Landroidx/compose/ui/platform/InspectorInfo;", "equals", "", "other", "", "hashCode", "", "component1", "component2", "copy", "toString", "", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final /* data */ class StyleElement extends ModifierNodeElement<StyleOuterNode> {
    public static final int $stable = 0;
    private final Style style;
    private final StyleState styleState;

    public static /* synthetic */ StyleElement copy$default(StyleElement styleElement, StyleState styleState, Style style, int i, Object obj) {
        if ((i & 1) != 0) {
            styleState = styleElement.styleState;
        }
        if ((i & 2) != 0) {
            style = styleElement.style;
        }
        return styleElement.copy(styleState, style);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final StyleState getStyleState() {
        return this.styleState;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final Style getStyle() {
        return this.style;
    }

    public final StyleElement copy(StyleState styleState, Style style) {
        return new StyleElement(styleState, style);
    }

    public String toString() {
        return "StyleElement(styleState=" + this.styleState + ", style=" + this.style + ')';
    }

    public StyleElement(StyleState styleState, Style style) {
        this.styleState = styleState;
        this.style = style;
    }

    public final Style getStyle() {
        return this.style;
    }

    public final StyleState getStyleState() {
        return this.styleState;
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    /* JADX INFO: renamed from: create */
    public StyleOuterNode getNode() {
        return new StyleOuterNode(this.styleState, this.style);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public void update(StyleOuterNode node) {
        node.setStyle$foundation(this.style);
        MutableStyleState mutableStyleState = this.styleState;
        if (mutableStyleState == null) {
            mutableStyleState = new MutableStyleState(null);
        }
        node.setState$foundation(mutableStyleState);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public void inspectableProperties(InspectorInfo $this$inspectableProperties) {
        $this$inspectableProperties.setName("style");
        $this$inspectableProperties.getProperties().set("style", this.style);
        $this$inspectableProperties.getProperties().set("styleState", this.styleState);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public boolean equals(Object other) {
        return this == other || ((other instanceof StyleElement) && Intrinsics.areEqual(((StyleElement) other).style, this.style) && Intrinsics.areEqual(((StyleElement) other).styleState, this.styleState));
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public int hashCode() {
        return this.style.hashCode();
    }
}
