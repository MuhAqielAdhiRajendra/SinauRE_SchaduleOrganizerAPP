package androidx.compose.foundation.text.input.internal;

import androidx.compose.foundation.text.input.TextFieldBuffer;
import androidx.compose.runtime.collection.MutableVector;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: ImeEditCommand.android.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0001\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\u0010\u0010\u000eJ\b\u0010\u0019\u001a\u00020\u001aH\u0016J!\u0010\u001b\u001a\u00020\u00172\u0017\u0010\u001c\u001a\u0013\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00170\u0016¢\u0006\u0002\b\u0018H\u0016J\b\u0010\u001d\u001a\u00020\u001aH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\u00020\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R%\u0010\u0014\u001a\u0019\u0012\u0015\u0012\u0013\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00170\u0016¢\u0006\u0002\b\u00180\u0015X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Landroidx/compose/foundation/text/input/internal/DefaultImeEditCommandScope;", "Landroidx/compose/foundation/text/input/internal/ImeEditCommandScope;", "transformedTextFieldState", "Landroidx/compose/foundation/text/input/internal/TransformedTextFieldState;", "<init>", "(Landroidx/compose/foundation/text/input/internal/TransformedTextFieldState;)V", "batchEditTextFieldBuffer", "Landroidx/compose/foundation/text/input/TextFieldBuffer;", "batchDepth", "", "mapFromTransformed", "Landroidx/compose/ui/text/TextRange;", "range", "mapFromTransformed-GEjPoXI", "(J)J", "mapToTransformed", "mapToTransformed-GEjPoXI", "transformedLength", "getTransformedLength", "()I", "editCommands", "Landroidx/compose/runtime/collection/MutableVector;", "Lkotlin/Function1;", "", "Lkotlin/ExtensionFunctionType;", "beginBatchEdit", "", "edit", "block", "endBatchEdit", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class DefaultImeEditCommandScope implements ImeEditCommandScope {
    public static final int $stable = 8;
    private int batchDepth;
    private TextFieldBuffer batchEditTextFieldBuffer;
    private final MutableVector<Function1<TextFieldBuffer, Unit>> editCommands = new MutableVector<>(new Function1[16], 0);
    private final TransformedTextFieldState transformedTextFieldState;

    public DefaultImeEditCommandScope(TransformedTextFieldState transformedTextFieldState) {
        this.transformedTextFieldState = transformedTextFieldState;
    }

    @Override // androidx.compose.foundation.text.input.internal.ImeEditCommandScope
    /* JADX INFO: renamed from: mapFromTransformed-GEjPoXI */
    public long mo1749mapFromTransformedGEjPoXI(long range) {
        if (this.transformedTextFieldState.isTransformed()) {
            return this.transformedTextFieldState.m1893mapFromTransformedGEjPoXI(range);
        }
        return range;
    }

    @Override // androidx.compose.foundation.text.input.internal.ImeEditCommandScope
    /* JADX INFO: renamed from: mapToTransformed-GEjPoXI */
    public long mo1750mapToTransformedGEjPoXI(long range) {
        if (this.transformedTextFieldState.isTransformed()) {
            return this.transformedTextFieldState.m1895mapToTransformedGEjPoXI(range);
        }
        return range;
    }

    @Override // androidx.compose.foundation.text.input.internal.ImeEditCommandScope
    public int getTransformedLength() {
        TextFieldBuffer textFieldBuffer = this.batchEditTextFieldBuffer;
        return textFieldBuffer != null ? textFieldBuffer.getLength() : this.transformedTextFieldState.getVisualText().length();
    }

    @Override // androidx.compose.foundation.text.input.internal.ImeEditCommandScope
    public boolean beginBatchEdit() {
        this.batchDepth++;
        return true;
    }

    @Override // androidx.compose.foundation.text.input.internal.ImeEditCommandScope
    public void edit(Function1<? super TextFieldBuffer, Unit> block) {
        beginBatchEdit();
        this.editCommands.add(block);
        endBatchEdit();
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0089  */
    @Override // androidx.compose.foundation.text.input.internal.ImeEditCommandScope
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean endBatchEdit() {
        /*
            r20 = this;
            r0 = r20
            int r1 = r0.batchDepth
            int r1 = r1 + (-1)
            r0.batchDepth = r1
            int r1 = r0.batchDepth
            r2 = 0
            if (r1 != 0) goto L89
            androidx.compose.runtime.collection.MutableVector<kotlin.jvm.functions.Function1<androidx.compose.foundation.text.input.TextFieldBuffer, kotlin.Unit>> r1 = r0.editCommands
            r4 = 0
            int r5 = r1.getSize()
            if (r5 == 0) goto L18
            r1 = 1
            goto L19
        L18:
            r1 = r2
        L19:
            if (r1 == 0) goto L89
            androidx.compose.foundation.text.input.internal.TransformedTextFieldState r1 = r0.transformedTextFieldState
            r4 = r2
            r5 = 0
            androidx.compose.foundation.text.input.TextFieldState r6 = androidx.compose.foundation.text.input.internal.TransformedTextFieldState.access$getTextFieldState$p(r1)
            androidx.compose.foundation.text.input.InputTransformation r7 = androidx.compose.foundation.text.input.internal.TransformedTextFieldState.access$getInputTransformation$p(r1)
            r8 = r4
            androidx.compose.foundation.text.input.internal.undo.TextFieldEditUndoBehavior r9 = androidx.compose.foundation.text.input.internal.undo.TextFieldEditUndoBehavior.MergeIfPossible
            r10 = 0
            androidx.compose.foundation.text.input.TextFieldBuffer r11 = r6.getMainBuffer()
            androidx.compose.foundation.text.input.internal.ChangeTracker r11 = r11.getChangeTracker$foundation()
            r11.clearChanges()
            androidx.compose.foundation.text.input.TextFieldBuffer r11 = r6.getMainBuffer()
            r12 = 0
            r13 = r11
            r14 = 0
            androidx.compose.foundation.text.input.internal.TransformedTextFieldState r15 = r0.transformedTextFieldState
            boolean r15 = r15.isTransformed()
            if (r15 != 0) goto L4a
            r0.batchEditTextFieldBuffer = r13
        L4a:
            androidx.compose.runtime.collection.MutableVector<kotlin.jvm.functions.Function1<androidx.compose.foundation.text.input.TextFieldBuffer, kotlin.Unit>> r15 = r0.editCommands
            r16 = 0
            r17 = 0
            T[] r2 = r15.content
            int r3 = r15.getSize()
            r18 = r2
            r2 = r17
        L5a:
            if (r2 >= r3) goto L6d
            r17 = r18[r2]
            r19 = r2
            r2 = r17
            kotlin.jvm.functions.Function1 r2 = (kotlin.jvm.functions.Function1) r2
            r17 = 0
            r2.invoke(r13)
            int r2 = r19 + 1
            goto L5a
        L6d:
            r19 = r2
            androidx.compose.foundation.text.input.internal.TransformedTextFieldState.access$updateWedgeAffinity(r1, r11)
            androidx.compose.foundation.text.input.TextFieldState.access$commitEditAsUser(r6, r7, r8, r9)
            r2 = 1
            androidx.compose.foundation.text.input.TextFieldState.access$setUserCommit(r6, r2)
            androidx.compose.runtime.collection.MutableVector<kotlin.jvm.functions.Function1<androidx.compose.foundation.text.input.TextFieldBuffer, kotlin.Unit>> r1 = r0.editCommands
            r1.clear()
            goto L8a
        L89:
            r2 = 1
        L8a:
            int r1 = r0.batchDepth
            if (r1 <= 0) goto L8f
            goto L90
        L8f:
            r2 = 0
        L90:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.input.internal.DefaultImeEditCommandScope.endBatchEdit():boolean");
    }
}
