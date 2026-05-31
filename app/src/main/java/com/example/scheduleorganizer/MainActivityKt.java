package com.example.scheduleorganizer;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.compose.animation.AnimatedContentScope;
import androidx.compose.foundation.DarkThemeKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScope;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.shape.RoundedCornerShape;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.foundation.text.KeyboardActions;
import androidx.compose.foundation.text.KeyboardOptions;
import androidx.compose.material3.AndroidAlertDialog_androidKt;
import androidx.compose.material3.AppBarKt;
import androidx.compose.material3.ButtonDefaults;
import androidx.compose.material3.ButtonKt;
import androidx.compose.material3.DrawerState;
import androidx.compose.material3.DrawerValue;
import androidx.compose.material3.FloatingActionButtonKt;
import androidx.compose.material3.IconButtonKt;
import androidx.compose.material3.IconKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.NavigationBarKt;
import androidx.compose.material3.NavigationDrawerKt;
import androidx.compose.material3.ScaffoldKt;
import androidx.compose.material3.SnackbarHostKt;
import androidx.compose.material3.SnackbarHostState;
import androidx.compose.material3.TextFieldColors;
import androidx.compose.material3.TextFieldKt;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.input.VisualTransformation;
import androidx.compose.ui.unit.Dp;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.view.PointerIconCompat;
import androidx.navigation.NavBackStackEntry;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.NavGraph;
import androidx.navigation.NavGraphBuilder;
import androidx.navigation.NavHostController;
import androidx.navigation.NavOptions;
import androidx.navigation.NavOptionsBuilder;
import androidx.navigation.Navigator;
import androidx.navigation.PopUpToBuilder;
import androidx.navigation.compose.NavGraphBuilderKt;
import androidx.navigation.compose.NavHostControllerKt;
import androidx.navigation.compose.NavHostKt;
import androidx.profileinstaller.ProfileVerifier;
import com.example.scheduleorganizer.Screen;
import com.example.scheduleorganizer.data.entity.UserProfile;
import com.example.scheduleorganizer.ui.MainViewModel;
import com.example.scheduleorganizer.ui.component.CommonComponentsKt;
import com.example.scheduleorganizer.ui.screen.ChatScreenKt;
import com.example.scheduleorganizer.ui.screen.HomeScreenKt;
import com.example.scheduleorganizer.ui.screen.JadwalScreenKt;
import com.example.scheduleorganizer.ui.screen.NotesScreenKt;
import com.example.scheduleorganizer.ui.screen.ProfilScreenKt;
import com.example.scheduleorganizer.ui.screen.StatsScreenKt;
import com.example.scheduleorganizer.ui.screen.TourGuideScreenKt;
import com.example.scheduleorganizer.ui.screen.TugasScreenKt;
import com.example.scheduleorganizer.ui.theme.ThemeKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: MainActivity.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000.\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\u001a\u0015\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0007¢\u0006\u0002\u0010\u0004¨\u0006\u0005²\u0006\f\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u008a\u0084\u0002²\u0006\f\u0010\b\u001a\u0004\u0018\u00010\tX\u008a\u0084\u0002²\u0006\n\u0010\n\u001a\u00020\u000bX\u008a\u008e\u0002²\u0006\n\u0010\f\u001a\u00020\rX\u008a\u008e\u0002²\u0006\n\u0010\u000e\u001a\u00020\u000bX\u008a\u008e\u0002²\u0006\n\u0010\u000f\u001a\u00020\u000bX\u008a\u008e\u0002²\u0006\n\u0010\u0010\u001a\u00020\u000bX\u008a\u008e\u0002²\u0006\n\u0010\u0011\u001a\u00020\u000bX\u008a\u008e\u0002²\u0006\n\u0010\u0012\u001a\u00020\u0013X\u008a\u008e\u0002²\u0006\f\u0010\b\u001a\u0004\u0018\u00010\tX\u008a\u0084\u0002²\u0006\f\u0010\b\u001a\u0004\u0018\u00010\tX\u008a\u0084\u0002"}, d2 = {"ScheduleOrganizerApp", "", "viewModel", "Lcom/example/scheduleorganizer/ui/MainViewModel;", "(Lcom/example/scheduleorganizer/ui/MainViewModel;Landroidx/compose/runtime/Composer;I)V", "app", "userProfile", "Lcom/example/scheduleorganizer/data/entity/UserProfile;", "navBackStackEntry", "Landroidx/navigation/NavBackStackEntry;", "showAddDialog", "", "selectedAddDialogType", "Lcom/example/scheduleorganizer/AddDialogType;", "showAddMenuDialog", "showManageCourses", "showTourGuide", "showOnboardingDialog", "nameInput", ""}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class MainActivityKt {

    /* JADX INFO: compiled from: MainActivity.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[AddDialogType.values().length];
            try {
                iArr[AddDialogType.Task.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[AddDialogType.Schedule.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    static final Unit ScheduleOrganizerApp$lambda$28(MainViewModel mainViewModel, int i, Composer composer, int i2) {
        ScheduleOrganizerApp(mainViewModel, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v14 */
    /* JADX WARN: Type inference failed for: r10v5 */
    /* JADX WARN: Type inference failed for: r10v6 */
    /* JADX WARN: Type inference failed for: r10v7 */
    /* JADX WARN: Type inference failed for: r46v0, types: [boolean] */
    public static final void ScheduleOrganizerApp(MainViewModel mainViewModel, Composer composer, final int i) {
        Composer composer2;
        boolean z;
        MutableState mutableState;
        SharedPreferences sharedPreferences;
        MutableState mutableState2;
        Composer composer3;
        Context context;
        int i2;
        char c;
        char c2;
        char c3;
        ?? r10;
        NavDestination destination;
        final MainViewModel viewModel = mainViewModel;
        Intrinsics.checkNotNullParameter(viewModel, "viewModel");
        Composer composerStartRestartGroup = composer.startRestartGroup(-194621629);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(ScheduleOrganizerApp)N(viewModel)162@7312L23,163@7367L7,165@7487L16,166@7547L30,168@7664L34,169@7732L51,170@7813L34,171@7877L34,172@7937L34,173@8004L67,174@8093L31,175@8159L32,176@8214L39,177@8270L24,239@10344L8875,235@10193L9026:MainActivity.kt#342o8p");
        int i3 = i;
        if ((i & 6) == 0) {
            i3 |= (i & 8) == 0 ? composerStartRestartGroup.changed(viewModel) : composerStartRestartGroup.changedInstance(viewModel) ? 4 : 2;
        }
        int i4 = i3;
        if (composerStartRestartGroup.shouldExecute((i4 & 3) != 2, i4 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-194621629, i4, -1, "com.example.scheduleorganizer.ScheduleOrganizerApp (MainActivity.kt:161)");
            }
            final NavHostController navHostControllerRememberNavController = NavHostControllerKt.rememberNavController(new Navigator[0], composerStartRestartGroup, 0);
            ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = composerStartRestartGroup.consume(localContext);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            Context context2 = (Context) objConsume;
            final SharedPreferences sharedPreferences2 = context2.getSharedPreferences("app_setup_prefs", 0);
            SnapshotStateKt.collectAsState(viewModel.getUserProfile(), null, composerStartRestartGroup, 0, 1);
            NavBackStackEntry navBackStackEntryScheduleOrganizerApp$lambda$1 = ScheduleOrganizerApp$lambda$1(NavHostControllerKt.currentBackStackEntryAsState(navHostControllerRememberNavController, composerStartRestartGroup, 0));
            final String route = (navBackStackEntryScheduleOrganizerApp$lambda$1 == null || (destination = navBackStackEntryScheduleOrganizerApp$lambda$1.getDestination()) == null) ? null : destination.getRoute();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1947189317, "CC(remember):MainActivity.kt#9igjgp");
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                z = false;
                MutableState mutableStateMutableStateOf$default = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default);
                objRememberedValue = mutableStateMutableStateOf$default;
            } else {
                z = false;
            }
            final MutableState mutableState3 = (MutableState) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1947191510, "CC(remember):MainActivity.kt#9igjgp");
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                MutableState mutableStateMutableStateOf$default2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(AddDialogType.Schedule, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default2);
                objRememberedValue2 = mutableStateMutableStateOf$default2;
            }
            final MutableState mutableState4 = (MutableState) objRememberedValue2;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1947194085, "CC(remember):MainActivity.kt#9igjgp");
            Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                MutableState mutableStateMutableStateOf$default3 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.valueOf(z), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default3);
                objRememberedValue3 = mutableStateMutableStateOf$default3;
            }
            final MutableState mutableState5 = (MutableState) objRememberedValue3;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1947196133, "CC(remember):MainActivity.kt#9igjgp");
            Object objRememberedValue4 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                MutableState mutableStateMutableStateOf$default4 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.valueOf(z), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default4);
                objRememberedValue4 = mutableStateMutableStateOf$default4;
            }
            final MutableState mutableState6 = (MutableState) objRememberedValue4;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1947198053, "CC(remember):MainActivity.kt#9igjgp");
            Object objRememberedValue5 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                MutableState mutableStateMutableStateOf$default5 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.valueOf(z), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default5);
                objRememberedValue5 = mutableStateMutableStateOf$default5;
            }
            final MutableState mutableState7 = (MutableState) objRememberedValue5;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1947200230, "CC(remember):MainActivity.kt#9igjgp");
            Object objRememberedValue6 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                MutableState mutableStateMutableStateOf$default6 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.valueOf(sharedPreferences2.getBoolean("first_launch", true)), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default6);
                objRememberedValue6 = mutableStateMutableStateOf$default6;
            }
            MutableState mutableState8 = (MutableState) objRememberedValue6;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1947203042, "CC(remember):MainActivity.kt#9igjgp");
            Object objRememberedValue7 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue7 == Composer.INSTANCE.getEmpty()) {
                mutableState = mutableState8;
                MutableState mutableStateMutableStateOf$default7 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default("", null, 2, null);
                composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default7);
                objRememberedValue7 = mutableStateMutableStateOf$default7;
            } else {
                mutableState = mutableState8;
            }
            final MutableState mutableState9 = (MutableState) objRememberedValue7;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1947205155, "CC(remember):MainActivity.kt#9igjgp");
            Object objRememberedValue8 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue8 == Composer.INSTANCE.getEmpty()) {
                SnackbarHostState snackbarHostState = new SnackbarHostState();
                composerStartRestartGroup.updateRememberedValue(snackbarHostState);
                objRememberedValue8 = snackbarHostState;
            }
            final SnackbarHostState snackbarHostState2 = (SnackbarHostState) objRememberedValue8;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            final DrawerState drawerStateRememberDrawerState = NavigationDrawerKt.rememberDrawerState(DrawerValue.Closed, null, composerStartRestartGroup, 6, 2);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 773894976, "CC(rememberCoroutineScope)N(getContext)616@28039L68:Effects.kt#9igjgp");
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 683736516, "CC(remember):Effects.kt#9igjgp");
            Object objRememberedValue9 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue9 == Composer.INSTANCE.getEmpty()) {
                CoroutineScope coroutineScopeCreateCompositionCoroutineScope = EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup);
                composerStartRestartGroup.updateRememberedValue(coroutineScopeCreateCompositionCoroutineScope);
                objRememberedValue9 = coroutineScopeCreateCompositionCoroutineScope;
            }
            final CoroutineScope coroutineScope = (CoroutineScope) objRememberedValue9;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            if (ScheduleOrganizerApp$lambda$18(mutableState)) {
                composerStartRestartGroup.startReplaceGroup(234185354);
                ComposerKt.sourceInformation(composerStartRestartGroup, "184@8542L3,200@9258L579,186@8615L613,183@8498L1349");
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1947217382, "CC(remember):MainActivity.kt#9igjgp");
                Object objRememberedValue10 = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue10 == Composer.INSTANCE.getEmpty()) {
                    Function0 function0 = new Function0() { // from class: com.example.scheduleorganizer.MainActivityKt$$ExternalSyntheticLambda41
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return Unit.INSTANCE;
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(function0);
                    objRememberedValue10 = function0;
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                final MutableState mutableState10 = mutableState;
                mutableState2 = mutableState7;
                context = context2;
                c2 = 1;
                c3 = 6;
                sharedPreferences = sharedPreferences2;
                i2 = 4;
                c = 2;
                AndroidAlertDialog_androidKt.m2150AlertDialogOix01E0((Function0) objRememberedValue10, ComposableLambdaKt.rememberComposableLambda(1764970256, true, new Function2() { // from class: com.example.scheduleorganizer.MainActivityKt$$ExternalSyntheticLambda42
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return MainActivityKt.ScheduleOrganizerApp$lambda$25(viewModel, sharedPreferences2, mutableState9, mutableState10, mutableState7, (Composer) obj, ((Integer) obj2).intValue());
                    }
                }, composerStartRestartGroup, 54), null, null, null, ComposableSingletons$MainActivityKt.INSTANCE.getLambda$895959060$app(), ComposableLambdaKt.rememberComposableLambda(-1468777387, true, new Function2() { // from class: com.example.scheduleorganizer.MainActivityKt$$ExternalSyntheticLambda43
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return MainActivityKt.ScheduleOrganizerApp$lambda$26(mutableState9, (Composer) obj, ((Integer) obj2).intValue());
                    }
                }, composerStartRestartGroup, 54), null, 0L, 0L, 0L, 0L, 0.0f, null, composerStartRestartGroup, 1769526, 0, 16284);
                composer3 = composerStartRestartGroup;
                composer3.endReplaceGroup();
            } else {
                sharedPreferences = sharedPreferences2;
                mutableState2 = mutableState7;
                composer3 = composerStartRestartGroup;
                context = context2;
                i2 = 4;
                c = 2;
                c2 = 1;
                c3 = 6;
                composer3.startReplaceGroup(235497119);
                composer3.endReplaceGroup();
            }
            Screen[] screenArr = new Screen[i2];
            screenArr[0] = Screen.Home.INSTANCE;
            screenArr[c2] = Screen.Jadwal.INSTANCE;
            screenArr[c] = Screen.Tugas.INSTANCE;
            screenArr[3] = Screen.Stats.INSTANCE;
            final List listListOf = CollectionsKt.listOf((Object[]) screenArr);
            Screen[] screenArr2 = new Screen[8];
            screenArr2[0] = Screen.Home.INSTANCE;
            screenArr2[c2] = Screen.Jadwal.INSTANCE;
            screenArr2[c] = Screen.Tugas.INSTANCE;
            screenArr2[3] = Screen.Stats.INSTANCE;
            screenArr2[i2] = Screen.Notes.INSTANCE;
            screenArr2[5] = Screen.Tour.INSTANCE;
            screenArr2[c3] = Screen.Profil.INSTANCE;
            screenArr2[7] = Screen.Chat.INSTANCE;
            final List listListOf2 = CollectionsKt.listOf((Object[]) screenArr2);
            switch (mainViewModel.getThemeMode().getValue().intValue()) {
                case 1:
                    composer3.startReplaceGroup(235917133);
                    composer3.endReplaceGroup();
                    r10 = 0;
                    break;
                case 2:
                    composer3.startReplaceGroup(235935944);
                    composer3.endReplaceGroup();
                    r10 = c2;
                    break;
                default:
                    composer3.startReplaceGroup(1947274136);
                    ComposerKt.sourceInformation(composer3, "238@10315L21");
                    boolean zIsSystemInDarkTheme = DarkThemeKt.isSystemInDarkTheme(composer3, 0);
                    composer3.endReplaceGroup();
                    r10 = zIsSystemInDarkTheme;
                    break;
            }
            Composer composer4 = composer3;
            final MutableState mutableState11 = mutableState2;
            final Context context3 = context;
            final SharedPreferences sharedPreferences3 = sharedPreferences;
            viewModel = mainViewModel;
            composer2 = composer4;
            ThemeKt.ScheduleOrganizerTheme(r10, ComposableLambdaKt.rememberComposableLambda(-691392124, true, new Function2() { // from class: com.example.scheduleorganizer.MainActivityKt$$ExternalSyntheticLambda44
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return MainActivityKt.ScheduleOrganizerApp$lambda$27(viewModel, context3, sharedPreferences3, drawerStateRememberDrawerState, mutableState11, navHostControllerRememberNavController, listListOf2, coroutineScope, listListOf, route, mutableState5, snackbarHostState2, mutableState4, mutableState3, mutableState6, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, composer2, 54), composer2, 48, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer2 = composerStartRestartGroup;
            composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.example.scheduleorganizer.MainActivityKt$$ExternalSyntheticLambda45
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return MainActivityKt.ScheduleOrganizerApp$lambda$28(viewModel, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final UserProfile ScheduleOrganizerApp$lambda$0(State<UserProfile> state) {
        return (UserProfile) state.getValue();
    }

    private static final NavBackStackEntry ScheduleOrganizerApp$lambda$1(State<NavBackStackEntry> state) {
        return (NavBackStackEntry) state.getValue();
    }

    private static final boolean ScheduleOrganizerApp$lambda$3(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void ScheduleOrganizerApp$lambda$4(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final AddDialogType ScheduleOrganizerApp$lambda$6(MutableState<AddDialogType> mutableState) {
        return mutableState.getValue();
    }

    private static final void ScheduleOrganizerApp$lambda$10(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final boolean ScheduleOrganizerApp$lambda$9(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final boolean ScheduleOrganizerApp$lambda$12(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void ScheduleOrganizerApp$lambda$13(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final boolean ScheduleOrganizerApp$lambda$15(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void ScheduleOrganizerApp$lambda$16(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final boolean ScheduleOrganizerApp$lambda$18(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void ScheduleOrganizerApp$lambda$19(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final String ScheduleOrganizerApp$lambda$21(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    static final Unit ScheduleOrganizerApp$lambda$26(final MutableState $nameInput$delegate, Composer $composer, int $changed) {
        Function0<ComposeUiNode> function0;
        Composer composer;
        ComposerKt.sourceInformation($composer, "C187@8633L581:MainActivity.kt#342o8p");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1468777387, $changed, -1, "com.example.scheduleorganizer.ScheduleOrganizerApp.<anonymous> (MainActivity.kt:187)");
            }
            ComposerKt.sourceInformationMarkerStart($composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            Modifier modifier = Modifier.INSTANCE;
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer, ((0 >> 3) & 14) | ((0 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer, modifier);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i = ((((0 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
            if (!($composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer.startReusableNode();
            if ($composer.getInserting()) {
                function0 = constructor;
                $composer.createNode(function0);
            } else {
                function0 = constructor;
                $composer.useNode();
            }
            Composer composerM4433constructorimpl = Updater.m4433constructorimpl($composer);
            Updater.m4441setimpl(composerM4433constructorimpl, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4441setimpl(composerM4433constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4441setimpl(composerM4433constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4439reconcileimpl(composerM4433constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4441setimpl(composerM4433constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i2 = (i >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            int i3 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -1020417982, "C188@8662L95,189@8778L41,192@8934L18,190@8840L356:MainActivity.kt#342o8p");
            TextKt.m3157TextNvy7gAk("Masukkan nama Anda untuk memulai. Nama ini akan digunakan di halaman profil dan sapaan.", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262142);
            SpacerKt.Spacer(SizeKt.m1101height3ABfNKs(Modifier.INSTANCE, Dp.m8150constructorimpl(12)), $composer, 6);
            String strScheduleOrganizerApp$lambda$21 = ScheduleOrganizerApp$lambda$21($nameInput$delegate);
            Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            ComposerKt.sourceInformationMarkerStart($composer, -1972571171, "CC(remember):MainActivity.kt#9igjgp");
            Object objRememberedValue = $composer.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                composer = $composer;
                Object obj = new Function1() { // from class: com.example.scheduleorganizer.MainActivityKt$$ExternalSyntheticLambda31
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return MainActivityKt.ScheduleOrganizerApp$lambda$26$0$0$0($nameInput$delegate, (String) obj2);
                    }
                };
                $composer.updateRememberedValue(obj);
                objRememberedValue = obj;
            } else {
                composer = $composer;
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            Composer composer2 = composer;
            TextFieldKt.TextField(strScheduleOrganizerApp$lambda$21, (Function1<? super String, Unit>) objRememberedValue, modifierFillMaxWidth$default, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$MainActivityKt.INSTANCE.getLambda$1190415271$app(), (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$MainActivityKt.INSTANCE.m8658getLambda$1810133178$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, true, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, composer2, 14156208, 12582912, 0, 8257336);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ScheduleOrganizerApp$lambda$26$0$0$0(MutableState $nameInput$delegate, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        $nameInput$delegate.setValue(it);
        return Unit.INSTANCE;
    }

    static final Unit ScheduleOrganizerApp$lambda$25(final MainViewModel $viewModel, final SharedPreferences $prefs, final MutableState $nameInput$delegate, final MutableState $showOnboardingDialog$delegate, final MutableState $showTourGuide$delegate, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C203@9368L374,201@9276L547:MainActivity.kt#342o8p");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1764970256, $changed, -1, "com.example.scheduleorganizer.ScheduleOrganizerApp.<anonymous> (MainActivity.kt:201)");
            }
            boolean zIsBlank = true ^ StringsKt.isBlank(ScheduleOrganizerApp$lambda$21($nameInput$delegate));
            ComposerKt.sourceInformationMarkerStart($composer, -375662010, "CC(remember):MainActivity.kt#9igjgp");
            boolean zChangedInstance = $composer.changedInstance($viewModel) | $composer.changedInstance($prefs);
            Object objRememberedValue = $composer.rememberedValue();
            if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                Object obj = new Function0() { // from class: com.example.scheduleorganizer.MainActivityKt$$ExternalSyntheticLambda35
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return MainActivityKt.ScheduleOrganizerApp$lambda$25$0$0($viewModel, $prefs, $nameInput$delegate, $showOnboardingDialog$delegate, $showTourGuide$delegate);
                    }
                };
                $composer.updateRememberedValue(obj);
                objRememberedValue = obj;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            ButtonKt.Button((Function0) objRememberedValue, null, zIsBlank, null, null, null, null, null, null, ComposableSingletons$MainActivityKt.INSTANCE.m8655getLambda$163031296$app(), $composer, 805306368, TypedValues.PositionType.TYPE_PERCENT_X);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ScheduleOrganizerApp$lambda$25$0$0(MainViewModel $viewModel, SharedPreferences $prefs, MutableState $nameInput$delegate, MutableState $showOnboardingDialog$delegate, MutableState $showTourGuide$delegate) {
        $viewModel.insertUserProfile(StringsKt.trim((CharSequence) ScheduleOrganizerApp$lambda$21($nameInput$delegate)).toString());
        $prefs.edit().putBoolean("first_launch", false).apply();
        ScheduleOrganizerApp$lambda$19($showOnboardingDialog$delegate, false);
        if (!$prefs.getBoolean("tour_completed", false)) {
            ScheduleOrganizerApp$lambda$16($showTourGuide$delegate, true);
        }
        return Unit.INSTANCE;
    }

    static final Unit ScheduleOrganizerApp$lambda$27(final MainViewModel $viewModel, Context $context, final SharedPreferences $prefs, final DrawerState $drawerState, final MutableState $showTourGuide$delegate, final NavHostController $navController, final List $drawerScreens, final CoroutineScope $scope, final List $items, final String $currentRoute, final MutableState $showAddMenuDialog$delegate, final SnackbarHostState $globalSnackbarHostState, final MutableState $selectedAddDialogType$delegate, final MutableState $showAddDialog$delegate, final MutableState $showManageCourses$delegate, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C241@10448L34,241@10427L55:MainActivity.kt#342o8p");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-691392124, $changed, -1, "com.example.scheduleorganizer.ScheduleOrganizerApp.<anonymous> (MainActivity.kt:241)");
            }
            Unit unit = Unit.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer, 1292103078, "CC(remember):MainActivity.kt#9igjgp");
            boolean zChangedInstance = $composer.changedInstance($viewModel) | $composer.changedInstance($context);
            Object objRememberedValue = $composer.rememberedValue();
            if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                Object obj = (Function2) new MainActivityKt$ScheduleOrganizerApp$4$1$1($viewModel, $context, null);
                $composer.updateRememberedValue(obj);
                objRememberedValue = obj;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            EffectsKt.LaunchedEffect(unit, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) objRememberedValue, $composer, 6);
            if (ScheduleOrganizerApp$lambda$15($showTourGuide$delegate)) {
                $composer.startReplaceGroup(1400562079);
                ComposerKt.sourceInformation($composer, "244@10566L137,248@10732L137,243@10524L359");
                ComposerKt.sourceInformationMarkerStart($composer, 1292106957, "CC(remember):MainActivity.kt#9igjgp");
                boolean zChangedInstance2 = $composer.changedInstance($prefs);
                Object objRememberedValue2 = $composer.rememberedValue();
                if (zChangedInstance2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    Object obj2 = new Function0() { // from class: com.example.scheduleorganizer.MainActivityKt$$ExternalSyntheticLambda22
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return MainActivityKt.ScheduleOrganizerApp$lambda$27$1$0($prefs, $showTourGuide$delegate);
                        }
                    };
                    $composer.updateRememberedValue(obj2);
                    objRememberedValue2 = obj2;
                }
                Function0 function0 = (Function0) objRememberedValue2;
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerStart($composer, 1292112269, "CC(remember):MainActivity.kt#9igjgp");
                boolean zChangedInstance3 = $composer.changedInstance($prefs);
                Object objRememberedValue3 = $composer.rememberedValue();
                if (zChangedInstance3 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                    Object obj3 = new Function0() { // from class: com.example.scheduleorganizer.MainActivityKt$$ExternalSyntheticLambda23
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return MainActivityKt.ScheduleOrganizerApp$lambda$27$2$0($prefs, $showTourGuide$delegate);
                        }
                    };
                    $composer.updateRememberedValue(obj3);
                    objRememberedValue3 = obj3;
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                TourGuideScreenKt.TourGuideScreen(function0, (Function0) objRememberedValue3, $composer, 0);
                $composer.endReplaceGroup();
            } else {
                $composer.startReplaceGroup(1401193952);
                ComposerKt.sourceInformation($composer, "256@11011L1544,286@12566L6649,254@10913L8302");
                NavigationDrawerKt.m2733ModalNavigationDrawerFHprtrg(ComposableLambdaKt.rememberComposableLambda(1047544633, true, new Function2() { // from class: com.example.scheduleorganizer.MainActivityKt$$ExternalSyntheticLambda24
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj4, Object obj5) {
                        return MainActivityKt.ScheduleOrganizerApp$lambda$27$3($navController, $drawerScreens, $scope, $drawerState, (Composer) obj4, ((Integer) obj5).intValue());
                    }
                }, $composer, 54), null, $drawerState, false, 0L, ComposableLambdaKt.rememberComposableLambda(-1268479874, true, new Function2() { // from class: com.example.scheduleorganizer.MainActivityKt$$ExternalSyntheticLambda25
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj4, Object obj5) {
                        return MainActivityKt.ScheduleOrganizerApp$lambda$27$4($scope, $drawerState, $navController, $items, $currentRoute, $showAddMenuDialog$delegate, $viewModel, $globalSnackbarHostState, $selectedAddDialogType$delegate, $showAddDialog$delegate, $showManageCourses$delegate, $prefs, (Composer) obj4, ((Integer) obj5).intValue());
                    }
                }, $composer, 54), $composer, 196614, 26);
                $composer.endReplaceGroup();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ScheduleOrganizerApp$lambda$27$1$0(SharedPreferences $prefs, MutableState $showTourGuide$delegate) {
        $prefs.edit().putBoolean("tour_completed", true).apply();
        ScheduleOrganizerApp$lambda$16($showTourGuide$delegate, false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ScheduleOrganizerApp$lambda$27$2$0(SharedPreferences $prefs, MutableState $showTourGuide$delegate) {
        $prefs.edit().putBoolean("tour_completed", true).apply();
        ScheduleOrganizerApp$lambda$16($showTourGuide$delegate, false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ScheduleOrganizerApp$lambda$27$3(final NavHostController $navController, final List $drawerScreens, final CoroutineScope $scope, final DrawerState $drawerState, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C257@11046L1495,257@11029L1512:MainActivity.kt#342o8p");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1047544633, $changed, -1, "com.example.scheduleorganizer.ScheduleOrganizerApp.<anonymous>.<anonymous> (MainActivity.kt:257)");
            }
            NavigationDrawerKt.m2732ModalDrawerSheetafqeVBk(null, null, 0L, 0L, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(417905373, true, new Function3() { // from class: com.example.scheduleorganizer.MainActivityKt$$ExternalSyntheticLambda30
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return MainActivityKt.ScheduleOrganizerApp$lambda$27$3$0($navController, $drawerScreens, $scope, $drawerState, (ColumnScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, $composer, 54), $composer, 1572864, 63);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:40:0x015a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final kotlin.Unit ScheduleOrganizerApp$lambda$27$3$0(final androidx.navigation.NavHostController r31, java.util.List r32, final kotlinx.coroutines.CoroutineScope r33, final androidx.compose.material3.DrawerState r34, androidx.compose.foundation.layout.ColumnScope r35, androidx.compose.runtime.Composer r36, int r37) {
        /*
            Method dump skipped, instruction units count: 433
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.scheduleorganizer.MainActivityKt.ScheduleOrganizerApp$lambda$27$3$0(androidx.navigation.NavHostController, java.util.List, kotlinx.coroutines.CoroutineScope, androidx.compose.material3.DrawerState, androidx.compose.foundation.layout.ColumnScope, androidx.compose.runtime.Composer, int):kotlin.Unit");
    }

    private static final NavBackStackEntry ScheduleOrganizerApp$lambda$27$3$0$0(State<NavBackStackEntry> state) {
        return (NavBackStackEntry) state.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ScheduleOrganizerApp$lambda$27$3$0$1$1(Screen $screen, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C268@11611L18:MainActivity.kt#342o8p");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-122670205, $changed, -1, "com.example.scheduleorganizer.ScheduleOrganizerApp.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (MainActivity.kt:268)");
            }
            TextKt.m3157TextNvy7gAk($screen.getLabel(), null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 0, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ScheduleOrganizerApp$lambda$27$3$0$1$2$0(CoroutineScope $scope, final NavHostController $navController, Screen $screen, DrawerState $drawerState) {
        BuildersKt__Builders_commonKt.launch$default($scope, null, null, new MainActivityKt$ScheduleOrganizerApp$4$4$1$1$3$1$1($drawerState, null), 3, null);
        $navController.navigate($screen.getRoute(), new Function1() { // from class: com.example.scheduleorganizer.MainActivityKt$$ExternalSyntheticLambda37
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MainActivityKt.ScheduleOrganizerApp$lambda$27$3$0$1$2$0$0($navController, (NavOptionsBuilder) obj);
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ScheduleOrganizerApp$lambda$27$3$0$1$2$0$0(NavHostController $navController, NavOptionsBuilder navigate) {
        Intrinsics.checkNotNullParameter(navigate, "$this$navigate");
        navigate.popUpTo(NavGraph.INSTANCE.findStartDestination($navController.getGraph()).getId(), new Function1() { // from class: com.example.scheduleorganizer.MainActivityKt$$ExternalSyntheticLambda29
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MainActivityKt.ScheduleOrganizerApp$lambda$27$3$0$1$2$0$0$0((PopUpToBuilder) obj);
            }
        });
        navigate.setLaunchSingleTop(true);
        navigate.setRestoreState(true);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ScheduleOrganizerApp$lambda$27$3$0$1$2$0$0$0(PopUpToBuilder popUpTo) {
        Intrinsics.checkNotNullParameter(popUpTo, "$this$popUpTo");
        popUpTo.setSaveState(true);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ScheduleOrganizerApp$lambda$27$3$0$1$3(Screen $screen, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C280@12330L44:MainActivity.kt#342o8p");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1402608641, $changed, -1, "com.example.scheduleorganizer.ScheduleOrganizerApp.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (MainActivity.kt:280)");
            }
            IconKt.m2605Iconww6aTOc($screen.getIcon(), (String) null, (Modifier) null, 0L, $composer, 48, 12);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ScheduleOrganizerApp$lambda$27$4(final CoroutineScope $scope, final DrawerState $drawerState, final NavHostController $navController, final List $items, final String $currentRoute, final MutableState $showAddMenuDialog$delegate, final MainViewModel $viewModel, final SnackbarHostState $globalSnackbarHostState, final MutableState $selectedAddDialogType$delegate, final MutableState $showAddDialog$delegate, final MutableState $showManageCourses$delegate, final SharedPreferences $prefs, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C288@12615L430,298@13071L1299,323@14407L539,335@14957L4252,287@12580L6629:MainActivity.kt#342o8p");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1268479874, $changed, -1, "com.example.scheduleorganizer.ScheduleOrganizerApp.<anonymous>.<anonymous> (MainActivity.kt:287)");
            }
            ScaffoldKt.m2850ScaffoldTvnljyQ(null, ComposableLambdaKt.rememberComposableLambda(-64638654, true, new Function2() { // from class: com.example.scheduleorganizer.MainActivityKt$$ExternalSyntheticLambda15
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return MainActivityKt.ScheduleOrganizerApp$lambda$27$4$0($scope, $drawerState, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer, 54), ComposableLambdaKt.rememberComposableLambda(104612931, true, new Function2() { // from class: com.example.scheduleorganizer.MainActivityKt$$ExternalSyntheticLambda16
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return MainActivityKt.ScheduleOrganizerApp$lambda$27$4$1($navController, $items, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer, 54), null, ComposableLambdaKt.rememberComposableLambda(443116101, true, new Function2() { // from class: com.example.scheduleorganizer.MainActivityKt$$ExternalSyntheticLambda17
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return MainActivityKt.ScheduleOrganizerApp$lambda$27$4$2($currentRoute, $showAddMenuDialog$delegate, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer, 54), 0, 0L, 0L, null, ComposableLambdaKt.rememberComposableLambda(-1196439795, true, new Function3() { // from class: com.example.scheduleorganizer.MainActivityKt$$ExternalSyntheticLambda18
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return MainActivityKt.ScheduleOrganizerApp$lambda$27$4$3($viewModel, $globalSnackbarHostState, $showAddMenuDialog$delegate, $currentRoute, $selectedAddDialogType$delegate, $showAddDialog$delegate, $showManageCourses$delegate, $navController, $prefs, (PaddingValues) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, $composer, 54), $composer, 805331376, 489);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ScheduleOrganizerApp$lambda$27$4$0(final CoroutineScope $scope, final DrawerState $drawerState, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C291@12766L239,289@12637L390:MainActivity.kt#342o8p");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-64638654, $changed, -1, "com.example.scheduleorganizer.ScheduleOrganizerApp.<anonymous>.<anonymous>.<anonymous> (MainActivity.kt:289)");
            }
            AppBarKt.m2167CenterAlignedTopAppBarGHTll3U(ComposableSingletons$MainActivityKt.INSTANCE.m8656getLambda$1647848025$app(), null, ComposableLambdaKt.rememberComposableLambda(-1955394135, true, new Function2() { // from class: com.example.scheduleorganizer.MainActivityKt$$ExternalSyntheticLambda38
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return MainActivityKt.ScheduleOrganizerApp$lambda$27$4$0$0($scope, $drawerState, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer, 54), null, 0.0f, null, null, null, $composer, 390, 250);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ScheduleOrganizerApp$lambda$27$4$0$0(final CoroutineScope $scope, final DrawerState $drawerState, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C292@12817L39,292@12796L183:MainActivity.kt#342o8p");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1955394135, $changed, -1, "com.example.scheduleorganizer.ScheduleOrganizerApp.<anonymous>.<anonymous>.<anonymous>.<anonymous> (MainActivity.kt:292)");
            }
            ComposerKt.sourceInformationMarkerStart($composer, 2061913584, "CC(remember):MainActivity.kt#9igjgp");
            boolean zChangedInstance = $composer.changedInstance($scope) | $composer.changed($drawerState);
            Object objRememberedValue = $composer.rememberedValue();
            if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                Object obj = new Function0() { // from class: com.example.scheduleorganizer.MainActivityKt$$ExternalSyntheticLambda36
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return MainActivityKt.ScheduleOrganizerApp$lambda$27$4$0$0$0$0($scope, $drawerState);
                    }
                };
                $composer.updateRememberedValue(obj);
                objRememberedValue = obj;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            IconButtonKt.IconButton((Function0) objRememberedValue, null, false, null, null, null, ComposableSingletons$MainActivityKt.INSTANCE.m8659getLambda$2077071801$app(), $composer, 1572864, 62);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ScheduleOrganizerApp$lambda$27$4$0$0$0$0(CoroutineScope $scope, DrawerState $drawerState) {
        BuildersKt__Builders_commonKt.launch$default($scope, null, null, new MainActivityKt$ScheduleOrganizerApp$4$5$1$1$1$1$1($drawerState, null), 3, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ScheduleOrganizerApp$lambda$27$4$1(final NavHostController $navController, final List $items, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C299@13103L1253,299@13089L1267:MainActivity.kt#342o8p");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(104612931, $changed, -1, "com.example.scheduleorganizer.ScheduleOrganizerApp.<anonymous>.<anonymous>.<anonymous> (MainActivity.kt:299)");
            }
            NavigationBarKt.m2719NavigationBarHsRjFd4(null, 0L, 0L, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(652676906, true, new Function3() { // from class: com.example.scheduleorganizer.MainActivityKt$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return MainActivityKt.ScheduleOrganizerApp$lambda$27$4$1$0($navController, $items, (RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, $composer, 54), $composer, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 31);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00a3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final kotlin.Unit ScheduleOrganizerApp$lambda$27$4$1$0(final androidx.navigation.NavHostController r25, java.util.List r26, androidx.compose.foundation.layout.RowScope r27, androidx.compose.runtime.Composer r28, int r29) {
        /*
            Method dump skipped, instruction units count: 308
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.scheduleorganizer.MainActivityKt.ScheduleOrganizerApp$lambda$27$4$1$0(androidx.navigation.NavHostController, java.util.List, androidx.compose.foundation.layout.RowScope, androidx.compose.runtime.Composer, int):kotlin.Unit");
    }

    private static final NavBackStackEntry ScheduleOrganizerApp$lambda$27$4$1$0$0(State<NavBackStackEntry> state) {
        return (NavBackStackEntry) state.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ScheduleOrganizerApp$lambda$27$4$1$0$1$2(Screen $screen, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C304@13397L44:MainActivity.kt#342o8p");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(533771408, $changed, -1, "com.example.scheduleorganizer.ScheduleOrganizerApp.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (MainActivity.kt:304)");
            }
            IconKt.m2605Iconww6aTOc($screen.getIcon(), (String) null, (Modifier) null, 0L, $composer, 48, 12);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ScheduleOrganizerApp$lambda$27$4$1$0$1$3(Screen $screen, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C305@13483L18:MainActivity.kt#342o8p");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1990930387, $changed, -1, "com.example.scheduleorganizer.ScheduleOrganizerApp.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (MainActivity.kt:305)");
            }
            TextKt.m3157TextNvy7gAk($screen.getLabel(), null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 0, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ScheduleOrganizerApp$lambda$27$4$1$0$1$1$0(Screen $screen, final NavHostController $navController) {
        if (Intrinsics.areEqual($screen.getRoute(), Screen.Home.INSTANCE.getRoute())) {
            NavController.popBackStack$default((NavController) $navController, Screen.Home.INSTANCE.getRoute(), false, false, 4, (Object) null);
        }
        $navController.navigate($screen.getRoute(), new Function1() { // from class: com.example.scheduleorganizer.MainActivityKt$$ExternalSyntheticLambda28
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MainActivityKt.ScheduleOrganizerApp$lambda$27$4$1$0$1$1$0$0($navController, (NavOptionsBuilder) obj);
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ScheduleOrganizerApp$lambda$27$4$1$0$1$1$0$0(NavHostController $navController, NavOptionsBuilder navigate) {
        Intrinsics.checkNotNullParameter(navigate, "$this$navigate");
        navigate.popUpTo(NavGraph.INSTANCE.findStartDestination($navController.getGraph()).getId(), new Function1() { // from class: com.example.scheduleorganizer.MainActivityKt$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MainActivityKt.ScheduleOrganizerApp$lambda$27$4$1$0$1$1$0$0$0((PopUpToBuilder) obj);
            }
        });
        navigate.setLaunchSingleTop(true);
        navigate.setRestoreState(true);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ScheduleOrganizerApp$lambda$27$4$1$0$1$1$0$0$0(PopUpToBuilder popUpTo) {
        Intrinsics.checkNotNullParameter(popUpTo, "$this$popUpTo");
        popUpTo.setSaveState(true);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ScheduleOrganizerApp$lambda$27$4$2(String $currentRoute, final MutableState $showAddMenuDialog$delegate, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C:MainActivity.kt#342o8p");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(443116101, $changed, -1, "com.example.scheduleorganizer.ScheduleOrganizerApp.<anonymous>.<anonymous>.<anonymous> (MainActivity.kt:324)");
            }
            if (CollectionsKt.contains(CollectionsKt.listOf((Object[]) new String[]{Screen.Home.INSTANCE.getRoute(), Screen.Jadwal.INSTANCE.getRoute(), Screen.Tugas.INSTANCE.getRoute()}), $currentRoute)) {
                $composer.startReplaceGroup(-1857564286);
                ComposerKt.sourceInformation($composer, "327@14676L11,326@14591L28,325@14535L379");
                long jM2322getPrimary0d7_KjU = MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getPrimary();
                long jM5350getWhite0d7_KjU = Color.INSTANCE.m5350getWhite0d7_KjU();
                RoundedCornerShape circleShape = RoundedCornerShapeKt.getCircleShape();
                ComposerKt.sourceInformationMarkerStart($composer, 632816673, "CC(remember):MainActivity.kt#9igjgp");
                Object objRememberedValue = $composer.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    Object obj = new Function0() { // from class: com.example.scheduleorganizer.MainActivityKt$$ExternalSyntheticLambda6
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return MainActivityKt.ScheduleOrganizerApp$lambda$27$4$2$0$0($showAddMenuDialog$delegate);
                        }
                    };
                    $composer.updateRememberedValue(obj);
                    objRememberedValue = obj;
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                FloatingActionButtonKt.m2559FloatingActionButtonXz6DiA((Function0) objRememberedValue, null, circleShape, jM2322getPrimary0d7_KjU, jM5350getWhite0d7_KjU, null, null, ComposableSingletons$MainActivityKt.INSTANCE.m8657getLambda$1761644830$app(), $composer, 12607494, 98);
                $composer.endReplaceGroup();
            } else {
                $composer.startReplaceGroup(-1857182211);
                $composer.endReplaceGroup();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ScheduleOrganizerApp$lambda$27$4$2$0$0(MutableState $showAddMenuDialog$delegate) {
        ScheduleOrganizerApp$lambda$10($showAddMenuDialog$delegate, true);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ScheduleOrganizerApp$lambda$27$4$3(MainViewModel $viewModel, SnackbarHostState $globalSnackbarHostState, final MutableState $showAddMenuDialog$delegate, final String $currentRoute, MutableState $selectedAddDialogType$delegate, final MutableState $showAddDialog$delegate, final MutableState $showManageCourses$delegate, final NavHostController $navController, final SharedPreferences $prefs, PaddingValues innerPadding, Composer $composer, int $changed) {
        final MutableState mutableState;
        String str;
        Function0<ComposeUiNode> function0;
        Composer composer;
        final MainViewModel mainViewModel = $viewModel;
        Intrinsics.checkNotNullParameter(innerPadding, "innerPadding");
        ComposerKt.sourceInformation($composer, "CN(innerPadding)375@17308L1891:MainActivity.kt#342o8p");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer.changed(innerPadding) ? 4 : 2;
        }
        if ($composer.shouldExecute(($dirty & 19) != 18, $dirty & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1196439795, $dirty, -1, "com.example.scheduleorganizer.ScheduleOrganizerApp.<anonymous>.<anonymous>.<anonymous> (MainActivity.kt:336)");
            }
            if (ScheduleOrganizerApp$lambda$9($showAddMenuDialog$delegate)) {
                $composer.startReplaceGroup(795758992);
                ComposerKt.sourceInformation($composer, "338@15080L29,341@15387L491,350@15916L667,340@15190L159,337@15028L1573");
                ComposerKt.sourceInformationMarkerStart($composer, 1272596298, "CC(remember):MainActivity.kt#9igjgp");
                Object objRememberedValue = $composer.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    Object obj = new Function0() { // from class: com.example.scheduleorganizer.MainActivityKt$$ExternalSyntheticLambda7
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return MainActivityKt.ScheduleOrganizerApp$lambda$27$4$3$0$0($showAddMenuDialog$delegate);
                        }
                    };
                    $composer.updateRememberedValue(obj);
                    objRememberedValue = obj;
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                mutableState = $selectedAddDialogType$delegate;
                AndroidAlertDialog_androidKt.m2150AlertDialogOix01E0((Function0) objRememberedValue, ComposableLambdaKt.rememberComposableLambda(-519481318, true, new Function2() { // from class: com.example.scheduleorganizer.MainActivityKt$$ExternalSyntheticLambda8
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj2, Object obj3) {
                        return MainActivityKt.ScheduleOrganizerApp$lambda$27$4$3$1($currentRoute, mutableState, $showAddMenuDialog$delegate, $showAddDialog$delegate, (Composer) obj2, ((Integer) obj3).intValue());
                    }
                }, $composer, 54), null, ComposableLambdaKt.rememberComposableLambda(749116700, true, new Function2() { // from class: com.example.scheduleorganizer.MainActivityKt$$ExternalSyntheticLambda9
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj2, Object obj3) {
                        return MainActivityKt.ScheduleOrganizerApp$lambda$27$4$3$2($currentRoute, $showAddMenuDialog$delegate, $showManageCourses$delegate, (Composer) obj2, ((Integer) obj3).intValue());
                    }
                }, $composer, 54), null, ComposableSingletons$MainActivityKt.INSTANCE.getLambda$2017714718$app(), ComposableLambdaKt.rememberComposableLambda(-1642953569, true, new Function2() { // from class: com.example.scheduleorganizer.MainActivityKt$$ExternalSyntheticLambda10
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj2, Object obj3) {
                        return MainActivityKt.ScheduleOrganizerApp$lambda$27$4$3$3($currentRoute, (Composer) obj2, ((Integer) obj3).intValue());
                    }
                }, $composer, 54), null, 0L, 0L, 0L, 0L, 0.0f, null, $composer, 1772598, 0, 16276);
                $composer.endReplaceGroup();
            } else {
                mutableState = $selectedAddDialogType$delegate;
                $composer.startReplaceGroup(797301397);
                $composer.endReplaceGroup();
            }
            if (ScheduleOrganizerApp$lambda$12($showManageCourses$delegate)) {
                $composer.startReplaceGroup(797341294);
                ComposerKt.sourceInformation($composer, "366@16743L29,366@16712L60");
                ComposerKt.sourceInformationMarkerStart($composer, 1272649514, "CC(remember):MainActivity.kt#9igjgp");
                Object objRememberedValue2 = $composer.rememberedValue();
                if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    Object obj2 = new Function0() { // from class: com.example.scheduleorganizer.MainActivityKt$$ExternalSyntheticLambda11
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return MainActivityKt.ScheduleOrganizerApp$lambda$27$4$3$4$0($showManageCourses$delegate);
                        }
                    };
                    $composer.updateRememberedValue(obj2);
                    objRememberedValue2 = obj2;
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                CommonComponentsKt.ManageCoursesDialog(mainViewModel, (Function0) objRememberedValue2, $composer, MainViewModel.$stable | 48);
                $composer.endReplaceGroup();
            } else {
                $composer.startReplaceGroup(797471029);
                $composer.endReplaceGroup();
            }
            if (ScheduleOrganizerApp$lambda$3($showAddDialog$delegate)) {
                $composer.startReplaceGroup(797534393);
                ComposerKt.sourceInformation($composer, "");
                switch (WhenMappings.$EnumSwitchMapping$0[ScheduleOrganizerApp$lambda$6(mutableState).ordinal()]) {
                    case 1:
                        str = "CC(remember):MainActivity.kt#9igjgp";
                        $composer.startReplaceGroup(1272656281);
                        ComposerKt.sourceInformation($composer, "370@16989L25,370@16952L108");
                        ComposerKt.sourceInformationMarkerStart($composer, 1272657382, str);
                        Object objRememberedValue3 = $composer.rememberedValue();
                        if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                            Object obj3 = new Function0() { // from class: com.example.scheduleorganizer.MainActivityKt$$ExternalSyntheticLambda12
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return MainActivityKt.ScheduleOrganizerApp$lambda$27$4$3$5$0($showAddDialog$delegate);
                                }
                            };
                            $composer.updateRememberedValue(obj3);
                            objRememberedValue3 = obj3;
                        }
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        mainViewModel = $viewModel;
                        CommonComponentsKt.AddTaskDialog(mainViewModel, (Function0) objRememberedValue3, $globalSnackbarHostState, $composer, MainViewModel.$stable | 432, 0);
                        composer = $composer;
                        composer.endReplaceGroup();
                        Unit unit = Unit.INSTANCE;
                        break;
                    case 2:
                        $composer.startReplaceGroup(1272662621);
                        ComposerKt.sourceInformation($composer, "371@17191L25,371@17150L112");
                        ComposerKt.sourceInformationMarkerStart($composer, 1272663846, "CC(remember):MainActivity.kt#9igjgp");
                        Object objRememberedValue4 = $composer.rememberedValue();
                        if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                            Object obj4 = new Function0() { // from class: com.example.scheduleorganizer.MainActivityKt$$ExternalSyntheticLambda13
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return MainActivityKt.ScheduleOrganizerApp$lambda$27$4$3$6$0($showAddDialog$delegate);
                                }
                            };
                            $composer.updateRememberedValue(obj4);
                            objRememberedValue4 = obj4;
                        }
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        str = "CC(remember):MainActivity.kt#9igjgp";
                        CommonComponentsKt.AddScheduleDialog($viewModel, (Function0) objRememberedValue4, $globalSnackbarHostState, $composer, MainViewModel.$stable | 432, 0);
                        $composer.endReplaceGroup();
                        Unit unit2 = Unit.INSTANCE;
                        mainViewModel = $viewModel;
                        composer = $composer;
                        break;
                    default:
                        $composer.startReplaceGroup(1272652905);
                        $composer.endReplaceGroup();
                        throw new NoWhenBranchMatchedException();
                }
                composer.endReplaceGroup();
            } else {
                str = "CC(remember):MainActivity.kt#9igjgp";
                $composer.startReplaceGroup(797974965);
                $composer.endReplaceGroup();
            }
            Modifier modifierPadding = PaddingKt.padding(Modifier.INSTANCE, innerPadding);
            ComposerKt.sourceInformationMarkerStart($composer, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
            ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer, modifierPadding);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i = ((((0 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
            if (!($composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer.startReusableNode();
            if ($composer.getInserting()) {
                $composer.createNode(constructor);
            } else {
                $composer.useNode();
            }
            Composer composerM4433constructorimpl = Updater.m4433constructorimpl($composer);
            Updater.m4441setimpl(composerM4433constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4441setimpl(composerM4433constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4441setimpl(composerM4433constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4439reconcileimpl(composerM4433constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4441setimpl(composerM4433constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i2 = (i >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            int i3 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, 367693277, "C380@17525L1464,376@17362L1627,409@19006L179:MainActivity.kt#342o8p");
            String route = Screen.Home.INSTANCE.getRoute();
            Modifier modifierFillMaxSize$default = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null);
            ComposerKt.sourceInformationMarkerStart($composer, 704602591, str);
            boolean zChangedInstance = $composer.changedInstance(mainViewModel) | $composer.changedInstance($navController) | $composer.changedInstance($prefs);
            Object objRememberedValue5 = $composer.rememberedValue();
            if (zChangedInstance || objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                Object obj5 = new Function1() { // from class: com.example.scheduleorganizer.MainActivityKt$$ExternalSyntheticLambda14
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj6) {
                        return MainActivityKt.ScheduleOrganizerApp$lambda$27$4$3$7$0$0(mainViewModel, $navController, $prefs, (NavGraphBuilder) obj6);
                    }
                };
                $composer.updateRememberedValue(obj5);
                objRememberedValue5 = obj5;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            NavHostKt.NavHost($navController, route, modifierFillMaxSize$default, null, null, null, null, null, null, null, (Function1) objRememberedValue5, $composer, 384, 0, PointerIconCompat.TYPE_TOP_RIGHT_DIAGONAL_DOUBLE_ARROW);
            Modifier modifierFillMaxSize$default2 = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null);
            ComposerKt.sourceInformationMarkerStart($composer, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy2 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
            ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
            CompositionLocalMap currentCompositionLocalMap2 = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier($composer, modifierFillMaxSize$default2);
            Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
            int i4 = ((((6 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
            if (!($composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer.startReusableNode();
            if ($composer.getInserting()) {
                function0 = constructor2;
                $composer.createNode(function0);
            } else {
                function0 = constructor2;
                $composer.useNode();
            }
            Composer composerM4433constructorimpl2 = Updater.m4433constructorimpl($composer);
            Updater.m4441setimpl(composerM4433constructorimpl2, measurePolicyMaybeCachedBoxMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4441setimpl(composerM4433constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4441setimpl(composerM4433constructorimpl2, Integer.valueOf(iHashCode2), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4439reconcileimpl(composerM4433constructorimpl2, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4441setimpl(composerM4433constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
            int i5 = (i4 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
            int i6 = ((6 >> 6) & 112) | 6;
            BoxScope boxScope = BoxScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer, 613858238, "C410@19067L100:MainActivity.kt#342o8p");
            SnackbarHostKt.SnackbarHost($globalSnackbarHostState, boxScope.align(Modifier.INSTANCE, Alignment.INSTANCE.getBottomCenter()), null, $composer, 6, 4);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ScheduleOrganizerApp$lambda$27$4$3$0$0(MutableState $showAddMenuDialog$delegate) {
        ScheduleOrganizerApp$lambda$10($showAddMenuDialog$delegate, false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ScheduleOrganizerApp$lambda$27$4$3$3(String $currentRoute, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C340@15192L155:MainActivity.kt#342o8p");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1642953569, $changed, -1, "com.example.scheduleorganizer.ScheduleOrganizerApp.<anonymous>.<anonymous>.<anonymous>.<anonymous> (MainActivity.kt:340)");
            }
            TextKt.m3157TextNvy7gAk(Intrinsics.areEqual($currentRoute, Screen.Tugas.INSTANCE.getRoute()) ? "Tambahkan tugas baru di halaman Tugas." : "Tambahkan jadwal baru di halaman Jadwal atau kelola kategori.", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 0, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ScheduleOrganizerApp$lambda$27$4$3$1(final String $currentRoute, final MutableState $selectedAddDialogType$delegate, final MutableState $showAddMenuDialog$delegate, final MutableState $showAddDialog$delegate, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C343@15459L285,348@15771L85,342@15413L443:MainActivity.kt#342o8p");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-519481318, $changed, -1, "com.example.scheduleorganizer.ScheduleOrganizerApp.<anonymous>.<anonymous>.<anonymous>.<anonymous> (MainActivity.kt:342)");
            }
            ComposerKt.sourceInformationMarkerStart($composer, -1005337673, "CC(remember):MainActivity.kt#9igjgp");
            boolean zChanged = $composer.changed($currentRoute);
            Object objRememberedValue = $composer.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                Object obj = new Function0() { // from class: com.example.scheduleorganizer.MainActivityKt$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return MainActivityKt.ScheduleOrganizerApp$lambda$27$4$3$1$0$0($currentRoute, $selectedAddDialogType$delegate, $showAddMenuDialog$delegate, $showAddDialog$delegate);
                    }
                };
                $composer.updateRememberedValue(obj);
                objRememberedValue = obj;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            ButtonKt.Button((Function0) objRememberedValue, null, false, null, null, null, null, null, null, ComposableLambdaKt.rememberComposableLambda(549387274, true, new Function3() { // from class: com.example.scheduleorganizer.MainActivityKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj2, Object obj3, Object obj4) {
                    return MainActivityKt.ScheduleOrganizerApp$lambda$27$4$3$1$1($currentRoute, (RowScope) obj2, (Composer) obj3, ((Integer) obj4).intValue());
                }
            }, $composer, 54), $composer, 805306368, TypedValues.PositionType.TYPE_POSITION_TYPE);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ScheduleOrganizerApp$lambda$27$4$3$1$0$0(String $currentRoute, MutableState $selectedAddDialogType$delegate, MutableState $showAddMenuDialog$delegate, MutableState $showAddDialog$delegate) {
        $selectedAddDialogType$delegate.setValue(Intrinsics.areEqual($currentRoute, Screen.Tugas.INSTANCE.getRoute()) ? AddDialogType.Task : AddDialogType.Schedule);
        ScheduleOrganizerApp$lambda$10($showAddMenuDialog$delegate, false);
        ScheduleOrganizerApp$lambda$4($showAddDialog$delegate, true);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ScheduleOrganizerApp$lambda$27$4$3$1$1(String $currentRoute, RowScope Button, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(Button, "$this$Button");
        ComposerKt.sourceInformation($composer, "C348@15773L81:MainActivity.kt#342o8p");
        if ($composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(549387274, $changed, -1, "com.example.scheduleorganizer.ScheduleOrganizerApp.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (MainActivity.kt:348)");
            }
            TextKt.m3157TextNvy7gAk(Intrinsics.areEqual($currentRoute, Screen.Tugas.INSTANCE.getRoute()) ? "Tambah Tugas" : "Tambah Jadwal", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 0, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ScheduleOrganizerApp$lambda$27$4$3$2(String $currentRoute, final MutableState $showAddMenuDialog$delegate, final MutableState $showManageCourses$delegate, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C:MainActivity.kt#342o8p");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(749116700, $changed, -1, "com.example.scheduleorganizer.ScheduleOrganizerApp.<anonymous>.<anonymous>.<anonymous>.<anonymous> (MainActivity.kt:351)");
            }
            if (Intrinsics.areEqual($currentRoute, Screen.Tugas.INSTANCE.getRoute())) {
                $composer.startReplaceGroup(285609095);
                ComposerKt.sourceInformation($composer, "352@16033L29,352@16012L71");
                ComposerKt.sourceInformationMarkerStart($composer, -1653353255, "CC(remember):MainActivity.kt#9igjgp");
                Object objRememberedValue = $composer.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    Object obj = new Function0() { // from class: com.example.scheduleorganizer.MainActivityKt$$ExternalSyntheticLambda26
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return MainActivityKt.ScheduleOrganizerApp$lambda$27$4$3$2$0$0($showAddMenuDialog$delegate);
                        }
                    };
                    $composer.updateRememberedValue(obj);
                    objRememberedValue = obj;
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                ButtonKt.TextButton((Function0) objRememberedValue, null, false, null, null, null, null, null, null, ComposableSingletons$MainActivityKt.INSTANCE.getLambda$1598376052$app(), $composer, 805306374, TypedValues.PositionType.TYPE_POSITION_TYPE);
                $composer.endReplaceGroup();
            } else {
                $composer.startReplaceGroup(285750920);
                ComposerKt.sourceInformation($composer, "355@16195L158,359@16455L11,359@16411L66,354@16145L390");
                ComposerKt.sourceInformationMarkerStart($composer, -1653347942, "CC(remember):MainActivity.kt#9igjgp");
                Object objRememberedValue2 = $composer.rememberedValue();
                if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    Object obj2 = new Function0() { // from class: com.example.scheduleorganizer.MainActivityKt$$ExternalSyntheticLambda27
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return MainActivityKt.ScheduleOrganizerApp$lambda$27$4$3$2$1$0($showAddMenuDialog$delegate, $showManageCourses$delegate);
                        }
                    };
                    $composer.updateRememberedValue(obj2);
                    objRememberedValue2 = obj2;
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                ButtonKt.Button((Function0) objRememberedValue2, null, false, null, ButtonDefaults.INSTANCE.m2208buttonColorsro_MJ88(MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getSecondary(), 0L, 0L, 0L, $composer, ButtonDefaults.$stable << 12, 14), null, null, null, null, ComposableSingletons$MainActivityKt.INSTANCE.getLambda$1305576624$app(), $composer, 805306374, 494);
                $composer.endReplaceGroup();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ScheduleOrganizerApp$lambda$27$4$3$2$0$0(MutableState $showAddMenuDialog$delegate) {
        ScheduleOrganizerApp$lambda$10($showAddMenuDialog$delegate, false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ScheduleOrganizerApp$lambda$27$4$3$2$1$0(MutableState $showAddMenuDialog$delegate, MutableState $showManageCourses$delegate) {
        ScheduleOrganizerApp$lambda$10($showAddMenuDialog$delegate, false);
        ScheduleOrganizerApp$lambda$13($showManageCourses$delegate, true);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ScheduleOrganizerApp$lambda$27$4$3$4$0(MutableState $showManageCourses$delegate) {
        ScheduleOrganizerApp$lambda$13($showManageCourses$delegate, false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ScheduleOrganizerApp$lambda$27$4$3$5$0(MutableState $showAddDialog$delegate) {
        ScheduleOrganizerApp$lambda$4($showAddDialog$delegate, false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ScheduleOrganizerApp$lambda$27$4$3$6$0(MutableState $showAddDialog$delegate) {
        ScheduleOrganizerApp$lambda$4($showAddDialog$delegate, false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ScheduleOrganizerApp$lambda$27$4$3$7$0$0(final MainViewModel $viewModel, final NavHostController $navController, final SharedPreferences $prefs, NavGraphBuilder NavHost) {
        Intrinsics.checkNotNullParameter(NavHost, "$this$NavHost");
        NavGraphBuilderKt.composable$default(NavHost, Screen.Home.INSTANCE.getRoute(), (List) null, (List) null, (Function1) null, (Function1) null, (Function1) null, (Function1) null, (Function1) null, ComposableLambdaKt.composableLambdaInstance(223480196, true, new Function4() { // from class: com.example.scheduleorganizer.MainActivityKt$$ExternalSyntheticLambda47
            @Override // kotlin.jvm.functions.Function4
            public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
                return MainActivityKt.ScheduleOrganizerApp$lambda$27$4$3$7$0$0$0($viewModel, $navController, (AnimatedContentScope) obj, (NavBackStackEntry) obj2, (Composer) obj3, ((Integer) obj4).intValue());
            }
        }), 254, (Object) null);
        NavGraphBuilderKt.composable$default(NavHost, Screen.Jadwal.INSTANCE.getRoute(), (List) null, (List) null, (Function1) null, (Function1) null, (Function1) null, (Function1) null, (Function1) null, ComposableLambdaKt.composableLambdaInstance(838434797, true, new Function4() { // from class: com.example.scheduleorganizer.MainActivityKt$$ExternalSyntheticLambda48
            @Override // kotlin.jvm.functions.Function4
            public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
                return MainActivityKt.ScheduleOrganizerApp$lambda$27$4$3$7$0$0$1($viewModel, (AnimatedContentScope) obj, (NavBackStackEntry) obj2, (Composer) obj3, ((Integer) obj4).intValue());
            }
        }), 254, (Object) null);
        NavGraphBuilderKt.composable$default(NavHost, Screen.Tugas.INSTANCE.getRoute(), (List) null, (List) null, (Function1) null, (Function1) null, (Function1) null, (Function1) null, (Function1) null, ComposableLambdaKt.composableLambdaInstance(1245462284, true, new Function4() { // from class: com.example.scheduleorganizer.MainActivityKt$$ExternalSyntheticLambda49
            @Override // kotlin.jvm.functions.Function4
            public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
                return MainActivityKt.ScheduleOrganizerApp$lambda$27$4$3$7$0$0$2($viewModel, (AnimatedContentScope) obj, (NavBackStackEntry) obj2, (Composer) obj3, ((Integer) obj4).intValue());
            }
        }), 254, (Object) null);
        NavGraphBuilderKt.composable$default(NavHost, Screen.Stats.INSTANCE.getRoute(), (List) null, (List) null, (Function1) null, (Function1) null, (Function1) null, (Function1) null, (Function1) null, ComposableLambdaKt.composableLambdaInstance(1652489771, true, new Function4() { // from class: com.example.scheduleorganizer.MainActivityKt$$ExternalSyntheticLambda50
            @Override // kotlin.jvm.functions.Function4
            public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
                return MainActivityKt.ScheduleOrganizerApp$lambda$27$4$3$7$0$0$3($viewModel, (AnimatedContentScope) obj, (NavBackStackEntry) obj2, (Composer) obj3, ((Integer) obj4).intValue());
            }
        }), 254, (Object) null);
        NavGraphBuilderKt.composable$default(NavHost, Screen.Notes.INSTANCE.getRoute(), (List) null, (List) null, (Function1) null, (Function1) null, (Function1) null, (Function1) null, (Function1) null, ComposableLambdaKt.composableLambdaInstance(2059517258, true, new Function4() { // from class: com.example.scheduleorganizer.MainActivityKt$$ExternalSyntheticLambda51
            @Override // kotlin.jvm.functions.Function4
            public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
                return MainActivityKt.ScheduleOrganizerApp$lambda$27$4$3$7$0$0$4($viewModel, $navController, (AnimatedContentScope) obj, (NavBackStackEntry) obj2, (Composer) obj3, ((Integer) obj4).intValue());
            }
        }), 254, (Object) null);
        NavGraphBuilderKt.composable$default(NavHost, Screen.Tour.INSTANCE.getRoute(), (List) null, (List) null, (Function1) null, (Function1) null, (Function1) null, (Function1) null, (Function1) null, ComposableLambdaKt.composableLambdaInstance(-1828422551, true, new Function4() { // from class: com.example.scheduleorganizer.MainActivityKt$$ExternalSyntheticLambda52
            @Override // kotlin.jvm.functions.Function4
            public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
                return MainActivityKt.ScheduleOrganizerApp$lambda$27$4$3$7$0$0$5($navController, $prefs, (AnimatedContentScope) obj, (NavBackStackEntry) obj2, (Composer) obj3, ((Integer) obj4).intValue());
            }
        }), 254, (Object) null);
        NavGraphBuilderKt.composable$default(NavHost, Screen.Profil.INSTANCE.getRoute(), (List) null, (List) null, (Function1) null, (Function1) null, (Function1) null, (Function1) null, (Function1) null, ComposableLambdaKt.composableLambdaInstance(-1421395064, true, new Function4() { // from class: com.example.scheduleorganizer.MainActivityKt$$ExternalSyntheticLambda53
            @Override // kotlin.jvm.functions.Function4
            public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
                return MainActivityKt.ScheduleOrganizerApp$lambda$27$4$3$7$0$0$6($viewModel, (AnimatedContentScope) obj, (NavBackStackEntry) obj2, (Composer) obj3, ((Integer) obj4).intValue());
            }
        }), 254, (Object) null);
        NavGraphBuilderKt.composable$default(NavHost, Screen.Chat.INSTANCE.getRoute(), (List) null, (List) null, (Function1) null, (Function1) null, (Function1) null, (Function1) null, (Function1) null, ComposableLambdaKt.composableLambdaInstance(-1014367577, true, new Function4() { // from class: com.example.scheduleorganizer.MainActivityKt$$ExternalSyntheticLambda54
            @Override // kotlin.jvm.functions.Function4
            public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
                return MainActivityKt.ScheduleOrganizerApp$lambda$27$4$3$7$0$0$7($viewModel, (AnimatedContentScope) obj, (NavBackStackEntry) obj2, (Composer) obj3, ((Integer) obj4).intValue());
            }
        }), 254, (Object) null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ScheduleOrganizerApp$lambda$27$4$3$7$0$0$0(MainViewModel $viewModel, final NavHostController $navController, AnimatedContentScope composable, NavBackStackEntry it, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(composable, "$this$composable");
        Intrinsics.checkNotNullParameter(it, "it");
        ComposerKt.sourceInformation($composer, "CN(it)384@17696L46,382@17603L165:MainActivity.kt#342o8p");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(223480196, $changed, -1, "com.example.scheduleorganizer.ScheduleOrganizerApp.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (MainActivity.kt:382)");
        }
        ComposerKt.sourceInformationMarkerStart($composer, 16107410, "CC(remember):MainActivity.kt#9igjgp");
        boolean zChangedInstance = $composer.changedInstance($navController);
        Object objRememberedValue = $composer.rememberedValue();
        if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            Object obj = new Function0() { // from class: com.example.scheduleorganizer.MainActivityKt$$ExternalSyntheticLambda46
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return MainActivityKt.ScheduleOrganizerApp$lambda$27$4$3$7$0$0$0$0$0($navController);
                }
            };
            $composer.updateRememberedValue(obj);
            objRememberedValue = obj;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        HomeScreenKt.HomeScreen($viewModel, (Function0) objRememberedValue, $composer, MainViewModel.$stable);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ScheduleOrganizerApp$lambda$27$4$3$7$0$0$0$0$0(NavHostController $navController) {
        NavController.navigate$default((NavController) $navController, Screen.Notes.INSTANCE.getRoute(), (NavOptions) null, (Navigator.Extras) null, 6, (Object) null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ScheduleOrganizerApp$lambda$27$4$3$7$0$0$1(MainViewModel $viewModel, AnimatedContentScope composable, NavBackStackEntry it, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(composable, "$this$composable");
        Intrinsics.checkNotNullParameter(it, "it");
        ComposerKt.sourceInformation($composer, "CN(it)387@17845L23:MainActivity.kt#342o8p");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(838434797, $changed, -1, "com.example.scheduleorganizer.ScheduleOrganizerApp.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (MainActivity.kt:387)");
        }
        JadwalScreenKt.JadwalScreen($viewModel, $composer, MainViewModel.$stable);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ScheduleOrganizerApp$lambda$27$4$3$7$0$0$2(MainViewModel $viewModel, AnimatedContentScope composable, NavBackStackEntry it, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(composable, "$this$composable");
        Intrinsics.checkNotNullParameter(it, "it");
        ComposerKt.sourceInformation($composer, "CN(it)388@17924L22:MainActivity.kt#342o8p");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1245462284, $changed, -1, "com.example.scheduleorganizer.ScheduleOrganizerApp.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (MainActivity.kt:388)");
        }
        TugasScreenKt.TugasScreen($viewModel, $composer, MainViewModel.$stable);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ScheduleOrganizerApp$lambda$27$4$3$7$0$0$3(MainViewModel $viewModel, AnimatedContentScope composable, NavBackStackEntry it, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(composable, "$this$composable");
        Intrinsics.checkNotNullParameter(it, "it");
        ComposerKt.sourceInformation($composer, "CN(it)389@18002L22:MainActivity.kt#342o8p");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1652489771, $changed, -1, "com.example.scheduleorganizer.ScheduleOrganizerApp.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (MainActivity.kt:389)");
        }
        StatsScreenKt.StatsScreen($viewModel, $composer, MainViewModel.$stable);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ScheduleOrganizerApp$lambda$27$4$3$7$0$0$4(MainViewModel $viewModel, final NavHostController $navController, AnimatedContentScope composable, NavBackStackEntry it, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(composable, "$this$composable");
        Intrinsics.checkNotNullParameter(it, "it");
        ComposerKt.sourceInformation($composer, "CN(it)393@18200L47,394@18290L45,391@18104L257:MainActivity.kt#342o8p");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(2059517258, $changed, -1, "com.example.scheduleorganizer.ScheduleOrganizerApp.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (MainActivity.kt:391)");
        }
        ComposerKt.sourceInformationMarkerStart($composer, -783834567, "CC(remember):MainActivity.kt#9igjgp");
        boolean zChangedInstance = $composer.changedInstance($navController);
        Object objRememberedValue = $composer.rememberedValue();
        if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            Object obj = new Function0() { // from class: com.example.scheduleorganizer.MainActivityKt$$ExternalSyntheticLambda39
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return MainActivityKt.ScheduleOrganizerApp$lambda$27$4$3$7$0$0$4$0$0($navController);
                }
            };
            $composer.updateRememberedValue(obj);
            objRememberedValue = obj;
        }
        Function0 function0 = (Function0) objRememberedValue;
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerStart($composer, -783831689, "CC(remember):MainActivity.kt#9igjgp");
        boolean zChangedInstance2 = $composer.changedInstance($navController);
        Object objRememberedValue2 = $composer.rememberedValue();
        if (zChangedInstance2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
            Object obj2 = new Function0() { // from class: com.example.scheduleorganizer.MainActivityKt$$ExternalSyntheticLambda40
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return MainActivityKt.ScheduleOrganizerApp$lambda$27$4$3$7$0$0$4$1$0($navController);
                }
            };
            $composer.updateRememberedValue(obj2);
            objRememberedValue2 = obj2;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        NotesScreenKt.NotesScreen($viewModel, function0, (Function0) objRememberedValue2, $composer, MainViewModel.$stable);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ScheduleOrganizerApp$lambda$27$4$3$7$0$0$4$0$0(NavHostController $navController) {
        NavController.navigate$default((NavController) $navController, Screen.Profil.INSTANCE.getRoute(), (NavOptions) null, (Navigator.Extras) null, 6, (Object) null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ScheduleOrganizerApp$lambda$27$4$3$7$0$0$4$1$0(NavHostController $navController) {
        NavController.navigate$default((NavController) $navController, Screen.Chat.INSTANCE.getRoute(), (NavOptions) null, (Navigator.Extras) null, 6, (Object) null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ScheduleOrganizerApp$lambda$27$4$3$7$0$0$5(final NavHostController $navController, final SharedPreferences $prefs, AnimatedContentScope composable, NavBackStackEntry it, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(composable, "$this$composable");
        Intrinsics.checkNotNullParameter(it, "it");
        ComposerKt.sourceInformation($composer, "CN(it)399@18514L32,400@18587L180,398@18460L333:MainActivity.kt#342o8p");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1828422551, $changed, -1, "com.example.scheduleorganizer.ScheduleOrganizerApp.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (MainActivity.kt:398)");
        }
        ComposerKt.sourceInformationMarkerStart($composer, -472444855, "CC(remember):MainActivity.kt#9igjgp");
        boolean zChangedInstance = $composer.changedInstance($navController);
        Object objRememberedValue = $composer.rememberedValue();
        if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            Object obj = new Function0() { // from class: com.example.scheduleorganizer.MainActivityKt$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return MainActivityKt.ScheduleOrganizerApp$lambda$27$4$3$7$0$0$5$0$0($navController);
                }
            };
            $composer.updateRememberedValue(obj);
            objRememberedValue = obj;
        }
        Function0 function0 = (Function0) objRememberedValue;
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerStart($composer, -472442371, "CC(remember):MainActivity.kt#9igjgp");
        boolean zChangedInstance2 = $composer.changedInstance($prefs) | $composer.changedInstance($navController);
        Object objRememberedValue2 = $composer.rememberedValue();
        if (zChangedInstance2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
            Object obj2 = new Function0() { // from class: com.example.scheduleorganizer.MainActivityKt$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return MainActivityKt.ScheduleOrganizerApp$lambda$27$4$3$7$0$0$5$1$0($prefs, $navController);
                }
            };
            $composer.updateRememberedValue(obj2);
            objRememberedValue2 = obj2;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        TourGuideScreenKt.TourGuideScreen(function0, (Function0) objRememberedValue2, $composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ScheduleOrganizerApp$lambda$27$4$3$7$0$0$5$0$0(NavHostController $navController) {
        $navController.popBackStack();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ScheduleOrganizerApp$lambda$27$4$3$7$0$0$5$1$0(SharedPreferences $prefs, NavHostController $navController) {
        $prefs.edit().putBoolean("tour_completed", true).apply();
        $navController.popBackStack();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ScheduleOrganizerApp$lambda$27$4$3$7$0$0$6(MainViewModel $viewModel, AnimatedContentScope composable, NavBackStackEntry it, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(composable, "$this$composable");
        Intrinsics.checkNotNullParameter(it, "it");
        ComposerKt.sourceInformation($composer, "CN(it)406@18870L23:MainActivity.kt#342o8p");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1421395064, $changed, -1, "com.example.scheduleorganizer.ScheduleOrganizerApp.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (MainActivity.kt:406)");
        }
        ProfilScreenKt.ProfilScreen($viewModel, $composer, MainViewModel.$stable);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ScheduleOrganizerApp$lambda$27$4$3$7$0$0$7(MainViewModel $viewModel, AnimatedContentScope composable, NavBackStackEntry it, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(composable, "$this$composable");
        Intrinsics.checkNotNullParameter(it, "it");
        ComposerKt.sourceInformation($composer, "CN(it)407@18948L21:MainActivity.kt#342o8p");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1014367577, $changed, -1, "com.example.scheduleorganizer.ScheduleOrganizerApp.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (MainActivity.kt:407)");
        }
        ChatScreenKt.ChatScreen($viewModel, $composer, MainViewModel.$stable);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }
}
