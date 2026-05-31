package androidx.compose.foundation.text;

import androidx.compose.foundation.text.input.internal.CursorAnimationState;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.DrawModifierKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.SolidColor;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.WindowInfo;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.input.OffsetMapping;
import androidx.compose.ui.text.input.TextFieldValue;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: TextFieldCursor.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000&\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u001a4\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0000¨\u0006\f"}, d2 = {"cursor", "Landroidx/compose/ui/Modifier;", "state", "Landroidx/compose/foundation/text/LegacyTextFieldState;", "value", "Landroidx/compose/ui/text/input/TextFieldValue;", "offsetMapping", "Landroidx/compose/ui/text/input/OffsetMapping;", "cursorBrush", "Landroidx/compose/ui/graphics/Brush;", "enabled", "", "foundation"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class TextFieldCursorKt {
    public static final Modifier cursor(Modifier $this$cursor, final LegacyTextFieldState state, final TextFieldValue value, final OffsetMapping offsetMapping, final Brush cursorBrush, boolean enabled) {
        if (enabled) {
            return ComposedModifierKt.composed$default($this$cursor, null, new Function3() { // from class: androidx.compose.foundation.text.TextFieldCursorKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return TextFieldCursorKt.cursor$lambda$0(cursorBrush, state, value, offsetMapping, (Modifier) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, 1, null);
        }
        return $this$cursor;
    }

    static final Modifier cursor$lambda$0(final Brush $cursorBrush, final LegacyTextFieldState $state, final TextFieldValue $value, final OffsetMapping $offsetMapping, Modifier $this$composed, Composer $composer, int $changed) {
        Modifier.Companion companionDrawWithContent;
        $composer.startReplaceGroup(-84507373);
        ComposerKt.sourceInformation($composer, "C46@1735L7,47@1777L63,54@2290L7:TextFieldCursor.kt#423gt5");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-84507373, $changed, -1, "androidx.compose.foundation.text.cursor.<anonymous> (TextFieldCursor.kt:46)");
        }
        ProvidableCompositionLocal<Boolean> localCursorBlinkEnabled = CompositionLocalsKt.getLocalCursorBlinkEnabled();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
        Object objConsume = $composer.consume(localCursorBlinkEnabled);
        ComposerKt.sourceInformationMarkerEnd($composer);
        boolean animateCursor = ((Boolean) objConsume).booleanValue();
        ComposerKt.sourceInformationMarkerStart($composer, 392796434, "CC(remember):TextFieldCursor.kt#9igjgp");
        boolean invalid$iv = $composer.changed(animateCursor);
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            Object value$iv = new CursorAnimationState(animateCursor);
            $composer.updateRememberedValue(value$iv);
            it$iv = value$iv;
        }
        final CursorAnimationState cursorAnimation = (CursorAnimationState) it$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        boolean z = true;
        if ($cursorBrush instanceof SolidColor) {
            long $this$isUnspecified$iv = ((SolidColor) $cursorBrush).getValue();
            if (($this$isUnspecified$iv == 16 ? 1 : 0) != 0) {
                z = false;
            }
        }
        boolean isBrushSpecified = z;
        ProvidableCompositionLocal<WindowInfo> localWindowInfo = CompositionLocalsKt.getLocalWindowInfo();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
        Object objConsume2 = $composer.consume(localWindowInfo);
        ComposerKt.sourceInformationMarkerEnd($composer);
        boolean isWindowFocused = ((WindowInfo) objConsume2).isWindowFocused();
        if (isWindowFocused && $state.getHasFocus() && TextRange.m7567getCollapsedimpl($value.getSelection()) && isBrushSpecified) {
            $composer.startReplaceGroup(-707487962);
            ComposerKt.sourceInformation($composer, "58@2517L81,58@2462L136,61@2631L1888");
            AnnotatedString text = $value.getText();
            TextRange textRangeM7561boximpl = TextRange.m7561boximpl($value.getSelection());
            ComposerKt.sourceInformationMarkerStart($composer, 392820132, "CC(remember):TextFieldCursor.kt#9igjgp");
            boolean invalid$iv2 = $composer.changedInstance(cursorAnimation);
            Object it$iv2 = $composer.rememberedValue();
            if (invalid$iv2 || it$iv2 == Composer.INSTANCE.getEmpty()) {
                Object value$iv2 = (Function2) new TextFieldCursorKt$cursor$1$1$1(cursorAnimation, null);
                $composer.updateRememberedValue(value$iv2);
                it$iv2 = value$iv2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            EffectsKt.LaunchedEffect(text, textRangeM7561boximpl, (Function2) it$iv2, $composer, 0);
            ComposerKt.sourceInformationMarkerStart($composer, 392825587, "CC(remember):TextFieldCursor.kt#9igjgp");
            boolean invalid$iv3 = $composer.changed($cursorBrush) | $composer.changedInstance(cursorAnimation) | $composer.changedInstance($offsetMapping) | $composer.changed($value) | $composer.changedInstance($state);
            Object it$iv3 = $composer.rememberedValue();
            if (invalid$iv3 || it$iv3 == Composer.INSTANCE.getEmpty()) {
                Object value$iv3 = new Function1() { // from class: androidx.compose.foundation.text.TextFieldCursorKt$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return TextFieldCursorKt.cursor$lambda$0$2$0(cursorAnimation, $offsetMapping, $value, $state, $cursorBrush, (ContentDrawScope) obj);
                    }
                };
                $composer.updateRememberedValue(value$iv3);
                it$iv3 = value$iv3;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            companionDrawWithContent = DrawModifierKt.drawWithContent($this$composed, (Function1) it$iv3);
            $composer.endReplaceGroup();
        } else {
            $composer.startReplaceGroup(-705473241);
            $composer.endReplaceGroup();
            companionDrawWithContent = Modifier.INSTANCE;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceGroup();
        return companionDrawWithContent;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit cursor$lambda$0$2$0(CursorAnimationState $cursorAnimation, OffsetMapping $offsetMapping, TextFieldValue $value, LegacyTextFieldState $state, Brush $cursorBrush, ContentDrawScope $this$drawWithContent) {
        Rect rect;
        float x$iv;
        TextLayoutResult value;
        $this$drawWithContent.drawContent();
        float cursorAlphaValue = $cursorAnimation.getCursorAlpha();
        if (!(cursorAlphaValue == 0.0f)) {
            int transformedOffset = $offsetMapping.originalToTransformed(TextRange.m7573getStartimpl($value.getSelection()));
            TextLayoutResultProxy layoutResult = $state.getLayoutResult();
            if (layoutResult == null || (value = layoutResult.getValue()) == null || (rect = value.getCursorRect(transformedOffset)) == null) {
                rect = new Rect(0.0f, 0.0f, 0.0f, 0.0f);
            }
            Rect cursorRect = rect;
            float cursorWidth = RangesKt.coerceAtLeast((float) Math.floor($this$drawWithContent.mo432toPx0680j_4(TextFieldCursor_androidKt.getDefaultCursorThickness())), 1.0f);
            float left = cursorRect.getLeft() + (cursorWidth / 2.0f);
            long arg0$iv = $this$drawWithContent.mo5887getSizeNHjbRc();
            int bits$iv$iv$iv = (int) (arg0$iv >> 32);
            float it = RangesKt.coerceAtLeast(RangesKt.coerceAtMost(left, Float.intBitsToFloat(bits$iv$iv$iv) - (cursorWidth / 2.0f)), cursorWidth / 2.0f);
            if (((int) cursorWidth) % 2 == 1) {
                x$iv = ((float) Math.floor(it)) + 0.5f;
            } else {
                x$iv = (float) Math.rint(it);
            }
            float cursorX = x$iv;
            float y$iv = cursorRect.getTop();
            float val1$iv$iv = x$iv;
            long v1$iv$iv = Float.floatToRawIntBits(val1$iv$iv);
            long v2$iv$iv = Float.floatToRawIntBits(y$iv);
            long jM5060constructorimpl = Offset.m5060constructorimpl((v1$iv$iv << 32) | (v2$iv$iv & 4294967295L));
            float y$iv2 = cursorRect.getBottom();
            long v1$iv$iv2 = Float.floatToRawIntBits(cursorX);
            long v2$iv$iv2 = Float.floatToRawIntBits(y$iv2);
            DrawScope.m5872drawLine1RTmtNc$default($this$drawWithContent, $cursorBrush, jM5060constructorimpl, Offset.m5060constructorimpl((v1$iv$iv2 << 32) | (v2$iv$iv2 & 4294967295L)), cursorWidth, 0, null, cursorAlphaValue, null, 0, 432, null);
        }
        return Unit.INSTANCE;
    }
}
