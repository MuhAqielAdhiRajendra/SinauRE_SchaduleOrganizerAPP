package androidx.compose.animation;

import androidx.compose.animation.core.AnimationVector4D;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.animation.core.Transition;
import androidx.compose.runtime.State;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LayoutCoordinatesKt;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DrawModifierNode;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: EnterExitTransition.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002BC\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u001c\u0010\u0006\u001a\u0018\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007R\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0004\b\u000e\u0010\u000fJ\f\u0010 \u001a\u00020!*\u00020\"H\u0016R \u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R0\u0010\u0006\u001a\u0018\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007R\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001a\u0010\f\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001f¨\u0006#"}, d2 = {"Landroidx/compose/animation/VeilModifierNode;", "Landroidx/compose/ui/Modifier$Node;", "Landroidx/compose/ui/node/DrawModifierNode;", "transition", "Landroidx/compose/animation/core/Transition;", "Landroidx/compose/animation/EnterExitState;", "veilAnimation", "Landroidx/compose/animation/core/Transition$DeferredAnimation;", "Landroidx/compose/ui/graphics/Color;", "Landroidx/compose/animation/core/AnimationVector4D;", "enter", "Landroidx/compose/animation/EnterTransition;", "exit", "Landroidx/compose/animation/ExitTransition;", "<init>", "(Landroidx/compose/animation/core/Transition;Landroidx/compose/animation/core/Transition$DeferredAnimation;Landroidx/compose/animation/EnterTransition;Landroidx/compose/animation/ExitTransition;)V", "getTransition", "()Landroidx/compose/animation/core/Transition;", "setTransition", "(Landroidx/compose/animation/core/Transition;)V", "getVeilAnimation", "()Landroidx/compose/animation/core/Transition$DeferredAnimation;", "setVeilAnimation", "(Landroidx/compose/animation/core/Transition$DeferredAnimation;)V", "getEnter", "()Landroidx/compose/animation/EnterTransition;", "setEnter", "(Landroidx/compose/animation/EnterTransition;)V", "getExit", "()Landroidx/compose/animation/ExitTransition;", "setExit", "(Landroidx/compose/animation/ExitTransition;)V", "draw", "", "Landroidx/compose/ui/graphics/drawscope/ContentDrawScope;", "animation"}, k = 1, mv = {2, 1, 0}, xi = 48)
final class VeilModifierNode extends Modifier.Node implements DrawModifierNode {
    private EnterTransition enter;
    private ExitTransition exit;
    private Transition<EnterExitState> transition;
    private Transition<EnterExitState>.DeferredAnimation<Color, AnimationVector4D> veilAnimation;

    public VeilModifierNode(Transition<EnterExitState> transition, Transition<EnterExitState>.DeferredAnimation<Color, AnimationVector4D> deferredAnimation, EnterTransition enter, ExitTransition exit) {
        this.transition = transition;
        this.veilAnimation = deferredAnimation;
        this.enter = enter;
        this.exit = exit;
    }

    public final Transition<EnterExitState> getTransition() {
        return this.transition;
    }

    public final void setTransition(Transition<EnterExitState> transition) {
        this.transition = transition;
    }

    public final Transition<EnterExitState>.DeferredAnimation<Color, AnimationVector4D> getVeilAnimation() {
        return this.veilAnimation;
    }

    public final void setVeilAnimation(Transition<EnterExitState>.DeferredAnimation<Color, AnimationVector4D> deferredAnimation) {
        this.veilAnimation = deferredAnimation;
    }

    public final EnterTransition getEnter() {
        return this.enter;
    }

    public final void setEnter(EnterTransition enterTransition) {
        this.enter = enterTransition;
    }

    public final ExitTransition getExit() {
        return this.exit;
    }

    public final void setExit(ExitTransition exitTransition) {
        this.exit = exitTransition;
    }

