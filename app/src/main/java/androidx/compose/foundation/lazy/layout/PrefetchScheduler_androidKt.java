package androidx.compose.foundation.lazy.layout;

import android.view.View;
import androidx.compose.foundation.R;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import kotlin.Metadata;

/* JADX INFO: compiled from: PrefetchScheduler.android.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0005*\u0001\u0004\u001a\r\u0010\u0000\u001a\u00020\u0001H\u0001¢\u0006\u0002\u0010\u0002\"\u001a\u0010\u0003\u001a\u0004\u0018\u00010\u00048\u0002X\u0083\u0004¢\u0006\n\n\u0002\u0010\u0007\u0012\u0004\b\u0005\u0010\u0006¨\u0006\b"}, d2 = {"rememberDefaultPrefetchScheduler", "Landroidx/compose/foundation/lazy/layout/PrefetchScheduler;", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/foundation/lazy/layout/PrefetchScheduler;", "RobolectricImpl", "androidx/compose/foundation/lazy/layout/PrefetchScheduler_androidKt$RobolectricImpl$1", "getRobolectricImpl$annotations", "()V", "Landroidx/compose/foundation/lazy/layout/PrefetchScheduler_androidKt$RobolectricImpl$1;", "foundation"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class PrefetchScheduler_androidKt {
    private static final PrefetchScheduler_androidKt$RobolectricImpl$1 RobolectricImpl;

    private static /* synthetic */ void getRobolectricImpl$annotations() {
    }

    public static final PrefetchScheduler rememberDefaultPrefetchScheduler(Composer $composer, int $changed) {
        PrefetchScheduler value$iv;
        PrefetchScheduler_androidKt$RobolectricImpl$1 prefetchScheduler_androidKt$RobolectricImpl$1;
        ComposerKt.sourceInformationMarkerStart($composer, 1141871251, "C(rememberDefaultPrefetchScheduler):PrefetchScheduler.android.kt#wow0x6");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1141871251, $changed, -1, "androidx.compose.foundation.lazy.layout.rememberDefaultPrefetchScheduler (PrefetchScheduler.android.kt:36)");
        }
        if (RobolectricImpl != null) {
            $composer.startReplaceGroup(1345554384);
            $composer.endReplaceGroup();
            prefetchScheduler_androidKt$RobolectricImpl$1 = RobolectricImpl;
        } else {
            $composer.startReplaceGroup(1345603457);
            ComposerKt.sourceInformation($composer, "40@1441L7,41@1457L377");
            ProvidableCompositionLocal<View> localView = AndroidCompositionLocals_androidKt.getLocalView();
            ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = $composer.consume(localView);
            ComposerKt.sourceInformationMarkerEnd($composer);
            View view = (View) objConsume;
            ComposerKt.sourceInformationMarkerStart($composer, 2121617996, "CC(remember):PrefetchScheduler.android.kt#9igjgp");
            boolean invalid$iv = $composer.changed(view);
            Object it$iv = $composer.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                Object tag = view.getTag(R.id.compose_prefetch_scheduler);
                PrefetchScheduler existing = tag instanceof PrefetchScheduler ? (PrefetchScheduler) tag : null;
                if (existing == null) {
                    PrefetchScheduler scheduler = new AndroidPrefetchScheduler(view);
                    view.setTag(R.id.compose_prefetch_scheduler, scheduler);
                    value$iv = scheduler;
                } else {
                    value$iv = existing;
                }
                $composer.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endReplaceGroup();
            prefetchScheduler_androidKt$RobolectricImpl$1 = (PrefetchScheduler) it$iv;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return prefetchScheduler_androidKt$RobolectricImpl$1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0021  */
    static {
        /*
            java.lang.String r0 = android.os.Build.FINGERPRINT
            if (r0 == 0) goto L21
            java.lang.String r0 = android.os.Build.FINGERPRINT
            java.util.Locale r1 = java.util.Locale.ROOT
            java.lang.String r0 = r0.toLowerCase(r1)
            java.lang.String r1 = "toLowerCase(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            java.lang.String r1 = "robolectric"
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L21
            androidx.compose.foundation.lazy.layout.PrefetchScheduler_androidKt$RobolectricImpl$1 r0 = new androidx.compose.foundation.lazy.layout.PrefetchScheduler_androidKt$RobolectricImpl$1
            r0.<init>()
            goto L22
        L21:
            r0 = 0
        L22:
            androidx.compose.foundation.lazy.layout.PrefetchScheduler_androidKt.RobolectricImpl = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.lazy.layout.PrefetchScheduler_androidKt.<clinit>():void");
    }
}
