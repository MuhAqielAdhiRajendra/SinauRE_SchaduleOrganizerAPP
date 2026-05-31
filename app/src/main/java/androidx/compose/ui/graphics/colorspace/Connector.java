package androidx.compose.ui.graphics.colorspace;

import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Connector.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0014\n\u0002\b\u000b\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0017\u0018\u0000  2\u00020\u0001:\u0002\u001f B;\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u000b\u0010\fB!\b\u0010\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\r\u001a\u00020\b¢\u0006\u0004\b\u000b\u0010\u000eJ \u0010\t\u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u0016H\u0007J\u0012\u0010\t\u001a\u00020\n2\b\b\u0001\u0010\u0019\u001a\u00020\nH\u0017J\u0017\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001bH\u0010¢\u0006\u0004\b\u001d\u0010\u001eR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u000e\u0010\u0005\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0013\u0010\u0007\u001a\u00020\b¢\u0006\n\n\u0002\u0010\u0014\u001a\u0004\b\u0012\u0010\u0013R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Landroidx/compose/ui/graphics/colorspace/Connector;", "", "source", "Landroidx/compose/ui/graphics/colorspace/ColorSpace;", "destination", "transformSource", "transformDestination", "renderIntent", "Landroidx/compose/ui/graphics/colorspace/RenderIntent;", "transform", "", "<init>", "(Landroidx/compose/ui/graphics/colorspace/ColorSpace;Landroidx/compose/ui/graphics/colorspace/ColorSpace;Landroidx/compose/ui/graphics/colorspace/ColorSpace;Landroidx/compose/ui/graphics/colorspace/ColorSpace;I[FLkotlin/jvm/internal/DefaultConstructorMarker;)V", "intent", "(Landroidx/compose/ui/graphics/colorspace/ColorSpace;Landroidx/compose/ui/graphics/colorspace/ColorSpace;ILkotlin/jvm/internal/DefaultConstructorMarker;)V", "getSource", "()Landroidx/compose/ui/graphics/colorspace/ColorSpace;", "getDestination", "getRenderIntent-uksYyKA", "()I", "I", "r", "", "g", "b", "v", "transformToColor", "Landroidx/compose/ui/graphics/Color;", TypedValues.Custom.S_COLOR, "transformToColor-l2rxGTc$ui_graphics", "(J)J", "RgbConnector", "Companion", "ui-graphics"}, k = 1, mv = {2, 1, 0}, xi = 48)
public class Connector {
    private final ColorSpace destination;
    private final int renderIntent;
    private final ColorSpace source;
    private final float[] transform;
    private final ColorSpace transformDestination;
    private final ColorSpace transformSource;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    public /* synthetic */ Connector(ColorSpace colorSpace, ColorSpace colorSpace2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(colorSpace, colorSpace2, i);
    }

    public /* synthetic */ Connector(ColorSpace colorSpace, ColorSpace colorSpace2, ColorSpace colorSpace3, ColorSpace colorSpace4, int i, float[] fArr, DefaultConstructorMarker defaultConstructorMarker) {
        this(colorSpace, colorSpace2, colorSpace3, colorSpace4, i, fArr);
    }

    private Connector(ColorSpace source, ColorSpace destination, ColorSpace transformSource, ColorSpace transformDestination, int renderIntent, float[] transform) {
        this.source = source;
        this.destination = destination;
        this.transformSource = transformSource;
        this.transformDestination = transformDestination;
        this.renderIntent = renderIntent;
        this.transform = transform;
    }

    public final ColorSpace getSource() {
        return this.source;
    }

    public final ColorSpace getDestination() {
        return this.destination;
    }

    /* JADX INFO: renamed from: getRenderIntent-uksYyKA, reason: not valid java name and from getter */
    public final int getRenderIntent() {
        return this.renderIntent;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    private Connector(ColorSpace source, ColorSpace destination, int intent) {
        ColorSpace colorSpaceAdapt$default;
        ColorSpace colorSpaceAdapt$default2 = ColorModel.m5743equalsimpl0(source.getModel(), ColorModel.INSTANCE.m5750getRgbxdoWZVw()) ? ColorSpaceKt.adapt$default(source, Illuminant.INSTANCE.getD50(), null, 2, null) : source;
        if (ColorModel.m5743equalsimpl0(destination.getModel(), ColorModel.INSTANCE.m5750getRgbxdoWZVw())) {
            colorSpaceAdapt$default = ColorSpaceKt.adapt$default(destination, Illuminant.INSTANCE.getD50(), null, 2, null);
        } else {
            colorSpaceAdapt$default = destination;
        }
        this(source, destination, colorSpaceAdapt$default2, colorSpaceAdapt$default, intent, INSTANCE.m5760computeTransformYBCOT_4(source, destination, intent), null);
    }

    public final float[] transform(float r, float g, float b) {
        return transform(new float[]{r, g, b});
    }

    public float[] transform(float[] v) {
        float[] xyz = this.transformSource.toXyz(v);
        if (this.transform != null) {
            xyz[0] = xyz[0] * this.transform[0];
            xyz[1] = xyz[1] * this.transform[1];
            xyz[2] = xyz[2] * this.transform[2];
        }
        return this.transformDestination.fromXyz(xyz);
    }

    /* JADX INFO: renamed from: transformToColor-l2rxGTc$ui_graphics, reason: not valid java name */
    public long mo5758transformToColorl2rxGTc$ui_graphics(long color) {
        float x;
        float x2;
        float y;
        float r = Color.m5319getRedimpl(color);
        float g = Color.m5318getGreenimpl(color);
        float b = Color.m5316getBlueimpl(color);
        float fM5315getAlphaimpl = Color.m5315getAlphaimpl(color);
        long packed = this.transformSource.toXy$ui_graphics(r, g, b);
        int bits$iv$iv = (int) (packed >> 32);
        float x3 = Float.intBitsToFloat(bits$iv$iv);
        int bits$iv$iv2 = (int) (4294967295L & packed);
        float y2 = Float.intBitsToFloat(bits$iv$iv2);
        float z = this.transformSource.toZ$ui_graphics(r, g, b);
        if (this.transform != null) {
            float x4 = x3 * this.transform[0];
            x = x4;
            x2 = y2 * this.transform[1];
            y = z * this.transform[2];
        } else {
            x = x3;
            x2 = y2;
            y = z;
        }
        return this.transformDestination.mo5753xyzaToColorJlNiLsg$ui_graphics(x, x2, y, fM5315getAlphaimpl, this.destination);
    }

    /* JADX INFO: compiled from: Connector.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0014\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\b\u0001\u0018\u00002\u00020\u0001B!\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\nH\u0016J\u0017\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000eH\u0010¢\u0006\u0004\b\u0010\u0010\u0011J'\u0010\u0012\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u0015\u0010\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Landroidx/compose/ui/graphics/colorspace/Connector$RgbConnector;", "Landroidx/compose/ui/graphics/colorspace/Connector;", "mSource", "Landroidx/compose/ui/graphics/colorspace/Rgb;", "mDestination", "intent", "Landroidx/compose/ui/graphics/colorspace/RenderIntent;", "<init>", "(Landroidx/compose/ui/graphics/colorspace/Rgb;Landroidx/compose/ui/graphics/colorspace/Rgb;ILkotlin/jvm/internal/DefaultConstructorMarker;)V", "mTransform", "", "transform", "v", "transformToColor", "Landroidx/compose/ui/graphics/Color;", TypedValues.Custom.S_COLOR, "transformToColor-l2rxGTc$ui_graphics", "(J)J", "computeTransform", "source", "destination", "computeTransform-YBCOT_4", "(Landroidx/compose/ui/graphics/colorspace/Rgb;Landroidx/compose/ui/graphics/colorspace/Rgb;I)[F", "ui-graphics"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class RgbConnector extends Connector {
        public static final int $stable = 8;
        private final Rgb mDestination;
        private final Rgb mSource;
        private final float[] mTransform;

        public /* synthetic */ RgbConnector(Rgb rgb, Rgb rgb2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(rgb, rgb2, i);
        }

        private RgbConnector(Rgb mSource, Rgb mDestination, int intent) {
            super(mSource, mDestination, mSource, mDestination, intent, null, null);
            this.mSource = mSource;
            this.mDestination = mDestination;
            this.mTransform = m5761computeTransformYBCOT_4(this.mSource, this.mDestination, intent);
        }

        @Override // androidx.compose.ui.graphics.colorspace.Connector
        public float[] transform(float[] v) {
            v[0] = (float) this.mSource.getEotfFunc().invoke(v[0]);
            v[1] = (float) this.mSource.getEotfFunc().invoke(v[1]);
            v[2] = (float) this.mSource.getEotfFunc().invoke(v[2]);
            ColorSpaceKt.mul3x3Float3(this.mTransform, v);
            v[0] = (float) this.mDestination.getOetfFunc().invoke(v[0]);
            v[1] = (float) this.mDestination.getOetfFunc().invoke(v[1]);
            v[2] = (float) this.mDestination.getOetfFunc().invoke(v[2]);
            return v;
        }

        @Override // androidx.compose.ui.graphics.colorspace.Connector
        /* JADX INFO: renamed from: transformToColor-l2rxGTc$ui_graphics */
        public long mo5758transformToColorl2rxGTc$ui_graphics(long color) {
            float r = Color.m5319getRedimpl(color);
            float g = Color.m5318getGreenimpl(color);
            float b = Color.m5316getBlueimpl(color);
            float a = Color.m5315getAlphaimpl(color);
            float v0 = (float) this.mSource.getEotfFunc().invoke(r);
            float v1 = (float) this.mSource.getEotfFunc().invoke(g);
            float v2 = (float) this.mSource.getEotfFunc().invoke(b);
            float[] lhs$iv = this.mTransform;
            float v01 = (lhs$iv[0] * v0) + (lhs$iv[3] * v1) + (lhs$iv[6] * v2);
            float[] lhs$iv2 = this.mTransform;
            float v11 = (lhs$iv2[1] * v0) + (lhs$iv2[4] * v1) + (lhs$iv2[7] * v2);
            float[] lhs$iv3 = this.mTransform;
            float v21 = (lhs$iv3[2] * v0) + (lhs$iv3[5] * v1) + (lhs$iv3[8] * v2);
            float v02 = (float) this.mDestination.getOetfFunc().invoke(v01);
            float v12 = (float) this.mDestination.getOetfFunc().invoke(v11);
            float v22 = (float) this.mDestination.getOetfFunc().invoke(v21);
            return ColorKt.Color(v02, v12, v22, a, this.mDestination);
        }

        /* JADX INFO: renamed from: computeTransform-YBCOT_4, reason: not valid java name */
        private final float[] m5761computeTransformYBCOT_4(Rgb source, Rgb destination, int intent) {
            if (ColorSpaceKt.compare(source.getWhitePoint(), destination.getWhitePoint())) {
                return ColorSpaceKt.mul3x3(destination.getInverseTransform(), source.getTransform());
            }
            float[] transform = source.getTransform();
            float[] inverseTransform = destination.getInverseTransform();
            float[] srcXYZ = source.getWhitePoint().toXyz$ui_graphics();
            float[] dstXYZ = destination.getWhitePoint().toXyz$ui_graphics();
            if (!ColorSpaceKt.compare(source.getWhitePoint(), Illuminant.INSTANCE.getD50())) {
                float[] srcAdaptation = ColorSpaceKt.chromaticAdaptation(Adaptation.INSTANCE.getBradford().getTransform(), srcXYZ, Illuminant.INSTANCE.newD50Xyz$ui_graphics());
                transform = ColorSpaceKt.mul3x3(srcAdaptation, source.getTransform());
            }
            if (!ColorSpaceKt.compare(destination.getWhitePoint(), Illuminant.INSTANCE.getD50())) {
                float[] dstAdaptation = ColorSpaceKt.chromaticAdaptation(Adaptation.INSTANCE.getBradford().getTransform(), dstXYZ, Illuminant.INSTANCE.newD50Xyz$ui_graphics());
                inverseTransform = ColorSpaceKt.inverse3x3(ColorSpaceKt.mul3x3(dstAdaptation, destination.getTransform()));
            }
            if (RenderIntent.m5766equalsimpl0(intent, RenderIntent.INSTANCE.m5770getAbsoluteuksYyKA())) {
                transform = ColorSpaceKt.mul3x3Diag(new float[]{srcXYZ[0] / dstXYZ[0], srcXYZ[1] / dstXYZ[1], srcXYZ[2] / dstXYZ[2]}, transform);
            }
            return ColorSpaceKt.mul3x3(inverseTransform, transform);
        }
    }

    /* JADX INFO: compiled from: Connector.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0080\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J)\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\nH\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u0015\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0006\u001a\u00020\u0007H\u0000¢\u0006\u0002\b\u000f¨\u0006\u0010"}, d2 = {"Landroidx/compose/ui/graphics/colorspace/Connector$Companion;", "", "<init>", "()V", "computeTransform", "", "source", "Landroidx/compose/ui/graphics/colorspace/ColorSpace;", "destination", "intent", "Landroidx/compose/ui/graphics/colorspace/RenderIntent;", "computeTransform-YBCOT_4", "(Landroidx/compose/ui/graphics/colorspace/ColorSpace;Landroidx/compose/ui/graphics/colorspace/ColorSpace;I)[F", "identity", "Landroidx/compose/ui/graphics/colorspace/Connector;", "identity$ui_graphics", "ui-graphics"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: computeTransform-YBCOT_4, reason: not valid java name */
        public final float[] m5760computeTransformYBCOT_4(ColorSpace source, ColorSpace destination, int intent) {
            if (!RenderIntent.m5766equalsimpl0(intent, RenderIntent.INSTANCE.m5770getAbsoluteuksYyKA())) {
                return null;
            }
            boolean srcRGB = ColorModel.m5743equalsimpl0(source.getModel(), ColorModel.INSTANCE.m5750getRgbxdoWZVw());
            boolean dstRGB = ColorModel.m5743equalsimpl0(destination.getModel(), ColorModel.INSTANCE.m5750getRgbxdoWZVw());
            if (srcRGB && dstRGB) {
                return null;
            }
            if (!srcRGB && !dstRGB) {
                return null;
            }
            ColorSpace colorSpace = srcRGB ? source : destination;
            Intrinsics.checkNotNull(colorSpace, "null cannot be cast to non-null type androidx.compose.ui.graphics.colorspace.Rgb");
            Rgb rgb = (Rgb) colorSpace;
            float[] srcXYZ = srcRGB ? rgb.getWhitePoint().toXyz$ui_graphics() : Illuminant.INSTANCE.getD50Xyz$ui_graphics();
            float[] dstXYZ = dstRGB ? rgb.getWhitePoint().toXyz$ui_graphics() : Illuminant.INSTANCE.getD50Xyz$ui_graphics();
            return new float[]{srcXYZ[0] / dstXYZ[0], srcXYZ[1] / dstXYZ[1], srcXYZ[2] / dstXYZ[2]};
        }

        public final Connector identity$ui_graphics(final ColorSpace source) {
            final int iM5772getRelativeuksYyKA = RenderIntent.INSTANCE.m5772getRelativeuksYyKA();
            return new Connector(source, iM5772getRelativeuksYyKA) { // from class: androidx.compose.ui.graphics.colorspace.Connector$Companion$identity$1
                {
                    super(source, source, iM5772getRelativeuksYyKA, null);
                }

                @Override // androidx.compose.ui.graphics.colorspace.Connector
                public float[] transform(float[] v) {
                    return v;
                }

                @Override // androidx.compose.ui.graphics.colorspace.Connector
                /* JADX INFO: renamed from: transformToColor-l2rxGTc$ui_graphics */
                public long mo5758transformToColorl2rxGTc$ui_graphics(long color) {
                    return color;
                }
            };
        }
    }
}
