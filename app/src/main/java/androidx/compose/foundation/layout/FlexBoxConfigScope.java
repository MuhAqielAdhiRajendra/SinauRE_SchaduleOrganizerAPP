package androidx.compose.foundation.layout;

import androidx.compose.ui.layout.AlignmentLine;
import androidx.compose.ui.layout.Measured;
import androidx.compose.ui.unit.Density;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: FlexBox.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\bw\u0018\u00002\u00020\u0001J\u0017\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH&¢\u0006\u0004\b\n\u0010\u000bJ\u0017\u0010\f\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\rH&¢\u0006\u0004\b\u000e\u0010\u000bJ\u0017\u0010\u000f\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0010H&¢\u0006\u0004\b\u0011\u0010\u000bJ\u0017\u0010\u0012\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0013H&¢\u0006\u0004\b\u0014\u0010\u000bJ\u0010\u0010\u0012\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\u0016H&J\u001c\u0010\u0012\u001a\u00020\u00072\u0012\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u001a0\u0018H&J\u0017\u0010\u001b\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u001cH&¢\u0006\u0004\b\u001d\u0010\u000bJ\u0017\u0010\u001e\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u001fH&¢\u0006\u0004\b \u0010!J\u0017\u0010\"\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u001fH&¢\u0006\u0004\b#\u0010!J\u0017\u0010$\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u001fH&¢\u0006\u0004\b%\u0010!J\u001f\u0010$\u001a\u00020\u00072\u0006\u0010&\u001a\u00020\u001f2\u0006\u0010'\u001a\u00020\u001fH&¢\u0006\u0004\b(\u0010)R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005\u0082\u0001\u0001*ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006+À\u0006\u0001"}, d2 = {"Landroidx/compose/foundation/layout/FlexBoxConfigScope;", "Landroidx/compose/ui/unit/Density;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "getConstraints-msEJaDk", "()J", "direction", "", "value", "Landroidx/compose/foundation/layout/FlexDirection;", "direction-d5Yd7B0", "(I)V", "wrap", "Landroidx/compose/foundation/layout/FlexWrap;", "wrap-CLQ35Ag", "justifyContent", "Landroidx/compose/foundation/layout/FlexJustifyContent;", "justifyContent-q3qUS_E", "alignItems", "Landroidx/compose/foundation/layout/FlexAlignItems;", "alignItems-yvIbNKY", "alignmentLine", "Landroidx/compose/ui/layout/AlignmentLine;", "alignmentLineBlock", "Lkotlin/Function1;", "Landroidx/compose/ui/layout/Measured;", "", "alignContent", "Landroidx/compose/foundation/layout/FlexAlignContent;", "alignContent-RVFKNBI", "rowGap", "Landroidx/compose/ui/unit/Dp;", "rowGap-0680j_4", "(F)V", "columnGap", "columnGap-0680j_4", "gap", "gap-0680j_4", "row", "column", "gap-YgX7TsA", "(FF)V", "Landroidx/compose/foundation/layout/ResolvedFlexBoxConfig;", "foundation-layout"}, k = 1, mv = {2, 1, 0}, xi = 48)
public interface FlexBoxConfigScope extends Density {
    /* JADX INFO: renamed from: alignContent-RVFKNBI, reason: not valid java name */
    void mo835alignContentRVFKNBI(int value);

    void alignItems(AlignmentLine alignmentLine);

    void alignItems(Function1<? super Measured, Integer> alignmentLineBlock);

    /* JADX INFO: renamed from: alignItems-yvIbNKY, reason: not valid java name */
    void mo836alignItemsyvIbNKY(int value);

    /* JADX INFO: renamed from: columnGap-0680j_4, reason: not valid java name */
    void mo837columnGap0680j_4(float value);

    /* JADX INFO: renamed from: direction-d5Yd7B0, reason: not valid java name */
    void mo838directiond5Yd7B0(int value);

    /* JADX INFO: renamed from: gap-0680j_4, reason: not valid java name */
    void mo839gap0680j_4(float value);

    /* JADX INFO: renamed from: gap-YgX7TsA, reason: not valid java name */
    void mo840gapYgX7TsA(float row, float column);

    /* JADX INFO: renamed from: getConstraints-msEJaDk, reason: not valid java name */
    long mo841getConstraintsmsEJaDk();

    /* JADX INFO: renamed from: justifyContent-q3qUS_E, reason: not valid java name */
    void mo842justifyContentq3qUS_E(int value);

    /* JADX INFO: renamed from: rowGap-0680j_4, reason: not valid java name */
    void mo843rowGap0680j_4(float value);

    /* JADX INFO: renamed from: wrap-CLQ35Ag, reason: not valid java name */
    void mo844wrapCLQ35Ag(int value);
}
