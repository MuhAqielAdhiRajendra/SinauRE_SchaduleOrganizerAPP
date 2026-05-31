package androidx.compose.material3.tokens;

import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;

/* JADX INFO: compiled from: NavigationRailHorizontalItemTokens.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0003\bÁ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0013\u0010\u0004\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\t\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\n\u0010\u0007R\u0013\u0010\u000b\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\f\u0010\u0007R\u0013\u0010\r\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u000e\u0010\u0007R\u0013\u0010\u000f\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0010\u0010\u0007R\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"Landroidx/compose/material3/tokens/NavigationRailHorizontalItemTokens;", "", "<init>", "()V", "ActiveIndicatorHeight", "Landroidx/compose/ui/unit/Dp;", "getActiveIndicatorHeight-D9Ej5fM", "()F", "F", "FullWidthLeadingSpace", "getFullWidthLeadingSpace-D9Ej5fM", "FullWidthTrailingSpace", "getFullWidthTrailingSpace-D9Ej5fM", "IconLabelSpace", "getIconLabelSpace-D9Ej5fM", "LeadingSpace", "getLeadingSpace-D9Ej5fM", "LabelTextFont", "Landroidx/compose/material3/tokens/TypographyKeyTokens;", "getLabelTextFont", "()Landroidx/compose/material3/tokens/TypographyKeyTokens;", "material3"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class NavigationRailHorizontalItemTokens {
    public static final int $stable = 0;
    public static final NavigationRailHorizontalItemTokens INSTANCE = new NavigationRailHorizontalItemTokens();
    private static final float ActiveIndicatorHeight = Dp.m8150constructorimpl((float) 56.0d);
    private static final float FullWidthLeadingSpace = Dp.m8150constructorimpl((float) 16.0d);
    private static final float FullWidthTrailingSpace = Dp.m8150constructorimpl((float) 16.0d);
    private static final float IconLabelSpace = Dp.m8150constructorimpl((float) 8.0d);
    private static final float LeadingSpace = Dp.m8150constructorimpl((float) 16.0d);
    private static final TypographyKeyTokens LabelTextFont = TypographyKeyTokens.LabelLarge;

    private NavigationRailHorizontalItemTokens() {
    }

    /* JADX INFO: renamed from: getActiveIndicatorHeight-D9Ej5fM, reason: not valid java name */
    public final float m4014getActiveIndicatorHeightD9Ej5fM() {
        return ActiveIndicatorHeight;
    }

    /* JADX INFO: renamed from: getFullWidthLeadingSpace-D9Ej5fM, reason: not valid java name */
    public final float m4015getFullWidthLeadingSpaceD9Ej5fM() {
        return FullWidthLeadingSpace;
    }

    /* JADX INFO: renamed from: getFullWidthTrailingSpace-D9Ej5fM, reason: not valid java name */
    public final float m4016getFullWidthTrailingSpaceD9Ej5fM() {
        return FullWidthTrailingSpace;
    }

    /* JADX INFO: renamed from: getIconLabelSpace-D9Ej5fM, reason: not valid java name */
    public final float m4017getIconLabelSpaceD9Ej5fM() {
        return IconLabelSpace;
    }

    /* JADX INFO: renamed from: getLeadingSpace-D9Ej5fM, reason: not valid java name */
    public final float m4018getLeadingSpaceD9Ej5fM() {
        return LeadingSpace;
    }

    public final TypographyKeyTokens getLabelTextFont() {
        return LabelTextFont;
    }
}
