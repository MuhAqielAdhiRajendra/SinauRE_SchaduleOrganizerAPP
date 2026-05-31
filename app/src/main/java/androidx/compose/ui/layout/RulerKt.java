package androidx.compose.ui.layout;

import kotlin.Metadata;

/* JADX INFO: compiled from: Ruler.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a1\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u000e\u0010\u0005\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\u0001H\u0002¢\u0006\u0002\u0010\t¨\u0006\n"}, d2 = {"mergeRulerValues", "", "Landroidx/compose/ui/layout/Placeable$PlacementScope;", "useGreater", "", "rulers", "", "Landroidx/compose/ui/layout/Ruler;", "defaultValue", "(Landroidx/compose/ui/layout/Placeable$PlacementScope;Z[Landroidx/compose/ui/layout/Ruler;F)F", "ui"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class RulerKt {
    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final float mergeRulerValues(androidx.compose.ui.layout.Placeable.PlacementScope r11, boolean r12, androidx.compose.ui.layout.Ruler[] r13, float r14) {
        /*
            r0 = 0
            r0 = 2143289344(0x7fc00000, float:NaN)
            r1 = r13
            r2 = 0
            int r3 = r1.length
            r4 = 0
            r5 = r4
        L8:
            if (r5 >= r3) goto L29
            r6 = r1[r5]
            r7 = r6
            r8 = 0
            r9 = 2143289344(0x7fc00000, float:NaN)
            float r9 = r11.current(r7, r9)
            boolean r10 = java.lang.Float.isNaN(r0)
            if (r10 != 0) goto L23
            int r10 = (r9 > r0 ? 1 : (r9 == r0 ? 0 : -1))
            if (r10 <= 0) goto L20
            r10 = 1
            goto L21
        L20:
            r10 = r4
        L21:
            if (r12 != r10) goto L24
        L23:
            r0 = r9
        L24:
            int r5 = r5 + 1
            goto L8
        L29:
            boolean r1 = java.lang.Float.isNaN(r0)
            if (r1 == 0) goto L32
            r1 = r14
            goto L33
        L32:
            r1 = r0
        L33:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.layout.RulerKt.mergeRulerValues(androidx.compose.ui.layout.Placeable$PlacementScope, boolean, androidx.compose.ui.layout.Ruler[], float):float");
    }
}
