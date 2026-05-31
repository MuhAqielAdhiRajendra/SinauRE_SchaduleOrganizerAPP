package androidx.compose.foundation.border;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.RoundRect;
import androidx.compose.ui.geometry.RoundRectKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.AndroidPath_androidKt;
import androidx.compose.ui.graphics.BlendMode;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.ClipOp;
import androidx.compose.ui.graphics.Outline;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.PathOperation;
import androidx.compose.ui.graphics.drawscope.DrawContext;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.graphics.drawscope.DrawTransform;
import androidx.compose.ui.graphics.drawscope.Fill;
import androidx.compose.ui.graphics.drawscope.Stroke;
import androidx.compose.ui.graphics.layer.CompositingStrategy;
import androidx.compose.ui.graphics.layer.GraphicsLayer;
import androidx.compose.ui.graphics.layer.GraphicsLayerKt;
import androidx.compose.ui.unit.IntSize;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.location.LocationRequestCompat;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: BorderLogic.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0001\u0018\u00002\u00020\u0001B\t\b\u0007¢\u0006\u0004\b\u0002\u0010\u0003JM\u0010\r\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u000f2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\u0014\u001a\u00020\n2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\u00072\u0006\u0010\u0017\u001a\u00020\f2\b\b\u0002\u0010\u0018\u001a\u00020\u0019H\u0000¢\u0006\u0004\b\u001a\u0010\u001bJ\t\u0010\u001c\u001a\u00020\bH\u0082\bJ7\u0010\u001d\u001a\u0013\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\u000e¢\u0006\u0002\b\u00112\u0006\u0010\u0014\u001a\u00020\n2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\u00072\u0006\u0010\u0017\u001a\u00020\u001eH\u0002J\b\u0010\u001f\u001a\u00020\u0005H\u0002J)\u0010 \u001a\u0013\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\u000e¢\u0006\u0002\b\u00112\u0006\u0010\u0014\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020!H\u0002J)\u0010\"\u001a\u0013\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\u000e¢\u0006\u0002\b\u00112\u0006\u0010\u0014\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020#H\u0002R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R!\u0010\r\u001a\u0015\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000e¢\u0006\u0002\b\u0011X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Landroidx/compose/foundation/border/BorderLogic;", "", "<init>", "()V", "borderPath", "Landroidx/compose/ui/graphics/Path;", "borderWidth", "Lkotlin/Function0;", "", "lastBrush", "Landroidx/compose/ui/graphics/Brush;", "lastOutline", "Landroidx/compose/ui/graphics/Outline;", "drawBorder", "Lkotlin/Function1;", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "", "Lkotlin/ExtensionFunctionType;", "drawScope", "width", "brush", "graphicsLayerProvider", "Landroidx/compose/ui/graphics/layer/GraphicsLayer;", "outline", TypedValues.CycleType.S_WAVE_OFFSET, "Landroidx/compose/ui/geometry/Offset;", "drawBorder-2gY9BTk$foundation", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/graphics/Brush;Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/graphics/Outline;J)V", "strokeWidthPx", "createDrawGenericBorder", "Landroidx/compose/ui/graphics/Outline$Generic;", "obtainPath", "createDrawRoundRectBorder", "Landroidx/compose/ui/graphics/Outline$Rounded;", "createDrawRectBorder", "Landroidx/compose/ui/graphics/Outline$Rectangle;", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class BorderLogic {
    public static final int $stable = 8;
    private Path borderPath;
    private Function0<Float> borderWidth;
    private Function1<? super DrawScope, Unit> drawBorder;
    private Brush lastBrush;
    private Outline lastOutline;

    /* JADX INFO: renamed from: drawBorder-2gY9BTk$foundation$default */
    public static /* synthetic */ void m377drawBorder2gY9BTk$foundation$default(BorderLogic borderLogic, DrawScope drawScope, Function0 function0, Brush brush, Function0 function02, Outline outline, long j, int i, Object obj) {
        long jM5084getZeroF1C5BW0;
        if ((i & 32) == 0) {
            jM5084getZeroF1C5BW0 = j;
        } else {
            jM5084getZeroF1C5BW0 = Offset.INSTANCE.m5084getZeroF1C5BW0();
        }
        borderLogic.m378drawBorder2gY9BTk$foundation(drawScope, function0, brush, function02, outline, jM5084getZeroF1C5BW0);
    }

    /* JADX INFO: renamed from: drawBorder-2gY9BTk$foundation */
    public final void m378drawBorder2gY9BTk$foundation(DrawScope drawScope, Function0<Float> width, Brush brush, Function0<GraphicsLayer> graphicsLayerProvider, Outline outline, long j) {
        Function1<DrawScope, Unit> function1CreateDrawRectBorder;
        this.borderWidth = width;
        if (!Intrinsics.areEqual(brush, this.lastBrush) || !Intrinsics.areEqual(outline, this.lastOutline) || this.drawBorder == null) {
            this.lastBrush = brush;
            this.lastOutline = outline;
            if (outline instanceof Outline.Generic) {
                function1CreateDrawRectBorder = createDrawGenericBorder(brush, graphicsLayerProvider, (Outline.Generic) outline);
            } else if (outline instanceof Outline.Rounded) {
                function1CreateDrawRectBorder = createDrawRoundRectBorder(brush, (Outline.Rounded) outline);
            } else {
                if (!(outline instanceof Outline.Rectangle)) {
                    throw new NoWhenBranchMatchedException();
                }
                function1CreateDrawRectBorder = createDrawRectBorder(brush, (Outline.Rectangle) outline);
            }
            this.drawBorder = function1CreateDrawRectBorder;
        }
        if (Offset.m5065equalsimpl0(j, Offset.INSTANCE.m5084getZeroF1C5BW0())) {
            Function1<? super DrawScope, Unit> function1 = this.drawBorder;
            Intrinsics.checkNotNull(function1);
            function1.invoke(drawScope);
            return;
        }
        int bits$iv$iv$iv = (int) (j >> 32);
        float left$iv = Float.intBitsToFloat(bits$iv$iv$iv);
        int bits$iv$iv$iv2 = (int) (4294967295L & j);
        float top$iv = Float.intBitsToFloat(bits$iv$iv$iv2);
        drawScope.getDrawContext().getTransform().translate(left$iv, top$iv);
        try {
            Function1<? super DrawScope, Unit> function12 = this.drawBorder;
            Intrinsics.checkNotNull(function12);
            function12.invoke(drawScope);
        } finally {
            drawScope.getDrawContext().getTransform().translate(-left$iv, -top$iv);
        }
    }

    private final float strokeWidthPx() {
        Function0<Float> function0 = this.borderWidth;
        Intrinsics.checkNotNull(function0);
        return RangesKt.coerceAtLeast(function0.invoke().floatValue(), 0.0f);
    }

    private final Function1<DrawScope, Unit> createDrawGenericBorder(final Brush brush, final Function0<GraphicsLayer> graphicsLayerProvider, final Outline.Generic outline) {
        final Rect pathBounds = outline.getPath().getBounds();
        final float pathMinDimension = pathBounds.getMinDimension();
        final Path $this$createDrawGenericBorder_u24lambda_u240 = obtainPath();
        $this$createDrawGenericBorder_u24lambda_u240.reset();
        Path.addRect$default($this$createDrawGenericBorder_u24lambda_u240, pathBounds, null, 2, null);
        $this$createDrawGenericBorder_u24lambda_u240.mo5202opN5in7k0($this$createDrawGenericBorder_u24lambda_u240, outline.getPath(), PathOperation.INSTANCE.m5619getDifferenceb3I0S0c());
        int width$iv = (int) Math.ceil(pathBounds.getRight() - pathBounds.getLeft());
        int height$iv = (int) Math.ceil(pathBounds.getBottom() - pathBounds.getTop());
        final long pathBoundsSize = IntSize.m8316constructorimpl((((long) width$iv) << 32) | (((long) height$iv) & 4294967295L));
        return new Function1() { // from class: androidx.compose.foundation.border.BorderLogic$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return BorderLogic.createDrawGenericBorder$lambda$1(this.f$0, pathMinDimension, outline, brush, graphicsLayerProvider, pathBounds, pathBoundsSize, $this$createDrawGenericBorder_u24lambda_u240, (DrawScope) obj);
            }
        };
    }

    static final Unit createDrawGenericBorder$lambda$1(BorderLogic this$0, float $pathMinDimension, final Outline.Generic $outline, final Brush $brush, Function0 $graphicsLayerProvider, final Rect $pathBounds, long $pathBoundsSize, final Path $maskPath, DrawScope drawScope) throws Throwable {
        Function0<Float> function0 = this$0.borderWidth;
        Intrinsics.checkNotNull(function0);
        final float fCoerceAtLeast = RangesKt.coerceAtLeast(function0.invoke().floatValue(), 0.0f);
        boolean fillArea = 2.0f * fCoerceAtLeast > $pathMinDimension;
        if (fillArea) {
            DrawScope.m5876drawPathGBMwjPU$default(drawScope, $outline.getPath(), $brush, 0.0f, null, null, 0, 60, null);
        } else {
            GraphicsLayer layer = (GraphicsLayer) $graphicsLayerProvider.invoke();
            layer.m5984setCompositingStrategyWpw9cng(CompositingStrategy.INSTANCE.m5969getOffscreenke2Ky5w());
            float left$iv = $pathBounds.getLeft();
            float top$iv = $pathBounds.getTop();
            drawScope.getDrawContext().getTransform().translate(left$iv, top$iv);
            try {
            } catch (Throwable th) {
                th = th;
            }
            try {
                drawScope.mo5888recordJVtK1S4(layer, $pathBoundsSize, new Function1() { // from class: androidx.compose.foundation.border.BorderLogic$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return BorderLogic.createDrawGenericBorder$lambda$1$0$0($pathBounds, $outline, $brush, fCoerceAtLeast, $maskPath, (DrawScope) obj);
                    }
                });
                GraphicsLayerKt.drawLayer(drawScope, layer);
                drawScope.getDrawContext().getTransform().translate(-left$iv, -top$iv);
            } catch (Throwable th2) {
                th = th2;
                drawScope.getDrawContext().getTransform().translate(-left$iv, -top$iv);
                throw th;
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final Unit createDrawGenericBorder$lambda$1$0$0(Rect $pathBounds, Outline.Generic $outline, Brush $brush, float $strokeWidth, Path $maskPath, DrawScope $this$record) throws Throwable {
        float left$iv;
        DrawScope drawScope;
        float fIntBitsToFloat;
        int bits$iv$iv$iv;
        long previousSize$iv$iv;
        float left$iv2 = -$pathBounds.getLeft();
        float top$iv = -$pathBounds.getTop();
        $this$record.getDrawContext().getTransform().translate(left$iv2, top$iv);
        try {
            DrawScope.m5876drawPathGBMwjPU$default($this$record, $outline.getPath(), $brush, 0.0f, new Stroke($strokeWidth * 2.0f, 0.0f, 0, 0, null, 30, null), null, 0, 52, null);
            long arg0$iv = $this$record.mo5887getSizeNHjbRc();
            int bits$iv$iv$iv2 = (int) (arg0$iv >> 32);
            fIntBitsToFloat = Float.intBitsToFloat(bits$iv$iv$iv2) + 1.0f;
            long arg0$iv2 = $this$record.mo5887getSizeNHjbRc();
            drawScope = 1065353216;
            left$iv = left$iv2;
            bits$iv$iv$iv = (int) (arg0$iv2 >> 32);
        } catch (Throwable th) {
            th = th;
            left$iv = left$iv2;
            drawScope = $this$record;
        }
        try {
            try {
                float scaleX$iv = fIntBitsToFloat / Float.intBitsToFloat(bits$iv$iv$iv);
                long arg0$iv3 = $this$record.mo5887getSizeNHjbRc();
                int bits$iv$iv$iv3 = (int) (arg0$iv3 & 4294967295L);
                float fIntBitsToFloat2 = Float.intBitsToFloat(bits$iv$iv$iv3) + 1.0f;
                long arg0$iv4 = $this$record.mo5887getSizeNHjbRc();
                int bits$iv$iv$iv4 = (int) (arg0$iv4 & 4294967295L);
                float scaleY$iv = fIntBitsToFloat2 / Float.intBitsToFloat(bits$iv$iv$iv4);
                long pivot$iv = $this$record.mo5886getCenterF1C5BW0();
                DrawContext $this$withTransform_u24lambda_u240$iv$iv = $this$record.getDrawContext();
                long previousSize$iv$iv2 = $this$withTransform_u24lambda_u240$iv$iv.mo5808getSizeNHjbRc();
                $this$withTransform_u24lambda_u240$iv$iv.getCanvas().save();
                try {
                    DrawTransform $this$scale_Fgt4K4Q_u24lambda_u240$iv = $this$withTransform_u24lambda_u240$iv$iv.getTransform();
                    $this$scale_Fgt4K4Q_u24lambda_u240$iv.mo5815scale0AR0LA0(scaleX$iv, scaleY$iv, pivot$iv);
                    try {
                    } catch (Throwable th2) {
                        th = th2;
                        $this$withTransform_u24lambda_u240$iv$iv = $this$withTransform_u24lambda_u240$iv$iv;
                        previousSize$iv$iv = previousSize$iv$iv2;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    previousSize$iv$iv = previousSize$iv$iv2;
                }
                try {
                    DrawScope.m5876drawPathGBMwjPU$default($this$record, $maskPath, $brush, 0.0f, null, null, BlendMode.INSTANCE.m5226getClear0nO6VwU(), 28, null);
                    $this$withTransform_u24lambda_u240$iv$iv.getCanvas().restore();
                    $this$withTransform_u24lambda_u240$iv$iv.mo5809setSizeuvyYCjk(previousSize$iv$iv2);
                    $this$record.getDrawContext().getTransform().translate(-left$iv, -top$iv);
                    return Unit.INSTANCE;
                } catch (Throwable th4) {
                    th = th4;
                    previousSize$iv$iv = previousSize$iv$iv2;
                    $this$withTransform_u24lambda_u240$iv$iv = $this$withTransform_u24lambda_u240$iv$iv;
                    $this$withTransform_u24lambda_u240$iv$iv.getCanvas().restore();
                    $this$withTransform_u24lambda_u240$iv$iv.mo5809setSizeuvyYCjk(previousSize$iv$iv);
                    throw th;
                }
            } catch (Throwable th5) {
                th = th5;
                drawScope = $this$record;
                drawScope.getDrawContext().getTransform().translate(-left$iv, -top$iv);
                throw th;
            }
        } catch (Throwable th6) {
            th = th6;
            drawScope.getDrawContext().getTransform().translate(-left$iv, -top$iv);
            throw th;
        }
    }

    private final Path obtainPath() {
        Path path = this.borderPath;
        if (path != null) {
            return path;
        }
        Path it = AndroidPath_androidKt.Path();
        this.borderPath = it;
        return it;
    }

    private final Function1<DrawScope, Unit> createDrawRoundRectBorder(final Brush brush, Outline.Rounded outline) {
        final RoundRect roundRect = outline.getRoundRect();
        if (RoundRectKt.isSimple(roundRect)) {
            return new Function1() { // from class: androidx.compose.foundation.border.BorderLogic$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return BorderLogic.createDrawRoundRectBorder$lambda$0(this.f$0, roundRect, brush, (DrawScope) obj);
                }
            };
        }
        final Path path = obtainPath();
        final Ref.FloatRef lastStrokeWidth = new Ref.FloatRef();
        lastStrokeWidth.element = Float.NaN;
        final Ref.ObjectRef roundedRectPath = new Ref.ObjectRef();
        return new Function1() { // from class: androidx.compose.foundation.border.BorderLogic$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return BorderLogic.createDrawRoundRectBorder$lambda$1(this.f$0, roundRect, lastStrokeWidth, roundedRectPath, path, brush, (DrawScope) obj);
            }
        };
    }

    static final Unit createDrawRoundRectBorder$lambda$0(BorderLogic this$0, RoundRect $roundRect, Brush $brush, DrawScope drawScope) throws Throwable {
        long previousSize$iv$iv;
        long v2$iv$iv;
        long v1$iv$iv;
        long v2$iv$iv2;
        Function0<Float> function0 = this$0.borderWidth;
        Intrinsics.checkNotNull(function0);
        float fCoerceAtLeast = RangesKt.coerceAtLeast(function0.invoke().floatValue(), 0.0f);
        float halfStroke = fCoerceAtLeast / 2.0f;
        boolean fillArea = 2.0f * fCoerceAtLeast > RoundRectKt.getMinDimension($roundRect);
        long cornerRadius = $roundRect.m5118getTopLeftCornerRadiuskKHJgLs();
        Stroke borderStroke = new Stroke(fCoerceAtLeast, 0.0f, 0, 0, null, 30, null);
        if (fillArea) {
            float x$iv = $roundRect.getLeft();
            float y$iv = $roundRect.getTop();
            long v1$iv$iv2 = Float.floatToRawIntBits(x$iv);
            long v2$iv$iv3 = Float.floatToRawIntBits(y$iv);
            long v1$iv$iv3 = Offset.m5060constructorimpl((v1$iv$iv2 << 32) | (v2$iv$iv3 & 4294967295L));
            float width$iv = $roundRect.getWidth();
            float height$iv = $roundRect.getHeight();
            long v1$iv$iv4 = Float.floatToRawIntBits(width$iv);
            long v2$iv$iv4 = Float.floatToRawIntBits(height$iv);
            DrawScope.m5882drawRoundRectZuiqVtQ$default(drawScope, $brush, v1$iv$iv3, Size.m5128constructorimpl((v1$iv$iv4 << 32) | (v2$iv$iv4 & 4294967295L)), cornerRadius, 0.0f, null, null, 0, 240, null);
        } else {
            int bits$iv$iv$iv = (int) (cornerRadius >> 32);
            if (Float.intBitsToFloat(bits$iv$iv$iv) < halfStroke) {
                float left$iv = $roundRect.getLeft() + fCoerceAtLeast;
                float top$iv = $roundRect.getTop() + fCoerceAtLeast;
                float right$iv = $roundRect.getRight() - fCoerceAtLeast;
                float bottom$iv = $roundRect.getBottom() - fCoerceAtLeast;
                int clipOp$iv = ClipOp.INSTANCE.m5301getDifferencertfAjoo();
                DrawContext $this$withTransform_u24lambda_u240$iv$iv = drawScope.getDrawContext();
                long previousSize$iv$iv2 = $this$withTransform_u24lambda_u240$iv$iv.mo5808getSizeNHjbRc();
                $this$withTransform_u24lambda_u240$iv$iv.getCanvas().save();
                try {
                    DrawTransform $this$clipRect_rOu3jXo_u24lambda_u240$iv = $this$withTransform_u24lambda_u240$iv$iv.getTransform();
                    $this$clipRect_rOu3jXo_u24lambda_u240$iv.mo5811clipRectN_I0leg(left$iv, top$iv, right$iv, bottom$iv, clipOp$iv);
                    try {
                        float x$iv2 = $roundRect.getLeft();
                        float y$iv2 = $roundRect.getTop();
                        long v1$iv$iv5 = Float.floatToRawIntBits(x$iv2);
                        try {
                            long v2$iv$iv5 = Float.floatToRawIntBits(y$iv2);
                            v2$iv$iv = Offset.m5060constructorimpl((v1$iv$iv5 << 32) | (v2$iv$iv5 & 4294967295L));
                            float width$iv2 = $roundRect.getWidth();
                            float height$iv2 = $roundRect.getHeight();
                            v1$iv$iv = Float.floatToRawIntBits(width$iv2);
                            v2$iv$iv2 = Float.floatToRawIntBits(height$iv2);
                            previousSize$iv$iv = previousSize$iv$iv2;
                        } catch (Throwable th) {
                            th = th;
                            previousSize$iv$iv = previousSize$iv$iv2;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        previousSize$iv$iv = previousSize$iv$iv2;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    previousSize$iv$iv = previousSize$iv$iv2;
                }
                try {
                    DrawScope.m5882drawRoundRectZuiqVtQ$default(drawScope, $brush, v2$iv$iv, Size.m5128constructorimpl((v1$iv$iv << 32) | (v2$iv$iv2 & 4294967295L)), cornerRadius, 0.0f, null, null, 0, 240, null);
                    $this$withTransform_u24lambda_u240$iv$iv.getCanvas().restore();
                    $this$withTransform_u24lambda_u240$iv$iv.mo5809setSizeuvyYCjk(previousSize$iv$iv);
                } catch (Throwable th4) {
                    th = th4;
                    $this$withTransform_u24lambda_u240$iv$iv.getCanvas().restore();
                    $this$withTransform_u24lambda_u240$iv$iv.mo5809setSizeuvyYCjk(previousSize$iv$iv);
                    throw th;
                }
            } else {
                float x$iv3 = $roundRect.getLeft() + halfStroke;
                float y$iv3 = $roundRect.getTop() + halfStroke;
                long v1$iv$iv6 = Float.floatToRawIntBits(x$iv3);
                long v2$iv$iv6 = Float.floatToRawIntBits(y$iv3);
                long jM5060constructorimpl = Offset.m5060constructorimpl((v1$iv$iv6 << 32) | (v2$iv$iv6 & 4294967295L));
                float x$iv4 = $roundRect.getWidth();
                float width$iv3 = x$iv4 - fCoerceAtLeast;
                float height$iv3 = $roundRect.getHeight() - fCoerceAtLeast;
                long v1$iv$iv7 = Float.floatToRawIntBits(width$iv3);
                long v2$iv$iv7 = Float.floatToRawIntBits(height$iv3);
                DrawScope.m5882drawRoundRectZuiqVtQ$default(drawScope, $brush, jM5060constructorimpl, Size.m5128constructorimpl((v1$iv$iv7 << 32) | (v2$iv$iv7 & 4294967295L)), BorderLogicKt.m380shrinkKibmq7A(cornerRadius, halfStroke), 0.0f, borderStroke, null, 0, 208, null);
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Type inference failed for: r6v4, types: [T, androidx.compose.ui.graphics.Path] */
    static final Unit createDrawRoundRectBorder$lambda$1(BorderLogic this$0, RoundRect $roundRect, Ref.FloatRef $lastStrokeWidth, Ref.ObjectRef $roundedRectPath, Path $path, Brush $brush, DrawScope drawScope) {
        Function0<Float> function0 = this$0.borderWidth;
        Intrinsics.checkNotNull(function0);
        float strokeWidthPx = RangesKt.coerceAtLeast(function0.invoke().floatValue(), 0.0f);
        boolean fillArea = 2.0f * strokeWidthPx > RoundRectKt.getMinDimension($roundRect);
        if (!($lastStrokeWidth.element == strokeWidthPx)) {
            $roundedRectPath.element = BorderLogicKt.createRoundRectPath($path, $roundRect, strokeWidthPx, fillArea);
            $lastStrokeWidth.element = strokeWidthPx;
        }
        T t = $roundedRectPath.element;
        Intrinsics.checkNotNull(t);
        DrawScope.m5876drawPathGBMwjPU$default(drawScope, (Path) t, $brush, 0.0f, null, null, 0, 60, null);
        return Unit.INSTANCE;
    }

    private final Function1<DrawScope, Unit> createDrawRectBorder(final Brush brush, Outline.Rectangle outline) {
        final Rect rect = outline.getRect();
        return new Function1() { // from class: androidx.compose.foundation.border.BorderLogic$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return BorderLogic.createDrawRectBorder$lambda$0(this.f$0, rect, brush, (DrawScope) obj);
            }
        };
    }

    static final Unit createDrawRectBorder$lambda$0(BorderLogic this$0, Rect $rect, Brush $brush, DrawScope drawScope) {
        long jM5060constructorimpl;
        float strokeWidthPx;
        long rectSize;
        Function0<Float> function0 = this$0.borderWidth;
        Intrinsics.checkNotNull(function0);
        float fCoerceAtLeast = RangesKt.coerceAtLeast(function0.invoke().floatValue(), 0.0f);
        boolean fillArea = fCoerceAtLeast * 2.0f > $rect.getMinDimension();
        if (!fillArea) {
            float x$iv = $rect.getLeft() + (fCoerceAtLeast / 2.0f);
            float y$iv = $rect.getTop() + (fCoerceAtLeast / 2.0f);
            long v1$iv$iv = Float.floatToRawIntBits(x$iv);
            long v2$iv$iv = Float.floatToRawIntBits(y$iv);
            jM5060constructorimpl = Offset.m5060constructorimpl((v1$iv$iv << 32) | (v2$iv$iv & 4294967295L));
        } else {
            jM5060constructorimpl = $rect.m5103getTopLeftF1C5BW0();
        }
        long rectTopLeft = jM5060constructorimpl;
        if (fillArea) {
            rectSize = $rect.m5101getSizeNHjbRc();
            strokeWidthPx = fCoerceAtLeast;
        } else {
            float width$iv = ($rect.getRight() - $rect.getLeft()) - fCoerceAtLeast;
            float height$iv = ($rect.getBottom() - $rect.getTop()) - fCoerceAtLeast;
            long v1$iv$iv2 = Float.floatToRawIntBits(width$iv);
            strokeWidthPx = fCoerceAtLeast;
            long v2$iv$iv2 = Float.floatToRawIntBits(height$iv);
            rectSize = Size.m5128constructorimpl((v1$iv$iv2 << 32) | (4294967295L & v2$iv$iv2));
        }
        DrawStyle style = fillArea ? Fill.INSTANCE : new Stroke(strokeWidthPx, 0.0f, 0, 0, null, 30, null);
        DrawScope.m5880drawRectAsUm42w$default(drawScope, $brush, rectTopLeft, rectSize, 0.0f, style, null, 0, LocationRequestCompat.QUALITY_LOW_POWER, null);
        return Unit.INSTANCE;
    }
}
