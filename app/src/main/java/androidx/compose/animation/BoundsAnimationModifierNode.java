package androidx.compose.animation;

import androidx.autofill.HintConstants;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.InlineClassHelperKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.layout.ApproachLayoutModifierNode;
import androidx.compose.ui.layout.ApproachMeasureScope;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LookaheadScope;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNodeKt;
import androidx.compose.ui.node.DrawModifierNode;
import androidx.compose.ui.text.TextMeasurer;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.IntSizeKt;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AnimateBoundsModifier.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0092\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0001\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004BW\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u00126\u0010\t\u001a2\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0013\u0012\u00110\u000f¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\u000f0\n\u0012\u0006\u0010\u0011\u001a\u00020\u0012¢\u0006\u0004\b\u0013\u0010\u0014J\u0017\u0010<\u001a\u00020\u00122\u0006\u0010=\u001a\u00020\u000bH\u0016¢\u0006\u0004\b>\u0010?J\b\u0010@\u001a\u00020AH\u0016J\u0014\u0010B\u001a\u00020\u0012*\u00020C2\u0006\u0010D\u001a\u00020EH\u0016J#\u0010F\u001a\u00020G*\u00020H2\u0006\u0010I\u001a\u00020J2\u0006\u0010\u0010\u001a\u00020\u000fH\u0016¢\u0006\u0004\bK\u0010LJ\f\u0010M\u001a\u00020A*\u00020NH\u0016J\u0010\u0010O\u001a\u00020A2\u0006\u0010P\u001a\u00020+H\u0002R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cRJ\u0010\t\u001a2\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0013\u0012\u00110\u000f¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\u000f0\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u001a\u0010\u0011\u001a\u00020\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u000e\u0010%\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020'X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010(\u001a\u0004\u0018\u00010)X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010*\u001a\u0004\u0018\u00010+X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/R\u001c\u00100\u001a\u0004\u0018\u000101X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u00103\"\u0004\b4\u00105R\u001c\u00106\u001a\u0004\u0018\u000107X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b8\u00109\"\u0004\b:\u0010;¨\u0006Q"}, d2 = {"Landroidx/compose/animation/BoundsAnimationModifierNode;", "Landroidx/compose/ui/layout/ApproachLayoutModifierNode;", "Landroidx/compose/ui/Modifier$Node;", "Landroidx/compose/ui/node/CompositionLocalConsumerModifierNode;", "Landroidx/compose/ui/node/DrawModifierNode;", "lookaheadScope", "Landroidx/compose/ui/layout/LookaheadScope;", "boundsTransform", "Landroidx/compose/animation/BoundsTransform;", "onChooseMeasureConstraints", "Lkotlin/Function2;", "Landroidx/compose/ui/unit/IntSize;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "animatedSize", "Landroidx/compose/ui/unit/Constraints;", "constraints", "animateMotionFrameOfReference", "", "<init>", "(Landroidx/compose/ui/layout/LookaheadScope;Landroidx/compose/animation/BoundsTransform;Lkotlin/jvm/functions/Function2;Z)V", "getLookaheadScope", "()Landroidx/compose/ui/layout/LookaheadScope;", "setLookaheadScope", "(Landroidx/compose/ui/layout/LookaheadScope;)V", "getBoundsTransform", "()Landroidx/compose/animation/BoundsTransform;", "setBoundsTransform", "(Landroidx/compose/animation/BoundsTransform;)V", "getOnChooseMeasureConstraints", "()Lkotlin/jvm/functions/Function2;", "setOnChooseMeasureConstraints", "(Lkotlin/jvm/functions/Function2;)V", "getAnimateMotionFrameOfReference", "()Z", "setAnimateMotionFrameOfReference", "(Z)V", "directManipulationParentsDirty", "boundsAnimation", "Landroidx/compose/animation/BoundsTransformDeferredAnimation;", "textMeasurer", "Landroidx/compose/ui/text/TextMeasurer;", "currentResolver", "Landroidx/compose/ui/text/font/FontFamily$Resolver;", "getCurrentResolver", "()Landroidx/compose/ui/text/font/FontFamily$Resolver;", "setCurrentResolver", "(Landroidx/compose/ui/text/font/FontFamily$Resolver;)V", "currentDensity", "Landroidx/compose/ui/unit/Density;", "getCurrentDensity", "()Landroidx/compose/ui/unit/Density;", "setCurrentDensity", "(Landroidx/compose/ui/unit/Density;)V", "currentLayoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "getCurrentLayoutDirection", "()Landroidx/compose/ui/unit/LayoutDirection;", "setCurrentLayoutDirection", "(Landroidx/compose/ui/unit/LayoutDirection;)V", "isMeasurementApproachInProgress", "lookaheadSize", "isMeasurementApproachInProgress-ozmzZPI", "(J)Z", "onAttach", "", "isPlacementApproachInProgress", "Landroidx/compose/ui/layout/Placeable$PlacementScope;", "lookaheadCoordinates", "Landroidx/compose/ui/layout/LayoutCoordinates;", "approachMeasure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/ApproachMeasureScope;", "measurable", "Landroidx/compose/ui/layout/Measurable;", "approachMeasure-3p2s80s", "(Landroidx/compose/ui/layout/ApproachMeasureScope;Landroidx/compose/ui/layout/Measurable;J)Landroidx/compose/ui/layout/MeasureResult;", "draw", "Landroidx/compose/ui/graphics/drawscope/ContentDrawScope;", "updateTextMeasurer", "fontFamilyResolver", "animation"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class BoundsAnimationModifierNode extends Modifier.Node implements ApproachLayoutModifierNode, CompositionLocalConsumerModifierNode, DrawModifierNode {
    public static final int $stable = 8;
    private boolean animateMotionFrameOfReference;
    private BoundsTransform boundsTransform;
    private Density currentDensity;
    private LayoutDirection currentLayoutDirection;
    private FontFamily.Resolver currentResolver;
    private LookaheadScope lookaheadScope;
    private Function2<? super IntSize, ? super Constraints, Constraints> onChooseMeasureConstraints;
    private TextMeasurer textMeasurer;
    private boolean directManipulationParentsDirty = true;
    private final BoundsTransformDeferredAnimation boundsAnimation = new BoundsTransformDeferredAnimation();

    public BoundsAnimationModifierNode(LookaheadScope lookaheadScope, BoundsTransform boundsTransform, Function2<? super IntSize, ? super Constraints, Constraints> function2, boolean animateMotionFrameOfReference) {
        this.lookaheadScope = lookaheadScope;
        this.boundsTransform = boundsTransform;
        this.onChooseMeasureConstraints = function2;
        this.animateMotionFrameOfReference = animateMotionFrameOfReference;
    }

    public final LookaheadScope getLookaheadScope() {
        return this.lookaheadScope;
    }

    public final void setLookaheadScope(LookaheadScope lookaheadScope) {
        this.lookaheadScope = lookaheadScope;
    }

    public final BoundsTransform getBoundsTransform() {
        return this.boundsTransform;
    }

    public final void setBoundsTransform(BoundsTransform boundsTransform) {
        this.boundsTransform = boundsTransform;
    }

    public final Function2<IntSize, Constraints, Constraints> getOnChooseMeasureConstraints() {
        return this.onChooseMeasureConstraints;
    }

    public final void setOnChooseMeasureConstraints(Function2<? super IntSize, ? super Constraints, Constraints> function2) {
        this.onChooseMeasureConstraints = function2;
    }

    public final boolean getAnimateMotionFrameOfReference() {
        return this.animateMotionFrameOfReference;
    }

    public final void setAnimateMotionFrameOfReference(boolean z) {
        this.animateMotionFrameOfReference = z;
    }

    public final FontFamily.Resolver getCurrentResolver() {
        return this.currentResolver;
    }

    public final void setCurrentResolver(FontFamily.Resolver resolver) {
        this.currentResolver = resolver;
    }

    public final Density getCurrentDensity() {
        return this.currentDensity;
    }

    public final void setCurrentDensity(Density density) {
        this.currentDensity = density;
    }

    public final LayoutDirection getCurrentLayoutDirection() {
        return this.currentLayoutDirection;
    }

    public final void setCurrentLayoutDirection(LayoutDirection layoutDirection) {
        this.currentLayoutDirection = layoutDirection;
    }

    @Override // androidx.compose.ui.layout.ApproachLayoutModifierNode
    /* JADX INFO: renamed from: isMeasurementApproachInProgress-ozmzZPI, reason: not valid java name */
    public boolean mo73isMeasurementApproachInProgressozmzZPI(long lookaheadSize) {
        this.boundsAnimation.m80updateTargetSizeuvyYCjk(IntSizeKt.m8333toSizeozmzZPI(lookaheadSize));
        return !this.boundsAnimation.isIdle();
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void onAttach() {
        this.directManipulationParentsDirty = true;
    }

    @Override // androidx.compose.ui.layout.ApproachLayoutModifierNode
    public boolean isPlacementApproachInProgress(Placeable.PlacementScope $this$isPlacementApproachInProgress, LayoutCoordinates lookaheadCoordinates) {
        if (IsLookaheadAnimationVisualDebuggingEnabledKt.isLookaheadAnimationVisualDebuggingEnabled() && this.boundsAnimation.getLookaheadAnimationVisualDebugHelper() == null) {
            this.boundsAnimation.setLookaheadAnimationVisualDebugHelper(new LookaheadAnimationVisualDebugHelper());
        }
        this.boundsAnimation.updateTargetOffsetAndAnimate(this.lookaheadScope, $this$isPlacementApproachInProgress, getCoroutineScope(), this.directManipulationParentsDirty, this.animateMotionFrameOfReference, this.boundsTransform);
        this.directManipulationParentsDirty = this.animateMotionFrameOfReference;
        return !this.boundsAnimation.isIdle();
    }

    @Override // androidx.compose.ui.layout.ApproachLayoutModifierNode
    /* JADX INFO: renamed from: approachMeasure-3p2s80s, reason: not valid java name */
    public MeasureResult mo72approachMeasure3p2s80s(ApproachMeasureScope $this$approachMeasure_u2d3p2s80s, Measurable measurable, long constraints) {
        long fallbackSize;
        if (this.boundsAnimation.getCurrentSize() == InlineClassHelperKt.UnspecifiedPackedFloats) {
            fallbackSize = IntSizeKt.m8333toSizeozmzZPI($this$approachMeasure_u2d3p2s80s.mo6761getLookaheadSizeYbymL2g());
        } else {
            fallbackSize = this.boundsAnimation.getCurrentSize();
        }
        Rect value = this.boundsAnimation.getValue();
        long animatedSize = IntSizeKt.m8329roundToIntSizeuvyYCjk(value != null ? value.m5101getSizeNHjbRc() : fallbackSize);
        long chosenConstraints = this.onChooseMeasureConstraints.invoke(IntSize.m8313boximpl(animatedSize), Constraints.m8090boximpl(constraints)).getValue();
        final Placeable placeable = measurable.mo6783measureBRTryo0(chosenConstraints);
        long jM8117constrain4WqzIAM = ConstraintsKt.m8117constrain4WqzIAM(chosenConstraints, animatedSize);
        long fallbackSize2 = jM8117constrain4WqzIAM >> 32;
        int w = (int) fallbackSize2;
        int h = (int) (jM8117constrain4WqzIAM & 4294967295L);
        return MeasureScope.layout$default($this$approachMeasure_u2d3p2s80s, w, h, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.animation.BoundsAnimationModifierNode$approachMeasure$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                invoke2(placementScope);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Placeable.PlacementScope $this$layout) {
                Placeable.PlacementScope placementScope;
                Offset positionInScope;
                long topLeft;
                long it;
                Rect animatedBounds = this.this$0.boundsAnimation.getValue();
                LookaheadScope $this$invoke_u24lambda_u240 = this.this$0.getLookaheadScope();
                BoundsAnimationModifierNode boundsAnimationModifierNode = this.this$0;
                LayoutCoordinates coordinates = $this$layout.getCoordinates();
                if (coordinates != null) {
                    placementScope = $this$layout;
                    positionInScope = Offset.m5057boximpl($this$invoke_u24lambda_u240.getLookaheadScopeCoordinates(placementScope).mo6793localPositionOfS_NoaFU(coordinates, Offset.INSTANCE.m5084getZeroF1C5BW0(), boundsAnimationModifierNode.getAnimateMotionFrameOfReference()));
                } else {
                    placementScope = $this$layout;
                    positionInScope = null;
                }
                BoundsAnimationModifierNode boundsAnimationModifierNode2 = this.this$0;
                if (animatedBounds != null) {
                    boundsAnimationModifierNode2.boundsAnimation.m79updateCurrentBoundstz77jQw(animatedBounds.m5103getTopLeftF1C5BW0(), animatedBounds.m5101getSizeNHjbRc());
                    topLeft = animatedBounds.m5103getTopLeftF1C5BW0();
                } else {
                    Rect currentBounds = boundsAnimationModifierNode2.boundsAnimation.getCurrentBounds();
                    topLeft = currentBounds != null ? currentBounds.m5103getTopLeftF1C5BW0() : Offset.INSTANCE.m5084getZeroF1C5BW0();
                }
                if (positionInScope != null) {
                    long it2 = positionInScope.m5078unboximpl();
                    it = Offset.m5072minusMKHz9U(topLeft, it2);
                } else {
                    it = Offset.INSTANCE.m5084getZeroF1C5BW0();
                }
                long j = it;
                int bits$iv$iv$iv$iv = (int) (j >> 32);
                float x = Float.intBitsToFloat(bits$iv$iv$iv$iv);
                int bits$iv$iv$iv$iv2 = (int) (j & 4294967295L);
                float y = Float.intBitsToFloat(bits$iv$iv$iv$iv2);
                Placeable.PlacementScope.place$default(placementScope, placeable, Math.round(x), Math.round(y), 0.0f, 4, null);
            }
        }, 4, null);
    }

    @Override // androidx.compose.ui.node.DrawModifierNode
    public void draw(ContentDrawScope $this$draw) throws Throwable {
        $this$draw.drawContent();
        if (IsLookaheadAnimationVisualDebuggingEnabledKt.isLookaheadAnimationVisualDebuggingEnabled()) {
            boolean isInnerNode = Constraints.m8101getHasFixedWidthimpl(this.onChooseMeasureConstraints.invoke(IntSize.m8313boximpl(IntSize.INSTANCE.m8326getZeroYbymL2g()), Constraints.m8090boximpl(ConstraintsKt.Constraints$default(0, 0, 0, 0, 15, null))).getValue());
            if (!isInnerNode) {
                return;
            }
            LookaheadAnimationVisualDebugConfig lookaheadAnimationVisualDebugConfig = (LookaheadAnimationVisualDebugConfig) CompositionLocalConsumerModifierNodeKt.currentValueOf(this, CompositionLocalsKt.getLocalLookaheadAnimationVisualDebugConfig());
            if (lookaheadAnimationVisualDebugConfig.getIsEnabled()) {
                if (this.currentDensity == null) {
                    this.currentDensity = (Density) CompositionLocalConsumerModifierNodeKt.currentValueOf(this, androidx.compose.ui.platform.CompositionLocalsKt.getLocalDensity());
                    this.currentLayoutDirection = (LayoutDirection) CompositionLocalConsumerModifierNodeKt.currentValueOf(this, androidx.compose.ui.platform.CompositionLocalsKt.getLocalLayoutDirection());
                }
                LookaheadAnimationVisualDebugHelper lookaheadAnimationVisualDebugHelper = this.boundsAnimation.getLookaheadAnimationVisualDebugHelper();
                Intrinsics.checkNotNull(lookaheadAnimationVisualDebugHelper);
                long lookaheadAnimationVisualDebugColor = ((Color) CompositionLocalConsumerModifierNodeKt.currentValueOf(this, CompositionLocalsKt.getLocalLookaheadAnimationVisualDebugColor())).m5323unboximpl();
                updateTextMeasurer((FontFamily.Resolver) CompositionLocalConsumerModifierNodeKt.currentValueOf(this, androidx.compose.ui.platform.CompositionLocalsKt.getLocalFontFamilyResolver()));
                if (this.boundsAnimation.isIdle()) {
                    boolean isShowKeyLabelEnabled = lookaheadAnimationVisualDebugConfig.getIsShowKeyLabelEnabled();
                    float f = $this$draw.mo432toPx0680j_4(Dp.m8150constructorimpl((float) 2.5d));
                    String strSubstring = this.boundsAnimation.toString().substring(60);
                    Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
                    lookaheadAnimationVisualDebugHelper.m121drawInactiveVisualizations3IgeMak$animation($this$draw, lookaheadAnimationVisualDebugColor, isShowKeyLabelEnabled, f, strSubstring, this.textMeasurer);
                    return;
                }
                long targetOffset = this.boundsAnimation.getTargetOffset();
                long targetSize = this.boundsAnimation.getTargetSize();
                Rect value = this.boundsAnimation.getValue();
                Intrinsics.checkNotNull(value);
                long j = $this$draw.mo5886getCenterF1C5BW0();
                boolean isShowKeyLabelEnabled2 = lookaheadAnimationVisualDebugConfig.getIsShowKeyLabelEnabled();
                float f2 = $this$draw.mo432toPx0680j_4(Dp.m8150constructorimpl((float) 2.5d));
                String strSubstring2 = this.boundsAnimation.toString().substring(60);
                Intrinsics.checkNotNullExpressionValue(strSubstring2, "substring(...)");
                lookaheadAnimationVisualDebugHelper.m122drawLocalVisualizations0XenJco$animation($this$draw, lookaheadAnimationVisualDebugColor, targetOffset, targetSize, value, j, isShowKeyLabelEnabled2, f2, strSubstring2, this.textMeasurer);
            }
        }
    }

    private final void updateTextMeasurer(FontFamily.Resolver fontFamilyResolver) {
        if (this.textMeasurer == null || !Intrinsics.areEqual(this.currentResolver, fontFamilyResolver)) {
            Density density = this.currentDensity;
            Intrinsics.checkNotNull(density);
            LayoutDirection layoutDirection = this.currentLayoutDirection;
            Intrinsics.checkNotNull(layoutDirection);
            this.textMeasurer = new TextMeasurer(fontFamilyResolver, density, layoutDirection, 0, 8, null);
            this.currentResolver = fontFamilyResolver;
        }
    }
}
