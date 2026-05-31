package androidx.compose.foundation.layout;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.ParentDataModifierNode;
import androidx.compose.ui.unit.Density;
import kotlin.Metadata;

/* JADX INFO: compiled from: FlexBox.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0001\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0016\u0010\n\u001a\u00020\u000b*\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000bH\u0016R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\u0006¨\u0006\u000e"}, d2 = {"Landroidx/compose/foundation/layout/FlexBoxChildDataNode;", "Landroidx/compose/ui/node/ParentDataModifierNode;", "Landroidx/compose/ui/Modifier$Node;", "config", "Landroidx/compose/foundation/layout/FlexConfig;", "<init>", "(Landroidx/compose/foundation/layout/FlexConfig;)V", "getConfig", "()Landroidx/compose/foundation/layout/FlexConfig;", "setConfig", "modifyParentData", "", "Landroidx/compose/ui/unit/Density;", "parentData", "foundation-layout"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class FlexBoxChildDataNode extends Modifier.Node implements ParentDataModifierNode {
    public static final int $stable = 8;
    private FlexConfig config;

    public FlexBoxChildDataNode(FlexConfig config) {
        this.config = config;
    }

    public final FlexConfig getConfig() {
        return this.config;
    }

    public final void setConfig(FlexConfig flexConfig) {
        this.config = flexConfig;
    }

    @Override // androidx.compose.ui.node.ParentDataModifierNode
    public Object modifyParentData(Density $this$modifyParentData, Object parentData) {
        return this;
    }
}
