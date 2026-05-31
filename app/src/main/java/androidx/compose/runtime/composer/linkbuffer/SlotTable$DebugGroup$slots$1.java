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
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\u0010\u0000\u001a\u00020\u0001*\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlin/sequences/SequenceScope;", ""}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.runtime.composer.linkbuffer.SlotTable$DebugGroup$slots$1", f = "SlotTable.kt", i = {0, 0}, l = {600}, m = "invokeSuspend", n = {"$this$sequence", "address"}, s = {"L$0", "I$0"}, v = 1)
final class SlotTable$DebugGroup$slots$1 extends RestrictedSuspendLambda implements Function2<SequenceScope<? super Object>, Continuation<? super Unit>, Object> {
    int I$0;
    int I$1;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ SlotTable.DebugGroup this$0;
    final /* synthetic */ SlotTable this$1;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    SlotTable$DebugGroup$slots$1(SlotTable.DebugGroup debugGroup, SlotTable slotTable, Continuation<? super SlotTable$DebugGroup$slots$1> continuation) {
        super(2, continuation);
        this.this$0 = debugGroup;
        this.this$1 = slotTable;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        SlotTable$DebugGroup$slots$1 slotTable$DebugGroup$slots$1 = new SlotTable$DebugGroup$slots$1(this.this$0, this.this$1, continuation);
        slotTable$DebugGroup$slots$1.L$0 = obj;
        return slotTable$DebugGroup$slots$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(SequenceScope<? super Object> sequenceScope, Continuation<? super Unit> continuation) {
        return invoke2((SequenceScope<Object>) sequenceScope, continuation);
    }

    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(SequenceScope<Object> sequenceScope, Continuation<? super Unit> continuation) {
        return ((SlotTable$DebugGroup$slots$1) create(sequenceScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0048  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0064  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:11:0x005f -> B:13:0x0062). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r9) {
        /*
            r8 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r8.label
            r2 = 1
            switch(r1) {
                case 0: goto L1f;
                case 1: goto L12;
                default: goto La;
            }
        La:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r0)
            throw r9
        L12:
            int r1 = r8.I$1
            int r3 = r8.I$0
            java.lang.Object r4 = r8.L$0
            kotlin.sequences.SequenceScope r4 = (kotlin.sequences.SequenceScope) r4
            kotlin.ResultKt.throwOnFailure(r9)
            r5 = r8
            goto L62
        L1f:
            kotlin.ResultKt.throwOnFailure(r9)
            java.lang.Object r1 = r8.L$0
            kotlin.sequences.SequenceScope r1 = (kotlin.sequences.SequenceScope) r1
            androidx.compose.runtime.composer.linkbuffer.SlotTable$DebugGroup r3 = r8.this$0
            androidx.compose.runtime.composer.linkbuffer.SlotTable$DebugSlotRange r3 = r3.getSlotRange()
            int r4 = r3.getAddress()
            androidx.compose.runtime.composer.linkbuffer.SlotTable$DebugGroup r5 = r8.this$0
            int r5 = r5.getFlags()
            int r5 = androidx.compose.runtime.composer.linkbuffer.GroupFlagsKt.utilitySlotsCountForFlags(r5)
            int r4 = r4 + r5
            int r5 = r3.getEnd()
            r3 = r4
            r4 = r1
            r1 = r5
            r5 = r8
        L44:
            if (r3 >= r1) goto L64
            androidx.compose.runtime.composer.linkbuffer.SlotTable r6 = r5.this$1
            java.lang.Object[] r6 = androidx.compose.runtime.composer.linkbuffer.SlotTable.access$getSlots(r6)
            r6 = r6[r3]
            r7 = r5
            kotlin.coroutines.Continuation r7 = (kotlin.coroutines.Continuation) r7
            r5.L$0 = r4
            r5.I$0 = r3
            r5.I$1 = r1
            r5.label = r2
            java.lang.Object r6 = r4.yield(r6, r7)
            if (r6 != r0) goto L62
            return r0
        L62:
            int r3 = r3 + r2
            goto L44
        L64:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.composer.linkbuffer.SlotTable$DebugGroup$slots$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
