package androidx.compose.runtime;

import androidx.autofill.HintConstants;
import androidx.collection.MutableScatterMap;
import androidx.collection.MutableScatterSet;
import androidx.collection.ObjectIntMap;
import androidx.collection.ScatterMap;
import androidx.collection.ScatterSet;
import androidx.compose.animation.core.MutatorMutex$$ExternalSyntheticBackportWithForwarding0;
import androidx.compose.runtime.DerivedState;
import androidx.compose.runtime.collection.ScopeMap;
import androidx.compose.runtime.composer.linkbuffer.SlotTable;
import androidx.compose.runtime.composer.linkbuffer.SlotTableKt;
import androidx.compose.runtime.composer.linkbuffer.changelist.ChangeList;
import androidx.compose.runtime.internal.RememberEventDispatcher;
import androidx.compose.runtime.internal.Trace;
import androidx.compose.runtime.snapshots.ReaderKind;
import androidx.compose.runtime.snapshots.StateObject;
import androidx.compose.runtime.snapshots.StateObjectImpl;
import androidx.compose.runtime.tooling.CompositionErrorContext;
import androidx.compose.runtime.tooling.CompositionObserver;
import androidx.compose.runtime.tooling.CompositionObserverHandle;
import androidx.compose.runtime.tooling.ObservableComposition;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.navigation.compose.ComposeNavigator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Composition.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000¬\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000f\b\u0001\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u00052\u00020\u0006B\u001b\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\n\u0010\t\u001a\u0006\u0012\u0002\b\u00030\n¢\u0006\u0004\b\u000b\u0010\fJ\b\u0010!\u001a\u00020\u001dH\u0002J\b\u0010R\u001a\u00020OH\u0002J\b\u0010S\u001a\u000206H\u0002J \u0010e\u001a\u00020Z2\u0011\u0010f\u001a\r\u0012\u0004\u0012\u00020Z0Y¢\u0006\u0002\b[H\u0016¢\u0006\u0002\u0010_J \u0010g\u001a\u00020Z2\u0011\u0010f\u001a\r\u0012\u0004\u0012\u00020Z0Y¢\u0006\u0002\b[H\u0016¢\u0006\u0002\u0010_J \u0010h\u001a\u00020i2\u0011\u0010f\u001a\r\u0012\u0004\u0012\u00020Z0Y¢\u0006\u0002\b[H\u0016¢\u0006\u0002\u0010jJ \u0010k\u001a\u00020i2\u0011\u0010f\u001a\r\u0012\u0004\u0012\u00020Z0Y¢\u0006\u0002\b[H\u0016¢\u0006\u0002\u0010jJ\u001d\u0010l\u001a\u00020Z2\u000e\u0010m\u001a\n\u0012\u0004\u0012\u00020o\u0018\u00010nH\u0000¢\u0006\u0002\bpJ \u0010q\u001a\u00020Z2\u0011\u0010f\u001a\r\u0012\u0004\u0012\u00020Z0Y¢\u0006\u0002\b[H\u0002¢\u0006\u0002\u0010_J(\u0010r\u001a\u00020i2\u0006\u0010s\u001a\u00020;2\u0011\u0010f\u001a\r\u0012\u0004\u0012\u00020Z0Y¢\u0006\u0002\b[H\u0002¢\u0006\u0002\u0010tJ \u0010u\u001a\u00020Z2\u0011\u0010f\u001a\r\u0012\u0004\u0012\u00020Z0Y¢\u0006\u0002\b[H\u0002¢\u0006\u0002\u0010_J\b\u0010v\u001a\u00020ZH\u0002J\b\u0010w\u001a\u00020;H\u0002J\u0010\u0010x\u001a\u00020y2\u0006\u0010z\u001a\u00020{H\u0016J\u000e\u0010|\u001a\u00020Z2\u0006\u0010}\u001a\u00020GJ\b\u0010~\u001a\u00020ZH\u0002J\b\u0010\u007f\u001a\u00020ZH\u0002J\t\u0010\u0080\u0001\u001a\u00020ZH\u0002J!\u0010\u0081\u0001\u001a\u00020Z2\u0011\u0010f\u001a\r\u0012\u0004\u0012\u00020Z0Y¢\u0006\u0002\b[H\u0017¢\u0006\u0002\u0010_J\u000f\u0010\u0082\u0001\u001a\u00020ZH\u0000¢\u0006\u0003\b\u0083\u0001J\t\u0010\u0084\u0001\u001a\u00020ZH\u0016J\u0018\u0010\u0087\u0001\u001a\u00020Z2\r\u0010\u0088\u0001\u001a\b\u0012\u0004\u0012\u00020\u00110'H\u0016J\u0018\u0010\u0089\u0001\u001a\u00020;2\r\u0010\u0088\u0001\u001a\b\u0012\u0004\u0012\u00020\u00110'H\u0016J\u0018\u0010\u008a\u0001\u001a\u00020Z2\r\u0010\u008b\u0001\u001a\b\u0012\u0004\u0012\u00020Z0YH\u0016J,\u0010\u008c\u0001\u001a\u0015\u0012\u0011\u0012\u000f\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020\u00110\u008d\u0001022\b\u0010\u008e\u0001\u001a\u00030\u008f\u0001H\u0000¢\u0006\u0003\b\u0090\u0001J:\u0010\u0091\u0001\u001a\u0015\u0012\u0011\u0012\u000f\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020\u00110\u008d\u0001022\u0015\u0010\u0092\u0001\u001a\u0010\u0012\u0005\u0012\u00030\u008f\u0001\u0012\u0004\u0012\u00020;0\u0093\u0001H\u0080\b¢\u0006\u0003\b\u0094\u0001J\u001b\u0010\u0095\u0001\u001a\u00020Z2\u0007\u0010\u0096\u0001\u001a\u00020\u00112\u0007\u0010\u0097\u0001\u001a\u00020;H\u0002J!\u0010\u0095\u0001\u001a\u00020Z2\r\u0010\u0088\u0001\u001a\b\u0012\u0004\u0012\u00020\u00110'2\u0007\u0010\u0097\u0001\u001a\u00020;H\u0002J\t\u0010\u0098\u0001\u001a\u00020ZH\u0002J\u0012\u0010\u0099\u0001\u001a\u00020Z2\u0007\u0010\u0096\u0001\u001a\u00020\u0011H\u0016J\u0012\u0010\u009a\u0001\u001a\u00020Z2\u0007\u0010\u0096\u0001\u001a\u00020\u0011H\u0002J\u0012\u0010\u009b\u0001\u001a\u00020Z2\u0007\u0010\u0096\u0001\u001a\u00020\u0011H\u0016J\t\u0010\u009c\u0001\u001a\u00020;H\u0016J)\u0010\u009d\u0001\u001a\u00020Z2\u001e\u0010\u009e\u0001\u001a\u0019\u0012\u0015\u0012\u0013\u0012\u0005\u0012\u00030\u009f\u0001\u0012\u0007\u0012\u0005\u0018\u00010\u009f\u00010\u008d\u000102H\u0016J\u0012\u0010 \u0001\u001a\u00020Z2\u0007\u0010U\u001a\u00030¡\u0001H\u0016J\u0011\u0010¢\u0001\u001a\u00020Z2\u0006\u00105\u001a\u000206H\u0002J\t\u0010£\u0001\u001a\u00020ZH\u0016J\t\u0010¤\u0001\u001a\u00020ZH\u0016J\t\u0010¥\u0001\u001a\u00020ZH\u0016JL\u0010¦\u0001\u001a\u0003H§\u0001\"\u0005\b\u0000\u0010§\u000122\u0010\u008b\u0001\u001a-\u0012!\u0012\u001f\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020\u00110#¢\u0006\u000e\b¨\u0001\u0012\t\b©\u0001\u0012\u0004\b\b(5\u0012\u0005\u0012\u0003H§\u00010\u0093\u0001H\u0082\b¢\u0006\u0003\u0010ª\u0001J(\u0010«\u0001\u001a\u0003H§\u0001\"\u0005\b\u0000\u0010§\u00012\u000e\u0010\u008b\u0001\u001a\t\u0012\u0005\u0012\u0003H§\u00010YH\u0082\b¢\u0006\u0003\u0010¬\u0001J\t\u0010\u00ad\u0001\u001a\u00020ZH\u0016J\t\u0010®\u0001\u001a\u00020ZH\u0016J\t\u0010¯\u0001\u001a\u00020ZH\u0016J;\u0010°\u0001\u001a\u0003H±\u0001\"\u0005\b\u0000\u0010±\u00012\t\u0010²\u0001\u001a\u0004\u0018\u00010\u00012\u0007\u0010³\u0001\u001a\u00020G2\u000e\u0010\u008b\u0001\u001a\t\u0012\u0005\u0012\u0003H±\u00010YH\u0016¢\u0006\u0003\u0010´\u0001J\u0015\u0010µ\u0001\u001a\u0004\u0018\u00010B2\b\u0010A\u001a\u0004\u0018\u00010BH\u0016J\u001e\u0010¶\u0001\u001a\u00030·\u00012\u0007\u0010¸\u0001\u001a\u00020$2\t\u0010¹\u0001\u001a\u0004\u0018\u00010\u0011H\u0016J\u0012\u0010º\u0001\u001a\u00020Z2\u0007\u0010¸\u0001\u001a\u00020$H\u0016J)\u0010»\u0001\u001a\u0005\u0018\u0001H§\u0001\"\u0005\b\u0000\u0010§\u00012\u000e\u0010}\u001a\n\u0012\u0005\u0012\u0003H§\u00010¼\u0001H\u0016¢\u0006\u0003\u0010½\u0001J\u001d\u0010¾\u0001\u001a\u00020;2\u0007\u0010¸\u0001\u001a\u00020$2\t\u0010¹\u0001\u001a\u0004\u0018\u00010\u0011H\u0002J(\u0010¿\u0001\u001a\u00030·\u00012\u0007\u0010¸\u0001\u001a\u00020$2\b\u0010\u008e\u0001\u001a\u00030\u008f\u00012\t\u0010¹\u0001\u001a\u0004\u0018\u00010\u0011H\u0002J!\u0010À\u0001\u001a\u00020Z2\u0007\u0010¹\u0001\u001a\u00020\u00112\u0007\u0010¸\u0001\u001a\u00020$H\u0000¢\u0006\u0003\bÁ\u0001J\u001b\u0010Â\u0001\u001a\u00020Z2\n\u0010U\u001a\u0006\u0012\u0002\b\u00030.H\u0000¢\u0006\u0003\bÃ\u0001J\u001e\u0010Ä\u0001\u001a\u000e\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020\u00110#H\u0002¢\u0006\u0006\bÅ\u0001\u0010Æ\u0001J(\u0010Ç\u0001\u001a\u0003H§\u0001\"\u0005\b\u0000\u0010§\u00012\u000e\u0010\u008b\u0001\u001a\t\u0012\u0005\u0012\u0003H§\u00010YH\u0082\b¢\u0006\u0003\u0010¬\u0001J\n\u0010z\u001a\u0004\u0018\u00010{H\u0002J\t\u0010È\u0001\u001a\u00020ZH\u0016J\u000f\u0010É\u0001\u001a\u00020GH\u0000¢\u0006\u0003\bÊ\u0001R\u0013\u0010\u0007\u001a\u00020\b8\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0012\u0010\t\u001a\u0006\u0012\u0002\b\u00030\nX\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010\u000f\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u00110\u0010j\n\u0012\u0006\u0012\u0004\u0018\u00010\u0011`\u0012X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0013R\u0014\u0010\u0014\u001a\u00060\u0011j\u0002`\u0015X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0016R\u001a\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018X\u0082\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u001a\u0010\u001bR\u001a\u0010\u001c\u001a\u00020\u001dX\u0080\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001e\u0010\u001b\u001a\u0004\b\u001f\u0010 R\u001c\u0010\"\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020$0#X\u0082\u0004¢\u0006\u0004\n\u0002\u0010%R\u001a\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00110'8AX\u0080\u0004¢\u0006\u0006\u001a\u0004\b(\u0010)R\u0014\u0010*\u001a\b\u0012\u0004\u0012\u00020$0+X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010,\u001a\b\u0012\u0004\u0012\u00020$0+X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010-\u001a\u0012\u0012\u0004\u0012\u00020\u0011\u0012\b\u0012\u0006\u0012\u0002\b\u00030.0#X\u0082\u0004¢\u0006\u0004\n\u0002\u0010%R\u001a\u0010/\u001a\b\u0012\u0004\u0012\u00020\u00110'8AX\u0080\u0004¢\u0006\u0006\u001a\u0004\b0\u0010)R\u001a\u00101\u001a\b\u0012\u0004\u0012\u00020$028AX\u0080\u0004¢\u0006\u0006\u001a\u0004\b3\u00104R\u000e\u00105\u001a\u000206X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00107\u001a\u000206X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u00108\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020$0#X\u0082\u0004¢\u0006\u0004\n\u0002\u0010%R\u001c\u00109\u001a\u000e\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020\u00110#X\u0082\u000e¢\u0006\u0004\n\u0002\u0010%R \u0010:\u001a\u00020;X\u0080\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b<\u0010\u001b\u001a\u0004\b=\u0010>\"\u0004\b?\u0010@R\u0010\u0010A\u001a\u0004\u0018\u00010BX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010C\u001a\u0004\u0018\u00010DX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010E\u001a\u0004\u0018\u00010\u0000X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010F\u001a\u00020GX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010H\u001a\u00020IX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\bJ\u0010KR\u000e\u0010L\u001a\u00020MX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010N\u001a\u00020OX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\bP\u0010QR\u0011\u0010T\u001a\u00020;¢\u0006\b\n\u0000\u001a\u0004\bT\u0010>R\u000e\u0010U\u001a\u00020GX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010V\u001a\u00020;8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bW\u0010>R'\u0010X\u001a\r\u0012\u0004\u0012\u00020Z0Y¢\u0006\u0002\b[X\u0086\u000e¢\u0006\u0010\n\u0002\u0010`\u001a\u0004\b\\\u0010]\"\u0004\b^\u0010_R\u0014\u0010a\u001a\u00020;8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\ba\u0010>R\u0014\u0010b\u001a\u00020;8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bb\u0010>R\u0014\u0010c\u001a\u00020;8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bd\u0010>R\u0016\u0010\u0085\u0001\u001a\u00020;8VX\u0096\u0004¢\u0006\u0007\u001a\u0005\b\u0086\u0001\u0010>¨\u0006Ë\u0001"}, d2 = {"Landroidx/compose/runtime/CompositionImpl;", "Landroidx/compose/runtime/ControlledComposition;", "Landroidx/compose/runtime/ReusableComposition;", "Landroidx/compose/runtime/RecomposeScopeOwner;", "Landroidx/compose/runtime/CompositionServices;", "Landroidx/compose/runtime/PausableComposition;", "Landroidx/compose/runtime/tooling/ObservableComposition;", "parent", "Landroidx/compose/runtime/CompositionContext;", "applier", "Landroidx/compose/runtime/Applier;", "<init>", "(Landroidx/compose/runtime/CompositionContext;Landroidx/compose/runtime/Applier;)V", "getParent", "()Landroidx/compose/runtime/CompositionContext;", "pendingModifications", "Ljava/util/concurrent/atomic/AtomicReference;", "", "Landroidx/compose/runtime/internal/AtomicReference;", "Ljava/util/concurrent/atomic/AtomicReference;", "lock", "Landroidx/compose/runtime/platform/SynchronizedObject;", "Ljava/lang/Object;", "abandonSet", "", "Landroidx/compose/runtime/RememberObserver;", "getAbandonSet$annotations", "()V", "slotStorage", "Landroidx/compose/runtime/SlotStorage;", "getSlotStorage$runtime$annotations", "getSlotStorage$runtime", "()Landroidx/compose/runtime/SlotStorage;", "createSlotStorage", "observations", "Landroidx/compose/runtime/collection/ScopeMap;", "Landroidx/compose/runtime/RecomposeScopeImpl;", "Landroidx/collection/MutableScatterMap;", "observedObjects", "", "getObservedObjects$runtime", "()Ljava/util/Set;", "invalidatedScopes", "Landroidx/collection/MutableScatterSet;", "conditionallyInvalidatedScopes", "derivedStates", "Landroidx/compose/runtime/DerivedState;", "derivedStateDependencies", "getDerivedStateDependencies$runtime", "conditionalScopes", "", "getConditionalScopes$runtime", "()Ljava/util/List;", "changes", "Landroidx/compose/runtime/Changes;", "lateChanges", "observationsProcessed", "invalidations", "pendingInvalidScopes", "", "getPendingInvalidScopes$runtime$annotations", "getPendingInvalidScopes$runtime", "()Z", "setPendingInvalidScopes$runtime", "(Z)V", "shouldPause", "Landroidx/compose/runtime/ShouldPauseCallback;", "pendingPausedComposition", "Landroidx/compose/runtime/PausedCompositionImpl;", "invalidationDelegate", "invalidationDelegateGroup", "", "observerHolder", "Landroidx/compose/runtime/CompositionObserverHolder;", "getObserverHolder$runtime", "()Landroidx/compose/runtime/CompositionObserverHolder;", "rememberManager", "Landroidx/compose/runtime/internal/RememberEventDispatcher;", "composer", "Landroidx/compose/runtime/InternalComposer;", "getComposer$runtime", "()Landroidx/compose/runtime/InternalComposer;", "createComposer", "createChangeList", "isRoot", "state", "areChildrenComposing", "getAreChildrenComposing", ComposeNavigator.NAME, "Lkotlin/Function0;", "", "Landroidx/compose/runtime/Composable;", "getComposable", "()Lkotlin/jvm/functions/Function2;", "setComposable", "(Lkotlin/jvm/functions/Function2;)V", "Lkotlin/jvm/functions/Function2;", "isComposing", "isDisposed", "hasPendingChanges", "getHasPendingChanges", "setContent", "content", "setContentWithReuse", "setPausableContent", "Landroidx/compose/runtime/PausedComposition;", "(Lkotlin/jvm/functions/Function2;)Landroidx/compose/runtime/PausedComposition;", "setPausableContentWithReuse", "pausedCompositionFinished", "ignoreSet", "Landroidx/collection/ScatterSet;", "Landroidx/compose/runtime/RememberObserverHolder;", "pausedCompositionFinished$runtime", "composeInitial", "composeInitialPaused", "reusable", "(ZLkotlin/jvm/functions/Function2;)Landroidx/compose/runtime/PausedComposition;", "composeInitialWithReuse", "ensureRunning", "clearDeactivated", "setObserver", "Landroidx/compose/runtime/tooling/CompositionObserverHandle;", "observer", "Landroidx/compose/runtime/tooling/CompositionObserver;", "invalidateGroupsWithKey", "key", "drainPendingModificationsForCompositionLocked", "drainPendingModificationsLocked", "drainPendingModificationsOutOfBandLocked", "composeContent", "updateMovingInvalidations", "updateMovingInvalidations$runtime", "dispose", "hasInvalidations", "getHasInvalidations", "recordModificationsOf", "values", "observesAnyOf", "prepareCompose", "block", "extractInvalidationsOf", "Lkotlin/Pair;", "anchor", "Landroidx/compose/runtime/Anchor;", "extractInvalidationsOf$runtime", "extractInvalidationsOfGroup", "inGroup", "Lkotlin/Function1;", "extractInvalidationsOfGroup$runtime", "addPendingInvalidationsLocked", "value", "forgetConditionalScopes", "cleanUpDerivedStateObservations", "recordReadOf", "invalidateScopeOfLocked", "recordWriteOf", "recompose", "insertMovableContent", "references", "Landroidx/compose/runtime/MovableContentStateReference;", "disposeUnusedMovableContent", "Landroidx/compose/runtime/MovableContentState;", "applyChangesInLocked", "applyChanges", "applyLateChanges", "changesApplied", "guardInvalidationsLocked", "T", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "(Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "guardChanges", "(Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "abandonChanges", "invalidateAll", "verifyConsistent", "delegateInvalidations", "R", TypedValues.TransitionType.S_TO, "groupIndex", "(Landroidx/compose/runtime/ControlledComposition;ILkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "getAndSetShouldPauseCallback", "invalidate", "Landroidx/compose/runtime/InvalidationResult;", "scope", "instance", "recomposeScopeReleased", "getCompositionService", "Landroidx/compose/runtime/CompositionServiceKey;", "(Landroidx/compose/runtime/CompositionServiceKey;)Ljava/lang/Object;", "tryImminentInvalidation", "invalidateChecked", "removeObservation", "removeObservation$runtime", "removeDerivedStateObservation", "removeDerivedStateObservation$runtime", "takeInvalidations", "takeInvalidations-afanTW4", "()Landroidx/collection/MutableScatterMap;", "trackAbandonedValues", "deactivate", "composerStacksSizes", "composerStacksSizes$runtime", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class CompositionImpl implements ControlledComposition, ReusableComposition, RecomposeScopeOwner, CompositionServices, PausableComposition, ObservableComposition {
    public static final int $stable = 8;
    private final Set<RememberObserver> abandonSet;
    private final Applier<?> applier;
    private final Changes changes;
    private Function2<? super Composer, ? super Integer, Unit> composable;
    private final InternalComposer composer;
    private final MutableScatterSet<RecomposeScopeImpl> conditionallyInvalidatedScopes;
    private final MutableScatterMap<Object, Object> derivedStates;
    private final MutableScatterSet<RecomposeScopeImpl> invalidatedScopes;
    private CompositionImpl invalidationDelegate;
    private int invalidationDelegateGroup;
    private MutableScatterMap<Object, Object> invalidations;
    private final boolean isRoot;
    private final Changes lateChanges;
    private final MutableScatterMap<Object, Object> observations;
    private final MutableScatterMap<Object, Object> observationsProcessed;
    private final CompositionObserverHolder observerHolder;
    private final CompositionContext parent;
    private boolean pendingInvalidScopes;
    private PausedCompositionImpl pendingPausedComposition;
    private final RememberEventDispatcher rememberManager;
    private ShouldPauseCallback shouldPause;
    private final SlotStorage slotStorage;
    private int state;
    private final AtomicReference<Object> pendingModifications = new AtomicReference<>(null);
    private final Object lock = new Object();

    private static /* synthetic */ void getAbandonSet$annotations() {
    }

    public static /* synthetic */ void getPendingInvalidScopes$runtime$annotations() {
    }

    public static /* synthetic */ void getSlotStorage$runtime$annotations() {
    }

    public CompositionImpl(CompositionContext parent, Applier<?> applier) {
        this.parent = parent;
        this.applier = applier;
        DefaultConstructorMarker defaultConstructorMarker = null;
        int i = 0;
        int i2 = 1;
        this.abandonSet = new MutableScatterSet(i, i2, defaultConstructorMarker).asMutableSet();
        SlotStorage it = createSlotStorage();
        if (this.parent.getCollectingCallByInformation$runtime()) {
            it.collectCalledByInformation();
        }
        if (this.parent.getCollectingSourceInformation()) {
            it.collectSourceInformation();
        }
        this.slotStorage = it;
        this.observations = ScopeMap.m4473constructorimpl$default(null, 1, null);
        this.invalidatedScopes = new MutableScatterSet<>(i, i2, defaultConstructorMarker);
        this.conditionallyInvalidatedScopes = new MutableScatterSet<>(i, i2, defaultConstructorMarker);
        this.derivedStates = ScopeMap.m4473constructorimpl$default(null, 1, null);
        this.changes = createChangeList();
        this.lateChanges = createChangeList();
        this.observationsProcessed = ScopeMap.m4473constructorimpl$default(null, 1, null);
        this.invalidations = ScopeMap.m4473constructorimpl$default(null, 1, null);
        this.observerHolder = new CompositionObserverHolder(null, false, this.parent, 3, null);
        this.rememberManager = new RememberEventDispatcher();
        InternalComposer it2 = createComposer();
        this.parent.registerComposer$runtime(it2);
        this.composer = it2;
        this.isRoot = this.parent instanceof Recomposer;
        this.composable = ComposableSingletons$CompositionKt.INSTANCE.getLambda$954879418$runtime();
    }

    public final CompositionContext getParent() {
        return this.parent;
    }

    /* JADX INFO: renamed from: getSlotStorage$runtime, reason: from getter */
    public final SlotStorage getSlotStorage() {
        return this.slotStorage;
    }

    private final SlotStorage createSlotStorage() {
        if (ComposeRuntimeFlags.isLinkBufferComposerEnabled) {
            return new SlotTable(0, null, false, false, 15, null);
        }
        return new androidx.compose.runtime.composer.gapbuffer.SlotTable();
    }

    public final Set<Object> getObservedObjects$runtime() {
        return this.observations.asMap().keySet();
    }

    public final Set<Object> getDerivedStateDependencies$runtime() {
        return this.derivedStates.asMap().keySet();
    }

    public final List<RecomposeScopeImpl> getConditionalScopes$runtime() {
        return CollectionsKt.toList(this.conditionallyInvalidatedScopes.asSet());
    }

    /* JADX INFO: renamed from: getPendingInvalidScopes$runtime, reason: from getter */
    public final boolean getPendingInvalidScopes() {
        return this.pendingInvalidScopes;
    }

    public final void setPendingInvalidScopes$runtime(boolean z) {
        this.pendingInvalidScopes = z;
    }

    /* JADX INFO: renamed from: getObserverHolder$runtime, reason: from getter */
    public final CompositionObserverHolder getObserverHolder() {
        return this.observerHolder;
    }

    /* JADX INFO: renamed from: getComposer$runtime, reason: from getter */
    public final InternalComposer getComposer() {
        return this.composer;
    }

    private final InternalComposer createComposer() {
        boolean z = ComposeRuntimeFlags.isLinkBufferComposerEnabled;
        Applier<?> applier = this.applier;
        if (z) {
            return new LinkComposer(applier, this.parent, this.abandonSet, SlotTableKt.asLinkBufferSlotTable(this.slotStorage), this.changes, this.lateChanges, this.observerHolder, this);
        }
        return new GapComposer(applier, this.parent, androidx.compose.runtime.composer.gapbuffer.SlotTableKt.asGapBufferSlotTable(this.slotStorage), this.abandonSet, this.changes, this.lateChanges, this.observerHolder, this);
    }

    private final Changes createChangeList() {
        if (ComposeRuntimeFlags.isLinkBufferComposerEnabled) {
            return new ChangeList();
        }
        return new androidx.compose.runtime.composer.gapbuffer.changelist.ChangeList();
    }

    /* JADX INFO: renamed from: isRoot, reason: from getter */
    public final boolean getIsRoot() {
        return this.isRoot;
    }

    private final boolean getAreChildrenComposing() {
        return this.composer.getAreChildrenComposing$runtime();
    }

    public final Function2<Composer, Integer, Unit> getComposable() {
        return this.composable;
    }

    public final void setComposable(Function2<? super Composer, ? super Integer, Unit> function2) {
        this.composable = function2;
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public boolean isComposing() {
        return this.composer.getIsComposing();
    }

    @Override // androidx.compose.runtime.Composition
    public boolean isDisposed() {
        return this.state == 3;
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public boolean getHasPendingChanges() {
        boolean hasPendingChanges$runtime;
        Object lock$iv = this.lock;
        synchronized (lock$iv) {
            hasPendingChanges$runtime = this.composer.getHasPendingChanges$runtime();
        }
        return hasPendingChanges$runtime;
    }

    @Override // androidx.compose.runtime.Composition
    public void setContent(Function2<? super Composer, ? super Integer, Unit> content) {
        boolean wasDeactivated = clearDeactivated();
        ensureRunning();
        if (wasDeactivated) {
            composeInitialWithReuse(content);
        } else {
            composeInitial(content);
        }
    }

    @Override // androidx.compose.runtime.ReusableComposition
    public void setContentWithReuse(Function2<? super Composer, ? super Integer, Unit> content) {
        clearDeactivated();
        ensureRunning();
        composeInitialWithReuse(content);
    }

    @Override // androidx.compose.runtime.PausableComposition
    public PausedComposition setPausableContent(Function2<? super Composer, ? super Integer, Unit> content) {
        boolean wasDeactivated = clearDeactivated();
        return composeInitialPaused(wasDeactivated, content);
    }

    @Override // androidx.compose.runtime.PausableComposition
    public PausedComposition setPausableContentWithReuse(Function2<? super Composer, ? super Integer, Unit> content) {
        clearDeactivated();
        ensureRunning();
        return composeInitialPaused(true, content);
    }

    public final void pausedCompositionFinished$runtime(ScatterSet<RememberObserverHolder> ignoreSet) {
        this.pendingPausedComposition = null;
        if (ignoreSet != null) {
            this.rememberManager.ignoreForgotten(ignoreSet);
            this.state = 2;
        }
    }

    private final void composeInitial(Function2<? super Composer, ? super Integer, Unit> content) {
        this.composable = content;
        this.parent.composeInitial$runtime(this, this.composable);
    }

    private final PausedComposition composeInitialPaused(boolean reusable, Function2<? super Composer, ? super Integer, Unit> content) {
        boolean value$iv = this.pendingPausedComposition == null;
        if (!value$iv) {
            PreconditionsKt.throwIllegalStateException("A pausable composition is in progress");
        }
        PausedCompositionImpl pausedComposition = new PausedCompositionImpl(this, this.parent, this.composer, this.abandonSet, content, reusable, this.applier, this.lock);
        this.pendingPausedComposition = pausedComposition;
        return pausedComposition;
    }

    private final void composeInitialWithReuse(Function2<? super Composer, ? super Integer, Unit> content) {
        this.composer.startReuseFromRoot$runtime();
        composeInitial(content);
        this.composer.endReuseFromRoot$runtime();
    }

    private final void ensureRunning() {
        String str;
        boolean value$iv = this.state == 0;
        if (!value$iv) {
            switch (this.state) {
                case 1:
                    str = "The composition should be activated before setting content.";
                    break;
                case 2:
                    str = "A previous pausable composition for this composition was cancelled. This composition must be disposed.";
                    break;
                case 3:
                    str = "The composition is disposed";
                    break;
                default:
                    str = "";
                    break;
            }
            PreconditionsKt.throwIllegalStateException(str);
        }
        boolean value$iv2 = this.pendingPausedComposition == null;
        if (value$iv2) {
            return;
        }
        PreconditionsKt.throwIllegalStateException("A pausable composition is in progress");
    }

    private final boolean clearDeactivated() {
        boolean isDeactivated;
        Object lock$iv = this.lock;
        synchronized (lock$iv) {
            isDeactivated = true;
            if (this.state != 1) {
                isDeactivated = false;
            }
            if (isDeactivated) {
                this.state = 0;
            }
        }
        return isDeactivated;
    }

    @Override // androidx.compose.runtime.tooling.ObservableComposition
    public CompositionObserverHandle setObserver(final CompositionObserver observer) {
        Object lock$iv = this.lock;
        synchronized (lock$iv) {
            this.observerHolder.setObserver(observer);
            this.observerHolder.setRoot(true);
            Unit unit = Unit.INSTANCE;
        }
        return new CompositionObserverHandle() { // from class: androidx.compose.runtime.CompositionImpl.setObserver.2
            @Override // androidx.compose.runtime.tooling.CompositionObserverHandle
            public void dispose() {
                Object lock$iv2 = CompositionImpl.this.lock;
                CompositionImpl compositionImpl = CompositionImpl.this;
                CompositionObserver compositionObserver = observer;
                synchronized (lock$iv2) {
                    if (Intrinsics.areEqual(compositionImpl.getObserverHolder().getObserver(), compositionObserver)) {
                        compositionImpl.getObserverHolder().setObserver(null);
                        compositionImpl.getObserverHolder().setRoot(false);
                    }
                    Unit unit2 = Unit.INSTANCE;
                }
            }
        };
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0046  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void invalidateGroupsWithKey(int r17) throws java.lang.Throwable {
        /*
            r16 = this;
            r1 = r16
            java.lang.Object r2 = r1.lock
            r3 = 0
            monitor-enter(r2)
            r0 = 0
            androidx.compose.runtime.SlotStorage r4 = r1.slotStorage     // Catch: java.lang.Throwable -> L5d
            r5 = r17
            java.util.List r4 = r4.invalidateGroupsWithKey(r5)     // Catch: java.lang.Throwable -> L5b
            monitor-exit(r2)
            if (r4 == 0) goto L46
            r2 = r4
            r3 = 0
            r6 = r2
            r7 = 0
            r8 = 0
            r9 = r6
            java.util.Collection r9 = (java.util.Collection) r9
            int r9 = r9.size()
        L1f:
            if (r8 >= r9) goto L3f
            java.lang.Object r11 = r6.get(r8)
            r12 = r11
            r13 = 0
            r14 = r12
            androidx.compose.runtime.RecomposeScopeImpl r14 = (androidx.compose.runtime.RecomposeScopeImpl) r14
            r15 = 0
            r0 = 0
            androidx.compose.runtime.InvalidationResult r0 = r14.invalidateForResult(r0)
            androidx.compose.runtime.InvalidationResult r10 = androidx.compose.runtime.InvalidationResult.IGNORED
            if (r0 != r10) goto L36
            r0 = 1
            goto L37
        L36:
            r0 = 0
        L37:
            if (r0 == 0) goto L3b
            r0 = 1
            goto L41
        L3b:
            int r8 = r8 + 1
            goto L1f
        L3f:
            r0 = 0
        L41:
            if (r0 == 0) goto L44
            goto L46
        L44:
            r0 = 0
            goto L47
        L46:
            r0 = 1
        L47:
            if (r0 == 0) goto L5a
            androidx.compose.runtime.InternalComposer r2 = r1.composer
            boolean r2 = r2.forceRecomposeScopes$runtime()
            if (r2 == 0) goto L5a
            androidx.compose.runtime.CompositionContext r2 = r1.parent
            r3 = r1
            androidx.compose.runtime.ControlledComposition r3 = (androidx.compose.runtime.ControlledComposition) r3
            r2.invalidate$runtime(r3)
        L5a:
            return
        L5b:
            r0 = move-exception
            goto L60
        L5d:
            r0 = move-exception
            r5 = r17
        L60:
            monitor-exit(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.CompositionImpl.invalidateGroupsWithKey(int):void");
    }

    private final void drainPendingModificationsForCompositionLocked() {
        Object toRecord = this.pendingModifications.getAndSet(CompositionKt.PendingApplyNoModifications);
        if (toRecord != null) {
            if (Intrinsics.areEqual(toRecord, CompositionKt.PendingApplyNoModifications)) {
                ComposerKt.composeRuntimeError("pending composition has not been applied");
                throw new KotlinNothingValueException();
            }
            if (toRecord instanceof Set) {
                addPendingInvalidationsLocked((Set<? extends Object>) toRecord, true);
                return;
            }
            if (toRecord instanceof Object[]) {
                for (Set<? extends Object> set : (Set[]) toRecord) {
                    addPendingInvalidationsLocked(set, true);
                }
                return;
            }
            ComposerKt.composeRuntimeError("corrupt pendingModifications drain: " + this.pendingModifications);
            throw new KotlinNothingValueException();
        }
    }

    private final void drainPendingModificationsLocked() {
        Object toRecord = this.pendingModifications.getAndSet(null);
        if (!Intrinsics.areEqual(toRecord, CompositionKt.PendingApplyNoModifications)) {
            if (toRecord instanceof Set) {
                addPendingInvalidationsLocked((Set<? extends Object>) toRecord, false);
                return;
            }
            if (toRecord instanceof Object[]) {
                for (Set<? extends Object> set : (Set[]) toRecord) {
                    addPendingInvalidationsLocked(set, false);
                }
                return;
            }
            if (toRecord == null) {
                if (this.pendingPausedComposition == null) {
                    ComposerKt.composeImmediateRuntimeError("calling recordModificationsOf and applyChanges concurrently is not supported");
                    return;
                }
                return;
            }
            ComposerKt.composeRuntimeError("corrupt pendingModifications drain: " + this.pendingModifications);
            throw new KotlinNothingValueException();
        }
    }

    private final void drainPendingModificationsOutOfBandLocked() {
        Object toRecord = this.pendingModifications.getAndSet(SetsKt.emptySet());
        if (!Intrinsics.areEqual(toRecord, CompositionKt.PendingApplyNoModifications) && toRecord != null) {
            if (toRecord instanceof Set) {
                addPendingInvalidationsLocked((Set<? extends Object>) toRecord, false);
                return;
            }
            if (toRecord instanceof Object[]) {
                for (Set<? extends Object> set : (Set[]) toRecord) {
                    addPendingInvalidationsLocked(set, false);
                }
                return;
            }
            ComposerKt.composeRuntimeError("corrupt pendingModifications drain: " + this.pendingModifications);
            throw new KotlinNothingValueException();
        }
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public void composeContent(Function2<? super Composer, ? super Integer, Unit> content) {
        MutableScatterMap<Object, Object> mutableScatterMapM4389takeInvalidationsafanTW4;
        try {
            Object lock$iv = this.lock;
            synchronized (lock$iv) {
                try {
                    try {
                        drainPendingModificationsForCompositionLocked();
                        mutableScatterMapM4389takeInvalidationsafanTW4 = m4389takeInvalidationsafanTW4();
                    } catch (Throwable th) {
                        e$iv = th;
                    }
                    try {
                        try {
                            try {
                                try {
                                    this.composer.mo4394composeContentZbOJvo$runtime(mutableScatterMapM4389takeInvalidationsafanTW4, content, this.shouldPause);
                                    Unit unit = Unit.INSTANCE;
                                    Unit unit2 = Unit.INSTANCE;
                                    Unit unit3 = Unit.INSTANCE;
                                } catch (Throwable th2) {
                                    e$iv = th2;
                                    this.invalidations = mutableScatterMapM4389takeInvalidationsafanTW4;
                                    throw e$iv;
                                }
                            } catch (Throwable th3) {
                                e$iv = th3;
                            }
                        } catch (Throwable th4) {
                            e$iv = th4;
                        }
                    } catch (Throwable th5) {
                        e$iv = th5;
                        throw e$iv;
                    }
                } catch (Throwable th6) {
                    th = th6;
                    if (0 == 0) {
                        try {
                            if (!this.abandonSet.isEmpty()) {
                                RememberEventDispatcher this_$iv$iv$iv = this.rememberManager;
                                Set<RememberObserver> set = this.abandonSet;
                                CompositionErrorContext traceContext$iv$iv$iv = this.composer.getErrorContext$runtime();
                                try {
                                    this_$iv$iv$iv.prepare(set, traceContext$iv$iv$iv);
                                    this_$iv$iv$iv.dispatchAbandons();
                                    this_$iv$iv$iv.clear();
                                } catch (Throwable th7) {
                                    this_$iv$iv$iv.clear();
                                    throw th7;
                                }
                            }
                        } catch (Throwable e$iv) {
                            abandonChanges();
                            throw e$iv;
                        }
                    }
                    throw th;
                }
            }
        } catch (Throwable th8) {
            th = th8;
        }
    }

    public final void updateMovingInvalidations$runtime() {
        Object lock$iv = this.lock;
        synchronized (lock$iv) {
            drainPendingModificationsOutOfBandLocked();
            MutableScatterMap<Object, Object> mutableScatterMapM4389takeInvalidationsafanTW4 = m4389takeInvalidationsafanTW4();
            try {
                this.composer.mo4396updateComposerInvalidationsRY85e9Y$runtime(mutableScatterMapM4389takeInvalidationsafanTW4);
                Unit unit = Unit.INSTANCE;
                Unit unit2 = Unit.INSTANCE;
            } catch (Throwable e$iv) {
                this.invalidations = mutableScatterMapM4389takeInvalidationsafanTW4;
                throw e$iv;
            }
        }
    }

    @Override // androidx.compose.runtime.Composition
    public void dispose() {
        Object lock$iv = this.lock;
        synchronized (lock$iv) {
            boolean value$iv = !this.composer.getIsComposing();
            if (!value$iv) {
                PreconditionsKt.throwIllegalStateException("Composition is disposed while composing. If dispose is triggered by a call in @Composable function, consider wrapping it with SideEffect block.");
            }
            if (this.state != 3) {
                this.state = 3;
                this.composable = ComposableSingletons$CompositionKt.INSTANCE.getLambda$1918065384$runtime();
                Changes deferredChanges = this.composer.getDeferredChanges();
                if (deferredChanges != null) {
                    applyChangesInLocked(deferredChanges);
                }
                boolean nonEmptySlotTable = !this.slotStorage.isEmpty();
                if (nonEmptySlotTable || !this.abandonSet.isEmpty()) {
                    RememberEventDispatcher this_$iv = this.rememberManager;
                    Set<RememberObserver> set = this.abandonSet;
                    CompositionErrorContext traceContext$iv = this.composer.getErrorContext$runtime();
                    try {
                        this_$iv.prepare(set, traceContext$iv);
                        if (nonEmptySlotTable) {
                            this.applier.onBeginChanges();
                            this.slotStorage.clear(this.rememberManager);
                            this.applier.clear();
                            this.applier.onEndChanges();
                            this_$iv.dispatchRememberObservers();
                        }
                        this_$iv.dispatchAbandons();
                    } finally {
                        this_$iv.clear();
                    }
                }
                this.composer.dispose$runtime();
            }
            Unit unit = Unit.INSTANCE;
        }
        this.parent.unregisterComposition$runtime(this);
    }

    @Override // androidx.compose.runtime.Composition
    public boolean getHasInvalidations() {
        boolean z;
        Object lock$iv = this.lock;
        synchronized (lock$iv) {
            z = ScopeMap.m4481getSizeimpl(this.invalidations) > 0;
        }
        return z;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.compose.runtime.ControlledComposition
    public void recordModificationsOf(Set<? extends Object> values) {
        Object obj;
        Object objPlus;
        do {
            obj = this.pendingModifications.get();
            if (obj == null || Intrinsics.areEqual(obj, CompositionKt.PendingApplyNoModifications)) {
                objPlus = values;
            } else if (obj instanceof Set) {
                objPlus = new Set[]{obj, values};
            } else {
                if (!(obj instanceof Object[])) {
                    throw new IllegalStateException(("corrupt pendingModifications: " + this.pendingModifications).toString());
                }
                Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Array<kotlin.collections.Set<kotlin.Any>>");
                objPlus = ArraysKt.plus((Set<? extends Object>[]) obj, values);
            }
        } while (!MutatorMutex$$ExternalSyntheticBackportWithForwarding0.m(this.pendingModifications, obj, objPlus));
        if (obj == null) {
            synchronized (this.lock) {
                drainPendingModificationsLocked();
                Unit unit = Unit.INSTANCE;
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x007f, code lost:
    
        return true;
     */
    @Override // androidx.compose.runtime.ControlledComposition
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean observesAnyOf(java.util.Set<? extends java.lang.Object> r25) {
        /*
            Method dump skipped, instruction units count: 221
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.CompositionImpl.observesAnyOf(java.util.Set):boolean");
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public void prepareCompose(Function0<Unit> block) {
        this.composer.prepareCompose$runtime(block);
    }

    public final List<Pair<RecomposeScopeImpl, Object>> extractInvalidationsOf$runtime(Anchor anchor) {
        int $i$f$forEachIndexed;
        long[] m$iv$iv$iv;
        int $i$f$removeIf;
        int j$iv$iv$iv;
        int $i$f$forEachIndexed2;
        long[] m$iv$iv$iv2;
        long $this$maskEmptyOrDeleted$iv$iv$iv$iv;
        int $i$f$removeIf2;
        boolean zIsEmpty;
        int j$iv$iv$iv2;
        int $i$f$removeIf3;
        int $i$f$removeIf4;
        if (ScopeMap.m4481getSizeimpl(this.invalidations) > 0) {
            List result = new ArrayList();
            SlotStorage slotStorage = this.slotStorage;
            MutableScatterMap<Object, Object> mutableScatterMap = this.invalidations;
            int i = 0;
            int $i$f$removeIf5 = 0;
            MutableScatterMap<Object, Object> this_$iv$iv$iv = mutableScatterMap;
            int $i$f$forEachIndexed3 = 0;
            long[] m$iv$iv$iv3 = this_$iv$iv$iv.metadata;
            int lastIndex$iv$iv$iv = m$iv$iv$iv3.length - 2;
            int i$iv$iv$iv = 0;
            if (0 > lastIndex$iv$iv$iv) {
                return result;
            }
            while (true) {
                long slot$iv$iv$iv = m$iv$iv$iv3[i$iv$iv$iv];
                MutableScatterMap<Object, Object> mutableScatterMap2 = mutableScatterMap;
                int i2 = i;
                int $i$f$removeIf6 = $i$f$removeIf5;
                ScatterMap this_$iv$iv$iv2 = this_$iv$iv$iv;
                long $this$maskEmptyOrDeleted$iv$iv$iv$iv2 = ((~slot$iv$iv$iv) << 7) & slot$iv$iv$iv & (-9187201950435737472L);
                if ($this$maskEmptyOrDeleted$iv$iv$iv$iv2 == -9187201950435737472L) {
                    $i$f$forEachIndexed = $i$f$forEachIndexed3;
                    m$iv$iv$iv = m$iv$iv$iv3;
                    $i$f$removeIf = $i$f$removeIf6;
                } else {
                    int i3 = 8;
                    int bitCount$iv$iv$iv = 8 - ((~(i$iv$iv$iv - lastIndex$iv$iv$iv)) >>> 31);
                    int j$iv$iv$iv3 = 0;
                    while (j$iv$iv$iv3 < bitCount$iv$iv$iv) {
                        long value$iv$iv$iv$iv = slot$iv$iv$iv & 255;
                        boolean z = false;
                        int $i$f$isFull = value$iv$iv$iv$iv < 128 ? 1 : 0;
                        if ($i$f$isFull == 0) {
                            j$iv$iv$iv = j$iv$iv$iv3;
                            $i$f$forEachIndexed2 = $i$f$forEachIndexed3;
                            m$iv$iv$iv2 = m$iv$iv$iv3;
                            $this$maskEmptyOrDeleted$iv$iv$iv$iv = slot$iv$iv$iv;
                            $i$f$removeIf2 = $i$f$removeIf6;
                        } else {
                            int index$iv$iv$iv = (i$iv$iv$iv << 3) + j$iv$iv$iv3;
                            int i4 = i3;
                            Object key$iv = mutableScatterMap.keys[index$iv$iv$iv];
                            Object scopes$iv = mutableScatterMap.values[index$iv$iv$iv];
                            j$iv$iv$iv = j$iv$iv$iv3;
                            Intrinsics.checkNotNull(key$iv, "null cannot be cast to non-null type Key of androidx.compose.runtime.collection.ScopeMap");
                            if (scopes$iv instanceof MutableScatterSet) {
                                Intrinsics.checkNotNull(scopes$iv, "null cannot be cast to non-null type androidx.collection.MutableScatterSet<Scope of androidx.compose.runtime.collection.ScopeMap>");
                                MutableScatterSet this_$iv$iv = (MutableScatterSet) scopes$iv;
                                Object[] elements$iv$iv = this_$iv$iv.elements;
                                long[] m$iv$iv$iv4 = this_$iv$iv.metadata;
                                int lastIndex$iv$iv$iv2 = m$iv$iv$iv4.length - 2;
                                int i$iv$iv$iv2 = 0;
                                if (0 <= lastIndex$iv$iv$iv2) {
                                    while (true) {
                                        long slot$iv$iv$iv2 = m$iv$iv$iv4[i$iv$iv$iv2];
                                        $i$f$forEachIndexed2 = $i$f$forEachIndexed3;
                                        m$iv$iv$iv2 = m$iv$iv$iv3;
                                        $this$maskEmptyOrDeleted$iv$iv$iv$iv = slot$iv$iv$iv;
                                        long $this$maskEmptyOrDeleted$iv$iv$iv$iv3 = ((~slot$iv$iv$iv2) << 7) & slot$iv$iv$iv2 & (-9187201950435737472L);
                                        if ($this$maskEmptyOrDeleted$iv$iv$iv$iv3 == -9187201950435737472L) {
                                            $i$f$removeIf2 = $i$f$removeIf6;
                                        } else {
                                            int bitCount$iv$iv$iv2 = 8 - ((~(i$iv$iv$iv2 - lastIndex$iv$iv$iv2)) >>> 31);
                                            int j$iv$iv$iv4 = 0;
                                            while (j$iv$iv$iv4 < bitCount$iv$iv$iv2) {
                                                long value$iv$iv$iv$iv2 = slot$iv$iv$iv2 & 255;
                                                if (!(value$iv$iv$iv$iv2 < 128)) {
                                                    j$iv$iv$iv2 = j$iv$iv$iv4;
                                                    $i$f$removeIf3 = $i$f$removeIf6;
                                                } else {
                                                    int index$iv$iv$iv2 = (i$iv$iv$iv2 << 3) + j$iv$iv$iv4;
                                                    Object it$iv = elements$iv$iv[index$iv$iv$iv2];
                                                    RecomposeScopeImpl scope = (RecomposeScopeImpl) key$iv;
                                                    j$iv$iv$iv2 = j$iv$iv$iv4;
                                                    Anchor scopeAnchor = scope.getAnchor();
                                                    if (scopeAnchor != null && slotStorage.inGroup(anchor, scopeAnchor)) {
                                                        $i$f$removeIf3 = $i$f$removeIf6;
                                                        result.add(TuplesKt.to(scope, it$iv));
                                                        $i$f$removeIf4 = 1;
                                                    } else {
                                                        $i$f$removeIf3 = $i$f$removeIf6;
                                                        $i$f$removeIf4 = 0;
                                                    }
                                                    if ($i$f$removeIf4 != 0) {
                                                        this_$iv$iv.removeElementAt(index$iv$iv$iv2);
                                                    }
                                                }
                                                slot$iv$iv$iv2 >>= i4;
                                                j$iv$iv$iv4 = j$iv$iv$iv2 + 1;
                                                $i$f$removeIf6 = $i$f$removeIf3;
                                            }
                                            $i$f$removeIf2 = $i$f$removeIf6;
                                            int j$iv$iv$iv5 = i4;
                                            if (bitCount$iv$iv$iv2 != j$iv$iv$iv5) {
                                                break;
                                            }
                                        }
                                        if (i$iv$iv$iv2 == lastIndex$iv$iv$iv2) {
                                            break;
                                        }
                                        i$iv$iv$iv2++;
                                        slot$iv$iv$iv = $this$maskEmptyOrDeleted$iv$iv$iv$iv;
                                        $i$f$forEachIndexed3 = $i$f$forEachIndexed2;
                                        m$iv$iv$iv3 = m$iv$iv$iv2;
                                        $i$f$removeIf6 = $i$f$removeIf2;
                                        i4 = 8;
                                    }
                                } else {
                                    $i$f$forEachIndexed2 = $i$f$forEachIndexed3;
                                    m$iv$iv$iv2 = m$iv$iv$iv3;
                                    $this$maskEmptyOrDeleted$iv$iv$iv$iv = slot$iv$iv$iv;
                                    $i$f$removeIf2 = $i$f$removeIf6;
                                }
                                zIsEmpty = ((MutableScatterSet) scopes$iv).isEmpty();
                            } else {
                                $i$f$forEachIndexed2 = $i$f$forEachIndexed3;
                                m$iv$iv$iv2 = m$iv$iv$iv3;
                                $this$maskEmptyOrDeleted$iv$iv$iv$iv = slot$iv$iv$iv;
                                $i$f$removeIf2 = $i$f$removeIf6;
                                Intrinsics.checkNotNull(scopes$iv, "null cannot be cast to non-null type Scope of androidx.compose.runtime.collection.ScopeMap");
                                RecomposeScopeImpl scope2 = (RecomposeScopeImpl) key$iv;
                                Anchor scopeAnchor2 = scope2.getAnchor();
                                if (scopeAnchor2 != null && slotStorage.inGroup(anchor, scopeAnchor2)) {
                                    result.add(TuplesKt.to(scope2, scopes$iv));
                                    z = true;
                                }
                                zIsEmpty = z;
                            }
                            if (zIsEmpty) {
                                mutableScatterMap.removeValueAt(index$iv$iv$iv);
                            }
                        }
                        slot$iv$iv$iv = $this$maskEmptyOrDeleted$iv$iv$iv$iv >> 8;
                        j$iv$iv$iv3 = j$iv$iv$iv + 1;
                        i3 = 8;
                        $i$f$forEachIndexed3 = $i$f$forEachIndexed2;
                        m$iv$iv$iv3 = m$iv$iv$iv2;
                        $i$f$removeIf6 = $i$f$removeIf2;
                    }
                    $i$f$forEachIndexed = $i$f$forEachIndexed3;
                    m$iv$iv$iv = m$iv$iv$iv3;
                    $i$f$removeIf = $i$f$removeIf6;
                    if (bitCount$iv$iv$iv != i3) {
                        return result;
                    }
                }
                if (i$iv$iv$iv == lastIndex$iv$iv$iv) {
                    return result;
                }
                i$iv$iv$iv++;
                this_$iv$iv$iv = this_$iv$iv$iv2;
                mutableScatterMap = mutableScatterMap2;
                i = i2;
                $i$f$forEachIndexed3 = $i$f$forEachIndexed;
                m$iv$iv$iv3 = m$iv$iv$iv;
                $i$f$removeIf5 = $i$f$removeIf;
            }
        } else {
            return CollectionsKt.emptyList();
        }
    }

    public final List<Pair<RecomposeScopeImpl, Object>> extractInvalidationsOfGroup$runtime(Function1<? super Anchor, Boolean> inGroup) {
        int $i$f$extractInvalidationsOfGroup$runtime;
        int $i$f$removeIf;
        ScatterMap this_$iv$iv$iv;
        int $i$f$forEachIndexed;
        long[] m$iv$iv$iv;
        int $i$f$extractInvalidationsOfGroup$runtime2;
        int $i$f$removeIf2;
        ScatterMap this_$iv$iv$iv2;
        int $i$f$forEachIndexed2;
        long[] m$iv$iv$iv2;
        long $this$maskEmptyOrDeleted$iv$iv$iv$iv;
        int j$iv$iv$iv;
        boolean zIsEmpty;
        int j$iv$iv$iv2;
        int j$iv$iv$iv3;
        int j$iv$iv$iv4;
        int $i$f$extractInvalidationsOfGroup$runtime3 = 0;
        if (ScopeMap.m4481getSizeimpl(this.invalidations) <= 0) {
            return CollectionsKt.emptyList();
        }
        List result = new ArrayList();
        MutableScatterMap arg0$iv = this.invalidations;
        int i = 0;
        int $i$f$removeIf3 = 0;
        MutableScatterMap this_$iv$iv$iv3 = arg0$iv;
        int $i$f$forEachIndexed3 = 0;
        long[] m$iv$iv$iv3 = this_$iv$iv$iv3.metadata;
        int lastIndex$iv$iv$iv = m$iv$iv$iv3.length - 2;
        int i$iv$iv$iv = 0;
        if (0 > lastIndex$iv$iv$iv) {
            return result;
        }
        while (true) {
            long slot$iv$iv$iv = m$iv$iv$iv3[i$iv$iv$iv];
            MutableScatterMap arg0$iv2 = arg0$iv;
            int i2 = i;
            if ((((~slot$iv$iv$iv) << 7) & slot$iv$iv$iv & (-9187201950435737472L)) == -9187201950435737472L) {
                $i$f$extractInvalidationsOfGroup$runtime = $i$f$extractInvalidationsOfGroup$runtime3;
                $i$f$removeIf = $i$f$removeIf3;
                this_$iv$iv$iv = this_$iv$iv$iv3;
                $i$f$forEachIndexed = $i$f$forEachIndexed3;
                m$iv$iv$iv = m$iv$iv$iv3;
            } else {
                int i3 = 8;
                int bitCount$iv$iv$iv = 8 - ((~(i$iv$iv$iv - lastIndex$iv$iv$iv)) >>> 31);
                int j$iv$iv$iv5 = 0;
                while (j$iv$iv$iv5 < bitCount$iv$iv$iv) {
                    long value$iv$iv$iv$iv = slot$iv$iv$iv & 255;
                    boolean z = false;
                    if (!(value$iv$iv$iv$iv < 128)) {
                        $i$f$extractInvalidationsOfGroup$runtime2 = $i$f$extractInvalidationsOfGroup$runtime3;
                        $i$f$removeIf2 = $i$f$removeIf3;
                        this_$iv$iv$iv2 = this_$iv$iv$iv3;
                        $i$f$forEachIndexed2 = $i$f$forEachIndexed3;
                        m$iv$iv$iv2 = m$iv$iv$iv3;
                        $this$maskEmptyOrDeleted$iv$iv$iv$iv = slot$iv$iv$iv;
                        j$iv$iv$iv = j$iv$iv$iv5;
                    } else {
                        int index$iv$iv$iv = (i$iv$iv$iv << 3) + j$iv$iv$iv5;
                        Object key$iv = arg0$iv.keys[index$iv$iv$iv];
                        int i4 = i3;
                        Object scopes$iv = arg0$iv.values[index$iv$iv$iv];
                        $i$f$extractInvalidationsOfGroup$runtime2 = $i$f$extractInvalidationsOfGroup$runtime3;
                        Intrinsics.checkNotNull(key$iv, "null cannot be cast to non-null type Key of androidx.compose.runtime.collection.ScopeMap");
                        if (scopes$iv instanceof MutableScatterSet) {
                            Intrinsics.checkNotNull(scopes$iv, "null cannot be cast to non-null type androidx.collection.MutableScatterSet<Scope of androidx.compose.runtime.collection.ScopeMap>");
                            MutableScatterSet this_$iv$iv = (MutableScatterSet) scopes$iv;
                            $i$f$removeIf2 = $i$f$removeIf3;
                            Object[] elements$iv$iv = this_$iv$iv.elements;
                            this_$iv$iv$iv2 = this_$iv$iv$iv3;
                            long[] m$iv$iv$iv4 = this_$iv$iv.metadata;
                            int lastIndex$iv$iv$iv2 = m$iv$iv$iv4.length - 2;
                            int i$iv$iv$iv2 = 0;
                            if (0 <= lastIndex$iv$iv$iv2) {
                                while (true) {
                                    long slot$iv$iv$iv2 = m$iv$iv$iv4[i$iv$iv$iv2];
                                    $i$f$forEachIndexed2 = $i$f$forEachIndexed3;
                                    m$iv$iv$iv2 = m$iv$iv$iv3;
                                    $this$maskEmptyOrDeleted$iv$iv$iv$iv = slot$iv$iv$iv;
                                    long $this$maskEmptyOrDeleted$iv$iv$iv$iv2 = ((~slot$iv$iv$iv2) << 7) & slot$iv$iv$iv2 & (-9187201950435737472L);
                                    if ($this$maskEmptyOrDeleted$iv$iv$iv$iv2 == -9187201950435737472L) {
                                        j$iv$iv$iv = j$iv$iv$iv5;
                                    } else {
                                        int bitCount$iv$iv$iv2 = 8 - ((~(i$iv$iv$iv2 - lastIndex$iv$iv$iv2)) >>> 31);
                                        int j$iv$iv$iv6 = 0;
                                        while (j$iv$iv$iv6 < bitCount$iv$iv$iv2) {
                                            long value$iv$iv$iv$iv2 = slot$iv$iv$iv2 & 255;
                                            if (!(value$iv$iv$iv$iv2 < 128)) {
                                                j$iv$iv$iv2 = j$iv$iv$iv6;
                                                j$iv$iv$iv3 = j$iv$iv$iv5;
                                            } else {
                                                int index$iv$iv$iv2 = (i$iv$iv$iv2 << 3) + j$iv$iv$iv6;
                                                Object it$iv = elements$iv$iv[index$iv$iv$iv2];
                                                RecomposeScopeImpl scope = (RecomposeScopeImpl) key$iv;
                                                j$iv$iv$iv2 = j$iv$iv$iv6;
                                                Anchor scopeAnchor = scope.getAnchor();
                                                if (scopeAnchor != null && inGroup.invoke(scopeAnchor).booleanValue()) {
                                                    j$iv$iv$iv3 = j$iv$iv$iv5;
                                                    result.add(TuplesKt.to(scope, it$iv));
                                                    j$iv$iv$iv4 = 1;
                                                } else {
                                                    j$iv$iv$iv3 = j$iv$iv$iv5;
                                                    j$iv$iv$iv4 = 0;
                                                }
                                                if (j$iv$iv$iv4 != 0) {
                                                    this_$iv$iv.removeElementAt(index$iv$iv$iv2);
                                                }
                                            }
                                            slot$iv$iv$iv2 >>= i4;
                                            j$iv$iv$iv6 = j$iv$iv$iv2 + 1;
                                            j$iv$iv$iv5 = j$iv$iv$iv3;
                                        }
                                        j$iv$iv$iv = j$iv$iv$iv5;
                                        int j$iv$iv$iv7 = i4;
                                        if (bitCount$iv$iv$iv2 != j$iv$iv$iv7) {
                                            break;
                                        }
                                    }
                                    if (i$iv$iv$iv2 == lastIndex$iv$iv$iv2) {
                                        break;
                                    }
                                    i$iv$iv$iv2++;
                                    slot$iv$iv$iv = $this$maskEmptyOrDeleted$iv$iv$iv$iv;
                                    $i$f$forEachIndexed3 = $i$f$forEachIndexed2;
                                    m$iv$iv$iv3 = m$iv$iv$iv2;
                                    j$iv$iv$iv5 = j$iv$iv$iv;
                                    i4 = 8;
                                }
                            } else {
                                $i$f$forEachIndexed2 = $i$f$forEachIndexed3;
                                m$iv$iv$iv2 = m$iv$iv$iv3;
                                $this$maskEmptyOrDeleted$iv$iv$iv$iv = slot$iv$iv$iv;
                                j$iv$iv$iv = j$iv$iv$iv5;
                            }
                            zIsEmpty = ((MutableScatterSet) scopes$iv).isEmpty();
                        } else {
                            $i$f$removeIf2 = $i$f$removeIf3;
                            this_$iv$iv$iv2 = this_$iv$iv$iv3;
                            $i$f$forEachIndexed2 = $i$f$forEachIndexed3;
                            m$iv$iv$iv2 = m$iv$iv$iv3;
                            $this$maskEmptyOrDeleted$iv$iv$iv$iv = slot$iv$iv$iv;
                            j$iv$iv$iv = j$iv$iv$iv5;
                            Intrinsics.checkNotNull(scopes$iv, "null cannot be cast to non-null type Scope of androidx.compose.runtime.collection.ScopeMap");
                            RecomposeScopeImpl scope2 = (RecomposeScopeImpl) key$iv;
                            Anchor scopeAnchor2 = scope2.getAnchor();
                            if (scopeAnchor2 != null && inGroup.invoke(scopeAnchor2).booleanValue()) {
                                result.add(TuplesKt.to(scope2, scopes$iv));
                                z = true;
                            }
                            zIsEmpty = z;
                        }
                        if (zIsEmpty) {
                            arg0$iv.removeValueAt(index$iv$iv$iv);
                        }
                    }
                    slot$iv$iv$iv = $this$maskEmptyOrDeleted$iv$iv$iv$iv >> 8;
                    j$iv$iv$iv5 = j$iv$iv$iv + 1;
                    i3 = 8;
                    $i$f$extractInvalidationsOfGroup$runtime3 = $i$f$extractInvalidationsOfGroup$runtime2;
                    $i$f$removeIf3 = $i$f$removeIf2;
                    this_$iv$iv$iv3 = this_$iv$iv$iv2;
                    $i$f$forEachIndexed3 = $i$f$forEachIndexed2;
                    m$iv$iv$iv3 = m$iv$iv$iv2;
                }
                $i$f$extractInvalidationsOfGroup$runtime = $i$f$extractInvalidationsOfGroup$runtime3;
                $i$f$removeIf = $i$f$removeIf3;
                this_$iv$iv$iv = this_$iv$iv$iv3;
                $i$f$forEachIndexed = $i$f$forEachIndexed3;
                m$iv$iv$iv = m$iv$iv$iv3;
                if (bitCount$iv$iv$iv != i3) {
                    return result;
                }
            }
            if (i$iv$iv$iv == lastIndex$iv$iv$iv) {
                return result;
            }
            i$iv$iv$iv++;
            arg0$iv = arg0$iv2;
            i = i2;
            $i$f$extractInvalidationsOfGroup$runtime3 = $i$f$extractInvalidationsOfGroup$runtime;
            $i$f$removeIf3 = $i$f$removeIf;
            this_$iv$iv$iv3 = this_$iv$iv$iv;
            $i$f$forEachIndexed3 = $i$f$forEachIndexed;
            m$iv$iv$iv3 = m$iv$iv$iv;
        }
    }

    private final void addPendingInvalidationsLocked(Object value, boolean forgetConditionalScopes) {
        int i;
        int j$iv$iv$iv;
        MutableScatterMap<Object, Object> mutableScatterMap = this.observations;
        Object key$iv = value;
        int i2 = 0;
        Object value$iv = mutableScatterMap.get(key$iv);
        if (value$iv == null) {
            return;
        }
        if (!(value$iv instanceof MutableScatterSet)) {
            RecomposeScopeImpl scope = (RecomposeScopeImpl) value$iv;
            if (!ScopeMap.m4486removeimpl(this.observationsProcessed, value, scope) && scope.invalidateForResult(value) != InvalidationResult.IGNORED) {
                if (scope.isConditional() && !forgetConditionalScopes) {
                    this.conditionallyInvalidatedScopes.add(scope);
                    return;
                } else {
                    this.invalidatedScopes.add(scope);
                    return;
                }
            }
            return;
        }
        ScatterSet this_$iv$iv = (MutableScatterSet) value$iv;
        Object[] elements$iv$iv = this_$iv$iv.elements;
        long[] m$iv$iv$iv = this_$iv$iv.metadata;
        int lastIndex$iv$iv$iv = m$iv$iv$iv.length - 2;
        int i$iv$iv$iv = 0;
        if (0 > lastIndex$iv$iv$iv) {
            return;
        }
        while (true) {
            long slot$iv$iv$iv = m$iv$iv$iv[i$iv$iv$iv];
            MutableScatterMap<Object, Object> mutableScatterMap2 = mutableScatterMap;
            Object key$iv2 = key$iv;
            int i3 = i2;
            Object value$iv2 = value$iv;
            long $this$maskEmptyOrDeleted$iv$iv$iv$iv = ((~slot$iv$iv$iv) << 7) & slot$iv$iv$iv & (-9187201950435737472L);
            if ($this$maskEmptyOrDeleted$iv$iv$iv$iv != -9187201950435737472L) {
                int i4 = 8;
                int bitCount$iv$iv$iv = 8 - ((~(i$iv$iv$iv - lastIndex$iv$iv$iv)) >>> 31);
                int j$iv$iv$iv2 = 0;
                while (j$iv$iv$iv2 < bitCount$iv$iv$iv) {
                    long value$iv$iv$iv$iv = slot$iv$iv$iv & 255;
                    if (!(value$iv$iv$iv$iv < 128)) {
                        i = i4;
                        j$iv$iv$iv = j$iv$iv$iv2;
                    } else {
                        int index$iv$iv$iv = (i$iv$iv$iv << 3) + j$iv$iv$iv2;
                        i = i4;
                        RecomposeScopeImpl scope2 = (RecomposeScopeImpl) elements$iv$iv[index$iv$iv$iv];
                        j$iv$iv$iv = j$iv$iv$iv2;
                        if (!ScopeMap.m4486removeimpl(this.observationsProcessed, value, scope2) && scope2.invalidateForResult(value) != InvalidationResult.IGNORED) {
                            if (scope2.isConditional() && !forgetConditionalScopes) {
                                this.conditionallyInvalidatedScopes.add(scope2);
                            } else {
                                this.invalidatedScopes.add(scope2);
                            }
                        }
                    }
                    slot$iv$iv$iv >>= i;
                    j$iv$iv$iv2 = j$iv$iv$iv + 1;
                    i4 = i;
                }
                if (bitCount$iv$iv$iv != i4) {
                    return;
                }
            }
            if (i$iv$iv$iv == lastIndex$iv$iv$iv) {
                return;
            }
            i$iv$iv$iv++;
            i2 = i3;
            value$iv = value$iv2;
            mutableScatterMap = mutableScatterMap2;
            key$iv = key$iv2;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x0109  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x025e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void addPendingInvalidationsLocked(java.util.Set<? extends java.lang.Object> r53, boolean r54) {
        /*
            Method dump skipped, instruction units count: 1504
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.CompositionImpl.addPendingInvalidationsLocked(java.util.Set, boolean):void");
    }

    private final void cleanUpDerivedStateObservations() {
        char c;
        long j;
        MutableScatterMap<Object, Object> mutableScatterMap;
        int i;
        int $i$f$removeIf;
        ScatterMap this_$iv$iv$iv;
        int $i$f$forEachIndexed;
        long[] m$iv$iv$iv;
        MutableScatterMap<Object, Object> mutableScatterMap2;
        int i2;
        int $i$f$removeIf2;
        ScatterMap this_$iv$iv$iv2;
        int $i$f$forEachIndexed2;
        long[] m$iv$iv$iv2;
        int j$iv$iv$iv;
        boolean zIsEmpty;
        int j$iv$iv$iv2;
        long[] m$iv$iv$iv3;
        MutableScatterMap<Object, Object> mutableScatterMap3 = this.derivedStates;
        int i3 = 0;
        int $i$f$removeIf3 = 0;
        MutableScatterMap<Object, Object> this_$iv$iv$iv3 = mutableScatterMap3;
        int $i$f$forEachIndexed3 = 0;
        long[] m$iv$iv$iv4 = this_$iv$iv$iv3.metadata;
        int lastIndex$iv$iv$iv = m$iv$iv$iv4.length - 2;
        int i$iv$iv$iv = 0;
        int i4 = 8;
        if (0 <= lastIndex$iv$iv$iv) {
            while (true) {
                long slot$iv$iv$iv = m$iv$iv$iv4[i$iv$iv$iv];
                c = 7;
                j = 255;
                long $this$maskEmptyOrDeleted$iv$iv$iv$iv = ((~slot$iv$iv$iv) << 7) & slot$iv$iv$iv & (-9187201950435737472L);
                if ($this$maskEmptyOrDeleted$iv$iv$iv$iv == -9187201950435737472L) {
                    mutableScatterMap = mutableScatterMap3;
                    i = i3;
                    $i$f$removeIf = $i$f$removeIf3;
                    this_$iv$iv$iv = this_$iv$iv$iv3;
                    $i$f$forEachIndexed = $i$f$forEachIndexed3;
                    m$iv$iv$iv = m$iv$iv$iv4;
                } else {
                    int bitCount$iv$iv$iv = 8 - ((~(i$iv$iv$iv - lastIndex$iv$iv$iv)) >>> 31);
                    int j$iv$iv$iv3 = 0;
                    while (j$iv$iv$iv3 < bitCount$iv$iv$iv) {
                        long value$iv$iv$iv$iv = slot$iv$iv$iv & 255;
                        if (!(value$iv$iv$iv$iv < 128)) {
                            mutableScatterMap2 = mutableScatterMap3;
                            i2 = i3;
                            $i$f$removeIf2 = $i$f$removeIf3;
                            this_$iv$iv$iv2 = this_$iv$iv$iv3;
                            $i$f$forEachIndexed2 = $i$f$forEachIndexed3;
                            m$iv$iv$iv2 = m$iv$iv$iv4;
                            j$iv$iv$iv = j$iv$iv$iv3;
                        } else {
                            int index$iv$iv$iv = (i$iv$iv$iv << 3) + j$iv$iv$iv3;
                            Object obj = mutableScatterMap3.keys[index$iv$iv$iv];
                            Object value$iv = mutableScatterMap3.values[index$iv$iv$iv];
                            int i5 = i4;
                            if (value$iv instanceof MutableScatterSet) {
                                Intrinsics.checkNotNull(value$iv, "null cannot be cast to non-null type androidx.collection.MutableScatterSet<Scope of androidx.compose.runtime.collection.ScopeMap>");
                                MutableScatterSet set$iv = (MutableScatterSet) value$iv;
                                mutableScatterMap2 = mutableScatterMap3;
                                i2 = i3;
                                Object[] elements$iv$iv = set$iv.elements;
                                $i$f$removeIf2 = $i$f$removeIf3;
                                long[] m$iv$iv$iv5 = set$iv.metadata;
                                int lastIndex$iv$iv$iv2 = m$iv$iv$iv5.length - 2;
                                int i$iv$iv$iv2 = 0;
                                if (0 <= lastIndex$iv$iv$iv2) {
                                    while (true) {
                                        long slot$iv$iv$iv2 = m$iv$iv$iv5[i$iv$iv$iv2];
                                        this_$iv$iv$iv2 = this_$iv$iv$iv3;
                                        $i$f$forEachIndexed2 = $i$f$forEachIndexed3;
                                        j$iv$iv$iv = j$iv$iv$iv3;
                                        int index$iv$iv$iv2 = index$iv$iv$iv;
                                        long $this$maskEmptyOrDeleted$iv$iv$iv$iv2 = ((~slot$iv$iv$iv2) << 7) & slot$iv$iv$iv2 & (-9187201950435737472L);
                                        if ($this$maskEmptyOrDeleted$iv$iv$iv$iv2 == -9187201950435737472L) {
                                            m$iv$iv$iv2 = m$iv$iv$iv4;
                                        } else {
                                            int bitCount$iv$iv$iv2 = 8 - ((~(i$iv$iv$iv2 - lastIndex$iv$iv$iv2)) >>> 31);
                                            int j$iv$iv$iv4 = 0;
                                            while (j$iv$iv$iv4 < bitCount$iv$iv$iv2) {
                                                long value$iv$iv$iv$iv2 = slot$iv$iv$iv2 & 255;
                                                if (!(value$iv$iv$iv$iv2 < 128)) {
                                                    j$iv$iv$iv2 = j$iv$iv$iv4;
                                                    m$iv$iv$iv3 = m$iv$iv$iv4;
                                                } else {
                                                    int index$iv$iv$iv3 = (i$iv$iv$iv2 << 3) + j$iv$iv$iv4;
                                                    j$iv$iv$iv2 = j$iv$iv$iv4;
                                                    DerivedState derivedState = (DerivedState) elements$iv$iv[index$iv$iv$iv3];
                                                    m$iv$iv$iv3 = m$iv$iv$iv4;
                                                    if (!ScopeMap.m4474containsimpl(this.observations, derivedState)) {
                                                        set$iv.removeElementAt(index$iv$iv$iv3);
                                                    }
                                                }
                                                slot$iv$iv$iv2 >>= i5;
                                                j$iv$iv$iv4 = j$iv$iv$iv2 + 1;
                                                m$iv$iv$iv4 = m$iv$iv$iv3;
                                            }
                                            m$iv$iv$iv2 = m$iv$iv$iv4;
                                            int j$iv$iv$iv5 = i5;
                                            if (bitCount$iv$iv$iv2 != j$iv$iv$iv5) {
                                                break;
                                            }
                                        }
                                        if (i$iv$iv$iv2 == lastIndex$iv$iv$iv2) {
                                            break;
                                        }
                                        i$iv$iv$iv2++;
                                        j$iv$iv$iv3 = j$iv$iv$iv;
                                        index$iv$iv$iv = index$iv$iv$iv2;
                                        this_$iv$iv$iv3 = this_$iv$iv$iv2;
                                        $i$f$forEachIndexed3 = $i$f$forEachIndexed2;
                                        m$iv$iv$iv4 = m$iv$iv$iv2;
                                        i5 = 8;
                                    }
                                } else {
                                    this_$iv$iv$iv2 = this_$iv$iv$iv3;
                                    $i$f$forEachIndexed2 = $i$f$forEachIndexed3;
                                    m$iv$iv$iv2 = m$iv$iv$iv4;
                                    j$iv$iv$iv = j$iv$iv$iv3;
                                }
                                zIsEmpty = set$iv.isEmpty();
                            } else {
                                mutableScatterMap2 = mutableScatterMap3;
                                i2 = i3;
                                $i$f$removeIf2 = $i$f$removeIf3;
                                this_$iv$iv$iv2 = this_$iv$iv$iv3;
                                $i$f$forEachIndexed2 = $i$f$forEachIndexed3;
                                m$iv$iv$iv2 = m$iv$iv$iv4;
                                j$iv$iv$iv = j$iv$iv$iv3;
                                Intrinsics.checkNotNull(value$iv, "null cannot be cast to non-null type Scope of androidx.compose.runtime.collection.ScopeMap");
                                DerivedState derivedState2 = (DerivedState) value$iv;
                                zIsEmpty = !ScopeMap.m4474containsimpl(this.observations, derivedState2);
                            }
                            if (zIsEmpty) {
                                mutableScatterMap3.removeValueAt(index$iv$iv$iv);
                            }
                        }
                        slot$iv$iv$iv >>= 8;
                        j$iv$iv$iv3 = j$iv$iv$iv + 1;
                        i4 = 8;
                        i3 = i2;
                        mutableScatterMap3 = mutableScatterMap2;
                        $i$f$removeIf3 = $i$f$removeIf2;
                        this_$iv$iv$iv3 = this_$iv$iv$iv2;
                        $i$f$forEachIndexed3 = $i$f$forEachIndexed2;
                        m$iv$iv$iv4 = m$iv$iv$iv2;
                    }
                    mutableScatterMap = mutableScatterMap3;
                    i = i3;
                    $i$f$removeIf = $i$f$removeIf3;
                    this_$iv$iv$iv = this_$iv$iv$iv3;
                    $i$f$forEachIndexed = $i$f$forEachIndexed3;
                    m$iv$iv$iv = m$iv$iv$iv4;
                    int $i$f$forEachIndexed4 = i4;
                    if (bitCount$iv$iv$iv != $i$f$forEachIndexed4) {
                        break;
                    }
                }
                if (i$iv$iv$iv == lastIndex$iv$iv$iv) {
                    break;
                }
                i$iv$iv$iv++;
                i3 = i;
                mutableScatterMap3 = mutableScatterMap;
                $i$f$removeIf3 = $i$f$removeIf;
                this_$iv$iv$iv3 = this_$iv$iv$iv;
                $i$f$forEachIndexed3 = $i$f$forEachIndexed;
                m$iv$iv$iv4 = m$iv$iv$iv;
                i4 = 8;
            }
        } else {
            c = 7;
            j = 255;
        }
        if (this.conditionallyInvalidatedScopes.isNotEmpty()) {
            MutableScatterSet<RecomposeScopeImpl> mutableScatterSet = this.conditionallyInvalidatedScopes;
            Object[] elements$iv = mutableScatterSet.elements;
            MutableScatterSet<RecomposeScopeImpl> this_$iv$iv = mutableScatterSet;
            long[] m$iv$iv = this_$iv$iv.metadata;
            int lastIndex$iv$iv = m$iv$iv.length - 2;
            int i$iv$iv = 0;
            if (0 > lastIndex$iv$iv) {
                return;
            }
            while (true) {
                long slot$iv$iv = m$iv$iv[i$iv$iv];
                long $this$maskEmptyOrDeleted$iv$iv$iv = ((~slot$iv$iv) << c) & slot$iv$iv & (-9187201950435737472L);
                if ($this$maskEmptyOrDeleted$iv$iv$iv != -9187201950435737472L) {
                    int bitCount$iv$iv = 8 - ((~(i$iv$iv - lastIndex$iv$iv)) >>> 31);
                    for (int j$iv$iv = 0; j$iv$iv < bitCount$iv$iv; j$iv$iv++) {
                        long value$iv$iv$iv = slot$iv$iv & j;
                        if (value$iv$iv$iv < 128) {
                            int index$iv$iv = (i$iv$iv << 3) + j$iv$iv;
                            RecomposeScopeImpl scope = (RecomposeScopeImpl) elements$iv[index$iv$iv];
                            if (!scope.isConditional()) {
                                mutableScatterSet.removeElementAt(index$iv$iv);
                            }
                        }
                        slot$iv$iv >>= 8;
                    }
                    if (bitCount$iv$iv != 8) {
                        return;
                    }
                }
                if (i$iv$iv == lastIndex$iv$iv) {
                    return;
                } else {
                    i$iv$iv++;
                }
            }
        }
    }

    @Override // androidx.compose.runtime.ControlledComposition, androidx.compose.runtime.RecomposeScopeOwner
    public void recordReadOf(Object value) {
        RecomposeScopeImpl scope;
        DerivedState.Record record;
        int i;
        int i2;
        int i3;
        int j$iv$iv;
        if (!getAreChildrenComposing() && (scope = this.composer.getCurrentRecomposeScope$runtime()) != null) {
            int i4 = 0;
            int i5 = 1;
            scope.setUsed(true);
            boolean alreadyRead = scope.recordRead(value);
            CompositionObserver compositionObserverObserver = observer();
            if (compositionObserverObserver != null) {
                compositionObserverObserver.onReadInScope(scope, value);
            }
            if (!alreadyRead) {
                if (value instanceof StateObjectImpl) {
                    ReaderKind.Companion companion = ReaderKind.INSTANCE;
                    ((StateObjectImpl) value).m4724recordReadInh_f27i8$runtime(ReaderKind.m4712constructorimpl(1));
                }
                ScopeMap.m4466addimpl(this.observations, value, scope);
                if (value instanceof DerivedState) {
                    DerivedState.Record record2 = ((DerivedState) value).getCurrentRecord();
                    ScopeMap.m4488removeScopeimpl(this.derivedStates, value);
                    ObjectIntMap<StateObject> dependencies = record2.getDependencies();
                    Object[] k$iv = dependencies.keys;
                    long[] m$iv$iv = dependencies.metadata;
                    int lastIndex$iv$iv = m$iv$iv.length - 2;
                    int i$iv$iv = 0;
                    if (0 <= lastIndex$iv$iv) {
                        while (true) {
                            long slot$iv$iv = m$iv$iv[i$iv$iv];
                            int i6 = i5;
                            boolean alreadyRead2 = alreadyRead;
                            record = record2;
                            ObjectIntMap<StateObject> objectIntMap = dependencies;
                            long $this$maskEmptyOrDeleted$iv$iv$iv = ((~slot$iv$iv) << 7) & slot$iv$iv & (-9187201950435737472L);
                            if ($this$maskEmptyOrDeleted$iv$iv$iv == -9187201950435737472L) {
                                i = i4;
                            } else {
                                int i7 = 8;
                                int bitCount$iv$iv = 8 - ((~(i$iv$iv - lastIndex$iv$iv)) >>> 31);
                                int j$iv$iv2 = 0;
                                while (j$iv$iv2 < bitCount$iv$iv) {
                                    long value$iv$iv$iv = slot$iv$iv & 255;
                                    if ((value$iv$iv$iv < 128 ? i6 : 0) == 0) {
                                        i2 = i4;
                                        i3 = i7;
                                        j$iv$iv = j$iv$iv2;
                                    } else {
                                        int index$iv$iv = (i$iv$iv << 3) + j$iv$iv2;
                                        i3 = i7;
                                        StateObject dependency = (StateObject) k$iv[index$iv$iv];
                                        i2 = i4;
                                        if (!(dependency instanceof StateObjectImpl)) {
                                            j$iv$iv = j$iv$iv2;
                                        } else {
                                            ReaderKind.Companion companion2 = ReaderKind.INSTANCE;
                                            j$iv$iv = j$iv$iv2;
                                            int j$iv$iv3 = ReaderKind.m4712constructorimpl(i6);
                                            ((StateObjectImpl) dependency).m4724recordReadInh_f27i8$runtime(j$iv$iv3);
                                        }
                                        ScopeMap.m4466addimpl(this.derivedStates, dependency, value);
                                    }
                                    slot$iv$iv >>= i3;
                                    j$iv$iv2 = j$iv$iv + 1;
                                    i7 = i3;
                                    i4 = i2;
                                }
                                i = i4;
                                if (bitCount$iv$iv != i7) {
                                    break;
                                }
                            }
                            if (i$iv$iv == lastIndex$iv$iv) {
                                break;
                            }
                            i$iv$iv++;
                            record2 = record;
                            dependencies = objectIntMap;
                            i5 = i6;
                            alreadyRead = alreadyRead2;
                            i4 = i;
                        }
                    } else {
                        record = record2;
                    }
                    scope.recordDerivedStateValue((DerivedState) value, record.getCurrentValue());
                }
            }
        }
    }

    private final void invalidateScopeOfLocked(Object value) {
        int i;
        int j$iv$iv$iv;
        MutableScatterMap<Object, Object> mutableScatterMap = this.observations;
        Object key$iv = value;
        int i2 = 0;
        Object value$iv = mutableScatterMap.get(key$iv);
        if (value$iv == null) {
            return;
        }
        if (!(value$iv instanceof MutableScatterSet)) {
            RecomposeScopeImpl scope = (RecomposeScopeImpl) value$iv;
            if (scope.invalidateForResult(value) == InvalidationResult.IMMINENT) {
                ScopeMap.m4466addimpl(this.observationsProcessed, value, scope);
                return;
            }
            return;
        }
        ScatterSet this_$iv$iv = (MutableScatterSet) value$iv;
        Object[] elements$iv$iv = this_$iv$iv.elements;
        long[] m$iv$iv$iv = this_$iv$iv.metadata;
        int lastIndex$iv$iv$iv = m$iv$iv$iv.length - 2;
        int i$iv$iv$iv = 0;
        if (0 > lastIndex$iv$iv$iv) {
            return;
        }
        while (true) {
            long slot$iv$iv$iv = m$iv$iv$iv[i$iv$iv$iv];
            MutableScatterMap<Object, Object> mutableScatterMap2 = mutableScatterMap;
            Object key$iv2 = key$iv;
            int i3 = i2;
            Object value$iv2 = value$iv;
            long $this$maskEmptyOrDeleted$iv$iv$iv$iv = ((~slot$iv$iv$iv) << 7) & slot$iv$iv$iv & (-9187201950435737472L);
            if ($this$maskEmptyOrDeleted$iv$iv$iv$iv != -9187201950435737472L) {
                int i4 = 8;
                int bitCount$iv$iv$iv = 8 - ((~(i$iv$iv$iv - lastIndex$iv$iv$iv)) >>> 31);
                int j$iv$iv$iv2 = 0;
                while (j$iv$iv$iv2 < bitCount$iv$iv$iv) {
                    long value$iv$iv$iv$iv = slot$iv$iv$iv & 255;
                    if (!(value$iv$iv$iv$iv < 128)) {
                        i = i4;
                        j$iv$iv$iv = j$iv$iv$iv2;
                    } else {
                        int index$iv$iv$iv = (i$iv$iv$iv << 3) + j$iv$iv$iv2;
                        i = i4;
                        RecomposeScopeImpl scope2 = (RecomposeScopeImpl) elements$iv$iv[index$iv$iv$iv];
                        j$iv$iv$iv = j$iv$iv$iv2;
                        if (scope2.invalidateForResult(value) == InvalidationResult.IMMINENT) {
                            ScopeMap.m4466addimpl(this.observationsProcessed, value, scope2);
                        }
                    }
                    slot$iv$iv$iv >>= i;
                    j$iv$iv$iv2 = j$iv$iv$iv + 1;
                    i4 = i;
                }
                if (bitCount$iv$iv$iv != i4) {
                    return;
                }
            }
            if (i$iv$iv$iv == lastIndex$iv$iv$iv) {
                return;
            }
            i$iv$iv$iv++;
            i2 = i3;
            value$iv = value$iv2;
            mutableScatterMap = mutableScatterMap2;
            key$iv = key$iv2;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0091  */
    @Override // androidx.compose.runtime.ControlledComposition
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void recordWriteOf(java.lang.Object r28) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 206
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.CompositionImpl.recordWriteOf(java.lang.Object):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x004f A[Catch: all -> 0x0062, TRY_LEAVE, TryCatch #1 {all -> 0x0062, blocks: (B:23:0x0044, B:25:0x004f), top: B:61:0x0044 }] */
    @Override // androidx.compose.runtime.ControlledComposition
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean recompose() throws java.lang.Throwable {
        /*
            r19 = this;
            r1 = r19
            java.lang.Object r2 = r1.lock
            r3 = 0
            monitor-enter(r2)
            r4 = 0
            androidx.compose.runtime.PausedCompositionImpl r0 = r1.pendingPausedComposition     // Catch: java.lang.Throwable -> La9
            r5 = r0
            if (r5 == 0) goto L27
            boolean r0 = r5.isRecomposing$runtime()     // Catch: java.lang.Throwable -> L22
            if (r0 != 0) goto L27
            r5.markIncomplete$runtime()     // Catch: java.lang.Throwable -> L22
            androidx.compose.runtime.RecordingApplier r0 = r5.getPausableApplier$runtime()     // Catch: java.lang.Throwable -> L22
            r0.markRecomposePending()     // Catch: java.lang.Throwable -> L22
            r0 = r2
            monitor-exit(r2)
            r0 = 0
            return r0
        L22:
            r0 = move-exception
            r16 = r3
            goto Lac
        L27:
            r1.drainPendingModificationsForCompositionLocked()     // Catch: java.lang.Throwable -> La9
            r6 = r19
            r7 = 0
            r8 = r6
            r9 = 0
            r10 = 0
            r11 = 0
            r12 = r19
            r13 = 0
            androidx.collection.MutableScatterMap r0 = r12.m4389takeInvalidationsafanTW4()     // Catch: java.lang.Throwable -> L6d
            r14 = r0
            r0 = r14
            r15 = 0
            r16 = r3
            androidx.compose.runtime.InternalComposer r3 = r1.composer     // Catch: java.lang.Throwable -> L64
            r17 = r4
            androidx.compose.runtime.ShouldPauseCallback r4 = r1.shouldPause     // Catch: java.lang.Throwable -> L62
            boolean r3 = r3.mo4395recomposeaFTiNEg$runtime(r0, r4)     // Catch: java.lang.Throwable -> L62
            r4 = r3
            r18 = 0
            if (r4 != 0) goto L52
            r1.drainPendingModificationsLocked()     // Catch: java.lang.Throwable -> L62
        L52:
            r0 = r3
            r4 = 0
            r0 = 1
            monitor-exit(r2)
            return r3
        L62:
            r0 = move-exception
            goto L67
        L64:
            r0 = move-exception
            r17 = r4
        L67:
            r12.invalidations = r14     // Catch: java.lang.Throwable -> L6b
            throw r0     // Catch: java.lang.Throwable -> L6b
        L6b:
            r0 = move-exception
            goto L72
        L6d:
            r0 = move-exception
            r16 = r3
            r17 = r4
        L72:
            if (r10 != 0) goto L9f
            java.util.Set<androidx.compose.runtime.RememberObserver> r3 = r8.abandonSet     // Catch: java.lang.Throwable -> La1
            java.util.Collection r3 = (java.util.Collection) r3     // Catch: java.lang.Throwable -> La1
            boolean r3 = r3.isEmpty()     // Catch: java.lang.Throwable -> La1
            if (r3 != 0) goto L9f
            androidx.compose.runtime.internal.RememberEventDispatcher r3 = r8.rememberManager     // Catch: java.lang.Throwable -> La1
            java.util.Set<androidx.compose.runtime.RememberObserver> r4 = r8.abandonSet     // Catch: java.lang.Throwable -> La1
            androidx.compose.runtime.InternalComposer r11 = r8.composer     // Catch: java.lang.Throwable -> La1
            androidx.compose.runtime.tooling.CompositionErrorContextImpl r11 = r11.getErrorContext$runtime()     // Catch: java.lang.Throwable -> La1
            androidx.compose.runtime.tooling.CompositionErrorContext r11 = (androidx.compose.runtime.tooling.CompositionErrorContext) r11     // Catch: java.lang.Throwable -> La1
            r12 = 0
            r3.prepare(r4, r11)     // Catch: java.lang.Throwable -> L9a
            r13 = r3
            r14 = 0
            r13.dispatchAbandons()     // Catch: java.lang.Throwable -> L9a
            r3.clear()     // Catch: java.lang.Throwable -> La1
            goto L9f
        L9a:
            r0 = move-exception
            r3.clear()     // Catch: java.lang.Throwable -> La1
            throw r0     // Catch: java.lang.Throwable -> La1
        L9f:
            throw r0     // Catch: java.lang.Throwable -> La1
        La1:
            r0 = move-exception
            r6.abandonChanges()     // Catch: java.lang.Throwable -> La7
            throw r0     // Catch: java.lang.Throwable -> La7
        La7:
            r0 = move-exception
            goto Lac
        La9:
            r0 = move-exception
            r16 = r3
        Lac:
            monitor-exit(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.CompositionImpl.recompose():boolean");
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public void insertMovableContent(List<Pair<MovableContentStateReference, MovableContentStateReference>> references) {
        boolean value$iv;
        int index$iv$iv = 0;
        int size = references.size();
        while (true) {
            if (index$iv$iv < size) {
                Object item$iv$iv = references.get(index$iv$iv);
                if (!Intrinsics.areEqual(((Pair) item$iv$iv).getFirst().getComposition(), this)) {
                    value$iv = false;
                    break;
                }
                index$iv$iv++;
            } else {
                value$iv = true;
                break;
            }
        }
        boolean value$iv$iv = value$iv;
        if (!value$iv$iv) {
            ComposerKt.composeImmediateRuntimeError("Check failed");
        }
        try {
            this.composer.insertMovableContentReferences(references);
            Unit unit = Unit.INSTANCE;
        } catch (Throwable th) {
            if (0 == 0) {
                try {
                    if (!this.abandonSet.isEmpty()) {
                        RememberEventDispatcher this_$iv$iv$iv = this.rememberManager;
                        Set<RememberObserver> set = this.abandonSet;
                        CompositionErrorContext traceContext$iv$iv$iv = this.composer.getErrorContext$runtime();
                        try {
                            this_$iv$iv$iv.prepare(set, traceContext$iv$iv$iv);
                            this_$iv$iv$iv.dispatchAbandons();
                            this_$iv$iv$iv.clear();
                        } catch (Throwable th2) {
                            this_$iv$iv$iv.clear();
                            throw th2;
                        }
                    }
                } catch (Throwable e$iv) {
                    abandonChanges();
                    throw e$iv;
                }
            }
            throw th;
        }
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public void disposeUnusedMovableContent(MovableContentState state) {
        RememberEventDispatcher this_$iv = this.rememberManager;
        Set<RememberObserver> set = this.abandonSet;
        CompositionErrorContext traceContext$iv = this.composer.getErrorContext$runtime();
        try {
            this_$iv.prepare(set, traceContext$iv);
            state.getSlotStorage().disposeUnusedMovableContent(this.rememberManager, state);
            this_$iv.dispatchRememberObservers();
        } finally {
            this_$iv.clear();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x007c A[Catch: all -> 0x028f, TRY_ENTER, TryCatch #7 {all -> 0x028f, blocks: (B:31:0x006a, B:39:0x007e, B:38:0x007c), top: B:149:0x006a }] */
    /* JADX WARN: Removed duplicated region for block: B:77:0x01a7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void applyChangesInLocked(androidx.compose.runtime.Changes r54) {
        /*
            Method dump skipped, instruction units count: 698
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.CompositionImpl.applyChangesInLocked(androidx.compose.runtime.Changes):void");
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public void applyChanges() {
        Object lock$iv = this.lock;
        synchronized (lock$iv) {
            try {
                applyChangesInLocked(this.changes);
                drainPendingModificationsLocked();
                Unit unit = Unit.INSTANCE;
                Unit unit2 = Unit.INSTANCE;
            } catch (Throwable th) {
                if (0 == 0) {
                    try {
                        if (!this.abandonSet.isEmpty()) {
                            RememberEventDispatcher this_$iv$iv$iv = this.rememberManager;
                            Set<RememberObserver> set = this.abandonSet;
                            CompositionErrorContext traceContext$iv$iv$iv = this.composer.getErrorContext$runtime();
                            try {
                                this_$iv$iv$iv.prepare(set, traceContext$iv$iv$iv);
                                this_$iv$iv$iv.dispatchAbandons();
                                this_$iv$iv$iv.clear();
                            } catch (Throwable th2) {
                                this_$iv$iv$iv.clear();
                                throw th2;
                            }
                        }
                    } catch (Throwable e$iv) {
                        abandonChanges();
                        throw e$iv;
                    }
                }
                throw th;
            }
        }
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public void applyLateChanges() {
        Object lock$iv = this.lock;
        synchronized (lock$iv) {
            try {
                if (this.lateChanges.isNotEmpty()) {
                    applyChangesInLocked(this.lateChanges);
                }
                Unit unit = Unit.INSTANCE;
                Unit unit2 = Unit.INSTANCE;
            } catch (Throwable th) {
                if (0 == 0) {
                    try {
                        if (!this.abandonSet.isEmpty()) {
                            RememberEventDispatcher this_$iv$iv$iv = this.rememberManager;
                            Set<RememberObserver> set = this.abandonSet;
                            CompositionErrorContext traceContext$iv$iv$iv = this.composer.getErrorContext$runtime();
                            try {
                                this_$iv$iv$iv.prepare(set, traceContext$iv$iv$iv);
                                this_$iv$iv$iv.dispatchAbandons();
                                this_$iv$iv$iv.clear();
                            } catch (Throwable th2) {
                                this_$iv$iv$iv.clear();
                                throw th2;
                            }
                        }
                    } catch (Throwable e$iv) {
                        abandonChanges();
                        throw e$iv;
                    }
                }
                throw th;
            }
        }
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public void changesApplied() {
        RememberEventDispatcher this_$iv;
        Object lock$iv = this.lock;
        synchronized (lock$iv) {
            try {
                this.composer.changesApplied$runtime();
                if (!this.abandonSet.isEmpty()) {
                    this_$iv = this.rememberManager;
                    Set<RememberObserver> set = this.abandonSet;
                    CompositionErrorContext traceContext$iv = this.composer.getErrorContext$runtime();
                    try {
                        this_$iv.prepare(set, traceContext$iv);
                        this_$iv.dispatchAbandons();
                        this_$iv.clear();
                    } finally {
                    }
                }
                Unit unit = Unit.INSTANCE;
                Unit unit2 = Unit.INSTANCE;
            } catch (Throwable th) {
                if (0 == 0) {
                    try {
                        if (!this.abandonSet.isEmpty()) {
                            this_$iv = this.rememberManager;
                            Set<RememberObserver> set2 = this.abandonSet;
                            CompositionErrorContext traceContext$iv$iv$iv = this.composer.getErrorContext$runtime();
                            try {
                                this_$iv.prepare(set2, traceContext$iv$iv$iv);
                                this_$iv.dispatchAbandons();
                                this_$iv.clear();
                            } finally {
                            }
                        }
                    } catch (Throwable e$iv) {
                        abandonChanges();
                        throw e$iv;
                    }
                }
                throw th;
            }
        }
    }

    private final <T> T guardInvalidationsLocked(Function1<? super ScopeMap<RecomposeScopeImpl, Object>, ? extends T> block) {
        MutableScatterMap<Object, Object> mutableScatterMapM4389takeInvalidationsafanTW4 = m4389takeInvalidationsafanTW4();
        try {
            return block.invoke(ScopeMap.m4470boximpl(mutableScatterMapM4389takeInvalidationsafanTW4));
        } catch (Throwable e) {
            this.invalidations = mutableScatterMapM4389takeInvalidationsafanTW4;
            throw e;
        }
    }

    private final <T> T guardChanges(Function0<? extends T> block) {
        try {
            return block.invoke();
        } catch (Throwable th) {
            if (0 == 0) {
                try {
                    if (!this.abandonSet.isEmpty()) {
                        RememberEventDispatcher this_$iv$iv = this.rememberManager;
                        Set<RememberObserver> set = this.abandonSet;
                        CompositionErrorContext traceContext$iv$iv = this.composer.getErrorContext$runtime();
                        try {
                            this_$iv$iv.prepare(set, traceContext$iv$iv);
                            this_$iv$iv.dispatchAbandons();
                            this_$iv$iv.clear();
                        } catch (Throwable th2) {
                            this_$iv$iv.clear();
                            throw th2;
                        }
                    }
                } catch (Throwable e) {
                    abandonChanges();
                    throw e;
                }
            }
            throw th;
        }
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public void abandonChanges() {
        this.pendingModifications.set(null);
        this.changes.clear();
        this.lateChanges.clear();
        if (!this.abandonSet.isEmpty()) {
            RememberEventDispatcher this_$iv = this.rememberManager;
            Set<RememberObserver> set = this.abandonSet;
            CompositionErrorContext traceContext$iv = this.composer.getErrorContext$runtime();
            try {
                this_$iv.prepare(set, traceContext$iv);
                this_$iv.dispatchAbandons();
            } finally {
                this_$iv.clear();
            }
        }
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public void invalidateAll() {
        this.slotStorage.invalidateAll();
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public void verifyConsistent() {
        Object lock$iv = this.lock;
        synchronized (lock$iv) {
            if (!isComposing()) {
                this.composer.verifyConsistent$runtime();
                this.slotStorage.verifyWellFormed();
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public <R> R delegateInvalidations(ControlledComposition to, int groupIndex, Function0<? extends R> block) {
        if (to != null && !Intrinsics.areEqual(to, this) && groupIndex >= 0) {
            this.invalidationDelegate = (CompositionImpl) to;
            this.invalidationDelegateGroup = groupIndex;
            try {
                return block.invoke();
            } finally {
                this.invalidationDelegate = null;
                this.invalidationDelegateGroup = 0;
            }
        }
        return block.invoke();
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public ShouldPauseCallback getAndSetShouldPauseCallback(ShouldPauseCallback shouldPause) {
        ShouldPauseCallback previous = this.shouldPause;
        this.shouldPause = shouldPause;
        return previous;
    }

    @Override // androidx.compose.runtime.RecomposeScopeOwner
    public InvalidationResult invalidate(RecomposeScopeImpl scope, Object instance) throws Throwable {
        CompositionObserver compositionObserverObserver;
        CompositionImpl delegate;
        if (scope.getDefaultsInScope()) {
            scope.setDefaultsInvalid(true);
        }
        Anchor anchor = scope.getAnchor();
        if (anchor == null || !anchor.getValid()) {
            return InvalidationResult.IGNORED;
        }
        if (!this.slotStorage.ownsRecomposeScope(scope)) {
            Object lock$iv = this.lock;
            synchronized (lock$iv) {
                delegate = this.invalidationDelegate;
            }
            if (delegate != null && delegate.tryImminentInvalidation(scope, instance)) {
                return InvalidationResult.IMMINENT;
            }
            return InvalidationResult.IGNORED;
        }
        if (!scope.getCanRecompose()) {
            return InvalidationResult.IGNORED;
        }
        InvalidationResult it = invalidateChecked(scope, anchor, instance);
        if (it != InvalidationResult.IGNORED && (compositionObserverObserver = observer()) != null) {
            compositionObserverObserver.onScopeInvalidated(scope, instance);
        }
        return it;
    }

    @Override // androidx.compose.runtime.RecomposeScopeOwner
    public void recomposeScopeReleased(RecomposeScopeImpl scope) {
        this.pendingInvalidScopes = true;
        CompositionObserver compositionObserverObserver = observer();
        if (compositionObserverObserver != null) {
            compositionObserverObserver.onScopeDisposed(scope);
        }
    }

    @Override // androidx.compose.runtime.CompositionServices
    public <T> T getCompositionService(CompositionServiceKey<T> key) {
        if (Intrinsics.areEqual(key, CompositionKt.getObservableCompositionServiceKey())) {
            return (T) this;
        }
        return null;
    }

    private final boolean tryImminentInvalidation(RecomposeScopeImpl scope, Object instance) {
        return isComposing() && this.composer.tryImminentInvalidation$runtime(scope, instance);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0029 A[Catch: all -> 0x0177, TRY_ENTER, TRY_LEAVE, TryCatch #1 {all -> 0x0177, blocks: (B:5:0x000d, B:17:0x0029, B:26:0x0046, B:32:0x005f, B:34:0x0065, B:36:0x006b), top: B:96:0x000d }] */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0102  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0149 A[Catch: all -> 0x014f, TRY_LEAVE, TryCatch #2 {all -> 0x014f, blocks: (B:38:0x0082, B:40:0x008e, B:42:0x00af, B:44:0x00bb, B:49:0x00cc, B:75:0x0149, B:57:0x00f5, B:61:0x0104, B:65:0x011b), top: B:98:0x0063 }] */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0151  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x015e  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0163  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final androidx.compose.runtime.InvalidationResult invalidateChecked(androidx.compose.runtime.RecomposeScopeImpl r39, androidx.compose.runtime.Anchor r40, java.lang.Object r41) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 380
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.CompositionImpl.invalidateChecked(androidx.compose.runtime.RecomposeScopeImpl, androidx.compose.runtime.Anchor, java.lang.Object):androidx.compose.runtime.InvalidationResult");
    }

    public final void removeObservation$runtime(Object instance, RecomposeScopeImpl scope) {
        ScopeMap.m4486removeimpl(this.observations, instance, scope);
    }

    public final void removeDerivedStateObservation$runtime(DerivedState<?> state) {
        if (!ScopeMap.m4474containsimpl(this.observations, state)) {
            ScopeMap.m4488removeScopeimpl(this.derivedStates, state);
        }
    }

    /* JADX INFO: renamed from: takeInvalidations-afanTW4, reason: not valid java name */
    private final MutableScatterMap<Object, Object> m4389takeInvalidationsafanTW4() {
        MutableScatterMap<Object, Object> mutableScatterMap = this.invalidations;
        this.invalidations = ScopeMap.m4473constructorimpl$default(null, 1, null);
        return mutableScatterMap;
    }

    private final <T> T trackAbandonedValues(Function0<? extends T> block) {
        try {
            return block.invoke();
        } catch (Throwable th) {
            if (0 == 0 && !this.abandonSet.isEmpty()) {
                RememberEventDispatcher this_$iv = this.rememberManager;
                Set<RememberObserver> set = this.abandonSet;
                CompositionErrorContext traceContext$iv = this.composer.getErrorContext$runtime();
                try {
                    this_$iv.prepare(set, traceContext$iv);
                    this_$iv.dispatchAbandons();
                } finally {
                    this_$iv.clear();
                }
            }
            throw th;
        }
    }

    private final CompositionObserver observer() {
        return this.observerHolder.current();
    }

    @Override // androidx.compose.runtime.ReusableComposition
    public void deactivate() {
        RememberEventDispatcher $this$deactivate_u24lambda_u240_u241_u240;
        Object lock$iv = this.lock;
        synchronized (lock$iv) {
            boolean value$iv = this.pendingPausedComposition == null;
            if (!value$iv) {
                PreconditionsKt.throwIllegalStateException("Deactivate is not supported while pausable composition is in progress");
            }
            boolean nonEmptySlotTable = !this.slotStorage.isEmpty();
            if (nonEmptySlotTable || !this.abandonSet.isEmpty()) {
                Object token$iv = Trace.INSTANCE.beginSection("Compose:deactivate");
                try {
                    RememberEventDispatcher this_$iv = this.rememberManager;
                    Set<RememberObserver> set = this.abandonSet;
                    CompositionErrorContext traceContext$iv = this.composer.getErrorContext$runtime();
                    try {
                        this_$iv.prepare(set, traceContext$iv);
                        if (!nonEmptySlotTable) {
                            $this$deactivate_u24lambda_u240_u241_u240 = this_$iv;
                        } else {
                            this.applier.onBeginChanges();
                            SlotStorage slotStorage = this.slotStorage;
                            $this$deactivate_u24lambda_u240_u241_u240 = this_$iv;
                            RememberEventDispatcher $this$deactivate_u24lambda_u240_u241_u2402 = this.rememberManager;
                            slotStorage.deactivateAll($this$deactivate_u24lambda_u240_u241_u2402);
                            this.applier.onEndChanges();
                            $this$deactivate_u24lambda_u240_u241_u240.dispatchRememberObservers();
                        }
                        $this$deactivate_u24lambda_u240_u241_u240.dispatchAbandons();
                        this_$iv.clear();
                        Unit unit = Unit.INSTANCE;
                    } catch (Throwable th) {
                        this_$iv.clear();
                        throw th;
                    }
                } finally {
                    Trace.INSTANCE.endSection(token$iv);
                }
            }
            ScopeMap.m4471clearimpl(this.observations);
            ScopeMap.m4471clearimpl(this.derivedStates);
            ScopeMap.m4471clearimpl(this.invalidations);
            this.changes.clear();
            this.lateChanges.clear();
            this.composer.deactivate$runtime();
            this.state = 1;
            Unit unit2 = Unit.INSTANCE;
        }
    }

    public final int composerStacksSizes$runtime() {
        return this.composer.stacksSize$runtime();
    }
}