    @Override // androidx.compose.ui.node.DrawModifierNode
    public void draw(ContentDrawScope $this$draw) {
        State<Color> state;
        long v2$iv$iv;
        $this$draw.drawContent();
        State<Color> stateAnimate = this.veilAnimation.animate(new Function1<Transition.Segment<EnterExitState>, FiniteAnimationSpec<Color>>() { // from class: androidx.compose.animation.VeilModifierNode$draw$veilColor$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final FiniteAnimationSpec<Color> invoke(Transition.Segment<EnterExitState> segment) {
                FiniteAnimationSpec<Color> animationSpec;
                FiniteAnimationSpec<Color> animationSpec2;
                if (segment.isTransitioningTo(EnterExitState.PreEnter, EnterExitState.Visible)) {
                    Veil veil = this.this$0.getEnter().getData().getVeil();
                    if (veil == null || (animationSpec2 = veil.getAnimationSpec()) == null) {
                        return EnterExitTransitionKt.DefaultColorAnimationSpec;
                    }
                    return animationSpec2;
                }
                if (!segment.isTransitioningTo(EnterExitState.Visible, EnterExitState.PostExit)) {
                    return EnterExitTransitionKt.DefaultColorAnimationSpec;
                }
                Veil veil2 = this.this$0.getExit().getData().getVeil();
                if (veil2 == null || (animationSpec = veil2.getAnimationSpec()) == null) {
                    return EnterExitTransitionKt.DefaultColorAnimationSpec;
                }
                return animationSpec;
            }
        }, new Function1<EnterExitState, Color>() { // from class: androidx.compose.animation.VeilModifierNode$draw$veilColor$2

            /* JADX INFO: compiled from: EnterExitTransition.kt */
            @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
            public static final /* synthetic */ class WhenMappings {
                public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                static {
                    int[] iArr = new int[EnterExitState.values().length];
                    try {
                        iArr[EnterExitState.Visible.ordinal()] = 1;
                    } catch (NoSuchFieldError e) {
                    }
                    try {
                        iArr[EnterExitState.PreEnter.ordinal()] = 2;
                    } catch (NoSuchFieldError e2) {
                    }
                    try {
                        iArr[EnterExitState.PostExit.ordinal()] = 3;
                    } catch (NoSuchFieldError e3) {
                    }
                    $EnumSwitchMapping$0 = iArr;
                }
            }

            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Color invoke(EnterExitState enterExitState) {
                return Color.m5303boximpl(m182invokevNxB06k(enterExitState));
            }

            /* JADX INFO: renamed from: invoke-vNxB06k, reason: not valid java name */
            public final long m182invokevNxB06k(EnterExitState it) {
                switch (WhenMappings.$EnumSwitchMapping$0[it.ordinal()]) {
                    case 1:
                        Veil veil = this.this$0.getEnter().getData().getVeil();
                        if (veil != null) {
                            return veil.m181getTargetColor0d7_KjU();
                        }
                        Veil veil2 = this.this$0.getExit().getData().getVeil();
                        return veil2 != null ? veil2.m180getInitialColor0d7_KjU() : Color.INSTANCE.m5348getTransparent0d7_KjU();
                    case 2:
                        Veil veil3 = this.this$0.getEnter().getData().getVeil();
                        return veil3 != null ? veil3.m180getInitialColor0d7_KjU() : Color.INSTANCE.m5348getTransparent0d7_KjU();
                    case 3:
                        Veil veil4 = this.this$0.getExit().getData().getVeil();
                        return veil4 != null ? veil4.m181getTargetColor0d7_KjU() : Color.INSTANCE.m5348getTransparent0d7_KjU();
                    default:
                        throw new NoWhenBranchMatchedException();
                }
            }
        });
        if (Color.m5315getAlphaimpl(stateAnimate.getValue().m5323unboximpl()) == 0.0f) {
            return;
        }
        Veil veil = this.enter.getData().getVeil();
        if (veil == null) {
            veil = this.exit.getData().getVeil();
        }
        if (!(veil != null && veil.getMatchParentSize())) {
            DrawScope.m5881drawRectnJ9OG0$default($this$draw, stateAnimate.getValue().m5323unboximpl(), 0L, 0L, 0.0f, null, null, 0, 126, null);
            return;
        }
        LayoutCoordinates layoutCoordinates = DelegatableNodeKt.requireLayoutCoordinates(this);
        LayoutCoordinates parentLayoutCoordinates = layoutCoordinates.getParentLayoutCoordinates();
        if (parentLayoutCoordinates != null) {
            long it = parentLayoutCoordinates.mo6791getSizeYbymL2g();
            float width$iv = (int) (it >> 32);
            float height$iv = (int) (it & 4294967295L);
            long v1$iv$iv = Float.floatToRawIntBits(width$iv);
            state = stateAnimate;
            long v2$iv$iv2 = Float.floatToRawIntBits(height$iv);
            v2$iv$iv = Size.m5128constructorimpl((v1$iv$iv << 32) | (v2$iv$iv2 & 4294967295L));
        } else {
            state = stateAnimate;
            v2$iv$iv = Size.INSTANCE.m5146getZeroNHjbRc();
        }
        long parentSize = v2$iv$iv;
        long offsetInParent = LayoutCoordinatesKt.positionInParent(layoutCoordinates);
        DrawScope.m5881drawRectnJ9OG0$default($this$draw, state.getValue().m5323unboximpl(), Offset.m5060constructorimpl((-9223372034707292160L) ^ offsetInParent), parentSize, 0.0f, null, null, 0, 120, null);
    }
}
