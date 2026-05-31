package com.example.scheduleorganizer.ai;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;

/* JADX INFO: compiled from: OpenAIClient.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0016\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u0003H\u0096@¢\u0006\u0002\u0010\tJ\u000e\u0010\n\u001a\u00020\u000bH\u0086@¢\u0006\u0002\u0010\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/example/scheduleorganizer/ai/OpenAIClient;", "Lcom/example/scheduleorganizer/ai/AIClient;", "apiKey", "", "model", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "chat", "prompt", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "validate", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class OpenAIClient implements AIClient {
    public static final int $stable = 0;
    private final String apiKey;
    private final String model;

    /* JADX INFO: renamed from: com.example.scheduleorganizer.ai.OpenAIClient$chat$2, reason: invalid class name */
    /* JADX INFO: compiled from: OpenAIClient.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.example.scheduleorganizer.ai.OpenAIClient$chat$2", f = "OpenAIClient.kt", i = {0, 0, 0, 0}, l = {82}, m = "invokeSuspend", n = {"lastError", "maxAttempts", "attempt", "backoff"}, nl = {85}, s = {"L$0", "I$0", "I$1", "J$0"}, v = 2)
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super String>, Object> {
        final /* synthetic */ String $prompt;
        int I$0;
        int I$1;
        long J$0;
        Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(String str, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$prompt = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return OpenAIClient.this.new AnonymousClass2(this.$prompt, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super String> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x004a  */
        /* JADX WARN: Removed duplicated region for block: B:72:0x01ed A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:73:0x01ee  */
        /* JADX WARN: Removed duplicated region for block: B:75:0x01f9  */
        /* JADX WARN: Removed duplicated region for block: B:77:0x01fd  */
        /* JADX WARN: Removed duplicated region for block: B:78:0x0201  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:73:0x01ee -> B:74:0x01f2). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r19) throws java.lang.Exception {
            /*
                Method dump skipped, instruction units count: 540
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.example.scheduleorganizer.ai.OpenAIClient.AnonymousClass2.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public OpenAIClient(String apiKey, String model) {
        Intrinsics.checkNotNullParameter(apiKey, "apiKey");
        Intrinsics.checkNotNullParameter(model, "model");
        this.apiKey = apiKey;
        this.model = model;
    }

    public /* synthetic */ OpenAIClient(String str, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? "gpt-3.5-turbo" : str2);
    }

    @Override // com.example.scheduleorganizer.ai.AIClient
    public Object chat(String prompt, Continuation<? super String> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new AnonymousClass2(prompt, null), continuation);
    }

    /* JADX INFO: renamed from: com.example.scheduleorganizer.ai.OpenAIClient$validate$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: OpenAIClient.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.example.scheduleorganizer.ai.OpenAIClient$validate$2", f = "OpenAIClient.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class C03802 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Boolean>, Object> {
        int label;

        C03802(Continuation<? super C03802> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return OpenAIClient.this.new C03802(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Boolean> continuation) {
            return ((C03802) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    if (StringsKt.isBlank(OpenAIClient.this.apiKey)) {
                        return Boxing.boxBoolean(false);
                    }
                    try {
                        URL endpoint = new URL("https://api.openai.com/v1/models");
                        URLConnection uRLConnectionOpenConnection = endpoint.openConnection();
                        Intrinsics.checkNotNull(uRLConnectionOpenConnection, "null cannot be cast to non-null type java.net.HttpURLConnection");
                        HttpURLConnection conn = (HttpURLConnection) uRLConnectionOpenConnection;
                        OpenAIClient openAIClient = OpenAIClient.this;
                        conn.setRequestMethod("GET");
                        conn.setRequestProperty("Authorization", "Bearer " + openAIClient.apiKey);
                        conn.setConnectTimeout(8000);
                        conn.setReadTimeout(8000);
                        int code = conn.getResponseCode();
                        conn.disconnect();
                        return Boxing.boxBoolean(200 <= code && code < 300);
                    } catch (Exception e) {
                        return Boxing.boxBoolean(false);
                    }
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    public final Object validate(Continuation<? super Boolean> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new C03802(null), continuation);
    }
}
