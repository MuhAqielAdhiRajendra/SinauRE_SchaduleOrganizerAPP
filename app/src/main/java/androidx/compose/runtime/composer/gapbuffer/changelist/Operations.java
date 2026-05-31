package androidx.compose.runtime.composer.gapbuffer.changelist;

import androidx.compose.runtime.Applier;
import androidx.compose.runtime.composer.RememberManager;
import androidx.compose.runtime.composer.gapbuffer.SlotWriter;
import androidx.compose.runtime.composer.gapbuffer.changelist.Operation;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmInline;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: Operations.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\u008c\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\f\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u001c\n\u0002\b\u0003\b\u0001\u0018\u00002\u00020\u0001:\u0002STB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0017\u001a\u00020\u0018J\u0006\u0010\u0019\u001a\u00020\u0018J\u0006\u0010\u001a\u001a\u00020\u001bJ\u0010\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u0006H\u0007J\u0018\u0010\u001e\u001a\u00020\n2\u0006\u0010\u001f\u001a\u00020\n2\u0006\u0010 \u001a\u00020\nH\u0002J\b\u0010!\u001a\u00020\u001bH\u0002J\u0011\u0010\"\u001a\u00020\u001b2\u0006\u0010 \u001a\u00020\nH\u0082\bJ\u0018\u0010#\u001a\u00020\u001b2\u0006\u0010\u001f\u001a\u00020\n2\u0006\u0010 \u001a\u00020\nH\u0002J\u0011\u0010$\u001a\u00020\u001b2\u0006\u0010 \u001a\u00020\nH\u0082\bJ\u0018\u0010%\u001a\u00020\u001b2\u0006\u0010\u001f\u001a\u00020\n2\u0006\u0010 \u001a\u00020\nH\u0002J\u000e\u0010&\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u0006J\u0010\u0010'\u001a\u00020(2\u0006\u0010\u001d\u001a\u00020\u0006H\u0002J7\u0010&\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u00062\u0017\u0010)\u001a\u0013\u0012\u0004\u0012\u00020+\u0012\u0004\u0012\u00020\u001b0*¢\u0006\u0002\b,H\u0086\b\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0001J\u000e\u0010-\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u0006J\u0010\u0010.\u001a\u00020(2\u0006\u0010\u001d\u001a\u00020\u0006H\u0002J\u0011\u0010/\u001a\u00020\n2\u0006\u00100\u001a\u00020\nH\u0082\bJ\u0006\u00101\u001a\u00020\u001bJ\u000e\u00102\u001a\u00020\u001b2\u0006\u00103\u001a\u00020\u0000J&\u00104\u001a\u00020\u001b2\u001b\u00105\u001a\u0017\u0012\b\u0012\u000606R\u00020\u0000\u0012\u0004\u0012\u00020\u001b0*¢\u0006\u0002\b,H\u0086\bJ&\u00107\u001a\u00020\u001b2\u001b\u00108\u001a\u0017\u0012\b\u0012\u000606R\u00020\u0000\u0012\u0004\u0012\u00020\u001b0*¢\u0006\u0002\b,H\u0086\bJ,\u00109\u001a\u00020\u001b2\n\u0010:\u001a\u0006\u0012\u0002\b\u00030;2\u0006\u0010<\u001a\u00020=2\u0006\u0010>\u001a\u00020?2\b\u0010@\u001a\u0004\u0018\u00010AJ\f\u0010B\u001a\u00020(*\u00020(H\u0002J\t\u0010C\u001a\u00020\u0006H\u0082\bJ\u0015\u0010D\u001a\u00020\n2\n\u0010E\u001a\u00060\nj\u0002`FH\u0082\bJ\u001c\u0010G\u001a\u00020\n2\n\u0010E\u001a\u0006\u0012\u0002\b\u00030HH\u0082\b¢\u0006\u0004\bI\u0010JJ\b\u0010K\u001a\u00020(H\u0017J\u0010\u0010L\u001a\u00020(2\u0006\u0010M\u001a\u00020(H\u0016J\u0018\u0010N\u001a\u00020(*\u000606R\u00020\u00002\u0006\u0010M\u001a\u00020(H\u0002J\u0016\u0010O\u001a\u00020(*\u0004\u0018\u00010\u000f2\u0006\u0010M\u001a\u00020(H\u0002J \u0010P\u001a\u00020(\"\u0004\b\u0000\u0010Q*\b\u0012\u0004\u0012\u0002HQ0R2\u0006\u0010M\u001a\u00020(H\u0002R \u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0000@\u0000X\u0081\u000e¢\u0006\n\n\u0002\u0010\b\u0012\u0004\b\u0007\u0010\u0003R\u0012\u0010\t\u001a\u00020\n8\u0000@\u0000X\u0081\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u000b\u001a\u00020\f8\u0000@\u0000X\u0081\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\r\u001a\u00020\n8\u0000@\u0000X\u0081\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u000e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u00058\u0000@\u0000X\u0081\u000e¢\u0006\u0004\n\u0002\u0010\u0010R\u0012\u0010\u0011\u001a\u00020\n8\u0000@\u0000X\u0081\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0014\u001a\u00020\n8F¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016¨\u0006U"}, d2 = {"Landroidx/compose/runtime/composer/gapbuffer/changelist/Operations;", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationsDebugStringFormattable;", "<init>", "()V", "opCodes", "", "Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation;", "getOpCodes$runtime$annotations", "[Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation;", "opCodesSize", "", "intArgs", "", "intArgsSize", "objectArgs", "", "[Ljava/lang/Object;", "objectArgsSize", "pushedIntMask", "pushedObjectMask", "size", "getSize", "()I", "isEmpty", "", "isNotEmpty", "clear", "", "pushOp", "operation", "determineNewSize", "currentSize", "requiredSize", "resizeOpCodes", "ensureIntArgsSizeAtLeast", "resizeIntArgs", "ensureObjectArgsSizeAtLeast", "resizeObjectArgs", "push", "exceptionMessageForOperationPushNoScope", "", "args", "Lkotlin/Function1;", "Landroidx/compose/runtime/composer/gapbuffer/changelist/Operations$WriteScope;", "Lkotlin/ExtensionFunctionType;", "ensureAllArgumentsPushedFor", "exceptionMessageForOperationPushWithScope", "createExpectedArgMask", "paramCount", "pop", "popInto", "other", "drain", "sink", "Landroidx/compose/runtime/composer/gapbuffer/changelist/Operations$OpIterator;", "forEach", "action", "executeAndFlushAllPendingOperations", "applier", "Landroidx/compose/runtime/Applier;", "slots", "Landroidx/compose/runtime/composer/gapbuffer/SlotWriter;", "rememberManager", "Landroidx/compose/runtime/composer/RememberManager;", "errorContext", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationErrorContext;", "indent", "peekOperation", "topIntIndexOf", "parameter", "Landroidx/compose/runtime/composer/gapbuffer/changelist/IntParameter;", "topObjectIndexOf", "Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation$ObjectParameter;", "topObjectIndexOf-PtL-UHM", "(I)I", "toString", "toDebugString", "linePrefix", "currentOpToDebugString", "formatOpArgumentToString", "toCollectionString", "T", "", "WriteScope", "OpIterator", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class Operations extends OperationsDebugStringFormattable {
    public static final int $stable = 8;
    public int intArgsSize;
    public int objectArgsSize;
    public int opCodesSize;
    private int pushedIntMask;
    private int pushedObjectMask;
    public Operation[] opCodes = new Operation[16];
    public int[] intArgs = new int[16];
    public Object[] objectArgs = new Object[16];

    public static /* synthetic */ void getOpCodes$runtime$annotations() {
    }

    /* JADX INFO: renamed from: getSize, reason: from getter */
    public final int getOpCodesSize() {
        return this.opCodesSize;
    }

    public final boolean isEmpty() {
        return getOpCodesSize() == 0;
    }

    public final boolean isNotEmpty() {
        return getOpCodesSize() != 0;
    }

    public final void clear() {
        this.opCodesSize = 0;
        this.intArgsSize = 0;
        ArraysKt.fill(this.objectArgs, (Object) null, 0, this.objectArgsSize);
        this.objectArgsSize = 0;
    }

    public final void pushOp(Operation operation) {
        if (this.opCodesSize == this.opCodes.length) {
            resizeOpCodes();
        }
        int requiredSize$iv = this.intArgsSize + operation.getInts();
        int currentSize$iv = this.intArgs.length;
        if (requiredSize$iv > currentSize$iv) {
            resizeIntArgs(currentSize$iv, requiredSize$iv);
        }
        int requiredSize$iv2 = this.objectArgsSize + operation.getObjects();
        int currentSize$iv2 = this.objectArgs.length;
        if (requiredSize$iv2 > currentSize$iv2) {
            resizeObjectArgs(currentSize$iv2, requiredSize$iv2);
        }
        Operation[] operationArr = this.opCodes;
        int i = this.opCodesSize;
        this.opCodesSize = i + 1;
        operationArr[i] = operation;
        this.intArgsSize += operation.getInts();
        this.objectArgsSize += operation.getObjects();
    }

    private final int determineNewSize(int currentSize, int requiredSize) {
        int resizeAmount = RangesKt.coerceAtMost(currentSize, 1024);
        return RangesKt.coerceAtLeast(currentSize + resizeAmount, requiredSize);
    }

    private final void resizeOpCodes() {
        int resizeAmount = RangesKt.coerceAtMost(this.opCodesSize, 1024);
        Operation[] newOpCodes = new Operation[this.opCodesSize + resizeAmount];
        Object[] $this$fastCopyInto$iv = this.opCodes;
        int endIndex$iv = this.opCodesSize;
        System.arraycopy($this$fastCopyInto$iv, 0, newOpCodes, 0, endIndex$iv - 0);
        this.opCodes = newOpCodes;
    }

    private final void ensureIntArgsSizeAtLeast(int requiredSize) {
        int currentSize = this.intArgs.length;
        if (requiredSize > currentSize) {
            resizeIntArgs(currentSize, requiredSize);
        }
    }

    private final void resizeIntArgs(int currentSize, int requiredSize) {
        int[] newIntArgs = new int[determineNewSize(currentSize, requiredSize)];
        ArraysKt.copyInto(this.intArgs, newIntArgs, 0, 0, currentSize);
        this.intArgs = newIntArgs;
    }

    private final void ensureObjectArgsSizeAtLeast(int requiredSize) {
        int currentSize = this.objectArgs.length;
        if (requiredSize > currentSize) {
            resizeObjectArgs(currentSize, requiredSize);
        }
    }

    private final void resizeObjectArgs(int currentSize, int requiredSize) {
        Object[] newObjectArgs = new Object[determineNewSize(currentSize, requiredSize)];
        Object[] $this$fastCopyInto$iv = this.objectArgs;
        System.arraycopy($this$fastCopyInto$iv, 0, newObjectArgs, 0, currentSize - 0);
        this.objectArgs = newObjectArgs;
    }

    public final void push(Operation operation) {
        pushOp(operation);
    }

    private final String exceptionMessageForOperationPushNoScope(Operation operation) {
        return "Cannot push " + operation + " without arguments because it expects " + operation.getInts() + " ints and " + operation.getObjects() + " objects.";
    }

    public final void push(Operation operation, Function1<? super WriteScope, Unit> args) {
        pushOp(operation);
        args.invoke(WriteScope.m4569boximpl(WriteScope.m4570constructorimpl(this)));
        ensureAllArgumentsPushedFor(operation);
    }

    public final void ensureAllArgumentsPushedFor(Operation operation) {
        int i = this.pushedIntMask;
        int paramCount$iv = operation.getInts();
        if (i == ((paramCount$iv == 0 ? 0 : -1) >>> (32 - paramCount$iv))) {
            int i2 = this.pushedObjectMask;
            int paramCount$iv2 = operation.getObjects();
            if (i2 == ((paramCount$iv2 == 0 ? 0 : -1) >>> (32 - paramCount$iv2))) {
            }
        }
    }

    private final String exceptionMessageForOperationPushWithScope(Operation operation) {
        int missingIntCount = 0;
        StringBuilder $this$exceptionMessageForOperationPushWithScope_u24lambda_u240 = new StringBuilder();
        int ints = operation.getInts();
        for (int i = 0; i < ints; i++) {
            int arg = i;
            if (((1 << arg) & this.pushedIntMask) == 0) {
                if (missingIntCount > 0) {
                    $this$exceptionMessageForOperationPushWithScope_u24lambda_u240.append(", ");
                }
                $this$exceptionMessageForOperationPushWithScope_u24lambda_u240.append(operation.intParamName(arg));
                missingIntCount++;
            }
        }
        String missingInts = $this$exceptionMessageForOperationPushWithScope_u24lambda_u240.toString();
        int missingObjectCount = 0;
        StringBuilder $this$exceptionMessageForOperationPushWithScope_u24lambda_u241 = new StringBuilder();
        int objects = operation.getObjects();
        for (int i2 = 0; i2 < objects; i2++) {
            int arg2 = i2;
            if (((1 << arg2) & this.pushedObjectMask) == 0) {
                if (missingIntCount > 0) {
                    $this$exceptionMessageForOperationPushWithScope_u24lambda_u241.append(", ");
                }
                $this$exceptionMessageForOperationPushWithScope_u24lambda_u241.append(operation.mo4521objectParamNamePtLUHM(Operation.ObjectParameter.m4547constructorimpl(arg2)));
                missingObjectCount++;
            }
        }
        String missingObjects = $this$exceptionMessageForOperationPushWithScope_u24lambda_u241.toString();
        return "Error while pushing " + operation + ". Not all arguments were provided. Missing " + missingIntCount + " int arguments (" + missingInts + ") and " + missingObjectCount + " object arguments (" + missingObjects + ").";
    }

    private final int createExpectedArgMask(int paramCount) {
        return (paramCount == 0 ? 0 : -1) >>> (32 - paramCount);
    }

    public final void pop() {
        Operation[] opCodes = this.opCodes;
        this.opCodesSize--;
        Operation op = opCodes[this.opCodesSize];
        opCodes[this.opCodesSize] = null;
        int objects = op.getObjects();
        for (int i = 0; i < objects; i++) {
            this.objectArgsSize--;
            this.objectArgs[this.objectArgsSize] = null;
        }
        this.intArgsSize -= op.getInts();
    }

    public final void popInto(Operations other) {
        Operation[] opCodes = this.opCodes;
        this.opCodesSize--;
        Operation op = opCodes[this.opCodesSize];
        opCodes[this.opCodesSize] = null;
        other.pushOp(op);
        Object[] $this$fastCopyInto$iv = this.objectArgs;
        Object[] destination$iv = other.objectArgs;
        int destinationOffset$iv = other.objectArgsSize - op.getObjects();
        int startIndex$iv = this.objectArgsSize - op.getObjects();
        int endIndex$iv = this.objectArgsSize;
        System.arraycopy($this$fastCopyInto$iv, startIndex$iv, destination$iv, destinationOffset$iv, endIndex$iv - startIndex$iv);
        Object[] $this$fastCopyInto$iv2 = this.objectArgs;
        ArraysKt.fill($this$fastCopyInto$iv2, (Object) null, this.objectArgsSize - op.getObjects(), this.objectArgsSize);
        ArraysKt.copyInto(this.intArgs, other.intArgs, other.intArgsSize - op.getInts(), this.intArgsSize - op.getInts(), this.intArgsSize);
        this.objectArgsSize -= op.getObjects();
        this.intArgsSize -= op.getInts();
    }

    public final void drain(Function1<? super OpIterator, Unit> sink) {
        if (isNotEmpty()) {
            OpIterator iterator$iv = new OpIterator();
            do {
                sink.invoke(iterator$iv);
            } while (iterator$iv.next());
        }
        clear();
    }

    public final void forEach(Function1<? super OpIterator, Unit> action) {
        if (isNotEmpty()) {
            OpIterator iterator = new OpIterator();
            do {
                action.invoke(iterator);
            } while (iterator.next());
        }
    }

    public final void executeAndFlushAllPendingOperations(Applier<?> applier, SlotWriter slots, RememberManager rememberManager, OperationErrorContext errorContext) {
        if (isNotEmpty()) {
            OpIterator iterator$iv$iv = new OpIterator();
            do {
                Operation $this$executeAndFlushAllPendingOperations_u24lambda_u240_u240 = iterator$iv$iv.getOperation();
                $this$executeAndFlushAllPendingOperations_u24lambda_u240_u240.executeWithComposeStackTrace(iterator$iv$iv, applier, slots, rememberManager, errorContext);
            } while (iterator$iv$iv.next());
        }
        clear();
    }

    private final String indent(String $this$indent) {
        return $this$indent + "    ";
    }

    private final Operation peekOperation() {
        return this.opCodes[this.opCodesSize - 1];
    }

    private final int topIntIndexOf(int parameter) {
        return (this.intArgsSize - this.opCodes[this.opCodesSize - 1].getInts()) + parameter;
    }

    /* JADX INFO: renamed from: topObjectIndexOf-PtL-UHM, reason: not valid java name */
    private final int m4568topObjectIndexOfPtLUHM(int parameter) {
        return (this.objectArgsSize - this.opCodes[this.opCodesSize - 1].getObjects()) + parameter;
    }

    /* JADX INFO: compiled from: Operations.kt */
    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0087@\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J$\u0010\n\u001a\u00020\u000b2\n\u0010\f\u001a\u00060\rj\u0002`\u000e2\u0006\u0010\u000f\u001a\u00020\rH\u0086\b¢\u0006\u0004\b\u0010\u0010\u0011J8\u0010\u0012\u001a\u00020\u000b2\n\u0010\u0013\u001a\u00060\rj\u0002`\u000e2\u0006\u0010\u0014\u001a\u00020\r2\n\u0010\u0015\u001a\u00060\rj\u0002`\u000e2\u0006\u0010\u0016\u001a\u00020\rH\u0086\b¢\u0006\u0004\b\u0017\u0010\u0018JL\u0010\u0012\u001a\u00020\u000b2\n\u0010\u0013\u001a\u00060\rj\u0002`\u000e2\u0006\u0010\u0014\u001a\u00020\r2\n\u0010\u0015\u001a\u00060\rj\u0002`\u000e2\u0006\u0010\u0016\u001a\u00020\r2\n\u0010\u0019\u001a\u00060\rj\u0002`\u000e2\u0006\u0010\u001a\u001a\u00020\rH\u0086\b¢\u0006\u0004\b\u0017\u0010\u001bJ)\u0010\u001c\u001a\u00020\u000b\"\u0004\b\u0000\u0010\u001d2\f\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u001d0\u001e2\u0006\u0010\u000f\u001a\u0002H\u001d¢\u0006\u0004\b\u001f\u0010 JE\u0010!\u001a\u00020\u000b\"\u0004\b\u0000\u0010\u001d\"\u0004\b\u0001\u0010\"2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u001d0\u001e2\u0006\u0010\u0014\u001a\u0002H\u001d2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\"0\u001e2\u0006\u0010\u0016\u001a\u0002H\"¢\u0006\u0004\b#\u0010$Ja\u0010!\u001a\u00020\u000b\"\u0004\b\u0000\u0010\u001d\"\u0004\b\u0001\u0010\"\"\u0004\b\u0002\u0010%2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u001d0\u001e2\u0006\u0010\u0014\u001a\u0002H\u001d2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\"0\u001e2\u0006\u0010\u0016\u001a\u0002H\"2\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H%0\u001e2\u0006\u0010\u001a\u001a\u0002H%¢\u0006\u0004\b&\u0010'J}\u0010!\u001a\u00020\u000b\"\u0004\b\u0000\u0010\u001d\"\u0004\b\u0001\u0010\"\"\u0004\b\u0002\u0010%\"\u0004\b\u0003\u0010(2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u001d0\u001e2\u0006\u0010\u0014\u001a\u0002H\u001d2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\"0\u001e2\u0006\u0010\u0016\u001a\u0002H\"2\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H%0\u001e2\u0006\u0010\u001a\u001a\u0002H%2\f\u0010)\u001a\b\u0012\u0004\u0012\u0002H(0\u001e2\u0006\u0010*\u001a\u0002H(¢\u0006\u0004\b+\u0010,J\u0014\u0010-\u001a\u00020.2\b\u0010/\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u00100\u001a\u00020\rHÖ\u0081\u0004J\n\u00101\u001a\u000202HÖ\u0081\u0004R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\b\u0010\t\u0088\u0001\u0002\u0092\u0001\u00020\u0003¨\u00063"}, d2 = {"Landroidx/compose/runtime/composer/gapbuffer/changelist/Operations$WriteScope;", "", "stack", "Landroidx/compose/runtime/composer/gapbuffer/changelist/Operations;", "constructor-impl", "(Landroidx/compose/runtime/composer/gapbuffer/changelist/Operations;)Landroidx/compose/runtime/composer/gapbuffer/changelist/Operations;", "operation", "Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation;", "getOperation-impl", "(Landroidx/compose/runtime/composer/gapbuffer/changelist/Operations;)Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation;", "setInt", "", "parameter", "", "Landroidx/compose/runtime/composer/gapbuffer/changelist/IntParameter;", "value", "setInt-impl", "(Landroidx/compose/runtime/composer/gapbuffer/changelist/Operations;II)V", "setInts", "parameter1", "value1", "parameter2", "value2", "setInts-impl", "(Landroidx/compose/runtime/composer/gapbuffer/changelist/Operations;IIII)V", "parameter3", "value3", "(Landroidx/compose/runtime/composer/gapbuffer/changelist/Operations;IIIIII)V", "setObject", "T", "Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation$ObjectParameter;", "setObject-sGr0YRc", "(Landroidx/compose/runtime/composer/gapbuffer/changelist/Operations;ILjava/lang/Object;)V", "setObjects", "U", "setObjects-EsEZvaA", "(Landroidx/compose/runtime/composer/gapbuffer/changelist/Operations;ILjava/lang/Object;ILjava/lang/Object;)V", "V", "setObjects-JOGOPjs", "(Landroidx/compose/runtime/composer/gapbuffer/changelist/Operations;ILjava/lang/Object;ILjava/lang/Object;ILjava/lang/Object;)V", "W", "parameter4", "value4", "setObjects-fiWQlIY", "(Landroidx/compose/runtime/composer/gapbuffer/changelist/Operations;ILjava/lang/Object;ILjava/lang/Object;ILjava/lang/Object;ILjava/lang/Object;)V", "equals", "", "other", "hashCode", "toString", "", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    @JvmInline
    public static final class WriteScope {
        private final Operations stack;

        /* JADX INFO: renamed from: box-impl, reason: not valid java name */
        public static final /* synthetic */ WriteScope m4569boximpl(Operations operations) {
            return new WriteScope(operations);
        }

        /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
        public static Operations m4570constructorimpl(Operations operations) {
            return operations;
        }

        /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
        public static boolean m4571equalsimpl(Operations operations, Object obj) {
            return (obj instanceof WriteScope) && Intrinsics.areEqual(operations, ((WriteScope) obj).getStack());
        }

        /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
        public static final boolean m4572equalsimpl0(Operations operations, Operations operations2) {
            return Intrinsics.areEqual(operations, operations2);
        }

        /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
        public static int m4574hashCodeimpl(Operations operations) {
            return operations.hashCode();
        }

        /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
        public static String m4582toStringimpl(Operations operations) {
            return "WriteScope(stack=" + operations + ')';
        }

        public boolean equals(Object other) {
            return m4571equalsimpl(this.stack, other);
        }

        public int hashCode() {
            return m4574hashCodeimpl(this.stack);
        }

        public String toString() {
            return m4582toStringimpl(this.stack);
        }

        /* JADX INFO: renamed from: unbox-impl, reason: not valid java name and from getter */
        public final /* synthetic */ Operations getStack() {
            return this.stack;
        }

        private /* synthetic */ WriteScope(Operations stack) {
            this.stack = stack;
        }

        /* JADX INFO: renamed from: getOperation-impl, reason: not valid java name */
        public static final Operation m4573getOperationimpl(Operations arg0) {
            return arg0.opCodes[arg0.opCodesSize - 1];
        }

        /* JADX INFO: renamed from: setInt-impl, reason: not valid java name */
        public static final void m4575setIntimpl(Operations arg0, int parameter, int value) {
            arg0.intArgs[(arg0.intArgsSize - arg0.opCodes[arg0.opCodesSize - 1].getInts()) + parameter] = value;
        }

        /* JADX INFO: renamed from: setInts-impl, reason: not valid java name */
        public static final void m4576setIntsimpl(Operations arg0, int parameter1, int value1, int parameter2, int value2) {
            int base = arg0.intArgsSize - arg0.opCodes[arg0.opCodesSize - 1].getInts();
            int[] intArgs = arg0.intArgs;
            intArgs[base + parameter1] = value1;
            intArgs[base + parameter2] = value2;
        }

        /* JADX INFO: renamed from: setInts-impl, reason: not valid java name */
        public static final void m4577setIntsimpl(Operations arg0, int parameter1, int value1, int parameter2, int value2, int parameter3, int value3) {
            int base = arg0.intArgsSize - arg0.opCodes[arg0.opCodesSize - 1].getInts();
            int[] intArgs = arg0.intArgs;
            intArgs[base + parameter1] = value1;
            intArgs[base + parameter2] = value2;
            intArgs[base + parameter3] = value3;
        }

        /* JADX INFO: renamed from: setObject-sGr0YRc, reason: not valid java name */
        public static final <T> void m4578setObjectsGr0YRc(Operations arg0, int parameter, T t) {
            arg0.objectArgs[(arg0.objectArgsSize - arg0.opCodes[arg0.opCodesSize - 1].getObjects()) + parameter] = t;
        }

        /* JADX INFO: renamed from: setObjects-EsEZvaA, reason: not valid java name */
        public static final <T, U> void m4579setObjectsEsEZvaA(Operations arg0, int parameter1, T t, int parameter2, U u) {
            int base = arg0.objectArgsSize - arg0.opCodes[arg0.opCodesSize - 1].getObjects();
            Object[] objectArgs = arg0.objectArgs;
            objectArgs[base + parameter1] = t;
            objectArgs[base + parameter2] = u;
        }

        /* JADX INFO: renamed from: setObjects-JOGOPjs, reason: not valid java name */
        public static final <T, U, V> void m4580setObjectsJOGOPjs(Operations arg0, int parameter1, T t, int parameter2, U u, int parameter3, V v) {
            int base = arg0.objectArgsSize - arg0.opCodes[arg0.opCodesSize - 1].getObjects();
            Object[] objectArgs = arg0.objectArgs;
            objectArgs[base + parameter1] = t;
            objectArgs[base + parameter2] = u;
            objectArgs[base + parameter3] = v;
        }

        /* JADX INFO: renamed from: setObjects-fiWQlIY, reason: not valid java name */
        public static final <T, U, V, W> void m4581setObjectsfiWQlIY(Operations arg0, int parameter1, T t, int parameter2, U u, int parameter3, V v, int parameter4, W w) {
            int base = arg0.objectArgsSize - arg0.opCodes[arg0.opCodesSize - 1].getObjects();
            Object[] objectArgs = arg0.objectArgs;
            objectArgs[base + parameter1] = t;
            objectArgs[base + parameter2] = u;
            objectArgs[base + parameter3] = v;
            objectArgs[base + parameter4] = w;
        }
    }

    /* JADX INFO: compiled from: Operations.kt */
    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\b\u001a\u00020\tJ\u0014\u0010\u000e\u001a\u00020\u00052\n\u0010\u000f\u001a\u00060\u0005j\u0002`\u0010H\u0016J#\u0010\u0011\u001a\u0002H\u0012\"\u0004\b\u0000\u0010\u00122\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u00120\u0013H\u0016¢\u0006\u0004\b\u0014\u0010\u0015J\u0006\u0010\u0016\u001a\u00020\u0017R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\n\u001a\u00020\u000b8F¢\u0006\u0006\u001a\u0004\b\f\u0010\r¨\u0006\u0018"}, d2 = {"Landroidx/compose/runtime/composer/gapbuffer/changelist/Operations$OpIterator;", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationArgContainer;", "<init>", "(Landroidx/compose/runtime/composer/gapbuffer/changelist/Operations;)V", "opIdx", "", "intIdx", "objIdx", "next", "", "operation", "Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation;", "getOperation", "()Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation;", "getInt", "parameter", "Landroidx/compose/runtime/composer/gapbuffer/changelist/IntParameter;", "getObject", "T", "Landroidx/compose/runtime/composer/gapbuffer/changelist/Operation$ObjectParameter;", "getObject-PtL-UHM", "(I)Ljava/lang/Object;", "currentOperationDebugString", "", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public final class OpIterator implements OperationArgContainer {
        private int intIdx;
        private int objIdx;
        private int opIdx;

        public OpIterator() {
        }

        public final boolean next() {
            if (this.opIdx >= Operations.this.opCodesSize) {
                return false;
            }
            Operation op = getOperation();
            this.intIdx += op.getInts();
            this.objIdx += op.getObjects();
            this.opIdx++;
            return this.opIdx < Operations.this.opCodesSize;
        }

        public final Operation getOperation() {
            return Operations.this.opCodes[this.opIdx];
        }

        @Override // androidx.compose.runtime.composer.gapbuffer.changelist.OperationArgContainer
        public int getInt(int parameter) {
            return Operations.this.intArgs[this.intIdx + parameter];
        }

        @Override // androidx.compose.runtime.composer.gapbuffer.changelist.OperationArgContainer
        /* JADX INFO: renamed from: getObject-PtL-UHM */
        public <T> T mo4567getObjectPtLUHM(int parameter) {
            return (T) Operations.this.objectArgs[this.objIdx + parameter];
        }

        public final String currentOperationDebugString() {
            Operations operations = Operations.this;
            StringBuilder $this$currentOperationDebugString_u24lambda_u240 = new StringBuilder();
            $this$currentOperationDebugString_u24lambda_u240.append("operation[");
            $this$currentOperationDebugString_u24lambda_u240.append(this.opIdx);
            $this$currentOperationDebugString_u24lambda_u240.append("] = ");
            $this$currentOperationDebugString_u24lambda_u240.append(operations.currentOpToDebugString(this, ""));
            return $this$currentOperationDebugString_u24lambda_u240.toString();
        }
    }

    @Deprecated(message = "toString() will return the default implementation from Any. Did you mean to use toDebugString()?", replaceWith = @ReplaceWith(expression = "toDebugString()", imports = {}))
    public String toString() {
        return super.toString();
    }

    @Override // androidx.compose.runtime.composer.gapbuffer.changelist.OperationsDebugStringFormattable
    public String toDebugString(String linePrefix) {
        StringBuilder $this$toDebugString_u24lambda_u240 = new StringBuilder();
        int opNumber = 0;
        if (isNotEmpty()) {
            OpIterator iterator$iv = new OpIterator();
            while (true) {
                $this$toDebugString_u24lambda_u240.append(linePrefix);
                int opNumber2 = opNumber + 1;
                $this$toDebugString_u24lambda_u240.append(opNumber);
                $this$toDebugString_u24lambda_u240.append(". ");
                $this$toDebugString_u24lambda_u240.append(currentOpToDebugString(iterator$iv, linePrefix)).append('\n');
                if (!iterator$iv.next()) {
                    break;
                }
                opNumber = opNumber2;
            }
        }
        return $this$toDebugString_u24lambda_u240.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String currentOpToDebugString(OpIterator $this$currentOpToDebugString, String linePrefix) {
        Operation operation = $this$currentOpToDebugString.getOperation();
        if (operation.getInts() == 0 && operation.getObjects() == 0) {
            return operation.getName();
        }
        StringBuilder $this$currentOpToDebugString_u24lambda_u240 = new StringBuilder();
        $this$currentOpToDebugString_u24lambda_u240.append(operation.getName());
        $this$currentOpToDebugString_u24lambda_u240.append('(');
        boolean isFirstParam = true;
        String argLinePrefix = indent(linePrefix);
        int ints = operation.getInts();
        for (int i = 0; i < ints; i++) {
            int offset = i;
            String name = operation.intParamName(offset);
            if (isFirstParam) {
                isFirstParam = false;
            } else {
                $this$currentOpToDebugString_u24lambda_u240.append(", ");
            }
            $this$currentOpToDebugString_u24lambda_u240.append('\n');
            $this$currentOpToDebugString_u24lambda_u240.append(argLinePrefix);
            $this$currentOpToDebugString_u24lambda_u240.append(name);
            $this$currentOpToDebugString_u24lambda_u240.append(" = ");
            $this$currentOpToDebugString_u24lambda_u240.append($this$currentOpToDebugString.getInt(offset));
        }
        int objects = operation.getObjects();
        int offset2 = 0;
        while (offset2 < objects) {
            int param = Operation.ObjectParameter.m4547constructorimpl(offset2);
            StringBuilder sb = $this$currentOpToDebugString_u24lambda_u240;
            String name2 = operation.mo4521objectParamNamePtLUHM(param);
            if (isFirstParam) {
                isFirstParam = false;
            } else {
                $this$currentOpToDebugString_u24lambda_u240.append(", ");
            }
            $this$currentOpToDebugString_u24lambda_u240.append('\n');
            $this$currentOpToDebugString_u24lambda_u240.append(argLinePrefix);
            $this$currentOpToDebugString_u24lambda_u240.append(name2);
            $this$currentOpToDebugString_u24lambda_u240.append(" = ");
            $this$currentOpToDebugString_u24lambda_u240.append(formatOpArgumentToString($this$currentOpToDebugString.mo4567getObjectPtLUHM(param), argLinePrefix));
            offset2++;
            $this$currentOpToDebugString_u24lambda_u240 = sb;
            operation = operation;
        }
        $this$currentOpToDebugString_u24lambda_u240.append('\n');
        $this$currentOpToDebugString_u24lambda_u240.append(linePrefix);
        $this$currentOpToDebugString_u24lambda_u240.append(")");
        return $this$currentOpToDebugString_u24lambda_u240.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String formatOpArgumentToString(Object $this$formatOpArgumentToString, String linePrefix) {
        return $this$formatOpArgumentToString == null ? "null" : $this$formatOpArgumentToString instanceof Object[] ? toCollectionString(ArraysKt.asIterable((Object[]) $this$formatOpArgumentToString), linePrefix) : $this$formatOpArgumentToString instanceof int[] ? toCollectionString(ArraysKt.asIterable((int[]) $this$formatOpArgumentToString), linePrefix) : $this$formatOpArgumentToString instanceof long[] ? toCollectionString(ArraysKt.asIterable((long[]) $this$formatOpArgumentToString), linePrefix) : $this$formatOpArgumentToString instanceof float[] ? toCollectionString(ArraysKt.asIterable((float[]) $this$formatOpArgumentToString), linePrefix) : $this$formatOpArgumentToString instanceof double[] ? toCollectionString(ArraysKt.asIterable((double[]) $this$formatOpArgumentToString), linePrefix) : $this$formatOpArgumentToString instanceof Iterable ? toCollectionString((Iterable) $this$formatOpArgumentToString, linePrefix) : $this$formatOpArgumentToString instanceof OperationsDebugStringFormattable ? ((OperationsDebugStringFormattable) $this$formatOpArgumentToString).toDebugString(linePrefix) : $this$formatOpArgumentToString.toString();
    }

    private final <T> String toCollectionString(Iterable<? extends T> iterable, final String linePrefix) {
        return CollectionsKt.joinToString$default(iterable, ", ", "[", "]", 0, null, new Function1() { // from class: androidx.compose.runtime.composer.gapbuffer.changelist.Operations$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return this.f$0.formatOpArgumentToString(obj, linePrefix);
            }
        }, 24, null);
    }
}
