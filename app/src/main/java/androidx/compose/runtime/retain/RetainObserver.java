package androidx.compose.runtime.retain;

import kotlin.Metadata;

/* JADX INFO: compiled from: RetainObserver.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J\b\u0010\u0005\u001a\u00020\u0003H&J\b\u0010\u0006\u001a\u00020\u0003H&J\b\u0010\u0007\u001a\u00020\u0003H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\bÀ\u0006\u0001"}, d2 = {"Landroidx/compose/runtime/retain/RetainObserver;", "", "onRetained", "", "onEnteredComposition", "onExitedComposition", "onRetired", "onUnused", "runtime-retain"}, k = 1, mv = {2, 1, 0}, xi = 48)
public interface RetainObserver {
    void onEnteredComposition();

    void onExitedComposition();

    void onRetained();

    void onRetired();

    void onUnused();
}
