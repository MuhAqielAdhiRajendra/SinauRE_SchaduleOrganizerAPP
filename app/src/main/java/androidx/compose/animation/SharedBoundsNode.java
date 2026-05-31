package androidx.compose.animation;

import androidx.compose.animation.SharedTransitionScope;
import androidx.compose.animation.SharedTransitionStateMachine;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.animation.core.SpringSpec;
import androidx.compose.animation.core.VisibilityThresholdsKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.RectKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.layer.GraphicsLayer;
import androidx.compose.ui.graphics.layer.GraphicsLayerKt;
import androidx.compose.ui.layout.ApproachLayoutModifierNode;
import androidx.compose.ui.layout.ApproachMeasureScope;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LayoutCoordinatesKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.modifier.ModifierLocalMap;
import androidx.compose.ui.modifier.ModifierLocalModifierNode;
import androidx.compose.ui.modifier.ModifierLocalModifierNodeKt;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNodeKt;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DrawModifierNode;
import androidx.compose.ui.node.ObserverModifierNode;
import androidx.compose.ui.node.ObserverModifierNodeKt;
import androidx.compose.ui.text.TextMeasurer;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntOffsetKt;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.IntSizeKt;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: SharedContentNode.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000Ö\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0001\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u00052\u00020\u00062\u00020\u0007B\u000f\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\u0012\u0010\u0011\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0012\u001a\u00020\rH\u0016J\b\u0010 \u001a\u00020\u0014H\u0002J\b\u00101\u001a\u000202H\u0002J\b\u00103\u001a\u000202H\u0016J\b\u00104\u001a\u000202H\u0016J\b\u00105\u001a\u000202H\u0016J#\u00106\u001a\u000207*\u0002082\u0006\u00109\u001a\u00020:2\u0006\u0010;\u001a\u00020<H\u0016¢\u0006\u0004\b=\u0010>J\u001c\u0010I\u001a\u000202*\u00020J2\u0006\u0010K\u001a\u00020L2\u0006\u0010M\u001a\u00020\rH\u0002J)\u0010N\u001a\u000202*\u00020J2\u0006\u0010K\u001a\u00020L2\u0006\u0010O\u001a\u00020P2\u0006\u0010M\u001a\u00020\rH\u0000¢\u0006\u0002\bQJ\u0014\u0010R\u001a\u000207*\u0002082\u0006\u0010K\u001a\u00020LH\u0002J\u0017\u0010S\u001a\u00020\u00182\u0006\u0010T\u001a\u00020UH\u0016¢\u0006\u0004\bV\u0010WJ#\u0010X\u001a\u000207*\u00020Y2\u0006\u00109\u001a\u00020:2\u0006\u0010;\u001a\u00020<H\u0016¢\u0006\u0004\bZ\u0010[J\f\u0010\\\u001a\u000202*\u00020]H\u0016J&\u0010^\u001a\u000202*\u00020]2\u0006\u0010&\u001a\u00020%2\b\u0010_\u001a\u0004\u0018\u00010\r2\u0006\u0010`\u001a\u00020aH\u0002J\b\u0010b\u001a\u000202H\u0016J\u0010\u0010c\u001a\u0002022\u0006\u0010d\u001a\u00020DH\u0002R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u000e\u001a\u0004\u0018\u00010\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0013\u001a\u00020\u00148BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0019\u001a\u00020\u00148BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u0016R$\u0010\u001c\u001a\u00020\t2\u0006\u0010\u001b\u001a\u00020\t@@X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010\u000bR\u0014\u0010!\u001a\u00020\"8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b#\u0010$R\"\u0010&\u001a\u0004\u0018\u00010%2\b\u0010\u001b\u001a\u0004\u0018\u00010%@BX\u0082\u000e¢\u0006\b\n\u0000\"\u0004\b'\u0010(R\u0014\u0010)\u001a\u00020*8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b+\u0010,R\u0014\u0010-\u001a\u00020.X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b/\u00100R\u0010\u0010?\u001a\u0004\u0018\u00010@X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010A\u001a\u0004\u0018\u00010BX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010C\u001a\u0004\u0018\u00010DX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010E\u001a\u0004\u0018\u00010FX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010G\u001a\u0004\u0018\u00010HX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006e"}, d2 = {"Landroidx/compose/animation/SharedBoundsNode;", "Landroidx/compose/ui/layout/ApproachLayoutModifierNode;", "Landroidx/compose/ui/Modifier$Node;", "Landroidx/compose/ui/node/DrawModifierNode;", "Landroidx/compose/ui/modifier/ModifierLocalModifierNode;", "Landroidx/compose/ui/node/ObserverModifierNode;", "Landroidx/compose/animation/BoundsProvider;", "Landroidx/compose/ui/node/CompositionLocalConsumerModifierNode;", "state", "Landroidx/compose/animation/SharedElementEntry;", "<init>", "(Landroidx/compose/animation/SharedElementEntry;)V", "boundsBeforeDetached", "Landroidx/compose/ui/geometry/Rect;", "lastBoundsInSharedTransitionScope", "getLastBoundsInSharedTransitionScope", "()Landroidx/compose/ui/geometry/Rect;", "calculateAlternativeTargetBounds", "targetBoundsBeforeDisposed", "approachCoordinates", "Landroidx/compose/ui/layout/LayoutCoordinates;", "getApproachCoordinates", "()Landroidx/compose/ui/layout/LayoutCoordinates;", "isPlaced", "", "rootCoords", "getRootCoords", "value", "sharedElementEntry", "getSharedElementEntry", "()Landroidx/compose/animation/SharedElementEntry;", "setSharedElementEntry$animation", "requireLookaheadLayoutCoordinates", "boundsAnimation", "Landroidx/compose/animation/BoundsAnimation;", "getBoundsAnimation", "()Landroidx/compose/animation/BoundsAnimation;", "Landroidx/compose/ui/graphics/layer/GraphicsLayer;", "layer", "setLayer", "(Landroidx/compose/ui/graphics/layer/GraphicsLayer;)V", "sharedElement", "Landroidx/compose/animation/SharedElement;", "getSharedElement", "()Landroidx/compose/animation/SharedElement;", "providedValues", "Landroidx/compose/ui/modifier/ModifierLocalMap;", "getProvidedValues", "()Landroidx/compose/ui/modifier/ModifierLocalMap;", "setup", "", "onAttach", "onDetach", "onReset", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "measurable", "Landroidx/compose/ui/layout/Measurable;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Measurable;J)Landroidx/compose/ui/layout/MeasureResult;", "textMeasurer", "Landroidx/compose/ui/text/TextMeasurer;", "lookaheadAnimationVisualDebugHelper", "Landroidx/compose/animation/LookaheadAnimationVisualDebugHelper;", "currentResolver", "Landroidx/compose/ui/text/font/FontFamily$Resolver;", "currentDensity", "Landroidx/compose/ui/unit/Density;", "currentLayoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "approachPlaceMatchBeyondTransition", "Landroidx/compose/ui/layout/Placeable$PlacementScope;", "placeable", "Landroidx/compose/ui/layout/Placeable;", "currentBounds", "approachPlaceMatchInTransition", "targetData", "Landroidx/compose/animation/TargetData;", "approachPlaceMatchInTransition$animation", "approachPlace", "isMeasurementApproachInProgress", "lookaheadSize", "Landroidx/compose/ui/unit/IntSize;", "isMeasurementApproachInProgress-ozmzZPI", "(J)Z", "approachMeasure", "Landroidx/compose/ui/layout/ApproachMeasureScope;", "approachMeasure-3p2s80s", "(Landroidx/compose/ui/layout/ApproachMeasureScope;Landroidx/compose/ui/layout/Measurable;J)Landroidx/compose/ui/layout/MeasureResult;", "draw", "Landroidx/compose/ui/graphics/drawscope/ContentDrawScope;", "drawContentWithLookaheadAnimationDebug", "bounds", "visualDebugConfig", "Landroidx/compose/animation/LookaheadAnimationVisualDebugConfig;", "onObservedReadsChanged", "updateTextMeasurer", "fontFamilyResolver", "animation"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class SharedBoundsNode extends Modifier.Node implements ApproachLayoutModifierNode, DrawModifierNode, ModifierLocalModifierNode, ObserverModifierNode, BoundsProvider, CompositionLocalConsumerModifierNode {
    public static final int $stable = 8;
    private Rect boundsBeforeDetached;
    private Density currentDensity;
    private LayoutDirection currentLayoutDirection;
    private FontFamily.Resolver currentResolver;
    private boolean isPlaced;
    private GraphicsLayer layer;
    private LookaheadAnimationVisualDebugHelper lookaheadAnimationVisualDebugHelper;
    private final ModifierLocalMap providedValues;
    private SharedElementEntry sharedElementEntry;
    private TextMeasurer textMeasurer;

    public SharedBoundsNode(SharedElementEntry state) {
        this.sharedElementEntry = state;
        this.layer = state.getLayer();
        this.providedValues = ModifierLocalModifierNodeKt.modifierLocalMapOf(TuplesKt.to(SharedContentNodeKt.getModifierLocalSharedElementInternalState(), state));
    }

    @Override // androidx.compose.animation.BoundsProvider
    public Rect getLastBoundsInSharedTransitionScope() {
        if (getIsAttached()) {
            return !this.isPlaced ? this.boundsBeforeDetached : RectKt.m5108Recttz77jQw(LayoutCoordinates.m6790localPositionOfS_NoaFU$default(getRootCoords(), getApproachCoordinates(), 0L, false, 6, null), IntSizeKt.m8333toSizeozmzZPI(getApproachCoordinates().mo6791getSizeYbymL2g()));
        }
        return null;
    }

    @Override // androidx.compose.animation.BoundsProvider
    public Rect calculateAlternativeTargetBounds(Rect targetBoundsBeforeDisposed) {
        return this.sharedElementEntry.calculateTargetBounds(targetBoundsBeforeDisposed);
    }

    private final LayoutCoordinates getApproachCoordinates() {
        return DelegatableNodeKt.requireLayoutCoordinates(this);
    }

    private final LayoutCoordinates getRootCoords() {
        return getSharedElement().getScope().getRoot$animation();
    }

    public final SharedElementEntry getSharedElementEntry() {
        return this.sharedElementEntry;
    }

    public final void setSharedElementEntry$animation(SharedElementEntry value) {
        if (!Intrinsics.areEqual(value, this.sharedElementEntry)) {
            this.sharedElementEntry.setAttached(false);
            this.sharedElementEntry = value;
            value.setAttached(getIsAttached());
            if (getIsAttached()) {
                setup();
            }
        }
    }

    private final LayoutCoordinates requireLookaheadLayoutCoordinates() {
        SharedTransitionScopeImpl $this$requireLookaheadLayoutCoordinates_u24lambda_u240 = this.sharedElementEntry.getSharedElement().getScope();
        return $this$requireLookaheadLayoutCoordinates_u24lambda_u240.toLookaheadCoordinates(DelegatableNodeKt.requireLayoutCoordinates(this));
    }

    private final BoundsAnimation getBoundsAnimation() {
        return this.sharedElementEntry.getBoundsAnimation();
    }

    private final void setLayer(GraphicsLayer value) {
        if (value == null) {
            GraphicsLayer it = this.layer;
            if (it != null) {
                DelegatableNodeKt.requireGraphicsContext(this).releaseGraphicsLayer(it);
            }
        } else {
            this.sharedElementEntry.setLayer(value);
        }
        this.layer = value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final SharedElement getSharedElement() {
        return this.sharedElementEntry.getSharedElement();
    }

    @Override // androidx.compose.ui.modifier.ModifierLocalModifierNode
    public ModifierLocalMap getProvidedValues() {
        return this.providedValues;
    }

    private final void setup() {
        provide(SharedContentNodeKt.getModifierLocalSharedElementInternalState(), this.sharedElementEntry);
        this.sharedElementEntry.setParentState((SharedElementEntry) getCurrent(SharedContentNodeKt.getModifierLocalSharedElementInternalState()));
        setLayer(DelegatableNodeKt.requireGraphicsContext(this).createGraphicsLayer());
        this.isPlaced = false;
        this.sharedElementEntry.setBoundsProvider(this);
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void onAttach() {
        super.onAttach();
        ObserverModifierNodeKt.observeReads(this, getSharedElement().getObservingVisibilityChange$animation());
        setup();
        this.sharedElementEntry.setAttached(true);
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void onDetach() {
        Rect rectM5108Recttz77jQw;
        super.onDetach();
        LayoutCoordinates rootCoords = getSharedElement().getScope().getNullableRoot();
        if (rootCoords != null) {
            if (rootCoords.isAttached() && this.isPlaced) {
                rectM5108Recttz77jQw = RectKt.m5108Recttz77jQw(Offset.m5072minusMKHz9U(LayoutCoordinatesKt.positionInRoot(getApproachCoordinates()), LayoutCoordinatesKt.positionInRoot(rootCoords)), IntSizeKt.m8333toSizeozmzZPI(getApproachCoordinates().mo6791getSizeYbymL2g()));
            } else {
                rectM5108Recttz77jQw = null;
            }
            this.boundsBeforeDetached = rectM5108Recttz77jQw;
        }
        setLayer(null);
        this.sharedElementEntry.setParentState(null);
        this.sharedElementEntry.setBoundsProvider(null);
        this.sharedElementEntry.setAttached(false);
        this.isPlaced = false;
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void onReset() {
        super.onReset();
        this.boundsBeforeDetached = null;
        GraphicsLayer it = this.layer;
        if (it != null) {
            DelegatableNodeKt.requireGraphicsContext(this).releaseGraphicsLayer(it);
        }
        setLayer(DelegatableNodeKt.requireGraphicsContext(this).createGraphicsLayer());
    }

    @Override // androidx.compose.ui.layout.ApproachLayoutModifierNode, androidx.compose.ui.node.LayoutModifierNode
    /* JADX INFO: renamed from: measure-3p2s80s */
    public MeasureResult mo67measure3p2s80s(MeasureScope $this$measure_u2d3p2s80s, Measurable measurable, long constraints) {
        final Placeable placeable = measurable.mo6783measureBRTryo0(constraints);
        return MeasureScope.layout$default($this$measure_u2d3p2s80s, placeable.getWidth(), placeable.getHeight(), null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.animation.SharedBoundsNode$measure$1
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
                Placeable.PlacementScope.place$default($this$layout, placeable, 0, 0, 0.0f, 4, null);
                this.getSharedElement().onLookaheadPlaced($this$layout, this.getSharedElementEntry());
            }
        }, 4, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void approachPlaceMatchBeyondTransition(Placeable.PlacementScope $this$approachPlaceMatchBeyondTransition, Placeable placeable, Rect currentBounds) {
        long jM8289getZeronOccac;
        if (!getBoundsAnimation().getTarget()) {
            LayoutCoordinates it = $this$approachPlaceMatchBeyondTransition.getCoordinates();
            if (it != null) {
                long positionInScope = getRootCoords().mo6792localPositionOfR5De75A(it, Offset.INSTANCE.m5084getZeroF1C5BW0());
                jM8289getZeronOccac = IntOffsetKt.m8295roundk4lQ0M(Offset.m5072minusMKHz9U(currentBounds.m5103getTopLeftF1C5BW0(), positionInScope));
            } else {
                jM8289getZeronOccac = IntOffset.INSTANCE.m8289getZeronOccac();
            }
            int iM8278getXimpl = IntOffset.m8278getXimpl(jM8289getZeronOccac);
            int y = IntOffset.m8279getYimpl(jM8289getZeronOccac);
            Placeable.PlacementScope.place$default($this$approachPlaceMatchBeyondTransition, placeable, iM8278getXimpl, y, 0.0f, 4, null);
            return;
        }
        Placeable.PlacementScope.place$default($this$approachPlaceMatchBeyondTransition, placeable, 0, 0, 0.0f, 4, null);
    }

    public final void approachPlaceMatchInTransition$animation(Placeable.PlacementScope $this$approachPlaceMatchInTransition, Placeable placeable, TargetData targetData, Rect currentBounds) {
        boolean actualIsLookaheadAnimationVisualDebuggingEnabled;
        SpringSpec springSpec;
        FiniteAnimationSpec<Rect> finiteAnimationSpec;
        long topLeft;
        LayoutCoordinates coordinates = $this$approachPlaceMatchInTransition.getCoordinates();
        if (coordinates == null) {
            Placeable.PlacementScope.place$default($this$approachPlaceMatchInTransition, placeable, 0, 0, 0.0f, 4, null);
            return;
        }
        boolean activeMatchRemoved = !getSharedElement().getState$animation().getActiveMatchFound();
        long positionInScope = getRootCoords().mo6792localPositionOfR5De75A(coordinates, Offset.INSTANCE.m5084getZeroF1C5BW0());
        Rect targetBounds = SharedTransitionStateMachineKt.getTargetBounds(targetData);
        FiniteAnimationSpec<Rect> finiteAnimationSpecCreateAnimationSpec = null;
        if (IsLookaheadAnimationVisualDebuggingEnabledKt.isLookaheadAnimationVisualDebuggingEnabled()) {
            boolean actualIsLookaheadAnimationVisualDebuggingEnabled2 = ((LookaheadAnimationVisualDebugConfig) CompositionLocalConsumerModifierNodeKt.currentValueOf(this, CompositionLocalsKt.getLocalLookaheadAnimationVisualDebugConfig())).getIsEnabled();
            actualIsLookaheadAnimationVisualDebuggingEnabled = actualIsLookaheadAnimationVisualDebuggingEnabled2;
        } else {
            actualIsLookaheadAnimationVisualDebuggingEnabled = false;
        }
        Offset animatedTopLeft = null;
        if (activeMatchRemoved) {
            if (actualIsLookaheadAnimationVisualDebuggingEnabled) {
                BoundsTransform boundsTransform = new BoundsTransform() { // from class: androidx.compose.animation.SharedBoundsNode$$ExternalSyntheticLambda0
                    @Override // androidx.compose.animation.BoundsTransform
                    public final FiniteAnimationSpec createAnimationSpec(Rect rect, Rect rect2) {
                        return AnimationSpecKt.spring$default(0.0f, 0.0f, VisibilityThresholdsKt.getVisibilityThreshold(Rect.INSTANCE), 3, null);
                    }
                };
                finiteAnimationSpecCreateAnimationSpec = boundsTransform.createAnimationSpec(currentBounds, targetBounds);
            }
            getBoundsAnimation().animate(currentBounds, SharedTransitionStateMachineKt.getTargetBounds(targetData), new BoundsTransform() { // from class: androidx.compose.animation.SharedBoundsNode$$ExternalSyntheticLambda1
                @Override // androidx.compose.animation.BoundsTransform
                public final FiniteAnimationSpec createAnimationSpec(Rect rect, Rect rect2) {
                    return AnimationSpecKt.spring$default(0.0f, 0.0f, VisibilityThresholdsKt.getVisibilityThreshold(Rect.INSTANCE), 3, null);
                }
            });
            finiteAnimationSpec = finiteAnimationSpecCreateAnimationSpec;
        } else {
            if (actualIsLookaheadAnimationVisualDebuggingEnabled) {
                FiniteAnimationSpec spec = AnimationSpecKt.spring$default(0.0f, 0.0f, null, 7, null);
                springSpec = spec;
            } else {
                springSpec = null;
            }
            BoundsAnimation.animate$default(getBoundsAnimation(), currentBounds, SharedTransitionStateMachineKt.getTargetBounds(targetData), null, 4, null);
            finiteAnimationSpec = springSpec;
        }
        if (actualIsLookaheadAnimationVisualDebuggingEnabled && this.lookaheadAnimationVisualDebugHelper != null) {
            LookaheadAnimationVisualDebugHelper lookaheadAnimationVisualDebugHelper = this.lookaheadAnimationVisualDebugHelper;
            Intrinsics.checkNotNull(lookaheadAnimationVisualDebugHelper);
            Intrinsics.checkNotNull(finiteAnimationSpec);
            LookaheadAnimationVisualDebugHelper.calculatePath$animation$default(lookaheadAnimationVisualDebugHelper, finiteAnimationSpec, currentBounds, targetBounds, null, 8, null);
        }
        Rect animatedBounds = getBoundsAnimation().getValue();
        if (animatedBounds != null) {
            animatedTopLeft = Offset.m5057boximpl(SharedTransitionStateMachineKt.calculateOffsetFromDirectManipulation(targetData, animatedBounds));
        }
        if (getBoundsAnimation().getTarget() || activeMatchRemoved) {
            long topLeft2 = animatedTopLeft != null ? animatedTopLeft.m5078unboximpl() : positionInScope;
            Rect bounds = animatedTopLeft == null ? RectKt.m5108Recttz77jQw(positionInScope, IntSizeKt.m8333toSizeozmzZPI(coordinates.mo6791getSizeYbymL2g())) : RectKt.m5108Recttz77jQw(animatedTopLeft.m5078unboximpl(), animatedBounds.m5101getSizeNHjbRc());
            getSharedElement().getState$animation().updateBounds(bounds);
            topLeft = topLeft2;
        } else {
            topLeft = animatedTopLeft != null ? animatedTopLeft.m5078unboximpl() : currentBounds.m5103getTopLeftF1C5BW0();
        }
        long it = Offset.m5072minusMKHz9U(topLeft, positionInScope);
        int bits$iv$iv$iv$iv = (int) (it >> 32);
        float x = Float.intBitsToFloat(bits$iv$iv$iv$iv);
        int bits$iv$iv$iv$iv2 = (int) (it & 4294967295L);
        float y = Float.intBitsToFloat(bits$iv$iv$iv$iv2);
        Placeable.PlacementScope.place$default($this$approachPlaceMatchInTransition, placeable, Math.round(x), Math.round(y), 0.0f, 4, null);
    }

    private final MeasureResult approachPlace(MeasureScope $this$approachPlace, final Placeable placeable) {
        long j;
        long jM8316constructorimpl;
        if (getSharedElement().getState$animation().getMatchIsOrHasBeenConfigured()) {
            SharedTransitionScope.PlaceholderSize placeholderSize = this.sharedElementEntry.getPlaceholderSize();
            long jMo6791getSizeYbymL2g = requireLookaheadLayoutCoordinates().mo6791getSizeYbymL2g();
            int width$iv = placeable.getWidth();
            int height$iv = placeable.getHeight();
            j = 4294967295L;
            jM8316constructorimpl = placeholderSize.mo145calculateSizeJyjRU_E(jMo6791getSizeYbymL2g, IntSize.m8316constructorimpl((((long) height$iv) & 4294967295L) | (((long) width$iv) << 32)));
        } else {
            j = 4294967295L;
            int width$iv2 = placeable.getWidth();
            int height$iv2 = placeable.getHeight();
            jM8316constructorimpl = IntSize.m8316constructorimpl((((long) width$iv2) << 32) | (((long) height$iv2) & 4294967295L));
        }
        int w = (int) (jM8316constructorimpl >> 32);
        int h = (int) (jM8316constructorimpl & j);
        return MeasureScope.layout$default($this$approachPlace, w, h, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.animation.SharedBoundsNode.approachPlace.1
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
                SharedBoundsNode.this.isPlaced = true;
                SharedBoundsNode.this.boundsBeforeDetached = null;
                SharedTransitionStateMachine.State matchState = SharedBoundsNode.this.getSharedElement().getState$animation();
                if (!SharedBoundsNode.this.getSharedElementEntry().isEnabled()) {
                    Placeable.PlacementScope.place$default($this$layout, placeable, 0, 0, 0.0f, 4, null);
                    return;
                }
                if (matchState.getMatchIsOrHasBeenConfigured()) {
                    TargetData targetData = matchState.getTargetData();
                    if (targetData == null) {
                        throw new IllegalArgumentException(("Match State is configured, but target data is null. State = " + matchState).toString());
                    }
                    Rect currentBounds = matchState.getCurrentBounds();
                    if (currentBounds != null) {
                        boolean zIsTransitionActive = SharedBoundsNode.this.getSharedElement().getScope().isTransitionActive();
                        SharedBoundsNode sharedBoundsNode = SharedBoundsNode.this;
                        if (!zIsTransitionActive) {
                            sharedBoundsNode.approachPlaceMatchBeyondTransition($this$layout, placeable, currentBounds);
                            return;
                        } else {
                            sharedBoundsNode.approachPlaceMatchInTransition$animation($this$layout, placeable, targetData, currentBounds);
                            return;
                        }
                    }
                    throw new IllegalArgumentException(("Match State is configured, but current bounds is null. State = " + matchState).toString());
                }
                Placeable.PlacementScope.place$default($this$layout, placeable, 0, 0, 0.0f, 4, null);
            }
        }, 4, null);
    }

    @Override // androidx.compose.ui.layout.ApproachLayoutModifierNode
    /* JADX INFO: renamed from: isMeasurementApproachInProgress-ozmzZPI */
    public boolean mo73isMeasurementApproachInProgressozmzZPI(long lookaheadSize) {
        return this.sharedElementEntry.isEnabled() && getSharedElement().getFoundMatch() && getSharedElement().getScope().isTransitionActive();
    }

    @Override // androidx.compose.ui.layout.ApproachLayoutModifierNode
    /* JADX INFO: renamed from: approachMeasure-3p2s80s */
    public MeasureResult mo72approachMeasure3p2s80s(ApproachMeasureScope $this$approachMeasure_u2d3p2s80s, Measurable measurable, long constraints) {
        long resolvedConstraints;
        Rect it = getBoundsAnimation().getValue();
        if (it == null) {
            it = getSharedElement().tryInitializingCurrentBounds();
        }
        if (it != null) {
            long jM8329roundToIntSizeuvyYCjk = IntSizeKt.m8329roundToIntSizeuvyYCjk(it.m5101getSizeNHjbRc());
            int width = (int) (jM8329roundToIntSizeuvyYCjk >> 32);
            int height = (int) (4294967295L & jM8329roundToIntSizeuvyYCjk);
            if (!((width == Integer.MAX_VALUE || height == Integer.MAX_VALUE) ? false : true)) {
                throw new IllegalArgumentException(("Error: Infinite width/height is invalid. animated bounds: " + getBoundsAnimation().getValue() + ", current bounds: " + getSharedElement().getState$animation().getCurrentBounds()).toString());
            }
            resolvedConstraints = Constraints.INSTANCE.m8113fixedJhjzzOo(RangesKt.coerceAtLeast(width, 0), RangesKt.coerceAtLeast(height, 0));
        } else {
            resolvedConstraints = constraints;
        }
        Placeable placeable = measurable.mo6783measureBRTryo0(resolvedConstraints);
        return approachPlace($this$approachMeasure_u2d3p2s80s, placeable);
    }

    @Override // androidx.compose.ui.node.DrawModifierNode
    public void draw(final ContentDrawScope $this$draw) {
        final SharedElement sharedElement = getSharedElement();
        SharedTransitionStateMachine.State matchState = sharedElement.getState$animation();
        final Rect bounds = matchState.getCurrentBounds();
        this.sharedElementEntry.setClipPathInOverlay$animation((!this.sharedElementEntry.getShouldRenderInOverlay$animation() || bounds == null) ? null : this.sharedElementEntry.getOverlayClip().getClipPath(this.sharedElementEntry.getUserState(), bounds, $this$draw.getLayoutDirection(), DelegatableNodeKt.requireDensity(this)));
        GraphicsLayer layer = this.sharedElementEntry.getLayer();
        if (layer == null) {
            throw new IllegalArgumentException(("Error: Layer is null when accessed for shared bounds/element : " + sharedElement.getKey() + ",target: " + this.sharedElementEntry.getBoundsAnimation().getTarget() + ", is attached: " + getIsAttached()).toString());
        }
        LookaheadAnimationVisualDebugConfig visualDebugConfig = IsLookaheadAnimationVisualDebuggingEnabledKt.isLookaheadAnimationVisualDebuggingEnabled() ? (LookaheadAnimationVisualDebugConfig) CompositionLocalConsumerModifierNodeKt.currentValueOf(this, CompositionLocalsKt.getLocalLookaheadAnimationVisualDebugConfig()) : null;
        if (visualDebugConfig == null || !visualDebugConfig.getIsEnabled()) {
            DrawScope.m5885recordJVtK1S4$default($this$draw, layer, 0L, new Function1<DrawScope, Unit>() { // from class: androidx.compose.animation.SharedBoundsNode.draw.2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope) {
                    invoke2(drawScope);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(DrawScope $this$record) {
                    $this$draw.drawContent();
                }
            }, 1, null);
        } else {
            drawContentWithLookaheadAnimationDebug($this$draw, layer, bounds, visualDebugConfig);
        }
        if (this.sharedElementEntry.getShouldRenderInPlace()) {
            GraphicsLayerKt.drawLayer($this$draw, layer);
        }
    }

    private final void drawContentWithLookaheadAnimationDebug(final ContentDrawScope $this$drawContentWithLookaheadAnimationDebug, GraphicsLayer layer, final Rect bounds, final LookaheadAnimationVisualDebugConfig visualDebugConfig) {
        if (this.lookaheadAnimationVisualDebugHelper == null) {
            this.lookaheadAnimationVisualDebugHelper = new LookaheadAnimationVisualDebugHelper();
        }
        if (this.currentDensity == null) {
            this.currentDensity = (Density) CompositionLocalConsumerModifierNodeKt.currentValueOf(this, androidx.compose.ui.platform.CompositionLocalsKt.getLocalDensity());
            this.currentLayoutDirection = (LayoutDirection) CompositionLocalConsumerModifierNodeKt.currentValueOf(this, androidx.compose.ui.platform.CompositionLocalsKt.getLocalLayoutDirection());
        }
        final long lookaheadAnimationVisualDebugColor = ((Color) CompositionLocalConsumerModifierNodeKt.currentValueOf(this, CompositionLocalsKt.getLocalLookaheadAnimationVisualDebugColor())).m5323unboximpl();
        final float strokeWeight = $this$drawContentWithLookaheadAnimationDebug.mo432toPx0680j_4(Dp.m8150constructorimpl((float) 2.5d));
        final TargetData targetData = getSharedElement().getState$animation().getTargetData();
        updateTextMeasurer((FontFamily.Resolver) CompositionLocalConsumerModifierNodeKt.currentValueOf(this, androidx.compose.ui.platform.CompositionLocalsKt.getLocalFontFamilyResolver()));
        DrawScope.m5885recordJVtK1S4$default($this$drawContentWithLookaheadAnimationDebug, layer, 0L, new Function1<DrawScope, Unit>() { // from class: androidx.compose.animation.SharedBoundsNode.drawContentWithLookaheadAnimationDebug.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope) throws Throwable {
                invoke2(drawScope);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(DrawScope $this$record) throws Throwable {
                ContentDrawScope drawScope = $this$drawContentWithLookaheadAnimationDebug;
                drawScope.drawContent();
                if (this.getSharedElementEntry().isEnabled()) {
                    LookaheadAnimationVisualDebugHelper $this$invoke_u24lambda_u240 = this.lookaheadAnimationVisualDebugHelper;
                    Intrinsics.checkNotNull($this$invoke_u24lambda_u240);
                    SharedBoundsNode sharedBoundsNode = this;
                    LookaheadAnimationVisualDebugConfig lookaheadAnimationVisualDebugConfig = visualDebugConfig;
                    float f = strokeWeight;
                    TargetData targetData2 = targetData;
                    Rect rect = bounds;
                    long j = lookaheadAnimationVisualDebugColor;
                    if (sharedBoundsNode.getSharedElement().getScope().isTransitionActive()) {
                        if (sharedBoundsNode.getSharedElement().getBoundsTransformIsActive()) {
                            if (sharedBoundsNode.getSharedElement().getEnabledEntries().size() <= 2) {
                                if (targetData2 != null && rect != null) {
                                    $this$invoke_u24lambda_u240.m122drawLocalVisualizations0XenJco$animation(drawScope, j, SharedTransitionStateMachineKt.getTargetBounds(targetData2).m5103getTopLeftF1C5BW0(), targetData2.m168getSizeNHjbRc(), rect, $this$record.mo5886getCenterF1C5BW0(), lookaheadAnimationVisualDebugConfig.getIsShowKeyLabelEnabled(), f, sharedBoundsNode.getSharedElement().getKey(), sharedBoundsNode.textMeasurer);
                                    return;
                                }
                                return;
                            }
                            long multipleMatchesColor = lookaheadAnimationVisualDebugConfig.getMultipleMatchesColor();
                            boolean isShowKeyLabelEnabled = lookaheadAnimationVisualDebugConfig.getIsShowKeyLabelEnabled();
                            Object key = sharedBoundsNode.getSharedElement().getKey();
                            int size = sharedBoundsNode.getSharedElement().getEnabledEntries().size() - 1;
                            TextMeasurer textMeasurer = sharedBoundsNode.textMeasurer;
                            Intrinsics.checkNotNull(textMeasurer);
                            $this$invoke_u24lambda_u240.m123drawMultipleMatchesElementsW7UJKQ$animation(drawScope, multipleMatchesColor, isShowKeyLabelEnabled, key, size, textMeasurer, 3.0f * f);
                            return;
                        }
                        long unmatchedElementColor = lookaheadAnimationVisualDebugConfig.getUnmatchedElementColor();
                        boolean isShowKeyLabelEnabled2 = lookaheadAnimationVisualDebugConfig.getIsShowKeyLabelEnabled();
                        Object key2 = sharedBoundsNode.getSharedElement().getKey();
                        TextMeasurer textMeasurer2 = sharedBoundsNode.textMeasurer;
                        Intrinsics.checkNotNull(textMeasurer2);
                        $this$invoke_u24lambda_u240.m125drawUnmatchedElement3IgeMak$animation(drawScope, unmatchedElementColor, isShowKeyLabelEnabled2, key2, textMeasurer2, f);
                        return;
                    }
                    $this$invoke_u24lambda_u240.m121drawInactiveVisualizations3IgeMak$animation(drawScope, j, lookaheadAnimationVisualDebugConfig.getIsShowKeyLabelEnabled(), f, sharedBoundsNode.getSharedElement().getKey(), sharedBoundsNode.textMeasurer);
                }
            }
        }, 1, null);
    }

    @Override // androidx.compose.ui.node.ObserverModifierNode
    public void onObservedReadsChanged() {
        getSharedElement().updateMatch$animation();
        ObserverModifierNodeKt.observeReads(this, getSharedElement().getObservingVisibilityChange$animation());
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
