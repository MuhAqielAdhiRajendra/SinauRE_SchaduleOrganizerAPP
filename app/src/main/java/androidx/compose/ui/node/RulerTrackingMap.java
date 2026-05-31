package androidx.compose.ui.node;

import androidx.collection.MutableScatterSet;
import androidx.collection.ScatterSetKt;
import androidx.compose.ui.layout.Ruler;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LookaheadDelegate.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0014\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\b2\u0006\u0010\u0016\u001a\u00020\u0014J\u0019\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0015\u001a\u00020\b2\u0006\u0010\u0019\u001a\u00020\u0014H\u0086\u0002J\u0011\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0015\u001a\u00020\bH\u0086\u0002J8\u0010\u001c\u001a\u00020\u00182\u0006\u0010\u001d\u001a\u00020\u001b2\u0006\u0010\u001e\u001a\u00020\u001f2 \u0010 \u001a\u001c\u0012\u0004\u0012\u00020\b\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00100\u000f\u0018\u00010!J\u0006\u0010\"\u001a\u00020\u0018R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0018\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0007X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00100\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\b0\u000fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Landroidx/compose/ui/node/RulerTrackingMap;", "", "<init>", "()V", "size", "", "rulers", "", "Landroidx/compose/ui/layout/Ruler;", "[Landroidx/compose/ui/layout/Ruler;", "values", "", "accessFlags", "", "layoutNodes", "Landroidx/collection/MutableScatterSet;", "Landroidx/compose/ui/node/WeakReference;", "Landroidx/compose/ui/node/LayoutNode;", "newRulers", "getOrDefault", "", "ruler", "defaultValue", "set", "", "value", "contains", "", "notifyChanged", "isLookingAhead", "node", "Landroidx/compose/ui/node/LookaheadCapablePlaceable;", "rulerReaders", "Landroidx/collection/MutableScatterMap;", "clear", "ui"}, k = 1, mv = {2, 1, 0}, xi = 48)
final class RulerTrackingMap {
    private int size;
    private Ruler[] rulers = new Ruler[32];
    private float[] values = new float[32];
    private byte[] accessFlags = new byte[32];
    private MutableScatterSet<WeakReference<LayoutNode>> layoutNodes = ScatterSetKt.mutableScatterSetOf();
    private final MutableScatterSet<Ruler> newRulers = ScatterSetKt.mutableScatterSetOf();

    public final float getOrDefault(Ruler ruler, float defaultValue) {
        int index = ArraysKt.indexOf(this.rulers, ruler);
        if (index < 0) {
            return defaultValue;
        }
        return this.values[index];
    }

    public final void set(Ruler ruler, float value) {
        int index = ArraysKt.indexOf(this.rulers, ruler);
        if (index < 0) {
            int newIndex = this.size;
            if (newIndex == this.rulers.length) {
                int newSize = newIndex * 2;
                Object[] objArrCopyOf = Arrays.copyOf(this.rulers, newSize);
                Intrinsics.checkNotNullExpressionValue(objArrCopyOf, "copyOf(...)");
                this.rulers = (Ruler[]) objArrCopyOf;
                float[] fArrCopyOf = Arrays.copyOf(this.values, newSize);
                Intrinsics.checkNotNullExpressionValue(fArrCopyOf, "copyOf(...)");
                this.values = fArrCopyOf;
                byte[] bArrCopyOf = Arrays.copyOf(this.accessFlags, newSize);
                Intrinsics.checkNotNullExpressionValue(bArrCopyOf, "copyOf(...)");
                this.accessFlags = bArrCopyOf;
            }
            this.rulers[newIndex] = ruler;
            this.accessFlags[newIndex] = 3;
            this.values[newIndex] = value;
            this.size++;
            return;
        }
        float oldValue = this.values[index];
        if (!(oldValue == value)) {
            this.values[index] = value;
            this.accessFlags[index] = 1;
        } else if (this.accessFlags[index] == 2) {
            this.accessFlags[index] = 0;
        }
    }

    public final boolean contains(Ruler ruler) {
        return ArraysKt.contains(this.rulers, ruler);
    }

    /* JADX WARN: Removed duplicated region for block: B:149:0x00ef  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void notifyChanged(boolean r33, androidx.compose.ui.node.LookaheadCapablePlaceable r34, androidx.collection.MutableScatterMap<androidx.compose.ui.layout.Ruler, androidx.collection.MutableScatterSet<androidx.compose.ui.node.WeakReference<androidx.compose.ui.node.LayoutNode>>> r35) {
        /*
            Method dump skipped, instruction units count: 422
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.node.RulerTrackingMap.notifyChanged(boolean, androidx.compose.ui.node.LookaheadCapablePlaceable, androidx.collection.MutableScatterMap):void");
    }

    public final void clear() {
        int i = this.size;
        for (int i2 = 0; i2 < i; i2++) {
            this.rulers[i2] = null;
            this.values[i2] = Float.NaN;
            this.accessFlags[i2] = 0;
        }
        this.size = 0;
    }
}
