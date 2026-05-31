package androidx.compose.runtime;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: HostDefaultProvider.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0017\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"LocalHostDefaultProvider", "Landroidx/compose/runtime/ProvidableCompositionLocal;", "Landroidx/compose/runtime/HostDefaultProvider;", "getLocalHostDefaultProvider", "()Landroidx/compose/runtime/ProvidableCompositionLocal;", "runtime"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class HostDefaultProviderKt {
    private static final ProvidableCompositionLocal<HostDefaultProvider> LocalHostDefaultProvider = CompositionLocalKt.compositionLocalOf$default(null, new Function0() { // from class: androidx.compose.runtime.HostDefaultProviderKt$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return HostDefaultProviderKt.LocalHostDefaultProvider$lambda$0();
        }
    }, 1, null);

    public static final ProvidableCompositionLocal<HostDefaultProvider> getLocalHostDefaultProvider() {
        return LocalHostDefaultProvider;
    }

    static final HostDefaultProvider LocalHostDefaultProvider$lambda$0() {
        throw new IllegalStateException("CompositionLocal LocalHostDefaultProvider not present".toString());
    }
}
