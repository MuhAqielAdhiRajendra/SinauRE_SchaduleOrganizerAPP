package androidx.compose.material3;

import androidx.autofill.HintConstants;
import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimatableKt;
import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.material3.internal.Strings;
import androidx.compose.material3.internal.Strings_androidKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.AccessibilityManager;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.util.ListUtilsKt;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: SnackbarHost.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000Z\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a:\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u0019\b\u0002\u0010\u0006\u001a\u0013\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00010\u0007¢\u0006\u0002\b\tH\u0007¢\u0006\u0002\u0010\n\u001a\u001e\u0010\u000b\u001a\u00020\f*\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0000\u001a:\u0010\u0012\u001a\u00020\u00012\b\u0010\u0013\u001a\u0004\u0018\u00010\b2\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u0017\u0010\u0014\u001a\u0013\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00010\u0007¢\u0006\u0002\b\tH\u0003¢\u0006\u0002\u0010\u0015\u001a9\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001e2\u0006\u0010\u001f\u001a\u00020\u000f2\u000e\b\u0002\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00010\u0017H\u0003¢\u0006\u0002\u0010!\u001a)\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001e2\u0006\u0010\u001f\u001a\u00020\u000fH\u0003¢\u0006\u0002\u0010#*b\b\u0002\u0010\u0016\"-\u0012\u001e\u0012\u001c\u0012\u0004\u0012\u00020\u00010\u0017¢\u0006\u0002\b\t¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\u00010\u0007¢\u0006\u0002\b\t2-\u0012\u001e\u0012\u001c\u0012\u0004\u0012\u00020\u00010\u0017¢\u0006\u0002\b\t¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\u00010\u0007¢\u0006\u0002\b\t¨\u0006$"}, d2 = {"SnackbarHost", "", "hostState", "Landroidx/compose/material3/SnackbarHostState;", "modifier", "Landroidx/compose/ui/Modifier;", "snackbar", "Lkotlin/Function1;", "Landroidx/compose/material3/SnackbarData;", "Landroidx/compose/runtime/Composable;", "(Landroidx/compose/material3/SnackbarHostState;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "toMillis", "", "Landroidx/compose/material3/SnackbarDuration;", "hasAction", "", "accessibilityManager", "Landroidx/compose/ui/platform/AccessibilityManager;", "FadeInFadeOutWithScale", "current", "content", "(Landroidx/compose/material3/SnackbarData;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "FadeInFadeOutTransition", "Lkotlin/Function0;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "animatedOpacity", "Landroidx/compose/runtime/State;", "", "animation", "Landroidx/compose/animation/core/AnimationSpec;", "visible", "onAnimationFinish", "(Landroidx/compose/animation/core/AnimationSpec;ZLkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;II)Landroidx/compose/runtime/State;", "animatedScale", "(Landroidx/compose/animation/core/AnimationSpec;ZLandroidx/compose/runtime/Composer;I)Landroidx/compose/runtime/State;", "material3"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class SnackbarHostKt {

    /* JADX INFO: compiled from: SnackbarHost.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[SnackbarDuration.values().length];
            try {
                iArr[SnackbarDuration.Indefinite.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[SnackbarDuration.Long.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[SnackbarDuration.Short.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    static final Unit FadeInFadeOutWithScale$lambda$7(SnackbarData snackbarData, Modifier modifier, Function3 function3, int i, int i2, Composer composer, int i3) {
        FadeInFadeOutWithScale(snackbarData, modifier, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit SnackbarHost$lambda$1(SnackbarHostState snackbarHostState, Modifier modifier, Function3 function3, int i, int i2, Composer composer, int i3) {
        SnackbarHost(snackbarHostState, modifier, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static final void SnackbarHost(SnackbarHostState hostState, Modifier modifier, Function3<? super SnackbarData, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        SnackbarHostState snackbarHostState;
        Modifier modifier2;
        Function3<? super SnackbarData, ? super Composer, ? super Integer, Unit> function3M2390getLambda$1548712596$material3;
        final Function3<? super SnackbarData, ? super Composer, ? super Integer, Unit> function32;
        int i2;
        Modifier modifier3;
        Composer $composer2 = $composer.startRestartGroup(-1077081618);
        ComposerKt.sourceInformation($composer2, "C(SnackbarHost)N(hostState,modifier,snackbar)222@9520L7,223@9568L349,223@9532L385,234@9922L135:SnackbarHost.kt#uh7d8r");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
            snackbarHostState = hostState;
        } else if (($changed & 6) == 0) {
            snackbarHostState = hostState;
            $dirty |= $composer2.changed(snackbarHostState) ? 4 : 2;
        } else {
            snackbarHostState = hostState;
        }
        int i3 = i & 2;
        if (i3 != 0) {
            $dirty |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i4 = i & 4;
        if (i4 != 0) {
            $dirty |= 384;
            function3M2390getLambda$1548712596$material3 = function3;
        } else if (($changed & 384) == 0) {
            function3M2390getLambda$1548712596$material3 = function3;
            $dirty |= $composer2.changedInstance(function3M2390getLambda$1548712596$material3) ? 256 : 128;
        } else {
            function3M2390getLambda$1548712596$material3 = function3;
        }
        int $dirty2 = $dirty;
        if (!$composer2.shouldExecute(($dirty2 & 147) != 146, $dirty2 & 1)) {
            $composer2.skipToGroupEnd();
            function32 = function3M2390getLambda$1548712596$material3;
        } else {
            if (i3 != 0) {
                modifier3 = Modifier.INSTANCE;
                i2 = i4;
            } else {
                i2 = i4;
                modifier3 = modifier2;
            }
            if (i2 != 0) {
                function3M2390getLambda$1548712596$material3 = ComposableSingletons$SnackbarHostKt.INSTANCE.m2390getLambda$1548712596$material3();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1077081618, $dirty2, -1, "androidx.compose.material3.SnackbarHost (SnackbarHost.kt:220)");
            }
            SnackbarData currentSnackbarData = snackbarHostState.getCurrentSnackbarData();
            ProvidableCompositionLocal<AccessibilityManager> localAccessibilityManager = CompositionLocalsKt.getLocalAccessibilityManager();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = $composer2.consume(localAccessibilityManager);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            AccessibilityManager accessibilityManager = (AccessibilityManager) objConsume;
            ComposerKt.sourceInformationMarkerStart($composer2, 11694571, "CC(remember):SnackbarHost.kt#9igjgp");
            boolean invalid$iv = $composer2.changed(currentSnackbarData) | $composer2.changedInstance(accessibilityManager);
            Object it$iv = $composer2.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = (Function2) new SnackbarHostKt$SnackbarHost$1$1(currentSnackbarData, accessibilityManager, null);
                $composer2.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            EffectsKt.LaunchedEffect(currentSnackbarData, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) it$iv, $composer2, 0);
            FadeInFadeOutWithScale(snackbarHostState.getCurrentSnackbarData(), modifier3, function3M2390getLambda$1548712596$material3, $composer2, ($dirty2 & 896) | ($dirty2 & 112), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier2 = modifier3;
            function32 = function3M2390getLambda$1548712596$material3;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final SnackbarHostState snackbarHostState2 = snackbarHostState;
            final Modifier modifier4 = modifier2;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.SnackbarHostKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return SnackbarHostKt.SnackbarHost$lambda$1(snackbarHostState2, modifier4, function32, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final long toMillis(SnackbarDuration $this$toMillis, boolean hasAction, AccessibilityManager accessibilityManager) {
        long j;
        switch (WhenMappings.$EnumSwitchMapping$0[$this$toMillis.ordinal()]) {
            case 1:
                j = Long.MAX_VALUE;
                break;
            case 2:
                j = 10000;
                break;
            case 3:
                j = 4000;
                break;
            default:
                throw new NoWhenBranchMatchedException();
        }
        long original = j;
        if (accessibilityManager == null) {
            return original;
        }
        return accessibilityManager.calculateRecommendedTimeoutMillis(original, true, true, hasAction);
    }

    private static final void FadeInFadeOutWithScale(final SnackbarData current, Modifier modifier, final Function3<? super SnackbarData, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        final Modifier modifier3;
        Modifier.Companion modifier4;
        Modifier modifier5;
        int $dirty;
        SnackbarData snackbarData = current;
        final Function3<? super SnackbarData, ? super Composer, ? super Integer, Unit> function32 = function3;
        Composer $composer2 = $composer.startRestartGroup(-977568115);
        ComposerKt.sourceInformation($composer2, "C(FadeInFadeOutWithScale)N(current,modifier,content)327@12796L36,328@12849L48,380@15021L162:SnackbarHost.kt#uh7d8r");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty2 |= $composer2.changed(snackbarData) ? 4 : 2;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty2 |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer2.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        if ((i & 4) != 0) {
            $dirty2 |= 384;
        } else if (($changed & 384) == 0) {
            $dirty2 |= $composer2.changedInstance(function32) ? 256 : 128;
        }
        int $dirty3 = $dirty2;
        if ($composer2.shouldExecute(($dirty3 & 147) != 146, $dirty3 & 1)) {
            if (i2 != 0) {
                modifier4 = Modifier.INSTANCE;
            } else {
                modifier4 = modifier2;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-977568115, $dirty3, -1, "androidx.compose.material3.FadeInFadeOutWithScale (SnackbarHost.kt:326)");
            }
            Strings.Companion companion = Strings.INSTANCE;
            String a11yPaneTitle = Strings_androidKt.m3533getString2EP1pXo(Strings.m3454constructorimpl(R.string.m3c_snackbar_pane_title), $composer2, 0);
            ComposerKt.sourceInformationMarkerStart($composer2, 1154887069, "CC(remember):SnackbarHost.kt#9igjgp");
            Object it$iv = $composer2.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = new FadeInFadeOutState();
                $composer2.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            FadeInFadeOutState state = (FadeInFadeOutState) it$iv;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (Intrinsics.areEqual(snackbarData, state.getCurrent())) {
                modifier5 = modifier4;
                $dirty = $dirty3;
                $composer2.startReplaceGroup(1443908949);
                $composer2.endReplaceGroup();
            } else {
                $composer2.startReplaceGroup(1154891761);
                ComposerKt.sourceInformation($composer2, "*337@13248L1752");
                state.setCurrent(snackbarData);
                List $this$fastMap$iv = state.getItems();
                ArrayList target$iv = new ArrayList($this$fastMap$iv.size());
                int size = $this$fastMap$iv.size();
                int index$iv$iv = 0;
                while (index$iv$iv < size) {
                    Object item$iv$iv = $this$fastMap$iv.get(index$iv$iv);
                    FadeInFadeOutAnimationItem it = (FadeInFadeOutAnimationItem) item$iv$iv;
                    target$iv.add((SnackbarData) it.getKey());
                    index$iv$iv++;
                    modifier4 = modifier4;
                }
                modifier5 = modifier4;
                List keys = CollectionsKt.toMutableList((Collection) target$iv);
                if (!keys.contains(snackbarData)) {
                    keys.add(snackbarData);
                }
                state.getItems().clear();
                List $this$fastMapTo$iv = ListUtilsKt.fastFilterNotNull(keys);
                Collection destination$iv = state.getItems();
                int index$iv$iv2 = 0;
                int size2 = $this$fastMapTo$iv.size();
                while (index$iv$iv2 < size2) {
                    Object item$iv$iv2 = $this$fastMapTo$iv.get(index$iv$iv2);
                    List keys2 = keys;
                    SnackbarData key = (SnackbarData) item$iv$iv2;
                    destination$iv.add(new FadeInFadeOutAnimationItem(key, ComposableLambdaKt.rememberComposableLambda(-1952400805, true, new SnackbarHostKt$FadeInFadeOutWithScale$1$1(key, snackbarData, state, a11yPaneTitle), $composer2, 54)));
                    index$iv$iv2++;
                    snackbarData = current;
                    keys = keys2;
                    $this$fastMapTo$iv = $this$fastMapTo$iv;
                    a11yPaneTitle = a11yPaneTitle;
                    $dirty3 = $dirty3;
                }
                $dirty = $dirty3;
                $composer2.endReplaceGroup();
            }
            int $changed$iv = ($dirty >> 3) & 14;
            Modifier modifier$iv = modifier5;
            ComposerKt.sourceInformationMarkerStart($composer2, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
            Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
            MeasurePolicy measurePolicy$iv = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
            int $changed$iv$iv = ($changed$iv << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
            CompositionLocalMap localMap$iv$iv = $composer2.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer2, modifier$iv);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
            if (!($composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer2.startReusableNode();
            if ($composer2.getInserting()) {
                $composer2.createNode(constructor);
            } else {
                $composer2.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4433constructorimpl($composer2);
            Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
            }
            Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
            int i3 = ($changed$iv$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            int i4 = (($changed$iv >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, 1595840844, "C381@15059L21:SnackbarHost.kt#uh7d8r");
            state.setScope(ComposablesKt.getCurrentRecomposeScope($composer2, 0));
            $composer2.startReplaceGroup(-1888182177);
            ComposerKt.sourceInformation($composer2, "");
            List $this$fastForEach$iv = state.getItems();
            int $i$f$fastForEach = $this$fastForEach$iv.size();
            int $changed2 = 0;
            while ($changed2 < $i$f$fastForEach) {
                Object item$iv = $this$fastForEach$iv.get($changed2);
                FadeInFadeOutAnimationItem fadeInFadeOutAnimationItem = (FadeInFadeOutAnimationItem) item$iv;
                List $this$fastForEach$iv2 = $this$fastForEach$iv;
                final SnackbarData item = (SnackbarData) fadeInFadeOutAnimationItem.component1();
                int i5 = $i$f$fastForEach;
                Function3<Function2<? super Composer, ? super Integer, Unit>, Composer, Integer, Unit> function3Component2 = fadeInFadeOutAnimationItem.component2();
                int index$iv = $changed2;
                $composer2.startMovableGroup(1325010085, item);
                ComposerKt.sourceInformation($composer2, "382@15154L19,382@15146L27");
                function3Component2.invoke(ComposableLambdaKt.rememberComposableLambda(-1893791890, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SnackbarHostKt$FadeInFadeOutWithScale$2$1$1
                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                        invoke(composer, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer $composer3, int $changed3) {
                        ComposerKt.sourceInformation($composer3, "C382@15156L15:SnackbarHost.kt#uh7d8r");
                        if (!$composer3.shouldExecute(($changed3 & 3) != 2, $changed3 & 1)) {
                            $composer3.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1893791890, $changed3, -1, "androidx.compose.material3.FadeInFadeOutWithScale.<anonymous>.<anonymous>.<anonymous>.<anonymous> (SnackbarHost.kt:382)");
                        }
                        Function3<SnackbarData, Composer, Integer, Unit> function33 = function32;
                        SnackbarData snackbarData2 = item;
                        Intrinsics.checkNotNull(snackbarData2);
                        function33.invoke(snackbarData2, $composer3, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }, $composer2, 54), $composer2, 6);
                $composer2.endMovableGroup();
                $changed2 = index$iv + 1;
                state = state;
                function32 = function3;
                $i$f$fastForEach = i5;
                $this$fastForEach$iv = $this$fastForEach$iv2;
            }
            $composer2.endReplaceGroup();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier5;
        } else {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.SnackbarHostKt$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return SnackbarHostKt.FadeInFadeOutWithScale$lambda$7(current, modifier3, function3, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final State<Float> animatedOpacity(AnimationSpec<Float> animationSpec, boolean visible, Function0<Unit> function0, Composer $composer, int $changed, int i) {
        Function0<Unit> function02;
        ComposerKt.sourceInformationMarkerStart($composer, 1431889134, "C(animatedOpacity)N(animation,visible,onAnimationFinish)404@15795L2,406@15833L49,407@15911L111,407@15887L135:SnackbarHost.kt#uh7d8r");
        if ((i & 4) != 0) {
            ComposerKt.sourceInformationMarkerStart($composer, 1655927408, "CC(remember):SnackbarHost.kt#9igjgp");
            Object it$iv = $composer.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = new Function0() { // from class: androidx.compose.material3.SnackbarHostKt$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return Unit.INSTANCE;
                    }
                };
                $composer.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            function02 = (Function0) it$iv;
        } else {
            function02 = function0;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1431889134, $changed, -1, "androidx.compose.material3.animatedOpacity (SnackbarHost.kt:405)");
        }
        ComposerKt.sourceInformationMarkerStart($composer, 1655928671, "CC(remember):SnackbarHost.kt#9igjgp");
        Object it$iv2 = $composer.rememberedValue();
        if (it$iv2 == Composer.INSTANCE.getEmpty()) {
            Object value$iv2 = AnimatableKt.Animatable$default(!visible ? 1.0f : 0.0f, 0.0f, 2, null);
            $composer.updateRememberedValue(value$iv2);
            it$iv2 = value$iv2;
        }
        Animatable alpha = (Animatable) it$iv2;
        ComposerKt.sourceInformationMarkerEnd($composer);
        Boolean boolValueOf = Boolean.valueOf(visible);
        ComposerKt.sourceInformationMarkerStart($composer, 1655931229, "CC(remember):SnackbarHost.kt#9igjgp");
        boolean invalid$iv = $composer.changedInstance(alpha) | (((($changed & 112) ^ 48) > 32 && $composer.changed(visible)) || ($changed & 48) == 32) | $composer.changedInstance(animationSpec) | (((($changed & 896) ^ 384) > 256 && $composer.changed(function02)) || ($changed & 384) == 256);
        Object it$iv3 = $composer.rememberedValue();
        if (invalid$iv || it$iv3 == Composer.INSTANCE.getEmpty()) {
            Object value$iv3 = (Function2) new SnackbarHostKt$animatedOpacity$2$1(alpha, visible, animationSpec, function02, null);
            $composer.updateRememberedValue(value$iv3);
            it$iv3 = value$iv3;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        EffectsKt.LaunchedEffect(boolValueOf, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) it$iv3, $composer, ($changed >> 3) & 14);
        State<Float> stateAsState = alpha.asState();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return stateAsState;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final State<Float> animatedScale(AnimationSpec<Float> animationSpec, boolean visible, Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, 1966809761, "C(animatedScale)N(animation,visible)416@16174L51,417@16254L85,417@16230L109:SnackbarHost.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1966809761, $changed, -1, "androidx.compose.material3.animatedScale (SnackbarHost.kt:415)");
        }
        ComposerKt.sourceInformationMarkerStart($composer, 318574004, "CC(remember):SnackbarHost.kt#9igjgp");
        Object it$iv = $composer.rememberedValue();
        if (it$iv == Composer.INSTANCE.getEmpty()) {
            Object value$iv = AnimatableKt.Animatable$default(!visible ? 1.0f : 0.8f, 0.0f, 2, null);
            $composer.updateRememberedValue(value$iv);
            it$iv = value$iv;
        }
        Animatable scale = (Animatable) it$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        Boolean boolValueOf = Boolean.valueOf(visible);
        ComposerKt.sourceInformationMarkerStart($composer, 318576598, "CC(remember):SnackbarHost.kt#9igjgp");
        boolean invalid$iv = $composer.changedInstance(scale) | (((($changed & 112) ^ 48) > 32 && $composer.changed(visible)) || ($changed & 48) == 32) | $composer.changedInstance(animationSpec);
        Object it$iv2 = $composer.rememberedValue();
        if (invalid$iv || it$iv2 == Composer.INSTANCE.getEmpty()) {
            Object value$iv2 = (Function2) new SnackbarHostKt$animatedScale$1$1(scale, visible, animationSpec, null);
            $composer.updateRememberedValue(value$iv2);
            it$iv2 = value$iv2;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        EffectsKt.LaunchedEffect(boolValueOf, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) it$iv2, $composer, ($changed >> 3) & 14);
        State<Float> stateAsState = scale.asState();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return stateAsState;
    }
}
