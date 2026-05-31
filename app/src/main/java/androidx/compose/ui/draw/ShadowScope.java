package androidx.compose.ui.draw;

import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.DpRect;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Metadata;

/* JADX INFO: compiled from: Shadow.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001R\u0018\u0010\u0002\u001a\u00020\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007R\u0018\u0010\b\u001a\u00020\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\t\u0010\u0005\"\u0004\b\n\u0010\u0007R\u0018\u0010\u000b\u001a\u00020\fX¦\u000e¢\u0006\f\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u0004\u0018\u00010\u0012X¦\u000e¢\u0006\f\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u0018\u0010\u0017\u001a\u00020\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\u0018\u0010\u0005\"\u0004\b\u0019\u0010\u0007R\u0018\u0010\u001a\u001a\u00020\u001bX¦\u000e¢\u0006\f\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u0018\u0010 \u001a\u00020!X¦\u000e¢\u0006\f\u001a\u0004\b\"\u0010\u000e\"\u0004\b#\u0010\u0010ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006$À\u0006\u0003"}, d2 = {"Landroidx/compose/ui/draw/ShadowScope;", "Landroidx/compose/ui/unit/Density;", "radius", "", "getRadius", "()F", "setRadius", "(F)V", "spread", "getSpread", "setSpread", TypedValues.Custom.S_COLOR, "Landroidx/compose/ui/graphics/Color;", "getColor-0d7_KjU", "()J", "setColor-8_81llA", "(J)V", "brush", "Landroidx/compose/ui/graphics/Brush;", "getBrush", "()Landroidx/compose/ui/graphics/Brush;", "setBrush", "(Landroidx/compose/ui/graphics/Brush;)V", "alpha", "getAlpha", "setAlpha", "blendMode", "Landroidx/compose/ui/graphics/BlendMode;", "getBlendMode-0nO6VwU", "()I", "setBlendMode-s9anfk8", "(I)V", TypedValues.CycleType.S_WAVE_OFFSET, "Landroidx/compose/ui/geometry/Offset;", "getOffset-F1C5BW0", "setOffset-k-4lQ0M", "ui"}, k = 1, mv = {2, 1, 0}, xi = 48)
public interface ShadowScope extends Density {
    float getAlpha();

    /* JADX INFO: renamed from: getBlendMode-0nO6VwU */
    int mo4827getBlendMode0nO6VwU();

    Brush getBrush();

    /* JADX INFO: renamed from: getColor-0d7_KjU */
    long mo4828getColor0d7_KjU();

    /* JADX INFO: renamed from: getOffset-F1C5BW0 */
    long mo4829getOffsetF1C5BW0();

    float getRadius();

    float getSpread();

    void setAlpha(float f);

    /* JADX INFO: renamed from: setBlendMode-s9anfk8 */
    void mo4830setBlendModes9anfk8(int i);

    void setBrush(Brush brush);

    /* JADX INFO: renamed from: setColor-8_81llA */
    void mo4831setColor8_81llA(long j);

    /* JADX INFO: renamed from: setOffset-k-4lQ0M */
    void mo4832setOffsetk4lQ0M(long j);

    void setRadius(float f);

    void setSpread(float f);

    /* JADX INFO: compiled from: Shadow.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    public static final class DefaultImpls {
        @Deprecated
        /* JADX INFO: renamed from: roundToPx--R2X_6o, reason: not valid java name */
        public static int m4926roundToPxR2X_6o(ShadowScope $this, long $receiver) {
            return ShadowScope.super.mo425roundToPxR2X_6o($receiver);
        }

        @Deprecated
        /* JADX INFO: renamed from: roundToPx-0680j_4, reason: not valid java name */
        public static int m4927roundToPx0680j_4(ShadowScope $this, float $receiver) {
            return ShadowScope.super.mo426roundToPx0680j_4($receiver);
        }

        @Deprecated
        /* JADX INFO: renamed from: toDp-GaN1DYA, reason: not valid java name */
        public static float m4928toDpGaN1DYA(ShadowScope $this, long $receiver) {
            return ShadowScope.super.mo427toDpGaN1DYA($receiver);
        }

        @Deprecated
        /* JADX INFO: renamed from: toDp-u2uoSUM, reason: not valid java name */
        public static float m4929toDpu2uoSUM(ShadowScope $this, float $receiver) {
            return ShadowScope.super.mo428toDpu2uoSUM($receiver);
        }

        @Deprecated
        /* JADX INFO: renamed from: toDp-u2uoSUM, reason: not valid java name */
        public static float m4930toDpu2uoSUM(ShadowScope $this, int $receiver) {
            return ShadowScope.super.mo429toDpu2uoSUM($receiver);
        }

        @Deprecated
        /* JADX INFO: renamed from: toDpSize-k-rfVVM, reason: not valid java name */
        public static long m4931toDpSizekrfVVM(ShadowScope $this, long $receiver) {
            return ShadowScope.super.mo430toDpSizekrfVVM($receiver);
        }

        @Deprecated
        /* JADX INFO: renamed from: toPx--R2X_6o, reason: not valid java name */
        public static float m4932toPxR2X_6o(ShadowScope $this, long $receiver) {
            return ShadowScope.super.mo431toPxR2X_6o($receiver);
        }

        @Deprecated
        /* JADX INFO: renamed from: toPx-0680j_4, reason: not valid java name */
        public static float m4933toPx0680j_4(ShadowScope $this, float $receiver) {
            return ShadowScope.super.mo432toPx0680j_4($receiver);
        }

        @Deprecated
        public static Rect toRect(ShadowScope $this, DpRect $receiver) {
            return ShadowScope.super.toRect($receiver);
        }

        @Deprecated
        /* JADX INFO: renamed from: toSize-XkaWNTQ, reason: not valid java name */
        public static long m4934toSizeXkaWNTQ(ShadowScope $this, long $receiver) {
            return ShadowScope.super.mo433toSizeXkaWNTQ($receiver);
        }

        @Deprecated
        /* JADX INFO: renamed from: toSp-0xMU5do, reason: not valid java name */
        public static long m4935toSp0xMU5do(ShadowScope $this, float $receiver) {
            return ShadowScope.super.mo434toSp0xMU5do($receiver);
        }

        @Deprecated
        /* JADX INFO: renamed from: toSp-kPz2Gy4, reason: not valid java name */
        public static long m4936toSpkPz2Gy4(ShadowScope $this, float $receiver) {
            return ShadowScope.super.mo435toSpkPz2Gy4($receiver);
        }

        @Deprecated
        /* JADX INFO: renamed from: toSp-kPz2Gy4, reason: not valid java name */
        public static long m4937toSpkPz2Gy4(ShadowScope $this, int $receiver) {
            return ShadowScope.super.mo436toSpkPz2Gy4($receiver);
        }
    }
}
