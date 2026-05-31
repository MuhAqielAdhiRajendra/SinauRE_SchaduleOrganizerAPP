package androidx.compose.foundation.style;

import androidx.collection.MutableIntList;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.SpringSpec;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.Interpolatable;
import androidx.compose.ui.graphics.RectangleShapeKt;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.SolidColor;
import androidx.compose.ui.graphics.TransformOrigin;
import androidx.compose.ui.graphics.TransformOriginKt;
import androidx.compose.ui.graphics.shadow.Shadow;
import androidx.compose.ui.graphics.shadow.ShadowKt;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontSynthesis;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.BaselineShift;
import androidx.compose.ui.text.style.LineBreak;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.unit.TextUnit;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.compose.ui.util.MathHelpersKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* JADX INFO: compiled from: ResolvedStyle.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000z\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u001d\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a(\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0003H\u0000\u001a!\u0010\b\u001a\u00020\u00062\u0006\u0010\u0002\u001a\u00020\u00062\u0006\u0010\u0004\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0006H\u0080\b\u001a(\u0010\t\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0003H\u0000\u001a(\u0010\n\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0003H\u0000\u001a&\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010\u0002\u001a\u0004\u0018\u00010\f2\b\u0010\u0004\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0005\u001a\u00020\u0006H\u0000\u001a9\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u000e2\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u0006\u0010\u0005\u001a\u00020\u0006H\u0000¢\u0006\u0002\u0010\u0010\u001a(\u0010\u0011\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0003H\u0000\u001a(\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0003H\u0000\u001a(\u0010\u0013\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0003H\u0000\u001a0\u0010\u0014\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0007\u001a\u00020\u0003H\u0000\u001a\r\u0010\u0017\u001a\u00020\u0016*\u00020\u0016H\u0082\b\u001a=\u0010\u0014\u001a\u0004\u0018\u00010\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u001d\u001a\u00020\u001b2\u0006\u0010\u0005\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u001e\u0010\u001f\u001a\"\u0010\u0014\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0002\u001a\u00020\u00182\u0006\u0010\u0004\u001a\u00020\u00182\u0006\u0010\u0005\u001a\u00020\u0006H\u0002\u001a \u0010\u0014\u001a\u00020 2\u0006\u0010\u0002\u001a\u00020 2\u0006\u0010\u0004\u001a\u00020 2\u0006\u0010\u0005\u001a\u00020\u0006H\u0002\u001a\u001c\u0010$\u001a\u00020%*\u00020%2\u0006\u0010&\u001a\u00020%H\u0080\b¢\u0006\u0004\b'\u0010(\u001a\u001c\u0010$\u001a\u00020)*\u00020)2\u0006\u0010&\u001a\u00020)H\u0080\b¢\u0006\u0004\b*\u0010+\u001a\u001c\u0010$\u001a\u00020,*\u00020,2\u0006\u0010&\u001a\u00020,H\u0080\b¢\u0006\u0004\b-\u0010.\u001a\u001c\u0010$\u001a\u00020\u001b*\u00020\u001b2\u0006\u0010&\u001a\u00020\u001bH\u0080\b¢\u0006\u0004\b/\u0010.\u001a\u0019\u00100\u001a\u00020\u00162\u0006\u00101\u001a\u00020\u00162\u0006\u00102\u001a\u00020\u0016H\u0080\b\u001a!\u00103\u001a\u00020\u00162\u0006\u00101\u001a\u00020\u00162\u0006\u00102\u001a\u00020\u00162\u0006\u00104\u001a\u00020\u0016H\u0080\b\u001a\u001d\u00105\u001a\u00020\u0016*\u00020\u00162\u0006\u00104\u001a\u00020\u00162\u0006\u00106\u001a\u00020\u0016H\u0080\b\u001a%\u00107\u001a\u00020\u0016*\u00020\u00162\u0006\u00104\u001a\u00020\u00162\u0006\u00106\u001a\u00020\u00162\u0006\u00108\u001a\u00020\u0016H\u0080\b\u001a\u0018\u00109\u001a\u00020\u00162\u0006\u0010:\u001a\u00020\u00162\u0006\u0010;\u001a\u00020\u0016H\u0002\u001a\u0018\u0010<\u001a\u00020\u00162\u0006\u0010:\u001a\u00020\u00162\u0006\u0010;\u001a\u00020\u0016H\u0002\u001a\u0015\u0010=\u001a\u00020>*\u00020?2\u0006\u00108\u001a\u00020\u0016H\u0082\b\u001a\r\u0010@\u001a\u00020\u0016*\u00020?H\u0082\b\u001a\f\u0010\\\u001a\u00020]*\u00020\u0003H\u0000\u001a\u0010\u0010^\u001a\u00020]2\u0006\u0010\u0015\u001a\u00020\u0016H\u0000\"\u0014\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00060\"X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010#\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0016\u0010A\u001a\u00020\u0001X\u0080\u0004¢\u0006\n\n\u0002\u0010D\u001a\u0004\bB\u0010C\"\u000e\u0010E\u001a\u00020\u0016X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010F\u001a\u00020\u0016X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010G\u001a\u00020\u0016X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010H\u001a\u00020\u0016X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010I\u001a\u00020\u0016X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010J\u001a\u00020\u0016X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010K\u001a\u00020\u0016X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010L\u001a\u00020\u0016X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010M\u001a\u00020\u0016X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010N\u001a\u00020\u0016X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010O\u001a\u00020\u0016X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010P\u001a\u00020\u0016X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010Q\u001a\u00020\u0016X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010R\u001a\u00020\u0016X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010S\u001a\u00020\u0016X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010T\u001a\u00020\u0016X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010U\u001a\u00020\u0016X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010V\u001a\u00020\u0016X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010W\u001a\u00020\u0016X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010X\u001a\u00020\u0016X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010Y\u001a\u00020\u0016X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010Z\u001a\u00020\u0016X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010[\u001a\u00020\u0016X\u0080T¢\u0006\u0002\n\u0000¨\u0006_"}, d2 = {"lerpOuterLayout", "", "a", "Landroidx/compose/foundation/style/ResolvedStyle;", "b", "t", "", "result", "lerpMaybeNan", "lerpInnerLayout", "lerpDraw", "lerpShadows", "", "lerpArrayShadows", "", "Landroidx/compose/ui/graphics/shadow/Shadow;", "([Landroidx/compose/ui/graphics/shadow/Shadow;[Landroidx/compose/ui/graphics/shadow/Shadow;F)[Landroidx/compose/ui/graphics/shadow/Shadow;", "lerpLayer", "lerpTextDraw", "lerpTextLayout", "lerp", "flags", "", "floorToNearest100", "Landroidx/compose/ui/graphics/Brush;", "leftBrush", "leftColor", "Landroidx/compose/ui/graphics/Color;", "rightBrush", "rightColor", "lerp-wffgcV4", "(Landroidx/compose/ui/graphics/Brush;JLandroidx/compose/ui/graphics/Brush;JF)Landroidx/compose/ui/graphics/Brush;", "Landroidx/compose/ui/graphics/Shape;", "DefaultSpringSpec", "Landroidx/compose/animation/core/SpringSpec;", "EmptyResolvedStyle", "takeOrElse", "Landroidx/compose/ui/text/style/LineBreak;", "other", "takeOrElse-w1xZEK0", "(II)I", "Landroidx/compose/ui/text/style/BaselineShift;", "takeOrElse-y00tBZM", "(FF)F", "Landroidx/compose/ui/unit/TextUnit;", "takeOrElse-NB67dxo", "(JJ)J", "takeOrElse--OWjLjI", "applyTextEnum", "left", "right", "setBitsIfNonZero", "mask", "getBits", "shift", "setBits", "value", "updateHashEnter", "hash", "key", "updateHashExit", "push", "", "Landroidx/collection/MutableIntList;", "pop", "TextDefaultsResolvedStyle", "getTextDefaultsResolvedStyle", "()Lkotlin/Unit;", "Lkotlin/Unit;", "FontStyleShift", "TextAlignShift", "TextDirectionShift", "HyphensShift", "FontSynthesisShift", "TextDecorationShift", "FontWeightShift", "FontStyleMask", "TextAlignMask", "TextDirectionMask", "HyphensMask", "FontSynthesisMask", "TextDecorationMask", "FontWeightMask", "InnerLayoutFlag", "DrawFlag", "LayerFlag", "OuterLayoutFlag", "AnimatedFlag", "TextLayoutFlag", "TextDrawFlag", "InheritedFlags", "AnimateGroup", "flagsAsString", "", "resolvedStyleFlagsToString", "foundation"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class ResolvedStyleKt {
    public static final int AnimateGroup = 1318433304;
    public static final int AnimatedFlag = 16;
    public static final int DrawFlag = 2;
    private static final int FontStyleMask = 3;
    private static final int FontStyleShift = 0;
    private static final int FontSynthesisMask = 15360;
    private static final int FontSynthesisShift = 10;
    private static final int FontWeightMask = 134086656;
    private static final int FontWeightShift = 17;
    private static final int HyphensMask = 768;
    private static final int HyphensShift = 8;
    public static final int InheritedFlags = 96;
    public static final int InnerLayoutFlag = 1;
    public static final int LayerFlag = 4;
    public static final int OuterLayoutFlag = 8;
    private static final int TextAlignMask = 28;
    private static final int TextAlignShift = 2;
    private static final int TextDecorationMask = 114688;
    private static final int TextDecorationShift = 14;
    private static final Unit TextDefaultsResolvedStyle;
    private static final int TextDirectionMask = 112;
    private static final int TextDirectionShift = 4;
    public static final int TextDrawFlag = 64;
    public static final int TextLayoutFlag = 32;
    private static final SpringSpec<Float> DefaultSpringSpec = AnimationSpecKt.spring$default(0.0f, 0.0f, null, 7, null);
    private static final ResolvedStyle EmptyResolvedStyle = new ResolvedStyle();

    public static final void lerpOuterLayout(ResolvedStyle a, ResolvedStyle b, float t, ResolvedStyle result) {
        float a$iv = a.getExternalPaddingStart();
        float b$iv = b.getExternalPaddingStart();
        boolean aNan$iv = Float.isNaN(a$iv);
        boolean bNan$iv = Float.isNaN(b$iv);
        float next$iv = ((1.0f - t) * a$iv) + (t * b$iv);
        if (aNan$iv) {
            a$iv = b$iv;
        } else if (!bNan$iv) {
            a$iv = next$iv;
        }
        result.setExternalPaddingStart$foundation(a$iv);
        float a$iv2 = a.getExternalPaddingEnd();
        float b$iv2 = b.getExternalPaddingEnd();
        boolean aNan$iv2 = Float.isNaN(a$iv2);
        boolean bNan$iv2 = Float.isNaN(b$iv2);
        float next$iv2 = ((1.0f - t) * a$iv2) + (t * b$iv2);
        if (aNan$iv2) {
            a$iv2 = b$iv2;
        } else if (!bNan$iv2) {
            a$iv2 = next$iv2;
        }
        result.setExternalPaddingEnd$foundation(a$iv2);
        float a$iv3 = a.getExternalPaddingTop();
        float b$iv3 = b.getExternalPaddingTop();
        boolean aNan$iv3 = Float.isNaN(a$iv3);
        boolean bNan$iv3 = Float.isNaN(b$iv3);
        float next$iv3 = ((1.0f - t) * a$iv3) + (t * b$iv3);
        if (aNan$iv3) {
            a$iv3 = b$iv3;
        } else if (!bNan$iv3) {
            a$iv3 = next$iv3;
        }
        result.setExternalPaddingTop$foundation(a$iv3);
        float a$iv4 = a.getExternalPaddingBottom();
        float b$iv4 = b.getExternalPaddingBottom();
        boolean aNan$iv4 = Float.isNaN(a$iv4);
        boolean bNan$iv4 = Float.isNaN(b$iv4);
        float next$iv4 = ((1.0f - t) * a$iv4) + (t * b$iv4);
        if (aNan$iv4) {
            a$iv4 = b$iv4;
        } else if (!bNan$iv4) {
            a$iv4 = next$iv4;
        }
        result.setExternalPaddingBottom$foundation(a$iv4);
        float a$iv5 = a.getLeft();
        float b$iv5 = b.getLeft();
        boolean aNan$iv5 = Float.isNaN(a$iv5);
        boolean bNan$iv5 = Float.isNaN(b$iv5);
        float next$iv5 = ((1.0f - t) * a$iv5) + (t * b$iv5);
        if (aNan$iv5) {
            a$iv5 = b$iv5;
        } else if (!bNan$iv5) {
            a$iv5 = next$iv5;
        }
        result.setLeft$foundation(a$iv5);
        float a$iv6 = a.getTop();
        float b$iv6 = b.getTop();
        boolean aNan$iv6 = Float.isNaN(a$iv6);
        boolean bNan$iv6 = Float.isNaN(b$iv6);
        float next$iv6 = ((1.0f - t) * a$iv6) + (t * b$iv6);
        if (aNan$iv6) {
            a$iv6 = b$iv6;
        } else if (!bNan$iv6) {
            a$iv6 = next$iv6;
        }
        result.setTop$foundation(a$iv6);
        float a$iv7 = a.getRight();
        float b$iv7 = b.getRight();
        boolean aNan$iv7 = Float.isNaN(a$iv7);
        boolean bNan$iv7 = Float.isNaN(b$iv7);
        float next$iv7 = ((1.0f - t) * a$iv7) + (t * b$iv7);
        if (aNan$iv7) {
            a$iv7 = b$iv7;
        } else if (!bNan$iv7) {
            a$iv7 = next$iv7;
        }
        result.setRight$foundation(a$iv7);
        float a$iv8 = a.getBottom();
        float b$iv8 = b.getBottom();
        boolean aNan$iv8 = Float.isNaN(a$iv8);
        boolean bNan$iv8 = Float.isNaN(b$iv8);
        float next$iv8 = ((1.0f - t) * a$iv8) + (t * b$iv8);
        if (aNan$iv8) {
            a$iv8 = b$iv8;
        } else if (!bNan$iv8) {
            a$iv8 = next$iv8;
        }
        result.setBottom$foundation(a$iv8);
        float a$iv9 = a.getWidth();
        float b$iv9 = b.getWidth();
        boolean aNan$iv9 = Float.isNaN(a$iv9);
        boolean bNan$iv9 = Float.isNaN(b$iv9);
        float next$iv9 = ((1.0f - t) * a$iv9) + (t * b$iv9);
        if (aNan$iv9) {
            a$iv9 = b$iv9;
        } else if (!bNan$iv9) {
            a$iv9 = next$iv9;
        }
        result.setWidth$foundation(a$iv9);
        float a$iv10 = a.getHeight();
        float b$iv10 = b.getHeight();
        boolean aNan$iv10 = Float.isNaN(a$iv10);
        boolean bNan$iv10 = Float.isNaN(b$iv10);
        float next$iv10 = ((1.0f - t) * a$iv10) + (t * b$iv10);
        if (aNan$iv10) {
            a$iv10 = b$iv10;
        } else if (!bNan$iv10) {
            a$iv10 = next$iv10;
        }
        result.setHeight$foundation(a$iv10);
        float a$iv11 = a.getWidthFraction();
        float b$iv11 = b.getWidthFraction();
        boolean aNan$iv11 = Float.isNaN(a$iv11);
        boolean bNan$iv11 = Float.isNaN(b$iv11);
        float next$iv11 = ((1.0f - t) * a$iv11) + (t * b$iv11);
        if (aNan$iv11) {
            a$iv11 = b$iv11;
        } else if (!bNan$iv11) {
            a$iv11 = next$iv11;
        }
        result.setWidthFraction$foundation(a$iv11);
        float a$iv12 = a.getHeightFraction();
        float b$iv12 = b.getHeightFraction();
        boolean aNan$iv12 = Float.isNaN(a$iv12);
        boolean bNan$iv12 = Float.isNaN(b$iv12);
        float next$iv12 = ((1.0f - t) * a$iv12) + (t * b$iv12);
        if (aNan$iv12) {
            a$iv12 = b$iv12;
        } else if (!bNan$iv12) {
            a$iv12 = next$iv12;
        }
        result.setHeightFraction$foundation(a$iv12);
        float a$iv13 = a.getMinWidth();
        float b$iv13 = b.getMinWidth();
        boolean aNan$iv13 = Float.isNaN(a$iv13);
        boolean bNan$iv13 = Float.isNaN(b$iv13);
        float next$iv13 = ((1.0f - t) * a$iv13) + (t * b$iv13);
        if (aNan$iv13) {
            a$iv13 = b$iv13;
        } else if (!bNan$iv13) {
            a$iv13 = next$iv13;
        }
        result.setMinWidth$foundation(a$iv13);
        float a$iv14 = a.getMaxWidth();
        float b$iv14 = b.getMaxWidth();
        boolean aNan$iv14 = Float.isNaN(a$iv14);
        boolean bNan$iv14 = Float.isNaN(b$iv14);
        float next$iv14 = ((1.0f - t) * a$iv14) + (t * b$iv14);
        if (aNan$iv14) {
            a$iv14 = b$iv14;
        } else if (!bNan$iv14) {
            a$iv14 = next$iv14;
        }
        result.setMaxWidth$foundation(a$iv14);
        float a$iv15 = a.getMinHeight();
        float b$iv15 = b.getMinHeight();
        boolean aNan$iv15 = Float.isNaN(a$iv15);
        boolean bNan$iv15 = Float.isNaN(b$iv15);
        float next$iv15 = ((1.0f - t) * a$iv15) + (t * b$iv15);
        if (aNan$iv15) {
            a$iv15 = b$iv15;
        } else if (!bNan$iv15) {
            a$iv15 = next$iv15;
        }
        result.setMinHeight$foundation(a$iv15);
        float a$iv16 = a.getMaxHeight();
        float b$iv16 = b.getMaxHeight();
        boolean aNan$iv16 = Float.isNaN(a$iv16);
        boolean bNan$iv16 = Float.isNaN(b$iv16);
        float next$iv16 = ((1.0f - t) * a$iv16) + (t * b$iv16);
        if (aNan$iv16) {
            a$iv16 = b$iv16;
        } else if (!bNan$iv16) {
            a$iv16 = next$iv16;
        }
        result.setMaxHeight$foundation(a$iv16);
    }

    public static final float lerpMaybeNan(float a, float b, float t) {
        boolean aNan = Float.isNaN(a);
        boolean bNan = Float.isNaN(b);
        float next = ((1.0f - t) * a) + (t * b);
        return aNan ? b : bNan ? a : next;
    }

    public static final void lerpInnerLayout(ResolvedStyle a, ResolvedStyle b, float t, ResolvedStyle result) {
        result.setContentPaddingStart$foundation(MathHelpersKt.lerp(a.getContentPaddingStart(), b.getContentPaddingStart(), t));
        result.setContentPaddingEnd$foundation(MathHelpersKt.lerp(a.getContentPaddingEnd(), b.getContentPaddingEnd(), t));
        result.setContentPaddingTop$foundation(MathHelpersKt.lerp(a.getContentPaddingTop(), b.getContentPaddingTop(), t));
        result.setContentPaddingBottom$foundation(MathHelpersKt.lerp(a.getContentPaddingBottom(), b.getContentPaddingBottom(), t));
    }

    public static final void lerpDraw(ResolvedStyle a, ResolvedStyle b, float t, ResolvedStyle result) {
        result.setBorderWidth$foundation(MathHelpersKt.lerp(a.getBorderWidth(), b.getBorderWidth(), t));
        result.m1443setBorderColor8_81llA$foundation(ColorKt.m5364lerpjxsXWHM(a.getBorderColor(), b.getBorderColor(), t));
        result.setBorderBrush$foundation(m1460lerpwffgcV4(a.getBorderBrush(), a.getBorderColor(), b.getBorderBrush(), b.getBorderColor(), t));
        result.m1441setBackgroundColor8_81llA$foundation(ColorKt.m5364lerpjxsXWHM(a.getBackgroundColor(), b.getBackgroundColor(), t));
        result.setBackgroundBrush$foundation(m1460lerpwffgcV4(a.getBackgroundBrush(), a.getBackgroundColor(), b.getBackgroundBrush(), b.getBackgroundColor(), t));
        result.setForegroundBrush$foundation(m1460lerpwffgcV4(a.getForegroundBrush(), Color.INSTANCE.m5349getUnspecified0d7_KjU(), b.getForegroundBrush(), Color.INSTANCE.m5349getUnspecified0d7_KjU(), t));
        result.setInnerShadow$foundation(lerpShadows(a.getInnerShadow(), b.getInnerShadow(), t));
        result.setDropShadow$foundation(lerpShadows(a.getDropShadow(), b.getDropShadow(), t));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final Object lerpShadows(Object obj, Object obj2, float f) {
        Shadow[] shadowArr;
        Shadow[] shadowArr2;
        if (obj == null && obj2 == null) {
            return null;
        }
        boolean z = obj instanceof Object[];
        boolean z2 = obj2 instanceof Object[];
        if (!z && !z2) {
            return ShadowKt.lerp(obj instanceof Shadow ? (Shadow) obj : null, obj2 instanceof Shadow ? (Shadow) obj2 : null, f);
        }
        if (z) {
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Array<androidx.compose.ui.graphics.shadow.Shadow>");
            shadowArr = (Shadow[]) obj;
        } else {
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type androidx.compose.ui.graphics.shadow.Shadow");
            shadowArr = new Shadow[]{obj};
        }
        if (z2) {
            Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type kotlin.Array<androidx.compose.ui.graphics.shadow.Shadow>");
            shadowArr2 = (Shadow[]) obj2;
        } else {
            Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type androidx.compose.ui.graphics.shadow.Shadow");
            shadowArr2 = new Shadow[]{obj2};
        }
        return lerpArrayShadows(shadowArr, shadowArr2, f);
    }

    public static final Shadow[] lerpArrayShadows(Shadow[] a, Shadow[] b, float t) {
        int maxSize = Math.max(a.length, b.length);
        Shadow[] result = new Shadow[maxSize];
        for (int i = 0; i < maxSize; i++) {
            result[i] = null;
        }
        for (int i2 = 0; i2 < maxSize; i2++) {
            Shadow left = (Shadow) ArraysKt.getOrNull(a, i2);
            Shadow right = (Shadow) ArraysKt.getOrNull(b, i2);
            result[i2] = ShadowKt.lerp(left, right, t);
        }
        return result;
    }

    public static final void lerpLayer(ResolvedStyle a, ResolvedStyle b, float t, ResolvedStyle result) {
        result.setAlpha$foundation(MathHelpersKt.lerp(a.getAlpha(), b.getAlpha(), t));
        result.setScaleX$foundation(MathHelpersKt.lerp(a.getScaleX(), b.getScaleX(), t));
        result.setScaleY$foundation(MathHelpersKt.lerp(a.getScaleY(), b.getScaleY(), t));
        result.setTranslationX$foundation(MathHelpersKt.lerp(a.getTranslationX(), b.getTranslationX(), t));
        result.setTranslationY$foundation(MathHelpersKt.lerp(a.getTranslationY(), b.getTranslationY(), t));
        result.setRotationX$foundation(MathHelpersKt.lerp(a.getRotationX(), b.getRotationX(), t));
        result.setRotationY$foundation(MathHelpersKt.lerp(a.getRotationY(), b.getRotationY(), t));
        result.setRotationZ$foundation(MathHelpersKt.lerp(a.getRotationZ(), b.getRotationZ(), t));
        result.m1450setTransformOrigin__ExYCQ$foundation(TransformOriginKt.TransformOrigin(MathHelpersKt.lerp(TransformOrigin.m5721getPivotFractionXimpl(a.getTransformOrigin()), TransformOrigin.m5721getPivotFractionXimpl(b.getTransformOrigin()), t), MathHelpersKt.lerp(TransformOrigin.m5722getPivotFractionYimpl(a.getTransformOrigin()), TransformOrigin.m5722getPivotFractionYimpl(b.getTransformOrigin()), t)));
        result.setZIndex$foundation(MathHelpersKt.lerp(a.getZIndex(), b.getZIndex(), t));
        result.setShape$foundation(lerp(a.getShape(), b.getShape(), t));
        result.setClip$foundation(t < 0.5f ? a.getClip() : b.getClip());
    }

    public static final void lerpTextDraw(ResolvedStyle a, ResolvedStyle b, float t, ResolvedStyle result) {
        result.m1444setContentColor8_81llA$foundation(ColorKt.m5364lerpjxsXWHM(a.getContentColor(), b.getContentColor(), t));
        result.setContentBrush$foundation(m1460lerpwffgcV4(a.getContentBrush(), a.getContentColor(), b.getContentBrush(), b.getContentColor(), t));
    }

    public static final void lerpTextLayout(ResolvedStyle a, ResolvedStyle b, float t, ResolvedStyle result) {
        long $this$isSpecified$iv = a.getFontSize();
        if (!(TextUnit.m8342getRawTypeimpl($this$isSpecified$iv) == 0)) {
            long $this$isSpecified$iv2 = b.getFontSize();
            if (!(TextUnit.m8342getRawTypeimpl($this$isSpecified$iv2) == 0)) {
                result.m1445setFontSizeR2X_6o$foundation(TextUnitKt.m8364lerpC3pnCVY(a.getFontSize(), b.getFontSize(), t));
            }
        }
        long $this$isSpecified$iv3 = a.getLineHeight();
        if (!(TextUnit.m8342getRawTypeimpl($this$isSpecified$iv3) == 0)) {
            long $this$isSpecified$iv4 = b.getLineHeight();
            if (!(TextUnit.m8342getRawTypeimpl($this$isSpecified$iv4) == 0)) {
                result.m1449setLineHeightR2X_6o$foundation(TextUnitKt.m8364lerpC3pnCVY(a.getLineHeight(), b.getLineHeight(), t));
            }
        }
        long $this$isSpecified$iv5 = a.getLetterSpacing();
        if (!(TextUnit.m8342getRawTypeimpl($this$isSpecified$iv5) == 0)) {
            long $this$isSpecified$iv6 = b.getLetterSpacing();
            if (!(TextUnit.m8342getRawTypeimpl($this$isSpecified$iv6) == 0)) {
                result.m1447setLetterSpacingR2X_6o$foundation(TextUnitKt.m8364lerpC3pnCVY(a.getLetterSpacing(), b.getLetterSpacing(), t));
            }
        }
        result.setFontFamily$foundation(t < 0.5f ? a.getFontFamily() : b.getFontFamily());
        result.setTextIndent$foundation(t < 0.5f ? a.getTextIndent() : b.getTextIndent());
        result.m1442setBaselineShift4Dl_Bck$foundation(t < 0.5f ? a.getBaselineShift() : b.getBaselineShift());
        result.m1448setLineBreakCZqVlQI$foundation(t < 0.5f ? a.getLineBreak() : b.getLineBreak());
        result.setTextEnums$foundation(t < 0.5f ? a.getTextEnums() : b.getTextEnums());
        int $this$getBits$iv = (a.getTextEnums() & FontWeightMask) >> 17;
        int $this$getBits$iv2 = (b.getTextEnums() & FontWeightMask) >> 17;
        if ($this$getBits$iv <= 0 || $this$getBits$iv2 <= 0) {
            return;
        }
        int $this$floorToNearest100$iv = MathHelpersKt.lerp($this$getBits$iv, $this$getBits$iv2, t);
        int weight = ($this$floorToNearest100$iv / 100) * 100;
        int $this$setBits$iv = result.getTextEnums();
        result.setTextEnums$foundation(((~FontWeightMask) & $this$setBits$iv) | ((weight << 17) & FontWeightMask));
    }

    public static final void lerp(ResolvedStyle a, ResolvedStyle b, float t, int flags, ResolvedStyle result) {
        int resultFlags = a.flags | b.flags;
        result.flags = resultFlags;
        int flagsToRun = resultFlags & flags;
        if ((flagsToRun & 8) != 0) {
            lerpOuterLayout(a, b, t, result);
        }
        if ((flagsToRun & 1) != 0) {
            lerpInnerLayout(a, b, t, result);
        }
        if ((flagsToRun & 2) != 0) {
            lerpDraw(a, b, t, result);
        }
        if ((flagsToRun & 4) != 0) {
            lerpLayer(a, b, t, result);
        }
        if ((flagsToRun & 64) != 0) {
            lerpTextDraw(a, b, t, result);
        }
        if ((flagsToRun & 32) != 0) {
            lerpTextLayout(a, b, t, result);
        }
    }

    private static final int floorToNearest100(int $this$floorToNearest100) {
        return ($this$floorToNearest100 / 100) * 100;
    }

    /* JADX INFO: renamed from: lerp-wffgcV4 */
    private static final Brush m1460lerpwffgcV4(Brush leftBrush, long leftColor, Brush rightBrush, long rightColor, float t) {
        Brush a = leftBrush;
        Brush b = rightBrush;
        if (leftBrush == null && rightBrush == null) {
            return null;
        }
        if (leftBrush == null) {
            a = new SolidColor(leftColor, null);
        } else if (rightBrush == null) {
            b = new SolidColor(rightColor, null);
        }
        Object objLerp = Interpolatable.INSTANCE.lerp(a, b, t);
        if (objLerp instanceof Brush) {
            return (Brush) objLerp;
        }
        return null;
    }

    private static final Brush lerp(Brush a, Brush b, float t) {
        Object objLerp = Interpolatable.INSTANCE.lerp(a, b, t);
        if (objLerp instanceof Brush) {
            return (Brush) objLerp;
        }
        return null;
    }

    private static final Shape lerp(Shape a, Shape b, float t) {
        Object objLerp = Interpolatable.INSTANCE.lerp(a, b, t);
        Shape shape = objLerp instanceof Shape ? (Shape) objLerp : null;
        return shape == null ? RectangleShapeKt.getRectangleShape() : shape;
    }

    static {
        StyleScopeKt.apply(new ResolvedStyle(), new Style() { // from class: androidx.compose.foundation.style.ResolvedStyleKt$$ExternalSyntheticLambda0
            @Override // androidx.compose.foundation.style.Style
            public final void applyStyle(StyleScope styleScope) {
                ResolvedStyleKt.TextDefaultsResolvedStyle$lambda$0(styleScope);
            }
        });
        TextDefaultsResolvedStyle = Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: takeOrElse-w1xZEK0 */
    public static final int m1463takeOrElsew1xZEK0(int $this$takeOrElse_u2dw1xZEK0, int other) {
        return !LineBreak.m7907equalsimpl0($this$takeOrElse_u2dw1xZEK0, LineBreak.INSTANCE.m7921getUnspecifiedrAG3T2k()) ? $this$takeOrElse_u2dw1xZEK0 : other;
    }

    /* JADX INFO: renamed from: takeOrElse-y00tBZM */
    public static final float m1464takeOrElsey00tBZM(float $this$takeOrElse_u2dy00tBZM, float other) {
        return BaselineShift.m7868equalsimpl0($this$takeOrElse_u2dy00tBZM, BaselineShift.INSTANCE.m7879getUnspecifiedy9eOQZs()) ? $this$takeOrElse_u2dy00tBZM : other;
    }

    /* JADX INFO: renamed from: takeOrElse-NB67dxo */
    public static final long m1462takeOrElseNB67dxo(long $this$takeOrElse_u2dNB67dxo, long other) {
        return !((TextUnit.m8342getRawTypeimpl($this$takeOrElse_u2dNB67dxo) > 0L ? 1 : (TextUnit.m8342getRawTypeimpl($this$takeOrElse_u2dNB67dxo) == 0L ? 0 : -1)) == 0) ? $this$takeOrElse_u2dNB67dxo : other;
    }

    /* JADX INFO: renamed from: takeOrElse--OWjLjI */
    public static final long m1461takeOrElseOWjLjI(long $this$takeOrElse_u2d_u2dOWjLjI, long other) {
        return ($this$takeOrElse_u2d_u2dOWjLjI > 16L ? 1 : ($this$takeOrElse_u2d_u2dOWjLjI == 16L ? 0 : -1)) != 0 ? $this$takeOrElse_u2d_u2dOWjLjI : other;
    }

    public static final int applyTextEnum(int left, int right) {
        int rightBits$iv = right & 3;
        int mask$iv = ((~3) & left) | (rightBits$iv != 0 ? rightBits$iv : left);
        int rightBits$iv2 = right & 28;
        int mask$iv2 = ((~28) & mask$iv) | (rightBits$iv2 != 0 ? rightBits$iv2 : mask$iv);
        int rightBits$iv3 = right & TextDirectionMask;
        int mask$iv3 = ((~TextDirectionMask) & mask$iv2) | (rightBits$iv3 != 0 ? rightBits$iv3 : mask$iv2);
        int rightBits$iv4 = right & 768;
        int mask$iv4 = ((~768) & mask$iv3) | (rightBits$iv4 != 0 ? rightBits$iv4 : mask$iv3);
        int rightBits$iv5 = right & FontSynthesisMask;
        int mask$iv5 = ((~FontSynthesisMask) & mask$iv4) | (rightBits$iv5 != 0 ? rightBits$iv5 : mask$iv4);
        int rightBits$iv6 = right & FontWeightMask;
        return ((~FontWeightMask) & mask$iv5) | (rightBits$iv6 != 0 ? rightBits$iv6 : mask$iv5);
    }

    public static final int setBitsIfNonZero(int left, int right, int mask) {
        int rightBits = right & mask;
        return ((~mask) & left) | (rightBits != 0 ? rightBits : left);
    }

    public static final int getBits(int $this$getBits, int mask, int shift) {
        return ($this$getBits & mask) >> shift;
    }

    public static final int setBits(int $this$setBits, int mask, int shift, int value) {
        return ((~mask) & $this$setBits) | ((value << shift) & mask);
    }

    public static final int updateHashEnter(int hash, int key) {
        return Integer.rotateLeft(hash, 3) ^ key;
    }

    public static final int updateHashExit(int hash, int key) {
        return Integer.rotateRight(hash ^ key, 3);
    }

    private static final boolean push(MutableIntList $this$push, int value) {
        return $this$push.add(value);
    }

    private static final int pop(MutableIntList $this$pop) {
        MutableIntList this_$iv = $this$pop;
        return $this$pop.removeAt(this_$iv._size - 1);
    }

    public static final Unit getTextDefaultsResolvedStyle() {
        return TextDefaultsResolvedStyle;
    }

    static final void TextDefaultsResolvedStyle$lambda$0(StyleScope $this$apply) {
        $this$apply.mo1407fontSizeR2X_6o(TextUnitKt.getSp(14));
        $this$apply.mo1429letterSpacingR2X_6o(TextUnitKt.getSp(0));
        $this$apply.mo1388contentColor8_81llA(Color.INSTANCE.m5339getBlack0d7_KjU());
        $this$apply.fontWeight(FontWeight.INSTANCE.getNormal());
        $this$apply.mo1408fontStylenzbMABs(FontStyle.INSTANCE.m7692getNormal_LCdwA());
        $this$apply.mo1409fontSynthesis6p3vJLY(FontSynthesis.INSTANCE.m7702getAllGVVA2EU());
        $this$apply.fontFamily(FontFamily.INSTANCE.getDefault());
        $this$apply.mo1382baselineShift4Dl_Bck(BaselineShift.INSTANCE.m7876getNoney9eOQZs());
        $this$apply.textDecoration(TextDecoration.INSTANCE.getNone());
    }

    public static final String flagsAsString(ResolvedStyle $this$flagsAsString) {
        return resolvedStyleFlagsToString($this$flagsAsString.flags);
    }

    public static final String resolvedStyleFlagsToString(int flags) {
        StringBuilder $this$resolvedStyleFlagsToString_u24lambda_u240 = new StringBuilder();
        Ref.BooleanRef first = new Ref.BooleanRef();
        first.element = true;
        if ((flags & 1) != 0) {
            resolvedStyleFlagsToString$lambda$0$emit(first, $this$resolvedStyleFlagsToString_u24lambda_u240, "InnerLayoutFlag");
        }
        if ((flags & 2) != 0) {
            resolvedStyleFlagsToString$lambda$0$emit(first, $this$resolvedStyleFlagsToString_u24lambda_u240, "DrawFlag");
        }
        if ((flags & 4) != 0) {
            resolvedStyleFlagsToString$lambda$0$emit(first, $this$resolvedStyleFlagsToString_u24lambda_u240, "LayerFlag");
        }
        if ((flags & 8) != 0) {
            resolvedStyleFlagsToString$lambda$0$emit(first, $this$resolvedStyleFlagsToString_u24lambda_u240, "OuterLayoutFlag");
        }
        if ((flags & 16) != 0) {
            resolvedStyleFlagsToString$lambda$0$emit(first, $this$resolvedStyleFlagsToString_u24lambda_u240, "AnimatedFlag");
        }
        if ((flags & 32) != 0) {
            resolvedStyleFlagsToString$lambda$0$emit(first, $this$resolvedStyleFlagsToString_u24lambda_u240, "TextLayoutFlag");
        }
        if ((flags & 64) != 0) {
            resolvedStyleFlagsToString$lambda$0$emit(first, $this$resolvedStyleFlagsToString_u24lambda_u240, "TextDrawFlag");
        }
        return $this$resolvedStyleFlagsToString_u24lambda_u240.toString();
    }

    private static final void resolvedStyleFlagsToString$lambda$0$emit(Ref.BooleanRef first, StringBuilder $this_buildString, String value) {
        if (!first.element) {
            $this_buildString.append(", ");
        }
        first.element = false;
        $this_buildString.append(value);
    }
}
