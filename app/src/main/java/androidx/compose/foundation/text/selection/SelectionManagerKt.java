package androidx.compose.foundation.text.selection;

import androidx.compose.foundation.contextmenu.ContextMenuScope;
import androidx.compose.foundation.contextmenu.ContextMenuState;
import androidx.compose.foundation.text.CommonContextMenuAreaKt;
import androidx.compose.foundation.text.Handle;
import androidx.compose.foundation.text.TextContextMenuItems;
import androidx.compose.foundation.text.selection.Selection;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.RectKt;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LayoutCoordinatesKt;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.unit.IntSize;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: SelectionManager.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000d\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\u001a\u001e\u0010\u0000\u001a\u0004\u0018\u00010\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u00012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0001H\u0000\u001a%\u0010\u0004\u001a\u0013\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\u0002\b\b*\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0000\u001a\u001e\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\u00100\u000f\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\u000fH\u0002\u001a*\u0010\u0011\u001a\u00020\r2\u0018\u0010\u0012\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00010\u00130\u000f2\u0006\u0010\u0015\u001a\u00020\u0016H\u0001\u001a\u001f\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\t2\u0006\u0010\u001a\u001a\u00020\u001bH\u0000¢\u0006\u0004\b\u001c\u0010\u001d\u001a'\u0010\u001e\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\t2\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001f\u001a\u00020 H\u0002¢\u0006\u0004\b!\u0010\"\u001a\f\u0010#\u001a\u00020\r*\u00020\u0016H\u0000\u001a\u001b\u0010$\u001a\u00020%*\u00020\r2\u0006\u0010&\u001a\u00020\u0018H\u0000¢\u0006\u0004\b'\u0010(\"\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006)"}, d2 = {"merge", "Landroidx/compose/foundation/text/selection/Selection;", "lhs", "rhs", "contextMenuBuilder", "Lkotlin/Function1;", "Landroidx/compose/foundation/contextmenu/ContextMenuScope;", "", "Lkotlin/ExtensionFunctionType;", "Landroidx/compose/foundation/text/selection/SelectionManager;", "state", "Landroidx/compose/foundation/contextmenu/ContextMenuState;", "invertedInfiniteRect", "Landroidx/compose/ui/geometry/Rect;", "firstAndLast", "", "T", "getSelectedRegionRect", "selectableSubSelectionPairs", "Lkotlin/Pair;", "Landroidx/compose/foundation/text/selection/Selectable;", "containerCoordinates", "Landroidx/compose/ui/layout/LayoutCoordinates;", "calculateSelectionMagnifierCenterAndroid", "Landroidx/compose/ui/geometry/Offset;", "manager", "magnifierSize", "Landroidx/compose/ui/unit/IntSize;", "calculateSelectionMagnifierCenterAndroid-O0kMr_c", "(Landroidx/compose/foundation/text/selection/SelectionManager;J)J", "getMagnifierCenter", "anchor", "Landroidx/compose/foundation/text/selection/Selection$AnchorInfo;", "getMagnifierCenter-JVtK1S4", "(Landroidx/compose/foundation/text/selection/SelectionManager;JLandroidx/compose/foundation/text/selection/Selection$AnchorInfo;)J", "visibleBounds", "containsInclusive", "", TypedValues.CycleType.S_WAVE_OFFSET, "containsInclusive-Uv8p0NA", "(Landroidx/compose/ui/geometry/Rect;J)Z", "foundation"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class SelectionManagerKt {
    private static final Rect invertedInfiniteRect = new Rect(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY, Float.NEGATIVE_INFINITY, Float.NEGATIVE_INFINITY);

    /* JADX INFO: compiled from: SelectionManager.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Handle.values().length];
            try {
                iArr[Handle.SelectionStart.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[Handle.SelectionEnd.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[Handle.Cursor.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static final Selection merge(Selection lhs, Selection rhs) {
        Selection selectionMerge;
        return (lhs == null || (selectionMerge = lhs.merge(rhs)) == null) ? rhs : selectionMerge;
    }

    public static final Function1<ContextMenuScope, Unit> contextMenuBuilder(final SelectionManager $this$contextMenuBuilder, final ContextMenuState state) {
        return new Function1() { // from class: androidx.compose.foundation.text.selection.SelectionManagerKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SelectionManagerKt.contextMenuBuilder$lambda$0($this$contextMenuBuilder, state, (ContextMenuScope) obj);
            }
        };
    }

    private static final void contextMenuBuilder$lambda$0$selectionItem(ContextMenuScope $this, ContextMenuState $state, TextContextMenuItems label, boolean enabled, Function0<Unit> function0) {
        if (enabled) {
            $this.item(new CommonContextMenuAreaKt.AnonymousClass1(label), (14 & 2) != 0 ? Modifier.INSTANCE : null, (14 & 4) != 0, (14 & 8) != 0 ? null : null, new CommonContextMenuAreaKt.AnonymousClass2(function0, $state));
        }
    }

    static final Unit contextMenuBuilder$lambda$0(final SelectionManager $this_contextMenuBuilder, ContextMenuState $state, ContextMenuScope contextMenuScope) {
        contextMenuBuilder$lambda$0$selectionItem(contextMenuScope, $state, TextContextMenuItems.Copy, $this_contextMenuBuilder.isNonEmptySelection$foundation(), new Function0() { // from class: androidx.compose.foundation.text.selection.SelectionManagerKt$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return SelectionManagerKt.contextMenuBuilder$lambda$0$0($this_contextMenuBuilder);
            }
        });
        contextMenuBuilder$lambda$0$selectionItem(contextMenuScope, $state, TextContextMenuItems.SelectAll, !$this_contextMenuBuilder.isEntireContainerSelected$foundation(), new Function0() { // from class: androidx.compose.foundation.text.selection.SelectionManagerKt$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return SelectionManagerKt.contextMenuBuilder$lambda$0$1($this_contextMenuBuilder);
            }
        });
        CollectionsKt.listOf((Object[]) new Unit[]{Unit.INSTANCE, Unit.INSTANCE});
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit contextMenuBuilder$lambda$0$0(SelectionManager $this_contextMenuBuilder) {
        $this_contextMenuBuilder.copy$foundation();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit contextMenuBuilder$lambda$0$1(SelectionManager $this_contextMenuBuilder) {
        $this_contextMenuBuilder.selectAll$foundation();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final <T> List<T> firstAndLast(List<? extends T> list) {
        switch (list.size()) {
            case 0:
            case 1:
                return list;
            default:
                return CollectionsKt.listOf(CollectionsKt.first((List) list), CollectionsKt.last((List) list));
        }
    }

    public static final Rect getSelectedRegionRect(List<? extends Pair<? extends Selectable, Selection>> list, LayoutCoordinates containerCoordinates) {
        List<? extends Pair<? extends Selectable, Selection>> list2;
        int $i$f$fastForEach;
        int index$iv;
        int i;
        int[] offsets;
        if (list.isEmpty()) {
            return invertedInfiniteRect;
        }
        Rect rect = invertedInfiniteRect;
        float containerLeft = rect.getLeft();
        float containerTop = rect.getTop();
        float containerRight = rect.getRight();
        float containerBottom = rect.getBottom();
        List<? extends Pair<? extends Selectable, Selection>> list3 = list;
        int $i$f$fastForEach2 = 0;
        int index$iv2 = 0;
        int size = list3.size();
        while (index$iv2 < size) {
            Object item$iv = list3.get(index$iv2);
            Pair<? extends Selectable, Selection> pair = (Pair) item$iv;
            Selectable selectable = pair.component1();
            Selection subSelection = pair.component2();
            int startOffset = subSelection.getStart().getOffset();
            int endOffset = subSelection.getEnd().getOffset();
            if (startOffset == endOffset) {
                list2 = list3;
                $i$f$fastForEach = $i$f$fastForEach2;
                index$iv = index$iv2;
                i = size;
            } else {
                LayoutCoordinates localCoordinates = selectable.getLayoutCoordinates();
                if (localCoordinates == null) {
                    list2 = list3;
                    $i$f$fastForEach = $i$f$fastForEach2;
                    index$iv = index$iv2;
                    i = size;
                } else {
                    list2 = list3;
                    int minOffset = Math.min(startOffset, endOffset);
                    int maxOffset = Math.max(startOffset, endOffset);
                    $i$f$fastForEach = $i$f$fastForEach2;
                    int $i$f$fastForEach3 = maxOffset - 1;
                    index$iv = index$iv2;
                    if (minOffset == $i$f$fastForEach3) {
                        offsets = new int[]{minOffset};
                    } else {
                        offsets = new int[]{minOffset, maxOffset - 1};
                    }
                    Rect rect2 = invertedInfiniteRect;
                    float left = rect2.getLeft();
                    float top = rect2.getTop();
                    float right = rect2.getRight();
                    float bottom = rect2.getBottom();
                    int minOffset2 = offsets.length;
                    int[] offsets2 = offsets;
                    int i2 = 0;
                    i = size;
                    float left2 = left;
                    float top2 = top;
                    float right2 = right;
                    while (i2 < minOffset2) {
                        int i3 = minOffset2;
                        int i4 = offsets2[i2];
                        Rect rect3 = selectable.getBoundingBox(i4);
                        left2 = Math.min(left2, rect3.getLeft());
                        top2 = Math.min(top2, rect3.getTop());
                        right2 = Math.max(right2, rect3.getRight());
                        bottom = Math.max(bottom, rect3.getBottom());
                        i2++;
                        minOffset2 = i3;
                    }
                    float y$iv = top2;
                    float x$iv = left2;
                    long v1$iv$iv = Float.floatToRawIntBits(x$iv);
                    long v2$iv$iv = Float.floatToRawIntBits(y$iv);
                    long localTopLeft = Offset.m5060constructorimpl((v1$iv$iv << 32) | (v2$iv$iv & 4294967295L));
                    float y$iv2 = bottom;
                    float x$iv2 = right2;
                    long v1$iv$iv2 = Float.floatToRawIntBits(x$iv2);
                    long v2$iv$iv2 = Float.floatToRawIntBits(y$iv2);
                    long localBottomRight = Offset.m5060constructorimpl((v1$iv$iv2 << 32) | (v2$iv$iv2 & 4294967295L));
                    long containerTopLeft = containerCoordinates.mo6792localPositionOfR5De75A(localCoordinates, localTopLeft);
                    long containerBottomRight = containerCoordinates.mo6792localPositionOfR5De75A(localCoordinates, localBottomRight);
                    long localTopLeft2 = containerTopLeft >> 32;
                    int bits$iv$iv$iv = (int) localTopLeft2;
                    float containerLeft2 = Math.min(containerLeft, Float.intBitsToFloat(bits$iv$iv$iv));
                    long arg0$iv = containerTopLeft & 4294967295L;
                    int bits$iv$iv$iv2 = (int) arg0$iv;
                    float containerTop2 = Math.min(containerTop, Float.intBitsToFloat(bits$iv$iv$iv2));
                    int bits$iv$iv$iv3 = (int) (containerBottomRight >> 32);
                    float containerRight2 = Math.max(containerRight, Float.intBitsToFloat(bits$iv$iv$iv3));
                    int bits$iv$iv$iv4 = (int) (containerBottomRight & 4294967295L);
                    containerBottom = Math.max(containerBottom, Float.intBitsToFloat(bits$iv$iv$iv4));
                    containerRight = containerRight2;
                    containerLeft = containerLeft2;
                    containerTop = containerTop2;
                }
            }
            index$iv2 = index$iv + 1;
            list3 = list2;
            $i$f$fastForEach2 = $i$f$fastForEach;
            size = i;
        }
        return new Rect(containerLeft, containerTop, containerRight, containerBottom);
    }

    /* JADX INFO: renamed from: calculateSelectionMagnifierCenterAndroid-O0kMr_c, reason: not valid java name */
    public static final long m2077calculateSelectionMagnifierCenterAndroidO0kMr_c(SelectionManager manager, long magnifierSize) {
        Selection selection = manager.getSelection();
        if (selection == null) {
            return Offset.INSTANCE.m5083getUnspecifiedF1C5BW0();
        }
        Handle draggingHandle = manager.getDraggingHandle();
        switch (draggingHandle == null ? -1 : WhenMappings.$EnumSwitchMapping$0[draggingHandle.ordinal()]) {
            case -1:
                return Offset.INSTANCE.m5083getUnspecifiedF1C5BW0();
            case 0:
            default:
                throw new NoWhenBranchMatchedException();
            case 1:
                return m2079getMagnifierCenterJVtK1S4(manager, magnifierSize, selection.getStart());
            case 2:
                return m2079getMagnifierCenterJVtK1S4(manager, magnifierSize, selection.getEnd());
            case 3:
                throw new IllegalStateException("SelectionContainer does not support cursor".toString());
        }
    }

    /* JADX INFO: renamed from: getMagnifierCenter-JVtK1S4, reason: not valid java name */
    private static final long m2079getMagnifierCenterJVtK1S4(SelectionManager manager, long magnifierSize, Selection.AnchorInfo anchor) {
        LayoutCoordinates containerCoordinates;
        LayoutCoordinates selectableCoordinates;
        int offset;
        float lineStartX;
        Selectable selectable = manager.getAnchorSelectable$foundation(anchor);
        if (selectable != null && (containerCoordinates = manager.getContainerLayoutCoordinates()) != null && (selectableCoordinates = selectable.getLayoutCoordinates()) != null && (offset = anchor.getOffset()) <= selectable.getLastVisibleOffset()) {
            Offset offsetM2067getCurrentDragPosition_m7T9E = manager.m2067getCurrentDragPosition_m7T9E();
            Intrinsics.checkNotNull(offsetM2067getCurrentDragPosition_m7T9E);
            long localDragPosition = selectableCoordinates.mo6792localPositionOfR5De75A(containerCoordinates, offsetM2067getCurrentDragPosition_m7T9E.m5078unboximpl());
            int bits$iv$iv$iv = (int) (localDragPosition >> 32);
            float dragX = Float.intBitsToFloat(bits$iv$iv$iv);
            long lineRange = selectable.mo2028getRangeOfLineContainingjx7JFs(offset);
            if (TextRange.m7567getCollapsedimpl(lineRange)) {
                lineStartX = selectable.getLineLeft(offset);
            } else {
                float lineStartX2 = selectable.getLineLeft(TextRange.m7573getStartimpl(lineRange));
                float lineEndX = selectable.getLineRight(TextRange.m7568getEndimpl(lineRange) - 1);
                float minX = Math.min(lineStartX2, lineEndX);
                float maxX = Math.max(lineStartX2, lineEndX);
                lineStartX = RangesKt.coerceIn(dragX, minX, maxX);
            }
            if (lineStartX == -1.0f) {
                return Offset.INSTANCE.m5083getUnspecifiedF1C5BW0();
            }
            if (!IntSize.m8319equalsimpl0(magnifierSize, IntSize.INSTANCE.m8326getZeroYbymL2g()) && Math.abs(dragX - lineStartX) > ((int) (magnifierSize >> 32)) / 2) {
                return Offset.INSTANCE.m5083getUnspecifiedF1C5BW0();
            }
            float lineCenterY = selectable.getCenterYForOffset(offset);
            if (lineCenterY == -1.0f) {
                return Offset.INSTANCE.m5083getUnspecifiedF1C5BW0();
            }
            float x$iv = lineStartX;
            long v1$iv$iv = Float.floatToRawIntBits(x$iv);
            long v2$iv$iv = Float.floatToRawIntBits(lineCenterY);
            return containerCoordinates.mo6792localPositionOfR5De75A(selectableCoordinates, Offset.m5060constructorimpl((v1$iv$iv << 32) | (v2$iv$iv & 4294967295L)));
        }
        return Offset.INSTANCE.m5083getUnspecifiedF1C5BW0();
    }

    public static final Rect visibleBounds(LayoutCoordinates $this$visibleBounds) {
        Rect boundsInWindow = LayoutCoordinatesKt.boundsInWindow$default($this$visibleBounds, false, 1, null);
        return RectKt.m5106Rect0a9Yr6o($this$visibleBounds.mo6800windowToLocalMKHz9U(boundsInWindow.m5103getTopLeftF1C5BW0()), $this$visibleBounds.mo6800windowToLocalMKHz9U(boundsInWindow.m5097getBottomRightF1C5BW0()));
    }

    /* JADX INFO: renamed from: containsInclusive-Uv8p0NA, reason: not valid java name */
    public static final boolean m2078containsInclusiveUv8p0NA(Rect $this$containsInclusive_u2dUv8p0NA, long offset) {
        float left = $this$containsInclusive_u2dUv8p0NA.getLeft();
        float right = $this$containsInclusive_u2dUv8p0NA.getRight();
        int bits$iv$iv$iv = (int) (offset >> 32);
        float fIntBitsToFloat = Float.intBitsToFloat(bits$iv$iv$iv);
        if (left <= fIntBitsToFloat && fIntBitsToFloat <= right) {
            float top = $this$containsInclusive_u2dUv8p0NA.getTop();
            float bottom = $this$containsInclusive_u2dUv8p0NA.getBottom();
            int bits$iv$iv$iv2 = (int) (4294967295L & offset);
            float fIntBitsToFloat2 = Float.intBitsToFloat(bits$iv$iv$iv2);
            if (top <= fIntBitsToFloat2 && fIntBitsToFloat2 <= bottom) {
                return true;
            }
        }
        return false;
    }
}
