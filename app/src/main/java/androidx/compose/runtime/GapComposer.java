package androidx.compose.runtime;

import androidx.collection.IntIntMap;
import androidx.collection.MutableIntIntMap;
import androidx.collection.MutableIntObjectMap;
import androidx.collection.MutableScatterMap;
import androidx.collection.MutableScatterSet;
import androidx.collection.ScatterMap;
import androidx.collection.ScatterSet;
import androidx.collection.ScatterSetKt;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.runtime.collection.ScopeMap;
import androidx.compose.runtime.composer.GroupKind;
import androidx.compose.runtime.composer.gapbuffer.GapAnchor;
import androidx.compose.runtime.composer.gapbuffer.GapAnchorKt;
import androidx.compose.runtime.composer.gapbuffer.KeyInfo;
import androidx.compose.runtime.composer.gapbuffer.SlotReader;
import androidx.compose.runtime.composer.gapbuffer.SlotTable;
import androidx.compose.runtime.composer.gapbuffer.SlotTableKt;
import androidx.compose.runtime.composer.gapbuffer.SlotWriter;
import androidx.compose.runtime.composer.gapbuffer.changelist.ChangeList;
import androidx.compose.runtime.composer.gapbuffer.changelist.ChangeListKt;
import androidx.compose.runtime.composer.gapbuffer.changelist.ComposerChangeListWriter;
import androidx.compose.runtime.composer.gapbuffer.changelist.FixupList;
import androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentMap;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.internal.Expect_jvmKt;
import androidx.compose.runtime.internal.IntRef;
import androidx.compose.runtime.internal.PersistentCompositionLocalMapKt;
import androidx.compose.runtime.internal.Trace;
import androidx.compose.runtime.snapshots.ListUtilsKt;
import androidx.compose.runtime.snapshots.SnapshotKt;
import androidx.compose.runtime.tooling.ComposeStackTrace;
import androidx.compose.runtime.tooling.ComposeStackTraceBuilderKt;
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
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;

