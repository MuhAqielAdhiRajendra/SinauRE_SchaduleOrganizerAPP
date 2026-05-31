package androidx.compose.foundation.text.input.internal.selection;

import android.content.Context;
import android.os.Build;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.text.ContextMenu_androidKt;
import androidx.compose.foundation.text.TextContextMenuItems;
import androidx.compose.foundation.text.TextDragObserver;
import androidx.compose.foundation.text.contextmenu.builder.TextContextMenuBuilderScope;
import androidx.compose.foundation.text.contextmenu.data.TextContextMenuSession;
import androidx.compose.foundation.text.contextmenu.modifier.TextContextMenuModifier_androidKt;
import androidx.compose.foundation.text.selection.MouseSelectionObserver;
import androidx.compose.foundation.text.selection.PlatformSelectionBehaviors_androidKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.text.TextRange;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;

/* JADX INFO: compiled from: TextFieldSelectionState.android.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000<\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0000\u001a@\u0010\u0006\u001a\u00020\u0007*\u00020\u00032\u0006\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00070\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00070\rH\u0080@¢\u0006\u0002\u0010\u000f\u001a*\u0010\u0010\u001a\u00020\u0007*\u00020\u00032\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0080@¢\u0006\u0002\u0010\u0015¨\u0006\u0016"}, d2 = {"addBasicTextFieldTextContextMenuComponents", "Landroidx/compose/ui/Modifier;", "state", "Landroidx/compose/foundation/text/input/internal/selection/TextFieldSelectionState;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "detectTextFieldTapGestures", "", "pointerInputScope", "Landroidx/compose/ui/input/pointer/PointerInputScope;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "requestFocus", "Lkotlin/Function0;", "showKeyboard", "(Landroidx/compose/foundation/text/input/internal/selection/TextFieldSelectionState;Landroidx/compose/ui/input/pointer/PointerInputScope;Landroidx/compose/foundation/interaction/MutableInteractionSource;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "textFieldSelectionGestures", "mouseSelectionObserver", "Landroidx/compose/foundation/text/selection/MouseSelectionObserver;", "textDragObserver", "Landroidx/compose/foundation/text/TextDragObserver;", "(Landroidx/compose/foundation/text/input/internal/selection/TextFieldSelectionState;Landroidx/compose/ui/input/pointer/PointerInputScope;Landroidx/compose/foundation/text/selection/MouseSelectionObserver;Landroidx/compose/foundation/text/TextDragObserver;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "foundation"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class TextFieldSelectionState_androidKt {
    public static final Modifier addBasicTextFieldTextContextMenuComponents(Modifier $this$addBasicTextFieldTextContextMenuComponents, final TextFieldSelectionState state, final CoroutineScope coroutineScope) {
        return TextContextMenuModifier_androidKt.addTextContextMenuComponentsWithContext($this$addBasicTextFieldTextContextMenuComponents, new Function2() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState_androidKt$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return TextFieldSelectionState_androidKt.addBasicTextFieldTextContextMenuComponents$lambda$0(state, coroutineScope, (TextContextMenuBuilderScope) obj, (Context) obj2);
            }
        });
    }

    static /* synthetic */ void addBasicTextFieldTextContextMenuComponents$lambda$0$textFieldItem$default(TextContextMenuBuilderScope textContextMenuBuilderScope, Context context, TextFieldSelectionState textFieldSelectionState, TextContextMenuItems textContextMenuItems, boolean z, TextToolbarState textToolbarState, Function0 function0, Function0 function02, int i, Object obj) {
        TextToolbarState textToolbarState2;
        Function0 function03;
        if ((i & 16) == 0) {
            textToolbarState2 = textToolbarState;
        } else {
            textToolbarState2 = TextToolbarState.None;
        }
        if ((i & 32) == 0) {
            function03 = function0;
        } else {
            function03 = null;
        }
        addBasicTextFieldTextContextMenuComponents$lambda$0$textFieldItem(textContextMenuBuilderScope, context, textFieldSelectionState, textContextMenuItems, z, textToolbarState2, function03, function02);
    }

    private static final void addBasicTextFieldTextContextMenuComponents$lambda$0$textFieldItem(TextContextMenuBuilderScope $this$addBasicTextFieldTextContextMenuComponents_u24lambda_u240_u24textFieldItem, Context $context, final TextFieldSelectionState $state, TextContextMenuItems item, boolean enabled, final TextToolbarState desiredState, final Function0<Boolean> function0, final Function0<Unit> function02) {
        ContextMenu_androidKt.textItem($this$addBasicTextFieldTextContextMenuComponents_u24lambda_u240_u24textFieldItem, $context.getResources(), item, enabled, new Function1() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState_androidKt$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TextFieldSelectionState_androidKt.addBasicTextFieldTextContextMenuComponents$lambda$0$textFieldItem$0(function02, function0, $state, desiredState, (TextContextMenuSession) obj);
            }
        });
    }

    public static final Unit addBasicTextFieldTextContextMenuComponents$lambda$0$textFieldItem$0(Function0 $onClick, Function0 $closePredicate, TextFieldSelectionState $state, TextToolbarState $desiredState, TextContextMenuSession $this$textItem) {
        $onClick.invoke();
        if ($closePredicate != null ? ((Boolean) $closePredicate.invoke()).booleanValue() : true) {
            $this$textItem.close();
        }
        $state.updateTextToolbarState($desiredState);
        return Unit.INSTANCE;
    }

    private static final void addBasicTextFieldTextContextMenuComponents$lambda$0$textFieldSuspendItem(TextContextMenuBuilderScope $this$addBasicTextFieldTextContextMenuComponents_u24lambda_u240_u24textFieldSuspendItem, final CoroutineScope $coroutineScope, Context $context, TextFieldSelectionState $state, TextContextMenuItems item, boolean enabled, final Function1<? super Continuation<? super Unit>, ? extends Object> function1) {
        addBasicTextFieldTextContextMenuComponents$lambda$0$textFieldItem$default($this$addBasicTextFieldTextContextMenuComponents_u24lambda_u240_u24textFieldSuspendItem, $context, $state, item, enabled, null, null, new Function0() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState_androidKt$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return TextFieldSelectionState_androidKt.addBasicTextFieldTextContextMenuComponents$lambda$0$textFieldSuspendItem$1($coroutineScope, function1);
            }
        }, 48, null);
    }

    public static final Unit addBasicTextFieldTextContextMenuComponents$lambda$0$textFieldSuspendItem$1(CoroutineScope $coroutineScope, Function1 $onClick) {
        BuildersKt__Builders_commonKt.launch$default($coroutineScope, null, CoroutineStart.UNDISPATCHED, new TextFieldSelectionState_androidKt$addBasicTextFieldTextContextMenuComponents$1$textFieldSuspendItem$1$1($onClick, null), 1, null);
        return Unit.INSTANCE;
    }

    static final Unit addBasicTextFieldTextContextMenuComponents$lambda$0(final TextFieldSelectionState $state, final CoroutineScope $coroutineScope, TextContextMenuBuilderScope $this$addTextContextMenuComponentsWithContext, final Context context) {
        PlatformSelectionBehaviors_androidKt.m2043addPlatformTextContextMenuItems71BSaZU($this$addTextContextMenuComponentsWithContext, context, $state.getEditable$foundation(), $state.getTextFieldState().getVisualText().getText(), TextRange.m7561boximpl($state.getTextFieldState().getVisualText().getSelection()), $state.getPlatformSelectionBehaviors(), new Function1() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState_androidKt$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TextFieldSelectionState_androidKt.addBasicTextFieldTextContextMenuComponents$lambda$0$2($state, $coroutineScope, context, (TextContextMenuBuilderScope) obj);
            }
        });
        return Unit.INSTANCE;
    }

    public static final Unit addBasicTextFieldTextContextMenuComponents$lambda$0$2(final TextFieldSelectionState $state, CoroutineScope $coroutineScope, Context $context, TextContextMenuBuilderScope $this$addPlatformTextContextMenuItems) {
        $this$addPlatformTextContextMenuItems.separator();
        addBasicTextFieldTextContextMenuComponents$lambda$0$textFieldSuspendItem($this$addPlatformTextContextMenuItems, $coroutineScope, $context, $state, TextContextMenuItems.Cut, $state.canShowCutMenuItem(), new TextFieldSelectionState_androidKt$addBasicTextFieldTextContextMenuComponents$1$1$1$1($state, null));
        addBasicTextFieldTextContextMenuComponents$lambda$0$textFieldSuspendItem($this$addPlatformTextContextMenuItems, $coroutineScope, $context, $state, TextContextMenuItems.Copy, $state.canShowCopyMenuItem(), new TextFieldSelectionState_androidKt$addBasicTextFieldTextContextMenuComponents$1$1$1$2($state, null));
        addBasicTextFieldTextContextMenuComponents$lambda$0$textFieldSuspendItem($this$addPlatformTextContextMenuItems, $coroutineScope, $context, $state, TextContextMenuItems.Paste, $state.canShowPasteMenuItem(), new TextFieldSelectionState_androidKt$addBasicTextFieldTextContextMenuComponents$1$1$1$3($state, null));
        addBasicTextFieldTextContextMenuComponents$lambda$0$textFieldItem($this$addPlatformTextContextMenuItems, $context, $state, TextContextMenuItems.SelectAll, $state.canShowSelectAllMenuItem(), TextToolbarState.Selection, new Function0() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState_androidKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(TextFieldSelectionState_androidKt.addBasicTextFieldTextContextMenuComponents$lambda$0$2$0$0($state));
            }
        }, new Function0() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState_androidKt$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return TextFieldSelectionState_androidKt.addBasicTextFieldTextContextMenuComponents$lambda$0$2$0$1($state);
            }
        });
        if (Build.VERSION.SDK_INT >= 26) {
            addBasicTextFieldTextContextMenuComponents$lambda$0$textFieldItem$default($this$addPlatformTextContextMenuItems, $context, $state, TextContextMenuItems.Autofill, $state.canShowAutofillMenuItem(), null, null, new Function0() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState_androidKt$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return TextFieldSelectionState_androidKt.addBasicTextFieldTextContextMenuComponents$lambda$0$2$0$2($state);
                }
            }, 48, null);
        }
        $this$addPlatformTextContextMenuItems.separator();
        return Unit.INSTANCE;
    }

    public static final boolean addBasicTextFieldTextContextMenuComponents$lambda$0$2$0$0(TextFieldSelectionState $this_with) {
        return !$this_with.getTextToolbarShown();
    }

    public static final Unit addBasicTextFieldTextContextMenuComponents$lambda$0$2$0$1(TextFieldSelectionState $this_with) {
        $this_with.selectAll();
        return Unit.INSTANCE;
    }

    public static final Unit addBasicTextFieldTextContextMenuComponents$lambda$0$2$0$2(TextFieldSelectionState $this_with) {
        $this_with.autofill();
        return Unit.INSTANCE;
    }

    public static final Object detectTextFieldTapGestures(TextFieldSelectionState $this$detectTextFieldTapGestures, PointerInputScope pointerInputScope, MutableInteractionSource interactionSource, Function0<Unit> function0, Function0<Unit> function02, Continuation<? super Unit> continuation) {
        Object objDefaultDetectTextFieldTapGestures = TextFieldSelectionStateKt.defaultDetectTextFieldTapGestures($this$detectTextFieldTapGestures, pointerInputScope, interactionSource, function0, function02, continuation);
        return objDefaultDetectTextFieldTapGestures == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objDefaultDetectTextFieldTapGestures : Unit.INSTANCE;
    }

    public static final Object textFieldSelectionGestures(TextFieldSelectionState $this$textFieldSelectionGestures, PointerInputScope pointerInputScope, MouseSelectionObserver mouseSelectionObserver, TextDragObserver textDragObserver, Continuation<? super Unit> continuation) {
        Object objDefaultTextFieldSelectionGestures = TextFieldSelectionStateKt.defaultTextFieldSelectionGestures(pointerInputScope, mouseSelectionObserver, textDragObserver, continuation);
        return objDefaultTextFieldSelectionGestures == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objDefaultTextFieldSelectionGestures : Unit.INSTANCE;
    }
}
