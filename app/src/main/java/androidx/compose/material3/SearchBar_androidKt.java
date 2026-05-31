package androidx.compose.material3;

import android.content.res.Configuration;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;

/* JADX INFO: compiled from: SearchBar.android.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\r\u0010\u0000\u001a\u00020\u0001H\u0001¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"getWindowContainerHeight", "Landroidx/compose/ui/unit/Dp;", "(Landroidx/compose/runtime/Composer;I)F", "material3"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class SearchBar_androidKt {
    public static final float getWindowContainerHeight(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, 1909557323, "C(getWindowContainerHeight)27@1060L7:SearchBar.android.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1909557323, $changed, -1, "androidx.compose.material3.getWindowContainerHeight (SearchBar.android.kt:27)");
        }
        ProvidableCompositionLocal<Configuration> localConfiguration = AndroidCompositionLocals_androidKt.getLocalConfiguration();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
        Object objConsume = $composer.consume(localConfiguration);
        ComposerKt.sourceInformationMarkerEnd($composer);
        int $this$dp$iv = ((Configuration) objConsume).screenHeightDp;
        float fM8150constructorimpl = Dp.m8150constructorimpl($this$dp$iv);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return fM8150constructorimpl;
    }
}
