package androidx.compose.ui.tooling.animation;

import kotlin.Metadata;

/* JADX INFO: compiled from: ClockInfo.android.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0000\b`\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0005H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0006À\u0006\u0001"}, d2 = {"Landroidx/compose/ui/tooling/animation/ClockInfo;", "", "getMaxDurationPerIterationMillis", "", "requestLayout", "", "ui-tooling"}, k = 1, mv = {2, 1, 0}, xi = 48)
public interface ClockInfo {
    long getMaxDurationPerIterationMillis();

    void requestLayout();
}
