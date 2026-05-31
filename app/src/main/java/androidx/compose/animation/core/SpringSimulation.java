package androidx.compose.animation.core;

import kotlin.Metadata;

/* JADX INFO: compiled from: SpringSimulation.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0010\u0006\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\b\u0001\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0016\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u0003J'\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0017\u001a\u00020\u0018H\u0000¢\u0006\u0004\b\u0019\u0010\u001aR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\u0005R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010\f\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u00038F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\r\u0010\u0007\"\u0004\b\u000e\u0010\u0005R$\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u0003@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0007\"\u0004\b\u0011\u0010\u0005¨\u0006\u001b"}, d2 = {"Landroidx/compose/animation/core/SpringSimulation;", "", "finalPosition", "", "<init>", "(F)V", "getFinalPosition", "()F", "setFinalPosition", "naturalFreq", "", "value", "stiffness", "getStiffness", "setStiffness", "dampingRatio", "getDampingRatio", "setDampingRatio", "getAcceleration", "lastDisplacement", "lastVelocity", "updateValues", "Landroidx/compose/animation/core/Motion;", "timeElapsed", "", "updateValues-IJZedt4$animation_core", "(FFJ)J", "animation-core"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class SpringSimulation {
    public static final int $stable = 8;
    private float finalPosition;
    private double naturalFreq = Math.sqrt(50.0d);
    private float dampingRatio = 1.0f;

    public SpringSimulation(float finalPosition) {
        this.finalPosition = finalPosition;
    }

    public final float getFinalPosition() {
        return this.finalPosition;
    }

    public final void setFinalPosition(float f) {
        this.finalPosition = f;
    }

    public final void setStiffness(float value) {
        if (getStiffness() <= 0.0f) {
            PreconditionsKt.throwIllegalArgumentException("Spring stiffness constant must be positive.");
        }
        this.naturalFreq = Math.sqrt(value);
    }

    public final float getStiffness() {
        return (float) (this.naturalFreq * this.naturalFreq);
    }

    public final float getDampingRatio() {
        return this.dampingRatio;
    }

    public final void setDampingRatio(float value) {
        if (value < 0.0f) {
            PreconditionsKt.throwIllegalArgumentException("Damping ratio must be non-negative");
        }
        this.dampingRatio = value;
    }

    public final float getAcceleration(float lastDisplacement, float lastVelocity) {
        float adjustedDisplacement = lastDisplacement - this.finalPosition;
        double k = this.naturalFreq * this.naturalFreq;
        double c = this.naturalFreq * 2.0d * ((double) this.dampingRatio);
        return (float) (((-k) * ((double) adjustedDisplacement)) - (((double) lastVelocity) * c));
    }

    /* JADX INFO: renamed from: updateValues-IJZedt4$animation_core, reason: not valid java name */
    public final long m222updateValuesIJZedt4$animation_core(float lastDisplacement, float lastVelocity, long timeElapsed) {
        double displacement;
        double currentVelocity;
        float adjustedDisplacement = lastDisplacement - this.finalPosition;
        double deltaT = timeElapsed / 1000.0d;
        double dampingRatioSquared = ((double) this.dampingRatio) * ((double) this.dampingRatio);
        double r = ((double) (-this.dampingRatio)) * this.naturalFreq;
        if (this.dampingRatio > 1.0f) {
            double s = this.naturalFreq * Math.sqrt(dampingRatioSquared - 1.0d);
            double gammaPlus = r + s;
            double gammaMinus = r - s;
            double coeffB = ((((double) adjustedDisplacement) * gammaMinus) - ((double) lastVelocity)) / (gammaMinus - gammaPlus);
            double coeffA = ((double) adjustedDisplacement) - coeffB;
            displacement = (Math.exp(gammaMinus * deltaT) * coeffA) + (Math.exp(gammaPlus * deltaT) * coeffB);
            currentVelocity = (coeffA * gammaMinus * Math.exp(gammaMinus * deltaT)) + (coeffB * gammaPlus * Math.exp(gammaPlus * deltaT));
        } else {
            boolean z = this.dampingRatio == 1.0f;
            double d = this.naturalFreq;
            if (z) {
                double coeffB2 = ((double) lastVelocity) + (((double) adjustedDisplacement) * d);
                double nFdT = (-this.naturalFreq) * deltaT;
                displacement = (((double) adjustedDisplacement) + (coeffB2 * deltaT)) * Math.exp(nFdT);
                currentVelocity = ((((double) adjustedDisplacement) + (coeffB2 * deltaT)) * Math.exp(nFdT) * (-this.naturalFreq)) + (Math.exp(nFdT) * coeffB2);
            } else {
                double dampedFreq = Math.sqrt(1.0d - dampingRatioSquared) * d;
                double sinCoeff = (1.0d / dampedFreq) * ((((double) adjustedDisplacement) * (-r)) + ((double) lastVelocity));
                double dFdT = dampedFreq * deltaT;
                displacement = Math.exp(r * deltaT) * ((((double) adjustedDisplacement) * Math.cos(dFdT)) + (Math.sin(dFdT) * sinCoeff));
                currentVelocity = (displacement * r) + (Math.exp(r * deltaT) * ((((double) adjustedDisplacement) * (-dampedFreq) * Math.sin(dFdT)) + (dampedFreq * sinCoeff * Math.cos(dFdT))));
            }
        }
        float newValue = (float) (displacement + ((double) this.finalPosition));
        float newVelocity = (float) currentVelocity;
        long v1$iv$iv = Float.floatToRawIntBits(newValue);
        long v1$iv$iv2 = Float.floatToRawIntBits(newVelocity);
        long v2$iv$iv = (v1$iv$iv << 32) | (v1$iv$iv2 & 4294967295L);
        return Motion.m213constructorimpl(v2$iv$iv);
    }
}
