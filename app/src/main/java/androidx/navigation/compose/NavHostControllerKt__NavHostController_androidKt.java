package androidx.navigation.compose;

import android.content.Context;
import android.os.Bundle;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.runtime.saveable.SaverKt;
import androidx.compose.runtime.saveable.SaverScope;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.navigation.NavDestination;
import androidx.navigation.NavHostController;
import androidx.navigation.Navigator;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: NavHostController.android.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u0000(\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a1\u0010\u0000\u001a\u00020\u00012\"\u0010\u0002\u001a\u0012\u0012\u000e\b\u0001\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00050\u00040\u0003\"\n\u0012\u0006\b\u0001\u0012\u00020\u00050\u0004H\u0007¢\u0006\u0002\u0010\u0006\u001a\u0015\u0010\u0007\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\tH\u0002¢\u0006\u0002\b\n\u001a\u001f\u0010\u000b\u001a\f\u0012\u0004\u0012\u00020\u0001\u0012\u0002\b\u00030\f2\u0006\u0010\b\u001a\u00020\tH\u0002¢\u0006\u0002\b\r¨\u0006\u000e"}, d2 = {"rememberNavController", "Landroidx/navigation/NavHostController;", "navigators", "", "Landroidx/navigation/Navigator;", "Landroidx/navigation/NavDestination;", "([Landroidx/navigation/Navigator;Landroidx/compose/runtime/Composer;I)Landroidx/navigation/NavHostController;", "createNavController", "context", "Landroid/content/Context;", "createNavController$NavHostControllerKt__NavHostController_androidKt", "NavControllerSaver", "Landroidx/compose/runtime/saveable/Saver;", "NavControllerSaver$NavHostControllerKt__NavHostController_androidKt", "navigation-compose_release"}, k = 5, mv = {2, 0, 0}, xi = 48, xs = "androidx/navigation/compose/NavHostControllerKt")
final /* synthetic */ class NavHostControllerKt__NavHostController_androidKt {
    public static final NavHostController rememberNavController(Navigator<? extends NavDestination>[] navigatorArr, Composer $composer, int $changed) {
        Object value$iv;
        ComposerKt.sourceInformationMarkerStart($composer, -342848815, "C(rememberNavController)34@1229L7,35@1323L52,35@1248L127:NavHostController.android.kt#opm8kd");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-342848815, $changed, -1, "androidx.navigation.compose.rememberNavController (NavHostController.android.kt:33)");
        }
        ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object objConsume = $composer.consume(localContext);
        ComposerKt.sourceInformationMarkerEnd($composer);
        final Context context = (Context) objConsume;
        Object[] objArrCopyOf = Arrays.copyOf(navigatorArr, navigatorArr.length);
        Saver<NavHostController, ?> saverNavControllerSaver$NavHostControllerKt__NavHostController_androidKt = NavControllerSaver$NavHostControllerKt__NavHostController_androidKt(context);
        ComposerKt.sourceInformationMarkerStart($composer, 1234778757, "CC(remember):NavHostController.android.kt#9igjgp");
        boolean invalid$iv = $composer.changedInstance(context);
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            value$iv = new Function0() { // from class: androidx.navigation.compose.NavHostControllerKt__NavHostController_androidKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return NavHostControllerKt__NavHostController_androidKt.createNavController$NavHostControllerKt__NavHostController_androidKt(context);
                }
            };
            $composer.updateRememberedValue(value$iv);
        } else {
            value$iv = it$iv;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        Object objM4703rememberSaveable = RememberSaveableKt.m4703rememberSaveable(objArrCopyOf, saverNavControllerSaver$NavHostControllerKt__NavHostController_androidKt, (String) null, (Function0<? extends Object>) value$iv, $composer, 0, 4);
        NavHostController $this$rememberNavController_u24lambda_u242 = (NavHostController) objM4703rememberSaveable;
        for (Navigator<? extends NavDestination> navigator : navigatorArr) {
            $this$rememberNavController_u24lambda_u242.getNavigatorProvider().addNavigator(navigator);
        }
        NavHostController navHostController = (NavHostController) objM4703rememberSaveable;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return navHostController;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final NavHostController createNavController$NavHostControllerKt__NavHostController_androidKt(Context context) {
        NavHostController $this$createNavController_u24lambda_u243 = new NavHostController(context);
        $this$createNavController_u24lambda_u243.getNavigatorProvider().addNavigator(new ComposeNavGraphNavigator($this$createNavController_u24lambda_u243.getNavigatorProvider()));
        $this$createNavController_u24lambda_u243.getNavigatorProvider().addNavigator(new ComposeNavigator());
        $this$createNavController_u24lambda_u243.getNavigatorProvider().addNavigator(new DialogNavigator());
        return $this$createNavController_u24lambda_u243;
    }

    private static final Saver<NavHostController, ?> NavControllerSaver$NavHostControllerKt__NavHostController_androidKt(final Context context) {
        return SaverKt.Saver(new Function2() { // from class: androidx.navigation.compose.NavHostControllerKt__NavHostController_androidKt$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return NavHostControllerKt__NavHostController_androidKt.NavControllerSaver$lambda$4$NavHostControllerKt__NavHostController_androidKt((SaverScope) obj, (NavHostController) obj2);
            }
        }, new Function1() { // from class: androidx.navigation.compose.NavHostControllerKt__NavHostController_androidKt$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return NavHostControllerKt__NavHostController_androidKt.NavControllerSaver$lambda$6$NavHostControllerKt__NavHostController_androidKt(context, (Bundle) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Bundle NavControllerSaver$lambda$4$NavHostControllerKt__NavHostController_androidKt(SaverScope $this$Saver, NavHostController it) {
        return it.saveState();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final NavHostController NavControllerSaver$lambda$6$NavHostControllerKt__NavHostController_androidKt(Context $context, Bundle it) {
        NavHostController $this$NavControllerSaver_u24lambda_u246_u24lambda_u245 = createNavController$NavHostControllerKt__NavHostController_androidKt($context);
        $this$NavControllerSaver_u24lambda_u246_u24lambda_u245.restoreState(it);
        return $this$NavControllerSaver_u24lambda_u246_u24lambda_u245;
    }
}
