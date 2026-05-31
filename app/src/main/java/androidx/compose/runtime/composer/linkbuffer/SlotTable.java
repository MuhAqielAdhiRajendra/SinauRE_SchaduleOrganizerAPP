package androidx.compose.runtime.composer.linkbuffer;

import androidx.autofill.HintConstants;
import androidx.collection.IntSetKt;
import androidx.collection.MutableIntSet;
import androidx.collection.MutableScatterMap;
import androidx.collection.ObjectList;
import androidx.collection.ScatterMap;
import androidx.collection.ScatterMapKt;
import androidx.compose.runtime.Anchor;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.IntStack;
import androidx.compose.runtime.MovableContentState;
import androidx.compose.runtime.MovableContentStateReference;
import androidx.compose.runtime.RecomposeScope;
import androidx.compose.runtime.RecomposeScopeImpl;
import androidx.compose.runtime.SlotStorage;
import androidx.compose.runtime.composer.RememberManager;
import androidx.compose.runtime.tooling.CompositionData;
import androidx.compose.runtime.tooling.CompositionGroup;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.UInt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.sequences.SequencesKt;
import kotlin.text.CharsKt;

/* JADX INFO: compiled from: SlotTable.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000â\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010(\n\u0002\b\u0015\n\u0002\u0010\u0015\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\b\b\u0001\u0018\u0000 §\u00012\u00020\u00012\u00020\u00022\b\u0012\u0004\u0012\u00020\u00040\u0003:\u0006¥\u0001¦\u0001§\u0001B/\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\f\u0010\rJ\n\u0010\u001d\u001a\u00060\u001ej\u0002`\u001fJ\b\u0010%\u001a\u00020&H\u0016J\"\u0010'\u001a\u00020\u00002\u0017\u0010(\u001a\u0013\u0012\u0004\u0012\u00020*\u0012\u0004\u0012\u00020&0)¢\u0006\u0002\b+H\u0086\bJ-\u0010,\u001a\u0002H-\"\u0004\b\u0000\u0010-2\u0017\u0010(\u001a\u0013\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u0002H-0)¢\u0006\u0002\b+H\u0086\b¢\u0006\u0002\u0010.J-\u0010/\u001a\u0002H-\"\u0004\b\u0000\u0010-2\u0017\u0010(\u001a\u0013\u0012\u0004\u0012\u000200\u0012\u0004\u0012\u0002H-0)¢\u0006\u0002\b+H\u0086\b¢\u0006\u0002\u0010.J\u0006\u00101\u001a\u000200J\u000e\u00102\u001a\u00020&2\u0006\u00103\u001a\u000200J\u0006\u00104\u001a\u00020\u001bJ\u000e\u00105\u001a\u00020&2\u0006\u00106\u001a\u00020\u001bJ\u0015\u00107\u001a\u00020\n2\n\u00108\u001a\u00060\u0006j\u0002`9H\u0086\u0002J\u0011\u00107\u001a\u00020\n2\u0006\u0010:\u001a\u00020;H\u0086\u0002J\u0012\u0010<\u001a\u00020\n2\n\u0010=\u001a\u00060\u0006j\u0002`>J\u0012\u0010?\u001a\u00020\n2\n\u00108\u001a\u00060\u0006j\u0002`9J\u0016\u0010@\u001a\u00020\n2\u0006\u0010A\u001a\u00020;2\u0006\u0010B\u001a\u00020;J\u0010\u0010C\u001a\u00020&2\u0006\u0010D\u001a\u00020EH\u0016J\u0010\u0010F\u001a\u00020&2\u0006\u0010D\u001a\u00020EH\u0016J.\u0010G\u001a\u000e\u0012\u0004\u0012\u00020I\u0012\u0004\u0012\u00020J0H2\n\u0010K\u001a\u0006\u0012\u0002\b\u00030L2\f\u0010M\u001a\b\u0012\u0004\u0012\u00020I0NH\u0016J\u0012\u0010O\u001a\u0004\u0018\u00010\u00042\u0006\u0010P\u001a\u00020QH\u0016J\u0018\u0010R\u001a\u00020\n2\u0006\u00108\u001a\u00020\u00062\u0006\u0010:\u001a\u00020SH\u0016J\u0018\u0010@\u001a\u00020\n2\u0006\u0010T\u001a\u00020S2\u0006\u0010U\u001a\u00020SH\u0016J\u0018\u0010V\u001a\u00020&2\u0006\u0010D\u001a\u00020E2\u0006\u0010W\u001a\u00020JH\u0016J\b\u0010X\u001a\u00020&H\u0016J\u0018\u0010Y\u001a\n\u0012\u0004\u0012\u00020[\u0018\u00010Z2\u0006\u0010\\\u001a\u00020\u0006H\u0016J\u0010\u0010]\u001a\u00020\n2\u0006\u0010^\u001a\u00020[H\u0016J\u0016\u0010_\u001a\u0004\u0018\u00010[2\n\u00108\u001a\u00060\u0006j\u0002`9H\u0002J\u001b\u0010`\u001a\u0004\u0018\u00010[2\n\u00108\u001a\u00060\u0006j\u0002`9H\u0000¢\u0006\u0002\baJA\u0010b\u001a\u00020&2\n\u00108\u001a\u00060\u0006j\u0002`92%\u0010c\u001a!\u0012\u0017\u0012\u00150\u0006j\u0002`9¢\u0006\f\bd\u0012\b\be\u0012\u0004\b\b(8\u0012\u0004\u0012\u00020&0)H\u0080\b¢\u0006\u0002\bfJA\u0010g\u001a\u00020&2\n\u00108\u001a\u00060\u0006j\u0002`92%\u0010c\u001a!\u0012\u0017\u0012\u00150\u0006j\u0002`9¢\u0006\f\bd\u0012\b\be\u0012\u0004\b\b(8\u0012\u0004\u0012\u00020&0)H\u0080\b¢\u0006\u0002\bhJA\u0010i\u001a\u00020&2\n\u00108\u001a\u00060\u0006j\u0002`92%\u0010c\u001a!\u0012\u0017\u0012\u00150\u0006j\u0002`9¢\u0006\f\bd\u0012\b\be\u0012\u0004\b\b(8\u0012\u0004\u0012\u00020&0)H\u0080\b¢\u0006\u0002\bjJK\u0010k\u001a\u00020&2\n\u00108\u001a\u00060\u0006j\u0002`92\b\b\u0002\u0010l\u001a\u00020\n2%\u0010c\u001a!\u0012\u0017\u0012\u00150\u0006j\u0002`9¢\u0006\f\bd\u0012\b\be\u0012\u0004\b\b(8\u0012\u0004\u0012\u00020&0)H\u0080\b¢\u0006\u0002\bmJ5\u0010n\u001a\u00020&2%\u0010c\u001a!\u0012\u0017\u0012\u00150\u0006j\u0002`9¢\u0006\f\bd\u0012\b\be\u0012\u0004\b\b(8\u0012\u0004\u0012\u00020&0)H\u0080\b¢\u0006\u0002\boJ%\u0010p\u001a\u00020\n2\n\u0010U\u001a\u00060\u0006j\u0002`92\n\u0010T\u001a\u00060\u0006j\u0002`9H\u0000¢\u0006\u0002\bqJ3\u0010r\u001a\u00020&2#\u0010s\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010Q¢\u0006\f\bd\u0012\b\be\u0012\u0004\b\b(t\u0012\u0004\u0012\u00020&0)H\u0080\b¢\u0006\u0002\buJT\u0010v\u001a\u00020&2\n\u00108\u001a\u00060\u0006j\u0002`928\u0010s\u001a4\u0012\u0015\u0012\u0013\u0018\u00010Q¢\u0006\f\bd\u0012\b\be\u0012\u0004\b\b(t\u0012\u0013\u0012\u00110\u0006¢\u0006\f\bd\u0012\b\be\u0012\u0004\b\b(x\u0012\u0004\u0012\u00020&0wH\u0080\b¢\u0006\u0002\byJ\u0006\u0010z\u001a\u00020\u0000J\b\u0010{\u001a\u00020&H\u0016J\b\u0010|\u001a\u00020&H\u0016J\b\u0010}\u001a\u00020~H\u0016J\u000e\u0010}\u001a\u00020~2\u0006\u0010\u007f\u001a\u00020\nJ\t\u0010\u0080\u0001\u001a\u00020&H\u0016J\u0011\u0010\u0085\u0001\u001a\t\u0012\u0004\u0012\u00020\u00040\u0086\u0001H\u0096\u0002J\u001b\u0010\u0087\u0001\u001a\u00020\u00062\n\u00108\u001a\u00060\u0006j\u0002`9H\u0000¢\u0006\u0003\b\u0088\u0001J\u001b\u0010\u0089\u0001\u001a\u00020\u00062\n\u00108\u001a\u00060\u0006j\u0002`9H\u0000¢\u0006\u0003\b\u008a\u0001J\u001b\u0010\u008b\u0001\u001a\u00020\n2\n\u00108\u001a\u00060\u0006j\u0002`9H\u0000¢\u0006\u0003\b\u008c\u0001J%\u0010\u008d\u0001\u001a\u0004\u0018\u00010Q2\n\u00108\u001a\u00060\u0006j\u0002`92\u0006\u0010x\u001a\u00020\u0006H\u0000¢\u0006\u0003\b\u008e\u0001J\u001d\u0010\u008f\u0001\u001a\u0004\u0018\u00010Q2\n\u00108\u001a\u00060\u0006j\u0002`9H\u0000¢\u0006\u0003\b\u0090\u0001J\u001d\u0010\u0091\u0001\u001a\u0004\u0018\u00010Q2\n\u00108\u001a\u00060\u0006j\u0002`9H\u0000¢\u0006\u0003\b\u0092\u0001J\u001d\u0010\u0093\u0001\u001a\u0004\u0018\u00010Q2\n\u00108\u001a\u00060\u0006j\u0002`9H\u0000¢\u0006\u0003\b\u0094\u0001J\u001b\u0010\u0095\u0001\u001a\u00020\u00062\n\u00108\u001a\u00060\u0006j\u0002`9H\u0000¢\u0006\u0003\b\u0096\u0001J\u001b\u0010\u0097\u0001\u001a\u00020\u00062\n\u00108\u001a\u00060\u0006j\u0002`9H\u0000¢\u0006\u0003\b\u0098\u0001J\u001b\u0010\u0099\u0001\u001a\u00020\u00062\n\u00108\u001a\u00060\u0006j\u0002`9H\u0000¢\u0006\u0003\b\u009a\u0001J\u0011\u0010¡\u0001\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010Q0\u0003H\u0016J\u000f\u0010£\u0001\u001a\u00020QH\u0000¢\u0006\u0003\b¤\u0001R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\u000b\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0015\"\u0004\b\u0019\u0010\u0017R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010!\u001a\u00020\u00062\u0006\u0010 \u001a\u00020\u0006@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u000fR\u0011\u0010#\u001a\u00020\n8F¢\u0006\u0006\u001a\u0004\b$\u0010\u0015R\u001d\u0010\u0081\u0001\u001a\b\u0012\u0004\u0012\u00020\u00040\u00038VX\u0096\u0004¢\u0006\b\u001a\u0006\b\u0082\u0001\u0010\u0083\u0001R\u0016\u0010\u0084\u0001\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0007\u001a\u0005\b\u0084\u0001\u0010\u0015R\u0018\u0010\u009b\u0001\u001a\u00030\u009c\u00018BX\u0082\u0004¢\u0006\b\u001a\u0006\b\u009d\u0001\u0010\u009e\u0001R \u0010\u009f\u0001\u001a\u000b\u0012\u0006\u0012\u0004\u0018\u00010Q0 \u00018BX\u0082\u0004¢\u0006\b\u001a\u0006\b¡\u0001\u0010¢\u0001¨\u0006¨\u0001"}, d2 = {"Landroidx/compose/runtime/composer/linkbuffer/SlotTable;", "Landroidx/compose/runtime/SlotStorage;", "Landroidx/compose/runtime/tooling/CompositionData;", "", "Landroidx/compose/runtime/tooling/CompositionGroup;", "root", "", "addressSpace", "Landroidx/compose/runtime/composer/linkbuffer/SlotTableAddressSpace;", "recordSourceInformation", "", "recordCallByInformation", "<init>", "(ILandroidx/compose/runtime/composer/linkbuffer/SlotTableAddressSpace;ZZ)V", "getRoot", "()I", "setRoot", "(I)V", "getAddressSpace", "()Landroidx/compose/runtime/composer/linkbuffer/SlotTableAddressSpace;", "getRecordSourceInformation", "()Z", "setRecordSourceInformation", "(Z)V", "getRecordCallByInformation", "setRecordCallByInformation", "currentEditor", "Landroidx/compose/runtime/composer/linkbuffer/SlotTableEditor;", "openReaders", "rootHandle", "", "Landroidx/compose/runtime/composer/linkbuffer/GroupHandle;", "value", "version", "getVersion", "hasEditor", "getHasEditor", "dispose", "", "buildSubTable", "block", "Lkotlin/Function1;", "Landroidx/compose/runtime/composer/linkbuffer/SlotTableBuilder;", "Lkotlin/ExtensionFunctionType;", "edit", "T", "(Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "read", "Landroidx/compose/runtime/composer/linkbuffer/SlotTableReader;", "openReader", "closeReader", "reader", "openEditor", "closeEditor", "editor", "contains", "group", "Landroidx/compose/runtime/composer/linkbuffer/GroupAddress;", "anchor", "Landroidx/compose/runtime/composer/linkbuffer/LinkAnchor;", "containsFlags", "flags", "Landroidx/compose/runtime/composer/linkbuffer/GroupFlags;", "hasRecomposeScopes", "inGroup", "groupAnchor", "childAnchor", "clear", "rememberManager", "Landroidx/compose/runtime/composer/RememberManager;", "deactivateAll", "extractNestedStates", "Landroidx/collection/ScatterMap;", "Landroidx/compose/runtime/MovableContentStateReference;", "Landroidx/compose/runtime/MovableContentState;", "applier", "Landroidx/compose/runtime/Applier;", "references", "Landroidx/collection/ObjectList;", "find", "identityToFind", "", "groupContainsAnchor", "Landroidx/compose/runtime/Anchor;", "parent", "child", "disposeUnusedMovableContent", "state", "invalidateAll", "invalidateGroupsWithKey", "", "Landroidx/compose/runtime/RecomposeScopeImpl;", TypedValues.AttributesType.S_TARGET, "ownsRecomposeScope", "scope", "findEffectiveRecomposeScope", "getRecomposeScopeOrNull", "getRecomposeScopeOrNull$runtime", "traverseSiblings", "visit", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "traverseSiblings$runtime", "traverseGroupAndParents", "traverseGroupAndParents$runtime", "traverseChildren", "traverseChildren$runtime", "traverseGroup", "includeSiblingsOfStartGroup", "traverseGroup$runtime", "traverseTable", "traverseTable$runtime", "isGroupAChildOf", "isGroupAChildOf$runtime", "forEachSlot", "action", "slot", "forEachSlot$runtime", "forEachGroupSlot", "Lkotlin/Function2;", "index", "forEachGroupSlot$runtime", "newTableInSameAddressSpace", "collectSourceInformation", "collectCalledByInformation", "toDebugString", "", "includeSlots", "verifyWellFormed", "compositionGroups", "getCompositionGroups", "()Ljava/lang/Iterable;", "isEmpty", "iterator", "", "nextSiblingOf", "nextSiblingOf$runtime", "firstChildOf", "firstChildOf$runtime", "groupHasAux", "groupHasAux$runtime", "groupSlotAtIndex", "groupSlotAtIndex$runtime", "groupObjectKey", "groupObjectKey$runtime", "groupAux", "groupAux$runtime", "groupNode", "groupNode$runtime", "groupKeyOf", "groupKeyOf$runtime", "groupSlotRange", "groupSlotRange$runtime", "groupFlags", "groupFlags$runtime", "groups", "", "getGroups", "()[I", "slots", "", "getSlots", "()[Ljava/lang/Object;", "toDebugTree", "toDebugTree$runtime", "DebugGroup", "DebugSlotRange", "Companion", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class SlotTable extends SlotStorage implements CompositionData, Iterable<CompositionGroup>, KMappedMarker {
    private final SlotTableAddressSpace addressSpace;
    private SlotTableEditor currentEditor;
    private int openReaders;
    private boolean recordCallByInformation;
    private boolean recordSourceInformation;
    private int root;
    private int version;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    public SlotTable() {
        this(0, null, false, false, 15, null);
    }

    public SlotTable(int root, SlotTableAddressSpace addressSpace, boolean recordSourceInformation, boolean recordCallByInformation) {
        this.root = root;
        this.addressSpace = addressSpace;
        this.recordSourceInformation = recordSourceInformation;
        this.recordCallByInformation = recordCallByInformation;
    }

    public /* synthetic */ SlotTable(int i, SlotTableAddressSpace slotTableAddressSpace, boolean z, boolean z2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? -1 : i, (i2 & 2) != 0 ? new SlotTableAddressSpace() : slotTableAddressSpace, (i2 & 4) != 0 ? false : z, (i2 & 8) != 0 ? false : z2);
    }

    public final int getRoot() {
        return this.root;
    }

    public final void setRoot(int i) {
        this.root = i;
    }

    public final SlotTableAddressSpace getAddressSpace() {
        return this.addressSpace;
    }

    public final boolean getRecordSourceInformation() {
        return this.recordSourceInformation;
    }

    public final void setRecordSourceInformation(boolean z) {
        this.recordSourceInformation = z;
    }

    public final boolean getRecordCallByInformation() {
        return this.recordCallByInformation;
    }

    public final void setRecordCallByInformation(boolean z) {
        this.recordCallByInformation = z;
    }

    public final long rootHandle() {
        int group$iv = this.root;
        return (((long) (-1)) << 32) | (((long) UInt.m9024constructorimpl(group$iv)) & 4294967295L);
    }

    public final int getVersion() {
        return this.version;
    }

    public final boolean getHasEditor() {
        return this.currentEditor != null;
    }

    @Override // androidx.compose.runtime.SlotStorage
    public void dispose() {
        if (this.root != -1) {
            this.addressSpace.freeGroupTree(this.root);
            this.root = -1;
        }
    }

    public final SlotTable buildSubTable(Function1<? super SlotTableBuilder, Unit> block) {
        Companion companion = INSTANCE;
        SlotTableAddressSpace addressSpace$iv = getAddressSpace();
        SlotTableBuilder builder$iv = new SlotTableBuilder(addressSpace$iv, false, false);
        builder$iv.buildStart();
        block.invoke(builder$iv);
        return builder$iv.build();
    }

    public final <T> T edit(Function1<? super SlotTableEditor, ? extends T> block) {
        SlotTableEditor $this$edit_u24lambda_u240 = openEditor();
        try {
            return block.invoke($this$edit_u24lambda_u240);
        } finally {
            $this$edit_u24lambda_u240.close();
        }
    }

    public final <T> T read(Function1<? super SlotTableReader, ? extends T> block) {
        SlotTableReader $this$read_u24lambda_u240 = openReader();
        try {
            return block.invoke($this$read_u24lambda_u240);
        } finally {
            $this$read_u24lambda_u240.close();
        }
    }

    public final SlotTableReader openReader() {
        boolean value$iv = !getHasEditor();
        if (!value$iv) {
            ComposerKt.composeImmediateRuntimeError("Cannot read while a writer is pending");
        }
        this.openReaders++;
        return new SlotTableReader(this);
    }

    public final void closeReader(SlotTableReader reader) {
        boolean value$iv = reader.getTable() == this && this.openReaders > 0;
        if (!value$iv) {
            ComposerKt.composeImmediateRuntimeError("Unexpected reader close()");
        }
        this.openReaders--;
    }

    public final SlotTableEditor openEditor() {
        boolean value$iv = !getHasEditor();
        if (!value$iv) {
            ComposerKt.composeImmediateRuntimeError("Cannot start a writer when another writer is pending");
        }
        boolean value$iv2 = this.openReaders <= 0;
        if (!value$iv2) {
            ComposerKt.composeImmediateRuntimeError("Cannot start a writer when a reader is pending");
        }
        this.version++;
        SlotTableEditor editor = new SlotTableEditor(this);
        this.currentEditor = editor;
        return editor;
    }

    public final void closeEditor(SlotTableEditor editor) {
        boolean value$iv = this.currentEditor == editor;
        if (!value$iv) {
            ComposerKt.composeImmediateRuntimeError("Attempted to close an editor that was not the current editor");
        }
        this.currentEditor = null;
    }

    public final boolean contains(int group) {
        if (group < 0 || !this.addressSpace.contains(group)) {
            return false;
        }
        SlotTableAddressSpace this_$iv = this.addressSpace;
        int[] groups$iv$iv = this_$iv.getGroups();
        int current$iv$iv = groups$iv$iv[group + 2];
        while (true) {
            if (current$iv$iv > 0) {
                int parent = current$iv$iv;
                if (parent == this.root) {
                    return true;
                }
                int address$iv$iv$iv = current$iv$iv;
                current$iv$iv = groups$iv$iv[address$iv$iv$iv + 2];
            } else {
                boolean value$iv$iv$iv = current$iv$iv != 0;
                if (value$iv$iv$iv) {
                    return false;
                }
                ComposerKt.composeImmediateRuntimeError("Traversing parent of group not in the slot table: " + group);
                return false;
            }
        }
    }

    public final boolean contains(LinkAnchor anchor) {
        return anchor.getValid() && this.addressSpace.ownsAnchor(anchor) && contains(anchor.getAddress());
    }

    public final boolean containsFlags(int flags) {
        if (isEmpty()) {
            return false;
        }
        int[] $this$groupFlags$iv = this.addressSpace.getGroups();
        int address$iv = this.root;
        int $this$contains$iv = $this$groupFlags$iv[address$iv + 4];
        return (flags & $this$contains$iv) == flags;
    }

    public final boolean hasRecomposeScopes(int group) {
        int address$iv$iv$iv;
        int[] groups = this.addressSpace.getGroups();
        Object[] slots = this.addressSpace.getSlots();
        SlotTableAddressSpace this_$iv$iv = getAddressSpace();
        if (group < 0) {
            return false;
        }
        IntStack toVisit$iv$iv = new IntStack();
        int group$iv$iv = group;
        int[] groups$iv$iv = this_$iv$iv.getGroups();
        while (true) {
            int child = group$iv$iv;
            RecomposeScopeImpl recomposeScope = SlotTableKt.recomposeScopeOrNullInRegion(slots, groups[child + 5]);
            if (recomposeScope != null) {
                return true;
            }
            if (group$iv$iv != group && (address$iv$iv$iv = groups$iv$iv[group$iv$iv + 1]) >= 0) {
                toVisit$iv$iv.push(address$iv$iv$iv);
            }
            int nextSibling$iv$iv = group$iv$iv;
            int address$iv$iv$iv2 = groups$iv$iv[nextSibling$iv$iv + 3];
            if (address$iv$iv$iv2 >= 0) {
                group$iv$iv = address$iv$iv$iv2;
            } else {
                if (toVisit$iv$iv.tos == 0) {
                    return false;
                }
                group$iv$iv = toVisit$iv$iv.pop();
            }
        }
    }

    public final boolean inGroup(LinkAnchor groupAnchor, LinkAnchor childAnchor) {
        boolean z = false;
        if (!groupAnchor.getValid() || !childAnchor.getValid()) {
            return false;
        }
        if (Intrinsics.areEqual(groupAnchor, childAnchor)) {
            return true;
        }
        SlotTableAddressSpace addressSpace = this.addressSpace;
        if (!addressSpace.ownsAnchor(childAnchor) || !addressSpace.ownsAnchor(groupAnchor)) {
            return false;
        }
        int group = groupAnchor.getAddress();
        int childGroup = childAnchor.getAddress();
        if (!addressSpace.contains(group) || !addressSpace.contains(childGroup)) {
            return false;
        }
        int[] groups$iv$iv = addressSpace.getGroups();
        int current$iv$iv = groups$iv$iv[childGroup + 2];
        while (current$iv$iv > 0) {
            int parent = current$iv$iv;
            boolean z2 = z;
            if (parent == group) {
                return true;
            }
            if (group <= 0) {
                return z2;
            }
            int address$iv$iv$iv = current$iv$iv;
            current$iv$iv = groups$iv$iv[address$iv$iv$iv + 2];
            z = z2;
        }
        boolean z3 = z;
        boolean value$iv$iv$iv = current$iv$iv == 0 ? z3 : true;
        if (!value$iv$iv$iv) {
            ComposerKt.composeImmediateRuntimeError("Traversing parent of group not in the slot table: " + childGroup);
        }
        return z3;
    }

    @Override // androidx.compose.runtime.SlotStorage
    public void clear(RememberManager rememberManager) {
        SlotTableEditor $this$edit_u24lambda_u240$iv = openEditor();
        try {
            SlotTableKt.removeCurrentGroup($this$edit_u24lambda_u240$iv, rememberManager);
            Unit unit = Unit.INSTANCE;
        } finally {
            $this$edit_u24lambda_u240$iv.close();
        }
    }

    @Override // androidx.compose.runtime.SlotStorage
    public void deactivateAll(RememberManager rememberManager) {
        SlotTableEditor $this$edit_u24lambda_u240$iv = openEditor();
        try {
            SlotTableKt.deactivateCurrentGroup($this$edit_u24lambda_u240$iv, rememberManager);
            Unit unit = Unit.INSTANCE;
        } finally {
            $this$edit_u24lambda_u240$iv.close();
        }
    }

    @Override // androidx.compose.runtime.SlotStorage
    public ScatterMap<MovableContentStateReference, MovableContentState> extractNestedStates(Applier<?> applier, ObjectList<MovableContentStateReference> references) throws Throwable {
        SlotTable this_$iv;
        MutableScatterMap result = ScatterMapKt.mutableScatterMapOf();
        SlotTable this_$iv2 = this;
        SlotTableEditor $this$edit_u24lambda_u240$iv = this_$iv2.openEditor();
        try {
            Object[] content$iv = references.content;
            int i$iv = 0;
            int i = references._size;
            while (i$iv < i) {
                MovableContentStateReference reference = (MovableContentStateReference) content$iv[i$iv];
                LinkAnchor anchor = LinkAnchorKt.asLinkAnchor(reference.getAnchor());
                if (!$this$edit_u24lambda_u240$iv.getTable().contains(anchor)) {
                    this_$iv = this_$iv2;
                } else {
                    $this$edit_u24lambda_u240$iv.seek(anchor);
                    this_$iv = this_$iv2;
                    try {
                        result.set(reference, SlotTableKt.extractMovableContentAtCurrent(reference.getComposition(), reference, $this$edit_u24lambda_u240$iv, applier));
                    } catch (Throwable th) {
                        th = th;
                        $this$edit_u24lambda_u240$iv.close();
                        throw th;
                    }
                }
                i$iv++;
                this_$iv2 = this_$iv;
            }
            Unit unit = Unit.INSTANCE;
            $this$edit_u24lambda_u240$iv.close();
            return result;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    @Override // androidx.compose.runtime.tooling.CompositionData
    public CompositionGroup find(Object identityToFind) {
        return new SlotTableGroup(this, this.root, this.version).find(identityToFind);
    }

    @Override // androidx.compose.runtime.SlotStorage
    public boolean groupContainsAnchor(int group, Anchor anchor) {
        LinkAnchor anchor2 = LinkAnchorKt.asLinkAnchor(anchor);
        return this.addressSpace.ownsAnchor(anchor2) && isGroupAChildOf$runtime(anchor2.getAddress(), group);
    }

    @Override // androidx.compose.runtime.SlotStorage
    public boolean inGroup(Anchor parent, Anchor child) {
        return inGroup(LinkAnchorKt.asLinkAnchor(parent), LinkAnchorKt.asLinkAnchor(child));
    }

    @Override // androidx.compose.runtime.SlotStorage
    public void disposeUnusedMovableContent(RememberManager rememberManager, MovableContentState state) {
        SlotTableEditor $this$edit_u24lambda_u240$iv = openEditor();
        try {
            SlotTableKt.removeCurrentGroup($this$edit_u24lambda_u240$iv, rememberManager);
            Unit unit = Unit.INSTANCE;
        } finally {
            $this$edit_u24lambda_u240$iv.close();
        }
    }

    @Override // androidx.compose.runtime.SlotStorage
    public void invalidateAll() {
        int $i$f$forEachSlot$runtime;
        int address$iv$iv$iv;
        int size$iv$iv$iv;
        SlotTable this_$iv = this;
        int $i$f$forEachSlot$runtime2 = 0;
        boolean z = true;
        if (!(!this_$iv.getHasEditor())) {
            ComposerKt.composeImmediateRuntimeError("Cannot read while an editor is pending");
        }
        SlotTableAddressSpace this_$iv$iv$iv = this_$iv.getAddressSpace();
        int start$iv$iv$iv = this_$iv.getRoot();
        if (start$iv$iv$iv < 0) {
            return;
        }
        IntStack toVisit$iv$iv$iv = new IntStack();
        int group$iv$iv$iv = start$iv$iv$iv;
        int[] groups$iv$iv$iv = this_$iv$iv$iv.getGroups();
        while (true) {
            int group$iv = group$iv$iv$iv;
            SlotTable this_$iv$iv = this_$iv;
            int[] $this$groupSlotRange$iv$iv$iv = this_$iv$iv.getGroups();
            int i = $this$groupSlotRange$iv$iv$iv[group$iv + 5];
            boolean z2 = z;
            SlotTable this_$iv2 = this_$iv;
            if (i != -1) {
                SlotTableAddressSpace this_$iv$iv$iv2 = this_$iv$iv.getAddressSpace();
                int smallSize$iv$iv$iv = (i & 15) + 1;
                int slotRange$iv$iv$iv$iv = i >> 4;
                $i$f$forEachSlot$runtime = $i$f$forEachSlot$runtime2;
                if (smallSize$iv$iv$iv > 15 ? z2 : false) {
                    address$iv$iv$iv = slotRange$iv$iv$iv$iv;
                    size$iv$iv$iv = this_$iv$iv$iv2.getLargeSizes().get(address$iv$iv$iv);
                } else {
                    address$iv$iv$iv = slotRange$iv$iv$iv$iv;
                    size$iv$iv$iv = smallSize$iv$iv$iv;
                }
                int address$iv$iv = address$iv$iv$iv;
                int size$iv$iv = size$iv$iv$iv;
                int size$iv$iv$iv2 = 0;
                while (true) {
                    int address$iv$iv$iv2 = address$iv$iv$iv;
                    int address$iv$iv$iv3 = size$iv$iv;
                    if (size$iv$iv$iv2 >= address$iv$iv$iv3) {
                        break;
                    }
                    int index$iv$iv = size$iv$iv$iv2;
                    Object value$iv$iv = this_$iv$iv.getSlots()[address$iv$iv + size$iv$iv$iv2];
                    if (Intrinsics.areEqual(value$iv$iv, Composer.INSTANCE.getEmpty())) {
                        break;
                    }
                    RecomposeScope recomposeScope = value$iv$iv instanceof RecomposeScope ? (RecomposeScope) value$iv$iv : null;
                    if (recomposeScope != null) {
                        recomposeScope.invalidate();
                    }
                    size$iv$iv$iv2 = index$iv$iv + 1;
                    address$iv$iv$iv = address$iv$iv$iv2;
                    size$iv$iv = address$iv$iv$iv3;
                }
            } else {
                $i$f$forEachSlot$runtime = $i$f$forEachSlot$runtime2;
            }
            int address$iv$iv$iv$iv = groups$iv$iv$iv[group$iv$iv$iv + 1];
            if (address$iv$iv$iv$iv >= 0) {
                toVisit$iv$iv$iv.push(address$iv$iv$iv$iv);
            }
            int nextSibling$iv$iv$iv = group$iv$iv$iv;
            int address$iv$iv$iv$iv2 = groups$iv$iv$iv[nextSibling$iv$iv$iv + 3];
            if (address$iv$iv$iv$iv2 >= 0) {
                group$iv$iv$iv = address$iv$iv$iv$iv2;
                z = z2;
                this_$iv = this_$iv2;
                $i$f$forEachSlot$runtime2 = $i$f$forEachSlot$runtime;
            } else {
                if (toVisit$iv$iv$iv.tos == 0 ? z2 : false) {
                    return;
                }
                group$iv$iv$iv = toVisit$iv$iv$iv.pop();
                z = z2;
                this_$iv = this_$iv2;
                $i$f$forEachSlot$runtime2 = $i$f$forEachSlot$runtime;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x0120 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:103:0x0112 A[SYNTHETIC] */
    @Override // androidx.compose.runtime.SlotStorage
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.util.List<androidx.compose.runtime.RecomposeScopeImpl> invalidateGroupsWithKey(int r42) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 569
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.composer.linkbuffer.SlotTable.invalidateGroupsWithKey(int):java.util.List");
    }

    @Override // androidx.compose.runtime.SlotStorage
    public boolean ownsRecomposeScope(RecomposeScopeImpl scope) {
        Anchor it = scope.getAnchor();
        if (it == null) {
            return false;
        }
        LinkAnchor anchor = LinkAnchorKt.asLinkAnchor(it);
        return this.addressSpace.ownsAnchor(anchor) && isGroupAChildOf$runtime(anchor.getAddress(), this.root);
    }

    private final RecomposeScopeImpl findEffectiveRecomposeScope(int group) {
        SlotTableAddressSpace $this$iv$iv = getAddressSpace();
        int[] groups$iv$iv = $this$iv$iv.getGroups();
        int current$iv$iv = group;
        while (true) {
            if (current$iv$iv > 0) {
                int parent = current$iv$iv;
                RecomposeScopeImpl recomposeScope = getRecomposeScopeOrNull$runtime(parent);
                if (recomposeScope != null) {
                    if (recomposeScope.getUsed() && parent != group) {
                        return recomposeScope;
                    }
                    recomposeScope.setForcedRecompose(true);
                }
                int address$iv$iv$iv = current$iv$iv;
                current$iv$iv = groups$iv$iv[address$iv$iv$iv + 2];
            } else {
                boolean value$iv$iv$iv = current$iv$iv != 0;
                if (value$iv$iv$iv) {
                    return null;
                }
                ComposerKt.composeImmediateRuntimeError("Traversing parent of group not in the slot table: " + group);
                return null;
            }
        }
    }

    public final RecomposeScopeImpl getRecomposeScopeOrNull$runtime(int group) {
        SlotTableAddressSpace addressSpace = this.addressSpace;
        int[] groups = addressSpace.getGroups();
        Object[] slots = addressSpace.getSlots();
        int address$iv = groups[group + 5];
        if (address$iv == -1) {
            return null;
        }
        int smallSize$iv = (address$iv & 15) + 1;
        int address$iv2 = address$iv >> 4;
        int size$iv = smallSize$iv > 15 ? addressSpace.getLargeSizes().get(address$iv2) : smallSize$iv;
        int size = size$iv;
        int slotEndAddress = address$iv2 + size;
        int recomposeScopeSlotAddress = address$iv2 + GroupFlagsKt.utilitySlotsCountForFlags(groups[group + 4]);
        if (recomposeScopeSlotAddress > slotEndAddress) {
            return null;
        }
        Object obj = slots[recomposeScopeSlotAddress];
        if (obj instanceof RecomposeScopeImpl) {
            return (RecomposeScopeImpl) obj;
        }
        return null;
    }

    public final void traverseSiblings$runtime(int group, Function1<? super Integer, Unit> visit) {
        SlotTableAddressSpace this_$iv = getAddressSpace();
        int[] groups$iv = this_$iv.getGroups();
        int current$iv = group;
        while (current$iv >= 0) {
            visit.invoke(Integer.valueOf(current$iv));
            int address$iv$iv = current$iv;
            current$iv = groups$iv[address$iv$iv + 1];
        }
    }

    public final void traverseGroupAndParents$runtime(int group, Function1<? super Integer, Unit> visit) {
        SlotTableAddressSpace $this$iv = getAddressSpace();
        int[] groups$iv = $this$iv.getGroups();
        int current$iv = group;
        while (current$iv > 0) {
            visit.invoke(Integer.valueOf(current$iv));
            int address$iv$iv = current$iv;
            current$iv = groups$iv[address$iv$iv + 2];
        }
        boolean value$iv$iv = current$iv != 0;
        if (value$iv$iv) {
            return;
        }
        ComposerKt.composeImmediateRuntimeError("Traversing parent of group not in the slot table: " + group);
    }

    public final void traverseChildren$runtime(int group, Function1<? super Integer, Unit> visit) {
        SlotTableAddressSpace this_$iv = getAddressSpace();
        int[] groups$iv = this_$iv.getGroups();
        int current$iv = groups$iv[group + 3];
        while (current$iv > 0) {
            visit.invoke(Integer.valueOf(current$iv));
            int address$iv$iv = current$iv;
            current$iv = groups$iv[address$iv$iv + 1];
        }
    }

    public static /* synthetic */ void traverseGroup$runtime$default(SlotTable $this, int group, boolean includeSiblingsOfStartGroup, Function1 visit, int i, Object obj) {
        int address$iv$iv;
        if ((i & 2) != 0) {
            includeSiblingsOfStartGroup = false;
        }
        SlotTableAddressSpace this_$iv = $this.getAddressSpace();
        boolean includeSiblingsOfStartGroup$iv = includeSiblingsOfStartGroup;
        if (group < 0) {
            return;
        }
        IntStack toVisit$iv = new IntStack();
        int group$iv = group;
        int[] groups$iv = this_$iv.getGroups();
        while (true) {
            visit.invoke(Integer.valueOf(group$iv));
            if ((group$iv != group || includeSiblingsOfStartGroup$iv) && (address$iv$iv = groups$iv[group$iv + 1]) >= 0) {
                toVisit$iv.push(address$iv$iv);
            }
            int nextSibling$iv = group$iv;
            int address$iv$iv2 = groups$iv[nextSibling$iv + 3];
            if (address$iv$iv2 >= 0) {
                group$iv = address$iv$iv2;
            } else {
                if (toVisit$iv.tos == 0) {
                    return;
                } else {
                    group$iv = toVisit$iv.pop();
                }
            }
        }
    }

    public final void traverseGroup$runtime(int group, boolean includeSiblingsOfStartGroup, Function1<? super Integer, Unit> visit) {
        int address$iv$iv;
        SlotTableAddressSpace this_$iv = getAddressSpace();
        if (group < 0) {
            return;
        }
        IntStack toVisit$iv = new IntStack();
        int group$iv = group;
        int[] groups$iv = this_$iv.getGroups();
        while (true) {
            visit.invoke(Integer.valueOf(group$iv));
            if ((group$iv != group || includeSiblingsOfStartGroup) && (address$iv$iv = groups$iv[group$iv + 1]) >= 0) {
                toVisit$iv.push(address$iv$iv);
            }
            int nextSibling$iv = group$iv;
            int address$iv$iv2 = groups$iv[nextSibling$iv + 3];
            if (address$iv$iv2 >= 0) {
                group$iv = address$iv$iv2;
            } else {
                if (toVisit$iv.tos == 0) {
                    return;
                } else {
                    group$iv = toVisit$iv.pop();
                }
            }
        }
    }

    public final void traverseTable$runtime(Function1<? super Integer, Unit> visit) {
        SlotTableAddressSpace this_$iv = getAddressSpace();
        int start$iv = getRoot();
        if (start$iv < 0) {
            return;
        }
        IntStack toVisit$iv = new IntStack();
        int group$iv = start$iv;
        int[] groups$iv = this_$iv.getGroups();
        while (true) {
            visit.invoke(Integer.valueOf(group$iv));
            int address$iv$iv = groups$iv[group$iv + 1];
            if (address$iv$iv >= 0) {
                toVisit$iv.push(address$iv$iv);
            }
            int nextSibling$iv = group$iv;
            int address$iv$iv2 = groups$iv[nextSibling$iv + 3];
            if (address$iv$iv2 >= 0) {
                group$iv = address$iv$iv2;
            } else {
                if (toVisit$iv.tos == 0) {
                    return;
                } else {
                    group$iv = toVisit$iv.pop();
                }
            }
        }
    }

    public final boolean isGroupAChildOf$runtime(int child, int parent) {
        SlotTableAddressSpace $this$iv$iv = getAddressSpace();
        int[] groups$iv$iv = $this$iv$iv.getGroups();
        int current$iv$iv = child;
        while (true) {
            if (current$iv$iv > 0) {
                int group = current$iv$iv;
                if (group == parent) {
                    return true;
                }
                int address$iv$iv$iv = current$iv$iv;
                current$iv$iv = groups$iv$iv[address$iv$iv$iv + 2];
            } else {
                boolean value$iv$iv$iv = current$iv$iv != 0;
                if (!value$iv$iv$iv) {
                    ComposerKt.composeImmediateRuntimeError("Traversing parent of group not in the slot table: " + child);
                }
                return false;
            }
        }
    }

    public final void forEachSlot$runtime(Function1<Object, Unit> action) {
        SlotTable this_$iv;
        int address$iv$iv;
        int size$iv$iv;
        int $i$f$forEachSlot$runtime = 0;
        boolean z = true;
        if (!(!getHasEditor())) {
            ComposerKt.composeImmediateRuntimeError("Cannot read while an editor is pending");
        }
        SlotTable this_$iv2 = this;
        SlotTableAddressSpace this_$iv$iv = this_$iv2.getAddressSpace();
        int start$iv$iv = this_$iv2.getRoot();
        if (start$iv$iv < 0) {
            return;
        }
        IntStack toVisit$iv$iv = new IntStack();
        int group$iv$iv = start$iv$iv;
        int[] groups$iv$iv = this_$iv$iv.getGroups();
        while (true) {
            int group = group$iv$iv;
            int[] $this$groupSlotRange$iv$iv = getGroups();
            int i = $this$groupSlotRange$iv$iv[group + 5];
            boolean z2 = z;
            int $i$f$forEachSlot$runtime2 = $i$f$forEachSlot$runtime;
            if (i != -1) {
                SlotTableAddressSpace this_$iv$iv2 = getAddressSpace();
                int smallSize$iv$iv = (i & 15) + 1;
                int slotRange$iv$iv$iv = i >> 4;
                this_$iv = this_$iv2;
                if (smallSize$iv$iv > 15 ? z2 : false) {
                    address$iv$iv = slotRange$iv$iv$iv;
                    size$iv$iv = this_$iv$iv2.getLargeSizes().get(address$iv$iv);
                } else {
                    address$iv$iv = slotRange$iv$iv$iv;
                    size$iv$iv = smallSize$iv$iv;
                }
                int address$iv = address$iv$iv;
                int size$iv = size$iv$iv;
                int size$iv$iv2 = 0;
                while (true) {
                    int address$iv$iv2 = address$iv$iv;
                    int address$iv$iv3 = size$iv;
                    if (size$iv$iv2 >= address$iv$iv3) {
                        break;
                    }
                    int index$iv = size$iv$iv2;
                    Object value$iv = getSlots()[address$iv + size$iv$iv2];
                    if (Intrinsics.areEqual(value$iv, Composer.INSTANCE.getEmpty())) {
                        break;
                    }
                    action.invoke(value$iv);
                    size$iv$iv2 = index$iv + 1;
                    address$iv$iv = address$iv$iv2;
                    size$iv = address$iv$iv3;
                }
            } else {
                this_$iv = this_$iv2;
            }
            int address$iv$iv$iv = groups$iv$iv[group$iv$iv + 1];
            if (address$iv$iv$iv >= 0) {
                toVisit$iv$iv.push(address$iv$iv$iv);
            }
            int nextSibling$iv$iv = group$iv$iv;
            int address$iv$iv$iv2 = groups$iv$iv[nextSibling$iv$iv + 3];
            if (address$iv$iv$iv2 >= 0) {
                group$iv$iv = address$iv$iv$iv2;
                z = z2;
                $i$f$forEachSlot$runtime = $i$f$forEachSlot$runtime2;
                this_$iv2 = this_$iv;
            } else {
                if (toVisit$iv$iv.tos == 0 ? z2 : false) {
                    return;
                }
                group$iv$iv = toVisit$iv$iv.pop();
                z = z2;
                $i$f$forEachSlot$runtime = $i$f$forEachSlot$runtime2;
                this_$iv2 = this_$iv;
            }
        }
    }

    public final void forEachGroupSlot$runtime(int group, Function2<Object, ? super Integer, Unit> action) {
        int[] $this$groupSlotRange$iv = getGroups();
        int slotRange = $this$groupSlotRange$iv[group + 5];
        if (slotRange != -1) {
            SlotTableAddressSpace this_$iv = getAddressSpace();
            int smallSize$iv = (slotRange & 15) + 1;
            int address$iv = slotRange >> 4;
            int size$iv = smallSize$iv > 15 ? this_$iv.getLargeSizes().get(address$iv) : smallSize$iv;
            int size = size$iv;
            for (int index = 0; index < size; index++) {
                Object value = getSlots()[address$iv + index];
                if (!Intrinsics.areEqual(value, Composer.INSTANCE.getEmpty())) {
                    action.invoke(value, Integer.valueOf(index));
                } else {
                    return;
                }
            }
        }
    }

    public final SlotTable newTableInSameAddressSpace() {
        return new SlotTable(0, this.addressSpace, this.recordSourceInformation, this.recordCallByInformation, 1, null);
    }

    @Override // androidx.compose.runtime.SlotStorage
    public void collectSourceInformation() {
        this.recordSourceInformation = true;
    }

    @Override // androidx.compose.runtime.SlotStorage
    public void collectCalledByInformation() {
        this.recordCallByInformation = true;
    }

    @Override // androidx.compose.runtime.SlotStorage
    public String toDebugString() {
        return toDebugString(true);
    }

    public final String toDebugString(boolean includeSlots) {
        StringBuilder $this$toDebugString_u24lambda_u240 = new StringBuilder();
        $this$toDebugString_u24lambda_u240.append("SlotTable(");
        $this$toDebugString_u24lambda_u240.append('\n');
        int group$iv = this.root;
        SlotTableAddressSpace this_$iv$iv = getAddressSpace();
        int[] groups$iv$iv = this_$iv$iv.getGroups();
        int current$iv$iv = group$iv;
        while (current$iv$iv >= 0) {
            int it = current$iv$iv;
            toDebugString$lambda$0$dumpGroup($this$toDebugString_u24lambda_u240, this, includeSlots, it, "  ");
            int address$iv$iv$iv = groups$iv$iv[it + 1];
            current$iv$iv = address$iv$iv$iv;
        }
        $this$toDebugString_u24lambda_u240.append(")");
        return $this$toDebugString_u24lambda_u240.toString();
    }

    private static final void toDebugString$lambda$0$dumpGroup(StringBuilder $this_buildString, SlotTable this$0, boolean $includeSlots, int address, String indent) {
        int i;
        $this_buildString.append(indent);
        int[] groups = this$0.addressSpace.getGroups();
        $this_buildString.append("Group(" + address + ") key: " + groups[address + 0]);
        Object[] slots = this$0.addressSpace.getSlots();
        int address$iv = groups[address + 5];
        int address$iv2 = groups[address + 4];
        int childNodes = address$iv2 & GroupFlagsSpec.CHILD_NODE_COUNT_MASK;
        if (childNodes > 0) {
            $this_buildString.append(" Nodes: ");
            $this_buildString.append(childNodes);
        }
        if (((-67108864) & address$iv2) != 0) {
            $this_buildString.append(" Marks: ");
            int other$iv = (268435456 & address$iv2) == 268435456 ? 1 : 0;
            if (other$iv != 0) {
                $this_buildString.append('C');
            }
            int other$iv2 = (536870912 & address$iv2) == 536870912 ? 1 : 0;
            if (other$iv2 != 0) {
                $this_buildString.append('c');
            }
            int other$iv3 = (1073741824 & address$iv2) == 1073741824 ? 1 : 0;
            if (other$iv3 != 0) {
                $this_buildString.append('S');
            }
            int other$iv4 = (Integer.MIN_VALUE & address$iv2) == Integer.MIN_VALUE ? 1 : 0;
            if (other$iv4 != 0) {
                $this_buildString.append('s');
            }
            int other$iv5 = (67108864 & address$iv2) == 67108864 ? 1 : 0;
            if (other$iv5 != 0) {
                $this_buildString.append('R');
            }
            int other$iv6 = (134217728 & address$iv2) == 134217728 ? 1 : 0;
            if (other$iv6 != 0) {
                $this_buildString.append('r');
            }
        }
        if ($includeSlots) {
            int currentSlot = address$iv >> 4;
            if (address$iv != -1) {
                SlotTableAddressSpace this_$iv = this$0.addressSpace;
                if (address$iv == -1) {
                    i = 0;
                } else {
                    int smallSize$iv = (address$iv & 15) + 1;
                    if (smallSize$iv > 15) {
                        int slotRange$iv$iv = address$iv >> 4;
                        i = this_$iv.getLargeSizes().get(slotRange$iv$iv);
                    } else {
                        i = smallSize$iv;
                    }
                }
                int slotEnd = i + currentSlot;
                if ((8388608 & address$iv2) == 8388608) {
                    $this_buildString.append(" Node: ");
                    $this_buildString.append(SlotTableKt.summarize(slots[currentSlot], 10));
                    currentSlot++;
                }
                int other$iv7 = (16777216 & address$iv2) == 16777216 ? 1 : 0;
                if (other$iv7 != 0) {
                    $this_buildString.append(" Key: ");
                    $this_buildString.append(SlotTableKt.summarize(slots[currentSlot], 10));
                    currentSlot++;
                }
                int $this$contains$iv = (33554432 & address$iv2) == 33554432 ? 1 : 0;
                if ($this$contains$iv != 0) {
                    $this_buildString.append(" Aux: ");
                    $this_buildString.append(SlotTableKt.summarize(slots[currentSlot], 10));
                    currentSlot++;
                }
                if (currentSlot < slotEnd) {
                    $this_buildString.append(" (" + currentSlot + '-' + slotEnd + ")[");
                    while (currentSlot < slotEnd) {
                        int currentSlot2 = currentSlot + 1;
                        $this_buildString.append(SlotTableKt.summarize(slots[currentSlot], 10));
                        if (currentSlot2 < slotEnd) {
                            $this_buildString.append(", ");
                        }
                        currentSlot = currentSlot2;
                    }
                    $this_buildString.append("]");
                }
            }
        }
        $this_buildString.append(':').append('\n');
        String childIndent = indent + "  ";
        SlotTableAddressSpace this_$iv$iv = this$0.getAddressSpace();
        int[] groups$iv$iv = this_$iv$iv.getGroups();
        int current$iv$iv = groups$iv$iv[address + 3];
        while (current$iv$iv > 0) {
            int childAddress = current$iv$iv;
            toDebugString$lambda$0$dumpGroup($this_buildString, this$0, $includeSlots, childAddress, childIndent);
            int address$iv$iv$iv = current$iv$iv;
            current$iv$iv = groups$iv$iv[address$iv$iv$iv + 1];
        }
    }

    @Override // androidx.compose.runtime.SlotStorage
    public void verifyWellFormed() {
        this.addressSpace.validate();
        int[] groups = this.addressSpace.getGroups();
        Object[] slots = this.addressSpace.getSlots();
        MutableIntSet groupsSeen = IntSetKt.mutableIntSetOf();
        int group$iv = this.root;
        SlotTableAddressSpace this_$iv$iv = getAddressSpace();
        int[] groups$iv$iv = this_$iv$iv.getGroups();
        int current$iv$iv = group$iv;
        while (current$iv$iv >= 0) {
            int it = current$iv$iv;
            verifyWellFormed$validateGroup(groupsSeen, groups, this, slots, -1, it);
            int address$iv$iv$iv = current$iv$iv;
            current$iv$iv = groups$iv$iv[address$iv$iv$iv + 1];
        }
    }

    private static final void verifyWellFormed$validateSlotRange(SlotTable this$0, Object[] slots, int group, int slotRange) {
        if (slotRange == -1) {
            return;
        }
        SlotTableAddressSpace this_$iv = this$0.addressSpace;
        int smallSize$iv = (slotRange & 15) + 1;
        int address$iv = slotRange >> 4;
        int size$iv = smallSize$iv > 15 ? this_$iv.getLargeSizes().get(address$iv) : smallSize$iv;
        if (address$iv < 0 || address$iv >= slots.length) {
            throw new IllegalStateException(("Slot index for group " + group + " out of bounds: " + address$iv).toString());
        }
    }

    private static final int verifyWellFormed$validateGroup(MutableIntSet groupsSeen, int[] groups, SlotTable this$0, Object[] slots, int parent, int group) {
        MutableIntSet mutableIntSet = groupsSeen;
        if (group == -1) {
            return 0;
        }
        if (mutableIntSet.contains(group)) {
            throw new IllegalStateException(("Circular group encountered at " + group).toString());
        }
        mutableIntSet.add(group);
        if (group % 6 != 0) {
            throw new IllegalStateException(("Invalid group address: " + group).toString());
        }
        int address$iv = groups[group + 2];
        if (address$iv != parent) {
            throw new IllegalStateException(("Invalid parent link in group " + group).toString());
        }
        int address$iv2 = groups[group + 5];
        SlotTable slotTable = this$0;
        Object[] objArr = slots;
        verifyWellFormed$validateSlotRange(slotTable, objArr, group, address$iv2);
        int nodeCount = 0;
        int expectedHasFlags = 0;
        int address$iv3 = groups[group + 4];
        int groupHasFlags = address$iv3 & GroupFlagsKt.HasMarkFlags;
        int group$iv = group;
        SlotTableAddressSpace this_$iv$iv = this$0.getAddressSpace();
        int[] groups$iv$iv = this_$iv$iv.getGroups();
        int current$iv$iv = groups$iv$iv[group$iv + 3];
        while (current$iv$iv > 0) {
            int expectedHasFlags2 = expectedHasFlags;
            int expectedHasFlags3 = current$iv$iv;
            int expectedHasFlags4 = group$iv;
            nodeCount += verifyWellFormed$validateGroup(mutableIntSet, groups, slotTable, objArr, group, expectedHasFlags3);
            int address$iv4 = groups[expectedHasFlags3 + 4];
            int propagateFlags = GroupFlagsKt.propagatingFlagsOf(address$iv4);
            int expectedHasFlags5 = expectedHasFlags2 | propagateFlags;
            if (propagateFlags != 0 && (groupHasFlags & propagateFlags) == 0) {
                StringBuilder sbAppend = new StringBuilder().append("Group ").append(expectedHasFlags3).append(" contains a flag that the parent, ").append(group).append(", is not recorded as having, ");
                String string = Integer.toString(propagateFlags, CharsKt.checkRadix(16));
                Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
                throw new IllegalStateException(sbAppend.append(string).append(' ').append(SlotTableKt.flagsNames(propagateFlags)).toString().toString());
            }
            int address$iv$iv$iv = current$iv$iv;
            current$iv$iv = groups$iv$iv[address$iv$iv$iv + 1];
            mutableIntSet = groupsSeen;
            slotTable = this$0;
            expectedHasFlags = expectedHasFlags5;
            group$iv = expectedHasFlags4;
            objArr = slots;
        }
        int nodeCount2 = nodeCount;
        int group$iv2 = expectedHasFlags;
        int receivedNodeCount = groups[group + 4] & GroupFlagsSpec.CHILD_NODE_COUNT_MASK;
        if (receivedNodeCount != nodeCount2) {
            throw new IllegalStateException(("Unexpected node count for group " + group + ", expected " + nodeCount2 + ", received: " + receivedNodeCount).toString());
        }
        if (groupHasFlags == group$iv2) {
            int address$iv$iv = groups[group + 4];
            if ((8388608 & address$iv$iv) == 8388608) {
                return 1;
            }
            return address$iv$iv & GroupFlagsSpec.CHILD_NODE_COUNT_MASK;
        }
        StringBuilder sbAppend2 = new StringBuilder().append("Unexpected has mark flags for group ").append(group).append(", expected ");
        String string2 = Integer.toString(group$iv2, CharsKt.checkRadix(16));
        Intrinsics.checkNotNullExpressionValue(string2, "toString(...)");
        StringBuilder sbAppend3 = sbAppend2.append(string2).append(' ').append(SlotTableKt.flagsNames(group$iv2)).append(", received ");
        String string3 = Integer.toString(groupHasFlags, CharsKt.checkRadix(16));
        Intrinsics.checkNotNullExpressionValue(string3, "toString(...)");
        throw new IllegalStateException(sbAppend3.append(string3).append(' ').append(SlotTableKt.flagsNames(groupHasFlags)).toString().toString());
    }

    @Override // androidx.compose.runtime.tooling.CompositionData
    public Iterable<CompositionGroup> getCompositionGroups() {
        return this;
    }

    @Override // androidx.compose.runtime.SlotStorage, androidx.compose.runtime.tooling.CompositionData
    public boolean isEmpty() {
        return this.root == -1;
    }

    @Override // java.lang.Iterable
    public Iterator<CompositionGroup> iterator() {
        return new GroupIterator(this, this.root);
    }

    public final int nextSiblingOf$runtime(int group) {
        int[] $this$groupNext$iv = getGroups();
        return $this$groupNext$iv[group + 1];
    }

    public final int firstChildOf$runtime(int group) {
        int[] $this$groupChild$iv = getGroups();
        return $this$groupChild$iv[group + 3];
    }

    public final boolean groupHasAux$runtime(int group) {
        int[] $this$groupFlags$iv = getGroups();
        int $this$contains$iv = $this$groupFlags$iv[group + 4];
        return (33554432 & $this$contains$iv) == 33554432;
    }

    public final Object groupSlotAtIndex$runtime(int group, int index) {
        int[] $this$groupSlotRange$iv = getGroups();
        int range = $this$groupSlotRange$iv[group + 5];
        if (range == -1 || index < 0) {
            return null;
        }
        int[] $this$groupSlotRange$iv2 = getGroups();
        int slotRange = $this$groupSlotRange$iv2[group + 5];
        SlotTableAddressSpace this_$iv = this.addressSpace;
        int smallSize$iv = (slotRange & 15) + 1;
        int address$iv = slotRange >> 4;
        int size$iv = smallSize$iv > 15 ? this_$iv.getLargeSizes().get(address$iv) : smallSize$iv;
        int size = size$iv;
        if (index >= size) {
            return null;
        }
        return getSlots()[address$iv + index];
    }

    public final Object groupObjectKey$runtime(int group) {
        int[] $this$groupFlags$iv = getGroups();
        int flags = $this$groupFlags$iv[group + 4];
        if ((16777216 & flags) == 16777216) {
            return groupSlotAtIndex$runtime(group, Integer.bitCount(8388608 & flags));
        }
        return null;
    }

    public final Object groupAux$runtime(int group) {
        int[] $this$groupFlags$iv = getGroups();
        int flags = $this$groupFlags$iv[group + 4];
        if ((33554432 & flags) == 33554432) {
            return groupSlotAtIndex$runtime(group, Integer.bitCount(25165824 & flags));
        }
        return null;
    }

    public final Object groupNode$runtime(int group) {
        int[] $this$groupFlags$iv = getGroups();
        int flags = $this$groupFlags$iv[group + 4];
        if ((8388608 & flags) == 8388608) {
            return groupSlotAtIndex$runtime(group, 0);
        }
        return null;
    }

    public final int groupKeyOf$runtime(int group) {
        int[] $this$groupKey$iv = getGroups();
        return $this$groupKey$iv[group + 0];
    }

    public final int groupSlotRange$runtime(int group) {
        int[] $this$groupSlotRange$iv = getGroups();
        return $this$groupSlotRange$iv[group + 5];
    }

    public final int groupFlags$runtime(int group) {
        int[] $this$groupFlags$iv = getGroups();
        return $this$groupFlags$iv[group + 4];
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int[] getGroups() {
        return this.addressSpace.getGroups();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object[] getSlots() {
        return this.addressSpace.getSlots();
    }

    /* JADX INFO: renamed from: androidx.compose.runtime.composer.linkbuffer.SlotTable$getSlots$1, reason: invalid class name */
    /* JADX INFO: compiled from: SlotTable.kt */
    @Metadata(d1 = {"\u0000\u0015\n\u0000\n\u0002\u0010\u001c\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010(\n\u0000*\u0001\u0000\b\n\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001J\u0011\u0010\u0003\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0004H\u0096\u0002¨\u0006\u0005"}, d2 = {"androidx/compose/runtime/composer/linkbuffer/SlotTable$getSlots$1", "", "", "iterator", "", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class AnonymousClass1 implements Iterable<Object>, KMappedMarker {
        AnonymousClass1() {
        }

        @Override // java.lang.Iterable
        public Iterator<Object> iterator() {
            return SequencesKt.iterator(new SlotTable$getSlots$1$iterator$1(SlotTable.this, null));
        }
    }

    @Override // androidx.compose.runtime.SlotStorage
    /* JADX INFO: renamed from: getSlots, reason: collision with other method in class */
    public Iterable<Object> mo4584getSlots() {
        return new AnonymousClass1();
    }

    public final Object toDebugTree$runtime() {
        List it = SequencesKt.toList(SequencesKt.sequence(new SlotTable$toDebugTree$1(this, null)));
        return it.size() == 1 ? CollectionsKt.first(it) : it;
    }

    /* JADX INFO: compiled from: SlotTable.kt */
    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\u000e\n\u0000\b\u0080\u0004\u0018\u00002\u00020\u0001B\u0013\u0012\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010+\u001a\u00020,H\u0016R\u0015\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001b\u0010\t\u001a\f\u0012\b\u0012\u00060\u0000R\u00020\u000b0\n8F¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0019\u0010\u000e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\n8F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\rR\u0011\u0010\u0010\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\bR\u0011\u0010\u0012\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\bR\u0015\u0010\u0014\u001a\u00060\u0015R\u00020\u000b8F¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u0013\u0010\u0018\u001a\u0004\u0018\u00010\u00018F¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aR\u0013\u0010\u001b\u001a\u0004\u0018\u00010\u00018F¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001aR\u0013\u0010\u001d\u001a\u0004\u0018\u00010\u00018F¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001aR\u0011\u0010\u001f\u001a\u00020 8F¢\u0006\u0006\u001a\u0004\b\u001f\u0010!R\u0011\u0010\"\u001a\u00020 8F¢\u0006\u0006\u001a\u0004\b\"\u0010!R\u0011\u0010#\u001a\u00020 8F¢\u0006\u0006\u001a\u0004\b$\u0010!R\u0011\u0010%\u001a\u00020 8F¢\u0006\u0006\u001a\u0004\b%\u0010!R\u0011\u0010&\u001a\u00020 8F¢\u0006\u0006\u001a\u0004\b'\u0010!R\u0011\u0010(\u001a\u00020 8F¢\u0006\u0006\u001a\u0004\b(\u0010!R\u0011\u0010)\u001a\u00020 8F¢\u0006\u0006\u001a\u0004\b*\u0010!¨\u0006-"}, d2 = {"Landroidx/compose/runtime/composer/linkbuffer/SlotTable$DebugGroup;", "", "address", "", "Landroidx/compose/runtime/composer/linkbuffer/GroupAddress;", "<init>", "(Landroidx/compose/runtime/composer/linkbuffer/SlotTable;I)V", "getAddress", "()I", "children", "", "Landroidx/compose/runtime/composer/linkbuffer/SlotTable;", "getChildren", "()Ljava/util/List;", "slots", "getSlots", "key", "getKey", "flags", "getFlags", "slotRange", "Landroidx/compose/runtime/composer/linkbuffer/SlotTable$DebugSlotRange;", "getSlotRange", "()Landroidx/compose/runtime/composer/linkbuffer/SlotTable$DebugSlotRange;", "objectKey", "getObjectKey", "()Ljava/lang/Object;", "node", "getNode", "aux", "getAux", "isNode", "", "()Z", "isMovableContent", "hasMovableContent", "getHasMovableContent", "isSubComposition", "hasSubComposition", "getHasSubComposition", "isRecomposeRequired", "hasRecomposeRequired", "getHasRecomposeRequired", "toString", "", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public final class DebugGroup {
        private final int address;

        public DebugGroup(int address) {
            this.address = address;
        }

        public final int getAddress() {
            return this.address;
        }

        public final List<DebugGroup> getChildren() {
            return SequencesKt.toList(SequencesKt.sequence(new SlotTable$DebugGroup$children$1(SlotTable.this, this, null)));
        }

        public final List<Object> getSlots() {
            return SequencesKt.toList(SequencesKt.sequence(new SlotTable$DebugGroup$slots$1(this, SlotTable.this, null)));
        }

        public final int getKey() {
            int[] $this$groupKey$iv = SlotTable.this.getGroups();
            int address$iv = this.address;
            return $this$groupKey$iv[address$iv + 0];
        }

        public final int getFlags() {
            int[] $this$groupFlags$iv = SlotTable.this.getGroups();
            int address$iv = this.address;
            return $this$groupFlags$iv[address$iv + 4];
        }

        public final DebugSlotRange getSlotRange() {
            SlotTable slotTable = SlotTable.this;
            int[] $this$groupSlotRange$iv = SlotTable.this.getGroups();
            int address$iv = this.address;
            return slotTable.new DebugSlotRange($this$groupSlotRange$iv[address$iv + 5]);
        }

        public final Object getObjectKey() {
            int $this$contains$iv = getFlags();
            if ((16777216 & $this$contains$iv) == 16777216) {
                Object[] slots = SlotTable.this.getSlots();
                int address = getSlotRange().getAddress();
                int flags$iv = getFlags();
                return slots[address + Integer.bitCount(8388608 & flags$iv)];
            }
            return Composer.INSTANCE.getEmpty();
        }

        public final Object getNode() {
            int $this$contains$iv = getFlags();
            if ((8388608 & $this$contains$iv) == 8388608) {
                Object[] slots = SlotTable.this.getSlots();
                int address = getSlotRange().getAddress();
                getFlags();
                return slots[address + 0];
            }
            return Composer.INSTANCE.getEmpty();
        }

        public final Object getAux() {
            int $this$contains$iv = getFlags();
            if ((33554432 & $this$contains$iv) == 33554432) {
                Object[] slots = SlotTable.this.getSlots();
                int address = getSlotRange().getAddress();
                int flags$iv = getFlags();
                return slots[address + Integer.bitCount(25165824 & flags$iv)];
            }
            return Composer.INSTANCE.getEmpty();
        }

        public final boolean isNode() {
            int $this$contains$iv = getFlags();
            return (8388608 & $this$contains$iv) == 8388608;
        }

        public final boolean isMovableContent() {
            int $this$contains$iv = getFlags();
            return (268435456 & $this$contains$iv) == 268435456;
        }

        public final boolean getHasMovableContent() {
            int $this$contains$iv = getFlags();
            return (536870912 & $this$contains$iv) == 536870912;
        }

        public final boolean isSubComposition() {
            int $this$contains$iv = getFlags();
            return (67108864 & $this$contains$iv) == 67108864;
        }

        public final boolean getHasSubComposition() {
            int $this$contains$iv = getFlags();
            return (Integer.MIN_VALUE & $this$contains$iv) == Integer.MIN_VALUE;
        }

        public final boolean isRecomposeRequired() {
            int $this$contains$iv = getFlags();
            return (67108864 & $this$contains$iv) == 67108864;
        }

        public final boolean getHasRecomposeRequired() {
            int $this$contains$iv = getFlags();
            return (134217728 & $this$contains$iv) == 134217728;
        }

        public String toString() {
            SlotTable this_$iv = SlotTable.this;
            StringBuilder $this$toString_u24lambda_u240 = new StringBuilder();
            $this$toString_u24lambda_u240.append("Group(");
            $this$toString_u24lambda_u240.append(getKey());
            if ((getFlags() & (-67108864)) != 0) {
                $this$toString_u24lambda_u240.append(", flags=");
                if (isMovableContent()) {
                    $this$toString_u24lambda_u240.append('C');
                }
                if (getHasMovableContent()) {
                    $this$toString_u24lambda_u240.append('c');
                }
                if (isSubComposition()) {
                    $this$toString_u24lambda_u240.append('S');
                }
                if (getHasSubComposition()) {
                    $this$toString_u24lambda_u240.append('s');
                }
                if (isRecomposeRequired()) {
                    $this$toString_u24lambda_u240.append('R');
                }
                if (getHasRecomposeRequired()) {
                    $this$toString_u24lambda_u240.append('r');
                }
            }
            int $this$contains$iv = getFlags();
            int $this$contains$iv2 = (16777216 & $this$contains$iv) == 16777216 ? 1 : 0;
            if ($this$contains$iv2 != 0) {
                $this$toString_u24lambda_u240.append(", object key");
            }
            int $this$contains$iv3 = getFlags();
            if ((33554432 & $this$contains$iv3) == 33554432) {
                $this$toString_u24lambda_u240.append(", aux");
            }
            if (isNode()) {
                $this$toString_u24lambda_u240.append(", node");
            }
            int[] $this$groupChild$iv = this_$iv.getGroups();
            int address$iv = this.address;
            if ($this$groupChild$iv[address$iv + 3] != -1) {
                int count = 0;
                int group$iv = this.address;
                SlotTableAddressSpace this_$iv$iv = this_$iv.getAddressSpace();
                int[] groups$iv$iv = this_$iv$iv.getGroups();
                int current$iv$iv = groups$iv$iv[group$iv + 3];
                while (current$iv$iv > 0) {
                    int address$iv$iv$iv = current$iv$iv;
                    count++;
                    current$iv$iv = groups$iv$iv[address$iv$iv$iv + 1];
                }
                $this$toString_u24lambda_u240.append(", ");
                $this$toString_u24lambda_u240.append(count);
                $this$toString_u24lambda_u240.append(count == 1 ? " child" : " children");
            }
            int[] $this$groupSlotRange$iv = this_$iv.getGroups();
            int address$iv2 = this.address;
            if ($this$groupSlotRange$iv[address$iv2 + 5] != -1) {
                $this$toString_u24lambda_u240.append(", ");
                $this$toString_u24lambda_u240.append(getSlotRange().getSize());
                $this$toString_u24lambda_u240.append(" slots");
            }
            $this$toString_u24lambda_u240.append(')');
            return $this$toString_u24lambda_u240.toString();
        }
    }

    /* JADX INFO: compiled from: SlotTable.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0080\u0004\u0018\u00002\u00020\u0001B\u0013\u0012\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004¢\u0006\u0004\b\u0005\u0010\u0006R\u0015\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\n\u0010\bR\u0011\u0010\u000b\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\f\u0010\bR\u0011\u0010\r\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\b¨\u0006\u000f"}, d2 = {"Landroidx/compose/runtime/composer/linkbuffer/SlotTable$DebugSlotRange;", "", "range", "", "Landroidx/compose/runtime/composer/linkbuffer/SlotRange;", "<init>", "(Landroidx/compose/runtime/composer/linkbuffer/SlotTable;I)V", "getRange", "()I", "address", "getAddress", "size", "getSize", "end", "getEnd", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public final class DebugSlotRange {
        private final int range;

        public DebugSlotRange(int range) {
            this.range = range;
        }

        public final int getRange() {
            return this.range;
        }

        public final int getAddress() {
            int slotRange$iv = this.range;
            return slotRange$iv >> 4;
        }

        public final int getSize() {
            SlotTableAddressSpace this_$iv = SlotTable.this.getAddressSpace();
            int slotRange$iv = this.range;
            if (slotRange$iv == -1) {
                return 0;
            }
            int smallSize$iv = (slotRange$iv & 15) + 1;
            if (!(smallSize$iv > 15)) {
                return smallSize$iv;
            }
            int slotRange$iv$iv = slotRange$iv >> 4;
            return this_$iv.getLargeSizes().get(slotRange$iv$iv);
        }

        public final int getEnd() {
            return getAddress() + getSize();
        }
    }

    /* JADX INFO: compiled from: SlotTable.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J,\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u0017\u0010\b\u001a\u0013\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\t¢\u0006\u0002\b\fH\u0086\b¨\u0006\r"}, d2 = {"Landroidx/compose/runtime/composer/linkbuffer/SlotTable$Companion;", "", "<init>", "()V", "build", "Landroidx/compose/runtime/composer/linkbuffer/SlotTable;", "addressSpace", "Landroidx/compose/runtime/composer/linkbuffer/SlotTableAddressSpace;", "block", "Lkotlin/Function1;", "Landroidx/compose/runtime/composer/linkbuffer/SlotTableBuilder;", "", "Lkotlin/ExtensionFunctionType;", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public static /* synthetic */ SlotTable build$default(Companion $this, SlotTableAddressSpace addressSpace, Function1 block, int i, Object obj) {
            if ((i & 1) != 0) {
                addressSpace = new SlotTableAddressSpace();
            }
            SlotTableBuilder builder = new SlotTableBuilder(addressSpace, false, false);
            builder.buildStart();
            block.invoke(builder);
            return builder.build();
        }

        public final SlotTable build(SlotTableAddressSpace addressSpace, Function1<? super SlotTableBuilder, Unit> block) {
            SlotTableBuilder builder = new SlotTableBuilder(addressSpace, false, false);
            builder.buildStart();
            block.invoke(builder);
            return builder.build();
        }
    }
}
