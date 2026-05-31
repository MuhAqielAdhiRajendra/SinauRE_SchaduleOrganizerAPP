package androidx.compose.foundation.text.selection;

import kotlin.Metadata;

/* JADX INFO: compiled from: SelectionRegistrarImpl.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a/\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0003H\u0000¢\u0006\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"inARow", "", "boxATopLeft", "Landroidx/compose/ui/geometry/Offset;", "boxABottomRight", "boxBTopLeft", "boxBBottomRight", "inARow-zwwh4xc", "(JJJJ)Z", "foundation"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class SelectionRegistrarImplKt {
    /* JADX INFO: renamed from: inARow-zwwh4xc, reason: not valid java name */
    public static final boolean m2088inARowzwwh4xc(long boxATopLeft, long boxABottomRight, long boxBTopLeft, long boxBBottomRight) {
        int bits$iv$iv$iv = (int) (boxABottomRight & 4294967295L);
        int bits$iv$iv$iv2 = (int) (boxATopLeft & 4294967295L);
        float heightA = Float.intBitsToFloat(bits$iv$iv$iv) - Float.intBitsToFloat(bits$iv$iv$iv2);
        int bits$iv$iv$iv3 = (int) (boxABottomRight >> 32);
        int bits$iv$iv$iv4 = (int) (boxATopLeft >> 32);
        float widthA = Float.intBitsToFloat(bits$iv$iv$iv3) - Float.intBitsToFloat(bits$iv$iv$iv4);
        int bits$iv$iv$iv5 = (int) (boxBBottomRight & 4294967295L);
        int bits$iv$iv$iv6 = (int) (boxBTopLeft & 4294967295L);
        float heightB = Float.intBitsToFloat(bits$iv$iv$iv5) - Float.intBitsToFloat(bits$iv$iv$iv6);
        int bits$iv$iv$iv7 = (int) (boxBBottomRight >> 32);
        int bits$iv$iv$iv8 = (int) (boxBTopLeft >> 32);
        float widthB = Float.intBitsToFloat(bits$iv$iv$iv7) - Float.intBitsToFloat(bits$iv$iv$iv8);
        int bits$iv$iv$iv9 = (int) (boxATopLeft & 4294967295L);
        int bits$iv$iv$iv10 = (int) (boxBTopLeft & 4294967295L);
        float vertInterTop = Math.max(Float.intBitsToFloat(bits$iv$iv$iv9), Float.intBitsToFloat(bits$iv$iv$iv10));
        int bits$iv$iv$iv11 = (int) (boxABottomRight & 4294967295L);
        int bits$iv$iv$iv12 = (int) (4294967295L & boxBBottomRight);
        float vertInterBottom = Math.min(Float.intBitsToFloat(bits$iv$iv$iv11), Float.intBitsToFloat(bits$iv$iv$iv12));
        float vertIntersection = Math.max(0.0f, vertInterBottom - vertInterTop);
        int bits$iv$iv$iv13 = (int) (boxATopLeft >> 32);
        int bits$iv$iv$iv14 = (int) (boxBTopLeft >> 32);
        float horzInterLeft = Math.max(Float.intBitsToFloat(bits$iv$iv$iv13), Float.intBitsToFloat(bits$iv$iv$iv14));
        int bits$iv$iv$iv15 = (int) (boxABottomRight >> 32);
        int bits$iv$iv$iv16 = (int) (boxBBottomRight >> 32);
        float horzInterRight = Math.min(Float.intBitsToFloat(bits$iv$iv$iv15), Float.intBitsToFloat(bits$iv$iv$iv16));
        float horzIntersection = Math.max(0.0f, horzInterRight - horzInterLeft);
        boolean isVerticallyAligned = vertIntersection >= heightA * 0.5f || vertIntersection >= heightB * 0.5f;
        boolean isHorizontallyDistinct = horzIntersection < widthA * 0.5f && horzIntersection < 0.5f * widthB;
        return isVerticallyAligned && isHorizontallyDistinct;
    }
}
