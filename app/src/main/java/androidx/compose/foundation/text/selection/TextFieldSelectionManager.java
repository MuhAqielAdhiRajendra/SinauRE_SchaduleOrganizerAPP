package androidx.compose.foundation.text.selection;

import androidx.compose.foundation.ComposeFoundationFlags;
import androidx.compose.foundation.internal.ClipboardUtils_androidKt;
import androidx.compose.foundation.text.Handle;
import androidx.compose.foundation.text.HandleState;
import androidx.compose.foundation.text.LegacyTextFieldState;
import androidx.compose.foundation.text.TextDelegate;
import androidx.compose.foundation.text.TextDragObserver;
import androidx.compose.foundation.text.TextFieldCursor_androidKt;
import androidx.compose.foundation.text.TextLayoutHelperKt;
import androidx.compose.foundation.text.TextLayoutResultProxy;
import androidx.compose.foundation.text.UndoManager;
import androidx.compose.foundation.text.ValidatingOffsetMappingKt;
import androidx.compose.foundation.text.contextmenu.modifier.TextContextMenuGesturesModifierKt;
import androidx.compose.foundation.text.contextmenu.modifier.TextContextMenuToolbarHandlerModifierKt;
import androidx.compose.foundation.text.contextmenu.modifier.ToolbarRequester;
import androidx.compose.foundation.text.contextmenu.modifier.ToolbarRequesterImpl;
import androidx.compose.foundation.text.selection.TextFieldSelectionManager;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.focus.FocusRequester;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.hapticfeedback.HapticFeedback;
import androidx.compose.ui.hapticfeedback.HapticFeedbackType;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.platform.Clipboard;
import androidx.compose.ui.platform.TextToolbar;
import androidx.compose.ui.platform.TextToolbarStatus;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import androidx.compose.ui.text.input.OffsetMapping;
import androidx.compose.ui.text.input.PasswordVisualTransformation;
import androidx.compose.ui.text.input.TextFieldValue;
import androidx.compose.ui.text.input.TextFieldValueKt;
import androidx.compose.ui.text.input.VisualTransformation;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;

