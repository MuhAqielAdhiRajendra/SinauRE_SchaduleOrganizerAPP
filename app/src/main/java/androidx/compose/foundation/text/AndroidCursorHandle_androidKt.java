package androidx.compose.foundation.text;

import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.text.selection.AndroidSelectionHandles_androidKt;
import androidx.compose.foundation.text.selection.OffsetProvider;
import androidx.compose.foundation.text.selection.SelectionColors;
import androidx.compose.foundation.text.selection.SelectionHandleAnchor;
import androidx.compose.foundation.text.selection.SelectionHandleInfo;
import androidx.compose.foundation.text.selection.SelectionHandlesKt;
import androidx.compose.foundation.text.selection.TextSelectionColorsKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.CacheDrawScope;
import androidx.compose.ui.draw.DrawModifierKt;
import androidx.compose.ui.draw.DrawResult;
import androidx.compose.ui.geometry.InlineClassHelperKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.ImageBitmap;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawContext;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.DrawTransform;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.DpSize;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: AndroidCursorHandle.android.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a)\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u0010H\u0001¢\u0006\u0004\b\u0011\u0010\u0012\u001a\u0017\u0010\u0013\u001a\u00020\n2\b\b\u0002\u0010\r\u001a\u00020\u000eH\u0003¢\u0006\u0002\u0010\u0014\u001a\u001b\u0010\u0015\u001a\u00020\u000e*\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u0017H\u0002¢\u0006\u0004\b\u0018\u0010\u0019\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u0016\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\n\n\u0002\u0010\u0006\u001a\u0004\b\u0004\u0010\u0005\"\u0016\u0010\u0007\u001a\u00020\u0003X\u0080\u0004¢\u0006\n\n\u0002\u0010\u0006\u001a\u0004\b\b\u0010\u0005¨\u0006\u001a"}, d2 = {"Sqrt2", "", "CursorHandleHeight", "Landroidx/compose/ui/unit/Dp;", "getCursorHandleHeight", "()F", "F", "CursorHandleWidth", "getCursorHandleWidth", "CursorHandle", "", "offsetProvider", "Landroidx/compose/foundation/text/selection/OffsetProvider;", "modifier", "Landroidx/compose/ui/Modifier;", "minTouchTargetSize", "Landroidx/compose/ui/unit/DpSize;", "CursorHandle-USBMPiE", "(Landroidx/compose/foundation/text/selection/OffsetProvider;Landroidx/compose/ui/Modifier;JLandroidx/compose/runtime/Composer;II)V", "DefaultCursorHandle", "(Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "drawCursorHandle", "handleColor", "Landroidx/compose/ui/graphics/Color;", "drawCursorHandle-4WTKRHQ", "(Landroidx/compose/ui/Modifier;J)Landroidx/compose/ui/Modifier;", "foundation"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class AndroidCursorHandle_androidKt {
    private static final float CursorHandleHeight = Dp.m8150constructorimpl(25);
    private static final float CursorHandleWidth;
    private static final float Sqrt2 = 1.4142135f;

    static final Unit CursorHandle_USBMPiE$lambda$2(OffsetProvider offsetProvider, Modifier modifier, long j, int i, int i2, Composer composer, int i3) {
        m1472CursorHandleUSBMPiE(offsetProvider, modifier, j, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit DefaultCursorHandle$lambda$0(Modifier modifier, int i, int i2, Composer composer, int i3) {
        DefaultCursorHandle(modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static {
        float arg0$iv = CursorHandleHeight;
        CursorHandleWidth = Dp.m8150constructorimpl(Dp.m8150constructorimpl(arg0$iv * 2.0f) / 2.4142137f);
    }

    public static final float getCursorHandleHeight() {
        return CursorHandleHeight;
    }

    public static final float getCursorHandleWidth() {
        return CursorHandleWidth;
    }

    /* JADX INFO: renamed from: CursorHandle-USBMPiE, reason: not valid java name */
    public static final void m1472CursorHandleUSBMPiE(final OffsetProvider offsetProvider, final Modifier modifier, long minTouchTargetSize, Composer $composer, final int $changed, final int i) {
        final long minTouchTargetSize2;
        final long minTouchTargetSize3;
        Composer $composer2 = $composer.startRestartGroup(1776202187);
        ComposerKt.sourceInformation($composer2, "C(CursorHandle)N(offsetProvider,modifier,minTouchTargetSize:c#ui.unit.DpSize)53@2207L305,62@2608L492,62@2517L583:AndroidCursorHandle.android.kt#423gt5");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= ($changed & 8) == 0 ? $composer2.changed(offsetProvider) : $composer2.changedInstance(offsetProvider) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(modifier) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                minTouchTargetSize2 = minTouchTargetSize;
                int i2 = $composer2.changed(minTouchTargetSize2) ? 256 : 128;
                $dirty |= i2;
            } else {
                minTouchTargetSize2 = minTouchTargetSize;
            }
            $dirty |= i2;
        } else {
            minTouchTargetSize2 = minTouchTargetSize;
        }
        if ($composer2.shouldExecute(($dirty & 147) != 146, $dirty & 1)) {
            $composer2.startDefaults();
            if (($changed & 1) != 0 && !$composer2.getDefaultsInvalid()) {
                $composer2.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty &= -897;
                }
            } else if ((i & 4) != 0) {
                minTouchTargetSize2 = DpSize.INSTANCE.m8257getUnspecifiedMYxV2XQ();
                $dirty &= -897;
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1776202187, $dirty, -1, "androidx.compose.foundation.text.CursorHandle (AndroidCursorHandle.android.kt:51)");
            }
            ComposerKt.sourceInformationMarkerStart($composer2, 1828290780, "CC(remember):AndroidCursorHandle.android.kt#9igjgp");
            boolean invalid$iv = ($dirty & 14) == 4 || (($dirty & 8) != 0 && $composer2.changedInstance(offsetProvider));
            Object it$iv = $composer2.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = new Function1() { // from class: androidx.compose.foundation.text.AndroidCursorHandle_androidKt$$ExternalSyntheticLambda3
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return AndroidCursorHandle_androidKt.CursorHandle_USBMPiE$lambda$0$0(offsetProvider, (SemanticsPropertyReceiver) obj);
                    }
                };
                $composer2.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            final Modifier finalModifier = SemanticsModifierKt.semantics$default(modifier, false, (Function1) it$iv, 1, null);
            AndroidSelectionHandles_androidKt.HandlePopup(offsetProvider, Alignment.INSTANCE.getTopCenter(), ComposableLambdaKt.rememberComposableLambda(-1653527038, true, new Function2() { // from class: androidx.compose.foundation.text.AndroidCursorHandle_androidKt$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AndroidCursorHandle_androidKt.CursorHandle_USBMPiE$lambda$1(minTouchTargetSize2, finalModifier, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer2, 54), $composer2, ($dirty & 14) | 432);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            minTouchTargetSize3 = minTouchTargetSize2;
        } else {
            $composer2.skipToGroupEnd();
            minTouchTargetSize3 = minTouchTargetSize2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.foundation.text.AndroidCursorHandle_androidKt$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AndroidCursorHandle_androidKt.CursorHandle_USBMPiE$lambda$2(offsetProvider, modifier, minTouchTargetSize3, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit CursorHandle_USBMPiE$lambda$0$0(OffsetProvider $offsetProvider, SemanticsPropertyReceiver $this$semantics) {
        $this$semantics.set(SelectionHandlesKt.getSelectionHandleInfoKey(), new SelectionHandleInfo(Handle.Cursor, $offsetProvider.mo1487provideF1C5BW0(), SelectionHandleAnchor.Middle, true, null));
        return Unit.INSTANCE;
    }

    static final Unit CursorHandle_USBMPiE$lambda$1(long $minTouchTargetSize, Modifier $finalModifier, Composer $composer, int $changed) {
        Function0<ComposeUiNode> function0;
        ComposerKt.sourceInformation($composer, "C:AndroidCursorHandle.android.kt#423gt5");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1653527038, $changed, -1, "androidx.compose.foundation.text.CursorHandle.<anonymous> (AndroidCursorHandle.android.kt:63)");
            }
            if ($minTouchTargetSize != InlineClassHelperKt.UnspecifiedPackedFloats) {
                $composer.startReplaceGroup(-1244013944);
                ComposerKt.sourceInformation($composer, "64@2668L352");
                Modifier modifier$iv = SizeKt.m1111requiredSizeInqDBjuR0$default($finalModifier, DpSize.m8248getWidthD9Ej5fM($minTouchTargetSize), DpSize.m8246getHeightD9Ej5fM($minTouchTargetSize), 0.0f, 0.0f, 12, null);
                Alignment contentAlignment$iv = Alignment.INSTANCE.getTopCenter();
                ComposerKt.sourceInformationMarkerStart($composer, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
                MeasurePolicy measurePolicy$iv = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
                int $changed$iv$iv = (48 << 3) & 112;
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
                ComposerKt.sourceInformationMarkerStart($composer, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                int i2 = ((48 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, 922244333, "C72@2985L21:AndroidCursorHandle.android.kt#423gt5");
                DefaultCursorHandle(null, $composer, 0, 1);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endReplaceGroup();
            } else {
                $composer.startReplaceGroup(-1243644858);
                ComposerKt.sourceInformation($composer, "75@3050L34");
                DefaultCursorHandle($finalModifier, $composer, 0, 0);
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

    private static final void DefaultCursorHandle(final Modifier modifier, Composer $composer, final int $changed, final int i) {
        Composer $composer2 = $composer.startRestartGroup(694251107);
        ComposerKt.sourceInformation($composer2, "C(DefaultCursorHandle)N(modifier)86@3345L7,83@3208L163:AndroidCursorHandle.android.kt#423gt5");
        int $dirty = $changed;
        int i2 = i & 1;
        if (i2 != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(modifier) ? 4 : 2;
        }
        if (!$composer2.shouldExecute(($dirty & 3) != 2, $dirty & 1)) {
            $composer2.skipToGroupEnd();
        } else {
            if (i2 != 0) {
                modifier = Modifier.INSTANCE;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(694251107, $dirty, -1, "androidx.compose.foundation.text.DefaultCursorHandle (AndroidCursorHandle.android.kt:82)");
            }
            Modifier modifierM1117sizeVpY3zN4 = SizeKt.m1117sizeVpY3zN4(modifier, CursorHandleWidth, CursorHandleHeight);
            ProvidableCompositionLocal<SelectionColors> localTextSelectionColors = TextSelectionColorsKt.getLocalTextSelectionColors();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = $composer2.consume(localTextSelectionColors);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            SpacerKt.Spacer(m1473drawCursorHandle4WTKRHQ(modifierM1117sizeVpY3zN4, ((SelectionColors) objConsume).getSelectionHandleColor()), $composer2, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.foundation.text.AndroidCursorHandle_androidKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AndroidCursorHandle_androidKt.DefaultCursorHandle$lambda$0(modifier, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: drawCursorHandle-4WTKRHQ, reason: not valid java name */
    private static final Modifier m1473drawCursorHandle4WTKRHQ(Modifier $this$drawCursorHandle_u2d4WTKRHQ, final long handleColor) {
        return DrawModifierKt.drawWithCache($this$drawCursorHandle_u2d4WTKRHQ, new Function1() { // from class: androidx.compose.foundation.text.AndroidCursorHandle_androidKt$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AndroidCursorHandle_androidKt.drawCursorHandle_4WTKRHQ$lambda$0(handleColor, (CacheDrawScope) obj);
            }
        });
    }

    static final DrawResult drawCursorHandle_4WTKRHQ$lambda$0(long $handleColor, CacheDrawScope $this$drawWithCache) {
        long arg0$iv = $this$drawWithCache.m4848getSizeNHjbRc();
        int bits$iv$iv$iv = (int) (arg0$iv >> 32);
        final float radius = Float.intBitsToFloat(bits$iv$iv$iv) / 2.0f;
        final ImageBitmap imageBitmap = AndroidSelectionHandles_androidKt.createHandleImage($this$drawWithCache, radius);
        final ColorFilter colorFilter = ColorFilter.Companion.m5354tintxETnrds$default(ColorFilter.INSTANCE, $handleColor, 0, 2, null);
        return $this$drawWithCache.onDrawWithContent(new Function1() { // from class: androidx.compose.foundation.text.AndroidCursorHandle_androidKt$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AndroidCursorHandle_androidKt.drawCursorHandle_4WTKRHQ$lambda$0$0(radius, imageBitmap, colorFilter, (ContentDrawScope) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit drawCursorHandle_4WTKRHQ$lambda$0$0(float $radius, ImageBitmap $imageBitmap, ColorFilter $colorFilter, ContentDrawScope $this$onDrawWithContent) throws Throwable {
        DrawTransform $this$drawCursorHandle_4WTKRHQ_u24lambda_u240_u240_u240;
        $this$onDrawWithContent.drawContent();
        ContentDrawScope $this$withTransform$iv = $this$onDrawWithContent;
        DrawContext $this$withTransform_u24lambda_u240$iv = $this$withTransform$iv.getDrawContext();
        long previousSize$iv = $this$withTransform_u24lambda_u240$iv.mo5808getSizeNHjbRc();
        $this$withTransform_u24lambda_u240$iv.getCanvas().save();
        try {
            $this$drawCursorHandle_4WTKRHQ_u24lambda_u240_u240_u240 = $this$withTransform_u24lambda_u240$iv.getTransform();
        } catch (Throwable th) {
            th = th;
        }
        try {
            DrawTransform.translate$default($this$drawCursorHandle_4WTKRHQ_u24lambda_u240_u240_u240, $radius, 0.0f, 2, null);
            $this$drawCursorHandle_4WTKRHQ_u24lambda_u240_u240_u240.mo5814rotateUv8p0NA(45.0f, Offset.INSTANCE.m5084getZeroF1C5BW0());
            DrawScope.m5871drawImagegbVJVH8$default($this$withTransform$iv, $imageBitmap, 0L, 0.0f, null, $colorFilter, 0, 46, null);
            $this$withTransform_u24lambda_u240$iv.getCanvas().restore();
            $this$withTransform_u24lambda_u240$iv.mo5809setSizeuvyYCjk(previousSize$iv);
            return Unit.INSTANCE;
        } catch (Throwable th2) {
            th = th2;
            $this$withTransform_u24lambda_u240$iv.getCanvas().restore();
            $this$withTransform_u24lambda_u240$iv.mo5809setSizeuvyYCjk(previousSize$iv);
            throw th;
        }
    }
}
