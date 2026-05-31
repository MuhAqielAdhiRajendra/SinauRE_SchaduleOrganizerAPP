package androidx.compose.foundation.text.input.internal;

import androidx.compose.foundation.internal.InlineClassHelperKt;
import androidx.compose.foundation.text.input.TextFieldBuffer;
import androidx.compose.foundation.text.input.TextFieldBufferKt;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: ImeEditCommand.android.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000H\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\f\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\r\n\u0002\b\u0002\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0000\u001a\u001c\u0010\u0007\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0006H\u0000\u001a8\u0010\n\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u001a\b\u0002\u0010\u000b\u001a\u0014\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u000e0\rj\u0002`\u000f\u0018\u00010\fH\u0000\u001a\u001c\u0010\u0010\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u0006H\u0000\u001a\u001c\u0010\u0013\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u0006H\u0000\u001a\f\u0010\u0014\u001a\u00020\u0001*\u00020\u0002H\u0000\u001a\u001c\u0010\u0015\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0006H\u0000\u001a\u0018\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0019H\u0002\u001a$\u0010\u001b\u001a\u00020\u0001*\u00020\u001c2\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u001dH\u0001\u001a\u001c\u0010\u001e\u001a\u00020\u0001*\u00020\u001c2\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0006H\u0001¨\u0006\u001f"}, d2 = {"commitText", "", "Landroidx/compose/foundation/text/input/internal/ImeEditCommandScope;", "text", "", "newCursorPosition", "", "setComposingRegion", "start", "end", "setComposingText", "annotations", "", "Landroidx/compose/ui/text/AnnotatedString$Range;", "Landroidx/compose/ui/text/AnnotatedString$Annotation;", "Landroidx/compose/foundation/text/input/PlacedAnnotation;", "deleteSurroundingText", "lengthBeforeCursor", "lengthAfterCursor", "deleteSurroundingTextInCodePoints", "finishComposingText", "setSelection", "isSurrogatePair", "", "high", "", "low", "imeReplace", "Landroidx/compose/foundation/text/input/TextFieldBuffer;", "", "imeDelete", "foundation"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class ImeEditCommand_androidKt {
    public static final void commitText(ImeEditCommandScope $this$commitText, final String text, final int newCursorPosition) {
        $this$commitText.edit(new Function1() { // from class: androidx.compose.foundation.text.input.internal.ImeEditCommand_androidKt$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ImeEditCommand_androidKt.commitText$lambda$0(text, newCursorPosition, (TextFieldBuffer) obj);
            }
        });
    }

    static final Unit commitText$lambda$0(String $text, int $newCursorPosition, TextFieldBuffer $this$edit) {
        int newCursorInBuffer;
        TextRange compositionRange = $this$edit.getComposition();
        if (compositionRange != null) {
            imeReplace($this$edit, TextRange.m7573getStartimpl(compositionRange.getPackedValue()), TextRange.m7568getEndimpl(compositionRange.getPackedValue()), $text);
        } else {
            imeReplace($this$edit, TextRange.m7571getMinimpl($this$edit.getSelectionInChars()), TextRange.m7570getMaximpl($this$edit.getSelectionInChars()), $text);
        }
        int newCursor = TextRange.m7571getMinimpl($this$edit.getSelectionInChars());
        if ($newCursorPosition > 0) {
            newCursorInBuffer = (newCursor + $newCursorPosition) - 1;
        } else {
            newCursorInBuffer = (newCursor + $newCursorPosition) - $text.length();
        }
        $this$edit.m1716setSelection5zctL8(TextRangeKt.TextRange(RangesKt.coerceIn(newCursorInBuffer, 0, $this$edit.getLength())));
        return Unit.INSTANCE;
    }

    public static final void setComposingRegion(final ImeEditCommandScope $this$setComposingRegion, final int start, final int end) {
        $this$setComposingRegion.edit(new Function1() { // from class: androidx.compose.foundation.text.input.internal.ImeEditCommand_androidKt$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ImeEditCommand_androidKt.setComposingRegion$lambda$0(start, end, $this$setComposingRegion, (TextFieldBuffer) obj);
            }
        });
    }

    static final Unit setComposingRegion$lambda$0(int $start, int $end, ImeEditCommandScope $this_setComposingRegion, TextFieldBuffer $this$edit) {
        if ($this$edit.hasComposition$foundation()) {
            $this$edit.commitComposition$foundation();
        }
        int minimumValue$iv = $start >= 0 ? $start : 0;
        int $this$fastCoerceAtLeast$iv = $end;
        if ($this$fastCoerceAtLeast$iv < 0) {
            $this$fastCoerceAtLeast$iv = 0;
        }
        long range = $this_setComposingRegion.mo1749mapFromTransformedGEjPoXI(TextRangeKt.TextRange(minimumValue$iv, $this$fastCoerceAtLeast$iv));
        int clampedStart = RangesKt.coerceIn(TextRange.m7571getMinimpl(range), 0, $this$edit.getLength());
        int clampedEnd = RangesKt.coerceIn(TextRange.m7570getMaximpl(range), 0, $this$edit.getLength());
        if (clampedStart != clampedEnd) {
            if (clampedStart < clampedEnd) {
                TextFieldBuffer.setComposition$foundation$default($this$edit, clampedStart, clampedEnd, null, 4, null);
            } else {
                TextFieldBuffer.setComposition$foundation$default($this$edit, clampedEnd, clampedStart, null, 4, null);
            }
        }
        return Unit.INSTANCE;
    }

    public static /* synthetic */ void setComposingText$default(ImeEditCommandScope imeEditCommandScope, String str, int i, List list, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            list = null;
        }
        setComposingText(imeEditCommandScope, str, i, list);
    }

    public static final void setComposingText(ImeEditCommandScope $this$setComposingText, final String text, final int newCursorPosition, final List<AnnotatedString.Range<AnnotatedString.Annotation>> list) {
        $this$setComposingText.edit(new Function1() { // from class: androidx.compose.foundation.text.input.internal.ImeEditCommand_androidKt$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ImeEditCommand_androidKt.setComposingText$lambda$0(text, list, newCursorPosition, (TextFieldBuffer) obj);
            }
        });
    }

    static final Unit setComposingText$lambda$0(String $text, List $annotations, int $newCursorPosition, TextFieldBuffer $this$edit) {
        int newCursorInBuffer;
        TextRange compositionRange = $this$edit.getComposition();
        if (compositionRange != null) {
            imeReplace($this$edit, TextRange.m7573getStartimpl(compositionRange.getPackedValue()), TextRange.m7568getEndimpl(compositionRange.getPackedValue()), $text);
            if ($text.length() > 0) {
                $this$edit.setComposition$foundation(TextRange.m7573getStartimpl(compositionRange.getPackedValue()), TextRange.m7573getStartimpl(compositionRange.getPackedValue()) + $text.length(), $annotations);
            }
        } else {
            int initialSelectionStart = TextRange.m7571getMinimpl($this$edit.getSelectionInChars());
            imeReplace($this$edit, initialSelectionStart, TextRange.m7570getMaximpl($this$edit.getSelectionInChars()), $text);
            if ($text.length() > 0) {
                $this$edit.setComposition$foundation(initialSelectionStart, $text.length() + initialSelectionStart, $annotations);
            }
        }
        int newCursor = TextRange.m7571getMinimpl($this$edit.getSelectionInChars());
        if ($newCursorPosition > 0) {
            newCursorInBuffer = (newCursor + $newCursorPosition) - 1;
        } else {
            newCursorInBuffer = (newCursor + $newCursorPosition) - $text.length();
        }
        $this$edit.m1716setSelection5zctL8(TextRangeKt.TextRange(RangesKt.coerceIn(newCursorInBuffer, 0, $this$edit.getLength())));
        return Unit.INSTANCE;
    }

    public static final void deleteSurroundingText(final ImeEditCommandScope $this$deleteSurroundingText, final int lengthBeforeCursor, final int lengthAfterCursor) {
        $this$deleteSurroundingText.edit(new Function1() { // from class: androidx.compose.foundation.text.input.internal.ImeEditCommand_androidKt$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ImeEditCommand_androidKt.deleteSurroundingText$lambda$0(lengthBeforeCursor, lengthAfterCursor, $this$deleteSurroundingText, (TextFieldBuffer) obj);
            }
        });
    }

    static final Unit deleteSurroundingText$lambda$0(int $lengthBeforeCursor, int $lengthAfterCursor, ImeEditCommandScope $this_deleteSurroundingText, TextFieldBuffer $this$edit) {
        boolean value$iv = $lengthBeforeCursor >= 0 && $lengthAfterCursor >= 0;
        if (!value$iv) {
            InlineClassHelperKt.throwIllegalArgumentException("Expected lengthBeforeCursor and lengthAfterCursor to be non-negative, were " + $lengthBeforeCursor + " and " + $lengthAfterCursor + " respectively.");
        }
        long transformedSelection = $this_deleteSurroundingText.mo1750mapToTransformedGEjPoXI($this$edit.getSelectionInChars());
        int $this$addExactOrElse$iv = TextRange.m7570getMaximpl(transformedSelection);
        int result$iv = $this$addExactOrElse$iv + $lengthAfterCursor;
        if ((($this$addExactOrElse$iv ^ result$iv) & ($lengthAfterCursor ^ result$iv)) < 0) {
            result$iv = $this_deleteSurroundingText.getTransformedLength();
        }
        long untransformedDeleteRangeAfter = $this_deleteSurroundingText.mo1749mapFromTransformedGEjPoXI(TextRangeKt.TextRange(TextRange.m7570getMaximpl(transformedSelection), Math.min(result$iv, $this_deleteSurroundingText.getTransformedLength())));
        imeDelete($this$edit, TextRange.m7571getMinimpl(untransformedDeleteRangeAfter), TextRange.m7570getMaximpl(untransformedDeleteRangeAfter));
        int $this$subtractExactOrElse$iv = TextRange.m7571getMinimpl(transformedSelection);
        int result$iv2 = $this$subtractExactOrElse$iv - $lengthBeforeCursor;
        if ((($this$subtractExactOrElse$iv ^ $lengthBeforeCursor) & ($this$subtractExactOrElse$iv ^ result$iv2)) < 0) {
            result$iv2 = 0;
        }
        long untransformedDeleteRangeBefore = $this_deleteSurroundingText.mo1749mapFromTransformedGEjPoXI(TextRangeKt.TextRange(Math.max(0, result$iv2), TextRange.m7571getMinimpl(transformedSelection)));
        imeDelete($this$edit, TextRange.m7571getMinimpl(untransformedDeleteRangeBefore), TextRange.m7570getMaximpl(untransformedDeleteRangeBefore));
        return Unit.INSTANCE;
    }

    public static final void deleteSurroundingTextInCodePoints(ImeEditCommandScope $this$deleteSurroundingTextInCodePoints, final int lengthBeforeCursor, final int lengthAfterCursor) {
        $this$deleteSurroundingTextInCodePoints.edit(new Function1() { // from class: androidx.compose.foundation.text.input.internal.ImeEditCommand_androidKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ImeEditCommand_androidKt.deleteSurroundingTextInCodePoints$lambda$0(lengthBeforeCursor, lengthAfterCursor, (TextFieldBuffer) obj);
            }
        });
    }

    static final Unit deleteSurroundingTextInCodePoints$lambda$0(int $lengthBeforeCursor, int $lengthAfterCursor, TextFieldBuffer $this$edit) {
        boolean value$iv = $lengthBeforeCursor >= 0 && $lengthAfterCursor >= 0;
        if (!value$iv) {
            InlineClassHelperKt.throwIllegalArgumentException("Expected lengthBeforeCursor and lengthAfterCursor to be non-negative, were " + $lengthBeforeCursor + " and " + $lengthAfterCursor + " respectively.");
        }
        int beforeLenInChars = 0;
        int i = 0;
        while (true) {
            if (i < $lengthBeforeCursor) {
                beforeLenInChars++;
                if (TextRange.m7571getMinimpl($this$edit.getSelectionInChars()) <= beforeLenInChars) {
                    beforeLenInChars = TextRange.m7571getMinimpl($this$edit.getSelectionInChars());
                    break;
                }
                char lead = $this$edit.asCharSequence().charAt((TextRange.m7571getMinimpl($this$edit.getSelectionInChars()) - beforeLenInChars) - 1);
                char trail = $this$edit.asCharSequence().charAt(TextRange.m7571getMinimpl($this$edit.getSelectionInChars()) - beforeLenInChars);
                if (isSurrogatePair(lead, trail)) {
                    beforeLenInChars++;
                }
                i++;
            } else {
                break;
            }
        }
        int afterLenInChars = 0;
        int i2 = 0;
        while (true) {
            if (i2 >= $lengthAfterCursor) {
                break;
            }
            afterLenInChars++;
            if (TextRange.m7570getMaximpl($this$edit.getSelectionInChars()) + afterLenInChars >= $this$edit.getLength()) {
                afterLenInChars = $this$edit.getLength() - TextRange.m7570getMaximpl($this$edit.getSelectionInChars());
                break;
            }
            char lead2 = $this$edit.asCharSequence().charAt((TextRange.m7570getMaximpl($this$edit.getSelectionInChars()) + afterLenInChars) - 1);
            char trail2 = $this$edit.asCharSequence().charAt(TextRange.m7570getMaximpl($this$edit.getSelectionInChars()) + afterLenInChars);
            if (isSurrogatePair(lead2, trail2)) {
                afterLenInChars++;
            }
            i2++;
        }
        imeDelete($this$edit, TextRange.m7570getMaximpl($this$edit.getSelectionInChars()), TextRange.m7570getMaximpl($this$edit.getSelectionInChars()) + afterLenInChars);
        imeDelete($this$edit, TextRange.m7571getMinimpl($this$edit.getSelectionInChars()) - beforeLenInChars, TextRange.m7571getMinimpl($this$edit.getSelectionInChars()));
        return Unit.INSTANCE;
    }

    public static final void finishComposingText(ImeEditCommandScope $this$finishComposingText) {
        $this$finishComposingText.edit(new Function1() { // from class: androidx.compose.foundation.text.input.internal.ImeEditCommand_androidKt$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ImeEditCommand_androidKt.finishComposingText$lambda$0((TextFieldBuffer) obj);
            }
        });
    }

    static final Unit finishComposingText$lambda$0(TextFieldBuffer $this$edit) {
        $this$edit.commitComposition$foundation();
        return Unit.INSTANCE;
    }

    public static final void setSelection(final ImeEditCommandScope $this$setSelection, final int start, final int end) {
        $this$setSelection.edit(new Function1() { // from class: androidx.compose.foundation.text.input.internal.ImeEditCommand_androidKt$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ImeEditCommand_androidKt.setSelection$lambda$0($this$setSelection, start, end, (TextFieldBuffer) obj);
            }
        });
    }

    static final Unit setSelection$lambda$0(ImeEditCommandScope $this_setSelection, int $start, int $end, TextFieldBuffer $this$edit) {
        long transformedSpaceLength = $this_setSelection.mo1750mapToTransformedGEjPoXI(TextRangeKt.TextRange(0, $this$edit.getLength()));
        int minimumValue$iv = TextRange.m7571getMinimpl(transformedSpaceLength);
        int maximumValue$iv = TextRange.m7570getMaximpl(transformedSpaceLength);
        int minimumValue$iv$iv = minimumValue$iv;
        if ($start >= minimumValue$iv$iv) {
            minimumValue$iv$iv = $start;
        }
        int maximumValue$iv$iv = maximumValue$iv;
        if (minimumValue$iv$iv <= maximumValue$iv$iv) {
            maximumValue$iv$iv = minimumValue$iv$iv;
        }
        int minimumValue$iv2 = TextRange.m7571getMinimpl(transformedSpaceLength);
        int maximumValue$iv2 = TextRange.m7570getMaximpl(transformedSpaceLength);
        int $this$fastCoerceAtLeast$iv$iv = $end;
        if ($this$fastCoerceAtLeast$iv$iv < minimumValue$iv2) {
            $this$fastCoerceAtLeast$iv$iv = minimumValue$iv2;
        }
        if ($this$fastCoerceAtLeast$iv$iv > maximumValue$iv2) {
            $this$fastCoerceAtLeast$iv$iv = maximumValue$iv2;
        }
        $this$edit.m1716setSelection5zctL8($this_setSelection.mo1749mapFromTransformedGEjPoXI(TextRangeKt.TextRange(maximumValue$iv$iv, $this$fastCoerceAtLeast$iv$iv)));
        return Unit.INSTANCE;
    }

    private static final boolean isSurrogatePair(char high, char low) {
        return Character.isHighSurrogate(high) && Character.isLowSurrogate(low);
    }

    public static final void imeReplace(TextFieldBuffer $this$imeReplace, int start, int end, CharSequence text) {
        int min = Math.min(start, end);
        int max = Math.max(start, end);
        int i = 0;
        int cMin = min;
        while (cMin < max && i < text.length() && text.charAt(i) == $this$imeReplace.asCharSequence().charAt(cMin)) {
            i++;
            cMin++;
        }
        int j = text.length();
        int cMax = max;
        while (cMax > cMin && j > i && text.charAt(j - 1) == $this$imeReplace.asCharSequence().charAt(cMax - 1)) {
            j--;
            cMax--;
        }
        if (cMin != cMax || i != j) {
            $this$imeReplace.replace(cMin, cMax, text.subSequence(i, j));
        } else {
            $this$imeReplace.commitComposition$foundation();
            $this$imeReplace.clearHighlight$foundation();
        }
        $this$imeReplace.m1716setSelection5zctL8(TextRangeKt.TextRange(text.length() + min));
    }

    public static final void imeDelete(TextFieldBuffer $this$imeDelete, int start, int end) {
        TextRange initialComposition = $this$imeDelete.getComposition();
        int min = Math.min(start, end);
        int max = Math.max(start, end);
        TextFieldBufferKt.delete($this$imeDelete, min, max);
        if (initialComposition != null) {
            initialComposition.getPackedValue();
            long adjustedComposition = TextFieldBufferKt.m1720adjustTextRangevJH6DeI(initialComposition.getPackedValue(), min, max, 0);
            if (TextRange.m7567getCollapsedimpl(adjustedComposition)) {
                $this$imeDelete.commitComposition$foundation();
            } else {
                TextFieldBuffer.setComposition$foundation$default($this$imeDelete, TextRange.m7571getMinimpl(adjustedComposition), TextRange.m7570getMaximpl(adjustedComposition), null, 4, null);
            }
        }
    }
}
