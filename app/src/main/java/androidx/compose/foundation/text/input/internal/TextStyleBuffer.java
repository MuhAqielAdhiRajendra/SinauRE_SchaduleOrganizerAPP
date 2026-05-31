package androidx.compose.foundation.text.input.internal;

import androidx.collection.MutableIntList;
import androidx.compose.ui.text.AnnotatedString;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TextStyleBuffer.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\f\b\u0001\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0019\u0012\u0010\b\u0002\u0010\u0003\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0000¢\u0006\u0004\b\u0004\u0010\u0005J#\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00028\u00002\u0006\u0010\u0016\u001a\u00020\t2\u0006\u0010\u0017\u001a\u00020\t¢\u0006\u0002\u0010\u0018J\"\u0010\u0019\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u001b0\u001a2\u0006\u0010\u0016\u001a\u00020\t2\u0006\u0010\u0017\u001a\u00020\tJ\u0012\u0010\u001c\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u001b0\u001aJ#\u0010\u001d\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00028\u00002\u0006\u0010\u0016\u001a\u00020\t2\u0006\u0010\u0017\u001a\u00020\t¢\u0006\u0002\u0010\u0018J\u001e\u0010\u001e\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\t2\u0006\u0010\u0017\u001a\u00020\t2\u0006\u0010\u001f\u001a\u00020\tJ\u0010\u0010 \u001a\u00020\t2\u0006\u0010!\u001a\u00020\tH\u0002J\u0010\u0010\"\u001a\u00020\t2\u0006\u0010!\u001a\u00020\tH\u0002J\u0018\u0010#\u001a\u00020$2\u0006\u0010\u0016\u001a\u00020\t2\u0006\u0010\u0017\u001a\u00020\tH\u0002J\u0010\u0010%\u001a\u00020$2\u0006\u0010&\u001a\u00020\tH\u0002J\u0010\u0010'\u001a\u00020$2\u0006\u0010&\u001a\u00020\tH\u0002J\u0010\u0010(\u001a\u00020$2\u0006\u0010&\u001a\u00020\tH\u0002J\u0010\u0010)\u001a\u00020$2\u0006\u0010&\u001a\u00020\tH\u0002J\u0010\u0010*\u001a\u00020$2\u0006\u0010+\u001a\u00020\tH\u0002J\u0006\u0010,\u001a\u00020$J\u0013\u0010-\u001a\u00020\u00142\b\u0010.\u001a\u0004\u0018\u00010\u0002H\u0096\u0002J\b\u0010/\u001a\u00020\tH\u0016R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\u000e\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u000b\"\u0004\b\u0010\u0010\rR\u0014\u0010\u0011\u001a\u00020\t8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u000b¨\u00060"}, d2 = {"Landroidx/compose/foundation/text/input/internal/TextStyleBuffer;", "T", "", "source", "<init>", "(Landroidx/compose/foundation/text/input/internal/TextStyleBuffer;)V", "intervalTree", "Landroidx/compose/foundation/text/input/internal/IntIntervalTree;", "gapStart", "", "getGapStart", "()I", "setGapStart", "(I)V", "gapEnd", "getGapEnd", "setGapEnd", "gapLength", "getGapLength", "addStyle", "", "style", "start", "end", "(Ljava/lang/Object;II)Z", "getStyles", "", "Landroidx/compose/ui/text/AnnotatedString$Range;", "getAllStyles", "removeStyle", "replaceText", "newLength", "originalIndexToGapBuffer", "index", "gapBufferToOriginalIndex", "deleteText", "", "moveGapLeft", "count", "moveGapRight", "deleteBeforeGap", "deleteAfterGap", "enlargeGapIfNeeded", "requiredSize", "clear", "equals", "other", "hashCode", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class TextStyleBuffer<T> {
    public static final int $stable = 8;
    private int gapEnd;
    private int gapStart;
    private final IntIntervalTree<T> intervalTree;

    public TextStyleBuffer() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public TextStyleBuffer(TextStyleBuffer<T> textStyleBuffer) {
        this.intervalTree = textStyleBuffer != null ? new IntIntervalTree<>(textStyleBuffer.intervalTree) : new IntIntervalTree<>(null, 1, null);
        if (textStyleBuffer != null) {
            this.gapStart = textStyleBuffer.gapStart;
            this.gapEnd = textStyleBuffer.gapEnd;
        } else {
            this.gapStart = 0;
            this.gapEnd = 1000;
        }
    }

    public /* synthetic */ TextStyleBuffer(TextStyleBuffer textStyleBuffer, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : textStyleBuffer);
    }

    public final int getGapStart() {
        return this.gapStart;
    }

    public final void setGapStart(int i) {
        this.gapStart = i;
    }

    public final int getGapEnd() {
        return this.gapEnd;
    }

    public final void setGapEnd(int i) {
        this.gapEnd = i;
    }

    private final int getGapLength() {
        return this.gapEnd - this.gapStart;
    }

    public final boolean addStyle(T style, int start, int end) {
        int startInBuffer = originalIndexToGapBuffer(start);
        int endInBuffer = originalIndexToGapBuffer(end);
        return this.intervalTree.addInterval(style, startInBuffer, endInBuffer);
    }

    public final List<AnnotatedString.Range<T>> getStyles(int start, int end) {
        if (start > end) {
            return CollectionsKt.emptyList();
        }
        int startInBuffer = originalIndexToGapBuffer(start);
        int endInBuffer = originalIndexToGapBuffer(end);
        final List result = new ArrayList();
        this.intervalTree.forEachIntervalInRange(startInBuffer, endInBuffer, new Function3() { // from class: androidx.compose.foundation.text.input.internal.TextStyleBuffer$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return TextStyleBuffer.getStyles$lambda$0(result, this, obj, ((Integer) obj2).intValue(), ((Integer) obj3).intValue());
            }
        });
        return result;
    }

    static final Unit getStyles$lambda$0(List $result, TextStyleBuffer this$0, Object item, int intervalStart, int intervalEnd) {
        $result.add(new AnnotatedString.Range(item, this$0.gapBufferToOriginalIndex(intervalStart), this$0.gapBufferToOriginalIndex(intervalEnd)));
        return Unit.INSTANCE;
    }

    public final List<AnnotatedString.Range<T>> getAllStyles() {
        final List result = new ArrayList();
        this.intervalTree.forAllIntervals(new Function3() { // from class: androidx.compose.foundation.text.input.internal.TextStyleBuffer$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return TextStyleBuffer.getAllStyles$lambda$0(result, this, obj, ((Integer) obj2).intValue(), ((Integer) obj3).intValue());
            }
        });
        return result;
    }

    static final Unit getAllStyles$lambda$0(List $result, TextStyleBuffer this$0, Object item, int start, int end) {
        $result.add(new AnnotatedString.Range(item, this$0.gapBufferToOriginalIndex(start), this$0.gapBufferToOriginalIndex(end)));
        return Unit.INSTANCE;
    }

    public final boolean removeStyle(T style, int start, int end) {
        int startInBuffer = originalIndexToGapBuffer(start);
        int endInBuffer = originalIndexToGapBuffer(end);
        return this.intervalTree.removeInterval(style, startInBuffer, endInBuffer);
    }

    public final boolean replaceText(int start, int end, int newLength) {
        if (this.intervalTree.isEmpty()) {
            return false;
        }
        enlargeGapIfNeeded(newLength - (end - start));
        deleteText(start, end);
        this.gapStart += newLength;
        return true;
    }

    private final int originalIndexToGapBuffer(int index) {
        if (index < this.gapStart) {
            return index;
        }
        return getGapLength() + index;
    }

    private final int gapBufferToOriginalIndex(int index) {
        if (index < this.gapStart) {
            return index;
        }
        return index - getGapLength();
    }

    private final void deleteText(int start, int end) {
        if (start < this.gapStart && end <= this.gapStart) {
            moveGapLeft(this.gapStart - end);
            deleteBeforeGap(end - start);
        } else {
            if (start < this.gapStart && end >= this.gapStart) {
                int deleteCountBeforeGap = this.gapStart - start;
                int deleteCountAfterGap = end - this.gapStart;
                deleteBeforeGap(deleteCountBeforeGap);
                deleteAfterGap(deleteCountAfterGap);
                return;
            }
            moveGapRight(start - this.gapStart);
            deleteAfterGap(end - start);
        }
    }

    private final void moveGapLeft(int count) {
        if (count == 0) {
            return;
        }
        IntIntervalTree<T> intIntervalTree = this.intervalTree;
        int start$iv = this.gapStart - count;
        int end$iv = this.gapStart;
        MutableIntList toRemove$iv = intIntervalTree.getTempArray();
        if (!Node.m1832equalsimpl0(intIntervalTree.getRoot(), intIntervalTree.getTerminator()) && intIntervalTree.m1807getMax330cO7A(intIntervalTree.getRoot()) >= start$iv && intIntervalTree.m1808getMin330cO7A(intIntervalTree.getRoot()) <= end$iv) {
            int visitedState$iv$iv = 0;
            int node$iv$iv = intIntervalTree.getRoot();
            while (!Node.m1832equalsimpl0(node$iv$iv, intIntervalTree.getTerminator())) {
                switch (visitedState$iv$iv) {
                    case 0:
                        int start$iv2 = start$iv;
                        int end$iv2 = end$iv;
                        if (Node.m1832equalsimpl0(intIntervalTree.m1806getLeftbLpG9ms(node$iv$iv), intIntervalTree.getTerminator()) || intIntervalTree.m1807getMax330cO7A(intIntervalTree.m1806getLeftbLpG9ms(node$iv$iv)) < start$iv) {
                            visitedState$iv$iv = 1;
                            start$iv = start$iv2;
                            end$iv = end$iv2;
                            continue;
                        } else {
                            node$iv$iv = intIntervalTree.m1806getLeftbLpG9ms(node$iv$iv);
                            visitedState$iv$iv = 0;
                            start$iv = start$iv2;
                            end$iv = end$iv2;
                        }
                        break;
                    case 1:
                        int it$iv = node$iv$iv;
                        int node$iv = Node.m1830constructorimpl(it$iv);
                        int value = intIntervalTree.m1812getStart330cO7A(node$iv);
                        int start$iv3 = start$iv;
                        int end$iv3 = end$iv;
                        intIntervalTree.m1826setStart9hnwElY(node$iv, value < this.gapStart && this.gapStart - count <= value ? getGapLength() + value : value);
                        int value2 = intIntervalTree.m1805getEnd330cO7A(node$iv);
                        intIntervalTree.m1819setEnd9hnwElY(node$iv, value2 < this.gapStart && this.gapStart - count <= value2 ? getGapLength() + value2 : value2);
                        int value3 = intIntervalTree.m1808getMin330cO7A(node$iv);
                        intIntervalTree.m1822setMin9hnwElY(node$iv, value3 < this.gapStart && this.gapStart - count <= value3 ? getGapLength() + value3 : value3);
                        int value4 = intIntervalTree.m1807getMax330cO7A(node$iv);
                        intIntervalTree.m1821setMax9hnwElY(node$iv, value4 < this.gapStart && this.gapStart - count <= value4 ? getGapLength() + value4 : value4);
                        if (intIntervalTree.m1805getEnd330cO7A(node$iv) <= intIntervalTree.m1812getStart330cO7A(node$iv)) {
                            intIntervalTree.m1803addZlWbn38(toRemove$iv, node$iv);
                        }
                        if (Node.m1832equalsimpl0(intIntervalTree.m1810getRightbLpG9ms(node$iv$iv), intIntervalTree.getTerminator()) || intIntervalTree.m1807getMax330cO7A(intIntervalTree.m1810getRightbLpG9ms(node$iv$iv)) < start$iv || intIntervalTree.m1808getMin330cO7A(intIntervalTree.m1810getRightbLpG9ms(node$iv$iv)) > end$iv) {
                            visitedState$iv$iv = 2;
                            start$iv = start$iv3;
                            end$iv = end$iv3;
                            continue;
                        } else {
                            node$iv$iv = intIntervalTree.m1810getRightbLpG9ms(node$iv$iv);
                            visitedState$iv$iv = 0;
                            start$iv = start$iv3;
                            end$iv = end$iv3;
                        }
                        break;
                    case 2:
                        if (!Node.m1832equalsimpl0(intIntervalTree.m1809getParentbLpG9ms(node$iv$iv), intIntervalTree.getTerminator())) {
                            visitedState$iv$iv = Node.m1832equalsimpl0(node$iv$iv, intIntervalTree.m1806getLeftbLpG9ms(intIntervalTree.m1809getParentbLpG9ms(node$iv$iv))) ? 1 : 2;
                        }
                        node$iv$iv = intIntervalTree.m1809getParentbLpG9ms(node$iv$iv);
                        break;
                }
                start$iv = start$iv;
                end$iv = end$iv;
            }
        }
        MutableIntList this_$iv$iv = toRemove$iv;
        int[] content$iv$iv = this_$iv$iv.content;
        int i = this_$iv$iv._size;
        for (int i$iv$iv = 0; i$iv$iv < i; i$iv$iv++) {
            int it$iv2 = content$iv$iv[i$iv$iv];
            intIntervalTree.m1797removeNode9hnwElY(Node.m1830constructorimpl(it$iv2), false);
        }
        toRemove$iv.clear();
        intIntervalTree.cleanDeletedNodesIfNeeded();
        this.gapStart -= count;
        this.gapEnd -= count;
    }

    private final void moveGapRight(int count) {
        if (count == 0) {
            return;
        }
        IntIntervalTree<T> intIntervalTree = this.intervalTree;
        int start$iv = this.gapEnd;
        int end$iv = this.gapEnd + count;
        MutableIntList toRemove$iv = intIntervalTree.getTempArray();
        if (!Node.m1832equalsimpl0(intIntervalTree.getRoot(), intIntervalTree.getTerminator()) && intIntervalTree.m1807getMax330cO7A(intIntervalTree.getRoot()) >= start$iv && intIntervalTree.m1808getMin330cO7A(intIntervalTree.getRoot()) <= end$iv) {
            int visitedState$iv$iv = 0;
            int node$iv$iv = intIntervalTree.getRoot();
            while (!Node.m1832equalsimpl0(node$iv$iv, intIntervalTree.getTerminator())) {
                switch (visitedState$iv$iv) {
                    case 0:
                        int start$iv2 = start$iv;
                        int end$iv2 = end$iv;
                        if (Node.m1832equalsimpl0(intIntervalTree.m1806getLeftbLpG9ms(node$iv$iv), intIntervalTree.getTerminator()) || intIntervalTree.m1807getMax330cO7A(intIntervalTree.m1806getLeftbLpG9ms(node$iv$iv)) < start$iv) {
                            visitedState$iv$iv = 1;
                            start$iv = start$iv2;
                            end$iv = end$iv2;
                            continue;
                        } else {
                            node$iv$iv = intIntervalTree.m1806getLeftbLpG9ms(node$iv$iv);
                            visitedState$iv$iv = 0;
                            start$iv = start$iv2;
                            end$iv = end$iv2;
                        }
                        break;
                    case 1:
                        int it$iv = node$iv$iv;
                        int node$iv = Node.m1830constructorimpl(it$iv);
                        int value = intIntervalTree.m1812getStart330cO7A(node$iv);
                        int start$iv3 = start$iv;
                        int end$iv3 = end$iv;
                        intIntervalTree.m1826setStart9hnwElY(node$iv, value < this.gapEnd + count && this.gapEnd <= value ? value - getGapLength() : value);
                        int value2 = intIntervalTree.m1805getEnd330cO7A(node$iv);
                        intIntervalTree.m1819setEnd9hnwElY(node$iv, value2 < this.gapEnd + count && this.gapEnd <= value2 ? value2 - getGapLength() : value2);
                        int value3 = intIntervalTree.m1808getMin330cO7A(node$iv);
                        intIntervalTree.m1822setMin9hnwElY(node$iv, value3 < this.gapEnd + count && this.gapEnd <= value3 ? value3 - getGapLength() : value3);
                        int value4 = intIntervalTree.m1807getMax330cO7A(node$iv);
                        intIntervalTree.m1821setMax9hnwElY(node$iv, value4 < this.gapEnd + count && this.gapEnd <= value4 ? value4 - getGapLength() : value4);
                        if (intIntervalTree.m1805getEnd330cO7A(node$iv) <= intIntervalTree.m1812getStart330cO7A(node$iv)) {
                            intIntervalTree.m1803addZlWbn38(toRemove$iv, node$iv);
                        }
                        if (Node.m1832equalsimpl0(intIntervalTree.m1810getRightbLpG9ms(node$iv$iv), intIntervalTree.getTerminator()) || intIntervalTree.m1807getMax330cO7A(intIntervalTree.m1810getRightbLpG9ms(node$iv$iv)) < start$iv || intIntervalTree.m1808getMin330cO7A(intIntervalTree.m1810getRightbLpG9ms(node$iv$iv)) > end$iv) {
                            visitedState$iv$iv = 2;
                            start$iv = start$iv3;
                            end$iv = end$iv3;
                            continue;
                        } else {
                            node$iv$iv = intIntervalTree.m1810getRightbLpG9ms(node$iv$iv);
                            visitedState$iv$iv = 0;
                            start$iv = start$iv3;
                            end$iv = end$iv3;
                        }
                        break;
                    case 2:
                        if (!Node.m1832equalsimpl0(intIntervalTree.m1809getParentbLpG9ms(node$iv$iv), intIntervalTree.getTerminator())) {
                            visitedState$iv$iv = Node.m1832equalsimpl0(node$iv$iv, intIntervalTree.m1806getLeftbLpG9ms(intIntervalTree.m1809getParentbLpG9ms(node$iv$iv))) ? 1 : 2;
                        }
                        node$iv$iv = intIntervalTree.m1809getParentbLpG9ms(node$iv$iv);
                        break;
                }
                start$iv = start$iv;
                end$iv = end$iv;
            }
        }
        MutableIntList this_$iv$iv = toRemove$iv;
        int[] content$iv$iv = this_$iv$iv.content;
        int i = this_$iv$iv._size;
        for (int i$iv$iv = 0; i$iv$iv < i; i$iv$iv++) {
            int it$iv2 = content$iv$iv[i$iv$iv];
            intIntervalTree.m1797removeNode9hnwElY(Node.m1830constructorimpl(it$iv2), false);
        }
        toRemove$iv.clear();
        intIntervalTree.cleanDeletedNodesIfNeeded();
        this.gapStart += count;
        this.gapEnd += count;
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x00a4  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00a7  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00c0  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00c3  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00dc  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00df  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00eb  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00f4  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00fa  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00fd  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x010c  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x011f  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0140 A[ADDED_TO_REGION, REMOVE, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void deleteBeforeGap(int r21) {
        /*
            Method dump skipped, instruction units count: 454
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.input.internal.TextStyleBuffer.deleteBeforeGap(int):void");
    }

    private final void deleteAfterGap(int count) {
        if (count == 0) {
            return;
        }
        IntIntervalTree<T> intIntervalTree = this.intervalTree;
        int start$iv = this.gapEnd;
        int end$iv = this.gapEnd + count;
        MutableIntList toRemove$iv = intIntervalTree.getTempArray();
        if (!Node.m1832equalsimpl0(intIntervalTree.getRoot(), intIntervalTree.getTerminator()) && intIntervalTree.m1807getMax330cO7A(intIntervalTree.getRoot()) >= start$iv && intIntervalTree.m1808getMin330cO7A(intIntervalTree.getRoot()) <= end$iv) {
            int visitedState$iv$iv = 0;
            int node$iv$iv = intIntervalTree.getRoot();
            while (!Node.m1832equalsimpl0(node$iv$iv, intIntervalTree.getTerminator())) {
                switch (visitedState$iv$iv) {
                    case 0:
                        int start$iv2 = start$iv;
                        int end$iv2 = end$iv;
                        if (Node.m1832equalsimpl0(intIntervalTree.m1806getLeftbLpG9ms(node$iv$iv), intIntervalTree.getTerminator()) || intIntervalTree.m1807getMax330cO7A(intIntervalTree.m1806getLeftbLpG9ms(node$iv$iv)) < start$iv) {
                            visitedState$iv$iv = 1;
                            start$iv = start$iv2;
                            end$iv = end$iv2;
                            continue;
                        } else {
                            node$iv$iv = intIntervalTree.m1806getLeftbLpG9ms(node$iv$iv);
                            visitedState$iv$iv = 0;
                            start$iv = start$iv2;
                            end$iv = end$iv2;
                        }
                        break;
                    case 1:
                        int it$iv = node$iv$iv;
                        int node$iv = Node.m1830constructorimpl(it$iv);
                        int value = intIntervalTree.m1812getStart330cO7A(node$iv);
                        int start$iv3 = start$iv;
                        int end$iv3 = end$iv;
                        intIntervalTree.m1826setStart9hnwElY(node$iv, value < this.gapEnd + count && this.gapEnd <= value ? this.gapEnd + count : value);
                        int value2 = intIntervalTree.m1805getEnd330cO7A(node$iv);
                        intIntervalTree.m1819setEnd9hnwElY(node$iv, value2 < this.gapEnd + count && this.gapEnd <= value2 ? this.gapEnd + count : value2);
                        int value3 = intIntervalTree.m1808getMin330cO7A(node$iv);
                        intIntervalTree.m1822setMin9hnwElY(node$iv, value3 < this.gapEnd + count && this.gapEnd <= value3 ? this.gapEnd + count : value3);
                        int value4 = intIntervalTree.m1807getMax330cO7A(node$iv);
                        intIntervalTree.m1821setMax9hnwElY(node$iv, value4 < this.gapEnd + count && this.gapEnd <= value4 ? this.gapEnd + count : value4);
                        if (intIntervalTree.m1805getEnd330cO7A(node$iv) <= intIntervalTree.m1812getStart330cO7A(node$iv)) {
                            intIntervalTree.m1803addZlWbn38(toRemove$iv, node$iv);
                        }
                        if (Node.m1832equalsimpl0(intIntervalTree.m1810getRightbLpG9ms(node$iv$iv), intIntervalTree.getTerminator()) || intIntervalTree.m1807getMax330cO7A(intIntervalTree.m1810getRightbLpG9ms(node$iv$iv)) < start$iv || intIntervalTree.m1808getMin330cO7A(intIntervalTree.m1810getRightbLpG9ms(node$iv$iv)) > end$iv) {
                            visitedState$iv$iv = 2;
                            start$iv = start$iv3;
                            end$iv = end$iv3;
                            continue;
                        } else {
                            node$iv$iv = intIntervalTree.m1810getRightbLpG9ms(node$iv$iv);
                            visitedState$iv$iv = 0;
                            start$iv = start$iv3;
                            end$iv = end$iv3;
                        }
                        break;
                    case 2:
                        if (!Node.m1832equalsimpl0(intIntervalTree.m1809getParentbLpG9ms(node$iv$iv), intIntervalTree.getTerminator())) {
                            visitedState$iv$iv = Node.m1832equalsimpl0(node$iv$iv, intIntervalTree.m1806getLeftbLpG9ms(intIntervalTree.m1809getParentbLpG9ms(node$iv$iv))) ? 1 : 2;
                        }
                        node$iv$iv = intIntervalTree.m1809getParentbLpG9ms(node$iv$iv);
                        break;
                }
                start$iv = start$iv;
                end$iv = end$iv;
            }
        }
        MutableIntList this_$iv$iv = toRemove$iv;
        int[] content$iv$iv = this_$iv$iv.content;
        int i = this_$iv$iv._size;
        for (int i$iv$iv = 0; i$iv$iv < i; i$iv$iv++) {
            int it$iv2 = content$iv$iv[i$iv$iv];
            intIntervalTree.m1797removeNode9hnwElY(Node.m1830constructorimpl(it$iv2), false);
        }
        toRemove$iv.clear();
        intIntervalTree.cleanDeletedNodesIfNeeded();
        this.gapEnd += count;
    }

    private final void enlargeGapIfNeeded(int requiredSize) {
        int offset;
        if (!this.intervalTree.isEmpty() && getGapLength() < requiredSize) {
            int offset2 = (getGapLength() - requiredSize) + 1000;
            IntIntervalTree<T> intIntervalTree = this.intervalTree;
            int start$iv = this.gapStart;
            MutableIntList toRemove$iv = intIntervalTree.getTempArray();
            if (Node.m1832equalsimpl0(intIntervalTree.getRoot(), intIntervalTree.getTerminator()) || intIntervalTree.m1807getMax330cO7A(intIntervalTree.getRoot()) < start$iv || intIntervalTree.m1808getMin330cO7A(intIntervalTree.getRoot()) > Integer.MAX_VALUE) {
                offset = offset2;
            } else {
                int visitedState$iv$iv = 0;
                int node$iv$iv = intIntervalTree.getRoot();
                while (!Node.m1832equalsimpl0(node$iv$iv, intIntervalTree.getTerminator())) {
                    switch (visitedState$iv$iv) {
                        case 0:
                            int offset3 = offset2;
                            int start$iv2 = start$iv;
                            if (Node.m1832equalsimpl0(intIntervalTree.m1806getLeftbLpG9ms(node$iv$iv), intIntervalTree.getTerminator()) || intIntervalTree.m1807getMax330cO7A(intIntervalTree.m1806getLeftbLpG9ms(node$iv$iv)) < start$iv) {
                                visitedState$iv$iv = 1;
                                offset2 = offset3;
                                start$iv = start$iv2;
                                continue;
                            } else {
                                node$iv$iv = intIntervalTree.m1806getLeftbLpG9ms(node$iv$iv);
                                visitedState$iv$iv = 0;
                                offset2 = offset3;
                                start$iv = start$iv2;
                            }
                            break;
                        case 1:
                            int it$iv = node$iv$iv;
                            int node$iv = Node.m1830constructorimpl(it$iv);
                            int offset4 = offset2;
                            int value = intIntervalTree.m1812getStart330cO7A(node$iv);
                            int start$iv3 = start$iv;
                            if (value >= this.gapStart) {
                                value += offset4;
                            }
                            intIntervalTree.m1826setStart9hnwElY(node$iv, value);
                            int value2 = intIntervalTree.m1805getEnd330cO7A(node$iv);
                            if (value2 >= this.gapStart) {
                                value2 += offset4;
                            }
                            intIntervalTree.m1819setEnd9hnwElY(node$iv, value2);
                            int value3 = intIntervalTree.m1808getMin330cO7A(node$iv);
                            if (value3 >= this.gapStart) {
                                value3 += offset4;
                            }
                            intIntervalTree.m1822setMin9hnwElY(node$iv, value3);
                            int value4 = intIntervalTree.m1807getMax330cO7A(node$iv);
                            if (value4 >= this.gapStart) {
                                value4 += offset4;
                            }
                            intIntervalTree.m1821setMax9hnwElY(node$iv, value4);
                            if (intIntervalTree.m1805getEnd330cO7A(node$iv) <= intIntervalTree.m1812getStart330cO7A(node$iv)) {
                                intIntervalTree.m1803addZlWbn38(toRemove$iv, node$iv);
                            }
                            if (Node.m1832equalsimpl0(intIntervalTree.m1810getRightbLpG9ms(node$iv$iv), intIntervalTree.getTerminator()) || intIntervalTree.m1807getMax330cO7A(intIntervalTree.m1810getRightbLpG9ms(node$iv$iv)) < start$iv || intIntervalTree.m1808getMin330cO7A(intIntervalTree.m1810getRightbLpG9ms(node$iv$iv)) > Integer.MAX_VALUE) {
                                visitedState$iv$iv = 2;
                                offset2 = offset4;
                                start$iv = start$iv3;
                                continue;
                            } else {
                                node$iv$iv = intIntervalTree.m1810getRightbLpG9ms(node$iv$iv);
                                visitedState$iv$iv = 0;
                                offset2 = offset4;
                                start$iv = start$iv3;
                            }
                            break;
                        case 2:
                            if (!Node.m1832equalsimpl0(intIntervalTree.m1809getParentbLpG9ms(node$iv$iv), intIntervalTree.getTerminator())) {
                                visitedState$iv$iv = Node.m1832equalsimpl0(node$iv$iv, intIntervalTree.m1806getLeftbLpG9ms(intIntervalTree.m1809getParentbLpG9ms(node$iv$iv))) ? 1 : 2;
                            }
                            node$iv$iv = intIntervalTree.m1809getParentbLpG9ms(node$iv$iv);
                            break;
                    }
                    offset2 = offset2;
                    start$iv = start$iv;
                }
                offset = offset2;
            }
            MutableIntList this_$iv$iv = toRemove$iv;
            int[] content$iv$iv = this_$iv$iv.content;
            int i = this_$iv$iv._size;
            for (int i$iv$iv = 0; i$iv$iv < i; i$iv$iv++) {
                int it$iv2 = content$iv$iv[i$iv$iv];
                intIntervalTree.m1797removeNode9hnwElY(Node.m1830constructorimpl(it$iv2), false);
            }
            toRemove$iv.clear();
            intIntervalTree.cleanDeletedNodesIfNeeded();
            this.gapEnd += offset;
        }
    }

    public final void clear() {
        this.intervalTree.clear();
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof TextStyleBuffer) {
            return Intrinsics.areEqual(this.intervalTree, ((TextStyleBuffer) other).intervalTree);
        }
        return false;
    }

    public int hashCode() {
        return this.intervalTree.hashCode();
    }
}
