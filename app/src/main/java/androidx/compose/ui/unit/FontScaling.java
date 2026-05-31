package androidx.compose.ui.unit;

import androidx.compose.ui.unit.fontscaling.FontScaleConverter;
import androidx.compose.ui.unit.fontscaling.FontScaleConverterFactory;
import kotlin.Metadata;

/* JADX INFO: compiled from: FontScaling.android.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\bg\u0018\u00002\u00020\u0001J\u0013\u0010\b\u001a\u00020\t*\u00020\nH\u0017¢\u0006\u0004\b\u000b\u0010\fJ\u0013\u0010\r\u001a\u00020\n*\u00020\tH\u0017¢\u0006\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0002\u001a\u00020\u00038&X§\u0004¢\u0006\f\u0012\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0010À\u0006\u0003"}, d2 = {"Landroidx/compose/ui/unit/FontScaling;", "", "fontScale", "", "getFontScale$annotations", "()V", "getFontScale", "()F", "toSp", "Landroidx/compose/ui/unit/TextUnit;", "Landroidx/compose/ui/unit/Dp;", "toSp-0xMU5do", "(F)J", "toDp", "toDp-GaN1DYA", "(J)F", "ui-unit"}, k = 1, mv = {2, 1, 0}, xi = 48)
public interface FontScaling {
    float getFontScale();

    /* JADX INFO: compiled from: FontScaling.android.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    public static final class DefaultImpls {
        public static /* synthetic */ void getFontScale$annotations() {
        }

        @Deprecated
        /* JADX INFO: renamed from: toSp-0xMU5do, reason: not valid java name */
        public static long m8262toSp0xMU5do(FontScaling $this, float $receiver) {
            return FontScaling.super.mo434toSp0xMU5do($receiver);
        }

        @Deprecated
        /* JADX INFO: renamed from: toDp-GaN1DYA, reason: not valid java name */
        public static float m8261toDpGaN1DYA(FontScaling $this, long $receiver) {
            return FontScaling.super.mo427toDpGaN1DYA($receiver);
        }
    }

    /* JADX INFO: renamed from: toSp-0xMU5do */
    default long mo434toSp0xMU5do(float $this$toSp_u2d0xMU5do) {
        if (!FontScaleConverterFactory.INSTANCE.isNonLinearFontScalingActive(getFontScale())) {
            return TextUnitKt.getSp($this$toSp_u2d0xMU5do / getFontScale());
        }
        FontScaleConverter converter = FontScaleConverterFactory.INSTANCE.forScale(getFontScale());
        return TextUnitKt.getSp(converter != null ? converter.convertDpToSp($this$toSp_u2d0xMU5do) : $this$toSp_u2d0xMU5do / getFontScale());
    }

    /* JADX INFO: renamed from: toDp-GaN1DYA */
    default float mo427toDpGaN1DYA(long $this$toDp_u2dGaN1DYA) {
        boolean value$iv = TextUnitType.m8372equalsimpl0(TextUnit.m8343getTypeUIouoOA($this$toDp_u2dGaN1DYA), TextUnitType.INSTANCE.m8377getSpUIouoOA());
        if (!value$iv) {
            InlineClassHelperKt.throwIllegalStateException("Only Sp can convert to Px");
        }
        if (!FontScaleConverterFactory.INSTANCE.isNonLinearFontScalingActive(getFontScale())) {
            return Dp.m8150constructorimpl(TextUnit.m8344getValueimpl($this$toDp_u2dGaN1DYA) * getFontScale());
        }
        FontScaleConverter converter = FontScaleConverterFactory.INSTANCE.forScale(getFontScale());
        float fM8344getValueimpl = TextUnit.m8344getValueimpl($this$toDp_u2dGaN1DYA);
        return Dp.m8150constructorimpl(converter == null ? fM8344getValueimpl * getFontScale() : converter.convertSpToDp(fM8344getValueimpl));
    }
}
