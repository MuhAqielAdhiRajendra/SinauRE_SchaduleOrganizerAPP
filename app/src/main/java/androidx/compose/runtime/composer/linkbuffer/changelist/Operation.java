package androidx.compose.runtime.composer.linkbuffer.changelist;

import androidx.autofill.HintConstants;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposeNodeLifecycleCallback;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.Composition;
import androidx.compose.runtime.CompositionContext;
import androidx.compose.runtime.ControlledComposition;
import androidx.compose.runtime.LinkRememberObserverHolder;
import androidx.compose.runtime.MovableContentState;
import androidx.compose.runtime.MovableContentStateReference;
import androidx.compose.runtime.OffsetApplier;
import androidx.compose.runtime.RecomposeScopeImpl;
import androidx.compose.runtime.RecomposeScopeOwner;
import androidx.compose.runtime.RememberObserverHolder;
import androidx.compose.runtime.composer.RememberManager;
import androidx.compose.runtime.composer.gapbuffer.changelist.OperationErrorContext;
import androidx.compose.runtime.composer.linkbuffer.AnchorHandle;
import androidx.compose.runtime.composer.linkbuffer.GroupHandleKt;
import androidx.compose.runtime.composer.linkbuffer.LinkAnchor;
import androidx.compose.runtime.composer.linkbuffer.SlotTable;
import androidx.compose.runtime.composer.linkbuffer.SlotTableEditor;
import androidx.compose.runtime.composer.linkbuffer.SlotTableEditorKt;
import androidx.compose.runtime.composer.linkbuffer.SlotTableKt;
import androidx.compose.runtime.composer.linkbuffer.changelist.Operation;
import androidx.compose.runtime.internal.IntRef;
import androidx.compose.runtime.internal.System_jvmKt;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.util.ArrayList;
import java.util.List;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.UInt;
import kotlin.Unit;
import kotlin.jvm.JvmInline;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: Operation.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\u0084\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b-\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b1\u0018\u00002\u00020\u0001:*()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQB'\b\u0004\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ0\u0010\u0011\u001a\u00020\u0012*\u00020\u00132\n\u0010\u0014\u001a\u0006\u0012\u0002\b\u00030\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bJ\u0018\u0010\u001c\u001a\u00060\u001dj\u0002`\u001e*\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u0017H\u0014J2\u0010\u001f\u001a\u00020\u0012*\u00020\u00132\n\u0010\u0014\u001a\u0006\u0012\u0002\b\u00030\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH$J\u0014\u0010 \u001a\u00020\u000e2\n\u0010!\u001a\u00060\u0003j\u0002`\"H\u0016J\u001b\u0010#\u001a\u00020\u000e2\n\u0010!\u001a\u0006\u0012\u0002\b\u00030$H\u0016¢\u0006\u0004\b%\u0010&J\b\u0010'\u001a\u00020\u000eH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\fR\u0011\u0010\r\u001a\u00020\u000e8F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010\u0082\u0001)RSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz¨\u0006{"}, d2 = {"Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation;", "", "ints", "", "objects", "isExternallyVisible", "", "<init>", "(IIZ)V", "getInts", "()I", "getObjects", "()Z", HintConstants.AUTOFILL_HINT_NAME, "", "getName", "()Ljava/lang/String;", "executeWithComposeStackTrace", "", "Landroidx/compose/runtime/composer/linkbuffer/changelist/OperationArgContainer;", "applier", "Landroidx/compose/runtime/Applier;", "slots", "Landroidx/compose/runtime/composer/linkbuffer/SlotTableEditor;", "rememberManager", "Landroidx/compose/runtime/composer/RememberManager;", "errorContext", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationErrorContext;", "getGroupHandle", "", "Landroidx/compose/runtime/composer/linkbuffer/GroupHandle;", "execute", "intParamName", "parameter", "Landroidx/compose/runtime/composer/linkbuffer/changelist/IntParameter;", "objectParamName", "Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation$ObjectParameter;", "objectParamName-gvac4VY", "(I)Ljava/lang/String;", "toString", "ObjectParameter", "Ups", "Downs", "SeekToAnchor", "SeekToGroupHandle", "StartGroup", "SkipGroup", "SideEffect", "Remember", "RememberPausingScope", "StartResumingScope", "EndResumingScope", "AppendValue", "RemoveTailGroupsAndValues", "UpdateValue", "UpdateRememberObserverHolderOrdering", "UpdateValueRelative", "UpdateAuxData", "RemoveGroup", "MoveGroup", "ClearAllRecompositionRequired", "EndCompositionScope", "UseCurrentNode", "UpdateNode", "RemoveNode", "MoveNode", "InsertSlots", "InsertSlotsWithFixups", "InsertNodeFixup", "InsertNodeFixupByAnchor", "PostInsertNodeFixup", "PostInsertNodeFixupByAnchor", "DeactivateGroup", "ResetSlots", "DetermineMovableContentNodeIndex", "CopyNodesToNewAnchorLocation", "EndMovableContentPlacement", "CopySlotTableToHandleLocation", "ReleaseMovableGroup", "DisposeMovableContentState", "ApplyChangeList", "TestOperation", "Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation$AppendValue;", "Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation$ApplyChangeList;", "Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation$ClearAllRecompositionRequired;", "Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation$CopyNodesToNewAnchorLocation;", "Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation$CopySlotTableToHandleLocation;", "Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation$DeactivateGroup;", "Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation$DetermineMovableContentNodeIndex;", "Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation$DisposeMovableContentState;", "Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation$Downs;", "Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation$EndCompositionScope;", "Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation$EndMovableContentPlacement;", "Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation$EndResumingScope;", "Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation$InsertNodeFixup;", "Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation$InsertNodeFixupByAnchor;", "Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation$InsertSlots;", "Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation$InsertSlotsWithFixups;", "Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation$MoveGroup;", "Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation$MoveNode;", "Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation$PostInsertNodeFixup;", "Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation$PostInsertNodeFixupByAnchor;", "Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation$ReleaseMovableGroup;", "Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation$Remember;", "Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation$RememberPausingScope;", "Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation$RemoveGroup;", "Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation$RemoveNode;", "Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation$RemoveTailGroupsAndValues;", "Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation$ResetSlots;", "Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation$SeekToAnchor;", "Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation$SeekToGroupHandle;", "Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation$SideEffect;", "Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation$SkipGroup;", "Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation$StartGroup;", "Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation$StartResumingScope;", "Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation$TestOperation;", "Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation$UpdateAuxData;", "Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation$UpdateNode;", "Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation$UpdateRememberObserverHolderOrdering;", "Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation$UpdateValue;", "Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation$UpdateValueRelative;", "Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation$Ups;", "Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation$UseCurrentNode;", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
public abstract class Operation {
    public static final int $stable = 0;
    private final int ints;
    private final boolean isExternallyVisible;
    private final int objects;

    public /* synthetic */ Operation(int i, int i2, boolean z, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, i2, z);
    }

    protected abstract void execute(OperationArgContainer operationArgContainer, Applier<?> applier, SlotTableEditor slotTableEditor, RememberManager rememberManager, OperationErrorContext operationErrorContext);

    private Operation(int ints, int objects, boolean isExternallyVisible) {
        this.ints = ints;
        this.objects = objects;
        this.isExternallyVisible = isExternallyVisible;
    }

    public /* synthetic */ Operation(int i, int i2, boolean z, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? 0 : i, (i3 & 2) != 0 ? 0 : i2, (i3 & 4) != 0 ? true : z, null);
    }

    public final int getInts() {
        return this.ints;
    }

    public final int getObjects() {
        return this.objects;
    }

    /* JADX INFO: renamed from: isExternallyVisible, reason: from getter */
    public final boolean getIsExternallyVisible() {
        return this.isExternallyVisible;
    }

    public final String getName() {
        String simpleName = Reflection.getOrCreateKotlinClass(getClass()).getSimpleName();
        return simpleName == null ? "" : simpleName;
    }

    public final void executeWithComposeStackTrace(OperationArgContainer $this$executeWithComposeStackTrace, Applier<?> applier, SlotTableEditor slots, RememberManager rememberManager, OperationErrorContext errorContext) throws Throwable {
        long location$iv = getGroupHandle($this$executeWithComposeStackTrace, slots);
        try {
            execute($this$executeWithComposeStackTrace, applier, slots, rememberManager, errorContext);
        } catch (Throwable e$iv) {
            throw OperationKt.attachComposeStackTrace(e$iv, errorContext, slots, location$iv);
        }
    }

    protected long getGroupHandle(OperationArgContainer $this$getGroupHandle, SlotTableEditor slots) {
        return -1L;
    }

    public String intParamName(int parameter) {
        return "IntParameter(" + parameter + ')';
    }

    /* JADX INFO: renamed from: objectParamName-gvac4VY, reason: not valid java name */
    public String mo4585objectParamNamegvac4VY(int parameter) {
        return "ObjectParameter(" + parameter + ')';
    }

    public String toString() {
        return getName();
    }

    /* JADX INFO: compiled from: Operation.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0087@\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0014\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0002HÖ\u0083\u0004J\n\u0010\f\u001a\u00020\u0004HÖ\u0081\u0004J\n\u0010\r\u001a\u00020\u000eHÖ\u0081\u0004R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b\u0088\u0001\u0003¨\u0006\u000f"}, d2 = {"Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation$ObjectParameter;", "T", "", TypedValues.CycleType.S_WAVE_OFFSET, "", "constructor-impl", "(I)I", "getOffset", "()I", "equals", "", "other", "hashCode", "toString", "", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    @JvmInline
    public static final class ObjectParameter<T> {
        private final int offset;

        /* JADX INFO: renamed from: box-impl, reason: not valid java name */
        public static final /* synthetic */ ObjectParameter m4607boximpl(int i) {
            return new ObjectParameter(i);
        }

        /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
        public static <T> int m4608constructorimpl(int i) {
            return i;
        }

        /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
        public static boolean m4609equalsimpl(int i, Object obj) {
            return (obj instanceof ObjectParameter) && i == ((ObjectParameter) obj).m4613unboximpl();
        }

        /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
        public static final boolean m4610equalsimpl0(int i, int i2) {
            return i == i2;
        }

        /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
        public static int m4611hashCodeimpl(int i) {
            return Integer.hashCode(i);
        }

        /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
        public static String m4612toStringimpl(int i) {
            return "ObjectParameter(offset=" + i + ')';
        }

        public boolean equals(Object other) {
            return m4609equalsimpl(this.offset, other);
        }

        public int hashCode() {
            return m4611hashCodeimpl(this.offset);
        }

        public String toString() {
            return m4612toStringimpl(this.offset);
        }

        /* JADX INFO: renamed from: unbox-impl, reason: not valid java name */
        public final /* synthetic */ int m4613unboximpl() {
            return this.offset;
        }

        private /* synthetic */ ObjectParameter(int offset) {
            this.offset = offset;
        }

        public final int getOffset() {
            return this.offset;
        }
    }

    /* JADX INFO: compiled from: Operation.kt */
    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\b\u001a\u00020\t2\n\u0010\n\u001a\u00060\u0005j\u0002`\u000bH\u0016J2\u0010\f\u001a\u00020\r*\u00020\u000e2\n\u0010\u000f\u001a\u0006\u0012\u0002\b\u00030\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0014R\u0012\u0010\u0004\u001a\u00020\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0017"}, d2 = {"Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation$Ups;", "Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation;", "<init>", "()V", "Count", "", "getCount", "()I", "intParamName", "", "parameter", "Landroidx/compose/runtime/composer/linkbuffer/changelist/IntParameter;", "execute", "", "Landroidx/compose/runtime/composer/linkbuffer/changelist/OperationArgContainer;", "applier", "Landroidx/compose/runtime/Applier;", "slots", "Landroidx/compose/runtime/composer/linkbuffer/SlotTableEditor;", "rememberManager", "Landroidx/compose/runtime/composer/RememberManager;", "errorContext", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationErrorContext;", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Ups extends Operation {
        public static final int $stable = 0;
        public static final Ups INSTANCE = new Ups();

        private Ups() {
            super(1, 0, false, 6, null);
        }

        public final int getCount() {
            return 0;
        }

        @Override // androidx.compose.runtime.composer.linkbuffer.changelist.Operation
        public String intParamName(int parameter) {
            return parameter == 0 ? "count" : super.intParamName(parameter);
        }

        @Override // androidx.compose.runtime.composer.linkbuffer.changelist.Operation
        protected void execute(OperationArgContainer $this$execute, Applier<?> applier, SlotTableEditor slots, RememberManager rememberManager, OperationErrorContext errorContext) {
            int i = $this$execute.getInt(0);
            for (int i2 = 0; i2 < i; i2++) {
                applier.up();
            }
        }
    }

    /* JADX INFO: compiled from: Operation.kt */
    @Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\n\u001a\u00020\u000b2\n\u0010\f\u001a\u0006\u0012\u0002\b\u00030\u0005H\u0016¢\u0006\u0004\b\r\u0010\u000eJ2\u0010\u000f\u001a\u00020\u0010*\u00020\u00112\n\u0010\u0012\u001a\u0006\u0012\u0002\b\u00030\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0014R \u0010\u0004\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00060\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\u001a"}, d2 = {"Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation$Downs;", "Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation;", "<init>", "()V", "Nodes", "Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation$ObjectParameter;", "", "", "getNodes-s5KIjrA", "()I", "objectParamName", "", "parameter", "objectParamName-gvac4VY", "(I)Ljava/lang/String;", "execute", "", "Landroidx/compose/runtime/composer/linkbuffer/changelist/OperationArgContainer;", "applier", "Landroidx/compose/runtime/Applier;", "slots", "Landroidx/compose/runtime/composer/linkbuffer/SlotTableEditor;", "rememberManager", "Landroidx/compose/runtime/composer/RememberManager;", "errorContext", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationErrorContext;", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Downs extends Operation {
        public static final int $stable = 0;
        public static final Downs INSTANCE = new Downs();

        private Downs() {
            super(0, 1, false, 5, null);
        }

        /* JADX INFO: renamed from: getNodes-s5KIjrA, reason: not valid java name */
        public final int m4597getNodess5KIjrA() {
            return ObjectParameter.m4608constructorimpl(0);
        }

        @Override // androidx.compose.runtime.composer.linkbuffer.changelist.Operation
        /* JADX INFO: renamed from: objectParamName-gvac4VY */
        public String mo4585objectParamNamegvac4VY(int parameter) {
            return ObjectParameter.m4610equalsimpl0(parameter, ObjectParameter.m4608constructorimpl(0)) ? "nodes" : super.mo4585objectParamNamegvac4VY(parameter);
        }

        @Override // androidx.compose.runtime.composer.linkbuffer.changelist.Operation
        protected void execute(OperationArgContainer $this$execute, Applier<?> applier, SlotTableEditor slots, RememberManager rememberManager, OperationErrorContext errorContext) {
            Intrinsics.checkNotNull(applier, "null cannot be cast to non-null type androidx.compose.runtime.Applier<kotlin.Any?>");
            Object[] nodes = (Object[]) $this$execute.mo4630getObjectgvac4VY(ObjectParameter.m4608constructorimpl(0));
            for (Object obj : nodes) {
                applier.down(obj);
            }
        }
    }

    /* JADX INFO: compiled from: Operation.kt */
    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\t\u001a\u00020\n2\n\u0010\u000b\u001a\u0006\u0012\u0002\b\u00030\u0005H\u0016¢\u0006\u0004\b\f\u0010\rJ2\u0010\u000e\u001a\u00020\u000f*\u00020\u00102\n\u0010\u0011\u001a\u0006\u0012\u0002\b\u00030\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0014R\u0018\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\u0019"}, d2 = {"Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation$SeekToAnchor;", "Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation;", "<init>", "()V", "AnchorHandle", "Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation$ObjectParameter;", "Landroidx/compose/runtime/composer/linkbuffer/AnchorHandle;", "getAnchorHandle-s5KIjrA", "()I", "objectParamName", "", "parameter", "objectParamName-gvac4VY", "(I)Ljava/lang/String;", "execute", "", "Landroidx/compose/runtime/composer/linkbuffer/changelist/OperationArgContainer;", "applier", "Landroidx/compose/runtime/Applier;", "slots", "Landroidx/compose/runtime/composer/linkbuffer/SlotTableEditor;", "rememberManager", "Landroidx/compose/runtime/composer/RememberManager;", "errorContext", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationErrorContext;", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class SeekToAnchor extends Operation {
        public static final int $stable = 0;
        public static final SeekToAnchor INSTANCE = new SeekToAnchor();

        private SeekToAnchor() {
            super(0, 1, false, 1, null);
        }

        /* JADX INFO: renamed from: getAnchorHandle-s5KIjrA, reason: not valid java name */
        public final int m4620getAnchorHandles5KIjrA() {
            return ObjectParameter.m4608constructorimpl(0);
        }

        @Override // androidx.compose.runtime.composer.linkbuffer.changelist.Operation
        /* JADX INFO: renamed from: objectParamName-gvac4VY */
        public String mo4585objectParamNamegvac4VY(int parameter) {
            return ObjectParameter.m4610equalsimpl0(parameter, ObjectParameter.m4608constructorimpl(0)) ? "anchorHandle" : super.mo4585objectParamNamegvac4VY(parameter);
        }

        @Override // androidx.compose.runtime.composer.linkbuffer.changelist.Operation
        protected void execute(OperationArgContainer $this$execute, Applier<?> applier, SlotTableEditor slots, RememberManager rememberManager, OperationErrorContext errorContext) {
            AnchorHandle anchorHandle = (AnchorHandle) $this$execute.mo4630getObjectgvac4VY(ObjectParameter.m4608constructorimpl(0));
            slots.seek(anchorHandle.toHandle());
        }
    }

    /* JADX INFO: compiled from: Operation.kt */
    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\n\u001a\u00020\u000b2\n\u0010\f\u001a\u00060\u0005j\u0002`\rH\u0016J2\u0010\u000e\u001a\u00020\u000f*\u00020\u00102\n\u0010\u0011\u001a\u0006\u0012\u0002\b\u00030\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0014R\u0012\u0010\u0004\u001a\u00020\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0012\u0010\b\u001a\u00020\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\t\u0010\u0007¨\u0006\u0019"}, d2 = {"Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation$SeekToGroupHandle;", "Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation;", "<init>", "()V", "GroupHandleHighBits", "", "getGroupHandleHighBits", "()I", "GroupHandleLowBits", "getGroupHandleLowBits", "intParamName", "", "parameter", "Landroidx/compose/runtime/composer/linkbuffer/changelist/IntParameter;", "execute", "", "Landroidx/compose/runtime/composer/linkbuffer/changelist/OperationArgContainer;", "applier", "Landroidx/compose/runtime/Applier;", "slots", "Landroidx/compose/runtime/composer/linkbuffer/SlotTableEditor;", "rememberManager", "Landroidx/compose/runtime/composer/RememberManager;", "errorContext", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationErrorContext;", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class SeekToGroupHandle extends Operation {
        public static final int $stable = 0;
        public static final SeekToGroupHandle INSTANCE = new SeekToGroupHandle();

        private SeekToGroupHandle() {
            super(2, 0, false, 2, null);
        }

        public final int getGroupHandleHighBits() {
            return 0;
        }

        public final int getGroupHandleLowBits() {
            return 1;
        }

        @Override // androidx.compose.runtime.composer.linkbuffer.changelist.Operation
        public String intParamName(int parameter) {
            return parameter == 0 ? "group[32..63]" : parameter == 1 ? "group[0..31]" : super.intParamName(parameter);
        }

        @Override // androidx.compose.runtime.composer.linkbuffer.changelist.Operation
        protected void execute(OperationArgContainer $this$execute, Applier<?> applier, SlotTableEditor slots, RememberManager rememberManager, OperationErrorContext errorContext) {
            long handle = OperationArgContainerKt.getLong($this$execute, 0, 1);
            slots.seek(handle);
        }
    }

    /* JADX INFO: compiled from: Operation.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J2\u0010\u0004\u001a\u00020\u0005*\u00020\u00062\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0014¨\u0006\u000f"}, d2 = {"Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation$StartGroup;", "Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation;", "<init>", "()V", "execute", "", "Landroidx/compose/runtime/composer/linkbuffer/changelist/OperationArgContainer;", "applier", "Landroidx/compose/runtime/Applier;", "slots", "Landroidx/compose/runtime/composer/linkbuffer/SlotTableEditor;", "rememberManager", "Landroidx/compose/runtime/composer/RememberManager;", "errorContext", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationErrorContext;", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class StartGroup extends Operation {
        public static final int $stable = 0;
        public static final StartGroup INSTANCE = new StartGroup();

        private StartGroup() {
            super(0, 0, false, 7, null);
        }

        @Override // androidx.compose.runtime.composer.linkbuffer.changelist.Operation
        protected void execute(OperationArgContainer $this$execute, Applier<?> applier, SlotTableEditor slots, RememberManager rememberManager, OperationErrorContext errorContext) {
            slots.startGroup();
        }
    }

    /* JADX INFO: compiled from: Operation.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J2\u0010\u0004\u001a\u00020\u0005*\u00020\u00062\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0014¨\u0006\u000f"}, d2 = {"Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation$SkipGroup;", "Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation;", "<init>", "()V", "execute", "", "Landroidx/compose/runtime/composer/linkbuffer/changelist/OperationArgContainer;", "applier", "Landroidx/compose/runtime/Applier;", "slots", "Landroidx/compose/runtime/composer/linkbuffer/SlotTableEditor;", "rememberManager", "Landroidx/compose/runtime/composer/RememberManager;", "errorContext", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationErrorContext;", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class SkipGroup extends Operation {
        public static final int $stable = 0;
        public static final SkipGroup INSTANCE = new SkipGroup();

        private SkipGroup() {
            super(0, 0, false, 7, null);
        }

        @Override // androidx.compose.runtime.composer.linkbuffer.changelist.Operation
        protected void execute(OperationArgContainer $this$execute, Applier<?> applier, SlotTableEditor slots, RememberManager rememberManager, OperationErrorContext errorContext) {
            slots.skipGroup();
        }
    }

    /* JADX INFO: compiled from: Operation.kt */
    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\n\u001a\u00020\u000b2\n\u0010\f\u001a\u0006\u0012\u0002\b\u00030\u0005H\u0016¢\u0006\u0004\b\r\u0010\u000eJ2\u0010\u000f\u001a\u00020\u0007*\u00020\u00102\n\u0010\u0011\u001a\u0006\u0012\u0002\b\u00030\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0014R\u001e\u0010\u0004\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\u0019"}, d2 = {"Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation$SideEffect;", "Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation;", "<init>", "()V", "Effect", "Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation$ObjectParameter;", "Lkotlin/Function0;", "", "getEffect-s5KIjrA", "()I", "objectParamName", "", "parameter", "objectParamName-gvac4VY", "(I)Ljava/lang/String;", "execute", "Landroidx/compose/runtime/composer/linkbuffer/changelist/OperationArgContainer;", "applier", "Landroidx/compose/runtime/Applier;", "slots", "Landroidx/compose/runtime/composer/linkbuffer/SlotTableEditor;", "rememberManager", "Landroidx/compose/runtime/composer/RememberManager;", "errorContext", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationErrorContext;", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class SideEffect extends Operation {
        public static final int $stable = 0;
        public static final SideEffect INSTANCE = new SideEffect();

        private SideEffect() {
            super(0, 1, false, 5, null);
        }

        /* JADX INFO: renamed from: getEffect-s5KIjrA, reason: not valid java name */
        public final int m4621getEffects5KIjrA() {
            return ObjectParameter.m4608constructorimpl(0);
        }

        @Override // androidx.compose.runtime.composer.linkbuffer.changelist.Operation
        /* JADX INFO: renamed from: objectParamName-gvac4VY */
        public String mo4585objectParamNamegvac4VY(int parameter) {
            return ObjectParameter.m4610equalsimpl0(parameter, ObjectParameter.m4608constructorimpl(0)) ? "effect" : super.mo4585objectParamNamegvac4VY(parameter);
        }

        @Override // androidx.compose.runtime.composer.linkbuffer.changelist.Operation
        protected void execute(OperationArgContainer $this$execute, Applier<?> applier, SlotTableEditor slots, RememberManager rememberManager, OperationErrorContext errorContext) {
            rememberManager.sideEffect((Function0) $this$execute.mo4630getObjectgvac4VY(ObjectParameter.m4608constructorimpl(0)));
        }
    }

    /* JADX INFO: compiled from: Operation.kt */
    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\t\u001a\u00020\n2\n\u0010\u000b\u001a\u0006\u0012\u0002\b\u00030\u0005H\u0016¢\u0006\u0004\b\f\u0010\rJ2\u0010\u000e\u001a\u00020\u000f*\u00020\u00102\n\u0010\u0011\u001a\u0006\u0012\u0002\b\u00030\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0014R\u0018\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\u0019"}, d2 = {"Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation$Remember;", "Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation;", "<init>", "()V", "Value", "Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation$ObjectParameter;", "Landroidx/compose/runtime/RememberObserverHolder;", "getValue-s5KIjrA", "()I", "objectParamName", "", "parameter", "objectParamName-gvac4VY", "(I)Ljava/lang/String;", "execute", "", "Landroidx/compose/runtime/composer/linkbuffer/changelist/OperationArgContainer;", "applier", "Landroidx/compose/runtime/Applier;", "slots", "Landroidx/compose/runtime/composer/linkbuffer/SlotTableEditor;", "rememberManager", "Landroidx/compose/runtime/composer/RememberManager;", "errorContext", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationErrorContext;", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Remember extends Operation {
        public static final int $stable = 0;
        public static final Remember INSTANCE = new Remember();

        private Remember() {
            super(0, 1, false, 5, null);
        }

        /* JADX INFO: renamed from: getValue-s5KIjrA, reason: not valid java name */
        public final int m4618getValues5KIjrA() {
            return ObjectParameter.m4608constructorimpl(0);
        }

        @Override // androidx.compose.runtime.composer.linkbuffer.changelist.Operation
        /* JADX INFO: renamed from: objectParamName-gvac4VY */
        public String mo4585objectParamNamegvac4VY(int parameter) {
            return ObjectParameter.m4610equalsimpl0(parameter, ObjectParameter.m4608constructorimpl(0)) ? "value" : super.mo4585objectParamNamegvac4VY(parameter);
        }

        @Override // androidx.compose.runtime.composer.linkbuffer.changelist.Operation
        protected void execute(OperationArgContainer $this$execute, Applier<?> applier, SlotTableEditor slots, RememberManager rememberManager, OperationErrorContext errorContext) {
            rememberManager.remembering((RememberObserverHolder) $this$execute.mo4630getObjectgvac4VY(ObjectParameter.m4608constructorimpl(0)));
        }
    }

    /* JADX INFO: compiled from: Operation.kt */
    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\t\u001a\u00020\n2\n\u0010\u000b\u001a\u0006\u0012\u0002\b\u00030\u0005H\u0016¢\u0006\u0004\b\f\u0010\rJ2\u0010\u000e\u001a\u00020\u000f*\u00020\u00102\n\u0010\u0011\u001a\u0006\u0012\u0002\b\u00030\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0014R\u0018\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\u0019"}, d2 = {"Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation$RememberPausingScope;", "Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation;", "<init>", "()V", "Scope", "Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation$ObjectParameter;", "Landroidx/compose/runtime/RecomposeScopeImpl;", "getScope-s5KIjrA", "()I", "objectParamName", "", "parameter", "objectParamName-gvac4VY", "(I)Ljava/lang/String;", "execute", "", "Landroidx/compose/runtime/composer/linkbuffer/changelist/OperationArgContainer;", "applier", "Landroidx/compose/runtime/Applier;", "slots", "Landroidx/compose/runtime/composer/linkbuffer/SlotTableEditor;", "rememberManager", "Landroidx/compose/runtime/composer/RememberManager;", "errorContext", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationErrorContext;", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class RememberPausingScope extends Operation {
        public static final int $stable = 0;
        public static final RememberPausingScope INSTANCE = new RememberPausingScope();

        private RememberPausingScope() {
            super(0, 1, false, 5, null);
        }

        /* JADX INFO: renamed from: getScope-s5KIjrA, reason: not valid java name */
        public final int m4619getScopes5KIjrA() {
            return ObjectParameter.m4608constructorimpl(0);
        }

        @Override // androidx.compose.runtime.composer.linkbuffer.changelist.Operation
        /* JADX INFO: renamed from: objectParamName-gvac4VY */
        public String mo4585objectParamNamegvac4VY(int parameter) {
            return ObjectParameter.m4610equalsimpl0(parameter, ObjectParameter.m4608constructorimpl(0)) ? "scope" : super.mo4585objectParamNamegvac4VY(parameter);
        }

        @Override // androidx.compose.runtime.composer.linkbuffer.changelist.Operation
        protected void execute(OperationArgContainer $this$execute, Applier<?> applier, SlotTableEditor slots, RememberManager rememberManager, OperationErrorContext errorContext) {
            RecomposeScopeImpl scope = (RecomposeScopeImpl) $this$execute.mo4630getObjectgvac4VY(ObjectParameter.m4608constructorimpl(0));
            rememberManager.rememberPausingScope(scope);
        }
    }

    /* JADX INFO: compiled from: Operation.kt */
    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\t\u001a\u00020\n2\n\u0010\u000b\u001a\u0006\u0012\u0002\b\u00030\u0005H\u0016¢\u0006\u0004\b\f\u0010\rJ2\u0010\u000e\u001a\u00020\u000f*\u00020\u00102\n\u0010\u0011\u001a\u0006\u0012\u0002\b\u00030\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0014R\u0018\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\u0019"}, d2 = {"Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation$StartResumingScope;", "Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation;", "<init>", "()V", "Scope", "Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation$ObjectParameter;", "Landroidx/compose/runtime/RecomposeScopeImpl;", "getScope-s5KIjrA", "()I", "objectParamName", "", "parameter", "objectParamName-gvac4VY", "(I)Ljava/lang/String;", "execute", "", "Landroidx/compose/runtime/composer/linkbuffer/changelist/OperationArgContainer;", "applier", "Landroidx/compose/runtime/Applier;", "slots", "Landroidx/compose/runtime/composer/linkbuffer/SlotTableEditor;", "rememberManager", "Landroidx/compose/runtime/composer/RememberManager;", "errorContext", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationErrorContext;", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class StartResumingScope extends Operation {
        public static final int $stable = 0;
        public static final StartResumingScope INSTANCE = new StartResumingScope();

        private StartResumingScope() {
            super(0, 1, false, 5, null);
        }

        /* JADX INFO: renamed from: getScope-s5KIjrA, reason: not valid java name */
        public final int m4622getScopes5KIjrA() {
            return ObjectParameter.m4608constructorimpl(0);
        }

        @Override // androidx.compose.runtime.composer.linkbuffer.changelist.Operation
        /* JADX INFO: renamed from: objectParamName-gvac4VY */
        public String mo4585objectParamNamegvac4VY(int parameter) {
            return ObjectParameter.m4610equalsimpl0(parameter, ObjectParameter.m4608constructorimpl(0)) ? "scope" : super.mo4585objectParamNamegvac4VY(parameter);
        }

        @Override // androidx.compose.runtime.composer.linkbuffer.changelist.Operation
        protected void execute(OperationArgContainer $this$execute, Applier<?> applier, SlotTableEditor slots, RememberManager rememberManager, OperationErrorContext errorContext) {
            RecomposeScopeImpl scope = (RecomposeScopeImpl) $this$execute.mo4630getObjectgvac4VY(ObjectParameter.m4608constructorimpl(0));
            rememberManager.startResumingScope(scope);
        }
    }

    /* JADX INFO: compiled from: Operation.kt */
    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\t\u001a\u00020\n2\n\u0010\u000b\u001a\u0006\u0012\u0002\b\u00030\u0005H\u0016¢\u0006\u0004\b\f\u0010\rJ2\u0010\u000e\u001a\u00020\u000f*\u00020\u00102\n\u0010\u0011\u001a\u0006\u0012\u0002\b\u00030\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0014R\u0018\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\u0019"}, d2 = {"Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation$EndResumingScope;", "Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation;", "<init>", "()V", "Scope", "Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation$ObjectParameter;", "Landroidx/compose/runtime/RecomposeScopeImpl;", "getScope-s5KIjrA", "()I", "objectParamName", "", "parameter", "objectParamName-gvac4VY", "(I)Ljava/lang/String;", "execute", "", "Landroidx/compose/runtime/composer/linkbuffer/changelist/OperationArgContainer;", "applier", "Landroidx/compose/runtime/Applier;", "slots", "Landroidx/compose/runtime/composer/linkbuffer/SlotTableEditor;", "rememberManager", "Landroidx/compose/runtime/composer/RememberManager;", "errorContext", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationErrorContext;", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class EndResumingScope extends Operation {
        public static final int $stable = 0;
        public static final EndResumingScope INSTANCE = new EndResumingScope();

        private EndResumingScope() {
            super(0, 1, false, 5, null);
        }

        /* JADX INFO: renamed from: getScope-s5KIjrA, reason: not valid java name */
        public final int m4600getScopes5KIjrA() {
            return ObjectParameter.m4608constructorimpl(0);
        }

        @Override // androidx.compose.runtime.composer.linkbuffer.changelist.Operation
        /* JADX INFO: renamed from: objectParamName-gvac4VY */
        public String mo4585objectParamNamegvac4VY(int parameter) {
            return ObjectParameter.m4610equalsimpl0(parameter, ObjectParameter.m4608constructorimpl(0)) ? "scope" : super.mo4585objectParamNamegvac4VY(parameter);
        }

        @Override // androidx.compose.runtime.composer.linkbuffer.changelist.Operation
        protected void execute(OperationArgContainer $this$execute, Applier<?> applier, SlotTableEditor slots, RememberManager rememberManager, OperationErrorContext errorContext) {
            RecomposeScopeImpl scope = (RecomposeScopeImpl) $this$execute.mo4630getObjectgvac4VY(ObjectParameter.m4608constructorimpl(0));
            rememberManager.endResumingScope(scope);
        }
    }

    /* JADX INFO: compiled from: Operation.kt */
    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\t\u001a\u00020\n2\n\u0010\u000b\u001a\u0006\u0012\u0002\b\u00030\u0005H\u0016¢\u0006\u0004\b\f\u0010\rJ2\u0010\u000e\u001a\u00020\u000f*\u00020\u00102\n\u0010\u0011\u001a\u0006\u0012\u0002\b\u00030\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0014R\u001a\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\u0019"}, d2 = {"Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation$AppendValue;", "Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation;", "<init>", "()V", "Value", "Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation$ObjectParameter;", "", "getValue-s5KIjrA", "()I", "objectParamName", "", "parameter", "objectParamName-gvac4VY", "(I)Ljava/lang/String;", "execute", "", "Landroidx/compose/runtime/composer/linkbuffer/changelist/OperationArgContainer;", "applier", "Landroidx/compose/runtime/Applier;", "slots", "Landroidx/compose/runtime/composer/linkbuffer/SlotTableEditor;", "rememberManager", "Landroidx/compose/runtime/composer/RememberManager;", "errorContext", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationErrorContext;", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class AppendValue extends Operation {
        public static final int $stable = 0;
        public static final AppendValue INSTANCE = new AppendValue();

        private AppendValue() {
            super(0, 1, false, 5, null);
        }

        /* JADX INFO: renamed from: getValue-s5KIjrA, reason: not valid java name */
        public final int m4586getValues5KIjrA() {
            return ObjectParameter.m4608constructorimpl(0);
        }

        @Override // androidx.compose.runtime.composer.linkbuffer.changelist.Operation
        /* JADX INFO: renamed from: objectParamName-gvac4VY */
        public String mo4585objectParamNamegvac4VY(int parameter) {
            return ObjectParameter.m4610equalsimpl0(parameter, ObjectParameter.m4608constructorimpl(0)) ? "value" : super.mo4585objectParamNamegvac4VY(parameter);
        }

        @Override // androidx.compose.runtime.composer.linkbuffer.changelist.Operation
        protected void execute(OperationArgContainer $this$execute, Applier<?> applier, SlotTableEditor slots, RememberManager rememberManager, OperationErrorContext errorContext) {
            Object value = $this$execute.mo4630getObjectgvac4VY(ObjectParameter.m4608constructorimpl(0));
            if (value instanceof RememberObserverHolder) {
                rememberManager.remembering((RememberObserverHolder) value);
            }
            slots.appendSlot(value);
        }
    }

    /* JADX INFO: compiled from: Operation.kt */
    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\n\u001a\u00020\u000b2\n\u0010\f\u001a\u00060\u0005j\u0002`\rH\u0016J2\u0010\u000e\u001a\u00020\u000f*\u00020\u00102\n\u0010\u0011\u001a\u0006\u0012\u0002\b\u00030\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0014R\u0012\u0010\u0004\u001a\u00020\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0012\u0010\b\u001a\u00020\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\t\u0010\u0007¨\u0006\u0019"}, d2 = {"Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation$RemoveTailGroupsAndValues;", "Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation;", "<init>", "()V", "FirstTailGroupToRemove", "", "getFirstTailGroupToRemove", "()I", "TailSlotCount", "getTailSlotCount", "intParamName", "", "parameter", "Landroidx/compose/runtime/composer/linkbuffer/changelist/IntParameter;", "execute", "", "Landroidx/compose/runtime/composer/linkbuffer/changelist/OperationArgContainer;", "applier", "Landroidx/compose/runtime/Applier;", "slots", "Landroidx/compose/runtime/composer/linkbuffer/SlotTableEditor;", "rememberManager", "Landroidx/compose/runtime/composer/RememberManager;", "errorContext", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationErrorContext;", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class RemoveTailGroupsAndValues extends Operation {
        public static final int $stable = 0;
        public static final RemoveTailGroupsAndValues INSTANCE = new RemoveTailGroupsAndValues();

        private RemoveTailGroupsAndValues() {
            super(2, 0, false, 6, null);
        }

        public final int getFirstTailGroupToRemove() {
            return 0;
        }

        public final int getTailSlotCount() {
            return 1;
        }

        @Override // androidx.compose.runtime.composer.linkbuffer.changelist.Operation
        public String intParamName(int parameter) {
            return parameter == 0 ? "firstTailGroupToRemove" : parameter == 1 ? "tailSlotCount" : super.intParamName(parameter);
        }

        @Override // androidx.compose.runtime.composer.linkbuffer.changelist.Operation
        protected void execute(OperationArgContainer $this$execute, Applier<?> applier, SlotTableEditor slots, final RememberManager rememberManager, OperationErrorContext errorContext) {
            int tailSlotCount = $this$execute.getInt(1);
            int firstTailGroupToRemove = $this$execute.getInt(0);
            slots.visitTailSlotsInRememberOrder$runtime(slots.getParent(), firstTailGroupToRemove, tailSlotCount, new SlotTableEditor.VisitSlotsInRememberOrderCallback() { // from class: androidx.compose.runtime.composer.linkbuffer.changelist.Operation$RemoveTailGroupsAndValues$$ExternalSyntheticLambda0
                @Override // androidx.compose.runtime.composer.linkbuffer.SlotTableEditor.VisitSlotsInRememberOrderCallback
                public final boolean visit(int i, int i2, Object obj) {
                    return Operation.RemoveTailGroupsAndValues.execute$lambda$0(rememberManager, i, i2, obj);
                }
            });
            slots.trimSlots(tailSlotCount);
            while (slots.getCurrent() != firstTailGroupToRemove) {
                slots.skipGroup();
            }
            while (slots.getCurrent() >= 0) {
                slots.removeGroup(true);
            }
        }

        static final boolean execute$lambda$0(RememberManager $rememberManager, int i, int i2, Object value) {
            if (!(value instanceof ComposeNodeLifecycleCallback)) {
                if (!(value instanceof RememberObserverHolder)) {
                    if (value instanceof RecomposeScopeImpl) {
                        ((RecomposeScopeImpl) value).release();
                        return false;
                    }
                    return false;
                }
                $rememberManager.forgetting((RememberObserverHolder) value);
                return false;
            }
            ((ComposeNodeLifecycleCallback) value).onRelease();
            return false;
        }
    }

    /* JADX INFO: compiled from: Operation.kt */
    @Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\f\u001a\u00020\r2\n\u0010\u000e\u001a\u00060\u0005j\u0002`\u000fH\u0016J\u001b\u0010\u0010\u001a\u00020\r2\n\u0010\u000e\u001a\u0006\u0012\u0002\b\u00030\tH\u0016¢\u0006\u0004\b\u0011\u0010\u0012J2\u0010\u0013\u001a\u00020\u0014*\u00020\u00152\n\u0010\u0016\u001a\u0006\u0012\u0002\b\u00030\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0014R\u0012\u0010\u0004\u001a\u00020\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\t8Æ\u0002¢\u0006\u0006\u001a\u0004\b\u000b\u0010\u0007¨\u0006\u001e"}, d2 = {"Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation$UpdateValue;", "Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation;", "<init>", "()V", "SlotAddress", "", "getSlotAddress", "()I", "Value", "Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation$ObjectParameter;", "", "getValue-s5KIjrA", "intParamName", "", "parameter", "Landroidx/compose/runtime/composer/linkbuffer/changelist/IntParameter;", "objectParamName", "objectParamName-gvac4VY", "(I)Ljava/lang/String;", "execute", "", "Landroidx/compose/runtime/composer/linkbuffer/changelist/OperationArgContainer;", "applier", "Landroidx/compose/runtime/Applier;", "slots", "Landroidx/compose/runtime/composer/linkbuffer/SlotTableEditor;", "rememberManager", "Landroidx/compose/runtime/composer/RememberManager;", "errorContext", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationErrorContext;", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class UpdateValue extends Operation {
        public static final int $stable = 0;
        public static final UpdateValue INSTANCE = new UpdateValue();

        private UpdateValue() {
            super(1, 1, false, 4, null);
        }

        public final int getSlotAddress() {
            return 0;
        }

        /* JADX INFO: renamed from: getValue-s5KIjrA, reason: not valid java name */
        public final int m4628getValues5KIjrA() {
            return ObjectParameter.m4608constructorimpl(0);
        }

        @Override // androidx.compose.runtime.composer.linkbuffer.changelist.Operation
        public String intParamName(int parameter) {
            return parameter == 0 ? "slotAddress" : super.intParamName(parameter);
        }

        @Override // androidx.compose.runtime.composer.linkbuffer.changelist.Operation
        /* JADX INFO: renamed from: objectParamName-gvac4VY */
        public String mo4585objectParamNamegvac4VY(int parameter) {
            return ObjectParameter.m4610equalsimpl0(parameter, ObjectParameter.m4608constructorimpl(0)) ? "value" : super.mo4585objectParamNamegvac4VY(parameter);
        }

        @Override // androidx.compose.runtime.composer.linkbuffer.changelist.Operation
        protected void execute(OperationArgContainer $this$execute, Applier<?> applier, SlotTableEditor slots, RememberManager rememberManager, OperationErrorContext errorContext) {
            Object value = $this$execute.mo4630getObjectgvac4VY(ObjectParameter.m4608constructorimpl(0));
            int slot = $this$execute.getInt(0);
            if (value instanceof RememberObserverHolder) {
                rememberManager.remembering((RememberObserverHolder) value);
            }
            Object previous = slots.setAbsolute(slot, value);
            if (!(previous instanceof RememberObserverHolder)) {
                if (previous instanceof RecomposeScopeImpl) {
                    ((RecomposeScopeImpl) previous).release();
                    return;
                }
                return;
            }
            rememberManager.forgetting((RememberObserverHolder) previous);
        }
    }

    /* JADX INFO: compiled from: Operation.kt */
    @Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\f\u001a\u00020\r2\n\u0010\u000e\u001a\u0006\u0012\u0002\b\u00030\u0005H\u0016¢\u0006\u0004\b\u000f\u0010\u0010J2\u0010\u0011\u001a\u00020\u0012*\u00020\u00132\n\u0010\u0014\u001a\u0006\u0012\u0002\b\u00030\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0014R\u0018\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0018\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\u000b\u0010\b¨\u0006\u001c"}, d2 = {"Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation$UpdateRememberObserverHolderOrdering;", "Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation;", "<init>", "()V", "After", "Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation$ObjectParameter;", "Landroidx/compose/runtime/composer/linkbuffer/LinkAnchor;", "getAfter-s5KIjrA", "()I", "Holder", "Landroidx/compose/runtime/LinkRememberObserverHolder;", "getHolder-s5KIjrA", "objectParamName", "", "parameter", "objectParamName-gvac4VY", "(I)Ljava/lang/String;", "execute", "", "Landroidx/compose/runtime/composer/linkbuffer/changelist/OperationArgContainer;", "applier", "Landroidx/compose/runtime/Applier;", "slots", "Landroidx/compose/runtime/composer/linkbuffer/SlotTableEditor;", "rememberManager", "Landroidx/compose/runtime/composer/RememberManager;", "errorContext", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationErrorContext;", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class UpdateRememberObserverHolderOrdering extends Operation {
        public static final int $stable = 0;
        public static final UpdateRememberObserverHolderOrdering INSTANCE = new UpdateRememberObserverHolderOrdering();

        private UpdateRememberObserverHolderOrdering() {
            super(0, 2, false, 4, null);
        }

        /* JADX INFO: renamed from: getAfter-s5KIjrA, reason: not valid java name */
        public final int m4626getAfters5KIjrA() {
            return ObjectParameter.m4608constructorimpl(0);
        }

        /* JADX INFO: renamed from: getHolder-s5KIjrA, reason: not valid java name */
        public final int m4627getHolders5KIjrA() {
            return ObjectParameter.m4608constructorimpl(1);
        }

        @Override // androidx.compose.runtime.composer.linkbuffer.changelist.Operation
        /* JADX INFO: renamed from: objectParamName-gvac4VY */
        public String mo4585objectParamNamegvac4VY(int parameter) {
            return ObjectParameter.m4610equalsimpl0(parameter, ObjectParameter.m4608constructorimpl(0)) ? "after" : ObjectParameter.m4610equalsimpl0(parameter, ObjectParameter.m4608constructorimpl(1)) ? "rememberObserverHolder" : super.mo4585objectParamNamegvac4VY(parameter);
        }

        @Override // androidx.compose.runtime.composer.linkbuffer.changelist.Operation
        protected void execute(OperationArgContainer $this$execute, Applier<?> applier, SlotTableEditor slots, RememberManager rememberManager, OperationErrorContext errorContext) {
            LinkRememberObserverHolder holder = (LinkRememberObserverHolder) $this$execute.mo4630getObjectgvac4VY(ObjectParameter.m4608constructorimpl(1));
            LinkAnchor after = (LinkAnchor) $this$execute.mo4630getObjectgvac4VY(ObjectParameter.m4608constructorimpl(0));
            holder.setAfter(after);
        }
    }

    /* JADX INFO: compiled from: Operation.kt */
    @Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\f\u001a\u00020\r2\n\u0010\u000e\u001a\u00060\u0005j\u0002`\u000fH\u0016J\u001b\u0010\u0010\u001a\u00020\r2\n\u0010\u000e\u001a\u0006\u0012\u0002\b\u00030\tH\u0016¢\u0006\u0004\b\u0011\u0010\u0012J2\u0010\u0013\u001a\u00020\u0014*\u00020\u00152\n\u0010\u0016\u001a\u0006\u0012\u0002\b\u00030\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0014R\u0012\u0010\u0004\u001a\u00020\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\t8Æ\u0002¢\u0006\u0006\u001a\u0004\b\u000b\u0010\u0007¨\u0006\u001e"}, d2 = {"Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation$UpdateValueRelative;", "Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation;", "<init>", "()V", "SlotIndex", "", "getSlotIndex", "()I", "Value", "Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation$ObjectParameter;", "", "getValue-s5KIjrA", "intParamName", "", "parameter", "Landroidx/compose/runtime/composer/linkbuffer/changelist/IntParameter;", "objectParamName", "objectParamName-gvac4VY", "(I)Ljava/lang/String;", "execute", "", "Landroidx/compose/runtime/composer/linkbuffer/changelist/OperationArgContainer;", "applier", "Landroidx/compose/runtime/Applier;", "slots", "Landroidx/compose/runtime/composer/linkbuffer/SlotTableEditor;", "rememberManager", "Landroidx/compose/runtime/composer/RememberManager;", "errorContext", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationErrorContext;", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class UpdateValueRelative extends Operation {
        public static final int $stable = 0;
        public static final UpdateValueRelative INSTANCE = new UpdateValueRelative();

        private UpdateValueRelative() {
            super(1, 1, false, 4, null);
        }

        public final int getSlotIndex() {
            return 0;
        }

        /* JADX INFO: renamed from: getValue-s5KIjrA, reason: not valid java name */
        public final int m4629getValues5KIjrA() {
            return ObjectParameter.m4608constructorimpl(0);
        }

        @Override // androidx.compose.runtime.composer.linkbuffer.changelist.Operation
        public String intParamName(int parameter) {
            return parameter == 0 ? "slotIndex" : super.intParamName(parameter);
        }

        @Override // androidx.compose.runtime.composer.linkbuffer.changelist.Operation
        /* JADX INFO: renamed from: objectParamName-gvac4VY */
        public String mo4585objectParamNamegvac4VY(int parameter) {
            return ObjectParameter.m4610equalsimpl0(parameter, ObjectParameter.m4608constructorimpl(0)) ? "value" : super.mo4585objectParamNamegvac4VY(parameter);
        }

        @Override // androidx.compose.runtime.composer.linkbuffer.changelist.Operation
        protected void execute(OperationArgContainer $this$execute, Applier<?> applier, SlotTableEditor slots, RememberManager rememberManager, OperationErrorContext errorContext) {
            Object value = $this$execute.mo4630getObjectgvac4VY(ObjectParameter.m4608constructorimpl(0));
            int slotIndex = $this$execute.getInt(0);
            if (value instanceof RememberObserverHolder) {
                rememberManager.remembering((RememberObserverHolder) value);
            }
            Object previous = slots.setRelative(slotIndex, value);
            if (!(previous instanceof RememberObserverHolder)) {
                if (previous instanceof RecomposeScopeImpl) {
                    ((RecomposeScopeImpl) previous).release();
                    return;
                }
                return;
            }
            rememberManager.forgetting((RememberObserverHolder) previous);
        }
    }

    /* JADX INFO: compiled from: Operation.kt */
    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\t\u001a\u00020\n2\n\u0010\u000b\u001a\u0006\u0012\u0002\b\u00030\u0005H\u0016¢\u0006\u0004\b\f\u0010\rJ2\u0010\u000e\u001a\u00020\u000f*\u00020\u00102\n\u0010\u0011\u001a\u0006\u0012\u0002\b\u00030\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0014R\u001a\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\u0019"}, d2 = {"Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation$UpdateAuxData;", "Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation;", "<init>", "()V", "Data", "Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation$ObjectParameter;", "", "getData-s5KIjrA", "()I", "objectParamName", "", "parameter", "objectParamName-gvac4VY", "(I)Ljava/lang/String;", "execute", "", "Landroidx/compose/runtime/composer/linkbuffer/changelist/OperationArgContainer;", "applier", "Landroidx/compose/runtime/Applier;", "slots", "Landroidx/compose/runtime/composer/linkbuffer/SlotTableEditor;", "rememberManager", "Landroidx/compose/runtime/composer/RememberManager;", "errorContext", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationErrorContext;", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class UpdateAuxData extends Operation {
        public static final int $stable = 0;
        public static final UpdateAuxData INSTANCE = new UpdateAuxData();

        private UpdateAuxData() {
            super(0, 1, false, 5, null);
        }

        /* JADX INFO: renamed from: getData-s5KIjrA, reason: not valid java name */
        public final int m4623getDatas5KIjrA() {
            return ObjectParameter.m4608constructorimpl(0);
        }

        @Override // androidx.compose.runtime.composer.linkbuffer.changelist.Operation
        /* JADX INFO: renamed from: objectParamName-gvac4VY */
        public String mo4585objectParamNamegvac4VY(int parameter) {
            return ObjectParameter.m4610equalsimpl0(parameter, ObjectParameter.m4608constructorimpl(0)) ? "data" : super.mo4585objectParamNamegvac4VY(parameter);
        }

        @Override // androidx.compose.runtime.composer.linkbuffer.changelist.Operation
        protected void execute(OperationArgContainer $this$execute, Applier<?> applier, SlotTableEditor slots, RememberManager rememberManager, OperationErrorContext errorContext) {
            slots.updateAux($this$execute.mo4630getObjectgvac4VY(ObjectParameter.m4608constructorimpl(0)));
        }
    }

    /* JADX INFO: compiled from: Operation.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J2\u0010\u0004\u001a\u00020\u0005*\u00020\u00062\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0014¨\u0006\u000f"}, d2 = {"Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation$RemoveGroup;", "Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation;", "<init>", "()V", "execute", "", "Landroidx/compose/runtime/composer/linkbuffer/changelist/OperationArgContainer;", "applier", "Landroidx/compose/runtime/Applier;", "slots", "Landroidx/compose/runtime/composer/linkbuffer/SlotTableEditor;", "rememberManager", "Landroidx/compose/runtime/composer/RememberManager;", "errorContext", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationErrorContext;", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class RemoveGroup extends Operation {
        public static final int $stable = 0;
        public static final RemoveGroup INSTANCE = new RemoveGroup();

        private RemoveGroup() {
            super(0, 0, false, 7, null);
        }

        @Override // androidx.compose.runtime.composer.linkbuffer.changelist.Operation
        protected void execute(OperationArgContainer $this$execute, Applier<?> applier, SlotTableEditor slots, RememberManager rememberManager, OperationErrorContext errorContext) {
            SlotTableEditorKt.removeGroupAndForgetSlots(slots, rememberManager);
        }
    }

    /* JADX INFO: compiled from: Operation.kt */
    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\b\u001a\u00020\t2\n\u0010\n\u001a\u00060\u0005j\u0002`\u000bH\u0016J2\u0010\f\u001a\u00020\r*\u00020\u000e2\n\u0010\u000f\u001a\u0006\u0012\u0002\b\u00030\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0014R\u0012\u0010\u0004\u001a\u00020\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0017"}, d2 = {"Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation$MoveGroup;", "Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation;", "<init>", "()V", "Offset", "", "getOffset", "()I", "intParamName", "", "parameter", "Landroidx/compose/runtime/composer/linkbuffer/changelist/IntParameter;", "execute", "", "Landroidx/compose/runtime/composer/linkbuffer/changelist/OperationArgContainer;", "applier", "Landroidx/compose/runtime/Applier;", "slots", "Landroidx/compose/runtime/composer/linkbuffer/SlotTableEditor;", "rememberManager", "Landroidx/compose/runtime/composer/RememberManager;", "errorContext", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationErrorContext;", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class MoveGroup extends Operation {
        public static final int $stable = 0;
        public static final MoveGroup INSTANCE = new MoveGroup();

        private MoveGroup() {
            super(1, 0, false, 6, null);
        }

        public final int getOffset() {
            return 0;
        }

        @Override // androidx.compose.runtime.composer.linkbuffer.changelist.Operation
        public String intParamName(int parameter) {
            return parameter == 0 ? TypedValues.CycleType.S_WAVE_OFFSET : super.intParamName(parameter);
        }

        @Override // androidx.compose.runtime.composer.linkbuffer.changelist.Operation
        protected void execute(OperationArgContainer $this$execute, Applier<?> applier, SlotTableEditor slots, RememberManager rememberManager, OperationErrorContext errorContext) {
            slots.moveGroup($this$execute.getInt(0));
        }
    }

    /* JADX INFO: compiled from: Operation.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J2\u0010\u0004\u001a\u00020\u0005*\u00020\u00062\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0014¨\u0006\u000f"}, d2 = {"Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation$ClearAllRecompositionRequired;", "Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation;", "<init>", "()V", "execute", "", "Landroidx/compose/runtime/composer/linkbuffer/changelist/OperationArgContainer;", "applier", "Landroidx/compose/runtime/Applier;", "slots", "Landroidx/compose/runtime/composer/linkbuffer/SlotTableEditor;", "rememberManager", "Landroidx/compose/runtime/composer/RememberManager;", "errorContext", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationErrorContext;", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class ClearAllRecompositionRequired extends Operation {
        public static final int $stable = 0;
        public static final ClearAllRecompositionRequired INSTANCE = new ClearAllRecompositionRequired();

        private ClearAllRecompositionRequired() {
            super(0, 0, false, 3, null);
        }

        @Override // androidx.compose.runtime.composer.linkbuffer.changelist.Operation
        protected void execute(OperationArgContainer $this$execute, Applier<?> applier, SlotTableEditor slots, RememberManager rememberManager, OperationErrorContext errorContext) {
            slots.removeAllInstancesOfFlags(67108864);
        }
    }

    /* JADX INFO: compiled from: Operation.kt */
    @Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\r\u001a\u00020\u000e2\n\u0010\u000f\u001a\u0006\u0012\u0002\b\u00030\u0005H\u0016¢\u0006\u0004\b\u0010\u0010\u0011J2\u0010\u0012\u001a\u00020\b*\u00020\u00132\n\u0010\u0014\u001a\u0006\u0012\u0002\b\u00030\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0014R$\u0010\u0004\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u00060\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0018\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\f\u0010\n¨\u0006\u001c"}, d2 = {"Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation$EndCompositionScope;", "Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation;", "<init>", "()V", "Action", "Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation$ObjectParameter;", "Lkotlin/Function1;", "Landroidx/compose/runtime/Composition;", "", "getAction-s5KIjrA", "()I", "Composition", "getComposition-s5KIjrA", "objectParamName", "", "parameter", "objectParamName-gvac4VY", "(I)Ljava/lang/String;", "execute", "Landroidx/compose/runtime/composer/linkbuffer/changelist/OperationArgContainer;", "applier", "Landroidx/compose/runtime/Applier;", "slots", "Landroidx/compose/runtime/composer/linkbuffer/SlotTableEditor;", "rememberManager", "Landroidx/compose/runtime/composer/RememberManager;", "errorContext", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationErrorContext;", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class EndCompositionScope extends Operation {
        public static final int $stable = 0;
        public static final EndCompositionScope INSTANCE = new EndCompositionScope();

        private EndCompositionScope() {
            super(0, 2, false, 5, null);
        }

        /* JADX INFO: renamed from: getAction-s5KIjrA, reason: not valid java name */
        public final int m4598getActions5KIjrA() {
            return ObjectParameter.m4608constructorimpl(0);
        }

        /* JADX INFO: renamed from: getComposition-s5KIjrA, reason: not valid java name */
        public final int m4599getCompositions5KIjrA() {
            return ObjectParameter.m4608constructorimpl(1);
        }

        @Override // androidx.compose.runtime.composer.linkbuffer.changelist.Operation
        /* JADX INFO: renamed from: objectParamName-gvac4VY */
        public String mo4585objectParamNamegvac4VY(int parameter) {
            return ObjectParameter.m4610equalsimpl0(parameter, ObjectParameter.m4608constructorimpl(0)) ? "anchor" : ObjectParameter.m4610equalsimpl0(parameter, ObjectParameter.m4608constructorimpl(1)) ? "composition" : super.mo4585objectParamNamegvac4VY(parameter);
        }

        @Override // androidx.compose.runtime.composer.linkbuffer.changelist.Operation
        protected void execute(OperationArgContainer $this$execute, Applier<?> applier, SlotTableEditor slots, RememberManager rememberManager, OperationErrorContext errorContext) {
            Function1 action = (Function1) $this$execute.mo4630getObjectgvac4VY(ObjectParameter.m4608constructorimpl(0));
            Composition composition = (Composition) $this$execute.mo4630getObjectgvac4VY(ObjectParameter.m4608constructorimpl(1));
            action.invoke(composition);
        }
    }

    /* JADX INFO: compiled from: Operation.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J2\u0010\u0004\u001a\u00020\u0005*\u00020\u00062\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0014¨\u0006\u000f"}, d2 = {"Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation$UseCurrentNode;", "Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation;", "<init>", "()V", "execute", "", "Landroidx/compose/runtime/composer/linkbuffer/changelist/OperationArgContainer;", "applier", "Landroidx/compose/runtime/Applier;", "slots", "Landroidx/compose/runtime/composer/linkbuffer/SlotTableEditor;", "rememberManager", "Landroidx/compose/runtime/composer/RememberManager;", "errorContext", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationErrorContext;", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class UseCurrentNode extends Operation {
        public static final int $stable = 0;
        public static final UseCurrentNode INSTANCE = new UseCurrentNode();

        private UseCurrentNode() {
            super(0, 0, false, 7, null);
        }

        @Override // androidx.compose.runtime.composer.linkbuffer.changelist.Operation
        protected void execute(OperationArgContainer $this$execute, Applier<?> applier, SlotTableEditor slots, RememberManager rememberManager, OperationErrorContext errorContext) {
            applier.reuse();
        }
    }

    /* JADX INFO: compiled from: Operation.kt */
    @Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\u000e\u001a\u00020\u000f2\n\u0010\u0010\u001a\u0006\u0012\u0002\b\u00030\u0005H\u0016¢\u0006\u0004\b\u0011\u0010\u0012J2\u0010\u0013\u001a\u00020\u000b*\u00020\u00142\n\u0010\u0015\u001a\u0006\u0012\u0002\b\u00030\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0014R\u001a\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR3\u0010\t\u001a#\u0012\u001f\u0012\u001d\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\u0002\b\f0\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\r\u0010\b¨\u0006\u001d"}, d2 = {"Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation$UpdateNode;", "Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation;", "<init>", "()V", "Value", "Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation$ObjectParameter;", "", "getValue-s5KIjrA", "()I", "Block", "Lkotlin/Function2;", "", "Lkotlin/ExtensionFunctionType;", "getBlock-s5KIjrA", "objectParamName", "", "parameter", "objectParamName-gvac4VY", "(I)Ljava/lang/String;", "execute", "Landroidx/compose/runtime/composer/linkbuffer/changelist/OperationArgContainer;", "applier", "Landroidx/compose/runtime/Applier;", "slots", "Landroidx/compose/runtime/composer/linkbuffer/SlotTableEditor;", "rememberManager", "Landroidx/compose/runtime/composer/RememberManager;", "errorContext", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationErrorContext;", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class UpdateNode extends Operation {
        public static final int $stable = 0;
        public static final UpdateNode INSTANCE = new UpdateNode();

        private UpdateNode() {
            super(0, 2, false, 5, null);
        }

        /* JADX INFO: renamed from: getValue-s5KIjrA, reason: not valid java name */
        public final int m4625getValues5KIjrA() {
            return ObjectParameter.m4608constructorimpl(0);
        }

        /* JADX INFO: renamed from: getBlock-s5KIjrA, reason: not valid java name */
        public final int m4624getBlocks5KIjrA() {
            return ObjectParameter.m4608constructorimpl(1);
        }

        @Override // androidx.compose.runtime.composer.linkbuffer.changelist.Operation
        /* JADX INFO: renamed from: objectParamName-gvac4VY */
        public String mo4585objectParamNamegvac4VY(int parameter) {
            return ObjectParameter.m4610equalsimpl0(parameter, ObjectParameter.m4608constructorimpl(0)) ? "value" : ObjectParameter.m4610equalsimpl0(parameter, ObjectParameter.m4608constructorimpl(1)) ? "block" : super.mo4585objectParamNamegvac4VY(parameter);
        }

        @Override // androidx.compose.runtime.composer.linkbuffer.changelist.Operation
        protected void execute(OperationArgContainer $this$execute, Applier<?> applier, SlotTableEditor slots, RememberManager rememberManager, OperationErrorContext errorContext) {
            Object value = $this$execute.mo4630getObjectgvac4VY(ObjectParameter.m4608constructorimpl(0));
            applier.apply((Function2) $this$execute.mo4630getObjectgvac4VY(ObjectParameter.m4608constructorimpl(1)), value);
        }
    }

    /* JADX INFO: compiled from: Operation.kt */
    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\n\u001a\u00020\u000b2\n\u0010\f\u001a\u00060\u0005j\u0002`\rH\u0016J2\u0010\u000e\u001a\u00020\u000f*\u00020\u00102\n\u0010\u0011\u001a\u0006\u0012\u0002\b\u00030\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0014R\u0012\u0010\u0004\u001a\u00020\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0012\u0010\b\u001a\u00020\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\t\u0010\u0007¨\u0006\u0019"}, d2 = {"Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation$RemoveNode;", "Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation;", "<init>", "()V", "RemoveIndex", "", "getRemoveIndex", "()I", "Count", "getCount", "intParamName", "", "parameter", "Landroidx/compose/runtime/composer/linkbuffer/changelist/IntParameter;", "execute", "", "Landroidx/compose/runtime/composer/linkbuffer/changelist/OperationArgContainer;", "applier", "Landroidx/compose/runtime/Applier;", "slots", "Landroidx/compose/runtime/composer/linkbuffer/SlotTableEditor;", "rememberManager", "Landroidx/compose/runtime/composer/RememberManager;", "errorContext", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationErrorContext;", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class RemoveNode extends Operation {
        public static final int $stable = 0;
        public static final RemoveNode INSTANCE = new RemoveNode();

        private RemoveNode() {
            super(2, 0, false, 6, null);
        }

        public final int getRemoveIndex() {
            return 0;
        }

        public final int getCount() {
            return 1;
        }

        @Override // androidx.compose.runtime.composer.linkbuffer.changelist.Operation
        public String intParamName(int parameter) {
            return parameter == 0 ? "removeIndex" : parameter == 1 ? "count" : super.intParamName(parameter);
        }

        @Override // androidx.compose.runtime.composer.linkbuffer.changelist.Operation
        protected void execute(OperationArgContainer $this$execute, Applier<?> applier, SlotTableEditor slots, RememberManager rememberManager, OperationErrorContext errorContext) {
            applier.remove($this$execute.getInt(0), $this$execute.getInt(1));
        }
    }

    /* JADX INFO: compiled from: Operation.kt */
    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\f\u001a\u00020\r2\n\u0010\u000e\u001a\u00060\u0005j\u0002`\u000fH\u0016J2\u0010\u0010\u001a\u00020\u0011*\u00020\u00122\n\u0010\u0013\u001a\u0006\u0012\u0002\b\u00030\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0014R\u0012\u0010\u0004\u001a\u00020\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0012\u0010\b\u001a\u00020\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\t\u0010\u0007R\u0012\u0010\n\u001a\u00020\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\u000b\u0010\u0007¨\u0006\u001b"}, d2 = {"Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation$MoveNode;", "Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation;", "<init>", "()V", "From", "", "getFrom", "()I", "To", "getTo", "Count", "getCount", "intParamName", "", "parameter", "Landroidx/compose/runtime/composer/linkbuffer/changelist/IntParameter;", "execute", "", "Landroidx/compose/runtime/composer/linkbuffer/changelist/OperationArgContainer;", "applier", "Landroidx/compose/runtime/Applier;", "slots", "Landroidx/compose/runtime/composer/linkbuffer/SlotTableEditor;", "rememberManager", "Landroidx/compose/runtime/composer/RememberManager;", "errorContext", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationErrorContext;", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class MoveNode extends Operation {
        public static final int $stable = 0;
        public static final MoveNode INSTANCE = new MoveNode();

        private MoveNode() {
            super(3, 0, false, 6, null);
        }

        public final int getFrom() {
            return 0;
        }

        public final int getTo() {
            return 1;
        }

        public final int getCount() {
            return 2;
        }

        @Override // androidx.compose.runtime.composer.linkbuffer.changelist.Operation
        public String intParamName(int parameter) {
            return parameter == 0 ? TypedValues.TransitionType.S_FROM : parameter == 1 ? TypedValues.TransitionType.S_TO : parameter == 2 ? "count" : super.intParamName(parameter);
        }

        @Override // androidx.compose.runtime.composer.linkbuffer.changelist.Operation
        protected void execute(OperationArgContainer $this$execute, Applier<?> applier, SlotTableEditor slots, RememberManager rememberManager, OperationErrorContext errorContext) {
            applier.move($this$execute.getInt(0), $this$execute.getInt(1), $this$execute.getInt(2));
        }
    }

    /* JADX INFO: compiled from: Operation.kt */
    @Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u000e\u001a\u00020\u000f2\n\u0010\u0010\u001a\u00060\u0005j\u0002`\u0011H\u0016J\u001b\u0010\u0012\u001a\u00020\u000f2\n\u0010\u0010\u001a\u0006\u0012\u0002\b\u00030\u000bH\u0016¢\u0006\u0004\b\u0013\u0010\u0014J2\u0010\u0015\u001a\u00020\u0016*\u00020\u00172\n\u0010\u0018\u001a\u0006\u0012\u0002\b\u00030\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0014R\u0012\u0010\u0004\u001a\u00020\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0012\u0010\b\u001a\u00020\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\t\u0010\u0007R\u0018\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b8Æ\u0002¢\u0006\u0006\u001a\u0004\b\r\u0010\u0007¨\u0006 "}, d2 = {"Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation$InsertSlots;", "Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation;", "<init>", "()V", "SourceHighBits", "", "getSourceHighBits", "()I", "SourceLowBits", "getSourceLowBits", "FromSlotTable", "Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation$ObjectParameter;", "Landroidx/compose/runtime/composer/linkbuffer/SlotTable;", "getFromSlotTable-s5KIjrA", "intParamName", "", "parameter", "Landroidx/compose/runtime/composer/linkbuffer/changelist/IntParameter;", "objectParamName", "objectParamName-gvac4VY", "(I)Ljava/lang/String;", "execute", "", "Landroidx/compose/runtime/composer/linkbuffer/changelist/OperationArgContainer;", "applier", "Landroidx/compose/runtime/Applier;", "slots", "Landroidx/compose/runtime/composer/linkbuffer/SlotTableEditor;", "rememberManager", "Landroidx/compose/runtime/composer/RememberManager;", "errorContext", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationErrorContext;", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class InsertSlots extends Operation {
        public static final int $stable = 0;
        public static final InsertSlots INSTANCE = new InsertSlots();

        private InsertSlots() {
            super(2, 1, false, 4, null);
        }

        public final int getSourceHighBits() {
            return 0;
        }

        public final int getSourceLowBits() {
            return 1;
        }

        /* JADX INFO: renamed from: getFromSlotTable-s5KIjrA, reason: not valid java name */
        public final int m4604getFromSlotTables5KIjrA() {
            return ObjectParameter.m4608constructorimpl(0);
        }

        @Override // androidx.compose.runtime.composer.linkbuffer.changelist.Operation
        public String intParamName(int parameter) {
            return parameter == 0 ? "source[32..63]" : parameter == 1 ? "source[0..31]" : super.intParamName(parameter);
        }

        @Override // androidx.compose.runtime.composer.linkbuffer.changelist.Operation
        /* JADX INFO: renamed from: objectParamName-gvac4VY */
        public String mo4585objectParamNamegvac4VY(int parameter) {
            return ObjectParameter.m4610equalsimpl0(parameter, ObjectParameter.m4608constructorimpl(0)) ? TypedValues.TransitionType.S_FROM : super.mo4585objectParamNamegvac4VY(parameter);
        }

        @Override // androidx.compose.runtime.composer.linkbuffer.changelist.Operation
        protected void execute(OperationArgContainer $this$execute, Applier<?> applier, SlotTableEditor slots, RememberManager rememberManager, OperationErrorContext errorContext) {
            SlotTableEditor.moveFrom$default(slots, (SlotTable) $this$execute.mo4630getObjectgvac4VY(ObjectParameter.m4608constructorimpl(0)), OperationArgContainerKt.getLong($this$execute, 0, 1), 0L, 4, (Object) null);
            slots.skipGroup();
        }
    }

    /* JADX INFO: compiled from: Operation.kt */
    @Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0011\u001a\u00020\u00122\n\u0010\u0013\u001a\u00060\u0005j\u0002`\u0014H\u0016J\u001b\u0010\u0015\u001a\u00020\u00122\n\u0010\u0013\u001a\u0006\u0012\u0002\b\u00030\u000bH\u0016¢\u0006\u0004\b\u0016\u0010\u0017J2\u0010\u0018\u001a\u00020\u0019*\u00020\u001a2\n\u0010\u001b\u001a\u0006\u0012\u0002\b\u00030\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\"H\u0014R\u0012\u0010\u0004\u001a\u00020\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0012\u0010\b\u001a\u00020\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\t\u0010\u0007R\u0018\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b8Æ\u0002¢\u0006\u0006\u001a\u0004\b\r\u0010\u0007R\u0018\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000b8Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0007¨\u0006#"}, d2 = {"Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation$InsertSlotsWithFixups;", "Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation;", "<init>", "()V", "SourceHighBits", "", "getSourceHighBits", "()I", "SourceLowBits", "getSourceLowBits", "FromSlotTable", "Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation$ObjectParameter;", "Landroidx/compose/runtime/composer/linkbuffer/SlotTable;", "getFromSlotTable-s5KIjrA", "Fixups", "Landroidx/compose/runtime/composer/linkbuffer/changelist/FixupList;", "getFixups-s5KIjrA", "intParamName", "", "parameter", "Landroidx/compose/runtime/composer/linkbuffer/changelist/IntParameter;", "objectParamName", "objectParamName-gvac4VY", "(I)Ljava/lang/String;", "execute", "", "Landroidx/compose/runtime/composer/linkbuffer/changelist/OperationArgContainer;", "applier", "Landroidx/compose/runtime/Applier;", "slots", "Landroidx/compose/runtime/composer/linkbuffer/SlotTableEditor;", "rememberManager", "Landroidx/compose/runtime/composer/RememberManager;", "errorContext", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationErrorContext;", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class InsertSlotsWithFixups extends Operation {
        public static final int $stable = 0;
        public static final InsertSlotsWithFixups INSTANCE = new InsertSlotsWithFixups();

        private InsertSlotsWithFixups() {
            super(2, 2, false, 4, null);
        }

        public final int getSourceHighBits() {
            return 0;
        }

        public final int getSourceLowBits() {
            return 1;
        }

        /* JADX INFO: renamed from: getFromSlotTable-s5KIjrA, reason: not valid java name */
        public final int m4606getFromSlotTables5KIjrA() {
            return ObjectParameter.m4608constructorimpl(0);
        }

        /* JADX INFO: renamed from: getFixups-s5KIjrA, reason: not valid java name */
        public final int m4605getFixupss5KIjrA() {
            return ObjectParameter.m4608constructorimpl(1);
        }

        @Override // androidx.compose.runtime.composer.linkbuffer.changelist.Operation
        public String intParamName(int parameter) {
            return parameter == 0 ? "sourceHandle[32..63]" : parameter == 1 ? "sourceHandle[0..31]" : super.intParamName(parameter);
        }

        @Override // androidx.compose.runtime.composer.linkbuffer.changelist.Operation
        /* JADX INFO: renamed from: objectParamName-gvac4VY */
        public String mo4585objectParamNamegvac4VY(int parameter) {
            return ObjectParameter.m4610equalsimpl0(parameter, ObjectParameter.m4608constructorimpl(0)) ? TypedValues.TransitionType.S_FROM : ObjectParameter.m4610equalsimpl0(parameter, ObjectParameter.m4608constructorimpl(1)) ? "fixups" : super.mo4585objectParamNamegvac4VY(parameter);
        }

        @Override // androidx.compose.runtime.composer.linkbuffer.changelist.Operation
        protected void execute(OperationArgContainer $this$execute, Applier<?> applier, SlotTableEditor slots, RememberManager rememberManager, OperationErrorContext errorContext) throws Throwable {
            OperationErrorContext operationErrorContextWithCurrentStackTrace;
            Throwable th;
            SlotTable insertTable = (SlotTable) $this$execute.mo4630getObjectgvac4VY(ObjectParameter.m4608constructorimpl(0));
            FixupList fixups = (FixupList) $this$execute.mo4630getObjectgvac4VY(ObjectParameter.m4608constructorimpl(1));
            SlotTableEditor $this$edit_u24lambda_u240$iv = insertTable.openEditor();
            if (errorContext != null) {
                try {
                    operationErrorContextWithCurrentStackTrace = OperationKt.withCurrentStackTrace(errorContext, slots);
                } catch (Throwable th2) {
                    th = th2;
                    $this$edit_u24lambda_u240$iv.close();
                    throw th;
                }
            } else {
                operationErrorContextWithCurrentStackTrace = null;
            }
            try {
                fixups.executeAndFlushAllPendingFixups(applier, $this$edit_u24lambda_u240$iv, rememberManager, operationErrorContextWithCurrentStackTrace);
                Unit unit = Unit.INSTANCE;
                $this$edit_u24lambda_u240$iv.close();
                SlotTableEditor.moveFrom$default(slots, insertTable, OperationArgContainerKt.getLong($this$execute, 0, 1), 0L, 4, (Object) null);
                slots.skipGroup();
            } catch (Throwable th3) {
                th = th3;
                $this$edit_u24lambda_u240$iv.close();
                throw th;
            }
        }
    }

    /* JADX INFO: compiled from: Operation.kt */
    @Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0011\u001a\u00020\u00122\n\u0010\u0013\u001a\u00060\u000bj\u0002`\u0014H\u0016J\u001b\u0010\u0015\u001a\u00020\u00122\n\u0010\u0013\u001a\u0006\u0012\u0002\b\u00030\u0005H\u0016¢\u0006\u0004\b\u0016\u0010\u0017J\u0018\u0010\u0018\u001a\u00060\u0019j\u0002`\u001a*\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0014J2\u0010\u001e\u001a\u00020\u001f*\u00020\u001b2\n\u0010 \u001a\u0006\u0012\u0002\b\u00030!2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010%H\u0014R \u0010\u0004\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00060\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0012\u0010\n\u001a\u00020\u000b8Æ\u0002¢\u0006\u0006\u001a\u0004\b\f\u0010\tR\u0012\u0010\r\u001a\u00020\u000b8Æ\u0002¢\u0006\u0006\u001a\u0004\b\u000e\u0010\tR\u0012\u0010\u000f\u001a\u00020\u000b8Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0010\u0010\t¨\u0006&"}, d2 = {"Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation$InsertNodeFixup;", "Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation;", "<init>", "()V", "Factory", "Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation$ObjectParameter;", "Lkotlin/Function0;", "", "getFactory-s5KIjrA", "()I", "InsertIndex", "", "getInsertIndex", "GroupHandleHigh", "getGroupHandleHigh", "GroupHandleLow", "getGroupHandleLow", "intParamName", "", "parameter", "Landroidx/compose/runtime/composer/linkbuffer/changelist/IntParameter;", "objectParamName", "objectParamName-gvac4VY", "(I)Ljava/lang/String;", "getGroupHandle", "", "Landroidx/compose/runtime/composer/linkbuffer/GroupHandle;", "Landroidx/compose/runtime/composer/linkbuffer/changelist/OperationArgContainer;", "slots", "Landroidx/compose/runtime/composer/linkbuffer/SlotTableEditor;", "execute", "", "applier", "Landroidx/compose/runtime/Applier;", "rememberManager", "Landroidx/compose/runtime/composer/RememberManager;", "errorContext", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationErrorContext;", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class InsertNodeFixup extends Operation {
        public static final int $stable = 0;
        public static final InsertNodeFixup INSTANCE = new InsertNodeFixup();

        private InsertNodeFixup() {
            super(3, 1, false, 4, null);
        }

        /* JADX INFO: renamed from: getFactory-s5KIjrA, reason: not valid java name */
        public final int m4601getFactorys5KIjrA() {
            return ObjectParameter.m4608constructorimpl(0);
        }

        public final int getInsertIndex() {
            return 0;
        }

        public final int getGroupHandleHigh() {
            return 1;
        }

        public final int getGroupHandleLow() {
            return 2;
        }

        @Override // androidx.compose.runtime.composer.linkbuffer.changelist.Operation
        public String intParamName(int parameter) {
            return parameter == 0 ? "insertIndex" : parameter == 1 ? "groupHandleHigh" : parameter == 2 ? "groupHandleLow" : super.intParamName(parameter);
        }

        @Override // androidx.compose.runtime.composer.linkbuffer.changelist.Operation
        /* JADX INFO: renamed from: objectParamName-gvac4VY */
        public String mo4585objectParamNamegvac4VY(int parameter) {
            return ObjectParameter.m4610equalsimpl0(parameter, ObjectParameter.m4608constructorimpl(0)) ? "factory" : super.mo4585objectParamNamegvac4VY(parameter);
        }

        @Override // androidx.compose.runtime.composer.linkbuffer.changelist.Operation
        protected long getGroupHandle(OperationArgContainer $this$getGroupHandle, SlotTableEditor slots) {
            return OperationArgContainerKt.getLong($this$getGroupHandle, 1, 2);
        }

        @Override // androidx.compose.runtime.composer.linkbuffer.changelist.Operation
        protected void execute(OperationArgContainer $this$execute, Applier<?> applier, SlotTableEditor slots, RememberManager rememberManager, OperationErrorContext errorContext) {
            Object node = ((Function0) $this$execute.mo4630getObjectgvac4VY(ObjectParameter.m4608constructorimpl(0))).invoke();
            int insertIndex = $this$execute.getInt(0);
            int groupAddress = GroupHandleKt.getGroup(getGroupHandle($this$execute, slots));
            Intrinsics.checkNotNull(applier, "null cannot be cast to non-null type androidx.compose.runtime.Applier<kotlin.Any?>");
            slots.updateNode(groupAddress, node);
            applier.insertTopDown(insertIndex, node);
            applier.down(node);
        }
    }

    /* JADX INFO: compiled from: Operation.kt */
    @Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0010\u001a\u00020\u00112\n\u0010\u0012\u001a\u00060\u000bj\u0002`\u0013H\u0016J\u001b\u0010\u0014\u001a\u00020\u00112\n\u0010\u0012\u001a\u0006\u0012\u0002\b\u00030\u0005H\u0016¢\u0006\u0004\b\u0015\u0010\u0016J2\u0010\u0017\u001a\u00020\u0018*\u00020\u00192\n\u0010\u001a\u001a\u0006\u0012\u0002\b\u00030\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010!H\u0014R \u0010\u0004\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00060\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0012\u0010\n\u001a\u00020\u000b8Æ\u0002¢\u0006\u0006\u001a\u0004\b\f\u0010\tR\u0018\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\u000f\u0010\t¨\u0006\""}, d2 = {"Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation$InsertNodeFixupByAnchor;", "Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation;", "<init>", "()V", "Factory", "Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation$ObjectParameter;", "Lkotlin/Function0;", "", "getFactory-s5KIjrA", "()I", "InsertIndex", "", "getInsertIndex", "Anchor", "Landroidx/compose/runtime/composer/linkbuffer/LinkAnchor;", "getAnchor-s5KIjrA", "intParamName", "", "parameter", "Landroidx/compose/runtime/composer/linkbuffer/changelist/IntParameter;", "objectParamName", "objectParamName-gvac4VY", "(I)Ljava/lang/String;", "execute", "", "Landroidx/compose/runtime/composer/linkbuffer/changelist/OperationArgContainer;", "applier", "Landroidx/compose/runtime/Applier;", "slots", "Landroidx/compose/runtime/composer/linkbuffer/SlotTableEditor;", "rememberManager", "Landroidx/compose/runtime/composer/RememberManager;", "errorContext", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationErrorContext;", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class InsertNodeFixupByAnchor extends Operation {
        public static final int $stable = 0;
        public static final InsertNodeFixupByAnchor INSTANCE = new InsertNodeFixupByAnchor();

        private InsertNodeFixupByAnchor() {
            super(1, 2, false, 4, null);
        }

        /* JADX INFO: renamed from: getFactory-s5KIjrA, reason: not valid java name */
        public final int m4603getFactorys5KIjrA() {
            return ObjectParameter.m4608constructorimpl(0);
        }

        public final int getInsertIndex() {
            return 0;
        }

        /* JADX INFO: renamed from: getAnchor-s5KIjrA, reason: not valid java name */
        public final int m4602getAnchors5KIjrA() {
            return ObjectParameter.m4608constructorimpl(1);
        }

        @Override // androidx.compose.runtime.composer.linkbuffer.changelist.Operation
        public String intParamName(int parameter) {
            return parameter == 0 ? "insertIndex" : super.intParamName(parameter);
        }

        @Override // androidx.compose.runtime.composer.linkbuffer.changelist.Operation
        /* JADX INFO: renamed from: objectParamName-gvac4VY */
        public String mo4585objectParamNamegvac4VY(int parameter) {
            return ObjectParameter.m4610equalsimpl0(parameter, ObjectParameter.m4608constructorimpl(0)) ? "factory" : ObjectParameter.m4610equalsimpl0(parameter, ObjectParameter.m4608constructorimpl(1)) ? "anchor" : super.mo4585objectParamNamegvac4VY(parameter);
        }

        @Override // androidx.compose.runtime.composer.linkbuffer.changelist.Operation
        protected void execute(OperationArgContainer $this$execute, Applier<?> applier, SlotTableEditor slots, RememberManager rememberManager, OperationErrorContext errorContext) {
            Object node = ((Function0) $this$execute.mo4630getObjectgvac4VY(ObjectParameter.m4608constructorimpl(0))).invoke();
            int insertIndex = $this$execute.getInt(0);
            int groupAddress = ((LinkAnchor) $this$execute.mo4630getObjectgvac4VY(ObjectParameter.m4608constructorimpl(1))).getAddress();
            Intrinsics.checkNotNull(applier, "null cannot be cast to non-null type androidx.compose.runtime.Applier<kotlin.Any?>");
            slots.updateNode(groupAddress, node);
            applier.insertTopDown(insertIndex, node);
            applier.down(node);
        }
    }

    /* JADX INFO: compiled from: Operation.kt */
    @Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\f\u001a\u00020\r2\n\u0010\u000e\u001a\u00060\u0005j\u0002`\u000fH\u0016J\u0018\u0010\u0010\u001a\u00060\u0011j\u0002`\u0012*\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0014J2\u0010\u0016\u001a\u00020\u0017*\u00020\u00132\n\u0010\u0018\u001a\u0006\u0012\u0002\b\u00030\u00192\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0014R\u0012\u0010\u0004\u001a\u00020\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0012\u0010\b\u001a\u00020\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\t\u0010\u0007R\u0012\u0010\n\u001a\u00020\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\u000b\u0010\u0007¨\u0006\u001e"}, d2 = {"Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation$PostInsertNodeFixup;", "Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation;", "<init>", "()V", "InsertIndex", "", "getInsertIndex", "()I", "GroupHandleHigh", "getGroupHandleHigh", "GroupHandleLow", "getGroupHandleLow", "intParamName", "", "parameter", "Landroidx/compose/runtime/composer/linkbuffer/changelist/IntParameter;", "getGroupHandle", "", "Landroidx/compose/runtime/composer/linkbuffer/GroupHandle;", "Landroidx/compose/runtime/composer/linkbuffer/changelist/OperationArgContainer;", "slots", "Landroidx/compose/runtime/composer/linkbuffer/SlotTableEditor;", "execute", "", "applier", "Landroidx/compose/runtime/Applier;", "rememberManager", "Landroidx/compose/runtime/composer/RememberManager;", "errorContext", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationErrorContext;", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class PostInsertNodeFixup extends Operation {
        public static final int $stable = 0;
        public static final PostInsertNodeFixup INSTANCE = new PostInsertNodeFixup();

        private PostInsertNodeFixup() {
            super(3, 0, false, 6, null);
        }

        public final int getInsertIndex() {
            return 0;
        }

        public final int getGroupHandleHigh() {
            return 1;
        }

        public final int getGroupHandleLow() {
            return 2;
        }

        @Override // androidx.compose.runtime.composer.linkbuffer.changelist.Operation
        public String intParamName(int parameter) {
            return parameter == 0 ? "insertIndex" : parameter == 1 ? "groupHandleHigh" : parameter == 2 ? "groupHandleLow" : super.intParamName(parameter);
        }

        @Override // androidx.compose.runtime.composer.linkbuffer.changelist.Operation
        protected long getGroupHandle(OperationArgContainer $this$getGroupHandle, SlotTableEditor slots) {
            return OperationArgContainerKt.getLong($this$getGroupHandle, 1, 2);
        }

        @Override // androidx.compose.runtime.composer.linkbuffer.changelist.Operation
        protected void execute(OperationArgContainer $this$execute, Applier<?> applier, SlotTableEditor slots, RememberManager rememberManager, OperationErrorContext errorContext) {
            int insertIndex = $this$execute.getInt(0);
            int groupAddress = GroupHandleKt.getGroup(OperationArgContainerKt.getLong($this$execute, 1, 2));
            applier.up();
            Intrinsics.checkNotNull(applier, "null cannot be cast to non-null type androidx.compose.runtime.Applier<kotlin.Any?>");
            applier.insertBottomUp(insertIndex, slots.node(groupAddress));
        }
    }

    /* JADX INFO: compiled from: Operation.kt */
    @Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\f\u001a\u00020\r2\n\u0010\u000e\u001a\u00060\u0005j\u0002`\u000fH\u0016J\u001b\u0010\u0010\u001a\u00020\r2\n\u0010\u000e\u001a\u0006\u0012\u0002\b\u00030\tH\u0016¢\u0006\u0004\b\u0011\u0010\u0012J2\u0010\u0013\u001a\u00020\u0014*\u00020\u00152\n\u0010\u0016\u001a\u0006\u0012\u0002\b\u00030\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0014R\u0012\u0010\u0004\u001a\u00020\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0018\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t8Æ\u0002¢\u0006\u0006\u001a\u0004\b\u000b\u0010\u0007¨\u0006\u001e"}, d2 = {"Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation$PostInsertNodeFixupByAnchor;", "Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation;", "<init>", "()V", "InsertIndex", "", "getInsertIndex", "()I", "Anchor", "Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation$ObjectParameter;", "Landroidx/compose/runtime/composer/linkbuffer/LinkAnchor;", "getAnchor-s5KIjrA", "intParamName", "", "parameter", "Landroidx/compose/runtime/composer/linkbuffer/changelist/IntParameter;", "objectParamName", "objectParamName-gvac4VY", "(I)Ljava/lang/String;", "execute", "", "Landroidx/compose/runtime/composer/linkbuffer/changelist/OperationArgContainer;", "applier", "Landroidx/compose/runtime/Applier;", "slots", "Landroidx/compose/runtime/composer/linkbuffer/SlotTableEditor;", "rememberManager", "Landroidx/compose/runtime/composer/RememberManager;", "errorContext", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationErrorContext;", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class PostInsertNodeFixupByAnchor extends Operation {
        public static final int $stable = 0;
        public static final PostInsertNodeFixupByAnchor INSTANCE = new PostInsertNodeFixupByAnchor();

        private PostInsertNodeFixupByAnchor() {
            super(1, 1, false, 4, null);
        }

        public final int getInsertIndex() {
            return 0;
        }

        /* JADX INFO: renamed from: getAnchor-s5KIjrA, reason: not valid java name */
        public final int m4614getAnchors5KIjrA() {
            return ObjectParameter.m4608constructorimpl(0);
        }

        @Override // androidx.compose.runtime.composer.linkbuffer.changelist.Operation
        public String intParamName(int parameter) {
            return parameter == 0 ? "insertIndex" : super.intParamName(parameter);
        }

        @Override // androidx.compose.runtime.composer.linkbuffer.changelist.Operation
        /* JADX INFO: renamed from: objectParamName-gvac4VY */
        public String mo4585objectParamNamegvac4VY(int parameter) {
            return ObjectParameter.m4610equalsimpl0(parameter, ObjectParameter.m4608constructorimpl(0)) ? "anchor" : super.mo4585objectParamNamegvac4VY(parameter);
        }

        @Override // androidx.compose.runtime.composer.linkbuffer.changelist.Operation
        protected void execute(OperationArgContainer $this$execute, Applier<?> applier, SlotTableEditor slots, RememberManager rememberManager, OperationErrorContext errorContext) {
            int insertIndex = $this$execute.getInt(0);
            int groupAddress = ((LinkAnchor) $this$execute.mo4630getObjectgvac4VY(ObjectParameter.m4608constructorimpl(0))).getAddress();
            applier.up();
            Intrinsics.checkNotNull(applier, "null cannot be cast to non-null type androidx.compose.runtime.Applier<kotlin.Any?>");
            applier.insertBottomUp(insertIndex, slots.node(groupAddress));
        }
    }

    /* JADX INFO: compiled from: Operation.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J2\u0010\u0004\u001a\u00020\u0005*\u00020\u00062\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0014¨\u0006\u000f"}, d2 = {"Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation$DeactivateGroup;", "Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation;", "<init>", "()V", "execute", "", "Landroidx/compose/runtime/composer/linkbuffer/changelist/OperationArgContainer;", "applier", "Landroidx/compose/runtime/Applier;", "slots", "Landroidx/compose/runtime/composer/linkbuffer/SlotTableEditor;", "rememberManager", "Landroidx/compose/runtime/composer/RememberManager;", "errorContext", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationErrorContext;", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class DeactivateGroup extends Operation {
        public static final int $stable = 0;
        public static final DeactivateGroup INSTANCE = new DeactivateGroup();

        private DeactivateGroup() {
            super(0, 0, false, 7, null);
        }

        @Override // androidx.compose.runtime.composer.linkbuffer.changelist.Operation
        protected void execute(OperationArgContainer $this$execute, Applier<?> applier, SlotTableEditor slots, RememberManager rememberManager, OperationErrorContext errorContext) {
            SlotTableEditorKt.deactivateGroup(slots, rememberManager);
        }
    }

    /* JADX INFO: compiled from: Operation.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J2\u0010\u0004\u001a\u00020\u0005*\u00020\u00062\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0014¨\u0006\u000f"}, d2 = {"Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation$ResetSlots;", "Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation;", "<init>", "()V", "execute", "", "Landroidx/compose/runtime/composer/linkbuffer/changelist/OperationArgContainer;", "applier", "Landroidx/compose/runtime/Applier;", "slots", "Landroidx/compose/runtime/composer/linkbuffer/SlotTableEditor;", "rememberManager", "Landroidx/compose/runtime/composer/RememberManager;", "errorContext", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationErrorContext;", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class ResetSlots extends Operation {
        public static final int $stable = 0;
        public static final ResetSlots INSTANCE = new ResetSlots();

        private ResetSlots() {
            super(0, 0, false, 7, null);
        }

        @Override // androidx.compose.runtime.composer.linkbuffer.changelist.Operation
        protected void execute(OperationArgContainer $this$execute, Applier<?> applier, SlotTableEditor slots, RememberManager rememberManager, OperationErrorContext errorContext) {
            slots.reset();
        }
    }

    /* JADX INFO: compiled from: Operation.kt */
    @Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u000e\u001a\u00020\u000f2\n\u0010\u0010\u001a\u00060\nj\u0002`\u0011H\u0016J\u001b\u0010\u0012\u001a\u00020\u000f2\n\u0010\u0010\u001a\u0006\u0012\u0002\b\u00030\u0005H\u0016¢\u0006\u0004\b\u0013\u0010\u0014J2\u0010\u0015\u001a\u00020\u0016*\u00020\u00172\n\u0010\u0018\u001a\u0006\u0012\u0002\b\u00030\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0014R\u0018\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0012\u0010\t\u001a\u00020\n8Æ\u0002¢\u0006\u0006\u001a\u0004\b\u000b\u0010\bR\u0012\u0010\f\u001a\u00020\n8Æ\u0002¢\u0006\u0006\u001a\u0004\b\r\u0010\b¨\u0006 "}, d2 = {"Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation$DetermineMovableContentNodeIndex;", "Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation;", "<init>", "()V", "EffectiveNodeIndexOut", "Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation$ObjectParameter;", "Landroidx/compose/runtime/internal/IntRef;", "getEffectiveNodeIndexOut-s5KIjrA", "()I", "GroupHandleLowBits", "", "getGroupHandleLowBits", "GroupHandleHighBits", "getGroupHandleHighBits", "intParamName", "", "parameter", "Landroidx/compose/runtime/composer/linkbuffer/changelist/IntParameter;", "objectParamName", "objectParamName-gvac4VY", "(I)Ljava/lang/String;", "execute", "", "Landroidx/compose/runtime/composer/linkbuffer/changelist/OperationArgContainer;", "applier", "Landroidx/compose/runtime/Applier;", "slots", "Landroidx/compose/runtime/composer/linkbuffer/SlotTableEditor;", "rememberManager", "Landroidx/compose/runtime/composer/RememberManager;", "errorContext", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationErrorContext;", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class DetermineMovableContentNodeIndex extends Operation {
        public static final int $stable = 0;
        public static final DetermineMovableContentNodeIndex INSTANCE = new DetermineMovableContentNodeIndex();

        private DetermineMovableContentNodeIndex() {
            super(2, 1, false, 4, null);
        }

        /* JADX INFO: renamed from: getEffectiveNodeIndexOut-s5KIjrA, reason: not valid java name */
        public final int m4595getEffectiveNodeIndexOuts5KIjrA() {
            return ObjectParameter.m4608constructorimpl(0);
        }

        public final int getGroupHandleLowBits() {
            return 0;
        }

        public final int getGroupHandleHighBits() {
            return 1;
        }

        @Override // androidx.compose.runtime.composer.linkbuffer.changelist.Operation
        public String intParamName(int parameter) {
            return parameter == 0 ? "groupHandle[32..63]" : parameter == 1 ? "groupHandle[0..31]" : super.intParamName(parameter);
        }

        @Override // androidx.compose.runtime.composer.linkbuffer.changelist.Operation
        /* JADX INFO: renamed from: objectParamName-gvac4VY */
        public String mo4585objectParamNamegvac4VY(int parameter) {
            return ObjectParameter.m4610equalsimpl0(parameter, ObjectParameter.m4608constructorimpl(0)) ? "effectiveNodeIndexOut" : super.mo4585objectParamNamegvac4VY(parameter);
        }

        @Override // androidx.compose.runtime.composer.linkbuffer.changelist.Operation
        protected void execute(OperationArgContainer $this$execute, Applier<?> applier, SlotTableEditor slots, RememberManager rememberManager, OperationErrorContext errorContext) {
            IntRef effectiveNodeIndexOut = (IntRef) $this$execute.mo4630getObjectgvac4VY(ObjectParameter.m4608constructorimpl(0));
            long j = OperationArgContainerKt.getLong($this$execute, 1, 0);
            Intrinsics.checkNotNull(applier, "null cannot be cast to non-null type androidx.compose.runtime.Applier<kotlin.Any?>");
            effectiveNodeIndexOut.setElement(OperationKt.positionToInsert(slots, j, applier));
        }
    }

    /* JADX INFO: compiled from: Operation.kt */
    @Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\r\u001a\u00020\u000e2\n\u0010\u000f\u001a\u0006\u0012\u0002\b\u00030\u0005H\u0016¢\u0006\u0004\b\u0010\u0010\u0011J2\u0010\u0012\u001a\u00020\u0013*\u00020\u00142\n\u0010\u0015\u001a\u0006\u0012\u0002\b\u00030\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0014R\u0018\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR \u0010\t\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\n0\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\f\u0010\b¨\u0006\u001d"}, d2 = {"Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation$CopyNodesToNewAnchorLocation;", "Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation;", "<init>", "()V", "EffectiveNodeIndex", "Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation$ObjectParameter;", "Landroidx/compose/runtime/internal/IntRef;", "getEffectiveNodeIndex-s5KIjrA", "()I", "Nodes", "", "", "getNodes-s5KIjrA", "objectParamName", "", "parameter", "objectParamName-gvac4VY", "(I)Ljava/lang/String;", "execute", "", "Landroidx/compose/runtime/composer/linkbuffer/changelist/OperationArgContainer;", "applier", "Landroidx/compose/runtime/Applier;", "slots", "Landroidx/compose/runtime/composer/linkbuffer/SlotTableEditor;", "rememberManager", "Landroidx/compose/runtime/composer/RememberManager;", "errorContext", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationErrorContext;", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class CopyNodesToNewAnchorLocation extends Operation {
        public static final int $stable = 0;
        public static final CopyNodesToNewAnchorLocation INSTANCE = new CopyNodesToNewAnchorLocation();

        private CopyNodesToNewAnchorLocation() {
            super(0, 2, false, 5, null);
        }

        /* JADX INFO: renamed from: getEffectiveNodeIndex-s5KIjrA, reason: not valid java name */
        public final int m4589getEffectiveNodeIndexs5KIjrA() {
            return ObjectParameter.m4608constructorimpl(0);
        }

        /* JADX INFO: renamed from: getNodes-s5KIjrA, reason: not valid java name */
        public final int m4590getNodess5KIjrA() {
            return ObjectParameter.m4608constructorimpl(1);
        }

        @Override // androidx.compose.runtime.composer.linkbuffer.changelist.Operation
        /* JADX INFO: renamed from: objectParamName-gvac4VY */
        public String mo4585objectParamNamegvac4VY(int parameter) {
            return ObjectParameter.m4610equalsimpl0(parameter, ObjectParameter.m4608constructorimpl(0)) ? "effectiveNodeIndex" : ObjectParameter.m4610equalsimpl0(parameter, ObjectParameter.m4608constructorimpl(1)) ? "nodes" : super.mo4585objectParamNamegvac4VY(parameter);
        }

        @Override // androidx.compose.runtime.composer.linkbuffer.changelist.Operation
        protected void execute(OperationArgContainer $this$execute, Applier<?> applier, SlotTableEditor slots, RememberManager rememberManager, OperationErrorContext errorContext) {
            int effectiveNodeIndex = ((IntRef) $this$execute.mo4630getObjectgvac4VY(ObjectParameter.m4608constructorimpl(0))).getElement();
            List nodesToInsert = (List) $this$execute.mo4630getObjectgvac4VY(ObjectParameter.m4608constructorimpl(1));
            int size = nodesToInsert.size();
            for (int index$iv = 0; index$iv < size; index$iv++) {
                Object item$iv = nodesToInsert.get(index$iv);
                int i = index$iv;
                Intrinsics.checkNotNull(applier, "null cannot be cast to non-null type androidx.compose.runtime.Applier<kotlin.Any?>");
                applier.insertBottomUp(effectiveNodeIndex + i, item$iv);
                applier.insertTopDown(effectiveNodeIndex + i, item$iv);
            }
        }
    }

    /* JADX INFO: compiled from: Operation.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J2\u0010\u0004\u001a\u00020\u0005*\u00020\u00062\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0014¨\u0006\u000f"}, d2 = {"Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation$EndMovableContentPlacement;", "Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation;", "<init>", "()V", "execute", "", "Landroidx/compose/runtime/composer/linkbuffer/changelist/OperationArgContainer;", "applier", "Landroidx/compose/runtime/Applier;", "slots", "Landroidx/compose/runtime/composer/linkbuffer/SlotTableEditor;", "rememberManager", "Landroidx/compose/runtime/composer/RememberManager;", "errorContext", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationErrorContext;", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class EndMovableContentPlacement extends Operation {
        public static final int $stable = 0;
        public static final EndMovableContentPlacement INSTANCE = new EndMovableContentPlacement();

        private EndMovableContentPlacement() {
            super(0, 0, false, 7, null);
        }

        @Override // androidx.compose.runtime.composer.linkbuffer.changelist.Operation
        protected void execute(OperationArgContainer $this$execute, Applier<?> applier, SlotTableEditor slots, RememberManager rememberManager, OperationErrorContext errorContext) {
            while (slots.getParent() >= 0) {
                if (slots.isParentGroupANode()) {
                    applier.up();
                }
                slots.endGroup();
            }
        }
    }

    /* JADX INFO: compiled from: Operation.kt */
    @Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\u0011\u001a\u00020\u00122\n\u0010\u0013\u001a\u0006\u0012\u0002\b\u00030\u0005H\u0016¢\u0006\u0004\b\u0014\u0010\u0015J2\u0010\u0016\u001a\u00020\u0017*\u00020\u00182\n\u0010\u0019\u001a\u0006\u0012\u0002\b\u00030\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0014R\u001a\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0018\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\u000b\u0010\bR\u0018\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\u000e\u0010\bR\u0018\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\r0\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0010\u0010\b¨\u0006!"}, d2 = {"Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation$CopySlotTableToHandleLocation;", "Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation;", "<init>", "()V", "ResolvedState", "Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation$ObjectParameter;", "Landroidx/compose/runtime/MovableContentState;", "getResolvedState-s5KIjrA", "()I", "ParentCompositionContext", "Landroidx/compose/runtime/CompositionContext;", "getParentCompositionContext-s5KIjrA", "From", "Landroidx/compose/runtime/MovableContentStateReference;", "getFrom-s5KIjrA", "To", "getTo-s5KIjrA", "objectParamName", "", "parameter", "objectParamName-gvac4VY", "(I)Ljava/lang/String;", "execute", "", "Landroidx/compose/runtime/composer/linkbuffer/changelist/OperationArgContainer;", "applier", "Landroidx/compose/runtime/Applier;", "slots", "Landroidx/compose/runtime/composer/linkbuffer/SlotTableEditor;", "rememberManager", "Landroidx/compose/runtime/composer/RememberManager;", "errorContext", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationErrorContext;", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class CopySlotTableToHandleLocation extends Operation {
        public static final int $stable = 0;
        public static final CopySlotTableToHandleLocation INSTANCE = new CopySlotTableToHandleLocation();

        private CopySlotTableToHandleLocation() {
            super(0, 4, false, 5, null);
        }

        /* JADX INFO: renamed from: getResolvedState-s5KIjrA, reason: not valid java name */
        public final int m4593getResolvedStates5KIjrA() {
            return ObjectParameter.m4608constructorimpl(0);
        }

        /* JADX INFO: renamed from: getParentCompositionContext-s5KIjrA, reason: not valid java name */
        public final int m4592getParentCompositionContexts5KIjrA() {
            return ObjectParameter.m4608constructorimpl(1);
        }

        /* JADX INFO: renamed from: getFrom-s5KIjrA, reason: not valid java name */
        public final int m4591getFroms5KIjrA() {
            return ObjectParameter.m4608constructorimpl(2);
        }

        /* JADX INFO: renamed from: getTo-s5KIjrA, reason: not valid java name */
        public final int m4594getTos5KIjrA() {
            return ObjectParameter.m4608constructorimpl(3);
        }

        @Override // androidx.compose.runtime.composer.linkbuffer.changelist.Operation
        /* JADX INFO: renamed from: objectParamName-gvac4VY */
        public String mo4585objectParamNamegvac4VY(int parameter) {
            return ObjectParameter.m4610equalsimpl0(parameter, ObjectParameter.m4608constructorimpl(0)) ? "resolvedState" : ObjectParameter.m4610equalsimpl0(parameter, ObjectParameter.m4608constructorimpl(1)) ? "resolvedCompositionContext" : ObjectParameter.m4610equalsimpl0(parameter, ObjectParameter.m4608constructorimpl(2)) ? TypedValues.TransitionType.S_FROM : ObjectParameter.m4610equalsimpl0(parameter, ObjectParameter.m4608constructorimpl(3)) ? TypedValues.TransitionType.S_TO : super.mo4585objectParamNamegvac4VY(parameter);
        }

        @Override // androidx.compose.runtime.composer.linkbuffer.changelist.Operation
        protected void execute(OperationArgContainer $this$execute, Applier<?> applier, SlotTableEditor slots, RememberManager rememberManager, OperationErrorContext errorContext) {
            MovableContentStateReference from = (MovableContentStateReference) $this$execute.mo4630getObjectgvac4VY(ObjectParameter.m4608constructorimpl(2));
            MovableContentStateReference to = (MovableContentStateReference) $this$execute.mo4630getObjectgvac4VY(ObjectParameter.m4608constructorimpl(3));
            CompositionContext parentCompositionContext = (CompositionContext) $this$execute.mo4630getObjectgvac4VY(ObjectParameter.m4608constructorimpl(1));
            MovableContentState movableContentStateMovableContentStateResolve$runtime = (MovableContentState) $this$execute.mo4630getObjectgvac4VY(ObjectParameter.m4608constructorimpl(0));
            if (movableContentStateMovableContentStateResolve$runtime != null || (movableContentStateMovableContentStateResolve$runtime = parentCompositionContext.movableContentStateResolve$runtime(from)) != null) {
                MovableContentState resolvedState = movableContentStateMovableContentStateResolve$runtime;
                SlotTable resolvedTable = SlotTableKt.asLinkBufferSlotTable(resolvedState.getSlotStorage());
                SlotTableEditor $this$edit_u24lambda_u240$iv = resolvedTable.openEditor();
                try {
                    $this$edit_u24lambda_u240$iv.startGroup();
                    $this$edit_u24lambda_u240$iv.startGroup();
                    long jHandle = $this$edit_u24lambda_u240$iv.handle();
                    int groupContext$iv = slots.firstChildOf(slots.getCurrent());
                    long newGroup = slots.moveFrom($this$edit_u24lambda_u240$iv, jHandle, (((long) groupContext$iv) << 32) | (((long) UInt.m9024constructorimpl(-1)) & 4294967295L));
                    $this$edit_u24lambda_u240$iv.close();
                    SlotTable table = slots.getTable();
                    int group = GroupHandleKt.getGroup(newGroup);
                    ControlledComposition composition = to.getComposition();
                    Intrinsics.checkNotNull(composition, "null cannot be cast to non-null type androidx.compose.runtime.RecomposeScopeOwner");
                    SlotTableKt.adoptScopesInGroupToNewParent(table, group, (RecomposeScopeOwner) composition);
                    return;
                } catch (Throwable th) {
                    $this$edit_u24lambda_u240$iv.close();
                    throw th;
                }
            }
            ComposerKt.composeRuntimeError("Could not resolve state for movable content");
            throw new KotlinNothingValueException();
        }
    }

    /* JADX INFO: compiled from: Operation.kt */
    @Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\u000f\u001a\u00020\u00102\n\u0010\u0011\u001a\u0006\u0012\u0002\b\u00030\u0005H\u0016¢\u0006\u0004\b\u0012\u0010\u0013J2\u0010\u0014\u001a\u00020\u0015*\u00020\u00162\n\u0010\u0017\u001a\u0006\u0012\u0002\b\u00030\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0014R\u0018\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0018\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\u000b\u0010\bR\u0018\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\u000e\u0010\b¨\u0006\u001f"}, d2 = {"Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation$ReleaseMovableGroup;", "Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation;", "<init>", "()V", "Composition", "Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation$ObjectParameter;", "Landroidx/compose/runtime/ControlledComposition;", "getComposition-s5KIjrA", "()I", "ParentCompositionContext", "Landroidx/compose/runtime/CompositionContext;", "getParentCompositionContext-s5KIjrA", "Reference", "Landroidx/compose/runtime/MovableContentStateReference;", "getReference-s5KIjrA", "objectParamName", "", "parameter", "objectParamName-gvac4VY", "(I)Ljava/lang/String;", "execute", "", "Landroidx/compose/runtime/composer/linkbuffer/changelist/OperationArgContainer;", "applier", "Landroidx/compose/runtime/Applier;", "slots", "Landroidx/compose/runtime/composer/linkbuffer/SlotTableEditor;", "rememberManager", "Landroidx/compose/runtime/composer/RememberManager;", "errorContext", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationErrorContext;", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class ReleaseMovableGroup extends Operation {
        public static final int $stable = 0;
        public static final ReleaseMovableGroup INSTANCE = new ReleaseMovableGroup();

        private ReleaseMovableGroup() {
            super(0, 3, false, 5, null);
        }

        /* JADX INFO: renamed from: getComposition-s5KIjrA, reason: not valid java name */
        public final int m4615getCompositions5KIjrA() {
            return ObjectParameter.m4608constructorimpl(0);
        }

        /* JADX INFO: renamed from: getParentCompositionContext-s5KIjrA, reason: not valid java name */
        public final int m4616getParentCompositionContexts5KIjrA() {
            return ObjectParameter.m4608constructorimpl(1);
        }

        /* JADX INFO: renamed from: getReference-s5KIjrA, reason: not valid java name */
        public final int m4617getReferences5KIjrA() {
            return ObjectParameter.m4608constructorimpl(2);
        }

        @Override // androidx.compose.runtime.composer.linkbuffer.changelist.Operation
        /* JADX INFO: renamed from: objectParamName-gvac4VY */
        public String mo4585objectParamNamegvac4VY(int parameter) {
            return ObjectParameter.m4610equalsimpl0(parameter, ObjectParameter.m4608constructorimpl(0)) ? "composition" : ObjectParameter.m4610equalsimpl0(parameter, ObjectParameter.m4608constructorimpl(1)) ? "parentCompositionContext" : ObjectParameter.m4610equalsimpl0(parameter, ObjectParameter.m4608constructorimpl(2)) ? TypedValues.Custom.S_REFERENCE : super.mo4585objectParamNamegvac4VY(parameter);
        }

        @Override // androidx.compose.runtime.composer.linkbuffer.changelist.Operation
        protected void execute(OperationArgContainer $this$execute, Applier<?> applier, SlotTableEditor slots, RememberManager rememberManager, OperationErrorContext errorContext) {
            OperationKt.releaseMovableGroup((ControlledComposition) $this$execute.mo4630getObjectgvac4VY(ObjectParameter.m4608constructorimpl(0)), (CompositionContext) $this$execute.mo4630getObjectgvac4VY(ObjectParameter.m4608constructorimpl(1)), (MovableContentStateReference) $this$execute.mo4630getObjectgvac4VY(ObjectParameter.m4608constructorimpl(2)), slots, applier);
        }
    }

    /* JADX INFO: compiled from: Operation.kt */
    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\t\u001a\u00020\n2\n\u0010\u000b\u001a\u0006\u0012\u0002\b\u00030\u0005H\u0016¢\u0006\u0004\b\f\u0010\rJ2\u0010\u000e\u001a\u00020\u000f*\u00020\u00102\n\u0010\u0011\u001a\u0006\u0012\u0002\b\u00030\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0014R\u0018\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\u0019"}, d2 = {"Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation$DisposeMovableContentState;", "Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation;", "<init>", "()V", "ResolvedState", "Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation$ObjectParameter;", "Landroidx/compose/runtime/MovableContentState;", "getResolvedState-s5KIjrA", "()I", "objectParamName", "", "parameter", "objectParamName-gvac4VY", "(I)Ljava/lang/String;", "execute", "", "Landroidx/compose/runtime/composer/linkbuffer/changelist/OperationArgContainer;", "applier", "Landroidx/compose/runtime/Applier;", "slots", "Landroidx/compose/runtime/composer/linkbuffer/SlotTableEditor;", "rememberManager", "Landroidx/compose/runtime/composer/RememberManager;", "errorContext", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationErrorContext;", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class DisposeMovableContentState extends Operation {
        public static final int $stable = 0;
        public static final DisposeMovableContentState INSTANCE = new DisposeMovableContentState();

        private DisposeMovableContentState() {
            super(0, 1, false, 5, null);
        }

        /* JADX INFO: renamed from: getResolvedState-s5KIjrA, reason: not valid java name */
        public final int m4596getResolvedStates5KIjrA() {
            return ObjectParameter.m4608constructorimpl(0);
        }

        @Override // androidx.compose.runtime.composer.linkbuffer.changelist.Operation
        /* JADX INFO: renamed from: objectParamName-gvac4VY */
        public String mo4585objectParamNamegvac4VY(int parameter) {
            return ObjectParameter.m4610equalsimpl0(parameter, ObjectParameter.m4608constructorimpl(0)) ? "resolvedState" : super.mo4585objectParamNamegvac4VY(parameter);
        }

        @Override // androidx.compose.runtime.composer.linkbuffer.changelist.Operation
        protected void execute(OperationArgContainer $this$execute, Applier<?> applier, SlotTableEditor slots, RememberManager rememberManager, OperationErrorContext errorContext) {
            MovableContentState resolvedState = (MovableContentState) $this$execute.mo4630getObjectgvac4VY(ObjectParameter.m4608constructorimpl(0));
            resolvedState.dispose$runtime();
        }
    }

    /* JADX INFO: compiled from: Operation.kt */
    @Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\f\u001a\u00020\r2\n\u0010\u000e\u001a\u0006\u0012\u0002\b\u00030\u0005H\u0016¢\u0006\u0004\b\u000f\u0010\u0010J2\u0010\u0011\u001a\u00020\u0012*\u00020\u00132\n\u0010\u0014\u001a\u0006\u0012\u0002\b\u00030\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0014R\u0018\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\u000b\u0010\b¨\u0006\u001c"}, d2 = {"Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation$ApplyChangeList;", "Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation;", "<init>", "()V", "Changes", "Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation$ObjectParameter;", "Landroidx/compose/runtime/composer/linkbuffer/changelist/ChangeList;", "getChanges-s5KIjrA", "()I", "EffectiveNodeIndex", "Landroidx/compose/runtime/internal/IntRef;", "getEffectiveNodeIndex-s5KIjrA", "objectParamName", "", "parameter", "objectParamName-gvac4VY", "(I)Ljava/lang/String;", "execute", "", "Landroidx/compose/runtime/composer/linkbuffer/changelist/OperationArgContainer;", "applier", "Landroidx/compose/runtime/Applier;", "slots", "Landroidx/compose/runtime/composer/linkbuffer/SlotTableEditor;", "rememberManager", "Landroidx/compose/runtime/composer/RememberManager;", "errorContext", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationErrorContext;", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class ApplyChangeList extends Operation {
        public static final int $stable = 0;
        public static final ApplyChangeList INSTANCE = new ApplyChangeList();

        private ApplyChangeList() {
            super(0, 2, false, 1, null);
        }

        /* JADX INFO: renamed from: getChanges-s5KIjrA, reason: not valid java name */
        public final int m4587getChangess5KIjrA() {
            return ObjectParameter.m4608constructorimpl(0);
        }

        /* JADX INFO: renamed from: getEffectiveNodeIndex-s5KIjrA, reason: not valid java name */
        public final int m4588getEffectiveNodeIndexs5KIjrA() {
            return ObjectParameter.m4608constructorimpl(1);
        }

        @Override // androidx.compose.runtime.composer.linkbuffer.changelist.Operation
        /* JADX INFO: renamed from: objectParamName-gvac4VY */
        public String mo4585objectParamNamegvac4VY(int parameter) {
            return ObjectParameter.m4610equalsimpl0(parameter, ObjectParameter.m4608constructorimpl(0)) ? "changes" : ObjectParameter.m4610equalsimpl0(parameter, ObjectParameter.m4608constructorimpl(1)) ? "effectiveNodeIndex" : super.mo4585objectParamNamegvac4VY(parameter);
        }

        @Override // androidx.compose.runtime.composer.linkbuffer.changelist.Operation
        protected void execute(OperationArgContainer $this$execute, Applier<?> applier, SlotTableEditor slots, RememberManager rememberManager, OperationErrorContext errorContext) {
            OffsetApplier offsetApplier;
            IntRef intRef = (IntRef) $this$execute.mo4630getObjectgvac4VY(ObjectParameter.m4608constructorimpl(1));
            int effectiveNodeIndex = intRef != null ? intRef.getElement() : 0;
            ChangeList changeList = (ChangeList) $this$execute.mo4630getObjectgvac4VY(ObjectParameter.m4608constructorimpl(0));
            if (effectiveNodeIndex > 0) {
                offsetApplier = new OffsetApplier(applier, effectiveNodeIndex);
            } else {
                offsetApplier = applier;
            }
            changeList.executeAndFlushAllPendingChanges(offsetApplier, slots, rememberManager, errorContext != null ? OperationKt.withCurrentStackTrace(errorContext, slots) : null);
        }
    }

    /* JADX INFO: compiled from: Operation.kt */
    @Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0007\u0018\u00002\u00020\u0001BC\b\u0007\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012$\b\u0002\u0010\u0005\u001a\u001e\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0007\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\u0006¢\u0006\u0004\b\u000b\u0010\fJ2\u0010\u001a\u001a\u00020\n*\u00020\u001b2\n\u0010\u001c\u001a\u0006\u0012\u0002\b\u00030\u00072\u0006\u0010\u001d\u001a\u00020\b2\u0006\u0010\u001e\u001a\u00020\t2\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0014J\b\u0010!\u001a\u00020\"H\u0016R-\u0010\u0005\u001a\u001e\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0007\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\u0006¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001d\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00030\u0010¢\u0006\u000e\n\u0000\u0012\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014R%\u0010\u0015\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00170\u00160\u0010¢\u0006\u000e\n\u0000\u0012\u0004\b\u0018\u0010\u0012\u001a\u0004\b\u0019\u0010\u0014¨\u0006#"}, d2 = {"Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation$TestOperation;", "Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation;", "ints", "", "objects", "block", "Lkotlin/Function3;", "Landroidx/compose/runtime/Applier;", "Landroidx/compose/runtime/composer/linkbuffer/SlotTableEditor;", "Landroidx/compose/runtime/composer/RememberManager;", "", "<init>", "(IILkotlin/jvm/functions/Function3;)V", "getBlock", "()Lkotlin/jvm/functions/Function3;", "intParams", "", "getIntParams$annotations", "()V", "getIntParams", "()Ljava/util/List;", "objParams", "Landroidx/compose/runtime/composer/linkbuffer/changelist/Operation$ObjectParameter;", "", "getObjParams$annotations", "getObjParams", "execute", "Landroidx/compose/runtime/composer/linkbuffer/changelist/OperationArgContainer;", "applier", "slots", "rememberManager", "errorContext", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationErrorContext;", "toString", "", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class TestOperation extends Operation {
        public static final int $stable = 8;
        private final Function3<Applier<?>, SlotTableEditor, RememberManager, Unit> block;
        private final List<Integer> intParams;
        private final List<ObjectParameter<Object>> objParams;

        public TestOperation() {
            this(0, 0, null, 7, null);
        }

        public static /* synthetic */ void getIntParams$annotations() {
        }

        public static /* synthetic */ void getObjParams$annotations() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        public TestOperation(int ints, int objects, Function3<? super Applier<?>, ? super SlotTableEditor, ? super RememberManager, Unit> function3) {
            super(ints, objects, false, 4, null);
            this.block = function3;
            ArrayList arrayList = new ArrayList(ints);
            for (int i = 0; i < ints; i++) {
                int it = i;
                arrayList.add(Integer.valueOf(it));
            }
            this.intParams = arrayList;
            ArrayList arrayList2 = new ArrayList(objects);
            for (int i2 = 0; i2 < objects; i2++) {
                int index = i2;
                arrayList2.add(ObjectParameter.m4607boximpl(ObjectParameter.m4608constructorimpl(index)));
            }
            this.objParams = arrayList2;
        }

        public /* synthetic */ TestOperation(int i, int i2, Function3 function3, int i3, DefaultConstructorMarker defaultConstructorMarker) {
            this((i3 & 1) != 0 ? 0 : i, (i3 & 2) != 0 ? 0 : i2, (i3 & 4) != 0 ? new Function3() { // from class: androidx.compose.runtime.composer.linkbuffer.changelist.Operation$TestOperation$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return Unit.INSTANCE;
                }
            } : function3);
        }

        public final Function3<Applier<?>, SlotTableEditor, RememberManager, Unit> getBlock() {
            return this.block;
        }

        public final List<Integer> getIntParams() {
            return this.intParams;
        }

        public final List<ObjectParameter<Object>> getObjParams() {
            return this.objParams;
        }

        @Override // androidx.compose.runtime.composer.linkbuffer.changelist.Operation
        protected void execute(OperationArgContainer $this$execute, Applier<?> applier, SlotTableEditor slots, RememberManager rememberManager, OperationErrorContext errorContext) {
            this.block.invoke(applier, slots, rememberManager);
        }

        @Override // androidx.compose.runtime.composer.linkbuffer.changelist.Operation
        public String toString() {
            return "TestOperation(ints = " + getInts() + ", objects = " + getObjects() + ")@" + System_jvmKt.identityHashCode(this);
        }
    }
}
