package androidx.compose.foundation.style;

import kotlin.Metadata;

/* JADX INFO: compiled from: StyleScope.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0007\u001a\f\u0010\u0003\u001a\u00020\u0001*\u00020\u0002H\u0007\u001a\f\u0010\u0004\u001a\u00020\u0001*\u00020\u0002H\u0007\u001a\u0014\u0010\u0005\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u0007¨\u0006\b"}, d2 = {"fillWidth", "", "Landroidx/compose/foundation/style/StyleScope;", "fillHeight", "fillSize", "apply", "style", "Landroidx/compose/foundation/style/Style;", "foundation"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class StyleScopeKt {
    public static final void fillWidth(StyleScope $this$fillWidth) {
        $this$fillWidth.width(1.0f);
    }

    public static final void fillHeight(StyleScope $this$fillHeight) {
        $this$fillHeight.height(1.0f);
    }

    public static final void fillSize(StyleScope $this$fillSize) {
        $this$fillSize.width(1.0f);
        $this$fillSize.height(1.0f);
    }

    public static final void apply(StyleScope $this$apply, Style style) {
        style.applyStyle($this$apply);
    }
}
