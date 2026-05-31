package androidx.compose.foundation.text;

import androidx.compose.foundation.ComposeFoundationFlags;
import androidx.compose.foundation.internal.InlineClassHelperKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.State;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.InspectableValueKt;
import androidx.compose.ui.platform.InspectorInfo;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.TextStyleKt;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontSynthesis;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;

/* JADX INFO: compiled from: HeightInLinesModifier.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\u001a(\u0010\u0002\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00012\b\b\u0002\u0010\u0007\u001a\u00020\u0001H\u0000\u001a(\u0010\b\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00012\b\b\u0002\u0010\u0007\u001a\u00020\u0001H\u0000\u001a\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\u0001H\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000¨\u0006\u000b²\u0006\n\u0010\f\u001a\u00020\rX\u008a\u0084\u0002"}, d2 = {"DefaultMinLines", "", "heightInLines", "Landroidx/compose/ui/Modifier;", "textStyle", "Landroidx/compose/ui/text/TextStyle;", "minLines", "maxLines", "legacyHeightInLines", "validateMinMaxLines", "", "foundation", "typeface", ""}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class HeightInLinesModifierKt {
    public static final int DefaultMinLines = 1;

    public static /* synthetic */ Modifier heightInLines$default(Modifier modifier, TextStyle textStyle, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 1;
        }
        if ((i3 & 4) != 0) {
            i2 = Integer.MAX_VALUE;
        }
        return heightInLines(modifier, textStyle, i, i2);
    }

    public static final Modifier heightInLines(Modifier $this$heightInLines, TextStyle textStyle, int minLines, int maxLines) {
        validateMinMaxLines(minLines, maxLines);
        if (minLines == 1 && maxLines == Integer.MAX_VALUE) {
            return $this$heightInLines;
        }
        if (ComposeFoundationFlags.isBasicTextFieldMinSizeOptimizationEnabled) {
            return $this$heightInLines.then(new HeightInLinesElement(textStyle, minLines, maxLines));
        }
        return legacyHeightInLines($this$heightInLines, textStyle, minLines, maxLines);
    }

    public static /* synthetic */ Modifier legacyHeightInLines$default(Modifier modifier, TextStyle textStyle, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 1;
        }
        if ((i3 & 4) != 0) {
            i2 = Integer.MAX_VALUE;
        }
        return legacyHeightInLines(modifier, textStyle, i, i2);
    }

    public static final Modifier legacyHeightInLines(Modifier $this$legacyHeightInLines, final TextStyle textStyle, final int minLines, final int maxLines) {
        return ComposedModifierKt.composed($this$legacyHeightInLines, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.foundation.text.HeightInLinesModifierKt$legacyHeightInLines$$inlined$debugInspectorInfo$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(InspectorInfo inspectorInfo) {
                invoke2(inspectorInfo);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(InspectorInfo inspectorInfo) {
                inspectorInfo.setName("heightInLines");
                inspectorInfo.getProperties().set("minLines", Integer.valueOf(minLines));
                inspectorInfo.getProperties().set("maxLines", Integer.valueOf(maxLines));
                inspectorInfo.getProperties().set("textStyle", textStyle);
            }
        } : InspectableValueKt.getNoInspectorInfo(), new Function3() { // from class: androidx.compose.foundation.text.HeightInLinesModifierKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return HeightInLinesModifierKt.legacyHeightInLines$lambda$1(textStyle, minLines, maxLines, (Modifier) obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        });
    }

    static final Modifier legacyHeightInLines$lambda$1(TextStyle $textStyle, int $minLines, int $maxLines, Modifier $this$composed, Composer $composer, int $changed) {
        LayoutDirection layoutDirection;
        Density density;
        $composer.startReplaceGroup(595899793);
        ComposerKt.sourceInformation($composer, "C297@10617L7,298@10682L7,299@10741L7,302@10790L84,304@10911L369,314@11324L430,326@11802L500:HeightInLinesModifier.kt#423gt5");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(595899793, $changed, -1, "androidx.compose.foundation.text.legacyHeightInLines.<anonymous> (HeightInLinesModifier.kt:297)");
        }
        ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
        Object objConsume = $composer.consume(localDensity);
        ComposerKt.sourceInformationMarkerEnd($composer);
        Density density2 = (Density) objConsume;
        ProvidableCompositionLocal<FontFamily.Resolver> localFontFamilyResolver = CompositionLocalsKt.getLocalFontFamilyResolver();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
        Object objConsume2 = $composer.consume(localFontFamilyResolver);
        ComposerKt.sourceInformationMarkerEnd($composer);
        FontFamily.Resolver fontFamilyResolver = (FontFamily.Resolver) objConsume2;
        ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
        Object objConsume3 = $composer.consume(localLayoutDirection);
        ComposerKt.sourceInformationMarkerEnd($composer);
        LayoutDirection layoutDirection2 = (LayoutDirection) objConsume3;
        ComposerKt.sourceInformationMarkerStart($composer, 1429057509, "CC(remember):HeightInLinesModifier.kt#9igjgp");
        boolean invalid$iv = $composer.changed($textStyle) | $composer.changed(layoutDirection2.ordinal());
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            Object value$iv = TextStyleKt.resolveDefaults($textStyle, layoutDirection2);
            $composer.updateRememberedValue(value$iv);
            it$iv = value$iv;
        }
        TextStyle resolvedStyle = (TextStyle) it$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerStart($composer, 1429061666, "CC(remember):HeightInLinesModifier.kt#9igjgp");
        boolean invalid$iv2 = $composer.changed(fontFamilyResolver) | $composer.changed(resolvedStyle);
        Object it$iv2 = $composer.rememberedValue();
        if (invalid$iv2 || it$iv2 == Composer.INSTANCE.getEmpty()) {
            layoutDirection = layoutDirection2;
            FontFamily fontFamily = resolvedStyle.getFontFamily();
            FontWeight fontWeight = resolvedStyle.getFontWeight();
            if (fontWeight == null) {
                fontWeight = FontWeight.INSTANCE.getNormal();
            }
            FontWeight fontWeight2 = fontWeight;
            FontStyle fontStyleM7605getFontStyle4Lr2A7w = resolvedStyle.m7605getFontStyle4Lr2A7w();
            int iM7688unboximpl = fontStyleM7605getFontStyle4Lr2A7w != null ? fontStyleM7605getFontStyle4Lr2A7w.m7688unboximpl() : FontStyle.INSTANCE.m7692getNormal_LCdwA();
            FontSynthesis fontSynthesisM7606getFontSynthesisZQGJjVo = resolvedStyle.m7606getFontSynthesisZQGJjVo();
            int $i$f$cache = fontSynthesisM7606getFontSynthesisZQGJjVo != null ? fontSynthesisM7606getFontSynthesisZQGJjVo.m7701unboximpl() : FontSynthesis.INSTANCE.m7702getAllGVVA2EU();
            Object value$iv2 = fontFamilyResolver.mo7658resolveDPcqOEQ(fontFamily, fontWeight2, iM7688unboximpl, $i$f$cache);
            $composer.updateRememberedValue(value$iv2);
            it$iv2 = value$iv2;
        } else {
            layoutDirection = layoutDirection2;
        }
        State typeface$delegate = (State) it$iv2;
        ComposerKt.sourceInformationMarkerEnd($composer);
        Object value = typeface$delegate.getValue();
        ComposerKt.sourceInformationMarkerStart($composer, 1429074943, "CC(remember):HeightInLinesModifier.kt#9igjgp");
        boolean invalid$iv3 = $composer.changed(value) | $composer.changed(density2) | $composer.changed(fontFamilyResolver) | $composer.changed($textStyle) | $composer.changed(layoutDirection.ordinal());
        Object it$iv3 = $composer.rememberedValue();
        if (invalid$iv3 || it$iv3 == Composer.INSTANCE.getEmpty()) {
            long arg0$iv = TextFieldDelegateKt.computeSizeForDefaultText(resolvedStyle, density2, fontFamilyResolver, TextFieldDelegateKt.getEmptyTextReplacement(), 1);
            Object value$iv3 = Integer.valueOf((int) (arg0$iv & 4294967295L));
            $composer.updateRememberedValue(value$iv3);
            it$iv3 = value$iv3;
        }
        int firstLineHeight = ((Number) it$iv3).intValue();
        ComposerKt.sourceInformationMarkerEnd($composer);
        Object value2 = typeface$delegate.getValue();
        ComposerKt.sourceInformationMarkerStart($composer, 1429090309, "CC(remember):HeightInLinesModifier.kt#9igjgp");
        boolean invalid$iv4 = $composer.changed(value2) | $composer.changed(density2) | $composer.changed(fontFamilyResolver) | $composer.changed($textStyle) | $composer.changed(layoutDirection.ordinal());
        Object it$iv4 = $composer.rememberedValue();
        if (invalid$iv4 || it$iv4 == Composer.INSTANCE.getEmpty()) {
            String twoLines = TextFieldDelegateKt.getEmptyTextReplacement() + '\n' + TextFieldDelegateKt.getEmptyTextReplacement();
            long arg0$iv2 = TextFieldDelegateKt.computeSizeForDefaultText(resolvedStyle, density2, fontFamilyResolver, twoLines, 2);
            density = density2;
            Object value$iv4 = Integer.valueOf((int) (arg0$iv2 & 4294967295L));
            $composer.updateRememberedValue(value$iv4);
            it$iv4 = value$iv4;
        } else {
            density = density2;
        }
        int firstTwoLinesHeight = ((Number) it$iv4).intValue();
        ComposerKt.sourceInformationMarkerEnd($composer);
        int lineHeight = firstTwoLinesHeight - firstLineHeight;
        Integer precomputedMinLinesHeight = $minLines == 1 ? null : Integer.valueOf((($minLines - 1) * lineHeight) + firstLineHeight);
        Integer precomputedMaxLinesHeight = $maxLines != Integer.MAX_VALUE ? Integer.valueOf((($maxLines - 1) * lineHeight) + firstLineHeight) : null;
        Density $this$legacyHeightInLines_u24lambda_u241_u245 = density;
        Modifier modifierM1102heightInVpY3zN4 = SizeKt.m1102heightInVpY3zN4(Modifier.INSTANCE, precomputedMinLinesHeight != null ? $this$legacyHeightInLines_u24lambda_u241_u245.mo429toDpu2uoSUM(precomputedMinLinesHeight.intValue()) : Dp.INSTANCE.m8170getUnspecifiedD9Ej5fM(), precomputedMaxLinesHeight != null ? $this$legacyHeightInLines_u24lambda_u241_u245.mo429toDpu2uoSUM(precomputedMaxLinesHeight.intValue()) : Dp.INSTANCE.m8170getUnspecifiedD9Ej5fM());
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceGroup();
        return modifierM1102heightInVpY3zN4;
    }

    public static final void validateMinMaxLines(int minLines, int maxLines) {
        boolean value$iv = minLines > 0 && maxLines > 0;
        if (!value$iv) {
            InlineClassHelperKt.throwIllegalArgumentException("both minLines " + minLines + " and maxLines " + maxLines + " must be greater than zero");
        }
        boolean value$iv2 = minLines <= maxLines;
        if (value$iv2) {
            return;
        }
        InlineClassHelperKt.throwIllegalArgumentException("minLines " + minLines + " must be less than or equal to maxLines " + maxLines);
    }
}
