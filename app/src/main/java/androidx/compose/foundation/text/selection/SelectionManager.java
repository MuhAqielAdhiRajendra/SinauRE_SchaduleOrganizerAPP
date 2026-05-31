package androidx.compose.foundation.text.selection;

import androidx.autofill.HintConstants;
import androidx.collection.LongIntMapKt;
import androidx.collection.LongObjectMap;
import androidx.collection.LongObjectMapKt;
import androidx.collection.MutableLongIntMap;
import androidx.collection.MutableLongObjectMap;
import androidx.compose.foundation.ComposeFoundationFlags;
import androidx.compose.foundation.FocusableKt;
import androidx.compose.foundation.gestures.ForEachGestureKt;
import androidx.compose.foundation.internal.InlineClassHelperKt;
import androidx.compose.foundation.text.Handle;
import androidx.compose.foundation.text.TextDragObserver;
import androidx.compose.foundation.text.TextLayoutHelperKt;
import androidx.compose.foundation.text.contextmenu.modifier.TextContextMenuGesturesModifierKt;
import androidx.compose.foundation.text.contextmenu.modifier.TextContextMenuToolbarHandlerModifierKt;
import androidx.compose.foundation.text.contextmenu.modifier.ToolbarRequester;
import androidx.compose.foundation.text.contextmenu.modifier.ToolbarRequesterImpl;
import androidx.compose.foundation.text.input.internal.TextLayoutStateKt;
import androidx.compose.foundation.text.selection.Selection;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.focus.FocusChangedModifierKt;
import androidx.compose.ui.focus.FocusRequester;
import androidx.compose.ui.focus.FocusRequesterModifierKt;
import androidx.compose.ui.focus.FocusState;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.hapticfeedback.HapticFeedback;
import androidx.compose.ui.hapticfeedback.HapticFeedbackType;
import androidx.compose.ui.input.key.KeyEvent;
import androidx.compose.ui.input.key.KeyInputModifierKt;
import androidx.compose.ui.input.pointer.AwaitPointerEventScope;
import androidx.compose.ui.input.pointer.PointerInputEventHandler;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LayoutCoordinatesKt;
import androidx.compose.ui.layout.OnGloballyPositionedModifierKt;
import androidx.compose.ui.platform.TextToolbar;
import androidx.compose.ui.platform.TextToolbarStatus;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.view.PointerIconCompat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.comparisons.ComparisonsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.functions.Function6;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: SelectionManager.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000ê\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u001f\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0014\b\u0001\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\u009a\u0001\u001a\u00020\u0017H\u0002J\u001c\u0010\u009b\u0001\u001a\u0005\u0018\u00010\u009c\u00012\b\u0010\u009d\u0001\u001a\u00030\u009e\u0001H\u0000¢\u0006\u0003\b\u009f\u0001J\t\u0010 \u0001\u001a\u00020\u0017H\u0002J\u000f\u0010¡\u0001\u001a\u00020SH\u0000¢\u0006\u0003\b¢\u0001J:\u0010£\u0001\u001a\u0018\u0012\u0006\u0012\u0004\u0018\u00010\b\u0012\u000b\u0012\t\u0012\u0004\u0012\u00020\b0¥\u00010¤\u00012\b\u0010¦\u0001\u001a\u00030§\u00012\t\u0010¨\u0001\u001a\u0004\u0018\u00010\bH\u0000¢\u0006\u0003\b©\u0001J\u000f\u0010ª\u0001\u001a\u00020\u0010H\u0000¢\u0006\u0003\b«\u0001J\u000f\u0010¬\u0001\u001a\u00020\u0017H\u0000¢\u0006\u0003\b\u00ad\u0001J\u000f\u0010®\u0001\u001a\u00020\u0010H\u0000¢\u0006\u0003\b¯\u0001J\u000f\u0010°\u0001\u001a\u00020\u0010H\u0000¢\u0006\u0003\b±\u0001J\u0011\u0010²\u0001\u001a\u0004\u0018\u00010$H\u0000¢\u0006\u0003\b³\u0001J\u001f\u0010´\u0001\u001a\u0012\u0012\u0004\u0012\u00020$\u0012\u0005\u0012\u00030µ\u0001\u0018\u00010¤\u0001H\u0000¢\u0006\u0003\b¶\u0001J\u0083\u0001\u0010·\u0001\u001a\u00020\u00172q\b\u0004\u0010¸\u0001\u001aj\u0012\u0017\u0012\u00150§\u0001¢\u0006\u000f\bº\u0001\u0012\n\b»\u0001\u0012\u0005\b\b(¦\u0001\u0012\u0016\u0012\u00140$¢\u0006\u000f\bº\u0001\u0012\n\b»\u0001\u0012\u0005\b\b(¼\u0001\u0012\u0016\u0012\u00140µ\u0001¢\u0006\u000e\bº\u0001\u0012\t\b»\u0001\u0012\u0004\b\b(\n\u0012\u0016\u0012\u00140\u0010¢\u0006\u000f\bº\u0001\u0012\n\b»\u0001\u0012\u0005\b\b(½\u0001\u0012\u0004\u0012\u00020\u00100¹\u0001H\u0080\b¢\u0006\u0003\b¾\u0001J\u000f\u0010¿\u0001\u001a\u00020\u0017H\u0000¢\u0006\u0003\bÀ\u0001J\t\u0010Ä\u0001\u001a\u00020\u0017H\u0002J\t\u0010Å\u0001\u001a\u00020\u0017H\u0002J\u000f\u0010Æ\u0001\u001a\u00020\u0010H\u0000¢\u0006\u0003\bÇ\u0001J\t\u0010È\u0001\u001a\u00020\u0017H\u0002J\u000b\u0010É\u0001\u001a\u0004\u0018\u00010JH\u0002J\u0007\u0010Ê\u0001\u001a\u00020\u0017J\u0011\u0010Ë\u0001\u001a\u00030Ì\u00012\u0007\u0010Í\u0001\u001a\u00020\u0010J\r\u0010Î\u0001\u001a\u00020F*\u00020FH\u0002J\u001d\u0010Ï\u0001\u001a\u00020F*\u00020F2\u000e\u0010¸\u0001\u001a\t\u0012\u0004\u0012\u00020\u00170Ð\u0001H\u0002J$\u0010Ñ\u0001\u001a\u00020R2\u0007\u0010Ò\u0001\u001a\u00020S2\u0007\u0010Ó\u0001\u001a\u00020RH\u0002¢\u0006\u0006\bÔ\u0001\u0010Õ\u0001J.\u0010Ö\u0001\u001a\u00020\u00172\u0007\u0010×\u0001\u001a\u00020R2\u0007\u0010Í\u0001\u001a\u00020\u00102\b\u0010Ø\u0001\u001a\u00030Ù\u0001H\u0002¢\u0006\u0006\bÚ\u0001\u0010Û\u0001J8\u0010Ü\u0001\u001a\u00020\u00102\t\u0010Ý\u0001\u001a\u0004\u0018\u00010R2\u0006\u0010Q\u001a\u00020R2\u0007\u0010Í\u0001\u001a\u00020\u00102\b\u0010Ø\u0001\u001a\u00030Ù\u0001H\u0000¢\u0006\u0006\bÞ\u0001\u0010ß\u0001J7\u0010Ü\u0001\u001a\u00020\u00102\u0007\u0010×\u0001\u001a\u00020R2\u0007\u0010à\u0001\u001a\u00020R2\u0007\u0010Í\u0001\u001a\u00020\u00102\b\u0010Ø\u0001\u001a\u00030Ù\u0001H\u0000¢\u0006\u0006\bá\u0001\u0010â\u0001J0\u0010ã\u0001\u001a\u0005\u0018\u00010\u0087\u00012\u0007\u0010×\u0001\u001a\u00020R2\u0007\u0010à\u0001\u001a\u00020R2\u0007\u0010Í\u0001\u001a\u00020\u0010H\u0002¢\u0006\u0006\bä\u0001\u0010å\u0001J\u001c\u0010æ\u0001\u001a\u00020\u00172\b\u0010ç\u0001\u001a\u00030\u0087\u00012\u0007\u0010è\u0001\u001a\u00020\bH\u0002J\u000f\u0010é\u0001\u001a\u00020\u0010H\u0001¢\u0006\u0003\bê\u0001J\u0018\u0010ë\u0001\u001a\u00020\u00172\u0007\u0010×\u0001\u001a\u00020R¢\u0006\u0005\bì\u0001\u0010cR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R(\u0010\n\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\b8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010\u0011\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u00108F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R@\u0010\u0018\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\b\u0012\u0004\u0012\u00020\u00170\u00162\u0014\u0010\u0015\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\b\u0012\u0004\u0012\u00020\u00170\u0016@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001c\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R(\u0010#\u001a\u0010\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u001a\"\u0004\b&\u0010\u001cR\u001c\u0010'\u001a\u0004\u0018\u00010(X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R$\u0010-\u001a\u00020.8\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b/\u00100\u001a\u0004\b1\u00102\"\u0004\b3\u00104R\u001a\u00105\u001a\u000206X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b7\u00108\"\u0004\b9\u0010:R+\u0010<\u001a\u00020\u00102\u0006\u0010;\u001a\u00020\u00108F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b?\u0010@\u001a\u0004\b=\u0010\u0012\"\u0004\b>\u0010\u0014R\u0014\u0010A\u001a\u00020\u00108BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bA\u0010\u0012R\u001a\u0010B\u001a\u00020\u0010X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bC\u0010\u0012\"\u0004\bD\u0010\u0014R\u0011\u0010E\u001a\u00020F8F¢\u0006\u0006\u001a\u0004\bG\u0010HR\u001d\u0010I\u001a\u0004\u0018\u00010J8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bM\u0010N\u001a\u0004\bK\u0010LR\u0011\u0010O\u001a\u00020F8F¢\u0006\u0006\u001a\u0004\bP\u0010HR\u0010\u0010Q\u001a\u0004\u0018\u00010RX\u0082\u000e¢\u0006\u0002\n\u0000R(\u0010T\u001a\u0004\u0018\u00010S2\b\u0010\t\u001a\u0004\u0018\u00010S@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bU\u0010V\"\u0004\bW\u0010XR+\u0010Y\u001a\u00020\u00172\u0006\u0010;\u001a\u00020\u00178B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b^\u0010@\u001a\u0004\bZ\u0010[\"\u0004\b\\\u0010]R+\u0010_\u001a\u00020R2\u0006\u0010;\u001a\u00020R8@@BX\u0080\u008e\u0002¢\u0006\u0012\n\u0004\bd\u0010@\u001a\u0004\b`\u0010a\"\u0004\bb\u0010cR+\u0010e\u001a\u00020R2\u0006\u0010;\u001a\u00020R8@@BX\u0080\u008e\u0002¢\u0006\u0012\n\u0004\bh\u0010@\u001a\u0004\bf\u0010a\"\u0004\bg\u0010cR/\u0010i\u001a\u0004\u0018\u00010R2\b\u0010;\u001a\u0004\u0018\u00010R8F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bn\u0010@\u001a\u0004\bj\u0010k\"\u0004\bl\u0010mR/\u0010o\u001a\u0004\u0018\u00010R2\b\u0010;\u001a\u0004\u0018\u00010R8F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\br\u0010@\u001a\u0004\bp\u0010k\"\u0004\bq\u0010mR/\u0010t\u001a\u0004\u0018\u00010s2\b\u0010;\u001a\u0004\u0018\u00010s8F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\by\u0010@\u001a\u0004\bu\u0010v\"\u0004\bw\u0010xR\u0011\u0010z\u001a\u00020{8F¢\u0006\u0006\u001a\u0004\b|\u0010}R\u0011\u0010~\u001a\u00020{8F¢\u0006\u0006\u001a\u0004\b\u007f\u0010}R3\u0010\u0080\u0001\u001a\u0004\u0018\u00010R2\b\u0010;\u001a\u0004\u0018\u00010R8F@BX\u0086\u008e\u0002¢\u0006\u0015\n\u0005\b\u0083\u0001\u0010@\u001a\u0005\b\u0081\u0001\u0010k\"\u0005\b\u0082\u0001\u0010mR\u0016\u0010\u0084\u0001\u001a\u00020\u00108BX\u0082\u0004¢\u0006\u0007\u001a\u0005\b\u0085\u0001\u0010\u0012R-\u0010\u0086\u0001\u001a\u0005\u0018\u00010\u0087\u00018\u0000@\u0000X\u0081\u000e¢\u0006\u0019\n\u0000\u0012\u0005\b\u0088\u0001\u00100\u001a\u0006\b\u0089\u0001\u0010\u008a\u0001\"\u0006\b\u008b\u0001\u0010\u008c\u0001R\u000f\u0010\u008d\u0001\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010\u008e\u0001\u001a\u0005\u0018\u00010\u008f\u0001X\u0080\u000e¢\u0006\u0012\n\u0000\u001a\u0006\b\u0090\u0001\u0010\u0091\u0001\"\u0006\b\u0092\u0001\u0010\u0093\u0001R\"\u0010\u0094\u0001\u001a\u0005\u0018\u00010\u0095\u0001X\u0080\u000e¢\u0006\u0012\n\u0000\u001a\u0006\b\u0096\u0001\u0010\u0097\u0001\"\u0006\b\u0098\u0001\u0010\u0099\u0001R'\u0010Á\u0001\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0010@@X\u0080\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bÂ\u0001\u0010\u0012\"\u0005\bÃ\u0001\u0010\u0014¨\u0006í\u0001"}, d2 = {"Landroidx/compose/foundation/text/selection/SelectionManager;", "", "selectionRegistrar", "Landroidx/compose/foundation/text/selection/SelectionRegistrarImpl;", "<init>", "(Landroidx/compose/foundation/text/selection/SelectionRegistrarImpl;)V", "_selection", "Landroidx/compose/runtime/MutableState;", "Landroidx/compose/foundation/text/selection/Selection;", "value", "selection", "getSelection", "()Landroidx/compose/foundation/text/selection/Selection;", "setSelection", "(Landroidx/compose/foundation/text/selection/Selection;)V", "_isInTouchMode", "", "isInTouchMode", "()Z", "setInTouchMode", "(Z)V", "newOnSelectionChange", "Lkotlin/Function1;", "", "onSelectionChange", "getOnSelectionChange", "()Lkotlin/jvm/functions/Function1;", "setOnSelectionChange", "(Lkotlin/jvm/functions/Function1;)V", "hapticFeedBack", "Landroidx/compose/ui/hapticfeedback/HapticFeedback;", "getHapticFeedBack", "()Landroidx/compose/ui/hapticfeedback/HapticFeedback;", "setHapticFeedBack", "(Landroidx/compose/ui/hapticfeedback/HapticFeedback;)V", "onCopyHandler", "Landroidx/compose/ui/text/AnnotatedString;", "getOnCopyHandler", "setOnCopyHandler", "textToolbar", "Landroidx/compose/ui/platform/TextToolbar;", "getTextToolbar", "()Landroidx/compose/ui/platform/TextToolbar;", "setTextToolbar", "(Landroidx/compose/ui/platform/TextToolbar;)V", "toolbarRequester", "Landroidx/compose/foundation/text/contextmenu/modifier/ToolbarRequester;", "getToolbarRequester$foundation$annotations", "()V", "getToolbarRequester$foundation", "()Landroidx/compose/foundation/text/contextmenu/modifier/ToolbarRequester;", "setToolbarRequester$foundation", "(Landroidx/compose/foundation/text/contextmenu/modifier/ToolbarRequester;)V", "focusRequester", "Landroidx/compose/ui/focus/FocusRequester;", "getFocusRequester", "()Landroidx/compose/ui/focus/FocusRequester;", "setFocusRequester", "(Landroidx/compose/ui/focus/FocusRequester;)V", "<set-?>", "hasFocus", "getHasFocus", "setHasFocus", "hasFocus$delegate", "Landroidx/compose/runtime/MutableState;", "isDraggingInProgress", "shouldIgnoreCopyKeyEvent", "getShouldIgnoreCopyKeyEvent$foundation", "setShouldIgnoreCopyKeyEvent$foundation", "modifier", "Landroidx/compose/ui/Modifier;", "getModifier", "()Landroidx/compose/ui/Modifier;", "derivedContentRect", "Landroidx/compose/ui/geometry/Rect;", "getDerivedContentRect", "()Landroidx/compose/ui/geometry/Rect;", "derivedContentRect$delegate", "Landroidx/compose/runtime/State;", "contextMenuAreaModifier", "getContextMenuAreaModifier", "previousPosition", "Landroidx/compose/ui/geometry/Offset;", "Landroidx/compose/ui/layout/LayoutCoordinates;", "containerLayoutCoordinates", "getContainerLayoutCoordinates", "()Landroidx/compose/ui/layout/LayoutCoordinates;", "setContainerLayoutCoordinates", "(Landroidx/compose/ui/layout/LayoutCoordinates;)V", "positionChangeState", "getPositionChangeState", "()Lkotlin/Unit;", "setPositionChangeState", "(Lkotlin/Unit;)V", "positionChangeState$delegate", "dragBeginPosition", "getDragBeginPosition-F1C5BW0$foundation", "()J", "setDragBeginPosition-k-4lQ0M", "(J)V", "dragBeginPosition$delegate", "dragTotalDistance", "getDragTotalDistance-F1C5BW0$foundation", "setDragTotalDistance-k-4lQ0M", "dragTotalDistance$delegate", "startHandlePosition", "getStartHandlePosition-_m7T9-E", "()Landroidx/compose/ui/geometry/Offset;", "setStartHandlePosition-_kEHs6E", "(Landroidx/compose/ui/geometry/Offset;)V", "startHandlePosition$delegate", "endHandlePosition", "getEndHandlePosition-_m7T9-E", "setEndHandlePosition-_kEHs6E", "endHandlePosition$delegate", "Landroidx/compose/foundation/text/Handle;", "draggingHandle", "getDraggingHandle", "()Landroidx/compose/foundation/text/Handle;", "setDraggingHandle", "(Landroidx/compose/foundation/text/Handle;)V", "draggingHandle$delegate", "startHandleLineHeight", "", "getStartHandleLineHeight", "()F", "endHandleLineHeight", "getEndHandleLineHeight", "currentDragPosition", "getCurrentDragPosition-_m7T9-E", "setCurrentDragPosition-_kEHs6E", "currentDragPosition$delegate", "shouldShowMagnifier", "getShouldShowMagnifier", "previousSelectionLayout", "Landroidx/compose/foundation/text/selection/SelectionLayout;", "getPreviousSelectionLayout$foundation$annotations", "getPreviousSelectionLayout$foundation", "()Landroidx/compose/foundation/text/selection/SelectionLayout;", "setPreviousSelectionLayout$foundation", "(Landroidx/compose/foundation/text/selection/SelectionLayout;)V", "isLongPressOrClickSelection", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "getCoroutineScope$foundation", "()Lkotlinx/coroutines/CoroutineScope;", "setCoroutineScope$foundation", "(Lkotlinx/coroutines/CoroutineScope;)V", "platformSelectionBehaviors", "Landroidx/compose/foundation/text/selection/PlatformSelectionBehaviors;", "getPlatformSelectionBehaviors$foundation", "()Landroidx/compose/foundation/text/selection/PlatformSelectionBehaviors;", "setPlatformSelectionBehaviors$foundation", "(Landroidx/compose/foundation/text/selection/PlatformSelectionBehaviors;)V", "suggestSelectionForLongPressOrDoubleClick", "getAnchorSelectable", "Landroidx/compose/foundation/text/selection/Selectable;", "anchor", "Landroidx/compose/foundation/text/selection/Selection$AnchorInfo;", "getAnchorSelectable$foundation", "updateHandleOffsets", "requireContainerCoordinates", "requireContainerCoordinates$foundation", "selectAllInSelectable", "Lkotlin/Pair;", "Landroidx/collection/LongObjectMap;", "selectableId", "", "previousSelection", "selectAllInSelectable$foundation", "isEntireContainerSelected", "isEntireContainerSelected$foundation", "selectAll", "selectAll$foundation", "isTriviallyCollapsedSelection", "isTriviallyCollapsedSelection$foundation", "isNonEmptySelection", "isNonEmptySelection$foundation", "getSelectedText", "getSelectedText$foundation", "getContextTextAndSelection", "Landroidx/compose/ui/text/TextRange;", "getContextTextAndSelection$foundation", "forEachSelectableWithSelection", "block", "Lkotlin/Function4;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "text", "isLastSelectable", "forEachSelectableWithSelection$foundation", "copy", "copy$foundation", "showToolbar", "getShowToolbar$foundation", "setShowToolbar$foundation", "toolbarCopy", "updateSelectionToolbar", "canCopy", "canCopy$foundation", "updateSelectionTextToolbar", "getContentRect", "onRelease", "handleDragObserver", "Landroidx/compose/foundation/text/TextDragObserver;", "isStartHandle", "addContextMenuComponents", "onClearSelectionRequested", "Lkotlin/Function0;", "convertToContainerCoordinates", "layoutCoordinates", TypedValues.CycleType.S_WAVE_OFFSET, "convertToContainerCoordinates-R5De75A", "(Landroidx/compose/ui/layout/LayoutCoordinates;J)J", "startSelection", "position", "adjustment", "Landroidx/compose/foundation/text/selection/SelectionAdjustment;", "startSelection-9KIMszo", "(JZLandroidx/compose/foundation/text/selection/SelectionAdjustment;)V", "updateSelection", "newPosition", "updateSelection-qNKwrvQ$foundation", "(Landroidx/compose/ui/geometry/Offset;JZLandroidx/compose/foundation/text/selection/SelectionAdjustment;)Z", "previousHandlePosition", "updateSelection-jyLRC_s$foundation", "(JJZLandroidx/compose/foundation/text/selection/SelectionAdjustment;)Z", "getSelectionLayout", "getSelectionLayout-Wko1d7g", "(JJZ)Landroidx/compose/foundation/text/selection/SelectionLayout;", "selectionChanged", "selectionLayout", "newSelection", "shouldPerformHaptics", "shouldPerformHaptics$foundation", "selectWordAtPositionIfNotAlreadySelected", "selectWordAtPositionIfNotAlreadySelected-k-4lQ0M", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class SelectionManager {
    public static final int $stable = 8;
    private LayoutCoordinates containerLayoutCoordinates;
    private CoroutineScope coroutineScope;
    private HapticFeedback hapticFeedBack;
    private boolean isLongPressOrClickSelection;
    private Function1<? super AnnotatedString, Unit> onCopyHandler;
    private PlatformSelectionBehaviors platformSelectionBehaviors;
    private Offset previousPosition;
    private SelectionLayout previousSelectionLayout;
    private final SelectionRegistrarImpl selectionRegistrar;
    private boolean shouldIgnoreCopyKeyEvent;
    private boolean showToolbar;
    private TextToolbar textToolbar;
    private final MutableState<Selection> _selection = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
    private final MutableState<Boolean> _isInTouchMode = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(true, null, 2, null);
    private Function1<? super Selection, Unit> onSelectionChange = new Function1() { // from class: androidx.compose.foundation.text.selection.SelectionManager$$ExternalSyntheticLambda5
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return SelectionManager.onSelectionChange$lambda$0(this.f$0, (Selection) obj);
        }
    };
    private ToolbarRequester toolbarRequester = new ToolbarRequesterImpl();
    private FocusRequester focusRequester = new FocusRequester();

    /* JADX INFO: renamed from: hasFocus$delegate, reason: from kotlin metadata */
    private final MutableState hasFocus = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);

    /* JADX INFO: renamed from: derivedContentRect$delegate, reason: from kotlin metadata */
    private final State derivedContentRect = SnapshotStateKt.derivedStateOf(new Function0() { // from class: androidx.compose.foundation.text.selection.SelectionManager$$ExternalSyntheticLambda6
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return this.f$0.getContentRect();
        }
    });

    /* JADX INFO: renamed from: positionChangeState$delegate, reason: from kotlin metadata */
    private final MutableState positionChangeState = SnapshotStateKt.mutableStateOf(Unit.INSTANCE, SnapshotStateKt.neverEqualPolicy());

    /* JADX INFO: renamed from: dragBeginPosition$delegate, reason: from kotlin metadata */
    private final MutableState dragBeginPosition = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Offset.m5057boximpl(Offset.INSTANCE.m5084getZeroF1C5BW0()), null, 2, null);

    /* JADX INFO: renamed from: dragTotalDistance$delegate, reason: from kotlin metadata */
    private final MutableState dragTotalDistance = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Offset.m5057boximpl(Offset.INSTANCE.m5084getZeroF1C5BW0()), null, 2, null);

    /* JADX INFO: renamed from: startHandlePosition$delegate, reason: from kotlin metadata */
    private final MutableState startHandlePosition = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);

    /* JADX INFO: renamed from: endHandlePosition$delegate, reason: from kotlin metadata */
    private final MutableState endHandlePosition = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);

    /* JADX INFO: renamed from: draggingHandle$delegate, reason: from kotlin metadata */
    private final MutableState draggingHandle = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);

    /* JADX INFO: renamed from: currentDragPosition$delegate, reason: from kotlin metadata */
    private final MutableState currentDragPosition = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);

    public static /* synthetic */ void getPreviousSelectionLayout$foundation$annotations() {
    }

    public static /* synthetic */ void getToolbarRequester$foundation$annotations() {
    }

    public SelectionManager(SelectionRegistrarImpl selectionRegistrar) {
        this.selectionRegistrar = selectionRegistrar;
        this.selectionRegistrar.setOnPositionChangeCallback$foundation(new Function1() { // from class: androidx.compose.foundation.text.selection.SelectionManager$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SelectionManager._init_$lambda$0(this.f$0, ((Long) obj).longValue());
            }
        });
        this.selectionRegistrar.setOnSelectionUpdateStartCallback$foundation(new Function4() { // from class: androidx.compose.foundation.text.selection.SelectionManager$$ExternalSyntheticLambda8
            @Override // kotlin.jvm.functions.Function4
            public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
                return SelectionManager._init_$lambda$1(this.f$0, ((Boolean) obj).booleanValue(), (LayoutCoordinates) obj2, (Offset) obj3, (SelectionAdjustment) obj4);
            }
        });
        this.selectionRegistrar.setOnSelectionUpdateSelectAll$foundation(new Function2() { // from class: androidx.compose.foundation.text.selection.SelectionManager$$ExternalSyntheticLambda9
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return SelectionManager._init_$lambda$2(this.f$0, ((Boolean) obj).booleanValue(), ((Long) obj2).longValue());
            }
        });
        this.selectionRegistrar.setOnSelectionUpdateCallback$foundation(new Function6() { // from class: androidx.compose.foundation.text.selection.SelectionManager$$ExternalSyntheticLambda10
            @Override // kotlin.jvm.functions.Function6
            public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
                return Boolean.valueOf(SelectionManager._init_$lambda$3(this.f$0, ((Boolean) obj).booleanValue(), (LayoutCoordinates) obj2, (Offset) obj3, (Offset) obj4, ((Boolean) obj5).booleanValue(), (SelectionAdjustment) obj6));
            }
        });
        this.selectionRegistrar.setOnSelectionUpdateEndCallback$foundation(new Function0() { // from class: androidx.compose.foundation.text.selection.SelectionManager$$ExternalSyntheticLambda11
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return SelectionManager._init_$lambda$4(this.f$0);
            }
        });
        this.selectionRegistrar.setOnSelectableChangeCallback$foundation(new Function1() { // from class: androidx.compose.foundation.text.selection.SelectionManager$$ExternalSyntheticLambda12
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SelectionManager._init_$lambda$5(this.f$0, ((Long) obj).longValue());
            }
        });
        this.selectionRegistrar.setAfterSelectableUnsubscribe$foundation(new Function1() { // from class: androidx.compose.foundation.text.selection.SelectionManager$$ExternalSyntheticLambda13
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SelectionManager._init_$lambda$6(this.f$0, ((Long) obj).longValue());
            }
        });
    }

    public final Selection getSelection() {
        return this._selection.getValue();
    }

    public final void setSelection(Selection value) {
        this._selection.setValue(value);
        if (value != null) {
            updateHandleOffsets();
        }
    }

    public final boolean isInTouchMode() {
        return this._isInTouchMode.getValue().booleanValue();
    }

    public final void setInTouchMode(boolean value) {
        if (this._isInTouchMode.getValue().booleanValue() != value) {
            this._isInTouchMode.setValue(Boolean.valueOf(value));
            updateSelectionToolbar();
        }
    }

    static final Unit onSelectionChange$lambda$0(SelectionManager this$0, Selection it) {
        this$0.setSelection(it);
        return Unit.INSTANCE;
    }

    public final Function1<Selection, Unit> getOnSelectionChange() {
        return this.onSelectionChange;
    }

    public final void setOnSelectionChange(final Function1<? super Selection, Unit> function1) {
        this.onSelectionChange = new Function1() { // from class: androidx.compose.foundation.text.selection.SelectionManager$$ExternalSyntheticLambda14
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SelectionManager._set_onSelectionChange_$lambda$0(this.f$0, function1, (Selection) obj);
            }
        };
    }

    static final Unit _set_onSelectionChange_$lambda$0(SelectionManager this$0, Function1 $newOnSelectionChange, Selection newSelection) {
        this$0.setSelection(newSelection);
        $newOnSelectionChange.invoke(newSelection);
        return Unit.INSTANCE;
    }

    public final HapticFeedback getHapticFeedBack() {
        return this.hapticFeedBack;
    }

    public final void setHapticFeedBack(HapticFeedback hapticFeedback) {
        this.hapticFeedBack = hapticFeedback;
    }

    public final Function1<AnnotatedString, Unit> getOnCopyHandler() {
        return this.onCopyHandler;
    }

    public final void setOnCopyHandler(Function1<? super AnnotatedString, Unit> function1) {
        this.onCopyHandler = function1;
    }

    public final TextToolbar getTextToolbar() {
        return this.textToolbar;
    }

    public final void setTextToolbar(TextToolbar textToolbar) {
        this.textToolbar = textToolbar;
    }

    /* JADX INFO: renamed from: getToolbarRequester$foundation, reason: from getter */
    public final ToolbarRequester getToolbarRequester() {
        return this.toolbarRequester;
    }

    public final void setToolbarRequester$foundation(ToolbarRequester toolbarRequester) {
        this.toolbarRequester = toolbarRequester;
    }

    public final FocusRequester getFocusRequester() {
        return this.focusRequester;
    }

    public final void setFocusRequester(FocusRequester focusRequester) {
        this.focusRequester = focusRequester;
    }

    public final boolean getHasFocus() {
        State $this$getValue$iv = this.hasFocus;
        return ((Boolean) $this$getValue$iv.getValue()).booleanValue();
    }

    public final void setHasFocus(boolean z) {
        MutableState $this$setValue$iv = this.hasFocus;
        $this$setValue$iv.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isDraggingInProgress() {
        return getDraggingHandle() != null;
    }

    /* JADX INFO: renamed from: getShouldIgnoreCopyKeyEvent$foundation, reason: from getter */
    public final boolean getShouldIgnoreCopyKeyEvent() {
        return this.shouldIgnoreCopyKeyEvent;
    }

    public final void setShouldIgnoreCopyKeyEvent$foundation(boolean z) {
        this.shouldIgnoreCopyKeyEvent = z;
    }

    static final Unit _get_modifier_$lambda$0(SelectionManager this$0) {
        this$0.onRelease();
        return Unit.INSTANCE;
    }

    public final Modifier getModifier() {
        return addContextMenuComponents(KeyInputModifierKt.onKeyEvent(SelectionGesturesKt.updateSelectionTouchMode(FocusableKt.focusable$default(FocusChangedModifierKt.onFocusChanged(FocusRequesterModifierKt.focusRequester(OnGloballyPositionedModifierKt.onGloballyPositioned(onClearSelectionRequested(Modifier.INSTANCE, new Function0() { // from class: androidx.compose.foundation.text.selection.SelectionManager$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return SelectionManager._get_modifier_$lambda$0(this.f$0);
            }
        }), new Function1() { // from class: androidx.compose.foundation.text.selection.SelectionManager$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SelectionManager._get_modifier_$lambda$1(this.f$0, (LayoutCoordinates) obj);
            }
        }), this.focusRequester), new Function1() { // from class: androidx.compose.foundation.text.selection.SelectionManager$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SelectionManager._get_modifier_$lambda$2(this.f$0, (FocusState) obj);
            }
        }), false, null, 3, null), new Function1() { // from class: androidx.compose.foundation.text.selection.SelectionManager$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SelectionManager._get_modifier_$lambda$3(this.f$0, ((Boolean) obj).booleanValue());
            }
        }), new Function1<KeyEvent, Boolean>() { // from class: androidx.compose.foundation.text.selection.SelectionManager$modifier$5
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Boolean invoke(KeyEvent keyEvent) {
                return m2076invokeZmokQxo(keyEvent.m6471unboximpl());
            }

            /* JADX INFO: renamed from: invoke-ZmokQxo, reason: not valid java name */
            public final Boolean m2076invokeZmokQxo(android.view.KeyEvent it) {
                boolean z;
                if (!this.this$0.getShouldIgnoreCopyKeyEvent() && SelectionManager_androidKt.m2082isCopyKeyEventZmokQxo(it)) {
                    this.this$0.copy$foundation();
                    z = true;
                } else {
                    z = false;
                }
                return Boolean.valueOf(z);
            }
        }).then(getShouldShowMagnifier() ? SelectionManager_androidKt.selectionMagnifier(Modifier.INSTANCE, this) : Modifier.INSTANCE));
    }

    static final Unit _get_modifier_$lambda$1(SelectionManager this$0, LayoutCoordinates it) {
        this$0.setContainerLayoutCoordinates(it);
        return Unit.INSTANCE;
    }

    static final Unit _get_modifier_$lambda$2(SelectionManager this$0, FocusState focusState) {
        if (!focusState.getHasFocus() && this$0.getHasFocus()) {
            this$0.onRelease();
        }
        this$0.setHasFocus(focusState.getHasFocus());
        return Unit.INSTANCE;
    }

    static final Unit _get_modifier_$lambda$3(SelectionManager this$0, boolean it) {
        this$0.setInTouchMode(it);
        return Unit.INSTANCE;
    }

    private final Rect getDerivedContentRect() {
        State $this$getValue$iv = this.derivedContentRect;
        return (Rect) $this$getValue$iv.getValue();
    }

    public final Modifier getContextMenuAreaModifier() {
        return TextContextMenuToolbarHandlerModifierKt.textContextMenuToolbarHandler$default(TextContextMenuGesturesModifierKt.showTextContextMenuOnSecondaryClick(Modifier.INSTANCE, new SelectionManager$contextMenuAreaModifier$1(this, null)), this.toolbarRequester, new SelectionManager$contextMenuAreaModifier$2(this, null), null, new Function1() { // from class: androidx.compose.foundation.text.selection.SelectionManager$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SelectionManager._get_contextMenuAreaModifier_$lambda$0(this.f$0, (LayoutCoordinates) obj);
            }
        }, 4, null);
    }

    static final Rect _get_contextMenuAreaModifier_$lambda$0(SelectionManager this$0, LayoutCoordinates destinationCoordinates) {
        Rect rootBounds = this$0.getDerivedContentRect();
        if (rootBounds == null) {
            return null;
        }
        LayoutCoordinates localCoordinates = this$0.containerLayoutCoordinates;
        if (localCoordinates != null) {
            return TextContextMenuToolbarHandlerModifierKt.translateRootToDestination(rootBounds, localCoordinates, destinationCoordinates);
        }
        InlineClassHelperKt.throwIllegalStateExceptionForNullCheck("Required value was null.");
        throw new KotlinNothingValueException();
    }

    public final LayoutCoordinates getContainerLayoutCoordinates() {
        return this.containerLayoutCoordinates;
    }

    public final void setContainerLayoutCoordinates(LayoutCoordinates value) {
        this.containerLayoutCoordinates = value;
        if (getHasFocus() && getSelection() != null) {
            Offset positionInWindow = value != null ? Offset.m5057boximpl(LayoutCoordinatesKt.positionInWindow(value)) : null;
            if (!Intrinsics.areEqual(this.previousPosition, positionInWindow)) {
                this.previousPosition = positionInWindow;
                updateHandleOffsets();
                updateSelectionToolbar();
            }
        }
    }

    private final Unit getPositionChangeState() {
        State $this$getValue$iv = this.positionChangeState;
        $this$getValue$iv.getValue();
        return Unit.INSTANCE;
    }

    private final void setPositionChangeState(Unit unit) {
        MutableState $this$setValue$iv = this.positionChangeState;
        $this$setValue$iv.setValue(unit);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setDragBeginPosition-k-4lQ0M, reason: not valid java name */
    public final void m2062setDragBeginPositionk4lQ0M(long j) {
        MutableState $this$setValue$iv = this.dragBeginPosition;
        $this$setValue$iv.setValue(Offset.m5057boximpl(j));
    }

    /* JADX INFO: renamed from: getDragBeginPosition-F1C5BW0$foundation, reason: not valid java name */
    public final long m2068getDragBeginPositionF1C5BW0$foundation() {
        State $this$getValue$iv = this.dragBeginPosition;
        return ((Offset) $this$getValue$iv.getValue()).m5078unboximpl();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setDragTotalDistance-k-4lQ0M, reason: not valid java name */
    public final void m2063setDragTotalDistancek4lQ0M(long j) {
        MutableState $this$setValue$iv = this.dragTotalDistance;
        $this$setValue$iv.setValue(Offset.m5057boximpl(j));
    }

    /* JADX INFO: renamed from: getDragTotalDistance-F1C5BW0$foundation, reason: not valid java name */
    public final long m2069getDragTotalDistanceF1C5BW0$foundation() {
        State $this$getValue$iv = this.dragTotalDistance;
        return ((Offset) $this$getValue$iv.getValue()).m5078unboximpl();
    }

    /* JADX INFO: renamed from: setStartHandlePosition-_kEHs6E, reason: not valid java name */
    private final void m2065setStartHandlePosition_kEHs6E(Offset offset) {
        MutableState $this$setValue$iv = this.startHandlePosition;
        $this$setValue$iv.setValue(offset);
    }

    /* JADX INFO: renamed from: getStartHandlePosition-_m7T9-E, reason: not valid java name */
    public final Offset m2071getStartHandlePosition_m7T9E() {
        State $this$getValue$iv = this.startHandlePosition;
        return (Offset) $this$getValue$iv.getValue();
    }

    /* JADX INFO: renamed from: setEndHandlePosition-_kEHs6E, reason: not valid java name */
    private final void m2064setEndHandlePosition_kEHs6E(Offset offset) {
        MutableState $this$setValue$iv = this.endHandlePosition;
        $this$setValue$iv.setValue(offset);
    }

    /* JADX INFO: renamed from: getEndHandlePosition-_m7T9-E, reason: not valid java name */
    public final Offset m2070getEndHandlePosition_m7T9E() {
        State $this$getValue$iv = this.endHandlePosition;
        return (Offset) $this$getValue$iv.getValue();
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

    public final float getStartHandleLineHeight() {
        Selection selection = getSelection();
        if (selection == null) {
            return 0.0f;
        }
        Selection.AnchorInfo p0 = selection.getStart();
        Selectable selectable = getAnchorSelectable$foundation(p0);
        if (selectable == null) {
            return 0.0f;
        }
        return selectable.getLineHeight(selection.getStart().getOffset());
    }

    public final float getEndHandleLineHeight() {
        Selection selection = getSelection();
        if (selection == null) {
            return 0.0f;
        }
        Selection.AnchorInfo p0 = selection.getEnd();
        Selectable selectable = getAnchorSelectable$foundation(p0);
        if (selectable == null) {
            return 0.0f;
        }
        return selectable.getLineHeight(selection.getEnd().getOffset());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setCurrentDragPosition-_kEHs6E, reason: not valid java name */
    public final void m2061setCurrentDragPosition_kEHs6E(Offset offset) {
        MutableState $this$setValue$iv = this.currentDragPosition;
        $this$setValue$iv.setValue(offset);
    }

    /* JADX INFO: renamed from: getCurrentDragPosition-_m7T9-E, reason: not valid java name */
    public final Offset m2067getCurrentDragPosition_m7T9E() {
        State $this$getValue$iv = this.currentDragPosition;
        return (Offset) $this$getValue$iv.getValue();
    }

    private final boolean getShouldShowMagnifier() {
        return isDraggingInProgress() && isInTouchMode() && !isTriviallyCollapsedSelection$foundation();
    }

    /* JADX INFO: renamed from: getPreviousSelectionLayout$foundation, reason: from getter */
    public final SelectionLayout getPreviousSelectionLayout() {
        return this.previousSelectionLayout;
    }

    public final void setPreviousSelectionLayout$foundation(SelectionLayout selectionLayout) {
        this.previousSelectionLayout = selectionLayout;
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

    static final Unit _init_$lambda$0(SelectionManager this$0, long selectableId) {
        if (this$0.selectionRegistrar.getSubselections().containsKey(selectableId)) {
            this$0.setPositionChangeState(Unit.INSTANCE);
            this$0.updateHandleOffsets();
            this$0.updateSelectionToolbar();
        }
        return Unit.INSTANCE;
    }

    static final Unit _init_$lambda$1(SelectionManager this$0, boolean isInTouchMode, LayoutCoordinates layoutCoordinates, Offset rawPosition, SelectionAdjustment selectionMode) {
        long $this$lambda_u241_u240 = layoutCoordinates.mo6791getSizeYbymL2g();
        Rect textRect = new Rect(0.0f, 0.0f, (int) ($this$lambda_u241_u240 >> 32), (int) (4294967295L & $this$lambda_u241_u240));
        long position = SelectionManagerKt.m2078containsInclusiveUv8p0NA(textRect, rawPosition.m5078unboximpl()) ? rawPosition.m5078unboximpl() : TextLayoutStateKt.m1883coerceIn3MmeM6k(rawPosition.m5078unboximpl(), textRect);
        long positionInContainer = this$0.m2059convertToContainerCoordinatesR5De75A(layoutCoordinates, position);
        if (((9223372034707292159L & positionInContainer) != androidx.compose.ui.geometry.InlineClassHelperKt.UnspecifiedPackedFloats ? 1 : 0) != 0) {
            this$0.setInTouchMode(isInTouchMode);
            this$0.m2066startSelection9KIMszo(positionInContainer, false, selectionMode);
            FocusRequester.m4973requestFocus3ESFkO8$default(this$0.focusRequester, 0, 1, null);
            this$0.setShowToolbar$foundation(false);
            this$0.isLongPressOrClickSelection = true;
        }
        return Unit.INSTANCE;
    }

    static final Unit _init_$lambda$2(SelectionManager this$0, boolean isInTouchMode, long selectableId) {
        Pair<Selection, LongObjectMap<Selection>> pairSelectAllInSelectable$foundation = this$0.selectAllInSelectable$foundation(selectableId, this$0.getSelection());
        Selection newSelection = pairSelectAllInSelectable$foundation.component1();
        LongObjectMap<Selection> longObjectMapComponent2 = pairSelectAllInSelectable$foundation.component2();
        if (!Intrinsics.areEqual(newSelection, this$0.getSelection())) {
            this$0.selectionRegistrar.setSubselections(longObjectMapComponent2);
            this$0.onSelectionChange.invoke(newSelection);
        }
        this$0.setInTouchMode(isInTouchMode);
        FocusRequester.m4973requestFocus3ESFkO8$default(this$0.focusRequester, 0, 1, null);
        this$0.setShowToolbar$foundation(false);
        return Unit.INSTANCE;
    }

    static final boolean _init_$lambda$3(SelectionManager this$0, boolean isInTouchMode, LayoutCoordinates layoutCoordinates, Offset newPosition, Offset previousPosition, boolean isStartHandle, SelectionAdjustment selectionMode) {
        long newPositionInContainer = this$0.m2059convertToContainerCoordinatesR5De75A(layoutCoordinates, newPosition.m5078unboximpl());
        long previousPositionInContainer = this$0.m2059convertToContainerCoordinatesR5De75A(layoutCoordinates, previousPosition.m5078unboximpl());
        this$0.setInTouchMode(isInTouchMode);
        return this$0.m2074updateSelectionqNKwrvQ$foundation(Offset.m5057boximpl(newPositionInContainer), previousPositionInContainer, isStartHandle, selectionMode);
    }

    static final Unit _init_$lambda$4(SelectionManager this$0) {
        this$0.setShowToolbar$foundation(true);
        this$0.setDraggingHandle(null);
        this$0.m2061setCurrentDragPosition_kEHs6E(null);
        if (this$0.isLongPressOrClickSelection && this$0.isNonEmptySelection$foundation()) {
            this$0.suggestSelectionForLongPressOrDoubleClick();
        }
        this$0.isLongPressOrClickSelection = false;
        return Unit.INSTANCE;
    }

    static final Unit _init_$lambda$5(SelectionManager this$0, long selectableKey) {
        if (this$0.selectionRegistrar.getSubselections().containsKey(selectableKey)) {
            this$0.onRelease();
            this$0.setSelection(null);
        }
        return Unit.INSTANCE;
    }

    static final Unit _init_$lambda$6(SelectionManager this$0, long selectableId) {
        Selection.AnchorInfo end;
        Selection.AnchorInfo start;
        Selection selection = this$0.getSelection();
        if ((selection == null || (start = selection.getStart()) == null || selectableId != start.getSelectableId()) ? false : true) {
            this$0.m2065setStartHandlePosition_kEHs6E(null);
        }
        Selection selection2 = this$0.getSelection();
        if ((selection2 == null || (end = selection2.getEnd()) == null || selectableId != end.getSelectableId()) ? false : true) {
            this$0.m2064setEndHandlePosition_kEHs6E(null);
        }
        if (this$0.selectionRegistrar.getSubselections().containsKey(selectableId)) {
            this$0.updateSelectionToolbar();
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Type inference failed for: r15v1, types: [T, androidx.compose.ui.text.AnnotatedString] */
    /* JADX WARN: Type inference failed for: r1v6, types: [T, androidx.compose.ui.text.TextRange] */
    private final void suggestSelectionForLongPressOrDoubleClick() {
        boolean z;
        int lastSelectableIndex$iv;
        CoroutineScope coroutineScope;
        Ref.ObjectRef textInSelectable = new Ref.ObjectRef();
        Ref.ObjectRef selectionInSelectable = new Ref.ObjectRef();
        Ref.LongRef targetSelectableId = new Ref.LongRef();
        SelectionManager this_$iv = this;
        int $i$f$forEachSelectableWithSelection$foundation = 0;
        List<Selectable> listSort = this_$iv.selectionRegistrar.sort(this_$iv.requireContainerCoordinates$foundation());
        ListIterator<Selectable> listIterator = listSort.listIterator(listSort.size());
        while (true) {
            if (listIterator.hasPrevious()) {
                Selectable it$iv = listIterator.previous();
                z = false;
                Selection subSelection$iv = this_$iv.selectionRegistrar.getSubselections().get(it$iv.getSelectableId());
                if ((subSelection$iv == null || subSelection$iv.getStart().getOffset() == subSelection$iv.getEnd().getOffset()) ? false : true) {
                    lastSelectableIndex$iv = listIterator.nextIndex();
                    break;
                }
            } else {
                z = false;
                lastSelectableIndex$iv = -1;
                break;
            }
        }
        if (lastSelectableIndex$iv != -1) {
            int index$iv$iv = 0;
            int size = listSort.size();
            while (index$iv$iv < size) {
                Object item$iv$iv = listSort.get(index$iv$iv);
                Selectable selectable$iv = (Selectable) item$iv$iv;
                int selectableIndex$iv = index$iv$iv;
                SelectionManager this_$iv2 = this_$iv;
                int $i$f$forEachSelectableWithSelection$foundation2 = $i$f$forEachSelectableWithSelection$foundation;
                Selection subSelection$iv2 = this_$iv.selectionRegistrar.getSubselections().get(selectable$iv.getSelectableId());
                if (subSelection$iv2 != null) {
                    ?? text = selectable$iv.getText();
                    long selectionRange$iv = TextRangeKt.TextRange(subSelection$iv2.getStart().getOffset(), subSelection$iv2.getEnd().getOffset());
                    boolean isLastSelectable$iv = selectableIndex$iv >= lastSelectableIndex$iv ? true : z;
                    long selectableId = selectable$iv.getSelectableId();
                    boolean isLastSelectable = isLastSelectable$iv;
                    if (isLastSelectable) {
                        textInSelectable.element = text;
                        selectionInSelectable.element = TextRange.m7561boximpl(selectionRange$iv);
                        targetSelectableId.element = selectableId;
                    }
                    boolean shouldContinue$iv = z;
                    if (!shouldContinue$iv) {
                        break;
                    }
                }
                index$iv$iv++;
                this_$iv = this_$iv2;
                $i$f$forEachSelectableWithSelection$foundation = $i$f$forEachSelectableWithSelection$foundation2;
            }
        }
        if (textInSelectable.element != 0 && selectionInSelectable.element != 0 && targetSelectableId.element != 0) {
            if ((((CharSequence) textInSelectable.element).length() > 0 ? true : z) && (coroutineScope = this.coroutineScope) != null) {
                BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new AnonymousClass2(textInSelectable, selectionInSelectable, targetSelectableId, null), 3, null);
            }
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.selection.SelectionManager$suggestSelectionForLongPressOrDoubleClick$2, reason: invalid class name */
    /* JADX INFO: compiled from: SelectionManager.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text.selection.SelectionManager$suggestSelectionForLongPressOrDoubleClick$2", f = "SelectionManager.kt", i = {}, l = {455}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Ref.ObjectRef<TextRange> $selectionInSelectable;
        final /* synthetic */ Ref.LongRef $targetSelectableId;
        final /* synthetic */ Ref.ObjectRef<CharSequence> $textInSelectable;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(Ref.ObjectRef<CharSequence> objectRef, Ref.ObjectRef<TextRange> objectRef2, Ref.LongRef longRef, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$textInSelectable = objectRef;
            this.$selectionInSelectable = objectRef2;
            this.$targetSelectableId = longRef;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return SelectionManager.this.new AnonymousClass2(this.$textInSelectable, this.$selectionInSelectable, this.$targetSelectableId, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:17:0x004a  */
        /* JADX WARN: Removed duplicated region for block: B:25:0x007a  */
        /* JADX WARN: Removed duplicated region for block: B:27:0x007d  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r10) {
            /*
                Method dump skipped, instruction units count: 238
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.selection.SelectionManager.AnonymousClass2.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public final Selectable getAnchorSelectable$foundation(Selection.AnchorInfo anchor) {
        return this.selectionRegistrar.getSelectableMap$foundation().get(anchor.getSelectableId());
    }

    private final void updateHandleOffsets() {
        long j;
        long j2;
        Offset offset;
        Offset offset2;
        Offset offsetM5057boximpl;
        Selection.AnchorInfo p0;
        Selection.AnchorInfo p02;
        Selection selection = getSelection();
        LayoutCoordinates containerCoordinates = this.containerLayoutCoordinates;
        Selectable startSelectable = (selection == null || (p02 = selection.getStart()) == null) ? null : getAnchorSelectable$foundation(p02);
        Selectable endSelectable = (selection == null || (p0 = selection.getEnd()) == null) ? null : getAnchorSelectable$foundation(p0);
        LayoutCoordinates startLayoutCoordinates = startSelectable != null ? startSelectable.getLayoutCoordinates() : null;
        LayoutCoordinates endLayoutCoordinates = endSelectable != null ? endSelectable.getLayoutCoordinates() : null;
        if (selection == null || containerCoordinates == null || !containerCoordinates.isAttached() || (startLayoutCoordinates == null && endLayoutCoordinates == null)) {
            m2065setStartHandlePosition_kEHs6E(null);
            m2064setEndHandlePosition_kEHs6E(null);
            return;
        }
        Rect visibleBounds = SelectionManagerKt.visibleBounds(containerCoordinates);
        if (startLayoutCoordinates == null) {
            j = 9205357640488583168L;
            j2 = 9223372034707292159L;
            offset = null;
        } else {
            LayoutCoordinates handleCoordinates = startLayoutCoordinates;
            long $this$isUnspecified$iv = startSelectable.mo2027getHandlePositiondBAh8RU(selection, true);
            if (($this$isUnspecified$iv & 9223372034707292159L) == androidx.compose.ui.geometry.InlineClassHelperKt.UnspecifiedPackedFloats) {
                j = 9205357640488583168L;
                j2 = 9223372034707292159L;
                offsetM5057boximpl = null;
            } else {
                j = 9205357640488583168L;
                long position = containerCoordinates.mo6792localPositionOfR5De75A(handleCoordinates, $this$isUnspecified$iv);
                offsetM5057boximpl = Offset.m5057boximpl(position);
                j2 = 9223372034707292159L;
                long it = offsetM5057boximpl.m5078unboximpl();
                if (!(getDraggingHandle() == Handle.SelectionStart || SelectionManagerKt.m2078containsInclusiveUv8p0NA(visibleBounds, it))) {
                    offsetM5057boximpl = null;
                }
            }
            offset = offsetM5057boximpl;
        }
        m2065setStartHandlePosition_kEHs6E(offset);
        if (endLayoutCoordinates != null) {
            LayoutCoordinates handleCoordinates2 = endLayoutCoordinates;
            long handlePosition = endSelectable.mo2027getHandlePositiondBAh8RU(selection, false);
            if (((handlePosition & j2) == j ? 1 : 0) != 0) {
                offset2 = null;
            } else {
                long position2 = containerCoordinates.mo6792localPositionOfR5De75A(handleCoordinates2, handlePosition);
                Offset offsetM5057boximpl2 = Offset.m5057boximpl(position2);
                long it2 = offsetM5057boximpl2.m5078unboximpl();
                offset2 = getDraggingHandle() == Handle.SelectionEnd || SelectionManagerKt.m2078containsInclusiveUv8p0NA(visibleBounds, it2) ? offsetM5057boximpl2 : null;
            }
        } else {
            offset2 = null;
        }
        m2064setEndHandlePosition_kEHs6E(offset2);
    }

    public final LayoutCoordinates requireContainerCoordinates$foundation() {
        LayoutCoordinates coordinates = this.containerLayoutCoordinates;
        if (coordinates != null) {
            boolean value$iv = coordinates.isAttached();
            if (!value$iv) {
                InlineClassHelperKt.throwIllegalArgumentException("unattached coordinates");
            }
            return coordinates;
        }
        InlineClassHelperKt.throwIllegalArgumentExceptionForNullCheck("null coordinates");
        throw new KotlinNothingValueException();
    }

    public final Pair<Selection, LongObjectMap<Selection>> selectAllInSelectable$foundation(long selectableId, Selection previousSelection) {
        HapticFeedback hapticFeedback;
        List<Selectable> list;
        Object initial$iv;
        int $i$f$fastFold;
        MutableLongObjectMap subselections = LongObjectMapKt.mutableLongObjectMapOf();
        List<Selectable> listSort = this.selectionRegistrar.sort(requireContainerCoordinates$foundation());
        Object initial$iv2 = null;
        int $i$f$fastFold2 = 0;
        Selection newSelection = null;
        int index$iv$iv = 0;
        int size = listSort.size();
        while (index$iv$iv < size) {
            Object item$iv$iv = listSort.get(index$iv$iv);
            Selectable selectable = (Selectable) item$iv$iv;
            Selection mergedSelection = newSelection;
            Selection selection = selectable.getSelectableId() == selectableId ? selectable.getSelectAllSelection() : null;
            if (selection != null) {
                list = listSort;
                initial$iv = initial$iv2;
                $i$f$fastFold = $i$f$fastFold2;
                subselections.set(selectable.getSelectableId(), selection);
            } else {
                list = listSort;
                initial$iv = initial$iv2;
                $i$f$fastFold = $i$f$fastFold2;
            }
            newSelection = SelectionManagerKt.merge(mergedSelection, selection);
            index$iv$iv++;
            listSort = list;
            initial$iv2 = initial$iv;
            $i$f$fastFold2 = $i$f$fastFold;
        }
        if (isInTouchMode() && !Intrinsics.areEqual(newSelection, previousSelection) && (hapticFeedback = this.hapticFeedBack) != null) {
            hapticFeedback.mo6082performHapticFeedbackCdsT49E(HapticFeedbackType.INSTANCE.m6099getTextHandleMove5zf0vsI());
        }
        return new Pair<>(newSelection, subselections);
    }

    public final boolean isEntireContainerSelected$foundation() {
        int $i$f$fastAll;
        List<Selectable> list;
        SelectionManager selectionManager = this;
        List<Selectable> listSort = selectionManager.selectionRegistrar.sort(selectionManager.requireContainerCoordinates$foundation());
        boolean z = true;
        if (listSort.isEmpty()) {
            return true;
        }
        int $i$f$fastAll2 = 0;
        int index$iv$iv = 0;
        int size = listSort.size();
        while (index$iv$iv < size) {
            Object item$iv$iv = listSort.get(index$iv$iv);
            Selectable it = (Selectable) item$iv$iv;
            AnnotatedString text = it.getText();
            if (text.length() == 0 ? z : false) {
                list = listSort;
                $i$f$fastAll = $i$f$fastAll2;
            } else {
                $i$f$fastAll = $i$f$fastAll2;
                Selection subSelection = selectionManager.selectionRegistrar.getSubselections().get(it.getSelectableId());
                if (subSelection == null) {
                    list = listSort;
                    z = false;
                } else {
                    int selectionStart = subSelection.getStart().getOffset();
                    int selectionEnd = subSelection.getEnd().getOffset();
                    list = listSort;
                    z = Math.abs(selectionStart - selectionEnd) == text.length();
                }
            }
            if (!z) {
                return false;
            }
            index$iv$iv++;
            z = true;
            selectionManager = this;
            $i$f$fastAll2 = $i$f$fastAll;
            listSort = list;
        }
        return true;
    }

    public final void selectAll$foundation() {
        Selection newSelection;
        List<Selectable> list;
        List<Selectable> listSort = this.selectionRegistrar.sort(requireContainerCoordinates$foundation());
        if (listSort.isEmpty()) {
            return;
        }
        Selection selection = null;
        Selection selection2 = null;
        MutableLongObjectMap newSubSelections = LongObjectMapKt.mutableLongObjectMapOf();
        int index$iv = 0;
        int size = listSort.size();
        while (index$iv < size) {
            Object item$iv = listSort.get(index$iv);
            Selectable selectable = (Selectable) item$iv;
            Selection subSelection = selectable.getSelectAllSelection();
            if (subSelection == null) {
                list = listSort;
            } else {
                if (selection == null) {
                    selection = subSelection;
                }
                selection2 = subSelection;
                list = listSort;
                newSubSelections.put(selectable.getSelectableId(), subSelection);
                selection = selection;
            }
            index$iv++;
            listSort = list;
        }
        if (newSubSelections.isEmpty()) {
            return;
        }
        if (selection == selection2) {
            newSelection = selection;
        } else {
            Intrinsics.checkNotNull(selection);
            Selection.AnchorInfo start = selection.getStart();
            Intrinsics.checkNotNull(selection2);
            newSelection = new Selection(start, selection2.getEnd(), false);
        }
        this.selectionRegistrar.setSubselections(newSubSelections);
        this.onSelectionChange.invoke(newSelection);
        this.previousSelectionLayout = null;
    }

    public final boolean isTriviallyCollapsedSelection$foundation() {
        Selection selection = getSelection();
        if (selection == null) {
            return true;
        }
        return Intrinsics.areEqual(selection.getStart(), selection.getEnd());
    }

    public final boolean isNonEmptySelection$foundation() {
        Selection selection = getSelection();
        if (selection == null || Intrinsics.areEqual(selection.getStart(), selection.getEnd())) {
            return false;
        }
        if (selection.getStart().getSelectableId() == selection.getEnd().getSelectableId()) {
            return true;
        }
        List<Selectable> listSort = this.selectionRegistrar.sort(requireContainerCoordinates$foundation());
        int index$iv$iv = 0;
        int size = listSort.size();
        while (index$iv$iv < size) {
            Object item$iv$iv = listSort.get(index$iv$iv);
            Selectable selectable = (Selectable) item$iv$iv;
            List<Selectable> list = listSort;
            Selection $this$isNonEmptySelection_u24lambda_u240_u240 = this.selectionRegistrar.getSubselections().get(selectable.getSelectableId());
            boolean z = ($this$isNonEmptySelection_u24lambda_u240_u240 == null || $this$isNonEmptySelection_u24lambda_u240_u240.getStart().getOffset() == $this$isNonEmptySelection_u24lambda_u240_u240.getEnd().getOffset()) ? false : true;
            if (z) {
                return true;
            }
            index$iv$iv++;
            listSort = list;
        }
        return false;
    }

    public final AnnotatedString getSelectedText$foundation() {
        boolean z;
        int lastSelectableIndex$iv;
        AnnotatedString.Builder builder;
        if (getSelection() == null || this.selectionRegistrar.getSubselections().isEmpty()) {
            return null;
        }
        int $i$f$buildAnnotatedString = 0;
        boolean z2 = true;
        AnnotatedString.Builder $this$getSelectedText_u24lambda_u240 = new AnnotatedString.Builder(0, 1, null);
        int i = 0;
        List<Selectable> listSort = this.selectionRegistrar.sort(requireContainerCoordinates$foundation());
        ListIterator<Selectable> listIterator = listSort.listIterator(listSort.size());
        while (true) {
            if (listIterator.hasPrevious()) {
                Selectable it$iv = listIterator.previous();
                z = z2;
                int i2 = i;
                Selection subSelection$iv = this.selectionRegistrar.getSubselections().get(it$iv.getSelectableId());
                if (!((subSelection$iv == null || subSelection$iv.getStart().getOffset() == subSelection$iv.getEnd().getOffset()) ? false : z)) {
                    z2 = z;
                    i = i2;
                } else {
                    lastSelectableIndex$iv = listIterator.nextIndex();
                    break;
                }
            } else {
                z = z2;
                lastSelectableIndex$iv = -1;
                break;
            }
        }
        if (lastSelectableIndex$iv != -1) {
            int index$iv$iv = 0;
            int size = listSort.size();
            while (true) {
                if (index$iv$iv >= size) {
                    builder = $this$getSelectedText_u24lambda_u240;
                    break;
                }
                Object item$iv$iv = listSort.get(index$iv$iv);
                Selectable selectable$iv = (Selectable) item$iv$iv;
                int selectableIndex$iv = index$iv$iv;
                int $i$f$buildAnnotatedString2 = $i$f$buildAnnotatedString;
                builder = $this$getSelectedText_u24lambda_u240;
                Selection subSelection$iv2 = this.selectionRegistrar.getSubselections().get(selectable$iv.getSelectableId());
                if (subSelection$iv2 != null) {
                    AnnotatedString currentText$iv = selectable$iv.getText();
                    long selectionRange$iv = TextRangeKt.TextRange(subSelection$iv2.getStart().getOffset(), subSelection$iv2.getEnd().getOffset());
                    boolean isLastSelectable$iv = selectableIndex$iv >= lastSelectableIndex$iv ? z : false;
                    selectable$iv.getSelectableId();
                    boolean isLastSelectable = isLastSelectable$iv;
                    $this$getSelectedText_u24lambda_u240.append(currentText$iv, TextRange.m7571getMinimpl(selectionRange$iv), TextRange.m7570getMaximpl(selectionRange$iv));
                    if (!isLastSelectable) {
                        $this$getSelectedText_u24lambda_u240.append('\n');
                    }
                    boolean shouldContinue$iv = z;
                    if (!shouldContinue$iv) {
                        break;
                    }
                }
                index$iv$iv++;
                $i$f$buildAnnotatedString = $i$f$buildAnnotatedString2;
                $this$getSelectedText_u24lambda_u240 = builder;
            }
        } else {
            builder = $this$getSelectedText_u24lambda_u240;
        }
        return builder.toAnnotatedString();
    }

    public final Pair<AnnotatedString, TextRange> getContextTextAndSelection$foundation() {
        Pair<AnnotatedString, TextRange> pair;
        int start;
        boolean z;
        int lastSelectableIndex$iv;
        AnnotatedString.Builder builder;
        int start2;
        List<Selectable> list;
        int lastSelectableIndex$iv2;
        AnnotatedString text;
        int start3;
        Pair<AnnotatedString, TextRange> pair2 = null;
        if (getSelection() != null && !this.selectionRegistrar.getSelectables$foundation().isEmpty()) {
            int start4 = -1;
            int end = -1;
            int $i$f$buildAnnotatedString = 0;
            boolean z2 = true;
            AnnotatedString.Builder $this$getContextTextAndSelection_u24lambda_u240 = new AnnotatedString.Builder(0, 1, null);
            List<Selectable> listSort = this.selectionRegistrar.sort(requireContainerCoordinates$foundation());
            ListIterator<Selectable> listIterator = listSort.listIterator(listSort.size());
            while (true) {
                pair = pair2;
                if (listIterator.hasPrevious()) {
                    Selectable it$iv = listIterator.previous();
                    z = z2;
                    start = start4;
                    Selection subSelection$iv = this.selectionRegistrar.getSubselections().get(it$iv.getSelectableId());
                    if (!((subSelection$iv == null || subSelection$iv.getStart().getOffset() == subSelection$iv.getEnd().getOffset()) ? false : z)) {
                        pair2 = pair;
                        start4 = start;
                        z2 = z;
                    } else {
                        lastSelectableIndex$iv = listIterator.nextIndex();
                        break;
                    }
                } else {
                    start = start4;
                    z = z2;
                    lastSelectableIndex$iv = -1;
                    break;
                }
            }
            if (lastSelectableIndex$iv != -1) {
                List<Selectable> list2 = listSort;
                int size = list2.size();
                int index$iv$iv = 0;
                int end2 = -1;
                int end3 = start;
                while (true) {
                    if (index$iv$iv < size) {
                        Object item$iv$iv = list2.get(index$iv$iv);
                        Selectable selectable$iv = (Selectable) item$iv$iv;
                        int selectableIndex$iv = index$iv$iv;
                        int $i$f$buildAnnotatedString2 = $i$f$buildAnnotatedString;
                        builder = $this$getContextTextAndSelection_u24lambda_u240;
                        Selection subSelection$iv2 = this.selectionRegistrar.getSubselections().get(selectable$iv.getSelectableId());
                        if (subSelection$iv2 == null) {
                            list = list2;
                            lastSelectableIndex$iv2 = lastSelectableIndex$iv;
                        } else {
                            AnnotatedString currentText$iv = selectable$iv.getText();
                            list = list2;
                            long selectionRange$iv = TextRangeKt.TextRange(subSelection$iv2.getStart().getOffset(), subSelection$iv2.getEnd().getOffset());
                            boolean isLastSelectable$iv = selectableIndex$iv >= lastSelectableIndex$iv ? z : false;
                            selectable$iv.getSelectableId();
                            boolean isLastSelectable = isLastSelectable$iv;
                            if (end3 != -1) {
                                text = currentText$iv;
                                lastSelectableIndex$iv2 = lastSelectableIndex$iv;
                                start3 = end3;
                            } else {
                                int start5 = TextRange.m7571getMinimpl(selectionRange$iv);
                                int start6 = TextRange.m7571getMinimpl(selectionRange$iv);
                                start3 = start5;
                                text = currentText$iv;
                                lastSelectableIndex$iv2 = lastSelectableIndex$iv;
                                $this$getContextTextAndSelection_u24lambda_u240.append(text, 0, start6);
                            }
                            int start7 = TextRange.m7571getMinimpl(selectionRange$iv);
                            $this$getContextTextAndSelection_u24lambda_u240.append(text, start7, TextRange.m7570getMaximpl(selectionRange$iv));
                            if (!isLastSelectable) {
                                $this$getContextTextAndSelection_u24lambda_u240.append('\n');
                                end = end2;
                            } else {
                                int end4 = $this$getContextTextAndSelection_u24lambda_u240.getLength();
                                $this$getContextTextAndSelection_u24lambda_u240.append(text, TextRange.m7570getMaximpl(selectionRange$iv), text.length());
                                end = end4;
                            }
                            boolean shouldContinue$iv = z;
                            if (shouldContinue$iv) {
                                end2 = end;
                                end3 = start3;
                            } else {
                                start2 = start3;
                                break;
                            }
                        }
                        index$iv$iv++;
                        lastSelectableIndex$iv = lastSelectableIndex$iv2;
                        $i$f$buildAnnotatedString = $i$f$buildAnnotatedString2;
                        $this$getContextTextAndSelection_u24lambda_u240 = builder;
                        list2 = list;
                    } else {
                        builder = $this$getContextTextAndSelection_u24lambda_u240;
                        start2 = end3;
                        end = end2;
                        break;
                    }
                }
            } else {
                builder = $this$getContextTextAndSelection_u24lambda_u240;
                start2 = start;
            }
            AnnotatedString text2 = builder.toAnnotatedString();
            if (start2 == -1 || end == -1) {
                return pair;
            }
            return new Pair<>(text2, TextRange.m7561boximpl(TextRangeKt.TextRange(start2, end)));
        }
        return null;
    }

    public final void forEachSelectableWithSelection$foundation(Function4<? super Long, ? super AnnotatedString, ? super TextRange, ? super Boolean, Boolean> block) {
        int lastSelectableIndex;
        int $i$f$forEachSelectableWithSelection$foundation;
        List<Selectable> list;
        List<Selectable> list2;
        int $i$f$forEachSelectableWithSelection$foundation2 = 0;
        List<Selectable> listSort = this.selectionRegistrar.sort(requireContainerCoordinates$foundation());
        ListIterator<Selectable> listIterator = listSort.listIterator(listSort.size());
        while (true) {
            if (listIterator.hasPrevious()) {
                Selectable it = listIterator.previous();
                Selection subSelection = this.selectionRegistrar.getSubselections().get(it.getSelectableId());
                if ((subSelection == null || subSelection.getStart().getOffset() == subSelection.getEnd().getOffset()) ? false : true) {
                    lastSelectableIndex = listIterator.nextIndex();
                    break;
                }
            } else {
                lastSelectableIndex = -1;
                break;
            }
        }
        if (lastSelectableIndex == -1) {
            return;
        }
        List<Selectable> list3 = listSort;
        int index$iv = 0;
        int size = list3.size();
        while (index$iv < size) {
            Object item$iv = list3.get(index$iv);
            Selectable selectable = (Selectable) item$iv;
            int selectableIndex = index$iv;
            Selection subSelection2 = this.selectionRegistrar.getSubselections().get(selectable.getSelectableId());
            if (subSelection2 == null) {
                $i$f$forEachSelectableWithSelection$foundation = $i$f$forEachSelectableWithSelection$foundation2;
                list = listSort;
                list2 = list3;
            } else {
                AnnotatedString currentText = selectable.getText();
                long selectionRange = TextRangeKt.TextRange(subSelection2.getStart().getOffset(), subSelection2.getEnd().getOffset());
                boolean isLastSelectable = selectableIndex >= lastSelectableIndex;
                $i$f$forEachSelectableWithSelection$foundation = $i$f$forEachSelectableWithSelection$foundation2;
                list = listSort;
                list2 = list3;
                boolean shouldContinue = block.invoke(Long.valueOf(selectable.getSelectableId()), currentText, TextRange.m7561boximpl(selectionRange), Boolean.valueOf(isLastSelectable)).booleanValue();
                if (!shouldContinue) {
                    return;
                }
            }
            index$iv++;
            $i$f$forEachSelectableWithSelection$foundation2 = $i$f$forEachSelectableWithSelection$foundation;
            listSort = list;
            list3 = list2;
        }
    }

    public final void copy$foundation() {
        Function1<? super AnnotatedString, Unit> function1;
        AnnotatedString textToCopy = getSelectedText$foundation();
        if (textToCopy != null) {
            if (!(textToCopy.length() > 0)) {
                textToCopy = null;
            }
            if (textToCopy == null || (function1 = this.onCopyHandler) == null) {
                return;
            }
            function1.invoke(textToCopy);
        }
    }

    /* JADX INFO: renamed from: getShowToolbar$foundation, reason: from getter */
    public final boolean getShowToolbar() {
        return this.showToolbar;
    }

    public final void setShowToolbar$foundation(boolean value) {
        this.showToolbar = value;
        updateSelectionToolbar();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void toolbarCopy() {
        copy$foundation();
        onRelease();
    }

    private final void updateSelectionToolbar() {
        if (!getHasFocus()) {
            return;
        }
        if (ComposeFoundationFlags.isNewContextMenuEnabled) {
            if (this.showToolbar && isInTouchMode()) {
                if (getDerivedContentRect() == null) {
                    return;
                }
                this.toolbarRequester.show();
                return;
            }
            this.toolbarRequester.hide();
            return;
        }
        updateSelectionTextToolbar();
    }

    public final boolean canCopy$foundation() {
        return this.onCopyHandler != null && isNonEmptySelection$foundation();
    }

    private final void updateSelectionTextToolbar() {
        TextToolbar textToolbar = this.textToolbar;
        if (textToolbar == null) {
            return;
        }
        if (this.showToolbar && isInTouchMode()) {
            Rect rect = getContentRect();
            if (rect == null) {
                return;
            }
            TextToolbar.showMenu$default(textToolbar, rect, canCopy$foundation() ? new C02411(this) : null, null, null, isEntireContainerSelected$foundation() ? null : new C02422(this), null, 12, null);
            return;
        }
        if (textToolbar.getStatus() == TextToolbarStatus.Shown) {
            textToolbar.hide();
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.selection.SelectionManager$updateSelectionTextToolbar$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SelectionManager.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    static final /* synthetic */ class C02411 extends FunctionReferenceImpl implements Function0<Unit> {
        C02411(Object obj) {
            super(0, obj, SelectionManager.class, "toolbarCopy", "toolbarCopy()V", 0);
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Unit invoke() {
            invoke2();
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2() {
            ((SelectionManager) this.receiver).toolbarCopy();
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.selection.SelectionManager$updateSelectionTextToolbar$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SelectionManager.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    static final /* synthetic */ class C02422 extends FunctionReferenceImpl implements Function0<Unit> {
        C02422(Object obj) {
            super(0, obj, SelectionManager.class, "selectAll", "selectAll$foundation()V", 0);
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Unit invoke() {
            invoke2();
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2() {
            ((SelectionManager) this.receiver).selectAll$foundation();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Rect getContentRect() {
        LayoutCoordinates containerCoordinates;
        getPositionChangeState();
        Rect rect = null;
        if (getSelection() == null || (containerCoordinates = this.containerLayoutCoordinates) == null || !containerCoordinates.isAttached()) {
            return null;
        }
        List<Selectable> listSort = this.selectionRegistrar.sort(requireContainerCoordinates$foundation());
        ArrayList target$iv = new ArrayList(listSort.size());
        int index$iv$iv = 0;
        int size = listSort.size();
        while (index$iv$iv < size) {
            Object item$iv$iv = listSort.get(index$iv$iv);
            Selectable selectable = (Selectable) item$iv$iv;
            Rect rect2 = rect;
            List<Selectable> list = listSort;
            Selection it = this.selectionRegistrar.getSubselections().get(selectable.getSelectableId());
            Object it$iv = it != null ? TuplesKt.to(selectable, it) : rect2;
            if (it$iv != null) {
                target$iv.add(it$iv);
            }
            index$iv$iv++;
            rect = rect2;
            listSort = list;
        }
        Rect rect3 = rect;
        List selectableSubSelections = SelectionManagerKt.firstAndLast(target$iv);
        if (selectableSubSelections.isEmpty()) {
            return rect3;
        }
        Rect selectedRegionRect = SelectionManagerKt.getSelectedRegionRect(selectableSubSelections, containerCoordinates);
        if (Intrinsics.areEqual(selectedRegionRect, SelectionManagerKt.invertedInfiniteRect)) {
            return rect3;
        }
        Rect visibleRect = SelectionManagerKt.visibleBounds(containerCoordinates).intersect(selectedRegionRect);
        if (visibleRect.getRight() - visibleRect.getLeft() < 0.0f || visibleRect.getBottom() - visibleRect.getTop() < 0.0f) {
            return rect3;
        }
        Rect rootRect = visibleRect.m5105translatek4lQ0M(LayoutCoordinatesKt.positionInRoot(containerCoordinates));
        return Rect.copy$default(rootRect, 0.0f, 0.0f, 0.0f, rootRect.getBottom() + (SelectionHandlesKt.getHandleHeight() * 4.0f), 7, null);
    }

    public final void onRelease() {
        HapticFeedback hapticFeedback;
        this.selectionRegistrar.setSubselections(LongObjectMapKt.emptyLongObjectMap());
        setShowToolbar$foundation(false);
        if (getSelection() != null) {
            this.onSelectionChange.invoke(null);
            if (!isInTouchMode() || (hapticFeedback = this.hapticFeedBack) == null) {
                return;
            }
            hapticFeedback.mo6082performHapticFeedbackCdsT49E(HapticFeedbackType.INSTANCE.m6099getTextHandleMove5zf0vsI());
        }
    }

    public final TextDragObserver handleDragObserver(final boolean isStartHandle) {
        return new TextDragObserver() { // from class: androidx.compose.foundation.text.selection.SelectionManager.handleDragObserver.1
            @Override // androidx.compose.foundation.text.TextDragObserver
            /* JADX INFO: renamed from: onDown-k-4lQ0M */
            public void mo1639onDownk4lQ0M(long point) {
                LayoutCoordinates beginLayoutCoordinates;
                boolean z = isStartHandle;
                SelectionManager selectionManager = this;
                Offset offsetM2071getStartHandlePosition_m7T9E = z ? selectionManager.m2071getStartHandlePosition_m7T9E() : selectionManager.m2070getEndHandlePosition_m7T9E();
                if (offsetM2071getStartHandlePosition_m7T9E == null) {
                    return;
                }
                offsetM2071getStartHandlePosition_m7T9E.m5078unboximpl();
                Selection selection = this.getSelection();
                if (selection == null) {
                    return;
                }
                Selection.AnchorInfo anchor = isStartHandle ? selection.getStart() : selection.getEnd();
                Selectable selectable = this.getAnchorSelectable$foundation(anchor);
                if (selectable == null || (beginLayoutCoordinates = selectable.getLayoutCoordinates()) == null) {
                    return;
                }
                long handlePosition = selectable.mo2027getHandlePositiondBAh8RU(selection, isStartHandle);
                if ((9223372034707292159L & handlePosition) == androidx.compose.ui.geometry.InlineClassHelperKt.UnspecifiedPackedFloats) {
                    return;
                }
                long beginCoordinates = SelectionHandlesKt.m2051getAdjustedCoordinatesk4lQ0M(handlePosition);
                this.m2061setCurrentDragPosition_kEHs6E(Offset.m5057boximpl(this.requireContainerCoordinates$foundation().mo6792localPositionOfR5De75A(beginLayoutCoordinates, beginCoordinates)));
                this.setDraggingHandle(isStartHandle ? Handle.SelectionStart : Handle.SelectionEnd);
                this.setShowToolbar$foundation(false);
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            /* JADX INFO: renamed from: onStart-3MmeM6k */
            public void mo1641onStart3MmeM6k(long startPoint, SelectionAdjustment selectionAdjustment) {
                if (this.getDraggingHandle() == null) {
                    return;
                }
                Selection selection = this.getSelection();
                Intrinsics.checkNotNull(selection);
                Selection.AnchorInfo anchor = isStartHandle ? selection.getStart() : selection.getEnd();
                Object value$iv = this.selectionRegistrar.getSelectableMap$foundation().get(anchor.getSelectableId());
                if (value$iv != null) {
                    Selectable selectable = (Selectable) value$iv;
                    LayoutCoordinates beginLayoutCoordinates = selectable.getLayoutCoordinates();
                    if (beginLayoutCoordinates != null) {
                        long handlePosition = selectable.mo2027getHandlePositiondBAh8RU(selection, isStartHandle);
                        if ((9223372034707292159L & handlePosition) == androidx.compose.ui.geometry.InlineClassHelperKt.UnspecifiedPackedFloats) {
                            return;
                        }
                        long beginCoordinates = SelectionHandlesKt.m2051getAdjustedCoordinatesk4lQ0M(handlePosition);
                        this.m2062setDragBeginPositionk4lQ0M(this.requireContainerCoordinates$foundation().mo6792localPositionOfR5De75A(beginLayoutCoordinates, beginCoordinates));
                        this.m2063setDragTotalDistancek4lQ0M(Offset.INSTANCE.m5084getZeroF1C5BW0());
                        return;
                    }
                    InlineClassHelperKt.throwIllegalStateExceptionForNullCheck("Current selectable should have layout coordinates.");
                    throw new KotlinNothingValueException();
                }
                InlineClassHelperKt.throwIllegalStateExceptionForNullCheck("SelectionRegistrar should contain the current selection's selectableIds");
                throw new KotlinNothingValueException();
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            /* JADX INFO: renamed from: onDrag-k-4lQ0M */
            public void mo1640onDragk4lQ0M(long delta) {
                if (this.getDraggingHandle() == null) {
                    return;
                }
                this.m2063setDragTotalDistancek4lQ0M(Offset.m5073plusMKHz9U(this.m2069getDragTotalDistanceF1C5BW0$foundation(), delta));
                long endPosition = Offset.m5073plusMKHz9U(this.m2068getDragBeginPositionF1C5BW0$foundation(), this.m2069getDragTotalDistanceF1C5BW0$foundation());
                boolean consumed = this.m2074updateSelectionqNKwrvQ$foundation(Offset.m5057boximpl(endPosition), this.m2068getDragBeginPositionF1C5BW0$foundation(), isStartHandle, SelectionAdjustment.INSTANCE.getCharacterWithWordAccelerate());
                if (consumed) {
                    this.m2062setDragBeginPositionk4lQ0M(endPosition);
                    this.m2063setDragTotalDistancek4lQ0M(Offset.INSTANCE.m5084getZeroF1C5BW0());
                }
            }

            private final void done() {
                this.setShowToolbar$foundation(true);
                this.setDraggingHandle(null);
                this.m2061setCurrentDragPosition_kEHs6E(null);
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            public void onUp() {
                done();
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            public void onStop() {
                done();
            }

            @Override // androidx.compose.foundation.text.TextDragObserver
            public void onCancel() {
                done();
            }
        };
    }

    private final Modifier addContextMenuComponents(Modifier $this$addContextMenuComponents) {
        if (ComposeFoundationFlags.isNewContextMenuEnabled) {
            return SelectionManager_androidKt.addSelectionContainerTextContextMenuComponents($this$addContextMenuComponents, this);
        }
        return $this$addContextMenuComponents;
    }

    private final Modifier onClearSelectionRequested(Modifier $this$onClearSelectionRequested, final Function0<Unit> function0) {
        return SuspendingPointerInputFilterKt.pointerInput($this$onClearSelectionRequested, Unit.INSTANCE, new PointerInputEventHandler() { // from class: androidx.compose.foundation.text.selection.SelectionManager.onClearSelectionRequested.1

            /* JADX INFO: renamed from: androidx.compose.foundation.text.selection.SelectionManager$onClearSelectionRequested$1$1, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: SelectionManager.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
            @DebugMetadata(c = "androidx.compose.foundation.text.selection.SelectionManager$onClearSelectionRequested$1$1", f = "SelectionManager.kt", i = {0}, l = {PointerIconCompat.TYPE_CONTEXT_MENU, PointerIconCompat.TYPE_CROSSHAIR}, m = "invokeSuspend", n = {"$this$awaitEachGesture"}, s = {"L$0"}, v = 1)
            static final class C00381 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ Function0<Unit> $block;
                private /* synthetic */ Object L$0;
                int label;
                final /* synthetic */ SelectionManager this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C00381(SelectionManager selectionManager, Function0<Unit> function0, Continuation<? super C00381> continuation) {
                    super(2, continuation);
                    this.this$0 = selectionManager;
                    this.$block = function0;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    C00381 c00381 = new C00381(this.this$0, this.$block, continuation);
                    c00381.L$0 = obj;
                    return c00381;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super Unit> continuation) {
                    return ((C00381) create(awaitPointerEventScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                /* JADX WARN: Removed duplicated region for block: B:14:0x0052 A[RETURN] */
                /* JADX WARN: Removed duplicated region for block: B:17:0x005c  */
                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct code enable 'Show inconsistent code' option in preferences
                */
                public final java.lang.Object invokeSuspend(java.lang.Object r10) {
                    /*
                        r9 = this;
                        java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                        int r1 = r9.label
                        switch(r1) {
                            case 0: goto L21;
                            case 1: goto L17;
                            case 2: goto L12;
                            default: goto L9;
                        }
                    L9:
                        java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
                        java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                        r10.<init>(r0)
                        throw r10
                    L12:
                        kotlin.ResultKt.throwOnFailure(r10)
                        r1 = r10
                        goto L53
                    L17:
                        java.lang.Object r1 = r9.L$0
                        androidx.compose.ui.input.pointer.AwaitPointerEventScope r1 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r1
                        kotlin.ResultKt.throwOnFailure(r10)
                        r2 = r1
                        r1 = r10
                        goto L3f
                    L21:
                        kotlin.ResultKt.throwOnFailure(r10)
                        java.lang.Object r1 = r9.L$0
                        r2 = r1
                        androidx.compose.ui.input.pointer.AwaitPointerEventScope r2 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r2
                        r5 = r9
                        kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
                        r9.L$0 = r2
                        r1 = 1
                        r9.label = r1
                        r3 = 0
                        r4 = 0
                        r6 = 2
                        r7 = 0
                        java.lang.Object r1 = androidx.compose.foundation.gestures.TapGestureDetectorKt.awaitPrimaryFirstDown$default(r2, r3, r4, r5, r6, r7)
                        if (r1 != r0) goto L3c
                        return r0
                    L3c:
                        r8 = r1
                        r1 = r10
                        r10 = r8
                    L3f:
                        androidx.compose.ui.input.pointer.PointerInputChange r10 = (androidx.compose.ui.input.pointer.PointerInputChange) r10
                        androidx.compose.ui.input.pointer.PointerEventPass r3 = androidx.compose.ui.input.pointer.PointerEventPass.Initial
                        r4 = r9
                        kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4
                        r5 = 0
                        r9.L$0 = r5
                        r5 = 2
                        r9.label = r5
                        java.lang.Object r10 = androidx.compose.foundation.gestures.DragGestureDetectorKt.awaitAllPointersUpWithSlopDetection(r2, r10, r3, r4)
                        if (r10 != r0) goto L53
                        return r0
                    L53:
                        java.lang.Boolean r10 = (java.lang.Boolean) r10
                        boolean r10 = r10.booleanValue()
                        if (r10 != 0) goto L69
                        androidx.compose.foundation.text.selection.SelectionManager r0 = r9.this$0
                        boolean r0 = androidx.compose.foundation.text.selection.SelectionManager.access$isDraggingInProgress(r0)
                        if (r0 != 0) goto L69
                        kotlin.jvm.functions.Function0<kotlin.Unit> r10 = r9.$block
                        r10.invoke()
                    L69:
                        kotlin.Unit r10 = kotlin.Unit.INSTANCE
                        return r10
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.selection.SelectionManager.C02401.C00381.invokeSuspend(java.lang.Object):java.lang.Object");
                }
            }

            @Override // androidx.compose.ui.input.pointer.PointerInputEventHandler
            public final Object invoke(PointerInputScope $this$pointerInput, Continuation<? super Unit> continuation) {
                Object objAwaitEachGesture = ForEachGestureKt.awaitEachGesture($this$pointerInput, new C00381(SelectionManager.this, function0, null), continuation);
                return objAwaitEachGesture == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAwaitEachGesture : Unit.INSTANCE;
            }
        });
    }

    /* JADX INFO: renamed from: convertToContainerCoordinates-R5De75A, reason: not valid java name */
    private final long m2059convertToContainerCoordinatesR5De75A(LayoutCoordinates layoutCoordinates, long offset) {
        LayoutCoordinates coordinates = this.containerLayoutCoordinates;
        if (coordinates == null || !coordinates.isAttached()) {
            return Offset.INSTANCE.m5083getUnspecifiedF1C5BW0();
        }
        return requireContainerCoordinates$foundation().mo6792localPositionOfR5De75A(layoutCoordinates, offset);
    }

    /* JADX INFO: renamed from: startSelection-9KIMszo, reason: not valid java name */
    private final void m2066startSelection9KIMszo(long position, boolean isStartHandle, SelectionAdjustment adjustment) {
        this.previousSelectionLayout = null;
        m2073updateSelectionjyLRC_s$foundation(position, Offset.INSTANCE.m5083getUnspecifiedF1C5BW0(), isStartHandle, adjustment);
    }

    /* JADX INFO: renamed from: updateSelection-qNKwrvQ$foundation, reason: not valid java name */
    public final boolean m2074updateSelectionqNKwrvQ$foundation(Offset newPosition, long previousPosition, boolean isStartHandle, SelectionAdjustment adjustment) {
        if (newPosition == null) {
            return false;
        }
        return m2073updateSelectionjyLRC_s$foundation(newPosition.m5078unboximpl(), previousPosition, isStartHandle, adjustment);
    }

    /* JADX INFO: renamed from: updateSelection-jyLRC_s$foundation, reason: not valid java name */
    public final boolean m2073updateSelectionjyLRC_s$foundation(long position, long previousHandlePosition, boolean isStartHandle, SelectionAdjustment adjustment) {
        setDraggingHandle(isStartHandle ? Handle.SelectionStart : Handle.SelectionEnd);
        m2061setCurrentDragPosition_kEHs6E(Offset.m5057boximpl(position));
        SelectionLayout selectionLayout = m2060getSelectionLayoutWko1d7g(position, previousHandlePosition, isStartHandle);
        if (selectionLayout == null || !selectionLayout.shouldRecomputeSelection(this.previousSelectionLayout)) {
            return false;
        }
        Selection newSelection = adjustment.adjust(selectionLayout);
        if (!Intrinsics.areEqual(newSelection, getSelection())) {
            selectionChanged(selectionLayout, newSelection);
            this.isLongPressOrClickSelection = false;
        }
        this.previousSelectionLayout = selectionLayout;
        return true;
    }

    /* JADX INFO: renamed from: getSelectionLayout-Wko1d7g, reason: not valid java name */
    private final SelectionLayout m2060getSelectionLayoutWko1d7g(long position, long previousHandlePosition, boolean isStartHandle) {
        LayoutCoordinates containerCoordinates = requireContainerCoordinates$foundation();
        List<Selectable> listSort = this.selectionRegistrar.sort(containerCoordinates);
        final MutableLongIntMap idToIndexMap = LongIntMapKt.mutableLongIntMapOf();
        int size = listSort.size();
        for (int index$iv = 0; index$iv < size; index$iv++) {
            Object item$iv = listSort.get(index$iv);
            Selectable selectable = (Selectable) item$iv;
            int index = index$iv;
            idToIndexMap.set(selectable.getSelectableId(), index);
        }
        Comparator selectableIdOrderingComparator = new Comparator() { // from class: androidx.compose.foundation.text.selection.SelectionManager$getSelectionLayout-Wko1d7g$$inlined$compareBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                long it = ((Number) t).longValue();
                Integer numValueOf = Integer.valueOf(idToIndexMap.get(it));
                long it2 = ((Number) t2).longValue();
                return ComparisonsKt.compareValues(numValueOf, Integer.valueOf(idToIndexMap.get(it2)));
            }
        };
        Selection previousSelection = ((9223372034707292159L & previousHandlePosition) > androidx.compose.ui.geometry.InlineClassHelperKt.UnspecifiedPackedFloats ? 1 : ((9223372034707292159L & previousHandlePosition) == androidx.compose.ui.geometry.InlineClassHelperKt.UnspecifiedPackedFloats ? 0 : -1)) == 0 ? null : getSelection();
        SelectionLayoutBuilder builder = new SelectionLayoutBuilder(position, previousHandlePosition, containerCoordinates, isStartHandle, previousSelection, selectableIdOrderingComparator, null);
        int size2 = listSort.size();
        for (int index$iv2 = 0; index$iv2 < size2; index$iv2++) {
            Object item$iv2 = listSort.get(index$iv2);
            Selectable it = (Selectable) item$iv2;
            it.appendSelectableInfoToBuilder(builder);
        }
        return builder.build();
    }

    private final void selectionChanged(SelectionLayout selectionLayout, Selection newSelection) {
        HapticFeedback hapticFeedback;
        if (shouldPerformHaptics$foundation() && (hapticFeedback = this.hapticFeedBack) != null) {
            hapticFeedback.mo6082performHapticFeedbackCdsT49E(HapticFeedbackType.INSTANCE.m6099getTextHandleMove5zf0vsI());
        }
        this.selectionRegistrar.setSubselections(selectionLayout.createSubSelections(newSelection));
        this.onSelectionChange.invoke(newSelection);
    }

    public final boolean shouldPerformHaptics$foundation() {
        boolean z;
        if (!isInTouchMode()) {
            return false;
        }
        List<Selectable> selectables$foundation = this.selectionRegistrar.getSelectables$foundation();
        int index$iv$iv = 0;
        int size = selectables$foundation.size();
        while (true) {
            if (index$iv$iv < size) {
                Object item$iv$iv = selectables$foundation.get(index$iv$iv);
                Selectable it = (Selectable) item$iv$iv;
                if (it.getText().length() > 0) {
                    z = true;
                    break;
                }
                index$iv$iv++;
            } else {
                z = false;
                break;
            }
        }
        return z;
    }

    /* JADX INFO: renamed from: selectWordAtPositionIfNotAlreadySelected-k-4lQ0M, reason: not valid java name */
    public final void m2072selectWordAtPositionIfNotAlreadySelectedk4lQ0M(long position) {
        boolean isClickedPositionInsideSelection;
        LayoutCoordinates selectableLayoutCoords;
        LayoutCoordinates containerCoordinates;
        boolean zM1674isPositionInsideSelectionuaM50fQ;
        LayoutCoordinates containerCoordinates2 = this.containerLayoutCoordinates;
        if (containerCoordinates2 != null && containerCoordinates2.isAttached()) {
            List<Selectable> selectables$foundation = this.selectionRegistrar.getSelectables$foundation();
            int $i$f$fastAny = 0;
            int index$iv$iv = 0;
            int size = selectables$foundation.size();
            while (true) {
                if (index$iv$iv < size) {
                    Object item$iv$iv = selectables$foundation.get(index$iv$iv);
                    Selectable selectable = (Selectable) item$iv$iv;
                    List<Selectable> list = selectables$foundation;
                    int $i$f$fastAny2 = $i$f$fastAny;
                    Selection selection = this.selectionRegistrar.getSubselections().get(selectable.getSelectableId());
                    if (selection == null || (selectableLayoutCoords = selectable.getLayoutCoordinates()) == null) {
                        containerCoordinates = containerCoordinates2;
                        zM1674isPositionInsideSelectionuaM50fQ = false;
                    } else {
                        long positionInSelectable = selectableLayoutCoords.mo6792localPositionOfR5De75A(containerCoordinates2, position);
                        TextLayoutResult textLayoutResult = selectable.textLayoutResult();
                        if (textLayoutResult == null) {
                            containerCoordinates = containerCoordinates2;
                            zM1674isPositionInsideSelectionuaM50fQ = false;
                        } else {
                            containerCoordinates = containerCoordinates2;
                            zM1674isPositionInsideSelectionuaM50fQ = TextLayoutHelperKt.m1674isPositionInsideSelectionuaM50fQ(textLayoutResult, positionInSelectable, TextRange.m7561boximpl(selection.m2045toTextRanged9O1mEE()));
                        }
                    }
                    if (zM1674isPositionInsideSelectionuaM50fQ) {
                        isClickedPositionInsideSelection = true;
                        break;
                    }
                    index$iv$iv++;
                    selectables$foundation = list;
                    $i$f$fastAny = $i$f$fastAny2;
                    containerCoordinates2 = containerCoordinates;
                } else {
                    isClickedPositionInsideSelection = false;
                    break;
                }
            }
            if (!isClickedPositionInsideSelection) {
                m2066startSelection9KIMszo(position, true, SelectionAdjustment.INSTANCE.getWord());
            }
        }
    }
}
