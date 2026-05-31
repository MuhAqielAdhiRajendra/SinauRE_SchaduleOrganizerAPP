package androidx.compose.foundation.text;

import androidx.compose.foundation.OverscrollEffect;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.runtime.Composer;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.layout.LayoutModifier;
import androidx.compose.ui.platform.InspectableValueKt;
import androidx.compose.ui.platform.InspectorInfo;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.input.TextFieldValue;
import androidx.compose.ui.text.input.TransformedText;
import androidx.compose.ui.text.input.VisualTransformation;
import androidx.compose.ui.unit.Density;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;

/* JADX INFO: compiled from: TextFieldScroll.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000V\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a4\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0000\u001a4\u0010\n\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u000e\u0010\u000f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00110\u0010H\u0000\u001a6\u0010\u0012\u001a\u00020\u0013*\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u001b\u001a\u00020\u00072\u0006\u0010\u001c\u001a\u00020\u0016H\u0002¨\u0006\u001d"}, d2 = {"textFieldScrollable", "Landroidx/compose/ui/Modifier;", "scrollerPosition", "Landroidx/compose/foundation/text/TextFieldScrollerPosition;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "enabled", "", "overscrollEffect", "Landroidx/compose/foundation/OverscrollEffect;", "defaultTextFieldScroll", "textFieldValue", "Landroidx/compose/ui/text/input/TextFieldValue;", "visualTransformation", "Landroidx/compose/ui/text/input/VisualTransformation;", "textLayoutResultProvider", "Lkotlin/Function0;", "Landroidx/compose/foundation/text/TextLayoutResultProxy;", "getCursorRectInScroller", "Landroidx/compose/ui/geometry/Rect;", "Landroidx/compose/ui/unit/Density;", "cursorOffset", "", "transformedText", "Landroidx/compose/ui/text/input/TransformedText;", "textLayoutResult", "Landroidx/compose/ui/text/TextLayoutResult;", "rtl", "textFieldWidth", "foundation"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class TextFieldScrollKt {

    /* JADX INFO: compiled from: TextFieldScroll.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Orientation.values().length];
            try {
                iArr[Orientation.Vertical.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[Orientation.Horizontal.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static /* synthetic */ Modifier textFieldScrollable$default(Modifier modifier, TextFieldScrollerPosition textFieldScrollerPosition, MutableInteractionSource mutableInteractionSource, boolean z, OverscrollEffect overscrollEffect, int i, Object obj) {
        if ((i & 2) != 0) {
            mutableInteractionSource = null;
        }
        if ((i & 4) != 0) {
            z = true;
        }
        return textFieldScrollable(modifier, textFieldScrollerPosition, mutableInteractionSource, z, overscrollEffect);
    }

    public static final Modifier textFieldScrollable(Modifier $this$textFieldScrollable, final TextFieldScrollerPosition scrollerPosition, final MutableInteractionSource interactionSource, final boolean enabled, final OverscrollEffect overscrollEffect) {
        return ComposedModifierKt.composed($this$textFieldScrollable, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.foundation.text.TextFieldScrollKt$textFieldScrollable$$inlined$debugInspectorInfo$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(InspectorInfo inspectorInfo) {
                invoke2(inspectorInfo);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(InspectorInfo inspectorInfo) {
                inspectorInfo.setName("textFieldScrollable");
                inspectorInfo.getProperties().set("scrollerPosition", scrollerPosition);
                inspectorInfo.getProperties().set("interactionSource", interactionSource);
                inspectorInfo.getProperties().set("enabled", Boolean.valueOf(enabled));
            }
        } : InspectableValueKt.getNoInspectorInfo(), new Function3() { // from class: androidx.compose.foundation.text.TextFieldScrollKt$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return TextFieldScrollKt.textFieldScrollable$lambda$1(scrollerPosition, enabled, overscrollEffect, interactionSource, (Modifier) obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        });
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x00d6  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static final androidx.compose.ui.Modifier textFieldScrollable$lambda$1(final androidx.compose.foundation.text.TextFieldScrollerPosition r19, boolean r20, androidx.compose.foundation.OverscrollEffect r21, androidx.compose.foundation.interaction.MutableInteractionSource r22, androidx.compose.ui.Modifier r23, androidx.compose.runtime.Composer r24, int r25) {
        /*
            Method dump skipped, instruction units count: 255
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.TextFieldScrollKt.textFieldScrollable$lambda$1(androidx.compose.foundation.text.TextFieldScrollerPosition, boolean, androidx.compose.foundation.OverscrollEffect, androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.ui.Modifier, androidx.compose.runtime.Composer, int):androidx.compose.ui.Modifier");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float textFieldScrollable$lambda$1$0$0(TextFieldScrollerPosition $scrollerPosition, float delta) {
        float consumedDelta;
        float newOffset = $scrollerPosition.getOffset() + delta;
        if (newOffset > $scrollerPosition.getMaximum()) {
            consumedDelta = $scrollerPosition.getMaximum() - $scrollerPosition.getOffset();
        } else {
            consumedDelta = newOffset < 0.0f ? -$scrollerPosition.getOffset() : delta;
        }
        $scrollerPosition.setOffset($scrollerPosition.getOffset() + consumedDelta);
        return consumedDelta;
    }

    public static final Modifier defaultTextFieldScroll(Modifier $this$defaultTextFieldScroll, TextFieldScrollerPosition scrollerPosition, TextFieldValue textFieldValue, VisualTransformation visualTransformation, Function0<TextLayoutResultProxy> function0) {
        LayoutModifier layout;
        Orientation orientation = scrollerPosition.getOrientation();
        int cursorOffset = scrollerPosition.m1667getOffsetToFollow5zctL8(textFieldValue.getSelection());
        scrollerPosition.m1669setPreviousSelection5zctL8(textFieldValue.getSelection());
        TransformedText transformedText = ValidatingOffsetMappingKt.filterWithValidation(visualTransformation, textFieldValue.getText());
        switch (WhenMappings.$EnumSwitchMapping$0[orientation.ordinal()]) {
            case 1:
                layout = new VerticalScrollLayoutModifier(scrollerPosition, cursorOffset, transformedText, function0);
                break;
            case 2:
                layout = new HorizontalScrollLayoutModifier(scrollerPosition, cursorOffset, transformedText, function0);
                break;
            default:
                throw new NoWhenBranchMatchedException();
        }
        return ClipKt.clipToBounds($this$defaultTextFieldScroll).then(layout);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Rect getCursorRectInScroller(Density $this$getCursorRectInScroller, int cursorOffset, TransformedText transformedText, TextLayoutResult textLayoutResult, boolean rtl, int textFieldWidth) {
        Rect zero;
        float cursorLeft;
        float cursorRight;
        if (textLayoutResult == null || (zero = textLayoutResult.getCursorRect(transformedText.getOffsetMapping().originalToTransformed(cursorOffset))) == null) {
            zero = Rect.INSTANCE.getZero();
        }
        Rect cursorRect = zero;
        int thickness = $this$getCursorRectInScroller.mo426roundToPx0680j_4(TextFieldCursor_androidKt.getDefaultCursorThickness());
        if (rtl) {
            cursorLeft = (textFieldWidth - cursorRect.getLeft()) - thickness;
        } else {
            cursorLeft = cursorRect.getLeft();
        }
        if (rtl) {
            cursorRight = textFieldWidth - cursorRect.getLeft();
        } else {
            cursorRight = cursorRect.getLeft() + thickness;
        }
        return Rect.copy$default(cursorRect, cursorLeft, 0.0f, cursorRight, 0.0f, 10, null);
    }
}
