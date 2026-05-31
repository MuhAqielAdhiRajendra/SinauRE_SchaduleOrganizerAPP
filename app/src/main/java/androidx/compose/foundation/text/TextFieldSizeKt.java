package androidx.compose.foundation.text;

import androidx.compose.foundation.ComposeFoundationFlags;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.State;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.LayoutModifierKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.TextStyleKt;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontSynthesis;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: TextFieldSize.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0000\u001a\u0014\u0010\u0004\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0000¨\u0006\u0005²\u0006\n\u0010\u0006\u001a\u00020\u0007X\u008a\u0084\u0002"}, d2 = {"textFieldMinSize", "Landroidx/compose/ui/Modifier;", "style", "Landroidx/compose/ui/text/TextStyle;", "legacyTextFieldMinSize", "foundation", "typeface", ""}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class TextFieldSizeKt {
    public static final Modifier textFieldMinSize(Modifier $this$textFieldMinSize, TextStyle style) {
        if (ComposeFoundationFlags.isBasicTextFieldMinSizeOptimizationEnabled) {
            return $this$textFieldMinSize.then(new TextFieldSizeElement(style));
        }
        return legacyTextFieldMinSize($this$textFieldMinSize, style);
    }

    public static final Modifier legacyTextFieldMinSize(Modifier $this$legacyTextFieldMinSize, final TextStyle style) {
        return ComposedModifierKt.composed$default($this$legacyTextFieldMinSize, null, new Function3() { // from class: androidx.compose.foundation.text.TextFieldSizeKt$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return TextFieldSizeKt.legacyTextFieldMinSize$lambda$0(style, (Modifier) obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        }, 1, null);
    }

    static final Modifier legacyTextFieldMinSize$lambda$0(TextStyle $style, Modifier $this$composed, Composer $composer, int $changed) {
        LayoutDirection layoutDirection;
        Density density;
        LayoutDirection layoutDirection2;
        $composer.startReplaceGroup(-390200690);
        ComposerKt.sourceInformation($composer, "C163@6412L7,164@6473L7,165@6528L7,167@6561L76,169@6666L341,178@7032L107,184@7257L488:TextFieldSize.kt#423gt5");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-390200690, $changed, -1, "androidx.compose.foundation.text.legacyTextFieldMinSize.<anonymous> (TextFieldSize.kt:163)");
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
        LayoutDirection layoutDirection3 = (LayoutDirection) objConsume3;
        ComposerKt.sourceInformationMarkerStart($composer, -1320702438, "CC(remember):TextFieldSize.kt#9igjgp");
        boolean invalid$iv = $composer.changed($style) | $composer.changed(layoutDirection3.ordinal());
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            Object value$iv = TextStyleKt.resolveDefaults($style, layoutDirection3);
            $composer.updateRememberedValue(value$iv);
            it$iv = value$iv;
        }
        TextStyle resolvedStyle = (TextStyle) it$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerStart($composer, -1320698813, "CC(remember):TextFieldSize.kt#9igjgp");
        boolean invalid$iv2 = $composer.changed(fontFamilyResolver) | $composer.changed(resolvedStyle);
        Object it$iv2 = $composer.rememberedValue();
        if (invalid$iv2 || it$iv2 == Composer.INSTANCE.getEmpty()) {
            FontFamily fontFamily = resolvedStyle.getFontFamily();
            FontWeight fontWeight = resolvedStyle.getFontWeight();
            if (fontWeight == null) {
                fontWeight = FontWeight.INSTANCE.getNormal();
            }
            FontStyle fontStyleM7605getFontStyle4Lr2A7w = resolvedStyle.m7605getFontStyle4Lr2A7w();
            int iM7688unboximpl = fontStyleM7605getFontStyle4Lr2A7w != null ? fontStyleM7605getFontStyle4Lr2A7w.m7688unboximpl() : FontStyle.INSTANCE.m7692getNormal_LCdwA();
            FontSynthesis fontSynthesisM7606getFontSynthesisZQGJjVo = resolvedStyle.m7606getFontSynthesisZQGJjVo();
            layoutDirection = layoutDirection3;
            Object value$iv2 = fontFamilyResolver.mo7658resolveDPcqOEQ(fontFamily, fontWeight, iM7688unboximpl, fontSynthesisM7606getFontSynthesisZQGJjVo != null ? fontSynthesisM7606getFontSynthesisZQGJjVo.m7701unboximpl() : FontSynthesis.INSTANCE.m7702getAllGVVA2EU());
            $composer.updateRememberedValue(value$iv2);
            it$iv2 = value$iv2;
        } else {
            layoutDirection = layoutDirection3;
        }
        State typeface$delegate = (State) it$iv2;
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerStart($composer, -1320687335, "CC(remember):TextFieldSize.kt#9igjgp");
        Object value$iv3 = $composer.rememberedValue();
        if (value$iv3 == Composer.INSTANCE.getEmpty()) {
            density = density2;
            layoutDirection2 = layoutDirection;
            value$iv3 = new LegacyTextFieldSize(layoutDirection2, density, fontFamilyResolver, $style, typeface$delegate.getValue());
            $composer.updateRememberedValue(value$iv3);
        } else {
            density = density2;
            layoutDirection2 = layoutDirection;
        }
        final LegacyTextFieldSize minSizeState = (LegacyTextFieldSize) value$iv3;
        ComposerKt.sourceInformationMarkerEnd($composer);
        minSizeState.update(layoutDirection2, density, fontFamilyResolver, resolvedStyle, typeface$delegate.getValue());
        Modifier.Companion companion = Modifier.INSTANCE;
        ComposerKt.sourceInformationMarkerStart($composer, -1320679754, "CC(remember):TextFieldSize.kt#9igjgp");
        boolean invalid$iv3 = $composer.changedInstance(minSizeState);
        Object it$iv3 = $composer.rememberedValue();
        if (invalid$iv3 || it$iv3 == Composer.INSTANCE.getEmpty()) {
            Object value$iv4 = new Function3() { // from class: androidx.compose.foundation.text.TextFieldSizeKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return TextFieldSizeKt.legacyTextFieldMinSize$lambda$0$4$0(minSizeState, (MeasureScope) obj, (Measurable) obj2, (Constraints) obj3);
                }
            };
            $composer.updateRememberedValue(value$iv4);
            it$iv3 = value$iv4;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        Modifier modifierLayout = LayoutModifierKt.layout(companion, (Function3) it$iv3);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceGroup();
        return modifierLayout;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MeasureResult legacyTextFieldMinSize$lambda$0$4$0(LegacyTextFieldSize $minSizeState, MeasureScope $this$layout, Measurable measurable, Constraints constraints) {
        long minSize = $minSizeState.getMinSize();
        long value = constraints.getValue();
        long arg0$iv = constraints.getValue();
        int iCoerceIn = RangesKt.coerceIn((int) (minSize >> 32), Constraints.m8105getMinWidthimpl(arg0$iv), Constraints.m8103getMaxWidthimpl(constraints.getValue()));
        long arg0$iv2 = constraints.getValue();
        long childConstraints = Constraints.m8092copyZbe2FdA(value, (11 & 1) != 0 ? Constraints.m8105getMinWidthimpl(value) : iCoerceIn, (11 & 2) != 0 ? Constraints.m8103getMaxWidthimpl(value) : 0, (11 & 4) != 0 ? Constraints.m8104getMinHeightimpl(value) : RangesKt.coerceIn((int) (4294967295L & minSize), Constraints.m8104getMinHeightimpl(arg0$iv2), Constraints.m8102getMaxHeightimpl(constraints.getValue())), (11 & 8) != 0 ? Constraints.m8102getMaxHeightimpl(value) : 0);
        final Placeable measured = measurable.mo6783measureBRTryo0(childConstraints);
        return MeasureScope.layout$default($this$layout, measured.getWidth(), measured.getHeight(), null, new Function1() { // from class: androidx.compose.foundation.text.TextFieldSizeKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TextFieldSizeKt.legacyTextFieldMinSize$lambda$0$4$0$0(measured, (Placeable.PlacementScope) obj);
            }
        }, 4, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit legacyTextFieldMinSize$lambda$0$4$0$0(Placeable $measured, Placeable.PlacementScope $this$layout) {
        Placeable.PlacementScope.placeRelative$default($this$layout, $measured, 0, 0, 0.0f, 4, null);
        return Unit.INSTANCE;
    }
}
