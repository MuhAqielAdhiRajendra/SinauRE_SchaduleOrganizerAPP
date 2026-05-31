package androidx.compose.material3;

import androidx.compose.ui.graphics.Color;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: NavigationBar.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\b\n\u0000\b\u0007\u0018\u00002\u00020\u0001B?\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003¢\u0006\u0004\b\n\u0010\u000bJS\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u0003¢\u0006\u0004\b\u0016\u0010\u0017J\u001f\u0010\u0018\u001a\u00020\u00032\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001aH\u0001¢\u0006\u0004\b\u001c\u0010\u001dJ\u001f\u0010\u001e\u001a\u00020\u00032\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001aH\u0001¢\u0006\u0004\b\u001f\u0010\u001dJ\u0013\u0010\"\u001a\u00020\u001a2\b\u0010#\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010$\u001a\u00020%H\u0016R\u0013\u0010\u0002\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\f\u0010\rR\u0013\u0010\u0004\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\u000f\u0010\rR\u0013\u0010\u0005\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\u0010\u0010\rR\u0013\u0010\u0006\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\u0011\u0010\rR\u0013\u0010\u0007\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\u0012\u0010\rR\u0013\u0010\b\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\u0013\u0010\rR\u0013\u0010\t\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\u0014\u0010\rR\u0014\u0010 \u001a\u00020\u00038@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b!\u0010\r¨\u0006&"}, d2 = {"Landroidx/compose/material3/NavigationBarItemColors;", "", "selectedIconColor", "Landroidx/compose/ui/graphics/Color;", "selectedTextColor", "selectedIndicatorColor", "unselectedIconColor", "unselectedTextColor", "disabledIconColor", "disabledTextColor", "<init>", "(JJJJJJJLkotlin/jvm/internal/DefaultConstructorMarker;)V", "getSelectedIconColor-0d7_KjU", "()J", "J", "getSelectedTextColor-0d7_KjU", "getSelectedIndicatorColor-0d7_KjU", "getUnselectedIconColor-0d7_KjU", "getUnselectedTextColor-0d7_KjU", "getDisabledIconColor-0d7_KjU", "getDisabledTextColor-0d7_KjU", "copy", "copy-4JmcsL4", "(JJJJJJJ)Landroidx/compose/material3/NavigationBarItemColors;", "iconColor", "selected", "", "enabled", "iconColor-WaAFU9c$material3", "(ZZ)J", "textColor", "textColor-WaAFU9c$material3", "indicatorColor", "getIndicatorColor-0d7_KjU$material3", "equals", "other", "hashCode", "", "material3"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class NavigationBarItemColors {
    public static final int $stable = 0;
    private final long disabledIconColor;
    private final long disabledTextColor;
    private final long selectedIconColor;
    private final long selectedIndicatorColor;
    private final long selectedTextColor;
    private final long unselectedIconColor;
    private final long unselectedTextColor;

    public /* synthetic */ NavigationBarItemColors(long j, long j2, long j3, long j4, long j5, long j6, long j7, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, j2, j3, j4, j5, j6, j7);
    }

    private NavigationBarItemColors(long selectedIconColor, long selectedTextColor, long selectedIndicatorColor, long unselectedIconColor, long unselectedTextColor, long disabledIconColor, long disabledTextColor) {
        this.selectedIconColor = selectedIconColor;
        this.selectedTextColor = selectedTextColor;
        this.selectedIndicatorColor = selectedIndicatorColor;
        this.unselectedIconColor = unselectedIconColor;
        this.unselectedTextColor = unselectedTextColor;
        this.disabledIconColor = disabledIconColor;
        this.disabledTextColor = disabledTextColor;
    }

    /* JADX INFO: renamed from: getSelectedIconColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getSelectedIconColor() {
        return this.selectedIconColor;
    }

    /* JADX INFO: renamed from: getSelectedTextColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getSelectedTextColor() {
        return this.selectedTextColor;
    }

    /* JADX INFO: renamed from: getSelectedIndicatorColor-0d7_KjU, reason: not valid java name */
    public final long m2711getSelectedIndicatorColor0d7_KjU() {
        return this.selectedIndicatorColor;
    }

    /* JADX INFO: renamed from: getUnselectedIconColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getUnselectedIconColor() {
        return this.unselectedIconColor;
    }

    /* JADX INFO: renamed from: getUnselectedTextColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getUnselectedTextColor() {
        return this.unselectedTextColor;
    }

    /* JADX INFO: renamed from: getDisabledIconColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDisabledIconColor() {
        return this.disabledIconColor;
    }

    /* JADX INFO: renamed from: getDisabledTextColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDisabledTextColor() {
        return this.disabledTextColor;
    }

    /* JADX INFO: renamed from: copy-4JmcsL4, reason: not valid java name */
    public final NavigationBarItemColors m2706copy4JmcsL4(long selectedIconColor, long selectedTextColor, long selectedIndicatorColor, long unselectedIconColor, long unselectedTextColor, long disabledIconColor, long disabledTextColor) {
        return new NavigationBarItemColors((selectedIconColor > 16L ? 1 : (selectedIconColor == 16L ? 0 : -1)) != 0 ? selectedIconColor : this.selectedIconColor, (selectedTextColor > 16L ? 1 : (selectedTextColor == 16L ? 0 : -1)) != 0 ? selectedTextColor : this.selectedTextColor, (selectedIndicatorColor > 16L ? 1 : (selectedIndicatorColor == 16L ? 0 : -1)) != 0 ? selectedIndicatorColor : this.selectedIndicatorColor, (unselectedIconColor > 16L ? 1 : (unselectedIconColor == 16L ? 0 : -1)) != 0 ? unselectedIconColor : this.unselectedIconColor, (unselectedTextColor > 16L ? 1 : (unselectedTextColor == 16L ? 0 : -1)) != 0 ? unselectedTextColor : this.unselectedTextColor, (disabledIconColor > 16L ? 1 : (disabledIconColor == 16L ? 0 : -1)) != 0 ? disabledIconColor : this.disabledIconColor, disabledTextColor != 16 ? disabledTextColor : this.disabledTextColor, null);
    }

    /* JADX INFO: renamed from: iconColor-WaAFU9c$material3, reason: not valid java name */
    public final long m2715iconColorWaAFU9c$material3(boolean selected, boolean enabled) {
        return !enabled ? this.disabledIconColor : selected ? this.selectedIconColor : this.unselectedIconColor;
    }

    /* JADX INFO: renamed from: textColor-WaAFU9c$material3, reason: not valid java name */
    public final long m2716textColorWaAFU9c$material3(boolean selected, boolean enabled) {
        return !enabled ? this.disabledTextColor : selected ? this.selectedTextColor : this.unselectedTextColor;
    }

    /* JADX INFO: renamed from: getIndicatorColor-0d7_KjU$material3, reason: not valid java name and from getter */
    public final long getSelectedIndicatorColor() {
        return this.selectedIndicatorColor;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other != null && (other instanceof NavigationBarItemColors) && Color.m5314equalsimpl0(this.selectedIconColor, ((NavigationBarItemColors) other).selectedIconColor) && Color.m5314equalsimpl0(this.unselectedIconColor, ((NavigationBarItemColors) other).unselectedIconColor) && Color.m5314equalsimpl0(this.selectedTextColor, ((NavigationBarItemColors) other).selectedTextColor) && Color.m5314equalsimpl0(this.unselectedTextColor, ((NavigationBarItemColors) other).unselectedTextColor) && Color.m5314equalsimpl0(this.selectedIndicatorColor, ((NavigationBarItemColors) other).selectedIndicatorColor) && Color.m5314equalsimpl0(this.disabledIconColor, ((NavigationBarItemColors) other).disabledIconColor) && Color.m5314equalsimpl0(this.disabledTextColor, ((NavigationBarItemColors) other).disabledTextColor)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int result = Color.m5320hashCodeimpl(this.selectedIconColor);
        return (((((((((((result * 31) + Color.m5320hashCodeimpl(this.unselectedIconColor)) * 31) + Color.m5320hashCodeimpl(this.selectedTextColor)) * 31) + Color.m5320hashCodeimpl(this.unselectedTextColor)) * 31) + Color.m5320hashCodeimpl(this.selectedIndicatorColor)) * 31) + Color.m5320hashCodeimpl(this.disabledIconColor)) * 31) + Color.m5320hashCodeimpl(this.disabledTextColor);
    }
}
