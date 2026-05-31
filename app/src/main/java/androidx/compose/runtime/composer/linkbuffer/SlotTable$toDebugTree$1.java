package androidx.compose.runtime.composer.linkbuffer;

import androidx.compose.runtime.composer.linkbuffer.SlotTable;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.sequences.SequenceScope;

/* JADX INFO: compiled from: SlotTable.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\f\u0012\b\u0012\u00060\u0003R\u00020\u00040\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlin/sequences/SequenceScope;", "Landroidx/compose/runtime/composer/linkbuffer/SlotTable$DebugGroup;", "Landroidx/compose/runtime/composer/linkbuffer/SlotTable;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.runtime.composer.linkbuffer.SlotTable$toDebugTree$1", f = "SlotTable.kt", i = {0, 0, 0}, l = {585}, m = "invokeSuspend", n = {"$this$sequence", "groups$iv$iv", "current$iv$iv"}, s = {"L$0", "L$2", "I$0"}, v = 1)
final class SlotTable$toDebugTree$1 extends RestrictedSuspendLambda implements Function2<SequenceScope<? super SlotTable.DebugGroup>, Continuation<? super Unit>, Object> {
    int I$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    int label;
    final /* synthetic */ SlotTable this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    SlotTable$toDebugTree$1(SlotTable slotTable, Continuation<? super SlotTable$toDebugTree$1> continuation) {
        super(2, continuation);
        this.this$0 = slotTable;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        SlotTable$toDebugTree$1 slotTable$toDebugTree$1 = new SlotTable$toDebugTree$1(this.this$0, continuation);
        slotTable$toDebugTree$1.L$0 = obj;
        return slotTable$toDebugTree$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(SequenceScope<? super SlotTable.DebugGroup> sequenceScope, Continuation<? super Unit> continuation) {
        return ((SlotTable$toDebugTree$1) create(sequenceScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x007d  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x004f  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:12:0x0068 -> B:13:0x006e). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r13) {
        /*
            r12 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r12.label
            switch(r1) {
                case 0: goto L2b;
                case 1: goto L11;
                default: goto L9;
            }
        L9:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r13.<init>(r0)
            throw r13
        L11:
            r1 = 0
            r2 = 0
            r3 = 0
            int r4 = r12.I$0
            java.lang.Object r5 = r12.L$2
            int[] r5 = (int[]) r5
            java.lang.Object r6 = r12.L$1
            androidx.compose.runtime.composer.linkbuffer.SlotTable r6 = (androidx.compose.runtime.composer.linkbuffer.SlotTable) r6
            java.lang.Object r7 = r12.L$0
            kotlin.sequences.SequenceScope r7 = (kotlin.sequences.SequenceScope) r7
            kotlin.ResultKt.throwOnFailure(r13)
            r8 = r7
            r7 = r6
            r6 = r5
            r5 = r4
            r4 = r12
            goto L6e
        L2b:
            kotlin.ResultKt.throwOnFailure(r13)
            java.lang.Object r1 = r12.L$0
            kotlin.sequences.SequenceScope r1 = (kotlin.sequences.SequenceScope) r1
            androidx.compose.runtime.composer.linkbuffer.SlotTable r2 = r12.this$0
            androidx.compose.runtime.composer.linkbuffer.SlotTable r3 = r12.this$0
            int r3 = r3.getRoot()
            androidx.compose.runtime.composer.linkbuffer.SlotTable r4 = r12.this$0
            r5 = 0
            androidx.compose.runtime.composer.linkbuffer.SlotTableAddressSpace r6 = r2.getAddressSpace()
            r2 = 0
            int[] r6 = r6.getGroups()
            r7 = r3
            r7 = r1
            r1 = r5
            r5 = r6
            r6 = r4
            r4 = r3
            r3 = r12
        L4d:
            if (r4 < 0) goto L7d
            r8 = r4
            r9 = 0
            androidx.compose.runtime.composer.linkbuffer.SlotTable$DebugGroup r10 = new androidx.compose.runtime.composer.linkbuffer.SlotTable$DebugGroup
            r10.<init>(r8)
            r3.L$0 = r7
            r3.L$1 = r6
            r3.L$2 = r5
            r3.I$0 = r4
            r11 = 1
            r3.label = r11
            java.lang.Object r8 = r7.yield(r10, r3)
            if (r8 != r0) goto L68
            return r0
        L68:
            r8 = r7
            r7 = r6
            r6 = r5
            r5 = r4
            r4 = r3
            r3 = r9
        L6e:
            r3 = r5
            r9 = r6
            r10 = 0
            int r11 = r3 + 1
            r3 = r9[r11]
            r5 = r4
            r4 = r3
            r3 = r5
            r5 = r6
            r6 = r7
            r7 = r8
            goto L4d
        L7d:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.composer.linkbuffer.SlotTable$toDebugTree$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
