package androidx.compose.foundation.layout;

import androidx.compose.ui.layout.AlignmentLine;
import androidx.compose.ui.layout.Measured;
import androidx.compose.ui.unit.Density;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: FlexBox.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bw\u0018\u00002\u00020\u0001J\u0017\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH&¢\u0006\u0004\b\u0010\u0010\u0011J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u0013H&J\u001c\u0010\f\u001a\u00020\r2\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00030\u0015H&J\u0010\u0010\u0017\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0003H&J\u0012\u0010\u0018\u001a\u00020\r2\b\b\u0001\u0010\u000e\u001a\u00020\u0019H&J\u0012\u0010\u001a\u001a\u00020\r2\b\b\u0001\u0010\u000e\u001a\u00020\u0019H&J\u0017\u0010\u001b\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u001cH&¢\u0006\u0004\b\u001d\u0010\u001eJ\u0017\u0010\u001b\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u001fH&¢\u0006\u0004\b \u0010!J\u0012\u0010\u001b\u001a\u00020\r2\b\b\u0001\u0010\u000e\u001a\u00020\u0019H&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\u0005R\u0012\u0010\b\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\u0005R\u0012\u0010\n\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\u0005\u0082\u0001\u0001\"ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006#À\u0006\u0001"}, d2 = {"Landroidx/compose/foundation/layout/FlexConfigScope;", "Landroidx/compose/ui/unit/Density;", "flexBoxMainAxisMax", "", "getFlexBoxMainAxisMax", "()I", "flexBoxMainAxisMin", "getFlexBoxMainAxisMin", "flexBoxCrossAxisMax", "getFlexBoxCrossAxisMax", "flexBoxCrossAxisMin", "getFlexBoxCrossAxisMin", "alignSelf", "", "value", "Landroidx/compose/foundation/layout/FlexAlignSelf;", "alignSelf-aKVMlHY", "(I)V", "alignmentLine", "Landroidx/compose/ui/layout/AlignmentLine;", "alignmentLineBlock", "Lkotlin/Function1;", "Landroidx/compose/ui/layout/Measured;", "order", "grow", "", "shrink", "basis", "Landroidx/compose/foundation/layout/FlexBasis;", "basis-MFoeH6Y", "(J)V", "Landroidx/compose/ui/unit/Dp;", "basis-0680j_4", "(F)V", "Landroidx/compose/foundation/layout/ResolvedFlexItemInfo;", "foundation-layout"}, k = 1, mv = {2, 1, 0}, xi = 48)
public interface FlexConfigScope extends Density {
    void alignSelf(AlignmentLine alignmentLine);

    void alignSelf(Function1<? super Measured, Integer> alignmentLineBlock);

    /* JADX INFO: renamed from: alignSelf-aKVMlHY, reason: not valid java name */
    void mo854alignSelfaKVMlHY(int value);

    void basis(float value);

    /* JADX INFO: renamed from: basis-0680j_4, reason: not valid java name */
    void mo855basis0680j_4(float value);

    /* JADX INFO: renamed from: basis-MFoeH6Y, reason: not valid java name */
    void mo856basisMFoeH6Y(long value);

    int getFlexBoxCrossAxisMax();

    int getFlexBoxCrossAxisMin();

    int getFlexBoxMainAxisMax();

    int getFlexBoxMainAxisMin();

    void grow(float value);

    void order(int value);

    void shrink(float value);
}
