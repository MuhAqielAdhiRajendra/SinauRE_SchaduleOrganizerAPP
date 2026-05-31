package androidx.compose.material3.internal.colorUtil;

import kotlin.Metadata;

/* JADX INFO: compiled from: Frame.android.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0004\u001a \u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0001H\u0002¨\u0006\u0005"}, d2 = {"lerp", "", "start", "stop", "amount", "material3"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class Frame_androidKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final float lerp(float start, float stop, float amount) {
        return ((1.0f - amount) * start) + (amount * stop);
    }
}
