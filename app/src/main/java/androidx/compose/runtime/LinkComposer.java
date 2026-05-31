package androidx.compose.runtime;

import androidx.collection.MutableIntIntMap;
import androidx.collection.MutableIntObjectMap;
import androidx.collection.MutableIntSet;
import androidx.collection.MutableScatterMap;
import androidx.collection.MutableScatterSet;
import androidx.collection.ScatterMap;
import androidx.collection.ScatterSet;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.runtime.collection.ScopeMap;
import androidx.compose.runtime.composer.GroupKind;
import androidx.compose.runtime.composer.ThrowingRememberManagerStub;
import androidx.compose.runtime.composer.linkbuffer.GroupFlagsKt;
import androidx.compose.runtime.composer.linkbuffer.GroupFlagsSpec;
import androidx.compose.runtime.composer.linkbuffer.GroupHandleKt;
import androidx.compose.runtime.composer.linkbuffer.KeyInfo;
import androidx.compose.runtime.composer.linkbuffer.LinkAnchor;
import androidx.compose.runtime.composer.linkbuffer.LinkAnchorKt;
import androidx.compose.runtime.composer.linkbuffer.SlotTable;
import androidx.compose.runtime.composer.linkbuffer.SlotTableAddressSpace;
import androidx.compose.runtime.composer.linkbuffer.SlotTableBuilder;
import androidx.compose.runtime.composer.linkbuffer.SlotTableBuilderKt;
import androidx.compose.runtime.composer.linkbuffer.SlotTableEditor;
import androidx.compose.runtime.composer.linkbuffer.SlotTableKt;
import androidx.compose.runtime.composer.linkbuffer.SlotTableReader;
import androidx.compose.runtime.composer.linkbuffer.SlotTableReaderKt;
import androidx.compose.runtime.composer.linkbuffer.changelist.ChangeList;
import androidx.compose.runtime.composer.linkbuffer.changelist.ChangeListKt;
import androidx.compose.runtime.composer.linkbuffer.changelist.ComposerChangeListWriter;
import androidx.compose.runtime.composer.linkbuffer.changelist.ComposerChangeListWriterAddressMode;
import androidx.compose.runtime.composer.linkbuffer.changelist.FixupList;
import androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentMap;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.internal.Expect_jvmKt;
import androidx.compose.runtime.internal.IntRef;
import androidx.compose.runtime.internal.PersistentCompositionLocalMapKt;
import androidx.compose.runtime.internal.Trace;
import androidx.compose.runtime.snapshots.ListUtilsKt;
import androidx.compose.runtime.snapshots.SnapshotKt;
import androidx.compose.runtime.tooling.ComposeStackTrace;
import androidx.compose.runtime.tooling.ComposeStackTraceFrame;
import androidx.compose.runtime.tooling.ComposeStackTraceKt;
import androidx.compose.runtime.tooling.CompositionData;
import androidx.compose.runtime.tooling.CompositionErrorContext;
import androidx.compose.runtime.tooling.CompositionErrorContextImpl;
import androidx.compose.runtime.tooling.CompositionErrorContextKt;
import androidx.compose.runtime.tooling.CompositionObserver;
import androidx.compose.runtime.tooling.InspectionTablesKt;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.UInt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;

