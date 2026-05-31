package androidx.compose.ui.graphics.layer;

import android.graphics.Canvas;
import android.graphics.Outline;
import android.graphics.RectF;
import android.os.Build;
import androidx.compose.ui.geometry.CornerRadius;
import androidx.compose.ui.geometry.InlineClassHelperKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.RoundRectKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.AndroidCanvas_androidKt;
import androidx.compose.ui.graphics.AndroidPaint_androidKt;
import androidx.compose.ui.graphics.AndroidPath;
import androidx.compose.ui.graphics.BlendMode;
import androidx.compose.ui.graphics.ClipOp;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.Outline;
import androidx.compose.ui.graphics.Paint;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.RenderEffect;
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawContext;
import androidx.compose.ui.graphics.drawscope.DrawContextKt;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.DrawTransform;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.IntSizeKt;
import androidx.compose.ui.unit.LayoutDirection;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AndroidGraphicsLayer.android.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000â\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b)\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\t\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 »\u00012\u00020\u0001:\u0002»\u0001B\u0011\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001f\u0010w\u001a\u00020\u000f2\u0006\u00105\u001a\u0002042\u0006\u0010;\u001a\u00020:H\u0002¢\u0006\u0004\bx\u0010yJ>\u0010z\u001a\u00020\u000f2\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010;\u001a\u00020:2\u0017\u0010{\u001a\u0013\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\r¢\u0006\u0002\b\u0010¢\u0006\u0004\b|\u0010}J\b\u0010~\u001a\u00020\u000fH\u0002J\f\u0010\u007f\u001a\u00020\u000f*\u00020\u000eH\u0002J\u0012\u0010\u0080\u0001\u001a\u00020\u000f2\u0007\u0010\u0081\u0001\u001a\u00020\u0000H\u0002J\u0013\u0010\u0082\u0001\u001a\u00020\u000f2\b\u0010\u0083\u0001\u001a\u00030\u0084\u0001H\u0002J\u0019\u0010\u0085\u0001\u001a\u00020\u000f2\b\u0010\u0086\u0001\u001a\u00030\u0087\u0001H\u0000¢\u0006\u0003\b\u0088\u0001J\t\u0010\u0089\u0001\u001a\u00020\u000fH\u0002J$\u0010\u008a\u0001\u001a\u00020\u000f2\b\u0010\u0086\u0001\u001a\u00030\u0087\u00012\t\u0010\u008b\u0001\u001a\u0004\u0018\u00010\u0000H\u0000¢\u0006\u0003\b\u008c\u0001J\t\u0010\u008d\u0001\u001a\u00020\u000fH\u0002J\t\u0010\u008e\u0001\u001a\u00020\u000fH\u0002J\n\u0010\u0091\u0001\u001a\u00030\u0090\u0001H\u0002J\t\u0010\u0092\u0001\u001a\u00020\u000fH\u0002J4\u0010\u0093\u0001\u001a\u0003H\u0094\u0001\"\u0005\b\u0000\u0010\u0094\u00012\u001a\u0010{\u001a\u0016\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u001a\u0012\u0005\u0012\u0003H\u0094\u00010\u0095\u0001H\u0082\b¢\u0006\u0003\u0010\u0096\u0001J\u0014\u0010\u0097\u0001\u001a\u0004\u0018\u00010\u00132\u0007\u0010\u0098\u0001\u001a\u00020 H\u0002J\t\u0010\u0099\u0001\u001a\u00020\u0013H\u0002J\u000f\u0010\u009a\u0001\u001a\u00020\u000fH\u0000¢\u0006\u0003\b\u009b\u0001J\t\u0010\u009c\u0001\u001a\u00020\u000fH\u0002J\u000f\u0010\u009d\u0001\u001a\u00020\u000fH\u0000¢\u0006\u0003\b\u009e\u0001J\u000f\u0010\u009f\u0001\u001a\u00020\u000fH\u0001¢\u0006\u0003\b \u0001J\t\u0010©\u0001\u001a\u00020\u000fH\u0002J\u0010\u0010ª\u0001\u001a\u00020\u000f2\u0007\u0010\u0098\u0001\u001a\u00020 J/\u0010«\u0001\u001a\u00020\u000f2\b\b\u0002\u00105\u001a\u00020\u00172\b\b\u0002\u0010;\u001a\u00020\u001a2\t\b\u0002\u0010¬\u0001\u001a\u00020\u001c¢\u0006\u0006\b\u00ad\u0001\u0010®\u0001J#\u0010¯\u0001\u001a\u00020\u000f2\b\b\u0002\u00105\u001a\u00020\u00172\b\b\u0002\u0010;\u001a\u00020\u001a¢\u0006\u0005\b°\u0001\u0010yJ\u0011\u0010¸\u0001\u001a\u00030¹\u0001H\u0086@¢\u0006\u0003\u0010º\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001f\u0010\f\u001a\u0013\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\r¢\u0006\u0002\b\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u001f\u0010\u0011\u001a\u0013\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\r¢\u0006\u0002\b\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0018R\u0010\u0010\u0019\u001a\u00020\u001aX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0018R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001f\u001a\u0004\u0018\u00010 X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010!\u001a\u0004\u0018\u00010 X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010#\u001a\u0004\u0018\u00010$X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010%\u001a\u0004\u0018\u00010&X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020(X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020*X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010,\u001a\u00020\u00152\u0006\u0010+\u001a\u00020\u0015@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b,\u0010-R$\u0010/\u001a\u00020.2\u0006\u0010+\u001a\u00020.8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b0\u00101\"\u0004\b2\u00103R&\u00105\u001a\u0002042\u0006\u0010+\u001a\u000204@FX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0018\u001a\u0004\b6\u00107\"\u0004\b8\u00109R&\u0010;\u001a\u00020:2\u0006\u0010+\u001a\u00020:@BX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0018\u001a\u0004\b<\u00107\"\u0004\b=\u00109R$\u0010>\u001a\u00020\u001c2\u0006\u0010+\u001a\u00020\u001c8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b?\u0010@\"\u0004\bA\u0010BR$\u0010D\u001a\u00020C2\u0006\u0010+\u001a\u00020C8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bE\u00101\"\u0004\bF\u00103R(\u0010H\u001a\u0004\u0018\u00010G2\b\u0010+\u001a\u0004\u0018\u00010G8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bI\u0010J\"\u0004\bK\u0010LR&\u0010M\u001a\u00020\u00172\u0006\u0010+\u001a\u00020\u0017@FX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0018\u001a\u0004\bN\u00107\"\u0004\bO\u00109R$\u0010P\u001a\u00020\u001c2\u0006\u0010+\u001a\u00020\u001c8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bQ\u0010@\"\u0004\bR\u0010BR$\u0010S\u001a\u00020\u001c2\u0006\u0010+\u001a\u00020\u001c8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bT\u0010@\"\u0004\bU\u0010BR$\u0010V\u001a\u00020\u001c2\u0006\u0010+\u001a\u00020\u001c8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bW\u0010@\"\u0004\bX\u0010BR$\u0010Y\u001a\u00020\u001c2\u0006\u0010+\u001a\u00020\u001c8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bZ\u0010@\"\u0004\b[\u0010BR$\u0010\\\u001a\u00020\u001c2\u0006\u0010+\u001a\u00020\u001c8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b]\u0010@\"\u0004\b^\u0010BR$\u0010_\u001a\u00020\u001c2\u0006\u0010+\u001a\u00020\u001c8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b`\u0010@\"\u0004\ba\u0010BR$\u0010b\u001a\u00020\u001c2\u0006\u0010+\u001a\u00020\u001c8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bc\u0010@\"\u0004\bd\u0010BR$\u0010e\u001a\u00020\u001c2\u0006\u0010+\u001a\u00020\u001c8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bf\u0010@\"\u0004\bg\u0010BR$\u0010h\u001a\u00020\u001c2\u0006\u0010+\u001a\u00020\u001c8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bi\u0010@\"\u0004\bj\u0010BR*\u0010k\u001a\u00020\u00152\u0006\u0010+\u001a\u00020\u0015@FX\u0086\u000e¢\u0006\u0014\n\u0000\u0012\u0004\bl\u0010m\u001a\u0004\bn\u0010-\"\u0004\bo\u0010pR(\u0010r\u001a\u0004\u0018\u00010q2\b\u0010+\u001a\u0004\u0018\u00010q8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bs\u0010t\"\u0004\bu\u0010vR\u0012\u0010\u008f\u0001\u001a\u0005\u0018\u00010\u0090\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010¡\u0001\u001a\u00030¢\u00018F¢\u0006\u0007\u001a\u0005\b£\u0001\u00107R\u0014\u0010¤\u0001\u001a\u00030¢\u00018F¢\u0006\u0007\u001a\u0005\b¥\u0001\u00107R\u0014\u0010¦\u0001\u001a\u00020\u001e8F¢\u0006\b\u001a\u0006\b§\u0001\u0010¨\u0001R)\u0010²\u0001\u001a\u00030±\u00012\u0007\u0010+\u001a\u00030±\u00018F@FX\u0086\u000e¢\u0006\u000e\u001a\u0005\b³\u0001\u00107\"\u0005\b´\u0001\u00109R)\u0010µ\u0001\u001a\u00030±\u00012\u0007\u0010+\u001a\u00030±\u00018F@FX\u0086\u000e¢\u0006\u000e\u001a\u0005\b¶\u0001\u00107\"\u0005\b·\u0001\u00109¨\u0006¼\u0001"}, d2 = {"Landroidx/compose/ui/graphics/layer/GraphicsLayer;", "", "impl", "Landroidx/compose/ui/graphics/layer/GraphicsLayerImpl;", "<init>", "(Landroidx/compose/ui/graphics/layer/GraphicsLayerImpl;)V", "getImpl$ui_graphics", "()Landroidx/compose/ui/graphics/layer/GraphicsLayerImpl;", "density", "Landroidx/compose/ui/unit/Density;", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "drawBlock", "Lkotlin/Function1;", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "", "Lkotlin/ExtensionFunctionType;", "clipDrawBlock", "androidOutline", "Landroid/graphics/Outline;", "outlineDirty", "", "roundRectOutlineTopLeft", "Landroidx/compose/ui/geometry/Offset;", "J", "roundRectOutlineSize", "Landroidx/compose/ui/geometry/Size;", "roundRectCornerRadius", "", "internalOutline", "Landroidx/compose/ui/graphics/Outline;", "outlinePath", "Landroidx/compose/ui/graphics/Path;", "roundRectClipPath", "usePathForClip", "softwareDrawScope", "Landroidx/compose/ui/graphics/drawscope/CanvasDrawScope;", "softwareLayerPaint", "Landroidx/compose/ui/graphics/Paint;", "parentLayerUsages", "", "childDependenciesTracker", "Landroidx/compose/ui/graphics/layer/ChildLayerDependenciesTracker;", "value", "isReleased", "()Z", "Landroidx/compose/ui/graphics/layer/CompositingStrategy;", "compositingStrategy", "getCompositingStrategy-ke2Ky5w", "()I", "setCompositingStrategy-Wpw9cng", "(I)V", "Landroidx/compose/ui/unit/IntOffset;", "topLeft", "getTopLeft-nOcc-ac", "()J", "setTopLeft--gyyYBs", "(J)V", "Landroidx/compose/ui/unit/IntSize;", "size", "getSize-YbymL2g", "setSize-ozmzZPI", "alpha", "getAlpha", "()F", "setAlpha", "(F)V", "Landroidx/compose/ui/graphics/BlendMode;", "blendMode", "getBlendMode-0nO6VwU", "setBlendMode-s9anfk8", "Landroidx/compose/ui/graphics/ColorFilter;", "colorFilter", "getColorFilter", "()Landroidx/compose/ui/graphics/ColorFilter;", "setColorFilter", "(Landroidx/compose/ui/graphics/ColorFilter;)V", "pivotOffset", "getPivotOffset-F1C5BW0", "setPivotOffset-k-4lQ0M", "scaleX", "getScaleX", "setScaleX", "scaleY", "getScaleY", "setScaleY", "translationX", "getTranslationX", "setTranslationX", "translationY", "getTranslationY", "setTranslationY", "shadowElevation", "getShadowElevation", "setShadowElevation", "rotationX", "getRotationX", "setRotationX", "rotationY", "getRotationY", "setRotationY", "rotationZ", "getRotationZ", "setRotationZ", "cameraDistance", "getCameraDistance", "setCameraDistance", "clip", "getClip$annotations", "()V", "getClip", "setClip", "(Z)V", "Landroidx/compose/ui/graphics/RenderEffect;", "renderEffect", "getRenderEffect", "()Landroidx/compose/ui/graphics/RenderEffect;", "setRenderEffect", "(Landroidx/compose/ui/graphics/RenderEffect;)V", "setPosition", "setPosition-VbeCjmY", "(JJ)V", "record", "block", "record-mL-hObY", "(Landroidx/compose/ui/unit/Density;Landroidx/compose/ui/unit/LayoutDirection;JLkotlin/jvm/functions/Function1;)V", "recordInternal", "drawWithChildTracking", "addSubLayer", "graphicsLayer", "transformCanvas", "androidCanvas", "Landroid/graphics/Canvas;", "drawForPersistence", "canvas", "Landroidx/compose/ui/graphics/Canvas;", "drawForPersistence$ui_graphics", "recreateDisplayListIfNeeded", "draw", "parentLayer", "draw$ui_graphics", "onAddedToParentLayer", "onRemovedFromParentLayer", "pathBounds", "Landroid/graphics/RectF;", "obtainPathBounds", "configureOutlineAndClip", "resolveOutlinePosition", "T", "Lkotlin/Function2;", "(Lkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "updatePathOutline", "path", "obtainAndroidOutline", "release", "release$ui_graphics", "discardContentIfReleasedAndHaveNoParentLayerUsages", "discardDisplayList", "discardDisplayList$ui_graphics", "emulateTrimMemory", "emulateTrimMemory$ui_graphics", "layerId", "", "getLayerId", "ownerViewId", "getOwnerViewId", "outline", "getOutline", "()Landroidx/compose/ui/graphics/Outline;", "resetOutlineParams", "setPathOutline", "setRoundRectOutline", "cornerRadius", "setRoundRectOutline-TNW_H78", "(JJF)V", "setRectOutline", "setRectOutline-tz77jQw", "Landroidx/compose/ui/graphics/Color;", "ambientShadowColor", "getAmbientShadowColor-0d7_KjU", "setAmbientShadowColor-8_81llA", "spotShadowColor", "getSpotShadowColor-0d7_KjU", "setSpotShadowColor-8_81llA", "toImageBitmap", "Landroidx/compose/ui/graphics/ImageBitmap;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "ui-graphics"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class GraphicsLayer {
    private static final LayerSnapshotImpl SnapshotImpl;
    private static final boolean isRobolectric;
    private Outline androidOutline;
    private boolean clip;
    private final GraphicsLayerImpl impl;
    private androidx.compose.ui.graphics.Outline internalOutline;
    private boolean isReleased;
    private Path outlinePath;
    private int parentLayerUsages;
    private RectF pathBounds;
    private long pivotOffset;
    private Path roundRectClipPath;
    private float roundRectCornerRadius;
    private long size;
    private CanvasDrawScope softwareDrawScope;
    private Paint softwareLayerPaint;
    private long topLeft;
    private boolean usePathForClip;
    public static final int $stable = 8;
    private Density density = DrawContextKt.getDefaultDensity();
    private LayoutDirection layoutDirection = LayoutDirection.Ltr;
    private Function1<? super DrawScope, Unit> drawBlock = new Function1<DrawScope, Unit>() { // from class: androidx.compose.ui.graphics.layer.GraphicsLayer$drawBlock$1
        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope) {
            invoke2(drawScope);
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(DrawScope drawScope) {
        }
    };
    private final Function1<DrawScope, Unit> clipDrawBlock = new Function1<DrawScope, Unit>() { // from class: androidx.compose.ui.graphics.layer.GraphicsLayer$clipDrawBlock$1
        {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope) {
            invoke2(drawScope);
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(DrawScope drawScope) {
            Path path = this.this$0.outlinePath;
            if (!this.this$0.usePathForClip || !this.this$0.getClip() || path == null) {
                this.this$0.drawWithChildTracking(drawScope);
                return;
            }
            GraphicsLayer graphicsLayer = this.this$0;
            int clipOp$iv = ClipOp.INSTANCE.m5302getIntersectrtfAjoo();
            DrawContext $this$withTransform_u24lambda_u240$iv$iv = drawScope.getDrawContext();
            long previousSize$iv$iv = $this$withTransform_u24lambda_u240$iv$iv.mo5808getSizeNHjbRc();
            $this$withTransform_u24lambda_u240$iv$iv.getCanvas().save();
            try {
                DrawTransform $this$clipPath_KD09W0M_u24lambda_u240$iv = $this$withTransform_u24lambda_u240$iv$iv.getTransform();
                $this$clipPath_KD09W0M_u24lambda_u240$iv.mo5810clipPathmtrdDE(path, clipOp$iv);
                graphicsLayer.drawWithChildTracking(drawScope);
            } finally {
                $this$withTransform_u24lambda_u240$iv$iv.getCanvas().restore();
                $this$withTransform_u24lambda_u240$iv$iv.mo5809setSizeuvyYCjk(previousSize$iv$iv);
            }
        }
    };
    private boolean outlineDirty = true;
    private long roundRectOutlineTopLeft = Offset.INSTANCE.m5084getZeroF1C5BW0();
    private long roundRectOutlineSize = Size.INSTANCE.m5145getUnspecifiedNHjbRc();
    private final ChildLayerDependenciesTracker childDependenciesTracker = new ChildLayerDependenciesTracker();

    /* JADX INFO: renamed from: androidx.compose.ui.graphics.layer.GraphicsLayer$toImageBitmap$1, reason: invalid class name */
    /* JADX INFO: compiled from: AndroidGraphicsLayer.android.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.ui.graphics.layer.GraphicsLayer", f = "AndroidGraphicsLayer.android.kt", i = {}, l = {870}, m = "toImageBitmap", n = {}, s = {}, v = 1)
    static final class AnonymousClass1 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return GraphicsLayer.this.toImageBitmap(this);
        }
    }

    public static /* synthetic */ void getClip$annotations() {
    }

    public GraphicsLayer(GraphicsLayerImpl impl) {
        this.impl = impl;
        this.impl.setClip(false);
        this.topLeft = IntOffset.INSTANCE.m8289getZeronOccac();
        this.size = IntSize.INSTANCE.m8326getZeroYbymL2g();
        this.pivotOffset = Offset.INSTANCE.m5083getUnspecifiedF1C5BW0();
    }

    /* JADX INFO: renamed from: getImpl$ui_graphics, reason: from getter */
    public final GraphicsLayerImpl getImpl() {
        return this.impl;
    }

    /* JADX INFO: renamed from: isReleased, reason: from getter */
    public final boolean getIsReleased() {
        return this.isReleased;
    }

    /* JADX INFO: renamed from: getCompositingStrategy-ke2Ky5w, reason: not valid java name */
    public final int m5976getCompositingStrategyke2Ky5w() {
        return this.impl.getCompositingStrategy();
    }

    /* JADX INFO: renamed from: setCompositingStrategy-Wpw9cng, reason: not valid java name */
    public final void m5984setCompositingStrategyWpw9cng(int value) {
        if (!CompositingStrategy.m5963equalsimpl0(this.impl.getCompositingStrategy(), value)) {
            this.impl.mo5997setCompositingStrategyWpw9cng(value);
        }
    }

    /* JADX INFO: renamed from: getTopLeft-nOcc-ac, reason: not valid java name and from getter */
    public final long getTopLeft() {
        return this.topLeft;
    }

    /* JADX INFO: renamed from: setTopLeft--gyyYBs, reason: not valid java name */
    public final void m5989setTopLeftgyyYBs(long value) {
        if (!IntOffset.m8277equalsimpl0(this.topLeft, value)) {
            this.topLeft = value;
            m5970setPositionVbeCjmY(value, this.size);
        }
    }

    /* JADX INFO: renamed from: getSize-YbymL2g, reason: not valid java name and from getter */
    public final long getSize() {
        return this.size;
    }

    /* JADX INFO: renamed from: setSize-ozmzZPI, reason: not valid java name */
    private final void m5973setSizeozmzZPI(long value) {
        if (!IntSize.m8319equalsimpl0(this.size, value)) {
            this.size = value;
            m5970setPositionVbeCjmY(this.topLeft, value);
            long $this$isUnspecified$iv = this.roundRectOutlineSize;
            if ($this$isUnspecified$iv == InlineClassHelperKt.UnspecifiedPackedFloats) {
                this.outlineDirty = true;
                configureOutlineAndClip();
            }
        }
    }

    public final float getAlpha() {
        return this.impl.getAlpha();
    }

    public final void setAlpha(float value) {
        if (!(this.impl.getAlpha() == value)) {
            this.impl.setAlpha(value);
        }
    }

    /* JADX INFO: renamed from: getBlendMode-0nO6VwU, reason: not valid java name */
    public final int m5975getBlendMode0nO6VwU() {
        return this.impl.getBlendMode();
    }

    /* JADX INFO: renamed from: setBlendMode-s9anfk8, reason: not valid java name */
    public final void m5983setBlendModes9anfk8(int value) {
        if (!BlendMode.m5222equalsimpl0(this.impl.getBlendMode(), value)) {
            this.impl.mo5996setBlendModes9anfk8(value);
        }
    }

    public final ColorFilter getColorFilter() {
        return this.impl.getColorFilter();
    }

    public final void setColorFilter(ColorFilter value) {
        if (!Intrinsics.areEqual(this.impl.getColorFilter(), value)) {
            this.impl.setColorFilter(value);
        }
    }

    /* JADX INFO: renamed from: getPivotOffset-F1C5BW0, reason: not valid java name and from getter */
    public final long getPivotOffset() {
        return this.pivotOffset;
    }

    /* JADX INFO: renamed from: setPivotOffset-k-4lQ0M, reason: not valid java name */
    public final void m5985setPivotOffsetk4lQ0M(long value) {
        if (!Offset.m5065equalsimpl0(this.pivotOffset, value)) {
            this.pivotOffset = value;
            this.impl.mo5999setPivotOffsetk4lQ0M(value);
        }
    }

    public final float getScaleX() {
        return this.impl.getScaleX();
    }

    public final void setScaleX(float value) {
        if (!(this.impl.getScaleX() == value)) {
            this.impl.setScaleX(value);
        }
    }

    public final float getScaleY() {
        return this.impl.getScaleY();
    }

    public final void setScaleY(float value) {
        if (!(this.impl.getScaleY() == value)) {
            this.impl.setScaleY(value);
        }
    }

    public final float getTranslationX() {
        return this.impl.getTranslationX();
    }

    public final void setTranslationX(float value) {
        if (!(this.impl.getTranslationX() == value)) {
            this.impl.setTranslationX(value);
        }
    }

    public final float getTranslationY() {
        return this.impl.getTranslationY();
    }

    public final void setTranslationY(float value) {
        if (!(this.impl.getTranslationY() == value)) {
            this.impl.setTranslationY(value);
        }
    }

    public final float getShadowElevation() {
        return this.impl.getShadowElevation();
    }

    public final void setShadowElevation(float value) {
        if (!(this.impl.getShadowElevation() == value)) {
            this.impl.setShadowElevation(value);
            this.outlineDirty = true;
            configureOutlineAndClip();
        }
    }

    public final float getRotationX() {
        return this.impl.getRotationX();
    }

    public final void setRotationX(float value) {
        if (!(this.impl.getRotationX() == value)) {
            this.impl.setRotationX(value);
        }
    }

    public final float getRotationY() {
        return this.impl.getRotationY();
    }

    public final void setRotationY(float value) {
        if (!(this.impl.getRotationY() == value)) {
            this.impl.setRotationY(value);
        }
    }

    public final float getRotationZ() {
        return this.impl.getRotationZ();
    }

    public final void setRotationZ(float value) {
        if (!(this.impl.getRotationZ() == value)) {
            this.impl.setRotationZ(value);
        }
    }

    public final float getCameraDistance() {
        return this.impl.getCameraDistance();
    }

    public final void setCameraDistance(float value) {
        if (!(this.impl.getCameraDistance() == value)) {
            this.impl.setCameraDistance(value);
        }
    }

    public final boolean getClip() {
        return this.clip;
    }

    public final void setClip(boolean value) {
        if (this.clip != value) {
            this.clip = value;
            this.outlineDirty = true;
            configureOutlineAndClip();
        }
    }

    public final RenderEffect getRenderEffect() {
        return this.impl.getRenderEffect();
    }

    public final void setRenderEffect(RenderEffect value) {
        if (!Intrinsics.areEqual(this.impl.getRenderEffect(), value)) {
            this.impl.setRenderEffect(value);
        }
    }

    /* JADX INFO: renamed from: setPosition-VbeCjmY, reason: not valid java name */
    private final void m5970setPositionVbeCjmY(long topLeft, long size) {
        this.impl.mo6000setPositionH0pRuoY(IntOffset.m8278getXimpl(topLeft), IntOffset.m8279getYimpl(topLeft), size);
    }

    /* JADX INFO: renamed from: record-mL-hObY, reason: not valid java name */
    public final void m5981recordmLhObY(Density density, LayoutDirection layoutDirection, long size, Function1<? super DrawScope, Unit> block) {
        m5973setSizeozmzZPI(size);
        this.density = density;
        this.layoutDirection = layoutDirection;
        this.drawBlock = block;
        this.impl.setInvalidated(true);
        recordInternal();
    }

    private final void recordInternal() {
        this.impl.record(this.density, this.layoutDirection, this, this.clipDrawBlock);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00c2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void drawWithChildTracking(androidx.compose.ui.graphics.drawscope.DrawScope r24) {
        /*
            Method dump skipped, instruction units count: 222
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.graphics.layer.GraphicsLayer.drawWithChildTracking(androidx.compose.ui.graphics.drawscope.DrawScope):void");
    }

    private final void addSubLayer(GraphicsLayer graphicsLayer) {
        if (this.childDependenciesTracker.onDependencyAdded(graphicsLayer)) {
            graphicsLayer.onAddedToParentLayer();
        }
    }

    private final void transformCanvas(Canvas androidCanvas) {
        Canvas androidCanvas2;
        float left = IntOffset.m8278getXimpl(this.topLeft);
        float top = IntOffset.m8279getYimpl(this.topLeft);
        float fM8278getXimpl = IntOffset.m8278getXimpl(this.topLeft);
        long arg0$iv = this.size;
        float right = fM8278getXimpl + ((int) (arg0$iv >> 32));
        float fM8279getYimpl = IntOffset.m8279getYimpl(this.topLeft);
        long arg0$iv2 = this.size;
        float bottom = fM8279getYimpl + ((int) (4294967295L & arg0$iv2));
        float layerAlpha = getAlpha();
        ColorFilter layerColorFilter = getColorFilter();
        int layerBlendMode = m5975getBlendMode0nO6VwU();
        boolean useSaveLayer = layerAlpha < 1.0f || !BlendMode.m5222equalsimpl0(layerBlendMode, BlendMode.INSTANCE.m5253getSrcOver0nO6VwU()) || layerColorFilter != null || CompositingStrategy.m5963equalsimpl0(m5976getCompositingStrategyke2Ky5w(), CompositingStrategy.INSTANCE.m5969getOffscreenke2Ky5w());
        if (useSaveLayer) {
            Paint it = this.softwareLayerPaint;
            if (it == null) {
                it = AndroidPaint_androidKt.Paint();
                this.softwareLayerPaint = it;
            }
            Paint it2 = it;
            it2.setAlpha(layerAlpha);
            it2.mo5188setBlendModes9anfk8(layerBlendMode);
            it2.setColorFilter(layerColorFilter);
            Paint paint = it;
            androidCanvas2 = androidCanvas;
            androidCanvas2.saveLayer(left, top, right, bottom, AndroidPaint_androidKt.getNativePaint(paint));
        } else {
            androidCanvas2 = androidCanvas;
            androidCanvas2.save();
        }
        androidCanvas2.translate(left, top);
        androidCanvas2.concat(this.impl.calculateMatrix());
    }

    public final void drawForPersistence$ui_graphics(androidx.compose.ui.graphics.Canvas canvas) {
        if (AndroidCanvas_androidKt.getNativeCanvas(canvas).isHardwareAccelerated() || this.impl.getSupportsSoftwareRendering()) {
            recreateDisplayListIfNeeded();
            this.impl.draw(canvas);
        }
    }

    private final void recreateDisplayListIfNeeded() {
        if (!this.impl.getHasDisplayList()) {
            try {
                recordInternal();
            } catch (Throwable th) {
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:55:0x0170  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0175  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x017a  */
    /* JADX WARN: Removed duplicated region for block: B:63:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void draw$ui_graphics(androidx.compose.ui.graphics.Canvas r25, androidx.compose.ui.graphics.layer.GraphicsLayer r26) {
        /*
            Method dump skipped, instruction units count: 382
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.graphics.layer.GraphicsLayer.draw$ui_graphics(androidx.compose.ui.graphics.Canvas, androidx.compose.ui.graphics.layer.GraphicsLayer):void");
    }

    private final void onAddedToParentLayer() {
        this.parentLayerUsages++;
    }

    private final void onRemovedFromParentLayer() {
        this.parentLayerUsages--;
        discardContentIfReleasedAndHaveNoParentLayerUsages();
    }

    private final RectF obtainPathBounds() {
        RectF rectF = this.pathBounds;
        if (rectF != null) {
            return rectF;
        }
        RectF it = new RectF();
        this.pathBounds = it;
        return it;
    }

    private final void configureOutlineAndClip() {
        if (this.outlineDirty) {
            boolean outlineIsNeeded = this.clip || getShadowElevation() > 0.0f;
            Outline $this$configureOutlineAndClip_u24lambda_u240 = null;
            if (outlineIsNeeded) {
                Path tmpPath = this.outlinePath;
                if (tmpPath != null) {
                    RectF bounds = obtainPathBounds();
                    if (!(tmpPath instanceof AndroidPath)) {
                        throw new UnsupportedOperationException("Unable to obtain android.graphics.Path");
                    }
                    ((AndroidPath) tmpPath).getInternalPath().computeBounds(bounds, false);
                    Outline outlineUpdatePathOutline = updatePathOutline(tmpPath);
                    if (outlineUpdatePathOutline != null) {
                        $this$configureOutlineAndClip_u24lambda_u240 = outlineUpdatePathOutline;
                        $this$configureOutlineAndClip_u24lambda_u240.setAlpha(getAlpha());
                    }
                    GraphicsLayerImpl graphicsLayerImpl = this.impl;
                    float $this$fastRoundToInt$iv = bounds.width();
                    int width$iv = Math.round($this$fastRoundToInt$iv);
                    float $this$fastRoundToInt$iv2 = bounds.height();
                    int height$iv = Math.round($this$fastRoundToInt$iv2);
                    graphicsLayerImpl.mo5998setOutlineO0kMr_c($this$configureOutlineAndClip_u24lambda_u240, IntSize.m8316constructorimpl((((long) height$iv) & 4294967295L) | (((long) width$iv) << 32)));
                    if (this.usePathForClip && this.clip) {
                        this.impl.setClip(false);
                        this.impl.discardDisplayList();
                    } else {
                        this.impl.setClip(this.clip);
                    }
                } else {
                    this.impl.setClip(this.clip);
                    Size.INSTANCE.m5146getZeroNHjbRc();
                    Outline $this$configureOutlineAndClip_u24lambda_u241 = obtainAndroidOutline();
                    long layerSize$iv = IntSizeKt.m8333toSizeozmzZPI(this.size);
                    long rRectTopLeft$iv = this.roundRectOutlineTopLeft;
                    long rRectSize$iv = this.roundRectOutlineSize;
                    long outlineSize$iv = rRectSize$iv == InlineClassHelperKt.UnspecifiedPackedFloats ? layerSize$iv : rRectSize$iv;
                    long outlineSize = outlineSize$iv;
                    int bits$iv$iv$iv = (int) (rRectTopLeft$iv >> 32);
                    float $this$fastRoundToInt$iv3 = Float.intBitsToFloat(bits$iv$iv$iv);
                    int left = Math.round($this$fastRoundToInt$iv3);
                    int bits$iv$iv$iv2 = (int) (rRectTopLeft$iv & 4294967295L);
                    float $this$fastRoundToInt$iv4 = Float.intBitsToFloat(bits$iv$iv$iv2);
                    int top = Math.round($this$fastRoundToInt$iv4);
                    int bits$iv$iv$iv3 = (int) (rRectTopLeft$iv >> 32);
                    float fIntBitsToFloat = Float.intBitsToFloat(bits$iv$iv$iv3);
                    int bits$iv$iv$iv4 = (int) (outlineSize >> 32);
                    float $this$fastRoundToInt$iv5 = fIntBitsToFloat + Float.intBitsToFloat(bits$iv$iv$iv4);
                    int right = Math.round($this$fastRoundToInt$iv5);
                    int bits$iv$iv$iv5 = (int) (rRectTopLeft$iv & 4294967295L);
                    float fIntBitsToFloat2 = Float.intBitsToFloat(bits$iv$iv$iv5);
                    int bits$iv$iv$iv6 = (int) (outlineSize & 4294967295L);
                    float $this$fastRoundToInt$iv6 = fIntBitsToFloat2 + Float.intBitsToFloat(bits$iv$iv$iv6);
                    int bottom = Math.round($this$fastRoundToInt$iv6);
                    $this$configureOutlineAndClip_u24lambda_u241.setRoundRect(left, top, right, bottom, this.roundRectCornerRadius);
                    $this$configureOutlineAndClip_u24lambda_u241.setAlpha(getAlpha());
                    this.impl.mo5998setOutlineO0kMr_c($this$configureOutlineAndClip_u24lambda_u241, IntSizeKt.m8329roundToIntSizeuvyYCjk(outlineSize));
                }
            } else {
                this.impl.setClip(false);
                this.impl.mo5998setOutlineO0kMr_c(null, IntSize.INSTANCE.m8326getZeroYbymL2g());
            }
        }
        this.outlineDirty = false;
    }

    private final <T> T resolveOutlinePosition(Function2<? super Offset, ? super Size, ? extends T> block) {
        long outlineSize;
        long layerSize = IntSizeKt.m8333toSizeozmzZPI(this.size);
        long rRectTopLeft = this.roundRectOutlineTopLeft;
        long rRectSize = this.roundRectOutlineSize;
        if (rRectSize == InlineClassHelperKt.UnspecifiedPackedFloats) {
            outlineSize = layerSize;
        } else {
            outlineSize = rRectSize;
        }
        return block.invoke(Offset.m5057boximpl(rRectTopLeft), Size.m5125boximpl(outlineSize));
    }

    private final Outline updatePathOutline(Path path) {
        Outline resultOutline;
        if (Build.VERSION.SDK_INT > 28 || path.isConvex()) {
            resultOutline = obtainAndroidOutline();
            if (Build.VERSION.SDK_INT >= 30) {
                OutlineVerificationHelper.INSTANCE.setPath(resultOutline, path);
            } else if (path instanceof AndroidPath) {
                resultOutline.setConvexPath(((AndroidPath) path).getInternalPath());
            } else {
                throw new UnsupportedOperationException("Unable to obtain android.graphics.Path");
            }
            this.usePathForClip = !resultOutline.canClip();
        } else {
            Outline outline = this.androidOutline;
            if (outline != null) {
                outline.setEmpty();
            }
            resultOutline = null;
            this.usePathForClip = true;
            this.impl.setInvalidated(true);
        }
        this.outlinePath = path;
        return resultOutline;
    }

    private final Outline obtainAndroidOutline() {
        Outline outline = this.androidOutline;
        if (outline != null) {
            return outline;
        }
        Outline it = new Outline();
        this.androidOutline = it;
        return it;
    }

    public final void release$ui_graphics() {
        if (!this.isReleased) {
            this.isReleased = true;
            discardContentIfReleasedAndHaveNoParentLayerUsages();
        }
    }

    private final void discardContentIfReleasedAndHaveNoParentLayerUsages() {
        if (this.isReleased && this.parentLayerUsages == 0) {
            discardDisplayList$ui_graphics();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0086  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void discardDisplayList$ui_graphics() {
        /*
            r24 = this;
            r0 = r24
            androidx.compose.ui.graphics.layer.ChildLayerDependenciesTracker r1 = r0.childDependenciesTracker
            r2 = 0
            androidx.compose.ui.graphics.layer.GraphicsLayer r3 = androidx.compose.ui.graphics.layer.ChildLayerDependenciesTracker.access$getDependency$p(r1)
            if (r3 == 0) goto L18
            r4 = 0
            r5 = r3
            r6 = 0
            r5.onRemovedFromParentLayer()
            r5 = 0
            androidx.compose.ui.graphics.layer.ChildLayerDependenciesTracker.access$setDependency$p(r1, r5)
        L18:
            androidx.collection.MutableScatterSet r3 = androidx.compose.ui.graphics.layer.ChildLayerDependenciesTracker.access$getDependenciesSet$p(r1)
            if (r3 == 0) goto La0
            r4 = 0
            r5 = r3
            androidx.collection.ScatterSet r5 = (androidx.collection.ScatterSet) r5
            r6 = 0
            java.lang.Object[] r7 = r5.elements
            r8 = r5
            r9 = 0
            long[] r10 = r8.metadata
            int r11 = r10.length
            int r11 = r11 + (-2)
            r12 = 0
            if (r12 > r11) goto L92
        L31:
            r13 = r10[r12]
            r15 = r13
            r17 = 0
            r18 = r1
            r19 = r2
            r1 = r15
            r15 = r3
            r16 = r4
            long r3 = ~r1
            r20 = 7
            long r3 = r3 << r20
            long r3 = r3 & r1
            r20 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r1 = r3 & r20
            int r1 = (r1 > r20 ? 1 : (r1 == r20 ? 0 : -1))
            if (r1 == 0) goto L86
            int r1 = r12 - r11
            int r1 = ~r1
            int r1 = r1 >>> 31
            r2 = 8
            int r1 = 8 - r1
            r3 = 0
        L59:
            if (r3 >= r1) goto L84
            r20 = 255(0xff, double:1.26E-321)
            long r20 = r13 & r20
            r4 = 0
            r22 = 128(0x80, double:6.3E-322)
            int r17 = (r20 > r22 ? 1 : (r20 == r22 ? 0 : -1))
            if (r17 >= 0) goto L69
            r17 = 1
            goto L6b
        L69:
            r17 = 0
        L6b:
            if (r17 == 0) goto L80
            int r4 = r12 << 3
            int r4 = r4 + r3
            r17 = r4
            r20 = 0
            r21 = r7[r17]
            androidx.compose.ui.graphics.layer.GraphicsLayer r21 = (androidx.compose.ui.graphics.layer.GraphicsLayer) r21
            r22 = 0
            r21.onRemovedFromParentLayer()
        L80:
            long r13 = r13 >> r2
            int r3 = r3 + 1
            goto L59
        L84:
            if (r1 != r2) goto L9a
        L86:
            if (r12 == r11) goto L99
            int r12 = r12 + 1
            r3 = r15
            r4 = r16
            r1 = r18
            r2 = r19
            goto L31
        L92:
            r18 = r1
            r19 = r2
            r15 = r3
            r16 = r4
        L99:
        L9a:
            r15.clear()
            goto La4
        La0:
            r18 = r1
            r19 = r2
        La4:
            androidx.compose.ui.graphics.layer.GraphicsLayerImpl r1 = r0.impl
            r1.discardDisplayList()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.graphics.layer.GraphicsLayer.discardDisplayList$ui_graphics():void");
    }

    public final void emulateTrimMemory$ui_graphics() {
        this.impl.discardDisplayList();
    }

    public final long getLayerId() {
        return this.impl.getLayerId();
    }

    public final long getOwnerViewId() {
        return this.impl.getOwnerId();
    }

    public final androidx.compose.ui.graphics.Outline getOutline() {
        long outlineSize$iv;
        Outline.Rectangle it;
        androidx.compose.ui.graphics.Outline tmpOutline = this.internalOutline;
        Path tmpPath = this.outlinePath;
        if (tmpOutline != null) {
            return tmpOutline;
        }
        if (tmpPath != null) {
            Outline.Generic it2 = new Outline.Generic(tmpPath);
            this.internalOutline = it2;
            return it2;
        }
        long layerSize$iv = IntSizeKt.m8333toSizeozmzZPI(this.size);
        long rRectTopLeft$iv = this.roundRectOutlineTopLeft;
        long rRectSize$iv = this.roundRectOutlineSize;
        if (rRectSize$iv == InlineClassHelperKt.UnspecifiedPackedFloats) {
            outlineSize$iv = layerSize$iv;
        } else {
            outlineSize$iv = rRectSize$iv;
        }
        long outlineSize = outlineSize$iv;
        int bits$iv$iv$iv = (int) (rRectTopLeft$iv >> 32);
        float left = Float.intBitsToFloat(bits$iv$iv$iv);
        long arg0$iv = rRectTopLeft$iv & 4294967295L;
        int bits$iv$iv$iv2 = (int) arg0$iv;
        float top = Float.intBitsToFloat(bits$iv$iv$iv2);
        long arg0$iv2 = outlineSize >> 32;
        int bits$iv$iv$iv3 = (int) arg0$iv2;
        float right = left + Float.intBitsToFloat(bits$iv$iv$iv3);
        long arg0$iv3 = outlineSize & 4294967295L;
        int bits$iv$iv$iv4 = (int) arg0$iv3;
        float bottom = top + Float.intBitsToFloat(bits$iv$iv$iv4);
        float cornerRadius = this.roundRectCornerRadius;
        if (cornerRadius <= 0.0f) {
            it = new Outline.Rectangle(new Rect(left, top, right, bottom));
        } else {
            long v1$iv$iv = Float.floatToRawIntBits(cornerRadius);
            long v1$iv$iv2 = Float.floatToRawIntBits(cornerRadius);
            long v2$iv$iv = (v1$iv$iv << 32) | (v1$iv$iv2 & 4294967295L);
            it = new Outline.Rounded(RoundRectKt.m5122RoundRectgG7oq9Y(left, top, right, bottom, CornerRadius.m5022constructorimpl(v2$iv$iv)));
        }
        this.internalOutline = it;
        return it;
    }

    private final void resetOutlineParams() {
        this.internalOutline = null;
        this.outlinePath = null;
        this.roundRectOutlineSize = Size.INSTANCE.m5145getUnspecifiedNHjbRc();
        this.roundRectOutlineTopLeft = Offset.INSTANCE.m5084getZeroF1C5BW0();
        this.roundRectCornerRadius = 0.0f;
        this.outlineDirty = true;
        this.usePathForClip = false;
    }

    public final void setPathOutline(Path path) {
        resetOutlineParams();
        this.outlinePath = path;
        configureOutlineAndClip();
    }

    /* JADX INFO: renamed from: setRoundRectOutline-TNW_H78, reason: not valid java name */
    public final void m5987setRoundRectOutlineTNW_H78(long topLeft, long size, float cornerRadius) {
        if (Offset.m5065equalsimpl0(this.roundRectOutlineTopLeft, topLeft) && Size.m5133equalsimpl0(this.roundRectOutlineSize, size)) {
            if ((this.roundRectCornerRadius == cornerRadius) && this.outlinePath == null) {
                return;
            }
        }
        resetOutlineParams();
        this.roundRectOutlineTopLeft = topLeft;
        this.roundRectOutlineSize = size;
        this.roundRectCornerRadius = cornerRadius;
        configureOutlineAndClip();
    }

    /* JADX INFO: renamed from: setRectOutline-tz77jQw$default, reason: not valid java name */
    public static /* synthetic */ void m5971setRectOutlinetz77jQw$default(GraphicsLayer graphicsLayer, long j, long j2, int i, Object obj) {
        if ((i & 1) != 0) {
            j = Offset.INSTANCE.m5084getZeroF1C5BW0();
        }
        if ((i & 2) != 0) {
            j2 = Size.INSTANCE.m5145getUnspecifiedNHjbRc();
        }
        graphicsLayer.m5986setRectOutlinetz77jQw(j, j2);
    }

    /* JADX INFO: renamed from: setRectOutline-tz77jQw, reason: not valid java name */
    public final void m5986setRectOutlinetz77jQw(long topLeft, long size) {
        m5987setRoundRectOutlineTNW_H78(topLeft, size, 0.0f);
    }

    /* JADX INFO: renamed from: getAmbientShadowColor-0d7_KjU, reason: not valid java name */
    public final long m5974getAmbientShadowColor0d7_KjU() {
        return this.impl.getAmbientShadowColor();
    }

    /* JADX INFO: renamed from: setAmbientShadowColor-8_81llA, reason: not valid java name */
    public final void m5982setAmbientShadowColor8_81llA(long value) {
        if (!Color.m5314equalsimpl0(value, this.impl.getAmbientShadowColor())) {
            this.impl.mo5995setAmbientShadowColor8_81llA(value);
        }
    }

    /* JADX INFO: renamed from: getSpotShadowColor-0d7_KjU, reason: not valid java name */
    public final long m5979getSpotShadowColor0d7_KjU() {
        return this.impl.getSpotShadowColor();
    }

    /* JADX INFO: renamed from: setSpotShadowColor-8_81llA, reason: not valid java name */
    public final void m5988setSpotShadowColor8_81llA(long value) {
        if (!Color.m5314equalsimpl0(value, this.impl.getSpotShadowColor())) {
            this.impl.mo6001setSpotShadowColor8_81llA(value);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object toImageBitmap(kotlin.coroutines.Continuation<? super androidx.compose.ui.graphics.ImageBitmap> r7) {
        /*
            r6 = this;
            boolean r0 = r7 instanceof androidx.compose.ui.graphics.layer.GraphicsLayer.AnonymousClass1
            if (r0 == 0) goto L14
            r0 = r7
            androidx.compose.ui.graphics.layer.GraphicsLayer$toImageBitmap$1 r0 = (androidx.compose.ui.graphics.layer.GraphicsLayer.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r1 = r0.label
            int r1 = r1 - r2
            r0.label = r1
            goto L19
        L14:
            androidx.compose.ui.graphics.layer.GraphicsLayer$toImageBitmap$1 r0 = new androidx.compose.ui.graphics.layer.GraphicsLayer$toImageBitmap$1
            r0.<init>(r7)
        L19:
            java.lang.Object r1 = r0.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r0.label
            switch(r3) {
                case 0: goto L31;
                case 1: goto L2c;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L2c:
            kotlin.ResultKt.throwOnFailure(r1)
            r3 = r1
            goto L41
        L31:
            kotlin.ResultKt.throwOnFailure(r1)
            r3 = r6
            androidx.compose.ui.graphics.layer.LayerSnapshotImpl r4 = androidx.compose.ui.graphics.layer.GraphicsLayer.SnapshotImpl
            r5 = 1
            r0.label = r5
            java.lang.Object r3 = r4.toBitmap(r3, r0)
            if (r3 != r2) goto L41
            return r2
        L41:
            android.graphics.Bitmap r3 = (android.graphics.Bitmap) r3
            androidx.compose.ui.graphics.ImageBitmap r2 = androidx.compose.ui.graphics.AndroidImageBitmap_androidKt.asImageBitmap(r3)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.graphics.layer.GraphicsLayer.toImageBitmap(kotlin.coroutines.Continuation):java.lang.Object");
    }

    static {
        LayerSnapshotV21 layerSnapshotV21;
        String lowerCase = Build.FINGERPRINT.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        isRobolectric = Intrinsics.areEqual(lowerCase, "robolectric");
        if (isRobolectric) {
            layerSnapshotV21 = LayerSnapshotV21.INSTANCE;
        } else if (Build.VERSION.SDK_INT >= 28) {
            layerSnapshotV21 = LayerSnapshotV28.INSTANCE;
        } else if (SurfaceUtils.INSTANCE.isLockHardwareCanvasAvailable()) {
            layerSnapshotV21 = LayerSnapshotV22.INSTANCE;
        } else {
            layerSnapshotV21 = LayerSnapshotV21.INSTANCE;
        }
        SnapshotImpl = layerSnapshotV21;
    }
}
