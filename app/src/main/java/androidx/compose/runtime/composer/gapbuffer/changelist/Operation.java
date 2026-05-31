package androidx.compose.runtime.composer.gapbuffer.changelist;

import androidx.autofill.HintConstants;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.Composition;
import androidx.compose.runtime.CompositionContext;
import androidx.compose.runtime.ControlledComposition;
import androidx.compose.runtime.GapComposerKt;
import androidx.compose.runtime.MovableContentState;
import androidx.compose.runtime.MovableContentStateReference;
import androidx.compose.runtime.OffsetApplier;
import androidx.compose.runtime.RecomposeScopeImpl;
import androidx.compose.runtime.RecomposeScopeOwner;
import androidx.compose.runtime.RememberObserverHolder;
import androidx.compose.runtime.composer.RememberManager;
import androidx.compose.runtime.composer.gapbuffer.GapAnchor;
import androidx.compose.runtime.composer.gapbuffer.SlotTable;
import androidx.compose.runtime.composer.gapbuffer.SlotTableKt;
import androidx.compose.runtime.composer.gapbuffer.SlotWriter;
import androidx.compose.runtime.internal.IntRef;
import androidx.compose.runtime.internal.System_jvmKt;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.util.ArrayList;
import java.util.List;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
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
@Metadata(d1 = {"\u0000è\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b)\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b1\u0018\u00002\u00020\u0001:&$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIB\u001d\b\u0004\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J0\u0010\u000e\u001a\u00020\u000f*\u00020\u00102\n\u0010\u0011\u001a\u0006\u0012\u0002\b\u00030\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018J\u0016\u0010\u0019\u001a\u0004\u0018\u00010\u001a*\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u0014H\u0014J2\u0010\u001b\u001a\u00020\u000f*\u00020\u00102\n\u0010\u0011\u001a\u0006\u0012\u0002\b\u00030\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H$J\u0014\u0010\u001c\u001a\u00020\u000b2\n\u0010\u001d\u001a\u00060\u0003j\u0002`\u001eH\u0016J\u001b\u0010\u001f\u001a\u00020\u000b2\n\u0010\u001d\u001a\u0006\u0012\u0002\b\u00030 H\u0016¢\u0006\u0004\b!\u0010\"J\b\u0010#\u001a\u00020\u000bH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\n\u001a\u00020\u000b8F¢\u0006\u0006\u001a\u0004\b\f\u0010\r\u0082\u0001%JKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmn¨\u0006o"}, d2 = {"Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation;", "", "ints", "", "objects", "<init>", "(II)V", "getInts", "()I", "getObjects", HintConstants.AUTOFILL_HINT_NAME, "", "getName", "()Ljava/lang/String;", "executeWithComposeStackTrace", "", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationArgContainer;", "applier", "Landroidx/compose/runtime/Applier;", "slots", "Landroidx/compose/runtime/composer/gapbuffer/SlotWriter;", "rememberManager", "Landroidx/compose/runtime/composer/RememberManager;", "errorContext", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationErrorContext;", "getGroupAnchor", "Landroidx/compose/runtime/composer/gapbuffer/GapAnchor;", "execute", "intParamName", "parameter", "Landroidx/compose/runtime/composer/gapbuffer/changelist/IntParameter;", "objectParamName", "Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation$ObjectParameter;", "objectParamName-PtL-UHM", "(I)Ljava/lang/String;", "toString", "ObjectParameter", "Ups", "Downs", "AdvanceSlotsBy", "SideEffect", "Remember", "RememberPausingScope", "StartResumingScope", "EndResumingScope", "AppendValue", "TrimParentValues", "UpdateValue", "UpdateAnchoredValue", "UpdateAuxData", "EnsureRootGroupStarted", "EnsureGroupStarted", "RemoveCurrentGroup", "MoveCurrentGroup", "EndCurrentGroup", "SkipToEndOfCurrentGroup", "EndCompositionScope", "UseCurrentNode", "UpdateNode", "RemoveNode", "MoveNode", "InsertSlots", "InsertSlotsWithFixups", "InsertNodeFixup", "PostInsertNodeFixup", "DeactivateCurrentGroup", "ResetSlots", "DetermineMovableContentNodeIndex", "CopyNodesToNewAnchorLocation", "CopySlotTableToAnchorLocation", "EndMovableContentPlacement", "ReleaseMovableGroupAtCurrent", "ApplyChangeList", "TestOperation", "Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation$AdvanceSlotsBy;", "Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation$AppendValue;", "Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation$ApplyChangeList;", "Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation$CopyNodesToNewAnchorLocation;", "Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation$CopySlotTableToAnchorLocation;", "Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation$DeactivateCurrentGroup;", "Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation$DetermineMovableContentNodeIndex;", "Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation$Downs;", "Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation$EndCompositionScope;", "Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation$EndCurrentGroup;", "Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation$EndMovableContentPlacement;", "Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation$EndResumingScope;", "Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation$EnsureGroupStarted;", "Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation$EnsureRootGroupStarted;", "Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation$InsertNodeFixup;", "Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation$InsertSlots;", "Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation$InsertSlotsWithFixups;", "Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation$MoveCurrentGroup;", "Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation$MoveNode;", "Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation$PostInsertNodeFixup;", "Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation$ReleaseMovableGroupAtCurrent;", "Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation$Remember;", "Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation$RememberPausingScope;", "Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation$RemoveCurrentGroup;", "Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation$RemoveNode;", "Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation$ResetSlots;", "Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation$SideEffect;", "Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation$SkipToEndOfCurrentGroup;", "Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation$StartResumingScope;", "Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation$TestOperation;", "Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation$TrimParentValues;", "Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation$UpdateAnchoredValue;", "Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation$UpdateAuxData;", "Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation$UpdateNode;", "Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation$UpdateValue;", "Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation$Ups;", "Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation$UseCurrentNode;", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
public abstract class Operation {
    public static final int $stable = 0;
    private final int ints;
    private final int objects;

    public /* synthetic */ Operation(int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, i2);
    }

    protected abstract void execute(OperationArgContainer operationArgContainer, Applier<?> applier, SlotWriter slotWriter, RememberManager rememberManager, OperationErrorContext operationErrorContext);

    private Operation(int ints, int objects) {
        this.ints = ints;
        this.objects = objects;
    }

    public /* synthetic */ Operation(int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? 0 : i, (i3 & 2) != 0 ? 0 : i2, null);
    }

    public final int getInts() {
        return this.ints;
    }

    public final int getObjects() {
        return this.objects;
    }

    public final String getName() {
        String simpleName = Reflection.getOrCreateKotlinClass(getClass()).getSimpleName();
        return simpleName == null ? "" : simpleName;
    }

    public final void executeWithComposeStackTrace(OperationArgContainer $this$executeWithComposeStackTrace, Applier<?> applier, SlotWriter slots, RememberManager rememberManager, OperationErrorContext errorContext) throws Throwable {
        GapAnchor location$iv = getGroupAnchor($this$executeWithComposeStackTrace, slots);
        try {
            execute($this$executeWithComposeStackTrace, applier, slots, rememberManager, errorContext);
        } catch (Throwable e$iv) {
            throw OperationKt.attachComposeStackTrace(e$iv, errorContext, slots, location$iv);
        }
    }

    protected GapAnchor getGroupAnchor(OperationArgContainer $this$getGroupAnchor, SlotWriter slots) {
        return null;
    }

    public String intParamName(int parameter) {
        return "IntParameter(" + parameter + ')';
    }

    /* JADX INFO: renamed from: objectParamName-PtL-UHM, reason: not valid java name */
    public String mo4521objectParamNamePtLUHM(int parameter) {
        return "ObjectParameter(" + parameter + ')';
    }

    public String toString() {
        return getName();
    }

    /* JADX INFO: compiled from: Operation.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0087@\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0014\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0002HÖ\u0083\u0004J\n\u0010\f\u001a\u00020\u0004HÖ\u0081\u0004J\n\u0010\r\u001a\u00020\u000eHÖ\u0081\u0004R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b\u0088\u0001\u0003¨\u0006\u000f"}, d2 = {"Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation$ObjectParameter;", "T", "", TypedValues.CycleType.S_WAVE_OFFSET, "", "constructor-impl", "(I)I", "getOffset", "()I", "equals", "", "other", "hashCode", "toString", "", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    @JvmInline
    public static final class ObjectParameter<T> {
        private final int offset;

        /* JADX INFO: renamed from: box-impl, reason: not valid java name */
        public static final /* synthetic */ ObjectParameter m4546boximpl(int i) {
            return new ObjectParameter(i);
        }

        /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
        public static <T> int m4547constructorimpl(int i) {
            return i;
        }

        /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
        public static boolean m4548equalsimpl(int i, Object obj) {
            return (obj instanceof ObjectParameter) && i == ((ObjectParameter) obj).m4552unboximpl();
        }

        /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
        public static final boolean m4549equalsimpl0(int i, int i2) {
            return i == i2;
        }

        /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
        public static int m4550hashCodeimpl(int i) {
            return Integer.hashCode(i);
        }

        /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
        public static String m4551toStringimpl(int i) {
            return "ObjectParameter(offset=" + i + ')';
        }

        public boolean equals(Object other) {
            return m4548equalsimpl(this.offset, other);
        }

        public int hashCode() {
            return m4550hashCodeimpl(this.offset);
        }

        public String toString() {
            return m4551toStringimpl(this.offset);
        }

        /* JADX INFO: renamed from: unbox-impl, reason: not valid java name */
        public final /* synthetic */ int m4552unboximpl() {
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
    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\b\u001a\u00020\t2\n\u0010\n\u001a\u00060\u0005j\u0002`\u000bH\u0016J2\u0010\f\u001a\u00020\r*\u00020\u000e2\n\u0010\u000f\u001a\u0006\u0012\u0002\b\u00030\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0014R\u0012\u0010\u0004\u001a\u00020\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0017"}, d2 = {"Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation$Ups;", "Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation;", "<init>", "()V", "Count", "", "getCount", "()I", "intParamName", "", "parameter", "Landroidx/compose/runtime/composer/gapbuffer/changelist/IntParameter;", "execute", "", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationArgContainer;", "applier", "Landroidx/compose/runtime/Applier;", "slots", "Landroidx/compose/runtime/composer/gapbuffer/SlotWriter;", "rememberManager", "Landroidx/compose/runtime/composer/RememberManager;", "errorContext", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationErrorContext;", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Ups extends Operation {
        public static final int $stable = 0;
        public static final Ups INSTANCE = new Ups();

        private Ups() {
            super(1, 0, 2, null);
        }

        public final int getCount() {
            return 0;
        }

        @Override // androidx.compose.runtime.composer.gapbuffer.changelist.Operation
        public String intParamName(int parameter) {
            return parameter == 0 ? "count" : super.intParamName(parameter);
        }

        @Override // androidx.compose.runtime.composer.gapbuffer.changelist.Operation
        protected void execute(OperationArgContainer $this$execute, Applier<?> applier, SlotWriter slots, RememberManager rememberManager, OperationErrorContext errorContext) {
            int i = $this$execute.getInt(0);
            for (int i2 = 0; i2 < i; i2++) {
                applier.up();
            }
        }
    }

    /* JADX INFO: compiled from: Operation.kt */
    @Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\n\u001a\u00020\u000b2\n\u0010\f\u001a\u0006\u0012\u0002\b\u00030\u0005H\u0016¢\u0006\u0004\b\r\u0010\u000eJ2\u0010\u000f\u001a\u00020\u0010*\u00020\u00112\n\u0010\u0012\u001a\u0006\u0012\u0002\b\u00030\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0014R \u0010\u0004\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00060\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\u001a"}, d2 = {"Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation$Downs;", "Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation;", "<init>", "()V", "Nodes", "Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation$ObjectParameter;", "", "", "getNodes-w8_IdGo", "()I", "objectParamName", "", "parameter", "objectParamName-PtL-UHM", "(I)Ljava/lang/String;", "execute", "", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationArgContainer;", "applier", "Landroidx/compose/runtime/Applier;", "slots", "Landroidx/compose/runtime/composer/gapbuffer/SlotWriter;", "rememberManager", "Landroidx/compose/runtime/composer/RememberManager;", "errorContext", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationErrorContext;", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Downs extends Operation {
        public static final int $stable = 0;
        public static final Downs INSTANCE = new Downs();

        /* JADX WARN: Illegal instructions before constructor call */
        private Downs() {
            int i = 1;
            super(0, i, i, null);
        }

        /* JADX INFO: renamed from: getNodes-w8_IdGo, reason: not valid java name */
        public final int m4534getNodesw8_IdGo() {
            return ObjectParameter.m4547constructorimpl(0);
        }

        @Override // androidx.compose.runtime.composer.gapbuffer.changelist.Operation
        /* JADX INFO: renamed from: objectParamName-PtL-UHM */
        public String mo4521objectParamNamePtLUHM(int parameter) {
            return ObjectParameter.m4549equalsimpl0(parameter, ObjectParameter.m4547constructorimpl(0)) ? "nodes" : super.mo4521objectParamNamePtLUHM(parameter);
        }

        @Override // androidx.compose.runtime.composer.gapbuffer.changelist.Operation
        protected void execute(OperationArgContainer $this$execute, Applier<?> applier, SlotWriter slots, RememberManager rememberManager, OperationErrorContext errorContext) {
            Intrinsics.checkNotNull(applier, "null cannot be cast to non-null type androidx.compose.runtime.Applier<kotlin.Any?>");
            Object[] nodes = (Object[]) $this$execute.mo4567getObjectPtLUHM(ObjectParameter.m4547constructorimpl(0));
            for (Object obj : nodes) {
                applier.down(obj);
            }
        }
    }

    /* JADX INFO: compiled from: Operation.kt */
    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\b\u001a\u00020\t2\n\u0010\n\u001a\u00060\u0005j\u0002`\u000bH\u0016J2\u0010\f\u001a\u00020\r*\u00020\u000e2\n\u0010\u000f\u001a\u0006\u0012\u0002\b\u00030\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0014R\u0012\u0010\u0004\u001a\u00020\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0017"}, d2 = {"Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation$AdvanceSlotsBy;", "Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation;", "<init>", "()V", "Distance", "", "getDistance", "()I", "intParamName", "", "parameter", "Landroidx/compose/runtime/composer/gapbuffer/changelist/IntParameter;", "execute", "", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationArgContainer;", "applier", "Landroidx/compose/runtime/Applier;", "slots", "Landroidx/compose/runtime/composer/gapbuffer/SlotWriter;", "rememberManager", "Landroidx/compose/runtime/composer/RememberManager;", "errorContext", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationErrorContext;", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class AdvanceSlotsBy extends Operation {
        public static final int $stable = 0;
        public static final AdvanceSlotsBy INSTANCE = new AdvanceSlotsBy();

        private AdvanceSlotsBy() {
            super(1, 0, 2, null);
        }

        public final int getDistance() {
            return 0;
        }

        @Override // androidx.compose.runtime.composer.gapbuffer.changelist.Operation
        public String intParamName(int parameter) {
            return parameter == 0 ? "distance" : super.intParamName(parameter);
        }

        @Override // androidx.compose.runtime.composer.gapbuffer.changelist.Operation
        protected void execute(OperationArgContainer $this$execute, Applier<?> applier, SlotWriter slots, RememberManager rememberManager, OperationErrorContext errorContext) {
            slots.advanceBy($this$execute.getInt(0));
        }
    }

    /* JADX INFO: compiled from: Operation.kt */
    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\n\u001a\u00020\u000b2\n\u0010\f\u001a\u0006\u0012\u0002\b\u00030\u0005H\u0016¢\u0006\u0004\b\r\u0010\u000eJ2\u0010\u000f\u001a\u00020\u0007*\u00020\u00102\n\u0010\u0011\u001a\u0006\u0012\u0002\b\u00030\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0014R\u001e\u0010\u0004\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\u0019"}, d2 = {"Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation$SideEffect;", "Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation;", "<init>", "()V", "Effect", "Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation$ObjectParameter;", "Lkotlin/Function0;", "", "getEffect-w8_IdGo", "()I", "objectParamName", "", "parameter", "objectParamName-PtL-UHM", "(I)Ljava/lang/String;", "execute", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationArgContainer;", "applier", "Landroidx/compose/runtime/Applier;", "slots", "Landroidx/compose/runtime/composer/gapbuffer/SlotWriter;", "rememberManager", "Landroidx/compose/runtime/composer/RememberManager;", "errorContext", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationErrorContext;", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class SideEffect extends Operation {
        public static final int $stable = 0;
        public static final SideEffect INSTANCE = new SideEffect();

        /* JADX WARN: Illegal instructions before constructor call */
        private SideEffect() {
            int i = 1;
            super(0, i, i, null);
        }

        /* JADX INFO: renamed from: getEffect-w8_IdGo, reason: not valid java name */
        public final int m4559getEffectw8_IdGo() {
            return ObjectParameter.m4547constructorimpl(0);
        }

        @Override // androidx.compose.runtime.composer.gapbuffer.changelist.Operation
        /* JADX INFO: renamed from: objectParamName-PtL-UHM */
        public String mo4521objectParamNamePtLUHM(int parameter) {
            return ObjectParameter.m4549equalsimpl0(parameter, ObjectParameter.m4547constructorimpl(0)) ? "effect" : super.mo4521objectParamNamePtLUHM(parameter);
        }

        @Override // androidx.compose.runtime.composer.gapbuffer.changelist.Operation
        protected void execute(OperationArgContainer $this$execute, Applier<?> applier, SlotWriter slots, RememberManager rememberManager, OperationErrorContext errorContext) {
            rememberManager.sideEffect((Function0) $this$execute.mo4567getObjectPtLUHM(ObjectParameter.m4547constructorimpl(0)));
        }
    }

    /* JADX INFO: compiled from: Operation.kt */
    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\t\u001a\u00020\n2\n\u0010\u000b\u001a\u0006\u0012\u0002\b\u00030\u0005H\u0016¢\u0006\u0004\b\f\u0010\rJ2\u0010\u000e\u001a\u00020\u000f*\u00020\u00102\n\u0010\u0011\u001a\u0006\u0012\u0002\b\u00030\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0014R\u0018\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\u0019"}, d2 = {"Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation$Remember;", "Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation;", "<init>", "()V", "Value", "Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation$ObjectParameter;", "Landroidx/compose/runtime/RememberObserverHolder;", "getValue-w8_IdGo", "()I", "objectParamName", "", "parameter", "objectParamName-PtL-UHM", "(I)Ljava/lang/String;", "execute", "", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationArgContainer;", "applier", "Landroidx/compose/runtime/Applier;", "slots", "Landroidx/compose/runtime/composer/gapbuffer/SlotWriter;", "rememberManager", "Landroidx/compose/runtime/composer/RememberManager;", "errorContext", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationErrorContext;", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Remember extends Operation {
        public static final int $stable = 0;
        public static final Remember INSTANCE = new Remember();

        /* JADX WARN: Illegal instructions before constructor call */
        private Remember() {
            int i = 1;
            super(0, i, i, null);
        }

        /* JADX INFO: renamed from: getValue-w8_IdGo, reason: not valid java name */
        public final int m4557getValuew8_IdGo() {
            return ObjectParameter.m4547constructorimpl(0);
        }

        @Override // androidx.compose.runtime.composer.gapbuffer.changelist.Operation
        /* JADX INFO: renamed from: objectParamName-PtL-UHM */
        public String mo4521objectParamNamePtLUHM(int parameter) {
            return ObjectParameter.m4549equalsimpl0(parameter, ObjectParameter.m4547constructorimpl(0)) ? "value" : super.mo4521objectParamNamePtLUHM(parameter);
        }

        @Override // androidx.compose.runtime.composer.gapbuffer.changelist.Operation
        protected void execute(OperationArgContainer $this$execute, Applier<?> applier, SlotWriter slots, RememberManager rememberManager, OperationErrorContext errorContext) {
            rememberManager.remembering((RememberObserverHolder) $this$execute.mo4567getObjectPtLUHM(ObjectParameter.m4547constructorimpl(0)));
        }
    }

    /* JADX INFO: compiled from: Operation.kt */
    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\t\u001a\u00020\n2\n\u0010\u000b\u001a\u0006\u0012\u0002\b\u00030\u0005H\u0016¢\u0006\u0004\b\f\u0010\rJ2\u0010\u000e\u001a\u00020\u000f*\u00020\u00102\n\u0010\u0011\u001a\u0006\u0012\u0002\b\u00030\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0014R\u0018\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\u0019"}, d2 = {"Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation$RememberPausingScope;", "Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation;", "<init>", "()V", "Scope", "Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation$ObjectParameter;", "Landroidx/compose/runtime/RecomposeScopeImpl;", "getScope-w8_IdGo", "()I", "objectParamName", "", "parameter", "objectParamName-PtL-UHM", "(I)Ljava/lang/String;", "execute", "", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationArgContainer;", "applier", "Landroidx/compose/runtime/Applier;", "slots", "Landroidx/compose/runtime/composer/gapbuffer/SlotWriter;", "rememberManager", "Landroidx/compose/runtime/composer/RememberManager;", "errorContext", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationErrorContext;", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class RememberPausingScope extends Operation {
        public static final int $stable = 0;
        public static final RememberPausingScope INSTANCE = new RememberPausingScope();

        /* JADX WARN: Illegal instructions before constructor call */
        private RememberPausingScope() {
            int i = 1;
            super(0, i, i, null);
        }

        /* JADX INFO: renamed from: getScope-w8_IdGo, reason: not valid java name */
        public final int m4558getScopew8_IdGo() {
            return ObjectParameter.m4547constructorimpl(0);
        }

        @Override // androidx.compose.runtime.composer.gapbuffer.changelist.Operation
        /* JADX INFO: renamed from: objectParamName-PtL-UHM */
        public String mo4521objectParamNamePtLUHM(int parameter) {
            return ObjectParameter.m4549equalsimpl0(parameter, ObjectParameter.m4547constructorimpl(0)) ? "scope" : super.mo4521objectParamNamePtLUHM(parameter);
        }

        @Override // androidx.compose.runtime.composer.gapbuffer.changelist.Operation
        protected void execute(OperationArgContainer $this$execute, Applier<?> applier, SlotWriter slots, RememberManager rememberManager, OperationErrorContext errorContext) {
            RecomposeScopeImpl scope = (RecomposeScopeImpl) $this$execute.mo4567getObjectPtLUHM(ObjectParameter.m4547constructorimpl(0));
            rememberManager.rememberPausingScope(scope);
        }
    }

    /* JADX INFO: compiled from: Operation.kt */
    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\t\u001a\u00020\n2\n\u0010\u000b\u001a\u0006\u0012\u0002\b\u00030\u0005H\u0016¢\u0006\u0004\b\f\u0010\rJ2\u0010\u000e\u001a\u00020\u000f*\u00020\u00102\n\u0010\u0011\u001a\u0006\u0012\u0002\b\u00030\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0014R\u0018\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\u0019"}, d2 = {"Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation$StartResumingScope;", "Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation;", "<init>", "()V", "Scope", "Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation$ObjectParameter;", "Landroidx/compose/runtime/RecomposeScopeImpl;", "getScope-w8_IdGo", "()I", "objectParamName", "", "parameter", "objectParamName-PtL-UHM", "(I)Ljava/lang/String;", "execute", "", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationArgContainer;", "applier", "Landroidx/compose/runtime/Applier;", "slots", "Landroidx/compose/runtime/composer/gapbuffer/SlotWriter;", "rememberManager", "Landroidx/compose/runtime/composer/RememberManager;", "errorContext", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationErrorContext;", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class StartResumingScope extends Operation {
        public static final int $stable = 0;
        public static final StartResumingScope INSTANCE = new StartResumingScope();

        /* JADX WARN: Illegal instructions before constructor call */
        private StartResumingScope() {
            int i = 1;
            super(0, i, i, null);
        }

        /* JADX INFO: renamed from: getScope-w8_IdGo, reason: not valid java name */
        public final int m4560getScopew8_IdGo() {
            return ObjectParameter.m4547constructorimpl(0);
        }

        @Override // androidx.compose.runtime.composer.gapbuffer.changelist.Operation
        /* JADX INFO: renamed from: objectParamName-PtL-UHM */
        public String mo4521objectParamNamePtLUHM(int parameter) {
            return ObjectParameter.m4549equalsimpl0(parameter, ObjectParameter.m4547constructorimpl(0)) ? "scope" : super.mo4521objectParamNamePtLUHM(parameter);
        }

        @Override // androidx.compose.runtime.composer.gapbuffer.changelist.Operation
        protected void execute(OperationArgContainer $this$execute, Applier<?> applier, SlotWriter slots, RememberManager rememberManager, OperationErrorContext errorContext) {
            RecomposeScopeImpl scope = (RecomposeScopeImpl) $this$execute.mo4567getObjectPtLUHM(ObjectParameter.m4547constructorimpl(0));
            rememberManager.startResumingScope(scope);
        }
    }

    /* JADX INFO: compiled from: Operation.kt */
    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\t\u001a\u00020\n2\n\u0010\u000b\u001a\u0006\u0012\u0002\b\u00030\u0005H\u0016¢\u0006\u0004\b\f\u0010\rJ2\u0010\u000e\u001a\u00020\u000f*\u00020\u00102\n\u0010\u0011\u001a\u0006\u0012\u0002\b\u00030\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0014R\u0018\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\u0019"}, d2 = {"Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation$EndResumingScope;", "Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation;", "<init>", "()V", "Scope", "Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation$ObjectParameter;", "Landroidx/compose/runtime/RecomposeScopeImpl;", "getScope-w8_IdGo", "()I", "objectParamName", "", "parameter", "objectParamName-PtL-UHM", "(I)Ljava/lang/String;", "execute", "", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationArgContainer;", "applier", "Landroidx/compose/runtime/Applier;", "slots", "Landroidx/compose/runtime/composer/gapbuffer/SlotWriter;", "rememberManager", "Landroidx/compose/runtime/composer/RememberManager;", "errorContext", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationErrorContext;", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class EndResumingScope extends Operation {
        public static final int $stable = 0;
        public static final EndResumingScope INSTANCE = new EndResumingScope();

        /* JADX WARN: Illegal instructions before constructor call */
        private EndResumingScope() {
            int i = 1;
            super(0, i, i, null);
        }

        /* JADX INFO: renamed from: getScope-w8_IdGo, reason: not valid java name */
        public final int m4537getScopew8_IdGo() {
            return ObjectParameter.m4547constructorimpl(0);
        }

        @Override // androidx.compose.runtime.composer.gapbuffer.changelist.Operation
        /* JADX INFO: renamed from: objectParamName-PtL-UHM */
        public String mo4521objectParamNamePtLUHM(int parameter) {
            return ObjectParameter.m4549equalsimpl0(parameter, ObjectParameter.m4547constructorimpl(0)) ? "scope" : super.mo4521objectParamNamePtLUHM(parameter);
        }

        @Override // androidx.compose.runtime.composer.gapbuffer.changelist.Operation
        protected void execute(OperationArgContainer $this$execute, Applier<?> applier, SlotWriter slots, RememberManager rememberManager, OperationErrorContext errorContext) {
            RecomposeScopeImpl scope = (RecomposeScopeImpl) $this$execute.mo4567getObjectPtLUHM(ObjectParameter.m4547constructorimpl(0));
            rememberManager.endResumingScope(scope);
        }
    }

    /* JADX INFO: compiled from: Operation.kt */
    @Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\f\u001a\u00020\r2\n\u0010\u000e\u001a\u0006\u0012\u0002\b\u00030\u0005H\u0016¢\u0006\u0004\b\u000f\u0010\u0010J2\u0010\u0011\u001a\u00020\u0012*\u00020\u00132\n\u0010\u0014\u001a\u0006\u0012\u0002\b\u00030\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0014R\u0018\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\u000b\u0010\b¨\u0006\u001c"}, d2 = {"Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation$AppendValue;", "Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation;", "<init>", "()V", "Anchor", "Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation$ObjectParameter;", "Landroidx/compose/runtime/composer/gapbuffer/GapAnchor;", "getAnchor-w8_IdGo", "()I", "Value", "", "getValue-w8_IdGo", "objectParamName", "", "parameter", "objectParamName-PtL-UHM", "(I)Ljava/lang/String;", "execute", "", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationArgContainer;", "applier", "Landroidx/compose/runtime/Applier;", "slots", "Landroidx/compose/runtime/composer/gapbuffer/SlotWriter;", "rememberManager", "Landroidx/compose/runtime/composer/RememberManager;", "errorContext", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationErrorContext;", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class AppendValue extends Operation {
        public static final int $stable = 0;
        public static final AppendValue INSTANCE = new AppendValue();

        private AppendValue() {
            super(0, 2, 1, null);
        }

        /* JADX INFO: renamed from: getAnchor-w8_IdGo, reason: not valid java name */
        public final int m4522getAnchorw8_IdGo() {
            return ObjectParameter.m4547constructorimpl(0);
        }

        /* JADX INFO: renamed from: getValue-w8_IdGo, reason: not valid java name */
        public final int m4523getValuew8_IdGo() {
            return ObjectParameter.m4547constructorimpl(1);
        }

        @Override // androidx.compose.runtime.composer.gapbuffer.changelist.Operation
        /* JADX INFO: renamed from: objectParamName-PtL-UHM */
        public String mo4521objectParamNamePtLUHM(int parameter) {
            return ObjectParameter.m4549equalsimpl0(parameter, ObjectParameter.m4547constructorimpl(0)) ? "anchor" : ObjectParameter.m4549equalsimpl0(parameter, ObjectParameter.m4547constructorimpl(1)) ? "value" : super.mo4521objectParamNamePtLUHM(parameter);
        }

        @Override // androidx.compose.runtime.composer.gapbuffer.changelist.Operation
        protected void execute(OperationArgContainer $this$execute, Applier<?> applier, SlotWriter slots, RememberManager rememberManager, OperationErrorContext errorContext) {
            GapAnchor anchor = (GapAnchor) $this$execute.mo4567getObjectPtLUHM(ObjectParameter.m4547constructorimpl(0));
            Object value = $this$execute.mo4567getObjectPtLUHM(ObjectParameter.m4547constructorimpl(1));
            if (value instanceof RememberObserverHolder) {
                rememberManager.remembering((RememberObserverHolder) value);
            }
            slots.appendSlot(anchor, value);
        }
    }

    /* JADX INFO: compiled from: Operation.kt */
    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\b\u001a\u00020\t2\n\u0010\n\u001a\u00060\u0005j\u0002`\u000bH\u0016J2\u0010\f\u001a\u00020\r*\u00020\u000e2\n\u0010\u000f\u001a\u0006\u0012\u0002\b\u00030\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0014R\u0012\u0010\u0004\u001a\u00020\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0017"}, d2 = {"Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation$TrimParentValues;", "Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation;", "<init>", "()V", "Count", "", "getCount", "()I", "intParamName", "", "parameter", "Landroidx/compose/runtime/composer/gapbuffer/changelist/IntParameter;", "execute", "", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationArgContainer;", "applier", "Landroidx/compose/runtime/Applier;", "slots", "Landroidx/compose/runtime/composer/gapbuffer/SlotWriter;", "rememberManager", "Landroidx/compose/runtime/composer/RememberManager;", "errorContext", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationErrorContext;", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class TrimParentValues extends Operation {
        public static final int $stable = 0;
        public static final TrimParentValues INSTANCE = new TrimParentValues();

        private TrimParentValues() {
            super(1, 0, 2, null);
        }

        public final int getCount() {
            return 0;
        }

        @Override // androidx.compose.runtime.composer.gapbuffer.changelist.Operation
        public String intParamName(int parameter) {
            return parameter == 0 ? "count" : super.intParamName(parameter);
        }

        @Override // androidx.compose.runtime.composer.gapbuffer.changelist.Operation
        protected void execute(OperationArgContainer $this$execute, Applier<?> applier, SlotWriter slots, RememberManager rememberManager, OperationErrorContext errorContext) {
            int count = $this$execute.getInt(0);
            int groupIndex$iv = slots.getParent();
            int slotsStart$iv = slots.slotsStartIndex$runtime(groupIndex$iv);
            int slotsEnd$iv = slots.slotsEndIndex$runtime(groupIndex$iv);
            for (int slotIndex$iv = Math.max(slotsStart$iv, slotsEnd$iv - count); slotIndex$iv < slotsEnd$iv; slotIndex$iv++) {
                Object value = slots.slots[slots.dataIndexToDataAddress(slotIndex$iv)];
                if (value instanceof RememberObserverHolder) {
                    rememberManager.forgetting((RememberObserverHolder) value);
                } else if (value instanceof RecomposeScopeImpl) {
                    ((RecomposeScopeImpl) value).release();
                }
            }
            slots.trimTailSlots(count);
        }
    }

    /* JADX INFO: compiled from: Operation.kt */
    @Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\f\u001a\u00020\r2\n\u0010\u000e\u001a\u00060\nj\u0002`\u000fH\u0016J\u001b\u0010\u0010\u001a\u00020\r2\n\u0010\u000e\u001a\u0006\u0012\u0002\b\u00030\u0005H\u0016¢\u0006\u0004\b\u0011\u0010\u0012J2\u0010\u0013\u001a\u00020\u0014*\u00020\u00152\n\u0010\u0016\u001a\u0006\u0012\u0002\b\u00030\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0014R\u001a\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0012\u0010\t\u001a\u00020\n8Æ\u0002¢\u0006\u0006\u001a\u0004\b\u000b\u0010\b¨\u0006\u001e"}, d2 = {"Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation$UpdateValue;", "Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation;", "<init>", "()V", "Value", "Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation$ObjectParameter;", "", "getValue-w8_IdGo", "()I", "GroupSlotIndex", "", "getGroupSlotIndex", "intParamName", "", "parameter", "Landroidx/compose/runtime/composer/gapbuffer/changelist/IntParameter;", "objectParamName", "objectParamName-PtL-UHM", "(I)Ljava/lang/String;", "execute", "", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationArgContainer;", "applier", "Landroidx/compose/runtime/Applier;", "slots", "Landroidx/compose/runtime/composer/gapbuffer/SlotWriter;", "rememberManager", "Landroidx/compose/runtime/composer/RememberManager;", "errorContext", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationErrorContext;", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class UpdateValue extends Operation {
        public static final int $stable = 0;
        public static final UpdateValue INSTANCE = new UpdateValue();

        /* JADX WARN: Illegal instructions before constructor call */
        private UpdateValue() {
            int i = 1;
            super(i, i, null);
        }

        /* JADX INFO: renamed from: getValue-w8_IdGo, reason: not valid java name */
        public final int m4566getValuew8_IdGo() {
            return ObjectParameter.m4547constructorimpl(0);
        }

        public final int getGroupSlotIndex() {
            return 0;
        }

        @Override // androidx.compose.runtime.composer.gapbuffer.changelist.Operation
        public String intParamName(int parameter) {
            return parameter == 0 ? "groupSlotIndex" : super.intParamName(parameter);
        }

        @Override // androidx.compose.runtime.composer.gapbuffer.changelist.Operation
        /* JADX INFO: renamed from: objectParamName-PtL-UHM */
        public String mo4521objectParamNamePtLUHM(int parameter) {
            return ObjectParameter.m4549equalsimpl0(parameter, ObjectParameter.m4547constructorimpl(0)) ? "value" : super.mo4521objectParamNamePtLUHM(parameter);
        }

        @Override // androidx.compose.runtime.composer.gapbuffer.changelist.Operation
        protected void execute(OperationArgContainer $this$execute, Applier<?> applier, SlotWriter slots, RememberManager rememberManager, OperationErrorContext errorContext) {
            Object value = $this$execute.mo4567getObjectPtLUHM(ObjectParameter.m4547constructorimpl(0));
            int groupSlotIndex = $this$execute.getInt(0);
            if (value instanceof RememberObserverHolder) {
                rememberManager.remembering((RememberObserverHolder) value);
            }
            Object value$iv = slots.set(slots.getCurrentGroup(), groupSlotIndex, value);
            if (value$iv instanceof RememberObserverHolder) {
                rememberManager.forgetting((RememberObserverHolder) value$iv);
            } else if (value$iv instanceof RecomposeScopeImpl) {
                ((RecomposeScopeImpl) value$iv).release();
            }
        }
    }

    /* JADX INFO: compiled from: Operation.kt */
    @Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u000f\u001a\u00020\u00102\n\u0010\u0011\u001a\u00060\rj\u0002`\u0012H\u0016J\u001b\u0010\u0013\u001a\u00020\u00102\n\u0010\u0011\u001a\u0006\u0012\u0002\b\u00030\u0005H\u0016¢\u0006\u0004\b\u0014\u0010\u0015J2\u0010\u0016\u001a\u00020\u0017*\u00020\u00182\n\u0010\u0019\u001a\u0006\u0012\u0002\b\u00030\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0014R\u001a\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0018\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\u000b\u0010\bR\u0012\u0010\f\u001a\u00020\r8Æ\u0002¢\u0006\u0006\u001a\u0004\b\u000e\u0010\b¨\u0006!"}, d2 = {"Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation$UpdateAnchoredValue;", "Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation;", "<init>", "()V", "Value", "Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation$ObjectParameter;", "", "getValue-w8_IdGo", "()I", "Anchor", "Landroidx/compose/runtime/composer/gapbuffer/GapAnchor;", "getAnchor-w8_IdGo", "GroupSlotIndex", "", "getGroupSlotIndex", "intParamName", "", "parameter", "Landroidx/compose/runtime/composer/gapbuffer/changelist/IntParameter;", "objectParamName", "objectParamName-PtL-UHM", "(I)Ljava/lang/String;", "execute", "", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationArgContainer;", "applier", "Landroidx/compose/runtime/Applier;", "slots", "Landroidx/compose/runtime/composer/gapbuffer/SlotWriter;", "rememberManager", "Landroidx/compose/runtime/composer/RememberManager;", "errorContext", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationErrorContext;", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class UpdateAnchoredValue extends Operation {
        public static final int $stable = 0;
        public static final UpdateAnchoredValue INSTANCE = new UpdateAnchoredValue();

        private UpdateAnchoredValue() {
            super(1, 2, null);
        }

        /* JADX INFO: renamed from: getValue-w8_IdGo, reason: not valid java name */
        public final int m4562getValuew8_IdGo() {
            return ObjectParameter.m4547constructorimpl(0);
        }

        /* JADX INFO: renamed from: getAnchor-w8_IdGo, reason: not valid java name */
        public final int m4561getAnchorw8_IdGo() {
            return ObjectParameter.m4547constructorimpl(1);
        }

        public final int getGroupSlotIndex() {
            return 0;
        }

        @Override // androidx.compose.runtime.composer.gapbuffer.changelist.Operation
        public String intParamName(int parameter) {
            return parameter == 0 ? "groupSlotIndex" : super.intParamName(parameter);
        }

        @Override // androidx.compose.runtime.composer.gapbuffer.changelist.Operation
        /* JADX INFO: renamed from: objectParamName-PtL-UHM */
        public String mo4521objectParamNamePtLUHM(int parameter) {
            return ObjectParameter.m4549equalsimpl0(parameter, ObjectParameter.m4547constructorimpl(0)) ? "value" : ObjectParameter.m4549equalsimpl0(parameter, ObjectParameter.m4547constructorimpl(1)) ? "anchor" : super.mo4521objectParamNamePtLUHM(parameter);
        }

        @Override // androidx.compose.runtime.composer.gapbuffer.changelist.Operation
        protected void execute(OperationArgContainer $this$execute, Applier<?> applier, SlotWriter slots, RememberManager rememberManager, OperationErrorContext errorContext) {
            Object value = $this$execute.mo4567getObjectPtLUHM(ObjectParameter.m4547constructorimpl(0));
            GapAnchor anchor = (GapAnchor) $this$execute.mo4567getObjectPtLUHM(ObjectParameter.m4547constructorimpl(1));
            int groupSlotIndex = $this$execute.getInt(0);
            if (value instanceof RememberObserverHolder) {
                rememberManager.remembering((RememberObserverHolder) value);
            }
            int groupIndex = slots.anchorIndex(anchor);
            Object previous = slots.set(groupIndex, groupSlotIndex, value);
            if (previous instanceof RememberObserverHolder) {
                rememberManager.forgetting((RememberObserverHolder) previous);
            } else if (previous instanceof RecomposeScopeImpl) {
                ((RecomposeScopeImpl) previous).release();
            }
        }
    }

    /* JADX INFO: compiled from: Operation.kt */
    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\t\u001a\u00020\n2\n\u0010\u000b\u001a\u0006\u0012\u0002\b\u00030\u0005H\u0016¢\u0006\u0004\b\f\u0010\rJ2\u0010\u000e\u001a\u00020\u000f*\u00020\u00102\n\u0010\u0011\u001a\u0006\u0012\u0002\b\u00030\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0014R\u001a\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\u0019"}, d2 = {"Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation$UpdateAuxData;", "Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation;", "<init>", "()V", "Data", "Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation$ObjectParameter;", "", "getData-w8_IdGo", "()I", "objectParamName", "", "parameter", "objectParamName-PtL-UHM", "(I)Ljava/lang/String;", "execute", "", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationArgContainer;", "applier", "Landroidx/compose/runtime/Applier;", "slots", "Landroidx/compose/runtime/composer/gapbuffer/SlotWriter;", "rememberManager", "Landroidx/compose/runtime/composer/RememberManager;", "errorContext", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationErrorContext;", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class UpdateAuxData extends Operation {
        public static final int $stable = 0;
        public static final UpdateAuxData INSTANCE = new UpdateAuxData();

        /* JADX WARN: Illegal instructions before constructor call */
        private UpdateAuxData() {
            int i = 1;
            super(0, i, i, null);
        }

        /* JADX INFO: renamed from: getData-w8_IdGo, reason: not valid java name */
        public final int m4563getDataw8_IdGo() {
            return ObjectParameter.m4547constructorimpl(0);
        }

        @Override // androidx.compose.runtime.composer.gapbuffer.changelist.Operation
        /* JADX INFO: renamed from: objectParamName-PtL-UHM */
        public String mo4521objectParamNamePtLUHM(int parameter) {
            return ObjectParameter.m4549equalsimpl0(parameter, ObjectParameter.m4547constructorimpl(0)) ? "data" : super.mo4521objectParamNamePtLUHM(parameter);
        }

        @Override // androidx.compose.runtime.composer.gapbuffer.changelist.Operation
        protected void execute(OperationArgContainer $this$execute, Applier<?> applier, SlotWriter slots, RememberManager rememberManager, OperationErrorContext errorContext) {
            slots.updateAux($this$execute.mo4567getObjectPtLUHM(ObjectParameter.m4547constructorimpl(0)));
        }
    }

    /* JADX INFO: compiled from: Operation.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J2\u0010\u0004\u001a\u00020\u0005*\u00020\u00062\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0014¨\u0006\u000f"}, d2 = {"Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation$EnsureRootGroupStarted;", "Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation;", "<init>", "()V", "execute", "", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationArgContainer;", "applier", "Landroidx/compose/runtime/Applier;", "slots", "Landroidx/compose/runtime/composer/gapbuffer/SlotWriter;", "rememberManager", "Landroidx/compose/runtime/composer/RememberManager;", "errorContext", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationErrorContext;", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class EnsureRootGroupStarted extends Operation {
        public static final int $stable = 0;
        public static final EnsureRootGroupStarted INSTANCE = new EnsureRootGroupStarted();

        /* JADX WARN: Illegal instructions before constructor call */
        private EnsureRootGroupStarted() {
            int i = 0;
            super(i, i, 3, null);
        }

        @Override // androidx.compose.runtime.composer.gapbuffer.changelist.Operation
        protected void execute(OperationArgContainer $this$execute, Applier<?> applier, SlotWriter slots, RememberManager rememberManager, OperationErrorContext errorContext) {
            slots.ensureStarted(0);
        }
    }

    /* JADX INFO: compiled from: Operation.kt */
    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\t\u001a\u00020\n2\n\u0010\u000b\u001a\u0006\u0012\u0002\b\u00030\u0005H\u0016¢\u0006\u0004\b\f\u0010\rJ2\u0010\u000e\u001a\u00020\u000f*\u00020\u00102\n\u0010\u0011\u001a\u0006\u0012\u0002\b\u00030\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0014R\u0018\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\u0019"}, d2 = {"Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation$EnsureGroupStarted;", "Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation;", "<init>", "()V", "Anchor", "Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation$ObjectParameter;", "Landroidx/compose/runtime/composer/gapbuffer/GapAnchor;", "getAnchor-w8_IdGo", "()I", "objectParamName", "", "parameter", "objectParamName-PtL-UHM", "(I)Ljava/lang/String;", "execute", "", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationArgContainer;", "applier", "Landroidx/compose/runtime/Applier;", "slots", "Landroidx/compose/runtime/composer/gapbuffer/SlotWriter;", "rememberManager", "Landroidx/compose/runtime/composer/RememberManager;", "errorContext", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationErrorContext;", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class EnsureGroupStarted extends Operation {
        public static final int $stable = 0;
        public static final EnsureGroupStarted INSTANCE = new EnsureGroupStarted();

        /* JADX WARN: Illegal instructions before constructor call */
        private EnsureGroupStarted() {
            int i = 1;
            super(0, i, i, null);
        }

        /* JADX INFO: renamed from: getAnchor-w8_IdGo, reason: not valid java name */
        public final int m4538getAnchorw8_IdGo() {
            return ObjectParameter.m4547constructorimpl(0);
        }

        @Override // androidx.compose.runtime.composer.gapbuffer.changelist.Operation
        /* JADX INFO: renamed from: objectParamName-PtL-UHM */
        public String mo4521objectParamNamePtLUHM(int parameter) {
            return ObjectParameter.m4549equalsimpl0(parameter, ObjectParameter.m4547constructorimpl(0)) ? "anchor" : super.mo4521objectParamNamePtLUHM(parameter);
        }

        @Override // androidx.compose.runtime.composer.gapbuffer.changelist.Operation
        protected void execute(OperationArgContainer $this$execute, Applier<?> applier, SlotWriter slots, RememberManager rememberManager, OperationErrorContext errorContext) {
            slots.ensureStarted((GapAnchor) $this$execute.mo4567getObjectPtLUHM(ObjectParameter.m4547constructorimpl(0)));
        }
    }

    /* JADX INFO: compiled from: Operation.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J2\u0010\u0004\u001a\u00020\u0005*\u00020\u00062\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0014¨\u0006\u000f"}, d2 = {"Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation$RemoveCurrentGroup;", "Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation;", "<init>", "()V", "execute", "", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationArgContainer;", "applier", "Landroidx/compose/runtime/Applier;", "slots", "Landroidx/compose/runtime/composer/gapbuffer/SlotWriter;", "rememberManager", "Landroidx/compose/runtime/composer/RememberManager;", "errorContext", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationErrorContext;", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class RemoveCurrentGroup extends Operation {
        public static final int $stable = 0;
        public static final RemoveCurrentGroup INSTANCE = new RemoveCurrentGroup();

        /* JADX WARN: Illegal instructions before constructor call */
        private RemoveCurrentGroup() {
            int i = 0;
            super(i, i, 3, null);
        }

        @Override // androidx.compose.runtime.composer.gapbuffer.changelist.Operation
        protected void execute(OperationArgContainer $this$execute, Applier<?> applier, SlotWriter slots, RememberManager rememberManager, OperationErrorContext errorContext) {
            ComposerKt.removeCurrentGroup(slots, rememberManager);
        }
    }

    /* JADX INFO: compiled from: Operation.kt */
    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\b\u001a\u00020\t2\n\u0010\n\u001a\u00060\u0005j\u0002`\u000bH\u0016J2\u0010\f\u001a\u00020\r*\u00020\u000e2\n\u0010\u000f\u001a\u0006\u0012\u0002\b\u00030\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0014R\u0012\u0010\u0004\u001a\u00020\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0017"}, d2 = {"Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation$MoveCurrentGroup;", "Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation;", "<init>", "()V", "Offset", "", "getOffset", "()I", "intParamName", "", "parameter", "Landroidx/compose/runtime/composer/gapbuffer/changelist/IntParameter;", "execute", "", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationArgContainer;", "applier", "Landroidx/compose/runtime/Applier;", "slots", "Landroidx/compose/runtime/composer/gapbuffer/SlotWriter;", "rememberManager", "Landroidx/compose/runtime/composer/RememberManager;", "errorContext", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationErrorContext;", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class MoveCurrentGroup extends Operation {
        public static final int $stable = 0;
        public static final MoveCurrentGroup INSTANCE = new MoveCurrentGroup();

        private MoveCurrentGroup() {
            super(1, 0, 2, null);
        }

        public final int getOffset() {
            return 0;
        }

        @Override // androidx.compose.runtime.composer.gapbuffer.changelist.Operation
        public String intParamName(int parameter) {
            return parameter == 0 ? TypedValues.CycleType.S_WAVE_OFFSET : super.intParamName(parameter);
        }

        @Override // androidx.compose.runtime.composer.gapbuffer.changelist.Operation
        protected void execute(OperationArgContainer $this$execute, Applier<?> applier, SlotWriter slots, RememberManager rememberManager, OperationErrorContext errorContext) {
            slots.moveGroup($this$execute.getInt(0));
        }
    }

    /* JADX INFO: compiled from: Operation.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J2\u0010\u0004\u001a\u00020\u0005*\u00020\u00062\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0014¨\u0006\u000f"}, d2 = {"Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation$EndCurrentGroup;", "Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation;", "<init>", "()V", "execute", "", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationArgContainer;", "applier", "Landroidx/compose/runtime/Applier;", "slots", "Landroidx/compose/runtime/composer/gapbuffer/SlotWriter;", "rememberManager", "Landroidx/compose/runtime/composer/RememberManager;", "errorContext", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationErrorContext;", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class EndCurrentGroup extends Operation {
        public static final int $stable = 0;
        public static final EndCurrentGroup INSTANCE = new EndCurrentGroup();

        /* JADX WARN: Illegal instructions before constructor call */
        private EndCurrentGroup() {
            int i = 0;
            super(i, i, 3, null);
        }

        @Override // androidx.compose.runtime.composer.gapbuffer.changelist.Operation
        protected void execute(OperationArgContainer $this$execute, Applier<?> applier, SlotWriter slots, RememberManager rememberManager, OperationErrorContext errorContext) {
            slots.endGroup();
        }
    }

    /* JADX INFO: compiled from: Operation.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J2\u0010\u0004\u001a\u00020\u0005*\u00020\u00062\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0014¨\u0006\u000f"}, d2 = {"Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation$SkipToEndOfCurrentGroup;", "Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation;", "<init>", "()V", "execute", "", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationArgContainer;", "applier", "Landroidx/compose/runtime/Applier;", "slots", "Landroidx/compose/runtime/composer/gapbuffer/SlotWriter;", "rememberManager", "Landroidx/compose/runtime/composer/RememberManager;", "errorContext", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationErrorContext;", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class SkipToEndOfCurrentGroup extends Operation {
        public static final int $stable = 0;
        public static final SkipToEndOfCurrentGroup INSTANCE = new SkipToEndOfCurrentGroup();

        /* JADX WARN: Illegal instructions before constructor call */
        private SkipToEndOfCurrentGroup() {
            int i = 0;
            super(i, i, 3, null);
        }

        @Override // androidx.compose.runtime.composer.gapbuffer.changelist.Operation
        protected void execute(OperationArgContainer $this$execute, Applier<?> applier, SlotWriter slots, RememberManager rememberManager, OperationErrorContext errorContext) {
            slots.skipToGroupEnd();
        }
    }

    /* JADX INFO: compiled from: Operation.kt */
    @Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\r\u001a\u00020\u000e2\n\u0010\u000f\u001a\u0006\u0012\u0002\b\u00030\u0005H\u0016¢\u0006\u0004\b\u0010\u0010\u0011J2\u0010\u0012\u001a\u00020\b*\u00020\u00132\n\u0010\u0014\u001a\u0006\u0012\u0002\b\u00030\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0014R$\u0010\u0004\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u00060\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0018\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\f\u0010\n¨\u0006\u001c"}, d2 = {"Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation$EndCompositionScope;", "Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation;", "<init>", "()V", "Action", "Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation$ObjectParameter;", "Lkotlin/Function1;", "Landroidx/compose/runtime/Composition;", "", "getAction-w8_IdGo", "()I", "Composition", "getComposition-w8_IdGo", "objectParamName", "", "parameter", "objectParamName-PtL-UHM", "(I)Ljava/lang/String;", "execute", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationArgContainer;", "applier", "Landroidx/compose/runtime/Applier;", "slots", "Landroidx/compose/runtime/composer/gapbuffer/SlotWriter;", "rememberManager", "Landroidx/compose/runtime/composer/RememberManager;", "errorContext", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationErrorContext;", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class EndCompositionScope extends Operation {
        public static final int $stable = 0;
        public static final EndCompositionScope INSTANCE = new EndCompositionScope();

        private EndCompositionScope() {
            super(0, 2, 1, null);
        }

        /* JADX INFO: renamed from: getAction-w8_IdGo, reason: not valid java name */
        public final int m4535getActionw8_IdGo() {
            return ObjectParameter.m4547constructorimpl(0);
        }

        /* JADX INFO: renamed from: getComposition-w8_IdGo, reason: not valid java name */
        public final int m4536getCompositionw8_IdGo() {
            return ObjectParameter.m4547constructorimpl(1);
        }

        @Override // androidx.compose.runtime.composer.gapbuffer.changelist.Operation
        /* JADX INFO: renamed from: objectParamName-PtL-UHM */
        public String mo4521objectParamNamePtLUHM(int parameter) {
            return ObjectParameter.m4549equalsimpl0(parameter, ObjectParameter.m4547constructorimpl(0)) ? "anchor" : ObjectParameter.m4549equalsimpl0(parameter, ObjectParameter.m4547constructorimpl(1)) ? "composition" : super.mo4521objectParamNamePtLUHM(parameter);
        }

        @Override // androidx.compose.runtime.composer.gapbuffer.changelist.Operation
        protected void execute(OperationArgContainer $this$execute, Applier<?> applier, SlotWriter slots, RememberManager rememberManager, OperationErrorContext errorContext) {
            Function1 action = (Function1) $this$execute.mo4567getObjectPtLUHM(ObjectParameter.m4547constructorimpl(0));
            Composition composition = (Composition) $this$execute.mo4567getObjectPtLUHM(ObjectParameter.m4547constructorimpl(1));
            action.invoke(composition);
        }
    }

    /* JADX INFO: compiled from: Operation.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J2\u0010\u0004\u001a\u00020\u0005*\u00020\u00062\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0014¨\u0006\u000f"}, d2 = {"Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation$UseCurrentNode;", "Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation;", "<init>", "()V", "execute", "", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationArgContainer;", "applier", "Landroidx/compose/runtime/Applier;", "slots", "Landroidx/compose/runtime/composer/gapbuffer/SlotWriter;", "rememberManager", "Landroidx/compose/runtime/composer/RememberManager;", "errorContext", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationErrorContext;", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class UseCurrentNode extends Operation {
        public static final int $stable = 0;
        public static final UseCurrentNode INSTANCE = new UseCurrentNode();

        /* JADX WARN: Illegal instructions before constructor call */
        private UseCurrentNode() {
            int i = 0;
            super(i, i, 3, null);
        }

        @Override // androidx.compose.runtime.composer.gapbuffer.changelist.Operation
        protected void execute(OperationArgContainer $this$execute, Applier<?> applier, SlotWriter slots, RememberManager rememberManager, OperationErrorContext errorContext) {
            applier.reuse();
        }
    }

    /* JADX INFO: compiled from: Operation.kt */
    @Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\u000e\u001a\u00020\u000f2\n\u0010\u0010\u001a\u0006\u0012\u0002\b\u00030\u0005H\u0016¢\u0006\u0004\b\u0011\u0010\u0012J2\u0010\u0013\u001a\u00020\u000b*\u00020\u00142\n\u0010\u0015\u001a\u0006\u0012\u0002\b\u00030\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0014R\u001a\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR3\u0010\t\u001a#\u0012\u001f\u0012\u001d\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\u0002\b\f0\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\r\u0010\b¨\u0006\u001d"}, d2 = {"Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation$UpdateNode;", "Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation;", "<init>", "()V", "Value", "Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation$ObjectParameter;", "", "getValue-w8_IdGo", "()I", "Block", "Lkotlin/Function2;", "", "Lkotlin/ExtensionFunctionType;", "getBlock-w8_IdGo", "objectParamName", "", "parameter", "objectParamName-PtL-UHM", "(I)Ljava/lang/String;", "execute", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationArgContainer;", "applier", "Landroidx/compose/runtime/Applier;", "slots", "Landroidx/compose/runtime/composer/gapbuffer/SlotWriter;", "rememberManager", "Landroidx/compose/runtime/composer/RememberManager;", "errorContext", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationErrorContext;", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class UpdateNode extends Operation {
        public static final int $stable = 0;
        public static final UpdateNode INSTANCE = new UpdateNode();

        private UpdateNode() {
            super(0, 2, 1, null);
        }

        /* JADX INFO: renamed from: getValue-w8_IdGo, reason: not valid java name */
        public final int m4565getValuew8_IdGo() {
            return ObjectParameter.m4547constructorimpl(0);
        }

        /* JADX INFO: renamed from: getBlock-w8_IdGo, reason: not valid java name */
        public final int m4564getBlockw8_IdGo() {
            return ObjectParameter.m4547constructorimpl(1);
        }

        @Override // androidx.compose.runtime.composer.gapbuffer.changelist.Operation
        /* JADX INFO: renamed from: objectParamName-PtL-UHM */
        public String mo4521objectParamNamePtLUHM(int parameter) {
            return ObjectParameter.m4549equalsimpl0(parameter, ObjectParameter.m4547constructorimpl(0)) ? "value" : ObjectParameter.m4549equalsimpl0(parameter, ObjectParameter.m4547constructorimpl(1)) ? "block" : super.mo4521objectParamNamePtLUHM(parameter);
        }

        @Override // androidx.compose.runtime.composer.gapbuffer.changelist.Operation
        protected void execute(OperationArgContainer $this$execute, Applier<?> applier, SlotWriter slots, RememberManager rememberManager, OperationErrorContext errorContext) {
            Object value = $this$execute.mo4567getObjectPtLUHM(ObjectParameter.m4547constructorimpl(0));
            applier.apply((Function2) $this$execute.mo4567getObjectPtLUHM(ObjectParameter.m4547constructorimpl(1)), value);
        }
    }

    /* JADX INFO: compiled from: Operation.kt */
    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\n\u001a\u00020\u000b2\n\u0010\f\u001a\u00060\u0005j\u0002`\rH\u0016J2\u0010\u000e\u001a\u00020\u000f*\u00020\u00102\n\u0010\u0011\u001a\u0006\u0012\u0002\b\u00030\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0014R\u0012\u0010\u0004\u001a\u00020\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0012\u0010\b\u001a\u00020\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\t\u0010\u0007¨\u0006\u0019"}, d2 = {"Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation$RemoveNode;", "Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation;", "<init>", "()V", "RemoveIndex", "", "getRemoveIndex", "()I", "Count", "getCount", "intParamName", "", "parameter", "Landroidx/compose/runtime/composer/gapbuffer/changelist/IntParameter;", "execute", "", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationArgContainer;", "applier", "Landroidx/compose/runtime/Applier;", "slots", "Landroidx/compose/runtime/composer/gapbuffer/SlotWriter;", "rememberManager", "Landroidx/compose/runtime/composer/RememberManager;", "errorContext", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationErrorContext;", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class RemoveNode extends Operation {
        public static final int $stable = 0;
        public static final RemoveNode INSTANCE = new RemoveNode();

        /* JADX WARN: Illegal instructions before constructor call */
        private RemoveNode() {
            int i = 2;
            super(i, 0, i, null);
        }

        public final int getRemoveIndex() {
            return 0;
        }

        public final int getCount() {
            return 1;
        }

        @Override // androidx.compose.runtime.composer.gapbuffer.changelist.Operation
        public String intParamName(int parameter) {
            return parameter == 0 ? "removeIndex" : parameter == 1 ? "count" : super.intParamName(parameter);
        }

        @Override // androidx.compose.runtime.composer.gapbuffer.changelist.Operation
        protected void execute(OperationArgContainer $this$execute, Applier<?> applier, SlotWriter slots, RememberManager rememberManager, OperationErrorContext errorContext) {
            applier.remove($this$execute.getInt(0), $this$execute.getInt(1));
        }
    }

    /* JADX INFO: compiled from: Operation.kt */
    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\f\u001a\u00020\r2\n\u0010\u000e\u001a\u00060\u0005j\u0002`\u000fH\u0016J2\u0010\u0010\u001a\u00020\u0011*\u00020\u00122\n\u0010\u0013\u001a\u0006\u0012\u0002\b\u00030\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0014R\u0012\u0010\u0004\u001a\u00020\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0012\u0010\b\u001a\u00020\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\t\u0010\u0007R\u0012\u0010\n\u001a\u00020\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\u000b\u0010\u0007¨\u0006\u001b"}, d2 = {"Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation$MoveNode;", "Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation;", "<init>", "()V", "From", "", "getFrom", "()I", "To", "getTo", "Count", "getCount", "intParamName", "", "parameter", "Landroidx/compose/runtime/composer/gapbuffer/changelist/IntParameter;", "execute", "", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationArgContainer;", "applier", "Landroidx/compose/runtime/Applier;", "slots", "Landroidx/compose/runtime/composer/gapbuffer/SlotWriter;", "rememberManager", "Landroidx/compose/runtime/composer/RememberManager;", "errorContext", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationErrorContext;", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class MoveNode extends Operation {
        public static final int $stable = 0;
        public static final MoveNode INSTANCE = new MoveNode();

        private MoveNode() {
            super(3, 0, 2, null);
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

        @Override // androidx.compose.runtime.composer.gapbuffer.changelist.Operation
        public String intParamName(int parameter) {
            return parameter == 0 ? TypedValues.TransitionType.S_FROM : parameter == 1 ? TypedValues.TransitionType.S_TO : parameter == 2 ? "count" : super.intParamName(parameter);
        }

        @Override // androidx.compose.runtime.composer.gapbuffer.changelist.Operation
        protected void execute(OperationArgContainer $this$execute, Applier<?> applier, SlotWriter slots, RememberManager rememberManager, OperationErrorContext errorContext) {
            applier.move($this$execute.getInt(0), $this$execute.getInt(1), $this$execute.getInt(2));
        }
    }

    /* JADX INFO: compiled from: Operation.kt */
    @Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\f\u001a\u00020\r2\n\u0010\u000e\u001a\u0006\u0012\u0002\b\u00030\u0005H\u0016¢\u0006\u0004\b\u000f\u0010\u0010J2\u0010\u0011\u001a\u00020\u0012*\u00020\u00132\n\u0010\u0014\u001a\u0006\u0012\u0002\b\u00030\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0014R\u0018\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0018\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\u000b\u0010\b¨\u0006\u001c"}, d2 = {"Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation$InsertSlots;", "Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation;", "<init>", "()V", "Anchor", "Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation$ObjectParameter;", "Landroidx/compose/runtime/composer/gapbuffer/GapAnchor;", "getAnchor-w8_IdGo", "()I", "FromSlotTable", "Landroidx/compose/runtime/composer/gapbuffer/SlotTable;", "getFromSlotTable-w8_IdGo", "objectParamName", "", "parameter", "objectParamName-PtL-UHM", "(I)Ljava/lang/String;", "execute", "", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationArgContainer;", "applier", "Landroidx/compose/runtime/Applier;", "slots", "Landroidx/compose/runtime/composer/gapbuffer/SlotWriter;", "rememberManager", "Landroidx/compose/runtime/composer/RememberManager;", "errorContext", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationErrorContext;", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class InsertSlots extends Operation {
        public static final int $stable = 0;
        public static final InsertSlots INSTANCE = new InsertSlots();

        private InsertSlots() {
            super(0, 2, 1, null);
        }

        /* JADX INFO: renamed from: getAnchor-w8_IdGo, reason: not valid java name */
        public final int m4541getAnchorw8_IdGo() {
            return ObjectParameter.m4547constructorimpl(0);
        }

        /* JADX INFO: renamed from: getFromSlotTable-w8_IdGo, reason: not valid java name */
        public final int m4542getFromSlotTablew8_IdGo() {
            return ObjectParameter.m4547constructorimpl(1);
        }

        @Override // androidx.compose.runtime.composer.gapbuffer.changelist.Operation
        /* JADX INFO: renamed from: objectParamName-PtL-UHM */
        public String mo4521objectParamNamePtLUHM(int parameter) {
            return ObjectParameter.m4549equalsimpl0(parameter, ObjectParameter.m4547constructorimpl(0)) ? "anchor" : ObjectParameter.m4549equalsimpl0(parameter, ObjectParameter.m4547constructorimpl(1)) ? TypedValues.TransitionType.S_FROM : super.mo4521objectParamNamePtLUHM(parameter);
        }

        @Override // androidx.compose.runtime.composer.gapbuffer.changelist.Operation
        protected void execute(OperationArgContainer $this$execute, Applier<?> applier, SlotWriter slots, RememberManager rememberManager, OperationErrorContext errorContext) {
            SlotTable insertTable = (SlotTable) $this$execute.mo4567getObjectPtLUHM(ObjectParameter.m4547constructorimpl(1));
            GapAnchor anchor = (GapAnchor) $this$execute.mo4567getObjectPtLUHM(ObjectParameter.m4547constructorimpl(0));
            slots.beginInsert();
            slots.moveFrom(insertTable, anchor.toIndexFor(insertTable), false);
            slots.endInsert();
        }
    }

    /* JADX INFO: compiled from: Operation.kt */
    @Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\u000f\u001a\u00020\u00102\n\u0010\u0011\u001a\u0006\u0012\u0002\b\u00030\u0005H\u0016¢\u0006\u0004\b\u0012\u0010\u0013J2\u0010\u0014\u001a\u00020\u0015*\u00020\u00162\n\u0010\u0017\u001a\u0006\u0012\u0002\b\u00030\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0014R\u0018\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0018\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\u000b\u0010\bR\u0018\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\u000e\u0010\b¨\u0006\u001f"}, d2 = {"Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation$InsertSlotsWithFixups;", "Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation;", "<init>", "()V", "Anchor", "Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation$ObjectParameter;", "Landroidx/compose/runtime/composer/gapbuffer/GapAnchor;", "getAnchor-w8_IdGo", "()I", "FromSlotTable", "Landroidx/compose/runtime/composer/gapbuffer/SlotTable;", "getFromSlotTable-w8_IdGo", "Fixups", "Landroidx/compose/runtime/composer/gapbuffer/changelist/FixupList;", "getFixups-w8_IdGo", "objectParamName", "", "parameter", "objectParamName-PtL-UHM", "(I)Ljava/lang/String;", "execute", "", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationArgContainer;", "applier", "Landroidx/compose/runtime/Applier;", "slots", "Landroidx/compose/runtime/composer/gapbuffer/SlotWriter;", "rememberManager", "Landroidx/compose/runtime/composer/RememberManager;", "errorContext", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationErrorContext;", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class InsertSlotsWithFixups extends Operation {
        public static final int $stable = 0;
        public static final InsertSlotsWithFixups INSTANCE = new InsertSlotsWithFixups();

        private InsertSlotsWithFixups() {
            super(0, 3, 1, null);
        }

        /* JADX INFO: renamed from: getAnchor-w8_IdGo, reason: not valid java name */
        public final int m4543getAnchorw8_IdGo() {
            return ObjectParameter.m4547constructorimpl(0);
        }

        /* JADX INFO: renamed from: getFromSlotTable-w8_IdGo, reason: not valid java name */
        public final int m4545getFromSlotTablew8_IdGo() {
            return ObjectParameter.m4547constructorimpl(1);
        }

        /* JADX INFO: renamed from: getFixups-w8_IdGo, reason: not valid java name */
        public final int m4544getFixupsw8_IdGo() {
            return ObjectParameter.m4547constructorimpl(2);
        }

        @Override // androidx.compose.runtime.composer.gapbuffer.changelist.Operation
        /* JADX INFO: renamed from: objectParamName-PtL-UHM */
        public String mo4521objectParamNamePtLUHM(int parameter) {
            return ObjectParameter.m4549equalsimpl0(parameter, ObjectParameter.m4547constructorimpl(0)) ? "anchor" : ObjectParameter.m4549equalsimpl0(parameter, ObjectParameter.m4547constructorimpl(1)) ? TypedValues.TransitionType.S_FROM : ObjectParameter.m4549equalsimpl0(parameter, ObjectParameter.m4547constructorimpl(2)) ? "fixups" : super.mo4521objectParamNamePtLUHM(parameter);
        }

        @Override // androidx.compose.runtime.composer.gapbuffer.changelist.Operation
        protected void execute(OperationArgContainer $this$execute, Applier<?> applier, SlotWriter slots, RememberManager rememberManager, OperationErrorContext errorContext) throws Throwable {
            OperationErrorContext operationErrorContextWithCurrentStackTrace;
            SlotTable insertTable = (SlotTable) $this$execute.mo4567getObjectPtLUHM(ObjectParameter.m4547constructorimpl(1));
            GapAnchor anchor = (GapAnchor) $this$execute.mo4567getObjectPtLUHM(ObjectParameter.m4547constructorimpl(0));
            FixupList fixups = (FixupList) $this$execute.mo4567getObjectPtLUHM(ObjectParameter.m4547constructorimpl(2));
            SlotWriter writer$iv = insertTable.openWriter();
            if (errorContext != null) {
                try {
                    operationErrorContextWithCurrentStackTrace = OperationKt.withCurrentStackTrace(errorContext, slots);
                } catch (Throwable th) {
                    th = th;
                    writer$iv.close(false);
                    throw th;
                }
            } else {
                operationErrorContextWithCurrentStackTrace = null;
            }
            try {
                fixups.executeAndFlushAllPendingFixups(applier, writer$iv, rememberManager, operationErrorContextWithCurrentStackTrace);
                Unit unit = Unit.INSTANCE;
                writer$iv.close(true);
                slots.beginInsert();
                slots.moveFrom(insertTable, anchor.toIndexFor(insertTable), false);
                slots.endInsert();
            } catch (Throwable th2) {
                th = th2;
                writer$iv.close(false);
                throw th;
            }
        }
    }

    /* JADX INFO: compiled from: Operation.kt */
    @Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0010\u001a\u00020\u00112\n\u0010\u0012\u001a\u00060\u000bj\u0002`\u0013H\u0016J\u001b\u0010\u0014\u001a\u00020\u00112\n\u0010\u0012\u001a\u0006\u0012\u0002\b\u00030\u0005H\u0016¢\u0006\u0004\b\u0015\u0010\u0016J\u0016\u0010\u0017\u001a\u0004\u0018\u00010\u000e*\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0014J2\u0010\u001b\u001a\u00020\u001c*\u00020\u00182\n\u0010\u001d\u001a\u0006\u0012\u0002\b\u00030\u001e2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\"H\u0014R \u0010\u0004\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00060\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0012\u0010\n\u001a\u00020\u000b8Æ\u0002¢\u0006\u0006\u001a\u0004\b\f\u0010\tR\u0018\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\u000f\u0010\t¨\u0006#"}, d2 = {"Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation$InsertNodeFixup;", "Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation;", "<init>", "()V", "Factory", "Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation$ObjectParameter;", "Lkotlin/Function0;", "", "getFactory-w8_IdGo", "()I", "InsertIndex", "", "getInsertIndex", "GroupAnchor", "Landroidx/compose/runtime/composer/gapbuffer/GapAnchor;", "getGroupAnchor-w8_IdGo", "intParamName", "", "parameter", "Landroidx/compose/runtime/composer/gapbuffer/changelist/IntParameter;", "objectParamName", "objectParamName-PtL-UHM", "(I)Ljava/lang/String;", "getGroupAnchor", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationArgContainer;", "slots", "Landroidx/compose/runtime/composer/gapbuffer/SlotWriter;", "execute", "", "applier", "Landroidx/compose/runtime/Applier;", "rememberManager", "Landroidx/compose/runtime/composer/RememberManager;", "errorContext", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationErrorContext;", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class InsertNodeFixup extends Operation {
        public static final int $stable = 0;
        public static final InsertNodeFixup INSTANCE = new InsertNodeFixup();

        private InsertNodeFixup() {
            super(1, 2, null);
        }

        /* JADX INFO: renamed from: getFactory-w8_IdGo, reason: not valid java name */
        public final int m4539getFactoryw8_IdGo() {
            return ObjectParameter.m4547constructorimpl(0);
        }

        public final int getInsertIndex() {
            return 0;
        }

        /* JADX INFO: renamed from: getGroupAnchor-w8_IdGo, reason: not valid java name */
        public final int m4540getGroupAnchorw8_IdGo() {
            return ObjectParameter.m4547constructorimpl(1);
        }

        @Override // androidx.compose.runtime.composer.gapbuffer.changelist.Operation
        public String intParamName(int parameter) {
            return parameter == 0 ? "insertIndex" : super.intParamName(parameter);
        }

        @Override // androidx.compose.runtime.composer.gapbuffer.changelist.Operation
        /* JADX INFO: renamed from: objectParamName-PtL-UHM */
        public String mo4521objectParamNamePtLUHM(int parameter) {
            return ObjectParameter.m4549equalsimpl0(parameter, ObjectParameter.m4547constructorimpl(0)) ? "factory" : ObjectParameter.m4549equalsimpl0(parameter, ObjectParameter.m4547constructorimpl(1)) ? "groupAnchor" : super.mo4521objectParamNamePtLUHM(parameter);
        }

        @Override // androidx.compose.runtime.composer.gapbuffer.changelist.Operation
        protected GapAnchor getGroupAnchor(OperationArgContainer $this$getGroupAnchor, SlotWriter slots) {
            return (GapAnchor) $this$getGroupAnchor.mo4567getObjectPtLUHM(ObjectParameter.m4547constructorimpl(1));
        }

        @Override // androidx.compose.runtime.composer.gapbuffer.changelist.Operation
        protected void execute(OperationArgContainer $this$execute, Applier<?> applier, SlotWriter slots, RememberManager rememberManager, OperationErrorContext errorContext) {
            Object node = ((Function0) $this$execute.mo4567getObjectPtLUHM(ObjectParameter.m4547constructorimpl(0))).invoke();
            GapAnchor groupAnchor = (GapAnchor) $this$execute.mo4567getObjectPtLUHM(ObjectParameter.m4547constructorimpl(1));
            int insertIndex = $this$execute.getInt(0);
            Intrinsics.checkNotNull(applier, "null cannot be cast to non-null type androidx.compose.runtime.Applier<kotlin.Any?>");
            slots.updateNode(groupAnchor, node);
            applier.insertTopDown(insertIndex, node);
            applier.down(node);
        }
    }

    /* JADX INFO: compiled from: Operation.kt */
    @Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\f\u001a\u00020\r2\n\u0010\u000e\u001a\u00060\u0005j\u0002`\u000fH\u0016J\u001b\u0010\u0010\u001a\u00020\r2\n\u0010\u000e\u001a\u0006\u0012\u0002\b\u00030\tH\u0016¢\u0006\u0004\b\u0011\u0010\u0012J\u0016\u0010\u0013\u001a\u0004\u0018\u00010\n*\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0014J2\u0010\u0017\u001a\u00020\u0018*\u00020\u00142\n\u0010\u0019\u001a\u0006\u0012\u0002\b\u00030\u001a2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0014R\u0012\u0010\u0004\u001a\u00020\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0018\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t8Æ\u0002¢\u0006\u0006\u001a\u0004\b\u000b\u0010\u0007¨\u0006\u001f"}, d2 = {"Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation$PostInsertNodeFixup;", "Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation;", "<init>", "()V", "InsertIndex", "", "getInsertIndex", "()I", "GroupAnchor", "Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation$ObjectParameter;", "Landroidx/compose/runtime/composer/gapbuffer/GapAnchor;", "getGroupAnchor-w8_IdGo", "intParamName", "", "parameter", "Landroidx/compose/runtime/composer/gapbuffer/changelist/IntParameter;", "objectParamName", "objectParamName-PtL-UHM", "(I)Ljava/lang/String;", "getGroupAnchor", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationArgContainer;", "slots", "Landroidx/compose/runtime/composer/gapbuffer/SlotWriter;", "execute", "", "applier", "Landroidx/compose/runtime/Applier;", "rememberManager", "Landroidx/compose/runtime/composer/RememberManager;", "errorContext", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationErrorContext;", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class PostInsertNodeFixup extends Operation {
        public static final int $stable = 0;
        public static final PostInsertNodeFixup INSTANCE = new PostInsertNodeFixup();

        /* JADX WARN: Illegal instructions before constructor call */
        private PostInsertNodeFixup() {
            int i = 1;
            super(i, i, null);
        }

        public final int getInsertIndex() {
            return 0;
        }

        /* JADX INFO: renamed from: getGroupAnchor-w8_IdGo, reason: not valid java name */
        public final int m4553getGroupAnchorw8_IdGo() {
            return ObjectParameter.m4547constructorimpl(0);
        }

        @Override // androidx.compose.runtime.composer.gapbuffer.changelist.Operation
        public String intParamName(int parameter) {
            return parameter == 0 ? "insertIndex" : super.intParamName(parameter);
        }

        @Override // androidx.compose.runtime.composer.gapbuffer.changelist.Operation
        /* JADX INFO: renamed from: objectParamName-PtL-UHM */
        public String mo4521objectParamNamePtLUHM(int parameter) {
            return ObjectParameter.m4549equalsimpl0(parameter, ObjectParameter.m4547constructorimpl(0)) ? "groupAnchor" : super.mo4521objectParamNamePtLUHM(parameter);
        }

        @Override // androidx.compose.runtime.composer.gapbuffer.changelist.Operation
        protected GapAnchor getGroupAnchor(OperationArgContainer $this$getGroupAnchor, SlotWriter slots) {
            return (GapAnchor) $this$getGroupAnchor.mo4567getObjectPtLUHM(ObjectParameter.m4547constructorimpl(0));
        }

        @Override // androidx.compose.runtime.composer.gapbuffer.changelist.Operation
        protected void execute(OperationArgContainer $this$execute, Applier<?> applier, SlotWriter slots, RememberManager rememberManager, OperationErrorContext errorContext) {
            GapAnchor groupAnchor = (GapAnchor) $this$execute.mo4567getObjectPtLUHM(ObjectParameter.m4547constructorimpl(0));
            int insertIndex = $this$execute.getInt(0);
            applier.up();
            Intrinsics.checkNotNull(applier, "null cannot be cast to non-null type androidx.compose.runtime.Applier<kotlin.Any?>");
            Object nodeToInsert = slots.node(groupAnchor);
            applier.insertBottomUp(insertIndex, nodeToInsert);
        }
    }

    /* JADX INFO: compiled from: Operation.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J2\u0010\u0004\u001a\u00020\u0005*\u00020\u00062\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0014¨\u0006\u000f"}, d2 = {"Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation$DeactivateCurrentGroup;", "Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation;", "<init>", "()V", "execute", "", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationArgContainer;", "applier", "Landroidx/compose/runtime/Applier;", "slots", "Landroidx/compose/runtime/composer/gapbuffer/SlotWriter;", "rememberManager", "Landroidx/compose/runtime/composer/RememberManager;", "errorContext", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationErrorContext;", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class DeactivateCurrentGroup extends Operation {
        public static final int $stable = 0;
        public static final DeactivateCurrentGroup INSTANCE = new DeactivateCurrentGroup();

        /* JADX WARN: Illegal instructions before constructor call */
        private DeactivateCurrentGroup() {
            int i = 0;
            super(i, i, 3, null);
        }

        @Override // androidx.compose.runtime.composer.gapbuffer.changelist.Operation
        protected void execute(OperationArgContainer $this$execute, Applier<?> applier, SlotWriter slots, RememberManager rememberManager, OperationErrorContext errorContext) {
            GapComposerKt.deactivateCurrentGroup(slots, rememberManager);
        }
    }

    /* JADX INFO: compiled from: Operation.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J2\u0010\u0004\u001a\u00020\u0005*\u00020\u00062\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0014¨\u0006\u000f"}, d2 = {"Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation$ResetSlots;", "Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation;", "<init>", "()V", "execute", "", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationArgContainer;", "applier", "Landroidx/compose/runtime/Applier;", "slots", "Landroidx/compose/runtime/composer/gapbuffer/SlotWriter;", "rememberManager", "Landroidx/compose/runtime/composer/RememberManager;", "errorContext", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationErrorContext;", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class ResetSlots extends Operation {
        public static final int $stable = 0;
        public static final ResetSlots INSTANCE = new ResetSlots();

        /* JADX WARN: Illegal instructions before constructor call */
        private ResetSlots() {
            int i = 0;
            super(i, i, 3, null);
        }

        @Override // androidx.compose.runtime.composer.gapbuffer.changelist.Operation
        protected void execute(OperationArgContainer $this$execute, Applier<?> applier, SlotWriter slots, RememberManager rememberManager, OperationErrorContext errorContext) {
            slots.reset();
        }
    }

    /* JADX INFO: compiled from: Operation.kt */
    @Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\f\u001a\u00020\r2\n\u0010\u000e\u001a\u0006\u0012\u0002\b\u00030\u0005H\u0016¢\u0006\u0004\b\u000f\u0010\u0010J2\u0010\u0011\u001a\u00020\u0012*\u00020\u00132\n\u0010\u0014\u001a\u0006\u0012\u0002\b\u00030\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0014R\u0018\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0018\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\u000b\u0010\b¨\u0006\u001c"}, d2 = {"Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation$DetermineMovableContentNodeIndex;", "Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation;", "<init>", "()V", "EffectiveNodeIndexOut", "Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation$ObjectParameter;", "Landroidx/compose/runtime/internal/IntRef;", "getEffectiveNodeIndexOut-w8_IdGo", "()I", "Anchor", "Landroidx/compose/runtime/composer/gapbuffer/GapAnchor;", "getAnchor-w8_IdGo", "objectParamName", "", "parameter", "objectParamName-PtL-UHM", "(I)Ljava/lang/String;", "execute", "", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationArgContainer;", "applier", "Landroidx/compose/runtime/Applier;", "slots", "Landroidx/compose/runtime/composer/gapbuffer/SlotWriter;", "rememberManager", "Landroidx/compose/runtime/composer/RememberManager;", "errorContext", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationErrorContext;", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class DetermineMovableContentNodeIndex extends Operation {
        public static final int $stable = 0;
        public static final DetermineMovableContentNodeIndex INSTANCE = new DetermineMovableContentNodeIndex();

        private DetermineMovableContentNodeIndex() {
            super(0, 2, 1, null);
        }

        /* JADX INFO: renamed from: getEffectiveNodeIndexOut-w8_IdGo, reason: not valid java name */
        public final int m4533getEffectiveNodeIndexOutw8_IdGo() {
            return ObjectParameter.m4547constructorimpl(0);
        }

        /* JADX INFO: renamed from: getAnchor-w8_IdGo, reason: not valid java name */
        public final int m4532getAnchorw8_IdGo() {
            return ObjectParameter.m4547constructorimpl(1);
        }

        @Override // androidx.compose.runtime.composer.gapbuffer.changelist.Operation
        /* JADX INFO: renamed from: objectParamName-PtL-UHM */
        public String mo4521objectParamNamePtLUHM(int parameter) {
            return ObjectParameter.m4549equalsimpl0(parameter, ObjectParameter.m4547constructorimpl(0)) ? "effectiveNodeIndexOut" : ObjectParameter.m4549equalsimpl0(parameter, ObjectParameter.m4547constructorimpl(1)) ? "anchor" : super.mo4521objectParamNamePtLUHM(parameter);
        }

        @Override // androidx.compose.runtime.composer.gapbuffer.changelist.Operation
        protected void execute(OperationArgContainer $this$execute, Applier<?> applier, SlotWriter slots, RememberManager rememberManager, OperationErrorContext errorContext) {
            IntRef effectiveNodeIndexOut = (IntRef) $this$execute.mo4567getObjectPtLUHM(ObjectParameter.m4547constructorimpl(0));
            GapAnchor gapAnchor = (GapAnchor) $this$execute.mo4567getObjectPtLUHM(ObjectParameter.m4547constructorimpl(1));
            Intrinsics.checkNotNull(applier, "null cannot be cast to non-null type androidx.compose.runtime.Applier<kotlin.Any?>");
            effectiveNodeIndexOut.setElement(OperationKt.positionToInsert(slots, gapAnchor, applier));
        }
    }

    /* JADX INFO: compiled from: Operation.kt */
    @Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\r\u001a\u00020\u000e2\n\u0010\u000f\u001a\u0006\u0012\u0002\b\u00030\u0005H\u0016¢\u0006\u0004\b\u0010\u0010\u0011J2\u0010\u0012\u001a\u00020\u0013*\u00020\u00142\n\u0010\u0015\u001a\u0006\u0012\u0002\b\u00030\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0014R\u0018\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR \u0010\t\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\n0\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\f\u0010\b¨\u0006\u001d"}, d2 = {"Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation$CopyNodesToNewAnchorLocation;", "Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation;", "<init>", "()V", "EffectiveNodeIndex", "Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation$ObjectParameter;", "Landroidx/compose/runtime/internal/IntRef;", "getEffectiveNodeIndex-w8_IdGo", "()I", "Nodes", "", "", "getNodes-w8_IdGo", "objectParamName", "", "parameter", "objectParamName-PtL-UHM", "(I)Ljava/lang/String;", "execute", "", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationArgContainer;", "applier", "Landroidx/compose/runtime/Applier;", "slots", "Landroidx/compose/runtime/composer/gapbuffer/SlotWriter;", "rememberManager", "Landroidx/compose/runtime/composer/RememberManager;", "errorContext", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationErrorContext;", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class CopyNodesToNewAnchorLocation extends Operation {
        public static final int $stable = 0;
        public static final CopyNodesToNewAnchorLocation INSTANCE = new CopyNodesToNewAnchorLocation();

        private CopyNodesToNewAnchorLocation() {
            super(0, 2, 1, null);
        }

        /* JADX INFO: renamed from: getEffectiveNodeIndex-w8_IdGo, reason: not valid java name */
        public final int m4526getEffectiveNodeIndexw8_IdGo() {
            return ObjectParameter.m4547constructorimpl(0);
        }

        /* JADX INFO: renamed from: getNodes-w8_IdGo, reason: not valid java name */
        public final int m4527getNodesw8_IdGo() {
            return ObjectParameter.m4547constructorimpl(1);
        }

        @Override // androidx.compose.runtime.composer.gapbuffer.changelist.Operation
        /* JADX INFO: renamed from: objectParamName-PtL-UHM */
        public String mo4521objectParamNamePtLUHM(int parameter) {
            return ObjectParameter.m4549equalsimpl0(parameter, ObjectParameter.m4547constructorimpl(0)) ? "effectiveNodeIndex" : ObjectParameter.m4549equalsimpl0(parameter, ObjectParameter.m4547constructorimpl(1)) ? "nodes" : super.mo4521objectParamNamePtLUHM(parameter);
        }

        @Override // androidx.compose.runtime.composer.gapbuffer.changelist.Operation
        protected void execute(OperationArgContainer $this$execute, Applier<?> applier, SlotWriter slots, RememberManager rememberManager, OperationErrorContext errorContext) {
            int effectiveNodeIndex = ((IntRef) $this$execute.mo4567getObjectPtLUHM(ObjectParameter.m4547constructorimpl(0))).getElement();
            List nodesToInsert = (List) $this$execute.mo4567getObjectPtLUHM(ObjectParameter.m4547constructorimpl(1));
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
    @Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\u0011\u001a\u00020\u00122\n\u0010\u0013\u001a\u0006\u0012\u0002\b\u00030\u0005H\u0016¢\u0006\u0004\b\u0014\u0010\u0015J2\u0010\u0016\u001a\u00020\u0017*\u00020\u00182\n\u0010\u0019\u001a\u0006\u0012\u0002\b\u00030\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0014R\u001a\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0018\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\u000b\u0010\bR\u0018\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\u000e\u0010\bR\u0018\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\r0\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0010\u0010\b¨\u0006!"}, d2 = {"Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation$CopySlotTableToAnchorLocation;", "Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation;", "<init>", "()V", "ResolvedState", "Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation$ObjectParameter;", "Landroidx/compose/runtime/MovableContentState;", "getResolvedState-w8_IdGo", "()I", "ParentCompositionContext", "Landroidx/compose/runtime/CompositionContext;", "getParentCompositionContext-w8_IdGo", "From", "Landroidx/compose/runtime/MovableContentStateReference;", "getFrom-w8_IdGo", "To", "getTo-w8_IdGo", "objectParamName", "", "parameter", "objectParamName-PtL-UHM", "(I)Ljava/lang/String;", "execute", "", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationArgContainer;", "applier", "Landroidx/compose/runtime/Applier;", "slots", "Landroidx/compose/runtime/composer/gapbuffer/SlotWriter;", "rememberManager", "Landroidx/compose/runtime/composer/RememberManager;", "errorContext", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationErrorContext;", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class CopySlotTableToAnchorLocation extends Operation {
        public static final int $stable = 0;
        public static final CopySlotTableToAnchorLocation INSTANCE = new CopySlotTableToAnchorLocation();

        private CopySlotTableToAnchorLocation() {
            super(0, 4, 1, null);
        }

        /* JADX INFO: renamed from: getResolvedState-w8_IdGo, reason: not valid java name */
        public final int m4530getResolvedStatew8_IdGo() {
            return ObjectParameter.m4547constructorimpl(0);
        }

        /* JADX INFO: renamed from: getParentCompositionContext-w8_IdGo, reason: not valid java name */
        public final int m4529getParentCompositionContextw8_IdGo() {
            return ObjectParameter.m4547constructorimpl(1);
        }

        /* JADX INFO: renamed from: getFrom-w8_IdGo, reason: not valid java name */
        public final int m4528getFromw8_IdGo() {
            return ObjectParameter.m4547constructorimpl(2);
        }

        /* JADX INFO: renamed from: getTo-w8_IdGo, reason: not valid java name */
        public final int m4531getTow8_IdGo() {
            return ObjectParameter.m4547constructorimpl(3);
        }

        @Override // androidx.compose.runtime.composer.gapbuffer.changelist.Operation
        /* JADX INFO: renamed from: objectParamName-PtL-UHM */
        public String mo4521objectParamNamePtLUHM(int parameter) {
            return ObjectParameter.m4549equalsimpl0(parameter, ObjectParameter.m4547constructorimpl(0)) ? "resolvedState" : ObjectParameter.m4549equalsimpl0(parameter, ObjectParameter.m4547constructorimpl(1)) ? "resolvedCompositionContext" : ObjectParameter.m4549equalsimpl0(parameter, ObjectParameter.m4547constructorimpl(2)) ? TypedValues.TransitionType.S_FROM : ObjectParameter.m4549equalsimpl0(parameter, ObjectParameter.m4547constructorimpl(3)) ? TypedValues.TransitionType.S_TO : super.mo4521objectParamNamePtLUHM(parameter);
        }

        @Override // androidx.compose.runtime.composer.gapbuffer.changelist.Operation
        protected void execute(OperationArgContainer $this$execute, Applier<?> applier, SlotWriter slots, RememberManager rememberManager, OperationErrorContext errorContext) throws Throwable {
            MovableContentStateReference from = (MovableContentStateReference) $this$execute.mo4567getObjectPtLUHM(ObjectParameter.m4547constructorimpl(2));
            MovableContentStateReference to = (MovableContentStateReference) $this$execute.mo4567getObjectPtLUHM(ObjectParameter.m4547constructorimpl(3));
            CompositionContext parentCompositionContext = (CompositionContext) $this$execute.mo4567getObjectPtLUHM(ObjectParameter.m4547constructorimpl(1));
            MovableContentState resolvedState = (MovableContentState) $this$execute.mo4567getObjectPtLUHM(ObjectParameter.m4547constructorimpl(0));
            if (resolvedState == null && (resolvedState = parentCompositionContext.movableContentStateResolve$runtime(from)) == null) {
                ComposerKt.composeRuntimeError("Could not resolve state for movable content");
                throw new KotlinNothingValueException();
            }
            List<GapAnchor> listMoveIntoGroupFrom = slots.moveIntoGroupFrom(1, SlotTableKt.asGapBufferSlotTable(resolvedState.getSlotStorage()), 2);
            RecomposeScopeImpl.Companion companion = RecomposeScopeImpl.INSTANCE;
            ControlledComposition composition = to.getComposition();
            Intrinsics.checkNotNull(composition, "null cannot be cast to non-null type androidx.compose.runtime.RecomposeScopeOwner");
            companion.adoptAnchoredScopes$runtime(slots, listMoveIntoGroupFrom, (RecomposeScopeOwner) composition);
        }
    }

    /* JADX INFO: compiled from: Operation.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J2\u0010\u0004\u001a\u00020\u0005*\u00020\u00062\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0014¨\u0006\u000f"}, d2 = {"Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation$EndMovableContentPlacement;", "Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation;", "<init>", "()V", "execute", "", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationArgContainer;", "applier", "Landroidx/compose/runtime/Applier;", "slots", "Landroidx/compose/runtime/composer/gapbuffer/SlotWriter;", "rememberManager", "Landroidx/compose/runtime/composer/RememberManager;", "errorContext", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationErrorContext;", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class EndMovableContentPlacement extends Operation {
        public static final int $stable = 0;
        public static final EndMovableContentPlacement INSTANCE = new EndMovableContentPlacement();

        /* JADX WARN: Illegal instructions before constructor call */
        private EndMovableContentPlacement() {
            int i = 0;
            super(i, i, 3, null);
        }

        @Override // androidx.compose.runtime.composer.gapbuffer.changelist.Operation
        protected void execute(OperationArgContainer $this$execute, Applier<?> applier, SlotWriter slots, RememberManager rememberManager, OperationErrorContext errorContext) {
            Intrinsics.checkNotNull(applier, "null cannot be cast to non-null type androidx.compose.runtime.Applier<kotlin.Any?>");
            OperationKt.positionToParentOf(slots, applier, 0);
            slots.endGroup();
        }
    }

    /* JADX INFO: compiled from: Operation.kt */
    @Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\u000f\u001a\u00020\u00102\n\u0010\u0011\u001a\u0006\u0012\u0002\b\u00030\u0005H\u0016¢\u0006\u0004\b\u0012\u0010\u0013J2\u0010\u0014\u001a\u00020\u0015*\u00020\u00162\n\u0010\u0017\u001a\u0006\u0012\u0002\b\u00030\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0014R\u0018\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0018\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\u000b\u0010\bR\u0018\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\u000e\u0010\b¨\u0006\u001f"}, d2 = {"Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation$ReleaseMovableGroupAtCurrent;", "Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation;", "<init>", "()V", "Composition", "Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation$ObjectParameter;", "Landroidx/compose/runtime/ControlledComposition;", "getComposition-w8_IdGo", "()I", "ParentCompositionContext", "Landroidx/compose/runtime/CompositionContext;", "getParentCompositionContext-w8_IdGo", "Reference", "Landroidx/compose/runtime/MovableContentStateReference;", "getReference-w8_IdGo", "objectParamName", "", "parameter", "objectParamName-PtL-UHM", "(I)Ljava/lang/String;", "execute", "", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationArgContainer;", "applier", "Landroidx/compose/runtime/Applier;", "slots", "Landroidx/compose/runtime/composer/gapbuffer/SlotWriter;", "rememberManager", "Landroidx/compose/runtime/composer/RememberManager;", "errorContext", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationErrorContext;", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class ReleaseMovableGroupAtCurrent extends Operation {
        public static final int $stable = 0;
        public static final ReleaseMovableGroupAtCurrent INSTANCE = new ReleaseMovableGroupAtCurrent();

        private ReleaseMovableGroupAtCurrent() {
            super(0, 3, 1, null);
        }

        /* JADX INFO: renamed from: getComposition-w8_IdGo, reason: not valid java name */
        public final int m4554getCompositionw8_IdGo() {
            return ObjectParameter.m4547constructorimpl(0);
        }

        /* JADX INFO: renamed from: getParentCompositionContext-w8_IdGo, reason: not valid java name */
        public final int m4555getParentCompositionContextw8_IdGo() {
            return ObjectParameter.m4547constructorimpl(1);
        }

        /* JADX INFO: renamed from: getReference-w8_IdGo, reason: not valid java name */
        public final int m4556getReferencew8_IdGo() {
            return ObjectParameter.m4547constructorimpl(2);
        }

        @Override // androidx.compose.runtime.composer.gapbuffer.changelist.Operation
        /* JADX INFO: renamed from: objectParamName-PtL-UHM */
        public String mo4521objectParamNamePtLUHM(int parameter) {
            return ObjectParameter.m4549equalsimpl0(parameter, ObjectParameter.m4547constructorimpl(0)) ? "composition" : ObjectParameter.m4549equalsimpl0(parameter, ObjectParameter.m4547constructorimpl(1)) ? "parentCompositionContext" : ObjectParameter.m4549equalsimpl0(parameter, ObjectParameter.m4547constructorimpl(2)) ? TypedValues.Custom.S_REFERENCE : super.mo4521objectParamNamePtLUHM(parameter);
        }

        @Override // androidx.compose.runtime.composer.gapbuffer.changelist.Operation
        protected void execute(OperationArgContainer $this$execute, Applier<?> applier, SlotWriter slots, RememberManager rememberManager, OperationErrorContext errorContext) {
            ControlledComposition composition = (ControlledComposition) $this$execute.mo4567getObjectPtLUHM(ObjectParameter.m4547constructorimpl(0));
            MovableContentStateReference reference = (MovableContentStateReference) $this$execute.mo4567getObjectPtLUHM(ObjectParameter.m4547constructorimpl(2));
            CompositionContext parentContext = (CompositionContext) $this$execute.mo4567getObjectPtLUHM(ObjectParameter.m4547constructorimpl(1));
            MovableContentState state = ComposerKt.extractMovableContentAtCurrent(composition, reference, slots, null);
            parentContext.movableContentStateReleased$runtime(reference, state, applier);
        }
    }

    /* JADX INFO: compiled from: Operation.kt */
    @Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\f\u001a\u00020\r2\n\u0010\u000e\u001a\u0006\u0012\u0002\b\u00030\u0005H\u0016¢\u0006\u0004\b\u000f\u0010\u0010J2\u0010\u0011\u001a\u00020\u0012*\u00020\u00132\n\u0010\u0014\u001a\u0006\u0012\u0002\b\u00030\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0014R\u0018\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\u000b\u0010\b¨\u0006\u001c"}, d2 = {"Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation$ApplyChangeList;", "Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation;", "<init>", "()V", "Changes", "Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation$ObjectParameter;", "Landroidx/compose/runtime/composer/gapbuffer/changelist/ChangeList;", "getChanges-w8_IdGo", "()I", "EffectiveNodeIndex", "Landroidx/compose/runtime/internal/IntRef;", "getEffectiveNodeIndex-w8_IdGo", "objectParamName", "", "parameter", "objectParamName-PtL-UHM", "(I)Ljava/lang/String;", "execute", "", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationArgContainer;", "applier", "Landroidx/compose/runtime/Applier;", "slots", "Landroidx/compose/runtime/composer/gapbuffer/SlotWriter;", "rememberManager", "Landroidx/compose/runtime/composer/RememberManager;", "errorContext", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationErrorContext;", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class ApplyChangeList extends Operation {
        public static final int $stable = 0;
        public static final ApplyChangeList INSTANCE = new ApplyChangeList();

        private ApplyChangeList() {
            super(0, 2, 1, null);
        }

        /* JADX INFO: renamed from: getChanges-w8_IdGo, reason: not valid java name */
        public final int m4524getChangesw8_IdGo() {
            return ObjectParameter.m4547constructorimpl(0);
        }

        /* JADX INFO: renamed from: getEffectiveNodeIndex-w8_IdGo, reason: not valid java name */
        public final int m4525getEffectiveNodeIndexw8_IdGo() {
            return ObjectParameter.m4547constructorimpl(1);
        }

        @Override // androidx.compose.runtime.composer.gapbuffer.changelist.Operation
        /* JADX INFO: renamed from: objectParamName-PtL-UHM */
        public String mo4521objectParamNamePtLUHM(int parameter) {
            return ObjectParameter.m4549equalsimpl0(parameter, ObjectParameter.m4547constructorimpl(0)) ? "changes" : ObjectParameter.m4549equalsimpl0(parameter, ObjectParameter.m4547constructorimpl(1)) ? "effectiveNodeIndex" : super.mo4521objectParamNamePtLUHM(parameter);
        }

        @Override // androidx.compose.runtime.composer.gapbuffer.changelist.Operation
        protected void execute(OperationArgContainer $this$execute, Applier<?> applier, SlotWriter slots, RememberManager rememberManager, OperationErrorContext errorContext) {
            OffsetApplier offsetApplier;
            IntRef intRef = (IntRef) $this$execute.mo4567getObjectPtLUHM(ObjectParameter.m4547constructorimpl(1));
            int effectiveNodeIndex = intRef != null ? intRef.getElement() : 0;
            ChangeList changeList = (ChangeList) $this$execute.mo4567getObjectPtLUHM(ObjectParameter.m4547constructorimpl(0));
            if (effectiveNodeIndex > 0) {
                offsetApplier = new OffsetApplier(applier, effectiveNodeIndex);
            } else {
                offsetApplier = applier;
            }
            changeList.executeAndFlushAllPendingChanges(offsetApplier, slots, rememberManager, errorContext != null ? OperationKt.withCurrentStackTrace(errorContext, slots) : null);
        }
    }

    /* JADX INFO: compiled from: Operation.kt */
    @Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0007\u0018\u00002\u00020\u0001BC\b\u0007\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012$\b\u0002\u0010\u0005\u001a\u001e\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0007\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\u0006¢\u0006\u0004\b\u000b\u0010\fJ2\u0010\u001a\u001a\u00020\n*\u00020\u001b2\n\u0010\u001c\u001a\u0006\u0012\u0002\b\u00030\u00072\u0006\u0010\u001d\u001a\u00020\b2\u0006\u0010\u001e\u001a\u00020\t2\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0014J\b\u0010!\u001a\u00020\"H\u0016R-\u0010\u0005\u001a\u001e\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0007\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\u0006¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001d\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00030\u0010¢\u0006\u000e\n\u0000\u0012\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014R%\u0010\u0015\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00170\u00160\u0010¢\u0006\u000e\n\u0000\u0012\u0004\b\u0018\u0010\u0012\u001a\u0004\b\u0019\u0010\u0014¨\u0006#"}, d2 = {"Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation$TestOperation;", "Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation;", "ints", "", "objects", "block", "Lkotlin/Function3;", "Landroidx/compose/runtime/Applier;", "Landroidx/compose/runtime/composer/gapbuffer/SlotWriter;", "Landroidx/compose/runtime/composer/RememberManager;", "", "<init>", "(IILkotlin/jvm/functions/Function3;)V", "getBlock", "()Lkotlin/jvm/functions/Function3;", "intParams", "", "getIntParams$annotations", "()V", "getIntParams", "()Ljava/util/List;", "objParams", "Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation$ObjectParameter;", "", "getObjParams$annotations", "getObjParams", "execute", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationArgContainer;", "applier", "slots", "rememberManager", "errorContext", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationErrorContext;", "toString", "", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class TestOperation extends Operation {
        public static final int $stable = 8;
        private final Function3<Applier<?>, SlotWriter, RememberManager, Unit> block;
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
        public TestOperation(int ints, int objects, Function3<? super Applier<?>, ? super SlotWriter, ? super RememberManager, Unit> function3) {
            super(ints, objects, null);
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
                arrayList2.add(ObjectParameter.m4546boximpl(ObjectParameter.m4547constructorimpl(index)));
            }
            this.objParams = arrayList2;
        }

        public /* synthetic */ TestOperation(int i, int i2, Function3 function3, int i3, DefaultConstructorMarker defaultConstructorMarker) {
            this((i3 & 1) != 0 ? 0 : i, (i3 & 2) != 0 ? 0 : i2, (i3 & 4) != 0 ? new Function3() { // from class: androidx.compose.runtime.composer.gapbuffer.changelist.Operation$TestOperation$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return Unit.INSTANCE;
                }
            } : function3);
        }

        public final Function3<Applier<?>, SlotWriter, RememberManager, Unit> getBlock() {
            return this.block;
        }

        public final List<Integer> getIntParams() {
            return this.intParams;
        }

        public final List<ObjectParameter<Object>> getObjParams() {
            return this.objParams;
        }

        @Override // androidx.compose.runtime.composer.gapbuffer.changelist.Operation
        protected void execute(OperationArgContainer $this$execute, Applier<?> applier, SlotWriter slots, RememberManager rememberManager, OperationErrorContext errorContext) {
            this.block.invoke(applier, slots, rememberManager);
        }

        @Override // androidx.compose.runtime.composer.gapbuffer.changelist.Operation
        public String toString() {
            return "TestOperation(ints = " + getInts() + ", objects = " + getObjects() + ")@" + System_jvmKt.identityHashCode(this);
        }
    }
}
