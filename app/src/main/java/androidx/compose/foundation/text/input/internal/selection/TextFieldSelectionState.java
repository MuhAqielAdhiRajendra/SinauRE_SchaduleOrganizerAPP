package androidx.compose.foundation.text.input.internal.selection;

import androidx.compose.foundation.ComposeFoundationFlags;
import androidx.compose.foundation.content.internal.ReceiveContentConfiguration;
import androidx.compose.foundation.gestures.TapGestureDetectorKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.interaction.PressInteraction;
import androidx.compose.foundation.internal.ClipboardUtils_androidKt;
import androidx.compose.foundation.text.Handle;
import androidx.compose.foundation.text.TextDragObserver;
import androidx.compose.foundation.text.TextFieldCursor_androidKt;
import androidx.compose.foundation.text.contextmenu.modifier.ToolbarRequester;
import androidx.compose.foundation.text.input.TextFieldCharSequence;
import androidx.compose.foundation.text.input.TextFieldCharSequenceKt;
import androidx.compose.foundation.text.input.internal.IndexTransformationType;
import androidx.compose.foundation.text.input.internal.MathUtilsKt;
import androidx.compose.foundation.text.input.internal.SelectionWedgeAffinity;
import androidx.compose.foundation.text.input.internal.TextLayoutState;
import androidx.compose.foundation.text.input.internal.TextLayoutStateKt;
import androidx.compose.foundation.text.input.internal.TransformedTextFieldState;
import androidx.compose.foundation.text.input.internal.WedgeAffinity;
import androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState;
import androidx.compose.foundation.text.input.internal.undo.TextFieldEditUndoBehavior;
import androidx.compose.foundation.text.selection.MouseSelectionObserver;
import androidx.compose.foundation.text.selection.PlatformSelectionBehaviors;
import androidx.compose.foundation.text.selection.SelectionAdjustment;
import androidx.compose.foundation.text.selection.SelectionHandlesKt;
import androidx.compose.foundation.text.selection.SelectionLayout;
import androidx.compose.foundation.text.selection.SelectionLayoutKt;
import androidx.compose.foundation.text.selection.SelectionManagerKt;
import androidx.compose.foundation.text.selection.TextSelectionDelegateKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.ui.focus.FocusProperties;
import androidx.compose.ui.geometry.InlineClassHelperKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.RectKt;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.hapticfeedback.HapticFeedback;
import androidx.compose.ui.hapticfeedback.HapticFeedbackType;
import androidx.compose.ui.input.pointer.AwaitPointerEventScope;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LayoutCoordinatesKt;
import androidx.compose.ui.platform.Clipboard;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextLayoutInput;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;

