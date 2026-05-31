package androidx.compose.foundation.text.selection;

import android.content.Context;
import android.os.Build;
import androidx.compose.foundation.Magnifier_androidKt;
import androidx.compose.foundation.PlatformMagnifierFactory;
import androidx.compose.foundation.internal.ClipboardUtils;
import androidx.compose.foundation.text.ContextMenu_androidKt;
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
import androidx.compose.ui.platform.Clipboard;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import androidx.compose.ui.text.input.OffsetMapping;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.DpSize;
import androidx.compose.ui.unit.IntSize;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;

/* JADX INFO: compiled from: TextFieldSelectionManager.android.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0000\u001a\u001c\u0010\u0004\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\u0000\u001a\u0012\u0010\u0007\u001a\u00020\b*\u00020\u0003H\u0080@¢\u0006\u0002\u0010\t\u001a\u0014\u0010\n\u001a\u00020\b*\u00020\u00032\u0006\u0010\u000b\u001a\u00020\bH\u0000¨\u0006\f²\u0006\n\u0010\r\u001a\u00020\u000eX\u008a\u008e\u0002"}, d2 = {"textFieldMagnifier", "Landroidx/compose/ui/Modifier;", "manager", "Landroidx/compose/foundation/text/selection/TextFieldSelectionManager;", "addBasicTextFieldTextContextMenuComponents", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "hasAvailableTextToPaste", "", "(Landroidx/compose/foundation/text/selection/TextFieldSelectionManager;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isSelectionHandleInVisibleBound", "isStartHandle", "foundation", "magnifierSize", "Landroidx/compose/ui/unit/IntSize;"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class TextFieldSelectionManager_androidKt {
    public static final Modifier textFieldMagnifier(Modifier $this$textFieldMagnifier, final TextFieldSelectionManager manager) {
        if (!Magnifier_androidKt.isPlatformMagnifierSupported$default(0, 1, null)) {
            return $this$textFieldMagnifier;
        }
        return ComposedModifierKt.composed$default($this$textFieldMagnifier, null, new Function3() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManager_androidKt$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return TextFieldSelectionManager_androidKt.textFieldMagnifier$lambda$0(manager, (Modifier) obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        }, 1, null);
    }

    static final Modifier textFieldMagnifier$lambda$0(final TextFieldSelectionManager $manager, Modifier $this$composed, Composer $composer, int $changed) {
        $composer.startReplaceGroup(1980580247);
        ComposerKt.sourceInformation($composer, "C54@2523L7,55@2560L41,57@2668L68,58@2770L540:TextFieldSelectionManager.android.kt#eksfi3");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1980580247, $changed, -1, "androidx.compose.foundation.text.selection.textFieldMagnifier.<anonymous> (TextFieldSelectionManager.android.kt:54)");
        }
        ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
        Object objConsume = $composer.consume(localDensity);
        ComposerKt.sourceInformationMarkerEnd($composer);
        final Density density = (Density) objConsume;
        ComposerKt.sourceInformationMarkerStart($composer, 667107648, "CC(remember):TextFieldSelectionManager.android.kt#9igjgp");
        Object it$iv = $composer.rememberedValue();
        if (it$iv == Composer.INSTANCE.getEmpty()) {
            Object value$iv = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(IntSize.m8313boximpl(IntSize.INSTANCE.m8326getZeroYbymL2g()), null, 2, null);
            $composer.updateRememberedValue(value$iv);
            it$iv = value$iv;
        }
        final MutableState magnifierSize$delegate = (MutableState) it$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerStart($composer, 667111131, "CC(remember):TextFieldSelectionManager.android.kt#9igjgp");
        boolean invalid$iv = $composer.changedInstance($manager);
        Object it$iv2 = $composer.rememberedValue();
        if (invalid$iv || it$iv2 == Composer.INSTANCE.getEmpty()) {
            Object value$iv2 = new Function0() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManager_androidKt$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return TextFieldSelectionManager_androidKt.textFieldMagnifier$lambda$0$3$0($manager, magnifierSize$delegate);
                }
            };
            $composer.updateRememberedValue(value$iv2);
            it$iv2 = value$iv2;
        }
        Function0 function0 = (Function0) it$iv2;
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerStart($composer, 667114867, "CC(remember):TextFieldSelectionManager.android.kt#9igjgp");
        boolean invalid$iv2 = $composer.changed(density);
        Object it$iv3 = $composer.rememberedValue();
        if (invalid$iv2 || it$iv3 == Composer.INSTANCE.getEmpty()) {
            Object value$iv3 = new Function1() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManager_androidKt$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return TextFieldSelectionManager_androidKt.textFieldMagnifier$lambda$0$4$0(density, magnifierSize$delegate, (Function0) obj);
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

    private static final long textFieldMagnifier$lambda$0$1(MutableState<IntSize> mutableState) {
        MutableState<IntSize> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue().m8325unboximpl();
    }

    private static final void textFieldMagnifier$lambda$0$2(MutableState<IntSize> mutableState, long j) {
        mutableState.setValue(IntSize.m8313boximpl(j));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Offset textFieldMagnifier$lambda$0$3$0(TextFieldSelectionManager $manager, MutableState $magnifierSize$delegate) {
        return Offset.m5057boximpl(TextFieldSelectionManagerKt.m2114calculateSelectionMagnifierCenterAndroidO0kMr_c($manager, textFieldMagnifier$lambda$0$1($magnifierSize$delegate)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Modifier textFieldMagnifier$lambda$0$4$0(final Density $density, final MutableState $magnifierSize$delegate, final Function0 center) {
        return Magnifier_androidKt.m355magnifierjPUL71Q$default(Modifier.INSTANCE, new Function1() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManager_androidKt$$ExternalSyntheticLambda8
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TextFieldSelectionManager_androidKt.textFieldMagnifier$lambda$0$4$0$0(center, (Density) obj);
            }
        }, null, new Function1() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManager_androidKt$$ExternalSyntheticLambda9
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TextFieldSelectionManager_androidKt.textFieldMagnifier$lambda$0$4$0$1($density, $magnifierSize$delegate, (DpSize) obj);
            }
        }, 0.0f, true, 0L, 0.0f, 0.0f, false, PlatformMagnifierFactory.INSTANCE.getForCurrentPlatform(), 490, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Offset textFieldMagnifier$lambda$0$4$0$0(Function0 $center, Density $this$magnifier) {
        return (Offset) $center.invoke();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit textFieldMagnifier$lambda$0$4$0$1(Density $density, MutableState $magnifierSize$delegate, DpSize size) {
        int width$iv = $density.mo426roundToPx0680j_4(DpSize.m8248getWidthD9Ej5fM(size.getPackedValue()));
        int height$iv = $density.mo426roundToPx0680j_4(DpSize.m8246getHeightD9Ej5fM(size.getPackedValue()));
        textFieldMagnifier$lambda$0$2($magnifierSize$delegate, IntSize.m8316constructorimpl((((long) width$iv) << 32) | (((long) height$iv) & 4294967295L)));
        return Unit.INSTANCE;
    }

    public static final Modifier addBasicTextFieldTextContextMenuComponents(Modifier $this$addBasicTextFieldTextContextMenuComponents, final TextFieldSelectionManager manager, final CoroutineScope coroutineScope) {
        return TextContextMenuModifier_androidKt.addTextContextMenuComponentsWithContext($this$addBasicTextFieldTextContextMenuComponents, new Function2() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManager_androidKt$$ExternalSyntheticLambda10
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return TextFieldSelectionManager_androidKt.addBasicTextFieldTextContextMenuComponents$lambda$0(manager, coroutineScope, (TextContextMenuBuilderScope) obj, (Context) obj2);
            }
        });
    }

    static /* synthetic */ void addBasicTextFieldTextContextMenuComponents$lambda$0$textFieldItem$default(TextContextMenuBuilderScope textContextMenuBuilderScope, Context context, TextContextMenuItems textContextMenuItems, boolean z, Function0 function0, Function0 function02, int i, Object obj) {
        Function0 function03;
        if ((i & 8) == 0) {
            function03 = function0;
        } else {
            function03 = null;
        }
        addBasicTextFieldTextContextMenuComponents$lambda$0$textFieldItem(textContextMenuBuilderScope, context, textContextMenuItems, z, function03, function02);
    }

    private static final void addBasicTextFieldTextContextMenuComponents$lambda$0$textFieldItem(TextContextMenuBuilderScope $this$addBasicTextFieldTextContextMenuComponents_u24lambda_u240_u24textFieldItem, Context $context, TextContextMenuItems item, boolean enabled, final Function0<Boolean> function0, final Function0<Unit> function02) {
        ContextMenu_androidKt.textItem($this$addBasicTextFieldTextContextMenuComponents_u24lambda_u240_u24textFieldItem, $context.getResources(), item, enabled, new Function1() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManager_androidKt$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TextFieldSelectionManager_androidKt.addBasicTextFieldTextContextMenuComponents$lambda$0$textFieldItem$0(function02, function0, (TextContextMenuSession) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit addBasicTextFieldTextContextMenuComponents$lambda$0$textFieldItem$0(Function0 $onClick, Function0 $closePredicate, TextContextMenuSession $this$textItem) {
        $onClick.invoke();
        if ($closePredicate != null ? ((Boolean) $closePredicate.invoke()).booleanValue() : true) {
            $this$textItem.close();
        }
        return Unit.INSTANCE;
    }

    private static final void addBasicTextFieldTextContextMenuComponents$lambda$0$textFieldSuspendItem(TextContextMenuBuilderScope $this$addBasicTextFieldTextContextMenuComponents_u24lambda_u240_u24textFieldSuspendItem, final CoroutineScope $coroutineScope, Context $context, TextContextMenuItems item, boolean enabled, final Function1<? super Continuation<? super Unit>, ? extends Object> function1) {
        addBasicTextFieldTextContextMenuComponents$lambda$0$textFieldItem$default($this$addBasicTextFieldTextContextMenuComponents_u24lambda_u240_u24textFieldSuspendItem, $context, item, enabled, null, new Function0() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManager_androidKt$$ExternalSyntheticLambda11
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return TextFieldSelectionManager_androidKt.addBasicTextFieldTextContextMenuComponents$lambda$0$textFieldSuspendItem$1($coroutineScope, function1);
            }
        }, 8, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit addBasicTextFieldTextContextMenuComponents$lambda$0$textFieldSuspendItem$1(CoroutineScope $coroutineScope, Function1 $onClick) {
        BuildersKt__Builders_commonKt.launch$default($coroutineScope, null, CoroutineStart.UNDISPATCHED, new TextFieldSelectionManager_androidKt$addBasicTextFieldTextContextMenuComponents$1$textFieldSuspendItem$1$1($onClick, null), 1, null);
        return Unit.INSTANCE;
    }

    static final Unit addBasicTextFieldTextContextMenuComponents$lambda$0(final TextFieldSelectionManager $manager, final CoroutineScope $coroutineScope, TextContextMenuBuilderScope $this$addTextContextMenuComponentsWithContext, final Context context) {
        boolean editable = $manager.getEditable();
        AnnotatedString transformedText$foundation = $manager.getTransformedText$foundation();
        TextRange textRangeM7561boximpl = null;
        String text = transformedText$foundation != null ? transformedText$foundation.getText() : null;
        TextRange latestSelection = $manager.getLatestSelection();
        if (latestSelection != null) {
            long it = latestSelection.getPackedValue();
            OffsetMapping offsetMapping = $manager.getOffsetMapping();
            textRangeM7561boximpl = TextRange.m7561boximpl(TextRangeKt.TextRange(offsetMapping.originalToTransformed(TextRange.m7573getStartimpl(it)), offsetMapping.originalToTransformed(TextRange.m7568getEndimpl(it))));
        }
        PlatformSelectionBehaviors_androidKt.m2043addPlatformTextContextMenuItems71BSaZU($this$addTextContextMenuComponentsWithContext, context, editable, text, textRangeM7561boximpl, $manager.getPlatformSelectionBehaviors(), new Function1() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManager_androidKt$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TextFieldSelectionManager_androidKt.addBasicTextFieldTextContextMenuComponents$lambda$0$3($manager, $coroutineScope, context, (TextContextMenuBuilderScope) obj);
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit addBasicTextFieldTextContextMenuComponents$lambda$0$3(final TextFieldSelectionManager $manager, CoroutineScope $coroutineScope, Context $context, TextContextMenuBuilderScope $this$addPlatformTextContextMenuItems) {
        $this$addPlatformTextContextMenuItems.separator();
        addBasicTextFieldTextContextMenuComponents$lambda$0$textFieldSuspendItem($this$addPlatformTextContextMenuItems, $coroutineScope, $context, TextContextMenuItems.Cut, $manager.canShowCutMenuItem$foundation(), new TextFieldSelectionManager_androidKt$addBasicTextFieldTextContextMenuComponents$1$2$1$1($manager, null));
        addBasicTextFieldTextContextMenuComponents$lambda$0$textFieldSuspendItem($this$addPlatformTextContextMenuItems, $coroutineScope, $context, TextContextMenuItems.Copy, $manager.canShowCopyMenuItem$foundation(), new TextFieldSelectionManager_androidKt$addBasicTextFieldTextContextMenuComponents$1$2$1$2($manager, null));
        addBasicTextFieldTextContextMenuComponents$lambda$0$textFieldSuspendItem($this$addPlatformTextContextMenuItems, $coroutineScope, $context, TextContextMenuItems.Paste, $manager.canShowPasteMenuItem$foundation(), new TextFieldSelectionManager_androidKt$addBasicTextFieldTextContextMenuComponents$1$2$1$3($manager, null));
        addBasicTextFieldTextContextMenuComponents$lambda$0$textFieldItem($this$addPlatformTextContextMenuItems, $context, TextContextMenuItems.SelectAll, $manager.canShowSelectAllMenuItem$foundation(), new Function0() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManager_androidKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(TextFieldSelectionManager_androidKt.addBasicTextFieldTextContextMenuComponents$lambda$0$3$0$0($manager));
            }
        }, new Function0() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManager_androidKt$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return TextFieldSelectionManager_androidKt.addBasicTextFieldTextContextMenuComponents$lambda$0$3$0$1($manager);
            }
        });
        if (Build.VERSION.SDK_INT >= 26) {
            addBasicTextFieldTextContextMenuComponents$lambda$0$textFieldItem$default($this$addPlatformTextContextMenuItems, $context, TextContextMenuItems.Autofill, $manager.canShowAutofillMenuItem$foundation(), null, new Function0() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManager_androidKt$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return TextFieldSelectionManager_androidKt.addBasicTextFieldTextContextMenuComponents$lambda$0$3$0$2($manager);
                }
            }, 8, null);
        }
        $this$addPlatformTextContextMenuItems.separator();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean addBasicTextFieldTextContextMenuComponents$lambda$0$3$0$0(TextFieldSelectionManager $this_with) {
        return !$this_with.getTextToolbarShown$foundation();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit addBasicTextFieldTextContextMenuComponents$lambda$0$3$0$1(TextFieldSelectionManager $this_with) {
        $this_with.selectAll$foundation();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit addBasicTextFieldTextContextMenuComponents$lambda$0$3$0$2(TextFieldSelectionManager $this_with) {
        $this_with.autofill$foundation();
        return Unit.INSTANCE;
    }

    public static final Object hasAvailableTextToPaste(TextFieldSelectionManager $this$hasAvailableTextToPaste, Continuation<? super Boolean> continuation) {
        Clipboard it = $this$hasAvailableTextToPaste.getClipboard();
        return Boxing.boxBoolean(it != null ? ClipboardUtils.hasText(it) : false);
    }

    public static final boolean isSelectionHandleInVisibleBound(TextFieldSelectionManager $this$isSelectionHandleInVisibleBound, boolean isStartHandle) {
        return TextFieldSelectionManagerKt.isSelectionHandleInVisibleBoundDefault($this$isSelectionHandleInVisibleBound, isStartHandle);
    }
}
