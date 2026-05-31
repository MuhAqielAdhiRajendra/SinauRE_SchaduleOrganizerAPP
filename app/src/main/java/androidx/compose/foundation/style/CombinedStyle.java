package androidx.compose.foundation.style;

import java.util.Arrays;
import kotlin.Metadata;

/* JADX INFO: compiled from: Style.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\b\u0001\u0018\u00002\u00020\u0001B\u001b\u0012\u0012\u0010\u0002\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u0003\"\u00020\u0001¢\u0006\u0004\b\u0004\u0010\u0005J\f\u0010\t\u001a\u00020\n*\u00020\u000bH\u0016J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0096\u0002J\b\u0010\u0010\u001a\u00020\u0011H\u0016R\u001b\u0010\u0002\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u0003¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Landroidx/compose/foundation/style/CombinedStyle;", "Landroidx/compose/foundation/style/Style;", "styles", "", "<init>", "([Landroidx/compose/foundation/style/Style;)V", "getStyles", "()[Landroidx/compose/foundation/style/Style;", "[Landroidx/compose/foundation/style/Style;", "applyStyle", "", "Landroidx/compose/foundation/style/StyleScope;", "equals", "", "other", "", "hashCode", "", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class CombinedStyle implements Style {
    public static final int $stable = 8;
    private final Style[] styles;

    public CombinedStyle(Style... styles) {
        this.styles = styles;
    }

    public final Style[] getStyles() {
        return this.styles;
    }

    @Override // androidx.compose.foundation.style.Style
    public void applyStyle(StyleScope $this$applyStyle) {
        for (Style style : this.styles) {
            style.applyStyle($this$applyStyle);
        }
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || !(other instanceof CombinedStyle)) {
            return false;
        }
        return Arrays.equals(this.styles, ((CombinedStyle) other).styles);
    }

    public int hashCode() {
        return Arrays.hashCode(this.styles);
    }
}
