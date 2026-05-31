package androidx.compose.ui.graphics;

import android.graphics.ColorSpace;
import android.os.Build;
import androidx.compose.ui.graphics.colorspace.ColorSpaces;
import androidx.compose.ui.graphics.colorspace.DoubleFunction;
import androidx.compose.ui.graphics.colorspace.Rgb;
import androidx.compose.ui.graphics.colorspace.TransferParameters;
import androidx.compose.ui.graphics.colorspace.WhitePoint;
import java.util.Arrays;
import java.util.function.DoubleUnaryOperator;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AndroidColorSpace.android.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÃ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\u00020\u0005*\u00020\u0006H\u0007J\f\u0010\u0007\u001a\u00020\u0006*\u00020\u0005H\u0007¨\u0006\b"}, d2 = {"Landroidx/compose/ui/graphics/ColorSpaceVerificationHelper;", "", "<init>", "()V", "androidColorSpace", "Landroid/graphics/ColorSpace;", "Landroidx/compose/ui/graphics/colorspace/ColorSpace;", "composeColorSpace", "ui-graphics"}, k = 1, mv = {2, 1, 0}, xi = 48)
final class ColorSpaceVerificationHelper {
    public static final ColorSpaceVerificationHelper INSTANCE = new ColorSpaceVerificationHelper();

    private ColorSpaceVerificationHelper() {
    }

