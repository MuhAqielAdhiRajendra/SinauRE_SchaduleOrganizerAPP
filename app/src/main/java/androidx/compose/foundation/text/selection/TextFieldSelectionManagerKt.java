package androidx.compose.foundation.text.selection;

import androidx.compose.foundation.contextmenu.ContextMenuScope;
import androidx.compose.foundation.contextmenu.ContextMenuState;
import androidx.compose.foundation.internal.PlatformUtils_androidKt;
import androidx.compose.foundation.text.CommonContextMenuAreaKt;
import androidx.compose.foundation.text.Handle;
import androidx.compose.foundation.text.LegacyTextFieldState;
import androidx.compose.foundation.text.LongPressTextDragObserverKt;
import androidx.compose.foundation.text.MenuItemsAvailability;
import androidx.compose.foundation.text.TextContextMenuItems;
import androidx.compose.foundation.text.TextDragObserver;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.State;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.input.pointer.PointerInputEventHandler;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.style.ResolvedTextDirection;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: TextFieldSelectionManager.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000H\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a%\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0001¢\u0006\u0002\u0010\b\u001a\u0014\u0010\t\u001a\u00020\u0003*\u00020\u00072\u0006\u0010\u0002\u001a\u00020\u0003H\u0000\u001a\u001f\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\rH\u0000¢\u0006\u0004\b\u000e\u0010\u000f\u001a3\u0010\u0010\u001a\u0013\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00010\u0011¢\u0006\u0002\b\u0013*\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u00152\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017H\u0000¨\u0006\u0019"}, d2 = {"TextFieldSelectionHandle", "", "isStartHandle", "", "direction", "Landroidx/compose/ui/text/style/ResolvedTextDirection;", "manager", "Landroidx/compose/foundation/text/selection/TextFieldSelectionManager;", "(ZLandroidx/compose/ui/text/style/ResolvedTextDirection;Landroidx/compose/foundation/text/selection/TextFieldSelectionManager;Landroidx/compose/runtime/Composer;I)V", "isSelectionHandleInVisibleBoundDefault", "calculateSelectionMagnifierCenterAndroid", "Landroidx/compose/ui/geometry/Offset;", "magnifierSize", "Landroidx/compose/ui/unit/IntSize;", "calculateSelectionMagnifierCenterAndroid-O0kMr_c", "(Landroidx/compose/foundation/text/selection/TextFieldSelectionManager;J)J", "contextMenuBuilder", "Lkotlin/Function1;", "Landroidx/compose/foundation/contextmenu/ContextMenuScope;", "Lkotlin/ExtensionFunctionType;", "contextMenuState", "Landroidx/compose/foundation/contextmenu/ContextMenuState;", "itemsAvailability", "Landroidx/compose/runtime/State;", "Landroidx/compose/foundation/text/MenuItemsAvailability;", "foundation"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class TextFieldSelectionManagerKt {

    /* JADX INFO: compiled from: TextFieldSelectionManager.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Handle.values().length];
            try {
                iArr[Handle.Cursor.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[Handle.SelectionStart.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[Handle.SelectionEnd.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    static final Unit TextFieldSelectionHandle$lambda$3(boolean z, ResolvedTextDirection resolvedTextDirection, TextFieldSelectionManager textFieldSelectionManager, int i, Composer composer, int i2) {
        TextFieldSelectionHandle(z, resolvedTextDirection, textFieldSelectionManager, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static final void TextFieldSelectionHandle(final boolean isStartHandle, ResolvedTextDirection direction, final TextFieldSelectionManager manager, Composer $composer, final int $changed) {
        final ResolvedTextDirection resolvedTextDirection;
        OffsetProvider offsetProvider;
        Composer $composer2 = $composer.startRestartGroup(-1344558920);
        ComposerKt.sourceInformation($composer2, "C(TextFieldSelectionHandle)N(isStartHandle,direction,manager)1366@58081L78,1369@58207L44,1375@58510L51,1368@58165L403:TextFieldSelectionManager.kt#eksfi3");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(isStartHandle) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(direction.ordinal()) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer2.changedInstance(manager) ? 256 : 128;
        }
        int $dirty2 = $dirty;
        if (!$composer2.shouldExecute(($dirty2 & 147) != 146, $dirty2 & 1)) {
            resolvedTextDirection = direction;
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1344558920, $dirty2, -1, "androidx.compose.foundation.text.selection.TextFieldSelectionHandle (TextFieldSelectionManager.kt:1365)");
            }
            ComposerKt.sourceInformationMarkerStart($composer2, 664039142, "CC(remember):TextFieldSelectionManager.kt#9igjgp");
            boolean invalid$iv = (($dirty2 & 14) == 4) | $composer2.changed(manager);
            Object it$iv = $composer2.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = manager.handleDragObserver$foundation(isStartHandle);
                $composer2.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            final TextDragObserver observer = (TextDragObserver) it$iv;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerStart($composer2, 664043140, "CC(remember):TextFieldSelectionManager.kt#9igjgp");
            boolean invalid$iv2 = $composer2.changedInstance(manager) | (($dirty2 & 14) == 4);
            Object it$iv2 = $composer2.rememberedValue();
            if (invalid$iv2 || it$iv2 == Composer.INSTANCE.getEmpty()) {
                Object value$iv2 = (OffsetProvider) new OffsetProvider() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManagerKt$TextFieldSelectionHandle$1$1
                    @Override // androidx.compose.foundation.text.selection.OffsetProvider
                    /* JADX INFO: renamed from: provide-F1C5BW0 */
                    public final long mo1487provideF1C5BW0() {
                        return manager.m2105getHandlePositiontuRUvjQ$foundation(isStartHandle);
                    }
                };
                $composer2.updateRememberedValue(value$iv2);
                it$iv2 = value$iv2;
            }
            OffsetProvider offsetProvider2 = (OffsetProvider) it$iv2;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            boolean zM7572getReversedimpl = TextRange.m7572getReversedimpl(manager.getValue$foundation().getSelection());
            float handleLineHeight$foundation = manager.getHandleLineHeight$foundation(isStartHandle);
            Modifier.Companion companion = Modifier.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer2, 664052843, "CC(remember):TextFieldSelectionManager.kt#9igjgp");
            boolean invalid$iv3 = $composer2.changedInstance(observer);
            Object it$iv3 = $composer2.rememberedValue();
            if (invalid$iv3 || it$iv3 == Composer.INSTANCE.getEmpty()) {
                offsetProvider = offsetProvider2;
                Object value$iv3 = (PointerInputEventHandler) new PointerInputEventHandler() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManagerKt$TextFieldSelectionHandle$2$1
                    @Override // androidx.compose.ui.input.pointer.PointerInputEventHandler
                    public final Object invoke(PointerInputScope $this$pointerInput, Continuation<? super Unit> continuation) {
                        Object objDetectDownAndDragGesturesWithObserver = LongPressTextDragObserverKt.detectDownAndDragGesturesWithObserver($this$pointerInput, observer, continuation);
                        return objDetectDownAndDragGesturesWithObserver == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objDetectDownAndDragGesturesWithObserver : Unit.INSTANCE;
                    }
                };
                $composer2.updateRememberedValue(value$iv3);
                it$iv3 = value$iv3;
            } else {
                offsetProvider = offsetProvider2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            resolvedTextDirection = direction;
            AndroidSelectionHandles_androidKt.m2022SelectionHandlewLIcFTc(offsetProvider, isStartHandle, resolvedTextDirection, zM7572getReversedimpl, 0L, handleLineHeight$foundation, SuspendingPointerInputFilterKt.pointerInput(companion, observer, (PointerInputEventHandler) it$iv3), $composer2, (($dirty2 << 3) & 112) | (($dirty2 << 3) & 896), 16);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManagerKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TextFieldSelectionManagerKt.TextFieldSelectionHandle$lambda$3(isStartHandle, resolvedTextDirection, manager, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final boolean isSelectionHandleInVisibleBoundDefault(TextFieldSelectionManager $this$isSelectionHandleInVisibleBoundDefault, boolean isStartHandle) {
        LayoutCoordinates layoutCoordinates;
        Rect rectVisibleBounds;
        LegacyTextFieldState state = $this$isSelectionHandleInVisibleBoundDefault.getState();
        if (state == null || (layoutCoordinates = state.getLayoutCoordinates()) == null || (rectVisibleBounds = SelectionManagerKt.visibleBounds(layoutCoordinates)) == null) {
            return false;
        }
        return SelectionManagerKt.m2078containsInclusiveUv8p0NA(rectVisibleBounds, $this$isSelectionHandleInVisibleBoundDefault.m2105getHandlePositiontuRUvjQ$foundation(isStartHandle));
    }

    /* JADX WARN: Removed duplicated region for block: B:67:0x0020  */
    /* JADX INFO: renamed from: calculateSelectionMagnifierCenterAndroid-O0kMr_c */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final long m2114calculateSelectionMagnifierCenterAndroidO0kMr_c(androidx.compose.foundation.text.selection.TextFieldSelectionManager r32, long r33) {
        /*
            Method dump skipped, instruction units count: 354
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.selection.TextFieldSelectionManagerKt.m2114calculateSelectionMagnifierCenterAndroidO0kMr_c(androidx.compose.foundation.text.selection.TextFieldSelectionManager, long):long");
    }

    public static final Function1<ContextMenuScope, Unit> contextMenuBuilder(final TextFieldSelectionManager $this$contextMenuBuilder, final ContextMenuState contextMenuState, final State<MenuItemsAvailability> state) {
        return new Function1() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManagerKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TextFieldSelectionManagerKt.contextMenuBuilder$lambda$0(state, $this$contextMenuBuilder, contextMenuState, (ContextMenuScope) obj);
            }
        };
    }

    private static final void contextMenuBuilder$lambda$0$textFieldItem(ContextMenuScope $this, ContextMenuState $contextMenuState, TextContextMenuItems label, boolean enabled, Function0<Unit> function0) {
        if (enabled) {
            $this.item(new CommonContextMenuAreaKt.AnonymousClass1(label), (14 & 2) != 0 ? Modifier.INSTANCE : null, (14 & 4) != 0, (14 & 8) != 0 ? null : null, new CommonContextMenuAreaKt.AnonymousClass2(function0, $contextMenuState));
        }
    }

    static final Unit contextMenuBuilder$lambda$0(State $itemsAvailability, final TextFieldSelectionManager $this_contextMenuBuilder, ContextMenuState $contextMenuState, ContextMenuScope contextMenuScope) {
        int availability = ((MenuItemsAvailability) $itemsAvailability.getValue()).m1625unboximpl();
        contextMenuBuilder$lambda$0$textFieldItem(contextMenuScope, $contextMenuState, TextContextMenuItems.Cut, MenuItemsAvailability.m1620getCanCutimpl(availability), new Function0() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManagerKt$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return TextFieldSelectionManagerKt.contextMenuBuilder$lambda$0$0($this_contextMenuBuilder);
            }
        });
        contextMenuBuilder$lambda$0$textFieldItem(contextMenuScope, $contextMenuState, TextContextMenuItems.Copy, MenuItemsAvailability.m1619getCanCopyimpl(availability), new Function0() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManagerKt$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return TextFieldSelectionManagerKt.contextMenuBuilder$lambda$0$1($this_contextMenuBuilder);
            }
        });
        contextMenuBuilder$lambda$0$textFieldItem(contextMenuScope, $contextMenuState, TextContextMenuItems.Paste, MenuItemsAvailability.m1621getCanPasteimpl(availability), new Function0() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManagerKt$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return TextFieldSelectionManagerKt.contextMenuBuilder$lambda$0$2($this_contextMenuBuilder);
            }
        });
        contextMenuBuilder$lambda$0$textFieldItem(contextMenuScope, $contextMenuState, TextContextMenuItems.SelectAll, MenuItemsAvailability.m1622getCanSelectAllimpl(availability), new Function0() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManagerKt$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return TextFieldSelectionManagerKt.contextMenuBuilder$lambda$0$3($this_contextMenuBuilder);
            }
        });
        if (PlatformUtils_androidKt.isAutofillAvailable()) {
            contextMenuBuilder$lambda$0$textFieldItem(contextMenuScope, $contextMenuState, TextContextMenuItems.Autofill, MenuItemsAvailability.m1618getCanAutofillimpl(availability), new Function0() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManagerKt$$ExternalSyntheticLambda6
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return TextFieldSelectionManagerKt.contextMenuBuilder$lambda$0$4($this_contextMenuBuilder);
                }
            });
        }
        return Unit.INSTANCE;
    }

    public static final Unit contextMenuBuilder$lambda$0$0(TextFieldSelectionManager $this_contextMenuBuilder) {
        $this_contextMenuBuilder.cut$foundation();
        return Unit.INSTANCE;
    }

    public static final Unit contextMenuBuilder$lambda$0$1(TextFieldSelectionManager $this_contextMenuBuilder) {
        $this_contextMenuBuilder.copy$foundation(false);
        return Unit.INSTANCE;
    }

    public static final Unit contextMenuBuilder$lambda$0$2(TextFieldSelectionManager $this_contextMenuBuilder) {
        $this_contextMenuBuilder.paste$foundation();
        return Unit.INSTANCE;
    }

    public static final Unit contextMenuBuilder$lambda$0$3(TextFieldSelectionManager $this_contextMenuBuilder) {
        $this_contextMenuBuilder.selectAll$foundation();
        return Unit.INSTANCE;
    }

    public static final Unit contextMenuBuilder$lambda$0$4(TextFieldSelectionManager $this_contextMenuBuilder) {
        $this_contextMenuBuilder.autofill$foundation();
        return Unit.INSTANCE;
    }
}
