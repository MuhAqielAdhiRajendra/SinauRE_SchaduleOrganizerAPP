package androidx.compose.material3;

import androidx.autofill.HintConstants;
import androidx.compose.foundation.ScrollKt;
import androidx.compose.foundation.ScrollState;
import androidx.compose.foundation.interaction.FocusInteractionKt;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
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
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.SolidColor;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
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

/* JADX INFO: compiled from: TextField.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000Ê\u0001\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a£\u0003\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2 \b\u0002\u0010\r\u001a\u001a\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u000e¢\u0006\u0002\b\u0010¢\u0006\u0002\b\u00112\u0015\b\u0002\u0010\u0012\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0013¢\u0006\u0002\b\u00102\u0015\b\u0002\u0010\u0014\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0013¢\u0006\u0002\b\u00102\u0015\b\u0002\u0010\u0015\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0013¢\u0006\u0002\b\u00102\u0015\b\u0002\u0010\u0016\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0013¢\u0006\u0002\b\u00102\u0015\b\u0002\u0010\u0017\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0013¢\u0006\u0002\b\u00102\u0015\b\u0002\u0010\u0018\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0013¢\u0006\u0002\b\u00102\b\b\u0002\u0010\u0019\u001a\u00020\u00072\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\b\b\u0002\u0010\u001e\u001a\u00020\u001f2\n\b\u0002\u0010 \u001a\u0004\u0018\u00010!2\b\b\u0002\u0010\"\u001a\u00020#28\b\u0002\u0010$\u001a2\u0012\u0004\u0012\u00020&\u0012\u001b\u0012\u0019\u0012\u0006\u0012\u0004\u0018\u00010'0\u0013¢\u0006\f\b(\u0012\b\b)\u0012\u0004\b\b(*\u0012\u0004\u0012\u00020\u0001\u0018\u00010%¢\u0006\u0002\b\u00112\b\b\u0002\u0010+\u001a\u00020,2\b\b\u0002\u0010-\u001a\u00020.2\b\b\u0002\u0010/\u001a\u0002002\b\b\u0002\u00101\u001a\u0002022\n\b\u0002\u00103\u001a\u0004\u0018\u000104H\u0007¢\u0006\u0002\u00105\u001aØ\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u00106\u001a\u0002072\u0012\u00108\u001a\u000e\u0012\u0004\u0012\u000207\u0012\u0004\u0012\u00020\u00010\u000e2\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\n2\u0015\b\u0002\u0010\r\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0013¢\u0006\u0002\b\u00102\u0015\b\u0002\u0010\u0012\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0013¢\u0006\u0002\b\u00102\u0015\b\u0002\u0010\u0014\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0013¢\u0006\u0002\b\u00102\u0015\b\u0002\u0010\u0015\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0013¢\u0006\u0002\b\u00102\u0015\b\u0002\u0010\u0016\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0013¢\u0006\u0002\b\u00102\u0015\b\u0002\u0010\u0017\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0013¢\u0006\u0002\b\u00102\u0015\b\u0002\u0010\u0018\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0013¢\u0006\u0002\b\u00102\b\b\u0002\u0010\u0019\u001a\u00020\u00072\b\b\u0002\u00109\u001a\u00020:2\b\b\u0002\u0010\u001e\u001a\u00020\u001f2\b\b\u0002\u0010;\u001a\u00020<2\b\b\u0002\u0010=\u001a\u00020\u00072\b\b\u0002\u0010>\u001a\u00020?2\b\b\u0002\u0010@\u001a\u00020?2\n\b\u0002\u00103\u001a\u0004\u0018\u0001042\b\b\u0002\u0010-\u001a\u00020.2\b\b\u0002\u0010/\u001a\u000200H\u0007¢\u0006\u0002\u0010A\u001aØ\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u00106\u001a\u00020B2\u0012\u00108\u001a\u000e\u0012\u0004\u0012\u00020B\u0012\u0004\u0012\u00020\u00010\u000e2\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\n2\u0015\b\u0002\u0010\r\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0013¢\u0006\u0002\b\u00102\u0015\b\u0002\u0010\u0012\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0013¢\u0006\u0002\b\u00102\u0015\b\u0002\u0010\u0014\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0013¢\u0006\u0002\b\u00102\u0015\b\u0002\u0010\u0015\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0013¢\u0006\u0002\b\u00102\u0015\b\u0002\u0010\u0016\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0013¢\u0006\u0002\b\u00102\u0015\b\u0002\u0010\u0017\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0013¢\u0006\u0002\b\u00102\u0015\b\u0002\u0010\u0018\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0013¢\u0006\u0002\b\u00102\b\b\u0002\u0010\u0019\u001a\u00020\u00072\b\b\u0002\u00109\u001a\u00020:2\b\b\u0002\u0010\u001e\u001a\u00020\u001f2\b\b\u0002\u0010;\u001a\u00020<2\b\b\u0002\u0010=\u001a\u00020\u00072\b\b\u0002\u0010>\u001a\u00020?2\b\b\u0002\u0010@\u001a\u00020?2\n\b\u0002\u00103\u001a\u0004\u0018\u0001042\b\b\u0002\u0010-\u001a\u00020.2\b\b\u0002\u0010/\u001a\u000200H\u0007¢\u0006\u0002\u0010C\u001aô\u0001\u0010D\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0011\u0010E\u001a\r\u0012\u0004\u0012\u00020\u00010\u0013¢\u0006\u0002\b\u00102\u0013\u0010\r\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0013¢\u0006\u0002\b\u00102\u0019\u0010\u0012\u001a\u0015\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u000e¢\u0006\u0002\b\u00102\u0013\u0010F\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0013¢\u0006\u0002\b\u00102\u0013\u0010G\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0013¢\u0006\u0002\b\u00102\u0013\u0010\u0016\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0013¢\u0006\u0002\b\u00102\u0013\u0010\u0017\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0013¢\u0006\u0002\b\u00102\u0006\u0010=\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010H\u001a\u00020I2\u0011\u0010J\u001a\r\u0012\u0004\u0012\u00020\u00010\u0013¢\u0006\u0002\b\u00102\u0013\u0010K\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0013¢\u0006\u0002\b\u00102\u0006\u0010L\u001a\u000202H\u0001¢\u0006\u0002\u0010M\"\u0016\u0010N\u001a\u00020OX\u0080\u0004¢\u0006\n\n\u0002\u0010R\u001a\u0004\bP\u0010Q¨\u0006S"}, d2 = {TextFieldImplKt.TextFieldId, "", "state", "Landroidx/compose/foundation/text/input/TextFieldState;", "modifier", "Landroidx/compose/ui/Modifier;", "enabled", "", "readOnly", "textStyle", "Landroidx/compose/ui/text/TextStyle;", "labelPosition", "Landroidx/compose/material3/TextFieldLabelPosition;", "label", "Lkotlin/Function1;", "Landroidx/compose/material3/TextFieldLabelScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "placeholder", "Lkotlin/Function0;", "leadingIcon", "trailingIcon", "prefix", "suffix", "supportingText", "isError", "inputTransformation", "Landroidx/compose/foundation/text/input/InputTransformation;", "outputTransformation", "Landroidx/compose/foundation/text/input/OutputTransformation;", "keyboardOptions", "Landroidx/compose/foundation/text/KeyboardOptions;", "onKeyboardAction", "Landroidx/compose/foundation/text/input/KeyboardActionHandler;", "lineLimits", "Landroidx/compose/foundation/text/input/TextFieldLineLimits;", "onTextLayout", "Lkotlin/Function2;", "Landroidx/compose/ui/unit/Density;", "Landroidx/compose/ui/text/TextLayoutResult;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "getResult", "scrollState", "Landroidx/compose/foundation/ScrollState;", "shape", "Landroidx/compose/ui/graphics/Shape;", "colors", "Landroidx/compose/material3/TextFieldColors;", "contentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "(Landroidx/compose/foundation/text/input/TextFieldState;Landroidx/compose/ui/Modifier;ZZLandroidx/compose/ui/text/TextStyle;Landroidx/compose/material3/TextFieldLabelPosition;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;ZLandroidx/compose/foundation/text/input/InputTransformation;Landroidx/compose/foundation/text/input/OutputTransformation;Landroidx/compose/foundation/text/KeyboardOptions;Landroidx/compose/foundation/text/input/KeyboardActionHandler;Landroidx/compose/foundation/text/input/TextFieldLineLimits;Lkotlin/jvm/functions/Function2;Landroidx/compose/foundation/ScrollState;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/TextFieldColors;Landroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;IIII)V", "value", "", "onValueChange", "visualTransformation", "Landroidx/compose/ui/text/input/VisualTransformation;", "keyboardActions", "Landroidx/compose/foundation/text/KeyboardActions;", "singleLine", "maxLines", "", "minLines", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZZLandroidx/compose/ui/text/TextStyle;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;ZLandroidx/compose/ui/text/input/VisualTransformation;Landroidx/compose/foundation/text/KeyboardOptions;Landroidx/compose/foundation/text/KeyboardActions;ZIILandroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/TextFieldColors;Landroidx/compose/runtime/Composer;IIII)V", "Landroidx/compose/ui/text/input/TextFieldValue;", "(Landroidx/compose/ui/text/input/TextFieldValue;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZZLandroidx/compose/ui/text/TextStyle;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;ZLandroidx/compose/ui/text/input/VisualTransformation;Landroidx/compose/foundation/text/KeyboardOptions;Landroidx/compose/foundation/text/KeyboardActions;ZIILandroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/TextFieldColors;Landroidx/compose/runtime/Composer;IIII)V", "TextFieldLayout", "textField", "leading", "trailing", "labelProgress", "Landroidx/compose/material3/internal/FloatProducer;", "container", "supporting", "paddingValues", "(Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;ZLandroidx/compose/material3/TextFieldLabelPosition;Landroidx/compose/material3/internal/FloatProducer;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/runtime/Composer;II)V", "TextFieldWithLabelVerticalPadding", "Landroidx/compose/ui/unit/Dp;", "getTextFieldWithLabelVerticalPadding", "()F", "F", "material3"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class TextFieldKt {
    private static final float TextFieldWithLabelVerticalPadding = Dp.m8150constructorimpl(8);

    static final Unit TextField$lambda$2(TextFieldState textFieldState, Modifier modifier, boolean z, boolean z2, TextStyle textStyle, TextFieldLabelPosition textFieldLabelPosition, Function3 function3, Function2 function2, Function2 function22, Function2 function23, Function2 function24, Function2 function25, Function2 function26, boolean z3, InputTransformation inputTransformation, OutputTransformation outputTransformation, KeyboardOptions keyboardOptions, KeyboardActionHandler keyboardActionHandler, TextFieldLineLimits textFieldLineLimits, Function2 function27, ScrollState scrollState, Shape shape, TextFieldColors textFieldColors, PaddingValues paddingValues, MutableInteractionSource mutableInteractionSource, int i, int i2, int i3, int i4, Composer composer, int i5) {
        TextField(textFieldState, modifier, z, z2, textStyle, textFieldLabelPosition, function3, function2, function22, function23, function24, function25, function26, z3, inputTransformation, outputTransformation, keyboardOptions, keyboardActionHandler, textFieldLineLimits, function27, scrollState, shape, textFieldColors, paddingValues, mutableInteractionSource, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), RecomposeScopeImplKt.updateChangedFlags(i3), i4);
        return Unit.INSTANCE;
    }

    static final Unit TextField$lambda$5(String str, Function1 function1, Modifier modifier, boolean z, boolean z2, TextStyle textStyle, Function2 function2, Function2 function22, Function2 function23, Function2 function24, Function2 function25, Function2 function26, Function2 function27, boolean z3, VisualTransformation visualTransformation, KeyboardOptions keyboardOptions, KeyboardActions keyboardActions, boolean z4, int i, int i2, MutableInteractionSource mutableInteractionSource, Shape shape, TextFieldColors textFieldColors, int i3, int i4, int i5, int i6, Composer composer, int i7) {
        TextField(str, (Function1<? super String, Unit>) function1, modifier, z, z2, textStyle, (Function2<? super Composer, ? super Integer, Unit>) function2, (Function2<? super Composer, ? super Integer, Unit>) function22, (Function2<? super Composer, ? super Integer, Unit>) function23, (Function2<? super Composer, ? super Integer, Unit>) function24, (Function2<? super Composer, ? super Integer, Unit>) function25, (Function2<? super Composer, ? super Integer, Unit>) function26, (Function2<? super Composer, ? super Integer, Unit>) function27, z3, visualTransformation, keyboardOptions, keyboardActions, z4, i, i2, mutableInteractionSource, shape, textFieldColors, composer, RecomposeScopeImplKt.updateChangedFlags(i3 | 1), RecomposeScopeImplKt.updateChangedFlags(i4), RecomposeScopeImplKt.updateChangedFlags(i5), i6);
        return Unit.INSTANCE;
    }

    static final Unit TextField$lambda$8(TextFieldValue textFieldValue, Function1 function1, Modifier modifier, boolean z, boolean z2, TextStyle textStyle, Function2 function2, Function2 function22, Function2 function23, Function2 function24, Function2 function25, Function2 function26, Function2 function27, boolean z3, VisualTransformation visualTransformation, KeyboardOptions keyboardOptions, KeyboardActions keyboardActions, boolean z4, int i, int i2, MutableInteractionSource mutableInteractionSource, Shape shape, TextFieldColors textFieldColors, int i3, int i4, int i5, int i6, Composer composer, int i7) {
        TextField(textFieldValue, (Function1<? super TextFieldValue, Unit>) function1, modifier, z, z2, textStyle, (Function2<? super Composer, ? super Integer, Unit>) function2, (Function2<? super Composer, ? super Integer, Unit>) function22, (Function2<? super Composer, ? super Integer, Unit>) function23, (Function2<? super Composer, ? super Integer, Unit>) function24, (Function2<? super Composer, ? super Integer, Unit>) function25, (Function2<? super Composer, ? super Integer, Unit>) function26, (Function2<? super Composer, ? super Integer, Unit>) function27, z3, visualTransformation, keyboardOptions, keyboardActions, z4, i, i2, mutableInteractionSource, shape, textFieldColors, composer, RecomposeScopeImplKt.updateChangedFlags(i3 | 1), RecomposeScopeImplKt.updateChangedFlags(i4), RecomposeScopeImplKt.updateChangedFlags(i5), i6);
        return Unit.INSTANCE;
    }

    static final Unit TextFieldLayout$lambda$20(Modifier modifier, Function2 function2, Function2 function22, Function3 function3, Function2 function23, Function2 function24, Function2 function25, Function2 function26, boolean z, TextFieldLabelPosition textFieldLabelPosition, FloatProducer floatProducer, Function2 function27, Function2 function28, PaddingValues paddingValues, int i, int i2, Composer composer, int i3) {
        TextFieldLayout(modifier, function2, function22, function3, function23, function24, function25, function26, z, textFieldLabelPosition, floatProducer, function27, function28, paddingValues, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2));
        return Unit.INSTANCE;
    }

    public static final void TextField(final TextFieldState state, Modifier modifier, boolean enabled, boolean readOnly, TextStyle textStyle, TextFieldLabelPosition labelPosition, Function3<? super TextFieldLabelScope, ? super Composer, ? super Integer, Unit> function3, Function2<? super Composer, ? super Integer, Unit> function2, Function2<? super Composer, ? super Integer, Unit> function22, Function2<? super Composer, ? super Integer, Unit> function23, Function2<? super Composer, ? super Integer, Unit> function24, Function2<? super Composer, ? super Integer, Unit> function25, Function2<? super Composer, ? super Integer, Unit> function26, boolean isError, InputTransformation inputTransformation, OutputTransformation outputTransformation, KeyboardOptions keyboardOptions, KeyboardActionHandler onKeyboardAction, TextFieldLineLimits lineLimits, Function2<? super Density, ? super Function0<TextLayoutResult>, Unit> function27, ScrollState scrollState, Shape shape, TextFieldColors colors, PaddingValues contentPadding, MutableInteractionSource interactionSource, Composer $composer, final int $changed, final int $changed1, final int $changed2, final int i) {
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
        final boolean isError2;
        final InputTransformation inputTransformation2;
        final OutputTransformation outputTransformation2;
        final KeyboardOptions keyboardOptions2;
        final KeyboardActionHandler onKeyboardAction2;
        final TextFieldLineLimits lineLimits2;
        final Function2<? super Density, ? super Function0<TextLayoutResult>, Unit> function29;
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
        final Function2<? super Composer, ? super Integer, Unit> function210;
        final Function2<? super Composer, ? super Integer, Unit> function211;
        final Function2<? super Composer, ? super Integer, Unit> function212;
        final Function2<? super Composer, ? super Integer, Unit> function213;
        final Function2<? super Composer, ? super Integer, Unit> function214;
        TextFieldLineLimits lineLimits3;
        Function3<? super TextFieldLabelScope, ? super Composer, ? super Integer, Unit> function33;
        Function2<? super Composer, ? super Integer, Unit> function215;
        ScrollState scrollState3;
        Shape shape3;
        Shape shape4;
        TextFieldColors colors3;
        PaddingValues contentPadding3;
        Shape shape5;
        PaddingValues contentPadding4;
        boolean readOnly4;
        Function2<? super Composer, ? super Integer, Unit> function216;
        InputTransformation inputTransformation3;
        OutputTransformation outputTransformation3;
        KeyboardOptions keyboardOptions3;
        KeyboardActionHandler onKeyboardAction3;
        Function2<? super Density, ? super Function0<TextLayoutResult>, Unit> function217;
        ScrollState scrollState4;
        Function3<? super TextFieldLabelScope, ? super Composer, ? super Integer, Unit> function34;
        Function2<? super Composer, ? super Integer, Unit> function218;
        MutableInteractionSource interactionSource3;
        Function2<? super Composer, ? super Integer, Unit> function219;
        TextFieldLabelPosition labelPosition4;
        Modifier modifier4;
        Function2<? super Composer, ? super Integer, Unit> function220;
        Function2<? super Composer, ? super Integer, Unit> function221;
        boolean isError3;
        TextFieldLineLimits lineLimits4;
        TextFieldColors colors4;
        Function2<? super Composer, ? super Integer, Unit> function222;
        TextStyle textStyle4;
        MutableInteractionSource interactionSource4;
        MutableInteractionSource interactionSource5;
        int i10;
        Composer $composer3 = $composer.startRestartGroup(-1717599650);
        ComposerKt.sourceInformation($composer3, "C(TextField)N(state,modifier,enabled,readOnly,textStyle,labelPosition,label,placeholder,leadingIcon,trailingIcon,prefix,suffix,supportingText,isError,inputTransformation,outputTransformation,keyboardOptions,onKeyboardAction,lineLimits,onTextLayout,scrollState,shape,colors,contentPadding,interactionSource)298@16411L2145,298@16324L2232:TextField.kt#uh7d8r");
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
            ComposerKt.sourceInformation($composer3, "261@14511L7,277@15401L21,278@15461L5,279@15516L8");
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
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
                Function3<? super TextFieldLabelScope, ? super Composer, ? super Integer, Unit> function35 = i15 != 0 ? null : function3;
                Function2<? super Composer, ? super Integer, Unit> function223 = i2 != 0 ? null : function2;
                Function2<? super Composer, ? super Integer, Unit> function224 = i3 != 0 ? null : function22;
                Function2<? super Composer, ? super Integer, Unit> function225 = i4 != 0 ? null : function23;
                Function2<? super Composer, ? super Integer, Unit> function226 = i5 != 0 ? null : function24;
                Function2<? super Composer, ? super Integer, Unit> function227 = i6 != 0 ? null : function25;
                Function2<? super Composer, ? super Integer, Unit> function228 = i7 != 0 ? null : function26;
                boolean isError4 = i8 != 0 ? false : isError;
                InputTransformation inputTransformation4 = i9 != 0 ? null : inputTransformation;
                OutputTransformation outputTransformation4 = i24 != 0 ? null : outputTransformation;
                KeyboardOptions keyboardOptions4 = i25 != 0 ? KeyboardOptions.INSTANCE.getDefault() : keyboardOptions;
                KeyboardActionHandler onKeyboardAction4 = i26 != 0 ? null : onKeyboardAction;
                if ((i & 262144) != 0) {
                    lineLimits3 = TextFieldLineLimits.INSTANCE.getDefault();
                    $dirty1 &= -234881025;
                } else {
                    lineLimits3 = lineLimits;
                }
                Function2<? super Density, ? super Function0<TextLayoutResult>, Unit> function229 = i27 != 0 ? null : function27;
                if ((i & 1048576) != 0) {
                    function33 = function35;
                    function215 = function223;
                    scrollState3 = ScrollKt.rememberScrollState(0, $composer3, 0, 1);
                    $dirty2 &= -15;
                } else {
                    function33 = function35;
                    function215 = function223;
                    scrollState3 = scrollState;
                }
                if ((2097152 & i) != 0) {
                    shape3 = TextFieldDefaults.INSTANCE.getShape($composer3, 6);
                    $dirty2 &= -113;
                } else {
                    shape3 = shape;
                }
                if ((i & 4194304) != 0) {
                    shape4 = shape3;
                    colors3 = TextFieldDefaults.INSTANCE.colors($composer3, 6);
                    $dirty2 &= -897;
                } else {
                    shape4 = shape3;
                    colors3 = colors;
                }
                if ((i & 8388608) != 0) {
                    contentPadding3 = (function33 == null || (labelPosition2 instanceof TextFieldLabelPosition.Above)) ? TextFieldDefaults.m3127contentPaddingWithoutLabela9UjIt4$default(TextFieldDefaults.INSTANCE, 0.0f, 0.0f, 0.0f, 0.0f, 15, null) : TextFieldDefaults.m3126contentPaddingWithLabela9UjIt4$default(TextFieldDefaults.INSTANCE, 0.0f, 0.0f, 0.0f, 0.0f, 15, null);
                    $dirty2 &= -7169;
                } else {
                    contentPadding3 = contentPadding;
                }
                if (i28 != 0) {
                    shape5 = shape4;
                    contentPadding4 = contentPadding3;
                    readOnly4 = readOnly2;
                    interactionSource3 = null;
                    function216 = function228;
                    inputTransformation3 = inputTransformation4;
                    outputTransformation3 = outputTransformation4;
                    keyboardOptions3 = keyboardOptions4;
                    onKeyboardAction3 = onKeyboardAction4;
                    function217 = function229;
                    scrollState4 = scrollState3;
                    function34 = function33;
                    function218 = function215;
                    function219 = function224;
                    labelPosition4 = labelPosition2;
                    modifier4 = modifier2;
                    function220 = function226;
                    function221 = function227;
                    isError3 = isError4;
                    lineLimits4 = lineLimits3;
                    colors4 = colors3;
                    function222 = function225;
                    textStyle4 = textStyle2;
                } else {
                    shape5 = shape4;
                    contentPadding4 = contentPadding3;
                    readOnly4 = readOnly2;
                    function216 = function228;
                    inputTransformation3 = inputTransformation4;
                    outputTransformation3 = outputTransformation4;
                    keyboardOptions3 = keyboardOptions4;
                    onKeyboardAction3 = onKeyboardAction4;
                    function217 = function229;
                    scrollState4 = scrollState3;
                    function34 = function33;
                    function218 = function215;
                    interactionSource3 = interactionSource;
                    function219 = function224;
                    labelPosition4 = labelPosition2;
                    modifier4 = modifier2;
                    function220 = function226;
                    function221 = function227;
                    isError3 = isError4;
                    lineLimits4 = lineLimits3;
                    colors4 = colors3;
                    function222 = function225;
                    textStyle4 = textStyle2;
                }
            } else {
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
                    function34 = function3;
                    function218 = function2;
                    function219 = function22;
                    function222 = function23;
                    function220 = function24;
                    function221 = function25;
                    function216 = function26;
                    inputTransformation3 = inputTransformation;
                    outputTransformation3 = outputTransformation;
                    keyboardOptions3 = keyboardOptions;
                    onKeyboardAction3 = onKeyboardAction;
                    lineLimits4 = lineLimits;
                    function217 = function27;
                    scrollState4 = scrollState;
                    shape5 = shape;
                    colors4 = colors;
                    contentPadding4 = contentPadding;
                    interactionSource3 = interactionSource;
                    $dirty2 &= -7169;
                    readOnly4 = readOnly2;
                    textStyle4 = textStyle2;
                    labelPosition4 = labelPosition2;
                    modifier4 = modifier2;
                    isError3 = isError;
                } else {
                    function34 = function3;
                    function218 = function2;
                    function219 = function22;
                    function222 = function23;
                    function220 = function24;
                    function221 = function25;
                    function216 = function26;
                    inputTransformation3 = inputTransformation;
                    outputTransformation3 = outputTransformation;
                    keyboardOptions3 = keyboardOptions;
                    onKeyboardAction3 = onKeyboardAction;
                    lineLimits4 = lineLimits;
                    function217 = function27;
                    scrollState4 = scrollState;
                    shape5 = shape;
                    colors4 = colors;
                    contentPadding4 = contentPadding;
                    interactionSource3 = interactionSource;
                    readOnly4 = readOnly2;
                    textStyle4 = textStyle2;
                    labelPosition4 = labelPosition2;
                    modifier4 = modifier2;
                    isError3 = isError;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1717599650, $dirty, $dirty1, "androidx.compose.material3.TextField (TextField.kt:287)");
            }
            if (interactionSource3 == null) {
                $composer3.startReplaceGroup(1230824445);
                ComposerKt.sourceInformation($composer3, "289@15925L39");
                ComposerKt.sourceInformationMarkerStart($composer3, -1345769307, "CC(remember):TextField.kt#9igjgp");
                Object it$iv = $composer3.rememberedValue();
                interactionSource4 = interactionSource3;
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
                $composer3.startReplaceGroup(-1345769958);
                $composer3.endReplaceGroup();
                interactionSource5 = interactionSource4;
            }
            $composer3.startReplaceGroup(-1345763848);
            ComposerKt.sourceInformation($composer3, "*293@16149L25");
            long $this$takeOrElse_u2dDxMtmZc$iv = textStyle4.m7603getColor0d7_KjU();
            if (!($this$takeOrElse_u2dDxMtmZc$iv != 16)) {
                boolean focused = FocusInteractionKt.collectIsFocusedAsState(interactionSource5, $composer3, 0).getValue().booleanValue();
                $this$takeOrElse_u2dDxMtmZc$iv = colors4.m3124textColorXeAY9LY$material3(enabled2, isError3, focused);
            }
            $composer3.endReplaceGroup();
            long textColor = $this$takeOrElse_u2dDxMtmZc$iv;
            TextStyle mergedTextStyle = textStyle4.merge(new TextStyle(textColor, 0L, (FontWeight) null, (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, 0L, (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16777214, (DefaultConstructorMarker) null));
            boolean enabled4 = enabled2;
            CompositionLocalKt.CompositionLocalProvider(TextSelectionColorsKt.getLocalTextSelectionColors().provides(colors4.getTextSelectionColors()), ComposableLambdaKt.rememberComposableLambda(484558238, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TextFieldKt.TextField.1
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
                AnonymousClass1(boolean isError32, TextFieldColors colors42, TextFieldState textFieldState2, boolean enabled42, TextFieldLineLimits lineLimits42, OutputTransformation outputTransformation32, MutableInteractionSource interactionSource52, TextFieldLabelPosition labelPosition42, Function3<? super TextFieldLabelScope, ? super Composer, ? super Integer, Unit> function342, Function2<? super Composer, ? super Integer, Unit> function2182, Function2<? super Composer, ? super Integer, Unit> function2192, Function2<? super Composer, ? super Integer, Unit> function2222, Function2<? super Composer, ? super Integer, Unit> function2202, Function2<? super Composer, ? super Integer, Unit> function2212, Function2<? super Composer, ? super Integer, Unit> function2162, PaddingValues contentPadding42, boolean readOnly42, InputTransformation inputTransformation32, TextStyle mergedTextStyle2, KeyboardOptions keyboardOptions32, KeyboardActionHandler onKeyboardAction32, Function2<? super Density, ? super Function0<TextLayoutResult>, Unit> function2172, ScrollState scrollState42, Shape shape52) {
                    z = isError32;
                    textFieldColors = colors42;
                    textFieldState = textFieldState2;
                    z = enabled42;
                    textFieldLineLimits = lineLimits42;
                    outputTransformation = outputTransformation32;
                    mutableInteractionSource = interactionSource52;
                    textFieldLabelPosition = labelPosition42;
                    function3 = function342;
                    function2 = function2182;
                    function2 = function2192;
                    function2 = function2222;
                    function2 = function2202;
                    function2 = function2212;
                    function2 = function2162;
                    paddingValues = contentPadding42;
                    z = readOnly42;
                    inputTransformation = inputTransformation32;
                    textStyle = mergedTextStyle2;
                    keyboardOptions = keyboardOptions32;
                    keyboardActionHandler = onKeyboardAction32;
                    function2 = function2172;
                    scrollState = scrollState42;
                    shape = shape52;
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer $composer4, int $changed3) {
                    ComposerKt.sourceInformation($composer4, "C303@16564L38,338@18169L351,321@17398L1141,299@16421L2129:TextField.kt#uh7d8r");
                    if (!$composer4.shouldExecute(($changed3 & 3) != 2, $changed3 & 1)) {
                        $composer4.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(484558238, $changed3, -1, "androidx.compose.material3.TextField.<anonymous> (TextField.kt:299)");
                    }
                    Modifier modifier5 = modifier;
                    boolean z = z;
                    Strings.Companion companion = Strings.INSTANCE;
                    BasicTextFieldKt.BasicTextField(textFieldState, SizeKt.m1099defaultMinSizeVpY3zN4(TextFieldImplKt.defaultErrorSemantics(modifier5, z, Strings_androidKt.m3533getString2EP1pXo(Strings.m3454constructorimpl(androidx.compose.ui.R.string.default_error_message), $composer4, 0)), TextFieldDefaults.INSTANCE.m3143getMinWidthD9Ej5fM(), TextFieldDefaults.INSTANCE.m3142getMinHeightD9Ej5fM()), z, z, inputTransformation, textStyle, keyboardOptions, keyboardActionHandler, textFieldLineLimits, function2, mutableInteractionSource, new SolidColor(textFieldColors.m3074cursorColorvNxB06k$material3(z), null), outputTransformation, TextFieldDefaults.INSTANCE.decorator(textFieldState, z, textFieldLineLimits, outputTransformation, mutableInteractionSource, textFieldLabelPosition, function3, function2, function2, function2, function2, function2, function2, z, textFieldColors, paddingValues, ComposableLambdaKt.rememberComposableLambda(-2009308227, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TextFieldKt.TextField.1.1
                        final /* synthetic */ TextFieldColors $colors;
                        final /* synthetic */ boolean $enabled;
                        final /* synthetic */ MutableInteractionSource $interactionSource;
                        final /* synthetic */ boolean $isError;
                        final /* synthetic */ Shape $shape;

                        C00501(boolean z2, boolean z3, MutableInteractionSource mutableInteractionSource, TextFieldColors textFieldColors, Shape shape6) {
                            z = z2;
                            z = z3;
                            mutableInteractionSource = mutableInteractionSource;
                            textFieldColors = textFieldColors;
                            shape = shape6;
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                            invoke(composer, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer $composer5, int $changed4) {
                            ComposerKt.sourceInformation($composer5, "C339@18213L285:TextField.kt#uh7d8r");
                            if (!$composer5.shouldExecute(($changed4 & 3) != 2, $changed4 & 1)) {
                                $composer5.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-2009308227, $changed4, -1, "androidx.compose.material3.TextField.<anonymous>.<anonymous> (TextField.kt:339)");
                            }
                            TextFieldDefaults.INSTANCE.m3136Container4EFweAY(z, z, mutableInteractionSource, null, textFieldColors, shape, 0.0f, 0.0f, $composer5, 100663296, 200);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, $composer4, 54), $composer4, 0, 14155776, 0), scrollState, $composer4, 0, 0, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }

                /* JADX INFO: renamed from: androidx.compose.material3.TextFieldKt$TextField$1$1 */
                /* JADX INFO: compiled from: TextField.kt */
                @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
                static final class C00501 implements Function2<Composer, Integer, Unit> {
                    final /* synthetic */ TextFieldColors $colors;
                    final /* synthetic */ boolean $enabled;
                    final /* synthetic */ MutableInteractionSource $interactionSource;
                    final /* synthetic */ boolean $isError;
                    final /* synthetic */ Shape $shape;

                    C00501(boolean z2, boolean z3, MutableInteractionSource mutableInteractionSource, TextFieldColors textFieldColors, Shape shape6) {
                        z = z2;
                        z = z3;
                        mutableInteractionSource = mutableInteractionSource;
                        textFieldColors = textFieldColors;
                        shape = shape6;
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                        invoke(composer, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer $composer5, int $changed4) {
                        ComposerKt.sourceInformation($composer5, "C339@18213L285:TextField.kt#uh7d8r");
                        if (!$composer5.shouldExecute(($changed4 & 3) != 2, $changed4 & 1)) {
                            $composer5.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-2009308227, $changed4, -1, "androidx.compose.material3.TextField.<anonymous>.<anonymous> (TextField.kt:339)");
                        }
                        TextFieldDefaults.INSTANCE.m3136Container4EFweAY(z, z, mutableInteractionSource, null, textFieldColors, shape, 0.0f, 0.0f, $composer5, 100663296, 200);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }
            }, $composer3, 54), $composer3, ProvidedValue.$stable | 48);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier4;
            enabled3 = enabled42;
            lineLimits2 = lineLimits42;
            outputTransformation2 = outputTransformation32;
            labelPosition3 = labelPosition42;
            function28 = function2182;
            function210 = function2192;
            function211 = function2222;
            function212 = function2202;
            function213 = function2212;
            contentPadding2 = contentPadding42;
            inputTransformation2 = inputTransformation32;
            keyboardOptions2 = keyboardOptions32;
            onKeyboardAction2 = onKeyboardAction32;
            function29 = function2172;
            shape2 = shape52;
            interactionSource2 = interactionSource4;
            colors2 = colors42;
            function32 = function342;
            scrollState2 = scrollState42;
            textStyle3 = textStyle4;
            $composer2 = $composer3;
            isError2 = isError32;
            function214 = function2162;
            readOnly3 = readOnly42;
        } else {
            $composer3.skipToGroupEnd();
            function28 = function2;
            isError2 = isError;
            inputTransformation2 = inputTransformation;
            outputTransformation2 = outputTransformation;
            keyboardOptions2 = keyboardOptions;
            onKeyboardAction2 = onKeyboardAction;
            lineLimits2 = lineLimits;
            function29 = function27;
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
            function210 = function22;
            function211 = function23;
            function212 = function24;
            function213 = function25;
            function214 = function26;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.TextFieldKt$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TextFieldKt.TextField$lambda$2(state, modifier3, enabled3, readOnly3, textStyle3, labelPosition3, function32, function28, function210, function211, function212, function213, function214, isError2, inputTransformation2, outputTransformation2, keyboardOptions2, onKeyboardAction2, lineLimits2, function29, scrollState2, shape2, colors2, contentPadding2, interactionSource2, $changed, $changed1, $changed2, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.TextFieldKt$TextField$1 */
    /* JADX INFO: compiled from: TextField.kt */
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
        AnonymousClass1(boolean isError32, TextFieldColors colors42, TextFieldState textFieldState2, boolean enabled42, TextFieldLineLimits lineLimits42, OutputTransformation outputTransformation32, MutableInteractionSource interactionSource52, TextFieldLabelPosition labelPosition42, Function3<? super TextFieldLabelScope, ? super Composer, ? super Integer, Unit> function342, Function2<? super Composer, ? super Integer, Unit> function2182, Function2<? super Composer, ? super Integer, Unit> function2192, Function2<? super Composer, ? super Integer, Unit> function2222, Function2<? super Composer, ? super Integer, Unit> function2202, Function2<? super Composer, ? super Integer, Unit> function2212, Function2<? super Composer, ? super Integer, Unit> function2162, PaddingValues contentPadding42, boolean readOnly42, InputTransformation inputTransformation32, TextStyle mergedTextStyle2, KeyboardOptions keyboardOptions32, KeyboardActionHandler onKeyboardAction32, Function2<? super Density, ? super Function0<TextLayoutResult>, Unit> function2172, ScrollState scrollState42, Shape shape52) {
            z = isError32;
            textFieldColors = colors42;
            textFieldState = textFieldState2;
            z = enabled42;
            textFieldLineLimits = lineLimits42;
            outputTransformation = outputTransformation32;
            mutableInteractionSource = interactionSource52;
            textFieldLabelPosition = labelPosition42;
            function3 = function342;
            function2 = function2182;
            function2 = function2192;
            function2 = function2222;
            function2 = function2202;
            function2 = function2212;
            function2 = function2162;
            paddingValues = contentPadding42;
            z = readOnly42;
            inputTransformation = inputTransformation32;
            textStyle = mergedTextStyle2;
            keyboardOptions = keyboardOptions32;
            keyboardActionHandler = onKeyboardAction32;
            function2 = function2172;
            scrollState = scrollState42;
            shape = shape52;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer $composer4, int $changed3) {
            ComposerKt.sourceInformation($composer4, "C303@16564L38,338@18169L351,321@17398L1141,299@16421L2129:TextField.kt#uh7d8r");
            if (!$composer4.shouldExecute(($changed3 & 3) != 2, $changed3 & 1)) {
                $composer4.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(484558238, $changed3, -1, "androidx.compose.material3.TextField.<anonymous> (TextField.kt:299)");
            }
            Modifier modifier5 = modifier;
            boolean z = z;
            Strings.Companion companion = Strings.INSTANCE;
            BasicTextFieldKt.BasicTextField(textFieldState, SizeKt.m1099defaultMinSizeVpY3zN4(TextFieldImplKt.defaultErrorSemantics(modifier5, z, Strings_androidKt.m3533getString2EP1pXo(Strings.m3454constructorimpl(androidx.compose.ui.R.string.default_error_message), $composer4, 0)), TextFieldDefaults.INSTANCE.m3143getMinWidthD9Ej5fM(), TextFieldDefaults.INSTANCE.m3142getMinHeightD9Ej5fM()), z, z, inputTransformation, textStyle, keyboardOptions, keyboardActionHandler, textFieldLineLimits, function2, mutableInteractionSource, new SolidColor(textFieldColors.m3074cursorColorvNxB06k$material3(z), null), outputTransformation, TextFieldDefaults.INSTANCE.decorator(textFieldState, z, textFieldLineLimits, outputTransformation, mutableInteractionSource, textFieldLabelPosition, function3, function2, function2, function2, function2, function2, function2, z, textFieldColors, paddingValues, ComposableLambdaKt.rememberComposableLambda(-2009308227, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TextFieldKt.TextField.1.1
                final /* synthetic */ TextFieldColors $colors;
                final /* synthetic */ boolean $enabled;
                final /* synthetic */ MutableInteractionSource $interactionSource;
                final /* synthetic */ boolean $isError;
                final /* synthetic */ Shape $shape;

                C00501(boolean z2, boolean z3, MutableInteractionSource mutableInteractionSource, TextFieldColors textFieldColors, Shape shape6) {
                    z = z2;
                    z = z3;
                    mutableInteractionSource = mutableInteractionSource;
                    textFieldColors = textFieldColors;
                    shape = shape6;
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer $composer5, int $changed4) {
                    ComposerKt.sourceInformation($composer5, "C339@18213L285:TextField.kt#uh7d8r");
                    if (!$composer5.shouldExecute(($changed4 & 3) != 2, $changed4 & 1)) {
                        $composer5.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-2009308227, $changed4, -1, "androidx.compose.material3.TextField.<anonymous>.<anonymous> (TextField.kt:339)");
                    }
                    TextFieldDefaults.INSTANCE.m3136Container4EFweAY(z, z, mutableInteractionSource, null, textFieldColors, shape, 0.0f, 0.0f, $composer5, 100663296, 200);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, $composer4, 54), $composer4, 0, 14155776, 0), scrollState, $composer4, 0, 0, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }

        /* JADX INFO: renamed from: androidx.compose.material3.TextFieldKt$TextField$1$1 */
        /* JADX INFO: compiled from: TextField.kt */
        @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
        static final class C00501 implements Function2<Composer, Integer, Unit> {
            final /* synthetic */ TextFieldColors $colors;
            final /* synthetic */ boolean $enabled;
            final /* synthetic */ MutableInteractionSource $interactionSource;
            final /* synthetic */ boolean $isError;
            final /* synthetic */ Shape $shape;

            C00501(boolean z2, boolean z3, MutableInteractionSource mutableInteractionSource, TextFieldColors textFieldColors, Shape shape6) {
                z = z2;
                z = z3;
                mutableInteractionSource = mutableInteractionSource;
                textFieldColors = textFieldColors;
                shape = shape6;
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                invoke(composer, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Composer $composer5, int $changed4) {
                ComposerKt.sourceInformation($composer5, "C339@18213L285:TextField.kt#uh7d8r");
                if (!$composer5.shouldExecute(($changed4 & 3) != 2, $changed4 & 1)) {
                    $composer5.skipToGroupEnd();
                    return;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-2009308227, $changed4, -1, "androidx.compose.material3.TextField.<anonymous>.<anonymous> (TextField.kt:339)");
                }
                TextFieldDefaults.INSTANCE.m3136Container4EFweAY(z, z, mutableInteractionSource, null, textFieldColors, shape, 0.0f, 0.0f, $composer5, 100663296, 200);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
        }
    }

    public static final void TextField(final String value, final Function1<? super String, Unit> function1, Modifier modifier, boolean enabled, boolean readOnly, TextStyle textStyle, Function2<? super Composer, ? super Integer, Unit> function2, Function2<? super Composer, ? super Integer, Unit> function22, Function2<? super Composer, ? super Integer, Unit> function23, Function2<? super Composer, ? super Integer, Unit> function24, Function2<? super Composer, ? super Integer, Unit> function25, Function2<? super Composer, ? super Integer, Unit> function26, Function2<? super Composer, ? super Integer, Unit> function27, boolean isError, VisualTransformation visualTransformation, KeyboardOptions keyboardOptions, KeyboardActions keyboardActions, boolean singleLine, int maxLines, int minLines, MutableInteractionSource interactionSource, Shape shape, TextFieldColors colors, Composer $composer, final int $changed, final int $changed1, final int $changed2, final int i) {
        String str;
        Function1<? super String, Unit> function12;
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
        final VisualTransformation visualTransformation2;
        final KeyboardOptions keyboardOptions2;
        final KeyboardActions keyboardActions2;
        final boolean readOnly3;
        final int maxLines2;
        final int minLines2;
        final MutableInteractionSource interactionSource2;
        final Shape shape2;
        final TextFieldColors colors2;
        Composer $composer2;
        final boolean enabled3;
        final boolean readOnly4;
        final TextStyle textStyle3;
        final Function2<? super Composer, ? super Integer, Unit> function212;
        final Function2<? super Composer, ? super Integer, Unit> function213;
        final Function2<? super Composer, ? super Integer, Unit> function214;
        final Function2<? super Composer, ? super Integer, Unit> function215;
        final boolean isError2;
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
        boolean isError4;
        MutableInteractionSource interactionSource4;
        TextFieldColors colors3;
        Modifier modifier3;
        VisualTransformation visualTransformation4;
        KeyboardOptions keyboardOptions4;
        boolean readOnly5;
        MutableInteractionSource interactionSource5;
        MutableInteractionSource interactionSource6;
        int i10;
        Composer $composer3 = $composer.startRestartGroup(-154966360);
        ComposerKt.sourceInformation($composer3, "C(TextField)N(value,onValueChange,modifier,enabled,readOnly,textStyle,label,placeholder,leadingIcon,trailingIcon,prefix,suffix,supportingText,isError,visualTransformation,keyboardOptions,keyboardActions,singleLine,maxLines,minLines,interactionSource,shape,colors)453@24857L1959,453@24770L2046:TextField.kt#uh7d8r");
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
            function12 = function1;
        } else if (($changed & 48) == 0) {
            function12 = function1;
            $dirty |= $composer3.changedInstance(function12) ? 32 : 16;
        } else {
            function12 = function1;
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
            ComposerKt.sourceInformation($composer3, "424@23425L7,440@24221L5,441@24276L8");
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
                    isError4 = isError;
                    visualTransformation4 = visualTransformation;
                    keyboardOptions4 = keyboardOptions;
                    keyboardActions3 = keyboardActions;
                    singleLine2 = singleLine;
                    maxLines3 = maxLines;
                    minLines3 = minLines;
                    interactionSource4 = interactionSource;
                    shape4 = shape;
                    $dirty2 &= -897;
                    readOnly5 = readOnly2;
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
                    isError4 = isError;
                    visualTransformation4 = visualTransformation;
                    keyboardOptions4 = keyboardOptions;
                    keyboardActions3 = keyboardActions;
                    singleLine2 = singleLine;
                    maxLines3 = maxLines;
                    minLines3 = minLines;
                    interactionSource4 = interactionSource;
                    shape4 = shape;
                    readOnly5 = readOnly2;
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
                    shape3 = TextFieldDefaults.INSTANCE.getShape($composer3, 6);
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
                    isError4 = isError3;
                    interactionSource4 = interactionSource3;
                    colors3 = colors;
                    modifier3 = modifier2;
                    visualTransformation4 = visualTransformation3;
                    keyboardOptions4 = keyboardOptions3;
                    readOnly5 = readOnly2;
                } else {
                    function223 = function229;
                    function210 = function222;
                    shape4 = shape3;
                    $dirty2 &= -897;
                    function224 = function218;
                    function225 = function219;
                    function226 = function28;
                    function227 = function220;
                    function228 = function221;
                    isError4 = isError3;
                    interactionSource4 = interactionSource3;
                    colors3 = TextFieldDefaults.INSTANCE.colors($composer3, 6);
                    textStyle4 = textStyle2;
                    visualTransformation4 = visualTransformation3;
                    keyboardOptions4 = keyboardOptions3;
                    modifier3 = modifier2;
                    readOnly5 = readOnly2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-154966360, $dirty, $dirty1, "androidx.compose.material3.TextField (TextField.kt:442)");
            }
            if (interactionSource4 == null) {
                $composer3.startReplaceGroup(488210003);
                ComposerKt.sourceInformation($composer3, "444@24371L39");
                ComposerKt.sourceInformationMarkerStart($composer3, 1401222031, "CC(remember):TextField.kt#9igjgp");
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
                $composer3.startReplaceGroup(1401221380);
                $composer3.endReplaceGroup();
                interactionSource6 = interactionSource5;
            }
            $composer3.startReplaceGroup(1401227490);
            ComposerKt.sourceInformation($composer3, "*448@24595L25");
            long $this$takeOrElse_u2dDxMtmZc$iv = textStyle4.m7603getColor0d7_KjU();
            if (!($this$takeOrElse_u2dDxMtmZc$iv != 16)) {
                boolean focused = FocusInteractionKt.collectIsFocusedAsState(interactionSource6, $composer3, 0).getValue().booleanValue();
                $this$takeOrElse_u2dDxMtmZc$iv = colors3.m3124textColorXeAY9LY$material3(enabled2, isError4, focused);
            }
            $composer3.endReplaceGroup();
            long textColor = $this$takeOrElse_u2dDxMtmZc$iv;
            TextStyle mergedTextStyle = textStyle4.merge(new TextStyle(textColor, 0L, (FontWeight) null, (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, 0L, (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16777214, (DefaultConstructorMarker) null));
            boolean enabled4 = enabled2;
            CompositionLocalKt.CompositionLocalProvider(TextSelectionColorsKt.getLocalTextSelectionColors().provides(colors3.getTextSelectionColors()), ComposableLambdaKt.rememberComposableLambda(1459735400, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TextFieldKt.TextField.3
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
                AnonymousClass3(boolean isError42, TextFieldColors colors32, String str2, Function1<? super String, Unit> function122, boolean enabled42, boolean readOnly52, TextStyle mergedTextStyle2, KeyboardOptions keyboardOptions42, KeyboardActions keyboardActions32, boolean singleLine22, int maxLines32, int minLines32, VisualTransformation visualTransformation42, MutableInteractionSource interactionSource62, Function2<? super Composer, ? super Integer, Unit> function2262, Function2<? super Composer, ? super Integer, Unit> function2232, Function2<? super Composer, ? super Integer, Unit> function2102, Function2<? super Composer, ? super Integer, Unit> function2242, Function2<? super Composer, ? super Integer, Unit> function2252, Function2<? super Composer, ? super Integer, Unit> function2272, Function2<? super Composer, ? super Integer, Unit> function2282, Shape shape42) {
                    z = isError42;
                    textFieldColors = colors32;
                    str = str2;
                    function1 = function122;
                    z = enabled42;
                    z = readOnly52;
                    textStyle = mergedTextStyle2;
                    keyboardOptions = keyboardOptions42;
                    keyboardActions = keyboardActions32;
                    z = singleLine22;
                    i = maxLines32;
                    i = minLines32;
                    visualTransformation = visualTransformation42;
                    mutableInteractionSource = interactionSource62;
                    function2 = function2262;
                    function2 = function2232;
                    function2 = function2102;
                    function2 = function2242;
                    function2 = function2252;
                    function2 = function2272;
                    function2 = function2282;
                    shape = shape42;
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer $composer4, int $changed3) {
                    ComposerKt.sourceInformation($composer4, "C458@25010L38,476@25814L985,454@24867L1943:TextField.kt#uh7d8r");
                    if (!$composer4.shouldExecute(($changed3 & 3) != 2, $changed3 & 1)) {
                        $composer4.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1459735400, $changed3, -1, "androidx.compose.material3.TextField.<anonymous> (TextField.kt:454)");
                    }
                    Modifier modifier4 = modifier;
                    boolean z = z;
                    Strings.Companion companion = Strings.INSTANCE;
                    BasicTextFieldKt.BasicTextField(str, function1, SizeKt.m1099defaultMinSizeVpY3zN4(TextFieldImplKt.defaultErrorSemantics(modifier4, z, Strings_androidKt.m3533getString2EP1pXo(Strings.m3454constructorimpl(androidx.compose.ui.R.string.default_error_message), $composer4, 0)), TextFieldDefaults.INSTANCE.m3143getMinWidthD9Ej5fM(), TextFieldDefaults.INSTANCE.m3142getMinHeightD9Ej5fM()), z, z, textStyle, keyboardOptions, keyboardActions, z, i, i, visualTransformation, (Function1<? super TextLayoutResult, Unit>) null, mutableInteractionSource, new SolidColor(textFieldColors.m3074cursorColorvNxB06k$material3(z), null), ComposableLambdaKt.rememberComposableLambda(1451491557, true, new Function3<Function2<? super Composer, ? super Integer, ? extends Unit>, Composer, Integer, Unit>() { // from class: androidx.compose.material3.TextFieldKt.TextField.3.1
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
                        AnonymousClass1(String str2, boolean z2, boolean z3, VisualTransformation visualTransformation5, MutableInteractionSource mutableInteractionSource, boolean z4, Function2<? super Composer, ? super Integer, Unit> function230, Function2<? super Composer, ? super Integer, Unit> function231, Function2<? super Composer, ? super Integer, Unit> function232, Function2<? super Composer, ? super Integer, Unit> function233, Function2<? super Composer, ? super Integer, Unit> function234, Function2<? super Composer, ? super Integer, Unit> function235, Function2<? super Composer, ? super Integer, Unit> function236, Shape shape5, TextFieldColors textFieldColors) {
                            str = str2;
                            z = z2;
                            z = z3;
                            visualTransformation = visualTransformation5;
                            mutableInteractionSource = mutableInteractionSource;
                            z = z4;
                            function2 = function230;
                            function2 = function231;
                            function2 = function232;
                            function2 = function233;
                            function2 = function234;
                            function2 = function235;
                            function2 = function236;
                            shape = shape5;
                            textFieldColors = textFieldColors;
                        }

                        @Override // kotlin.jvm.functions.Function3
                        public /* bridge */ /* synthetic */ Unit invoke(Function2<? super Composer, ? super Integer, ? extends Unit> function230, Composer composer, Integer num) {
                            invoke((Function2<? super Composer, ? super Integer, Unit>) function230, composer, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Function2<? super Composer, ? super Integer, Unit> function230, Composer $composer5, int $changed4) {
                            Function2<? super Composer, ? super Integer, Unit> function231;
                            ComposerKt.sourceInformation($composer5, "CN(innerTextField)478@25969L812:TextField.kt#uh7d8r");
                            int $dirty3 = $changed4;
                            if (($changed4 & 6) == 0) {
                                function231 = function230;
                                $dirty3 |= $composer5.changedInstance(function231) ? 4 : 2;
                            } else {
                                function231 = function230;
                            }
                            if ($composer5.shouldExecute(($dirty3 & 19) != 18, $dirty3 & 1)) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(1451491557, $dirty3, -1, "androidx.compose.material3.TextField.<anonymous>.<anonymous> (TextField.kt:478)");
                                }
                                TextFieldDefaults.INSTANCE.DecorationBox(str, function231, z, z, visualTransformation, mutableInteractionSource, z, function2, function2, function2, function2, function2, function2, function2, shape, textFieldColors, null, null, $composer5, ($dirty3 << 3) & 112, 100663296, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            $composer5.skipToGroupEnd();
                        }
                    }, $composer4, 54), $composer4, 0, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 4096);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }

                /* JADX INFO: renamed from: androidx.compose.material3.TextFieldKt$TextField$3$1 */
                /* JADX INFO: compiled from: TextField.kt */
                @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
                static final class AnonymousClass1 implements Function3<Function2<? super Composer, ? super Integer, ? extends Unit>, Composer, Integer, Unit> {
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
                    AnonymousClass1(String str2, boolean z2, boolean z3, VisualTransformation visualTransformation5, MutableInteractionSource mutableInteractionSource, boolean z4, Function2<? super Composer, ? super Integer, Unit> function230, Function2<? super Composer, ? super Integer, Unit> function231, Function2<? super Composer, ? super Integer, Unit> function232, Function2<? super Composer, ? super Integer, Unit> function233, Function2<? super Composer, ? super Integer, Unit> function234, Function2<? super Composer, ? super Integer, Unit> function235, Function2<? super Composer, ? super Integer, Unit> function236, Shape shape5, TextFieldColors textFieldColors) {
                        str = str2;
                        z = z2;
                        z = z3;
                        visualTransformation = visualTransformation5;
                        mutableInteractionSource = mutableInteractionSource;
                        z = z4;
                        function2 = function230;
                        function2 = function231;
                        function2 = function232;
                        function2 = function233;
                        function2 = function234;
                        function2 = function235;
                        function2 = function236;
                        shape = shape5;
                        textFieldColors = textFieldColors;
                    }

                    @Override // kotlin.jvm.functions.Function3
                    public /* bridge */ /* synthetic */ Unit invoke(Function2<? super Composer, ? super Integer, ? extends Unit> function230, Composer composer, Integer num) {
                        invoke((Function2<? super Composer, ? super Integer, Unit>) function230, composer, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Function2<? super Composer, ? super Integer, Unit> function230, Composer $composer5, int $changed4) {
                        Function2<? super Composer, ? super Integer, Unit> function231;
                        ComposerKt.sourceInformation($composer5, "CN(innerTextField)478@25969L812:TextField.kt#uh7d8r");
                        int $dirty3 = $changed4;
                        if (($changed4 & 6) == 0) {
                            function231 = function230;
                            $dirty3 |= $composer5.changedInstance(function231) ? 4 : 2;
                        } else {
                            function231 = function230;
                        }
                        if ($composer5.shouldExecute(($dirty3 & 19) != 18, $dirty3 & 1)) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1451491557, $dirty3, -1, "androidx.compose.material3.TextField.<anonymous>.<anonymous> (TextField.kt:478)");
                            }
                            TextFieldDefaults.INSTANCE.DecorationBox(str, function231, z, z, visualTransformation, mutableInteractionSource, z, function2, function2, function2, function2, function2, function2, function2, shape, textFieldColors, null, null, $composer5, ($dirty3 << 3) & 112, 100663296, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        $composer5.skipToGroupEnd();
                    }
                }
            }, $composer3, 54), $composer3, ProvidedValue.$stable | 48);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier2 = modifier3;
            keyboardOptions2 = keyboardOptions42;
            maxLines2 = maxLines32;
            minLines2 = minLines32;
            visualTransformation2 = visualTransformation42;
            function29 = function2232;
            function213 = function2242;
            function214 = function2252;
            function215 = function2272;
            colors2 = colors32;
            readOnly4 = readOnly52;
            readOnly3 = singleLine22;
            function212 = function2262;
            shape2 = shape42;
            textStyle3 = textStyle4;
            $composer2 = $composer3;
            isError2 = isError42;
            enabled3 = enabled42;
            keyboardActions2 = keyboardActions32;
            function211 = function2282;
            interactionSource2 = interactionSource5;
        } else {
            $composer3.skipToGroupEnd();
            function29 = function22;
            function2102 = function23;
            function211 = function27;
            visualTransformation2 = visualTransformation;
            keyboardOptions2 = keyboardOptions;
            keyboardActions2 = keyboardActions;
            readOnly3 = singleLine;
            maxLines2 = maxLines;
            minLines2 = minLines;
            interactionSource2 = interactionSource;
            shape2 = shape;
            colors2 = colors;
            $composer2 = $composer3;
            enabled3 = enabled2;
            readOnly4 = readOnly2;
            textStyle3 = textStyle2;
            function212 = function28;
            function213 = function24;
            function214 = function25;
            function215 = function26;
            isError2 = isError;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Modifier modifier4 = modifier2;
            final Function2<? super Composer, ? super Integer, Unit> function230 = function2102;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.TextFieldKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TextFieldKt.TextField$lambda$5(value, function1, modifier4, enabled3, readOnly4, textStyle3, function212, function29, function230, function213, function214, function215, function211, isError2, visualTransformation2, keyboardOptions2, keyboardActions2, readOnly3, maxLines2, minLines2, interactionSource2, shape2, colors2, $changed, $changed1, $changed2, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.TextFieldKt$TextField$3 */
    /* JADX INFO: compiled from: TextField.kt */
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
        AnonymousClass3(boolean isError42, TextFieldColors colors32, String str2, Function1<? super String, Unit> function122, boolean enabled42, boolean readOnly52, TextStyle mergedTextStyle2, KeyboardOptions keyboardOptions42, KeyboardActions keyboardActions32, boolean singleLine22, int maxLines32, int minLines32, VisualTransformation visualTransformation42, MutableInteractionSource interactionSource62, Function2<? super Composer, ? super Integer, Unit> function2262, Function2<? super Composer, ? super Integer, Unit> function2232, Function2<? super Composer, ? super Integer, Unit> function2102, Function2<? super Composer, ? super Integer, Unit> function2242, Function2<? super Composer, ? super Integer, Unit> function2252, Function2<? super Composer, ? super Integer, Unit> function2272, Function2<? super Composer, ? super Integer, Unit> function2282, Shape shape42) {
            z = isError42;
            textFieldColors = colors32;
            str = str2;
            function1 = function122;
            z = enabled42;
            z = readOnly52;
            textStyle = mergedTextStyle2;
            keyboardOptions = keyboardOptions42;
            keyboardActions = keyboardActions32;
            z = singleLine22;
            i = maxLines32;
            i = minLines32;
            visualTransformation = visualTransformation42;
            mutableInteractionSource = interactionSource62;
            function2 = function2262;
            function2 = function2232;
            function2 = function2102;
            function2 = function2242;
            function2 = function2252;
            function2 = function2272;
            function2 = function2282;
            shape = shape42;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer $composer4, int $changed3) {
            ComposerKt.sourceInformation($composer4, "C458@25010L38,476@25814L985,454@24867L1943:TextField.kt#uh7d8r");
            if (!$composer4.shouldExecute(($changed3 & 3) != 2, $changed3 & 1)) {
                $composer4.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1459735400, $changed3, -1, "androidx.compose.material3.TextField.<anonymous> (TextField.kt:454)");
            }
            Modifier modifier4 = modifier;
            boolean z = z;
            Strings.Companion companion = Strings.INSTANCE;
            BasicTextFieldKt.BasicTextField(str, function1, SizeKt.m1099defaultMinSizeVpY3zN4(TextFieldImplKt.defaultErrorSemantics(modifier4, z, Strings_androidKt.m3533getString2EP1pXo(Strings.m3454constructorimpl(androidx.compose.ui.R.string.default_error_message), $composer4, 0)), TextFieldDefaults.INSTANCE.m3143getMinWidthD9Ej5fM(), TextFieldDefaults.INSTANCE.m3142getMinHeightD9Ej5fM()), z, z, textStyle, keyboardOptions, keyboardActions, z, i, i, visualTransformation, (Function1<? super TextLayoutResult, Unit>) null, mutableInteractionSource, new SolidColor(textFieldColors.m3074cursorColorvNxB06k$material3(z), null), ComposableLambdaKt.rememberComposableLambda(1451491557, true, new Function3<Function2<? super Composer, ? super Integer, ? extends Unit>, Composer, Integer, Unit>() { // from class: androidx.compose.material3.TextFieldKt.TextField.3.1
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
                AnonymousClass1(String str2, boolean z2, boolean z3, VisualTransformation visualTransformation5, MutableInteractionSource mutableInteractionSource, boolean z4, Function2<? super Composer, ? super Integer, Unit> function230, Function2<? super Composer, ? super Integer, Unit> function231, Function2<? super Composer, ? super Integer, Unit> function232, Function2<? super Composer, ? super Integer, Unit> function233, Function2<? super Composer, ? super Integer, Unit> function234, Function2<? super Composer, ? super Integer, Unit> function235, Function2<? super Composer, ? super Integer, Unit> function236, Shape shape5, TextFieldColors textFieldColors) {
                    str = str2;
                    z = z2;
                    z = z3;
                    visualTransformation = visualTransformation5;
                    mutableInteractionSource = mutableInteractionSource;
                    z = z4;
                    function2 = function230;
                    function2 = function231;
                    function2 = function232;
                    function2 = function233;
                    function2 = function234;
                    function2 = function235;
                    function2 = function236;
                    shape = shape5;
                    textFieldColors = textFieldColors;
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(Function2<? super Composer, ? super Integer, ? extends Unit> function230, Composer composer, Integer num) {
                    invoke((Function2<? super Composer, ? super Integer, Unit>) function230, composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Function2<? super Composer, ? super Integer, Unit> function230, Composer $composer5, int $changed4) {
                    Function2<? super Composer, ? super Integer, Unit> function231;
                    ComposerKt.sourceInformation($composer5, "CN(innerTextField)478@25969L812:TextField.kt#uh7d8r");
                    int $dirty3 = $changed4;
                    if (($changed4 & 6) == 0) {
                        function231 = function230;
                        $dirty3 |= $composer5.changedInstance(function231) ? 4 : 2;
                    } else {
                        function231 = function230;
                    }
                    if ($composer5.shouldExecute(($dirty3 & 19) != 18, $dirty3 & 1)) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1451491557, $dirty3, -1, "androidx.compose.material3.TextField.<anonymous>.<anonymous> (TextField.kt:478)");
                        }
                        TextFieldDefaults.INSTANCE.DecorationBox(str, function231, z, z, visualTransformation, mutableInteractionSource, z, function2, function2, function2, function2, function2, function2, function2, shape, textFieldColors, null, null, $composer5, ($dirty3 << 3) & 112, 100663296, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer5.skipToGroupEnd();
                }
            }, $composer4, 54), $composer4, 0, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 4096);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }

        /* JADX INFO: renamed from: androidx.compose.material3.TextFieldKt$TextField$3$1 */
        /* JADX INFO: compiled from: TextField.kt */
        @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
        static final class AnonymousClass1 implements Function3<Function2<? super Composer, ? super Integer, ? extends Unit>, Composer, Integer, Unit> {
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
            AnonymousClass1(String str2, boolean z2, boolean z3, VisualTransformation visualTransformation5, MutableInteractionSource mutableInteractionSource, boolean z4, Function2<? super Composer, ? super Integer, Unit> function230, Function2<? super Composer, ? super Integer, Unit> function231, Function2<? super Composer, ? super Integer, Unit> function232, Function2<? super Composer, ? super Integer, Unit> function233, Function2<? super Composer, ? super Integer, Unit> function234, Function2<? super Composer, ? super Integer, Unit> function235, Function2<? super Composer, ? super Integer, Unit> function236, Shape shape5, TextFieldColors textFieldColors) {
                str = str2;
                z = z2;
                z = z3;
                visualTransformation = visualTransformation5;
                mutableInteractionSource = mutableInteractionSource;
                z = z4;
                function2 = function230;
                function2 = function231;
                function2 = function232;
                function2 = function233;
                function2 = function234;
                function2 = function235;
                function2 = function236;
                shape = shape5;
                textFieldColors = textFieldColors;
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Unit invoke(Function2<? super Composer, ? super Integer, ? extends Unit> function230, Composer composer, Integer num) {
                invoke((Function2<? super Composer, ? super Integer, Unit>) function230, composer, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Function2<? super Composer, ? super Integer, Unit> function230, Composer $composer5, int $changed4) {
                Function2<? super Composer, ? super Integer, Unit> function231;
                ComposerKt.sourceInformation($composer5, "CN(innerTextField)478@25969L812:TextField.kt#uh7d8r");
                int $dirty3 = $changed4;
                if (($changed4 & 6) == 0) {
                    function231 = function230;
                    $dirty3 |= $composer5.changedInstance(function231) ? 4 : 2;
                } else {
                    function231 = function230;
                }
                if ($composer5.shouldExecute(($dirty3 & 19) != 18, $dirty3 & 1)) {
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1451491557, $dirty3, -1, "androidx.compose.material3.TextField.<anonymous>.<anonymous> (TextField.kt:478)");
                    }
                    TextFieldDefaults.INSTANCE.DecorationBox(str, function231, z, z, visualTransformation, mutableInteractionSource, z, function2, function2, function2, function2, function2, function2, function2, shape, textFieldColors, null, null, $composer5, ($dirty3 << 3) & 112, 100663296, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                        return;
                    }
                    return;
                }
                $composer5.skipToGroupEnd();
            }
        }
    }

    public static final void TextField(final TextFieldValue value, final Function1<? super TextFieldValue, Unit> function1, Modifier modifier, boolean enabled, boolean readOnly, TextStyle textStyle, Function2<? super Composer, ? super Integer, Unit> function2, Function2<? super Composer, ? super Integer, Unit> function22, Function2<? super Composer, ? super Integer, Unit> function23, Function2<? super Composer, ? super Integer, Unit> function24, Function2<? super Composer, ? super Integer, Unit> function25, Function2<? super Composer, ? super Integer, Unit> function26, Function2<? super Composer, ? super Integer, Unit> function27, boolean isError, VisualTransformation visualTransformation, KeyboardOptions keyboardOptions, KeyboardActions keyboardActions, boolean singleLine, int maxLines, int minLines, MutableInteractionSource interactionSource, Shape shape, TextFieldColors colors, Composer $composer, final int $changed, final int $changed1, final int $changed2, final int i) {
        TextFieldValue textFieldValue;
        Function1<? super TextFieldValue, Unit> function12;
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
        final VisualTransformation visualTransformation2;
        final KeyboardOptions keyboardOptions2;
        final KeyboardActions keyboardActions2;
        final boolean readOnly3;
        final int maxLines2;
        final int minLines2;
        final MutableInteractionSource interactionSource2;
        final Shape shape2;
        final TextFieldColors colors2;
        Composer $composer2;
        final boolean enabled3;
        final boolean readOnly4;
        final TextStyle textStyle3;
        final Function2<? super Composer, ? super Integer, Unit> function212;
        final Function2<? super Composer, ? super Integer, Unit> function213;
        final Function2<? super Composer, ? super Integer, Unit> function214;
        final Function2<? super Composer, ? super Integer, Unit> function215;
        final boolean isError2;
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
        boolean isError4;
        MutableInteractionSource interactionSource4;
        TextFieldColors colors3;
        Modifier modifier3;
        VisualTransformation visualTransformation4;
        KeyboardOptions keyboardOptions4;
        boolean readOnly5;
        MutableInteractionSource interactionSource5;
        MutableInteractionSource interactionSource6;
        int i10;
        Composer $composer3 = $composer.startRestartGroup(-1126989771);
        ComposerKt.sourceInformation($composer3, "C(TextField)N(value,onValueChange,modifier,enabled,readOnly,textStyle,label,placeholder,leadingIcon,trailingIcon,prefix,suffix,supportingText,isError,visualTransformation,keyboardOptions,keyboardActions,singleLine,maxLines,minLines,interactionSource,shape,colors)603@33216L1964,603@33129L2051:TextField.kt#uh7d8r");
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
            function12 = function1;
        } else if (($changed & 48) == 0) {
            function12 = function1;
            $dirty |= $composer3.changedInstance(function12) ? 32 : 16;
        } else {
            function12 = function1;
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
            ComposerKt.sourceInformation($composer3, "574@31784L7,590@32580L5,591@32635L8");
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
                    isError4 = isError;
                    visualTransformation4 = visualTransformation;
                    keyboardOptions4 = keyboardOptions;
                    keyboardActions3 = keyboardActions;
                    singleLine2 = singleLine;
                    maxLines3 = maxLines;
                    minLines3 = minLines;
                    interactionSource4 = interactionSource;
                    shape4 = shape;
                    $dirty2 &= -897;
                    readOnly5 = readOnly2;
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
                    isError4 = isError;
                    visualTransformation4 = visualTransformation;
                    keyboardOptions4 = keyboardOptions;
                    keyboardActions3 = keyboardActions;
                    singleLine2 = singleLine;
                    maxLines3 = maxLines;
                    minLines3 = minLines;
                    interactionSource4 = interactionSource;
                    shape4 = shape;
                    readOnly5 = readOnly2;
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
                    shape3 = TextFieldDefaults.INSTANCE.getShape($composer3, 6);
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
                    isError4 = isError3;
                    interactionSource4 = interactionSource3;
                    colors3 = colors;
                    modifier3 = modifier2;
                    visualTransformation4 = visualTransformation3;
                    keyboardOptions4 = keyboardOptions3;
                    readOnly5 = readOnly2;
                } else {
                    function223 = function229;
                    function210 = function222;
                    shape4 = shape3;
                    $dirty2 &= -897;
                    function224 = function218;
                    function225 = function219;
                    function226 = function28;
                    function227 = function220;
                    function228 = function221;
                    isError4 = isError3;
                    interactionSource4 = interactionSource3;
                    colors3 = TextFieldDefaults.INSTANCE.colors($composer3, 6);
                    textStyle4 = textStyle2;
                    visualTransformation4 = visualTransformation3;
                    keyboardOptions4 = keyboardOptions3;
                    modifier3 = modifier2;
                    readOnly5 = readOnly2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1126989771, $dirty, $dirty1, "androidx.compose.material3.TextField (TextField.kt:592)");
            }
            if (interactionSource4 == null) {
                $composer3.startReplaceGroup(-391701594);
                ComposerKt.sourceInformation($composer3, "594@32730L39");
                ComposerKt.sourceInformationMarkerStart($composer3, -705372196, "CC(remember):TextField.kt#9igjgp");
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
                $composer3.startReplaceGroup(-705372847);
                $composer3.endReplaceGroup();
                interactionSource6 = interactionSource5;
            }
            $composer3.startReplaceGroup(-705366737);
            ComposerKt.sourceInformation($composer3, "*598@32954L25");
            long $this$takeOrElse_u2dDxMtmZc$iv = textStyle4.m7603getColor0d7_KjU();
            if (!($this$takeOrElse_u2dDxMtmZc$iv != 16)) {
                boolean focused = FocusInteractionKt.collectIsFocusedAsState(interactionSource6, $composer3, 0).getValue().booleanValue();
                $this$takeOrElse_u2dDxMtmZc$iv = colors3.m3124textColorXeAY9LY$material3(enabled2, isError4, focused);
            }
            $composer3.endReplaceGroup();
            long textColor = $this$takeOrElse_u2dDxMtmZc$iv;
            TextStyle mergedTextStyle = textStyle4.merge(new TextStyle(textColor, 0L, (FontWeight) null, (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, 0L, (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16777214, (DefaultConstructorMarker) null));
            boolean enabled4 = enabled2;
            CompositionLocalKt.CompositionLocalProvider(TextSelectionColorsKt.getLocalTextSelectionColors().provides(colors3.getTextSelectionColors()), ComposableLambdaKt.rememberComposableLambda(-306109195, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TextFieldKt.TextField.5
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
                AnonymousClass5(boolean isError42, TextFieldColors colors32, TextFieldValue textFieldValue2, Function1<? super TextFieldValue, Unit> function122, boolean enabled42, boolean readOnly52, TextStyle mergedTextStyle2, KeyboardOptions keyboardOptions42, KeyboardActions keyboardActions32, boolean singleLine22, int maxLines32, int minLines32, VisualTransformation visualTransformation42, MutableInteractionSource interactionSource62, Function2<? super Composer, ? super Integer, Unit> function2262, Function2<? super Composer, ? super Integer, Unit> function2232, Function2<? super Composer, ? super Integer, Unit> function2102, Function2<? super Composer, ? super Integer, Unit> function2242, Function2<? super Composer, ? super Integer, Unit> function2252, Function2<? super Composer, ? super Integer, Unit> function2272, Function2<? super Composer, ? super Integer, Unit> function2282, Shape shape42) {
                    z = isError42;
                    textFieldColors = colors32;
                    textFieldValue = textFieldValue2;
                    function1 = function122;
                    z = enabled42;
                    z = readOnly52;
                    textStyle = mergedTextStyle2;
                    keyboardOptions = keyboardOptions42;
                    keyboardActions = keyboardActions32;
                    z = singleLine22;
                    i = maxLines32;
                    i = minLines32;
                    visualTransformation = visualTransformation42;
                    mutableInteractionSource = interactionSource62;
                    function2 = function2262;
                    function2 = function2232;
                    function2 = function2102;
                    function2 = function2242;
                    function2 = function2252;
                    function2 = function2272;
                    function2 = function2282;
                    shape = shape42;
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer $composer4, int $changed3) {
                    ComposerKt.sourceInformation($composer4, "C608@33369L38,626@34173L990,604@33226L1948:TextField.kt#uh7d8r");
                    if (!$composer4.shouldExecute(($changed3 & 3) != 2, $changed3 & 1)) {
                        $composer4.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-306109195, $changed3, -1, "androidx.compose.material3.TextField.<anonymous> (TextField.kt:604)");
                    }
                    Modifier modifier4 = modifier;
                    boolean z = z;
                    Strings.Companion companion = Strings.INSTANCE;
                    BasicTextFieldKt.BasicTextField(textFieldValue, function1, SizeKt.m1099defaultMinSizeVpY3zN4(TextFieldImplKt.defaultErrorSemantics(modifier4, z, Strings_androidKt.m3533getString2EP1pXo(Strings.m3454constructorimpl(androidx.compose.ui.R.string.default_error_message), $composer4, 0)), TextFieldDefaults.INSTANCE.m3143getMinWidthD9Ej5fM(), TextFieldDefaults.INSTANCE.m3142getMinHeightD9Ej5fM()), z, z, textStyle, keyboardOptions, keyboardActions, z, i, i, visualTransformation, (Function1<? super TextLayoutResult, Unit>) null, mutableInteractionSource, new SolidColor(textFieldColors.m3074cursorColorvNxB06k$material3(z), null), ComposableLambdaKt.rememberComposableLambda(-609710734, true, new Function3<Function2<? super Composer, ? super Integer, ? extends Unit>, Composer, Integer, Unit>() { // from class: androidx.compose.material3.TextFieldKt.TextField.5.1
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
                        AnonymousClass1(boolean z2, boolean z3, VisualTransformation visualTransformation5, MutableInteractionSource mutableInteractionSource, boolean z4, Function2<? super Composer, ? super Integer, Unit> function230, Function2<? super Composer, ? super Integer, Unit> function231, Function2<? super Composer, ? super Integer, Unit> function232, Function2<? super Composer, ? super Integer, Unit> function233, Function2<? super Composer, ? super Integer, Unit> function234, Function2<? super Composer, ? super Integer, Unit> function235, Function2<? super Composer, ? super Integer, Unit> function236, Shape shape5, TextFieldColors textFieldColors) {
                            z = z2;
                            z = z3;
                            visualTransformation = visualTransformation5;
                            mutableInteractionSource = mutableInteractionSource;
                            z = z4;
                            function2 = function230;
                            function2 = function231;
                            function2 = function232;
                            function2 = function233;
                            function2 = function234;
                            function2 = function235;
                            function2 = function236;
                            shape = shape5;
                            textFieldColors = textFieldColors;
                        }

                        @Override // kotlin.jvm.functions.Function3
                        public /* bridge */ /* synthetic */ Unit invoke(Function2<? super Composer, ? super Integer, ? extends Unit> function230, Composer composer, Integer num) {
                            invoke((Function2<? super Composer, ? super Integer, Unit>) function230, composer, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Function2<? super Composer, ? super Integer, Unit> function230, Composer $composer5, int $changed4) {
                            Function2<? super Composer, ? super Integer, Unit> function231;
                            ComposerKt.sourceInformation($composer5, "CN(innerTextField)628@34328L817:TextField.kt#uh7d8r");
                            int $dirty3 = $changed4;
                            if (($changed4 & 6) == 0) {
                                function231 = function230;
                                $dirty3 |= $composer5.changedInstance(function231) ? 4 : 2;
                            } else {
                                function231 = function230;
                            }
                            if ($composer5.shouldExecute(($dirty3 & 19) != 18, $dirty3 & 1)) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-609710734, $dirty3, -1, "androidx.compose.material3.TextField.<anonymous>.<anonymous> (TextField.kt:628)");
                                }
                                TextFieldDefaults.INSTANCE.DecorationBox(textFieldValue.getText(), function231, z, z, visualTransformation, mutableInteractionSource, z, function2, function2, function2, function2, function2, function2, function2, shape, textFieldColors, null, null, $composer5, ($dirty3 << 3) & 112, 100663296, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            $composer5.skipToGroupEnd();
                        }
                    }, $composer4, 54), $composer4, 0, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 4096);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }

                /* JADX INFO: renamed from: androidx.compose.material3.TextFieldKt$TextField$5$1 */
                /* JADX INFO: compiled from: TextField.kt */
                @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
                static final class AnonymousClass1 implements Function3<Function2<? super Composer, ? super Integer, ? extends Unit>, Composer, Integer, Unit> {
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
                    AnonymousClass1(boolean z2, boolean z3, VisualTransformation visualTransformation5, MutableInteractionSource mutableInteractionSource, boolean z4, Function2<? super Composer, ? super Integer, Unit> function230, Function2<? super Composer, ? super Integer, Unit> function231, Function2<? super Composer, ? super Integer, Unit> function232, Function2<? super Composer, ? super Integer, Unit> function233, Function2<? super Composer, ? super Integer, Unit> function234, Function2<? super Composer, ? super Integer, Unit> function235, Function2<? super Composer, ? super Integer, Unit> function236, Shape shape5, TextFieldColors textFieldColors) {
                        z = z2;
                        z = z3;
                        visualTransformation = visualTransformation5;
                        mutableInteractionSource = mutableInteractionSource;
                        z = z4;
                        function2 = function230;
                        function2 = function231;
                        function2 = function232;
                        function2 = function233;
                        function2 = function234;
                        function2 = function235;
                        function2 = function236;
                        shape = shape5;
                        textFieldColors = textFieldColors;
                    }

                    @Override // kotlin.jvm.functions.Function3
                    public /* bridge */ /* synthetic */ Unit invoke(Function2<? super Composer, ? super Integer, ? extends Unit> function230, Composer composer, Integer num) {
                        invoke((Function2<? super Composer, ? super Integer, Unit>) function230, composer, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Function2<? super Composer, ? super Integer, Unit> function230, Composer $composer5, int $changed4) {
                        Function2<? super Composer, ? super Integer, Unit> function231;
                        ComposerKt.sourceInformation($composer5, "CN(innerTextField)628@34328L817:TextField.kt#uh7d8r");
                        int $dirty3 = $changed4;
                        if (($changed4 & 6) == 0) {
                            function231 = function230;
                            $dirty3 |= $composer5.changedInstance(function231) ? 4 : 2;
                        } else {
                            function231 = function230;
                        }
                        if ($composer5.shouldExecute(($dirty3 & 19) != 18, $dirty3 & 1)) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-609710734, $dirty3, -1, "androidx.compose.material3.TextField.<anonymous>.<anonymous> (TextField.kt:628)");
                            }
                            TextFieldDefaults.INSTANCE.DecorationBox(textFieldValue.getText(), function231, z, z, visualTransformation, mutableInteractionSource, z, function2, function2, function2, function2, function2, function2, function2, shape, textFieldColors, null, null, $composer5, ($dirty3 << 3) & 112, 100663296, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        $composer5.skipToGroupEnd();
                    }
                }
            }, $composer3, 54), $composer3, ProvidedValue.$stable | 48);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier2 = modifier3;
            keyboardOptions2 = keyboardOptions42;
            maxLines2 = maxLines32;
            minLines2 = minLines32;
            visualTransformation2 = visualTransformation42;
            function29 = function2232;
            function213 = function2242;
            function214 = function2252;
            function215 = function2272;
            colors2 = colors32;
            readOnly4 = readOnly52;
            readOnly3 = singleLine22;
            function212 = function2262;
            shape2 = shape42;
            textStyle3 = textStyle4;
            $composer2 = $composer3;
            isError2 = isError42;
            enabled3 = enabled42;
            keyboardActions2 = keyboardActions32;
            function211 = function2282;
            interactionSource2 = interactionSource5;
        } else {
            $composer3.skipToGroupEnd();
            function29 = function22;
            function2102 = function23;
            function211 = function27;
            visualTransformation2 = visualTransformation;
            keyboardOptions2 = keyboardOptions;
            keyboardActions2 = keyboardActions;
            readOnly3 = singleLine;
            maxLines2 = maxLines;
            minLines2 = minLines;
            interactionSource2 = interactionSource;
            shape2 = shape;
            colors2 = colors;
            $composer2 = $composer3;
            enabled3 = enabled2;
            readOnly4 = readOnly2;
            textStyle3 = textStyle2;
            function212 = function28;
            function213 = function24;
            function214 = function25;
            function215 = function26;
            isError2 = isError;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Modifier modifier4 = modifier2;
            final Function2<? super Composer, ? super Integer, Unit> function230 = function2102;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.TextFieldKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TextFieldKt.TextField$lambda$8(value, function1, modifier4, enabled3, readOnly4, textStyle3, function212, function29, function230, function213, function214, function215, function211, isError2, visualTransformation2, keyboardOptions2, keyboardActions2, readOnly3, maxLines2, minLines2, interactionSource2, shape2, colors2, $changed, $changed1, $changed2, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.TextFieldKt$TextField$5 */
    /* JADX INFO: compiled from: TextField.kt */
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
        AnonymousClass5(boolean isError42, TextFieldColors colors32, TextFieldValue textFieldValue2, Function1<? super TextFieldValue, Unit> function122, boolean enabled42, boolean readOnly52, TextStyle mergedTextStyle2, KeyboardOptions keyboardOptions42, KeyboardActions keyboardActions32, boolean singleLine22, int maxLines32, int minLines32, VisualTransformation visualTransformation42, MutableInteractionSource interactionSource62, Function2<? super Composer, ? super Integer, Unit> function2262, Function2<? super Composer, ? super Integer, Unit> function2232, Function2<? super Composer, ? super Integer, Unit> function2102, Function2<? super Composer, ? super Integer, Unit> function2242, Function2<? super Composer, ? super Integer, Unit> function2252, Function2<? super Composer, ? super Integer, Unit> function2272, Function2<? super Composer, ? super Integer, Unit> function2282, Shape shape42) {
            z = isError42;
            textFieldColors = colors32;
            textFieldValue = textFieldValue2;
            function1 = function122;
            z = enabled42;
            z = readOnly52;
            textStyle = mergedTextStyle2;
            keyboardOptions = keyboardOptions42;
            keyboardActions = keyboardActions32;
            z = singleLine22;
            i = maxLines32;
            i = minLines32;
            visualTransformation = visualTransformation42;
            mutableInteractionSource = interactionSource62;
            function2 = function2262;
            function2 = function2232;
            function2 = function2102;
            function2 = function2242;
            function2 = function2252;
            function2 = function2272;
            function2 = function2282;
            shape = shape42;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer $composer4, int $changed3) {
            ComposerKt.sourceInformation($composer4, "C608@33369L38,626@34173L990,604@33226L1948:TextField.kt#uh7d8r");
            if (!$composer4.shouldExecute(($changed3 & 3) != 2, $changed3 & 1)) {
                $composer4.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-306109195, $changed3, -1, "androidx.compose.material3.TextField.<anonymous> (TextField.kt:604)");
            }
            Modifier modifier4 = modifier;
            boolean z = z;
            Strings.Companion companion = Strings.INSTANCE;
            BasicTextFieldKt.BasicTextField(textFieldValue, function1, SizeKt.m1099defaultMinSizeVpY3zN4(TextFieldImplKt.defaultErrorSemantics(modifier4, z, Strings_androidKt.m3533getString2EP1pXo(Strings.m3454constructorimpl(androidx.compose.ui.R.string.default_error_message), $composer4, 0)), TextFieldDefaults.INSTANCE.m3143getMinWidthD9Ej5fM(), TextFieldDefaults.INSTANCE.m3142getMinHeightD9Ej5fM()), z, z, textStyle, keyboardOptions, keyboardActions, z, i, i, visualTransformation, (Function1<? super TextLayoutResult, Unit>) null, mutableInteractionSource, new SolidColor(textFieldColors.m3074cursorColorvNxB06k$material3(z), null), ComposableLambdaKt.rememberComposableLambda(-609710734, true, new Function3<Function2<? super Composer, ? super Integer, ? extends Unit>, Composer, Integer, Unit>() { // from class: androidx.compose.material3.TextFieldKt.TextField.5.1
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
                AnonymousClass1(boolean z2, boolean z3, VisualTransformation visualTransformation5, MutableInteractionSource mutableInteractionSource, boolean z4, Function2<? super Composer, ? super Integer, Unit> function230, Function2<? super Composer, ? super Integer, Unit> function231, Function2<? super Composer, ? super Integer, Unit> function232, Function2<? super Composer, ? super Integer, Unit> function233, Function2<? super Composer, ? super Integer, Unit> function234, Function2<? super Composer, ? super Integer, Unit> function235, Function2<? super Composer, ? super Integer, Unit> function236, Shape shape5, TextFieldColors textFieldColors) {
                    z = z2;
                    z = z3;
                    visualTransformation = visualTransformation5;
                    mutableInteractionSource = mutableInteractionSource;
                    z = z4;
                    function2 = function230;
                    function2 = function231;
                    function2 = function232;
                    function2 = function233;
                    function2 = function234;
                    function2 = function235;
                    function2 = function236;
                    shape = shape5;
                    textFieldColors = textFieldColors;
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(Function2<? super Composer, ? super Integer, ? extends Unit> function230, Composer composer, Integer num) {
                    invoke((Function2<? super Composer, ? super Integer, Unit>) function230, composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Function2<? super Composer, ? super Integer, Unit> function230, Composer $composer5, int $changed4) {
                    Function2<? super Composer, ? super Integer, Unit> function231;
                    ComposerKt.sourceInformation($composer5, "CN(innerTextField)628@34328L817:TextField.kt#uh7d8r");
                    int $dirty3 = $changed4;
                    if (($changed4 & 6) == 0) {
                        function231 = function230;
                        $dirty3 |= $composer5.changedInstance(function231) ? 4 : 2;
                    } else {
                        function231 = function230;
                    }
                    if ($composer5.shouldExecute(($dirty3 & 19) != 18, $dirty3 & 1)) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-609710734, $dirty3, -1, "androidx.compose.material3.TextField.<anonymous>.<anonymous> (TextField.kt:628)");
                        }
                        TextFieldDefaults.INSTANCE.DecorationBox(textFieldValue.getText(), function231, z, z, visualTransformation, mutableInteractionSource, z, function2, function2, function2, function2, function2, function2, function2, shape, textFieldColors, null, null, $composer5, ($dirty3 << 3) & 112, 100663296, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer5.skipToGroupEnd();
                }
            }, $composer4, 54), $composer4, 0, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 4096);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }

        /* JADX INFO: renamed from: androidx.compose.material3.TextFieldKt$TextField$5$1 */
        /* JADX INFO: compiled from: TextField.kt */
        @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
        static final class AnonymousClass1 implements Function3<Function2<? super Composer, ? super Integer, ? extends Unit>, Composer, Integer, Unit> {
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
            AnonymousClass1(boolean z2, boolean z3, VisualTransformation visualTransformation5, MutableInteractionSource mutableInteractionSource, boolean z4, Function2<? super Composer, ? super Integer, Unit> function230, Function2<? super Composer, ? super Integer, Unit> function231, Function2<? super Composer, ? super Integer, Unit> function232, Function2<? super Composer, ? super Integer, Unit> function233, Function2<? super Composer, ? super Integer, Unit> function234, Function2<? super Composer, ? super Integer, Unit> function235, Function2<? super Composer, ? super Integer, Unit> function236, Shape shape5, TextFieldColors textFieldColors) {
                z = z2;
                z = z3;
                visualTransformation = visualTransformation5;
                mutableInteractionSource = mutableInteractionSource;
                z = z4;
                function2 = function230;
                function2 = function231;
                function2 = function232;
                function2 = function233;
                function2 = function234;
                function2 = function235;
                function2 = function236;
                shape = shape5;
                textFieldColors = textFieldColors;
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Unit invoke(Function2<? super Composer, ? super Integer, ? extends Unit> function230, Composer composer, Integer num) {
                invoke((Function2<? super Composer, ? super Integer, Unit>) function230, composer, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Function2<? super Composer, ? super Integer, Unit> function230, Composer $composer5, int $changed4) {
                Function2<? super Composer, ? super Integer, Unit> function231;
                ComposerKt.sourceInformation($composer5, "CN(innerTextField)628@34328L817:TextField.kt#uh7d8r");
                int $dirty3 = $changed4;
                if (($changed4 & 6) == 0) {
                    function231 = function230;
                    $dirty3 |= $composer5.changedInstance(function231) ? 4 : 2;
                } else {
                    function231 = function230;
                }
                if ($composer5.shouldExecute(($dirty3 & 19) != 18, $dirty3 & 1)) {
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-609710734, $dirty3, -1, "androidx.compose.material3.TextField.<anonymous>.<anonymous> (TextField.kt:628)");
                    }
                    TextFieldDefaults.INSTANCE.DecorationBox(textFieldValue.getText(), function231, z, z, visualTransformation, mutableInteractionSource, z, function2, function2, function2, function2, function2, function2, function2, shape, textFieldColors, null, null, $composer5, ($dirty3 << 3) & 112, 100663296, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                        return;
                    }
                    return;
                }
                $composer5.skipToGroupEnd();
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:1013:0x0dcb  */
    /* JADX WARN: Removed duplicated region for block: B:1016:0x0dec  */
    /* JADX WARN: Removed duplicated region for block: B:831:0x026d  */
    /* JADX WARN: Removed duplicated region for block: B:834:0x0279  */
    /* JADX WARN: Removed duplicated region for block: B:835:0x027f  */
    /* JADX WARN: Removed duplicated region for block: B:846:0x030c  */
    /* JADX WARN: Removed duplicated region for block: B:862:0x0452  */
    /* JADX WARN: Removed duplicated region for block: B:864:0x0463  */
    /* JADX WARN: Removed duplicated region for block: B:880:0x05a4  */
    /* JADX WARN: Removed duplicated region for block: B:883:0x05bc  */
    /* JADX WARN: Removed duplicated region for block: B:884:0x05d7  */
    /* JADX WARN: Removed duplicated region for block: B:887:0x05de  */
    /* JADX WARN: Removed duplicated region for block: B:888:0x05f9  */
    /* JADX WARN: Removed duplicated region for block: B:891:0x0602  */
    /* JADX WARN: Removed duplicated region for block: B:907:0x075e  */
    /* JADX WARN: Removed duplicated region for block: B:909:0x076d  */
    /* JADX WARN: Removed duplicated region for block: B:925:0x08c5  */
    /* JADX WARN: Removed duplicated region for block: B:928:0x08d4  */
    /* JADX WARN: Removed duplicated region for block: B:929:0x08f2  */
    /* JADX WARN: Removed duplicated region for block: B:932:0x090a  */
    /* JADX WARN: Removed duplicated region for block: B:945:0x0953  */
    /* JADX WARN: Removed duplicated region for block: B:949:0x095f A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:953:0x09de  */
    /* JADX WARN: Removed duplicated region for block: B:956:0x09ea  */
    /* JADX WARN: Removed duplicated region for block: B:957:0x09f0  */
    /* JADX WARN: Removed duplicated region for block: B:960:0x0a23  */
    /* JADX WARN: Removed duplicated region for block: B:964:0x0a39 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:967:0x0aad  */
    /* JADX WARN: Removed duplicated region for block: B:970:0x0ad3  */
    /* JADX WARN: Removed duplicated region for block: B:971:0x0ad6  */
    /* JADX WARN: Removed duplicated region for block: B:974:0x0ae2  */
    /* JADX WARN: Removed duplicated region for block: B:975:0x0ae5  */
    /* JADX WARN: Removed duplicated region for block: B:978:0x0afd  */
    /* JADX WARN: Removed duplicated region for block: B:979:0x0b27  */
    /* JADX WARN: Removed duplicated region for block: B:982:0x0b9d  */
    /* JADX WARN: Removed duplicated region for block: B:985:0x0ba9  */
    /* JADX WARN: Removed duplicated region for block: B:986:0x0baf  */
    /* JADX WARN: Removed duplicated region for block: B:997:0x0c68  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void TextFieldLayout(final androidx.compose.ui.Modifier r66, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r67, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r68, final kotlin.jvm.functions.Function3<? super androidx.compose.ui.Modifier, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r69, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r70, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r71, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r72, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r73, final boolean r74, androidx.compose.material3.TextFieldLabelPosition r75, androidx.compose.material3.internal.FloatProducer r76, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r77, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r78, androidx.compose.foundation.layout.PaddingValues r79, androidx.compose.runtime.Composer r80, final int r81, final int r82) {
        /*
            Method dump skipped, instruction units count: 3630
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.TextFieldKt.TextFieldLayout(androidx.compose.ui.Modifier, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function3, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, boolean, androidx.compose.material3.TextFieldLabelPosition, androidx.compose.material3.internal.FloatProducer, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, androidx.compose.foundation.layout.PaddingValues, androidx.compose.runtime.Composer, int, int):void");
    }

    public static final float getTextFieldWithLabelVerticalPadding() {
        return TextFieldWithLabelVerticalPadding;
    }
}