/* JADX INFO: compiled from: LinkComposer.kt */
/* JADX INFO: loaded from: classes12.dex */
@ComposeCompilerApi
@Metadata(d1 = {"\u0000³\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\b\r\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\f\n\u0002\u0010\u0005\n\u0002\u0010\n\n\u0002\u0010\u0007\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b0\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n*\u0001V\b\u0001\u0018\u00002\u00020\u0001:\u0004ó\u0002ô\u0002BQ\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\f\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011¢\u0006\u0004\b\u0012\u0010\u0013J\b\u0010m\u001a\u00020nH\u0016J\b\u0010o\u001a\u00020nH\u0016J\b\u0010p\u001a\u00020nH\u0016J\r\u0010+\u001a\u00020,H\u0010¢\u0006\u0002\b|J\u001e\u0010\u007f\u001a\u00020n2\u000e\u0010\u0080\u0001\u001a\t\u0012\u0004\u0012\u00020n0\u0081\u0001H\u0010¢\u0006\u0003\b\u0082\u0001J\u0013\u0010\u008a\u0001\u001a\u00020n2\b\u0010\u008b\u0001\u001a\u00030\u0084\u0001H\u0016JJ\u0010\u008e\u0001\u001a\u00020n\"\u0005\b\u0000\u0010\u008f\u0001\"\u0005\b\u0001\u0010\u0090\u00012\u0007\u0010Y\u001a\u0003H\u008f\u00012\"\u0010\u0080\u0001\u001a\u001d\u0012\u0005\u0012\u0003H\u0090\u0001\u0012\u0005\u0012\u0003H\u008f\u0001\u0012\u0004\u0012\u00020n0\u0091\u0001¢\u0006\u0003\b\u0092\u0001H\u0016¢\u0006\u0003\u0010\u0093\u0001J\t\u0010\u0099\u0001\u001a\u00020\u0005H\u0017J\u0013\u0010\u009a\u0001\u001a\u00020,2\b\u0010Y\u001a\u0004\u0018\u00010\u001bH\u0016J\u0013\u0010\u009b\u0001\u001a\u00020,2\b\u0010Y\u001a\u0004\u0018\u00010\u001bH\u0016J\u0012\u0010\u009a\u0001\u001a\u00020,2\u0007\u0010Y\u001a\u00030\u009c\u0001H\u0016J\u0012\u0010\u009a\u0001\u001a\u00020,2\u0007\u0010Y\u001a\u00030\u009d\u0001H\u0016J\u0012\u0010\u009a\u0001\u001a\u00020,2\u0007\u0010Y\u001a\u00030\u009e\u0001H\u0016J\u0011\u0010\u009a\u0001\u001a\u00020,2\u0006\u0010Y\u001a\u00020,H\u0016J\u0012\u0010\u009a\u0001\u001a\u00020,2\u0007\u0010Y\u001a\u00030\u009f\u0001H\u0016J\u0011\u0010\u009a\u0001\u001a\u00020,2\u0006\u0010Y\u001a\u00020cH\u0016J\u0012\u0010\u009a\u0001\u001a\u00020,2\u0007\u0010Y\u001a\u00030 \u0001H\u0016J\u0011\u0010\u009a\u0001\u001a\u00020,2\u0006\u0010Y\u001a\u00020#H\u0016J\t\u0010¡\u0001\u001a\u00020nH\u0016JH\u0010¢\u0001\u001a\u00020n2\u0013\u0010£\u0001\u001a\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u001b0\u00192\u0014\u0010¤\u0001\u001a\u000f\u0012\u0004\u0012\u00020n0\u0081\u0001¢\u0006\u0003\b¥\u00012\t\u0010¦\u0001\u001a\u0004\u0018\u00010wH\u0011¢\u0006\u0006\b§\u0001\u0010¨\u0001J(\u0010¬\u0001\u001a\u0003H\u0090\u0001\"\u0005\b\u0000\u0010\u0090\u00012\u000f\u0010\u00ad\u0001\u001a\n\u0012\u0005\u0012\u0003H\u0090\u00010®\u0001H\u0017¢\u0006\u0003\u0010¯\u0001J!\u0010°\u0001\u001a\u00020n\"\u0005\b\u0000\u0010\u0090\u00012\u000f\u0010±\u0001\u001a\n\u0012\u0005\u0012\u0003H\u0090\u00010\u0081\u0001H\u0016J\u000f\u0010º\u0001\u001a\u00020nH\u0010¢\u0006\u0003\b»\u0001J\u0012\u0010¼\u0001\u001a\u00020n2\u0007\u0010\u009a\u0001\u001a\u00020,H\u0016J\u000f\u0010½\u0001\u001a\u00020nH\u0010¢\u0006\u0003\b¾\u0001J\t\u0010¿\u0001\u001a\u00020nH\u0016J\t\u0010À\u0001\u001a\u00020nH\u0016J\t\u0010Á\u0001\u001a\u00020nH\u0017J\t\u0010Â\u0001\u001a\u00020nH\u0017J\t\u0010Ã\u0001\u001a\u00020nH\u0016J\f\u0010Ä\u0001\u001a\u0005\u0018\u00010Å\u0001H\u0016J\t\u0010Æ\u0001\u001a\u00020nH\u0017J\t\u0010Ç\u0001\u001a\u00020nH\u0016J\u000f\u0010È\u0001\u001a\u00020nH\u0010¢\u0006\u0003\bÉ\u0001J\t\u0010Ê\u0001\u001a\u00020nH\u0016J\u0017\u0010Ë\u0001\u001a\u00020n2\f\u0010Ì\u0001\u001a\u00070#j\u0003`·\u0001H\u0016J!\u0010Ï\u0001\u001a\u00020n2\u000b\u0010Y\u001a\u0007\u0012\u0002\b\u00030Ð\u00012\t\u0010Ñ\u0001\u001a\u0004\u0018\u00010\u001bH\u0017J*\u0010Ò\u0001\u001a\u00020n2\u001f\u0010Ó\u0001\u001a\u001a\u0012\u0015\u0012\u0013\u0012\u0005\u0012\u00030Ö\u0001\u0012\u0007\u0012\u0005\u0018\u00010Ö\u00010Õ\u00010Ô\u0001H\u0017J\u001f\u0010×\u0001\u001a\u00020\u001b2\t\u0010Ø\u0001\u001a\u0004\u0018\u00010\u001b2\t\u0010Ù\u0001\u001a\u0004\u0018\u00010\u001bH\u0016J\u000f\u0010Ú\u0001\u001a\u00020#H\u0011¢\u0006\u0003\bÛ\u0001J\u0017\u0010Ü\u0001\u001a\n\u0012\u0005\u0012\u00030Ý\u00010Ô\u0001H\u0010¢\u0006\u0003\bÞ\u0001J2\u0010ß\u0001\u001a\u00020,2\u0013\u0010£\u0001\u001a\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u001b0\u00192\t\u0010¦\u0001\u001a\u0004\u0018\u00010wH\u0011¢\u0006\u0006\bà\u0001\u0010á\u0001J\u0019\u0010â\u0001\u001a\u00020n2\u000e\u0010ã\u0001\u001a\t\u0012\u0004\u0012\u00020n0\u0081\u0001H\u0017J\u000b\u0010ä\u0001\u001a\u0004\u0018\u00010\u001bH\u0016J\u001b\u0010å\u0001\u001a\u00020,2\u0007\u0010æ\u0001\u001a\u00020,2\u0007\u0010ç\u0001\u001a\u00020#H\u0017J\t\u0010è\u0001\u001a\u00020nH\u0017J\t\u0010é\u0001\u001a\u00020nH\u0017J\u001a\u0010ê\u0001\u001a\u00030ë\u00012\u000e\u0010ì\u0001\u001a\t\u0012\u0004\u0012\u00020n0\u0081\u0001H\u0016J\u0013\u0010í\u0001\u001a\u00020n2\b\u0010í\u0001\u001a\u00030î\u0001H\u0016J\t\u0010ï\u0001\u001a\u00020nH\u0016J\u001c\u0010ð\u0001\u001a\u00020n2\u0007\u0010\u00ad\u0001\u001a\u00020#2\b\u0010í\u0001\u001a\u00030î\u0001H\u0016J\u000f\u0010ñ\u0001\u001a\u00020#H\u0010¢\u0006\u0003\bò\u0001J\u001a\u0010ó\u0001\u001a\u00030ô\u00012\b\u0010Y\u001a\u0004\u0018\u00010\u001bH\u0010¢\u0006\u0003\bõ\u0001J\t\u0010ö\u0001\u001a\u00020nH\u0016J\t\u0010÷\u0001\u001a\u00020nH\u0016J\u0016\u0010ø\u0001\u001a\u00020n2\u000b\u0010Y\u001a\u0007\u0012\u0002\b\u00030ù\u0001H\u0017J&\u0010ú\u0001\u001a\u00020n2\u0015\u0010û\u0001\u001a\u0010\u0012\u000b\b\u0001\u0012\u0007\u0012\u0002\b\u00030ù\u00010ü\u0001H\u0017¢\u0006\u0003\u0010ý\u0001J\u0012\u0010þ\u0001\u001a\u00020n2\u0007\u0010\u00ad\u0001\u001a\u00020#H\u0016J\u0012\u0010ÿ\u0001\u001a\u00020n2\u0007\u0010\u00ad\u0001\u001a\u00020#H\u0016J\u0013\u0010\u0080\u0002\u001a\u00030\u0081\u00022\u0007\u0010\u00ad\u0001\u001a\u00020#H\u0016J\u001d\u0010\u0082\u0002\u001a\u00020n2\u0007\u0010\u00ad\u0001\u001a\u00020#2\t\u0010\u0083\u0002\u001a\u0004\u0018\u00010\u001bH\u0016J\t\u0010\u0084\u0002\u001a\u00020nH\u0016J\u000f\u0010\u0085\u0002\u001a\u00020nH\u0010¢\u0006\u0003\b\u0086\u0002J\u001d\u0010\u0087\u0002\u001a\u00020n2\u0007\u0010\u00ad\u0001\u001a\u00020#2\t\u0010\u0083\u0002\u001a\u0004\u0018\u00010\u001bH\u0016J#\u0010\u0088\u0002\u001a\u00020,2\u0007\u0010\u008b\u0001\u001a\u00020\u001a2\t\u0010\u0089\u0002\u001a\u0004\u0018\u00010\u001bH\u0010¢\u0006\u0003\b\u008a\u0002J'\u0010\u008b\u0002\u001a\u00020n2\u0013\u0010£\u0001\u001a\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u001b0\u0019H\u0010¢\u0006\u0006\b\u008c\u0002\u0010\u008d\u0002J\u0013\u0010\u008e\u0002\u001a\u00020n2\b\u0010Y\u001a\u0004\u0018\u00010\u001bH\u0016J\t\u0010\u008f\u0002\u001a\u00020nH\u0016J\u000f\u0010\u0090\u0002\u001a\u00020nH\u0010¢\u0006\u0003\b\u0091\u0002J\u0011\u0010\u0092\u0002\u001a\u0004\u0018\u00010\u001bH\u0000¢\u0006\u0003\b\u0093\u0002J\u0011\u0010\u0094\u0002\u001a\u0004\u0018\u00010\u001bH\u0000¢\u0006\u0003\b\u0095\u0002J\u0013\u0010\u0096\u0002\u001a\u00020n2\b\u0010Y\u001a\u0004\u0018\u00010\u001bH\u0001J\t\u0010\u0097\u0002\u001a\u00020nH\u0002J\t\u0010\u0098\u0002\u001a\u00020nH\u0002J\t\u0010\u0099\u0002\u001a\u00020nH\u0002J\t\u0010\u009a\u0002\u001a\u00020nH\u0002J\f\u0010\u009b\u0002\u001a\u0005\u0018\u00010ô\u0001H\u0002J?\u0010\u009c\u0002\u001a\u00020n2\u0013\u0010£\u0001\u001a\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u001b0\u00192\u0016\u0010¤\u0001\u001a\u0011\u0012\u0004\u0012\u00020n\u0018\u00010\u0081\u0001¢\u0006\u0003\b¥\u0001H\u0003¢\u0006\u0006\b\u009d\u0002\u0010\u009e\u0002J\u0012\u0010\u009f\u0002\u001a\u00020n2\u0007\u0010 \u0002\u001a\u00020,H\u0002J\t\u0010¡\u0002\u001a\u00020nH\u0002J\t\u0010¢\u0002\u001a\u00020nH\u0002J\u000f\u0010£\u0002\u001a\u00020nH\u0010¢\u0006\u0003\b¤\u0002J\u001d\u0010¥\u0002\u001a\u00020n2\u0007\u0010 \u0002\u001a\u00020,2\t\u0010¦\u0002\u001a\u0004\u0018\u00010\u001fH\u0002J\t\u0010§\u0002\u001a\u00020nH\u0002J\u001a\u0010¨\u0002\u001a\u00020n2\u0007\u0010©\u0002\u001a\u00020#2\u0006\u0010}\u001a\u00020,H\u0002J\t\u0010ª\u0002\u001a\u00020nH\u0002J\t\u0010«\u0002\u001a\u00020nH\u0002J\t\u0010¬\u0002\u001a\u000201H\u0002J\u0017\u0010¬\u0002\u001a\u0002012\f\u0010\u00ad\u0002\u001a\u00070#j\u0003`·\u0001H\u0002J*\u0010®\u0002\u001a\u00020n2\u001f\u0010Ó\u0001\u001a\u001a\u0012\u0015\u0012\u0013\u0012\u0005\u0012\u00030Ö\u0001\u0012\u0007\u0012\u0005\u0018\u00010Ö\u00010Õ\u00010Ô\u0001H\u0003Jt\u0010¯\u0002\u001a\u0003H°\u0002\"\u0005\b\u0000\u0010°\u00022\f\b\u0002\u0010±\u0002\u001a\u0005\u0018\u00010²\u00022\f\b\u0002\u0010³\u0002\u001a\u0005\u0018\u00010²\u00022\u000e\b\u0002\u0010´\u0002\u001a\u00070#j\u0003`·\u00012\u001e\b\u0002\u0010\u0018\u001a\u0018\u0012\u0013\u0012\u0011\u0012\u0004\u0012\u00020\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u001b0Õ\u00010Ô\u00012\u000f\u0010\u0080\u0001\u001a\n\u0012\u0005\u0012\u0003H°\u00020\u0081\u0001H\u0002¢\u0006\u0003\u0010µ\u0002J8\u0010¶\u0002\u001a\u00020n2\u0010\u0010¤\u0001\u001a\u000b\u0012\u0006\u0012\u0004\u0018\u00010\u001b0Ð\u00012\u0007\u0010·\u0002\u001a\u0002012\t\u0010Ñ\u0001\u001a\u0004\u0018\u00010\u001b2\u0007\u0010¸\u0002\u001a\u00020,H\u0003J\u0017\u0010¹\u0002\u001a\u00020,2\f\u0010\u00ad\u0002\u001a\u00070cj\u0003`º\u0002H\u0002J\t\u0010»\u0002\u001a\u00020nH\u0003J\t\u0010¼\u0002\u001a\u00020nH\u0002J\u0017\u0010½\u0002\u001a\u00020n2\f\u0010¾\u0002\u001a\u00070cj\u0003`º\u0002H\u0002J\u0012\u0010¿\u0002\u001a\u00020n2\u0007\u0010À\u0002\u001a\u000201H\u0002J\t\u0010Á\u0002\u001a\u00020nH\u0002J\u0017\u0010Â\u0002\u001a\u00020n2\f\u0010Ã\u0002\u001a\u00070cj\u0003`º\u0002H\u0002J\u0012\u0010Ä\u0002\u001a\u00020n2\u0007\u0010½\u0001\u001a\u00020,H\u0002J\u0017\u0010Å\u0002\u001a\u00020\u001a2\f\u0010\u00ad\u0002\u001a\u00070#j\u0003`·\u0001H\u0002J\u0017\u0010Æ\u0002\u001a\u00020,2\f\u0010\u00ad\u0002\u001a\u00070#j\u0003`·\u0001H\u0002J\u0017\u0010Ç\u0002\u001a\u00020#2\f\u0010\u00ad\u0002\u001a\u00070#j\u0003`·\u0001H\u0002J\t\u0010È\u0002\u001a\u00020nH\u0002J\t\u0010É\u0002\u001a\u00020nH\u0002J;\u0010Ê\u0002\u001a\u00020n2\u0007\u0010\u00ad\u0001\u001a\u00020#2\t\u0010Ë\u0002\u001a\u0004\u0018\u00010\u001b2\b\u0010Ì\u0002\u001a\u00030Í\u00022\t\u0010Î\u0002\u001a\u0004\u0018\u00010\u001bH\u0002¢\u0006\u0006\bÏ\u0002\u0010Ð\u0002J\u0013\u0010Ñ\u0002\u001a\u00020n2\u0007\u0010\u00ad\u0001\u001a\u00020#H\u0082\bJ\u001d\u0010Ñ\u0002\u001a\u00020n2\u0007\u0010\u00ad\u0001\u001a\u00020#2\t\u0010\u0083\u0002\u001a\u0004\u0018\u00010\u001bH\u0002J\u001d\u0010Ò\u0002\u001a\u00020n2\u0007\u0010 \u0002\u001a\u00020,2\t\u0010Î\u0002\u001a\u0004\u0018\u00010\u001bH\u0002J\t\u0010Ó\u0002\u001a\u00020nH\u0002J+\u0010Ô\u0002\u001a\n\u0012\u0005\u0012\u00030Ý\u00010Ô\u00012\u0007\u0010\u00ad\u0002\u001a\u00020#2\t\u0010Õ\u0002\u001a\u0004\u0018\u00010#H\u0002¢\u0006\u0003\u0010Ö\u0002J\u0019\u0010×\u0002\u001a\u00020n2\b\u0010Y\u001a\u0004\u0018\u00010\u001bH\u0000¢\u0006\u0003\bØ\u0002J \u0010Ù\u0002\u001a\u00020n2\f\u0010Ú\u0002\u001a\u00070cj\u0003`Û\u00022\u0007\u0010Ü\u0002\u001a\u00020#H\u0002J \u0010Ý\u0002\u001a\u00020n2\f\u0010Þ\u0002\u001a\u00070cj\u0003`Û\u00022\u0007\u0010ß\u0002\u001a\u00020#H\u0002J\u001b\u0010à\u0002\u001a\u0002012\u0007\u0010á\u0002\u001a\u0002012\u0007\u0010â\u0002\u001a\u000201H\u0002J\u0013\u0010ã\u0002\u001a\u00020n2\b\u0010Y\u001a\u0004\u0018\u00010\u001bH\u0002J\u0017\u0010ä\u0002\u001a\u00020#2\f\u0010Þ\u0002\u001a\u00070cj\u0003`Û\u0002H\u0002J\u0011\u0010å\u0002\u001a\u0004\u0018\u00010\u001b*\u0004\u0018\u00010\u001bH\u0002J1\u0010æ\u0002\u001a\u0003H°\u0002\"\u0005\b\u0000\u0010°\u00022\u0006\u00109\u001a\u00020:2\u000f\u0010\u0080\u0001\u001a\n\u0012\u0005\u0012\u0003H°\u00020\u0081\u0001H\u0082\b¢\u0006\u0003\u0010ç\u0002J\u0012\u0010è\u0002\u001a\u00020n2\u0007\u0010\u008b\u0001\u001a\u00020\u001aH\u0002J\"\u0010é\u0002\u001a\u0012\u0012\u0005\u0012\u00030ë\u0002\u0012\u0004\u0012\u00020n\u0018\u00010ê\u00022\u0007\u0010\u008b\u0001\u001a\u00020\u001aH\u0002J1\u0010ì\u0002\u001a\u00020n2\u0007\u0010í\u0002\u001a\u00020#2\u0006\u0010%\u001a\u00020#2\t\u0010\u0083\u0002\u001a\u0004\u0018\u00010\u001b2\t\u0010Î\u0002\u001a\u0004\u0018\u00010\u001bH\u0082\bJ\u001b\u0010î\u0002\u001a\u00020n2\u0007\u0010í\u0002\u001a\u00020#2\u0006\u0010%\u001a\u00020#H\u0082\bJ1\u0010ï\u0002\u001a\u00020n2\u0007\u0010í\u0002\u001a\u00020#2\u0006\u0010%\u001a\u00020#2\t\u0010\u0083\u0002\u001a\u0004\u0018\u00010\u001b2\t\u0010Î\u0002\u001a\u0004\u0018\u00010\u001bH\u0082\bJ\u001b\u0010ð\u0002\u001a\u00020n2\u0007\u0010í\u0002\u001a\u00020#2\u0006\u0010%\u001a\u00020#H\u0082\bJ\t\u0010ñ\u0002\u001a\u00020nH\u0002J\t\u0010ò\u0002\u001a\u00020nH\u0002R\u0018\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\u00020\u0011X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u001c\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u001b0\u0019X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001cR\u0018\u0010\u001d\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001f0\u001eX\u0082\u0004¢\u0006\u0004\n\u0002\u0010 R\u0010\u0010!\u001a\u0004\u0018\u00010\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020#X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020#X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020#X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020'X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010(\u001a\u0004\u0018\u00010)X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010*\u001a\u0004\u0018\u00010)X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020,X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020,X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020,X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u00020'X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00100\u001a\u000201X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u00102\u001a\n\u0012\u0004\u0012\u000201\u0018\u000103X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00104\u001a\u00020,X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00105\u001a\u00020'X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00106\u001a\u00020,X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00107\u001a\u00020#X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00108\u001a\u0004\u0018\u000101X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u00109\u001a\u00020:X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b;\u0010<\"\u0004\b=\u0010>R\u000e\u0010?\u001a\u00020@X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010A\u001a\u00020,X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010B\u001a\u00020CX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010D\u001a\u0004\u0018\u00010EX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010F\u001a\u00020#X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010G\u001a\u00020HX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010I\u001a\u00020\n8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bJ\u0010KR\u0014\u0010L\u001a\u00020\n8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bM\u0010KR\u000e\u0010N\u001a\u00020#X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010O\u001a\u00020#X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010P\u001a\u00020,X\u0090\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bQ\u0010R\"\u0004\bS\u0010TR\u0010\u0010U\u001a\u00020VX\u0082\u0004¢\u0006\u0004\n\u0002\u0010WR\u0016\u0010X\u001a\b\u0012\u0004\u0012\u00020\u001a0\u001eX\u0082\u0004¢\u0006\u0004\n\u0002\u0010 R\u001e\u0010Z\u001a\u00020,2\u0006\u0010Y\u001a\u00020,@RX\u0090\u000e¢\u0006\b\n\u0000\u001a\u0004\b[\u0010RR\u001e\u0010\\\u001a\u00020,2\u0006\u0010Y\u001a\u00020,@BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\b]\u0010RR\u0014\u0010^\u001a\u00020,8PX\u0090\u0004¢\u0006\u0006\u001a\u0004\b_\u0010RR\u0016\u0010`\u001a\u0004\u0018\u00010\u001a8PX\u0090\u0004¢\u0006\u0006\u001a\u0004\ba\u0010bR0\u0010e\u001a\u00060cj\u0002`d2\n\u0010Y\u001a\u00060cj\u0002`d8\u0016@RX\u0097\u000e¢\u0006\u0010\n\u0002\u0010j\u0012\u0004\bf\u0010g\u001a\u0004\bh\u0010iR\u0014\u0010k\u001a\u00020,8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bl\u0010RR\u001c\u0010q\u001a\u0004\u0018\u00010\fX\u0090\u000e¢\u0006\u000e\n\u0000\u001a\u0004\br\u0010s\"\u0004\bt\u0010uR\u0010\u0010v\u001a\u0004\u0018\u00010wX\u0082\u000e¢\u0006\u0002\n\u0000R\u0018\u0010x\u001a\u0004\u0018\u00010y8PX\u0090\u0004¢\u0006\b\n\u0000\u001a\u0004\bz\u0010{R\u001e\u0010}\u001a\u00020,2\u0006\u0010Y\u001a\u00020,@RX\u0096\u000e¢\u0006\b\n\u0000\u001a\u0004\b~\u0010RR\u001a\u0010\u0083\u0001\u001a\u0005\u0018\u00010\u0084\u00018VX\u0096\u0004¢\u0006\b\u001a\u0006\b\u0085\u0001\u0010\u0086\u0001R\u0019\u0010\u0087\u0001\u001a\u0004\u0018\u00010\u001b8VX\u0096\u0004¢\u0006\b\u001a\u0006\b\u0088\u0001\u0010\u0089\u0001R\u0016\u0010\u008c\u0001\u001a\u00020,8VX\u0096\u0004¢\u0006\u0007\u001a\u0005\b\u008d\u0001\u0010RR!\u0010\u0094\u0001\u001a\u00030\u0095\u00018\u0016X\u0097\u0004¢\u0006\u0011\n\u0000\u0012\u0005\b\u0096\u0001\u0010g\u001a\u0006\b\u0097\u0001\u0010\u0098\u0001R\u0017\u0010©\u0001\u001a\u00020E8VX\u0096\u0004¢\u0006\b\u001a\u0006\bª\u0001\u0010«\u0001R\u0018\u0010²\u0001\u001a\u00030³\u00018VX\u0096\u0004¢\u0006\b\u001a\u0006\b´\u0001\u0010µ\u0001R\u001c\u0010¶\u0001\u001a\u00070#j\u0003`·\u00018VX\u0096\u0004¢\u0006\b\u001a\u0006\b¸\u0001\u0010¹\u0001R\u0016\u0010Í\u0001\u001a\u00020,8PX\u0090\u0004¢\u0006\u0007\u001a\u0005\bÎ\u0001\u0010R¨\u0006õ\u0002"}, d2 = {"Landroidx/compose/runtime/LinkComposer;", "Landroidx/compose/runtime/InternalComposer;", "applier", "Landroidx/compose/runtime/Applier;", "parentContext", "Landroidx/compose/runtime/CompositionContext;", "abandonSet", "", "Landroidx/compose/runtime/RememberObserver;", "slotTable", "Landroidx/compose/runtime/composer/linkbuffer/SlotTable;", "changes", "Landroidx/compose/runtime/Changes;", "lateChanges", "observerHolder", "Landroidx/compose/runtime/CompositionObserverHolder;", "composition", "Landroidx/compose/runtime/CompositionImpl;", "<init>", "(Landroidx/compose/runtime/Applier;Landroidx/compose/runtime/CompositionContext;Ljava/util/Set;Landroidx/compose/runtime/composer/linkbuffer/SlotTable;Landroidx/compose/runtime/Changes;Landroidx/compose/runtime/Changes;Landroidx/compose/runtime/CompositionObserverHolder;Landroidx/compose/runtime/CompositionImpl;)V", "getApplier", "()Landroidx/compose/runtime/Applier;", "getComposition", "()Landroidx/compose/runtime/CompositionImpl;", "invalidations", "Landroidx/compose/runtime/collection/ScopeMap;", "Landroidx/compose/runtime/RecomposeScopeImpl;", "", "Landroidx/collection/MutableScatterMap;", "pendingStack", "Landroidx/compose/runtime/Stack;", "Landroidx/compose/runtime/LinkPending;", "Ljava/util/ArrayList;", "pending", "nodeIndex", "", "groupNodeCount", "rGroupIndex", "parentStateStack", "Landroidx/compose/runtime/IntStack;", "nodeCountOverrides", "Landroidx/collection/MutableIntIntMap;", "nodeCountVirtualOverrides", "forceRecomposeScopes", "", "forciblyRecompose", "nodeExpected", "entersStack", "rootProvider", "Landroidx/compose/runtime/PersistentCompositionLocalMap;", "providerUpdates", "Landroidx/collection/MutableIntObjectMap;", "providersInvalid", "providersInvalidStack", "reusing", "reusingGroup", "providerCache", "reader", "Landroidx/compose/runtime/composer/linkbuffer/SlotTableReader;", "getReader$runtime", "()Landroidx/compose/runtime/composer/linkbuffer/SlotTableReader;", "setReader$runtime", "(Landroidx/compose/runtime/composer/linkbuffer/SlotTableReader;)V", "builder", "Landroidx/compose/runtime/composer/linkbuffer/SlotTableBuilder;", "builderHasAProvider", "changeListWriter", "Landroidx/compose/runtime/composer/linkbuffer/changelist/ComposerChangeListWriter;", "_compositionData", "Landroidx/compose/runtime/tooling/CompositionData;", "lastPlacedChildGroup", "insertFixups", "Landroidx/compose/runtime/composer/linkbuffer/changelist/FixupList;", "insertTable", "getInsertTable$runtime", "()Landroidx/compose/runtime/composer/linkbuffer/SlotTable;", "readerTable", "getReaderTable$runtime", "childrenComposing", "compositionToken", "sourceMarkersEnabled", "getSourceMarkersEnabled$runtime", "()Z", "setSourceMarkersEnabled$runtime", "(Z)V", "derivedStateObserver", "androidx/compose/runtime/LinkComposer$derivedStateObserver$1", "Landroidx/compose/runtime/LinkComposer$derivedStateObserver$1;", "invalidateStack", "value", "isComposing", "isComposing$runtime", "isDisposed", "isDisposed$runtime", "areChildrenComposing", "getAreChildrenComposing$runtime", "currentRecomposeScope", "getCurrentRecomposeScope$runtime", "()Landroidx/compose/runtime/RecomposeScopeImpl;", "", "Landroidx/compose/runtime/CompositeKeyHashCode;", "compositeKeyHashCode", "getCompositeKeyHashCode$annotations", "()V", "getCompositeKeyHashCode", "()J", "J", "defaultsInvalid", "getDefaultsInvalid", "disableReusing", "", "disableSourceInformation", "enableReusing", "deferredChanges", "getDeferredChanges$runtime", "()Landroidx/compose/runtime/Changes;", "setDeferredChanges$runtime", "(Landroidx/compose/runtime/Changes;)V", "shouldPauseCallback", "Landroidx/compose/runtime/ShouldPauseCallback;", "errorContext", "Landroidx/compose/runtime/tooling/CompositionErrorContextImpl;", "getErrorContext$runtime", "()Landroidx/compose/runtime/tooling/CompositionErrorContextImpl;", "forceRecomposeScopes$runtime", "inserting", "getInserting", "prepareCompose", "block", "Lkotlin/Function0;", "prepareCompose$runtime", "recomposeScope", "Landroidx/compose/runtime/RecomposeScope;", "getRecomposeScope", "()Landroidx/compose/runtime/RecomposeScope;", "recomposeScopeIdentity", "getRecomposeScopeIdentity", "()Ljava/lang/Object;", "recordUsed", "scope", "skipping", "getSkipping", "apply", "V", "T", "Lkotlin/Function2;", "Lkotlin/ExtensionFunctionType;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)V", "applyCoroutineContext", "Lkotlin/coroutines/CoroutineContext;", "getApplyCoroutineContext$annotations", "getApplyCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "buildContext", "changed", "changedInstance", "", "", "", "", "", "collectParameterInformation", "composeContent", "invalidationsRequested", "content", "Landroidx/compose/runtime/Composable;", "shouldPause", "composeContent--ZbOJvo$runtime", "(Landroidx/collection/MutableScatterMap;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/ShouldPauseCallback;)V", "compositionData", "getCompositionData", "()Landroidx/compose/runtime/tooling/CompositionData;", "consume", "key", "Landroidx/compose/runtime/CompositionLocal;", "(Landroidx/compose/runtime/CompositionLocal;)Ljava/lang/Object;", "createNode", "factory", "currentCompositionLocalMap", "Landroidx/compose/runtime/CompositionLocalMap;", "getCurrentCompositionLocalMap", "()Landroidx/compose/runtime/CompositionLocalMap;", "currentMarker", "Landroidx/compose/runtime/composer/linkbuffer/GroupAddress;", "getCurrentMarker", "()I", "deactivate", "deactivate$runtime", "deactivateToEndGroup", "dispose", "dispose$runtime", "endDefaults", "endNode", "endProvider", "endProviders", "endReplaceableGroup", "endRestartGroup", "Landroidx/compose/runtime/ScopeUpdateScope;", "endReplaceGroup", "endReusableGroup", "endReuseFromRoot", "endReuseFromRoot$runtime", "endMovableGroup", "endToMarker", "marker", "hasPendingChanges", "getHasPendingChanges$runtime", "insertMovableContent", "Landroidx/compose/runtime/MovableContent;", "parameter", "insertMovableContentReferences", "references", "", "Lkotlin/Pair;", "Landroidx/compose/runtime/MovableContentStateReference;", "joinKey", "left", "right", "parentKey", "parentKey$runtime", "parentStackTrace", "Landroidx/compose/runtime/tooling/ComposeStackTraceFrame;", "parentStackTrace$runtime", "recompose", "recompose-aFTiNEg$runtime", "(Landroidx/collection/MutableScatterMap;Landroidx/compose/runtime/ShouldPauseCallback;)Z", "recordSideEffect", "effect", "rememberedValue", "shouldExecute", "parametersChanged", "flags", "skipCurrentGroup", "skipToGroupEnd", "scheduleFrameEndCallback", "Landroidx/compose/runtime/CancellationHandle;", "action", "sourceInformation", "", "sourceInformationMarkerEnd", "sourceInformationMarkerStart", "stacksSize", "stacksSize$runtime", "stackTraceForValue", "Landroidx/compose/runtime/tooling/ComposeStackTrace;", "stackTraceForValue$runtime", "startDefaults", "startNode", "startProvider", "Landroidx/compose/runtime/ProvidedValue;", "startProviders", "values", "", "([Landroidx/compose/runtime/ProvidedValue;)V", "startReplaceableGroup", "startReplaceGroup", "startRestartGroup", "Landroidx/compose/runtime/Composer;", "startReusableGroup", "dataKey", "startReusableNode", "startReuseFromRoot", "startReuseFromRoot$runtime", "startMovableGroup", "tryImminentInvalidation", "instance", "tryImminentInvalidation$runtime", "updateComposerInvalidations", "updateComposerInvalidations-RY85e9Y$runtime", "(Landroidx/collection/MutableScatterMap;)V", "updateRememberedValue", "useNode", "verifyConsistent", "verifyConsistent$runtime", "nextSlot", "nextSlot$runtime", "nextSlotForCache", "nextSlotForCache$runtime", "updateValue", "abortRoot", "addRecomposeScope", "cleanUpCompose", "clearUpdatedNodeCounts", "currentStackTrace", "doCompose", "doCompose-aFTiNEg", "(Landroidx/collection/MutableScatterMap;Lkotlin/jvm/functions/Function2;)V", "end", "isNode", "endGroup", "endRoot", "changesApplied", "changesApplied$runtime", "enterGroup", "newPending", "executeChangesImmediatelyWithoutApplier", "exitGroup", "expectedNodeCount", "ensureBuilder", "finalizeCompose", "currentCompositionLocalScope", "group", "insertMovableContentGuarded", "recomposeMovableContent", "R", TypedValues.TransitionType.S_FROM, "Landroidx/compose/runtime/ControlledComposition;", TypedValues.TransitionType.S_TO, "address", "(Landroidx/compose/runtime/ControlledComposition;Landroidx/compose/runtime/ControlledComposition;ILjava/util/List;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "invokeMovableContentLambda", "locals", "force", "isGroupAfterCurrentReaderPosition", "Landroidx/compose/runtime/composer/linkbuffer/GroupHandle;", "recomposeToGroupEnd", "recordDelete", "recordInsert", "source", "recordProviderUpdate", "providers", "reportAllMovableContent", "reportFreeMovableContent", "groupBeingRemoved", "resetInsertBuilder", "requireRecomposeScope", "requiresRecomposition", "rGroupIndexOf", "skipGroup", "skipReaderToGroupEnd", "start", "objectKey", "kind", "Landroidx/compose/runtime/composer/GroupKind;", "data", "start-AzEfcrM", "(ILjava/lang/Object;ILjava/lang/Object;)V", "startGroup", "startReaderGroup", "startRoot", "stackTraceForGroup", "dataOffset", "(ILjava/lang/Integer;)Ljava/util/List;", "updateCachedValue", "updateCachedValue$runtime", "updateChildNodeCount", "virtualGroup", "Landroidx/compose/runtime/VirtualGroupHandle;", "count", "updateNodeCountOverrides", "virtualHandle", "newCount", "updateProviderMapGroup", "parentScope", "currentProviders", "updateSlot", "updatedNodeCount", "unwrapRememberObserverHolder", "withReader", "(Landroidx/compose/runtime/composer/linkbuffer/SlotTableReader;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "enterRecomposeScope", "exitRecomposeScope", "Lkotlin/Function1;", "Landroidx/compose/runtime/Composition;", "updateCompositeKeyWhenWeEnterGroup", "groupKey", "updateCompositeKeyWhenWeEnterGroupKeyHash", "updateCompositeKeyWhenWeExitGroup", "updateCompositeKeyWhenWeExitGroupKeyHash", "validateNodeExpected", "validateNodeNotExpected", "CompositionContextImpl", "CompositionContextHolder", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class LinkComposer extends InternalComposer {
    public static final int $stable = 8;
    private CompositionData _compositionData;
    private final Set<RememberObserver> abandonSet;
    private final Applier<?> applier;
    private final CoroutineContext applyCoroutineContext;
    private SlotTableBuilder builder;
    private boolean builderHasAProvider;
    private final ComposerChangeListWriter changeListWriter;
    private Changes changes;
    private int childrenComposing;
    private long compositeKeyHashCode;
    private final CompositionImpl composition;
    private int compositionToken;
    private Changes deferredChanges;
    private final LinkComposer$derivedStateObserver$1 derivedStateObserver;
    private final CompositionErrorContextImpl errorContext;
    private boolean forceRecomposeScopes;
    private boolean forciblyRecompose;
    private int groupNodeCount;
    private FixupList insertFixups;
    private boolean inserting;
    private final ArrayList<RecomposeScopeImpl> invalidateStack;
    private boolean isComposing;
    private boolean isDisposed;
    private int lastPlacedChildGroup;
    private Changes lateChanges;
    private MutableIntIntMap nodeCountOverrides;
    private MutableIntIntMap nodeCountVirtualOverrides;
    private boolean nodeExpected;
    private int nodeIndex;
    private final CompositionObserverHolder observerHolder;
    private final CompositionContext parentContext;
    private LinkPending pending;
    private PersistentCompositionLocalMap providerCache;
    private MutableIntObjectMap<PersistentCompositionLocalMap> providerUpdates;
    private boolean providersInvalid;
    private int rGroupIndex;
    private SlotTableReader reader;
    private boolean reusing;
    private ShouldPauseCallback shouldPauseCallback;
    private final SlotTable slotTable;
    private boolean sourceMarkersEnabled;
    private final MutableScatterMap<Object, Object> invalidations = ScopeMap.m4473constructorimpl$default(null, 1, null);
    private final ArrayList<LinkPending> pendingStack = Stack.m4418constructorimpl$default(null, 1, null);
    private final IntStack parentStateStack = new IntStack();
    private final IntStack entersStack = new IntStack();
    private PersistentCompositionLocalMap rootProvider = PersistentCompositionLocalMapKt.persistentCompositionLocalHashMapOf();
    private final IntStack providersInvalidStack = new IntStack();
    private int reusingGroup = -1;

    public static /* synthetic */ void getApplyCoroutineContext$annotations() {
    }

    public static /* synthetic */ void getCompositeKeyHashCode$annotations() {
    }

    /* JADX WARN: Type inference failed for: r2v11, types: [androidx.compose.runtime.LinkComposer$derivedStateObserver$1] */
    public LinkComposer(Applier<?> applier, CompositionContext parentContext, Set<RememberObserver> set, SlotTable slotTable, Changes changes, Changes lateChanges, CompositionObserverHolder observerHolder, CompositionImpl composition) {
        this.applier = applier;
        this.parentContext = parentContext;
        this.abandonSet = set;
        this.slotTable = slotTable;
        this.changes = changes;
        this.lateChanges = lateChanges;
        this.observerHolder = observerHolder;
        this.composition = composition;
        SlotTableReader it = this.slotTable.openReader();
        it.close();
        this.reader = it;
        SlotTableBuilder it2 = new SlotTableBuilder(this.slotTable.getAddressSpace(), false, false);
        it2.close();
        this.builder = it2;
        this.changeListWriter = new ComposerChangeListWriter(this, ChangeListKt.asLinkBufferChangeList(this.changes));
        this.lastPlacedChildGroup = -1;
        this.insertFixups = new FixupList();
        this.sourceMarkersEnabled = this.parentContext.getCollectingSourceInformation() || this.parentContext.getCollectingCallByInformation$runtime();
        this.derivedStateObserver = new DerivedStateObserver() { // from class: androidx.compose.runtime.LinkComposer$derivedStateObserver$1
            @Override // androidx.compose.runtime.DerivedStateObserver
            public void start(DerivedState<?> derivedState) {
                this.this$0.childrenComposing++;
            }

            @Override // androidx.compose.runtime.DerivedStateObserver
            public void done(DerivedState<?> derivedState) {
                this.this$0.childrenComposing--;
            }
        };
        this.invalidateStack = Stack.m4418constructorimpl$default(null, 1, null);
        this.errorContext = new CompositionErrorContextImpl(this);
        CoroutineContext effectCoroutineContext = this.parentContext.getEffectCoroutineContext();
        CoroutineContext errorContext$runtime = getErrorContext$runtime();
        this.applyCoroutineContext = effectCoroutineContext.plus(errorContext$runtime == null ? EmptyCoroutineContext.INSTANCE : errorContext$runtime);
    }

    @Override // androidx.compose.runtime.Composer
    public Applier<?> getApplier() {
        return this.applier;
    }

    @Override // androidx.compose.runtime.Composer
    public CompositionImpl getComposition() {
        return this.composition;
    }

    /* JADX INFO: renamed from: getReader$runtime, reason: from getter */
    public final SlotTableReader getReader() {
        return this.reader;
    }

    public final void setReader$runtime(SlotTableReader slotTableReader) {
        this.reader = slotTableReader;
    }

    public final SlotTable getInsertTable$runtime() {
        return this.builder.getTable();
    }

    public final SlotTable getReaderTable$runtime() {
        return this.reader.getTable();
    }

    @Override // androidx.compose.runtime.InternalComposer
    /* JADX INFO: renamed from: getSourceMarkersEnabled$runtime, reason: from getter */
    public boolean getSourceMarkersEnabled() {
        return this.sourceMarkersEnabled;
    }

    public void setSourceMarkersEnabled$runtime(boolean z) {
        this.sourceMarkersEnabled = z;
    }

    @Override // androidx.compose.runtime.InternalComposer
    /* JADX INFO: renamed from: isComposing$runtime, reason: from getter */
    public boolean getIsComposing() {
        return this.isComposing;
    }

    /* JADX INFO: renamed from: isDisposed$runtime, reason: from getter */
    public final boolean getIsDisposed() {
        return this.isDisposed;
    }

    @Override // androidx.compose.runtime.InternalComposer
    public boolean getAreChildrenComposing$runtime() {
        return this.childrenComposing > 0;
    }

    @Override // androidx.compose.runtime.InternalComposer
    public RecomposeScopeImpl getCurrentRecomposeScope$runtime() {
        ArrayList<RecomposeScopeImpl> arrayList = this.invalidateStack;
        if (this.childrenComposing == 0 && Stack.m4424isNotEmptyimpl(arrayList)) {
            return (RecomposeScopeImpl) Stack.m4425peekimpl(arrayList);
        }
        return null;
    }

    @Override // androidx.compose.runtime.Composer
    public long getCompositeKeyHashCode() {
        return this.compositeKeyHashCode;
    }

    @Override // androidx.compose.runtime.Composer
    public boolean getDefaultsInvalid() {
        if (!getSkipping() || this.providersInvalid) {
            return true;
        }
        RecomposeScopeImpl currentRecomposeScope$runtime = getCurrentRecomposeScope$runtime();
        return currentRecomposeScope$runtime != null && currentRecomposeScope$runtime.getDefaultsInvalid();
    }

    @Override // androidx.compose.runtime.Composer
    public void disableReusing() {
        this.reusing = false;
    }

    @Override // androidx.compose.runtime.Composer
    public void disableSourceInformation() {
        setSourceMarkersEnabled$runtime(false);
    }

    @Override // androidx.compose.runtime.Composer
    public void enableReusing() {
        this.reusing = this.reusingGroup >= 0;
    }

    @Override // androidx.compose.runtime.InternalComposer
    /* JADX INFO: renamed from: getDeferredChanges$runtime, reason: from getter */
    public Changes getDeferredChanges() {
        return this.deferredChanges;
    }

    public void setDeferredChanges$runtime(Changes changes) {
        this.deferredChanges = changes;
    }

    @Override // androidx.compose.runtime.InternalComposer
    public CompositionErrorContextImpl getErrorContext$runtime() {
        if (getSourceMarkersEnabled()) {
            return this.errorContext;
        }
        return null;
    }

    @Override // androidx.compose.runtime.InternalComposer
    public boolean forceRecomposeScopes$runtime() {
        if (!this.forceRecomposeScopes) {
            this.forceRecomposeScopes = true;
            this.forciblyRecompose = true;
            return true;
        }
        return false;
    }

    @Override // androidx.compose.runtime.Composer
    public boolean getInserting() {
        return this.inserting;
    }

    @Override // androidx.compose.runtime.InternalComposer
    public void prepareCompose$runtime(Function0<Unit> block) {
        boolean value$iv = !getIsComposing();
        if (!value$iv) {
            ComposerKt.composeImmediateRuntimeError("Preparing a composition while composing is not supported");
        }
        this.isComposing = true;
        try {
            block.invoke();
        } finally {
            this.isComposing = false;
        }
    }

    @Override // androidx.compose.runtime.Composer
    public RecomposeScope getRecomposeScope() {
        return getCurrentRecomposeScope$runtime();
    }

    @Override // androidx.compose.runtime.Composer
    public Object getRecomposeScopeIdentity() {
        RecomposeScopeImpl currentRecomposeScope$runtime = getCurrentRecomposeScope$runtime();
        if (currentRecomposeScope$runtime != null) {
            return currentRecomposeScope$runtime.getAnchor();
        }
        return null;
    }

    @Override // androidx.compose.runtime.Composer
    public void recordUsed(RecomposeScope scope) {
        RecomposeScopeImpl recomposeScopeImpl = scope instanceof RecomposeScopeImpl ? (RecomposeScopeImpl) scope : null;
        if (recomposeScopeImpl != null) {
            recomposeScopeImpl.setUsed(true);
        }
    }

    @Override // androidx.compose.runtime.Composer
    public boolean getSkipping() {
        if (getInserting() || this.reusing || this.providersInvalid) {
            return false;
        }
        RecomposeScopeImpl currentRecomposeScope$runtime = getCurrentRecomposeScope$runtime();
        return (currentRecomposeScope$runtime != null && !currentRecomposeScope$runtime.getRequiresRecompose()) && !this.forciblyRecompose;
    }

    @Override // androidx.compose.runtime.Composer
    public <V, T> void apply(V value, Function2<? super T, ? super V, Unit> block) {
        if (getInserting()) {
            this.insertFixups.updateNode(value, block);
        } else {
            this.changeListWriter.updateNode(value, block);
        }
    }

    @Override // androidx.compose.runtime.Composer
    public CoroutineContext getApplyCoroutineContext() {
        return this.applyCoroutineContext;
    }

    @Override // androidx.compose.runtime.Composer
    public CompositionContext buildContext() {
        startGroup(ComposerKt.referenceKey, ComposerKt.getReference());
        if (getInserting()) {
            this.builder.addFlags(1073741824);
        }
        Object objNextSlot$runtime = nextSlot$runtime();
        ReusableRememberObserverHolder observerHolder = objNextSlot$runtime instanceof ReusableRememberObserverHolder ? (ReusableRememberObserverHolder) objNextSlot$runtime : null;
        if (observerHolder == null) {
            observerHolder = new ReusableLinkRememberObserverHolder(new CompositionContextHolder(new CompositionContextImpl(getCompositeKeyHashCode(), this.forceRecomposeScopes, getSourceMarkersEnabled(), getComposition().getObserverHolder())), LinkAnchorKt.getNullAnchor());
            updateValue(observerHolder);
        }
        RememberObserver wrapped = observerHolder.getWrapped();
        Intrinsics.checkNotNull(wrapped, "null cannot be cast to non-null type androidx.compose.runtime.LinkComposer.CompositionContextHolder");
        CompositionContextHolder holder = (CompositionContextHolder) wrapped;
        holder.getRef().updateCompositionLocalScope(currentCompositionLocalScope());
        endGroup();
        return holder.getRef();
    }

    @Override // androidx.compose.runtime.Composer
    public boolean changed(Object value) {
        if (!Intrinsics.areEqual(nextSlot$runtime(), value)) {
            updateValue(value);
            return true;
        }
        return false;
    }

    @Override // androidx.compose.runtime.Composer
    public boolean changedInstance(Object value) {
        if (nextSlot$runtime() != value) {
            updateValue(value);
            return true;
        }
        return false;
    }

    @Override // androidx.compose.runtime.Composer
    public boolean changed(char value) {
        Object next = nextSlot$runtime();
        if (next instanceof Character) {
            char nextPrimitive = ((Character) next).charValue();
            if (value == nextPrimitive) {
                return false;
            }
        }
        updateValue(Character.valueOf(value));
        return true;
    }

    @Override // androidx.compose.runtime.Composer
    public boolean changed(byte value) {
        Object next = nextSlot$runtime();
        if (next instanceof Byte) {
            byte nextPrimitive = ((Number) next).byteValue();
            if (value == nextPrimitive) {
                return false;
            }
        }
        updateValue(Byte.valueOf(value));
        return true;
    }

    @Override // androidx.compose.runtime.Composer
    public boolean changed(short value) {
        Object next = nextSlot$runtime();
        if (next instanceof Short) {
            short nextPrimitive = ((Number) next).shortValue();
            if (value == nextPrimitive) {
                return false;
            }
        }
        updateValue(Short.valueOf(value));
        return true;
    }

    @Override // androidx.compose.runtime.Composer
    public boolean changed(boolean value) {
        Object next = nextSlot$runtime();
        if (next instanceof Boolean) {
            boolean nextPrimitive = ((Boolean) next).booleanValue();
            if (value == nextPrimitive) {
                return false;
            }
        }
        updateValue(Boolean.valueOf(value));
        return true;
    }

    @Override // androidx.compose.runtime.Composer
    public boolean changed(float value) {
        Object next = nextSlot$runtime();
        if ((next instanceof Float) && Intrinsics.areEqual(value, (Float) next)) {
            return false;
        }
        updateValue(Float.valueOf(value));
        return true;
    }

    @Override // androidx.compose.runtime.Composer
    public boolean changed(long value) {
        Object next = nextSlot$runtime();
        if ((next instanceof Long) && (next instanceof Long) && value == ((Number) next).longValue()) {
            return false;
        }
        updateValue(Long.valueOf(value));
        return true;
    }

    @Override // androidx.compose.runtime.Composer
    public boolean changed(double value) {
        Object next = nextSlot$runtime();
        if ((next instanceof Double) && Intrinsics.areEqual(value, (Double) next)) {
            return false;
        }
        updateValue(Double.valueOf(value));
        return true;
    }

    @Override // androidx.compose.runtime.Composer
    public boolean changed(int value) {
        Object next = nextSlot$runtime();
        if ((next instanceof Integer) && (next instanceof Integer) && value == ((Number) next).intValue()) {
            return false;
        }
        updateValue(Integer.valueOf(value));
        return true;
    }

    @Override // androidx.compose.runtime.Composer
    public void collectParameterInformation() {
        this.forceRecomposeScopes = true;
        setSourceMarkersEnabled$runtime(true);
        this.slotTable.collectSourceInformation();
        this.builder.collectSourceInformation();
    }

    @Override // androidx.compose.runtime.InternalComposer
    /* JADX INFO: renamed from: composeContent--ZbOJvo$runtime */
    public void mo4394composeContentZbOJvo$runtime(MutableScatterMap<Object, Object> invalidationsRequested, Function2<? super Composer, ? super Integer, Unit> content, ShouldPauseCallback shouldPause) {
        boolean value$iv = this.changes.isEmpty();
        if (!value$iv) {
            ComposerKt.composeImmediateRuntimeError("Expected applyChanges() to have been called");
        }
        this.shouldPauseCallback = shouldPause;
        try {
            m4401doComposeaFTiNEg(invalidationsRequested, content);
        } finally {
            this.shouldPauseCallback = null;
        }
    }

    @Override // androidx.compose.runtime.Composer
    public CompositionData getCompositionData() {
        CompositionData data = this._compositionData;
        if (data == null) {
            LinkCompositionDataImpl newData = new LinkCompositionDataImpl(getComposition());
            this._compositionData = newData;
            return newData;
        }
        return data;
    }

    @Override // androidx.compose.runtime.Composer
    public <T> T consume(CompositionLocal<T> key) {
        return (T) CompositionLocalMapKt.read(currentCompositionLocalScope(), key);
    }

    @Override // androidx.compose.runtime.Composer
    public <T> void createNode(Function0<? extends T> factory) {
        validateNodeExpected();
        boolean value$iv = getInserting();
        if (!value$iv) {
            ComposerKt.composeImmediateRuntimeError("createNode() can only be called when inserting");
        }
        int insertIndex = this.parentStateStack.peek();
        this.groupNodeCount++;
        long handle = this.builder.getParentHandle();
        if (this.changeListWriter.isInAnchorMode()) {
            LinkAnchor anchor = this.builder.getTable().getAddressSpace().anchorOfAddress(GroupHandleKt.getGroup(handle));
            this.insertFixups.createAndInsertNodeByAnchor(factory, insertIndex, anchor);
        } else {
            this.insertFixups.createAndInsertNode(factory, insertIndex, this.builder.getParentHandle());
        }
    }

    @Override // androidx.compose.runtime.Composer
    public CompositionLocalMap getCurrentCompositionLocalMap() {
        return currentCompositionLocalScope();
    }

    @Override // androidx.compose.runtime.Composer
    public int getCurrentMarker() {
        return getInserting() ? -this.builder.getParent() : this.reader.getParent();
    }

    @Override // androidx.compose.runtime.InternalComposer
    public void deactivate$runtime() {
        Stack.m4416clearimpl(this.invalidateStack);
        ScopeMap.m4471clearimpl(this.invalidations);
        this.changes.clear();
        this.providerUpdates = null;
    }

    @Override // androidx.compose.runtime.Composer
    public void deactivateToEndGroup(boolean changed) {
        boolean value$iv = this.groupNodeCount == 0;
        if (!value$iv) {
            ComposerKt.composeImmediateRuntimeError("No nodes can be emitted before calling deactivateToEndGroup");
        }
        boolean value$iv2 = getInserting();
        if (!value$iv2) {
            if (!changed) {
                skipReaderToGroupEnd();
            } else {
                this.changeListWriter.deactivateCurrentGroup();
                this.reader.skipToGroupEnd();
            }
        }
    }

    @Override // androidx.compose.runtime.InternalComposer
    public void dispose$runtime() {
        this.slotTable.dispose();
        this.parentContext.unregisterComposer$runtime(this);
        deactivate$runtime();
        getApplier().clear();
        this.isDisposed = true;
    }

    @Override // androidx.compose.runtime.Composer
    public void endDefaults() {
        endGroup();
        RecomposeScopeImpl scope = getCurrentRecomposeScope$runtime();
        if (scope != null && scope.getUsed()) {
            scope.setDefaultsInScope(true);
        }
    }

    @Override // androidx.compose.runtime.Composer
    public void endNode() {
        end(true);
    }

    @Override // androidx.compose.runtime.Composer
    public void endProvider() {
        endGroup();
        endGroup();
        this.providersInvalid = LinkComposerKt.asBool(this.providersInvalidStack.pop());
        this.providerCache = null;
    }

    @Override // androidx.compose.runtime.Composer
    public void endProviders() {
        endGroup();
        endGroup();
        this.providersInvalid = LinkComposerKt.asBool(this.providersInvalidStack.pop());
        this.providerCache = null;
    }

    @Override // androidx.compose.runtime.Composer
    public void endReplaceableGroup() {
        endGroup();
    }

    @Override // androidx.compose.runtime.Composer
    public ScopeUpdateScope endRestartGroup() {
        LinkAnchor parentAnchor;
        RecomposeScopeImpl result = null;
        RecomposeScopeImpl scope = Stack.m4424isNotEmptyimpl(this.invalidateStack) ? (RecomposeScopeImpl) Stack.m4427popimpl(this.invalidateStack) : null;
        if (scope != null) {
            scope.setRequiresRecompose(false);
            Function1<Composition, Unit> function1ExitRecomposeScope = exitRecomposeScope(scope);
            if (function1ExitRecomposeScope != null) {
                this.changeListWriter.endCompositionScope(function1ExitRecomposeScope, getComposition());
            }
            if (scope.getResuming()) {
                scope.setResuming(false);
                this.changeListWriter.endResumingScope(scope);
                scope.setReusing(false);
                if (scope.getResetReusing() && this.reusingGroup == this.reader.getParent()) {
                    scope.setResetReusing(false);
                    this.reusingGroup = -1;
                    this.reusing = false;
                }
            }
        }
        if (scope != null && !scope.getSkipped$runtime() && (scope.getUsed() || this.forceRecomposeScopes)) {
            if (scope.getAnchor() == null) {
                if (getInserting()) {
                    parentAnchor = this.builder.getParentAnchor();
                } else {
                    parentAnchor = this.reader.getParentAnchor();
                }
                scope.setAnchor(parentAnchor);
            }
            scope.setDefaultsInvalid(false);
            result = scope;
        }
        end(false);
        return result;
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public void endReplaceGroup() {
        endGroup();
    }

    @Override // androidx.compose.runtime.Composer
    public void endReusableGroup() {
        if (this.reusing && this.reader.getParent() == this.reusingGroup) {
            this.reusingGroup = -1;
            this.reusing = false;
        }
        end(false);
    }

    @Override // androidx.compose.runtime.InternalComposer
    public void endReuseFromRoot$runtime() {
        int it = this.reusingGroup;
        int reusingGroupKey = it < 0 ? 100 : this.reader.groupKey(it);
        boolean value$iv = !getIsComposing() && reusingGroupKey == 100;
        if (!value$iv) {
            PreconditionsKt.throwIllegalArgumentException("Cannot disable reuse from root if it was caused by other groups");
        }
        this.reusingGroup = -1;
        this.reusing = false;
    }

    @Override // androidx.compose.runtime.Composer
    public void endMovableGroup() {
        endGroup();
    }

    @Override // androidx.compose.runtime.Composer
    public void endToMarker(int marker) {
        if (marker < 0) {
            int writerLocation = -marker;
            SlotTableBuilder builder = this.builder;
            MutableIntSet targetParents = new MutableIntSet(0, 1, null);
            SlotTable this_$iv = getReaderTable$runtime();
            SlotTableAddressSpace $this$iv$iv = this_$iv.getAddressSpace();
            int[] groups$iv$iv = $this$iv$iv.getGroups();
            int current$iv$iv = writerLocation;
            while (current$iv$iv > 0) {
                int parent = current$iv$iv;
                targetParents.add(parent);
                int address$iv$iv$iv = current$iv$iv;
                current$iv$iv = groups$iv$iv[address$iv$iv$iv + 2];
            }
            boolean value$iv$iv$iv = current$iv$iv != 0;
            if (!value$iv$iv$iv) {
                ComposerKt.composeImmediateRuntimeError("Traversing parent of group not in the slot table: " + writerLocation);
            }
            while (!targetParents.contains(builder.getParent())) {
                end(builder.isNode());
            }
            return;
        }
        if (getInserting()) {
            SlotTableBuilder builder2 = this.builder;
            while (getInserting()) {
                end(builder2.isNode());
            }
        }
        MutableIntSet markerParents = new MutableIntSet(0, 1, null);
        SlotTable this_$iv2 = getReaderTable$runtime();
        SlotTableAddressSpace $this$iv$iv2 = this_$iv2.getAddressSpace();
        int[] groups$iv$iv2 = $this$iv$iv2.getGroups();
        int current$iv$iv2 = marker;
        while (current$iv$iv2 > 0) {
            int parent2 = current$iv$iv2;
            markerParents.add(parent2);
            int address$iv$iv$iv2 = current$iv$iv2;
            current$iv$iv2 = groups$iv$iv2[address$iv$iv$iv2 + 2];
        }
        boolean value$iv$iv$iv2 = current$iv$iv2 != 0;
        if (!value$iv$iv$iv2) {
            ComposerKt.composeImmediateRuntimeError("Traversing parent of group not in the slot table: " + marker);
        }
        SlotTableReader reader = this.reader;
        for (int parent3 = reader.getParent(); !markerParents.contains(parent3); parent3 = reader.getParent()) {
            int $this$contains$iv = reader.flagsOf(parent3);
            end((8388608 & $this$contains$iv) == 8388608);
        }
    }

    @Override // androidx.compose.runtime.InternalComposer
    public boolean getHasPendingChanges$runtime() {
        return this.changes.isNotEmpty();
    }

    @Override // androidx.compose.runtime.Composer
    public void insertMovableContent(MovableContent<?> value, Object parameter) {
        Intrinsics.checkNotNull(value, "null cannot be cast to non-null type androidx.compose.runtime.MovableContent<kotlin.Any?>");
        invokeMovableContentLambda(value, currentCompositionLocalScope(), parameter, false);
    }

    @Override // androidx.compose.runtime.Composer
    public void insertMovableContentReferences(List<Pair<MovableContentStateReference, MovableContentStateReference>> references) {
        try {
            insertMovableContentGuarded(references);
            cleanUpCompose();
        } catch (Throwable th) {
            abortRoot();
            throw th;
        }
    }

    @Override // androidx.compose.runtime.Composer
    public Object joinKey(Object left, Object right) {
        Object key = LinkComposerKt.getKey(getInserting() ? null : this.reader.getGroupObjectKey(), left, right);
        return key == null ? new JoinedKey(left, right) : key;
    }

    @Override // androidx.compose.runtime.InternalComposer
    public int parentKey$runtime() {
        if (getInserting()) {
            return this.builder.groupKey(this.builder.getParent());
        }
        return this.reader.groupKey(this.reader.getParent());
    }

    @Override // androidx.compose.runtime.InternalComposer
    public List<ComposeStackTraceFrame> parentStackTrace$runtime() throws Throwable {
        Composition composition$runtime = this.parentContext.getComposition$runtime();
        CompositionImpl parentComposition = composition$runtime instanceof CompositionImpl ? (CompositionImpl) composition$runtime : null;
        if (parentComposition == null) {
            return CollectionsKt.emptyList();
        }
        Integer position = LinkComposerKt.findSubcompositionContextGroup(SlotTableKt.asLinkBufferSlotTable(parentComposition.getSlotStorage()), this.parentContext);
        if (position != null) {
            SlotTable this_$iv = SlotTableKt.asLinkBufferSlotTable(parentComposition.getSlotStorage());
            SlotTableReader $this$read_u24lambda_u240$iv = this_$iv.openReader();
            try {
                List<ComposeStackTraceFrame> listTraceForGroup = SlotTableReaderKt.traceForGroup($this$read_u24lambda_u240$iv, position.intValue(), 0);
                $this$read_u24lambda_u240$iv.close();
                return CollectionsKt.plus((Collection) listTraceForGroup, (Iterable) parentComposition.getComposer().parentStackTrace$runtime());
            } catch (Throwable th) {
                $this$read_u24lambda_u240$iv.close();
                throw th;
            }
        }
        return CollectionsKt.emptyList();
    }

    @Override // androidx.compose.runtime.InternalComposer
    /* JADX INFO: renamed from: recompose-aFTiNEg$runtime */
    public boolean mo4395recomposeaFTiNEg$runtime(MutableScatterMap<Object, Object> invalidationsRequested, ShouldPauseCallback shouldPause) {
        boolean value$iv = this.changes.isEmpty();
        if (!value$iv) {
            ComposerKt.composeImmediateRuntimeError("Expected applyChanges() to have been called");
        }
        if (ScopeMap.m4481getSizeimpl(invalidationsRequested) > 0 || ScopeMap.m4484isNotEmptyimpl(this.invalidations) || ((this.slotTable.getRoot() >= 0 && requiresRecomposition(this.slotTable.getRoot())) || this.forciblyRecompose)) {
            this.shouldPauseCallback = shouldPause;
            try {
                this.changeListWriter.startComposition();
                m4401doComposeaFTiNEg(invalidationsRequested, null);
                this.shouldPauseCallback = null;
                if (ChangeListKt.asLinkBufferChangeList(this.changes).hasChangesRequiringApplication()) {
                    return true;
                }
                if (this.changes.isNotEmpty()) {
                    executeChangesImmediatelyWithoutApplier();
                    return false;
                }
                return false;
            } catch (Throwable th) {
                this.shouldPauseCallback = null;
                throw th;
            }
        }
        return false;
    }

    @Override // androidx.compose.runtime.Composer
    public void recordSideEffect(Function0<Unit> effect) {
        this.changeListWriter.sideEffect(effect);
    }

    @Override // androidx.compose.runtime.Composer
    public Object rememberedValue() {
        return unwrapRememberObserverHolder(nextSlotForCache$runtime());
    }

    @Override // androidx.compose.runtime.Composer
    public boolean shouldExecute(boolean parametersChanged, int flags) {
        RecomposeScopeImpl scope;
        if ((flags & 1) != 0 || (!getInserting() && !this.reusing)) {
            return parametersChanged || !getSkipping();
        }
        ShouldPauseCallback callback = this.shouldPauseCallback;
        if (callback == null || (scope = getCurrentRecomposeScope$runtime()) == null) {
            return true;
        }
        boolean pausing = callback.shouldPause();
        if (!pausing || scope.getResuming()) {
            return true;
        }
        scope.setUsed(true);
        scope.setReusing(this.reusing);
        scope.setPaused(true);
        this.changeListWriter.rememberPausingScope(scope);
        this.parentContext.reportPausedScope$runtime(scope);
        return false;
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public void skipCurrentGroup() throws Throwable {
        SlotTableReader reader;
        int key;
        Object dataKey;
        Object aux;
        int rGroupIndex;
        if (!requiresRecomposition(this.reader.getCurrentGroup())) {
            skipGroup();
            return;
        }
        SlotTableReader reader2 = this.reader;
        int key2 = reader2.getGroupKey();
        Object dataKey2 = reader2.getGroupObjectKey();
        Object aux2 = reader2.getGroupAux();
        int rGroupIndex2 = this.rGroupIndex;
        if (dataKey2 != null) {
            reader = reader2;
            key = key2;
            dataKey = dataKey2;
            aux = aux2;
            rGroupIndex = rGroupIndex2;
            if (dataKey2 instanceof Enum) {
                int groupKey$iv$iv = ((Enum) dataKey2).ordinal();
                long $this$compoundWith$iv$iv$iv = getCompositeKeyHashCode();
                long $this$rol$iv$iv$iv$iv = groupKey$iv$iv;
                long $this$rol$iv$iv$iv$iv2 = 0;
                this.compositeKeyHashCode = Long.rotateLeft(Long.rotateLeft($this$compoundWith$iv$iv$iv, 3) ^ $this$rol$iv$iv$iv$iv, 3) ^ $this$rol$iv$iv$iv$iv2;
            } else {
                int groupKey$iv$iv2 = dataKey2.hashCode();
                long $this$compoundWith$iv$iv$iv2 = getCompositeKeyHashCode();
                long $this$rol$iv$iv$iv$iv3 = groupKey$iv$iv2;
                long $this$rol$iv$iv$iv$iv4 = 0;
                this.compositeKeyHashCode = Long.rotateLeft(Long.rotateLeft($this$compoundWith$iv$iv$iv2, 3) ^ $this$rol$iv$iv$iv$iv3, 3) ^ $this$rol$iv$iv$iv$iv4;
            }
        } else if (aux2 == null || key2 != 207 || Intrinsics.areEqual(aux2, Composer.INSTANCE.getEmpty())) {
            reader = reader2;
            key = key2;
            dataKey = dataKey2;
            long $this$compoundWith$iv$iv$iv3 = getCompositeKeyHashCode();
            aux = aux2;
            rGroupIndex = rGroupIndex2;
            long $this$rol$iv$iv$iv$iv5 = rGroupIndex2;
            this.compositeKeyHashCode = Long.rotateLeft(Long.rotateLeft($this$compoundWith$iv$iv$iv3, 3) ^ ((long) key2), 3) ^ $this$rol$iv$iv$iv$iv5;
        } else {
            int groupKey$iv$iv3 = aux2.hashCode();
            long $this$compoundWith$iv$iv$iv4 = getCompositeKeyHashCode();
            reader = reader2;
            key = key2;
            dataKey = dataKey2;
            long $this$rol$iv$iv$iv$iv6 = Long.rotateLeft(Long.rotateLeft($this$compoundWith$iv$iv$iv4, 3) ^ ((long) groupKey$iv$iv3), 3);
            this.compositeKeyHashCode = $this$rol$iv$iv$iv$iv6 ^ ((long) rGroupIndex2);
            aux = aux2;
            rGroupIndex = rGroupIndex2;
        }
        startReaderGroup(reader.isNode(), null);
        recomposeToGroupEnd();
        reader.endGroup();
        Object data$iv = aux;
        int rGroupIndex$iv = rGroupIndex;
        Object dataKey$iv = dataKey;
        int groupKey$iv = key;
        if (dataKey$iv != null) {
            if (dataKey$iv instanceof Enum) {
                int groupKey$iv$iv4 = ((Enum) dataKey$iv).ordinal();
                long $this$unCompoundWith$iv$iv$iv = getCompositeKeyHashCode();
                long $this$ror$iv$iv$iv$iv = ((long) 0) ^ $this$unCompoundWith$iv$iv$iv;
                long $this$unCompoundWith$iv$iv$iv2 = Long.rotateRight($this$ror$iv$iv$iv$iv, 3);
                long $this$ror$iv$iv$iv$iv2 = $this$unCompoundWith$iv$iv$iv2 ^ ((long) groupKey$iv$iv4);
                this.compositeKeyHashCode = Long.rotateRight($this$ror$iv$iv$iv$iv2, 3);
                return;
            }
            int groupKey$iv$iv5 = dataKey$iv.hashCode();
            long $this$unCompoundWith$iv$iv$iv3 = getCompositeKeyHashCode();
            long $this$ror$iv$iv$iv$iv3 = ((long) 0) ^ $this$unCompoundWith$iv$iv$iv3;
            long $this$unCompoundWith$iv$iv$iv4 = Long.rotateRight($this$ror$iv$iv$iv$iv3, 3);
            long $this$ror$iv$iv$iv$iv4 = $this$unCompoundWith$iv$iv$iv4 ^ ((long) groupKey$iv$iv5);
            this.compositeKeyHashCode = Long.rotateRight($this$ror$iv$iv$iv$iv4, 3);
            return;
        }
        if (data$iv == null || groupKey$iv != 207 || Intrinsics.areEqual(data$iv, Composer.INSTANCE.getEmpty())) {
            long $this$unCompoundWith$iv$iv$iv5 = getCompositeKeyHashCode();
            long $this$ror$iv$iv$iv$iv5 = ((long) rGroupIndex$iv) ^ $this$unCompoundWith$iv$iv$iv5;
            long $this$unCompoundWith$iv$iv$iv6 = Long.rotateRight($this$ror$iv$iv$iv$iv5, 3);
            long $this$ror$iv$iv$iv$iv6 = $this$unCompoundWith$iv$iv$iv6 ^ ((long) groupKey$iv);
            this.compositeKeyHashCode = Long.rotateRight($this$ror$iv$iv$iv$iv6, 3);
            return;
        }
        int groupKey$iv$iv6 = data$iv.hashCode();
        long $this$unCompoundWith$iv$iv$iv7 = getCompositeKeyHashCode();
        long $this$ror$iv$iv$iv$iv7 = ((long) rGroupIndex$iv) ^ $this$unCompoundWith$iv$iv$iv7;
        long $this$unCompoundWith$iv$iv$iv8 = Long.rotateRight($this$ror$iv$iv$iv$iv7, 3);
        long $this$ror$iv$iv$iv$iv8 = $this$unCompoundWith$iv$iv$iv8 ^ ((long) groupKey$iv$iv6);
        this.compositeKeyHashCode = Long.rotateRight($this$ror$iv$iv$iv$iv8, 3);
    }

    @Override // androidx.compose.runtime.Composer
    public void skipToGroupEnd() throws Throwable {
        boolean value$iv = this.groupNodeCount == 0;
        if (!value$iv) {
            ComposerKt.composeImmediateRuntimeError("No nodes can be emitted before calling skipAndEndGroup");
        }
        boolean value$iv2 = getInserting();
        if (!value$iv2) {
            RecomposeScopeImpl currentRecomposeScope$runtime = getCurrentRecomposeScope$runtime();
            if (currentRecomposeScope$runtime != null) {
                currentRecomposeScope$runtime.scopeSkipped();
            }
            if (this.reader.getCurrentGroup() < 0 || !requiresRecomposition(this.reader.getParent())) {
                skipReaderToGroupEnd();
            } else {
                recomposeToGroupEnd();
            }
        }
    }

    @Override // androidx.compose.runtime.Composer
    public CancellationHandle scheduleFrameEndCallback(Function0<Unit> action) {
        return this.parentContext.scheduleFrameEndCallback(action);
    }

    @Override // androidx.compose.runtime.Composer
    public void sourceInformation(String sourceInformation) {
        if (getInserting() && getSourceMarkersEnabled()) {
            this.builder.recordGroupSourceInformation(sourceInformation);
        }
    }

    @Override // androidx.compose.runtime.Composer
    public void sourceInformationMarkerEnd() {
        if (getInserting() && getSourceMarkersEnabled()) {
            this.builder.recordGrouplessCallSourceInformationEnd();
        }
    }

    @Override // androidx.compose.runtime.Composer
    public void sourceInformationMarkerStart(int key, String sourceInformation) {
        if (getInserting() && getSourceMarkersEnabled()) {
            this.builder.recordGrouplessCallSourceInformationStart(key, sourceInformation);
        }
    }

    @Override // androidx.compose.runtime.InternalComposer
    public int stacksSize$runtime() {
        IntStack this_$iv = this.entersStack;
        int iM4421getSizeimpl = this_$iv.tos + Stack.m4421getSizeimpl(this.invalidateStack);
        IntStack this_$iv2 = this.providersInvalidStack;
        int iM4421getSizeimpl2 = iM4421getSizeimpl + this_$iv2.tos + Stack.m4421getSizeimpl(this.pendingStack);
        IntStack this_$iv3 = this.parentStateStack;
        return iM4421getSizeimpl2 + this_$iv3.tos;
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x003d  */
    @Override // androidx.compose.runtime.InternalComposer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public androidx.compose.runtime.tooling.ComposeStackTrace stackTraceForValue$runtime(final java.lang.Object r6) throws java.lang.Throwable {
        /*
            r5 = this;
            boolean r0 = r5.getSourceMarkersEnabled()
            if (r0 != 0) goto L11
            androidx.compose.runtime.tooling.ComposeStackTrace r0 = new androidx.compose.runtime.tooling.ComposeStackTrace
            java.util.List r1 = kotlin.collections.CollectionsKt.emptyList()
            r2 = 0
            r0.<init>(r1, r2)
            return r0
        L11:
            androidx.compose.runtime.composer.linkbuffer.SlotTable r0 = r5.slotTable
            androidx.compose.runtime.LinkComposer$$ExternalSyntheticLambda3 r1 = new androidx.compose.runtime.LinkComposer$$ExternalSyntheticLambda3
            r1.<init>()
            androidx.compose.runtime.tooling.ObjectLocation r0 = androidx.compose.runtime.composer.linkbuffer.SlotTableKt.findLocation(r0, r1)
            if (r0 == 0) goto L3d
        L20:
            r1 = 0
            int r2 = r0.getGroup()
            java.lang.Integer r0 = r0.getDataOffset()
            java.util.List r3 = r5.stackTraceForGroup(r2, r0)
            java.util.Collection r3 = (java.util.Collection) r3
            java.util.List r4 = r5.parentStackTrace$runtime()
            java.lang.Iterable r4 = (java.lang.Iterable) r4
            java.util.List r0 = kotlin.collections.CollectionsKt.plus(r3, r4)
            if (r0 == 0) goto L3d
            goto L41
        L3d:
            java.util.List r0 = kotlin.collections.CollectionsKt.emptyList()
        L41:
            boolean r1 = r5.getSourceMarkersEnabled()
            androidx.compose.runtime.tooling.ComposeStackTrace r2 = new androidx.compose.runtime.tooling.ComposeStackTrace
            r2.<init>(r0, r1)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.LinkComposer.stackTraceForValue$runtime(java.lang.Object):androidx.compose.runtime.tooling.ComposeStackTrace");
    }

    static final boolean stackTraceForValue$lambda$0(Object $value, Object it) {
        if (it != $value) {
            RememberObserverHolder rememberObserverHolder = it instanceof RememberObserverHolder ? (RememberObserverHolder) it : null;
            if ((rememberObserverHolder != null ? rememberObserverHolder.getWrapped() : null) != $value) {
                return false;
            }
        }
        return true;
    }

    @Override // androidx.compose.runtime.Composer
    public void startDefaults() {
        m4402startAzEfcrM(ComposerKt.defaultsKey, null, GroupKind.INSTANCE.m4502getGroup9udXigM(), null);
    }

    @Override // androidx.compose.runtime.Composer
    public void startNode() {
        m4402startAzEfcrM(GapComposerKt.nodeKey, null, GroupKind.INSTANCE.m4503getNode9udXigM(), null);
        this.nodeExpected = true;
    }

    @Override // androidx.compose.runtime.Composer
    public void startProvider(ProvidedValue<?> value) {
        ValueHolder<?> valueHolder;
        PersistentCompositionLocalMap providers;
        boolean invalid;
        PersistentCompositionLocalMap oldScope;
        PersistentCompositionLocalMap parentScope = currentCompositionLocalScope();
        startGroup(ComposerKt.providerKey, ComposerKt.getProvider());
        Object it = rememberedValue();
        if (Intrinsics.areEqual(it, Composer.INSTANCE.getEmpty())) {
            valueHolder = null;
        } else {
            Intrinsics.checkNotNull(it, "null cannot be cast to non-null type androidx.compose.runtime.ValueHolder<kotlin.Any?>");
            valueHolder = (ValueHolder) it;
        }
        CompositionLocal<?> compositionLocal = value.getCompositionLocal();
        Intrinsics.checkNotNull(compositionLocal, "null cannot be cast to non-null type androidx.compose.runtime.CompositionLocal<kotlin.Any?>");
        Intrinsics.checkNotNull(value, "null cannot be cast to non-null type androidx.compose.runtime.ProvidedValue<kotlin.Any?>");
        ValueHolder<?> valueHolderUpdatedStateOf$runtime = compositionLocal.updatedStateOf$runtime(value, valueHolder);
        boolean z = true;
        boolean change = !Intrinsics.areEqual(valueHolderUpdatedStateOf$runtime, valueHolder);
        if (change) {
            updateRememberedValue(valueHolderUpdatedStateOf$runtime);
        }
        if (getInserting()) {
            if (value.getCanOverride() || !CompositionLocalMapKt.contains(parentScope, compositionLocal)) {
                oldScope = parentScope.putValue(compositionLocal, valueHolderUpdatedStateOf$runtime);
            } else {
                oldScope = parentScope;
            }
            invalid = false;
            this.builderHasAProvider = true;
        } else {
            Object objGroupAux = this.reader.groupAux(this.reader.getCurrentGroup());
            Intrinsics.checkNotNull(objGroupAux, "null cannot be cast to non-null type androidx.compose.runtime.PersistentCompositionLocalMap");
            PersistentCompositionLocalMap oldScope2 = (PersistentCompositionLocalMap) objGroupAux;
            if ((!getSkipping() || change) && (value.getCanOverride() || !CompositionLocalMapKt.contains(parentScope, compositionLocal))) {
                providers = parentScope.putValue(compositionLocal, valueHolderUpdatedStateOf$runtime);
            } else {
                providers = ((change || this.providersInvalid) && this.providersInvalid) ? parentScope : oldScope2;
            }
            if (!this.reusing && oldScope2 == providers) {
                z = false;
            }
            invalid = z;
            oldScope = providers;
        }
        if (invalid && !getInserting()) {
            recordProviderUpdate(oldScope);
        }
        this.providersInvalidStack.push(LinkComposerKt.asInt(this.providersInvalid));
        this.providersInvalid = invalid;
        this.providerCache = oldScope;
        m4402startAzEfcrM(ComposerKt.compositionLocalMapKey, ComposerKt.getCompositionLocalMap(), GroupKind.INSTANCE.m4502getGroup9udXigM(), oldScope);
    }

    @Override // androidx.compose.runtime.Composer
    public void startProviders(ProvidedValue<?>[] values) {
        PersistentCompositionLocalMap providers;
        boolean invalid;
        PersistentCompositionLocalMap parentScope = currentCompositionLocalScope();
        startGroup(ComposerKt.providerKey, ComposerKt.getProvider());
        boolean z = true;
        if (getInserting()) {
            providers = updateProviderMapGroup(parentScope, CompositionLocalMapKt.updateCompositionMap$default(values, parentScope, null, 4, null));
            invalid = false;
            this.builderHasAProvider = true;
        } else {
            Object obj = this.reader.get(0);
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type androidx.compose.runtime.PersistentCompositionLocalMap");
            PersistentCompositionLocalMap oldScope = (PersistentCompositionLocalMap) obj;
            Object obj2 = this.reader.get(1);
            Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type androidx.compose.runtime.PersistentCompositionLocalMap");
            PersistentCompositionLocalMap oldValues = (PersistentCompositionLocalMap) obj2;
            PersistentCompositionLocalMap currentProviders = CompositionLocalMapKt.updateCompositionMap(values, parentScope, oldValues);
            if (!getSkipping() || this.reusing || !Intrinsics.areEqual(oldValues, currentProviders)) {
                providers = updateProviderMapGroup(parentScope, currentProviders);
                if (!this.reusing && Intrinsics.areEqual(providers, oldScope)) {
                    z = false;
                }
                invalid = z;
            } else {
                skipGroup();
                providers = oldScope;
                invalid = false;
            }
        }
        if (invalid && !getInserting()) {
            recordProviderUpdate(providers);
        }
        this.providersInvalidStack.push(LinkComposerKt.asInt(this.providersInvalid));
        this.providersInvalid = invalid;
        this.providerCache = providers;
        m4402startAzEfcrM(ComposerKt.compositionLocalMapKey, ComposerKt.getCompositionLocalMap(), GroupKind.INSTANCE.m4502getGroup9udXigM(), providers);
    }

    @Override // androidx.compose.runtime.Composer
    public void startReplaceableGroup(int key) {
        m4402startAzEfcrM(key, null, GroupKind.INSTANCE.m4502getGroup9udXigM(), null);
    }

    @Override // androidx.compose.runtime.Composer
    public void startReplaceGroup(int key) {
        LinkPending pending = this.pending;
        if (pending != null) {
            m4402startAzEfcrM(key, null, GroupKind.INSTANCE.m4502getGroup9udXigM(), null);
            return;
        }
        validateNodeNotExpected();
        int rGroupIndex$iv = this.rGroupIndex;
        long $this$compoundWith$iv$iv$iv = getCompositeKeyHashCode();
        long $this$rol$iv$iv$iv$iv = Long.rotateLeft($this$compoundWith$iv$iv$iv, 3) ^ ((long) key);
        long jRotateLeft = Long.rotateLeft($this$rol$iv$iv$iv$iv, 3);
        long $this$rol$iv$iv$iv$iv2 = rGroupIndex$iv;
        this.compositeKeyHashCode = jRotateLeft ^ $this$rol$iv$iv$iv$iv2;
        this.rGroupIndex++;
        SlotTableReader reader = this.reader;
        if (getInserting()) {
            reader.beginEmpty();
            SlotTableBuilder this_$iv = this.builder;
            Object objectKey$iv = Composer.INSTANCE.getEmpty();
            this_$iv.startNewGroup(key, objectKey$iv == Composer.INSTANCE.getEmpty() ? 0 : 16777216, objectKey$iv, null, null);
            enterGroup(false, null);
            return;
        }
        int slotKey = reader.getGroupKey();
        if (slotKey == key && !reader.getHasObjectKey()) {
            reader.startGroup();
            enterGroup(false, null);
            return;
        }
        if (!reader.isGroupEnd()) {
            int removeIndex = this.nodeIndex;
            recordDelete();
            int nodesToRemove = reader.skipGroup();
            this.changeListWriter.removeNode(removeIndex, nodesToRemove);
        }
        reader.beginEmpty();
        this.inserting = true;
        this.providerCache = null;
        ensureBuilder();
        SlotTableBuilder this_$iv2 = this.builder;
        Object objectKey$iv2 = Composer.INSTANCE.getEmpty();
        this_$iv2.startNewGroup(key, objectKey$iv2 == Composer.INSTANCE.getEmpty() ? 0 : 16777216, objectKey$iv2, null, null);
        enterGroup(false, null);
    }

    @Override // androidx.compose.runtime.Composer
    public Composer startRestartGroup(int key) {
        startReplaceGroup(key);
        addRecomposeScope();
        return this;
    }

    @Override // androidx.compose.runtime.Composer
    public void startReusableGroup(int key, Object dataKey) {
        if (!getInserting() && this.reader.getGroupKey() == key && !Intrinsics.areEqual(this.reader.getGroupAux(), dataKey) && this.reusingGroup < 0) {
            this.reusingGroup = this.reader.getCurrentGroup();
            this.reusing = true;
        }
        m4402startAzEfcrM(key, null, GroupKind.INSTANCE.m4502getGroup9udXigM(), dataKey);
    }

    @Override // androidx.compose.runtime.Composer
    public void startReusableNode() {
        m4402startAzEfcrM(GapComposerKt.nodeKey, null, GroupKind.INSTANCE.m4504getReusableNode9udXigM(), null);
        this.nodeExpected = true;
    }

    @Override // androidx.compose.runtime.InternalComposer
    public void startReuseFromRoot$runtime() {
        this.reusingGroup = this.slotTable.getRoot();
        this.reusing = true;
    }

    @Override // androidx.compose.runtime.Composer
    public void startMovableGroup(int key, Object dataKey) {
        m4402startAzEfcrM(key, dataKey, GroupKind.INSTANCE.m4502getGroup9udXigM(), null);
    }

    @Override // androidx.compose.runtime.InternalComposer
    public boolean tryImminentInvalidation$runtime(RecomposeScopeImpl scope, Object instance) {
        int address;
        Anchor anchor = scope.getAnchor();
        if (anchor == null || (address = LinkAnchorKt.asLinkAnchor(anchor).getAddress()) < 0 || !getIsComposing() || !isGroupAfterCurrentReaderPosition((((long) 0) << 32) | (((long) UInt.m9024constructorimpl(address)) & 4294967295L))) {
            return false;
        }
        this.reader.addFlag(address, 67108864);
        if (instance == null || Intrinsics.areEqual(instance, ScopeInvalidated.INSTANCE)) {
            ScopeMap.m4490setimpl(this.invalidations, scope, ScopeInvalidated.INSTANCE);
            return true;
        }
        boolean z = instance instanceof ScatterSet;
        MutableScatterMap<Object, Object> mutableScatterMap = this.invalidations;
        if (z) {
            Intrinsics.checkNotNull(instance, "null cannot be cast to non-null type androidx.collection.ScatterSet<kotlin.Any>");
            ScopeMap.m4467addAllimpl(mutableScatterMap, scope, (ScatterSet) instance);
            return true;
        }
        if (!Intrinsics.areEqual(ScopeMap.m4480getimpl(mutableScatterMap, scope), ScopeInvalidated.INSTANCE)) {
            ScopeMap.m4466addimpl(this.invalidations, scope, instance);
            return true;
        }
        return true;
    }

    @Override // androidx.compose.runtime.InternalComposer
    /* JADX INFO: renamed from: updateComposerInvalidations-RY85e9Y$runtime */
    public void mo4396updateComposerInvalidationsRY85e9Y$runtime(MutableScatterMap<Object, Object> invalidationsRequested) {
        Object[] k$iv;
        Object[] v$iv;
        ScatterMap this_$iv$iv;
        int $i$f$forEachIndexed;
        int i;
        Object[] k$iv2;
        Object[] v$iv2;
        ScatterMap this_$iv$iv2;
        int $i$f$forEachIndexed2;
        MutableScatterMap<Object, Object> this_$iv = invalidationsRequested;
        int $i$f$forEach = 0;
        Object[] k$iv3 = this_$iv.keys;
        Object[] v$iv3 = this_$iv.values;
        ScatterMap this_$iv$iv3 = this_$iv;
        int $i$f$forEachIndexed3 = 0;
        long[] m$iv$iv = this_$iv$iv3.metadata;
        int lastIndex$iv$iv = m$iv$iv.length - 2;
        int i$iv$iv = 0;
        if (0 > lastIndex$iv$iv) {
            return;
        }
        while (true) {
            long slot$iv$iv = m$iv$iv[i$iv$iv];
            ScatterMap this_$iv2 = this_$iv;
            int $i$f$forEach2 = $i$f$forEach;
            if ((((~slot$iv$iv) << 7) & slot$iv$iv & (-9187201950435737472L)) == -9187201950435737472L) {
                k$iv = k$iv3;
                v$iv = v$iv3;
                this_$iv$iv = this_$iv$iv3;
                $i$f$forEachIndexed = $i$f$forEachIndexed3;
            } else {
                int i2 = 8;
                int bitCount$iv$iv = 8 - ((~(i$iv$iv - lastIndex$iv$iv)) >>> 31);
                int j$iv$iv = 0;
                while (j$iv$iv < bitCount$iv$iv) {
                    long value$iv$iv$iv = 255 & slot$iv$iv;
                    if (!(value$iv$iv$iv < 128)) {
                        i = i2;
                        k$iv2 = k$iv3;
                        v$iv2 = v$iv3;
                        this_$iv$iv2 = this_$iv$iv3;
                        $i$f$forEachIndexed2 = $i$f$forEachIndexed3;
                    } else {
                        int index$iv$iv = (i$iv$iv << 3) + j$iv$iv;
                        i = i2;
                        Object scope = k$iv3[index$iv$iv];
                        k$iv2 = k$iv3;
                        Object instances = v$iv3[index$iv$iv];
                        v$iv2 = v$iv3;
                        Intrinsics.checkNotNull(scope, "null cannot be cast to non-null type androidx.compose.runtime.RecomposeScopeImpl");
                        Anchor anchor = ((RecomposeScopeImpl) scope).getAnchor();
                        LinkAnchor anchor2 = anchor != null ? LinkAnchorKt.asLinkAnchor(anchor) : null;
                        if (anchor2 == null) {
                            this_$iv$iv2 = this_$iv$iv3;
                            $i$f$forEachIndexed2 = $i$f$forEachIndexed3;
                        } else if (anchor2.getValid()) {
                            int address = anchor2.getAddress();
                            this_$iv$iv2 = this_$iv$iv3;
                            $i$f$forEachIndexed2 = $i$f$forEachIndexed3;
                            this.reader.addFlag(address, 67108864);
                            if (Intrinsics.areEqual(instances, ScopeInvalidated.INSTANCE)) {
                                ScopeMap.m4490setimpl(this.invalidations, scope, ScopeInvalidated.INSTANCE);
                            } else if (instances instanceof MutableScatterSet) {
                                Intrinsics.checkNotNull(instances, "null cannot be cast to non-null type androidx.collection.MutableScatterSet<kotlin.Any>");
                                ScopeMap.m4467addAllimpl(this.invalidations, scope, (ScatterSet) instances);
                            } else {
                                ScopeMap.m4466addimpl(this.invalidations, scope, instances);
                            }
                            this.reader.addFlag(address, 67108864);
                        } else {
                            this_$iv$iv2 = this_$iv$iv3;
                            $i$f$forEachIndexed2 = $i$f$forEachIndexed3;
                        }
                    }
                    slot$iv$iv >>= i;
                    j$iv$iv++;
                    i2 = i;
                    k$iv3 = k$iv2;
                    v$iv3 = v$iv2;
                    this_$iv$iv3 = this_$iv$iv2;
                    $i$f$forEachIndexed3 = $i$f$forEachIndexed2;
                }
                k$iv = k$iv3;
                v$iv = v$iv3;
                this_$iv$iv = this_$iv$iv3;
                $i$f$forEachIndexed = $i$f$forEachIndexed3;
                if (bitCount$iv$iv != i2) {
                    return;
                }
            }
            if (i$iv$iv == lastIndex$iv$iv) {
                return;
            }
            i$iv$iv++;
            this_$iv = this_$iv2;
            $i$f$forEach = $i$f$forEach2;
            k$iv3 = k$iv;
            v$iv3 = v$iv;
            this_$iv$iv3 = this_$iv$iv;
            $i$f$forEachIndexed3 = $i$f$forEachIndexed;
        }
    }

    @Override // androidx.compose.runtime.Composer
    public void updateRememberedValue(Object value) {
        updateCachedValue$runtime(value);
    }

    @Override // androidx.compose.runtime.Composer
    public void useNode() {
        validateNodeExpected();
        boolean value$iv = !getInserting();
        if (!value$iv) {
            ComposerKt.composeImmediateRuntimeError("useNode() called while inserting");
        }
        Object node = this.reader.getParentNode();
        this.changeListWriter.moveDown(node);
        if (this.reusing && (node instanceof ComposeNodeLifecycleCallback)) {
            this.changeListWriter.useNode(node);
        }
    }

    @Override // androidx.compose.runtime.InternalComposer
    public void verifyConsistent$runtime() {
        if (!getIsComposing()) {
            getInsertTable$runtime().verifyWellFormed();
        }
    }

    public final Object nextSlot$runtime() {
        if (getInserting()) {
            validateNodeNotExpected();
            return Composer.INSTANCE.getEmpty();
        }
        Object it = this.reader.next();
        return (!this.reusing || (it instanceof ReusableRememberObserverHolder)) ? it : Composer.INSTANCE.getEmpty();
    }

    public final Object nextSlotForCache$runtime() {
        if (getInserting()) {
            validateNodeNotExpected();
            return Composer.INSTANCE.getEmpty();
        }
        Object it = this.reader.next();
        if (this.reusing && !(it instanceof ReusableRememberObserverHolder)) {
            return Composer.INSTANCE.getEmpty();
        }
        if (!(it instanceof RememberObserverHolder)) {
            return it;
        }
        this.changeListWriter.updateRememberOrdering(LinkComposerKt.asLinkRememberObserverHolder((RememberObserverHolder) it), getReaderTable$runtime().getAddressSpace().anchorOfAddress(this.lastPlacedChildGroup));
        return it;
    }

    public final void updateValue(Object value) {
        if (getInserting()) {
            this.builder.append(value);
            return;
        }
        boolean hadNext = this.reader.getHadNext();
        ComposerChangeListWriter composerChangeListWriter = this.changeListWriter;
        if (hadNext) {
            composerChangeListWriter.updateValue(this.reader.getParentCurrentSlotOffset() - 1, value);
        } else {
            composerChangeListWriter.appendValue(value);
        }
    }

    private final void abortRoot() {
        cleanUpCompose();
        Stack.m4416clearimpl(this.pendingStack);
        this.parentStateStack.clear();
        this.entersStack.clear();
        this.providersInvalidStack.clear();
        this.providerUpdates = null;
        this.insertFixups.clear();
        this.compositeKeyHashCode = 0;
        this.childrenComposing = 0;
        this.nodeExpected = false;
        this.inserting = false;
        this.reusing = false;
        this.isComposing = false;
        this.forciblyRecompose = false;
        this.reusingGroup = -1;
        if (!this.reader.getIsClosed()) {
            this.reader.close();
        }
        resetInsertBuilder(false);
    }

    /* JADX WARN: Removed duplicated region for block: B:65:0x008a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void addRecomposeScope() {
        /*
            r11 = this;
            boolean r0 = r11.getInserting()
            if (r0 == 0) goto L1e
            androidx.compose.runtime.RecomposeScopeImpl r0 = new androidx.compose.runtime.RecomposeScopeImpl
            androidx.compose.runtime.CompositionImpl r1 = r11.getComposition()
            androidx.compose.runtime.RecomposeScopeOwner r1 = (androidx.compose.runtime.RecomposeScopeOwner) r1
            r0.<init>(r1)
            java.util.ArrayList<androidx.compose.runtime.RecomposeScopeImpl> r1 = r11.invalidateStack
            androidx.compose.runtime.Stack.m4428pushimpl(r1, r0)
            r11.updateValue(r0)
            r11.enterRecomposeScope(r0)
            goto Lbe
        L1e:
            androidx.compose.runtime.composer.linkbuffer.SlotTableReader r0 = r11.reader
            int r0 = r0.getParent()
            androidx.compose.runtime.composer.linkbuffer.SlotTableReader r1 = r11.reader
            androidx.compose.runtime.RecomposeScopeImpl r1 = androidx.compose.runtime.LinkComposerKt.getRecomposeScopeOrNull(r1, r0)
            if (r1 == 0) goto L35
            r2 = r1
            r3 = 0
            androidx.collection.MutableScatterMap<java.lang.Object, java.lang.Object> r4 = r11.invalidations
            java.lang.Object r2 = androidx.compose.runtime.collection.ScopeMap.m4485removeimpl(r4, r2)
            goto L36
        L35:
            r2 = 0
        L36:
            androidx.compose.runtime.composer.linkbuffer.SlotTableReader r3 = r11.reader
            boolean r3 = r3.recomposeRequired(r0)
            if (r3 == 0) goto L45
            androidx.compose.runtime.composer.linkbuffer.SlotTableReader r4 = r11.reader
            r5 = 67108864(0x4000000, float:1.5046328E-36)
            r4.removeFlag(r5)
        L45:
            androidx.compose.runtime.composer.linkbuffer.SlotTableReader r4 = r11.reader
            java.lang.Object r4 = r4.next()
            androidx.compose.runtime.Composer$Companion r5 = androidx.compose.runtime.Composer.INSTANCE
            java.lang.Object r5 = r5.getEmpty()
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual(r4, r5)
            if (r5 == 0) goto L67
            androidx.compose.runtime.RecomposeScopeImpl r5 = new androidx.compose.runtime.RecomposeScopeImpl
            androidx.compose.runtime.CompositionImpl r6 = r11.getComposition()
            androidx.compose.runtime.RecomposeScopeOwner r6 = (androidx.compose.runtime.RecomposeScopeOwner) r6
            r5.<init>(r6)
            r11.updateValue(r5)
            goto L70
        L67:
            java.lang.String r5 = "null cannot be cast to non-null type androidx.compose.runtime.RecomposeScopeImpl"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r4, r5)
            r5 = r4
            androidx.compose.runtime.RecomposeScopeImpl r5 = (androidx.compose.runtime.RecomposeScopeImpl) r5
        L70:
            r6 = 0
            r7 = 1
            if (r3 != 0) goto L8a
            if (r2 != 0) goto L8a
            boolean r8 = r5.getForcedRecompose()
            r9 = r8
            r10 = 0
            if (r9 == 0) goto L84
            r5.setForcedRecompose(r6)
        L84:
            if (r8 == 0) goto L88
            goto L8a
        L88:
            r8 = r6
            goto L8b
        L8a:
            r8 = r7
        L8b:
            r5.setRequiresRecompose(r8)
            java.util.ArrayList<androidx.compose.runtime.RecomposeScopeImpl> r8 = r11.invalidateStack
            androidx.compose.runtime.Stack.m4428pushimpl(r8, r5)
            r11.enterRecomposeScope(r5)
            boolean r8 = r5.getPaused()
            if (r8 == 0) goto Lbe
            r5.setPaused(r6)
            r5.setResuming(r7)
            androidx.compose.runtime.composer.linkbuffer.changelist.ComposerChangeListWriter r6 = r11.changeListWriter
            r6.startResumingScope(r5)
            boolean r6 = r11.reusing
            if (r6 != 0) goto Lbe
            boolean r6 = r5.getReusing()
            if (r6 == 0) goto Lbe
            r11.reusing = r7
            androidx.compose.runtime.composer.linkbuffer.SlotTableReader r6 = r11.reader
            int r6 = r6.getParent()
            r11.reusingGroup = r6
            r5.setResetReusing(r7)
        Lbe:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.LinkComposer.addRecomposeScope():void");
    }

    private final void cleanUpCompose() {
        this.pending = null;
        this.nodeIndex = 0;
        this.groupNodeCount = 0;
        this.compositeKeyHashCode = 0L;
        this.nodeExpected = false;
        Stack.m4416clearimpl(this.invalidateStack);
        clearUpdatedNodeCounts();
    }

    private final void clearUpdatedNodeCounts() {
        this.nodeCountOverrides = null;
        this.nodeCountVirtualOverrides = null;
    }

    public final ComposeStackTrace currentStackTrace() {
        if (getSourceMarkersEnabled()) {
            List $this$currentStackTrace_u24lambda_u240 = CollectionsKt.createListBuilder();
            $this$currentStackTrace_u24lambda_u240.addAll(SlotTableBuilderKt.buildTrace(this.builder));
            $this$currentStackTrace_u24lambda_u240.addAll(SlotTableReaderKt.buildTrace(this.reader));
            $this$currentStackTrace_u24lambda_u240.addAll(parentStackTrace$runtime());
            return new ComposeStackTrace(CollectionsKt.build($this$currentStackTrace_u24lambda_u240), getSourceMarkersEnabled());
        }
        return null;
    }

    /* JADX INFO: renamed from: doCompose-aFTiNEg */
    private final void m4401doComposeaFTiNEg(MutableScatterMap<Object, Object> invalidationsRequested, Function2<? super Composer, ? super Integer, Unit> content) {
        Object savedContent;
        DerivedStateObserver observer$iv;
        MutableVector<DerivedStateObserver> mutableVectorDerivedStateObservers;
        boolean value$iv = !getIsComposing();
        if (!value$iv) {
            ComposerKt.composeImmediateRuntimeError("Reentrant composition is not supported");
        }
        CompositionObserver observer = this.observerHolder.current();
        Object token$iv = Trace.INSTANCE.beginSection("Compose:recompose");
        try {
            this.compositionToken = Long.hashCode(SnapshotKt.currentSnapshot().getSnapshotId());
            this.providerUpdates = null;
            mo4396updateComposerInvalidationsRY85e9Y$runtime(invalidationsRequested);
            this.nodeIndex = 0;
            this.isComposing = true;
            if (observer != null) {
                observer.onBeginComposition(getComposition());
            }
            try {
                startRoot();
                savedContent = nextSlot$runtime();
                if (savedContent != content && content != null) {
                    updateValue(content);
                }
                observer$iv = this.derivedStateObserver;
                mutableVectorDerivedStateObservers = SnapshotStateKt.derivedStateObservers();
            } catch (Throwable th) {
                e = th;
            }
            try {
                try {
                    mutableVectorDerivedStateObservers.add(observer$iv);
                } catch (Throwable th2) {
                    th = th2;
                }
                try {
                    if (content != null) {
                        startGroup(200, ComposerKt.getInvocation());
                        Expect_jvmKt.invokeComposable(this, content);
                        endGroup();
                    } else if ((!this.forciblyRecompose && !this.providersInvalid) || savedContent == null || Intrinsics.areEqual(savedContent, Composer.INSTANCE.getEmpty())) {
                        skipCurrentGroup();
                    } else {
                        startGroup(200, ComposerKt.getInvocation());
                        Expect_jvmKt.invokeComposable(this, (Function2) TypeIntrinsics.beforeCheckcastToFunctionOfArity(savedContent, 2));
                        endGroup();
                    }
                    mutableVectorDerivedStateObservers.removeAt(mutableVectorDerivedStateObservers.getSize() - 1);
                    endRoot();
                    if (observer != null) {
                        observer.onEndComposition(getComposition());
                    }
                    this.isComposing = false;
                    resetInsertBuilder(false);
                    Unit unit = Unit.INSTANCE;
                } catch (Throwable th3) {
                    th = th3;
                    mutableVectorDerivedStateObservers.removeAt(mutableVectorDerivedStateObservers.getSize() - 1);
                    throw th;
                }
            } catch (Throwable th4) {
                e = th4;
                try {
                    throw ComposeStackTraceKt.attachComposeStackTrace(e, new Function0() { // from class: androidx.compose.runtime.LinkComposer$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return this.f$0.currentStackTrace();
                        }
                    });
                } catch (Throwable e) {
                    if (observer != null) {
                        observer.onEndComposition(getComposition());
                    }
                    this.isComposing = false;
                    abortRoot();
                    resetInsertBuilder(true);
                    throw e;
                }
            }
        } finally {
            Trace.INSTANCE.endSection(token$iv);
        }
    }

    private final void end(boolean isNode) {
        int previousEnd;
        List<KeyInfo> list;
        int rGroupIndex = this.parentStateStack.peek2() - 1;
        if (getInserting()) {
            int parent = this.builder.getParent();
            int groupKey$iv = this.builder.groupKey(parent);
            Object dataKey$iv = this.builder.groupObjectKey(parent);
            Object data$iv = this.builder.groupAux(parent);
            if (dataKey$iv == null) {
                if (data$iv == null || groupKey$iv != 207 || Intrinsics.areEqual(data$iv, Composer.INSTANCE.getEmpty())) {
                    long $this$unCompoundWith$iv$iv$iv = getCompositeKeyHashCode();
                    long $this$ror$iv$iv$iv$iv = ((long) rGroupIndex) ^ $this$unCompoundWith$iv$iv$iv;
                    long $this$unCompoundWith$iv$iv$iv2 = Long.rotateRight($this$ror$iv$iv$iv$iv, 3);
                    long $this$ror$iv$iv$iv$iv2 = $this$unCompoundWith$iv$iv$iv2 ^ ((long) groupKey$iv);
                    this.compositeKeyHashCode = Long.rotateRight($this$ror$iv$iv$iv$iv2, 3);
                } else {
                    int groupKey$iv$iv = data$iv.hashCode();
                    long $this$unCompoundWith$iv$iv$iv3 = getCompositeKeyHashCode();
                    long $this$ror$iv$iv$iv$iv3 = ((long) rGroupIndex) ^ $this$unCompoundWith$iv$iv$iv3;
                    long $this$unCompoundWith$iv$iv$iv4 = Long.rotateRight($this$ror$iv$iv$iv$iv3, 3);
                    long $this$ror$iv$iv$iv$iv4 = $this$unCompoundWith$iv$iv$iv4 ^ ((long) groupKey$iv$iv);
                    this.compositeKeyHashCode = Long.rotateRight($this$ror$iv$iv$iv$iv4, 3);
                }
            } else if (dataKey$iv instanceof Enum) {
                int groupKey$iv$iv2 = ((Enum) dataKey$iv).ordinal();
                long $this$unCompoundWith$iv$iv$iv5 = getCompositeKeyHashCode();
                long $this$ror$iv$iv$iv$iv5 = ((long) 0) ^ $this$unCompoundWith$iv$iv$iv5;
                long $this$unCompoundWith$iv$iv$iv6 = Long.rotateRight($this$ror$iv$iv$iv$iv5, 3);
                long $this$ror$iv$iv$iv$iv6 = $this$unCompoundWith$iv$iv$iv6 ^ ((long) groupKey$iv$iv2);
                this.compositeKeyHashCode = Long.rotateRight($this$ror$iv$iv$iv$iv6, 3);
            } else {
                int groupKey$iv$iv3 = dataKey$iv.hashCode();
                long $this$unCompoundWith$iv$iv$iv7 = getCompositeKeyHashCode();
                long $this$ror$iv$iv$iv$iv7 = ((long) 0) ^ $this$unCompoundWith$iv$iv$iv7;
                long $this$unCompoundWith$iv$iv$iv8 = Long.rotateRight($this$ror$iv$iv$iv$iv7, 3);
                long $this$ror$iv$iv$iv$iv8 = $this$unCompoundWith$iv$iv$iv8 ^ ((long) groupKey$iv$iv3);
                this.compositeKeyHashCode = Long.rotateRight($this$ror$iv$iv$iv$iv8, 3);
            }
        } else {
            int parent2 = this.reader.getParent();
            int groupKey$iv2 = this.reader.groupKey(parent2);
            Object dataKey$iv2 = this.reader.groupObjectKey(parent2);
            Object data$iv2 = this.reader.groupAux(parent2);
            if (dataKey$iv2 == null) {
                if (data$iv2 == null || groupKey$iv2 != 207 || Intrinsics.areEqual(data$iv2, Composer.INSTANCE.getEmpty())) {
                    long $this$unCompoundWith$iv$iv$iv9 = getCompositeKeyHashCode();
                    long $this$ror$iv$iv$iv$iv9 = ((long) rGroupIndex) ^ $this$unCompoundWith$iv$iv$iv9;
                    long $this$unCompoundWith$iv$iv$iv10 = Long.rotateRight($this$ror$iv$iv$iv$iv9, 3);
                    long $this$ror$iv$iv$iv$iv10 = $this$unCompoundWith$iv$iv$iv10 ^ ((long) groupKey$iv2);
                    this.compositeKeyHashCode = Long.rotateRight($this$ror$iv$iv$iv$iv10, 3);
                } else {
                    int groupKey$iv$iv4 = data$iv2.hashCode();
                    long $this$unCompoundWith$iv$iv$iv11 = getCompositeKeyHashCode();
                    long $this$ror$iv$iv$iv$iv11 = ((long) rGroupIndex) ^ $this$unCompoundWith$iv$iv$iv11;
                    long $this$unCompoundWith$iv$iv$iv12 = Long.rotateRight($this$ror$iv$iv$iv$iv11, 3);
                    long $this$ror$iv$iv$iv$iv12 = $this$unCompoundWith$iv$iv$iv12 ^ ((long) groupKey$iv$iv4);
                    this.compositeKeyHashCode = Long.rotateRight($this$ror$iv$iv$iv$iv12, 3);
                }
            } else if (dataKey$iv2 instanceof Enum) {
                int groupKey$iv$iv5 = ((Enum) dataKey$iv2).ordinal();
                long $this$unCompoundWith$iv$iv$iv13 = getCompositeKeyHashCode();
                long $this$ror$iv$iv$iv$iv13 = ((long) 0) ^ $this$unCompoundWith$iv$iv$iv13;
                long $this$unCompoundWith$iv$iv$iv14 = Long.rotateRight($this$ror$iv$iv$iv$iv13, 3);
                long $this$ror$iv$iv$iv$iv14 = $this$unCompoundWith$iv$iv$iv14 ^ ((long) groupKey$iv$iv5);
                this.compositeKeyHashCode = Long.rotateRight($this$ror$iv$iv$iv$iv14, 3);
            } else {
                int groupKey$iv$iv6 = dataKey$iv2.hashCode();
                long $this$unCompoundWith$iv$iv$iv15 = getCompositeKeyHashCode();
                long $this$ror$iv$iv$iv$iv15 = ((long) 0) ^ $this$unCompoundWith$iv$iv$iv15;
                long $this$unCompoundWith$iv$iv$iv16 = Long.rotateRight($this$ror$iv$iv$iv$iv15, 3);
                long $this$ror$iv$iv$iv$iv16 = $this$unCompoundWith$iv$iv$iv16 ^ ((long) groupKey$iv$iv6);
                this.compositeKeyHashCode = Long.rotateRight($this$ror$iv$iv$iv$iv16, 3);
            }
        }
        int expectedNodeCount = this.groupNodeCount;
        LinkPending pending = this.pending;
        if (pending != null && !pending.getKeyInfos().isEmpty()) {
            List<KeyInfo> keyInfos = pending.getKeyInfos();
            List<KeyInfo> used = pending.getUsed();
            Set usedKeys = ListUtilsKt.fastToSet(used);
            Set placedKeys = new LinkedHashSet();
            int currentIndex = 0;
            int currentEnd = used.size();
            int previousIndex = 0;
            int previousEnd2 = keyInfos.size();
            int nodeOffset = 0;
            while (previousIndex < previousEnd2) {
                KeyInfo previousInfo = keyInfos.get(previousIndex);
                if (usedKeys.contains(previousInfo)) {
                    List<KeyInfo> list2 = keyInfos;
                    int previousIndex2 = previousIndex;
                    int previousEnd3 = previousEnd2;
                    if (placedKeys.contains(previousInfo)) {
                        previousIndex = previousIndex2 + 1;
                        previousEnd2 = previousEnd3;
                        keyInfos = list2;
                    } else if (currentIndex < currentEnd) {
                        KeyInfo currentInfo = used.get(currentIndex);
                        if (currentInfo != previousInfo) {
                            int nodePosition = pending.nodePositionOf(currentInfo);
                            placedKeys.add(currentInfo);
                            if (nodePosition != nodeOffset) {
                                int updatedCount = pending.updatedNodeCountOf(currentInfo);
                                ComposerChangeListWriter composerChangeListWriter = this.changeListWriter;
                                previousEnd = previousEnd3;
                                int previousEnd4 = nodePosition + pending.getStartIndex();
                                list = used;
                                composerChangeListWriter.moveNode(previousEnd4, nodeOffset + pending.getStartIndex(), updatedCount);
                                pending.registerMoveNode(nodePosition, nodeOffset, updatedCount);
                            } else {
                                previousEnd = previousEnd3;
                                list = used;
                            }
                            previousIndex = previousIndex2;
                        } else {
                            previousEnd = previousEnd3;
                            list = used;
                            previousIndex = previousIndex2 + 1;
                        }
                        currentIndex++;
                        nodeOffset += pending.updatedNodeCountOf(currentInfo);
                        keyInfos = list2;
                        previousEnd2 = previousEnd;
                        used = list;
                    } else {
                        previousIndex = previousIndex2;
                        keyInfos = list2;
                        previousEnd2 = previousEnd3;
                    }
                } else {
                    int deleteOffset = pending.nodePositionOf(previousInfo);
                    this.changeListWriter.removeNode(deleteOffset + pending.getStartIndex(), previousInfo.getNodes());
                    pending.updateNodeCount(GroupHandleKt.getGroup(previousInfo.getHandle()), 0);
                    this.reader.reposition(previousInfo.getHandle());
                    recordDelete();
                    this.reader.skipGroup();
                    previousIndex++;
                    previousEnd2 = previousEnd2;
                    keyInfos = keyInfos;
                }
            }
            this.changeListWriter.endNodeMovement();
            if (!keyInfos.isEmpty()) {
                this.reader.skipToGroupEnd();
            }
        }
        boolean inserting = getInserting();
        if (!inserting) {
            int removeIndex = this.nodeIndex;
            int predecessor = this.reader.get_previousSibling();
            SlotTable this_$iv = getReaderTable$runtime();
            int group$iv = this.reader.getCurrentGroup();
            SlotTableAddressSpace this_$iv$iv = this_$iv.getAddressSpace();
            int[] groups$iv$iv = this_$iv$iv.getGroups();
            int current$iv$iv = group$iv;
            while (current$iv$iv >= 0) {
                int group = current$iv$iv;
                LinkPending pending2 = pending;
                reportFreeMovableContent(GroupHandleKt.makeGroupHandle(this.reader.getParent(), predecessor, group));
                int nodesToRemove = this.reader.nodeCount(group);
                this.changeListWriter.removeNode(removeIndex, nodesToRemove);
                this.changeListWriter.endNodeMovement();
                predecessor = group;
                int address$iv$iv$iv = current$iv$iv;
                current$iv$iv = groups$iv$iv[address$iv$iv$iv + 1];
                pending = pending2;
                this_$iv = this_$iv;
                group$iv = group$iv;
            }
            this.changeListWriter.removeTailGroupsAndValues(this.reader.getCurrentGroup(), this.reader.getRemainingSlots());
        }
        if (inserting) {
            if (isNode) {
                this.insertFixups.endNodeInsert();
                expectedNodeCount = 1;
            }
            this.lastPlacedChildGroup = this.builder.getParent();
            this.reader.endEmpty();
            this.builder.endGroup();
            if (!this.reader.getInEmpty()) {
                long insertSrcAddress = this.builder.lastRoot();
                recordInsert(insertSrcAddress);
                this.inserting = false;
                if (!getReaderTable$runtime().isEmpty()) {
                    long insertedGroup = LinkComposerKt.toInsertAddress(insertSrcAddress);
                    updateChildNodeCount(insertedGroup, 0);
                    updateNodeCountOverrides(insertedGroup, expectedNodeCount);
                }
            }
        } else {
            if (isNode) {
                this.changeListWriter.moveUp();
            }
            long parentGroup = this.reader.getParentHandle();
            int parentNodeCount = updatedNodeCount(parentGroup);
            if (expectedNodeCount != parentNodeCount) {
                updateNodeCountOverrides(parentGroup, expectedNodeCount);
            }
            if (isNode) {
                expectedNodeCount = 1;
            }
            this.lastPlacedChildGroup = GroupHandleKt.getGroup(parentGroup);
            this.reader.endGroup();
            this.changeListWriter.endNodeMovement();
        }
        exitGroup(expectedNodeCount, inserting);
    }

    private final void endGroup() {
        end(false);
    }

    private final void endRoot() {
        endGroup();
        this.parentContext.doneComposing$runtime();
        endGroup();
        finalizeCompose();
        this.reader.close();
        this.forciblyRecompose = false;
        this.providersInvalid = LinkComposerKt.asBool(this.providersInvalidStack.pop());
    }

    @Override // androidx.compose.runtime.InternalComposer
    public void changesApplied$runtime() {
        this.providerUpdates = null;
    }

    private final void enterGroup(boolean isNode, LinkPending newPending) {
        Stack.m4428pushimpl(this.pendingStack, this.pending);
        this.pending = newPending;
        this.parentStateStack.push(this.groupNodeCount);
        this.parentStateStack.push(this.rGroupIndex);
        this.parentStateStack.push(this.nodeIndex);
        if (isNode) {
            this.nodeIndex = 0;
        }
        this.groupNodeCount = 0;
        this.rGroupIndex = 0;
        this.lastPlacedChildGroup = -1;
    }

    private final void executeChangesImmediatelyWithoutApplier() {
        SlotTable this_$iv = this.slotTable;
        SlotTableEditor $this$edit_u24lambda_u240$iv = this_$iv.openEditor();
        try {
            ChangeListKt.asLinkBufferChangeList(this.changes).executeAndFlushAllPendingChanges(ThrowingApplierStub.INSTANCE, $this$edit_u24lambda_u240$iv, ThrowingRememberManagerStub.INSTANCE, getErrorContext$runtime());
            Unit unit = Unit.INSTANCE;
        } finally {
            $this$edit_u24lambda_u240$iv.close();
        }
    }

    private final void exitGroup(int expectedNodeCount, boolean inserting) {
        LinkPending previousPending = (LinkPending) Stack.m4427popimpl(this.pendingStack);
        if (previousPending != null && !inserting) {
            previousPending.setGroupIndex(previousPending.getGroupIndex() + 1);
        }
        this.pending = previousPending;
        this.nodeIndex = this.parentStateStack.pop() + expectedNodeCount;
        this.rGroupIndex = this.parentStateStack.pop();
        this.groupNodeCount = this.parentStateStack.pop() + expectedNodeCount;
    }

    private final void ensureBuilder() {
        if (this.builder.getIsClosed()) {
            this.builder = new SlotTableBuilder(this.slotTable.getAddressSpace(), this.slotTable.getRecordSourceInformation(), this.slotTable.getRecordCallByInformation());
            this.builder.buildStart();
            this.builderHasAProvider = false;
            this.providerCache = null;
        }
    }

    private final void finalizeCompose() {
        this.changeListWriter.finalizeComposition();
        boolean value$iv = Stack.m4423isEmptyimpl(this.pendingStack);
        if (!value$iv) {
            ComposerKt.composeImmediateRuntimeError("Start/end imbalance");
        }
        cleanUpCompose();
    }

    private final PersistentCompositionLocalMap currentCompositionLocalScope() {
        PersistentCompositionLocalMap it = this.providerCache;
        if (it != null) {
            return it;
        }
        return currentCompositionLocalScope(this.reader.getParent());
    }

    private final PersistentCompositionLocalMap currentCompositionLocalScope(int group) {
        PersistentCompositionLocalMap providers;
        if (getInserting() && this.builderHasAProvider) {
            int current = this.builder.getParent();
            while (current >= 0) {
                if (this.builder.groupKey(current) == 202 && Intrinsics.areEqual(this.builder.groupObjectKey(current), ComposerKt.getCompositionLocalMap())) {
                    Object objGroupAux = this.builder.groupAux(current);
                    Intrinsics.checkNotNull(objGroupAux, "null cannot be cast to non-null type androidx.compose.runtime.PersistentCompositionLocalMap");
                    PersistentCompositionLocalMap providers2 = (PersistentCompositionLocalMap) objGroupAux;
                    this.providerCache = providers2;
                    return providers2;
                }
                current = this.builder.parent(current);
            }
        }
        if (!this.reader.isEmpty()) {
            int current2 = group;
            while (current2 >= 0) {
                if (this.reader.groupKey(current2) == 202 && Intrinsics.areEqual(this.reader.groupObjectKey(current2), ComposerKt.getCompositionLocalMap())) {
                    MutableIntObjectMap<PersistentCompositionLocalMap> mutableIntObjectMap = this.providerUpdates;
                    if (mutableIntObjectMap == null || (providers = mutableIntObjectMap.get(current2)) == null) {
                        Object objGroupAux2 = this.reader.groupAux(current2);
                        Intrinsics.checkNotNull(objGroupAux2, "null cannot be cast to non-null type androidx.compose.runtime.PersistentCompositionLocalMap");
                        providers = (PersistentCompositionLocalMap) objGroupAux2;
                    }
                    this.providerCache = providers;
                    return providers;
                }
                current2 = this.reader.parentOf(current2);
            }
        }
        this.providerCache = this.rootProvider;
        return this.rootProvider;
    }

    private final void insertMovableContentGuarded(List<Pair<MovableContentStateReference, MovableContentStateReference>> references) throws Throwable {
        ComposerChangeListWriter this_$iv;
        final MovableContentStateReference to;
        MovableContentStateReference from;
        final long handle;
        IntRef effectiveNodeIndex;
        int i;
        int index$iv;
        ChangeList newChangeList$iv;
        int $i$f$withChangeList;
        int i2;
        List<Pair<MovableContentStateReference, MovableContentStateReference>> list;
        int $i$f$fastForEach;
        SlotTable slotTableAsLinkBufferSlotTable;
        SlotTable fromTable;
        ComposerChangeListWriter this_$iv2;
        SlotTableReader savedReader$iv;
        MutableIntIntMap savedCountOverrides$iv;
        LinkComposer this_$iv3;
        MutableIntObjectMap<PersistentCompositionLocalMap> mutableIntObjectMap;
        ChangeList previousChangeList$iv;
        boolean previousImplicitRootStart$iv;
        ComposerChangeListWriter this_$iv4;
        ComposerChangeListWriterAddressMode previousMode$iv$iv;
        long previousCurrentPosition$iv$iv;
        MovableContentState resolvedState;
        ComposerChangeListWriterAddressMode previousMode$iv$iv2;
        Throwable th;
        long j;
        SlotStorage slotStorage;
        SlotTable slotTableAsLinkBufferSlotTable2;
        SlotStorage slotStorage2;
        final LinkComposer linkComposer = this;
        ComposerChangeListWriter this_$iv5 = linkComposer.changeListWriter;
        ChangeList newChangeList$iv2 = ChangeListKt.asLinkBufferChangeList(linkComposer.lateChanges);
        int $i$f$withChangeList2 = 0;
        ChangeList previousChangeList$iv2 = this_$iv5.getChangeList();
        try {
            this_$iv5.setChangeList(newChangeList$iv2);
            int i3 = 0;
            linkComposer.changeListWriter.resetSlots();
            List<Pair<MovableContentStateReference, MovableContentStateReference>> list2 = references;
            int $i$f$fastForEach2 = 0;
            int size = list2.size();
            int index$iv2 = 0;
            while (index$iv2 < size) {
                try {
                    Object item$iv = list2.get(index$iv2);
                    Pair<MovableContentStateReference, MovableContentStateReference> pair = (Pair) item$iv;
                    to = pair.component1();
                    from = pair.component2();
                    int $this$toGroupHandle$iv = LinkAnchorKt.asLinkAnchor(to.getAnchor()).getAddress();
                    try {
                        handle = (((long) 0) << 32) | (((long) UInt.m9024constructorimpl($this$toGroupHandle$iv)) & 4294967295L);
                        effectiveNodeIndex = new IntRef(0, 1, null);
                    } catch (Throwable th2) {
                        th = th2;
                    }
                } catch (Throwable th3) {
                    th = th3;
                }
                try {
                    this.changeListWriter.determineMovableContentNodeIndex(effectiveNodeIndex, handle);
                    if (from == null) {
                        try {
                            SlotTable toSlotTable = SlotTableKt.asLinkBufferSlotTable(to.getSlotStorage());
                            if (Intrinsics.areEqual(toSlotTable, this.builder.getTable())) {
                                resetInsertBuilder(false);
                            }
                            final SlotTableReader $this$read_u24lambda_u240$iv = toSlotTable.openReader();
                            try {
                                $this$read_u24lambda_u240$iv.reposition(handle);
                                final ChangeList offsetChanges = new ChangeList();
                                try {
                                    try {
                                        i = size;
                                        index$iv = index$iv2;
                                        linkComposer = this;
                                        try {
                                            recomposeMovableContent$default(linkComposer, null, null, 0, null, new Function0() { // from class: androidx.compose.runtime.LinkComposer$$ExternalSyntheticLambda1
                                                @Override // kotlin.jvm.functions.Function0
                                                public final Object invoke() {
                                                    return LinkComposer.insertMovableContentGuarded$lambda$0$0$0$0(this.f$0, offsetChanges, $this$read_u24lambda_u240$iv, handle, to);
                                                }
                                            }, 15, null);
                                        } catch (Throwable th4) {
                                            th = th4;
                                        }
                                    } catch (Throwable th5) {
                                        th = th5;
                                    }
                                } catch (Throwable th6) {
                                    th = th6;
                                }
                                try {
                                    linkComposer.changeListWriter.includeOperationsIn(offsetChanges, effectiveNodeIndex);
                                    Unit unit = Unit.INSTANCE;
                                    try {
                                        $this$read_u24lambda_u240$iv.close();
                                        newChangeList$iv = newChangeList$iv2;
                                        $i$f$withChangeList = $i$f$withChangeList2;
                                        i2 = i3;
                                        list = list2;
                                        $i$f$fastForEach = $i$f$fastForEach2;
                                        index$iv2 = index$iv + 1;
                                        size = i;
                                        newChangeList$iv2 = newChangeList$iv;
                                        $i$f$withChangeList2 = $i$f$withChangeList;
                                        $i$f$fastForEach2 = $i$f$fastForEach;
                                        i3 = i2;
                                        list2 = list;
                                    } catch (Throwable th7) {
                                        th = th7;
                                        this_$iv = this_$iv5;
                                        this_$iv.setChangeList(previousChangeList$iv2);
                                        throw th;
                                    }
                                } catch (Throwable th8) {
                                    th = th8;
                                    $this$read_u24lambda_u240$iv.close();
                                    throw th;
                                }
                            } catch (Throwable th9) {
                                th = th9;
                            }
                        } catch (Throwable th10) {
                            th = th10;
                        }
                    } else {
                        index$iv = index$iv2;
                        i = size;
                        linkComposer = this;
                        MovableContentState resolvedState2 = linkComposer.parentContext.movableContentStateResolve$runtime(from);
                        if (resolvedState2 == null || (slotStorage2 = resolvedState2.getSlotStorage()) == null || (slotTableAsLinkBufferSlotTable = SlotTableKt.asLinkBufferSlotTable(slotStorage2)) == null) {
                            slotTableAsLinkBufferSlotTable = SlotTableKt.asLinkBufferSlotTable(from.getSlotStorage());
                        }
                        SlotTable fromTable2 = slotTableAsLinkBufferSlotTable;
                        int fromAddress = (resolvedState2 == null || (slotStorage = resolvedState2.getSlotStorage()) == null || (slotTableAsLinkBufferSlotTable2 = SlotTableKt.asLinkBufferSlotTable(slotStorage)) == null) ? LinkAnchorKt.asLinkAnchor(from.getAnchor()).getAddress() : slotTableAsLinkBufferSlotTable2.getRoot();
                        List<? extends Object> listCollectNodesFrom = LinkComposerKt.collectNodesFrom(fromTable2, fromAddress);
                        if (listCollectNodesFrom.isEmpty()) {
                            fromTable = fromTable2;
                            newChangeList$iv = newChangeList$iv2;
                            $i$f$withChangeList = $i$f$withChangeList2;
                        } else {
                            try {
                                linkComposer.changeListWriter.copyNodesToNewAnchorLocation(listCollectNodesFrom, effectiveNodeIndex);
                                if (Intrinsics.areEqual(to.getSlotStorage(), linkComposer.slotTable)) {
                                    int $this$toGroupHandle$iv2 = LinkAnchorKt.asLinkAnchor(to.getAnchor()).getAddress();
                                    fromTable = fromTable2;
                                    int groupContext$iv$iv = UInt.m9024constructorimpl($this$toGroupHandle$iv2);
                                    long j2 = (((long) 0) << 32) | (((long) groupContext$iv$iv) & 4294967295L);
                                    int $this$toGroupHandle$iv3 = LinkAnchorKt.asLinkAnchor(to.getAnchor()).getAddress();
                                    newChangeList$iv = newChangeList$iv2;
                                    $i$f$withChangeList = $i$f$withChangeList2;
                                    long j3 = ((long) 0) << 32;
                                    try {
                                        int groupContext$iv$iv2 = UInt.m9024constructorimpl($this$toGroupHandle$iv3);
                                        linkComposer.updateChildNodeCount(j2, linkComposer.updatedNodeCount(j3 | (((long) groupContext$iv$iv2) & 4294967295L)) + listCollectNodesFrom.size());
                                    } catch (Throwable th11) {
                                        th = th11;
                                        this_$iv = this_$iv5;
                                        this_$iv.setChangeList(previousChangeList$iv2);
                                        throw th;
                                    }
                                } else {
                                    fromTable = fromTable2;
                                    newChangeList$iv = newChangeList$iv2;
                                    $i$f$withChangeList = $i$f$withChangeList2;
                                }
                            } catch (Throwable th12) {
                                th = th12;
                                this_$iv = this_$iv5;
                            }
                        }
                        try {
                            linkComposer.changeListWriter.copySlotTableToAnchorLocation(resolvedState2, linkComposer.parentContext, from, to);
                            SlotTableReader $this$read_u24lambda_u240$iv2 = fromTable.openReader();
                            try {
                                SlotTableReader savedReader$iv2 = this.reader;
                                MutableIntIntMap savedCountOverrides$iv2 = this.nodeCountOverrides;
                                MutableIntObjectMap<PersistentCompositionLocalMap> mutableIntObjectMap2 = this.providerUpdates;
                                this.nodeCountOverrides = null;
                                this.providerUpdates = null;
                                try {
                                    this.reader = $this$read_u24lambda_u240$iv2;
                                    linkComposer.reader.reposition(fromAddress);
                                    ChangeList offsetChanges2 = new ChangeList();
                                    ComposerChangeListWriter this_$iv6 = linkComposer.changeListWriter;
                                    ChangeList previousChangeList$iv3 = this_$iv6.getChangeList();
                                    try {
                                        this_$iv6.setChangeList(offsetChanges2);
                                        ComposerChangeListWriter this_$iv7 = linkComposer.changeListWriter;
                                        boolean previousImplicitRootStart$iv2 = this_$iv7.getImplicitRootStart();
                                        try {
                                            this_$iv7.setImplicitRootStart(false);
                                            this_$iv4 = linkComposer.changeListWriter;
                                            long relativeStart$iv = linkComposer.reader.handle();
                                            $i$f$fastForEach = $i$f$fastForEach2;
                                            i2 = i3;
                                            list = list2;
                                            try {
                                                this_$iv4.editorCurrentPosition = relativeStart$iv;
                                                ComposerChangeListWriterAddressMode newMode$iv$iv = ComposerChangeListWriterAddressMode.RelativeAddressing;
                                                previousMode$iv$iv = this_$iv4.getAddressMode();
                                                previousCurrentPosition$iv$iv = this_$iv4.editorCurrentPosition;
                                                this_$iv4.setAddressMode$runtime(newMode$iv$iv);
                                                try {
                                                    from.transferPendingInvalidations$runtime();
                                                    try {
                                                        try {
                                                            try {
                                                                try {
                                                                    resolvedState = resolvedState2;
                                                                    this_$iv3 = this;
                                                                } catch (Throwable th13) {
                                                                    th = th13;
                                                                    savedReader$iv = savedReader$iv2;
                                                                    savedCountOverrides$iv = savedCountOverrides$iv2;
                                                                    previousImplicitRootStart$iv = previousImplicitRootStart$iv2;
                                                                    previousMode$iv$iv2 = previousMode$iv$iv;
                                                                    resolvedState = resolvedState2;
                                                                    mutableIntObjectMap = mutableIntObjectMap2;
                                                                    this_$iv3 = this;
                                                                    previousChangeList$iv = previousChangeList$iv3;
                                                                }
                                                                try {
                                                                    linkComposer.recomposeMovableContent(from.getComposition(), to.getComposition(), linkComposer.reader.getCurrentGroup(), from.getInvalidations$runtime(), new Function0() { // from class: androidx.compose.runtime.LinkComposer$$ExternalSyntheticLambda2
                                                                        @Override // kotlin.jvm.functions.Function0
                                                                        public final Object invoke() {
                                                                            return LinkComposer.insertMovableContentGuarded$lambda$0$0$1$0$0$0$0$0(this.f$0, to);
                                                                        }
                                                                    });
                                                                } catch (Throwable th14) {
                                                                    th = th14;
                                                                    savedReader$iv = savedReader$iv2;
                                                                    savedCountOverrides$iv = savedCountOverrides$iv2;
                                                                    previousChangeList$iv = previousChangeList$iv3;
                                                                    previousImplicitRootStart$iv = previousImplicitRootStart$iv2;
                                                                    previousMode$iv$iv2 = previousMode$iv$iv;
                                                                    mutableIntObjectMap = mutableIntObjectMap2;
                                                                    try {
                                                                        this_$iv4.setAddressMode$runtime(previousMode$iv$iv2);
                                                                        th = th;
                                                                        if (previousMode$iv$iv2 == ComposerChangeListWriterAddressMode.RelativeAddressing) {
                                                                            this_$iv2 = this_$iv5;
                                                                            j = previousCurrentPosition$iv$iv;
                                                                        } else {
                                                                            this_$iv2 = this_$iv5;
                                                                            j = -1;
                                                                        }
                                                                    } catch (Throwable th15) {
                                                                        th = th15;
                                                                        this_$iv2 = this_$iv5;
                                                                    }
                                                                    try {
                                                                        this_$iv4.editorCurrentPosition = j;
                                                                        throw th;
                                                                    } catch (Throwable th16) {
                                                                        th = th16;
                                                                        try {
                                                                            this_$iv7.setImplicitRootStart(previousImplicitRootStart$iv);
                                                                            throw th;
                                                                        } catch (Throwable th17) {
                                                                            th = th17;
                                                                            try {
                                                                                this_$iv6.setChangeList(previousChangeList$iv);
                                                                                throw th;
                                                                            } catch (Throwable th18) {
                                                                                th = th18;
                                                                                try {
                                                                                    this_$iv3.reader = savedReader$iv;
                                                                                    this_$iv3.nodeCountOverrides = savedCountOverrides$iv;
                                                                                    this_$iv3.providerUpdates = mutableIntObjectMap;
                                                                                    throw th;
                                                                                } catch (Throwable th19) {
                                                                                    th = th19;
                                                                                    try {
                                                                                        $this$read_u24lambda_u240$iv2.close();
                                                                                        throw th;
                                                                                    } catch (Throwable th20) {
                                                                                        th = th20;
                                                                                        this_$iv = this_$iv2;
                                                                                        this_$iv.setChangeList(previousChangeList$iv2);
                                                                                        throw th;
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            } catch (Throwable th21) {
                                                                th = th21;
                                                                savedReader$iv = savedReader$iv2;
                                                                savedCountOverrides$iv = savedCountOverrides$iv2;
                                                                previousImplicitRootStart$iv = previousImplicitRootStart$iv2;
                                                                previousMode$iv$iv2 = previousMode$iv$iv;
                                                                resolvedState = resolvedState2;
                                                                mutableIntObjectMap = mutableIntObjectMap2;
                                                                this_$iv3 = this;
                                                                previousChangeList$iv = previousChangeList$iv3;
                                                            }
                                                        } catch (Throwable th22) {
                                                            th = th22;
                                                            savedReader$iv = savedReader$iv2;
                                                            savedCountOverrides$iv = savedCountOverrides$iv2;
                                                            previousImplicitRootStart$iv = previousImplicitRootStart$iv2;
                                                            previousMode$iv$iv2 = previousMode$iv$iv;
                                                            resolvedState = resolvedState2;
                                                            mutableIntObjectMap = mutableIntObjectMap2;
                                                            this_$iv3 = this;
                                                            previousChangeList$iv = previousChangeList$iv3;
                                                        }
                                                    } catch (Throwable th23) {
                                                        th = th23;
                                                        savedReader$iv = savedReader$iv2;
                                                        savedCountOverrides$iv = savedCountOverrides$iv2;
                                                        previousChangeList$iv = previousChangeList$iv3;
                                                        previousMode$iv$iv2 = previousMode$iv$iv;
                                                        resolvedState = resolvedState2;
                                                        mutableIntObjectMap = mutableIntObjectMap2;
                                                        this_$iv3 = this;
                                                        previousImplicitRootStart$iv = previousImplicitRootStart$iv2;
                                                    }
                                                } catch (Throwable th24) {
                                                    th = th24;
                                                    savedReader$iv = savedReader$iv2;
                                                    savedCountOverrides$iv = savedCountOverrides$iv2;
                                                    previousChangeList$iv = previousChangeList$iv3;
                                                    resolvedState = resolvedState2;
                                                    this_$iv3 = this;
                                                    mutableIntObjectMap = mutableIntObjectMap2;
                                                    previousImplicitRootStart$iv = previousImplicitRootStart$iv2;
                                                    previousMode$iv$iv2 = previousMode$iv$iv;
                                                }
                                            } catch (Throwable th25) {
                                                th = th25;
                                                this_$iv3 = this;
                                                savedReader$iv = savedReader$iv2;
                                                savedCountOverrides$iv = savedCountOverrides$iv2;
                                                previousChangeList$iv = previousChangeList$iv3;
                                                previousImplicitRootStart$iv = previousImplicitRootStart$iv2;
                                                mutableIntObjectMap = mutableIntObjectMap2;
                                                this_$iv2 = this_$iv5;
                                            }
                                        } catch (Throwable th26) {
                                            th = th26;
                                            savedReader$iv = savedReader$iv2;
                                            savedCountOverrides$iv = savedCountOverrides$iv2;
                                            previousChangeList$iv = previousChangeList$iv3;
                                            this_$iv3 = this;
                                            mutableIntObjectMap = mutableIntObjectMap2;
                                            previousImplicitRootStart$iv = previousImplicitRootStart$iv2;
                                            this_$iv2 = this_$iv5;
                                        }
                                        try {
                                            this_$iv4.setAddressMode$runtime(previousMode$iv$iv);
                                            this_$iv4.editorCurrentPosition = previousMode$iv$iv == ComposerChangeListWriterAddressMode.RelativeAddressing ? previousCurrentPosition$iv$iv : -1L;
                                            try {
                                                this_$iv7.setImplicitRootStart(previousImplicitRootStart$iv2);
                                                try {
                                                    this_$iv6.setChangeList(previousChangeList$iv3);
                                                    linkComposer.changeListWriter.includeOperationsIn(offsetChanges2, effectiveNodeIndex);
                                                    Unit unit2 = Unit.INSTANCE;
                                                    try {
                                                        this_$iv3.reader = savedReader$iv2;
                                                        this_$iv3.nodeCountOverrides = savedCountOverrides$iv2;
                                                        this_$iv3.providerUpdates = mutableIntObjectMap2;
                                                        Unit unit3 = Unit.INSTANCE;
                                                        $this$read_u24lambda_u240$iv2.close();
                                                        linkComposer.changeListWriter.disposeResolvedMovableState(resolvedState);
                                                        index$iv2 = index$iv + 1;
                                                        size = i;
                                                        newChangeList$iv2 = newChangeList$iv;
                                                        $i$f$withChangeList2 = $i$f$withChangeList;
                                                        $i$f$fastForEach2 = $i$f$fastForEach;
                                                        i3 = i2;
                                                        list2 = list;
                                                    } catch (Throwable th27) {
                                                        th = th27;
                                                        this_$iv2 = this_$iv5;
                                                        $this$read_u24lambda_u240$iv2.close();
                                                        throw th;
                                                    }
                                                } catch (Throwable th28) {
                                                    th = th28;
                                                    savedReader$iv = savedReader$iv2;
                                                    savedCountOverrides$iv = savedCountOverrides$iv2;
                                                    this_$iv2 = this_$iv5;
                                                    mutableIntObjectMap = mutableIntObjectMap2;
                                                    this_$iv3.reader = savedReader$iv;
                                                    this_$iv3.nodeCountOverrides = savedCountOverrides$iv;
                                                    this_$iv3.providerUpdates = mutableIntObjectMap;
                                                    throw th;
                                                }
                                            } catch (Throwable th29) {
                                                th = th29;
                                                savedReader$iv = savedReader$iv2;
                                                previousChangeList$iv = previousChangeList$iv3;
                                                this_$iv2 = this_$iv5;
                                                mutableIntObjectMap = mutableIntObjectMap2;
                                                savedCountOverrides$iv = savedCountOverrides$iv2;
                                                this_$iv6.setChangeList(previousChangeList$iv);
                                                throw th;
                                            }
                                        } catch (Throwable th30) {
                                            th = th30;
                                            savedReader$iv = savedReader$iv2;
                                            previousChangeList$iv = previousChangeList$iv3;
                                            previousImplicitRootStart$iv = previousImplicitRootStart$iv2;
                                            this_$iv2 = this_$iv5;
                                            mutableIntObjectMap = mutableIntObjectMap2;
                                            savedCountOverrides$iv = savedCountOverrides$iv2;
                                            this_$iv7.setImplicitRootStart(previousImplicitRootStart$iv);
                                            throw th;
                                        }
                                    } catch (Throwable th31) {
                                        th = th31;
                                        savedReader$iv = savedReader$iv2;
                                        savedCountOverrides$iv = savedCountOverrides$iv2;
                                        previousChangeList$iv = previousChangeList$iv3;
                                        this_$iv3 = this;
                                        mutableIntObjectMap = mutableIntObjectMap2;
                                        this_$iv2 = this_$iv5;
                                    }
                                } catch (Throwable th32) {
                                    th = th32;
                                    savedReader$iv = savedReader$iv2;
                                    savedCountOverrides$iv = savedCountOverrides$iv2;
                                    this_$iv3 = this;
                                    mutableIntObjectMap = mutableIntObjectMap2;
                                    this_$iv2 = this_$iv5;
                                }
                            } catch (Throwable th33) {
                                th = th33;
                                this_$iv2 = this_$iv5;
                            }
                        } catch (Throwable th34) {
                            th = th34;
                            this_$iv = this_$iv5;
                        }
                    }
                } catch (Throwable th35) {
                    th = th35;
                    this_$iv = this_$iv5;
                    this_$iv.setChangeList(previousChangeList$iv2);
                    throw th;
                }
            }
        } catch (Throwable th36) {
            th = th36;
            this_$iv = this_$iv5;
        }
        try {
            linkComposer.resetInsertBuilder(false);
            linkComposer.changeListWriter.endMovableContentPlacement();
            this_$iv5.setChangeList(previousChangeList$iv2);
        } catch (Throwable th37) {
            th = th37;
            this_$iv = this_$iv5;
            this_$iv.setChangeList(previousChangeList$iv2);
            throw th;
        }
    }

    public static final Unit insertMovableContentGuarded$lambda$0$0$0$0(LinkComposer this$0, ChangeList $offsetChanges, SlotTableReader $this_read, long $handle, MovableContentStateReference $to) throws Throwable {
        ComposerChangeListWriter this_$iv;
        boolean previousImplicitRootStart$iv;
        boolean previousImplicitRootStart$iv2;
        ComposerChangeListWriterAddressMode newMode$iv$iv;
        Throwable th;
        long j;
        ComposerChangeListWriter this_$iv2 = this$0.changeListWriter;
        ChangeList previousChangeList$iv = this_$iv2.getChangeList();
        try {
            try {
                this_$iv2.setChangeList($offsetChanges);
                SlotTableReader savedReader$iv = this$0.reader;
                MutableIntIntMap savedCountOverrides$iv = this$0.nodeCountOverrides;
                MutableIntObjectMap<PersistentCompositionLocalMap> mutableIntObjectMap = this$0.providerUpdates;
                this$0.nodeCountOverrides = null;
                this$0.providerUpdates = null;
                try {
                    this$0.reader = $this_read;
                    this_$iv = this$0.changeListWriter;
                    previousImplicitRootStart$iv = this_$iv.getImplicitRootStart();
                } catch (Throwable th2) {
                    th = th2;
                }
                try {
                    try {
                        this_$iv.setImplicitRootStart(false);
                        ComposerChangeListWriter this_$iv3 = this$0.changeListWriter;
                        try {
                            this_$iv3.editorCurrentPosition = $handle;
                            ComposerChangeListWriterAddressMode newMode$iv$iv2 = ComposerChangeListWriterAddressMode.RelativeAddressing;
                            ComposerChangeListWriterAddressMode previousMode$iv$iv = this_$iv3.getAddressMode();
                            long previousCurrentPosition$iv$iv = this_$iv3.editorCurrentPosition;
                            this_$iv3.setAddressMode$runtime(newMode$iv$iv2);
                            try {
                                try {
                                    try {
                                    } catch (Throwable th3) {
                                        th = th3;
                                        previousImplicitRootStart$iv2 = previousImplicitRootStart$iv;
                                        newMode$iv$iv = previousMode$iv$iv;
                                    }
                                } catch (Throwable th4) {
                                    th = th4;
                                    previousImplicitRootStart$iv2 = previousImplicitRootStart$iv;
                                    newMode$iv$iv = previousMode$iv$iv;
                                }
                            } catch (Throwable th5) {
                                th = th5;
                                previousImplicitRootStart$iv2 = previousImplicitRootStart$iv;
                                newMode$iv$iv = previousMode$iv$iv;
                            }
                            try {
                                this$0.invokeMovableContentLambda($to.getContent$runtime(), $to.getLocals(), $to.getParameter(), true);
                                try {
                                    this_$iv3.setAddressMode$runtime(previousMode$iv$iv);
                                    this_$iv3.editorCurrentPosition = previousMode$iv$iv == ComposerChangeListWriterAddressMode.RelativeAddressing ? previousCurrentPosition$iv$iv : -1L;
                                    this_$iv.setImplicitRootStart(previousImplicitRootStart$iv);
                                    Unit unit = Unit.INSTANCE;
                                    this$0.reader = savedReader$iv;
                                    this$0.nodeCountOverrides = savedCountOverrides$iv;
                                    this$0.providerUpdates = mutableIntObjectMap;
                                    this_$iv2.setChangeList(previousChangeList$iv);
                                    return Unit.INSTANCE;
                                } catch (Throwable th6) {
                                    th = th6;
                                    previousImplicitRootStart$iv2 = previousImplicitRootStart$iv;
                                    this_$iv.setImplicitRootStart(previousImplicitRootStart$iv2);
                                    throw th;
                                }
                            } catch (Throwable th7) {
                                th = th7;
                                previousImplicitRootStart$iv2 = previousImplicitRootStart$iv;
                                newMode$iv$iv = previousMode$iv$iv;
                                try {
                                    this_$iv3.setAddressMode$runtime(newMode$iv$iv);
                                    if (newMode$iv$iv == ComposerChangeListWriterAddressMode.RelativeAddressing) {
                                        th = th;
                                        j = previousCurrentPosition$iv$iv;
                                    } else {
                                        th = th;
                                        j = -1;
                                    }
                                    this_$iv3.editorCurrentPosition = j;
                                    throw th;
                                } catch (Throwable th8) {
                                    th = th8;
                                    this_$iv.setImplicitRootStart(previousImplicitRootStart$iv2);
                                    throw th;
                                }
                            }
                        } catch (Throwable th9) {
                            th = th9;
                            previousImplicitRootStart$iv2 = previousImplicitRootStart$iv;
                        }
                    } catch (Throwable th10) {
                        th = th10;
                        previousImplicitRootStart$iv2 = previousImplicitRootStart$iv;
                    }
                } catch (Throwable th11) {
                    th = th11;
                    this$0.reader = savedReader$iv;
                    this$0.nodeCountOverrides = savedCountOverrides$iv;
                    this$0.providerUpdates = mutableIntObjectMap;
                    throw th;
                }
            } catch (Throwable th12) {
                th = th12;
                this_$iv2.setChangeList(previousChangeList$iv);
                throw th;
            }
        } catch (Throwable th13) {
            th = th13;
            this_$iv2.setChangeList(previousChangeList$iv);
            throw th;
        }
    }

    public static final Unit insertMovableContentGuarded$lambda$0$0$1$0$0$0$0$0(LinkComposer this$0, MovableContentStateReference $to) {
        this$0.invokeMovableContentLambda($to.getContent$runtime(), $to.getLocals(), $to.getParameter(), true);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ Object recomposeMovableContent$default(LinkComposer linkComposer, ControlledComposition controlledComposition, ControlledComposition controlledComposition2, int i, List list, Function0 function0, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            controlledComposition = null;
        }
        if ((i2 & 2) != 0) {
            controlledComposition2 = null;
        }
        if ((i2 & 4) != 0) {
            i = -1;
        }
        if ((i2 & 8) != 0) {
            list = CollectionsKt.emptyList();
        }
        return linkComposer.recomposeMovableContent(controlledComposition, controlledComposition2, i, list, function0);
    }

    private final <R> R recomposeMovableContent(ControlledComposition controlledComposition, ControlledComposition controlledComposition2, int address, List<? extends Pair<RecomposeScopeImpl, ? extends Object>> invalidations, Function0<? extends R> block) throws Throwable {
        Function0<? extends R> function0;
        R rInvoke;
        boolean isComposing = getIsComposing();
        int i = this.nodeIndex;
        try {
            this.isComposing = true;
            this.nodeIndex = 0;
            int size = invalidations.size();
            for (int i2 = 0; i2 < size; i2++) {
                Pair<RecomposeScopeImpl, ? extends Object> pair = invalidations.get(i2);
                RecomposeScopeImpl recomposeScopeImplComponent1 = pair.component1();
                Object objComponent2 = pair.component2();
                if (objComponent2 != null) {
                    tryImminentInvalidation$runtime(recomposeScopeImplComponent1, objComponent2);
                } else {
                    tryImminentInvalidation$runtime(recomposeScopeImplComponent1, null);
                }
            }
            if (controlledComposition != null) {
                function0 = block;
                try {
                    rInvoke = (R) controlledComposition.delegateInvalidations(controlledComposition2, address, function0);
                    if (rInvoke == null) {
                    }
                    this.isComposing = isComposing;
                    this.nodeIndex = i;
                    return rInvoke;
                } catch (Throwable th) {
                    th = th;
                    this.isComposing = isComposing;
                    this.nodeIndex = i;
                    throw th;
                }
            }
            function0 = block;
            rInvoke = function0.invoke();
            this.isComposing = isComposing;
            this.nodeIndex = i;
            return rInvoke;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* JADX WARN: Finally extract failed */
    private final void invokeMovableContentLambda(final MovableContent<Object> content, PersistentCompositionLocalMap locals, final Object parameter, boolean force) {
        startMovableGroup(MovableContentKt.movableContentKey, content);
        updateSlot(parameter);
        long savedCompositeKeyHash = getCompositeKeyHashCode();
        try {
            this.compositeKeyHashCode = MovableContentKt.movableContentKey;
            if (getInserting()) {
                this.builder.addFlags(GroupFlagsKt.IsMovableContentFlag);
            }
            boolean z = false;
            if (!getInserting() && !Intrinsics.areEqual(this.reader.getGroupAux(), locals)) {
                z = true;
            }
            boolean providersChanged = z;
            if (providersChanged) {
                recordProviderUpdate(locals);
            }
            m4402startAzEfcrM(ComposerKt.compositionLocalMapKey, ComposerKt.getCompositionLocalMap(), GroupKind.INSTANCE.m4502getGroup9udXigM(), locals);
            this.providerCache = null;
            if (!getInserting() || force) {
                boolean savedProvidersInvalid = this.providersInvalid;
                this.providersInvalid = providersChanged;
                this.changeListWriter.seekTo(this.reader.handle(), true);
                ComposerChangeListWriter this_$iv = this.changeListWriter;
                this_$iv.editorCurrentPosition = -1L;
                ComposerChangeListWriterAddressMode newMode$iv$iv = ComposerChangeListWriterAddressMode.AnchorAddressing;
                ComposerChangeListWriterAddressMode previousMode$iv$iv = this_$iv.getAddressMode();
                long previousCurrentPosition$iv$iv = this_$iv.editorCurrentPosition;
                this_$iv.setAddressMode$runtime(newMode$iv$iv);
                try {
                    Expect_jvmKt.invokeComposable(this, ComposableLambdaKt.composableLambdaInstance(-1241221479, true, new Function2() { // from class: androidx.compose.runtime.LinkComposer$$ExternalSyntheticLambda4
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj, Object obj2) {
                            return LinkComposer.invokeMovableContentLambda$lambda$0$0(content, parameter, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    }));
                    this_$iv.setAddressMode$runtime(previousMode$iv$iv);
                    this_$iv.editorCurrentPosition = previousMode$iv$iv == ComposerChangeListWriterAddressMode.RelativeAddressing ? previousCurrentPosition$iv$iv : -1L;
                    this.providersInvalid = savedProvidersInvalid;
                } catch (Throwable th) {
                    this_$iv.setAddressMode$runtime(previousMode$iv$iv);
                    this_$iv.editorCurrentPosition = previousMode$iv$iv == ComposerChangeListWriterAddressMode.RelativeAddressing ? previousCurrentPosition$iv$iv : -1L;
                    throw th;
                }
            } else {
                this.builderHasAProvider = true;
                int address = this.builder.parent(this.builder.getParent());
                LinkAnchor anchor = this.builder.getTable().getAddressSpace().anchorOfAddress(address);
                MovableContentStateReference reference = new MovableContentStateReference(content, parameter, getComposition(), this.builder.getTable(), anchor, CollectionsKt.emptyList(), currentCompositionLocalScope(), null);
                this.parentContext.insertMovableContent$runtime(reference);
            }
        } finally {
        }
    }

    public static final Unit invokeMovableContentLambda$lambda$0$0(MovableContent $content, Object $parameter, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C2031@81096L18:LinkComposer.kt#9igjgp");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1241221479, $changed, -1, "androidx.compose.runtime.LinkComposer.invokeMovableContentLambda.<anonymous>.<anonymous> (LinkComposer.kt:2031)");
            }
            $content.getContent().invoke($parameter, $composer, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    private final boolean isGroupAfterCurrentReaderPosition(long group) {
        long readerPosition = this.reader.handle();
        return readerPosition == -1 || LinkComposerKt.firstGroupInTopologicalOrder(getReaderTable$runtime(), group, readerPosition) == readerPosition;
    }

    /* JADX WARN: Removed duplicated region for block: B:202:0x0318  */
    /* JADX WARN: Removed duplicated region for block: B:203:0x031f  */
    /* JADX WARN: Removed duplicated region for block: B:206:0x0346  */
    /* JADX WARN: Removed duplicated region for block: B:211:0x0355  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void recomposeToGroupEnd() throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 1434
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.LinkComposer.recomposeToGroupEnd():void");
    }

    private final void recordDelete() {
        reportFreeMovableContent(this.reader.handle());
        this.changeListWriter.removeGroup();
    }

    private final void recordInsert(long source) {
        boolean zIsEmpty = this.insertFixups.isEmpty();
        ComposerChangeListWriter composerChangeListWriter = this.changeListWriter;
        if (zIsEmpty) {
            composerChangeListWriter.insertSlots(this.builder.getTable(), source);
        } else {
            composerChangeListWriter.insertSlots(this.builder.getTable(), source, this.insertFixups);
            this.insertFixups = new FixupList();
        }
    }

    private final void recordProviderUpdate(PersistentCompositionLocalMap providers) {
        MutableIntObjectMap<PersistentCompositionLocalMap> mutableIntObjectMap = this.providerUpdates;
        if (mutableIntObjectMap == null) {
            LinkComposer $this$recordProviderUpdate_u24lambda_u240 = this;
            MutableIntObjectMap<PersistentCompositionLocalMap> mutableIntObjectMap2 = new MutableIntObjectMap<>(0, 1, null);
            $this$recordProviderUpdate_u24lambda_u240.providerUpdates = mutableIntObjectMap2;
            mutableIntObjectMap = mutableIntObjectMap2;
        }
        mutableIntObjectMap.set(this.reader.getCurrentGroup(), providers);
    }

    private final void reportAllMovableContent() {
        if (this.slotTable.containsFlags(GroupFlagsKt.HasMovableContentFlag)) {
            getComposition().updateMovingInvalidations$runtime();
            ChangeList changes = new ChangeList();
            setDeferredChanges$runtime(changes);
            SlotTableReader $this$read_u24lambda_u240$iv = this.slotTable.openReader();
            try {
                this.reader = $this$read_u24lambda_u240$iv;
                ComposerChangeListWriter this_$iv = this.changeListWriter;
                ChangeList previousChangeList$iv = this_$iv.getChangeList();
                try {
                    this_$iv.setChangeList(changes);
                    reportFreeMovableContent($this$read_u24lambda_u240$iv.rootHandle());
                    this_$iv.setChangeList(previousChangeList$iv);
                    Unit unit = Unit.INSTANCE;
                } catch (Throwable th) {
                    this_$iv.setChangeList(previousChangeList$iv);
                    throw th;
                }
            } finally {
                $this$read_u24lambda_u240$iv.close();
            }
        }
    }

    private static final MovableContentStateReference reportFreeMovableContent$createMovableContentReferenceForGroup(LinkComposer this$0, int group, List<MovableContentStateReference> list) {
        Object objGroupObjectKey = this$0.reader.groupObjectKey(group);
        Intrinsics.checkNotNull(objGroupObjectKey, "null cannot be cast to non-null type androidx.compose.runtime.MovableContent<kotlin.Any?>");
        MovableContent movableContent = (MovableContent) objGroupObjectKey;
        Object parameter = this$0.reader.get(group, 0);
        List<Pair<RecomposeScopeImpl, Object>> listM4403findInvalidationsVpaz1Sg = LinkComposerKt.m4403findInvalidationsVpaz1Sg(this$0.reader, group, this$0.invalidations);
        LinkAnchor anchor = this$0.getReaderTable$runtime().getAddressSpace().anchorOfAddress(group);
        MovableContentStateReference reference = new MovableContentStateReference(movableContent, parameter, this$0.getComposition(), this$0.getReaderTable$runtime(), anchor, listM4403findInvalidationsVpaz1Sg, this$0.currentCompositionLocalScope(group), list);
        return reference;
    }

    /* JADX WARN: Removed duplicated region for block: B:112:0x0091  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static final androidx.compose.runtime.MovableContentStateReference reportFreeMovableContent$movableContentReferenceFor(androidx.compose.runtime.LinkComposer r19, int r20) {
        /*
            r0 = r19
            r1 = r20
            androidx.compose.runtime.composer.linkbuffer.SlotTableReader r2 = r0.reader
            int r2 = r2.flagsOf(r1)
            r3 = 268435456(0x10000000, float:2.524355E-29)
            r4 = r2
            r5 = 0
            r6 = r3 & r4
            if (r6 != r3) goto L14
            r3 = 1
            goto L15
        L14:
            r3 = 0
        L15:
            r4 = 0
            if (r3 == 0) goto Lb8
            r3 = 536870912(0x20000000, float:1.0842022E-19)
            r5 = r2
            r6 = 0
            r9 = r3 & r5
            if (r9 != r3) goto L22
            r3 = 1
            goto L23
        L22:
            r3 = 0
        L23:
            if (r3 == 0) goto Laf
            java.util.List r3 = kotlin.collections.CollectionsKt.createListBuilder()
            r4 = r3
            r5 = 0
            androidx.compose.runtime.composer.linkbuffer.SlotTableReader r6 = r0.reader
            r9 = r20
            r10 = 0
            int r11 = r6.firstChildOf(r9)
        L35:
            r12 = -1
            if (r11 == r12) goto La7
            r13 = r11
            r14 = 0
            androidx.compose.runtime.composer.linkbuffer.SlotTableReader r15 = r0.reader
            int r15 = r15.flagsOf(r13)
            r7 = 268435456(0x10000000, float:2.524355E-29)
            r16 = 0
            r8 = r7 & r15
            if (r8 != r7) goto L4a
            r7 = 1
            goto L4b
        L4a:
            r7 = 0
        L4b:
            if (r7 == 0) goto L59
            androidx.compose.runtime.MovableContentStateReference r7 = reportFreeMovableContent$movableContentReferenceFor(r0, r13)
            if (r7 == 0) goto L57
            r8 = 0
            r4.add(r7)
        L57:
            r7 = 1
            goto L5a
        L59:
            r7 = 0
        L5a:
            int r8 = r6.firstChildOf(r11)
            if (r7 != 0) goto L7f
            if (r8 == r12) goto L7f
            r13 = r11
            r14 = 0
            androidx.compose.runtime.composer.linkbuffer.SlotTableReader r15 = r0.reader
            int r15 = r15.flagsOf(r13)
            r12 = 536870912(0x20000000, float:1.0842022E-19)
            r17 = 0
            r18 = r2
            r2 = r12 & r15
            if (r2 != r12) goto L77
            r2 = 1
            goto L78
        L77:
            r2 = 0
        L78:
            if (r2 == 0) goto L81
            r11 = r8
            r2 = r18
            goto L35
        L7f:
            r18 = r2
        L81:
            r2 = -1
            if (r8 != r2) goto L89
            if (r7 != 0) goto L89
            r2 = r11
            r12 = 0
        L89:
            int r2 = r6.nextSiblingOf(r11)
        L8e:
            r12 = -1
            if (r2 != r12) goto La3
            int r11 = r6.parentOf(r11)
            if (r11 == r12) goto La9
            if (r11 != r9) goto L9a
            goto La9
        L9a:
            r13 = r11
            r14 = 0
            int r2 = r6.nextSiblingOf(r11)
            goto L8e
        La3:
            r11 = r2
            r2 = r18
            goto L35
        La7:
            r18 = r2
        La9:
            java.util.List r4 = kotlin.collections.CollectionsKt.build(r3)
            goto Lb1
        Laf:
            r18 = r2
        Lb1:
            androidx.compose.runtime.MovableContentStateReference r4 = reportFreeMovableContent$createMovableContentReferenceForGroup(r0, r1, r4)
            goto Lba
        Lb8:
            r18 = r2
        Lba:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.LinkComposer.reportFreeMovableContent$movableContentReferenceFor(androidx.compose.runtime.LinkComposer, int):androidx.compose.runtime.MovableContentStateReference");
    }

    /* JADX WARN: Removed duplicated region for block: B:183:0x011f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static final int reportFreeMovableContent$reportGroup(androidx.compose.runtime.LinkComposer r21, long r22, boolean r24, int r25) {
        /*
            Method dump skipped, instruction units count: 458
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.LinkComposer.reportFreeMovableContent$reportGroup(androidx.compose.runtime.LinkComposer, long, boolean, int):int");
    }

    private final void reportFreeMovableContent(long groupBeingRemoved) {
        int group = GroupHandleKt.getGroup(groupBeingRemoved);
        int $this$contains$iv = this.reader.flagsOf(group);
        boolean rootIsNode = (8388608 & $this$contains$iv) == 8388608;
        if (rootIsNode) {
            this.changeListWriter.endNodeMovement();
            this.changeListWriter.moveDown(this.reader.node(group));
        }
        reportFreeMovableContent$reportGroup(this, groupBeingRemoved, rootIsNode, 0);
        this.changeListWriter.endNodeMovement();
        if (rootIsNode) {
            this.changeListWriter.moveUp();
        }
    }

    private final void resetInsertBuilder(boolean dispose) {
        if (!this.builder.getIsClosed()) {
            SlotTable table = this.builder.build();
            if (dispose) {
                table.dispose();
            }
        }
        SlotTableBuilder it = new SlotTableBuilder(this.slotTable.getAddressSpace(), false, false);
        it.close();
        this.builder = it;
    }

    private final RecomposeScopeImpl requireRecomposeScope(int group) {
        Object slot = this.reader.get(group, 0);
        boolean value$iv = !Intrinsics.areEqual(slot, Composer.INSTANCE.getEmpty());
        if (!value$iv) {
            ComposerKt.composeImmediateRuntimeError("Cannot obtain RecomposeScope. Group does not have a corresponding slot.");
        }
        boolean value$iv2 = slot instanceof RecomposeScopeImpl;
        if (!value$iv2) {
            ComposerKt.composeImmediateRuntimeError("Expected a RecomposeScope in the first non-utility slot, found " + slot + '.');
        }
        return (RecomposeScopeImpl) slot;
    }

    private final boolean requiresRecomposition(int group) {
        return this.reader.hasRecomposeRequired(group);
    }

    private final int rGroupIndexOf(int group) {
        int eldestSibling;
        int result = 0;
        int groupParent = this.reader.parentOf(group);
        if (groupParent < 0) {
            eldestSibling = getReaderTable$runtime().getRoot();
        } else {
            eldestSibling = this.reader.firstChildOf(groupParent);
        }
        SlotTable this_$iv = getReaderTable$runtime();
        int group$iv = eldestSibling;
        SlotTableAddressSpace this_$iv$iv = this_$iv.getAddressSpace();
        int[] groups$iv$iv = this_$iv$iv.getGroups();
        int current$iv$iv = group$iv;
        while (current$iv$iv >= 0) {
            int predecessor = current$iv$iv;
            if (predecessor == group) {
                return result;
            }
            if (!this.reader.hasObjectKey(predecessor)) {
                result++;
            }
            int address$iv$iv$iv = current$iv$iv;
            current$iv$iv = groups$iv$iv[address$iv$iv$iv + 1];
        }
        return result;
    }

    private final void skipGroup() {
        this.groupNodeCount += this.reader.skipGroup();
    }

    private final void skipReaderToGroupEnd() {
        this.groupNodeCount = this.reader.getParentNodeCount();
        this.reader.skipToGroupEnd();
    }

    /* JADX INFO: renamed from: start-AzEfcrM */
    private final void m4402startAzEfcrM(int key, Object objectKey, int kind, Object data) {
        LinkComposer this_$iv$iv;
        int segment$iv$iv$iv;
        int other$iv$iv$iv$iv;
        int rGroupIndex$iv$iv;
        long $this$rol$iv$iv$iv$iv;
        LinkPending newPending;
        validateNodeNotExpected();
        int rGroupIndex$iv = this.rGroupIndex;
        if (objectKey == null) {
            if (data == null || key != 207 || Intrinsics.areEqual(data, Composer.INSTANCE.getEmpty())) {
                this_$iv$iv = this;
                long $this$compoundWith$iv$iv$iv = this_$iv$iv.getCompositeKeyHashCode();
                segment$iv$iv$iv = key;
                other$iv$iv$iv$iv = 3;
                rGroupIndex$iv$iv = rGroupIndex$iv;
                $this$rol$iv$iv$iv$iv = $this$compoundWith$iv$iv$iv;
                long $this$rol$iv$iv$iv$iv2 = Long.rotateLeft($this$rol$iv$iv$iv$iv, other$iv$iv$iv$iv);
                long $this$rol$iv$iv$iv$iv3 = segment$iv$iv$iv;
                long $this$compoundWith$iv$iv$iv2 = $this$rol$iv$iv$iv$iv2 ^ $this$rol$iv$iv$iv$iv3;
                int segment$iv$iv$iv2 = rGroupIndex$iv$iv;
                this_$iv$iv.compositeKeyHashCode = Long.rotateLeft($this$compoundWith$iv$iv$iv2, 3) ^ ((long) segment$iv$iv$iv2);
            } else {
                int groupKey$iv$iv = data.hashCode();
                long $this$compoundWith$iv$iv$iv3 = getCompositeKeyHashCode();
                long $this$rol$iv$iv$iv$iv4 = Long.rotateLeft($this$compoundWith$iv$iv$iv3, 3) ^ ((long) groupKey$iv$iv);
                long $this$rol$iv$iv$iv$iv5 = Long.rotateLeft($this$rol$iv$iv$iv$iv4, 3);
                long $this$rol$iv$iv$iv$iv6 = rGroupIndex$iv;
                this.compositeKeyHashCode = $this$rol$iv$iv$iv$iv5 ^ $this$rol$iv$iv$iv$iv6;
            }
        } else if (objectKey instanceof Enum) {
            int groupKey$iv$iv2 = ((Enum) objectKey).ordinal();
            this_$iv$iv = this;
            long $this$compoundWith$iv$iv$iv4 = this_$iv$iv.getCompositeKeyHashCode();
            segment$iv$iv$iv = groupKey$iv$iv2;
            other$iv$iv$iv$iv = 3;
            rGroupIndex$iv$iv = 0;
            $this$rol$iv$iv$iv$iv = $this$compoundWith$iv$iv$iv4;
            long $this$rol$iv$iv$iv$iv22 = Long.rotateLeft($this$rol$iv$iv$iv$iv, other$iv$iv$iv$iv);
            long $this$rol$iv$iv$iv$iv32 = segment$iv$iv$iv;
            long $this$compoundWith$iv$iv$iv22 = $this$rol$iv$iv$iv$iv22 ^ $this$rol$iv$iv$iv$iv32;
            int segment$iv$iv$iv22 = rGroupIndex$iv$iv;
            this_$iv$iv.compositeKeyHashCode = Long.rotateLeft($this$compoundWith$iv$iv$iv22, 3) ^ ((long) segment$iv$iv$iv22);
        } else {
            int groupKey$iv$iv3 = objectKey.hashCode();
            long $this$compoundWith$iv$iv$iv5 = getCompositeKeyHashCode();
            long $this$rol$iv$iv$iv$iv7 = Long.rotateLeft($this$compoundWith$iv$iv$iv5, 3);
            long $this$rol$iv$iv$iv$iv8 = groupKey$iv$iv3;
            long $this$compoundWith$iv$iv$iv6 = $this$rol$iv$iv$iv$iv7 ^ $this$rol$iv$iv$iv$iv8;
            this.compositeKeyHashCode = Long.rotateLeft($this$compoundWith$iv$iv$iv6, 3) ^ ((long) 0);
        }
        if (objectKey == null) {
            this.rGroupIndex++;
        }
        boolean isNode = kind != GroupKind.INSTANCE.m4502getGroup9udXigM();
        if (getInserting()) {
            this.reader.beginEmpty();
            SlotTableBuilder builder = this.builder;
            if (isNode) {
                Object objectKey$iv = Composer.INSTANCE.getEmpty();
                Object node$iv = Composer.INSTANCE.getEmpty();
                builder.startNewGroup(key, objectKey$iv == Composer.INSTANCE.getEmpty() ? 8388608 : 25165824, objectKey$iv, null, node$iv);
            } else if (data != null) {
                Object objectKey$iv2 = objectKey == null ? Composer.INSTANCE.getEmpty() : objectKey;
                builder.startNewGroup(key, objectKey$iv2 == Composer.INSTANCE.getEmpty() ? GroupFlagsKt.HasAuxSlotFlag : 50331648, objectKey$iv2, data, null);
            } else {
                Object objectKey$iv3 = objectKey == null ? Composer.INSTANCE.getEmpty() : objectKey;
                builder.startNewGroup(key, objectKey$iv3 == Composer.INSTANCE.getEmpty() ? 0 : 16777216, objectKey$iv3, null, null);
            }
            LinkPending pending = this.pending;
            if (pending != null) {
                KeyInfo insertKeyInfo = new KeyInfo(key, -1, LinkComposerKt.toInsertAddress(builder.getParentHandle()), -1, 0);
                pending.registerInsert(insertKeyInfo, this.nodeIndex - pending.getStartIndex());
                pending.recordUsed(insertKeyInfo);
            }
            enterGroup(isNode, null);
            return;
        }
        boolean forceReplace = !(kind != GroupKind.INSTANCE.m4503getNode9udXigM()) && this.reusing;
        if (this.pending == null) {
            int slotKey = this.reader.getGroupKey();
            if (!forceReplace && slotKey == key && Intrinsics.areEqual(objectKey, this.reader.getGroupObjectKey())) {
                startReaderGroup(isNode, data);
            } else {
                this.pending = new LinkPending(this.reader.extractKeys(), this.nodeIndex);
            }
        }
        LinkPending pending2 = this.pending;
        if (pending2 != null) {
            KeyInfo keyInfo = pending2.getNext(key, objectKey);
            if (forceReplace || keyInfo == null) {
                this.reader.beginEmpty();
                this.inserting = true;
                this.providerCache = null;
                ensureBuilder();
                SlotTableBuilder builder2 = this.builder;
                if (isNode) {
                    Object objectKey$iv4 = Composer.INSTANCE.getEmpty();
                    Object node$iv2 = Composer.INSTANCE.getEmpty();
                    builder2.startNewGroup(key, objectKey$iv4 == Composer.INSTANCE.getEmpty() ? 8388608 : 25165824, objectKey$iv4, null, node$iv2);
                } else if (data != null) {
                    Object objectKey$iv5 = objectKey == null ? Composer.INSTANCE.getEmpty() : objectKey;
                    builder2.startNewGroup(key, objectKey$iv5 == Composer.INSTANCE.getEmpty() ? GroupFlagsKt.HasAuxSlotFlag : 50331648, objectKey$iv5, data, null);
                } else {
                    Object objectKey$iv6 = objectKey == null ? Composer.INSTANCE.getEmpty() : objectKey;
                    builder2.startNewGroup(key, objectKey$iv6 == Composer.INSTANCE.getEmpty() ? 0 : 16777216, objectKey$iv6, null, null);
                }
                KeyInfo insertKeyInfo2 = new KeyInfo(key, -1, LinkComposerKt.toInsertAddress(builder2.getParentHandle()), -1, 0);
                pending2.registerInsert(insertKeyInfo2, this.nodeIndex - pending2.getStartIndex());
                pending2.recordUsed(insertKeyInfo2);
                LinkPending newPending2 = new LinkPending(new ArrayList(), isNode ? 0 : this.nodeIndex);
                newPending = newPending2;
            } else {
                pending2.recordUsed(keyInfo);
                long location = keyInfo.getHandle();
                this.nodeIndex = pending2.nodePositionOf(keyInfo) + pending2.getStartIndex();
                int relativePosition = pending2.slotPositionOf(keyInfo);
                int currentRelativePosition = relativePosition - pending2.getGroupIndex();
                pending2.registerMoveSlot(relativePosition, pending2.getGroupIndex());
                if (currentRelativePosition > 0) {
                    this.reader.reposition(pending2.groupHandleOfNextUnmovedGroup());
                    this.changeListWriter.moveGroup(currentRelativePosition);
                }
                pending2.markGroupLocationReconciled(keyInfo.getIndex());
                this.reader.reposition(location);
                startReaderGroup(isNode, data);
                newPending = null;
            }
        } else {
            newPending = null;
        }
        enterGroup(isNode, newPending);
    }

    private final void startGroup(int key) {
        m4402startAzEfcrM(key, null, GroupKind.INSTANCE.m4502getGroup9udXigM(), null);
    }

    private final void startGroup(int key, Object dataKey) {
        m4402startAzEfcrM(key, dataKey, GroupKind.INSTANCE.m4502getGroup9udXigM(), null);
    }

    private final void startReaderGroup(boolean isNode, Object data) {
        if (isNode) {
            this.reader.startNode();
            return;
        }
        if (data != null && this.reader.getGroupAux() != data) {
            this.changeListWriter.updateAuxData(data);
        }
        this.reader.startGroup();
    }

    private final void startRoot() {
        PersistentCompositionLocalMap persistentCompositionLocalMapPutValue;
        this.rGroupIndex = 0;
        this.reader = this.slotTable.openReader();
        m4402startAzEfcrM(100, null, GroupKind.INSTANCE.m4502getGroup9udXigM(), null);
        this.parentContext.startComposing$runtime();
        PersistentCompositionLocalMap parentProvider = this.parentContext.getCompositionLocalScope$runtime();
        this.providersInvalidStack.push(LinkComposerKt.asInt(this.providersInvalid));
        this.providersInvalid = changed(parentProvider);
        this.providerCache = null;
        if (!this.forceRecomposeScopes) {
            this.forceRecomposeScopes = this.parentContext.getCollectingParameterInformation();
        }
        if (!getSourceMarkersEnabled()) {
            setSourceMarkersEnabled$runtime(this.parentContext.getCollectingSourceInformation());
        }
        if (getSourceMarkersEnabled()) {
            CompositionLocal<CompositionErrorContext> localCompositionErrorContext = CompositionErrorContextKt.getLocalCompositionErrorContext();
            Intrinsics.checkNotNull(localCompositionErrorContext, "null cannot be cast to non-null type androidx.compose.runtime.CompositionLocal<kotlin.Any?>");
            persistentCompositionLocalMapPutValue = parentProvider.putValue(localCompositionErrorContext, new StaticValueHolder(getErrorContext$runtime()));
        } else {
            persistentCompositionLocalMapPutValue = parentProvider;
        }
        this.rootProvider = persistentCompositionLocalMapPutValue;
        Set<CompositionData> set = (Set) CompositionLocalMapKt.read(this.rootProvider, InspectionTablesKt.getLocalInspectionTables());
        if (set != null) {
            set.add(getCompositionData());
            this.parentContext.recordInspectionTable$runtime(set);
        }
        int key$iv = Long.hashCode(this.parentContext.getCompositeKeyHashCode());
        m4402startAzEfcrM(key$iv, null, GroupKind.INSTANCE.m4502getGroup9udXigM(), null);
    }

    private final List<ComposeStackTraceFrame> stackTraceForGroup(int group, Integer dataOffset) {
        if (!getSourceMarkersEnabled()) {
            return CollectionsKt.emptyList();
        }
        SlotTable this_$iv = this.slotTable;
        SlotTableReader $this$read_u24lambda_u240$iv = this_$iv.openReader();
        try {
            return SlotTableReaderKt.traceForGroup($this$read_u24lambda_u240$iv, group, dataOffset);
        } finally {
            $this$read_u24lambda_u240$iv.close();
        }
    }

    public final void updateCachedValue$runtime(Object value) {
        Object toStore;
        if (value instanceof RememberObserver) {
            toStore = new LinkRememberObserverHolder((RememberObserver) value, getReaderTable$runtime().getAddressSpace().anchorOfAddress(this.lastPlacedChildGroup));
            if (getInserting()) {
                this.changeListWriter.remember((RememberObserverHolder) toStore);
            }
            this.abandonSet.add(value);
        } else {
            toStore = value;
        }
        updateValue(toStore);
    }

    private final void updateChildNodeCount(long virtualGroup, int count) {
        if (updatedNodeCount(virtualGroup) != count) {
            if (LinkComposerKt.isInsertHandle(virtualGroup)) {
                MutableIntIntMap virtualCounts = this.nodeCountVirtualOverrides;
                if (virtualCounts == null) {
                    LinkComposer $this$updateChildNodeCount_u24lambda_u240 = this;
                    MutableIntIntMap newCounts = new MutableIntIntMap(0, 1, null);
                    $this$updateChildNodeCount_u24lambda_u240.nodeCountVirtualOverrides = newCounts;
                    virtualCounts = newCounts;
                }
                virtualCounts.set(GroupHandleKt.getGroup(virtualGroup), count);
                return;
            }
            MutableIntIntMap nodeCounts = this.nodeCountOverrides;
            if (nodeCounts == null) {
                LinkComposer $this$updateChildNodeCount_u24lambda_u241 = this;
                MutableIntIntMap newCounts2 = new MutableIntIntMap(0, 1, null);
                $this$updateChildNodeCount_u24lambda_u241.nodeCountOverrides = newCounts2;
                nodeCounts = newCounts2;
            }
            boolean zIsInsertHandle = true ^ LinkComposerKt.isInsertHandle(virtualGroup);
            nodeCounts.set(GroupHandleKt.getGroup(virtualGroup), count);
        }
    }

    private final void updateNodeCountOverrides(long virtualHandle, int newCount) {
        LinkComposer linkComposer = this;
        int currentCount = updatedNodeCount(virtualHandle);
        if (currentCount != newCount) {
            int delta = newCount - currentCount;
            long current = virtualHandle;
            boolean z = true;
            int minPending = Stack.m4421getSizeimpl(linkComposer.pendingStack) - 1;
            while (GroupHandleKt.getGroup(current) != -1) {
                int newCurrentNodes = linkComposer.updatedNodeCount(current) + delta;
                linkComposer.updateChildNodeCount(current, newCurrentNodes);
                int pendingIndex = minPending;
                while (true) {
                    if (-1 < pendingIndex) {
                        LinkPending pending = (LinkPending) Stack.m4426peekimpl(linkComposer.pendingStack, pendingIndex);
                        if (pending != null && pending.updateNodeCount(GroupHandleKt.getGroup(current), newCurrentNodes)) {
                            minPending = pendingIndex - 1;
                            break;
                        }
                        pendingIndex--;
                    } else {
                        break;
                    }
                }
                if (LinkComposerKt.isInsertHandle(current)) {
                    current = linkComposer.reader.getParentHandle();
                } else {
                    int[] groups = linkComposer.getReaderTable$runtime().getAddressSpace().getGroups();
                    int group = GroupHandleKt.getGroup(current);
                    int address$iv = groups[group + 4];
                    if ((8388608 & address$iv) == 8388608 ? z : false) {
                        return;
                    }
                    int address$iv2 = groups[group + 2];
                    current = (((long) UInt.m9024constructorimpl(address$iv2)) & 4294967295L) | (((long) 0) << 32);
                    z = true;
                    linkComposer = this;
                }
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [androidx.compose.runtime.PersistentCompositionLocalMap, java.lang.Object] */
    private final PersistentCompositionLocalMap updateProviderMapGroup(PersistentCompositionLocalMap parentScope, PersistentCompositionLocalMap currentProviders) {
        PersistentMap.Builder<CompositionLocal<Object>, ValueHolder<Object>> builderBuilder2 = parentScope.builder2();
        PersistentMap.Builder<CompositionLocal<Object>, ValueHolder<Object>> it = builderBuilder2;
        it.putAll(currentProviders);
        ?? Build2 = builderBuilder2.build2();
        startGroup(ComposerKt.providerMapsKey, ComposerKt.getProviderMaps());
        updateSlot(Build2);
        updateSlot(currentProviders);
        endGroup();
        return Build2;
    }

    private final void updateSlot(Object value) {
        nextSlot$runtime();
        updateValue(value);
    }

    private final int updatedNodeCount(long virtualHandle) {
        int override;
        if (!LinkComposerKt.isInsertHandle(virtualHandle)) {
            boolean z = !LinkComposerKt.isInsertHandle(virtualHandle);
            int group = GroupHandleKt.getGroup(virtualHandle);
            MutableIntIntMap nodeCounts = this.nodeCountOverrides;
            if (nodeCounts != null && (override = nodeCounts.getOrDefault(group, -1)) >= 0) {
                return override;
            }
            int[] $this$groupFlags$iv = getReaderTable$runtime().getAddressSpace().getGroups();
            int flags$iv = $this$groupFlags$iv[group + 4];
            return flags$iv & GroupFlagsSpec.CHILD_NODE_COUNT_MASK;
        }
        MutableIntIntMap mutableIntIntMap = this.nodeCountVirtualOverrides;
        if (mutableIntIntMap != null) {
            return mutableIntIntMap.getOrDefault(GroupHandleKt.getGroup(virtualHandle), 0);
        }
        return 0;
    }

    private final Object unwrapRememberObserverHolder(Object $this$unwrapRememberObserverHolder) {
        return $this$unwrapRememberObserverHolder instanceof RememberObserverHolder ? ((RememberObserverHolder) $this$unwrapRememberObserverHolder).getWrapped() : $this$unwrapRememberObserverHolder;
    }

    private final <R> R withReader(SlotTableReader reader, Function0<? extends R> block) {
        SlotTableReader savedReader = this.reader;
        MutableIntIntMap savedCountOverrides = this.nodeCountOverrides;
        MutableIntObjectMap<PersistentCompositionLocalMap> mutableIntObjectMap = this.providerUpdates;
        this.nodeCountOverrides = null;
        this.providerUpdates = null;
        try {
            this.reader = reader;
            return block.invoke();
        } finally {
            this.reader = savedReader;
            this.nodeCountOverrides = savedCountOverrides;
            this.providerUpdates = mutableIntObjectMap;
        }
    }

    private final void enterRecomposeScope(RecomposeScopeImpl scope) {
        scope.start(this.compositionToken);
        CompositionObserver compositionObserverCurrent = this.observerHolder.current();
        if (compositionObserverCurrent != null) {
            compositionObserverCurrent.onScopeEnter(scope);
        }
    }

    private final Function1<Composition, Unit> exitRecomposeScope(RecomposeScopeImpl scope) {
        CompositionObserver compositionObserverCurrent = this.observerHolder.current();
        if (compositionObserverCurrent != null) {
            compositionObserverCurrent.onScopeExit(scope);
        }
        return scope.end(this.compositionToken);
    }

    private final void updateCompositeKeyWhenWeEnterGroup(int groupKey, int rGroupIndex, Object dataKey, Object data) {
        if (dataKey != null) {
            if (dataKey instanceof Enum) {
                int groupKey$iv = ((Enum) dataKey).ordinal();
                long $this$compoundWith$iv$iv = getCompositeKeyHashCode();
                this.compositeKeyHashCode = Long.rotateLeft(Long.rotateLeft($this$compoundWith$iv$iv, 3) ^ ((long) groupKey$iv), 3) ^ ((long) 0);
                return;
            }
            int groupKey$iv2 = dataKey.hashCode();
            long $this$compoundWith$iv$iv2 = getCompositeKeyHashCode();
            this.compositeKeyHashCode = Long.rotateLeft(Long.rotateLeft($this$compoundWith$iv$iv2, 3) ^ ((long) groupKey$iv2), 3) ^ ((long) 0);
            return;
        }
        if (data != null && groupKey == 207 && !Intrinsics.areEqual(data, Composer.INSTANCE.getEmpty())) {
            int groupKey$iv3 = data.hashCode();
            long $this$compoundWith$iv$iv3 = getCompositeKeyHashCode();
            this.compositeKeyHashCode = Long.rotateLeft(Long.rotateLeft($this$compoundWith$iv$iv3, 3) ^ ((long) groupKey$iv3), 3) ^ ((long) rGroupIndex);
            return;
        }
        long $this$compoundWith$iv$iv4 = getCompositeKeyHashCode();
        this.compositeKeyHashCode = Long.rotateLeft(Long.rotateLeft($this$compoundWith$iv$iv4, 3) ^ ((long) groupKey), 3) ^ ((long) rGroupIndex);
    }

    private final void updateCompositeKeyWhenWeEnterGroupKeyHash(int groupKey, int rGroupIndex) {
        long $this$compoundWith$iv = getCompositeKeyHashCode();
        this.compositeKeyHashCode = Long.rotateLeft(Long.rotateLeft($this$compoundWith$iv, 3) ^ ((long) groupKey), 3) ^ ((long) rGroupIndex);
    }

    private final void updateCompositeKeyWhenWeExitGroup(int groupKey, int rGroupIndex, Object dataKey, Object data) {
        if (dataKey != null) {
            if (dataKey instanceof Enum) {
                int groupKey$iv = ((Enum) dataKey).ordinal();
                long $this$unCompoundWith$iv$iv = getCompositeKeyHashCode();
                long $this$ror$iv$iv$iv = ((long) 0) ^ $this$unCompoundWith$iv$iv;
                long $this$unCompoundWith$iv$iv2 = Long.rotateRight($this$ror$iv$iv$iv, 3);
                long $this$ror$iv$iv$iv2 = $this$unCompoundWith$iv$iv2 ^ ((long) groupKey$iv);
                this.compositeKeyHashCode = Long.rotateRight($this$ror$iv$iv$iv2, 3);
                return;
            }
            int groupKey$iv2 = dataKey.hashCode();
            long $this$unCompoundWith$iv$iv3 = getCompositeKeyHashCode();
            long $this$ror$iv$iv$iv3 = ((long) 0) ^ $this$unCompoundWith$iv$iv3;
            long $this$unCompoundWith$iv$iv4 = Long.rotateRight($this$ror$iv$iv$iv3, 3);
            long $this$ror$iv$iv$iv4 = $this$unCompoundWith$iv$iv4 ^ ((long) groupKey$iv2);
            this.compositeKeyHashCode = Long.rotateRight($this$ror$iv$iv$iv4, 3);
            return;
        }
        if (data != null && groupKey == 207 && !Intrinsics.areEqual(data, Composer.INSTANCE.getEmpty())) {
            int groupKey$iv3 = data.hashCode();
            long $this$unCompoundWith$iv$iv5 = getCompositeKeyHashCode();
            long $this$ror$iv$iv$iv5 = ((long) rGroupIndex) ^ $this$unCompoundWith$iv$iv5;
            long $this$unCompoundWith$iv$iv6 = Long.rotateRight($this$ror$iv$iv$iv5, 3);
            long $this$ror$iv$iv$iv6 = $this$unCompoundWith$iv$iv6 ^ ((long) groupKey$iv3);
            this.compositeKeyHashCode = Long.rotateRight($this$ror$iv$iv$iv6, 3);
            return;
        }
        long $this$unCompoundWith$iv$iv7 = getCompositeKeyHashCode();
        long $this$ror$iv$iv$iv7 = ((long) rGroupIndex) ^ $this$unCompoundWith$iv$iv7;
        long $this$unCompoundWith$iv$iv8 = Long.rotateRight($this$ror$iv$iv$iv7, 3);
        long $this$ror$iv$iv$iv8 = $this$unCompoundWith$iv$iv8 ^ ((long) groupKey);
        this.compositeKeyHashCode = Long.rotateRight($this$ror$iv$iv$iv8, 3);
    }

    private final void updateCompositeKeyWhenWeExitGroupKeyHash(int groupKey, int rGroupIndex) {
        long $this$unCompoundWith$iv = getCompositeKeyHashCode();
        long $this$ror$iv$iv = ((long) rGroupIndex) ^ $this$unCompoundWith$iv;
        long $this$unCompoundWith$iv2 = Long.rotateRight($this$ror$iv$iv, 3);
        long $this$ror$iv$iv2 = ((long) groupKey) ^ $this$unCompoundWith$iv2;
        this.compositeKeyHashCode = Long.rotateRight($this$ror$iv$iv2, 3);
    }

    private final void validateNodeExpected() {
        boolean value$iv = this.nodeExpected;
        if (!value$iv) {
            ComposerKt.composeImmediateRuntimeError("A call to createNode(), emitNode() or useNode() expected was not expected");
        }
        this.nodeExpected = false;
    }

    private final void validateNodeNotExpected() {
        boolean value$iv = !this.nodeExpected;
        if (value$iv) {
            return;
        }
        ComposerKt.composeImmediateRuntimeError("A call to createNode(), emitNode() or useNode() expected");
    }

    /* JADX INFO: compiled from: LinkComposer.kt */
    @Metadata(d1 = {"\u0000ª\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0080\u0004\u0018\u00002\u00020\u0001B-\u0012\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\n\u0010\u000bJ\u0006\u0010\"\u001a\u00020#J\u0015\u0010$\u001a\u00020#2\u0006\u0010%\u001a\u00020&H\u0010¢\u0006\u0002\b'J\u0015\u0010(\u001a\u00020#2\u0006\u0010%\u001a\u00020&H\u0010¢\u0006\u0002\b)J\u0015\u0010*\u001a\u00020#2\u0006\u0010+\u001a\u00020,H\u0010¢\u0006\u0002\b-J\u0015\u0010.\u001a\u00020#2\u0006\u0010+\u001a\u00020,H\u0010¢\u0006\u0002\b/J\u0015\u00100\u001a\u00020#2\u0006\u00101\u001a\u000202H\u0010¢\u0006\u0002\b3J*\u00108\u001a\u00020#2\u0006\u0010+\u001a\u00020,2\u0011\u00109\u001a\r\u0012\u0004\u0012\u00020#0:¢\u0006\u0002\b;H\u0011¢\u0006\u0004\b<\u0010=J8\u0010>\u001a\b\u0012\u0004\u0012\u0002020?2\u0006\u0010+\u001a\u00020,2\u0006\u0010@\u001a\u00020A2\u0011\u00109\u001a\r\u0012\u0004\u0012\u00020#0:¢\u0006\u0002\b;H\u0011¢\u0006\u0004\bB\u0010CJ1\u0010D\u001a\b\u0012\u0004\u0012\u0002020?2\u0006\u0010+\u001a\u00020,2\u0006\u0010@\u001a\u00020A2\f\u0010E\u001a\b\u0012\u0004\u0012\u0002020?H\u0010¢\u0006\u0002\bFJ\u0015\u0010G\u001a\u00020#2\u0006\u0010+\u001a\u00020,H\u0010¢\u0006\u0002\bHJ\u0015\u0010I\u001a\u00020#2\u0006\u00101\u001a\u000202H\u0010¢\u0006\u0002\bJJ\r\u0010N\u001a\u00020LH\u0010¢\u0006\u0002\bTJ\u000e\u0010U\u001a\u00020#2\u0006\u00101\u001a\u00020LJ\u001b\u0010V\u001a\u00020#2\f\u0010W\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015H\u0010¢\u0006\u0002\bXJ\r\u0010Y\u001a\u00020#H\u0010¢\u0006\u0002\bZJ\r\u0010[\u001a\u00020#H\u0010¢\u0006\u0002\b\\J\u0015\u0010]\u001a\u00020#2\u0006\u0010^\u001a\u00020_H\u0010¢\u0006\u0002\b`J\u0015\u0010a\u001a\u00020#2\u0006\u0010^\u001a\u00020_H\u0010¢\u0006\u0002\bbJ\u0017\u0010c\u001a\u0004\u0018\u00010d2\u0006\u0010^\u001a\u00020_H\u0010¢\u0006\u0002\beJ)\u0010f\u001a\u00020#2\u0006\u0010^\u001a\u00020_2\u0006\u0010g\u001a\u00020d2\n\u0010h\u001a\u0006\u0012\u0002\b\u00030iH\u0010¢\u0006\u0002\bjJ\u0015\u0010k\u001a\u00020#2\u0006\u0010+\u001a\u00020,H\u0010¢\u0006\u0002\blJ\u0016\u0010p\u001a\u00020q2\f\u0010r\u001a\b\u0012\u0004\u0012\u00020#0:H\u0016R\u001a\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004X\u0090\u0004¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\f\u0010\rR\u0014\u0010\u0005\u001a\u00020\u0006X\u0090\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0007\u001a\u00020\u0006X\u0090\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0016\u0010\b\u001a\u0004\u0018\u00010\tX\u0090\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R(\u0010\u0014\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\u0015\u0018\u00010\u0015X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u0017\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001c0\u0015¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0018R\u0014\u0010\u001e\u001a\u00020\u00068PX\u0090\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010\u0010R\u0014\u0010 \u001a\u00020\u00068PX\u0090\u0004¢\u0006\u0006\u001a\u0004\b!\u0010\u0010R\u0014\u00104\u001a\u0002058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b6\u00107R+\u0010M\u001a\u00020L2\u0006\u0010K\u001a\u00020L8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\bR\u0010S\u001a\u0004\bN\u0010O\"\u0004\bP\u0010QR\u0014\u0010+\u001a\u00020m8PX\u0090\u0004¢\u0006\u0006\u001a\u0004\bn\u0010o¨\u0006s"}, d2 = {"Landroidx/compose/runtime/LinkComposer$CompositionContextImpl;", "Landroidx/compose/runtime/CompositionContext;", "compositeKeyHashCode", "", "Landroidx/compose/runtime/CompositeKeyHashCode;", "collectingParameterInformation", "", "collectingSourceInformation", "observerHolder", "Landroidx/compose/runtime/CompositionObserverHolder;", "<init>", "(Landroidx/compose/runtime/LinkComposer;JZZLandroidx/compose/runtime/CompositionObserverHolder;)V", "getCompositeKeyHashCode$runtime", "()J", "J", "getCollectingParameterInformation$runtime", "()Z", "getCollectingSourceInformation$runtime", "getObserverHolder$runtime", "()Landroidx/compose/runtime/CompositionObserverHolder;", "inspectionTables", "", "Landroidx/compose/runtime/tooling/CompositionData;", "getInspectionTables", "()Ljava/util/Set;", "setInspectionTables", "(Ljava/util/Set;)V", "composers", "Landroidx/compose/runtime/LinkComposer;", "getComposers", "collectingCallByInformation", "getCollectingCallByInformation$runtime", "stackTraceEnabled", "getStackTraceEnabled$runtime", "dispose", "", "registerComposer", "composer", "Landroidx/compose/runtime/Composer;", "registerComposer$runtime", "unregisterComposer", "unregisterComposer$runtime", "registerComposition", "composition", "Landroidx/compose/runtime/ControlledComposition;", "registerComposition$runtime", "unregisterComposition", "unregisterComposition$runtime", "reportPausedScope", "scope", "Landroidx/compose/runtime/RecomposeScopeImpl;", "reportPausedScope$runtime", "effectCoroutineContext", "Lkotlin/coroutines/CoroutineContext;", "getEffectCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "composeInitial", "content", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "composeInitial$runtime", "(Landroidx/compose/runtime/ControlledComposition;Lkotlin/jvm/functions/Function2;)V", "composeInitialPaused", "Landroidx/collection/ScatterSet;", "shouldPause", "Landroidx/compose/runtime/ShouldPauseCallback;", "composeInitialPaused$runtime", "(Landroidx/compose/runtime/ControlledComposition;Landroidx/compose/runtime/ShouldPauseCallback;Lkotlin/jvm/functions/Function2;)Landroidx/collection/ScatterSet;", "recomposePaused", "invalidScopes", "recomposePaused$runtime", "invalidate", "invalidate$runtime", "invalidateScope", "invalidateScope$runtime", "<set-?>", "Landroidx/compose/runtime/PersistentCompositionLocalMap;", "compositionLocalScope", "getCompositionLocalScope", "()Landroidx/compose/runtime/PersistentCompositionLocalMap;", "setCompositionLocalScope", "(Landroidx/compose/runtime/PersistentCompositionLocalMap;)V", "compositionLocalScope$delegate", "Landroidx/compose/runtime/MutableState;", "getCompositionLocalScope$runtime", "updateCompositionLocalScope", "recordInspectionTable", "table", "recordInspectionTable$runtime", "startComposing", "startComposing$runtime", "doneComposing", "doneComposing$runtime", "insertMovableContent", TypedValues.Custom.S_REFERENCE, "Landroidx/compose/runtime/MovableContentStateReference;", "insertMovableContent$runtime", "deletedMovableContent", "deletedMovableContent$runtime", "movableContentStateResolve", "Landroidx/compose/runtime/MovableContentState;", "movableContentStateResolve$runtime", "movableContentStateReleased", "data", "applier", "Landroidx/compose/runtime/Applier;", "movableContentStateReleased$runtime", "reportRemovedComposition", "reportRemovedComposition$runtime", "Landroidx/compose/runtime/Composition;", "getComposition$runtime", "()Landroidx/compose/runtime/Composition;", "scheduleFrameEndCallback", "Landroidx/compose/runtime/CancellationHandle;", "action", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public final class CompositionContextImpl extends CompositionContext {
        private final boolean collectingParameterInformation;
        private final boolean collectingSourceInformation;
        private final long compositeKeyHashCode;
        private Set<Set<CompositionData>> inspectionTables;
        private final CompositionObserverHolder observerHolder;
        private final Set<LinkComposer> composers = new LinkedHashSet();

        /* JADX INFO: renamed from: compositionLocalScope$delegate, reason: from kotlin metadata */
        private final MutableState compositionLocalScope = SnapshotStateKt.mutableStateOf(PersistentCompositionLocalMapKt.persistentCompositionLocalHashMapOf(), SnapshotStateKt.referentialEqualityPolicy());

        public CompositionContextImpl(long compositeKeyHashCode, boolean collectingParameterInformation, boolean collectingSourceInformation, CompositionObserverHolder observerHolder) {
            this.compositeKeyHashCode = compositeKeyHashCode;
            this.collectingParameterInformation = collectingParameterInformation;
            this.collectingSourceInformation = collectingSourceInformation;
            this.observerHolder = observerHolder;
        }

        @Override // androidx.compose.runtime.CompositionContext
        /* JADX INFO: renamed from: getCompositeKeyHashCode$runtime, reason: from getter */
        public long getCompositeKeyHashCode() {
            return this.compositeKeyHashCode;
        }

        @Override // androidx.compose.runtime.CompositionContext
        /* JADX INFO: renamed from: getCollectingParameterInformation$runtime, reason: from getter */
        public boolean getCollectingParameterInformation() {
            return this.collectingParameterInformation;
        }

        @Override // androidx.compose.runtime.CompositionContext
        /* JADX INFO: renamed from: getCollectingSourceInformation$runtime, reason: from getter */
        public boolean getCollectingSourceInformation() {
            return this.collectingSourceInformation;
        }

        @Override // androidx.compose.runtime.CompositionContext
        /* JADX INFO: renamed from: getObserverHolder$runtime, reason: from getter */
        public CompositionObserverHolder getObserverHolder() {
            return this.observerHolder;
        }

        public final Set<Set<CompositionData>> getInspectionTables() {
            return this.inspectionTables;
        }

        public final void setInspectionTables(Set<Set<CompositionData>> set) {
            this.inspectionTables = set;
        }

        public final Set<LinkComposer> getComposers() {
            return this.composers;
        }

        @Override // androidx.compose.runtime.CompositionContext
        public boolean getCollectingCallByInformation$runtime() {
            return LinkComposer.this.parentContext.getCollectingCallByInformation$runtime();
        }

        @Override // androidx.compose.runtime.CompositionContext
        public boolean getStackTraceEnabled$runtime() {
            return LinkComposer.this.parentContext.getStackTraceEnabled$runtime();
        }

        public final void dispose() {
            if (!this.composers.isEmpty()) {
                Set<Set<CompositionData>> set = this.inspectionTables;
                if (set != null) {
                    for (LinkComposer composer : this.composers) {
                        Iterator<Set<CompositionData>> it = set.iterator();
                        while (it.hasNext()) {
                            it.next().remove(composer.getCompositionData());
                        }
                    }
                }
                this.composers.clear();
            }
        }

        @Override // androidx.compose.runtime.CompositionContext
        public void registerComposer$runtime(Composer composer) {
            super.registerComposer$runtime(composer);
            this.composers.add(LinkComposerKt.asLinkComposer(composer));
        }

        @Override // androidx.compose.runtime.CompositionContext
        public void unregisterComposer$runtime(Composer composer) {
            Iterable iterable = this.inspectionTables;
            if (iterable != null) {
                Iterable $this$forEach$iv = iterable;
                for (Object element$iv : $this$forEach$iv) {
                    Set it = (Set) element$iv;
                    it.remove(LinkComposerKt.asLinkComposer(composer).getCompositionData());
                }
            }
            Iterable $this$forEach$iv2 = this.composers;
            TypeIntrinsics.asMutableCollection((Collection) $this$forEach$iv2).remove(composer);
        }

        @Override // androidx.compose.runtime.CompositionContext
        public void registerComposition$runtime(ControlledComposition composition) {
            LinkComposer.this.parentContext.registerComposition$runtime(composition);
        }

        @Override // androidx.compose.runtime.CompositionContext
        public void unregisterComposition$runtime(ControlledComposition composition) {
            LinkComposer.this.parentContext.unregisterComposition$runtime(composition);
        }

        @Override // androidx.compose.runtime.CompositionContext
        public void reportPausedScope$runtime(RecomposeScopeImpl scope) {
            LinkComposer.this.parentContext.reportPausedScope$runtime(scope);
        }

        @Override // androidx.compose.runtime.CompositionContext
        public CoroutineContext getEffectCoroutineContext() {
            return LinkComposer.this.parentContext.getEffectCoroutineContext();
        }

        @Override // androidx.compose.runtime.CompositionContext
        public void composeInitial$runtime(ControlledComposition composition, Function2<? super Composer, ? super Integer, Unit> content) {
            LinkComposer.this.parentContext.composeInitial$runtime(composition, content);
        }

        @Override // androidx.compose.runtime.CompositionContext
        public ScatterSet<RecomposeScopeImpl> composeInitialPaused$runtime(ControlledComposition composition, ShouldPauseCallback shouldPause, Function2<? super Composer, ? super Integer, Unit> content) {
            return LinkComposer.this.parentContext.composeInitialPaused$runtime(composition, shouldPause, content);
        }

        @Override // androidx.compose.runtime.CompositionContext
        public ScatterSet<RecomposeScopeImpl> recomposePaused$runtime(ControlledComposition composition, ShouldPauseCallback shouldPause, ScatterSet<RecomposeScopeImpl> invalidScopes) {
            return LinkComposer.this.parentContext.recomposePaused$runtime(composition, shouldPause, invalidScopes);
        }

        @Override // androidx.compose.runtime.CompositionContext
        public void invalidate$runtime(ControlledComposition composition) {
            LinkComposer.this.parentContext.invalidate$runtime(LinkComposer.this.getComposition());
            LinkComposer.this.parentContext.invalidate$runtime(composition);
        }

        @Override // androidx.compose.runtime.CompositionContext
        public void invalidateScope$runtime(RecomposeScopeImpl scope) {
            LinkComposer.this.parentContext.invalidateScope$runtime(scope);
        }

        private final PersistentCompositionLocalMap getCompositionLocalScope() {
            State $this$getValue$iv = this.compositionLocalScope;
            return (PersistentCompositionLocalMap) $this$getValue$iv.getValue();
        }

        private final void setCompositionLocalScope(PersistentCompositionLocalMap persistentCompositionLocalMap) {
            MutableState $this$setValue$iv = this.compositionLocalScope;
            $this$setValue$iv.setValue(persistentCompositionLocalMap);
        }

        @Override // androidx.compose.runtime.CompositionContext
        public PersistentCompositionLocalMap getCompositionLocalScope$runtime() {
            return getCompositionLocalScope();
        }

        public final void updateCompositionLocalScope(PersistentCompositionLocalMap scope) {
            setCompositionLocalScope(scope);
        }

        @Override // androidx.compose.runtime.CompositionContext
        public void recordInspectionTable$runtime(Set<CompositionData> table) {
            HashSet hashSet = this.inspectionTables;
            if (hashSet == null) {
                HashSet it = new HashSet();
                this.inspectionTables = it;
                hashSet = it;
            }
            hashSet.add(table);
        }

        @Override // androidx.compose.runtime.CompositionContext
        public void startComposing$runtime() {
            LinkComposer.this.childrenComposing++;
        }

        @Override // androidx.compose.runtime.CompositionContext
        public void doneComposing$runtime() {
            LinkComposer.this.childrenComposing--;
        }

        @Override // androidx.compose.runtime.CompositionContext
        public void insertMovableContent$runtime(MovableContentStateReference movableContentStateReference) {
            LinkComposer.this.parentContext.insertMovableContent$runtime(movableContentStateReference);
        }

        @Override // androidx.compose.runtime.CompositionContext
        public void deletedMovableContent$runtime(MovableContentStateReference movableContentStateReference) {
            LinkComposer.this.parentContext.deletedMovableContent$runtime(movableContentStateReference);
        }

        @Override // androidx.compose.runtime.CompositionContext
        public MovableContentState movableContentStateResolve$runtime(MovableContentStateReference movableContentStateReference) {
            return LinkComposer.this.parentContext.movableContentStateResolve$runtime(movableContentStateReference);
        }

        @Override // androidx.compose.runtime.CompositionContext
        public void movableContentStateReleased$runtime(MovableContentStateReference movableContentStateReference, MovableContentState data, Applier<?> applier) {
            LinkComposer.this.parentContext.movableContentStateReleased$runtime(movableContentStateReference, data, applier);
        }

        @Override // androidx.compose.runtime.CompositionContext
        public void reportRemovedComposition$runtime(ControlledComposition composition) {
            LinkComposer.this.parentContext.reportRemovedComposition$runtime(composition);
        }

        @Override // androidx.compose.runtime.CompositionContext
        public Composition getComposition$runtime() {
            return LinkComposer.this.getComposition();
        }

        @Override // androidx.compose.runtime.CompositionContext
        public CancellationHandle scheduleFrameEndCallback(Function0<Unit> action) {
            return LinkComposer.this.parentContext.scheduleFrameEndCallback(action);
        }
    }

    /* JADX INFO: compiled from: LinkComposer.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0001\u0018\u00002\u00020\u0001B\u0013\u0012\n\u0010\u0002\u001a\u00060\u0003R\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\nH\u0016J\b\u0010\f\u001a\u00020\nH\u0016R\u0015\u0010\u0002\u001a\u00060\u0003R\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\r"}, d2 = {"Landroidx/compose/runtime/LinkComposer$CompositionContextHolder;", "Landroidx/compose/runtime/RememberObserver;", "ref", "Landroidx/compose/runtime/LinkComposer$CompositionContextImpl;", "Landroidx/compose/runtime/LinkComposer;", "<init>", "(Landroidx/compose/runtime/LinkComposer$CompositionContextImpl;)V", "getRef", "()Landroidx/compose/runtime/LinkComposer$CompositionContextImpl;", "onRemembered", "", "onAbandoned", "onForgotten", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class CompositionContextHolder implements RememberObserver {
        public static final int $stable = 8;
        private final CompositionContextImpl ref;

        public CompositionContextHolder(CompositionContextImpl ref) {
            this.ref = ref;
        }

        public final CompositionContextImpl getRef() {
            return this.ref;
        }

        @Override // androidx.compose.runtime.RememberObserver
        public void onRemembered() {
        }

        @Override // androidx.compose.runtime.RememberObserver
        public void onAbandoned() {
            this.ref.dispose();
        }

        @Override // androidx.compose.runtime.RememberObserver
        public void onForgotten() {
            this.ref.dispose();
        }
    }
}
