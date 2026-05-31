package androidx.compose.material.ripple;

import androidx.collection.MutableObjectList;
import androidx.collection.ObjectList;
import androidx.compose.foundation.interaction.Interaction;
import androidx.compose.foundation.interaction.InteractionSource;
import androidx.compose.foundation.interaction.PressInteraction;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.ColorProducer;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DrawModifierNode;
import androidx.compose.ui.node.DrawModifierNodeKt;
import androidx.compose.ui.node.LayoutAwareModifierNode;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.IntSizeKt;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

/* JADX INFO: compiled from: Ripple.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0092\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b!\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004B5\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e¢\u0006\u0004\b\u0010\u0010\u0011J\u0017\u0010.\u001a\u00020/2\u0006\u00100\u001a\u000201H\u0016¢\u0006\u0004\b2\u00103J\b\u00104\u001a\u00020/H\u0016J\u0010\u00105\u001a\u00020/2\u0006\u00106\u001a\u00020-H\u0002J\f\u00107\u001a\u00020/*\u000208H\u0016J\f\u00109\u001a\u00020/*\u00020:H&J'\u0010;\u001a\u00020/2\u0006\u0010<\u001a\u00020=2\u0006\u00100\u001a\u00020\"2\u0006\u0010\u001b\u001a\u00020\u001cH&¢\u0006\u0004\b>\u0010?J\u0010\u0010@\u001a\u00020/2\u0006\u0010<\u001a\u00020=H&J\u0018\u0010A\u001a\u00020/2\u0006\u0010<\u001a\u00020B2\u0006\u0010C\u001a\u00020DH\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\u00020\bX\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0010\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0014R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0017\u001a\u00020\bX\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0013R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u001b\u001a\u00020\u001cX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R \u0010#\u001a\u00020\"2\u0006\u0010!\u001a\u00020\"@BX\u0084\u000e¢\u0006\n\n\u0002\u0010&\u001a\u0004\b$\u0010%R\u0011\u0010'\u001a\u00020(8F¢\u0006\u0006\u001a\u0004\b)\u0010%R\u000e\u0010*\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010+\u001a\b\u0012\u0004\u0012\u00020-0,X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006E"}, d2 = {"Landroidx/compose/material/ripple/RippleNode;", "Landroidx/compose/ui/Modifier$Node;", "Landroidx/compose/ui/node/CompositionLocalConsumerModifierNode;", "Landroidx/compose/ui/node/DrawModifierNode;", "Landroidx/compose/ui/node/LayoutAwareModifierNode;", "interactionSource", "Landroidx/compose/foundation/interaction/InteractionSource;", "bounded", "", "radius", "Landroidx/compose/ui/unit/Dp;", TypedValues.Custom.S_COLOR, "Landroidx/compose/ui/graphics/ColorProducer;", "rippleAlpha", "Lkotlin/Function0;", "Landroidx/compose/material/ripple/RippleAlpha;", "<init>", "(Landroidx/compose/foundation/interaction/InteractionSource;ZFLandroidx/compose/ui/graphics/ColorProducer;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "getBounded", "()Z", "F", "getRippleAlpha", "()Lkotlin/jvm/functions/Function0;", "shouldAutoInvalidate", "getShouldAutoInvalidate", "stateLayer", "Landroidx/compose/material/ripple/StateLayer;", "targetRadius", "", "getTargetRadius", "()F", "setTargetRadius", "(F)V", "value", "Landroidx/compose/ui/geometry/Size;", "rippleSize", "getRippleSize-NH-jbRc", "()J", "J", "rippleColor", "Landroidx/compose/ui/graphics/Color;", "getRippleColor-0d7_KjU", "hasValidSize", "pendingInteractions", "Landroidx/collection/MutableObjectList;", "Landroidx/compose/foundation/interaction/PressInteraction;", "onRemeasured", "", "size", "Landroidx/compose/ui/unit/IntSize;", "onRemeasured-ozmzZPI", "(J)V", "onAttach", "handlePressInteraction", "pressInteraction", "draw", "Landroidx/compose/ui/graphics/drawscope/ContentDrawScope;", "drawRipples", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "addRipple", "interaction", "Landroidx/compose/foundation/interaction/PressInteraction$Press;", "addRipple-12SF9DM", "(Landroidx/compose/foundation/interaction/PressInteraction$Press;JF)V", "removeRipple", "updateStateLayer", "Landroidx/compose/foundation/interaction/Interaction;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "material-ripple"}, k = 1, mv = {2, 1, 0}, xi = 48)
public abstract class RippleNode extends Modifier.Node implements CompositionLocalConsumerModifierNode, DrawModifierNode, LayoutAwareModifierNode {
    public static final int $stable = 8;
    private final boolean bounded;
    private final ColorProducer color;
    private boolean hasValidSize;
    private final InteractionSource interactionSource;
    private final MutableObjectList<PressInteraction> pendingInteractions;
    private final float radius;
    private final Function0<RippleAlpha> rippleAlpha;
    private long rippleSize;
    private final boolean shouldAutoInvalidate;
    private StateLayer stateLayer;
    private float targetRadius;

    public /* synthetic */ RippleNode(InteractionSource interactionSource, boolean z, float f, ColorProducer colorProducer, Function0 function0, DefaultConstructorMarker defaultConstructorMarker) {
        this(interactionSource, z, f, colorProducer, function0);
    }

    /* JADX INFO: renamed from: addRipple-12SF9DM */
    public abstract void mo2122addRipple12SF9DM(PressInteraction.Press interaction, long size, float targetRadius);

    public abstract void drawRipples(DrawScope drawScope);

    public abstract void removeRipple(PressInteraction.Press interaction);

    private RippleNode(InteractionSource interactionSource, boolean bounded, float radius, ColorProducer color, Function0<RippleAlpha> function0) {
        this.interactionSource = interactionSource;
        this.bounded = bounded;
        this.radius = radius;
        this.color = color;
        this.rippleAlpha = function0;
        this.rippleSize = Size.INSTANCE.m5146getZeroNHjbRc();
        this.pendingInteractions = new MutableObjectList<>(0, 1, null);
    }

    protected final boolean getBounded() {
        return this.bounded;
    }

    protected final Function0<RippleAlpha> getRippleAlpha() {
        return this.rippleAlpha;
    }

    @Override // androidx.compose.ui.Modifier.Node
    public final boolean getShouldAutoInvalidate() {
        return this.shouldAutoInvalidate;
    }

    protected final float getTargetRadius() {
        return this.targetRadius;
    }

    protected final void setTargetRadius(float f) {
        this.targetRadius = f;
    }

    /* JADX INFO: renamed from: getRippleSize-NH-jbRc, reason: not valid java name and from getter */
    protected final long getRippleSize() {
        return this.rippleSize;
    }

    /* JADX INFO: renamed from: getRippleColor-0d7_KjU, reason: not valid java name */
    public final long m2134getRippleColor0d7_KjU() {
        return this.color.mo2472invoke0d7_KjU();
    }

    @Override // androidx.compose.ui.node.LayoutAwareModifierNode, androidx.compose.ui.node.MeasuredSizeAwareModifierNode
    /* JADX INFO: renamed from: onRemeasured-ozmzZPI */
    public void mo421onRemeasuredozmzZPI(long size) {
        this.hasValidSize = true;
        Density density = DelegatableNodeKt.requireDensity(this);
        this.rippleSize = IntSizeKt.m8333toSizeozmzZPI(size);
        float $this$isUnspecified$iv = this.radius;
        this.targetRadius = Float.isNaN($this$isUnspecified$iv) ? RippleAnimationKt.m2127getRippleEndRadiuscSwnlzA(density, this.bounded, this.rippleSize) : density.mo432toPx0680j_4(this.radius);
        ObjectList this_$iv = this.pendingInteractions;
        Object[] content$iv = this_$iv.content;
        int i = this_$iv._size;
        for (int i$iv = 0; i$iv < i; i$iv++) {
            PressInteraction it = (PressInteraction) content$iv[i$iv];
            handlePressInteraction(it);
        }
        this.pendingInteractions.clear();
    }

    /* JADX INFO: renamed from: androidx.compose.material.ripple.RippleNode$onAttach$1, reason: invalid class name */
    /* JADX INFO: compiled from: Ripple.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.material.ripple.RippleNode$onAttach$1", f = "Ripple.kt", i = {}, l = {364}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = RippleNode.this.new AnonymousClass1(continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    final CoroutineScope $this$launch = (CoroutineScope) this.L$0;
                    Flow<Interaction> interactions = RippleNode.this.interactionSource.getInteractions();
                    final RippleNode rippleNode = RippleNode.this;
                    this.label = 1;
                    if (interactions.collect(new FlowCollector() { // from class: androidx.compose.material.ripple.RippleNode.onAttach.1.1
                        @Override // kotlinx.coroutines.flow.FlowCollector
                        public /* bridge */ /* synthetic */ Object emit(Object value, Continuation $completion) {
                            return emit((Interaction) value, (Continuation<? super Unit>) $completion);
                        }

                        public final Object emit(Interaction interaction, Continuation<? super Unit> continuation) {
                            boolean z = interaction instanceof PressInteraction;
                            RippleNode rippleNode2 = rippleNode;
                            if (z) {
                                boolean z2 = rippleNode2.hasValidSize;
                                RippleNode rippleNode3 = rippleNode;
                                if (z2) {
                                    rippleNode3.handlePressInteraction((PressInteraction) interaction);
                                } else {
                                    MutableObjectList this_$iv = rippleNode3.pendingInteractions;
                                    this_$iv.add(interaction);
                                }
                            } else {
                                rippleNode2.updateStateLayer(interaction, $this$launch);
                            }
                            return Unit.INSTANCE;
                        }
                    }, this) == coroutine_suspended) {
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
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void onAttach() {
        BuildersKt__Builders_commonKt.launch$default(getCoroutineScope(), null, null, new AnonymousClass1(null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handlePressInteraction(PressInteraction pressInteraction) {
        if (!(pressInteraction instanceof PressInteraction.Press)) {
            if (!(pressInteraction instanceof PressInteraction.Release)) {
                if (pressInteraction instanceof PressInteraction.Cancel) {
                    removeRipple(((PressInteraction.Cancel) pressInteraction).getPress());
                    return;
                }
                return;
            }
            removeRipple(((PressInteraction.Release) pressInteraction).getPress());
            return;
        }
        mo2122addRipple12SF9DM((PressInteraction.Press) pressInteraction, this.rippleSize, this.targetRadius);
    }

    @Override // androidx.compose.ui.node.DrawModifierNode
    public void draw(ContentDrawScope $this$draw) throws Throwable {
        $this$draw.drawContent();
        StateLayer $this$draw_u24lambda_u240 = this.stateLayer;
        if ($this$draw_u24lambda_u240 != null) {
            $this$draw_u24lambda_u240.m2139drawStateLayermxwnekA($this$draw, this.targetRadius, m2134getRippleColor0d7_KjU());
        }
        drawRipples($this$draw);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateStateLayer(Interaction interaction, CoroutineScope scope) {
        StateLayer instance = this.stateLayer;
        if (instance == null) {
            instance = new StateLayer(this.bounded, this.rippleAlpha);
            DrawModifierNodeKt.invalidateDraw(this);
            this.stateLayer = instance;
        }
        instance.handleInteraction$material_ripple(interaction, scope);
    }
}
