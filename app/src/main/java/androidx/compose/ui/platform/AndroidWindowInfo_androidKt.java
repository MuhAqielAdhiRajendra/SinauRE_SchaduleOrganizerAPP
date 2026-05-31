package androidx.compose.ui.platform;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Configuration;
import android.inputmethodservice.InputMethodService;
import android.view.View;
import androidx.compose.ui.platform.DerivedSize;
import androidx.compose.ui.unit.AndroidDensity_androidKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.DpKt;
import androidx.compose.ui.unit.IntSize;
import androidx.window.layout.WindowMetrics;
import androidx.window.layout.WindowMetricsCalculator;
import kotlin.Metadata;

/* JADX INFO: compiled from: AndroidWindowInfo.android.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0000\u001a\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0002¨\u0006\u0007"}, d2 = {"calculateWindowSize", "Landroidx/compose/ui/platform/DerivedSize;", "view", "Landroid/view/View;", "tryUnwrapContext", "Landroid/content/Context;", "context", "ui"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class AndroidWindowInfo_androidKt {
    public static final DerivedSize calculateWindowSize(View view) {
        Context context = view.getContext();
        Context unwrapped = tryUnwrapContext(context);
        if (unwrapped != null) {
            WindowMetrics metrics = WindowMetricsCalculator.INSTANCE.getOrCreate().computeCurrentWindowMetrics(unwrapped);
            DerivedSize.Companion companion = DerivedSize.INSTANCE;
            int width$iv = metrics.getBounds().width();
            int height$iv = metrics.getBounds().height();
            return companion.m7260fromPxSizeviCIZxY(IntSize.m8316constructorimpl((((long) width$iv) << 32) | (((long) height$iv) & 4294967295L)), AndroidDensity_androidKt.Density(unwrapped));
        }
        Configuration configuration = context.getResources().getConfiguration();
        Density density = AndroidDensity_androidKt.Density(context);
        DerivedSize.Companion companion2 = DerivedSize.INSTANCE;
        int $this$dp$iv = configuration.screenWidthDp;
        float fM8150constructorimpl = Dp.m8150constructorimpl($this$dp$iv);
        int $i$f$getDp = configuration.screenHeightDp;
        return companion2.m7259fromDpSizeitqla9I(DpKt.m8172DpSizeYgX7TsA(fM8150constructorimpl, Dp.m8150constructorimpl($i$f$getDp)), density);
    }

    private static final Context tryUnwrapContext(Context context) {
        for (Context iterator = context; iterator instanceof ContextWrapper; iterator = ((ContextWrapper) iterator).getBaseContext()) {
            if (iterator instanceof Activity) {
                return iterator;
            }
            if (iterator instanceof InputMethodService) {
                return iterator;
            }
            if (iterator instanceof Application) {
                return iterator;
            }
            if (((ContextWrapper) iterator).getBaseContext() == null) {
                return null;
            }
        }
        return null;
    }
}
