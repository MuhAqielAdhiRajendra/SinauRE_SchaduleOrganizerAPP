package androidx.compose.foundation.text.selection;

import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.text.Handle;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.AbsoluteAlignment;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.CacheDrawScope;
import androidx.compose.ui.draw.DrawModifierKt;
import androidx.compose.ui.draw.DrawResult;
import androidx.compose.ui.geometry.InlineClassHelperKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.BlendMode;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.CanvasKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.ImageBitmap;
import androidx.compose.ui.graphics.ImageBitmapConfig;
import androidx.compose.ui.graphics.ImageBitmapKt;
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawContext;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.DrawTransform;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.ViewConfiguration;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.text.style.ResolvedTextDirection;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.DpSize;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.compose.ui.window.AndroidPopup_androidKt;
import androidx.compose.ui.window.PopupProperties;
import androidx.compose.ui.window.SecureFlagPolicy;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: AndroidSelectionHandles.android.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000R\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001aI\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0001¢\u0006\u0004\b\u000f\u0010\u0010\u001a+\u0010\u0011\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u000e2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00050\u00132\u0006\u0010\u0014\u001a\u00020\u0005H\u0001¢\u0006\u0002\u0010\u0015\u001a\"\u0010\u0016\u001a\u00020\u000e*\u00020\u000e2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00050\u00132\u0006\u0010\u0014\u001a\u00020\u0005H\u0000\u001a\u0014\u0010\u0017\u001a\u00020\u0018*\u00020\u00192\u0006\u0010\u001a\u001a\u00020\fH\u0000\u001a0\u0010\u001b\u001a\u00020\u00012\u0006\u0010\u001c\u001a\u00020\u00032\u0006\u0010\u001d\u001a\u00020\u001e2\u0011\u0010\u001f\u001a\r\u0012\u0004\u0012\u00020\u00010\u0013¢\u0006\u0002\b H\u0001¢\u0006\u0002\u0010!¨\u0006\""}, d2 = {"SelectionHandle", "", "offsetProvider", "Landroidx/compose/foundation/text/selection/OffsetProvider;", "isStartHandle", "", "direction", "Landroidx/compose/ui/text/style/ResolvedTextDirection;", "handlesCrossed", "minTouchTargetSize", "Landroidx/compose/ui/unit/DpSize;", "lineHeight", "", "modifier", "Landroidx/compose/ui/Modifier;", "SelectionHandle-wLIcFTc", "(Landroidx/compose/foundation/text/selection/OffsetProvider;ZLandroidx/compose/ui/text/style/ResolvedTextDirection;ZJFLandroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "SelectionHandleIcon", "iconVisible", "Lkotlin/Function0;", "isLeft", "(Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function0;ZLandroidx/compose/runtime/Composer;I)V", "drawSelectionHandle", "createHandleImage", "Landroidx/compose/ui/graphics/ImageBitmap;", "Landroidx/compose/ui/draw/CacheDrawScope;", "radius", "HandlePopup", "positionProvider", "handleReferencePoint", "Landroidx/compose/ui/Alignment;", "content", "Landroidx/compose/runtime/Composable;", "(Landroidx/compose/foundation/text/selection/OffsetProvider;Landroidx/compose/ui/Alignment;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "foundation"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class AndroidSelectionHandles_androidKt {
    static final Unit HandlePopup$lambda$1(OffsetProvider offsetProvider, Alignment alignment, Function2 function2, int i, Composer composer, int i2) {
        HandlePopup(offsetProvider, alignment, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit SelectionHandleIcon$lambda$0(Modifier modifier, Function0 function0, boolean z, int i, Composer composer, int i2) {
        SelectionHandleIcon(modifier, function0, z, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit SelectionHandle_wLIcFTc$lambda$2(OffsetProvider offsetProvider, boolean z, ResolvedTextDirection resolvedTextDirection, boolean z2, long j, float f, Modifier modifier, int i, int i2, Composer composer, int i3) {
        m2022SelectionHandlewLIcFTc(offsetProvider, z, resolvedTextDirection, z2, j, f, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: SelectionHandle-wLIcFTc, reason: not valid java name */
    public static final void m2022SelectionHandlewLIcFTc(final OffsetProvider offsetProvider, final boolean isStartHandle, final ResolvedTextDirection direction, boolean handlesCrossed, long minTouchTargetSize, final float lineHeight, final Modifier modifier, Composer $composer, final int $changed, final int i) {
        boolean z;
        long minTouchTargetSize2;
        int $dirty;
        Composer $composer2 = $composer.startRestartGroup(-466280168);
        ComposerKt.sourceInformation($composer2, "C(SelectionHandle)N(offsetProvider,isStartHandle,direction,handlesCrossed,minTouchTargetSize:c#ui.unit.DpSize,lineHeight,modifier)71@3073L394,83@3574L7,84@3678L1346,84@3586L1438:AndroidSelectionHandles.android.kt#eksfi3");
        int $dirty2 = $changed;
        if (($changed & 6) == 0) {
            $dirty2 |= ($changed & 8) == 0 ? $composer2.changed(offsetProvider) : $composer2.changedInstance(offsetProvider) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty2 |= $composer2.changed(isStartHandle) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty2 |= $composer2.changed(direction.ordinal()) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            z = handlesCrossed;
            $dirty2 |= $composer2.changed(z) ? 2048 : 1024;
        } else {
            z = handlesCrossed;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                minTouchTargetSize2 = minTouchTargetSize;
                int i2 = $composer2.changed(minTouchTargetSize2) ? 16384 : 8192;
                $dirty2 |= i2;
            } else {
                minTouchTargetSize2 = minTouchTargetSize;
            }
            $dirty2 |= i2;
        } else {
            minTouchTargetSize2 = minTouchTargetSize;
        }
        if ((1572864 & $changed) == 0) {
            $dirty2 |= $composer2.changed(modifier) ? 1048576 : 524288;
        }
        if ($composer2.shouldExecute((533651 & $dirty2) != 533650, $dirty2 & 1)) {
            $composer2.startDefaults();
            if (($changed & 1) != 0 && !$composer2.getDefaultsInvalid()) {
                $composer2.skipToGroupEnd();
                if ((i & 16) != 0) {
                    $dirty2 &= -57345;
                }
            } else if ((i & 16) != 0) {
                minTouchTargetSize2 = DpSize.INSTANCE.m8257getUnspecifiedMYxV2XQ();
                $dirty2 &= -57345;
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-466280168, $dirty2, -1, "androidx.compose.foundation.text.selection.SelectionHandle (AndroidSelectionHandles.android.kt:65)");
            }
            final boolean isLeft = SelectionHandlesKt.isLeftSelectionHandle(isStartHandle, direction, handlesCrossed);
            AbsoluteAlignment absoluteAlignment = AbsoluteAlignment.INSTANCE;
            Alignment handleReferencePoint = isLeft ? absoluteAlignment.getTopRight() : absoluteAlignment.getTopLeft();
            ComposerKt.sourceInformationMarkerStart($composer2, -1418624126, "CC(remember):AndroidSelectionHandles.android.kt#9igjgp");
            boolean invalid$iv = (($dirty2 & 14) == 4 || (($dirty2 & 8) != 0 && $composer2.changedInstance(offsetProvider))) | (($dirty2 & 112) == 32) | $composer2.changed(isLeft);
            Object it$iv = $composer2.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                $dirty = $dirty2;
                Object value$iv = new Function1() { // from class: androidx.compose.foundation.text.selection.AndroidSelectionHandles_androidKt$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return AndroidSelectionHandles_androidKt.SelectionHandle_wLIcFTc$lambda$0$0(offsetProvider, isStartHandle, isLeft, (SemanticsPropertyReceiver) obj);
                    }
                };
                $composer2.updateRememberedValue(value$iv);
                it$iv = value$iv;
            } else {
                $dirty = $dirty2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            final Modifier semanticsModifier = SemanticsModifierKt.semantics$default(modifier, false, (Function1) it$iv, 1, null);
            ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration = CompositionLocalsKt.getLocalViewConfiguration();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = $composer2.consume(localViewConfiguration);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            final ViewConfiguration viewConfiguration = (ViewConfiguration) objConsume;
            final long minTouchTargetSize3 = minTouchTargetSize2;
            HandlePopup(offsetProvider, handleReferencePoint, ComposableLambdaKt.rememberComposableLambda(1365123137, true, new Function2() { // from class: androidx.compose.foundation.text.selection.AndroidSelectionHandles_androidKt$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AndroidSelectionHandles_androidKt.SelectionHandle_wLIcFTc$lambda$1(viewConfiguration, minTouchTargetSize3, isLeft, semanticsModifier, offsetProvider, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer2, 54), $composer2, ($dirty & 14) | 384);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            minTouchTargetSize2 = minTouchTargetSize3;
        } else {
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final boolean z2 = z;
            final long minTouchTargetSize4 = minTouchTargetSize2;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.foundation.text.selection.AndroidSelectionHandles_androidKt$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AndroidSelectionHandles_androidKt.SelectionHandle_wLIcFTc$lambda$2(offsetProvider, isStartHandle, direction, z2, minTouchTargetSize4, lineHeight, modifier, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit SelectionHandle_wLIcFTc$lambda$0$0(OffsetProvider $offsetProvider, boolean $isStartHandle, boolean $isLeft, SemanticsPropertyReceiver $this$semantics) {
        long position = $offsetProvider.mo1487provideF1C5BW0();
        $this$semantics.set(SelectionHandlesKt.getSelectionHandleInfoKey(), new SelectionHandleInfo($isStartHandle ? Handle.SelectionStart : Handle.SelectionEnd, position, $isLeft ? SelectionHandleAnchor.Left : SelectionHandleAnchor.Right, (9223372034707292159L & position) != InlineClassHelperKt.UnspecifiedPackedFloats, null));
        return Unit.INSTANCE;
    }

    static final Unit SelectionHandle_wLIcFTc$lambda$1(ViewConfiguration $viewConfiguration, final long $minTouchTargetSize, final boolean $isLeft, final Modifier $semanticsModifier, final OffsetProvider $offsetProvider, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C85@3764L1254,85@3688L1330:AndroidSelectionHandles.android.kt#eksfi3");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1365123137, $changed, -1, "androidx.compose.foundation.text.selection.SelectionHandle.<anonymous> (AndroidSelectionHandles.android.kt:85)");
            }
            CompositionLocalKt.CompositionLocalProvider(CompositionLocalsKt.getLocalViewConfiguration().provides($viewConfiguration), ComposableLambdaKt.rememberComposableLambda(1260045569, true, new Function2() { // from class: androidx.compose.foundation.text.selection.AndroidSelectionHandles_androidKt$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AndroidSelectionHandles_androidKt.SelectionHandle_wLIcFTc$lambda$1$0($minTouchTargetSize, $isLeft, $semanticsModifier, $offsetProvider, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer, 54), $composer, ProvidedValue.$stable | 48);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit SelectionHandle_wLIcFTc$lambda$1$0(long $minTouchTargetSize, boolean $isLeft, Modifier $semanticsModifier, final OffsetProvider $offsetProvider, Composer $composer, int $changed) {
        Arrangement.Horizontal arrangement;
        Function0<ComposeUiNode> function0;
        ComposerKt.sourceInformation($composer, "C:AndroidSelectionHandles.android.kt#eksfi3");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1260045569, $changed, -1, "androidx.compose.foundation.text.selection.SelectionHandle.<anonymous>.<anonymous> (AndroidSelectionHandles.android.kt:86)");
            }
            if ($minTouchTargetSize != InlineClassHelperKt.UnspecifiedPackedFloats) {
                $composer.startReplaceGroup(3458246);
                ComposerKt.sourceInformation($composer, "96@4179L576");
                if ($isLeft) {
                    arrangement = Arrangement.Absolute.INSTANCE.getRight();
                } else {
                    arrangement = Arrangement.Absolute.INSTANCE.getLeft();
                }
                Modifier modifier$iv = SizeKt.m1111requiredSizeInqDBjuR0$default($semanticsModifier, DpSize.m8248getWidthD9Ej5fM($minTouchTargetSize), DpSize.m8246getHeightD9Ej5fM($minTouchTargetSize), 0.0f, 0.0f, 12, null);
                Arrangement.Horizontal horizontalArrangement$iv = arrangement;
                ComposerKt.sourceInformationMarkerStart($composer, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                Alignment.Vertical verticalAlignment$iv = Alignment.INSTANCE.getTop();
                MeasurePolicy measurePolicy$iv = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                int $changed$iv$iv = (0 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)N(content,modifier,measurePolicy)81@3355L27,84@3521L415:Layout.kt#80mrfh");
                int compositeKeyHash$iv$iv = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                CompositionLocalMap localMap$iv$iv = $composer.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer, modifier$iv);
                Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
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
                Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4433constructorimpl($composer);
                Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv, Integer.valueOf(compositeKeyHash$iv$iv), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4439reconcileimpl($this$Layout_u24lambda_u240$iv$iv, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
                int i = ($changed$iv$iv$iv >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                int i2 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -1665345997, "C106@4633L40,104@4529L208:AndroidSelectionHandles.android.kt#eksfi3");
                Modifier.Companion companion = Modifier.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer, -1439191000, "CC(remember):AndroidSelectionHandles.android.kt#9igjgp");
                boolean invalid$iv = $composer.changedInstance($offsetProvider);
                Object value$iv = $composer.rememberedValue();
                if (invalid$iv || value$iv == Composer.INSTANCE.getEmpty()) {
                    value$iv = new Function0() { // from class: androidx.compose.foundation.text.selection.AndroidSelectionHandles_androidKt$$ExternalSyntheticLambda9
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return Boolean.valueOf(AndroidSelectionHandles_androidKt.SelectionHandle_wLIcFTc$lambda$1$0$0$0$0($offsetProvider));
                        }
                    };
                    $composer.updateRememberedValue(value$iv);
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                SelectionHandleIcon(companion, (Function0) value$iv, $isLeft, $composer, 6);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endReplaceGroup();
            } else {
                $composer.startReplaceGroup(4389176);
                ComposerKt.sourceInformation($composer, "113@4898L40,111@4793L201");
                ComposerKt.sourceInformationMarkerStart($composer, -276949335, "CC(remember):AndroidSelectionHandles.android.kt#9igjgp");
                boolean invalid$iv2 = $composer.changedInstance($offsetProvider);
                Object it$iv = $composer.rememberedValue();
                if (invalid$iv2 || it$iv == Composer.INSTANCE.getEmpty()) {
                    Object value$iv2 = new Function0() { // from class: androidx.compose.foundation.text.selection.AndroidSelectionHandles_androidKt$$ExternalSyntheticLambda10
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return Boolean.valueOf(AndroidSelectionHandles_androidKt.SelectionHandle_wLIcFTc$lambda$1$0$1$0($offsetProvider));
                        }
                    };
                    $composer.updateRememberedValue(value$iv2);
                    it$iv = value$iv2;
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                SelectionHandleIcon($semanticsModifier, (Function0) it$iv, $isLeft, $composer, 0);
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
    public static final boolean SelectionHandle_wLIcFTc$lambda$1$0$0$0$0(OffsetProvider $offsetProvider) {
        long $this$isSpecified$iv = $offsetProvider.mo1487provideF1C5BW0();
        return (9223372034707292159L & $this$isSpecified$iv) != InlineClassHelperKt.UnspecifiedPackedFloats;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean SelectionHandle_wLIcFTc$lambda$1$0$1$0(OffsetProvider $offsetProvider) {
        long $this$isSpecified$iv = $offsetProvider.mo1487provideF1C5BW0();
        return (9223372034707292159L & $this$isSpecified$iv) != InlineClassHelperKt.UnspecifiedPackedFloats;
    }

    public static final void SelectionHandleIcon(final Modifier modifier, final Function0<Boolean> function0, final boolean isLeft, Composer $composer, final int $changed) {
        Composer $composer2 = $composer.startRestartGroup(2111672474);
        ComposerKt.sourceInformation($composer2, "C(SelectionHandleIcon)N(modifier,iconVisible,isLeft)124@5167L89:AndroidSelectionHandles.android.kt#eksfi3");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(modifier) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changedInstance(function0) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer2.changed(isLeft) ? 256 : 128;
        }
        if (!$composer2.shouldExecute(($dirty & 147) != 146, $dirty & 1)) {
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2111672474, $dirty, -1, "androidx.compose.foundation.text.selection.SelectionHandleIcon (AndroidSelectionHandles.android.kt:123)");
            }
            SpacerKt.Spacer(drawSelectionHandle(SizeKt.m1117sizeVpY3zN4(modifier, SelectionHandlesKt.getHandleWidth(), SelectionHandlesKt.getHandleHeight()), function0, isLeft), $composer2, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.foundation.text.selection.AndroidSelectionHandles_androidKt$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AndroidSelectionHandles_androidKt.SelectionHandleIcon$lambda$0(modifier, function0, isLeft, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final Modifier drawSelectionHandle(Modifier $this$drawSelectionHandle, final Function0<Boolean> function0, final boolean isLeft) {
        return ComposedModifierKt.composed$default($this$drawSelectionHandle, null, new Function3() { // from class: androidx.compose.foundation.text.selection.AndroidSelectionHandles_androidKt$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return AndroidSelectionHandles_androidKt.drawSelectionHandle$lambda$0(function0, isLeft, (Modifier) obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        }, 1, null);
    }

    static final Modifier drawSelectionHandle$lambda$0(final Function0 $iconVisible, final boolean $isLeft, Modifier $this$composed, Composer $composer, int $changed) {
        $composer.startReplaceGroup(-196777734);
        ComposerKt.sourceInformation($composer, "C129@5425L7,130@5472L678:AndroidSelectionHandles.android.kt#eksfi3");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-196777734, $changed, -1, "androidx.compose.foundation.text.selection.drawSelectionHandle.<anonymous> (AndroidSelectionHandles.android.kt:129)");
        }
        ProvidableCompositionLocal<SelectionColors> localTextSelectionColors = TextSelectionColorsKt.getLocalTextSelectionColors();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
        Object objConsume = $composer.consume(localTextSelectionColors);
        ComposerKt.sourceInformationMarkerEnd($composer);
        final long handleColor = ((SelectionColors) objConsume).getSelectionHandleColor();
        ComposerKt.sourceInformationMarkerStart($composer, -124837472, "CC(remember):AndroidSelectionHandles.android.kt#9igjgp");
        boolean invalid$iv = $composer.changed(handleColor) | $composer.changed($iconVisible) | $composer.changed($isLeft);
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            Object value$iv = new Function1() { // from class: androidx.compose.foundation.text.selection.AndroidSelectionHandles_androidKt$$ExternalSyntheticLambda7
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return AndroidSelectionHandles_androidKt.drawSelectionHandle$lambda$0$0$0(handleColor, $iconVisible, $isLeft, (CacheDrawScope) obj);
                }
            };
            $composer.updateRememberedValue(value$iv);
            it$iv = value$iv;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        Modifier modifierDrawWithCache = DrawModifierKt.drawWithCache($this$composed, (Function1) it$iv);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceGroup();
        return modifierDrawWithCache;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DrawResult drawSelectionHandle$lambda$0$0$0(long $handleColor, final Function0 $iconVisible, final boolean $isLeft, CacheDrawScope $this$drawWithCache) {
        long arg0$iv = $this$drawWithCache.m4848getSizeNHjbRc();
        int bits$iv$iv$iv = (int) (arg0$iv >> 32);
        float radius = Float.intBitsToFloat(bits$iv$iv$iv) / 2.0f;
        final ImageBitmap handleImage = createHandleImage($this$drawWithCache, radius);
        final ColorFilter colorFilter = ColorFilter.Companion.m5354tintxETnrds$default(ColorFilter.INSTANCE, $handleColor, 0, 2, null);
        return $this$drawWithCache.onDrawWithContent(new Function1() { // from class: androidx.compose.foundation.text.selection.AndroidSelectionHandles_androidKt$$ExternalSyntheticLambda8
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AndroidSelectionHandles_androidKt.drawSelectionHandle$lambda$0$0$0$0($iconVisible, $isLeft, handleImage, colorFilter, (ContentDrawScope) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit drawSelectionHandle$lambda$0$0$0$0(Function0 $iconVisible, boolean $isLeft, ImageBitmap $handleImage, ColorFilter $colorFilter, ContentDrawScope $this$onDrawWithContent) {
        $this$onDrawWithContent.drawContent();
        if (!((Boolean) $iconVisible.invoke()).booleanValue()) {
            return Unit.INSTANCE;
        }
        if ($isLeft) {
            ContentDrawScope $this$scale_u2dFgt4K4Q_u24default$iv = $this$onDrawWithContent;
            long pivot$iv = $this$scale_u2dFgt4K4Q_u24default$iv.mo5886getCenterF1C5BW0();
            DrawContext $this$withTransform_u24lambda_u240$iv$iv = $this$scale_u2dFgt4K4Q_u24default$iv.getDrawContext();
            long previousSize$iv$iv = $this$withTransform_u24lambda_u240$iv$iv.mo5808getSizeNHjbRc();
            $this$withTransform_u24lambda_u240$iv$iv.getCanvas().save();
            try {
                DrawTransform $this$scale_Fgt4K4Q_u24lambda_u240$iv = $this$withTransform_u24lambda_u240$iv$iv.getTransform();
                $this$scale_Fgt4K4Q_u24lambda_u240$iv.mo5815scale0AR0LA0(-1.0f, 1.0f, pivot$iv);
                DrawScope.m5871drawImagegbVJVH8$default($this$scale_u2dFgt4K4Q_u24default$iv, $handleImage, 0L, 0.0f, null, $colorFilter, 0, 46, null);
            } finally {
                $this$withTransform_u24lambda_u240$iv$iv.getCanvas().restore();
                $this$withTransform_u24lambda_u240$iv$iv.mo5809setSizeuvyYCjk(previousSize$iv$iv);
            }
        } else {
            DrawScope.m5871drawImagegbVJVH8$default($this$onDrawWithContent, $handleImage, 0L, 0.0f, null, $colorFilter, 0, 46, null);
        }
        return Unit.INSTANCE;
    }

    public static final ImageBitmap createHandleImage(CacheDrawScope $this$createHandleImage, float radius) {
        int edge;
        ImageBitmap imageBitmap;
        Canvas canvas;
        CanvasDrawScope drawScope;
        int edge2 = ((int) Math.ceil(radius)) * 2;
        ImageBitmap imageBitmap2 = HandleImageCache.INSTANCE.getImageBitmap();
        Canvas canvas2 = HandleImageCache.INSTANCE.getCanvas();
        CanvasDrawScope drawScope2 = HandleImageCache.INSTANCE.getCanvasDrawScope();
        if (imageBitmap2 == null || canvas2 == null || edge2 > imageBitmap2.getWidth() || edge2 > imageBitmap2.getHeight()) {
            ImageBitmap imageBitmap3 = ImageBitmapKt.m5549ImageBitmapx__hDU$default(edge2, edge2, ImageBitmapConfig.INSTANCE.m5543getAlpha8_sVssgQ(), false, null, 24, null);
            edge = edge2;
            HandleImageCache.INSTANCE.setImageBitmap(imageBitmap3);
            Canvas canvas3 = CanvasKt.Canvas(imageBitmap3);
            HandleImageCache.INSTANCE.setCanvas(canvas3);
            imageBitmap = imageBitmap3;
            canvas = canvas3;
        } else {
            edge = edge2;
            imageBitmap = imageBitmap2;
            canvas = canvas2;
        }
        if (drawScope2 != null) {
            drawScope = drawScope2;
        } else {
            CanvasDrawScope drawScope3 = new CanvasDrawScope();
            HandleImageCache.INSTANCE.setCanvasDrawScope(drawScope3);
            drawScope = drawScope3;
        }
        CacheDrawScope density$iv = $this$createHandleImage;
        LayoutDirection layoutDirection$iv = $this$createHandleImage.getLayoutDirection();
        float width$iv = imageBitmap.getWidth();
        float height$iv = imageBitmap.getHeight();
        long v1$iv$iv = Float.floatToRawIntBits(width$iv);
        long v2$iv$iv = Float.floatToRawIntBits(height$iv);
        long size$iv = Size.m5128constructorimpl((v1$iv$iv << 32) | (v2$iv$iv & 4294967295L));
        CanvasDrawScope this_$iv = drawScope;
        Canvas canvas$iv = canvas;
        CanvasDrawScope.DrawParams drawParams = this_$iv.getDrawParams();
        Density prevDensity$iv = drawParams.getDensity();
        LayoutDirection prevLayoutDirection$iv = drawParams.getLayoutDirection();
        Canvas prevCanvas$iv = drawParams.getCanvas();
        long prevSize$iv = drawParams.getSize();
        CanvasDrawScope.DrawParams $this$draw_yzxVdVo_u24lambda_u240$iv = this_$iv.getDrawParams();
        $this$draw_yzxVdVo_u24lambda_u240$iv.setDensity(density$iv);
        $this$draw_yzxVdVo_u24lambda_u240$iv.setLayoutDirection(layoutDirection$iv);
        $this$draw_yzxVdVo_u24lambda_u240$iv.setCanvas(canvas$iv);
        $this$draw_yzxVdVo_u24lambda_u240$iv.m5807setSizeuvyYCjk(size$iv);
        canvas$iv.save();
        CanvasDrawScope $this$createHandleImage_u24lambda_u240 = this_$iv;
        DrawScope.m5881drawRectnJ9OG0$default($this$createHandleImage_u24lambda_u240, Color.INSTANCE.m5339getBlack0d7_KjU(), 0L, $this$createHandleImage_u24lambda_u240.mo5887getSizeNHjbRc(), 0.0f, null, null, BlendMode.INSTANCE.m5226getClear0nO6VwU(), 58, null);
        long jColor = ColorKt.Color(4278190080L);
        long jM5084getZeroF1C5BW0 = Offset.INSTANCE.m5084getZeroF1C5BW0();
        long v1$iv$iv2 = Float.floatToRawIntBits(radius);
        long v2$iv$iv2 = Float.floatToRawIntBits(radius);
        DrawScope.m5881drawRectnJ9OG0$default($this$createHandleImage_u24lambda_u240, jColor, jM5084getZeroF1C5BW0, Size.m5128constructorimpl((v1$iv$iv2 << 32) | (v2$iv$iv2 & 4294967295L)), 0.0f, null, null, 0, 120, null);
        long jColor2 = ColorKt.Color(4278190080L);
        long v1$iv$iv3 = Float.floatToRawIntBits(radius);
        long v2$iv$iv3 = Float.floatToRawIntBits(radius);
        ImageBitmap imageBitmap4 = imageBitmap;
        DrawScope.m5868drawCircleVaOC9Bg$default($this$createHandleImage_u24lambda_u240, jColor2, radius, Offset.m5060constructorimpl((v1$iv$iv3 << 32) | (v2$iv$iv3 & 4294967295L)), 0.0f, null, null, 0, 120, null);
        canvas$iv.restore();
        CanvasDrawScope.DrawParams $this$draw_yzxVdVo_u24lambda_u241$iv = this_$iv.getDrawParams();
        $this$draw_yzxVdVo_u24lambda_u241$iv.setDensity(prevDensity$iv);
        $this$draw_yzxVdVo_u24lambda_u241$iv.setLayoutDirection(prevLayoutDirection$iv);
        $this$draw_yzxVdVo_u24lambda_u241$iv.setCanvas(prevCanvas$iv);
        $this$draw_yzxVdVo_u24lambda_u241$iv.m5807setSizeuvyYCjk(prevSize$iv);
        return imageBitmap4;
    }

    public static final void HandlePopup(final OffsetProvider positionProvider, final Alignment handleReferencePoint, final Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer, final int $changed) {
        Composer $composer2 = $composer.startRestartGroup(-1090171650);
        ComposerKt.sourceInformation($composer2, "C(HandlePopup)N(positionProvider,handleReferencePoint,content)221@8864L135,224@9004L190:AndroidSelectionHandles.android.kt#eksfi3");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= ($changed & 8) == 0 ? $composer2.changed(positionProvider) : $composer2.changedInstance(positionProvider) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(handleReferencePoint) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer2.changedInstance(function2) ? 256 : 128;
        }
        int $dirty2 = $dirty;
        boolean z = false;
        if (!$composer2.shouldExecute(($dirty2 & 147) != 146, $dirty2 & 1)) {
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1090171650, $dirty2, -1, "androidx.compose.foundation.text.selection.HandlePopup (AndroidSelectionHandles.android.kt:219)");
            }
            ComposerKt.sourceInformationMarkerStart($composer2, 317070245, "CC(remember):AndroidSelectionHandles.android.kt#9igjgp");
            boolean z2 = ($dirty2 & 112) == 32;
            if (($dirty2 & 14) == 4 || (($dirty2 & 8) != 0 && $composer2.changed(positionProvider))) {
                z = true;
            }
            boolean invalid$iv = z2 | z;
            Object it$iv = $composer2.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = new HandlePositionProvider(handleReferencePoint, positionProvider);
                $composer2.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            HandlePositionProvider popupPositionProvider = (HandlePositionProvider) it$iv;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            AndroidPopup_androidKt.Popup(popupPositionProvider, null, new PopupProperties(false, false, false, (SecureFlagPolicy) null, true, false, 15, (DefaultConstructorMarker) null), function2, $composer2, (($dirty2 << 3) & 7168) | 384, 2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.foundation.text.selection.AndroidSelectionHandles_androidKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AndroidSelectionHandles_androidKt.HandlePopup$lambda$1(positionProvider, handleReferencePoint, function2, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }
}