    @JvmStatic
    public static final ColorSpace androidColorSpace(androidx.compose.ui.graphics.colorspace.ColorSpace $this$androidColorSpace) {
        ColorSpace.Rgb.TransferParameters transferParameters;
        ColorSpace.Rgb directColorSpace;
        ColorSpace v34ColorSpace;
        if (Intrinsics.areEqual($this$androidColorSpace, ColorSpaces.INSTANCE.getSrgb())) {
            return ColorSpace.get(ColorSpace.Named.SRGB);
        }
        if (Intrinsics.areEqual($this$androidColorSpace, ColorSpaces.INSTANCE.getAces())) {
            return ColorSpace.get(ColorSpace.Named.ACES);
        }
        if (Intrinsics.areEqual($this$androidColorSpace, ColorSpaces.INSTANCE.getAcescg())) {
            return ColorSpace.get(ColorSpace.Named.ACESCG);
        }
        if (Intrinsics.areEqual($this$androidColorSpace, ColorSpaces.INSTANCE.getAdobeRgb())) {
            return ColorSpace.get(ColorSpace.Named.ADOBE_RGB);
        }
        if (Intrinsics.areEqual($this$androidColorSpace, ColorSpaces.INSTANCE.getBt2020())) {
            return ColorSpace.get(ColorSpace.Named.BT2020);
        }
        if (Intrinsics.areEqual($this$androidColorSpace, ColorSpaces.INSTANCE.getBt709())) {
            return ColorSpace.get(ColorSpace.Named.BT709);
        }
        if (Intrinsics.areEqual($this$androidColorSpace, ColorSpaces.INSTANCE.getCieLab())) {
            return ColorSpace.get(ColorSpace.Named.CIE_LAB);
        }
        if (Intrinsics.areEqual($this$androidColorSpace, ColorSpaces.INSTANCE.getCieXyz())) {
            return ColorSpace.get(ColorSpace.Named.CIE_XYZ);
        }
        if (Intrinsics.areEqual($this$androidColorSpace, ColorSpaces.INSTANCE.getDciP3())) {
            return ColorSpace.get(ColorSpace.Named.DCI_P3);
        }
        if (Intrinsics.areEqual($this$androidColorSpace, ColorSpaces.INSTANCE.getDisplayP3())) {
            return ColorSpace.get(ColorSpace.Named.DISPLAY_P3);
        }
        if (Intrinsics.areEqual($this$androidColorSpace, ColorSpaces.INSTANCE.getExtendedSrgb())) {
            return ColorSpace.get(ColorSpace.Named.EXTENDED_SRGB);
        }
        if (!Intrinsics.areEqual($this$androidColorSpace, ColorSpaces.INSTANCE.getLinearExtendedSrgb())) {
            if (Intrinsics.areEqual($this$androidColorSpace, ColorSpaces.INSTANCE.getLinearSrgb())) {
                return ColorSpace.get(ColorSpace.Named.LINEAR_SRGB);
            }
            if (Intrinsics.areEqual($this$androidColorSpace, ColorSpaces.INSTANCE.getNtsc1953())) {
                return ColorSpace.get(ColorSpace.Named.NTSC_1953);
            }
            if (Intrinsics.areEqual($this$androidColorSpace, ColorSpaces.INSTANCE.getProPhotoRgb())) {
                return ColorSpace.get(ColorSpace.Named.PRO_PHOTO_RGB);
            }
            if (Intrinsics.areEqual($this$androidColorSpace, ColorSpaces.INSTANCE.getSmpteC())) {
                return ColorSpace.get(ColorSpace.Named.SMPTE_C);
            }
            if (Build.VERSION.SDK_INT >= 34 && (v34ColorSpace = ColorSpaceVerificationHelperV34.obtainAndroidColorSpace($this$androidColorSpace)) != null) {
                return v34ColorSpace;
            }
            if ($this$androidColorSpace instanceof Rgb) {
                float[] whitePointArray = ((Rgb) $this$androidColorSpace).getWhitePoint().toXyz$ui_graphics();
                TransferParameters transferParams = ((Rgb) $this$androidColorSpace).getTransferParameters();
                if (transferParams != null) {
                    transferParameters = new ColorSpace.Rgb.TransferParameters(transferParams.getA(), transferParams.getB(), transferParams.getC(), transferParams.getD(), transferParams.getE(), transferParams.getF(), transferParams.getGamma());
                } else {
                    transferParameters = null;
                }
                ColorSpace.Rgb.TransferParameters androidTransferParams = transferParameters;
                float[] transform = ((Rgb) $this$androidColorSpace).getTransform();
                if (androidTransferParams == null) {
                    String name = $this$androidColorSpace.getName();
                    float[] primaries = ((Rgb) $this$androidColorSpace).getPrimaries();
                    final Function1<Double, Double> oetf = ((Rgb) $this$androidColorSpace).getOetf();
                    DoubleUnaryOperator doubleUnaryOperator = new DoubleUnaryOperator() { // from class: androidx.compose.ui.graphics.ColorSpaceVerificationHelper$$ExternalSyntheticLambda0
                        @Override // java.util.function.DoubleUnaryOperator
                        public final double applyAsDouble(double d) {
                            return ((Number) oetf.invoke(Double.valueOf(d))).doubleValue();
                        }
                    };
                    final Function1<Double, Double> eotf = ((Rgb) $this$androidColorSpace).getEotf();
                    directColorSpace = new ColorSpace.Rgb(name, primaries, whitePointArray, doubleUnaryOperator, new DoubleUnaryOperator() { // from class: androidx.compose.ui.graphics.ColorSpaceVerificationHelper$$ExternalSyntheticLambda1
                        @Override // java.util.function.DoubleUnaryOperator
                        public final double applyAsDouble(double d) {
                            return ((Number) eotf.invoke(Double.valueOf(d))).doubleValue();
                        }
                    }, ((Rgb) $this$androidColorSpace).getMinValue(0), ((Rgb) $this$androidColorSpace).getMaxValue(0));
                } else {
                    directColorSpace = new ColorSpace.Rgb($this$androidColorSpace.getName(), ((Rgb) $this$androidColorSpace).getPrimaries(), whitePointArray, androidTransferParams);
                    if (!Float.isNaN(transform[0]) && !Arrays.equals(directColorSpace.getTransform(), transform)) {
                        directColorSpace = new ColorSpace.Rgb($this$androidColorSpace.getName(), transform, androidTransferParams);
                    }
                }
                return directColorSpace;
            }
            return ColorSpace.get(ColorSpace.Named.SRGB);
        }
        return ColorSpace.get(ColorSpace.Named.LINEAR_EXTENDED_SRGB);
    }

