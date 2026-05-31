package androidx.compose.foundation.text.input.internal;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: TextFieldCoreModifier.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\u0002\u0010\u0000\u001a\u00020\u0001H\n"}, d2 = {"<anonymous>", ""}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.foundation.text.input.internal.TextFieldCoreModifierNode$textContextMenuToolbarHandlerNode$1", f = "TextFieldCoreModifier.kt", i = {}, l = {209, 210}, m = "invokeSuspend", n = {}, s = {}, v = 1)
final class TextFieldCoreModifierNode$textContextMenuToolbarHandlerNode$1 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ TextFieldCoreModifierNode this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    TextFieldCoreModifierNode$textContextMenuToolbarHandlerNode$1(TextFieldCoreModifierNode textFieldCoreModifierNode, Continuation<? super TextFieldCoreModifierNode$textContextMenuToolbarHandlerNode$1> continuation) {
        super(1, continuation);
        this.this$0 = textFieldCoreModifierNode;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Continuation<?> continuation) {
        return new TextFieldCoreModifierNode$textContextMenuToolbarHandlerNode$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Continuation<? super Unit> continuation) {
        return ((TextFieldCoreModifierNode$textContextMenuToolbarHandlerNode$1) create(continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0038  */
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
                case 0: goto L1b;
                case 1: goto L17;
                case 2: goto L13;
                default: goto La;
            }
        La:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r0)
            throw r9
        L13:
            kotlin.ResultKt.throwOnFailure(r9)
            goto L69
        L17:
            kotlin.ResultKt.throwOnFailure(r9)
            goto L30
        L1b:
            kotlin.ResultKt.throwOnFailure(r9)
            androidx.compose.foundation.text.input.internal.TextFieldCoreModifierNode r1 = r8.this$0
            androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState r1 = androidx.compose.foundation.text.input.internal.TextFieldCoreModifierNode.access$getTextFieldSelectionState$p(r1)
            r3 = r8
            kotlin.coroutines.Continuation r3 = (kotlin.coroutines.Continuation) r3
            r8.label = r2
            java.lang.Object r1 = r1.updateClipboardEntry(r3)
            if (r1 != r0) goto L30
            return r0
        L30:
            androidx.compose.foundation.text.input.internal.TextFieldCoreModifierNode r1 = r8.this$0
            androidx.compose.foundation.text.selection.PlatformSelectionBehaviors r1 = androidx.compose.foundation.text.input.internal.TextFieldCoreModifierNode.access$getPlatformSelectionBehaviors$p(r1)
            if (r1 == 0) goto L69
            androidx.compose.foundation.text.input.internal.TextFieldCoreModifierNode r3 = r8.this$0
            androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState r3 = androidx.compose.foundation.text.input.internal.TextFieldCoreModifierNode.access$getTextFieldSelectionState$p(r3)
            androidx.compose.foundation.text.input.internal.TransformedTextFieldState r3 = r3.getTextFieldState()
            androidx.compose.foundation.text.input.TextFieldCharSequence r3 = r3.getVisualText()
            java.lang.CharSequence r3 = r3.getText()
            androidx.compose.foundation.text.input.internal.TextFieldCoreModifierNode r4 = r8.this$0
            androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState r4 = androidx.compose.foundation.text.input.internal.TextFieldCoreModifierNode.access$getTextFieldSelectionState$p(r4)
            androidx.compose.foundation.text.input.internal.TransformedTextFieldState r4 = r4.getTextFieldState()
            androidx.compose.foundation.text.input.TextFieldCharSequence r4 = r4.getVisualText()
            long r4 = r4.getSelection()
            r6 = r8
            kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6
            r7 = 2
            r8.label = r7
            java.lang.Object r1 = r1.mo2034onShowSelectionToolbarSbBc2M(r3, r4, r6)
            if (r1 != r0) goto L69
            return r0
        L69:
            androidx.compose.foundation.text.input.internal.TextFieldCoreModifierNode r0 = r8.this$0
            androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState r0 = androidx.compose.foundation.text.input.internal.TextFieldCoreModifierNode.access$getTextFieldSelectionState$p(r0)
            r0.setTextToolbarShown$foundation(r2)
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.input.internal.TextFieldCoreModifierNode$textContextMenuToolbarHandlerNode$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
