package androidx.compose.ui.text.android;

import android.graphics.RectF;
import android.text.GraphemeClusterSegmentFinder;
import android.text.Layout$TextInclusionStrategy;
import androidx.compose.ui.text.android.selection.Api34SegmentFinder;
import androidx.compose.ui.text.android.selection.WordSegmentFinder;
import androidx.window.reflection.WindowExtensionsConstants;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: TextLayout.android.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\bÁ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003JA\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0018\u0010\f\u001a\u0014\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u000e0\rH\u0000¢\u0006\u0002\b\u000f¨\u0006\u0010"}, d2 = {"Landroidx/compose/ui/text/android/AndroidLayoutApi34;", "", "<init>", "()V", "getRangeForRect", "", WindowExtensionsConstants.LAYOUT_PACKAGE, "Landroidx/compose/ui/text/android/TextLayout;", "rectF", "Landroid/graphics/RectF;", "granularity", "", "inclusionStrategy", "Lkotlin/Function2;", "", "getRangeForRect$ui_text", "ui-text"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class AndroidLayoutApi34 {
    public static final int $stable = 0;
    public static final AndroidLayoutApi34 INSTANCE = new AndroidLayoutApi34();

    private AndroidLayoutApi34() {
    }

    public final int[] getRangeForRect$ui_text(TextLayout layout, RectF rectF, int granularity, final Function2<? super RectF, ? super RectF, Boolean> inclusionStrategy) {
        GraphemeClusterSegmentFinder segmentFinder;
        if (granularity == 1) {
            segmentFinder = Api34SegmentFinder.INSTANCE.toAndroidSegmentFinder$ui_text(new WordSegmentFinder(layout.getText(), layout.getWordIterator()));
        } else {
            segmentFinder = new GraphemeClusterSegmentFinder(layout.getText(), layout.getTextPaint());
        }
        return layout.getLayout().getRangeForRect(rectF, segmentFinder, new Layout$TextInclusionStrategy() { // from class: androidx.compose.ui.text.android.AndroidLayoutApi34$$ExternalSyntheticLambda0
            public final boolean isSegmentInside(RectF rectF2, RectF rectF3) {
                return ((Boolean) inclusionStrategy.invoke(rectF2, rectF3)).booleanValue();
            }
        });
    }
}
