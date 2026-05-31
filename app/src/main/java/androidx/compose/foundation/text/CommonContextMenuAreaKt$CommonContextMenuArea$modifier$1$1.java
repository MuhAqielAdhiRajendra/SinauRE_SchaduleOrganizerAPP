package androidx.compose.foundation.text;

import androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState;
import androidx.compose.ui.geometry.Offset;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: CommonContextMenuArea.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "clickLocation", "Landroidx/compose/ui/geometry/Offset;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.foundation.text.CommonContextMenuAreaKt$CommonContextMenuArea$modifier$1$1", f = "CommonContextMenuArea.kt", i = {0}, l = {82, 83}, m = "invokeSuspend", n = {"clickLocation"}, s = {"J$0"}, v = 1)
final class CommonContextMenuAreaKt$CommonContextMenuArea$modifier$1$1 extends SuspendLambda implements Function2<Offset, Continuation<? super Unit>, Object> {
    final /* synthetic */ TextFieldSelectionState $selectionState;
    /* synthetic */ long J$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    CommonContextMenuAreaKt$CommonContextMenuArea$modifier$1$1(TextFieldSelectionState textFieldSelectionState, Continuation<? super CommonContextMenuAreaKt$CommonContextMenuArea$modifier$1$1> continuation) {
        super(2, continuation);
        this.$selectionState = textFieldSelectionState;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        CommonContextMenuAreaKt$CommonContextMenuArea$modifier$1$1 commonContextMenuAreaKt$CommonContextMenuArea$modifier$1$1 = new CommonContextMenuAreaKt$CommonContextMenuArea$modifier$1$1(this.$selectionState, continuation);
        commonContextMenuAreaKt$CommonContextMenuArea$modifier$1$1.J$0 = ((Offset) obj).m5078unboximpl();
        return commonContextMenuAreaKt$CommonContextMenuArea$modifier$1$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(Offset offset, Continuation<? super Unit> continuation) {
        return m1507invoke3MmeM6k(offset.m5078unboximpl(), continuation);
    }

    /* JADX INFO: renamed from: invoke-3MmeM6k, reason: not valid java name */
    public final Object m1507invoke3MmeM6k(long j, Continuation<? super Unit> continuation) {
        return ((CommonContextMenuAreaKt$CommonContextMenuArea$modifier$1$1) create(Offset.m5057boximpl(j), continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x003a  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r11) {
        /*
            r10 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r10.label
            switch(r1) {
                case 0: goto L1c;
                case 1: goto L16;
                case 2: goto L12;
                default: goto L9;
            }
        L9:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r0)
            throw r11
        L12:
            kotlin.ResultKt.throwOnFailure(r11)
            goto L67
        L16:
            long r1 = r10.J$0
            kotlin.ResultKt.throwOnFailure(r11)
            goto L32
        L1c:
            kotlin.ResultKt.throwOnFailure(r11)
            long r1 = r10.J$0
            androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState r3 = r10.$selectionState
            r4 = r10
            kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4
            r10.J$0 = r1
            r5 = 1
            r10.label = r5
            java.lang.Object r3 = r3.updateClipboardEntry(r4)
            if (r3 != r0) goto L32
            return r0
        L32:
            androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState r3 = r10.$selectionState
            androidx.compose.foundation.text.selection.PlatformSelectionBehaviors r4 = r3.getPlatformSelectionBehaviors()
            if (r4 == 0) goto L68
            androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState r3 = r10.$selectionState
            androidx.compose.foundation.text.input.internal.TransformedTextFieldState r3 = r3.getTextFieldState()
            androidx.compose.foundation.text.input.TextFieldCharSequence r3 = r3.getVisualText()
            java.lang.CharSequence r5 = r3.getText()
            androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState r3 = r10.$selectionState
            androidx.compose.foundation.text.input.internal.TransformedTextFieldState r3 = r3.getTextFieldState()
            androidx.compose.foundation.text.input.TextFieldCharSequence r3 = r3.getVisualText()
            long r6 = r3.getSelection()
            androidx.compose.ui.geometry.Offset r8 = androidx.compose.ui.geometry.Offset.m5057boximpl(r1)
            r9 = r10
            kotlin.coroutines.Continuation r9 = (kotlin.coroutines.Continuation) r9
            r1 = 2
            r10.label = r1
            java.lang.Object r1 = r4.mo2033onShowContextMenu_2OEclM(r5, r6, r8, r9)
            if (r1 != r0) goto L67
            return r0
        L67:
        L68:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.CommonContextMenuAreaKt$CommonContextMenuArea$modifier$1$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
