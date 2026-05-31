package androidx.compose.foundation.text;

import androidx.compose.foundation.text.TextFieldDelegate;
import androidx.compose.foundation.text.selection.SelectionManagerKt;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Matrix;
import androidx.compose.ui.graphics.Paint;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LayoutCoordinatesKt;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.PlatformSpanStyle;
import androidx.compose.ui.text.SpanStyle;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextPainter;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontSynthesis;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.input.EditCommand;
import androidx.compose.ui.text.input.EditProcessor;
import androidx.compose.ui.text.input.ImeAction;
import androidx.compose.ui.text.input.ImeOptions;
import androidx.compose.ui.text.input.OffsetMapping;
import androidx.compose.ui.text.input.TextFieldValue;
import androidx.compose.ui.text.input.TextInputService;
import androidx.compose.ui.text.input.TextInputSession;
import androidx.compose.ui.text.input.TransformedText;
import androidx.compose.ui.text.intl.LocaleList;
import androidx.compose.ui.text.style.BaselineShift;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextGeometricTransform;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.window.reflection.WindowExtensionsConstants;
import java.util.List;
import kotlin.Metadata;
import kotlin.Triple;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Ref;

/* JADX INFO: compiled from: TextFieldDelegate.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\b\u0001\u0018\u0000 \u00042\u00020\u0001:\u0001\u0004B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0005"}, d2 = {"Landroidx/compose/foundation/text/TextFieldDelegate;", "", "<init>", "()V", "Companion", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class TextFieldDelegate {
    public static final int $stable = 0;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: compiled from: TextFieldDelegate.kt */
    @Metadata(d1 = {"\u0000¶\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003JE\u0010\u0004\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00052\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0007H\u0001¢\u0006\u0004\b\u000f\u0010\u0010JO\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u00072\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 H\u0001¢\u0006\u0004\b!\u0010\"J7\u0010#\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010$\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u00072\u0006\u0010%\u001a\u00020\u001eH\u0002¢\u0006\u0004\b&\u0010'JE\u0010(\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u001c\u001a\u00020\u00072\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020.2\u0006\u0010\u001a\u001a\u00020\u001bH\u0001¢\u0006\u0002\b/J-\u00100\u001a\u00020\u00122\u0006\u0010+\u001a\u00020,2\u0006\u00101\u001a\u00020\u00162\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u000202H\u0001¢\u0006\u0002\b3JA\u00104\u001a\u00020\u00122\f\u00105\u001a\b\u0012\u0004\u0012\u000207062\u0006\u00108\u001a\u0002092\u0012\u0010:\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00120;2\b\u0010<\u001a\u0004\u0018\u00010,H\u0001¢\u0006\u0002\b=JC\u0010>\u001a\u00020\u00122\u0006\u0010?\u001a\u00020@2\u0006\u0010\u001c\u001a\u0002022\u0006\u00108\u001a\u0002092\u0006\u0010\u001a\u001a\u00020\u001b2\u0012\u0010:\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00120;H\u0001¢\u0006\u0004\bA\u0010BJU\u0010C\u001a\u00020,2\u0006\u0010D\u001a\u00020E2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u00108\u001a\u0002092\u0006\u0010F\u001a\u00020G2\u0012\u0010:\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00120;2\u0012\u0010H\u001a\u000e\u0012\u0004\u0012\u00020I\u0012\u0004\u0012\u00020\u00120;H\u0001¢\u0006\u0002\bJJU\u0010K\u001a\u00020,2\u0006\u0010D\u001a\u00020E2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u00108\u001a\u0002092\u0006\u0010F\u001a\u00020G2\u0012\u0010:\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00120;2\u0012\u0010H\u001a\u000e\u0012\u0004\u0012\u00020I\u0012\u0004\u0012\u00020\u00120;H\u0001¢\u0006\u0002\bLJ1\u0010M\u001a\u00020\u00122\u0006\u0010+\u001a\u00020,2\u0006\u00108\u001a\u0002092\u0012\u0010:\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00120;H\u0001¢\u0006\u0002\bNJ\u001d\u0010O\u001a\u00020P2\u0006\u0010Q\u001a\u00020\u00182\u0006\u0010R\u001a\u00020P¢\u0006\u0004\bS\u0010T¨\u0006U"}, d2 = {"Landroidx/compose/foundation/text/TextFieldDelegate$Companion;", "", "<init>", "()V", WindowExtensionsConstants.LAYOUT_PACKAGE, "Lkotlin/Triple;", "", "Landroidx/compose/ui/text/TextLayoutResult;", "textDelegate", "Landroidx/compose/foundation/text/TextDelegate;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "prevResultText", "layout-_EkL_-Y$foundation", "(Landroidx/compose/foundation/text/TextDelegate;JLandroidx/compose/ui/unit/LayoutDirection;Landroidx/compose/ui/text/TextLayoutResult;)Lkotlin/Triple;", "draw", "", "canvas", "Landroidx/compose/ui/graphics/Canvas;", "value", "Landroidx/compose/ui/text/input/TextFieldValue;", "selectionPreviewHighlightRange", "Landroidx/compose/ui/text/TextRange;", "deletionPreviewHighlightRange", "offsetMapping", "Landroidx/compose/ui/text/input/OffsetMapping;", "textLayoutResult", "highlightPaint", "Landroidx/compose/ui/graphics/Paint;", "selectionBackgroundColor", "Landroidx/compose/ui/graphics/Color;", "draw-Q1vqE60$foundation", "(Landroidx/compose/ui/graphics/Canvas;Landroidx/compose/ui/text/input/TextFieldValue;JJLandroidx/compose/ui/text/input/OffsetMapping;Landroidx/compose/ui/text/TextLayoutResult;Landroidx/compose/ui/graphics/Paint;J)V", "drawHighlight", "range", "paint", "drawHighlight-Le-punE", "(Landroidx/compose/ui/graphics/Canvas;JLandroidx/compose/ui/text/input/OffsetMapping;Landroidx/compose/ui/text/TextLayoutResult;Landroidx/compose/ui/graphics/Paint;)V", "notifyFocusedRect", "layoutCoordinates", "Landroidx/compose/ui/layout/LayoutCoordinates;", "textInputSession", "Landroidx/compose/ui/text/input/TextInputSession;", "hasFocus", "", "notifyFocusedRect$foundation", "updateTextLayoutResult", "textFieldValue", "Landroidx/compose/foundation/text/TextLayoutResultProxy;", "updateTextLayoutResult$foundation", "onEditCommand", "ops", "", "Landroidx/compose/ui/text/input/EditCommand;", "editProcessor", "Landroidx/compose/ui/text/input/EditProcessor;", "onValueChange", "Lkotlin/Function1;", "session", "onEditCommand$foundation", "setCursorOffset", "position", "Landroidx/compose/ui/geometry/Offset;", "setCursorOffset-ULxng0E$foundation", "(JLandroidx/compose/foundation/text/TextLayoutResultProxy;Landroidx/compose/ui/text/input/EditProcessor;Landroidx/compose/ui/text/input/OffsetMapping;Lkotlin/jvm/functions/Function1;)V", "restartInput", "textInputService", "Landroidx/compose/ui/text/input/TextInputService;", "imeOptions", "Landroidx/compose/ui/text/input/ImeOptions;", "onImeActionPerformed", "Landroidx/compose/ui/text/input/ImeAction;", "restartInput$foundation", "onFocus", "onFocus$foundation", "onBlur", "onBlur$foundation", "applyCompositionDecoration", "Landroidx/compose/ui/text/input/TransformedText;", "compositionRange", "transformed", "applyCompositionDecoration-72CqOWE", "(JLandroidx/compose/ui/text/input/TransformedText;)Landroidx/compose/ui/text/input/TransformedText;", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: renamed from: layout-_EkL_-Y$foundation$default, reason: not valid java name */
        public static /* synthetic */ Triple m1646layout_EkL_Y$foundation$default(Companion companion, TextDelegate textDelegate, long j, LayoutDirection layoutDirection, TextLayoutResult textLayoutResult, int i, Object obj) {
            TextLayoutResult textLayoutResult2;
            if ((i & 8) == 0) {
                textLayoutResult2 = textLayoutResult;
            } else {
                textLayoutResult2 = null;
            }
            return companion.m1649layout_EkL_Y$foundation(textDelegate, j, layoutDirection, textLayoutResult2);
        }

        @JvmStatic
        /* JADX INFO: renamed from: layout-_EkL_-Y$foundation, reason: not valid java name */
        public final Triple<Integer, Integer, TextLayoutResult> m1649layout_EkL_Y$foundation(TextDelegate textDelegate, long constraints, LayoutDirection layoutDirection, TextLayoutResult prevResultText) {
            TextLayoutResult layoutResult = textDelegate.m1636layoutNN6EwU(constraints, layoutDirection, prevResultText);
            long arg0$iv = layoutResult.getSize();
            Integer numValueOf = Integer.valueOf((int) (arg0$iv >> 32));
            long arg0$iv2 = layoutResult.getSize();
            return new Triple<>(numValueOf, Integer.valueOf((int) (4294967295L & arg0$iv2)), layoutResult);
        }

        @JvmStatic
        /* JADX INFO: renamed from: draw-Q1vqE60$foundation, reason: not valid java name */
        public final void m1648drawQ1vqE60$foundation(Canvas canvas, TextFieldValue value, long selectionPreviewHighlightRange, long deletionPreviewHighlightRange, OffsetMapping offsetMapping, TextLayoutResult textLayoutResult, Paint highlightPaint, long selectionBackgroundColor) {
            if (!TextRange.m7567getCollapsedimpl(selectionPreviewHighlightRange)) {
                highlightPaint.mo5189setColor8_81llA(selectionBackgroundColor);
                m1645drawHighlightLepunE(canvas, selectionPreviewHighlightRange, offsetMapping, textLayoutResult, highlightPaint);
            } else if (!TextRange.m7567getCollapsedimpl(deletionPreviewHighlightRange)) {
                Color colorM5303boximpl = Color.m5303boximpl(textLayoutResult.getLayoutInput().getStyle().m7603getColor0d7_KjU());
                long it = colorM5303boximpl.m5323unboximpl();
                if (it == 16) {
                    colorM5303boximpl = null;
                }
                long textColor = colorM5303boximpl != null ? colorM5303boximpl.m5323unboximpl() : Color.INSTANCE.m5339getBlack0d7_KjU();
                highlightPaint.mo5189setColor8_81llA(Color.m5311copywmQWz5c(textColor, (14 & 1) != 0 ? Color.m5315getAlphaimpl(textColor) : Color.m5315getAlphaimpl(textColor) * 0.2f, (14 & 2) != 0 ? Color.m5319getRedimpl(textColor) : 0.0f, (14 & 4) != 0 ? Color.m5318getGreenimpl(textColor) : 0.0f, (14 & 8) != 0 ? Color.m5316getBlueimpl(textColor) : 0.0f));
                m1645drawHighlightLepunE(canvas, deletionPreviewHighlightRange, offsetMapping, textLayoutResult, highlightPaint);
            } else if (!TextRange.m7567getCollapsedimpl(value.getSelection())) {
                highlightPaint.mo5189setColor8_81llA(selectionBackgroundColor);
                m1645drawHighlightLepunE(canvas, value.getSelection(), offsetMapping, textLayoutResult, highlightPaint);
            }
            TextPainter.INSTANCE.paint(canvas, textLayoutResult);
        }

        /* JADX INFO: renamed from: drawHighlight-Le-punE, reason: not valid java name */
        private final void m1645drawHighlightLepunE(Canvas canvas, long range, OffsetMapping offsetMapping, TextLayoutResult textLayoutResult, Paint paint) {
            int start = offsetMapping.originalToTransformed(TextRange.m7571getMinimpl(range));
            int end = offsetMapping.originalToTransformed(TextRange.m7570getMaximpl(range));
            if (start != end) {
                Path selectionPath = textLayoutResult.getPathForRange(start, end);
                canvas.drawPath(selectionPath, paint);
            }
        }

        @JvmStatic
        public final void notifyFocusedRect$foundation(TextFieldValue value, final TextDelegate textDelegate, TextLayoutResult textLayoutResult, LayoutCoordinates layoutCoordinates, TextInputSession textInputSession, boolean hasFocus, OffsetMapping offsetMapping) {
            if (!hasFocus) {
                return;
            }
            textInputSession.notifyFocusedRect(TextFieldDelegateKt.focusedRectInRoot(textLayoutResult, layoutCoordinates, offsetMapping.originalToTransformed(TextRange.m7570getMaximpl(value.getSelection())), new Function0() { // from class: androidx.compose.foundation.text.TextFieldDelegate$Companion$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    TextDelegate textDelegate2 = textDelegate;
                    return IntSize.m8313boximpl(TextFieldDelegateKt.computeSizeForDefaultText$default(textDelegate2.getStyle(), textDelegate2.getDensity(), textDelegate2.getFontFamilyResolver(), null, 0, 24, null));
                }
            }));
        }

        @JvmStatic
        public final void updateTextLayoutResult$foundation(TextInputSession textInputSession, TextFieldValue textFieldValue, OffsetMapping offsetMapping, TextLayoutResultProxy textLayoutResult) {
            LayoutCoordinates decorationBoxCoordinates;
            final LayoutCoordinates innerTextFieldCoordinates = textLayoutResult.getInnerTextFieldCoordinates();
            if (innerTextFieldCoordinates != null && innerTextFieldCoordinates.isAttached() && (decorationBoxCoordinates = textLayoutResult.getDecorationBoxCoordinates()) != null) {
                textInputSession.updateTextLayoutResult(textFieldValue, offsetMapping, textLayoutResult.getValue(), new Function1<Matrix, Unit>() { // from class: androidx.compose.foundation.text.TextFieldDelegate$Companion$updateTextLayoutResult$1$1$1
                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Matrix matrix) {
                        m1651invoke58bKbWc(matrix.m5580unboximpl());
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke-58bKbWc, reason: not valid java name */
                    public final void m1651invoke58bKbWc(float[] matrix) {
                        if (innerTextFieldCoordinates.isAttached()) {
                            LayoutCoordinatesKt.findRootCoordinates(innerTextFieldCoordinates).mo6798transformFromEL8BTi8(innerTextFieldCoordinates, matrix);
                        }
                    }
                }, SelectionManagerKt.visibleBounds(innerTextFieldCoordinates), innerTextFieldCoordinates.localBoundingBoxOf(decorationBoxCoordinates, false));
            }
        }

        @JvmStatic
        public final void onEditCommand$foundation(List<? extends EditCommand> ops, EditProcessor editProcessor, Function1<? super TextFieldValue, Unit> onValueChange, TextInputSession session) {
            TextFieldValue newValue = editProcessor.apply(ops);
            if (session != null) {
                session.updateState(null, newValue);
            }
            onValueChange.invoke(newValue);
        }

        @JvmStatic
        /* JADX INFO: renamed from: setCursorOffset-ULxng0E$foundation, reason: not valid java name */
        public final void m1650setCursorOffsetULxng0E$foundation(long position, TextLayoutResultProxy textLayoutResult, EditProcessor editProcessor, OffsetMapping offsetMapping, Function1<? super TextFieldValue, Unit> onValueChange) {
            int offset = offsetMapping.transformedToOriginal(TextLayoutResultProxy.m1676getOffsetForPosition3MmeM6k$default(textLayoutResult, position, false, 2, null));
            onValueChange.invoke(TextFieldValue.m7818copy3r_uNRQ$default(editProcessor.toTextFieldValue(), (AnnotatedString) null, TextRangeKt.TextRange(offset), (TextRange) null, 5, (Object) null));
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r1v1, types: [T, androidx.compose.ui.text.input.TextInputSession] */
        @JvmStatic
        public final TextInputSession restartInput$foundation(TextInputService textInputService, TextFieldValue value, final EditProcessor editProcessor, ImeOptions imeOptions, final Function1<? super TextFieldValue, Unit> onValueChange, Function1<? super ImeAction, Unit> onImeActionPerformed) {
            final Ref.ObjectRef session = new Ref.ObjectRef();
            session.element = textInputService.startInput(value, imeOptions, new Function1() { // from class: androidx.compose.foundation.text.TextFieldDelegate$Companion$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return TextFieldDelegate.Companion.restartInput$lambda$0(editProcessor, onValueChange, session, (List) obj);
                }
            }, onImeActionPerformed);
            return (TextInputSession) session.element;
        }

        /* JADX WARN: Multi-variable type inference failed */
        static final Unit restartInput$lambda$0(EditProcessor $editProcessor, Function1 $onValueChange, Ref.ObjectRef $session, List it) {
            TextFieldDelegate.INSTANCE.onEditCommand$foundation(it, $editProcessor, $onValueChange, (TextInputSession) $session.element);
            return Unit.INSTANCE;
        }

        @JvmStatic
        public final TextInputSession onFocus$foundation(TextInputService textInputService, TextFieldValue value, EditProcessor editProcessor, ImeOptions imeOptions, Function1<? super TextFieldValue, Unit> onValueChange, Function1<? super ImeAction, Unit> onImeActionPerformed) {
            return restartInput$foundation(textInputService, value, editProcessor, imeOptions, onValueChange, onImeActionPerformed);
        }

        @JvmStatic
        public final void onBlur$foundation(TextInputSession textInputSession, EditProcessor editProcessor, Function1<? super TextFieldValue, Unit> onValueChange) {
            onValueChange.invoke(TextFieldValue.m7818copy3r_uNRQ$default(editProcessor.toTextFieldValue(), (AnnotatedString) null, 0L, (TextRange) null, 3, (Object) null));
            textInputSession.dispose();
        }

        /* JADX INFO: renamed from: applyCompositionDecoration-72CqOWE, reason: not valid java name */
        public final TransformedText m1647applyCompositionDecoration72CqOWE(long compositionRange, TransformedText transformed) {
            int startPositionTransformed = transformed.getOffsetMapping().originalToTransformed(TextRange.m7573getStartimpl(compositionRange));
            int endPositionTransformed = transformed.getOffsetMapping().originalToTransformed(TextRange.m7568getEndimpl(compositionRange));
            int start = Math.min(startPositionTransformed, endPositionTransformed);
            int coercedEnd = Math.max(startPositionTransformed, endPositionTransformed);
            AnnotatedString.Builder $this$applyCompositionDecoration_72CqOWE_u24lambda_u240 = new AnnotatedString.Builder(transformed.getText());
            $this$applyCompositionDecoration_72CqOWE_u24lambda_u240.addStyle(new SpanStyle(0L, 0L, (FontWeight) null, (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, TextDecoration.INSTANCE.getUnderline(), (Shadow) null, (PlatformSpanStyle) null, (DrawStyle) null, 61439, (DefaultConstructorMarker) null), start, coercedEnd);
            return new TransformedText($this$applyCompositionDecoration_72CqOWE_u24lambda_u240.toAnnotatedString(), transformed.getOffsetMapping());
        }
    }
}
