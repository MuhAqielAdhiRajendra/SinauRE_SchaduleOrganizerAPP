package androidx.compose.material3.internal.colorUtil;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Cam.android.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0017\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0001\u0018\u0000 \u001f2\u00020\u0001:\u0001\u001fBG\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003¢\u0006\u0004\b\u000b\u0010\fJ\u000e\u0010\u0018\u001a\u00020\u00032\u0006\u0010\u0019\u001a\u00020\u0000J\u0006\u0010\u001a\u001a\u00020\u001bJ\u000e\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u001eR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000eR\u001a\u0010\b\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u000e\"\u0004\b\u0014\u0010\u0015R\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u000eR\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u000e¨\u0006 "}, d2 = {"Landroidx/compose/material3/internal/colorUtil/Cam;", "", "hue", "", "chroma", "j", "m", "s", "jstar", "astar", "bstar", "<init>", "(FFFFFFFF)V", "getHue", "()F", "getChroma", "getJ", "getM", "getS", "getJstar", "setJstar", "(F)V", "getAstar", "getBstar", "distance", "other", "viewedInSrgb", "", "viewed", TypedValues.AttributesType.S_FRAME, "Landroidx/compose/material3/internal/colorUtil/Frame;", "Companion", "material3"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class Cam {
    private static final float CHROMA_SEARCH_ENDPOINT = 0.4f;
    private static final float DE_MAX = 1.0f;
    private static final float DL_MAX = 0.2f;
    private static final float LIGHTNESS_SEARCH_ENDPOINT = 0.01f;
    private final float astar;
    private final float bstar;
    private final float chroma;
    private final float hue;
    private final float j;
    private float jstar;
    private final float m;
    private final float s;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    public Cam(float hue, float chroma, float j, float m, float s, float jstar, float astar, float bstar) {
        this.hue = hue;
        this.chroma = chroma;
        this.j = j;
        this.m = m;
        this.s = s;
        this.jstar = jstar;
        this.astar = astar;
        this.bstar = bstar;
    }

    public final float getHue() {
        return this.hue;
    }

    public final float getChroma() {
        return this.chroma;
    }

    public final float getJ() {
        return this.j;
    }

    public final float getM() {
        return this.m;
    }

    public final float getS() {
        return this.s;
    }

    public final float getJstar() {
        return this.jstar;
    }

    public final void setJstar(float f) {
        this.jstar = f;
    }

    public final float getAstar() {
        return this.astar;
    }

    public final float getBstar() {
        return this.bstar;
    }

    public final float distance(Cam other) {
        float dJ = this.jstar - other.jstar;
        float dA = this.astar - other.astar;
        float dB = this.bstar - other.bstar;
        double dEPrime = Math.sqrt((dJ * dJ) + (dA * dA) + (dB * dB));
        double dE = Math.pow(dEPrime, 0.63d) * 1.41d;
        return (float) dE;
    }

    public final int viewedInSrgb() {
        return viewed(Frame.INSTANCE.getDefault());
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0029  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int viewed(androidx.compose.material3.internal.colorUtil.Frame r39) {
        /*
            Method dump skipped, instruction units count: 514
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.internal.colorUtil.Cam.viewed(androidx.compose.material3.internal.colorUtil.Frame):int");
    }

    /* JADX INFO: compiled from: Cam.android.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u0005J\u000e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\nJ\u0018\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J \u0010\u0014\u001a\u00020\u000f2\u0006\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\u0005H\u0002J(\u0010\u0018\u001a\u00020\u000f2\u0006\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J(\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\"\u0010\u0019\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u0005H\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Landroidx/compose/material3/internal/colorUtil/Cam$Companion;", "", "<init>", "()V", "DL_MAX", "", "DE_MAX", "CHROMA_SEARCH_ENDPOINT", "LIGHTNESS_SEARCH_ENDPOINT", "getInt", "", "hue", "chroma", "lstar", "fromInt", "Landroidx/compose/material3/internal/colorUtil/Cam;", "argb", "fromIntInFrame", TypedValues.AttributesType.S_FRAME, "Landroidx/compose/material3/internal/colorUtil/Frame;", "fromJch", "j", "c", "h", "fromJchInFrame", "findCamByJ", "material3"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final int getInt(float hue, float chroma, float lstar) {
            return getInt(hue, chroma, lstar, Frame.INSTANCE.getDefault());
        }

        public final Cam fromInt(int argb) {
            return fromIntInFrame(argb, Frame.INSTANCE.getDefault());
        }

        private final Cam fromIntInFrame(int argb, Frame frame) {
            float[] xyz = CamUtils.INSTANCE.xyzFromInt(argb);
            float[][] matrix = CamUtils.INSTANCE.getXYZ_TO_CAM16RGB();
            float rT = (xyz[0] * matrix[0][0]) + (xyz[1] * matrix[0][1]) + (xyz[2] * matrix[0][2]);
            float gT = (xyz[0] * matrix[1][0]) + (xyz[1] * matrix[1][1]) + (xyz[2] * matrix[1][2]);
            float bT = (xyz[0] * matrix[2][0]) + (xyz[1] * matrix[2][1]) + (xyz[2] * matrix[2][2]);
            float rD = frame.getRgbD()[0] * rT;
            float gD = frame.getRgbD()[1] * gT;
            float bD = frame.getRgbD()[2] * bT;
            float rAF = (float) Math.pow((frame.getFl() * Math.abs(rD)) / 100.0f, 0.41999998688697815d);
            float gAF = (float) Math.pow((frame.getFl() * Math.abs(gD)) / 100.0f, 0.41999998688697815d);
            float bAF = (float) Math.pow((frame.getFl() * Math.abs(bD)) / 100.0f, 0.41999998688697815d);
            float rA = ((Math.signum(rD) * 400.0f) * rAF) / (rAF + 27.13f);
            float gA = ((Math.signum(gD) * 400.0f) * gAF) / (gAF + 27.13f);
            float bA = ((Math.signum(bD) * 400.0f) * bAF) / (27.13f + bAF);
            float a = (((rA * 11.0f) + ((-12.0f) * gA)) + bA) / 11.0f;
            float b = ((rA + gA) - (bA * 2.0f)) / 9.0f;
            float u = (((rA * 20.0f) + (gA * 20.0f)) + (21.0f * bA)) / 20.0f;
            float p2 = (((40.0f * rA) + (gA * 20.0f)) + bA) / 20.0f;
            float atan2 = (float) Math.atan2(b, a);
            float atanDegrees = (atan2 * 180.0f) / 3.1415927f;
            float hue = atanDegrees < 0.0f ? atanDegrees + 360.0f : atanDegrees >= 360.0f ? atanDegrees - 360.0f : atanDegrees;
            float hueRadians = (hue * 3.1415927f) / 180.0f;
            float ac = frame.getNbb() * p2;
            float j = ((float) Math.pow(ac / frame.getAw(), frame.getC() * frame.getZ())) * 100.0f;
            float huePrime = ((double) hue) < 20.14d ? hue + 360.0f : hue;
            float eHue = (((float) Math.cos(((huePrime * 3.1415927f) / 180.0f) + 2.0f)) + 3.8f) * 0.25f;
            float p1 = 3846.1538f * eHue * frame.getNc() * frame.getNcb();
            float t = (p1 * ((float) Math.sqrt((a * a) + (b * b)))) / (u + 0.305f);
            float alpha = ((float) Math.pow(t, 0.8999999761581421d)) * ((float) Math.pow(1.64f - ((float) Math.pow(0.28999999165534973d, frame.getN())), 0.7300000190734863d));
            float c = alpha * ((float) Math.sqrt(j / 100.0f));
            float m = c * frame.getFlRoot();
            float s = ((float) Math.sqrt((frame.getC() * alpha) / (frame.getAw() + 4.0f))) * 50.0f;
            float jstar = (1.7f * j) / ((0.007f * j) + 1.0f);
            float mstar = ((float) Math.log((0.0228f * m) + 1.0f)) * 43.85965f;
            float astar = mstar * ((float) Math.cos(hueRadians));
            float bstar = mstar * ((float) Math.sin(hueRadians));
            return new Cam(hue, c, j, m, s, jstar, astar, bstar);
        }

        private final Cam fromJch(float j, float c, float h) {
            return fromJchInFrame(j, c, h, Frame.INSTANCE.getDefault());
        }

        private final Cam fromJchInFrame(float j, float c, float h, Frame frame) {
            float m = c * frame.getFlRoot();
            float alpha = c / ((float) Math.sqrt(((double) j) / 100.0d));
            float s = ((float) Math.sqrt((frame.getC() * alpha) / (frame.getAw() + 4.0f))) * 50.0f;
            float hueRadians = (3.1415927f * h) / 180.0f;
            float jstar = (1.7f * j) / ((0.007f * j) + 1.0f);
            float mstar = ((float) Math.log((((double) m) * 0.0228d) + 1.0d)) * 43.85965f;
            float astar = mstar * ((float) Math.cos(hueRadians));
            float bstar = mstar * ((float) Math.sin(hueRadians));
            return new Cam(h, c, j, m, s, jstar, astar, bstar);
        }

        private final int getInt(float hue, float chroma, float lstar, Frame frame) {
            if (Intrinsics.areEqual(frame, Frame.INSTANCE.getDefault())) {
                return HctSolver.INSTANCE.solveToInt(hue, chroma, lstar);
            }
            if (chroma < 1.0d || Math.round(lstar) <= 0.0d || Math.round(lstar) >= 100.0d) {
                return CamUtils.INSTANCE.intFromLstar(lstar);
            }
            float huePrime = 0.0f;
            if (hue >= 0.0f) {
                huePrime = Math.min(360.0f, hue);
            }
            float high = chroma;
            float mid = chroma;
            float low = 0.0f;
            boolean isFirstLoop = true;
            Cam answer = null;
            while (Math.abs(low - high) >= 0.4000000059604645d) {
                Cam possibleAnswer = findCamByJ(huePrime, mid, lstar);
                if (isFirstLoop) {
                    if (possibleAnswer != null) {
                        return possibleAnswer.viewed(frame);
                    }
                    isFirstLoop = false;
                    mid = low + ((high - low) / 2.0f);
                } else {
                    if (possibleAnswer == null) {
                        high = mid;
                    } else {
                        answer = possibleAnswer;
                        low = mid;
                    }
                    mid = low + ((high - low) / 2.0f);
                }
            }
            if (answer == null) {
                return CamUtils.INSTANCE.intFromLstar(lstar);
            }
            return answer.viewed(frame);
        }

        private final Cam findCamByJ(float hue, float chroma, float lstar) {
            float low = 0.0f;
            float high = 100.0f;
            float bestdL = 1000.0f;
            float bestdE = 1000.0f;
            Cam bestCam = null;
            while (Math.abs(low - high) > 0.009999999776482582d) {
                float mid = low + ((high - low) / 2.0f);
                Cam camBeforeClip = fromJch(mid, chroma, hue);
                int clipped = camBeforeClip.viewedInSrgb();
                float clippedLstar = CamUtils.INSTANCE.lstarFromInt(clipped);
                float dL = (float) Math.abs(lstar - clippedLstar);
                if (dL < 0.2f) {
                    Cam camClipped = fromInt(clipped);
                    float dE = camClipped.distance(fromJch(camClipped.getJ(), camClipped.getChroma(), hue));
                    if (dE <= 1.0f) {
                        bestdL = dL;
                        bestdE = dE;
                        bestCam = camClipped;
                    }
                }
                if (bestdL == 0.0f) {
                    if (bestdE == 0.0f) {
                        break;
                    }
                }
                if (clippedLstar < lstar) {
                    low = mid;
                } else {
                    high = mid;
                }
            }
            return bestCam;
        }
    }
}
