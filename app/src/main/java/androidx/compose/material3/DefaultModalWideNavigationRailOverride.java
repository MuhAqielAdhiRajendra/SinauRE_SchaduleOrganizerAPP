package androidx.compose.material3;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimatableKt;
import androidx.compose.animation.core.AnimateAsStateKt;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.layout.WindowInsetsPadding_androidKt;
import androidx.compose.material3.tokens.MotionSchemeKeyTokens;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MovableContentKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.unit.Density;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelKt;

/* JADX INFO: compiled from: WideNavigationRail.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\bÁ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0011\u0010\u0004\u001a\u00020\u0005*\u00020\u0006H\u0017¢\u0006\u0002\u0010\u0007¨\u0006\b²\u0006\n\u0010\t\u001a\u00020\nX\u008a\u0084\u0002²\u0006\n\u0010\u000b\u001a\u00020\nX\u008a\u0084\u0002"}, d2 = {"Landroidx/compose/material3/DefaultModalWideNavigationRailOverride;", "Landroidx/compose/material3/ModalWideNavigationRailOverride;", "<init>", "()V", "ModalWideNavigationRail", "", "Landroidx/compose/material3/ModalWideNavigationRailOverrideScope;", "(Landroidx/compose/material3/ModalWideNavigationRailOverrideScope;Landroidx/compose/runtime/Composer;I)V", "material3", "isCollapsed", "", "modalExpanded"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class DefaultModalWideNavigationRailOverride implements ModalWideNavigationRailOverride {
    public static final int $stable = 0;
    public static final DefaultModalWideNavigationRailOverride INSTANCE = new DefaultModalWideNavigationRailOverride();

    static final Unit ModalWideNavigationRail$lambda$23(DefaultModalWideNavigationRailOverride defaultModalWideNavigationRailOverride, ModalWideNavigationRailOverrideScope modalWideNavigationRailOverrideScope, int i, Composer composer, int i2) {
        defaultModalWideNavigationRailOverride.ModalWideNavigationRail(modalWideNavigationRailOverrideScope, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    private DefaultModalWideNavigationRailOverride() {
    }

    /* JADX WARN: Type inference failed for: r5v28 */
    /* JADX WARN: Type inference failed for: r5v8 */
    /* JADX WARN: Type inference failed for: r5v9, types: [java.lang.Object, kotlin.coroutines.Continuation, kotlin.jvm.functions.Function1, kotlinx.coroutines.channels.BufferOverflow] */
    @Override // androidx.compose.material3.ModalWideNavigationRailOverride
    public void ModalWideNavigationRail(final ModalWideNavigationRailOverrideScope $this$ModalWideNavigationRail, Composer $composer, final int $changed) {
        final ModalWideNavigationRailOverrideScope modalWideNavigationRailOverrideScope;
        Function2<Composer, Integer, Unit> content;
        Function1 animateToDismiss;
        ?? r5;
        Function2 settleToDismiss;
        int $dirty;
        ModalWideNavigationRailState modalState;
        State modalExpanded$delegate;
        Function0<ComposeUiNode> function0;
        Composer $composer2 = $composer.startRestartGroup(1751235721);
        ComposerKt.sourceInformation($composer2, "C(ModalWideNavigationRail)509@25093L7,511@25254L14,513@25306L230,524@25833L7,521@25580L275,526@25892L60,527@25991L62,528@26105L132,535@26304L176,557@27012L48:WideNavigationRail.kt#uh7d8r");
        int $dirty2 = $changed;
        if (($changed & 6) == 0) {
            $dirty2 |= ($changed & 8) == 0 ? $composer2.changed($this$ModalWideNavigationRail) : $composer2.changedInstance($this$ModalWideNavigationRail) ? 4 : 2;
        }
        if ($composer2.shouldExecute(($dirty2 & 3) != 2, $dirty2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1751235721, $dirty2, -1, "androidx.compose.material3.DefaultModalWideNavigationRailOverride.ModalWideNavigationRail (WideNavigationRail.kt:503)");
            }
            if ($this$ModalWideNavigationRail.getShouldHideOnCollapse()) {
                $composer2.startReplaceGroup(95781714);
                $composer2.endReplaceGroup();
                content = $this$ModalWideNavigationRail.getContent();
            } else {
                $composer2.startReplaceGroup(95826602);
                ComposerKt.sourceInformation($composer2, "507@25009L47");
                Function2<Composer, Integer, Unit> content2 = $this$ModalWideNavigationRail.getContent();
                ComposerKt.sourceInformationMarkerStart($composer2, -689645480, "CC(remember):WideNavigationRail.kt#9igjgp");
                boolean invalid$iv = $composer2.changed(content2);
                Object it$iv = $composer2.rememberedValue();
                if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                    Object value$iv = MovableContentKt.movableContentOf($this$ModalWideNavigationRail.getContent());
                    $composer2.updateRememberedValue(value$iv);
                    it$iv = value$iv;
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endReplaceGroup();
                content = (Function2) it$iv;
            }
            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = $composer2.consume(localDensity);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Density density = (Density) objConsume;
            FiniteAnimationSpec modalStateAnimationSpec = MotionSchemeKt.value(MotionSchemeKeyTokens.DefaultSpatial, $composer2, 6);
            WideNavigationRailState state = $this$ModalWideNavigationRail.getState();
            ComposerKt.sourceInformationMarkerStart($composer2, -689635793, "CC(remember):WideNavigationRail.kt#9igjgp");
            boolean invalid$iv2 = $composer2.changed(state);
            Object it$iv2 = $composer2.rememberedValue();
            if (invalid$iv2 || it$iv2 == Composer.INSTANCE.getEmpty()) {
                Object value$iv2 = new ModalWideNavigationRailState($this$ModalWideNavigationRail.getState(), density, modalStateAnimationSpec);
                $composer2.updateRememberedValue(value$iv2);
                it$iv2 = value$iv2;
            }
            ModalWideNavigationRailState modalState2 = (ModalWideNavigationRailState) it$iv2;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            final State<Float> stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(!WideNavigationRailStateKt.isExpanded($this$ModalWideNavigationRail.getState().getTargetValue()) ? 0.0f : 1.0f, MotionSchemeKt.value(MotionSchemeKeyTokens.DefaultEffects, $composer2, 6), 0.0f, null, null, $composer2, 0, 28);
            ComposerKt.sourceInformationMarkerStart($composer2, -689617211, "CC(remember):WideNavigationRail.kt#9igjgp");
            Object it$iv3 = $composer2.rememberedValue();
            if (it$iv3 == Composer.INSTANCE.getEmpty()) {
                Object value$iv3 = SnapshotStateKt.derivedStateOf(new Function0() { // from class: androidx.compose.material3.DefaultModalWideNavigationRailOverride$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return Boolean.valueOf(DefaultModalWideNavigationRailOverride.ModalWideNavigationRail$lambda$3$lambda$2(stateAnimateFloatAsState));
                    }
                });
                $composer2.updateRememberedValue(value$iv3);
                it$iv3 = value$iv3;
            }
            State isCollapsed$delegate = (State) it$iv3;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerStart($composer2, -689614041, "CC(remember):WideNavigationRail.kt#9igjgp");
            Object it$iv4 = $composer2.rememberedValue();
            if (it$iv4 == Composer.INSTANCE.getEmpty()) {
                Object value$iv4 = SnapshotStateKt.derivedStateOf(new Function0() { // from class: androidx.compose.material3.DefaultModalWideNavigationRailOverride$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return Boolean.valueOf(DefaultModalWideNavigationRailOverride.ModalWideNavigationRail$lambda$6$lambda$5(stateAnimateFloatAsState));
                    }
                });
                $composer2.updateRememberedValue(value$iv4);
                it$iv4 = value$iv4;
            }
            State modalExpanded$delegate2 = (State) it$iv4;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerStart($composer2, -689610323, "CC(remember):WideNavigationRail.kt#9igjgp");
            boolean invalid$iv3 = (($dirty2 & 14) == 4 || (($dirty2 & 8) != 0 && $composer2.changedInstance($this$ModalWideNavigationRail))) | $composer2.changedInstance(modalState2);
            Object it$iv5 = $composer2.rememberedValue();
            if (invalid$iv3 || it$iv5 == Composer.INSTANCE.getEmpty()) {
                Object value$iv5 = (Function1) new DefaultModalWideNavigationRailOverride$ModalWideNavigationRail$animateToDismiss$1$1($this$ModalWideNavigationRail, modalState2, null);
                $composer2.updateRememberedValue(value$iv5);
                it$iv5 = value$iv5;
            }
            Function1 animateToDismiss2 = (Function1) it$iv5;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerStart($composer2, -689603911, "CC(remember):WideNavigationRail.kt#9igjgp");
            boolean invalid$iv4 = (($dirty2 & 14) == 4 || (($dirty2 & 8) != 0 && $composer2.changedInstance($this$ModalWideNavigationRail))) | $composer2.changedInstance(modalState2);
            Object it$iv6 = $composer2.rememberedValue();
            if (invalid$iv4 || it$iv6 == Composer.INSTANCE.getEmpty()) {
                animateToDismiss = animateToDismiss2;
                Object value$iv6 = (Function2) new DefaultModalWideNavigationRailOverride$ModalWideNavigationRail$settleToDismiss$1$1($this$ModalWideNavigationRail, modalState2, null);
                $composer2.updateRememberedValue(value$iv6);
                it$iv6 = value$iv6;
            } else {
                animateToDismiss = animateToDismiss2;
            }
            Function2 settleToDismiss2 = (Function2) it$iv6;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if ($this$ModalWideNavigationRail.getShouldHideOnCollapse() || !ModalWideNavigationRail$lambda$4(isCollapsed$delegate)) {
                r5 = 0;
                $composer2.startReplaceGroup(97788313);
                $composer2.endReplaceGroup();
            } else {
                $composer2.startReplaceGroup(97400069);
                ComposerKt.sourceInformation($composer2, "544@26598L380");
                r5 = 0;
                WideNavigationRailKt.WideNavigationRailLayout($this$ModalWideNavigationRail.getModifier(), false, false, $this$ModalWideNavigationRail.getColors(), $this$ModalWideNavigationRail.getCollapsedShape(), $this$ModalWideNavigationRail.getHeader(), $this$ModalWideNavigationRail.getWindowInsets(), $this$ModalWideNavigationRail.getArrangement(), content, $composer2, 432);
                $composer2 = $composer2;
                $composer2.endReplaceGroup();
            }
            ComposerKt.sourceInformationMarkerStart($composer2, -689581383, "CC(remember):WideNavigationRail.kt#9igjgp");
            Composer $this$cache$iv = $composer2;
            Object it$iv7 = $this$cache$iv.rememberedValue();
            if (it$iv7 == Composer.INSTANCE.getEmpty()) {
                settleToDismiss = settleToDismiss2;
                $dirty = $dirty2;
                Object value$iv7 = ChannelKt.Channel$default(-1, r5, r5, 6, r5);
                $this$cache$iv.updateRememberedValue(value$iv7);
                it$iv7 = value$iv7;
            } else {
                settleToDismiss = settleToDismiss2;
                $dirty = $dirty2;
            }
            final Channel channel = (Channel) it$iv7;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if ($this$ModalWideNavigationRail.getShouldHideOnCollapse()) {
                $composer2.startReplaceGroup(97908438);
                ComposerKt.sourceInformation($composer2, "559@27133L403,559@27109L427");
                ComposerKt.sourceInformationMarkerStart($composer2, -689577156, "CC(remember):WideNavigationRail.kt#9igjgp");
                boolean invalid$iv5 = $composer2.changedInstance(channel) | $composer2.changedInstance(modalState2);
                Composer $this$cache$iv2 = $composer2;
                Object it$iv8 = $this$cache$iv2.rememberedValue();
                if (invalid$iv5 || it$iv8 == Composer.INSTANCE.getEmpty()) {
                    Object value$iv8 = (Function2) new DefaultModalWideNavigationRailOverride$ModalWideNavigationRail$1$1(channel, modalState2, r5);
                    $this$cache$iv2.updateRememberedValue(value$iv8);
                    it$iv8 = value$iv8;
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                EffectsKt.LaunchedEffect(channel, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) it$iv8, $composer2, 0);
                $composer2.endReplaceGroup();
            } else {
                $composer2.startReplaceGroup(98341849);
                $composer2.endReplaceGroup();
            }
            if (ModalWideNavigationRail$lambda$4(isCollapsed$delegate)) {
                modalWideNavigationRailOverrideScope = $this$ModalWideNavigationRail;
                $composer2.startReplaceGroup(101334713);
                $composer2.endReplaceGroup();
            } else {
                $composer2.startReplaceGroup(98512256);
                ComposerKt.sourceInformation($composer2, "583@28043L24,584@28109L42,585@28190L38,587@28253L49,587@28242L60,591@28431L37,592@28505L110,595@28661L93,599@28829L1724,589@28316L2237");
                if ($this$ModalWideNavigationRail.getShouldHideOnCollapse()) {
                    modalState = modalState2;
                    modalExpanded$delegate = modalExpanded$delegate2;
                    $composer2.startReplaceGroup(98809081);
                    $composer2.endReplaceGroup();
                } else {
                    $composer2.startReplaceGroup(98472731);
                    ComposerKt.sourceInformation($composer2, "578@27806L197");
                    Modifier modifier$iv = BackgroundKt.m285backgroundbw27NRU(Modifier.INSTANCE, $this$ModalWideNavigationRail.getColors().getContainerColor(), $this$ModalWideNavigationRail.getCollapsedShape());
                    ComposerKt.sourceInformationMarkerStart($composer2, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
                    Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                    MeasurePolicy measurePolicy$iv = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
                    int $changed$iv$iv = (0 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                    int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
                    CompositionLocalMap localMap$iv$iv = $composer2.getCurrentCompositionLocalMap();
                    modalState = modalState2;
                    Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer2, modifier$iv);
                    Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                    int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
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
                    Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4433constructorimpl($composer2);
                    Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if ($this$Layout_u24lambda_u240$iv$iv.getInserting()) {
                        modalExpanded$delegate = modalExpanded$delegate2;
                    } else {
                        modalExpanded$delegate = modalExpanded$delegate2;
                        if (!Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                        }
                        Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
                        int i = ($changed$iv$iv$iv >> 6) & 14;
                        Composer $composer$iv = $composer2;
                        ComposerKt.sourceInformationMarkerStart($composer$iv, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                        int i2 = ((0 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer$iv, -1258502535, "C579@27908L77:WideNavigationRail.kt#uh7d8r");
                        SpacerKt.Spacer(SizeKt.fillMaxHeight$default(SizeKt.m1122widthInVpY3zN4$default($this$ModalWideNavigationRail.getModifier(), WideNavigationRailKt.CollapsedRailWidth, 0.0f, 2, null), 0.0f, 1, null), $composer$iv, 0);
                        ComposerKt.sourceInformationMarkerEnd($composer$iv);
                        ComposerKt.sourceInformationMarkerEnd($composer$iv);
                        $composer2.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        $composer2.endReplaceGroup();
                    }
                    $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                    $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
                    Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
                    int i3 = ($changed$iv$iv$iv >> 6) & 14;
                    Composer $composer$iv2 = $composer2;
                    ComposerKt.sourceInformationMarkerStart($composer$iv2, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                    BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
                    int i22 = ((0 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer$iv2, -1258502535, "C579@27908L77:WideNavigationRail.kt#uh7d8r");
                    SpacerKt.Spacer(SizeKt.fillMaxHeight$default(SizeKt.m1122widthInVpY3zN4$default($this$ModalWideNavigationRail.getModifier(), WideNavigationRailKt.CollapsedRailWidth, 0.0f, 2, null), 0.0f, 1, null), $composer$iv2, 0);
                    ComposerKt.sourceInformationMarkerEnd($composer$iv2);
                    ComposerKt.sourceInformationMarkerEnd($composer$iv2);
                    $composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $composer2.endReplaceGroup();
                }
                ComposerKt.sourceInformationMarkerStart($composer2, 773894976, "CC(rememberCoroutineScope)N(getContext)608@27648L68:Effects.kt#9igjgp");
                Composer composer$iv = $composer2;
                ComposerKt.sourceInformationMarkerStart($composer2, 683737348, "CC(remember):Effects.kt#9igjgp");
                Composer $this$cache$iv$iv = $composer2;
                Object it$iv$iv = $this$cache$iv$iv.rememberedValue();
                if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                    Object value$iv$iv = EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composer$iv);
                    $this$cache$iv$iv.updateRememberedValue(value$iv$iv);
                    it$iv$iv = value$iv$iv;
                }
                final CoroutineScope scope = (CoroutineScope) it$iv$iv;
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerStart($composer2, -689546285, "CC(remember):WideNavigationRail.kt#9igjgp");
                Composer $this$cache$iv3 = $composer2;
                Object it$iv9 = $this$cache$iv3.rememberedValue();
                if (it$iv9 == Composer.INSTANCE.getEmpty()) {
                    Object value$iv9 = AnimatableKt.Animatable$default(0.0f, 0.0f, 2, null);
                    $this$cache$iv3.updateRememberedValue(value$iv9);
                    it$iv9 = value$iv9;
                }
                final Animatable predictiveBackProgress = (Animatable) it$iv9;
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerStart($composer2, -689543697, "CC(remember):WideNavigationRail.kt#9igjgp");
                Composer $this$cache$iv4 = $composer2;
                Object it$iv10 = $this$cache$iv4.rememberedValue();
                if (it$iv10 == Composer.INSTANCE.getEmpty()) {
                    Object value$iv10 = new RailPredictiveBackState();
                    $this$cache$iv4.updateRememberedValue(value$iv10);
                    it$iv10 = value$iv10;
                }
                final RailPredictiveBackState predictiveBackState = (RailPredictiveBackState) it$iv10;
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerStart($composer2, -689541670, "CC(remember):WideNavigationRail.kt#9igjgp");
                boolean invalid$iv6 = $composer2.changedInstance(channel) | (($dirty & 14) == 4 || (($dirty & 8) != 0 && $composer2.changedInstance($this$ModalWideNavigationRail)));
                Composer $this$cache$iv5 = $composer2;
                Object it$iv11 = $this$cache$iv5.rememberedValue();
                if (invalid$iv6 || it$iv11 == Composer.INSTANCE.getEmpty()) {
                    Object value$iv11 = new Function0() { // from class: androidx.compose.material3.DefaultModalWideNavigationRailOverride$$ExternalSyntheticLambda2
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return DefaultModalWideNavigationRailOverride.ModalWideNavigationRail$lambda$16$lambda$15(channel, $this$ModalWideNavigationRail);
                        }
                    };
                    $this$cache$iv5.updateRememberedValue(value$iv11);
                    it$iv11 = value$iv11;
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                EffectsKt.SideEffect((Function0) it$iv11, $composer2, 0);
                ModalWideNavigationRailProperties expandedProperties = $this$ModalWideNavigationRail.getExpandedProperties();
                ComposerKt.sourceInformationMarkerStart($composer2, -689535986, "CC(remember):WideNavigationRail.kt#9igjgp");
                boolean invalid$iv7 = $composer2.changedInstance(scope) | (($dirty & 14) == 4 || (($dirty & 8) != 0 && $composer2.changedInstance($this$ModalWideNavigationRail)));
                Composer $this$cache$iv6 = $composer2;
                Object it$iv12 = $this$cache$iv6.rememberedValue();
                if (invalid$iv7 || it$iv12 == Composer.INSTANCE.getEmpty()) {
                    Object value$iv12 = new Function0() { // from class: androidx.compose.material3.DefaultModalWideNavigationRailOverride$$ExternalSyntheticLambda3
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return DefaultModalWideNavigationRailOverride.ModalWideNavigationRail$lambda$18$lambda$17(scope, $this$ModalWideNavigationRail);
                        }
                    };
                    $this$cache$iv6.updateRememberedValue(value$iv12);
                    it$iv12 = value$iv12;
                }
                Function0 function02 = (Function0) it$iv12;
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerStart($composer2, -689533545, "CC(remember):WideNavigationRail.kt#9igjgp");
                boolean invalid$iv8 = $composer2.changedInstance(scope) | $composer2.changedInstance(predictiveBackProgress);
                Composer $this$cache$iv7 = $composer2;
                Object it$iv13 = $this$cache$iv7.rememberedValue();
                if (invalid$iv8 || it$iv13 == Composer.INSTANCE.getEmpty()) {
                    Object value$iv13 = new Function1() { // from class: androidx.compose.material3.DefaultModalWideNavigationRailOverride$$ExternalSyntheticLambda4
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return DefaultModalWideNavigationRailOverride.ModalWideNavigationRail$lambda$20$lambda$19(scope, predictiveBackProgress, ((Float) obj).floatValue());
                        }
                    };
                    $this$cache$iv7.updateRememberedValue(value$iv13);
                    it$iv13 = value$iv13;
                }
                Function1 function1 = (Function1) it$iv13;
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerStart($composer2, -689528570, "CC(remember):WideNavigationRail.kt#9igjgp");
                boolean invalid$iv9 = $composer2.changedInstance(scope) | $composer2.changedInstance(predictiveBackProgress);
                Composer $this$cache$iv8 = $composer2;
                Object it$iv14 = $this$cache$iv8.rememberedValue();
                if (invalid$iv9 || it$iv14 == Composer.INSTANCE.getEmpty()) {
                    Object value$iv14 = new Function0() { // from class: androidx.compose.material3.DefaultModalWideNavigationRailOverride$$ExternalSyntheticLambda5
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return DefaultModalWideNavigationRailOverride.ModalWideNavigationRail$lambda$22$lambda$21(scope, predictiveBackProgress);
                        }
                    };
                    $this$cache$iv8.updateRememberedValue(value$iv14);
                    it$iv14 = value$iv14;
                }
                Function0 function03 = (Function0) it$iv14;
                ComposerKt.sourceInformationMarkerEnd($composer2);
                final Function2 settleToDismiss3 = settleToDismiss;
                final Function2<Composer, Integer, Unit> function2 = content;
                final ModalWideNavigationRailState modalState3 = modalState;
                final Function1 animateToDismiss3 = animateToDismiss;
                final State positionProgress = modalExpanded$delegate;
                modalWideNavigationRailOverrideScope = $this$ModalWideNavigationRail;
                WideNavigationRail_androidKt.ModalWideNavigationRailDialog(function02, expandedProperties, function1, function03, predictiveBackState, ComposableLambdaKt.rememberComposableLambda(1345045690, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DefaultModalWideNavigationRailOverride.ModalWideNavigationRail.7
                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                        invoke(composer, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer $composer3, int $changed2) {
                        Function0<ComposeUiNode> function04;
                        boolean isScrimVisible;
                        ComposerKt.sourceInformation($composer3, "C600@28847L1692:WideNavigationRail.kt#uh7d8r");
                        if (!$composer3.shouldExecute(($changed2 & 3) != 2, $changed2 & 1)) {
                            $composer3.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1345045690, $changed2, -1, "androidx.compose.material3.DefaultModalWideNavigationRailOverride.ModalWideNavigationRail.<anonymous> (WideNavigationRail.kt:600)");
                        }
                        Modifier modifier$iv2 = WindowInsetsPadding_androidKt.imePadding(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null));
                        final ModalWideNavigationRailOverrideScope modalWideNavigationRailOverrideScope2 = $this$ModalWideNavigationRail;
                        ModalWideNavigationRailState modalWideNavigationRailState = modalState3;
                        Function1<Continuation<? super Unit>, Object> function12 = animateToDismiss3;
                        Animatable<Float, AnimationVector1D> animatable = predictiveBackProgress;
                        RailPredictiveBackState railPredictiveBackState = predictiveBackState;
                        Function2<Float, Continuation<? super Unit>, Object> function22 = settleToDismiss3;
                        Function2<Composer, Integer, Unit> function23 = function2;
                        State<Boolean> state2 = positionProgress;
                        ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
                        Alignment contentAlignment$iv2 = Alignment.INSTANCE.getTopStart();
                        MeasurePolicy measurePolicy$iv2 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv2, false);
                        int $changed$iv$iv2 = (0 << 3) & 112;
                        ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                        int compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                        CompositionLocalMap localMap$iv$iv2 = $composer3.getCurrentCompositionLocalMap();
                        Modifier materialized$iv$iv2 = ComposedModifierKt.materializeModifier($composer3, modifier$iv2);
                        Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                        int $changed$iv$iv$iv2 = (($changed$iv$iv2 << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                        if (!($composer3.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer3.startReusableNode();
                        if ($composer3.getInserting()) {
                            function04 = constructor2;
                            $composer3.createNode(function04);
                        } else {
                            function04 = constructor2;
                            $composer3.useNode();
                        }
                        Composer $this$Layout_u24lambda_u240$iv$iv2 = Updater.m4433constructorimpl($composer3);
                        Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if ($this$Layout_u24lambda_u240$iv$iv2.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                            $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
                            $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash2);
                        }
                        Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv2, ComposeUiNode.INSTANCE.getSetModifier());
                        int i4 = ($changed$iv$iv$iv2 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer3, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                        BoxScopeInstance boxScopeInstance3 = BoxScopeInstance.INSTANCE;
                        int i5 = ((0 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer3, 238599055, "C608@29206L195,625@30078L201,614@29423L1098:WideNavigationRail.kt#uh7d8r");
                        if (!modalWideNavigationRailOverrideScope2.getShouldHideOnCollapse()) {
                            isScrimVisible = DefaultModalWideNavigationRailOverride.ModalWideNavigationRail$lambda$7(state2);
                        } else {
                            isScrimVisible = modalWideNavigationRailState.getTargetValue() != WideNavigationRailValue.Collapsed;
                        }
                        WideNavigationRailKt.m3398Scrim3JVO9M(modalWideNavigationRailOverrideScope2.getColors().getModalScrimColor(), function12, isScrimVisible, $composer3, 0);
                        WideNavigationRailKt.m3397ModalWideNavigationRailContentpU6N4AM(modalWideNavigationRailOverrideScope2.getShouldHideOnCollapse() || DefaultModalWideNavigationRailOverride.ModalWideNavigationRail$lambda$7(state2), modalWideNavigationRailOverrideScope2.getShouldHideOnCollapse(), animatable, railPredictiveBackState, function22, modalWideNavigationRailOverrideScope2.getModifier(), modalWideNavigationRailState, modalWideNavigationRailOverrideScope2.getColors(), modalWideNavigationRailOverrideScope2.getExpandedShape(), WideNavigationRailKt.ExpandedRailMaxWidth, ComposableLambdaKt.rememberComposableLambda(208840989, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DefaultModalWideNavigationRailOverride$ModalWideNavigationRail$7$1$1
                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                                invoke(composer, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer $composer4, int $changed3) {
                                Function0<ComposeUiNode> function05;
                                ComposerKt.sourceInformation($composer4, "C626@30108L145:WideNavigationRail.kt#uh7d8r");
                                if (!$composer4.shouldExecute(($changed3 & 3) != 2, $changed3 & 1)) {
                                    $composer4.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(208840989, $changed3, -1, "androidx.compose.material3.DefaultModalWideNavigationRailOverride.ModalWideNavigationRail.<anonymous>.<anonymous>.<anonymous> (WideNavigationRail.kt:626)");
                                }
                                Modifier modifier$iv3 = PaddingKt.m1052paddingqDBjuR0$default(Modifier.INSTANCE, 0.0f, modalWideNavigationRailOverrideScope2.getExpandedHeaderTopPadding(), 0.0f, 0.0f, 13, null);
                                ModalWideNavigationRailOverrideScope modalWideNavigationRailOverrideScope3 = modalWideNavigationRailOverrideScope2;
                                ComposerKt.sourceInformationMarkerStart($composer4, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
                                Alignment contentAlignment$iv3 = Alignment.INSTANCE.getTopStart();
                                MeasurePolicy measurePolicy$iv3 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv3, false);
                                int $changed$iv$iv3 = (0 << 3) & 112;
                                ComposerKt.sourceInformationMarkerStart($composer4, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                                int compositeKeyHash$iv$iv3 = ComposablesKt.getCurrentCompositeKeyHash($composer4, 0);
                                CompositionLocalMap localMap$iv$iv3 = $composer4.getCurrentCompositionLocalMap();
                                Modifier materialized$iv$iv3 = ComposedModifierKt.materializeModifier($composer4, modifier$iv3);
                                Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
                                int $changed$iv$iv$iv3 = (($changed$iv$iv3 << 6) & 896) | 6;
                                ComposerKt.sourceInformationMarkerStart($composer4, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                                if (!($composer4.getApplier() instanceof Applier)) {
                                    ComposablesKt.invalidApplier();
                                }
                                $composer4.startReusableNode();
                                if ($composer4.getInserting()) {
                                    function05 = constructor3;
                                    $composer4.createNode(function05);
                                } else {
                                    function05 = constructor3;
                                    $composer4.useNode();
                                }
                                Composer $this$Layout_u24lambda_u240$iv$iv3 = Updater.m4433constructorimpl($composer4);
                                Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv3, measurePolicy$iv3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv3, localMap$iv$iv3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash3 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                if ($this$Layout_u24lambda_u240$iv$iv3.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv3.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv3))) {
                                    $this$Layout_u24lambda_u240$iv$iv3.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv3));
                                    $this$Layout_u24lambda_u240$iv$iv3.apply(Integer.valueOf(compositeKeyHash$iv$iv3), setCompositeKeyHash3);
                                }
                                Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv3, materialized$iv$iv3, ComposeUiNode.INSTANCE.getSetModifier());
                                int i6 = ($changed$iv$iv$iv3 >> 6) & 14;
                                ComposerKt.sourceInformationMarkerStart($composer4, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                                BoxScopeInstance boxScopeInstance4 = BoxScopeInstance.INSTANCE;
                                int i7 = ((0 >> 6) & 112) | 6;
                                ComposerKt.sourceInformationMarkerStart($composer4, -1627808976, "C:WideNavigationRail.kt#uh7d8r");
                                Function2<Composer, Integer, Unit> header = modalWideNavigationRailOverrideScope3.getHeader();
                                if (header == null) {
                                    $composer4.startReplaceGroup(-1627801290);
                                } else {
                                    $composer4.startReplaceGroup(-2130719701);
                                    ComposerKt.sourceInformation($composer4, "627@30215L8");
                                    header.invoke($composer4, 0);
                                }
                                $composer4.endReplaceGroup();
                                ComposerKt.sourceInformationMarkerEnd($composer4);
                                ComposerKt.sourceInformationMarkerEnd($composer4);
                                $composer4.endNode();
                                ComposerKt.sourceInformationMarkerEnd($composer4);
                                ComposerKt.sourceInformationMarkerEnd($composer4);
                                ComposerKt.sourceInformationMarkerEnd($composer4);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }, $composer3, 54), modalWideNavigationRailOverrideScope2.getWindowInsets(), modalWideNavigationRailOverrideScope2.getShouldHideOnCollapse(), modalWideNavigationRailOverrideScope2.getArrangement(), function23, $composer3, (Animatable.$stable << 6) | 805309440, 6);
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
                }, $composer2, 54), $composer2, 221184);
                $composer2.endReplaceGroup();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            modalWideNavigationRailOverrideScope = $this$ModalWideNavigationRail;
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.DefaultModalWideNavigationRailOverride$$ExternalSyntheticLambda6
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return DefaultModalWideNavigationRailOverride.ModalWideNavigationRail$lambda$23(this.f$0, modalWideNavigationRailOverrideScope, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    static final boolean ModalWideNavigationRail$lambda$3$lambda$2(State $positionProgress) {
        return ((Number) $positionProgress.getValue()).floatValue() == 0.0f;
    }

    private static final boolean ModalWideNavigationRail$lambda$4(State<Boolean> state) {
        Object thisObj$iv = state.getValue();
        return ((Boolean) thisObj$iv).booleanValue();
    }

    static final boolean ModalWideNavigationRail$lambda$6$lambda$5(State $positionProgress) {
        return ((Number) $positionProgress.getValue()).floatValue() >= 0.3f;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean ModalWideNavigationRail$lambda$7(State<Boolean> state) {
        Object thisObj$iv = state.getValue();
        return ((Boolean) thisObj$iv).booleanValue();
    }

    static final Unit ModalWideNavigationRail$lambda$16$lambda$15(Channel $channel, ModalWideNavigationRailOverrideScope $this_ModalWideNavigationRail) {
        $channel.mo10436trySendJP2dKIU(Boolean.valueOf(WideNavigationRailStateKt.isExpanded($this_ModalWideNavigationRail.getState().getTargetValue())));
        return Unit.INSTANCE;
    }

    static final Unit ModalWideNavigationRail$lambda$18$lambda$17(CoroutineScope $scope, ModalWideNavigationRailOverrideScope $this_ModalWideNavigationRail) {
        BuildersKt__Builders_commonKt.launch$default($scope, null, null, new DefaultModalWideNavigationRailOverride$ModalWideNavigationRail$4$1$1($this_ModalWideNavigationRail, null), 3, null);
        return Unit.INSTANCE;
    }

    static final Unit ModalWideNavigationRail$lambda$20$lambda$19(CoroutineScope $scope, Animatable $predictiveBackProgress, float backEvent) {
        BuildersKt__Builders_commonKt.launch$default($scope, null, null, new DefaultModalWideNavigationRailOverride$ModalWideNavigationRail$5$1$1($predictiveBackProgress, backEvent, null), 3, null);
        return Unit.INSTANCE;
    }

    static final Unit ModalWideNavigationRail$lambda$22$lambda$21(CoroutineScope $scope, Animatable $predictiveBackProgress) {
        BuildersKt__Builders_commonKt.launch$default($scope, null, null, new DefaultModalWideNavigationRailOverride$ModalWideNavigationRail$6$1$1($predictiveBackProgress, null), 3, null);
        return Unit.INSTANCE;
    }
}
