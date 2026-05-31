package androidx.compose.foundation.text.selection;

import android.content.Context;
import android.view.KeyEvent;
import androidx.compose.foundation.Magnifier_androidKt;
import androidx.compose.foundation.PlatformMagnifierFactory;
import androidx.compose.foundation.text.ContextMenu_androidKt;
import androidx.compose.foundation.text.KeyCommand;
import androidx.compose.foundation.text.KeyMapping_androidKt;
import androidx.compose.foundation.text.TextContextMenuItems;
import androidx.compose.foundation.text.contextmenu.builder.TextContextMenuBuilderScope;
import androidx.compose.foundation.text.contextmenu.data.TextContextMenuSession;
import androidx.compose.foundation.text.contextmenu.modifier.TextContextMenuModifier_androidKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.DpSize;
import androidx.compose.ui.unit.IntSize;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;

/* JADX INFO: compiled from: SelectionManager.android.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\u001a\u0017\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0000¢\u0006\u0004\b\u0004\u0010\u0005\u001a\u0014\u0010\u0006\u001a\u00020\u0007*\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0000\u001a\u0014\u0010\n\u001a\u00020\u0007*\u00020\u00072\u0006\u0010\u000b\u001a\u00020\tH\u0000¨\u0006\f²\u0006\n\u0010\r\u001a\u00020\u000eX\u008a\u008e\u0002"}, d2 = {"isCopyKeyEvent", "", "keyEvent", "Landroidx/compose/ui/input/key/KeyEvent;", "isCopyKeyEvent-ZmokQxo", "(Landroid/view/KeyEvent;)Z", "selectionMagnifier", "Landroidx/compose/ui/Modifier;", "manager", "Landroidx/compose/foundation/text/selection/SelectionManager;", "addSelectionContainerTextContextMenuComponents", "selectionManager", "foundation", "magnifierSize", "Landroidx/compose/ui/unit/IntSize;"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class SelectionManager_androidKt {
    /* JADX INFO: renamed from: isCopyKeyEvent-ZmokQxo, reason: not valid java name */
    public static final boolean m2082isCopyKeyEventZmokQxo(KeyEvent keyEvent) {
        return KeyMapping_androidKt.getPlatformDefaultKeyMapping().mo1539mapZmokQxo(keyEvent) == KeyCommand.COPY;
    }

    public static final Modifier selectionMagnifier(Modifier $this$selectionMagnifier, final SelectionManager manager) {
        if (!Magnifier_androidKt.isPlatformMagnifierSupported$default(0, 1, null)) {
            return $this$selectionMagnifier;
        }
        return ComposedModifierKt.composed$default($this$selectionMagnifier, null, new Function3() { // from class: androidx.compose.foundation.text.selection.SelectionManager_androidKt$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return SelectionManager_androidKt.selectionMagnifier$lambda$0(manager, (Modifier) obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        }, 1, null);
    }

    static final Modifier selectionMagnifier$lambda$0(final SelectionManager $manager, Modifier $this$composed, Composer $composer, int $changed) {
        $composer.startReplaceGroup(-1914520728);
        ComposerKt.sourceInformation($composer, "C51@2356L7,52@2393L41,54@2501L68,55@2603L540:SelectionManager.android.kt#eksfi3");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1914520728, $changed, -1, "androidx.compose.foundation.text.selection.selectionMagnifier.<anonymous> (SelectionManager.android.kt:51)");
        }
        ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
        Object objConsume = $composer.consume(localDensity);
        ComposerKt.sourceInformationMarkerEnd($composer);
        final Density density = (Density) objConsume;
        ComposerKt.sourceInformationMarkerStart($composer, -1608414511, "CC(remember):SelectionManager.android.kt#9igjgp");
        Object it$iv = $composer.rememberedValue();
        if (it$iv == Composer.INSTANCE.getEmpty()) {
            Object value$iv = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(IntSize.m8313boximpl(IntSize.INSTANCE.m8326getZeroYbymL2g()), null, 2, null);
            $composer.updateRememberedValue(value$iv);
            it$iv = value$iv;
        }
        final MutableState magnifierSize$delegate = (MutableState) it$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerStart($composer, -1608411028, "CC(remember):SelectionManager.android.kt#9igjgp");
        boolean invalid$iv = $composer.changedInstance($manager);
        Object it$iv2 = $composer.rememberedValue();
        if (invalid$iv || it$iv2 == Composer.INSTANCE.getEmpty()) {
            Object value$iv2 = new Function0() { // from class: androidx.compose.foundation.text.selection.SelectionManager_androidKt$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return SelectionManager_androidKt.selectionMagnifier$lambda$0$3$0($manager, magnifierSize$delegate);
                }
            };
            $composer.updateRememberedValue(value$iv2);
            it$iv2 = value$iv2;
        }
        Function0 function0 = (Function0) it$iv2;
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerStart($composer, -1608407292, "CC(remember):SelectionManager.android.kt#9igjgp");
        boolean invalid$iv2 = $composer.changed(density);
        Object it$iv3 = $composer.rememberedValue();
        if (invalid$iv2 || it$iv3 == Composer.INSTANCE.getEmpty()) {
            Object value$iv3 = new Function1() { // from class: androidx.compose.foundation.text.selection.SelectionManager_androidKt$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return SelectionManager_androidKt.selectionMagnifier$lambda$0$4$0(density, magnifierSize$delegate, (Function0) obj);
                }
            };
            $composer.updateRememberedValue(value$iv3);
            it$iv3 = value$iv3;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        Modifier modifierAnimatedSelectionMagnifier = SelectionMagnifierKt.animatedSelectionMagnifier($this$composed, function0, (Function1) it$iv3);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceGroup();
        return modifierAnimatedSelectionMagnifier;
    }

    private static final long selectionMagnifier$lambda$0$1(MutableState<IntSize> mutableState) {
        MutableState<IntSize> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue().m8325unboximpl();
    }

    private static final void selectionMagnifier$lambda$0$2(MutableState<IntSize> mutableState, long j) {
        mutableState.setValue(IntSize.m8313boximpl(j));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Offset selectionMagnifier$lambda$0$3$0(SelectionManager $manager, MutableState $magnifierSize$delegate) {
        return Offset.m5057boximpl(SelectionManagerKt.m2077calculateSelectionMagnifierCenterAndroidO0kMr_c($manager, selectionMagnifier$lambda$0$1($magnifierSize$delegate)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Modifier selectionMagnifier$lambda$0$4$0(final Density $density, final MutableState $magnifierSize$delegate, final Function0 center) {
        return Magnifier_androidKt.m355magnifierjPUL71Q$default(Modifier.INSTANCE, new Function1() { // from class: androidx.compose.foundation.text.selection.SelectionManager_androidKt$$ExternalSyntheticLambda9
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SelectionManager_androidKt.selectionMagnifier$lambda$0$4$0$0(center, (Density) obj);
            }
        }, null, new Function1() { // from class: androidx.compose.foundation.text.selection.SelectionManager_androidKt$$ExternalSyntheticLambda10
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SelectionManager_androidKt.selectionMagnifier$lambda$0$4$0$1($density, $magnifierSize$delegate, (DpSize) obj);
            }
        }, 0.0f, true, 0L, 0.0f, 0.0f, false, PlatformMagnifierFactory.INSTANCE.getForCurrentPlatform(), 490, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Offset selectionMagnifier$lambda$0$4$0$0(Function0 $center, Density $this$magnifier) {
        return (Offset) $center.invoke();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit selectionMagnifier$lambda$0$4$0$1(Density $density, MutableState $magnifierSize$delegate, DpSize size) {
        int width$iv = $density.mo426roundToPx0680j_4(DpSize.m8248getWidthD9Ej5fM(size.getPackedValue()));
        int height$iv = $density.mo426roundToPx0680j_4(DpSize.m8246getHeightD9Ej5fM(size.getPackedValue()));
        selectionMagnifier$lambda$0$2($magnifierSize$delegate, IntSize.m8316constructorimpl((((long) width$iv) << 32) | (((long) height$iv) & 4294967295L)));
        return Unit.INSTANCE;
    }

    public static final Modifier addSelectionContainerTextContextMenuComponents(Modifier $this$addSelectionContainerTextContextMenuComponents, final SelectionManager selectionManager) {
        return TextContextMenuModifier_androidKt.addTextContextMenuComponentsWithContext($this$addSelectionContainerTextContextMenuComponents, new Function2() { // from class: androidx.compose.foundation.text.selection.SelectionManager_androidKt$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return SelectionManager_androidKt.addSelectionContainerTextContextMenuComponents$lambda$0(selectionManager, (TextContextMenuBuilderScope) obj, (Context) obj2);
            }
        });
    }

    static /* synthetic */ void addSelectionContainerTextContextMenuComponents$lambda$0$selectionContainerItem$default(TextContextMenuBuilderScope textContextMenuBuilderScope, Context context, TextContextMenuItems textContextMenuItems, boolean z, Function0 function0, Function0 function02, int i, Object obj) {
        Function0 function03;
        if ((i & 8) == 0) {
            function03 = function0;
        } else {
            function03 = null;
        }
        addSelectionContainerTextContextMenuComponents$lambda$0$selectionContainerItem(textContextMenuBuilderScope, context, textContextMenuItems, z, function03, function02);
    }

    private static final void addSelectionContainerTextContextMenuComponents$lambda$0$selectionContainerItem(TextContextMenuBuilderScope $this$addSelectionContainerTextContextMenuComponents_u24lambda_u240_u24selectionContainerItem, Context $context, TextContextMenuItems item, boolean enabled, final Function0<Boolean> function0, final Function0<Unit> function02) {
        ContextMenu_androidKt.textItem($this$addSelectionContainerTextContextMenuComponents_u24lambda_u240_u24selectionContainerItem, $context.getResources(), item, enabled, new Function1() { // from class: androidx.compose.foundation.text.selection.SelectionManager_androidKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SelectionManager_androidKt.addSelectionContainerTextContextMenuComponents$lambda$0$selectionContainerItem$0(function02, function0, (TextContextMenuSession) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit addSelectionContainerTextContextMenuComponents$lambda$0$selectionContainerItem$0(Function0 $onClick, Function0 $closePredicate, TextContextMenuSession $this$textItem) {
        $onClick.invoke();
        if ($closePredicate != null ? ((Boolean) $closePredicate.invoke()).booleanValue() : true) {
            $this$textItem.close();
        }
        return Unit.INSTANCE;
    }

    static final Unit addSelectionContainerTextContextMenuComponents$lambda$0(final SelectionManager $selectionManager, TextContextMenuBuilderScope $this$addTextContextMenuComponentsWithContext, final Context context) {
        Pair<AnnotatedString, TextRange> contextTextAndSelection$foundation = $selectionManager.getContextTextAndSelection$foundation();
        PlatformSelectionBehaviors_androidKt.m2043addPlatformTextContextMenuItems71BSaZU($this$addTextContextMenuComponentsWithContext, context, false, contextTextAndSelection$foundation != null ? contextTextAndSelection$foundation.getFirst() : null, contextTextAndSelection$foundation != null ? contextTextAndSelection$foundation.getSecond() : null, $selectionManager.getPlatformSelectionBehaviors(), new Function1() { // from class: androidx.compose.foundation.text.selection.SelectionManager_androidKt$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SelectionManager_androidKt.addSelectionContainerTextContextMenuComponents$lambda$0$1($selectionManager, context, (TextContextMenuBuilderScope) obj);
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit addSelectionContainerTextContextMenuComponents$lambda$0$1(final SelectionManager $selectionManager, Context $context, TextContextMenuBuilderScope $this$addPlatformTextContextMenuItems) {
        $this$addPlatformTextContextMenuItems.separator();
        addSelectionContainerTextContextMenuComponents$lambda$0$selectionContainerItem$default($this$addPlatformTextContextMenuItems, $context, TextContextMenuItems.Copy, $selectionManager.isNonEmptySelection$foundation(), null, new Function0() { // from class: androidx.compose.foundation.text.selection.SelectionManager_androidKt$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return SelectionManager_androidKt.addSelectionContainerTextContextMenuComponents$lambda$0$1$0$0($selectionManager);
            }
        }, 8, null);
        addSelectionContainerTextContextMenuComponents$lambda$0$selectionContainerItem($this$addPlatformTextContextMenuItems, $context, TextContextMenuItems.SelectAll, !$selectionManager.isEntireContainerSelected$foundation(), new Function0() { // from class: androidx.compose.foundation.text.selection.SelectionManager_androidKt$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(SelectionManager_androidKt.addSelectionContainerTextContextMenuComponents$lambda$0$1$0$1($selectionManager));
            }
        }, new Function0() { // from class: androidx.compose.foundation.text.selection.SelectionManager_androidKt$$ExternalSyntheticLambda8
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return SelectionManager_androidKt.addSelectionContainerTextContextMenuComponents$lambda$0$1$0$2($selectionManager);
            }
        });
        $this$addPlatformTextContextMenuItems.separator();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit addSelectionContainerTextContextMenuComponents$lambda$0$1$0$0(SelectionManager $this_with) {
        $this_with.copy$foundation();
        if ($this_with.isInTouchMode()) {
            $this_with.onRelease();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean addSelectionContainerTextContextMenuComponents$lambda$0$1$0$1(SelectionManager $this_with) {
        return ($this_with.getShowToolbar() && $this_with.isInTouchMode()) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit addSelectionContainerTextContextMenuComponents$lambda$0$1$0$2(SelectionManager $this_with) {
        $this_with.selectAll$foundation();
        return Unit.INSTANCE;
    }
}
