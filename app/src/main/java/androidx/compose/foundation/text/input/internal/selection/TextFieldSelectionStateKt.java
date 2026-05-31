package androidx.compose.foundation.text.input.internal.selection;

import androidx.compose.foundation.contextmenu.ContextMenuScope;
import androidx.compose.foundation.contextmenu.ContextMenuState;
import androidx.compose.foundation.contextmenu.ContextMenuStateKt;
import androidx.compose.foundation.gestures.PressGestureScope;
import androidx.compose.foundation.gestures.TapGestureDetectorKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.internal.PlatformUtils_androidKt;
import androidx.compose.foundation.text.CommonContextMenuAreaKt;
import androidx.compose.foundation.text.MenuItemsAvailability;
import androidx.compose.foundation.text.TextContextMenuItems;
import androidx.compose.foundation.text.TextDragObserver;
import androidx.compose.foundation.text.input.internal.TextLayoutStateKt;
import androidx.compose.foundation.text.selection.MouseSelectionObserver;
import androidx.compose.foundation.text.selection.SelectionGesturesKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CoroutineScopeKt;

/* JADX INFO: compiled from: TextFieldSelectionState.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000t\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a@\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00010\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00010\bH\u0080@¢\u0006\u0002\u0010\n\u001a\"\u0010\u000b\u001a\u00020\u0001*\u00020\u00042\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0080@¢\u0006\u0002\u0010\u0010\u001a\u0013\u0010\u0011\u001a\u00020\u0012*\u00020\u0012H\u0002¢\u0006\u0004\b\u0013\u0010\u0014\u001a5\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\b*\u00020\u00022\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u000e\b\u0004\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00010\bH\u0080\b\u001a\u0017\u0010\u001e\u001a\u00020\u00012\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u001d0\bH\u0082\b\u001aR\u0010 \u001a\u0013\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020\u00010!¢\u0006\u0002\b#*\u00020\u00022\u0006\u0010$\u001a\u00020%2\f\u0010&\u001a\b\u0012\u0004\u0012\u00020(0'2\u001d\u0010)\u001a\u0019\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020+\u0012\u0004\u0012\u00020\u00010*¢\u0006\u0002\b#H\u0000\"\u000e\u0010\u001b\u001a\u00020\u0017X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u001c\u001a\u00020\u001dX\u0082T¢\u0006\u0002\n\u0000¨\u0006,"}, d2 = {"defaultDetectTextFieldTapGestures", "", "Landroidx/compose/foundation/text/input/internal/selection/TextFieldSelectionState;", "pointerInputScope", "Landroidx/compose/ui/input/pointer/PointerInputScope;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "requestFocus", "Lkotlin/Function0;", "showKeyboard", "(Landroidx/compose/foundation/text/input/internal/selection/TextFieldSelectionState;Landroidx/compose/ui/input/pointer/PointerInputScope;Landroidx/compose/foundation/interaction/MutableInteractionSource;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "defaultTextFieldSelectionGestures", "mouseSelectionObserver", "Landroidx/compose/foundation/text/selection/MouseSelectionObserver;", "textDragObserver", "Landroidx/compose/foundation/text/TextDragObserver;", "(Landroidx/compose/ui/input/pointer/PointerInputScope;Landroidx/compose/foundation/text/selection/MouseSelectionObserver;Landroidx/compose/foundation/text/TextDragObserver;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "reverse", "Landroidx/compose/ui/text/TextRange;", "reverse-5zc-tL8", "(J)J", "menuItem", "enabled", "", "desiredState", "Landroidx/compose/foundation/text/input/internal/selection/TextToolbarState;", "operation", "DEBUG", "DEBUG_TAG", "", "logDebug", "text", "contextMenuBuilder", "Lkotlin/Function1;", "Landroidx/compose/foundation/contextmenu/ContextMenuScope;", "Lkotlin/ExtensionFunctionType;", "state", "Landroidx/compose/foundation/contextmenu/ContextMenuState;", "itemsAvailability", "Landroidx/compose/runtime/State;", "Landroidx/compose/foundation/text/MenuItemsAvailability;", "onMenuItemClicked", "Lkotlin/Function2;", "Landroidx/compose/foundation/text/TextContextMenuItems;", "foundation"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class TextFieldSelectionStateKt {
    private static final boolean DEBUG = false;
    private static final String DEBUG_TAG = "TextFieldSelectionState";

    public static final Object defaultDetectTextFieldTapGestures(final TextFieldSelectionState $this$defaultDetectTextFieldTapGestures, PointerInputScope pointerInputScope, MutableInteractionSource interactionSource, final Function0<Unit> function0, final Function0<Unit> function02, Continuation<? super Unit> continuation) {
        Object objDetectTapAndPress = TapGestureDetectorKt.detectTapAndPress(pointerInputScope, new AnonymousClass2(interactionSource, $this$defaultDetectTextFieldTapGestures, null), new Function1() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionStateKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TextFieldSelectionStateKt.defaultDetectTextFieldTapGestures$lambda$0(function0, $this$defaultDetectTextFieldTapGestures, function02, (Offset) obj);
            }
        }, continuation);
        return objDetectTapAndPress == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objDetectTapAndPress : Unit.INSTANCE;
    }

    static final Unit defaultDetectTextFieldTapGestures$lambda$0(Function0 $requestFocus, TextFieldSelectionState $this_defaultDetectTextFieldTapGestures, Function0 $showKeyboard, Offset offset) {
        $requestFocus.invoke();
        if ($this_defaultDetectTextFieldTapGestures.getEnabled() && $this_defaultDetectTextFieldTapGestures.getIsFocused()) {
            if (!$this_defaultDetectTextFieldTapGestures.getReadOnly()) {
                $showKeyboard.invoke();
                if ($this_defaultDetectTextFieldTapGestures.getTextFieldState().getVisualText().length() > 0) {
                    $this_defaultDetectTextFieldTapGestures.setShowCursorHandle(true);
                }
            }
            $this_defaultDetectTextFieldTapGestures.updateTextToolbarState(TextToolbarState.None);
            long coercedOffset = $this_defaultDetectTextFieldTapGestures.getTextLayoutState().m1877coercedInVisibleBoundsOfInputTextMKHz9U$foundation(offset.m5078unboximpl());
            $this_defaultDetectTextFieldTapGestures.m1940placeCursorAtNearestOffsetk4lQ0M(TextLayoutStateKt.m1884fromDecorationToTextLayoutUv8p0NA($this_defaultDetectTextFieldTapGestures.getTextLayoutState(), coercedOffset));
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionStateKt$defaultDetectTextFieldTapGestures$2 */
    /* JADX INFO: compiled from: TextFieldSelectionState.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/foundation/gestures/PressGestureScope;", TypedValues.CycleType.S_WAVE_OFFSET, "Landroidx/compose/ui/geometry/Offset;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionStateKt$defaultDetectTextFieldTapGestures$2", f = "TextFieldSelectionState.kt", i = {}, l = {1821}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class AnonymousClass2 extends SuspendLambda implements Function3<PressGestureScope, Offset, Continuation<? super Unit>, Object> {
        final /* synthetic */ MutableInteractionSource $interactionSource;
        final /* synthetic */ TextFieldSelectionState $this_defaultDetectTextFieldTapGestures;
        /* synthetic */ long J$0;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(MutableInteractionSource mutableInteractionSource, TextFieldSelectionState textFieldSelectionState, Continuation<? super AnonymousClass2> continuation) {
            super(3, continuation);
            this.$interactionSource = mutableInteractionSource;
            this.$this_defaultDetectTextFieldTapGestures = textFieldSelectionState;
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Object invoke(PressGestureScope pressGestureScope, Offset offset, Continuation<? super Unit> continuation) {
            return m1950invoked4ec7I(pressGestureScope, offset.m5078unboximpl(), continuation);
        }

        /* JADX INFO: renamed from: invoke-d-4ec7I */
        public final Object m1950invoked4ec7I(PressGestureScope pressGestureScope, long j, Continuation<? super Unit> continuation) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$interactionSource, this.$this_defaultDetectTextFieldTapGestures, continuation);
            anonymousClass2.L$0 = pressGestureScope;
            anonymousClass2.J$0 = j;
            return anonymousClass2.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    PressGestureScope $this$detectTapAndPress = (PressGestureScope) this.L$0;
                    long offset = this.J$0;
                    MutableInteractionSource interactionSource = this.$interactionSource;
                    if (interactionSource != null) {
                        TextFieldSelectionStateKt$defaultDetectTextFieldTapGestures$2$1$1 textFieldSelectionStateKt$defaultDetectTextFieldTapGestures$2$1$1 = new TextFieldSelectionStateKt$defaultDetectTextFieldTapGestures$2$1$1($this$detectTapAndPress, this.$this_defaultDetectTextFieldTapGestures, offset, interactionSource, null);
                        this.label = 1;
                        if (CoroutineScopeKt.coroutineScope(textFieldSelectionStateKt$defaultDetectTextFieldTapGestures$2$1$1, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Unit.INSTANCE;
        }
    }

    public static final Object defaultTextFieldSelectionGestures(PointerInputScope $this$defaultTextFieldSelectionGestures, MouseSelectionObserver mouseSelectionObserver, TextDragObserver textDragObserver, Continuation<? super Unit> continuation) {
        Object objAwaitSelectionGestures = SelectionGesturesKt.awaitSelectionGestures($this$defaultTextFieldSelectionGestures, mouseSelectionObserver, textDragObserver, continuation);
        return objAwaitSelectionGestures == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAwaitSelectionGestures : Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: reverse-5zc-tL8 */
    public static final long m1949reverse5zctL8(long $this$reverse_u2d5zc_u2dtL8) {
        return TextRangeKt.TextRange(TextRange.m7568getEndimpl($this$reverse_u2d5zc_u2dtL8), TextRange.m7573getStartimpl($this$reverse_u2d5zc_u2dtL8));
    }

    public static final Function0<Unit> menuItem(TextFieldSelectionState $this$menuItem, boolean enabled, TextToolbarState desiredState, Function0<Unit> function0) {
        if (enabled) {
            return new Function0<Unit>() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionStateKt.menuItem.1
                final /* synthetic */ TextToolbarState $desiredState;
                final /* synthetic */ Function0<Unit> $operation;
                final /* synthetic */ TextFieldSelectionState $this_menuItem;

                public AnonymousClass1(Function0<Unit> function02, TextFieldSelectionState $this$menuItem2, TextToolbarState desiredState2) {
                    function0 = function02;
                    textFieldSelectionState = $this$menuItem2;
                    textToolbarState = desiredState2;
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke */
                public final void invoke2() {
                    function0.invoke();
                    textFieldSelectionState.updateTextToolbarState(textToolbarState);
                }
            };
        }
        return null;
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionStateKt$menuItem$1 */
    /* JADX INFO: compiled from: TextFieldSelectionState.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 176)
    public static final class AnonymousClass1 implements Function0<Unit> {
        final /* synthetic */ TextToolbarState $desiredState;
        final /* synthetic */ Function0<Unit> $operation;
        final /* synthetic */ TextFieldSelectionState $this_menuItem;

        public AnonymousClass1(Function0<Unit> function02, TextFieldSelectionState $this$menuItem2, TextToolbarState desiredState2) {
            function0 = function02;
            textFieldSelectionState = $this$menuItem2;
            textToolbarState = desiredState2;
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Unit invoke() {
            invoke2();
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke */
        public final void invoke2() {
            function0.invoke();
            textFieldSelectionState.updateTextToolbarState(textToolbarState);
        }
    }

    private static final void logDebug(Function0<String> function0) {
    }

    public static final Function1<ContextMenuScope, Unit> contextMenuBuilder(final TextFieldSelectionState $this$contextMenuBuilder, final ContextMenuState state, final State<MenuItemsAvailability> state2, final Function2<? super TextFieldSelectionState, ? super TextContextMenuItems, Unit> function2) {
        return new Function1() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionStateKt$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TextFieldSelectionStateKt.contextMenuBuilder$lambda$0(state2, state, function2, $this$contextMenuBuilder, (ContextMenuScope) obj);
            }
        };
    }

    private static final void contextMenuBuilder$lambda$0$textFieldItem(ContextMenuScope $this, final ContextMenuState $state, final Function2<? super TextFieldSelectionState, ? super TextContextMenuItems, Unit> function2, final TextFieldSelectionState $this_contextMenuBuilder, final TextContextMenuItems label, boolean enabled) {
        if (enabled) {
            $this.item(new CommonContextMenuAreaKt.AnonymousClass1(label), (14 & 2) != 0 ? Modifier.INSTANCE : null, (14 & 4) != 0, (14 & 8) != 0 ? null : null, new Function0<Unit>() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionStateKt$contextMenuBuilder$lambda$0$textFieldItem$$inlined$TextItem$1
                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    function2.invoke($this_contextMenuBuilder, label);
                    ContextMenuStateKt.close($state);
                }
            });
        }
    }

    static final Unit contextMenuBuilder$lambda$0(State $itemsAvailability, ContextMenuState $state, Function2 $onMenuItemClicked, TextFieldSelectionState $this_contextMenuBuilder, ContextMenuScope contextMenuScope) {
        int availability = ((MenuItemsAvailability) $itemsAvailability.getValue()).m1625unboximpl();
        contextMenuBuilder$lambda$0$textFieldItem(contextMenuScope, $state, $onMenuItemClicked, $this_contextMenuBuilder, TextContextMenuItems.Cut, MenuItemsAvailability.m1620getCanCutimpl(availability));
        contextMenuBuilder$lambda$0$textFieldItem(contextMenuScope, $state, $onMenuItemClicked, $this_contextMenuBuilder, TextContextMenuItems.Copy, MenuItemsAvailability.m1619getCanCopyimpl(availability));
        contextMenuBuilder$lambda$0$textFieldItem(contextMenuScope, $state, $onMenuItemClicked, $this_contextMenuBuilder, TextContextMenuItems.Paste, MenuItemsAvailability.m1621getCanPasteimpl(availability));
        contextMenuBuilder$lambda$0$textFieldItem(contextMenuScope, $state, $onMenuItemClicked, $this_contextMenuBuilder, TextContextMenuItems.SelectAll, MenuItemsAvailability.m1622getCanSelectAllimpl(availability));
        if (PlatformUtils_androidKt.isAutofillAvailable()) {
            contextMenuBuilder$lambda$0$textFieldItem(contextMenuScope, $state, $onMenuItemClicked, $this_contextMenuBuilder, TextContextMenuItems.Autofill, MenuItemsAvailability.m1618getCanAutofillimpl(availability));
        }
        return Unit.INSTANCE;
    }
}
