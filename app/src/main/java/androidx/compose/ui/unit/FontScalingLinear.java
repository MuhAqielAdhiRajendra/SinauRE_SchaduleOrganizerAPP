package androidx.compose.ui.unit;

import kotlin.Metadata;

/* JADX INFO: compiled from: FontScaling.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\bg\u0018\u00002\u00020\u0001J\u0013\u0010\b\u001a\u00020\t*\u00020\nH\u0017¢\u0006\u0004\b\u000b\u0010\fJ\u0013\u0010\r\u001a\u00020\n*\u00020\tH\u0017¢\u0006\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0002\u001a\u00020\u00038&X§\u0004¢\u0006\f\u0012\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0010À\u0006\u0003"}, d2 = {"Landroidx/compose/ui/unit/FontScalingLinear;", "", "fontScale", "", "getFontScale$annotations", "()V", "getFontScale", "()F", "toSp", "Landroidx/compose/ui/unit/TextUnit;", "Landroidx/compose/ui/unit/Dp;", "toSp-0xMU5do", "(F)J", "toDp", "toDp-GaN1DYA", "(J)F", "ui-unit"}, k = 1, mv = {2, 1, 0}, xi = 48)
public interface FontScalingLinear {
    float getFontScale();

    /* JADX INFO: compiled from: FontScaling.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    public static final class DefaultImpls {
        public static /* synthetic */ void getFontScale$annotations() {
        }

        @Deprecated
        /* JADX INFO: renamed from: toSp-0xMU5do, reason: not valid java name */
        public static long m8268toSp0xMU5do(FontScalingLinear $this, float $receiver) {
            return FontScalingLinear.super.m8266toSp0xMU5do($receiver);
        }

        @Deprecated
        /* JADX INFO: renamed from: toDp-GaN1DYA, reason: not valid java name */
        public static float m8267toDpGaN1DYA(FontScalingLinear $this, long $receiver) {
            return FontScalingLinear.super.m8265toDpGaN1DYA($receiver);
        }
    }

    /* JADX INFO: renamed from: toSp-0xMU5do, reason: not valid java name */
    default long m8266toSp0xMU5do(float $this$toSp_u2d0xMU5do) {
        return TextUnitKt.getSp($this$toSp_u2d0xMU5do / getFontScale());
    }

    /* JADX INFO: renamed from: toDp-GaN1DYA, reason: not valid java name */
    default float m8265toDpGaN1DYA(long $this$toDp_u2dGaN1DYA) {
        if (TextUnitType.m8372equalsimpl0(TextUnit.m8343getTypeUIouoOA($this$toDp_u2dGaN1DYA), TextUnitType.INSTANCE.m8377getSpUIouoOA())) {
            return Dp.m8150constructorimpl(TextUnit.m8344getValueimpl($this$toDp_u2dGaN1DYA) * getFontScale());
        }
        throw new IllegalStateException("Only Sp can convert to Px".toString());
    }
}
