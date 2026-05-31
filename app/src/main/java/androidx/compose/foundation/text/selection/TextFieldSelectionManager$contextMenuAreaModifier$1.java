package androidx.compose.foundation.text.selection;

import androidx.compose.ui.geometry.Offset;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: TextFieldSelectionManager.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "clickLocation", "Landroidx/compose/ui/geometry/Offset;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.foundation.text.selection.TextFieldSelectionManager$contextMenuAreaModifier$1", f = "TextFieldSelectionManager.kt", i = {0}, l = {228, 230}, m = "invokeSuspend", n = {"clickLocation"}, s = {"J$0"}, v = 1)
final class TextFieldSelectionManager$contextMenuAreaModifier$1 extends SuspendLambda implements Function2<Offset, Continuation<? super Unit>, Object> {
    /* synthetic */ long J$0;
    int label;
    final /* synthetic */ TextFieldSelectionManager this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    TextFieldSelectionManager$contextMenuAreaModifier$1(TextFieldSelectionManager textFieldSelectionManager, Continuation<? super TextFieldSelectionManager$contextMenuAreaModifier$1> continuation) {
        super(2, continuation);
        this.this$0 = textFieldSelectionManager;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        TextFieldSelectionManager$contextMenuAreaModifier$1 textFieldSelectionManager$contextMenuAreaModifier$1 = new TextFieldSelectionManager$contextMenuAreaModifier$1(this.this$0, continuation);
        textFieldSelectionManager$contextMenuAreaModifier$1.J$0 = ((Offset) obj).m5078unboximpl();
        return textFieldSelectionManager$contextMenuAreaModifier$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(Offset offset, Continuation<? super Unit> continuation) {
        return m2111invoke3MmeM6k(offset.m5078unboximpl(), continuation);
    }

    /* JADX INFO: renamed from: invoke-3MmeM6k, reason: not valid java name */
    public final Object m2111invoke3MmeM6k(long j, Continuation<? super Unit> continuation) {
        return ((TextFieldSelectionManager$contextMenuAreaModifier$1) create(Offset.m5057boximpl(j), continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x003b  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r14) {
        /*
            r13 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r13.label
            switch(r1) {
                case 0: goto L1d;
                case 1: goto L17;
                case 2: goto L12;
                default: goto L9;
            }
        L9:
            java.lang.IllegalStateException r14 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r14.<init>(r0)
            throw r14
        L12:
            r0 = 0
            kotlin.ResultKt.throwOnFailure(r14)
            goto L68
        L17:
            long r1 = r13.J$0
            kotlin.ResultKt.throwOnFailure(r14)
            goto L33
        L1d:
            kotlin.ResultKt.throwOnFailure(r14)
            long r1 = r13.J$0
            androidx.compose.foundation.text.selection.TextFieldSelectionManager r3 = r13.this$0
            r4 = r13
            kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4
            r13.J$0 = r1
            r5 = 1
            r13.label = r5
            java.lang.Object r3 = r3.updateClipboardEntry$foundation(r4)
            if (r3 != r0) goto L33
            return r0
        L33:
            androidx.compose.foundation.text.selection.TextFieldSelectionManager r3 = r13.this$0
            kotlin.Pair r3 = androidx.compose.foundation.text.selection.TextFieldSelectionManager.access$getContextTextAndSelection(r3)
            if (r3 == 0) goto L6c
            androidx.compose.foundation.text.selection.TextFieldSelectionManager r4 = r13.this$0
            r5 = 0
            java.lang.Object r6 = r3.component1()
            java.lang.String r6 = (java.lang.String) r6
            java.lang.Object r3 = r3.component2()
            androidx.compose.ui.text.TextRange r3 = (androidx.compose.ui.text.TextRange) r3
            long r9 = r3.getPackedValue()
            androidx.compose.foundation.text.selection.PlatformSelectionBehaviors r7 = r4.getPlatformSelectionBehaviors()
            if (r7 == 0) goto L6a
            r8 = r6
            java.lang.CharSequence r8 = (java.lang.CharSequence) r8
            androidx.compose.ui.geometry.Offset r11 = androidx.compose.ui.geometry.Offset.m5057boximpl(r1)
            r1 = 2
            r13.label = r1
            r12 = r13
            java.lang.Object r1 = r7.mo2033onShowContextMenu_2OEclM(r8, r9, r11, r12)
            if (r1 != r0) goto L67
            return r0
        L67:
            r0 = r5
        L68:
            r5 = r0
        L6a:
        L6c:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.selection.TextFieldSelectionManager$contextMenuAreaModifier$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
