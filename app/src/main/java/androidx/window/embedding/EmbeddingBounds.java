package androidx.window.embedding;

import android.graphics.Rect;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.window.core.Bounds;
import androidx.window.layout.FoldingFeature;
import androidx.window.layout.WindowLayoutInfo;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: EmbeddingBounds.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0007\u0018\u0000 '2\u00020\u0001:\u0003%&'B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\b\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\u0015\u0010\u0015\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u0017H\u0000¢\u0006\u0002\b\u0018J\u0015\u0010\u0019\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u0017H\u0000¢\u0006\u0002\b\u001aJ\f\u0010\u001b\u001a\u00020\u0013*\u00020\u0017H\u0002J\f\u0010\u001c\u001a\u00020\u0013*\u00020\u0017H\u0002J\u000e\u0010\u001d\u001a\u0004\u0018\u00010\u001e*\u00020\u0017H\u0002J\u001d\u0010\u001f\u001a\u00020\u00112\u0006\u0010 \u001a\u00020!2\u0006\u0010\u0016\u001a\u00020\u0017H\u0001¢\u0006\u0002\b\"J\u001d\u0010#\u001a\u00020\u00112\u0006\u0010 \u001a\u00020!2\u0006\u0010\u0016\u001a\u00020\u0017H\u0001¢\u0006\u0002\b$R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\f¨\u0006("}, d2 = {"Landroidx/window/embedding/EmbeddingBounds;", "", "alignment", "Landroidx/window/embedding/EmbeddingBounds$Alignment;", "width", "Landroidx/window/embedding/EmbeddingBounds$Dimension;", "height", "<init>", "(Landroidx/window/embedding/EmbeddingBounds$Alignment;Landroidx/window/embedding/EmbeddingBounds$Dimension;Landroidx/window/embedding/EmbeddingBounds$Dimension;)V", "getAlignment", "()Landroidx/window/embedding/EmbeddingBounds$Alignment;", "getWidth", "()Landroidx/window/embedding/EmbeddingBounds$Dimension;", "getHeight", "toString", "", "hashCode", "", "equals", "", "other", "shouldUseFallbackDimensionForWidth", "windowLayoutInfo", "Landroidx/window/layout/WindowLayoutInfo;", "shouldUseFallbackDimensionForWidth$window_release", "shouldUseFallbackDimensionForHeight", "shouldUseFallbackDimensionForHeight$window_release", "isHorizontal", "isVertical", "getOnlyFoldingFeatureOrNull", "Landroidx/window/layout/FoldingFeature;", "getWidthInPixel", "parentContainerBounds", "Landroidx/window/core/Bounds;", "getWidthInPixel$window_release", "getHeightInPixel", "getHeightInPixel$window_release", "Alignment", "Dimension", "Companion", "window_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class EmbeddingBounds {
    private final Alignment alignment;
    private final Dimension height;
    private final Dimension width;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final EmbeddingBounds BOUNDS_EXPANDED = new EmbeddingBounds(Alignment.ALIGN_TOP, Dimension.DIMENSION_EXPANDED, Dimension.DIMENSION_EXPANDED);
    public static final EmbeddingBounds BOUNDS_HINGE_TOP = new EmbeddingBounds(Alignment.ALIGN_TOP, Dimension.DIMENSION_EXPANDED, Dimension.DIMENSION_HINGE);
    public static final EmbeddingBounds BOUNDS_HINGE_LEFT = new EmbeddingBounds(Alignment.ALIGN_LEFT, Dimension.DIMENSION_HINGE, Dimension.DIMENSION_EXPANDED);
    public static final EmbeddingBounds BOUNDS_HINGE_BOTTOM = new EmbeddingBounds(Alignment.ALIGN_BOTTOM, Dimension.DIMENSION_EXPANDED, Dimension.DIMENSION_HINGE);
    public static final EmbeddingBounds BOUNDS_HINGE_RIGHT = new EmbeddingBounds(Alignment.ALIGN_RIGHT, Dimension.DIMENSION_HINGE, Dimension.DIMENSION_EXPANDED);

    public EmbeddingBounds(Alignment alignment, Dimension width, Dimension height) {
        Intrinsics.checkNotNullParameter(alignment, "alignment");
        Intrinsics.checkNotNullParameter(width, "width");
        Intrinsics.checkNotNullParameter(height, "height");
        this.alignment = alignment;
        this.width = width;
        this.height = height;
    }

    public final Alignment getAlignment() {
        return this.alignment;
    }

    public final Dimension getWidth() {
        return this.width;
    }

    public final Dimension getHeight() {
        return this.height;
    }

    public String toString() {
        return "Bounds:{alignment=" + this.alignment + ", width=" + this.width + ", height=" + this.height + '}';
    }

    public int hashCode() {
        int result = this.alignment.hashCode();
        return (((result * 31) + this.width.hashCode()) * 31) + this.height.hashCode();
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof EmbeddingBounds) {
            return Intrinsics.areEqual(this.alignment, ((EmbeddingBounds) other).alignment) && Intrinsics.areEqual(this.width, ((EmbeddingBounds) other).width) && Intrinsics.areEqual(this.height, ((EmbeddingBounds) other).height);
        }
        return false;
    }

    public final boolean shouldUseFallbackDimensionForWidth$window_release(WindowLayoutInfo windowLayoutInfo) {
        Intrinsics.checkNotNullParameter(windowLayoutInfo, "windowLayoutInfo");
        if (Intrinsics.areEqual(this.width, Dimension.DIMENSION_HINGE)) {
            return !isVertical(windowLayoutInfo) || CollectionsKt.listOf((Object[]) new Alignment[]{Alignment.ALIGN_TOP, Alignment.ALIGN_BOTTOM}).contains(this.alignment);
        }
        return false;
    }

    public final boolean shouldUseFallbackDimensionForHeight$window_release(WindowLayoutInfo windowLayoutInfo) {
        Intrinsics.checkNotNullParameter(windowLayoutInfo, "windowLayoutInfo");
        if (Intrinsics.areEqual(this.height, Dimension.DIMENSION_HINGE)) {
            return !isHorizontal(windowLayoutInfo) || CollectionsKt.listOf((Object[]) new Alignment[]{Alignment.ALIGN_LEFT, Alignment.ALIGN_RIGHT}).contains(this.alignment);
        }
        return false;
    }

    private final boolean isHorizontal(WindowLayoutInfo $this$isHorizontal) {
        FoldingFeature foldingFeature = getOnlyFoldingFeatureOrNull($this$isHorizontal);
        if (foldingFeature == null) {
            return false;
        }
        return Intrinsics.areEqual(foldingFeature.getOrientation(), FoldingFeature.Orientation.HORIZONTAL);
    }

    private final boolean isVertical(WindowLayoutInfo $this$isVertical) {
        FoldingFeature foldingFeature = getOnlyFoldingFeatureOrNull($this$isVertical);
        if (foldingFeature == null) {
            return false;
        }
        return Intrinsics.areEqual(foldingFeature.getOrientation(), FoldingFeature.Orientation.VERTICAL);
    }

    private final FoldingFeature getOnlyFoldingFeatureOrNull(WindowLayoutInfo $this$getOnlyFoldingFeatureOrNull) {
        Iterable $this$filterIsInstance$iv = $this$getOnlyFoldingFeatureOrNull.getDisplayFeatures();
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filterIsInstance$iv) {
            if (element$iv$iv instanceof FoldingFeature) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        List foldingFeatures = (List) destination$iv$iv;
        if (foldingFeatures.size() == 1) {
            return (FoldingFeature) foldingFeatures.get(0);
        }
        return null;
    }

    public final int getWidthInPixel$window_release(Bounds parentContainerBounds, WindowLayoutInfo windowLayoutInfo) {
        Intrinsics.checkNotNullParameter(parentContainerBounds, "parentContainerBounds");
        Intrinsics.checkNotNullParameter(windowLayoutInfo, "windowLayoutInfo");
        int taskWidth = parentContainerBounds.getWidth();
        Dimension widthDimension = shouldUseFallbackDimensionForWidth$window_release(windowLayoutInfo) ? Dimension.INSTANCE.ratio(0.5f) : this.width;
        if (widthDimension instanceof Dimension.Ratio) {
            return ((Dimension.Ratio) widthDimension).times$window_release(taskWidth);
        }
        if (widthDimension instanceof Dimension.Pixel) {
            return Math.min(taskWidth, ((Dimension.Pixel) widthDimension).getValue());
        }
        if (!Intrinsics.areEqual(widthDimension, Dimension.DIMENSION_HINGE)) {
            throw new IllegalArgumentException("Unhandled width dimension=" + this.width);
        }
        FoldingFeature onlyFoldingFeatureOrNull = getOnlyFoldingFeatureOrNull(windowLayoutInfo);
        Intrinsics.checkNotNull(onlyFoldingFeatureOrNull);
        Rect hingeBounds = onlyFoldingFeatureOrNull.getBounds();
        Alignment alignment = this.alignment;
        if (Intrinsics.areEqual(alignment, Alignment.ALIGN_LEFT)) {
            return hingeBounds.left - parentContainerBounds.getLeft();
        }
        if (Intrinsics.areEqual(alignment, Alignment.ALIGN_RIGHT)) {
            return parentContainerBounds.getRight() - hingeBounds.right;
        }
        throw new IllegalStateException("Unhandled condition to get height in pixel! embeddingBounds=" + this + " taskBounds=" + parentContainerBounds + " windowLayoutInfo=" + windowLayoutInfo);
    }

    public final int getHeightInPixel$window_release(Bounds parentContainerBounds, WindowLayoutInfo windowLayoutInfo) {
        Intrinsics.checkNotNullParameter(parentContainerBounds, "parentContainerBounds");
        Intrinsics.checkNotNullParameter(windowLayoutInfo, "windowLayoutInfo");
        int taskHeight = parentContainerBounds.getHeight();
        Dimension heightDimension = shouldUseFallbackDimensionForHeight$window_release(windowLayoutInfo) ? Dimension.INSTANCE.ratio(0.5f) : this.height;
        if (heightDimension instanceof Dimension.Ratio) {
            return ((Dimension.Ratio) heightDimension).times$window_release(taskHeight);
        }
        if (heightDimension instanceof Dimension.Pixel) {
            return Math.min(taskHeight, ((Dimension.Pixel) heightDimension).getValue());
        }
        if (!Intrinsics.areEqual(heightDimension, Dimension.DIMENSION_HINGE)) {
            throw new IllegalArgumentException("Unhandled width dimension=" + this.width);
        }
        FoldingFeature onlyFoldingFeatureOrNull = getOnlyFoldingFeatureOrNull(windowLayoutInfo);
        Intrinsics.checkNotNull(onlyFoldingFeatureOrNull);
        Rect hingeBounds = onlyFoldingFeatureOrNull.getBounds();
        Alignment alignment = this.alignment;
        if (Intrinsics.areEqual(alignment, Alignment.ALIGN_TOP)) {
            return hingeBounds.top - parentContainerBounds.getTop();
        }
        if (Intrinsics.areEqual(alignment, Alignment.ALIGN_BOTTOM)) {
            return parentContainerBounds.getBottom() - hingeBounds.bottom;
        }
        throw new IllegalStateException("Unhandled condition to get height in pixel! embeddingBounds=" + this + " taskBounds=" + parentContainerBounds + " windowLayoutInfo=" + windowLayoutInfo);
    }

    /* JADX INFO: compiled from: EmbeddingBounds.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\u0013\b\u0000\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0013\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u000b\u001a\u00020\u0003H\u0016J\b\u0010\f\u001a\u00020\rH\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000f"}, d2 = {"Landroidx/window/embedding/EmbeddingBounds$Alignment;", "", "value", "", "<init>", "(I)V", "getValue$window_release", "()I", "equals", "", "other", "hashCode", "toString", "", "Companion", "window_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Alignment {
        private final int value;
        public static final Alignment ALIGN_LEFT = new Alignment(0);
        public static final Alignment ALIGN_TOP = new Alignment(1);
        public static final Alignment ALIGN_RIGHT = new Alignment(2);
        public static final Alignment ALIGN_BOTTOM = new Alignment(3);

        public Alignment(int value) {
            this.value = value;
            int i = this.value;
            boolean z = false;
            if (i >= 0 && i < 4) {
                z = true;
            }
            if (z) {
            } else {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
        }

        /* JADX INFO: renamed from: getValue$window_release, reason: from getter */
        public final int getValue() {
            return this.value;
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Alignment) && this.value == ((Alignment) other).value;
        }

        public int hashCode() {
            return this.value;
        }

        public String toString() {
            switch (this.value) {
                case 0:
                    return "left";
                case 1:
                    return "top";
                case 2:
                    return "right";
                case 3:
                    return "bottom";
                default:
                    return "unknown position:" + this.value;
            }
        }
    }

    /* JADX INFO: compiled from: EmbeddingBounds.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\b&\u0018\u0000 \u00102\u00020\u0001:\u0003\u000e\u000f\u0010B\u0011\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0013\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\r\u001a\u00020\u0003H\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Landroidx/window/embedding/EmbeddingBounds$Dimension;", "", "description", "", "<init>", "(Ljava/lang/String;)V", "getDescription$window_release", "()Ljava/lang/String;", "equals", "", "other", "hashCode", "", "toString", "Pixel", "Ratio", "Companion", "window_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static abstract class Dimension {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        public static final Dimension DIMENSION_EXPANDED = new Ratio(1.0f);
        public static final Dimension DIMENSION_HINGE = new Dimension() { // from class: androidx.window.embedding.EmbeddingBounds$Dimension$Companion$DIMENSION_HINGE$1
        };
        private final String description;

        public Dimension(String description) {
            Intrinsics.checkNotNullParameter(description, "description");
            this.description = description;
        }

        /* JADX INFO: renamed from: getDescription$window_release, reason: from getter */
        public final String getDescription() {
            return this.description;
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (other instanceof Dimension) {
                return Intrinsics.areEqual(this.description, ((Dimension) other).description);
            }
            return false;
        }

        public int hashCode() {
            return this.description.hashCode();
        }

        public String toString() {
            return this.description;
        }

        /* JADX INFO: compiled from: EmbeddingBounds.kt */
        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\b\b\u0000\u0018\u00002\u00020\u0001B\u0011\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0016\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0003H\u0080\u0002¢\u0006\u0002\b\nR\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000b"}, d2 = {"Landroidx/window/embedding/EmbeddingBounds$Dimension$Pixel;", "Landroidx/window/embedding/EmbeddingBounds$Dimension;", "value", "", "<init>", "(I)V", "getValue$window_release", "()I", "compareTo", "dimen", "compareTo$window_release", "window_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
        public static final class Pixel extends Dimension {
            private final int value;

            /* JADX INFO: renamed from: getValue$window_release, reason: from getter */
            public final int getValue() {
                return this.value;
            }

            public Pixel(int value) {
                super("dimension in pixel:" + value);
                this.value = value;
                if (!(this.value >= 1)) {
                    throw new IllegalArgumentException("Pixel value must be a positive integer.".toString());
                }
            }

            public final int compareTo$window_release(int dimen) {
                return this.value - dimen;
            }
        }

        /* JADX INFO: compiled from: EmbeddingBounds.kt */
        @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u0011\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0016\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0080\u0002¢\u0006\u0002\b\u000bR\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\f"}, d2 = {"Landroidx/window/embedding/EmbeddingBounds$Dimension$Ratio;", "Landroidx/window/embedding/EmbeddingBounds$Dimension;", "value", "", "<init>", "(F)V", "getValue$window_release", "()F", "times", "", "dimen", "times$window_release", "window_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
        public static final class Ratio extends Dimension {
            private final float value;

            /* JADX INFO: renamed from: getValue$window_release, reason: from getter */
            public final float getValue() {
                return this.value;
            }

            public Ratio(float value) {
                super("dimension in ratio:" + value);
                this.value = value;
                if (!(((double) this.value) > 0.0d && ((double) this.value) <= 1.0d)) {
                    throw new IllegalArgumentException("Ratio must be in range (0.0, 1.0]".toString());
                }
            }

            public final int times$window_release(int dimen) {
                return (int) (this.value * dimen);
            }
        }

        /* JADX INFO: compiled from: EmbeddingBounds.kt */
        @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0007\u001a\u00020\u00052\b\b\u0001\u0010\b\u001a\u00020\tH\u0007J\u0012\u0010\n\u001a\u00020\u00052\b\b\u0001\u0010\n\u001a\u00020\u000bH\u0007R\u0010\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Landroidx/window/embedding/EmbeddingBounds$Dimension$Companion;", "", "<init>", "()V", "DIMENSION_EXPANDED", "Landroidx/window/embedding/EmbeddingBounds$Dimension;", "DIMENSION_HINGE", "pixel", "value", "", "ratio", "", "window_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            @JvmStatic
            public final Dimension pixel(int value) {
                return new Pixel(value);
            }

            @JvmStatic
            public final Dimension ratio(float ratio) {
                return new Ratio(ratio);
            }
        }

        @JvmStatic
        public static final Dimension pixel(int value) {
            return INSTANCE.pixel(value);
        }

        @JvmStatic
        public static final Dimension ratio(float ratio) {
            return INSTANCE.ratio(ratio);
        }
    }

    /* JADX INFO: compiled from: EmbeddingBounds.kt */
    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J%\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u000fH\u0001¢\u0006\u0002\b\u0010J\u001c\u0010\u0011\u001a\u00020\u000b*\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0013H\u0002J\u001d\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\u0016H\u0000¢\u0006\u0002\b\u0010R\u0010\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Landroidx/window/embedding/EmbeddingBounds$Companion;", "", "<init>", "()V", "BOUNDS_EXPANDED", "Landroidx/window/embedding/EmbeddingBounds;", "BOUNDS_HINGE_TOP", "BOUNDS_HINGE_LEFT", "BOUNDS_HINGE_BOTTOM", "BOUNDS_HINGE_RIGHT", "translateEmbeddingBounds", "Landroidx/window/core/Bounds;", "embeddingBounds", "parentContainerBounds", "windowLayoutInfo", "Landroidx/window/layout/WindowLayoutInfo;", "translateEmbeddingBounds$window_release", TypedValues.CycleType.S_WAVE_OFFSET, "dx", "", "dy", "parentContainerInfo", "Landroidx/window/embedding/ParentContainerInfo;", "window_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Bounds translateEmbeddingBounds$window_release(EmbeddingBounds embeddingBounds, Bounds parentContainerBounds, WindowLayoutInfo windowLayoutInfo) {
            Dimension width;
            Dimension height;
            Intrinsics.checkNotNullParameter(embeddingBounds, "embeddingBounds");
            Intrinsics.checkNotNullParameter(parentContainerBounds, "parentContainerBounds");
            Intrinsics.checkNotNullParameter(windowLayoutInfo, "windowLayoutInfo");
            if (Intrinsics.areEqual(embeddingBounds.getWidth(), Dimension.DIMENSION_EXPANDED) && Intrinsics.areEqual(embeddingBounds.getHeight(), Dimension.DIMENSION_EXPANDED)) {
                return Bounds.INSTANCE.getEMPTY_BOUNDS();
            }
            if (embeddingBounds.shouldUseFallbackDimensionForWidth$window_release(windowLayoutInfo)) {
                width = Dimension.INSTANCE.ratio(0.5f);
            } else {
                width = embeddingBounds.getWidth();
            }
            if (embeddingBounds.shouldUseFallbackDimensionForHeight$window_release(windowLayoutInfo)) {
                height = Dimension.INSTANCE.ratio(0.5f);
            } else {
                height = embeddingBounds.getHeight();
            }
            EmbeddingBounds sanitizedBounds = new EmbeddingBounds(embeddingBounds.getAlignment(), width, height);
            int widthInPixel = sanitizedBounds.getWidthInPixel$window_release(parentContainerBounds, windowLayoutInfo);
            int heightInPixel = sanitizedBounds.getHeightInPixel$window_release(parentContainerBounds, windowLayoutInfo);
            int taskWidth = parentContainerBounds.getWidth();
            int taskHeight = parentContainerBounds.getHeight();
            if (widthInPixel == taskWidth && heightInPixel == taskHeight) {
                return Bounds.INSTANCE.getEMPTY_BOUNDS();
            }
            Bounds bounds = new Bounds(0, 0, widthInPixel, heightInPixel);
            Alignment alignment = embeddingBounds.getAlignment();
            if (Intrinsics.areEqual(alignment, Alignment.ALIGN_TOP)) {
                return EmbeddingBounds.INSTANCE.offset(bounds, (taskWidth - widthInPixel) / 2, 0);
            }
            if (Intrinsics.areEqual(alignment, Alignment.ALIGN_LEFT)) {
                return EmbeddingBounds.INSTANCE.offset(bounds, 0, (taskHeight - heightInPixel) / 2);
            }
            if (Intrinsics.areEqual(alignment, Alignment.ALIGN_BOTTOM)) {
                return EmbeddingBounds.INSTANCE.offset(bounds, (taskWidth - widthInPixel) / 2, taskHeight - heightInPixel);
            }
            if (Intrinsics.areEqual(alignment, Alignment.ALIGN_RIGHT)) {
                return EmbeddingBounds.INSTANCE.offset(bounds, taskWidth - widthInPixel, (taskHeight - heightInPixel) / 2);
            }
            throw new IllegalArgumentException("Unknown alignment: " + embeddingBounds.getAlignment());
        }

        private final Bounds offset(Bounds $this$offset, int dx, int dy) {
            return new Bounds($this$offset.getLeft() + dx, $this$offset.getTop() + dy, $this$offset.getRight() + dx, $this$offset.getBottom() + dy);
        }

        public final Bounds translateEmbeddingBounds$window_release(EmbeddingBounds embeddingBounds, ParentContainerInfo parentContainerInfo) {
            Intrinsics.checkNotNullParameter(embeddingBounds, "embeddingBounds");
            Intrinsics.checkNotNullParameter(parentContainerInfo, "parentContainerInfo");
            return translateEmbeddingBounds$window_release(embeddingBounds, parentContainerInfo.getWindowBounds(), parentContainerInfo.getWindowLayoutInfo());
        }
    }
}
