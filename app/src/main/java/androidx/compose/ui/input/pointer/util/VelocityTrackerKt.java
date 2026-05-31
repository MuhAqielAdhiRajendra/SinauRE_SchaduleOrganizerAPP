package androidx.compose.ui.input.pointer.util;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.app.NotificationCompat;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: VelocityTracker.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\\\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u0014\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\r\u001a\u0012\u0010\u0004\u001a\u00020\u0005*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b\u001a!\u0010\u0004\u001a\u00020\u0005*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\f\u001a1\u0010\r\u001a\u00020\u0005*\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u000e2\u0006\u0010\u0010\u001a\u00020\u00012\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0002¢\u0006\u0002\u0010\u0015\u001a2\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u00012\u0006\u0010\u001c\u001a\u00020\u00012\b\b\u0002\u0010\u001d\u001a\u00020\u0018H\u0000\u001a(\u0010\u001e\u001a\u00020\u00142\u0006\u0010\u001f\u001a\u00020\u00182\u0006\u0010\u0011\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u00012\u0006\u0010 \u001a\u00020!H\u0002\u001a\u0011\u0010\"\u001a\u00020\u00142\u0006\u0010#\u001a\u00020\u0014H\u0082\b\u001a\u0014\u0010%\u001a\u00020\u0014*\u00020\u00182\u0006\u0010&\u001a\u00020\u0018H\u0002\u001a\r\u0010'\u001a\u00020\u0014*\u00020\u0018H\u0082\b\u001a(\u0010(\u001a\f\u0012\b\u0012\u00060\u0018j\u0002`)0\u000e2\u0006\u0010*\u001a\u00020\u00012\u0006\u0010+\u001a\u00020\u0001H\u0082\b¢\u0006\u0002\u0010,\u001a,\u0010-\u001a\u00020\u0014*\f\u0012\u0004\u0012\u00020\u00180\u000ej\u0002`.2\u0006\u0010/\u001a\u00020\u00012\u0006\u00100\u001a\u00020\u0001H\u0082\n¢\u0006\u0002\u00101\u001a4\u0010\r\u001a\u00020\u0005*\f\u0012\u0004\u0012\u00020\u00180\u000ej\u0002`.2\u0006\u0010/\u001a\u00020\u00012\u0006\u00100\u001a\u00020\u00012\u0006\u00102\u001a\u00020\u0014H\u0082\n¢\u0006\u0002\u00103\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0016\u001a\u00020\u0014X\u0082T¢\u0006\u0002\n\u0000\"$\u00104\u001a\u00020!8\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b5\u00106\u001a\u0004\b7\u00108\"\u0004\b9\u0010:*\f\b\u0002\u0010$\"\u00020\u00182\u00020\u0018*\u0018\b\u0002\u0010(\"\b\u0012\u0004\u0012\u00020\u00180\u000e2\b\u0012\u0004\u0012\u00020\u00180\u000e¨\u0006;"}, d2 = {"AssumePointerMoveStoppedMilliseconds", "", "HistorySize", "HorizonMilliseconds", "addPointerInputChange", "", "Landroidx/compose/ui/input/pointer/util/VelocityTracker;", NotificationCompat.CATEGORY_EVENT, "Landroidx/compose/ui/input/pointer/PointerInputChange;", TypedValues.CycleType.S_WAVE_OFFSET, "Landroidx/compose/ui/geometry/Offset;", "addPointerInputChange-0AR0LA0", "(Landroidx/compose/ui/input/pointer/util/VelocityTracker;Landroidx/compose/ui/input/pointer/PointerInputChange;J)V", "set", "", "Landroidx/compose/ui/input/pointer/util/DataPointAtTime;", "index", "time", "", "dataPoint", "", "([Landroidx/compose/ui/input/pointer/util/DataPointAtTime;IJF)V", "DefaultWeight", "polyFitLeastSquares", "", "x", "y", "sampleCount", "degree", "coefficients", "calculateImpulseVelocity", "dataPoints", "isDataDifferential", "", "kineticEnergyToVelocity", "kineticEnergy", "Vector", "dot", "a", "norm", "Matrix", "Landroidx/compose/ui/input/pointer/util/Vector;", "rows", "cols", "(II)[[F", "get", "Landroidx/compose/ui/input/pointer/util/Matrix;", "row", "col", "([[FII)F", "value", "([[FIIF)V", "VelocityTrackerAddPointsFix", "getVelocityTrackerAddPointsFix$annotations", "()V", "getVelocityTrackerAddPointsFix", "()Z", "setVelocityTrackerAddPointsFix", "(Z)V", "ui"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class VelocityTrackerKt {
    private static final int AssumePointerMoveStoppedMilliseconds = 40;
    private static final float DefaultWeight = 1.0f;
    private static final int HistorySize = 20;
    private static final int HorizonMilliseconds = 100;
    private static boolean VelocityTrackerAddPointsFix = true;

    public static /* synthetic */ void getVelocityTrackerAddPointsFix$annotations() {
    }

    public static final void addPointerInputChange(VelocityTracker $this$addPointerInputChange, PointerInputChange event) {
        m6759addPointerInputChange0AR0LA0($this$addPointerInputChange, event, Offset.INSTANCE.m5084getZeroF1C5BW0());
    }

    /* JADX INFO: renamed from: addPointerInputChange-0AR0LA0, reason: not valid java name */
    public static final void m6759addPointerInputChange0AR0LA0(VelocityTracker $this$addPointerInputChange_u2d0AR0LA0, PointerInputChange event, long offset) {
        $this$addPointerInputChange_u2d0AR0LA0.getPlatformVelocityTracker().mo6746addPointerInputChangeUv8p0NA(event, offset);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void set(DataPointAtTime[] $this$set, int index, long time, float dataPoint) {
        DataPointAtTime currentEntry = $this$set[index];
        if (currentEntry == null) {
            $this$set[index] = new DataPointAtTime(time, dataPoint);
        } else {
            currentEntry.setTime(time);
            currentEntry.setDataPoint(dataPoint);
        }
    }

    public static /* synthetic */ float[] polyFitLeastSquares$default(float[] fArr, float[] fArr2, int i, int i2, float[] fArr3, int i3, Object obj) {
        if ((i3 & 16) != 0) {
            fArr3 = new float[RangesKt.coerceAtLeast(i2 + 1, 0)];
        }
        return polyFitLeastSquares(fArr, fArr2, i, i2, fArr3);
    }

    public static final float[] polyFitLeastSquares(float[] x, float[] y, int sampleCount, int degree, float[] coefficients) {
        if (degree < 1) {
            InlineClassHelperKt.throwIllegalArgumentException("The degree must be at positive integer");
        }
        if (sampleCount == 0) {
            InlineClassHelperKt.throwIllegalArgumentException("At least one point must be provided");
        }
        int truncatedDegree = degree >= sampleCount ? sampleCount - 1 : degree;
        int n = truncatedDegree + 1;
        float[][] a = new float[n][];
        for (int i = 0; i < n; i++) {
            a[i] = new float[sampleCount];
        }
        for (int h = 0; h < sampleCount; h++) {
            int col$iv = h;
            a[0][col$iv] = 1.0f;
            for (int i2 = 1; i2 < n; i2++) {
                int row$iv = i2 - 1;
                int col$iv2 = h;
                float value$iv = a[row$iv][col$iv2] * x[h];
                int row$iv2 = i2;
                int col$iv3 = h;
                a[row$iv2][col$iv3] = value$iv;
            }
        }
        float[][] q = new float[n][];
        for (int i3 = 0; i3 < n; i3++) {
            q[i3] = new float[sampleCount];
        }
        float[][] r = new float[n][];
        for (int i4 = 0; i4 < n; i4++) {
            r[i4] = new float[n];
        }
        int j = 0;
        while (j < n) {
            float[] w = q[j];
            ArraysKt.copyInto(a[j], w, 0, 0, sampleCount);
            for (int i5 = 0; i5 < j; i5++) {
                float[] z = q[i5];
                float dot = dot(w, z);
                for (int h2 = 0; h2 < sampleCount; h2++) {
                    w[h2] = w[h2] - (z[h2] * dot);
                }
            }
            float $this$fastCoerceAtLeast$iv = (float) Math.sqrt(dot(w, w));
            float minimumValue$iv = $this$fastCoerceAtLeast$iv >= 1.0E-6f ? $this$fastCoerceAtLeast$iv : 1.0E-6f;
            float inverseNorm = 1.0f / minimumValue$iv;
            for (int h3 = 0; h3 < sampleCount; h3++) {
                w[h3] = w[h3] * inverseNorm;
            }
            float[] v = r[j];
            int i6 = 0;
            while (i6 < n) {
                v[i6] = i6 < j ? 0.0f : dot(w, a[i6]);
                i6++;
            }
            j++;
        }
        for (int i7 = n - 1; -1 < i7; i7--) {
            float c = dot(q[i7], y);
            float[] ri = r[i7];
            int j2 = n - 1;
            int i8 = i7 + 1;
            if (i8 <= j2) {
                while (true) {
                    c -= ri[j2] * coefficients[j2];
                    if (j2 != i8) {
                        j2--;
                    }
                }
            }
            coefficients[i7] = c / ri[i7];
        }
        return coefficients;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float calculateImpulseVelocity(float[] dataPoints, float[] time, int sampleCount, boolean isDataDifferential) {
        float work = 0.0f;
        int start = sampleCount - 1;
        float nextTime = time[start];
        for (int i = start; i > 0; i--) {
            float currentTime = nextTime;
            nextTime = time[i - 1];
            if (!(currentTime == nextTime)) {
                float dataPointsDelta = isDataDifferential ? -dataPoints[i - 1] : dataPoints[i] - dataPoints[i - 1];
                float vCurr = dataPointsDelta / (currentTime - nextTime);
                float kineticEnergy$iv = work;
                float vPrev = Math.signum(kineticEnergy$iv) * ((float) Math.sqrt(2.0f * Math.abs(kineticEnergy$iv)));
                work += (vCurr - vPrev) * Math.abs(vCurr);
                if (i == start) {
                    work *= 0.5f;
                }
            }
        }
        float kineticEnergy$iv2 = work;
        return Math.signum(kineticEnergy$iv2) * ((float) Math.sqrt(2.0f * Math.abs(kineticEnergy$iv2)));
    }

    private static final float kineticEnergyToVelocity(float kineticEnergy) {
        return Math.signum(kineticEnergy) * ((float) Math.sqrt(2.0f * Math.abs(kineticEnergy)));
    }

    private static final float dot(float[] $this$dot, float[] a) {
        float result = 0.0f;
        int length = $this$dot.length;
        for (int i = 0; i < length; i++) {
            result += $this$dot[i] * a[i];
        }
        return result;
    }

    private static final float norm(float[] $this$norm) {
        return (float) Math.sqrt(dot($this$norm, $this$norm));
    }

    private static final float[][] Matrix(int rows, int cols) {
        float[][] fArr = new float[rows][];
        for (int i = 0; i < rows; i++) {
            fArr[i] = new float[cols];
        }
        return fArr;
    }

    private static final float get(float[][] $this$get, int row, int col) {
        return $this$get[row][col];
    }

    private static final void set(float[][] $this$set, int row, int col, float value) {
        $this$set[row][col] = value;
    }

    public static final boolean getVelocityTrackerAddPointsFix() {
        return VelocityTrackerAddPointsFix;
    }

    public static final void setVelocityTrackerAddPointsFix(boolean z) {
        VelocityTrackerAddPointsFix = z;
    }
}
