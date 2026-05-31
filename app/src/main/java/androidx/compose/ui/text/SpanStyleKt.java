package androidx.compose.ui.text;

import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.graphics.ShadowKt;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.graphics.drawscope.Fill;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontSynthesis;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.font.FontWeightKt;
import androidx.compose.ui.text.font.SystemFontFamily;
import androidx.compose.ui.text.intl.LocaleList;
import androidx.compose.ui.text.style.BaselineShift;
import androidx.compose.ui.text.style.BaselineShiftKt;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextDrawStyleKt;
import androidx.compose.ui.text.style.TextForegroundStyle;
import androidx.compose.ui.text.style.TextGeometricTransform;
import androidx.compose.ui.text.style.TextGeometricTransformKt;
import androidx.compose.ui.unit.TextUnit;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SpanStyle.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\u0086\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a'\u0010\t\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\rH\u0000¢\u0006\u0004\b\u000e\u0010\u000f\u001a+\u0010\u0010\u001a\u0002H\u0011\"\u0004\b\u0000\u0010\u00112\u0006\u0010\n\u001a\u0002H\u00112\u0006\u0010\u000b\u001a\u0002H\u00112\u0006\u0010\u0012\u001a\u00020\rH\u0000¢\u0006\u0002\u0010\u0013\u001a\u001e\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u00152\u0006\u0010\u0012\u001a\u00020\r\u001a&\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u00192\b\u0010\u001b\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u0012\u001a\u00020\rH\u0000\u001a\f\u0010\u001c\u001a\u00020\u0019*\u00020\u0019H\u0002\u001a&\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\b\u0010\u0016\u001a\u0004\u0018\u00010\u001e2\b\u0010\u0017\u001a\u0004\u0018\u00010\u001e2\u0006\u0010\u0012\u001a\u00020\rH\u0002\u001a\u0010\u0010\u001f\u001a\u00020\u00152\u0006\u0010 \u001a\u00020\u0015H\u0000\u001a½\u0001\u0010!\u001a\u00020\u0015*\u00020\u00152\u0006\u0010\"\u001a\u00020\u00052\b\u0010#\u001a\u0004\u0018\u00010$2\u0006\u0010%\u001a\u00020\r2\u0006\u0010&\u001a\u00020\u00012\b\u0010'\u001a\u0004\u0018\u00010(2\b\u0010)\u001a\u0004\u0018\u00010*2\b\u0010+\u001a\u0004\u0018\u00010,2\b\u0010-\u001a\u0004\u0018\u00010.2\b\u0010/\u001a\u0004\u0018\u0001002\u0006\u00101\u001a\u00020\u00012\b\u00102\u001a\u0004\u0018\u0001032\b\u00104\u001a\u0004\u0018\u0001052\b\u00106\u001a\u0004\u0018\u0001072\u0006\u00108\u001a\u00020\u00052\b\u00109\u001a\u0004\u0018\u00010:2\b\u0010;\u001a\u0004\u0018\u00010\u00192\b\u0010<\u001a\u0004\u0018\u00010\u001e2\b\u0010=\u001a\u0004\u0018\u00010>H\u0000¢\u0006\u0004\b?\u0010@\u001a\u0018\u0010A\u001a\u0004\u0018\u00010\u001e*\u00020\u00152\b\u0010B\u001a\u0004\u0018\u00010\u001eH\u0002\"\u0010\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\u0003\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\u0006\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006C"}, d2 = {"DefaultFontSize", "Landroidx/compose/ui/unit/TextUnit;", "J", "DefaultLetterSpacing", "DefaultBackgroundColor", "Landroidx/compose/ui/graphics/Color;", "DefaultColor", "DefaultColorForegroundStyle", "Landroidx/compose/ui/text/style/TextForegroundStyle;", "lerpTextUnitInheritable", "a", "b", "t", "", "lerpTextUnitInheritable-C3pnCVY", "(JJF)J", "lerpDiscrete", "T", "fraction", "(Ljava/lang/Object;Ljava/lang/Object;F)Ljava/lang/Object;", "lerp", "Landroidx/compose/ui/text/SpanStyle;", "start", "stop", "nullSafeLerp", "Landroidx/compose/ui/graphics/Shadow;", "lhs", "rhs", "dropAlpha", "lerpPlatformStyle", "Landroidx/compose/ui/text/PlatformSpanStyle;", "resolveSpanStyleDefaults", "style", "fastMerge", TypedValues.Custom.S_COLOR, "brush", "Landroidx/compose/ui/graphics/Brush;", "alpha", "fontSize", "fontWeight", "Landroidx/compose/ui/text/font/FontWeight;", "fontStyle", "Landroidx/compose/ui/text/font/FontStyle;", "fontSynthesis", "Landroidx/compose/ui/text/font/FontSynthesis;", "fontFamily", "Landroidx/compose/ui/text/font/FontFamily;", "fontFeatureSettings", "", "letterSpacing", "baselineShift", "Landroidx/compose/ui/text/style/BaselineShift;", "textGeometricTransform", "Landroidx/compose/ui/text/style/TextGeometricTransform;", "localeList", "Landroidx/compose/ui/text/intl/LocaleList;", "background", "textDecoration", "Landroidx/compose/ui/text/style/TextDecoration;", "shadow", "platformStyle", "drawStyle", "Landroidx/compose/ui/graphics/drawscope/DrawStyle;", "fastMerge-dSHsh3o", "(Landroidx/compose/ui/text/SpanStyle;JLandroidx/compose/ui/graphics/Brush;FJLandroidx/compose/ui/text/font/FontWeight;Landroidx/compose/ui/text/font/FontStyle;Landroidx/compose/ui/text/font/FontSynthesis;Landroidx/compose/ui/text/font/FontFamily;Ljava/lang/String;JLandroidx/compose/ui/text/style/BaselineShift;Landroidx/compose/ui/text/style/TextGeometricTransform;Landroidx/compose/ui/text/intl/LocaleList;JLandroidx/compose/ui/text/style/TextDecoration;Landroidx/compose/ui/graphics/Shadow;Landroidx/compose/ui/text/PlatformSpanStyle;Landroidx/compose/ui/graphics/drawscope/DrawStyle;)Landroidx/compose/ui/text/SpanStyle;", "mergePlatformStyle", "other", "ui-text"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class SpanStyleKt {
    private static final long DefaultFontSize = TextUnitKt.getSp(14);
    private static final long DefaultLetterSpacing = TextUnitKt.getSp(0);
    private static final long DefaultBackgroundColor = Color.INSTANCE.m5348getTransparent0d7_KjU();
    private static final long DefaultColor = Color.INSTANCE.m5339getBlack0d7_KjU();
    private static final TextForegroundStyle DefaultColorForegroundStyle = TextForegroundStyle.INSTANCE.m8030from8_81llA(DefaultColor);

    /* JADX INFO: renamed from: lerpTextUnitInheritable-C3pnCVY, reason: not valid java name */
    public static final long m7520lerpTextUnitInheritableC3pnCVY(long a, long b, float t) {
        if (!(TextUnit.m8342getRawTypeimpl(a) == 0)) {
            if (!(TextUnit.m8342getRawTypeimpl(b) == 0)) {
                return TextUnitKt.m8364lerpC3pnCVY(a, b, t);
            }
        }
        return ((TextUnit) lerpDiscrete(TextUnit.m8334boximpl(a), TextUnit.m8334boximpl(b), t)).getPackedValue();
    }

    public static final <T> T lerpDiscrete(T t, T t2, float fraction) {
        return ((double) fraction) < 0.5d ? t : t2;
    }

    public static final SpanStyle lerp(SpanStyle start, SpanStyle stop, float fraction) {
        TextForegroundStyle textForegroundStyleLerp = TextDrawStyleKt.lerp(start.getTextForegroundStyle(), stop.getTextForegroundStyle(), fraction);
        FontFamily fontFamily = (FontFamily) lerpDiscrete(start.getFontFamily(), stop.getFontFamily(), fraction);
        long jM7520lerpTextUnitInheritableC3pnCVY = m7520lerpTextUnitInheritableC3pnCVY(start.getFontSize(), stop.getFontSize(), fraction);
        FontWeight fontWeight = start.getFontWeight();
        if (fontWeight == null) {
            fontWeight = FontWeight.INSTANCE.getNormal();
        }
        FontWeight fontWeight2 = stop.getFontWeight();
        if (fontWeight2 == null) {
            fontWeight2 = FontWeight.INSTANCE.getNormal();
        }
        FontWeight fontWeightLerp = FontWeightKt.lerp(fontWeight, fontWeight2, fraction);
        FontStyle fontStyle = (FontStyle) lerpDiscrete(start.getFontStyle(), stop.getFontStyle(), fraction);
        FontSynthesis fontSynthesis = (FontSynthesis) lerpDiscrete(start.getFontSynthesis(), stop.getFontSynthesis(), fraction);
        String str = (String) lerpDiscrete(start.getFontFeatureSettings(), stop.getFontFeatureSettings(), fraction);
        long jM7520lerpTextUnitInheritableC3pnCVY2 = m7520lerpTextUnitInheritableC3pnCVY(start.getLetterSpacing(), stop.getLetterSpacing(), fraction);
        BaselineShift baselineShift = start.getBaselineShift();
        float fM7871unboximpl = baselineShift != null ? baselineShift.m7871unboximpl() : BaselineShift.m7866constructorimpl(0.0f);
        BaselineShift baselineShift2 = stop.getBaselineShift();
        float fM7881lerpjWV1Mfo = BaselineShiftKt.m7881lerpjWV1Mfo(fM7871unboximpl, baselineShift2 != null ? baselineShift2.m7871unboximpl() : BaselineShift.m7866constructorimpl(0.0f), fraction);
        TextGeometricTransform textGeometricTransform = start.getTextGeometricTransform();
        if (textGeometricTransform == null) {
            textGeometricTransform = TextGeometricTransform.INSTANCE.getNone$ui_text();
        }
        TextGeometricTransform textGeometricTransform2 = stop.getTextGeometricTransform();
        if (textGeometricTransform2 == null) {
            textGeometricTransform2 = TextGeometricTransform.INSTANCE.getNone$ui_text();
        }
        return new SpanStyle(textForegroundStyleLerp, jM7520lerpTextUnitInheritableC3pnCVY, fontWeightLerp, fontStyle, fontSynthesis, fontFamily, str, jM7520lerpTextUnitInheritableC3pnCVY2, BaselineShift.m7865boximpl(fM7881lerpjWV1Mfo), TextGeometricTransformKt.lerp(textGeometricTransform, textGeometricTransform2, fraction), (LocaleList) lerpDiscrete(start.getLocaleList(), stop.getLocaleList(), fraction), ColorKt.m5364lerpjxsXWHM(start.getBackground(), stop.getBackground(), fraction), (TextDecoration) lerpDiscrete(start.getTextDecoration(), stop.getTextDecoration(), fraction), nullSafeLerp(start.getShadow(), stop.getShadow(), fraction), lerpPlatformStyle(start.getPlatformStyle(), stop.getPlatformStyle(), fraction), (DrawStyle) lerpDiscrete(start.getDrawStyle(), stop.getDrawStyle(), fraction), (DefaultConstructorMarker) null);
    }

    public static final Shadow nullSafeLerp(Shadow lhs, Shadow rhs, float fraction) {
        if (!ComposeUiTextFlags.isCorrectShadowLerpWithNullsEnabled) {
            return ShadowKt.lerp(lhs == null ? new Shadow(0L, 0L, 0.0f, 7, null) : lhs, rhs == null ? new Shadow(0L, 0L, 0.0f, 7, null) : rhs, fraction);
        }
        if (lhs == null && rhs == null) {
            return null;
        }
        if (lhs == null) {
            Intrinsics.checkNotNull(rhs);
            return ShadowKt.lerp(dropAlpha(rhs), rhs, fraction);
        }
        if (rhs == null) {
            return ShadowKt.lerp(lhs, dropAlpha(lhs), fraction);
        }
        return ShadowKt.lerp(lhs, rhs, fraction);
    }

    private static final Shadow dropAlpha(Shadow $this$dropAlpha) {
        long color = $this$dropAlpha.getColor();
        return $this$dropAlpha.m5656copyqcb84PM((6 & 1) != 0 ? $this$dropAlpha.color : Color.m5311copywmQWz5c(color, (14 & 1) != 0 ? Color.m5315getAlphaimpl(color) : 0.0f, (14 & 2) != 0 ? Color.m5319getRedimpl(color) : 0.0f, (14 & 4) != 0 ? Color.m5318getGreenimpl(color) : 0.0f, (14 & 8) != 0 ? Color.m5316getBlueimpl(color) : 0.0f), (6 & 2) != 0 ? $this$dropAlpha.offset : 0L, (6 & 4) != 0 ? $this$dropAlpha.blurRadius : 0.0f);
    }

    private static final PlatformSpanStyle lerpPlatformStyle(PlatformSpanStyle start, PlatformSpanStyle stop, float fraction) {
        if (start == null && stop == null) {
            return null;
        }
        PlatformSpanStyle startNonNull = start == null ? PlatformSpanStyle.INSTANCE.getDefault() : start;
        PlatformSpanStyle stopNonNull = stop == null ? PlatformSpanStyle.INSTANCE.getDefault() : stop;
        return AndroidTextStyle_androidKt.lerp(startNonNull, stopNonNull, fraction);
    }

    public static final SpanStyle resolveSpanStyleDefaults(SpanStyle style) {
        long letterSpacing;
        TextForegroundStyle textForegroundStyleTakeOrElse = style.getTextForegroundStyle().takeOrElse(new Function0() { // from class: androidx.compose.ui.text.SpanStyleKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return SpanStyleKt.DefaultColorForegroundStyle;
            }
        });
        long $this$isUnspecified$iv = style.getFontSize();
        long fontSize = (TextUnit.m8342getRawTypeimpl($this$isUnspecified$iv) > 0L ? 1 : (TextUnit.m8342getRawTypeimpl($this$isUnspecified$iv) == 0L ? 0 : -1)) == 0 ? DefaultFontSize : style.getFontSize();
        FontWeight fontWeight = style.getFontWeight();
        if (fontWeight == null) {
            fontWeight = FontWeight.INSTANCE.getNormal();
        }
        FontStyle fontStyle = style.getFontStyle();
        FontStyle fontStyleM7682boximpl = FontStyle.m7682boximpl(fontStyle != null ? fontStyle.m7688unboximpl() : FontStyle.INSTANCE.m7692getNormal_LCdwA());
        FontSynthesis fontSynthesis = style.getFontSynthesis();
        FontSynthesis fontSynthesisM7693boximpl = FontSynthesis.m7693boximpl(fontSynthesis != null ? fontSynthesis.m7701unboximpl() : FontSynthesis.INSTANCE.m7702getAllGVVA2EU());
        SystemFontFamily fontFamily = style.getFontFamily();
        if (fontFamily == null) {
            fontFamily = FontFamily.INSTANCE.getDefault();
        }
        String fontFeatureSettings = style.getFontFeatureSettings();
        if (fontFeatureSettings == null) {
            fontFeatureSettings = "";
        }
        long $this$isUnspecified$iv2 = style.getLetterSpacing();
        if (TextUnit.m8342getRawTypeimpl($this$isUnspecified$iv2) == 0) {
            letterSpacing = DefaultLetterSpacing;
        } else {
            letterSpacing = style.getLetterSpacing();
        }
        BaselineShift baselineShift = style.getBaselineShift();
        float $this$takeOrElse_u2dJpAxnlU$iv = baselineShift != null ? baselineShift.m7871unboximpl() : BaselineShift.INSTANCE.m7876getNoney9eOQZs();
        if (Float.isNaN($this$takeOrElse_u2dJpAxnlU$iv)) {
            $this$takeOrElse_u2dJpAxnlU$iv = BaselineShift.INSTANCE.m7876getNoney9eOQZs();
        }
        BaselineShift baselineShiftM7865boximpl = BaselineShift.m7865boximpl($this$takeOrElse_u2dJpAxnlU$iv);
        TextGeometricTransform textGeometricTransform = style.getTextGeometricTransform();
        if (textGeometricTransform == null) {
            textGeometricTransform = TextGeometricTransform.INSTANCE.getNone$ui_text();
        }
        TextGeometricTransform textGeometricTransform2 = textGeometricTransform;
        LocaleList localeList = style.getLocaleList();
        if (localeList == null) {
            localeList = LocaleList.INSTANCE.getCurrent();
        }
        LocaleList localeList2 = localeList;
        long $this$takeOrElse_u2dDxMtmZc$iv = style.getBackground();
        if (!($this$takeOrElse_u2dDxMtmZc$iv != 16)) {
            $this$takeOrElse_u2dDxMtmZc$iv = DefaultBackgroundColor;
        }
        TextDecoration textDecoration = style.getTextDecoration();
        if (textDecoration == null) {
            textDecoration = TextDecoration.INSTANCE.getNone();
        }
        TextDecoration textDecoration2 = textDecoration;
        Shadow shadow = style.getShadow();
        if (shadow == null) {
            shadow = Shadow.INSTANCE.getNone();
        }
        Shadow shadow2 = shadow;
        PlatformSpanStyle platformStyle = style.getPlatformStyle();
        Fill drawStyle = style.getDrawStyle();
        if (drawStyle == null) {
            drawStyle = Fill.INSTANCE;
        }
        return new SpanStyle(textForegroundStyleTakeOrElse, fontSize, fontWeight, fontStyleM7682boximpl, fontSynthesisM7693boximpl, fontFamily, fontFeatureSettings, letterSpacing, baselineShiftM7865boximpl, textGeometricTransform2, localeList2, $this$takeOrElse_u2dDxMtmZc$iv, textDecoration2, shadow2, platformStyle, drawStyle, (DefaultConstructorMarker) null);
    }

    /* JADX WARN: Removed duplicated region for block: B:121:0x020b A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:122:0x020c  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0130  */
    /* JADX INFO: renamed from: fastMerge-dSHsh3o, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final androidx.compose.ui.text.SpanStyle m7519fastMergedSHsh3o(androidx.compose.ui.text.SpanStyle r48, long r49, androidx.compose.ui.graphics.Brush r51, float r52, long r53, androidx.compose.ui.text.font.FontWeight r55, androidx.compose.ui.text.font.FontStyle r56, androidx.compose.ui.text.font.FontSynthesis r57, androidx.compose.ui.text.font.FontFamily r58, java.lang.String r59, long r60, androidx.compose.ui.text.style.BaselineShift r62, androidx.compose.ui.text.style.TextGeometricTransform r63, androidx.compose.ui.text.intl.LocaleList r64, long r65, androidx.compose.ui.text.style.TextDecoration r67, androidx.compose.ui.graphics.Shadow r68, androidx.compose.ui.text.PlatformSpanStyle r69, androidx.compose.ui.graphics.drawscope.DrawStyle r70) {
        /*
            Method dump skipped, instruction units count: 786
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.text.SpanStyleKt.m7519fastMergedSHsh3o(androidx.compose.ui.text.SpanStyle, long, androidx.compose.ui.graphics.Brush, float, long, androidx.compose.ui.text.font.FontWeight, androidx.compose.ui.text.font.FontStyle, androidx.compose.ui.text.font.FontSynthesis, androidx.compose.ui.text.font.FontFamily, java.lang.String, long, androidx.compose.ui.text.style.BaselineShift, androidx.compose.ui.text.style.TextGeometricTransform, androidx.compose.ui.text.intl.LocaleList, long, androidx.compose.ui.text.style.TextDecoration, androidx.compose.ui.graphics.Shadow, androidx.compose.ui.text.PlatformSpanStyle, androidx.compose.ui.graphics.drawscope.DrawStyle):androidx.compose.ui.text.SpanStyle");
    }

    private static final PlatformSpanStyle mergePlatformStyle(SpanStyle $this$mergePlatformStyle, PlatformSpanStyle other) {
        return $this$mergePlatformStyle.getPlatformStyle() == null ? other : other == null ? $this$mergePlatformStyle.getPlatformStyle() : $this$mergePlatformStyle.getPlatformStyle().merge(other);
    }
}
