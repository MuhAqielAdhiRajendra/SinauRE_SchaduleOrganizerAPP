package androidx.compose.foundation.text.selection;

import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import kotlin.Metadata;

/* JADX INFO: compiled from: DefaultTextSelectionColors.android.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\"\u0010\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u001c\u0010\u0003\u001a\u00020\u00048\u0000X\u0081\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"DefaultSelectionColor", "Landroidx/compose/ui/graphics/Color;", "J", "DefaultTextSelectionColors", "Landroidx/compose/foundation/text/selection/TextSelectionColors;", "getDefaultTextSelectionColors$annotations", "()V", "getDefaultTextSelectionColors", "()Landroidx/compose/foundation/text/selection/TextSelectionColors;", "foundation"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class DefaultTextSelectionColors_androidKt {
    private static final long DefaultSelectionColor = ColorKt.Color(4282550004L);
    private static final SelectionColors DefaultTextSelectionColors;

    public static /* synthetic */ void getDefaultTextSelectionColors$annotations() {
    }

    static {
        long j = DefaultSelectionColor;
        long j2 = DefaultSelectionColor;
        DefaultTextSelectionColors = new SelectionColors(j, Color.m5311copywmQWz5c(j2, (14 & 1) != 0 ? Color.m5315getAlphaimpl(j2) : 0.4f, (14 & 2) != 0 ? Color.m5319getRedimpl(j2) : 0.0f, (14 & 4) != 0 ? Color.m5318getGreenimpl(j2) : 0.0f, (14 & 8) != 0 ? Color.m5316getBlueimpl(j2) : 0.0f), null);
    }

    public static final SelectionColors getDefaultTextSelectionColors() {
        return DefaultTextSelectionColors;
    }
}
