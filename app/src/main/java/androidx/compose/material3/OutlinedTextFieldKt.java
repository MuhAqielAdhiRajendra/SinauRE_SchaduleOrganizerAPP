package androidx.compose.material3;

import androidx.autofill.HintConstants;
import androidx.compose.foundation.ScrollKt;
import androidx.compose.foundation.ScrollState;
import androidx.compose.foundation.interaction.FocusInteractionKt;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.text.BasicTextFieldKt;
import androidx.compose.foundation.text.KeyboardActions;
import androidx.compose.foundation.text.KeyboardOptions;
import androidx.compose.foundation.text.input.InputTransformation;
import androidx.compose.foundation.text.input.KeyboardActionHandler;
import androidx.compose.foundation.text.input.OutputTransformation;
import androidx.compose.foundation.text.input.TextFieldLineLimits;
import androidx.compose.foundation.text.input.TextFieldState;
import androidx.compose.foundation.text.selection.TextSelectionColorsKt;
import androidx.compose.material3.TextFieldLabelPosition;
import androidx.compose.material3.internal.FloatProducer;
import androidx.compose.material3.internal.Strings;
import androidx.compose.material3.internal.Strings_androidKt;
import androidx.compose.material3.internal.TextFieldImplKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.composer.linkbuffer.GroupFlagsKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.DrawModifierKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.ClipOp;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.SolidColor;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawContext;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.graphics.drawscope.DrawTransform;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.text.PlatformTextStyle;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontSynthesis;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.input.TextFieldValue;
import androidx.compose.ui.text.input.VisualTransformation;
import androidx.compose.ui.text.intl.LocaleList;
import androidx.compose.ui.text.style.BaselineShift;
import androidx.compose.ui.text.style.LineHeightStyle;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextGeometricTransform;
import androidx.compose.ui.text.style.TextIndent;
import androidx.compose.ui.text.style.TextMotion;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.profileinstaller.ProfileVerifier;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: OutlinedTextField.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000Ö\u0001\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a£\u0003\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2 \b\u0002\u0010\r\u001a\u001a\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u000e¢\u0006\u0002\b\u0010¢\u0006\u0002\b\u00112\u0015\b\u0002\u0010\u0012\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0013¢\u0006\u0002\b\u00102\u0015\b\u0002\u0010\u0014\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0013¢\u0006\u0002\b\u00102\u0015\b\u0002\u0010\u0015\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0013¢\u0006\u0002\b\u00102\u0015\b\u0002\u0010\u0016\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0013¢\u0006\u0002\b\u00102\u0015\b\u0002\u0010\u0017\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0013¢\u0006\u0002\b\u00102\u0015\b\u0002\u0010\u0018\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0013¢\u0006\u0002\b\u00102\b\b\u0002\u0010\u0019\u001a\u00020\u00072\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\b\b\u0002\u0010\u001e\u001a\u00020\u001f2\n\b\u0002\u0010 \u001a\u0004\u0018\u00010!2\b\b\u0002\u0010\"\u001a\u00020#28\b\u0002\u0010$\u001a2\u0012\u0004\u0012\u00020&\u0012\u001b\u0012\u0019\u0012\u0006\u0012\u0004\u0018\u00010'0\u0013¢\u0006\f\b(\u0012\b\b)\u0012\u0004\b\b(*\u0012\u0004\u0012\u00020\u0001\u0018\u00010%¢\u0006\u0002\b\u00112\b\b\u0002\u0010+\u001a\u00020,2\b\b\u0002\u0010-\u001a\u00020.2\b\b\u0002\u0010/\u001a\u0002002\b\b\u0002\u00101\u001a\u0002022\n\b\u0002\u00103\u001a\u0004\u0018\u000104H\u0007¢\u0006\u0002\u00105\u001aØ\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u00106\u001a\u0002072\u0012\u00108\u001a\u000e\u0012\u0004\u0012\u000207\u0012\u0004\u0012\u00020\u00010\u000e2\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\n2\u0015\b\u0002\u0010\r\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0013¢\u0006\u0002\b\u00102\u0015\b\u0002\u0010\u0012\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0013¢\u0006\u0002\b\u00102\u0015\b\u0002\u0010\u0014\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0013¢\u0006\u0002\b\u00102\u0015\b\u0002\u0010\u0015\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0013¢\u0006\u0002\b\u00102\u0015\b\u0002\u0010\u0016\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0013¢\u0006\u0002\b\u00102\u0015\b\u0002\u0010\u0017\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0013¢\u0006\u0002\b\u00102\u0015\b\u0002\u0010\u0018\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0013¢\u0006\u0002\b\u00102\b\b\u0002\u0010\u0019\u001a\u00020\u00072\b\b\u0002\u00109\u001a\u00020:2\b\b\u0002\u0010\u001e\u001a\u00020\u001f2\b\b\u0002\u0010;\u001a\u00020<2\b\b\u0002\u0010=\u001a\u00020\u00072\b\b\u0002\u0010>\u001a\u00020?2\b\b\u0002\u0010@\u001a\u00020?2\n\b\u0002\u00103\u001a\u0004\u0018\u0001042\b\b\u0002\u0010-\u001a\u00020.2\b\b\u0002\u0010/\u001a\u000200H\u0007¢\u0006\u0002\u0010A\u001aØ\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u00106\u001a\u00020B2\u0012\u00108\u001a\u000e\u0012\u0004\u0012\u00020B\u0012\u0004\u0012\u00020\u00010\u000e2\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\n2\u0015\b\u0002\u0010\r\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0013¢\u0006\u0002\b\u00102\u0015\b\u0002\u0010\u0012\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0013¢\u0006\u0002\b\u00102\u0015\b\u0002\u0010\u0014\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0013¢\u0006\u0002\b\u00102\u0015\b\u0002\u0010\u0015\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0013¢\u0006\u0002\b\u00102\u0015\b\u0002\u0010\u0016\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0013¢\u0006\u0002\b\u00102\u0015\b\u0002\u0010\u0017\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0013¢\u0006\u0002\b\u00102\u0015\b\u0002\u0010\u0018\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0013¢\u0006\u0002\b\u00102\b\b\u0002\u0010\u0019\u001a\u00020\u00072\b\b\u0002\u00109\u001a\u00020:2\b\b\u0002\u0010\u001e\u001a\u00020\u001f2\b\b\u0002\u0010;\u001a\u00020<2\b\b\u0002\u0010=\u001a\u00020\u00072\b\b\u0002\u0010>\u001a\u00020?2\b\b\u0002\u0010@\u001a\u00020?2\n\b\u0002\u00103\u001a\u0004\u0018\u0001042\b\b\u0002\u0010-\u001a\u00020.2\b\b\u0002\u0010/\u001a\u000200H\u0007¢\u0006\u0002\u0010C\u001a\u0088\u0002\u0010D\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0011\u0010E\u001a\r\u0012\u0004\u0012\u00020\u00010\u0013¢\u0006\u0002\b\u00102\u0019\u0010\u0012\u001a\u0015\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u000e¢\u0006\u0002\b\u00102\u0013\u0010\r\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0013¢\u0006\u0002\b\u00102\u0013\u0010F\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0013¢\u0006\u0002\b\u00102\u0013\u0010G\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0013¢\u0006\u0002\b\u00102\u0013\u0010\u0016\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0013¢\u0006\u0002\b\u00102\u0013\u0010\u0017\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0013¢\u0006\u0002\b\u00102\u0006\u0010=\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010H\u001a\u00020I2\u0012\u0010J\u001a\u000e\u0012\u0004\u0012\u00020K\u0012\u0004\u0012\u00020\u00010\u000e2\u0011\u0010L\u001a\r\u0012\u0004\u0012\u00020\u00010\u0013¢\u0006\u0002\b\u00102\u0013\u0010M\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0013¢\u0006\u0002\b\u00102\u0006\u0010N\u001a\u000202H\u0001¢\u0006\u0002\u0010O\u001a*\u0010P\u001a\u00020\u0005*\u00020\u00052\f\u0010Q\u001a\b\u0012\u0004\u0012\u00020K0\u00132\u0006\u0010R\u001a\u00020S2\u0006\u0010N\u001a\u000202H\u0000\"\u0010\u0010T\u001a\u00020UX\u0082\u0004¢\u0006\u0004\n\u0002\u0010V¨\u0006W"}, d2 = {"OutlinedTextField", "", "state", "Landroidx/compose/foundation/text/input/TextFieldState;", "modifier", "Landroidx/compose/ui/Modifier;", "enabled", "", "readOnly", "textStyle", "Landroidx/compose/ui/text/TextStyle;", "labelPosition", "Landroidx/compose/material3/TextFieldLabelPosition;", "label", "Lkotlin/Function1;", "Landroidx/compose/material3/TextFieldLabelScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "placeholder", "Lkotlin/Function0;", "leadingIcon", "trailingIcon", "prefix", "suffix", "supportingText", "isError", "inputTransformation", "Landroidx/compose/foundation/text/input/InputTransformation;", "outputTransformation", "Landroidx/compose/foundation/text/input/OutputTransformation;", "keyboardOptions", "Landroidx/compose/foundation/text/KeyboardOptions;", "onKeyboardAction", "Landroidx/compose/foundation/text/input/KeyboardActionHandler;", "lineLimits", "Landroidx/compose/foundation/text/input/TextFieldLineLimits;", "onTextLayout", "Lkotlin/Function2;", "Landroidx/compose/ui/unit/Density;", "Landroidx/compose/ui/text/TextLayoutResult;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "getResult", "scrollState", "Landroidx/compose/foundation/ScrollState;", "shape", "Landroidx/compose/ui/graphics/Shape;", "colors", "Landroidx/compose/material3/TextFieldColors;", "contentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "(Landroidx/compose/foundation/text/input/TextFieldState;Landroidx/compose/ui/Modifier;ZZLandroidx/compose/ui/text/TextStyle;Landroidx/compose/material3/TextFieldLabelPosition;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;ZLandroidx/compose/foundation/text/input/InputTransformation;Landroidx/compose/foundation/text/input/OutputTransformation;Landroidx/compose/foundation/text/KeyboardOptions;Landroidx/compose/foundation/text/input/KeyboardActionHandler;Landroidx/compose/foundation/text/input/TextFieldLineLimits;Lkotlin/jvm/functions/Function2;Landroidx/compose/foundation/ScrollState;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/TextFieldColors;Landroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;IIII)V", "value", "", "onValueChange", "visualTransformation", "Landroidx/compose/ui/text/input/VisualTransformation;", "keyboardActions", "Landroidx/compose/foundation/text/KeyboardActions;", "singleLine", "maxLines", "", "minLines", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZZLandroidx/compose/ui/text/TextStyle;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;ZLandroidx/compose/ui/text/input/VisualTransformation;Landroidx/compose/foundation/text/KeyboardOptions;Landroidx/compose/foundation/text/KeyboardActions;ZIILandroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/TextFieldColors;Landroidx/compose/runtime/Composer;IIII)V", "Landroidx/compose/ui/text/input/TextFieldValue;", "(Landroidx/compose/ui/text/input/TextFieldValue;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZZLandroidx/compose/ui/text/TextStyle;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;ZLandroidx/compose/ui/text/input/VisualTransformation;Landroidx/compose/foundation/text/KeyboardOptions;Landroidx/compose/foundation/text/KeyboardActions;ZIILandroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/TextFieldColors;Landroidx/compose/runtime/Composer;IIII)V", "OutlinedTextFieldLayout", "textField", "leading", "trailing", "labelProgress", "Landroidx/compose/material3/internal/FloatProducer;", "onLabelMeasured", "Landroidx/compose/ui/geometry/Size;", "container", "supporting", "paddingValues", "(Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;ZLandroidx/compose/material3/TextFieldLabelPosition;Landroidx/compose/material3/internal/FloatProducer;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/runtime/Composer;II)V", "outlineCutout", "labelSize", "alignment", "Landroidx/compose/ui/Alignment$Horizontal;", "OutlinedTextFieldInnerPadding", "Landroidx/compose/ui/unit/Dp;", "F", "material3"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class OutlinedTextFieldKt {
    private static final float OutlinedTextFieldInnerPadding = Dp.m8150constructorimpl(4);

    static final Unit OutlinedTextField$lambda$2(TextFieldState textFieldState, Modifier modifier, boolean z, boolean z2, TextStyle textStyle, TextFieldLabelPosition textFieldLabelPosition, Function3 function3, Function2 function2, Function2 function22, Function2 function23, Function2 function24, Function2 function25, Function2 function26, boolean z3, InputTransformation inputTransformation, OutputTransformation outputTransformation, KeyboardOptions keyboardOptions, KeyboardActionHandler keyboardActionHandler, TextFieldLineLimits textFieldLineLimits, Function2 function27, ScrollState scrollState, Shape shape, TextFieldColors textFieldColors, PaddingValues paddingValues, MutableInteractionSource mutableInteractionSource, int i, int i2, int i3, int i4, Composer composer, int i5) {
        OutlinedTextField(textFieldState, modifier, z, z2, textStyle, textFieldLabelPosition, function3, function2, function22, function23, function24, function25, function26, z3, inputTransformation, outputTransformation, keyboardOptions, keyboardActionHandler, textFieldLineLimits, function27, scrollState, shape, textFieldColors, paddingValues, mutableInteractionSource, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), RecomposeScopeImplKt.updateChangedFlags(i3), i4);
        return Unit.INSTANCE;
    }

    static final Unit OutlinedTextField$lambda$5(String str, Function1 function1, Modifier modifier, boolean z, boolean z2, TextStyle textStyle, Function2 function2, Function2 function22, Function2 function23, Function2 function24, Function2 function25, Function2 function26, Function2 function27, boolean z3, VisualTransformation visualTransformation, KeyboardOptions keyboardOptions, KeyboardActions keyboardActions, boolean z4, int i, int i2, MutableInteractionSource mutableInteractionSource, Shape shape, TextFieldColors textFieldColors, int i3, int i4, int i5, int i6, Composer composer, int i7) {
        OutlinedTextField(str, (Function1<? super String, Unit>) function1, modifier, z, z2, textStyle, (Function2<? super Composer, ? super Integer, Unit>) function2, (Function2<? super Composer, ? super Integer, Unit>) function22, (Function2<? super Composer, ? super Integer, Unit>) function23, (Function2<? super Composer, ? super Integer, Unit>) function24, (Function2<? super Composer, ? super Integer, Unit>) function25, (Function2<? super Composer, ? super Integer, Unit>) function26, (Function2<? super Composer, ? super Integer, Unit>) function27, z3, visualTransformation, keyboardOptions, keyboardActions, z4, i, i2, mutableInteractionSource, shape, textFieldColors, composer, RecomposeScopeImplKt.updateChangedFlags(i3 | 1), RecomposeScopeImplKt.updateChangedFlags(i4), RecomposeScopeImplKt.updateChangedFlags(i5), i6);
        return Unit.INSTANCE;
    }

    static final Unit OutlinedTextField$lambda$8(TextFieldValue textFieldValue, Function1 function1, Modifier modifier, boolean z, boolean z2, TextStyle textStyle, Function2 function2, Function2 function22, Function2 function23, Function2 function24, Function2 function25, Function2 function26, Function2 function27, boolean z3, VisualTransformation visualTransformation, KeyboardOptions keyboardOptions, KeyboardActions keyboardActions, boolean z4, int i, int i2, MutableInteractionSource mutableInteractionSource, Shape shape, TextFieldColors textFieldColors, int i3, int i4, int i5, int i6, Composer composer, int i7) {
        OutlinedTextField(textFieldValue, (Function1<? super TextFieldValue, Unit>) function1, modifier, z, z2, textStyle, (Function2<? super Composer, ? super Integer, Unit>) function2, (Function2<? super Composer, ? super Integer, Unit>) function22, (Function2<? super Composer, ? super Integer, Unit>) function23, (Function2<? super Composer, ? super Integer, Unit>) function24, (Function2<? super Composer, ? super Integer, Unit>) function25, (Function2<? super Composer, ? super Integer, Unit>) function26, (Function2<? super Composer, ? super Integer, Unit>) function27, z3, visualTransformation, keyboardOptions, keyboardActions, z4, i, i2, mutableInteractionSource, shape, textFieldColors, composer, RecomposeScopeImplKt.updateChangedFlags(i3 | 1), RecomposeScopeImplKt.updateChangedFlags(i4), RecomposeScopeImplKt.updateChangedFlags(i5), i6);
        return Unit.INSTANCE;
    }

    static final Unit OutlinedTextFieldLayout$lambda$20(Modifier modifier, Function2 function2, Function3 function3, Function2 function22, Function2 function23, Function2 function24, Function2 function25, Function2 function26, boolean z, TextFieldLabelPosition textFieldLabelPosition, FloatProducer floatProducer, Function1 function1, Function2 function27, Function2 function28, PaddingValues paddingValues, int i, int i2, Composer composer, int i3) {
        OutlinedTextFieldLayout(modifier, function2, function3, function22, function23, function24, function25, function26, z, textFieldLabelPosition, floatProducer, function1, function27, function28, paddingValues, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2));
        return Unit.INSTANCE;
    }

    public static final void OutlinedTextField(final TextFieldState state, Modifier modifier, boolean enabled, boolean readOnly, TextStyle textStyle, TextFieldLabelPosition labelPosition, Function3<? super TextFieldLabelScope, ? super Composer, ? super Integer, Unit> function3, Function2<? super Composer, ? super Integer, Unit> function2, Function2<? super Composer, ? super Integer, Unit> function22, Function2<? super Composer, ? super Integer, Unit> function23, Function2<? super Composer, ? super Integer, Unit> function24, Function2<? super Composer, ? super Integer, Unit> function25, Function2<? super Composer, ? super Integer, Unit> function26, boolean isError, InputTransformation inputTransformation, OutputTransformation outputTransformation, KeyboardOptions keyboardOptions, KeyboardActionHandler onKeyboardAction, TextFieldLineLimits lineLimits, Function2<? super Density, ? super Function0<TextLayoutResult>, Unit> function27, ScrollState scrollState, Shape shape, TextFieldColors colors, PaddingValues contentPadding, MutableInteractionSource interactionSource, Composer $composer, final int $changed, final int $changed1, final int $changed2, final int i) {
        TextFieldState textFieldState;
        Modifier modifier2;
        boolean enabled2;
        boolean readOnly2;
        TextStyle textStyle2;
        TextFieldLabelPosition labelPosition2;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        final Function2<? super Composer, ? super Integer, Unit> function28;
        final Function2<? super Composer, ? super Integer, Unit> function29;
        final InputTransformation inputTransformation2;
        final OutputTransformation outputTransformation2;
        final KeyboardOptions keyboardOptions2;
        final KeyboardActionHandler onKeyboardAction2;
        final TextFieldLineLimits lineLimits2;
        final Function2<? super Density, ? super Function0<TextLayoutResult>, Unit> function210;
        final ScrollState scrollState2;
        final Shape shape2;
        final TextFieldColors colors2;
        final PaddingValues contentPadding2;
        final MutableInteractionSource interactionSource2;
        Composer $composer2;
        final boolean enabled3;
        final boolean readOnly3;
        final TextStyle textStyle3;
        final TextFieldLabelPosition labelPosition3;
        final Modifier modifier3;
        final Function3<? super TextFieldLabelScope, ? super Composer, ? super Integer, Unit> function32;
        final Function2<? super Composer, ? super Integer, Unit> function211;
        final Function2<? super Composer, ? super Integer, Unit> function212;
        final Function2<? super Composer, ? super Integer, Unit> function213;
        final Function2<? super Composer, ? super Integer, Unit> function214;
        final boolean isError2;
        Function3<? super TextFieldLabelScope, ? super Composer, ? super Integer, Unit> function33;
        Function2<? super Composer, ? super Integer, Unit> function215;
        Function2<? super Composer, ? super Integer, Unit> function216;
        Function2<? super Composer, ? super Integer, Unit> function217;
        Function2<? super Composer, ? super Integer, Unit> function218;
        Function2<? super Composer, ? super Integer, Unit> function219;
        Function2<? super Composer, ? super Integer, Unit> function220;
        boolean isError3;
        InputTransformation inputTransformation3;
        OutputTransformation outputTransformation3;
        KeyboardOptions keyboardOptions3;
        KeyboardActionHandler onKeyboardAction3;
        TextFieldLineLimits lineLimits3;
        Function2<? super Density, ? super Function0<TextLayoutResult>, Unit> function221;
        Function3<? super TextFieldLabelScope, ? super Composer, ? super Integer, Unit> function34;
        Function2<? super Composer, ? super Integer, Unit> function222;
        ScrollState scrollState3;
        Shape shape3;
        Shape shape4;
        TextFieldColors colors3;
        PaddingValues contentPadding3;
        Shape shape5;
        PaddingValues contentPadding4;
        boolean readOnly4;
        Function2<? super Composer, ? super Integer, Unit> function223;
        InputTransformation inputTransformation4;
        OutputTransformation outputTransformation4;
        KeyboardOptions keyboardOptions4;
        KeyboardActionHandler onKeyboardAction4;
        Function2<? super Density, ? super Function0<TextLayoutResult>, Unit> function224;
        ScrollState scrollState4;
        Function2<? super Composer, ? super Integer, Unit> function225;
        TextFieldColors colors4;
        Function2<? super Composer, ? super Integer, Unit> function226;
        TextStyle textStyle4;
        Modifier modifier4;
        Function2<? super Composer, ? super Integer, Unit> function227;
        Function2<? super Composer, ? super Integer, Unit> function228;
        boolean isError4;
        MutableInteractionSource interactionSource3;
        Function2<? super Composer, ? super Integer, Unit> function229;
        TextFieldLabelPosition labelPosition4;
        Function3<? super TextFieldLabelScope, ? super Composer, ? super Integer, Unit> function35;
        MutableInteractionSource interactionSource4;
        MutableInteractionSource interactionSource5;
        int i10;
        Composer $composer3 = $composer.startRestartGroup(-2007078942);
        ComposerKt.sourceInformation($composer3, "C(OutlinedTextField)N(state,modifier,enabled,readOnly,textStyle,labelPosition,label,placeholder,leadingIcon,trailingIcon,prefix,suffix,supportingText,isError,inputTransformation,outputTransformation,keyboardOptions,onKeyboardAction,lineLimits,onTextLayout,scrollState,shape,colors,contentPadding,interactionSource)239@14004L2781,239@13917L2868:OutlinedTextField.kt#uh7d8r");
        int $dirty = $changed;
        int $dirty1 = $changed1;
        int $dirty2 = $changed2;
        if ((i & 1) != 0) {
            $dirty |= 6;
            textFieldState = state;
        } else if (($changed & 6) == 0) {
            textFieldState = state;
            $dirty |= $composer3.changed(textFieldState) ? 4 : 2;
        } else {
            textFieldState = state;
        }
        int i11 = i & 2;
        if (i11 != 0) {
            $dirty |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty |= $composer3.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i12 = i & 4;
        if (i12 != 0) {
            $dirty |= 384;
            enabled2 = enabled;
        } else if (($changed & 384) == 0) {
            enabled2 = enabled;
            $dirty |= $composer3.changed(enabled2) ? 256 : 128;
        } else {
            enabled2 = enabled;
        }
        int i13 = i & 8;
        if (i13 != 0) {
            $dirty |= 3072;
            readOnly2 = readOnly;
        } else if (($changed & 3072) == 0) {
            readOnly2 = readOnly;
            $dirty |= $composer3.changed(readOnly2) ? 2048 : 1024;
        } else {
            readOnly2 = readOnly;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                textStyle2 = textStyle;
                if ($composer3.changed(textStyle2)) {
                    i10 = 16384;
                }
                $dirty |= i10;
            } else {
                textStyle2 = textStyle;
            }
            i10 = 8192;
            $dirty |= i10;
        } else {
            textStyle2 = textStyle;
        }
        if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            if ((i & 32) == 0) {
                labelPosition2 = labelPosition;
                int i14 = $composer3.changed(labelPosition2) ? 131072 : 65536;
                $dirty |= i14;
            } else {
                labelPosition2 = labelPosition;
            }
            $dirty |= i14;
        } else {
            labelPosition2 = labelPosition;
        }
        int i15 = i & 64;
        if (i15 != 0) {
            $dirty |= 1572864;
        } else if (($changed & 1572864) == 0) {
            $dirty |= $composer3.changedInstance(function3) ? 1048576 : 524288;
        }
        int i16 = i & 128;
        if (i16 != 0) {
            $dirty |= 12582912;
            i2 = i16;
        } else if (($changed & 12582912) == 0) {
            i2 = i16;
            $dirty |= $composer3.changedInstance(function2) ? 8388608 : 4194304;
        } else {
            i2 = i16;
        }
        int i17 = i & 256;
        if (i17 != 0) {
            $dirty |= 100663296;
            i3 = i17;
        } else if (($changed & 100663296) == 0) {
            i3 = i17;
            $dirty |= $composer3.changedInstance(function22) ? 67108864 : GroupFlagsKt.HasAuxSlotFlag;
        } else {
            i3 = i17;
        }
        int i18 = i & 512;
        if (i18 != 0) {
            $dirty |= 805306368;
            i4 = i18;
        } else if (($changed & 805306368) == 0) {
            i4 = i18;
            $dirty |= $composer3.changedInstance(function23) ? GroupFlagsKt.HasMovableContentFlag : GroupFlagsKt.IsMovableContentFlag;
        } else {
            i4 = i18;
        }
        int i19 = i & 1024;
        if (i19 != 0) {
            $dirty1 |= 6;
            i5 = i19;
        } else if (($changed1 & 6) == 0) {
            i5 = i19;
            $dirty1 |= $composer3.changedInstance(function24) ? 4 : 2;
        } else {
            i5 = i19;
        }
        int i20 = i & 2048;
        if (i20 != 0) {
            $dirty1 |= 48;
            i6 = i20;
        } else if (($changed1 & 48) == 0) {
            i6 = i20;
            $dirty1 |= $composer3.changedInstance(function25) ? 32 : 16;
        } else {
            i6 = i20;
        }
        int i21 = i & 4096;
        if (i21 != 0) {
            $dirty1 |= 384;
            i7 = i21;
        } else {
            i7 = i21;
            if (($changed1 & 384) == 0) {
                $dirty1 |= $composer3.changedInstance(function26) ? 256 : 128;
            }
        }
        int i22 = i & 8192;
        if (i22 != 0) {
            $dirty1 |= 3072;
            i8 = i22;
        } else {
            i8 = i22;
            if (($changed1 & 3072) == 0) {
                $dirty1 |= $composer3.changed(isError) ? 2048 : 1024;
            }
        }
        int i23 = i & 16384;
        if (i23 != 0) {
            $dirty1 |= 24576;
            i9 = i23;
        } else {
            i9 = i23;
            if (($changed1 & 24576) == 0) {
                $dirty1 |= $composer3.changed(inputTransformation) ? 16384 : 8192;
            }
        }
        int i24 = i & 32768;
        if (i24 != 0) {
            $dirty1 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if (($changed1 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty1 |= $composer3.changed(outputTransformation) ? 131072 : 65536;
        }
        int i25 = i & 65536;
        if (i25 != 0) {
            $dirty1 |= 1572864;
        } else if (($changed1 & 1572864) == 0) {
            $dirty1 |= $composer3.changed(keyboardOptions) ? 1048576 : 524288;
        }
        int i26 = i & 131072;
        if (i26 != 0) {
            $dirty1 |= 12582912;
        } else if (($changed1 & 12582912) == 0) {
            $dirty1 |= $composer3.changed(onKeyboardAction) ? 8388608 : 4194304;
        }
        if (($changed1 & 100663296) == 0) {
            $dirty1 |= ((i & 262144) == 0 && $composer3.changed(lineLimits)) ? 67108864 : GroupFlagsKt.HasAuxSlotFlag;
        }
        int i27 = i & 524288;
        if (i27 != 0) {
            $dirty1 |= 805306368;
        } else if (($changed1 & 805306368) == 0) {
            $dirty1 |= $composer3.changedInstance(function27) ? GroupFlagsKt.HasMovableContentFlag : GroupFlagsKt.IsMovableContentFlag;
        }
        if (($changed2 & 6) == 0) {
            $dirty2 |= ((i & 1048576) == 0 && $composer3.changed(scrollState)) ? 4 : 2;
        }
        if (($changed2 & 48) == 0) {
            $dirty2 |= ((i & 2097152) == 0 && $composer3.changed(shape)) ? 32 : 16;
        }
        if (($changed2 & 384) == 0) {
            $dirty2 |= ((i & 4194304) == 0 && $composer3.changed(colors)) ? 256 : 128;
        }
        if (($changed2 & 3072) == 0) {
            $dirty2 |= ((i & 8388608) == 0 && $composer3.changed(contentPadding)) ? 2048 : 1024;
        }
        int i28 = i & 16777216;
        if (i28 != 0) {
            $dirty2 |= 24576;
        } else if (($changed2 & 24576) == 0) {
            $dirty2 |= $composer3.changed(interactionSource) ? 16384 : 8192;
        }
        if ($composer3.shouldExecute((($dirty & 306783379) == 306783378 && (306783379 & $dirty1) == 306783378 && ($dirty2 & 9363) == 9362) ? false : true, $dirty & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "207@12265L7,223@13155L21,224@13223L5,225@13286L8");
            if (($changed & 1) != 0 && !$composer3.getDefaultsInvalid()) {
                $composer3.skipToGroupEnd();
                if ((i & 16) != 0) {
                    $dirty &= -57345;
                }
                if ((i & 32) != 0) {
                    $dirty &= -458753;
                }
                if ((262144 & i) != 0) {
                    $dirty1 &= -234881025;
                }
                if ((i & 1048576) != 0) {
                    $dirty2 &= -15;
                }
                if ((2097152 & i) != 0) {
                    $dirty2 &= -113;
                }
                if ((i & 4194304) != 0) {
                    $dirty2 &= -897;
                }
                if ((i & 8388608) != 0) {
                    function225 = function2;
                    function226 = function22;
                    function229 = function23;
                    function227 = function24;
                    function228 = function25;
                    function223 = function26;
                    isError4 = isError;
                    inputTransformation4 = inputTransformation;
                    outputTransformation4 = outputTransformation;
                    keyboardOptions4 = keyboardOptions;
                    onKeyboardAction4 = onKeyboardAction;
                    lineLimits2 = lineLimits;
                    function224 = function27;
                    scrollState4 = scrollState;
                    shape5 = shape;
                    colors4 = colors;
                    contentPadding4 = contentPadding;
                    interactionSource3 = interactionSource;
                    $dirty2 &= -7169;
                    readOnly4 = readOnly2;
                    textStyle4 = textStyle2;
                    modifier4 = modifier2;
                    labelPosition4 = labelPosition2;
                    function35 = function3;
                } else {
                    function225 = function2;
                    function226 = function22;
                    function229 = function23;
                    function227 = function24;
                    function228 = function25;
                    function223 = function26;
                    isError4 = isError;
                    inputTransformation4 = inputTransformation;
                    outputTransformation4 = outputTransformation;
                    keyboardOptions4 = keyboardOptions;
                    onKeyboardAction4 = onKeyboardAction;
                    lineLimits2 = lineLimits;
                    function224 = function27;
                    scrollState4 = scrollState;
                    shape5 = shape;
                    colors4 = colors;
                    contentPadding4 = contentPadding;
                    interactionSource3 = interactionSource;
                    readOnly4 = readOnly2;
                    textStyle4 = textStyle2;
                    modifier4 = modifier2;
                    labelPosition4 = labelPosition2;
                    function35 = function3;
                }
            } else {
                if (i11 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i12 != 0) {
                    enabled2 = true;
                }
                if (i13 != 0) {
                    readOnly2 = false;
                }
                if ((i & 16) != 0) {
                    ProvidableCompositionLocal<TextStyle> localTextStyle = TextKt.getLocalTextStyle();
                    ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                    Object objConsume = $composer3.consume(localTextStyle);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $dirty &= -57345;
                    textStyle2 = (TextStyle) objConsume;
                }
                if ((i & 32) != 0) {
                    $dirty &= -458753;
                    labelPosition2 = new TextFieldLabelPosition.Attached(false, null, null, 7, null);
                }
                if (i15 == 0) {
                    function33 = function3;
                } else {
                    function33 = null;
                }
                if (i2 == 0) {
                    function215 = function2;
                } else {
                    function215 = null;
                }
                if (i3 == 0) {
                    function216 = function22;
                } else {
                    function216 = null;
                }
                if (i4 == 0) {
                    function217 = function23;
                } else {
                    function217 = null;
                }
                if (i5 == 0) {
                    function218 = function24;
                } else {
                    function218 = null;
                }
                if (i6 == 0) {
                    function219 = function25;
                } else {
                    function219 = null;
                }
                if (i7 == 0) {
                    function220 = function26;
                } else {
                    function220 = null;
                }
                if (i8 == 0) {
                    isError3 = isError;
                } else {
                    isError3 = false;
                }
                if (i9 == 0) {
                    inputTransformation3 = inputTransformation;
                } else {
                    inputTransformation3 = null;
                }
                if (i24 == 0) {
                    outputTransformation3 = outputTransformation;
                } else {
                    outputTransformation3 = null;
                }
                if (i25 == 0) {
                    keyboardOptions3 = keyboardOptions;
                } else {
                    keyboardOptions3 = KeyboardOptions.INSTANCE.getDefault();
                }
                if (i26 == 0) {
                    onKeyboardAction3 = onKeyboardAction;
                } else {
                    onKeyboardAction3 = null;
                }
                if ((i & 262144) == 0) {
                    lineLimits3 = lineLimits;
                } else {
                    lineLimits3 = TextFieldLineLimits.INSTANCE.getDefault();
                    $dirty1 &= -234881025;
                }
                if (i27 == 0) {
                    function221 = function27;
                } else {
                    function221 = null;
                }
                if ((i & 1048576) == 0) {
                    function34 = function33;
                    function222 = function215;
                    scrollState3 = scrollState;
                } else {
                    function34 = function33;
                    function222 = function215;
                    scrollState3 = ScrollKt.rememberScrollState(0, $composer3, 0, 1);
                    $dirty2 &= -15;
                }
                if ((2097152 & i) == 0) {
                    shape3 = shape;
                } else {
                    shape3 = OutlinedTextFieldDefaults.INSTANCE.getShape($composer3, 6);
                    $dirty2 &= -113;
                }
                if ((i & 4194304) == 0) {
                    shape4 = shape3;
                    colors3 = colors;
                } else {
                    shape4 = shape3;
                    colors3 = OutlinedTextFieldDefaults.INSTANCE.colors($composer3, 6);
                    $dirty2 &= -897;
                }
                if ((i & 8388608) == 0) {
                    contentPadding3 = contentPadding;
                } else {
                    contentPadding3 = OutlinedTextFieldDefaults.m2790contentPaddinga9UjIt4$default(OutlinedTextFieldDefaults.INSTANCE, 0.0f, 0.0f, 0.0f, 0.0f, 15, null);
                    $dirty2 &= -7169;
                }
                if (i28 == 0) {
                    shape5 = shape4;
                    contentPadding4 = contentPadding3;
                    readOnly4 = readOnly2;
                    function223 = function220;
                    inputTransformation4 = inputTransformation3;
                    outputTransformation4 = outputTransformation3;
                    keyboardOptions4 = keyboardOptions3;
                    onKeyboardAction4 = onKeyboardAction3;
                    function224 = function221;
                    scrollState4 = scrollState3;
                    function225 = function222;
                    colors4 = colors3;
                    function226 = function216;
                    textStyle4 = textStyle2;
                    modifier4 = modifier2;
                    function227 = function218;
                    function228 = function219;
                    isError4 = isError3;
                    lineLimits2 = lineLimits3;
                    interactionSource3 = interactionSource;
                    function229 = function217;
                    labelPosition4 = labelPosition2;
                    function35 = function34;
                } else {
                    shape5 = shape4;
                    contentPadding4 = contentPadding3;
                    readOnly4 = readOnly2;
                    function223 = function220;
                    inputTransformation4 = inputTransformation3;
                    outputTransformation4 = outputTransformation3;
                    keyboardOptions4 = keyboardOptions3;
                    onKeyboardAction4 = onKeyboardAction3;
                    function224 = function221;
                    scrollState4 = scrollState3;
                    function225 = function222;
                    colors4 = colors3;
                    function226 = function216;
                    textStyle4 = textStyle2;
                    modifier4 = modifier2;
                    function227 = function218;
                    interactionSource3 = null;
                    function228 = function219;
                    isError4 = isError3;
                    lineLimits2 = lineLimits3;
                    function229 = function217;
                    labelPosition4 = labelPosition2;
                    function35 = function34;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2007078942, $dirty, $dirty1, "androidx.compose.material3.OutlinedTextField (OutlinedTextField.kt:228)");
            }
            if (interactionSource3 == null) {
                $composer3.startReplaceGroup(1647415065);
                ComposerKt.sourceInformation($composer3, "230@13518L39");
                ComposerKt.sourceInformationMarkerStart($composer3, -362499575, "CC(remember):OutlinedTextField.kt#9igjgp");
                interactionSource4 = interactionSource3;
                Object it$iv = $composer3.rememberedValue();
                if (it$iv == Composer.INSTANCE.getEmpty()) {
                    Object value$iv = InteractionSourceKt.MutableInteractionSource();
                    $composer3.updateRememberedValue(value$iv);
                    it$iv = value$iv;
                }
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endReplaceGroup();
                interactionSource5 = (MutableInteractionSource) it$iv;
            } else {
                interactionSource4 = interactionSource3;
                $composer3.startReplaceGroup(-362500226);
                $composer3.endReplaceGroup();
                interactionSource5 = interactionSource4;
            }
            $composer3.startReplaceGroup(-362494116);
            ComposerKt.sourceInformation($composer3, "*234@13742L25");
            long $this$takeOrElse_u2dDxMtmZc$iv = textStyle4.m7603getColor0d7_KjU();
            if (($this$takeOrElse_u2dDxMtmZc$iv != 16 ? 1 : 0) == 0) {
                boolean focused = FocusInteractionKt.collectIsFocusedAsState(interactionSource5, $composer3, 0).getValue().booleanValue();
                $this$takeOrElse_u2dDxMtmZc$iv = colors4.m3124textColorXeAY9LY$material3(enabled2, isError4, focused);
            }
            $composer3.endReplaceGroup();
            long textColor = $this$takeOrElse_u2dDxMtmZc$iv;
            TextStyle mergedTextStyle = textStyle4.merge(new TextStyle(textColor, 0L, (FontWeight) null, (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, 0L, (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16777214, (DefaultConstructorMarker) null));
            TextFieldColors colors5 = colors4;
            boolean enabled4 = enabled2;
            CompositionLocalKt.CompositionLocalProvider(TextSelectionColorsKt.getLocalTextSelectionColors().provides(colors4.getTextSelectionColors()), ComposableLambdaKt.rememberComposableLambda(-416142558, true, new AnonymousClass1(modifier4, function35, labelPosition4, isError4, colors5, textFieldState, enabled4, lineLimits2, outputTransformation4, interactionSource5, function225, function226, function229, function227, function228, function223, contentPadding4, readOnly4, inputTransformation4, mergedTextStyle, keyboardOptions4, onKeyboardAction4, function224, scrollState4, shape5), $composer3, 54), $composer3, ProvidedValue.$stable | 48);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier4;
            labelPosition3 = labelPosition4;
            isError2 = isError4;
            enabled3 = enabled4;
            function28 = function225;
            function211 = function226;
            function212 = function229;
            function29 = function227;
            function213 = function228;
            contentPadding2 = contentPadding4;
            inputTransformation2 = inputTransformation4;
            keyboardOptions2 = keyboardOptions4;
            onKeyboardAction2 = onKeyboardAction4;
            scrollState2 = scrollState4;
            shape2 = shape5;
            interactionSource2 = interactionSource4;
            function32 = function35;
            colors2 = colors5;
            outputTransformation2 = outputTransformation4;
            function214 = function223;
            function210 = function224;
            textStyle3 = textStyle4;
            $composer2 = $composer3;
            readOnly3 = readOnly4;
        } else {
            $composer3.skipToGroupEnd();
            function28 = function2;
            function29 = function24;
            inputTransformation2 = inputTransformation;
            outputTransformation2 = outputTransformation;
            keyboardOptions2 = keyboardOptions;
            onKeyboardAction2 = onKeyboardAction;
            lineLimits2 = lineLimits;
            function210 = function27;
            scrollState2 = scrollState;
            shape2 = shape;
            colors2 = colors;
            contentPadding2 = contentPadding;
            interactionSource2 = interactionSource;
            $composer2 = $composer3;
            enabled3 = enabled2;
            readOnly3 = readOnly2;
            textStyle3 = textStyle2;
            labelPosition3 = labelPosition2;
            modifier3 = modifier2;
            function32 = function3;
            function211 = function22;
            function212 = function23;
            function213 = function25;
            function214 = function26;
            isError2 = isError;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.OutlinedTextFieldKt$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return OutlinedTextFieldKt.OutlinedTextField$lambda$2(state, modifier3, enabled3, readOnly3, textStyle3, labelPosition3, function32, function28, function211, function212, function29, function213, function214, isError2, inputTransformation2, outputTransformation2, keyboardOptions2, onKeyboardAction2, lineLimits2, function210, scrollState2, shape2, colors2, contentPadding2, interactionSource2, $changed, $changed1, $changed2, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.OutlinedTextFieldKt$OutlinedTextField$1 */
    /* JADX INFO: compiled from: OutlinedTextField.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    static final class AnonymousClass1 implements Function2<Composer, Integer, Unit> {
        final /* synthetic */ TextFieldColors $colors;
        final /* synthetic */ PaddingValues $contentPadding;
        final /* synthetic */ boolean $enabled;
        final /* synthetic */ InputTransformation $inputTransformation;
        final /* synthetic */ MutableInteractionSource $interactionSource;
        final /* synthetic */ boolean $isError;
        final /* synthetic */ KeyboardOptions $keyboardOptions;
        final /* synthetic */ Function3<TextFieldLabelScope, Composer, Integer, Unit> $label;
        final /* synthetic */ TextFieldLabelPosition $labelPosition;
        final /* synthetic */ Function2<Composer, Integer, Unit> $leadingIcon;
        final /* synthetic */ TextFieldLineLimits $lineLimits;
        final /* synthetic */ TextStyle $mergedTextStyle;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ KeyboardActionHandler $onKeyboardAction;
        final /* synthetic */ Function2<Density, Function0<TextLayoutResult>, Unit> $onTextLayout;
        final /* synthetic */ OutputTransformation $outputTransformation;
        final /* synthetic */ Function2<Composer, Integer, Unit> $placeholder;
        final /* synthetic */ Function2<Composer, Integer, Unit> $prefix;
        final /* synthetic */ boolean $readOnly;
        final /* synthetic */ ScrollState $scrollState;
        final /* synthetic */ Shape $shape;
        final /* synthetic */ TextFieldState $state;
        final /* synthetic */ Function2<Composer, Integer, Unit> $suffix;
        final /* synthetic */ Function2<Composer, Integer, Unit> $supportingText;
        final /* synthetic */ Function2<Composer, Integer, Unit> $trailingIcon;

        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass1(Modifier modifier, Function3<? super TextFieldLabelScope, ? super Composer, ? super Integer, Unit> function3, TextFieldLabelPosition textFieldLabelPosition, boolean z, TextFieldColors textFieldColors, TextFieldState textFieldState, boolean z2, TextFieldLineLimits textFieldLineLimits, OutputTransformation outputTransformation, MutableInteractionSource mutableInteractionSource, Function2<? super Composer, ? super Integer, Unit> function2, Function2<? super Composer, ? super Integer, Unit> function22, Function2<? super Composer, ? super Integer, Unit> function23, Function2<? super Composer, ? super Integer, Unit> function24, Function2<? super Composer, ? super Integer, Unit> function25, Function2<? super Composer, ? super Integer, Unit> function26, PaddingValues paddingValues, boolean z3, InputTransformation inputTransformation, TextStyle textStyle, KeyboardOptions keyboardOptions, KeyboardActionHandler keyboardActionHandler, Function2<? super Density, ? super Function0<TextLayoutResult>, Unit> function27, ScrollState scrollState, Shape shape) {
            this.$modifier = modifier;
            this.$label = function3;
            this.$labelPosition = textFieldLabelPosition;
            this.$isError = z;
            this.$colors = textFieldColors;
            this.$state = textFieldState;
            this.$enabled = z2;
            this.$lineLimits = textFieldLineLimits;
            this.$outputTransformation = outputTransformation;
            this.$interactionSource = mutableInteractionSource;
            this.$placeholder = function2;
            this.$leadingIcon = function22;
            this.$trailingIcon = function23;
            this.$prefix = function24;
            this.$suffix = function25;
            this.$supportingText = function26;
            this.$contentPadding = paddingValues;
            this.$readOnly = z3;
            this.$inputTransformation = inputTransformation;
            this.$mergedTextStyle = textStyle;
            this.$keyboardOptions = keyboardOptions;
            this.$onKeyboardAction = keyboardActionHandler;
            this.$onTextLayout = function27;
            this.$scrollState = scrollState;
            this.$shape = shape;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer $composer, int $changed) {
            Modifier.Companion companionM1052paddingqDBjuR0$default;
            ComposerKt.sourceInformation($composer, "C255@14761L38,290@16390L359,273@15619L1149,240@14014L2765:OutlinedTextField.kt#uh7d8r");
            if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-416142558, $changed, -1, "androidx.compose.material3.OutlinedTextField.<anonymous> (OutlinedTextField.kt:240)");
            }
            Modifier modifier = this.$modifier;
            if (this.$label != null && !(this.$labelPosition instanceof TextFieldLabelPosition.Above)) {
                $composer.startReplaceGroup(-2027097767);
                ComposerKt.sourceInformation($composer, "249@14513L2,250@14563L26");
                Modifier.Companion companion = Modifier.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer, -481023420, "CC(remember):OutlinedTextField.kt#9igjgp");
                Object it$iv = $composer.rememberedValue();
                if (it$iv == Composer.INSTANCE.getEmpty()) {
                    Object value$iv = new Function1() { // from class: androidx.compose.material3.OutlinedTextFieldKt$OutlinedTextField$1$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return Unit.INSTANCE;
                        }
                    };
                    $composer.updateRememberedValue(value$iv);
                    it$iv = value$iv;
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                companionM1052paddingqDBjuR0$default = PaddingKt.m1052paddingqDBjuR0$default(SemanticsModifierKt.semantics(companion, true, (Function1) it$iv), 0.0f, TextFieldImplKt.minimizedLabelHalfHeight($composer, 0), 0.0f, 0.0f, 13, null);
                $composer.endReplaceGroup();
            } else {
                $composer.startReplaceGroup(-2026714080);
                $composer.endReplaceGroup();
                companionM1052paddingqDBjuR0$default = Modifier.INSTANCE;
            }
            Modifier modifierThen = modifier.then(companionM1052paddingqDBjuR0$default);
            boolean z = this.$isError;
            Strings.Companion companion2 = Strings.INSTANCE;
            BasicTextFieldKt.BasicTextField(this.$state, SizeKt.m1099defaultMinSizeVpY3zN4(TextFieldImplKt.defaultErrorSemantics(modifierThen, z, Strings_androidKt.m3533getString2EP1pXo(Strings.m3454constructorimpl(androidx.compose.ui.R.string.default_error_message), $composer, 0)), OutlinedTextFieldDefaults.INSTANCE.m2797getMinWidthD9Ej5fM(), OutlinedTextFieldDefaults.INSTANCE.m2796getMinHeightD9Ej5fM()), this.$enabled, this.$readOnly, this.$inputTransformation, this.$mergedTextStyle, this.$keyboardOptions, this.$onKeyboardAction, this.$lineLimits, this.$onTextLayout, this.$interactionSource, new SolidColor(this.$colors.m3074cursorColorvNxB06k$material3(this.$isError), null), this.$outputTransformation, OutlinedTextFieldDefaults.INSTANCE.decorator(this.$state, this.$enabled, this.$lineLimits, this.$outputTransformation, this.$interactionSource, this.$labelPosition, this.$label, this.$placeholder, this.$leadingIcon, this.$trailingIcon, this.$prefix, this.$suffix, this.$supportingText, this.$isError, this.$colors, this.$contentPadding, ComposableLambdaKt.rememberComposableLambda(-98391231, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldKt.OutlinedTextField.1.2
                final /* synthetic */ TextFieldColors $colors;
                final /* synthetic */ boolean $enabled;
                final /* synthetic */ MutableInteractionSource $interactionSource;
                final /* synthetic */ boolean $isError;
                final /* synthetic */ Shape $shape;

                AnonymousClass2(boolean z2, boolean z3, MutableInteractionSource mutableInteractionSource, TextFieldColors textFieldColors, Shape shape) {
                    z = z2;
                    z = z3;
                    mutableInteractionSource = mutableInteractionSource;
                    textFieldColors = textFieldColors;
                    shape = shape;
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer $composer2, int $changed2) {
                    ComposerKt.sourceInformation($composer2, "C291@16442L285:OutlinedTextField.kt#uh7d8r");
                    if (!$composer2.shouldExecute(($changed2 & 3) != 2, $changed2 & 1)) {
                        $composer2.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-98391231, $changed2, -1, "androidx.compose.material3.OutlinedTextField.<anonymous>.<anonymous> (OutlinedTextField.kt:291)");
                    }
                    OutlinedTextFieldDefaults.INSTANCE.m2791Container4EFweAY(z, z, mutableInteractionSource, null, textFieldColors, shape, 0.0f, 0.0f, $composer2, 100663296, 200);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, $composer, 54), $composer, 0, 14155776, 0), this.$scrollState, $composer, 0, 0, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }

        /* JADX INFO: renamed from: androidx.compose.material3.OutlinedTextFieldKt$OutlinedTextField$1$2 */
        /* JADX INFO: compiled from: OutlinedTextField.kt */
        @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
        static final class AnonymousClass2 implements Function2<Composer, Integer, Unit> {
            final /* synthetic */ TextFieldColors $colors;
            final /* synthetic */ boolean $enabled;
            final /* synthetic */ MutableInteractionSource $interactionSource;
            final /* synthetic */ boolean $isError;
            final /* synthetic */ Shape $shape;

            AnonymousClass2(boolean z2, boolean z3, MutableInteractionSource mutableInteractionSource, TextFieldColors textFieldColors, Shape shape) {
                z = z2;
                z = z3;
                mutableInteractionSource = mutableInteractionSource;
                textFieldColors = textFieldColors;
                shape = shape;
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                invoke(composer, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Composer $composer2, int $changed2) {
                ComposerKt.sourceInformation($composer2, "C291@16442L285:OutlinedTextField.kt#uh7d8r");
                if (!$composer2.shouldExecute(($changed2 & 3) != 2, $changed2 & 1)) {
                    $composer2.skipToGroupEnd();
                    return;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-98391231, $changed2, -1, "androidx.compose.material3.OutlinedTextField.<anonymous>.<anonymous> (OutlinedTextField.kt:291)");
                }
                OutlinedTextFieldDefaults.INSTANCE.m2791Container4EFweAY(z, z, mutableInteractionSource, null, textFieldColors, shape, 0.0f, 0.0f, $composer2, 100663296, 200);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
        }
    }

    public static final void OutlinedTextField(final String value, final Function1<? super String, Unit> function1, Modifier modifier, boolean enabled, boolean readOnly, TextStyle textStyle, Function2<? super Composer, ? super Integer, Unit> function2, Function2<? super Composer, ? super Integer, Unit> function22, Function2<? super Composer, ? super Integer, Unit> function23, Function2<? super Composer, ? super Integer, Unit> function24, Function2<? super Composer, ? super Integer, Unit> function25, Function2<? super Composer, ? super Integer, Unit> function26, Function2<? super Composer, ? super Integer, Unit> function27, boolean isError, VisualTransformation visualTransformation, KeyboardOptions keyboardOptions, KeyboardActions keyboardActions, boolean singleLine, int maxLines, int minLines, MutableInteractionSource interactionSource, Shape shape, TextFieldColors colors, Composer $composer, final int $changed, final int $changed1, final int $changed2, final int i) {
        String str;
        Modifier modifier2;
        boolean enabled2;
        boolean readOnly2;
        TextStyle textStyle2;
        Function2<? super Composer, ? super Integer, Unit> function28;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        final Function2<? super Composer, ? super Integer, Unit> function29;
        Function2<? super Composer, ? super Integer, Unit> function210;
        final Function2<? super Composer, ? super Integer, Unit> function211;
        final boolean isError2;
        final KeyboardOptions keyboardOptions2;
        final KeyboardActions keyboardActions2;
        final boolean enabled3;
        final int maxLines2;
        final int minLines2;
        final MutableInteractionSource interactionSource2;
        final Shape shape2;
        final TextFieldColors colors2;
        Composer $composer2;
        final boolean enabled4;
        final boolean readOnly3;
        final TextStyle textStyle3;
        final Function2<? super Composer, ? super Integer, Unit> function212;
        final Function2<? super Composer, ? super Integer, Unit> function213;
        final Function2<? super Composer, ? super Integer, Unit> function214;
        final Function2<? super Composer, ? super Integer, Unit> function215;
        final VisualTransformation visualTransformation2;
        Function2<? super Composer, ? super Integer, Unit> function216;
        Function2<? super Composer, ? super Integer, Unit> function217;
        Function2<? super Composer, ? super Integer, Unit> function218;
        Function2<? super Composer, ? super Integer, Unit> function219;
        Function2<? super Composer, ? super Integer, Unit> function220;
        Function2<? super Composer, ? super Integer, Unit> function221;
        boolean isError3;
        VisualTransformation visualTransformation3;
        KeyboardOptions keyboardOptions3;
        KeyboardActions keyboardActions3;
        boolean singleLine2;
        int maxLines3;
        int minLines3;
        MutableInteractionSource interactionSource3;
        Function2<? super Composer, ? super Integer, Unit> function222;
        Shape shape3;
        Function2<? super Composer, ? super Integer, Unit> function223;
        Shape shape4;
        Function2<? super Composer, ? super Integer, Unit> function224;
        TextStyle textStyle4;
        Function2<? super Composer, ? super Integer, Unit> function225;
        Function2<? super Composer, ? super Integer, Unit> function226;
        Function2<? super Composer, ? super Integer, Unit> function227;
        Function2<? super Composer, ? super Integer, Unit> function228;
        VisualTransformation visualTransformation4;
        MutableInteractionSource interactionSource4;
        TextFieldColors colors3;
        int minLines4;
        int minLines5;
        boolean singleLine3;
        KeyboardActions keyboardActions4;
        KeyboardOptions keyboardOptions4;
        boolean readOnly4;
        Modifier modifier3;
        MutableInteractionSource interactionSource5;
        MutableInteractionSource interactionSource6;
        int i10;
        Composer $composer3 = $composer.startRestartGroup(1901501544);
        ComposerKt.sourceInformation($composer3, "C(OutlinedTextField)N(value,onValueChange,modifier,enabled,readOnly,textStyle,label,placeholder,leadingIcon,trailingIcon,prefix,suffix,supportingText,isError,visualTransformation,keyboardOptions,keyboardActions,singleLine,maxLines,minLines,interactionSource,shape,colors)404@23117L2830,404@23030L2917:OutlinedTextField.kt#uh7d8r");
        int $dirty = $changed;
        int $dirty1 = $changed1;
        int $dirty2 = $changed2;
        if ((i & 1) != 0) {
            $dirty |= 6;
            str = value;
        } else if (($changed & 6) == 0) {
            str = value;
            $dirty |= $composer3.changed(str) ? 4 : 2;
        } else {
            str = value;
        }
        if ((i & 2) != 0) {
            $dirty |= 48;
        } else if (($changed & 48) == 0) {
            $dirty |= $composer3.changedInstance(function1) ? 32 : 16;
        }
        int i11 = i & 4;
        if (i11 != 0) {
            $dirty |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty |= $composer3.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i12 = i & 8;
        if (i12 != 0) {
            $dirty |= 3072;
            enabled2 = enabled;
        } else if (($changed & 3072) == 0) {
            enabled2 = enabled;
            $dirty |= $composer3.changed(enabled2) ? 2048 : 1024;
        } else {
            enabled2 = enabled;
        }
        int i13 = i & 16;
        if (i13 != 0) {
            $dirty |= 24576;
            readOnly2 = readOnly;
        } else if (($changed & 24576) == 0) {
            readOnly2 = readOnly;
            $dirty |= $composer3.changed(readOnly2) ? 16384 : 8192;
        } else {
            readOnly2 = readOnly;
        }
        if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            if ((i & 32) == 0) {
                textStyle2 = textStyle;
                if ($composer3.changed(textStyle2)) {
                    i10 = 131072;
                }
                $dirty |= i10;
            } else {
                textStyle2 = textStyle;
            }
            i10 = 65536;
            $dirty |= i10;
        } else {
            textStyle2 = textStyle;
        }
        int i14 = i & 64;
        if (i14 != 0) {
            $dirty |= 1572864;
            function28 = function2;
        } else if (($changed & 1572864) == 0) {
            function28 = function2;
            $dirty |= $composer3.changedInstance(function28) ? 1048576 : 524288;
        } else {
            function28 = function2;
        }
        int i15 = i & 128;
        if (i15 != 0) {
            $dirty |= 12582912;
            i2 = i15;
        } else if (($changed & 12582912) == 0) {
            i2 = i15;
            $dirty |= $composer3.changedInstance(function22) ? 8388608 : 4194304;
        } else {
            i2 = i15;
        }
        int i16 = i & 256;
        if (i16 != 0) {
            $dirty |= 100663296;
            i3 = i16;
        } else if (($changed & 100663296) == 0) {
            i3 = i16;
            $dirty |= $composer3.changedInstance(function23) ? 67108864 : GroupFlagsKt.HasAuxSlotFlag;
        } else {
            i3 = i16;
        }
        int i17 = i & 512;
        if (i17 != 0) {
            $dirty |= 805306368;
            i4 = i17;
        } else if (($changed & 805306368) == 0) {
            i4 = i17;
            $dirty |= $composer3.changedInstance(function24) ? GroupFlagsKt.HasMovableContentFlag : GroupFlagsKt.IsMovableContentFlag;
        } else {
            i4 = i17;
        }
        int i18 = i & 1024;
        if (i18 != 0) {
            $dirty1 |= 6;
            i5 = i18;
        } else if (($changed1 & 6) == 0) {
            i5 = i18;
            $dirty1 |= $composer3.changedInstance(function25) ? 4 : 2;
        } else {
            i5 = i18;
        }
        int i19 = i & 2048;
        if (i19 != 0) {
            $dirty1 |= 48;
            i6 = i19;
        } else if (($changed1 & 48) == 0) {
            i6 = i19;
            $dirty1 |= $composer3.changedInstance(function26) ? 32 : 16;
        } else {
            i6 = i19;
        }
        int i20 = i & 4096;
        if (i20 != 0) {
            $dirty1 |= 384;
            i7 = i20;
        } else {
            i7 = i20;
            if (($changed1 & 384) == 0) {
                $dirty1 |= $composer3.changedInstance(function27) ? 256 : 128;
            }
        }
        int i21 = i & 8192;
        if (i21 != 0) {
            $dirty1 |= 3072;
            i8 = i21;
        } else {
            i8 = i21;
            if (($changed1 & 3072) == 0) {
                $dirty1 |= $composer3.changed(isError) ? 2048 : 1024;
            }
        }
        int i22 = i & 16384;
        if (i22 != 0) {
            $dirty1 |= 24576;
            i9 = i22;
        } else {
            i9 = i22;
            if (($changed1 & 24576) == 0) {
                $dirty1 |= $composer3.changed(visualTransformation) ? 16384 : 8192;
            }
        }
        int i23 = i & 32768;
        if (i23 != 0) {
            $dirty1 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if (($changed1 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty1 |= $composer3.changed(keyboardOptions) ? 131072 : 65536;
        }
        int i24 = i & 65536;
        if (i24 != 0) {
            $dirty1 |= 1572864;
        } else if (($changed1 & 1572864) == 0) {
            $dirty1 |= $composer3.changed(keyboardActions) ? 1048576 : 524288;
        }
        int i25 = i & 131072;
        if (i25 != 0) {
            $dirty1 |= 12582912;
        } else if (($changed1 & 12582912) == 0) {
            $dirty1 |= $composer3.changed(singleLine) ? 8388608 : 4194304;
        }
        if (($changed1 & 100663296) == 0) {
            $dirty1 |= ((i & 262144) == 0 && $composer3.changed(maxLines)) ? 67108864 : GroupFlagsKt.HasAuxSlotFlag;
        }
        int i26 = i & 524288;
        if (i26 != 0) {
            $dirty1 |= 805306368;
        } else if (($changed1 & 805306368) == 0) {
            $dirty1 |= $composer3.changed(minLines) ? GroupFlagsKt.HasMovableContentFlag : GroupFlagsKt.IsMovableContentFlag;
        }
        int i27 = i & 1048576;
        if (i27 != 0) {
            $dirty2 |= 6;
        } else if (($changed2 & 6) == 0) {
            $dirty2 |= $composer3.changed(interactionSource) ? 4 : 2;
        }
        if (($changed2 & 48) == 0) {
            $dirty2 |= ((i & 2097152) == 0 && $composer3.changed(shape)) ? 32 : 16;
        }
        if (($changed2 & 384) == 0) {
            $dirty2 |= ((i & 4194304) == 0 && $composer3.changed(colors)) ? 256 : 128;
        }
        if ($composer3.shouldExecute((($dirty & 306783379) == 306783378 && (306783379 & $dirty1) == 306783378 && ($dirty2 & 147) == 146) ? false : true, $dirty & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "375@21669L7,391@22473L5,392@22536L8");
            if (($changed & 1) != 0 && !$composer3.getDefaultsInvalid()) {
                $composer3.skipToGroupEnd();
                if ((i & 32) != 0) {
                    $dirty &= -458753;
                }
                if ((262144 & i) != 0) {
                    $dirty1 &= -234881025;
                }
                if ((2097152 & i) != 0) {
                    $dirty2 &= -113;
                }
                if ((i & 4194304) != 0) {
                    function223 = function22;
                    function210 = function23;
                    function224 = function24;
                    function225 = function25;
                    function227 = function26;
                    function228 = function27;
                    isError2 = isError;
                    visualTransformation4 = visualTransformation;
                    keyboardOptions4 = keyboardOptions;
                    keyboardActions4 = keyboardActions;
                    singleLine3 = singleLine;
                    minLines5 = maxLines;
                    minLines4 = minLines;
                    interactionSource4 = interactionSource;
                    shape4 = shape;
                    $dirty2 &= -897;
                    readOnly4 = readOnly2;
                    textStyle4 = textStyle2;
                    function226 = function28;
                    colors3 = colors;
                    modifier3 = modifier2;
                } else {
                    function223 = function22;
                    function210 = function23;
                    function224 = function24;
                    function225 = function25;
                    function227 = function26;
                    function228 = function27;
                    isError2 = isError;
                    visualTransformation4 = visualTransformation;
                    keyboardOptions4 = keyboardOptions;
                    keyboardActions4 = keyboardActions;
                    singleLine3 = singleLine;
                    minLines5 = maxLines;
                    minLines4 = minLines;
                    interactionSource4 = interactionSource;
                    shape4 = shape;
                    readOnly4 = readOnly2;
                    textStyle4 = textStyle2;
                    function226 = function28;
                    colors3 = colors;
                    modifier3 = modifier2;
                }
            } else {
                if (i11 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i12 != 0) {
                    enabled2 = true;
                }
                if (i13 != 0) {
                    readOnly2 = false;
                }
                if ((i & 32) != 0) {
                    ProvidableCompositionLocal<TextStyle> localTextStyle = TextKt.getLocalTextStyle();
                    ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                    Object objConsume = $composer3.consume(localTextStyle);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $dirty &= -458753;
                    textStyle2 = (TextStyle) objConsume;
                }
                if (i14 != 0) {
                    function28 = null;
                }
                if (i2 == 0) {
                    function216 = function22;
                } else {
                    function216 = null;
                }
                if (i3 == 0) {
                    function217 = function23;
                } else {
                    function217 = null;
                }
                if (i4 == 0) {
                    function218 = function24;
                } else {
                    function218 = null;
                }
                if (i5 == 0) {
                    function219 = function25;
                } else {
                    function219 = null;
                }
                if (i6 == 0) {
                    function220 = function26;
                } else {
                    function220 = null;
                }
                if (i7 == 0) {
                    function221 = function27;
                } else {
                    function221 = null;
                }
                if (i8 == 0) {
                    isError3 = isError;
                } else {
                    isError3 = false;
                }
                if (i9 == 0) {
                    visualTransformation3 = visualTransformation;
                } else {
                    visualTransformation3 = VisualTransformation.INSTANCE.getNone();
                }
                if (i23 == 0) {
                    keyboardOptions3 = keyboardOptions;
                } else {
                    keyboardOptions3 = KeyboardOptions.INSTANCE.getDefault();
                }
                if (i24 == 0) {
                    keyboardActions3 = keyboardActions;
                } else {
                    keyboardActions3 = KeyboardActions.INSTANCE.getDefault();
                }
                if (i25 == 0) {
                    singleLine2 = singleLine;
                } else {
                    singleLine2 = false;
                }
                if ((i & 262144) == 0) {
                    maxLines3 = maxLines;
                } else {
                    maxLines3 = singleLine2 ? 1 : Integer.MAX_VALUE;
                    $dirty1 &= -234881025;
                }
                if (i26 == 0) {
                    minLines3 = minLines;
                } else {
                    minLines3 = 1;
                }
                if (i27 == 0) {
                    interactionSource3 = interactionSource;
                } else {
                    interactionSource3 = null;
                }
                Function2<? super Composer, ? super Integer, Unit> function229 = function216;
                if ((i & 2097152) == 0) {
                    function222 = function217;
                    shape3 = shape;
                } else {
                    function222 = function217;
                    shape3 = OutlinedTextFieldDefaults.INSTANCE.getShape($composer3, 6);
                    $dirty2 &= -113;
                }
                if ((i & 4194304) == 0) {
                    function223 = function229;
                    function210 = function222;
                    shape4 = shape3;
                    function224 = function218;
                    textStyle4 = textStyle2;
                    function225 = function219;
                    function226 = function28;
                    function227 = function220;
                    function228 = function221;
                    visualTransformation4 = visualTransformation3;
                    isError2 = isError3;
                    interactionSource4 = interactionSource3;
                    colors3 = colors;
                    minLines4 = minLines3;
                    minLines5 = maxLines3;
                    singleLine3 = singleLine2;
                    keyboardActions4 = keyboardActions3;
                    keyboardOptions4 = keyboardOptions3;
                    readOnly4 = readOnly2;
                    modifier3 = modifier2;
                } else {
                    Shape shape5 = shape3;
                    int i28 = $dirty2 & (-897);
                    Function2<? super Composer, ? super Integer, Unit> function230 = function28;
                    colors3 = OutlinedTextFieldDefaults.INSTANCE.colors($composer3, 6);
                    textStyle4 = textStyle2;
                    function226 = function230;
                    function223 = function229;
                    function210 = function222;
                    shape4 = shape5;
                    $dirty2 = i28;
                    function224 = function218;
                    function225 = function219;
                    function227 = function220;
                    function228 = function221;
                    visualTransformation4 = visualTransformation3;
                    isError2 = isError3;
                    interactionSource4 = interactionSource3;
                    minLines4 = minLines3;
                    minLines5 = maxLines3;
                    singleLine3 = singleLine2;
                    keyboardActions4 = keyboardActions3;
                    keyboardOptions4 = keyboardOptions3;
                    readOnly4 = readOnly2;
                    modifier3 = modifier2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1901501544, $dirty, $dirty1, "androidx.compose.material3.OutlinedTextField (OutlinedTextField.kt:393)");
            }
            if (interactionSource4 == null) {
                $composer3.startReplaceGroup(1310051731);
                ComposerKt.sourceInformation($composer3, "395@22631L39");
                ComposerKt.sourceInformationMarkerStart($composer3, 1981922383, "CC(remember):OutlinedTextField.kt#9igjgp");
                interactionSource5 = interactionSource4;
                Object it$iv = $composer3.rememberedValue();
                if (it$iv == Composer.INSTANCE.getEmpty()) {
                    Object value$iv = InteractionSourceKt.MutableInteractionSource();
                    $composer3.updateRememberedValue(value$iv);
                    it$iv = value$iv;
                }
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endReplaceGroup();
                interactionSource6 = (MutableInteractionSource) it$iv;
            } else {
                interactionSource5 = interactionSource4;
                $composer3.startReplaceGroup(1981921732);
                $composer3.endReplaceGroup();
                interactionSource6 = interactionSource5;
            }
            $composer3.startReplaceGroup(1981927842);
            ComposerKt.sourceInformation($composer3, "*399@22855L25");
            long $this$takeOrElse_u2dDxMtmZc$iv = textStyle4.m7603getColor0d7_KjU();
            if (!($this$takeOrElse_u2dDxMtmZc$iv != 16)) {
                boolean focused = FocusInteractionKt.collectIsFocusedAsState(interactionSource6, $composer3, 0).getValue().booleanValue();
                $this$takeOrElse_u2dDxMtmZc$iv = colors3.m3124textColorXeAY9LY$material3(enabled2, isError2, focused);
            }
            $composer3.endReplaceGroup();
            long textColor = $this$takeOrElse_u2dDxMtmZc$iv;
            TextStyle mergedTextStyle = textStyle4.merge(new TextStyle(textColor, 0L, (FontWeight) null, (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, 0L, (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16777214, (DefaultConstructorMarker) null));
            boolean enabled5 = enabled2;
            CompositionLocalKt.CompositionLocalProvider(TextSelectionColorsKt.getLocalTextSelectionColors().provides(colors3.getTextSelectionColors()), ComposableLambdaKt.rememberComposableLambda(1874034984, true, new AnonymousClass3(modifier3, function226, isError2, colors3, str, function1, enabled5, readOnly4, mergedTextStyle, keyboardOptions4, keyboardActions4, singleLine3, minLines5, minLines4, visualTransformation4, interactionSource6, function223, function210, function224, function225, function227, function228, shape4), $composer3, 54), $composer3, ProvidedValue.$stable | 48);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier2 = modifier3;
            keyboardOptions2 = keyboardOptions4;
            keyboardActions2 = keyboardActions4;
            minLines2 = minLines4;
            function29 = function223;
            function213 = function224;
            function211 = function225;
            function214 = function227;
            shape2 = shape4;
            interactionSource2 = interactionSource5;
            function212 = function226;
            readOnly3 = readOnly4;
            maxLines2 = minLines5;
            function215 = function228;
            textStyle3 = textStyle4;
            $composer2 = $composer3;
            enabled4 = enabled5;
            enabled3 = singleLine3;
            colors2 = colors3;
            visualTransformation2 = visualTransformation4;
        } else {
            $composer3.skipToGroupEnd();
            function29 = function22;
            function210 = function23;
            function211 = function25;
            isError2 = isError;
            keyboardOptions2 = keyboardOptions;
            keyboardActions2 = keyboardActions;
            enabled3 = singleLine;
            maxLines2 = maxLines;
            minLines2 = minLines;
            interactionSource2 = interactionSource;
            shape2 = shape;
            colors2 = colors;
            $composer2 = $composer3;
            enabled4 = enabled2;
            readOnly3 = readOnly2;
            textStyle3 = textStyle2;
            function212 = function28;
            function213 = function24;
            function214 = function26;
            function215 = function27;
            visualTransformation2 = visualTransformation;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Modifier modifier4 = modifier2;
            final Function2<? super Composer, ? super Integer, Unit> function231 = function210;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.OutlinedTextFieldKt$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return OutlinedTextFieldKt.OutlinedTextField$lambda$5(value, function1, modifier4, enabled4, readOnly3, textStyle3, function212, function29, function231, function213, function211, function214, function215, isError2, visualTransformation2, keyboardOptions2, keyboardActions2, enabled3, maxLines2, minLines2, interactionSource2, shape2, colors2, $changed, $changed1, $changed2, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.OutlinedTextFieldKt$OutlinedTextField$3 */
    /* JADX INFO: compiled from: OutlinedTextField.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    static final class AnonymousClass3 implements Function2<Composer, Integer, Unit> {
        final /* synthetic */ TextFieldColors $colors;
        final /* synthetic */ boolean $enabled;
        final /* synthetic */ MutableInteractionSource $interactionSource;
        final /* synthetic */ boolean $isError;
        final /* synthetic */ KeyboardActions $keyboardActions;
        final /* synthetic */ KeyboardOptions $keyboardOptions;
        final /* synthetic */ Function2<Composer, Integer, Unit> $label;
        final /* synthetic */ Function2<Composer, Integer, Unit> $leadingIcon;
        final /* synthetic */ int $maxLines;
        final /* synthetic */ TextStyle $mergedTextStyle;
        final /* synthetic */ int $minLines;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ Function1<String, Unit> $onValueChange;
        final /* synthetic */ Function2<Composer, Integer, Unit> $placeholder;
        final /* synthetic */ Function2<Composer, Integer, Unit> $prefix;
        final /* synthetic */ boolean $readOnly;
        final /* synthetic */ Shape $shape;
        final /* synthetic */ boolean $singleLine;
        final /* synthetic */ Function2<Composer, Integer, Unit> $suffix;
        final /* synthetic */ Function2<Composer, Integer, Unit> $supportingText;
        final /* synthetic */ Function2<Composer, Integer, Unit> $trailingIcon;
        final /* synthetic */ String $value;
        final /* synthetic */ VisualTransformation $visualTransformation;

        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass3(Modifier modifier, Function2<? super Composer, ? super Integer, Unit> function2, boolean z, TextFieldColors textFieldColors, String str, Function1<? super String, Unit> function1, boolean z2, boolean z3, TextStyle textStyle, KeyboardOptions keyboardOptions, KeyboardActions keyboardActions, boolean z4, int i, int i2, VisualTransformation visualTransformation, MutableInteractionSource mutableInteractionSource, Function2<? super Composer, ? super Integer, Unit> function22, Function2<? super Composer, ? super Integer, Unit> function23, Function2<? super Composer, ? super Integer, Unit> function24, Function2<? super Composer, ? super Integer, Unit> function25, Function2<? super Composer, ? super Integer, Unit> function26, Function2<? super Composer, ? super Integer, Unit> function27, Shape shape) {
            this.$modifier = modifier;
            this.$label = function2;
            this.$isError = z;
            this.$colors = textFieldColors;
            this.$value = str;
            this.$onValueChange = function1;
            this.$enabled = z2;
            this.$readOnly = z3;
            this.$mergedTextStyle = textStyle;
            this.$keyboardOptions = keyboardOptions;
            this.$keyboardActions = keyboardActions;
            this.$singleLine = z4;
            this.$maxLines = i;
            this.$minLines = i2;
            this.$visualTransformation = visualTransformation;
            this.$interactionSource = mutableInteractionSource;
            this.$placeholder = function22;
            this.$leadingIcon = function23;
            this.$trailingIcon = function24;
            this.$prefix = function25;
            this.$suffix = function26;
            this.$supportingText = function27;
            this.$shape = shape;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer $composer, int $changed) {
            Modifier.Companion companionM1052paddingqDBjuR0$default;
            ComposerKt.sourceInformation($composer, "C420@23824L38,438@24644L1286,405@23127L2814:OutlinedTextField.kt#uh7d8r");
            if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1874034984, $changed, -1, "androidx.compose.material3.OutlinedTextField.<anonymous> (OutlinedTextField.kt:405)");
            }
            Modifier modifier = this.$modifier;
            if (this.$label != null) {
                $composer.startReplaceGroup(-903490605);
                ComposerKt.sourceInformation($composer, "414@23576L2,415@23626L26");
                Modifier.Companion companion = Modifier.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer, 1356337290, "CC(remember):OutlinedTextField.kt#9igjgp");
                Object it$iv = $composer.rememberedValue();
                if (it$iv == Composer.INSTANCE.getEmpty()) {
                    Object value$iv = new Function1() { // from class: androidx.compose.material3.OutlinedTextFieldKt$OutlinedTextField$3$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return Unit.INSTANCE;
                        }
                    };
                    $composer.updateRememberedValue(value$iv);
                    it$iv = value$iv;
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                companionM1052paddingqDBjuR0$default = PaddingKt.m1052paddingqDBjuR0$default(SemanticsModifierKt.semantics(companion, true, (Function1) it$iv), 0.0f, TextFieldImplKt.minimizedLabelHalfHeight($composer, 0), 0.0f, 0.0f, 13, null);
                $composer.endReplaceGroup();
            } else {
                $composer.startReplaceGroup(-903106918);
                $composer.endReplaceGroup();
                companionM1052paddingqDBjuR0$default = Modifier.INSTANCE;
            }
            Modifier modifierThen = modifier.then(companionM1052paddingqDBjuR0$default);
            boolean z = this.$isError;
            Strings.Companion companion2 = Strings.INSTANCE;
            BasicTextFieldKt.BasicTextField(this.$value, this.$onValueChange, SizeKt.m1099defaultMinSizeVpY3zN4(TextFieldImplKt.defaultErrorSemantics(modifierThen, z, Strings_androidKt.m3533getString2EP1pXo(Strings.m3454constructorimpl(androidx.compose.ui.R.string.default_error_message), $composer, 0)), OutlinedTextFieldDefaults.INSTANCE.m2797getMinWidthD9Ej5fM(), OutlinedTextFieldDefaults.INSTANCE.m2796getMinHeightD9Ej5fM()), this.$enabled, this.$readOnly, this.$mergedTextStyle, this.$keyboardOptions, this.$keyboardActions, this.$singleLine, this.$maxLines, this.$minLines, this.$visualTransformation, (Function1<? super TextLayoutResult, Unit>) null, this.$interactionSource, new SolidColor(this.$colors.m3074cursorColorvNxB06k$material3(this.$isError), null), ComposableLambdaKt.rememberComposableLambda(-1189274459, true, new Function3<Function2<? super Composer, ? super Integer, ? extends Unit>, Composer, Integer, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldKt.OutlinedTextField.3.2
                final /* synthetic */ TextFieldColors $colors;
                final /* synthetic */ boolean $enabled;
                final /* synthetic */ MutableInteractionSource $interactionSource;
                final /* synthetic */ boolean $isError;
                final /* synthetic */ Function2<Composer, Integer, Unit> $label;
                final /* synthetic */ Function2<Composer, Integer, Unit> $leadingIcon;
                final /* synthetic */ Function2<Composer, Integer, Unit> $placeholder;
                final /* synthetic */ Function2<Composer, Integer, Unit> $prefix;
                final /* synthetic */ Shape $shape;
                final /* synthetic */ boolean $singleLine;
                final /* synthetic */ Function2<Composer, Integer, Unit> $suffix;
                final /* synthetic */ Function2<Composer, Integer, Unit> $supportingText;
                final /* synthetic */ Function2<Composer, Integer, Unit> $trailingIcon;
                final /* synthetic */ String $value;
                final /* synthetic */ VisualTransformation $visualTransformation;

                /* JADX WARN: Multi-variable type inference failed */
                AnonymousClass2(String str, boolean z2, boolean z3, VisualTransformation visualTransformation, MutableInteractionSource mutableInteractionSource, boolean z4, Function2<? super Composer, ? super Integer, Unit> function2, Function2<? super Composer, ? super Integer, Unit> function22, Function2<? super Composer, ? super Integer, Unit> function23, Function2<? super Composer, ? super Integer, Unit> function24, Function2<? super Composer, ? super Integer, Unit> function25, Function2<? super Composer, ? super Integer, Unit> function26, Function2<? super Composer, ? super Integer, Unit> function27, TextFieldColors textFieldColors, Shape shape) {
                    str = str;
                    z = z2;
                    z = z3;
                    visualTransformation = visualTransformation;
                    mutableInteractionSource = mutableInteractionSource;
                    z = z4;
                    function2 = function2;
                    function2 = function22;
                    function2 = function23;
                    function2 = function24;
                    function2 = function25;
                    function2 = function26;
                    function2 = function27;
                    textFieldColors = textFieldColors;
                    shape = shape;
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(Function2<? super Composer, ? super Integer, ? extends Unit> function2, Composer composer, Integer num) {
                    invoke((Function2<? super Composer, ? super Integer, Unit>) function2, composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer2, int $changed2) {
                    ComposerKt.sourceInformation($composer2, "CN(innerTextField)455@25498L391,439@24710L1202:OutlinedTextField.kt#uh7d8r");
                    int $dirty = $changed2;
                    if (($changed2 & 6) == 0) {
                        $dirty |= $composer2.changedInstance(function2) ? 4 : 2;
                    }
                    if ($composer2.shouldExecute(($dirty & 19) != 18, $dirty & 1)) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1189274459, $dirty, -1, "androidx.compose.material3.OutlinedTextField.<anonymous>.<anonymous> (OutlinedTextField.kt:439)");
                        }
                        OutlinedTextFieldDefaults.INSTANCE.DecorationBox(str, function2, z, z, visualTransformation, mutableInteractionSource, z, function2, function2, function2, function2, function2, function2, function2, textFieldColors, null, ComposableLambdaKt.rememberComposableLambda(-656940872, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldKt.OutlinedTextField.3.2.1
                            final /* synthetic */ TextFieldColors $colors;
                            final /* synthetic */ boolean $enabled;
                            final /* synthetic */ MutableInteractionSource $interactionSource;
                            final /* synthetic */ boolean $isError;
                            final /* synthetic */ Shape $shape;

                            AnonymousClass1(boolean z2, boolean z3, MutableInteractionSource mutableInteractionSource, TextFieldColors textFieldColors, Shape shape) {
                                z = z2;
                                z = z3;
                                mutableInteractionSource = mutableInteractionSource;
                                textFieldColors = textFieldColors;
                                shape = shape;
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                                invoke(composer, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer $composer3, int $changed3) {
                                ComposerKt.sourceInformation($composer3, "C456@25554L309:OutlinedTextField.kt#uh7d8r");
                                if (!$composer3.shouldExecute(($changed3 & 3) != 2, $changed3 & 1)) {
                                    $composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-656940872, $changed3, -1, "androidx.compose.material3.OutlinedTextField.<anonymous>.<anonymous>.<anonymous> (OutlinedTextField.kt:456)");
                                }
                                OutlinedTextFieldDefaults.INSTANCE.m2791Container4EFweAY(z, z, mutableInteractionSource, null, textFieldColors, shape, 0.0f, 0.0f, $composer3, 100663296, 200);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }, $composer2, 54), $composer2, ($dirty << 3) & 112, 14155776, 32768);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer2.skipToGroupEnd();
                }

                /* JADX INFO: renamed from: androidx.compose.material3.OutlinedTextFieldKt$OutlinedTextField$3$2$1 */
                /* JADX INFO: compiled from: OutlinedTextField.kt */
                @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
                static final class AnonymousClass1 implements Function2<Composer, Integer, Unit> {
                    final /* synthetic */ TextFieldColors $colors;
                    final /* synthetic */ boolean $enabled;
                    final /* synthetic */ MutableInteractionSource $interactionSource;
                    final /* synthetic */ boolean $isError;
                    final /* synthetic */ Shape $shape;

                    AnonymousClass1(boolean z2, boolean z3, MutableInteractionSource mutableInteractionSource, TextFieldColors textFieldColors, Shape shape) {
                        z = z2;
                        z = z3;
                        mutableInteractionSource = mutableInteractionSource;
                        textFieldColors = textFieldColors;
                        shape = shape;
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                        invoke(composer, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer $composer3, int $changed3) {
                        ComposerKt.sourceInformation($composer3, "C456@25554L309:OutlinedTextField.kt#uh7d8r");
                        if (!$composer3.shouldExecute(($changed3 & 3) != 2, $changed3 & 1)) {
                            $composer3.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-656940872, $changed3, -1, "androidx.compose.material3.OutlinedTextField.<anonymous>.<anonymous>.<anonymous> (OutlinedTextField.kt:456)");
                        }
                        OutlinedTextFieldDefaults.INSTANCE.m2791Container4EFweAY(z, z, mutableInteractionSource, null, textFieldColors, shape, 0.0f, 0.0f, $composer3, 100663296, 200);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }
            }, $composer, 54), $composer, 0, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 4096);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }

        /* JADX INFO: renamed from: androidx.compose.material3.OutlinedTextFieldKt$OutlinedTextField$3$2 */
        /* JADX INFO: compiled from: OutlinedTextField.kt */
        @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
        static final class AnonymousClass2 implements Function3<Function2<? super Composer, ? super Integer, ? extends Unit>, Composer, Integer, Unit> {
            final /* synthetic */ TextFieldColors $colors;
            final /* synthetic */ boolean $enabled;
            final /* synthetic */ MutableInteractionSource $interactionSource;
            final /* synthetic */ boolean $isError;
            final /* synthetic */ Function2<Composer, Integer, Unit> $label;
            final /* synthetic */ Function2<Composer, Integer, Unit> $leadingIcon;
            final /* synthetic */ Function2<Composer, Integer, Unit> $placeholder;
            final /* synthetic */ Function2<Composer, Integer, Unit> $prefix;
            final /* synthetic */ Shape $shape;
            final /* synthetic */ boolean $singleLine;
            final /* synthetic */ Function2<Composer, Integer, Unit> $suffix;
            final /* synthetic */ Function2<Composer, Integer, Unit> $supportingText;
            final /* synthetic */ Function2<Composer, Integer, Unit> $trailingIcon;
            final /* synthetic */ String $value;
            final /* synthetic */ VisualTransformation $visualTransformation;

            /* JADX WARN: Multi-variable type inference failed */
            AnonymousClass2(String str, boolean z2, boolean z3, VisualTransformation visualTransformation, MutableInteractionSource mutableInteractionSource, boolean z4, Function2<? super Composer, ? super Integer, Unit> function2, Function2<? super Composer, ? super Integer, Unit> function22, Function2<? super Composer, ? super Integer, Unit> function23, Function2<? super Composer, ? super Integer, Unit> function24, Function2<? super Composer, ? super Integer, Unit> function25, Function2<? super Composer, ? super Integer, Unit> function26, Function2<? super Composer, ? super Integer, Unit> function27, TextFieldColors textFieldColors, Shape shape) {
                str = str;
                z = z2;
                z = z3;
                visualTransformation = visualTransformation;
                mutableInteractionSource = mutableInteractionSource;
                z = z4;
                function2 = function2;
                function2 = function22;
                function2 = function23;
                function2 = function24;
                function2 = function25;
                function2 = function26;
                function2 = function27;
                textFieldColors = textFieldColors;
                shape = shape;
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Unit invoke(Function2<? super Composer, ? super Integer, ? extends Unit> function2, Composer composer, Integer num) {
                invoke((Function2<? super Composer, ? super Integer, Unit>) function2, composer, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer2, int $changed2) {
                ComposerKt.sourceInformation($composer2, "CN(innerTextField)455@25498L391,439@24710L1202:OutlinedTextField.kt#uh7d8r");
                int $dirty = $changed2;
                if (($changed2 & 6) == 0) {
                    $dirty |= $composer2.changedInstance(function2) ? 4 : 2;
                }
                if ($composer2.shouldExecute(($dirty & 19) != 18, $dirty & 1)) {
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1189274459, $dirty, -1, "androidx.compose.material3.OutlinedTextField.<anonymous>.<anonymous> (OutlinedTextField.kt:439)");
                    }
                    OutlinedTextFieldDefaults.INSTANCE.DecorationBox(str, function2, z, z, visualTransformation, mutableInteractionSource, z, function2, function2, function2, function2, function2, function2, function2, textFieldColors, null, ComposableLambdaKt.rememberComposableLambda(-656940872, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldKt.OutlinedTextField.3.2.1
                        final /* synthetic */ TextFieldColors $colors;
                        final /* synthetic */ boolean $enabled;
                        final /* synthetic */ MutableInteractionSource $interactionSource;
                        final /* synthetic */ boolean $isError;
                        final /* synthetic */ Shape $shape;

                        AnonymousClass1(boolean z2, boolean z3, MutableInteractionSource mutableInteractionSource, TextFieldColors textFieldColors, Shape shape) {
                            z = z2;
                            z = z3;
                            mutableInteractionSource = mutableInteractionSource;
                            textFieldColors = textFieldColors;
                            shape = shape;
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                            invoke(composer, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer $composer3, int $changed3) {
                            ComposerKt.sourceInformation($composer3, "C456@25554L309:OutlinedTextField.kt#uh7d8r");
                            if (!$composer3.shouldExecute(($changed3 & 3) != 2, $changed3 & 1)) {
                                $composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-656940872, $changed3, -1, "androidx.compose.material3.OutlinedTextField.<anonymous>.<anonymous>.<anonymous> (OutlinedTextField.kt:456)");
                            }
                            OutlinedTextFieldDefaults.INSTANCE.m2791Container4EFweAY(z, z, mutableInteractionSource, null, textFieldColors, shape, 0.0f, 0.0f, $composer3, 100663296, 200);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, $composer2, 54), $composer2, ($dirty << 3) & 112, 14155776, 32768);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                        return;
                    }
                    return;
                }
                $composer2.skipToGroupEnd();
            }

            /* JADX INFO: renamed from: androidx.compose.material3.OutlinedTextFieldKt$OutlinedTextField$3$2$1 */
            /* JADX INFO: compiled from: OutlinedTextField.kt */
            @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
            static final class AnonymousClass1 implements Function2<Composer, Integer, Unit> {
                final /* synthetic */ TextFieldColors $colors;
                final /* synthetic */ boolean $enabled;
                final /* synthetic */ MutableInteractionSource $interactionSource;
                final /* synthetic */ boolean $isError;
                final /* synthetic */ Shape $shape;

                AnonymousClass1(boolean z2, boolean z3, MutableInteractionSource mutableInteractionSource, TextFieldColors textFieldColors, Shape shape) {
                    z = z2;
                    z = z3;
                    mutableInteractionSource = mutableInteractionSource;
                    textFieldColors = textFieldColors;
                    shape = shape;
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer $composer3, int $changed3) {
                    ComposerKt.sourceInformation($composer3, "C456@25554L309:OutlinedTextField.kt#uh7d8r");
                    if (!$composer3.shouldExecute(($changed3 & 3) != 2, $changed3 & 1)) {
                        $composer3.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-656940872, $changed3, -1, "androidx.compose.material3.OutlinedTextField.<anonymous>.<anonymous>.<anonymous> (OutlinedTextField.kt:456)");
                    }
                    OutlinedTextFieldDefaults.INSTANCE.m2791Container4EFweAY(z, z, mutableInteractionSource, null, textFieldColors, shape, 0.0f, 0.0f, $composer3, 100663296, 200);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }
        }
    }

    public static final void OutlinedTextField(final TextFieldValue value, final Function1<? super TextFieldValue, Unit> function1, Modifier modifier, boolean enabled, boolean readOnly, TextStyle textStyle, Function2<? super Composer, ? super Integer, Unit> function2, Function2<? super Composer, ? super Integer, Unit> function22, Function2<? super Composer, ? super Integer, Unit> function23, Function2<? super Composer, ? super Integer, Unit> function24, Function2<? super Composer, ? super Integer, Unit> function25, Function2<? super Composer, ? super Integer, Unit> function26, Function2<? super Composer, ? super Integer, Unit> function27, boolean isError, VisualTransformation visualTransformation, KeyboardOptions keyboardOptions, KeyboardActions keyboardActions, boolean singleLine, int maxLines, int minLines, MutableInteractionSource interactionSource, Shape shape, TextFieldColors colors, Composer $composer, final int $changed, final int $changed1, final int $changed2, final int i) {
        TextFieldValue textFieldValue;
        Modifier modifier2;
        boolean enabled2;
        boolean readOnly2;
        TextStyle textStyle2;
        Function2<? super Composer, ? super Integer, Unit> function28;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        final Function2<? super Composer, ? super Integer, Unit> function29;
        Function2<? super Composer, ? super Integer, Unit> function210;
        final Function2<? super Composer, ? super Integer, Unit> function211;
        final boolean isError2;
        final KeyboardOptions keyboardOptions2;
        final KeyboardActions keyboardActions2;
        final boolean enabled3;
        final int maxLines2;
        final int minLines2;
        final MutableInteractionSource interactionSource2;
        final Shape shape2;
        final TextFieldColors colors2;
        Composer $composer2;
        final boolean enabled4;
        final boolean readOnly3;
        final TextStyle textStyle3;
        final Function2<? super Composer, ? super Integer, Unit> function212;
        final Function2<? super Composer, ? super Integer, Unit> function213;
        final Function2<? super Composer, ? super Integer, Unit> function214;
        final Function2<? super Composer, ? super Integer, Unit> function215;
        final VisualTransformation visualTransformation2;
        Function2<? super Composer, ? super Integer, Unit> function216;
        Function2<? super Composer, ? super Integer, Unit> function217;
        Function2<? super Composer, ? super Integer, Unit> function218;
        Function2<? super Composer, ? super Integer, Unit> function219;
        Function2<? super Composer, ? super Integer, Unit> function220;
        Function2<? super Composer, ? super Integer, Unit> function221;
        boolean isError3;
        VisualTransformation visualTransformation3;
        KeyboardOptions keyboardOptions3;
        KeyboardActions keyboardActions3;
        boolean singleLine2;
        int maxLines3;
        int minLines3;
        MutableInteractionSource interactionSource3;
        Function2<? super Composer, ? super Integer, Unit> function222;
        Shape shape3;
        Function2<? super Composer, ? super Integer, Unit> function223;
        Shape shape4;
        Function2<? super Composer, ? super Integer, Unit> function224;
        TextStyle textStyle4;
        Function2<? super Composer, ? super Integer, Unit> function225;
        Function2<? super Composer, ? super Integer, Unit> function226;
        Function2<? super Composer, ? super Integer, Unit> function227;
        Function2<? super Composer, ? super Integer, Unit> function228;
        VisualTransformation visualTransformation4;
        MutableInteractionSource interactionSource4;
        TextFieldColors colors3;
        int minLines4;
        int minLines5;
        boolean singleLine3;
        KeyboardActions keyboardActions4;
        KeyboardOptions keyboardOptions4;
        boolean readOnly4;
        Modifier modifier3;
        MutableInteractionSource interactionSource5;
        MutableInteractionSource interactionSource6;
        int i10;
        Composer $composer3 = $composer.startRestartGroup(2057288437);
        ComposerKt.sourceInformation($composer3, "C(OutlinedTextField)N(value,onValueChange,modifier,enabled,readOnly,textStyle,label,placeholder,leadingIcon,trailingIcon,prefix,suffix,supportingText,isError,visualTransformation,keyboardOptions,keyboardActions,singleLine,maxLines,minLines,interactionSource,shape,colors)571@32380L2835,571@32293L2922:OutlinedTextField.kt#uh7d8r");
        int $dirty = $changed;
        int $dirty1 = $changed1;
        int $dirty2 = $changed2;
        if ((i & 1) != 0) {
            $dirty |= 6;
            textFieldValue = value;
        } else if (($changed & 6) == 0) {
            textFieldValue = value;
            $dirty |= $composer3.changed(textFieldValue) ? 4 : 2;
        } else {
            textFieldValue = value;
        }
        if ((i & 2) != 0) {
            $dirty |= 48;
        } else if (($changed & 48) == 0) {
            $dirty |= $composer3.changedInstance(function1) ? 32 : 16;
        }
        int i11 = i & 4;
        if (i11 != 0) {
            $dirty |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty |= $composer3.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i12 = i & 8;
        if (i12 != 0) {
            $dirty |= 3072;
            enabled2 = enabled;
        } else if (($changed & 3072) == 0) {
            enabled2 = enabled;
            $dirty |= $composer3.changed(enabled2) ? 2048 : 1024;
        } else {
            enabled2 = enabled;
        }
        int i13 = i & 16;
        if (i13 != 0) {
            $dirty |= 24576;
            readOnly2 = readOnly;
        } else if (($changed & 24576) == 0) {
            readOnly2 = readOnly;
            $dirty |= $composer3.changed(readOnly2) ? 16384 : 8192;
        } else {
            readOnly2 = readOnly;
        }
        if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            if ((i & 32) == 0) {
                textStyle2 = textStyle;
                if ($composer3.changed(textStyle2)) {
                    i10 = 131072;
                }
                $dirty |= i10;
            } else {
                textStyle2 = textStyle;
            }
            i10 = 65536;
            $dirty |= i10;
        } else {
            textStyle2 = textStyle;
        }
        int i14 = i & 64;
        if (i14 != 0) {
            $dirty |= 1572864;
            function28 = function2;
        } else if (($changed & 1572864) == 0) {
            function28 = function2;
            $dirty |= $composer3.changedInstance(function28) ? 1048576 : 524288;
        } else {
            function28 = function2;
        }
        int i15 = i & 128;
        if (i15 != 0) {
            $dirty |= 12582912;
            i2 = i15;
        } else if (($changed & 12582912) == 0) {
            i2 = i15;
            $dirty |= $composer3.changedInstance(function22) ? 8388608 : 4194304;
        } else {
            i2 = i15;
        }
        int i16 = i & 256;
        if (i16 != 0) {
            $dirty |= 100663296;
            i3 = i16;
        } else if (($changed & 100663296) == 0) {
            i3 = i16;
            $dirty |= $composer3.changedInstance(function23) ? 67108864 : GroupFlagsKt.HasAuxSlotFlag;
        } else {
            i3 = i16;
        }
        int i17 = i & 512;
        if (i17 != 0) {
            $dirty |= 805306368;
            i4 = i17;
        } else if (($changed & 805306368) == 0) {
            i4 = i17;
            $dirty |= $composer3.changedInstance(function24) ? GroupFlagsKt.HasMovableContentFlag : GroupFlagsKt.IsMovableContentFlag;
        } else {
            i4 = i17;
        }
        int i18 = i & 1024;
        if (i18 != 0) {
            $dirty1 |= 6;
            i5 = i18;
        } else if (($changed1 & 6) == 0) {
            i5 = i18;
            $dirty1 |= $composer3.changedInstance(function25) ? 4 : 2;
        } else {
            i5 = i18;
        }
        int i19 = i & 2048;
        if (i19 != 0) {
            $dirty1 |= 48;
            i6 = i19;
        } else if (($changed1 & 48) == 0) {
            i6 = i19;
            $dirty1 |= $composer3.changedInstance(function26) ? 32 : 16;
        } else {
            i6 = i19;
        }
        int i20 = i & 4096;
        if (i20 != 0) {
            $dirty1 |= 384;
            i7 = i20;
        } else {
            i7 = i20;
            if (($changed1 & 384) == 0) {
                $dirty1 |= $composer3.changedInstance(function27) ? 256 : 128;
            }
        }
        int i21 = i & 8192;
        if (i21 != 0) {
            $dirty1 |= 3072;
            i8 = i21;
        } else {
            i8 = i21;
            if (($changed1 & 3072) == 0) {
                $dirty1 |= $composer3.changed(isError) ? 2048 : 1024;
            }
        }
        int i22 = i & 16384;
        if (i22 != 0) {
            $dirty1 |= 24576;
            i9 = i22;
        } else {
            i9 = i22;
            if (($changed1 & 24576) == 0) {
                $dirty1 |= $composer3.changed(visualTransformation) ? 16384 : 8192;
            }
        }
        int i23 = i & 32768;
        if (i23 != 0) {
            $dirty1 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if (($changed1 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty1 |= $composer3.changed(keyboardOptions) ? 131072 : 65536;
        }
        int i24 = i & 65536;
        if (i24 != 0) {
            $dirty1 |= 1572864;
        } else if (($changed1 & 1572864) == 0) {
            $dirty1 |= $composer3.changed(keyboardActions) ? 1048576 : 524288;
        }
        int i25 = i & 131072;
        if (i25 != 0) {
            $dirty1 |= 12582912;
        } else if (($changed1 & 12582912) == 0) {
            $dirty1 |= $composer3.changed(singleLine) ? 8388608 : 4194304;
        }
        if (($changed1 & 100663296) == 0) {
            $dirty1 |= ((i & 262144) == 0 && $composer3.changed(maxLines)) ? 67108864 : GroupFlagsKt.HasAuxSlotFlag;
        }
        int i26 = i & 524288;
        if (i26 != 0) {
            $dirty1 |= 805306368;
        } else if (($changed1 & 805306368) == 0) {
            $dirty1 |= $composer3.changed(minLines) ? GroupFlagsKt.HasMovableContentFlag : GroupFlagsKt.IsMovableContentFlag;
        }
        int i27 = i & 1048576;
        if (i27 != 0) {
            $dirty2 |= 6;
        } else if (($changed2 & 6) == 0) {
            $dirty2 |= $composer3.changed(interactionSource) ? 4 : 2;
        }
        if (($changed2 & 48) == 0) {
            $dirty2 |= ((i & 2097152) == 0 && $composer3.changed(shape)) ? 32 : 16;
        }
        if (($changed2 & 384) == 0) {
            $dirty2 |= ((i & 4194304) == 0 && $composer3.changed(colors)) ? 256 : 128;
        }
        if ($composer3.shouldExecute((($dirty & 306783379) == 306783378 && (306783379 & $dirty1) == 306783378 && ($dirty2 & 147) == 146) ? false : true, $dirty & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "542@30932L7,558@31736L5,559@31799L8");
            if (($changed & 1) != 0 && !$composer3.getDefaultsInvalid()) {
                $composer3.skipToGroupEnd();
                if ((i & 32) != 0) {
                    $dirty &= -458753;
                }
                if ((262144 & i) != 0) {
                    $dirty1 &= -234881025;
                }
                if ((2097152 & i) != 0) {
                    $dirty2 &= -113;
                }
                if ((i & 4194304) != 0) {
                    function223 = function22;
                    function210 = function23;
                    function224 = function24;
                    function225 = function25;
                    function227 = function26;
                    function228 = function27;
                    isError2 = isError;
                    visualTransformation4 = visualTransformation;
                    keyboardOptions4 = keyboardOptions;
                    keyboardActions4 = keyboardActions;
                    singleLine3 = singleLine;
                    minLines5 = maxLines;
                    minLines4 = minLines;
                    interactionSource4 = interactionSource;
                    shape4 = shape;
                    $dirty2 &= -897;
                    readOnly4 = readOnly2;
                    textStyle4 = textStyle2;
                    function226 = function28;
                    colors3 = colors;
                    modifier3 = modifier2;
                } else {
                    function223 = function22;
                    function210 = function23;
                    function224 = function24;
                    function225 = function25;
                    function227 = function26;
                    function228 = function27;
                    isError2 = isError;
                    visualTransformation4 = visualTransformation;
                    keyboardOptions4 = keyboardOptions;
                    keyboardActions4 = keyboardActions;
                    singleLine3 = singleLine;
                    minLines5 = maxLines;
                    minLines4 = minLines;
                    interactionSource4 = interactionSource;
                    shape4 = shape;
                    readOnly4 = readOnly2;
                    textStyle4 = textStyle2;
                    function226 = function28;
                    colors3 = colors;
                    modifier3 = modifier2;
                }
            } else {
                if (i11 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i12 != 0) {
                    enabled2 = true;
                }
                if (i13 != 0) {
                    readOnly2 = false;
                }
                if ((i & 32) != 0) {
                    ProvidableCompositionLocal<TextStyle> localTextStyle = TextKt.getLocalTextStyle();
                    ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                    Object objConsume = $composer3.consume(localTextStyle);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $dirty &= -458753;
                    textStyle2 = (TextStyle) objConsume;
                }
                if (i14 != 0) {
                    function28 = null;
                }
                if (i2 == 0) {
                    function216 = function22;
                } else {
                    function216 = null;
                }
                if (i3 == 0) {
                    function217 = function23;
                } else {
                    function217 = null;
                }
                if (i4 == 0) {
                    function218 = function24;
                } else {
                    function218 = null;
                }
                if (i5 == 0) {
                    function219 = function25;
                } else {
                    function219 = null;
                }
                if (i6 == 0) {
                    function220 = function26;
                } else {
                    function220 = null;
                }
                if (i7 == 0) {
                    function221 = function27;
                } else {
                    function221 = null;
                }
                if (i8 == 0) {
                    isError3 = isError;
                } else {
                    isError3 = false;
                }
                if (i9 == 0) {
                    visualTransformation3 = visualTransformation;
                } else {
                    visualTransformation3 = VisualTransformation.INSTANCE.getNone();
                }
                if (i23 == 0) {
                    keyboardOptions3 = keyboardOptions;
                } else {
                    keyboardOptions3 = KeyboardOptions.INSTANCE.getDefault();
                }
                if (i24 == 0) {
                    keyboardActions3 = keyboardActions;
                } else {
                    keyboardActions3 = KeyboardActions.INSTANCE.getDefault();
                }
                if (i25 == 0) {
                    singleLine2 = singleLine;
                } else {
                    singleLine2 = false;
                }
                if ((i & 262144) == 0) {
                    maxLines3 = maxLines;
                } else {
                    maxLines3 = singleLine2 ? 1 : Integer.MAX_VALUE;
                    $dirty1 &= -234881025;
                }
                if (i26 == 0) {
                    minLines3 = minLines;
                } else {
                    minLines3 = 1;
                }
                if (i27 == 0) {
                    interactionSource3 = interactionSource;
                } else {
                    interactionSource3 = null;
                }
                Function2<? super Composer, ? super Integer, Unit> function229 = function216;
                if ((i & 2097152) == 0) {
                    function222 = function217;
                    shape3 = shape;
                } else {
                    function222 = function217;
                    shape3 = OutlinedTextFieldDefaults.INSTANCE.getShape($composer3, 6);
                    $dirty2 &= -113;
                }
                if ((i & 4194304) == 0) {
                    function223 = function229;
                    function210 = function222;
                    shape4 = shape3;
                    function224 = function218;
                    textStyle4 = textStyle2;
                    function225 = function219;
                    function226 = function28;
                    function227 = function220;
                    function228 = function221;
                    visualTransformation4 = visualTransformation3;
                    isError2 = isError3;
                    interactionSource4 = interactionSource3;
                    colors3 = colors;
                    minLines4 = minLines3;
                    minLines5 = maxLines3;
                    singleLine3 = singleLine2;
                    keyboardActions4 = keyboardActions3;
                    keyboardOptions4 = keyboardOptions3;
                    readOnly4 = readOnly2;
                    modifier3 = modifier2;
                } else {
                    Shape shape5 = shape3;
                    int i28 = $dirty2 & (-897);
                    Function2<? super Composer, ? super Integer, Unit> function230 = function28;
                    colors3 = OutlinedTextFieldDefaults.INSTANCE.colors($composer3, 6);
                    textStyle4 = textStyle2;
                    function226 = function230;
                    function223 = function229;
                    function210 = function222;
                    shape4 = shape5;
                    $dirty2 = i28;
                    function224 = function218;
                    function225 = function219;
                    function227 = function220;
                    function228 = function221;
                    visualTransformation4 = visualTransformation3;
                    isError2 = isError3;
                    interactionSource4 = interactionSource3;
                    minLines4 = minLines3;
                    minLines5 = maxLines3;
                    singleLine3 = singleLine2;
                    keyboardActions4 = keyboardActions3;
                    keyboardOptions4 = keyboardOptions3;
                    readOnly4 = readOnly2;
                    modifier3 = modifier2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2057288437, $dirty, $dirty1, "androidx.compose.material3.OutlinedTextField (OutlinedTextField.kt:560)");
            }
            if (interactionSource4 == null) {
                $composer3.startReplaceGroup(-502250010);
                ComposerKt.sourceInformation($composer3, "562@31894L39");
                ComposerKt.sourceInformationMarkerStart($composer3, 1369271708, "CC(remember):OutlinedTextField.kt#9igjgp");
                interactionSource5 = interactionSource4;
                Object it$iv = $composer3.rememberedValue();
                if (it$iv == Composer.INSTANCE.getEmpty()) {
                    Object value$iv = InteractionSourceKt.MutableInteractionSource();
                    $composer3.updateRememberedValue(value$iv);
                    it$iv = value$iv;
                }
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endReplaceGroup();
                interactionSource6 = (MutableInteractionSource) it$iv;
            } else {
                interactionSource5 = interactionSource4;
                $composer3.startReplaceGroup(1369271057);
                $composer3.endReplaceGroup();
                interactionSource6 = interactionSource5;
            }
            $composer3.startReplaceGroup(1369277167);
            ComposerKt.sourceInformation($composer3, "*566@32118L25");
            long $this$takeOrElse_u2dDxMtmZc$iv = textStyle4.m7603getColor0d7_KjU();
            if (!($this$takeOrElse_u2dDxMtmZc$iv != 16)) {
                boolean focused = FocusInteractionKt.collectIsFocusedAsState(interactionSource6, $composer3, 0).getValue().booleanValue();
                $this$takeOrElse_u2dDxMtmZc$iv = colors3.m3124textColorXeAY9LY$material3(enabled2, isError2, focused);
            }
            $composer3.endReplaceGroup();
            long textColor = $this$takeOrElse_u2dDxMtmZc$iv;
            TextStyle mergedTextStyle = textStyle4.merge(new TextStyle(textColor, 0L, (FontWeight) null, (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, 0L, (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16777214, (DefaultConstructorMarker) null));
            boolean enabled5 = enabled2;
            CompositionLocalKt.CompositionLocalProvider(TextSelectionColorsKt.getLocalTextSelectionColors().provides(colors3.getTextSelectionColors()), ComposableLambdaKt.rememberComposableLambda(-2094276683, true, new AnonymousClass5(modifier3, function226, isError2, colors3, textFieldValue, function1, enabled5, readOnly4, mergedTextStyle, keyboardOptions4, keyboardActions4, singleLine3, minLines5, minLines4, visualTransformation4, interactionSource6, function223, function210, function224, function225, function227, function228, shape4), $composer3, 54), $composer3, ProvidedValue.$stable | 48);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier2 = modifier3;
            keyboardOptions2 = keyboardOptions4;
            keyboardActions2 = keyboardActions4;
            minLines2 = minLines4;
            function29 = function223;
            function213 = function224;
            function211 = function225;
            function214 = function227;
            shape2 = shape4;
            interactionSource2 = interactionSource5;
            function212 = function226;
            readOnly3 = readOnly4;
            maxLines2 = minLines5;
            function215 = function228;
            textStyle3 = textStyle4;
            $composer2 = $composer3;
            enabled4 = enabled5;
            enabled3 = singleLine3;
            colors2 = colors3;
            visualTransformation2 = visualTransformation4;
        } else {
            $composer3.skipToGroupEnd();
            function29 = function22;
            function210 = function23;
            function211 = function25;
            isError2 = isError;
            keyboardOptions2 = keyboardOptions;
            keyboardActions2 = keyboardActions;
            enabled3 = singleLine;
            maxLines2 = maxLines;
            minLines2 = minLines;
            interactionSource2 = interactionSource;
            shape2 = shape;
            colors2 = colors;
            $composer2 = $composer3;
            enabled4 = enabled2;
            readOnly3 = readOnly2;
            textStyle3 = textStyle2;
            function212 = function28;
            function213 = function24;
            function214 = function26;
            function215 = function27;
            visualTransformation2 = visualTransformation;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Modifier modifier4 = modifier2;
            final Function2<? super Composer, ? super Integer, Unit> function231 = function210;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.OutlinedTextFieldKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return OutlinedTextFieldKt.OutlinedTextField$lambda$8(value, function1, modifier4, enabled4, readOnly3, textStyle3, function212, function29, function231, function213, function211, function214, function215, isError2, visualTransformation2, keyboardOptions2, keyboardActions2, enabled3, maxLines2, minLines2, interactionSource2, shape2, colors2, $changed, $changed1, $changed2, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.OutlinedTextFieldKt$OutlinedTextField$5 */
    /* JADX INFO: compiled from: OutlinedTextField.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    static final class AnonymousClass5 implements Function2<Composer, Integer, Unit> {
        final /* synthetic */ TextFieldColors $colors;
        final /* synthetic */ boolean $enabled;
        final /* synthetic */ MutableInteractionSource $interactionSource;
        final /* synthetic */ boolean $isError;
        final /* synthetic */ KeyboardActions $keyboardActions;
        final /* synthetic */ KeyboardOptions $keyboardOptions;
        final /* synthetic */ Function2<Composer, Integer, Unit> $label;
        final /* synthetic */ Function2<Composer, Integer, Unit> $leadingIcon;
        final /* synthetic */ int $maxLines;
        final /* synthetic */ TextStyle $mergedTextStyle;
        final /* synthetic */ int $minLines;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ Function1<TextFieldValue, Unit> $onValueChange;
        final /* synthetic */ Function2<Composer, Integer, Unit> $placeholder;
        final /* synthetic */ Function2<Composer, Integer, Unit> $prefix;
        final /* synthetic */ boolean $readOnly;
        final /* synthetic */ Shape $shape;
        final /* synthetic */ boolean $singleLine;
        final /* synthetic */ Function2<Composer, Integer, Unit> $suffix;
        final /* synthetic */ Function2<Composer, Integer, Unit> $supportingText;
        final /* synthetic */ Function2<Composer, Integer, Unit> $trailingIcon;
        final /* synthetic */ TextFieldValue $value;
        final /* synthetic */ VisualTransformation $visualTransformation;

        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass5(Modifier modifier, Function2<? super Composer, ? super Integer, Unit> function2, boolean z, TextFieldColors textFieldColors, TextFieldValue textFieldValue, Function1<? super TextFieldValue, Unit> function1, boolean z2, boolean z3, TextStyle textStyle, KeyboardOptions keyboardOptions, KeyboardActions keyboardActions, boolean z4, int i, int i2, VisualTransformation visualTransformation, MutableInteractionSource mutableInteractionSource, Function2<? super Composer, ? super Integer, Unit> function22, Function2<? super Composer, ? super Integer, Unit> function23, Function2<? super Composer, ? super Integer, Unit> function24, Function2<? super Composer, ? super Integer, Unit> function25, Function2<? super Composer, ? super Integer, Unit> function26, Function2<? super Composer, ? super Integer, Unit> function27, Shape shape) {
            this.$modifier = modifier;
            this.$label = function2;
            this.$isError = z;
            this.$colors = textFieldColors;
            this.$value = textFieldValue;
            this.$onValueChange = function1;
            this.$enabled = z2;
            this.$readOnly = z3;
            this.$mergedTextStyle = textStyle;
            this.$keyboardOptions = keyboardOptions;
            this.$keyboardActions = keyboardActions;
            this.$singleLine = z4;
            this.$maxLines = i;
            this.$minLines = i2;
            this.$visualTransformation = visualTransformation;
            this.$interactionSource = mutableInteractionSource;
            this.$placeholder = function22;
            this.$leadingIcon = function23;
            this.$trailingIcon = function24;
            this.$prefix = function25;
            this.$suffix = function26;
            this.$supportingText = function27;
            this.$shape = shape;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer $composer, int $changed) {
            Modifier.Companion companionM1052paddingqDBjuR0$default;
            ComposerKt.sourceInformation($composer, "C587@33087L38,605@33907L1291,572@32390L2819:OutlinedTextField.kt#uh7d8r");
            if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2094276683, $changed, -1, "androidx.compose.material3.OutlinedTextField.<anonymous> (OutlinedTextField.kt:572)");
            }
            Modifier modifier = this.$modifier;
            if (this.$label != null) {
                $composer.startReplaceGroup(-1901539802);
                ComposerKt.sourceInformation($composer, "581@32839L2,582@32889L26");
                Modifier.Companion companion = Modifier.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer, 1739784151, "CC(remember):OutlinedTextField.kt#9igjgp");
                Object it$iv = $composer.rememberedValue();
                if (it$iv == Composer.INSTANCE.getEmpty()) {
                    Object value$iv = new Function1() { // from class: androidx.compose.material3.OutlinedTextFieldKt$OutlinedTextField$5$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return Unit.INSTANCE;
                        }
                    };
                    $composer.updateRememberedValue(value$iv);
                    it$iv = value$iv;
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                companionM1052paddingqDBjuR0$default = PaddingKt.m1052paddingqDBjuR0$default(SemanticsModifierKt.semantics(companion, true, (Function1) it$iv), 0.0f, TextFieldImplKt.minimizedLabelHalfHeight($composer, 0), 0.0f, 0.0f, 13, null);
                $composer.endReplaceGroup();
            } else {
                $composer.startReplaceGroup(-1901156115);
                $composer.endReplaceGroup();
                companionM1052paddingqDBjuR0$default = Modifier.INSTANCE;
            }
            Modifier modifierThen = modifier.then(companionM1052paddingqDBjuR0$default);
            boolean z = this.$isError;
            Strings.Companion companion2 = Strings.INSTANCE;
            BasicTextFieldKt.BasicTextField(this.$value, this.$onValueChange, SizeKt.m1099defaultMinSizeVpY3zN4(TextFieldImplKt.defaultErrorSemantics(modifierThen, z, Strings_androidKt.m3533getString2EP1pXo(Strings.m3454constructorimpl(androidx.compose.ui.R.string.default_error_message), $composer, 0)), OutlinedTextFieldDefaults.INSTANCE.m2797getMinWidthD9Ej5fM(), OutlinedTextFieldDefaults.INSTANCE.m2796getMinHeightD9Ej5fM()), this.$enabled, this.$readOnly, this.$mergedTextStyle, this.$keyboardOptions, this.$keyboardActions, this.$singleLine, this.$maxLines, this.$minLines, this.$visualTransformation, (Function1<? super TextLayoutResult, Unit>) null, this.$interactionSource, new SolidColor(this.$colors.m3074cursorColorvNxB06k$material3(this.$isError), null), ComposableLambdaKt.rememberComposableLambda(674541106, true, new Function3<Function2<? super Composer, ? super Integer, ? extends Unit>, Composer, Integer, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldKt.OutlinedTextField.5.2
                final /* synthetic */ TextFieldColors $colors;
                final /* synthetic */ boolean $enabled;
                final /* synthetic */ MutableInteractionSource $interactionSource;
                final /* synthetic */ boolean $isError;
                final /* synthetic */ Function2<Composer, Integer, Unit> $label;
                final /* synthetic */ Function2<Composer, Integer, Unit> $leadingIcon;
                final /* synthetic */ Function2<Composer, Integer, Unit> $placeholder;
                final /* synthetic */ Function2<Composer, Integer, Unit> $prefix;
                final /* synthetic */ Shape $shape;
                final /* synthetic */ boolean $singleLine;
                final /* synthetic */ Function2<Composer, Integer, Unit> $suffix;
                final /* synthetic */ Function2<Composer, Integer, Unit> $supportingText;
                final /* synthetic */ Function2<Composer, Integer, Unit> $trailingIcon;
                final /* synthetic */ VisualTransformation $visualTransformation;

                /* JADX WARN: Multi-variable type inference failed */
                AnonymousClass2(boolean z2, boolean z3, VisualTransformation visualTransformation, MutableInteractionSource mutableInteractionSource, boolean z4, Function2<? super Composer, ? super Integer, Unit> function2, Function2<? super Composer, ? super Integer, Unit> function22, Function2<? super Composer, ? super Integer, Unit> function23, Function2<? super Composer, ? super Integer, Unit> function24, Function2<? super Composer, ? super Integer, Unit> function25, Function2<? super Composer, ? super Integer, Unit> function26, Function2<? super Composer, ? super Integer, Unit> function27, TextFieldColors textFieldColors, Shape shape) {
                    z = z2;
                    z = z3;
                    visualTransformation = visualTransformation;
                    mutableInteractionSource = mutableInteractionSource;
                    z = z4;
                    function2 = function2;
                    function2 = function22;
                    function2 = function23;
                    function2 = function24;
                    function2 = function25;
                    function2 = function26;
                    function2 = function27;
                    textFieldColors = textFieldColors;
                    shape = shape;
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(Function2<? super Composer, ? super Integer, ? extends Unit> function2, Composer composer, Integer num) {
                    invoke((Function2<? super Composer, ? super Integer, Unit>) function2, composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer2, int $changed2) {
                    ComposerKt.sourceInformation($composer2, "CN(innerTextField)622@34766L391,606@33973L1207:OutlinedTextField.kt#uh7d8r");
                    int $dirty = $changed2;
                    if (($changed2 & 6) == 0) {
                        $dirty |= $composer2.changedInstance(function2) ? 4 : 2;
                    }
                    if ($composer2.shouldExecute(($dirty & 19) != 18, $dirty & 1)) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(674541106, $dirty, -1, "androidx.compose.material3.OutlinedTextField.<anonymous>.<anonymous> (OutlinedTextField.kt:606)");
                        }
                        OutlinedTextFieldDefaults.INSTANCE.DecorationBox(textFieldValue.getText(), function2, z, z, visualTransformation, mutableInteractionSource, z, function2, function2, function2, function2, function2, function2, function2, textFieldColors, null, ComposableLambdaKt.rememberComposableLambda(1409265477, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldKt.OutlinedTextField.5.2.1
                            final /* synthetic */ TextFieldColors $colors;
                            final /* synthetic */ boolean $enabled;
                            final /* synthetic */ MutableInteractionSource $interactionSource;
                            final /* synthetic */ boolean $isError;
                            final /* synthetic */ Shape $shape;

                            AnonymousClass1(boolean z2, boolean z3, MutableInteractionSource mutableInteractionSource, TextFieldColors textFieldColors, Shape shape) {
                                z = z2;
                                z = z3;
                                mutableInteractionSource = mutableInteractionSource;
                                textFieldColors = textFieldColors;
                                shape = shape;
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                                invoke(composer, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer $composer3, int $changed3) {
                                ComposerKt.sourceInformation($composer3, "C623@34822L309:OutlinedTextField.kt#uh7d8r");
                                if (!$composer3.shouldExecute(($changed3 & 3) != 2, $changed3 & 1)) {
                                    $composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(1409265477, $changed3, -1, "androidx.compose.material3.OutlinedTextField.<anonymous>.<anonymous>.<anonymous> (OutlinedTextField.kt:623)");
                                }
                                OutlinedTextFieldDefaults.INSTANCE.m2791Container4EFweAY(z, z, mutableInteractionSource, null, textFieldColors, shape, 0.0f, 0.0f, $composer3, 100663296, 200);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }, $composer2, 54), $composer2, ($dirty << 3) & 112, 14155776, 32768);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer2.skipToGroupEnd();
                }

                /* JADX INFO: renamed from: androidx.compose.material3.OutlinedTextFieldKt$OutlinedTextField$5$2$1 */
                /* JADX INFO: compiled from: OutlinedTextField.kt */
                @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
                static final class AnonymousClass1 implements Function2<Composer, Integer, Unit> {
                    final /* synthetic */ TextFieldColors $colors;
                    final /* synthetic */ boolean $enabled;
                    final /* synthetic */ MutableInteractionSource $interactionSource;
                    final /* synthetic */ boolean $isError;
                    final /* synthetic */ Shape $shape;

                    AnonymousClass1(boolean z2, boolean z3, MutableInteractionSource mutableInteractionSource, TextFieldColors textFieldColors, Shape shape) {
                        z = z2;
                        z = z3;
                        mutableInteractionSource = mutableInteractionSource;
                        textFieldColors = textFieldColors;
                        shape = shape;
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                        invoke(composer, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer $composer3, int $changed3) {
                        ComposerKt.sourceInformation($composer3, "C623@34822L309:OutlinedTextField.kt#uh7d8r");
                        if (!$composer3.shouldExecute(($changed3 & 3) != 2, $changed3 & 1)) {
                            $composer3.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1409265477, $changed3, -1, "androidx.compose.material3.OutlinedTextField.<anonymous>.<anonymous>.<anonymous> (OutlinedTextField.kt:623)");
                        }
                        OutlinedTextFieldDefaults.INSTANCE.m2791Container4EFweAY(z, z, mutableInteractionSource, null, textFieldColors, shape, 0.0f, 0.0f, $composer3, 100663296, 200);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }
            }, $composer, 54), $composer, 0, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 4096);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }

        /* JADX INFO: renamed from: androidx.compose.material3.OutlinedTextFieldKt$OutlinedTextField$5$2 */
        /* JADX INFO: compiled from: OutlinedTextField.kt */
        @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
        static final class AnonymousClass2 implements Function3<Function2<? super Composer, ? super Integer, ? extends Unit>, Composer, Integer, Unit> {
            final /* synthetic */ TextFieldColors $colors;
            final /* synthetic */ boolean $enabled;
            final /* synthetic */ MutableInteractionSource $interactionSource;
            final /* synthetic */ boolean $isError;
            final /* synthetic */ Function2<Composer, Integer, Unit> $label;
            final /* synthetic */ Function2<Composer, Integer, Unit> $leadingIcon;
            final /* synthetic */ Function2<Composer, Integer, Unit> $placeholder;
            final /* synthetic */ Function2<Composer, Integer, Unit> $prefix;
            final /* synthetic */ Shape $shape;
            final /* synthetic */ boolean $singleLine;
            final /* synthetic */ Function2<Composer, Integer, Unit> $suffix;
            final /* synthetic */ Function2<Composer, Integer, Unit> $supportingText;
            final /* synthetic */ Function2<Composer, Integer, Unit> $trailingIcon;
            final /* synthetic */ VisualTransformation $visualTransformation;

            /* JADX WARN: Multi-variable type inference failed */
            AnonymousClass2(boolean z2, boolean z3, VisualTransformation visualTransformation, MutableInteractionSource mutableInteractionSource, boolean z4, Function2<? super Composer, ? super Integer, Unit> function2, Function2<? super Composer, ? super Integer, Unit> function22, Function2<? super Composer, ? super Integer, Unit> function23, Function2<? super Composer, ? super Integer, Unit> function24, Function2<? super Composer, ? super Integer, Unit> function25, Function2<? super Composer, ? super Integer, Unit> function26, Function2<? super Composer, ? super Integer, Unit> function27, TextFieldColors textFieldColors, Shape shape) {
                z = z2;
                z = z3;
                visualTransformation = visualTransformation;
                mutableInteractionSource = mutableInteractionSource;
                z = z4;
                function2 = function2;
                function2 = function22;
                function2 = function23;
                function2 = function24;
                function2 = function25;
                function2 = function26;
                function2 = function27;
                textFieldColors = textFieldColors;
                shape = shape;
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Unit invoke(Function2<? super Composer, ? super Integer, ? extends Unit> function2, Composer composer, Integer num) {
                invoke((Function2<? super Composer, ? super Integer, Unit>) function2, composer, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer2, int $changed2) {
                ComposerKt.sourceInformation($composer2, "CN(innerTextField)622@34766L391,606@33973L1207:OutlinedTextField.kt#uh7d8r");
                int $dirty = $changed2;
                if (($changed2 & 6) == 0) {
                    $dirty |= $composer2.changedInstance(function2) ? 4 : 2;
                }
                if ($composer2.shouldExecute(($dirty & 19) != 18, $dirty & 1)) {
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(674541106, $dirty, -1, "androidx.compose.material3.OutlinedTextField.<anonymous>.<anonymous> (OutlinedTextField.kt:606)");
                    }
                    OutlinedTextFieldDefaults.INSTANCE.DecorationBox(textFieldValue.getText(), function2, z, z, visualTransformation, mutableInteractionSource, z, function2, function2, function2, function2, function2, function2, function2, textFieldColors, null, ComposableLambdaKt.rememberComposableLambda(1409265477, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.OutlinedTextFieldKt.OutlinedTextField.5.2.1
                        final /* synthetic */ TextFieldColors $colors;
                        final /* synthetic */ boolean $enabled;
                        final /* synthetic */ MutableInteractionSource $interactionSource;
                        final /* synthetic */ boolean $isError;
                        final /* synthetic */ Shape $shape;

                        AnonymousClass1(boolean z2, boolean z3, MutableInteractionSource mutableInteractionSource, TextFieldColors textFieldColors, Shape shape) {
                            z = z2;
                            z = z3;
                            mutableInteractionSource = mutableInteractionSource;
                            textFieldColors = textFieldColors;
                            shape = shape;
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                            invoke(composer, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer $composer3, int $changed3) {
                            ComposerKt.sourceInformation($composer3, "C623@34822L309:OutlinedTextField.kt#uh7d8r");
                            if (!$composer3.shouldExecute(($changed3 & 3) != 2, $changed3 & 1)) {
                                $composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1409265477, $changed3, -1, "androidx.compose.material3.OutlinedTextField.<anonymous>.<anonymous>.<anonymous> (OutlinedTextField.kt:623)");
                            }
                            OutlinedTextFieldDefaults.INSTANCE.m2791Container4EFweAY(z, z, mutableInteractionSource, null, textFieldColors, shape, 0.0f, 0.0f, $composer3, 100663296, 200);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, $composer2, 54), $composer2, ($dirty << 3) & 112, 14155776, 32768);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                        return;
                    }
                    return;
                }
                $composer2.skipToGroupEnd();
            }

            /* JADX INFO: renamed from: androidx.compose.material3.OutlinedTextFieldKt$OutlinedTextField$5$2$1 */
            /* JADX INFO: compiled from: OutlinedTextField.kt */
            @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
            static final class AnonymousClass1 implements Function2<Composer, Integer, Unit> {
                final /* synthetic */ TextFieldColors $colors;
                final /* synthetic */ boolean $enabled;
                final /* synthetic */ MutableInteractionSource $interactionSource;
                final /* synthetic */ boolean $isError;
                final /* synthetic */ Shape $shape;

                AnonymousClass1(boolean z2, boolean z3, MutableInteractionSource mutableInteractionSource, TextFieldColors textFieldColors, Shape shape) {
                    z = z2;
                    z = z3;
                    mutableInteractionSource = mutableInteractionSource;
                    textFieldColors = textFieldColors;
                    shape = shape;
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer $composer3, int $changed3) {
                    ComposerKt.sourceInformation($composer3, "C623@34822L309:OutlinedTextField.kt#uh7d8r");
                    if (!$composer3.shouldExecute(($changed3 & 3) != 2, $changed3 & 1)) {
                        $composer3.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1409265477, $changed3, -1, "androidx.compose.material3.OutlinedTextField.<anonymous>.<anonymous>.<anonymous> (OutlinedTextField.kt:623)");
                    }
                    OutlinedTextFieldDefaults.INSTANCE.m2791Container4EFweAY(z, z, mutableInteractionSource, null, textFieldColors, shape, 0.0f, 0.0f, $composer3, 100663296, 200);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:1024:0x0c64  */
    /* JADX WARN: Removed duplicated region for block: B:1026:0x0c75  */
    /* JADX WARN: Removed duplicated region for block: B:1042:0x0dd8  */
    /* JADX WARN: Removed duplicated region for block: B:1045:0x0df9  */
    /* JADX WARN: Removed duplicated region for block: B:987:0x0aa4  */
    /* JADX WARN: Removed duplicated region for block: B:988:0x0ac2  */
    /* JADX WARN: Removed duplicated region for block: B:991:0x0aca  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void OutlinedTextFieldLayout(final androidx.compose.ui.Modifier r65, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r66, final kotlin.jvm.functions.Function3<? super androidx.compose.ui.Modifier, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r67, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r68, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r69, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r70, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r71, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r72, final boolean r73, androidx.compose.material3.TextFieldLabelPosition r74, final androidx.compose.material3.internal.FloatProducer r75, final kotlin.jvm.functions.Function1<? super androidx.compose.ui.geometry.Size, kotlin.Unit> r76, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r77, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r78, androidx.compose.foundation.layout.PaddingValues r79, androidx.compose.runtime.Composer r80, final int r81, final int r82) {
        /*
            Method dump skipped, instruction units count: 3648
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.OutlinedTextFieldKt.OutlinedTextFieldLayout(androidx.compose.ui.Modifier, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function3, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, boolean, androidx.compose.material3.TextFieldLabelPosition, androidx.compose.material3.internal.FloatProducer, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, androidx.compose.foundation.layout.PaddingValues, androidx.compose.runtime.Composer, int, int):void");
    }

    public static final Modifier outlineCutout(Modifier $this$outlineCutout, final Function0<Size> function0, final Alignment.Horizontal alignment, final PaddingValues paddingValues) {
        return DrawModifierKt.drawWithContent($this$outlineCutout, new Function1() { // from class: androidx.compose.material3.OutlinedTextFieldKt$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return OutlinedTextFieldKt.outlineCutout$lambda$22(function0, paddingValues, alignment, (ContentDrawScope) obj);
            }
        });
    }

    static final Unit outlineCutout$lambda$22(Function0 $labelSize, PaddingValues $paddingValues, Alignment.Horizontal $alignment, ContentDrawScope $this$drawWithContent) {
        long labelSizeValue = ((Size) $labelSize.invoke()).m5142unboximpl();
        int bits$iv$iv$iv = (int) (labelSizeValue >> 32);
        float labelWidth = Float.intBitsToFloat(bits$iv$iv$iv);
        if (labelWidth > 0.0f) {
            float innerPadding = $this$drawWithContent.mo432toPx0680j_4(OutlinedTextFieldInnerPadding);
            float leftPadding = $this$drawWithContent.mo432toPx0680j_4($paddingValues.mo998calculateLeftPaddingu2uoSUM($this$drawWithContent.getLayoutDirection()));
            float rightPadding = $this$drawWithContent.mo432toPx0680j_4($paddingValues.mo999calculateRightPaddingu2uoSUM($this$drawWithContent.getLayoutDirection()));
            int iRoundToInt = MathKt.roundToInt(labelWidth);
            long arg0$iv = $this$drawWithContent.mo5887getSizeNHjbRc();
            int bits$iv$iv$iv2 = (int) (arg0$iv >> 32);
            float labelCenter = $alignment.align(iRoundToInt, MathKt.roundToInt((Float.intBitsToFloat(bits$iv$iv$iv2) - leftPadding) - rightPadding), $this$drawWithContent.getLayoutDirection()) + leftPadding + (labelWidth / 2.0f);
            float left = RangesKt.coerceAtLeast((labelCenter - (labelWidth / 2.0f)) - innerPadding, 0.0f);
            float f = (labelWidth / 2.0f) + labelCenter + innerPadding;
            long arg0$iv2 = $this$drawWithContent.mo5887getSizeNHjbRc();
            int bits$iv$iv$iv3 = (int) (arg0$iv2 >> 32);
            float right = RangesKt.coerceAtMost(f, Float.intBitsToFloat(bits$iv$iv$iv3));
            int bits$iv$iv$iv4 = (int) (4294967295L & labelSizeValue);
            float labelHeight = Float.intBitsToFloat(bits$iv$iv$iv4);
            ContentDrawScope $this$clipRect_u2drOu3jXo$iv = $this$drawWithContent;
            float top$iv = (-labelHeight) / 2.0f;
            float bottom$iv = labelHeight / 2.0f;
            int clipOp$iv = ClipOp.INSTANCE.m5301getDifferencertfAjoo();
            DrawContext $this$withTransform_u24lambda_u246$iv$iv = $this$clipRect_u2drOu3jXo$iv.getDrawContext();
            long previousSize$iv$iv = $this$withTransform_u24lambda_u246$iv$iv.mo5808getSizeNHjbRc();
            $this$withTransform_u24lambda_u246$iv$iv.getCanvas().save();
            try {
                DrawTransform $this$clipRect_rOu3jXo_u24lambda_u244$iv = $this$withTransform_u24lambda_u246$iv$iv.getTransform();
                $this$clipRect_rOu3jXo_u24lambda_u244$iv.mo5811clipRectN_I0leg(left, top$iv, right, bottom$iv, clipOp$iv);
                $this$drawWithContent.drawContent();
            } finally {
                $this$withTransform_u24lambda_u246$iv$iv.getCanvas().restore();
                $this$withTransform_u24lambda_u246$iv$iv.mo5809setSizeuvyYCjk(previousSize$iv$iv);
            }
        } else {
            $this$drawWithContent.drawContent();
        }
        return Unit.INSTANCE;
    }
}
