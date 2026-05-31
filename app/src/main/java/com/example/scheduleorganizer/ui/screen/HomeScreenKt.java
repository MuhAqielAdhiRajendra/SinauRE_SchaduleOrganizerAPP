package com.example.scheduleorganizer.ui.screen;

import android.content.Context;
import androidx.compose.animation.AnimatedVisibilityKt;
import androidx.compose.animation.AnimatedVisibilityScope;
import androidx.compose.animation.EnterExitTransitionKt;
import androidx.compose.animation.EnterTransition;
import androidx.compose.animation.ExitTransition;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.ClickableKt;
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
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.lazy.LazyDslKt;
import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.foundation.lazy.LazyListScope;
import androidx.compose.foundation.shape.RoundedCornerShape;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.foundation.text.KeyboardActions;
import androidx.compose.foundation.text.KeyboardOptions;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.PauseKt;
import androidx.compose.material.icons.filled.PlayArrowKt;
import androidx.compose.material3.AndroidAlertDialog_androidKt;
import androidx.compose.material3.ButtonKt;
import androidx.compose.material3.CardColors;
import androidx.compose.material3.CardDefaults;
import androidx.compose.material3.CardKt;
import androidx.compose.material3.ChipKt;
import androidx.compose.material3.IconButtonKt;
import androidx.compose.material3.IconKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.SnackbarHostKt;
import androidx.compose.material3.SnackbarHostState;
import androidx.compose.material3.SwitchKt;
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
import androidx.compose.runtime.snapshots.SnapshotStateMap;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.input.KeyboardType;
import androidx.compose.ui.text.input.PlatformImeOptions;
import androidx.compose.ui.text.input.VisualTransformation;
import androidx.compose.ui.text.intl.LocaleList;
import androidx.compose.ui.text.style.TextOverflow;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.profileinstaller.ProfileVerifier;
import com.example.scheduleorganizer.data.entity.Course;
import com.example.scheduleorganizer.data.entity.Schedule;
import com.example.scheduleorganizer.data.entity.UserProfile;
import com.example.scheduleorganizer.ui.MainViewModel;
import com.example.scheduleorganizer.ui.component.CommonComponentsKt;
import com.example.scheduleorganizer.util.ConsistencyManager;
import com.example.scheduleorganizer.util.DailyNotesManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: HomeScreen.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000V\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000f\u001a#\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005H\u0007¢\u0006\u0002\u0010\u0006\u001a\u0017\u0010\u0007\u001a\u00020\u00012\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0007¢\u0006\u0002\u0010\n\u001a#\u0010\u000b\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\r2\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005H\u0007¢\u0006\u0002\u0010\u000e\u001a\u0015\u0010\u000f\u001a\u00020\u00012\u0006\u0010\u0010\u001a\u00020\u0011H\u0007¢\u0006\u0002\u0010\u0012\u001aO\u0010\u0013\u001a\u00020\u00012\u0006\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\u00172\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005H\u0007¢\u0006\u0002\u0010\u001b\u001a;\u0010\u001c\u001a\u00020\u00012\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001e2\b\u0010 \u001a\u0004\u0018\u00010!2\u0014\u0010\"\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010!\u0012\u0004\u0012\u00020\u00010#H\u0007¢\u0006\u0002\u0010$\u001aS\u0010%\u001a\u00020\u00012\u0006\u0010&\u001a\u00020'2\f\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\f\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\u0012\u0010*\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00010#2\f\u0010+\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005H\u0007¢\u0006\u0002\u0010,\u001a\u0010\u0010-\u001a\u00020\r2\u0006\u0010.\u001a\u00020\rH\u0002¨\u0006/²\u0006\f\u00100\u001a\u0004\u0018\u00010\tX\u008a\u0084\u0002²\u0006\u0010\u00101\u001a\b\u0012\u0004\u0012\u00020'0\u001eX\u008a\u0084\u0002²\u0006\u0010\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001eX\u008a\u0084\u0002²\u0006\f\u0010 \u001a\u0004\u0018\u00010!X\u008a\u0084\u0002²\u0006\f\u00102\u001a\u0004\u0018\u00010'X\u008a\u008e\u0002²\u0006\f\u00103\u001a\u0004\u0018\u00010'X\u008a\u008e\u0002²\u0006\n\u00104\u001a\u00020\u0011X\u008a\u008e\u0002²\u0006\n\u00105\u001a\u00020\u0017X\u008a\u008e\u0002²\u0006\n\u00106\u001a\u00020\rX\u008a\u008e\u0002"}, d2 = {"HomeScreen", "", "viewModel", "Lcom/example/scheduleorganizer/ui/MainViewModel;", "onOpenNotes", "Lkotlin/Function0;", "(Lcom/example/scheduleorganizer/ui/MainViewModel;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "HeaderSection", "profile", "Lcom/example/scheduleorganizer/data/entity/UserProfile;", "(Lcom/example/scheduleorganizer/data/entity/UserProfile;Landroidx/compose/runtime/Composer;I)V", "DailyNotesPreview", "note", "", "(Ljava/lang/String;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "ConsistencySection", "count", "", "(ILandroidx/compose/runtime/Composer;I)V", "FocusSessionSection", "time", "durationMinutes", "isRunning", "", "onToggle", "onReset", "onAdjustDuration", "(IIZLkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "MataKuliahSection", "courses", "", "Lcom/example/scheduleorganizer/data/entity/Course;", "selectedCourseId", "", "onCourseSelected", "Lkotlin/Function1;", "(Ljava/util/List;Ljava/lang/Long;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;I)V", "ScheduleItem", "schedule", "Lcom/example/scheduleorganizer/data/entity/Schedule;", "onDelete", "onEdit", "onToggleActive", "onAdhere", "(Lcom/example/scheduleorganizer/data/entity/Schedule;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "formatScheduleDays", "days", "app", "userProfile", "schedules", "editingSchedule", "confirmDeleteSchedule", "consistency", "showFocusDurationDialog", "focusDurationInput"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class HomeScreenKt {
    static final Unit ConsistencySection$lambda$1(int i, int i2, Composer composer, int i3) {
        ConsistencySection(i, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1));
        return Unit.INSTANCE;
    }

    static final Unit DailyNotesPreview$lambda$2(String str, Function0 function0, int i, Composer composer, int i2) {
        DailyNotesPreview(str, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit FocusSessionSection$lambda$1(int i, int i2, boolean z, Function0 function0, Function0 function02, Function0 function03, int i3, Composer composer, int i4) {
        FocusSessionSection(i, i2, z, function0, function02, function03, composer, RecomposeScopeImplKt.updateChangedFlags(i3 | 1));
        return Unit.INSTANCE;
    }

    static final Unit HeaderSection$lambda$1(UserProfile userProfile, int i, Composer composer, int i2) {
        HeaderSection(userProfile, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit HomeScreen$lambda$31(MainViewModel mainViewModel, Function0 function0, int i, Composer composer, int i2) {
        HomeScreen(mainViewModel, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit MataKuliahSection$lambda$1(List list, Long l, Function1 function1, int i, Composer composer, int i2) {
        MataKuliahSection(list, l, function1, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit ScheduleItem$lambda$1(Schedule schedule, Function0 function0, Function0 function02, Function1 function1, Function0 function03, int i, Composer composer, int i2) {
        ScheduleItem(schedule, function0, function02, function1, function03, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static final void HomeScreen(MainViewModel viewModel, final Function0<Unit> onOpenNotes, Composer $composer, final int $changed) {
        final Function0<Unit> function0;
        Composer $composer2;
        State userProfile$delegate;
        SnackbarHostState snackbarHostState;
        State userProfile$delegate2;
        State userProfile$delegate3;
        SnackbarHostState snackbarHostState2;
        Composer $composer3;
        Object next;
        SnackbarHostState snackbarHostState3;
        final List<Schedule> listHomeScreen$lambda$1;
        Composer $composer4;
        String str;
        MutableState focusDurationInput$delegate;
        SnapshotStateMap removingMap;
        Context context;
        CoroutineScope coroutineScope;
        MutableState confirmDeleteSchedule$delegate;
        Object obj;
        final MainViewModel mainViewModel;
        Function0<ComposeUiNode> function02;
        Composer $composer5;
        String str2;
        final MutableState showFocusDurationDialog$delegate;
        final MainViewModel viewModel2 = viewModel;
        Intrinsics.checkNotNullParameter(viewModel2, "viewModel");
        Intrinsics.checkNotNullParameter(onOpenNotes, "onOpenNotes");
        Composer $composer6 = $composer.startRestartGroup(-1878789610);
        ComposerKt.sourceInformation($composer6, "C(HomeScreen)N(viewModel,onOpenNotes)50@2375L16,51@2436L16,52@2493L16,54@2596L7,55@2632L32,56@2690L24,57@2742L44,58@2809L47,59@2890L44,60@2958L73,72@3490L34,73@3555L69,78@3735L11,79@3765L3038,75@3630L3173,152@6883L149:HomeScreen.kt#kl928v");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer6.changedInstance(viewModel2) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer6.changedInstance(onOpenNotes) ? 32 : 16;
        }
        int $dirty2 = $dirty;
        if ($composer6.shouldExecute(($dirty2 & 19) != 18, $dirty2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1878789610, $dirty2, -1, "com.example.scheduleorganizer.ui.screen.HomeScreen (HomeScreen.kt:49)");
            }
            State userProfile$delegate4 = SnapshotStateKt.collectAsState(viewModel2.getUserProfile(), null, $composer6, 0, 1);
            State schedules$delegate = SnapshotStateKt.collectAsState(viewModel2.getAllSchedules(), null, $composer6, 0, 1);
            State courses$delegate = SnapshotStateKt.collectAsState(viewModel2.getAllCourses(), null, $composer6, 0, 1);
            final MutableState<Long> selectedCourseId = viewModel2.getSelectedCourseId();
            ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
            ComposerKt.sourceInformationMarkerStart($composer6, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = $composer6.consume(localContext);
            ComposerKt.sourceInformationMarkerEnd($composer6);
            final Context context2 = (Context) objConsume;
            ComposerKt.sourceInformationMarkerStart($composer6, -1630539018, "CC(remember):HomeScreen.kt#9igjgp");
            Object objRememberedValue = $composer6.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                Object snackbarHostState4 = new SnackbarHostState();
                $composer6.updateRememberedValue(snackbarHostState4);
                objRememberedValue = snackbarHostState4;
            }
            SnackbarHostState snackbarHostState5 = (SnackbarHostState) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd($composer6);
            ComposerKt.sourceInformationMarkerStart($composer6, 773894976, "CC(rememberCoroutineScope)N(getContext)616@28039L68:Effects.kt#9igjgp");
            ComposerKt.sourceInformationMarkerStart($composer6, 683736516, "CC(remember):Effects.kt#9igjgp");
            Object objRememberedValue2 = $composer6.rememberedValue();
            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, $composer6);
                $composer6.updateRememberedValue(objRememberedValue2);
            }
            final CoroutineScope coroutineScope2 = (CoroutineScope) objRememberedValue2;
            ComposerKt.sourceInformationMarkerEnd($composer6);
            ComposerKt.sourceInformationMarkerEnd($composer6);
            ComposerKt.sourceInformationMarkerStart($composer6, -1630535486, "CC(remember):HomeScreen.kt#9igjgp");
            Object objRememberedValue3 = $composer6.rememberedValue();
            if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                userProfile$delegate = userProfile$delegate4;
                MutableState mutableStateMutableStateOf$default = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                $composer6.updateRememberedValue(mutableStateMutableStateOf$default);
                objRememberedValue3 = mutableStateMutableStateOf$default;
            } else {
                userProfile$delegate = userProfile$delegate4;
            }
            final MutableState editingSchedule$delegate = (MutableState) objRememberedValue3;
            ComposerKt.sourceInformationMarkerEnd($composer6);
            ComposerKt.sourceInformationMarkerStart($composer6, -1630533339, "CC(remember):HomeScreen.kt#9igjgp");
            Object objRememberedValue4 = $composer6.rememberedValue();
            if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                Object objMutableStateMapOf = SnapshotStateKt.mutableStateMapOf();
                $composer6.updateRememberedValue(objMutableStateMapOf);
                objRememberedValue4 = objMutableStateMapOf;
            }
            final SnapshotStateMap removingMap2 = (SnapshotStateMap) objRememberedValue4;
            ComposerKt.sourceInformationMarkerEnd($composer6);
            ComposerKt.sourceInformationMarkerStart($composer6, -1630530750, "CC(remember):HomeScreen.kt#9igjgp");
            Object objRememberedValue5 = $composer6.rememberedValue();
            if (objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                MutableState mutableStateMutableStateOf$default2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                $composer6.updateRememberedValue(mutableStateMutableStateOf$default2);
                objRememberedValue5 = mutableStateMutableStateOf$default2;
            }
            final MutableState confirmDeleteSchedule$delegate2 = (MutableState) objRememberedValue5;
            ComposerKt.sourceInformationMarkerEnd($composer6);
            ComposerKt.sourceInformationMarkerStart($composer6, -1630528545, "CC(remember):HomeScreen.kt#9igjgp");
            Object objRememberedValue6 = $composer6.rememberedValue();
            if (objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                snackbarHostState = snackbarHostState5;
                Object objMutableStateOf$default = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Integer.valueOf(ConsistencyManager.INSTANCE.getCurrentStreak(context2)), null, 2, null);
                $composer6.updateRememberedValue(objMutableStateOf$default);
                objRememberedValue6 = objMutableStateOf$default;
            } else {
                snackbarHostState = snackbarHostState5;
            }
            final MutableState consistency$delegate = (MutableState) objRememberedValue6;
            ComposerKt.sourceInformationMarkerEnd($composer6);
            Schedule scheduleHomeScreen$lambda$6 = HomeScreen$lambda$6(editingSchedule$delegate);
            if (scheduleHomeScreen$lambda$6 == null) {
                $composer6.startReplaceGroup(993320990);
                $composer6.endReplaceGroup();
                $composer3 = $composer6;
                userProfile$delegate3 = userProfile$delegate;
                snackbarHostState2 = snackbarHostState;
                userProfile$delegate2 = courses$delegate;
            } else {
                $composer6.startReplaceGroup(993320991);
                ComposerKt.sourceInformation($composer6, "*63@3155L26,63@3080L141");
                ComposerKt.sourceInformationMarkerStart($composer6, 162555656, "CC(remember):HomeScreen.kt#9igjgp");
                Object objRememberedValue7 = $composer6.rememberedValue();
                if (objRememberedValue7 == Composer.INSTANCE.getEmpty()) {
                    Object obj2 = new Function0() { // from class: com.example.scheduleorganizer.ui.screen.HomeScreenKt$$ExternalSyntheticLambda7
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return HomeScreenKt.HomeScreen$lambda$15$0$0(editingSchedule$delegate);
                        }
                    };
                    $composer6.updateRememberedValue(obj2);
                    objRememberedValue7 = obj2;
                }
                ComposerKt.sourceInformationMarkerEnd($composer6);
                State state = userProfile$delegate;
                userProfile$delegate2 = courses$delegate;
                userProfile$delegate3 = state;
                snackbarHostState2 = snackbarHostState;
                CommonComponentsKt.EditScheduleDialog(viewModel2, scheduleHomeScreen$lambda$6, (Function0) objRememberedValue7, snackbarHostState2, $composer6, ($dirty2 & 14) | 3456, 0);
                viewModel2 = viewModel2;
                $composer3 = $composer6;
                Unit unit = Unit.INSTANCE;
                $composer3.endReplaceGroup();
                Unit unit2 = Unit.INSTANCE;
            }
            if (HomeScreen$lambda$3(selectedCourseId) == null) {
                listHomeScreen$lambda$1 = HomeScreen$lambda$1(schedules$delegate);
                snackbarHostState3 = snackbarHostState2;
            } else {
                Iterator<T> it = HomeScreen$lambda$2(userProfile$delegate2).iterator();
                while (true) {
                    if (!it.hasNext()) {
                        next = null;
                        break;
                    }
                    next = it.next();
                    long id = ((Course) next).getId();
                    Long lHomeScreen$lambda$3 = HomeScreen$lambda$3(selectedCourseId);
                    if (lHomeScreen$lambda$3 != null && id == lHomeScreen$lambda$3.longValue()) {
                        break;
                    }
                }
                Course course = (Course) next;
                String courseName = course != null ? course.getName() : null;
                Iterable iterableHomeScreen$lambda$1 = HomeScreen$lambda$1(schedules$delegate);
                Collection arrayList = new ArrayList();
                for (Object obj3 : iterableHomeScreen$lambda$1) {
                    Iterable iterable = iterableHomeScreen$lambda$1;
                    SnackbarHostState snackbarHostState6 = snackbarHostState2;
                    if (Intrinsics.areEqual(((Schedule) obj3).getCategory(), courseName)) {
                        arrayList.add(obj3);
                    }
                    iterableHomeScreen$lambda$1 = iterable;
                    snackbarHostState2 = snackbarHostState6;
                }
                snackbarHostState3 = snackbarHostState2;
                listHomeScreen$lambda$1 = (List) arrayList;
            }
            ComposerKt.sourceInformationMarkerStart($composer3, -1630511560, "CC(remember):HomeScreen.kt#9igjgp");
            Composer composer = $composer3;
            Object objRememberedValue8 = composer.rememberedValue();
            if (objRememberedValue8 == Composer.INSTANCE.getEmpty()) {
                Object objMutableStateOf$default2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
                composer.updateRememberedValue(objMutableStateOf$default2);
                objRememberedValue8 = objMutableStateOf$default2;
            }
            final MutableState showFocusDurationDialog$delegate2 = (MutableState) objRememberedValue8;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, -1630509445, "CC(remember):HomeScreen.kt#9igjgp");
            Composer composer2 = $composer3;
            Object objRememberedValue9 = composer2.rememberedValue();
            if (objRememberedValue9 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue9 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(String.valueOf(viewModel2.getFocusDuration().getValue().intValue()), null, 2, null);
                composer2.updateRememberedValue(objRememberedValue9);
            }
            MutableState focusDurationInput$delegate2 = (MutableState) objRememberedValue9;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Modifier modifierM286backgroundbw27NRU$default = BackgroundKt.m286backgroundbw27NRU$default(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), MaterialTheme.INSTANCE.getColorScheme($composer3, MaterialTheme.$stable).getBackground(), null, 2, null);
            ComposerKt.sourceInformationMarkerStart($composer3, -1630499756, "CC(remember):HomeScreen.kt#9igjgp");
            final State courses$delegate2 = userProfile$delegate2;
            boolean zChanged = $composer3.changed(userProfile$delegate3) | (($dirty2 & 112) == 32) | $composer3.changedInstance(viewModel2) | $composer3.changed(courses$delegate2) | $composer3.changed(selectedCourseId) | $composer3.changedInstance(listHomeScreen$lambda$1) | $composer3.changedInstance(context2) | $composer3.changedInstance(coroutineScope2);
            Composer composer3 = $composer3;
            Object objRememberedValue10 = composer3.rememberedValue();
            if (zChanged || objRememberedValue10 == Composer.INSTANCE.getEmpty()) {
                $composer4 = $composer3;
                final State userProfile$delegate5 = userProfile$delegate3;
                str = "CC(remember):HomeScreen.kt#9igjgp";
                focusDurationInput$delegate = focusDurationInput$delegate2;
                final SnackbarHostState snackbarHostState7 = snackbarHostState3;
                Function1 function1 = new Function1() { // from class: com.example.scheduleorganizer.ui.screen.HomeScreenKt$$ExternalSyntheticLambda9
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj4) {
                        return HomeScreenKt.HomeScreen$lambda$24$0(listHomeScreen$lambda$1, userProfile$delegate5, onOpenNotes, viewModel2, showFocusDurationDialog$delegate2, courses$delegate2, selectedCourseId, removingMap2, context2, coroutineScope2, confirmDeleteSchedule$delegate2, editingSchedule$delegate, consistency$delegate, snackbarHostState7, (LazyListScope) obj4);
                    }
                };
                removingMap = removingMap2;
                context = context2;
                coroutineScope = coroutineScope2;
                confirmDeleteSchedule$delegate = confirmDeleteSchedule$delegate2;
                obj = function1;
                function0 = onOpenNotes;
                mainViewModel = viewModel2;
                composer3.updateRememberedValue(obj);
            } else {
                $composer4 = $composer3;
                mainViewModel = viewModel2;
                obj = objRememberedValue10;
                coroutineScope = coroutineScope2;
                context = context2;
                str = "CC(remember):HomeScreen.kt#9igjgp";
                removingMap = removingMap2;
                focusDurationInput$delegate = focusDurationInput$delegate2;
                confirmDeleteSchedule$delegate = confirmDeleteSchedule$delegate2;
                function0 = onOpenNotes;
            }
            ComposerKt.sourceInformationMarkerEnd($composer4);
            final MutableState confirmDeleteSchedule$delegate3 = confirmDeleteSchedule$delegate;
            Composer $composer7 = $composer4;
            LazyDslKt.LazyColumn(modifierM286backgroundbw27NRU$default, null, null, false, null, null, null, false, null, (Function1) obj, $composer7, 0, TypedValues.PositionType.TYPE_POSITION_TYPE);
            Modifier modifierFillMaxSize$default = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null);
            ComposerKt.sourceInformationMarkerStart($composer7, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
            ComposerKt.sourceInformationMarkerStart($composer7, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer7, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer7.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer7, modifierFillMaxSize$default);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i = ((((6 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer7, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
            if (!($composer7.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer7.startReusableNode();
            if ($composer7.getInserting()) {
                function02 = constructor;
                $composer7.createNode(function02);
            } else {
                function02 = constructor;
                $composer7.useNode();
            }
            Composer composerM4433constructorimpl = Updater.m4433constructorimpl($composer7);
            Updater.m4441setimpl(composerM4433constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4441setimpl(composerM4433constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4441setimpl(composerM4433constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4439reconcileimpl(composerM4433constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4441setimpl(composerM4433constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i2 = (i >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer7, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
            int i3 = ((6 >> 6) & 112) | 6;
            BoxScope boxScope = BoxScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer7, 771538517, "C153@6932L94:HomeScreen.kt#kl928v");
            final SnackbarHostState snackbarHostState8 = snackbarHostState3;
            SnackbarHostKt.SnackbarHost(snackbarHostState8, boxScope.align(Modifier.INSTANCE, Alignment.INSTANCE.getBottomCenter()), null, $composer7, 6, 4);
            ComposerKt.sourceInformationMarkerEnd($composer7);
            ComposerKt.sourceInformationMarkerEnd($composer7);
            $composer7.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer7);
            ComposerKt.sourceInformationMarkerEnd($composer7);
            ComposerKt.sourceInformationMarkerEnd($composer7);
            if (HomeScreen$lambda$19(showFocusDurationDialog$delegate2)) {
                $composer7.startReplaceGroup(997338498);
                ComposerKt.sourceInformation($composer7, "158@7121L35,173@7853L413,183@8296L109,160@7223L600,157@7077L1338");
                str2 = str;
                ComposerKt.sourceInformationMarkerStart($composer7, -1630395367, str2);
                Object objRememberedValue11 = $composer7.rememberedValue();
                if (objRememberedValue11 == Composer.INSTANCE.getEmpty()) {
                    showFocusDurationDialog$delegate = showFocusDurationDialog$delegate2;
                    Object obj4 = new Function0() { // from class: com.example.scheduleorganizer.ui.screen.HomeScreenKt$$ExternalSyntheticLambda10
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return HomeScreenKt.HomeScreen$lambda$26$0(showFocusDurationDialog$delegate);
                        }
                    };
                    $composer7.updateRememberedValue(obj4);
                    objRememberedValue11 = obj4;
                } else {
                    showFocusDurationDialog$delegate = showFocusDurationDialog$delegate2;
                }
                ComposerKt.sourceInformationMarkerEnd($composer7);
                final MutableState focusDurationInput$delegate3 = focusDurationInput$delegate;
                AndroidAlertDialog_androidKt.m2150AlertDialogOix01E0((Function0) objRememberedValue11, ComposableLambdaKt.rememberComposableLambda(1580127459, true, new Function2() { // from class: com.example.scheduleorganizer.ui.screen.HomeScreenKt$$ExternalSyntheticLambda11
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj5, Object obj6) {
                        return HomeScreenKt.HomeScreen$lambda$27(mainViewModel, focusDurationInput$delegate3, showFocusDurationDialog$delegate, (Composer) obj5, ((Integer) obj6).intValue());
                    }
                }, $composer7, 54), null, ComposableLambdaKt.rememberComposableLambda(-1315886235, true, new Function2() { // from class: com.example.scheduleorganizer.ui.screen.HomeScreenKt$$ExternalSyntheticLambda12
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj5, Object obj6) {
                        return HomeScreenKt.HomeScreen$lambda$28(showFocusDurationDialog$delegate, (Composer) obj5, ((Integer) obj6).intValue());
                    }
                }, $composer7, 54), null, ComposableSingletons$HomeScreenKt.INSTANCE.getLambda$83067367$app(), ComposableLambdaKt.rememberComposableLambda(-1364939480, true, new Function2() { // from class: com.example.scheduleorganizer.ui.screen.HomeScreenKt$$ExternalSyntheticLambda13
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj5, Object obj6) {
                        return HomeScreenKt.HomeScreen$lambda$29(focusDurationInput$delegate3, (Composer) obj5, ((Integer) obj6).intValue());
                    }
                }, $composer7, 54), null, 0L, 0L, 0L, 0L, 0.0f, null, $composer7, 1772598, 0, 16276);
                $composer5 = $composer7;
                $composer5.endReplaceGroup();
            } else {
                $composer5 = $composer7;
                str2 = str;
                $composer5.startReplaceGroup(998639692);
                $composer5.endReplaceGroup();
            }
            final Schedule scheduleHomeScreen$lambda$10 = HomeScreen$lambda$10(confirmDeleteSchedule$delegate3);
            if (scheduleHomeScreen$lambda$10 == null) {
                $composer5.startReplaceGroup(998712758);
                $composer5.endReplaceGroup();
                viewModel2 = mainViewModel;
                $composer2 = $composer5;
            } else {
                $composer5.startReplaceGroup(998712759);
                ComposerKt.sourceInformation($composer5, "*191@8520L32,194@8698L1029,212@9757L106,193@8623L45,190@8476L1397");
                ComposerKt.sourceInformationMarkerStart($composer5, 1621451543, str2);
                Composer composer4 = $composer5;
                Object objRememberedValue12 = composer4.rememberedValue();
                if (objRememberedValue12 == Composer.INSTANCE.getEmpty()) {
                    Object obj5 = new Function0() { // from class: com.example.scheduleorganizer.ui.screen.HomeScreenKt$$ExternalSyntheticLambda14
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return HomeScreenKt.HomeScreen$lambda$30$0$0(confirmDeleteSchedule$delegate3);
                        }
                    };
                    composer4.updateRememberedValue(obj5);
                    objRememberedValue12 = obj5;
                }
                Function0 function03 = (Function0) objRememberedValue12;
                ComposerKt.sourceInformationMarkerEnd($composer5);
                viewModel2 = viewModel;
                final Context context3 = context;
                final CoroutineScope coroutineScope3 = coroutineScope;
                final SnapshotStateMap removingMap3 = removingMap;
                $composer2 = $composer5;
                AndroidAlertDialog_androidKt.m2150AlertDialogOix01E0(function03, ComposableLambdaKt.rememberComposableLambda(-1446622417, true, new Function2() { // from class: com.example.scheduleorganizer.ui.screen.HomeScreenKt$$ExternalSyntheticLambda15
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj6, Object obj7) {
                        return HomeScreenKt.HomeScreen$lambda$30$1(coroutineScope3, scheduleHomeScreen$lambda$10, context3, viewModel2, confirmDeleteSchedule$delegate3, removingMap3, snackbarHostState8, (Composer) obj6, ((Integer) obj7).intValue());
                    }
                }, $composer5, 54), null, ComposableLambdaKt.rememberComposableLambda(1500211309, true, new Function2() { // from class: com.example.scheduleorganizer.ui.screen.HomeScreenKt$$ExternalSyntheticLambda16
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj6, Object obj7) {
                        return HomeScreenKt.HomeScreen$lambda$30$2(confirmDeleteSchedule$delegate3, (Composer) obj6, ((Integer) obj7).intValue());
                    }
                }, $composer5, 54), null, ComposableSingletons$HomeScreenKt.INSTANCE.getLambda$152077739$app(), ComposableLambdaKt.rememberComposableLambda(-521989046, true, new Function2() { // from class: com.example.scheduleorganizer.ui.screen.HomeScreenKt$$ExternalSyntheticLambda17
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj6, Object obj7) {
                        return HomeScreenKt.HomeScreen$lambda$30$3(scheduleHomeScreen$lambda$10, (Composer) obj6, ((Integer) obj7).intValue());
                    }
                }, $composer5, 54), null, 0L, 0L, 0L, 0L, 0.0f, null, $composer2, 1772598, 0, 16276);
                Unit unit3 = Unit.INSTANCE;
                $composer2.endReplaceGroup();
                Unit unit4 = Unit.INSTANCE;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            function0 = onOpenNotes;
            $composer2 = $composer6;
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.example.scheduleorganizer.ui.screen.HomeScreenKt$$ExternalSyntheticLambda8
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj6, Object obj7) {
                    return HomeScreenKt.HomeScreen$lambda$31(viewModel2, function0, $changed, (Composer) obj6, ((Integer) obj7).intValue());
                }
            });
        }
    }

    private static final UserProfile HomeScreen$lambda$0(State<UserProfile> state) {
        return (UserProfile) state.getValue();
    }

    private static final List<Schedule> HomeScreen$lambda$1(State<? extends List<Schedule>> state) {
        return (List) state.getValue();
    }

    private static final List<Course> HomeScreen$lambda$2(State<? extends List<Course>> state) {
        return (List) state.getValue();
    }

    private static final Long HomeScreen$lambda$3(MutableState<Long> mutableState) {
        return mutableState.getValue();
    }

    private static final Schedule HomeScreen$lambda$6(MutableState<Schedule> mutableState) {
        return mutableState.getValue();
    }

    private static final Schedule HomeScreen$lambda$10(MutableState<Schedule> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int HomeScreen$lambda$13(MutableState<Integer> mutableState) {
        return mutableState.getValue().intValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void HomeScreen$lambda$14(MutableState<Integer> mutableState, int i) {
        mutableState.setValue(Integer.valueOf(i));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit HomeScreen$lambda$15$0$0(MutableState $editingSchedule$delegate) {
        $editingSchedule$delegate.setValue(null);
        return Unit.INSTANCE;
    }

    private static final boolean HomeScreen$lambda$19(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void HomeScreen$lambda$20(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final String HomeScreen$lambda$22(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit HomeScreen$lambda$24$0(final List $filteredSchedules, final State $userProfile$delegate, final Function0 $onOpenNotes, final MainViewModel $viewModel, final MutableState $showFocusDurationDialog$delegate, final State $courses$delegate, final MutableState $selectedCourseId$delegate, final SnapshotStateMap $removingMap, final Context $context, final CoroutineScope $coroutineScope, final MutableState $confirmDeleteSchedule$delegate, final MutableState $editingSchedule$delegate, final MutableState $consistency$delegate, final SnackbarHostState $snackbarHostState, LazyListScope LazyColumn) {
        Intrinsics.checkNotNullParameter(LazyColumn, "$this$LazyColumn");
        LazyListScope.item$default(LazyColumn, null, null, ComposableLambdaKt.composableLambdaInstance(418608993, true, new Function3() { // from class: com.example.scheduleorganizer.ui.screen.HomeScreenKt$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return HomeScreenKt.HomeScreen$lambda$24$0$0($userProfile$delegate, (LazyItemScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        }), 3, null);
        LazyListScope.item$default(LazyColumn, null, null, ComposableLambdaKt.composableLambdaInstance(-1110715766, true, new Function3() { // from class: com.example.scheduleorganizer.ui.screen.HomeScreenKt$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return HomeScreenKt.HomeScreen$lambda$24$0$1($onOpenNotes, (LazyItemScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        }), 3, null);
        LazyListScope.item$default(LazyColumn, null, null, ComposableSingletons$HomeScreenKt.INSTANCE.m8718getLambda$856326359$app(), 3, null);
        LazyListScope.item$default(LazyColumn, null, null, ComposableLambdaKt.composableLambdaInstance(-601936952, true, new Function3() { // from class: com.example.scheduleorganizer.ui.screen.HomeScreenKt$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return HomeScreenKt.HomeScreen$lambda$24$0$2($viewModel, $showFocusDurationDialog$delegate, (LazyItemScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        }), 3, null);
        LazyListScope.item$default(LazyColumn, null, null, ComposableLambdaKt.composableLambdaInstance(-347547545, true, new Function3() { // from class: com.example.scheduleorganizer.ui.screen.HomeScreenKt$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return HomeScreenKt.HomeScreen$lambda$24$0$3($viewModel, $courses$delegate, $selectedCourseId$delegate, (LazyItemScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        }), 3, null);
        LazyListScope.item$default(LazyColumn, null, null, ComposableSingletons$HomeScreenKt.INSTANCE.m8719getLambda$93158138$app(), 3, null);
        final Function1 function1 = new Function1() { // from class: com.example.scheduleorganizer.ui.screen.HomeScreenKt$HomeScreen$lambda$24$0$$inlined$items$default$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object p1) {
                return invoke((Schedule) p1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Void invoke(Schedule schedule) {
                return null;
            }
        };
        LazyColumn.items($filteredSchedules.size(), null, new Function1<Integer, Object>() { // from class: com.example.scheduleorganizer.ui.screen.HomeScreenKt$HomeScreen$lambda$24$0$$inlined$items$default$3
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Integer num) {
                return invoke(num.intValue());
            }

            public final Object invoke(int index) {
                return function1.invoke($filteredSchedules.get(index));
            }
        }, ComposableLambdaKt.composableLambdaInstance(802480018, true, new Function4<LazyItemScope, Integer, Composer, Integer, Unit>() { // from class: com.example.scheduleorganizer.ui.screen.HomeScreenKt$HomeScreen$lambda$24$0$$inlined$items$default$4
            @Override // kotlin.jvm.functions.Function4
            public /* bridge */ /* synthetic */ Unit invoke(LazyItemScope lazyItemScope, Integer num, Composer composer, Integer num2) {
                invoke(lazyItemScope, num.intValue(), composer, num2.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(LazyItemScope $this$items, int it, Composer $composer, int $changed) {
                ComposerKt.sourceInformation($composer, "CN(it)178@8834L22:LazyDsl.kt#428nma");
                int $dirty = $changed;
                if (($changed & 6) == 0) {
                    $dirty |= $composer.changed($this$items) ? 4 : 2;
                }
                if (($changed & 48) == 0) {
                    $dirty |= $composer.changed(it) ? 32 : 16;
                }
                if (!$composer.shouldExecute(($dirty & 147) != 146, $dirty & 1)) {
                    $composer.skipToGroupEnd();
                    return;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(802480018, $dirty, -1, "androidx.compose.foundation.lazy.items.<anonymous> (LazyDsl.kt:178)");
                }
                int i = $dirty & 14;
                final Schedule schedule = (Schedule) $filteredSchedules.get(it);
                $composer.startReplaceGroup(143872323);
                ComposerKt.sourceInformation($composer, "CN(schedule)*121@5345L10,122@5434L10,123@5464L1191,119@5208L1447:HomeScreen.kt#kl928v");
                boolean z = !Intrinsics.areEqual($removingMap.get(Long.valueOf(schedule.getId())), (Object) true);
                EnterTransition enterTransitionFadeIn$default = EnterExitTransitionKt.fadeIn$default(null, 0.0f, 3, null);
                ComposerKt.sourceInformationMarkerStart($composer, 1528666658, "CC(remember):HomeScreen.kt#9igjgp");
                Object objRememberedValue = $composer.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    Object obj = (Function1) new Function1<Integer, Integer>() { // from class: com.example.scheduleorganizer.ui.screen.HomeScreenKt$HomeScreen$2$1$5$1$1
                        public final Integer invoke(int it2) {
                            return Integer.valueOf(it2 / 4);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Integer invoke(Integer num) {
                            return invoke(num.intValue());
                        }
                    };
                    $composer.updateRememberedValue(obj);
                    objRememberedValue = obj;
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                EnterTransition enterTransitionPlus = enterTransitionFadeIn$default.plus(EnterExitTransitionKt.slideInHorizontally$default(null, (Function1) objRememberedValue, 1, null));
                ExitTransition exitTransitionFadeOut$default = EnterExitTransitionKt.fadeOut$default(null, 0.0f, 3, null);
                ComposerKt.sourceInformationMarkerStart($composer, 1528669506, "CC(remember):HomeScreen.kt#9igjgp");
                Object objRememberedValue2 = $composer.rememberedValue();
                if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    Object obj2 = (Function1) new Function1<Integer, Integer>() { // from class: com.example.scheduleorganizer.ui.screen.HomeScreenKt$HomeScreen$2$1$5$2$1
                        public final Integer invoke(int it2) {
                            return Integer.valueOf(it2 / 4);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Integer invoke(Integer num) {
                            return invoke(num.intValue());
                        }
                    };
                    $composer.updateRememberedValue(obj2);
                    objRememberedValue2 = obj2;
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                ExitTransition exitTransitionPlus = exitTransitionFadeOut$default.plus(EnterExitTransitionKt.slideOutHorizontally$default(null, (Function1) objRememberedValue2, 1, null));
                final Context context = $context;
                final MainViewModel mainViewModel = $viewModel;
                final CoroutineScope coroutineScope = $coroutineScope;
                final MutableState mutableState = $confirmDeleteSchedule$delegate;
                final MutableState mutableState2 = $editingSchedule$delegate;
                final MutableState mutableState3 = $consistency$delegate;
                final SnackbarHostState snackbarHostState = $snackbarHostState;
                AnimatedVisibilityKt.AnimatedVisibility(z, (Modifier) null, enterTransitionPlus, exitTransitionPlus, (String) null, ComposableLambdaKt.rememberComposableLambda(-328538128, true, new Function3<AnimatedVisibilityScope, Composer, Integer, Unit>() { // from class: com.example.scheduleorganizer.ui.screen.HomeScreenKt$HomeScreen$2$1$5$3
                    @Override // kotlin.jvm.functions.Function3
                    public /* bridge */ /* synthetic */ Unit invoke(AnimatedVisibilityScope animatedVisibilityScope, Composer composer, Integer num) {
                        invoke(animatedVisibilityScope, composer, num.intValue());
                        return Unit.INSTANCE;
                    }

                    /* JADX WARN: Removed duplicated region for block: B:34:0x011e  */
                    /* JADX WARN: Removed duplicated region for block: B:37:0x014a  */
                    /* JADX WARN: Removed duplicated region for block: B:39:? A[RETURN, SYNTHETIC] */
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                        To view partially-correct code enable 'Show inconsistent code' option in preferences
                    */
                    public final void invoke(androidx.compose.animation.AnimatedVisibilityScope r25, androidx.compose.runtime.Composer r26, int r27) {
                        /*
                            Method dump skipped, instruction units count: 334
                            To view this dump change 'Code comments level' option to 'DEBUG'
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.example.scheduleorganizer.ui.screen.HomeScreenKt$HomeScreen$2$1$5$3.invoke(androidx.compose.animation.AnimatedVisibilityScope, androidx.compose.runtime.Composer, int):void");
                    }
                }, $composer, 54), $composer, 200064, 18);
                $composer.endReplaceGroup();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
        }));
        LazyListScope.item$default(LazyColumn, null, null, ComposableSingletons$HomeScreenKt.INSTANCE.getLambda$161231269$app(), 3, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit HomeScreen$lambda$24$0$0(State $userProfile$delegate, LazyItemScope item, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(item, "$this$item");
        ComposerKt.sourceInformation($composer, "C81@3794L26:HomeScreen.kt#kl928v");
        if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(418608993, $changed, -1, "com.example.scheduleorganizer.ui.screen.HomeScreen.<anonymous>.<anonymous>.<anonymous> (HomeScreen.kt:81)");
            }
            HeaderSection(HomeScreen$lambda$0($userProfile$delegate), $composer, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit HomeScreen$lambda$24$0$1(Function0 $onOpenNotes, LazyItemScope item, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(item, "$this$item");
        ComposerKt.sourceInformation($composer, "C85@3944L7,84@3858L151:HomeScreen.kt#kl928v");
        if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1110715766, $changed, -1, "com.example.scheduleorganizer.ui.screen.HomeScreen.<anonymous>.<anonymous>.<anonymous> (HomeScreen.kt:84)");
            }
            DailyNotesManager dailyNotesManager = DailyNotesManager.INSTANCE;
            ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
            ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = $composer.consume(localContext);
            ComposerKt.sourceInformationMarkerEnd($composer);
            DailyNotesPreview(dailyNotesManager.getDailyNote((Context) objConsume), $onOpenNotes, $composer, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit HomeScreen$lambda$24$0$2(final MainViewModel $viewModel, final MutableState $showFocusDurationDialog$delegate, LazyItemScope item, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(item, "$this$item");
        ComposerKt.sourceInformation($composer, "C97@4394L33,98@4455L33,99@4525L34,93@4162L411:HomeScreen.kt#kl928v");
        if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-601936952, $changed, -1, "com.example.scheduleorganizer.ui.screen.HomeScreen.<anonymous>.<anonymous>.<anonymous> (HomeScreen.kt:93)");
            }
            int iIntValue = $viewModel.getFocusTimeRemaining().getValue().intValue();
            int iIntValue2 = $viewModel.getFocusDuration().getValue().intValue();
            boolean zBooleanValue = $viewModel.isFocusRunning().getValue().booleanValue();
            ComposerKt.sourceInformationMarkerStart($composer, 1359181993, "CC(remember):HomeScreen.kt#9igjgp");
            boolean zChangedInstance = $composer.changedInstance($viewModel);
            Object objRememberedValue = $composer.rememberedValue();
            if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                Object obj = new Function0() { // from class: com.example.scheduleorganizer.ui.screen.HomeScreenKt$$ExternalSyntheticLambda29
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return HomeScreenKt.HomeScreen$lambda$24$0$2$0$0($viewModel);
                    }
                };
                $composer.updateRememberedValue(obj);
                objRememberedValue = obj;
            }
            Function0 function0 = (Function0) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerStart($composer, 1359183945, "CC(remember):HomeScreen.kt#9igjgp");
            boolean zChangedInstance2 = $composer.changedInstance($viewModel);
            Object objRememberedValue2 = $composer.rememberedValue();
            if (zChangedInstance2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                Object obj2 = new Function0() { // from class: com.example.scheduleorganizer.ui.screen.HomeScreenKt$$ExternalSyntheticLambda30
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return HomeScreenKt.HomeScreen$lambda$24$0$2$1$0($viewModel);
                    }
                };
                $composer.updateRememberedValue(obj2);
                objRememberedValue2 = obj2;
            }
            Function0 function02 = (Function0) objRememberedValue2;
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerStart($composer, 1359186186, "CC(remember):HomeScreen.kt#9igjgp");
            Object objRememberedValue3 = $composer.rememberedValue();
            if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                Object obj3 = new Function0() { // from class: com.example.scheduleorganizer.ui.screen.HomeScreenKt$$ExternalSyntheticLambda31
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return HomeScreenKt.HomeScreen$lambda$24$0$2$2$0($showFocusDurationDialog$delegate);
                    }
                };
                $composer.updateRememberedValue(obj3);
                objRememberedValue3 = obj3;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            FocusSessionSection(iIntValue, iIntValue2, zBooleanValue, function0, function02, (Function0) objRememberedValue3, $composer, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit HomeScreen$lambda$24$0$2$0$0(MainViewModel $viewModel) {
        $viewModel.startFocusSession();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit HomeScreen$lambda$24$0$2$1$0(MainViewModel $viewModel) {
        $viewModel.resetFocusSession();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit HomeScreen$lambda$24$0$2$2$0(MutableState $showFocusDurationDialog$delegate) {
        HomeScreen$lambda$20($showFocusDurationDialog$delegate, true);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit HomeScreen$lambda$24$0$3(final MainViewModel $viewModel, State $courses$delegate, MutableState $selectedCourseId$delegate, LazyItemScope item, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(item, "$this$item");
        ComposerKt.sourceInformation($composer, "C106@4753L41,103@4611L197:HomeScreen.kt#kl928v");
        if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-347547545, $changed, -1, "com.example.scheduleorganizer.ui.screen.HomeScreen.<anonymous>.<anonymous>.<anonymous> (HomeScreen.kt:103)");
            }
            List<Course> listHomeScreen$lambda$2 = HomeScreen$lambda$2($courses$delegate);
            Long lHomeScreen$lambda$3 = HomeScreen$lambda$3($selectedCourseId$delegate);
            ComposerKt.sourceInformationMarkerStart($composer, 1014263376, "CC(remember):HomeScreen.kt#9igjgp");
            boolean zChangedInstance = $composer.changedInstance($viewModel);
            Object objRememberedValue = $composer.rememberedValue();
            if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                Object obj = new Function1() { // from class: com.example.scheduleorganizer.ui.screen.HomeScreenKt$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return HomeScreenKt.HomeScreen$lambda$24$0$3$0$0($viewModel, (Long) obj2);
                    }
                };
                $composer.updateRememberedValue(obj);
                objRememberedValue = obj;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            MataKuliahSection(listHomeScreen$lambda$2, lHomeScreen$lambda$3, (Function1) objRememberedValue, $composer, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit HomeScreen$lambda$24$0$3$0$0(MainViewModel $viewModel, Long it) {
        $viewModel.getSelectedCourseId().setValue(it);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit HomeScreen$lambda$26$0(MutableState $showFocusDurationDialog$delegate) {
        HomeScreen$lambda$20($showFocusDurationDialog$delegate, false);
        return Unit.INSTANCE;
    }

    static final Unit HomeScreen$lambda$29(final MutableState $focusDurationInput$delegate, Composer $composer, int $changed) {
        Function0<ComposeUiNode> function0;
        Composer composer;
        ComposerKt.sourceInformation($composer, "C161@7241L568:HomeScreen.kt#kl928v");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1364939480, $changed, -1, "com.example.scheduleorganizer.ui.screen.HomeScreen.<anonymous> (HomeScreen.kt:161)");
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
            ComposerKt.sourceInformationMarkerStart($composer, 1182578428, "C162@7270L46,163@7337L41,166@7502L61,164@7399L392:HomeScreen.kt#kl928v");
            TextKt.m3157TextNvy7gAk("Atur durasi fokus dalam menit (1-120):", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262142);
            SpacerKt.Spacer(SizeKt.m1101height3ABfNKs(Modifier.INSTANCE, Dp.m8150constructorimpl(12)), $composer, 6);
            String strHomeScreen$lambda$22 = HomeScreen$lambda$22($focusDurationInput$delegate);
            Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            KeyboardOptions keyboardOptions = new KeyboardOptions(0, (Boolean) null, KeyboardType.INSTANCE.m7811getNumberPjHm6EE(), 0, (PlatformImeOptions) null, (Boolean) null, (LocaleList) null, 123, (DefaultConstructorMarker) null);
            ComposerKt.sourceInformationMarkerStart($composer, -100392677, "CC(remember):HomeScreen.kt#9igjgp");
            Object objRememberedValue = $composer.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                composer = $composer;
                Object obj = new Function1() { // from class: com.example.scheduleorganizer.ui.screen.HomeScreenKt$$ExternalSyntheticLambda28
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return HomeScreenKt.HomeScreen$lambda$29$0$0$0($focusDurationInput$delegate, (String) obj2);
                    }
                };
                $composer.updateRememberedValue(obj);
                objRememberedValue = obj;
            } else {
                composer = $composer;
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            Composer composer2 = composer;
            TextFieldKt.TextField(strHomeScreen$lambda$22, (Function1<? super String, Unit>) objRememberedValue, modifierFillMaxWidth$default, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$HomeScreenKt.INSTANCE.getLambda$1301367418$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, keyboardOptions, (KeyboardActions) null, false, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, composer2, 1573296, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 0, 8355768);
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
    public static final Unit HomeScreen$lambda$29$0$0$0(MutableState $focusDurationInput$delegate, String it) throws IOException {
        Intrinsics.checkNotNullParameter(it, "it");
        String str = it;
        Appendable sb = new StringBuilder();
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char cCharAt = str.charAt(i);
            if (Character.isDigit(cCharAt)) {
                sb.append(cCharAt);
            }
        }
        $focusDurationInput$delegate.setValue(((StringBuilder) sb).toString());
        return Unit.INSTANCE;
    }

    static final Unit HomeScreen$lambda$27(final MainViewModel $viewModel, final MutableState $focusDurationInput$delegate, final MutableState $showFocusDurationDialog$delegate, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C174@7888L308,174@7871L381:HomeScreen.kt#kl928v");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1580127459, $changed, -1, "com.example.scheduleorganizer.ui.screen.HomeScreen.<anonymous> (HomeScreen.kt:174)");
            }
            ComposerKt.sourceInformationMarkerStart($composer, -1915933257, "CC(remember):HomeScreen.kt#9igjgp");
            boolean zChangedInstance = $composer.changedInstance($viewModel);
            Object objRememberedValue = $composer.rememberedValue();
            if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                Object obj = new Function0() { // from class: com.example.scheduleorganizer.ui.screen.HomeScreenKt$$ExternalSyntheticLambda39
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return HomeScreenKt.HomeScreen$lambda$27$0$0($viewModel, $focusDurationInput$delegate, $showFocusDurationDialog$delegate);
                    }
                };
                $composer.updateRememberedValue(obj);
                objRememberedValue = obj;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            ButtonKt.Button((Function0) objRememberedValue, null, false, null, null, null, null, null, null, ComposableSingletons$HomeScreenKt.INSTANCE.m8717getLambda$445322029$app(), $composer, 805306368, TypedValues.PositionType.TYPE_POSITION_TYPE);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit HomeScreen$lambda$27$0$0(MainViewModel $viewModel, MutableState $focusDurationInput$delegate, MutableState $showFocusDurationDialog$delegate) {
        Integer intOrNull = StringsKt.toIntOrNull(HomeScreen$lambda$22($focusDurationInput$delegate));
        int duration = intOrNull != null ? RangesKt.coerceIn(intOrNull.intValue(), 1, 120) : $viewModel.getFocusDuration().getValue().intValue();
        $viewModel.setFocusDuration(duration);
        $focusDurationInput$delegate.setValue(String.valueOf(duration));
        HomeScreen$lambda$20($showFocusDurationDialog$delegate, false);
        return Unit.INSTANCE;
    }

    static final Unit HomeScreen$lambda$28(final MutableState $showFocusDurationDialog$delegate, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C184@8335L35,184@8314L77:HomeScreen.kt#kl928v");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1315886235, $changed, -1, "com.example.scheduleorganizer.ui.screen.HomeScreen.<anonymous> (HomeScreen.kt:184)");
            }
            ComposerKt.sourceInformationMarkerStart($composer, -1846285528, "CC(remember):HomeScreen.kt#9igjgp");
            Object objRememberedValue = $composer.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                Object obj = new Function0() { // from class: com.example.scheduleorganizer.ui.screen.HomeScreenKt$$ExternalSyntheticLambda18
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return HomeScreenKt.HomeScreen$lambda$28$0$0($showFocusDurationDialog$delegate);
                    }
                };
                $composer.updateRememberedValue(obj);
                objRememberedValue = obj;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            ButtonKt.TextButton((Function0) objRememberedValue, null, false, null, null, null, null, null, null, ComposableSingletons$HomeScreenKt.INSTANCE.getLambda$2106694946$app(), $composer, 805306374, TypedValues.PositionType.TYPE_POSITION_TYPE);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit HomeScreen$lambda$28$0$0(MutableState $showFocusDurationDialog$delegate) {
        HomeScreen$lambda$20($showFocusDurationDialog$delegate, false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit HomeScreen$lambda$30$0$0(MutableState $confirmDeleteSchedule$delegate) {
        $confirmDeleteSchedule$delegate.setValue(null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit HomeScreen$lambda$30$3(Schedule $schedule, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C193@8625L41:HomeScreen.kt#kl928v");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-521989046, $changed, -1, "com.example.scheduleorganizer.ui.screen.HomeScreen.<anonymous>.<anonymous> (HomeScreen.kt:193)");
            }
            TextKt.m3157TextNvy7gAk("Hapus jadwal '" + $schedule.getTitle() + "'?", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 0, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit HomeScreen$lambda$30$1(final CoroutineScope $coroutineScope, final Schedule $schedule, final Context $context, final MainViewModel $viewModel, final MutableState $confirmDeleteSchedule$delegate, final SnapshotStateMap $removingMap, final SnackbarHostState $snackbarHostState, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C195@8733L961,195@8716L997:HomeScreen.kt#kl928v");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1446622417, $changed, -1, "com.example.scheduleorganizer.ui.screen.HomeScreen.<anonymous>.<anonymous> (HomeScreen.kt:195)");
            }
            ComposerKt.sourceInformationMarkerStart($composer, 1365263248, "CC(remember):HomeScreen.kt#9igjgp");
            boolean zChangedInstance = $composer.changedInstance($coroutineScope) | $composer.changed($schedule) | $composer.changedInstance($context) | $composer.changedInstance($viewModel);
            Object objRememberedValue = $composer.rememberedValue();
            if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                Object obj = new Function0() { // from class: com.example.scheduleorganizer.ui.screen.HomeScreenKt$$ExternalSyntheticLambda36
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return HomeScreenKt.HomeScreen$lambda$30$1$0$0($coroutineScope, $confirmDeleteSchedule$delegate, $removingMap, $schedule, $context, $viewModel, $snackbarHostState);
                    }
                };
                $composer.updateRememberedValue(obj);
                objRememberedValue = obj;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            ButtonKt.Button((Function0) objRememberedValue, null, false, null, null, null, null, null, null, ComposableSingletons$HomeScreenKt.INSTANCE.m8716getLambda$398256833$app(), $composer, 805306368, TypedValues.PositionType.TYPE_POSITION_TYPE);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit HomeScreen$lambda$30$1$0$0(CoroutineScope $coroutineScope, MutableState $confirmDeleteSchedule$delegate, SnapshotStateMap $removingMap, Schedule $schedule, Context $context, MainViewModel $viewModel, SnackbarHostState $snackbarHostState) {
        $confirmDeleteSchedule$delegate.setValue(null);
        BuildersKt__Builders_commonKt.launch$default($coroutineScope, null, null, new HomeScreenKt$HomeScreen$8$2$1$1$1($removingMap, $schedule, $context, $viewModel, $snackbarHostState, null), 3, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit HomeScreen$lambda$30$2(final MutableState $confirmDeleteSchedule$delegate, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C213@9796L32,213@9775L74:HomeScreen.kt#kl928v");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1500211309, $changed, -1, "com.example.scheduleorganizer.ui.screen.HomeScreen.<anonymous>.<anonymous> (HomeScreen.kt:213)");
            }
            ComposerKt.sourceInformationMarkerStart($composer, -1405942227, "CC(remember):HomeScreen.kt#9igjgp");
            Object objRememberedValue = $composer.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                Object obj = new Function0() { // from class: com.example.scheduleorganizer.ui.screen.HomeScreenKt$$ExternalSyntheticLambda37
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return HomeScreenKt.HomeScreen$lambda$30$2$0$0($confirmDeleteSchedule$delegate);
                    }
                };
                $composer.updateRememberedValue(obj);
                objRememberedValue = obj;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            ButtonKt.TextButton((Function0) objRememberedValue, null, false, null, null, null, null, null, null, ComposableSingletons$HomeScreenKt.INSTANCE.getLambda$695752976$app(), $composer, 805306374, TypedValues.PositionType.TYPE_POSITION_TYPE);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit HomeScreen$lambda$30$2$0$0(MutableState $confirmDeleteSchedule$delegate) {
        $confirmDeleteSchedule$delegate.setValue(null);
        return Unit.INSTANCE;
    }

    public static final void HeaderSection(final UserProfile profile, Composer $composer, final int $changed) {
        Composer $composer2;
        Function0<ComposeUiNode> function0;
        Function0<ComposeUiNode> function02;
        String name;
        Composer $composer3 = $composer.startRestartGroup(-899261138);
        ComposerKt.sourceInformation($composer3, "C(HeaderSection)N(profile)228@10179L11,229@10238L11,221@9942L1099:HomeScreen.kt#kl928v");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(profile) ? 4 : 2;
        }
        if (!$composer3.shouldExecute(($dirty & 3) != 2, $dirty & 1)) {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-899261138, $dirty, -1, "com.example.scheduleorganizer.ui.screen.HeaderSection (HomeScreen.kt:220)");
            }
            Modifier modifierM1101height3ABfNKs = SizeKt.m1101height3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.m8150constructorimpl(220));
            Brush.Companion companion = Brush.INSTANCE;
            long primary = MaterialTheme.INSTANCE.getColorScheme($composer3, MaterialTheme.$stable).getPrimary();
            Modifier modifierM1048padding3ABfNKs = PaddingKt.m1048padding3ABfNKs(BackgroundKt.background$default(modifierM1101height3ABfNKs, Brush.Companion.m5268verticalGradient8A3gB4$default(companion, CollectionsKt.listOf((Object[]) new Color[]{Color.m5303boximpl(MaterialTheme.INSTANCE.getColorScheme($composer3, MaterialTheme.$stable).getPrimary()), Color.m5303boximpl(Color.m5311copywmQWz5c(primary, (14 & 1) != 0 ? Color.m5315getAlphaimpl(primary) : 0.8f, (14 & 2) != 0 ? Color.m5319getRedimpl(primary) : 0.0f, (14 & 4) != 0 ? Color.m5318getGreenimpl(primary) : 0.0f, (14 & 8) != 0 ? Color.m5316getBlueimpl(primary) : 0.0f))}), 0.0f, 0.0f, 0, 14, (Object) null), null, 0.0f, 6, null), Dp.m8150constructorimpl(24));
            ComposerKt.sourceInformationMarkerStart($composer3, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
            ComposerKt.sourceInformationMarkerStart($composer3, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer3, 0));
            $composer2 = $composer3;
            CompositionLocalMap currentCompositionLocalMap = $composer3.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer3, modifierM1048padding3ABfNKs);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i = ((((0 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                function0 = constructor;
                $composer3.createNode(function0);
            } else {
                function0 = constructor;
                $composer3.useNode();
            }
            Composer composerM4433constructorimpl = Updater.m4433constructorimpl($composer3);
            Updater.m4441setimpl(composerM4433constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4441setimpl(composerM4433constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4441setimpl(composerM4433constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4439reconcileimpl(composerM4433constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4441setimpl(composerM4433constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i2 = (i >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            int i3 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, 285287931, "C235@10375L660:HomeScreen.kt#kl928v");
            ComposerKt.sourceInformationMarkerStart($composer3, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            Modifier modifier = Modifier.INSTANCE;
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer3, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer3, 0));
            CompositionLocalMap currentCompositionLocalMap2 = $composer3.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier($composer3, modifier);
            Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
            int i4 = ((((0 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                function02 = constructor2;
                $composer3.createNode(function02);
            } else {
                function02 = constructor2;
                $composer3.useNode();
            }
            Composer composerM4433constructorimpl2 = Updater.m4433constructorimpl($composer3);
            Updater.m4441setimpl(composerM4433constructorimpl2, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4441setimpl(composerM4433constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4441setimpl(composerM4433constructorimpl2, Integer.valueOf(iHashCode2), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4439reconcileimpl(composerM4433constructorimpl2, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4441setimpl(composerM4433constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
            int i5 = (i4 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            int i6 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -916688636, "C236@10396L180,242@10589L163,247@10765L41,248@10819L206:HomeScreen.kt#kl928v");
            TextKt.m3157TextNvy7gAk("Schedule Organizer", null, Color.INSTANCE.m5350getWhite0d7_KjU(), null, TextUnitKt.getSp(28), null, FontWeight.INSTANCE.getBold(), null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer3, 1597830, 0, 262058);
            long jM5350getWhite0d7_KjU = Color.INSTANCE.m5350getWhite0d7_KjU();
            TextKt.m3157TextNvy7gAk("Manajemen waktu yang efisien", null, Color.m5311copywmQWz5c(jM5350getWhite0d7_KjU, (14 & 1) != 0 ? Color.m5315getAlphaimpl(jM5350getWhite0d7_KjU) : 0.8f, (14 & 2) != 0 ? Color.m5319getRedimpl(jM5350getWhite0d7_KjU) : 0.0f, (14 & 4) != 0 ? Color.m5318getGreenimpl(jM5350getWhite0d7_KjU) : 0.0f, (14 & 8) != 0 ? Color.m5316getBlueimpl(jM5350getWhite0d7_KjU) : 0.0f), null, TextUnitKt.getSp(14), null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer3, 24966, 0, 262122);
            SpacerKt.Spacer(SizeKt.m1101height3ABfNKs(Modifier.INSTANCE, Dp.m8150constructorimpl(20)), $composer3, 6);
            if (profile == null || (name = profile.getName()) == null) {
                name = "User";
            }
            TextKt.m3157TextNvy7gAk("Selamat Siang, " + name + " 👋", null, Color.INSTANCE.m5350getWhite0d7_KjU(), null, TextUnitKt.getSp(22), null, FontWeight.INSTANCE.getBold(), null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer3, 1597824, 0, 262058);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer3.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer3.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.example.scheduleorganizer.ui.screen.HomeScreenKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return HomeScreenKt.HeaderSection$lambda$1(profile, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final void DailyNotesPreview(final String note, final Function0<Unit> onOpenNotes, Composer $composer, final int $changed) {
        Intrinsics.checkNotNullParameter(note, "note");
        Intrinsics.checkNotNullParameter(onOpenNotes, "onOpenNotes");
        Composer $composer2 = $composer.startRestartGroup(534624577);
        ComposerKt.sourceInformation($composer2, "C(DailyNotesPreview)N(note,onOpenNotes)264@11268L17,266@11402L11,266@11360L62,267@11429L634,260@11124L939:HomeScreen.kt#kl928v");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(note) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changedInstance(onOpenNotes) ? 32 : 16;
        }
        if ($composer2.shouldExecute(($dirty & 19) != 18, $dirty & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(534624577, $dirty, -1, "com.example.scheduleorganizer.ui.screen.DailyNotesPreview (HomeScreen.kt:259)");
            }
            Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(PaddingKt.m1049paddingVpY3zN4(Modifier.INSTANCE, Dp.m8150constructorimpl(16), Dp.m8150constructorimpl(12)), 0.0f, 1, null);
            ComposerKt.sourceInformationMarkerStart($composer2, -1621850254, "CC(remember):HomeScreen.kt#9igjgp");
            boolean z = ($dirty & 112) == 32;
            Object objRememberedValue = $composer2.rememberedValue();
            if (z || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                Object obj = new Function0() { // from class: com.example.scheduleorganizer.ui.screen.HomeScreenKt$$ExternalSyntheticLambda19
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return HomeScreenKt.DailyNotesPreview$lambda$0$0(onOpenNotes);
                    }
                };
                $composer2.updateRememberedValue(obj);
                objRememberedValue = obj;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            CardKt.Card(ClickableKt.m321clickableoSLSa3U$default(modifierFillMaxWidth$default, false, null, null, null, (Function0) objRememberedValue, 15, null), RoundedCornerShapeKt.m1378RoundedCornerShape0680j_4(Dp.m8150constructorimpl(16)), CardDefaults.INSTANCE.m2228cardColorsro_MJ88(MaterialTheme.INSTANCE.getColorScheme($composer2, MaterialTheme.$stable).getSurface(), 0L, 0L, 0L, $composer2, CardDefaults.$stable << 12, 14), null, null, ComposableLambdaKt.rememberComposableLambda(-1901037553, true, new Function3() { // from class: com.example.scheduleorganizer.ui.screen.HomeScreenKt$$ExternalSyntheticLambda20
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj2, Object obj3, Object obj4) {
                    return HomeScreenKt.DailyNotesPreview$lambda$1(note, (ColumnScope) obj2, (Composer) obj3, ((Integer) obj4).intValue());
                }
            }, $composer2, 54), $composer2, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 24);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.example.scheduleorganizer.ui.screen.HomeScreenKt$$ExternalSyntheticLambda21
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    return HomeScreenKt.DailyNotesPreview$lambda$2(note, onOpenNotes, $changed, (Composer) obj2, ((Integer) obj3).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit DailyNotesPreview$lambda$0$0(Function0 $onOpenNotes) {
        $onOpenNotes.invoke();
        return Unit.INSTANCE;
    }

    static final Unit DailyNotesPreview$lambda$1(String $note, ColumnScope Card, Composer $composer, int $changed) {
        Function0<ComposeUiNode> function0;
        Intrinsics.checkNotNullParameter(Card, "$this$Card");
        ComposerKt.sourceInformation($composer, "C268@11439L618:HomeScreen.kt#kl928v");
        if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1901037553, $changed, -1, "com.example.scheduleorganizer.ui.screen.DailyNotesPreview.<anonymous> (HomeScreen.kt:268)");
            }
            Modifier modifierM1048padding3ABfNKs = PaddingKt.m1048padding3ABfNKs(Modifier.INSTANCE, Dp.m8150constructorimpl(16));
            ComposerKt.sourceInformationMarkerStart($composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer, ((6 >> 3) & 14) | ((6 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer, modifierM1048padding3ABfNKs);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i = ((((6 << 3) & 112) << 6) & 896) | 6;
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
            int i3 = ((6 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -1138541693, "C269@11496L62,270@11571L40,273@11765L10,274@11826L11,271@11624L274,277@11911L40,278@12027L11,278@11964L83:HomeScreen.kt#kl928v");
            TextKt.m3157TextNvy7gAk("📝 Catatan Harian", null, 0L, null, 0L, null, FontWeight.INSTANCE.getBold(), null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 1572870, 0, 262078);
            SpacerKt.Spacer(SizeKt.m1101height3ABfNKs(Modifier.INSTANCE, Dp.m8150constructorimpl(8)), $composer, 6);
            TextKt.m3157TextNvy7gAk(StringsKt.isBlank($note) ? "Tulis catatan harianmu di halaman Notes." : $note, null, MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnSurfaceVariant(), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 3, 0, null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodyMedium(), $composer, 0, 24576, 114682);
            SpacerKt.Spacer(SizeKt.m1101height3ABfNKs(Modifier.INSTANCE, Dp.m8150constructorimpl(8)), $composer, 6);
            TextKt.m3157TextNvy7gAk("Ketuk untuk membuka Notes", null, MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getPrimary(), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262138);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    public static final void ConsistencySection(final int count, Composer $composer, final int $changed) {
        Composer $composer2;
        Composer $composer3 = $composer.startRestartGroup(1837301616);
        ComposerKt.sourceInformation($composer3, "C(ConsistencySection)N(count)290@12326L11,290@12284L62,291@12353L714,285@12120L947:HomeScreen.kt#kl928v");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(count) ? 4 : 2;
        }
        int $dirty2 = $dirty;
        if (!$composer3.shouldExecute(($dirty2 & 3) != 2, $dirty2 & 1)) {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1837301616, $dirty2, -1, "com.example.scheduleorganizer.ui.screen.ConsistencySection (HomeScreen.kt:284)");
            }
            $composer2 = $composer3;
            CardKt.Card(SizeKt.fillMaxWidth$default(PaddingKt.m1048padding3ABfNKs(Modifier.INSTANCE, Dp.m8150constructorimpl(16)), 0.0f, 1, null), RoundedCornerShapeKt.m1378RoundedCornerShape0680j_4(Dp.m8150constructorimpl(16)), CardDefaults.INSTANCE.m2228cardColorsro_MJ88(MaterialTheme.INSTANCE.getColorScheme($composer3, MaterialTheme.$stable).getSurface(), 0L, 0L, 0L, $composer2, CardDefaults.$stable << 12, 14), null, null, ComposableLambdaKt.rememberComposableLambda(1574110014, true, new Function3() { // from class: com.example.scheduleorganizer.ui.screen.HomeScreenKt$$ExternalSyntheticLambda22
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return HomeScreenKt.ConsistencySection$lambda$0(count, (ColumnScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, $composer2, 54), $composer2, 196614, 24);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.example.scheduleorganizer.ui.screen.HomeScreenKt$$ExternalSyntheticLambda23
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return HomeScreenKt.ConsistencySection$lambda$1(count, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    static final Unit ConsistencySection$lambda$0(int $count, ColumnScope Card, Composer $composer, int $changed) {
        Function0<ComposeUiNode> function0;
        Function0<ComposeUiNode> function02;
        Intrinsics.checkNotNullParameter(Card, "$this$Card");
        ComposerKt.sourceInformation($composer, "C292@12363L698:HomeScreen.kt#kl928v");
        if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1574110014, $changed, -1, "com.example.scheduleorganizer.ui.screen.ConsistencySection.<anonymous> (HomeScreen.kt:292)");
            }
            Modifier modifierM1048padding3ABfNKs = PaddingKt.m1048padding3ABfNKs(Modifier.INSTANCE, Dp.m8150constructorimpl(16));
            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
            ComposerKt.sourceInformationMarkerStart($composer, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically, $composer, ((390 >> 3) & 14) | ((390 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer, modifierM1048padding3ABfNKs);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i = ((((390 << 3) & 112) << 6) & 896) | 6;
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
            Updater.m4441setimpl(composerM4433constructorimpl, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4441setimpl(composerM4433constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4441setimpl(composerM4433constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4439reconcileimpl(composerM4433constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4441setimpl(composerM4433constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i2 = (i >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
            int i3 = ((390 >> 6) & 112) | 6;
            RowScope rowScope = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer, 1594542209, "C296@12499L285,304@12948L10,305@13009L11,301@12797L254:HomeScreen.kt#kl928v");
            Modifier modifierWeight$default = RowScope.weight$default(rowScope, Modifier.INSTANCE, 1.0f, false, 2, null);
            ComposerKt.sourceInformationMarkerStart($composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer, ((0 >> 3) & 14) | ((0 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
            CompositionLocalMap currentCompositionLocalMap2 = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier($composer, modifierWeight$default);
            Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
            int i4 = ((((0 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
            if (!($composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer.startReusableNode();
            if ($composer.getInserting()) {
                function02 = constructor2;
                $composer.createNode(function02);
            } else {
                function02 = constructor2;
                $composer.useNode();
            }
            Composer composerM4433constructorimpl2 = Updater.m4433constructorimpl($composer);
            Updater.m4441setimpl(composerM4433constructorimpl2, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4441setimpl(composerM4433constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4441setimpl(composerM4433constructorimpl2, Integer.valueOf(iHashCode2), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4439reconcileimpl(composerM4433constructorimpl2, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4441setimpl(composerM4433constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
            int i5 = (i4 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            int i6 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -1011328375, "C297@12556L35,298@12608L69,299@12741L11,299@12694L76:HomeScreen.kt#kl928v");
            TextKt.m3157TextNvy7gAk("🔥", null, 0L, null, TextUnitKt.getSp(24), null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 24582, 0, 262126);
            TextKt.m3157TextNvy7gAk(String.valueOf($count), null, 0L, null, TextUnitKt.getSp(24), null, FontWeight.INSTANCE.getBold(), null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 1597440, 0, 262062);
            TextKt.m3157TextNvy7gAk("Konsisten", null, MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnSurfaceVariant(), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262138);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            TextKt.m3157TextNvy7gAk("\"Mulai sekarang, bukan nanti.\"", RowScope.weight$default(rowScope, Modifier.INSTANCE, 2.0f, false, 2, null), MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnSurfaceVariant(), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodyMedium(), $composer, 6, 0, 131064);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    public static final void FocusSessionSection(final int time, final int durationMinutes, final boolean isRunning, final Function0<Unit> onToggle, final Function0<Unit> onReset, final Function0<Unit> onAdjustDuration, Composer $composer, final int $changed) {
        int i;
        Intrinsics.checkNotNullParameter(onToggle, "onToggle");
        Intrinsics.checkNotNullParameter(onReset, "onReset");
        Intrinsics.checkNotNullParameter(onAdjustDuration, "onAdjustDuration");
        Composer $composer2 = $composer.startRestartGroup(-1453571087);
        ComposerKt.sourceInformation($composer2, "C(FocusSessionSection)N(time,durationMinutes,isRunning,onToggle,onReset,onAdjustDuration)318@13458L11,318@13416L62,319@13485L1691,313@13239L1937:HomeScreen.kt#kl928v");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            i = time;
            $dirty |= $composer2.changed(i) ? 4 : 2;
        } else {
            i = time;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(durationMinutes) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer2.changed(isRunning) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            $dirty |= $composer2.changedInstance(onToggle) ? 2048 : 1024;
        }
        if (($changed & 24576) == 0) {
            $dirty |= $composer2.changedInstance(onReset) ? 16384 : 8192;
        }
        if ((196608 & $changed) == 0) {
            $dirty |= $composer2.changedInstance(onAdjustDuration) ? 131072 : 65536;
        }
        if ($composer2.shouldExecute((74899 & $dirty) != 74898, $dirty & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1453571087, $dirty, -1, "com.example.scheduleorganizer.ui.screen.FocusSessionSection (HomeScreen.kt:312)");
            }
            Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(PaddingKt.m1050paddingVpY3zN4$default(Modifier.INSTANCE, Dp.m8150constructorimpl(16), 0.0f, 2, null), 0.0f, 1, null);
            RoundedCornerShape roundedCornerShapeM1378RoundedCornerShape0680j_4 = RoundedCornerShapeKt.m1378RoundedCornerShape0680j_4(Dp.m8150constructorimpl(16));
            CardColors cardColorsM2228cardColorsro_MJ88 = CardDefaults.INSTANCE.m2228cardColorsro_MJ88(MaterialTheme.INSTANCE.getColorScheme($composer2, MaterialTheme.$stable).getSurface(), 0L, 0L, 0L, $composer2, CardDefaults.$stable << 12, 14);
            $composer2 = $composer2;
            final int $dirty2 = i;
            CardKt.Card(modifierFillMaxWidth$default, roundedCornerShapeM1378RoundedCornerShape0680j_4, cardColorsM2228cardColorsro_MJ88, null, null, ComposableLambdaKt.rememberComposableLambda(1183132515, true, new Function3() { // from class: com.example.scheduleorganizer.ui.screen.HomeScreenKt$$ExternalSyntheticLambda32
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return HomeScreenKt.FocusSessionSection$lambda$0(onToggle, onReset, onAdjustDuration, $dirty2, isRunning, durationMinutes, (ColumnScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, $composer2, 54), $composer2, 196614, 24);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.example.scheduleorganizer.ui.screen.HomeScreenKt$$ExternalSyntheticLambda33
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return HomeScreenKt.FocusSessionSection$lambda$1(time, durationMinutes, isRunning, onToggle, onReset, onAdjustDuration, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    static final Unit FocusSessionSection$lambda$0(Function0 $onToggle, Function0 $onReset, Function0 $onAdjustDuration, int $time, final boolean $isRunning, final int $durationMinutes, ColumnScope Card, Composer $composer, int $changed) {
        Function0<ComposeUiNode> function0;
        Function0<ComposeUiNode> function02;
        Intrinsics.checkNotNullParameter(Card, "$this$Card");
        ComposerKt.sourceInformation($composer, "C320@13495L1675:HomeScreen.kt#kl928v");
        if ($composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1183132515, $changed, -1, "com.example.scheduleorganizer.ui.screen.FocusSessionSection.<anonymous> (HomeScreen.kt:320)");
            }
            Modifier modifierM1048padding3ABfNKs = PaddingKt.m1048padding3ABfNKs(Modifier.INSTANCE, Dp.m8150constructorimpl(16));
            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
            ComposerKt.sourceInformationMarkerStart($composer, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically, $composer, ((390 >> 3) & 14) | ((390 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer, modifierM1048padding3ABfNKs);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i = ((((390 << 3) & 112) << 6) & 896) | 6;
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
            Updater.m4441setimpl(composerM4433constructorimpl, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4441setimpl(composerM4433constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4441setimpl(composerM4433constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4439reconcileimpl(composerM4433constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4441setimpl(composerM4433constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i2 = (i >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
            int i3 = ((390 >> 6) & 112) | 6;
            RowScope rowScope = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer, 2045127491, "C324@13631L586,341@14393L11,342@14455L148,337@14230L373,345@14616L39,350@14830L11,346@14668L317,354@14998L39,355@15093L67,355@15050L110:HomeScreen.kt#kl928v");
            Modifier modifierWeight$default = RowScope.weight$default(rowScope, Modifier.INSTANCE, 1.5f, false, 2, null);
            ComposerKt.sourceInformationMarkerStart($composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer, ((0 >> 3) & 14) | ((0 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
            CompositionLocalMap currentCompositionLocalMap2 = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier($composer, modifierWeight$default);
            Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
            int i4 = ((((0 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
            if (!($composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer.startReusableNode();
            if ($composer.getInserting()) {
                function02 = constructor2;
                $composer.createNode(function02);
            } else {
                function02 = constructor2;
                $composer.useNode();
            }
            Composer composerM4433constructorimpl2 = Updater.m4433constructorimpl($composer);
            Updater.m4441setimpl(composerM4433constructorimpl2, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4441setimpl(composerM4433constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4441setimpl(composerM4433constructorimpl2, Integer.valueOf(iHashCode2), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4439reconcileimpl(composerM4433constructorimpl2, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4441setimpl(composerM4433constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
            int i5 = (i4 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            int i6 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -1905015163, "C331@13979L11,327@13770L318,335@14156L11,335@14105L98:HomeScreen.kt#kl928v");
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String str = String.format("%02d:%02d", Arrays.copyOf(new Object[]{Integer.valueOf($time / 60), Integer.valueOf($time % 60)}, 2));
            Intrinsics.checkNotNullExpressionValue(str, "format(...)");
            TextKt.m3157TextNvy7gAk(str, null, MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getPrimary(), null, TextUnitKt.getSp(28), null, FontWeight.INSTANCE.getBold(), null, 0L, null, null, 0L, 0, false, 1, 0, null, null, $composer, 1597440, 27648, 237482);
            TextKt.m3157TextNvy7gAk("Fokus Session", null, MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnSurfaceVariant(), null, TextUnitKt.getSp(12), null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 24582, 0, 262122);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            IconButtonKt.IconButton($onToggle, BackgroundKt.m285backgroundbw27NRU(SizeKt.m1115size3ABfNKs(Modifier.INSTANCE, Dp.m8150constructorimpl(48)), MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getPrimary(), RoundedCornerShapeKt.m1378RoundedCornerShape0680j_4(Dp.m8150constructorimpl(12))), false, null, null, null, ComposableLambdaKt.rememberComposableLambda(600035045, true, new Function2() { // from class: com.example.scheduleorganizer.ui.screen.HomeScreenKt$$ExternalSyntheticLambda34
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return HomeScreenKt.FocusSessionSection$lambda$0$0$1($isRunning, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer, 54), $composer, 1572864, 60);
            SpacerKt.Spacer(SizeKt.m1120width3ABfNKs(Modifier.INSTANCE, Dp.m8150constructorimpl(8)), $composer, 6);
            IconButtonKt.IconButton($onReset, BackgroundKt.m285backgroundbw27NRU(SizeKt.m1115size3ABfNKs(Modifier.INSTANCE, Dp.m8150constructorimpl(48)), MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getSurfaceVariant(), RoundedCornerShapeKt.m1378RoundedCornerShape0680j_4(Dp.m8150constructorimpl(12))), false, null, null, null, ComposableSingletons$HomeScreenKt.INSTANCE.getLambda$210702876$app(), $composer, 1572864, 60);
            SpacerKt.Spacer(SizeKt.m1120width3ABfNKs(Modifier.INSTANCE, Dp.m8150constructorimpl(8)), $composer, 6);
            ButtonKt.OutlinedButton($onAdjustDuration, null, false, null, null, null, null, null, null, ComposableLambdaKt.rememberComposableLambda(-1028551, true, new Function3() { // from class: com.example.scheduleorganizer.ui.screen.HomeScreenKt$$ExternalSyntheticLambda35
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return HomeScreenKt.FocusSessionSection$lambda$0$0$2($durationMinutes, (RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, $composer, 54), $composer, 805306368, TypedValues.PositionType.TYPE_POSITION_TYPE);
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
    public static final Unit FocusSessionSection$lambda$0$0$1(boolean $isRunning, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C343@14473L116:HomeScreen.kt#kl928v");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(600035045, $changed, -1, "com.example.scheduleorganizer.ui.screen.FocusSessionSection.<anonymous>.<anonymous>.<anonymous> (HomeScreen.kt:343)");
            }
            Icons.Filled filled = Icons.INSTANCE.getDefault();
            IconKt.m2605Iconww6aTOc($isRunning ? PauseKt.getPause(filled) : PlayArrowKt.getPlayArrow(filled), (String) null, (Modifier) null, Color.INSTANCE.m5350getWhite0d7_KjU(), $composer, 3120, 4);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit FocusSessionSection$lambda$0$0$2(int $durationMinutes, RowScope OutlinedButton, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(OutlinedButton, "$this$OutlinedButton");
        ComposerKt.sourceInformation($composer, "C356@15111L35:HomeScreen.kt#kl928v");
        if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1028551, $changed, -1, "com.example.scheduleorganizer.ui.screen.FocusSessionSection.<anonymous>.<anonymous>.<anonymous> (HomeScreen.kt:356)");
            }
            TextKt.m3157TextNvy7gAk("Durasi: " + $durationMinutes + "m", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 0, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    public static final void MataKuliahSection(final List<Course> courses, final Long selectedCourseId, final Function1<? super Long, Unit> onCourseSelected, Composer $composer, final int $changed) {
        Function0<ComposeUiNode> function0;
        Intrinsics.checkNotNullParameter(courses, "courses");
        Intrinsics.checkNotNullParameter(onCourseSelected, "onCourseSelected");
        Composer $composer2 = $composer.startRestartGroup(-679455131);
        ComposerKt.sourceInformation($composer2, "C(MataKuliahSection)N(courses,selectedCourseId,onCourseSelected)364@15345L1012:HomeScreen.kt#kl928v");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changedInstance(courses) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(selectedCourseId) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer2.changedInstance(onCourseSelected) ? 256 : 128;
        }
        if (!$composer2.shouldExecute(($dirty & 147) != 146, $dirty & 1)) {
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-679455131, $dirty, -1, "com.example.scheduleorganizer.ui.screen.MataKuliahSection (HomeScreen.kt:363)");
            }
            ComposerKt.sourceInformationMarkerStart($composer2, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            Modifier modifier = Modifier.INSTANCE;
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer2, ((0 >> 3) & 14) | ((0 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer2.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer2, modifier);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i = ((((0 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
            if (!($composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer2.startReusableNode();
            if ($composer2.getInserting()) {
                function0 = constructor;
                $composer2.createNode(function0);
            } else {
                function0 = constructor;
                $composer2.useNode();
            }
            Composer composerM4433constructorimpl = Updater.m4433constructorimpl($composer2);
            Updater.m4441setimpl(composerM4433constructorimpl, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4441setimpl(composerM4433constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4441setimpl(composerM4433constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4439reconcileimpl(composerM4433constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4441setimpl(composerM4433constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i2 = (i >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            int i3 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, 1550230475, "C367@15439L10,365@15362L199,374@15716L635,371@15570L781:HomeScreen.kt#kl928v");
            TextKt.m3157TextNvy7gAk("📚 Mata Kuliah", PaddingKt.m1048padding3ABfNKs(Modifier.INSTANCE, Dp.m8150constructorimpl(16)), 0L, null, 0L, null, FontWeight.INSTANCE.getBold(), null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography($composer2, MaterialTheme.$stable).getTitleMedium(), $composer2, 1572918, 0, 131004);
            PaddingValues paddingValuesM1043PaddingValuesYgX7TsA$default = PaddingKt.m1043PaddingValuesYgX7TsA$default(Dp.m8150constructorimpl(16), 0.0f, 2, null);
            Arrangement.HorizontalOrVertical horizontalOrVerticalM740spacedBy0680j_4 = Arrangement.INSTANCE.m740spacedBy0680j_4(Dp.m8150constructorimpl(8));
            ComposerKt.sourceInformationMarkerStart($composer2, 1574039062, "CC(remember):HomeScreen.kt#9igjgp");
            boolean zChangedInstance = (($dirty & 112) == 32) | (($dirty & 896) == 256) | $composer2.changedInstance(courses);
            Object objRememberedValue = $composer2.rememberedValue();
            if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                Object obj = new Function1() { // from class: com.example.scheduleorganizer.ui.screen.HomeScreenKt$$ExternalSyntheticLambda26
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return HomeScreenKt.MataKuliahSection$lambda$0$0$0(courses, selectedCourseId, onCourseSelected, (LazyListScope) obj2);
                    }
                };
                $composer2.updateRememberedValue(obj);
                objRememberedValue = obj;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            LazyDslKt.LazyRow(null, null, paddingValuesM1043PaddingValuesYgX7TsA$default, false, horizontalOrVerticalM740spacedBy0680j_4, null, null, false, null, (Function1) objRememberedValue, $composer2, 24960, 491);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.example.scheduleorganizer.ui.screen.HomeScreenKt$$ExternalSyntheticLambda27
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    return HomeScreenKt.MataKuliahSection$lambda$1(courses, selectedCourseId, onCourseSelected, $changed, (Composer) obj2, ((Integer) obj3).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MataKuliahSection$lambda$0$0$0(final List $courses, final Long $selectedCourseId, final Function1 $onCourseSelected, LazyListScope LazyRow) {
        Intrinsics.checkNotNullParameter(LazyRow, "$this$LazyRow");
        LazyListScope.item$default(LazyRow, null, null, ComposableLambdaKt.composableLambdaInstance(-383308328, true, new Function3() { // from class: com.example.scheduleorganizer.ui.screen.HomeScreenKt$$ExternalSyntheticLambda38
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return HomeScreenKt.MataKuliahSection$lambda$0$0$0$0($selectedCourseId, $onCourseSelected, (LazyItemScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        }), 3, null);
        final Function1 function1 = new Function1() { // from class: com.example.scheduleorganizer.ui.screen.HomeScreenKt$MataKuliahSection$lambda$0$0$0$$inlined$items$default$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object p1) {
                return invoke((Course) p1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Void invoke(Course course) {
                return null;
            }
        };
        LazyRow.items($courses.size(), null, new Function1<Integer, Object>() { // from class: com.example.scheduleorganizer.ui.screen.HomeScreenKt$MataKuliahSection$lambda$0$0$0$$inlined$items$default$3
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Integer num) {
                return invoke(num.intValue());
            }

            public final Object invoke(int index) {
                return function1.invoke($courses.get(index));
            }
        }, ComposableLambdaKt.composableLambdaInstance(802480018, true, new Function4<LazyItemScope, Integer, Composer, Integer, Unit>() { // from class: com.example.scheduleorganizer.ui.screen.HomeScreenKt$MataKuliahSection$lambda$0$0$0$$inlined$items$default$4
            @Override // kotlin.jvm.functions.Function4
            public /* bridge */ /* synthetic */ Unit invoke(LazyItemScope lazyItemScope, Integer num, Composer composer, Integer num2) {
                invoke(lazyItemScope, num.intValue(), composer, num2.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(LazyItemScope $this$items, int it, Composer $composer, int $changed) {
                ComposerKt.sourceInformation($composer, "CN(it)178@8834L22:LazyDsl.kt#428nma");
                int $dirty = $changed;
                if (($changed & 6) == 0) {
                    $dirty |= $composer.changed($this$items) ? 4 : 2;
                }
                if (($changed & 48) == 0) {
                    $dirty |= $composer.changed(it) ? 32 : 16;
                }
                if (!$composer.shouldExecute(($dirty & 147) != 146, $dirty & 1)) {
                    $composer.skipToGroupEnd();
                    return;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(802480018, $dirty, -1, "androidx.compose.foundation.lazy.items.<anonymous> (LazyDsl.kt:178)");
                }
                int i = $dirty & 14;
                final Course course = (Course) $courses.get(it);
                $composer.startReplaceGroup(-2110348043);
                ComposerKt.sourceInformation($composer, "CN(course)*386@16172L31,387@16233L21,384@16068L259:HomeScreen.kt#kl928v");
                Long l = $selectedCourseId;
                boolean z = l != null && l.longValue() == course.getId();
                ComposerKt.sourceInformationMarkerStart($composer, 1455948010, "CC(remember):HomeScreen.kt#9igjgp");
                boolean zChanged = $composer.changed($onCourseSelected) | ((((i & 112) ^ 48) > 32 && $composer.changed(course)) || (i & 48) == 32);
                Object objRememberedValue = $composer.rememberedValue();
                if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    final Function1 function12 = $onCourseSelected;
                    Object obj = (Function0) new Function0<Unit>() { // from class: com.example.scheduleorganizer.ui.screen.HomeScreenKt$MataKuliahSection$1$1$1$2$1$1
                        @Override // kotlin.jvm.functions.Function0
                        public /* bridge */ /* synthetic */ Unit invoke() {
                            invoke2();
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2() {
                            function12.invoke(Long.valueOf(course.getId()));
                        }
                    };
                    $composer.updateRememberedValue(obj);
                    objRememberedValue = obj;
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                ChipKt.FilterChip(z, (Function0) objRememberedValue, ComposableLambdaKt.rememberComposableLambda(1361479678, true, new Function2<Composer, Integer, Unit>() { // from class: com.example.scheduleorganizer.ui.screen.HomeScreenKt$MataKuliahSection$1$1$1$2$2
                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                        invoke(composer, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer $composer2, int $changed2) {
                        ComposerKt.sourceInformation($composer2, "C387@16235L17:HomeScreen.kt#kl928v");
                        if (!$composer2.shouldExecute(($changed2 & 3) != 2, $changed2 & 1)) {
                            $composer2.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1361479678, $changed2, -1, "com.example.scheduleorganizer.ui.screen.MataKuliahSection.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (HomeScreen.kt:387)");
                        }
                        TextKt.m3157TextNvy7gAk(course.getName(), null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer2, 0, 0, 262142);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }, $composer, 54), null, false, null, null, RoundedCornerShapeKt.m1378RoundedCornerShape0680j_4(Dp.m8150constructorimpl(20)), null, null, null, null, $composer, 384, 0, 3960);
                $composer.endReplaceGroup();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
        }));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MataKuliahSection$lambda$0$0$0$0(Long $selectedCourseId, final Function1 $onCourseSelected, LazyItemScope item, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(item, "$this$item");
        ComposerKt.sourceInformation($composer, "C378@15852L26,376@15753L245:HomeScreen.kt#kl928v");
        if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-383308328, $changed, -1, "com.example.scheduleorganizer.ui.screen.MataKuliahSection.<anonymous>.<anonymous>.<anonymous>.<anonymous> (HomeScreen.kt:376)");
            }
            boolean z = $selectedCourseId == null;
            ComposerKt.sourceInformationMarkerStart($composer, 1007888018, "CC(remember):HomeScreen.kt#9igjgp");
            boolean zChanged = $composer.changed($onCourseSelected);
            Object objRememberedValue = $composer.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                Object obj = new Function0() { // from class: com.example.scheduleorganizer.ui.screen.HomeScreenKt$$ExternalSyntheticLambda6
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return HomeScreenKt.MataKuliahSection$lambda$0$0$0$0$0$0($onCourseSelected);
                    }
                };
                $composer.updateRememberedValue(obj);
                objRememberedValue = obj;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            ChipKt.FilterChip(z, (Function0) objRememberedValue, ComposableSingletons$HomeScreenKt.INSTANCE.m8715getLambda$1534785467$app(), null, false, null, null, RoundedCornerShapeKt.m1378RoundedCornerShape0680j_4(Dp.m8150constructorimpl(20)), null, null, null, null, $composer, 384, 0, 3960);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MataKuliahSection$lambda$0$0$0$0$0$0(Function1 $onCourseSelected) {
        $onCourseSelected.invoke(null);
        return Unit.INSTANCE;
    }

    public static final void ScheduleItem(final Schedule schedule, final Function0<Unit> onDelete, final Function0<Unit> onEdit, final Function1<? super Boolean, Unit> onToggleActive, final Function0<Unit> onAdhere, Composer $composer, final int $changed) {
        Intrinsics.checkNotNullParameter(schedule, "schedule");
        Intrinsics.checkNotNullParameter(onDelete, "onDelete");
        Intrinsics.checkNotNullParameter(onEdit, "onEdit");
        Intrinsics.checkNotNullParameter(onToggleActive, "onToggleActive");
        Intrinsics.checkNotNullParameter(onAdhere, "onAdhere");
        Composer $composer2 = $composer.startRestartGroup(-1674279866);
        ComposerKt.sourceInformation($composer2, "C(ScheduleItem)N(schedule,onDelete,onEdit,onToggleActive,onAdhere)402@16751L11,402@16709L62,403@16778L2579,397@16515L2842:HomeScreen.kt#kl928v");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(schedule) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changedInstance(onDelete) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer2.changedInstance(onEdit) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            $dirty |= $composer2.changedInstance(onToggleActive) ? 2048 : 1024;
        }
        if (($changed & 24576) == 0) {
            $dirty |= $composer2.changedInstance(onAdhere) ? 16384 : 8192;
        }
        if ($composer2.shouldExecute(($dirty & 9363) != 9362, $dirty & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1674279866, $dirty, -1, "com.example.scheduleorganizer.ui.screen.ScheduleItem (HomeScreen.kt:396)");
            }
            Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(PaddingKt.m1049paddingVpY3zN4(Modifier.INSTANCE, Dp.m8150constructorimpl(16), Dp.m8150constructorimpl(8)), 0.0f, 1, null);
            RoundedCornerShape roundedCornerShapeM1378RoundedCornerShape0680j_4 = RoundedCornerShapeKt.m1378RoundedCornerShape0680j_4(Dp.m8150constructorimpl(16));
            CardColors cardColorsM2228cardColorsro_MJ88 = CardDefaults.INSTANCE.m2228cardColorsro_MJ88(MaterialTheme.INSTANCE.getColorScheme($composer2, MaterialTheme.$stable).getSurface(), 0L, 0L, 0L, $composer2, CardDefaults.$stable << 12, 14);
            $composer2 = $composer2;
            CardKt.Card(modifierFillMaxWidth$default, roundedCornerShapeM1378RoundedCornerShape0680j_4, cardColorsM2228cardColorsro_MJ88, null, null, ComposableLambdaKt.rememberComposableLambda(1708061880, true, new Function3() { // from class: com.example.scheduleorganizer.ui.screen.HomeScreenKt$$ExternalSyntheticLambda24
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return HomeScreenKt.ScheduleItem$lambda$0(schedule, onEdit, onDelete, onToggleActive, onAdhere, (ColumnScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, $composer2, 54), $composer2, 196614, 24);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.example.scheduleorganizer.ui.screen.HomeScreenKt$$ExternalSyntheticLambda25
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return HomeScreenKt.ScheduleItem$lambda$1(schedule, onDelete, onEdit, onToggleActive, onAdhere, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    static final Unit ScheduleItem$lambda$0(Schedule $schedule, Function0 $onEdit, Function0 $onDelete, Function1 $onToggleActive, Function0 $onAdhere, ColumnScope Card, Composer $composer, int $changed) {
        Function0<ComposeUiNode> function0;
        Function0<ComposeUiNode> function02;
        Function0<ComposeUiNode> function03;
        Function0<ComposeUiNode> function04;
        Function0<ComposeUiNode> function05;
        Function0<ComposeUiNode> function06;
        Intrinsics.checkNotNullParameter(Card, "$this$Card");
        ComposerKt.sourceInformation($composer, "C404@16788L2563:HomeScreen.kt#kl928v");
        if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1708061880, $changed, -1, "com.example.scheduleorganizer.ui.screen.ScheduleItem.<anonymous> (HomeScreen.kt:404)");
            }
            Modifier modifierM1048padding3ABfNKs = PaddingKt.m1048padding3ABfNKs(Modifier.INSTANCE, Dp.m8150constructorimpl(16));
            ComposerKt.sourceInformationMarkerStart($composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer, ((6 >> 3) & 14) | ((6 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer, modifierM1048padding3ABfNKs);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i = ((((6 << 3) & 112) << 6) & 896) | 6;
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
            int i3 = ((6 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -1594998251, "C405@16845L1694,447@18553L40,449@18607L734:HomeScreen.kt#kl928v");
            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
            ComposerKt.sourceInformationMarkerStart($composer, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
            Modifier modifier = Modifier.INSTANCE;
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically, $composer, ((384 >> 3) & 14) | ((384 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
            CompositionLocalMap currentCompositionLocalMap2 = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier($composer, modifier);
            Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
            int i4 = ((((384 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
            if (!($composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer.startReusableNode();
            if ($composer.getInserting()) {
                function02 = constructor2;
                $composer.createNode(function02);
            } else {
                function02 = constructor2;
                $composer.useNode();
            }
            Composer composerM4433constructorimpl2 = Updater.m4433constructorimpl($composer);
            Updater.m4441setimpl(composerM4433constructorimpl2, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4441setimpl(composerM4433constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4441setimpl(composerM4433constructorimpl2, Integer.valueOf(iHashCode2), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4439reconcileimpl(composerM4433constructorimpl2, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4441setimpl(composerM4433constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
            int i5 = (i4 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
            int i6 = ((384 >> 6) & 112) | 6;
            RowScope rowScope = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer, -454556569, "C406@16915L355,416@17288L40,418@17346L682,435@18046L40,437@18104L421:HomeScreen.kt#kl928v");
            Modifier modifierM1120width3ABfNKs = SizeKt.m1120width3ABfNKs(Modifier.INSTANCE, Dp.m8150constructorimpl(100));
            ComposerKt.sourceInformationMarkerStart($composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            MeasurePolicy measurePolicyColumnMeasurePolicy2 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer, ((6 >> 3) & 14) | ((6 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode3 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
            CompositionLocalMap currentCompositionLocalMap3 = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier($composer, modifierM1120width3ABfNKs);
            Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
            int i7 = ((((6 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
            if (!($composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer.startReusableNode();
            if ($composer.getInserting()) {
                function03 = constructor3;
                $composer.createNode(function03);
            } else {
                function03 = constructor3;
                $composer.useNode();
            }
            Composer composerM4433constructorimpl3 = Updater.m4433constructorimpl($composer);
            Updater.m4441setimpl(composerM4433constructorimpl3, measurePolicyColumnMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4441setimpl(composerM4433constructorimpl3, currentCompositionLocalMap3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4441setimpl(composerM4433constructorimpl3, Integer.valueOf(iHashCode3), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4439reconcileimpl(composerM4433constructorimpl3, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4441setimpl(composerM4433constructorimpl3, modifierMaterializeModifier3, ComposeUiNode.INSTANCE.getSetModifier());
            int i8 = (i7 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance2 = ColumnScopeInstance.INSTANCE;
            int i9 = ((6 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, 1101331658, "C407@16979L273:HomeScreen.kt#kl928v");
            TextKt.m3157TextNvy7gAk($schedule.getTime() + " WIB", null, 0L, null, TextUnitKt.getSp(18), null, FontWeight.INSTANCE.getBold(), null, 0L, null, null, 0L, TextOverflow.INSTANCE.m8061getEllipsisgIe3tQ8(), false, 1, 0, null, null, $composer, 1597440, 24960, 241582);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            SpacerKt.Spacer(SizeKt.m1120width3ABfNKs(Modifier.INSTANCE, Dp.m8150constructorimpl(12)), $composer, 6);
            Modifier modifierWeight$default = RowScope.weight$default(rowScope, Modifier.INSTANCE, 1.0f, false, 2, null);
            ComposerKt.sourceInformationMarkerStart($composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            MeasurePolicy measurePolicyColumnMeasurePolicy3 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer, ((0 >> 3) & 14) | ((0 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode4 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
            CompositionLocalMap currentCompositionLocalMap4 = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier4 = ComposedModifierKt.materializeModifier($composer, modifierWeight$default);
            Function0<ComposeUiNode> constructor4 = ComposeUiNode.INSTANCE.getConstructor();
            int i10 = ((((0 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
            if (!($composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer.startReusableNode();
            if ($composer.getInserting()) {
                function04 = constructor4;
                $composer.createNode(function04);
            } else {
                function04 = constructor4;
                $composer.useNode();
            }
            Composer composerM4433constructorimpl4 = Updater.m4433constructorimpl($composer);
            Updater.m4441setimpl(composerM4433constructorimpl4, measurePolicyColumnMeasurePolicy3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4441setimpl(composerM4433constructorimpl4, currentCompositionLocalMap4, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4441setimpl(composerM4433constructorimpl4, Integer.valueOf(iHashCode4), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4439reconcileimpl(composerM4433constructorimpl4, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4441setimpl(composerM4433constructorimpl4, modifierMaterializeModifier4, ComposeUiNode.INSTANCE.getSetModifier());
            int i11 = (i10 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance3 = ColumnScopeInstance.INSTANCE;
            int i12 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -455679447, "C419@17407L223,425@17651L40,429@17864L11,426@17712L298:HomeScreen.kt#kl928v");
            TextKt.m3157TextNvy7gAk($schedule.getTitle(), null, 0L, null, 0L, null, FontWeight.INSTANCE.getBold(), null, 0L, null, null, 0L, TextOverflow.INSTANCE.m8061getEllipsisgIe3tQ8(), false, 1, 0, null, null, $composer, 1572864, 24960, 241598);
            SpacerKt.Spacer(SizeKt.m1101height3ABfNKs(Modifier.INSTANCE, Dp.m8150constructorimpl(4)), $composer, 6);
            TextKt.m3157TextNvy7gAk("🏫 " + $schedule.getCategory(), null, MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnSurfaceVariant(), null, TextUnitKt.getSp(12), null, null, null, 0L, null, null, 0L, TextOverflow.INSTANCE.m8061getEllipsisgIe3tQ8(), false, 1, 0, null, null, $composer, 24576, 24960, 241642);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            SpacerKt.Spacer(SizeKt.m1120width3ABfNKs(Modifier.INSTANCE, Dp.m8150constructorimpl(12)), $composer, 6);
            Alignment.Horizontal end = Alignment.INSTANCE.getEnd();
            ComposerKt.sourceInformationMarkerStart($composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            Modifier modifier2 = Modifier.INSTANCE;
            MeasurePolicy measurePolicyColumnMeasurePolicy4 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), end, $composer, ((384 >> 3) & 14) | ((384 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode5 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
            CompositionLocalMap currentCompositionLocalMap5 = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier5 = ComposedModifierKt.materializeModifier($composer, modifier2);
            Function0<ComposeUiNode> constructor5 = ComposeUiNode.INSTANCE.getConstructor();
            int i13 = ((((384 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
            if (!($composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer.startReusableNode();
            if ($composer.getInserting()) {
                function05 = constructor5;
                $composer.createNode(function05);
            } else {
                function05 = constructor5;
                $composer.useNode();
            }
            Composer composerM4433constructorimpl5 = Updater.m4433constructorimpl($composer);
            Updater.m4441setimpl(composerM4433constructorimpl5, measurePolicyColumnMeasurePolicy4, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4441setimpl(composerM4433constructorimpl5, currentCompositionLocalMap5, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4441setimpl(composerM4433constructorimpl5, Integer.valueOf(iHashCode5), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4439reconcileimpl(composerM4433constructorimpl5, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4441setimpl(composerM4433constructorimpl5, modifierMaterializeModifier5, ComposeUiNode.INSTANCE.getSetModifier());
            int i14 = (i13 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance4 = ColumnScopeInstance.INSTANCE;
            int i15 = ((384 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -531526254, "C438@18170L137,441@18328L179:HomeScreen.kt#kl928v");
            IconButtonKt.IconButton($onEdit, null, false, null, null, null, ComposableSingletons$HomeScreenKt.INSTANCE.getLambda$274726430$app(), $composer, 1572864, 62);
            IconButtonKt.IconButton($onDelete, null, false, null, null, null, ComposableSingletons$HomeScreenKt.INSTANCE.getLambda$2064312341$app(), $composer, 1572864, 62);
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
            SpacerKt.Spacer(SizeKt.m1101height3ABfNKs(Modifier.INSTANCE, Dp.m8150constructorimpl(8)), $composer, 6);
            Alignment.Vertical centerVertically2 = Alignment.INSTANCE.getCenterVertically();
            ComposerKt.sourceInformationMarkerStart($composer, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
            Modifier modifier3 = Modifier.INSTANCE;
            MeasurePolicy measurePolicyRowMeasurePolicy2 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically2, $composer, ((384 >> 3) & 14) | ((384 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode6 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
            CompositionLocalMap currentCompositionLocalMap6 = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier6 = ComposedModifierKt.materializeModifier($composer, modifier3);
            Function0<ComposeUiNode> constructor6 = ComposeUiNode.INSTANCE.getConstructor();
            int i16 = ((((384 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
            if (!($composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer.startReusableNode();
            if ($composer.getInserting()) {
                function06 = constructor6;
                $composer.createNode(function06);
            } else {
                function06 = constructor6;
                $composer.useNode();
            }
            Composer composerM4433constructorimpl6 = Updater.m4433constructorimpl($composer);
            Updater.m4441setimpl(composerM4433constructorimpl6, measurePolicyRowMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4441setimpl(composerM4433constructorimpl6, currentCompositionLocalMap6, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4441setimpl(composerM4433constructorimpl6, Integer.valueOf(iHashCode6), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4439reconcileimpl(composerM4433constructorimpl6, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4441setimpl(composerM4433constructorimpl6, modifierMaterializeModifier6, ComposeUiNode.INSTANCE.getSetModifier());
            int i17 = (i16 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
            int i18 = ((384 >> 6) & 112) | 6;
            RowScope rowScope2 = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer, 377667792, "C453@18825L11,450@18677L278,457@18972L38,458@19027L127,462@19171L156:HomeScreen.kt#kl928v");
            TextKt.m3157TextNvy7gAk(formatScheduleDays($schedule.getDays()), null, MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnSurfaceVariant(), null, TextUnitKt.getSp(12), null, null, null, 0L, null, null, 0L, TextOverflow.INSTANCE.m8060getClipgIe3tQ8(), false, 2, 0, null, null, $composer, 24576, 24960, 241642);
            SpacerKt.Spacer(RowScope.weight$default(rowScope2, Modifier.INSTANCE, 1.0f, false, 2, null), $composer, 0);
            SwitchKt.Switch($schedule.isActive(), $onToggleActive, null, null, false, null, null, $composer, 0, 124);
            IconButtonKt.IconButton($onAdhere, null, false, null, null, null, ComposableSingletons$HomeScreenKt.INSTANCE.getLambda$370891687$app(), $composer, 1572864, 62);
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
        }
        return Unit.INSTANCE;
    }

    private static final String formatScheduleDays(String days) {
        String str = "Setiap hari";
        if (StringsKt.isBlank(days)) {
            return "Setiap hari";
        }
        Map dayLabels = MapsKt.mapOf(TuplesKt.to("1", "Sen"), TuplesKt.to("2", "Sel"), TuplesKt.to("3", "Rab"), TuplesKt.to("4", "Kam"), TuplesKt.to("5", "Jum"), TuplesKt.to("6", "Sab"), TuplesKt.to("7", "Min"));
        Iterable iterableSplit$default = StringsKt.split$default((CharSequence) days, new String[]{","}, false, 0, 6, (Object) null);
        Collection arrayList = new ArrayList();
        Iterator it = iterableSplit$default.iterator();
        while (it.hasNext()) {
            String str2 = (String) dayLabels.get(StringsKt.trim((CharSequence) it.next()).toString());
            if (str2 != null) {
                arrayList.add(str2);
            }
        }
        String strJoinToString$default = CollectionsKt.joinToString$default((List) arrayList, " ", null, null, 0, null, null, 62, null);
        if (!StringsKt.isBlank(strJoinToString$default)) {
            str = strJoinToString$default;
        }
        return str;
    }
}
