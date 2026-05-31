package androidx.core.os;

import java.lang.Throwable;

/* JADX INFO: loaded from: classes12.dex */
public interface OutcomeReceiverCompat<R, E extends Throwable> {
    void onResult(R r);

    default void onError(E error) {
    }
}
