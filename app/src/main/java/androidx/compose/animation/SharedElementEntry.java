package androidx.compose.animation;

import androidx.compose.animation.SharedTransitionScope;
import androidx.compose.animation.SharedTransitionStateMachine;
import androidx.compose.runtime.FloatState;
import androidx.compose.runtime.MutableFloatState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.PrimitiveSnapshotStateKt;
import androidx.compose.runtime.RememberObserver;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.graphics.ClipOp;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.drawscope.DrawContext;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.DrawTransform;
import androidx.compose.ui.graphics.layer.GraphicsLayer;
import androidx.compose.ui.graphics.layer.GraphicsLayerKt;
import androidx.compose.ui.unit.IntSizeKt;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Metadata;

/* JADX INFO: compiled from: SharedElementEntry.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b0\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0010\b\u0001\u0018\u00002\u00020\u00012\u00020\u0002BG\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\n\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011¢\u0006\u0004\b\u0012\u0010\u0013J\u0010\u0010A\u001a\u0004\u0018\u00010B2\u0006\u0010C\u001a\u00020BJ\u0010\u0010J\u001a\u00020K2\u0006\u0010L\u001a\u00020MH\u0016J\b\u0010h\u001a\u00020KH\u0016J\b\u0010i\u001a\u00020KH\u0016J\b\u0010j\u001a\u00020KH\u0016R+\u0010\u0015\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\n8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R+\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u00118V@VX\u0096\u008e\u0002¢\u0006\u0012\n\u0004\b\u001f\u0010 \u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR+\u0010\r\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\n8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b#\u0010\u001a\u001a\u0004\b!\u0010\u0016\"\u0004\b\"\u0010\u0018R+\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00048F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b(\u0010\u001a\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R+\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u00068F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b-\u0010\u001a\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R+\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\b8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b2\u0010\u001a\u001a\u0004\b.\u0010/\"\u0004\b0\u00101R+\u0010\t\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\n8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b5\u0010\u001a\u001a\u0004\b3\u0010\u0016\"\u0004\b4\u0010\u0018R+\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\f8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b:\u0010\u001a\u001a\u0004\b6\u00107\"\u0004\b8\u00109R+\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u000f8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b?\u0010\u001a\u001a\u0004\b;\u0010<\"\u0004\b=\u0010>R\u0011\u0010@\u001a\u00020\n8F¢\u0006\u0006\u001a\u0004\b@\u0010\u0016R\u001c\u0010D\u001a\u0004\u0018\u00010EX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bF\u0010G\"\u0004\bH\u0010IR\u001c\u0010N\u001a\u0004\u0018\u00010\u0000X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bO\u0010P\"\u0004\bQ\u0010RR\u0011\u0010S\u001a\u00020\n8F¢\u0006\u0006\u001a\u0004\bT\u0010\u0016R\u001c\u0010U\u001a\u0004\u0018\u00010VX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bW\u0010X\"\u0004\bY\u0010ZR/\u0010\\\u001a\u0004\u0018\u00010[2\b\u0010\u0014\u001a\u0004\u0018\u00010[8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\ba\u0010\u001a\u001a\u0004\b]\u0010^\"\u0004\b_\u0010`R\u0014\u0010b\u001a\u00020\n8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bc\u0010\u0016R\u0014\u0010d\u001a\u00020\n8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\be\u0010\u0016R\u0011\u0010f\u001a\u00020\n8F¢\u0006\u0006\u001a\u0004\bg\u0010\u0016¨\u0006k"}, d2 = {"Landroidx/compose/animation/SharedElementEntry;", "Landroidx/compose/animation/LayerRenderer;", "Landroidx/compose/runtime/RememberObserver;", "sharedElement", "Landroidx/compose/animation/SharedElement;", "boundsAnimation", "Landroidx/compose/animation/BoundsAnimation;", "placeholderSize", "Landroidx/compose/animation/SharedTransitionScope$PlaceholderSize;", "renderOnlyWhenVisible", "", "overlayClip", "Landroidx/compose/animation/SharedTransitionScope$OverlayClip;", "renderInOverlayDuringTransition", "userState", "Landroidx/compose/animation/SharedTransitionScope$SharedContentState;", "zIndex", "", "<init>", "(Landroidx/compose/animation/SharedElement;Landroidx/compose/animation/BoundsAnimation;Landroidx/compose/animation/SharedTransitionScope$PlaceholderSize;ZLandroidx/compose/animation/SharedTransitionScope$OverlayClip;ZLandroidx/compose/animation/SharedTransitionScope$SharedContentState;F)V", "<set-?>", "isAttached", "()Z", "setAttached", "(Z)V", "isAttached$delegate", "Landroidx/compose/runtime/MutableState;", "getZIndex", "()F", "setZIndex", "(F)V", "zIndex$delegate", "Landroidx/compose/runtime/MutableFloatState;", "getRenderInOverlayDuringTransition", "setRenderInOverlayDuringTransition", "renderInOverlayDuringTransition$delegate", "getSharedElement", "()Landroidx/compose/animation/SharedElement;", "setSharedElement", "(Landroidx/compose/animation/SharedElement;)V", "sharedElement$delegate", "getBoundsAnimation", "()Landroidx/compose/animation/BoundsAnimation;", "setBoundsAnimation", "(Landroidx/compose/animation/BoundsAnimation;)V", "boundsAnimation$delegate", "getPlaceholderSize", "()Landroidx/compose/animation/SharedTransitionScope$PlaceholderSize;", "setPlaceholderSize", "(Landroidx/compose/animation/SharedTransitionScope$PlaceholderSize;)V", "placeholderSize$delegate", "getRenderOnlyWhenVisible", "setRenderOnlyWhenVisible", "renderOnlyWhenVisible$delegate", "getOverlayClip", "()Landroidx/compose/animation/SharedTransitionScope$OverlayClip;", "setOverlayClip", "(Landroidx/compose/animation/SharedTransitionScope$OverlayClip;)V", "overlayClip$delegate", "getUserState", "()Landroidx/compose/animation/SharedTransitionScope$SharedContentState;", "setUserState", "(Landroidx/compose/animation/SharedTransitionScope$SharedContentState;)V", "userState$delegate", "isEnabled", "calculateTargetBounds", "Landroidx/compose/ui/geometry/Rect;", "previousTargetBoundsBeforeLosingTarget", "clipPathInOverlay", "Landroidx/compose/ui/graphics/Path;", "getClipPathInOverlay$animation", "()Landroidx/compose/ui/graphics/Path;", "setClipPathInOverlay$animation", "(Landroidx/compose/ui/graphics/Path;)V", "drawInOverlay", "", "drawScope", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "parentState", "getParentState", "()Landroidx/compose/animation/SharedElementEntry;", "setParentState", "(Landroidx/compose/animation/SharedElementEntry;)V", TypedValues.AttributesType.S_TARGET, "getTarget", "boundsProvider", "Landroidx/compose/animation/BoundsProvider;", "getBoundsProvider", "()Landroidx/compose/animation/BoundsProvider;", "setBoundsProvider", "(Landroidx/compose/animation/BoundsProvider;)V", "Landroidx/compose/ui/graphics/layer/GraphicsLayer;", "layer", "getLayer", "()Landroidx/compose/ui/graphics/layer/GraphicsLayer;", "setLayer", "(Landroidx/compose/ui/graphics/layer/GraphicsLayer;)V", "layer$delegate", "shouldRenderAtAll", "getShouldRenderAtAll", "shouldRenderInOverlay", "getShouldRenderInOverlay$animation", "shouldRenderInPlace", "getShouldRenderInPlace", "onRemembered", "onForgotten", "onAbandoned", "animation"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class SharedElementEntry implements LayerRenderer, RememberObserver {
    public static final int $stable = 8;

    /* JADX INFO: renamed from: boundsAnimation$delegate, reason: from kotlin metadata */
    private final MutableState boundsAnimation;
    private BoundsProvider boundsProvider;
    private Path clipPathInOverlay;

    /* JADX INFO: renamed from: isAttached$delegate, reason: from kotlin metadata */
    private final MutableState isAttached = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);

    /* JADX INFO: renamed from: layer$delegate, reason: from kotlin metadata */
    private final MutableState layer = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);

    /* JADX INFO: renamed from: overlayClip$delegate, reason: from kotlin metadata */
    private final MutableState overlayClip;
    private SharedElementEntry parentState;

    /* JADX INFO: renamed from: placeholderSize$delegate, reason: from kotlin metadata */
    private final MutableState placeholderSize;

    /* JADX INFO: renamed from: renderInOverlayDuringTransition$delegate, reason: from kotlin metadata */
    private final MutableState renderInOverlayDuringTransition;

    /* JADX INFO: renamed from: renderOnlyWhenVisible$delegate, reason: from kotlin metadata */
    private final MutableState renderOnlyWhenVisible;

    /* JADX INFO: renamed from: sharedElement$delegate, reason: from kotlin metadata */
    private final MutableState sharedElement;

    /* JADX INFO: renamed from: userState$delegate, reason: from kotlin metadata */
    private final MutableState userState;

    /* JADX INFO: renamed from: zIndex$delegate, reason: from kotlin metadata */
    private final MutableFloatState zIndex;

    public SharedElementEntry(SharedElement sharedElement, BoundsAnimation boundsAnimation, SharedTransitionScope.PlaceholderSize placeholderSize, boolean renderOnlyWhenVisible, SharedTransitionScope.OverlayClip overlayClip, boolean renderInOverlayDuringTransition, SharedTransitionScope.SharedContentState userState, float zIndex) {
        this.zIndex = PrimitiveSnapshotStateKt.mutableFloatStateOf(zIndex);
        this.renderInOverlayDuringTransition = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.valueOf(renderInOverlayDuringTransition), null, 2, null);
        this.sharedElement = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(sharedElement, null, 2, null);
        this.boundsAnimation = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(boundsAnimation, null, 2, null);
        this.placeholderSize = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(placeholderSize, null, 2, null);
        this.renderOnlyWhenVisible = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.valueOf(renderOnlyWhenVisible), null, 2, null);
        this.overlayClip = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(overlayClip, null, 2, null);
        this.userState = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(userState, null, 2, null);
    }

    public final boolean isAttached() {
        State $this$getValue$iv = this.isAttached;
        return ((Boolean) $this$getValue$iv.getValue()).booleanValue();
    }

    public final void setAttached(boolean z) {
        MutableState $this$setValue$iv = this.isAttached;
        $this$setValue$iv.setValue(Boolean.valueOf(z));
    }

    @Override // androidx.compose.animation.LayerRenderer
    public float getZIndex() {
        FloatState $this$getValue$iv = this.zIndex;
        return $this$getValue$iv.getFloatValue();
    }

    public void setZIndex(float f) {
        MutableFloatState $this$setValue$iv = this.zIndex;
        $this$setValue$iv.setFloatValue(f);
    }

    public final boolean getRenderInOverlayDuringTransition() {
        State $this$getValue$iv = this.renderInOverlayDuringTransition;
        return ((Boolean) $this$getValue$iv.getValue()).booleanValue();
    }

    public final void setRenderInOverlayDuringTransition(boolean z) {
        MutableState $this$setValue$iv = this.renderInOverlayDuringTransition;
        $this$setValue$iv.setValue(Boolean.valueOf(z));
    }

    public final SharedElement getSharedElement() {
        State $this$getValue$iv = this.sharedElement;
        return (SharedElement) $this$getValue$iv.getValue();
    }

    public final void setSharedElement(SharedElement sharedElement) {
        MutableState $this$setValue$iv = this.sharedElement;
        $this$setValue$iv.setValue(sharedElement);
    }

    public final BoundsAnimation getBoundsAnimation() {
        State $this$getValue$iv = this.boundsAnimation;
        return (BoundsAnimation) $this$getValue$iv.getValue();
    }

    public final void setBoundsAnimation(BoundsAnimation boundsAnimation) {
        MutableState $this$setValue$iv = this.boundsAnimation;
        $this$setValue$iv.setValue(boundsAnimation);
    }

    public final SharedTransitionScope.PlaceholderSize getPlaceholderSize() {
        State $this$getValue$iv = this.placeholderSize;
        return (SharedTransitionScope.PlaceholderSize) $this$getValue$iv.getValue();
    }

    public final void setPlaceholderSize(SharedTransitionScope.PlaceholderSize placeholderSize) {
        MutableState $this$setValue$iv = this.placeholderSize;
        $this$setValue$iv.setValue(placeholderSize);
    }

    public final boolean getRenderOnlyWhenVisible() {
        State $this$getValue$iv = this.renderOnlyWhenVisible;
        return ((Boolean) $this$getValue$iv.getValue()).booleanValue();
    }

    public final void setRenderOnlyWhenVisible(boolean z) {
        MutableState $this$setValue$iv = this.renderOnlyWhenVisible;
        $this$setValue$iv.setValue(Boolean.valueOf(z));
    }

    public final SharedTransitionScope.OverlayClip getOverlayClip() {
        State $this$getValue$iv = this.overlayClip;
        return (SharedTransitionScope.OverlayClip) $this$getValue$iv.getValue();
    }

    public final void setOverlayClip(SharedTransitionScope.OverlayClip overlayClip) {
        MutableState $this$setValue$iv = this.overlayClip;
        $this$setValue$iv.setValue(overlayClip);
    }

    public final SharedTransitionScope.SharedContentState getUserState() {
        State $this$getValue$iv = this.userState;
        return (SharedTransitionScope.SharedContentState) $this$getValue$iv.getValue();
    }

    public final void setUserState(SharedTransitionScope.SharedContentState sharedContentState) {
        MutableState $this$setValue$iv = this.userState;
        $this$setValue$iv.setValue(sharedContentState);
    }

    public final boolean isEnabled() {
        SharedTransitionScope.SharedContentState $this$_get_isEnabled__u24lambda_u240 = getUserState();
        return isAttached() && $this$_get_isEnabled__u24lambda_u240.isEnabledByUser$animation();
    }

    public final Rect calculateTargetBounds(Rect previousTargetBoundsBeforeLosingTarget) {
        SharedTransitionScope.SharedContentConfig $this$calculateTargetBounds_u24lambda_u240 = getUserState().getConfig$animation();
        return $this$calculateTargetBounds_u24lambda_u240.m146alternativeTargetBoundsInTransitionScopeAfterRemovalcSwnlzA(getUserState(), previousTargetBoundsBeforeLosingTarget, IntSizeKt.m8333toSizeozmzZPI(getSharedElement().getScope().getLookaheadRoot$animation().mo6791getSizeYbymL2g()));
    }

    /* JADX INFO: renamed from: getClipPathInOverlay$animation, reason: from getter */
    public final Path getClipPathInOverlay() {
        return this.clipPathInOverlay;
    }

    public final void setClipPathInOverlay$animation(Path path) {
        this.clipPathInOverlay = path;
    }

    @Override // androidx.compose.animation.LayerRenderer
    public void drawInOverlay(DrawScope drawScope) throws Throwable {
        GraphicsLayer layer = getLayer();
        if (layer == null) {
            return;
        }
        SharedTransitionStateMachine.State matchState = getSharedElement().getState$animation();
        Rect currentBounds = matchState.getCurrentBounds();
        if (currentBounds != null && getShouldRenderInOverlay$animation()) {
            long jM5103getTopLeftF1C5BW0 = currentBounds.m5103getTopLeftF1C5BW0();
            int bits$iv$iv$iv$iv = (int) (jM5103getTopLeftF1C5BW0 >> 32);
            float x = Float.intBitsToFloat(bits$iv$iv$iv$iv);
            int bits$iv$iv$iv$iv2 = (int) (4294967295L & jM5103getTopLeftF1C5BW0);
            float y = Float.intBitsToFloat(bits$iv$iv$iv$iv2);
            Path it = this.clipPathInOverlay;
            if (it == null) {
                drawScope.getDrawContext().getTransform().translate(x, y);
                try {
                    GraphicsLayerKt.drawLayer(drawScope, layer);
                    return;
                } finally {
                }
            }
            int iM5302getIntersectrtfAjoo = ClipOp.INSTANCE.m5302getIntersectrtfAjoo();
            DrawContext $this$withTransform_u24lambda_u240$iv$iv = drawScope.getDrawContext();
            long previousSize$iv$iv = $this$withTransform_u24lambda_u240$iv$iv.mo5808getSizeNHjbRc();
            $this$withTransform_u24lambda_u240$iv$iv.getCanvas().save();
            try {
                DrawTransform $this$clipPath_KD09W0M_u24lambda_u240$iv = $this$withTransform_u24lambda_u240$iv$iv.getTransform();
                $this$clipPath_KD09W0M_u24lambda_u240$iv.mo5810clipPathmtrdDE(it, iM5302getIntersectrtfAjoo);
                try {
                    drawScope.getDrawContext().getTransform().translate(x, y);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    try {
                        GraphicsLayerKt.drawLayer(drawScope, layer);
                        drawScope.getDrawContext().getTransform().translate(-x, -y);
                        $this$withTransform_u24lambda_u240$iv$iv.getCanvas().restore();
                        $this$withTransform_u24lambda_u240$iv$iv.mo5809setSizeuvyYCjk(previousSize$iv$iv);
                    } finally {
                    }
                } catch (Throwable th2) {
                    th = th2;
                    $this$withTransform_u24lambda_u240$iv$iv.getCanvas().restore();
                    $this$withTransform_u24lambda_u240$iv$iv.mo5809setSizeuvyYCjk(previousSize$iv$iv);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
            }
        }
    }

    @Override // androidx.compose.animation.LayerRenderer
    public SharedElementEntry getParentState() {
        return this.parentState;
    }

    public void setParentState(SharedElementEntry sharedElementEntry) {
        this.parentState = sharedElementEntry;
    }

    public final boolean getTarget() {
        return getBoundsAnimation().getTarget();
    }

    public final BoundsProvider getBoundsProvider() {
        return this.boundsProvider;
    }

    public final void setBoundsProvider(BoundsProvider boundsProvider) {
        this.boundsProvider = boundsProvider;
    }

    public final GraphicsLayer getLayer() {
        State $this$getValue$iv = this.layer;
        return (GraphicsLayer) $this$getValue$iv.getValue();
    }

    public final void setLayer(GraphicsLayer graphicsLayer) {
        MutableState $this$setValue$iv = this.layer;
        $this$setValue$iv.setValue(graphicsLayer);
    }

    private final boolean getShouldRenderAtAll() {
        return getBoundsAnimation().getTarget() || (getSharedElement().getState$animation().getMatchIsOrHasBeenConfigured() && !getSharedElement().getState$animation().getActiveMatchFound()) || !getRenderOnlyWhenVisible();
    }

    public final boolean getShouldRenderInOverlay$animation() {
        return getShouldRenderAtAll() && getSharedElement().getBoundsTransformIsActive() && isEnabled() && getRenderInOverlayDuringTransition() && getSharedElement().getScope().isTransitionActive();
    }

    public final boolean getShouldRenderInPlace() {
        return !getSharedElement().getBoundsTransformIsActive() || (!getShouldRenderInOverlay$animation() && getShouldRenderAtAll());
    }

    @Override // androidx.compose.runtime.RememberObserver
    public void onRemembered() throws Throwable {
        getSharedElement().getScope().onEntryAdded$animation(this);
        getSharedElement().invalidateTargetBoundsProvider();
    }

    @Override // androidx.compose.runtime.RememberObserver
    public void onForgotten() {
        getSharedElement().getScope().onEntryRemoved$animation(this);
        getSharedElement().invalidateTargetBoundsProvider();
    }

    @Override // androidx.compose.runtime.RememberObserver
    public void onAbandoned() {
    }
}