/* JADX INFO: compiled from: TextFieldSelectionState.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000ô\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b$\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\f\b\u0001\u0018\u00002\u00020\u0001:\u0006å\u0001æ\u0001ç\u0001Ba\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\t\u0012\u0006\u0010\f\u001a\u00020\t\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012\u0012\u0006\u0010\u0013\u001a\u00020\u0014¢\u0006\u0004\b\u0015\u0010\u0016J\u0015\u0010w\u001a\u00020x2\u0006\u0010y\u001a\u00020\tH\u0000¢\u0006\u0002\bzJ\b\u0010{\u001a\u00020\tH\u0002J\u0006\u0010|\u001a\u00020}J\u0006\u0010~\u001a\u00020}J\u001c\u0010\u007f\u001a\u00020}2\b\u0010\u0080\u0001\u001a\u00030\u0081\u00012\b\u0010\u0082\u0001\u001a\u00030\u0083\u0001H\u0002J\u001d\u0010\u0084\u0001\u001a\u00020}2\b\u0010\u0080\u0001\u001a\u00030\u0081\u00012\b\u0010\u0082\u0001\u001a\u00030\u0083\u0001H\u0002J@\u0010\u0085\u0001\u001a\u0002022\u0006\u0010#\u001a\u00020$2\u0006\u0010\u0013\u001a\u00020\u00142\u0007\u0010\u0086\u0001\u001a\u00020*2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\tJ\u0015\u0010\u0087\u0001\u001a\u000202*\u00030\u0088\u0001H\u0086@¢\u0006\u0003\u0010\u0089\u0001J\u001e\u0010\u008a\u0001\u001a\u000202*\u00030\u0088\u00012\u0007\u0010\u008b\u0001\u001a\u00020\tH\u0086@¢\u0006\u0003\u0010\u008c\u0001J\u0010\u0010\u008d\u0001\u001a\u000202H\u0086@¢\u0006\u0003\u0010\u008e\u0001J\u000f\u0010\u008f\u0001\u001a\u0002022\u0006\u0010]\u001a\u00020\\J\u0007\u0010\u0090\u0001\u001a\u000202J\u0015\u0010\u0091\u0001\u001a\u000202*\u00030\u0088\u0001H\u0086@¢\u0006\u0003\u0010\u0089\u0001J?\u0010\u0092\u0001\u001a\u000202*\u00030\u0088\u00012\n\u0010\u0093\u0001\u001a\u0005\u0018\u00010\u0094\u00012\r\u0010\u0095\u0001\u001a\b\u0012\u0004\u0012\u000202012\r\u0010\u0096\u0001\u001a\b\u0012\u0004\u0012\u00020201H\u0086@¢\u0006\u0003\u0010\u0097\u0001J\u0019\u0010\u0098\u0001\u001a\u00020\t2\u0007\u0010\u0099\u0001\u001a\u00020;¢\u0006\u0006\b\u009a\u0001\u0010\u009b\u0001J\u0015\u0010\u009c\u0001\u001a\u000202*\u00030\u0088\u0001H\u0082@¢\u0006\u0003\u0010\u0089\u0001J$\u0010\u009d\u0001\u001a\u000202*\u00030\u0088\u00012\r\u0010\u0095\u0001\u001a\b\u0012\u0004\u0012\u00020201H\u0086@¢\u0006\u0003\u0010\u009e\u0001J\u0007\u0010\u009f\u0001\u001a\u000202J\u001e\u0010 \u0001\u001a\u000202*\u00030\u0088\u00012\u0007\u0010\u008b\u0001\u001a\u00020\tH\u0082@¢\u0006\u0003\u0010\u008c\u0001J\u0010\u0010¡\u0001\u001a\u000202H\u0082@¢\u0006\u0003\u0010\u008e\u0001J\u0010\u0010¢\u0001\u001a\u000202H\u0082@¢\u0006\u0003\u0010\u008e\u0001J\t\u0010¨\u0001\u001a\u00020}H\u0002J \u0010©\u0001\u001a\u00020x2\u0007\u0010\u008b\u0001\u001a\u00020\t2\u0006\u0010y\u001a\u00020\tH\u0000¢\u0006\u0003\bª\u0001J\u001b\u0010«\u0001\u001a\u00020;2\u0007\u0010\u008b\u0001\u001a\u00020\tH\u0002¢\u0006\u0006\b¬\u0001\u0010\u00ad\u0001J\"\u0010®\u0001\u001a\u0002022\u0007\u0010¯\u0001\u001a\u00020J2\u0007\u0010°\u0001\u001a\u00020;¢\u0006\u0006\b±\u0001\u0010²\u0001J\t\u0010³\u0001\u001a\u000202H\u0002J\u0007\u0010´\u0001\u001a\u000202J\u0007\u0010µ\u0001\u001a\u00020\tJ\n\u0010¶\u0001\u001a\u00020\tH\u0086\bJ\u0010\u0010·\u0001\u001a\u000202H\u0086@¢\u0006\u0003\u0010\u008e\u0001J\n\u0010¸\u0001\u001a\u0005\u0018\u00010¹\u0001J\u0007\u0010º\u0001\u001a\u00020\tJ\n\u0010»\u0001\u001a\u00020\tH\u0086\bJ\u001b\u0010¼\u0001\u001a\u0002022\t\b\u0002\u0010½\u0001\u001a\u00020\tH\u0086@¢\u0006\u0003\u0010¾\u0001J\u001d\u0010¿\u0001\u001a\u0005\u0018\u00010¹\u00012\t\b\u0002\u0010½\u0001\u001a\u00020\tH\u0000¢\u0006\u0003\bÀ\u0001J\u0010\u0010Ã\u0001\u001a\u000202H\u0086@¢\u0006\u0003\u0010\u008e\u0001J\u0007\u0010Ä\u0001\u001a\u00020\tJ\n\u0010Å\u0001\u001a\u00020\tH\u0086\bJ\u0010\u0010Æ\u0001\u001a\u000202H\u0086@¢\u0006\u0003\u0010\u008e\u0001J\u0010\u0010Ç\u0001\u001a\u000202H\u0082@¢\u0006\u0003\u0010\u008e\u0001J\u0018\u0010È\u0001\u001a\u0002022\u0007\u0010 \u001a\u00030¹\u0001H\u0000¢\u0006\u0003\bÉ\u0001J\u0007\u0010Ê\u0001\u001a\u00020\tJ\u0007\u0010Ë\u0001\u001a\u000202J\u0007\u0010Ì\u0001\u001a\u00020\tJ\u0007\u0010Í\u0001\u001a\u000202J\u0019\u0010\u0086\u0001\u001a\u0002022\u0007\u0010Î\u0001\u001a\u00020}H\u0082@¢\u0006\u0003\u0010Ï\u0001J\u0007\u0010Ð\u0001\u001a\u000202J\t\u0010Ñ\u0001\u001a\u000202H\u0002Jd\u0010Ò\u0001\u001a\u00030Ó\u00012\b\u0010Ô\u0001\u001a\u00030\u0083\u00012\u0007\u0010Õ\u0001\u001a\u00020p2\u0007\u0010Ö\u0001\u001a\u00020p2\u0007\u0010\u008b\u0001\u001a\u00020\t2\b\u0010×\u0001\u001a\u00030Ø\u00012\t\b\u0002\u0010Ù\u0001\u001a\u00020\t2\t\b\u0002\u0010Ú\u0001\u001a\u00020\t2\n\u0010Û\u0001\u001a\u0005\u0018\u00010Ü\u0001H\u0000¢\u0006\u0006\bÝ\u0001\u0010Þ\u0001JD\u0010ß\u0001\u001a\u00030Ó\u00012\u0007\u0010à\u0001\u001a\u00020p2\u0007\u0010á\u0001\u001a\u00020p2\n\u0010â\u0001\u001a\u0005\u0018\u00010Ó\u00012\u0007\u0010\u008b\u0001\u001a\u00020\t2\b\u0010×\u0001\u001a\u00030Ø\u0001H\u0002¢\u0006\u0006\bã\u0001\u0010ä\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0004\u001a\u00020\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u000e\u0010\f\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\b\u001a\u00020\t2\u0006\u0010 \u001a\u00020\t@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001bR\u001e\u0010\n\u001a\u00020\t2\u0006\u0010 \u001a\u00020\t@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001bR\u001c\u0010#\u001a\u0004\u0018\u00010$X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R\u0010\u0010)\u001a\u0004\u0018\u00010*X\u0082\u000e¢\u0006\u0002\n\u0000R+\u0010,\u001a\u00020\t2\u0006\u0010+\u001a\u00020\t8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b.\u0010/\u001a\u0004\b,\u0010\u001b\"\u0004\b-\u0010\u001dR\"\u00100\u001a\n\u0012\u0004\u0012\u000202\u0018\u000101X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b3\u00104\"\u0004\b5\u00106R$\u00107\u001a\f\u0012\u0006\u0012\u0004\u0018\u000108\u0018\u000101X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b9\u00104\"\u0004\b:\u00106R+\u0010<\u001a\u00020;2\u0006\u0010+\u001a\u00020;8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\bA\u0010/\u001a\u0004\b=\u0010>\"\u0004\b?\u0010@R\u0014\u0010B\u001a\u00020;8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bC\u0010>R+\u0010D\u001a\u00020;2\u0006\u0010+\u001a\u00020;8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\bG\u0010/\u001a\u0004\bE\u0010>\"\u0004\bF\u0010@R\u0011\u0010H\u001a\u00020;8F¢\u0006\u0006\u001a\u0004\bI\u0010>R/\u0010K\u001a\u0004\u0018\u00010J2\b\u0010+\u001a\u0004\u0018\u00010J8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bP\u0010/\u001a\u0004\bL\u0010M\"\u0004\bN\u0010OR+\u0010R\u001a\u00020Q2\u0006\u0010+\u001a\u00020Q8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bW\u0010/\u001a\u0004\bS\u0010T\"\u0004\bU\u0010VR+\u0010X\u001a\u00020\t2\u0006\u0010+\u001a\u00020\t8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b[\u0010/\u001a\u0004\bY\u0010\u001b\"\u0004\bZ\u0010\u001dR+\u0010]\u001a\u00020\\2\u0006\u0010+\u001a\u00020\\8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\bb\u0010/\u001a\u0004\b^\u0010_\"\u0004\b`\u0010aR+\u0010c\u001a\u00020\t2\u0006\u0010+\u001a\u00020\t8F@@X\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bf\u0010/\u001a\u0004\bd\u0010\u001b\"\u0004\be\u0010\u001dR\u0016\u0010g\u001a\u0004\u0018\u00010h8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bi\u0010jR\u0014\u0010k\u001a\u00020\t8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bl\u0010\u001bR\u0010\u0010m\u001a\u0004\u0018\u00010nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010o\u001a\u00020pX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010q\u001a\u0004\u0018\u00010rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bs\u0010t\"\u0004\bu\u0010vR\"\u0010£\u0001\u001a\u0004\u0018\u00010}8@X\u0080\u0084\u0002¢\u0006\u0010\n\u0006\b¦\u0001\u0010§\u0001\u001a\u0006\b¤\u0001\u0010¥\u0001R\u0010\u0010Á\u0001\u001a\u00030Â\u0001X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006è\u0001"}, d2 = {"Landroidx/compose/foundation/text/input/internal/selection/TextFieldSelectionState;", "", "textFieldState", "Landroidx/compose/foundation/text/input/internal/TransformedTextFieldState;", "textLayoutState", "Landroidx/compose/foundation/text/input/internal/TextLayoutState;", "density", "Landroidx/compose/ui/unit/Density;", "enabled", "", "readOnly", "isFocused", "isPassword", "toolbarRequester", "Landroidx/compose/foundation/text/contextmenu/modifier/ToolbarRequester;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "platformSelectionBehaviors", "Landroidx/compose/foundation/text/selection/PlatformSelectionBehaviors;", "clipboard", "Landroidx/compose/ui/platform/Clipboard;", "<init>", "(Landroidx/compose/foundation/text/input/internal/TransformedTextFieldState;Landroidx/compose/foundation/text/input/internal/TextLayoutState;Landroidx/compose/ui/unit/Density;ZZZZLandroidx/compose/foundation/text/contextmenu/modifier/ToolbarRequester;Lkotlinx/coroutines/CoroutineScope;Landroidx/compose/foundation/text/selection/PlatformSelectionBehaviors;Landroidx/compose/ui/platform/Clipboard;)V", "getTextFieldState$foundation", "()Landroidx/compose/foundation/text/input/internal/TransformedTextFieldState;", "getTextLayoutState$foundation", "()Landroidx/compose/foundation/text/input/internal/TextLayoutState;", "()Z", "setFocused", "(Z)V", "getPlatformSelectionBehaviors$foundation", "()Landroidx/compose/foundation/text/selection/PlatformSelectionBehaviors;", "value", "getEnabled", "getReadOnly", "hapticFeedBack", "Landroidx/compose/ui/hapticfeedback/HapticFeedback;", "getHapticFeedBack", "()Landroidx/compose/ui/hapticfeedback/HapticFeedback;", "setHapticFeedBack", "(Landroidx/compose/ui/hapticfeedback/HapticFeedback;)V", "textToolbarHandler", "Landroidx/compose/foundation/text/input/internal/selection/TextToolbarHandler;", "<set-?>", "isInTouchMode", "setInTouchMode", "isInTouchMode$delegate", "Landroidx/compose/runtime/MutableState;", "requestAutofillAction", "Lkotlin/Function0;", "", "getRequestAutofillAction", "()Lkotlin/jvm/functions/Function0;", "setRequestAutofillAction", "(Lkotlin/jvm/functions/Function0;)V", "receiveContentConfiguration", "Landroidx/compose/foundation/content/internal/ReceiveContentConfiguration;", "getReceiveContentConfiguration", "setReceiveContentConfiguration", "Landroidx/compose/ui/geometry/Offset;", "startTextLayoutPositionInWindow", "getStartTextLayoutPositionInWindow-F1C5BW0", "()J", "setStartTextLayoutPositionInWindow-k-4lQ0M", "(J)V", "startTextLayoutPositionInWindow$delegate", "currentTextLayoutPositionInWindow", "getCurrentTextLayoutPositionInWindow-F1C5BW0", "rawHandleDragPosition", "getRawHandleDragPosition-F1C5BW0", "setRawHandleDragPosition-k-4lQ0M", "rawHandleDragPosition$delegate", "handleDragPosition", "getHandleDragPosition-F1C5BW0", "Landroidx/compose/foundation/text/Handle;", "draggingHandle", "getDraggingHandle", "()Landroidx/compose/foundation/text/Handle;", "setDraggingHandle", "(Landroidx/compose/foundation/text/Handle;)V", "draggingHandle$delegate", "Landroidx/compose/foundation/text/input/internal/selection/TextFieldSelectionState$InputType;", "directDragGestureInitiator", "getDirectDragGestureInitiator", "()Landroidx/compose/foundation/text/input/internal/selection/TextFieldSelectionState$InputType;", "setDirectDragGestureInitiator", "(Landroidx/compose/foundation/text/input/internal/selection/TextFieldSelectionState$InputType;)V", "directDragGestureInitiator$delegate", "showCursorHandle", "getShowCursorHandle", "setShowCursorHandle", "showCursorHandle$delegate", "Landroidx/compose/foundation/text/input/internal/selection/TextToolbarState;", "textToolbarState", "getTextToolbarState", "()Landroidx/compose/foundation/text/input/internal/selection/TextToolbarState;", "setTextToolbarState", "(Landroidx/compose/foundation/text/input/internal/selection/TextToolbarState;)V", "textToolbarState$delegate", "textToolbarShown", "getTextToolbarShown", "setTextToolbarShown$foundation", "textToolbarShown$delegate", "textLayoutCoordinates", "Landroidx/compose/ui/layout/LayoutCoordinates;", "getTextLayoutCoordinates", "()Landroidx/compose/ui/layout/LayoutCoordinates;", "editable", "getEditable$foundation", "previousSelectionLayout", "Landroidx/compose/foundation/text/selection/SelectionLayout;", "previousRawDragOffset", "", "pressInteraction", "Landroidx/compose/foundation/interaction/PressInteraction$Press;", "getPressInteraction", "()Landroidx/compose/foundation/interaction/PressInteraction$Press;", "setPressInteraction", "(Landroidx/compose/foundation/interaction/PressInteraction$Press;)V", "getCursorHandleState", "Landroidx/compose/foundation/text/input/internal/selection/TextFieldHandleState;", "includePosition", "getCursorHandleState$foundation", "isCursorHandleInVisibleBounds", "getCursorRect", "Landroidx/compose/ui/geometry/Rect;", "getFocusRect", "calculateCursorRect", "layoutResult", "Landroidx/compose/ui/text/TextLayoutResult;", "visualText", "Landroidx/compose/foundation/text/input/TextFieldCharSequence;", "calculateSelectionRect", "update", "showTextToolbar", "cursorHandleGestures", "Landroidx/compose/ui/input/pointer/PointerInputScope;", "(Landroidx/compose/ui/input/pointer/PointerInputScope;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "selectionHandleGestures", "isStartHandle", "(Landroidx/compose/ui/input/pointer/PointerInputScope;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "startToolbarAndHandlesVisibilityObserver", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateTextToolbarState", "dispose", "detectTouchMode", "detectTextFieldTapGestures", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "requestFocus", "showKeyboard", "(Landroidx/compose/ui/input/pointer/PointerInputScope;Landroidx/compose/foundation/interaction/MutableInteractionSource;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "placeCursorAtNearestOffset", TypedValues.CycleType.S_WAVE_OFFSET, "placeCursorAtNearestOffset-k-4lQ0M", "(J)Z", "detectCursorHandleDragGestures", "textFieldSelectionGestures", "(Landroidx/compose/ui/input/pointer/PointerInputScope;Lkotlin/jvm/functions/Function0;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "maybeSuggestSelectionRange", "detectSelectionHandleDragGestures", "observeTextChanges", "observeTextToolbarVisibility", "derivedVisibleContentBounds", "getDerivedVisibleContentBounds$foundation", "()Landroidx/compose/ui/geometry/Rect;", "derivedVisibleContentBounds$delegate", "Landroidx/compose/runtime/State;", "getContentRect", "getSelectionHandleState", "getSelectionHandleState$foundation", "getHandlePosition", "getHandlePosition-tuRUvjQ", "(Z)J", "updateHandleDragging", "handle", "position", "updateHandleDragging-Uv8p0NA", "(Landroidx/compose/foundation/text/Handle;J)V", "markStartContentVisibleOffset", "clearHandleDragging", "canShowCutMenuItem", "isCutAllowed", "cut", "cutWithResult", "Landroidx/compose/ui/text/AnnotatedString;", "canShowCopyMenuItem", "isCopyAllowed", "copy", "cancelSelection", "(ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "copyWithResult", "copyWithResult$foundation", "clipboardPasteState", "Landroidx/compose/foundation/text/input/internal/selection/ClipboardPasteState;", "updateClipboardEntry", "canShowPasteMenuItem", "isPasteAllowed", "paste", "pasteAsPlainText", "onPasteEvent", "onPasteEvent$foundation", "canShowSelectAllMenuItem", "selectAll", "canShowAutofillMenuItem", "autofill", "contentRect", "(Landroidx/compose/ui/geometry/Rect;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deselect", "hideTextToolbar", "updateSelection", "Landroidx/compose/ui/text/TextRange;", "textFieldCharSequence", "startOffset", "endOffset", "adjustment", "Landroidx/compose/foundation/text/selection/SelectionAdjustment;", "allowPreviousSelectionCollapsed", "isStartOfSelection", "hapticFeedbackType", "Landroidx/compose/ui/hapticfeedback/HapticFeedbackType;", "updateSelection-QkiN0lo$foundation", "(Landroidx/compose/foundation/text/input/TextFieldCharSequence;IIZLandroidx/compose/foundation/text/selection/SelectionAdjustment;ZZLandroidx/compose/ui/hapticfeedback/HapticFeedbackType;)J", "getTextFieldSelection", "rawStartOffset", "rawEndOffset", "previousSelection", "getTextFieldSelection-qeG_v_k", "(IILandroidx/compose/ui/text/TextRange;ZLandroidx/compose/foundation/text/selection/SelectionAdjustment;)J", "InputType", "TextFieldMouseSelectionObserver", "TextFieldTextDragObserver", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class TextFieldSelectionState {
    public static final int $stable = 8;
    private Clipboard clipboard;
    private ClipboardPasteState clipboardPasteState;
    private final CoroutineScope coroutineScope;
    private Density density;
    private boolean enabled;
    private HapticFeedback hapticFeedBack;
    private boolean isFocused;
    private boolean isPassword;
    private final PlatformSelectionBehaviors platformSelectionBehaviors;
    private PressInteraction.Press pressInteraction;
    private SelectionLayout previousSelectionLayout;
    private boolean readOnly;
    private Function0<? extends ReceiveContentConfiguration> receiveContentConfiguration;
    private Function0<Unit> requestAutofillAction;
    private final TransformedTextFieldState textFieldState;
    private final TextLayoutState textLayoutState;
    private TextToolbarHandler textToolbarHandler;
    private final ToolbarRequester toolbarRequester;

    /* JADX INFO: renamed from: isInTouchMode$delegate, reason: from kotlin metadata */
    private final MutableState isInTouchMode = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(true, null, 2, null);

    /* JADX INFO: renamed from: startTextLayoutPositionInWindow$delegate, reason: from kotlin metadata */
    private final MutableState startTextLayoutPositionInWindow = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Offset.m5057boximpl(Offset.INSTANCE.m5083getUnspecifiedF1C5BW0()), null, 2, null);

    /* JADX INFO: renamed from: rawHandleDragPosition$delegate, reason: from kotlin metadata */
    private final MutableState rawHandleDragPosition = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Offset.m5057boximpl(Offset.INSTANCE.m5083getUnspecifiedF1C5BW0()), null, 2, null);

    /* JADX INFO: renamed from: draggingHandle$delegate, reason: from kotlin metadata */
    private final MutableState draggingHandle = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);

    /* JADX INFO: renamed from: directDragGestureInitiator$delegate, reason: from kotlin metadata */
    private final MutableState directDragGestureInitiator = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(InputType.None, null, 2, null);

    /* JADX INFO: renamed from: showCursorHandle$delegate, reason: from kotlin metadata */
    private final MutableState showCursorHandle = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);

    /* JADX INFO: renamed from: textToolbarState$delegate, reason: from kotlin metadata */
    private final MutableState textToolbarState = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(TextToolbarState.None, null, 2, null);

    /* JADX INFO: renamed from: textToolbarShown$delegate, reason: from kotlin metadata */
    private final MutableState textToolbarShown = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
    private int previousRawDragOffset = -1;

    /* JADX INFO: renamed from: derivedVisibleContentBounds$delegate, reason: from kotlin metadata */
    private final State derivedVisibleContentBounds = SnapshotStateKt.derivedStateOf(new Function0() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$$ExternalSyntheticLambda2
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return TextFieldSelectionState.derivedVisibleContentBounds_delegate$lambda$0(this.f$0);
        }
    });

    /* JADX INFO: compiled from: TextFieldSelectionState.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Landroidx/compose/foundation/text/input/internal/selection/TextFieldSelectionState$InputType;", "", "<init>", "(Ljava/lang/String;I)V", "None", "Touch", "Mouse", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public enum InputType {
        None,
        Touch,
        Mouse;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

        public static EnumEntries<InputType> getEntries() {
            return $ENTRIES;
        }
    }

    /* JADX INFO: compiled from: TextFieldSelectionState.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[IndexTransformationType.values().length];
            try {
                iArr[IndexTransformationType.Untransformed.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[IndexTransformationType.Deletion.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[IndexTransformationType.Insertion.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[IndexTransformationType.Replacement.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$detectCursorHandleDragGestures$1, reason: invalid class name */
    /* JADX INFO: compiled from: TextFieldSelectionState.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState", f = "TextFieldSelectionState.kt", i = {0, 0}, l = {676}, m = "detectCursorHandleDragGestures", n = {"cursorDragStart", "cursorDragDelta"}, s = {"L$0", "L$1"}, v = 1)
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return TextFieldSelectionState.this.detectCursorHandleDragGestures(null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$detectSelectionHandleDragGestures$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: TextFieldSelectionState.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState", f = "TextFieldSelectionState.kt", i = {0, 0, 0}, l = {1162}, m = "detectSelectionHandleDragGestures", n = {"dragBeginPosition", "dragTotalDistance", "handle"}, s = {"L$0", "L$1", "L$2"}, v = 1)
    static final class C02271 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        C02271(Continuation<? super C02271> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return TextFieldSelectionState.this.detectSelectionHandleDragGestures(null, false, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$paste$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: TextFieldSelectionState.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState", f = "TextFieldSelectionState.kt", i = {1}, l = {1570, 1572, 1572}, m = "paste", n = {"receiveContentConfiguration"}, s = {"L$0"}, v = 1)
    static final class C02311 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C02311(Continuation<? super C02311> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return TextFieldSelectionState.this.paste(this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$pasteAsPlainText$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: TextFieldSelectionState.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState", f = "TextFieldSelectionState.kt", i = {}, l = {1603, 1603}, m = "pasteAsPlainText", n = {}, s = {}, v = 1)
    static final class C02321 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        C02321(Continuation<? super C02321> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return TextFieldSelectionState.this.pasteAsPlainText(this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$startToolbarAndHandlesVisibilityObserver$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: TextFieldSelectionState.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState", f = "TextFieldSelectionState.kt", i = {}, l = {537}, m = "startToolbarAndHandlesVisibilityObserver", n = {}, s = {}, v = 1)
    static final class C02341 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        C02341(Continuation<? super C02341> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return TextFieldSelectionState.this.startToolbarAndHandlesVisibilityObserver(this);
        }
    }

    public TextFieldSelectionState(TransformedTextFieldState textFieldState, TextLayoutState textLayoutState, Density density, boolean enabled, boolean readOnly, boolean isFocused, boolean isPassword, ToolbarRequester toolbarRequester, CoroutineScope coroutineScope, PlatformSelectionBehaviors platformSelectionBehaviors, Clipboard clipboard) {
        this.textFieldState = textFieldState;
        this.textLayoutState = textLayoutState;
        this.density = density;
        this.isFocused = isFocused;
        this.isPassword = isPassword;
        this.toolbarRequester = toolbarRequester;
        this.coroutineScope = coroutineScope;
        this.platformSelectionBehaviors = platformSelectionBehaviors;
        this.clipboard = clipboard;
        this.enabled = enabled;
        this.readOnly = readOnly;
        this.clipboardPasteState = new ClipboardPasteState(this.clipboard);
    }

    /* JADX INFO: renamed from: getTextFieldState$foundation, reason: from getter */
    public final TransformedTextFieldState getTextFieldState() {
        return this.textFieldState;
    }

    /* JADX INFO: renamed from: getTextLayoutState$foundation, reason: from getter */
    public final TextLayoutState getTextLayoutState() {
        return this.textLayoutState;
    }

    /* JADX INFO: renamed from: isFocused, reason: from getter */
    public final boolean getIsFocused() {
        return this.isFocused;
    }

    public final void setFocused(boolean z) {
        this.isFocused = z;
    }

    /* JADX INFO: renamed from: getPlatformSelectionBehaviors$foundation, reason: from getter */
    public final PlatformSelectionBehaviors getPlatformSelectionBehaviors() {
        return this.platformSelectionBehaviors;
    }

    public final boolean getEnabled() {
        return this.enabled;
    }

    public final boolean getReadOnly() {
        return this.readOnly;
    }

    public final HapticFeedback getHapticFeedBack() {
        return this.hapticFeedBack;
    }

    public final void setHapticFeedBack(HapticFeedback hapticFeedback) {
        this.hapticFeedBack = hapticFeedback;
    }

    public final boolean isInTouchMode() {
        State $this$getValue$iv = this.isInTouchMode;
        return ((Boolean) $this$getValue$iv.getValue()).booleanValue();
    }

    public final void setInTouchMode(boolean z) {
        MutableState $this$setValue$iv = this.isInTouchMode;
        $this$setValue$iv.setValue(Boolean.valueOf(z));
    }

    public final Function0<Unit> getRequestAutofillAction() {
        return this.requestAutofillAction;
    }

    public final void setRequestAutofillAction(Function0<Unit> function0) {
        this.requestAutofillAction = function0;
    }

    public final Function0<ReceiveContentConfiguration> getReceiveContentConfiguration() {
        return this.receiveContentConfiguration;
    }

    public final void setReceiveContentConfiguration(Function0<? extends ReceiveContentConfiguration> function0) {
        this.receiveContentConfiguration = function0;
    }

    /* JADX INFO: renamed from: getStartTextLayoutPositionInWindow-F1C5BW0, reason: not valid java name */
    private final long m1934getStartTextLayoutPositionInWindowF1C5BW0() {
        State $this$getValue$iv = this.startTextLayoutPositionInWindow;
        return ((Offset) $this$getValue$iv.getValue()).m5078unboximpl();
    }

    /* JADX INFO: renamed from: setStartTextLayoutPositionInWindow-k-4lQ0M, reason: not valid java name */
    private final void m1937setStartTextLayoutPositionInWindowk4lQ0M(long j) {
        MutableState $this$setValue$iv = this.startTextLayoutPositionInWindow;
        $this$setValue$iv.setValue(Offset.m5057boximpl(j));
    }

    /* JADX INFO: renamed from: getCurrentTextLayoutPositionInWindow-F1C5BW0, reason: not valid java name */
    private final long m1931getCurrentTextLayoutPositionInWindowF1C5BW0() {
        LayoutCoordinates textLayoutCoordinates = getTextLayoutCoordinates();
        return textLayoutCoordinates != null ? LayoutCoordinatesKt.positionInWindow(textLayoutCoordinates) : Offset.INSTANCE.m5083getUnspecifiedF1C5BW0();
    }

    /* JADX INFO: renamed from: getRawHandleDragPosition-F1C5BW0, reason: not valid java name */
    private final long m1933getRawHandleDragPositionF1C5BW0() {
        State $this$getValue$iv = this.rawHandleDragPosition;
        return ((Offset) $this$getValue$iv.getValue()).m5078unboximpl();
    }

    /* JADX INFO: renamed from: setRawHandleDragPosition-k-4lQ0M, reason: not valid java name */
    private final void m1936setRawHandleDragPositionk4lQ0M(long j) {
        MutableState $this$setValue$iv = this.rawHandleDragPosition;
        $this$setValue$iv.setValue(Offset.m5057boximpl(j));
    }

    /* JADX INFO: renamed from: getHandleDragPosition-F1C5BW0, reason: not valid java name */
    public final long m1939getHandleDragPositionF1C5BW0() {
        long $this$isUnspecified$iv = m1933getRawHandleDragPositionF1C5BW0();
        if (($this$isUnspecified$iv & 9223372034707292159L) == InlineClassHelperKt.UnspecifiedPackedFloats) {
            return Offset.INSTANCE.m5083getUnspecifiedF1C5BW0();
        }
        long $this$isUnspecified$iv2 = m1934getStartTextLayoutPositionInWindowF1C5BW0();
        if ((9223372034707292159L & $this$isUnspecified$iv2) == InlineClassHelperKt.UnspecifiedPackedFloats) {
            return TextLayoutStateKt.m1884fromDecorationToTextLayoutUv8p0NA(this.textLayoutState, m1933getRawHandleDragPositionF1C5BW0());
        }
        return Offset.m5073plusMKHz9U(m1933getRawHandleDragPositionF1C5BW0(), Offset.m5072minusMKHz9U(m1934getStartTextLayoutPositionInWindowF1C5BW0(), m1931getCurrentTextLayoutPositionInWindowF1C5BW0()));
    }

    public final Handle getDraggingHandle() {
        State $this$getValue$iv = this.draggingHandle;
        return (Handle) $this$getValue$iv.getValue();
    }

    public final void setDraggingHandle(Handle handle) {
        MutableState $this$setValue$iv = this.draggingHandle;
        $this$setValue$iv.setValue(handle);
    }

    public final InputType getDirectDragGestureInitiator() {
        State $this$getValue$iv = this.directDragGestureInitiator;
        return (InputType) $this$getValue$iv.getValue();
    }

    public final void setDirectDragGestureInitiator(InputType inputType) {
        MutableState $this$setValue$iv = this.directDragGestureInitiator;
        $this$setValue$iv.setValue(inputType);
    }

    public final boolean getShowCursorHandle() {
        State $this$getValue$iv = this.showCursorHandle;
        return ((Boolean) $this$getValue$iv.getValue()).booleanValue();
    }

    public final void setShowCursorHandle(boolean z) {
        MutableState $this$setValue$iv = this.showCursorHandle;
        $this$setValue$iv.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final TextToolbarState getTextToolbarState() {
        State $this$getValue$iv = this.textToolbarState;
        return (TextToolbarState) $this$getValue$iv.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setTextToolbarState(TextToolbarState textToolbarState) {
        MutableState $this$setValue$iv = this.textToolbarState;
        $this$setValue$iv.setValue(textToolbarState);
    }

    public final boolean getTextToolbarShown() {
        State $this$getValue$iv = this.textToolbarShown;
        return ((Boolean) $this$getValue$iv.getValue()).booleanValue();
    }

    public final void setTextToolbarShown$foundation(boolean z) {
        MutableState $this$setValue$iv = this.textToolbarShown;
        $this$setValue$iv.setValue(Boolean.valueOf(z));
    }

    private final LayoutCoordinates getTextLayoutCoordinates() {
        LayoutCoordinates it = this.textLayoutState.getTextLayoutNodeCoordinates();
        if (it == null || !it.isAttached()) {
            return null;
        }
        return it;
    }

    public final boolean getEditable$foundation() {
        return this.enabled && !this.readOnly;
    }

    public final PressInteraction.Press getPressInteraction() {
        return this.pressInteraction;
    }

    public final void setPressInteraction(PressInteraction.Press press) {
        this.pressInteraction = press;
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0048  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final androidx.compose.foundation.text.input.internal.selection.TextFieldHandleState getCursorHandleState$foundation(boolean r15) {
        /*
            r14 = this;
            androidx.compose.foundation.text.input.internal.TransformedTextFieldState r0 = r14.textFieldState
            androidx.compose.foundation.text.input.TextFieldCharSequence r0 = r0.getVisualText()
            boolean r1 = r14.getShowCursorHandle()
            androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$InputType r2 = r14.getDirectDragGestureInitiator()
            androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$InputType r3 = androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState.InputType.None
            r4 = 1
            r5 = 0
            if (r2 != r3) goto L16
            r2 = r4
            goto L17
        L16:
            r2 = r5
        L17:
            androidx.compose.foundation.text.Handle r3 = r14.getDraggingHandle()
            if (r1 == 0) goto L48
            if (r2 == 0) goto L48
            long r6 = r0.getSelection()
            boolean r6 = androidx.compose.ui.text.TextRange.m7567getCollapsedimpl(r6)
            if (r6 == 0) goto L48
            boolean r6 = r0.shouldShowSelection()
            if (r6 == 0) goto L48
            r6 = r0
            java.lang.CharSequence r6 = (java.lang.CharSequence) r6
            int r6 = r6.length()
            if (r6 <= 0) goto L3a
            r6 = r4
            goto L3b
        L3a:
            r6 = r5
        L3b:
            if (r6 == 0) goto L48
            androidx.compose.foundation.text.Handle r6 = androidx.compose.foundation.text.Handle.Cursor
            if (r3 == r6) goto L49
            boolean r6 = r14.isCursorHandleInVisibleBounds()
            if (r6 == 0) goto L48
            goto L49
        L48:
            r4 = r5
        L49:
            if (r4 != 0) goto L53
            androidx.compose.foundation.text.input.internal.selection.TextFieldHandleState$Companion r5 = androidx.compose.foundation.text.input.internal.selection.TextFieldHandleState.INSTANCE
            androidx.compose.foundation.text.input.internal.selection.TextFieldHandleState r5 = r5.getHidden()
            return r5
        L53:
            r10 = 0
            androidx.compose.foundation.text.input.internal.selection.TextFieldHandleState r6 = new androidx.compose.foundation.text.input.internal.selection.TextFieldHandleState
            if (r15 == 0) goto L62
            androidx.compose.ui.geometry.Rect r5 = r14.getCursorRect()
            long r7 = r5.m5095getBottomCenterF1C5BW0()
            goto L68
        L62:
            androidx.compose.ui.geometry.Offset$Companion r5 = androidx.compose.ui.geometry.Offset.INSTANCE
            long r7 = r5.m5083getUnspecifiedF1C5BW0()
        L68:
            r8 = r7
            androidx.compose.ui.text.style.ResolvedTextDirection r11 = androidx.compose.ui.text.style.ResolvedTextDirection.Ltr
            r7 = 1
            r12 = 0
            r13 = 0
            r6.<init>(r7, r8, r10, r11, r12, r13)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState.getCursorHandleState$foundation(boolean):androidx.compose.foundation.text.input.internal.selection.TextFieldHandleState");
    }

    private final boolean isCursorHandleInVisibleBounds() {
        Rect rectVisibleBounds;
        Snapshot.Companion this_$iv = Snapshot.INSTANCE;
        Snapshot previousSnapshot$iv = this_$iv.getCurrentThreadSnapshot();
        Function1<Object, Unit> readObserver = previousSnapshot$iv != null ? previousSnapshot$iv.getReadObserver() : null;
        Snapshot newSnapshot$iv = this_$iv.makeCurrentNonObservable(previousSnapshot$iv);
        try {
            long position = getCursorRect().m5095getBottomCenterF1C5BW0();
            this_$iv.restoreNonObservable(previousSnapshot$iv, newSnapshot$iv, readObserver);
            LayoutCoordinates textLayoutCoordinates = getTextLayoutCoordinates();
            if (textLayoutCoordinates == null || (rectVisibleBounds = SelectionManagerKt.visibleBounds(textLayoutCoordinates)) == null) {
                return false;
            }
            return SelectionManagerKt.m2078containsInclusiveUv8p0NA(rectVisibleBounds, position);
        } catch (Throwable th) {
            this_$iv.restoreNonObservable(previousSnapshot$iv, newSnapshot$iv, readObserver);
            throw th;
        }
    }

    public final Rect getCursorRect() {
        TextLayoutResult layoutResult = this.textLayoutState.getLayoutResult();
        if (layoutResult == null) {
            return Rect.INSTANCE.getZero();
        }
        TextFieldCharSequence value = this.textFieldState.getVisualText();
        return calculateCursorRect(layoutResult, value);
    }

    public final Rect getFocusRect() {
        Rect focusRectInTextLayout;
        TextLayoutResult layoutResult = this.textLayoutState.getLayoutResult();
        if (layoutResult == null) {
            return Rect.INSTANCE.getZero();
        }
        if (!this.isFocused) {
            return FocusProperties.INSTANCE.getUnsetFocusRect();
        }
        TextFieldCharSequence value = this.textFieldState.getVisualText();
        if (TextRange.m7567getCollapsedimpl(value.getSelection())) {
            focusRectInTextLayout = calculateCursorRect(layoutResult, value);
        } else {
            focusRectInTextLayout = calculateSelectionRect(layoutResult, value);
        }
        return TextLayoutStateKt.fromTextLayoutToDecoration(this.textLayoutState, focusRectInTextLayout);
    }

    private final Rect calculateCursorRect(TextLayoutResult layoutResult, TextFieldCharSequence visualText) {
        float cursorCenterX;
        float coercedCursorCenterX;
        if (!TextRange.m7567getCollapsedimpl(visualText.getSelection())) {
            return Rect.INSTANCE.getZero();
        }
        Rect cursorRect = layoutResult.getCursorRect(TextRange.m7573getStartimpl(visualText.getSelection()));
        Density $this$calculateCursorRect_u24lambda_u240 = this.density;
        float cursorWidth = RangesKt.coerceAtLeast((float) Math.floor($this$calculateCursorRect_u24lambda_u240.mo432toPx0680j_4(TextFieldCursor_androidKt.getDefaultCursorThickness())), 1.0f);
        if (layoutResult.getLayoutInput().getLayoutDirection() == LayoutDirection.Ltr) {
            cursorCenterX = cursorRect.getLeft() + (cursorWidth / 2.0f);
        } else {
            cursorCenterX = cursorRect.getRight() - (cursorWidth / 2.0f);
        }
        long arg0$iv = layoutResult.getSize();
        float it = RangesKt.coerceAtLeast(RangesKt.coerceAtMost(cursorCenterX, ((int) (arg0$iv >> 32)) - (cursorWidth / 2.0f)), cursorWidth / 2.0f);
        if (((int) cursorWidth) % 2 == 1) {
            coercedCursorCenterX = ((float) Math.floor(it)) + 0.5f;
        } else {
            coercedCursorCenterX = (float) Math.rint(it);
        }
        return new Rect(coercedCursorCenterX - (cursorWidth / 2.0f), cursorRect.getTop(), (cursorWidth / 2.0f) + coercedCursorCenterX, cursorRect.getBottom());
    }

    private final Rect calculateSelectionRect(TextLayoutResult layoutResult, TextFieldCharSequence visualText) {
        if (TextRange.m7567getCollapsedimpl(visualText.getSelection())) {
            return Rect.INSTANCE.getZero();
        }
        int lineStart = layoutResult.getLineForOffset(TextRange.m7573getStartimpl(visualText.getSelection()));
        int lineEnd = layoutResult.getLineForOffset(TextRange.m7568getEndimpl(visualText.getSelection()));
        if (lineStart == lineEnd) {
            float startHorizontal = layoutResult.getHorizontalPosition(TextRange.m7573getStartimpl(visualText.getSelection()), true);
            float endHorizontal = layoutResult.getHorizontalPosition(TextRange.m7568getEndimpl(visualText.getSelection()), true);
            return new Rect(Math.min(startHorizontal, endHorizontal), layoutResult.getLineTop(lineStart), Math.max(startHorizontal, endHorizontal), layoutResult.getLineBottom(lineEnd));
        }
        Path path = layoutResult.getPathForRange(TextRange.m7571getMinimpl(visualText.getSelection()), TextRange.m7570getMaximpl(visualText.getSelection()));
        return path.getBounds();
    }

    public final void update(HapticFeedback hapticFeedBack, Clipboard clipboard, TextToolbarHandler showTextToolbar, Density density, boolean enabled, boolean readOnly, boolean isPassword) {
        if (!enabled) {
            hideTextToolbar();
        }
        this.hapticFeedBack = hapticFeedBack;
        this.clipboard = clipboard;
        this.textToolbarHandler = showTextToolbar;
        this.density = density;
        this.enabled = enabled;
        this.readOnly = readOnly;
        this.isPassword = isPassword;
        if (clipboard != clipboard) {
            this.clipboardPasteState = new ClipboardPasteState(clipboard);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$cursorHandleGestures$2, reason: invalid class name */
    /* JADX INFO: compiled from: TextFieldSelectionState.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Lkotlinx/coroutines/Job;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$cursorHandleGestures$2", f = "TextFieldSelectionState.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Job>, Object> {
        final /* synthetic */ PointerInputScope $this_cursorHandleGestures;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(PointerInputScope pointerInputScope, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$this_cursorHandleGestures = pointerInputScope;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = TextFieldSelectionState.this.new AnonymousClass2(this.$this_cursorHandleGestures, continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Job> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX INFO: renamed from: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$cursorHandleGestures$2$1, reason: invalid class name */
        /* JADX INFO: compiled from: TextFieldSelectionState.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
        @DebugMetadata(c = "androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$cursorHandleGestures$2$1", f = "TextFieldSelectionState.kt", i = {}, l = {493}, m = "invokeSuspend", n = {}, s = {}, v = 1)
        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ PointerInputScope $this_cursorHandleGestures;
            int label;
            final /* synthetic */ TextFieldSelectionState this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass1(TextFieldSelectionState textFieldSelectionState, PointerInputScope pointerInputScope, Continuation<? super AnonymousClass1> continuation) {
                super(2, continuation);
                this.this$0 = textFieldSelectionState;
                this.$this_cursorHandleGestures = pointerInputScope;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass1(this.this$0, this.$this_cursorHandleGestures, continuation);
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
                        if (this.this$0.detectTouchMode(this.$this_cursorHandleGestures, this) == coroutine_suspended) {
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

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    CoroutineScope $this$coroutineScope = (CoroutineScope) this.L$0;
                    BuildersKt__Builders_commonKt.launch$default($this$coroutineScope, null, CoroutineStart.UNDISPATCHED, new AnonymousClass1(TextFieldSelectionState.this, this.$this_cursorHandleGestures, null), 1, null);
                    BuildersKt__Builders_commonKt.launch$default($this$coroutineScope, null, CoroutineStart.UNDISPATCHED, new C00342(TextFieldSelectionState.this, this.$this_cursorHandleGestures, null), 1, null);
                    return BuildersKt__Builders_commonKt.launch$default($this$coroutineScope, null, CoroutineStart.UNDISPATCHED, new AnonymousClass3(this.$this_cursorHandleGestures, TextFieldSelectionState.this, null), 1, null);
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        /* JADX INFO: renamed from: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$cursorHandleGestures$2$2, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: TextFieldSelectionState.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
        @DebugMetadata(c = "androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$cursorHandleGestures$2$2", f = "TextFieldSelectionState.kt", i = {}, l = {494}, m = "invokeSuspend", n = {}, s = {}, v = 1)
        static final class C00342 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ PointerInputScope $this_cursorHandleGestures;
            int label;
            final /* synthetic */ TextFieldSelectionState this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C00342(TextFieldSelectionState textFieldSelectionState, PointerInputScope pointerInputScope, Continuation<? super C00342> continuation) {
                super(2, continuation);
                this.this$0 = textFieldSelectionState;
                this.$this_cursorHandleGestures = pointerInputScope;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new C00342(this.this$0, this.$this_cursorHandleGestures, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C00342) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object $result) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        this.label = 1;
                        if (this.this$0.detectCursorHandleDragGestures(this.$this_cursorHandleGestures, this) == coroutine_suspended) {
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

        /* JADX INFO: renamed from: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$cursorHandleGestures$2$3, reason: invalid class name */
        /* JADX INFO: compiled from: TextFieldSelectionState.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
        @DebugMetadata(c = "androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$cursorHandleGestures$2$3", f = "TextFieldSelectionState.kt", i = {}, l = {496}, m = "invokeSuspend", n = {}, s = {}, v = 1)
        static final class AnonymousClass3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ PointerInputScope $this_cursorHandleGestures;
            int label;
            final /* synthetic */ TextFieldSelectionState this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass3(PointerInputScope pointerInputScope, TextFieldSelectionState textFieldSelectionState, Continuation<? super AnonymousClass3> continuation) {
                super(2, continuation);
                this.$this_cursorHandleGestures = pointerInputScope;
                this.this$0 = textFieldSelectionState;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass3(this.$this_cursorHandleGestures, this.this$0, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object $result) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        PointerInputScope pointerInputScope = this.$this_cursorHandleGestures;
                        final TextFieldSelectionState textFieldSelectionState = this.this$0;
                        this.label = 1;
                        if (TapGestureDetectorKt.detectTapGestures$default(pointerInputScope, null, null, null, new Function1() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$cursorHandleGestures$2$3$$ExternalSyntheticLambda0
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return TextFieldSelectionState.AnonymousClass2.AnonymousClass3.invokeSuspend$lambda$0(textFieldSelectionState, (Offset) obj);
                            }
                        }, this, 7, null) == coroutine_suspended) {
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

            static final Unit invokeSuspend$lambda$0(TextFieldSelectionState this$0, Offset it) {
                this$0.setTextToolbarState(this$0.getTextToolbarState() == TextToolbarState.Cursor ? TextToolbarState.None : TextToolbarState.Cursor);
                return Unit.INSTANCE;
            }
        }
    }

    public final Object cursorHandleGestures(PointerInputScope $this$cursorHandleGestures, Continuation<? super Unit> continuation) {
        Object objCoroutineScope = CoroutineScopeKt.coroutineScope(new AnonymousClass2($this$cursorHandleGestures, null), continuation);
        return objCoroutineScope == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCoroutineScope : Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$selectionHandleGestures$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: TextFieldSelectionState.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Lkotlinx/coroutines/Job;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$selectionHandleGestures$2", f = "TextFieldSelectionState.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class C02332 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Job>, Object> {
        final /* synthetic */ boolean $isStartHandle;
        final /* synthetic */ PointerInputScope $this_selectionHandleGestures;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C02332(PointerInputScope pointerInputScope, boolean z, Continuation<? super C02332> continuation) {
            super(2, continuation);
            this.$this_selectionHandleGestures = pointerInputScope;
            this.$isStartHandle = z;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C02332 c02332 = TextFieldSelectionState.this.new C02332(this.$this_selectionHandleGestures, this.$isStartHandle, continuation);
            c02332.L$0 = obj;
            return c02332;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Job> continuation) {
            return ((C02332) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX INFO: renamed from: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$selectionHandleGestures$2$1, reason: invalid class name */
        /* JADX INFO: compiled from: TextFieldSelectionState.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
        @DebugMetadata(c = "androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$selectionHandleGestures$2$1", f = "TextFieldSelectionState.kt", i = {}, l = {TypedValues.PositionType.TYPE_PERCENT_X}, m = "invokeSuspend", n = {}, s = {}, v = 1)
        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ PointerInputScope $this_selectionHandleGestures;
            int label;
            final /* synthetic */ TextFieldSelectionState this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass1(TextFieldSelectionState textFieldSelectionState, PointerInputScope pointerInputScope, Continuation<? super AnonymousClass1> continuation) {
                super(2, continuation);
                this.this$0 = textFieldSelectionState;
                this.$this_selectionHandleGestures = pointerInputScope;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass1(this.this$0, this.$this_selectionHandleGestures, continuation);
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
                        if (this.this$0.detectTouchMode(this.$this_selectionHandleGestures, this) == coroutine_suspended) {
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

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    CoroutineScope $this$coroutineScope = (CoroutineScope) this.L$0;
                    BuildersKt__Builders_commonKt.launch$default($this$coroutineScope, null, CoroutineStart.UNDISPATCHED, new AnonymousClass1(TextFieldSelectionState.this, this.$this_selectionHandleGestures, null), 1, null);
                    Job jobLaunch$default = BuildersKt__Builders_commonKt.launch$default($this$coroutineScope, null, CoroutineStart.UNDISPATCHED, new C00352(this.$this_selectionHandleGestures, TextFieldSelectionState.this, this.$isStartHandle, null), 1, null);
                    final TextFieldSelectionState textFieldSelectionState = TextFieldSelectionState.this;
                    jobLaunch$default.invokeOnCompletion(new Function1() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$selectionHandleGestures$2$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            return TextFieldSelectionState.C02332.invokeSuspend$lambda$0(textFieldSelectionState, (Throwable) obj2);
                        }
                    });
                    return BuildersKt__Builders_commonKt.launch$default($this$coroutineScope, null, CoroutineStart.UNDISPATCHED, new AnonymousClass4(TextFieldSelectionState.this, this.$this_selectionHandleGestures, this.$isStartHandle, null), 1, null);
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        /* JADX INFO: renamed from: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$selectionHandleGestures$2$2, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: TextFieldSelectionState.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
        @DebugMetadata(c = "androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$selectionHandleGestures$2$2", f = "TextFieldSelectionState.kt", i = {}, l = {TypedValues.PositionType.TYPE_CURVE_FIT}, m = "invokeSuspend", n = {}, s = {}, v = 1)
        static final class C00352 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ boolean $isStartHandle;
            final /* synthetic */ PointerInputScope $this_selectionHandleGestures;
            int label;
            final /* synthetic */ TextFieldSelectionState this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C00352(PointerInputScope pointerInputScope, TextFieldSelectionState textFieldSelectionState, boolean z, Continuation<? super C00352> continuation) {
                super(2, continuation);
                this.$this_selectionHandleGestures = pointerInputScope;
                this.this$0 = textFieldSelectionState;
                this.$isStartHandle = z;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new C00352(this.$this_selectionHandleGestures, this.this$0, this.$isStartHandle, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C00352) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object $result) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        PointerInputScope pointerInputScope = this.$this_selectionHandleGestures;
                        final TextFieldSelectionState textFieldSelectionState = this.this$0;
                        final boolean z = this.$isStartHandle;
                        TapOnPosition tapOnPosition = new TapOnPosition() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState.selectionHandleGestures.2.2.1
                            @Override // androidx.compose.foundation.text.input.internal.selection.TapOnPosition
                            /* JADX INFO: renamed from: onEvent-k-4lQ0M */
                            public final void mo1919onEventk4lQ0M(long it) {
                                Handle handle;
                                textFieldSelectionState.markStartContentVisibleOffset();
                                TextFieldSelectionState textFieldSelectionState2 = textFieldSelectionState;
                                if (z) {
                                    handle = Handle.SelectionStart;
                                } else {
                                    handle = Handle.SelectionEnd;
                                }
                                textFieldSelectionState2.m1941updateHandleDraggingUv8p0NA(handle, SelectionHandlesKt.m2051getAdjustedCoordinatesk4lQ0M(textFieldSelectionState.m1932getHandlePositiontuRUvjQ(z)));
                            }
                        };
                        final TextFieldSelectionState textFieldSelectionState2 = this.this$0;
                        this.label = 1;
                        if (PressDownGestureKt.detectPressDownGesture(pointerInputScope, tapOnPosition, new Function0() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$selectionHandleGestures$2$2$$ExternalSyntheticLambda0
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return TextFieldSelectionState.C02332.C00352.invokeSuspend$lambda$0(textFieldSelectionState2);
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
                return Unit.INSTANCE;
            }

            static final Unit invokeSuspend$lambda$0(TextFieldSelectionState this$0) {
                this$0.clearHandleDragging();
                return Unit.INSTANCE;
            }
        }

        /* JADX INFO: renamed from: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$selectionHandleGestures$2$4, reason: invalid class name */
        /* JADX INFO: compiled from: TextFieldSelectionState.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
        @DebugMetadata(c = "androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$selectionHandleGestures$2$4", f = "TextFieldSelectionState.kt", i = {}, l = {526}, m = "invokeSuspend", n = {}, s = {}, v = 1)
        static final class AnonymousClass4 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ boolean $isStartHandle;
            final /* synthetic */ PointerInputScope $this_selectionHandleGestures;
            int label;
            final /* synthetic */ TextFieldSelectionState this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass4(TextFieldSelectionState textFieldSelectionState, PointerInputScope pointerInputScope, boolean z, Continuation<? super AnonymousClass4> continuation) {
                super(2, continuation);
                this.this$0 = textFieldSelectionState;
                this.$this_selectionHandleGestures = pointerInputScope;
                this.$isStartHandle = z;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass4(this.this$0, this.$this_selectionHandleGestures, this.$isStartHandle, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass4) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object $result) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        this.label = 1;
                        if (this.this$0.detectSelectionHandleDragGestures(this.$this_selectionHandleGestures, this.$isStartHandle, this) == coroutine_suspended) {
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

        static final Unit invokeSuspend$lambda$0(TextFieldSelectionState this$0, Throwable it) {
            this$0.clearHandleDragging();
            return Unit.INSTANCE;
        }
    }

    public final Object selectionHandleGestures(PointerInputScope $this$selectionHandleGestures, boolean isStartHandle, Continuation<? super Unit> continuation) {
        Object objCoroutineScope = CoroutineScopeKt.coroutineScope(new C02332($this$selectionHandleGestures, isStartHandle, null), continuation);
        return objCoroutineScope == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCoroutineScope : Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object startToolbarAndHandlesVisibilityObserver(kotlin.coroutines.Continuation<? super kotlin.Unit> r9) throws java.lang.Throwable {
        /*
            r8 = this;
            boolean r0 = r9 instanceof androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState.C02341
            if (r0 == 0) goto L14
            r0 = r9
            androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$startToolbarAndHandlesVisibilityObserver$1 r0 = (androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState.C02341) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r1 = r0.label
            int r1 = r1 - r2
            r0.label = r1
            goto L19
        L14:
            androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$startToolbarAndHandlesVisibilityObserver$1 r0 = new androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$startToolbarAndHandlesVisibilityObserver$1
            r0.<init>(r9)
        L19:
            java.lang.Object r1 = r0.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r0.label
            r4 = 0
            switch(r3) {
                case 0: goto L36;
                case 1: goto L2e;
                default: goto L25;
            }
        L25:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L2e:
            r2 = r8
            kotlin.ResultKt.throwOnFailure(r1)     // Catch: java.lang.Throwable -> L34
            r5 = r1
            goto L4e
        L34:
            r3 = move-exception
            goto L66
        L36:
            kotlin.ResultKt.throwOnFailure(r1)
            r3 = r8
            androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$startToolbarAndHandlesVisibilityObserver$2 r5 = new androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$startToolbarAndHandlesVisibilityObserver$2     // Catch: java.lang.Throwable -> L62
            r6 = 0
            r5.<init>(r6)     // Catch: java.lang.Throwable -> L62
            kotlin.jvm.functions.Function2 r5 = (kotlin.jvm.functions.Function2) r5     // Catch: java.lang.Throwable -> L62
            r6 = 1
            r0.label = r6     // Catch: java.lang.Throwable -> L62
            java.lang.Object r5 = kotlinx.coroutines.CoroutineScopeKt.coroutineScope(r5, r0)     // Catch: java.lang.Throwable -> L62
            if (r5 != r2) goto L4d
            return r2
        L4d:
            r2 = r3
        L4e:
            kotlinx.coroutines.Job r5 = (kotlinx.coroutines.Job) r5     // Catch: java.lang.Throwable -> L34
            r2.setShowCursorHandle(r4)
            androidx.compose.foundation.text.input.internal.selection.TextToolbarState r3 = r2.getTextToolbarState()
            androidx.compose.foundation.text.input.internal.selection.TextToolbarState r4 = androidx.compose.foundation.text.input.internal.selection.TextToolbarState.None
            if (r3 == r4) goto L5e
            r2.hideTextToolbar()
        L5e:
            kotlin.Unit r2 = kotlin.Unit.INSTANCE
            return r2
        L62:
            r2 = move-exception
            r7 = r3
            r3 = r2
            r2 = r7
        L66:
            r2.setShowCursorHandle(r4)
            androidx.compose.foundation.text.input.internal.selection.TextToolbarState r4 = r2.getTextToolbarState()
            androidx.compose.foundation.text.input.internal.selection.TextToolbarState r5 = androidx.compose.foundation.text.input.internal.selection.TextToolbarState.None
            if (r4 == r5) goto L74
            r2.hideTextToolbar()
        L74:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState.startToolbarAndHandlesVisibilityObserver(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$startToolbarAndHandlesVisibilityObserver$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: TextFieldSelectionState.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Lkotlinx/coroutines/Job;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$startToolbarAndHandlesVisibilityObserver$2", f = "TextFieldSelectionState.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class C02352 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Job>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        C02352(Continuation<? super C02352> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C02352 c02352 = TextFieldSelectionState.this.new C02352(continuation);
            c02352.L$0 = obj;
            return c02352;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Job> continuation) {
            return ((C02352) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX INFO: renamed from: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$startToolbarAndHandlesVisibilityObserver$2$1, reason: invalid class name */
        /* JADX INFO: compiled from: TextFieldSelectionState.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
        @DebugMetadata(c = "androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$startToolbarAndHandlesVisibilityObserver$2$1", f = "TextFieldSelectionState.kt", i = {}, l = {538}, m = "invokeSuspend", n = {}, s = {}, v = 1)
        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;
            final /* synthetic */ TextFieldSelectionState this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass1(TextFieldSelectionState textFieldSelectionState, Continuation<? super AnonymousClass1> continuation) {
                super(2, continuation);
                this.this$0 = textFieldSelectionState;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass1(this.this$0, continuation);
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
                        if (this.this$0.observeTextChanges(this) == coroutine_suspended) {
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

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    CoroutineScope $this$coroutineScope = (CoroutineScope) this.L$0;
                    BuildersKt__Builders_commonKt.launch$default($this$coroutineScope, null, null, new AnonymousClass1(TextFieldSelectionState.this, null), 3, null);
                    return BuildersKt__Builders_commonKt.launch$default($this$coroutineScope, null, null, new C00362(TextFieldSelectionState.this, null), 3, null);
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        /* JADX INFO: renamed from: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$startToolbarAndHandlesVisibilityObserver$2$2, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: TextFieldSelectionState.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
        @DebugMetadata(c = "androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$startToolbarAndHandlesVisibilityObserver$2$2", f = "TextFieldSelectionState.kt", i = {}, l = {539}, m = "invokeSuspend", n = {}, s = {}, v = 1)
        static final class C00362 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;
            final /* synthetic */ TextFieldSelectionState this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C00362(TextFieldSelectionState textFieldSelectionState, Continuation<? super C00362> continuation) {
                super(2, continuation);
                this.this$0 = textFieldSelectionState;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new C00362(this.this$0, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C00362) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object $result) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        this.label = 1;
                        if (this.this$0.observeTextToolbarVisibility(this) == coroutine_suspended) {
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
    }

    public final void updateTextToolbarState(TextToolbarState textToolbarState) {
        setTextToolbarState(textToolbarState);
    }

    public final void dispose() {
        hideTextToolbar();
        this.hapticFeedBack = null;
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$detectTouchMode$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: TextFieldSelectionState.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$detectTouchMode$2", f = "TextFieldSelectionState.kt", i = {0}, l = {566}, m = "invokeSuspend", n = {"$this$awaitPointerEventScope"}, s = {"L$0"}, v = 1)
    static final class C02282 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        C02282(Continuation<? super C02282> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C02282 c02282 = TextFieldSelectionState.this.new C02282(continuation);
            c02282.L$0 = obj;
            return c02282;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super Unit> continuation) {
            return ((C02282) create(awaitPointerEventScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:10:0x0037 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:11:0x0038  */
        /* JADX WARN: Removed duplicated region for block: B:14:0x0048  */
        /* JADX WARN: Removed duplicated region for block: B:15:0x004a  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:11:0x0038 -> B:12:0x003e). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r9) {
            /*
                r8 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r8.label
                r2 = 1
                switch(r1) {
                    case 0: goto L1f;
                    case 1: goto L13;
                    default: goto La;
                }
            La:
                java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r9.<init>(r0)
                throw r9
            L13:
                java.lang.Object r1 = r8.L$0
                androidx.compose.ui.input.pointer.AwaitPointerEventScope r1 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r1
                kotlin.ResultKt.throwOnFailure(r9)
                r4 = r8
                r3 = r1
                r1 = r0
                r0 = r9
                goto L3e
            L1f:
                kotlin.ResultKt.throwOnFailure(r9)
                java.lang.Object r1 = r8.L$0
                androidx.compose.ui.input.pointer.AwaitPointerEventScope r1 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r1
                r3 = r8
            L27:
                androidx.compose.ui.input.pointer.PointerEventPass r4 = androidx.compose.ui.input.pointer.PointerEventPass.Initial
                r5 = r3
                kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
                r3.L$0 = r1
                r3.label = r2
                java.lang.Object r4 = r1.awaitPointerEvent(r4, r5)
                if (r4 != r0) goto L38
                return r0
            L38:
                r7 = r0
                r0 = r9
                r9 = r4
                r4 = r3
                r3 = r1
                r1 = r7
            L3e:
                androidx.compose.ui.input.pointer.PointerEvent r9 = (androidx.compose.ui.input.pointer.PointerEvent) r9
                androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState r5 = androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState.this
                boolean r6 = androidx.compose.foundation.text.selection.SelectionGestures_androidKt.isMouseOrTouchPad(r9)
                if (r6 != 0) goto L4a
                r9 = r2
                goto L4b
            L4a:
                r9 = 0
            L4b:
                r5.setInTouchMode(r9)
                r9 = r0
                r0 = r1
                r1 = r3
                r3 = r4
                goto L27
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState.C02282.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public final Object detectTouchMode(PointerInputScope $this$detectTouchMode, Continuation<? super Unit> continuation) {
        Object objAwaitPointerEventScope = $this$detectTouchMode.awaitPointerEventScope(new C02282(null), continuation);
        return objAwaitPointerEventScope == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAwaitPointerEventScope : Unit.INSTANCE;
    }

    public final Object detectTextFieldTapGestures(PointerInputScope $this$detectTextFieldTapGestures, MutableInteractionSource interactionSource, Function0<Unit> function0, Function0<Unit> function02, Continuation<? super Unit> continuation) {
        Object objDetectTextFieldTapGestures = TextFieldSelectionState_androidKt.detectTextFieldTapGestures(this, $this$detectTextFieldTapGestures, interactionSource, function0, function02, continuation);
        return objDetectTextFieldTapGestures == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objDetectTextFieldTapGestures : Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: placeCursorAtNearestOffset-k-4lQ0M, reason: not valid java name */
    public final boolean m1940placeCursorAtNearestOffsetk4lQ0M(long offset) {
        int index;
        IndexTransformationType type$iv;
        boolean z;
        int untransformedCursor;
        SelectionWedgeAffinity selectionWedgeAffinity;
        TextLayoutResult layoutResult = this.textLayoutState.getLayoutResult();
        if (layoutResult == null || (index = layoutResult.m7543getOffsetForPositionk4lQ0M(offset)) == -1) {
            return false;
        }
        SelectionWedgeAffinity selectionWedgeAffinity2 = null;
        TransformedTextFieldState $this$getIndexTransformationType$iv = this.textFieldState;
        long untransformed$iv = $this$getIndexTransformationType$iv.m1892mapFromTransformedjx7JFs(index);
        long retransformed$iv = $this$getIndexTransformationType$iv.m1895mapToTransformedGEjPoXI(untransformed$iv);
        if (TextRange.m7567getCollapsedimpl(untransformed$iv) && TextRange.m7567getCollapsedimpl(retransformed$iv)) {
            type$iv = IndexTransformationType.Untransformed;
        } else if (!TextRange.m7567getCollapsedimpl(untransformed$iv) && !TextRange.m7567getCollapsedimpl(retransformed$iv)) {
            type$iv = IndexTransformationType.Replacement;
        } else if (TextRange.m7567getCollapsedimpl(untransformed$iv) && !TextRange.m7567getCollapsedimpl(retransformed$iv)) {
            type$iv = IndexTransformationType.Insertion;
        } else {
            type$iv = IndexTransformationType.Deletion;
        }
        IndexTransformationType type = type$iv;
        switch (WhenMappings.$EnumSwitchMapping$0[type.ordinal()]) {
            case 1:
                z = false;
                untransformedCursor = TextRange.m7573getStartimpl(untransformed$iv);
                break;
            case 2:
                z = false;
                untransformedCursor = TextRange.m7573getStartimpl(untransformed$iv);
                break;
            case 3:
                z = false;
                Rect wedgeStartCursorRect = layoutResult.getCursorRect(TextRange.m7573getStartimpl(retransformed$iv));
                Rect wedgeEndCursorRect = layoutResult.getCursorRect(TextRange.m7568getEndimpl(retransformed$iv));
                if (MathUtilsKt.m1828findClosestRect9KIMszo(offset, wedgeStartCursorRect, wedgeEndCursorRect) < 0) {
                    selectionWedgeAffinity = new SelectionWedgeAffinity(WedgeAffinity.Start);
                } else {
                    selectionWedgeAffinity = new SelectionWedgeAffinity(WedgeAffinity.End);
                }
                selectionWedgeAffinity2 = selectionWedgeAffinity;
                untransformedCursor = TextRange.m7573getStartimpl(untransformed$iv);
                break;
            case 4:
                z = false;
                Rect wedgeStartCursorRect2 = layoutResult.getCursorRect(TextRange.m7573getStartimpl(retransformed$iv));
                Rect wedgeEndCursorRect2 = layoutResult.getCursorRect(TextRange.m7568getEndimpl(retransformed$iv));
                if (MathUtilsKt.m1828findClosestRect9KIMszo(offset, wedgeStartCursorRect2, wedgeEndCursorRect2) < 0) {
                    untransformedCursor = TextRange.m7573getStartimpl(untransformed$iv);
                } else {
                    untransformedCursor = TextRange.m7568getEndimpl(untransformed$iv);
                }
                break;
            default:
                throw new NoWhenBranchMatchedException();
        }
        long untransformedCursorRange = TextRangeKt.TextRange(untransformedCursor);
        if (TextRange.m7566equalsimpl0(untransformedCursorRange, this.textFieldState.getUntransformedText().getSelection()) && (selectionWedgeAffinity2 == null || Intrinsics.areEqual(selectionWedgeAffinity2, this.textFieldState.getSelectionWedgeAffinity()))) {
            return z;
        }
        this.textFieldState.m1898selectUntransformedCharsIn5zctL8(untransformedCursorRange);
        if (selectionWedgeAffinity2 != null) {
            SelectionWedgeAffinity it = selectionWedgeAffinity2;
            this.textFieldState.setSelectionWedgeAffinity(it);
            return true;
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object detectCursorHandleDragGestures(androidx.compose.ui.input.pointer.PointerInputScope r12, kotlin.coroutines.Continuation<? super kotlin.Unit> r13) throws java.lang.Throwable {
        /*
            r11 = this;
            boolean r0 = r13 instanceof androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState.AnonymousClass1
            if (r0 == 0) goto L14
            r0 = r13
            androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$detectCursorHandleDragGestures$1 r0 = (androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r1 = r0.label
            int r1 = r1 - r2
            r0.label = r1
            goto L19
        L14:
            androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$detectCursorHandleDragGestures$1 r0 = new androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$detectCursorHandleDragGestures$1
            r0.<init>(r13)
        L19:
            r6 = r0
            java.lang.Object r7 = r6.result
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r6.label
            switch(r1) {
                case 0: goto L3f;
                case 1: goto L2e;
                default: goto L25;
            }
        L25:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r0)
            throw r12
        L2e:
            r12 = r11
            java.lang.Object r0 = r6.L$1
            r1 = r0
            kotlin.jvm.internal.Ref$LongRef r1 = (kotlin.jvm.internal.Ref.LongRef) r1
            java.lang.Object r0 = r6.L$0
            r2 = r0
            kotlin.jvm.internal.Ref$LongRef r2 = (kotlin.jvm.internal.Ref.LongRef) r2
            kotlin.ResultKt.throwOnFailure(r7)     // Catch: java.lang.Throwable -> L3d
            goto L85
        L3d:
            r0 = move-exception
            goto L90
        L3f:
            kotlin.ResultKt.throwOnFailure(r7)
            r8 = r11
            r1 = r12
            kotlin.jvm.internal.Ref$LongRef r12 = new kotlin.jvm.internal.Ref$LongRef
            r12.<init>()
            androidx.compose.ui.geometry.Offset$Companion r2 = androidx.compose.ui.geometry.Offset.INSTANCE
            long r2 = r2.m5083getUnspecifiedF1C5BW0()
            r12.element = r2
            kotlin.jvm.internal.Ref$LongRef r2 = new kotlin.jvm.internal.Ref$LongRef
            r2.<init>()
            r9 = r2
            androidx.compose.ui.geometry.Offset$Companion r2 = androidx.compose.ui.geometry.Offset.INSTANCE
            long r2 = r2.m5083getUnspecifiedF1C5BW0()
            r9.element = r2
            androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$$ExternalSyntheticLambda4 r2 = new androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$$ExternalSyntheticLambda4     // Catch: java.lang.Throwable -> L8c
            r2.<init>()     // Catch: java.lang.Throwable -> L8c
            androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$$ExternalSyntheticLambda5 r3 = new androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$$ExternalSyntheticLambda5     // Catch: java.lang.Throwable -> L8c
            r3.<init>()     // Catch: java.lang.Throwable -> L8c
            androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$$ExternalSyntheticLambda6 r4 = new androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$$ExternalSyntheticLambda6     // Catch: java.lang.Throwable -> L8c
            r4.<init>()     // Catch: java.lang.Throwable -> L8c
            androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$$ExternalSyntheticLambda7 r5 = new androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$$ExternalSyntheticLambda7     // Catch: java.lang.Throwable -> L8c
            r5.<init>()     // Catch: java.lang.Throwable -> L8c
            r6.L$0 = r12     // Catch: java.lang.Throwable -> L8c
            r6.L$1 = r9     // Catch: java.lang.Throwable -> L8c
            r10 = 1
            r6.label = r10     // Catch: java.lang.Throwable -> L8c
            java.lang.Object r2 = androidx.compose.foundation.gestures.DragGestureDetectorKt.detectDragGestures(r1, r2, r3, r4, r5, r6)     // Catch: java.lang.Throwable -> L8c
            if (r2 != r0) goto L82
            return r0
        L82:
            r2 = r12
            r12 = r8
            r1 = r9
        L85:
            detectCursorHandleDragGestures$onDragStop(r2, r1, r12)
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L8c:
            r0 = move-exception
            r2 = r12
            r12 = r8
            r1 = r9
        L90:
            detectCursorHandleDragGestures$onDragStop(r2, r1, r12)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState.detectCursorHandleDragGestures(androidx.compose.ui.input.pointer.PointerInputScope, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private static final void detectCursorHandleDragGestures$onDragStop(Ref.LongRef cursorDragStart, Ref.LongRef cursorDragDelta, TextFieldSelectionState this$0) {
        long $this$isSpecified$iv = cursorDragStart.element;
        if ((9223372034707292159L & $this$isSpecified$iv) != InlineClassHelperKt.UnspecifiedPackedFloats) {
            cursorDragStart.element = Offset.INSTANCE.m5083getUnspecifiedF1C5BW0();
            cursorDragDelta.element = Offset.INSTANCE.m5083getUnspecifiedF1C5BW0();
            this$0.clearHandleDragging();
        }
    }

    static final Unit detectCursorHandleDragGestures$lambda$0(Ref.LongRef $cursorDragStart, TextFieldSelectionState this$0, Ref.LongRef $cursorDragDelta, Offset it) {
        $cursorDragStart.element = SelectionHandlesKt.m2051getAdjustedCoordinatesk4lQ0M(this$0.getCursorRect().m5095getBottomCenterF1C5BW0());
        $cursorDragDelta.element = Offset.INSTANCE.m5084getZeroF1C5BW0();
        this$0.setInTouchMode(true);
        this$0.markStartContentVisibleOffset();
        this$0.m1941updateHandleDraggingUv8p0NA(Handle.Cursor, $cursorDragStart.element);
        return Unit.INSTANCE;
    }

    static final Unit detectCursorHandleDragGestures$lambda$1(Ref.LongRef $cursorDragStart, Ref.LongRef $cursorDragDelta, TextFieldSelectionState this$0) {
        detectCursorHandleDragGestures$onDragStop($cursorDragStart, $cursorDragDelta, this$0);
        return Unit.INSTANCE;
    }

    static final Unit detectCursorHandleDragGestures$lambda$2(Ref.LongRef $cursorDragStart, Ref.LongRef $cursorDragDelta, TextFieldSelectionState this$0) {
        detectCursorHandleDragGestures$onDragStop($cursorDragStart, $cursorDragDelta, this$0);
        return Unit.INSTANCE;
    }

    static final Unit detectCursorHandleDragGestures$lambda$3(Ref.LongRef $cursorDragDelta, TextFieldSelectionState this$0, Ref.LongRef $cursorDragStart, PointerInputChange change, Offset dragAmount) {
        $cursorDragDelta.element = Offset.m5073plusMKHz9U($cursorDragDelta.element, dragAmount.m5078unboximpl());
        this$0.m1941updateHandleDraggingUv8p0NA(Handle.Cursor, Offset.m5073plusMKHz9U($cursorDragStart.element, $cursorDragDelta.element));
        if (this$0.m1940placeCursorAtNearestOffsetk4lQ0M(this$0.m1939getHandleDragPositionF1C5BW0())) {
            change.consume();
            HapticFeedback hapticFeedback = this$0.hapticFeedBack;
            if (hapticFeedback != null) {
                hapticFeedback.mo6082performHapticFeedbackCdsT49E(HapticFeedbackType.INSTANCE.m6099getTextHandleMove5zf0vsI());
            }
        }
        return Unit.INSTANCE;
    }

    public final Object textFieldSelectionGestures(PointerInputScope $this$textFieldSelectionGestures, Function0<Unit> function0, Continuation<? super Unit> continuation) {
        Object objTextFieldSelectionGestures = TextFieldSelectionState_androidKt.textFieldSelectionGestures(this, $this$textFieldSelectionGestures, new TextFieldMouseSelectionObserver(function0), new TextFieldTextDragObserver(function0), continuation);
        return objTextFieldSelectionGestures == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objTextFieldSelectionGestures : Unit.INSTANCE;
    }

    /* JADX INFO: compiled from: TextFieldSelectionState.kt */
    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006J'\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\bH\u0016¢\u0006\u0004\b\u0013\u0010\u0014J\u001f\u0010\u0015\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016¢\u0006\u0004\b\u0017\u0010\u0018J/\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0016\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\rH\u0002¢\u0006\u0004\b\u001e\u0010\u001fJ\b\u0010 \u001a\u00020\u0004H\u0016J\u0017\u0010!\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\nH\u0016¢\u0006\u0004\b\"\u0010#J\u0017\u0010$\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\nH\u0016¢\u0006\u0004\b%\u0010#R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000bR\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Landroidx/compose/foundation/text/input/internal/selection/TextFieldSelectionState$TextFieldMouseSelectionObserver;", "Landroidx/compose/foundation/text/selection/MouseSelectionObserver;", "requestFocus", "Lkotlin/Function0;", "", "<init>", "(Landroidx/compose/foundation/text/input/internal/selection/TextFieldSelectionState;Lkotlin/jvm/functions/Function0;)V", "dragBeginOffsetInText", "", "dragBeginPosition", "Landroidx/compose/ui/geometry/Offset;", "J", "isDoubleOrTripleClickOnly", "", "onStart", "downPosition", "adjustment", "Landroidx/compose/foundation/text/selection/SelectionAdjustment;", "clickCount", "onStart-9KIMszo", "(JLandroidx/compose/foundation/text/selection/SelectionAdjustment;I)Z", "onDrag", "dragPosition", "onDrag-3MmeM6k", "(JLandroidx/compose/foundation/text/selection/SelectionAdjustment;)Z", "updateSelection", "Landroidx/compose/ui/text/TextRange;", "layoutResult", "Landroidx/compose/ui/text/TextLayoutResult;", "isStartOfSelection", "updateSelection-12glfjA", "(JLandroidx/compose/foundation/text/selection/SelectionAdjustment;Landroidx/compose/ui/text/TextLayoutResult;Z)J", "onDragDone", "onExtend", "onExtend-k-4lQ0M", "(J)Z", "onExtendDrag", "onExtendDrag-k-4lQ0M", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
    private final class TextFieldMouseSelectionObserver implements MouseSelectionObserver {
        private int dragBeginOffsetInText = -1;
        private long dragBeginPosition = Offset.INSTANCE.m5083getUnspecifiedF1C5BW0();
        private boolean isDoubleOrTripleClickOnly = true;
        private final Function0<Unit> requestFocus;

        public TextFieldMouseSelectionObserver(Function0<Unit> function0) {
            this.requestFocus = function0;
        }

        @Override // androidx.compose.foundation.text.selection.MouseSelectionObserver
        /* JADX INFO: renamed from: onStart-9KIMszo, reason: not valid java name */
        public boolean mo1947onStart9KIMszo(long downPosition, SelectionAdjustment adjustment, int clickCount) {
            TextLayoutResult layoutResult = TextFieldSelectionState.this.getTextLayoutState().getLayoutResult();
            if (TextFieldSelectionState.this.getEnabled() && layoutResult != null) {
                if (!(TextFieldSelectionState.this.getTextFieldState().getVisualText().length() == 0)) {
                    this.isDoubleOrTripleClickOnly = clickCount >= 2;
                    TextFieldSelectionState.this.setDirectDragGestureInitiator(InputType.Mouse);
                    this.requestFocus.invoke();
                    TextFieldSelectionState.this.previousRawDragOffset = -1;
                    this.dragBeginOffsetInText = -1;
                    this.dragBeginPosition = downPosition;
                    long newSelection = m1943updateSelection12glfjA(downPosition, adjustment, layoutResult, true);
                    this.dragBeginOffsetInText = TextRange.m7573getStartimpl(newSelection);
                    return true;
                }
            }
            return false;
        }

        @Override // androidx.compose.foundation.text.selection.MouseSelectionObserver
        /* JADX INFO: renamed from: onDrag-3MmeM6k, reason: not valid java name */
        public boolean mo1944onDrag3MmeM6k(long dragPosition, SelectionAdjustment adjustment) {
            TextLayoutResult layoutResult = TextFieldSelectionState.this.getTextLayoutState().getLayoutResult();
            if (TextFieldSelectionState.this.getEnabled() && layoutResult != null) {
                if (!(TextFieldSelectionState.this.getTextFieldState().getVisualText().length() == 0)) {
                    long prevSelection = TextFieldSelectionState.this.getTextFieldState().getVisualText().getSelection();
                    long newSelection = m1943updateSelection12glfjA(dragPosition, adjustment, layoutResult, false);
                    if (!TextRange.m7566equalsimpl0(prevSelection, newSelection)) {
                        this.isDoubleOrTripleClickOnly = false;
                    }
                    return true;
                }
            }
            return false;
        }

        /* JADX INFO: renamed from: updateSelection-12glfjA, reason: not valid java name */
        private final long m1943updateSelection12glfjA(long dragPosition, SelectionAdjustment adjustment, TextLayoutResult layoutResult, boolean isStartOfSelection) {
            int iM1879getOffsetForPosition3MmeM6k;
            int textLength = layoutResult.getLayoutInput().getText().length();
            if (this.dragBeginOffsetInText >= 0 && this.dragBeginOffsetInText <= textLength) {
                iM1879getOffsetForPosition3MmeM6k = this.dragBeginOffsetInText;
            } else {
                iM1879getOffsetForPosition3MmeM6k = TextFieldSelectionState.this.getTextLayoutState().m1879getOffsetForPosition3MmeM6k(this.dragBeginPosition, false);
            }
            int startOffset = iM1879getOffsetForPosition3MmeM6k;
            int endOffset = TextFieldSelectionState.this.getTextLayoutState().m1879getOffsetForPosition3MmeM6k(dragPosition, false);
            long newSelection = TextFieldSelectionState.this.m1942updateSelectionQkiN0lo$foundation(TextFieldSelectionState.this.getTextFieldState().getVisualText(), startOffset, endOffset, false, adjustment, false, isStartOfSelection, null);
            if (this.dragBeginOffsetInText == -1 && !TextRange.m7567getCollapsedimpl(newSelection)) {
                this.dragBeginOffsetInText = TextRange.m7573getStartimpl(newSelection);
            }
            if (TextRange.m7572getReversedimpl(newSelection)) {
                newSelection = TextFieldSelectionStateKt.m1949reverse5zctL8(newSelection);
            }
            TextFieldSelectionState.this.getTextFieldState().m1897selectCharsIn5zctL8(newSelection);
            TextFieldSelectionState.this.updateTextToolbarState(TextToolbarState.Selection);
            return newSelection;
        }

        @Override // androidx.compose.foundation.text.selection.MouseSelectionObserver
        public void onDragDone() {
            TextFieldSelectionState.this.setDirectDragGestureInitiator(InputType.None);
            if (this.isDoubleOrTripleClickOnly) {
                TextFieldSelectionState.this.maybeSuggestSelectionRange();
            }
        }

        @Override // androidx.compose.foundation.text.selection.MouseSelectionObserver
        /* JADX INFO: renamed from: onExtend-k-4lQ0M, reason: not valid java name */
        public boolean mo1945onExtendk4lQ0M(long downPosition) {
            TextLayoutResult layoutResult = TextFieldSelectionState.this.getTextLayoutState().getLayoutResult();
            if (TextFieldSelectionState.this.getEnabled() && layoutResult != null) {
                if (!(TextFieldSelectionState.this.getTextFieldState().getVisualText().length() == 0)) {
                    this.isDoubleOrTripleClickOnly = false;
                    this.requestFocus.invoke();
                    m1943updateSelection12glfjA(downPosition, SelectionAdjustment.INSTANCE.getNone(), layoutResult, false);
                    return true;
                }
            }
            return false;
        }

        @Override // androidx.compose.foundation.text.selection.MouseSelectionObserver
        /* JADX INFO: renamed from: onExtendDrag-k-4lQ0M, reason: not valid java name */
        public boolean mo1946onExtendDragk4lQ0M(long dragPosition) {
            return true;
        }
    }

    /* JADX INFO: compiled from: TextFieldSelectionState.kt */
    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0011\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\u0013\u001a\u00020\u0004H\u0002J\u0017\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\nH\u0016¢\u0006\u0004\b\u0016\u0010\u0017J\b\u0010\u0018\u001a\u00020\u0004H\u0016J\b\u0010\u0019\u001a\u00020\u0004H\u0016J\b\u0010\u001a\u001a\u00020\u0004H\u0016J\u001f\u0010\u001b\u001a\u00020\u00042\u0006\u0010\u001c\u001a\u00020\n2\u0006\u0010\u001d\u001a\u00020\u0012H\u0016¢\u0006\u0004\b\u001e\u0010\u001fJ\u0017\u0010 \u001a\u00020\u00042\u0006\u0010!\u001a\u00020\nH\u0016¢\u0006\u0004\b\"\u0010\u0017R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000bR\u0010\u0010\f\u001a\u00020\nX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000bR\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Landroidx/compose/foundation/text/input/internal/selection/TextFieldSelectionState$TextFieldTextDragObserver;", "Landroidx/compose/foundation/text/TextDragObserver;", "requestFocus", "Lkotlin/Function0;", "", "<init>", "(Landroidx/compose/foundation/text/input/internal/selection/TextFieldSelectionState;Lkotlin/jvm/functions/Function0;)V", "dragBeginOffsetInText", "", "dragBeginPosition", "Landroidx/compose/ui/geometry/Offset;", "J", "dragTotalDistance", "actingHandle", "Landroidx/compose/foundation/text/Handle;", "isLongPressSelectionOnly", "", "selectionAdjustmentMode", "Landroidx/compose/foundation/text/selection/SelectionAdjustment;", "onDragStop", "onDown", "point", "onDown-k-4lQ0M", "(J)V", "onUp", "onStop", "onCancel", "onStart", "startPoint", "selectionAdjustment", "onStart-3MmeM6k", "(JLandroidx/compose/foundation/text/selection/SelectionAdjustment;)V", "onDrag", "delta", "onDrag-k-4lQ0M", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
    private final class TextFieldTextDragObserver implements TextDragObserver {
        private final Function0<Unit> requestFocus;
        private int dragBeginOffsetInText = -1;
        private long dragBeginPosition = Offset.INSTANCE.m5083getUnspecifiedF1C5BW0();
        private long dragTotalDistance = Offset.INSTANCE.m5084getZeroF1C5BW0();
        private Handle actingHandle = Handle.SelectionEnd;
        private boolean isLongPressSelectionOnly = true;
        private SelectionAdjustment selectionAdjustmentMode = SelectionAdjustment.INSTANCE.getNone();

        public TextFieldTextDragObserver(Function0<Unit> function0) {
            this.requestFocus = function0;
        }

        private final void onDragStop() {
            long $this$isSpecified$iv = this.dragBeginPosition;
            if ((9223372034707292159L & $this$isSpecified$iv) != InlineClassHelperKt.UnspecifiedPackedFloats) {
                TextFieldSelectionState.this.clearHandleDragging();
                this.dragBeginOffsetInText = -1;
                this.dragBeginPosition = Offset.INSTANCE.m5083getUnspecifiedF1C5BW0();
                this.dragTotalDistance = Offset.INSTANCE.m5084getZeroF1C5BW0();
                TextFieldSelectionState.this.previousRawDragOffset = -1;
                this.selectionAdjustmentMode = SelectionAdjustment.INSTANCE.getNone();
                TextFieldSelectionState.this.setDirectDragGestureInitiator(InputType.None);
                this.requestFocus.invoke();
                if (this.isLongPressSelectionOnly) {
                    TextFieldSelectionState.this.maybeSuggestSelectionRange();
                }
            }
        }

        @Override // androidx.compose.foundation.text.TextDragObserver
        /* JADX INFO: renamed from: onDown-k-4lQ0M */
        public void mo1639onDownk4lQ0M(long point) {
        }

        @Override // androidx.compose.foundation.text.TextDragObserver
        public void onUp() {
        }

        @Override // androidx.compose.foundation.text.TextDragObserver
        public void onStop() {
            onDragStop();
        }

        @Override // androidx.compose.foundation.text.TextDragObserver
        public void onCancel() {
            onDragStop();
        }

        @Override // androidx.compose.foundation.text.TextDragObserver
        /* JADX INFO: renamed from: onStart-3MmeM6k */
        public void mo1641onStart3MmeM6k(long startPoint, SelectionAdjustment selectionAdjustment) {
            if (TextFieldSelectionState.this.getEnabled()) {
                TextFieldSelectionState.this.m1941updateHandleDraggingUv8p0NA(this.actingHandle, startPoint);
                TextFieldSelectionState.this.setShowCursorHandle(false);
                TextFieldSelectionState.this.setDirectDragGestureInitiator(InputType.Touch);
                this.dragBeginPosition = startPoint;
                this.dragTotalDistance = Offset.INSTANCE.m5084getZeroF1C5BW0();
                TextFieldSelectionState.this.previousRawDragOffset = -1;
                this.isLongPressSelectionOnly = true;
                this.selectionAdjustmentMode = selectionAdjustment;
                if (TextFieldSelectionState.this.getTextLayoutState().getLayoutResult() == null) {
                    return;
                }
                boolean zM1880isPositionOnTextk4lQ0M = TextFieldSelectionState.this.getTextLayoutState().m1880isPositionOnTextk4lQ0M(startPoint);
                TextFieldSelectionState textFieldSelectionState = TextFieldSelectionState.this;
                if (!zM1880isPositionOnTextk4lQ0M) {
                    int offset = TextLayoutState.m1876getOffsetForPosition3MmeM6k$default(textFieldSelectionState.getTextLayoutState(), startPoint, false, 2, null);
                    HapticFeedback hapticFeedBack = TextFieldSelectionState.this.getHapticFeedBack();
                    if (hapticFeedBack != null) {
                        hapticFeedBack.mo6082performHapticFeedbackCdsT49E(HapticFeedbackType.INSTANCE.m6095getLongPress5zf0vsI());
                    }
                    TextFieldSelectionState.this.getTextFieldState().placeCursorBeforeCharAt(offset);
                    TextFieldSelectionState.this.setShowCursorHandle(true);
                    this.isLongPressSelectionOnly = false;
                    TextFieldSelectionState.this.updateTextToolbarState(TextToolbarState.Cursor);
                    return;
                }
                if (textFieldSelectionState.getTextFieldState().getVisualText().length() == 0) {
                    return;
                }
                int offset2 = TextLayoutState.m1876getOffsetForPosition3MmeM6k$default(TextFieldSelectionState.this.getTextLayoutState(), startPoint, false, 2, null);
                long newSelection = TextFieldSelectionState.m1938updateSelectionQkiN0lo$foundation$default(TextFieldSelectionState.this, new TextFieldCharSequence(TextFieldSelectionState.this.getTextFieldState().getVisualText(), TextRange.INSTANCE.m7578getZerod9O1mEE(), null, null, null, null, 60, null), offset2, offset2, false, this.selectionAdjustmentMode, false, false, HapticFeedbackType.m6083boximpl(HapticFeedbackType.INSTANCE.m6095getLongPress5zf0vsI()), 96, null);
                TextFieldSelectionState.this.getTextFieldState().m1897selectCharsIn5zctL8(newSelection);
                TextFieldSelectionState.this.updateTextToolbarState(TextToolbarState.Selection);
                this.dragBeginOffsetInText = TextRange.m7573getStartimpl(newSelection);
            }
        }

        @Override // androidx.compose.foundation.text.TextDragObserver
        /* JADX INFO: renamed from: onDrag-k-4lQ0M */
        public void mo1640onDragk4lQ0M(long delta) {
            Integer num;
            int iIntValue;
            int startOffset;
            int endOffset;
            SelectionAdjustment adjustment;
            TextLayoutInput layoutInput;
            AnnotatedString text;
            Handle handle;
            SelectionAdjustment none;
            if (TextFieldSelectionState.this.getEnabled() && TextFieldSelectionState.this.getTextLayoutState().getLayoutResult() != null) {
                if (!(TextFieldSelectionState.this.getTextFieldState().getVisualText().length() == 0)) {
                    this.dragTotalDistance = Offset.m5073plusMKHz9U(this.dragTotalDistance, delta);
                    long currentDragPosition = Offset.m5073plusMKHz9U(this.dragBeginPosition, this.dragTotalDistance);
                    if (this.dragBeginOffsetInText < 0 && !TextFieldSelectionState.this.getTextLayoutState().m1880isPositionOnTextk4lQ0M(currentDragPosition)) {
                        int startOffset2 = TextLayoutState.m1876getOffsetForPosition3MmeM6k$default(TextFieldSelectionState.this.getTextLayoutState(), this.dragBeginPosition, false, 2, null);
                        int endOffset2 = TextLayoutState.m1876getOffsetForPosition3MmeM6k$default(TextFieldSelectionState.this.getTextLayoutState(), currentDragPosition, false, 2, null);
                        if (startOffset2 == endOffset2) {
                            none = SelectionAdjustment.INSTANCE.getNone();
                        } else {
                            none = this.selectionAdjustmentMode;
                        }
                        startOffset = startOffset2;
                        endOffset = endOffset2;
                        adjustment = none;
                    } else {
                        if (ComposeFoundationFlags.isConcurrentTextFieldSelectionFixEnabled) {
                            TextLayoutResult layoutResult = TextFieldSelectionState.this.getTextLayoutState().getLayoutResult();
                            int textLength = (layoutResult == null || (layoutInput = layoutResult.getLayoutInput()) == null || (text = layoutInput.getText()) == null) ? 0 : text.length();
                            Integer numValueOf = Integer.valueOf(this.dragBeginOffsetInText);
                            int it = numValueOf.intValue();
                            num = it >= 0 && it <= textLength ? numValueOf : null;
                            iIntValue = num != null ? num.intValue() : TextFieldSelectionState.this.getTextLayoutState().m1879getOffsetForPosition3MmeM6k(this.dragBeginPosition, false);
                        } else {
                            Integer numValueOf2 = Integer.valueOf(this.dragBeginOffsetInText);
                            num = numValueOf2.intValue() >= 0 ? numValueOf2 : null;
                            iIntValue = num != null ? num.intValue() : TextFieldSelectionState.this.getTextLayoutState().m1879getOffsetForPosition3MmeM6k(this.dragBeginPosition, false);
                        }
                        int startOffset3 = iIntValue;
                        int endOffset3 = TextFieldSelectionState.this.getTextLayoutState().m1879getOffsetForPosition3MmeM6k(currentDragPosition, false);
                        if (this.dragBeginOffsetInText < 0 && startOffset3 == endOffset3) {
                            return;
                        }
                        SelectionAdjustment adjustment2 = this.selectionAdjustmentMode;
                        TextFieldSelectionState.this.updateTextToolbarState(TextToolbarState.Selection);
                        startOffset = startOffset3;
                        endOffset = endOffset3;
                        adjustment = adjustment2;
                    }
                    long prevSelection = TextFieldSelectionState.this.getTextFieldState().getVisualText().getSelection();
                    long newSelection = TextFieldSelectionState.m1938updateSelectionQkiN0lo$foundation$default(TextFieldSelectionState.this, TextFieldSelectionState.this.getTextFieldState().getVisualText(), startOffset, endOffset, false, adjustment, false, false, HapticFeedbackType.m6083boximpl(HapticFeedbackType.INSTANCE.m6099getTextHandleMove5zf0vsI()), 64, null);
                    if (this.dragBeginOffsetInText == -1 && !TextRange.m7567getCollapsedimpl(newSelection)) {
                        this.dragBeginOffsetInText = TextRange.m7573getStartimpl(newSelection);
                    }
                    if (TextRange.m7572getReversedimpl(newSelection)) {
                        newSelection = TextFieldSelectionStateKt.m1949reverse5zctL8(newSelection);
                    }
                    if (!TextRange.m7566equalsimpl0(newSelection, prevSelection)) {
                        if (TextRange.m7573getStartimpl(newSelection) != TextRange.m7573getStartimpl(prevSelection) && TextRange.m7568getEndimpl(newSelection) == TextRange.m7568getEndimpl(prevSelection)) {
                            handle = Handle.SelectionStart;
                        } else if (TextRange.m7573getStartimpl(newSelection) != TextRange.m7573getStartimpl(prevSelection) || TextRange.m7568getEndimpl(newSelection) == TextRange.m7568getEndimpl(prevSelection)) {
                            float newMiddle = (TextRange.m7573getStartimpl(newSelection) + TextRange.m7568getEndimpl(newSelection)) / 2.0f;
                            float prevMiddle = (TextRange.m7573getStartimpl(prevSelection) + TextRange.m7568getEndimpl(prevSelection)) / 2.0f;
                            if (newMiddle > prevMiddle) {
                                handle = Handle.SelectionEnd;
                            } else {
                                handle = Handle.SelectionStart;
                            }
                        } else {
                            handle = Handle.SelectionEnd;
                        }
                        this.actingHandle = handle;
                        this.isLongPressSelectionOnly = false;
                    }
                    if (TextRange.m7567getCollapsedimpl(prevSelection) || !TextRange.m7567getCollapsedimpl(newSelection)) {
                        TextFieldSelectionState.this.getTextFieldState().m1897selectCharsIn5zctL8(newSelection);
                    }
                    TextFieldSelectionState.this.m1941updateHandleDraggingUv8p0NA(this.actingHandle, currentDragPosition);
                }
            }
        }
    }

    public final void maybeSuggestSelectionRange() {
        PlatformSelectionBehaviors platformSelectionBehaviors = this.platformSelectionBehaviors;
        if (platformSelectionBehaviors == null) {
            return;
        }
        CharSequence text = this.textFieldState.getVisualText().getText();
        long selection = this.textFieldState.getVisualText().getSelection();
        if ((text.length() > 0) && !TextRange.m7567getCollapsedimpl(selection)) {
            BuildersKt__Builders_commonKt.launch$default(this.coroutineScope, null, CoroutineStart.UNDISPATCHED, new C02291(platformSelectionBehaviors, text, selection, this, null), 1, null);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$maybeSuggestSelectionRange$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: TextFieldSelectionState.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$maybeSuggestSelectionRange$1", f = "TextFieldSelectionState.kt", i = {}, l = {1120}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class C02291 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ PlatformSelectionBehaviors $platformSelectionBehaviors;
        final /* synthetic */ long $selection;
        final /* synthetic */ CharSequence $text;
        int label;
        final /* synthetic */ TextFieldSelectionState this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C02291(PlatformSelectionBehaviors platformSelectionBehaviors, CharSequence charSequence, long j, TextFieldSelectionState textFieldSelectionState, Continuation<? super C02291> continuation) {
            super(2, continuation);
            this.$platformSelectionBehaviors = platformSelectionBehaviors;
            this.$text = charSequence;
            this.$selection = j;
            this.this$0 = textFieldSelectionState;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C02291(this.$platformSelectionBehaviors, this.$text, this.$selection, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C02291) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    this.label = 1;
                    Object objMo2035suggestSelectionForLongPressOrDoubleClickpYaCww = this.$platformSelectionBehaviors.mo2035suggestSelectionForLongPressOrDoubleClickpYaCww(this.$text, this.$selection, this);
                    if (objMo2035suggestSelectionForLongPressOrDoubleClickpYaCww == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    $result = objMo2035suggestSelectionForLongPressOrDoubleClickpYaCww;
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            TextRange suggestedSelection = (TextRange) $result;
            if (!this.this$0.isPassword && suggestedSelection != null && Intrinsics.areEqual(this.this$0.getTextFieldState().getVisualText().getText(), this.$text) && TextRange.m7566equalsimpl0(this.this$0.getTextFieldState().getVisualText().getSelection(), this.$selection)) {
                if (!TextRange.m7566equalsimpl0(suggestedSelection.getPackedValue(), this.this$0.getTextFieldState().getVisualText().getSelection())) {
                    this.this$0.getTextFieldState().m1897selectCharsIn5zctL8(suggestedSelection.getPackedValue());
                }
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00ca  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00e8  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object detectSelectionHandleDragGestures(androidx.compose.ui.input.pointer.PointerInputScope r18, boolean r19, kotlin.coroutines.Continuation<? super kotlin.Unit> r20) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 244
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState.detectSelectionHandleDragGestures(androidx.compose.ui.input.pointer.PointerInputScope, boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private static final void detectSelectionHandleDragGestures$onDragStop(Ref.LongRef dragBeginPosition, TextFieldSelectionState this$0, Ref.LongRef dragTotalDistance) {
        long $this$isSpecified$iv = dragBeginPosition.element;
        if ((9223372034707292159L & $this$isSpecified$iv) != InlineClassHelperKt.UnspecifiedPackedFloats) {
            this$0.clearHandleDragging();
            dragBeginPosition.element = Offset.INSTANCE.m5083getUnspecifiedF1C5BW0();
            dragTotalDistance.element = Offset.INSTANCE.m5084getZeroF1C5BW0();
            this$0.previousRawDragOffset = -1;
        }
    }

    static final Unit detectSelectionHandleDragGestures$lambda$0(Ref.LongRef $dragBeginPosition, TextFieldSelectionState this$0, boolean $isStartHandle, Handle $handle, Ref.LongRef $dragTotalDistance, Offset it) {
        $dragBeginPosition.element = SelectionHandlesKt.m2051getAdjustedCoordinatesk4lQ0M(this$0.m1932getHandlePositiontuRUvjQ($isStartHandle));
        this$0.m1941updateHandleDraggingUv8p0NA($handle, $dragBeginPosition.element);
        $dragTotalDistance.element = Offset.INSTANCE.m5084getZeroF1C5BW0();
        this$0.previousRawDragOffset = -1;
        return Unit.INSTANCE;
    }

    static final Unit detectSelectionHandleDragGestures$lambda$1(Ref.LongRef $dragBeginPosition, TextFieldSelectionState this$0, Ref.LongRef $dragTotalDistance) {
        detectSelectionHandleDragGestures$onDragStop($dragBeginPosition, this$0, $dragTotalDistance);
        return Unit.INSTANCE;
    }

    static final Unit detectSelectionHandleDragGestures$lambda$2(Ref.LongRef $dragBeginPosition, TextFieldSelectionState this$0, Ref.LongRef $dragTotalDistance) {
        detectSelectionHandleDragGestures$onDragStop($dragBeginPosition, this$0, $dragTotalDistance);
        return Unit.INSTANCE;
    }

    static final Unit detectSelectionHandleDragGestures$lambda$3(Ref.LongRef $dragTotalDistance, TextFieldSelectionState this$0, Handle $handle, Ref.LongRef $dragBeginPosition, boolean $isStartHandle, PointerInputChange pointerInputChange, Offset delta) {
        int startOffset;
        int endOffset;
        $dragTotalDistance.element = Offset.m5073plusMKHz9U($dragTotalDistance.element, delta.m5078unboximpl());
        TextLayoutResult layoutResult = this$0.textLayoutState.getLayoutResult();
        if (layoutResult == null) {
            return Unit.INSTANCE;
        }
        this$0.m1941updateHandleDraggingUv8p0NA($handle, Offset.m5073plusMKHz9U($dragBeginPosition.element, $dragTotalDistance.element));
        if ($isStartHandle) {
            startOffset = layoutResult.m7543getOffsetForPositionk4lQ0M(this$0.m1939getHandleDragPositionF1C5BW0());
        } else {
            startOffset = TextRange.m7573getStartimpl(this$0.textFieldState.getVisualText().getSelection());
        }
        if ($isStartHandle) {
            endOffset = TextRange.m7568getEndimpl(this$0.textFieldState.getVisualText().getSelection());
        } else {
            endOffset = layoutResult.m7543getOffsetForPositionk4lQ0M(this$0.m1939getHandleDragPositionF1C5BW0());
        }
        long prevSelection = this$0.textFieldState.getVisualText().getSelection();
        long newSelection = m1938updateSelectionQkiN0lo$foundation$default(this$0, this$0.textFieldState.getVisualText(), startOffset, endOffset, $isStartHandle, SelectionAdjustment.INSTANCE.getCharacterWithWordAccelerate(), false, false, HapticFeedbackType.m6083boximpl(HapticFeedbackType.INSTANCE.m6099getTextHandleMove5zf0vsI()), 96, null);
        if (TextRange.m7567getCollapsedimpl(prevSelection) || !TextRange.m7567getCollapsedimpl(newSelection)) {
            this$0.textFieldState.m1897selectCharsIn5zctL8(newSelection);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$observeTextChanges$3, reason: invalid class name */
    /* JADX INFO: compiled from: TextFieldSelectionState.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    static final /* synthetic */ class AnonymousClass3 extends FunctionReferenceImpl implements Function2<TextFieldCharSequence, CharSequence, Boolean> {
        public static final AnonymousClass3 INSTANCE = new AnonymousClass3();

        AnonymousClass3() {
            super(2, TextFieldCharSequence.class, "contentEquals", "contentEquals(Ljava/lang/CharSequence;)Z", 0);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Boolean invoke(TextFieldCharSequence p0, CharSequence p1) {
            return Boolean.valueOf(p0.contentEquals(p1));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object observeTextChanges(Continuation<? super Unit> continuation) {
        Object objCollect = FlowKt.drop(FlowKt.distinctUntilChanged(SnapshotStateKt.snapshotFlow(new Function0() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return this.f$0.textFieldState.getVisualText();
            }
        }), AnonymousClass3.INSTANCE), 1).collect(new FlowCollector() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState.observeTextChanges.4
            @Override // kotlinx.coroutines.flow.FlowCollector
            public /* bridge */ /* synthetic */ Object emit(Object value, Continuation $completion) {
                return emit((TextFieldCharSequence) value, (Continuation<? super Unit>) $completion);
            }

            public final Object emit(TextFieldCharSequence it, Continuation<? super Unit> continuation2) {
                TextFieldSelectionState.this.setShowCursorHandle(false);
                TextFieldSelectionState.this.updateTextToolbarState(TextToolbarState.None);
                return Unit.INSTANCE;
            }
        }, continuation);
        return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean observeTextToolbarVisibility$lambda$1$0(Rect it) {
        return it == null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object observeTextToolbarVisibility(Continuation<? super Unit> continuation) {
        Flow flowDistinctUntilChangedBy;
        Flow $this$observeTextToolbarVisibility_u24lambda_u241 = SnapshotStateKt.snapshotFlow(new Function0() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return this.f$0.getDerivedVisibleContentBounds$foundation();
            }
        });
        if (ComposeFoundationFlags.isNewContextMenuEnabled) {
            flowDistinctUntilChangedBy = FlowKt.distinctUntilChangedBy($this$observeTextToolbarVisibility_u24lambda_u241, new Function1() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(TextFieldSelectionState.observeTextToolbarVisibility$lambda$1$0((Rect) obj));
                }
            });
        } else {
            flowDistinctUntilChangedBy = $this$observeTextToolbarVisibility_u24lambda_u241;
        }
        Object objCollect = flowDistinctUntilChangedBy.collect(new FlowCollector() { // from class: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState.observeTextToolbarVisibility.4
            @Override // kotlinx.coroutines.flow.FlowCollector
            public /* bridge */ /* synthetic */ Object emit(Object value, Continuation $completion) {
                return emit((Rect) value, (Continuation<? super Unit>) $completion);
            }

            public final Object emit(Rect rect, Continuation<? super Unit> continuation2) {
                TextFieldSelectionState textFieldSelectionState = TextFieldSelectionState.this;
                if (rect != null) {
                    Object objShowTextToolbar = textFieldSelectionState.showTextToolbar(rect, continuation2);
                    return objShowTextToolbar == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objShowTextToolbar : Unit.INSTANCE;
                }
                textFieldSelectionState.hideTextToolbar();
                return Unit.INSTANCE;
            }
        }, continuation);
        return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
    }

    public final Rect getDerivedVisibleContentBounds$foundation() {
        State $this$getValue$iv = this.derivedVisibleContentBounds;
        return (Rect) $this$getValue$iv.getValue();
    }

    static final Rect derivedVisibleContentBounds_delegate$lambda$0(TextFieldSelectionState this$0) {
        LayoutCoordinates textLayoutCoordinates;
        boolean isCollapsedSelection = TextRange.m7567getCollapsedimpl(this$0.textFieldState.getVisualText().getSelection());
        boolean textToolbarStateVisible = (isCollapsedSelection && this$0.getTextToolbarState() == TextToolbarState.Cursor) || (!isCollapsedSelection && this$0.getTextToolbarState() == TextToolbarState.Selection);
        boolean textToolbarVisible = textToolbarStateVisible && this$0.getDraggingHandle() == null && this$0.isInTouchMode();
        if (!textToolbarVisible || (textLayoutCoordinates = this$0.getTextLayoutCoordinates()) == null) {
            return null;
        }
        Rect visibleBounds = SelectionManagerKt.visibleBounds(textLayoutCoordinates);
        long visibleBoundsTopLeftInRoot = textLayoutCoordinates.mo6794localToRootMKHz9U(visibleBounds.m5103getTopLeftF1C5BW0());
        Rect visibleBoundsInRoot = RectKt.m5108Recttz77jQw(visibleBoundsTopLeftInRoot, visibleBounds.m5101getSizeNHjbRc());
        Rect contentRect = this$0.getContentRect();
        if (!contentRect.overlaps(visibleBoundsInRoot)) {
            return null;
        }
        return contentRect.intersect(visibleBoundsInRoot);
    }

    private final Rect getContentRect() {
        LayoutCoordinates textLayoutCoordinates = getTextLayoutCoordinates();
        if (textLayoutCoordinates == null) {
            androidx.compose.foundation.internal.InlineClassHelperKt.throwIllegalStateExceptionForNullCheck("textLayoutCoordinates should not be null.");
            throw new KotlinNothingValueException();
        }
        TextFieldCharSequence text = this.textFieldState.getVisualText();
        if (TextRange.m7567getCollapsedimpl(text.getSelection())) {
            Rect cursorRect = getCursorRect();
            long topLeft = textLayoutCoordinates.mo6794localToRootMKHz9U(cursorRect.m5103getTopLeftF1C5BW0());
            return RectKt.m5108Recttz77jQw(topLeft, cursorRect.m5101getSizeNHjbRc());
        }
        long startOffset = textLayoutCoordinates.mo6794localToRootMKHz9U(m1932getHandlePositiontuRUvjQ(true));
        long endOffset = textLayoutCoordinates.mo6794localToRootMKHz9U(m1932getHandlePositiontuRUvjQ(false));
        TextLayoutResult layoutResult = this.textLayoutState.getLayoutResult();
        if (layoutResult == null) {
            return Rect.INSTANCE.getZero();
        }
        float y$iv = layoutResult.getCursorRect(TextRange.m7573getStartimpl(text.getSelection())).getTop();
        long v1$iv$iv = Float.floatToRawIntBits(0.0f);
        long v2$iv$iv = Float.floatToRawIntBits(y$iv);
        long arg0$iv = textLayoutCoordinates.mo6794localToRootMKHz9U(Offset.m5060constructorimpl((v1$iv$iv << 32) | (v2$iv$iv & 4294967295L)));
        int bits$iv$iv$iv = (int) (arg0$iv & 4294967295L);
        float startTop = Float.intBitsToFloat(bits$iv$iv$iv);
        float y$iv2 = layoutResult.getCursorRect(TextRange.m7568getEndimpl(text.getSelection())).getTop();
        long v1$iv$iv2 = Float.floatToRawIntBits(0.0f);
        long v2$iv$iv2 = Float.floatToRawIntBits(y$iv2);
        long arg0$iv2 = textLayoutCoordinates.mo6794localToRootMKHz9U(Offset.m5060constructorimpl((v1$iv$iv2 << 32) | (v2$iv$iv2 & 4294967295L)));
        int bits$iv$iv$iv2 = (int) (arg0$iv2 & 4294967295L);
        float endTop = Float.intBitsToFloat(bits$iv$iv$iv2);
        int bits$iv$iv$iv3 = (int) (startOffset >> 32);
        int bits$iv$iv$iv4 = (int) (endOffset >> 32);
        float fMin = Math.min(Float.intBitsToFloat(bits$iv$iv$iv3), Float.intBitsToFloat(bits$iv$iv$iv4));
        int bits$iv$iv$iv5 = (int) (startOffset >> 32);
        int bits$iv$iv$iv6 = (int) (endOffset >> 32);
        long arg0$iv3 = startOffset & 4294967295L;
        int bits$iv$iv$iv7 = (int) arg0$iv3;
        int bits$iv$iv$iv8 = (int) (endOffset & 4294967295L);
        return new Rect(fMin, Math.min(startTop, endTop), Math.max(Float.intBitsToFloat(bits$iv$iv$iv5), Float.intBitsToFloat(bits$iv$iv$iv6)), Math.max(Float.intBitsToFloat(bits$iv$iv$iv7), Float.intBitsToFloat(bits$iv$iv$iv8)));
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0059  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final androidx.compose.foundation.text.input.internal.selection.TextFieldHandleState getSelectionHandleState$foundation(boolean r19, boolean r20) {
        /*
            Method dump skipped, instruction units count: 208
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState.getSelectionHandleState$foundation(boolean, boolean):androidx.compose.foundation.text.input.internal.selection.TextFieldHandleState");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getHandlePosition-tuRUvjQ, reason: not valid java name */
    public final long m1932getHandlePositiontuRUvjQ(boolean isStartHandle) {
        int offset;
        TextLayoutResult layoutResult = this.textLayoutState.getLayoutResult();
        if (layoutResult == null) {
            return Offset.INSTANCE.m5084getZeroF1C5BW0();
        }
        long selection = this.textFieldState.getVisualText().getSelection();
        if (isStartHandle) {
            offset = TextRange.m7573getStartimpl(selection);
        } else {
            offset = TextRange.m7568getEndimpl(selection);
        }
        return TextSelectionDelegateKt.getSelectionHandleCoordinates(layoutResult, offset, isStartHandle, TextRange.m7572getReversedimpl(selection));
    }

    /* JADX INFO: renamed from: updateHandleDragging-Uv8p0NA, reason: not valid java name */
    public final void m1941updateHandleDraggingUv8p0NA(Handle handle, long position) {
        setDraggingHandle(handle);
        m1936setRawHandleDragPositionk4lQ0M(position);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void markStartContentVisibleOffset() {
        m1937setStartTextLayoutPositionInWindowk4lQ0M(m1931getCurrentTextLayoutPositionInWindowF1C5BW0());
    }

    public final void clearHandleDragging() {
        setDraggingHandle(null);
        m1936setRawHandleDragPositionk4lQ0M(Offset.INSTANCE.m5083getUnspecifiedF1C5BW0());
        m1937setStartTextLayoutPositionInWindowk4lQ0M(Offset.INSTANCE.m5083getUnspecifiedF1C5BW0());
    }

    public final boolean canShowCutMenuItem() {
        return (!TextRange.m7567getCollapsedimpl(getTextFieldState().getVisualText().getSelection()) && getEditable$foundation() && !this.isPassword) && ClipboardUtils_androidKt.isWriteSupported(this.clipboard);
    }

    public final boolean isCutAllowed() {
        return (TextRange.m7567getCollapsedimpl(getTextFieldState().getVisualText().getSelection()) || !getEditable$foundation() || this.isPassword) ? false : true;
    }

    public final Object cut(Continuation<? super Unit> continuation) {
        Object clipEntry;
        AnnotatedString cutValue = cutWithResult();
        return (cutValue != null && (clipEntry = this.clipboard.setClipEntry(ClipboardUtils_androidKt.toClipEntry(cutValue), continuation)) == IntrinsicsKt.getCOROUTINE_SUSPENDED()) ? clipEntry : Unit.INSTANCE;
    }

    public final AnnotatedString cutWithResult() {
        if (!((TextRange.m7567getCollapsedimpl(getTextFieldState().getVisualText().getSelection()) || !getEditable$foundation() || this.isPassword) ? false : true)) {
            return null;
        }
        CharSequence selectedText = TextFieldCharSequenceKt.getSelectedText(this.textFieldState.getVisualText());
        AnnotatedString annotatedString = new AnnotatedString(selectedText.toString(), null, 2, null);
        this.textFieldState.deleteSelectedText();
        return annotatedString;
    }

    public final boolean canShowCopyMenuItem() {
        return (!TextRange.m7567getCollapsedimpl(getTextFieldState().getVisualText().getSelection()) && !this.isPassword) && ClipboardUtils_androidKt.isWriteSupported(this.clipboard);
    }

    public final boolean isCopyAllowed() {
        return (TextRange.m7567getCollapsedimpl(getTextFieldState().getVisualText().getSelection()) || this.isPassword) ? false : true;
    }

    public static /* synthetic */ Object copy$default(TextFieldSelectionState textFieldSelectionState, boolean z, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        return textFieldSelectionState.copy(z, continuation);
    }

    public final Object copy(boolean cancelSelection, Continuation<? super Unit> continuation) {
        Object clipEntry;
        AnnotatedString valueToCopy = copyWithResult$foundation(cancelSelection);
        return (valueToCopy != null && (clipEntry = this.clipboard.setClipEntry(ClipboardUtils_androidKt.toClipEntry(valueToCopy), continuation)) == IntrinsicsKt.getCOROUTINE_SUSPENDED()) ? clipEntry : Unit.INSTANCE;
    }

    public static /* synthetic */ AnnotatedString copyWithResult$foundation$default(TextFieldSelectionState textFieldSelectionState, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        return textFieldSelectionState.copyWithResult$foundation(z);
    }

    public final AnnotatedString copyWithResult$foundation(boolean cancelSelection) {
        if (!((TextRange.m7567getCollapsedimpl(getTextFieldState().getVisualText().getSelection()) || this.isPassword) ? false : true)) {
            return null;
        }
        CharSequence selectedText = TextFieldCharSequenceKt.getSelectedText(this.textFieldState.getVisualText());
        AnnotatedString annotatedString = new AnnotatedString(selectedText.toString(), null, 2, null);
        if (cancelSelection) {
            this.textFieldState.collapseSelectionToMax();
        }
        return annotatedString;
    }

    public final Object updateClipboardEntry(Continuation<? super Unit> continuation) {
        Object objUpdate = this.clipboardPasteState.update(continuation);
        return objUpdate == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objUpdate : Unit.INSTANCE;
    }

    public final boolean canShowPasteMenuItem() {
        if (!getEditable$foundation() || !ClipboardUtils_androidKt.isReadSupported(this.clipboard)) {
            return false;
        }
        if (this.clipboardPasteState.get_hasText()) {
            return true;
        }
        Function0<? extends ReceiveContentConfiguration> function0 = this.receiveContentConfiguration;
        return (function0 != null ? function0.invoke() : null) != null && this.clipboardPasteState.get_hasClip();
    }

    public final boolean isPasteAllowed() {
        return getEditable$foundation();
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0074  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object paste(kotlin.coroutines.Continuation<? super kotlin.Unit> r13) {
        /*
            Method dump skipped, instruction units count: 210
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState.paste(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:19:0x004d  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x005d  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object pasteAsPlainText(kotlin.coroutines.Continuation<? super kotlin.Unit> r13) {
        /*
            r12 = this;
            boolean r0 = r13 instanceof androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState.C02321
            if (r0 == 0) goto L14
            r0 = r13
            androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$pasteAsPlainText$1 r0 = (androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState.C02321) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r1 = r0.label
            int r1 = r1 - r2
            r0.label = r1
            goto L19
        L14:
            androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$pasteAsPlainText$1 r0 = new androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState$pasteAsPlainText$1
            r0.<init>(r13)
        L19:
            java.lang.Object r1 = r0.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r0.label
            switch(r3) {
                case 0: goto L39;
                case 1: goto L33;
                case 2: goto L2d;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L2d:
            r2 = r12
            kotlin.ResultKt.throwOnFailure(r1)
            r4 = r1
            goto L58
        L33:
            r3 = r12
            kotlin.ResultKt.throwOnFailure(r1)
            r4 = r1
            goto L49
        L39:
            kotlin.ResultKt.throwOnFailure(r1)
            r3 = r12
            androidx.compose.ui.platform.Clipboard r4 = r3.clipboard
            r5 = 1
            r0.label = r5
            java.lang.Object r4 = r4.getClipEntry(r0)
            if (r4 != r2) goto L49
        L48:
            return r2
        L49:
            androidx.compose.ui.platform.ClipEntry r4 = (androidx.compose.ui.platform.ClipEntry) r4
            if (r4 == 0) goto L70
            r5 = 2
            r0.label = r5
            java.lang.Object r4 = androidx.compose.foundation.internal.ClipboardUtils_androidKt.readText(r4, r0)
            if (r4 != r2) goto L57
            goto L48
        L57:
            r2 = r3
        L58:
            java.lang.String r4 = (java.lang.String) r4
            if (r4 != 0) goto L5d
            goto L70
        L5d:
            androidx.compose.foundation.text.input.internal.TransformedTextFieldState r5 = r2.textFieldState
            r6 = r4
            java.lang.CharSequence r6 = (java.lang.CharSequence) r6
            androidx.compose.foundation.text.input.internal.undo.TextFieldEditUndoBehavior r8 = androidx.compose.foundation.text.input.internal.undo.TextFieldEditUndoBehavior.NeverMerge
            r10 = 10
            r11 = 0
            r7 = 0
            r9 = 0
            androidx.compose.foundation.text.input.internal.TransformedTextFieldState.replaceSelectedText$default(r5, r6, r7, r8, r9, r10, r11)
            kotlin.Unit r3 = kotlin.Unit.INSTANCE
            return r3
        L70:
            kotlin.Unit r2 = kotlin.Unit.INSTANCE
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState.pasteAsPlainText(kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final void onPasteEvent$foundation(AnnotatedString value) {
        if (getEditable$foundation()) {
            TransformedTextFieldState.replaceSelectedText$default(this.textFieldState, value.getText(), false, TextFieldEditUndoBehavior.NeverMerge, false, 10, null);
        }
    }

    public final boolean canShowSelectAllMenuItem() {
        return TextRange.m7569getLengthimpl(this.textFieldState.getVisualText().getSelection()) != this.textFieldState.getVisualText().length();
    }

    public final void selectAll() {
        this.textFieldState.selectAll();
    }

    public final boolean canShowAutofillMenuItem() {
        return getEditable$foundation() && TextRange.m7567getCollapsedimpl(this.textFieldState.getVisualText().getSelection());
    }

    public final void autofill() {
        Function0<Unit> function0 = this.requestAutofillAction;
        if (function0 != null) {
            function0.invoke();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object showTextToolbar(Rect contentRect, Continuation<? super Unit> continuation) {
        if (ComposeFoundationFlags.isNewContextMenuEnabled) {
            this.toolbarRequester.show();
        } else {
            TextToolbarHandler textToolbarHandler = this.textToolbarHandler;
            if (textToolbarHandler != null) {
                Object objShowTextToolbar = textToolbarHandler.showTextToolbar(this, contentRect, continuation);
                return objShowTextToolbar == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objShowTextToolbar : Unit.INSTANCE;
            }
        }
        return Unit.INSTANCE;
    }

    public final void deselect() {
        if (!TextRange.m7567getCollapsedimpl(this.textFieldState.getVisualText().getSelection())) {
            this.textFieldState.collapseSelectionToEnd();
        }
        setShowCursorHandle(false);
        updateTextToolbarState(TextToolbarState.None);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void hideTextToolbar() {
        if (ComposeFoundationFlags.isNewContextMenuEnabled) {
            this.toolbarRequester.hide();
            return;
        }
        TextToolbarHandler textToolbarHandler = this.textToolbarHandler;
        if (textToolbarHandler != null) {
            textToolbarHandler.hideTextToolbar();
        }
    }

    /* JADX INFO: renamed from: updateSelection-QkiN0lo$foundation$default, reason: not valid java name */
    public static /* synthetic */ long m1938updateSelectionQkiN0lo$foundation$default(TextFieldSelectionState textFieldSelectionState, TextFieldCharSequence textFieldCharSequence, int i, int i2, boolean z, SelectionAdjustment selectionAdjustment, boolean z2, boolean z3, HapticFeedbackType hapticFeedbackType, int i3, Object obj) {
        if ((i3 & 32) != 0) {
            z2 = false;
        }
        if ((i3 & 64) != 0) {
            z3 = false;
        }
        return textFieldSelectionState.m1942updateSelectionQkiN0lo$foundation(textFieldCharSequence, i, i2, z, selectionAdjustment, z2, z3, hapticFeedbackType);
    }

    /* JADX INFO: renamed from: updateSelection-QkiN0lo$foundation, reason: not valid java name */
    public final long m1942updateSelectionQkiN0lo$foundation(TextFieldCharSequence textFieldCharSequence, int startOffset, int endOffset, boolean isStartHandle, SelectionAdjustment adjustment, boolean allowPreviousSelectionCollapsed, boolean isStartOfSelection, HapticFeedbackType hapticFeedbackType) {
        HapticFeedback hapticFeedback;
        TextRange textRangeM7561boximpl = TextRange.m7561boximpl(textFieldCharSequence.getSelection());
        long it = textRangeM7561boximpl.getPackedValue();
        if (!(!isStartOfSelection && (allowPreviousSelectionCollapsed || !TextRange.m7567getCollapsedimpl(it)))) {
            textRangeM7561boximpl = null;
        }
        long newSelection = m1935getTextFieldSelectionqeG_v_k(startOffset, endOffset, textRangeM7561boximpl, isStartHandle, adjustment);
        if (hapticFeedbackType != null && ((TextRange.m7571getMinimpl(newSelection) != TextRange.m7571getMinimpl(textFieldCharSequence.getSelection()) || TextRange.m7570getMaximpl(newSelection) != TextRange.m7570getMaximpl(textFieldCharSequence.getSelection())) && (hapticFeedback = this.hapticFeedBack) != null)) {
            hapticFeedback.mo6082performHapticFeedbackCdsT49E(hapticFeedbackType.getValue());
        }
        return newSelection;
    }

    /* JADX INFO: renamed from: getTextFieldSelection-qeG_v_k, reason: not valid java name */
    private final long m1935getTextFieldSelectionqeG_v_k(int rawStartOffset, int rawEndOffset, TextRange previousSelection, boolean isStartHandle, SelectionAdjustment adjustment) {
        TextLayoutResult layoutResult = this.textLayoutState.getLayoutResult();
        if (layoutResult == null) {
            return TextRange.INSTANCE.m7578getZerod9O1mEE();
        }
        if (previousSelection == null && Intrinsics.areEqual(adjustment, SelectionAdjustment.INSTANCE.getCharacter())) {
            return TextRangeKt.TextRange(rawStartOffset, rawEndOffset);
        }
        SelectionLayout selectionLayout = SelectionLayoutKt.m2054getTextFieldSelectionLayoutRcvTLA(layoutResult, rawStartOffset, rawEndOffset, this.previousRawDragOffset, previousSelection != null ? previousSelection.getPackedValue() : TextRange.INSTANCE.m7578getZerod9O1mEE(), previousSelection == null, isStartHandle);
        if (previousSelection != null && !selectionLayout.shouldRecomputeSelection(this.previousSelectionLayout)) {
            return previousSelection.getPackedValue();
        }
        long result = adjustment.adjust(selectionLayout).m2045toTextRanged9O1mEE();
        this.previousSelectionLayout = selectionLayout;
        this.previousRawDragOffset = isStartHandle ? rawStartOffset : rawEndOffset;
        return result;
    }
}
