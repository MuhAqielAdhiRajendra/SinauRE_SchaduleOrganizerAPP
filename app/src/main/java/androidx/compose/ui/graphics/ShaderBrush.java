package androidx.compose.ui.graphics;

import android.graphics.Shader;
import androidx.compose.ui.geometry.Size;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Brush.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\b'\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\u0011\u001a\u00060\u0012j\u0002`\u00132\u0006\u0010\u0014\u001a\u00020\u0007H&¢\u0006\u0004\b\u0015\u0010\u0016J\b\u0010\u0017\u001a\u00020\u0005H\u0002J%\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0014\u001a\u00020\u00072\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d¢\u0006\u0004\b\u001e\u0010\u001fR\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\bR*\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\u0010\t\u001a\u0004\u0018\u00010\n@FX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0010\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006 "}, d2 = {"Landroidx/compose/ui/graphics/ShaderBrush;", "Landroidx/compose/ui/graphics/Brush;", "<init>", "()V", "internalTransformShader", "Landroidx/compose/ui/graphics/TransformShader;", "createdSize", "Landroidx/compose/ui/geometry/Size;", "J", "value", "Landroidx/compose/ui/graphics/Matrix;", "transform", "getTransform-3i98HWw", "()[F", "setTransform-Q8lPUPs", "([F)V", "[F", "createShader", "Landroid/graphics/Shader;", "Landroidx/compose/ui/graphics/Shader;", "size", "createShader-uvyYCjk", "(J)Landroid/graphics/Shader;", "obtainTransformShader", "applyTo", "", "p", "Landroidx/compose/ui/graphics/Paint;", "alpha", "", "applyTo-Pq9zytI", "(JLandroidx/compose/ui/graphics/Paint;F)V", "ui-graphics"}, k = 1, mv = {2, 1, 0}, xi = 48)
public abstract class ShaderBrush extends Brush {
    public static final int $stable = 0;
    private long createdSize;
    private TransformShader internalTransformShader;
    private float[] transform;

    /* JADX INFO: renamed from: createShader-uvyYCjk */
    public abstract Shader mo5282createShaderuvyYCjk(long size);

    public ShaderBrush() {
        super(null);
        this.createdSize = Size.INSTANCE.m5145getUnspecifiedNHjbRc();
    }

    /* JADX INFO: renamed from: getTransform-3i98HWw, reason: not valid java name and from getter */
    public final float[] getTransform() {
        return this.transform;
    }

    /* JADX INFO: renamed from: setTransform-Q8lPUPs, reason: not valid java name */
    public final void m5643setTransformQ8lPUPs(float[] value) {
        this.transform = value;
        TransformShader transformShader = this.internalTransformShader;
        if (transformShader != null) {
            transformShader.m5727transformQ8lPUPs(value);
        }
    }

    private final TransformShader obtainTransformShader() {
        TransformShader transformShader = this.internalTransformShader;
        if (transformShader != null) {
            return transformShader;
        }
        TransformShader it = new TransformShader();
        this.internalTransformShader = it;
        return it;
    }

    @Override // androidx.compose.ui.graphics.Brush
    /* JADX INFO: renamed from: applyTo-Pq9zytI */
    public final void mo5258applyToPq9zytI(long size, Paint p, float alpha) {
        TransformShader transformShader = this.internalTransformShader;
        if (transformShader == null || !Size.m5133equalsimpl0(this.createdSize, size)) {
            if (Size.m5139isEmptyimpl(size)) {
                transformShader = null;
                this.internalTransformShader = null;
                this.createdSize = Size.INSTANCE.m5145getUnspecifiedNHjbRc();
            } else {
                TransformShader $this$applyTo_Pq9zytI_u24lambda_u240 = obtainTransformShader();
                if (this.transform != null) {
                    $this$applyTo_Pq9zytI_u24lambda_u240.m5727transformQ8lPUPs(this.transform);
                }
                $this$applyTo_Pq9zytI_u24lambda_u240.setShader(mo5282createShaderuvyYCjk(size));
                transformShader = $this$applyTo_Pq9zytI_u24lambda_u240;
                this.internalTransformShader = transformShader;
                this.createdSize = size;
            }
        }
        if (!Color.m5314equalsimpl0(p.mo5183getColor0d7_KjU(), Color.INSTANCE.m5339getBlack0d7_KjU())) {
            p.mo5189setColor8_81llA(Color.INSTANCE.m5339getBlack0d7_KjU());
        }
        if (!Intrinsics.areEqual(p.getShader(), transformShader != null ? transformShader.getShader() : null)) {
            p.setShader(transformShader != null ? transformShader.getShader() : null);
        }
        if (!(p.getAlpha() == alpha)) {
            p.setAlpha(alpha);
        }
    }
}
