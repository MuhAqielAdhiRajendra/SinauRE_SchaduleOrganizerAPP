package com.example.scheduleorganizer.ui.screen;

import android.content.Context;
import androidx.compose.animation.AnimatedVisibilityKt;
import androidx.compose.animation.AnimatedVisibilityScope;
import androidx.compose.animation.EnterExitTransitionKt;
import androidx.compose.animation.EnterTransition;
import androidx.compose.animation.ExitTransition;
import androidx.compose.foundation.BackgroundKt;
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
import androidx.compose.material3.AndroidAlertDialog_androidKt;
import androidx.compose.material3.ButtonKt;
import androidx.compose.material3.CardColors;
import androidx.compose.material3.CardDefaults;
import androidx.compose.material3.CardKt;
import androidx.compose.material3.ChipKt;
import androidx.compose.material3.IconButtonKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.RadioButtonKt;
import androidx.compose.material3.SnackbarHostKt;
import androidx.compose.material3.SnackbarHostState;
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
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.example.scheduleorganizer.data.entity.Course;
import com.example.scheduleorganizer.data.entity.Task;
import com.example.scheduleorganizer.ui.MainViewModel;
import com.example.scheduleorganizer.ui.component.CommonComponentsKt;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: TugasScreen.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000<\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a\u0015\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0007¢\u0006\u0002\u0010\u0004\u001a\r\u0010\u0005\u001a\u00020\u0001H\u0007¢\u0006\u0002\u0010\u0006\u001a;\u0010\u0007\u001a\u00020\u00012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0014\u0010\r\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\f\u0012\u0004\u0012\u00020\u00010\u000eH\u0007¢\u0006\u0002\u0010\u000f\u001aI\u0010\u0010\u001a\u00020\u00012\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00010\u00162\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00010\u00162\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00010\u0016H\u0007¢\u0006\u0002\u0010\u0019¨\u0006\u001a²\u0006\u0010\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00120\tX\u008a\u0084\u0002²\u0006\u0010\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u008a\u0084\u0002²\u0006\f\u0010\u000b\u001a\u0004\u0018\u00010\fX\u008a\u0084\u0002²\u0006\f\u0010\u001c\u001a\u0004\u0018\u00010\u0012X\u008a\u008e\u0002²\u0006\f\u0010\u001d\u001a\u0004\u0018\u00010\u0012X\u008a\u008e\u0002"}, d2 = {"TugasScreen", "", "viewModel", "Lcom/example/scheduleorganizer/ui/MainViewModel;", "(Lcom/example/scheduleorganizer/ui/MainViewModel;Landroidx/compose/runtime/Composer;I)V", "HeaderSectionTugas", "(Landroidx/compose/runtime/Composer;I)V", "MataKuliahSectionTugas", "courses", "", "Lcom/example/scheduleorganizer/data/entity/Course;", "selectedCourseId", "", "onCourseSelected", "Lkotlin/Function1;", "(Ljava/util/List;Ljava/lang/Long;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;I)V", "TaskItem", "task", "Lcom/example/scheduleorganizer/data/entity/Task;", "courseName", "", "onToggle", "Lkotlin/Function0;", "onEdit", "onDelete", "(Lcom/example/scheduleorganizer/data/entity/Task;Ljava/lang/String;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "app", "tasks", "editingTask", "confirmDeleteTask"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class TugasScreenKt {
    static final Unit HeaderSectionTugas$lambda$1(int i, Composer composer, int i2) {
        HeaderSectionTugas(composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit MataKuliahSectionTugas$lambda$1(List list, Long l, Function1 function1, int i, Composer composer, int i2) {
        MataKuliahSectionTugas(list, l, function1, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit TaskItem$lambda$1(Task task, String str, Function0 function0, Function0 function02, Function0 function03, int i, Composer composer, int i2) {
        TaskItem(task, str, function0, function02, function03, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit TugasScreen$lambda$23(MainViewModel mainViewModel, int i, Composer composer, int i2) {
        TugasScreen(mainViewModel, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static final void TugasScreen(final MainViewModel viewModel, Composer $composer, final int $changed) {
        Composer $composer2;
        List listTugasScreen$lambda$0;
        MainViewModel mainViewModel;
        SnackbarHostState snackbarHostState;
        Composer $composer3;
        Object obj;
        Composer $composer4;
        float f;
        Map priorityLabel;
        int i;
        final MutableState confirmDeleteTask$delegate;
        SnapshotStateMap removingMap;
        Function0<ComposeUiNode> function0;
        int i2;
        Object arrayList;
        final MainViewModel viewModel2 = viewModel;
        Intrinsics.checkNotNullParameter(viewModel2, "viewModel");
        Composer $composer5 = $composer.startRestartGroup(-2125352456);
        ComposerKt.sourceInformation($composer5, "C(TugasScreen)N(viewModel)38@1651L16,39@1708L16,41@1811L7,42@1842L40,43@1911L32,44@1969L24,62@2632L47,63@2709L40,72@3032L11,73@3062L3518,69@2927L3653,157@6586L149:TugasScreen.kt#kl928v");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer5.changedInstance(viewModel2) ? 4 : 2;
        }
        int $dirty2 = $dirty;
        if ($composer5.shouldExecute(($dirty2 & 3) != 2, $dirty2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2125352456, $dirty2, -1, "com.example.scheduleorganizer.ui.screen.TugasScreen (TugasScreen.kt:37)");
            }
            State tasks$delegate = SnapshotStateKt.collectAsState(viewModel2.getAllTasks(), null, $composer5, 0, 1);
            final State courses$delegate = SnapshotStateKt.collectAsState(viewModel2.getAllCourses(), null, $composer5, 0, 1);
            final MutableState<Long> selectedCourseId = viewModel2.getSelectedCourseId();
            ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
            ComposerKt.sourceInformationMarkerStart($composer5, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = $composer5.consume(localContext);
            ComposerKt.sourceInformationMarkerEnd($composer5);
            final Context context = (Context) objConsume;
            ComposerKt.sourceInformationMarkerStart($composer5, 1940731392, "CC(remember):TugasScreen.kt#9igjgp");
            Object objRememberedValue = $composer5.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                Object objMutableStateOf$default = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                $composer5.updateRememberedValue(objMutableStateOf$default);
                objRememberedValue = objMutableStateOf$default;
            }
            final MutableState editingTask$delegate = (MutableState) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd($composer5);
            ComposerKt.sourceInformationMarkerStart($composer5, 1940733592, "CC(remember):TugasScreen.kt#9igjgp");
            Object objRememberedValue2 = $composer5.rememberedValue();
            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                Object snackbarHostState2 = new SnackbarHostState();
                $composer5.updateRememberedValue(snackbarHostState2);
                objRememberedValue2 = snackbarHostState2;
            }
            SnackbarHostState snackbarHostState3 = (SnackbarHostState) objRememberedValue2;
            ComposerKt.sourceInformationMarkerEnd($composer5);
            ComposerKt.sourceInformationMarkerStart($composer5, 773894976, "CC(rememberCoroutineScope)N(getContext)616@28039L68:Effects.kt#9igjgp");
            ComposerKt.sourceInformationMarkerStart($composer5, 683736516, "CC(remember):Effects.kt#9igjgp");
            Object objRememberedValue3 = $composer5.rememberedValue();
            if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                Object objCreateCompositionCoroutineScope = EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, $composer5);
                $composer5.updateRememberedValue(objCreateCompositionCoroutineScope);
                objRememberedValue3 = objCreateCompositionCoroutineScope;
            }
            final CoroutineScope coroutineScope = (CoroutineScope) objRememberedValue3;
            ComposerKt.sourceInformationMarkerEnd($composer5);
            ComposerKt.sourceInformationMarkerEnd($composer5);
            if (TugasScreen$lambda$2(selectedCourseId) == null) {
                listTugasScreen$lambda$0 = TugasScreen$lambda$0(tasks$delegate);
            } else {
                Iterable iterableTugasScreen$lambda$0 = TugasScreen$lambda$0(tasks$delegate);
                int i3 = 0;
                Collection arrayList2 = new ArrayList();
                for (Object obj2 : iterableTugasScreen$lambda$0) {
                    Iterable iterable = iterableTugasScreen$lambda$0;
                    int i4 = i3;
                    if (Intrinsics.areEqual(((Task) obj2).getCourseId(), TugasScreen$lambda$2(selectedCourseId))) {
                        arrayList2.add(obj2);
                    }
                    iterableTugasScreen$lambda$0 = iterable;
                    i3 = i4;
                }
                listTugasScreen$lambda$0 = (List) arrayList2;
            }
            List filteredTasks = listTugasScreen$lambda$0;
            Collection arrayList3 = new ArrayList();
            for (Object obj3 : filteredTasks) {
                if (!((Task) obj3).isCompleted()) {
                    arrayList3.add(obj3);
                }
            }
            final Comparator comparator = new Comparator() { // from class: com.example.scheduleorganizer.ui.screen.TugasScreenKt$TugasScreen$$inlined$compareByDescending$1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    return ComparisonsKt.compareValues(Integer.valueOf(((Task) t2).getPriority()), Integer.valueOf(((Task) t).getPriority()));
                }
            };
            Iterable iterableSortedWith = CollectionsKt.sortedWith((List) arrayList3, new Comparator() { // from class: com.example.scheduleorganizer.ui.screen.TugasScreenKt$TugasScreen$$inlined$thenBy$1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    int previousCompare = comparator.compare(t, t2);
                    return previousCompare != 0 ? previousCompare : ComparisonsKt.compareValues(Long.valueOf(((Task) t).getDueDate()), Long.valueOf(((Task) t2).getDueDate()));
                }
            });
            int i5 = 0;
            final Map linkedHashMap = new LinkedHashMap();
            for (Object obj4 : iterableSortedWith) {
                Integer numValueOf = Integer.valueOf(((Task) obj4).getPriority());
                Iterable iterable2 = iterableSortedWith;
                Object obj5 = linkedHashMap.get(numValueOf);
                if (obj5 == null) {
                    arrayList = new ArrayList();
                    i2 = i5;
                    linkedHashMap.put(numValueOf, arrayList);
                } else {
                    i2 = i5;
                    arrayList = obj5;
                }
                ((List) arrayList).add(obj4);
                iterableSortedWith = iterable2;
                i5 = i2;
            }
            Collection arrayList4 = new ArrayList();
            for (Object obj6 : filteredTasks) {
                if (((Task) obj6).isCompleted()) {
                    arrayList4.add(obj6);
                }
            }
            final Comparator comparator2 = new Comparator() { // from class: com.example.scheduleorganizer.ui.screen.TugasScreenKt$TugasScreen$$inlined$compareByDescending$2
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    return ComparisonsKt.compareValues(Integer.valueOf(((Task) t2).getPriority()), Integer.valueOf(((Task) t).getPriority()));
                }
            };
            final List sortedCompletedTasks = CollectionsKt.sortedWith((List) arrayList4, new Comparator() { // from class: com.example.scheduleorganizer.ui.screen.TugasScreenKt$TugasScreen$$inlined$thenBy$2
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    int previousCompare = comparator2.compare(t, t2);
                    return previousCompare != 0 ? previousCompare : ComparisonsKt.compareValues(Long.valueOf(((Task) t).getDueDate()), Long.valueOf(((Task) t2).getDueDate()));
                }
            });
            final Map priorityLabel2 = MapsKt.mapOf(TuplesKt.to(2, "Prioritas Tinggi"), TuplesKt.to(1, "Prioritas Sedang"), TuplesKt.to(0, "Prioritas Rendah"));
            ComposerKt.sourceInformationMarkerStart($composer5, 1940756679, "CC(remember):TugasScreen.kt#9igjgp");
            Object objRememberedValue4 = $composer5.rememberedValue();
            if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                Object objMutableStateMapOf = SnapshotStateKt.mutableStateMapOf();
                $composer5.updateRememberedValue(objMutableStateMapOf);
                objRememberedValue4 = objMutableStateMapOf;
            }
            final SnapshotStateMap removingMap2 = (SnapshotStateMap) objRememberedValue4;
            ComposerKt.sourceInformationMarkerEnd($composer5);
            ComposerKt.sourceInformationMarkerStart($composer5, 1940759136, "CC(remember):TugasScreen.kt#9igjgp");
            Object objRememberedValue5 = $composer5.rememberedValue();
            if (objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                Object objMutableStateOf$default2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                $composer5.updateRememberedValue(objMutableStateOf$default2);
                objRememberedValue5 = objMutableStateOf$default2;
            }
            final MutableState confirmDeleteTask$delegate2 = (MutableState) objRememberedValue5;
            ComposerKt.sourceInformationMarkerEnd($composer5);
            Task taskTugasScreen$lambda$4 = TugasScreen$lambda$4(editingTask$delegate);
            if (taskTugasScreen$lambda$4 == null) {
                $composer5.startReplaceGroup(34053104);
                $composer5.endReplaceGroup();
                mainViewModel = viewModel;
                $composer3 = $composer5;
                snackbarHostState = snackbarHostState3;
            } else {
                $composer5.startReplaceGroup(34053105);
                ComposerKt.sourceInformation($composer5, "*66@2853L22,66@2790L125");
                ComposerKt.sourceInformationMarkerStart($composer5, -1454140104, "CC(remember):TugasScreen.kt#9igjgp");
                Object objRememberedValue6 = $composer5.rememberedValue();
                if (objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                    Object obj7 = new Function0() { // from class: com.example.scheduleorganizer.ui.screen.TugasScreenKt$$ExternalSyntheticLambda2
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return TugasScreenKt.TugasScreen$lambda$19$0$0(editingTask$delegate);
                        }
                    };
                    $composer5.updateRememberedValue(obj7);
                    objRememberedValue6 = obj7;
                }
                ComposerKt.sourceInformationMarkerEnd($composer5);
                mainViewModel = viewModel;
                CommonComponentsKt.EditTaskDialog(mainViewModel, taskTugasScreen$lambda$4, (Function0) objRememberedValue6, snackbarHostState3, $composer5, ($dirty2 & 14) | 3456, 0);
                snackbarHostState = snackbarHostState3;
                $composer3 = $composer5;
                Unit unit = Unit.INSTANCE;
                $composer3.endReplaceGroup();
                Unit unit2 = Unit.INSTANCE;
            }
            Modifier modifierM286backgroundbw27NRU$default = BackgroundKt.m286backgroundbw27NRU$default(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), MaterialTheme.INSTANCE.getColorScheme($composer3, MaterialTheme.$stable).getBackground(), null, 2, null);
            ComposerKt.sourceInformationMarkerStart($composer3, 1940773910, "CC(remember):TugasScreen.kt#9igjgp");
            boolean zChanged = $composer3.changed(courses$delegate) | $composer3.changed(selectedCourseId) | $composer3.changedInstance(mainViewModel) | $composer3.changedInstance(linkedHashMap) | $composer3.changedInstance(sortedCompletedTasks);
            Composer composer = $composer3;
            Object objRememberedValue7 = composer.rememberedValue();
            if (zChanged || objRememberedValue7 == Composer.INSTANCE.getEmpty()) {
                $composer4 = $composer3;
                f = 0.0f;
                priorityLabel = null;
                i = 1;
                obj = new Function1() { // from class: com.example.scheduleorganizer.ui.screen.TugasScreenKt$$ExternalSyntheticLambda3
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj8) {
                        return TugasScreenKt.TugasScreen$lambda$20$0(linkedHashMap, sortedCompletedTasks, viewModel, courses$delegate, selectedCourseId, priorityLabel2, removingMap2, editingTask$delegate, confirmDeleteTask$delegate2, (LazyListScope) obj8);
                    }
                };
                confirmDeleteTask$delegate = confirmDeleteTask$delegate2;
                removingMap = removingMap2;
                composer.updateRememberedValue(obj);
            } else {
                $composer4 = $composer3;
                obj = objRememberedValue7;
                removingMap = removingMap2;
                f = 0.0f;
                priorityLabel = null;
                i = 1;
                confirmDeleteTask$delegate = confirmDeleteTask$delegate2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer4);
            Composer $composer6 = $composer4;
            LazyDslKt.LazyColumn(modifierM286backgroundbw27NRU$default, null, null, false, null, null, null, false, null, (Function1) obj, $composer6, 0, TypedValues.PositionType.TYPE_POSITION_TYPE);
            Modifier modifierFillMaxSize$default = SizeKt.fillMaxSize$default(Modifier.INSTANCE, f, i, priorityLabel);
            ComposerKt.sourceInformationMarkerStart($composer6, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
            ComposerKt.sourceInformationMarkerStart($composer6, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer6, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer6.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer6, modifierFillMaxSize$default);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i6 = ((((6 << 3) & 112) << 6) & 896) | 6;
            final SnapshotStateMap removingMap3 = removingMap;
            ComposerKt.sourceInformationMarkerStart($composer6, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
            if (!($composer6.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer6.startReusableNode();
            if ($composer6.getInserting()) {
                function0 = constructor;
                $composer6.createNode(function0);
            } else {
                function0 = constructor;
                $composer6.useNode();
            }
            Composer composerM4433constructorimpl = Updater.m4433constructorimpl($composer6);
            Updater.m4441setimpl(composerM4433constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4441setimpl(composerM4433constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4441setimpl(composerM4433constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4439reconcileimpl(composerM4433constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4441setimpl(composerM4433constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i7 = (i6 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer6, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
            int i8 = ((6 >> 6) & 112) | 6;
            BoxScope boxScope = BoxScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer6, -929906381, "C158@6635L94:TugasScreen.kt#kl928v");
            final SnackbarHostState snackbarHostState4 = snackbarHostState;
            SnackbarHostKt.SnackbarHost(snackbarHostState4, boxScope.align(Modifier.INSTANCE, Alignment.INSTANCE.getBottomCenter()), null, $composer6, 6, 4);
            ComposerKt.sourceInformationMarkerEnd($composer6);
            ComposerKt.sourceInformationMarkerEnd($composer6);
            $composer6.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer6);
            ComposerKt.sourceInformationMarkerEnd($composer6);
            ComposerKt.sourceInformationMarkerEnd($composer6);
            final Task taskTugasScreen$lambda$17 = TugasScreen$lambda$17(confirmDeleteTask$delegate);
            if (taskTugasScreen$lambda$17 == null) {
                $composer6.startReplaceGroup(38052135);
                $composer6.endReplaceGroup();
                viewModel2 = viewModel;
                $composer2 = $composer6;
            } else {
                $composer6.startReplaceGroup(38052136);
                ComposerKt.sourceInformation($composer6, "*163@6826L28,166@6995L1027,185@8052L102,165@6925L40,162@6782L1382");
                ComposerKt.sourceInformationMarkerStart($composer6, 1913691975, "CC(remember):TugasScreen.kt#9igjgp");
                Object objRememberedValue8 = $composer6.rememberedValue();
                if (objRememberedValue8 == Composer.INSTANCE.getEmpty()) {
                    Object obj8 = new Function0() { // from class: com.example.scheduleorganizer.ui.screen.TugasScreenKt$$ExternalSyntheticLambda4
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return TugasScreenKt.TugasScreen$lambda$22$0$0(confirmDeleteTask$delegate);
                        }
                    };
                    $composer6.updateRememberedValue(obj8);
                    objRememberedValue8 = obj8;
                }
                ComposerKt.sourceInformationMarkerEnd($composer6);
                viewModel2 = viewModel;
                $composer2 = $composer6;
                AndroidAlertDialog_androidKt.m2150AlertDialogOix01E0((Function0) objRememberedValue8, ComposableLambdaKt.rememberComposableLambda(-2058913565, true, new Function2() { // from class: com.example.scheduleorganizer.ui.screen.TugasScreenKt$$ExternalSyntheticLambda5
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj9, Object obj10) {
                        return TugasScreenKt.TugasScreen$lambda$22$1(coroutineScope, taskTugasScreen$lambda$17, context, viewModel, confirmDeleteTask$delegate, removingMap3, snackbarHostState4, (Composer) obj9, ((Integer) obj10).intValue());
                    }
                }, $composer6, 54), null, ComposableLambdaKt.rememberComposableLambda(732955425, true, new Function2() { // from class: com.example.scheduleorganizer.ui.screen.TugasScreenKt$$ExternalSyntheticLambda6
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj9, Object obj10) {
                        return TugasScreenKt.TugasScreen$lambda$22$2(confirmDeleteTask$delegate, (Composer) obj9, ((Integer) obj10).intValue());
                    }
                }, $composer6, 54), null, ComposableSingletons$TugasScreenKt.INSTANCE.m8739getLambda$770142881$app(), ComposableLambdaKt.rememberComposableLambda(-1521692034, true, new Function2() { // from class: com.example.scheduleorganizer.ui.screen.TugasScreenKt$$ExternalSyntheticLambda7
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj9, Object obj10) {
                        return TugasScreenKt.TugasScreen$lambda$22$3(taskTugasScreen$lambda$17, (Composer) obj9, ((Integer) obj10).intValue());
                    }
                }, $composer6, 54), null, 0L, 0L, 0L, 0L, 0.0f, null, $composer2, 1772598, 0, 16276);
                Unit unit3 = Unit.INSTANCE;
                $composer2.endReplaceGroup();
                Unit unit4 = Unit.INSTANCE;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2 = $composer5;
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.example.scheduleorganizer.ui.screen.TugasScreenKt$$ExternalSyntheticLambda8
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj9, Object obj10) {
                    return TugasScreenKt.TugasScreen$lambda$23(viewModel2, $changed, (Composer) obj9, ((Integer) obj10).intValue());
                }
            });
        }
    }

    private static final List<Task> TugasScreen$lambda$0(State<? extends List<Task>> state) {
        return (List) state.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List<Course> TugasScreen$lambda$1(State<? extends List<Course>> state) {
        return (List) state.getValue();
    }

    private static final Long TugasScreen$lambda$2(MutableState<Long> mutableState) {
        return mutableState.getValue();
    }

    private static final Task TugasScreen$lambda$4(MutableState<Task> mutableState) {
        return mutableState.getValue();
    }

    private static final Task TugasScreen$lambda$17(MutableState<Task> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit TugasScreen$lambda$19$0$0(MutableState $editingTask$delegate) {
        $editingTask$delegate.setValue(null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit TugasScreen$lambda$20$0(Map $sortedActiveTasks, final List $sortedCompletedTasks, final MainViewModel $viewModel, final State $courses$delegate, final MutableState $selectedCourseId$delegate, final Map $priorityLabel, final SnapshotStateMap $removingMap, final MutableState $editingTask$delegate, final MutableState $confirmDeleteTask$delegate, LazyListScope LazyColumn) {
        Intrinsics.checkNotNullParameter(LazyColumn, "$this$LazyColumn");
        LazyListScope.item$default(LazyColumn, null, null, ComposableSingletons$TugasScreenKt.INSTANCE.getLambda$758428227$app(), 3, null);
        final MainViewModel mainViewModel = $viewModel;
        final State state = $courses$delegate;
        LazyListScope.item$default(LazyColumn, null, null, ComposableLambdaKt.composableLambdaInstance(-1729411476, true, new Function3() { // from class: com.example.scheduleorganizer.ui.screen.TugasScreenKt$$ExternalSyntheticLambda17
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return TugasScreenKt.TugasScreen$lambda$20$0$0(mainViewModel, state, $selectedCourseId$delegate, (LazyItemScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        }), 3, null);
        LazyListScope.item$default(LazyColumn, null, null, ComposableSingletons$TugasScreenKt.INSTANCE.m8738getLambda$679542261$app(), 3, null);
        Iterator it = CollectionsKt.listOf((Object[]) new Integer[]{2, 1, 0}).iterator();
        while (it.hasNext()) {
            final int priority = ((Number) it.next()).intValue();
            List listEmptyList = (List) $sortedActiveTasks.get(Integer.valueOf(priority));
            if (listEmptyList == null) {
                listEmptyList = CollectionsKt.emptyList();
            }
            final List tasksForPriority = listEmptyList;
            if (tasksForPriority.isEmpty()) {
                mainViewModel = $viewModel;
                state = $courses$delegate;
            } else {
                LazyListScope.item$default(LazyColumn, null, null, ComposableLambdaKt.composableLambdaInstance(-914818010, true, new Function3() { // from class: com.example.scheduleorganizer.ui.screen.TugasScreenKt$$ExternalSyntheticLambda18
                    @Override // kotlin.jvm.functions.Function3
                    public final Object invoke(Object obj, Object obj2, Object obj3) {
                        return TugasScreenKt.TugasScreen$lambda$20$0$1($priorityLabel, priority, (LazyItemScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                    }
                }), 3, null);
                final Function1 function1 = new Function1() { // from class: com.example.scheduleorganizer.ui.screen.TugasScreenKt$TugasScreen$lambda$20$0$$inlined$items$default$1
                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Object invoke(Object p1) {
                        return invoke((Task) p1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Void invoke(Task task) {
                        return null;
                    }
                };
                final MainViewModel mainViewModel2 = mainViewModel;
                final State state2 = state;
                LazyColumn.items(tasksForPriority.size(), null, new Function1<Integer, Object>() { // from class: com.example.scheduleorganizer.ui.screen.TugasScreenKt$TugasScreen$lambda$20$0$$inlined$items$default$3
                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Object invoke(Integer num) {
                        return invoke(num.intValue());
                    }

                    public final Object invoke(int index) {
                        return function1.invoke(tasksForPriority.get(index));
                    }
                }, ComposableLambdaKt.composableLambdaInstance(802480018, true, new Function4<LazyItemScope, Integer, Composer, Integer, Unit>() { // from class: com.example.scheduleorganizer.ui.screen.TugasScreenKt$TugasScreen$lambda$20$0$$inlined$items$default$4
                    @Override // kotlin.jvm.functions.Function4
                    public /* bridge */ /* synthetic */ Unit invoke(LazyItemScope lazyItemScope, Integer num, Composer composer, Integer num2) {
                        invoke(lazyItemScope, num.intValue(), composer, num2.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(LazyItemScope $this$items, int it2, Composer $composer, int $changed) {
                        ComposerKt.sourceInformation($composer, "CN(it)178@8834L22:LazyDsl.kt#428nma");
                        int $dirty = $changed;
                        if (($changed & 6) == 0) {
                            $dirty |= $composer.changed($this$items) ? 4 : 2;
                        }
                        if (($changed & 48) == 0) {
                            $dirty |= $composer.changed(it2) ? 32 : 16;
                        }
                        if (!$composer.shouldExecute(($dirty & 147) != 146, $dirty & 1)) {
                            $composer.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(802480018, $dirty, -1, "androidx.compose.foundation.lazy.items.<anonymous> (LazyDsl.kt:178)");
                        }
                        int i = $dirty & 14;
                        final Task task = (Task) tasksForPriority.get(it2);
                        $composer.startReplaceGroup(1353231489);
                        ComposerKt.sourceInformation($composer, "CN(task)*107@4447L10,108@4540L10,109@4574L450,105@4302L722:TugasScreen.kt#kl928v");
                        boolean z = !Intrinsics.areEqual($removingMap.get(Long.valueOf(task.getId())), (Object) true);
                        EnterTransition enterTransitionFadeIn$default = EnterExitTransitionKt.fadeIn$default(null, 0.0f, 3, null);
                        ComposerKt.sourceInformationMarkerStart($composer, 1567679225, "CC(remember):TugasScreen.kt#9igjgp");
                        Object objRememberedValue = $composer.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            Object obj = (Function1) new Function1<Integer, Integer>() { // from class: com.example.scheduleorganizer.ui.screen.TugasScreenKt$TugasScreen$2$1$3$1$1
                                public final Integer invoke(int it3) {
                                    return Integer.valueOf(it3 / 4);
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
                        ComposerKt.sourceInformationMarkerStart($composer, 1567682201, "CC(remember):TugasScreen.kt#9igjgp");
                        Object objRememberedValue2 = $composer.rememberedValue();
                        if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                            Object obj2 = (Function1) new Function1<Integer, Integer>() { // from class: com.example.scheduleorganizer.ui.screen.TugasScreenKt$TugasScreen$2$1$3$2$1
                                public final Integer invoke(int it3) {
                                    return Integer.valueOf(it3 / 4);
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
                        final MainViewModel mainViewModel3 = mainViewModel2;
                        final State state3 = state2;
                        final MutableState mutableState = $editingTask$delegate;
                        final MutableState mutableState2 = $confirmDeleteTask$delegate;
                        AnimatedVisibilityKt.AnimatedVisibility(z, (Modifier) null, enterTransitionPlus, exitTransitionPlus, (String) null, ComposableLambdaKt.rememberComposableLambda(-62529145, true, new Function3<AnimatedVisibilityScope, Composer, Integer, Unit>() { // from class: com.example.scheduleorganizer.ui.screen.TugasScreenKt$TugasScreen$2$1$3$3
                            @Override // kotlin.jvm.functions.Function3
                            public /* bridge */ /* synthetic */ Unit invoke(AnimatedVisibilityScope animatedVisibilityScope, Composer composer, Integer num) {
                                invoke(animatedVisibilityScope, composer, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(AnimatedVisibilityScope AnimatedVisibility, Composer $composer2, int $changed2) {
                                Object next;
                                Task task2;
                                Intrinsics.checkNotNullParameter(AnimatedVisibility, "$this$AnimatedVisibility");
                                ComposerKt.sourceInformation($composer2, "C113@4778L68,114@4885L22,115@4948L28,110@4600L402:TugasScreen.kt#kl928v");
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-62529145, $changed2, -1, "com.example.scheduleorganizer.ui.screen.TugasScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous> (TugasScreen.kt:110)");
                                }
                                Task task3 = task;
                                List listTugasScreen$lambda$1 = TugasScreenKt.TugasScreen$lambda$1(state3);
                                Task task4 = task;
                                Iterator it3 = listTugasScreen$lambda$1.iterator();
                                while (true) {
                                    if (!it3.hasNext()) {
                                        next = null;
                                        break;
                                    }
                                    next = it3.next();
                                    long id = ((Course) next).getId();
                                    Long courseId = task4.getCourseId();
                                    if (courseId != null && id == courseId.longValue()) {
                                        break;
                                    }
                                }
                                Course course = (Course) next;
                                String name = course != null ? course.getName() : null;
                                ComposerKt.sourceInformationMarkerStart($composer2, 39040395, "CC(remember):TugasScreen.kt#9igjgp");
                                boolean zChangedInstance = $composer2.changedInstance(mainViewModel3) | $composer2.changed(task);
                                final MainViewModel mainViewModel4 = mainViewModel3;
                                final Task task5 = task;
                                Object objRememberedValue3 = $composer2.rememberedValue();
                                if (zChangedInstance || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                                    Object obj3 = (Function0) new Function0<Unit>() { // from class: com.example.scheduleorganizer.ui.screen.TugasScreenKt$TugasScreen$2$1$3$3$2$1
                                        @Override // kotlin.jvm.functions.Function0
                                        public /* bridge */ /* synthetic */ Unit invoke() {
                                            invoke2();
                                            return Unit.INSTANCE;
                                        }

                                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                        public final void invoke2() {
                                            mainViewModel4.updateTask(Task.copy$default(task5, 0L, null, null, 0L, !task5.isCompleted(), 0, 47, null));
                                        }
                                    };
                                    $composer2.updateRememberedValue(obj3);
                                    objRememberedValue3 = obj3;
                                }
                                Function0 function0 = (Function0) objRememberedValue3;
                                ComposerKt.sourceInformationMarkerEnd($composer2);
                                ComposerKt.sourceInformationMarkerStart($composer2, 39043773, "CC(remember):TugasScreen.kt#9igjgp");
                                boolean zChanged = $composer2.changed(task);
                                final Task task6 = task;
                                final MutableState<Task> mutableState3 = mutableState;
                                Object objRememberedValue4 = $composer2.rememberedValue();
                                if (zChanged || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                                    task2 = task3;
                                    Object obj4 = (Function0) new Function0<Unit>() { // from class: com.example.scheduleorganizer.ui.screen.TugasScreenKt$TugasScreen$2$1$3$3$3$1
                                        @Override // kotlin.jvm.functions.Function0
                                        public /* bridge */ /* synthetic */ Unit invoke() {
                                            invoke2();
                                            return Unit.INSTANCE;
                                        }

                                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                        public final void invoke2() {
                                            mutableState3.setValue(task6);
                                        }
                                    };
                                    $composer2.updateRememberedValue(obj4);
                                    objRememberedValue4 = obj4;
                                } else {
                                    task2 = task3;
                                }
                                Function0 function02 = (Function0) objRememberedValue4;
                                ComposerKt.sourceInformationMarkerEnd($composer2);
                                ComposerKt.sourceInformationMarkerStart($composer2, 39045795, "CC(remember):TugasScreen.kt#9igjgp");
                                boolean zChanged2 = $composer2.changed(task);
                                final Task task7 = task;
                                final MutableState<Task> mutableState4 = mutableState2;
                                Object objRememberedValue5 = $composer2.rememberedValue();
                                if (zChanged2 || objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                                    Object obj5 = (Function0) new Function0<Unit>() { // from class: com.example.scheduleorganizer.ui.screen.TugasScreenKt$TugasScreen$2$1$3$3$4$1
                                        @Override // kotlin.jvm.functions.Function0
                                        public /* bridge */ /* synthetic */ Unit invoke() {
                                            invoke2();
                                            return Unit.INSTANCE;
                                        }

                                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                        public final void invoke2() {
                                            mutableState4.setValue(task7);
                                        }
                                    };
                                    $composer2.updateRememberedValue(obj5);
                                    objRememberedValue5 = obj5;
                                }
                                ComposerKt.sourceInformationMarkerEnd($composer2);
                                TugasScreenKt.TaskItem(task2, name, function0, function02, (Function0) objRememberedValue5, $composer2, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }, $composer, 54), $composer, 200064, 18);
                        $composer.endReplaceGroup();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }));
                mainViewModel = $viewModel;
                state = $courses$delegate;
                it = it;
            }
        }
        LazyListScope.item$default(LazyColumn, null, null, ComposableSingletons$TugasScreenKt.INSTANCE.getLambda$370326954$app(), 3, null);
        if ($sortedCompletedTasks.isEmpty()) {
            LazyListScope.item$default(LazyColumn, null, null, ComposableSingletons$TugasScreenKt.INSTANCE.m8737getLambda$371147064$app(), 3, null);
        } else {
            final Function1 function12 = new Function1() { // from class: com.example.scheduleorganizer.ui.screen.TugasScreenKt$TugasScreen$lambda$20$0$$inlined$items$default$5
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Object invoke(Object p1) {
                    return invoke((Task) p1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Void invoke(Task task) {
                    return null;
                }
            };
            LazyColumn.items($sortedCompletedTasks.size(), null, new Function1<Integer, Object>() { // from class: com.example.scheduleorganizer.ui.screen.TugasScreenKt$TugasScreen$lambda$20$0$$inlined$items$default$7
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Object invoke(Integer num) {
                    return invoke(num.intValue());
                }

                public final Object invoke(int index) {
                    return function12.invoke($sortedCompletedTasks.get(index));
                }
            }, ComposableLambdaKt.composableLambdaInstance(802480018, true, new Function4<LazyItemScope, Integer, Composer, Integer, Unit>() { // from class: com.example.scheduleorganizer.ui.screen.TugasScreenKt$TugasScreen$lambda$20$0$$inlined$items$default$8
                @Override // kotlin.jvm.functions.Function4
                public /* bridge */ /* synthetic */ Unit invoke(LazyItemScope lazyItemScope, Integer num, Composer composer, Integer num2) {
                    invoke(lazyItemScope, num.intValue(), composer, num2.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(LazyItemScope $this$items, int it2, Composer $composer, int $changed) {
                    ComposerKt.sourceInformation($composer, "CN(it)178@8834L22:LazyDsl.kt#428nma");
                    int $dirty = $changed;
                    if (($changed & 6) == 0) {
                        $dirty |= $composer.changed($this$items) ? 4 : 2;
                    }
                    if (($changed & 48) == 0) {
                        $dirty |= $composer.changed(it2) ? 32 : 16;
                    }
                    if (!$composer.shouldExecute(($dirty & 147) != 146, $dirty & 1)) {
                        $composer.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(802480018, $dirty, -1, "androidx.compose.foundation.lazy.items.<anonymous> (LazyDsl.kt:178)");
                    }
                    int i = $dirty & 14;
                    final Task task = (Task) $sortedCompletedTasks.get(it2);
                    $composer.startReplaceGroup(1419404904);
                    ComposerKt.sourceInformation($composer, "CN(task)*142@5969L10,143@6062L10,144@6096L450,140@5824L722:TugasScreen.kt#kl928v");
                    boolean z = !Intrinsics.areEqual($removingMap.get(Long.valueOf(task.getId())), (Object) true);
                    EnterTransition enterTransitionFadeIn$default = EnterExitTransitionKt.fadeIn$default(null, 0.0f, 3, null);
                    ComposerKt.sourceInformationMarkerStart($composer, 184340530, "CC(remember):TugasScreen.kt#9igjgp");
                    Object objRememberedValue = $composer.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        Object obj = (Function1) new Function1<Integer, Integer>() { // from class: com.example.scheduleorganizer.ui.screen.TugasScreenKt$TugasScreen$2$1$4$1$1
                            public final Integer invoke(int it3) {
                                return Integer.valueOf(it3 / 4);
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
                    ComposerKt.sourceInformationMarkerStart($composer, 184343506, "CC(remember):TugasScreen.kt#9igjgp");
                    Object objRememberedValue2 = $composer.rememberedValue();
                    if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                        Object obj2 = (Function1) new Function1<Integer, Integer>() { // from class: com.example.scheduleorganizer.ui.screen.TugasScreenKt$TugasScreen$2$1$4$2$1
                            public final Integer invoke(int it3) {
                                return Integer.valueOf(it3 / 4);
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
                    final MainViewModel mainViewModel3 = $viewModel;
                    final State state3 = $courses$delegate;
                    final MutableState mutableState = $editingTask$delegate;
                    final MutableState mutableState2 = $confirmDeleteTask$delegate;
                    AnimatedVisibilityKt.AnimatedVisibility(z, (Modifier) null, enterTransitionPlus, exitTransitionPlus, (String) null, ComposableLambdaKt.rememberComposableLambda(1810386176, true, new Function3<AnimatedVisibilityScope, Composer, Integer, Unit>() { // from class: com.example.scheduleorganizer.ui.screen.TugasScreenKt$TugasScreen$2$1$4$3
                        @Override // kotlin.jvm.functions.Function3
                        public /* bridge */ /* synthetic */ Unit invoke(AnimatedVisibilityScope animatedVisibilityScope, Composer composer, Integer num) {
                            invoke(animatedVisibilityScope, composer, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(AnimatedVisibilityScope AnimatedVisibility, Composer $composer2, int $changed2) {
                            Object next;
                            Task task2;
                            Intrinsics.checkNotNullParameter(AnimatedVisibility, "$this$AnimatedVisibility");
                            ComposerKt.sourceInformation($composer2, "C148@6300L68,149@6407L22,150@6470L28,145@6122L402:TugasScreen.kt#kl928v");
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1810386176, $changed2, -1, "com.example.scheduleorganizer.ui.screen.TugasScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous> (TugasScreen.kt:145)");
                            }
                            Task task3 = task;
                            List listTugasScreen$lambda$1 = TugasScreenKt.TugasScreen$lambda$1(state3);
                            Task task4 = task;
                            Iterator it3 = listTugasScreen$lambda$1.iterator();
                            while (true) {
                                if (!it3.hasNext()) {
                                    next = null;
                                    break;
                                }
                                next = it3.next();
                                long id = ((Course) next).getId();
                                Long courseId = task4.getCourseId();
                                if (courseId != null && id == courseId.longValue()) {
                                    break;
                                }
                            }
                            Course course = (Course) next;
                            String name = course != null ? course.getName() : null;
                            ComposerKt.sourceInformationMarkerStart($composer2, 319366852, "CC(remember):TugasScreen.kt#9igjgp");
                            boolean zChangedInstance = $composer2.changedInstance(mainViewModel3) | $composer2.changed(task);
                            final MainViewModel mainViewModel4 = mainViewModel3;
                            final Task task5 = task;
                            Object objRememberedValue3 = $composer2.rememberedValue();
                            if (zChangedInstance || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                                Object obj3 = (Function0) new Function0<Unit>() { // from class: com.example.scheduleorganizer.ui.screen.TugasScreenKt$TugasScreen$2$1$4$3$2$1
                                    @Override // kotlin.jvm.functions.Function0
                                    public /* bridge */ /* synthetic */ Unit invoke() {
                                        invoke2();
                                        return Unit.INSTANCE;
                                    }

                                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2() {
                                        mainViewModel4.updateTask(Task.copy$default(task5, 0L, null, null, 0L, !task5.isCompleted(), 0, 47, null));
                                    }
                                };
                                $composer2.updateRememberedValue(obj3);
                                objRememberedValue3 = obj3;
                            }
                            Function0 function0 = (Function0) objRememberedValue3;
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            ComposerKt.sourceInformationMarkerStart($composer2, 319370230, "CC(remember):TugasScreen.kt#9igjgp");
                            boolean zChanged = $composer2.changed(task);
                            final Task task6 = task;
                            final MutableState<Task> mutableState3 = mutableState;
                            Object objRememberedValue4 = $composer2.rememberedValue();
                            if (zChanged || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                                task2 = task3;
                                Object obj4 = (Function0) new Function0<Unit>() { // from class: com.example.scheduleorganizer.ui.screen.TugasScreenKt$TugasScreen$2$1$4$3$3$1
                                    @Override // kotlin.jvm.functions.Function0
                                    public /* bridge */ /* synthetic */ Unit invoke() {
                                        invoke2();
                                        return Unit.INSTANCE;
                                    }

                                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2() {
                                        mutableState3.setValue(task6);
                                    }
                                };
                                $composer2.updateRememberedValue(obj4);
                                objRememberedValue4 = obj4;
                            } else {
                                task2 = task3;
                            }
                            Function0 function02 = (Function0) objRememberedValue4;
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            ComposerKt.sourceInformationMarkerStart($composer2, 319372252, "CC(remember):TugasScreen.kt#9igjgp");
                            boolean zChanged2 = $composer2.changed(task);
                            final Task task7 = task;
                            final MutableState<Task> mutableState4 = mutableState2;
                            Object objRememberedValue5 = $composer2.rememberedValue();
                            if (zChanged2 || objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                                Object obj5 = (Function0) new Function0<Unit>() { // from class: com.example.scheduleorganizer.ui.screen.TugasScreenKt$TugasScreen$2$1$4$3$4$1
                                    @Override // kotlin.jvm.functions.Function0
                                    public /* bridge */ /* synthetic */ Unit invoke() {
                                        invoke2();
                                        return Unit.INSTANCE;
                                    }

                                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2() {
                                        mutableState4.setValue(task7);
                                    }
                                };
                                $composer2.updateRememberedValue(obj5);
                                objRememberedValue5 = obj5;
                            }
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            TugasScreenKt.TaskItem(task2, name, function0, function02, (Function0) objRememberedValue5, $composer2, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, $composer, 54), $composer, 200064, 18);
                    $composer.endReplaceGroup();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }));
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit TugasScreen$lambda$20$0$0(final MainViewModel $viewModel, State $courses$delegate, MutableState $selectedCourseId$delegate, LazyItemScope item, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(item, "$this$item");
        ComposerKt.sourceInformation($composer, "C81@3296L41,78@3149L202:TugasScreen.kt#kl928v");
        if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1729411476, $changed, -1, "com.example.scheduleorganizer.ui.screen.TugasScreen.<anonymous>.<anonymous>.<anonymous> (TugasScreen.kt:78)");
            }
            List<Course> listTugasScreen$lambda$1 = TugasScreen$lambda$1($courses$delegate);
            Long lTugasScreen$lambda$2 = TugasScreen$lambda$2($selectedCourseId$delegate);
            ComposerKt.sourceInformationMarkerStart($composer, 187920309, "CC(remember):TugasScreen.kt#9igjgp");
            boolean zChangedInstance = $composer.changedInstance($viewModel);
            Object objRememberedValue = $composer.rememberedValue();
            if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                Object obj = new Function1() { // from class: com.example.scheduleorganizer.ui.screen.TugasScreenKt$$ExternalSyntheticLambda16
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return TugasScreenKt.TugasScreen$lambda$20$0$0$0$0($viewModel, (Long) obj2);
                    }
                };
                $composer.updateRememberedValue(obj);
                objRememberedValue = obj;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            MataKuliahSectionTugas(listTugasScreen$lambda$1, lTugasScreen$lambda$2, (Function1) objRememberedValue, $composer, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit TugasScreen$lambda$20$0$0$0$0(MainViewModel $viewModel, Long it) {
        $viewModel.getSelectedCourseId().setValue(it);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit TugasScreen$lambda$20$0$1(Map $priorityLabel, int $priority, LazyItemScope item, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(item, "$this$item");
        ComposerKt.sourceInformation($composer, "C98@3951L10,96@3828L320:TugasScreen.kt#kl928v");
        if ($composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-914818010, $changed, -1, "com.example.scheduleorganizer.ui.screen.TugasScreen.<anonymous>.<anonymous>.<anonymous> (TugasScreen.kt:96)");
            }
            String str = (String) $priorityLabel.get(Integer.valueOf($priority));
            if (str == null) {
                str = "Prioritas";
            }
            TextKt.m3157TextNvy7gAk(str, PaddingKt.m1052paddingqDBjuR0$default(Modifier.INSTANCE, Dp.m8150constructorimpl(16), Dp.m8150constructorimpl(12), 0.0f, Dp.m8150constructorimpl(4), 4, null), 0L, null, 0L, null, FontWeight.INSTANCE.getSemiBold(), null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getTitleSmall(), $composer, 1572912, 0, 131004);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit TugasScreen$lambda$22$0$0(MutableState $confirmDeleteTask$delegate) {
        $confirmDeleteTask$delegate.setValue(null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit TugasScreen$lambda$22$3(Task $task, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C165@6927L36:TugasScreen.kt#kl928v");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1521692034, $changed, -1, "com.example.scheduleorganizer.ui.screen.TugasScreen.<anonymous>.<anonymous> (TugasScreen.kt:165)");
            }
            TextKt.m3157TextNvy7gAk("Hapus tugas '" + $task.getTitle() + "'?", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 0, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit TugasScreen$lambda$22$1(final CoroutineScope $coroutineScope, final Task $task, final Context $context, final MainViewModel $viewModel, final MutableState $confirmDeleteTask$delegate, final SnapshotStateMap $removingMap, final SnackbarHostState $snackbarHostState, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C167@7030L959,167@7013L995:TugasScreen.kt#kl928v");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2058913565, $changed, -1, "com.example.scheduleorganizer.ui.screen.TugasScreen.<anonymous>.<anonymous> (TugasScreen.kt:167)");
            }
            ComposerKt.sourceInformationMarkerStart($composer, 1363989570, "CC(remember):TugasScreen.kt#9igjgp");
            boolean zChangedInstance = $composer.changedInstance($coroutineScope) | $composer.changed($task) | $composer.changedInstance($context) | $composer.changedInstance($viewModel);
            Object objRememberedValue = $composer.rememberedValue();
            if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                Object obj = new Function0() { // from class: com.example.scheduleorganizer.ui.screen.TugasScreenKt$$ExternalSyntheticLambda15
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return TugasScreenKt.TugasScreen$lambda$22$1$0$0($coroutineScope, $confirmDeleteTask$delegate, $removingMap, $task, $context, $viewModel, $snackbarHostState);
                    }
                };
                $composer.updateRememberedValue(obj);
                objRememberedValue = obj;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            ButtonKt.Button((Function0) objRememberedValue, null, false, null, null, null, null, null, null, ComposableSingletons$TugasScreenKt.INSTANCE.m8734getLambda$1315513613$app(), $composer, 805306368, TypedValues.PositionType.TYPE_POSITION_TYPE);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit TugasScreen$lambda$22$1$0$0(CoroutineScope $coroutineScope, MutableState $confirmDeleteTask$delegate, SnapshotStateMap $removingMap, Task $task, Context $context, MainViewModel $viewModel, SnackbarHostState $snackbarHostState) {
        $confirmDeleteTask$delegate.setValue(null);
        BuildersKt__Builders_commonKt.launch$default($coroutineScope, null, null, new TugasScreenKt$TugasScreen$4$2$1$1$1($removingMap, $task, $context, $viewModel, $snackbarHostState, null), 3, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit TugasScreen$lambda$22$2(final MutableState $confirmDeleteTask$delegate, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C186@8091L28,186@8070L70:TugasScreen.kt#kl928v");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(732955425, $changed, -1, "com.example.scheduleorganizer.ui.screen.TugasScreen.<anonymous>.<anonymous> (TugasScreen.kt:186)");
            }
            ComposerKt.sourceInformationMarkerStart($composer, -4471843, "CC(remember):TugasScreen.kt#9igjgp");
            Object objRememberedValue = $composer.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                Object obj = new Function0() { // from class: com.example.scheduleorganizer.ui.screen.TugasScreenKt$$ExternalSyntheticLambda14
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return TugasScreenKt.TugasScreen$lambda$22$2$0$0($confirmDeleteTask$delegate);
                    }
                };
                $composer.updateRememberedValue(obj);
                objRememberedValue = obj;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            ButtonKt.TextButton((Function0) objRememberedValue, null, false, null, null, null, null, null, null, ComposableSingletons$TugasScreenKt.INSTANCE.m8735getLambda$1707175868$app(), $composer, 805306374, TypedValues.PositionType.TYPE_POSITION_TYPE);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit TugasScreen$lambda$22$2$0$0(MutableState $confirmDeleteTask$delegate) {
        $confirmDeleteTask$delegate.setValue(null);
        return Unit.INSTANCE;
    }

    public static final void HeaderSectionTugas(Composer $composer, final int $changed) {
        Composer $composer2;
        Function0<ComposeUiNode> function0;
        Composer $composer3 = $composer.startRestartGroup(-236262473);
        ComposerKt.sourceInformation($composer3, "C(HeaderSectionTugas)201@8454L11,202@8513L11,194@8217L648:TugasScreen.kt#kl928v");
        if (!$composer3.shouldExecute($changed != 0, $changed & 1)) {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-236262473, $changed, -1, "com.example.scheduleorganizer.ui.screen.HeaderSectionTugas (TugasScreen.kt:193)");
            }
            Modifier modifierM1101height3ABfNKs = SizeKt.m1101height3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.m8150constructorimpl(150));
            Brush.Companion companion = Brush.INSTANCE;
            long primary = MaterialTheme.INSTANCE.getColorScheme($composer3, MaterialTheme.$stable).getPrimary();
            Modifier modifierM1048padding3ABfNKs = PaddingKt.m1048padding3ABfNKs(BackgroundKt.background$default(modifierM1101height3ABfNKs, Brush.Companion.m5268verticalGradient8A3gB4$default(companion, CollectionsKt.listOf((Object[]) new Color[]{Color.m5303boximpl(MaterialTheme.INSTANCE.getColorScheme($composer3, MaterialTheme.$stable).getPrimary()), Color.m5303boximpl(Color.m5311copywmQWz5c(primary, (14 & 1) != 0 ? Color.m5315getAlphaimpl(primary) : 0.8f, (14 & 2) != 0 ? Color.m5319getRedimpl(primary) : 0.0f, (14 & 4) != 0 ? Color.m5318getGreenimpl(primary) : 0.0f, (14 & 8) != 0 ? Color.m5316getBlueimpl(primary) : 0.0f))}), 0.0f, 0.0f, 0, 14, (Object) null), null, 0.0f, 6, null), Dp.m8150constructorimpl(24));
            ComposerKt.sourceInformationMarkerStart($composer3, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
            ComposerKt.sourceInformationMarkerStart($composer3, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer3, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer3.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer3, modifierM1048padding3ABfNKs);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            $composer2 = $composer3;
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
            int i3 = ((0 >> 6) & 112) | 6;
            BoxScope boxScope = BoxScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer3, -181484159, "C208@8650L209:TugasScreen.kt#kl928v");
            TextKt.m3157TextNvy7gAk("Tugas", boxScope.align(Modifier.INSTANCE, Alignment.INSTANCE.getBottomStart()), Color.INSTANCE.m5350getWhite0d7_KjU(), null, TextUnitKt.getSp(28), null, FontWeight.INSTANCE.getBold(), null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer3, 1597830, 0, 262056);
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
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.example.scheduleorganizer.ui.screen.TugasScreenKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TugasScreenKt.HeaderSectionTugas$lambda$1($changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final void MataKuliahSectionTugas(final List<Course> courses, final Long selectedCourseId, final Function1<? super Long, Unit> onCourseSelected, Composer $composer, final int $changed) {
        Intrinsics.checkNotNullParameter(courses, "courses");
        Intrinsics.checkNotNullParameter(onCourseSelected, "onCourseSelected");
        Composer $composer2 = $composer.startRestartGroup(-19934190);
        ComposerKt.sourceInformation($composer2, "C(MataKuliahSectionTugas)N(courses,selectedCourseId,onCourseSelected)223@9160L567,220@9039L688:TugasScreen.kt#kl928v");
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
        if ($composer2.shouldExecute(($dirty & 147) != 146, $dirty & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-19934190, $dirty, -1, "com.example.scheduleorganizer.ui.screen.MataKuliahSectionTugas (TugasScreen.kt:219)");
            }
            PaddingValues paddingValuesM1041PaddingValues0680j_4 = PaddingKt.m1041PaddingValues0680j_4(Dp.m8150constructorimpl(16));
            Arrangement.HorizontalOrVertical horizontalOrVerticalM740spacedBy0680j_4 = Arrangement.INSTANCE.m740spacedBy0680j_4(Dp.m8150constructorimpl(8));
            ComposerKt.sourceInformationMarkerStart($composer2, -1976877911, "CC(remember):TugasScreen.kt#9igjgp");
            boolean zChangedInstance = (($dirty & 112) == 32) | (($dirty & 896) == 256) | $composer2.changedInstance(courses);
            Object objRememberedValue = $composer2.rememberedValue();
            if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                Object obj = new Function1() { // from class: com.example.scheduleorganizer.ui.screen.TugasScreenKt$$ExternalSyntheticLambda11
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return TugasScreenKt.MataKuliahSectionTugas$lambda$0$0(courses, selectedCourseId, onCourseSelected, (LazyListScope) obj2);
                    }
                };
                $composer2.updateRememberedValue(obj);
                objRememberedValue = obj;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            LazyDslKt.LazyRow(null, null, paddingValuesM1041PaddingValues0680j_4, false, horizontalOrVerticalM740spacedBy0680j_4, null, null, false, null, (Function1) objRememberedValue, $composer2, 24960, 491);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.example.scheduleorganizer.ui.screen.TugasScreenKt$$ExternalSyntheticLambda12
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    return TugasScreenKt.MataKuliahSectionTugas$lambda$1(courses, selectedCourseId, onCourseSelected, $changed, (Composer) obj2, ((Integer) obj3).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MataKuliahSectionTugas$lambda$0$0(final List $courses, final Long $selectedCourseId, final Function1 $onCourseSelected, LazyListScope LazyRow) {
        Intrinsics.checkNotNullParameter(LazyRow, "$this$LazyRow");
        LazyListScope.item$default(LazyRow, null, null, ComposableLambdaKt.composableLambdaInstance(688159695, true, new Function3() { // from class: com.example.scheduleorganizer.ui.screen.TugasScreenKt$$ExternalSyntheticLambda13
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return TugasScreenKt.MataKuliahSectionTugas$lambda$0$0$0($selectedCourseId, $onCourseSelected, (LazyItemScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        }), 3, null);
        final Function1 function1 = new Function1() { // from class: com.example.scheduleorganizer.ui.screen.TugasScreenKt$MataKuliahSectionTugas$lambda$0$0$$inlined$items$default$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object p1) {
                return invoke((Course) p1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Void invoke(Course course) {
                return null;
            }
        };
        LazyRow.items($courses.size(), null, new Function1<Integer, Object>() { // from class: com.example.scheduleorganizer.ui.screen.TugasScreenKt$MataKuliahSectionTugas$lambda$0$0$$inlined$items$default$3
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Integer num) {
                return invoke(num.intValue());
            }

            public final Object invoke(int index) {
                return function1.invoke($courses.get(index));
            }
        }, ComposableLambdaKt.composableLambdaInstance(802480018, true, new Function4<LazyItemScope, Integer, Composer, Integer, Unit>() { // from class: com.example.scheduleorganizer.ui.screen.TugasScreenKt$MataKuliahSectionTugas$lambda$0$0$$inlined$items$default$4
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
                $composer.startReplaceGroup(983536754);
                ComposerKt.sourceInformation($composer, "CN(course)*235@9568L31,236@9625L21,233@9472L239:TugasScreen.kt#kl928v");
                Long l = $selectedCourseId;
                boolean z = l != null && l.longValue() == course.getId();
                ComposerKt.sourceInformationMarkerStart($composer, 1140108513, "CC(remember):TugasScreen.kt#9igjgp");
                boolean zChanged = $composer.changed($onCourseSelected) | ((((i & 112) ^ 48) > 32 && $composer.changed(course)) || (i & 48) == 32);
                Object objRememberedValue = $composer.rememberedValue();
                if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    final Function1 function12 = $onCourseSelected;
                    Object obj = (Function0) new Function0<Unit>() { // from class: com.example.scheduleorganizer.ui.screen.TugasScreenKt$MataKuliahSectionTugas$1$1$2$1$1
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
                ChipKt.FilterChip(z, (Function0) objRememberedValue, ComposableLambdaKt.rememberComposableLambda(-182187915, true, new Function2<Composer, Integer, Unit>() { // from class: com.example.scheduleorganizer.ui.screen.TugasScreenKt$MataKuliahSectionTugas$1$1$2$2
                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                        invoke(composer, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer $composer2, int $changed2) {
                        ComposerKt.sourceInformation($composer2, "C236@9627L17:TugasScreen.kt#kl928v");
                        if (!$composer2.shouldExecute(($changed2 & 3) != 2, $changed2 & 1)) {
                            $composer2.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-182187915, $changed2, -1, "com.example.scheduleorganizer.ui.screen.MataKuliahSectionTugas.<anonymous>.<anonymous>.<anonymous>.<anonymous> (TugasScreen.kt:236)");
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
    public static final Unit MataKuliahSectionTugas$lambda$0$0$0(Long $selectedCourseId, final Function1 $onCourseSelected, LazyItemScope item, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(item, "$this$item");
        ComposerKt.sourceInformation($composer, "C227@9280L26,225@9189L225:TugasScreen.kt#kl928v");
        if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(688159695, $changed, -1, "com.example.scheduleorganizer.ui.screen.MataKuliahSectionTugas.<anonymous>.<anonymous>.<anonymous> (TugasScreen.kt:225)");
            }
            boolean z = $selectedCourseId == null;
            ComposerKt.sourceInformationMarkerStart($composer, -103493303, "CC(remember):TugasScreen.kt#9igjgp");
            boolean zChanged = $composer.changed($onCourseSelected);
            Object objRememberedValue = $composer.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                Object obj = new Function0() { // from class: com.example.scheduleorganizer.ui.screen.TugasScreenKt$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return TugasScreenKt.MataKuliahSectionTugas$lambda$0$0$0$0$0($onCourseSelected);
                    }
                };
                $composer.updateRememberedValue(obj);
                objRememberedValue = obj;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            ChipKt.FilterChip(z, (Function0) objRememberedValue, ComposableSingletons$TugasScreenKt.INSTANCE.m8736getLambda$1932829444$app(), null, false, null, null, RoundedCornerShapeKt.m1378RoundedCornerShape0680j_4(Dp.m8150constructorimpl(20)), null, null, null, null, $composer, 384, 0, 3960);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MataKuliahSectionTugas$lambda$0$0$0$0$0(Function1 $onCourseSelected) {
        $onCourseSelected.invoke(null);
        return Unit.INSTANCE;
    }

    public static final void TaskItem(final Task task, final String courseName, final Function0<Unit> onToggle, final Function0<Unit> onEdit, final Function0<Unit> onDelete, Composer $composer, final int $changed) {
        Intrinsics.checkNotNullParameter(task, "task");
        Intrinsics.checkNotNullParameter(onToggle, "onToggle");
        Intrinsics.checkNotNullParameter(onEdit, "onEdit");
        Intrinsics.checkNotNullParameter(onDelete, "onDelete");
        Composer $composer2 = $composer.startRestartGroup(-1441175177);
        ComposerKt.sourceInformation($composer2, "C(TaskItem)N(task,courseName,onToggle,onEdit,onDelete)256@10117L11,256@10075L62,257@10144L1745,251@9881L2008:TugasScreen.kt#kl928v");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(task) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(courseName) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer2.changedInstance(onToggle) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            $dirty |= $composer2.changedInstance(onEdit) ? 2048 : 1024;
        }
        if (($changed & 24576) == 0) {
            $dirty |= $composer2.changedInstance(onDelete) ? 16384 : 8192;
        }
        if ($composer2.shouldExecute(($dirty & 9363) != 9362, $dirty & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1441175177, $dirty, -1, "com.example.scheduleorganizer.ui.screen.TaskItem (TugasScreen.kt:250)");
            }
            Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(PaddingKt.m1049paddingVpY3zN4(Modifier.INSTANCE, Dp.m8150constructorimpl(16), Dp.m8150constructorimpl(8)), 0.0f, 1, null);
            RoundedCornerShape roundedCornerShapeM1378RoundedCornerShape0680j_4 = RoundedCornerShapeKt.m1378RoundedCornerShape0680j_4(Dp.m8150constructorimpl(16));
            CardColors cardColorsM2228cardColorsro_MJ88 = CardDefaults.INSTANCE.m2228cardColorsro_MJ88(MaterialTheme.INSTANCE.getColorScheme($composer2, MaterialTheme.$stable).getSurface(), 0L, 0L, 0L, $composer2, CardDefaults.$stable << 12, 14);
            $composer2 = $composer2;
            CardKt.Card(modifierFillMaxWidth$default, roundedCornerShapeM1378RoundedCornerShape0680j_4, cardColorsM2228cardColorsro_MJ88, null, null, ComposableLambdaKt.rememberComposableLambda(-323067799, true, new Function3() { // from class: com.example.scheduleorganizer.ui.screen.TugasScreenKt$$ExternalSyntheticLambda9
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return TugasScreenKt.TaskItem$lambda$0(task, onToggle, onEdit, onDelete, courseName, (ColumnScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
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
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.example.scheduleorganizer.ui.screen.TugasScreenKt$$ExternalSyntheticLambda10
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TugasScreenKt.TaskItem$lambda$1(task, courseName, onToggle, onEdit, onDelete, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    static final Unit TaskItem$lambda$0(Task $task, Function0 $onToggle, Function0 $onEdit, Function0 $onDelete, String $courseName, ColumnScope Card, Composer $composer, int $changed) {
        Function0<ComposeUiNode> function0;
        Function0<ComposeUiNode> function02;
        Function0<ComposeUiNode> function03;
        Function0<ComposeUiNode> function04;
        Intrinsics.checkNotNullParameter(Card, "$this$Card");
        ComposerKt.sourceInformation($composer, "C258@10154L1729:TugasScreen.kt#kl928v");
        if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-323067799, $changed, -1, "com.example.scheduleorganizer.ui.screen.TaskItem.<anonymous> (TugasScreen.kt:258)");
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
            ComposerKt.sourceInformationMarkerStart($composer, 235419655, "C262@10290L60,263@10363L40,264@10416L1168,281@11597L120,284@11730L143:TugasScreen.kt#kl928v");
            RadioButtonKt.RadioButton($task.isCompleted(), $onToggle, null, false, null, null, $composer, 0, 60);
            SpacerKt.Spacer(SizeKt.m1120width3ABfNKs(Modifier.INSTANCE, Dp.m8150constructorimpl(12)), $composer, 6);
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
            ComposerKt.sourceInformationMarkerStart($composer, -894083849, "C265@10473L53,266@10543L40,267@10600L726,278@11343L40,279@11541L11,279@11400L170:TugasScreen.kt#kl928v");
            TextKt.m3157TextNvy7gAk($task.getTitle(), null, 0L, null, 0L, null, FontWeight.INSTANCE.getBold(), null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 1572864, 0, 262078);
            SpacerKt.Spacer(SizeKt.m1101height3ABfNKs(Modifier.INSTANCE, Dp.m8150constructorimpl(4)), $composer, 6);
            Alignment.Vertical centerVertically2 = Alignment.INSTANCE.getCenterVertically();
            ComposerKt.sourceInformationMarkerStart($composer, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
            Modifier modifier = Modifier.INSTANCE;
            MeasurePolicy measurePolicyRowMeasurePolicy2 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically2, $composer, ((384 >> 3) & 14) | ((384 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode3 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
            CompositionLocalMap currentCompositionLocalMap3 = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier($composer, modifier);
            Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
            int i7 = ((((384 << 3) & 112) << 6) & 896) | 6;
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
            Updater.m4441setimpl(composerM4433constructorimpl3, measurePolicyRowMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4441setimpl(composerM4433constructorimpl3, currentCompositionLocalMap3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4441setimpl(composerM4433constructorimpl3, Integer.valueOf(iHashCode3), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4439reconcileimpl(composerM4433constructorimpl3, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4441setimpl(composerM4433constructorimpl3, modifierMaterializeModifier3, ComposeUiNode.INSTANCE.getSetModifier());
            int i8 = (i7 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            int i9 = ((384 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -355240830, "C270@10777L11,268@10674L371,275@11066L39,276@11279L11,276@11126L182:TugasScreen.kt#kl928v");
            Modifier modifierM1049paddingVpY3zN4 = PaddingKt.m1049paddingVpY3zN4(BackgroundKt.m285backgroundbw27NRU(Modifier.INSTANCE, MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getSecondary(), RoundedCornerShapeKt.m1378RoundedCornerShape0680j_4(Dp.m8150constructorimpl(4))), Dp.m8150constructorimpl(8), Dp.m8150constructorimpl(2));
            ComposerKt.sourceInformationMarkerStart($composer, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
            ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
            int iHashCode4 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
            CompositionLocalMap currentCompositionLocalMap4 = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier4 = ComposedModifierKt.materializeModifier($composer, modifierM1049paddingVpY3zN4);
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
            Updater.m4441setimpl(composerM4433constructorimpl4, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4441setimpl(composerM4433constructorimpl4, currentCompositionLocalMap4, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4441setimpl(composerM4433constructorimpl4, Integer.valueOf(iHashCode4), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4439reconcileimpl(composerM4433constructorimpl4, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4441setimpl(composerM4433constructorimpl4, modifierMaterializeModifier4, ComposeUiNode.INSTANCE.getSetModifier());
            int i11 = (i10 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            int i12 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, 1592350890, "C273@10947L76:TugasScreen.kt#kl928v");
            TextKt.m3157TextNvy7gAk($courseName == null ? "Personal" : $courseName, null, Color.INSTANCE.m5350getWhite0d7_KjU(), null, TextUnitKt.getSp(10), null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 24960, 0, 262122);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            SpacerKt.Spacer(SizeKt.m1120width3ABfNKs(Modifier.INSTANCE, Dp.m8150constructorimpl(8)), $composer, 6);
            TextKt.m3157TextNvy7gAk("Priority: " + ($task.getPriority() == 0 ? "Rendah" : $task.getPriority() == 1 ? "Sedang" : "Tinggi"), null, MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnSurfaceVariant(), null, TextUnitKt.getSp(10), null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 24576, 0, 262122);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            SpacerKt.Spacer(SizeKt.m1101height3ABfNKs(Modifier.INSTANCE, Dp.m8150constructorimpl(4)), $composer, 6);
            TextKt.m3157TextNvy7gAk("Deadline: " + new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Long.valueOf($task.getDueDate())), null, MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnSurfaceVariant(), null, TextUnitKt.getSp(10), null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 24576, 0, 262122);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            IconButtonKt.IconButton($onEdit, null, false, null, null, null, ComposableSingletons$TugasScreenKt.INSTANCE.getLambda$999059307$app(), $composer, 1572864, 62);
            IconButtonKt.IconButton($onDelete, null, false, null, null, null, ComposableSingletons$TugasScreenKt.INSTANCE.getLambda$1270583010$app(), $composer, 1572864, 62);
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
}
