package androidx.compose.animation;

import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: LookaheadAnimationVisualDebugConfig.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0001\u0018\u00002\u00020\u0001B9\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0005\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003¢\u0006\u0004\b\t\u0010\nJ\u0013\u0010\u0011\u001a\u00020\u00032\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\b\u0010\u0015\u001a\u00020\u0016H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u000bR\u0013\u0010\u0004\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\f\u0010\rR\u0013\u0010\u0006\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\u000f\u0010\rR\u0013\u0010\u0007\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\u0010\u0010\rR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u000b¨\u0006\u0017"}, d2 = {"Landroidx/compose/animation/LookaheadAnimationVisualDebugConfig;", "", "isEnabled", "", "overlayColor", "Landroidx/compose/ui/graphics/Color;", "multipleMatchesColor", "unmatchedElementColor", "isShowKeyLabelEnabled", "<init>", "(ZJJJZLkotlin/jvm/internal/DefaultConstructorMarker;)V", "()Z", "getOverlayColor-0d7_KjU", "()J", "J", "getMultipleMatchesColor-0d7_KjU", "getUnmatchedElementColor-0d7_KjU", "equals", "other", "hashCode", "", "toString", "", "animation"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class LookaheadAnimationVisualDebugConfig {
    public static final int $stable = 0;
    private final boolean isEnabled;
    private final boolean isShowKeyLabelEnabled;
    private final long multipleMatchesColor;
    private final long overlayColor;
    private final long unmatchedElementColor;

    public /* synthetic */ LookaheadAnimationVisualDebugConfig(boolean z, long j, long j2, long j3, boolean z2, DefaultConstructorMarker defaultConstructorMarker) {
        this(z, j, j2, j3, z2);
    }

    private LookaheadAnimationVisualDebugConfig(boolean isEnabled, long overlayColor, long multipleMatchesColor, long unmatchedElementColor, boolean isShowKeyLabelEnabled) {
        this.isEnabled = isEnabled;
        this.overlayColor = overlayColor;
        this.multipleMatchesColor = multipleMatchesColor;
        this.unmatchedElementColor = unmatchedElementColor;
        this.isShowKeyLabelEnabled = isShowKeyLabelEnabled;
    }

    public /* synthetic */ LookaheadAnimationVisualDebugConfig(boolean z, long j, long j2, long j3, boolean z2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? true : z, (i & 2) != 0 ? ColorKt.Color(2150934611L) : j, (i & 4) != 0 ? ColorKt.Color(4293542709L) : j2, (i & 8) != 0 ? ColorKt.Color(4288323750L) : j3, (i & 16) != 0 ? false : z2, null);
    }

    /* JADX INFO: renamed from: isEnabled, reason: from getter */
    public final boolean getIsEnabled() {
        return this.isEnabled;
    }

    /* JADX INFO: renamed from: getOverlayColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getOverlayColor() {
        return this.overlayColor;
    }

    /* JADX INFO: renamed from: getMultipleMatchesColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getMultipleMatchesColor() {
        return this.multipleMatchesColor;
    }

    /* JADX INFO: renamed from: getUnmatchedElementColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getUnmatchedElementColor() {
        return this.unmatchedElementColor;
    }

    /* JADX INFO: renamed from: isShowKeyLabelEnabled, reason: from getter */
    public final boolean getIsShowKeyLabelEnabled() {
        return this.isShowKeyLabelEnabled;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof LookaheadAnimationVisualDebugConfig) && this.isEnabled == ((LookaheadAnimationVisualDebugConfig) other).isEnabled && Color.m5314equalsimpl0(this.overlayColor, ((LookaheadAnimationVisualDebugConfig) other).overlayColor) && Color.m5314equalsimpl0(this.multipleMatchesColor, ((LookaheadAnimationVisualDebugConfig) other).multipleMatchesColor) && Color.m5314equalsimpl0(this.unmatchedElementColor, ((LookaheadAnimationVisualDebugConfig) other).unmatchedElementColor) && this.isShowKeyLabelEnabled == ((LookaheadAnimationVisualDebugConfig) other).isShowKeyLabelEnabled;
    }

    public int hashCode() {
        int result = Boolean.hashCode(this.isEnabled);
        return (((((((result * 31) + Color.m5320hashCodeimpl(this.overlayColor)) * 31) + Color.m5320hashCodeimpl(this.multipleMatchesColor)) * 31) + Color.m5320hashCodeimpl(this.unmatchedElementColor)) * 31) + Boolean.hashCode(this.isShowKeyLabelEnabled);
    }

    public String toString() {
        return "LookaheadAnimationVisualDebugConfig(isEnabled=" + this.isEnabled + ", overlayColor=" + ((Object) Color.m5321toStringimpl(this.overlayColor)) + ", multipleMatchesColor=" + ((Object) Color.m5321toStringimpl(this.multipleMatchesColor)) + ", unmatchedElementColor=" + ((Object) Color.m5321toStringimpl(this.unmatchedElementColor)) + ", isShowKeyLabelEnabled=" + this.isShowKeyLabelEnabled + ')';
    }
}
