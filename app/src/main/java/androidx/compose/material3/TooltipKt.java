package androidx.compose.material3;

import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.animation.core.MutableTransitionState;
import androidx.compose.animation.core.Transition;
import androidx.compose.animation.core.TransitionKt;
import androidx.compose.animation.core.TwoWayConverter;
import androidx.compose.animation.core.VectorConvertersKt;
import androidx.compose.foundation.MutatorMutex;
import androidx.compose.foundation.layout.AlignmentLineKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.material3.internal.BasicTooltipDefaults;
import androidx.compose.material3.internal.BasicTooltipKt;
import androidx.compose.material3.tokens.ElevationTokens;
import androidx.compose.material3.tokens.MotionSchemeKeyTokens;
import androidx.compose.material3.tokens.RichTooltipTokens;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.composer.linkbuffer.GroupFlagsKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.CompositingStrategy;
import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import androidx.compose.ui.graphics.GraphicsLayerScopeKt;
import androidx.compose.ui.graphics.Matrix;
import androidx.compose.ui.graphics.RectangleShapeKt;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.TransformOrigin;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LayoutModifierKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.InspectableValueKt;
import androidx.compose.ui.platform.InspectorInfo;
import androidx.compose.ui.platform.WindowInfo;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.DpSize;
import androidx.compose.ui.window.PopupPositionProvider;
import androidx.profileinstaller.ProfileVerifier;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FloatCompanionObject;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Tooltip.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000¬\u0001\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u000f\u001al\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u001c\u0010\u0004\u001a\u0018\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u0007¢\u0006\u0002\b\b2\u0006\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\u0011\u0010\u0010\u001a\r\u0012\u0004\u0012\u00020\u00010\u0011¢\u0006\u0002\b\u0007H\u0007¢\u0006\u0002\u0010\u0012\u001a~\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u001c\u0010\u0004\u001a\u0018\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u0007¢\u0006\u0002\b\b2\u0006\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\u0010\b\u0002\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u00112\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\u0011\u0010\u0010\u001a\r\u0012\u0004\u0012\u00020\u00010\u0011¢\u0006\u0002\b\u0007H\u0007¢\u0006\u0002\u0010\u0014\u001a\u0088\u0001\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u001c\u0010\u0004\u001a\u0018\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u0007¢\u0006\u0002\b\b2\u0006\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\u0010\b\u0002\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u00112\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\b\b\u0002\u0010\u0015\u001a\u00020\u000e2\u0011\u0010\u0010\u001a\r\u0012\u0004\u0012\u00020\u00010\u0011¢\u0006\u0002\b\u0007H\u0007¢\u0006\u0002\u0010\u0016\u001av\u0010\u0017\u001a\u00020\u0001*\u00020\u00062\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\b\b\u0002\u0010\u001e\u001a\u00020\u001f2\b\b\u0002\u0010 \u001a\u00020\u001f2\b\b\u0002\u0010!\u001a\u00020\u001b2\b\b\u0002\u0010\"\u001a\u00020\u001b2\u0011\u0010\u0010\u001a\r\u0012\u0004\u0012\u00020\u00010\u0011¢\u0006\u0002\b\u0007H\u0007¢\u0006\u0004\b#\u0010$\u001ax\u0010\u0017\u001a\u00020\u0001*\u00020\u00062\b\b\u0002\u0010\u000b\u001a\u00020\f2\n\b\u0002\u0010%\u001a\u0004\u0018\u00010\u001d2\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\b\b\u0002\u0010\u001e\u001a\u00020\u001f2\b\b\u0002\u0010 \u001a\u00020\u001f2\b\b\u0002\u0010!\u001a\u00020\u001b2\b\b\u0002\u0010\"\u001a\u00020\u001b2\u0011\u0010\u0010\u001a\r\u0012\u0004\u0012\u00020\u00010\u0011¢\u0006\u0002\b\u0007H\u0007¢\u0006\u0004\b&\u0010'\u001a\u009a\u0001\u0010(\u001a\u00020\u0001*\u00020\u00062\b\b\u0002\u0010\u000b\u001a\u00020\f2\u0015\b\u0002\u0010)\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0011¢\u0006\u0002\b\u00072\u0015\b\u0002\u0010*\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0011¢\u0006\u0002\b\u00072\b\b\u0002\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\b\b\u0002\u0010+\u001a\u00020,2\b\b\u0002\u0010!\u001a\u00020\u001b2\b\b\u0002\u0010\"\u001a\u00020\u001b2\u0011\u0010-\u001a\r\u0012\u0004\u0012\u00020\u00010\u0011¢\u0006\u0002\b\u0007H\u0007¢\u0006\u0004\b.\u0010/\u001a\u009c\u0001\u0010(\u001a\u00020\u0001*\u00020\u00062\b\b\u0002\u0010\u000b\u001a\u00020\f2\u0015\b\u0002\u0010)\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0011¢\u0006\u0002\b\u00072\u0015\b\u0002\u0010*\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0011¢\u0006\u0002\b\u00072\n\b\u0002\u0010%\u001a\u0004\u0018\u00010\u001d2\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\b\b\u0002\u0010+\u001a\u00020,2\b\b\u0002\u0010!\u001a\u00020\u001b2\b\b\u0002\u0010\"\u001a\u00020\u001b2\u0011\u0010-\u001a\r\u0012\u0004\u0012\u00020\u00010\u0011¢\u0006\u0002\b\u0007H\u0007¢\u0006\u0004\b0\u00101\u001a+\u00102\u001a\u00020\n2\b\b\u0002\u00103\u001a\u00020\u000e2\b\b\u0002\u00104\u001a\u00020\u000e2\b\b\u0002\u00105\u001a\u000206H\u0007¢\u0006\u0002\u00107\u001a&\u00108\u001a\u00020\n2\b\b\u0002\u00103\u001a\u00020\u000e2\b\b\u0002\u00104\u001a\u00020\u000e2\b\b\u0002\u00105\u001a\u000206H\u0007\u001a\u001c\u00109\u001a\u00020\f*\u00020\f2\u0006\u0010:\u001a\u00020\u000e2\u0006\u0010;\u001a\u00020\u000eH\u0001\u001a\u001a\u0010<\u001a\u00020\f*\u00020\f2\f\u0010=\u001a\b\u0012\u0004\u0012\u00020\u000e0>H\u0000\u001a \u0010?\u001a\u00020@2\u0006\u0010A\u001a\u00020@2\u0006\u0010B\u001a\u00020C2\u0006\u0010D\u001a\u00020EH\u0000\u001aT\u0010F\u001a\u00020\f*\u00020\f2\f\u0010G\u001a\b\u0012\u0004\u0012\u00020I0H2\u0006\u0010J\u001a\u00020K2\u0006\u0010L\u001a\u00020M2\u0019\u0010N\u001a\u0015\u0012\u0004\u0012\u00020O\u0012\u0006\u0012\u0004\u0018\u00010P0\u0005¢\u0006\u0002\b\b2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002¢\u0006\u0004\bQ\u0010R\"\u0016\u0010S\u001a\u00020\u001bX\u0080\u0004¢\u0006\n\n\u0002\u0010V\u001a\u0004\bT\u0010U\"\u0016\u0010W\u001a\u00020\u001bX\u0080\u0004¢\u0006\n\n\u0002\u0010V\u001a\u0004\bX\u0010U\"\u0016\u0010Y\u001a\u00020\u001bX\u0080\u0004¢\u0006\n\n\u0002\u0010V\u001a\u0004\bZ\u0010U\"\u0010\u0010[\u001a\u00020\u001bX\u0082\u0004¢\u0006\u0004\n\u0002\u0010V\"\u0010\u0010\\\u001a\u00020\u001bX\u0082\u0004¢\u0006\u0004\n\u0002\u0010V\"\u0014\u0010]\u001a\u00020^X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b_\u0010`\"\u0016\u0010a\u001a\u00020\u001bX\u0080\u0004¢\u0006\n\n\u0002\u0010V\u001a\u0004\bb\u0010U\"\u0016\u0010c\u001a\u00020\u001bX\u0080\u0004¢\u0006\n\n\u0002\u0010V\u001a\u0004\bd\u0010U\"\u0010\u0010e\u001a\u00020\u001bX\u0082\u0004¢\u0006\u0004\n\u0002\u0010V\"\u0010\u0010f\u001a\u00020\u001bX\u0082\u0004¢\u0006\u0004\n\u0002\u0010V\"\u0016\u0010g\u001a\u00020\u001bX\u0080\u0004¢\u0006\n\n\u0002\u0010V\u001a\u0004\bh\u0010U\"\u0016\u0010i\u001a\u00020\u001bX\u0080\u0004¢\u0006\n\n\u0002\u0010V\u001a\u0004\bj\u0010U¨\u0006k²\u0006\n\u0010l\u001a\u00020@X\u008a\u0084\u0002²\u0006\n\u0010m\u001a\u00020@X\u008a\u0084\u0002"}, d2 = {"TooltipBox", "", "positionProvider", "Landroidx/compose/ui/window/PopupPositionProvider;", "tooltip", "Lkotlin/Function1;", "Landroidx/compose/material3/TooltipScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "state", "Landroidx/compose/material3/TooltipState;", "modifier", "Landroidx/compose/ui/Modifier;", "focusable", "", "enableUserInput", "content", "Lkotlin/Function0;", "(Landroidx/compose/ui/window/PopupPositionProvider;Lkotlin/jvm/functions/Function3;Landroidx/compose/material3/TooltipState;Landroidx/compose/ui/Modifier;ZZLkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "onDismissRequest", "(Landroidx/compose/ui/window/PopupPositionProvider;Lkotlin/jvm/functions/Function3;Landroidx/compose/material3/TooltipState;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function0;ZZLkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "hasAction", "(Landroidx/compose/ui/window/PopupPositionProvider;Lkotlin/jvm/functions/Function3;Landroidx/compose/material3/TooltipState;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function0;ZZZLkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "PlainTooltip", "caretSize", "Landroidx/compose/ui/unit/DpSize;", "maxWidth", "Landroidx/compose/ui/unit/Dp;", "shape", "Landroidx/compose/ui/graphics/Shape;", "contentColor", "Landroidx/compose/ui/graphics/Color;", "containerColor", "tonalElevation", "shadowElevation", "PlainTooltip-m9Er-Xc", "(Landroidx/compose/material3/TooltipScope;Landroidx/compose/ui/Modifier;JFLandroidx/compose/ui/graphics/Shape;JJFFLkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "caretShape", "PlainTooltip-gv3ox5I", "(Landroidx/compose/material3/TooltipScope;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/graphics/Shape;FLandroidx/compose/ui/graphics/Shape;JJFFLkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "RichTooltip", "title", "action", "colors", "Landroidx/compose/material3/RichTooltipColors;", "text", "RichTooltip-ZuUcA3Q", "(Landroidx/compose/material3/TooltipScope;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;JFLandroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/RichTooltipColors;FFLkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;III)V", "RichTooltip-EkvW5A0", "(Landroidx/compose/material3/TooltipScope;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/graphics/Shape;FLandroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/RichTooltipColors;FFLkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;III)V", "rememberTooltipState", "initialIsVisible", "isPersistent", "mutatorMutex", "Landroidx/compose/foundation/MutatorMutex;", "(ZZLandroidx/compose/foundation/MutatorMutex;Landroidx/compose/runtime/Composer;II)Landroidx/compose/material3/TooltipState;", "TooltipState", "textVerticalPadding", "subheadExists", "actionExists", "animateTooltip", "transition", "Landroidx/compose/animation/core/Transition;", "caretX", "", "tooltipWidth", "screenWidthPx", "", "anchorBounds", "Landroidx/compose/ui/geometry/Rect;", "layoutCaret", "transformationMatrix", "Landroidx/compose/runtime/MutableState;", "Landroidx/compose/ui/graphics/Matrix;", "density", "Landroidx/compose/ui/unit/Density;", "windowContainerSize", "Landroidx/compose/ui/unit/IntSize;", "getAnchorLayoutCoordinates", "Landroidx/compose/ui/layout/MeasureScope;", "Landroidx/compose/ui/layout/LayoutCoordinates;", "layoutCaret-J5j9r9c", "(Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/MutableState;Landroidx/compose/ui/unit/Density;JLkotlin/jvm/functions/Function1;Landroidx/compose/ui/window/PopupPositionProvider;)Landroidx/compose/ui/Modifier;", "SpacingBetweenTooltipAndAnchor", "getSpacingBetweenTooltipAndAnchor", "()F", "F", "TooltipMinHeight", "getTooltipMinHeight", "TooltipMinWidth", "getTooltipMinWidth", "PlainTooltipVerticalPadding", "PlainTooltipHorizontalPadding", "PlainTooltipContentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "getPlainTooltipContentPadding", "()Landroidx/compose/foundation/layout/PaddingValues;", "RichTooltipHorizontalPadding", "getRichTooltipHorizontalPadding", "HeightToSubheadFirstLine", "getHeightToSubheadFirstLine", "HeightFromSubheadToTextFirstLine", "TextBottomPadding", "ActionLabelMinHeight", "getActionLabelMinHeight", "ActionLabelBottomPadding", "getActionLabelBottomPadding", "material3", "scale", "alpha"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class TooltipKt {
    private static final float SpacingBetweenTooltipAndAnchor = Dp.m8150constructorimpl(4);
    private static final float TooltipMinHeight = Dp.m8150constructorimpl(24);
    private static final float TooltipMinWidth = Dp.m8150constructorimpl(40);
    private static final float PlainTooltipVerticalPadding = Dp.m8150constructorimpl(4);
    private static final float PlainTooltipHorizontalPadding = Dp.m8150constructorimpl(8);
    private static final PaddingValues PlainTooltipContentPadding = PaddingKt.m1042PaddingValuesYgX7TsA(PlainTooltipHorizontalPadding, PlainTooltipVerticalPadding);
    private static final float RichTooltipHorizontalPadding = Dp.m8150constructorimpl(16);
    private static final float HeightToSubheadFirstLine = Dp.m8150constructorimpl(28);
    private static final float HeightFromSubheadToTextFirstLine = Dp.m8150constructorimpl(24);
    private static final float TextBottomPadding = Dp.m8150constructorimpl(16);
    private static final float ActionLabelMinHeight = Dp.m8150constructorimpl(36);
    private static final float ActionLabelBottomPadding = Dp.m8150constructorimpl(8);

    static final Unit PlainTooltip_gv3ox5I$lambda$11(TooltipScope tooltipScope, Modifier modifier, Shape shape, float f, Shape shape2, long j, long j2, float f2, float f3, Function2 function2, int i, int i2, Composer composer, int i3) {
        m3339PlainTooltipgv3ox5I(tooltipScope, modifier, shape, f, shape2, j, j2, f2, f3, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit PlainTooltip_m9Er_Xc$lambda$6(TooltipScope tooltipScope, Modifier modifier, long j, float f, Shape shape, long j2, long j3, float f2, float f3, Function2 function2, int i, int i2, Composer composer, int i3) {
        m3340PlainTooltipm9ErXc(tooltipScope, modifier, j, f, shape, j2, j3, f2, f3, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit RichTooltip_EkvW5A0$lambda$17(TooltipScope tooltipScope, Modifier modifier, Function2 function2, Function2 function22, Shape shape, float f, Shape shape2, RichTooltipColors richTooltipColors, float f2, float f3, Function2 function23, int i, int i2, int i3, Composer composer, int i4) {
        m3341RichTooltipEkvW5A0(tooltipScope, modifier, function2, function22, shape, f, shape2, richTooltipColors, f2, f3, function23, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
        return Unit.INSTANCE;
    }

    static final Unit RichTooltip_ZuUcA3Q$lambda$12(TooltipScope tooltipScope, Modifier modifier, Function2 function2, Function2 function22, long j, float f, Shape shape, RichTooltipColors richTooltipColors, float f2, float f3, Function2 function23, int i, int i2, int i3, Composer composer, int i4) {
        m3342RichTooltipZuUcA3Q(tooltipScope, modifier, function2, function22, j, f, shape, richTooltipColors, f2, f3, function23, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
        return Unit.INSTANCE;
    }

    static final Unit TooltipBox$lambda$0(PopupPositionProvider popupPositionProvider, Function3 function3, TooltipState tooltipState, Modifier modifier, boolean z, boolean z2, Function2 function2, int i, int i2, Composer composer, int i3) {
        TooltipBox(popupPositionProvider, function3, tooltipState, modifier, z, z2, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit TooltipBox$lambda$1(PopupPositionProvider popupPositionProvider, Function3 function3, TooltipState tooltipState, Modifier modifier, Function0 function0, boolean z, boolean z2, Function2 function2, int i, int i2, Composer composer, int i3) {
        TooltipBox(popupPositionProvider, function3, tooltipState, modifier, function0, z, z2, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit TooltipBox$lambda$5(PopupPositionProvider popupPositionProvider, Function3 function3, TooltipState tooltipState, Modifier modifier, Function0 function0, boolean z, boolean z2, boolean z3, Function2 function2, int i, int i2, Composer composer, int i3) {
        TooltipBox(popupPositionProvider, function3, tooltipState, modifier, function0, z, z2, z3, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Deprecated in favor of TooltipBox API that contains onDismissRequest and hasAction params.")
    public static final /* synthetic */ void TooltipBox(final PopupPositionProvider positionProvider, final Function3 tooltip, final TooltipState state, Modifier modifier, boolean focusable, boolean enableUserInput, final Function2 content, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        boolean z;
        boolean z2;
        Function2 function2;
        Composer $composer2;
        final Modifier modifier3;
        final boolean focusable2;
        final boolean enableUserInput2;
        Modifier modifier4;
        Composer $composer3 = $composer.startRestartGroup(-375963176);
        ComposerKt.sourceInformation($composer3, "C(TooltipBox)N(positionProvider,tooltip,state,modifier,focusable,enableUserInput,content)118@5365L302:Tooltip.kt#uh7d8r");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(positionProvider) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty |= 48;
        } else if (($changed & 48) == 0) {
            $dirty |= $composer3.changedInstance(tooltip) ? 32 : 16;
        }
        if ((i & 4) != 0) {
            $dirty |= 384;
        } else if (($changed & 384) == 0) {
            $dirty |= ($changed & 512) == 0 ? $composer3.changed(state) : $composer3.changedInstance(state) ? 256 : 128;
        }
        int i2 = i & 8;
        if (i2 != 0) {
            $dirty |= 3072;
            modifier2 = modifier;
        } else if (($changed & 3072) == 0) {
            modifier2 = modifier;
            $dirty |= $composer3.changed(modifier2) ? 2048 : 1024;
        } else {
            modifier2 = modifier;
        }
        int i3 = i & 16;
        if (i3 != 0) {
            $dirty |= 24576;
            z = focusable;
        } else if (($changed & 24576) == 0) {
            z = focusable;
            $dirty |= $composer3.changed(z) ? 16384 : 8192;
        } else {
            z = focusable;
        }
        int i4 = i & 32;
        if (i4 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            z2 = enableUserInput;
        } else if ((196608 & $changed) == 0) {
            z2 = enableUserInput;
            $dirty |= $composer3.changed(z2) ? 131072 : 65536;
        } else {
            z2 = enableUserInput;
        }
        if ((i & 64) != 0) {
            $dirty |= 1572864;
            function2 = content;
        } else if (($changed & 1572864) == 0) {
            function2 = content;
            $dirty |= $composer3.changedInstance(function2) ? 1048576 : 524288;
        } else {
            function2 = content;
        }
        int $dirty2 = $dirty;
        if (!$composer3.shouldExecute((599187 & $dirty2) != 599186, $dirty2 & 1)) {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            focusable2 = z;
            enableUserInput2 = z2;
        } else {
            if (i2 != 0) {
                modifier4 = Modifier.INSTANCE;
            } else {
                modifier4 = modifier2;
            }
            if (i3 == 0) {
                focusable2 = z;
            } else {
                focusable2 = true;
            }
            if (i4 == 0) {
                enableUserInput2 = z2;
            } else {
                enableUserInput2 = true;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-375963176, $dirty2, -1, "androidx.compose.material3.TooltipBox (Tooltip.kt:118)");
            }
            TooltipBox(positionProvider, tooltip, state, modifier4, null, focusable2, enableUserInput2, false, function2, $composer3, ($dirty2 & 14) | 12607488 | ($dirty2 & 112) | ($dirty2 & 896) | ($dirty2 & 7168) | (($dirty2 << 3) & 458752) | (3670016 & ($dirty2 << 3)) | (234881024 & ($dirty2 << 6)), 0);
            $composer2 = $composer3;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier4;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.TooltipKt$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TooltipKt.TooltipBox$lambda$0(positionProvider, tooltip, state, modifier3, focusable2, enableUserInput2, content, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Deprecated in favor of TooltipBox API that contains hasAction param.")
    public static final /* synthetic */ void TooltipBox(final PopupPositionProvider positionProvider, final Function3 tooltip, final TooltipState state, Modifier modifier, Function0 onDismissRequest, boolean focusable, boolean enableUserInput, final Function2 content, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        boolean z;
        boolean z2;
        Function2 function2;
        Composer $composer2;
        final Function0 onDismissRequest2;
        final Modifier modifier3;
        final boolean enableUserInput2;
        final boolean enableUserInput3;
        Modifier modifier4;
        Function0 onDismissRequest3;
        boolean focusable2;
        boolean enableUserInput4;
        Composer $composer3 = $composer.startRestartGroup(2055306788);
        ComposerKt.sourceInformation($composer3, "C(TooltipBox)N(positionProvider,tooltip,state,modifier,onDismissRequest,focusable,enableUserInput,content)209@9135L302:Tooltip.kt#uh7d8r");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(positionProvider) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty |= 48;
        } else if (($changed & 48) == 0) {
            $dirty |= $composer3.changedInstance(tooltip) ? 32 : 16;
        }
        if ((i & 4) != 0) {
            $dirty |= 384;
        } else if (($changed & 384) == 0) {
            $dirty |= ($changed & 512) == 0 ? $composer3.changed(state) : $composer3.changedInstance(state) ? 256 : 128;
        }
        int i2 = i & 8;
        if (i2 != 0) {
            $dirty |= 3072;
            modifier2 = modifier;
        } else if (($changed & 3072) == 0) {
            modifier2 = modifier;
            $dirty |= $composer3.changed(modifier2) ? 2048 : 1024;
        } else {
            modifier2 = modifier;
        }
        int i3 = i & 32;
        if (i3 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            z = focusable;
        } else if ((196608 & $changed) == 0) {
            z = focusable;
            $dirty |= $composer3.changed(z) ? 131072 : 65536;
        } else {
            z = focusable;
        }
        int i4 = i & 64;
        if (i4 != 0) {
            $dirty |= 1572864;
            z2 = enableUserInput;
        } else if ((1572864 & $changed) == 0) {
            z2 = enableUserInput;
            $dirty |= $composer3.changed(z2) ? 1048576 : 524288;
        } else {
            z2 = enableUserInput;
        }
        if ((i & 128) != 0) {
            $dirty |= 12582912;
            function2 = content;
        } else if (($changed & 12582912) == 0) {
            function2 = content;
            $dirty |= $composer3.changedInstance(function2) ? 8388608 : 4194304;
        } else {
            function2 = content;
        }
        int $dirty2 = $dirty;
        if (!$composer3.shouldExecute((4785299 & $dirty2) != 4785298, $dirty2 & 1)) {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            onDismissRequest2 = onDismissRequest;
            modifier3 = modifier2;
            enableUserInput2 = z;
            enableUserInput3 = z2;
        } else {
            if (i2 != 0) {
                modifier4 = Modifier.INSTANCE;
            } else {
                modifier4 = modifier2;
            }
            if ((i & 16) == 0) {
                onDismissRequest3 = onDismissRequest;
            } else {
                onDismissRequest3 = null;
            }
            if (i3 == 0) {
                focusable2 = z;
            } else {
                focusable2 = true;
            }
            if (i4 == 0) {
                enableUserInput4 = z2;
            } else {
                enableUserInput4 = true;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2055306788, $dirty2, -1, "androidx.compose.material3.TooltipBox (Tooltip.kt:208)");
            }
            TooltipBox(positionProvider, tooltip, state, modifier4, null, focusable2, enableUserInput4, false, function2, $composer3, ($dirty2 & 14) | 12607488 | ($dirty2 & 112) | ($dirty2 & 896) | ($dirty2 & 7168) | (458752 & $dirty2) | (3670016 & $dirty2) | (($dirty2 << 3) & 234881024), 0);
            $composer2 = $composer3;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier4;
            enableUserInput3 = enableUserInput4;
            enableUserInput2 = focusable2;
            onDismissRequest2 = onDismissRequest3;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.TooltipKt$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TooltipKt.TooltipBox$lambda$1(positionProvider, tooltip, state, modifier3, onDismissRequest2, enableUserInput2, enableUserInput3, content, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final void TooltipBox(final PopupPositionProvider positionProvider, final Function3<? super TooltipScope, ? super Composer, ? super Integer, Unit> function3, final TooltipState state, Modifier modifier, Function0<Unit> function0, boolean focusable, boolean enableUserInput, boolean hasAction, final Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        Function0<Unit> function02;
        boolean z;
        int i2;
        Composer $composer2;
        final boolean hasAction2;
        final Modifier modifier3;
        final Function0<Unit> function03;
        final boolean enableUserInput2;
        final boolean hasAction3;
        Modifier modifier4;
        boolean focusable2;
        boolean enableUserInput3;
        boolean hasAction4;
        Composer $composer3 = $composer.startRestartGroup(-293753984);
        ComposerKt.sourceInformation($composer3, "C(TooltipBox)N(positionProvider,tooltip,state,modifier,onDismissRequest,focusable,enableUserInput,hasAction,content)311@13450L64,312@13572L33,313@13622L71,315@13744L103,321@13933L64,319@13853L387:Tooltip.kt#uh7d8r");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(positionProvider) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty |= 48;
        } else if (($changed & 48) == 0) {
            $dirty |= $composer3.changedInstance(function3) ? 32 : 16;
        }
        if ((i & 4) != 0) {
            $dirty |= 384;
        } else if (($changed & 384) == 0) {
            $dirty |= ($changed & 512) == 0 ? $composer3.changed(state) : $composer3.changedInstance(state) ? 256 : 128;
        }
        int i3 = i & 8;
        if (i3 != 0) {
            $dirty |= 3072;
            modifier2 = modifier;
        } else if (($changed & 3072) == 0) {
            modifier2 = modifier;
            $dirty |= $composer3.changed(modifier2) ? 2048 : 1024;
        } else {
            modifier2 = modifier;
        }
        int i4 = i & 16;
        if (i4 != 0) {
            $dirty |= 24576;
            function02 = function0;
        } else if (($changed & 24576) == 0) {
            function02 = function0;
            $dirty |= $composer3.changedInstance(function02) ? 16384 : 8192;
        } else {
            function02 = function0;
        }
        int i5 = i & 32;
        if (i5 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            z = focusable;
        } else if ((196608 & $changed) == 0) {
            z = focusable;
            $dirty |= $composer3.changed(z) ? 131072 : 65536;
        } else {
            z = focusable;
        }
        int i6 = i & 64;
        if (i6 != 0) {
            $dirty |= 1572864;
        } else if (($changed & 1572864) == 0) {
            $dirty |= $composer3.changed(enableUserInput) ? 1048576 : 524288;
        }
        int i7 = i & 128;
        if (i7 != 0) {
            $dirty |= 12582912;
            i2 = i7;
        } else if (($changed & 12582912) == 0) {
            i2 = i7;
            $dirty |= $composer3.changed(hasAction) ? 8388608 : 4194304;
        } else {
            i2 = i7;
        }
        if ((i & 256) != 0) {
            $dirty |= 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty |= $composer3.changedInstance(function2) ? 67108864 : GroupFlagsKt.HasAuxSlotFlag;
        }
        int $dirty2 = $dirty;
        if ($composer3.shouldExecute((38347923 & $dirty2) != 38347922, $dirty2 & 1)) {
            if (i3 != 0) {
                modifier4 = Modifier.INSTANCE;
            } else {
                modifier4 = modifier2;
            }
            if (i4 != 0) {
                function02 = null;
            }
            if (i5 == 0) {
                focusable2 = z;
            } else {
                focusable2 = false;
            }
            if (i6 == 0) {
                enableUserInput3 = enableUserInput;
            } else {
                enableUserInput3 = true;
            }
            if (i2 == 0) {
                hasAction4 = hasAction;
            } else {
                hasAction4 = false;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-293753984, $dirty2, -1, "androidx.compose.material3.TooltipBox (Tooltip.kt:309)");
            }
            Transition transition = TransitionKt.updateTransition((MutableTransitionState) state.getTransition(), "tooltip transition", $composer3, MutableTransitionState.$stable | 48, 0);
            ComposerKt.sourceInformationMarkerStart($composer3, 1170281089, "CC(remember):Tooltip.kt#9igjgp");
            Modifier modifier5 = modifier4;
            Object value$iv = $composer3.rememberedValue();
            if (value$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                $composer3.updateRememberedValue(value$iv);
            }
            final MutableState anchorBounds = (MutableState) value$iv;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, 1170282727, "CC(remember):Tooltip.kt#9igjgp");
            Object value$iv2 = $composer3.rememberedValue();
            boolean focusable3 = focusable2;
            if (value$iv2 == Composer.INSTANCE.getEmpty()) {
                value$iv2 = new TooltipScopeImpl(new Function0() { // from class: androidx.compose.material3.TooltipKt$$ExternalSyntheticLambda9
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return TooltipKt.TooltipBox$lambda$4$lambda$3(anchorBounds);
                    }
                }, positionProvider);
                $composer3.updateRememberedValue(value$iv2);
            }
            TooltipScopeImpl scope = (TooltipScopeImpl) value$iv2;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Function2 wrappedContent = ComposableLambdaKt.rememberComposableLambda(-23901870, true, new TooltipKt$TooltipBox$wrappedContent$1(anchorBounds, function2), $composer3, 54);
            Function0<Unit> function04 = function02;
            BasicTooltipKt.BasicTooltipBox(positionProvider, ComposableLambdaKt.rememberComposableLambda(-527401546, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TooltipKt.TooltipBox.3
                final /* synthetic */ TooltipScopeImpl $scope;
                final /* synthetic */ Function3<TooltipScope, Composer, Integer, Unit> $tooltip;
                final /* synthetic */ Transition<Boolean> $transition;

                /* JADX WARN: Multi-variable type inference failed */
                AnonymousClass3(Transition<Boolean> transition2, final Function3<? super TooltipScope, ? super Composer, ? super Integer, Unit> function32, TooltipScopeImpl scope2) {
                    transition = transition2;
                    function3 = function32;
                    tooltipScopeImpl = scope2;
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                /* JADX WARN: Removed duplicated region for block: B:61:0x0158  */
                /* JADX WARN: Removed duplicated region for block: B:64:? A[RETURN, SYNTHETIC] */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct code enable 'Show inconsistent code' option in preferences
                */
                public final void invoke(androidx.compose.runtime.Composer r29, int r30) {
                    /*
                        Method dump skipped, instruction units count: 352
                        To view this dump change 'Code comments level' option to 'DEBUG'
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.TooltipKt.AnonymousClass3.invoke(androidx.compose.runtime.Composer, int):void");
                }
            }, $composer3, 54), state, modifier5, function04, focusable3, enableUserInput3, hasAction4, wrappedContent, $composer3, (29360128 & $dirty2) | ($dirty2 & 14) | 100663344 | ($dirty2 & 896) | ($dirty2 & 7168) | (57344 & $dirty2) | (458752 & $dirty2) | (3670016 & $dirty2), 0);
            $composer2 = $composer3;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            hasAction3 = hasAction4;
            hasAction2 = enableUserInput3;
            enableUserInput2 = focusable3;
            function03 = function04;
            modifier3 = modifier5;
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            hasAction2 = enableUserInput;
            modifier3 = modifier2;
            function03 = function02;
            enableUserInput2 = z;
            hasAction3 = hasAction;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.TooltipKt$$ExternalSyntheticLambda10
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TooltipKt.TooltipBox$lambda$5(positionProvider, function32, state, modifier3, function03, enableUserInput2, hasAction2, hasAction3, function2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    static final LayoutCoordinates TooltipBox$lambda$4$lambda$3(MutableState $anchorBounds) {
        return (LayoutCoordinates) $anchorBounds.getValue();
    }

    /* JADX INFO: renamed from: androidx.compose.material3.TooltipKt$TooltipBox$3 */
    /* JADX INFO: compiled from: Tooltip.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    static final class AnonymousClass3 implements Function2<Composer, Integer, Unit> {
        final /* synthetic */ TooltipScopeImpl $scope;
        final /* synthetic */ Function3<TooltipScope, Composer, Integer, Unit> $tooltip;
        final /* synthetic */ Transition<Boolean> $transition;

        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass3(Transition<Boolean> transition2, final Function3 function32, TooltipScopeImpl scope2) {
            transition = transition2;
            function3 = function32;
            tooltipScopeImpl = scope2;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        /* JADX WARN: Removed duplicated region for block: B:61:0x0158  */
        /* JADX WARN: Removed duplicated region for block: B:64:? A[RETURN, SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final void invoke(androidx.compose.runtime.Composer r29, int r30) {
            /*
                Method dump skipped, instruction units count: 352
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.TooltipKt.AnonymousClass3.invoke(androidx.compose.runtime.Composer, int):void");
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility")
    /* JADX INFO: renamed from: PlainTooltip-m9Er-Xc */
    public static final /* synthetic */ void m3340PlainTooltipm9ErXc(final TooltipScope $this$PlainTooltip_u2dm9Er_u2dXc, Modifier modifier, long caretSize, float maxWidth, Shape shape, long contentColor, long containerColor, float tonalElevation, float shadowElevation, final Function2 content, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        long j;
        float f;
        Shape shape2;
        long j2;
        int $dirty;
        final float tonalElevation2;
        int i2;
        Composer $composer2;
        final float shadowElevation2;
        final Modifier modifier3;
        final long caretSize2;
        final float maxWidth2;
        final Shape shape3;
        final long containerColor2;
        final long containerColor3;
        Modifier.Companion modifier4;
        long caretSize3;
        float maxWidth3;
        Shape shape4;
        long contentColor2;
        long containerColor4;
        long caretSize4;
        Shape shape5;
        long containerColor5;
        int $dirty2;
        float shadowElevation3;
        float shadowElevation4;
        int $dirty3;
        Composer $composer3 = $composer.startRestartGroup(-1881241092);
        ComposerKt.sourceInformation($composer3, "C(PlainTooltip)N(modifier,caretSize:c#ui.unit.DpSize,maxWidth:c#ui.unit.Dp,shape,contentColor:c#ui.graphics.Color,containerColor:c#ui.graphics.Color,tonalElevation:c#ui.unit.Dp,shadowElevation:c#ui.unit.Dp,content)389@16632L229:Tooltip.kt#uh7d8r");
        int $dirty4 = $changed;
        if ((Integer.MIN_VALUE & i) != 0) {
            $dirty4 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty4 |= ($changed & 8) == 0 ? $composer3.changed($this$PlainTooltip_u2dm9Er_u2dXc) : $composer3.changedInstance($this$PlainTooltip_u2dm9Er_u2dXc) ? 4 : 2;
        }
        int i3 = i & 1;
        if (i3 != 0) {
            $dirty4 |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty4 |= $composer3.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 384) == 0) {
            if ((i & 2) == 0) {
                j = caretSize;
                int i4 = $composer3.changed(j) ? 256 : 128;
                $dirty4 |= i4;
            } else {
                j = caretSize;
            }
            $dirty4 |= i4;
        } else {
            j = caretSize;
        }
        int i5 = i & 4;
        if (i5 != 0) {
            $dirty4 |= 3072;
            f = maxWidth;
        } else if (($changed & 3072) == 0) {
            f = maxWidth;
            $dirty4 |= $composer3.changed(f) ? 2048 : 1024;
        } else {
            f = maxWidth;
        }
        if (($changed & 24576) == 0) {
            if ((i & 8) == 0) {
                shape2 = shape;
                int i6 = $composer3.changed(shape2) ? 16384 : 8192;
                $dirty4 |= i6;
            } else {
                shape2 = shape;
            }
            $dirty4 |= i6;
        } else {
            shape2 = shape;
        }
        if ((196608 & $changed) == 0) {
            if ((i & 16) == 0) {
                j2 = contentColor;
                int i7 = $composer3.changed(j2) ? 131072 : 65536;
                $dirty4 |= i7;
            } else {
                j2 = contentColor;
            }
            $dirty4 |= i7;
        } else {
            j2 = contentColor;
        }
        if ((1572864 & $changed) == 0) {
            if ((i & 32) == 0) {
                $dirty3 = $dirty4;
                int i8 = $composer3.changed(containerColor) ? 1048576 : 524288;
                $dirty = $dirty3 | i8;
            } else {
                $dirty3 = $dirty4;
            }
            $dirty = $dirty3 | i8;
        } else {
            $dirty = $dirty4;
        }
        int i9 = i & 64;
        if (i9 != 0) {
            $dirty |= 12582912;
            tonalElevation2 = tonalElevation;
        } else if (($changed & 12582912) == 0) {
            tonalElevation2 = tonalElevation;
            $dirty |= $composer3.changed(tonalElevation2) ? 8388608 : 4194304;
        } else {
            tonalElevation2 = tonalElevation;
        }
        int i10 = i & 128;
        if (i10 != 0) {
            $dirty |= 100663296;
            i2 = i10;
        } else if (($changed & 100663296) == 0) {
            i2 = i10;
            $dirty |= $composer3.changed(shadowElevation) ? 67108864 : GroupFlagsKt.HasAuxSlotFlag;
        } else {
            i2 = i10;
        }
        int i11 = 805306368;
        if ((i & 256) != 0) {
            $dirty |= i11;
        } else if (($changed & 805306368) == 0) {
            i11 = $composer3.changedInstance(content) ? GroupFlagsKt.HasMovableContentFlag : GroupFlagsKt.IsMovableContentFlag;
            $dirty |= i11;
        }
        int $dirty5 = $dirty;
        if ($composer3.shouldExecute(($dirty5 & 306783379) != 306783378, $dirty5 & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "382@16356L26,383@16426L24,384@16496L26");
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                modifier4 = i3 != 0 ? Modifier.INSTANCE : modifier2;
                if ((i & 2) != 0) {
                    caretSize3 = DpSize.INSTANCE.m8257getUnspecifiedMYxV2XQ();
                    $dirty5 &= -897;
                } else {
                    caretSize3 = j;
                }
                maxWidth3 = i5 != 0 ? TooltipDefaults.INSTANCE.m3332getPlainTooltipMaxWidthD9Ej5fM() : f;
                if ((i & 8) != 0) {
                    shape4 = TooltipDefaults.INSTANCE.getPlainTooltipContainerShape($composer3, 6);
                    $dirty5 &= -57345;
                } else {
                    shape4 = shape2;
                }
                if ((i & 16) != 0) {
                    contentColor2 = TooltipDefaults.INSTANCE.getPlainTooltipContentColor($composer3, 6);
                    $dirty5 &= -458753;
                } else {
                    contentColor2 = j2;
                }
                if ((i & 32) != 0) {
                    containerColor4 = TooltipDefaults.INSTANCE.getPlainTooltipContainerColor($composer3, 6);
                    $dirty5 &= -3670017;
                } else {
                    containerColor4 = containerColor;
                }
                float tonalElevation3 = i9 != 0 ? Dp.m8150constructorimpl(0) : tonalElevation2;
                if (i2 != 0) {
                    tonalElevation2 = tonalElevation3;
                    $dirty2 = $dirty5;
                    shadowElevation3 = Dp.m8150constructorimpl(0);
                    caretSize4 = caretSize3;
                    shape5 = shape4;
                    containerColor5 = containerColor4;
                } else {
                    tonalElevation2 = tonalElevation3;
                    caretSize4 = caretSize3;
                    shape5 = shape4;
                    containerColor5 = containerColor4;
                    $dirty2 = $dirty5;
                    shadowElevation3 = shadowElevation;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 2) != 0) {
                    $dirty5 &= -897;
                }
                if ((i & 8) != 0) {
                    $dirty5 &= -57345;
                }
                if ((i & 16) != 0) {
                    $dirty5 &= -458753;
                }
                if ((i & 32) != 0) {
                    $dirty5 &= -3670017;
                }
                modifier4 = modifier2;
                caretSize4 = j;
                maxWidth3 = f;
                shape5 = shape2;
                contentColor2 = j2;
                containerColor5 = containerColor;
                $dirty2 = $dirty5;
                shadowElevation3 = shadowElevation;
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                shadowElevation4 = shadowElevation3;
                ComposerKt.traceEventStart(-1881241092, $dirty2, -1, "androidx.compose.material3.PlainTooltip (Tooltip.kt:389)");
            } else {
                shadowElevation4 = shadowElevation3;
            }
            int i12 = ($dirty2 & 14) | ($dirty2 & 112) | ($dirty2 & 7168) | (57344 & $dirty2) | (458752 & $dirty2) | (3670016 & $dirty2) | (29360128 & $dirty2) | (234881024 & $dirty2) | (1879048192 & $dirty2);
            long caretSize5 = caretSize4;
            float maxWidth4 = maxWidth3;
            long contentColor3 = contentColor2;
            float tonalElevation4 = tonalElevation2;
            float shadowElevation5 = shadowElevation4;
            m3339PlainTooltipgv3ox5I($this$PlainTooltip_u2dm9Er_u2dXc, modifier4, TooltipDefaults.INSTANCE.m3330caretShapeEaSLcWc(caretSize4), maxWidth4, shape5, contentColor3, containerColor5, tonalElevation4, shadowElevation5, content, $composer3, i12, 0);
            $composer2 = $composer3;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier4;
            tonalElevation2 = tonalElevation4;
            shadowElevation2 = shadowElevation5;
            containerColor3 = containerColor5;
            containerColor2 = contentColor3;
            maxWidth2 = maxWidth4;
            shape3 = shape5;
            caretSize2 = caretSize5;
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            shadowElevation2 = shadowElevation;
            modifier3 = modifier2;
            caretSize2 = j;
            maxWidth2 = f;
            shape3 = shape2;
            containerColor2 = j2;
            containerColor3 = containerColor;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.TooltipKt$$ExternalSyntheticLambda7
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TooltipKt.PlainTooltip_m9Er_Xc$lambda$6($this$PlainTooltip_u2dm9Er_u2dXc, modifier3, caretSize2, maxWidth2, shape3, containerColor2, containerColor3, tonalElevation2, shadowElevation2, content, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:393:0x02ee  */
    /* JADX WARN: Removed duplicated region for block: B:397:0x02f8  */
    /* JADX WARN: Removed duplicated region for block: B:408:0x0342  */
    /* JADX WARN: Removed duplicated region for block: B:411:0x0352  */
    /* JADX WARN: Removed duplicated region for block: B:415:0x035c  */
    /* JADX INFO: renamed from: PlainTooltip-gv3ox5I */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void m3339PlainTooltipgv3ox5I(final androidx.compose.material3.TooltipScope r33, androidx.compose.ui.Modifier r34, androidx.compose.ui.graphics.Shape r35, float r36, androidx.compose.ui.graphics.Shape r37, long r38, long r40, float r42, float r43, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r44, androidx.compose.runtime.Composer r45, final int r46, final int r47) {
        /*
            Method dump skipped, instruction units count: 1026
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.TooltipKt.m3339PlainTooltipgv3ox5I(androidx.compose.material3.TooltipScope, androidx.compose.ui.Modifier, androidx.compose.ui.graphics.Shape, float, androidx.compose.ui.graphics.Shape, long, long, float, float, kotlin.jvm.functions.Function2, androidx.compose.runtime.Composer, int, int):void");
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility")
    /* JADX INFO: renamed from: RichTooltip-ZuUcA3Q */
    public static final /* synthetic */ void m3342RichTooltipZuUcA3Q(final TooltipScope $this$RichTooltip_u2dZuUcA3Q, Modifier modifier, Function2 title, Function2 action, long caretSize, float maxWidth, Shape shape, RichTooltipColors colors, float tonalElevation, float shadowElevation, final Function2 text, Composer $composer, final int $changed, final int $changed1, final int i) {
        Modifier modifier2;
        Function2 function2;
        Function2 function22;
        long caretSize2;
        int $dirty;
        float maxWidth2;
        Shape shape2;
        RichTooltipColors colors2;
        int i2;
        int i3;
        Composer $composer2;
        final float shadowElevation2;
        final RichTooltipColors colors3;
        final Function2 action2;
        final float maxWidth3;
        final long caretSize3;
        final Modifier modifier3;
        final Function2 action3;
        final Shape shape3;
        final float tonalElevation2;
        Shape shape4;
        float shadowElevation3;
        int $dirty2;
        Modifier modifier4;
        float maxWidth4;
        float tonalElevation3;
        Function2 title2;
        long caretSize4;
        Function2 action4;
        int $dirty3;
        Composer $composer3 = $composer.startRestartGroup(-2125725529);
        ComposerKt.sourceInformation($composer3, "C(RichTooltip)N(modifier,title,action,caretSize:c#ui.unit.DpSize,maxWidth:c#ui.unit.Dp,shape,colors,tonalElevation:c#ui.unit.Dp,shadowElevation:c#ui.unit.Dp,text)497@20615L226:Tooltip.kt#uh7d8r");
        int $dirty4 = $changed;
        int $dirty1 = $changed1;
        if ((Integer.MIN_VALUE & i) != 0) {
            $dirty4 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty4 |= ($changed & 8) == 0 ? $composer3.changed($this$RichTooltip_u2dZuUcA3Q) : $composer3.changedInstance($this$RichTooltip_u2dZuUcA3Q) ? 4 : 2;
        }
        int i4 = i & 1;
        if (i4 != 0) {
            $dirty4 |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty4 |= $composer3.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i5 = i & 2;
        if (i5 != 0) {
            $dirty4 |= 384;
            function2 = title;
        } else if (($changed & 384) == 0) {
            function2 = title;
            $dirty4 |= $composer3.changedInstance(function2) ? 256 : 128;
        } else {
            function2 = title;
        }
        int i6 = i & 4;
        if (i6 != 0) {
            $dirty4 |= 3072;
            function22 = action;
        } else if (($changed & 3072) == 0) {
            function22 = action;
            $dirty4 |= $composer3.changedInstance(function22) ? 2048 : 1024;
        } else {
            function22 = action;
        }
        if (($changed & 24576) == 0) {
            if ((i & 8) == 0) {
                $dirty3 = $dirty4;
                caretSize2 = caretSize;
                int i7 = $composer3.changed(caretSize2) ? 16384 : 8192;
                $dirty = $dirty3 | i7;
            } else {
                $dirty3 = $dirty4;
                caretSize2 = caretSize;
            }
            $dirty = $dirty3 | i7;
        } else {
            caretSize2 = caretSize;
            $dirty = $dirty4;
        }
        int i8 = i & 16;
        if (i8 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            maxWidth2 = maxWidth;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            maxWidth2 = maxWidth;
            $dirty |= $composer3.changed(maxWidth2) ? 131072 : 65536;
        } else {
            maxWidth2 = maxWidth;
        }
        if (($changed & 1572864) == 0) {
            if ((i & 32) == 0) {
                shape2 = shape;
                int i9 = $composer3.changed(shape2) ? 1048576 : 524288;
                $dirty |= i9;
            } else {
                shape2 = shape;
            }
            $dirty |= i9;
        } else {
            shape2 = shape;
        }
        if (($changed & 12582912) == 0) {
            if ((i & 64) == 0) {
                colors2 = colors;
                int i10 = $composer3.changed(colors2) ? 8388608 : 4194304;
                $dirty |= i10;
            } else {
                colors2 = colors;
            }
            $dirty |= i10;
        } else {
            colors2 = colors;
        }
        int i11 = i & 128;
        if (i11 != 0) {
            $dirty |= 100663296;
            i2 = i11;
        } else if (($changed & 100663296) == 0) {
            i2 = i11;
            $dirty |= $composer3.changed(tonalElevation) ? 67108864 : GroupFlagsKt.HasAuxSlotFlag;
        } else {
            i2 = i11;
        }
        int i12 = i & 256;
        if (i12 != 0) {
            $dirty |= 805306368;
            i3 = i12;
        } else if (($changed & 805306368) == 0) {
            i3 = i12;
            $dirty |= $composer3.changed(shadowElevation) ? GroupFlagsKt.HasMovableContentFlag : GroupFlagsKt.IsMovableContentFlag;
        } else {
            i3 = i12;
        }
        if ((i & 512) != 0) {
            $dirty1 |= 6;
        } else if (($changed1 & 6) == 0) {
            $dirty1 |= $composer3.changedInstance(text) ? 4 : 2;
        }
        if ($composer3.shouldExecute((($dirty & 306783379) == 306783378 && ($dirty1 & 3) == 2) ? false : true, $dirty & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "491@20364L25,492@20439L19");
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                Modifier.Companion modifier5 = i4 != 0 ? Modifier.INSTANCE : modifier2;
                Function2 title3 = i5 != 0 ? null : function2;
                Function2 action5 = i6 != 0 ? null : function22;
                if ((i & 8) != 0) {
                    caretSize2 = DpSize.INSTANCE.m8257getUnspecifiedMYxV2XQ();
                    $dirty &= -57345;
                }
                if (i8 != 0) {
                    maxWidth2 = TooltipDefaults.INSTANCE.m3333getRichTooltipMaxWidthD9Ej5fM();
                }
                if ((i & 32) != 0) {
                    shape4 = TooltipDefaults.INSTANCE.getRichTooltipContainerShape($composer3, 6);
                    $dirty &= -3670017;
                } else {
                    shape4 = shape2;
                }
                if ((i & 64) != 0) {
                    colors2 = TooltipDefaults.INSTANCE.richTooltipColors($composer3, 6);
                    $dirty &= -29360129;
                }
                float tonalElevation4 = i2 != 0 ? ElevationTokens.INSTANCE.m3804getLevel0D9Ej5fM() : tonalElevation;
                if (i3 != 0) {
                    shadowElevation3 = RichTooltipTokens.INSTANCE.m4147getContainerElevationD9Ej5fM();
                    $dirty2 = $dirty;
                    float f = tonalElevation4;
                    modifier4 = modifier5;
                    maxWidth4 = maxWidth2;
                    Function2 function23 = action5;
                    tonalElevation3 = f;
                    long j = caretSize2;
                    title2 = title3;
                    caretSize4 = j;
                    action4 = function23;
                } else {
                    shadowElevation3 = shadowElevation;
                    $dirty2 = $dirty;
                    float f2 = tonalElevation4;
                    modifier4 = modifier5;
                    maxWidth4 = maxWidth2;
                    Function2 function24 = action5;
                    tonalElevation3 = f2;
                    long j2 = caretSize2;
                    title2 = title3;
                    caretSize4 = j2;
                    action4 = function24;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 8) != 0) {
                    $dirty &= -57345;
                }
                if ((i & 32) != 0) {
                    $dirty &= -3670017;
                }
                if ((i & 64) != 0) {
                    $dirty2 = $dirty & (-29360129);
                    maxWidth4 = maxWidth2;
                    modifier4 = modifier2;
                    shape4 = shape2;
                    tonalElevation3 = tonalElevation;
                    caretSize4 = caretSize2;
                    title2 = function2;
                    action4 = function22;
                    shadowElevation3 = shadowElevation;
                } else {
                    maxWidth4 = maxWidth2;
                    modifier4 = modifier2;
                    shape4 = shape2;
                    $dirty2 = $dirty;
                    tonalElevation3 = tonalElevation;
                    caretSize4 = caretSize2;
                    title2 = function2;
                    action4 = function22;
                    shadowElevation3 = shadowElevation;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2125725529, $dirty2, $dirty1, "androidx.compose.material3.RichTooltip (Tooltip.kt:497)");
            }
            float maxWidth5 = maxWidth4;
            long caretSize5 = caretSize4;
            Shape shape5 = shape4;
            float shadowElevation4 = shadowElevation3;
            m3341RichTooltipEkvW5A0($this$RichTooltip_u2dZuUcA3Q, modifier4, title2, action4, TooltipDefaults.INSTANCE.m3330caretShapeEaSLcWc(caretSize4), maxWidth5, shape5, colors2, tonalElevation3, shadowElevation4, text, $composer3, ($dirty2 & 896) | ($dirty2 & 14) | ($dirty2 & 112) | ($dirty2 & 7168) | (458752 & $dirty2) | (3670016 & $dirty2) | (29360128 & $dirty2) | (234881024 & $dirty2) | (1879048192 & $dirty2), $dirty1 & 14, 0);
            $composer2 = $composer3;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            action2 = action4;
            tonalElevation2 = tonalElevation3;
            shadowElevation2 = shadowElevation4;
            action3 = title2;
            shape3 = shape5;
            colors3 = colors2;
            modifier3 = modifier4;
            maxWidth3 = maxWidth5;
            caretSize3 = caretSize5;
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            shadowElevation2 = shadowElevation;
            colors3 = colors2;
            action2 = function22;
            maxWidth3 = maxWidth2;
            caretSize3 = caretSize2;
            modifier3 = modifier2;
            action3 = function2;
            shape3 = shape2;
            tonalElevation2 = tonalElevation;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.TooltipKt$$ExternalSyntheticLambda11
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TooltipKt.RichTooltip_ZuUcA3Q$lambda$12($this$RichTooltip_u2dZuUcA3Q, modifier3, action3, action2, caretSize3, maxWidth3, shape3, colors3, tonalElevation2, shadowElevation2, text, $changed, $changed1, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: RichTooltip-EkvW5A0 */
    public static final void m3341RichTooltipEkvW5A0(final TooltipScope $this$RichTooltip_u2dEkvW5A0, Modifier modifier, Function2<? super Composer, ? super Integer, Unit> function2, Function2<? super Composer, ? super Integer, Unit> function22, Shape caretShape, float maxWidth, Shape shape, RichTooltipColors colors, float tonalElevation, float shadowElevation, final Function2<? super Composer, ? super Integer, Unit> function23, Composer $composer, final int $changed, final int $changed1, final int i) {
        Modifier modifier2;
        Function2<? super Composer, ? super Integer, Unit> function24;
        Function2<? super Composer, ? super Integer, Unit> function25;
        Shape caretShape2;
        final Shape shape2;
        int i2;
        int i3;
        Composer $composer2;
        final float maxWidth2;
        final Shape caretShape3;
        final Modifier modifier3;
        final Function2<? super Composer, ? super Integer, Unit> function26;
        final Function2<? super Composer, ? super Integer, Unit> function27;
        final float maxWidth3;
        final RichTooltipColors colors2;
        final float shadowElevation2;
        Modifier.Companion modifier4;
        final Function2<? super Composer, ? super Integer, Unit> function28;
        final Function2<? super Composer, ? super Integer, Unit> function29;
        float maxWidth4;
        final RichTooltipColors colors3;
        float tonalElevation2;
        float shadowElevation3;
        int $dirty;
        int i4;
        float maxWidth5;
        Shape tooltipShape;
        Modifier tooltipModifier;
        Composer $composer3 = $composer.startRestartGroup(236290785);
        ComposerKt.sourceInformation($composer3, "C(RichTooltip)N(modifier,title,action,caretShape,maxWidth:c#ui.unit.Dp,shape,colors,tonalElevation:c#ui.unit.Dp,shadowElevation:c#ui.unit.Dp,text)578@23684L1598,567@23319L1963:Tooltip.kt#uh7d8r");
        int $dirty2 = $changed;
        int $dirty1 = $changed1;
        if ((Integer.MIN_VALUE & i) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty2 |= ($changed & 8) == 0 ? $composer3.changed($this$RichTooltip_u2dEkvW5A0) : $composer3.changedInstance($this$RichTooltip_u2dEkvW5A0) ? 4 : 2;
        }
        int i5 = i & 1;
        if (i5 != 0) {
            $dirty2 |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer3.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i6 = i & 2;
        if (i6 != 0) {
            $dirty2 |= 384;
            function24 = function2;
        } else if (($changed & 384) == 0) {
            function24 = function2;
            $dirty2 |= $composer3.changedInstance(function24) ? 256 : 128;
        } else {
            function24 = function2;
        }
        int i7 = i & 4;
        if (i7 != 0) {
            $dirty2 |= 3072;
            function25 = function22;
        } else if (($changed & 3072) == 0) {
            function25 = function22;
            $dirty2 |= $composer3.changedInstance(function25) ? 2048 : 1024;
        } else {
            function25 = function22;
        }
        int i8 = i & 8;
        if (i8 != 0) {
            $dirty2 |= 24576;
            caretShape2 = caretShape;
        } else if (($changed & 24576) == 0) {
            caretShape2 = caretShape;
            $dirty2 |= $composer3.changed(caretShape2) ? 16384 : 8192;
        } else {
            caretShape2 = caretShape;
        }
        int i9 = i & 16;
        if (i9 != 0) {
            $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty2 |= $composer3.changed(maxWidth) ? 131072 : 65536;
        }
        if (($changed & 1572864) == 0) {
            if ((i & 32) == 0) {
                shape2 = shape;
                int i10 = $composer3.changed(shape2) ? 1048576 : 524288;
                $dirty2 |= i10;
            } else {
                shape2 = shape;
            }
            $dirty2 |= i10;
        } else {
            shape2 = shape;
        }
        if (($changed & 12582912) == 0) {
            $dirty2 |= ((i & 64) == 0 && $composer3.changed(colors)) ? 8388608 : 4194304;
        }
        int i11 = i & 128;
        if (i11 != 0) {
            $dirty2 |= 100663296;
            i2 = i11;
        } else if (($changed & 100663296) == 0) {
            i2 = i11;
            $dirty2 |= $composer3.changed(tonalElevation) ? 67108864 : GroupFlagsKt.HasAuxSlotFlag;
        } else {
            i2 = i11;
        }
        int i12 = i & 256;
        if (i12 != 0) {
            $dirty2 |= 805306368;
            i3 = i12;
        } else if (($changed & 805306368) == 0) {
            i3 = i12;
            $dirty2 |= $composer3.changed(shadowElevation) ? GroupFlagsKt.HasMovableContentFlag : GroupFlagsKt.IsMovableContentFlag;
        } else {
            i3 = i12;
        }
        if ((i & 512) != 0) {
            $dirty1 |= 6;
        } else if (($changed1 & 6) == 0) {
            $dirty1 |= $composer3.changedInstance(function23) ? 4 : 2;
        }
        int $dirty12 = $dirty1;
        int $dirty3 = $dirty2;
        if ($composer3.shouldExecute(((306783379 & $dirty2) == 306783378 && ($dirty12 & 3) == 2) ? false : true, $dirty3 & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "537@22236L25,538@22311L19");
            if (($changed & 1) != 0 && !$composer3.getDefaultsInvalid()) {
                $composer3.skipToGroupEnd();
                int $dirty4 = (i & 32) != 0 ? $dirty3 & (-3670017) : $dirty3;
                if ((i & 64) != 0) {
                    $dirty4 &= -29360129;
                }
                Function2<? super Composer, ? super Integer, Unit> function210 = function25;
                $dirty = $dirty4;
                modifier4 = modifier2;
                function29 = function210;
                maxWidth4 = maxWidth;
                tonalElevation2 = tonalElevation;
                shadowElevation3 = shadowElevation;
                function28 = function24;
                i4 = 2;
                colors3 = colors;
            } else {
                if (i5 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if (i6 == 0) {
                    function28 = function24;
                } else {
                    function28 = null;
                }
                if (i7 == 0) {
                    function29 = function25;
                } else {
                    function29 = null;
                }
                if (i8 != 0) {
                    caretShape2 = null;
                }
                if (i9 == 0) {
                    maxWidth4 = maxWidth;
                } else {
                    maxWidth4 = TooltipDefaults.INSTANCE.m3333getRichTooltipMaxWidthD9Ej5fM();
                }
                if ((i & 32) != 0) {
                    shape2 = TooltipDefaults.INSTANCE.getRichTooltipContainerShape($composer3, 6);
                    $dirty3 &= -3670017;
                }
                if ((i & 64) == 0) {
                    colors3 = colors;
                } else {
                    colors3 = TooltipDefaults.INSTANCE.richTooltipColors($composer3, 6);
                    $dirty3 &= -29360129;
                }
                if (i2 == 0) {
                    tonalElevation2 = tonalElevation;
                } else {
                    tonalElevation2 = ElevationTokens.INSTANCE.m3804getLevel0D9Ej5fM();
                }
                if (i3 == 0) {
                    shadowElevation3 = shadowElevation;
                    $dirty = $dirty3;
                    i4 = 2;
                } else {
                    shadowElevation3 = RichTooltipTokens.INSTANCE.m4147getContainerElevationD9Ej5fM();
                    $dirty = $dirty3;
                    i4 = 2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                maxWidth5 = maxWidth4;
                ComposerKt.traceEventStart(236290785, $dirty, $dirty12, "androidx.compose.material3.RichTooltip (Tooltip.kt:542)");
            } else {
                maxWidth5 = maxWidth4;
            }
            if (caretShape2 != null) {
                $composer3.startReplaceGroup(-111951894);
                ComposerKt.sourceInformation($composer3, "546@22610L37,547@22683L7,548@22741L7,554@22955L24,559@23112L118");
                ComposerKt.sourceInformationMarkerStart($composer3, -557800122, "CC(remember):Tooltip.kt#9igjgp");
                Object value$iv = $composer3.rememberedValue();
                if (value$iv == Composer.INSTANCE.getEmpty()) {
                    value$iv = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Matrix.m5555boximpl(Matrix.m5557constructorimpl$default(null, 1, null)), null, i4, null);
                    $composer3.updateRememberedValue(value$iv);
                }
                MutableState transformationMatrix = (MutableState) value$iv;
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                Object objConsume = $composer3.consume(localDensity);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                Density density = (Density) objConsume;
                ProvidableCompositionLocal<WindowInfo> localWindowInfo = CompositionLocalsKt.getLocalWindowInfo();
                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                Object objConsume2 = $composer3.consume(localWindowInfo);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                long windowContainerSize = ((WindowInfo) objConsume2).mo7290getContainerSizeYbymL2g();
                Modifier.Companion companion = Modifier.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer3, -557789095, "CC(remember):Tooltip.kt#9igjgp");
                boolean invalid$iv = ($dirty & 14) == 4 || (($dirty & 8) != 0 && $composer3.changedInstance($this$RichTooltip_u2dEkvW5A0));
                boolean invalid$iv2 = invalid$iv;
                Object value$iv2 = $composer3.rememberedValue();
                if (invalid$iv2 || value$iv2 == Composer.INSTANCE.getEmpty()) {
                    value$iv2 = new Function1() { // from class: androidx.compose.material3.TooltipKt$$ExternalSyntheticLambda5
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return $this$RichTooltip_u2dEkvW5A0.obtainAnchorBounds((MeasureScope) obj);
                        }
                    };
                    $composer3.updateRememberedValue(value$iv2);
                }
                ComposerKt.sourceInformationMarkerEnd($composer3);
                Modifier tooltipModifier2 = m3343layoutCaretJ5j9r9c(companion, transformationMatrix, density, windowContainerSize, (Function1) value$iv2, $this$RichTooltip_u2dEkvW5A0.obtainPositionProvider()).then(modifier4);
                ComposerKt.sourceInformationMarkerStart($composer3, -557783977, "CC(remember):Tooltip.kt#9igjgp");
                boolean invalid$iv3 = ((((3670016 & $dirty) ^ 1572864) > 1048576 && $composer3.changed(shape2)) || ($dirty & 1572864) == 1048576) | ((57344 & $dirty) == 16384);
                Object value$iv3 = $composer3.rememberedValue();
                if (invalid$iv3 || value$iv3 == Composer.INSTANCE.getEmpty()) {
                    value$iv3 = new TooltipCaretShape(transformationMatrix, shape2, caretShape2);
                    $composer3.updateRememberedValue(value$iv3);
                }
                ComposerKt.sourceInformationMarkerEnd($composer3);
                Shape tooltipShape2 = (TooltipCaretShape) value$iv3;
                $composer3.endReplaceGroup();
                tooltipShape = tooltipShape2;
                tooltipModifier = tooltipModifier2;
            } else {
                $composer3.startReplaceGroup(-111306598);
                $composer3.endReplaceGroup();
                Shape tooltipShape3 = shape2;
                tooltipShape = tooltipShape3;
                tooltipModifier = modifier4;
            }
            float maxWidth6 = maxWidth5;
            float tonalElevation3 = tonalElevation2;
            SurfaceKt.m3014SurfaceT9BRK9s(SizeKt.m1119sizeInqDBjuR0$default(tooltipModifier, TooltipMinWidth, TooltipMinHeight, maxWidth5, 0.0f, 8, null), tooltipShape, colors3.getContainerColor(), 0L, tonalElevation3, shadowElevation3, null, ComposableLambdaKt.rememberComposableLambda(-1249811482, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TooltipKt$RichTooltip$4
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer $composer4, int $changed2) {
                    Function0<ComposeUiNode> function0;
                    Function2<Composer, Integer, Unit> function211;
                    Function2<Composer, Integer, Unit> function212;
                    Function0<ComposeUiNode> function02;
                    Function0<ComposeUiNode> function03;
                    Function0<ComposeUiNode> function04;
                    Composer $composer5;
                    ComposerKt.sourceInformation($composer4, "C579@23759L5,580@23826L5,581@23903L5,583@23918L1358:Tooltip.kt#uh7d8r");
                    if (!$composer4.shouldExecute(($changed2 & 3) != 2, $changed2 & 1)) {
                        $composer4.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1249811482, $changed2, -1, "androidx.compose.material3.RichTooltip.<anonymous> (Tooltip.kt:579)");
                    }
                    TextStyle actionLabelTextStyle = TypographyKt.getValue(RichTooltipTokens.INSTANCE.getActionLabelTextFont(), $composer4, 6);
                    TextStyle subheadTextStyle = TypographyKt.getValue(RichTooltipTokens.INSTANCE.getSubheadFont(), $composer4, 6);
                    TextStyle supportingTextStyle = TypographyKt.getValue(RichTooltipTokens.INSTANCE.getSupportingTextFont(), $composer4, 6);
                    Modifier modifier$iv = PaddingKt.m1050paddingVpY3zN4$default(Modifier.INSTANCE, TooltipKt.getRichTooltipHorizontalPadding(), 0.0f, 2, null);
                    Function2<Composer, Integer, Unit> function213 = function28;
                    Function2<Composer, Integer, Unit> function214 = function29;
                    RichTooltipColors richTooltipColors = colors3;
                    Function2<Composer, Integer, Unit> function215 = function23;
                    ComposerKt.sourceInformationMarkerStart($composer4, -483455358, "CC(Column)P(2,3,1)87@4442L61,88@4508L133:Column.kt#2w3rfo");
                    Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getTop();
                    Alignment.Horizontal horizontalAlignment$iv = Alignment.INSTANCE.getStart();
                    MeasurePolicy measurePolicy$iv = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer4, ((6 >> 3) & 14) | ((6 >> 3) & 112));
                    int $changed$iv$iv = (6 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer4, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                    int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer4, 0);
                    CompositionLocalMap localMap$iv$iv = $composer4.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer4, modifier$iv);
                    Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                    int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer4, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                    if (!($composer4.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer4.startReusableNode();
                    if ($composer4.getInserting()) {
                        function0 = constructor;
                        $composer4.createNode(function0);
                    } else {
                        function0 = constructor;
                        $composer4.useNode();
                    }
                    Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4433constructorimpl($composer4);
                    Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                        $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                        $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
                    }
                    Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
                    int i13 = ($changed$iv$iv$iv >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer4, -384672921, "C89@4556L9:Column.kt#2w3rfo");
                    ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                    int i14 = ((6 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer4, 112071776, "C593@24412L320:Tooltip.kt#uh7d8r");
                    if (function213 == null) {
                        $composer4.startReplaceGroup(112051624);
                        $composer4.endReplaceGroup();
                        function211 = function213;
                        function212 = function214;
                    } else {
                        $composer4.startReplaceGroup(112051625);
                        ComposerKt.sourceInformation($composer4, "*585@24040L345");
                        function211 = function213;
                        function212 = function214;
                        Modifier modifier$iv2 = AlignmentLineKt.m715paddingFromBaselineVpY3zN4$default(Modifier.INSTANCE, TooltipKt.getHeightToSubheadFirstLine(), 0.0f, 2, null);
                        ComposerKt.sourceInformationMarkerStart($composer4, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
                        Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                        MeasurePolicy measurePolicy$iv2 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
                        int $changed$iv$iv2 = (6 << 3) & 112;
                        ComposerKt.sourceInformationMarkerStart($composer4, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                        int compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer4, 0);
                        CompositionLocalMap localMap$iv$iv2 = $composer4.getCurrentCompositionLocalMap();
                        Modifier materialized$iv$iv2 = ComposedModifierKt.materializeModifier($composer4, modifier$iv2);
                        Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                        int $changed$iv$iv$iv2 = (($changed$iv$iv2 << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer4, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                        if (!($composer4.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer4.startReusableNode();
                        if ($composer4.getInserting()) {
                            function02 = constructor2;
                            $composer4.createNode(function02);
                        } else {
                            function02 = constructor2;
                            $composer4.useNode();
                        }
                        Composer $this$Layout_u24lambda_u240$iv$iv2 = Updater.m4433constructorimpl($composer4);
                        Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if ($this$Layout_u24lambda_u240$iv$iv2.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                            $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
                            $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash2);
                        }
                        Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv2, ComposeUiNode.INSTANCE.getSetModifier());
                        int i15 = ($changed$iv$iv$iv2 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer4, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                        int i16 = ((6 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer4, 1350098263, "C586@24139L228:Tooltip.kt#uh7d8r");
                        CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) new ProvidedValue[]{ContentColorKt.getLocalContentColor().provides(Color.m5303boximpl(richTooltipColors.getTitleContentColor())), TextKt.getLocalTextStyle().provides(subheadTextStyle)}, function213, $composer4, ProvidedValue.$stable);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        $composer4.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        Unit unit = Unit.INSTANCE;
                        $composer4.endReplaceGroup();
                        Unit unit2 = Unit.INSTANCE;
                    }
                    Modifier modifier$iv3 = TooltipKt.textVerticalPadding(Modifier.INSTANCE, function211 != null, function212 != null);
                    ComposerKt.sourceInformationMarkerStart($composer4, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
                    Alignment contentAlignment$iv2 = Alignment.INSTANCE.getTopStart();
                    MeasurePolicy measurePolicy$iv3 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv2, false);
                    int $changed$iv$iv3 = (0 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer4, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                    int compositeKeyHash$iv$iv3 = ComposablesKt.getCurrentCompositeKeyHash($composer4, 0);
                    CompositionLocalMap localMap$iv$iv3 = $composer4.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv3 = ComposedModifierKt.materializeModifier($composer4, modifier$iv3);
                    Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
                    int $changed$iv$iv$iv3 = (($changed$iv$iv3 << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer4, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                    if (!($composer4.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer4.startReusableNode();
                    if ($composer4.getInserting()) {
                        function03 = constructor3;
                        $composer4.createNode(function03);
                    } else {
                        function03 = constructor3;
                        $composer4.useNode();
                    }
                    Composer $this$Layout_u24lambda_u240$iv$iv3 = Updater.m4433constructorimpl($composer4);
                    Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv3, measurePolicy$iv3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv3, localMap$iv$iv3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash3 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if ($this$Layout_u24lambda_u240$iv$iv3.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv3.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv3))) {
                        $this$Layout_u24lambda_u240$iv$iv3.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv3));
                        $this$Layout_u24lambda_u240$iv$iv3.apply(Integer.valueOf(compositeKeyHash$iv$iv3), setCompositeKeyHash3);
                    }
                    Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv3, materialized$iv$iv3, ComposeUiNode.INSTANCE.getSetModifier());
                    int i17 = ($changed$iv$iv$iv3 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer4, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                    BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
                    int i18 = ((0 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer4, 97538765, "C594@24506L212:Tooltip.kt#uh7d8r");
                    CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) new ProvidedValue[]{ContentColorKt.getLocalContentColor().provides(Color.m5303boximpl(richTooltipColors.getContentColor())), TextKt.getLocalTextStyle().provides(supportingTextStyle)}, function215, $composer4, ProvidedValue.$stable);
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    $composer4.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    if (function212 == null) {
                        $composer4.startReplaceGroup(112784836);
                        $composer4.endReplaceGroup();
                        $composer5 = $composer4;
                    } else {
                        $composer4.startReplaceGroup(112784837);
                        ComposerKt.sourceInformation($composer4, "*601@24775L477");
                        Function2<Composer, Integer, Unit> function216 = function212;
                        Modifier modifier$iv4 = PaddingKt.m1052paddingqDBjuR0$default(SizeKt.m1106requiredHeightInVpY3zN4$default(Modifier.INSTANCE, TooltipKt.getActionLabelMinHeight(), 0.0f, 2, null), 0.0f, 0.0f, 0.0f, TooltipKt.getActionLabelBottomPadding(), 7, null);
                        ComposerKt.sourceInformationMarkerStart($composer4, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
                        Alignment contentAlignment$iv3 = Alignment.INSTANCE.getTopStart();
                        MeasurePolicy measurePolicy$iv4 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv3, false);
                        int $changed$iv$iv4 = (6 << 3) & 112;
                        ComposerKt.sourceInformationMarkerStart($composer4, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                        int compositeKeyHash$iv$iv4 = ComposablesKt.getCurrentCompositeKeyHash($composer4, 0);
                        CompositionLocalMap localMap$iv$iv4 = $composer4.getCurrentCompositionLocalMap();
                        Modifier materialized$iv$iv4 = ComposedModifierKt.materializeModifier($composer4, modifier$iv4);
                        Function0<ComposeUiNode> constructor4 = ComposeUiNode.INSTANCE.getConstructor();
                        int $changed$iv$iv$iv4 = (($changed$iv$iv4 << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer4, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                        if (!($composer4.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer4.startReusableNode();
                        if ($composer4.getInserting()) {
                            function04 = constructor4;
                            $composer4.createNode(function04);
                        } else {
                            function04 = constructor4;
                            $composer4.useNode();
                        }
                        Composer $this$Layout_u24lambda_u240$iv$iv4 = Updater.m4433constructorimpl($composer4);
                        $composer5 = $composer4;
                        Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv4, measurePolicy$iv4, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv4, localMap$iv$iv4, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash4 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if ($this$Layout_u24lambda_u240$iv$iv4.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv4.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv4))) {
                            $this$Layout_u24lambda_u240$iv$iv4.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv4));
                            $this$Layout_u24lambda_u240$iv$iv4.apply(Integer.valueOf(compositeKeyHash$iv$iv4), setCompositeKeyHash4);
                        }
                        Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv4, materialized$iv$iv4, ComposeUiNode.INSTANCE.getSetModifier());
                        int i19 = ($changed$iv$iv$iv4 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer5, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                        BoxScopeInstance boxScopeInstance3 = BoxScopeInstance.INSTANCE;
                        int i20 = ((6 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer5, -424550181, "C606@25001L233:Tooltip.kt#uh7d8r");
                        CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) new ProvidedValue[]{ContentColorKt.getLocalContentColor().provides(Color.m5303boximpl(richTooltipColors.getActionContentColor())), TextKt.getLocalTextStyle().provides(actionLabelTextStyle)}, function216, $composer5, ProvidedValue.$stable);
                        ComposerKt.sourceInformationMarkerEnd($composer5);
                        ComposerKt.sourceInformationMarkerEnd($composer5);
                        $composer5.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer5);
                        ComposerKt.sourceInformationMarkerEnd($composer5);
                        ComposerKt.sourceInformationMarkerEnd($composer5);
                        Unit unit3 = Unit.INSTANCE;
                        $composer5.endReplaceGroup();
                        Unit unit4 = Unit.INSTANCE;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer5);
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    $composer4.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, $composer3, 54), $composer3, (($dirty >> 12) & 57344) | 12582912 | (($dirty >> 12) & 458752), 72);
            $composer2 = $composer3;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier4;
            function26 = function28;
            caretShape3 = caretShape2;
            function27 = function29;
            maxWidth3 = maxWidth6;
            colors2 = colors3;
            maxWidth2 = tonalElevation3;
            shadowElevation2 = shadowElevation3;
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            maxWidth2 = tonalElevation;
            caretShape3 = caretShape2;
            modifier3 = modifier2;
            function26 = function24;
            function27 = function25;
            maxWidth3 = maxWidth;
            colors2 = colors;
            shadowElevation2 = shadowElevation;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.TooltipKt$$ExternalSyntheticLambda6
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TooltipKt.RichTooltip_EkvW5A0$lambda$17($this$RichTooltip_u2dEkvW5A0, modifier3, function26, function27, caretShape3, maxWidth3, shape2, colors2, maxWidth2, shadowElevation2, function23, $changed, $changed1, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final TooltipState rememberTooltipState(boolean initialIsVisible, boolean isPersistent, MutatorMutex mutatorMutex, Composer $composer, int $changed, int i) {
        ComposerKt.sourceInformationMarkerStart($composer, -1413230530, "C(rememberTooltipState)N(initialIsVisible,isPersistent,mutatorMutex)962@39859L211:Tooltip.kt#uh7d8r");
        if ((i & 1) != 0) {
            initialIsVisible = false;
        }
        if ((i & 2) != 0) {
            isPersistent = false;
        }
        if ((i & 4) != 0) {
            mutatorMutex = BasicTooltipDefaults.INSTANCE.getGlobalMutatorMutex();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1413230530, $changed, -1, "androidx.compose.material3.rememberTooltipState (Tooltip.kt:962)");
        }
        ComposerKt.sourceInformationMarkerStart($composer, -904866095, "CC(remember):Tooltip.kt#9igjgp");
        boolean invalid$iv = (((($changed & 112) ^ 48) > 32 && $composer.changed(isPersistent)) || ($changed & 48) == 32) | (((($changed & 896) ^ 384) > 256 && $composer.changed(mutatorMutex)) || ($changed & 384) == 256);
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            Object value$iv = new TooltipStateImpl(initialIsVisible, isPersistent, mutatorMutex);
            $composer.updateRememberedValue(value$iv);
            it$iv = value$iv;
        }
        TooltipStateImpl tooltipStateImpl = (TooltipStateImpl) it$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return tooltipStateImpl;
    }

    public static /* synthetic */ TooltipState TooltipState$default(boolean z, boolean z2, MutatorMutex mutatorMutex, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        if ((i & 2) != 0) {
            z2 = true;
        }
        if ((i & 4) != 0) {
            mutatorMutex = BasicTooltipDefaults.INSTANCE.getGlobalMutatorMutex();
        }
        return TooltipState(z, z2, mutatorMutex);
    }

    public static final TooltipState TooltipState(boolean initialIsVisible, boolean isPersistent, MutatorMutex mutatorMutex) {
        return new TooltipStateImpl(initialIsVisible, isPersistent, mutatorMutex);
    }

    public static final Modifier textVerticalPadding(Modifier $this$textVerticalPadding, boolean subheadExists, boolean actionExists) {
        if (!subheadExists && !actionExists) {
            return PaddingKt.m1050paddingVpY3zN4$default($this$textVerticalPadding, 0.0f, PlainTooltipVerticalPadding, 1, null);
        }
        return PaddingKt.m1052paddingqDBjuR0$default(AlignmentLineKt.m715paddingFromBaselineVpY3zN4$default($this$textVerticalPadding, HeightFromSubheadToTextFirstLine, 0.0f, 2, null), 0.0f, 0.0f, 0.0f, TextBottomPadding, 7, null);
    }

    /* JADX INFO: renamed from: androidx.compose.material3.TooltipKt$animateTooltip$2 */
    /* JADX INFO: compiled from: Tooltip.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    static final class AnonymousClass2 implements Function3<Modifier, Composer, Integer, Modifier> {
        final /* synthetic */ Transition<Boolean> $transition;

        AnonymousClass2(Transition<Boolean> transition) {
            transition = transition;
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Modifier invoke(Modifier modifier, Composer composer, Integer num) {
            return invoke(modifier, composer, num.intValue());
        }

        public final Modifier invoke(Modifier $this$composed, Composer $composer, int $changed) {
            String str;
            TwoWayConverter<Float, AnimationVector1D> twoWayConverter;
            Composer $composer2;
            Transition<Boolean> transition;
            boolean it;
            boolean it2;
            $composer.startReplaceGroup(-1498516085);
            ComposerKt.sourceInformation($composer, "C1268@51666L14,1269@51753L14,1271@51812L197,1279@52055L193:Tooltip.kt#uh7d8r");
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1498516085, $changed, -1, "androidx.compose.material3.animateTooltip.<anonymous> (Tooltip.kt:1268)");
            }
            final FiniteAnimationSpec inOutScaleAnimationSpec = MotionSchemeKt.value(MotionSchemeKeyTokens.FastSpatial, $composer, 6);
            final FiniteAnimationSpec inOutAlphaAnimationSpec = MotionSchemeKt.value(MotionSchemeKeyTokens.FastEffects, $composer, 6);
            Transition<Boolean> transition2 = transition;
            Function3<Transition.Segment<Boolean>, Composer, Integer, FiniteAnimationSpec<Float>> function3 = new Function3<Transition.Segment<Boolean>, Composer, Integer, FiniteAnimationSpec<Float>>() { // from class: androidx.compose.material3.TooltipKt$animateTooltip$2$scale$2
                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ FiniteAnimationSpec<Float> invoke(Transition.Segment<Boolean> segment, Composer composer, Integer num) {
                    return invoke(segment, composer, num.intValue());
                }

                public final FiniteAnimationSpec<Float> invoke(Transition.Segment<Boolean> segment, Composer $composer3, int $changed2) {
                    $composer3.startReplaceGroup(386845748);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(386845748, $changed2, -1, "androidx.compose.material3.animateTooltip.<anonymous>.<anonymous> (Tooltip.kt:1272)");
                    }
                    FiniteAnimationSpec<Float> finiteAnimationSpec = inOutScaleAnimationSpec;
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    $composer3.endReplaceGroup();
                    return finiteAnimationSpec;
                }
            };
            ComposerKt.sourceInformationMarkerStart($composer, -1338768149, "CC(animateFloat)P(2)1924@81822L78:Transition.kt#pdpnli");
            TwoWayConverter<Float, AnimationVector1D> vectorConverter = VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE);
            int $changed$iv$iv = (384 & 14) | ((384 << 3) & 896) | ((384 << 3) & 7168) | ((384 << 3) & 57344);
            ComposerKt.sourceInformationMarkerStart($composer, -142660079, "CC(animateValue)P(3,2)1833@77788L32,1834@77843L31,1835@77899L23,1837@77935L89:Transition.kt#pdpnli");
            int $changed2 = ($changed$iv$iv >> 9) & 112;
            boolean it3 = transition2.getCurrentState().booleanValue();
            $composer.startReplaceGroup(-1553362193);
            ComposerKt.sourceInformation($composer, "CN(it):Tooltip.kt#uh7d8r");
            if (ComposerKt.isTraceInProgress()) {
                str = "CC(animateFloat)P(2)1924@81822L78:Transition.kt#pdpnli";
                twoWayConverter = vectorConverter;
                ComposerKt.traceEventStart(-1553362193, $changed2, -1, "androidx.compose.material3.animateTooltip.<anonymous>.<anonymous> (Tooltip.kt:1275)");
            } else {
                str = "CC(animateFloat)P(2)1924@81822L78:Transition.kt#pdpnli";
                twoWayConverter = vectorConverter;
            }
            float f = it3 ? 1.0f : 0.8f;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            $composer.endReplaceGroup();
            Object initialValue$iv$iv = Float.valueOf(f);
            int $changed3 = ($changed$iv$iv >> 9) & 112;
            boolean it4 = transition2.getTargetState().booleanValue();
            $composer.startReplaceGroup(-1553362193);
            ComposerKt.sourceInformation($composer, "CN(it):Tooltip.kt#uh7d8r");
            if (ComposerKt.isTraceInProgress()) {
                $composer2 = $composer;
                ComposerKt.traceEventStart(-1553362193, $changed3, -1, "androidx.compose.material3.animateTooltip.<anonymous>.<anonymous> (Tooltip.kt:1275)");
            } else {
                $composer2 = $composer;
            }
            float f2 = it4 ? 1.0f : 0.8f;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            $composer2.endReplaceGroup();
            Object targetValue$iv$iv = Float.valueOf(f2);
            State scale$delegate = TransitionKt.createTransitionAnimation(transition2, initialValue$iv$iv, targetValue$iv$iv, function3.invoke(transition2.getSegment(), $composer, Integer.valueOf(($changed$iv$iv >> 3) & 112)), twoWayConverter, "tooltip transition: scaling", $composer, ($changed$iv$iv & 14) | (($changed$iv$iv << 9) & 57344) | (($changed$iv$iv << 6) & 458752));
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            Transition<Boolean> transition3 = transition;
            Function3<Transition.Segment<Boolean>, Composer, Integer, FiniteAnimationSpec<Float>> function32 = new Function3<Transition.Segment<Boolean>, Composer, Integer, FiniteAnimationSpec<Float>>() { // from class: androidx.compose.material3.TooltipKt$animateTooltip$2$alpha$2
                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ FiniteAnimationSpec<Float> invoke(Transition.Segment<Boolean> segment, Composer composer, Integer num) {
                    return invoke(segment, composer, num.intValue());
                }

                public final FiniteAnimationSpec<Float> invoke(Transition.Segment<Boolean> segment, Composer $composer3, int $changed4) {
                    $composer3.startReplaceGroup(-281714272);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-281714272, $changed4, -1, "androidx.compose.material3.animateTooltip.<anonymous>.<anonymous> (Tooltip.kt:1280)");
                    }
                    FiniteAnimationSpec<Float> finiteAnimationSpec = inOutAlphaAnimationSpec;
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    $composer3.endReplaceGroup();
                    return finiteAnimationSpec;
                }
            };
            ComposerKt.sourceInformationMarkerStart($composer, -1338768149, str);
            TwoWayConverter<Float, AnimationVector1D> vectorConverter2 = VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE);
            int $changed$iv$iv2 = (384 & 14) | ((384 << 3) & 896) | ((384 << 3) & 7168) | ((384 << 3) & 57344);
            ComposerKt.sourceInformationMarkerStart($composer, -142660079, "CC(animateValue)P(3,2)1833@77788L32,1834@77843L31,1835@77899L23,1837@77935L89:Transition.kt#pdpnli");
            int $changed4 = ($changed$iv$iv2 >> 9) & 112;
            boolean it5 = transition3.getCurrentState().booleanValue();
            $composer.startReplaceGroup(2073045083);
            ComposerKt.sourceInformation($composer, "CN(it):Tooltip.kt#uh7d8r");
            if (ComposerKt.isTraceInProgress()) {
                transition = transition3;
                it = it5;
                ComposerKt.traceEventStart(2073045083, $changed4, -1, "androidx.compose.material3.animateTooltip.<anonymous>.<anonymous> (Tooltip.kt:1283)");
            } else {
                transition = transition3;
                it = it5;
            }
            float f3 = it ? 1.0f : 0.0f;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            $composer.endReplaceGroup();
            Object initialValue$iv$iv2 = Float.valueOf(f3);
            int $changed5 = ($changed$iv$iv2 >> 9) & 112;
            boolean it6 = transition.getTargetState().booleanValue();
            $composer.startReplaceGroup(2073045083);
            ComposerKt.sourceInformation($composer, "CN(it):Tooltip.kt#uh7d8r");
            if (ComposerKt.isTraceInProgress()) {
                it2 = it6;
                ComposerKt.traceEventStart(2073045083, $changed5, -1, "androidx.compose.material3.animateTooltip.<anonymous>.<anonymous> (Tooltip.kt:1283)");
            } else {
                it2 = it6;
            }
            float f4 = it2 ? 1.0f : 0.0f;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            $composer.endReplaceGroup();
            Object targetValue$iv$iv2 = Float.valueOf(f4);
            State alpha$delegate = TransitionKt.createTransitionAnimation(transition, initialValue$iv$iv2, targetValue$iv$iv2, function32.invoke(transition.getSegment(), $composer, Integer.valueOf(($changed$iv$iv2 >> 3) & 112)), vectorConverter2, "tooltip transition: alpha", $composer, ($changed$iv$iv2 & 14) | (($changed$iv$iv2 << 9) & 57344) | (($changed$iv$iv2 << 6) & 458752));
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            Modifier modifierM5475graphicsLayerAp8cVGQ = GraphicsLayerModifierKt.m5475graphicsLayerAp8cVGQ($this$composed, (124895 & 1) != 0 ? 1.0f : invoke$lambda$1(scale$delegate), (124895 & 2) != 0 ? 1.0f : invoke$lambda$1(scale$delegate), (124895 & 4) == 0 ? invoke$lambda$3(alpha$delegate) : 1.0f, (124895 & 8) != 0 ? 0.0f : 0.0f, (124895 & 16) != 0 ? 0.0f : 0.0f, (124895 & 32) != 0 ? 0.0f : 0.0f, (124895 & 64) != 0 ? 0.0f : 0.0f, (124895 & 128) != 0 ? 0.0f : 0.0f, (124895 & 256) == 0 ? 0.0f : 0.0f, (124895 & 512) != 0 ? 8.0f : 0.0f, (124895 & 1024) != 0 ? TransformOrigin.INSTANCE.m5726getCenterSzJe1aQ() : 0L, (124895 & 2048) != 0 ? RectangleShapeKt.getRectangleShape() : null, (124895 & 4096) != 0 ? false : false, (124895 & 8192) != 0 ? null : null, (124895 & 16384) != 0 ? GraphicsLayerScopeKt.getDefaultShadowColor() : 0L, (32768 & 124895) != 0 ? GraphicsLayerScopeKt.getDefaultShadowColor() : 0L, (124895 & 65536) != 0 ? CompositingStrategy.INSTANCE.m5402getAutoNrFUSI() : 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            $composer.endReplaceGroup();
            return modifierM5475graphicsLayerAp8cVGQ;
        }

        private static final float invoke$lambda$1(State<Float> state) {
            Object thisObj$iv = state.getValue();
            return ((Number) thisObj$iv).floatValue();
        }

        private static final float invoke$lambda$3(State<Float> state) {
            Object thisObj$iv = state.getValue();
            return ((Number) thisObj$iv).floatValue();
        }
    }

    public static final Modifier animateTooltip(Modifier $this$animateTooltip, final Transition<Boolean> transition) {
        return ComposedModifierKt.composed($this$animateTooltip, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.material3.TooltipKt$animateTooltip$$inlined$debugInspectorInfo$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(InspectorInfo inspectorInfo) {
                invoke2(inspectorInfo);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(InspectorInfo $this$null) {
                $this$null.setName("animateTooltip");
                $this$null.getProperties().set("transition", transition);
            }
        } : InspectableValueKt.getNoInspectorInfo(), new Function3<Modifier, Composer, Integer, Modifier>() { // from class: androidx.compose.material3.TooltipKt.animateTooltip.2
            final /* synthetic */ Transition<Boolean> $transition;

            AnonymousClass2(final Transition<Boolean> transition2) {
                transition = transition2;
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Modifier invoke(Modifier modifier, Composer composer, Integer num) {
                return invoke(modifier, composer, num.intValue());
            }

            public final Modifier invoke(Modifier $this$composed, Composer $composer, int $changed) {
                String str;
                TwoWayConverter<Float, AnimationVector1D> twoWayConverter;
                Composer $composer2;
                Transition<Boolean> transition2;
                boolean it;
                boolean it2;
                $composer.startReplaceGroup(-1498516085);
                ComposerKt.sourceInformation($composer, "C1268@51666L14,1269@51753L14,1271@51812L197,1279@52055L193:Tooltip.kt#uh7d8r");
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1498516085, $changed, -1, "androidx.compose.material3.animateTooltip.<anonymous> (Tooltip.kt:1268)");
                }
                final FiniteAnimationSpec<Float> inOutScaleAnimationSpec = MotionSchemeKt.value(MotionSchemeKeyTokens.FastSpatial, $composer, 6);
                final FiniteAnimationSpec<Float> inOutAlphaAnimationSpec = MotionSchemeKt.value(MotionSchemeKeyTokens.FastEffects, $composer, 6);
                Transition<Boolean> transition22 = transition;
                Function3<Transition.Segment<Boolean>, Composer, Integer, FiniteAnimationSpec<Float>> function3 = new Function3<Transition.Segment<Boolean>, Composer, Integer, FiniteAnimationSpec<Float>>() { // from class: androidx.compose.material3.TooltipKt$animateTooltip$2$scale$2
                    @Override // kotlin.jvm.functions.Function3
                    public /* bridge */ /* synthetic */ FiniteAnimationSpec<Float> invoke(Transition.Segment<Boolean> segment, Composer composer, Integer num) {
                        return invoke(segment, composer, num.intValue());
                    }

                    public final FiniteAnimationSpec<Float> invoke(Transition.Segment<Boolean> segment, Composer $composer3, int $changed2) {
                        $composer3.startReplaceGroup(386845748);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(386845748, $changed2, -1, "androidx.compose.material3.animateTooltip.<anonymous>.<anonymous> (Tooltip.kt:1272)");
                        }
                        FiniteAnimationSpec<Float> finiteAnimationSpec = inOutScaleAnimationSpec;
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        $composer3.endReplaceGroup();
                        return finiteAnimationSpec;
                    }
                };
                ComposerKt.sourceInformationMarkerStart($composer, -1338768149, "CC(animateFloat)P(2)1924@81822L78:Transition.kt#pdpnli");
                TwoWayConverter<Float, AnimationVector1D> vectorConverter = VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE);
                int $changed$iv$iv = (384 & 14) | ((384 << 3) & 896) | ((384 << 3) & 7168) | ((384 << 3) & 57344);
                ComposerKt.sourceInformationMarkerStart($composer, -142660079, "CC(animateValue)P(3,2)1833@77788L32,1834@77843L31,1835@77899L23,1837@77935L89:Transition.kt#pdpnli");
                int $changed2 = ($changed$iv$iv >> 9) & 112;
                boolean it3 = transition22.getCurrentState().booleanValue();
                $composer.startReplaceGroup(-1553362193);
                ComposerKt.sourceInformation($composer, "CN(it):Tooltip.kt#uh7d8r");
                if (ComposerKt.isTraceInProgress()) {
                    str = "CC(animateFloat)P(2)1924@81822L78:Transition.kt#pdpnli";
                    twoWayConverter = vectorConverter;
                    ComposerKt.traceEventStart(-1553362193, $changed2, -1, "androidx.compose.material3.animateTooltip.<anonymous>.<anonymous> (Tooltip.kt:1275)");
                } else {
                    str = "CC(animateFloat)P(2)1924@81822L78:Transition.kt#pdpnli";
                    twoWayConverter = vectorConverter;
                }
                float f = it3 ? 1.0f : 0.8f;
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                $composer.endReplaceGroup();
                Object initialValue$iv$iv = Float.valueOf(f);
                int $changed3 = ($changed$iv$iv >> 9) & 112;
                boolean it4 = transition22.getTargetState().booleanValue();
                $composer.startReplaceGroup(-1553362193);
                ComposerKt.sourceInformation($composer, "CN(it):Tooltip.kt#uh7d8r");
                if (ComposerKt.isTraceInProgress()) {
                    $composer2 = $composer;
                    ComposerKt.traceEventStart(-1553362193, $changed3, -1, "androidx.compose.material3.animateTooltip.<anonymous>.<anonymous> (Tooltip.kt:1275)");
                } else {
                    $composer2 = $composer;
                }
                float f2 = it4 ? 1.0f : 0.8f;
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                $composer2.endReplaceGroup();
                Object targetValue$iv$iv = Float.valueOf(f2);
                State scale$delegate = TransitionKt.createTransitionAnimation(transition22, initialValue$iv$iv, targetValue$iv$iv, function3.invoke(transition22.getSegment(), $composer, Integer.valueOf(($changed$iv$iv >> 3) & 112)), twoWayConverter, "tooltip transition: scaling", $composer, ($changed$iv$iv & 14) | (($changed$iv$iv << 9) & 57344) | (($changed$iv$iv << 6) & 458752));
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                Transition<Boolean> transition3 = transition;
                Function3<Transition.Segment<Boolean>, Composer, Integer, FiniteAnimationSpec<Float>> function32 = new Function3<Transition.Segment<Boolean>, Composer, Integer, FiniteAnimationSpec<Float>>() { // from class: androidx.compose.material3.TooltipKt$animateTooltip$2$alpha$2
                    @Override // kotlin.jvm.functions.Function3
                    public /* bridge */ /* synthetic */ FiniteAnimationSpec<Float> invoke(Transition.Segment<Boolean> segment, Composer composer, Integer num) {
                        return invoke(segment, composer, num.intValue());
                    }

                    public final FiniteAnimationSpec<Float> invoke(Transition.Segment<Boolean> segment, Composer $composer3, int $changed4) {
                        $composer3.startReplaceGroup(-281714272);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-281714272, $changed4, -1, "androidx.compose.material3.animateTooltip.<anonymous>.<anonymous> (Tooltip.kt:1280)");
                        }
                        FiniteAnimationSpec<Float> finiteAnimationSpec = inOutAlphaAnimationSpec;
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        $composer3.endReplaceGroup();
                        return finiteAnimationSpec;
                    }
                };
                ComposerKt.sourceInformationMarkerStart($composer, -1338768149, str);
                TwoWayConverter<Float, AnimationVector1D> vectorConverter2 = VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE);
                int $changed$iv$iv2 = (384 & 14) | ((384 << 3) & 896) | ((384 << 3) & 7168) | ((384 << 3) & 57344);
                ComposerKt.sourceInformationMarkerStart($composer, -142660079, "CC(animateValue)P(3,2)1833@77788L32,1834@77843L31,1835@77899L23,1837@77935L89:Transition.kt#pdpnli");
                int $changed4 = ($changed$iv$iv2 >> 9) & 112;
                boolean it5 = transition3.getCurrentState().booleanValue();
                $composer.startReplaceGroup(2073045083);
                ComposerKt.sourceInformation($composer, "CN(it):Tooltip.kt#uh7d8r");
                if (ComposerKt.isTraceInProgress()) {
                    transition2 = transition3;
                    it = it5;
                    ComposerKt.traceEventStart(2073045083, $changed4, -1, "androidx.compose.material3.animateTooltip.<anonymous>.<anonymous> (Tooltip.kt:1283)");
                } else {
                    transition2 = transition3;
                    it = it5;
                }
                float f3 = it ? 1.0f : 0.0f;
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                $composer.endReplaceGroup();
                Object initialValue$iv$iv2 = Float.valueOf(f3);
                int $changed5 = ($changed$iv$iv2 >> 9) & 112;
                boolean it6 = transition2.getTargetState().booleanValue();
                $composer.startReplaceGroup(2073045083);
                ComposerKt.sourceInformation($composer, "CN(it):Tooltip.kt#uh7d8r");
                if (ComposerKt.isTraceInProgress()) {
                    it2 = it6;
                    ComposerKt.traceEventStart(2073045083, $changed5, -1, "androidx.compose.material3.animateTooltip.<anonymous>.<anonymous> (Tooltip.kt:1283)");
                } else {
                    it2 = it6;
                }
                float f4 = it2 ? 1.0f : 0.0f;
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                $composer.endReplaceGroup();
                Object targetValue$iv$iv2 = Float.valueOf(f4);
                State alpha$delegate = TransitionKt.createTransitionAnimation(transition2, initialValue$iv$iv2, targetValue$iv$iv2, function32.invoke(transition2.getSegment(), $composer, Integer.valueOf(($changed$iv$iv2 >> 3) & 112)), vectorConverter2, "tooltip transition: alpha", $composer, ($changed$iv$iv2 & 14) | (($changed$iv$iv2 << 9) & 57344) | (($changed$iv$iv2 << 6) & 458752));
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                Modifier modifierM5475graphicsLayerAp8cVGQ = GraphicsLayerModifierKt.m5475graphicsLayerAp8cVGQ($this$composed, (124895 & 1) != 0 ? 1.0f : invoke$lambda$1(scale$delegate), (124895 & 2) != 0 ? 1.0f : invoke$lambda$1(scale$delegate), (124895 & 4) == 0 ? invoke$lambda$3(alpha$delegate) : 1.0f, (124895 & 8) != 0 ? 0.0f : 0.0f, (124895 & 16) != 0 ? 0.0f : 0.0f, (124895 & 32) != 0 ? 0.0f : 0.0f, (124895 & 64) != 0 ? 0.0f : 0.0f, (124895 & 128) != 0 ? 0.0f : 0.0f, (124895 & 256) == 0 ? 0.0f : 0.0f, (124895 & 512) != 0 ? 8.0f : 0.0f, (124895 & 1024) != 0 ? TransformOrigin.INSTANCE.m5726getCenterSzJe1aQ() : 0L, (124895 & 2048) != 0 ? RectangleShapeKt.getRectangleShape() : null, (124895 & 4096) != 0 ? false : false, (124895 & 8192) != 0 ? null : null, (124895 & 16384) != 0 ? GraphicsLayerScopeKt.getDefaultShadowColor() : 0L, (32768 & 124895) != 0 ? GraphicsLayerScopeKt.getDefaultShadowColor() : 0L, (124895 & 65536) != 0 ? CompositingStrategy.INSTANCE.m5402getAutoNrFUSI() : 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                $composer.endReplaceGroup();
                return modifierM5475graphicsLayerAp8cVGQ;
            }

            private static final float invoke$lambda$1(State<Float> state) {
                Object thisObj$iv = state.getValue();
                return ((Number) thisObj$iv).floatValue();
            }

            private static final float invoke$lambda$3(State<Float> state) {
                Object thisObj$iv = state.getValue();
                return ((Number) thisObj$iv).floatValue();
            }
        });
    }

    public static final float caretX(float tooltipWidth, int screenWidthPx, Rect anchorBounds) {
        float anchorLeft = anchorBounds.getLeft();
        float anchorRight = anchorBounds.getRight();
        float anchorMid = (anchorLeft + anchorRight) / 2.0f;
        if (tooltipWidth >= screenWidthPx) {
            return anchorMid;
        }
        if (anchorMid - (tooltipWidth / 2.0f) < 0.0f) {
            float horizontalCorrection = Math.max(tooltipWidth - screenWidthPx, -anchorLeft);
            return horizontalCorrection + anchorMid;
        }
        if ((tooltipWidth / 2.0f) + anchorMid > screenWidthPx) {
            float horizontalCorrection2 = Math.min(tooltipWidth - anchorRight, 0.0f);
            return horizontalCorrection2 + anchorMid;
        }
        return tooltipWidth / 2.0f;
    }

    /* JADX INFO: renamed from: layoutCaret-J5j9r9c */
    private static final Modifier m3343layoutCaretJ5j9r9c(Modifier $this$layoutCaret_u2dJ5j9r9c, final MutableState<Matrix> mutableState, final Density density, final long windowContainerSize, final Function1<? super MeasureScope, ? extends LayoutCoordinates> function1, final PopupPositionProvider positionProvider) {
        return LayoutModifierKt.layout($this$layoutCaret_u2dJ5j9r9c, new Function3() { // from class: androidx.compose.material3.TooltipKt$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return TooltipKt.layoutCaret_J5j9r9c$lambda$22(windowContainerSize, function1, density, positionProvider, mutableState, (MeasureScope) obj, (Measurable) obj2, (Constraints) obj3);
            }
        });
    }

    /* JADX WARN: Removed duplicated region for block: B:170:0x00f6  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x00fe  */
    /* JADX WARN: Removed duplicated region for block: B:197:0x01b4  */
    /* JADX WARN: Removed duplicated region for block: B:212:0x020e  */
    /* JADX WARN: Removed duplicated region for block: B:215:0x025d  */
    /* JADX WARN: Removed duplicated region for block: B:218:0x02c4  */
    /* JADX WARN: Removed duplicated region for block: B:259:0x0399  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static final androidx.compose.ui.layout.MeasureResult layoutCaret_J5j9r9c$lambda$22(long r37, kotlin.jvm.functions.Function1 r39, androidx.compose.ui.unit.Density r40, androidx.compose.ui.window.PopupPositionProvider r41, androidx.compose.runtime.MutableState r42, androidx.compose.ui.layout.MeasureScope r43, androidx.compose.ui.layout.Measurable r44, androidx.compose.ui.unit.Constraints r45) {
        /*
            Method dump skipped, instruction units count: 965
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.TooltipKt.layoutCaret_J5j9r9c$lambda$22(long, kotlin.jvm.functions.Function1, androidx.compose.ui.unit.Density, androidx.compose.ui.window.PopupPositionProvider, androidx.compose.runtime.MutableState, androidx.compose.ui.layout.MeasureScope, androidx.compose.ui.layout.Measurable, androidx.compose.ui.unit.Constraints):androidx.compose.ui.layout.MeasureResult");
    }

    static final Unit layoutCaret_J5j9r9c$lambda$22$lambda$21(Placeable $placeable, Placeable.PlacementScope $this$layout) {
        Placeable.PlacementScope.place$default($this$layout, $placeable, 0, 0, 0.0f, 4, null);
        return Unit.INSTANCE;
    }

    public static final float getSpacingBetweenTooltipAndAnchor() {
        return SpacingBetweenTooltipAndAnchor;
    }

    public static final float getTooltipMinHeight() {
        return TooltipMinHeight;
    }

    public static final float getTooltipMinWidth() {
        return TooltipMinWidth;
    }

    public static final PaddingValues getPlainTooltipContentPadding() {
        return PlainTooltipContentPadding;
    }

    public static final float getRichTooltipHorizontalPadding() {
        return RichTooltipHorizontalPadding;
    }

    public static final float getHeightToSubheadFirstLine() {
        return HeightToSubheadFirstLine;
    }

    public static final float getActionLabelMinHeight() {
        return ActionLabelMinHeight;
    }

    public static final float getActionLabelBottomPadding() {
        return ActionLabelBottomPadding;
    }
}
