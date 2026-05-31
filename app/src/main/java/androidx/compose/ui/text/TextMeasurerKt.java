package androidx.compose.ui.text;

import androidx.compose.ui.text.style.TextOverflow;
import kotlin.Metadata;

/* JADX INFO: compiled from: TextMeasurer.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u0018\u0010\u0002\u001a\u00020\u0003*\u00020\u00048BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"DefaultCacheSize", "", "isEllipsis", "", "Landroidx/compose/ui/text/style/TextOverflow;", "isEllipsis-MW5-ApA", "(I)Z", "ui-text"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class TextMeasurerKt {
    private static final int DefaultCacheSize = 8;

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: isEllipsis-MW5-ApA, reason: not valid java name */
    public static final boolean m7551isEllipsisMW5ApA(int $this$isEllipsis) {
        return TextOverflow.m8051equalsimpl0($this$isEllipsis, TextOverflow.INSTANCE.m8061getEllipsisgIe3tQ8()) || TextOverflow.m8051equalsimpl0($this$isEllipsis, TextOverflow.INSTANCE.m8063getStartEllipsisgIe3tQ8()) || TextOverflow.m8051equalsimpl0($this$isEllipsis, TextOverflow.INSTANCE.m8062getMiddleEllipsisgIe3tQ8());
    }
}
