package androidx.compose.ui.graphics;

import android.graphics.PorterDuff;
import android.os.Build;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;

/* JADX INFO: compiled from: AndroidBlendMode.android.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u0011\u0010\u0000\u001a\u00020\u0001*\u00020\u0002¢\u0006\u0004\b\u0003\u0010\u0004\u001a\u0013\u0010\u0005\u001a\u00020\u0006*\u00020\u0002H\u0000¢\u0006\u0004\b\u0007\u0010\b\u001a\u0013\u0010\t\u001a\u00020\n*\u00020\u0002H\u0001¢\u0006\u0004\b\u000b\u0010\f\u001a\u0011\u0010\r\u001a\u00020\u0002*\u00020\nH\u0001¢\u0006\u0002\u0010\u000e¨\u0006\u000f"}, d2 = {"isSupported", "", "Landroidx/compose/ui/graphics/BlendMode;", "isSupported-s9anfk8", "(I)Z", "toPorterDuffMode", "Landroid/graphics/PorterDuff$Mode;", "toPorterDuffMode-s9anfk8", "(I)Landroid/graphics/PorterDuff$Mode;", "toAndroidBlendMode", "Landroid/graphics/BlendMode;", "toAndroidBlendMode-s9anfk8", "(I)Landroid/graphics/BlendMode;", "toComposeBlendMode", "(Landroid/graphics/BlendMode;)I", "ui-graphics"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class AndroidBlendMode_androidKt {

    /* JADX INFO: compiled from: AndroidBlendMode.android.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[android.graphics.BlendMode.values().length];
            try {
                iArr[android.graphics.BlendMode.CLEAR.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[android.graphics.BlendMode.SRC.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[android.graphics.BlendMode.DST.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[android.graphics.BlendMode.SRC_OVER.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[android.graphics.BlendMode.DST_OVER.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr[android.graphics.BlendMode.SRC_IN.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                iArr[android.graphics.BlendMode.DST_IN.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                iArr[android.graphics.BlendMode.SRC_OUT.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                iArr[android.graphics.BlendMode.DST_OUT.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                iArr[android.graphics.BlendMode.SRC_ATOP.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
            try {
                iArr[android.graphics.BlendMode.DST_ATOP.ordinal()] = 11;
            } catch (NoSuchFieldError e11) {
            }
            try {
                iArr[android.graphics.BlendMode.XOR.ordinal()] = 12;
            } catch (NoSuchFieldError e12) {
            }
            try {
                iArr[android.graphics.BlendMode.PLUS.ordinal()] = 13;
            } catch (NoSuchFieldError e13) {
            }
            try {
                iArr[android.graphics.BlendMode.MODULATE.ordinal()] = 14;
            } catch (NoSuchFieldError e14) {
            }
            try {
                iArr[android.graphics.BlendMode.SCREEN.ordinal()] = 15;
            } catch (NoSuchFieldError e15) {
            }
            try {
                iArr[android.graphics.BlendMode.OVERLAY.ordinal()] = 16;
            } catch (NoSuchFieldError e16) {
            }
            try {
                iArr[android.graphics.BlendMode.DARKEN.ordinal()] = 17;
            } catch (NoSuchFieldError e17) {
            }
            try {
                iArr[android.graphics.BlendMode.LIGHTEN.ordinal()] = 18;
            } catch (NoSuchFieldError e18) {
            }
            try {
                iArr[android.graphics.BlendMode.COLOR_DODGE.ordinal()] = 19;
            } catch (NoSuchFieldError e19) {
            }
            try {
                iArr[android.graphics.BlendMode.COLOR_BURN.ordinal()] = 20;
            } catch (NoSuchFieldError e20) {
            }
            try {
                iArr[android.graphics.BlendMode.HARD_LIGHT.ordinal()] = 21;
            } catch (NoSuchFieldError e21) {
            }
            try {
                iArr[android.graphics.BlendMode.SOFT_LIGHT.ordinal()] = 22;
            } catch (NoSuchFieldError e22) {
            }
            try {
                iArr[android.graphics.BlendMode.DIFFERENCE.ordinal()] = 23;
            } catch (NoSuchFieldError e23) {
            }
            try {
                iArr[android.graphics.BlendMode.EXCLUSION.ordinal()] = 24;
            } catch (NoSuchFieldError e24) {
            }
            try {
                iArr[android.graphics.BlendMode.MULTIPLY.ordinal()] = 25;
            } catch (NoSuchFieldError e25) {
            }
            try {
                iArr[android.graphics.BlendMode.HUE.ordinal()] = 26;
            } catch (NoSuchFieldError e26) {
            }
            try {
                iArr[android.graphics.BlendMode.SATURATION.ordinal()] = 27;
            } catch (NoSuchFieldError e27) {
            }
            try {
                iArr[android.graphics.BlendMode.COLOR.ordinal()] = 28;
            } catch (NoSuchFieldError e28) {
            }
            try {
                iArr[android.graphics.BlendMode.LUMINOSITY.ordinal()] = 29;
            } catch (NoSuchFieldError e29) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: renamed from: isSupported-s9anfk8, reason: not valid java name */
    public static final boolean m5159isSupporteds9anfk8(int $this$isSupported_u2ds9anfk8) {
        return Build.VERSION.SDK_INT >= 29 || BlendMode.m5222equalsimpl0($this$isSupported_u2ds9anfk8, BlendMode.INSTANCE.m5253getSrcOver0nO6VwU()) || m5161toPorterDuffModes9anfk8($this$isSupported_u2ds9anfk8) != PorterDuff.Mode.SRC_OVER;
    }

    /* JADX INFO: renamed from: toPorterDuffMode-s9anfk8, reason: not valid java name */
    public static final PorterDuff.Mode m5161toPorterDuffModes9anfk8(int $this$toPorterDuffMode_u2ds9anfk8) {
        if (BlendMode.m5222equalsimpl0($this$toPorterDuffMode_u2ds9anfk8, BlendMode.INSTANCE.m5226getClear0nO6VwU())) {
            return PorterDuff.Mode.CLEAR;
        }
        if (BlendMode.m5222equalsimpl0($this$toPorterDuffMode_u2ds9anfk8, BlendMode.INSTANCE.m5249getSrc0nO6VwU())) {
            return PorterDuff.Mode.SRC;
        }
        if (BlendMode.m5222equalsimpl0($this$toPorterDuffMode_u2ds9anfk8, BlendMode.INSTANCE.m5232getDst0nO6VwU())) {
            return PorterDuff.Mode.DST;
        }
        if (BlendMode.m5222equalsimpl0($this$toPorterDuffMode_u2ds9anfk8, BlendMode.INSTANCE.m5253getSrcOver0nO6VwU())) {
            return PorterDuff.Mode.SRC_OVER;
        }
        if (BlendMode.m5222equalsimpl0($this$toPorterDuffMode_u2ds9anfk8, BlendMode.INSTANCE.m5236getDstOver0nO6VwU())) {
            return PorterDuff.Mode.DST_OVER;
        }
        if (BlendMode.m5222equalsimpl0($this$toPorterDuffMode_u2ds9anfk8, BlendMode.INSTANCE.m5251getSrcIn0nO6VwU())) {
            return PorterDuff.Mode.SRC_IN;
        }
        if (BlendMode.m5222equalsimpl0($this$toPorterDuffMode_u2ds9anfk8, BlendMode.INSTANCE.m5234getDstIn0nO6VwU())) {
            return PorterDuff.Mode.DST_IN;
        }
        if (BlendMode.m5222equalsimpl0($this$toPorterDuffMode_u2ds9anfk8, BlendMode.INSTANCE.m5252getSrcOut0nO6VwU())) {
            return PorterDuff.Mode.SRC_OUT;
        }
        if (BlendMode.m5222equalsimpl0($this$toPorterDuffMode_u2ds9anfk8, BlendMode.INSTANCE.m5235getDstOut0nO6VwU())) {
            return PorterDuff.Mode.DST_OUT;
        }
        if (BlendMode.m5222equalsimpl0($this$toPorterDuffMode_u2ds9anfk8, BlendMode.INSTANCE.m5250getSrcAtop0nO6VwU())) {
            return PorterDuff.Mode.SRC_ATOP;
        }
        if (BlendMode.m5222equalsimpl0($this$toPorterDuffMode_u2ds9anfk8, BlendMode.INSTANCE.m5233getDstAtop0nO6VwU())) {
            return PorterDuff.Mode.DST_ATOP;
        }
        if (BlendMode.m5222equalsimpl0($this$toPorterDuffMode_u2ds9anfk8, BlendMode.INSTANCE.m5254getXor0nO6VwU())) {
            return PorterDuff.Mode.XOR;
        }
        if (BlendMode.m5222equalsimpl0($this$toPorterDuffMode_u2ds9anfk8, BlendMode.INSTANCE.m5245getPlus0nO6VwU())) {
            return PorterDuff.Mode.ADD;
        }
        if (BlendMode.m5222equalsimpl0($this$toPorterDuffMode_u2ds9anfk8, BlendMode.INSTANCE.m5247getScreen0nO6VwU())) {
            return PorterDuff.Mode.SCREEN;
        }
        if (BlendMode.m5222equalsimpl0($this$toPorterDuffMode_u2ds9anfk8, BlendMode.INSTANCE.m5244getOverlay0nO6VwU())) {
            return PorterDuff.Mode.OVERLAY;
        }
        if (BlendMode.m5222equalsimpl0($this$toPorterDuffMode_u2ds9anfk8, BlendMode.INSTANCE.m5230getDarken0nO6VwU())) {
            return PorterDuff.Mode.DARKEN;
        }
        if (BlendMode.m5222equalsimpl0($this$toPorterDuffMode_u2ds9anfk8, BlendMode.INSTANCE.m5240getLighten0nO6VwU())) {
            return PorterDuff.Mode.LIGHTEN;
        }
        if (BlendMode.m5222equalsimpl0($this$toPorterDuffMode_u2ds9anfk8, BlendMode.INSTANCE.m5242getModulate0nO6VwU())) {
            return PorterDuff.Mode.MULTIPLY;
        }
        return PorterDuff.Mode.SRC_OVER;
    }

    /* JADX INFO: renamed from: toAndroidBlendMode-s9anfk8, reason: not valid java name */
    public static final android.graphics.BlendMode m5160toAndroidBlendModes9anfk8(int $this$toAndroidBlendMode_u2ds9anfk8) {
        return BlendMode.m5222equalsimpl0($this$toAndroidBlendMode_u2ds9anfk8, BlendMode.INSTANCE.m5226getClear0nO6VwU()) ? android.graphics.BlendMode.CLEAR : BlendMode.m5222equalsimpl0($this$toAndroidBlendMode_u2ds9anfk8, BlendMode.INSTANCE.m5249getSrc0nO6VwU()) ? android.graphics.BlendMode.SRC : BlendMode.m5222equalsimpl0($this$toAndroidBlendMode_u2ds9anfk8, BlendMode.INSTANCE.m5232getDst0nO6VwU()) ? android.graphics.BlendMode.DST : BlendMode.m5222equalsimpl0($this$toAndroidBlendMode_u2ds9anfk8, BlendMode.INSTANCE.m5253getSrcOver0nO6VwU()) ? android.graphics.BlendMode.SRC_OVER : BlendMode.m5222equalsimpl0($this$toAndroidBlendMode_u2ds9anfk8, BlendMode.INSTANCE.m5236getDstOver0nO6VwU()) ? android.graphics.BlendMode.DST_OVER : BlendMode.m5222equalsimpl0($this$toAndroidBlendMode_u2ds9anfk8, BlendMode.INSTANCE.m5251getSrcIn0nO6VwU()) ? android.graphics.BlendMode.SRC_IN : BlendMode.m5222equalsimpl0($this$toAndroidBlendMode_u2ds9anfk8, BlendMode.INSTANCE.m5234getDstIn0nO6VwU()) ? android.graphics.BlendMode.DST_IN : BlendMode.m5222equalsimpl0($this$toAndroidBlendMode_u2ds9anfk8, BlendMode.INSTANCE.m5252getSrcOut0nO6VwU()) ? android.graphics.BlendMode.SRC_OUT : BlendMode.m5222equalsimpl0($this$toAndroidBlendMode_u2ds9anfk8, BlendMode.INSTANCE.m5235getDstOut0nO6VwU()) ? android.graphics.BlendMode.DST_OUT : BlendMode.m5222equalsimpl0($this$toAndroidBlendMode_u2ds9anfk8, BlendMode.INSTANCE.m5250getSrcAtop0nO6VwU()) ? android.graphics.BlendMode.SRC_ATOP : BlendMode.m5222equalsimpl0($this$toAndroidBlendMode_u2ds9anfk8, BlendMode.INSTANCE.m5233getDstAtop0nO6VwU()) ? android.graphics.BlendMode.DST_ATOP : BlendMode.m5222equalsimpl0($this$toAndroidBlendMode_u2ds9anfk8, BlendMode.INSTANCE.m5254getXor0nO6VwU()) ? android.graphics.BlendMode.XOR : BlendMode.m5222equalsimpl0($this$toAndroidBlendMode_u2ds9anfk8, BlendMode.INSTANCE.m5245getPlus0nO6VwU()) ? android.graphics.BlendMode.PLUS : BlendMode.m5222equalsimpl0($this$toAndroidBlendMode_u2ds9anfk8, BlendMode.INSTANCE.m5242getModulate0nO6VwU()) ? android.graphics.BlendMode.MODULATE : BlendMode.m5222equalsimpl0($this$toAndroidBlendMode_u2ds9anfk8, BlendMode.INSTANCE.m5247getScreen0nO6VwU()) ? android.graphics.BlendMode.SCREEN : BlendMode.m5222equalsimpl0($this$toAndroidBlendMode_u2ds9anfk8, BlendMode.INSTANCE.m5244getOverlay0nO6VwU()) ? android.graphics.BlendMode.OVERLAY : BlendMode.m5222equalsimpl0($this$toAndroidBlendMode_u2ds9anfk8, BlendMode.INSTANCE.m5230getDarken0nO6VwU()) ? android.graphics.BlendMode.DARKEN : BlendMode.m5222equalsimpl0($this$toAndroidBlendMode_u2ds9anfk8, BlendMode.INSTANCE.m5240getLighten0nO6VwU()) ? android.graphics.BlendMode.LIGHTEN : BlendMode.m5222equalsimpl0($this$toAndroidBlendMode_u2ds9anfk8, BlendMode.INSTANCE.m5229getColorDodge0nO6VwU()) ? android.graphics.BlendMode.COLOR_DODGE : BlendMode.m5222equalsimpl0($this$toAndroidBlendMode_u2ds9anfk8, BlendMode.INSTANCE.m5228getColorBurn0nO6VwU()) ? android.graphics.BlendMode.COLOR_BURN : BlendMode.m5222equalsimpl0($this$toAndroidBlendMode_u2ds9anfk8, BlendMode.INSTANCE.m5238getHardlight0nO6VwU()) ? android.graphics.BlendMode.HARD_LIGHT : BlendMode.m5222equalsimpl0($this$toAndroidBlendMode_u2ds9anfk8, BlendMode.INSTANCE.m5248getSoftlight0nO6VwU()) ? android.graphics.BlendMode.SOFT_LIGHT : BlendMode.m5222equalsimpl0($this$toAndroidBlendMode_u2ds9anfk8, BlendMode.INSTANCE.m5231getDifference0nO6VwU()) ? android.graphics.BlendMode.DIFFERENCE : BlendMode.m5222equalsimpl0($this$toAndroidBlendMode_u2ds9anfk8, BlendMode.INSTANCE.m5237getExclusion0nO6VwU()) ? android.graphics.BlendMode.EXCLUSION : BlendMode.m5222equalsimpl0($this$toAndroidBlendMode_u2ds9anfk8, BlendMode.INSTANCE.m5243getMultiply0nO6VwU()) ? android.graphics.BlendMode.MULTIPLY : BlendMode.m5222equalsimpl0($this$toAndroidBlendMode_u2ds9anfk8, BlendMode.INSTANCE.m5239getHue0nO6VwU()) ? android.graphics.BlendMode.HUE : BlendMode.m5222equalsimpl0($this$toAndroidBlendMode_u2ds9anfk8, BlendMode.INSTANCE.m5246getSaturation0nO6VwU()) ? android.graphics.BlendMode.SATURATION : BlendMode.m5222equalsimpl0($this$toAndroidBlendMode_u2ds9anfk8, BlendMode.INSTANCE.m5227getColor0nO6VwU()) ? android.graphics.BlendMode.COLOR : BlendMode.m5222equalsimpl0($this$toAndroidBlendMode_u2ds9anfk8, BlendMode.INSTANCE.m5241getLuminosity0nO6VwU()) ? android.graphics.BlendMode.LUMINOSITY : android.graphics.BlendMode.SRC_OVER;
    }

    public static final int toComposeBlendMode(android.graphics.BlendMode $this$toComposeBlendMode) {
        switch (WhenMappings.$EnumSwitchMapping$0[$this$toComposeBlendMode.ordinal()]) {
            case 1:
                return BlendMode.INSTANCE.m5226getClear0nO6VwU();
            case 2:
                return BlendMode.INSTANCE.m5249getSrc0nO6VwU();
            case 3:
                return BlendMode.INSTANCE.m5232getDst0nO6VwU();
            case 4:
                return BlendMode.INSTANCE.m5253getSrcOver0nO6VwU();
            case 5:
                return BlendMode.INSTANCE.m5236getDstOver0nO6VwU();
            case 6:
                return BlendMode.INSTANCE.m5251getSrcIn0nO6VwU();
            case 7:
                return BlendMode.INSTANCE.m5234getDstIn0nO6VwU();
            case 8:
                return BlendMode.INSTANCE.m5252getSrcOut0nO6VwU();
            case 9:
                return BlendMode.INSTANCE.m5235getDstOut0nO6VwU();
            case 10:
                return BlendMode.INSTANCE.m5250getSrcAtop0nO6VwU();
            case 11:
                return BlendMode.INSTANCE.m5233getDstAtop0nO6VwU();
            case 12:
                return BlendMode.INSTANCE.m5254getXor0nO6VwU();
            case 13:
                return BlendMode.INSTANCE.m5245getPlus0nO6VwU();
            case 14:
                return BlendMode.INSTANCE.m5242getModulate0nO6VwU();
            case 15:
                return BlendMode.INSTANCE.m5247getScreen0nO6VwU();
            case 16:
                return BlendMode.INSTANCE.m5244getOverlay0nO6VwU();
            case 17:
                return BlendMode.INSTANCE.m5230getDarken0nO6VwU();
            case 18:
                return BlendMode.INSTANCE.m5240getLighten0nO6VwU();
            case 19:
                return BlendMode.INSTANCE.m5229getColorDodge0nO6VwU();
            case 20:
                return BlendMode.INSTANCE.m5228getColorBurn0nO6VwU();
            case 21:
                return BlendMode.INSTANCE.m5238getHardlight0nO6VwU();
            case 22:
                return BlendMode.INSTANCE.m5248getSoftlight0nO6VwU();
            case 23:
                return BlendMode.INSTANCE.m5231getDifference0nO6VwU();
            case 24:
                return BlendMode.INSTANCE.m5237getExclusion0nO6VwU();
            case 25:
                return BlendMode.INSTANCE.m5243getMultiply0nO6VwU();
            case 26:
                return BlendMode.INSTANCE.m5239getHue0nO6VwU();
            case 27:
                return BlendMode.INSTANCE.m5246getSaturation0nO6VwU();
            case 28:
                return BlendMode.INSTANCE.m5227getColor0nO6VwU();
            case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_HORIZONTAL_BIAS /* 29 */:
                return BlendMode.INSTANCE.m5241getLuminosity0nO6VwU();
            default:
                throw new NoWhenBranchMatchedException();
        }
    }
}
