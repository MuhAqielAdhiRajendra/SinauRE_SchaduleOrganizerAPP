package androidx.compose.foundation.contextmenu;

import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function8;

/* JADX INFO: compiled from: ContextMenuUi.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
public final class ComposableSingletons$ContextMenuUiKt {
    public static final ComposableSingletons$ContextMenuUiKt INSTANCE = new ComposableSingletons$ContextMenuUiKt();

    /* JADX INFO: renamed from: lambda$-1571120048 */
    private static Function8<Modifier, String, Boolean, ContextMenuColors, Function3<? super Color, ? super Composer, ? super Integer, Unit>, Function0<Unit>, Composer, Integer, Unit> f1lambda$1571120048 = ComposableLambdaKt.composableLambdaInstance(-1571120048, false, new Function8() { // from class: androidx.compose.foundation.contextmenu.ComposableSingletons$ContextMenuUiKt$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function8
        public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8) {
            return ComposableSingletons$ContextMenuUiKt.lambda__1571120048$lambda$0((Modifier) obj, (String) obj2, ((Boolean) obj3).booleanValue(), (ContextMenuColors) obj4, (Function3) obj5, (Function0) obj6, (Composer) obj7, ((Integer) obj8).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-1455401925 */
    private static Function3<ContextMenuColors, Composer, Integer, Unit> f0lambda$1455401925 = ComposableLambdaKt.composableLambdaInstance(-1455401925, false, new Function3() { // from class: androidx.compose.foundation.contextmenu.ComposableSingletons$ContextMenuUiKt$$ExternalSyntheticLambda1
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$ContextMenuUiKt.lambda__1455401925$lambda$0((ContextMenuColors) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });

    /* JADX INFO: renamed from: getLambda$-1455401925$foundation */
    public final Function3<ContextMenuColors, Composer, Integer, Unit> m392getLambda$1455401925$foundation() {
        return f0lambda$1455401925;
    }

    /* JADX INFO: renamed from: getLambda$-1571120048$foundation */
    public final Function8<Modifier, String, Boolean, ContextMenuColors, Function3<? super Color, ? super Composer, ? super Integer, Unit>, Function0<Unit>, Composer, Integer, Unit> m393getLambda$1571120048$foundation() {
        return f1lambda$1571120048;
    }

    static final Unit lambda__1571120048$lambda$0(Modifier modifier, String label, boolean enabled, ContextMenuColors colors, Function3 leadingIcon, Function0 onClick, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "CN(modifier,label,enabled,colors,leadingIcon,onClick)136@5002L71:ContextMenuUi.kt#3xeu6s");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer.changed(modifier) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer.changed(label) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer.changed(enabled) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            $dirty |= $composer.changed(colors) ? 2048 : 1024;
        }
        if (($changed & 24576) == 0) {
            $dirty |= $composer.changedInstance(leadingIcon) ? 16384 : 8192;
        }
        if ((196608 & $changed) == 0) {
            $dirty |= $composer.changedInstance(onClick) ? 131072 : 65536;
        }
        int $dirty2 = $dirty;
        if (!$composer.shouldExecute((599187 & $dirty2) != 599186, $dirty2 & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1571120048, $dirty2, -1, "androidx.compose.foundation.contextmenu.ComposableSingletons$ContextMenuUiKt.lambda$-1571120048.<anonymous> (ContextMenuUi.kt:136)");
            }
            ContextMenuUiKt.ContextMenuItem(label, enabled, colors, modifier, leadingIcon, onClick, $composer, (($dirty2 >> 3) & 14) | (($dirty2 >> 3) & 112) | (($dirty2 >> 3) & 896) | (($dirty2 << 9) & 7168) | (57344 & $dirty2) | (458752 & $dirty2), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__1455401925$lambda$0(ContextMenuColors colors, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "CN(colors)305@11326L290:ContextMenuUi.kt#3xeu6s");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer.changed(colors) ? 4 : 2;
        }
        if (!$composer.shouldExecute(($dirty & 19) != 18, $dirty & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1455401925, $dirty, -1, "androidx.compose.foundation.contextmenu.ComposableSingletons$ContextMenuUiKt.lambda$-1455401925.<anonymous> (ContextMenuUi.kt:305)");
            }
            BoxKt.Box(BackgroundKt.m286backgroundbw27NRU$default(SizeKt.m1101height3ABfNKs(SizeKt.fillMaxWidth$default(PaddingKt.m1050paddingVpY3zN4$default(Modifier.INSTANCE, 0.0f, ContextMenuSpec.INSTANCE.m404getDividerVerticalPaddingD9Ej5fM(), 1, null), 0.0f, 1, null), ContextMenuSpec.INSTANCE.m403getDividerHeightD9Ej5fM()), colors.getIconColor(), null, 2, null), $composer, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }
}
