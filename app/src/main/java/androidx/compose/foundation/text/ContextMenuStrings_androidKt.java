package androidx.compose.foundation.text;

import android.content.res.Resources;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Metadata;

/* JADX INFO: compiled from: ContextMenuStrings.android.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0017\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0001¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"getString", "", TypedValues.Custom.S_STRING, "Landroidx/compose/foundation/text/ContextMenuStrings;", "getString-tk4Tqcs", "(ILandroidx/compose/runtime/Composer;I)Ljava/lang/String;", "foundation"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class ContextMenuStrings_androidKt {
    /* JADX INFO: renamed from: getString-tk4Tqcs, reason: not valid java name */
    public static final String m1533getStringtk4Tqcs(int string, Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, -2083411200, "C(getString)N(string:c#foundation.text.ContextMenuStrings)56@1981L7:ContextMenuStrings.android.kt#423gt5");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-2083411200, $changed, -1, "androidx.compose.foundation.text.getString (ContextMenuStrings.android.kt:55)");
        }
        ProvidableCompositionLocal<Resources> localResources = AndroidCompositionLocals_androidKt.getLocalResources();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
        Object objConsume = $composer.consume(localResources);
        ComposerKt.sourceInformationMarkerEnd($composer);
        Resources resources = (Resources) objConsume;
        String string2 = resources.getString(string);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return string2;
    }
}
