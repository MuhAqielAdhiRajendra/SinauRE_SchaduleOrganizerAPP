package com.example.scheduleorganizer.ui.screen;

import android.content.Context;
import androidx.compose.material3.SnackbarHostState;
import androidx.compose.runtime.MutableState;
import com.example.scheduleorganizer.util.AISettings;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: ChatScreen.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.example.scheduleorganizer.ui.screen.ChatScreenKt$ChatScreen$2$1$5$1$1$1", f = "ChatScreen.kt", i = {0, 1, 1, 2, 2}, l = {165, 172, 175}, m = "invokeSuspend", n = {"client", "client", "ok", "client", "ok"}, nl = {166, 173, 177}, s = {"L$0", "L$0", "I$0", "L$0", "I$0"}, v = 2)
final class ChatScreenKt$ChatScreen$2$1$5$1$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MutableState<String> $apiKeyInput$delegate;
    final /* synthetic */ Context $context;
    final /* synthetic */ MutableState<String> $modelInput$delegate;
    final /* synthetic */ MutableState<AISettings.Provider> $selectedProvider$delegate;
    final /* synthetic */ MutableState<Boolean> $showProviderDialog$delegate;
    final /* synthetic */ SnackbarHostState $snackbarHostState;
    final /* synthetic */ MutableState<Boolean> $validating$delegate;
    int I$0;
    Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ChatScreenKt$ChatScreen$2$1$5$1$1$1(Context context, SnackbarHostState snackbarHostState, MutableState<String> mutableState, MutableState<String> mutableState2, MutableState<Boolean> mutableState3, MutableState<AISettings.Provider> mutableState4, MutableState<Boolean> mutableState5, Continuation<? super ChatScreenKt$ChatScreen$2$1$5$1$1$1> continuation) {
        super(2, continuation);
        this.$context = context;
        this.$snackbarHostState = snackbarHostState;
        this.$apiKeyInput$delegate = mutableState;
        this.$modelInput$delegate = mutableState2;
        this.$validating$delegate = mutableState3;
        this.$selectedProvider$delegate = mutableState4;
        this.$showProviderDialog$delegate = mutableState5;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ChatScreenKt$ChatScreen$2$1$5$1$1$1(this.$context, this.$snackbarHostState, this.$apiKeyInput$delegate, this.$modelInput$delegate, this.$validating$delegate, this.$selectedProvider$delegate, this.$showProviderDialog$delegate, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ChatScreenKt$ChatScreen$2$1$5$1$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00ed  */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r0v20 */
    /* JADX WARN: Type inference failed for: r0v3, types: [int] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r15) {
        /*
            Method dump skipped, instruction units count: 284
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.scheduleorganizer.ui.screen.ChatScreenKt$ChatScreen$2$1$5$1$1$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
