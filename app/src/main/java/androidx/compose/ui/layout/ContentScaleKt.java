package androidx.compose.ui.layout;

import kotlin.Metadata;

/* JADX INFO: compiled from: ContentScale.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\u001a\u001f\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\u0002¢\u0006\u0004\b\u0005\u0010\u0006\u001a\u001f\u0010\u0007\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\u0002¢\u0006\u0004\b\b\u0010\u0006\u001a \u0010\t\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\u0082\b¢\u0006\u0004\b\n\u0010\u0006\u001a \u0010\u000b\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\u0082\b¢\u0006\u0004\b\f\u0010\u0006¨\u0006\r"}, d2 = {"computeFillMaxDimension", "", "srcSize", "Landroidx/compose/ui/geometry/Size;", "dstSize", "computeFillMaxDimension-iLBOSCw", "(JJ)F", "computeFillMinDimension", "computeFillMinDimension-iLBOSCw", "computeFillWidth", "computeFillWidth-iLBOSCw", "computeFillHeight", "computeFillHeight-iLBOSCw", "ui"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class ContentScaleKt {
    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: computeFillMaxDimension-iLBOSCw, reason: not valid java name */
    public static final float m6780computeFillMaxDimensioniLBOSCw(long srcSize, long dstSize) {
        int bits$iv$iv$iv$iv = (int) (dstSize >> 32);
        int bits$iv$iv$iv$iv2 = (int) (srcSize >> 32);
        float widthScale = Float.intBitsToFloat(bits$iv$iv$iv$iv) / Float.intBitsToFloat(bits$iv$iv$iv$iv2);
        int bits$iv$iv$iv$iv3 = (int) (dstSize & 4294967295L);
        int bits$iv$iv$iv$iv4 = (int) (srcSize & 4294967295L);
        float heightScale = Float.intBitsToFloat(bits$iv$iv$iv$iv3) / Float.intBitsToFloat(bits$iv$iv$iv$iv4);
        return Math.max(widthScale, heightScale);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: computeFillMinDimension-iLBOSCw, reason: not valid java name */
    public static final float m6781computeFillMinDimensioniLBOSCw(long srcSize, long dstSize) {
        int bits$iv$iv$iv$iv = (int) (dstSize >> 32);
        int bits$iv$iv$iv$iv2 = (int) (srcSize >> 32);
        float widthScale = Float.intBitsToFloat(bits$iv$iv$iv$iv) / Float.intBitsToFloat(bits$iv$iv$iv$iv2);
        int bits$iv$iv$iv$iv3 = (int) (dstSize & 4294967295L);
        int bits$iv$iv$iv$iv4 = (int) (srcSize & 4294967295L);
        float heightScale = Float.intBitsToFloat(bits$iv$iv$iv$iv3) / Float.intBitsToFloat(bits$iv$iv$iv$iv4);
        return Math.min(widthScale, heightScale);
    }

    /* JADX INFO: renamed from: computeFillWidth-iLBOSCw, reason: not valid java name */
    private static final float m6782computeFillWidthiLBOSCw(long srcSize, long dstSize) {
        int bits$iv$iv$iv = (int) (dstSize >> 32);
        int bits$iv$iv$iv2 = (int) (srcSize >> 32);
        return Float.intBitsToFloat(bits$iv$iv$iv) / Float.intBitsToFloat(bits$iv$iv$iv2);
    }

    /* JADX INFO: renamed from: computeFillHeight-iLBOSCw, reason: not valid java name */
    private static final float m6779computeFillHeightiLBOSCw(long srcSize, long dstSize) {
        int bits$iv$iv$iv = (int) (dstSize & 4294967295L);
        int bits$iv$iv$iv2 = (int) (4294967295L & srcSize);
        return Float.intBitsToFloat(bits$iv$iv$iv) / Float.intBitsToFloat(bits$iv$iv$iv2);
    }
}
