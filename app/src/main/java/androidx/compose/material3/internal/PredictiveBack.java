package androidx.compose.material3.internal;

import kotlin.Metadata;

/* JADX INFO: compiled from: BackHandler.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0003\bÁ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0000¢\u0006\u0002\b\u0007¨\u0006\b"}, d2 = {"Landroidx/compose/material3/internal/PredictiveBack;", "", "<init>", "()V", "transform", "", "progress", "transform$material3", "material3"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class PredictiveBack {
    public static final int $stable = 0;
    public static final PredictiveBack INSTANCE = new PredictiveBack();

    private PredictiveBack() {
    }

    public final float transform$material3(float progress) {
        return BackHandlerKt.PredictiveBackEasing.transform(progress);
    }
}
