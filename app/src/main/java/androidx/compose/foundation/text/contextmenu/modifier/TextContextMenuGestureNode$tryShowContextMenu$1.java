package androidx.compose.foundation.text.contextmenu.modifier;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.compose.foundation.text.contextmenu.modifier.TextContextMenuGestureNode;
import androidx.compose.foundation.text.contextmenu.provider.TextContextMenuProvider;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: TextContextMenuGesturesModifier.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.foundation.text.contextmenu.modifier.TextContextMenuGestureNode$tryShowContextMenu$1", f = "TextContextMenuGesturesModifier.kt", i = {}, l = {107, AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR}, m = "invokeSuspend", n = {}, s = {}, v = 1)
final class TextContextMenuGestureNode$tryShowContextMenu$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ TextContextMenuGestureNode.ClickTextContextMenuDataProvider $dataProvider;
    final /* synthetic */ long $localClickOffset;
    final /* synthetic */ TextContextMenuProvider $provider;
    int label;
    final /* synthetic */ TextContextMenuGestureNode this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    TextContextMenuGestureNode$tryShowContextMenu$1(TextContextMenuGestureNode textContextMenuGestureNode, long j, TextContextMenuProvider textContextMenuProvider, TextContextMenuGestureNode.ClickTextContextMenuDataProvider clickTextContextMenuDataProvider, Continuation<? super TextContextMenuGestureNode$tryShowContextMenu$1> continuation) {
        super(2, continuation);
        this.this$0 = textContextMenuGestureNode;
        this.$localClickOffset = j;
        this.$provider = textContextMenuProvider;
        this.$dataProvider = clickTextContextMenuDataProvider;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new TextContextMenuGestureNode$tryShowContextMenu$1(this.this$0, this.$localClickOffset, this.$provider, this.$dataProvider, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((TextContextMenuGestureNode$tryShowContextMenu$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0048 A[RETURN] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r6) {
        /*
            r5 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r5.label
            switch(r1) {
                case 0: goto L1a;
                case 1: goto L16;
                case 2: goto L12;
                default: goto L9;
            }
        L9:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r0)
            throw r6
        L12:
            kotlin.ResultKt.throwOnFailure(r6)
            goto L49
        L16:
            kotlin.ResultKt.throwOnFailure(r6)
            goto L35
        L1a:
            kotlin.ResultKt.throwOnFailure(r6)
            androidx.compose.foundation.text.contextmenu.modifier.TextContextMenuGestureNode r1 = r5.this$0
            kotlin.jvm.functions.Function2 r1 = androidx.compose.foundation.text.contextmenu.modifier.TextContextMenuGestureNode.access$getOnPreShowContextMenu$p(r1)
            if (r1 == 0) goto L35
            long r2 = r5.$localClickOffset
            androidx.compose.ui.geometry.Offset r2 = androidx.compose.ui.geometry.Offset.m5057boximpl(r2)
            r3 = 1
            r5.label = r3
            java.lang.Object r1 = r1.invoke(r2, r5)
            if (r1 != r0) goto L35
            return r0
        L35:
            androidx.compose.foundation.text.contextmenu.provider.TextContextMenuProvider r1 = r5.$provider
            androidx.compose.foundation.text.contextmenu.modifier.TextContextMenuGestureNode$ClickTextContextMenuDataProvider r2 = r5.$dataProvider
            androidx.compose.foundation.text.contextmenu.provider.TextContextMenuDataProvider r2 = (androidx.compose.foundation.text.contextmenu.provider.TextContextMenuDataProvider) r2
            r3 = r5
            kotlin.coroutines.Continuation r3 = (kotlin.coroutines.Continuation) r3
            r4 = 2
            r5.label = r4
            java.lang.Object r1 = r1.showTextContextMenu(r2, r3)
            if (r1 != r0) goto L49
            return r0
        L49:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.contextmenu.modifier.TextContextMenuGestureNode$tryShowContextMenu$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
