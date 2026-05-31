package androidx.compose.material3;

import androidx.compose.animation.core.AnimateAsStateKt;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.IndicationKt;
import androidx.compose.foundation.interaction.InteractionSource;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.selection.SelectableKt;
import androidx.compose.material3.internal.MappedInteractionSource;
import androidx.compose.material3.internal.ProvideContentColorTextStyleKt;
import androidx.compose.material3.tokens.MotionSchemeKeyTokens;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotIntStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.composer.linkbuffer.GroupFlagsKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.layout.LayoutIdKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.OnRemeasuredModifierKt;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.IntSize;
import androidx.profileinstaller.ProfileVerifier;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: NavigationItem.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\u0090\u0001\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\b\u001aµ\u0001\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\u0011\u0010\u0006\u001a\r\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00032\u0013\u0010\u0018\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0005¢\u0006\u0002\b\u00072\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0001¢\u0006\u0004\b\u001d\u0010\u001e\u001aÕ\u0001\u0010\u001f\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\u0011\u0010\u0006\u001a\r\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u00072\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010 \u001a\u00020\r2\u0006\u0010!\u001a\u00020\t2\u0006\u0010\"\u001a\u00020\t2\u0006\u0010#\u001a\u00020\r2\u0006\u0010$\u001a\u00020\r2\u0006\u0010%\u001a\u00020\r2\u0006\u0010&\u001a\u00020\r2\u0006\u0010'\u001a\u00020\r2\u0006\u0010(\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\r2\u0006\u0010)\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00032\u0013\u0010\u0018\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0005¢\u0006\u0002\b\u00072\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0001¢\u0006\u0004\b*\u0010+\u001a\u008d\u0001\u0010,\u001a\u00020\u00012\u0006\u0010\u001b\u001a\u00020-2\u0006\u0010.\u001a\u00020/2\u0006\u0010\n\u001a\u00020\u000b2\u0011\u0010\u0006\u001a\r\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u00072\u0006\u0010\u0019\u001a\u00020\u001a2\u0013\u0010\u0018\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0005¢\u0006\u0002\b\u00072\f\u00100\u001a\b\u0012\u0004\u0012\u0002010\u00052\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\rH\u0003¢\u0006\u0004\b2\u00103\u001a³\u0001\u00104\u001a\u00020\u00012\u0006\u0010\u001b\u001a\u00020-2\u0006\u0010.\u001a\u00020/2\u0006\u0010\n\u001a\u00020\u000b2\f\u00100\u001a\b\u0012\u0004\u0012\u0002010\u00052\u0011\u0010\u0006\u001a\r\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u00072\u0006\u0010\u0019\u001a\u00020\u001a2\f\u00105\u001a\b\u0012\u0004\u0012\u0002010\u00052\u0013\u0010\u0018\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0005¢\u0006\u0002\b\u00072\u0006\u0010#\u001a\u00020\r2\u0006\u0010$\u001a\u00020\r2\u0006\u0010%\u001a\u00020\r2\u0006\u0010&\u001a\u00020\r2\u0006\u0010'\u001a\u00020\r2\u0006\u0010(\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\r2\u0006\u0010)\u001a\u00020\rH\u0003¢\u0006\u0004\b6\u00107\u001a3\u00108\u001a\u000209*\u00020:2\u0006\u0010;\u001a\u00020<2\u0006\u0010=\u001a\u00020<2\u0006\u0010>\u001a\u00020<2\u0006\u0010?\u001a\u00020@H\u0002¢\u0006\u0004\bA\u0010B\u001aS\u0010C\u001a\u000209*\u00020:2\u0006\u0010D\u001a\u00020<2\u0006\u0010;\u001a\u00020<2\u0006\u0010=\u001a\u00020<2\u0006\u0010>\u001a\u00020<2\u0006\u0010?\u001a\u00020@2\u0006\u0010\u0010\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\rH\u0002¢\u0006\u0004\bE\u0010F\u001aC\u0010G\u001a\u000209*\u00020:2\u0006\u0010D\u001a\u00020<2\u0006\u0010;\u001a\u00020<2\u0006\u0010=\u001a\u00020<2\u0006\u0010>\u001a\u00020<2\u0006\u0010?\u001a\u00020@2\u0006\u0010\u0011\u001a\u00020\rH\u0002¢\u0006\u0004\bH\u0010I\u001a\u0091\u0001\u0010J\u001a\u000209*\u00020:2\u0006\u0010\u0019\u001a\u00020\u001a2\f\u00105\u001a\b\u0012\u0004\u0012\u0002010\u00052\u0006\u0010D\u001a\u00020<2\u0006\u0010;\u001a\u00020<2\u0006\u0010=\u001a\u00020<2\u0006\u0010>\u001a\u00020<2\u0006\u0010 \u001a\u00020K2\u0006\u0010?\u001a\u00020@2\u0006\u0010%\u001a\u00020\r2\u0006\u0010$\u001a\u00020\r2\u0006\u0010#\u001a\u00020\r2\u0006\u0010&\u001a\u00020\r2\u0006\u0010'\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\r2\u0006\u0010)\u001a\u00020\rH\u0002¢\u0006\u0004\bL\u0010M\u001a@\u0010N\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u00032\u0011\u0010O\u001a\r\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u0007H\u0003¢\u0006\u0002\u0010P\u001a\u001b\u0010Q\u001a\b\u0012\u0004\u0012\u0002010R2\u0006\u0010\u0002\u001a\u00020\u0003H\u0003¢\u0006\u0002\u0010S\u001a\u001d\u0010T\u001a\u00020\u00012\u0006\u0010\u001b\u001a\u00020-2\u0006\u0010\n\u001a\u00020\u000bH\u0003¢\u0006\u0002\u0010U\u001a-\u0010V\u001a\u00020\u00012\u0006\u0010.\u001a\u00020/2\u0006\u0010\n\u001a\u00020\u000b2\f\u00100\u001a\b\u0012\u0004\u0012\u0002010\u0005H\u0003¢\u0006\u0004\bW\u0010X\"\u000e\u0010Y\u001a\u00020ZX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010[\u001a\u00020ZX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\\\u001a\u00020ZX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010]\u001a\u00020ZX\u0082T¢\u0006\u0002\n\u0000\"\u0010\u0010^\u001a\u00020\rX\u0082\u0004¢\u0006\u0004\n\u0002\u0010_¨\u0006`²\u0006\n\u0010a\u001a\u00020KX\u008a\u008e\u0002²\u0006\n\u0010a\u001a\u00020KX\u008a\u008e\u0002²\u0006\n\u00105\u001a\u000201X\u008a\u0084\u0002²\u0006\n\u0010b\u001a\u00020\tX\u008a\u0084\u0002"}, d2 = {"NavigationItem", "", "selected", "", "onClick", "Lkotlin/Function0;", NavigationItemKt.IconLayoutIdTag, "Landroidx/compose/runtime/Composable;", "labelTextStyle", "Landroidx/compose/ui/text/TextStyle;", "indicatorShape", "Landroidx/compose/ui/graphics/Shape;", "indicatorWidth", "Landroidx/compose/ui/unit/Dp;", "indicatorHorizontalPadding", "indicatorVerticalPadding", "indicatorToLabelVerticalPadding", "startIconToLabelHorizontalPadding", "topIconItemVerticalPadding", "colors", "Landroidx/compose/material3/NavigationItemColors;", "modifier", "Landroidx/compose/ui/Modifier;", "enabled", NavigationItemKt.LabelLayoutIdTag, "iconPosition", "Landroidx/compose/material3/NavigationItemIconPosition;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "NavigationItem-8Df7sds", "(ZLkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/graphics/Shape;FFFFFFLandroidx/compose/material3/NavigationItemColors;Landroidx/compose/ui/Modifier;ZLkotlin/jvm/functions/Function2;ILandroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;II)V", "AnimatedNavigationItem", "topIconIndicatorWidth", "topIconLabelTextStyle", "startIconLabelTextStyle", "topIconIndicatorHorizontalPadding", "topIconIndicatorVerticalPadding", "topIconIndicatorToLabelVerticalPadding", "startIconIndicatorHorizontalPadding", "startIconIndicatorVerticalPadding", "noLabelIndicatorPadding", "itemHorizontalPadding", "AnimatedNavigationItem-DQd_Gtc", "(ZLkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/graphics/Shape;FLandroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/TextStyle;FFFFFFFFLandroidx/compose/material3/NavigationItemColors;Landroidx/compose/ui/Modifier;ZLkotlin/jvm/functions/Function2;ILandroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;III)V", "NavigationItemLayout", "Landroidx/compose/foundation/interaction/InteractionSource;", "indicatorColor", "Landroidx/compose/ui/graphics/Color;", "indicatorAnimationProgress", "", "NavigationItemLayout-KmRX-Dg", "(Landroidx/compose/foundation/interaction/InteractionSource;JLandroidx/compose/ui/graphics/Shape;Lkotlin/jvm/functions/Function2;ILkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function0;FFFFFLandroidx/compose/runtime/Composer;II)V", "AnimatedNavigationItemLayout", "iconPositionProgress", "AnimatedNavigationItemLayout-he0WsC4", "(Landroidx/compose/foundation/interaction/InteractionSource;JLandroidx/compose/ui/graphics/Shape;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function2;ILkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function2;FFFFFFFFLandroidx/compose/runtime/Composer;II)V", "placeIcon", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "iconPlaceable", "Landroidx/compose/ui/layout/Placeable;", "indicatorRipplePlaceable", "indicatorPlaceable", "constraints", "Landroidx/compose/ui/unit/Constraints;", "placeIcon-X9ElhV4", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;J)Landroidx/compose/ui/layout/MeasureResult;", "placeLabelAndTopIcon", "labelPlaceable", "placeLabelAndTopIcon-qoqLrGI", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;JFFF)Landroidx/compose/ui/layout/MeasureResult;", "placeLabelAndStartIcon", "placeLabelAndStartIcon-nru01g4", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;JF)Landroidx/compose/ui/layout/MeasureResult;", "placeAnimatedLabelAndIcon", "", "placeAnimatedLabelAndIcon-2QYhCQ8", "(Landroidx/compose/ui/layout/MeasureScope;ILkotlin/jvm/functions/Function0;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;IJFFFFFFF)Landroidx/compose/ui/layout/MeasureResult;", "StyledLabel", "content", "(ZLandroidx/compose/ui/text/TextStyle;Landroidx/compose/material3/NavigationItemColors;ZLkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "animateIndicatorProgressAsState", "Landroidx/compose/runtime/State;", "(ZLandroidx/compose/runtime/Composer;I)Landroidx/compose/runtime/State;", "IndicatorRipple", "(Landroidx/compose/foundation/interaction/InteractionSource;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/runtime/Composer;I)V", "Indicator", "Indicator-3J-VO9M", "(JLandroidx/compose/ui/graphics/Shape;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "IndicatorRippleLayoutIdTag", "", "IndicatorLayoutIdTag", "IconLayoutIdTag", "LabelLayoutIdTag", "IndicatorVerticalOffset", "F", "material3", "itemWidth", "textStyle"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class NavigationItemKt {
    private static final String IconLayoutIdTag = "icon";
    private static final String IndicatorLayoutIdTag = "indicator";
    private static final String IndicatorRippleLayoutIdTag = "indicatorRipple";
    private static final float IndicatorVerticalOffset = Dp.m8150constructorimpl(12);
    private static final String LabelLayoutIdTag = "label";

    static final Unit AnimatedNavigationItemLayout_he0WsC4$lambda$35(InteractionSource interactionSource, long j, Shape shape, Function0 function0, Function2 function2, int i, Function0 function02, Function2 function22, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, int i2, int i3, Composer composer, int i4) {
        m2757AnimatedNavigationItemLayouthe0WsC4(interactionSource, j, shape, function0, function2, i, function02, function22, f, f2, f3, f4, f5, f6, f7, f8, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), RecomposeScopeImplKt.updateChangedFlags(i3));
        return Unit.INSTANCE;
    }

    static final Unit AnimatedNavigationItem_DQd_Gtc$lambda$27(boolean z, Function0 function0, Function2 function2, Shape shape, float f, TextStyle textStyle, TextStyle textStyle2, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, NavigationItemColors navigationItemColors, Modifier modifier, boolean z2, Function2 function22, int i, MutableInteractionSource mutableInteractionSource, int i2, int i3, int i4, Composer composer, int i5) {
        m2756AnimatedNavigationItemDQd_Gtc(z, function0, function2, shape, f, textStyle, textStyle2, f2, f3, f4, f5, f6, f7, f8, f9, navigationItemColors, modifier, z2, function22, i, mutableInteractionSource, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), RecomposeScopeImplKt.updateChangedFlags(i3), RecomposeScopeImplKt.updateChangedFlags(i4));
        return Unit.INSTANCE;
    }

    static final Unit IndicatorRipple$lambda$42(InteractionSource interactionSource, Shape shape, int i, Composer composer, int i2) {
        IndicatorRipple(interactionSource, shape, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit Indicator_3J_VO9M$lambda$45(long j, Shape shape, Function0 function0, int i, Composer composer, int i2) {
        m2758Indicator3JVO9M(j, shape, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit NavigationItemLayout_KmRX_Dg$lambda$31(InteractionSource interactionSource, long j, Shape shape, Function2 function2, int i, Function2 function22, Function0 function0, float f, float f2, float f3, float f4, float f5, int i2, int i3, Composer composer, int i4) {
        m2760NavigationItemLayoutKmRXDg(interactionSource, j, shape, function2, i, function22, function0, f, f2, f3, f4, f5, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), RecomposeScopeImplKt.updateChangedFlags(i3));
        return Unit.INSTANCE;
    }

    static final Unit NavigationItem_8Df7sds$lambda$10(boolean z, Function0 function0, Function2 function2, TextStyle textStyle, Shape shape, float f, float f2, float f3, float f4, float f5, float f6, NavigationItemColors navigationItemColors, Modifier modifier, boolean z2, Function2 function22, int i, MutableInteractionSource mutableInteractionSource, int i2, int i3, Composer composer, int i4) {
        m2759NavigationItem8Df7sds(z, function0, function2, textStyle, shape, f, f2, f3, f4, f5, f6, navigationItemColors, modifier, z2, function22, i, mutableInteractionSource, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), RecomposeScopeImplKt.updateChangedFlags(i3));
        return Unit.INSTANCE;
    }

    static final Unit StyledLabel$lambda$41(boolean z, TextStyle textStyle, NavigationItemColors navigationItemColors, boolean z2, Function2 function2, int i, Composer composer, int i2) {
        StyledLabel(z, textStyle, navigationItemColors, z2, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: NavigationItem-8Df7sds */
    public static final void m2759NavigationItem8Df7sds(final boolean selected, final Function0<Unit> function0, final Function2<? super Composer, ? super Integer, Unit> function2, final TextStyle labelTextStyle, final Shape indicatorShape, final float indicatorWidth, final float indicatorHorizontalPadding, final float indicatorVerticalPadding, final float indicatorToLabelVerticalPadding, final float startIconToLabelHorizontalPadding, final float topIconItemVerticalPadding, final NavigationItemColors colors, final Modifier modifier, final boolean enabled, final Function2<? super Composer, ? super Integer, Unit> function22, final int iconPosition, final MutableInteractionSource interactionSource, Composer $composer, final int $changed, final int $changed1) {
        TextStyle textStyle;
        Composer $composer2;
        int $dirty;
        int $dirty1;
        Function2 styledLabel;
        Function0<ComposeUiNode> function02;
        Composer $composer3 = $composer.startRestartGroup(2075155418);
        ComposerKt.sourceInformation($composer3, "C(NavigationItem)N(selected,onClick,icon,labelTextStyle,indicatorShape,indicatorWidth:c#ui.unit.Dp,indicatorHorizontalPadding:c#ui.unit.Dp,indicatorVerticalPadding:c#ui.unit.Dp,indicatorToLabelVerticalPadding:c#ui.unit.Dp,startIconToLabelHorizontalPadding:c#ui.unit.Dp,topIconItemVerticalPadding:c#ui.unit.Dp,colors,modifier,enabled,label,iconPosition:c#material3.NavigationItemIconPosition,interactionSource)247@10748L94,257@11065L33,270@11488L7,271@11562L7,273@11612L24,259@11104L2381:NavigationItem.kt#uh7d8r");
        int $dirty2 = $changed;
        int $dirty12 = $changed1;
        if (($changed & 6) == 0) {
            $dirty2 |= $composer3.changed(selected) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty2 |= $composer3.changedInstance(function0) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty2 |= $composer3.changedInstance(function2) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            textStyle = labelTextStyle;
            $dirty2 |= $composer3.changed(textStyle) ? 2048 : 1024;
        } else {
            textStyle = labelTextStyle;
        }
        if (($changed & 24576) == 0) {
            $dirty2 |= $composer3.changed(indicatorShape) ? 16384 : 8192;
        }
        if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty2 |= $composer3.changed(indicatorWidth) ? 131072 : 65536;
        }
        if (($changed & 1572864) == 0) {
            $dirty2 |= $composer3.changed(indicatorHorizontalPadding) ? 1048576 : 524288;
        }
        if (($changed & 12582912) == 0) {
            $dirty2 |= $composer3.changed(indicatorVerticalPadding) ? 8388608 : 4194304;
        }
        if (($changed & 100663296) == 0) {
            $dirty2 |= $composer3.changed(indicatorToLabelVerticalPadding) ? 67108864 : GroupFlagsKt.HasAuxSlotFlag;
        }
        if (($changed & 805306368) == 0) {
            $dirty2 |= $composer3.changed(startIconToLabelHorizontalPadding) ? GroupFlagsKt.HasMovableContentFlag : GroupFlagsKt.IsMovableContentFlag;
        }
        if (($changed1 & 6) == 0) {
            $dirty12 |= $composer3.changed(topIconItemVerticalPadding) ? 4 : 2;
        }
        if (($changed1 & 48) == 0) {
            $dirty12 |= $composer3.changed(colors) ? 32 : 16;
        }
        if (($changed1 & 384) == 0) {
            $dirty12 |= $composer3.changed(modifier) ? 256 : 128;
        }
        if (($changed1 & 3072) == 0) {
            $dirty12 |= $composer3.changed(enabled) ? 2048 : 1024;
        }
        if (($changed1 & 24576) == 0) {
            $dirty12 |= $composer3.changedInstance(function22) ? 16384 : 8192;
        }
        if (($changed1 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty12 |= $composer3.changed(iconPosition) ? 131072 : 65536;
        }
        if ((1572864 & $changed1) == 0) {
            $dirty12 |= $composer3.changed(interactionSource) ? 1048576 : 524288;
        }
        int $dirty13 = $dirty12;
        if ($composer3.shouldExecute(((306783379 & $dirty2) == 306783378 && (599187 & $dirty13) == 599186) ? false : true, $dirty2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2075155418, $dirty2, $dirty13, "androidx.compose.material3.NavigationItem (NavigationItem.kt:245)");
            }
            final long iconColor = colors.m2745iconColorWaAFU9c(selected, enabled);
            Function2 styledIcon = ComposableLambdaKt.rememberComposableLambda(1119868672, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationItemKt$NavigationItem$styledIcon$1
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer $composer4, int $changed2) {
                    ComposerKt.sourceInformation($composer4, "C248@10758L78:NavigationItem.kt#uh7d8r");
                    if (!$composer4.shouldExecute(($changed2 & 3) != 2, $changed2 & 1)) {
                        $composer4.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1119868672, $changed2, -1, "androidx.compose.material3.NavigationItem.<anonymous> (NavigationItem.kt:248)");
                    }
                    CompositionLocalKt.CompositionLocalProvider(ContentColorKt.getLocalContentColor().provides(Color.m5303boximpl(iconColor)), function2, $composer4, ProvidedValue.$stable);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, $composer3, 54);
            if (function22 == null) {
                $composer3.startReplaceGroup(-803302356);
                $composer3.endReplaceGroup();
                $dirty = $dirty2;
                $dirty1 = $dirty13;
                styledLabel = null;
            } else {
                $composer3.startReplaceGroup(-803266737);
                ComposerKt.sourceInformation($composer3, "254@10967L65");
                $dirty = $dirty2;
                final TextStyle textStyle2 = textStyle;
                $dirty1 = $dirty13;
                Function2 function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(1062206119, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationItemKt$NavigationItem$styledLabel$1
                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                        invoke(composer, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer $composer4, int $changed2) {
                        ComposerKt.sourceInformation($composer4, "C254@10969L61:NavigationItem.kt#uh7d8r");
                        if (!$composer4.shouldExecute(($changed2 & 3) != 2, $changed2 & 1)) {
                            $composer4.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1062206119, $changed2, -1, "androidx.compose.material3.NavigationItem.<anonymous> (NavigationItem.kt:254)");
                        }
                        NavigationItemKt.StyledLabel(selected, textStyle2, colors, enabled, function22, $composer4, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }, $composer3, 54);
                $composer3.endReplaceGroup();
                styledLabel = function2RememberComposableLambda;
            }
            ComposerKt.sourceInformationMarkerStart($composer3, 1359565019, "CC(remember):NavigationItem.kt#9igjgp");
            Object it$iv = $composer3.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = SnapshotIntStateKt.mutableIntStateOf(0);
                $composer3.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            final MutableIntState itemWidth$delegate = (MutableIntState) it$iv;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Modifier modifierM1340selectableO2vRcR0 = SelectableKt.m1340selectableO2vRcR0(modifier, selected, interactionSource, null, enabled, Role.m7336boximpl(Role.INSTANCE.m7350getTabo7Vup1c()), function0);
            ProvidableCompositionLocal<Dp> localMinimumInteractiveComponentSize = InteractiveComponentSizeKt.getLocalMinimumInteractiveComponentSize();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = $composer3.consume(localMinimumInteractiveComponentSize);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            float fM8164unboximpl = ((Dp) objConsume).m8164unboximpl();
            ProvidableCompositionLocal<Dp> localMinimumInteractiveComponentSize2 = InteractiveComponentSizeKt.getLocalMinimumInteractiveComponentSize();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume2 = $composer3.consume(localMinimumInteractiveComponentSize2);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Modifier modifierM1099defaultMinSizeVpY3zN4 = SizeKt.m1099defaultMinSizeVpY3zN4(modifierM1340selectableO2vRcR0, fM8164unboximpl, ((Dp) objConsume2).m8164unboximpl());
            ComposerKt.sourceInformationMarkerStart($composer3, 1359582514, "CC(remember):NavigationItem.kt#9igjgp");
            Object it$iv2 = $composer3.rememberedValue();
            if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                Object value$iv2 = new Function1() { // from class: androidx.compose.material3.NavigationItemKt$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return NavigationItemKt.NavigationItem_8Df7sds$lambda$4$lambda$3(itemWidth$delegate, (IntSize) obj);
                    }
                };
                $composer3.updateRememberedValue(value$iv2);
                it$iv2 = value$iv2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Modifier modifier$iv = OnRemeasuredModifierKt.onSizeChanged(modifierM1099defaultMinSizeVpY3zN4, (Function1) it$iv2);
            Alignment contentAlignment$iv = Alignment.INSTANCE.getCenter();
            ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
            MeasurePolicy measurePolicy$iv = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, true);
            int $changed$iv$iv = (432 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
            CompositionLocalMap localMap$iv$iv = $composer3.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer3, modifier$iv);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                function02 = constructor;
                $composer3.createNode(function02);
            } else {
                function02 = constructor;
                $composer3.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4433constructorimpl($composer3);
            Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
            }
            Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
            int i = ($changed$iv$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            int i2 = ((432 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, 484584471, "C277@11772L41,304@13049L54,297@12698L781:NavigationItem.kt#uh7d8r");
            final State<Float> stateAnimateIndicatorProgressAsState = animateIndicatorProgressAsState(selected, $composer3, $dirty & 14);
            MappedInteractionSource offsetInteractionSource = null;
            if (NavigationItemIconPosition.m2750equalsimpl0(iconPosition, NavigationItemIconPosition.INSTANCE.m2755getTopxw1Ddg())) {
                $composer3.startReplaceGroup(484755993);
                ComposerKt.sourceInformation($composer3, "284@12247L7,292@12542L136");
                ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                Object objConsume3 = $composer3.consume(localDensity);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                Density $this$NavigationItem_8Df7sds_u24lambda_u249_u24lambda_u245 = (Density) objConsume3;
                float x$iv = (NavigationItem_8Df7sds$lambda$1(itemWidth$delegate) - $this$NavigationItem_8Df7sds_u24lambda_u249_u24lambda_u245.mo426roundToPx0680j_4(indicatorWidth)) / 2.0f;
                float y$iv = $this$NavigationItem_8Df7sds_u24lambda_u249_u24lambda_u245.mo432toPx0680j_4(IndicatorVerticalOffset);
                long v1$iv$iv = Float.floatToRawIntBits(x$iv);
                long v2$iv$iv = Float.floatToRawIntBits(y$iv);
                long deltaOffset = Offset.m5060constructorimpl((v1$iv$iv << 32) | (v2$iv$iv & 4294967295L));
                Unit unit = Unit.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer3, -1092722808, "CC(remember):NavigationItem.kt#9igjgp");
                boolean invalid$iv = (($dirty1 & 3670016) == 1048576) | $composer3.changed(deltaOffset);
                Object value$iv3 = $composer3.rememberedValue();
                if (invalid$iv || value$iv3 == Composer.INSTANCE.getEmpty()) {
                    value$iv3 = new MappedInteractionSource(interactionSource, deltaOffset, null);
                    $composer3.updateRememberedValue(value$iv3);
                }
                ComposerKt.sourceInformationMarkerEnd($composer3);
                offsetInteractionSource = (MappedInteractionSource) value$iv3;
                $composer3.endReplaceGroup();
            } else {
                $composer3.startReplaceGroup(485471938);
                $composer3.endReplaceGroup();
            }
            MutableInteractionSource mutableInteractionSource = offsetInteractionSource != null ? offsetInteractionSource : interactionSource;
            long selectedIndicatorColor = colors.getSelectedIndicatorColor();
            ComposerKt.sourceInformationMarkerStart($composer3, -1092706666, "CC(remember):NavigationItem.kt#9igjgp");
            boolean invalid$iv2 = $composer3.changed(stateAnimateIndicatorProgressAsState);
            InteractionSource interactionSource2 = mutableInteractionSource;
            Object value$iv4 = $composer3.rememberedValue();
            if (invalid$iv2 || value$iv4 == Composer.INSTANCE.getEmpty()) {
                value$iv4 = new Function0() { // from class: androidx.compose.material3.NavigationItemKt$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return Float.valueOf(RangesKt.coerceAtLeast(((Number) stateAnimateIndicatorProgressAsState.getValue()).floatValue(), 0.0f));
                    }
                };
                $composer3.updateRememberedValue(value$iv4);
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer2 = $composer3;
            m2760NavigationItemLayoutKmRXDg(interactionSource2, selectedIndicatorColor, indicatorShape, styledIcon, iconPosition, styledLabel, (Function0) value$iv4, indicatorHorizontalPadding, indicatorVerticalPadding, indicatorToLabelVerticalPadding, startIconToLabelHorizontalPadding, topIconItemVerticalPadding, $composer3, (($dirty >> 6) & 896) | 3072 | (($dirty1 >> 3) & 57344) | (($dirty << 3) & 29360128) | (($dirty << 3) & 234881024) | (($dirty << 3) & 1879048192), (($dirty >> 27) & 14) | (($dirty1 << 3) & 112));
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.NavigationItemKt$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return NavigationItemKt.NavigationItem_8Df7sds$lambda$10(selected, function0, function2, labelTextStyle, indicatorShape, indicatorWidth, indicatorHorizontalPadding, indicatorVerticalPadding, indicatorToLabelVerticalPadding, startIconToLabelHorizontalPadding, topIconItemVerticalPadding, colors, modifier, enabled, function22, iconPosition, interactionSource, $changed, $changed1, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final int NavigationItem_8Df7sds$lambda$1(MutableIntState $itemWidth$delegate) {
        MutableIntState $this$getValue$iv = $itemWidth$delegate;
        return $this$getValue$iv.getIntValue();
    }

    static final Unit NavigationItem_8Df7sds$lambda$4$lambda$3(MutableIntState $itemWidth$delegate, IntSize it) {
        long arg0$iv = it.m8325unboximpl();
        $itemWidth$delegate.setIntValue((int) (arg0$iv >> 32));
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:451:0x040f  */
    /* JADX WARN: Removed duplicated region for block: B:452:0x0412  */
    /* JADX WARN: Removed duplicated region for block: B:455:0x0451  */
    /* JADX WARN: Removed duplicated region for block: B:456:0x0466  */
    /* JADX WARN: Removed duplicated region for block: B:459:0x0472  */
    /* JADX WARN: Removed duplicated region for block: B:460:0x04b3  */
    /* JADX WARN: Removed duplicated region for block: B:463:0x04db  */
    /* JADX WARN: Removed duplicated region for block: B:476:0x0593  */
    /* JADX WARN: Removed duplicated region for block: B:478:0x05a4  */
    /* JADX WARN: Removed duplicated region for block: B:479:0x05a8  */
    /* JADX WARN: Removed duplicated region for block: B:490:0x05ff  */
    /* JADX WARN: Removed duplicated region for block: B:494:0x060b  */
    /* JADX WARN: Removed duplicated region for block: B:498:0x06ab  */
    /* JADX INFO: renamed from: AnimatedNavigationItem-DQd_Gtc */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void m2756AnimatedNavigationItemDQd_Gtc(final boolean r62, final kotlin.jvm.functions.Function0<kotlin.Unit> r63, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r64, final androidx.compose.ui.graphics.Shape r65, final float r66, final androidx.compose.ui.text.TextStyle r67, final androidx.compose.ui.text.TextStyle r68, final float r69, final float r70, final float r71, final float r72, final float r73, final float r74, final float r75, final float r76, final androidx.compose.material3.NavigationItemColors r77, final androidx.compose.ui.Modifier r78, final boolean r79, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r80, final int r81, final androidx.compose.foundation.interaction.MutableInteractionSource r82, androidx.compose.runtime.Composer r83, final int r84, final int r85, final int r86) {
        /*
            Method dump skipped, instruction units count: 1791
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.NavigationItemKt.m2756AnimatedNavigationItemDQd_Gtc(boolean, kotlin.jvm.functions.Function0, kotlin.jvm.functions.Function2, androidx.compose.ui.graphics.Shape, float, androidx.compose.ui.text.TextStyle, androidx.compose.ui.text.TextStyle, float, float, float, float, float, float, float, float, androidx.compose.material3.NavigationItemColors, androidx.compose.ui.Modifier, boolean, kotlin.jvm.functions.Function2, int, androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.runtime.Composer, int, int, int):void");
    }

    private static final int AnimatedNavigationItem_DQd_Gtc$lambda$12(MutableIntState $itemWidth$delegate) {
        MutableIntState $this$getValue$iv = $itemWidth$delegate;
        return $this$getValue$iv.getIntValue();
    }

    static final Unit AnimatedNavigationItem_DQd_Gtc$lambda$15$lambda$14(MutableIntState $itemWidth$delegate, IntSize it) {
        long arg0$iv = it.m8325unboximpl();
        $itemWidth$delegate.setIntValue((int) (arg0$iv >> 32));
        return Unit.INSTANCE;
    }

    private static final float AnimatedNavigationItem_DQd_Gtc$lambda$26$lambda$16(State<Float> state) {
        Object thisObj$iv = state.getValue();
        return ((Number) thisObj$iv).floatValue();
    }

    public static final TextStyle AnimatedNavigationItem_DQd_Gtc$lambda$26$lambda$19(State<TextStyle> state) {
        Object thisObj$iv = state.getValue();
        return (TextStyle) thisObj$iv;
    }

    static final TextStyle AnimatedNavigationItem_DQd_Gtc$lambda$26$lambda$18$lambda$17(boolean $isIconPositionTop, TextStyle $topIconLabelTextStyle, TextStyle $startIconLabelTextStyle, State $iconPositionProgress$delegate) {
        return (!$isIconPositionTop || AnimatedNavigationItem_DQd_Gtc$lambda$26$lambda$16($iconPositionProgress$delegate) >= 0.5f) ? $startIconLabelTextStyle : $topIconLabelTextStyle;
    }

    /* JADX WARN: Removed duplicated region for block: B:308:0x02df  */
    /* JADX WARN: Removed duplicated region for block: B:311:0x02eb  */
    /* JADX WARN: Removed duplicated region for block: B:312:0x02f1  */
    /* JADX WARN: Removed duplicated region for block: B:323:0x03ab  */
    /* JADX WARN: Removed duplicated region for block: B:339:0x04e4  */
    /* JADX WARN: Removed duplicated region for block: B:342:0x0503  */
    /* JADX INFO: renamed from: NavigationItemLayout-KmRX-Dg */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static final void m2760NavigationItemLayoutKmRXDg(final androidx.compose.foundation.interaction.InteractionSource r50, final long r51, final androidx.compose.ui.graphics.Shape r53, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r54, final int r55, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r56, final kotlin.jvm.functions.Function0<java.lang.Float> r57, final float r58, final float r59, final float r60, final float r61, final float r62, androidx.compose.runtime.Composer r63, final int r64, final int r65) {
        /*
            Method dump skipped, instruction units count: 1344
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.NavigationItemKt.m2760NavigationItemLayoutKmRXDg(androidx.compose.foundation.interaction.InteractionSource, long, androidx.compose.ui.graphics.Shape, kotlin.jvm.functions.Function2, int, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function0, float, float, float, float, float, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: renamed from: AnimatedNavigationItemLayout-he0WsC4 */
    private static final void m2757AnimatedNavigationItemLayouthe0WsC4(final InteractionSource interactionSource, final long indicatorColor, final Shape indicatorShape, final Function0<Float> function0, final Function2<? super Composer, ? super Integer, Unit> function2, final int iconPosition, final Function0<Float> function02, final Function2<? super Composer, ? super Integer, Unit> function22, final float topIconIndicatorHorizontalPadding, final float topIconIndicatorVerticalPadding, final float topIconIndicatorToLabelVerticalPadding, final float startIconIndicatorHorizontalPadding, final float startIconIndicatorVerticalPadding, final float noLabelIndicatorPadding, final float startIconToLabelHorizontalPadding, final float itemHorizontalPadding, Composer $composer, final int $changed, final int $changed1) {
        long j;
        Function0<Float> function03;
        int i;
        Function0<Float> function04;
        float f;
        Object topIconOrIconOnlyMeasurePolicy;
        Function0<ComposeUiNode> function05;
        Function0<ComposeUiNode> function06;
        Function0<ComposeUiNode> function07;
        int $changed$iv;
        Composer $composer2 = $composer.startRestartGroup(94433406);
        ComposerKt.sourceInformation($composer2, "C(AnimatedNavigationItemLayout)N(interactionSource,indicatorColor:c#ui.graphics.Color,indicatorShape,indicatorAnimationProgress,icon,iconPosition:c#material3.NavigationItemIconPosition,iconPositionProgress,label,topIconIndicatorHorizontalPadding:c#ui.unit.Dp,topIconIndicatorVerticalPadding:c#ui.unit.Dp,topIconIndicatorToLabelVerticalPadding:c#ui.unit.Dp,startIconIndicatorHorizontalPadding:c#ui.unit.Dp,startIconIndicatorVerticalPadding:c#ui.unit.Dp,noLabelIndicatorPadding:c#ui.unit.Dp,startIconToLabelHorizontalPadding:c#ui.unit.Dp,itemHorizontalPadding:c#ui.unit.Dp)509@21184L2129:NavigationItem.kt#uh7d8r");
        int $dirty = $changed;
        int $dirty1 = $changed1;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(interactionSource) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            j = indicatorColor;
            $dirty |= $composer2.changed(j) ? 32 : 16;
        } else {
            j = indicatorColor;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer2.changed(indicatorShape) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            function03 = function0;
            $dirty |= $composer2.changedInstance(function03) ? 2048 : 1024;
        } else {
            function03 = function0;
        }
        if (($changed & 24576) == 0) {
            $dirty |= $composer2.changedInstance(function2) ? 16384 : 8192;
        }
        if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            i = 196608;
            $dirty |= $composer2.changed(iconPosition) ? 131072 : 65536;
        } else {
            i = 196608;
        }
        if (($changed & 1572864) == 0) {
            function04 = function02;
            $dirty |= $composer2.changedInstance(function04) ? 1048576 : 524288;
        } else {
            function04 = function02;
        }
        if (($changed & 12582912) == 0) {
            $dirty |= $composer2.changedInstance(function22) ? 8388608 : 4194304;
        }
        if (($changed & 100663296) == 0) {
            f = topIconIndicatorHorizontalPadding;
            $dirty |= $composer2.changed(f) ? 67108864 : GroupFlagsKt.HasAuxSlotFlag;
        } else {
            f = topIconIndicatorHorizontalPadding;
        }
        if (($changed & 805306368) == 0) {
            $dirty |= $composer2.changed(topIconIndicatorVerticalPadding) ? GroupFlagsKt.HasMovableContentFlag : GroupFlagsKt.IsMovableContentFlag;
        }
        if (($changed1 & 6) == 0) {
            $dirty1 |= $composer2.changed(topIconIndicatorToLabelVerticalPadding) ? 4 : 2;
        }
        if (($changed1 & 48) == 0) {
            $dirty1 |= $composer2.changed(startIconIndicatorHorizontalPadding) ? 32 : 16;
        }
        if (($changed1 & 384) == 0) {
            $dirty1 |= $composer2.changed(startIconIndicatorVerticalPadding) ? 256 : 128;
        }
        if (($changed1 & 3072) == 0) {
            $dirty1 |= $composer2.changed(noLabelIndicatorPadding) ? 2048 : 1024;
        }
        if (($changed1 & 24576) == 0) {
            $dirty1 |= $composer2.changed(startIconToLabelHorizontalPadding) ? 16384 : 8192;
        }
        if (($changed1 & i) == 0) {
            $dirty1 |= $composer2.changed(itemHorizontalPadding) ? 131072 : 65536;
        }
        if ($composer2.shouldExecute((($dirty & 306783379) == 306783378 && (74899 & $dirty1) == 74898) ? false : true, $dirty & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(94433406, $dirty, $dirty1, "androidx.compose.material3.AnimatedNavigationItemLayout (NavigationItem.kt:508)");
            }
            Modifier modifierBadgeBounds = BadgeKt.badgeBounds(Modifier.INSTANCE);
            if (function22 != null) {
                topIconOrIconOnlyMeasurePolicy = new AnimatedMeasurePolicy(iconPosition, function04, function03, f, topIconIndicatorVerticalPadding, topIconIndicatorToLabelVerticalPadding, startIconIndicatorHorizontalPadding, startIconIndicatorVerticalPadding, startIconToLabelHorizontalPadding, itemHorizontalPadding, null);
            } else {
                topIconOrIconOnlyMeasurePolicy = new TopIconOrIconOnlyMeasurePolicy(false, function0, noLabelIndicatorPadding, noLabelIndicatorPadding, Dp.m8150constructorimpl(0), Dp.m8150constructorimpl(0), null);
            }
            ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
            int compositeKeyHash$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
            CompositionLocalMap localMap$iv = $composer2.getCurrentCompositionLocalMap();
            Modifier materialized$iv = ComposedModifierKt.materializeModifier($composer2, modifierBadgeBounds);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int $dirty2 = $dirty;
            int $changed$iv$iv = ((0 << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
            if (!($composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer2.startReusableNode();
            if ($composer2.getInserting()) {
                function05 = constructor;
                $composer2.createNode(function05);
            } else {
                function05 = constructor;
                $composer2.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv = Updater.m4433constructorimpl($composer2);
            Updater.m4441setimpl($this$Layout_u24lambda_u240$iv, topIconOrIconOnlyMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4441setimpl($this$Layout_u24lambda_u240$iv, localMap$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv))) {
                $this$Layout_u24lambda_u240$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv));
                $this$Layout_u24lambda_u240$iv.apply(Integer.valueOf(compositeKeyHash$iv), setCompositeKeyHash);
            }
            Updater.m4441setimpl($this$Layout_u24lambda_u240$iv, materialized$iv, ComposeUiNode.INSTANCE.getSetModifier());
            int i2 = ($changed$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, -988347483, "C513@21311L50,516@21564L69,518@21647L50:NavigationItem.kt#uh7d8r");
            IndicatorRipple(interactionSource, indicatorShape, $composer2, ($dirty2 & 14) | (($dirty2 >> 3) & 112));
            m2758Indicator3JVO9M(j, indicatorShape, function0, $composer2, (($dirty2 >> 3) & 14) | (($dirty2 >> 3) & 112) | (($dirty2 >> 3) & 896));
            Modifier modifier$iv = LayoutIdKt.layoutId(Modifier.INSTANCE, IconLayoutIdTag);
            ComposerKt.sourceInformationMarkerStart($composer2, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
            Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
            MeasurePolicy measurePolicy$iv = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
            int $changed$iv$iv2 = (6 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
            CompositionLocalMap localMap$iv$iv = $composer2.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer2, modifier$iv);
            Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv$iv = (($changed$iv$iv2 << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
            if (!($composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer2.startReusableNode();
            if ($composer2.getInserting()) {
                function06 = constructor2;
                $composer2.createNode(function06);
            } else {
                function06 = constructor2;
                $composer2.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4433constructorimpl($composer2);
            Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash2);
            }
            Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
            int i3 = ($changed$iv$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            int i4 = ((6 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -1416456196, "C518@21689L6:NavigationItem.kt#uh7d8r");
            function2.invoke($composer2, Integer.valueOf(($dirty2 >> 12) & 14));
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (function22 != null) {
                $composer2.startReplaceGroup(-987944825);
                ComposerKt.sourceInformation($composer2, "521@21748L52");
                Modifier modifier$iv2 = LayoutIdKt.layoutId(Modifier.INSTANCE, LabelLayoutIdTag);
                ComposerKt.sourceInformationMarkerStart($composer2, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
                Alignment contentAlignment$iv2 = Alignment.INSTANCE.getTopStart();
                MeasurePolicy measurePolicy$iv2 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv2, false);
                int $changed$iv$iv3 = (6 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                int compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
                CompositionLocalMap localMap$iv$iv2 = $composer2.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv2 = ComposedModifierKt.materializeModifier($composer2, modifier$iv2);
                Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
                int $changed$iv$iv$iv2 = (($changed$iv$iv3 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                if (!($composer2.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer2.startReusableNode();
                if ($composer2.getInserting()) {
                    function07 = constructor3;
                    $composer2.createNode(function07);
                } else {
                    function07 = constructor3;
                    $composer2.useNode();
                }
                Composer $this$Layout_u24lambda_u240$iv$iv2 = Updater.m4433constructorimpl($composer2);
                Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash3 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if ($this$Layout_u24lambda_u240$iv$iv2.getInserting()) {
                    $changed$iv = 6;
                } else {
                    $changed$iv = 6;
                    if (!Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                    }
                    Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv2, ComposeUiNode.INSTANCE.getSetModifier());
                    int i5 = ($changed$iv$iv$iv2 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer2, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                    BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
                    int i6 = (($changed$iv >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, 157288374, "C521@21791L7:NavigationItem.kt#uh7d8r");
                    function22.invoke($composer2, Integer.valueOf(($dirty2 >> 21) & 14));
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $composer2.endReplaceGroup();
                }
                $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
                $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash3);
                Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv2, ComposeUiNode.INSTANCE.getSetModifier());
                int i52 = ($changed$iv$iv$iv2 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer2, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance22 = BoxScopeInstance.INSTANCE;
                int i62 = (($changed$iv >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, 157288374, "C521@21791L7:NavigationItem.kt#uh7d8r");
                function22.invoke($composer2, Integer.valueOf(($dirty2 >> 21) & 14));
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endReplaceGroup();
            } else {
                $composer2.startReplaceGroup(-987864101);
                $composer2.endReplaceGroup();
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.NavigationItemKt$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return NavigationItemKt.AnimatedNavigationItemLayout_he0WsC4$lambda$35(interactionSource, indicatorColor, indicatorShape, function0, function2, iconPosition, function02, function22, topIconIndicatorHorizontalPadding, topIconIndicatorVerticalPadding, topIconIndicatorToLabelVerticalPadding, startIconIndicatorHorizontalPadding, startIconIndicatorVerticalPadding, noLabelIndicatorPadding, startIconToLabelHorizontalPadding, itemHorizontalPadding, $changed, $changed1, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: placeIcon-X9ElhV4 */
    public static final MeasureResult m2766placeIconX9ElhV4(MeasureScope $this$placeIcon_u2dX9ElhV4, final Placeable iconPlaceable, final Placeable indicatorRipplePlaceable, final Placeable indicatorPlaceable, long constraints) {
        int width = ConstraintsKt.m8120constrainWidthK40F9xA(constraints, indicatorRipplePlaceable.getWidth());
        int height = ConstraintsKt.m8119constrainHeightK40F9xA(constraints, indicatorRipplePlaceable.getHeight());
        final int indicatorX = (width - indicatorPlaceable.getWidth()) / 2;
        final int indicatorY = (height - indicatorPlaceable.getHeight()) / 2;
        final int iconX = (width - iconPlaceable.getWidth()) / 2;
        final int iconY = (height - iconPlaceable.getHeight()) / 2;
        final int rippleX = (width - indicatorRipplePlaceable.getWidth()) / 2;
        final int rippleY = (height - indicatorRipplePlaceable.getHeight()) / 2;
        return MeasureScope.layout$default($this$placeIcon_u2dX9ElhV4, width, height, null, new Function1() { // from class: androidx.compose.material3.NavigationItemKt$$ExternalSyntheticLambda10
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return NavigationItemKt.placeIcon_X9ElhV4$lambda$36(indicatorPlaceable, indicatorX, indicatorY, iconPlaceable, iconX, iconY, indicatorRipplePlaceable, rippleX, rippleY, (Placeable.PlacementScope) obj);
            }
        }, 4, null);
    }

    static final Unit placeIcon_X9ElhV4$lambda$36(Placeable $indicatorPlaceable, int $indicatorX, int $indicatorY, Placeable $iconPlaceable, int $iconX, int $iconY, Placeable $indicatorRipplePlaceable, int $rippleX, int $rippleY, Placeable.PlacementScope $this$layout) {
        Placeable.PlacementScope.placeRelative$default($this$layout, $indicatorPlaceable, $indicatorX, $indicatorY, 0.0f, 4, null);
        Placeable.PlacementScope.placeRelative$default($this$layout, $iconPlaceable, $iconX, $iconY, 0.0f, 4, null);
        Placeable.PlacementScope.placeRelative$default($this$layout, $indicatorRipplePlaceable, $rippleX, $rippleY, 0.0f, 4, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: placeLabelAndTopIcon-qoqLrGI */
    public static final MeasureResult m2768placeLabelAndTopIconqoqLrGI(MeasureScope $this$placeLabelAndTopIcon_u2dqoqLrGI, final Placeable labelPlaceable, final Placeable iconPlaceable, final Placeable indicatorRipplePlaceable, final Placeable indicatorPlaceable, long constraints, float indicatorToLabelVerticalPadding, float indicatorVerticalPadding, float topIconItemVerticalPadding) {
        int width = ConstraintsKt.m8120constrainWidthK40F9xA(constraints, Math.max(labelPlaceable.getWidth(), indicatorRipplePlaceable.getWidth()));
        float contentHeight = indicatorRipplePlaceable.getHeight() + $this$placeLabelAndTopIcon_u2dqoqLrGI.mo432toPx0680j_4(indicatorToLabelVerticalPadding) + labelPlaceable.getHeight();
        int height = ConstraintsKt.m8119constrainHeightK40F9xA(constraints, MathKt.roundToInt(($this$placeLabelAndTopIcon_u2dqoqLrGI.mo432toPx0680j_4(topIconItemVerticalPadding) * 2.0f) + contentHeight));
        final int iconY = $this$placeLabelAndTopIcon_u2dqoqLrGI.mo426roundToPx0680j_4(Dp.m8150constructorimpl(topIconItemVerticalPadding + indicatorVerticalPadding));
        final int iconX = (width - iconPlaceable.getWidth()) / 2;
        final int indicatorX = (width - indicatorPlaceable.getWidth()) / 2;
        final int indicatorY = iconY - $this$placeLabelAndTopIcon_u2dqoqLrGI.mo426roundToPx0680j_4(indicatorVerticalPadding);
        final int labelX = (width - labelPlaceable.getWidth()) / 2;
        final int labelY = iconY + iconPlaceable.getHeight() + $this$placeLabelAndTopIcon_u2dqoqLrGI.mo426roundToPx0680j_4(Dp.m8150constructorimpl(indicatorVerticalPadding + indicatorToLabelVerticalPadding));
        final int rippleX = (width - indicatorRipplePlaceable.getWidth()) / 2;
        return MeasureScope.layout$default($this$placeLabelAndTopIcon_u2dqoqLrGI, width, height, null, new Function1() { // from class: androidx.compose.material3.NavigationItemKt$$ExternalSyntheticLambda11
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return NavigationItemKt.placeLabelAndTopIcon_qoqLrGI$lambda$37(indicatorPlaceable, indicatorX, indicatorY, labelPlaceable, labelX, labelY, iconPlaceable, iconX, iconY, indicatorRipplePlaceable, rippleX, indicatorY, (Placeable.PlacementScope) obj);
            }
        }, 4, null);
    }

    static final Unit placeLabelAndTopIcon_qoqLrGI$lambda$37(Placeable $indicatorPlaceable, int $indicatorX, int $indicatorY, Placeable $labelPlaceable, int $labelX, int $labelY, Placeable $iconPlaceable, int $iconX, int $iconY, Placeable $indicatorRipplePlaceable, int $rippleX, int $rippleY, Placeable.PlacementScope $this$layout) {
        Placeable.PlacementScope.placeRelative$default($this$layout, $indicatorPlaceable, $indicatorX, $indicatorY, 0.0f, 4, null);
        Placeable.PlacementScope.placeRelative$default($this$layout, $labelPlaceable, $labelX, $labelY, 0.0f, 4, null);
        Placeable.PlacementScope.placeRelative$default($this$layout, $iconPlaceable, $iconX, $iconY, 0.0f, 4, null);
        Placeable.PlacementScope.placeRelative$default($this$layout, $indicatorRipplePlaceable, $rippleX, $rippleY, 0.0f, 4, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: placeLabelAndStartIcon-nru01g4 */
    public static final MeasureResult m2767placeLabelAndStartIconnru01g4(MeasureScope $this$placeLabelAndStartIcon_u2dnru01g4, final Placeable labelPlaceable, final Placeable iconPlaceable, final Placeable indicatorRipplePlaceable, final Placeable indicatorPlaceable, long constraints, float startIconToLabelHorizontalPadding) {
        int width = ConstraintsKt.m8120constrainWidthK40F9xA(constraints, indicatorRipplePlaceable.getWidth());
        int height = ConstraintsKt.m8119constrainHeightK40F9xA(constraints, indicatorRipplePlaceable.getHeight());
        final int indicatorX = (width - indicatorPlaceable.getWidth()) / 2;
        final int indicatorY = (height - indicatorPlaceable.getHeight()) / 2;
        final int iconY = (height - iconPlaceable.getHeight()) / 2;
        final int labelY = (height - labelPlaceable.getHeight()) / 2;
        int itemContentWidth = iconPlaceable.getWidth() + $this$placeLabelAndStartIcon_u2dnru01g4.mo426roundToPx0680j_4(startIconToLabelHorizontalPadding) + labelPlaceable.getWidth();
        final int iconX = (width - itemContentWidth) / 2;
        final int labelX = iconX + iconPlaceable.getWidth() + $this$placeLabelAndStartIcon_u2dnru01g4.mo426roundToPx0680j_4(startIconToLabelHorizontalPadding);
        final int rippleX = (width - indicatorRipplePlaceable.getWidth()) / 2;
        final int rippleY = (height - indicatorRipplePlaceable.getHeight()) / 2;
        return MeasureScope.layout$default($this$placeLabelAndStartIcon_u2dnru01g4, width, height, null, new Function1() { // from class: androidx.compose.material3.NavigationItemKt$$ExternalSyntheticLambda17
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return NavigationItemKt.placeLabelAndStartIcon_nru01g4$lambda$38(indicatorPlaceable, indicatorX, indicatorY, labelPlaceable, labelX, labelY, iconPlaceable, iconX, iconY, indicatorRipplePlaceable, rippleX, rippleY, (Placeable.PlacementScope) obj);
            }
        }, 4, null);
    }

    static final Unit placeLabelAndStartIcon_nru01g4$lambda$38(Placeable $indicatorPlaceable, int $indicatorX, int $indicatorY, Placeable $labelPlaceable, int $labelX, int $labelY, Placeable $iconPlaceable, int $iconX, int $iconY, Placeable $indicatorRipplePlaceable, int $rippleX, int $rippleY, Placeable.PlacementScope $this$layout) {
        Placeable.PlacementScope.placeRelative$default($this$layout, $indicatorPlaceable, $indicatorX, $indicatorY, 0.0f, 4, null);
        Placeable.PlacementScope.placeRelative$default($this$layout, $labelPlaceable, $labelX, $labelY, 0.0f, 4, null);
        Placeable.PlacementScope.placeRelative$default($this$layout, $iconPlaceable, $iconX, $iconY, 0.0f, 4, null);
        Placeable.PlacementScope.placeRelative$default($this$layout, $indicatorRipplePlaceable, $rippleX, $rippleY, 0.0f, 4, null);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:47:0x00be  */
    /* JADX INFO: renamed from: placeAnimatedLabelAndIcon-2QYhCQ8 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final androidx.compose.ui.layout.MeasureResult m2765placeAnimatedLabelAndIcon2QYhCQ8(androidx.compose.ui.layout.MeasureScope r37, int r38, kotlin.jvm.functions.Function0<java.lang.Float> r39, final androidx.compose.ui.layout.Placeable r40, final androidx.compose.ui.layout.Placeable r41, final androidx.compose.ui.layout.Placeable r42, final androidx.compose.ui.layout.Placeable r43, int r44, long r45, float r47, float r48, float r49, float r50, float r51, float r52, float r53) {
        /*
            Method dump skipped, instruction units count: 430
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.NavigationItemKt.m2765placeAnimatedLabelAndIcon2QYhCQ8(androidx.compose.ui.layout.MeasureScope, int, kotlin.jvm.functions.Function0, androidx.compose.ui.layout.Placeable, androidx.compose.ui.layout.Placeable, androidx.compose.ui.layout.Placeable, androidx.compose.ui.layout.Placeable, int, long, float, float, float, float, float, float, float):androidx.compose.ui.layout.MeasureResult");
    }

    static final Unit placeAnimatedLabelAndIcon_2QYhCQ8$lambda$40(Placeable $indicatorPlaceable, int $indicatorX, Placeable $iconPlaceable, int $iconX, int $iconY, Placeable $labelPlaceable, Object $labelX, int $labelY, Placeable $indicatorRipplePlaceable, int $rippleX, final float $iconPositionProgress, Placeable.PlacementScope $this$layout) {
        Placeable.PlacementScope.placeRelativeWithLayer$default($this$layout, $indicatorPlaceable, $indicatorX, 0, 0.0f, (Function1) null, 12, (Object) null);
        Placeable.PlacementScope.placeRelativeWithLayer$default($this$layout, $iconPlaceable, $iconX, $iconY, 0.0f, (Function1) null, 12, (Object) null);
        Placeable.PlacementScope.placeRelativeWithLayer$default($this$layout, $labelPlaceable, ((Number) $labelX).intValue(), $labelY, 0.0f, new Function1() { // from class: androidx.compose.material3.NavigationItemKt$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return NavigationItemKt.placeAnimatedLabelAndIcon_2QYhCQ8$lambda$40$lambda$39($iconPositionProgress, (GraphicsLayerScope) obj);
            }
        }, 4, (Object) null);
        Placeable.PlacementScope.placeRelativeWithLayer$default($this$layout, $indicatorRipplePlaceable, $rippleX, 0, 0.0f, (Function1) null, 12, (Object) null);
        return Unit.INSTANCE;
    }

    static final Unit placeAnimatedLabelAndIcon_2QYhCQ8$lambda$40$lambda$39(float $iconPositionProgress, GraphicsLayerScope $this$placeRelativeWithLayer) {
        $this$placeRelativeWithLayer.setAlpha(4.0f * ($iconPositionProgress - 0.5f) * ($iconPositionProgress - 0.5f));
        return Unit.INSTANCE;
    }

    public static final void StyledLabel(final boolean selected, final TextStyle labelTextStyle, final NavigationItemColors colors, final boolean enabled, final Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer, final int $changed) {
        Function2<? super Composer, ? super Integer, Unit> function22;
        Composer $composer2 = $composer.startRestartGroup(-2136267443);
        ComposerKt.sourceInformation($composer2, "C(StyledLabel)N(selected,labelTextStyle,colors,enabled,content)1085@45893L132:NavigationItem.kt#uh7d8r");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(selected) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(labelTextStyle) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer2.changed(colors) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            $dirty |= $composer2.changed(enabled) ? 2048 : 1024;
        }
        if (($changed & 24576) == 0) {
            function22 = function2;
            $dirty |= $composer2.changedInstance(function22) ? 16384 : 8192;
        } else {
            function22 = function2;
        }
        int $dirty2 = $dirty;
        if (!$composer2.shouldExecute(($dirty2 & 9363) != 9362, $dirty2 & 1)) {
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2136267443, $dirty2, -1, "androidx.compose.material3.StyledLabel (NavigationItem.kt:1083)");
            }
            long textColor = colors.m2746textColorWaAFU9c(selected, enabled);
            ProvideContentColorTextStyleKt.m3452ProvideContentColorTextStyle3JVO9M(textColor, labelTextStyle, function22, $composer2, ($dirty2 & 112) | (($dirty2 >> 6) & 896));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.NavigationItemKt$$ExternalSyntheticLambda18
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return NavigationItemKt.StyledLabel$lambda$41(selected, labelTextStyle, colors, enabled, function2, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final State<Float> animateIndicatorProgressAsState(boolean selected, Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, -1105658511, "C(animateIndicatorProgressAsState)N(selected)1097@46316L7,1094@46110L220:NavigationItem.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1105658511, $changed, -1, "androidx.compose.material3.animateIndicatorProgressAsState (NavigationItem.kt:1094)");
        }
        State<Float> stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(selected ? 1.0f : 0.0f, MotionSchemeKt.value(MotionSchemeKeyTokens.DefaultSpatial, $composer, 6), 0.0f, null, null, $composer, 0, 28);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return stateAnimateFloatAsState;
    }

    private static final void IndicatorRipple(final InteractionSource interactionSource, final Shape indicatorShape, Composer $composer, final int $changed) {
        Composer $composer2 = $composer.startRestartGroup(-629069867);
        ComposerKt.sourceInformation($composer2, "C(IndicatorRipple)N(interactionSource,indicatorShape)1102@46439L151:NavigationItem.kt#uh7d8r");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(interactionSource) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(indicatorShape) ? 32 : 16;
        }
        if (!$composer2.shouldExecute(($dirty & 19) != 18, $dirty & 1)) {
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-629069867, $dirty, -1, "androidx.compose.material3.IndicatorRipple (NavigationItem.kt:1101)");
            }
            BoxKt.Box(IndicationKt.indication(ClipKt.clip(LayoutIdKt.layoutId(Modifier.INSTANCE, IndicatorRippleLayoutIdTag), indicatorShape), interactionSource, RippleKt.m2847rippleH2RKhps$default(false, 0.0f, 0L, 7, null)), $composer2, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.NavigationItemKt$$ExternalSyntheticLambda7
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return NavigationItemKt.IndicatorRipple$lambda$42(interactionSource, indicatorShape, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: Indicator-3J-VO9M */
    private static final void m2758Indicator3JVO9M(final long indicatorColor, final Shape indicatorShape, final Function0<Float> function0, Composer $composer, final int $changed) {
        Composer $composer2 = $composer.startRestartGroup(-273382589);
        ComposerKt.sourceInformation($composer2, "C(Indicator)N(indicatorColor:c#ui.graphics.Color,indicatorShape,indicatorAnimationProgress)1117@46816L40,1115@46736L198:NavigationItem.kt#uh7d8r");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(indicatorColor) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(indicatorShape) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer2.changedInstance(function0) ? 256 : 128;
        }
        if (!$composer2.shouldExecute(($dirty & 147) != 146, $dirty & 1)) {
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-273382589, $dirty, -1, "androidx.compose.material3.Indicator (NavigationItem.kt:1114)");
            }
            Modifier modifierLayoutId = LayoutIdKt.layoutId(Modifier.INSTANCE, IndicatorLayoutIdTag);
            ComposerKt.sourceInformationMarkerStart($composer2, -727655829, "CC(remember):NavigationItem.kt#9igjgp");
            boolean invalid$iv = ($dirty & 896) == 256;
            Object it$iv = $composer2.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = new Function1() { // from class: androidx.compose.material3.NavigationItemKt$$ExternalSyntheticLambda3
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return NavigationItemKt.Indicator_3J_VO9M$lambda$44$lambda$43(function0, (GraphicsLayerScope) obj);
                    }
                };
                $composer2.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            BoxKt.Box(BackgroundKt.m285backgroundbw27NRU(GraphicsLayerModifierKt.graphicsLayer(modifierLayoutId, (Function1) it$iv), indicatorColor, indicatorShape), $composer2, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.NavigationItemKt$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return NavigationItemKt.Indicator_3J_VO9M$lambda$45(indicatorColor, indicatorShape, function0, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    static final Unit Indicator_3J_VO9M$lambda$44$lambda$43(Function0 $indicatorAnimationProgress, GraphicsLayerScope $this$graphicsLayer) {
        $this$graphicsLayer.setAlpha(((Number) $indicatorAnimationProgress.invoke()).floatValue());
        return Unit.INSTANCE;
    }
}
