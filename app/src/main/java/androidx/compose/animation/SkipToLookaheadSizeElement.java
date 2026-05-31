package androidx.compose.animation;

import androidx.compose.ui.node.ModifierNodeElement;
import androidx.compose.ui.platform.InspectorInfo;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SkipToLookaheadSizeNode.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B#\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0004\b\b\u0010\tJ\b\u0010\r\u001a\u00020\u0002H\u0016J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0002H\u0016J\f\u0010\u0011\u001a\u00020\u000f*\u00020\u0012H\u0016J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\u0013\u0010\u0015\u001a\u00020\u00072\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0096\u0002R\u0013\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\f¨\u0006\u0018"}, d2 = {"Landroidx/compose/animation/SkipToLookaheadSizeElement;", "Landroidx/compose/ui/node/ModifierNodeElement;", "Landroidx/compose/animation/SkipToLookaheadSizeNode;", "scaleToBounds", "Landroidx/compose/animation/ScaleToBoundsImpl;", "isEnabled", "Lkotlin/Function0;", "", "<init>", "(Landroidx/compose/animation/ScaleToBoundsImpl;Lkotlin/jvm/functions/Function0;)V", "getScaleToBounds", "()Landroidx/compose/animation/ScaleToBoundsImpl;", "()Lkotlin/jvm/functions/Function0;", "create", "update", "", "node", "inspectableProperties", "Landroidx/compose/ui/platform/InspectorInfo;", "hashCode", "", "equals", "other", "", "animation"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class SkipToLookaheadSizeElement extends ModifierNodeElement<SkipToLookaheadSizeNode> {
    public static final int $stable = 0;
    private final Function0<Boolean> isEnabled;
    private final ScaleToBoundsImpl scaleToBounds;

    public SkipToLookaheadSizeElement() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    public SkipToLookaheadSizeElement(ScaleToBoundsImpl scaleToBounds, Function0<Boolean> function0) {
        this.scaleToBounds = scaleToBounds;
        this.isEnabled = function0;
    }

    public /* synthetic */ SkipToLookaheadSizeElement(ScaleToBoundsImpl scaleToBoundsImpl, Function0 function0, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : scaleToBoundsImpl, (i & 2) != 0 ? SkipToLookaheadSizeNodeKt.DefaultEnabled : function0);
    }

    public final ScaleToBoundsImpl getScaleToBounds() {
        return this.scaleToBounds;
    }

    public final Function0<Boolean> isEnabled() {
        return this.isEnabled;
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    /* JADX INFO: renamed from: create */
    public SkipToLookaheadSizeNode getNode() {
        return new SkipToLookaheadSizeNode(this.scaleToBounds, this.isEnabled);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public void update(SkipToLookaheadSizeNode node) {
        node.setScaleToBounds(this.scaleToBounds);
        node.setEnabled(this.isEnabled);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public void inspectableProperties(InspectorInfo $this$inspectableProperties) {
        $this$inspectableProperties.setName("skipToLookahead");
        $this$inspectableProperties.getProperties().set("scaleToBounds", this.scaleToBounds);
        $this$inspectableProperties.getProperties().set("isEnabled", this.isEnabled);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public int hashCode() {
        int iHashCode = this.isEnabled.hashCode() * 31;
        ScaleToBoundsImpl scaleToBoundsImpl = this.scaleToBounds;
        return iHashCode + (scaleToBoundsImpl != null ? scaleToBoundsImpl.hashCode() : 0);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public boolean equals(Object other) {
        return (other instanceof SkipToLookaheadSizeElement) && ((SkipToLookaheadSizeElement) other).isEnabled == this.isEnabled && Intrinsics.areEqual(((SkipToLookaheadSizeElement) other).scaleToBounds, this.scaleToBounds);
    }
}
