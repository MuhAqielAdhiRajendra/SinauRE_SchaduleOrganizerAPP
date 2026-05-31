package androidx.compose.runtime;

import kotlin.Metadata;

/* JADX INFO: compiled from: Effects.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÂ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Landroidx/compose/runtime/RememberedCoroutineScopeTracingContext;", "Landroidx/compose/runtime/TracingContext;", "<init>", "()V", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
final class RememberedCoroutineScopeTracingContext extends TracingContext {
    public static final RememberedCoroutineScopeTracingContext INSTANCE = new RememberedCoroutineScopeTracingContext();

    private RememberedCoroutineScopeTracingContext() {
        super("Compose:coroutineScope");
    }
}
