package androidx.compose.ui.graphics.drawscope;

import androidx.compose.ui.geometry.SizeKt;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.Path;
import kotlin.Metadata;

/* JADX INFO: compiled from: CanvasDrawScope.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0002¨\u0006\u0003"}, d2 = {"asDrawTransform", "Landroidx/compose/ui/graphics/drawscope/DrawTransform;", "Landroidx/compose/ui/graphics/drawscope/DrawContext;", "ui-graphics"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class CanvasDrawScopeKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final DrawTransform asDrawTransform(final DrawContext $this$asDrawTransform) {
        return new DrawTransform() { // from class: androidx.compose.ui.graphics.drawscope.CanvasDrawScopeKt.asDrawTransform.1
            @Override // androidx.compose.ui.graphics.drawscope.DrawTransform
            /* JADX INFO: renamed from: getSize-NH-jbRc, reason: not valid java name */
            public long mo5813getSizeNHjbRc() {
                return $this$asDrawTransform.mo5808getSizeNHjbRc();
            }

            @Override // androidx.compose.ui.graphics.drawscope.DrawTransform
            /* JADX INFO: renamed from: getCenter-F1C5BW0, reason: not valid java name */
            public long mo5812getCenterF1C5BW0() {
                return SizeKt.m5147getCenteruvyYCjk(mo5813getSizeNHjbRc());
            }

            /* JADX WARN: Removed duplicated region for block: B:7:0x0088  */
            @Override // androidx.compose.ui.graphics.drawscope.DrawTransform
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public void inset(float r25, float r26, float r27, float r28) {
                /*
                    r24 = this;
                    r0 = r24
                    r1 = r25
                    r2 = r26
                    androidx.compose.ui.graphics.drawscope.DrawContext r3 = r1
                    androidx.compose.ui.graphics.Canvas r3 = r3.getCanvas()
                    androidx.compose.ui.graphics.drawscope.DrawContext r4 = r1
                    r5 = 0
                    long r6 = r0.mo5813getSizeNHjbRc()
                    r8 = 0
                    r9 = r6
                    r11 = 0
                    r12 = 32
                    long r13 = r9 >> r12
                    int r13 = (int) r13
                    r14 = 0
                    float r13 = java.lang.Float.intBitsToFloat(r13)
                    float r6 = r1 + r27
                    float r13 = r13 - r6
                    long r6 = r0.mo5813getSizeNHjbRc()
                    r8 = 0
                    r9 = r6
                    r11 = 0
                    r14 = 4294967295(0xffffffff, double:2.1219957905E-314)
                    r16 = r12
                    r17 = r13
                    long r12 = r9 & r14
                    int r12 = (int) r12
                    r13 = 0
                    float r12 = java.lang.Float.intBitsToFloat(r12)
                    float r6 = r2 + r28
                    float r12 = r12 - r6
                    r6 = 0
                    r7 = r12
                    r8 = r17
                    r9 = 0
                    int r10 = java.lang.Float.floatToRawIntBits(r8)
                    long r10 = (long) r10
                    int r13 = java.lang.Float.floatToRawIntBits(r7)
                    r18 = r14
                    long r14 = (long) r13
                    long r20 = r10 << r16
                    long r22 = r14 & r18
                    long r7 = r20 | r22
                    long r6 = androidx.compose.ui.geometry.Size.m5128constructorimpl(r7)
                    r8 = r6
                    r10 = 0
                    r11 = r8
                    r13 = 0
                    long r14 = r11 >> r16
                    int r14 = (int) r14
                    r15 = 0
                    float r14 = java.lang.Float.intBitsToFloat(r14)
                    r8 = 0
                    int r9 = (r14 > r8 ? 1 : (r14 == r8 ? 0 : -1))
                    if (r9 < 0) goto L88
                    r9 = r6
                    r11 = 0
                    r12 = r9
                    r14 = 0
                    r15 = r8
                    r16 = r9
                    long r8 = r12 & r18
                    int r8 = (int) r8
                    r9 = 0
                    float r8 = java.lang.Float.intBitsToFloat(r8)
                    int r8 = (r8 > r15 ? 1 : (r8 == r15 ? 0 : -1))
                    if (r8 < 0) goto L88
                    r8 = 1
                    goto L89
                L88:
                    r8 = 0
                L89:
                    r9 = 0
                    if (r8 != 0) goto L93
                    r10 = 0
                    java.lang.String r10 = "Width and height must be greater than or equal to zero"
                    androidx.compose.ui.graphics.InlineClassHelperKt.throwIllegalArgumentException(r10)
                L93:
                    r4.mo5809setSizeuvyYCjk(r6)
                    r3.translate(r1, r2)
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.graphics.drawscope.CanvasDrawScopeKt.AnonymousClass1.inset(float, float, float, float):void");
            }

            @Override // androidx.compose.ui.graphics.drawscope.DrawTransform
            /* JADX INFO: renamed from: clipRect-N_I0leg, reason: not valid java name */
            public void mo5811clipRectN_I0leg(float left, float top, float right, float bottom, int clipOp) {
                $this$asDrawTransform.getCanvas().mo5163clipRectN_I0leg(left, top, right, bottom, clipOp);
            }

            @Override // androidx.compose.ui.graphics.drawscope.DrawTransform
            /* JADX INFO: renamed from: clipPath-mtrdD-E, reason: not valid java name */
            public void mo5810clipPathmtrdDE(Path path, int clipOp) {
                $this$asDrawTransform.getCanvas().mo5162clipPathmtrdDE(path, clipOp);
            }

            @Override // androidx.compose.ui.graphics.drawscope.DrawTransform
            public void translate(float left, float top) {
                $this$asDrawTransform.getCanvas().translate(left, top);
            }

            @Override // androidx.compose.ui.graphics.drawscope.DrawTransform
            /* JADX INFO: renamed from: rotate-Uv8p0NA, reason: not valid java name */
            public void mo5814rotateUv8p0NA(float degrees, long pivot) {
                Canvas $this$rotate_Uv8p0NA_u24lambda_u241 = $this$asDrawTransform.getCanvas();
                int bits$iv$iv$iv = (int) (pivot >> 32);
                int bits$iv$iv$iv2 = (int) (pivot & 4294967295L);
                $this$rotate_Uv8p0NA_u24lambda_u241.translate(Float.intBitsToFloat(bits$iv$iv$iv), Float.intBitsToFloat(bits$iv$iv$iv2));
                $this$rotate_Uv8p0NA_u24lambda_u241.rotate(degrees);
                int bits$iv$iv$iv3 = (int) (pivot >> 32);
                int bits$iv$iv$iv4 = (int) (pivot & 4294967295L);
                $this$rotate_Uv8p0NA_u24lambda_u241.translate(-Float.intBitsToFloat(bits$iv$iv$iv3), -Float.intBitsToFloat(bits$iv$iv$iv4));
            }

            @Override // androidx.compose.ui.graphics.drawscope.DrawTransform
            /* JADX INFO: renamed from: scale-0AR0LA0, reason: not valid java name */
            public void mo5815scale0AR0LA0(float scaleX, float scaleY, long pivot) {
                Canvas $this$scale_0AR0LA0_u24lambda_u242 = $this$asDrawTransform.getCanvas();
                int bits$iv$iv$iv = (int) (pivot >> 32);
                int bits$iv$iv$iv2 = (int) (pivot & 4294967295L);
                $this$scale_0AR0LA0_u24lambda_u242.translate(Float.intBitsToFloat(bits$iv$iv$iv), Float.intBitsToFloat(bits$iv$iv$iv2));
                $this$scale_0AR0LA0_u24lambda_u242.scale(scaleX, scaleY);
                int bits$iv$iv$iv3 = (int) (pivot >> 32);
                int bits$iv$iv$iv4 = (int) (4294967295L & pivot);
                $this$scale_0AR0LA0_u24lambda_u242.translate(-Float.intBitsToFloat(bits$iv$iv$iv3), -Float.intBitsToFloat(bits$iv$iv$iv4));
            }

            @Override // androidx.compose.ui.graphics.drawscope.DrawTransform
            /* JADX INFO: renamed from: transform-58bKbWc, reason: not valid java name */
            public void mo5816transform58bKbWc(float[] matrix) {
                $this$asDrawTransform.getCanvas().mo5164concat58bKbWc(matrix);
            }
        };
    }
}
