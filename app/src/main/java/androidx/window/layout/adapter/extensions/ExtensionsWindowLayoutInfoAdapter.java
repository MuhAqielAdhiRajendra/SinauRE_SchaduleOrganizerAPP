package androidx.window.layout.adapter.extensions;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import androidx.window.core.Bounds;
import androidx.window.extensions.layout.DisplayFeature;
import androidx.window.extensions.layout.DisplayFoldFeature;
import androidx.window.extensions.layout.SupportedWindowFeatures;
import androidx.window.layout.FoldingFeature;
import androidx.window.layout.HardwareFoldingFeature;
import androidx.window.layout.SupportedPosture;
import androidx.window.layout.WindowLayoutInfo;
import androidx.window.layout.WindowMetrics;
import androidx.window.layout.WindowMetricsCalculatorCompat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ExtensionsWindowLayoutInfoAdapter.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0000¢\u0006\u0002\b\nJ\u001d\u0010\u0004\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0000¢\u0006\u0002\b\nJ\u001d\u0010\u0004\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u000fH\u0000¢\u0006\u0002\b\nJ\u001b\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010\u0012\u001a\u00020\u0013H\u0000¢\u0006\u0002\b\nJ\u0018\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0016\u001a\u00020\u0017H\u0002¨\u0006\u0018"}, d2 = {"Landroidx/window/layout/adapter/extensions/ExtensionsWindowLayoutInfoAdapter;", "", "<init>", "()V", "translate", "Landroidx/window/layout/FoldingFeature;", "windowMetrics", "Landroidx/window/layout/WindowMetrics;", "oemFeature", "Landroidx/window/extensions/layout/FoldingFeature;", "translate$window_release", "Landroidx/window/layout/WindowLayoutInfo;", "context", "Landroid/content/Context;", "info", "Landroidx/window/extensions/layout/WindowLayoutInfo;", "", "Landroidx/window/layout/SupportedPosture;", "features", "Landroidx/window/extensions/layout/SupportedWindowFeatures;", "validBounds", "", "bounds", "Landroidx/window/core/Bounds;", "window_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class ExtensionsWindowLayoutInfoAdapter {
    public static final ExtensionsWindowLayoutInfoAdapter INSTANCE = new ExtensionsWindowLayoutInfoAdapter();

    private ExtensionsWindowLayoutInfoAdapter() {
    }

    public final FoldingFeature translate$window_release(WindowMetrics windowMetrics, androidx.window.extensions.layout.FoldingFeature oemFeature) {
        HardwareFoldingFeature.Type type;
        FoldingFeature.State state;
        Intrinsics.checkNotNullParameter(windowMetrics, "windowMetrics");
        Intrinsics.checkNotNullParameter(oemFeature, "oemFeature");
        switch (oemFeature.getType()) {
            case 1:
                type = HardwareFoldingFeature.Type.INSTANCE.getFOLD();
                break;
            case 2:
                type = HardwareFoldingFeature.Type.INSTANCE.getHINGE();
                break;
            default:
                return null;
        }
        switch (oemFeature.getState()) {
            case 1:
                state = FoldingFeature.State.FLAT;
                break;
            case 2:
                state = FoldingFeature.State.HALF_OPENED;
                break;
            default:
                return null;
        }
        Rect bounds = oemFeature.getBounds();
        Intrinsics.checkNotNullExpressionValue(bounds, "getBounds(...)");
        Bounds bounds2 = new Bounds(bounds);
        if (!validBounds(windowMetrics, bounds2)) {
            return null;
        }
        Rect bounds3 = oemFeature.getBounds();
        Intrinsics.checkNotNullExpressionValue(bounds3, "getBounds(...)");
        return new HardwareFoldingFeature(new Bounds(bounds3), type, state);
    }

    public final WindowLayoutInfo translate$window_release(Context context, androidx.window.extensions.layout.WindowLayoutInfo info) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(info, "info");
        WindowMetricsCalculatorCompat windowMetricsCalculatorCompat = new WindowMetricsCalculatorCompat(null, 1, 0 == true ? 1 : 0);
        if (Build.VERSION.SDK_INT >= 30) {
            return translate$window_release(windowMetricsCalculatorCompat.computeCurrentWindowMetrics(context), info);
        }
        if (Build.VERSION.SDK_INT >= 29 && (context instanceof Activity)) {
            return translate$window_release(windowMetricsCalculatorCompat.computeCurrentWindowMetrics((Activity) context), info);
        }
        throw new UnsupportedOperationException("Display Features are only supported after Q. Display features for non-Activity contexts are not expected to be reported on devices running Q.");
    }

    public final WindowLayoutInfo translate$window_release(WindowMetrics windowMetrics, androidx.window.extensions.layout.WindowLayoutInfo info) {
        Iterable $this$mapNotNull$iv;
        FoldingFeature foldingFeatureTranslate$window_release;
        Intrinsics.checkNotNullParameter(windowMetrics, "windowMetrics");
        Intrinsics.checkNotNullParameter(info, "info");
        Iterable displayFeatures = info.getDisplayFeatures();
        Intrinsics.checkNotNullExpressionValue(displayFeatures, "getDisplayFeatures(...)");
        Iterable $this$mapNotNull$iv2 = displayFeatures;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv$iv : $this$mapNotNull$iv2) {
            androidx.window.extensions.layout.FoldingFeature foldingFeature = (DisplayFeature) element$iv$iv$iv;
            if (foldingFeature instanceof androidx.window.extensions.layout.FoldingFeature) {
                $this$mapNotNull$iv = $this$mapNotNull$iv2;
                foldingFeatureTranslate$window_release = INSTANCE.translate$window_release(windowMetrics, foldingFeature);
            } else {
                $this$mapNotNull$iv = $this$mapNotNull$iv2;
                foldingFeatureTranslate$window_release = null;
            }
            if (foldingFeatureTranslate$window_release != null) {
                destination$iv$iv.add(foldingFeatureTranslate$window_release);
            }
            $this$mapNotNull$iv2 = $this$mapNotNull$iv;
        }
        List features = (List) destination$iv$iv;
        return new WindowLayoutInfo(features);
    }

    public final List<SupportedPosture> translate$window_release(SupportedWindowFeatures features) {
        Intrinsics.checkNotNullParameter(features, "features");
        Iterable displayFoldFeatures = features.getDisplayFoldFeatures();
        Intrinsics.checkNotNullExpressionValue(displayFoldFeatures, "getDisplayFoldFeatures(...)");
        Iterable $this$any$iv = displayFoldFeatures;
        boolean isTableTopSupported = false;
        if (!($this$any$iv instanceof Collection) || !((Collection) $this$any$iv).isEmpty()) {
            Iterator it = $this$any$iv.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Object element$iv = it.next();
                DisplayFoldFeature feature = (DisplayFoldFeature) element$iv;
                if (feature.hasProperties(new int[]{1})) {
                    isTableTopSupported = true;
                    break;
                }
            }
        }
        if (isTableTopSupported) {
            return CollectionsKt.listOf(SupportedPosture.TABLETOP);
        }
        return CollectionsKt.emptyList();
    }

    private final boolean validBounds(WindowMetrics windowMetrics, Bounds bounds) {
        Rect windowBounds = windowMetrics.getBounds();
        if (bounds.isZero()) {
            return false;
        }
        if (bounds.getWidth() != windowBounds.width() && bounds.getHeight() != windowBounds.height()) {
            return false;
        }
        if (bounds.getWidth() >= windowBounds.width() || bounds.getHeight() >= windowBounds.height()) {
            return (bounds.getWidth() == windowBounds.width() && bounds.getHeight() == windowBounds.height()) ? false : true;
        }
        return false;
    }
}
