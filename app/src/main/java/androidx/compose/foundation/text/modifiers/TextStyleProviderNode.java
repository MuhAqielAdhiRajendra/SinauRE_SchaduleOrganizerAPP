package androidx.compose.foundation.text.modifiers;

import androidx.compose.ui.node.TraversableNode;
import androidx.compose.ui.text.TextStyle;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Metadata;

/* JADX INFO: compiled from: TextStyleProviderNode.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b`\u0018\u00002\u00020\u0001J\u001f\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0003H&¢\u0006\u0004\b\u0007\u0010\bø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\tÀ\u0006\u0001"}, d2 = {"Landroidx/compose/foundation/text/modifiers/TextStyleProviderNode;", "Landroidx/compose/ui/node/TraversableNode;", "computeInheritedTextStyle", "Landroidx/compose/ui/text/TextStyle;", TypedValues.CycleType.S_WAVE_PHASE, "Landroidx/compose/foundation/text/modifiers/StylePhase;", "fallback", "computeInheritedTextStyle-B-LjeIk", "(ILandroidx/compose/ui/text/TextStyle;)Landroidx/compose/ui/text/TextStyle;", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
public interface TextStyleProviderNode extends TraversableNode {
    /* JADX INFO: renamed from: computeInheritedTextStyle-B-LjeIk */
    TextStyle mo1470computeInheritedTextStyleBLjeIk(int phase, TextStyle fallback);
}