    @JvmStatic
    public static final androidx.compose.ui.graphics.colorspace.ColorSpace composeColorSpace(final ColorSpace $this$composeColorSpace) {
        Rgb srgb;
        WhitePoint whitePoint;
        TransferParameters composeTransferParams;
        int id = $this$composeColorSpace.getId();
        if (id == ColorSpace.Named.SRGB.ordinal()) {
            return ColorSpaces.INSTANCE.getSrgb();
        }
        if (id == ColorSpace.Named.ACES.ordinal()) {
            return ColorSpaces.INSTANCE.getAces();
        }
        if (id == ColorSpace.Named.ACESCG.ordinal()) {
            return ColorSpaces.INSTANCE.getAcescg();
        }
        if (id == ColorSpace.Named.ADOBE_RGB.ordinal()) {
            return ColorSpaces.INSTANCE.getAdobeRgb();
        }
        if (id == ColorSpace.Named.BT2020.ordinal()) {
            return ColorSpaces.INSTANCE.getBt2020();
        }
        if (id == ColorSpace.Named.BT709.ordinal()) {
            return ColorSpaces.INSTANCE.getBt709();
        }
        if (id == ColorSpace.Named.CIE_LAB.ordinal()) {
            return ColorSpaces.INSTANCE.getCieLab();
        }
        if (id == ColorSpace.Named.CIE_XYZ.ordinal()) {
            return ColorSpaces.INSTANCE.getCieXyz();
        }
        if (id == ColorSpace.Named.DCI_P3.ordinal()) {
            return ColorSpaces.INSTANCE.getDciP3();
        }
        if (id == ColorSpace.Named.DISPLAY_P3.ordinal()) {
            return ColorSpaces.INSTANCE.getDisplayP3();
        }
        if (id == ColorSpace.Named.EXTENDED_SRGB.ordinal()) {
            return ColorSpaces.INSTANCE.getExtendedSrgb();
        }
        if (id == ColorSpace.Named.LINEAR_EXTENDED_SRGB.ordinal()) {
            return ColorSpaces.INSTANCE.getLinearExtendedSrgb();
        }
        if (id == ColorSpace.Named.LINEAR_SRGB.ordinal()) {
            return ColorSpaces.INSTANCE.getLinearSrgb();
        }
        if (id == ColorSpace.Named.NTSC_1953.ordinal()) {
            return ColorSpaces.INSTANCE.getNtsc1953();
        }
        if (id == ColorSpace.Named.PRO_PHOTO_RGB.ordinal()) {
            return ColorSpaces.INSTANCE.getProPhotoRgb();
        }
        if (id == ColorSpace.Named.SMPTE_C.ordinal()) {
            return ColorSpaces.INSTANCE.getSmpteC();
        }
        if (Build.VERSION.SDK_INT >= 34) {
            androidx.compose.ui.graphics.colorspace.ColorSpace v34ColorSpace = ColorSpaceVerificationHelperV34.obtainComposeColorSpaceFromId($this$composeColorSpace.getId());
            if (!Intrinsics.areEqual(v34ColorSpace, ColorSpaces.INSTANCE.getUnspecified$ui_graphics())) {
                return v34ColorSpace;
            }
        }
        if ($this$composeColorSpace instanceof ColorSpace.Rgb) {
            ColorSpace.Rgb.TransferParameters transferParams = ((ColorSpace.Rgb) $this$composeColorSpace).getTransferParameters();
            if (((ColorSpace.Rgb) $this$composeColorSpace).getWhitePoint().length == 3) {
                whitePoint = new WhitePoint(((ColorSpace.Rgb) $this$composeColorSpace).getWhitePoint()[0], ((ColorSpace.Rgb) $this$composeColorSpace).getWhitePoint()[1], ((ColorSpace.Rgb) $this$composeColorSpace).getWhitePoint()[2]);
            } else {
                whitePoint = new WhitePoint(((ColorSpace.Rgb) $this$composeColorSpace).getWhitePoint()[0], ((ColorSpace.Rgb) $this$composeColorSpace).getWhitePoint()[1]);
            }
            WhitePoint whitePoint2 = whitePoint;
            if (transferParams != null) {
                composeTransferParams = new TransferParameters(transferParams.g, transferParams.a, transferParams.b, transferParams.c, transferParams.d, transferParams.e, transferParams.f);
            } else {
                composeTransferParams = null;
            }
            srgb = new Rgb(((ColorSpace.Rgb) $this$composeColorSpace).getName(), ((ColorSpace.Rgb) $this$composeColorSpace).getPrimaries(), whitePoint2, ((ColorSpace.Rgb) $this$composeColorSpace).getTransform(), new DoubleFunction() { // from class: androidx.compose.ui.graphics.ColorSpaceVerificationHelper$$ExternalSyntheticLambda2
                @Override // androidx.compose.ui.graphics.colorspace.DoubleFunction
                public final double invoke(double d) {
                    return ((ColorSpace.Rgb) $this$composeColorSpace).getOetf().applyAsDouble(d);
                }
            }, new DoubleFunction() { // from class: androidx.compose.ui.graphics.ColorSpaceVerificationHelper$$ExternalSyntheticLambda3
                @Override // androidx.compose.ui.graphics.colorspace.DoubleFunction
                public final double invoke(double d) {
                    return ((ColorSpace.Rgb) $this$composeColorSpace).getEotf().applyAsDouble(d);
                }
            }, ((ColorSpace.Rgb) $this$composeColorSpace).getMinValue(0), ((ColorSpace.Rgb) $this$composeColorSpace).getMaxValue(0), composeTransferParams, ((ColorSpace.Rgb) $this$composeColorSpace).getId());
        } else {
            srgb = ColorSpaces.INSTANCE.getSrgb();
        }
        return srgb;
    }
}
