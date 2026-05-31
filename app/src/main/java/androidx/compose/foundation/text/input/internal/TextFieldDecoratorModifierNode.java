package androidx.compose.foundation.text.input.internal;

import android.view.KeyEvent;
import androidx.compose.foundation.FocusableNode;
import androidx.compose.foundation.content.ReceiveContentListener;
import androidx.compose.foundation.content.TransferableContent;
import androidx.compose.foundation.content.TransferableContent_androidKt;
import androidx.compose.foundation.content.internal.DragAndDropRequestPermission_androidKt;
import androidx.compose.foundation.content.internal.ReceiveContentConfiguration;
import androidx.compose.foundation.content.internal.ReceiveContentConfigurationKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.text.AutofillHighlightKt;
import androidx.compose.foundation.text.AutofillHighlight_androidKt;
import androidx.compose.foundation.text.Handle;
import androidx.compose.foundation.text.KeyCommand;
import androidx.compose.foundation.text.KeyboardOptions;
import androidx.compose.foundation.text.input.InputTransformation;
import androidx.compose.foundation.text.input.KeyboardActionHandler;
import androidx.compose.foundation.text.input.TextFieldBuffer;
import androidx.compose.foundation.text.input.TextFieldCharSequence;
import androidx.compose.foundation.text.input.TextFieldState;
import androidx.compose.foundation.text.input.internal.DragAndDropHoverInteraction;
import androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode;
import androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState;
import androidx.compose.foundation.text.input.internal.selection.TextToolbarState;
import androidx.compose.foundation.text.input.internal.undo.TextFieldEditUndoBehavior;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.autofill.ContentDataType;
import androidx.compose.ui.autofill.ContentType;
import androidx.compose.ui.autofill.FillableData;
import androidx.compose.ui.autofill.FillableData_androidKt;
import androidx.compose.ui.draganddrop.DragAndDropEvent;
import androidx.compose.ui.draganddrop.DragAndDropTargetModifierNode;
import androidx.compose.ui.focus.FocusDirection;
import androidx.compose.ui.focus.FocusManager;
import androidx.compose.ui.focus.FocusProperties;
import androidx.compose.ui.focus.FocusPropertiesModifierNode;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.input.InputMode;
import androidx.compose.ui.input.InputModeManager;
import androidx.compose.ui.input.key.KeyInputModifierNode;
import androidx.compose.ui.input.pointer.PointerEvent;
import androidx.compose.ui.input.pointer.PointerEventPass;
import androidx.compose.ui.input.pointer.PointerInputEventHandler;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.input.pointer.SuspendingPointerInputModifierNode;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.modifier.ModifierLocalModifierNode;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNodeKt;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.DrawModifierNode;
import androidx.compose.ui.node.GlobalPositionAwareModifierNode;
import androidx.compose.ui.node.LayoutAwareModifierNode;
import androidx.compose.ui.node.ObserverModifierNode;
import androidx.compose.ui.node.ObserverModifierNodeKt;
import androidx.compose.ui.node.PointerInputModifierNode;
import androidx.compose.ui.node.SemanticsModifierNode;
import androidx.compose.ui.node.SemanticsModifierNodeKt;
import androidx.compose.ui.platform.ClipEntry;
import androidx.compose.ui.platform.ClipMetadata;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.PlatformTextInputModifierNode;
import androidx.compose.ui.platform.PlatformTextInputModifierNodeKt;
import androidx.compose.ui.platform.PlatformTextInputSessionScope;
import androidx.compose.ui.platform.SoftwareKeyboardController;
import androidx.compose.ui.platform.ViewConfiguration;
import androidx.compose.ui.platform.WindowInfo;
import androidx.compose.ui.semantics.InputTextSuggestionState;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import androidx.compose.ui.text.input.ImeAction;
import androidx.compose.ui.text.input.ImeOptions;
import androidx.compose.ui.text.input.KeyboardType;
import androidx.core.app.NotificationCompat;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CancellationException;
import kotlin.KotlinNothingValueException;
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
import kotlin.jvm.internal.AdaptedFunctionReference;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableSharedFlow;

