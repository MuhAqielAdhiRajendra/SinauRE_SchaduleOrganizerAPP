package androidx.compose.foundation.text.contextmenu.internal;

import androidx.compose.foundation.text.contextmenu.data.TextContextMenuSession;
import androidx.compose.foundation.text.contextmenu.provider.TextContextMenuDataProvider;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.layout.LayoutCoordinates;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function5;

/* JADX INFO: compiled from: DefaultTextContextMenuDropdownProvider.android.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
public final class ComposableSingletons$DefaultTextContextMenuDropdownProvider_androidKt {
    public static final ComposableSingletons$DefaultTextContextMenuDropdownProvider_androidKt INSTANCE = new ComposableSingletons$DefaultTextContextMenuDropdownProvider_androidKt();
    private static Function5<TextContextMenuSession, TextContextMenuDataProvider, Function0<? extends LayoutCoordinates>, Composer, Integer, Unit> lambda$129995601 = ComposableLambdaKt.composableLambdaInstance(129995601, false, new Function5() { // from class: androidx.compose.foundation.text.contextmenu.internal.ComposableSingletons$DefaultTextContextMenuDropdownProvider_androidKt$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function5
        public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
            return ComposableSingletons$DefaultTextContextMenuDropdownProvider_androidKt.lambda_129995601$lambda$0((TextContextMenuSession) obj, (TextContextMenuDataProvider) obj2, (Function0) obj3, (Composer) obj4, ((Integer) obj5).intValue());
        }
    });
    private static Function5<TextContextMenuSession, TextContextMenuDataProvider, Function0<? extends LayoutCoordinates>, Composer, Integer, Unit> lambda$636288403 = ComposableLambdaKt.composableLambdaInstance(636288403, false, new Function5() { // from class: androidx.compose.foundation.text.contextmenu.internal.ComposableSingletons$DefaultTextContextMenuDropdownProvider_androidKt$$ExternalSyntheticLambda1
        @Override // kotlin.jvm.functions.Function5
        public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
            return ComposableSingletons$DefaultTextContextMenuDropdownProvider_androidKt.lambda_636288403$lambda$0((TextContextMenuSession) obj, (TextContextMenuDataProvider) obj2, (Function0) obj3, (Composer) obj4, ((Integer) obj5).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-1357803046, reason: not valid java name */
    private static Function5<TextContextMenuSession, TextContextMenuDataProvider, Function0<? extends LayoutCoordinates>, Composer, Integer, Unit> f3lambda$1357803046 = ComposableLambdaKt.composableLambdaInstance(-1357803046, false, new Function5() { // from class: androidx.compose.foundation.text.contextmenu.internal.ComposableSingletons$DefaultTextContextMenuDropdownProvider_androidKt$$ExternalSyntheticLambda2
        @Override // kotlin.jvm.functions.Function5
        public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
            return ComposableSingletons$DefaultTextContextMenuDropdownProvider_androidKt.lambda__1357803046$lambda$0((TextContextMenuSession) obj, (TextContextMenuDataProvider) obj2, (Function0) obj3, (Composer) obj4, ((Integer) obj5).intValue());
        }
    });

    /* JADX INFO: renamed from: getLambda$-1357803046$foundation, reason: not valid java name */
    public final Function5<TextContextMenuSession, TextContextMenuDataProvider, Function0<? extends LayoutCoordinates>, Composer, Integer, Unit> m1687getLambda$1357803046$foundation() {
        return f3lambda$1357803046;
    }

    public final Function5<TextContextMenuSession, TextContextMenuDataProvider, Function0<? extends LayoutCoordinates>, Composer, Integer, Unit> getLambda$129995601$foundation() {
        return lambda$129995601;
    }

    public final Function5<TextContextMenuSession, TextContextMenuDataProvider, Function0<? extends LayoutCoordinates>, Composer, Integer, Unit> getLambda$636288403$foundation() {
        return lambda$636288403;
    }

    static final Unit lambda_129995601$lambda$0(TextContextMenuSession session, TextContextMenuDataProvider dataProvider, Function0 anchorLayoutCoordinates, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "CN(session,dataProvider,anchorLayoutCoordinates)75@3942L63:DefaultTextContextMenuDropdownProvider.android.kt#18dpbw");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= ($changed & 8) == 0 ? $composer.changed(session) : $composer.changedInstance(session) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= ($changed & 64) == 0 ? $composer.changed(dataProvider) : $composer.changedInstance(dataProvider) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer.changedInstance(anchorLayoutCoordinates) ? 256 : 128;
        }
        if ($composer.shouldExecute(($dirty & 1171) != 1170, $dirty & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(129995601, $dirty, -1, "androidx.compose.foundation.text.contextmenu.internal.ComposableSingletons$DefaultTextContextMenuDropdownProvider_androidKt.lambda$129995601.<anonymous> (DefaultTextContextMenuDropdownProvider.android.kt:75)");
            }
            DefaultTextContextMenuDropdownProvider_androidKt.OpenContextMenu(session, dataProvider, anchorLayoutCoordinates, $composer, ($dirty & 14) | ($dirty & 112) | ($dirty & 896));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_636288403$lambda$0(TextContextMenuSession session, TextContextMenuDataProvider dataProvider, Function0 anchorLayoutCoordinates, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "CN(session,dataProvider,anchorLayoutCoordinates)90@4405L63:DefaultTextContextMenuDropdownProvider.android.kt#18dpbw");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= ($changed & 8) == 0 ? $composer.changed(session) : $composer.changedInstance(session) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= ($changed & 64) == 0 ? $composer.changed(dataProvider) : $composer.changedInstance(dataProvider) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer.changedInstance(anchorLayoutCoordinates) ? 256 : 128;
        }
        if ($composer.shouldExecute(($dirty & 1171) != 1170, $dirty & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(636288403, $dirty, -1, "androidx.compose.foundation.text.contextmenu.internal.ComposableSingletons$DefaultTextContextMenuDropdownProvider_androidKt.lambda$636288403.<anonymous> (DefaultTextContextMenuDropdownProvider.android.kt:90)");
            }
            DefaultTextContextMenuDropdownProvider_androidKt.OpenContextMenu(session, dataProvider, anchorLayoutCoordinates, $composer, ($dirty & 14) | ($dirty & 112) | ($dirty & 896));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__1357803046$lambda$0(TextContextMenuSession session, TextContextMenuDataProvider dataProvider, Function0 anchorLayoutCoordinates, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "CN(session,dataProvider,anchorLayoutCoordinates)99@4699L63:DefaultTextContextMenuDropdownProvider.android.kt#18dpbw");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= ($changed & 8) == 0 ? $composer.changed(session) : $composer.changedInstance(session) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= ($changed & 64) == 0 ? $composer.changed(dataProvider) : $composer.changedInstance(dataProvider) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer.changedInstance(anchorLayoutCoordinates) ? 256 : 128;
        }
        if ($composer.shouldExecute(($dirty & 1171) != 1170, $dirty & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1357803046, $dirty, -1, "androidx.compose.foundation.text.contextmenu.internal.ComposableSingletons$DefaultTextContextMenuDropdownProvider_androidKt.lambda$-1357803046.<anonymous> (DefaultTextContextMenuDropdownProvider.android.kt:99)");
            }
            DefaultTextContextMenuDropdownProvider_androidKt.OpenContextMenu(session, dataProvider, anchorLayoutCoordinates, $composer, ($dirty & 14) | ($dirty & 112) | ($dirty & 896));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }
}
