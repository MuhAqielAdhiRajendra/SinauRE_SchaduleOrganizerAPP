package androidx.compose.material3.carousel;

import androidx.compose.ui.unit.Density;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: Keylines.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000.\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\u001aD\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\u0005H\u0000\u001a(\u0010\f\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0005H\u0000\u001aU\u0010\u000e\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u000f\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\u0005H\u0000¢\u0006\u0002\u0010\u0012\u001a0\u0010\u0013\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u0017H\u0000\u001a0\u0010\u0018\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u0017H\u0000\u001a \u0010\u0019\u001a\u00020\u00052\u0006\u0010\u001a\u001a\u00020\u00052\u0006\u0010\u001b\u001a\u00020\u00052\u0006\u0010\u001c\u001a\u00020\u0005H\u0002¨\u0006\u001d"}, d2 = {"multiBrowseKeylineList", "Landroidx/compose/material3/carousel/KeylineList;", "density", "Landroidx/compose/ui/unit/Density;", "carouselMainAxisSize", "", "preferredItemSize", "itemSpacing", "itemCount", "", "minSmallItemSize", "maxSmallItemSize", "uncontainedKeylineList", "itemSize", "heroKeylineList", "maxItemSize", "isCentered", "", "(Landroidx/compose/ui/unit/Density;FLjava/lang/Float;FIZFF)Landroidx/compose/material3/carousel/KeylineList;", "createLeftAlignedKeylineList", "leftAnchorSize", "rightAnchorSize", "arrangement", "Landroidx/compose/material3/carousel/Arrangement;", "createCenterAlignedKeylineList", "calculateMediumChildSize", "minimumMediumSize", "largeItemSize", "remainingSpace", "material3"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class KeylinesKt {
    public static /* synthetic */ KeylineList multiBrowseKeylineList$default(Density $this$multiBrowseKeylineList_u24lambda_u240, float f, float f2, float f3, int i, float f4, float f5, int i2, Object obj) {
        float fMo432toPx0680j_4;
        float fMo432toPx0680j_42;
        if ((i2 & 32) == 0) {
            fMo432toPx0680j_4 = f4;
        } else {
            fMo432toPx0680j_4 = $this$multiBrowseKeylineList_u24lambda_u240.mo432toPx0680j_4(CarouselDefaults.INSTANCE.m3414getMinSmallItemSizeD9Ej5fM());
        }
        if ((i2 & 64) == 0) {
            fMo432toPx0680j_42 = f5;
        } else {
            fMo432toPx0680j_42 = $this$multiBrowseKeylineList_u24lambda_u240.mo432toPx0680j_4(CarouselDefaults.INSTANCE.m3413getMaxSmallItemSizeD9Ej5fM());
        }
        return multiBrowseKeylineList($this$multiBrowseKeylineList_u24lambda_u240, f, f2, f3, i, fMo432toPx0680j_4, fMo432toPx0680j_42);
    }

    public static final KeylineList multiBrowseKeylineList(Density density, float carouselMainAxisSize, float preferredItemSize, float itemSpacing, int itemCount, float minSmallItemSize, float maxSmallItemSize) {
        float f;
        Arrangement arrangement;
        if (!(carouselMainAxisSize == 0.0f)) {
            if (!(preferredItemSize == 0.0f)) {
                int[] smallCounts = {1};
                int[] mediumCounts = {1, 0};
                float targetLargeSize = Math.min(preferredItemSize, carouselMainAxisSize);
                float targetSmallSize = RangesKt.coerceIn(targetLargeSize / 3.0f, minSmallItemSize, maxSmallItemSize);
                float targetMediumSize = (targetLargeSize + targetSmallSize) / 2.0f;
                if (carouselMainAxisSize < 2.0f * minSmallItemSize) {
                    smallCounts = new int[]{0};
                }
                float minAvailableLargeSpace = (carouselMainAxisSize - (ArraysKt.maxOrThrow(mediumCounts) * targetMediumSize)) - (ArraysKt.maxOrThrow(smallCounts) * maxSmallItemSize);
                int minLargeCount = Math.max(1, (int) Math.floor(minAvailableLargeSpace / targetLargeSize));
                int maxLargeCount = (int) Math.ceil(carouselMainAxisSize / targetLargeSize);
                int i = (maxLargeCount - minLargeCount) + 1;
                int[] largeCounts = new int[i];
                for (int i2 = 0; i2 < i; i2++) {
                    largeCounts[i2] = maxLargeCount - i2;
                }
                float anchorSize = density.mo432toPx0680j_4(CarouselDefaults.INSTANCE.m3412getAnchorSizeD9Ej5fM$material3());
                Arrangement arrangement2 = Arrangement.INSTANCE.findLowestCostArrangement(carouselMainAxisSize, itemSpacing, targetSmallSize, minSmallItemSize, maxSmallItemSize, smallCounts, targetMediumSize, mediumCounts, targetLargeSize, largeCounts);
                if (arrangement2 == null || arrangement2.itemCount() <= itemCount) {
                    f = carouselMainAxisSize;
                    arrangement = arrangement2;
                } else {
                    int keylineSurplus = arrangement2.itemCount() - itemCount;
                    int smallCount = arrangement2.getSmallCount();
                    int smallCount2 = smallCount;
                    int keylineSurplus2 = arrangement2.getMediumCount();
                    for (int keylineSurplus3 = keylineSurplus; keylineSurplus3 > 0; keylineSurplus3--) {
                        if (smallCount2 > 0) {
                            smallCount2--;
                        } else if (keylineSurplus2 > 1) {
                            keylineSurplus2--;
                        }
                    }
                    int mediumCount = keylineSurplus2;
                    f = carouselMainAxisSize;
                    arrangement = Arrangement.INSTANCE.findLowestCostArrangement(f, itemSpacing, targetSmallSize, minSmallItemSize, maxSmallItemSize, new int[]{smallCount2}, targetMediumSize, new int[]{mediumCount}, targetLargeSize, largeCounts);
                }
                if (arrangement == null) {
                    return KeylineListKt.emptyKeylineList();
                }
                return createLeftAlignedKeylineList(f, itemSpacing, anchorSize, anchorSize, arrangement);
            }
        }
        return KeylineListKt.emptyKeylineList();
    }

    public static final KeylineList uncontainedKeylineList(Density density, float carouselMainAxisSize, float itemSize, float itemSpacing) {
        if (!(carouselMainAxisSize == 0.0f)) {
            if (!(itemSize == 0.0f)) {
                float largeItemSize = Math.min(itemSize + itemSpacing, carouselMainAxisSize);
                int largeCount = Math.max(1, (int) Math.floor(carouselMainAxisSize / largeItemSize));
                float remainingSpace = carouselMainAxisSize - (largeCount * largeItemSize);
                int mediumCount = remainingSpace > 0.0f ? 1 : 0;
                float defaultAnchorSize = density.mo432toPx0680j_4(CarouselDefaults.INSTANCE.m3412getAnchorSizeD9Ej5fM$material3());
                float mediumItemSize = calculateMediumChildSize(defaultAnchorSize, largeItemSize, remainingSpace);
                Arrangement arrangement = new Arrangement(0, 0.0f, 0, mediumItemSize, mediumCount, largeItemSize, largeCount);
                float xSmallSize = Math.min(defaultAnchorSize, itemSize);
                float leftAnchorSize = Math.max(xSmallSize, 0.5f * mediumItemSize);
                return createLeftAlignedKeylineList(carouselMainAxisSize, itemSpacing, leftAnchorSize, defaultAnchorSize, arrangement);
            }
        }
        return KeylineListKt.emptyKeylineList();
    }

    public static final KeylineList heroKeylineList(Density density, float carouselMainAxisSize, Float maxItemSize, float itemSpacing, int itemCount, boolean isCentered, float minSmallItemSize, float maxSmallItemSize) {
        if (carouselMainAxisSize == 0.0f) {
            return KeylineListKt.emptyKeylineList();
        }
        boolean shouldCenter = isCentered && itemCount >= 3;
        int[] smallCounts = new int[1];
        if (shouldCenter) {
            smallCounts[0] = 2;
        } else {
            smallCounts[0] = 1;
        }
        float targetLargeSize = Math.min(maxItemSize != null ? maxItemSize.floatValue() : carouselMainAxisSize, carouselMainAxisSize);
        float targetSmallSize = RangesKt.coerceIn(targetLargeSize / 3.0f, minSmallItemSize, maxSmallItemSize);
        float fullscreenThreshold = (ArraysKt.maxOrThrow(smallCounts) * minSmallItemSize) + (1.25f * minSmallItemSize);
        if (carouselMainAxisSize < fullscreenThreshold) {
            smallCounts = new int[]{0};
        }
        float minAvailableLargeSpace = carouselMainAxisSize - (ArraysKt.maxOrThrow(smallCounts) * minSmallItemSize);
        int minLargeCount = Math.max(1, (int) Math.floor(minAvailableLargeSpace / targetLargeSize));
        int maxLargeCount = (int) Math.ceil(carouselMainAxisSize / targetLargeSize);
        int i = (maxLargeCount - minLargeCount) + 1;
        int[] largeCounts = new int[i];
        for (int i2 = 0; i2 < i; i2++) {
            largeCounts[i2] = maxLargeCount - i2;
        }
        float anchorSize = density.mo432toPx0680j_4(CarouselDefaults.INSTANCE.m3412getAnchorSizeD9Ej5fM$material3());
        Arrangement arrangement = Arrangement.INSTANCE.findLowestCostArrangement(carouselMainAxisSize, itemSpacing, targetSmallSize, minSmallItemSize, maxSmallItemSize, smallCounts, 0.0f, new int[]{0}, targetLargeSize, largeCounts);
        if (arrangement == null) {
            return KeylineListKt.emptyKeylineList();
        }
        if (shouldCenter && itemCount >= arrangement.itemCount()) {
            return createCenterAlignedKeylineList(carouselMainAxisSize, itemSpacing, anchorSize, anchorSize, arrangement);
        }
        return createLeftAlignedKeylineList(carouselMainAxisSize, itemSpacing, anchorSize, anchorSize, arrangement);
    }

    public static final KeylineList createLeftAlignedKeylineList(float carouselMainAxisSize, float itemSpacing, final float leftAnchorSize, final float rightAnchorSize, final Arrangement arrangement) {
        return KeylineListKt.m3425keylineListOfWNYm7Xg(carouselMainAxisSize, itemSpacing, CarouselAlignment.INSTANCE.m3411getStartNUL3oTo(), new Function1() { // from class: androidx.compose.material3.carousel.KeylinesKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return KeylinesKt.createLeftAlignedKeylineList$lambda$10(leftAnchorSize, arrangement, rightAnchorSize, (KeylineListScope) obj);
            }
        });
    }

    static final Unit createLeftAlignedKeylineList$lambda$10(float $leftAnchorSize, Arrangement $arrangement, float $rightAnchorSize, KeylineListScope $this$keylineListOf) {
        $this$keylineListOf.add($leftAnchorSize, true);
        int largeCount = $arrangement.getLargeCount();
        for (int i = 0; i < largeCount; i++) {
            KeylineListScope.add$default($this$keylineListOf, $arrangement.getLargeSize(), false, 2, null);
        }
        int mediumCount = $arrangement.getMediumCount();
        for (int i2 = 0; i2 < mediumCount; i2++) {
            KeylineListScope.add$default($this$keylineListOf, $arrangement.getMediumSize(), false, 2, null);
        }
        int smallCount = $arrangement.getSmallCount();
        for (int i3 = 0; i3 < smallCount; i3++) {
            KeylineListScope.add$default($this$keylineListOf, $arrangement.getSmallSize(), false, 2, null);
        }
        $this$keylineListOf.add($rightAnchorSize, true);
        return Unit.INSTANCE;
    }

    public static final KeylineList createCenterAlignedKeylineList(float carouselMainAxisSize, float itemSpacing, final float leftAnchorSize, final float rightAnchorSize, final Arrangement arrangement) {
        return KeylineListKt.m3425keylineListOfWNYm7Xg(carouselMainAxisSize, itemSpacing, CarouselAlignment.INSTANCE.m3409getCenterNUL3oTo(), new Function1() { // from class: androidx.compose.material3.carousel.KeylinesKt$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return KeylinesKt.createCenterAlignedKeylineList$lambda$16(leftAnchorSize, arrangement, rightAnchorSize, (KeylineListScope) obj);
            }
        });
    }

    static final Unit createCenterAlignedKeylineList$lambda$16(float $leftAnchorSize, Arrangement $arrangement, float $rightAnchorSize, KeylineListScope $this$keylineListOf) {
        $this$keylineListOf.add($leftAnchorSize, true);
        int smallCount = $arrangement.getSmallCount() / 2;
        for (int i = 0; i < smallCount; i++) {
            KeylineListScope.add$default($this$keylineListOf, $arrangement.getSmallSize(), false, 2, null);
        }
        int mediumCount = $arrangement.getMediumCount() / 2;
        for (int i2 = 0; i2 < mediumCount; i2++) {
            KeylineListScope.add$default($this$keylineListOf, $arrangement.getMediumSize(), false, 2, null);
        }
        int largeCount = $arrangement.getLargeCount();
        for (int i3 = 0; i3 < largeCount; i3++) {
            KeylineListScope.add$default($this$keylineListOf, $arrangement.getLargeSize(), false, 2, null);
        }
        int mediumCount2 = $arrangement.getMediumCount() / 2;
        for (int i4 = 0; i4 < mediumCount2; i4++) {
            KeylineListScope.add$default($this$keylineListOf, $arrangement.getMediumSize(), false, 2, null);
        }
        int smallCount2 = $arrangement.getSmallCount() / 2;
        for (int i5 = 0; i5 < smallCount2; i5++) {
            KeylineListScope.add$default($this$keylineListOf, $arrangement.getSmallSize(), false, 2, null);
        }
        $this$keylineListOf.add($rightAnchorSize, true);
        return Unit.INSTANCE;
    }

    private static final float calculateMediumChildSize(float minimumMediumSize, float largeItemSize, float remainingSpace) {
        float sizeWithThirdCutOff = 1.5f * remainingSpace;
        float mediumItemSize = Math.max(sizeWithThirdCutOff, minimumMediumSize);
        float largeItemThreshold = 0.85f * largeItemSize;
        if (mediumItemSize > largeItemThreshold) {
            float sizeWithFifthCutOff = 1.2f * remainingSpace;
            return Math.min(Math.max(largeItemThreshold, sizeWithFifthCutOff), largeItemSize);
        }
        return mediumItemSize;
    }
}
