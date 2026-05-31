package androidx.compose.ui.text.android.selection;

import kotlin.Metadata;

/* JADX INFO: compiled from: SegmentFinder.android.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÁ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0011\u0010\u0004\u001a\u00020\u0005*\u00020\u0006H\u0000¢\u0006\u0002\b\u0007¨\u0006\b"}, d2 = {"Landroidx/compose/ui/text/android/selection/Api34SegmentFinder;", "", "<init>", "()V", "toAndroidSegmentFinder", "Landroid/text/SegmentFinder;", "Landroidx/compose/ui/text/android/selection/SegmentFinder;", "toAndroidSegmentFinder$ui_text", "ui-text"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class Api34SegmentFinder {
    public static final int $stable = 0;
    public static final Api34SegmentFinder INSTANCE = new Api34SegmentFinder();

    private Api34SegmentFinder() {
    }

    public final android.text.SegmentFinder toAndroidSegmentFinder$ui_text(final SegmentFinder $this$toAndroidSegmentFinder) {
        return new android.text.SegmentFinder() { // from class: androidx.compose.ui.text.android.selection.Api34SegmentFinder$toAndroidSegmentFinder$1
            public int previousStartBoundary(int offset) {
                return $this$toAndroidSegmentFinder.previousStartBoundary(offset);
            }

            public int previousEndBoundary(int offset) {
                return $this$toAndroidSegmentFinder.previousEndBoundary(offset);
            }

            public int nextStartBoundary(int offset) {
                return $this$toAndroidSegmentFinder.nextStartBoundary(offset);
            }

            public int nextEndBoundary(int offset) {
                return $this$toAndroidSegmentFinder.nextEndBoundary(offset);
            }
        };
    }
}
