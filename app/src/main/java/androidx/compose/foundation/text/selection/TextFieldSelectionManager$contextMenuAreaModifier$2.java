package androidx.compose.foundation.text.selection;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: TextFieldSelectionManager.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\u0002\u0010\u0000\u001a\u00020\u0001H\n"}, d2 = {"<anonymous>", ""}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.foundation.text.selection.TextFieldSelectionManager$contextMenuAreaModifier$2", f = "TextFieldSelectionManager.kt", i = {}, l = {241, 243}, m = "invokeSuspend", n = {}, s = {}, v = 1)
final class TextFieldSelectionManager$contextMenuAreaModifier$2 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ TextFieldSelectionManager this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    TextFieldSelectionManager$contextMenuAreaModifier$2(TextFieldSelectionManager textFieldSelectionManager, Continuation<? super TextFieldSelectionManager$contextMenuAreaModifier$2> continuation) {
        super(1, continuation);
        this.this$0 = textFieldSelectionManager;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Continuation<?> continuation) {
        return new TextFieldSelectionManager$contextMenuAreaModifier$2(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Continuation<? super Unit> continuation) {
        return ((TextFieldSelectionManager$contextMenuAreaModifier$2) create(continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0035  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r10) {
        /*
            r9 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r9.label
            r2 = 1
            switch(r1) {
                case 0: goto L1c;
                case 1: goto L18;
                case 2: goto L13;
                default: goto La;
            }
        La:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r0)
            throw r10
        L13:
            r0 = 0
            kotlin.ResultKt.throwOnFailure(r10)
            goto L5c
        L18:
            kotlin.ResultKt.throwOnFailure(r10)
            goto L2d
        L1c:
            kotlin.ResultKt.throwOnFailure(r10)
            androidx.compose.foundation.text.selection.TextFieldSelectionManager r1 = r9.this$0
            r3 = r9
            kotlin.coroutines.Continuation r3 = (kotlin.coroutines.Continuation) r3
            r9.label = r2
            java.lang.Object r1 = r1.updateClipboardEntry$foundation(r3)
            if (r1 != r0) goto L2d
            return r0
        L2d:
            androidx.compose.foundation.text.selection.TextFieldSelectionManager r1 = r9.this$0
            kotlin.Pair r1 = androidx.compose.foundation.text.selection.TextFieldSelectionManager.access$getContextTextAndSelection(r1)
            if (r1 == 0) goto L5d
            androidx.compose.foundation.text.selection.TextFieldSelectionManager r3 = r9.this$0
            r4 = 0
            java.lang.Object r5 = r1.component1()
            java.lang.String r5 = (java.lang.String) r5
            java.lang.Object r1 = r1.component2()
            androidx.compose.ui.text.TextRange r1 = (androidx.compose.ui.text.TextRange) r1
            long r6 = r1.getPackedValue()
            androidx.compose.foundation.text.selection.PlatformSelectionBehaviors r1 = r3.getPlatformSelectionBehaviors()
            if (r1 == 0) goto L5d
            r3 = r5
            java.lang.CharSequence r3 = (java.lang.CharSequence) r3
            r8 = 2
            r9.label = r8
            java.lang.Object r1 = r1.mo2034onShowSelectionToolbarSbBc2M(r3, r6, r9)
            if (r1 != r0) goto L5b
            return r0
        L5b:
            r0 = r4
        L5c:
        L5d:
            androidx.compose.foundation.text.selection.TextFieldSelectionManager r0 = r9.this$0
            r0.setTextToolbarShownViaProvider$foundation(r2)
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.selection.TextFieldSelectionManager$contextMenuAreaModifier$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
