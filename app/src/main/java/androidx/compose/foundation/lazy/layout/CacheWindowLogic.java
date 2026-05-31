package androidx.compose.foundation.lazy.layout;

import androidx.collection.IntIntMapKt;
import androidx.collection.IntObjectMapKt;
import androidx.collection.IntSetKt;
import androidx.collection.MutableIntIntMap;
import androidx.collection.MutableIntObjectMap;
import androidx.collection.MutableIntSet;
import androidx.compose.foundation.ComposeFoundationFlags;
import androidx.compose.foundation.lazy.layout.LazyLayoutPrefetchState;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.util.AndroidTrace_androidKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: CacheWindowLogic.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b%\b!\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0012\u0010!\u001a\u00020\"*\u00020#2\u0006\u0010$\u001a\u00020\u0013J\b\u0010%\u001a\u00020\"H\u0002J\n\u0010&\u001a\u00020\"*\u00020#J\f\u0010'\u001a\u00020\"*\u00020#H\u0002J\f\u0010(\u001a\u00020\"*\u00020#H\u0002J\u0006\u0010)\u001a\u00020\u0005J\u0014\u0010*\u001a\u00020\"*\u00020#2\u0006\u0010$\u001a\u00020\u0013H\u0002J\u0014\u0010+\u001a\u00020\"*\u00020#2\u0006\u0010$\u001a\u00020\u0013H\u0002J\u0014\u0010,\u001a\u00020\"*\u00020#2\u0006\u0010-\u001a\u00020\u0005H\u0002J\u0006\u0010.\u001a\u00020\"JD\u0010/\u001a\u00020\"*\u00020#2\u0006\u00100\u001a\u00020\u00152\u0006\u00101\u001a\u00020\u00152\u0006\u00102\u001a\u00020\u00152\u0006\u00103\u001a\u00020\u00152\u0006\u00104\u001a\u00020\u00152\u0006\u00105\u001a\u00020\u00132\u0006\u00106\u001a\u00020\u0005H\u0002J@\u00107\u001a\u00020\"2\u0006\u00100\u001a\u00020\u00152\u0006\u00101\u001a\u00020\u00152\u0006\u00103\u001a\u00020\u00152\u0006\u00104\u001a\u00020\u00152\u0006\u00108\u001a\u00020\u00152\u0006\u00105\u001a\u00020\u00132\u0006\u0010 \u001a\u00020\u0015H\u0002J\u001c\u00109\u001a\u00020\u0015*\u00020#2\u0006\u0010:\u001a\u00020\u00152\u0006\u0010;\u001a\u00020\u0005H\u0002J\u0018\u0010<\u001a\u00020\"2\u0006\u0010:\u001a\u00020\u00152\u0006\u0010=\u001a\u00020\u0015H\u0002J \u0010>\u001a\u00020\u00112\u0006\u0010:\u001a\u00020\u00152\u0006\u0010=\u001a\u00020\u00152\u0006\u0010?\u001a\u00020\u0001H\u0002J \u0010@\u001a\u00020\"2\u0006\u0010:\u001a\u00020\u00152\u0006\u0010?\u001a\u00020\u00012\u0006\u0010=\u001a\u00020\u0015H\u0002J\u0018\u0010A\u001a\u00020\"2\u0006\u0010:\u001a\u00020\u00152\u0006\u0010=\u001a\u00020\u0015H\u0002J\u0018\u0010B\u001a\u00020\"2\u0006\u0010C\u001a\u00020\u00152\u0006\u0010D\u001a\u00020\u0015H\u0002J\u001c\u0010E\u001a\u00020\"*\u00020#2\u0006\u0010:\u001a\u00020\u00152\u0006\u0010F\u001a\u00020\u0015H\u0002J\f\u0010G\u001a\u00020\"*\u00020#H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u0015@BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u001e\u0010\u001b\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u0015@BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001aR\u000e\u0010\u001d\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006H"}, d2 = {"Landroidx/compose/foundation/lazy/layout/CacheWindowLogic;", "", "cacheWindow", "Landroidx/compose/foundation/lazy/layout/LazyLayoutCacheWindow;", "enableInitialPrefetch", "", "<init>", "(Landroidx/compose/foundation/lazy/layout/LazyLayoutCacheWindow;Z)V", "prefetchWindowHandles", "Landroidx/collection/MutableIntObjectMap;", "", "Landroidx/compose/foundation/lazy/layout/LazyLayoutPrefetchState$PrefetchHandle;", "indicesToRemove", "Landroidx/collection/MutableIntSet;", "windowCache", "Landroidx/collection/MutableIntIntMap;", "windowCacheWithItems", "Landroidx/compose/foundation/lazy/layout/CachedItem;", "previousPassDelta", "", "previousPassItemCount", "", "hasUpdatedVisibleItemsOnce", "value", "prefetchWindowStartLine", "getPrefetchWindowStartLine$foundation", "()I", "prefetchWindowEndLine", "getPrefetchWindowEndLine$foundation", "prefetchWindowStartExtraSpace", "prefetchWindowEndExtraSpace", "shouldRefillWindow", "itemsCount", "onScroll", "", "Landroidx/compose/foundation/lazy/layout/CacheWindowScope;", "delta", "traceWindowInfo", "onVisibleItemsUpdated", "onDatasetChanged", "onDatasetChangedWithoutFix", "hasValidBounds", "fillCacheWindowBackward", "fillCacheWindowForward", "refillWindow", "refillForward", "resetStrategy", "onPrefetchForward", "visibleWindowStart", "visibleWindowEnd", "prefetchForwardWindow", "mainAxisExtraSpaceEnd", "mainAxisExtraSpaceStart", "scrollDelta", "applyForwardPrefetch", "onKeepAround", "keepAroundWindow", "getItemSizeOrPrefetch", "index", "isUrgent", "cachePrefetchedItem", "size", "updateOrCreateCachedItem", "key", "cacheVisibleItemsInfo", "cacheVisibleItemsInfoWithoutFix", "removeOutOfBoundsItems", "startLine", "endLine", "onItemPrefetched", "itemSize", "scheduleNextItemIfNeeded", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
public abstract class CacheWindowLogic {
    public static final int $stable = 8;
    private final LazyLayoutCacheWindow cacheWindow;
    private final boolean enableInitialPrefetch;
    private boolean hasUpdatedVisibleItemsOnce;
    private final MutableIntSet indicesToRemove;
    private int itemsCount;
    private int prefetchWindowEndExtraSpace;
    private int prefetchWindowEndLine;
    private final MutableIntObjectMap<List<LazyLayoutPrefetchState.PrefetchHandle>> prefetchWindowHandles;
    private int prefetchWindowStartExtraSpace;
    private int prefetchWindowStartLine;
    private float previousPassDelta;
    private int previousPassItemCount;
    private boolean shouldRefillWindow;
    private final MutableIntIntMap windowCache;
    private final MutableIntObjectMap<CachedItem> windowCacheWithItems;

    public CacheWindowLogic(LazyLayoutCacheWindow cacheWindow, boolean enableInitialPrefetch) {
        this.cacheWindow = cacheWindow;
        this.enableInitialPrefetch = enableInitialPrefetch;
        this.prefetchWindowHandles = IntObjectMapKt.mutableIntObjectMapOf();
        this.indicesToRemove = IntSetKt.mutableIntSetOf();
        this.windowCache = IntIntMapKt.mutableIntIntMapOf();
        this.windowCacheWithItems = IntObjectMapKt.mutableIntObjectMapOf();
        this.previousPassItemCount = -1;
        this.prefetchWindowStartLine = Integer.MAX_VALUE;
        this.prefetchWindowEndLine = Integer.MIN_VALUE;
    }

    public /* synthetic */ CacheWindowLogic(LazyLayoutCacheWindow lazyLayoutCacheWindow, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(lazyLayoutCacheWindow, (i & 2) != 0 ? true : z);
    }

    /* JADX INFO: renamed from: getPrefetchWindowStartLine$foundation, reason: from getter */
    public final int getPrefetchWindowStartLine() {
        return this.prefetchWindowStartLine;
    }

    /* JADX INFO: renamed from: getPrefetchWindowEndLine$foundation, reason: from getter */
    public final int getPrefetchWindowEndLine() {
        return this.prefetchWindowEndLine;
    }

    public final void onScroll(CacheWindowScope $this$onScroll, float delta) {
        traceWindowInfo();
        fillCacheWindowBackward($this$onScroll, delta);
        fillCacheWindowForward($this$onScroll, delta);
        this.previousPassDelta = delta;
        traceWindowInfo();
    }

    private final void traceWindowInfo() {
        AndroidTrace_androidKt.traceValue("prefetchWindowStartExtraSpace", this.prefetchWindowStartExtraSpace);
        AndroidTrace_androidKt.traceValue("prefetchWindowEndExtraSpace", this.prefetchWindowEndExtraSpace);
        AndroidTrace_androidKt.traceValue("prefetchWindowStartIndex", this.prefetchWindowStartLine);
        AndroidTrace_androidKt.traceValue("prefetchWindowEndIndex", this.prefetchWindowEndLine);
    }

    public final void onVisibleItemsUpdated(CacheWindowScope $this$onVisibleItemsUpdated) {
        if (!this.hasUpdatedVisibleItemsOnce && this.enableInitialPrefetch) {
            LazyLayoutCacheWindow $this$onVisibleItemsUpdated_u24lambda_u241 = this.cacheWindow;
            Density density = $this$onVisibleItemsUpdated.getDensity();
            int prefetchForwardWindow = density != null ? $this$onVisibleItemsUpdated_u24lambda_u241.calculateAheadWindow(density, $this$onVisibleItemsUpdated.getMainAxisViewportSize()) : 0;
            if (prefetchForwardWindow != 0) {
                this.shouldRefillWindow = true;
            }
            this.hasUpdatedVisibleItemsOnce = true;
        }
        if (this.previousPassItemCount != -1 && this.previousPassItemCount != $this$onVisibleItemsUpdated.getTotalItemsCount()) {
            if (ComposeFoundationFlags.isCacheWindowRefillFixEnabled) {
                onDatasetChanged($this$onVisibleItemsUpdated);
            } else {
                onDatasetChangedWithoutFix($this$onVisibleItemsUpdated);
            }
        }
        this.itemsCount = $this$onVisibleItemsUpdated.getTotalItemsCount();
        if (!$this$onVisibleItemsUpdated.getHasVisibleItems()) {
            resetStrategy();
        } else {
            int visibleLineCount = $this$onVisibleItemsUpdated.getVisibleLineCount();
            for (int i = 0; i < visibleLineCount; i++) {
                int it$iv = i;
                int index = $this$onVisibleItemsUpdated.getVisibleItemLine(it$iv);
                Object key = $this$onVisibleItemsUpdated.getVisibleLineKey(it$iv);
                int mainAxisSize = $this$onVisibleItemsUpdated.getVisibleItemSize(it$iv);
                if (ComposeFoundationFlags.isCacheWindowRefillFixEnabled) {
                    if (index != -1) {
                        cacheVisibleItemsInfo(index, key, mainAxisSize);
                    }
                } else if (index != -1) {
                    cacheVisibleItemsInfoWithoutFix(index, mainAxisSize);
                }
            }
            if (this.shouldRefillWindow) {
                refillWindow($this$onVisibleItemsUpdated, this.previousPassDelta <= 0.0f);
                this.shouldRefillWindow = false;
            }
        }
        this.previousPassItemCount = $this$onVisibleItemsUpdated.getTotalItemsCount();
    }

    private final void onDatasetChanged(CacheWindowScope $this$onDatasetChanged) {
        this.shouldRefillWindow = true;
        if ($this$onDatasetChanged.getHasVisibleItems()) {
            this.prefetchWindowStartLine = RangesKt.coerceAtLeast(this.prefetchWindowStartLine, 0);
            int lastLineIndex = $this$onDatasetChanged.getLastLineIndex();
            if (lastLineIndex != -1) {
                this.prefetchWindowEndLine = RangesKt.coerceAtMost(this.prefetchWindowEndLine, lastLineIndex);
            }
            if (this.previousPassDelta <= 0.0f) {
                removeOutOfBoundsItems($this$onDatasetChanged.getLastVisibleLineIndex(), this.itemsCount - 1);
            } else {
                removeOutOfBoundsItems(0, $this$onDatasetChanged.getFirstVisibleLineIndex());
            }
        }
    }

    private final void onDatasetChangedWithoutFix(CacheWindowScope $this$onDatasetChangedWithoutFix) {
        this.shouldRefillWindow = true;
        this.prefetchWindowStartLine = RangesKt.coerceAtLeast(this.prefetchWindowStartLine, 0);
        int lastLineIndex = $this$onDatasetChangedWithoutFix.getLastLineIndex();
        if (lastLineIndex != -1) {
            this.prefetchWindowEndLine = RangesKt.coerceAtMost(this.prefetchWindowEndLine, lastLineIndex);
        }
        removeOutOfBoundsItems(this.prefetchWindowEndLine, this.itemsCount - 1);
    }

    public final boolean hasValidBounds() {
        return (this.prefetchWindowStartLine == Integer.MAX_VALUE || this.prefetchWindowEndLine == Integer.MIN_VALUE) ? false : true;
    }

    private final void fillCacheWindowBackward(CacheWindowScope $this$fillCacheWindowBackward, float delta) {
        if ($this$fillCacheWindowBackward.getHasVisibleItems()) {
            int viewport = $this$fillCacheWindowBackward.getMainAxisViewportSize();
            LazyLayoutCacheWindow $this$fillCacheWindowBackward_u24lambda_u240 = this.cacheWindow;
            Density density = $this$fillCacheWindowBackward.getDensity();
            int keepAroundWindow = density != null ? $this$fillCacheWindowBackward_u24lambda_u240.calculateBehindWindow(density, viewport) : 0;
            this.itemsCount = $this$fillCacheWindowBackward.getTotalItemsCount();
            onKeepAround($this$fillCacheWindowBackward.getFirstVisibleLineIndex(), $this$fillCacheWindowBackward.getLastVisibleLineIndex(), $this$fillCacheWindowBackward.getMainAxisExtraSpaceEnd(), $this$fillCacheWindowBackward.getMainAxisExtraSpaceStart(), keepAroundWindow, delta, $this$fillCacheWindowBackward.getTotalItemsCount());
        }
    }

    private final void fillCacheWindowForward(CacheWindowScope $this$fillCacheWindowForward, float delta) {
        if ($this$fillCacheWindowForward.getHasVisibleItems()) {
            int viewport = $this$fillCacheWindowForward.getMainAxisViewportSize();
            LazyLayoutCacheWindow $this$fillCacheWindowForward_u24lambda_u240 = this.cacheWindow;
            Density density = $this$fillCacheWindowForward.getDensity();
            int prefetchForwardWindow = density != null ? $this$fillCacheWindowForward_u24lambda_u240.calculateAheadWindow(density, viewport) : 0;
            int firstVisibleLineIndex = $this$fillCacheWindowForward.getFirstVisibleLineIndex();
            boolean z = false;
            int prefetchForwardWindow2 = prefetchForwardWindow;
            int prefetchForwardWindow3 = $this$fillCacheWindowForward.getLastVisibleLineIndex();
            int mainAxisExtraSpaceStart = $this$fillCacheWindowForward.getMainAxisExtraSpaceStart();
            int mainAxisExtraSpaceEnd = $this$fillCacheWindowForward.getMainAxisExtraSpaceEnd();
            if (delta <= 0.0f) {
                z = true;
            }
            onPrefetchForward($this$fillCacheWindowForward, firstVisibleLineIndex, prefetchForwardWindow3, prefetchForwardWindow2, mainAxisExtraSpaceEnd, mainAxisExtraSpaceStart, delta, z);
        }
    }

    private final void refillWindow(CacheWindowScope $this$refillWindow, boolean refillForward) {
        if ($this$refillWindow.getHasVisibleItems()) {
            int viewport = $this$refillWindow.getMainAxisViewportSize();
            LazyLayoutCacheWindow $this$refillWindow_u24lambda_u240 = this.cacheWindow;
            Density density = $this$refillWindow.getDensity();
            int prefetchForwardWindow = density != null ? $this$refillWindow_u24lambda_u240.calculateAheadWindow(density, viewport) : 0;
            onPrefetchForward($this$refillWindow, $this$refillWindow.getFirstVisibleLineIndex(), $this$refillWindow.getLastVisibleLineIndex(), prefetchForwardWindow, $this$refillWindow.getMainAxisExtraSpaceEnd(), $this$refillWindow.getMainAxisExtraSpaceStart(), 0.0f, refillForward);
        }
    }

    public final void resetStrategy() {
        int i;
        this.prefetchWindowStartLine = Integer.MAX_VALUE;
        this.prefetchWindowEndLine = Integer.MIN_VALUE;
        boolean z = false;
        this.prefetchWindowStartExtraSpace = 0;
        this.prefetchWindowEndExtraSpace = 0;
        this.shouldRefillWindow = false;
        this.windowCache.clear();
        this.windowCacheWithItems.clear();
        MutableIntObjectMap<List<LazyLayoutPrefetchState.PrefetchHandle>> mutableIntObjectMap = this.prefetchWindowHandles;
        MutableIntObjectMap<List<LazyLayoutPrefetchState.PrefetchHandle>> this_$iv$iv = mutableIntObjectMap;
        long[] m$iv$iv = this_$iv$iv.metadata;
        int lastIndex$iv$iv = m$iv$iv.length - 2;
        int i$iv$iv = 0;
        if (0 > lastIndex$iv$iv) {
            return;
        }
        while (true) {
            long slot$iv$iv = m$iv$iv[i$iv$iv];
            long $this$maskEmptyOrDeleted$iv$iv$iv = ((~slot$iv$iv) << 7) & slot$iv$iv & (-9187201950435737472L);
            if ($this$maskEmptyOrDeleted$iv$iv$iv != -9187201950435737472L) {
                int i2 = 8;
                int bitCount$iv$iv = 8 - ((~(i$iv$iv - lastIndex$iv$iv)) >>> 31);
                int j$iv$iv = 0;
                while (j$iv$iv < bitCount$iv$iv) {
                    long value$iv$iv$iv = 255 & slot$iv$iv;
                    if (!(value$iv$iv$iv < 128 ? true : z)) {
                        i = i2;
                    } else {
                        int index$iv$iv = (i$iv$iv << 3) + j$iv$iv;
                        int i3 = mutableIntObjectMap.keys[index$iv$iv];
                        List $this$fastForEach$iv = (List) mutableIntObjectMap.values[index$iv$iv];
                        List $this$fastForEach$iv2 = $this$fastForEach$iv;
                        i = i2;
                        int size = $this$fastForEach$iv2.size();
                        int index$iv = 0;
                        while (index$iv < size) {
                            List value = $this$fastForEach$iv;
                            List value2 = $this$fastForEach$iv2;
                            Object item$iv = value2.get(index$iv);
                            LazyLayoutPrefetchState.PrefetchHandle it = (LazyLayoutPrefetchState.PrefetchHandle) item$iv;
                            it.cancel();
                            index$iv++;
                            $this$fastForEach$iv2 = value2;
                            $this$fastForEach$iv = value;
                        }
                        mutableIntObjectMap.removeValueAt(index$iv$iv);
                    }
                    slot$iv$iv >>= i;
                    j$iv$iv++;
                    z = false;
                    i2 = i;
                }
                if (bitCount$iv$iv != i2) {
                    return;
                }
            }
            if (i$iv$iv == lastIndex$iv$iv) {
                return;
            }
            i$iv$iv++;
            z = false;
        }
    }

    private final void onPrefetchForward(CacheWindowScope $this$onPrefetchForward, int visibleWindowStart, int visibleWindowEnd, int prefetchForwardWindow, int mainAxisExtraSpaceEnd, int mainAxisExtraSpaceStart, float scrollDelta, boolean applyForwardPrefetch) {
        boolean scrollDeltaCheck;
        boolean scrollDeltaCheck2;
        boolean changedScrollDirection = !(Math.signum(scrollDelta) == Math.signum(this.previousPassDelta));
        if (applyForwardPrefetch) {
            if (changedScrollDirection || this.shouldRefillWindow) {
                this.prefetchWindowEndExtraSpace = prefetchForwardWindow - mainAxisExtraSpaceEnd;
                this.prefetchWindowEndLine = visibleWindowEnd;
            } else {
                this.prefetchWindowEndExtraSpace = RangesKt.coerceAtMost(this.prefetchWindowEndExtraSpace + MathKt.roundToInt(Math.abs(scrollDelta)), prefetchForwardWindow - mainAxisExtraSpaceEnd);
            }
            while (this.prefetchWindowEndExtraSpace > 0 && $this$onPrefetchForward.getLastIndexInLine(this.prefetchWindowEndLine) != -1 && $this$onPrefetchForward.getLastIndexInLine(this.prefetchWindowEndLine) < this.itemsCount - 1) {
                if (ComposeFoundationFlags.isCacheWindowRefillFixEnabled) {
                    scrollDeltaCheck2 = !((scrollDelta > 0.0f ? 1 : (scrollDelta == 0.0f ? 0 : -1)) == 0);
                } else {
                    scrollDeltaCheck2 = true;
                }
                boolean isUrgent = this.prefetchWindowEndLine + 1 == visibleWindowEnd + 1 && scrollDeltaCheck2 && Math.abs(scrollDelta) >= ((float) mainAxisExtraSpaceEnd);
                int $i$f$debugLog = this.prefetchWindowEndLine;
                int itemSize = getItemSizeOrPrefetch($this$onPrefetchForward, $i$f$debugLog + 1, isUrgent);
                if (itemSize != -1) {
                    this.prefetchWindowEndLine++;
                    this.prefetchWindowEndExtraSpace -= itemSize;
                } else {
                    return;
                }
            }
            return;
        }
        if (changedScrollDirection || this.shouldRefillWindow) {
            this.prefetchWindowStartExtraSpace = prefetchForwardWindow - mainAxisExtraSpaceStart;
            this.prefetchWindowStartLine = visibleWindowStart;
        } else {
            this.prefetchWindowStartExtraSpace = RangesKt.coerceAtMost(this.prefetchWindowStartExtraSpace + MathKt.roundToInt(Math.abs(scrollDelta)), prefetchForwardWindow - mainAxisExtraSpaceStart);
        }
        while (this.prefetchWindowStartExtraSpace > 0 && this.prefetchWindowStartLine > 0) {
            if (ComposeFoundationFlags.isCacheWindowRefillFixEnabled) {
                scrollDeltaCheck = !((scrollDelta > 0.0f ? 1 : (scrollDelta == 0.0f ? 0 : -1)) == 0);
            } else {
                scrollDeltaCheck = true;
            }
            boolean isUrgent2 = this.prefetchWindowStartLine - 1 == visibleWindowStart + (-1) && scrollDeltaCheck && Math.abs(scrollDelta) >= ((float) mainAxisExtraSpaceStart);
            int $i$f$debugLog2 = this.prefetchWindowStartLine;
            int itemSize2 = getItemSizeOrPrefetch($this$onPrefetchForward, $i$f$debugLog2 - 1, isUrgent2);
            if (itemSize2 != -1) {
                this.prefetchWindowStartLine--;
                this.prefetchWindowStartExtraSpace -= itemSize2;
            } else {
                return;
            }
        }
    }

    private final void onKeepAround(int visibleWindowStart, int visibleWindowEnd, int mainAxisExtraSpaceEnd, int mainAxisExtraSpaceStart, int keepAroundWindow, float scrollDelta, int itemsCount) {
        int item;
        int item2;
        if (scrollDelta <= 0.0f) {
            this.prefetchWindowStartExtraSpace = keepAroundWindow - mainAxisExtraSpaceStart;
            this.prefetchWindowStartLine = visibleWindowStart;
            while (this.prefetchWindowStartExtraSpace > 0 && this.prefetchWindowStartLine > 0) {
                if (ComposeFoundationFlags.isCacheWindowRefillFixEnabled) {
                    if (!this.windowCacheWithItems.containsKey(this.prefetchWindowStartLine - 1)) {
                        break;
                    }
                    CachedItem cachedItem = this.windowCacheWithItems.get(this.prefetchWindowStartLine - 1);
                    Intrinsics.checkNotNull(cachedItem);
                    item2 = cachedItem.getMainAxisSize();
                    this.prefetchWindowStartLine--;
                    this.prefetchWindowStartExtraSpace -= item2;
                } else {
                    if (!this.windowCache.containsKey(this.prefetchWindowStartLine - 1)) {
                        break;
                    }
                    item2 = this.windowCache.get(this.prefetchWindowStartLine - 1);
                    this.prefetchWindowStartLine--;
                    this.prefetchWindowStartExtraSpace -= item2;
                }
            }
            removeOutOfBoundsItems(0, this.prefetchWindowStartLine - 1);
            return;
        }
        this.prefetchWindowEndExtraSpace = keepAroundWindow - mainAxisExtraSpaceEnd;
        this.prefetchWindowEndLine = visibleWindowEnd;
        while (this.prefetchWindowEndExtraSpace > 0 && this.prefetchWindowEndLine < itemsCount - 1) {
            if (ComposeFoundationFlags.isCacheWindowRefillFixEnabled) {
                if (!this.windowCacheWithItems.containsKey(this.prefetchWindowEndLine + 1)) {
                    break;
                }
                CachedItem cachedItem2 = this.windowCacheWithItems.get(this.prefetchWindowEndLine + 1);
                Intrinsics.checkNotNull(cachedItem2);
                item = cachedItem2.getMainAxisSize();
                this.prefetchWindowEndLine++;
                this.prefetchWindowEndExtraSpace -= item;
            } else {
                if (!this.windowCache.containsKey(this.prefetchWindowEndLine + 1)) {
                    break;
                }
                item = this.windowCache.get(this.prefetchWindowEndLine + 1);
                this.prefetchWindowEndLine++;
                this.prefetchWindowEndExtraSpace -= item;
            }
        }
        removeOutOfBoundsItems(this.prefetchWindowEndLine + 1, itemsCount - 1);
    }

    private final int getItemSizeOrPrefetch(final CacheWindowScope $this$getItemSizeOrPrefetch, int index, boolean isUrgent) {
        List<LazyLayoutPrefetchState.PrefetchHandle> list;
        List<LazyLayoutPrefetchState.PrefetchHandle> list2;
        List<LazyLayoutPrefetchState.PrefetchHandle> list3;
        List<LazyLayoutPrefetchState.PrefetchHandle> list4;
        if (ComposeFoundationFlags.isCacheWindowRefillFixEnabled) {
            if (this.windowCacheWithItems.containsKey(index)) {
                CachedItem cachedItem = this.windowCacheWithItems.get(index);
                Intrinsics.checkNotNull(cachedItem);
                return cachedItem.getMainAxisSize();
            }
            if (this.prefetchWindowHandles.containsKey(index)) {
                if (!isUrgent || (list4 = this.prefetchWindowHandles.get(index)) == null) {
                    return -1;
                }
                int size = list4.size();
                for (int index$iv = 0; index$iv < size; index$iv++) {
                    Object item$iv = list4.get(index$iv);
                    LazyLayoutPrefetchState.PrefetchHandle it = (LazyLayoutPrefetchState.PrefetchHandle) item$iv;
                    it.markAsUrgent();
                }
                return -1;
            }
            this.prefetchWindowHandles.set(index, $this$getItemSizeOrPrefetch.schedulePrefetch(index, new Function2() { // from class: androidx.compose.foundation.lazy.layout.CacheWindowLogic$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return CacheWindowLogic.getItemSizeOrPrefetch$lambda$4(this.f$0, $this$getItemSizeOrPrefetch, ((Integer) obj).intValue(), ((Integer) obj2).intValue());
                }
            }));
            if (!isUrgent || (list3 = this.prefetchWindowHandles.get(index)) == null) {
                return -1;
            }
            int size2 = list3.size();
            for (int index$iv2 = 0; index$iv2 < size2; index$iv2++) {
                Object item$iv2 = list3.get(index$iv2);
                LazyLayoutPrefetchState.PrefetchHandle it2 = (LazyLayoutPrefetchState.PrefetchHandle) item$iv2;
                it2.markAsUrgent();
            }
            return -1;
        }
        if (this.windowCache.containsKey(index)) {
            return this.windowCache.get(index);
        }
        if (this.prefetchWindowHandles.containsKey(index)) {
            if (!isUrgent || (list2 = this.prefetchWindowHandles.get(index)) == null) {
                return -1;
            }
            int size3 = list2.size();
            for (int index$iv3 = 0; index$iv3 < size3; index$iv3++) {
                Object item$iv3 = list2.get(index$iv3);
                LazyLayoutPrefetchState.PrefetchHandle it3 = (LazyLayoutPrefetchState.PrefetchHandle) item$iv3;
                it3.markAsUrgent();
            }
            return -1;
        }
        this.prefetchWindowHandles.set(index, $this$getItemSizeOrPrefetch.schedulePrefetch(index, new Function2() { // from class: androidx.compose.foundation.lazy.layout.CacheWindowLogic$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return CacheWindowLogic.getItemSizeOrPrefetch$lambda$10(this.f$0, $this$getItemSizeOrPrefetch, ((Integer) obj).intValue(), ((Integer) obj2).intValue());
            }
        }));
        if (!isUrgent || (list = this.prefetchWindowHandles.get(index)) == null) {
            return -1;
        }
        int size4 = list.size();
        for (int index$iv4 = 0; index$iv4 < size4; index$iv4++) {
            Object item$iv4 = list.get(index$iv4);
            LazyLayoutPrefetchState.PrefetchHandle it4 = (LazyLayoutPrefetchState.PrefetchHandle) item$iv4;
            it4.markAsUrgent();
        }
        return -1;
    }

    static final Unit getItemSizeOrPrefetch$lambda$4(CacheWindowLogic this$0, CacheWindowScope $this_getItemSizeOrPrefetch, int prefetchedIndex, int size) {
        this$0.onItemPrefetched($this_getItemSizeOrPrefetch, prefetchedIndex, size);
        return Unit.INSTANCE;
    }

    static final Unit getItemSizeOrPrefetch$lambda$10(CacheWindowLogic this$0, CacheWindowScope $this_getItemSizeOrPrefetch, int prefetchedIndex, int size) {
        this$0.onItemPrefetched($this_getItemSizeOrPrefetch, prefetchedIndex, size);
        return Unit.INSTANCE;
    }

    private final void cachePrefetchedItem(int index, int size) {
        if (ComposeFoundationFlags.isCacheWindowRefillFixEnabled) {
            this.windowCacheWithItems.set(index, updateOrCreateCachedItem(index, size, CachedItem.INSTANCE));
        } else {
            this.windowCache.set(index, size);
        }
        if (index > this.prefetchWindowEndLine) {
            this.prefetchWindowEndLine = index;
            this.prefetchWindowEndExtraSpace -= size;
        } else if (index < this.prefetchWindowStartLine) {
            this.prefetchWindowStartLine = index;
            this.prefetchWindowStartExtraSpace -= size;
        }
    }

    private final CachedItem updateOrCreateCachedItem(int index, int size, Object key) {
        CachedItem cachedItem = this.windowCacheWithItems.get(index);
        if (cachedItem != null) {
            cachedItem.setMainAxisSize(size);
            cachedItem.setKey(key);
            return cachedItem;
        }
        return new CachedItem(key, size);
    }

    private final void cacheVisibleItemsInfo(int index, Object key, int size) {
        if (this.windowCacheWithItems.containsKey(index)) {
            CachedItem cachedItem = this.windowCacheWithItems.get(index);
            Intrinsics.checkNotNull(cachedItem);
            int cachedSize = cachedItem.getMainAxisSize();
            CachedItem cachedItem2 = this.windowCacheWithItems.get(index);
            Intrinsics.checkNotNull(cachedItem2);
            Object cachedKey = cachedItem2.getKey();
            if (cachedSize != size || !Intrinsics.areEqual(cachedKey, key)) {
                this.shouldRefillWindow = true;
            }
        }
        this.windowCacheWithItems.set(index, updateOrCreateCachedItem(index, size, key));
        this.prefetchWindowStartLine = Math.min(this.prefetchWindowStartLine, index);
        this.prefetchWindowEndLine = Math.max(this.prefetchWindowEndLine, index);
        List<LazyLayoutPrefetchState.PrefetchHandle> listRemove = this.prefetchWindowHandles.remove(index);
        if (listRemove == null) {
            return;
        }
        int size2 = listRemove.size();
        for (int index$iv = 0; index$iv < size2; index$iv++) {
            Object item$iv = listRemove.get(index$iv);
            LazyLayoutPrefetchState.PrefetchHandle it = (LazyLayoutPrefetchState.PrefetchHandle) item$iv;
            it.cancel();
        }
    }

    private final void cacheVisibleItemsInfoWithoutFix(int index, int size) {
        if (this.windowCache.containsKey(index) && this.windowCache.get(index) != size) {
            this.shouldRefillWindow = true;
        }
        this.windowCache.set(index, size);
        this.prefetchWindowStartLine = Math.min(this.prefetchWindowStartLine, index);
        this.prefetchWindowEndLine = Math.max(this.prefetchWindowEndLine, index);
        List<LazyLayoutPrefetchState.PrefetchHandle> listRemove = this.prefetchWindowHandles.remove(index);
        if (listRemove == null) {
            return;
        }
        int size2 = listRemove.size();
        for (int index$iv = 0; index$iv < size2; index$iv++) {
            Object item$iv = listRemove.get(index$iv);
            LazyLayoutPrefetchState.PrefetchHandle it = (LazyLayoutPrefetchState.PrefetchHandle) item$iv;
            it.cancel();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:85:0x0194  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void removeOutOfBoundsItems(int r36, int r37) {
        /*
            Method dump skipped, instruction units count: 628
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.lazy.layout.CacheWindowLogic.removeOutOfBoundsItems(int, int):void");
    }

    private final void onItemPrefetched(CacheWindowScope $this$onItemPrefetched, int index, int itemSize) {
        cachePrefetchedItem(index, itemSize);
        scheduleNextItemIfNeeded($this$onItemPrefetched);
        traceWindowInfo();
    }

    private final void scheduleNextItemIfNeeded(final CacheWindowScope $this$scheduleNextItemIfNeeded) {
        int nextPrefetchableLineIndex = -1;
        if (Math.signum(this.previousPassDelta) <= 0.0f) {
            if (this.prefetchWindowEndExtraSpace > 0) {
                nextPrefetchableLineIndex = this.prefetchWindowEndLine + 1;
            }
        } else if (Math.signum(this.previousPassDelta) > 0.0f && this.prefetchWindowStartExtraSpace > 0) {
            nextPrefetchableLineIndex = this.prefetchWindowStartLine - 1;
        }
        if (nextPrefetchableLineIndex > 0 && $this$scheduleNextItemIfNeeded.getLastIndexInLine(nextPrefetchableLineIndex) != -1 && $this$scheduleNextItemIfNeeded.getLastIndexInLine(nextPrefetchableLineIndex) < this.itemsCount) {
            this.prefetchWindowHandles.set(nextPrefetchableLineIndex, $this$scheduleNextItemIfNeeded.schedulePrefetch(nextPrefetchableLineIndex, new Function2() { // from class: androidx.compose.foundation.lazy.layout.CacheWindowLogic$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return CacheWindowLogic.scheduleNextItemIfNeeded$lambda$1(this.f$0, $this$scheduleNextItemIfNeeded, ((Integer) obj).intValue(), ((Integer) obj2).intValue());
                }
            }));
        }
    }

    static final Unit scheduleNextItemIfNeeded$lambda$1(CacheWindowLogic this$0, CacheWindowScope $this_scheduleNextItemIfNeeded, int index, int mainAxisSize) {
        this$0.onItemPrefetched($this_scheduleNextItemIfNeeded, index, mainAxisSize);
        return Unit.INSTANCE;
    }
}
