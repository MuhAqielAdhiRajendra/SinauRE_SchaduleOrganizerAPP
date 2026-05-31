package androidx.compose.material3;

import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.layout.SubcomposeLayoutKt;
import androidx.compose.ui.layout.SubcomposeMeasureScope;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Dp;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Ref;

/* JADX INFO: compiled from: TabRow.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
final class TabRowKt$TabRowWithSubcomposeImpl$1 implements Function2<Composer, Integer, Unit> {
    final /* synthetic */ Function2<Composer, Integer, Unit> $divider;
    final /* synthetic */ Function3<List<TabPosition>, Composer, Integer, Unit> $indicator;
    final /* synthetic */ Function2<Composer, Integer, Unit> $tabs;

    /* JADX WARN: Multi-variable type inference failed */
    TabRowKt$TabRowWithSubcomposeImpl$1(Function2<? super Composer, ? super Integer, Unit> function2, Function2<? super Composer, ? super Integer, Unit> function22, Function3<? super List<TabPosition>, ? super Composer, ? super Integer, Unit> function3) {
        this.$tabs = function2;
        this.$divider = function22;
        this.$indicator = function3;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
        invoke(composer, num.intValue());
        return Unit.INSTANCE;
    }

    public final void invoke(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C768@33836L2160,768@33794L2202:TabRow.kt#uh7d8r");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
            return;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1815327065, $changed, -1, "androidx.compose.material3.TabRowWithSubcomposeImpl.<anonymous> (TabRow.kt:768)");
        }
        Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
        ComposerKt.sourceInformationMarkerStart($composer, -772583465, "CC(remember):TabRow.kt#9igjgp");
        boolean invalid$iv = $composer.changed(this.$tabs) | $composer.changed(this.$divider) | $composer.changed(this.$indicator);
        final Function2<Composer, Integer, Unit> function2 = this.$tabs;
        final Function2<Composer, Integer, Unit> function22 = this.$divider;
        final Function3<List<TabPosition>, Composer, Integer, Unit> function3 = this.$indicator;
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            Object value$iv = new Function2() { // from class: androidx.compose.material3.TabRowKt$TabRowWithSubcomposeImpl$1$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TabRowKt$TabRowWithSubcomposeImpl$1.invoke$lambda$8$lambda$7(function2, function22, function3, (SubcomposeMeasureScope) obj, (Constraints) obj2);
                }
            };
            $composer.updateRememberedValue(value$iv);
            it$iv = value$iv;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        SubcomposeLayoutKt.SubcomposeLayout(modifierFillMaxWidth$default, (Function2) it$iv, $composer, 6, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }

    static final MeasureResult invoke$lambda$8$lambda$7(Function2 $tabs, final Function2 $divider, final Function3 $indicator, final SubcomposeMeasureScope $this$SubcomposeLayout, final Constraints constraints) {
        int tabRowWidth = Constraints.m8103getMaxWidthimpl(constraints.getValue());
        List<Measurable> listSubcompose = $this$SubcomposeLayout.subcompose(TabSlots.Tabs, $tabs);
        int tabCount = listSubcompose.size();
        final Ref.IntRef tabWidth = new Ref.IntRef();
        if (tabCount > 0) {
            tabWidth.element = tabRowWidth / tabCount;
        }
        Object initial$iv = 0;
        Object accumulator$iv = initial$iv;
        int index$iv$iv = 0;
        int size = listSubcompose.size();
        while (index$iv$iv < size) {
            Object item$iv$iv = listSubcompose.get(index$iv$iv);
            Measurable curr = (Measurable) item$iv$iv;
            Object initial$iv2 = initial$iv;
            int max = ((Number) accumulator$iv).intValue();
            accumulator$iv = Integer.valueOf(Math.max(curr.maxIntrinsicHeight(tabWidth.element), max));
            index$iv$iv++;
            initial$iv = initial$iv2;
            tabRowWidth = tabRowWidth;
        }
        final int tabRowWidth2 = tabRowWidth;
        int tabRowHeight = ((Number) accumulator$iv).intValue();
        List<Measurable> list = listSubcompose;
        int $i$f$fastMap = 0;
        ArrayList target$iv = new ArrayList(list.size());
        List<Measurable> list2 = list;
        int index$iv$iv2 = 0;
        int size2 = list2.size();
        while (index$iv$iv2 < size2) {
            Object item$iv$iv2 = list2.get(index$iv$iv2);
            List<Measurable> list3 = list;
            ArrayList $this$fastMap$iv = target$iv;
            int $i$f$fastMap2 = $i$f$fastMap;
            Measurable it = (Measurable) item$iv$iv2;
            int tabRowHeight2 = tabRowHeight;
            $this$fastMap$iv.add(it.mo6783measureBRTryo0(Constraints.m8092copyZbe2FdA(constraints.getValue(), tabWidth.element, tabWidth.element, tabRowHeight2, tabRowHeight)));
            index$iv$iv2++;
            tabRowHeight = tabRowHeight2;
            list = list3;
            $i$f$fastMap = $i$f$fastMap2;
            target$iv = target$iv;
            list2 = list2;
        }
        ArrayList target$iv2 = target$iv;
        final int tabRowHeight3 = tabRowHeight;
        final ArrayList tabPlaceables = target$iv2;
        ArrayList arrayList = new ArrayList(tabCount);
        int i = 0;
        while (i < tabCount) {
            int index = i;
            float contentWidth = $this$SubcomposeLayout.mo429toDpu2uoSUM(Math.min(listSubcompose.get(index).maxIntrinsicWidth(tabRowHeight3), tabWidth.element));
            float arg0$iv = TabKt.getHorizontalTextPadding();
            float other$iv = Dp.m8150constructorimpl(contentWidth - Dp.m8150constructorimpl(2 * arg0$iv));
            float indicatorWidth = ((Dp) ComparisonsKt.maxOf(Dp.m8148boximpl(other$iv), Dp.m8148boximpl(Dp.m8150constructorimpl(24)))).m8164unboximpl();
            float arg0$iv2 = $this$SubcomposeLayout.mo429toDpu2uoSUM(tabWidth.element);
            arrayList.add(new TabPosition(Dp.m8150constructorimpl(index * arg0$iv2), $this$SubcomposeLayout.mo429toDpu2uoSUM(tabWidth.element), indicatorWidth, null));
            i++;
            tabPlaceables = tabPlaceables;
        }
        final ArrayList tabPositions = arrayList;
        return MeasureScope.layout$default($this$SubcomposeLayout, tabRowWidth2, tabRowHeight3, null, new Function1() { // from class: androidx.compose.material3.TabRowKt$TabRowWithSubcomposeImpl$1$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TabRowKt$TabRowWithSubcomposeImpl$1.invoke$lambda$8$lambda$7$lambda$6(tabPlaceables, $this$SubcomposeLayout, $divider, tabWidth, constraints, tabRowHeight3, $indicator, tabPositions, tabRowWidth2, (Placeable.PlacementScope) obj);
            }
        }, 4, null);
    }

    static final Unit invoke$lambda$8$lambda$7$lambda$6(List $tabPlaceables, SubcomposeMeasureScope $this_SubcomposeLayout, Function2 $divider, Ref.IntRef $tabWidth, Constraints $constraints, int $tabRowHeight, final Function3 $indicator, final List $tabPositions, int $tabRowWidth, Placeable.PlacementScope $this$layout) {
        int size = $tabPlaceables.size();
        for (int index$iv = 0; index$iv < size; index$iv++) {
            Object item$iv = $tabPlaceables.get(index$iv);
            int index = index$iv;
            Placeable.PlacementScope.placeRelative$default($this$layout, (Placeable) item$iv, $tabWidth.element * index, 0, 0.0f, 4, null);
        }
        List<Measurable> listSubcompose = $this_SubcomposeLayout.subcompose(TabSlots.Divider, $divider);
        int size2 = listSubcompose.size();
        for (int index$iv2 = 0; index$iv2 < size2; index$iv2++) {
            Object item$iv2 = listSubcompose.get(index$iv2);
            Measurable it = (Measurable) item$iv2;
            long value = $constraints.getValue();
            Placeable placeable = it.mo6783measureBRTryo0(Constraints.m8092copyZbe2FdA(value, (11 & 1) != 0 ? Constraints.m8105getMinWidthimpl(value) : 0, (11 & 2) != 0 ? Constraints.m8103getMaxWidthimpl(value) : 0, (11 & 4) != 0 ? Constraints.m8104getMinHeightimpl(value) : 0, (11 & 8) != 0 ? Constraints.m8102getMaxHeightimpl(value) : 0));
            Placeable.PlacementScope.placeRelative$default($this$layout, placeable, 0, $tabRowHeight - placeable.getHeight(), 0.0f, 4, null);
        }
        List<Measurable> listSubcompose2 = $this_SubcomposeLayout.subcompose(TabSlots.Indicator, ComposableLambdaKt.composableLambdaInstance(1918742627, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabRowKt$TabRowWithSubcomposeImpl$1$1$1$1$3
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                invoke(composer, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Composer $composer, int $changed) {
                ComposerKt.sourceInformation($composer, "C814@35789L23:TabRow.kt#uh7d8r");
                if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
                    $composer.skipToGroupEnd();
                    return;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1918742627, $changed, -1, "androidx.compose.material3.TabRowWithSubcomposeImpl.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (TabRow.kt:814)");
                }
                $indicator.invoke($tabPositions, $composer, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
        }));
        int size3 = listSubcompose2.size();
        for (int index$iv3 = 0; index$iv3 < size3; index$iv3++) {
            Object item$iv3 = listSubcompose2.get(index$iv3);
            Measurable it2 = (Measurable) item$iv3;
            Placeable.PlacementScope.placeRelative$default($this$layout, it2.mo6783measureBRTryo0(Constraints.INSTANCE.m8113fixedJhjzzOo($tabRowWidth, $tabRowHeight)), 0, 0, 0.0f, 4, null);
        }
        return Unit.INSTANCE;
    }
}
