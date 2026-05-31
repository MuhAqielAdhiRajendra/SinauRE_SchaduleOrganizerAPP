package androidx.window.embedding;

import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.view.WindowMetrics;
import androidx.core.util.Preconditions;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SplitRule.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\b\u0007\b\u0016\u0018\u0000 ,2\u00020\u0001:\u0004,-./BO\b\u0000\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0003\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0003\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0003\u0010\u0007\u001a\u00020\u0005\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0004\b\r\u0010\u000eJ\u001d\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0000¢\u0006\u0002\b\u001eJ\u001d\u0010\u001f\u001a\u00020\u00192\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#H\u0000¢\u0006\u0002\b$J\u001a\u0010%\u001a\u00020\u00052\u0006\u0010 \u001a\u00020!2\b\b\u0001\u0010&\u001a\u00020\u0005H\u0002J\u0013\u0010'\u001a\u00020\u00192\b\u0010(\u001a\u0004\u0018\u00010)H\u0096\u0002J\b\u0010*\u001a\u00020\u0005H\u0016J\b\u0010+\u001a\u00020\u0003H\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\n\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017¨\u00060"}, d2 = {"Landroidx/window/embedding/SplitRule;", "Landroidx/window/embedding/EmbeddingRule;", "tag", "", "minWidthDp", "", "minHeightDp", "minSmallestWidthDp", "maxAspectRatioInPortrait", "Landroidx/window/embedding/EmbeddingAspectRatio;", "maxAspectRatioInLandscape", "defaultSplitAttributes", "Landroidx/window/embedding/SplitAttributes;", "<init>", "(Ljava/lang/String;IIILandroidx/window/embedding/EmbeddingAspectRatio;Landroidx/window/embedding/EmbeddingAspectRatio;Landroidx/window/embedding/SplitAttributes;)V", "getMinWidthDp", "()I", "getMinHeightDp", "getMinSmallestWidthDp", "getMaxAspectRatioInPortrait", "()Landroidx/window/embedding/EmbeddingAspectRatio;", "getMaxAspectRatioInLandscape", "getDefaultSplitAttributes", "()Landroidx/window/embedding/SplitAttributes;", "checkParentMetrics", "", "context", "Landroid/content/Context;", "parentMetrics", "Landroid/view/WindowMetrics;", "checkParentMetrics$window_release", "checkParentBounds", "density", "", "bounds", "Landroid/graphics/Rect;", "checkParentBounds$window_release", "convertDpToPx", "dimensionDp", "equals", "other", "", "hashCode", "toString", "Companion", "FinishBehavior", "Api30Impl", "Api34Impl", "window_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public class SplitRule extends EmbeddingRule {
    public static final int SPLIT_MIN_DIMENSION_ALWAYS_ALLOW = 0;
    public static final int SPLIT_MIN_DIMENSION_DP_DEFAULT = 600;
    private final SplitAttributes defaultSplitAttributes;
    private final EmbeddingAspectRatio maxAspectRatioInLandscape;
    private final EmbeddingAspectRatio maxAspectRatioInPortrait;
    private final int minHeightDp;
    private final int minSmallestWidthDp;
    private final int minWidthDp;
    public static final EmbeddingAspectRatio SPLIT_MAX_ASPECT_RATIO_PORTRAIT_DEFAULT = EmbeddingAspectRatio.INSTANCE.ratio(1.4f);
    public static final EmbeddingAspectRatio SPLIT_MAX_ASPECT_RATIO_LANDSCAPE_DEFAULT = EmbeddingAspectRatio.ALWAYS_ALLOW;

    public /* synthetic */ SplitRule(String str, int i, int i2, int i3, EmbeddingAspectRatio embeddingAspectRatio, EmbeddingAspectRatio embeddingAspectRatio2, SplitAttributes splitAttributes, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this((i4 & 1) != 0 ? null : str, (i4 & 2) != 0 ? 600 : i, (i4 & 4) != 0 ? 600 : i2, (i4 & 8) != 0 ? 600 : i3, (i4 & 16) != 0 ? SPLIT_MAX_ASPECT_RATIO_PORTRAIT_DEFAULT : embeddingAspectRatio, (i4 & 32) != 0 ? SPLIT_MAX_ASPECT_RATIO_LANDSCAPE_DEFAULT : embeddingAspectRatio2, splitAttributes);
    }

    public final int getMinWidthDp() {
        return this.minWidthDp;
    }

    public final int getMinHeightDp() {
        return this.minHeightDp;
    }

    public final int getMinSmallestWidthDp() {
        return this.minSmallestWidthDp;
    }

    public final EmbeddingAspectRatio getMaxAspectRatioInPortrait() {
        return this.maxAspectRatioInPortrait;
    }

    public final EmbeddingAspectRatio getMaxAspectRatioInLandscape() {
        return this.maxAspectRatioInLandscape;
    }

    public final SplitAttributes getDefaultSplitAttributes() {
        return this.defaultSplitAttributes;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SplitRule(String tag, int minWidthDp, int minHeightDp, int minSmallestWidthDp, EmbeddingAspectRatio maxAspectRatioInPortrait, EmbeddingAspectRatio maxAspectRatioInLandscape, SplitAttributes defaultSplitAttributes) {
        super(tag);
        Intrinsics.checkNotNullParameter(maxAspectRatioInPortrait, "maxAspectRatioInPortrait");
        Intrinsics.checkNotNullParameter(maxAspectRatioInLandscape, "maxAspectRatioInLandscape");
        Intrinsics.checkNotNullParameter(defaultSplitAttributes, "defaultSplitAttributes");
        this.minWidthDp = minWidthDp;
        this.minHeightDp = minHeightDp;
        this.minSmallestWidthDp = minSmallestWidthDp;
        this.maxAspectRatioInPortrait = maxAspectRatioInPortrait;
        this.maxAspectRatioInLandscape = maxAspectRatioInLandscape;
        this.defaultSplitAttributes = defaultSplitAttributes;
        Preconditions.checkArgumentNonnegative(this.minWidthDp, "minWidthDp must be non-negative");
        Preconditions.checkArgumentNonnegative(this.minHeightDp, "minHeightDp must be non-negative");
        Preconditions.checkArgumentNonnegative(this.minSmallestWidthDp, "minSmallestWidthDp must be non-negative");
    }

    /* JADX INFO: compiled from: SplitRule.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\b\u0018\u0000 \f2\u00020\u0001:\u0001\fB\u0019\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\b\u0010\n\u001a\u00020\u0003H\u0016J\b\u0010\u000b\u001a\u00020\u0005H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\u00020\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\r"}, d2 = {"Landroidx/window/embedding/SplitRule$FinishBehavior;", "", "description", "", "value", "", "<init>", "(Ljava/lang/String;I)V", "getValue$window_release", "()I", "toString", "hashCode", "Companion", "window_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class FinishBehavior {
        private final String description;
        private final int value;

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        public static final FinishBehavior NEVER = new FinishBehavior("NEVER", 0);
        public static final FinishBehavior ALWAYS = new FinishBehavior("ALWAYS", 1);
        public static final FinishBehavior ADJACENT = new FinishBehavior("ADJACENT", 2);

        private FinishBehavior(String description, int value) {
            this.description = description;
            this.value = value;
        }

        /* JADX INFO: renamed from: getValue$window_release, reason: from getter */
        public final int getValue() {
            return this.value;
        }

        /* JADX INFO: renamed from: toString, reason: from getter */
        public String getDescription() {
            return this.description;
        }

        public int hashCode() {
            int result = this.description.hashCode();
            return (result * 31) + this.value;
        }

        /* JADX INFO: compiled from: SplitRule.kt */
        @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\b\u001a\u00020\u00052\b\b\u0001\u0010\t\u001a\u00020\nH\u0001¢\u0006\u0002\b\u000bR\u0010\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Landroidx/window/embedding/SplitRule$FinishBehavior$Companion;", "", "<init>", "()V", "NEVER", "Landroidx/window/embedding/SplitRule$FinishBehavior;", "ALWAYS", "ADJACENT", "getFinishBehaviorFromValue", "value", "", "getFinishBehaviorFromValue$window_release", "window_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            @JvmStatic
            public final FinishBehavior getFinishBehaviorFromValue$window_release(int value) {
                if (value == FinishBehavior.NEVER.getValue()) {
                    return FinishBehavior.NEVER;
                }
                if (value == FinishBehavior.ALWAYS.getValue()) {
                    return FinishBehavior.ALWAYS;
                }
                if (value == FinishBehavior.ADJACENT.getValue()) {
                    return FinishBehavior.ADJACENT;
                }
                throw new IllegalArgumentException("Unknown finish behavior:" + value);
            }
        }
    }

    public final boolean checkParentMetrics$window_release(Context context, WindowMetrics parentMetrics) {
        float density;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(parentMetrics, "parentMetrics");
        if (Build.VERSION.SDK_INT <= 30) {
            return false;
        }
        Rect bounds = Api30Impl.INSTANCE.getBounds(parentMetrics);
        if (Build.VERSION.SDK_INT <= 33) {
            density = context.getResources().getDisplayMetrics().density;
        } else {
            density = Api34Impl.INSTANCE.getDensity(parentMetrics, context);
        }
        return checkParentBounds$window_release(density, bounds);
    }

    public final boolean checkParentBounds$window_release(float density, Rect bounds) {
        boolean validAspectRatio;
        Intrinsics.checkNotNullParameter(bounds, "bounds");
        int width = bounds.width();
        int height = bounds.height();
        if (width == 0 || height == 0) {
            return false;
        }
        int minWidthPx = convertDpToPx(density, this.minWidthDp);
        int minHeightPx = convertDpToPx(density, this.minHeightDp);
        int minSmallestWidthPx = convertDpToPx(density, this.minSmallestWidthDp);
        boolean validMinWidth = this.minWidthDp == 0 || width >= minWidthPx;
        boolean validMinHeight = this.minHeightDp == 0 || height >= minHeightPx;
        boolean validSmallestMinWidth = this.minSmallestWidthDp == 0 || Math.min(width, height) >= minSmallestWidthPx;
        if (height >= width) {
            validAspectRatio = Intrinsics.areEqual(this.maxAspectRatioInPortrait, EmbeddingAspectRatio.ALWAYS_ALLOW) || (((float) height) * 1.0f) / ((float) width) <= this.maxAspectRatioInPortrait.getValue();
        } else {
            validAspectRatio = Intrinsics.areEqual(this.maxAspectRatioInLandscape, EmbeddingAspectRatio.ALWAYS_ALLOW) || (((float) width) * 1.0f) / ((float) height) <= this.maxAspectRatioInLandscape.getValue();
        }
        return validMinWidth && validMinHeight && validSmallestMinWidth && validAspectRatio;
    }

    private final int convertDpToPx(float density, int dimensionDp) {
        return (int) ((dimensionDp * density) + 0.5f);
    }

    /* JADX INFO: compiled from: SplitRule.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÁ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Landroidx/window/embedding/SplitRule$Api30Impl;", "", "<init>", "()V", "getBounds", "Landroid/graphics/Rect;", "windowMetrics", "Landroid/view/WindowMetrics;", "window_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Api30Impl {
        public static final Api30Impl INSTANCE = new Api30Impl();

        private Api30Impl() {
        }

        public final Rect getBounds(WindowMetrics windowMetrics) {
            Intrinsics.checkNotNullParameter(windowMetrics, "windowMetrics");
            Rect bounds = windowMetrics.getBounds();
            Intrinsics.checkNotNullExpressionValue(bounds, "getBounds(...)");
            return bounds;
        }
    }

    /* JADX INFO: compiled from: SplitRule.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÁ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t¨\u0006\n"}, d2 = {"Landroidx/window/embedding/SplitRule$Api34Impl;", "", "<init>", "()V", "getDensity", "", "windowMetrics", "Landroid/view/WindowMetrics;", "context", "Landroid/content/Context;", "window_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Api34Impl {
        public static final Api34Impl INSTANCE = new Api34Impl();

        private Api34Impl() {
        }

        public final float getDensity(WindowMetrics windowMetrics, Context context) {
            Intrinsics.checkNotNullParameter(windowMetrics, "windowMetrics");
            Intrinsics.checkNotNullParameter(context, "context");
            try {
                return windowMetrics.getDensity();
            } catch (NoSuchMethodError e) {
                return context.getResources().getDisplayMetrics().density;
            }
        }
    }

    @Override // androidx.window.embedding.EmbeddingRule
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof SplitRule) && super.equals(other) && this.minWidthDp == ((SplitRule) other).minWidthDp && this.minHeightDp == ((SplitRule) other).minHeightDp && this.minSmallestWidthDp == ((SplitRule) other).minSmallestWidthDp && Intrinsics.areEqual(this.maxAspectRatioInPortrait, ((SplitRule) other).maxAspectRatioInPortrait) && Intrinsics.areEqual(this.maxAspectRatioInLandscape, ((SplitRule) other).maxAspectRatioInLandscape) && Intrinsics.areEqual(this.defaultSplitAttributes, ((SplitRule) other).defaultSplitAttributes);
    }

    @Override // androidx.window.embedding.EmbeddingRule
    public int hashCode() {
        int result = super.hashCode();
        return (((((((((((result * 31) + this.minWidthDp) * 31) + this.minHeightDp) * 31) + this.minSmallestWidthDp) * 31) + this.maxAspectRatioInPortrait.hashCode()) * 31) + this.maxAspectRatioInLandscape.hashCode()) * 31) + this.defaultSplitAttributes.hashCode();
    }

    public String toString() {
        return SplitRule.class.getSimpleName() + "{ tag=" + getTag() + ", defaultSplitAttributes=" + this.defaultSplitAttributes + ", minWidthDp=" + this.minWidthDp + ", minHeightDp=" + this.minHeightDp + ", minSmallestWidthDp=" + this.minSmallestWidthDp + ", maxAspectRatioInPortrait=" + this.maxAspectRatioInPortrait + ", maxAspectRatioInLandscape=" + this.maxAspectRatioInLandscape + '}';
    }
}
