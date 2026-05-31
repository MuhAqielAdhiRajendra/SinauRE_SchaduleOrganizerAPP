package androidx.compose.ui.layout;

import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.node.LayoutNode;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: compiled from: Layout.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000J\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\u001a8\u0010\u0000\u001a\u00020\u00012\u0016\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u0004¢\u0006\u0002\b\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0087\b¢\u0006\u0002\u0010\n\u001a \u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0087\b¢\u0006\u0002\u0010\u000b\u001a>\u0010\u0000\u001a\u00020\u00012\u001c\u0010\f\u001a\u0018\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u0004¢\u0006\u0002\b\u00050\r2\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u000eH\u0087\b¢\u0006\u0002\u0010\u000f\u001a;\u0010\u0010\u001a\u0012\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u0004¢\u0006\u0002\b\u00052\u001c\u0010\f\u001a\u0018\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u0004¢\u0006\u0002\b\u00050\rH\u0001¢\u0006\u0002\u0010\u0011\u001a3\u0010\u0012\u001a\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u0014\u0012\u0004\u0012\u00020\u00010\u0013¢\u0006\u0002\b\u0004¢\u0006\u0002\b\u00162\u0006\u0010\u0006\u001a\u00020\u0007H\u0001¢\u0006\u0004\b\u0017\u0010\u0018\u001a3\u0010\u0019\u001a\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u0014\u0012\u0004\u0012\u00020\u00010\u0013¢\u0006\u0002\b\u0004¢\u0006\u0002\b\u00162\u0006\u0010\u0006\u001a\u00020\u0007H\u0001¢\u0006\u0004\b\u0012\u0010\u0018\u001a7\u0010\u001a\u001a\u00020\u00012\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u0016\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u0004¢\u0006\u0002\b\u00052\u0006\u0010\b\u001a\u00020\tH\u0007¢\u0006\u0002\u0010\u001b\"\u000e\u0010\u001c\u001a\u00020\u001dX\u0080T¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Layout", "", "content", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "Landroidx/compose/ui/UiComposable;", "modifier", "Landroidx/compose/ui/Modifier;", "measurePolicy", "Landroidx/compose/ui/layout/MeasurePolicy;", "(Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/layout/MeasurePolicy;Landroidx/compose/runtime/Composer;II)V", "(Landroidx/compose/ui/Modifier;Landroidx/compose/ui/layout/MeasurePolicy;Landroidx/compose/runtime/Composer;II)V", "contents", "", "Landroidx/compose/ui/layout/MultiContentMeasurePolicy;", "(Ljava/util/List;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/layout/MultiContentMeasurePolicy;Landroidx/compose/runtime/Composer;II)V", "combineAsVirtualLayouts", "(Ljava/util/List;)Lkotlin/jvm/functions/Function2;", "materializerOf", "Lkotlin/Function1;", "Landroidx/compose/runtime/SkippableUpdater;", "Landroidx/compose/ui/node/ComposeUiNode;", "Lkotlin/ExtensionFunctionType;", "modifierMaterializerOf", "(Landroidx/compose/ui/Modifier;)Lkotlin/jvm/functions/Function3;", "materializerOfWithCompositionLocalInjection", "MultiMeasureLayout", "(Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/layout/MeasurePolicy;Landroidx/compose/runtime/Composer;II)V", "LargeDimension", "", "ui"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class LayoutKt {
    public static final int LargeDimension = 32767;

    /* JADX INFO: renamed from: androidx.compose.ui.layout.LayoutKt$MultiMeasureLayout$2 */
    /* JADX INFO: compiled from: Layout.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    static final class AnonymousClass2 extends Lambda implements Function2<Composer, Integer, Unit> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$default;
        final /* synthetic */ Function2<Composer, Integer, Unit> $content;
        final /* synthetic */ MeasurePolicy $measurePolicy;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass2(Function2<? super Composer, ? super Integer, Unit> function2, MeasurePolicy measurePolicy, int i, int i2) {
            super(2);
            function2 = function2;
            measurePolicy = measurePolicy;
            i = i;
            i = i2;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer composer, int i) {
            LayoutKt.MultiMeasureLayout(modifier, function2, measurePolicy, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i);
        }
    }

    public static final void Layout(Function2<? super Composer, ? super Integer, Unit> function2, Modifier modifier, MeasurePolicy measurePolicy, Composer $composer, int $changed, int i) {
        Modifier.Companion modifier2;
        ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
        if ((i & 2) != 0) {
            modifier2 = Modifier.INSTANCE;
        } else {
            modifier2 = modifier;
        }
        int compositeKeyHash = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
        CompositionLocalMap localMap = $composer.getCurrentCompositionLocalMap();
        Modifier materialized = ComposedModifierKt.materializeModifier($composer, modifier2);
        Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
        int $changed$iv = (($changed << 6) & 896) | 6;
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
        Composer $this$Layout_u24lambda_u240 = Updater.m4433constructorimpl($composer);
        Updater.m4441setimpl($this$Layout_u24lambda_u240, measurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
        Updater.m4441setimpl($this$Layout_u24lambda_u240, localMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
        Updater.m4441setimpl($this$Layout_u24lambda_u240, Integer.valueOf(compositeKeyHash), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
        Updater.m4439reconcileimpl($this$Layout_u24lambda_u240, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
        Updater.m4441setimpl($this$Layout_u24lambda_u240, materialized, ComposeUiNode.INSTANCE.getSetModifier());
        function2.invoke($composer, Integer.valueOf(($changed$iv >> 6) & 14));
        $composer.endNode();
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerEnd($composer);
    }

    public static final void Layout(Modifier modifier, MeasurePolicy measurePolicy, Composer $composer, int $changed, int i) {
        ComposerKt.sourceInformationMarkerStart($composer, 544976794, "CC(Layout)N(modifier,measurePolicy)124@5018L27,127@5184L388:Layout.kt#80mrfh");
        if ((i & 1) != 0) {
            Modifier modifier2 = Modifier.INSTANCE;
            modifier = modifier2;
        }
        int compositeKeyHash = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
        Modifier materialized = ComposedModifierKt.materializeModifier($composer, modifier);
        CompositionLocalMap localMap = $composer.getCurrentCompositionLocalMap();
        Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
        ComposerKt.sourceInformationMarkerStart($composer, 1405779621, "CC(ReusableComposeNode)N(factory,update):Composables.kt#9igjgp");
        if (!($composer.getApplier() instanceof Applier)) {
            ComposablesKt.invalidApplier();
        }
        $composer.startReusableNode();
        if ($composer.getInserting()) {
            $composer.createNode(constructor);
        } else {
            $composer.useNode();
        }
        Composer $this$Layout_u24lambda_u241 = Updater.m4433constructorimpl($composer);
        Updater.m4441setimpl($this$Layout_u24lambda_u241, measurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
        Updater.m4441setimpl($this$Layout_u24lambda_u241, localMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
        Updater.m4439reconcileimpl($this$Layout_u24lambda_u241, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
        Updater.m4441setimpl($this$Layout_u24lambda_u241, materialized, ComposeUiNode.INSTANCE.getSetModifier());
        Updater.m4441setimpl($this$Layout_u24lambda_u241, Integer.valueOf(compositeKeyHash), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
        $composer.endNode();
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerEnd($composer);
    }

    /* JADX WARN: Removed duplicated region for block: B:46:0x003d A[PHI: r5
  0x003d: PHI (r5v6 androidx.compose.ui.layout.MultiContentMeasurePolicy) = 
  (r5v4 androidx.compose.ui.layout.MultiContentMeasurePolicy)
  (r5v7 androidx.compose.ui.layout.MultiContentMeasurePolicy)
 binds: [B:45:0x003b, B:41:0x0034] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00a7  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00b3  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00b7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void Layout(java.util.List<? extends kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit>> r19, androidx.compose.ui.Modifier r20, androidx.compose.ui.layout.MultiContentMeasurePolicy r21, androidx.compose.runtime.Composer r22, int r23, int r24) {
        /*
            Method dump skipped, instruction units count: 272
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.layout.LayoutKt.Layout(java.util.List, androidx.compose.ui.Modifier, androidx.compose.ui.layout.MultiContentMeasurePolicy, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: renamed from: androidx.compose.ui.layout.LayoutKt$combineAsVirtualLayouts$1 */
    /* JADX INFO: compiled from: Layout.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "", "invoke", "(Landroidx/compose/runtime/Composer;I)V"}, k = 3, mv = {2, 1, 0}, xi = 48)
    static final class AnonymousClass1 extends Lambda implements Function2<Composer, Integer, Unit> {
        final /* synthetic */ List<Function2<Composer, Integer, Unit>> $contents;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass1(List<? extends Function2<? super Composer, ? super Integer, Unit>> list) {
            super(2);
            list = list;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer $composer, int $changed) {
            Composer composer = $composer;
            ComposerKt.sourceInformation(composer, "C*181@7469L27,182@7516L215:Layout.kt#80mrfh");
            int i = 0;
            if (!composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1271844412, $changed, -1, "androidx.compose.ui.layout.combineAsVirtualLayouts.<anonymous> (Layout.kt:180)");
            }
            List<Function2<Composer, Integer, Unit>> list = list;
            int index$iv = 0;
            int size = list.size();
            while (index$iv < size) {
                Object item$iv = list.get(index$iv);
                Function2<Composer, Integer, Unit> function2 = (Function2) item$iv;
                int compositeKeyHash = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer, i));
                Function0<ComposeUiNode> virtualConstructor = ComposeUiNode.INSTANCE.getVirtualConstructor();
                ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    $composer.createNode(virtualConstructor);
                } else {
                    $composer.useNode();
                }
                Composer $this$invoke_u24lambda_u240_u240 = Updater.m4433constructorimpl($composer);
                Updater.m4441setimpl($this$invoke_u24lambda_u240_u240, Integer.valueOf(compositeKeyHash), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                function2.invoke($composer, Integer.valueOf((6 >> 6) & 14));
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                index$iv++;
                composer = $composer;
                i = 0;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    public static final Function2<Composer, Integer, Unit> combineAsVirtualLayouts(List<? extends Function2<? super Composer, ? super Integer, Unit>> list) {
        return ComposableLambdaKt.composableLambdaInstance(1271844412, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.layout.LayoutKt.combineAsVirtualLayouts.1
            final /* synthetic */ List<Function2<Composer, Integer, Unit>> $contents;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            AnonymousClass1(List<? extends Function2<? super Composer, ? super Integer, Unit>> list2) {
                super(2);
                list = list2;
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                invoke(composer, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Composer $composer, int $changed) {
                Composer composer = $composer;
                ComposerKt.sourceInformation(composer, "C*181@7469L27,182@7516L215:Layout.kt#80mrfh");
                int i = 0;
                if (!composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
                    $composer.skipToGroupEnd();
                    return;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1271844412, $changed, -1, "androidx.compose.ui.layout.combineAsVirtualLayouts.<anonymous> (Layout.kt:180)");
                }
                List<Function2<Composer, Integer, Unit>> list2 = list;
                int index$iv = 0;
                int size = list2.size();
                while (index$iv < size) {
                    Object item$iv = list2.get(index$iv);
                    Function2<Composer, Integer, Unit> function2 = (Function2) item$iv;
                    int compositeKeyHash = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer, i));
                    Function0<ComposeUiNode> virtualConstructor = ComposeUiNode.INSTANCE.getVirtualConstructor();
                    ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
                    if (!($composer.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer.startReusableNode();
                    if ($composer.getInserting()) {
                        $composer.createNode(virtualConstructor);
                    } else {
                        $composer.useNode();
                    }
                    Composer $this$invoke_u24lambda_u240_u240 = Updater.m4433constructorimpl($composer);
                    Updater.m4441setimpl($this$invoke_u24lambda_u240_u240, Integer.valueOf(compositeKeyHash), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    function2.invoke($composer, Integer.valueOf((6 >> 6) & 14));
                    $composer.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    index$iv++;
                    composer = $composer;
                    i = 0;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
        });
    }

    /* JADX INFO: renamed from: androidx.compose.ui.layout.LayoutKt$materializerOf$1 */
    /* JADX INFO: compiled from: Layout.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "Landroidx/compose/runtime/SkippableUpdater;", "Landroidx/compose/ui/node/ComposeUiNode;", "invoke-Deg8D_g", "(Landroidx/compose/runtime/Composer;Landroidx/compose/runtime/Composer;I)V"}, k = 3, mv = {2, 1, 0}, xi = 48)
    static final class C03021 extends Lambda implements Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> {
        C03021() {
            super(3);
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(SkippableUpdater<ComposeUiNode> skippableUpdater, Composer composer, Integer num) {
            m6807invokeDeg8D_g(skippableUpdater.getComposer(), composer, num.intValue());
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke-Deg8D_g */
        public final void m6807invokeDeg8D_g(Composer composer, Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C200@8185L27:Layout.kt#80mrfh");
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-511438721, $changed, -1, "androidx.compose.ui.layout.materializerOf.<anonymous> (Layout.kt:200)");
            }
            int compositeKeyHash = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
            Modifier materialized = ComposedModifierKt.materializeModifier($composer, modifier);
            composer.startReplaceableGroup(509942095);
            Composer $this$invoke_Deg8D_g_u24lambda_u240 = Updater.m4433constructorimpl(composer);
            Updater.m4441setimpl($this$invoke_Deg8D_g_u24lambda_u240, materialized, ComposeUiNode.INSTANCE.getSetModifier());
            Updater.m4441setimpl($this$invoke_Deg8D_g_u24lambda_u240, Integer.valueOf(compositeKeyHash), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            composer.endReplaceableGroup();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    public static final Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> modifierMaterializerOf(Modifier modifier) {
        return ComposableLambdaKt.composableLambdaInstance(-511438721, true, new Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit>() { // from class: androidx.compose.ui.layout.LayoutKt.materializerOf.1
            C03021() {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Unit invoke(SkippableUpdater<ComposeUiNode> skippableUpdater, Composer composer, Integer num) {
                m6807invokeDeg8D_g(skippableUpdater.getComposer(), composer, num.intValue());
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke-Deg8D_g */
            public final void m6807invokeDeg8D_g(Composer composer, Composer $composer, int $changed) {
                ComposerKt.sourceInformation($composer, "C200@8185L27:Layout.kt#80mrfh");
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-511438721, $changed, -1, "androidx.compose.ui.layout.materializerOf.<anonymous> (Layout.kt:200)");
                }
                int compositeKeyHash = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                Modifier materialized = ComposedModifierKt.materializeModifier($composer, modifier);
                composer.startReplaceableGroup(509942095);
                Composer $this$invoke_Deg8D_g_u24lambda_u240 = Updater.m4433constructorimpl(composer);
                Updater.m4441setimpl($this$invoke_Deg8D_g_u24lambda_u240, materialized, ComposeUiNode.INSTANCE.getSetModifier());
                Updater.m4441setimpl($this$invoke_Deg8D_g_u24lambda_u240, Integer.valueOf(compositeKeyHash), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                composer.endReplaceableGroup();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
        });
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Needed only for backwards compatibility. Do not use.")
    public static final Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> materializerOf(final Modifier modifier) {
        return ComposableLambdaKt.composableLambdaInstance(-2123382363, true, new Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit>() { // from class: androidx.compose.ui.layout.LayoutKt$materializerOfWithCompositionLocalInjection$1
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Unit invoke(SkippableUpdater<ComposeUiNode> skippableUpdater, Composer composer, Integer num) {
                m6808invokeDeg8D_g(skippableUpdater.getComposer(), composer, num.intValue());
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke-Deg8D_g, reason: not valid java name */
            public final void m6808invokeDeg8D_g(Composer composer, Composer $composer, int $changed) {
                ComposerKt.sourceInformation($composer, "C222@8993L23:Layout.kt#80mrfh");
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-2123382363, $changed, -1, "androidx.compose.ui.layout.materializerOfWithCompositionLocalInjection.<anonymous> (Layout.kt:222)");
                }
                int compositeKeyHash = Integer.hashCode(ComposablesKt.getCurrentCompositeKeyHash($composer, 0));
                Modifier materialized = ComposedModifierKt.materializeWithCompositionLocalInjectionInternal($composer, modifier);
                composer.startReplaceableGroup(509942095);
                Composer $this$invoke_Deg8D_g_u24lambda_u240 = Updater.m4433constructorimpl(composer);
                Updater.m4441setimpl($this$invoke_Deg8D_g_u24lambda_u240, materialized, ComposeUiNode.INSTANCE.getSetModifier());
                Updater.m4441setimpl($this$invoke_Deg8D_g_u24lambda_u240, Integer.valueOf(compositeKeyHash), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                composer.endReplaceableGroup();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
        });
    }

    @Deprecated(message = "This API is unsafe for UI performance at scale - using it incorrectly will lead to exponential performance issues. This API should be avoided whenever possible.")
    public static final void MultiMeasureLayout(Modifier modifier, Function2<? super Composer, ? super Integer, Unit> function2, MeasurePolicy measurePolicy, Composer $composer, int $changed, int i) {
        Modifier modifier2;
        Modifier.Companion modifier3;
        Composer $composer2 = $composer.startRestartGroup(-1663319424);
        ComposerKt.sourceInformation($composer2, "C(MultiMeasureLayout)N(modifier,content,measurePolicy)242@9681L23,246@9844L483:Layout.kt#80mrfh");
        int $dirty = $changed;
        int i2 = i & 1;
        if (i2 != 0) {
            $dirty |= 6;
            modifier2 = modifier;
        } else if (($changed & 6) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 4 : 2;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changedInstance(function2) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer2.changed(measurePolicy) ? 256 : 128;
        }
        int $dirty2 = $dirty;
        if (!$composer2.shouldExecute(($dirty2 & 147) != 146, $dirty2 & 1)) {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
        } else {
            if (i2 != 0) {
                modifier3 = Modifier.INSTANCE;
            } else {
                modifier3 = modifier2;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1663319424, $dirty2, -1, "androidx.compose.ui.layout.MultiMeasureLayout (Layout.kt:241)");
            }
            int compositeKeyHash = Integer.hashCode(ComposablesKt.getCurrentCompositeKeyHash($composer2, 0));
            Modifier materialized = ComposedModifierKt.materializeModifier($composer2, modifier3);
            CompositionLocalMap localMap = $composer2.getCurrentCompositionLocalMap();
            Function0<LayoutNode> constructor$ui = LayoutNode.INSTANCE.getConstructor$ui();
            int $changed$iv = (($dirty2 << 3) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)410@16187L9:Composables.kt#9igjgp");
            if (!($composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer2.startReusableNode();
            if ($composer2.getInserting()) {
                $composer2.createNode(constructor$ui);
            } else {
                $composer2.useNode();
            }
            Composer $this$MultiMeasureLayout_u24lambda_u240 = Updater.m4433constructorimpl($composer2);
            Updater.m4441setimpl($this$MultiMeasureLayout_u24lambda_u240, measurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4441setimpl($this$MultiMeasureLayout_u24lambda_u240, localMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4438initimpl($this$MultiMeasureLayout_u24lambda_u240, new Function1<LayoutNode, Unit>() { // from class: androidx.compose.ui.layout.LayoutKt$MultiMeasureLayout$1$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(LayoutNode layoutNode) {
                    invoke2(layoutNode);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(LayoutNode $this$init) {
                    $this$init.setCanMultiMeasure$ui(true);
                }
            });
            Updater.m4439reconcileimpl($this$MultiMeasureLayout_u24lambda_u240, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4441setimpl($this$MultiMeasureLayout_u24lambda_u240, materialized, ComposeUiNode.INSTANCE.getSetModifier());
            Updater.m4441setimpl($this$MultiMeasureLayout_u24lambda_u240, Integer.valueOf(compositeKeyHash), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            function2.invoke($composer2, Integer.valueOf(($changed$iv >> 6) & 14));
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.layout.LayoutKt.MultiMeasureLayout.2
                final /* synthetic */ int $$changed;
                final /* synthetic */ int $$default;
                final /* synthetic */ Function2<Composer, Integer, Unit> $content;
                final /* synthetic */ MeasurePolicy $measurePolicy;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                AnonymousClass2(Function2<? super Composer, ? super Integer, Unit> function22, MeasurePolicy measurePolicy2, int $changed2, int i3) {
                    super(2);
                    function2 = function22;
                    measurePolicy = measurePolicy2;
                    i = $changed2;
                    i = i3;
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i3) {
                    LayoutKt.MultiMeasureLayout(modifier, function2, measurePolicy, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i);
                }
            });
        }
    }
}
