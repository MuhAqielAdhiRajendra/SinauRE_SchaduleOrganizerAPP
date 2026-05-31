package androidx.compose.material3.internal.colorUtil;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Metadata;

/* JADX INFO: compiled from: HCTSolver.android.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0010\u0013\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\b\n\u0002\b\u0016\bÁ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0002J\u0010\u0010\u000e\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\fH\u0002J\u0010\u0010\u0010\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\fH\u0002J\u0010\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\u0006H\u0002J \u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\f2\u0006\u0010\u0018\u001a\u00020\fH\u0002J \u0010\u0019\u001a\u00020\f2\u0006\u0010\u001a\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\f2\u0006\u0010\u001c\u001a\u00020\fH\u0002J \u0010\u001d\u001a\u00020\u00062\u0006\u0010\u001a\u001a\u00020\u00062\u0006\u0010\u001e\u001a\u00020\f2\u0006\u0010\u001c\u001a\u00020\u0006H\u0002J(\u0010\u001f\u001a\u00020\u00062\u0006\u0010\u001a\u001a\u00020\u00062\u0006\u0010 \u001a\u00020\f2\u0006\u0010\u001c\u001a\u00020\u00062\u0006\u0010!\u001a\u00020\"H\u0002J\u0010\u0010#\u001a\u00020\u00152\u0006\u0010$\u001a\u00020\fH\u0002J\u0018\u0010%\u001a\u00020\u00062\u0006\u0010&\u001a\u00020\f2\u0006\u0010'\u001a\u00020\"H\u0002J#\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010&\u001a\u00020\f2\u0006\u0010)\u001a\u00020\fH\u0002¢\u0006\u0002\u0010*J\u0010\u0010+\u001a\u00020\"2\u0006\u0010$\u001a\u00020\fH\u0002J\u0010\u0010,\u001a\u00020\"2\u0006\u0010$\u001a\u00020\fH\u0002J\u0018\u0010-\u001a\u00020\"2\u0006\u0010&\u001a\u00020\f2\u0006\u0010)\u001a\u00020\fH\u0002J\u0010\u0010.\u001a\u00020\f2\u0006\u0010/\u001a\u00020\fH\u0002J \u00100\u001a\u00020\"2\u0006\u00101\u001a\u00020\f2\u0006\u00102\u001a\u00020\f2\u0006\u0010&\u001a\u00020\fH\u0002J\u001e\u00103\u001a\u00020\"2\u0006\u00104\u001a\u00020\f2\u0006\u00102\u001a\u00020\f2\u0006\u00105\u001a\u00020\fJ\u0010\u00106\u001a\u00020\f2\u0006\u00107\u001a\u00020\fH\u0002R\u0016\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0007R\u0016\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0007R\u000e\u0010\t\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00068"}, d2 = {"Landroidx/compose/material3/internal/colorUtil/HctSolver;", "", "<init>", "()V", "SCALED_DISCOUNT_FROM_LINRGB", "", "", "[[D", "LINRGB_FROM_SCALED_DISCOUNT", "Y_FROM_LINRGB", "CRITICAL_PLANES", "sanitizeRadians", "", "angle", "trueDelinearized", "rgbComponent", "chromaticAdaptation", "component", "hueOf", "linrgb", "areInCyclicOrder", "", "a", "b", "c", "intercept", "source", "mid", TypedValues.AttributesType.S_TARGET, "lerpPoint", "t", "setCoordinate", "coordinate", "axis", "", "isBounded", "x", "nthVertex", "y", "n", "bisectToSegment", "targetHue", "(DD)[[D", "criticalPlaneBelow", "criticalPlaneAbove", "bisectToLimit", "inverseChromaticAdaptation", "adapted", "findResultByJ", "hueRadians", "chroma", "solveToInt", "hueDegrees", "lstar", "sanitizeDegreesDouble", "degrees", "material3"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class HctSolver {
    public static final HctSolver INSTANCE = new HctSolver();
    private static final double[][] SCALED_DISCOUNT_FROM_LINRGB = {new double[]{0.001200833568784504d, 0.002389694492170889d, 2.795742885861124E-4d}, new double[]{5.891086651375999E-4d, 0.0029785502573438758d, 3.270666104008398E-4d}, new double[]{1.0146692491640572E-4d, 5.364214359186694E-4d, 0.0032979401770712076d}};
    private static final double[][] LINRGB_FROM_SCALED_DISCOUNT = {new double[]{1373.2198709594231d, -1100.4251190754821d, -7.278681089101213d}, new double[]{-271.815969077903d, 559.6580465940733d, -32.46047482791194d}, new double[]{1.9622899599665666d, -57.173814538844006d, 308.7233197812385d}};
    private static final double[] Y_FROM_LINRGB = {0.2126d, 0.7152d, 0.0722d};
    private static final double[] CRITICAL_PLANES = {0.015176349177441876d, 0.045529047532325624d, 0.07588174588720938d, 0.10623444424209313d, 0.13658714259697685d, 0.16693984095186062d, 0.19729253930674434d, 0.2276452376616281d, 0.2579979360165119d, 0.28835063437139563d, 0.3188300904430532d, 0.350925934958123d, 0.3848314933096426d, 0.42057480301049466d, 0.458183274052838d, 0.4976837250274023d, 0.5391024159806381d, 0.5824650784040898d, 0.6277969426914107d, 0.6751227633498623d, 0.7244668422128921d, 0.775853049866786d, 0.829304845476233d, 0.8848452951698498d, 0.942497089126609d, 1.0022825574869039d, 1.0642236851973577d, 1.1283421258858297d, 1.1946592148522128d, 1.2631959812511864d, 1.3339731595349034d, 1.407011200216447d, 1.4823302800086415d, 1.5599503113873272d, 1.6398909516233677d, 1.7221716113234105d, 1.8068114625156377d, 1.8938294463134073d, 1.9832442801866852d, 2.075074464868551d, 2.1693382909216234d, 2.2660538449872063d, 2.36523901573795d, 2.4669114995532007d, 2.5710888059345764d, 2.6777882626779785d, 2.7870270208169257d, 2.898822059350997d, 3.0131901897720907d, 3.1301480604002863d, 3.2497121605402226d, 3.3718988244681087d, 3.4967242352587946d, 3.624204428461639d, 3.754355295633311d, 3.887192587735158d, 4.022731918402185d, 4.160988767090289d, 4.301978482107941d, 4.445716283538092d, 4.592217266055746d, 4.741496401646282d, 4.893568542229298d, 5.048448422192488d, 5.20615066083972d, 5.3666897647573375d, 5.5300801301023865d, 5.696336044816294d, 5.865471690767354d, 6.037501145825082d, 6.212438385869475d, 6.390297286737924d, 6.571091626112461d, 6.7548350853498045d, 6.941541251256611d, 7.131223617812143d, 7.323895587840543d, 7.5195704746346665d, 7.7182615035334345d, 7.919981813454504d, 8.124744458384042d, 8.332562408825165d, 8.543448553206703d, 8.757415699253682d, 8.974476575321063d, 9.194643831691977d, 9.417930041841839d, 9.644347703669503d, 9.873909240696694d, 10.106627003236781d, 10.342513269534024d, 10.58158024687427d, 10.8238400726681d, 11.069304815507364d, 11.317986476196008d, 11.569896988756009d, 11.825048221409341d, 12.083451977536606d, 12.345119996613247d, 12.610063955123938d, 12.878295467455942d, 13.149826086772048d, 13.42466730586372d, 13.702830557985108d, 13.984327217668513d, 14.269168601521828d, 14.55736596900856d, 14.848930523210871d, 15.143873411576273d, 15.44220572664832d, 15.743938506781891d, 16.04908273684337d, 16.35764934889634d, 16.66964922287304d, 16.985093187232053d, 17.30399201960269d, 17.62635644741625d, 17.95219714852476d, 18.281524751807332d, 18.614349837764564d, 18.95068293910138d, 19.290534541298456d, 19.633915083172692d, 19.98083495742689d, 20.331304511189067d, 20.685334046541502d, 21.042933821039977d, 21.404114048223256d, 21.76888489811322d, 22.137256497705877d, 22.50923893145328d, 22.884842241736916d, 23.264076429332462d, 23.6469514538663d, 24.033477234264016d, 24.42366364919083d, 24.817520537484558d, 25.21505769858089d, 25.61628489293138d, 26.021211842414342d, 26.429848230738664d, 26.842203703840827d, 27.258287870275353d, 27.678110301598522d, 28.10168053274597d, 28.529008062403893d, 28.96010235337422d, 29.39497283293396d, 29.83362889318845d, 30.276079891419332d, 30.722335150426627d, 31.172403958865512d, 31.62629557157785d, 32.08401920991837d, 32.54558406207592d, 33.010999283389665d, 33.4802739966603d, 33.953417292456834d, 34.430438229418264d, 34.911345834551085d, 35.39614910352207d, 35.88485700094671d, 36.37747846067349d, 36.87402238606382d, 37.37449765026789d, 37.87891309649659d, 38.38727753828926d, 38.89959975977785d, 39.41588851594697d, 39.93615253289054d, 40.460400508064545d, 40.98864111053629d, 41.520882981230194d, 42.05713473317016d, 42.597404951718396d, 43.141702194811224d, 43.6900349931913d, 44.24241185063697d, 44.798841244188324d, 45.35933162437017d, 45.92389141541209d, 46.49252901546552d, 47.065252796817916d, 47.64207110610409d, 48.22299226451468d, 48.808024568002054d, 49.3971762874833d, 49.9904556690408d, 50.587870934119984d, 51.189430279724725d, 51.79514187861014d, 52.40501387947288d, 53.0190544071392d, 53.637271562750364d, 54.259673423945976d, 54.88626804504493d, 55.517063457223934d, 56.15206766869424d, 56.79128866487574d, 57.43473440856916d, 58.08241284012621d, 58.734331877617365d, 59.39049941699807d, 60.05092333227251d, 60.715611475655585d, 61.38457167773311d, 62.057811747619894d, 62.7353394731159d, 63.417162620860914d, 64.10328893648692d, 64.79372614476921d, 65.48848194977529d, 66.18756403501224d, 66.89098006357258d, 67.59873767827808d, 68.31084450182222d, 69.02730813691093d, 69.74813616640164d, 70.47333615344107d, 71.20291564160104d, 71.93688215501312d, 72.67524319850172d, 73.41800625771542d, 74.16517879925733d, 74.9167682708136d, 75.67278210128072d, 76.43322770089146d, 77.1981124613393d, 77.96744375590167d, 78.74122893956174d, 79.51947534912904d, 80.30219030335869d, 81.08938110306934d, 81.88105503125999d, 82.67721935322541d, 83.4778813166706d, 84.28304815182372d, 85.09272707154808d, 85.90692527145302d, 86.72564993000343d, 87.54890820862819d, 88.3767072518277d, 89.2090541872801d, 90.04595612594655d, 90.88742016217518d, 91.73345337380438d, 92.58406282226491d, 93.43925555268066d, 94.29903859396902d, 95.16341895893969d, 96.03240364439274d, 96.9059996312159d, 97.78421388448044d, 98.6670533535366d, 99.55452497210776d};
    public static final int $stable = 8;

    private HctSolver() {
    }

    private final double sanitizeRadians(double angle) {
        return (25.132741228718345d + angle) % 6.283185307179586d;
    }

    private final double trueDelinearized(double rgbComponent) {
        double delinearized;
        double normalized = rgbComponent / 100.0d;
        if (normalized <= 0.0031308d) {
            delinearized = 12.92d * normalized;
        } else {
            delinearized = (Math.pow(normalized, 0.4166666666666667d) * 1.055d) - 0.055d;
        }
        return 255.0d * delinearized;
    }

    private final double chromaticAdaptation(double component) {
        double af = Math.pow(Math.abs(component), 0.42d);
        return ((((double) CamUtils.INSTANCE.signum(component)) * 400.0d) * af) / (27.13d + af);
    }

    private final double hueOf(double[] linrgb) {
        double[][] matrix = SCALED_DISCOUNT_FROM_LINRGB;
        double rD = (linrgb[0] * matrix[0][0]) + (linrgb[1] * matrix[0][1]) + (linrgb[2] * matrix[0][2]);
        double gD = (linrgb[0] * matrix[1][0]) + (linrgb[1] * matrix[1][1]) + (linrgb[2] * matrix[1][2]);
        double bD = (linrgb[0] * matrix[2][0]) + (linrgb[1] * matrix[2][1]) + (linrgb[2] * matrix[2][2]);
        double rA = chromaticAdaptation(rD);
        double gA = chromaticAdaptation(gD);
        double bA = chromaticAdaptation(bD);
        double a = (((rA * 11.0d) + ((-12.0d) * gA)) + bA) / 11.0d;
        double b = ((rA + gA) - (2.0d * bA)) / 9.0d;
        return Math.atan2(b, a);
    }

    private final boolean areInCyclicOrder(double a, double b, double c) {
        double deltaAB = sanitizeRadians(b - a);
        double deltaAC = sanitizeRadians(c - a);
        return deltaAB < deltaAC;
    }

    private final double intercept(double source, double mid, double target) {
        if (target == source) {
            return target;
        }
        return (mid - source) / (target - source);
    }

    private final double[] lerpPoint(double[] source, double t, double[] target) {
        return new double[]{source[0] + ((target[0] - source[0]) * t), source[1] + ((target[1] - source[1]) * t), source[2] + ((target[2] - source[2]) * t)};
    }

    private final double[] setCoordinate(double[] source, double coordinate, double[] target, int axis) {
        double t = intercept(source[axis], coordinate, target[axis]);
        return lerpPoint(source, t, target);
    }

    private final boolean isBounded(double x) {
        return 0.0d <= x && x <= 100.0d;
    }

    private final double[] nthVertex(double y, int n) {
        double kR = Y_FROM_LINRGB[0];
        double kG = Y_FROM_LINRGB[1];
        double kB = Y_FROM_LINRGB[2];
        double coordA = n % 4 <= 1 ? 0.0d : 100.0d;
        double coordB = n % 2 != 0 ? 100.0d : 0.0d;
        if (n < 4) {
            double r = ((y - (coordA * kG)) - (coordB * kB)) / kR;
            if (isBounded(r)) {
                return new double[]{r, coordA, coordB};
            }
            return new double[]{-1.0d, -1.0d, -1.0d};
        }
        if (n < 8) {
            double g = ((y - (coordB * kR)) - (coordA * kB)) / kG;
            if (isBounded(g)) {
                return new double[]{coordB, g, coordA};
            }
            return new double[]{-1.0d, -1.0d, -1.0d};
        }
        double b = ((y - (coordA * kR)) - (coordB * kG)) / kB;
        if (isBounded(b)) {
            return new double[]{coordA, coordB, b};
        }
        return new double[]{-1.0d, -1.0d, -1.0d};
    }

    private final double[][] bisectToSegment(double y, double targetHue) {
        double rightHue;
        HctSolver hctSolver = this;
        double[] left = {-1.0d, -1.0d, -1.0d};
        double rightHue2 = 0.0d;
        boolean uncut = true;
        int n = 0;
        double[] right = left;
        boolean initialized = false;
        double[] left2 = left;
        double leftHue = 0.0d;
        while (n < 12) {
            double[] mid = hctSolver.nthVertex(y, n);
            if (mid[0] < 0.0d) {
                rightHue = rightHue2;
            } else {
                double midHue = hctSolver.hueOf(mid);
                if (!initialized) {
                    left2 = mid;
                    right = mid;
                    leftHue = midHue;
                    rightHue2 = midHue;
                    initialized = true;
                } else {
                    if (uncut) {
                        rightHue = rightHue2;
                    } else {
                        rightHue = rightHue2;
                        if (hctSolver.areInCyclicOrder(leftHue, midHue, rightHue2)) {
                        }
                    }
                    uncut = false;
                    if (areInCyclicOrder(leftHue, targetHue, midHue)) {
                        rightHue2 = midHue;
                        right = mid;
                    } else {
                        leftHue = midHue;
                        left2 = mid;
                        rightHue2 = rightHue;
                    }
                }
                n++;
                hctSolver = this;
            }
            rightHue2 = rightHue;
            n++;
            hctSolver = this;
        }
        return new double[][]{left2, right};
    }

    private final int criticalPlaneBelow(double x) {
        return (int) Math.floor(x - 0.5d);
    }

    private final int criticalPlaneAbove(double x) {
        return (int) Math.ceil(x - 0.5d);
    }

    private final int bisectToLimit(double y, double targetHue) {
        int i;
        boolean z;
        int iCriticalPlaneAbove;
        int iCriticalPlaneBelow;
        double[] dArr;
        double[] dArr2;
        double d;
        HctSolver hctSolver = this;
        double[][] dArrBisectToSegment = bisectToSegment(y, targetHue);
        boolean z2 = false;
        double[] dArr3 = dArrBisectToSegment[0];
        double dHueOf = hctSolver.hueOf(dArr3);
        double[] dArr4 = dArrBisectToSegment[1];
        int i2 = 0;
        while (i2 < 3) {
            if (dArr3[i2] == dArr4[i2] ? true : z2) {
                i = i2;
                z = z2;
            } else {
                if (dArr3[i2] < dArr4[i2]) {
                    iCriticalPlaneAbove = hctSolver.criticalPlaneBelow(hctSolver.trueDelinearized(dArr3[i2]));
                    iCriticalPlaneBelow = hctSolver.criticalPlaneAbove(hctSolver.trueDelinearized(dArr4[i2]));
                } else {
                    iCriticalPlaneAbove = hctSolver.criticalPlaneAbove(hctSolver.trueDelinearized(dArr3[i2]));
                    iCriticalPlaneBelow = hctSolver.criticalPlaneBelow(hctSolver.trueDelinearized(dArr4[i2]));
                }
                int i3 = iCriticalPlaneAbove;
                int i4 = iCriticalPlaneBelow;
                int i5 = 0;
                while (true) {
                    double d2 = dHueOf;
                    if (i5 >= 8) {
                        dArr = dArr3;
                        dArr2 = dArr4;
                        i = i2;
                        z = z2;
                        d = d2;
                        break;
                    }
                    if (Math.abs(i4 - i3) <= 1.0d) {
                        dArr = dArr3;
                        dArr2 = dArr4;
                        i = i2;
                        z = z2;
                        d = d2;
                        break;
                    }
                    int iFloor = (int) Math.floor(((double) (i3 + i4)) / 2.0d);
                    boolean z3 = z2;
                    double[] coordinate = hctSolver.setCoordinate(dArr3, CRITICAL_PLANES[iFloor], dArr4, i2);
                    double[] dArr5 = dArr3;
                    double[] dArr6 = dArr4;
                    int i6 = i2;
                    int i7 = i5;
                    double dHueOf2 = hctSolver.hueOf(coordinate);
                    if (hctSolver.areInCyclicOrder(d2, targetHue, dHueOf2)) {
                        dArr4 = coordinate;
                        i4 = iFloor;
                        dHueOf = d2;
                        dArr3 = dArr5;
                    } else {
                        i3 = iFloor;
                        dArr4 = dArr6;
                        dHueOf = dHueOf2;
                        dArr3 = coordinate;
                    }
                    i5 = i7 + 1;
                    hctSolver = this;
                    z2 = z3;
                    i2 = i6;
                }
                dHueOf = d;
                dArr3 = dArr;
                dArr4 = dArr2;
            }
            i2 = i + 1;
            hctSolver = this;
            z2 = z;
        }
        boolean z4 = z2;
        return CamUtils.INSTANCE.argbFromLinrgbComponents((dArr3[z4 ? 1 : 0] + dArr4[z4 ? 1 : 0]) / 2.0d, (dArr3[1] + dArr4[1]) / 2.0d, (dArr3[2] + dArr4[2]) / 2.0d);
    }

    private final double inverseChromaticAdaptation(double adapted) {
        double adaptedAbs = Math.abs(adapted);
        double base = Math.max(0.0d, (27.13d * adaptedAbs) / (400.0d - adaptedAbs));
        return ((double) CamUtils.INSTANCE.signum(adapted)) * Math.pow(base, 2.380952380952381d);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0092  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final int findResultByJ(double r76, double r78, double r80) {
        /*
            Method dump skipped, instruction units count: 509
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.internal.colorUtil.HctSolver.findResultByJ(double, double, double):int");
    }

    public final int solveToInt(double hueDegrees, double chroma, double lstar) {
        if (chroma < 1.0E-4d || lstar < 1.0E-4d || lstar > 99.9999d) {
            return CamUtils.INSTANCE.argbFromLstar(lstar);
        }
        double hueDegreesPrime = sanitizeDegreesDouble(hueDegrees);
        double hueRadians = Math.toRadians(hueDegreesPrime);
        double y = CamUtils.INSTANCE.yFromLstar(lstar);
        int exactAnswer = findResultByJ(hueRadians, chroma, y);
        if (exactAnswer != 0) {
            return exactAnswer;
        }
        return bisectToLimit(y, hueRadians);
    }

    private final double sanitizeDegreesDouble(double degrees) {
        double degreesPrime = degrees % 360.0d;
        if (degreesPrime < 0.0d) {
            return degreesPrime + 360.0d;
        }
        return degreesPrime;
    }
}
