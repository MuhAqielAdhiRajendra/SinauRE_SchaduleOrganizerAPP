package androidx.window.embedding;

import androidx.window.embedding.SplitAttributes;
import androidx.window.embedding.SplitRule;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SplitPairRule.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0001&B{\b\u0000\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\n\u0012\b\b\u0002\u0010\f\u001a\u00020\r\u0012\b\b\u0003\u0010\u000e\u001a\u00020\u000f\u0012\b\b\u0003\u0010\u0010\u001a\u00020\u000f\u0012\b\b\u0003\u0010\u0011\u001a\u00020\u000f\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0013\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u0013¢\u0006\u0004\b\u0015\u0010\u0016J\u0016\u0010\u001e\u001a\u00020\u00002\u0006\u0010\u001f\u001a\u00020\u0004H\u0080\u0002¢\u0006\u0002\b J\u0013\u0010!\u001a\u00020\r2\b\u0010\"\u001a\u0004\u0018\u00010#H\u0096\u0002J\b\u0010$\u001a\u00020\u000fH\u0016J\b\u0010%\u001a\u00020\bH\u0016R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u000b\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001aR\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001d¨\u0006'"}, d2 = {"Landroidx/window/embedding/SplitPairRule;", "Landroidx/window/embedding/SplitRule;", "filters", "", "Landroidx/window/embedding/SplitPairFilter;", "defaultSplitAttributes", "Landroidx/window/embedding/SplitAttributes;", "tag", "", "finishPrimaryWithSecondary", "Landroidx/window/embedding/SplitRule$FinishBehavior;", "finishSecondaryWithPrimary", "clearTop", "", "minWidthDp", "", "minHeightDp", "minSmallestWidthDp", "maxAspectRatioInPortrait", "Landroidx/window/embedding/EmbeddingAspectRatio;", "maxAspectRatioInLandscape", "<init>", "(Ljava/util/Set;Landroidx/window/embedding/SplitAttributes;Ljava/lang/String;Landroidx/window/embedding/SplitRule$FinishBehavior;Landroidx/window/embedding/SplitRule$FinishBehavior;ZIIILandroidx/window/embedding/EmbeddingAspectRatio;Landroidx/window/embedding/EmbeddingAspectRatio;)V", "getFilters", "()Ljava/util/Set;", "getFinishPrimaryWithSecondary", "()Landroidx/window/embedding/SplitRule$FinishBehavior;", "getFinishSecondaryWithPrimary", "getClearTop", "()Z", "plus", "filter", "plus$window_release", "equals", "other", "", "hashCode", "toString", "Builder", "window_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class SplitPairRule extends SplitRule {
    private final boolean clearTop;
    private final Set<SplitPairFilter> filters;
    private final SplitRule.FinishBehavior finishPrimaryWithSecondary;
    private final SplitRule.FinishBehavior finishSecondaryWithPrimary;

    public /* synthetic */ SplitPairRule(Set set, SplitAttributes splitAttributes, String str, SplitRule.FinishBehavior finishBehavior, SplitRule.FinishBehavior finishBehavior2, boolean z, int i, int i2, int i3, EmbeddingAspectRatio embeddingAspectRatio, EmbeddingAspectRatio embeddingAspectRatio2, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this(set, splitAttributes, (i4 & 4) != 0 ? null : str, (i4 & 8) != 0 ? SplitRule.FinishBehavior.NEVER : finishBehavior, (i4 & 16) != 0 ? SplitRule.FinishBehavior.ALWAYS : finishBehavior2, (i4 & 32) != 0 ? false : z, (i4 & 64) != 0 ? 600 : i, (i4 & 128) != 0 ? 600 : i2, (i4 & 256) != 0 ? 600 : i3, (i4 & 512) != 0 ? SplitRule.SPLIT_MAX_ASPECT_RATIO_PORTRAIT_DEFAULT : embeddingAspectRatio, (i4 & 1024) != 0 ? SplitRule.SPLIT_MAX_ASPECT_RATIO_LANDSCAPE_DEFAULT : embeddingAspectRatio2);
    }

    public final Set<SplitPairFilter> getFilters() {
        return this.filters;
    }

    public final SplitRule.FinishBehavior getFinishPrimaryWithSecondary() {
        return this.finishPrimaryWithSecondary;
    }

    public final SplitRule.FinishBehavior getFinishSecondaryWithPrimary() {
        return this.finishSecondaryWithPrimary;
    }

    public final boolean getClearTop() {
        return this.clearTop;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SplitPairRule(Set<SplitPairFilter> filters, SplitAttributes defaultSplitAttributes, String tag, SplitRule.FinishBehavior finishPrimaryWithSecondary, SplitRule.FinishBehavior finishSecondaryWithPrimary, boolean clearTop, int minWidthDp, int minHeightDp, int minSmallestWidthDp, EmbeddingAspectRatio maxAspectRatioInPortrait, EmbeddingAspectRatio maxAspectRatioInLandscape) {
        super(tag, minWidthDp, minHeightDp, minSmallestWidthDp, maxAspectRatioInPortrait, maxAspectRatioInLandscape, defaultSplitAttributes);
        Intrinsics.checkNotNullParameter(filters, "filters");
        Intrinsics.checkNotNullParameter(defaultSplitAttributes, "defaultSplitAttributes");
        Intrinsics.checkNotNullParameter(finishPrimaryWithSecondary, "finishPrimaryWithSecondary");
        Intrinsics.checkNotNullParameter(finishSecondaryWithPrimary, "finishSecondaryWithPrimary");
        Intrinsics.checkNotNullParameter(maxAspectRatioInPortrait, "maxAspectRatioInPortrait");
        Intrinsics.checkNotNullParameter(maxAspectRatioInLandscape, "maxAspectRatioInLandscape");
        this.filters = filters;
        this.finishPrimaryWithSecondary = finishPrimaryWithSecondary;
        this.finishSecondaryWithPrimary = finishSecondaryWithPrimary;
        this.clearTop = clearTop;
    }

    /* JADX INFO: compiled from: SplitPairRule.kt */
    @Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006B\u0011\b\u0010\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\u0005\u0010\tJ\u0010\u0010\u001a\u001a\u00020\u00002\b\b\u0001\u0010\f\u001a\u00020\rJ\u0010\u0010\u001b\u001a\u00020\u00002\b\b\u0001\u0010\u000e\u001a\u00020\rJ\u0010\u0010\u001c\u001a\u00020\u00002\b\b\u0001\u0010\u000f\u001a\u00020\rJ\u000e\u0010\u001d\u001a\u00020\u00002\u0006\u0010\u001e\u001a\u00020\u0011J\u000e\u0010\u001f\u001a\u00020\u00002\u0006\u0010\u001e\u001a\u00020\u0011J\u000e\u0010 \u001a\u00020\u00002\u0006\u0010\u0013\u001a\u00020\u0014J\u000e\u0010!\u001a\u00020\u00002\u0006\u0010\u0015\u001a\u00020\u0014J\u000e\u0010\"\u001a\u00020\u00002\u0006\u0010\u0016\u001a\u00020\u0017J\u000e\u0010#\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u0019J\u0010\u0010$\u001a\u00020\u00002\b\u0010\n\u001a\u0004\u0018\u00010\u000bJ\u0006\u0010%\u001a\u00020\bR\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\f\u001a\u00020\r8\u0002@\u0002X\u0083\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u000e\u001a\u00020\r8\u0002@\u0002X\u0083\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u000f\u001a\u00020\r8\u0002@\u0002X\u0083\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Landroidx/window/embedding/SplitPairRule$Builder;", "", "filters", "", "Landroidx/window/embedding/SplitPairFilter;", "<init>", "(Ljava/util/Set;)V", "original", "Landroidx/window/embedding/SplitPairRule;", "(Landroidx/window/embedding/SplitPairRule;)V", "tag", "", "minWidthDp", "", "minHeightDp", "minSmallestWidthDp", "maxAspectRatioInPortrait", "Landroidx/window/embedding/EmbeddingAspectRatio;", "maxAspectRatioInLandscape", "finishPrimaryWithSecondary", "Landroidx/window/embedding/SplitRule$FinishBehavior;", "finishSecondaryWithPrimary", "clearTop", "", "defaultSplitAttributes", "Landroidx/window/embedding/SplitAttributes;", "setMinWidthDp", "setMinHeightDp", "setMinSmallestWidthDp", "setMaxAspectRatioInPortrait", "aspectRatio", "setMaxAspectRatioInLandscape", "setFinishPrimaryWithSecondary", "setFinishSecondaryWithPrimary", "setClearTop", "setDefaultSplitAttributes", "setTag", "build", "window_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Builder {
        private boolean clearTop;
        private SplitAttributes defaultSplitAttributes;
        private final Set<SplitPairFilter> filters;
        private SplitRule.FinishBehavior finishPrimaryWithSecondary;
        private SplitRule.FinishBehavior finishSecondaryWithPrimary;
        private EmbeddingAspectRatio maxAspectRatioInLandscape;
        private EmbeddingAspectRatio maxAspectRatioInPortrait;
        private int minHeightDp;
        private int minSmallestWidthDp;
        private int minWidthDp;
        private String tag;

        public Builder(Set<SplitPairFilter> filters) {
            Intrinsics.checkNotNullParameter(filters, "filters");
            this.filters = filters;
            this.minWidthDp = 600;
            this.minHeightDp = 600;
            this.minSmallestWidthDp = 600;
            this.maxAspectRatioInPortrait = SplitRule.SPLIT_MAX_ASPECT_RATIO_PORTRAIT_DEFAULT;
            this.maxAspectRatioInLandscape = SplitRule.SPLIT_MAX_ASPECT_RATIO_LANDSCAPE_DEFAULT;
            this.finishPrimaryWithSecondary = SplitRule.FinishBehavior.NEVER;
            this.finishSecondaryWithPrimary = SplitRule.FinishBehavior.ALWAYS;
            this.defaultSplitAttributes = new SplitAttributes.Builder().build();
        }

        /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
        public Builder(SplitPairRule original) {
            this(original.getFilters());
            Intrinsics.checkNotNullParameter(original, "original");
            setTag(original.getTag()).setMinWidthDp(original.getMinWidthDp()).setMinHeightDp(original.getMinHeightDp()).setMinSmallestWidthDp(original.getMinSmallestWidthDp()).setMaxAspectRatioInPortrait(original.getMaxAspectRatioInPortrait()).setMaxAspectRatioInLandscape(original.getMaxAspectRatioInLandscape()).setFinishPrimaryWithSecondary(original.getFinishPrimaryWithSecondary()).setFinishSecondaryWithPrimary(original.getFinishSecondaryWithPrimary()).setClearTop(original.getClearTop()).setDefaultSplitAttributes(original.getDefaultSplitAttributes());
        }

        public final Builder setMinWidthDp(int minWidthDp) {
            Builder $this$setMinWidthDp_u24lambda_u240 = this;
            $this$setMinWidthDp_u24lambda_u240.minWidthDp = minWidthDp;
            return this;
        }

        public final Builder setMinHeightDp(int minHeightDp) {
            Builder $this$setMinHeightDp_u24lambda_u241 = this;
            $this$setMinHeightDp_u24lambda_u241.minHeightDp = minHeightDp;
            return this;
        }

        public final Builder setMinSmallestWidthDp(int minSmallestWidthDp) {
            Builder $this$setMinSmallestWidthDp_u24lambda_u242 = this;
            $this$setMinSmallestWidthDp_u24lambda_u242.minSmallestWidthDp = minSmallestWidthDp;
            return this;
        }

        public final Builder setMaxAspectRatioInPortrait(EmbeddingAspectRatio aspectRatio) {
            Intrinsics.checkNotNullParameter(aspectRatio, "aspectRatio");
            Builder $this$setMaxAspectRatioInPortrait_u24lambda_u243 = this;
            $this$setMaxAspectRatioInPortrait_u24lambda_u243.maxAspectRatioInPortrait = aspectRatio;
            return this;
        }

        public final Builder setMaxAspectRatioInLandscape(EmbeddingAspectRatio aspectRatio) {
            Intrinsics.checkNotNullParameter(aspectRatio, "aspectRatio");
            Builder $this$setMaxAspectRatioInLandscape_u24lambda_u244 = this;
            $this$setMaxAspectRatioInLandscape_u24lambda_u244.maxAspectRatioInLandscape = aspectRatio;
            return this;
        }

        public final Builder setFinishPrimaryWithSecondary(SplitRule.FinishBehavior finishPrimaryWithSecondary) {
            Intrinsics.checkNotNullParameter(finishPrimaryWithSecondary, "finishPrimaryWithSecondary");
            Builder $this$setFinishPrimaryWithSecondary_u24lambda_u245 = this;
            $this$setFinishPrimaryWithSecondary_u24lambda_u245.finishPrimaryWithSecondary = finishPrimaryWithSecondary;
            return this;
        }

        public final Builder setFinishSecondaryWithPrimary(SplitRule.FinishBehavior finishSecondaryWithPrimary) {
            Intrinsics.checkNotNullParameter(finishSecondaryWithPrimary, "finishSecondaryWithPrimary");
            Builder $this$setFinishSecondaryWithPrimary_u24lambda_u246 = this;
            $this$setFinishSecondaryWithPrimary_u24lambda_u246.finishSecondaryWithPrimary = finishSecondaryWithPrimary;
            return this;
        }

        public final Builder setClearTop(boolean clearTop) {
            Builder $this$setClearTop_u24lambda_u247 = this;
            $this$setClearTop_u24lambda_u247.clearTop = clearTop;
            return this;
        }

        public final Builder setDefaultSplitAttributes(SplitAttributes defaultSplitAttributes) {
            Intrinsics.checkNotNullParameter(defaultSplitAttributes, "defaultSplitAttributes");
            Builder $this$setDefaultSplitAttributes_u24lambda_u248 = this;
            $this$setDefaultSplitAttributes_u24lambda_u248.defaultSplitAttributes = defaultSplitAttributes;
            return this;
        }

        public final Builder setTag(String tag) {
            Builder $this$setTag_u24lambda_u249 = this;
            $this$setTag_u24lambda_u249.tag = tag;
            return this;
        }

        public final SplitPairRule build() {
            return new SplitPairRule(this.filters, this.defaultSplitAttributes, this.tag, this.finishPrimaryWithSecondary, this.finishSecondaryWithPrimary, this.clearTop, this.minWidthDp, this.minHeightDp, this.minSmallestWidthDp, this.maxAspectRatioInPortrait, this.maxAspectRatioInLandscape);
        }
    }

    public final SplitPairRule plus$window_release(SplitPairFilter filter) {
        Intrinsics.checkNotNullParameter(filter, "filter");
        Set newSet = new LinkedHashSet();
        newSet.addAll(this.filters);
        newSet.add(filter);
        return new Builder((Set<SplitPairFilter>) CollectionsKt.toSet(newSet)).setTag(getTag()).setMinWidthDp(getMinWidthDp()).setMinHeightDp(getMinHeightDp()).setMinSmallestWidthDp(getMinSmallestWidthDp()).setMaxAspectRatioInPortrait(getMaxAspectRatioInPortrait()).setMaxAspectRatioInLandscape(getMaxAspectRatioInLandscape()).setFinishPrimaryWithSecondary(this.finishPrimaryWithSecondary).setFinishSecondaryWithPrimary(this.finishSecondaryWithPrimary).setClearTop(this.clearTop).setDefaultSplitAttributes(getDefaultSplitAttributes()).build();
    }

    @Override // androidx.window.embedding.SplitRule, androidx.window.embedding.EmbeddingRule
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof SplitPairRule) && super.equals(other) && Intrinsics.areEqual(this.filters, ((SplitPairRule) other).filters) && Intrinsics.areEqual(this.finishPrimaryWithSecondary, ((SplitPairRule) other).finishPrimaryWithSecondary) && Intrinsics.areEqual(this.finishSecondaryWithPrimary, ((SplitPairRule) other).finishSecondaryWithPrimary) && this.clearTop == ((SplitPairRule) other).clearTop;
    }

    @Override // androidx.window.embedding.SplitRule, androidx.window.embedding.EmbeddingRule
    public int hashCode() {
        int result = super.hashCode();
        return (((((((result * 31) + this.filters.hashCode()) * 31) + this.finishPrimaryWithSecondary.hashCode()) * 31) + this.finishSecondaryWithPrimary.hashCode()) * 31) + Boolean.hashCode(this.clearTop);
    }

    @Override // androidx.window.embedding.SplitRule
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(SplitPairRule.class.getSimpleName()).append("{tag=").append(getTag()).append(", defaultSplitAttributes=").append(getDefaultSplitAttributes()).append(", minWidthDp=").append(getMinWidthDp()).append(", minHeightDp=").append(getMinHeightDp()).append(", minSmallestWidthDp=").append(getMinSmallestWidthDp()).append(", maxAspectRatioInPortrait=").append(getMaxAspectRatioInPortrait()).append(", maxAspectRatioInLandscape=").append(getMaxAspectRatioInLandscape()).append(", clearTop=").append(this.clearTop).append(", finishPrimaryWithSecondary=").append(this.finishPrimaryWithSecondary).append(", finishSecondaryWithPrimary=").append(this.finishSecondaryWithPrimary).append(", filters=").append(this.filters);
        sb.append('}');
        return sb.toString();
    }
}