/* JADX INFO: compiled from: GapComposer.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000·\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\f\n\u0002\u0010\u0000\n\u0002\b\f\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\f\n\u0002\u0010\u0005\n\u0002\u0010\n\n\u0002\u0010\u0007\n\u0002\u0010\u0006\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b-\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b(\n\u0002\u0018\u0002\n\u0002\b\u000b*\u0001?\b\u0001\u0018\u00002\u00020\u0001:\u0004\u0087\u0003\u0088\u0003BQ\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\f\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011¢\u0006\u0004\b\u0012\u0010\u0013J\u0010\u0010r\u001a\u00020s2\u0006\u0010t\u001a\u00020\u001eH\u0017J\b\u0010u\u001a\u00020sH\u0017J\u0010\u0010v\u001a\u00020s2\u0006\u0010t\u001a\u00020\u001eH\u0017J\b\u0010w\u001a\u00020sH\u0017J\b\u0010x\u001a\u00020sH\u0017J\b\u0010y\u001a\u00020sH\u0017J\u001b\u0010~\u001a\u00020s2\u0006\u0010t\u001a\u00020\u001e2\t\u0010\u007f\u001a\u0005\u0018\u00010\u0080\u0001H\u0017J\t\u0010\u0081\u0001\u001a\u00020sH\u0017J\t\u0010\u0082\u0001\u001a\u00020sH\u0002J\t\u0010\u0083\u0001\u001a\u00020sH\u0002J\t\u0010\u0084\u0001\u001a\u00020sH\u0002J\u000f\u0010\u0085\u0001\u001a\u00020sH\u0010¢\u0006\u0003\b\u0086\u0001J\t\u0010\u0094\u0001\u001a\u00020sH\u0016J\u001a\u0010\u0095\u0001\u001a\u00030\u0096\u00012\u000e\u0010\u0097\u0001\u001a\t\u0012\u0004\u0012\u00020s0\u0098\u0001H\u0016J\u000f\u0010\u0099\u0001\u001a\u00020sH\u0010¢\u0006\u0003\b\u009a\u0001J\u000f\u0010\u009b\u0001\u001a\u00020sH\u0010¢\u0006\u0003\b\u009c\u0001J\u000e\u0010'\u001a\u00020(H\u0010¢\u0006\u0003\b\u009d\u0001J\u0011\u0010\u009e\u0001\u001a\u00020s2\u0006\u0010t\u001a\u00020\u001eH\u0002J\u001c\u0010\u009e\u0001\u001a\u00020s2\u0006\u0010t\u001a\u00020\u001e2\t\u0010\u007f\u001a\u0005\u0018\u00010\u0080\u0001H\u0002J\t\u0010\u009f\u0001\u001a\u00020sH\u0002J\t\u0010 \u0001\u001a\u00020sH\u0002J\t\u0010¡\u0001\u001a\u00020sH\u0016J\t\u0010¢\u0001\u001a\u00020sH\u0016J!\u0010£\u0001\u001a\u00020s\"\u0005\b\u0000\u0010¤\u00012\u000f\u0010¥\u0001\u001a\n\u0012\u0005\u0012\u0003H¤\u00010\u0098\u0001H\u0016J\t\u0010¦\u0001\u001a\u00020sH\u0016J\t\u0010§\u0001\u001a\u00020sH\u0016J\u001c\u0010¨\u0001\u001a\u00020s2\u0006\u0010t\u001a\u00020\u001e2\t\u0010\u007f\u001a\u0005\u0018\u00010\u0080\u0001H\u0016J\t\u0010©\u0001\u001a\u00020sH\u0016J\t\u0010ª\u0001\u001a\u00020sH\u0016J\t\u0010«\u0001\u001a\u00020sH\u0016J\u000f\u0010¬\u0001\u001a\u00020sH\u0010¢\u0006\u0003\b\u00ad\u0001J\u000f\u0010®\u0001\u001a\u00020sH\u0010¢\u0006\u0003\b¯\u0001J\u0012\u0010³\u0001\u001a\u00020s2\u0007\u0010´\u0001\u001a\u00020\u001eH\u0016JJ\u0010µ\u0001\u001a\u00020s\"\u0005\b\u0000\u0010¶\u0001\"\u0005\b\u0001\u0010¤\u00012\u0007\u0010C\u001a\u0003H¶\u00012\"\u0010·\u0001\u001a\u001d\u0012\u0005\u0012\u0003H¤\u0001\u0012\u0005\u0012\u0003H¶\u0001\u0012\u0004\u0012\u00020s0¸\u0001¢\u0006\u0003\b¹\u0001H\u0016¢\u0006\u0003\u0010º\u0001J\"\u0010»\u0001\u001a\u00030\u0080\u00012\n\u0010¼\u0001\u001a\u0005\u0018\u00010\u0080\u00012\n\u0010½\u0001\u001a\u0005\u0018\u00010\u0080\u0001H\u0017J\f\u0010¾\u0001\u001a\u0005\u0018\u00010\u0080\u0001H\u0001J\f\u0010¿\u0001\u001a\u0005\u0018\u00010\u0080\u0001H\u0001J\u0014\u0010À\u0001\u001a\u00020(2\t\u0010C\u001a\u0005\u0018\u00010\u0080\u0001H\u0017J\u0014\u0010Á\u0001\u001a\u00020(2\t\u0010C\u001a\u0005\u0018\u00010\u0080\u0001H\u0017J\u0012\u0010À\u0001\u001a\u00020(2\u0007\u0010C\u001a\u00030Â\u0001H\u0017J\u0012\u0010À\u0001\u001a\u00020(2\u0007\u0010C\u001a\u00030Ã\u0001H\u0017J\u0012\u0010À\u0001\u001a\u00020(2\u0007\u0010C\u001a\u00030Ä\u0001H\u0017J\u0011\u0010À\u0001\u001a\u00020(2\u0006\u0010C\u001a\u00020(H\u0017J\u0012\u0010À\u0001\u001a\u00020(2\u0007\u0010C\u001a\u00030Å\u0001H\u0017J\u0012\u0010À\u0001\u001a\u00020(2\u0007\u0010C\u001a\u00030\u008d\u0001H\u0017J\u0012\u0010À\u0001\u001a\u00020(2\u0007\u0010C\u001a\u00030Æ\u0001H\u0017J\u0011\u0010À\u0001\u001a\u00020(2\u0006\u0010C\u001a\u00020\u001eH\u0017J2\u0010Ç\u0001\u001a\u0003H¤\u0001\"\u0005\b\u0000\u0010¤\u00012\u0007\u0010È\u0001\u001a\u00020(2\u000f\u0010·\u0001\u001a\n\u0012\u0005\u0012\u0003H¤\u00010\u0098\u0001H\u0087\b¢\u0006\u0003\u0010É\u0001J\u0014\u0010Ê\u0001\u001a\u00020s2\t\u0010C\u001a\u0005\u0018\u00010\u0080\u0001H\u0002J\u0014\u0010Ë\u0001\u001a\u00020s2\t\u0010C\u001a\u0005\u0018\u00010\u0080\u0001H\u0001J\u0014\u0010Ì\u0001\u001a\u00020s2\t\u0010C\u001a\u0005\u0018\u00010\u0080\u0001H\u0001J\t\u0010Í\u0001\u001a\u00020\u001eH\u0002J\u0019\u0010Ó\u0001\u001a\u00020s2\u000e\u0010Ô\u0001\u001a\t\u0012\u0004\u0012\u00020s0\u0098\u0001H\u0016J\t\u0010Õ\u0001\u001a\u000200H\u0002J\u0012\u0010Õ\u0001\u001a\u0002002\u0007\u0010Ú\u0001\u001a\u00020\u001eH\u0002J\u001b\u0010Û\u0001\u001a\u0002002\u0007\u0010Ü\u0001\u001a\u0002002\u0007\u0010Ý\u0001\u001a\u000200H\u0002J\u0016\u0010Þ\u0001\u001a\u00020s2\u000b\u0010C\u001a\u0007\u0012\u0002\b\u00030ß\u0001H\u0017J\u0012\u0010à\u0001\u001a\u00020s2\u0007\u0010á\u0001\u001a\u000200H\u0002J\t\u0010â\u0001\u001a\u00020sH\u0017J&\u0010ã\u0001\u001a\u00020s2\u0015\u0010ä\u0001\u001a\u0010\u0012\u000b\b\u0001\u0012\u0007\u0012\u0002\b\u00030ß\u00010å\u0001H\u0017¢\u0006\u0003\u0010æ\u0001J\t\u0010ç\u0001\u001a\u00020sH\u0017J'\u0010è\u0001\u001a\u0003H¤\u0001\"\u0005\b\u0000\u0010¤\u00012\u000e\u0010t\u001a\n\u0012\u0005\u0012\u0003H¤\u00010é\u0001H\u0017¢\u0006\u0003\u0010ê\u0001J\t\u0010ë\u0001\u001a\u00020\u0005H\u0016J\t\u0010ï\u0001\u001a\u00020sH\u0002J\t\u0010ð\u0001\u001a\u00020sH\u0002J\t\u0010ñ\u0001\u001a\u00020sH\u0002J\u001e\u0010ò\u0001\u001a\u00020s2\u0007\u0010ó\u0001\u001a\u00020(2\n\u0010ô\u0001\u001a\u0005\u0018\u00010\u0080\u0001H\u0002J<\u0010õ\u0001\u001a\u00020s2\u0006\u0010t\u001a\u00020\u001e2\n\u0010ö\u0001\u001a\u0005\u0018\u00010\u0080\u00012\b\u0010÷\u0001\u001a\u00030ø\u00012\n\u0010ô\u0001\u001a\u0005\u0018\u00010\u0080\u0001H\u0002¢\u0006\u0006\bù\u0001\u0010ú\u0001J\u001d\u0010û\u0001\u001a\u00020s2\u0007\u0010ó\u0001\u001a\u00020(2\t\u0010ü\u0001\u001a\u0004\u0018\u00010\u001aH\u0002J\u001b\u0010ý\u0001\u001a\u00020s2\u0007\u0010þ\u0001\u001a\u00020\u001e2\u0007\u0010\u0087\u0001\u001a\u00020(H\u0002J\u0012\u0010ÿ\u0001\u001a\u00020s2\u0007\u0010ó\u0001\u001a\u00020(H\u0002J\t\u0010\u0080\u0002\u001a\u00020sH\u0002J\u0012\u0010\u0081\u0002\u001a\u00020\u001e2\u0007\u0010\u0082\u0002\u001a\u00020\u001eH\u0002J\u001b\u0010\u0083\u0002\u001a\u00020s2\u0007\u0010Ú\u0001\u001a\u00020\u001e2\u0007\u0010\u0084\u0002\u001a\u00020\u001eH\u0002J-\u0010\u0085\u0002\u001a\u00020\u001e2\u0007\u0010\u0086\u0002\u001a\u00020\u001e2\u0007\u0010Ú\u0001\u001a\u00020\u001e2\u0007\u0010\u0087\u0002\u001a\u00020\u001e2\u0007\u0010\u0088\u0002\u001a\u00020\u001eH\u0002J\u0012\u0010\u0089\u0002\u001a\u00020\u001e2\u0007\u0010Ú\u0001\u001a\u00020\u001eH\u0002J\u0012\u0010\u008a\u0002\u001a\u00020\u001e2\u0007\u0010Ú\u0001\u001a\u00020\u001eH\u0002J\u001b\u0010\u008b\u0002\u001a\u00020s2\u0007\u0010Ú\u0001\u001a\u00020\u001e2\u0007\u0010\u008c\u0002\u001a\u00020\u001eH\u0002J\t\u0010\u008d\u0002\u001a\u00020sH\u0002J$\u0010\u008e\u0002\u001a\u00020s2\u0007\u0010\u008f\u0002\u001a\u00020\u001e2\u0007\u0010\u0090\u0002\u001a\u00020\u001e2\u0007\u0010\u0091\u0002\u001a\u00020\u001eH\u0002J\u001b\u0010\u0092\u0002\u001a\u00020s2\u0007\u0010Ú\u0001\u001a\u00020\u001e2\u0007\u0010\u0093\u0002\u001a\u00020\u001eH\u0002J6\u0010\u0094\u0002\u001a\b0\u008d\u0001j\u0003`\u008e\u00012\u0007\u0010Ú\u0001\u001a\u00020\u001e2\u0007\u0010\u0087\u0002\u001a\u00020\u001e2\r\u0010\u0095\u0002\u001a\b0\u008d\u0001j\u0003`\u008e\u0001H\u0002¢\u0006\u0003\u0010\u0096\u0002J\u0016\u0010\u0097\u0002\u001a\u00020\u001e*\u00020M2\u0007\u0010Ú\u0001\u001a\u00020\u001eH\u0002J$\u0010\u0098\u0002\u001a\u00020(2\u0007\u0010\u0099\u0002\u001a\u00020B2\n\u0010\u009a\u0002\u001a\u0005\u0018\u00010\u0080\u0001H\u0010¢\u0006\u0003\b\u009b\u0002J\u000f\u0010\u009c\u0002\u001a\u00020\u001eH\u0011¢\u0006\u0003\b\u009d\u0002J\t\u0010\u009e\u0002\u001a\u00020sH\u0017J\t\u0010\u009f\u0002\u001a\u00020sH\u0002J\u001b\u0010 \u0002\u001a\u00020(2\u0007\u0010¡\u0002\u001a\u00020(2\u0007\u0010¢\u0002\u001a\u00020\u001eH\u0017J\t\u0010£\u0002\u001a\u00020sH\u0017J\u0012\u0010¤\u0002\u001a\u00020s2\u0007\u0010À\u0001\u001a\u00020(H\u0017J\u0012\u0010¥\u0002\u001a\u00030¦\u00022\u0006\u0010t\u001a\u00020\u001eH\u0017J\t\u0010§\u0002\u001a\u00020sH\u0002J\u0012\u0010¨\u0002\u001a\u00020s2\u0007\u0010\u0099\u0002\u001a\u00020BH\u0002J\f\u0010©\u0002\u001a\u0005\u0018\u00010ª\u0002H\u0017J\"\u0010«\u0002\u001a\u0012\u0012\u0005\u0012\u00030\u00ad\u0002\u0012\u0004\u0012\u00020s\u0018\u00010¬\u00022\u0007\u0010\u0099\u0002\u001a\u00020BH\u0002J\"\u0010®\u0002\u001a\u00020s2\u000b\u0010C\u001a\u0007\u0012\u0002\b\u00030¯\u00022\n\u0010°\u0002\u001a\u0005\u0018\u00010\u0080\u0001H\u0017J:\u0010±\u0002\u001a\u00020s2\u0011\u0010²\u0002\u001a\f\u0012\u0007\u0012\u0005\u0018\u00010\u0080\u00010¯\u00022\u0007\u0010³\u0002\u001a\u0002002\n\u0010°\u0002\u001a\u0005\u0018\u00010\u0080\u00012\u0007\u0010´\u0002\u001a\u00020(H\u0002J*\u0010µ\u0002\u001a\u00020s2\u001f\u0010¶\u0002\u001a\u001a\u0012\u0015\u0012\u0013\u0012\u0005\u0012\u00030¹\u0002\u0012\u0007\u0012\u0005\u0018\u00010¹\u00020¸\u00020·\u0002H\u0017J*\u0010º\u0002\u001a\u00020s2\u001f\u0010¶\u0002\u001a\u001a\u0012\u0015\u0012\u0013\u0012\u0005\u0012\u00030¹\u0002\u0012\u0007\u0012\u0005\u0018\u00010¹\u00020¸\u00020·\u0002H\u0002J1\u0010»\u0002\u001a\u0003H¼\u0002\"\u0005\b\u0000\u0010¼\u00022\u0006\u0010L\u001a\u00020M2\u000f\u0010·\u0001\u001a\n\u0012\u0005\u0012\u0003H¼\u00020\u0098\u0001H\u0082\b¢\u0006\u0003\u0010½\u0002Jr\u0010¾\u0002\u001a\u0003H¼\u0002\"\u0005\b\u0000\u0010¼\u00022\f\b\u0002\u0010¿\u0002\u001a\u0005\u0018\u00010À\u00022\f\b\u0002\u0010Á\u0002\u001a\u0005\u0018\u00010À\u00022\u000b\b\u0002\u0010\u0082\u0002\u001a\u0004\u0018\u00010\u001e2\u001f\b\u0002\u0010+\u001a\u0019\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020B\u0012\u0007\u0012\u0005\u0018\u00010\u0080\u00010¸\u00020·\u00022\u000f\u0010·\u0001\u001a\n\u0012\u0005\u0012\u0003H¼\u00020\u0098\u0001H\u0002¢\u0006\u0003\u0010Â\u0002J\u0013\u0010Ã\u0002\u001a\u00020s2\b\u0010Ã\u0002\u001a\u00030Ä\u0002H\u0017J\u001b\u0010Å\u0002\u001a\u00020s2\u0006\u0010t\u001a\u00020\u001e2\b\u0010Ã\u0002\u001a\u00030Ä\u0002H\u0017J\t\u0010Æ\u0002\u001a\u00020sH\u0017J\t\u0010Ç\u0002\u001a\u00020sH\u0016J\u001b\u0010È\u0002\u001a\u00030É\u00022\t\u0010C\u001a\u0005\u0018\u00010\u0080\u0001H\u0010¢\u0006\u0003\bÊ\u0002J\f\u0010Ë\u0002\u001a\u0005\u0018\u00010É\u0002H\u0002J+\u0010Ì\u0002\u001a\n\u0012\u0005\u0012\u00030Í\u00020·\u00022\u0007\u0010Ú\u0001\u001a\u00020\u001e2\t\u0010Î\u0002\u001a\u0004\u0018\u00010\u001eH\u0002¢\u0006\u0003\u0010Ï\u0002J\u0017\u0010Ð\u0002\u001a\n\u0012\u0005\u0012\u00030Í\u00020·\u0002H\u0010¢\u0006\u0003\bÑ\u0002JJ\u0010Ò\u0002\u001a\u00020s2\u0015\u0010Ó\u0002\u001a\u0010\u0012\u0004\u0012\u00020B\u0012\u0005\u0012\u00030\u0080\u00010Ô\u00022\u0014\u0010²\u0002\u001a\u000f\u0012\u0004\u0012\u00020s0\u0098\u0001¢\u0006\u0003\bÕ\u00022\t\u0010Ö\u0002\u001a\u0004\u0018\u00010iH\u0010¢\u0006\u0006\b×\u0002\u0010Ø\u0002J\u001f\u0010Ù\u0002\u001a\u00020s2\u000e\u0010·\u0001\u001a\t\u0012\u0004\u0012\u00020s0\u0098\u0001H\u0010¢\u0006\u0003\bÚ\u0002J4\u0010Û\u0002\u001a\u00020(2\u0015\u0010Ó\u0002\u001a\u0010\u0012\u0004\u0012\u00020B\u0012\u0005\u0012\u00030\u0080\u00010Ô\u00022\t\u0010Ö\u0002\u001a\u0004\u0018\u00010iH\u0010¢\u0006\u0006\bÜ\u0002\u0010Ý\u0002J)\u0010Þ\u0002\u001a\u00020s2\u0015\u0010Ó\u0002\u001a\u0010\u0012\u0004\u0012\u00020B\u0012\u0005\u0012\u00030\u0080\u00010Ô\u0002H\u0010¢\u0006\u0006\bß\u0002\u0010à\u0002JA\u0010á\u0002\u001a\u00020s2\u0015\u0010Ó\u0002\u001a\u0010\u0012\u0004\u0012\u00020B\u0012\u0005\u0012\u00030\u0080\u00010Ô\u00022\u0016\u0010²\u0002\u001a\u0011\u0012\u0004\u0012\u00020s\u0018\u00010\u0098\u0001¢\u0006\u0003\bÕ\u0002H\u0002¢\u0006\u0006\bâ\u0002\u0010ã\u0002J\u0019\u0010é\u0002\u001a\u0005\u0018\u00010\u0080\u0001*\u00020M2\u0007\u0010\u0082\u0002\u001a\u00020\u001eH\u0002J\t\u0010ê\u0002\u001a\u00020sH\u0002J\t\u0010ë\u0002\u001a\u00020sH\u0002J\u0012\u0010ì\u0002\u001a\u00020s2\u0007\u0010í\u0002\u001a\u00020dH\u0002J\t\u0010î\u0002\u001a\u00020sH\u0002J\u0012\u0010ï\u0002\u001a\u00020s2\u0007\u0010ð\u0002\u001a\u00020\u001eH\u0002J\t\u0010ñ\u0002\u001a\u00020sH\u0002J\t\u0010ò\u0002\u001a\u00020sH\u0002J\t\u0010ó\u0002\u001a\u00020sH\u0002J\u000f\u0010ô\u0002\u001a\u00020sH\u0010¢\u0006\u0003\bõ\u0002J2\u0010ö\u0002\u001a\u00020s2\u0007\u0010÷\u0002\u001a\u00020\u001e2\u0006\u0010 \u001a\u00020\u001e2\t\u0010\u007f\u001a\u0005\u0018\u00010\u0080\u00012\n\u0010ô\u0001\u001a\u0005\u0018\u00010\u0080\u0001H\u0082\bJ\u001b\u0010ø\u0002\u001a\u00020s2\u0007\u0010÷\u0002\u001a\u00020\u001e2\u0006\u0010 \u001a\u00020\u001eH\u0082\bJ2\u0010ù\u0002\u001a\u00020s2\u0007\u0010÷\u0002\u001a\u00020\u001e2\u0006\u0010 \u001a\u00020\u001e2\t\u0010\u007f\u001a\u0005\u0018\u00010\u0080\u00012\n\u0010ô\u0001\u001a\u0005\u0018\u00010\u0080\u0001H\u0082\bJ\u001b\u0010ú\u0002\u001a\u00020s2\u0007\u0010÷\u0002\u001a\u00020\u001e2\u0006\u0010 \u001a\u00020\u001eH\u0082\bJ\u000f\u0010û\u0002\u001a\u00020\u001eH\u0010¢\u0006\u0003\bü\u0002J\f\u0010\u0084\u0003\u001a\u0005\u0018\u00010\u0080\u0001H\u0016J\u0014\u0010\u0085\u0003\u001a\u00020s2\t\u0010C\u001a\u0005\u0018\u00010\u0080\u0001H\u0016J\u0013\u0010\u0086\u0003\u001a\u00020s2\b\u0010\u0099\u0002\u001a\u00030þ\u0002H\u0016R\u0018\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\u00020\u0011X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0018\u0010\u0018\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001a0\u0019X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001bR\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\"X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010#\u001a\u0004\u0018\u00010$X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010%\u001a\u0004\u0018\u00010&X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020(X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020(X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020(X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010+\u001a\b\u0012\u0004\u0012\u00020-0,X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020\"X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u000200X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u00101\u001a\n\u0012\u0004\u0012\u000200\u0018\u000102X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00103\u001a\u00020(X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00104\u001a\u00020\"X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00105\u001a\u00020(X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00106\u001a\u00020\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00107\u001a\u00020\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00108\u001a\u00020\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u00109\u001a\u00020(X\u0090\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b:\u0010;\"\u0004\b<\u0010=R\u0010\u0010>\u001a\u00020?X\u0082\u0004¢\u0006\u0004\n\u0002\u0010@R\u0016\u0010A\u001a\b\u0012\u0004\u0012\u00020B0\u0019X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001bR\u001e\u0010D\u001a\u00020(2\u0006\u0010C\u001a\u00020(@RX\u0090\u000e¢\u0006\b\n\u0000\u001a\u0004\bE\u0010;R\u001e\u0010F\u001a\u00020(2\u0006\u0010C\u001a\u00020(@BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\bG\u0010;R\u0014\u0010H\u001a\u00020(8PX\u0090\u0004¢\u0006\u0006\u001a\u0004\bI\u0010;R\u0014\u0010J\u001a\u00020(8PX\u0090\u0004¢\u0006\u0006\u001a\u0004\bK\u0010;R\u001a\u0010L\u001a\u00020MX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bN\u0010O\"\u0004\bP\u0010QR\u001a\u0010R\u001a\u00020\u0007X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bS\u0010T\"\u0004\bU\u0010VR\u000e\u0010W\u001a\u00020XX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010Y\u001a\u00020(X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010Z\u001a\u0004\u0018\u000100X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010[\u001a\u0004\u0018\u00010\\X\u0090\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b]\u0010^\"\u0004\b_\u0010`R\u000e\u0010a\u001a\u00020bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010c\u001a\u00020dX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010e\u001a\u00020fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010g\u001a\u00020(X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010h\u001a\u0004\u0018\u00010iX\u0082\u000e¢\u0006\u0002\n\u0000R\u0018\u0010j\u001a\u0004\u0018\u00010k8PX\u0090\u0004¢\u0006\b\n\u0000\u001a\u0004\bl\u0010mR\u0014\u0010n\u001a\u00020oX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bp\u0010qR\u001a\u0010z\u001a\u00020(8VX\u0097\u0004¢\u0006\f\u0012\u0004\b{\u0010|\u001a\u0004\b}\u0010;R)\u0010\u0087\u0001\u001a\u00020(2\u0006\u0010C\u001a\u00020(8\u0016@RX\u0097\u000e¢\u0006\u0010\n\u0000\u0012\u0005\b\u0088\u0001\u0010|\u001a\u0005\b\u0089\u0001\u0010;R\u001d\u0010\u008a\u0001\u001a\u00020(8VX\u0097\u0004¢\u0006\u000e\u0012\u0005\b\u008b\u0001\u0010|\u001a\u0005\b\u008c\u0001\u0010;R9\u0010\u008f\u0001\u001a\b0\u008d\u0001j\u0003`\u008e\u00012\f\u0010C\u001a\b0\u008d\u0001j\u0003`\u008e\u00018\u0016@RX\u0097\u000e¢\u0006\u0014\n\u0003\u0010\u0093\u0001\u0012\u0005\b\u0090\u0001\u0010|\u001a\u0006\b\u0091\u0001\u0010\u0092\u0001R\u0017\u0010°\u0001\u001a\u00020\u001e8VX\u0096\u0004¢\u0006\b\u001a\u0006\b±\u0001\u0010²\u0001R\u0012\u0010Î\u0001\u001a\u0005\u0018\u00010Ï\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u0018\u0010Ð\u0001\u001a\u00030Ï\u00018VX\u0096\u0004¢\u0006\b\u001a\u0006\bÑ\u0001\u0010Ò\u0001R\u0018\u0010Ö\u0001\u001a\u00030×\u00018VX\u0096\u0004¢\u0006\b\u001a\u0006\bØ\u0001\u0010Ù\u0001R\u0019\u0010ì\u0001\u001a\u0004\u0018\u00010B8PX\u0090\u0004¢\u0006\b\u001a\u0006\bí\u0001\u0010î\u0001R\u0013\u0010ä\u0002\u001a\u00020(8F¢\u0006\u0007\u001a\u0005\bå\u0002\u0010;R\u001e\u0010æ\u0002\u001a\u0005\u0018\u00010\u0080\u0001*\u00020M8BX\u0082\u0004¢\u0006\b\u001a\u0006\bç\u0002\u0010è\u0002R\u001a\u0010ý\u0002\u001a\u0005\u0018\u00010þ\u00028VX\u0096\u0004¢\u0006\b\u001a\u0006\bÿ\u0002\u0010\u0080\u0003R\u001a\u0010\u0081\u0003\u001a\u0005\u0018\u00010\u0080\u00018VX\u0096\u0004¢\u0006\b\u001a\u0006\b\u0082\u0003\u0010\u0083\u0003¨\u0006\u0089\u0003"}, d2 = {"Landroidx/compose/runtime/GapComposer;", "Landroidx/compose/runtime/InternalComposer;", "applier", "Landroidx/compose/runtime/Applier;", "parentContext", "Landroidx/compose/runtime/CompositionContext;", "slotTable", "Landroidx/compose/runtime/composer/gapbuffer/SlotTable;", "abandonSet", "", "Landroidx/compose/runtime/RememberObserver;", "changes", "Landroidx/compose/runtime/Changes;", "lateChanges", "observerHolder", "Landroidx/compose/runtime/CompositionObserverHolder;", "composition", "Landroidx/compose/runtime/CompositionImpl;", "<init>", "(Landroidx/compose/runtime/Applier;Landroidx/compose/runtime/CompositionContext;Landroidx/compose/runtime/composer/gapbuffer/SlotTable;Ljava/util/Set;Landroidx/compose/runtime/Changes;Landroidx/compose/runtime/Changes;Landroidx/compose/runtime/CompositionObserverHolder;Landroidx/compose/runtime/CompositionImpl;)V", "getApplier", "()Landroidx/compose/runtime/Applier;", "getComposition", "()Landroidx/compose/runtime/CompositionImpl;", "pendingStack", "Landroidx/compose/runtime/Stack;", "Landroidx/compose/runtime/GapPending;", "Ljava/util/ArrayList;", "pending", "nodeIndex", "", "groupNodeCount", "rGroupIndex", "parentStateStack", "Landroidx/compose/runtime/IntStack;", "nodeCountOverrides", "", "nodeCountVirtualOverrides", "Landroidx/collection/MutableIntIntMap;", "forceRecomposeScopes", "", "forciblyRecompose", "nodeExpected", "invalidations", "", "Landroidx/compose/runtime/Invalidation;", "entersStack", "rootProvider", "Landroidx/compose/runtime/PersistentCompositionLocalMap;", "providerUpdates", "Landroidx/collection/MutableIntObjectMap;", "providersInvalid", "providersInvalidStack", "reusing", "reusingGroup", "childrenComposing", "compositionToken", "sourceMarkersEnabled", "getSourceMarkersEnabled$runtime", "()Z", "setSourceMarkersEnabled$runtime", "(Z)V", "derivedStateObserver", "androidx/compose/runtime/GapComposer$derivedStateObserver$1", "Landroidx/compose/runtime/GapComposer$derivedStateObserver$1;", "invalidateStack", "Landroidx/compose/runtime/RecomposeScopeImpl;", "value", "isComposing", "isComposing$runtime", "isDisposed", "isDisposed$runtime", "areChildrenComposing", "getAreChildrenComposing$runtime", "hasPendingChanges", "getHasPendingChanges$runtime", "reader", "Landroidx/compose/runtime/composer/gapbuffer/SlotReader;", "getReader$runtime", "()Landroidx/compose/runtime/composer/gapbuffer/SlotReader;", "setReader$runtime", "(Landroidx/compose/runtime/composer/gapbuffer/SlotReader;)V", "insertTable", "getInsertTable$runtime", "()Landroidx/compose/runtime/composer/gapbuffer/SlotTable;", "setInsertTable$runtime", "(Landroidx/compose/runtime/composer/gapbuffer/SlotTable;)V", "writer", "Landroidx/compose/runtime/composer/gapbuffer/SlotWriter;", "writerHasAProvider", "providerCache", "deferredChanges", "Landroidx/compose/runtime/composer/gapbuffer/changelist/ChangeList;", "getDeferredChanges$runtime", "()Landroidx/compose/runtime/composer/gapbuffer/changelist/ChangeList;", "setDeferredChanges$runtime", "(Landroidx/compose/runtime/composer/gapbuffer/changelist/ChangeList;)V", "changeListWriter", "Landroidx/compose/runtime/composer/gapbuffer/changelist/ComposerChangeListWriter;", "insertAnchor", "Landroidx/compose/runtime/composer/gapbuffer/GapAnchor;", "insertFixups", "Landroidx/compose/runtime/composer/gapbuffer/changelist/FixupList;", "pausable", "shouldPauseCallback", "Landroidx/compose/runtime/ShouldPauseCallback;", "errorContext", "Landroidx/compose/runtime/tooling/CompositionErrorContextImpl;", "getErrorContext$runtime", "()Landroidx/compose/runtime/tooling/CompositionErrorContextImpl;", "applyCoroutineContext", "Lkotlin/coroutines/CoroutineContext;", "getApplyCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "startReplaceableGroup", "", "key", "endReplaceableGroup", "startReplaceGroup", "endReplaceGroup", "startDefaults", "endDefaults", "defaultsInvalid", "getDefaultsInvalid$annotations", "()V", "getDefaultsInvalid", "startMovableGroup", "dataKey", "", "endMovableGroup", "startRoot", "endRoot", "abortRoot", "changesApplied", "changesApplied$runtime", "inserting", "getInserting$annotations", "getInserting", "skipping", "getSkipping$annotations", "getSkipping", "", "Landroidx/compose/runtime/CompositeKeyHashCode;", "compositeKeyHashCode", "getCompositeKeyHashCode$annotations", "getCompositeKeyHashCode", "()J", "J", "collectParameterInformation", "scheduleFrameEndCallback", "Landroidx/compose/runtime/CancellationHandle;", "action", "Lkotlin/Function0;", "dispose", "dispose$runtime", "deactivate", "deactivate$runtime", "forceRecomposeScopes$runtime", "startGroup", "endGroup", "skipGroup", "startNode", "startReusableNode", "createNode", "T", "factory", "useNode", "endNode", "startReusableGroup", "endReusableGroup", "disableReusing", "enableReusing", "startReuseFromRoot", "startReuseFromRoot$runtime", "endReuseFromRoot", "endReuseFromRoot$runtime", "currentMarker", "getCurrentMarker", "()I", "endToMarker", "marker", "apply", "V", "block", "Lkotlin/Function2;", "Lkotlin/ExtensionFunctionType;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)V", "joinKey", "left", "right", "nextSlot", "nextSlotForCache", "changed", "changedInstance", "", "", "", "", "", "cache", "invalid", "(ZLkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "updateSlot", "updateValue", "updateCachedValue", "rememberObserverGroupIndex", "_compositionData", "Landroidx/compose/runtime/tooling/CompositionData;", "compositionData", "getCompositionData", "()Landroidx/compose/runtime/tooling/CompositionData;", "recordSideEffect", "effect", "currentCompositionLocalScope", "currentCompositionLocalMap", "Landroidx/compose/runtime/CompositionLocalMap;", "getCurrentCompositionLocalMap", "()Landroidx/compose/runtime/CompositionLocalMap;", "group", "updateProviderMapGroup", "parentScope", "currentProviders", "startProvider", "Landroidx/compose/runtime/ProvidedValue;", "recordProviderUpdate", "providers", "endProvider", "startProviders", "values", "", "([Landroidx/compose/runtime/ProvidedValue;)V", "endProviders", "consume", "Landroidx/compose/runtime/CompositionLocal;", "(Landroidx/compose/runtime/CompositionLocal;)Ljava/lang/Object;", "buildContext", "currentRecomposeScope", "getCurrentRecomposeScope$runtime", "()Landroidx/compose/runtime/RecomposeScopeImpl;", "ensureWriter", "createFreshInsertTable", "forceFreshInsertTable", "startReaderGroup", "isNode", "data", "start", "objectKey", "kind", "Landroidx/compose/runtime/composer/GroupKind;", "start-AzEfcrM", "(ILjava/lang/Object;ILjava/lang/Object;)V", "enterGroup", "newPending", "exitGroup", "expectedNodeCount", "end", "recomposeToGroupEnd", "insertedGroupVirtualIndex", "index", "updateNodeCountOverrides", "newCount", "nodeIndexOf", "groupLocation", "recomposeGroup", "recomposeIndex", "rGroupIndexOf", "updatedNodeCount", "updateNodeCount", "count", "clearUpdatedNodeCounts", "recordUpsAndDowns", "oldGroup", "newGroup", "commonRoot", "doRecordDownsFor", "nearestCommonRoot", "compositeKeyOf", "recomposeKey", "(IIJ)J", "groupCompositeKeyPart", "tryImminentInvalidation", "scope", "instance", "tryImminentInvalidation$runtime", "parentKey", "parentKey$runtime", "skipCurrentGroup", "skipReaderToGroupEnd", "shouldExecute", "parametersChanged", "flags", "skipToGroupEnd", "deactivateToEndGroup", "startRestartGroup", "Landroidx/compose/runtime/Composer;", "addRecomposeScope", "enterRecomposeScope", "endRestartGroup", "Landroidx/compose/runtime/ScopeUpdateScope;", "exitRecomposeScope", "Lkotlin/Function1;", "Landroidx/compose/runtime/Composition;", "insertMovableContent", "Landroidx/compose/runtime/MovableContent;", "parameter", "invokeMovableContentLambda", "content", "locals", "force", "insertMovableContentReferences", "references", "", "Lkotlin/Pair;", "Landroidx/compose/runtime/MovableContentStateReference;", "insertMovableContentGuarded", "withReader", "R", "(Landroidx/compose/runtime/composer/gapbuffer/SlotReader;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "recomposeMovableContent", TypedValues.TransitionType.S_FROM, "Landroidx/compose/runtime/ControlledComposition;", TypedValues.TransitionType.S_TO, "(Landroidx/compose/runtime/ControlledComposition;Landroidx/compose/runtime/ControlledComposition;Ljava/lang/Integer;Ljava/util/List;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "sourceInformation", "", "sourceInformationMarkerStart", "sourceInformationMarkerEnd", "disableSourceInformation", "stackTraceForValue", "Landroidx/compose/runtime/tooling/ComposeStackTrace;", "stackTraceForValue$runtime", "currentStackTrace", "stackTraceForGroup", "Landroidx/compose/runtime/tooling/ComposeStackTraceFrame;", "dataOffset", "(ILjava/lang/Integer;)Ljava/util/List;", "parentStackTrace", "parentStackTrace$runtime", "composeContent", "invalidationsRequested", "Landroidx/compose/runtime/collection/ScopeMap;", "Landroidx/compose/runtime/Composable;", "shouldPause", "composeContent--ZbOJvo$runtime", "(Landroidx/collection/MutableScatterMap;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/ShouldPauseCallback;)V", "prepareCompose", "prepareCompose$runtime", "recompose", "recompose-aFTiNEg$runtime", "(Landroidx/collection/MutableScatterMap;Landroidx/compose/runtime/ShouldPauseCallback;)Z", "updateComposerInvalidations", "updateComposerInvalidations-RY85e9Y$runtime", "(Landroidx/collection/MutableScatterMap;)V", "doCompose", "doCompose-aFTiNEg", "(Landroidx/collection/MutableScatterMap;Lkotlin/jvm/functions/Function2;)V", "hasInvalidations", "getHasInvalidations", "node", "getNode", "(Landroidx/compose/runtime/composer/gapbuffer/SlotReader;)Ljava/lang/Object;", "nodeAt", "validateNodeExpected", "validateNodeNotExpected", "recordInsert", "anchor", "recordDelete", "reportFreeMovableContent", "groupBeingRemoved", "reportAllMovableContent", "finalizeCompose", "cleanUpCompose", "verifyConsistent", "verifyConsistent$runtime", "updateCompositeKeyWhenWeEnterGroup", "groupKey", "updateCompositeKeyWhenWeEnterGroupKeyHash", "updateCompositeKeyWhenWeExitGroup", "updateCompositeKeyWhenWeExitGroupKeyHash", "stacksSize", "stacksSize$runtime", "recomposeScope", "Landroidx/compose/runtime/RecomposeScope;", "getRecomposeScope", "()Landroidx/compose/runtime/RecomposeScope;", "recomposeScopeIdentity", "getRecomposeScopeIdentity", "()Ljava/lang/Object;", "rememberedValue", "updateRememberedValue", "recordUsed", "CompositionContextHolder", "CompositionContextImpl", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class GapComposer extends InternalComposer {
    public static final int $stable = 8;
    private CompositionData _compositionData;
    private final Set<RememberObserver> abandonSet;
    private final Applier<?> applier;
    private final CoroutineContext applyCoroutineContext;
    private final ComposerChangeListWriter changeListWriter;
    private Changes changes;
    private int childrenComposing;
    private long compositeKeyHashCode;
    private final CompositionImpl composition;
    private int compositionToken;
    private ChangeList deferredChanges;
    private final GapComposer$derivedStateObserver$1 derivedStateObserver;
    private final CompositionErrorContextImpl errorContext;
    private boolean forceRecomposeScopes;
    private boolean forciblyRecompose;
    private int groupNodeCount;
    private GapAnchor insertAnchor;
    private FixupList insertFixups;
    private SlotTable insertTable;
    private boolean inserting;
    private final ArrayList<RecomposeScopeImpl> invalidateStack;
    private boolean isComposing;
    private boolean isDisposed;
    private Changes lateChanges;
    private int[] nodeCountOverrides;
    private MutableIntIntMap nodeCountVirtualOverrides;
    private boolean nodeExpected;
    private int nodeIndex;
    private final CompositionObserverHolder observerHolder;
    private final CompositionContext parentContext;
    private boolean pausable;
    private GapPending pending;
    private PersistentCompositionLocalMap providerCache;
    private MutableIntObjectMap<PersistentCompositionLocalMap> providerUpdates;
    private boolean providersInvalid;
    private int rGroupIndex;
    private SlotReader reader;
    private boolean reusing;
    private ShouldPauseCallback shouldPauseCallback;
    private final SlotTable slotTable;
    private boolean sourceMarkersEnabled;
    private SlotWriter writer;
    private boolean writerHasAProvider;
    private final ArrayList<GapPending> pendingStack = Stack.m4418constructorimpl$default(null, 1, null);
    private final IntStack parentStateStack = new IntStack();
    private final List<Invalidation> invalidations = new ArrayList();
    private final IntStack entersStack = new IntStack();
    private PersistentCompositionLocalMap rootProvider = PersistentCompositionLocalMapKt.persistentCompositionLocalHashMapOf();
    private final IntStack providersInvalidStack = new IntStack();
    private int reusingGroup = -1;

    public static /* synthetic */ void getCompositeKeyHashCode$annotations() {
    }

    @ComposeCompilerApi
    public static /* synthetic */ void getDefaultsInvalid$annotations() {
    }

    @ComposeCompilerApi
    public static /* synthetic */ void getInserting$annotations() {
    }

    @ComposeCompilerApi
    public static /* synthetic */ void getSkipping$annotations() {
    }

    /* JADX WARN: Type inference failed for: r2v13, types: [androidx.compose.runtime.GapComposer$derivedStateObserver$1] */
    public GapComposer(Applier<?> applier, CompositionContext parentContext, SlotTable slotTable, Set<RememberObserver> set, Changes changes, Changes lateChanges, CompositionObserverHolder observerHolder, CompositionImpl composition) {
        this.applier = applier;
        this.parentContext = parentContext;
        this.slotTable = slotTable;
        this.abandonSet = set;
        this.changes = changes;
        this.lateChanges = lateChanges;
        this.observerHolder = observerHolder;
        this.composition = composition;
        this.sourceMarkersEnabled = this.parentContext.getCollectingSourceInformation() || this.parentContext.getCollectingCallByInformation$runtime();
        this.derivedStateObserver = new DerivedStateObserver() { // from class: androidx.compose.runtime.GapComposer$derivedStateObserver$1
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
        SlotReader it = this.slotTable.openReader();
        it.close();
        this.reader = it;
        SlotTable $this$insertTable_u24lambda_u240 = new SlotTable();
        if (this.parentContext.getCollectingSourceInformation()) {
            $this$insertTable_u24lambda_u240.collectSourceInformation();
        }
        if (this.parentContext.getCollectingCallByInformation$runtime()) {
            $this$insertTable_u24lambda_u240.collectCalledByInformation();
        }
        this.insertTable = $this$insertTable_u24lambda_u240;
        SlotWriter it2 = this.insertTable.openWriter();
        it2.close(true);
        this.writer = it2;
        this.changeListWriter = new ComposerChangeListWriter(this, ChangeListKt.asGapBufferChangeList(this.changes));
        SlotTable this_$iv = this.insertTable;
        SlotReader reader$iv = this_$iv.openReader();
        try {
            GapAnchor gapAnchorAnchor = reader$iv.anchor(0);
            reader$iv.close();
            this.insertAnchor = gapAnchorAnchor;
            this.insertFixups = new FixupList();
            this.errorContext = new CompositionErrorContextImpl(this);
            CoroutineContext effectCoroutineContext = this.parentContext.getEffectCoroutineContext();
            CoroutineContext errorContext$runtime = getErrorContext$runtime();
            this.applyCoroutineContext = effectCoroutineContext.plus(errorContext$runtime == null ? EmptyCoroutineContext.INSTANCE : errorContext$runtime);
        } catch (Throwable th) {
            reader$iv.close();
            throw th;
        }
    }

    @Override // androidx.compose.runtime.Composer
    public Applier<?> getApplier() {
        return this.applier;
    }

    @Override // androidx.compose.runtime.Composer
    public CompositionImpl getComposition() {
        return this.composition;
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
    public boolean getHasPendingChanges$runtime() {
        return this.changes.isNotEmpty();
    }

    /* JADX INFO: renamed from: getReader$runtime, reason: from getter */
    public final SlotReader getReader() {
        return this.reader;
    }

    public final void setReader$runtime(SlotReader slotReader) {
        this.reader = slotReader;
    }

    /* JADX INFO: renamed from: getInsertTable$runtime, reason: from getter */
    public final SlotTable getInsertTable() {
        return this.insertTable;
    }

    public final void setInsertTable$runtime(SlotTable slotTable) {
        this.insertTable = slotTable;
    }

    @Override // androidx.compose.runtime.InternalComposer
    /* JADX INFO: renamed from: getDeferredChanges$runtime, reason: from getter */
    public ChangeList getDeferredChanges() {
        return this.deferredChanges;
    }

    public void setDeferredChanges$runtime(ChangeList changeList) {
        this.deferredChanges = changeList;
    }

    @Override // androidx.compose.runtime.InternalComposer
    public CompositionErrorContextImpl getErrorContext$runtime() {
        if (this.parentContext.getStackTraceEnabled$runtime()) {
            return this.errorContext;
        }
        return null;
    }

    @Override // androidx.compose.runtime.Composer
    public CoroutineContext getApplyCoroutineContext() {
        return this.applyCoroutineContext;
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public void startReplaceableGroup(int key) {
        m4393startAzEfcrM(key, null, GroupKind.INSTANCE.m4502getGroup9udXigM(), null);
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public void endReplaceableGroup() {
        endGroup();
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public void startReplaceGroup(int key) {
        GapPending pending = this.pending;
        if (pending != null) {
            m4393startAzEfcrM(key, null, GroupKind.INSTANCE.m4502getGroup9udXigM(), null);
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
        SlotReader reader = this.reader;
        if (getInserting()) {
            reader.beginEmpty();
            this.writer.startGroup(key, Composer.INSTANCE.getEmpty());
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
            int startSlot = reader.getCurrent();
            recordDelete();
            int nodesToRemove = reader.skipGroup();
            this.changeListWriter.removeNode(removeIndex, nodesToRemove);
            GapComposerKt.removeRange(this.invalidations, startSlot, reader.getCurrent());
        }
        reader.beginEmpty();
        this.inserting = true;
        this.providerCache = null;
        ensureWriter();
        SlotWriter writer = this.writer;
        writer.beginInsert();
        int startIndex = writer.getCurrentGroup();
        writer.startGroup(key, Composer.INSTANCE.getEmpty());
        this.insertAnchor = writer.anchor(startIndex);
        enterGroup(false, null);
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public void endReplaceGroup() {
        endGroup();
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public void startDefaults() {
        m4393startAzEfcrM(ComposerKt.defaultsKey, null, GroupKind.INSTANCE.m4502getGroup9udXigM(), null);
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public void endDefaults() {
        endGroup();
        RecomposeScopeImpl scope = getCurrentRecomposeScope$runtime();
        if (scope != null && scope.getUsed()) {
            scope.setDefaultsInScope(true);
        }
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
    @ComposeCompilerApi
    public void startMovableGroup(int key, Object dataKey) {
        m4393startAzEfcrM(key, dataKey, GroupKind.INSTANCE.m4502getGroup9udXigM(), null);
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public void endMovableGroup() {
        endGroup();
    }

    private final void startRoot() {
        PersistentCompositionLocalMap persistentCompositionLocalMapPutValue;
        this.rGroupIndex = 0;
        this.reader = this.slotTable.openReader();
        startGroup(100);
        this.parentContext.startComposing$runtime();
        PersistentCompositionLocalMap parentProvider = this.parentContext.getCompositionLocalScope$runtime();
        this.providersInvalidStack.push(GapComposerKt.asInt(this.providersInvalid));
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
        startGroup(Long.hashCode(this.parentContext.getCompositeKeyHashCode()));
    }

    private final void endRoot() {
        endGroup();
        this.parentContext.doneComposing$runtime();
        endGroup();
        this.changeListWriter.endRoot();
        finalizeCompose();
        this.reader.close();
        this.forciblyRecompose = false;
        this.providersInvalid = GapComposerKt.asBool(this.providersInvalidStack.pop());
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
        if (!this.reader.getClosed()) {
            this.reader.close();
        }
        if (!this.writer.getClosed()) {
            forceFreshInsertTable();
        }
    }

    @Override // androidx.compose.runtime.InternalComposer
    public void changesApplied$runtime() {
        this.providerUpdates = null;
    }

    @Override // androidx.compose.runtime.Composer
    public boolean getInserting() {
        return this.inserting;
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
    public long getCompositeKeyHashCode() {
        return this.compositeKeyHashCode;
    }

    @Override // androidx.compose.runtime.Composer
    public void collectParameterInformation() {
        this.forceRecomposeScopes = true;
        setSourceMarkersEnabled$runtime(true);
        this.slotTable.collectSourceInformation();
        this.insertTable.collectSourceInformation();
        this.writer.updateToTableMaps();
    }

    @Override // androidx.compose.runtime.Composer
    public CancellationHandle scheduleFrameEndCallback(Function0<Unit> action) {
        return this.parentContext.scheduleFrameEndCallback(action);
    }

    @Override // androidx.compose.runtime.InternalComposer
    public void dispose$runtime() {
        Object token$iv = Trace.INSTANCE.beginSection("Compose:Composer.dispose");
        try {
            this.parentContext.unregisterComposer$runtime(this);
            deactivate$runtime();
            getApplier().clear();
            this.isDisposed = true;
            Unit unit = Unit.INSTANCE;
        } finally {
            Trace.INSTANCE.endSection(token$iv);
        }
    }

    @Override // androidx.compose.runtime.InternalComposer
    public void deactivate$runtime() {
        Stack.m4416clearimpl(this.invalidateStack);
        this.invalidations.clear();
        this.changes.clear();
        this.providerUpdates = null;
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

    private final void startGroup(int key) {
        m4393startAzEfcrM(key, null, GroupKind.INSTANCE.m4502getGroup9udXigM(), null);
    }

    private final void startGroup(int key, Object dataKey) {
        m4393startAzEfcrM(key, dataKey, GroupKind.INSTANCE.m4502getGroup9udXigM(), null);
    }

    private final void endGroup() {
        end(false);
    }

    private final void skipGroup() {
        this.groupNodeCount += this.reader.skipGroup();
    }

    @Override // androidx.compose.runtime.Composer
    public void startNode() {
        m4393startAzEfcrM(GapComposerKt.nodeKey, null, GroupKind.INSTANCE.m4503getNode9udXigM(), null);
        this.nodeExpected = true;
    }

    @Override // androidx.compose.runtime.Composer
    public void startReusableNode() {
        m4393startAzEfcrM(GapComposerKt.nodeKey, null, GroupKind.INSTANCE.m4504getReusableNode9udXigM(), null);
        this.nodeExpected = true;
    }

    @Override // androidx.compose.runtime.Composer
    public <T> void createNode(Function0<? extends T> factory) {
        validateNodeExpected();
        boolean value$iv = getInserting();
        if (!value$iv) {
            ComposerKt.composeImmediateRuntimeError("createNode() can only be called when inserting");
        }
        int insertIndex = this.parentStateStack.peek();
        GapAnchor groupAnchor = this.writer.anchor(this.writer.getParent());
        this.groupNodeCount++;
        this.insertFixups.createAndInsertNode(factory, insertIndex, groupAnchor);
    }

    @Override // androidx.compose.runtime.Composer
    public void useNode() {
        validateNodeExpected();
        boolean value$iv = !getInserting();
        if (!value$iv) {
            ComposerKt.composeImmediateRuntimeError("useNode() called while inserting");
        }
        Object node = getNode(this.reader);
        this.changeListWriter.moveDown(node);
        if (this.reusing && (node instanceof ComposeNodeLifecycleCallback)) {
            this.changeListWriter.useNode(node);
        }
    }

    @Override // androidx.compose.runtime.Composer
    public void endNode() {
        end(true);
    }

    @Override // androidx.compose.runtime.Composer
    public void startReusableGroup(int key, Object dataKey) {
        if (!getInserting() && this.reader.getGroupKey() == key && !Intrinsics.areEqual(this.reader.getGroupAux(), dataKey) && this.reusingGroup < 0) {
            this.reusingGroup = this.reader.getCurrent();
            this.reusing = true;
        }
        m4393startAzEfcrM(key, null, GroupKind.INSTANCE.m4502getGroup9udXigM(), dataKey);
    }

    @Override // androidx.compose.runtime.Composer
    public void endReusableGroup() {
        if (this.reusing && this.reader.getParent() == this.reusingGroup) {
            this.reusingGroup = -1;
            this.reusing = false;
        }
        end(false);
    }

    @Override // androidx.compose.runtime.Composer
    public void disableReusing() {
        this.reusing = false;
    }

    @Override // androidx.compose.runtime.Composer
    public void enableReusing() {
        this.reusing = this.reusingGroup >= 0;
    }

    @Override // androidx.compose.runtime.InternalComposer
    public void startReuseFromRoot$runtime() {
        this.reusingGroup = 0;
        this.reusing = true;
    }

    @Override // androidx.compose.runtime.InternalComposer
    public void endReuseFromRoot$runtime() {
        boolean value$iv = !getIsComposing() && this.reusingGroup == 0;
        if (!value$iv) {
            PreconditionsKt.throwIllegalArgumentException("Cannot disable reuse from root if it was caused by other groups");
        }
        this.reusingGroup = -1;
        this.reusing = false;
    }

    @Override // androidx.compose.runtime.Composer
    public int getCurrentMarker() {
        return getInserting() ? -this.writer.getParent() : this.reader.getParent();
    }

    @Override // androidx.compose.runtime.Composer
    public void endToMarker(int marker) {
        if (marker < 0) {
            int writerLocation = -marker;
            SlotWriter writer = this.writer;
            while (true) {
                int parent = writer.getParent();
                if (parent > writerLocation) {
                    end(writer.isNode(parent));
                } else {
                    return;
                }
            }
        } else {
            if (getInserting()) {
                SlotWriter writer2 = this.writer;
                while (getInserting()) {
                    end(writer2.isNode(writer2.getParent()));
                }
            }
            SlotReader reader = this.reader;
            while (true) {
                int parent2 = reader.getParent();
                if (parent2 > marker) {
                    end(reader.isNode(parent2));
                } else {
                    return;
                }
            }
        }
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
    @ComposeCompilerApi
    public Object joinKey(Object left, Object right) {
        Object key = GapComposerKt.getKey(this.reader.getGroupObjectKey(), left, right);
        return key == null ? new JoinedKey(left, right) : key;
    }

    public final Object nextSlot() {
        if (getInserting()) {
            validateNodeNotExpected();
            return Composer.INSTANCE.getEmpty();
        }
        Object it = this.reader.next();
        return (!this.reusing || (it instanceof ReusableRememberObserverHolder)) ? it : Composer.INSTANCE.getEmpty();
    }

    public final Object nextSlotForCache() {
        if (getInserting()) {
            validateNodeNotExpected();
            return Composer.INSTANCE.getEmpty();
        }
        Object it = this.reader.next();
        return (!this.reusing || (it instanceof ReusableRememberObserverHolder)) ? it instanceof RememberObserverHolder ? ((RememberObserverHolder) it).getWrapped() : it : Composer.INSTANCE.getEmpty();
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public boolean changed(Object value) {
        if (!Intrinsics.areEqual(nextSlot(), value)) {
            updateValue(value);
            return true;
        }
        return false;
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public boolean changedInstance(Object value) {
        if (nextSlot() != value) {
            updateValue(value);
            return true;
        }
        return false;
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public boolean changed(char value) {
        Object next = nextSlot();
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
    @ComposeCompilerApi
    public boolean changed(byte value) {
        Object next = nextSlot();
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
    @ComposeCompilerApi
    public boolean changed(short value) {
        Object next = nextSlot();
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
    @ComposeCompilerApi
    public boolean changed(boolean value) {
        Object next = nextSlot();
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
    @ComposeCompilerApi
    public boolean changed(float value) {
        Object next = nextSlot();
        if (next instanceof Float) {
            float nextPrimitive = ((Number) next).floatValue();
            if (value == nextPrimitive) {
                return false;
            }
        }
        updateValue(Float.valueOf(value));
        return true;
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public boolean changed(long value) {
        Object next = nextSlot();
        if (next instanceof Long) {
            long nextPrimitive = ((Number) next).longValue();
            if (value == nextPrimitive) {
                return false;
            }
        }
        updateValue(Long.valueOf(value));
        return true;
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public boolean changed(double value) {
        Object next = nextSlot();
        if (next instanceof Double) {
            double nextPrimitive = ((Number) next).doubleValue();
            if (value == nextPrimitive) {
                return false;
            }
        }
        updateValue(Double.valueOf(value));
        return true;
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public boolean changed(int value) {
        Object next = nextSlot();
        if (next instanceof Integer) {
            int nextPrimitive = ((Number) next).intValue();
            if (value == nextPrimitive) {
                return false;
            }
        }
        updateValue(Integer.valueOf(value));
        return true;
    }

    @ComposeCompilerApi
    public final <T> T cache(boolean invalid, Function0<? extends T> block) {
        T t = (T) nextSlotForCache();
        if (t == Composer.INSTANCE.getEmpty() || invalid) {
            T tInvoke = block.invoke();
            updateCachedValue(tInvoke);
            return tInvoke;
        }
        return t;
    }

    private final void updateSlot(Object value) {
        nextSlot();
        updateValue(value);
    }

    public final void updateValue(Object value) {
        if (getInserting()) {
            this.writer.update(value);
            return;
        }
        if (this.reader.getHadNext()) {
            int groupSlotIndex = this.reader.getGroupSlotIndex() - 1;
            boolean pastParent = this.changeListWriter.getPastParent();
            ComposerChangeListWriter composerChangeListWriter = this.changeListWriter;
            if (pastParent) {
                composerChangeListWriter.updateAnchoredValue(value, this.reader.anchor(this.reader.getParent()), groupSlotIndex);
                return;
            } else {
                composerChangeListWriter.updateValue(value, groupSlotIndex);
                return;
            }
        }
        this.changeListWriter.appendValue(this.reader.anchor(this.reader.getParent()), value);
    }

    public final void updateCachedValue(Object value) {
        Object toStore;
        if (value instanceof RememberObserver) {
            toStore = new GapRememberObserverHolder((RememberObserver) value, rememberObserverGroupIndex());
            if (getInserting()) {
                this.changeListWriter.remember((RememberObserverHolder) toStore);
            }
            this.abandonSet.add(value);
        } else {
            toStore = value;
        }
        updateValue(toStore);
    }

    private final int rememberObserverGroupIndex() {
        return this.rGroupIndex - 1;
    }

    @Override // androidx.compose.runtime.Composer
    public CompositionData getCompositionData() {
        CompositionData data = this._compositionData;
        if (data == null) {
            GapCompositionDataImpl newData = new GapCompositionDataImpl(getComposition());
            this._compositionData = newData;
            return newData;
        }
        return data;
    }

    @Override // androidx.compose.runtime.Composer
    public void recordSideEffect(Function0<Unit> effect) {
        this.changeListWriter.sideEffect(effect);
    }

    private final PersistentCompositionLocalMap currentCompositionLocalScope() {
        PersistentCompositionLocalMap it = this.providerCache;
        if (it != null) {
            return it;
        }
        return currentCompositionLocalScope(this.reader.getParent());
    }

    @Override // androidx.compose.runtime.Composer
    public CompositionLocalMap getCurrentCompositionLocalMap() {
        return currentCompositionLocalScope();
    }

    private final PersistentCompositionLocalMap currentCompositionLocalScope(int group) {
        PersistentCompositionLocalMap providers;
        if (getInserting() && this.writerHasAProvider) {
            int current = this.writer.getParent();
            while (current > 0) {
                if (this.writer.groupKey(current) == 202 && Intrinsics.areEqual(this.writer.groupObjectKey(current), ComposerKt.getCompositionLocalMap())) {
                    Object objGroupAux = this.writer.groupAux(current);
                    Intrinsics.checkNotNull(objGroupAux, "null cannot be cast to non-null type androidx.compose.runtime.PersistentCompositionLocalMap");
                    PersistentCompositionLocalMap providers2 = (PersistentCompositionLocalMap) objGroupAux;
                    this.providerCache = providers2;
                    return providers2;
                }
                current = this.writer.parent(current);
            }
        }
        if (this.reader.getGroupsSize() > 0) {
            int current2 = group;
            while (current2 > 0) {
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
                current2 = this.reader.parent(current2);
            }
        }
        this.providerCache = this.rootProvider;
        return this.rootProvider;
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
            this.writerHasAProvider = true;
        } else {
            Object objGroupAux = this.reader.groupAux(this.reader.getCurrent());
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
        this.providersInvalidStack.push(GapComposerKt.asInt(this.providersInvalid));
        this.providersInvalid = invalid;
        this.providerCache = oldScope;
        m4393startAzEfcrM(ComposerKt.compositionLocalMapKey, ComposerKt.getCompositionLocalMap(), GroupKind.INSTANCE.m4502getGroup9udXigM(), oldScope);
    }

    private final void recordProviderUpdate(PersistentCompositionLocalMap providers) {
        MutableIntObjectMap<PersistentCompositionLocalMap> mutableIntObjectMap = this.providerUpdates;
        if (mutableIntObjectMap == null) {
            GapComposer $this$recordProviderUpdate_u24lambda_u240 = this;
            MutableIntObjectMap<PersistentCompositionLocalMap> mutableIntObjectMap2 = new MutableIntObjectMap<>(0, 1, null);
            $this$recordProviderUpdate_u24lambda_u240.providerUpdates = mutableIntObjectMap2;
            mutableIntObjectMap = mutableIntObjectMap2;
        }
        mutableIntObjectMap.set(this.reader.getCurrent(), providers);
    }

    @Override // androidx.compose.runtime.Composer
    public void endProvider() {
        endGroup();
        endGroup();
        this.providersInvalid = GapComposerKt.asBool(this.providersInvalidStack.pop());
        this.providerCache = null;
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
            this.writerHasAProvider = true;
        } else {
            Object objGroupGet = this.reader.groupGet(0);
            Intrinsics.checkNotNull(objGroupGet, "null cannot be cast to non-null type androidx.compose.runtime.PersistentCompositionLocalMap");
            PersistentCompositionLocalMap oldScope = (PersistentCompositionLocalMap) objGroupGet;
            Object objGroupGet2 = this.reader.groupGet(1);
            Intrinsics.checkNotNull(objGroupGet2, "null cannot be cast to non-null type androidx.compose.runtime.PersistentCompositionLocalMap");
            PersistentCompositionLocalMap oldValues = (PersistentCompositionLocalMap) objGroupGet2;
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
        this.providersInvalidStack.push(GapComposerKt.asInt(this.providersInvalid));
        this.providersInvalid = invalid;
        this.providerCache = providers;
        m4393startAzEfcrM(ComposerKt.compositionLocalMapKey, ComposerKt.getCompositionLocalMap(), GroupKind.INSTANCE.m4502getGroup9udXigM(), providers);
    }

    @Override // androidx.compose.runtime.Composer
    public void endProviders() {
        endGroup();
        endGroup();
        this.providersInvalid = GapComposerKt.asBool(this.providersInvalidStack.pop());
        this.providerCache = null;
    }

    @Override // androidx.compose.runtime.Composer
    public <T> T consume(CompositionLocal<T> key) {
        return (T) CompositionLocalMapKt.read(currentCompositionLocalScope(), key);
    }

    @Override // androidx.compose.runtime.Composer
    public CompositionContext buildContext() {
        startGroup(ComposerKt.referenceKey, ComposerKt.getReference());
        if (getInserting()) {
            SlotWriter.markGroup$default(this.writer, 0, 1, null);
        }
        Object objNextSlot = nextSlot();
        RememberObserverHolder observerHolder = objNextSlot instanceof RememberObserverHolder ? (RememberObserverHolder) objNextSlot : null;
        if (observerHolder == null) {
            observerHolder = new ReusableGapRememberObserverHolder(new CompositionContextHolder(new CompositionContextImpl(getCompositeKeyHashCode(), this.forceRecomposeScopes, getSourceMarkersEnabled(), getComposition().getObserverHolder())), -1);
            updateValue(observerHolder);
        }
        RememberObserver wrapped = observerHolder.getWrapped();
        Intrinsics.checkNotNull(wrapped, "null cannot be cast to non-null type androidx.compose.runtime.GapComposer.CompositionContextHolder");
        CompositionContextHolder holder = (CompositionContextHolder) wrapped;
        holder.getRef().updateCompositionLocalScope(currentCompositionLocalScope());
        endGroup();
        return holder.getRef();
    }

    @Override // androidx.compose.runtime.InternalComposer
    public RecomposeScopeImpl getCurrentRecomposeScope$runtime() {
        ArrayList<RecomposeScopeImpl> arrayList = this.invalidateStack;
        if (this.childrenComposing == 0 && Stack.m4424isNotEmptyimpl(arrayList)) {
            return (RecomposeScopeImpl) Stack.m4425peekimpl(arrayList);
        }
        return null;
    }

    private final void ensureWriter() {
        if (this.writer.getClosed()) {
            this.writer = this.insertTable.openWriter();
            this.writer.skipToGroupEnd();
            this.writerHasAProvider = false;
            this.providerCache = null;
        }
    }

    private final void createFreshInsertTable() {
        boolean value$iv = this.writer.getClosed();
        if (!value$iv) {
            ComposerKt.composeImmediateRuntimeError("Check failed");
        }
        forceFreshInsertTable();
    }

    private final void forceFreshInsertTable() {
        SlotTable $this$forceFreshInsertTable_u24lambda_u240 = new SlotTable();
        if (getSourceMarkersEnabled()) {
            $this$forceFreshInsertTable_u24lambda_u240.collectSourceInformation();
        }
        if (this.parentContext.getCollectingCallByInformation$runtime()) {
            $this$forceFreshInsertTable_u24lambda_u240.collectCalledByInformation();
        }
        this.insertTable = $this$forceFreshInsertTable_u24lambda_u240;
        SlotWriter it = this.insertTable.openWriter();
        it.close(true);
        this.writer = it;
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

    /* JADX INFO: renamed from: start-AzEfcrM */
    private final void m4393startAzEfcrM(int key, Object objectKey, int kind, Object data) {
        validateNodeNotExpected();
        int rGroupIndex$iv = this.rGroupIndex;
        if (objectKey == null) {
            if (data == null || key != 207 || Intrinsics.areEqual(data, Composer.INSTANCE.getEmpty())) {
                long $this$compoundWith$iv$iv$iv = getCompositeKeyHashCode();
                long $this$rol$iv$iv$iv$iv = key;
                this.compositeKeyHashCode = Long.rotateLeft(Long.rotateLeft($this$compoundWith$iv$iv$iv, 3) ^ $this$rol$iv$iv$iv$iv, 3) ^ ((long) rGroupIndex$iv);
            } else {
                int groupKey$iv$iv = data.hashCode();
                long $this$compoundWith$iv$iv$iv2 = getCompositeKeyHashCode();
                long $this$rol$iv$iv$iv$iv2 = rGroupIndex$iv;
                this.compositeKeyHashCode = Long.rotateLeft(Long.rotateLeft($this$compoundWith$iv$iv$iv2, 3) ^ ((long) groupKey$iv$iv), 3) ^ $this$rol$iv$iv$iv$iv2;
            }
        } else if (objectKey instanceof Enum) {
            int groupKey$iv$iv2 = ((Enum) objectKey).ordinal();
            long $this$compoundWith$iv$iv$iv3 = getCompositeKeyHashCode();
            long $this$rol$iv$iv$iv$iv3 = Long.rotateLeft($this$compoundWith$iv$iv$iv3, 3);
            long $this$rol$iv$iv$iv$iv4 = groupKey$iv$iv2;
            long $this$compoundWith$iv$iv$iv4 = $this$rol$iv$iv$iv$iv3 ^ $this$rol$iv$iv$iv$iv4;
            this.compositeKeyHashCode = Long.rotateLeft($this$compoundWith$iv$iv$iv4, 3) ^ ((long) 0);
        } else {
            int groupKey$iv$iv3 = objectKey.hashCode();
            long $this$compoundWith$iv$iv$iv5 = getCompositeKeyHashCode();
            this.compositeKeyHashCode = Long.rotateLeft(Long.rotateLeft($this$compoundWith$iv$iv$iv5, 3) ^ ((long) groupKey$iv$iv3), 3) ^ ((long) 0);
        }
        if (objectKey == null) {
            this.rGroupIndex++;
        }
        boolean isNode = kind != GroupKind.INSTANCE.m4502getGroup9udXigM();
        if (getInserting()) {
            this.reader.beginEmpty();
            int startIndex = this.writer.getCurrentGroup();
            if (isNode) {
                this.writer.startNode(key, Composer.INSTANCE.getEmpty());
            } else {
                SlotWriter slotWriter = this.writer;
                if (data != null) {
                    slotWriter.startData(key, objectKey == null ? Composer.INSTANCE.getEmpty() : objectKey, data);
                } else {
                    slotWriter.startGroup(key, objectKey == null ? Composer.INSTANCE.getEmpty() : objectKey);
                }
            }
            GapPending pending = this.pending;
            if (pending != null) {
                KeyInfo insertKeyInfo = new KeyInfo(key, -1, insertedGroupVirtualIndex(startIndex), -1, 0);
                pending.registerInsert(insertKeyInfo, this.nodeIndex - pending.getStartIndex());
                pending.recordUsed(insertKeyInfo);
            }
            enterGroup(isNode, null);
            return;
        }
        int arg0$iv = kind != GroupKind.INSTANCE.m4503getNode9udXigM() ? 1 : 0;
        boolean forceReplace = arg0$iv == 0 && this.reusing;
        if (this.pending == null) {
            int slotKey = this.reader.getGroupKey();
            if (!forceReplace && slotKey == key && Intrinsics.areEqual(objectKey, this.reader.getGroupObjectKey())) {
                startReaderGroup(isNode, data);
            } else {
                this.pending = new GapPending(this.reader.extractKeys(), this.nodeIndex);
            }
        }
        GapPending pending2 = this.pending;
        GapPending newPending = null;
        if (pending2 != null) {
            KeyInfo keyInfo = pending2.getNext(key, objectKey);
            if (forceReplace || keyInfo == null) {
                this.reader.beginEmpty();
                this.inserting = true;
                this.providerCache = null;
                ensureWriter();
                this.writer.beginInsert();
                int startIndex2 = this.writer.getCurrentGroup();
                if (isNode) {
                    this.writer.startNode(key, Composer.INSTANCE.getEmpty());
                } else {
                    SlotWriter slotWriter2 = this.writer;
                    if (data != null) {
                        slotWriter2.startData(key, objectKey == null ? Composer.INSTANCE.getEmpty() : objectKey, data);
                    } else {
                        slotWriter2.startGroup(key, objectKey == null ? Composer.INSTANCE.getEmpty() : objectKey);
                    }
                }
                this.insertAnchor = this.writer.anchor(startIndex2);
                KeyInfo insertKeyInfo2 = new KeyInfo(key, -1, insertedGroupVirtualIndex(startIndex2), -1, 0);
                pending2.registerInsert(insertKeyInfo2, this.nodeIndex - pending2.getStartIndex());
                pending2.recordUsed(insertKeyInfo2);
                newPending = new GapPending(new ArrayList(), isNode ? 0 : this.nodeIndex);
            } else {
                pending2.recordUsed(keyInfo);
                int location = keyInfo.getLocation();
                this.nodeIndex = pending2.nodePositionOf(keyInfo) + pending2.getStartIndex();
                int relativePosition = pending2.slotPositionOf(keyInfo);
                int currentRelativePosition = relativePosition - pending2.getGroupIndex();
                pending2.registerMoveSlot(relativePosition, pending2.getGroupIndex());
                this.changeListWriter.moveReaderRelativeTo(location);
                this.reader.reposition(location);
                if (currentRelativePosition > 0) {
                    this.changeListWriter.moveCurrentGroup(currentRelativePosition);
                }
                startReaderGroup(isNode, data);
            }
        }
        enterGroup(isNode, newPending);
    }

    private final void enterGroup(boolean isNode, GapPending newPending) {
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
    }

    private final void exitGroup(int expectedNodeCount, boolean inserting) {
        GapPending previousPending = (GapPending) Stack.m4427popimpl(this.pendingStack);
        if (previousPending != null && !inserting) {
            previousPending.setGroupIndex(previousPending.getGroupIndex() + 1);
        }
        this.pending = previousPending;
        this.nodeIndex = this.parentStateStack.pop() + expectedNodeCount;
        this.rGroupIndex = this.parentStateStack.pop();
        this.groupNodeCount = this.parentStateStack.pop() + expectedNodeCount;
    }

    private final void end(boolean isNode) {
        int remainingSlots;
        int rGroupIndex = this.parentStateStack.peek2() - 1;
        if (getInserting()) {
            int parent = this.writer.getParent();
            int groupKey$iv = this.writer.groupKey(parent);
            Object dataKey$iv = this.writer.groupObjectKey(parent);
            Object data$iv = this.writer.groupAux(parent);
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
        GapPending pending = this.pending;
        if (pending != null && pending.getKeyInfos().size() > 0) {
            List<KeyInfo> keyInfos = pending.getKeyInfos();
            List<KeyInfo> used = pending.getUsed();
            Set usedKeys = ListUtilsKt.fastToSet(used);
            MutableScatterSet placedKeys = ScatterSetKt.mutableScatterSetOf();
            int currentIndex = 0;
            int currentEnd = used.size();
            int previousIndex = 0;
            int previousEnd = keyInfos.size();
            int nodeOffset = 0;
            while (previousIndex < previousEnd) {
                KeyInfo previousInfo = keyInfos.get(previousIndex);
                if (usedKeys.contains(previousInfo)) {
                    List<KeyInfo> list = keyInfos;
                    Set usedKeys2 = usedKeys;
                    int previousIndex2 = previousIndex;
                    if (placedKeys.contains(previousInfo)) {
                        previousIndex = previousIndex2 + 1;
                        keyInfos = list;
                        usedKeys = usedKeys2;
                    } else if (currentIndex < currentEnd) {
                        KeyInfo currentInfo = used.get(currentIndex);
                        if (currentInfo != previousInfo) {
                            int nodePosition = pending.nodePositionOf(currentInfo);
                            placedKeys.add(currentInfo);
                            if (nodePosition != nodeOffset) {
                                int updatedCount = pending.updatedNodeCountOf(currentInfo);
                                this.changeListWriter.moveNode(pending.getStartIndex() + nodePosition, pending.getStartIndex() + nodeOffset, updatedCount);
                                pending.registerMoveNode(nodePosition, nodeOffset, updatedCount);
                            }
                            previousIndex = previousIndex2;
                        } else {
                            previousIndex = previousIndex2 + 1;
                        }
                        currentIndex++;
                        nodeOffset += pending.updatedNodeCountOf(currentInfo);
                        keyInfos = list;
                        usedKeys = usedKeys2;
                    } else {
                        keyInfos = list;
                        usedKeys = usedKeys2;
                        previousIndex = previousIndex2;
                    }
                } else {
                    int deleteOffset = pending.nodePositionOf(previousInfo);
                    List<KeyInfo> list2 = keyInfos;
                    this.changeListWriter.removeNode(deleteOffset + pending.getStartIndex(), previousInfo.getNodes());
                    pending.updateNodeCount(previousInfo.getLocation(), 0);
                    this.changeListWriter.moveReaderRelativeTo(previousInfo.getLocation());
                    this.reader.reposition(previousInfo.getLocation());
                    recordDelete();
                    this.reader.skipGroup();
                    List<Invalidation> list3 = this.invalidations;
                    int location = previousInfo.getLocation();
                    int location2 = previousInfo.getLocation();
                    Set usedKeys3 = usedKeys;
                    SlotReader slotReader = this.reader;
                    int previousIndex3 = previousIndex;
                    int previousIndex4 = previousInfo.getLocation();
                    GapComposerKt.removeRange(list3, location, location2 + slotReader.groupSize(previousIndex4));
                    previousIndex = previousIndex3 + 1;
                    keyInfos = list2;
                    usedKeys = usedKeys3;
                }
            }
            this.changeListWriter.endNodeMovement();
            if (keyInfos.size() > 0) {
                this.changeListWriter.moveReaderRelativeTo(this.reader.getGroupEnd());
                this.reader.skipToGroupEnd();
            }
        }
        boolean inserting = getInserting();
        if (!inserting && (remainingSlots = this.reader.getRemainingSlots()) > 0) {
            this.changeListWriter.trimValues(remainingSlots);
        }
        int remainingSlots2 = this.nodeIndex;
        while (!this.reader.isGroupEnd()) {
            int startSlot = this.reader.getCurrent();
            recordDelete();
            int nodesToRemove = this.reader.skipGroup();
            this.changeListWriter.removeNode(remainingSlots2, nodesToRemove);
            GapComposerKt.removeRange(this.invalidations, startSlot, this.reader.getCurrent());
        }
        if (inserting) {
            if (isNode) {
                this.insertFixups.endNodeInsert();
                expectedNodeCount = 1;
            }
            this.reader.endEmpty();
            int parentGroup = this.writer.getParent();
            this.writer.endGroup();
            if (!this.reader.getInEmpty()) {
                int virtualIndex = insertedGroupVirtualIndex(parentGroup);
                this.writer.endInsert();
                this.writer.close(true);
                recordInsert(this.insertAnchor);
                this.inserting = false;
                if (!this.slotTable.isEmpty()) {
                    updateNodeCount(virtualIndex, 0);
                    updateNodeCountOverrides(virtualIndex, expectedNodeCount);
                }
            }
        } else {
            if (isNode) {
                this.changeListWriter.moveUp();
            }
            this.changeListWriter.endCurrentGroup();
            int parentGroup2 = this.reader.getParent();
            int parentNodeCount = updatedNodeCount(parentGroup2);
            if (expectedNodeCount != parentNodeCount) {
                updateNodeCountOverrides(parentGroup2, expectedNodeCount);
            }
            if (isNode) {
                expectedNodeCount = 1;
            }
            this.reader.endGroup();
            this.changeListWriter.endNodeMovement();
        }
        exitGroup(expectedNodeCount, inserting);
    }

    private final void recomposeToGroupEnd() throws Throwable {
        boolean wasComposing = getIsComposing();
        this.isComposing = true;
        int parent = this.reader.getParent();
        int end = this.reader.groupSize(parent) + parent;
        int recomposeIndex = this.nodeIndex;
        long recomposeCompositeKey = getCompositeKeyHashCode();
        int oldGroupNodeCount = this.groupNodeCount;
        int oldRGroupIndex = this.rGroupIndex;
        Invalidation firstInRange = GapComposerKt.firstInRange(this.invalidations, this.reader.getCurrent(), end);
        int oldGroup = parent;
        int oldGroup2 = 0;
        while (firstInRange != null) {
            int location = firstInRange.getLocation();
            RecomposeScopeImpl scope = firstInRange.getScope();
            GapComposerKt.removeLocation(this.invalidations, location);
            if (firstInRange.isInvalid()) {
                this.reader.reposition(location);
                int newGroup = this.reader.getCurrent();
                recordUpsAndDowns(oldGroup, newGroup, parent);
                this.nodeIndex = nodeIndexOf(location, newGroup, parent, recomposeIndex);
                this.rGroupIndex = rGroupIndexOf(newGroup);
                int newParent = this.reader.parent(newGroup);
                this.compositeKeyHashCode = compositeKeyOf(newParent, parent, recomposeCompositeKey);
                this.providerCache = null;
                scope.compose(this);
                this.providerCache = null;
                this.reader.restoreParent(parent);
                oldGroup2 = 1;
                oldGroup = newGroup;
            } else {
                Stack.m4428pushimpl(this.invalidateStack, scope);
                CompositionObserver observer = this.observerHolder.current();
                if (observer != null) {
                    try {
                        observer.onScopeEnter(scope);
                        scope.rereadTrackedInstances();
                    } finally {
                        observer.onScopeExit(scope);
                    }
                } else {
                    scope.rereadTrackedInstances();
                }
                Stack.m4427popimpl(this.invalidateStack);
            }
            firstInRange = GapComposerKt.firstInRange(this.invalidations, this.reader.getCurrent(), end);
        }
        if (oldGroup2 != 0) {
            recordUpsAndDowns(oldGroup, parent, parent);
            this.reader.skipToGroupEnd();
            int parentGroupNodes = updatedNodeCount(parent);
            this.nodeIndex = recomposeIndex + parentGroupNodes;
            this.groupNodeCount = oldGroupNodeCount + parentGroupNodes;
            this.rGroupIndex = oldRGroupIndex;
        } else {
            skipReaderToGroupEnd();
        }
        this.compositeKeyHashCode = recomposeCompositeKey;
        this.isComposing = wasComposing;
    }

    private final int insertedGroupVirtualIndex(int index) {
        return (-2) - index;
    }

    private final void updateNodeCountOverrides(int group, int newCount) {
        int currentCount = updatedNodeCount(group);
        if (currentCount != newCount) {
            int delta = newCount - currentCount;
            int current = group;
            int minPending = Stack.m4421getSizeimpl(this.pendingStack) - 1;
            while (current != -1) {
                int newCurrentNodes = updatedNodeCount(current) + delta;
                updateNodeCount(current, newCurrentNodes);
                int pendingIndex = minPending;
                while (true) {
                    if (-1 < pendingIndex) {
                        GapPending pending = (GapPending) Stack.m4426peekimpl(this.pendingStack, pendingIndex);
                        if (pending == null || !pending.updateNodeCount(current, newCurrentNodes)) {
                            pendingIndex--;
                        } else {
                            minPending = pendingIndex - 1;
                            break;
                        }
                    } else {
                        break;
                    }
                }
                SlotReader slotReader = this.reader;
                if (current < 0) {
                    current = slotReader.getParent();
                } else if (!slotReader.isNode(current)) {
                    current = this.reader.parent(current);
                } else {
                    return;
                }
            }
        }
    }

    private final int nodeIndexOf(int groupLocation, int group, int recomposeGroup, int recomposeIndex) {
        int anchorGroup = this.reader.parent(group);
        while (anchorGroup != recomposeGroup && !this.reader.isNode(anchorGroup)) {
            anchorGroup = this.reader.parent(anchorGroup);
        }
        int index = this.reader.isNode(anchorGroup) ? 0 : recomposeIndex;
        if (anchorGroup == group) {
            return index;
        }
        int current = anchorGroup;
        int nodeIndexLimit = (updatedNodeCount(anchorGroup) - this.reader.nodeCount(group)) + index;
        loop1: while (index < nodeIndexLimit && current != groupLocation) {
            current++;
            while (current < groupLocation) {
                int end = this.reader.groupSize(current) + current;
                if (groupLocation >= end) {
                    index += this.reader.isNode(current) ? 1 : updatedNodeCount(current);
                    current = end;
                }
            }
            break loop1;
        }
        return index;
    }

    private final int rGroupIndexOf(int group) {
        int result = 0;
        int parent = this.reader.parent(group);
        int child = parent + 1;
        while (child < group) {
            if (!this.reader.hasObjectKey(child)) {
                result++;
            }
            child += this.reader.groupSize(child);
        }
        return result;
    }

    private final int updatedNodeCount(int group) {
        int override;
        if (group < 0) {
            IntIntMap it = this.nodeCountVirtualOverrides;
            if (it == null) {
                return 0;
            }
            IntIntMap this_$iv = it;
            if (this_$iv.containsKey(group)) {
                return it.get(group);
            }
            return 0;
        }
        int[] nodeCounts = this.nodeCountOverrides;
        return (nodeCounts == null || (override = nodeCounts[group]) < 0) ? this.reader.nodeCount(group) : override;
    }

    private final void updateNodeCount(int group, int count) {
        if (updatedNodeCount(group) != count) {
            if (group < 0) {
                MutableIntIntMap virtualCounts = this.nodeCountVirtualOverrides;
                if (virtualCounts == null) {
                    GapComposer $this$updateNodeCount_u24lambda_u240 = this;
                    MutableIntIntMap newCounts = new MutableIntIntMap(0, 1, null);
                    $this$updateNodeCount_u24lambda_u240.nodeCountVirtualOverrides = newCounts;
                    virtualCounts = newCounts;
                }
                virtualCounts.set(group, count);
                return;
            }
            int[] nodeCounts = this.nodeCountOverrides;
            if (nodeCounts == null) {
                GapComposer $this$updateNodeCount_u24lambda_u241 = this;
                int[] newCounts2 = new int[$this$updateNodeCount_u24lambda_u241.reader.getGroupsSize()];
                ArraysKt.fill$default(newCounts2, -1, 0, 0, 6, (Object) null);
                $this$updateNodeCount_u24lambda_u241.nodeCountOverrides = newCounts2;
                nodeCounts = newCounts2;
            }
            nodeCounts[group] = count;
        }
    }

    private final void clearUpdatedNodeCounts() {
        this.nodeCountOverrides = null;
        this.nodeCountVirtualOverrides = null;
    }

    private final void recordUpsAndDowns(int oldGroup, int newGroup, int commonRoot) {
        SlotReader reader = this.reader;
        int nearestCommonRoot = GapComposerKt.nearestCommonRootOf(reader, oldGroup, newGroup, commonRoot);
        for (int current = oldGroup; current > 0 && current != nearestCommonRoot; current = reader.parent(current)) {
            if (reader.isNode(current)) {
                this.changeListWriter.moveUp();
            }
        }
        doRecordDownsFor(newGroup, nearestCommonRoot);
    }

    private final void doRecordDownsFor(int group, int nearestCommonRoot) {
        if (group > 0 && group != nearestCommonRoot) {
            doRecordDownsFor(this.reader.parent(group), nearestCommonRoot);
            if (this.reader.isNode(group)) {
                this.changeListWriter.moveDown(nodeAt(this.reader, group));
            }
        }
    }

    private final long compositeKeyOf(int group, int recomposeGroup, long recomposeKey) {
        int keyRot = 3;
        int rgiRot = 0;
        long result = 0;
        int parent = group;
        while (parent >= 0) {
            if (parent != recomposeGroup) {
                int groupKey = groupCompositeKeyPart(this.reader, parent);
                if (groupKey == 126665345) {
                    int shift$iv = rgiRot;
                    long $this$bottomUpCompoundWith$iv = result;
                    long $this$rol$iv$iv = groupKey;
                    long result2 = $this$bottomUpCompoundWith$iv ^ Long.rotateLeft($this$rol$iv$iv, shift$iv);
                    return result2;
                }
                int effectiveRGroupIndex = this.reader.hasObjectKey(parent) ? 0 : rGroupIndexOf(parent);
                int shift$iv2 = keyRot;
                long $this$bottomUpCompoundWith$iv2 = result;
                long $this$rol$iv$iv2 = groupKey;
                int keyRot2 = keyRot;
                long $this$bottomUpCompoundWith$iv3 = $this$bottomUpCompoundWith$iv2 ^ Long.rotateLeft($this$rol$iv$iv2, shift$iv2);
                int shift$iv3 = rgiRot;
                int segment$iv = effectiveRGroupIndex;
                long $this$rol$iv$iv3 = segment$iv;
                result = $this$bottomUpCompoundWith$iv3 ^ Long.rotateLeft($this$rol$iv$iv3, shift$iv3);
                keyRot = (keyRot2 + 6) % 64;
                rgiRot = (rgiRot + 6) % 64;
                parent = this.reader.parent(parent);
            } else {
                int shift$iv4 = rgiRot;
                long $this$bottomUpCompoundWith$iv4 = result;
                long result3 = $this$bottomUpCompoundWith$iv4 ^ Long.rotateLeft(recomposeKey, shift$iv4);
                return result3;
            }
        }
        return result;
    }

    private final int groupCompositeKeyPart(SlotReader $this$groupCompositeKeyPart, int group) {
        Object aux;
        if ($this$groupCompositeKeyPart.hasObjectKey(group)) {
            Object it = $this$groupCompositeKeyPart.groupObjectKey(group);
            if (it != null) {
                return it instanceof Enum ? ((Enum) it).ordinal() : it instanceof MovableContent ? MovableContentKt.movableContentKey : it.hashCode();
            }
            return 0;
        }
        int it2 = $this$groupCompositeKeyPart.groupKey(group);
        if (it2 != 207 || (aux = $this$groupCompositeKeyPart.groupAux(group)) == null) {
            return it2;
        }
        return Intrinsics.areEqual(aux, Composer.INSTANCE.getEmpty()) ? it2 : aux.hashCode();
    }

    @Override // androidx.compose.runtime.InternalComposer
    public boolean tryImminentInvalidation$runtime(RecomposeScopeImpl scope, Object instance) {
        Anchor anchor = scope.getAnchor();
        if (anchor == null) {
            return false;
        }
        SlotTable slotTable = this.reader.getTable();
        int location = GapAnchorKt.asGapAnchor(anchor).toIndexFor(slotTable);
        if (!getIsComposing() || location < this.reader.getCurrent()) {
            return false;
        }
        GapComposerKt.insertIfMissing(this.invalidations, location, scope, instance);
        return true;
    }

    @Override // androidx.compose.runtime.InternalComposer
    public int parentKey$runtime() {
        if (getInserting()) {
            return this.writer.groupKey(this.writer.getParent());
        }
        return this.reader.groupKey(this.reader.getParent());
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public void skipCurrentGroup() throws Throwable {
        SlotReader reader;
        int key;
        Object dataKey;
        Object aux;
        int rGroupIndex;
        if (this.invalidations.isEmpty()) {
            skipGroup();
            return;
        }
        SlotReader reader2 = this.reader;
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

    private final void skipReaderToGroupEnd() {
        this.groupNodeCount = this.reader.getParentNodes();
        this.reader.skipToGroupEnd();
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
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
            if (this.invalidations.isEmpty()) {
                skipReaderToGroupEnd();
            } else {
                recomposeToGroupEnd();
            }
        }
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public void deactivateToEndGroup(boolean changed) {
        boolean value$iv = this.groupNodeCount == 0;
        if (!value$iv) {
            ComposerKt.composeImmediateRuntimeError("No nodes can be emitted before calling deactivateToEndGroup");
        }
        boolean value$iv2 = getInserting();
        if (!value$iv2) {
            if (!changed) {
                skipReaderToGroupEnd();
                return;
            }
            int start = this.reader.getCurrent();
            int end = this.reader.getEnd();
            this.changeListWriter.deactivateCurrentGroup();
            GapComposerKt.removeRange(this.invalidations, start, end);
            this.reader.skipToGroupEnd();
        }
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public Composer startRestartGroup(int key) {
        startReplaceGroup(key);
        addRecomposeScope();
        return this;
    }

    /* JADX WARN: Removed duplicated region for block: B:49:0x0076  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void addRecomposeScope() {
        /*
            r8 = this;
            boolean r0 = r8.getInserting()
            java.lang.String r1 = "null cannot be cast to non-null type androidx.compose.runtime.CompositionImpl"
            if (r0 == 0) goto L24
            androidx.compose.runtime.RecomposeScopeImpl r0 = new androidx.compose.runtime.RecomposeScopeImpl
            androidx.compose.runtime.CompositionImpl r2 = r8.getComposition()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2, r1)
            androidx.compose.runtime.RecomposeScopeOwner r2 = (androidx.compose.runtime.RecomposeScopeOwner) r2
            r0.<init>(r2)
            java.util.ArrayList<androidx.compose.runtime.RecomposeScopeImpl> r1 = r8.invalidateStack
            androidx.compose.runtime.Stack.m4428pushimpl(r1, r0)
            r8.updateValue(r0)
            r8.enterRecomposeScope(r0)
            goto Laa
        L24:
            java.util.List<androidx.compose.runtime.Invalidation> r0 = r8.invalidations
            androidx.compose.runtime.composer.gapbuffer.SlotReader r2 = r8.reader
            int r2 = r2.getParent()
            androidx.compose.runtime.Invalidation r0 = androidx.compose.runtime.GapComposerKt.access$removeLocation(r0, r2)
            androidx.compose.runtime.composer.gapbuffer.SlotReader r2 = r8.reader
            java.lang.Object r2 = r2.next()
            androidx.compose.runtime.Composer$Companion r3 = androidx.compose.runtime.Composer.INSTANCE
            java.lang.Object r3 = r3.getEmpty()
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual(r2, r3)
            if (r3 == 0) goto L55
            androidx.compose.runtime.RecomposeScopeImpl r3 = new androidx.compose.runtime.RecomposeScopeImpl
            androidx.compose.runtime.CompositionImpl r4 = r8.getComposition()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r4, r1)
            androidx.compose.runtime.RecomposeScopeOwner r4 = (androidx.compose.runtime.RecomposeScopeOwner) r4
            r3.<init>(r4)
            r8.updateValue(r3)
            goto L5e
        L55:
            java.lang.String r1 = "null cannot be cast to non-null type androidx.compose.runtime.RecomposeScopeImpl"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2, r1)
            r3 = r2
            androidx.compose.runtime.RecomposeScopeImpl r3 = (androidx.compose.runtime.RecomposeScopeImpl) r3
        L5e:
            r1 = 0
            r4 = 1
            if (r0 != 0) goto L76
            boolean r5 = r3.getForcedRecompose()
            r6 = r5
            r7 = 0
            if (r6 == 0) goto L70
            r3.setForcedRecompose(r1)
        L70:
            if (r5 == 0) goto L74
            goto L76
        L74:
            r5 = r1
            goto L77
        L76:
            r5 = r4
        L77:
            r3.setRequiresRecompose(r5)
            java.util.ArrayList<androidx.compose.runtime.RecomposeScopeImpl> r5 = r8.invalidateStack
            androidx.compose.runtime.Stack.m4428pushimpl(r5, r3)
            r8.enterRecomposeScope(r3)
            boolean r5 = r3.getPaused()
            if (r5 == 0) goto Laa
            r3.setPaused(r1)
            r3.setResuming(r4)
            androidx.compose.runtime.composer.gapbuffer.changelist.ComposerChangeListWriter r1 = r8.changeListWriter
            r1.startResumingScope(r3)
            boolean r1 = r8.reusing
            if (r1 != 0) goto Laa
            boolean r1 = r3.getReusing()
            if (r1 == 0) goto Laa
            r8.reusing = r4
            androidx.compose.runtime.composer.gapbuffer.SlotReader r1 = r8.reader
            int r1 = r1.getParent()
            r8.reusingGroup = r1
            r3.setResetReusing(r4)
        Laa:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.GapComposer.addRecomposeScope():void");
    }

    private final void enterRecomposeScope(RecomposeScopeImpl scope) {
        scope.start(this.compositionToken);
        CompositionObserver compositionObserverCurrent = this.observerHolder.current();
        if (compositionObserverCurrent != null) {
            compositionObserverCurrent.onScopeEnter(scope);
        }
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public ScopeUpdateScope endRestartGroup() {
        GapAnchor gapAnchorAnchor;
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
                if (scope.getResetReusing()) {
                    scope.setResetReusing(false);
                    if (this.reusingGroup == this.reader.getParent()) {
                        this.reusing = false;
                        this.reusingGroup = -1;
                    }
                }
            }
        }
        if (scope != null && !scope.getSkipped$runtime() && (scope.getUsed() || this.forceRecomposeScopes)) {
            if (scope.getAnchor() == null) {
                if (getInserting()) {
                    gapAnchorAnchor = this.writer.anchor(this.writer.getParent());
                } else {
                    gapAnchorAnchor = this.reader.anchor(this.reader.getParent());
                }
                scope.setAnchor(gapAnchorAnchor);
            }
            scope.setDefaultsInvalid(false);
            result = scope;
        }
        end(false);
        return result;
    }

    private final Function1<Composition, Unit> exitRecomposeScope(RecomposeScopeImpl scope) {
        CompositionObserver compositionObserverCurrent = this.observerHolder.current();
        if (compositionObserverCurrent != null) {
            compositionObserverCurrent.onScopeExit(scope);
        }
        return scope.end(this.compositionToken);
    }

    @Override // androidx.compose.runtime.Composer
    public void insertMovableContent(MovableContent<?> value, Object parameter) {
        Intrinsics.checkNotNull(value, "null cannot be cast to non-null type androidx.compose.runtime.MovableContent<kotlin.Any?>");
        invokeMovableContentLambda(value, currentCompositionLocalScope(), parameter, false);
    }

    private final void invokeMovableContentLambda(final MovableContent<Object> content, PersistentCompositionLocalMap locals, final Object parameter, boolean force) {
        startMovableGroup(MovableContentKt.movableContentKey, content);
        updateSlot(parameter);
        long savedCompositeKeyHash = getCompositeKeyHashCode();
        try {
            this.compositeKeyHashCode = MovableContentKt.movableContentKey;
            boolean z = false;
            if (getInserting()) {
                SlotWriter.markGroup$default(this.writer, 0, 1, null);
            }
            if (!getInserting() && !Intrinsics.areEqual(this.reader.getGroupAux(), locals)) {
                z = true;
            }
            boolean providersChanged = z;
            if (providersChanged) {
                recordProviderUpdate(locals);
            }
            m4393startAzEfcrM(ComposerKt.compositionLocalMapKey, ComposerKt.getCompositionLocalMap(), GroupKind.INSTANCE.m4502getGroup9udXigM(), locals);
            this.providerCache = null;
            if (getInserting() && !force) {
                this.writerHasAProvider = true;
                GapAnchor anchor = this.writer.anchor(this.writer.parent(this.writer.getParent()));
                MovableContentStateReference reference = new MovableContentStateReference(content, parameter, getComposition(), this.insertTable, anchor, CollectionsKt.emptyList(), currentCompositionLocalScope(), null);
                this.parentContext.insertMovableContent$runtime(reference);
            } else {
                boolean savedProvidersInvalid = this.providersInvalid;
                this.providersInvalid = providersChanged;
                Expect_jvmKt.invokeComposable(this, ComposableLambdaKt.composableLambdaInstance(-59194059, true, new Function2() { // from class: androidx.compose.runtime.GapComposer$$ExternalSyntheticLambda3
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return GapComposer.invokeMovableContentLambda$lambda$0(content, parameter, (Composer) obj, ((Integer) obj2).intValue());
                    }
                }));
                this.providersInvalid = savedProvidersInvalid;
            }
        } catch (Throwable e) {
            try {
                throw ComposeStackTraceKt.attachComposeStackTrace(e, new Function0() { // from class: androidx.compose.runtime.GapComposer$$ExternalSyntheticLambda4
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return this.f$0.currentStackTrace();
                    }
                });
            } finally {
                endGroup();
                this.providerCache = null;
                this.compositeKeyHashCode = savedCompositeKeyHash;
                endMovableGroup();
            }
        }
    }

    static final Unit invokeMovableContentLambda$lambda$0(MovableContent $content, Object $parameter, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C2265@91036L18:GapComposer.kt#9igjgp");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-59194059, $changed, -1, "androidx.compose.runtime.GapComposer.invokeMovableContentLambda.<anonymous> (GapComposer.kt:2265)");
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

    @Override // androidx.compose.runtime.Composer
    public void insertMovableContentReferences(List<Pair<MovableContentStateReference, MovableContentStateReference>> references) {
        Object token$iv = Trace.INSTANCE.beginSection("Compose:insertMovableContent");
        try {
            try {
                insertMovableContentGuarded(references);
                cleanUpCompose();
                Unit unit = Unit.INSTANCE;
            } catch (Throwable th) {
                abortRoot();
                throw th;
            }
        } finally {
            Trace.INSTANCE.endSection(token$iv);
        }
    }

    private final void insertMovableContentGuarded(List<Pair<MovableContentStateReference, MovableContentStateReference>> references) throws Throwable {
        int $i$f$withChangeList;
        int location;
        List<Pair<MovableContentStateReference, MovableContentStateReference>> list;
        int $i$f$fastForEach;
        ChangeList newChangeList$iv;
        SlotReader savedReader$iv;
        int[] savedCountOverrides$iv;
        MutableIntObjectMap<PersistentCompositionLocalMap> mutableIntObjectMap;
        GapComposer this_$iv;
        ChangeList previousChangeList$iv;
        boolean previousImplicitRootStart$iv;
        GapAnchor gapAnchorAnchor;
        SlotStorage slotStorage;
        final ChangeList offsetChanges;
        ComposerChangeListWriter this_$iv2 = this.changeListWriter;
        ChangeList newChangeList$iv2 = ChangeListKt.asGapBufferChangeList(this.lateChanges);
        int $i$f$withChangeList2 = 0;
        ChangeList previousChangeList$iv2 = this_$iv2.getChangeList();
        try {
            this_$iv2.setChangeList(newChangeList$iv2);
            int i = 0;
            this.changeListWriter.resetSlots();
            List<Pair<MovableContentStateReference, MovableContentStateReference>> list2 = references;
            int $i$f$fastForEach2 = 0;
            int size = list2.size();
            int index$iv = 0;
            while (index$iv < size) {
                Object item$iv = list2.get(index$iv);
                Pair<MovableContentStateReference, MovableContentStateReference> pair = (Pair) item$iv;
                final MovableContentStateReference to = pair.component1();
                MovableContentStateReference from = pair.component2();
                GapAnchor anchor = GapAnchorKt.asGapAnchor(to.getAnchor());
                SlotTable toSlotTable = SlotTableKt.asGapBufferSlotTable(to.getSlotStorage());
                int location2 = toSlotTable.anchorIndex(anchor);
                int i2 = size;
                int index$iv2 = index$iv;
                IntRef effectiveNodeIndex = new IntRef(0, 1, null);
                this.changeListWriter.determineMovableContentNodeIndex(effectiveNodeIndex, anchor);
                if (from != null) {
                    $i$f$withChangeList = $i$f$withChangeList2;
                    location = i;
                    list = list2;
                    $i$f$fastForEach = $i$f$fastForEach2;
                    newChangeList$iv = newChangeList$iv2;
                    MovableContentState resolvedState = this.parentContext.movableContentStateResolve$runtime(from);
                    SlotTable resolvedSlotTable = (resolvedState == null || (slotStorage = resolvedState.getSlotStorage()) == null) ? null : SlotTableKt.asGapBufferSlotTable(slotStorage);
                    SlotTable fromTable = resolvedSlotTable == null ? SlotTableKt.asGapBufferSlotTable(from.getSlotStorage()) : resolvedSlotTable;
                    GapAnchor fromAnchor = GapAnchorKt.asGapAnchor((resolvedSlotTable == null || (gapAnchorAnchor = resolvedSlotTable.anchor(0)) == null) ? from.getAnchor() : gapAnchorAnchor);
                    List<? extends Object> listCollectNodesFrom = GapComposerKt.collectNodesFrom(fromTable, fromAnchor);
                    if (!listCollectNodesFrom.isEmpty()) {
                        this.changeListWriter.copyNodesToNewAnchorLocation(listCollectNodesFrom, effectiveNodeIndex);
                        if (Intrinsics.areEqual(toSlotTable, this.slotTable)) {
                            int group = this.slotTable.anchorIndex(anchor);
                            updateNodeCount(group, updatedNodeCount(group) + listCollectNodesFrom.size());
                        }
                    }
                    this.changeListWriter.copySlotTableToAnchorLocation(resolvedState, this.parentContext, from, to);
                    SlotReader reader$iv = fromTable.openReader();
                    try {
                        SlotReader savedReader$iv2 = this.reader;
                        int[] savedCountOverrides$iv2 = this.nodeCountOverrides;
                        MutableIntObjectMap<PersistentCompositionLocalMap> mutableIntObjectMap2 = this.providerUpdates;
                        this.nodeCountOverrides = null;
                        this.providerUpdates = null;
                        try {
                            this.reader = reader$iv;
                            int newLocation = fromTable.anchorIndex(GapAnchorKt.asGapAnchor(fromAnchor));
                            try {
                                reader$iv.reposition(newLocation);
                                this.changeListWriter.moveReaderToAbsolute(newLocation);
                                ChangeList offsetChanges2 = new ChangeList();
                                ComposerChangeListWriter this_$iv3 = this.changeListWriter;
                                ChangeList previousChangeList$iv3 = this_$iv3.getChangeList();
                                try {
                                    this_$iv3.setChangeList(offsetChanges2);
                                    ComposerChangeListWriter this_$iv4 = this.changeListWriter;
                                    boolean previousImplicitRootStart$iv2 = this_$iv4.getImplicitRootStart();
                                    try {
                                        this_$iv4.setImplicitRootStart(false);
                                        try {
                                            try {
                                                try {
                                                    try {
                                                        this_$iv = this;
                                                        try {
                                                            recomposeMovableContent(from.getComposition(), to.getComposition(), Integer.valueOf(reader$iv.getCurrent()), from.getInvalidations$runtime(), new Function0() { // from class: androidx.compose.runtime.GapComposer$$ExternalSyntheticLambda2
                                                                @Override // kotlin.jvm.functions.Function0
                                                                public final Object invoke() {
                                                                    return GapComposer.insertMovableContentGuarded$lambda$0$0$1$0$0$0$0(this.f$0, to);
                                                                }
                                                            });
                                                            try {
                                                                this_$iv4.setImplicitRootStart(previousImplicitRootStart$iv2);
                                                                try {
                                                                    this_$iv3.setChangeList(previousChangeList$iv3);
                                                                    this.changeListWriter.includeOperationsIn(offsetChanges2, effectiveNodeIndex);
                                                                    Unit unit = Unit.INSTANCE;
                                                                    try {
                                                                        this_$iv.reader = savedReader$iv2;
                                                                        this_$iv.nodeCountOverrides = savedCountOverrides$iv2;
                                                                        this_$iv.providerUpdates = mutableIntObjectMap2;
                                                                        Unit unit2 = Unit.INSTANCE;
                                                                        reader$iv.close();
                                                                    } catch (Throwable th) {
                                                                        th = th;
                                                                        reader$iv.close();
                                                                        throw th;
                                                                    }
                                                                } catch (Throwable th2) {
                                                                    th = th2;
                                                                    savedReader$iv = savedReader$iv2;
                                                                    savedCountOverrides$iv = savedCountOverrides$iv2;
                                                                    mutableIntObjectMap = mutableIntObjectMap2;
                                                                    this_$iv.reader = savedReader$iv;
                                                                    this_$iv.nodeCountOverrides = savedCountOverrides$iv;
                                                                    this_$iv.providerUpdates = mutableIntObjectMap;
                                                                    throw th;
                                                                }
                                                            } catch (Throwable th3) {
                                                                th = th3;
                                                                savedReader$iv = savedReader$iv2;
                                                                savedCountOverrides$iv = savedCountOverrides$iv2;
                                                                previousChangeList$iv = previousChangeList$iv3;
                                                                mutableIntObjectMap = mutableIntObjectMap2;
                                                                try {
                                                                    this_$iv3.setChangeList(previousChangeList$iv);
                                                                    throw th;
                                                                } catch (Throwable th4) {
                                                                    th = th4;
                                                                    this_$iv.reader = savedReader$iv;
                                                                    this_$iv.nodeCountOverrides = savedCountOverrides$iv;
                                                                    this_$iv.providerUpdates = mutableIntObjectMap;
                                                                    throw th;
                                                                }
                                                            }
                                                        } catch (Throwable th5) {
                                                            th = th5;
                                                            savedReader$iv = savedReader$iv2;
                                                            savedCountOverrides$iv = savedCountOverrides$iv2;
                                                            mutableIntObjectMap = mutableIntObjectMap2;
                                                            previousChangeList$iv = previousChangeList$iv3;
                                                            previousImplicitRootStart$iv = previousImplicitRootStart$iv2;
                                                            try {
                                                                this_$iv4.setImplicitRootStart(previousImplicitRootStart$iv);
                                                                throw th;
                                                            } catch (Throwable th6) {
                                                                th = th6;
                                                                this_$iv3.setChangeList(previousChangeList$iv);
                                                                throw th;
                                                            }
                                                        }
                                                    } catch (Throwable th7) {
                                                        th = th7;
                                                        savedReader$iv = savedReader$iv2;
                                                        savedCountOverrides$iv = savedCountOverrides$iv2;
                                                        previousChangeList$iv = previousChangeList$iv3;
                                                        this_$iv = this;
                                                        previousImplicitRootStart$iv = previousImplicitRootStart$iv2;
                                                        mutableIntObjectMap = mutableIntObjectMap2;
                                                    }
                                                } catch (Throwable th8) {
                                                    th = th8;
                                                    savedReader$iv = savedReader$iv2;
                                                    savedCountOverrides$iv = savedCountOverrides$iv2;
                                                    previousChangeList$iv = previousChangeList$iv3;
                                                    this_$iv = this;
                                                    previousImplicitRootStart$iv = previousImplicitRootStart$iv2;
                                                    mutableIntObjectMap = mutableIntObjectMap2;
                                                }
                                            } catch (Throwable th9) {
                                                th = th9;
                                                savedReader$iv = savedReader$iv2;
                                                savedCountOverrides$iv = savedCountOverrides$iv2;
                                                previousChangeList$iv = previousChangeList$iv3;
                                                this_$iv = this;
                                                previousImplicitRootStart$iv = previousImplicitRootStart$iv2;
                                                mutableIntObjectMap = mutableIntObjectMap2;
                                            }
                                        } catch (Throwable th10) {
                                            th = th10;
                                            savedReader$iv = savedReader$iv2;
                                            savedCountOverrides$iv = savedCountOverrides$iv2;
                                            mutableIntObjectMap = mutableIntObjectMap2;
                                            previousImplicitRootStart$iv = previousImplicitRootStart$iv2;
                                            this_$iv = this;
                                            previousChangeList$iv = previousChangeList$iv3;
                                        }
                                    } catch (Throwable th11) {
                                        th = th11;
                                        savedReader$iv = savedReader$iv2;
                                        savedCountOverrides$iv = savedCountOverrides$iv2;
                                        mutableIntObjectMap = mutableIntObjectMap2;
                                        previousImplicitRootStart$iv = previousImplicitRootStart$iv2;
                                        this_$iv = this;
                                        previousChangeList$iv = previousChangeList$iv3;
                                    }
                                } catch (Throwable th12) {
                                    th = th12;
                                    savedReader$iv = savedReader$iv2;
                                    savedCountOverrides$iv = savedCountOverrides$iv2;
                                    mutableIntObjectMap = mutableIntObjectMap2;
                                    this_$iv = this;
                                    previousChangeList$iv = previousChangeList$iv3;
                                }
                            } catch (Throwable th13) {
                                th = th13;
                                savedReader$iv = savedReader$iv2;
                                savedCountOverrides$iv = savedCountOverrides$iv2;
                                mutableIntObjectMap = mutableIntObjectMap2;
                                this_$iv = this;
                            }
                        } catch (Throwable th14) {
                            th = th14;
                            savedReader$iv = savedReader$iv2;
                            savedCountOverrides$iv = savedCountOverrides$iv2;
                            mutableIntObjectMap = mutableIntObjectMap2;
                            this_$iv = this;
                        }
                    } catch (Throwable th15) {
                        th = th15;
                    }
                } else {
                    if (Intrinsics.areEqual(toSlotTable, this.insertTable)) {
                        try {
                            createFreshInsertTable();
                        } catch (Throwable th16) {
                            th = th16;
                            this_$iv2.setChangeList(previousChangeList$iv2);
                            throw th;
                        }
                    }
                    final SlotReader reader$iv2 = toSlotTable.openReader();
                    try {
                        reader$iv2.reposition(location2);
                        this.changeListWriter.moveReaderToAbsolute(location2);
                        offsetChanges = new ChangeList();
                        try {
                            location = i;
                            $i$f$fastForEach = $i$f$fastForEach2;
                            list = list2;
                            $i$f$withChangeList = $i$f$withChangeList2;
                            newChangeList$iv = newChangeList$iv2;
                        } catch (Throwable th17) {
                            th = th17;
                        }
                    } catch (Throwable th18) {
                        th = th18;
                    }
                    try {
                        recomposeMovableContent$default(this, null, null, null, null, new Function0() { // from class: androidx.compose.runtime.GapComposer$$ExternalSyntheticLambda1
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return GapComposer.insertMovableContentGuarded$lambda$0$0$0$0(this.f$0, offsetChanges, reader$iv2, to);
                            }
                        }, 15, null);
                        this.changeListWriter.includeOperationsIn(offsetChanges, effectiveNodeIndex);
                        Unit unit3 = Unit.INSTANCE;
                        try {
                            reader$iv2.close();
                        } catch (Throwable th19) {
                            th = th19;
                            this_$iv2.setChangeList(previousChangeList$iv2);
                            throw th;
                        }
                    } catch (Throwable th20) {
                        th = th20;
                        reader$iv2.close();
                        throw th;
                    }
                }
                this.changeListWriter.skipToEndOfCurrentGroup();
                index$iv = index$iv2 + 1;
                size = i2;
                newChangeList$iv2 = newChangeList$iv;
                $i$f$withChangeList2 = $i$f$withChangeList;
                i = location;
                list2 = list;
                $i$f$fastForEach2 = $i$f$fastForEach;
            }
            this.changeListWriter.endMovableContentPlacement();
            this.changeListWriter.moveReaderToAbsolute(0);
            this_$iv2.setChangeList(previousChangeList$iv2);
        } catch (Throwable th21) {
            th = th21;
        }
    }

    public static final Unit insertMovableContentGuarded$lambda$0$0$0$0(GapComposer this$0, ChangeList $offsetChanges, SlotReader $reader, MovableContentStateReference $to) throws Throwable {
        ComposerChangeListWriter this_$iv;
        boolean previousImplicitRootStart$iv;
        boolean previousImplicitRootStart$iv2;
        ComposerChangeListWriter this_$iv2 = this$0.changeListWriter;
        ChangeList previousChangeList$iv = this_$iv2.getChangeList();
        try {
            try {
                this_$iv2.setChangeList($offsetChanges);
                SlotReader savedReader$iv = this$0.reader;
                int[] savedCountOverrides$iv = this$0.nodeCountOverrides;
                MutableIntObjectMap<PersistentCompositionLocalMap> mutableIntObjectMap = this$0.providerUpdates;
                this$0.nodeCountOverrides = null;
                this$0.providerUpdates = null;
                try {
                    this$0.reader = $reader;
                    this_$iv = this$0.changeListWriter;
                    previousImplicitRootStart$iv = this_$iv.getImplicitRootStart();
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    try {
                        this_$iv.setImplicitRootStart(false);
                        try {
                            try {
                            } catch (Throwable th2) {
                                th = th2;
                                previousImplicitRootStart$iv2 = previousImplicitRootStart$iv;
                            }
                        } catch (Throwable th3) {
                            th = th3;
                            previousImplicitRootStart$iv2 = previousImplicitRootStart$iv;
                        }
                    } catch (Throwable th4) {
                        th = th4;
                        previousImplicitRootStart$iv2 = previousImplicitRootStart$iv;
                    }
                    try {
                        this$0.invokeMovableContentLambda($to.getContent$runtime(), $to.getLocals(), $to.getParameter(), true);
                        this_$iv.setImplicitRootStart(previousImplicitRootStart$iv);
                        Unit unit = Unit.INSTANCE;
                        this$0.reader = savedReader$iv;
                        this$0.nodeCountOverrides = savedCountOverrides$iv;
                        this$0.providerUpdates = mutableIntObjectMap;
                        this_$iv2.setChangeList(previousChangeList$iv);
                        return Unit.INSTANCE;
                    } catch (Throwable th5) {
                        th = th5;
                        previousImplicitRootStart$iv2 = previousImplicitRootStart$iv;
                        this_$iv.setImplicitRootStart(previousImplicitRootStart$iv2);
                        throw th;
                    }
                } catch (Throwable th6) {
                    th = th6;
                    this$0.reader = savedReader$iv;
                    this$0.nodeCountOverrides = savedCountOverrides$iv;
                    this$0.providerUpdates = mutableIntObjectMap;
                    throw th;
                }
            } catch (Throwable th7) {
                th = th7;
                this_$iv2.setChangeList(previousChangeList$iv);
                throw th;
            }
        } catch (Throwable th8) {
            th = th8;
            this_$iv2.setChangeList(previousChangeList$iv);
            throw th;
        }
    }

    public static final Unit insertMovableContentGuarded$lambda$0$0$1$0$0$0$0(GapComposer this$0, MovableContentStateReference $to) {
        this$0.invokeMovableContentLambda($to.getContent$runtime(), $to.getLocals(), $to.getParameter(), true);
        return Unit.INSTANCE;
    }

    private final <R> R withReader(SlotReader reader, Function0<? extends R> block) {
        SlotReader savedReader = this.reader;
        int[] savedCountOverrides = this.nodeCountOverrides;
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

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ Object recomposeMovableContent$default(GapComposer gapComposer, ControlledComposition controlledComposition, ControlledComposition controlledComposition2, Integer num, List list, Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            controlledComposition = null;
        }
        if ((i & 2) != 0) {
            controlledComposition2 = null;
        }
        if ((i & 4) != 0) {
            num = null;
        }
        if ((i & 8) != 0) {
            list = CollectionsKt.emptyList();
        }
        return gapComposer.recomposeMovableContent(controlledComposition, controlledComposition2, num, list, function0);
    }

    private final <R> R recomposeMovableContent(ControlledComposition controlledComposition, ControlledComposition controlledComposition2, Integer index, List<? extends Pair<RecomposeScopeImpl, ? extends Object>> invalidations, Function0<? extends R> block) throws Throwable {
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
                    rInvoke = (R) controlledComposition.delegateInvalidations(controlledComposition2, index != null ? index.intValue() : -1, function0);
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

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public void sourceInformation(String sourceInformation) {
        if (getInserting() && getSourceMarkersEnabled()) {
            this.writer.recordGroupSourceInformation(sourceInformation);
        }
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public void sourceInformationMarkerStart(int key, String sourceInformation) {
        if (getInserting() && getSourceMarkersEnabled()) {
            this.writer.recordGrouplessCallSourceInformationStart(key, sourceInformation);
        }
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public void sourceInformationMarkerEnd() {
        if (getInserting() && getSourceMarkersEnabled()) {
            this.writer.recordGrouplessCallSourceInformationEnd();
        }
    }

    @Override // androidx.compose.runtime.Composer
    public void disableSourceInformation() {
        setSourceMarkersEnabled$runtime(false);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x002b  */
    @Override // androidx.compose.runtime.InternalComposer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public androidx.compose.runtime.tooling.ComposeStackTrace stackTraceForValue$runtime(final java.lang.Object r6) {
        /*
            r5 = this;
            androidx.compose.runtime.composer.gapbuffer.SlotTable r0 = r5.slotTable
            androidx.compose.runtime.GapComposer$$ExternalSyntheticLambda0 r1 = new androidx.compose.runtime.GapComposer$$ExternalSyntheticLambda0
            r1.<init>()
            androidx.compose.runtime.tooling.ObjectLocation r0 = androidx.compose.runtime.tooling.ComposeStackTraceBuilderKt.findLocation(r0, r1)
            if (r0 == 0) goto L2b
        Le:
            r1 = 0
            int r2 = r0.getGroup()
            java.lang.Integer r0 = r0.getDataOffset()
            java.util.List r3 = r5.stackTraceForGroup(r2, r0)
            java.util.Collection r3 = (java.util.Collection) r3
            java.util.List r4 = r5.parentStackTrace$runtime()
            java.lang.Iterable r4 = (java.lang.Iterable) r4
            java.util.List r0 = kotlin.collections.CollectionsKt.plus(r3, r4)
            if (r0 == 0) goto L2b
            goto L2f
        L2b:
            java.util.List r0 = kotlin.collections.CollectionsKt.emptyList()
        L2f:
            androidx.compose.runtime.tooling.ComposeStackTrace r1 = new androidx.compose.runtime.tooling.ComposeStackTrace
            boolean r2 = r5.getSourceMarkersEnabled()
            r1.<init>(r0, r2)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.GapComposer.stackTraceForValue$runtime(java.lang.Object):androidx.compose.runtime.tooling.ComposeStackTrace");
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

    public final ComposeStackTrace currentStackTrace() {
        if (this.parentContext.getStackTraceEnabled$runtime()) {
            List $this$currentStackTrace_u24lambda_u240 = CollectionsKt.createListBuilder();
            $this$currentStackTrace_u24lambda_u240.addAll(ComposeStackTraceBuilderKt.buildTrace$default(this.writer, null, 0, null, 7, null));
            $this$currentStackTrace_u24lambda_u240.addAll(ComposeStackTraceBuilderKt.buildTrace(this.reader));
            $this$currentStackTrace_u24lambda_u240.addAll(parentStackTrace$runtime());
            return new ComposeStackTrace(CollectionsKt.build($this$currentStackTrace_u24lambda_u240), getSourceMarkersEnabled());
        }
        return null;
    }

    private final List<ComposeStackTraceFrame> stackTraceForGroup(int group, Integer dataOffset) {
        SlotTable this_$iv = this.slotTable;
        SlotReader reader$iv = this_$iv.openReader();
        try {
            return ComposeStackTraceBuilderKt.traceForGroup(reader$iv, group, dataOffset);
        } finally {
            reader$iv.close();
        }
    }

    @Override // androidx.compose.runtime.InternalComposer
    public List<ComposeStackTraceFrame> parentStackTrace$runtime() {
        Composition composition$runtime = this.parentContext.getComposition$runtime();
        CompositionImpl parentComposition = composition$runtime instanceof CompositionImpl ? (CompositionImpl) composition$runtime : null;
        if (parentComposition == null) {
            return CollectionsKt.emptyList();
        }
        Integer position = ComposeStackTraceBuilderKt.findSubcompositionContextGroup(SlotTableKt.asGapBufferSlotTable(parentComposition.getSlotStorage()), this.parentContext);
        if (position != null) {
            SlotTable this_$iv = SlotTableKt.asGapBufferSlotTable(parentComposition.getSlotStorage());
            SlotReader reader$iv = this_$iv.openReader();
            try {
                List<ComposeStackTraceFrame> listTraceForGroup = ComposeStackTraceBuilderKt.traceForGroup(reader$iv, position.intValue(), 0);
                reader$iv.close();
                return CollectionsKt.plus((Collection) listTraceForGroup, (Iterable) parentComposition.getComposer().parentStackTrace$runtime());
            } catch (Throwable th) {
                reader$iv.close();
                throw th;
            }
        }
        return CollectionsKt.emptyList();
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
            m4392doComposeaFTiNEg(invalidationsRequested, content);
        } finally {
            this.shouldPauseCallback = null;
        }
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

    @Override // androidx.compose.runtime.InternalComposer
    /* JADX INFO: renamed from: recompose-aFTiNEg$runtime */
    public boolean mo4395recomposeaFTiNEg$runtime(MutableScatterMap<Object, Object> invalidationsRequested, ShouldPauseCallback shouldPause) {
        boolean value$iv = this.changes.isEmpty();
        if (!value$iv) {
            ComposerKt.composeImmediateRuntimeError("Expected applyChanges() to have been called");
        }
        if (ScopeMap.m4481getSizeimpl(invalidationsRequested) > 0 || !this.invalidations.isEmpty() || this.forciblyRecompose) {
            this.shouldPauseCallback = shouldPause;
            try {
                m4392doComposeaFTiNEg(invalidationsRequested, null);
                this.shouldPauseCallback = null;
                return this.changes.isNotEmpty();
            } catch (Throwable th) {
                this.shouldPauseCallback = null;
                throw th;
            }
        }
        return false;
    }

    @Override // androidx.compose.runtime.InternalComposer
    /* JADX INFO: renamed from: updateComposerInvalidations-RY85e9Y$runtime */
    public void mo4396updateComposerInvalidationsRY85e9Y$runtime(MutableScatterMap<Object, Object> invalidationsRequested) {
        ScatterMap this_$iv;
        Object[] k$iv;
        Object[] v$iv;
        ScatterMap this_$iv$iv;
        ScatterMap this_$iv2;
        int i;
        Object[] k$iv2;
        Object[] v$iv2;
        ScatterMap this_$iv$iv2;
        GapAnchor gapAnchorAsGapAnchor;
        for (int i2 = CollectionsKt.getLastIndex(this.invalidations); -1 < i2; i2--) {
            Invalidation invalidation = this.invalidations.get(i2);
            Anchor anchor = invalidation.getScope().getAnchor();
            GapAnchor anchor2 = anchor != null ? GapAnchorKt.asGapAnchor(anchor) : null;
            if (anchor2 != null && anchor2.getValid()) {
                if (invalidation.getLocation() != anchor2.getLocation()) {
                    invalidation.setLocation(anchor2.getLocation());
                }
            } else {
                this.invalidations.remove(i2);
            }
        }
        MutableScatterMap<Object, Object> this_$iv3 = invalidationsRequested;
        int $i$f$forEach = 0;
        Object[] k$iv3 = this_$iv3.keys;
        Object[] v$iv3 = this_$iv3.values;
        ScatterMap this_$iv$iv3 = this_$iv3;
        long[] m$iv$iv = this_$iv$iv3.metadata;
        int lastIndex$iv$iv = m$iv$iv.length - 2;
        int i$iv$iv = 0;
        if (0 <= lastIndex$iv$iv) {
            while (true) {
                long slot$iv$iv = m$iv$iv[i$iv$iv];
                int $i$f$forEach2 = $i$f$forEach;
                if ((((~slot$iv$iv) << 7) & slot$iv$iv & (-9187201950435737472L)) == -9187201950435737472L) {
                    this_$iv = this_$iv3;
                    k$iv = k$iv3;
                    v$iv = v$iv3;
                    this_$iv$iv = this_$iv$iv3;
                } else {
                    int i3 = 8;
                    int bitCount$iv$iv = 8 - ((~(i$iv$iv - lastIndex$iv$iv)) >>> 31);
                    int j$iv$iv = 0;
                    while (j$iv$iv < bitCount$iv$iv) {
                        long value$iv$iv$iv = 255 & slot$iv$iv;
                        if (!(value$iv$iv$iv < 128)) {
                            this_$iv2 = this_$iv3;
                            i = i3;
                            k$iv2 = k$iv3;
                            v$iv2 = v$iv3;
                            this_$iv$iv2 = this_$iv$iv3;
                        } else {
                            int index$iv$iv = (i$iv$iv << 3) + j$iv$iv;
                            i = i3;
                            Object scope = k$iv3[index$iv$iv];
                            Object instances = v$iv3[index$iv$iv];
                            this_$iv2 = this_$iv3;
                            Intrinsics.checkNotNull(scope, "null cannot be cast to non-null type androidx.compose.runtime.RecomposeScopeImpl");
                            Anchor anchor3 = ((RecomposeScopeImpl) scope).getAnchor();
                            if (anchor3 == null || (gapAnchorAsGapAnchor = GapAnchorKt.asGapAnchor(anchor3)) == null) {
                                k$iv2 = k$iv3;
                                v$iv2 = v$iv3;
                                this_$iv$iv2 = this_$iv$iv3;
                            } else {
                                int location = gapAnchorAsGapAnchor.getLocation();
                                k$iv2 = k$iv3;
                                v$iv2 = v$iv3;
                                this_$iv$iv2 = this_$iv$iv3;
                                this.invalidations.add(new Invalidation((RecomposeScopeImpl) scope, location, !(instances == ScopeInvalidated.INSTANCE) ? instances : null));
                            }
                        }
                        slot$iv$iv >>= i;
                        j$iv$iv++;
                        i3 = i;
                        this_$iv3 = this_$iv2;
                        k$iv3 = k$iv2;
                        v$iv3 = v$iv2;
                        this_$iv$iv3 = this_$iv$iv2;
                    }
                    this_$iv = this_$iv3;
                    k$iv = k$iv3;
                    v$iv = v$iv3;
                    this_$iv$iv = this_$iv$iv3;
                    if (bitCount$iv$iv != i3) {
                        break;
                    }
                }
                if (i$iv$iv == lastIndex$iv$iv) {
                    break;
                }
                i$iv$iv++;
                $i$f$forEach = $i$f$forEach2;
                this_$iv3 = this_$iv;
                k$iv3 = k$iv;
                v$iv3 = v$iv;
                this_$iv$iv3 = this_$iv$iv;
            }
        }
        CollectionsKt.sortWith(this.invalidations, GapComposerKt.InvalidationLocationAscending);
    }

    /* JADX INFO: renamed from: doCompose-aFTiNEg */
    private final void m4392doComposeaFTiNEg(MutableScatterMap<Object, Object> invalidationsRequested, Function2<? super Composer, ? super Integer, Unit> content) {
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
                Object savedContent = nextSlot();
                if (savedContent != content && content != null) {
                    updateValue(content);
                }
                DerivedStateObserver observer$iv = this.derivedStateObserver;
                MutableVector<DerivedStateObserver> mutableVectorDerivedStateObservers = SnapshotStateKt.derivedStateObservers();
                try {
                    mutableVectorDerivedStateObservers.add(observer$iv);
                } catch (Throwable th) {
                    th = th;
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
                    this.invalidations.clear();
                    createFreshInsertTable();
                    Unit unit = Unit.INSTANCE;
                } catch (Throwable th2) {
                    th = th2;
                    mutableVectorDerivedStateObservers.removeAt(mutableVectorDerivedStateObservers.getSize() - 1);
                    throw th;
                }
            } finally {
            }
        } finally {
            Trace.INSTANCE.endSection(token$iv);
        }
    }

    public final boolean getHasInvalidations() {
        return !this.invalidations.isEmpty();
    }

    private final Object getNode(SlotReader $this$node) {
        return $this$node.node($this$node.getParent());
    }

    private final Object nodeAt(SlotReader $this$nodeAt, int index) {
        return $this$nodeAt.node(index);
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

    private final void recordInsert(GapAnchor anchor) {
        boolean zIsEmpty = this.insertFixups.isEmpty();
        ComposerChangeListWriter composerChangeListWriter = this.changeListWriter;
        if (zIsEmpty) {
            composerChangeListWriter.insertSlots(anchor, this.insertTable);
        } else {
            composerChangeListWriter.insertSlots(anchor, this.insertTable, this.insertFixups);
            this.insertFixups = new FixupList();
        }
    }

    private final void recordDelete() {
        reportFreeMovableContent(this.reader.getCurrent());
        this.changeListWriter.removeCurrentGroup();
    }

    private static final MovableContentStateReference reportFreeMovableContent$createMovableContentReferenceForGroup(GapComposer this$0, int group, List<MovableContentStateReference> list) {
        GapAnchor anchor;
        Object objGroupObjectKey = this$0.reader.groupObjectKey(group);
        Intrinsics.checkNotNull(objGroupObjectKey, "null cannot be cast to non-null type androidx.compose.runtime.MovableContent<kotlin.Any?>");
        MovableContent movableContent = (MovableContent) objGroupObjectKey;
        Object parameter = this$0.reader.groupGet(group, 0);
        GapAnchor anchor2 = this$0.reader.anchor(group);
        int end = this$0.reader.groupSize(group) + group;
        List invalidations = new ArrayList();
        List<Invalidation> list2 = this$0.invalidations;
        int index$iv = GapComposerKt.findInsertLocation(list2, group);
        while (true) {
            if (index$iv >= list2.size()) {
                anchor = anchor2;
                break;
            }
            Invalidation invalidation$iv = list2.get(index$iv);
            if (invalidation$iv.getLocation() >= end) {
                anchor = anchor2;
                break;
            }
            invalidations.add(TuplesKt.to(invalidation$iv.getScope(), invalidation$iv.getInstances()));
            index$iv++;
            anchor2 = anchor2;
        }
        MovableContentStateReference reference = new MovableContentStateReference(movableContent, parameter, this$0.getComposition(), this$0.slotTable, anchor, invalidations, this$0.currentCompositionLocalScope(group), list);
        return reference;
    }

    private static final MovableContentStateReference reportFreeMovableContent$movableContentReferenceFor(GapComposer this$0, int group) {
        int key = this$0.reader.groupKey(group);
        Object objectKey = this$0.reader.groupObjectKey(group);
        List nestedStates = null;
        if (key != 126665345 || !(objectKey instanceof MovableContent)) {
            return null;
        }
        if (this$0.reader.containsMark(group)) {
            List nestedStates2 = new ArrayList();
            reportFreeMovableContent$movableContentReferenceFor$traverseGroups(this$0, nestedStates2, group);
            if (!nestedStates2.isEmpty()) {
                nestedStates = nestedStates2;
            }
        }
        return reportFreeMovableContent$createMovableContentReferenceForGroup(this$0, group, nestedStates);
    }

    private static final void reportFreeMovableContent$movableContentReferenceFor$traverseGroups(GapComposer this$0, List<MovableContentStateReference> list, int group) {
        int size = this$0.reader.groupSize(group);
        int end = group + size;
        int current = group + 1;
        while (current < end) {
            if (this$0.reader.hasMark(current)) {
                MovableContentStateReference it = reportFreeMovableContent$movableContentReferenceFor(this$0, current);
                if (it != null) {
                    list.add(it);
                }
            } else if (this$0.reader.containsMark(current)) {
                reportFreeMovableContent$movableContentReferenceFor$traverseGroups(this$0, list, current);
            }
            current += this$0.reader.groupSize(current);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:148:0x0109  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static final int reportFreeMovableContent$reportGroup(androidx.compose.runtime.GapComposer r30, int r31, int r32, boolean r33, int r34) {
        /*
            Method dump skipped, instruction units count: 418
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.GapComposer.reportFreeMovableContent$reportGroup(androidx.compose.runtime.GapComposer, int, int, boolean, int):int");
    }

    private final void reportFreeMovableContent(int groupBeingRemoved) {
        boolean rootIsNode = this.reader.isNode(groupBeingRemoved);
        if (rootIsNode) {
            this.changeListWriter.endNodeMovement();
            this.changeListWriter.moveDown(this.reader.node(groupBeingRemoved));
        }
        reportFreeMovableContent$reportGroup(this, groupBeingRemoved, groupBeingRemoved, rootIsNode, 0);
        this.changeListWriter.endNodeMovement();
        if (rootIsNode) {
            this.changeListWriter.moveUp();
        }
    }

    private final void reportAllMovableContent() {
        if (this.slotTable.containsMark()) {
            getComposition().updateMovingInvalidations$runtime();
            ChangeList changes = new ChangeList();
            setDeferredChanges$runtime(changes);
            SlotReader reader$iv = this.slotTable.openReader();
            try {
                this.reader = reader$iv;
                ComposerChangeListWriter this_$iv = this.changeListWriter;
                ChangeList previousChangeList$iv = this_$iv.getChangeList();
                try {
                    this_$iv.setChangeList(changes);
                    reportFreeMovableContent(0);
                    this.changeListWriter.releaseMovableContent();
                    this_$iv.setChangeList(previousChangeList$iv);
                    Unit unit = Unit.INSTANCE;
                } catch (Throwable th) {
                    this_$iv.setChangeList(previousChangeList$iv);
                    throw th;
                }
            } finally {
                reader$iv.close();
            }
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

    private final void cleanUpCompose() {
        this.pending = null;
        this.nodeIndex = 0;
        this.groupNodeCount = 0;
        this.compositeKeyHashCode = 0L;
        this.nodeExpected = false;
        this.changeListWriter.resetTransientState();
        Stack.m4416clearimpl(this.invalidateStack);
        clearUpdatedNodeCounts();
    }

    @Override // androidx.compose.runtime.InternalComposer
    public void verifyConsistent$runtime() {
        this.insertTable.verifyWellFormed();
    }

    /* JADX INFO: compiled from: GapComposer.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0001\u0018\u00002\u00020\u0001B\u0013\u0012\n\u0010\u0002\u001a\u00060\u0003R\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\nH\u0016J\b\u0010\f\u001a\u00020\nH\u0016R\u0015\u0010\u0002\u001a\u00060\u0003R\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\r"}, d2 = {"Landroidx/compose/runtime/GapComposer$CompositionContextHolder;", "Landroidx/compose/runtime/RememberObserver;", "ref", "Landroidx/compose/runtime/GapComposer$CompositionContextImpl;", "Landroidx/compose/runtime/GapComposer;", "<init>", "(Landroidx/compose/runtime/GapComposer$CompositionContextImpl;)V", "getRef", "()Landroidx/compose/runtime/GapComposer$CompositionContextImpl;", "onRemembered", "", "onAbandoned", "onForgotten", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
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

    /* JADX INFO: compiled from: GapComposer.kt */
    @Metadata(d1 = {"\u0000®\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0080\u0004\u0018\u00002\u00020\u0001B-\u0012\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\n\u0010\u000bJ\u0006\u0010$\u001a\u00020%J\u0015\u0010&\u001a\u00020%2\u0006\u0010'\u001a\u00020(H\u0010¢\u0006\u0002\b)J\u0015\u0010*\u001a\u00020%2\u0006\u0010'\u001a\u00020(H\u0010¢\u0006\u0002\b+J\u0015\u0010,\u001a\u00020%2\u0006\u0010-\u001a\u00020.H\u0010¢\u0006\u0002\b/J\u0015\u00100\u001a\u00020%2\u0006\u0010-\u001a\u00020.H\u0010¢\u0006\u0002\b1J\u0015\u00102\u001a\u00020%2\u0006\u00103\u001a\u000204H\u0010¢\u0006\u0002\b5J*\u0010:\u001a\u00020%2\u0006\u0010-\u001a\u00020.2\u0011\u0010;\u001a\r\u0012\u0004\u0012\u00020%0<¢\u0006\u0002\b=H\u0011¢\u0006\u0004\b>\u0010?J8\u0010@\u001a\b\u0012\u0004\u0012\u0002040A2\u0006\u0010-\u001a\u00020.2\u0006\u0010B\u001a\u00020C2\u0011\u0010;\u001a\r\u0012\u0004\u0012\u00020%0<¢\u0006\u0002\b=H\u0011¢\u0006\u0004\bD\u0010EJ1\u0010F\u001a\b\u0012\u0004\u0012\u0002040A2\u0006\u0010-\u001a\u00020.2\u0006\u0010B\u001a\u00020C2\f\u0010G\u001a\b\u0012\u0004\u0012\u0002040AH\u0010¢\u0006\u0002\bHJ\u0015\u0010I\u001a\u00020%2\u0006\u0010-\u001a\u00020.H\u0010¢\u0006\u0002\bJJ\u0015\u0010K\u001a\u00020%2\u0006\u00103\u001a\u000204H\u0010¢\u0006\u0002\bLJ\r\u0010P\u001a\u00020NH\u0010¢\u0006\u0002\bVJ\u000e\u0010W\u001a\u00020%2\u0006\u00103\u001a\u00020NJ\u001b\u0010X\u001a\u00020%2\f\u0010Y\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015H\u0010¢\u0006\u0002\bZJ\r\u0010[\u001a\u00020%H\u0010¢\u0006\u0002\b\\J\r\u0010]\u001a\u00020%H\u0010¢\u0006\u0002\b^J\u0015\u0010_\u001a\u00020%2\u0006\u0010`\u001a\u00020aH\u0010¢\u0006\u0002\bbJ\u0015\u0010c\u001a\u00020%2\u0006\u0010`\u001a\u00020aH\u0010¢\u0006\u0002\bdJ\u0017\u0010e\u001a\u0004\u0018\u00010f2\u0006\u0010`\u001a\u00020aH\u0010¢\u0006\u0002\bgJ)\u0010h\u001a\u00020%2\u0006\u0010`\u001a\u00020a2\u0006\u0010i\u001a\u00020f2\n\u0010j\u001a\u0006\u0012\u0002\b\u00030kH\u0010¢\u0006\u0002\blJ\u0015\u0010m\u001a\u00020%2\u0006\u0010-\u001a\u00020.H\u0010¢\u0006\u0002\bnJ\u0016\u0010r\u001a\u00020s2\f\u0010t\u001a\b\u0012\u0004\u0012\u00020%0<H\u0016R\u001a\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004X\u0090\u0004¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\f\u0010\rR\u0014\u0010\u0005\u001a\u00020\u0006X\u0090\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0007\u001a\u00020\u0006X\u0090\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0016\u0010\b\u001a\u0004\u0018\u00010\tX\u0090\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R(\u0010\u0014\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\u0015\u0018\u00010\u0015X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u0017\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001c¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0014\u0010 \u001a\u00020\u00068PX\u0090\u0004¢\u0006\u0006\u001a\u0004\b!\u0010\u0010R\u0014\u0010\"\u001a\u00020\u00068PX\u0090\u0004¢\u0006\u0006\u001a\u0004\b#\u0010\u0010R\u0014\u00106\u001a\u0002078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b8\u00109R+\u0010O\u001a\u00020N2\u0006\u0010M\u001a\u00020N8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\bT\u0010U\u001a\u0004\bP\u0010Q\"\u0004\bR\u0010SR\u0014\u0010-\u001a\u00020o8PX\u0090\u0004¢\u0006\u0006\u001a\u0004\bp\u0010q¨\u0006u"}, d2 = {"Landroidx/compose/runtime/GapComposer$CompositionContextImpl;", "Landroidx/compose/runtime/CompositionContext;", "compositeKeyHashCode", "", "Landroidx/compose/runtime/CompositeKeyHashCode;", "collectingParameterInformation", "", "collectingSourceInformation", "observerHolder", "Landroidx/compose/runtime/CompositionObserverHolder;", "<init>", "(Landroidx/compose/runtime/GapComposer;JZZLandroidx/compose/runtime/CompositionObserverHolder;)V", "getCompositeKeyHashCode$runtime", "()J", "J", "getCollectingParameterInformation$runtime", "()Z", "getCollectingSourceInformation$runtime", "getObserverHolder$runtime", "()Landroidx/compose/runtime/CompositionObserverHolder;", "inspectionTables", "", "Landroidx/compose/runtime/tooling/CompositionData;", "getInspectionTables", "()Ljava/util/Set;", "setInspectionTables", "(Ljava/util/Set;)V", "composers", "Landroidx/collection/MutableScatterSet;", "Landroidx/compose/runtime/GapComposer;", "getComposers", "()Landroidx/collection/MutableScatterSet;", "collectingCallByInformation", "getCollectingCallByInformation$runtime", "stackTraceEnabled", "getStackTraceEnabled$runtime", "dispose", "", "registerComposer", "composer", "Landroidx/compose/runtime/Composer;", "registerComposer$runtime", "unregisterComposer", "unregisterComposer$runtime", "registerComposition", "composition", "Landroidx/compose/runtime/ControlledComposition;", "registerComposition$runtime", "unregisterComposition", "unregisterComposition$runtime", "reportPausedScope", "scope", "Landroidx/compose/runtime/RecomposeScopeImpl;", "reportPausedScope$runtime", "effectCoroutineContext", "Lkotlin/coroutines/CoroutineContext;", "getEffectCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "composeInitial", "content", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "composeInitial$runtime", "(Landroidx/compose/runtime/ControlledComposition;Lkotlin/jvm/functions/Function2;)V", "composeInitialPaused", "Landroidx/collection/ScatterSet;", "shouldPause", "Landroidx/compose/runtime/ShouldPauseCallback;", "composeInitialPaused$runtime", "(Landroidx/compose/runtime/ControlledComposition;Landroidx/compose/runtime/ShouldPauseCallback;Lkotlin/jvm/functions/Function2;)Landroidx/collection/ScatterSet;", "recomposePaused", "invalidScopes", "recomposePaused$runtime", "invalidate", "invalidate$runtime", "invalidateScope", "invalidateScope$runtime", "<set-?>", "Landroidx/compose/runtime/PersistentCompositionLocalMap;", "compositionLocalScope", "getCompositionLocalScope", "()Landroidx/compose/runtime/PersistentCompositionLocalMap;", "setCompositionLocalScope", "(Landroidx/compose/runtime/PersistentCompositionLocalMap;)V", "compositionLocalScope$delegate", "Landroidx/compose/runtime/MutableState;", "getCompositionLocalScope$runtime", "updateCompositionLocalScope", "recordInspectionTable", "table", "recordInspectionTable$runtime", "startComposing", "startComposing$runtime", "doneComposing", "doneComposing$runtime", "insertMovableContent", TypedValues.Custom.S_REFERENCE, "Landroidx/compose/runtime/MovableContentStateReference;", "insertMovableContent$runtime", "deletedMovableContent", "deletedMovableContent$runtime", "movableContentStateResolve", "Landroidx/compose/runtime/MovableContentState;", "movableContentStateResolve$runtime", "movableContentStateReleased", "data", "applier", "Landroidx/compose/runtime/Applier;", "movableContentStateReleased$runtime", "reportRemovedComposition", "reportRemovedComposition$runtime", "Landroidx/compose/runtime/Composition;", "getComposition$runtime", "()Landroidx/compose/runtime/Composition;", "scheduleFrameEndCallback", "Landroidx/compose/runtime/CancellationHandle;", "action", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public final class CompositionContextImpl extends CompositionContext {
        private final boolean collectingParameterInformation;
        private final boolean collectingSourceInformation;
        private final long compositeKeyHashCode;
        private Set<Set<CompositionData>> inspectionTables;
        private final CompositionObserverHolder observerHolder;
        private final MutableScatterSet<GapComposer> composers = ScatterSetKt.mutableScatterSetOf();

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

        public final MutableScatterSet<GapComposer> getComposers() {
            return this.composers;
        }

        @Override // androidx.compose.runtime.CompositionContext
        public boolean getCollectingCallByInformation$runtime() {
            return GapComposer.this.parentContext.getCollectingCallByInformation$runtime();
        }

        @Override // androidx.compose.runtime.CompositionContext
        public boolean getStackTraceEnabled$runtime() {
            return GapComposer.this.parentContext.getStackTraceEnabled$runtime();
        }

        public final void dispose() {
            ScatterSet this_$iv;
            if (this.composers.isNotEmpty()) {
                Set<Set<CompositionData>> set = this.inspectionTables;
                if (set != null) {
                    int i = 0;
                    ScatterSet this_$iv2 = this.composers;
                    Object[] elements$iv = this_$iv2.elements;
                    long[] m$iv$iv = this_$iv2.metadata;
                    int lastIndex$iv$iv = m$iv$iv.length - 2;
                    int i$iv$iv = 0;
                    if (0 <= lastIndex$iv$iv) {
                        while (true) {
                            long slot$iv$iv = m$iv$iv[i$iv$iv];
                            Set<Set<CompositionData>> set2 = set;
                            int i2 = i;
                            if ((((~slot$iv$iv) << 7) & slot$iv$iv & (-9187201950435737472L)) == -9187201950435737472L) {
                                this_$iv = this_$iv2;
                            } else {
                                int i3 = 8;
                                int bitCount$iv$iv = 8 - ((~(i$iv$iv - lastIndex$iv$iv)) >>> 31);
                                int j$iv$iv = 0;
                                while (j$iv$iv < bitCount$iv$iv) {
                                    long value$iv$iv$iv = 255 & slot$iv$iv;
                                    if (value$iv$iv$iv < 128) {
                                        int index$iv$iv = (i$iv$iv << 3) + j$iv$iv;
                                        GapComposer composer = (GapComposer) elements$iv[index$iv$iv];
                                        Iterator<Set<CompositionData>> it = set2.iterator();
                                        while (it.hasNext()) {
                                            it.next().remove(composer.getCompositionData());
                                            this_$iv2 = this_$iv2;
                                            i3 = i3;
                                        }
                                    }
                                    int i4 = i3;
                                    slot$iv$iv >>= i4;
                                    j$iv$iv++;
                                    this_$iv2 = this_$iv2;
                                    i3 = i4;
                                }
                                this_$iv = this_$iv2;
                                if (bitCount$iv$iv != i3) {
                                    break;
                                }
                            }
                            if (i$iv$iv == lastIndex$iv$iv) {
                                break;
                            }
                            i$iv$iv++;
                            set = set2;
                            i = i2;
                            this_$iv2 = this_$iv;
                        }
                    }
                }
                this.composers.clear();
            }
        }

        @Override // androidx.compose.runtime.CompositionContext
        public void registerComposer$runtime(Composer composer) {
            Intrinsics.checkNotNull(composer, "null cannot be cast to non-null type androidx.compose.runtime.GapComposer");
            super.registerComposer$runtime((GapComposer) composer);
            this.composers.add(composer);
        }

        @Override // androidx.compose.runtime.CompositionContext
        public void unregisterComposer$runtime(Composer composer) {
            Iterable iterable = this.inspectionTables;
            if (iterable != null) {
                Iterable $this$forEach$iv = iterable;
                for (Object element$iv : $this$forEach$iv) {
                    Set it = (Set) element$iv;
                    Intrinsics.checkNotNull(composer, "null cannot be cast to non-null type androidx.compose.runtime.GapComposer");
                    it.remove(((GapComposer) composer).getCompositionData());
                }
            }
            if (composer instanceof GapComposer) {
                this.composers.remove(composer);
            }
        }

        @Override // androidx.compose.runtime.CompositionContext
        public void registerComposition$runtime(ControlledComposition composition) {
            GapComposer.this.parentContext.registerComposition$runtime(composition);
        }

        @Override // androidx.compose.runtime.CompositionContext
        public void unregisterComposition$runtime(ControlledComposition composition) {
            GapComposer.this.parentContext.unregisterComposition$runtime(composition);
        }

        @Override // androidx.compose.runtime.CompositionContext
        public void reportPausedScope$runtime(RecomposeScopeImpl scope) {
            GapComposer.this.parentContext.reportPausedScope$runtime(scope);
        }

        @Override // androidx.compose.runtime.CompositionContext
        public CoroutineContext getEffectCoroutineContext() {
            return GapComposer.this.parentContext.getEffectCoroutineContext();
        }

        @Override // androidx.compose.runtime.CompositionContext
        public void composeInitial$runtime(ControlledComposition composition, Function2<? super Composer, ? super Integer, Unit> content) {
            GapComposer.this.parentContext.composeInitial$runtime(composition, content);
        }

        @Override // androidx.compose.runtime.CompositionContext
        public ScatterSet<RecomposeScopeImpl> composeInitialPaused$runtime(ControlledComposition composition, ShouldPauseCallback shouldPause, Function2<? super Composer, ? super Integer, Unit> content) {
            return GapComposer.this.parentContext.composeInitialPaused$runtime(composition, shouldPause, content);
        }

        @Override // androidx.compose.runtime.CompositionContext
        public ScatterSet<RecomposeScopeImpl> recomposePaused$runtime(ControlledComposition composition, ShouldPauseCallback shouldPause, ScatterSet<RecomposeScopeImpl> invalidScopes) {
            return GapComposer.this.parentContext.recomposePaused$runtime(composition, shouldPause, invalidScopes);
        }

        @Override // androidx.compose.runtime.CompositionContext
        public void invalidate$runtime(ControlledComposition composition) {
            GapComposer.this.parentContext.invalidate$runtime(GapComposer.this.getComposition());
            GapComposer.this.parentContext.invalidate$runtime(composition);
        }

        @Override // androidx.compose.runtime.CompositionContext
        public void invalidateScope$runtime(RecomposeScopeImpl scope) {
            GapComposer.this.parentContext.invalidateScope$runtime(scope);
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
            GapComposer.this.childrenComposing++;
        }

        @Override // androidx.compose.runtime.CompositionContext
        public void doneComposing$runtime() {
            GapComposer.this.childrenComposing--;
        }

        @Override // androidx.compose.runtime.CompositionContext
        public void insertMovableContent$runtime(MovableContentStateReference movableContentStateReference) {
            GapComposer.this.parentContext.insertMovableContent$runtime(movableContentStateReference);
        }

        @Override // androidx.compose.runtime.CompositionContext
        public void deletedMovableContent$runtime(MovableContentStateReference movableContentStateReference) {
            GapComposer.this.parentContext.deletedMovableContent$runtime(movableContentStateReference);
        }

        @Override // androidx.compose.runtime.CompositionContext
        public MovableContentState movableContentStateResolve$runtime(MovableContentStateReference movableContentStateReference) {
            return GapComposer.this.parentContext.movableContentStateResolve$runtime(movableContentStateReference);
        }

        @Override // androidx.compose.runtime.CompositionContext
        public void movableContentStateReleased$runtime(MovableContentStateReference movableContentStateReference, MovableContentState data, Applier<?> applier) {
            GapComposer.this.parentContext.movableContentStateReleased$runtime(movableContentStateReference, data, applier);
        }

        @Override // androidx.compose.runtime.CompositionContext
        public void reportRemovedComposition$runtime(ControlledComposition composition) {
            GapComposer.this.parentContext.reportRemovedComposition$runtime(composition);
        }

        @Override // androidx.compose.runtime.CompositionContext
        public Composition getComposition$runtime() {
            return GapComposer.this.getComposition();
        }

        @Override // androidx.compose.runtime.CompositionContext
        public CancellationHandle scheduleFrameEndCallback(Function0<Unit> action) {
            return GapComposer.this.parentContext.scheduleFrameEndCallback(action);
        }
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

    @Override // androidx.compose.runtime.InternalComposer
    public int stacksSize$runtime() {
        IntStack this_$iv = this.entersStack;
        int iM4421getSizeimpl = this_$iv.tos + Stack.m4421getSizeimpl(this.invalidateStack);
        IntStack this_$iv2 = this.providersInvalidStack;
        int iM4421getSizeimpl2 = iM4421getSizeimpl + this_$iv2.tos + Stack.m4421getSizeimpl(this.pendingStack);
        IntStack this_$iv3 = this.parentStateStack;
        return iM4421getSizeimpl2 + this_$iv3.tos;
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
    public Object rememberedValue() {
        return nextSlotForCache();
    }

    @Override // androidx.compose.runtime.Composer
    public void updateRememberedValue(Object value) {
        updateCachedValue(value);
    }

    @Override // androidx.compose.runtime.Composer
    public void recordUsed(RecomposeScope scope) {
        RecomposeScopeImpl recomposeScopeImpl = scope instanceof RecomposeScopeImpl ? (RecomposeScopeImpl) scope : null;
        if (recomposeScopeImpl != null) {
            recomposeScopeImpl.setUsed(true);
        }
    }
}
