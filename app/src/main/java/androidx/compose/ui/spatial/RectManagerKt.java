package androidx.compose.ui.spatial;

import androidx.compose.ui.unit.IntOffset;
import kotlin.Metadata;

/* JADX INFO: compiled from: RectManager.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u000b\u001a\u0013\u0010\u0005\u001a\u00020\u0006*\u00020\u0007H\u0002¢\u0006\u0004\b\b\u0010\t\u001a\r\u0010\u0011\u001a\u00020\u0006*\u00020\u0001H\u0080\b\"\u0018\u0010\u0000\u001a\u00020\u0001*\u00020\u00028BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u001f\u0010\n\u001a\u00020\u0001*\u00020\u00068Â\u0002X\u0082\u0004¢\u0006\f\u0012\u0004\b\u000b\u0010\f\u001a\u0004\b\n\u0010\r\"\u001f\u0010\u000e\u001a\u00020\u0001*\u00020\u00068Â\u0002X\u0082\u0004¢\u0006\f\u0012\u0004\b\u000f\u0010\f\u001a\u0004\b\u0010\u0010\r¨\u0006\u0012"}, d2 = {"isSet", "", "Landroidx/compose/ui/unit/IntOffset;", "isSet--gyyYBs", "(J)Z", "analyzeComponents", "", "Landroidx/compose/ui/graphics/Matrix;", "analyzeComponents-58bKbWc", "([F)I", "isIdentity", "isIdentity$annotations", "(I)V", "(I)Z", "hasNonTranslationComponents", "getHasNonTranslationComponents$annotations", "getHasNonTranslationComponents", "toInt", "ui"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class RectManagerKt {
    private static /* synthetic */ void getHasNonTranslationComponents$annotations(int i) {
    }

    private static /* synthetic */ void isIdentity$annotations(int i) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: isSet--gyyYBs, reason: not valid java name */
    public static final boolean m7371isSetgyyYBs(long $this$isSet) {
        return !IntOffset.m8277equalsimpl0($this$isSet, IntOffset.INSTANCE.m8288getMaxnOccac());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:52:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x00b3  */
    /* JADX INFO: renamed from: analyzeComponents-58bKbWc, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final int m7370analyzeComponents58bKbWc(float[] r8) {
        /*
            r0 = r8
            int r1 = r0.length
            r2 = 16
            r3 = 0
            if (r1 >= r2) goto L8
            return r3
        L8:
            r1 = r0[r3]
            r2 = 1065353216(0x3f800000, float:1.0)
            int r1 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1))
            r4 = 1
            if (r1 != 0) goto L13
            r1 = r4
            goto L14
        L13:
            r1 = r3
        L14:
            r5 = 0
            if (r1 == 0) goto L7b
            r1 = r0[r4]
            int r1 = (r1 > r5 ? 1 : (r1 == r5 ? 0 : -1))
            if (r1 != 0) goto L1f
            r1 = r4
            goto L20
        L1f:
            r1 = r3
        L20:
            if (r1 == 0) goto L7b
            r1 = 2
            r1 = r0[r1]
            int r1 = (r1 > r5 ? 1 : (r1 == r5 ? 0 : -1))
            if (r1 != 0) goto L2b
            r1 = r4
            goto L2c
        L2b:
            r1 = r3
        L2c:
            if (r1 == 0) goto L7b
            r1 = 4
            r1 = r0[r1]
            int r1 = (r1 > r5 ? 1 : (r1 == r5 ? 0 : -1))
            if (r1 != 0) goto L37
            r1 = r4
            goto L38
        L37:
            r1 = r3
        L38:
            if (r1 == 0) goto L7b
            r1 = 5
            r1 = r0[r1]
            int r1 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1))
            if (r1 != 0) goto L43
            r1 = r4
            goto L44
        L43:
            r1 = r3
        L44:
            if (r1 == 0) goto L7b
            r1 = 6
            r1 = r0[r1]
            int r1 = (r1 > r5 ? 1 : (r1 == r5 ? 0 : -1))
            if (r1 != 0) goto L4f
            r1 = r4
            goto L50
        L4f:
            r1 = r3
        L50:
            if (r1 == 0) goto L7b
            r1 = 8
            r1 = r0[r1]
            int r1 = (r1 > r5 ? 1 : (r1 == r5 ? 0 : -1))
            if (r1 != 0) goto L5c
            r1 = r4
            goto L5d
        L5c:
            r1 = r3
        L5d:
            if (r1 == 0) goto L7b
            r1 = 9
            r1 = r0[r1]
            int r1 = (r1 > r5 ? 1 : (r1 == r5 ? 0 : -1))
            if (r1 != 0) goto L69
            r1 = r4
            goto L6a
        L69:
            r1 = r3
        L6a:
            if (r1 == 0) goto L7b
            r1 = 10
            r1 = r0[r1]
            int r1 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1))
            if (r1 != 0) goto L76
            r1 = r4
            goto L77
        L76:
            r1 = r3
        L77:
            if (r1 == 0) goto L7b
            r1 = r4
            goto L7c
        L7b:
            r1 = r3
        L7c:
            r6 = 12
            r6 = r0[r6]
            int r6 = (r6 > r5 ? 1 : (r6 == r5 ? 0 : -1))
            if (r6 != 0) goto L87
            r6 = r4
            goto L88
        L87:
            r6 = r3
        L88:
            if (r6 == 0) goto Lb3
            r6 = 13
            r6 = r0[r6]
            int r6 = (r6 > r5 ? 1 : (r6 == r5 ? 0 : -1))
            if (r6 != 0) goto L94
            r6 = r4
            goto L95
        L94:
            r6 = r3
        L95:
            if (r6 == 0) goto Lb3
            r6 = 14
            r6 = r0[r6]
            int r5 = (r6 > r5 ? 1 : (r6 == r5 ? 0 : -1))
            if (r5 != 0) goto La1
            r5 = r4
            goto La2
        La1:
            r5 = r3
        La2:
            if (r5 == 0) goto Lb3
            r5 = 15
            r5 = r0[r5]
            int r2 = (r5 > r2 ? 1 : (r5 == r2 ? 0 : -1))
            if (r2 != 0) goto Lae
            r2 = r4
            goto Laf
        Lae:
            r2 = r3
        Laf:
            if (r2 == 0) goto Lb3
            r2 = r4
            goto Lb4
        Lb3:
            r2 = r3
        Lb4:
            r5 = r1
            r6 = 0
            if (r5 == 0) goto Lba
            r5 = r4
            goto Lbb
        Lba:
            r5 = r3
        Lbb:
            int r5 = r5 << r4
            r6 = r2
            r7 = 0
            if (r6 == 0) goto Lc1
            r3 = r4
        Lc1:
            r3 = r3 | r5
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.spatial.RectManagerKt.m7370analyzeComponents58bKbWc(float[]):int");
    }

    private static final boolean isIdentity(int $this$isIdentity) {
        return $this$isIdentity == 3;
    }

    private static final boolean getHasNonTranslationComponents(int $this$hasNonTranslationComponents) {
        return ($this$hasNonTranslationComponents & 2) == 0;
    }

    public static final int toInt(boolean z) {
        return z ? 1 : 0;
    }
}
