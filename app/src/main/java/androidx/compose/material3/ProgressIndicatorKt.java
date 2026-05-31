package androidx.compose.material3;

import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.CubicBezierEasing;
import androidx.compose.animation.core.EasingKt;
import androidx.compose.animation.core.InfiniteRepeatableSpec;
import androidx.compose.animation.core.InfiniteTransition;
import androidx.compose.animation.core.InfiniteTransitionKt;
import androidx.compose.animation.core.KeyframesSpec;
import androidx.compose.foundation.CanvasKt;
import androidx.compose.foundation.ProgressSemanticsKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.material3.internal.AccessibilityUtilKt;
import androidx.compose.material3.tokens.CircularProgressIndicatorTokens;
import androidx.compose.material3.tokens.LinearProgressIndicatorTokens;
import androidx.compose.material3.tokens.MotionTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.State;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.Stroke;
import androidx.compose.ui.semantics.ProgressBarRangeInfo;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.profileinstaller.ProfileVerifier;
import androidx.window.core.layout.WindowSizeClass;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: ProgressIndicator.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000^\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b#\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\f\u001aE\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\u000bH\u0007¢\u0006\u0004\b\f\u0010\r\u001aj\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\u0019\b\u0002\u0010\u0010\u001a\u0013\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00010\u0011¢\u0006\u0002\b\u0013H\u0007¢\u0006\u0004\b\u0014\u0010\u0015\u001a7\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\u000bH\u0007¢\u0006\u0004\b\u0016\u0010\u0017\u001aA\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\u000fH\u0007¢\u0006\u0004\b\u0018\u0010\u0019\u001a?\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\u000bH\u0007¢\u0006\u0004\b\f\u0010\u001a\u001a5\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\bH\u0007¢\u0006\u0004\b\u001b\u0010\u001c\u001a-\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\bH\u0007¢\u0006\u0004\b\u001d\u0010\u001e\u001a;\u0010\u001f\u001a\u00020\u0001*\u00020\u00122\u0006\u0010 \u001a\u00020\u00042\u0006\u0010!\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\"\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000bH\u0002¢\u0006\u0004\b#\u0010$\u001aO\u0010%\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\"\u001a\u00020\u000f2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\u000bH\u0007¢\u0006\u0004\b&\u0010'\u001aY\u0010%\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\"\u001a\u00020\u000f2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\u000fH\u0007¢\u0006\u0004\b(\u0010)\u001aA\u0010%\u001a\u00020\u00012\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\"\u001a\u00020\u000f2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\u000bH\u0007¢\u0006\u0004\b*\u0010+\u001aK\u0010%\u001a\u00020\u00012\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\"\u001a\u00020\u000f2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\u000fH\u0007¢\u0006\u0004\b,\u0010-\u001aI\u0010%\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\"\u001a\u00020\u000f2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\u000bH\u0007¢\u0006\u0004\b&\u0010.\u001a5\u0010%\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\"\u001a\u00020\u000fH\u0007¢\u0006\u0004\b/\u00100\u001a-\u0010%\u001a\u00020\u00012\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\"\u001a\u00020\u000fH\u0007¢\u0006\u0004\b1\u00102\u001a3\u00103\u001a\u00020\u0001*\u00020\u00122\u0006\u00104\u001a\u00020\u00042\u0006\u00105\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\b2\u0006\u00106\u001a\u000207H\u0002¢\u0006\u0004\b8\u00109\u001a#\u0010:\u001a\u00020\u0001*\u00020\u00122\u0006\u0010\u0007\u001a\u00020\b2\u0006\u00106\u001a\u000207H\u0002¢\u0006\u0004\b;\u0010<\u001a3\u0010=\u001a\u00020\u0001*\u00020\u00122\u0006\u00104\u001a\u00020\u00042\u0006\u00105\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\b2\u0006\u00106\u001a\u000207H\u0002¢\u0006\u0004\b>\u00109\u001a;\u0010?\u001a\u00020\u0001*\u00020\u00122\u0006\u00104\u001a\u00020\u00042\u0006\u0010\"\u001a\u00020\u000f2\u0006\u00105\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\b2\u0006\u00106\u001a\u000207H\u0002¢\u0006\u0004\b@\u0010A\"\u001a\u0010B\u001a\b\u0012\u0004\u0012\u00020\u00040C8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bD\u0010E\"\u001a\u0010F\u001a\b\u0012\u0004\u0012\u00020\u00040C8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bG\u0010E\"\u001a\u0010H\u001a\b\u0012\u0004\u0012\u00020\u00040C8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bI\u0010E\"\u001a\u0010J\u001a\b\u0012\u0004\u0012\u00020\u00040C8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bK\u0010E\"\u001a\u0010L\u001a\b\u0012\u0004\u0012\u00020\u00040C8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bM\u0010E\"\u001a\u0010N\u001a\b\u0012\u0004\u0012\u00020\u00040C8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bO\u0010E\"\u001a\u0010P\u001a\b\u0012\u0004\u0012\u00020\u00040C8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bQ\u0010E\"\u0016\u0010R\u001a\u00020\u000fX\u0080\u0004¢\u0006\n\n\u0002\u0010U\u001a\u0004\bS\u0010T\"\u0016\u0010V\u001a\u00020\u000fX\u0080\u0004¢\u0006\n\n\u0002\u0010U\u001a\u0004\bW\u0010T\"\u0016\u0010X\u001a\u00020\u000fX\u0080\u0004¢\u0006\n\n\u0002\u0010U\u001a\u0004\bY\u0010T\"\u0016\u0010Z\u001a\u00020\u000fX\u0080\u0004¢\u0006\n\n\u0002\u0010U\u001a\u0004\b[\u0010T\"\u000e\u0010\\\u001a\u00020]X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010^\u001a\u00020]X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010_\u001a\u00020]X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010`\u001a\u00020]X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010a\u001a\u00020]X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010b\u001a\u00020]X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010c\u001a\u00020]X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010d\u001a\u00020]X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010e\u001a\u00020]X\u0080T¢\u0006\u0002\n\u0000\"\u0014\u0010f\u001a\u00020gX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\bh\u0010i\"\u0014\u0010j\u001a\u00020gX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\bk\u0010i\"\u000e\u0010l\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010m\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010n\u001a\u00020]X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010o\u001a\u00020]X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010p\u001a\u00020]X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010q\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010r\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000¨\u0006s"}, d2 = {"LinearProgressIndicator", "", "progress", "Lkotlin/Function0;", "", "modifier", "Landroidx/compose/ui/Modifier;", TypedValues.Custom.S_COLOR, "Landroidx/compose/ui/graphics/Color;", "trackColor", "strokeCap", "Landroidx/compose/ui/graphics/StrokeCap;", "LinearProgressIndicator-_5eSR-E", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;JJILandroidx/compose/runtime/Composer;II)V", "gapSize", "Landroidx/compose/ui/unit/Dp;", "drawStopIndicator", "Lkotlin/Function1;", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "Lkotlin/ExtensionFunctionType;", "LinearProgressIndicator-GJbTh5U", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;JJIFLkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)V", "LinearProgressIndicator-2cYBFYY", "(Landroidx/compose/ui/Modifier;JJILandroidx/compose/runtime/Composer;II)V", "LinearProgressIndicator-rIrjwxo", "(Landroidx/compose/ui/Modifier;JJIFLandroidx/compose/runtime/Composer;II)V", "(FLandroidx/compose/ui/Modifier;JJILandroidx/compose/runtime/Composer;II)V", "LinearProgressIndicator-eaDK9VM", "(FLandroidx/compose/ui/Modifier;JJLandroidx/compose/runtime/Composer;II)V", "LinearProgressIndicator-RIQooxk", "(Landroidx/compose/ui/Modifier;JJLandroidx/compose/runtime/Composer;II)V", "drawLinearIndicator", "startFraction", "endFraction", "strokeWidth", "drawLinearIndicator-qYKTg0g", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;FFJFI)V", "CircularProgressIndicator", "CircularProgressIndicator-DUhRLBM", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;JFJILandroidx/compose/runtime/Composer;II)V", "CircularProgressIndicator-IyT6zlY", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;JFJIFLandroidx/compose/runtime/Composer;II)V", "CircularProgressIndicator-LxG7B9w", "(Landroidx/compose/ui/Modifier;JFJILandroidx/compose/runtime/Composer;II)V", "CircularProgressIndicator-4lLiAd8", "(Landroidx/compose/ui/Modifier;JFJIFLandroidx/compose/runtime/Composer;II)V", "(FLandroidx/compose/ui/Modifier;JFJILandroidx/compose/runtime/Composer;II)V", "CircularProgressIndicator-MBs18nI", "(FLandroidx/compose/ui/Modifier;JFLandroidx/compose/runtime/Composer;II)V", "CircularProgressIndicator-aM-cp0Q", "(Landroidx/compose/ui/Modifier;JFLandroidx/compose/runtime/Composer;II)V", "drawCircularIndicator", "startAngle", "sweep", "stroke", "Landroidx/compose/ui/graphics/drawscope/Stroke;", "drawCircularIndicator-42QJj7c", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;FFJLandroidx/compose/ui/graphics/drawscope/Stroke;)V", "drawCircularIndicatorTrack", "drawCircularIndicatorTrack-bw27NRU", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;JLandroidx/compose/ui/graphics/drawscope/Stroke;)V", "drawDeterminateCircularIndicator", "drawDeterminateCircularIndicator-42QJj7c", "drawIndeterminateCircularIndicator", "drawIndeterminateCircularIndicator-hrjfTZI", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;FFFJLandroidx/compose/ui/graphics/drawscope/Stroke;)V", "circularIndeterminateGlobalRotationAnimationSpec", "Landroidx/compose/animation/core/InfiniteRepeatableSpec;", "getCircularIndeterminateGlobalRotationAnimationSpec", "()Landroidx/compose/animation/core/InfiniteRepeatableSpec;", "circularIndeterminateRotationAnimationSpec", "getCircularIndeterminateRotationAnimationSpec", "circularIndeterminateProgressAnimationSpec", "getCircularIndeterminateProgressAnimationSpec", "linearIndeterminateFirstLineHeadAnimationSpec", "getLinearIndeterminateFirstLineHeadAnimationSpec", "linearIndeterminateFirstLineTailAnimationSpec", "getLinearIndeterminateFirstLineTailAnimationSpec", "linearIndeterminateSecondLineHeadAnimationSpec", "getLinearIndeterminateSecondLineHeadAnimationSpec", "linearIndeterminateSecondLineTailAnimationSpec", "getLinearIndeterminateSecondLineTailAnimationSpec", "LinearIndicatorWidth", "getLinearIndicatorWidth", "()F", "F", "LinearIndicatorHeight", "getLinearIndicatorHeight", "StopIndicatorTrailingSpace", "getStopIndicatorTrailingSpace", "CircularIndicatorDiameter", "getCircularIndicatorDiameter", "LinearAnimationDuration", "", "FirstLineHeadDuration", "FirstLineTailDuration", "SecondLineHeadDuration", "SecondLineTailDuration", "FirstLineHeadDelay", "FirstLineTailDelay", "SecondLineHeadDelay", "SecondLineTailDelay", "LinearIndeterminateProgressEasing", "Landroidx/compose/animation/core/CubicBezierEasing;", "getLinearIndeterminateProgressEasing", "()Landroidx/compose/animation/core/CubicBezierEasing;", "CircularProgressEasing", "getCircularProgressEasing", "CircularIndeterminateMinProgress", "CircularIndeterminateMaxProgress", "CircularAnimationProgressDuration", "CircularAnimationAdditionalRotationDelay", "CircularAnimationAdditionalRotationDuration", "CircularAdditionalRotationDegreesTarget", "CircularGlobalRotationDegreesTarget", "material3"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class ProgressIndicatorKt {
    public static final float CircularAdditionalRotationDegreesTarget = 360.0f;
    public static final int CircularAnimationAdditionalRotationDelay = 1500;
    public static final int CircularAnimationAdditionalRotationDuration = 300;
    public static final int CircularAnimationProgressDuration = 6000;
    public static final float CircularGlobalRotationDegreesTarget = 1080.0f;
    public static final float CircularIndeterminateMaxProgress = 0.87f;
    public static final float CircularIndeterminateMinProgress = 0.1f;
    public static final int FirstLineHeadDelay = 0;
    public static final int FirstLineHeadDuration = 1000;
    public static final int FirstLineTailDelay = 250;
    public static final int FirstLineTailDuration = 1000;
    public static final int LinearAnimationDuration = 1750;
    public static final int SecondLineHeadDelay = 650;
    public static final int SecondLineHeadDuration = 850;
    public static final int SecondLineTailDelay = 900;
    public static final int SecondLineTailDuration = 850;
    private static final float LinearIndicatorWidth = Dp.m8150constructorimpl(240);
    private static final float LinearIndicatorHeight = LinearProgressIndicatorTokens.INSTANCE.m3941getHeightD9Ej5fM();
    private static final float StopIndicatorTrailingSpace = Dp.m8150constructorimpl(6);
    private static final float CircularIndicatorDiameter = CircularProgressIndicatorTokens.INSTANCE.m3650getSizeD9Ej5fM();
    private static final CubicBezierEasing LinearIndeterminateProgressEasing = MotionTokens.INSTANCE.getEasingEmphasizedAccelerateCubicBezier();
    private static final CubicBezierEasing CircularProgressEasing = MotionTokens.INSTANCE.getEasingStandardCubicBezier();

    static final Unit CircularProgressIndicator_4lLiAd8$lambda$35(Modifier modifier, long j, float f, long j2, int i, float f2, int i2, int i3, Composer composer, int i4) {
        m2812CircularProgressIndicator4lLiAd8(modifier, j, f, j2, i, f2, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
        return Unit.INSTANCE;
    }

    static final Unit CircularProgressIndicator_DUhRLBM$lambda$20(Function0 function0, Modifier modifier, long j, float f, long j2, int i, int i2, int i3, Composer composer, int i4) {
        m2814CircularProgressIndicatorDUhRLBM(function0, modifier, j, f, j2, i, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
        return Unit.INSTANCE;
    }

    static final Unit CircularProgressIndicator_DUhRLBM$lambda$38(float f, Modifier modifier, long j, float f2, long j2, int i, int i2, int i3, Composer composer, int i4) {
        m2813CircularProgressIndicatorDUhRLBM(f, modifier, j, f2, j2, i, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
        return Unit.INSTANCE;
    }

    static final Unit CircularProgressIndicator_IyT6zlY$lambda$29(Function0 function0, Modifier modifier, long j, float f, long j2, int i, float f2, int i2, int i3, Composer composer, int i4) {
        m2815CircularProgressIndicatorIyT6zlY(function0, modifier, j, f, j2, i, f2, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
        return Unit.INSTANCE;
    }

    static final Unit CircularProgressIndicator_LxG7B9w$lambda$30(Modifier modifier, long j, float f, long j2, int i, int i2, int i3, Composer composer, int i4) {
        m2816CircularProgressIndicatorLxG7B9w(modifier, j, f, j2, i, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
        return Unit.INSTANCE;
    }

    static final Unit CircularProgressIndicator_MBs18nI$lambda$39(float f, Modifier modifier, long j, float f2, int i, int i2, Composer composer, int i3) {
        m2817CircularProgressIndicatorMBs18nI(f, modifier, j, f2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit CircularProgressIndicator_aM_cp0Q$lambda$40(Modifier modifier, long j, float f, int i, int i2, Composer composer, int i3) {
        m2818CircularProgressIndicatoraMcp0Q(modifier, j, f, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit LinearProgressIndicator_2cYBFYY$lambda$11(Modifier modifier, long j, long j2, int i, int i2, int i3, Composer composer, int i4) {
        m2819LinearProgressIndicator2cYBFYY(modifier, j, j2, i, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
        return Unit.INSTANCE;
    }

    static final Unit LinearProgressIndicator_GJbTh5U$lambda$10(Function0 function0, Modifier modifier, long j, long j2, int i, float f, Function1 function1, int i2, int i3, Composer composer, int i4) {
        m2820LinearProgressIndicatorGJbTh5U(function0, modifier, j, j2, i, f, function1, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
        return Unit.INSTANCE;
    }

    static final Unit LinearProgressIndicator_RIQooxk$lambda$19(Modifier modifier, long j, long j2, int i, int i2, Composer composer, int i3) {
        m2821LinearProgressIndicatorRIQooxk(modifier, j, j2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit LinearProgressIndicator__5eSR_E$lambda$0(Function0 function0, Modifier modifier, long j, long j2, int i, int i2, int i3, Composer composer, int i4) {
        m2823LinearProgressIndicator_5eSRE(function0, modifier, j, j2, i, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
        return Unit.INSTANCE;
    }

    static final Unit LinearProgressIndicator__5eSR_E$lambda$17(float f, Modifier modifier, long j, long j2, int i, int i2, int i3, Composer composer, int i4) {
        m2822LinearProgressIndicator_5eSRE(f, modifier, j, j2, i, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
        return Unit.INSTANCE;
    }

    static final Unit LinearProgressIndicator_eaDK9VM$lambda$18(float f, Modifier modifier, long j, long j2, int i, int i2, Composer composer, int i3) {
        m2824LinearProgressIndicatoreaDK9VM(f, modifier, j, j2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit LinearProgressIndicator_rIrjwxo$lambda$14(Modifier modifier, long j, long j2, int i, float f, int i2, int i3, Composer composer, int i4) {
        m2825LinearProgressIndicatorrIrjwxo(modifier, j, j2, i, f, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
        return Unit.INSTANCE;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Use the overload that takes `gapSize` and `drawStopIndicator`, see `LegacyLinearProgressIndicatorSample` on how to restore the previous behavior", replaceWith = @ReplaceWith(expression = "LinearProgressIndicator(progress, modifier, color, trackColor, strokeCap, gapSize, drawStopIndicator)", imports = {}))
    /* JADX INFO: renamed from: LinearProgressIndicator-_5eSR-E, reason: not valid java name */
    public static final /* synthetic */ void m2823LinearProgressIndicator_5eSRE(final Function0 progress, Modifier modifier, long color, long trackColor, int strokeCap, Composer $composer, final int $changed, final int i) {
        Function0 function0;
        Modifier modifier2;
        long j;
        long trackColor2;
        int i2;
        Composer $composer2;
        final Modifier modifier3;
        final long color2;
        final long trackColor3;
        final int strokeCap2;
        Modifier.Companion modifier4;
        long color3;
        long trackColor4;
        int strokeCap3;
        Modifier modifier5;
        long color4;
        Composer $composer3 = $composer.startRestartGroup(-1796992155);
        ComposerKt.sourceInformation($composer3, "C(LinearProgressIndicator)N(progress,modifier,color:c#ui.graphics.Color,trackColor:c#ui.graphics.Color,strokeCap:c#ui.graphics.StrokeCap)101@4670L193:ProgressIndicator.kt#uh7d8r");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
            function0 = progress;
        } else if (($changed & 6) == 0) {
            function0 = progress;
            $dirty |= $composer3.changedInstance(function0) ? 4 : 2;
        } else {
            function0 = progress;
        }
        int i3 = i & 2;
        if (i3 != 0) {
            $dirty |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty |= $composer3.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                j = color;
                int i4 = $composer3.changed(j) ? 256 : 128;
                $dirty |= i4;
            } else {
                j = color;
            }
            $dirty |= i4;
        } else {
            j = color;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                trackColor2 = trackColor;
                int i5 = $composer3.changed(trackColor2) ? 2048 : 1024;
                $dirty |= i5;
            } else {
                trackColor2 = trackColor;
            }
            $dirty |= i5;
        } else {
            trackColor2 = trackColor;
        }
        int i6 = i & 16;
        if (i6 != 0) {
            $dirty |= 24576;
            i2 = strokeCap;
        } else if (($changed & 24576) == 0) {
            i2 = strokeCap;
            $dirty |= $composer3.changed(i2) ? 16384 : 8192;
        } else {
            i2 = strokeCap;
        }
        if (!$composer3.shouldExecute(($dirty & 9363) != 9362, $dirty & 1)) {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            color2 = j;
            trackColor3 = trackColor2;
            strokeCap2 = i2;
        } else {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "97@4511L11,98@4574L16");
            if (($changed & 1) != 0 && !$composer3.getDefaultsInvalid()) {
                $composer3.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty &= -897;
                }
                if ((i & 8) != 0) {
                    $dirty &= -7169;
                }
                trackColor4 = trackColor2;
                strokeCap3 = i2;
                modifier5 = modifier2;
                color4 = j;
            } else {
                if (i3 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if ((i & 4) == 0) {
                    color3 = j;
                } else {
                    color3 = ProgressIndicatorDefaults.INSTANCE.getLinearColor($composer3, 6);
                    $dirty &= -897;
                }
                if ((i & 8) != 0) {
                    trackColor2 = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor($composer3, 6);
                    $dirty &= -7169;
                }
                if (i6 == 0) {
                    trackColor4 = trackColor2;
                    strokeCap3 = i2;
                    modifier5 = modifier4;
                    color4 = color3;
                } else {
                    strokeCap3 = ProgressIndicatorDefaults.INSTANCE.m2810getLinearStrokeCapKaPHkGw();
                    trackColor4 = trackColor2;
                    modifier5 = modifier4;
                    color4 = color3;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1796992155, $dirty, -1, "androidx.compose.material3.LinearProgressIndicator (ProgressIndicator.kt:100)");
            }
            $composer2 = $composer3;
            m2820LinearProgressIndicatorGJbTh5U(function0, modifier5, color4, trackColor4, strokeCap3, ProgressIndicatorDefaults.INSTANCE.m2809getLinearIndicatorTrackGapSizeD9Ej5fM(), null, $composer2, ($dirty & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (57344 & $dirty), 64);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier5;
            color2 = color4;
            trackColor3 = trackColor4;
            strokeCap2 = strokeCap3;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.ProgressIndicatorKt$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ProgressIndicatorKt.LinearProgressIndicator__5eSR_E$lambda$0(progress, modifier3, color2, trackColor3, strokeCap2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    static final Unit LinearProgressIndicator_GJbTh5U$lambda$2$lambda$1(long $color, int $strokeCap, DrawScope drawScope) throws Throwable {
        ProgressIndicatorDefaults.INSTANCE.m2804drawStopIndicatorEgI2THU(drawScope, ProgressIndicatorDefaults.INSTANCE.m2811getLinearTrackStopIndicatorSizeD9Ej5fM(), $color, $strokeCap);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:160:0x0264  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x0266  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x026e  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x0270  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x0297  */
    /* JADX WARN: Removed duplicated region for block: B:178:0x029d  */
    /* JADX WARN: Removed duplicated region for block: B:184:0x02b0  */
    /* JADX WARN: Removed duplicated region for block: B:186:0x02b6  */
    /* JADX WARN: Removed duplicated region for block: B:192:0x02c8  */
    /* JADX WARN: Removed duplicated region for block: B:196:0x02da  */
    /* JADX WARN: Removed duplicated region for block: B:199:0x02fe  */
    /* JADX INFO: renamed from: LinearProgressIndicator-GJbTh5U, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void m2820LinearProgressIndicatorGJbTh5U(final kotlin.jvm.functions.Function0<java.lang.Float> r34, androidx.compose.ui.Modifier r35, long r36, long r38, int r40, float r41, kotlin.jvm.functions.Function1<? super androidx.compose.ui.graphics.drawscope.DrawScope, kotlin.Unit> r42, androidx.compose.runtime.Composer r43, final int r44, final int r45) {
        /*
            Method dump skipped, instruction units count: 817
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.ProgressIndicatorKt.m2820LinearProgressIndicatorGJbTh5U(kotlin.jvm.functions.Function0, androidx.compose.ui.Modifier, long, long, int, float, kotlin.jvm.functions.Function1, androidx.compose.runtime.Composer, int, int):void");
    }

    static final float LinearProgressIndicator_GJbTh5U$lambda$4$lambda$3(Function0 $progress) {
        float $this$fastCoerceIn$iv = ((Number) $progress.invoke()).floatValue();
        float $this$fastCoerceAtLeast$iv$iv = $this$fastCoerceIn$iv;
        if ($this$fastCoerceAtLeast$iv$iv < 0.0f) {
            $this$fastCoerceAtLeast$iv$iv = 0.0f;
        }
        if ($this$fastCoerceAtLeast$iv$iv > 1.0f) {
            return 1.0f;
        }
        return $this$fastCoerceAtLeast$iv$iv;
    }

    static final Unit LinearProgressIndicator_GJbTh5U$lambda$7$lambda$6(Function0 $coercedProgress, SemanticsPropertyReceiver $this$semantics) {
        Object objInvoke = $coercedProgress.invoke();
        float it = ((Number) objInvoke).floatValue();
        if (Float.isNaN(it)) {
            objInvoke = null;
        }
        Float f = (Float) objInvoke;
        SemanticsPropertiesKt.setProgressBarRangeInfo($this$semantics, new ProgressBarRangeInfo(f != null ? f.floatValue() : 0.0f, RangesKt.rangeTo(0.0f, 1.0f), 0, 4, null));
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x0059  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static final kotlin.Unit LinearProgressIndicator_GJbTh5U$lambda$9$lambda$8(int r12, float r13, kotlin.jvm.functions.Function0 r14, long r15, long r17, kotlin.jvm.functions.Function1 r19, androidx.compose.ui.graphics.drawscope.DrawScope r20) {
        /*
            r0 = r20
            long r1 = r0.mo5887getSizeNHjbRc()
            r3 = 0
            r4 = r1
            r6 = 0
            r7 = 4294967295(0xffffffff, double:2.1219957905E-314)
            long r9 = r4 & r7
            int r9 = (int) r9
            r10 = 0
            float r9 = java.lang.Float.intBitsToFloat(r9)
            r5 = r9
            androidx.compose.ui.graphics.StrokeCap$Companion r1 = androidx.compose.ui.graphics.StrokeCap.INSTANCE
            int r1 = r1.m5687getButtKaPHkGw()
            boolean r1 = androidx.compose.ui.graphics.StrokeCap.m5683equalsimpl0(r12, r1)
            r2 = 32
            if (r1 != 0) goto L59
            long r3 = r0.mo5887getSizeNHjbRc()
            r1 = 0
            r9 = r3
            r6 = 0
            long r7 = r7 & r9
            int r7 = (int) r7
            r8 = 0
            float r7 = java.lang.Float.intBitsToFloat(r7)
            long r3 = r0.mo5887getSizeNHjbRc()
            r1 = 0
            r8 = r3
            r6 = 0
            long r10 = r8 >> r2
            int r10 = (int) r10
            r11 = 0
            float r10 = java.lang.Float.intBitsToFloat(r10)
            int r1 = (r7 > r10 ? 1 : (r7 == r10 ? 0 : -1))
            if (r1 <= 0) goto L4d
            goto L59
        L4d:
            float r1 = r0.mo428toDpu2uoSUM(r5)
            r3 = 0
            float r4 = r13 + r1
            float r4 = androidx.compose.ui.unit.Dp.m8150constructorimpl(r4)
            goto L5a
        L59:
            r4 = r13
        L5a:
            r7 = r4
            long r3 = r0.mo5887getSizeNHjbRc()
            r1 = 0
            r8 = r3
            r6 = 0
            long r10 = r8 >> r2
            int r2 = (int) r10
            r10 = 0
            float r2 = java.lang.Float.intBitsToFloat(r2)
            float r1 = r0.mo428toDpu2uoSUM(r2)
            r2 = 0
            float r1 = r7 / r1
            r8 = r1
            java.lang.Object r1 = r14.invoke()
            java.lang.Number r1 = (java.lang.Number) r1
            float r9 = r1.floatValue()
            float r1 = java.lang.Math.min(r9, r8)
            float r1 = r1 + r9
            r2 = 1065353216(0x3f800000, float:1.0)
            int r2 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1))
            if (r2 > 0) goto L94
            r2 = 1065353216(0x3f800000, float:1.0)
            r6 = r12
            r3 = r15
            m2830drawLinearIndicatorqYKTg0g(r0, r1, r2, r3, r5, r6)
            r10 = r1
            goto L95
        L94:
            r10 = r1
        L95:
            r1 = 0
            r6 = r12
            r3 = r17
            r0 = r20
            r2 = r9
            m2830drawLinearIndicatorqYKTg0g(r0, r1, r2, r3, r5, r6)
            r19.invoke(r20)
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.ProgressIndicatorKt.LinearProgressIndicator_GJbTh5U$lambda$9$lambda$8(int, float, kotlin.jvm.functions.Function0, long, long, kotlin.jvm.functions.Function1, androidx.compose.ui.graphics.drawscope.DrawScope):kotlin.Unit");
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Use the overload that takes `gapSize`, see `LegacyIndeterminateLinearProgressIndicatorSample` on how to restore the previous behavior", replaceWith = @ReplaceWith(expression = "LinearProgressIndicator(modifier, color, trackColor, strokeCap, gapSize)", imports = {}))
    /* JADX INFO: renamed from: LinearProgressIndicator-2cYBFYY, reason: not valid java name */
    public static final /* synthetic */ void m2819LinearProgressIndicator2cYBFYY(Modifier modifier, long color, long trackColor, int strokeCap, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        long j;
        long trackColor2;
        int i2;
        final Modifier modifier3;
        final long color2;
        final long trackColor3;
        final int strokeCap2;
        Modifier.Companion modifier4;
        long color3;
        long trackColor4;
        int strokeCap3;
        Modifier modifier5;
        long color4;
        Composer $composer2 = $composer.startRestartGroup(-476865359);
        ComposerKt.sourceInformation($composer2, "C(LinearProgressIndicator)N(modifier,color:c#ui.graphics.Color,trackColor:c#ui.graphics.Color,strokeCap:c#ui.graphics.StrokeCap)220@9927L175:ProgressIndicator.kt#uh7d8r");
        int $dirty = $changed;
        int i3 = i & 1;
        if (i3 != 0) {
            $dirty |= 6;
            modifier2 = modifier;
        } else if (($changed & 6) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 4 : 2;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 48) == 0) {
            if ((i & 2) == 0) {
                j = color;
                int i4 = $composer2.changed(j) ? 32 : 16;
                $dirty |= i4;
            } else {
                j = color;
            }
            $dirty |= i4;
        } else {
            j = color;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                trackColor2 = trackColor;
                int i5 = $composer2.changed(trackColor2) ? 256 : 128;
                $dirty |= i5;
            } else {
                trackColor2 = trackColor;
            }
            $dirty |= i5;
        } else {
            trackColor2 = trackColor;
        }
        int i6 = i & 8;
        if (i6 != 0) {
            $dirty |= 3072;
            i2 = strokeCap;
        } else if (($changed & 3072) == 0) {
            i2 = strokeCap;
            $dirty |= $composer2.changed(i2) ? 2048 : 1024;
        } else {
            i2 = strokeCap;
        }
        if (!$composer2.shouldExecute(($dirty & 1171) != 1170, $dirty & 1)) {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            color2 = j;
            trackColor3 = trackColor2;
            strokeCap2 = i2;
        } else {
            $composer2.startDefaults();
            ComposerKt.sourceInformation($composer2, "216@9768L11,217@9831L16");
            if (($changed & 1) != 0 && !$composer2.getDefaultsInvalid()) {
                $composer2.skipToGroupEnd();
                if ((i & 2) != 0) {
                    $dirty &= -113;
                }
                if ((i & 4) != 0) {
                    $dirty &= -897;
                }
                trackColor4 = trackColor2;
                strokeCap3 = i2;
                modifier5 = modifier2;
                color4 = j;
            } else {
                if (i3 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if ((i & 2) == 0) {
                    color3 = j;
                } else {
                    color3 = ProgressIndicatorDefaults.INSTANCE.getLinearColor($composer2, 6);
                    $dirty &= -113;
                }
                if ((i & 4) != 0) {
                    trackColor2 = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor($composer2, 6);
                    $dirty &= -897;
                }
                if (i6 == 0) {
                    trackColor4 = trackColor2;
                    strokeCap3 = i2;
                    modifier5 = modifier4;
                    color4 = color3;
                } else {
                    strokeCap3 = ProgressIndicatorDefaults.INSTANCE.m2810getLinearStrokeCapKaPHkGw();
                    trackColor4 = trackColor2;
                    modifier5 = modifier4;
                    color4 = color3;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-476865359, $dirty, -1, "androidx.compose.material3.LinearProgressIndicator (ProgressIndicator.kt:219)");
            }
            m2825LinearProgressIndicatorrIrjwxo(modifier5, color4, trackColor4, strokeCap3, ProgressIndicatorDefaults.INSTANCE.m2809getLinearIndicatorTrackGapSizeD9Ej5fM(), $composer2, ($dirty & 14) | 24576 | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            color2 = color4;
            trackColor3 = trackColor4;
            strokeCap2 = strokeCap3;
            modifier3 = modifier5;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.ProgressIndicatorKt$$ExternalSyntheticLambda15
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ProgressIndicatorKt.LinearProgressIndicator_2cYBFYY$lambda$11(modifier3, color2, trackColor3, strokeCap2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: LinearProgressIndicator-rIrjwxo, reason: not valid java name */
    public static final void m2825LinearProgressIndicatorrIrjwxo(Modifier modifier, long color, long trackColor, int strokeCap, float gapSize, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        long color2;
        long trackColor2;
        int strokeCap2;
        final float gapSize2;
        Composer $composer2;
        final Modifier modifier3;
        final long color3;
        final long trackColor3;
        final int strokeCap3;
        Modifier.Companion modifier4;
        final float gapSize3;
        final int strokeCap4;
        final long color4;
        final long trackColor4;
        Composer $composer3 = $composer.startRestartGroup(567589233);
        ComposerKt.sourceInformation($composer3, "C(LinearProgressIndicator)N(modifier,color:c#ui.graphics.Color,trackColor:c#ui.graphics.Color,strokeCap:c#ui.graphics.StrokeCap,gapSize:c#ui.unit.Dp)255@11480L28,257@11560L159,263@11771L159,269@11983L160,275@12196L160,285@12539L1839,280@12361L2017:ProgressIndicator.kt#uh7d8r");
        int $dirty = $changed;
        int i2 = i & 1;
        if (i2 != 0) {
            $dirty |= 6;
            modifier2 = modifier;
        } else if (($changed & 6) == 0) {
            modifier2 = modifier;
            $dirty |= $composer3.changed(modifier2) ? 4 : 2;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 48) == 0) {
            if ((i & 2) == 0) {
                color2 = color;
                int i3 = $composer3.changed(color2) ? 32 : 16;
                $dirty |= i3;
            } else {
                color2 = color;
            }
            $dirty |= i3;
        } else {
            color2 = color;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                trackColor2 = trackColor;
                int i4 = $composer3.changed(trackColor2) ? 256 : 128;
                $dirty |= i4;
            } else {
                trackColor2 = trackColor;
            }
            $dirty |= i4;
        } else {
            trackColor2 = trackColor;
        }
        int i5 = i & 8;
        if (i5 != 0) {
            $dirty |= 3072;
            strokeCap2 = strokeCap;
        } else if (($changed & 3072) == 0) {
            strokeCap2 = strokeCap;
            $dirty |= $composer3.changed(strokeCap2) ? 2048 : 1024;
        } else {
            strokeCap2 = strokeCap;
        }
        int i6 = i & 16;
        if (i6 != 0) {
            $dirty |= 24576;
            gapSize2 = gapSize;
        } else if (($changed & 24576) == 0) {
            gapSize2 = gapSize;
            $dirty |= $composer3.changed(gapSize2) ? 16384 : 8192;
        } else {
            gapSize2 = gapSize;
        }
        if (!$composer3.shouldExecute(($dirty & 9363) != 9362, $dirty & 1)) {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            color3 = color2;
            trackColor3 = trackColor2;
            strokeCap3 = strokeCap2;
        } else {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "250@11223L11,251@11286L16");
            if (($changed & 1) != 0 && !$composer3.getDefaultsInvalid()) {
                $composer3.skipToGroupEnd();
                if ((i & 2) != 0) {
                    $dirty &= -113;
                }
                if ((i & 4) != 0) {
                    $dirty &= -897;
                }
                modifier4 = modifier2;
                gapSize3 = gapSize2;
                strokeCap4 = strokeCap2;
            } else {
                if (i2 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if ((i & 2) != 0) {
                    color2 = ProgressIndicatorDefaults.INSTANCE.getLinearColor($composer3, 6);
                    $dirty &= -113;
                }
                if ((i & 4) != 0) {
                    trackColor2 = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor($composer3, 6);
                    $dirty &= -897;
                }
                if (i5 != 0) {
                    strokeCap2 = ProgressIndicatorDefaults.INSTANCE.m2810getLinearStrokeCapKaPHkGw();
                }
                if (i6 == 0) {
                    gapSize3 = gapSize2;
                    strokeCap4 = strokeCap2;
                } else {
                    gapSize3 = ProgressIndicatorDefaults.INSTANCE.m2809getLinearIndicatorTrackGapSizeD9Ej5fM();
                    strokeCap4 = strokeCap2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(567589233, $dirty, -1, "androidx.compose.material3.LinearProgressIndicator (ProgressIndicator.kt:254)");
            }
            InfiniteTransition infiniteTransition = InfiniteTransitionKt.rememberInfiniteTransition(null, $composer3, 0, 1);
            final State<Float> stateAnimateFloat = InfiniteTransitionKt.animateFloat(infiniteTransition, 0.0f, 1.0f, getLinearIndeterminateFirstLineHeadAnimationSpec(), null, $composer3, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9), 8);
            final State<Float> stateAnimateFloat2 = InfiniteTransitionKt.animateFloat(infiniteTransition, 0.0f, 1.0f, getLinearIndeterminateFirstLineTailAnimationSpec(), null, $composer3, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9), 8);
            final State<Float> stateAnimateFloat3 = InfiniteTransitionKt.animateFloat(infiniteTransition, 0.0f, 1.0f, getLinearIndeterminateSecondLineHeadAnimationSpec(), null, $composer3, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9), 8);
            final State<Float> stateAnimateFloat4 = InfiniteTransitionKt.animateFloat(infiniteTransition, 0.0f, 1.0f, getLinearIndeterminateSecondLineTailAnimationSpec(), null, $composer3, InfiniteTransition.$stable | 432 | (InfiniteRepeatableSpec.$stable << 9), 8);
            $composer2 = $composer3;
            Modifier modifierM1117sizeVpY3zN4 = SizeKt.m1117sizeVpY3zN4(ProgressSemanticsKt.progressSemantics(modifier4.then(AccessibilityUtilKt.getIncreaseVerticalSemanticsBounds())), LinearIndicatorWidth, LinearIndicatorHeight);
            ComposerKt.sourceInformationMarkerStart($composer2, -7544576, "CC(remember):ProgressIndicator.kt#9igjgp");
            Modifier modifier5 = modifier4;
            boolean invalid$iv = ((57344 & $dirty) == 16384) | (($dirty & 7168) == 2048) | $composer2.changed(stateAnimateFloat) | (((($dirty & 896) ^ 384) > 256 && $composer2.changed(trackColor2)) || ($dirty & 384) == 256) | $composer2.changed(stateAnimateFloat2) | (((($dirty & 112) ^ 48) > 32 && $composer2.changed(color2)) || ($dirty & 48) == 32) | $composer2.changed(stateAnimateFloat3) | $composer2.changed(stateAnimateFloat4);
            Object it$iv = $composer2.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                color4 = color2;
                trackColor4 = trackColor2;
                Object value$iv = new Function1() { // from class: androidx.compose.material3.ProgressIndicatorKt$$ExternalSyntheticLambda16
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return ProgressIndicatorKt.LinearProgressIndicator_rIrjwxo$lambda$13$lambda$12(strokeCap4, gapSize3, stateAnimateFloat, trackColor4, stateAnimateFloat2, color4, stateAnimateFloat3, stateAnimateFloat4, (DrawScope) obj);
                    }
                };
                $composer2.updateRememberedValue(value$iv);
                it$iv = value$iv;
            } else {
                color4 = color2;
                trackColor4 = trackColor2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            CanvasKt.Canvas(modifierM1117sizeVpY3zN4, (Function1) it$iv, $composer2, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier5;
            strokeCap3 = strokeCap4;
            gapSize2 = gapSize3;
            trackColor3 = trackColor4;
            color3 = color4;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.ProgressIndicatorKt$$ExternalSyntheticLambda17
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ProgressIndicatorKt.LinearProgressIndicator_rIrjwxo$lambda$14(modifier3, color3, trackColor3, strokeCap3, gapSize2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x0059  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static final kotlin.Unit LinearProgressIndicator_rIrjwxo$lambda$13$lambda$12(int r12, float r13, androidx.compose.runtime.State r14, long r15, androidx.compose.runtime.State r17, long r18, androidx.compose.runtime.State r20, androidx.compose.runtime.State r21, androidx.compose.ui.graphics.drawscope.DrawScope r22) {
        /*
            Method dump skipped, instruction units count: 413
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.ProgressIndicatorKt.LinearProgressIndicator_rIrjwxo$lambda$13$lambda$12(int, float, androidx.compose.runtime.State, long, androidx.compose.runtime.State, long, androidx.compose.runtime.State, androidx.compose.runtime.State, androidx.compose.ui.graphics.drawscope.DrawScope):kotlin.Unit");
    }

    @Deprecated(message = "Use the overload that takes `progress` as a lambda", replaceWith = @ReplaceWith(expression = "LinearProgressIndicator(\nprogress = { progress },\nmodifier = modifier,\ncolor = color,\ntrackColor = trackColor,\nstrokeCap = strokeCap,\n)", imports = {}))
    /* JADX INFO: renamed from: LinearProgressIndicator-_5eSR-E, reason: not valid java name */
    public static final void m2822LinearProgressIndicator_5eSRE(final float progress, Modifier modifier, long color, long trackColor, int strokeCap, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        long color2;
        long trackColor2;
        int i2;
        Composer $composer2;
        final long color3;
        final Modifier modifier3;
        final long trackColor3;
        final int strokeCap2;
        int strokeCap3;
        long trackColor4;
        long trackColor5;
        Modifier modifier4;
        Composer $composer3 = $composer.startRestartGroup(905419617);
        ComposerKt.sourceInformation($composer3, "C(LinearProgressIndicator)N(progress,modifier,color:c#ui.graphics.Color,trackColor:c#ui.graphics.Color,strokeCap:c#ui.graphics.StrokeCap)360@15142L12,359@15098L179:ProgressIndicator.kt#uh7d8r");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(progress) ? 4 : 2;
        }
        int i3 = i & 2;
        if (i3 != 0) {
            $dirty |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty |= $composer3.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                color2 = color;
                int i4 = $composer3.changed(color2) ? 256 : 128;
                $dirty |= i4;
            } else {
                color2 = color;
            }
            $dirty |= i4;
        } else {
            color2 = color;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                trackColor2 = trackColor;
                int i5 = $composer3.changed(trackColor2) ? 2048 : 1024;
                $dirty |= i5;
            } else {
                trackColor2 = trackColor;
            }
            $dirty |= i5;
        } else {
            trackColor2 = trackColor;
        }
        int i6 = i & 16;
        if (i6 != 0) {
            $dirty |= 24576;
            i2 = strokeCap;
        } else if (($changed & 24576) == 0) {
            i2 = strokeCap;
            $dirty |= $composer3.changed(i2) ? 16384 : 8192;
        } else {
            i2 = strokeCap;
        }
        if ($composer3.shouldExecute(($dirty & 9363) != 9362, $dirty & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "355@14939L11,356@15002L16");
            if (($changed & 1) != 0 && !$composer3.getDefaultsInvalid()) {
                $composer3.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty &= -897;
                }
                if ((i & 8) != 0) {
                    $dirty &= -7169;
                }
                strokeCap3 = i2;
                trackColor4 = trackColor2;
                trackColor5 = color2;
                modifier4 = modifier2;
            } else {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if ((i & 4) != 0) {
                    color2 = ProgressIndicatorDefaults.INSTANCE.getLinearColor($composer3, 6);
                    $dirty &= -897;
                }
                if ((i & 8) != 0) {
                    trackColor2 = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor($composer3, 6);
                    $dirty &= -7169;
                }
                if (i6 == 0) {
                    strokeCap3 = i2;
                    trackColor4 = trackColor2;
                    trackColor5 = color2;
                    modifier4 = modifier2;
                } else {
                    strokeCap3 = ProgressIndicatorDefaults.INSTANCE.m2810getLinearStrokeCapKaPHkGw();
                    trackColor4 = trackColor2;
                    trackColor5 = color2;
                    modifier4 = modifier2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(905419617, $dirty, -1, "androidx.compose.material3.LinearProgressIndicator (ProgressIndicator.kt:359)");
            }
            ComposerKt.sourceInformationMarkerStart($composer3, -1770084819, "CC(remember):ProgressIndicator.kt#9igjgp");
            boolean invalid$iv = ($dirty & 14) == 4;
            Object it$iv = $composer3.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = new Function0() { // from class: androidx.compose.material3.ProgressIndicatorKt$$ExternalSyntheticLambda19
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return Float.valueOf(ProgressIndicatorKt.LinearProgressIndicator__5eSR_E$lambda$16$lambda$15(progress));
                    }
                };
                $composer3.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer2 = $composer3;
            m2820LinearProgressIndicatorGJbTh5U((Function0) it$iv, modifier4, trackColor5, trackColor4, strokeCap3, 0.0f, null, $composer2, ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (57344 & $dirty), 96);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier4;
            trackColor3 = trackColor4;
            strokeCap2 = strokeCap3;
            color3 = trackColor5;
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            color3 = color2;
            modifier3 = modifier2;
            trackColor3 = trackColor2;
            strokeCap2 = i2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.ProgressIndicatorKt$$ExternalSyntheticLambda20
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ProgressIndicatorKt.LinearProgressIndicator__5eSR_E$lambda$17(progress, modifier3, color3, trackColor3, strokeCap2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    static final float LinearProgressIndicator__5eSR_E$lambda$16$lambda$15(float $progress) {
        return $progress;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility")
    /* JADX INFO: renamed from: LinearProgressIndicator-eaDK9VM, reason: not valid java name */
    public static final /* synthetic */ void m2824LinearProgressIndicatoreaDK9VM(final float progress, Modifier modifier, long color, long trackColor, Composer $composer, final int $changed, final int i) {
        float f;
        Modifier modifier2;
        long j;
        long j2;
        final Modifier modifier3;
        final long color2;
        final long trackColor2;
        long color3;
        long trackColor3;
        Modifier modifier4;
        long color4;
        Composer $composer2 = $composer.startRestartGroup(-372717133);
        ComposerKt.sourceInformation($composer2, "C(LinearProgressIndicator)N(progress,modifier,color:c#ui.graphics.Color,trackColor:c#ui.graphics.Color)376@15619L164:ProgressIndicator.kt#uh7d8r");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
            f = progress;
        } else if (($changed & 6) == 0) {
            f = progress;
            $dirty |= $composer2.changed(f) ? 4 : 2;
        } else {
            f = progress;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                j = color;
                int i3 = $composer2.changed(j) ? 256 : 128;
                $dirty |= i3;
            } else {
                j = color;
            }
            $dirty |= i3;
        } else {
            j = color;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                j2 = trackColor;
                int i4 = $composer2.changed(j2) ? 2048 : 1024;
                $dirty |= i4;
            } else {
                j2 = trackColor;
            }
            $dirty |= i4;
        } else {
            j2 = trackColor;
        }
        if ($composer2.shouldExecute(($dirty & 1171) != 1170, $dirty & 1)) {
            $composer2.startDefaults();
            ComposerKt.sourceInformation($composer2, "373@15530L11,374@15593L16");
            if (($changed & 1) == 0 || $composer2.getDefaultsInvalid()) {
                Modifier.Companion modifier5 = i2 != 0 ? Modifier.INSTANCE : modifier2;
                if ((i & 4) != 0) {
                    color3 = ProgressIndicatorDefaults.INSTANCE.getLinearColor($composer2, 6);
                    $dirty &= -897;
                } else {
                    color3 = j;
                }
                if ((i & 8) != 0) {
                    $dirty &= -7169;
                    modifier4 = modifier5;
                    color4 = color3;
                    trackColor3 = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor($composer2, 6);
                } else {
                    trackColor3 = j2;
                    modifier4 = modifier5;
                    color4 = color3;
                }
            } else {
                $composer2.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty &= -897;
                }
                if ((i & 8) != 0) {
                    $dirty &= -7169;
                }
                trackColor3 = j2;
                modifier4 = modifier2;
                color4 = j;
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-372717133, $dirty, -1, "androidx.compose.material3.LinearProgressIndicator (ProgressIndicator.kt:376)");
            }
            m2822LinearProgressIndicator_5eSRE(f, modifier4, color4, trackColor3, ProgressIndicatorDefaults.INSTANCE.m2810getLinearStrokeCapKaPHkGw(), $composer2, ($dirty & 14) | 24576 | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier4;
            color2 = color4;
            trackColor2 = trackColor3;
        } else {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            color2 = j;
            trackColor2 = j2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.ProgressIndicatorKt$$ExternalSyntheticLambda26
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ProgressIndicatorKt.LinearProgressIndicator_eaDK9VM$lambda$18(progress, modifier3, color2, trackColor2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility")
    /* JADX INFO: renamed from: LinearProgressIndicator-RIQooxk, reason: not valid java name */
    public static final /* synthetic */ void m2821LinearProgressIndicatorRIQooxk(Modifier modifier, long color, long trackColor, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        long j;
        long trackColor2;
        final Modifier modifier3;
        final long color2;
        final long trackColor3;
        long color3;
        long trackColor4;
        Modifier modifier4;
        long color4;
        Composer $composer2 = $composer.startRestartGroup(585576195);
        ComposerKt.sourceInformation($composer2, "C(LinearProgressIndicator)N(modifier,color:c#ui.graphics.Color,trackColor:c#ui.graphics.Color)391@16079L146:ProgressIndicator.kt#uh7d8r");
        int $dirty = $changed;
        int i2 = i & 1;
        if (i2 != 0) {
            $dirty |= 6;
            modifier2 = modifier;
        } else if (($changed & 6) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 4 : 2;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 48) == 0) {
            if ((i & 2) == 0) {
                j = color;
                int i3 = $composer2.changed(j) ? 32 : 16;
                $dirty |= i3;
            } else {
                j = color;
            }
            $dirty |= i3;
        } else {
            j = color;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                trackColor2 = trackColor;
                int i4 = $composer2.changed(trackColor2) ? 256 : 128;
                $dirty |= i4;
            } else {
                trackColor2 = trackColor;
            }
            $dirty |= i4;
        } else {
            trackColor2 = trackColor;
        }
        if ($composer2.shouldExecute(($dirty & 147) != 146, $dirty & 1)) {
            $composer2.startDefaults();
            ComposerKt.sourceInformation($composer2, "388@15990L11,389@16053L16");
            if (($changed & 1) == 0 || $composer2.getDefaultsInvalid()) {
                Modifier.Companion modifier5 = i2 != 0 ? Modifier.INSTANCE : modifier2;
                if ((i & 2) != 0) {
                    color3 = ProgressIndicatorDefaults.INSTANCE.getLinearColor($composer2, 6);
                    $dirty &= -113;
                } else {
                    color3 = j;
                }
                if ((i & 4) != 0) {
                    $dirty &= -897;
                    trackColor4 = ProgressIndicatorDefaults.INSTANCE.getLinearTrackColor($composer2, 6);
                    modifier4 = modifier5;
                    color4 = color3;
                } else {
                    trackColor4 = trackColor2;
                    modifier4 = modifier5;
                    color4 = color3;
                }
            } else {
                $composer2.skipToGroupEnd();
                if ((i & 2) != 0) {
                    $dirty &= -113;
                }
                if ((i & 4) != 0) {
                    $dirty &= -897;
                }
                trackColor4 = trackColor2;
                modifier4 = modifier2;
                color4 = j;
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(585576195, $dirty, -1, "androidx.compose.material3.LinearProgressIndicator (ProgressIndicator.kt:391)");
            }
            m2825LinearProgressIndicatorrIrjwxo(modifier4, color4, trackColor4, ProgressIndicatorDefaults.INSTANCE.m2810getLinearStrokeCapKaPHkGw(), 0.0f, $composer2, ($dirty & 14) | 3072 | ($dirty & 112) | ($dirty & 896), 16);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            color2 = color4;
            trackColor3 = trackColor4;
            modifier3 = modifier4;
        } else {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            color2 = j;
            trackColor3 = trackColor2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.ProgressIndicatorKt$$ExternalSyntheticLambda24
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ProgressIndicatorKt.LinearProgressIndicator_RIQooxk$lambda$19(modifier3, color2, trackColor3, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: drawLinearIndicator-qYKTg0g, reason: not valid java name */
    private static final void m2830drawLinearIndicatorqYKTg0g(DrawScope $this$drawLinearIndicator_u2dqYKTg0g, float startFraction, float endFraction, long color, float strokeWidth, int strokeCap) {
        long arg0$iv = $this$drawLinearIndicator_u2dqYKTg0g.mo5887getSizeNHjbRc();
        int bits$iv$iv$iv = (int) (arg0$iv >> 32);
        float width = Float.intBitsToFloat(bits$iv$iv$iv);
        long arg0$iv2 = $this$drawLinearIndicator_u2dqYKTg0g.mo5887getSizeNHjbRc();
        int bits$iv$iv$iv2 = (int) (arg0$iv2 & 4294967295L);
        float height = Float.intBitsToFloat(bits$iv$iv$iv2);
        float yOffset = height / 2.0f;
        boolean isLtr = $this$drawLinearIndicator_u2dqYKTg0g.getLayoutDirection() == LayoutDirection.Ltr;
        float barStart = (isLtr ? startFraction : 1.0f - endFraction) * width;
        float barEnd = (isLtr ? endFraction : 1.0f - startFraction) * width;
        if (StrokeCap.m5683equalsimpl0(strokeCap, StrokeCap.INSTANCE.m5687getButtKaPHkGw()) || height > width) {
            float yOffset2 = yOffset;
            char c = ' ';
            long j = 4294967295L;
            long v1$iv$iv = Float.floatToRawIntBits(barStart);
            long v2$iv$iv = Float.floatToRawIntBits(yOffset2);
            long jM5060constructorimpl = Offset.m5060constructorimpl((v1$iv$iv << c) | (v2$iv$iv & j));
            long v1$iv$iv2 = Float.floatToRawIntBits(barEnd);
            long v2$iv$iv2 = Float.floatToRawIntBits(yOffset2);
            DrawScope.m5873drawLineNGM6Ib0$default($this$drawLinearIndicator_u2dqYKTg0g, color, jM5060constructorimpl, Offset.m5060constructorimpl((v1$iv$iv2 << c) | (v2$iv$iv2 & j)), strokeWidth, 0, null, 0.0f, null, 0, 496, null);
            return;
        }
        float strokeCapOffset = strokeWidth / 2.0f;
        float maximumValue$iv = width - strokeCapOffset;
        float $this$fastCoerceAtLeast$iv$iv = barStart;
        if ($this$fastCoerceAtLeast$iv$iv < strokeCapOffset) {
            $this$fastCoerceAtLeast$iv$iv = strokeCapOffset;
        }
        if ($this$fastCoerceAtLeast$iv$iv > maximumValue$iv) {
            $this$fastCoerceAtLeast$iv$iv = maximumValue$iv;
        }
        float maximumValue$iv2 = $this$fastCoerceAtLeast$iv$iv;
        float maximumValue$iv3 = width - strokeCapOffset;
        float $this$fastCoerceAtLeast$iv$iv2 = barEnd;
        if ($this$fastCoerceAtLeast$iv$iv2 < strokeCapOffset) {
            $this$fastCoerceAtLeast$iv$iv2 = strokeCapOffset;
        }
        if ($this$fastCoerceAtLeast$iv$iv2 > maximumValue$iv3) {
            $this$fastCoerceAtLeast$iv$iv2 = maximumValue$iv3;
        }
        float adjustedBarEnd = $this$fastCoerceAtLeast$iv$iv2;
        if (Math.abs(endFraction - startFraction) > 0.0f) {
            long v1$iv$iv3 = Float.floatToRawIntBits(maximumValue$iv2);
            long v2$iv$iv3 = Float.floatToRawIntBits(yOffset);
            long v1$iv$iv4 = Offset.m5060constructorimpl((v1$iv$iv3 << 32) | (v2$iv$iv3 & 4294967295L));
            long v1$iv$iv5 = Float.floatToRawIntBits(adjustedBarEnd);
            long v2$iv$iv4 = Float.floatToRawIntBits(yOffset);
            DrawScope.m5873drawLineNGM6Ib0$default($this$drawLinearIndicator_u2dqYKTg0g, color, v1$iv$iv4, Offset.m5060constructorimpl((v1$iv$iv5 << 32) | (v2$iv$iv4 & 4294967295L)), strokeWidth, strokeCap, null, 0.0f, null, 0, WindowSizeClass.HEIGHT_DP_MEDIUM_LOWER_BOUND, null);
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Use the overload that takes `gapSize`, see `LegacyCircularProgressIndicatorSample` on how to restore the previous behavior", replaceWith = @ReplaceWith(expression = "CircularProgressIndicator(progress, modifier, color, strokeWidth, trackColor, strokeCap, gapSize)", imports = {}))
    /* JADX INFO: renamed from: CircularProgressIndicator-DUhRLBM, reason: not valid java name */
    public static final /* synthetic */ void m2814CircularProgressIndicatorDUhRLBM(final Function0 progress, Modifier modifier, long color, float strokeWidth, long trackColor, int strokeCap, Composer $composer, final int $changed, final int i) {
        Function0 function0;
        Modifier modifier2;
        long color2;
        float strokeWidth2;
        long trackColor2;
        int i2;
        Composer $composer2;
        final Modifier modifier3;
        final long color3;
        final long trackColor3;
        final float strokeWidth3;
        final int strokeCap2;
        Modifier.Companion modifier4;
        long trackColor4;
        int strokeCap3;
        Modifier modifier5;
        long color4;
        float strokeWidth4;
        int i3;
        Composer $composer3 = $composer.startRestartGroup(-761680467);
        ComposerKt.sourceInformation($composer3, "C(CircularProgressIndicator)N(progress,modifier,color:c#ui.graphics.Color,strokeWidth:c#ui.unit.Dp,trackColor:c#ui.graphics.Color,strokeCap:c#ui.graphics.StrokeCap)481@19831L218:ProgressIndicator.kt#uh7d8r");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
            function0 = progress;
        } else if (($changed & 6) == 0) {
            function0 = progress;
            $dirty |= $composer3.changedInstance(function0) ? 4 : 2;
        } else {
            function0 = progress;
        }
        int i4 = i & 2;
        if (i4 != 0) {
            $dirty |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty |= $composer3.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                color2 = color;
                int i5 = $composer3.changed(color2) ? 256 : 128;
                $dirty |= i5;
            } else {
                color2 = color;
            }
            $dirty |= i5;
        } else {
            color2 = color;
        }
        int i6 = i & 8;
        if (i6 != 0) {
            $dirty |= 3072;
            strokeWidth2 = strokeWidth;
        } else if (($changed & 3072) == 0) {
            strokeWidth2 = strokeWidth;
            $dirty |= $composer3.changed(strokeWidth2) ? 2048 : 1024;
        } else {
            strokeWidth2 = strokeWidth;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                trackColor2 = trackColor;
                int i7 = $composer3.changed(trackColor2) ? 16384 : 8192;
                $dirty |= i7;
            } else {
                trackColor2 = trackColor;
            }
            $dirty |= i7;
        } else {
            trackColor2 = trackColor;
        }
        int i8 = i & 32;
        if (i8 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            i2 = strokeCap;
        } else if ((196608 & $changed) == 0) {
            i2 = strokeCap;
            $dirty |= $composer3.changed(i2) ? 131072 : 65536;
        } else {
            i2 = strokeCap;
        }
        if (!$composer3.shouldExecute((74899 & $dirty) != 74898, $dirty & 1)) {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            color3 = color2;
            trackColor3 = trackColor2;
            strokeWidth3 = strokeWidth2;
            strokeCap2 = i2;
        } else {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "476@19575L13,478@19709L29");
            if (($changed & 1) != 0 && !$composer3.getDefaultsInvalid()) {
                $composer3.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty &= -897;
                }
                if ((i & 16) != 0) {
                    $dirty &= -57345;
                    trackColor4 = trackColor2;
                    strokeCap3 = i2;
                    i3 = -761680467;
                    modifier5 = modifier2;
                    color4 = color2;
                    strokeWidth4 = strokeWidth2;
                } else {
                    trackColor4 = trackColor2;
                    strokeCap3 = i2;
                    i3 = -761680467;
                    modifier5 = modifier2;
                    color4 = color2;
                    strokeWidth4 = strokeWidth2;
                }
            } else {
                if (i4 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if ((i & 4) != 0) {
                    color2 = ProgressIndicatorDefaults.INSTANCE.getCircularColor($composer3, 6);
                    $dirty &= -897;
                }
                if (i6 != 0) {
                    strokeWidth2 = ProgressIndicatorDefaults.INSTANCE.m2808getCircularStrokeWidthD9Ej5fM();
                }
                if ((i & 16) != 0) {
                    $dirty &= -57345;
                    trackColor2 = ProgressIndicatorDefaults.INSTANCE.getCircularDeterminateTrackColor($composer3, 6);
                }
                if (i8 == 0) {
                    trackColor4 = trackColor2;
                    strokeCap3 = i2;
                    modifier5 = modifier4;
                    color4 = color2;
                    strokeWidth4 = strokeWidth2;
                    i3 = -761680467;
                } else {
                    strokeCap3 = ProgressIndicatorDefaults.INSTANCE.m2805getCircularDeterminateStrokeCapKaPHkGw();
                    strokeWidth4 = strokeWidth2;
                    trackColor4 = trackColor2;
                    modifier5 = modifier4;
                    color4 = color2;
                    i3 = -761680467;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(i3, $dirty, -1, "androidx.compose.material3.CircularProgressIndicator (ProgressIndicator.kt:480)");
            }
            $composer2 = $composer3;
            m2815CircularProgressIndicatorIyT6zlY(function0, modifier5, color4, strokeWidth4, trackColor4, strokeCap3, ProgressIndicatorDefaults.INSTANCE.m2807getCircularIndicatorTrackGapSizeD9Ej5fM(), $composer2, ($dirty & 14) | 1572864 | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (57344 & $dirty) | (458752 & $dirty), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier5;
            color3 = color4;
            strokeWidth3 = strokeWidth4;
            trackColor3 = trackColor4;
            strokeCap2 = strokeCap3;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.ProgressIndicatorKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ProgressIndicatorKt.CircularProgressIndicator_DUhRLBM$lambda$20(progress, modifier3, color3, strokeWidth3, trackColor3, strokeCap2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:172:0x02d7  */
    /* JADX INFO: renamed from: CircularProgressIndicator-IyT6zlY, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void m2815CircularProgressIndicatorIyT6zlY(final kotlin.jvm.functions.Function0<java.lang.Float> r30, androidx.compose.ui.Modifier r31, long r32, float r34, long r35, int r37, float r38, androidx.compose.runtime.Composer r39, final int r40, final int r41) {
        /*
            Method dump skipped, instruction units count: 774
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.ProgressIndicatorKt.m2815CircularProgressIndicatorIyT6zlY(kotlin.jvm.functions.Function0, androidx.compose.ui.Modifier, long, float, long, int, float, androidx.compose.runtime.Composer, int, int):void");
    }

    static final float CircularProgressIndicator_IyT6zlY$lambda$22$lambda$21(Function0 $progress) {
        float $this$fastCoerceIn$iv = ((Number) $progress.invoke()).floatValue();
        float $this$fastCoerceAtLeast$iv$iv = $this$fastCoerceIn$iv;
        if ($this$fastCoerceAtLeast$iv$iv < 0.0f) {
            $this$fastCoerceAtLeast$iv$iv = 0.0f;
        }
        if ($this$fastCoerceAtLeast$iv$iv > 1.0f) {
            return 1.0f;
        }
        return $this$fastCoerceAtLeast$iv$iv;
    }

    static final Unit CircularProgressIndicator_IyT6zlY$lambda$26$lambda$25(Function0 $coercedProgress, SemanticsPropertyReceiver $this$semantics) {
        Object objInvoke = $coercedProgress.invoke();
        float it = ((Number) objInvoke).floatValue();
        if (Float.isNaN(it)) {
            objInvoke = null;
        }
        Float f = (Float) objInvoke;
        SemanticsPropertiesKt.setProgressBarRangeInfo($this$semantics, new ProgressBarRangeInfo(f != null ? f.floatValue() : 0.0f, RangesKt.rangeTo(0.0f, 1.0f), 0, 4, null));
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x0052  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static final kotlin.Unit CircularProgressIndicator_IyT6zlY$lambda$28$lambda$27(kotlin.jvm.functions.Function0 r18, int r19, float r20, float r21, long r22, androidx.compose.ui.graphics.drawscope.Stroke r24, long r25, androidx.compose.ui.graphics.drawscope.DrawScope r27) {
        /*
            r1 = 1132920832(0x43870000, float:270.0)
            java.lang.Object r0 = r18.invoke()
            java.lang.Number r0 = (java.lang.Number) r0
            float r0 = r0.floatValue()
            r2 = 1135869952(0x43b40000, float:360.0)
            float r0 = r0 * r2
            androidx.compose.ui.graphics.StrokeCap$Companion r3 = androidx.compose.ui.graphics.StrokeCap.INSTANCE
            int r3 = r3.m5687getButtKaPHkGw()
            r6 = r19
            boolean r3 = androidx.compose.ui.graphics.StrokeCap.m5683equalsimpl0(r6, r3)
            r4 = 32
            if (r3 != 0) goto L52
            long r7 = r27.mo5887getSizeNHjbRc()
            r3 = 0
            r9 = r7
            r5 = 0
            r11 = 4294967295(0xffffffff, double:2.1219957905E-314)
            long r11 = r11 & r9
            int r11 = (int) r11
            r12 = 0
            float r11 = java.lang.Float.intBitsToFloat(r11)
            long r7 = r27.mo5887getSizeNHjbRc()
            r3 = 0
            r9 = r7
            r5 = 0
            long r12 = r9 >> r4
            int r12 = (int) r12
            r13 = 0
            float r12 = java.lang.Float.intBitsToFloat(r12)
            int r3 = (r11 > r12 ? 1 : (r11 == r12 ? 0 : -1))
            if (r3 <= 0) goto L4a
            goto L52
        L4a:
            r3 = 0
            float r5 = r20 + r21
            float r5 = androidx.compose.ui.unit.Dp.m8150constructorimpl(r5)
            goto L54
        L52:
            r5 = r20
        L54:
            r7 = r5
            long r8 = r27.mo5887getSizeNHjbRc()
            r3 = 0
            r10 = r8
            r5 = 0
            long r12 = r10 >> r4
            int r4 = (int) r12
            r12 = 0
            float r4 = java.lang.Float.intBitsToFloat(r4)
            r12 = r27
            float r3 = r12.mo428toDpu2uoSUM(r4)
            double r3 = (double) r3
            r8 = 4614256656552045848(0x400921fb54442d18, double:3.141592653589793)
            double r3 = r3 * r8
            float r3 = (float) r3
            float r3 = r7 / r3
            float r8 = r3 * r2
            float r3 = r1 + r0
            float r4 = java.lang.Math.min(r0, r8)
            float r13 = r3 + r4
            float r2 = r2 - r0
            float r3 = java.lang.Math.min(r0, r8)
            r4 = 1073741824(0x40000000, float:2.0)
            float r3 = r3 * r4
            float r14 = r2 - r3
            r15 = r22
            r17 = r24
            m2826drawCircularIndicator42QJj7c(r12, r13, r14, r15, r17)
            r5 = r24
            r3 = r25
            r2 = r0
            r0 = r27
            m2828drawDeterminateCircularIndicator42QJj7c(r0, r1, r2, r3, r5)
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.ProgressIndicatorKt.CircularProgressIndicator_IyT6zlY$lambda$28$lambda$27(kotlin.jvm.functions.Function0, int, float, float, long, androidx.compose.ui.graphics.drawscope.Stroke, long, androidx.compose.ui.graphics.drawscope.DrawScope):kotlin.Unit");
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Use the overload that takes `gapSize`", replaceWith = @ReplaceWith(expression = "CircularProgressIndicator(modifier, color, strokeWidth, trackColor, strokeCap, gapSize)", imports = {}))
    /* JADX INFO: renamed from: CircularProgressIndicator-LxG7B9w, reason: not valid java name */
    public static final /* synthetic */ void m2816CircularProgressIndicatorLxG7B9w(Modifier modifier, long color, float strokeWidth, long trackColor, int strokeCap, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        long j;
        float f;
        long j2;
        int i2;
        Composer $composer2;
        final long color2;
        final long trackColor2;
        final Modifier modifier3;
        final float strokeWidth2;
        final int strokeCap2;
        long color3;
        long trackColor3;
        float strokeWidth3;
        int strokeCap3;
        long trackColor4;
        Modifier modifier4;
        long color4;
        Composer $composer3 = $composer.startRestartGroup(-115871647);
        ComposerKt.sourceInformation($composer3, "C(CircularProgressIndicator)N(modifier,color:c#ui.graphics.Color,strokeWidth:c#ui.unit.Dp,trackColor:c#ui.graphics.Color,strokeCap:c#ui.graphics.StrokeCap)594@24879L258:ProgressIndicator.kt#uh7d8r");
        int $dirty = $changed;
        int i3 = i & 1;
        if (i3 != 0) {
            $dirty |= 6;
            modifier2 = modifier;
        } else if (($changed & 6) == 0) {
            modifier2 = modifier;
            $dirty |= $composer3.changed(modifier2) ? 4 : 2;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 48) == 0) {
            if ((i & 2) == 0) {
                j = color;
                int i4 = $composer3.changed(j) ? 32 : 16;
                $dirty |= i4;
            } else {
                j = color;
            }
            $dirty |= i4;
        } else {
            j = color;
        }
        int i5 = i & 4;
        if (i5 != 0) {
            $dirty |= 384;
            f = strokeWidth;
        } else if (($changed & 384) == 0) {
            f = strokeWidth;
            $dirty |= $composer3.changed(f) ? 256 : 128;
        } else {
            f = strokeWidth;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                j2 = trackColor;
                int i6 = $composer3.changed(j2) ? 2048 : 1024;
                $dirty |= i6;
            } else {
                j2 = trackColor;
            }
            $dirty |= i6;
        } else {
            j2 = trackColor;
        }
        int i7 = i & 16;
        if (i7 != 0) {
            $dirty |= 24576;
            i2 = strokeCap;
        } else if (($changed & 24576) == 0) {
            i2 = strokeCap;
            $dirty |= $composer3.changed(i2) ? 16384 : 8192;
        } else {
            i2 = strokeCap;
        }
        if ($composer3.shouldExecute(($dirty & 9363) != 9362, $dirty & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "589@24619L13,591@24753L31");
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                Modifier.Companion modifier5 = i3 != 0 ? Modifier.INSTANCE : modifier2;
                if ((i & 2) != 0) {
                    color3 = ProgressIndicatorDefaults.INSTANCE.getCircularColor($composer3, 6);
                    $dirty &= -113;
                } else {
                    color3 = j;
                }
                float strokeWidth4 = i5 != 0 ? ProgressIndicatorDefaults.INSTANCE.m2808getCircularStrokeWidthD9Ej5fM() : f;
                if ((i & 8) != 0) {
                    trackColor3 = ProgressIndicatorDefaults.INSTANCE.getCircularIndeterminateTrackColor($composer3, 6);
                    $dirty &= -7169;
                } else {
                    trackColor3 = j2;
                }
                if (i7 != 0) {
                    strokeWidth3 = strokeWidth4;
                    strokeCap3 = ProgressIndicatorDefaults.INSTANCE.m2806getCircularIndeterminateStrokeCapKaPHkGw();
                    trackColor4 = trackColor3;
                    modifier4 = modifier5;
                    color4 = color3;
                } else {
                    strokeWidth3 = strokeWidth4;
                    strokeCap3 = i2;
                    trackColor4 = trackColor3;
                    modifier4 = modifier5;
                    color4 = color3;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 2) != 0) {
                    $dirty &= -113;
                }
                if ((i & 8) != 0) {
                    $dirty &= -7169;
                }
                strokeWidth3 = f;
                strokeCap3 = i2;
                modifier4 = modifier2;
                trackColor4 = j2;
                color4 = j;
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-115871647, $dirty, -1, "androidx.compose.material3.CircularProgressIndicator (ProgressIndicator.kt:594)");
            }
            $composer2 = $composer3;
            m2812CircularProgressIndicator4lLiAd8(modifier4, color4, strokeWidth3, trackColor4, strokeCap3, ProgressIndicatorDefaults.INSTANCE.m2807getCircularIndicatorTrackGapSizeD9Ej5fM(), $composer2, ($dirty & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (57344 & $dirty), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            color2 = color4;
            modifier3 = modifier4;
            strokeWidth2 = strokeWidth3;
            trackColor2 = trackColor4;
            strokeCap2 = strokeCap3;
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            color2 = j;
            trackColor2 = j2;
            modifier3 = modifier2;
            strokeWidth2 = f;
            strokeCap2 = i2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.ProgressIndicatorKt$$ExternalSyntheticLambda9
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ProgressIndicatorKt.CircularProgressIndicator_LxG7B9w$lambda$30(modifier3, color2, strokeWidth2, trackColor2, strokeCap2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:146:0x0304  */
    /* JADX INFO: renamed from: CircularProgressIndicator-4lLiAd8, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void m2812CircularProgressIndicator4lLiAd8(androidx.compose.ui.Modifier r34, long r35, float r37, long r38, int r40, float r41, androidx.compose.runtime.Composer r42, final int r43, final int r44) {
        /*
            Method dump skipped, instruction units count: 817
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.ProgressIndicatorKt.m2812CircularProgressIndicator4lLiAd8(androidx.compose.ui.Modifier, long, float, long, int, float, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x0051  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static final kotlin.Unit CircularProgressIndicator_4lLiAd8$lambda$34$lambda$33(androidx.compose.runtime.State r26, int r27, float r28, float r29, androidx.compose.runtime.State r30, androidx.compose.runtime.State r31, long r32, androidx.compose.ui.graphics.drawscope.Stroke r34, long r35, androidx.compose.ui.graphics.drawscope.DrawScope r37) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 262
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.ProgressIndicatorKt.CircularProgressIndicator_4lLiAd8$lambda$34$lambda$33(androidx.compose.runtime.State, int, float, float, androidx.compose.runtime.State, androidx.compose.runtime.State, long, androidx.compose.ui.graphics.drawscope.Stroke, long, androidx.compose.ui.graphics.drawscope.DrawScope):kotlin.Unit");
    }

    @Deprecated(message = "Use the overload that takes `progress` as a lambda", replaceWith = @ReplaceWith(expression = "CircularProgressIndicator(\nprogress = { progress },\nmodifier = modifier,\ncolor = color,\nstrokeWidth = strokeWidth,\ntrackColor = trackColor,\nstrokeCap = strokeCap,\n)", imports = {}))
    /* JADX INFO: renamed from: CircularProgressIndicator-DUhRLBM, reason: not valid java name */
    public static final void m2813CircularProgressIndicatorDUhRLBM(final float progress, Modifier modifier, long color, float strokeWidth, long trackColor, int strokeCap, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        long color2;
        float strokeWidth2;
        long trackColor2;
        int i2;
        Composer $composer2;
        final Modifier modifier3;
        final float strokeWidth3;
        final long color3;
        final long trackColor3;
        final int strokeCap2;
        float strokeWidth4;
        boolean invalid$iv;
        int i3;
        Modifier modifier4;
        int strokeCap3;
        long trackColor4;
        long trackColor5;
        Composer $composer3 = $composer.startRestartGroup(-1472321743);
        ComposerKt.sourceInformation($composer3, "C(CircularProgressIndicator)N(progress,modifier,color:c#ui.graphics.Color,strokeWidth:c#ui.unit.Dp,trackColor:c#ui.graphics.Color,strokeCap:c#ui.graphics.StrokeCap)706@29604L12,705@29558L216:ProgressIndicator.kt#uh7d8r");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(progress) ? 4 : 2;
        }
        int i4 = i & 2;
        if (i4 != 0) {
            $dirty |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty |= $composer3.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                color2 = color;
                int i5 = $composer3.changed(color2) ? 256 : 128;
                $dirty |= i5;
            } else {
                color2 = color;
            }
            $dirty |= i5;
        } else {
            color2 = color;
        }
        int i6 = i & 8;
        if (i6 != 0) {
            $dirty |= 3072;
            strokeWidth2 = strokeWidth;
        } else if (($changed & 3072) == 0) {
            strokeWidth2 = strokeWidth;
            $dirty |= $composer3.changed(strokeWidth2) ? 2048 : 1024;
        } else {
            strokeWidth2 = strokeWidth;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                trackColor2 = trackColor;
                int i7 = $composer3.changed(trackColor2) ? 16384 : 8192;
                $dirty |= i7;
            } else {
                trackColor2 = trackColor;
            }
            $dirty |= i7;
        } else {
            trackColor2 = trackColor;
        }
        int i8 = i & 32;
        if (i8 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            i2 = strokeCap;
        } else if ((196608 & $changed) == 0) {
            i2 = strokeCap;
            $dirty |= $composer3.changed(i2) ? 131072 : 65536;
        } else {
            i2 = strokeCap;
        }
        if ($composer3.shouldExecute(($dirty & 74899) != 74898, $dirty & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "700@29313L13,702@29447L18");
            if (($changed & 1) != 0 && !$composer3.getDefaultsInvalid()) {
                $composer3.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty &= -897;
                }
                if ((i & 16) != 0) {
                    $dirty &= -57345;
                    strokeWidth4 = strokeWidth2;
                    invalid$iv = false;
                    i3 = -1472321743;
                    modifier4 = modifier2;
                    strokeCap3 = i2;
                    trackColor4 = trackColor2;
                    trackColor5 = color2;
                } else {
                    strokeWidth4 = strokeWidth2;
                    invalid$iv = false;
                    i3 = -1472321743;
                    modifier4 = modifier2;
                    strokeCap3 = i2;
                    trackColor4 = trackColor2;
                    trackColor5 = color2;
                }
            } else {
                if (i4 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if ((i & 4) != 0) {
                    color2 = ProgressIndicatorDefaults.INSTANCE.getCircularColor($composer3, 6);
                    $dirty &= -897;
                }
                if (i6 != 0) {
                    strokeWidth2 = ProgressIndicatorDefaults.INSTANCE.m2808getCircularStrokeWidthD9Ej5fM();
                }
                if ((i & 16) != 0) {
                    trackColor2 = ProgressIndicatorDefaults.INSTANCE.getCircularTrackColor($composer3, 6);
                    $dirty &= -57345;
                }
                if (i8 == 0) {
                    strokeWidth4 = strokeWidth2;
                    invalid$iv = false;
                    i3 = -1472321743;
                    modifier4 = modifier2;
                    strokeCap3 = i2;
                    trackColor4 = trackColor2;
                    trackColor5 = color2;
                } else {
                    strokeCap3 = ProgressIndicatorDefaults.INSTANCE.m2805getCircularDeterminateStrokeCapKaPHkGw();
                    invalid$iv = false;
                    strokeWidth4 = strokeWidth2;
                    trackColor4 = trackColor2;
                    i3 = -1472321743;
                    modifier4 = modifier2;
                    trackColor5 = color2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(i3, $dirty, -1, "androidx.compose.material3.CircularProgressIndicator (ProgressIndicator.kt:705)");
            }
            ComposerKt.sourceInformationMarkerStart($composer3, -1856924995, "CC(remember):ProgressIndicator.kt#9igjgp");
            if (($dirty & 14) == 4) {
                invalid$iv = true;
            }
            Object it$iv = $composer3.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = new Function0() { // from class: androidx.compose.material3.ProgressIndicatorKt$$ExternalSyntheticLambda29
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return Float.valueOf(ProgressIndicatorKt.CircularProgressIndicator_DUhRLBM$lambda$37$lambda$36(progress));
                    }
                };
                $composer3.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer2 = $composer3;
            m2815CircularProgressIndicatorIyT6zlY((Function0) it$iv, modifier4, trackColor5, strokeWidth4, trackColor4, strokeCap3, 0.0f, $composer2, ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (57344 & $dirty) | (458752 & $dirty), 64);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier4;
            strokeWidth3 = strokeWidth4;
            trackColor3 = trackColor4;
            strokeCap2 = strokeCap3;
            color3 = trackColor5;
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            strokeWidth3 = strokeWidth2;
            color3 = color2;
            trackColor3 = trackColor2;
            strokeCap2 = i2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.ProgressIndicatorKt$$ExternalSyntheticLambda30
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ProgressIndicatorKt.CircularProgressIndicator_DUhRLBM$lambda$38(progress, modifier3, color3, strokeWidth3, trackColor3, strokeCap2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    static final float CircularProgressIndicator_DUhRLBM$lambda$37$lambda$36(float $progress) {
        return $progress;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility")
    /* JADX INFO: renamed from: CircularProgressIndicator-MBs18nI, reason: not valid java name */
    public static final /* synthetic */ void m2817CircularProgressIndicatorMBs18nI(final float progress, Modifier modifier, long color, float strokeWidth, Composer $composer, final int $changed, final int i) {
        float f;
        Modifier modifier2;
        long j;
        float f2;
        final Modifier modifier3;
        final long color2;
        final float strokeWidth2;
        long color3;
        Modifier modifier4;
        float strokeWidth3;
        long color4;
        Composer $composer2 = $composer.startRestartGroup(402841196);
        ComposerKt.sourceInformation($composer2, "C(CircularProgressIndicator)N(progress,modifier,color:c#ui.graphics.Color,strokeWidth:c#ui.unit.Dp)728@30267L18,723@30121L247:ProgressIndicator.kt#uh7d8r");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
            f = progress;
        } else if (($changed & 6) == 0) {
            f = progress;
            $dirty |= $composer2.changed(f) ? 4 : 2;
        } else {
            f = progress;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                j = color;
                int i3 = $composer2.changed(j) ? 256 : 128;
                $dirty |= i3;
            } else {
                j = color;
            }
            $dirty |= i3;
        } else {
            j = color;
        }
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty |= 3072;
            f2 = strokeWidth;
        } else if (($changed & 3072) == 0) {
            f2 = strokeWidth;
            $dirty |= $composer2.changed(f2) ? 2048 : 1024;
        } else {
            f2 = strokeWidth;
        }
        if ($composer2.shouldExecute(($dirty & 1171) != 1170, $dirty & 1)) {
            $composer2.startDefaults();
            ComposerKt.sourceInformation($composer2, "720@30029L13");
            if (($changed & 1) == 0 || $composer2.getDefaultsInvalid()) {
                Modifier.Companion modifier5 = i2 != 0 ? Modifier.INSTANCE : modifier2;
                if ((i & 4) != 0) {
                    color3 = ProgressIndicatorDefaults.INSTANCE.getCircularColor($composer2, 6);
                    $dirty &= -897;
                } else {
                    color3 = j;
                }
                if (i4 != 0) {
                    strokeWidth3 = ProgressIndicatorDefaults.INSTANCE.m2808getCircularStrokeWidthD9Ej5fM();
                    modifier4 = modifier5;
                    color4 = color3;
                } else {
                    modifier4 = modifier5;
                    strokeWidth3 = f2;
                    color4 = color3;
                }
            } else {
                $composer2.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty &= -897;
                }
                strokeWidth3 = f2;
                color4 = j;
                modifier4 = modifier2;
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(402841196, $dirty, -1, "androidx.compose.material3.CircularProgressIndicator (ProgressIndicator.kt:723)");
            }
            float strokeWidth4 = strokeWidth3;
            m2813CircularProgressIndicatorDUhRLBM(f, modifier4, color4, strokeWidth4, ProgressIndicatorDefaults.INSTANCE.getCircularTrackColor($composer2, 6), ProgressIndicatorDefaults.INSTANCE.m2805getCircularDeterminateStrokeCapKaPHkGw(), $composer2, ($dirty & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier4;
            color2 = color4;
            strokeWidth2 = strokeWidth4;
        } else {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            color2 = j;
            strokeWidth2 = f2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.ProgressIndicatorKt$$ExternalSyntheticLambda22
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ProgressIndicatorKt.CircularProgressIndicator_MBs18nI$lambda$39(progress, modifier3, color2, strokeWidth2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility")
    /* JADX INFO: renamed from: CircularProgressIndicator-aM-cp0Q, reason: not valid java name */
    public static final /* synthetic */ void m2818CircularProgressIndicatoraMcp0Q(Modifier modifier, long color, float strokeWidth, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        long color2;
        float f;
        final Modifier modifier3;
        final long color3;
        final float strokeWidth2;
        float strokeWidth3;
        long color4;
        Modifier modifier4;
        Composer $composer2 = $composer.startRestartGroup(947193756);
        ComposerKt.sourceInformation($composer2, "C(CircularProgressIndicator)N(modifier,color:c#ui.graphics.Color,strokeWidth:c#ui.unit.Dp)744@30822L18,740@30694L231:ProgressIndicator.kt#uh7d8r");
        int $dirty = $changed;
        int i2 = i & 1;
        if (i2 != 0) {
            $dirty |= 6;
            modifier2 = modifier;
        } else if (($changed & 6) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 4 : 2;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 48) == 0) {
            if ((i & 2) == 0) {
                color2 = color;
                int i3 = $composer2.changed(color2) ? 32 : 16;
                $dirty |= i3;
            } else {
                color2 = color;
            }
            $dirty |= i3;
        } else {
            color2 = color;
        }
        int i4 = i & 4;
        if (i4 != 0) {
            $dirty |= 384;
            f = strokeWidth;
        } else if (($changed & 384) == 0) {
            f = strokeWidth;
            $dirty |= $composer2.changed(f) ? 256 : 128;
        } else {
            f = strokeWidth;
        }
        if ($composer2.shouldExecute(($dirty & 147) != 146, $dirty & 1)) {
            $composer2.startDefaults();
            ComposerKt.sourceInformation($composer2, "737@30602L13");
            if (($changed & 1) == 0 || $composer2.getDefaultsInvalid()) {
                Modifier.Companion modifier5 = i2 != 0 ? Modifier.INSTANCE : modifier2;
                if ((i & 2) != 0) {
                    color2 = ProgressIndicatorDefaults.INSTANCE.getCircularColor($composer2, 6);
                    $dirty &= -113;
                }
                if (i4 != 0) {
                    strokeWidth3 = ProgressIndicatorDefaults.INSTANCE.m2808getCircularStrokeWidthD9Ej5fM();
                    color4 = color2;
                    modifier4 = modifier5;
                } else {
                    strokeWidth3 = f;
                    color4 = color2;
                    modifier4 = modifier5;
                }
            } else {
                $composer2.skipToGroupEnd();
                if ((i & 2) != 0) {
                    $dirty &= -113;
                }
                strokeWidth3 = f;
                color4 = color2;
                modifier4 = modifier2;
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(947193756, $dirty, -1, "androidx.compose.material3.CircularProgressIndicator (ProgressIndicator.kt:740)");
            }
            m2812CircularProgressIndicator4lLiAd8(modifier4, color4, strokeWidth3, ProgressIndicatorDefaults.INSTANCE.getCircularTrackColor($composer2, 6), ProgressIndicatorDefaults.INSTANCE.m2806getCircularIndeterminateStrokeCapKaPHkGw(), 0.0f, $composer2, ($dirty & 14) | 24576 | ($dirty & 112) | ($dirty & 896), 32);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            color3 = color4;
            strokeWidth2 = strokeWidth3;
            modifier3 = modifier4;
        } else {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            color3 = color2;
            strokeWidth2 = f;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.ProgressIndicatorKt$$ExternalSyntheticLambda28
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ProgressIndicatorKt.CircularProgressIndicator_aM_cp0Q$lambda$40(modifier3, color3, strokeWidth2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: drawCircularIndicator-42QJj7c, reason: not valid java name */
    private static final void m2826drawCircularIndicator42QJj7c(DrawScope $this$drawCircularIndicator_u2d42QJj7c, float startAngle, float sweep, long color, Stroke stroke) {
        float diameterOffset = stroke.getWidth() / 2.0f;
        long arg0$iv = $this$drawCircularIndicator_u2d42QJj7c.mo5887getSizeNHjbRc();
        int bits$iv$iv$iv = (int) (arg0$iv >> 32);
        float arcDimen = Float.intBitsToFloat(bits$iv$iv$iv) - (2.0f * diameterOffset);
        long v1$iv$iv = Float.floatToRawIntBits(diameterOffset);
        long v2$iv$iv = Float.floatToRawIntBits(diameterOffset);
        long jM5060constructorimpl = Offset.m5060constructorimpl((v1$iv$iv << 32) | (v2$iv$iv & 4294967295L));
        long v1$iv$iv2 = Float.floatToRawIntBits(arcDimen);
        long v2$iv$iv2 = Float.floatToRawIntBits(arcDimen);
        DrawScope.m5866drawArcyD3GUKo$default($this$drawCircularIndicator_u2d42QJj7c, color, startAngle, sweep, false, jM5060constructorimpl, Size.m5128constructorimpl((v1$iv$iv2 << 32) | (v2$iv$iv2 & 4294967295L)), 0.0f, stroke, null, 0, 832, null);
    }

    /* JADX INFO: renamed from: drawCircularIndicatorTrack-bw27NRU, reason: not valid java name */
    private static final void m2827drawCircularIndicatorTrackbw27NRU(DrawScope $this$drawCircularIndicatorTrack_u2dbw27NRU, long color, Stroke stroke) {
        m2826drawCircularIndicator42QJj7c($this$drawCircularIndicatorTrack_u2dbw27NRU, 0.0f, 360.0f, color, stroke);
    }

    /* JADX INFO: renamed from: drawDeterminateCircularIndicator-42QJj7c, reason: not valid java name */
    private static final void m2828drawDeterminateCircularIndicator42QJj7c(DrawScope $this$drawDeterminateCircularIndicator_u2d42QJj7c, float startAngle, float sweep, long color, Stroke stroke) {
        m2826drawCircularIndicator42QJj7c($this$drawDeterminateCircularIndicator_u2d42QJj7c, startAngle, sweep, color, stroke);
    }

    /* JADX INFO: renamed from: drawIndeterminateCircularIndicator-hrjfTZI, reason: not valid java name */
    private static final void m2829drawIndeterminateCircularIndicatorhrjfTZI(DrawScope $this$drawIndeterminateCircularIndicator_u2dhrjfTZI, float startAngle, float strokeWidth, float sweep, long color, Stroke stroke) {
        float strokeCapOffset;
        if (StrokeCap.m5683equalsimpl0(stroke.getCap(), StrokeCap.INSTANCE.m5687getButtKaPHkGw())) {
            strokeCapOffset = 0.0f;
        } else {
            float arg0$iv = CircularIndicatorDiameter;
            float other$iv = strokeWidth / Dp.m8150constructorimpl(arg0$iv / 2);
            strokeCapOffset = (other$iv * 57.29578f) / 2.0f;
        }
        float adjustedStartAngle = startAngle + strokeCapOffset;
        float adjustedSweep = Math.max(sweep, 0.1f);
        m2826drawCircularIndicator42QJj7c($this$drawIndeterminateCircularIndicator_u2dhrjfTZI, adjustedStartAngle, adjustedSweep, color, stroke);
    }

    public static final InfiniteRepeatableSpec<Float> getCircularIndeterminateGlobalRotationAnimationSpec() {
        return AnimationSpecKt.m194infiniteRepeatable9IiC70o$default(AnimationSpecKt.tween$default(CircularAnimationProgressDuration, 0, EasingKt.getLinearEasing(), 2, null), null, 0L, 6, null);
    }

    public static final InfiniteRepeatableSpec<Float> getCircularIndeterminateRotationAnimationSpec() {
        return AnimationSpecKt.m194infiniteRepeatable9IiC70o$default(AnimationSpecKt.keyframes(new Function1() { // from class: androidx.compose.material3.ProgressIndicatorKt$$ExternalSyntheticLambda23
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ProgressIndicatorKt._get_circularIndeterminateRotationAnimationSpec_$lambda$41((KeyframesSpec.KeyframesSpecConfig) obj);
            }
        }), null, 0L, 6, null);
    }

    static final Unit _get_circularIndeterminateRotationAnimationSpec_$lambda$41(KeyframesSpec.KeyframesSpecConfig $this$keyframes) {
        $this$keyframes.setDurationMillis(CircularAnimationProgressDuration);
        Float fValueOf = Float.valueOf(90.0f);
        $this$keyframes.using($this$keyframes.at(fValueOf, 300), MotionTokens.INSTANCE.getEasingEmphasizedDecelerateCubicBezier());
        $this$keyframes.at(fValueOf, CircularAnimationAdditionalRotationDelay);
        Float fValueOf2 = Float.valueOf(180.0f);
        $this$keyframes.at(fValueOf2, 1800);
        $this$keyframes.at(fValueOf2, 3000);
        Float fValueOf3 = Float.valueOf(270.0f);
        $this$keyframes.at(fValueOf3, 3300);
        $this$keyframes.at(fValueOf3, 4500);
        Float fValueOf4 = Float.valueOf(360.0f);
        $this$keyframes.at(fValueOf4, 4800);
        $this$keyframes.at(fValueOf4, CircularAnimationProgressDuration);
        return Unit.INSTANCE;
    }

    public static final InfiniteRepeatableSpec<Float> getCircularIndeterminateProgressAnimationSpec() {
        return AnimationSpecKt.m194infiniteRepeatable9IiC70o$default(AnimationSpecKt.keyframes(new Function1() { // from class: androidx.compose.material3.ProgressIndicatorKt$$ExternalSyntheticLambda21
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ProgressIndicatorKt._get_circularIndeterminateProgressAnimationSpec_$lambda$42((KeyframesSpec.KeyframesSpecConfig) obj);
            }
        }), null, 0L, 6, null);
    }

    static final Unit _get_circularIndeterminateProgressAnimationSpec_$lambda$42(KeyframesSpec.KeyframesSpecConfig $this$keyframes) {
        $this$keyframes.setDurationMillis(CircularAnimationProgressDuration);
        $this$keyframes.using($this$keyframes.at(Float.valueOf(0.87f), 3000), CircularProgressEasing);
        $this$keyframes.at(Float.valueOf(0.1f), CircularAnimationProgressDuration);
        return Unit.INSTANCE;
    }

    public static final InfiniteRepeatableSpec<Float> getLinearIndeterminateFirstLineHeadAnimationSpec() {
        return AnimationSpecKt.m194infiniteRepeatable9IiC70o$default(AnimationSpecKt.keyframes(new Function1() { // from class: androidx.compose.material3.ProgressIndicatorKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ProgressIndicatorKt._get_linearIndeterminateFirstLineHeadAnimationSpec_$lambda$43((KeyframesSpec.KeyframesSpecConfig) obj);
            }
        }), null, 0L, 6, null);
    }

    static final Unit _get_linearIndeterminateFirstLineHeadAnimationSpec_$lambda$43(KeyframesSpec.KeyframesSpecConfig $this$keyframes) {
        $this$keyframes.setDurationMillis(LinearAnimationDuration);
        $this$keyframes.using($this$keyframes.at(Float.valueOf(0.0f), 0), LinearIndeterminateProgressEasing);
        $this$keyframes.at(Float.valueOf(1.0f), 1000);
        return Unit.INSTANCE;
    }

    public static final InfiniteRepeatableSpec<Float> getLinearIndeterminateFirstLineTailAnimationSpec() {
        return AnimationSpecKt.m194infiniteRepeatable9IiC70o$default(AnimationSpecKt.keyframes(new Function1() { // from class: androidx.compose.material3.ProgressIndicatorKt$$ExternalSyntheticLambda18
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ProgressIndicatorKt._get_linearIndeterminateFirstLineTailAnimationSpec_$lambda$44((KeyframesSpec.KeyframesSpecConfig) obj);
            }
        }), null, 0L, 6, null);
    }

    static final Unit _get_linearIndeterminateFirstLineTailAnimationSpec_$lambda$44(KeyframesSpec.KeyframesSpecConfig $this$keyframes) {
        $this$keyframes.setDurationMillis(LinearAnimationDuration);
        $this$keyframes.using($this$keyframes.at(Float.valueOf(0.0f), 250), LinearIndeterminateProgressEasing);
        $this$keyframes.at(Float.valueOf(1.0f), 1250);
        return Unit.INSTANCE;
    }

    public static final InfiniteRepeatableSpec<Float> getLinearIndeterminateSecondLineHeadAnimationSpec() {
        return AnimationSpecKt.m194infiniteRepeatable9IiC70o$default(AnimationSpecKt.keyframes(new Function1() { // from class: androidx.compose.material3.ProgressIndicatorKt$$ExternalSyntheticLambda25
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ProgressIndicatorKt._get_linearIndeterminateSecondLineHeadAnimationSpec_$lambda$45((KeyframesSpec.KeyframesSpecConfig) obj);
            }
        }), null, 0L, 6, null);
    }

    static final Unit _get_linearIndeterminateSecondLineHeadAnimationSpec_$lambda$45(KeyframesSpec.KeyframesSpecConfig $this$keyframes) {
        $this$keyframes.setDurationMillis(LinearAnimationDuration);
        $this$keyframes.using($this$keyframes.at(Float.valueOf(0.0f), SecondLineHeadDelay), LinearIndeterminateProgressEasing);
        $this$keyframes.at(Float.valueOf(1.0f), CircularAnimationAdditionalRotationDelay);
        return Unit.INSTANCE;
    }

    public static final InfiniteRepeatableSpec<Float> getLinearIndeterminateSecondLineTailAnimationSpec() {
        return AnimationSpecKt.m194infiniteRepeatable9IiC70o$default(AnimationSpecKt.keyframes(new Function1() { // from class: androidx.compose.material3.ProgressIndicatorKt$$ExternalSyntheticLambda27
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ProgressIndicatorKt._get_linearIndeterminateSecondLineTailAnimationSpec_$lambda$46((KeyframesSpec.KeyframesSpecConfig) obj);
            }
        }), null, 0L, 6, null);
    }

    static final Unit _get_linearIndeterminateSecondLineTailAnimationSpec_$lambda$46(KeyframesSpec.KeyframesSpecConfig $this$keyframes) {
        $this$keyframes.setDurationMillis(LinearAnimationDuration);
        $this$keyframes.using($this$keyframes.at(Float.valueOf(0.0f), 900), LinearIndeterminateProgressEasing);
        $this$keyframes.at(Float.valueOf(1.0f), LinearAnimationDuration);
        return Unit.INSTANCE;
    }

    public static final float getLinearIndicatorWidth() {
        return LinearIndicatorWidth;
    }

    public static final float getLinearIndicatorHeight() {
        return LinearIndicatorHeight;
    }

    public static final float getStopIndicatorTrailingSpace() {
        return StopIndicatorTrailingSpace;
    }

    public static final float getCircularIndicatorDiameter() {
        return CircularIndicatorDiameter;
    }

    public static final CubicBezierEasing getLinearIndeterminateProgressEasing() {
        return LinearIndeterminateProgressEasing;
    }

    public static final CubicBezierEasing getCircularProgressEasing() {
        return CircularProgressEasing;
    }
}
