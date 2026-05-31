package androidx.compose.runtime.composer.gapbuffer;

import androidx.autofill.HintConstants;
import androidx.collection.MutableIntObjectMap;
import androidx.collection.MutableIntSet;
import androidx.collection.MutableObjectList;
import androidx.collection.MutableScatterMap;
import androidx.collection.ObjectList;
import androidx.collection.ScatterMap;
import androidx.collection.ScatterMapKt;
import androidx.compose.runtime.Anchor;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.GapComposerKt;
import androidx.compose.runtime.MovableContentState;
import androidx.compose.runtime.MovableContentStateReference;
import androidx.compose.runtime.PreconditionsKt;
import androidx.compose.runtime.RecomposeScope;
import androidx.compose.runtime.RecomposeScopeImpl;
import androidx.compose.runtime.SlotStorage;
import androidx.compose.runtime.collection.ExtensionsKt;
import androidx.compose.runtime.composer.RememberManager;
import androidx.compose.runtime.composer.linkbuffer.GroupFlagsKt;
import androidx.compose.runtime.tooling.CompositionData;
import androidx.compose.runtime.tooling.CompositionGroup;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.sequences.SequencesKt;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: SlotTable.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000Þ\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010(\n\u0002\b\u0003\b\u0001\u0018\u00002\u00020\u00012\u00020\u00022\b\u0012\u0004\u0012\u00020\u00040\u0003B\u0007¢\u0006\u0004\b\u0005\u0010\u0006J\u0010\u0010<\u001a\u00020=2\u0006\u0010>\u001a\u00020?H\u0016J7\u0010@\u001a\u0002HA\"\u0004\b\u0000\u0010A2!\u0010B\u001a\u001d\u0012\u0013\u0012\u00110D¢\u0006\f\bE\u0012\b\bF\u0012\u0004\b\b(G\u0012\u0004\u0012\u0002HA0CH\u0086\b¢\u0006\u0002\u0010HJ7\u0010I\u001a\u0002HA\"\u0004\b\u0000\u0010A2!\u0010B\u001a\u001d\u0012\u0013\u0012\u00110J¢\u0006\f\bE\u0012\b\bF\u0012\u0004\b\b(\u001d\u0012\u0004\u0012\u0002HA0CH\u0086\b¢\u0006\u0002\u0010HJ\u0006\u0010K\u001a\u00020DJ\u0006\u0010L\u001a\u00020JJ\u000e\u0010M\u001a\u00020&2\u0006\u0010N\u001a\u00020\fJ\u0012\u0010O\u001a\u0004\u0018\u00010&2\u0006\u0010N\u001a\u00020\fH\u0002J\u000e\u0010P\u001a\u00020\f2\u0006\u0010M\u001a\u00020&J\u000e\u0010Q\u001a\u00020\u001c2\u0006\u0010M\u001a\u00020&J\u0018\u0010R\u001a\u00020\u001c2\u0006\u0010S\u001a\u00020\f2\u0006\u0010M\u001a\u00020TH\u0016J\u0018\u0010U\u001a\u00020\u001c2\u0006\u0010V\u001a\u00020T2\u0006\u0010W\u001a\u00020TH\u0016J=\u0010X\u001a\u00020=2\u0006\u0010G\u001a\u00020D2&\u0010,\u001a\"\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020.\u0018\u00010-j\u0010\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020.\u0018\u0001`/H\u0000¢\u0006\u0002\bYJ\u008f\u0001\u0010X\u001a\u00020=2\u0006\u0010\u001d\u001a\u00020J2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\f2\u000e\u0010\u0012\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00110\u00102\u0006\u0010\u0016\u001a\u00020\f2\u0016\u0010$\u001a\u0012\u0012\u0004\u0012\u00020&0%j\b\u0012\u0004\u0012\u00020&`'2&\u0010,\u001a\"\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020.\u0018\u00010-j\u0010\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020.\u0018\u0001`/2\u000e\u00104\u001a\n\u0012\u0004\u0012\u000206\u0018\u000105H\u0000¢\u0006\u0004\bY\u0010ZJ\u0087\u0001\u0010[\u001a\u00020=2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\f2\u000e\u0010\u0012\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00110\u00102\u0006\u0010\u0016\u001a\u00020\f2\u0016\u0010$\u001a\u0012\u0012\u0004\u0012\u00020&0%j\b\u0012\u0004\u0012\u00020&`'2&\u0010,\u001a\"\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020.\u0018\u00010-j\u0010\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020.\u0018\u0001`/2\u000e\u00104\u001a\n\u0012\u0004\u0012\u000206\u0018\u000105H\u0000¢\u0006\u0004\b\\\u0010]J\u0018\u0010^\u001a\n\u0012\u0004\u0012\u00020`\u0018\u00010_2\u0006\u0010a\u001a\u00020\fH\u0016J\u0010\u0010b\u001a\u00020\u001c2\u0006\u0010c\u001a\u00020`H\u0016J\u0006\u0010d\u001a\u00020\u001cJ\u0010\u0010e\u001a\u0004\u0018\u00010.2\u0006\u0010S\u001a\u00020\fJ\u0012\u0010f\u001a\u0004\u0018\u00010`2\u0006\u0010S\u001a\u00020\fH\u0002J\b\u0010g\u001a\u00020=H\u0016J\b\u0010h\u001a\u00020=H\u0002J\u0010\u0010\u0013\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00110\u0003H\u0016J\b\u0010i\u001a\u00020=H\u0016J\b\u0010j\u001a\u00020=H\u0016J\u0010\u0010k\u001a\u00020=2\u0006\u0010>\u001a\u00020?H\u0016J\b\u0010l\u001a\u00020=H\u0016J.\u0010m\u001a\u000e\u0012\u0004\u0012\u00020o\u0012\u0004\u0012\u00020p0n2\n\u0010q\u001a\u0006\u0012\u0002\b\u00030r2\f\u0010s\u001a\b\u0012\u0004\u0012\u00020o0tH\u0016J\u0018\u0010u\u001a\u00020=2\u0006\u0010>\u001a\u00020?2\u0006\u0010v\u001a\u00020pH\u0016J\b\u0010w\u001a\u00020=H\u0016J\b\u0010x\u001a\u00020yH\u0016J \u0010z\u001a\u00020\f*\u00060{j\u0002`|2\u0006\u0010N\u001a\u00020\f2\u0006\u0010}\u001a\u00020\fH\u0002J\u000e\u0010~\u001a\b\u0012\u0004\u0012\u00020\f0_H\u0002J\u000e\u0010\u007f\u001a\b\u0012\u0004\u0012\u00020\f0_H\u0002J\u000f\u0010\u0080\u0001\u001a\b\u0012\u0004\u0012\u00020\f0_H\u0002J\u000f\u0010\u0081\u0001\u001a\b\u0012\u0004\u0012\u00020\f0_H\u0002J\u000f\u0010\u0082\u0001\u001a\b\u0012\u0004\u0012\u00020\f0_H\u0002J\u001f\u0010\u0083\u0001\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00110_2\u0006\u0010S\u001a\u00020\fH\u0000¢\u0006\u0003\b\u0084\u0001J\"\u0010\u0085\u0001\u001a\u0004\u0018\u00010\u00112\u0006\u0010S\u001a\u00020\f2\u0007\u0010\u0086\u0001\u001a\u00020\fH\u0000¢\u0006\u0003\b\u0087\u0001J\u0011\u0010\u008b\u0001\u001a\t\u0012\u0004\u0012\u00020\u00040\u008c\u0001H\u0096\u0002J\u0014\u0010\u008d\u0001\u001a\u0004\u0018\u00010\u00042\u0007\u0010\u008e\u0001\u001a\u00020\u0011H\u0016R\u001e\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001e\u0010\r\u001a\u00020\f2\u0006\u0010\u0007\u001a\u00020\f@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR0\u0010\u0012\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00110\u00102\u000e\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00110\u0010@BX\u0086\u000e¢\u0006\n\n\u0002\u0010\u0015\u001a\u0004\b\u0013\u0010\u0014R\u001e\u0010\u0016\u001a\u00020\f2\u0006\u0010\u0007\u001a\u00020\f@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u000fR\u000e\u0010\u0018\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0019\u001a\u00060\u0011j\u0002`\u001aX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001bR\u001e\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u0007\u001a\u00020\u001c@BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u001a\u0010 \u001a\u00020\fX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u000f\"\u0004\b\"\u0010#R*\u0010$\u001a\u0012\u0012\u0004\u0012\u00020&0%j\b\u0012\u0004\u0012\u00020&`'X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+R:\u0010,\u001a\"\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020.\u0018\u00010-j\u0010\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020.\u0018\u0001`/X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u00101\"\u0004\b2\u00103R\"\u00104\u001a\n\u0012\u0004\u0012\u000206\u0018\u000105X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b7\u00108\"\u0004\b9\u0010:R\u0014\u0010;\u001a\u00020\u001c8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b;\u0010\u001fR\u001d\u0010\u0088\u0001\u001a\b\u0012\u0004\u0012\u00020\u00040\u00038VX\u0096\u0004¢\u0006\b\u001a\u0006\b\u0089\u0001\u0010\u008a\u0001¨\u0006\u008f\u0001"}, d2 = {"Landroidx/compose/runtime/composer/gapbuffer/SlotTable;", "Landroidx/compose/runtime/SlotStorage;", "Landroidx/compose/runtime/tooling/CompositionData;", "", "Landroidx/compose/runtime/tooling/CompositionGroup;", "<init>", "()V", "value", "", "groups", "getGroups", "()[I", "", "groupsSize", "getGroupsSize", "()I", "", "", "slots", "getSlots", "()[Ljava/lang/Object;", "[Ljava/lang/Object;", "slotsSize", "getSlotsSize", "readers", "lock", "Landroidx/compose/runtime/platform/SynchronizedObject;", "Ljava/lang/Object;", "", "writer", "getWriter$runtime", "()Z", "version", "getVersion$runtime", "setVersion$runtime", "(I)V", "anchors", "Ljava/util/ArrayList;", "Landroidx/compose/runtime/composer/gapbuffer/GapAnchor;", "Lkotlin/collections/ArrayList;", "getAnchors$runtime", "()Ljava/util/ArrayList;", "setAnchors$runtime", "(Ljava/util/ArrayList;)V", "sourceInformationMap", "Ljava/util/HashMap;", "Landroidx/compose/runtime/composer/gapbuffer/GapGroupSourceInformation;", "Lkotlin/collections/HashMap;", "getSourceInformationMap$runtime", "()Ljava/util/HashMap;", "setSourceInformationMap$runtime", "(Ljava/util/HashMap;)V", "calledByMap", "Landroidx/collection/MutableIntObjectMap;", "Landroidx/collection/MutableIntSet;", "getCalledByMap$runtime", "()Landroidx/collection/MutableIntObjectMap;", "setCalledByMap$runtime", "(Landroidx/collection/MutableIntObjectMap;)V", "isEmpty", "clear", "", "rememberManager", "Landroidx/compose/runtime/composer/RememberManager;", "read", "T", "block", "Lkotlin/Function1;", "Landroidx/compose/runtime/composer/gapbuffer/SlotReader;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "reader", "(Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "write", "Landroidx/compose/runtime/composer/gapbuffer/SlotWriter;", "openReader", "openWriter", "anchor", "index", "tryAnchor", "anchorIndex", "ownsAnchor", "groupContainsAnchor", "group", "Landroidx/compose/runtime/Anchor;", "inGroup", "parent", "child", "close", "close$runtime", "(Landroidx/compose/runtime/composer/gapbuffer/SlotWriter;[II[Ljava/lang/Object;ILjava/util/ArrayList;Ljava/util/HashMap;Landroidx/collection/MutableIntObjectMap;)V", "setTo", "setTo$runtime", "([II[Ljava/lang/Object;ILjava/util/ArrayList;Ljava/util/HashMap;Landroidx/collection/MutableIntObjectMap;)V", "invalidateGroupsWithKey", "", "Landroidx/compose/runtime/RecomposeScopeImpl;", TypedValues.AttributesType.S_TARGET, "ownsRecomposeScope", "scope", "containsMark", "sourceInformationOf", "findEffectiveRecomposeScope", "verifyWellFormed", "validateRecomposeScopeAnchors", "collectCalledByInformation", "collectSourceInformation", "deactivateAll", "dispose", "extractNestedStates", "Landroidx/collection/ScatterMap;", "Landroidx/compose/runtime/MovableContentStateReference;", "Landroidx/compose/runtime/MovableContentState;", "applier", "Landroidx/compose/runtime/Applier;", "references", "Landroidx/collection/ObjectList;", "disposeUnusedMovableContent", "state", "invalidateAll", "toDebugString", "", "emitGroup", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "level", "keys", "nodes", "parentIndexes", "dataIndexes", "groupSizes", "slotsOf", "slotsOf$runtime", "slot", "slotIndex", "slot$runtime", "compositionGroups", "getCompositionGroups", "()Ljava/lang/Iterable;", "iterator", "", "find", "identityToFind", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class SlotTable extends SlotStorage implements CompositionData, Iterable<CompositionGroup>, KMappedMarker {
    public static final int $stable = 8;
    private MutableIntObjectMap<MutableIntSet> calledByMap;
    private int groupsSize;
    private int readers;
    private int slotsSize;
    private HashMap<GapAnchor, GapGroupSourceInformation> sourceInformationMap;
    private int version;
    private boolean writer;
    private int[] groups = new int[0];
    private Object[] slots = new Object[0];
    private final Object lock = new Object();
    private ArrayList<GapAnchor> anchors = new ArrayList<>();

    public final int[] getGroups() {
        return this.groups;
    }

    public final int getGroupsSize() {
        return this.groupsSize;
    }

    public final Object[] getSlots() {
        return this.slots;
    }

    public final int getSlotsSize() {
        return this.slotsSize;
    }

    /* JADX INFO: renamed from: getWriter$runtime, reason: from getter */
    public final boolean getWriter() {
        return this.writer;
    }

    /* JADX INFO: renamed from: getVersion$runtime, reason: from getter */
    public final int getVersion() {
        return this.version;
    }

    public final void setVersion$runtime(int i) {
        this.version = i;
    }

    public final ArrayList<GapAnchor> getAnchors$runtime() {
        return this.anchors;
    }

    public final void setAnchors$runtime(ArrayList<GapAnchor> arrayList) {
        this.anchors = arrayList;
    }

    public final HashMap<GapAnchor, GapGroupSourceInformation> getSourceInformationMap$runtime() {
        return this.sourceInformationMap;
    }

    public final void setSourceInformationMap$runtime(HashMap<GapAnchor, GapGroupSourceInformation> map) {
        this.sourceInformationMap = map;
    }

    public final MutableIntObjectMap<MutableIntSet> getCalledByMap$runtime() {
        return this.calledByMap;
    }

    public final void setCalledByMap$runtime(MutableIntObjectMap<MutableIntSet> mutableIntObjectMap) {
        this.calledByMap = mutableIntObjectMap;
    }

    @Override // androidx.compose.runtime.SlotStorage, androidx.compose.runtime.tooling.CompositionData
    public boolean isEmpty() {
        return this.groupsSize == 0;
    }

    @Override // androidx.compose.runtime.SlotStorage
    public void clear(RememberManager rememberManager) {
        SlotWriter writer$iv = openWriter();
        boolean normalClose$iv = false;
        try {
            ComposerKt.removeCurrentGroup(writer$iv, rememberManager);
            Unit unit = Unit.INSTANCE;
            normalClose$iv = true;
        } finally {
            writer$iv.close(normalClose$iv);
        }
    }

    public final <T> T read(Function1<? super SlotReader, ? extends T> block) {
        SlotReader reader = openReader();
        try {
            return block.invoke(reader);
        } finally {
            reader.close();
        }
    }

    public final <T> T write(Function1<? super SlotWriter, ? extends T> block) {
        SlotWriter writer = openWriter();
        boolean normalClose = false;
        try {
            normalClose = true;
            return block.invoke(writer);
        } finally {
            writer.close(normalClose);
        }
    }

    public final SlotReader openReader() {
        if (this.writer) {
            throw new IllegalStateException("Cannot read while a writer is pending".toString());
        }
        this.readers++;
        return new SlotReader(this);
    }

    public final SlotWriter openWriter() {
        boolean value$iv = !this.writer;
        if (!value$iv) {
            ComposerKt.composeImmediateRuntimeError("Cannot start a writer when another writer is pending");
        }
        boolean value$iv2 = this.readers <= 0;
        if (!value$iv2) {
            ComposerKt.composeImmediateRuntimeError("Cannot start a writer when a reader is pending");
        }
        this.writer = true;
        this.version++;
        return new SlotWriter(this);
    }

    public final GapAnchor anchor(int index) {
        boolean value$iv = !this.writer;
        if (!value$iv) {
            ComposerKt.composeImmediateRuntimeError("use active SlotWriter to create an anchor location instead");
        }
        boolean value$iv2 = index >= 0 && index < this.groupsSize;
        if (!value$iv2) {
            PreconditionsKt.throwIllegalArgumentException("Parameter index is out of range");
        }
        ArrayList<GapAnchor> arrayList = this.anchors;
        int effectiveSize$iv = this.groupsSize;
        int location$iv = SlotTableKt.search(arrayList, index, effectiveSize$iv);
        if (location$iv < 0) {
            GapAnchor anchor$iv = new GapAnchor(index);
            arrayList.add(-(location$iv + 1), anchor$iv);
            return anchor$iv;
        }
        return arrayList.get(location$iv);
    }

    private final GapAnchor tryAnchor(int index) {
        boolean value$iv = !this.writer;
        if (!value$iv) {
            ComposerKt.composeImmediateRuntimeError("use active SlotWriter to crate an anchor for location instead");
        }
        if (index >= 0 && index < this.groupsSize) {
            return SlotTableKt.find(this.anchors, index, this.groupsSize);
        }
        return null;
    }

    public final int anchorIndex(GapAnchor anchor) {
        boolean value$iv = !this.writer;
        if (!value$iv) {
            ComposerKt.composeImmediateRuntimeError("Use active SlotWriter to determine anchor location instead");
        }
        boolean value$iv2 = anchor.getValid();
        if (!value$iv2) {
            PreconditionsKt.throwIllegalArgumentException("Anchor refers to a group that was removed");
        }
        return anchor.getLocation();
    }

    public final boolean ownsAnchor(GapAnchor anchor) {
        if (!anchor.getValid()) {
            return false;
        }
        int it = SlotTableKt.search(this.anchors, anchor.getLocation(), this.groupsSize);
        return ((it < 0 || !Intrinsics.areEqual(this.anchors.get(it), anchor)) ? 0 : 1) != 0;
    }

    @Override // androidx.compose.runtime.SlotStorage
    public boolean groupContainsAnchor(int group, Anchor anchor) {
        boolean value$iv = !this.writer;
        if (!value$iv) {
            ComposerKt.composeImmediateRuntimeError("Writer is active");
        }
        boolean value$iv2 = group >= 0 && group < this.groupsSize;
        if (!value$iv2) {
            ComposerKt.composeImmediateRuntimeError("Invalid group index");
        }
        GapAnchor gapAnchor = GapAnchorKt.asGapAnchor(anchor);
        if (ownsAnchor(gapAnchor)) {
            int iGroupSize = SlotTableKt.groupSize(this.groups, group) + group;
            int location = gapAnchor.getLocation();
            if (group <= location && location < iGroupSize) {
                return true;
            }
        }
        return false;
    }

    @Override // androidx.compose.runtime.SlotStorage
    public boolean inGroup(Anchor parent, Anchor child) {
        int group = GapAnchorKt.asGapAnchor(parent).getLocation();
        int groupEnd = SlotTableKt.groupSize(this.groups, group) + group;
        int location = GapAnchorKt.asGapAnchor(child).getLocation();
        return group <= location && location < groupEnd;
    }

    public final void close$runtime(SlotReader reader, HashMap<GapAnchor, GapGroupSourceInformation> sourceInformationMap) {
        boolean value$iv = reader.getTable() == this && this.readers > 0;
        if (!value$iv) {
            ComposerKt.composeImmediateRuntimeError("Unexpected reader close()");
        }
        this.readers--;
        if (sourceInformationMap != null) {
            Object lock$iv = this.lock;
            synchronized (lock$iv) {
                HashMap<GapAnchor, GapGroupSourceInformation> map = this.sourceInformationMap;
                if (map != null) {
                    map.putAll(sourceInformationMap);
                } else {
                    this.sourceInformationMap = sourceInformationMap;
                }
                Unit unit = Unit.INSTANCE;
            }
        }
    }

    public final void close$runtime(SlotWriter writer, int[] groups, int groupsSize, Object[] slots, int slotsSize, ArrayList<GapAnchor> anchors, HashMap<GapAnchor, GapGroupSourceInformation> sourceInformationMap, MutableIntObjectMap<MutableIntSet> calledByMap) {
        boolean value$iv = writer.getTable() == this && this.writer;
        if (!value$iv) {
            PreconditionsKt.throwIllegalArgumentException("Unexpected writer close()");
        }
        this.writer = false;
        setTo$runtime(groups, groupsSize, slots, slotsSize, anchors, sourceInformationMap, calledByMap);
    }

    public final void setTo$runtime(int[] groups, int groupsSize, Object[] slots, int slotsSize, ArrayList<GapAnchor> anchors, HashMap<GapAnchor, GapGroupSourceInformation> sourceInformationMap, MutableIntObjectMap<MutableIntSet> calledByMap) {
        this.groups = groups;
        this.groupsSize = groupsSize;
        this.slots = slots;
        this.slotsSize = slotsSize;
        this.anchors = anchors;
        this.sourceInformationMap = sourceInformationMap;
        this.calledByMap = calledByMap;
    }

    @Override // androidx.compose.runtime.SlotStorage
    public List<RecomposeScopeImpl> invalidateGroupsWithKey(int target) throws Throwable {
        MutableIntSet it;
        List anchors = new ArrayList();
        List scopes = new ArrayList();
        Ref.BooleanRef allScopesFound = new Ref.BooleanRef();
        allScopesFound.element = true;
        MutableIntSet set = new MutableIntSet(0, 1, null);
        set.add(target);
        set.add(-3);
        MutableIntObjectMap<MutableIntSet> mutableIntObjectMap = this.calledByMap;
        if (mutableIntObjectMap != null && (it = mutableIntObjectMap.get(target)) != null) {
            set.addAll(it);
        }
        SlotReader reader$iv = openReader();
        try {
            invalidateGroupsWithKey$lambda$2$scanGroup(reader$iv, set, anchors, allScopesFound, this, scopes);
            Unit unit = Unit.INSTANCE;
            reader$iv.close();
            SlotTable this_$iv = this;
            SlotWriter writer$iv = this_$iv.openWriter();
            try {
                writer$iv.startGroup();
                int index$iv = 0;
                int size = anchors.size();
                while (index$iv < size) {
                    Object item$iv = anchors.get(index$iv);
                    GapAnchor anchor = (GapAnchor) item$iv;
                    SlotTable this_$iv2 = this_$iv;
                    try {
                        MutableIntSet set2 = set;
                        try {
                            if (anchor.toIndexFor(writer$iv) >= writer$iv.getCurrentGroup()) {
                                writer$iv.seek(anchor);
                                writer$iv.bashCurrentGroup();
                            }
                            index$iv++;
                            this_$iv = this_$iv2;
                            set = set2;
                        } catch (Throwable th) {
                            th = th;
                            writer$iv.close(false);
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        writer$iv.close(false);
                        throw th;
                    }
                }
                writer$iv.skipToGroupEnd();
                writer$iv.endGroup();
                writer$iv.close(true);
                if (allScopesFound.element) {
                    return scopes;
                }
                return null;
            } catch (Throwable th3) {
                th = th3;
            }
        } catch (Throwable th4) {
            reader$iv.close();
            throw th4;
        }
    }

    private static final void invalidateGroupsWithKey$lambda$2$scanGroup(SlotReader $reader, MutableIntSet set, List<GapAnchor> list, Ref.BooleanRef allScopesFound, SlotTable this$0, List<RecomposeScopeImpl> list2) {
        RecomposeScopeImpl parentScope;
        GapAnchor gapAnchorAsGapAnchor;
        int key = $reader.getGroupKey();
        if (set.contains(key)) {
            if (key != -3) {
                list.add(SlotReader.anchor$default($reader, 0, 1, null));
            }
            if (allScopesFound.element) {
                RecomposeScopeImpl nearestScope = this$0.findEffectiveRecomposeScope($reader.getCurrent());
                if (nearestScope != null) {
                    list2.add(nearestScope);
                    Anchor anchor = nearestScope.getAnchor();
                    if (((anchor == null || (gapAnchorAsGapAnchor = GapAnchorKt.asGapAnchor(anchor)) == null || gapAnchorAsGapAnchor.getLocation() != $reader.getCurrent()) ? false : true) && (parentScope = this$0.findEffectiveRecomposeScope($reader.getParent())) != null) {
                        list2.add(parentScope);
                    }
                } else {
                    allScopesFound.element = false;
                    list2.clear();
                }
            }
            $reader.skipGroup();
            return;
        }
        $reader.startGroup();
        while (!$reader.isGroupEnd()) {
            invalidateGroupsWithKey$lambda$2$scanGroup($reader, set, list, allScopesFound, this$0, list2);
        }
        $reader.endGroup();
    }

    @Override // androidx.compose.runtime.SlotStorage
    public boolean ownsRecomposeScope(RecomposeScopeImpl scope) {
        Anchor it = scope.getAnchor();
        return it != null && ownsAnchor(GapAnchorKt.asGapAnchor(it));
    }

    public final boolean containsMark() {
        if (this.groupsSize <= 0) {
            return false;
        }
        int[] $this$containsMark$iv = this.groups;
        return ($this$containsMark$iv[(0 * 5) + 1] & 67108864) != 0;
    }

    public final GapGroupSourceInformation sourceInformationOf(int group) {
        GapAnchor anchor;
        HashMap<GapAnchor, GapGroupSourceInformation> map = this.sourceInformationMap;
        if (map == null || (anchor = tryAnchor(group)) == null) {
            return null;
        }
        return map.get(anchor);
    }

    private final RecomposeScopeImpl findEffectiveRecomposeScope(int group) {
        int current = group;
        while (current > 0) {
            for (Object data : new DataIterator(this, current)) {
                if (data instanceof RecomposeScopeImpl) {
                    if (((RecomposeScopeImpl) data).getUsed() && current != group) {
                        return (RecomposeScopeImpl) data;
                    }
                    ((RecomposeScopeImpl) data).setForcedRecompose(true);
                }
            }
            int[] $this$parentAnchor$iv = this.groups;
            int address$iv = current;
            current = $this$parentAnchor$iv[(address$iv * 5) + 2];
        }
        return null;
    }

    @Override // androidx.compose.runtime.SlotStorage
    public void verifyWellFormed() {
        Ref.IntRef current = new Ref.IntRef();
        if (this.groupsSize > 0) {
            while (current.element < this.groupsSize) {
                verifyWellFormed$validateGroup(current, this, -1, current.element + SlotTableKt.groupSize(this.groups, current.element));
            }
            boolean value$iv = current.element == this.groupsSize;
            if (!value$iv) {
                PreconditionsKt.throwIllegalStateException("Incomplete group at root " + current.element + " expected to be " + this.groupsSize);
            }
        }
        int length = this.slots.length;
        for (int index = this.slotsSize; index < length; index++) {
            boolean value$iv2 = this.slots[index] == null;
            if (!value$iv2) {
                PreconditionsKt.throwIllegalStateException("Non null value in the slot gap at index " + index);
            }
        }
        int lastLocation = -1;
        List $this$fastForEach$iv = this.anchors;
        int size = $this$fastForEach$iv.size();
        for (int index$iv = 0; index$iv < size; index$iv++) {
            Object item$iv = $this$fastForEach$iv.get(index$iv);
            int location = ((GapAnchor) item$iv).toIndexFor(this);
            boolean value$iv3 = location >= 0 && location <= this.groupsSize;
            if (!value$iv3) {
                PreconditionsKt.throwIllegalArgumentException("Invalid anchor, location out of bound");
            }
            boolean value$iv4 = lastLocation < location;
            if (!value$iv4) {
                PreconditionsKt.throwIllegalArgumentException("Anchor is out of order");
            }
            lastLocation = location;
        }
        HashMap<GapAnchor, GapGroupSourceInformation> map = this.sourceInformationMap;
        if (map != null) {
            for (Map.Entry<GapAnchor, GapGroupSourceInformation> entry : map.entrySet()) {
                GapAnchor anchor = entry.getKey();
                GapGroupSourceInformation sourceGroup = entry.getValue();
                boolean value$iv5 = anchor.getValid();
                if (!value$iv5) {
                    PreconditionsKt.throwIllegalArgumentException("Source map contains invalid anchor");
                }
                boolean value$iv6 = ownsAnchor(anchor);
                if (!value$iv6) {
                    PreconditionsKt.throwIllegalArgumentException("Source map anchor is not owned by the slot table");
                }
                verifyWellFormed$verifySourceGroup(this, sourceGroup);
            }
        }
        validateRecomposeScopeAnchors();
    }

    /* JADX WARN: Removed duplicated region for block: B:116:0x02a9  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x01b8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static final int verifyWellFormed$validateGroup(kotlin.jvm.internal.Ref.IntRef r22, androidx.compose.runtime.composer.gapbuffer.SlotTable r23, int r24, int r25) {
        /*
            Method dump skipped, instruction units count: 734
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.composer.gapbuffer.SlotTable.verifyWellFormed$validateGroup(kotlin.jvm.internal.Ref$IntRef, androidx.compose.runtime.composer.gapbuffer.SlotTable, int, int):int");
    }

    private static final void verifyWellFormed$verifySourceGroup(SlotTable this$0, GapGroupSourceInformation group) {
        List groups = group.getGroups();
        if (groups == null) {
            return;
        }
        List $this$fastForEach$iv = groups;
        int size = $this$fastForEach$iv.size();
        for (int index$iv = 0; index$iv < size; index$iv++) {
            Object item$iv = $this$fastForEach$iv.get(index$iv);
            if (item$iv instanceof GapAnchor) {
                boolean value$iv = ((GapAnchor) item$iv).getValid();
                if (!value$iv) {
                    PreconditionsKt.throwIllegalArgumentException("Source map contains invalid anchor");
                }
                boolean value$iv2 = this$0.ownsAnchor((GapAnchor) item$iv);
                if (!value$iv2) {
                    PreconditionsKt.throwIllegalArgumentException("Source map anchor is not owned by the slot table");
                }
            } else if (item$iv instanceof GapGroupSourceInformation) {
                verifyWellFormed$verifySourceGroup(this$0, (GapGroupSourceInformation) item$iv);
            }
        }
    }

    private final void validateRecomposeScopeAnchors() {
        GapAnchor anchor;
        SlotTable slotTable = this;
        Object[] $this$mapNotNull$iv = slotTable.slots;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv$iv : $this$mapNotNull$iv) {
            RecomposeScopeImpl recomposeScopeImpl = element$iv$iv$iv instanceof RecomposeScopeImpl ? (RecomposeScopeImpl) element$iv$iv$iv : null;
            if (recomposeScopeImpl != null) {
                destination$iv$iv.add(recomposeScopeImpl);
            }
        }
        List scopes = (List) destination$iv$iv;
        int index$iv = 0;
        int size = scopes.size();
        while (index$iv < size) {
            Object item$iv = scopes.get(index$iv);
            RecomposeScopeImpl scope = (RecomposeScopeImpl) item$iv;
            Anchor anchor2 = scope.getAnchor();
            if (anchor2 != null && (anchor = GapAnchorKt.asGapAnchor(anchor2)) != null) {
                boolean value$iv = slotTable.slotsOf$runtime(anchor.toIndexFor(slotTable)).contains(scope);
                if (!value$iv) {
                    int dataIndex = ArraysKt.indexOf((RecomposeScopeImpl[]) slotTable.slots, scope);
                    PreconditionsKt.throwIllegalStateException("Misaligned anchor " + anchor + " in scope " + scope + " encountered, scope found at " + dataIndex);
                }
            }
            index$iv++;
            slotTable = this;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.runtime.composer.gapbuffer.SlotTable$getSlots$1, reason: invalid class name */
    /* JADX INFO: compiled from: SlotTable.kt */
    @Metadata(d1 = {"\u0000\u0015\n\u0000\n\u0002\u0010\u001c\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010(\n\u0000*\u0001\u0000\b\n\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001J\u0011\u0010\u0003\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0004H\u0096\u0002¨\u0006\u0005"}, d2 = {"androidx/compose/runtime/composer/gapbuffer/SlotTable$getSlots$1", "", "", "iterator", "", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class AnonymousClass1 implements Iterable<Object>, KMappedMarker {
        AnonymousClass1() {
        }

        @Override // java.lang.Iterable
        public Iterator<Object> iterator() {
            return SequencesKt.iterator(new SlotTable$getSlots$1$iterator$1(SlotTable.this, null));
        }
    }

    @Override // androidx.compose.runtime.SlotStorage
    /* JADX INFO: renamed from: getSlots */
    public Iterable<Object> mo4584getSlots() {
        return new AnonymousClass1();
    }

    @Override // androidx.compose.runtime.SlotStorage
    public void collectCalledByInformation() {
        this.calledByMap = new MutableIntObjectMap<>(0, 1, null);
    }

    @Override // androidx.compose.runtime.SlotStorage
    public void collectSourceInformation() {
        this.sourceInformationMap = new HashMap<>();
    }

    @Override // androidx.compose.runtime.SlotStorage
    public void deactivateAll(RememberManager rememberManager) {
        SlotWriter writer$iv = openWriter();
        boolean normalClose$iv = false;
        try {
            GapComposerKt.deactivateCurrentGroup(writer$iv, rememberManager);
            Unit unit = Unit.INSTANCE;
            normalClose$iv = true;
        } finally {
            writer$iv.close(normalClose$iv);
        }
    }

    @Override // androidx.compose.runtime.SlotStorage
    public void dispose() {
    }

    @Override // androidx.compose.runtime.SlotStorage
    public ScatterMap<MovableContentStateReference, MovableContentState> extractNestedStates(Applier<?> applier, ObjectList<MovableContentStateReference> references) throws Throwable {
        boolean z;
        ObjectList<MovableContentStateReference> objectList = references;
        Object[] content$iv$iv$iv = objectList.content;
        int i$iv$iv$iv = 0;
        int i = objectList._size;
        while (true) {
            if (i$iv$iv$iv >= i) {
                z = true;
                break;
            }
            Object it$iv$iv = content$iv$iv$iv[i$iv$iv$iv];
            MovableContentStateReference it = (MovableContentStateReference) it$iv$iv;
            if (!ownsAnchor(GapAnchorKt.asGapAnchor(it.getAnchor()))) {
                z = false;
                break;
            }
            i$iv$iv$iv++;
        }
        if (!z) {
            MutableObjectList target$iv = new MutableObjectList(0, 1, null);
            Object[] content$iv$iv = objectList.content;
            int i2 = objectList._size;
            for (int i$iv$iv = 0; i$iv$iv < i2; i$iv$iv++) {
                Object it$iv = content$iv$iv[i$iv$iv];
                MovableContentStateReference it2 = (MovableContentStateReference) it$iv;
                if (ownsAnchor(GapAnchorKt.asGapAnchor(it2.getAnchor()))) {
                    target$iv.add(it$iv);
                }
            }
            objectList = target$iv;
        }
        ObjectList referencesToExtract = ExtensionsKt.sortedBy(objectList, new Function1() { // from class: androidx.compose.runtime.composer.gapbuffer.SlotTable$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Integer.valueOf(this.f$0.anchorIndex(GapAnchorKt.asGapAnchor(((MovableContentStateReference) obj).getAnchor())));
            }
        });
        if (referencesToExtract.isEmpty()) {
            return ScatterMapKt.emptyScatterMap();
        }
        MutableScatterMap result = ScatterMapKt.mutableScatterMapOf();
        SlotWriter writer$iv = openWriter();
        try {
            Object[] content$iv = referencesToExtract.content;
            int i$iv = 0;
            int i3 = referencesToExtract._size;
            while (i$iv < i3) {
                MovableContentStateReference reference = (MovableContentStateReference) content$iv[i$iv];
                int newGroup = writer$iv.anchorIndex(GapAnchorKt.asGapAnchor(reference.getAnchor()));
                int newParent = writer$iv.parent(newGroup);
                extractNestedStates$lambda$2$closeToGroupContaining(writer$iv, newParent);
                extractNestedStates$lambda$2$openParent(writer$iv, newParent);
                writer$iv.advanceBy(newGroup - writer$iv.getCurrentGroup());
                ObjectList referencesToExtract2 = referencesToExtract;
                try {
                    MovableContentState content = ComposerKt.extractMovableContentAtCurrent(reference.getComposition(), reference, writer$iv, applier);
                    result.set(reference, content);
                    i$iv++;
                    referencesToExtract = referencesToExtract2;
                } catch (Throwable th) {
                    th = th;
                    writer$iv.close(false);
                    throw th;
                }
            }
            extractNestedStates$lambda$2$closeToGroupContaining(writer$iv, Integer.MAX_VALUE);
            Unit unit = Unit.INSTANCE;
            writer$iv.close(true);
            return result;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private static final void extractNestedStates$lambda$2$closeToGroupContaining(SlotWriter $writer, int group) {
        while ($writer.getParent() >= 0 && $writer.getCurrentGroupEnd() <= group) {
            $writer.skipToGroupEnd();
            $writer.endGroup();
        }
    }

    private static final void extractNestedStates$lambda$2$openParent(SlotWriter $writer, int parent) {
        extractNestedStates$lambda$2$closeToGroupContaining($writer, parent);
        while ($writer.getCurrentGroup() != parent && !$writer.isGroupEnd()) {
            if (parent < SlotTableKt.getNextGroup($writer)) {
                $writer.startGroup();
            } else {
                $writer.skipGroup();
            }
        }
        boolean value$iv = $writer.getCurrentGroup() == parent;
        if (!value$iv) {
            ComposerKt.composeImmediateRuntimeError("Unexpected slot table structure");
        }
        $writer.startGroup();
    }

    @Override // androidx.compose.runtime.SlotStorage
    public void disposeUnusedMovableContent(RememberManager rememberManager, MovableContentState state) {
        SlotWriter writer$iv = openWriter();
        boolean normalClose$iv = false;
        try {
            ComposerKt.removeCurrentGroup(writer$iv, rememberManager);
            Unit unit = Unit.INSTANCE;
            normalClose$iv = true;
        } finally {
            writer$iv.close(normalClose$iv);
        }
    }

    @Override // androidx.compose.runtime.SlotStorage
    public void invalidateAll() {
        Object[] $this$fastForEach$iv = this.slots;
        for (Object it : $this$fastForEach$iv) {
            RecomposeScope recomposeScope = it instanceof RecomposeScope ? (RecomposeScope) it : null;
            if (recomposeScope != null) {
                recomposeScope.invalidate();
            }
        }
    }

    @Override // androidx.compose.runtime.SlotStorage
    public String toDebugString() {
        if (this.writer) {
            return super.toString();
        }
        StringBuilder $this$toDebugString_u24lambda_u240 = new StringBuilder();
        $this$toDebugString_u24lambda_u240.append(super.toString());
        $this$toDebugString_u24lambda_u240.append('\n');
        int groupsSize = this.groupsSize;
        if (groupsSize > 0) {
            int current = 0;
            while (current < groupsSize) {
                current += emitGroup($this$toDebugString_u24lambda_u240, current, 0);
            }
        } else {
            $this$toDebugString_u24lambda_u240.append("<EMPTY>");
        }
        return $this$toDebugString_u24lambda_u240.toString();
    }

    private final int emitGroup(StringBuilder $this$emitGroup, int index, int level) {
        String it;
        for (int i = 0; i < level; i++) {
            $this$emitGroup.append(' ');
        }
        $this$emitGroup.append("Group(");
        $this$emitGroup.append(index);
        $this$emitGroup.append(")");
        GapGroupSourceInformation gapGroupSourceInformationSourceInformationOf = sourceInformationOf(index);
        if (gapGroupSourceInformationSourceInformationOf != null && (it = gapGroupSourceInformationSourceInformationOf.getSourceInformation()) != null && (StringsKt.startsWith$default(it, "C(", false, 2, (Object) null) || StringsKt.startsWith$default(it, "CC(", false, 2, (Object) null))) {
            int start = StringsKt.indexOf$default((CharSequence) it, "(", 0, false, 6, (Object) null) + 1;
            int endParen = StringsKt.indexOf$default((CharSequence) it, ')', 0, false, 6, (Object) null);
            $this$emitGroup.append(" ");
            String strSubstring = it.substring(start, endParen);
            Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
            $this$emitGroup.append(strSubstring);
            $this$emitGroup.append("()");
        }
        $this$emitGroup.append(" key=");
        int[] $this$key$iv = this.groups;
        $this$emitGroup.append($this$key$iv[index * 5]);
        int groupSize = SlotTableKt.groupSize(this.groups, index);
        $this$emitGroup.append(", nodes=");
        int[] $this$nodeCount$iv = this.groups;
        $this$emitGroup.append($this$nodeCount$iv[(index * 5) + 1] & 67108863);
        $this$emitGroup.append(", size=");
        $this$emitGroup.append(groupSize);
        int[] $this$hasMark$iv = this.groups;
        if (($this$hasMark$iv[(index * 5) + 1] & GroupFlagsKt.HasRecompositionRequiredFlag) != 0) {
            $this$emitGroup.append(", mark");
        }
        int[] $this$containsMark$iv = this.groups;
        if (($this$containsMark$iv[(index * 5) + 1] & 67108864) != 0) {
            $this$emitGroup.append(", contains mark");
        }
        int dataStart = emitGroup$dataIndex(this, index);
        int dataEnd = emitGroup$dataIndex(this, index + 1);
        if ((dataStart >= 0 && dataStart <= dataEnd) && dataEnd <= this.slotsSize) {
            int[] $this$hasObjectKey$iv = this.groups;
            if (($this$hasObjectKey$iv[(index * 5) + 1] & GroupFlagsKt.HasMovableContentFlag) != 0) {
                $this$emitGroup.append(" objectKey=" + SlotTableKt.summarize(String.valueOf(this.slots[SlotTableKt.objectKeyIndex(this.groups, index)]), 10));
            }
            int[] $this$isNode$iv = this.groups;
            if (($this$isNode$iv[(index * 5) + 1] & 1073741824) != 0) {
                StringBuilder sbAppend = new StringBuilder().append(" node=");
                Object[] objArr = this.slots;
                int[] $this$nodeIndex$iv = this.groups;
                $this$emitGroup.append(sbAppend.append(SlotTableKt.summarize(String.valueOf(objArr[$this$nodeIndex$iv[(index * 5) + 4]]), 10)).toString());
            }
            int[] $this$hasAux$iv = this.groups;
            if (($this$hasAux$iv[(index * 5) + 1] & GroupFlagsKt.IsMovableContentFlag) != 0) {
                $this$emitGroup.append(" aux=" + SlotTableKt.summarize(String.valueOf(this.slots[SlotTableKt.auxIndex(this.groups, index)]), 10));
            }
            int slotStart = SlotTableKt.slotAnchor(this.groups, index);
            if (slotStart < dataEnd) {
                $this$emitGroup.append(", slots=[");
                $this$emitGroup.append(slotStart);
                $this$emitGroup.append(": ");
                for (int dataIndex = slotStart; dataIndex < dataEnd; dataIndex++) {
                    if (dataIndex != slotStart) {
                        $this$emitGroup.append(", ");
                    }
                    $this$emitGroup.append(SlotTableKt.summarize(String.valueOf(this.slots[dataIndex]), 10));
                }
                $this$emitGroup.append("]");
            }
        } else {
            $this$emitGroup.append(", *invalid data offsets " + dataStart + '-' + dataEnd + '*');
        }
        $this$emitGroup.append('\n');
        int current = index + 1;
        int end = index + groupSize;
        while (current < end) {
            current += emitGroup($this$emitGroup, current, level + 1);
        }
        return groupSize;
    }

    private static final int emitGroup$dataIndex(SlotTable this$0, int index) {
        if (index >= this$0.groupsSize) {
            return this$0.slotsSize;
        }
        int[] $this$dataAnchor$iv = this$0.groups;
        return $this$dataAnchor$iv[(index * 5) + 4];
    }

    private final List<Integer> keys() {
        return SlotTableKt.keys(this.groups, this.groupsSize * 5);
    }

    private final List<Integer> nodes() {
        return SlotTableKt.nodeCounts(this.groups, this.groupsSize * 5);
    }

    private final List<Integer> parentIndexes() {
        return SlotTableKt.parentAnchors(this.groups, this.groupsSize * 5);
    }

    private final List<Integer> dataIndexes() {
        return SlotTableKt.dataAnchors(this.groups, this.groupsSize * 5);
    }

    private final List<Integer> groupSizes() {
        return SlotTableKt.groupSizes(this.groups, this.groupsSize * 5);
    }

    public final List<Object> slotsOf$runtime(int group) {
        int end;
        int[] $this$dataAnchor$iv = this.groups;
        int start = $this$dataAnchor$iv[(group * 5) + 4];
        if (group + 1 >= this.groupsSize) {
            end = this.slots.length;
        } else {
            int[] $this$dataAnchor$iv2 = this.groups;
            int address$iv = group + 1;
            end = $this$dataAnchor$iv2[(address$iv * 5) + 4];
        }
        return ArraysKt.toList(this.slots).subList(start, end);
    }

    public final Object slot$runtime(int group, int slotIndex) {
        int end;
        int start = SlotTableKt.slotAnchor(this.groups, group);
        if (group + 1 >= this.groupsSize) {
            end = this.slots.length;
        } else {
            int[] $this$dataAnchor$iv = this.groups;
            int address$iv = group + 1;
            end = $this$dataAnchor$iv[(address$iv * 5) + 4];
        }
        int len = end - start;
        boolean z = false;
        if (slotIndex >= 0 && slotIndex < len) {
            z = true;
        }
        return z ? this.slots[start + slotIndex] : Composer.INSTANCE.getEmpty();
    }

    @Override // androidx.compose.runtime.tooling.CompositionData
    public Iterable<CompositionGroup> getCompositionGroups() {
        return this;
    }

    @Override // java.lang.Iterable
    public Iterator<CompositionGroup> iterator() {
        return new GroupIterator(this, 0, this.groupsSize);
    }

    @Override // androidx.compose.runtime.tooling.CompositionData
    public CompositionGroup find(Object identityToFind) {
        return new SlotTableGroup(this, 0, 0, 4, null).find(identityToFind);
    }
}
