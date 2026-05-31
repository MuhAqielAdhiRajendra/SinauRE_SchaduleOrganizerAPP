package androidx.compose.runtime.composer.linkbuffer.changelist;

import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.Composition;
import androidx.compose.runtime.CompositionContext;
import androidx.compose.runtime.ControlledComposition;
import androidx.compose.runtime.LinkComposer;
import androidx.compose.runtime.LinkRememberObserverHolder;
import androidx.compose.runtime.MovableContentState;
import androidx.compose.runtime.MovableContentStateReference;
import androidx.compose.runtime.RecomposeScopeImpl;
import androidx.compose.runtime.RememberObserverHolder;
import androidx.compose.runtime.Stack;
import androidx.compose.runtime.composer.linkbuffer.GroupFlagsSpec;
import androidx.compose.runtime.composer.linkbuffer.GroupHandleKt;
import androidx.compose.runtime.composer.linkbuffer.LinkAnchor;
import androidx.compose.runtime.composer.linkbuffer.SlotTable;
import androidx.compose.runtime.composer.linkbuffer.SlotTableReader;
import androidx.compose.runtime.internal.IntRef;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ComposerChangeListWriter.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000Î\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\b\u0001\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\b\u0010(\u001a\u00020)H\u0002J\b\u0010*\u001a\u00020)H\u0002J\b\u0010+\u001a\u00020)H\u0002J\u0006\u0010,\u001a\u00020)J\u001c\u0010-\u001a\u00020)2\n\u0010.\u001a\u00060&j\u0002`'2\b\b\u0002\u0010/\u001a\u00020\u0011J\u0017\u00101\u001a\u00020)2\f\u00102\u001a\b\u0012\u0004\u0012\u00020)03H\u0086\bJ#\u00104\u001a\u00020)2\n\u00105\u001a\u00060&j\u0002`'2\f\u00102\u001a\b\u0012\u0004\u0012\u00020)03H\u0086\bJ\u001f\u00106\u001a\u00020)2\u0006\u00107\u001a\u00020 2\f\u00102\u001a\b\u0012\u0004\u0012\u00020)03H\u0082\bJ\u001f\u00108\u001a\u00020)2\u0006\u00109\u001a\u00020\u00052\f\u00102\u001a\b\u0012\u0004\u0012\u00020)03H\u0086\bJ\u0017\u0010:\u001a\u00020)2\f\u00102\u001a\b\u0012\u0004\u0012\u00020)03H\u0086\bJ\u000e\u0010;\u001a\u00020)2\u0006\u0010<\u001a\u00020=J\u000e\u0010>\u001a\u00020)2\u0006\u0010?\u001a\u00020@J\u000e\u0010A\u001a\u00020)2\u0006\u0010?\u001a\u00020@J\u000e\u0010B\u001a\u00020)2\u0006\u0010?\u001a\u00020@J\u0016\u0010C\u001a\u00020)2\u0006\u0010D\u001a\u00020E2\u0006\u0010F\u001a\u00020GJ\u0018\u0010H\u001a\u00020)2\u0006\u0010I\u001a\u00020\u00172\b\u0010<\u001a\u0004\u0018\u00010\u0001J\u0010\u0010J\u001a\u00020)2\b\u0010<\u001a\u0004\u0018\u00010\u0001J\u001a\u0010K\u001a\u00020)2\n\u0010L\u001a\u00060\u0017j\u0002`M2\u0006\u0010N\u001a\u00020\u0017J\u0006\u0010O\u001a\u00020)J\u0010\u0010P\u001a\u00020)2\b\u0010Q\u001a\u0004\u0018\u00010\u0001J\u0006\u0010R\u001a\u00020)J\u001a\u0010S\u001a\u00020)2\u0006\u0010T\u001a\u00020U2\n\u0010V\u001a\u00060&j\u0002`'J\"\u0010S\u001a\u00020)2\u0006\u0010T\u001a\u00020U2\n\u0010V\u001a\u00060&j\u0002`'2\u0006\u0010W\u001a\u00020XJ\u000e\u0010Y\u001a\u00020)2\u0006\u0010Z\u001a\u00020\u0017J\"\u0010[\u001a\u00020)2\u0012\u0010\\\u001a\u000e\u0012\u0004\u0012\u00020^\u0012\u0004\u0012\u00020)0]2\u0006\u0010_\u001a\u00020^J\u0010\u0010`\u001a\u00020)2\b\u0010a\u001a\u0004\u0018\u00010\u0001J>\u0010b\u001a\u00020)\"\u0004\b\u0000\u0010c\"\u0004\b\u0001\u0010d2\u0006\u0010<\u001a\u0002Hd2\u001d\u00102\u001a\u0019\u0012\u0004\u0012\u0002Hc\u0012\u0004\u0012\u0002Hd\u0012\u0004\u0012\u00020)0e¢\u0006\u0002\bf¢\u0006\u0002\u0010gJ\u0016\u0010h\u001a\u00020)2\u0006\u0010i\u001a\u00020\u00172\u0006\u0010N\u001a\u00020\u0017J\u001e\u0010j\u001a\u00020)2\u0006\u0010k\u001a\u00020\u00172\u0006\u0010l\u001a\u00020\u00172\u0006\u0010N\u001a\u00020\u0017J\u0006\u0010m\u001a\u00020)J\u001a\u0010n\u001a\u00020)2\u0006\u0010i\u001a\u00020\u00172\n\u0010o\u001a\u00060\u0017j\u0002`MJ\b\u0010p\u001a\u00020)H\u0002J\u0018\u0010q\u001a\u00020)2\u0006\u0010i\u001a\u00020\u00172\u0006\u0010r\u001a\u00020\u0017H\u0002J \u0010s\u001a\u00020)2\u0006\u0010t\u001a\u00020\u00172\u0006\u0010u\u001a\u00020\u00172\u0006\u0010N\u001a\u00020\u0017H\u0002J\u0006\u0010v\u001a\u00020)J\u0010\u0010w\u001a\u00020)2\b\u0010a\u001a\u0004\u0018\u00010\u0001J\b\u0010x\u001a\u00020)H\u0002J\u0014\u0010y\u001a\u00020)2\f\u0010z\u001a\b\u0012\u0004\u0012\u00020)03J\u001a\u0010{\u001a\u00020)2\u0006\u0010|\u001a\u00020}2\n\u0010.\u001a\u00060&j\u0002`'J \u0010~\u001a\u00020)2\u000f\u0010\u007f\u001a\u000b\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0080\u00012\u0007\u0010\u0081\u0001\u001a\u00020}J/\u0010\u0082\u0001\u001a\u00020)2\n\u0010\u0083\u0001\u001a\u0005\u0018\u00010\u0084\u00012\b\u0010\u0085\u0001\u001a\u00030\u0086\u00012\u0007\u0010u\u001a\u00030\u0087\u00012\u0007\u0010t\u001a\u00030\u0087\u0001J$\u0010\u0088\u0001\u001a\u00020)2\u0007\u0010_\u001a\u00030\u0089\u00012\b\u0010\u0085\u0001\u001a\u00030\u0086\u00012\b\u0010\u008a\u0001\u001a\u00030\u0087\u0001J\u0007\u0010\u008b\u0001\u001a\u00020)J\u0013\u0010\u008c\u0001\u001a\u00020)2\n\u0010\u0083\u0001\u001a\u0005\u0018\u00010\u0084\u0001J\u001d\u0010\u008d\u0001\u001a\u00020)2\u0007\u0010\u008e\u0001\u001a\u00020\u00052\u000b\b\u0002\u0010\u0081\u0001\u001a\u0004\u0018\u00010}J\u0007\u0010\u008f\u0001\u001a\u00020)J\u0007\u0010\u0090\u0001\u001a\u00020)J\u0007\u0010\u0091\u0001\u001a\u00020)R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\r8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\u00020\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u0018\u0010\u0018\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0019X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001aR\u000e\u0010\u001b\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u001f\u001a\u00020 X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u0012\u0010%\u001a\u00060&j\u0002`'X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u00100\u001a\u00020\u00118F¢\u0006\u0006\u001a\u0004\b0\u0010\u0013¨\u0006\u0092\u0001"}, d2 = {"Landroidx/compose/runtime/composer/linkbuffer/changelist/ComposerChangeListWriter;", "", "composer", "Landroidx/compose/runtime/LinkComposer;", "changeList", "Landroidx/compose/runtime/composer/linkbuffer/changelist/ChangeList;", "<init>", "(Landroidx/compose/runtime/LinkComposer;Landroidx/compose/runtime/composer/linkbuffer/changelist/ChangeList;)V", "getChangeList", "()Landroidx/compose/runtime/composer/linkbuffer/changelist/ChangeList;", "setChangeList", "(Landroidx/compose/runtime/composer/linkbuffer/changelist/ChangeList;)V", "reader", "Landroidx/compose/runtime/composer/linkbuffer/SlotTableReader;", "getReader", "()Landroidx/compose/runtime/composer/linkbuffer/SlotTableReader;", "implicitRootStart", "", "getImplicitRootStart", "()Z", "setImplicitRootStart", "(Z)V", "pendingUps", "", "pendingDownNodes", "Landroidx/compose/runtime/Stack;", "Ljava/util/ArrayList;", "removeFromNodeIndex", "moveFromNodeIndex", "moveToNodeIndex", "moveCount", "addressMode", "Landroidx/compose/runtime/composer/linkbuffer/changelist/ComposerChangeListWriterAddressMode;", "getAddressMode$runtime", "()Landroidx/compose/runtime/composer/linkbuffer/changelist/ComposerChangeListWriterAddressMode;", "setAddressMode$runtime", "(Landroidx/compose/runtime/composer/linkbuffer/changelist/ComposerChangeListWriterAddressMode;)V", "editorCurrentPosition", "", "Landroidx/compose/runtime/composer/linkbuffer/GroupHandle;", "pushApplierOperationPreamble", "", "pushSlotOperationPreamble", "pushSlotOperationPreambleUnconditionally", "startComposition", "seekTo", "handle", "resetRelativeAddressing", "isInAnchorMode", "inAnchorMode", "block", "Lkotlin/Function0;", "inRelativeAddressMode", "relativeStart", "inMode", "newMode", "withChangeList", "newChangeList", "withoutImplicitRootStart", "remember", "value", "Landroidx/compose/runtime/RememberObserverHolder;", "rememberPausingScope", "scope", "Landroidx/compose/runtime/RecomposeScopeImpl;", "startResumingScope", "endResumingScope", "updateRememberOrdering", "holder", "Landroidx/compose/runtime/LinkRememberObserverHolder;", "after", "Landroidx/compose/runtime/composer/linkbuffer/LinkAnchor;", "updateValue", "slotIndex", "appendValue", "removeTailGroupsAndValues", "firstTailGroupToRemove", "Landroidx/compose/runtime/composer/linkbuffer/GroupAddress;", "count", "resetSlots", "updateAuxData", "data", "removeGroup", "insertSlots", "sourceTable", "Landroidx/compose/runtime/composer/linkbuffer/SlotTable;", "source", "fixups", "Landroidx/compose/runtime/composer/linkbuffer/changelist/FixupList;", "moveGroup", TypedValues.CycleType.S_WAVE_OFFSET, "endCompositionScope", "action", "Lkotlin/Function1;", "Landroidx/compose/runtime/Composition;", "composition", "useNode", "node", "updateNode", "T", "V", "Lkotlin/Function2;", "Lkotlin/ExtensionFunctionType;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)V", "removeNode", "nodeIndex", "moveNode", "fromNodeIndex", "toNodeIndex", "endNodeMovement", "endNodeMovementAndDeleteNode", "group", "realizeNodeMovementOperations", "realizeRemoveNode", "removeCount", "realizeMoveNode", TypedValues.TransitionType.S_TO, TypedValues.TransitionType.S_FROM, "moveUp", "moveDown", "pushPendingUpsAndDowns", "sideEffect", "effect", "determineMovableContentNodeIndex", "effectiveNodeIndexOut", "Landroidx/compose/runtime/internal/IntRef;", "copyNodesToNewAnchorLocation", "nodes", "", "effectiveNodeIndex", "copySlotTableToAnchorLocation", "resolvedState", "Landroidx/compose/runtime/MovableContentState;", "parentContext", "Landroidx/compose/runtime/CompositionContext;", "Landroidx/compose/runtime/MovableContentStateReference;", "releaseMovableGroup", "Landroidx/compose/runtime/ControlledComposition;", TypedValues.Custom.S_REFERENCE, "endMovableContentPlacement", "disposeResolvedMovableState", "includeOperationsIn", "other", "finalizeComposition", "resetTransientState", "deactivateCurrentGroup", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class ComposerChangeListWriter {
    public static final int $stable = 8;
    private ChangeList changeList;
    private final LinkComposer composer;
    private int pendingUps;
    private boolean implicitRootStart = true;
    private final ArrayList<Object> pendingDownNodes = Stack.m4418constructorimpl$default(null, 1, null);
    private int removeFromNodeIndex = -1;
    private int moveFromNodeIndex = -1;
    private int moveToNodeIndex = -1;
    private int moveCount = -1;
    private ComposerChangeListWriterAddressMode addressMode = ComposerChangeListWriterAddressMode.AbsoluteAddressing;
    private long editorCurrentPosition = -1;

    /* JADX INFO: compiled from: ComposerChangeListWriter.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ComposerChangeListWriterAddressMode.values().length];
            try {
                iArr[ComposerChangeListWriterAddressMode.AbsoluteAddressing.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[ComposerChangeListWriterAddressMode.AnchorAddressing.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[ComposerChangeListWriterAddressMode.RelativeAddressing.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public ComposerChangeListWriter(LinkComposer composer, ChangeList changeList) {
        this.composer = composer;
        this.changeList = changeList;
    }

    public final ChangeList getChangeList() {
        return this.changeList;
    }

    public final void setChangeList(ChangeList changeList) {
        this.changeList = changeList;
    }

    private final SlotTableReader getReader() {
        return this.composer.getReader();
    }

    public final boolean getImplicitRootStart() {
        return this.implicitRootStart;
    }

    public final void setImplicitRootStart(boolean z) {
        this.implicitRootStart = z;
    }

    /* JADX INFO: renamed from: getAddressMode$runtime, reason: from getter */
    public final ComposerChangeListWriterAddressMode getAddressMode() {
        return this.addressMode;
    }

    public final void setAddressMode$runtime(ComposerChangeListWriterAddressMode composerChangeListWriterAddressMode) {
        this.addressMode = composerChangeListWriterAddressMode;
    }

    private final void pushApplierOperationPreamble() {
        pushPendingUpsAndDowns();
    }

    private final void pushSlotOperationPreamble() {
        long readerLocation = getReader().handle();
        if (this.editorCurrentPosition != readerLocation) {
            seekTo$default(this, readerLocation, false, 2, null);
        }
    }

    private final void pushSlotOperationPreambleUnconditionally() {
        seekTo$default(this, getReader().handle(), false, 2, null);
    }

    public final void startComposition() {
        Stack.m4416clearimpl(this.pendingDownNodes);
        this.pendingUps = 0;
        this.removeFromNodeIndex = -1;
        this.moveFromNodeIndex = -1;
        this.moveToNodeIndex = -1;
        this.addressMode = ComposerChangeListWriterAddressMode.AbsoluteAddressing;
        this.editorCurrentPosition = -1L;
    }

    public static /* synthetic */ void seekTo$default(ComposerChangeListWriter composerChangeListWriter, long j, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        composerChangeListWriter.seekTo(j, z);
    }

    public final void seekTo(long handle, boolean resetRelativeAddressing) {
        switch (WhenMappings.$EnumSwitchMapping$0[this.addressMode.ordinal()]) {
            case 1:
                this.changeList.pushSeekToGroupHandle(handle);
                break;
            case 2:
                this.changeList.pushSeekToAnchor(getReader().getTable().getAddressSpace(), handle);
                break;
            case 3:
                int groupAddress = GroupHandleKt.getGroup(handle);
                int parentOfHandle = groupAddress == -1 ? GroupHandleKt.getContext(handle) : getReader().parentOf(groupAddress);
                boolean value$iv = parentOfHandle == GroupHandleKt.getGroup(this.editorCurrentPosition);
                if (!value$iv) {
                    ComposerKt.composeImmediateRuntimeError("Relative addressing only supports navigating to a child of the current group");
                }
                this.changeList.pushStartGroup();
                int currentChild = getReader().firstChildOf(parentOfHandle);
                while (currentChild != groupAddress) {
                    this.changeList.pushSkipGroup();
                    currentChild = getReader().nextSiblingOf(currentChild);
                }
                if (resetRelativeAddressing) {
                    this.addressMode = ComposerChangeListWriterAddressMode.AbsoluteAddressing;
                }
                break;
            default:
                throw new NoWhenBranchMatchedException();
        }
        this.editorCurrentPosition = handle;
    }

    public final boolean isInAnchorMode() {
        return this.addressMode == ComposerChangeListWriterAddressMode.AnchorAddressing;
    }

    public final void inAnchorMode(Function0<Unit> block) {
        this.editorCurrentPosition = -1L;
        ComposerChangeListWriterAddressMode newMode$iv = ComposerChangeListWriterAddressMode.AnchorAddressing;
        ComposerChangeListWriterAddressMode previousMode$iv = getAddressMode();
        long previousCurrentPosition$iv = this.editorCurrentPosition;
        setAddressMode$runtime(newMode$iv);
        try {
            block.invoke();
        } finally {
            setAddressMode$runtime(previousMode$iv);
            this.editorCurrentPosition = previousMode$iv == ComposerChangeListWriterAddressMode.RelativeAddressing ? previousCurrentPosition$iv : -1L;
        }
    }

    public final void inRelativeAddressMode(long relativeStart, Function0<Unit> block) {
        this.editorCurrentPosition = relativeStart;
        ComposerChangeListWriterAddressMode newMode$iv = ComposerChangeListWriterAddressMode.RelativeAddressing;
        ComposerChangeListWriterAddressMode previousMode$iv = getAddressMode();
        long previousCurrentPosition$iv = this.editorCurrentPosition;
        setAddressMode$runtime(newMode$iv);
        try {
            block.invoke();
        } finally {
            setAddressMode$runtime(previousMode$iv);
            this.editorCurrentPosition = previousMode$iv == ComposerChangeListWriterAddressMode.RelativeAddressing ? previousCurrentPosition$iv : -1L;
        }
    }

    private final void inMode(ComposerChangeListWriterAddressMode newMode, Function0<Unit> block) {
        ComposerChangeListWriterAddressMode previousMode = getAddressMode();
        long previousCurrentPosition = this.editorCurrentPosition;
        setAddressMode$runtime(newMode);
        try {
            block.invoke();
        } finally {
            setAddressMode$runtime(previousMode);
            this.editorCurrentPosition = previousMode == ComposerChangeListWriterAddressMode.RelativeAddressing ? previousCurrentPosition : -1L;
        }
    }

    public final void withChangeList(ChangeList newChangeList, Function0<Unit> block) {
        ChangeList previousChangeList = getChangeList();
        try {
            setChangeList(newChangeList);
            block.invoke();
        } finally {
            setChangeList(previousChangeList);
        }
    }

    public final void withoutImplicitRootStart(Function0<Unit> block) {
        boolean previousImplicitRootStart = getImplicitRootStart();
        try {
            setImplicitRootStart(false);
            block.invoke();
        } finally {
            setImplicitRootStart(previousImplicitRootStart);
        }
    }

    public final void remember(RememberObserverHolder value) {
        pushSlotOperationPreamble();
        this.changeList.pushRemember(value);
    }

    public final void rememberPausingScope(RecomposeScopeImpl scope) {
        this.changeList.pushRememberPausingScope(scope);
    }

    public final void startResumingScope(RecomposeScopeImpl scope) {
        this.changeList.pushStartResumingScope(scope);
    }

    public final void endResumingScope(RecomposeScopeImpl scope) {
        this.changeList.pushEndResumingScope(scope);
    }

    public final void updateRememberOrdering(LinkRememberObserverHolder holder, LinkAnchor after) {
        if (!Intrinsics.areEqual(holder.getAfter(), after)) {
            this.changeList.pushUpdateRememberObserverHolderOrdering(holder, after);
        }
    }

    public final void updateValue(int slotIndex, Object value) {
        pushSlotOperationPreamble();
        this.changeList.pushUpdateRelativeValue(slotIndex, value);
    }

    public final void appendValue(Object value) {
        pushSlotOperationPreamble();
        this.changeList.pushAppendValue(value);
    }

    public final void removeTailGroupsAndValues(int firstTailGroupToRemove, int count) {
        if (firstTailGroupToRemove >= 0 || count > 0) {
            pushSlotOperationPreamble();
            this.changeList.pushRemoveTailGroupsAndValues(firstTailGroupToRemove, count);
        }
    }

    public final void resetSlots() {
        this.changeList.pushResetSlots();
        this.editorCurrentPosition = -1L;
    }

    public final void updateAuxData(Object data) {
        pushSlotOperationPreamble();
        this.changeList.pushUpdateAuxData(data);
    }

    public final void removeGroup() {
        pushSlotOperationPreamble();
        this.changeList.pushRemoveGroup();
    }

    public final void insertSlots(SlotTable sourceTable, long source) {
        boolean value$iv = source != -1;
        if (!value$iv) {
            ComposerKt.composeImmediateRuntimeError("Tried moving from an unspecified position");
        }
        pushPendingUpsAndDowns();
        pushSlotOperationPreamble();
        realizeNodeMovementOperations();
        this.changeList.pushInsertSlots(sourceTable, source);
    }

    public final void insertSlots(SlotTable sourceTable, long source, FixupList fixups) {
        boolean value$iv = source != -1;
        if (!value$iv) {
            ComposerKt.composeImmediateRuntimeError("Tried moving from an unspecified position");
        }
        pushPendingUpsAndDowns();
        pushSlotOperationPreamble();
        realizeNodeMovementOperations();
        this.changeList.pushInsertSlots(sourceTable, source, fixups);
    }

    public final void moveGroup(int i) {
        boolean value$iv = i >= 0;
        if (!value$iv) {
            ComposerKt.composeImmediateRuntimeError("Offset must not be negative");
        }
        pushSlotOperationPreambleUnconditionally();
        this.changeList.pushMoveGroup(i);
        this.editorCurrentPosition = -1L;
    }

    public final void endCompositionScope(Function1<? super Composition, Unit> action, Composition composition) {
        this.changeList.pushEndCompositionScope(action, composition);
    }

    public final void useNode(Object node) {
        pushApplierOperationPreamble();
        this.changeList.pushUseNode(node);
    }

    public final <T, V> void updateNode(V value, Function2<? super T, ? super V, Unit> block) {
        pushApplierOperationPreamble();
        this.changeList.pushUpdateNode(value, block);
    }

    public final void removeNode(int nodeIndex, int count) {
        if (count > 0) {
            if (this.removeFromNodeIndex == nodeIndex) {
                this.moveCount += count;
                return;
            }
            realizeNodeMovementOperations();
            this.removeFromNodeIndex = nodeIndex;
            this.moveCount = count;
        }
    }

    public final void moveNode(int fromNodeIndex, int toNodeIndex, int count) {
        if (count > 0) {
            if (this.moveCount > 0 && this.moveFromNodeIndex == fromNodeIndex && this.moveToNodeIndex == toNodeIndex) {
                this.moveCount += count;
                return;
            }
            realizeNodeMovementOperations();
            this.moveToNodeIndex = toNodeIndex;
            this.moveFromNodeIndex = fromNodeIndex;
            this.moveCount = count;
        }
    }

    public final void endNodeMovement() {
        realizeNodeMovementOperations();
    }

    public final void endNodeMovementAndDeleteNode(int nodeIndex, int group) {
        endNodeMovement();
        pushPendingUpsAndDowns();
        int flags$iv = getReader().flagsOf(group);
        removeNode(nodeIndex, (8388608 & flags$iv) == 8388608 ? 1 : flags$iv & GroupFlagsSpec.CHILD_NODE_COUNT_MASK);
    }

    private final void realizeNodeMovementOperations() {
        if (this.moveCount > 0) {
            if (this.removeFromNodeIndex >= 0) {
                realizeRemoveNode(this.removeFromNodeIndex, this.moveCount);
                this.removeFromNodeIndex = -1;
            } else {
                realizeMoveNode(this.moveToNodeIndex, this.moveFromNodeIndex, this.moveCount);
                this.moveToNodeIndex = -1;
                this.moveFromNodeIndex = -1;
            }
            this.moveCount = 0;
        }
    }

    private final void realizeRemoveNode(int nodeIndex, int removeCount) {
        pushApplierOperationPreamble();
        this.changeList.pushRemoveNode(nodeIndex, removeCount);
    }

    private final void realizeMoveNode(int i, int i2, int count) {
        pushApplierOperationPreamble();
        this.changeList.pushMoveNode(i, i2, count);
    }

    public final void moveUp() {
        realizeNodeMovementOperations();
        if (Stack.m4424isNotEmptyimpl(this.pendingDownNodes)) {
            Stack.m4427popimpl(this.pendingDownNodes);
            return;
        }
        int i = this.pendingUps;
        this.pendingUps = i + 1;
        Integer.valueOf(i);
    }

    public final void moveDown(Object node) {
        realizeNodeMovementOperations();
        Stack.m4428pushimpl(this.pendingDownNodes, node);
    }

    private final void pushPendingUpsAndDowns() {
        if (this.pendingUps > 0) {
            this.changeList.pushUps(this.pendingUps);
            this.pendingUps = 0;
        }
        if (Stack.m4424isNotEmptyimpl(this.pendingDownNodes)) {
            this.changeList.pushDowns(Stack.m4429toArrayimpl(this.pendingDownNodes));
            Stack.m4416clearimpl(this.pendingDownNodes);
        }
    }

    public final void sideEffect(Function0<Unit> effect) {
        this.changeList.pushSideEffect(effect);
    }

    public final void determineMovableContentNodeIndex(IntRef effectiveNodeIndexOut, long handle) {
        pushPendingUpsAndDowns();
        this.changeList.pushDetermineMovableContentNodeIndex(effectiveNodeIndexOut, handle);
        this.editorCurrentPosition = handle;
    }

    public final void copyNodesToNewAnchorLocation(List<? extends Object> nodes, IntRef effectiveNodeIndex) {
        this.changeList.pushCopyNodesToNewAnchorLocation(nodes, effectiveNodeIndex);
    }

    public final void copySlotTableToAnchorLocation(MovableContentState resolvedState, CompositionContext parentContext, MovableContentStateReference movableContentStateReference, MovableContentStateReference movableContentStateReference2) {
        this.changeList.pushCopySlotTableToAnchorLocation(resolvedState, parentContext, movableContentStateReference, movableContentStateReference2);
    }

    public final void releaseMovableGroup(ControlledComposition composition, CompositionContext parentContext, MovableContentStateReference movableContentStateReference) {
        this.changeList.pushReleaseMovableGroup(composition, parentContext, movableContentStateReference);
        this.editorCurrentPosition = -1L;
    }

    public final void endMovableContentPlacement() {
        this.changeList.pushEndMovableContentPlacement();
        this.pendingUps = 0;
    }

    public final void disposeResolvedMovableState(MovableContentState resolvedState) {
        if (resolvedState != null) {
            this.changeList.pushDisposeDisposeMovableContentState(resolvedState);
        }
    }

    public static /* synthetic */ void includeOperationsIn$default(ComposerChangeListWriter composerChangeListWriter, ChangeList changeList, IntRef intRef, int i, Object obj) {
        if ((i & 2) != 0) {
            intRef = null;
        }
        composerChangeListWriter.includeOperationsIn(changeList, intRef);
    }

    public final void includeOperationsIn(ChangeList other, IntRef effectiveNodeIndex) {
        this.changeList.pushExecuteOperationsIn(other, effectiveNodeIndex);
    }

    public final void finalizeComposition() {
        pushPendingUpsAndDowns();
        this.changeList.pushClearAllRecompositionRequiredGroups();
        this.editorCurrentPosition = -1L;
    }

    public final void resetTransientState() {
        this.implicitRootStart = true;
        this.pendingUps = 0;
        Stack.m4416clearimpl(this.pendingDownNodes);
        this.removeFromNodeIndex = -1;
        this.moveFromNodeIndex = -1;
        this.moveToNodeIndex = -1;
        this.moveCount = -1;
        this.editorCurrentPosition = -1L;
    }

    public final void deactivateCurrentGroup() {
        pushSlotOperationPreamble();
        this.changeList.pushDeactivateGroup();
    }
}
