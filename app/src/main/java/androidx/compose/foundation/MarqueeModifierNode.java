package androidx.compose.foundation;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimatableKt;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.foundation.MarqueeModifierNode;
import androidx.compose.runtime.IntState;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotIntStateKt;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.focus.FocusEventModifierNode;
import androidx.compose.ui.focus.FocusState;
import androidx.compose.ui.graphics.ClipOp;
import androidx.compose.ui.graphics.GraphicsContext;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawContext;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.DrawTransform;
import androidx.compose.ui.graphics.layer.GraphicsLayer;
import androidx.compose.ui.graphics.layer.GraphicsLayerKt;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DrawModifierNode;
import androidx.compose.ui.node.LayoutModifierNode;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.math.MathKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.FlowKt;

/* JADX INFO: compiled from: BasicMarquee.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0092\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0002\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004B7\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\u0006\u0012\u0006\u0010\n\u001a\u00020\u0006\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e¢\u0006\u0004\b\u000f\u0010\u0010J\b\u0010:\u001a\u00020;H\u0016J\b\u0010<\u001a\u00020;H\u0016J=\u0010=\u001a\u00020;2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e¢\u0006\u0004\b>\u0010?J\u0010\u0010@\u001a\u00020;2\u0006\u0010A\u001a\u00020BH\u0016J#\u0010C\u001a\u00020D*\u00020E2\u0006\u0010F\u001a\u00020G2\u0006\u0010H\u001a\u00020IH\u0016¢\u0006\u0004\bJ\u0010KJ\u001c\u0010L\u001a\u00020\u0006*\u00020M2\u0006\u0010F\u001a\u00020N2\u0006\u0010O\u001a\u00020\u0006H\u0016J\u001c\u0010P\u001a\u00020\u0006*\u00020M2\u0006\u0010F\u001a\u00020N2\u0006\u0010O\u001a\u00020\u0006H\u0016J\u001c\u0010Q\u001a\u00020\u0006*\u00020M2\u0006\u0010F\u001a\u00020N2\u0006\u0010R\u001a\u00020\u0006H\u0016J\u001c\u0010S\u001a\u00020\u0006*\u00020M2\u0006\u0010F\u001a\u00020N2\u0006\u0010R\u001a\u00020\u0006H\u0016J\f\u0010T\u001a\u00020;*\u00020UH\u0016J\b\u0010V\u001a\u00020;H\u0002J\u000e\u0010W\u001a\u00020;H\u0082@¢\u0006\u0002\u0010XR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0011R+\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u00068B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R+\u0010\u001a\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u00068B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u001d\u0010\u0019\u001a\u0004\b\u001b\u0010\u0015\"\u0004\b\u001c\u0010\u0017R+\u0010\u001f\u001a\u00020\u001e2\u0006\u0010\u0012\u001a\u00020\u001e8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b$\u0010%\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u0010\u0010&\u001a\u0004\u0018\u00010'X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010(\u001a\u0004\u0018\u00010)X\u0082\u000e¢\u0006\u0002\n\u0000R+\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\f8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b.\u0010%\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R+\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\b8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b1\u0010%\u001a\u0004\b/\u0010\u0015\"\u0004\b0\u0010\u0017R\u001a\u00102\u001a\u000e\u0012\u0004\u0012\u000204\u0012\u0004\u0012\u00020503X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u00106\u001a\u00020\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b8\u00109\u001a\u0004\b7\u0010\u0015¨\u0006Y"}, d2 = {"Landroidx/compose/foundation/MarqueeModifierNode;", "Landroidx/compose/ui/Modifier$Node;", "Landroidx/compose/ui/node/LayoutModifierNode;", "Landroidx/compose/ui/node/DrawModifierNode;", "Landroidx/compose/ui/focus/FocusEventModifierNode;", "iterations", "", "animationMode", "Landroidx/compose/foundation/MarqueeAnimationMode;", "delayMillis", "initialDelayMillis", "spacing", "Landroidx/compose/foundation/MarqueeSpacing;", "velocity", "Landroidx/compose/ui/unit/Dp;", "<init>", "(IIIILandroidx/compose/foundation/MarqueeSpacing;FLkotlin/jvm/internal/DefaultConstructorMarker;)V", "F", "<set-?>", "contentWidth", "getContentWidth", "()I", "setContentWidth", "(I)V", "contentWidth$delegate", "Landroidx/compose/runtime/MutableIntState;", "containerWidth", "getContainerWidth", "setContainerWidth", "containerWidth$delegate", "", "hasFocus", "getHasFocus", "()Z", "setHasFocus", "(Z)V", "hasFocus$delegate", "Landroidx/compose/runtime/MutableState;", "animationJob", "Lkotlinx/coroutines/Job;", "marqueeLayer", "Landroidx/compose/ui/graphics/layer/GraphicsLayer;", "getSpacing", "()Landroidx/compose/foundation/MarqueeSpacing;", "setSpacing", "(Landroidx/compose/foundation/MarqueeSpacing;)V", "spacing$delegate", "getAnimationMode-ZbEOnfQ", "setAnimationMode-97h66l8", "animationMode$delegate", TypedValues.CycleType.S_WAVE_OFFSET, "Landroidx/compose/animation/core/Animatable;", "", "Landroidx/compose/animation/core/AnimationVector1D;", "spacingPx", "getSpacingPx", "spacingPx$delegate", "Landroidx/compose/runtime/State;", "onAttach", "", "onDetach", "update", "update-lWfNwf4", "(IIIILandroidx/compose/foundation/MarqueeSpacing;F)V", "onFocusEvent", "focusState", "Landroidx/compose/ui/focus/FocusState;", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "measurable", "Landroidx/compose/ui/layout/Measurable;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Measurable;J)Landroidx/compose/ui/layout/MeasureResult;", "minIntrinsicWidth", "Landroidx/compose/ui/layout/IntrinsicMeasureScope;", "Landroidx/compose/ui/layout/IntrinsicMeasurable;", "height", "maxIntrinsicWidth", "minIntrinsicHeight", "width", "maxIntrinsicHeight", "draw", "Landroidx/compose/ui/graphics/drawscope/ContentDrawScope;", "restartAnimation", "runAnimation", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
final class MarqueeModifierNode extends Modifier.Node implements LayoutModifierNode, DrawModifierNode, FocusEventModifierNode {
    private Job animationJob;

    /* JADX INFO: renamed from: animationMode$delegate, reason: from kotlin metadata */
    private final MutableState animationMode;

    /* JADX INFO: renamed from: containerWidth$delegate, reason: from kotlin metadata */
    private final MutableIntState containerWidth;

    /* JADX INFO: renamed from: contentWidth$delegate, reason: from kotlin metadata */
    private final MutableIntState contentWidth;
    private int delayMillis;

    /* JADX INFO: renamed from: hasFocus$delegate, reason: from kotlin metadata */
    private final MutableState hasFocus;
    private int initialDelayMillis;
    private int iterations;
    private GraphicsLayer marqueeLayer;
    private final Animatable<Float, AnimationVector1D> offset;

    /* JADX INFO: renamed from: spacing$delegate, reason: from kotlin metadata */
    private final MutableState spacing;

    /* JADX INFO: renamed from: spacingPx$delegate, reason: from kotlin metadata */
    private final State spacingPx;
    private float velocity;

    /* JADX INFO: compiled from: BasicMarquee.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[LayoutDirection.values().length];
            try {
                iArr[LayoutDirection.Ltr.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[LayoutDirection.Rtl.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public /* synthetic */ MarqueeModifierNode(int i, int i2, int i3, int i4, MarqueeSpacing marqueeSpacing, float f, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, i2, i3, i4, marqueeSpacing, f);
    }

    private MarqueeModifierNode(int iterations, int animationMode, int delayMillis, int initialDelayMillis, final MarqueeSpacing spacing, float velocity) {
        this.iterations = iterations;
        this.delayMillis = delayMillis;
        this.initialDelayMillis = initialDelayMillis;
        this.velocity = velocity;
        this.contentWidth = SnapshotIntStateKt.mutableIntStateOf(0);
        this.containerWidth = SnapshotIntStateKt.mutableIntStateOf(0);
        this.hasFocus = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
        this.spacing = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(spacing, null, 2, null);
        this.animationMode = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(MarqueeAnimationMode.m356boximpl(animationMode), null, 2, null);
        this.offset = AnimatableKt.Animatable$default(0.0f, 0.0f, 2, null);
        this.spacingPx = SnapshotStateKt.derivedStateOf(new Function0() { // from class: androidx.compose.foundation.MarqueeModifierNode$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                MarqueeSpacing marqueeSpacing = spacing;
                MarqueeModifierNode marqueeModifierNode = this;
                return Integer.valueOf(marqueeSpacing.calculateSpacing(DelegatableNodeKt.requireDensity(marqueeModifierNode), marqueeModifierNode.getContentWidth(), marqueeModifierNode.getContainerWidth()));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getContentWidth() {
        IntState $this$getValue$iv = this.contentWidth;
        return $this$getValue$iv.getIntValue();
    }

    private final void setContentWidth(int i) {
        MutableIntState $this$setValue$iv = this.contentWidth;
        $this$setValue$iv.setIntValue(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getContainerWidth() {
        IntState $this$getValue$iv = this.containerWidth;
        return $this$getValue$iv.getIntValue();
    }

    private final void setContainerWidth(int i) {
        MutableIntState $this$setValue$iv = this.containerWidth;
        $this$setValue$iv.setIntValue(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean getHasFocus() {
        State $this$getValue$iv = this.hasFocus;
        return ((Boolean) $this$getValue$iv.getValue()).booleanValue();
    }

    private final void setHasFocus(boolean z) {
        MutableState $this$setValue$iv = this.hasFocus;
        $this$setValue$iv.setValue(Boolean.valueOf(z));
    }

    public final MarqueeSpacing getSpacing() {
        State $this$getValue$iv = this.spacing;
        return (MarqueeSpacing) $this$getValue$iv.getValue();
    }

    public final void setSpacing(MarqueeSpacing marqueeSpacing) {
        MutableState $this$setValue$iv = this.spacing;
        $this$setValue$iv.setValue(marqueeSpacing);
    }

    /* JADX INFO: renamed from: getAnimationMode-ZbEOnfQ, reason: not valid java name */
    public final int m370getAnimationModeZbEOnfQ() {
        State $this$getValue$iv = this.animationMode;
        return ((MarqueeAnimationMode) $this$getValue$iv.getValue()).getValue();
    }

    /* JADX INFO: renamed from: setAnimationMode-97h66l8, reason: not valid java name */
    public final void m371setAnimationMode97h66l8(int i) {
        MutableState $this$setValue$iv = this.animationMode;
        $this$setValue$iv.setValue(MarqueeAnimationMode.m356boximpl(i));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getSpacingPx() {
        State $this$getValue$iv = this.spacingPx;
        return ((Number) $this$getValue$iv.getValue()).intValue();
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void onAttach() {
        GraphicsLayer layer = this.marqueeLayer;
        GraphicsContext graphicsContext = DelegatableNodeKt.requireGraphicsContext(this);
        if (layer != null) {
            graphicsContext.releaseGraphicsLayer(layer);
        }
        this.marqueeLayer = graphicsContext.createGraphicsLayer();
        restartAnimation();
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void onDetach() {
        Job job = this.animationJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this.animationJob = null;
        GraphicsLayer layer = this.marqueeLayer;
        if (layer != null) {
            DelegatableNodeKt.requireGraphicsContext(this).releaseGraphicsLayer(layer);
            this.marqueeLayer = null;
        }
    }

    /* JADX INFO: renamed from: update-lWfNwf4, reason: not valid java name */
    public final void m372updatelWfNwf4(int iterations, int animationMode, int delayMillis, int initialDelayMillis, MarqueeSpacing spacing, float velocity) {
        setSpacing(spacing);
        m371setAnimationMode97h66l8(animationMode);
        if (this.iterations != iterations || this.delayMillis != delayMillis || this.initialDelayMillis != initialDelayMillis || !Dp.m8155equalsimpl0(this.velocity, velocity)) {
            this.iterations = iterations;
            this.delayMillis = delayMillis;
            this.initialDelayMillis = initialDelayMillis;
            this.velocity = velocity;
            restartAnimation();
        }
    }

    @Override // androidx.compose.ui.focus.FocusEventModifierNode
    public void onFocusEvent(FocusState focusState) {
        setHasFocus(focusState.getHasFocus());
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    /* JADX INFO: renamed from: measure-3p2s80s */
    public MeasureResult mo67measure3p2s80s(MeasureScope $this$measure_u2d3p2s80s, Measurable measurable, long constraints) {
        long childConstraints = Constraints.m8092copyZbe2FdA(constraints, (11 & 1) != 0 ? Constraints.m8105getMinWidthimpl(constraints) : 0, (11 & 2) != 0 ? Constraints.m8103getMaxWidthimpl(constraints) : Integer.MAX_VALUE, (11 & 4) != 0 ? Constraints.m8104getMinHeightimpl(constraints) : 0, (11 & 8) != 0 ? Constraints.m8102getMaxHeightimpl(constraints) : 0);
        final Placeable placeable = measurable.mo6783measureBRTryo0(childConstraints);
        setContainerWidth(ConstraintsKt.m8120constrainWidthK40F9xA(constraints, placeable.getWidth()));
        setContentWidth(placeable.getWidth());
        return MeasureScope.layout$default($this$measure_u2d3p2s80s, getContainerWidth(), placeable.getHeight(), null, new Function1() { // from class: androidx.compose.foundation.MarqueeModifierNode$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MarqueeModifierNode.measure_3p2s80s$lambda$0(placeable, (Placeable.PlacementScope) obj);
            }
        }, 4, null);
    }

    static final Unit measure_3p2s80s$lambda$0(Placeable $placeable, Placeable.PlacementScope $this$layout) {
        Placeable.PlacementScope.placeWithLayer$default($this$layout, $placeable, 0, 0, 0.0f, (Function1) null, 12, (Object) null);
        return Unit.INSTANCE;
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public int minIntrinsicWidth(IntrinsicMeasureScope $this$minIntrinsicWidth, IntrinsicMeasurable measurable, int height) {
        return 0;
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public int maxIntrinsicWidth(IntrinsicMeasureScope $this$maxIntrinsicWidth, IntrinsicMeasurable measurable, int height) {
        return measurable.maxIntrinsicWidth(height);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public int minIntrinsicHeight(IntrinsicMeasureScope $this$minIntrinsicHeight, IntrinsicMeasurable measurable, int width) {
        return measurable.minIntrinsicHeight(Integer.MAX_VALUE);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public int maxIntrinsicHeight(IntrinsicMeasureScope $this$maxIntrinsicHeight, IntrinsicMeasurable measurable, int width) {
        return measurable.maxIntrinsicHeight(Integer.MAX_VALUE);
    }

    @Override // androidx.compose.ui.node.DrawModifierNode
    public void draw(final ContentDrawScope $this$draw) throws Throwable {
        float contentWidth;
        boolean secondCopyVisible;
        float secondCopyOffset;
        long j;
        DrawContext $this$withTransform_u24lambda_u240$iv$iv;
        DrawScope $this$draw_u24lambda_u241_u240;
        GraphicsLayer layer;
        if (Dp.m8149compareTo0680j_4(this.velocity, Dp.m8150constructorimpl(0)) > 0) {
            switch (WhenMappings.$EnumSwitchMapping$0[$this$draw.getLayoutDirection().ordinal()]) {
                case 1:
                    contentWidth = this.offset.getValue().floatValue();
                    break;
                case 2:
                    contentWidth = (((-this.offset.getValue().floatValue()) + (getContentWidth() * 2)) + getSpacingPx()) - getContainerWidth();
                    break;
                default:
                    throw new NoWhenBranchMatchedException();
            }
        } else {
            switch (WhenMappings.$EnumSwitchMapping$0[$this$draw.getLayoutDirection().ordinal()]) {
                case 1:
                    contentWidth = (-this.offset.getValue().floatValue()) + getContentWidth() + getSpacingPx();
                    break;
                case 2:
                    contentWidth = (this.offset.getValue().floatValue() + getContentWidth()) - getContainerWidth();
                    break;
                default:
                    throw new NoWhenBranchMatchedException();
            }
        }
        float clipWindowOffset = contentWidth;
        boolean firstCopyVisible = clipWindowOffset < ((float) getContentWidth());
        boolean secondCopyVisible2 = ((float) getContainerWidth()) + clipWindowOffset > ((float) (getContentWidth() + getSpacingPx()));
        float secondCopyOffset2 = getContentWidth() + getSpacingPx();
        long arg0$iv = $this$draw.mo5887getSizeNHjbRc();
        int bits$iv$iv$iv = (int) (arg0$iv & 4294967295L);
        float drawHeight = Float.intBitsToFloat(bits$iv$iv$iv);
        GraphicsLayer layer2 = this.marqueeLayer;
        if (layer2 != null) {
            int width$iv = getContentWidth();
            int height$iv = MathKt.roundToInt(drawHeight);
            j = 4294967295L;
            secondCopyVisible = secondCopyVisible2;
            secondCopyOffset = secondCopyOffset2;
            $this$draw.mo5888recordJVtK1S4(layer2, IntSize.m8316constructorimpl((((long) height$iv) & 4294967295L) | (((long) width$iv) << 32)), new Function1() { // from class: androidx.compose.foundation.MarqueeModifierNode$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return MarqueeModifierNode.draw$lambda$0$0($this$draw, (DrawScope) obj);
                }
            });
        } else {
            secondCopyVisible = secondCopyVisible2;
            secondCopyOffset = secondCopyOffset2;
            j = 4294967295L;
        }
        ContentDrawScope $this$clipRect_u2drOu3jXo_u24default$iv = $this$draw;
        float right$iv = getContainerWidth();
        long arg0$iv$iv = $this$clipRect_u2drOu3jXo_u24default$iv.mo5887getSizeNHjbRc();
        int bits$iv$iv$iv$iv = (int) (arg0$iv$iv & j);
        float bottom$iv = Float.intBitsToFloat(bits$iv$iv$iv$iv);
        int clipOp$iv = ClipOp.INSTANCE.m5302getIntersectrtfAjoo();
        DrawContext $this$withTransform_u24lambda_u240$iv$iv2 = $this$clipRect_u2drOu3jXo_u24default$iv.getDrawContext();
        long previousSize$iv$iv = $this$withTransform_u24lambda_u240$iv$iv2.mo5808getSizeNHjbRc();
        $this$withTransform_u24lambda_u240$iv$iv2.getCanvas().save();
        try {
            DrawTransform $this$clipRect_rOu3jXo_u24lambda_u240$iv = $this$withTransform_u24lambda_u240$iv$iv2.getTransform();
            try {
                $this$clipRect_rOu3jXo_u24lambda_u240$iv.mo5811clipRectN_I0leg(0.0f, 0.0f, right$iv, bottom$iv, clipOp$iv);
                float left$iv = -clipWindowOffset;
                float top$iv = 0.0f;
                try {
                    $this$clipRect_u2drOu3jXo_u24default$iv.getDrawContext().getTransform().translate(left$iv, top$iv);
                    try {
                        GraphicsLayer layer3 = this.marqueeLayer;
                        try {
                            if (layer3 != null) {
                                if (firstCopyVisible) {
                                    $this$draw_u24lambda_u241_u240 = $this$clipRect_u2drOu3jXo_u24default$iv;
                                    layer = layer3;
                                    try {
                                        GraphicsLayerKt.drawLayer($this$draw_u24lambda_u241_u240, layer);
                                    } catch (Throwable th) {
                                        th = th;
                                        $this$withTransform_u24lambda_u240$iv$iv = $this$withTransform_u24lambda_u240$iv$iv2;
                                        try {
                                            throw th;
                                        } catch (Throwable th2) {
                                            th = th2;
                                            $this$withTransform_u24lambda_u240$iv$iv.getCanvas().restore();
                                            $this$withTransform_u24lambda_u240$iv$iv.mo5809setSizeuvyYCjk(previousSize$iv$iv);
                                            throw th;
                                        }
                                    }
                                } else {
                                    $this$draw_u24lambda_u241_u240 = $this$clipRect_u2drOu3jXo_u24default$iv;
                                    layer = layer3;
                                }
                                if (secondCopyVisible) {
                                    float left$iv2 = secondCopyOffset;
                                    DrawScope $this$translate_u24default$iv = $this$draw_u24lambda_u241_u240;
                                    try {
                                    } catch (Throwable th3) {
                                        th = th3;
                                        $this$withTransform_u24lambda_u240$iv$iv = $this$withTransform_u24lambda_u240$iv$iv2;
                                    }
                                    try {
                                        $this$translate_u24default$iv.getDrawContext().getTransform().translate(left$iv2, 0.0f);
                                        try {
                                            GraphicsLayerKt.drawLayer($this$translate_u24default$iv, layer);
                                            $this$translate_u24default$iv.getDrawContext().getTransform().translate(-left$iv2, -0.0f);
                                        } catch (Throwable th4) {
                                            $this$translate_u24default$iv.getDrawContext().getTransform().translate(-left$iv2, -0.0f);
                                            throw th4;
                                        }
                                    } catch (Throwable th5) {
                                        th = th5;
                                        $this$withTransform_u24lambda_u240$iv$iv = $this$withTransform_u24lambda_u240$iv$iv2;
                                        throw th;
                                    }
                                }
                            } else {
                                if (firstCopyVisible) {
                                    $this$draw.drawContent();
                                }
                                if (secondCopyVisible) {
                                    left$iv = secondCopyOffset;
                                    top$iv = 0.0f;
                                    $this$clipRect_u2drOu3jXo_u24default$iv.getDrawContext().getTransform().translate(left$iv, top$iv);
                                    try {
                                        $this$draw.drawContent();
                                        $this$clipRect_u2drOu3jXo_u24default$iv.getDrawContext().getTransform().translate(-left$iv, -top$iv);
                                    } finally {
                                        $this$clipRect_u2drOu3jXo_u24default$iv.getDrawContext().getTransform().translate(-left$iv, -top$iv);
                                    }
                                }
                            }
                            try {
                                $this$withTransform_u24lambda_u240$iv$iv2.getCanvas().restore();
                                $this$withTransform_u24lambda_u240$iv$iv2.mo5809setSizeuvyYCjk(previousSize$iv$iv);
                            } catch (Throwable th6) {
                                th = th6;
                                $this$withTransform_u24lambda_u240$iv$iv = $this$withTransform_u24lambda_u240$iv$iv2;
                                $this$withTransform_u24lambda_u240$iv$iv.getCanvas().restore();
                                $this$withTransform_u24lambda_u240$iv$iv.mo5809setSizeuvyYCjk(previousSize$iv$iv);
                                throw th;
                            }
                        } catch (Throwable th7) {
                            th = th7;
                            $this$withTransform_u24lambda_u240$iv$iv = $this$withTransform_u24lambda_u240$iv$iv2;
                            throw th;
                        }
                    } catch (Throwable th8) {
                        th = th8;
                        $this$withTransform_u24lambda_u240$iv$iv = $this$withTransform_u24lambda_u240$iv$iv2;
                    }
                } catch (Throwable th9) {
                    th = th9;
                    $this$withTransform_u24lambda_u240$iv$iv = $this$withTransform_u24lambda_u240$iv$iv2;
                }
            } catch (Throwable th10) {
                th = th10;
                $this$withTransform_u24lambda_u240$iv$iv = $this$withTransform_u24lambda_u240$iv$iv2;
            }
        } catch (Throwable th11) {
            th = th11;
            $this$withTransform_u24lambda_u240$iv$iv = $this$withTransform_u24lambda_u240$iv$iv2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit draw$lambda$0$0(ContentDrawScope $this_draw, DrawScope $this$record) {
        $this_draw.drawContent();
        return Unit.INSTANCE;
    }

    private final void restartAnimation() {
        Job oldJob = this.animationJob;
        if (oldJob != null) {
            Job.DefaultImpls.cancel$default(oldJob, (CancellationException) null, 1, (Object) null);
        }
        if (getIsAttached()) {
            this.animationJob = BuildersKt__Builders_commonKt.launch$default(getCoroutineScope(), null, null, new AnonymousClass1(oldJob, this, null), 3, null);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.MarqueeModifierNode$restartAnimation$1, reason: invalid class name */
    /* JADX INFO: compiled from: BasicMarquee.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.MarqueeModifierNode$restartAnimation$1", f = "BasicMarquee.kt", i = {}, l = {390, 391}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Job $oldJob;
        int label;
        final /* synthetic */ MarqueeModifierNode this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(Job job, MarqueeModifierNode marqueeModifierNode, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$oldJob = job;
            this.this$0 = marqueeModifierNode;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$oldJob, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:15:0x003d A[RETURN] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r5) {
            /*
                r4 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r4.label
                switch(r1) {
                    case 0: goto L1a;
                    case 1: goto L16;
                    case 2: goto L12;
                    default: goto L9;
                }
            L9:
                java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r5.<init>(r0)
                throw r5
            L12:
                kotlin.ResultKt.throwOnFailure(r5)
                goto L3e
            L16:
                kotlin.ResultKt.throwOnFailure(r5)
                goto L2e
            L1a:
                kotlin.ResultKt.throwOnFailure(r5)
                kotlinx.coroutines.Job r1 = r4.$oldJob
                if (r1 == 0) goto L2e
                r2 = r4
                kotlin.coroutines.Continuation r2 = (kotlin.coroutines.Continuation) r2
                r3 = 1
                r4.label = r3
                java.lang.Object r1 = r1.join(r2)
                if (r1 != r0) goto L2e
                return r0
            L2e:
                androidx.compose.foundation.MarqueeModifierNode r1 = r4.this$0
                r2 = r4
                kotlin.coroutines.Continuation r2 = (kotlin.coroutines.Continuation) r2
                r3 = 2
                r4.label = r3
                java.lang.Object r1 = androidx.compose.foundation.MarqueeModifierNode.access$runAnimation(r1, r2)
                if (r1 != r0) goto L3e
                return r0
            L3e:
                kotlin.Unit r0 = kotlin.Unit.INSTANCE
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.MarqueeModifierNode.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object runAnimation(Continuation<? super Unit> continuation) {
        if (this.iterations <= 0) {
            return Unit.INSTANCE;
        }
        Object objWithContext = BuildersKt.withContext(FixedMotionDurationScale.INSTANCE, new AnonymousClass2(null), continuation);
        return objWithContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objWithContext : Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.MarqueeModifierNode$runAnimation$2, reason: invalid class name */
    /* JADX INFO: compiled from: BasicMarquee.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.MarqueeModifierNode$runAnimation$2", f = "BasicMarquee.kt", i = {}, l = {413}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return MarqueeModifierNode.this.new AnonymousClass2(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    final MarqueeModifierNode marqueeModifierNode = MarqueeModifierNode.this;
                    this.label = 1;
                    if (FlowKt.collectLatest(SnapshotStateKt.snapshotFlow(new Function0() { // from class: androidx.compose.foundation.MarqueeModifierNode$runAnimation$2$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return MarqueeModifierNode.AnonymousClass2.invokeSuspend$lambda$0(marqueeModifierNode);
                        }
                    }), new C00082(MarqueeModifierNode.this, null), this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Unit.INSTANCE;
        }

        static final Float invokeSuspend$lambda$0(MarqueeModifierNode this$0) {
            if (this$0.getContentWidth() <= this$0.getContainerWidth()) {
                return null;
            }
            if (!MarqueeAnimationMode.m359equalsimpl0(this$0.m370getAnimationModeZbEOnfQ(), MarqueeAnimationMode.INSTANCE.m364getWhileFocusedZbEOnfQ()) || this$0.getHasFocus()) {
                return Float.valueOf(this$0.getContentWidth() + this$0.getSpacingPx());
            }
            return null;
        }

        /* JADX INFO: renamed from: androidx.compose.foundation.MarqueeModifierNode$runAnimation$2$2, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: BasicMarquee.kt */
        @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0007\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n"}, d2 = {"<anonymous>", "", "contentWithSpacingWidth", ""}, k = 3, mv = {2, 1, 0}, xi = 48)
        @DebugMetadata(c = "androidx.compose.foundation.MarqueeModifierNode$runAnimation$2$2", f = "BasicMarquee.kt", i = {0, 0}, l = {427, 429, 433, 433}, m = "invokeSuspend", n = {"contentWithSpacingWidth", "spec"}, s = {"L$0", "L$1"}, v = 1)
        static final class C00082 extends SuspendLambda implements Function2<Float, Continuation<? super Unit>, Object> {
            /* synthetic */ Object L$0;
            Object L$1;
            int label;
            final /* synthetic */ MarqueeModifierNode this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C00082(MarqueeModifierNode marqueeModifierNode, Continuation<? super C00082> continuation) {
                super(2, continuation);
                this.this$0 = marqueeModifierNode;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                C00082 c00082 = new C00082(this.this$0, continuation);
                c00082.L$0 = obj;
                return c00082;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Float f, Continuation<? super Unit> continuation) {
                return ((C00082) create(f, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Removed duplicated region for block: B:24:0x00a8 A[RETURN] */
            /* JADX WARN: Removed duplicated region for block: B:25:0x00a9  */
            /* JADX WARN: Removed duplicated region for block: B:29:0x00c3 A[RETURN] */
            /* JADX WARN: Removed duplicated region for block: B:30:0x00c4  */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final java.lang.Object invokeSuspend(java.lang.Object r13) throws java.lang.Throwable {
                /*
                    Method dump skipped, instruction units count: 246
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.MarqueeModifierNode.AnonymousClass2.C00082.invokeSuspend(java.lang.Object):java.lang.Object");
            }
        }
    }
}
