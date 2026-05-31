package androidx.window.embedding;

import android.content.res.Configuration;
import androidx.window.layout.WindowLayoutInfo;
import androidx.window.layout.WindowMetrics;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SplitAttributesCalculatorParams.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\u0018\u00002\u00020\u0001B;\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\u0004\b\u000e\u0010\u000fJ\b\u0010\u001b\u001a\u00020\rH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0013\u0010\n\u001a\u00020\u000b8G¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0018R\u0013\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001a¨\u0006\u001c"}, d2 = {"Landroidx/window/embedding/SplitAttributesCalculatorParams;", "", "parentWindowMetrics", "Landroidx/window/layout/WindowMetrics;", "parentConfiguration", "Landroid/content/res/Configuration;", "parentWindowLayoutInfo", "Landroidx/window/layout/WindowLayoutInfo;", "defaultSplitAttributes", "Landroidx/window/embedding/SplitAttributes;", "areDefaultConstraintsSatisfied", "", "splitRuleTag", "", "<init>", "(Landroidx/window/layout/WindowMetrics;Landroid/content/res/Configuration;Landroidx/window/layout/WindowLayoutInfo;Landroidx/window/embedding/SplitAttributes;ZLjava/lang/String;)V", "getParentWindowMetrics", "()Landroidx/window/layout/WindowMetrics;", "getParentConfiguration", "()Landroid/content/res/Configuration;", "getParentWindowLayoutInfo", "()Landroidx/window/layout/WindowLayoutInfo;", "getDefaultSplitAttributes", "()Landroidx/window/embedding/SplitAttributes;", "()Z", "getSplitRuleTag", "()Ljava/lang/String;", "toString", "window_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class SplitAttributesCalculatorParams {
    private final boolean areDefaultConstraintsSatisfied;
    private final SplitAttributes defaultSplitAttributes;

    /* JADX INFO: renamed from: parentConfiguration, reason: from kotlin metadata and from toString */
    private final Configuration configuration;

    /* JADX INFO: renamed from: parentWindowLayoutInfo, reason: from kotlin metadata and from toString */
    private final WindowLayoutInfo windowLayoutInfo;
    private final WindowMetrics parentWindowMetrics;

    /* JADX INFO: renamed from: splitRuleTag, reason: from kotlin metadata and from toString */
    private final String tag;

    public SplitAttributesCalculatorParams(WindowMetrics parentWindowMetrics, Configuration parentConfiguration, WindowLayoutInfo parentWindowLayoutInfo, SplitAttributes defaultSplitAttributes, boolean areDefaultConstraintsSatisfied, String splitRuleTag) {
        Intrinsics.checkNotNullParameter(parentWindowMetrics, "parentWindowMetrics");
        Intrinsics.checkNotNullParameter(parentConfiguration, "parentConfiguration");
        Intrinsics.checkNotNullParameter(parentWindowLayoutInfo, "parentWindowLayoutInfo");
        Intrinsics.checkNotNullParameter(defaultSplitAttributes, "defaultSplitAttributes");
        this.parentWindowMetrics = parentWindowMetrics;
        this.configuration = parentConfiguration;
        this.windowLayoutInfo = parentWindowLayoutInfo;
        this.defaultSplitAttributes = defaultSplitAttributes;
        this.areDefaultConstraintsSatisfied = areDefaultConstraintsSatisfied;
        this.tag = splitRuleTag;
    }

    public final WindowMetrics getParentWindowMetrics() {
        return this.parentWindowMetrics;
    }

    /* JADX INFO: renamed from: getParentConfiguration, reason: from getter */
    public final Configuration getConfiguration() {
        return this.configuration;
    }

    /* JADX INFO: renamed from: getParentWindowLayoutInfo, reason: from getter */
    public final WindowLayoutInfo getWindowLayoutInfo() {
        return this.windowLayoutInfo;
    }

    public final SplitAttributes getDefaultSplitAttributes() {
        return this.defaultSplitAttributes;
    }

    /* JADX INFO: renamed from: areDefaultConstraintsSatisfied, reason: from getter */
    public final boolean getAreDefaultConstraintsSatisfied() {
        return this.areDefaultConstraintsSatisfied;
    }

    /* JADX INFO: renamed from: getSplitRuleTag, reason: from getter */
    public final String getTag() {
        return this.tag;
    }

    public String toString() {
        return SplitAttributesCalculatorParams.class.getSimpleName() + ":{windowMetrics=" + this.parentWindowMetrics + ", configuration=" + this.configuration + ", windowLayoutInfo=" + this.windowLayoutInfo + ", defaultSplitAttributes=" + this.defaultSplitAttributes + ", areDefaultConstraintsSatisfied=" + this.areDefaultConstraintsSatisfied + ", tag=" + this.tag + '}';
    }
}