/* JADX INFO: compiled from: TextFieldSelectionManager.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0094\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b+\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0001\u0018\u00002\u00020\u0001B\u0013\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001a\u0010\u0099\u0001\u001a\u00020\u00112\t\u0010\u009a\u0001\u001a\u0004\u0018\u00010hH\u0002¢\u0006\u0003\b\u009b\u0001J\u0019\u0010\u009c\u0001\u001a\u00030\u0092\u00012\u0007\u0010\u009d\u0001\u001a\u00020XH\u0000¢\u0006\u0003\b\u009e\u0001J\u0010\u0010\u009f\u0001\u001a\u00030\u0092\u0001H\u0000¢\u0006\u0003\b \u0001J\u001a\u0010¡\u0001\u001a\u00020\u00112\t\b\u0002\u0010¢\u0001\u001a\u00020XH\u0000¢\u0006\u0003\b£\u0001J\u000f\u0010¤\u0001\u001a\u00020\u0011H\u0000¢\u0006\u0003\b¥\u0001J\u001c\u0010¦\u0001\u001a\u00020\u00112\u000b\b\u0002\u0010§\u0001\u001a\u0004\u0018\u00010eH\u0000¢\u0006\u0003\b¨\u0001J\u001b\u0010©\u0001\u001a\u00020\u00112\u0007\u0010ª\u0001\u001a\u00020hH\u0000¢\u0006\u0006\b«\u0001\u0010¬\u0001J\u001b\u0010\u00ad\u0001\u001a\u00020\u00112\u0007\u0010ª\u0001\u001a\u00020hH\u0000¢\u0006\u0006\b®\u0001\u0010¬\u0001J\u000f\u0010¯\u0001\u001a\u00020\u0011H\u0000¢\u0006\u0003\b°\u0001J\u000f\u0010º\u0001\u001a\u00020XH\u0000¢\u0006\u0003\b»\u0001J\u0010\u0010¼\u0001\u001a\u00020XH\u0080\b¢\u0006\u0003\b½\u0001J\u0013\u0010¾\u0001\u001a\u00020\u0011H\u0080@¢\u0006\u0006\b¿\u0001\u0010À\u0001J\u0019\u0010Á\u0001\u001a\u0012\u0012\u0005\u0012\u00030Ã\u0001\u0012\u0004\u0012\u00020h\u0018\u00010Â\u0001H\u0002J\u000f\u0010Ä\u0001\u001a\u00020XH\u0000¢\u0006\u0003\bÅ\u0001J\u0010\u0010Æ\u0001\u001a\u00020XH\u0080\b¢\u0006\u0003\bÇ\u0001J\u000f\u0010È\u0001\u001a\u00020XH\u0000¢\u0006\u0003\bÉ\u0001J\u0010\u0010Ê\u0001\u001a\u00020XH\u0080\b¢\u0006\u0003\bË\u0001J\u000f\u0010Ì\u0001\u001a\u00020XH\u0000¢\u0006\u0003\bÍ\u0001J\u000f\u0010Î\u0001\u001a\u00020XH\u0000¢\u0006\u0003\bÏ\u0001J\u001d\u0010Ð\u0001\u001a\u0005\u0018\u00010Ñ\u00012\t\b\u0002\u0010Ò\u0001\u001a\u00020XH\u0000¢\u0006\u0003\bÓ\u0001J\u001c\u0010Ô\u0001\u001a\u0004\u0018\u00010$2\t\b\u0002\u0010Ò\u0001\u001a\u00020XH\u0000¢\u0006\u0003\bÕ\u0001J\u0012\u0010Ö\u0001\u001a\u0005\u0018\u00010Ñ\u0001H\u0000¢\u0006\u0003\b×\u0001J\u0018\u0010Ö\u0001\u001a\u00020\u00112\u0007\u0010Ø\u0001\u001a\u00020$H\u0000¢\u0006\u0003\b×\u0001J\u0012\u0010Ù\u0001\u001a\u0005\u0018\u00010Ñ\u0001H\u0000¢\u0006\u0003\bÚ\u0001J\u0011\u0010Û\u0001\u001a\u0004\u0018\u00010$H\u0000¢\u0006\u0003\bÜ\u0001J\u000f\u0010Ý\u0001\u001a\u00020\u0011H\u0000¢\u0006\u0003\bÞ\u0001J\u000f\u0010ß\u0001\u001a\u00020\u0011H\u0000¢\u0006\u0003\bà\u0001J\u001b\u0010á\u0001\u001a\u00020e2\u0007\u0010\u009d\u0001\u001a\u00020XH\u0000¢\u0006\u0006\bâ\u0001\u0010ã\u0001J\u0019\u0010ä\u0001\u001a\u00030å\u00012\u0007\u0010\u009d\u0001\u001a\u00020XH\u0000¢\u0006\u0003\bæ\u0001J\u001c\u0010ç\u0001\u001a\u00020e2\b\u0010è\u0001\u001a\u00030é\u0001H\u0000¢\u0006\u0006\bê\u0001\u0010ë\u0001J\u0012\u0010ì\u0001\u001a\u00020\u00112\u0007\u0010í\u0001\u001a\u00020XH\u0002J\u000f\u0010î\u0001\u001a\u00020\u0011H\u0000¢\u0006\u0003\bï\u0001J\f\u0010ð\u0001\u001a\u0005\u0018\u00010Ñ\u0001H\u0002J\u000f\u0010ñ\u0001\u001a\u00020\u0011H\u0000¢\u0006\u0003\bò\u0001J\u0019\u0010ó\u0001\u001a\u00020\u00112\u0007\u0010§\u0001\u001a\u00020e¢\u0006\u0006\bô\u0001\u0010¬\u0001J\u000f\u0010õ\u0001\u001a\u00020XH\u0000¢\u0006\u0003\bö\u0001J\n\u0010÷\u0001\u001a\u00030ø\u0001H\u0002JT\u0010ù\u0001\u001a\u00020h2\u0006\u0010\u001e\u001a\u00020\u00102\u0007\u0010ú\u0001\u001a\u00020e2\u0007\u0010û\u0001\u001a\u00020X2\u0007\u0010\u009d\u0001\u001a\u00020X2\b\u0010ü\u0001\u001a\u00030ý\u00012\u0007\u0010þ\u0001\u001a\u00020X2\n\u0010ÿ\u0001\u001a\u0005\u0018\u00010\u0080\u0002H\u0002¢\u0006\u0006\b\u0081\u0002\u0010\u0082\u0002J\u0013\u0010\u0083\u0002\u001a\u00020\u00112\b\u0010\u0084\u0002\u001a\u00030\u0085\u0002H\u0002J$\u0010\u0086\u0002\u001a\u00020\u00102\u0007\u0010\u0087\u0002\u001a\u00020$2\u0007\u0010\u009a\u0001\u001a\u00020hH\u0002¢\u0006\u0006\b\u0088\u0002\u0010\u0089\u0002R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\u00020\tX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR&\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00110\u000fX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001c\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u0014\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00100\u001dX\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010\u001e\u001a\u00020\u00102\u0006\u0010\u001e\u001a\u00020\u00108@@@X\u0080\u000e¢\u0006\f\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u0016\u0010#\u001a\u0004\u0018\u00010$8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b%\u0010&R\u001a\u0010'\u001a\u00020(X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R\"\u0010-\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010.X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u00100\"\u0004\b1\u00102R\u001c\u00103\u001a\u0004\u0018\u000104X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b5\u00106\"\u0004\b7\u00108R\u001c\u00109\u001a\u0004\u0018\u00010:X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b;\u0010<\"\u0004\b=\u0010>R\u001c\u0010?\u001a\u0004\u0018\u00010@X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bA\u0010B\"\u0004\bC\u0010DR\u001c\u0010E\u001a\u0004\u0018\u00010FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bG\u0010H\"\u0004\bI\u0010JR\u001c\u0010K\u001a\u0004\u0018\u00010LX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bM\u0010N\"\u0004\bO\u0010PR\u001c\u0010Q\u001a\u0004\u0018\u00010RX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bS\u0010T\"\u0004\bU\u0010VR+\u0010Y\u001a\u00020X2\u0006\u0010W\u001a\u00020X8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b^\u0010_\u001a\u0004\bZ\u0010[\"\u0004\b\\\u0010]R+\u0010`\u001a\u00020X2\u0006\u0010W\u001a\u00020X8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bc\u0010_\u001a\u0004\ba\u0010[\"\u0004\bb\u0010]R\u0010\u0010d\u001a\u00020eX\u0082\u000e¢\u0006\u0004\n\u0002\u0010fR\u0010\u0010g\u001a\u0004\u0018\u00010hX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010i\u001a\u00020eX\u0082\u000e¢\u0006\u0004\n\u0002\u0010fR/\u0010k\u001a\u0004\u0018\u00010j2\b\u0010W\u001a\u0004\u0018\u00010j8F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bp\u0010_\u001a\u0004\bl\u0010m\"\u0004\bn\u0010oR/\u0010q\u001a\u0004\u0018\u00010e2\b\u0010W\u001a\u0004\u0018\u00010e8F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bv\u0010_\u001a\u0004\br\u0010s\"\u0004\bt\u0010uR\u000e\u0010w\u001a\u00020xX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010y\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010z\u001a\u0004\u0018\u00010{X\u0082\u000e¢\u0006\u0002\n\u0000R\u001d\u0010|\u001a\u0004\u0018\u00010hX\u0080\u000e¢\u0006\u000f\n\u0000\u001a\u0004\b}\u0010~\"\u0005\b\u007f\u0010\u0080\u0001R/\u0010\u0081\u0001\u001a\u00020X2\u0006\u0010W\u001a\u00020X8B@BX\u0082\u008e\u0002¢\u0006\u0015\n\u0005\b\u0084\u0001\u0010_\u001a\u0005\b\u0082\u0001\u0010[\"\u0005\b\u0083\u0001\u0010]R,\u0010\u0085\u0001\u001a\u00030\u0086\u00018\u0000@\u0000X\u0081\u000e¢\u0006\u001a\n\u0000\u0012\u0006\b\u0087\u0001\u0010\u0088\u0001\u001a\u0006\b\u0089\u0001\u0010\u008a\u0001\"\u0006\b\u008b\u0001\u0010\u008c\u0001R\u0015\u0010\u008d\u0001\u001a\u00030\u008e\u00018F¢\u0006\b\u001a\u0006\b\u008f\u0001\u0010\u0090\u0001R\u0018\u0010\u0091\u0001\u001a\u00030\u0092\u0001X\u0080\u0004¢\u0006\n\n\u0000\u001a\u0006\b\u0093\u0001\u0010\u0094\u0001R\u0018\u0010\u0095\u0001\u001a\u00030\u0096\u0001X\u0080\u0004¢\u0006\n\n\u0000\u001a\u0006\b\u0097\u0001\u0010\u0098\u0001R\u001d\u0010±\u0001\u001a\u00020XX\u0080\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b²\u0001\u0010[\"\u0005\b³\u0001\u0010]R\u001e\u0010´\u0001\u001a\u00020X8@X\u0080\u0004¢\u0006\u000f\u0012\u0006\bµ\u0001\u0010\u0088\u0001\u001a\u0005\b¶\u0001\u0010[R\u0016\u0010·\u0001\u001a\u00020X8BX\u0082\u0004¢\u0006\u0007\u001a\u0005\b·\u0001\u0010[R\u0016\u0010¸\u0001\u001a\u00020X8BX\u0082\u0004¢\u0006\u0007\u001a\u0005\b¹\u0001\u0010[¨\u0006\u008a\u0002"}, d2 = {"Landroidx/compose/foundation/text/selection/TextFieldSelectionManager;", "", "undoManager", "Landroidx/compose/foundation/text/UndoManager;", "<init>", "(Landroidx/compose/foundation/text/UndoManager;)V", "getUndoManager", "()Landroidx/compose/foundation/text/UndoManager;", "offsetMapping", "Landroidx/compose/ui/text/input/OffsetMapping;", "getOffsetMapping$foundation", "()Landroidx/compose/ui/text/input/OffsetMapping;", "setOffsetMapping$foundation", "(Landroidx/compose/ui/text/input/OffsetMapping;)V", "onValueChange", "Lkotlin/Function1;", "Landroidx/compose/ui/text/input/TextFieldValue;", "", "getOnValueChange$foundation", "()Lkotlin/jvm/functions/Function1;", "setOnValueChange$foundation", "(Lkotlin/jvm/functions/Function1;)V", "state", "Landroidx/compose/foundation/text/LegacyTextFieldState;", "getState$foundation", "()Landroidx/compose/foundation/text/LegacyTextFieldState;", "setState$foundation", "(Landroidx/compose/foundation/text/LegacyTextFieldState;)V", "valueState", "Landroidx/compose/runtime/MutableState;", "value", "getValue$foundation", "()Landroidx/compose/ui/text/input/TextFieldValue;", "setValue$foundation", "(Landroidx/compose/ui/text/input/TextFieldValue;)V", "transformedText", "Landroidx/compose/ui/text/AnnotatedString;", "getTransformedText$foundation", "()Landroidx/compose/ui/text/AnnotatedString;", "visualTransformation", "Landroidx/compose/ui/text/input/VisualTransformation;", "getVisualTransformation$foundation", "()Landroidx/compose/ui/text/input/VisualTransformation;", "setVisualTransformation$foundation", "(Landroidx/compose/ui/text/input/VisualTransformation;)V", "requestAutofillAction", "Lkotlin/Function0;", "getRequestAutofillAction$foundation", "()Lkotlin/jvm/functions/Function0;", "setRequestAutofillAction$foundation", "(Lkotlin/jvm/functions/Function0;)V", "clipboard", "Landroidx/compose/ui/platform/Clipboard;", "getClipboard$foundation", "()Landroidx/compose/ui/platform/Clipboard;", "setClipboard$foundation", "(Landroidx/compose/ui/platform/Clipboard;)V", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "getCoroutineScope$foundation", "()Lkotlinx/coroutines/CoroutineScope;", "setCoroutineScope$foundation", "(Lkotlinx/coroutines/CoroutineScope;)V", "platformSelectionBehaviors", "Landroidx/compose/foundation/text/selection/PlatformSelectionBehaviors;", "getPlatformSelectionBehaviors$foundation", "()Landroidx/compose/foundation/text/selection/PlatformSelectionBehaviors;", "setPlatformSelectionBehaviors$foundation", "(Landroidx/compose/foundation/text/selection/PlatformSelectionBehaviors;)V", "textToolbar", "Landroidx/compose/ui/platform/TextToolbar;", "getTextToolbar", "()Landroidx/compose/ui/platform/TextToolbar;", "setTextToolbar", "(Landroidx/compose/ui/platform/TextToolbar;)V", "hapticFeedBack", "Landroidx/compose/ui/hapticfeedback/HapticFeedback;", "getHapticFeedBack", "()Landroidx/compose/ui/hapticfeedback/HapticFeedback;", "setHapticFeedBack", "(Landroidx/compose/ui/hapticfeedback/HapticFeedback;)V", "focusRequester", "Landroidx/compose/ui/focus/FocusRequester;", "getFocusRequester", "()Landroidx/compose/ui/focus/FocusRequester;", "setFocusRequester", "(Landroidx/compose/ui/focus/FocusRequester;)V", "<set-?>", "", "editable", "getEditable", "()Z", "setEditable", "(Z)V", "editable$delegate", "Landroidx/compose/runtime/MutableState;", "enabled", "getEnabled", "setEnabled", "enabled$delegate", "dragBeginPosition", "Landroidx/compose/ui/geometry/Offset;", "J", "dragBeginSelection", "Landroidx/compose/ui/text/TextRange;", "dragTotalDistance", "Landroidx/compose/foundation/text/Handle;", "draggingHandle", "getDraggingHandle", "()Landroidx/compose/foundation/text/Handle;", "setDraggingHandle", "(Landroidx/compose/foundation/text/Handle;)V", "draggingHandle$delegate", "currentDragPosition", "getCurrentDragPosition-_m7T9-E", "()Landroidx/compose/ui/geometry/Offset;", "setCurrentDragPosition-_kEHs6E", "(Landroidx/compose/ui/geometry/Offset;)V", "currentDragPosition$delegate", "previousRawDragOffset", "", "oldValue", "previousSelectionLayout", "Landroidx/compose/foundation/text/selection/SelectionLayout;", "latestSelection", "getLatestSelection-MzsxiRA$foundation", "()Landroidx/compose/ui/text/TextRange;", "setLatestSelection-OEnZFl4$foundation", "(Landroidx/compose/ui/text/TextRange;)V", "hasAvailableTextToPaste", "getHasAvailableTextToPaste", "setHasAvailableTextToPaste", "hasAvailableTextToPaste$delegate", "toolbarRequester", "Landroidx/compose/foundation/text/contextmenu/modifier/ToolbarRequester;", "getToolbarRequester$foundation$annotations", "()V", "getToolbarRequester$foundation", "()Landroidx/compose/foundation/text/contextmenu/modifier/ToolbarRequester;", "setToolbarRequester$foundation", "(Landroidx/compose/foundation/text/contextmenu/modifier/ToolbarRequester;)V", "contextMenuAreaModifier", "Landroidx/compose/ui/Modifier;", "getContextMenuAreaModifier", "()Landroidx/compose/ui/Modifier;", "touchSelectionObserver", "Landroidx/compose/foundation/text/TextDragObserver;", "getTouchSelectionObserver$foundation", "()Landroidx/compose/foundation/text/TextDragObserver;", "mouseSelectionObserver", "Landroidx/compose/foundation/text/selection/MouseSelectionObserver;", "getMouseSelectionObserver$foundation", "()Landroidx/compose/foundation/text/selection/MouseSelectionObserver;", "maybeSuggestSelection", "selection", "maybeSuggestSelection-OEnZFl4", "handleDragObserver", "isStartHandle", "handleDragObserver$foundation", "cursorDragObserver", "cursorDragObserver$foundation", "enterSelectionMode", "showFloatingToolbar", "enterSelectionMode$foundation", "exitSelectionMode", "exitSelectionMode$foundation", "deselect", "position", "deselect-_kEHs6E$foundation", "setSelectionPreviewHighlight", "range", "setSelectionPreviewHighlight-5zc-tL8$foundation", "(J)V", "setDeletionPreviewHighlight", "setDeletionPreviewHighlight-5zc-tL8$foundation", "clearPreviewHighlight", "clearPreviewHighlight$foundation", "textToolbarShownViaProvider", "getTextToolbarShownViaProvider$foundation", "setTextToolbarShownViaProvider$foundation", "textToolbarShown", "getTextToolbarShown$foundation$annotations", "getTextToolbarShown$foundation", "isPassword", "hasSelection", "getHasSelection", "canShowCopyMenuItem", "canShowCopyMenuItem$foundation", "isCopyAllowed", "isCopyAllowed$foundation", "updateClipboardEntry", "updateClipboardEntry$foundation", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getContextTextAndSelection", "Lkotlin/Pair;", "", "canShowPasteMenuItem", "canShowPasteMenuItem$foundation", "isPasteAllowed", "isPasteAllowed$foundation", "canShowCutMenuItem", "canShowCutMenuItem$foundation", "isCutAllowed", "isCutAllowed$foundation", "canShowSelectAllMenuItem", "canShowSelectAllMenuItem$foundation", "canShowAutofillMenuItem", "canShowAutofillMenuItem$foundation", "copy", "Lkotlinx/coroutines/Job;", "cancelSelection", "copy$foundation", "copyWithResult", "copyWithResult$foundation", "paste", "paste$foundation", "text", "cut", "cut$foundation", "cutWithResult", "cutWithResult$foundation", "selectAll", "selectAll$foundation", "autofill", "autofill$foundation", "getHandlePosition", "getHandlePosition-tuRUvjQ$foundation", "(Z)J", "getHandleLineHeight", "", "getHandleLineHeight$foundation", "getCursorPosition", "density", "Landroidx/compose/ui/unit/Density;", "getCursorPosition-tuRUvjQ$foundation", "(Landroidx/compose/ui/unit/Density;)J", "updateFloatingToolbar", "show", "showSelectionToolbar", "showSelectionToolbar$foundation", "showSelectionToolbarViaTextToolbar", "hideSelectionToolbar", "hideSelectionToolbar$foundation", "selectWordAtPositionIfNotAlreadySelected", "selectWordAtPositionIfNotAlreadySelected-k-4lQ0M", "isTextChanged", "isTextChanged$foundation", "getContentRect", "Landroidx/compose/ui/geometry/Rect;", "updateSelection", "currentPosition", "isStartOfSelection", "adjustment", "Landroidx/compose/foundation/text/selection/SelectionAdjustment;", "isTouchBasedSelection", "hapticFeedbackType", "Landroidx/compose/ui/hapticfeedback/HapticFeedbackType;", "updateSelection-jSglsI8", "(Landroidx/compose/ui/text/input/TextFieldValue;JZZLandroidx/compose/foundation/text/selection/SelectionAdjustment;ZLandroidx/compose/ui/hapticfeedback/HapticFeedbackType;)J", "setHandleState", "handleState", "Landroidx/compose/foundation/text/HandleState;", "createTextFieldValue", "annotatedString", "createTextFieldValue-FDrldGo", "(Landroidx/compose/ui/text/AnnotatedString;J)Landroidx/compose/ui/text/input/TextFieldValue;", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class TextFieldSelectionManager {
    public static final int $stable = 8;
    private Clipboard clipboard;
    private CoroutineScope coroutineScope;

    /* JADX INFO: renamed from: currentDragPosition$delegate, reason: from kotlin metadata */
    private final MutableState currentDragPosition;
    private long dragBeginPosition;
    private TextRange dragBeginSelection;
    private long dragTotalDistance;

    /* JADX INFO: renamed from: draggingHandle$delegate, reason: from kotlin metadata */
    private final MutableState draggingHandle;

    /* JADX INFO: renamed from: editable$delegate, reason: from kotlin metadata */
    private final MutableState editable;

    /* JADX INFO: renamed from: enabled$delegate, reason: from kotlin metadata */
    private final MutableState enabled;
    private FocusRequester focusRequester;
    private HapticFeedback hapticFeedBack;

    /* JADX INFO: renamed from: hasAvailableTextToPaste$delegate, reason: from kotlin metadata */
    private final MutableState hasAvailableTextToPaste;
    private TextRange latestSelection;
    private final MouseSelectionObserver mouseSelectionObserver;
    private OffsetMapping offsetMapping;
    private TextFieldValue oldValue;
    private Function1<? super TextFieldValue, Unit> onValueChange;
    private PlatformSelectionBehaviors platformSelectionBehaviors;
    private int previousRawDragOffset;
    private SelectionLayout previousSelectionLayout;
    private Function0<Unit> requestAutofillAction;
    private LegacyTextFieldState state;
    private TextToolbar textToolbar;
    private boolean textToolbarShownViaProvider;
    private ToolbarRequester toolbarRequester;
    private final TextDragObserver touchSelectionObserver;
    private final UndoManager undoManager;
    private final MutableState<TextFieldValue> valueState;
    private VisualTransformation visualTransformation;

    public TextFieldSelectionManager() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public static /* synthetic */ void getTextToolbarShown$foundation$annotations() {
    }

    public static /* synthetic */ void getToolbarRequester$foundation$annotations() {
    }

    public TextFieldSelectionManager(UndoManager undoManager) {
        this.undoManager = undoManager;
        this.offsetMapping = ValidatingOffsetMappingKt.getValidatingEmptyOffsetMappingIdentity();
        this.onValueChange = new Function1() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManager$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Unit.INSTANCE;
            }
        };
        this.valueState = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(new TextFieldValue((String) null, 0L, (TextRange) null, 7, (DefaultConstructorMarker) null), null, 2, null);
        this.visualTransformation = VisualTransformation.INSTANCE.getNone();
        this.editable = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(true, null, 2, null);
        this.enabled = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(true, null, 2, null);
        this.dragBeginPosition = Offset.INSTANCE.m5084getZeroF1C5BW0();
        this.dragTotalDistance = Offset.INSTANCE.m5084getZeroF1C5BW0();
        this.draggingHandle = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
        this.currentDragPosition = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
        this.previousRawDragOffset = -1;
        this.oldValue = new TextFieldValue((String) null, 0L, (TextRange) (0 == true ? 1 : 0), 7, (DefaultConstructorMarker) null);
        this.hasAvailableTextToPaste = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
        this.toolbarRequester = new ToolbarRequesterImpl();
        this.touchSelectionObserver = new TextDragObserver() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManager$touchSelectionObserver$1
            private TextRange runningSelection;
            private boolean isLongPressSelectionOnly = true;
            private SelectionAdjustment selectionAdjustmentMode = SelectionAdjustment.INSTANCE.getNone();

            @Override // androidx.compose.foundation.text.TextDragObserver
            /* JADX INFO: renamed from: onDown-k-4lQ0M */
            public void mo1639onDownk4lQ0M(long point) {
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            public void onUp() {
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            /* JADX INFO: renamed from: onStart-3MmeM6k */
            public void mo1641onStart3MmeM6k(long startPoint, SelectionAdjustment selectionAdjustment) {
                TextLayoutResultProxy layoutResult;
                TextLayoutResultProxy layoutResult2;
                if (this.this$0.getEnabled() && this.this$0.getDraggingHandle() == null) {
                    this.this$0.setDraggingHandle(Handle.SelectionEnd);
                    this.this$0.previousRawDragOffset = -1;
                    this.isLongPressSelectionOnly = true;
                    this.selectionAdjustmentMode = selectionAdjustment;
                    this.this$0.hideSelectionToolbar$foundation();
                    LegacyTextFieldState state = this.this$0.getState();
                    boolean z = (state == null || (layoutResult2 = state.getLayoutResult()) == null || !layoutResult2.m1678isPositionOnTextk4lQ0M(startPoint)) ? false : true;
                    TextFieldSelectionManager textFieldSelectionManager = this.this$0;
                    if (!z) {
                        LegacyTextFieldState state2 = textFieldSelectionManager.getState();
                        if (state2 != null && (layoutResult = state2.getLayoutResult()) != null) {
                            TextFieldSelectionManager textFieldSelectionManager2 = this.this$0;
                            int transformedOffset = TextLayoutResultProxy.m1676getOffsetForPosition3MmeM6k$default(layoutResult, startPoint, false, 2, null);
                            int offset = textFieldSelectionManager2.getOffsetMapping().transformedToOriginal(transformedOffset);
                            TextFieldValue newValue = textFieldSelectionManager2.m2097createTextFieldValueFDrldGo(textFieldSelectionManager2.getValue$foundation().getText(), TextRangeKt.TextRange(offset, offset));
                            textFieldSelectionManager2.enterSelectionMode$foundation(false);
                            HapticFeedback hapticFeedBack = textFieldSelectionManager2.getHapticFeedBack();
                            if (hapticFeedBack != null) {
                                hapticFeedBack.mo6082performHapticFeedbackCdsT49E(HapticFeedbackType.INSTANCE.m6095getLongPress5zf0vsI());
                            }
                            textFieldSelectionManager2.getOnValueChange$foundation().invoke(newValue);
                            textFieldSelectionManager2.m2109setLatestSelectionOEnZFl4$foundation(TextRange.m7561boximpl(newValue.getSelection()));
                        }
                        this.isLongPressSelectionOnly = false;
                    } else {
                        if (textFieldSelectionManager.getValue$foundation().getText().length() == 0) {
                            return;
                        }
                        this.this$0.enterSelectionMode$foundation(false);
                        long adjustedStartSelection = this.this$0.m2101updateSelectionjSglsI8(TextFieldValue.m7818copy3r_uNRQ$default(this.this$0.getValue$foundation(), (AnnotatedString) null, TextRange.INSTANCE.m7578getZerod9O1mEE(), (TextRange) null, 5, (Object) null), startPoint, true, false, this.selectionAdjustmentMode, true, HapticFeedbackType.m6083boximpl(HapticFeedbackType.INSTANCE.m6095getLongPress5zf0vsI()));
                        this.this$0.dragBeginSelection = TextRange.m7561boximpl(adjustedStartSelection);
                        this.runningSelection = TextRange.m7561boximpl(adjustedStartSelection);
                    }
                    this.this$0.setHandleState(HandleState.None);
                    this.this$0.dragBeginPosition = startPoint;
                    this.this$0.m2100setCurrentDragPosition_kEHs6E(Offset.m5057boximpl(this.this$0.dragBeginPosition));
                    this.this$0.dragTotalDistance = Offset.INSTANCE.m5084getZeroF1C5BW0();
                }
            }

            /* JADX WARN: Removed duplicated region for block: B:36:0x0159  */
            @Override // androidx.compose.foundation.text.TextDragObserver
            /* JADX INFO: renamed from: onDrag-k-4lQ0M */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public void mo1640onDragk4lQ0M(long r19) {
                /*
                    Method dump skipped, instruction units count: 358
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.selection.TextFieldSelectionManager$touchSelectionObserver$1.mo1640onDragk4lQ0M(long):void");
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            public void onStop() {
                onEnd();
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            public void onCancel() {
                onEnd();
            }

            private final void onEnd() {
                this.this$0.setDraggingHandle(null);
                this.this$0.m2100setCurrentDragPosition_kEHs6E(null);
                this.selectionAdjustmentMode = SelectionAdjustment.INSTANCE.getNone();
                this.this$0.updateFloatingToolbar(true);
                TextRange textRange = this.runningSelection;
                boolean collapsed = TextRange.m7567getCollapsedimpl(textRange != null ? textRange.getPackedValue() : this.this$0.getValue$foundation().getSelection());
                this.this$0.setHandleState(collapsed ? HandleState.Cursor : HandleState.Selection);
                LegacyTextFieldState state = this.this$0.getState();
                if (state != null) {
                    state.setShowSelectionHandleStart(!collapsed && TextFieldSelectionManager_androidKt.isSelectionHandleInVisibleBound(this.this$0, true));
                }
                LegacyTextFieldState state2 = this.this$0.getState();
                if (state2 != null) {
                    state2.setShowSelectionHandleEnd(!collapsed && TextFieldSelectionManager_androidKt.isSelectionHandleInVisibleBound(this.this$0, false));
                }
                LegacyTextFieldState state3 = this.this$0.getState();
                if (state3 != null) {
                    state3.setShowCursorHandle(collapsed && TextFieldSelectionManager_androidKt.isSelectionHandleInVisibleBound(this.this$0, true));
                }
                if (this.isLongPressSelectionOnly) {
                    this.this$0.m2099maybeSuggestSelectionOEnZFl4(this.this$0.dragBeginSelection);
                }
                this.this$0.dragBeginSelection = null;
            }
        };
        this.mouseSelectionObserver = new MouseSelectionObserver() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManager$mouseSelectionObserver$1
            private TextRange initialSelection;
            private boolean isDoubleOrTripleClickSelectionOnly = true;

            /* JADX INFO: renamed from: isDoubleOrTripleClickSelectionOnly, reason: from getter */
            public final boolean getIsDoubleOrTripleClickSelectionOnly() {
                return this.isDoubleOrTripleClickSelectionOnly;
            }

            public final void setDoubleOrTripleClickSelectionOnly(boolean z) {
                this.isDoubleOrTripleClickSelectionOnly = z;
            }

            public final TextRange getInitialSelection() {
                return this.initialSelection;
            }

            public final void setInitialSelection(TextRange textRange) {
                this.initialSelection = textRange;
            }

            @Override // androidx.compose.foundation.text.selection.MouseSelectionObserver
            /* JADX INFO: renamed from: onExtend-k-4lQ0M */
            public boolean mo1945onExtendk4lQ0M(long downPosition) {
                LegacyTextFieldState state = this.this$0.getState();
                if (state == null || state.getLayoutResult() == null || !this.this$0.getEnabled()) {
                    return false;
                }
                this.this$0.previousRawDragOffset = -1;
                FocusRequester focusRequester = this.this$0.getFocusRequester();
                if (focusRequester != null) {
                    FocusRequester.m4973requestFocus3ESFkO8$default(focusRequester, 0, 1, null);
                }
                updateMouseSelection(this.this$0.getValue$foundation(), downPosition, false, SelectionAdjustment.INSTANCE.getNone());
                return true;
            }

            @Override // androidx.compose.foundation.text.selection.MouseSelectionObserver
            /* JADX INFO: renamed from: onExtendDrag-k-4lQ0M */
            public boolean mo1946onExtendDragk4lQ0M(long dragPosition) {
                LegacyTextFieldState state;
                if (this.this$0.getEnabled()) {
                    if (!(this.this$0.getValue$foundation().getText().length() == 0) && (state = this.this$0.getState()) != null && state.getLayoutResult() != null) {
                        updateMouseSelection(this.this$0.getValue$foundation(), dragPosition, false, SelectionAdjustment.INSTANCE.getNone());
                        return true;
                    }
                    return false;
                }
                return false;
            }

            @Override // androidx.compose.foundation.text.selection.MouseSelectionObserver
            /* JADX INFO: renamed from: onStart-9KIMszo */
            public boolean mo1947onStart9KIMszo(long downPosition, SelectionAdjustment adjustment, int clickCount) {
                LegacyTextFieldState state;
                if (this.this$0.getEnabled()) {
                    if (!(this.this$0.getValue$foundation().getText().length() == 0) && (state = this.this$0.getState()) != null && state.getLayoutResult() != null) {
                        FocusRequester focusRequester = this.this$0.getFocusRequester();
                        if (focusRequester != null) {
                            FocusRequester.m4973requestFocus3ESFkO8$default(focusRequester, 0, 1, null);
                        }
                        this.this$0.dragBeginPosition = downPosition;
                        this.this$0.previousRawDragOffset = -1;
                        TextFieldSelectionManager.enterSelectionMode$foundation$default(this.this$0, false, 1, null);
                        long newSelection = updateMouseSelection(this.this$0.getValue$foundation(), this.this$0.dragBeginPosition, true, adjustment);
                        if (clickCount >= 2) {
                            this.isDoubleOrTripleClickSelectionOnly = true;
                            this.initialSelection = TextRange.m7561boximpl(newSelection);
                        }
                        return true;
                    }
                    return false;
                }
                return false;
            }

            @Override // androidx.compose.foundation.text.selection.MouseSelectionObserver
            /* JADX INFO: renamed from: onDrag-3MmeM6k */
            public boolean mo1944onDrag3MmeM6k(long dragPosition, SelectionAdjustment adjustment) {
                LegacyTextFieldState state;
                if (this.this$0.getEnabled()) {
                    if (!(this.this$0.getValue$foundation().getText().length() == 0) && (state = this.this$0.getState()) != null && state.getLayoutResult() != null) {
                        updateMouseSelection(this.this$0.getValue$foundation(), dragPosition, false, adjustment);
                        return true;
                    }
                    return false;
                }
                return false;
            }

            public final long updateMouseSelection(TextFieldValue value, long currentPosition, boolean isStartOfSelection, SelectionAdjustment adjustment) {
                long newSelection = this.this$0.m2101updateSelectionjSglsI8(value, currentPosition, isStartOfSelection, false, adjustment, false, null);
                if (!TextRange.m7565equalsimpl(newSelection, this.initialSelection)) {
                    this.isDoubleOrTripleClickSelectionOnly = false;
                }
                this.this$0.setHandleState(TextRange.m7567getCollapsedimpl(newSelection) ? HandleState.Cursor : HandleState.Selection);
                return newSelection;
            }

            @Override // androidx.compose.foundation.text.selection.MouseSelectionObserver
            public void onDragDone() {
                if (this.isDoubleOrTripleClickSelectionOnly) {
                    this.this$0.m2099maybeSuggestSelectionOEnZFl4(this.initialSelection);
                }
            }
        };
    }

    public /* synthetic */ TextFieldSelectionManager(UndoManager undoManager, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : undoManager);
    }

    public final UndoManager getUndoManager() {
        return this.undoManager;
    }

    /* JADX INFO: renamed from: getOffsetMapping$foundation, reason: from getter */
    public final OffsetMapping getOffsetMapping() {
        return this.offsetMapping;
    }

    public final void setOffsetMapping$foundation(OffsetMapping offsetMapping) {
        this.offsetMapping = offsetMapping;
    }

    public final Function1<TextFieldValue, Unit> getOnValueChange$foundation() {
        return this.onValueChange;
    }

    public final void setOnValueChange$foundation(Function1<? super TextFieldValue, Unit> function1) {
        this.onValueChange = function1;
    }

    /* JADX INFO: renamed from: getState$foundation, reason: from getter */
    public final LegacyTextFieldState getState() {
        return this.state;
    }

    public final void setState$foundation(LegacyTextFieldState legacyTextFieldState) {
        this.state = legacyTextFieldState;
    }

    public final TextFieldValue getValue$foundation() {
        return this.valueState.getValue();
    }

    public final void setValue$foundation(TextFieldValue value) {
        this.valueState.setValue(value);
        this.latestSelection = TextRange.m7561boximpl(value.getSelection());
    }

    public final AnnotatedString getTransformedText$foundation() {
        TextDelegate textDelegate;
        LegacyTextFieldState legacyTextFieldState = this.state;
        if (legacyTextFieldState == null || (textDelegate = legacyTextFieldState.getTextDelegate()) == null) {
            return null;
        }
        return textDelegate.getText();
    }

    /* JADX INFO: renamed from: getVisualTransformation$foundation, reason: from getter */
    public final VisualTransformation getVisualTransformation() {
        return this.visualTransformation;
    }

    public final void setVisualTransformation$foundation(VisualTransformation visualTransformation) {
        this.visualTransformation = visualTransformation;
    }

    public final Function0<Unit> getRequestAutofillAction$foundation() {
        return this.requestAutofillAction;
    }

    public final void setRequestAutofillAction$foundation(Function0<Unit> function0) {
        this.requestAutofillAction = function0;
    }

    /* JADX INFO: renamed from: getClipboard$foundation, reason: from getter */
    public final Clipboard getClipboard() {
        return this.clipboard;
    }

    public final void setClipboard$foundation(Clipboard clipboard) {
        this.clipboard = clipboard;
    }

    /* JADX INFO: renamed from: getCoroutineScope$foundation, reason: from getter */
    public final CoroutineScope getCoroutineScope() {
        return this.coroutineScope;
    }

    public final void setCoroutineScope$foundation(CoroutineScope coroutineScope) {
        this.coroutineScope = coroutineScope;
    }

    /* JADX INFO: renamed from: getPlatformSelectionBehaviors$foundation, reason: from getter */
    public final PlatformSelectionBehaviors getPlatformSelectionBehaviors() {
        return this.platformSelectionBehaviors;
    }

    public final void setPlatformSelectionBehaviors$foundation(PlatformSelectionBehaviors platformSelectionBehaviors) {
        this.platformSelectionBehaviors = platformSelectionBehaviors;
    }

    public final TextToolbar getTextToolbar() {
        return this.textToolbar;
    }

    public final void setTextToolbar(TextToolbar textToolbar) {
        this.textToolbar = textToolbar;
    }

    public final HapticFeedback getHapticFeedBack() {
        return this.hapticFeedBack;
    }

    public final void setHapticFeedBack(HapticFeedback hapticFeedback) {
        this.hapticFeedBack = hapticFeedback;
    }

    public final FocusRequester getFocusRequester() {
        return this.focusRequester;
    }

    public final void setFocusRequester(FocusRequester focusRequester) {
        this.focusRequester = focusRequester;
    }

    public final boolean getEditable() {
        State $this$getValue$iv = this.editable;
        return ((Boolean) $this$getValue$iv.getValue()).booleanValue();
    }

    public final void setEditable(boolean z) {
        MutableState $this$setValue$iv = this.editable;
        $this$setValue$iv.setValue(Boolean.valueOf(z));
    }

    public final boolean getEnabled() {
        State $this$getValue$iv = this.enabled;
        return ((Boolean) $this$getValue$iv.getValue()).booleanValue();
    }

    public final void setEnabled(boolean z) {
        MutableState $this$setValue$iv = this.enabled;
        $this$setValue$iv.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setDraggingHandle(Handle handle) {
        MutableState $this$setValue$iv = this.draggingHandle;
        $this$setValue$iv.setValue(handle);
    }

    public final Handle getDraggingHandle() {
        State $this$getValue$iv = this.draggingHandle;
        return (Handle) $this$getValue$iv.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setCurrentDragPosition-_kEHs6E, reason: not valid java name */
    public final void m2100setCurrentDragPosition_kEHs6E(Offset offset) {
        MutableState $this$setValue$iv = this.currentDragPosition;
        $this$setValue$iv.setValue(offset);
    }

    /* JADX INFO: renamed from: getCurrentDragPosition-_m7T9-E, reason: not valid java name */
    public final Offset m2103getCurrentDragPosition_m7T9E() {
        State $this$getValue$iv = this.currentDragPosition;
        return (Offset) $this$getValue$iv.getValue();
    }

    /* JADX INFO: renamed from: getLatestSelection-MzsxiRA$foundation, reason: not valid java name and from getter */
    public final TextRange getLatestSelection() {
        return this.latestSelection;
    }

    /* JADX INFO: renamed from: setLatestSelection-OEnZFl4$foundation, reason: not valid java name */
    public final void m2109setLatestSelectionOEnZFl4$foundation(TextRange textRange) {
        this.latestSelection = textRange;
    }

    private final boolean getHasAvailableTextToPaste() {
        State $this$getValue$iv = this.hasAvailableTextToPaste;
        return ((Boolean) $this$getValue$iv.getValue()).booleanValue();
    }

    private final void setHasAvailableTextToPaste(boolean z) {
        MutableState $this$setValue$iv = this.hasAvailableTextToPaste;
        $this$setValue$iv.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: renamed from: getToolbarRequester$foundation, reason: from getter */
    public final ToolbarRequester getToolbarRequester() {
        return this.toolbarRequester;
    }

    public final void setToolbarRequester$foundation(ToolbarRequester toolbarRequester) {
        this.toolbarRequester = toolbarRequester;
    }

    public final Modifier getContextMenuAreaModifier() {
        return !getEnabled() ? Modifier.INSTANCE : TextContextMenuToolbarHandlerModifierKt.textContextMenuToolbarHandler(TextContextMenuGesturesModifierKt.showTextContextMenuOnSecondaryClick(Modifier.INSTANCE, new TextFieldSelectionManager$contextMenuAreaModifier$1(this, null)), this.toolbarRequester, new TextFieldSelectionManager$contextMenuAreaModifier$2(this, null), new TextFieldSelectionManager$contextMenuAreaModifier$3(this, null), new Function1() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManager$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TextFieldSelectionManager._get_contextMenuAreaModifier_$lambda$0(this.f$0, (LayoutCoordinates) obj);
            }
        });
    }

    static final Rect _get_contextMenuAreaModifier_$lambda$0(TextFieldSelectionManager this$0, LayoutCoordinates destinationCoordinates) {
        LayoutCoordinates localCoordinates;
        Rect rootBounds = this$0.getContentRect();
        LegacyTextFieldState legacyTextFieldState = this$0.state;
        if (legacyTextFieldState == null || (localCoordinates = legacyTextFieldState.getLayoutCoordinates()) == null) {
            return null;
        }
        return TextContextMenuToolbarHandlerModifierKt.translateRootToDestination(rootBounds, localCoordinates, destinationCoordinates);
    }

    /* JADX INFO: renamed from: getTouchSelectionObserver$foundation, reason: from getter */
    public final TextDragObserver getTouchSelectionObserver() {
        return this.touchSelectionObserver;
    }

    /* JADX INFO: renamed from: getMouseSelectionObserver$foundation, reason: from getter */
    public final MouseSelectionObserver getMouseSelectionObserver() {
        return this.mouseSelectionObserver;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: maybeSuggestSelection-OEnZFl4, reason: not valid java name */
    public final void m2099maybeSuggestSelectionOEnZFl4(TextRange selection) {
        PlatformSelectionBehaviors platformSelectionBehaviors;
        AnnotatedString transformedText$foundation;
        String text;
        CoroutineScope coroutineScope;
        if (selection == null || (platformSelectionBehaviors = this.platformSelectionBehaviors) == null || (transformedText$foundation = getTransformedText$foundation()) == null || (text = transformedText$foundation.getText()) == null) {
            return;
        }
        OffsetMapping offsetMapping = this.offsetMapping;
        long transformedSelection = TextRangeKt.TextRange(offsetMapping.originalToTransformed(TextRange.m7573getStartimpl(selection.getPackedValue())), offsetMapping.originalToTransformed(TextRange.m7568getEndimpl(selection.getPackedValue())));
        if ((text.length() > 0) && !TextRange.m7567getCollapsedimpl(transformedSelection) && (coroutineScope = this.coroutineScope) != null) {
            BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new TextFieldSelectionManager$maybeSuggestSelection$1(platformSelectionBehaviors, text, transformedSelection, selection, this, offsetMapping, null), 3, null);
        }
    }

    public final TextDragObserver handleDragObserver$foundation(final boolean isStartHandle) {
        return new TextDragObserver() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManager$handleDragObserver$1
            @Override // androidx.compose.foundation.text.TextDragObserver
            /* JADX INFO: renamed from: onDown-k-4lQ0M */
            public void mo1639onDownk4lQ0M(long point) {
                TextLayoutResultProxy layoutResult;
                this.this$0.setDraggingHandle(isStartHandle ? Handle.SelectionStart : Handle.SelectionEnd);
                long handleCoordinates = SelectionHandlesKt.m2051getAdjustedCoordinatesk4lQ0M(this.this$0.m2105getHandlePositiontuRUvjQ$foundation(isStartHandle));
                LegacyTextFieldState state = this.this$0.getState();
                if (state == null || (layoutResult = state.getLayoutResult()) == null) {
                    return;
                }
                long translatedPosition = layoutResult.m1680translateInnerToDecorationCoordinatesMKHz9U$foundation(handleCoordinates);
                this.this$0.dragBeginPosition = translatedPosition;
                this.this$0.m2100setCurrentDragPosition_kEHs6E(Offset.m5057boximpl(translatedPosition));
                this.this$0.dragTotalDistance = Offset.INSTANCE.m5084getZeroF1C5BW0();
                this.this$0.previousRawDragOffset = -1;
                LegacyTextFieldState state2 = this.this$0.getState();
                if (state2 != null) {
                    state2.setInTouchMode(true);
                }
                this.this$0.updateFloatingToolbar(false);
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            public void onUp() {
                this.this$0.setDraggingHandle(null);
                this.this$0.m2100setCurrentDragPosition_kEHs6E(null);
                this.this$0.updateFloatingToolbar(true);
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            /* JADX INFO: renamed from: onStart-3MmeM6k */
            public void mo1641onStart3MmeM6k(long startPoint, SelectionAdjustment selectionAdjustment) {
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            /* JADX INFO: renamed from: onDrag-k-4lQ0M */
            public void mo1640onDragk4lQ0M(long delta) {
                this.this$0.dragTotalDistance = Offset.m5073plusMKHz9U(this.this$0.dragTotalDistance, delta);
                this.this$0.m2100setCurrentDragPosition_kEHs6E(Offset.m5057boximpl(Offset.m5073plusMKHz9U(this.this$0.dragBeginPosition, this.this$0.dragTotalDistance)));
                TextFieldSelectionManager textFieldSelectionManager = this.this$0;
                TextFieldValue value$foundation = this.this$0.getValue$foundation();
                Offset offsetM2103getCurrentDragPosition_m7T9E = this.this$0.m2103getCurrentDragPosition_m7T9E();
                Intrinsics.checkNotNull(offsetM2103getCurrentDragPosition_m7T9E);
                textFieldSelectionManager.m2101updateSelectionjSglsI8(value$foundation, offsetM2103getCurrentDragPosition_m7T9E.m5078unboximpl(), false, isStartHandle, SelectionAdjustment.INSTANCE.getCharacterWithWordAccelerate(), true, HapticFeedbackType.m6083boximpl(HapticFeedbackType.INSTANCE.m6099getTextHandleMove5zf0vsI()));
                this.this$0.updateFloatingToolbar(false);
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            public void onStop() {
                this.this$0.setDraggingHandle(null);
                this.this$0.m2100setCurrentDragPosition_kEHs6E(null);
                this.this$0.updateFloatingToolbar(true);
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            public void onCancel() {
            }
        };
    }

    public final TextDragObserver cursorDragObserver$foundation() {
        return new TextDragObserver() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManager$cursorDragObserver$1
            @Override // androidx.compose.foundation.text.TextDragObserver
            /* JADX INFO: renamed from: onDown-k-4lQ0M */
            public void mo1639onDownk4lQ0M(long point) {
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            public void onUp() {
                this.this$0.setDraggingHandle(null);
                this.this$0.m2100setCurrentDragPosition_kEHs6E(null);
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            /* JADX INFO: renamed from: onStart-3MmeM6k */
            public void mo1641onStart3MmeM6k(long startPoint, SelectionAdjustment selectionAdjustment) {
                TextLayoutResultProxy layoutResult;
                long handleCoordinates = SelectionHandlesKt.m2051getAdjustedCoordinatesk4lQ0M(this.this$0.m2105getHandlePositiontuRUvjQ$foundation(true));
                LegacyTextFieldState state = this.this$0.getState();
                if (state == null || (layoutResult = state.getLayoutResult()) == null) {
                    return;
                }
                long translatedPosition = layoutResult.m1680translateInnerToDecorationCoordinatesMKHz9U$foundation(handleCoordinates);
                this.this$0.dragBeginPosition = translatedPosition;
                this.this$0.m2100setCurrentDragPosition_kEHs6E(Offset.m5057boximpl(translatedPosition));
                this.this$0.dragTotalDistance = Offset.INSTANCE.m5084getZeroF1C5BW0();
                this.this$0.setDraggingHandle(Handle.Cursor);
                this.this$0.updateFloatingToolbar(false);
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            /* JADX INFO: renamed from: onDrag-k-4lQ0M */
            public void mo1640onDragk4lQ0M(long delta) {
                TextLayoutResultProxy layoutResult;
                HapticFeedback hapticFeedBack;
                this.this$0.dragTotalDistance = Offset.m5073plusMKHz9U(this.this$0.dragTotalDistance, delta);
                LegacyTextFieldState state = this.this$0.getState();
                if (state != null && (layoutResult = state.getLayoutResult()) != null) {
                    TextFieldSelectionManager textFieldSelectionManager = this.this$0;
                    textFieldSelectionManager.m2100setCurrentDragPosition_kEHs6E(Offset.m5057boximpl(Offset.m5073plusMKHz9U(textFieldSelectionManager.dragBeginPosition, textFieldSelectionManager.dragTotalDistance)));
                    OffsetMapping offsetMapping = textFieldSelectionManager.getOffsetMapping();
                    Offset offsetM2103getCurrentDragPosition_m7T9E = textFieldSelectionManager.m2103getCurrentDragPosition_m7T9E();
                    Intrinsics.checkNotNull(offsetM2103getCurrentDragPosition_m7T9E);
                    int offset = offsetMapping.transformedToOriginal(TextLayoutResultProxy.m1676getOffsetForPosition3MmeM6k$default(layoutResult, offsetM2103getCurrentDragPosition_m7T9E.m5078unboximpl(), false, 2, null));
                    long newSelection = TextRangeKt.TextRange(offset, offset);
                    if (TextRange.m7566equalsimpl0(newSelection, textFieldSelectionManager.getValue$foundation().getSelection())) {
                        return;
                    }
                    LegacyTextFieldState state2 = textFieldSelectionManager.getState();
                    boolean z = false;
                    if (state2 != null && !state2.isInTouchMode()) {
                        z = true;
                    }
                    if (!z && (hapticFeedBack = textFieldSelectionManager.getHapticFeedBack()) != null) {
                        hapticFeedBack.mo6082performHapticFeedbackCdsT49E(HapticFeedbackType.INSTANCE.m6099getTextHandleMove5zf0vsI());
                    }
                    textFieldSelectionManager.getOnValueChange$foundation().invoke(textFieldSelectionManager.m2097createTextFieldValueFDrldGo(textFieldSelectionManager.getValue$foundation().getText(), newSelection));
                    textFieldSelectionManager.m2109setLatestSelectionOEnZFl4$foundation(TextRange.m7561boximpl(newSelection));
                }
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            public void onStop() {
                this.this$0.setDraggingHandle(null);
                this.this$0.m2100setCurrentDragPosition_kEHs6E(null);
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            public void onCancel() {
            }
        };
    }

    public static /* synthetic */ void enterSelectionMode$foundation$default(TextFieldSelectionManager textFieldSelectionManager, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        textFieldSelectionManager.enterSelectionMode$foundation(z);
    }

    public final void enterSelectionMode$foundation(boolean showFloatingToolbar) {
        FocusRequester focusRequester;
        LegacyTextFieldState legacyTextFieldState = this.state;
        if (((legacyTextFieldState == null || legacyTextFieldState.getHasFocus()) ? false : true) && (focusRequester = this.focusRequester) != null) {
            FocusRequester.m4973requestFocus3ESFkO8$default(focusRequester, 0, 1, null);
        }
        this.oldValue = getValue$foundation();
        updateFloatingToolbar(showFloatingToolbar);
        setHandleState(HandleState.Selection);
    }

    public final void exitSelectionMode$foundation() {
        updateFloatingToolbar(false);
        setHandleState(HandleState.None);
    }

    /* JADX INFO: renamed from: deselect-_kEHs6E$foundation$default, reason: not valid java name */
    public static /* synthetic */ void m2098deselect_kEHs6E$foundation$default(TextFieldSelectionManager textFieldSelectionManager, Offset offset, int i, Object obj) {
        if ((i & 1) != 0) {
            offset = null;
        }
        textFieldSelectionManager.m2102deselect_kEHs6E$foundation(offset);
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0077  */
    /* JADX INFO: renamed from: deselect-_kEHs6E$foundation, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void m2102deselect_kEHs6E$foundation(androidx.compose.ui.geometry.Offset r10) {
        /*
            r9 = this;
            androidx.compose.ui.text.input.TextFieldValue r0 = r9.getValue$foundation()
            long r0 = r0.getSelection()
            boolean r0 = androidx.compose.ui.text.TextRange.m7567getCollapsedimpl(r0)
            if (r0 != 0) goto L5c
            androidx.compose.foundation.text.LegacyTextFieldState r0 = r9.state
            if (r0 == 0) goto L17
            androidx.compose.foundation.text.TextLayoutResultProxy r0 = r0.getLayoutResult()
            goto L18
        L17:
            r0 = 0
        L18:
            r1 = r0
            if (r10 == 0) goto L2f
            if (r1 == 0) goto L2f
            androidx.compose.ui.text.input.OffsetMapping r0 = r9.offsetMapping
            long r2 = r10.m5078unboximpl()
            r5 = 2
            r6 = 0
            r4 = 0
            int r2 = androidx.compose.foundation.text.TextLayoutResultProxy.m1676getOffsetForPosition3MmeM6k$default(r1, r2, r4, r5, r6)
            int r0 = r0.transformedToOriginal(r2)
            goto L3b
        L2f:
            androidx.compose.ui.text.input.TextFieldValue r0 = r9.getValue$foundation()
            long r2 = r0.getSelection()
            int r0 = androidx.compose.ui.text.TextRange.m7570getMaximpl(r2)
        L3b:
            androidx.compose.ui.text.input.TextFieldValue r2 = r9.getValue$foundation()
            long r4 = androidx.compose.ui.text.TextRangeKt.TextRange(r0)
            r7 = 5
            r8 = 0
            r3 = 0
            r6 = 0
            androidx.compose.ui.text.input.TextFieldValue r2 = androidx.compose.ui.text.input.TextFieldValue.m7818copy3r_uNRQ$default(r2, r3, r4, r6, r7, r8)
            kotlin.jvm.functions.Function1<? super androidx.compose.ui.text.input.TextFieldValue, kotlin.Unit> r3 = r9.onValueChange
            r3.invoke(r2)
            long r3 = r2.getSelection()
            androidx.compose.ui.text.TextRange r3 = androidx.compose.ui.text.TextRange.m7561boximpl(r3)
            r9.latestSelection = r3
        L5c:
            r0 = 0
            if (r10 == 0) goto L77
            androidx.compose.ui.text.input.TextFieldValue r1 = r9.getValue$foundation()
            java.lang.String r1 = r1.getText()
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1
            int r1 = r1.length()
            if (r1 <= 0) goto L71
            r1 = 1
            goto L72
        L71:
            r1 = r0
        L72:
            if (r1 == 0) goto L77
            androidx.compose.foundation.text.HandleState r1 = androidx.compose.foundation.text.HandleState.Cursor
            goto L79
        L77:
            androidx.compose.foundation.text.HandleState r1 = androidx.compose.foundation.text.HandleState.None
        L79:
            r9.setHandleState(r1)
            r9.updateFloatingToolbar(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.selection.TextFieldSelectionManager.m2102deselect_kEHs6E$foundation(androidx.compose.ui.geometry.Offset):void");
    }

    /* JADX INFO: renamed from: setSelectionPreviewHighlight-5zc-tL8$foundation, reason: not valid java name */
    public final void m2110setSelectionPreviewHighlight5zctL8$foundation(long range) {
        LegacyTextFieldState legacyTextFieldState = this.state;
        if (legacyTextFieldState != null) {
            legacyTextFieldState.m1611setSelectionPreviewHighlightRange5zctL8(range);
        }
        LegacyTextFieldState legacyTextFieldState2 = this.state;
        if (legacyTextFieldState2 != null) {
            legacyTextFieldState2.m1608setDeletionPreviewHighlightRange5zctL8(TextRange.INSTANCE.m7578getZerod9O1mEE());
        }
        if (!TextRange.m7567getCollapsedimpl(range)) {
            exitSelectionMode$foundation();
        }
    }

    /* JADX INFO: renamed from: setDeletionPreviewHighlight-5zc-tL8$foundation, reason: not valid java name */
    public final void m2108setDeletionPreviewHighlight5zctL8$foundation(long range) {
        LegacyTextFieldState legacyTextFieldState = this.state;
        if (legacyTextFieldState != null) {
            legacyTextFieldState.m1608setDeletionPreviewHighlightRange5zctL8(range);
        }
        LegacyTextFieldState legacyTextFieldState2 = this.state;
        if (legacyTextFieldState2 != null) {
            legacyTextFieldState2.m1611setSelectionPreviewHighlightRange5zctL8(TextRange.INSTANCE.m7578getZerod9O1mEE());
        }
        if (!TextRange.m7567getCollapsedimpl(range)) {
            exitSelectionMode$foundation();
        }
    }

    public final void clearPreviewHighlight$foundation() {
        LegacyTextFieldState legacyTextFieldState = this.state;
        if (legacyTextFieldState != null) {
            legacyTextFieldState.m1608setDeletionPreviewHighlightRange5zctL8(TextRange.INSTANCE.m7578getZerod9O1mEE());
        }
        LegacyTextFieldState legacyTextFieldState2 = this.state;
        if (legacyTextFieldState2 != null) {
            legacyTextFieldState2.m1611setSelectionPreviewHighlightRange5zctL8(TextRange.INSTANCE.m7578getZerod9O1mEE());
        }
    }

    /* JADX INFO: renamed from: getTextToolbarShownViaProvider$foundation, reason: from getter */
    public final boolean getTextToolbarShownViaProvider() {
        return this.textToolbarShownViaProvider;
    }

    public final void setTextToolbarShownViaProvider$foundation(boolean z) {
        this.textToolbarShownViaProvider = z;
    }

    public final boolean getTextToolbarShown$foundation() {
        if (ComposeFoundationFlags.isNewContextMenuEnabled) {
            return this.textToolbarShownViaProvider;
        }
        TextToolbar textToolbar = this.textToolbar;
        return (textToolbar != null ? textToolbar.getStatus() : null) == TextToolbarStatus.Shown;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isPassword() {
        return this.visualTransformation instanceof PasswordVisualTransformation;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean getHasSelection() {
        return !TextRange.m7567getCollapsedimpl(getValue$foundation().getSelection());
    }

    public final boolean canShowCopyMenuItem$foundation() {
        if (getHasSelection() && !isPassword()) {
            Clipboard clipboard = this.clipboard;
            if (clipboard != null && ClipboardUtils_androidKt.isWriteSupported(clipboard)) {
                return true;
            }
        }
        return false;
    }

    public final boolean isCopyAllowed$foundation() {
        return getHasSelection() && !isPassword();
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object updateClipboardEntry$foundation(kotlin.coroutines.Continuation<? super kotlin.Unit> r8) {
        /*
            r7 = this;
            boolean r0 = r8 instanceof androidx.compose.foundation.text.selection.TextFieldSelectionManager$updateClipboardEntry$1
            if (r0 == 0) goto L14
            r0 = r8
            androidx.compose.foundation.text.selection.TextFieldSelectionManager$updateClipboardEntry$1 r0 = (androidx.compose.foundation.text.selection.TextFieldSelectionManager$updateClipboardEntry$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r1 = r0.label
            int r1 = r1 - r2
            r0.label = r1
            goto L19
        L14:
            androidx.compose.foundation.text.selection.TextFieldSelectionManager$updateClipboardEntry$1 r0 = new androidx.compose.foundation.text.selection.TextFieldSelectionManager$updateClipboardEntry$1
            r0.<init>(r7, r8)
        L19:
            java.lang.Object r1 = r0.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r0.label
            switch(r3) {
                case 0: goto L36;
                case 1: goto L2d;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L2d:
            java.lang.Object r2 = r0.L$0
            androidx.compose.foundation.text.selection.TextFieldSelectionManager r2 = (androidx.compose.foundation.text.selection.TextFieldSelectionManager) r2
            kotlin.ResultKt.throwOnFailure(r1)
            r4 = r1
            goto L55
        L36:
            kotlin.ResultKt.throwOnFailure(r1)
            r3 = r7
            androidx.compose.ui.platform.Clipboard r4 = r3.clipboard
            r5 = 0
            r6 = 1
            if (r4 == 0) goto L47
            boolean r4 = androidx.compose.foundation.internal.ClipboardUtils_androidKt.isReadSupported(r4)
            if (r4 != r6) goto L47
            r5 = r6
        L47:
            if (r5 == 0) goto L5e
            r0.L$0 = r3
            r0.label = r6
            java.lang.Object r4 = androidx.compose.foundation.text.selection.TextFieldSelectionManager_androidKt.hasAvailableTextToPaste(r3, r0)
            if (r4 != r2) goto L54
            return r2
        L54:
            r2 = r3
        L55:
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            boolean r3 = r4.booleanValue()
            r2.setHasAvailableTextToPaste(r3)
        L5e:
            kotlin.Unit r2 = kotlin.Unit.INSTANCE
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.selection.TextFieldSelectionManager.updateClipboardEntry$foundation(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Pair<String, TextRange> getContextTextAndSelection() {
        String text;
        TextRange textRange;
        AnnotatedString transformedText$foundation = getTransformedText$foundation();
        if (transformedText$foundation == null || (text = transformedText$foundation.getText()) == null || (textRange = this.latestSelection) == null) {
            return null;
        }
        long selection = textRange.getPackedValue();
        return new Pair<>(text, TextRange.m7561boximpl(TextRangeKt.TextRange(this.offsetMapping.originalToTransformed(TextRange.m7573getStartimpl(selection)), this.offsetMapping.originalToTransformed(TextRange.m7568getEndimpl(selection)))));
    }

    public final boolean canShowPasteMenuItem$foundation() {
        if (!getEditable() || !getHasAvailableTextToPaste()) {
            return false;
        }
        Clipboard clipboard = this.clipboard;
        return clipboard != null && ClipboardUtils_androidKt.isReadSupported(clipboard);
    }

    public final boolean isPasteAllowed$foundation() {
        return getEditable();
    }

    public final boolean canShowCutMenuItem$foundation() {
        if (getHasSelection() && getEditable() && !isPassword()) {
            Clipboard clipboard = this.clipboard;
            if (clipboard != null && ClipboardUtils_androidKt.isWriteSupported(clipboard)) {
                return true;
            }
        }
        return false;
    }

    public final boolean isCutAllowed$foundation() {
        return getHasSelection() && getEditable() && !isPassword();
    }

    public final boolean canShowSelectAllMenuItem$foundation() {
        return TextRange.m7569getLengthimpl(getValue$foundation().getSelection()) != getValue$foundation().getText().length();
    }

    public final boolean canShowAutofillMenuItem$foundation() {
        return getEditable() && TextRange.m7567getCollapsedimpl(getValue$foundation().getSelection());
    }

    public static /* synthetic */ Job copy$foundation$default(TextFieldSelectionManager textFieldSelectionManager, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        return textFieldSelectionManager.copy$foundation(z);
    }

    public final Job copy$foundation(boolean cancelSelection) {
        CoroutineScope coroutineScope = this.coroutineScope;
        if (coroutineScope != null) {
            return BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, CoroutineStart.UNDISPATCHED, new TextFieldSelectionManager$copy$1(this, cancelSelection, null), 1, null);
        }
        return null;
    }

    public static /* synthetic */ AnnotatedString copyWithResult$foundation$default(TextFieldSelectionManager textFieldSelectionManager, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        return textFieldSelectionManager.copyWithResult$foundation(z);
    }

    public final AnnotatedString copyWithResult$foundation(boolean cancelSelection) {
        if (!(getHasSelection() && !isPassword())) {
            return null;
        }
        AnnotatedString selectedText = TextFieldValueKt.getSelectedText(getValue$foundation());
        if (!cancelSelection) {
            return selectedText;
        }
        int newCursorOffset = TextRange.m7570getMaximpl(getValue$foundation().getSelection());
        TextFieldValue newValue = m2097createTextFieldValueFDrldGo(getValue$foundation().getText(), TextRangeKt.TextRange(newCursorOffset, newCursorOffset));
        this.onValueChange.invoke(newValue);
        setHandleState(HandleState.None);
        return selectedText;
    }

    public final Job paste$foundation() {
        CoroutineScope coroutineScope = this.coroutineScope;
        if (coroutineScope != null) {
            return BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, CoroutineStart.UNDISPATCHED, new TextFieldSelectionManager$paste$1(this, null), 1, null);
        }
        return null;
    }

    public final void paste$foundation(AnnotatedString text) {
        if (getEditable()) {
            AnnotatedString newText = TextFieldValueKt.getTextBeforeSelection(getValue$foundation(), getValue$foundation().getText().length()).plus(text).plus(TextFieldValueKt.getTextAfterSelection(getValue$foundation(), getValue$foundation().getText().length()));
            int newCursorOffset = TextRange.m7571getMinimpl(getValue$foundation().getSelection()) + text.length();
            TextFieldValue newValue = m2097createTextFieldValueFDrldGo(newText, TextRangeKt.TextRange(newCursorOffset, newCursorOffset));
            this.onValueChange.invoke(newValue);
            setHandleState(HandleState.None);
            UndoManager undoManager = this.undoManager;
            if (undoManager != null) {
                undoManager.forceNextSnapshot();
            }
        }
    }

    public final Job cut$foundation() {
        CoroutineScope coroutineScope = this.coroutineScope;
        if (coroutineScope != null) {
            return BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, CoroutineStart.UNDISPATCHED, new TextFieldSelectionManager$cut$1(this, null), 1, null);
        }
        return null;
    }

    public final AnnotatedString cutWithResult$foundation() {
        if (!(getHasSelection() && getEditable() && !isPassword())) {
            return null;
        }
        AnnotatedString selectedText = TextFieldValueKt.getSelectedText(getValue$foundation());
        AnnotatedString newText = TextFieldValueKt.getTextBeforeSelection(getValue$foundation(), getValue$foundation().getText().length()).plus(TextFieldValueKt.getTextAfterSelection(getValue$foundation(), getValue$foundation().getText().length()));
        int newCursorOffset = TextRange.m7571getMinimpl(getValue$foundation().getSelection());
        TextFieldValue newValue = m2097createTextFieldValueFDrldGo(newText, TextRangeKt.TextRange(newCursorOffset, newCursorOffset));
        this.onValueChange.invoke(newValue);
        setHandleState(HandleState.None);
        UndoManager undoManager = this.undoManager;
        if (undoManager != null) {
            undoManager.forceNextSnapshot();
        }
        return selectedText;
    }

    public final void selectAll$foundation() {
        TextFieldValue newValue = m2097createTextFieldValueFDrldGo(getValue$foundation().getText(), TextRangeKt.TextRange(0, getValue$foundation().getText().length()));
        this.onValueChange.invoke(newValue);
        this.latestSelection = TextRange.m7561boximpl(newValue.getSelection());
        this.oldValue = TextFieldValue.m7818copy3r_uNRQ$default(this.oldValue, (AnnotatedString) null, newValue.getSelection(), (TextRange) null, 5, (Object) null);
        enterSelectionMode$foundation(true);
    }

    public final void autofill$foundation() {
        Function0<Unit> function0 = this.requestAutofillAction;
        if (function0 != null) {
            function0.invoke();
        }
    }

    /* JADX INFO: renamed from: getHandlePosition-tuRUvjQ$foundation, reason: not valid java name */
    public final long m2105getHandlePositiontuRUvjQ$foundation(boolean isStartHandle) {
        TextLayoutResultProxy layoutResult;
        TextLayoutResult textLayoutResult;
        LegacyTextFieldState legacyTextFieldState = this.state;
        if (legacyTextFieldState == null || (layoutResult = legacyTextFieldState.getLayoutResult()) == null || (textLayoutResult = layoutResult.getValue()) == null) {
            return Offset.INSTANCE.m5083getUnspecifiedF1C5BW0();
        }
        AnnotatedString transformedText = getTransformedText$foundation();
        if (transformedText == null) {
            return Offset.INSTANCE.m5083getUnspecifiedF1C5BW0();
        }
        String layoutInputText = textLayoutResult.getLayoutInput().getText().getText();
        if (!Intrinsics.areEqual(transformedText.getText(), layoutInputText)) {
            return Offset.INSTANCE.m5083getUnspecifiedF1C5BW0();
        }
        long selection = getValue$foundation().getSelection();
        int offset = isStartHandle ? TextRange.m7573getStartimpl(selection) : TextRange.m7568getEndimpl(selection);
        return TextSelectionDelegateKt.getSelectionHandleCoordinates(textLayoutResult, this.offsetMapping.originalToTransformed(offset), isStartHandle, TextRange.m7572getReversedimpl(getValue$foundation().getSelection()));
    }

    public final float getHandleLineHeight$foundation(boolean isStartHandle) {
        TextLayoutResultProxy layoutResult;
        TextLayoutResult value;
        long selection = getValue$foundation().getSelection();
        int offset = isStartHandle ? TextRange.m7573getStartimpl(selection) : TextRange.m7568getEndimpl(selection);
        LegacyTextFieldState legacyTextFieldState = this.state;
        if (legacyTextFieldState == null || (layoutResult = legacyTextFieldState.getLayoutResult()) == null || (value = layoutResult.getValue()) == null) {
            return 0.0f;
        }
        return TextLayoutHelperKt.getLineHeight(value, offset);
    }

    /* JADX INFO: renamed from: getCursorPosition-tuRUvjQ$foundation, reason: not valid java name */
    public final long m2104getCursorPositiontuRUvjQ$foundation(Density density) {
        int offset = this.offsetMapping.originalToTransformed(TextRange.m7573getStartimpl(getValue$foundation().getSelection()));
        LegacyTextFieldState legacyTextFieldState = this.state;
        TextLayoutResultProxy layoutResult = legacyTextFieldState != null ? legacyTextFieldState.getLayoutResult() : null;
        Intrinsics.checkNotNull(layoutResult);
        TextLayoutResult layoutResult2 = layoutResult.getValue();
        Rect cursorRect = layoutResult2.getCursorRect(RangesKt.coerceIn(offset, 0, layoutResult2.getLayoutInput().getText().length()));
        float x = cursorRect.getLeft() + (density.mo432toPx0680j_4(TextFieldCursor_androidKt.getDefaultCursorThickness()) / 2.0f);
        float y$iv = cursorRect.getBottom();
        long v1$iv$iv = Float.floatToRawIntBits(x);
        long v2$iv$iv = Float.floatToRawIntBits(y$iv);
        return Offset.m5060constructorimpl((v1$iv$iv << 32) | (v2$iv$iv & 4294967295L));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateFloatingToolbar(boolean show) {
        LegacyTextFieldState legacyTextFieldState = this.state;
        if (legacyTextFieldState != null) {
            legacyTextFieldState.setShowFloatingToolbar(show);
        }
        if (show) {
            showSelectionToolbar$foundation();
        } else {
            hideSelectionToolbar$foundation();
        }
    }

    public final void showSelectionToolbar$foundation() {
        Snapshot.Companion this_$iv = Snapshot.INSTANCE;
        Snapshot previousSnapshot$iv = this_$iv.getCurrentThreadSnapshot();
        Function1<Object, Unit> readObserver = previousSnapshot$iv != null ? previousSnapshot$iv.getReadObserver() : null;
        Snapshot newSnapshot$iv = this_$iv.makeCurrentNonObservable(previousSnapshot$iv);
        try {
            if (getEnabled()) {
                LegacyTextFieldState legacyTextFieldState = this.state;
                boolean z = false;
                if (legacyTextFieldState != null && !legacyTextFieldState.isInTouchMode()) {
                    z = true;
                }
                if (!z) {
                    Unit unit = Unit.INSTANCE;
                    this_$iv.restoreNonObservable(previousSnapshot$iv, newSnapshot$iv, readObserver);
                    if (ComposeFoundationFlags.isNewContextMenuEnabled) {
                        this.toolbarRequester.show();
                    } else {
                        showSelectionToolbarViaTextToolbar();
                    }
                }
            }
        } finally {
            this_$iv.restoreNonObservable(previousSnapshot$iv, newSnapshot$iv, readObserver);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.selection.TextFieldSelectionManager$showSelectionToolbarViaTextToolbar$1, reason: invalid class name */
    /* JADX INFO: compiled from: TextFieldSelectionManager.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text.selection.TextFieldSelectionManager$showSelectionToolbarViaTextToolbar$1", f = "TextFieldSelectionManager.kt", i = {}, l = {1083}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return TextFieldSelectionManager.this.new AnonymousClass1(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Function0<Unit> function0;
            Function0<Unit> function02;
            Function0<Unit> function03;
            Function0<Unit> function04;
            Function0<Unit> function05;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    this.label = 1;
                    if (TextFieldSelectionManager.this.updateClipboardEntry$foundation(this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            Snapshot.Companion this_$iv = Snapshot.INSTANCE;
            final TextFieldSelectionManager textFieldSelectionManager = TextFieldSelectionManager.this;
            Snapshot previousSnapshot$iv = this_$iv.getCurrentThreadSnapshot();
            Function1<Object, Unit> readObserver = previousSnapshot$iv != null ? previousSnapshot$iv.getReadObserver() : null;
            Snapshot newSnapshot$iv = this_$iv.makeCurrentNonObservable(previousSnapshot$iv);
            try {
                if (textFieldSelectionManager.canShowCopyMenuItem$foundation()) {
                    function0 = new Function0() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManager$showSelectionToolbarViaTextToolbar$1$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return TextFieldSelectionManager.AnonymousClass1.invokeSuspend$lambda$0$0(textFieldSelectionManager);
                        }
                    };
                } else {
                    function0 = null;
                }
                Function0<Unit> function06 = function0;
                if (textFieldSelectionManager.canShowCutMenuItem$foundation()) {
                    function02 = new Function0() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManager$showSelectionToolbarViaTextToolbar$1$$ExternalSyntheticLambda1
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return TextFieldSelectionManager.AnonymousClass1.invokeSuspend$lambda$0$1(textFieldSelectionManager);
                        }
                    };
                } else {
                    function02 = null;
                }
                if (textFieldSelectionManager.canShowPasteMenuItem$foundation()) {
                    function03 = new Function0() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManager$showSelectionToolbarViaTextToolbar$1$$ExternalSyntheticLambda2
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return TextFieldSelectionManager.AnonymousClass1.invokeSuspend$lambda$0$2(textFieldSelectionManager);
                        }
                    };
                } else {
                    function03 = null;
                }
                if (textFieldSelectionManager.canShowSelectAllMenuItem$foundation()) {
                    function04 = new Function0() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManager$showSelectionToolbarViaTextToolbar$1$$ExternalSyntheticLambda3
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return TextFieldSelectionManager.AnonymousClass1.invokeSuspend$lambda$0$3(textFieldSelectionManager);
                        }
                    };
                } else {
                    function04 = null;
                }
                if (textFieldSelectionManager.canShowAutofillMenuItem$foundation()) {
                    function05 = new Function0() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManager$showSelectionToolbarViaTextToolbar$1$$ExternalSyntheticLambda4
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return TextFieldSelectionManager.AnonymousClass1.invokeSuspend$lambda$0$4(textFieldSelectionManager);
                        }
                    };
                } else {
                    function05 = null;
                }
                TextToolbar textToolbar = textFieldSelectionManager.getTextToolbar();
                if (textToolbar != null) {
                    textToolbar.showMenu(textFieldSelectionManager.getContentRect(), function06, function03, function02, function04, function05);
                }
                Unit unit = Unit.INSTANCE;
                this_$iv.restoreNonObservable(previousSnapshot$iv, newSnapshot$iv, readObserver);
                return Unit.INSTANCE;
            } catch (Throwable th) {
                this_$iv.restoreNonObservable(previousSnapshot$iv, newSnapshot$iv, readObserver);
                throw th;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit invokeSuspend$lambda$0$0(TextFieldSelectionManager this$0) {
            CoroutineScope coroutineScope = this$0.getCoroutineScope();
            if (coroutineScope != null) {
                BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, CoroutineStart.UNDISPATCHED, new TextFieldSelectionManager$showSelectionToolbarViaTextToolbar$1$1$copy$1$1(this$0, null), 1, null);
            }
            this$0.hideSelectionToolbar$foundation();
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit invokeSuspend$lambda$0$1(TextFieldSelectionManager this$0) {
            CoroutineScope coroutineScope = this$0.getCoroutineScope();
            if (coroutineScope != null) {
                BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, CoroutineStart.UNDISPATCHED, new TextFieldSelectionManager$showSelectionToolbarViaTextToolbar$1$1$cut$1$1(this$0, null), 1, null);
            }
            this$0.hideSelectionToolbar$foundation();
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit invokeSuspend$lambda$0$2(TextFieldSelectionManager this$0) {
            CoroutineScope coroutineScope = this$0.getCoroutineScope();
            if (coroutineScope != null) {
                BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, CoroutineStart.UNDISPATCHED, new TextFieldSelectionManager$showSelectionToolbarViaTextToolbar$1$1$paste$1$1(this$0, null), 1, null);
            }
            this$0.hideSelectionToolbar$foundation();
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit invokeSuspend$lambda$0$3(TextFieldSelectionManager this$0) {
            this$0.selectAll$foundation();
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit invokeSuspend$lambda$0$4(TextFieldSelectionManager this$0) {
            this$0.autofill$foundation();
            return Unit.INSTANCE;
        }
    }

    private final Job showSelectionToolbarViaTextToolbar() {
        CoroutineScope coroutineScope = this.coroutineScope;
        if (coroutineScope != null) {
            return BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, CoroutineStart.UNDISPATCHED, new AnonymousClass1(null), 1, null);
        }
        return null;
    }

    public final void hideSelectionToolbar$foundation() {
        TextToolbar textToolbar;
        if (ComposeFoundationFlags.isNewContextMenuEnabled) {
            this.toolbarRequester.hide();
            return;
        }
        TextToolbar textToolbar2 = this.textToolbar;
        if ((textToolbar2 != null ? textToolbar2.getStatus() : null) != TextToolbarStatus.Shown || (textToolbar = this.textToolbar) == null) {
            return;
        }
        textToolbar.hide();
    }

    /* JADX INFO: renamed from: selectWordAtPositionIfNotAlreadySelected-k-4lQ0M, reason: not valid java name */
    public final void m2107selectWordAtPositionIfNotAlreadySelectedk4lQ0M(long position) {
        TextLayoutResultProxy layoutResult;
        LegacyTextFieldState legacyTextFieldState = this.state;
        if (legacyTextFieldState != null && (layoutResult = legacyTextFieldState.getLayoutResult()) != null) {
            boolean isClickedPositionInsideSelection = TextLayoutHelperKt.m1674isPositionInsideSelectionuaM50fQ(layoutResult.getValue(), layoutResult.m1679translateDecorationToInnerCoordinatesMKHz9U$foundation(position), TextRange.m7561boximpl(getValue$foundation().getSelection()));
            if (!isClickedPositionInsideSelection) {
                m2101updateSelectionjSglsI8(getValue$foundation(), position, true, false, SelectionAdjustment.INSTANCE.getWord(), false, null);
            }
        }
    }

    public final boolean isTextChanged$foundation() {
        return !Intrinsics.areEqual(this.oldValue.getText(), getValue$foundation().getText());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Rect getContentRect() {
        LegacyTextFieldState it;
        float x$iv;
        long j;
        char c;
        float startTop;
        LegacyTextFieldState legacyTextFieldState;
        float endTop;
        LayoutCoordinates layoutCoordinates;
        TextLayoutResult value;
        Rect cursorRect;
        LayoutCoordinates layoutCoordinates2;
        TextLayoutResult value2;
        Rect cursorRect2;
        LayoutCoordinates layoutCoordinates3;
        LayoutCoordinates layoutCoordinates4;
        LegacyTextFieldState it2 = this.state;
        if (it2 != null) {
            if (it2.getIsLayoutResultStale()) {
                it2 = null;
            }
            if (it2 != null) {
                int transformedStart = this.offsetMapping.originalToTransformed(TextRange.m7573getStartimpl(getValue$foundation().getSelection()));
                int transformedEnd = this.offsetMapping.originalToTransformed(TextRange.m7568getEndimpl(getValue$foundation().getSelection()));
                LegacyTextFieldState legacyTextFieldState2 = this.state;
                long startOffset = (legacyTextFieldState2 == null || (layoutCoordinates4 = legacyTextFieldState2.getLayoutCoordinates()) == null) ? Offset.INSTANCE.m5084getZeroF1C5BW0() : layoutCoordinates4.mo6794localToRootMKHz9U(m2105getHandlePositiontuRUvjQ$foundation(true));
                LegacyTextFieldState legacyTextFieldState3 = this.state;
                long endOffset = (legacyTextFieldState3 == null || (layoutCoordinates3 = legacyTextFieldState3.getLayoutCoordinates()) == null) ? Offset.INSTANCE.m5084getZeroF1C5BW0() : layoutCoordinates3.mo6794localToRootMKHz9U(m2105getHandlePositiontuRUvjQ$foundation(false));
                LegacyTextFieldState legacyTextFieldState4 = this.state;
                if (legacyTextFieldState4 != null && (layoutCoordinates2 = legacyTextFieldState4.getLayoutCoordinates()) != null) {
                    TextLayoutResultProxy layoutResult = it2.getLayoutResult();
                    float y$iv = (layoutResult == null || (value2 = layoutResult.getValue()) == null || (cursorRect2 = value2.getCursorRect(transformedStart)) == null) ? 0.0f : cursorRect2.getTop();
                    float val2$iv$iv = y$iv;
                    j = 4294967295L;
                    long v1$iv$iv = Float.floatToRawIntBits(0.0f);
                    c = ' ';
                    x$iv = 0.0f;
                    long v2$iv$iv = Float.floatToRawIntBits(val2$iv$iv);
                    long arg0$iv = layoutCoordinates2.mo6794localToRootMKHz9U(Offset.m5060constructorimpl((v1$iv$iv << 32) | (v2$iv$iv & 4294967295L)));
                    it = it2;
                    int bits$iv$iv$iv = (int) (arg0$iv & 4294967295L);
                    startTop = Float.intBitsToFloat(bits$iv$iv$iv);
                    legacyTextFieldState = this.state;
                    if (legacyTextFieldState != null) {
                    }
                    endTop = x$iv;
                    long arg0$iv2 = startOffset;
                    int bits$iv$iv$iv2 = (int) (arg0$iv2 >> c);
                    long arg0$iv3 = endOffset;
                    int bits$iv$iv$iv3 = (int) (arg0$iv3 >> c);
                    float left = Math.min(Float.intBitsToFloat(bits$iv$iv$iv2), Float.intBitsToFloat(bits$iv$iv$iv3));
                    long arg0$iv4 = startOffset;
                    int bits$iv$iv$iv4 = (int) (arg0$iv4 >> c);
                    long arg0$iv5 = endOffset;
                    int bits$iv$iv$iv5 = (int) (arg0$iv5 >> c);
                    float right = Math.max(Float.intBitsToFloat(bits$iv$iv$iv4), Float.intBitsToFloat(bits$iv$iv$iv5));
                    float top = Math.min(startTop, endTop);
                    int bits$iv$iv$iv6 = (int) (startOffset & j);
                    long arg0$iv6 = endOffset;
                    int bits$iv$iv$iv7 = (int) (arg0$iv6 & j);
                    float bottom = Math.max(Float.intBitsToFloat(bits$iv$iv$iv6), Float.intBitsToFloat(bits$iv$iv$iv7)) + (Dp.m8150constructorimpl(25) * it.getTextDelegate().getDensity().getDensity());
                    return new Rect(left, top, right, bottom);
                }
                it = it2;
                x$iv = 0.0f;
                j = 4294967295L;
                c = ' ';
                startTop = x$iv;
                legacyTextFieldState = this.state;
                if (legacyTextFieldState != null || (layoutCoordinates = legacyTextFieldState.getLayoutCoordinates()) == null) {
                    endTop = x$iv;
                    long arg0$iv22 = startOffset;
                    int bits$iv$iv$iv22 = (int) (arg0$iv22 >> c);
                    long arg0$iv32 = endOffset;
                    int bits$iv$iv$iv32 = (int) (arg0$iv32 >> c);
                    float left2 = Math.min(Float.intBitsToFloat(bits$iv$iv$iv22), Float.intBitsToFloat(bits$iv$iv$iv32));
                    long arg0$iv42 = startOffset;
                    int bits$iv$iv$iv42 = (int) (arg0$iv42 >> c);
                    long arg0$iv52 = endOffset;
                    int bits$iv$iv$iv52 = (int) (arg0$iv52 >> c);
                    float right2 = Math.max(Float.intBitsToFloat(bits$iv$iv$iv42), Float.intBitsToFloat(bits$iv$iv$iv52));
                    float top2 = Math.min(startTop, endTop);
                    int bits$iv$iv$iv62 = (int) (startOffset & j);
                    long arg0$iv62 = endOffset;
                    int bits$iv$iv$iv72 = (int) (arg0$iv62 & j);
                    float bottom2 = Math.max(Float.intBitsToFloat(bits$iv$iv$iv62), Float.intBitsToFloat(bits$iv$iv$iv72)) + (Dp.m8150constructorimpl(25) * it.getTextDelegate().getDensity().getDensity());
                    return new Rect(left2, top2, right2, bottom2);
                }
                TextLayoutResultProxy layoutResult2 = it.getLayoutResult();
                float y$iv2 = (layoutResult2 == null || (value = layoutResult2.getValue()) == null || (cursorRect = value.getCursorRect(transformedEnd)) == null) ? x$iv : cursorRect.getTop();
                float val2$iv$iv2 = y$iv2;
                float val1$iv$iv = x$iv;
                long v1$iv$iv2 = Float.floatToRawIntBits(val1$iv$iv);
                long v1$iv$iv3 = Float.floatToRawIntBits(val2$iv$iv2);
                long v2$iv$iv2 = (v1$iv$iv2 << c) | (v1$iv$iv3 & j);
                long arg0$iv7 = layoutCoordinates.mo6794localToRootMKHz9U(Offset.m5060constructorimpl(v2$iv$iv2));
                int bits$iv$iv$iv8 = (int) (arg0$iv7 & j);
                endTop = Float.intBitsToFloat(bits$iv$iv$iv8);
                long arg0$iv222 = startOffset;
                int bits$iv$iv$iv222 = (int) (arg0$iv222 >> c);
                long arg0$iv322 = endOffset;
                int bits$iv$iv$iv322 = (int) (arg0$iv322 >> c);
                float left22 = Math.min(Float.intBitsToFloat(bits$iv$iv$iv222), Float.intBitsToFloat(bits$iv$iv$iv322));
                long arg0$iv422 = startOffset;
                int bits$iv$iv$iv422 = (int) (arg0$iv422 >> c);
                long arg0$iv522 = endOffset;
                int bits$iv$iv$iv522 = (int) (arg0$iv522 >> c);
                float right22 = Math.max(Float.intBitsToFloat(bits$iv$iv$iv422), Float.intBitsToFloat(bits$iv$iv$iv522));
                float top22 = Math.min(startTop, endTop);
                int bits$iv$iv$iv622 = (int) (startOffset & j);
                long arg0$iv622 = endOffset;
                int bits$iv$iv$iv722 = (int) (arg0$iv622 & j);
                float bottom22 = Math.max(Float.intBitsToFloat(bits$iv$iv$iv622), Float.intBitsToFloat(bits$iv$iv$iv722)) + (Dp.m8150constructorimpl(25) * it.getTextDelegate().getDensity().getDensity());
                return new Rect(left22, top22, right22, bottom22);
            }
        }
        return Rect.INSTANCE.getZero();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0119  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x011d  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0156  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0163  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x016a  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0182  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0199  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x01a7  */
    /* JADX INFO: renamed from: updateSelection-jSglsI8, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final long m2101updateSelectionjSglsI8(androidx.compose.ui.text.input.TextFieldValue r26, long r27, boolean r29, boolean r30, androidx.compose.foundation.text.selection.SelectionAdjustment r31, boolean r32, androidx.compose.ui.hapticfeedback.HapticFeedbackType r33) {
        /*
            Method dump skipped, instruction units count: 439
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.selection.TextFieldSelectionManager.m2101updateSelectionjSglsI8(androidx.compose.ui.text.input.TextFieldValue, long, boolean, boolean, androidx.compose.foundation.text.selection.SelectionAdjustment, boolean, androidx.compose.ui.hapticfeedback.HapticFeedbackType):long");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setHandleState(HandleState handleState) {
        LegacyTextFieldState it = this.state;
        if (it != null) {
            if (it.getHandleState() == handleState) {
                it = null;
            }
            if (it != null) {
                it.setHandleState(handleState);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: createTextFieldValue-FDrldGo, reason: not valid java name */
    public final TextFieldValue m2097createTextFieldValueFDrldGo(AnnotatedString annotatedString, long selection) {
        return new TextFieldValue(annotatedString, selection, (TextRange) null, 4, (DefaultConstructorMarker) null);
    }
}
