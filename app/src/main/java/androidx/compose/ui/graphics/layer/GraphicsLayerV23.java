package androidx.compose.ui.graphics.layer;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.os.Build;
import android.view.DisplayListCanvas;
import android.view.RenderNode;
import android.view.View;
import androidx.compose.ui.geometry.InlineClassHelperKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.graphics.AndroidBlendMode_androidKt;
import androidx.compose.ui.graphics.AndroidCanvas_androidKt;
import androidx.compose.ui.graphics.AndroidColorFilter_androidKt;
import androidx.compose.ui.graphics.BlendMode;
import androidx.compose.ui.graphics.CanvasHolder;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.RenderEffect;
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawContext;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.IntSizeKt;
import androidx.compose.ui.unit.LayoutDirection;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: GraphicsLayerV23.android.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000¾\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u001b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\b\u0001\u0018\u0000 \u0092\u00012\u00020\u0001:\u0002\u0092\u0001B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\b\u0010\u001a\u001a\u00020\u0014H\u0002J\u0017\u0010#\u001a\u00020$2\u0006\u0010\u001d\u001a\u00020\u001cH\u0002¢\u0006\u0004\b%\u0010!J\b\u0010*\u001a\u00020\u0018H\u0002J\b\u0010+\u001a\u00020$H\u0002J\b\u0010g\u001a\u00020$H\u0002J'\u0010n\u001a\u00020$2\u0006\u0010o\u001a\u00020p2\u0006\u0010q\u001a\u00020p2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016¢\u0006\u0004\br\u0010sJ!\u0010t\u001a\u00020$2\b\u0010u\u001a\u0004\u0018\u00010v2\u0006\u0010\u0019\u001a\u00020\u0011H\u0016¢\u0006\u0004\bw\u0010xJA\u0010}\u001a\u00020$2\u0006\u0010~\u001a\u00020\u007f2\b\u0010\u0080\u0001\u001a\u00030\u0081\u00012\b\u0010\u0082\u0001\u001a\u00030\u0083\u00012\u001b\u0010\u0084\u0001\u001a\u0016\u0012\u0005\u0012\u00030\u0086\u0001\u0012\u0004\u0012\u00020$0\u0085\u0001¢\u0006\u0003\b\u0087\u0001H\u0016J\u0013\u0010\u0088\u0001\u001a\u00020$2\b\u0010\u0089\u0001\u001a\u00030\u008a\u0001H\u0016J\t\u0010\u008b\u0001\u001a\u00020\u0016H\u0016J\t\u0010\u008c\u0001\u001a\u00020$H\u0016J\u0011\u0010\u008f\u0001\u001a\u00020$2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\u000f\u0010\u0090\u0001\u001a\u00020$H\u0000¢\u0006\u0003\b\u0091\u0001R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0012R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0012R&\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u001b\u001a\u00020\u001c@VX\u0096\u000e¢\u0006\u0010\n\u0002\u0010\"\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R&\u0010'\u001a\u00020&2\u0006\u0010\u001b\u001a\u00020&@VX\u0096\u000e¢\u0006\u0010\n\u0002\u0010\"\u001a\u0004\b(\u0010\u001f\"\u0004\b)\u0010!R(\u0010-\u001a\u0004\u0018\u00010,2\b\u0010\u001b\u001a\u0004\u0018\u00010,@VX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010/\"\u0004\b0\u00101R$\u00103\u001a\u0002022\u0006\u0010\u001b\u001a\u000202@VX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u00105\"\u0004\b6\u00107R\u000e\u00108\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R&\u0010:\u001a\u0002092\u0006\u0010\u001b\u001a\u000209@VX\u0096\u000e¢\u0006\u0010\n\u0002\u0010\u0012\u001a\u0004\b;\u0010\r\"\u0004\b<\u0010=R$\u0010>\u001a\u0002022\u0006\u0010\u001b\u001a\u000202@VX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b?\u00105\"\u0004\b@\u00107R$\u0010A\u001a\u0002022\u0006\u0010\u001b\u001a\u000202@VX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bB\u00105\"\u0004\bC\u00107R$\u0010D\u001a\u0002022\u0006\u0010\u001b\u001a\u000202@VX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bE\u00105\"\u0004\bF\u00107R$\u0010G\u001a\u0002022\u0006\u0010\u001b\u001a\u000202@VX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bH\u00105\"\u0004\bI\u00107R$\u0010J\u001a\u0002022\u0006\u0010\u001b\u001a\u000202@VX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bK\u00105\"\u0004\bL\u00107R&\u0010N\u001a\u00020M2\u0006\u0010\u001b\u001a\u00020M@VX\u0096\u000e¢\u0006\u0010\n\u0002\u0010\u0012\u001a\u0004\bO\u0010\r\"\u0004\bP\u0010=R&\u0010Q\u001a\u00020M2\u0006\u0010\u001b\u001a\u00020M@VX\u0096\u000e¢\u0006\u0010\n\u0002\u0010\u0012\u001a\u0004\bR\u0010\r\"\u0004\bS\u0010=R$\u0010T\u001a\u0002022\u0006\u0010\u001b\u001a\u000202@VX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bU\u00105\"\u0004\bV\u00107R$\u0010W\u001a\u0002022\u0006\u0010\u001b\u001a\u000202@VX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bX\u00105\"\u0004\bY\u00107R$\u0010Z\u001a\u0002022\u0006\u0010\u001b\u001a\u000202@VX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b[\u00105\"\u0004\b\\\u00107R$\u0010]\u001a\u0002022\u0006\u0010\u001b\u001a\u000202@VX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b^\u00105\"\u0004\b_\u00107R$\u0010`\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u0018@VX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\ba\u0010b\"\u0004\bc\u0010dR\u000e\u0010e\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010f\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010h\u001a\u0004\u0018\u00010iX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bj\u0010k\"\u0004\bl\u0010mR\u001a\u0010y\u001a\u00020\u0018X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\by\u0010b\"\u0004\bz\u0010dR\u0014\u0010{\u001a\u00020\u00188VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b|\u0010bR\u0016\u0010\u008d\u0001\u001a\u00020\u0005X\u0096D¢\u0006\t\n\u0000\u001a\u0005\b\u008e\u0001\u0010\r¨\u0006\u0093\u0001"}, d2 = {"Landroidx/compose/ui/graphics/layer/GraphicsLayerV23;", "Landroidx/compose/ui/graphics/layer/GraphicsLayerImpl;", "ownerView", "Landroid/view/View;", "ownerId", "", "canvasHolder", "Landroidx/compose/ui/graphics/CanvasHolder;", "canvasDrawScope", "Landroidx/compose/ui/graphics/drawscope/CanvasDrawScope;", "<init>", "(Landroid/view/View;JLandroidx/compose/ui/graphics/CanvasHolder;Landroidx/compose/ui/graphics/drawscope/CanvasDrawScope;)V", "getOwnerId", "()J", "renderNode", "Landroid/view/RenderNode;", "size", "Landroidx/compose/ui/unit/IntSize;", "J", "layerPaint", "Landroid/graphics/Paint;", "matrix", "Landroid/graphics/Matrix;", "outlineIsProvided", "", "outlineSize", "obtainLayerPaint", "value", "Landroidx/compose/ui/graphics/layer/CompositingStrategy;", "compositingStrategy", "getCompositingStrategy-ke2Ky5w", "()I", "setCompositingStrategy-Wpw9cng", "(I)V", "I", "applyCompositingStrategy", "", "applyCompositingStrategy-Wpw9cng", "Landroidx/compose/ui/graphics/BlendMode;", "blendMode", "getBlendMode-0nO6VwU", "setBlendMode-s9anfk8", "requiresCompositingLayer", "updateLayerProperties", "Landroidx/compose/ui/graphics/ColorFilter;", "colorFilter", "getColorFilter", "()Landroidx/compose/ui/graphics/ColorFilter;", "setColorFilter", "(Landroidx/compose/ui/graphics/ColorFilter;)V", "", "alpha", "getAlpha", "()F", "setAlpha", "(F)V", "shouldManuallySetCenterPivot", "Landroidx/compose/ui/geometry/Offset;", "pivotOffset", "getPivotOffset-F1C5BW0", "setPivotOffset-k-4lQ0M", "(J)V", "scaleX", "getScaleX", "setScaleX", "scaleY", "getScaleY", "setScaleY", "translationX", "getTranslationX", "setTranslationX", "translationY", "getTranslationY", "setTranslationY", "shadowElevation", "getShadowElevation", "setShadowElevation", "Landroidx/compose/ui/graphics/Color;", "ambientShadowColor", "getAmbientShadowColor-0d7_KjU", "setAmbientShadowColor-8_81llA", "spotShadowColor", "getSpotShadowColor-0d7_KjU", "setSpotShadowColor-8_81llA", "rotationX", "getRotationX", "setRotationX", "rotationY", "getRotationY", "setRotationY", "rotationZ", "getRotationZ", "setRotationZ", "cameraDistance", "getCameraDistance", "setCameraDistance", "clip", "getClip", "()Z", "setClip", "(Z)V", "clipToBounds", "clipToOutline", "applyClip", "renderEffect", "Landroidx/compose/ui/graphics/RenderEffect;", "getRenderEffect", "()Landroidx/compose/ui/graphics/RenderEffect;", "setRenderEffect", "(Landroidx/compose/ui/graphics/RenderEffect;)V", "setPosition", "x", "", "y", "setPosition-H0pRuoY", "(IIJ)V", "setOutline", "outline", "Landroid/graphics/Outline;", "setOutline-O0kMr_c", "(Landroid/graphics/Outline;J)V", "isInvalidated", "setInvalidated", "hasDisplayList", "getHasDisplayList", "record", "density", "Landroidx/compose/ui/unit/Density;", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "layer", "Landroidx/compose/ui/graphics/layer/GraphicsLayer;", "block", "Lkotlin/Function1;", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "Lkotlin/ExtensionFunctionType;", "draw", "canvas", "Landroidx/compose/ui/graphics/Canvas;", "calculateMatrix", "discardDisplayList", "layerId", "getLayerId", "verifyShadowColorProperties", "discardDisplayListInternal", "discardDisplayListInternal$ui_graphics", "Companion", "ui-graphics"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class GraphicsLayerV23 implements GraphicsLayerImpl {
    private static boolean testFailCreateRenderNode;
    private float alpha;
    private long ambientShadowColor;
    private int blendMode;
    private float cameraDistance;
    private final CanvasDrawScope canvasDrawScope;
    private final CanvasHolder canvasHolder;
    private boolean clip;
    private boolean clipToBounds;
    private boolean clipToOutline;
    private ColorFilter colorFilter;
    private int compositingStrategy;
    private boolean isInvalidated;
    private final long layerId;
    private Paint layerPaint;
    private Matrix matrix;
    private boolean outlineIsProvided;
    private long outlineSize;
    private final long ownerId;
    private long pivotOffset;
    private RenderEffect renderEffect;
    private final RenderNode renderNode;
    private float rotationX;
    private float rotationY;
    private float rotationZ;
    private float scaleX;
    private float scaleY;
    private float shadowElevation;
    private boolean shouldManuallySetCenterPivot;
    private long size;
    private long spotShadowColor;
    private float translationX;
    private float translationY;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;
    private static final AtomicBoolean needToValidateAccess = new AtomicBoolean(true);

    public GraphicsLayerV23(View ownerView, long ownerId, CanvasHolder canvasHolder, CanvasDrawScope canvasDrawScope) {
        this.ownerId = ownerId;
        this.canvasHolder = canvasHolder;
        this.canvasDrawScope = canvasDrawScope;
        this.renderNode = RenderNode.create("Compose", ownerView);
        this.size = IntSize.INSTANCE.m8326getZeroYbymL2g();
        this.outlineSize = IntSize.INSTANCE.m8326getZeroYbymL2g();
        if (needToValidateAccess.getAndSet(false)) {
            this.renderNode.setScaleX(this.renderNode.getScaleX());
            this.renderNode.setScaleY(this.renderNode.getScaleY());
            this.renderNode.setTranslationX(this.renderNode.getTranslationX());
            this.renderNode.setTranslationY(this.renderNode.getTranslationY());
            this.renderNode.setElevation(this.renderNode.getElevation());
            this.renderNode.setRotation(this.renderNode.getRotation());
            this.renderNode.setRotationX(this.renderNode.getRotationX());
            this.renderNode.setRotationY(this.renderNode.getRotationY());
            this.renderNode.setCameraDistance(this.renderNode.getCameraDistance());
            this.renderNode.setPivotX(this.renderNode.getPivotX());
            this.renderNode.setPivotY(this.renderNode.getPivotY());
            this.renderNode.setClipToOutline(this.renderNode.getClipToOutline());
            this.renderNode.setClipToBounds(false);
            this.renderNode.setAlpha(this.renderNode.getAlpha());
            this.renderNode.isValid();
            this.renderNode.setLeftTopRightBottom(0, 0, 0, 0);
            this.renderNode.offsetLeftAndRight(0);
            this.renderNode.offsetTopAndBottom(0);
            verifyShadowColorProperties(this.renderNode);
            discardDisplayListInternal$ui_graphics();
            this.renderNode.setLayerType(0);
            this.renderNode.setHasOverlappingRendering(this.renderNode.hasOverlappingRendering());
        }
        if (testFailCreateRenderNode) {
            throw new NoClassDefFoundError();
        }
        this.renderNode.setClipToBounds(false);
        m6002applyCompositingStrategyWpw9cng(CompositingStrategy.INSTANCE.m5967getAutoke2Ky5w());
        this.compositingStrategy = CompositingStrategy.INSTANCE.m5967getAutoke2Ky5w();
        this.blendMode = BlendMode.INSTANCE.m5253getSrcOver0nO6VwU();
        this.alpha = 1.0f;
        this.pivotOffset = Offset.INSTANCE.m5083getUnspecifiedF1C5BW0();
        this.scaleX = 1.0f;
        this.scaleY = 1.0f;
        this.ambientShadowColor = Color.INSTANCE.m5339getBlack0d7_KjU();
        this.spotShadowColor = Color.INSTANCE.m5339getBlack0d7_KjU();
        this.cameraDistance = 8.0f;
        this.isInvalidated = true;
    }

    public /* synthetic */ GraphicsLayerV23(View view, long j, CanvasHolder canvasHolder, CanvasDrawScope canvasDrawScope, int i, DefaultConstructorMarker defaultConstructorMarker) {
        CanvasHolder canvasHolder2;
        CanvasDrawScope canvasDrawScope2;
        if ((i & 4) == 0) {
            canvasHolder2 = canvasHolder;
        } else {
            canvasHolder2 = new CanvasHolder();
        }
        if ((i & 8) == 0) {
            canvasDrawScope2 = canvasDrawScope;
        } else {
            canvasDrawScope2 = new CanvasDrawScope();
        }
        this(view, j, canvasHolder2, canvasDrawScope2);
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public long getOwnerId() {
        return this.ownerId;
    }

    private final Paint obtainLayerPaint() {
        Paint paint = this.layerPaint;
        if (paint != null) {
            return paint;
        }
        Paint it = new Paint();
        this.layerPaint = it;
        return it;
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    /* JADX INFO: renamed from: getCompositingStrategy-ke2Ky5w, reason: from getter */
    public int getCompositingStrategy() {
        return this.compositingStrategy;
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    /* JADX INFO: renamed from: setCompositingStrategy-Wpw9cng */
    public void mo5997setCompositingStrategyWpw9cng(int value) {
        this.compositingStrategy = value;
        updateLayerProperties();
    }

    /* JADX INFO: renamed from: applyCompositingStrategy-Wpw9cng */
    private final void m6002applyCompositingStrategyWpw9cng(int compositingStrategy) {
        RenderNode $this$applyCompositingStrategy_Wpw9cng_u24lambda_u240 = this.renderNode;
        if (CompositingStrategy.m5963equalsimpl0(compositingStrategy, CompositingStrategy.INSTANCE.m5969getOffscreenke2Ky5w())) {
            $this$applyCompositingStrategy_Wpw9cng_u24lambda_u240.setLayerType(2);
            $this$applyCompositingStrategy_Wpw9cng_u24lambda_u240.setLayerPaint(this.layerPaint);
            $this$applyCompositingStrategy_Wpw9cng_u24lambda_u240.setHasOverlappingRendering(true);
        } else if (CompositingStrategy.m5963equalsimpl0(compositingStrategy, CompositingStrategy.INSTANCE.m5968getModulateAlphake2Ky5w())) {
            $this$applyCompositingStrategy_Wpw9cng_u24lambda_u240.setLayerType(0);
            $this$applyCompositingStrategy_Wpw9cng_u24lambda_u240.setLayerPaint(this.layerPaint);
            $this$applyCompositingStrategy_Wpw9cng_u24lambda_u240.setHasOverlappingRendering(false);
        } else {
            $this$applyCompositingStrategy_Wpw9cng_u24lambda_u240.setLayerType(0);
            $this$applyCompositingStrategy_Wpw9cng_u24lambda_u240.setLayerPaint(this.layerPaint);
            $this$applyCompositingStrategy_Wpw9cng_u24lambda_u240.setHasOverlappingRendering(true);
        }
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    /* JADX INFO: renamed from: getBlendMode-0nO6VwU, reason: from getter */
    public int getBlendMode() {
        return this.blendMode;
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    /* JADX INFO: renamed from: setBlendMode-s9anfk8 */
    public void mo5996setBlendModes9anfk8(int value) {
        if (!BlendMode.m5222equalsimpl0(this.blendMode, value)) {
            this.blendMode = value;
            Paint $this$setBlendMode_s9anfk8_u24lambda_u240 = obtainLayerPaint();
            $this$setBlendMode_s9anfk8_u24lambda_u240.setXfermode(new PorterDuffXfermode(AndroidBlendMode_androidKt.m5161toPorterDuffModes9anfk8(value)));
            updateLayerProperties();
        }
    }

    private final boolean requiresCompositingLayer() {
        return (!CompositingStrategy.m5963equalsimpl0(getCompositingStrategy(), CompositingStrategy.INSTANCE.m5969getOffscreenke2Ky5w()) && BlendMode.m5222equalsimpl0(getBlendMode(), BlendMode.INSTANCE.m5253getSrcOver0nO6VwU()) && getColorFilter() == null) ? false : true;
    }

    private final void updateLayerProperties() {
        if (requiresCompositingLayer()) {
            m6002applyCompositingStrategyWpw9cng(CompositingStrategy.INSTANCE.m5969getOffscreenke2Ky5w());
        } else {
            m6002applyCompositingStrategyWpw9cng(getCompositingStrategy());
        }
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public ColorFilter getColorFilter() {
        return this.colorFilter;
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public void setColorFilter(ColorFilter value) {
        this.colorFilter = value;
        if (value != null) {
            m6002applyCompositingStrategyWpw9cng(CompositingStrategy.INSTANCE.m5969getOffscreenke2Ky5w());
            RenderNode renderNode = this.renderNode;
            Paint $this$_set_colorFilter__u24lambda_u240 = obtainLayerPaint();
            $this$_set_colorFilter__u24lambda_u240.setColorFilter(AndroidColorFilter_androidKt.asAndroidColorFilter(value));
            renderNode.setLayerPaint($this$_set_colorFilter__u24lambda_u240);
            return;
        }
        updateLayerProperties();
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public float getAlpha() {
        return this.alpha;
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public void setAlpha(float value) {
        this.alpha = value;
        this.renderNode.setAlpha(value);
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    /* JADX INFO: renamed from: getPivotOffset-F1C5BW0, reason: from getter */
    public long getPivotOffset() {
        return this.pivotOffset;
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    /* JADX INFO: renamed from: setPivotOffset-k-4lQ0M */
    public void mo5999setPivotOffsetk4lQ0M(long value) {
        this.pivotOffset = value;
        if ((9223372034707292159L & value) == InlineClassHelperKt.UnspecifiedPackedFloats) {
            this.shouldManuallySetCenterPivot = true;
            RenderNode renderNode = this.renderNode;
            long arg0$iv = this.size;
            renderNode.setPivotX(((int) (arg0$iv >> 32)) / 2.0f);
            RenderNode renderNode2 = this.renderNode;
            long arg0$iv2 = this.size;
            renderNode2.setPivotY(((int) (4294967295L & arg0$iv2)) / 2.0f);
            return;
        }
        this.shouldManuallySetCenterPivot = false;
        int bits$iv$iv$iv = (int) (value >> 32);
        this.renderNode.setPivotX(Float.intBitsToFloat(bits$iv$iv$iv));
        int bits$iv$iv$iv2 = (int) (4294967295L & value);
        this.renderNode.setPivotY(Float.intBitsToFloat(bits$iv$iv$iv2));
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public float getScaleX() {
        return this.scaleX;
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public void setScaleX(float value) {
        this.scaleX = value;
        this.renderNode.setScaleX(value);
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public float getScaleY() {
        return this.scaleY;
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public void setScaleY(float value) {
        this.scaleY = value;
        this.renderNode.setScaleY(value);
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public float getTranslationX() {
        return this.translationX;
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public void setTranslationX(float value) {
        this.translationX = value;
        this.renderNode.setTranslationX(value);
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public float getTranslationY() {
        return this.translationY;
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public void setTranslationY(float value) {
        this.translationY = value;
        this.renderNode.setTranslationY(value);
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public float getShadowElevation() {
        return this.shadowElevation;
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public void setShadowElevation(float value) {
        this.shadowElevation = value;
        this.renderNode.setElevation(value);
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    /* JADX INFO: renamed from: getAmbientShadowColor-0d7_KjU, reason: from getter */
    public long getAmbientShadowColor() {
        return this.ambientShadowColor;
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    /* JADX INFO: renamed from: setAmbientShadowColor-8_81llA */
    public void mo5995setAmbientShadowColor8_81llA(long value) {
        if (Build.VERSION.SDK_INT >= 28) {
            this.ambientShadowColor = value;
            RenderNodeVerificationHelper28.INSTANCE.setAmbientShadowColor(this.renderNode, ColorKt.m5367toArgb8_81llA(value));
        }
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    /* JADX INFO: renamed from: getSpotShadowColor-0d7_KjU, reason: from getter */
    public long getSpotShadowColor() {
        return this.spotShadowColor;
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    /* JADX INFO: renamed from: setSpotShadowColor-8_81llA */
    public void mo6001setSpotShadowColor8_81llA(long value) {
        if (Build.VERSION.SDK_INT >= 28) {
            this.spotShadowColor = value;
            RenderNodeVerificationHelper28.INSTANCE.setSpotShadowColor(this.renderNode, ColorKt.m5367toArgb8_81llA(value));
        }
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public float getRotationX() {
        return this.rotationX;
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public void setRotationX(float value) {
        this.rotationX = value;
        this.renderNode.setRotationX(value);
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public float getRotationY() {
        return this.rotationY;
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public void setRotationY(float value) {
        this.rotationY = value;
        this.renderNode.setRotationY(value);
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public float getRotationZ() {
        return this.rotationZ;
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public void setRotationZ(float value) {
        this.rotationZ = value;
        this.renderNode.setRotation(value);
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public float getCameraDistance() {
        return this.cameraDistance;
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public void setCameraDistance(float value) {
        this.cameraDistance = value;
        this.renderNode.setCameraDistance(-value);
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public boolean getClip() {
        return this.clip;
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public void setClip(boolean value) {
        this.clip = value;
        applyClip();
    }

    private final void applyClip() {
        boolean newClipToBounds = getClip() && !this.outlineIsProvided;
        boolean newClipToOutline = getClip() && this.outlineIsProvided;
        if (newClipToBounds != this.clipToBounds) {
            this.clipToBounds = newClipToBounds;
            this.renderNode.setClipToBounds(this.clipToBounds);
        }
        if (newClipToOutline != this.clipToOutline) {
            this.clipToOutline = newClipToOutline;
            this.renderNode.setClipToOutline(newClipToOutline);
        }
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public RenderEffect getRenderEffect() {
        return this.renderEffect;
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public void setRenderEffect(RenderEffect renderEffect) {
        this.renderEffect = renderEffect;
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    /* JADX INFO: renamed from: setPosition-H0pRuoY */
    public void mo6000setPositionH0pRuoY(int x, int y, long size) {
        this.renderNode.setLeftTopRightBottom(x, y, x + ((int) (size >> 32)), y + ((int) (size & 4294967295L)));
        if (!IntSize.m8319equalsimpl0(this.size, size)) {
            if (this.shouldManuallySetCenterPivot) {
                this.renderNode.setPivotX(((int) (size >> 32)) / 2.0f);
                this.renderNode.setPivotY(((int) (size & 4294967295L)) / 2.0f);
            }
            this.size = size;
        }
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    /* JADX INFO: renamed from: setOutline-O0kMr_c */
    public void mo5998setOutlineO0kMr_c(Outline outline, long outlineSize) {
        this.outlineSize = outlineSize;
        this.renderNode.setOutline(outline);
        this.outlineIsProvided = outline != null;
        applyClip();
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    /* JADX INFO: renamed from: isInvalidated, reason: from getter */
    public boolean getIsInvalidated() {
        return this.isInvalidated;
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public void setInvalidated(boolean z) {
        this.isInvalidated = z;
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public boolean getHasDisplayList() {
        return this.renderNode.isValid();
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public void record(Density density, LayoutDirection layoutDirection, GraphicsLayer layer, Function1<? super DrawScope, Unit> block) {
        RenderNode renderNode = this.renderNode;
        long arg0$iv = this.size;
        int i = (int) (arg0$iv >> 32);
        long arg0$iv2 = this.outlineSize;
        int iMax = Math.max(i, (int) (arg0$iv2 >> 32));
        long arg0$iv3 = this.size;
        int i2 = (int) (arg0$iv3 & 4294967295L);
        long arg0$iv4 = this.outlineSize;
        Canvas canvasStart = renderNode.start(iMax, Math.max(i2, (int) (4294967295L & arg0$iv4)));
        try {
            CanvasHolder this_$iv = this.canvasHolder;
            Canvas targetCanvas$iv = canvasStart;
            Canvas previousCanvas$iv = this_$iv.getAndroidCanvas().getInternalCanvas();
            this_$iv.getAndroidCanvas().setInternalCanvas(targetCanvas$iv);
            androidx.compose.ui.graphics.Canvas $this$record_u24lambda_u240 = this_$iv.getAndroidCanvas();
            DrawScope $this$draw_u2dymL40Pk$iv = this.canvasDrawScope;
            long size$iv = IntSizeKt.m8333toSizeozmzZPI(this.size);
            Density prevDensity$iv = $this$draw_u2dymL40Pk$iv.getDrawContext().getDensity();
            LayoutDirection prevLayoutDirection$iv = $this$draw_u2dymL40Pk$iv.getDrawContext().getLayoutDirection();
            androidx.compose.ui.graphics.Canvas prevCanvas$iv = $this$draw_u2dymL40Pk$iv.getDrawContext().getCanvas();
            long prevSize$iv = $this$draw_u2dymL40Pk$iv.getDrawContext().mo5808getSizeNHjbRc();
            GraphicsLayer prevLayer$iv = $this$draw_u2dymL40Pk$iv.getDrawContext().getGraphicsLayer();
            DrawContext $this$draw_ymL40Pk_u24lambda_u240$iv = $this$draw_u2dymL40Pk$iv.getDrawContext();
            $this$draw_ymL40Pk_u24lambda_u240$iv.setDensity(density);
            $this$draw_ymL40Pk_u24lambda_u240$iv.setLayoutDirection(layoutDirection);
            $this$draw_ymL40Pk_u24lambda_u240$iv.setCanvas($this$record_u24lambda_u240);
            $this$draw_ymL40Pk_u24lambda_u240$iv.mo5809setSizeuvyYCjk(size$iv);
            $this$draw_ymL40Pk_u24lambda_u240$iv.setGraphicsLayer(layer);
            $this$record_u24lambda_u240.save();
            try {
                block.invoke($this$draw_u2dymL40Pk$iv);
                $this$record_u24lambda_u240.restore();
                DrawContext $this$draw_ymL40Pk_u24lambda_u241$iv = $this$draw_u2dymL40Pk$iv.getDrawContext();
                $this$draw_ymL40Pk_u24lambda_u241$iv.setDensity(prevDensity$iv);
                $this$draw_ymL40Pk_u24lambda_u241$iv.setLayoutDirection(prevLayoutDirection$iv);
                $this$draw_ymL40Pk_u24lambda_u241$iv.setCanvas(prevCanvas$iv);
                $this$draw_ymL40Pk_u24lambda_u241$iv.mo5809setSizeuvyYCjk(prevSize$iv);
                $this$draw_ymL40Pk_u24lambda_u241$iv.setGraphicsLayer(prevLayer$iv);
                this_$iv.getAndroidCanvas().setInternalCanvas(previousCanvas$iv);
                this.renderNode.end(canvasStart);
                setInvalidated(false);
            } catch (Throwable th) {
                $this$record_u24lambda_u240.restore();
                DrawContext $this$draw_ymL40Pk_u24lambda_u241$iv2 = $this$draw_u2dymL40Pk$iv.getDrawContext();
                $this$draw_ymL40Pk_u24lambda_u241$iv2.setDensity(prevDensity$iv);
                $this$draw_ymL40Pk_u24lambda_u241$iv2.setLayoutDirection(prevLayoutDirection$iv);
                $this$draw_ymL40Pk_u24lambda_u241$iv2.setCanvas(prevCanvas$iv);
                $this$draw_ymL40Pk_u24lambda_u241$iv2.mo5809setSizeuvyYCjk(prevSize$iv);
                $this$draw_ymL40Pk_u24lambda_u241$iv2.setGraphicsLayer(prevLayer$iv);
                throw th;
            }
        } catch (Throwable th2) {
            this.renderNode.end(canvasStart);
            throw th2;
        }
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public void draw(androidx.compose.ui.graphics.Canvas canvas) {
        DisplayListCanvas nativeCanvas = AndroidCanvas_androidKt.getNativeCanvas(canvas);
        Intrinsics.checkNotNull(nativeCanvas, "null cannot be cast to non-null type android.view.DisplayListCanvas");
        nativeCanvas.drawRenderNode(this.renderNode);
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public Matrix calculateMatrix() {
        Matrix it = this.matrix;
        if (it == null) {
            it = new Matrix();
            this.matrix = it;
        }
        this.renderNode.getMatrix(it);
        return it;
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public void discardDisplayList() {
        discardDisplayListInternal$ui_graphics();
    }

    @Override // androidx.compose.ui.graphics.layer.GraphicsLayerImpl
    public long getLayerId() {
        return this.layerId;
    }

    private final void verifyShadowColorProperties(RenderNode renderNode) {
        if (Build.VERSION.SDK_INT >= 28) {
            RenderNodeVerificationHelper28.INSTANCE.setAmbientShadowColor(renderNode, RenderNodeVerificationHelper28.INSTANCE.getAmbientShadowColor(renderNode));
            RenderNodeVerificationHelper28.INSTANCE.setSpotShadowColor(renderNode, RenderNodeVerificationHelper28.INSTANCE.getSpotShadowColor(renderNode));
        }
    }

    public final void discardDisplayListInternal$ui_graphics() {
        RenderNodeVerificationHelper24.INSTANCE.discardDisplayList(this.renderNode);
    }

    /* JADX INFO: compiled from: GraphicsLayerV23.android.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001a\u0010\u0004\u001a\u00020\u0005X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Landroidx/compose/ui/graphics/layer/GraphicsLayerV23$Companion;", "", "<init>", "()V", "testFailCreateRenderNode", "", "getTestFailCreateRenderNode$ui_graphics", "()Z", "setTestFailCreateRenderNode$ui_graphics", "(Z)V", "needToValidateAccess", "Ljava/util/concurrent/atomic/AtomicBoolean;", "ui-graphics"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final boolean getTestFailCreateRenderNode$ui_graphics() {
            return GraphicsLayerV23.testFailCreateRenderNode;
        }

        public final void setTestFailCreateRenderNode$ui_graphics(boolean z) {
            GraphicsLayerV23.testFailCreateRenderNode = z;
        }
    }
}
