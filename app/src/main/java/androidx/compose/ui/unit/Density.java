package androidx.compose.ui.unit;

import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.Size;
import kotlin.Metadata;

/* JADX INFO: compiled from: Density.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bg\u0018\u00002\u00020\u0001J\u0013\u0010\b\u001a\u00020\u0003*\u00020\tH\u0017¢\u0006\u0004\b\n\u0010\u000bJ\u0013\u0010\f\u001a\u00020\r*\u00020\tH\u0017¢\u0006\u0004\b\u000e\u0010\u000fJ\u0013\u0010\b\u001a\u00020\u0003*\u00020\u0010H\u0017¢\u0006\u0004\b\u0011\u0010\u0012J\u0013\u0010\f\u001a\u00020\r*\u00020\u0010H\u0017¢\u0006\u0004\b\u0013\u0010\u0014J\u0013\u0010\u0015\u001a\u00020\t*\u00020\rH\u0017¢\u0006\u0004\b\u0016\u0010\u0017J\u0013\u0010\u0018\u001a\u00020\u0010*\u00020\rH\u0017¢\u0006\u0004\b\u0019\u0010\u001aJ\u0013\u0010\u0015\u001a\u00020\t*\u00020\u0003H\u0017¢\u0006\u0004\b\u0016\u0010\u000bJ\u0013\u0010\u0018\u001a\u00020\u0010*\u00020\u0003H\u0017¢\u0006\u0004\b\u0019\u0010\u001bJ\f\u0010\u001c\u001a\u00020\u001d*\u00020\u001eH\u0017J\u0013\u0010\u001f\u001a\u00020 *\u00020!H\u0017¢\u0006\u0004\b\"\u0010#J\u0013\u0010$\u001a\u00020!*\u00020 H\u0017¢\u0006\u0004\b%\u0010#R\u001a\u0010\u0002\u001a\u00020\u00038&X§\u0004¢\u0006\f\u0012\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006&À\u0006\u0003"}, d2 = {"Landroidx/compose/ui/unit/Density;", "Landroidx/compose/ui/unit/FontScaling;", "density", "", "getDensity$annotations", "()V", "getDensity", "()F", "toPx", "Landroidx/compose/ui/unit/Dp;", "toPx-0680j_4", "(F)F", "roundToPx", "", "roundToPx-0680j_4", "(F)I", "Landroidx/compose/ui/unit/TextUnit;", "toPx--R2X_6o", "(J)F", "roundToPx--R2X_6o", "(J)I", "toDp", "toDp-u2uoSUM", "(I)F", "toSp", "toSp-kPz2Gy4", "(I)J", "(F)J", "toRect", "Landroidx/compose/ui/geometry/Rect;", "Landroidx/compose/ui/unit/DpRect;", "toSize", "Landroidx/compose/ui/geometry/Size;", "Landroidx/compose/ui/unit/DpSize;", "toSize-XkaWNTQ", "(J)J", "toDpSize", "toDpSize-k-rfVVM", "ui-unit"}, k = 1, mv = {2, 1, 0}, xi = 48)
public interface Density extends FontScaling {
    float getDensity();

    /* JADX INFO: compiled from: Density.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    public static final class DefaultImpls {
        public static /* synthetic */ void getDensity$annotations() {
        }

        @Deprecated
        /* JADX INFO: renamed from: toDp-GaN1DYA, reason: not valid java name */
        public static float m8138toDpGaN1DYA(Density $this, long $receiver) {
            return Density.super.mo427toDpGaN1DYA($receiver);
        }

        @Deprecated
        /* JADX INFO: renamed from: toSp-0xMU5do, reason: not valid java name */
        public static long m8145toSp0xMU5do(Density $this, float $receiver) {
            return Density.super.mo434toSp0xMU5do($receiver);
        }

        @Deprecated
        /* JADX INFO: renamed from: toPx-0680j_4, reason: not valid java name */
        public static float m8143toPx0680j_4(Density $this, float $receiver) {
            return Density.super.mo432toPx0680j_4($receiver);
        }

        @Deprecated
        /* JADX INFO: renamed from: roundToPx-0680j_4, reason: not valid java name */
        public static int m8137roundToPx0680j_4(Density $this, float $receiver) {
            return Density.super.mo426roundToPx0680j_4($receiver);
        }

        @Deprecated
        /* JADX INFO: renamed from: toPx--R2X_6o, reason: not valid java name */
        public static float m8142toPxR2X_6o(Density $this, long $receiver) {
            return Density.super.mo431toPxR2X_6o($receiver);
        }

        @Deprecated
        /* JADX INFO: renamed from: roundToPx--R2X_6o, reason: not valid java name */
        public static int m8136roundToPxR2X_6o(Density $this, long $receiver) {
            return Density.super.mo425roundToPxR2X_6o($receiver);
        }

        @Deprecated
        /* JADX INFO: renamed from: toDp-u2uoSUM, reason: not valid java name */
        public static float m8140toDpu2uoSUM(Density $this, int $receiver) {
            return Density.super.mo429toDpu2uoSUM($receiver);
        }

        @Deprecated
        /* JADX INFO: renamed from: toSp-kPz2Gy4, reason: not valid java name */
        public static long m8147toSpkPz2Gy4(Density $this, int $receiver) {
            return Density.super.mo436toSpkPz2Gy4($receiver);
        }

        @Deprecated
        /* JADX INFO: renamed from: toDp-u2uoSUM, reason: not valid java name */
        public static float m8139toDpu2uoSUM(Density $this, float $receiver) {
            return Density.super.mo428toDpu2uoSUM($receiver);
        }

        @Deprecated
        /* JADX INFO: renamed from: toSp-kPz2Gy4, reason: not valid java name */
        public static long m8146toSpkPz2Gy4(Density $this, float $receiver) {
            return Density.super.mo435toSpkPz2Gy4($receiver);
        }

        @Deprecated
        public static Rect toRect(Density $this, DpRect $receiver) {
            return Density.super.toRect($receiver);
        }

        @Deprecated
        /* JADX INFO: renamed from: toSize-XkaWNTQ, reason: not valid java name */
        public static long m8144toSizeXkaWNTQ(Density $this, long $receiver) {
            return Density.super.mo433toSizeXkaWNTQ($receiver);
        }

        @Deprecated
        /* JADX INFO: renamed from: toDpSize-k-rfVVM, reason: not valid java name */
        public static long m8141toDpSizekrfVVM(Density $this, long $receiver) {
            return Density.super.mo430toDpSizekrfVVM($receiver);
        }
    }

    /* JADX INFO: renamed from: toPx-0680j_4 */
    default float mo432toPx0680j_4(float $this$toPx_u2d0680j_4) {
        return getDensity() * $this$toPx_u2d0680j_4;
    }

    /* JADX INFO: renamed from: roundToPx-0680j_4 */
    default int mo426roundToPx0680j_4(float $this$roundToPx_u2d0680j_4) {
        float px = mo432toPx0680j_4($this$roundToPx_u2d0680j_4);
        if (Float.isInfinite(px)) {
            return Integer.MAX_VALUE;
        }
        return Math.round(px);
    }

    /* JADX INFO: renamed from: toPx--R2X_6o */
    default float mo431toPxR2X_6o(long $this$toPx_u2d_u2dR2X_6o) {
        boolean value$iv = TextUnitType.m8372equalsimpl0(TextUnit.m8343getTypeUIouoOA($this$toPx_u2d_u2dR2X_6o), TextUnitType.INSTANCE.m8377getSpUIouoOA());
        if (!value$iv) {
            InlineClassHelperKt.throwIllegalStateException("Only Sp can convert to Px");
        }
        return mo432toPx0680j_4(mo427toDpGaN1DYA($this$toPx_u2d_u2dR2X_6o));
    }

    /* JADX INFO: renamed from: roundToPx--R2X_6o */
    default int mo425roundToPxR2X_6o(long $this$roundToPx_u2d_u2dR2X_6o) {
        float $this$fastRoundToInt$iv = mo431toPxR2X_6o($this$roundToPx_u2d_u2dR2X_6o);
        return Math.round($this$fastRoundToInt$iv);
    }

    /* JADX INFO: renamed from: toDp-u2uoSUM */
    default float mo429toDpu2uoSUM(int $this$toDp_u2du2uoSUM) {
        float $this$dp$iv = $this$toDp_u2du2uoSUM / getDensity();
        return Dp.m8150constructorimpl($this$dp$iv);
    }

    /* JADX INFO: renamed from: toSp-kPz2Gy4 */
    default long mo436toSpkPz2Gy4(int $this$toSp_u2dkPz2Gy4) {
        return mo434toSp0xMU5do(mo429toDpu2uoSUM($this$toSp_u2dkPz2Gy4));
    }

    /* JADX INFO: renamed from: toDp-u2uoSUM */
    default float mo428toDpu2uoSUM(float $this$toDp_u2du2uoSUM) {
        float $this$dp$iv = $this$toDp_u2du2uoSUM / getDensity();
        return Dp.m8150constructorimpl($this$dp$iv);
    }

    /* JADX INFO: renamed from: toSp-kPz2Gy4 */
    default long mo435toSpkPz2Gy4(float $this$toSp_u2dkPz2Gy4) {
        return mo434toSp0xMU5do(mo428toDpu2uoSUM($this$toSp_u2dkPz2Gy4));
    }

    default Rect toRect(DpRect $this$toRect) {
        return new Rect(mo432toPx0680j_4($this$toRect.m8233getLeftD9Ej5fM()), mo432toPx0680j_4($this$toRect.m8235getTopD9Ej5fM()), mo432toPx0680j_4($this$toRect.m8234getRightD9Ej5fM()), mo432toPx0680j_4($this$toRect.m8232getBottomD9Ej5fM()));
    }

    /* JADX INFO: renamed from: toSize-XkaWNTQ */
    default long mo433toSizeXkaWNTQ(long $this$toSize_u2dXkaWNTQ) {
        if ($this$toSize_u2dXkaWNTQ != androidx.compose.ui.geometry.InlineClassHelperKt.UnspecifiedPackedFloats) {
            float width$iv = mo432toPx0680j_4(DpSize.m8248getWidthD9Ej5fM($this$toSize_u2dXkaWNTQ));
            float height$iv = mo432toPx0680j_4(DpSize.m8246getHeightD9Ej5fM($this$toSize_u2dXkaWNTQ));
            long v1$iv$iv = Float.floatToRawIntBits(width$iv);
            long v2$iv$iv = Float.floatToRawIntBits(height$iv);
            return Size.m5128constructorimpl((v1$iv$iv << 32) | (4294967295L & v2$iv$iv));
        }
        return Size.INSTANCE.m5145getUnspecifiedNHjbRc();
    }

    /* JADX INFO: renamed from: toDpSize-k-rfVVM */
    default long mo430toDpSizekrfVVM(long $this$toDpSize_u2dk_u2drfVVM) {
        if (!($this$toDpSize_u2dk_u2drfVVM != androidx.compose.ui.geometry.InlineClassHelperKt.UnspecifiedPackedFloats)) {
            return DpSize.INSTANCE.m8257getUnspecifiedMYxV2XQ();
        }
        int bits$iv$iv$iv = (int) ($this$toDpSize_u2dk_u2drfVVM >> 32);
        int bits$iv$iv$iv2 = (int) (4294967295L & $this$toDpSize_u2dk_u2drfVVM);
        return DpKt.m8172DpSizeYgX7TsA(mo428toDpu2uoSUM(Float.intBitsToFloat(bits$iv$iv$iv)), mo428toDpu2uoSUM(Float.intBitsToFloat(bits$iv$iv$iv2)));
    }
}
