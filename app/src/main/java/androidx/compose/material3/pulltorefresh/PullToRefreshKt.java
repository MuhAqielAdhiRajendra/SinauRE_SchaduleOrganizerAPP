package androidx.compose.material3.pulltorefresh;

import androidx.compose.animation.core.AnimateAsStateKt;
import androidx.compose.foundation.CanvasKt;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScope;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.material3.MotionSchemeKt;
import androidx.compose.material3.internal.FloatProducer;
import androidx.compose.material3.tokens.MotionSchemeKeyTokens;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.RectKt;
import androidx.compose.ui.graphics.AndroidPath_androidKt;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.PathFillType;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.drawscope.DrawContext;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.DrawTransform;
import androidx.compose.ui.graphics.drawscope.Stroke;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.semantics.ProgressBarRangeInfo;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.Dp;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.profileinstaller.ProfileVerifier;
import com.google.android.material.internal.ViewUtils;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: PullToRefresh.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000r\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0013\u001a\u007f\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\u001e\b\u0002\u0010\f\u001a\u0018\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00010\r¢\u0006\u0002\b\u000f¢\u0006\u0002\b\u00102\u001c\u0010\u0011\u001a\u0018\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00010\r¢\u0006\u0002\b\u000f¢\u0006\u0002\b\u0010H\u0007¢\u0006\u0002\u0010\u0012\u001aC\u0010\u0013\u001a\u00020\u0007*\u00020\u00072\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\u0014\u001a\u00020\u00032\b\b\u0002\u0010\u0015\u001a\u00020\u00162\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0004\b\u0017\u0010\u0018\u001a\r\u0010\u0019\u001a\u00020\tH\u0007¢\u0006\u0002\u0010\u001a\u001a\b\u0010\u001b\u001a\u00020\tH\u0007\u001a\u001f\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 H\u0003¢\u0006\u0004\b!\u0010\"\u001a;\u0010#\u001a\u00020\u0001*\u00020$2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020\u0016H\u0002¢\u0006\u0004\b,\u0010-\u001a\u0010\u0010.\u001a\u00020(2\u0006\u0010\u001d\u001a\u00020&H\u0002\u001aC\u0010/\u001a\u00020\u0001*\u00020$2\u0006\u00100\u001a\u0002012\u0006\u00102\u001a\u00020*2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020(2\u0006\u0010+\u001a\u00020\u0016H\u0002¢\u0006\u0004\b3\u00104\"\u000e\u00105\u001a\u00020&X\u0082T¢\u0006\u0002\n\u0000\"\u0010\u00106\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0004\n\u0002\u00107\"\u0010\u00108\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0004\n\u0002\u00107\"\u0016\u00109\u001a\u00020\u0016X\u0080\u0004¢\u0006\n\n\u0002\u00107\u001a\u0004\b:\u0010;\"\u0016\u0010<\u001a\u00020\u0016X\u0080\u0004¢\u0006\n\n\u0002\u00107\u001a\u0004\b=\u0010;\"\u0010\u0010>\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0004\n\u0002\u00107\"\u0010\u0010?\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0004\n\u0002\u00107\"\u000e\u0010@\u001a\u00020&X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010A\u001a\u00020&X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010B\u001a\u00020&X\u0082T¢\u0006\u0002\n\u0000¨\u0006C²\u0006\n\u0010D\u001a\u00020&X\u008a\u0084\u0002"}, d2 = {"PullToRefreshBox", "", "isRefreshing", "", "onRefresh", "Lkotlin/Function0;", "modifier", "Landroidx/compose/ui/Modifier;", "state", "Landroidx/compose/material3/pulltorefresh/PullToRefreshState;", "contentAlignment", "Landroidx/compose/ui/Alignment;", "indicator", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/BoxScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "content", "(ZLkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;Landroidx/compose/material3/pulltorefresh/PullToRefreshState;Landroidx/compose/ui/Alignment;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "pullToRefresh", "enabled", "threshold", "Landroidx/compose/ui/unit/Dp;", "pullToRefresh-Z4HSEVQ", "(Landroidx/compose/ui/Modifier;ZLandroidx/compose/material3/pulltorefresh/PullToRefreshState;ZFLkotlin/jvm/functions/Function0;)Landroidx/compose/ui/Modifier;", "rememberPullToRefreshState", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/material3/pulltorefresh/PullToRefreshState;", "PullToRefreshState", "CircularArrowProgressIndicator", "progress", "Landroidx/compose/material3/internal/FloatProducer;", TypedValues.Custom.S_COLOR, "Landroidx/compose/ui/graphics/Color;", "CircularArrowProgressIndicator-RPmYEkk", "(Landroidx/compose/material3/internal/FloatProducer;JLandroidx/compose/runtime/Composer;I)V", "drawCircularIndicator", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "alpha", "", "values", "Landroidx/compose/material3/pulltorefresh/ArrowValues;", "arcBounds", "Landroidx/compose/ui/geometry/Rect;", "strokeWidth", "drawCircularIndicator-KzyDr3Q", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;JFLandroidx/compose/material3/pulltorefresh/ArrowValues;Landroidx/compose/ui/geometry/Rect;F)V", "ArrowValues", "drawArrow", "arrow", "Landroidx/compose/ui/graphics/Path;", "bounds", "drawArrow-uDrxG_w", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;Landroidx/compose/ui/graphics/Path;Landroidx/compose/ui/geometry/Rect;JFLandroidx/compose/material3/pulltorefresh/ArrowValues;F)V", "MaxProgressArc", "StrokeWidth", "F", "ArcRadius", "SpinnerSize", "getSpinnerSize", "()F", "SpinnerContainerSize", "getSpinnerContainerSize", "ArrowWidth", "ArrowHeight", "MinAlpha", "MaxAlpha", "DragMultiplier", "material3", "targetAlpha"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class PullToRefreshKt {
    private static final float DragMultiplier = 0.5f;
    private static final float MaxAlpha = 1.0f;
    private static final float MaxProgressArc = 0.8f;
    private static final float MinAlpha = 0.3f;
    private static final float StrokeWidth = Dp.m8150constructorimpl((float) 2.5d);
    private static final float ArcRadius = Dp.m8150constructorimpl((float) 5.5d);
    private static final float SpinnerSize = Dp.m8150constructorimpl(16);
    private static final float SpinnerContainerSize = Dp.m8150constructorimpl(40);
    private static final float ArrowWidth = Dp.m8150constructorimpl(10);
    private static final float ArrowHeight = Dp.m8150constructorimpl(5);

    static final Unit CircularArrowProgressIndicator_RPmYEkk$lambda$14(FloatProducer floatProducer, long j, int i, Composer composer, int i2) {
        m3548CircularArrowProgressIndicatorRPmYEkk(floatProducer, j, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit PullToRefreshBox$lambda$1(boolean z, Function0 function0, Modifier modifier, PullToRefreshState pullToRefreshState, Alignment alignment, Function3 function3, Function3 function32, int i, int i2, Composer composer, int i3) {
        PullToRefreshBox(z, function0, modifier, pullToRefreshState, alignment, function3, function32, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static final void PullToRefreshBox(final boolean isRefreshing, final Function0<Unit> function0, Modifier modifier, PullToRefreshState state, Alignment contentAlignment, Function3<? super BoxScope, ? super Composer, ? super Integer, Unit> function3, final Function3<? super BoxScope, ? super Composer, ? super Integer, Unit> function32, Composer $composer, final int $changed, final int i) {
        Function0<Unit> function02;
        Modifier.Companion modifier2;
        final PullToRefreshState state2;
        Alignment contentAlignment2;
        Function3<? super BoxScope, ? super Composer, ? super Integer, Unit> function3RememberComposableLambda;
        final Modifier modifier3;
        final PullToRefreshState state3;
        final Alignment contentAlignment3;
        final Function3<? super BoxScope, ? super Composer, ? super Integer, Unit> function33;
        Alignment contentAlignment4;
        int $dirty;
        Modifier modifier4;
        PullToRefreshState state4;
        Function0<ComposeUiNode> function03;
        Composer $composer2 = $composer.startRestartGroup(-532332839);
        ComposerKt.sourceInformation($composer2, "C(PullToRefreshBox)N(isRefreshing,onRefresh,modifier,state,contentAlignment,indicator,content)134@6006L200:PullToRefresh.kt#djiw08");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty2 |= $composer2.changed(isRefreshing) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty2 |= 48;
            function02 = function0;
        } else if (($changed & 48) == 0) {
            function02 = function0;
            $dirty2 |= $composer2.changedInstance(function02) ? 32 : 16;
        } else {
            function02 = function0;
        }
        int i2 = i & 4;
        if (i2 != 0) {
            $dirty2 |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer2.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                state2 = state;
                int i3 = $composer2.changed(state2) ? 2048 : 1024;
                $dirty2 |= i3;
            } else {
                state2 = state;
            }
            $dirty2 |= i3;
        } else {
            state2 = state;
        }
        int i4 = i & 16;
        if (i4 != 0) {
            $dirty2 |= 24576;
            contentAlignment2 = contentAlignment;
        } else if (($changed & 24576) == 0) {
            contentAlignment2 = contentAlignment;
            $dirty2 |= $composer2.changed(contentAlignment2) ? 16384 : 8192;
        } else {
            contentAlignment2 = contentAlignment;
        }
        int i5 = i & 32;
        if (i5 != 0) {
            $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            function3RememberComposableLambda = function3;
        } else if ((196608 & $changed) == 0) {
            function3RememberComposableLambda = function3;
            $dirty2 |= $composer2.changedInstance(function3RememberComposableLambda) ? 131072 : 65536;
        } else {
            function3RememberComposableLambda = function3;
        }
        if ((i & 64) != 0) {
            $dirty2 |= 1572864;
        } else if (($changed & 1572864) == 0) {
            $dirty2 |= $composer2.changedInstance(function32) ? 1048576 : 524288;
        }
        if ($composer2.shouldExecute((599187 & $dirty2) != 599186, $dirty2 & 1)) {
            $composer2.startDefaults();
            ComposerKt.sourceInformation($composer2, "123@5653L28,125@5786L164");
            if (($changed & 1) != 0 && !$composer2.getDefaultsInvalid()) {
                $composer2.skipToGroupEnd();
                if ((i & 8) != 0) {
                    $dirty2 &= -7169;
                }
                $dirty = $dirty2;
                modifier4 = modifier2;
                state4 = state2;
            } else {
                if (i2 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if ((i & 8) != 0) {
                    state2 = rememberPullToRefreshState($composer2, 0);
                    $dirty2 &= -7169;
                }
                if (i4 == 0) {
                    contentAlignment4 = contentAlignment2;
                } else {
                    contentAlignment4 = Alignment.INSTANCE.getTopStart();
                }
                if (i5 == 0) {
                    $dirty = $dirty2;
                    modifier4 = modifier2;
                    state4 = state2;
                    contentAlignment2 = contentAlignment4;
                } else {
                    function3RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(1028036671, true, new Function3<BoxScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.pulltorefresh.PullToRefreshKt.PullToRefreshBox.1
                        @Override // kotlin.jvm.functions.Function3
                        public /* bridge */ /* synthetic */ Unit invoke(BoxScope boxScope, Composer composer, Integer num) {
                            invoke(boxScope, composer, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(BoxScope boxScope, Composer $composer3, int $changed2) {
                            ComposerKt.sourceInformation($composer3, "C126@5796L148:PullToRefresh.kt#djiw08");
                            int $dirty3 = $changed2;
                            if (($changed2 & 6) == 0) {
                                $dirty3 |= $composer3.changed(boxScope) ? 4 : 2;
                            }
                            int $dirty4 = $dirty3;
                            if (!$composer3.shouldExecute(($dirty4 & 19) != 18, $dirty4 & 1)) {
                                $composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1028036671, $dirty4, -1, "androidx.compose.material3.pulltorefresh.PullToRefreshBox.<anonymous> (PullToRefresh.kt:126)");
                            }
                            PullToRefreshDefaults.INSTANCE.m3541Indicator2poqoh4(state2, isRefreshing, boxScope.align(Modifier.INSTANCE, Alignment.INSTANCE.getTopCenter()), 0L, 0L, 0.0f, $composer3, 1572864, 56);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, $composer2, 54);
                    $dirty = $dirty2;
                    modifier4 = modifier2;
                    state4 = state2;
                    contentAlignment2 = contentAlignment4;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-532332839, $dirty, -1, "androidx.compose.material3.pulltorefresh.PullToRefreshBox (PullToRefresh.kt:133)");
            }
            Modifier modifier$iv = m3553pullToRefreshZ4HSEVQ$default(modifier4, isRefreshing, state4, false, 0.0f, function02, 12, null);
            int $changed$iv = ($dirty >> 9) & 112;
            Alignment contentAlignment$iv = contentAlignment2;
            ComposerKt.sourceInformationMarkerStart($composer2, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
            MeasurePolicy measurePolicy$iv = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
            int $changed$iv$iv = ($changed$iv << 3) & 112;
            Modifier modifier5 = modifier4;
            ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
            CompositionLocalMap localMap$iv$iv = $composer2.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer2, modifier$iv);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
            PullToRefreshState state5 = state4;
            ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
            if (!($composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer2.startReusableNode();
            if ($composer2.getInserting()) {
                function03 = constructor;
                $composer2.createNode(function03);
            } else {
                function03 = constructor;
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
            int i6 = ($changed$iv$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
            int $changed2 = (($changed$iv >> 6) & 112) | 6;
            BoxScope $this$PullToRefreshBox_u24lambda_u240 = BoxScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer2, 1589913939, "C138@6171L9,139@6189L11:PullToRefresh.kt#djiw08");
            function32.invoke($this$PullToRefreshBox_u24lambda_u240, $composer2, Integer.valueOf(($changed2 & 14) | (($dirty >> 15) & 112)));
            function3RememberComposableLambda.invoke($this$PullToRefreshBox_u24lambda_u240, $composer2, Integer.valueOf(($changed2 & 14) | (($dirty >> 12) & 112)));
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
            state3 = state5;
            contentAlignment3 = contentAlignment2;
            function33 = function3RememberComposableLambda;
        } else {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            state3 = state2;
            contentAlignment3 = contentAlignment2;
            function33 = function3RememberComposableLambda;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.pulltorefresh.PullToRefreshKt$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return PullToRefreshKt.PullToRefreshBox$lambda$1(isRefreshing, function0, modifier3, state3, contentAlignment3, function33, function32, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: pullToRefresh-Z4HSEVQ$default, reason: not valid java name */
    public static /* synthetic */ Modifier m3553pullToRefreshZ4HSEVQ$default(Modifier modifier, boolean z, PullToRefreshState pullToRefreshState, boolean z2, float f, Function0 function0, int i, Object obj) {
        boolean z3;
        float fM3546getPositionalThresholdD9Ej5fM;
        if ((i & 4) == 0) {
            z3 = z2;
        } else {
            z3 = true;
        }
        if ((i & 8) == 0) {
            fM3546getPositionalThresholdD9Ej5fM = f;
        } else {
            fM3546getPositionalThresholdD9Ej5fM = PullToRefreshDefaults.INSTANCE.m3546getPositionalThresholdD9Ej5fM();
        }
        return m3552pullToRefreshZ4HSEVQ(modifier, z, pullToRefreshState, z3, fM3546getPositionalThresholdD9Ej5fM, function0);
    }

    /* JADX INFO: renamed from: pullToRefresh-Z4HSEVQ, reason: not valid java name */
    public static final Modifier m3552pullToRefreshZ4HSEVQ(Modifier $this$pullToRefresh_u2dZ4HSEVQ, boolean isRefreshing, PullToRefreshState state, boolean enabled, float threshold, Function0<Unit> function0) {
        return $this$pullToRefresh_u2dZ4HSEVQ.then(new PullToRefreshElement(isRefreshing, function0, enabled, state, threshold, null));
    }

    public static final PullToRefreshState rememberPullToRefreshState(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, 318623070, "C(rememberPullToRefreshState)586@22650L28,586@22595L83:PullToRefresh.kt#djiw08");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(318623070, $changed, -1, "androidx.compose.material3.pulltorefresh.rememberPullToRefreshState (PullToRefresh.kt:585)");
        }
        Object[] objArr = new Object[0];
        Saver<PullToRefreshStateImpl, Float> saver = PullToRefreshStateImpl.INSTANCE.getSaver();
        ComposerKt.sourceInformationMarkerStart($composer, 1254096538, "CC(remember):PullToRefresh.kt#9igjgp");
        Object it$iv = $composer.rememberedValue();
        if (it$iv == Composer.INSTANCE.getEmpty()) {
            Object value$iv = new Function0() { // from class: androidx.compose.material3.pulltorefresh.PullToRefreshKt$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return PullToRefreshKt.rememberPullToRefreshState$lambda$3$lambda$2();
                }
            };
            $composer.updateRememberedValue(value$iv);
            it$iv = value$iv;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        PullToRefreshStateImpl pullToRefreshStateImpl = (PullToRefreshStateImpl) RememberSaveableKt.m4704rememberSaveable(objArr, (Saver) saver, (Function0) it$iv, $composer, 384);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return pullToRefreshStateImpl;
    }

    static final PullToRefreshStateImpl rememberPullToRefreshState$lambda$3$lambda$2() {
        return new PullToRefreshStateImpl();
    }

    public static final PullToRefreshState PullToRefreshState() {
        return new PullToRefreshStateImpl();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: CircularArrowProgressIndicator-RPmYEkk, reason: not valid java name */
    public static final void m3548CircularArrowProgressIndicatorRPmYEkk(final FloatProducer progress, final long color, Composer $composer, final int $changed) {
        int $dirty;
        Composer $composer2 = $composer.startRestartGroup(-1353562852);
        ComposerKt.sourceInformation($composer2, "C(CircularArrowProgressIndicator)N(progress,color:c#ui.graphics.Color)632@23995L61,634@24157L76,639@24460L7,637@24335L143,644@24553L175,650@24770L443,642@24484L729:PullToRefresh.kt#djiw08");
        int $dirty2 = $changed;
        if (($changed & 6) == 0) {
            $dirty2 |= ($changed & 8) == 0 ? $composer2.changed(progress) : $composer2.changedInstance(progress) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty2 |= $composer2.changed(color) ? 32 : 16;
        }
        if ($composer2.shouldExecute(($dirty2 & 19) != 18, $dirty2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1353562852, $dirty2, -1, "androidx.compose.material3.pulltorefresh.CircularArrowProgressIndicator (PullToRefresh.kt:631)");
            }
            ComposerKt.sourceInformationMarkerStart($composer2, 601193529, "CC(remember):PullToRefresh.kt#9igjgp");
            Object it$iv = $composer2.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                Path $this$CircularArrowProgressIndicator_RPmYEkk_u24lambda_u245_u24lambda_u244 = AndroidPath_androidKt.Path();
                $this$CircularArrowProgressIndicator_RPmYEkk_u24lambda_u245_u24lambda_u244.mo5203setFillTypeoQ8Xj4U(PathFillType.INSTANCE.m5609getEvenOddRgk1Os());
                $composer2.updateRememberedValue($this$CircularArrowProgressIndicator_RPmYEkk_u24lambda_u245_u24lambda_u244);
                it$iv = $this$CircularArrowProgressIndicator_RPmYEkk_u24lambda_u245_u24lambda_u244;
            }
            final Path path = (Path) it$iv;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerStart($composer2, 601198728, "CC(remember):PullToRefresh.kt#9igjgp");
            Object it$iv2 = $composer2.rememberedValue();
            if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                Object value$iv = SnapshotStateKt.derivedStateOf(new Function0() { // from class: androidx.compose.material3.pulltorefresh.PullToRefreshKt$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return Float.valueOf(PullToRefreshKt.CircularArrowProgressIndicator_RPmYEkk$lambda$7$lambda$6(progress));
                    }
                });
                $composer2.updateRememberedValue(value$iv);
                it$iv2 = value$iv;
            }
            State targetAlpha$delegate = (State) it$iv2;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            final State<Float> stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(CircularArrowProgressIndicator_RPmYEkk$lambda$8(targetAlpha$delegate), MotionSchemeKt.value(MotionSchemeKeyTokens.DefaultEffects, $composer2, 6), 0.0f, null, null, $composer2, 0, 28);
            Modifier.Companion companion = Modifier.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer2, 601211499, "CC(remember):PullToRefresh.kt#9igjgp");
            boolean invalid$iv = ($dirty2 & 14) == 4 || (($dirty2 & 8) != 0 && $composer2.changedInstance(progress));
            Object it$iv3 = $composer2.rememberedValue();
            if (invalid$iv || it$iv3 == Composer.INSTANCE.getEmpty()) {
                $dirty = $dirty2;
                Object value$iv2 = new Function1() { // from class: androidx.compose.material3.pulltorefresh.PullToRefreshKt$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return PullToRefreshKt.CircularArrowProgressIndicator_RPmYEkk$lambda$10$lambda$9(progress, (SemanticsPropertyReceiver) obj);
                    }
                };
                $composer2.updateRememberedValue(value$iv2);
                it$iv3 = value$iv2;
            } else {
                $dirty = $dirty2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Modifier modifierM1115size3ABfNKs = SizeKt.m1115size3ABfNKs(SemanticsModifierKt.clearAndSetSemantics(companion, (Function1) it$iv3), SpinnerSize);
            ComposerKt.sourceInformationMarkerStart($composer2, 601218711, "CC(remember):PullToRefresh.kt#9igjgp");
            boolean invalid$iv2 = $composer2.changed(stateAnimateFloatAsState) | (($dirty & 14) == 4 || (($dirty & 8) != 0 && $composer2.changedInstance(progress))) | (($dirty & 112) == 32) | $composer2.changedInstance(path);
            Object it$iv4 = $composer2.rememberedValue();
            if (invalid$iv2 || it$iv4 == Composer.INSTANCE.getEmpty()) {
                Object value$iv3 = new Function1() { // from class: androidx.compose.material3.pulltorefresh.PullToRefreshKt$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return PullToRefreshKt.CircularArrowProgressIndicator_RPmYEkk$lambda$13$lambda$12(progress, stateAnimateFloatAsState, color, path, (DrawScope) obj);
                    }
                };
                $composer2.updateRememberedValue(value$iv3);
                it$iv4 = value$iv3;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            CanvasKt.Canvas(modifierM1115size3ABfNKs, (Function1) it$iv4, $composer2, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.pulltorefresh.PullToRefreshKt$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return PullToRefreshKt.CircularArrowProgressIndicator_RPmYEkk$lambda$14(progress, color, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    static final float CircularArrowProgressIndicator_RPmYEkk$lambda$7$lambda$6(FloatProducer $progress) {
        if ($progress.invoke() >= 1.0f) {
            return 1.0f;
        }
        return MinAlpha;
    }

    private static final float CircularArrowProgressIndicator_RPmYEkk$lambda$8(State<Float> state) {
        Object thisObj$iv = state.getValue();
        return ((Number) thisObj$iv).floatValue();
    }

    static final Unit CircularArrowProgressIndicator_RPmYEkk$lambda$10$lambda$9(FloatProducer $progress, SemanticsPropertyReceiver $this$clearAndSetSemantics) {
        if ($progress.invoke() > 0.0f) {
            SemanticsPropertiesKt.setProgressBarRangeInfo($this$clearAndSetSemantics, new ProgressBarRangeInfo($progress.invoke(), RangesKt.rangeTo(0.0f, 1.0f), 0));
        }
        return Unit.INSTANCE;
    }

    static final Unit CircularArrowProgressIndicator_RPmYEkk$lambda$13$lambda$12(FloatProducer $progress, State $alphaState, long $color, Path $path, DrawScope $this$Canvas) throws Throwable {
        long previousSize$iv$iv;
        DrawContext $this$withTransform_u24lambda_u246$iv$iv;
        ArrowValues values = ArrowValues($progress.invoke());
        float alpha = ((Number) $alphaState.getValue()).floatValue();
        float degrees$iv = values.getRotation();
        long pivot$iv = $this$Canvas.mo5886getCenterF1C5BW0();
        DrawContext $this$withTransform_u24lambda_u246$iv$iv2 = $this$Canvas.getDrawContext();
        long previousSize$iv$iv2 = $this$withTransform_u24lambda_u246$iv$iv2.mo5808getSizeNHjbRc();
        $this$withTransform_u24lambda_u246$iv$iv2.getCanvas().save();
        try {
            DrawTransform $this$rotate_Rg1IO4c_u24lambda_u240$iv = $this$withTransform_u24lambda_u246$iv$iv2.getTransform();
            $this$rotate_Rg1IO4c_u24lambda_u240$iv.mo5814rotateUv8p0NA(degrees$iv, pivot$iv);
            try {
                float arcRadius = $this$Canvas.mo432toPx0680j_4(ArcRadius) + ($this$Canvas.mo432toPx0680j_4(StrokeWidth) / 2.0f);
                Rect arcBounds = RectKt.m5107Rect3MmeM6k(androidx.compose.ui.geometry.SizeKt.m5147getCenteruvyYCjk($this$Canvas.mo5887getSizeNHjbRc()), arcRadius);
                previousSize$iv$iv = previousSize$iv$iv2;
                try {
                    m3551drawCircularIndicatorKzyDr3Q($this$Canvas, $color, alpha, values, arcBounds, StrokeWidth);
                    $this$withTransform_u24lambda_u246$iv$iv = $this$withTransform_u24lambda_u246$iv$iv2;
                    try {
                    } catch (Throwable th) {
                        th = th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    $this$withTransform_u24lambda_u246$iv$iv = $this$withTransform_u24lambda_u246$iv$iv2;
                }
                try {
                    m3550drawArrowuDrxG_w($this$Canvas, $path, arcBounds, $color, alpha, values, StrokeWidth);
                    $this$withTransform_u24lambda_u246$iv$iv.getCanvas().restore();
                    $this$withTransform_u24lambda_u246$iv$iv.mo5809setSizeuvyYCjk(previousSize$iv$iv);
                    return Unit.INSTANCE;
                } catch (Throwable th3) {
                    th = th3;
                    $this$withTransform_u24lambda_u246$iv$iv = $this$withTransform_u24lambda_u246$iv$iv;
                    $this$withTransform_u24lambda_u246$iv$iv.getCanvas().restore();
                    $this$withTransform_u24lambda_u246$iv$iv.mo5809setSizeuvyYCjk(previousSize$iv$iv);
                    throw th;
                }
            } catch (Throwable th4) {
                th = th4;
                previousSize$iv$iv = previousSize$iv$iv2;
                $this$withTransform_u24lambda_u246$iv$iv = $this$withTransform_u24lambda_u246$iv$iv2;
            }
        } catch (Throwable th5) {
            th = th5;
            previousSize$iv$iv = previousSize$iv$iv2;
            $this$withTransform_u24lambda_u246$iv$iv = $this$withTransform_u24lambda_u246$iv$iv2;
        }
    }

    /* JADX INFO: renamed from: drawCircularIndicator-KzyDr3Q, reason: not valid java name */
    private static final void m3551drawCircularIndicatorKzyDr3Q(DrawScope $this$drawCircularIndicator_u2dKzyDr3Q, long color, float alpha, ArrowValues values, Rect arcBounds, float strokeWidth) {
        DrawScope.m5866drawArcyD3GUKo$default($this$drawCircularIndicator_u2dKzyDr3Q, color, values.getStartAngle(), values.getEndAngle() - values.getStartAngle(), false, arcBounds.m5103getTopLeftF1C5BW0(), arcBounds.m5101getSizeNHjbRc(), alpha, new Stroke($this$drawCircularIndicator_u2dKzyDr3Q.mo432toPx0680j_4(strokeWidth), 0.0f, StrokeCap.INSTANCE.m5687getButtKaPHkGw(), 0, null, 26, null), null, 0, ViewUtils.EDGE_TO_EDGE_FLAGS, null);
    }

    private static final ArrowValues ArrowValues(float progress) {
        float adjustedPercent = (Math.max(Math.min(1.0f, progress) - 0.4f, 0.0f) * 5.0f) / 3.0f;
        float overshootPercent = Math.abs(progress) - 1.0f;
        float linearTension = RangesKt.coerceIn(overshootPercent, 0.0f, 2.0f);
        float tensionPercent = linearTension - (((float) Math.pow(linearTension, 2.0d)) / 4.0f);
        float endTrim = 0.8f * adjustedPercent;
        float rotation = (((0.4f * adjustedPercent) - 0.25f) + tensionPercent) * 0.5f;
        float startAngle = rotation * 360.0f;
        float endAngle = (rotation + endTrim) * 360.0f;
        float scale = Math.min(1.0f, adjustedPercent);
        return new ArrowValues(rotation, startAngle, endAngle, scale);
    }

    /* JADX INFO: renamed from: drawArrow-uDrxG_w, reason: not valid java name */
    private static final void m3550drawArrowuDrxG_w(DrawScope $this$drawArrow_u2duDrxG_w, Path arrow, Rect bounds, long color, float alpha, ArrowValues values, float strokeWidth) throws Throwable {
        DrawContext $this$withTransform_u24lambda_u246$iv$iv;
        long previousSize$iv$iv;
        arrow.reset();
        arrow.moveTo(0.0f, 0.0f);
        arrow.lineTo(($this$drawArrow_u2duDrxG_w.mo432toPx0680j_4(ArrowWidth) * values.getScale()) / 2.0f, $this$drawArrow_u2duDrxG_w.mo432toPx0680j_4(ArrowHeight) * values.getScale());
        arrow.lineTo($this$drawArrow_u2duDrxG_w.mo432toPx0680j_4(ArrowWidth) * values.getScale(), 0.0f);
        float radius = Math.min(bounds.getRight() - bounds.getLeft(), bounds.getBottom() - bounds.getTop()) / 2.0f;
        float inset = ($this$drawArrow_u2duDrxG_w.mo432toPx0680j_4(ArrowWidth) * values.getScale()) / 2.0f;
        long arg0$iv = bounds.m5098getCenterF1C5BW0();
        int bits$iv$iv$iv = (int) (arg0$iv >> 32);
        float x$iv = (Float.intBitsToFloat(bits$iv$iv$iv) + radius) - inset;
        long arg0$iv2 = bounds.m5098getCenterF1C5BW0();
        int bits$iv$iv$iv2 = (int) (arg0$iv2 & 4294967295L);
        float y$iv = Float.intBitsToFloat(bits$iv$iv$iv2) - $this$drawArrow_u2duDrxG_w.mo432toPx0680j_4(strokeWidth);
        long v1$iv$iv = Float.floatToRawIntBits(x$iv);
        long v2$iv$iv = Float.floatToRawIntBits(y$iv);
        arrow.mo5205translatek4lQ0M(Offset.m5060constructorimpl((v1$iv$iv << 32) | (v2$iv$iv & 4294967295L)));
        float degrees$iv = values.getEndAngle() - $this$drawArrow_u2duDrxG_w.mo432toPx0680j_4(strokeWidth);
        long pivot$iv = $this$drawArrow_u2duDrxG_w.mo5886getCenterF1C5BW0();
        DrawContext $this$withTransform_u24lambda_u246$iv$iv2 = $this$drawArrow_u2duDrxG_w.getDrawContext();
        long previousSize$iv$iv2 = $this$withTransform_u24lambda_u246$iv$iv2.mo5808getSizeNHjbRc();
        $this$withTransform_u24lambda_u246$iv$iv2.getCanvas().save();
        try {
            DrawTransform $this$rotate_Rg1IO4c_u24lambda_u240$iv = $this$withTransform_u24lambda_u246$iv$iv2.getTransform();
            $this$rotate_Rg1IO4c_u24lambda_u240$iv.mo5814rotateUv8p0NA(degrees$iv, pivot$iv);
            $this$withTransform_u24lambda_u246$iv$iv = $this$withTransform_u24lambda_u246$iv$iv2;
            try {
                previousSize$iv$iv = previousSize$iv$iv2;
            } catch (Throwable th) {
                th = th;
                previousSize$iv$iv = previousSize$iv$iv2;
            }
            try {
                DrawScope.m5877drawPathLG529CI$default($this$drawArrow_u2duDrxG_w, arrow, color, alpha, new Stroke($this$drawArrow_u2duDrxG_w.mo432toPx0680j_4(strokeWidth), 0.0f, 0, 0, null, 30, null), null, 0, 48, null);
                $this$withTransform_u24lambda_u246$iv$iv.getCanvas().restore();
                $this$withTransform_u24lambda_u246$iv$iv.mo5809setSizeuvyYCjk(previousSize$iv$iv);
            } catch (Throwable th2) {
                th = th2;
                $this$withTransform_u24lambda_u246$iv$iv = $this$withTransform_u24lambda_u246$iv$iv;
                $this$withTransform_u24lambda_u246$iv$iv.getCanvas().restore();
                $this$withTransform_u24lambda_u246$iv$iv.mo5809setSizeuvyYCjk(previousSize$iv$iv);
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            $this$withTransform_u24lambda_u246$iv$iv = $this$withTransform_u24lambda_u246$iv$iv2;
            previousSize$iv$iv = previousSize$iv$iv2;
        }
    }

    public static final float getSpinnerSize() {
        return SpinnerSize;
    }

    public static final float getSpinnerContainerSize() {
        return SpinnerContainerSize;
    }
}