/* JADX INFO: compiled from: TextFieldDecoratorModifier.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u008b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005*\u0001^\b\u0001\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u00052\u00020\u00062\u00020\u00072\u00020\b2\u00020\t2\u00020\n2\u00020\u000b2\u00020\fBs\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0012\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014\u0012\u0006\u0010\u0015\u001a\u00020\u0016\u0012\u0006\u0010\u0017\u001a\u00020\u0016\u0012\u0006\u0010\u0018\u001a\u00020\u0019\u0012\b\u0010\u001a\u001a\u0004\u0018\u00010\u001b\u0012\u0006\u0010\u001c\u001a\u00020\u0016\u0012\u0006\u0010\u001d\u001a\u00020\u001e\u0012\u0006\u0010\u001f\u001a\u00020\u0016\u0012\u000e\u0010 \u001a\n\u0012\u0004\u0012\u00020\"\u0018\u00010!¢\u0006\u0004\b#\u0010$J\f\u0010m\u001a\u00020\"*\u00020nH\u0016J\u000e\u0010o\u001a\u00020\"H\u0082@¢\u0006\u0002\u0010pJr\u0010q\u001a\u00020\"2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u001c\u001a\u00020\u00162\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u00162\u000e\u0010 \u001a\n\u0012\u0004\u0012\u00020\"\u0018\u00010!J\f\u0010t\u001a\u00020\"*\u00020uH\u0016J\b\u0010v\u001a\u00020\"H\u0002J\b\u0010w\u001a\u00020\"H\u0002J\u0010\u0010x\u001a\u00020\"2\u0006\u0010y\u001a\u00020zH\u0016J\b\u0010{\u001a\u00020\"H\u0016J\b\u0010|\u001a\u00020\"H\u0016J\u0010\u0010}\u001a\u00020\"2\u0006\u0010~\u001a\u00020\u007fH\u0016J0\u0010\u0080\u0001\u001a\u00020\"2\b\u0010\u0081\u0001\u001a\u00030\u0082\u00012\b\u0010\u0083\u0001\u001a\u00030\u0084\u00012\b\u0010\u0085\u0001\u001a\u00030\u0086\u0001H\u0016¢\u0006\u0006\b\u0087\u0001\u0010\u0088\u0001J\t\u0010\u0089\u0001\u001a\u00020\"H\u0016J\u001c\u0010\u008a\u0001\u001a\u00020\u00162\b\u0010\u008b\u0001\u001a\u00030\u008c\u0001H\u0016¢\u0006\u0006\b\u008d\u0001\u0010\u008e\u0001J\u001c\u0010\u008f\u0001\u001a\u00020\u00162\b\u0010\u008b\u0001\u001a\u00030\u008c\u0001H\u0016¢\u0006\u0006\b\u0090\u0001\u0010\u008e\u0001J\t\u0010\u0091\u0001\u001a\u00020\"H\u0016J\t\u0010\u0092\u0001\u001a\u00020\"H\u0002J\u0011\u0010\u0093\u0001\u001a\u00020\"2\u0006\u0010~\u001a\u00020\u007fH\u0016J\u001c\u0010\u0094\u0001\u001a\u00020\"2\b\u0010\u0095\u0001\u001a\u00030\u0086\u0001H\u0016¢\u0006\u0006\b\u0096\u0001\u0010\u0097\u0001J\t\u0010\u0098\u0001\u001a\u00020\"H\u0002J\u0012\u0010\u0099\u0001\u001a\u00020\"2\u0007\u0010\u009a\u0001\u001a\u00020\u0016H\u0002J\t\u0010\u009b\u0001\u001a\u00020\"H\u0002J\n\u0010\u009c\u0001\u001a\u00030\u009d\u0001H\u0002J\t\u0010\u009e\u0001\u001a\u00020\"H\u0002J\u001c\u0010\u009f\u0001\u001a\u00020\u00162\b\u0010 \u0001\u001a\u00030¡\u0001H\u0002¢\u0006\u0006\b¢\u0001\u0010£\u0001J\u001c\u0010¤\u0001\u001a\u00020\u00162\b\u0010 \u0001\u001a\u00030¡\u0001H\u0002¢\u0006\u0006\b¥\u0001\u0010£\u0001R\u001a\u0010\r\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R\u001a\u0010\u000f\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R\u001a\u0010\u0011\u001a\u00020\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R\u001c\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u00102\"\u0004\b3\u00104R\u001a\u0010\u0015\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b5\u00106\"\u0004\b7\u00108R\u001a\u0010\u0017\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b9\u00106\"\u0004\b:\u00108R\u001a\u0010\u0018\u001a\u00020\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b;\u0010<\"\u0004\b=\u0010>R\u001c\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b?\u0010@\"\u0004\bA\u0010BR\u001a\u0010\u001c\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bC\u00106\"\u0004\bD\u00108R\u001a\u0010\u001d\u001a\u00020\u001eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bE\u0010F\"\u0004\bG\u0010HR\u001a\u0010\u001f\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u00106\"\u0004\bI\u00108R\"\u0010 \u001a\n\u0012\u0004\u0012\u00020\"\u0018\u00010!X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bJ\u0010K\"\u0004\bL\u0010MR\u000e\u0010N\u001a\u00020OX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010P\u001a\u00020QX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010R\u001a\u0004\u0018\u00010SX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010T\u001a\u00020UX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010V\u001a\u0004\u0018\u00010WX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010X\u001a\u00020\u00168BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bX\u00106R\u0010\u0010Y\u001a\u0004\u0018\u00010ZX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010[\u001a\u00020\\X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010]\u001a\u00020^X\u0082\u0004¢\u0006\u0004\n\u0002\u0010_R\u0010\u0010`\u001a\u00020aX\u0082\u0004¢\u0006\u0004\n\u0002\u0010bR\u0010\u0010c\u001a\u0004\u0018\u00010ZX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010d\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010f0eX\u0082\u0004¢\u0006\u0002\n\u0000R+\u0010h\u001a\u00020\u00162\u0006\u0010g\u001a\u00020\u00168B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\bk\u0010l\u001a\u0004\bi\u00106\"\u0004\bj\u00108R\u0014\u0010r\u001a\u00020\u00168VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bs\u00106¨\u0006¦\u0001"}, d2 = {"Landroidx/compose/foundation/text/input/internal/TextFieldDecoratorModifierNode;", "Landroidx/compose/ui/node/DelegatingNode;", "Landroidx/compose/ui/node/DrawModifierNode;", "Landroidx/compose/ui/platform/PlatformTextInputModifierNode;", "Landroidx/compose/ui/node/SemanticsModifierNode;", "Landroidx/compose/ui/node/GlobalPositionAwareModifierNode;", "Landroidx/compose/ui/node/PointerInputModifierNode;", "Landroidx/compose/ui/input/key/KeyInputModifierNode;", "Landroidx/compose/ui/node/CompositionLocalConsumerModifierNode;", "Landroidx/compose/ui/modifier/ModifierLocalModifierNode;", "Landroidx/compose/ui/node/ObserverModifierNode;", "Landroidx/compose/ui/node/LayoutAwareModifierNode;", "Landroidx/compose/ui/focus/FocusPropertiesModifierNode;", "textFieldState", "Landroidx/compose/foundation/text/input/internal/TransformedTextFieldState;", "textLayoutState", "Landroidx/compose/foundation/text/input/internal/TextLayoutState;", "textFieldSelectionState", "Landroidx/compose/foundation/text/input/internal/selection/TextFieldSelectionState;", "filter", "Landroidx/compose/foundation/text/input/InputTransformation;", "enabled", "", "readOnly", "keyboardOptions", "Landroidx/compose/foundation/text/KeyboardOptions;", "keyboardActionHandler", "Landroidx/compose/foundation/text/input/KeyboardActionHandler;", "singleLine", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "isPassword", "stylusHandwritingTrigger", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "", "<init>", "(Landroidx/compose/foundation/text/input/internal/TransformedTextFieldState;Landroidx/compose/foundation/text/input/internal/TextLayoutState;Landroidx/compose/foundation/text/input/internal/selection/TextFieldSelectionState;Landroidx/compose/foundation/text/input/InputTransformation;ZZLandroidx/compose/foundation/text/KeyboardOptions;Landroidx/compose/foundation/text/input/KeyboardActionHandler;ZLandroidx/compose/foundation/interaction/MutableInteractionSource;ZLkotlinx/coroutines/flow/MutableSharedFlow;)V", "getTextFieldState", "()Landroidx/compose/foundation/text/input/internal/TransformedTextFieldState;", "setTextFieldState", "(Landroidx/compose/foundation/text/input/internal/TransformedTextFieldState;)V", "getTextLayoutState", "()Landroidx/compose/foundation/text/input/internal/TextLayoutState;", "setTextLayoutState", "(Landroidx/compose/foundation/text/input/internal/TextLayoutState;)V", "getTextFieldSelectionState", "()Landroidx/compose/foundation/text/input/internal/selection/TextFieldSelectionState;", "setTextFieldSelectionState", "(Landroidx/compose/foundation/text/input/internal/selection/TextFieldSelectionState;)V", "getFilter", "()Landroidx/compose/foundation/text/input/InputTransformation;", "setFilter", "(Landroidx/compose/foundation/text/input/InputTransformation;)V", "getEnabled", "()Z", "setEnabled", "(Z)V", "getReadOnly", "setReadOnly", "getKeyboardOptions", "()Landroidx/compose/foundation/text/KeyboardOptions;", "setKeyboardOptions", "(Landroidx/compose/foundation/text/KeyboardOptions;)V", "getKeyboardActionHandler", "()Landroidx/compose/foundation/text/input/KeyboardActionHandler;", "setKeyboardActionHandler", "(Landroidx/compose/foundation/text/input/KeyboardActionHandler;)V", "getSingleLine", "setSingleLine", "getInteractionSource", "()Landroidx/compose/foundation/interaction/MutableInteractionSource;", "setInteractionSource", "(Landroidx/compose/foundation/interaction/MutableInteractionSource;)V", "setPassword", "getStylusHandwritingTrigger", "()Lkotlinx/coroutines/flow/MutableSharedFlow;", "setStylusHandwritingTrigger", "(Lkotlinx/coroutines/flow/MutableSharedFlow;)V", "focusableNode", "Landroidx/compose/foundation/FocusableNode;", "pointerInputNode", "Landroidx/compose/ui/input/pointer/SuspendingPointerInputModifierNode;", "dragEnterEvent", "Landroidx/compose/foundation/text/input/internal/DragAndDropHoverInteraction$Enter;", "dragAndDropNode", "Landroidx/compose/ui/draganddrop/DragAndDropTargetModifierNode;", "windowInfo", "Landroidx/compose/ui/platform/WindowInfo;", "isFocused", "toolbarAndHandlesVisibilityObserverJob", "Lkotlinx/coroutines/Job;", "textFieldKeyEventHandler", "Landroidx/compose/foundation/text/input/internal/TextFieldKeyEventHandler;", "keyboardActionScope", "androidx/compose/foundation/text/input/internal/TextFieldDecoratorModifierNode$keyboardActionScope$1", "Landroidx/compose/foundation/text/input/internal/TextFieldDecoratorModifierNode$keyboardActionScope$1;", "clipboardKeyCommandsHandler", "Landroidx/compose/foundation/text/input/internal/ClipboardKeyCommandsHandler;", "Lkotlin/jvm/functions/Function1;", "inputSessionJob", "receiveContentConfigurationProvider", "Lkotlin/Function0;", "Landroidx/compose/foundation/content/internal/ReceiveContentConfiguration;", "<set-?>", "autofillHighlightOn", "getAutofillHighlightOn", "setAutofillHighlightOn", "autofillHighlightOn$delegate", "Landroidx/compose/runtime/MutableState;", "draw", "Landroidx/compose/ui/graphics/drawscope/ContentDrawScope;", "observeUntransformedTextChanges", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateNode", "shouldMergeDescendantSemantics", "getShouldMergeDescendantSemantics", "applySemantics", "Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;", "requestFocus", "onIsFocusedUpdated", "applyFocusProperties", "focusProperties", "Landroidx/compose/ui/focus/FocusProperties;", "onAttach", "onDetach", "onGloballyPositioned", "coordinates", "Landroidx/compose/ui/layout/LayoutCoordinates;", "onPointerEvent", "pointerEvent", "Landroidx/compose/ui/input/pointer/PointerEvent;", "pass", "Landroidx/compose/ui/input/pointer/PointerEventPass;", "bounds", "Landroidx/compose/ui/unit/IntSize;", "onPointerEvent-H0pRuoY", "(Landroidx/compose/ui/input/pointer/PointerEvent;Landroidx/compose/ui/input/pointer/PointerEventPass;J)V", "onCancelPointerInput", "onPreKeyEvent", NotificationCompat.CATEGORY_EVENT, "Landroidx/compose/ui/input/key/KeyEvent;", "onPreKeyEvent-ZmokQxo", "(Landroid/view/KeyEvent;)Z", "onKeyEvent", "onKeyEvent-ZmokQxo", "onObservedReadsChanged", "updateWindowFocus", "onPlaced", "onRemeasured", "size", "onRemeasured-ozmzZPI", "(J)V", "applyCurrentInputMode", "startInputSession", "fromTap", "disposeInputSession", "requireKeyboardController", "Landroidx/compose/ui/platform/SoftwareKeyboardController;", "emitDragExitEvent", "onImeActionPerformed", "imeAction", "Landroidx/compose/ui/text/input/ImeAction;", "onImeActionPerformed-KlQnJC8", "(I)Z", "defaultKeyboardActionWithResult", "defaultKeyboardActionWithResult-KlQnJC8", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class TextFieldDecoratorModifierNode extends DelegatingNode implements DrawModifierNode, PlatformTextInputModifierNode, SemanticsModifierNode, GlobalPositionAwareModifierNode, PointerInputModifierNode, KeyInputModifierNode, CompositionLocalConsumerModifierNode, ModifierLocalModifierNode, ObserverModifierNode, LayoutAwareModifierNode, FocusPropertiesModifierNode {
    public static final int $stable = 8;

    /* JADX INFO: renamed from: autofillHighlightOn$delegate, reason: from kotlin metadata */
    private final MutableState autofillHighlightOn;
    private final Function1<? super KeyCommand, ? extends Unit> clipboardKeyCommandsHandler;
    private final DragAndDropTargetModifierNode dragAndDropNode;
    private DragAndDropHoverInteraction.Enter dragEnterEvent;
    private boolean enabled;
    private InputTransformation filter;
    private final FocusableNode focusableNode;
    private Job inputSessionJob;
    private MutableInteractionSource interactionSource;
    private boolean isPassword;
    private KeyboardActionHandler keyboardActionHandler;
    private final TextFieldDecoratorModifierNode$keyboardActionScope$1 keyboardActionScope;
    private KeyboardOptions keyboardOptions;
    private final SuspendingPointerInputModifierNode pointerInputNode;
    private boolean readOnly;
    private final Function0<ReceiveContentConfiguration> receiveContentConfigurationProvider;
    private boolean singleLine;
    private MutableSharedFlow<Unit> stylusHandwritingTrigger;
    private final TextFieldKeyEventHandler textFieldKeyEventHandler;
    private TextFieldSelectionState textFieldSelectionState;
    private TransformedTextFieldState textFieldState;
    private TextLayoutState textLayoutState;
    private Job toolbarAndHandlesVisibilityObserverJob;
    private WindowInfo windowInfo;

    public TextFieldDecoratorModifierNode(TransformedTextFieldState textFieldState, TextLayoutState textLayoutState, TextFieldSelectionState textFieldSelectionState, InputTransformation filter, boolean enabled, boolean readOnly, KeyboardOptions keyboardOptions, KeyboardActionHandler keyboardActionHandler, boolean singleLine, MutableInteractionSource interactionSource, boolean isPassword, MutableSharedFlow<Unit> mutableSharedFlow) {
        this.textFieldState = textFieldState;
        this.textLayoutState = textLayoutState;
        this.textFieldSelectionState = textFieldSelectionState;
        this.filter = filter;
        this.enabled = enabled;
        this.readOnly = readOnly;
        this.keyboardOptions = keyboardOptions;
        this.keyboardActionHandler = keyboardActionHandler;
        this.singleLine = singleLine;
        this.interactionSource = interactionSource;
        this.isPassword = isPassword;
        this.stylusHandwritingTrigger = mutableSharedFlow;
        this.textFieldSelectionState.setRequestAutofillAction(new Function0() { // from class: androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode$$ExternalSyntheticLambda14
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return TextFieldDecoratorModifierNode._init_$lambda$0(this.f$0);
            }
        });
        this.focusableNode = new FocusableNode(this.interactionSource, 0, new Function1() { // from class: androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode$$ExternalSyntheticLambda16
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TextFieldDecoratorModifierNode.focusableNode$lambda$0(this.f$0, ((Boolean) obj).booleanValue());
            }
        }, 2, null);
        this.pointerInputNode = (SuspendingPointerInputModifierNode) delegate(SuspendingPointerInputFilterKt.SuspendingPointerInputModifierNode(new PointerInputEventHandler() { // from class: androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode$pointerInputNode$1

            /* JADX INFO: renamed from: androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode$pointerInputNode$1$1, reason: invalid class name */
            /* JADX INFO: compiled from: TextFieldDecoratorModifier.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
            @DebugMetadata(c = "androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode$pointerInputNode$1$1", f = "TextFieldDecoratorModifier.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
            static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ PointerInputScope $this_SuspendingPointerInputModifierNode;
                private /* synthetic */ Object L$0;
                int label;
                final /* synthetic */ TextFieldDecoratorModifierNode this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                AnonymousClass1(TextFieldDecoratorModifierNode textFieldDecoratorModifierNode, PointerInputScope pointerInputScope, Continuation<? super AnonymousClass1> continuation) {
                    super(2, continuation);
                    this.this$0 = textFieldDecoratorModifierNode;
                    this.$this_SuspendingPointerInputModifierNode = pointerInputScope;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, this.$this_SuspendingPointerInputModifierNode, continuation);
                    anonymousClass1.L$0 = obj;
                    return anonymousClass1;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    switch (this.label) {
                        case 0:
                            ResultKt.throwOnFailure(obj);
                            CoroutineScope $this$coroutineScope = (CoroutineScope) this.L$0;
                            final TextFieldSelectionState $this$invokeSuspend_u24lambda_u240 = this.this$0.getTextFieldSelectionState();
                            final TextFieldDecoratorModifierNode textFieldDecoratorModifierNode = this.this$0;
                            PointerInputScope pointerInputScope = this.$this_SuspendingPointerInputModifierNode;
                            Function0 requestFocus = 
                            /*  JADX ERROR: Method code generation error
                                jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0026: CONSTRUCTOR (r6v0 'requestFocus' kotlin.jvm.functions.Function0) = 
                                  (r0v3 '$this$invokeSuspend_u24lambda_u240' androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState A[D('$this$invokeSuspend_u24lambda_u240' androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState), DONT_INLINE])
                                  (r7v0 'textFieldDecoratorModifierNode' androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode A[DONT_INLINE])
                                 A[DECLARE_VAR, MD:(androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState, androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode):void (m)] (LINE:251) call: androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode$pointerInputNode$1$1$$ExternalSyntheticLambda0.<init>(androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState, androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode):void type: CONSTRUCTOR in method: androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode$pointerInputNode$1.1.invokeSuspend(java.lang.Object):java.lang.Object, file: classes.dex
                                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:310)
                                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:273)
                                	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:94)
                                	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:83)
                                	at jadx.core.codegen.RegionGen.makeSwitch(RegionGen.java:267)
                                	at jadx.core.dex.regions.SwitchRegion.generate(SwitchRegion.java:88)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:305)
                                	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:284)
                                	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:412)
                                	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:337)
                                	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:303)
                                	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(Unknown Source)
                                	at java.base/java.util.ArrayList.forEach(Unknown Source)
                                	at java.base/java.util.stream.SortedOps$RefSortingSink.end(Unknown Source)
                                	at java.base/java.util.stream.Sink$ChainedReference.end(Unknown Source)
                                	at java.base/java.util.stream.ReferencePipeline$7$1FlatMap.end(Unknown Source)
                                	at java.base/java.util.stream.AbstractPipeline.copyInto(Unknown Source)
                                	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(Unknown Source)
                                	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(Unknown Source)
                                	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(Unknown Source)
                                	at java.base/java.util.stream.AbstractPipeline.evaluate(Unknown Source)
                                	at java.base/java.util.stream.ReferencePipeline.forEach(Unknown Source)
                                	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:299)
                                	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:288)
                                	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:272)
                                	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:159)
                                	at jadx.core.codegen.ClassGen.addInnerClass(ClassGen.java:312)
                                	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:301)
                                	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(Unknown Source)
                                	at java.base/java.util.ArrayList.forEach(Unknown Source)
                                	at java.base/java.util.stream.SortedOps$RefSortingSink.end(Unknown Source)
                                	at java.base/java.util.stream.Sink$ChainedReference.end(Unknown Source)
                                	at java.base/java.util.stream.ReferencePipeline$7$1FlatMap.end(Unknown Source)
                                	at java.base/java.util.stream.AbstractPipeline.copyInto(Unknown Source)
                                	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(Unknown Source)
                                	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(Unknown Source)
                                	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(Unknown Source)
                                	at java.base/java.util.stream.AbstractPipeline.evaluate(Unknown Source)
                                	at java.base/java.util.stream.ReferencePipeline.forEach(Unknown Source)
                                	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:299)
                                	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:288)
                                	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:845)
                                	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:730)
                                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:418)
                                	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:145)
                                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:121)
                                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:108)
                                	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:1143)
                                	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:910)
                                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:422)
                                	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:145)
                                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:121)
                                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:108)
                                	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:1143)
                                	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:910)
                                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:422)
                                	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:145)
                                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:121)
                                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:108)
                                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:345)
                                	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:145)
                                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:121)
                                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:108)
                                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:487)
                                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:303)
                                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:273)
                                	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:94)
                                	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:305)
                                	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:284)
                                	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:412)
                                	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:337)
                                	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:303)
                                	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(Unknown Source)
                                	at java.base/java.util.ArrayList.forEach(Unknown Source)
                                	at java.base/java.util.stream.SortedOps$RefSortingSink.end(Unknown Source)
                                	at java.base/java.util.stream.Sink$ChainedReference.end(Unknown Source)
                                	at java.base/java.util.stream.ReferencePipeline$7$1FlatMap.end(Unknown Source)
                                	at java.base/java.util.stream.AbstractPipeline.copyInto(Unknown Source)
                                	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(Unknown Source)
                                	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(Unknown Source)
                                	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(Unknown Source)
                                	at java.base/java.util.stream.AbstractPipeline.evaluate(Unknown Source)
                                	at java.base/java.util.stream.ReferencePipeline.forEach(Unknown Source)
                                	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:299)
                                	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:288)
                                	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:272)
                                	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:159)
                                	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:103)
                                	at jadx.core.codegen.CodeGen.wrapCodeGen(CodeGen.java:45)
                                	at jadx.core.codegen.CodeGen.generateJavaCode(CodeGen.java:34)
                                	at jadx.core.codegen.CodeGen.generate(CodeGen.java:22)
                                	at jadx.core.ProcessClass.process(ProcessClass.java:88)
                                	at jadx.core.ProcessClass.generateCode(ProcessClass.java:126)
                                	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:405)
                                	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:393)
                                	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:311)
                                Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Expected class to be processed at this point, class: androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode$pointerInputNode$1$1$$ExternalSyntheticLambda0, state: NOT_LOADED
                                	at jadx.core.dex.nodes.ClassNode.ensureProcessed(ClassNode.java:306)
                                	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:807)
                                	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:730)
                                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:418)
                                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:303)
                                	... 106 more
                                */
                            /*
                                this = this;
                                kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                                int r0 = r13.label
                                switch(r0) {
                                    case 0: goto L11;
                                    default: goto L8;
                                }
                            L8:
                                java.lang.IllegalStateException r14 = new java.lang.IllegalStateException
                                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                                r14.<init>(r0)
                                throw r14
                            L11:
                                kotlin.ResultKt.throwOnFailure(r14)
                                java.lang.Object r0 = r13.L$0
                                r1 = r0
                                kotlinx.coroutines.CoroutineScope r1 = (kotlinx.coroutines.CoroutineScope) r1
                                androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode r0 = r13.this$0
                                androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState r0 = r0.getTextFieldSelectionState()
                                androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode r7 = r13.this$0
                                androidx.compose.ui.input.pointer.PointerInputScope r8 = r13.$this_SuspendingPointerInputModifierNode
                                r9 = 0
                                androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode$pointerInputNode$1$1$$ExternalSyntheticLambda0 r6 = new androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode$pointerInputNode$1$1$$ExternalSyntheticLambda0
                                r6.<init>(r0, r7)
                                r10 = r6
                                kotlinx.coroutines.CoroutineStart r3 = kotlinx.coroutines.CoroutineStart.UNDISPATCHED
                                androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode$pointerInputNode$1$1$1$1 r2 = new androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode$pointerInputNode$1$1$1$1
                                r11 = 0
                                r2.<init>(r0, r8, r11)
                                r4 = r2
                                kotlin.jvm.functions.Function2 r4 = (kotlin.jvm.functions.Function2) r4
                                r5 = 1
                                r6 = 0
                                r2 = 0
                                kotlinx.coroutines.BuildersKt.launch$default(r1, r2, r3, r4, r5, r6)
                                kotlinx.coroutines.CoroutineStart r12 = kotlinx.coroutines.CoroutineStart.UNDISPATCHED
                                androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode$pointerInputNode$1$1$1$2 r2 = new androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode$pointerInputNode$1$1$1$2
                                r3 = r7
                                r7 = 0
                                r4 = r0
                                r5 = r8
                                r6 = r10
                                r2.<init>(r3, r4, r5, r6, r7)
                                r7 = r5
                                r8 = r6
                                r4 = r2
                                kotlin.jvm.functions.Function2 r4 = (kotlin.jvm.functions.Function2) r4
                                r5 = 1
                                r6 = 0
                                r2 = 0
                                r3 = r12
                                kotlinx.coroutines.BuildersKt.launch$default(r1, r2, r3, r4, r5, r6)
                                kotlinx.coroutines.CoroutineStart r3 = kotlinx.coroutines.CoroutineStart.UNDISPATCHED
                                androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode$pointerInputNode$1$1$1$3 r2 = new androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode$pointerInputNode$1$1$1$3
                                r2.<init>(r0, r7, r8, r11)
                                r4 = r2
                                kotlin.jvm.functions.Function2 r4 = (kotlin.jvm.functions.Function2) r4
                                r2 = 0
                                kotlinx.coroutines.BuildersKt.launch$default(r1, r2, r3, r4, r5, r6)
                                kotlin.Unit r0 = kotlin.Unit.INSTANCE
                                return r0
                            */
                            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode$pointerInputNode$1.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
                        }

                        /* JADX INFO: Access modifiers changed from: private */
                        public static final Unit invokeSuspend$lambda$0$0(TextFieldSelectionState $this_with, TextFieldDecoratorModifierNode this$0) {
                            if (!$this_with.getIsFocused()) {
                                this$0.requestFocus();
                            }
                            return Unit.INSTANCE;
                        }
                    }

                    @Override // androidx.compose.ui.input.pointer.PointerInputEventHandler
                    public final Object invoke(PointerInputScope $this$SuspendingPointerInputModifierNode, Continuation<? super Unit> continuation) {
                        Object objCoroutineScope = CoroutineScopeKt.coroutineScope(new AnonymousClass1(this.this$0, $this$SuspendingPointerInputModifierNode, null), continuation);
                        return objCoroutineScope == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCoroutineScope : Unit.INSTANCE;
                    }
                }));
                this.dragAndDropNode = (DragAndDropTargetModifierNode) delegate(TextFieldDragAndDropNode_androidKt.textFieldDragAndDropNode$default(new Function0() { // from class: androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode$$ExternalSyntheticLambda17
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return TextFieldDecoratorModifierNode.dragAndDropNode$lambda$0(this.f$0);
                    }
                }, new Function2() { // from class: androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode$$ExternalSyntheticLambda18
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return Boolean.valueOf(TextFieldDecoratorModifierNode.dragAndDropNode$lambda$1(this.f$0, (ClipEntry) obj, (ClipMetadata) obj2));
                    }
                }, new Function1() { // from class: androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode$$ExternalSyntheticLambda19
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return TextFieldDecoratorModifierNode.dragAndDropNode$lambda$2(this.f$0, (DragAndDropEvent) obj);
                    }
                }, null, new Function1() { // from class: androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode$$ExternalSyntheticLambda20
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return TextFieldDecoratorModifierNode.dragAndDropNode$lambda$3(this.f$0, (DragAndDropEvent) obj);
                    }
                }, new Function1() { // from class: androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode$$ExternalSyntheticLambda21
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return TextFieldDecoratorModifierNode.dragAndDropNode$lambda$4(this.f$0, (Offset) obj);
                    }
                }, null, new Function1() { // from class: androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode$$ExternalSyntheticLambda22
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return TextFieldDecoratorModifierNode.dragAndDropNode$lambda$5(this.f$0, (DragAndDropEvent) obj);
                    }
                }, new Function1() { // from class: androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode$$ExternalSyntheticLambda23
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return TextFieldDecoratorModifierNode.dragAndDropNode$lambda$6(this.f$0, (DragAndDropEvent) obj);
                    }
                }, 72, null));
                this.textFieldKeyEventHandler = TextFieldKeyEventHandler_androidKt.createTextFieldKeyEventHandler();
                this.keyboardActionScope = new TextFieldDecoratorModifierNode$keyboardActionScope$1(this);
                this.clipboardKeyCommandsHandler = ClipboardKeyCommandsHandler.m1753constructorimpl(new Function1() { // from class: androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode$$ExternalSyntheticLambda24
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return TextFieldDecoratorModifierNode.clipboardKeyCommandsHandler$lambda$0(this.f$0, (KeyCommand) obj);
                    }
                });
                this.receiveContentConfigurationProvider = new Function0() { // from class: androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode$$ExternalSyntheticLambda15
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return ReceiveContentConfigurationKt.getReceiveContentConfiguration(this.f$0);
                    }
                };
                this.autofillHighlightOn = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
            }

            public final TransformedTextFieldState getTextFieldState() {
                return this.textFieldState;
            }

            public final void setTextFieldState(TransformedTextFieldState transformedTextFieldState) {
                this.textFieldState = transformedTextFieldState;
            }

            public final TextLayoutState getTextLayoutState() {
                return this.textLayoutState;
            }

            public final void setTextLayoutState(TextLayoutState textLayoutState) {
                this.textLayoutState = textLayoutState;
            }

            public final TextFieldSelectionState getTextFieldSelectionState() {
                return this.textFieldSelectionState;
            }

            public final void setTextFieldSelectionState(TextFieldSelectionState textFieldSelectionState) {
                this.textFieldSelectionState = textFieldSelectionState;
            }

            public final InputTransformation getFilter() {
                return this.filter;
            }

            public final void setFilter(InputTransformation inputTransformation) {
                this.filter = inputTransformation;
            }

            public final boolean getEnabled() {
                return this.enabled;
            }

            public final void setEnabled(boolean z) {
                this.enabled = z;
            }

            public final boolean getReadOnly() {
                return this.readOnly;
            }

            public final void setReadOnly(boolean z) {
                this.readOnly = z;
            }

            public final KeyboardOptions getKeyboardOptions() {
                return this.keyboardOptions;
            }

            public final void setKeyboardOptions(KeyboardOptions keyboardOptions) {
                this.keyboardOptions = keyboardOptions;
            }

            public final KeyboardActionHandler getKeyboardActionHandler() {
                return this.keyboardActionHandler;
            }

            public final void setKeyboardActionHandler(KeyboardActionHandler keyboardActionHandler) {
                this.keyboardActionHandler = keyboardActionHandler;
            }

            public final boolean getSingleLine() {
                return this.singleLine;
            }

            public final void setSingleLine(boolean z) {
                this.singleLine = z;
            }

            public final MutableInteractionSource getInteractionSource() {
                return this.interactionSource;
            }

            public final void setInteractionSource(MutableInteractionSource mutableInteractionSource) {
                this.interactionSource = mutableInteractionSource;
            }

            /* JADX INFO: renamed from: isPassword, reason: from getter */
            public final boolean getIsPassword() {
                return this.isPassword;
            }

            public final void setPassword(boolean z) {
                this.isPassword = z;
            }

            public final MutableSharedFlow<Unit> getStylusHandwritingTrigger() {
                return this.stylusHandwritingTrigger;
            }

            public final void setStylusHandwritingTrigger(MutableSharedFlow<Unit> mutableSharedFlow) {
                this.stylusHandwritingTrigger = mutableSharedFlow;
            }

            static final Unit _init_$lambda$0(TextFieldDecoratorModifierNode this$0) {
                DelegatableNodeKt.requestAutofill(this$0);
                return Unit.INSTANCE;
            }

            static final Unit focusableNode$lambda$0(TextFieldDecoratorModifierNode this$0, boolean isFocused) {
                boolean editable = this$0.enabled && !this$0.readOnly;
                if (isFocused) {
                    this$0.applyCurrentInputMode();
                    if (editable) {
                        this$0.startInputSession(false);
                    }
                } else {
                    this$0.disposeInputSession();
                    TransformedTextFieldState $this$iv = this$0.textFieldState;
                    TextFieldState $this$iv$iv = $this$iv.textFieldState;
                    InputTransformation inputTransformation$iv$iv = $this$iv.inputTransformation;
                    TextFieldEditUndoBehavior undoBehavior$iv$iv = TextFieldEditUndoBehavior.MergeIfPossible;
                    $this$iv$iv.getMainBuffer().getChangeTracker$foundation().clearChanges();
                    TextFieldBuffer $this$editUntransformedTextAsUser_u24lambda_u240$iv = $this$iv$iv.getMainBuffer();
                    $this$editUntransformedTextAsUser_u24lambda_u240$iv.commitComposition$foundation();
                    $this$iv.updateWedgeAffinity($this$editUntransformedTextAsUser_u24lambda_u240$iv);
                    $this$iv$iv.commitEditAsUser(inputTransformation$iv$iv, true, undoBehavior$iv$iv);
                    $this$iv$iv.setUserCommit(true);
                    this$0.textFieldState.collapseSelectionToMax();
                }
                this$0.updateWindowFocus();
                return Unit.INSTANCE;
            }

            static final Set dragAndDropNode$lambda$0(TextFieldDecoratorModifierNode this$0) {
                ReceiveContentConfiguration receiveContentConfiguration = ReceiveContentConfigurationKt.getReceiveContentConfiguration(this$0);
                return receiveContentConfiguration != null ? TextFieldDecoratorModifierKt.MediaTypesAll : TextFieldDecoratorModifierKt.MediaTypesText;
            }

            static final Unit dragAndDropNode$lambda$2(TextFieldDecoratorModifierNode this$0, DragAndDropEvent it) {
                if (ReceiveContentConfigurationKt.getReceiveContentConfiguration(this$0) != null) {
                    DragAndDropRequestPermission_androidKt.dragAndDropRequestPermission(this$0, it);
                }
                return Unit.INSTANCE;
            }

            static final Unit dragAndDropNode$lambda$3(TextFieldDecoratorModifierNode this$0, DragAndDropEvent it) {
                ReceiveContentListener receiveContentListener;
                DragAndDropHoverInteraction.Enter it2 = new DragAndDropHoverInteraction.Enter();
                this$0.interactionSource.tryEmit(it2);
                this$0.dragEnterEvent = it2;
                ReceiveContentConfiguration receiveContentConfiguration = ReceiveContentConfigurationKt.getReceiveContentConfiguration(this$0);
                if (receiveContentConfiguration != null && (receiveContentListener = receiveContentConfiguration.getReceiveContentListener()) != null) {
                    receiveContentListener.onDragEnter();
                }
                return Unit.INSTANCE;
            }

            static final Unit dragAndDropNode$lambda$4(TextFieldDecoratorModifierNode this$0, Offset position) {
                long positionOnTextField = TextLayoutStateKt.m1886fromWindowToDecorationUv8p0NA(this$0.textLayoutState, position.m5078unboximpl());
                int cursorPosition = TextLayoutState.m1876getOffsetForPosition3MmeM6k$default(this$0.textLayoutState, positionOnTextField, false, 2, null);
                if (cursorPosition >= 0) {
                    this$0.textFieldState.m1897selectCharsIn5zctL8(TextRangeKt.TextRange(cursorPosition));
                }
                this$0.textFieldSelectionState.m1941updateHandleDraggingUv8p0NA(Handle.Cursor, positionOnTextField);
                return Unit.INSTANCE;
            }

            static final boolean dragAndDropNode$lambda$1(TextFieldDecoratorModifierNode this$0, ClipEntry clipEntry, ClipMetadata clipMetadata) {
                ClipEntry clipEntry2;
                this$0.emitDragExitEvent();
                this$0.textFieldSelectionState.clearHandleDragging();
                String plainText = TransferableContent_androidKt.readPlainText(clipEntry);
                ReceiveContentConfiguration receiveContentConfiguration = ReceiveContentConfigurationKt.getReceiveContentConfiguration(this$0);
                if (receiveContentConfiguration != null) {
                    TransferableContent transferableContent = new TransferableContent(clipEntry, clipMetadata, TransferableContent.Source.INSTANCE.m390getDragAndDropkB6V9T0(), null, 8, null);
                    TransferableContent remaining = receiveContentConfiguration.getReceiveContentListener().onReceive(transferableContent);
                    plainText = (remaining == null || (clipEntry2 = remaining.getClipEntry()) == null) ? null : TransferableContent_androidKt.readPlainText(clipEntry2);
                }
                if (plainText != null) {
                    String p0 = plainText;
                    TransformedTextFieldState.replaceSelectedText$default(this$0.textFieldState, p0, false, null, false, 14, null);
                    return true;
                }
                return true;
            }

            static final Unit dragAndDropNode$lambda$5(TextFieldDecoratorModifierNode this$0, DragAndDropEvent it) {
                ReceiveContentListener receiveContentListener;
                this$0.emitDragExitEvent();
                this$0.textFieldSelectionState.clearHandleDragging();
                ReceiveContentConfiguration receiveContentConfiguration = ReceiveContentConfigurationKt.getReceiveContentConfiguration(this$0);
                if (receiveContentConfiguration != null && (receiveContentListener = receiveContentConfiguration.getReceiveContentListener()) != null) {
                    receiveContentListener.onDragExit();
                }
                return Unit.INSTANCE;
            }

            static final Unit dragAndDropNode$lambda$6(TextFieldDecoratorModifierNode this$0, DragAndDropEvent it) {
                this$0.emitDragExitEvent();
                return Unit.INSTANCE;
            }

            private final boolean isFocused() {
                if (!this.focusableNode.getFocusState().isFocused()) {
                    return false;
                }
                WindowInfo windowInfo = this.windowInfo;
                return windowInfo != null && windowInfo.isWindowFocused();
            }

            static final Unit clipboardKeyCommandsHandler$lambda$0(TextFieldDecoratorModifierNode this$0, KeyCommand keyCommand) {
                BuildersKt__Builders_commonKt.launch$default(this$0.getCoroutineScope(), null, CoroutineStart.UNDISPATCHED, new TextFieldDecoratorModifierNode$clipboardKeyCommandsHandler$1$1(keyCommand, this$0, null), 1, null);
                return Unit.INSTANCE;
            }

            private final boolean getAutofillHighlightOn() {
                State $this$getValue$iv = this.autofillHighlightOn;
                return ((Boolean) $this$getValue$iv.getValue()).booleanValue();
            }

            /* JADX INFO: Access modifiers changed from: private */
            public final void setAutofillHighlightOn(boolean z) {
                MutableState $this$setValue$iv = this.autofillHighlightOn;
                $this$setValue$iv.setValue(Boolean.valueOf(z));
            }

            @Override // androidx.compose.ui.node.DrawModifierNode
            public void draw(ContentDrawScope $this$draw) {
                $this$draw.drawContent();
                if (getAutofillHighlightOn()) {
                    DrawScope.m5880drawRectAsUm42w$default($this$draw, AutofillHighlightKt.m1475resolveAutofillHighlightWkMShQ((Brush) CompositionLocalConsumerModifierNodeKt.currentValueOf(this, AutofillHighlightKt.getLocalAutofillHighlightBrush()), ((Color) CompositionLocalConsumerModifierNodeKt.currentValueOf(this, AutofillHighlightKt.getLocalAutofillHighlightColor())).m5323unboximpl(), AutofillHighlight_androidKt.autofillHighlightColor()), 0L, 0L, 0.0f, null, null, 0, 126, null);
                }
            }

            /* JADX INFO: Access modifiers changed from: private */
            public final Object observeUntransformedTextChanges(Continuation<? super Unit> continuation) {
                Object objCollect = FlowKt.take(FlowKt.drop(SnapshotStateKt.snapshotFlow(new Function0() { // from class: androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return this.f$0.textFieldState.getUntransformedText().toString();
                    }
                }), 1), 1).collect(new FlowCollector() { // from class: androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode.observeUntransformedTextChanges.3
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public /* bridge */ /* synthetic */ Object emit(Object value, Continuation $completion) {
                        return emit((String) value, (Continuation<? super Unit>) $completion);
                    }

                    public final Object emit(String it, Continuation<? super Unit> continuation2) {
                        TextFieldDecoratorModifierNode.this.setAutofillHighlightOn(false);
                        return Unit.INSTANCE;
                    }
                }, continuation);
                return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
            }

            public final void updateNode(TransformedTextFieldState textFieldState, TextLayoutState textLayoutState, TextFieldSelectionState textFieldSelectionState, InputTransformation filter, boolean enabled, boolean readOnly, KeyboardOptions keyboardOptions, KeyboardActionHandler keyboardActionHandler, boolean singleLine, MutableInteractionSource interactionSource, boolean isPassword, MutableSharedFlow<Unit> stylusHandwritingTrigger) {
                boolean previousEditable = this.enabled && !this.readOnly;
                boolean previousEnabled = this.enabled;
                TransformedTextFieldState previousTextFieldState = this.textFieldState;
                KeyboardOptions previousKeyboardOptions = this.keyboardOptions;
                TextFieldSelectionState previousTextFieldSelectionState = this.textFieldSelectionState;
                MutableInteractionSource previousInteractionSource = this.interactionSource;
                boolean previousIsPassword = this.isPassword;
                MutableSharedFlow<Unit> mutableSharedFlow = this.stylusHandwritingTrigger;
                boolean editable = enabled && !readOnly;
                this.textFieldState = textFieldState;
                this.textLayoutState = textLayoutState;
                this.textFieldSelectionState = textFieldSelectionState;
                this.filter = filter;
                this.enabled = enabled;
                this.readOnly = readOnly;
                this.keyboardOptions = keyboardOptions;
                this.keyboardActionHandler = keyboardActionHandler;
                this.singleLine = singleLine;
                this.interactionSource = interactionSource;
                this.isPassword = isPassword;
                this.stylusHandwritingTrigger = stylusHandwritingTrigger;
                if (editable != previousEditable || !Intrinsics.areEqual(textFieldState, previousTextFieldState) || !Intrinsics.areEqual(keyboardOptions, previousKeyboardOptions) || !Intrinsics.areEqual(stylusHandwritingTrigger, mutableSharedFlow)) {
                    if (editable && (isFocused() || this.inputSessionJob != null)) {
                        startInputSession(false);
                    } else if (!editable) {
                        disposeInputSession();
                    }
                }
                if (enabled != previousEnabled || editable != previousEditable || !ImeAction.m7741equalsimpl0(keyboardOptions.m1600getImeActionOrDefaulteUduSuo$foundation(), previousKeyboardOptions.m1600getImeActionOrDefaulteUduSuo$foundation()) || isPassword != previousIsPassword) {
                    SemanticsModifierNodeKt.invalidateSemantics(this);
                }
                if (!Intrinsics.areEqual(textFieldSelectionState, previousTextFieldSelectionState)) {
                    this.pointerInputNode.resetPointerInputHandler();
                    if (getIsAttached()) {
                        textFieldSelectionState.setReceiveContentConfiguration(this.receiveContentConfigurationProvider);
                        if (isFocused() && this.toolbarAndHandlesVisibilityObserverJob != null) {
                            Job job = this.toolbarAndHandlesVisibilityObserverJob;
                            if (job != null) {
                                Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
                            }
                            this.toolbarAndHandlesVisibilityObserverJob = BuildersKt__Builders_commonKt.launch$default(getCoroutineScope(), null, null, new C02261(textFieldSelectionState, null), 3, null);
                        }
                    }
                    textFieldSelectionState.setRequestAutofillAction(new Function0() { // from class: androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode$$ExternalSyntheticLambda13
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return TextFieldDecoratorModifierNode.updateNode$lambda$0(this.f$0);
                        }
                    });
                }
                if (!Intrinsics.areEqual(interactionSource, previousInteractionSource)) {
                    this.pointerInputNode.resetPointerInputHandler();
                    if (this.focusableNode.getIsAttached()) {
                        this.focusableNode.update(interactionSource);
                    }
                }
                if (enabled != previousEnabled) {
                    FocusableNode focusableNode = this.focusableNode;
                    if (enabled) {
                        delegate(focusableNode);
                        this.focusableNode.update(interactionSource);
                    } else {
                        undelegate(focusableNode);
                    }
                }
            }

            /* JADX INFO: renamed from: androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode$updateNode$1, reason: invalid class name and case insensitive filesystem */
            /* JADX INFO: compiled from: TextFieldDecoratorModifier.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
            @DebugMetadata(c = "androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode$updateNode$1", f = "TextFieldDecoratorModifier.kt", i = {}, l = {514}, m = "invokeSuspend", n = {}, s = {}, v = 1)
            static final class C02261 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ TextFieldSelectionState $textFieldSelectionState;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C02261(TextFieldSelectionState textFieldSelectionState, Continuation<? super C02261> continuation) {
                    super(2, continuation);
                    this.$textFieldSelectionState = textFieldSelectionState;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    return new C02261(this.$textFieldSelectionState, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C02261) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object $result) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    switch (this.label) {
                        case 0:
                            ResultKt.throwOnFailure($result);
                            this.label = 1;
                            if (this.$textFieldSelectionState.startToolbarAndHandlesVisibilityObserver(this) == coroutine_suspended) {
                                return coroutine_suspended;
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

            static final Unit updateNode$lambda$0(TextFieldDecoratorModifierNode this$0) {
                DelegatableNodeKt.requestAutofill(this$0);
                return Unit.INSTANCE;
            }

            @Override // androidx.compose.ui.node.SemanticsModifierNode
            public boolean getShouldMergeDescendantSemantics() {
                return true;
            }

            @Override // androidx.compose.ui.node.SemanticsModifierNode
            public void applySemantics(SemanticsPropertyReceiver $this$applySemantics) {
                TextFieldCharSequence text = this.textFieldState.getOutputText();
                long selection = text.getSelection();
                SemanticsPropertiesKt.setInputText($this$applySemantics, new AnnotatedString(this.textFieldState.getUntransformedText().toString(), null, 2, null));
                SemanticsPropertiesKt.setEditableText($this$applySemantics, new AnnotatedString(text.toString(), null, 2, null));
                SemanticsPropertiesKt.m7364setTextSelectionRangeFDrldGo($this$applySemantics, selection);
                SemanticsPropertiesKt.m7363setTextCompositionRangepsREZIo($this$applySemantics, this.textFieldState.m1890getUntransformedCompositionMzsxiRA());
                SemanticsPropertiesKt.setInputTextSuggestionState($this$applySemantics, new InputTextSuggestionState(this.textFieldState.getUserCommit()));
                if (!this.enabled) {
                    SemanticsPropertiesKt.disabled($this$applySemantics);
                }
                if (this.isPassword) {
                    SemanticsPropertiesKt.password($this$applySemantics);
                }
                final boolean editable = this.enabled && !this.readOnly;
                SemanticsPropertiesKt.setEditable($this$applySemantics, editable);
                SemanticsPropertiesKt.setContentDataType($this$applySemantics, ContentDataType.INSTANCE.getText());
                FillableData it = FillableData_androidKt.createFromText(FillableData.INSTANCE, text);
                if (it != null) {
                    SemanticsPropertiesKt.setFillableData($this$applySemantics, it);
                }
                SemanticsPropertiesKt.onFillData$default($this$applySemantics, null, new Function1() { // from class: androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return Boolean.valueOf(TextFieldDecoratorModifierNode.applySemantics$lambda$1(editable, this, (FillableData) obj));
                    }
                }, 1, null);
                int keyboardType = this.keyboardOptions.getKeyboardType();
                if (KeyboardType.m7794equalsimpl0(keyboardType, KeyboardType.INSTANCE.m7810getEmailPjHm6EE())) {
                    SemanticsPropertiesKt.setContentType($this$applySemantics, ContentType.INSTANCE.getEmailAddress());
                } else if (KeyboardType.m7794equalsimpl0(keyboardType, KeyboardType.INSTANCE.m7813getPasswordPjHm6EE()) || KeyboardType.m7794equalsimpl0(keyboardType, KeyboardType.INSTANCE.m7812getNumberPasswordPjHm6EE())) {
                    SemanticsPropertiesKt.setContentType($this$applySemantics, ContentType.INSTANCE.getPassword());
                } else if (KeyboardType.m7794equalsimpl0(keyboardType, KeyboardType.INSTANCE.m7814getPhonePjHm6EE())) {
                    SemanticsPropertiesKt.setContentType($this$applySemantics, ContentType.INSTANCE.getPhoneNumber());
                }
                SemanticsPropertiesKt.getTextLayoutResult$default($this$applySemantics, null, new Function1() { // from class: androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode$$ExternalSyntheticLambda3
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return Boolean.valueOf(TextFieldDecoratorModifierNode.applySemantics$lambda$2(this.f$0, (List) obj));
                    }
                }, 1, null);
                if (editable) {
                    SemanticsPropertiesKt.setText$default($this$applySemantics, null, new Function1() { // from class: androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode$$ExternalSyntheticLambda4
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return Boolean.valueOf(TextFieldDecoratorModifierNode.applySemantics$lambda$3(editable, this, (AnnotatedString) obj));
                        }
                    }, 1, null);
                    SemanticsPropertiesKt.insertTextAtCursor$default($this$applySemantics, null, new Function1() { // from class: androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode$$ExternalSyntheticLambda5
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return Boolean.valueOf(TextFieldDecoratorModifierNode.applySemantics$lambda$4(editable, this, (AnnotatedString) obj));
                        }
                    }, 1, null);
                }
                SemanticsPropertiesKt.setSelection$default($this$applySemantics, null, new Function3() { // from class: androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode$$ExternalSyntheticLambda6
                    @Override // kotlin.jvm.functions.Function3
                    public final Object invoke(Object obj, Object obj2, Object obj3) {
                        return Boolean.valueOf(TextFieldDecoratorModifierNode.applySemantics$lambda$5(this.f$0, ((Integer) obj).intValue(), ((Integer) obj2).intValue(), ((Boolean) obj3).booleanValue()));
                    }
                }, 1, null);
                final int effectiveImeAction = this.keyboardOptions.m1600getImeActionOrDefaulteUduSuo$foundation();
                SemanticsPropertiesKt.m7359onImeAction9UiTYpY$default($this$applySemantics, effectiveImeAction, null, new Function0() { // from class: androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode$$ExternalSyntheticLambda7
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return Boolean.valueOf(TextFieldDecoratorModifierNode.applySemantics$lambda$6(this.f$0, effectiveImeAction));
                    }
                }, 2, null);
                SemanticsPropertiesKt.onClick$default($this$applySemantics, null, new Function0() { // from class: androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode$$ExternalSyntheticLambda8
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return Boolean.valueOf(TextFieldDecoratorModifierNode.applySemantics$lambda$7(this.f$0));
                    }
                }, 1, null);
                SemanticsPropertiesKt.onLongClick$default($this$applySemantics, null, new Function0() { // from class: androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode$$ExternalSyntheticLambda9
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return Boolean.valueOf(TextFieldDecoratorModifierNode.applySemantics$lambda$8(this.f$0));
                    }
                }, 1, null);
                if (!TextRange.m7567getCollapsedimpl(selection) && !this.isPassword) {
                    SemanticsPropertiesKt.copyText$default($this$applySemantics, null, new Function0() { // from class: androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode$$ExternalSyntheticLambda10
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return Boolean.valueOf(TextFieldDecoratorModifierNode.applySemantics$lambda$9(this.f$0));
                        }
                    }, 1, null);
                    if (this.enabled && !this.readOnly) {
                        SemanticsPropertiesKt.cutText$default($this$applySemantics, null, new Function0() { // from class: androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode$$ExternalSyntheticLambda11
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return Boolean.valueOf(TextFieldDecoratorModifierNode.applySemantics$lambda$10(this.f$0));
                            }
                        }, 1, null);
                    }
                }
                if (editable) {
                    SemanticsPropertiesKt.pasteText$default($this$applySemantics, null, new Function0() { // from class: androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode$$ExternalSyntheticLambda2
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return Boolean.valueOf(TextFieldDecoratorModifierNode.applySemantics$lambda$11(this.f$0));
                        }
                    }, 1, null);
                }
                InputTransformation it2 = this.filter;
                if (it2 != null) {
                    it2.applySemantics($this$applySemantics);
                }
                if (this.enabled) {
                    FocusableNode $this$applySemantics_u24lambda_u2413 = this.focusableNode;
                    $this$applySemantics_u24lambda_u2413.applySemantics($this$applySemantics);
                }
            }

            static final boolean applySemantics$lambda$1(boolean $editable, TextFieldDecoratorModifierNode this$0, FillableData dataValue) {
                if (!$editable) {
                    return false;
                }
                CharSequence it = dataValue.getTextValue();
                if (it != null) {
                    this$0.textFieldState.replaceAll(it);
                }
                this$0.setAutofillHighlightOn(true);
                BuildersKt__Builders_commonKt.launch$default(this$0.getCoroutineScope(), null, null, new TextFieldDecoratorModifierNode$applySemantics$2$2(this$0, null), 3, null);
                return true;
            }

            static final boolean applySemantics$lambda$2(TextFieldDecoratorModifierNode this$0, List it) {
                TextLayoutResult result = this$0.textLayoutState.getLayoutResult();
                if (result != null) {
                    return it.add(result);
                }
                return false;
            }

            static final boolean applySemantics$lambda$3(boolean $editable, TextFieldDecoratorModifierNode this$0, AnnotatedString newText) {
                if (!$editable) {
                    return false;
                }
                this$0.textFieldState.replaceAll(newText);
                return true;
            }

            static final boolean applySemantics$lambda$4(boolean $editable, TextFieldDecoratorModifierNode this$0, AnnotatedString newText) {
                if (!$editable) {
                    return false;
                }
                TransformedTextFieldState.replaceSelectedText$default(this$0.textFieldState, newText, true, null, false, 12, null);
                return true;
            }

            static final boolean applySemantics$lambda$5(TextFieldDecoratorModifierNode this$0, int start, int end, boolean relativeToOriginal) {
                TextFieldCharSequence text;
                if (relativeToOriginal) {
                    text = this$0.textFieldState.getUntransformedText();
                } else {
                    text = this$0.textFieldState.getVisualText();
                }
                long selection = text.getSelection();
                if (!this$0.enabled || Math.min(start, end) < 0 || Math.max(start, end) > text.length()) {
                    return false;
                }
                if (start == TextRange.m7573getStartimpl(selection) && end == TextRange.m7568getEndimpl(selection)) {
                    return true;
                }
                long selectionRange = TextRangeKt.TextRange(start, end);
                if (relativeToOriginal || start == end) {
                    this$0.textFieldSelectionState.updateTextToolbarState(TextToolbarState.None);
                } else {
                    this$0.textFieldSelectionState.updateTextToolbarState(TextToolbarState.Selection);
                }
                if (relativeToOriginal) {
                    this$0.textFieldState.m1898selectUntransformedCharsIn5zctL8(selectionRange);
                } else {
                    this$0.textFieldState.m1897selectCharsIn5zctL8(selectionRange);
                }
                return true;
            }

            static final boolean applySemantics$lambda$6(TextFieldDecoratorModifierNode this$0, int $effectiveImeAction) {
                this$0.m1862onImeActionPerformedKlQnJC8($effectiveImeAction);
                return true;
            }

            static final boolean applySemantics$lambda$7(TextFieldDecoratorModifierNode this$0) {
                if (!this$0.isFocused()) {
                    this$0.requestFocus();
                    return true;
                }
                if (!this$0.readOnly) {
                    this$0.requireKeyboardController().show();
                    return true;
                }
                return true;
            }

            static final boolean applySemantics$lambda$8(TextFieldDecoratorModifierNode this$0) {
                if (!this$0.isFocused()) {
                    this$0.requestFocus();
                }
                this$0.textFieldSelectionState.updateTextToolbarState(TextToolbarState.Selection);
                return true;
            }

            static final boolean applySemantics$lambda$9(TextFieldDecoratorModifierNode this$0) {
                BuildersKt__Builders_commonKt.launch$default(this$0.getCoroutineScope(), null, null, new TextFieldDecoratorModifierNode$applySemantics$10$1(this$0, null), 3, null);
                return true;
            }

            static final boolean applySemantics$lambda$10(TextFieldDecoratorModifierNode this$0) {
                BuildersKt__Builders_commonKt.launch$default(this$0.getCoroutineScope(), null, null, new TextFieldDecoratorModifierNode$applySemantics$11$1(this$0, null), 3, null);
                return true;
            }

            static final boolean applySemantics$lambda$11(TextFieldDecoratorModifierNode this$0) {
                BuildersKt__Builders_commonKt.launch$default(this$0.getCoroutineScope(), null, null, new TextFieldDecoratorModifierNode$applySemantics$12$1(this$0, null), 3, null);
                return true;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public final void requestFocus() {
                if (this.focusableNode.getIsAttached()) {
                    this.focusableNode.requestFocus();
                }
            }

            private final void onIsFocusedUpdated() {
                this.textFieldSelectionState.setFocused(isFocused());
                if (isFocused() && this.toolbarAndHandlesVisibilityObserverJob == null) {
                    this.toolbarAndHandlesVisibilityObserverJob = BuildersKt__Builders_commonKt.launch$default(getCoroutineScope(), null, null, new AnonymousClass1(null), 3, null);
                } else if (!isFocused()) {
                    Job job = this.toolbarAndHandlesVisibilityObserverJob;
                    if (job != null) {
                        Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
                    }
                    this.toolbarAndHandlesVisibilityObserverJob = null;
                }
            }

            /* JADX INFO: renamed from: androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode$onIsFocusedUpdated$1, reason: invalid class name */
            /* JADX INFO: compiled from: TextFieldDecoratorModifier.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
            @DebugMetadata(c = "androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode$onIsFocusedUpdated$1", f = "TextFieldDecoratorModifier.kt", i = {}, l = {708}, m = "invokeSuspend", n = {}, s = {}, v = 1)
            static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                int label;

                AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
                    super(2, continuation);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    return TextFieldDecoratorModifierNode.this.new AnonymousClass1(continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object $result) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    switch (this.label) {
                        case 0:
                            ResultKt.throwOnFailure($result);
                            this.label = 1;
                            if (TextFieldDecoratorModifierNode.this.getTextFieldSelectionState().startToolbarAndHandlesVisibilityObserver(this) == coroutine_suspended) {
                                return coroutine_suspended;
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

            @Override // androidx.compose.ui.focus.FocusPropertiesModifierNode
            public void applyFocusProperties(FocusProperties focusProperties) {
                focusProperties.setFocusRect(this.textFieldSelectionState.getFocusRect());
            }

            @Override // androidx.compose.ui.Modifier.Node
            public void onAttach() {
                onObservedReadsChanged();
                this.textFieldSelectionState.setReceiveContentConfiguration(this.receiveContentConfigurationProvider);
                if (this.enabled) {
                    delegate(this.focusableNode);
                }
            }

            @Override // androidx.compose.ui.Modifier.Node
            public void onDetach() {
                disposeInputSession();
                this.textFieldSelectionState.setReceiveContentConfiguration(null);
            }

            @Override // androidx.compose.ui.node.GlobalPositionAwareModifierNode
            public void onGloballyPositioned(LayoutCoordinates coordinates) {
                this.textLayoutState.setDecoratorNodeCoordinates(coordinates);
                if (this.enabled) {
                    this.focusableNode.onGloballyPositioned(coordinates);
                }
            }

            @Override // androidx.compose.ui.node.PointerInputModifierNode
            /* JADX INFO: renamed from: onPointerEvent-H0pRuoY */
            public void mo255onPointerEventH0pRuoY(PointerEvent pointerEvent, PointerEventPass pass, long bounds) {
                this.pointerInputNode.mo255onPointerEventH0pRuoY(pointerEvent, pass, bounds);
            }

            @Override // androidx.compose.ui.node.PointerInputModifierNode
            public void onCancelPointerInput() {
                this.pointerInputNode.onCancelPointerInput();
            }

            @Override // androidx.compose.ui.input.key.KeyInputModifierNode
            /* JADX INFO: renamed from: onPreKeyEvent-ZmokQxo */
            public boolean mo256onPreKeyEventZmokQxo(KeyEvent event) {
                return this.textFieldKeyEventHandler.m1867onPreKeyEventMyFupTE(event, this.textFieldState, this.textFieldSelectionState, (FocusManager) CompositionLocalConsumerModifierNodeKt.currentValueOf(this, CompositionLocalsKt.getLocalFocusManager()), requireKeyboardController());
            }

            @Override // androidx.compose.ui.input.key.KeyInputModifierNode
            /* JADX INFO: renamed from: onKeyEvent-ZmokQxo */
            public boolean mo254onKeyEventZmokQxo(KeyEvent event) {
                return this.textFieldKeyEventHandler.mo1748onKeyEvent8zsqlwg(event, this.textFieldState, this.textLayoutState, this.textFieldSelectionState, this.clipboardKeyCommandsHandler, requireKeyboardController(), this.enabled && !this.readOnly, this.singleLine, new Function0() { // from class: androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode$$ExternalSyntheticLambda25
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        TextFieldDecoratorModifierNode textFieldDecoratorModifierNode = this.f$0;
                        return Boolean.valueOf(textFieldDecoratorModifierNode.m1862onImeActionPerformedKlQnJC8(textFieldDecoratorModifierNode.keyboardOptions.m1600getImeActionOrDefaulteUduSuo$foundation()));
                    }
                });
            }

            @Override // androidx.compose.ui.node.ObserverModifierNode
            public void onObservedReadsChanged() {
                updateWindowFocus();
            }

            private final void updateWindowFocus() {
                ObserverModifierNodeKt.observeReads(this, new Function0() { // from class: androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode$$ExternalSyntheticLambda12
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return TextFieldDecoratorModifierNode.updateWindowFocus$lambda$0(this.f$0);
                    }
                });
            }

            static final Unit updateWindowFocus$lambda$0(TextFieldDecoratorModifierNode this$0) {
                this$0.windowInfo = (WindowInfo) CompositionLocalConsumerModifierNodeKt.currentValueOf(this$0, CompositionLocalsKt.getLocalWindowInfo());
                this$0.onIsFocusedUpdated();
                return Unit.INSTANCE;
            }

            @Override // androidx.compose.ui.node.LayoutAwareModifierNode
            public void onPlaced(LayoutCoordinates coordinates) {
                this.dragAndDropNode.onPlaced(coordinates);
            }

            @Override // androidx.compose.ui.node.LayoutAwareModifierNode, androidx.compose.ui.node.MeasuredSizeAwareModifierNode
            /* JADX INFO: renamed from: onRemeasured-ozmzZPI */
            public void mo421onRemeasuredozmzZPI(long size) {
                this.dragAndDropNode.mo421onRemeasuredozmzZPI(size);
            }

            private final void applyCurrentInputMode() {
                if (!InputMode.m6119equalsimpl0(((InputModeManager) CompositionLocalConsumerModifierNodeKt.currentValueOf(this, CompositionLocalsKt.getLocalInputModeManager())).mo6126getInputModeaOaMEAU(), InputMode.INSTANCE.m6124getTouchaOaMEAU())) {
                    this.textFieldSelectionState.setInTouchMode(false);
                }
            }

            /* JADX INFO: Access modifiers changed from: private */
            public final void startInputSession(boolean fromTap) {
                if (fromTap || this.keyboardOptions.getShowKeyboardOnFocusOrDefault$foundation()) {
                    ReceiveContentConfiguration receiveContentConfiguration = ReceiveContentConfigurationKt.getReceiveContentConfiguration(this);
                    this.inputSessionJob = BuildersKt__Builders_commonKt.launch$default(getCoroutineScope(), null, null, new C02251(receiveContentConfiguration, null), 3, null);
                }
            }

            /* JADX INFO: renamed from: androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode$startInputSession$1, reason: invalid class name and case insensitive filesystem */
            /* JADX INFO: compiled from: TextFieldDecoratorModifier.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
            @DebugMetadata(c = "androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode$startInputSession$1", f = "TextFieldDecoratorModifier.kt", i = {}, l = {817}, m = "invokeSuspend", n = {}, s = {}, v = 1)
            static final class C02251 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ ReceiveContentConfiguration $receiveContentConfiguration;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C02251(ReceiveContentConfiguration receiveContentConfiguration, Continuation<? super C02251> continuation) {
                    super(2, continuation);
                    this.$receiveContentConfiguration = receiveContentConfiguration;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    return TextFieldDecoratorModifierNode.this.new C02251(this.$receiveContentConfiguration, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C02251) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object $result) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    switch (this.label) {
                        case 0:
                            ResultKt.throwOnFailure($result);
                            this.label = 1;
                            if (PlatformTextInputModifierNodeKt.establishTextInputSession(TextFieldDecoratorModifierNode.this, new C00311(TextFieldDecoratorModifierNode.this, this.$receiveContentConfiguration, null), this) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            break;
                        case 1:
                            ResultKt.throwOnFailure($result);
                            break;
                        default:
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    throw new KotlinNothingValueException();
                }

                /* JADX INFO: renamed from: androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode$startInputSession$1$1, reason: invalid class name and collision with other inner class name */
                /* JADX INFO: compiled from: TextFieldDecoratorModifier.kt */
                @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0001\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/platform/PlatformTextInputSessionScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
                @DebugMetadata(c = "androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode$startInputSession$1$1", f = "TextFieldDecoratorModifier.kt", i = {}, l = {818}, m = "invokeSuspend", n = {}, s = {}, v = 1)
                static final class C00311 extends SuspendLambda implements Function2<PlatformTextInputSessionScope, Continuation<?>, Object> {
                    final /* synthetic */ ReceiveContentConfiguration $receiveContentConfiguration;
                    private /* synthetic */ Object L$0;
                    int label;
                    final /* synthetic */ TextFieldDecoratorModifierNode this$0;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    C00311(TextFieldDecoratorModifierNode textFieldDecoratorModifierNode, ReceiveContentConfiguration receiveContentConfiguration, Continuation<? super C00311> continuation) {
                        super(2, continuation);
                        this.this$0 = textFieldDecoratorModifierNode;
                        this.$receiveContentConfiguration = receiveContentConfiguration;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                        C00311 c00311 = new C00311(this.this$0, this.$receiveContentConfiguration, continuation);
                        c00311.L$0 = obj;
                        return c00311;
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(PlatformTextInputSessionScope platformTextInputSessionScope, Continuation<?> continuation) {
                        return ((C00311) create(platformTextInputSessionScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object $result) {
                        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        switch (this.label) {
                            case 0:
                                ResultKt.throwOnFailure($result);
                                PlatformTextInputSessionScope $this$establishTextInputSession = (PlatformTextInputSessionScope) this.L$0;
                                PlatformTextInputSessionScope platformTextInputSessionScope = $this$establishTextInputSession;
                                TransformedTextFieldState textFieldState = this.this$0.getTextFieldState();
                                TextLayoutState textLayoutState = this.this$0.getTextLayoutState();
                                ImeOptions imeOptions$foundation = this.this$0.getKeyboardOptions().toImeOptions$foundation(this.this$0.getSingleLine());
                                ReceiveContentConfiguration receiveContentConfiguration = this.$receiveContentConfiguration;
                                C00321 c00321 = new C00321(this.this$0);
                                final TextFieldDecoratorModifierNode textFieldDecoratorModifierNode = this.this$0;
                                Function0 function0 = new Function0() { // from class: androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode$startInputSession$1$1$$ExternalSyntheticLambda0
                                    @Override // kotlin.jvm.functions.Function0
                                    public final Object invoke() {
                                        return TextFieldDecoratorModifierNode.C02251.C00311.invokeSuspend$lambda$0(textFieldDecoratorModifierNode);
                                    }
                                };
                                MutableSharedFlow<Unit> stylusHandwritingTrigger = this.this$0.getStylusHandwritingTrigger();
                                ViewConfiguration viewConfiguration = (ViewConfiguration) CompositionLocalConsumerModifierNodeKt.currentValueOf(this.this$0, CompositionLocalsKt.getLocalViewConfiguration());
                                final TextFieldDecoratorModifierNode textFieldDecoratorModifierNode2 = this.this$0;
                                this.label = 1;
                                if (AndroidTextInputSession_androidKt.platformSpecificTextInputSession(platformTextInputSessionScope, textFieldState, textLayoutState, imeOptions$foundation, receiveContentConfiguration, c00321, function0, stylusHandwritingTrigger, viewConfiguration, new Function1() { // from class: androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode$startInputSession$1$1$$ExternalSyntheticLambda1
                                    @Override // kotlin.jvm.functions.Function1
                                    public final Object invoke(Object obj) {
                                        return TextFieldDecoratorModifierNode.C02251.C00311.invokeSuspend$lambda$1(textFieldDecoratorModifierNode2, ((Boolean) obj).booleanValue());
                                    }
                                }, this) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                break;
                            case 1:
                                ResultKt.throwOnFailure($result);
                                break;
                            default:
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        throw new KotlinNothingValueException();
                    }

                    /* JADX INFO: renamed from: androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode$startInputSession$1$1$1, reason: invalid class name and collision with other inner class name */
                    /* JADX INFO: compiled from: TextFieldDecoratorModifier.kt */
                    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
                    static final /* synthetic */ class C00321 extends AdaptedFunctionReference implements Function1<ImeAction, Unit> {
                        C00321(Object obj) {
                            super(1, obj, TextFieldDecoratorModifierNode.class, "onImeActionPerformed", "onImeActionPerformed-KlQnJC8(I)Z", 8);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(ImeAction imeAction) {
                            m1864invokeKlQnJC8(imeAction.getValue());
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke-KlQnJC8, reason: not valid java name */
                        public final void m1864invokeKlQnJC8(int p0) {
                            ((TextFieldDecoratorModifierNode) this.receiver).m1862onImeActionPerformedKlQnJC8(p0);
                        }
                    }

                    static final Unit invokeSuspend$lambda$0(TextFieldDecoratorModifierNode this$0) {
                        this$0.getTextFieldSelectionState().updateTextToolbarState(TextToolbarState.Selection);
                        return Unit.INSTANCE;
                    }

                    static final Unit invokeSuspend$lambda$1(TextFieldDecoratorModifierNode this$0, boolean it) {
                        this$0.getTextFieldSelectionState().setInTouchMode(it);
                        return Unit.INSTANCE;
                    }
                }
            }

            private final void disposeInputSession() {
                Job job = this.inputSessionJob;
                if (job != null) {
                    Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
                }
                this.inputSessionJob = null;
                MutableSharedFlow<Unit> mutableSharedFlow = this.stylusHandwritingTrigger;
                if (mutableSharedFlow != null) {
                    mutableSharedFlow.resetReplayCache();
                }
            }

            /* JADX INFO: Access modifiers changed from: private */
            public final SoftwareKeyboardController requireKeyboardController() {
                SoftwareKeyboardController softwareKeyboardController = (SoftwareKeyboardController) CompositionLocalConsumerModifierNodeKt.currentValueOf(this, CompositionLocalsKt.getLocalSoftwareKeyboardController());
                if (softwareKeyboardController != null) {
                    return softwareKeyboardController;
                }
                throw new IllegalStateException("No software keyboard controller".toString());
            }

            private final void emitDragExitEvent() {
                DragAndDropHoverInteraction.Enter it = this.dragEnterEvent;
                if (it != null) {
                    this.interactionSource.tryEmit(new DragAndDropHoverInteraction.Exit(it));
                    this.dragEnterEvent = null;
                }
            }

            /* JADX INFO: Access modifiers changed from: private */
            /* JADX INFO: renamed from: onImeActionPerformed-KlQnJC8, reason: not valid java name */
            public final boolean m1862onImeActionPerformedKlQnJC8(final int imeAction) {
                if (ImeAction.m7741equalsimpl0(imeAction, ImeAction.INSTANCE.m7758getNoneeUduSuo()) || ImeAction.m7741equalsimpl0(imeAction, ImeAction.INSTANCE.m7754getDefaulteUduSuo()) || this.keyboardActionHandler == null) {
                    return m1861defaultKeyboardActionWithResultKlQnJC8(imeAction);
                }
                KeyboardActionHandler keyboardActionHandler = this.keyboardActionHandler;
                if (keyboardActionHandler != null) {
                    keyboardActionHandler.onKeyboardAction(new Function0() { // from class: androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode$$ExternalSyntheticLambda26
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return TextFieldDecoratorModifierNode.onImeActionPerformed_KlQnJC8$lambda$0(this.f$0, imeAction);
                        }
                    });
                    return true;
                }
                return true;
            }

            static final Unit onImeActionPerformed_KlQnJC8$lambda$0(TextFieldDecoratorModifierNode this$0, int $imeAction) {
                this$0.keyboardActionScope.mo1586defaultKeyboardActionKlQnJC8($imeAction);
                return Unit.INSTANCE;
            }

            /* JADX INFO: Access modifiers changed from: private */
            /* JADX INFO: renamed from: defaultKeyboardActionWithResult-KlQnJC8, reason: not valid java name */
            public final boolean m1861defaultKeyboardActionWithResultKlQnJC8(int imeAction) {
                if (ImeAction.m7741equalsimpl0(imeAction, ImeAction.INSTANCE.m7757getNexteUduSuo())) {
                    ((FocusManager) CompositionLocalConsumerModifierNodeKt.currentValueOf(this, CompositionLocalsKt.getLocalFocusManager())).mo4957moveFocus3ESFkO8(FocusDirection.INSTANCE.m4951getNextdhqQ8s());
                    return true;
                }
                if (ImeAction.m7741equalsimpl0(imeAction, ImeAction.INSTANCE.m7759getPreviouseUduSuo())) {
                    ((FocusManager) CompositionLocalConsumerModifierNodeKt.currentValueOf(this, CompositionLocalsKt.getLocalFocusManager())).mo4957moveFocus3ESFkO8(FocusDirection.INSTANCE.m4952getPreviousdhqQ8s());
                    return true;
                }
                if (ImeAction.m7741equalsimpl0(imeAction, ImeAction.INSTANCE.m7755getDoneeUduSuo())) {
                    requireKeyboardController().hide();
                    return true;
                }
                return false;
            }
        }
