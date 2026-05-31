package androidx.window.embedding;

import android.content.Intent;
import androidx.core.util.Preconditions;
import androidx.window.embedding.SplitAttributes;
import androidx.window.embedding.SplitRule;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SplitPlaceholderRule.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0001'Bw\b\u0010\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f\u0012\b\b\u0003\u0010\r\u001a\u00020\u000e\u0012\b\b\u0003\u0010\u000f\u001a\u00020\u000e\u0012\b\b\u0003\u0010\u0010\u001a\u00020\u000e\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0012\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0012\u0012\u0006\u0010\u0014\u001a\u00020\u0015¢\u0006\u0004\b\u0016\u0010\u0017J\u0016\u0010\u001f\u001a\u00020\u00002\u0006\u0010 \u001a\u00020\u0006H\u0080\u0002¢\u0006\u0002\b!J\u0013\u0010\"\u001a\u00020\n2\b\u0010#\u001a\u0004\u0018\u00010$H\u0096\u0002J\b\u0010%\u001a\u00020\u000eH\u0016J\b\u0010&\u001a\u00020\u0003H\u0016R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u001cR\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001e¨\u0006("}, d2 = {"Landroidx/window/embedding/SplitPlaceholderRule;", "Landroidx/window/embedding/SplitRule;", "tag", "", "filters", "", "Landroidx/window/embedding/ActivityFilter;", "placeholderIntent", "Landroid/content/Intent;", "isSticky", "", "finishPrimaryWithPlaceholder", "Landroidx/window/embedding/SplitRule$FinishBehavior;", "minWidthDp", "", "minHeightDp", "minSmallestWidthDp", "maxAspectRatioInPortrait", "Landroidx/window/embedding/EmbeddingAspectRatio;", "maxAspectRatioInLandscape", "defaultSplitAttributes", "Landroidx/window/embedding/SplitAttributes;", "<init>", "(Ljava/lang/String;Ljava/util/Set;Landroid/content/Intent;ZLandroidx/window/embedding/SplitRule$FinishBehavior;IIILandroidx/window/embedding/EmbeddingAspectRatio;Landroidx/window/embedding/EmbeddingAspectRatio;Landroidx/window/embedding/SplitAttributes;)V", "getFilters", "()Ljava/util/Set;", "getPlaceholderIntent", "()Landroid/content/Intent;", "()Z", "getFinishPrimaryWithPlaceholder", "()Landroidx/window/embedding/SplitRule$FinishBehavior;", "plus", "filter", "plus$window_release", "equals", "other", "", "hashCode", "toString", "Builder", "window_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class SplitPlaceholderRule extends SplitRule {
    private final Set<ActivityFilter> filters;
    private final SplitRule.FinishBehavior finishPrimaryWithPlaceholder;
    private final boolean isSticky;
    private final Intent placeholderIntent;

    public final Set<ActivityFilter> getFilters() {
        return this.filters;
    }

    public final Intent getPlaceholderIntent() {
        return this.placeholderIntent;
    }

    /* JADX INFO: renamed from: isSticky, reason: from getter */
    public final boolean getIsSticky() {
        return this.isSticky;
    }

    public final SplitRule.FinishBehavior getFinishPrimaryWithPlaceholder() {
        return this.finishPrimaryWithPlaceholder;
    }

    public /* synthetic */ SplitPlaceholderRule(String str, Set set, Intent intent, boolean z, SplitRule.FinishBehavior finishBehavior, int i, int i2, int i3, EmbeddingAspectRatio embeddingAspectRatio, EmbeddingAspectRatio embeddingAspectRatio2, SplitAttributes splitAttributes, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this((i4 & 1) != 0 ? null : str, set, intent, z, (i4 & 16) != 0 ? SplitRule.FinishBehavior.ALWAYS : finishBehavior, (i4 & 32) != 0 ? 600 : i, (i4 & 64) != 0 ? 600 : i2, (i4 & 128) != 0 ? 600 : i3, (i4 & 256) != 0 ? SplitRule.SPLIT_MAX_ASPECT_RATIO_PORTRAIT_DEFAULT : embeddingAspectRatio, (i4 & 512) != 0 ? SplitRule.SPLIT_MAX_ASPECT_RATIO_LANDSCAPE_DEFAULT : embeddingAspectRatio2, splitAttributes);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SplitPlaceholderRule(String tag, Set<ActivityFilter> filters, Intent placeholderIntent, boolean isSticky, SplitRule.FinishBehavior finishPrimaryWithPlaceholder, int minWidthDp, int minHeightDp, int minSmallestWidthDp, EmbeddingAspectRatio maxAspectRatioInPortrait, EmbeddingAspectRatio maxAspectRatioInLandscape, SplitAttributes defaultSplitAttributes) {
        super(tag, minWidthDp, minHeightDp, minSmallestWidthDp, maxAspectRatioInPortrait, maxAspectRatioInLandscape, defaultSplitAttributes);
        Intrinsics.checkNotNullParameter(filters, "filters");
        Intrinsics.checkNotNullParameter(placeholderIntent, "placeholderIntent");
        Intrinsics.checkNotNullParameter(finishPrimaryWithPlaceholder, "finishPrimaryWithPlaceholder");
        Intrinsics.checkNotNullParameter(maxAspectRatioInPortrait, "maxAspectRatioInPortrait");
        Intrinsics.checkNotNullParameter(maxAspectRatioInLandscape, "maxAspectRatioInLandscape");
        Intrinsics.checkNotNullParameter(defaultSplitAttributes, "defaultSplitAttributes");
        Preconditions.checkArgument(!Intrinsics.areEqual(finishPrimaryWithPlaceholder, SplitRule.FinishBehavior.NEVER), "NEVER is not a valid configuration for SplitPlaceholderRule. Please use FINISH_ALWAYS or FINISH_ADJACENT instead or refer to the current API.", new Object[0]);
        this.filters = CollectionsKt.toSet(filters);
        this.placeholderIntent = placeholderIntent;
        this.isSticky = isSticky;
        this.finishPrimaryWithPlaceholder = finishPrimaryWithPlaceholder;
    }

    /* JADX INFO: compiled from: SplitPlaceholderRule.kt */
    @Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u00002\u00020\u0001B\u001d\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bB\u0011\b\u0010\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u0007\u0010\u000bJ\u0010\u0010\u001b\u001a\u00020\u00002\b\b\u0001\u0010\u000e\u001a\u00020\u000fJ\u0010\u0010\u001c\u001a\u00020\u00002\b\b\u0001\u0010\u0010\u001a\u00020\u000fJ\u0010\u0010\u001d\u001a\u00020\u00002\b\b\u0001\u0010\u0011\u001a\u00020\u000fJ\u000e\u0010\u001e\u001a\u00020\u00002\u0006\u0010\u001f\u001a\u00020\u0013J\u000e\u0010 \u001a\u00020\u00002\u0006\u0010\u001f\u001a\u00020\u0013J\u000e\u0010!\u001a\u00020\u00002\u0006\u0010\u0015\u001a\u00020\u0016J\u000e\u0010\"\u001a\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u0018J\u000e\u0010#\u001a\u00020\u00002\u0006\u0010\u0019\u001a\u00020\u001aJ\u0010\u0010$\u001a\u00020\u00002\b\u0010\f\u001a\u0004\u0018\u00010\rJ\u0006\u0010%\u001a\u00020\nR\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u000e\u001a\u00020\u000f8\u0002@\u0002X\u0083\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0010\u001a\u00020\u000f8\u0002@\u0002X\u0083\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0011\u001a\u00020\u000f8\u0002@\u0002X\u0083\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Landroidx/window/embedding/SplitPlaceholderRule$Builder;", "", "filters", "", "Landroidx/window/embedding/ActivityFilter;", "placeholderIntent", "Landroid/content/Intent;", "<init>", "(Ljava/util/Set;Landroid/content/Intent;)V", "original", "Landroidx/window/embedding/SplitPlaceholderRule;", "(Landroidx/window/embedding/SplitPlaceholderRule;)V", "tag", "", "minWidthDp", "", "minHeightDp", "minSmallestWidthDp", "maxAspectRatioInPortrait", "Landroidx/window/embedding/EmbeddingAspectRatio;", "maxAspectRatioInLandscape", "finishPrimaryWithPlaceholder", "Landroidx/window/embedding/SplitRule$FinishBehavior;", "isSticky", "", "defaultSplitAttributes", "Landroidx/window/embedding/SplitAttributes;", "setMinWidthDp", "setMinHeightDp", "setMinSmallestWidthDp", "setMaxAspectRatioInPortrait", "aspectRatio", "setMaxAspectRatioInLandscape", "setFinishPrimaryWithPlaceholder", "setSticky", "setDefaultSplitAttributes", "setTag", "build", "window_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Builder {
        private SplitAttributes defaultSplitAttributes;
        private final Set<ActivityFilter> filters;
        private SplitRule.FinishBehavior finishPrimaryWithPlaceholder;
        private boolean isSticky;
        private EmbeddingAspectRatio maxAspectRatioInLandscape;
        private EmbeddingAspectRatio maxAspectRatioInPortrait;
        private int minHeightDp;
        private int minSmallestWidthDp;
        private int minWidthDp;
        private final Intent placeholderIntent;
        private String tag;

        public Builder(Set<ActivityFilter> filters, Intent placeholderIntent) {
            Intrinsics.checkNotNullParameter(filters, "filters");
            Intrinsics.checkNotNullParameter(placeholderIntent, "placeholderIntent");
            this.filters = filters;
            this.placeholderIntent = placeholderIntent;
            this.minWidthDp = 600;
            this.minHeightDp = 600;
            this.minSmallestWidthDp = 600;
            this.maxAspectRatioInPortrait = SplitRule.SPLIT_MAX_ASPECT_RATIO_PORTRAIT_DEFAULT;
            this.maxAspectRatioInLandscape = SplitRule.SPLIT_MAX_ASPECT_RATIO_LANDSCAPE_DEFAULT;
            this.finishPrimaryWithPlaceholder = SplitRule.FinishBehavior.ALWAYS;
            this.defaultSplitAttributes = new SplitAttributes.Builder().build();
        }

        /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
        public Builder(SplitPlaceholderRule original) {
            this(original.getFilters(), original.getPlaceholderIntent());
            Intrinsics.checkNotNullParameter(original, "original");
            setTag(original.getTag()).setMinWidthDp(original.getMinWidthDp()).setMinHeightDp(original.getMinHeightDp()).setMinSmallestWidthDp(original.getMinSmallestWidthDp()).setMaxAspectRatioInPortrait(original.getMaxAspectRatioInPortrait()).setMaxAspectRatioInLandscape(original.getMaxAspectRatioInLandscape()).setFinishPrimaryWithPlaceholder(original.getFinishPrimaryWithPlaceholder()).setSticky(original.getIsSticky()).setDefaultSplitAttributes(original.getDefaultSplitAttributes());
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

        public final Builder setFinishPrimaryWithPlaceholder(SplitRule.FinishBehavior finishPrimaryWithPlaceholder) {
            Intrinsics.checkNotNullParameter(finishPrimaryWithPlaceholder, "finishPrimaryWithPlaceholder");
            Builder $this$setFinishPrimaryWithPlaceholder_u24lambda_u245 = this;
            $this$setFinishPrimaryWithPlaceholder_u24lambda_u245.finishPrimaryWithPlaceholder = finishPrimaryWithPlaceholder;
            return this;
        }

        public final Builder setSticky(boolean isSticky) {
            Builder $this$setSticky_u24lambda_u246 = this;
            $this$setSticky_u24lambda_u246.isSticky = isSticky;
            return this;
        }

        public final Builder setDefaultSplitAttributes(SplitAttributes defaultSplitAttributes) {
            Intrinsics.checkNotNullParameter(defaultSplitAttributes, "defaultSplitAttributes");
            Builder $this$setDefaultSplitAttributes_u24lambda_u247 = this;
            $this$setDefaultSplitAttributes_u24lambda_u247.defaultSplitAttributes = defaultSplitAttributes;
            return this;
        }

        public final Builder setTag(String tag) {
            Builder $this$setTag_u24lambda_u248 = this;
            $this$setTag_u24lambda_u248.tag = tag;
            return this;
        }

        public final SplitPlaceholderRule build() {
            return new SplitPlaceholderRule(this.tag, this.filters, this.placeholderIntent, this.isSticky, this.finishPrimaryWithPlaceholder, this.minWidthDp, this.minHeightDp, this.minSmallestWidthDp, this.maxAspectRatioInPortrait, this.maxAspectRatioInLandscape, this.defaultSplitAttributes);
        }
    }

    public final SplitPlaceholderRule plus$window_release(ActivityFilter filter) {
        Intrinsics.checkNotNullParameter(filter, "filter");
        Set newSet = new LinkedHashSet();
        newSet.addAll(this.filters);
        newSet.add(filter);
        return new Builder(CollectionsKt.toSet(newSet), this.placeholderIntent).setTag(getTag()).setMinWidthDp(getMinWidthDp()).setMinHeightDp(getMinHeightDp()).setMinSmallestWidthDp(getMinSmallestWidthDp()).setMaxAspectRatioInPortrait(getMaxAspectRatioInPortrait()).setMaxAspectRatioInLandscape(getMaxAspectRatioInLandscape()).setSticky(this.isSticky).setFinishPrimaryWithPlaceholder(this.finishPrimaryWithPlaceholder).setDefaultSplitAttributes(getDefaultSplitAttributes()).build();
    }

    @Override // androidx.window.embedding.SplitRule, androidx.window.embedding.EmbeddingRule
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof SplitPlaceholderRule) && super.equals(other) && this.placeholderIntent.filterEquals(((SplitPlaceholderRule) other).placeholderIntent) && this.isSticky == ((SplitPlaceholderRule) other).isSticky && Intrinsics.areEqual(this.finishPrimaryWithPlaceholder, ((SplitPlaceholderRule) other).finishPrimaryWithPlaceholder) && Intrinsics.areEqual(this.filters, ((SplitPlaceholderRule) other).filters);
    }

    @Override // androidx.window.embedding.SplitRule, androidx.window.embedding.EmbeddingRule
    public int hashCode() {
        int result = super.hashCode();
        return (((((((result * 31) + this.placeholderIntent.filterHashCode()) * 31) + Boolean.hashCode(this.isSticky)) * 31) + this.finishPrimaryWithPlaceholder.hashCode()) * 31) + this.filters.hashCode();
    }

    @Override // androidx.window.embedding.SplitRule
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SplitPlaceholderRule{tag=").append(getTag()).append(", defaultSplitAttributes=").append(getDefaultSplitAttributes()).append(", minWidthDp=").append(getMinWidthDp()).append(", minHeightDp=").append(getMinHeightDp()).append(", minSmallestWidthDp=").append(getMinSmallestWidthDp()).append(", maxAspectRatioInPortrait=").append(getMaxAspectRatioInPortrait()).append(", maxAspectRatioInLandscape=").append(getMaxAspectRatioInLandscape()).append(", placeholderIntent=").append(this.placeholderIntent).append(", isSticky=").append(this.isSticky).append(", finishPrimaryWithPlaceholder=").append(this.finishPrimaryWithPlaceholder).append(", filters=").append(this.filters).append('}');
        return sb.toString();
    }
}
