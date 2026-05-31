package androidx.compose.foundation.layout;

import androidx.compose.foundation.layout.FlexBoxConfig;
import androidx.compose.foundation.layout.FlexWrap;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.unit.Density;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;

/* JADX INFO: compiled from: FlexBox.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000j\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a@\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u001c\u0010\u0006\u001a\u0018\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00010\u0007¢\u0006\u0002\b\t¢\u0006\u0002\b\nH\u0087\b¢\u0006\u0002\u0010\u000b\u001a\u001b\u0010\f\u001a\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00050\u000fH\u0001¢\u0006\u0002\u0010\u0010\u001aF\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u00162\u0006\u0010\u0018\u001a\u00020\u00122\u001d\u0010\u0019\u001a\u0019\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00120\u001a¢\u0006\u0002\b\nH\u0082\b\u001ae\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u00162\u0006\u0010\u001c\u001a\u00020\u00122\u001d\u0010\u0019\u001a\u0019\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00120\u001a¢\u0006\u0002\b\n2\u001d\u0010\u001d\u001a\u0019\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00120\u001a¢\u0006\u0002\b\nH\u0082\b\u001aR\u0010\"\u001a\u00020\u0001\"\u0004\b\u0000\u0010#*\u0012\u0012\u0004\u0012\u0002H#0$j\b\u0012\u0004\u0012\u0002H#`%2\u0006\u0010&\u001a\u00020\u00122\u0006\u0010'\u001a\u00020\u00122\u0012\u0010(\u001a\u000e\u0012\u0004\u0012\u0002H#\u0012\u0004\u0012\u00020\u00010\u0007H\u0082\b\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0003\u001aR\u0010)\u001a\u00020\u0012\"\u0004\b\u0000\u0010#*\u0012\u0012\u0004\u0012\u0002H#0$j\b\u0012\u0004\u0012\u0002H#`%2\u0006\u0010&\u001a\u00020\u00122\u0006\u0010'\u001a\u00020\u00122\u0012\u0010*\u001a\u000e\u0012\u0004\u0012\u0002H#\u0012\u0004\u0012\u00020\u00120\u0007H\u0082\b\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0003\"\u0014\u0010\u001e\u001a\u00020\u001fX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b \u0010!¨\u0006+"}, d2 = {"FlexBox", "", "modifier", "Landroidx/compose/ui/Modifier;", "config", "Landroidx/compose/foundation/layout/FlexBoxConfig;", "content", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/FlexBoxScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "(Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/layout/FlexBoxConfig;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "flexMultiContentMeasurePolicy", "Landroidx/compose/ui/layout/MeasurePolicy;", "flexBoxConfigState", "Landroidx/compose/runtime/State;", "(Landroidx/compose/runtime/State;Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/layout/MeasurePolicy;", "intrinsicMainAxisSize", "", "flexBoxConfig", "Landroidx/compose/foundation/layout/ResolvedFlexBoxConfig;", "measurables", "", "Landroidx/compose/ui/layout/IntrinsicMeasurable;", "crossAxisAvailable", "mainAxisSize", "Lkotlin/Function2;", "intrinsicCrossAxisSize", "mainAxisAvailable", "crossAxisSize", "DefaultDensity", "Landroidx/compose/ui/unit/Density;", "getDefaultDensity", "()Landroidx/compose/ui/unit/Density;", "fastForEachUntil", "T", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "fromIndex", "toIndex", "action", "fastSumBy", "selector", "foundation-layout"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class FlexBoxKt {
    private static final Density DefaultDensity = new Density() { // from class: androidx.compose.foundation.layout.FlexBoxKt$DefaultDensity$1
        private final float density = 1.0f;
        private final float fontScale = 1.0f;

        @Override // androidx.compose.ui.unit.Density
        public float getDensity() {
            return this.density;
        }

        @Override // androidx.compose.ui.unit.FontScaling
        public float getFontScale() {
            return this.fontScale;
        }
    };

    public static final void FlexBox(Modifier modifier, FlexBoxConfig config, Function3<? super FlexBoxScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, int $changed, int i) {
        Modifier.Companion modifier2;
        FlexBoxConfig.Companion config2;
        ComposerKt.sourceInformationMarkerStart($composer, -2044607503, "CC(FlexBox)N(modifier,config,content)138@7604L28,142@7752L65,139@7637L187:FlexBox.kt#2w3rfo");
        if ((i & 1) != 0) {
            modifier2 = Modifier.INSTANCE;
        } else {
            modifier2 = modifier;
        }
        if ((i & 2) == 0) {
            config2 = config;
        } else {
            config2 = FlexBoxConfig.INSTANCE;
        }
        State currentConfig = SnapshotStateKt.rememberUpdatedState(config2, $composer, ($changed >> 3) & 14);
        MeasurePolicy measurePolicy$iv = flexMultiContentMeasurePolicy(currentConfig, $composer, 0);
        int $changed$iv = ($changed << 3) & 112;
        Modifier modifier$iv = modifier2;
        ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
        int compositeKeyHash$iv = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
        CompositionLocalMap localMap$iv = $composer.getCurrentCompositionLocalMap();
        Modifier materialized$iv = ComposedModifierKt.materializeModifier($composer, modifier$iv);
        Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
        int $changed$iv$iv = (($changed$iv << 6) & 896) | 6;
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
        Composer $this$Layout_u24lambda_u240$iv = Updater.m4433constructorimpl($composer);
        Updater.m4441setimpl($this$Layout_u24lambda_u240$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
        Updater.m4441setimpl($this$Layout_u24lambda_u240$iv, localMap$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
        Updater.m4441setimpl($this$Layout_u24lambda_u240$iv, Integer.valueOf(compositeKeyHash$iv), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
        Updater.m4439reconcileimpl($this$Layout_u24lambda_u240$iv, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
        Updater.m4441setimpl($this$Layout_u24lambda_u240$iv, materialized$iv, ComposeUiNode.INSTANCE.getSetModifier());
        int i2 = ($changed$iv$iv >> 6) & 14;
        ComposerKt.sourceInformationMarkerStart($composer, -1802593205, "C141@7715L9:FlexBox.kt#2w3rfo");
        function3.invoke(FlexBoxScopeInstance.INSTANCE, $composer, Integer.valueOf((($changed >> 3) & 112) | 6));
        ComposerKt.sourceInformationMarkerEnd($composer);
        $composer.endNode();
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerEnd($composer);
    }

    public static final MeasurePolicy flexMultiContentMeasurePolicy(State<? extends FlexBoxConfig> state, Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, 958632450, "C(flexMultiContentMeasurePolicy)N(flexBoxConfigState)157@8149L106:FlexBox.kt#2w3rfo");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(958632450, $changed, -1, "androidx.compose.foundation.layout.flexMultiContentMeasurePolicy (FlexBox.kt:156)");
        }
        ComposerKt.sourceInformationMarkerStart($composer, 2122786732, "CC(remember):FlexBox.kt#9igjgp");
        boolean invalid$iv = ((($changed & 14) ^ 6) > 4 && $composer.changed(state)) || ($changed & 6) == 4;
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            Object value$iv = new FlexBoxMeasurePolicy(state);
            $composer.updateRememberedValue(value$iv);
            it$iv = value$iv;
        }
        FlexBoxMeasurePolicy flexBoxMeasurePolicy = (FlexBoxMeasurePolicy) it$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return flexBoxMeasurePolicy;
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x0039  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static final int intrinsicMainAxisSize(androidx.compose.foundation.layout.ResolvedFlexBoxConfig r18, java.util.List<? extends androidx.compose.ui.layout.IntrinsicMeasurable> r19, int r20, kotlin.jvm.functions.Function2<? super androidx.compose.ui.layout.IntrinsicMeasurable, ? super java.lang.Integer, java.lang.Integer> r21) {
        /*
            r0 = r21
            r1 = 0
            boolean r2 = r19.isEmpty()
            r3 = 0
            if (r2 == 0) goto Lb
            return r3
        Lb:
            int r2 = r18.mainAxisGap()
            r4 = r18
            r5 = 0
            int r6 = r4.getWrap()
            androidx.compose.foundation.layout.FlexWrap$Companion r7 = androidx.compose.foundation.layout.FlexWrap.INSTANCE
            r8 = 0
            r9 = 1
            int r7 = androidx.compose.foundation.layout.FlexWrap.m882constructorimpl(r9)
            boolean r6 = androidx.compose.foundation.layout.FlexWrap.m884equalsimpl0(r6, r7)
            if (r6 != 0) goto L39
            int r6 = r4.getWrap()
            androidx.compose.foundation.layout.FlexWrap$Companion r7 = androidx.compose.foundation.layout.FlexWrap.INSTANCE
            r8 = 0
            r10 = 2
            int r7 = androidx.compose.foundation.layout.FlexWrap.m882constructorimpl(r10)
            boolean r6 = androidx.compose.foundation.layout.FlexWrap.m884equalsimpl0(r6, r7)
            if (r6 == 0) goto L37
            goto L39
        L37:
            r4 = r3
            goto L3a
        L39:
            r4 = r9
        L3a:
            if (r4 != 0) goto L7f
            r4 = r19
            r5 = 0
            r6 = 0
            r7 = r4
            r8 = 0
            r10 = 0
            r11 = r7
            java.util.Collection r11 = (java.util.Collection) r11
            int r11 = r11.size()
        L4a:
            if (r10 >= r11) goto L6e
            java.lang.Object r12 = r7.get(r10)
            r13 = r12
            r14 = 0
            r15 = r13
            androidx.compose.ui.layout.IntrinsicMeasurable r15 = (androidx.compose.ui.layout.IntrinsicMeasurable) r15
            r16 = 0
            r17 = r9
            java.lang.Integer r9 = java.lang.Integer.valueOf(r20)
            java.lang.Object r9 = r0.invoke(r15, r9)
            java.lang.Number r9 = (java.lang.Number) r9
            int r9 = r9.intValue()
            int r6 = r6 + r9
            int r10 = r10 + 1
            r9 = r17
            goto L4a
        L6e:
            r17 = r9
            int r4 = r19.size()
            int r4 = r4 + (-1)
            int r3 = kotlin.ranges.RangesKt.coerceAtLeast(r4, r3)
            int r3 = r3 * r2
            int r6 = r6 + r3
            goto Lad
        L7f:
            r3 = 0
            r4 = r19
            r5 = 0
            r6 = 0
            r7 = r4
            java.util.Collection r7 = (java.util.Collection) r7
            int r7 = r7.size()
        L8b:
            if (r6 >= r7) goto Lab
            java.lang.Object r8 = r4.get(r6)
            r9 = r8
            androidx.compose.ui.layout.IntrinsicMeasurable r9 = (androidx.compose.ui.layout.IntrinsicMeasurable) r9
            r10 = 0
            java.lang.Integer r11 = java.lang.Integer.valueOf(r20)
            java.lang.Object r11 = r0.invoke(r9, r11)
            java.lang.Number r11 = (java.lang.Number) r11
            int r11 = r11.intValue()
            int r3 = java.lang.Math.max(r3, r11)
            int r6 = r6 + 1
            goto L8b
        Lab:
            r6 = r3
        Lad:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.layout.FlexBoxKt.intrinsicMainAxisSize(androidx.compose.foundation.layout.ResolvedFlexBoxConfig, java.util.List, int, kotlin.jvm.functions.Function2):int");
    }

    private static final int intrinsicCrossAxisSize(ResolvedFlexBoxConfig flexBoxConfig, List<? extends IntrinsicMeasurable> list, int mainAxisAvailable, Function2<? super IntrinsicMeasurable, ? super Integer, Integer> function2, Function2<? super IntrinsicMeasurable, ? super Integer, Integer> function22) {
        int $i$f$intrinsicCrossAxisSize = 0;
        if (list.isEmpty()) {
            return 0;
        }
        int mainAxisGap = flexBoxConfig.mainAxisGap();
        int crossAxisGap = flexBoxConfig.crossAxisGap();
        int currentLineMainAxisSize = 0;
        int currentLineCrossAxisSize = 0;
        int totalCrossAxisSize = 0;
        int index$iv = 0;
        int size = list.size();
        while (index$iv < size) {
            Object item$iv = list.get(index$iv);
            IntrinsicMeasurable measurable = (IntrinsicMeasurable) item$iv;
            int itemMainAxisSize = function2.invoke(measurable, Integer.MAX_VALUE).intValue();
            int $i$f$intrinsicCrossAxisSize2 = $i$f$intrinsicCrossAxisSize;
            int itemCrossAxisSize = function22.invoke(measurable, Integer.valueOf(itemMainAxisSize)).intValue();
            int wrap = flexBoxConfig.getWrap();
            FlexWrap.Companion companion = FlexWrap.INSTANCE;
            boolean z = true;
            int mainAxisGap2 = mainAxisGap;
            int mainAxisGap3 = FlexWrap.m882constructorimpl(1);
            if (!FlexWrap.m884equalsimpl0(wrap, mainAxisGap3)) {
                int wrap2 = flexBoxConfig.getWrap();
                FlexWrap.Companion companion2 = FlexWrap.INSTANCE;
                if (!FlexWrap.m884equalsimpl0(wrap2, FlexWrap.m882constructorimpl(2))) {
                    z = false;
                }
            }
            if (z && currentLineMainAxisSize != 0 && currentLineMainAxisSize + itemMainAxisSize > mainAxisAvailable) {
                totalCrossAxisSize += currentLineCrossAxisSize + crossAxisGap;
                int currentLineMainAxisSize2 = itemMainAxisSize + mainAxisGap2;
                currentLineCrossAxisSize = itemCrossAxisSize;
                currentLineMainAxisSize = currentLineMainAxisSize2;
            } else {
                currentLineMainAxisSize += itemMainAxisSize + mainAxisGap2;
                currentLineCrossAxisSize = Math.max(currentLineCrossAxisSize, itemCrossAxisSize);
            }
            index$iv++;
            $i$f$intrinsicCrossAxisSize = $i$f$intrinsicCrossAxisSize2;
            mainAxisGap = mainAxisGap2;
        }
        return totalCrossAxisSize + currentLineCrossAxisSize;
    }

    public static final Density getDefaultDensity() {
        return DefaultDensity;
    }

    private static final <T> void fastForEachUntil(ArrayList<T> arrayList, int fromIndex, int toIndex, Function1<? super T, Unit> function1) {
        if (!(fromIndex >= 0 && fromIndex <= arrayList.size())) {
            throw new IndexOutOfBoundsException("fromIndex (" + fromIndex + ") is out of bounds [0, " + arrayList.size() + ']');
        }
        if (!(toIndex >= 0 && toIndex <= arrayList.size())) {
            throw new IndexOutOfBoundsException("toIndex (" + toIndex + ") is out of bounds [0, " + arrayList.size() + ']');
        }
        for (int index = fromIndex; index < toIndex; index++) {
            function1.invoke(arrayList.get(index));
        }
    }

    private static final <T> int fastSumBy(ArrayList<T> arrayList, int fromIndex, int toIndex, Function1<? super T, Integer> function1) {
        int sum = 0;
        if (!(fromIndex >= 0 && fromIndex <= arrayList.size())) {
            throw new IndexOutOfBoundsException("fromIndex (" + fromIndex + ") is out of bounds [0, " + arrayList.size() + ']');
        }
        if (!(toIndex >= 0 && toIndex <= arrayList.size())) {
            throw new IndexOutOfBoundsException("toIndex (" + toIndex + ") is out of bounds [0, " + arrayList.size() + ']');
        }
        for (int index$iv = fromIndex; index$iv < toIndex; index$iv++) {
            Object it = arrayList.get(index$iv);
            sum += function1.invoke(it).intValue();
        }
        return sum;
    }
}
