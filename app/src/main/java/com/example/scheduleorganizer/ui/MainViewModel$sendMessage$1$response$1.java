package com.example.scheduleorganizer.ui;

import android.content.Context;
import com.example.scheduleorganizer.ai.LocalAIClient;
import com.example.scheduleorganizer.ai.OpenAIClient;
import com.example.scheduleorganizer.util.AISettings;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: MainViewModel.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.example.scheduleorganizer.ui.MainViewModel$sendMessage$1$response$1", f = "MainViewModel.kt", i = {0, 0, 2}, l = {107, 110, 115}, m = "invokeSuspend", n = {"key", "model", "e"}, nl = {110, 113, 116}, s = {"L$0", "L$1", "L$0"}, v = 2)
final class MainViewModel$sendMessage$1$response$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super String>, Object> {
    final /* synthetic */ Context $context;
    final /* synthetic */ String $prompt;
    final /* synthetic */ AISettings.Provider $provider;
    Object L$0;
    Object L$1;
    int label;

    /* JADX INFO: compiled from: MainViewModel.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[AISettings.Provider.values().length];
            try {
                iArr[AISettings.Provider.OPENAI.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MainViewModel$sendMessage$1$response$1(AISettings.Provider provider, Context context, String str, Continuation<? super MainViewModel$sendMessage$1$response$1> continuation) {
        super(2, continuation);
        this.$provider = provider;
        this.$context = context;
        this.$prompt = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MainViewModel$sendMessage$1$response$1(this.$provider, this.$context, this.$prompt, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super String> continuation) {
        return ((MainViewModel$sendMessage$1$response$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object objChat;
        Object objChat2;
        Object objChat3;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        try {
        } catch (Exception e) {
            try {
                this.L$0 = SpillingKt.nullOutSpilledVariable(e);
                this.L$1 = null;
                this.label = 3;
                objChat = new LocalAIClient().chat(this.$prompt, this);
                if (objChat == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } catch (Exception e2) {
                return "Maaf, terjadi kesalahan saat memproses permintaan.";
            }
        }
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                if (WhenMappings.$EnumSwitchMapping$0[this.$provider.ordinal()] != 1) {
                    this.label = 2;
                    objChat2 = new LocalAIClient().chat(this.$prompt, this);
                    if (objChat2 == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    return (String) objChat2;
                }
                String key = AISettings.INSTANCE.getApiKey(this.$context);
                String model = AISettings.INSTANCE.getModel(this.$context);
                if (StringsKt.isBlank(key)) {
                    throw new IllegalStateException("OpenAI key not set");
                }
                this.L$0 = SpillingKt.nullOutSpilledVariable(key);
                this.L$1 = SpillingKt.nullOutSpilledVariable(model);
                this.label = 1;
                objChat3 = new OpenAIClient(key, model).chat(this.$prompt, this);
                if (objChat3 == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return (String) objChat3;
            case 1:
                ResultKt.throwOnFailure($result);
                objChat3 = $result;
                return (String) objChat3;
            case 2:
                ResultKt.throwOnFailure($result);
                objChat2 = $result;
                return (String) objChat2;
            case 3:
                try {
                    ResultKt.throwOnFailure($result);
                    objChat = $result;
                    return (String) objChat;
                } catch (Exception e3) {
                    return "Maaf, terjadi kesalahan saat memproses permintaan.";
                }
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
