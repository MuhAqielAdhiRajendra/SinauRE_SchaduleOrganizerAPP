package androidx.compose.foundation;

import androidx.compose.ui.draw.CacheDrawModifierNode;
import androidx.compose.ui.draw.CacheDrawScope;
import androidx.compose.ui.draw.DrawModifierKt;
import androidx.compose.ui.draw.DrawResult;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.RoundRectKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.ClipOp;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.ImageBitmap;
import androidx.compose.ui.graphics.Outline;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawContext;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.DrawTransform;
import androidx.compose.ui.graphics.drawscope.Stroke;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.SemanticsModifierNode;
import androidx.compose.ui.node.SemanticsModifierNodeKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* JADX INFO: compiled from: Border.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\b\u0001\u0018\u00002\u00020\u00012\u00020\u0002B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ,\u0010%\u001a\u00020&*\u00020'2\u0006\u0010\u0019\u001a\u00020\u00062\u0006\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020\f2\u0006\u0010+\u001a\u00020,H\u0002JC\u0010-\u001a\u00020&*\u00020'2\u0006\u0010\u0019\u001a\u00020\u00062\u0006\u0010(\u001a\u00020.2\u0006\u0010/\u001a\u0002002\u0006\u00101\u001a\u0002022\u0006\u0010*\u001a\u00020\f2\u0006\u0010+\u001a\u00020,H\u0002¢\u0006\u0004\b3\u00104J\f\u00105\u001a\u000206*\u000207H\u0016R\u0014\u0010\u000b\u001a\u00020\fX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\u00020\fX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R&\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u0004@FX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0018\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R$\u0010\u0019\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u0006@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR$\u0010\u001e\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\b@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u000e\u0010#\u001a\u00020$X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00068"}, d2 = {"Landroidx/compose/foundation/BorderModifierNode;", "Landroidx/compose/ui/node/DelegatingNode;", "Landroidx/compose/ui/node/SemanticsModifierNode;", "widthParameter", "Landroidx/compose/ui/unit/Dp;", "brushParameter", "Landroidx/compose/ui/graphics/Brush;", "shapeParameter", "Landroidx/compose/ui/graphics/Shape;", "<init>", "(FLandroidx/compose/ui/graphics/Brush;Landroidx/compose/ui/graphics/Shape;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "shouldAutoInvalidate", "", "getShouldAutoInvalidate", "()Z", "isImportantForBounds", "borderCache", "Landroidx/compose/foundation/BorderCache;", "value", "width", "getWidth-D9Ej5fM", "()F", "setWidth-0680j_4", "(F)V", "F", "brush", "getBrush", "()Landroidx/compose/ui/graphics/Brush;", "setBrush", "(Landroidx/compose/ui/graphics/Brush;)V", "shape", "getShape", "()Landroidx/compose/ui/graphics/Shape;", "setShape", "(Landroidx/compose/ui/graphics/Shape;)V", "drawWithCacheModifierNode", "Landroidx/compose/ui/draw/CacheDrawModifierNode;", "drawGenericBorder", "Landroidx/compose/ui/draw/DrawResult;", "Landroidx/compose/ui/draw/CacheDrawScope;", "outline", "Landroidx/compose/ui/graphics/Outline$Generic;", "fillArea", "strokeWidth", "", "drawRoundRectBorder", "Landroidx/compose/ui/graphics/Outline$Rounded;", "topLeft", "Landroidx/compose/ui/geometry/Offset;", "borderSize", "Landroidx/compose/ui/geometry/Size;", "drawRoundRectBorder-JqoCqck", "(Landroidx/compose/ui/draw/CacheDrawScope;Landroidx/compose/ui/graphics/Brush;Landroidx/compose/ui/graphics/Outline$Rounded;JJZF)Landroidx/compose/ui/draw/DrawResult;", "applySemantics", "", "Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class BorderModifierNode extends DelegatingNode implements SemanticsModifierNode {
    public static final int $stable = 8;
    private BorderCache borderCache;
    private Brush brush;
    private final CacheDrawModifierNode drawWithCacheModifierNode;
    private final boolean isImportantForBounds;
    private Shape shape;
    private final boolean shouldAutoInvalidate;
    private float width;

    public /* synthetic */ BorderModifierNode(float f, Brush brush, Shape shape, DefaultConstructorMarker defaultConstructorMarker) {
        this(f, brush, shape);
    }

    private BorderModifierNode(float widthParameter, Brush brushParameter, Shape shapeParameter) {
        this.width = widthParameter;
        this.brush = brushParameter;
        this.shape = shapeParameter;
        this.drawWithCacheModifierNode = (CacheDrawModifierNode) delegate(DrawModifierKt.CacheDrawModifierNode(new Function1() { // from class: androidx.compose.foundation.BorderModifierNode$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return BorderModifierNode.drawWithCacheModifierNode$lambda$0(this.f$0, (CacheDrawScope) obj);
            }
        }));
    }

    @Override // androidx.compose.ui.Modifier.Node
    public boolean getShouldAutoInvalidate() {
        return this.shouldAutoInvalidate;
    }

    @Override // androidx.compose.ui.node.SemanticsModifierNode
    /* JADX INFO: renamed from: isImportantForBounds, reason: from getter */
    public boolean getIsImportantForBounds() {
        return this.isImportantForBounds;
    }

    /* JADX INFO: renamed from: getWidth-D9Ej5fM, reason: not valid java name and from getter */
    public final float getWidth() {
        return this.width;
    }

    /* JADX INFO: renamed from: setWidth-0680j_4, reason: not valid java name */
    public final void m304setWidth0680j_4(float value) {
        if (!Dp.m8155equalsimpl0(this.width, value)) {
            this.width = value;
            this.drawWithCacheModifierNode.invalidateDrawCache();
        }
    }

    public final Brush getBrush() {
        return this.brush;
    }

    public final void setBrush(Brush value) {
        if (!Intrinsics.areEqual(this.brush, value)) {
            this.brush = value;
            this.drawWithCacheModifierNode.invalidateDrawCache();
        }
    }

    public final Shape getShape() {
        return this.shape;
    }

    public final void setShape(Shape value) {
        if (!Intrinsics.areEqual(this.shape, value)) {
            this.shape = value;
            this.drawWithCacheModifierNode.invalidateDrawCache();
            SemanticsModifierNodeKt.invalidateSemantics(this);
        }
    }

    static final DrawResult drawWithCacheModifierNode$lambda$0(BorderModifierNode this$0, CacheDrawScope $this$CacheDrawModifierNode) {
        boolean hasValidBorderParams = $this$CacheDrawModifierNode.mo432toPx0680j_4(this$0.width) >= 0.0f && Size.m5136getMinDimensionimpl($this$CacheDrawModifierNode.m4848getSizeNHjbRc()) > 0.0f;
        if (!hasValidBorderParams) {
            return BorderKt.drawContentWithoutBorder($this$CacheDrawModifierNode);
        }
        float strokeWidthPx = Math.min(Dp.m8155equalsimpl0(this$0.width, Dp.INSTANCE.m8168getHairlineD9Ej5fM()) ? 1.0f : (float) Math.ceil($this$CacheDrawModifierNode.mo432toPx0680j_4(this$0.width)), (float) Math.ceil(Size.m5136getMinDimensionimpl($this$CacheDrawModifierNode.m4848getSizeNHjbRc()) / 2.0f));
        float halfStroke = strokeWidthPx / 2.0f;
        long v1$iv$iv = Float.floatToRawIntBits(halfStroke);
        long v2$iv$iv = Float.floatToRawIntBits(halfStroke);
        long jM5060constructorimpl = Offset.m5060constructorimpl((v1$iv$iv << 32) | (v2$iv$iv & 4294967295L));
        long arg0$iv = $this$CacheDrawModifierNode.m4848getSizeNHjbRc();
        int bits$iv$iv$iv = (int) (arg0$iv >> 32);
        float width$iv = Float.intBitsToFloat(bits$iv$iv$iv) - strokeWidthPx;
        long arg0$iv2 = $this$CacheDrawModifierNode.m4848getSizeNHjbRc();
        int bits$iv$iv$iv2 = (int) (arg0$iv2 & 4294967295L);
        float height$iv = Float.intBitsToFloat(bits$iv$iv$iv2) - strokeWidthPx;
        long v1$iv$iv2 = Float.floatToRawIntBits(width$iv);
        long v2$iv$iv2 = Float.floatToRawIntBits(height$iv);
        long jM5128constructorimpl = Size.m5128constructorimpl((v1$iv$iv2 << 32) | (v2$iv$iv2 & 4294967295L));
        boolean fillArea = strokeWidthPx * 2.0f > Size.m5136getMinDimensionimpl($this$CacheDrawModifierNode.m4848getSizeNHjbRc());
        Outline outline = this$0.shape.mo342createOutlinePq9zytI($this$CacheDrawModifierNode.m4848getSizeNHjbRc(), $this$CacheDrawModifierNode.getLayoutDirection(), $this$CacheDrawModifierNode);
        if (outline instanceof Outline.Generic) {
            return this$0.drawGenericBorder($this$CacheDrawModifierNode, this$0.brush, (Outline.Generic) outline, fillArea, strokeWidthPx);
        }
        if (outline instanceof Outline.Rounded) {
            return this$0.m302drawRoundRectBorderJqoCqck($this$CacheDrawModifierNode, this$0.brush, (Outline.Rounded) outline, jM5060constructorimpl, jM5128constructorimpl, fillArea, strokeWidthPx);
        }
        if (outline instanceof Outline.Rectangle) {
            return BorderKt.m300drawRectBorderNsqcLGU($this$CacheDrawModifierNode, this$0.brush, jM5060constructorimpl, jM5128constructorimpl, fillArea, strokeWidthPx);
        }
        throw new NoWhenBranchMatchedException();
    }

    /* JADX WARN: Can't wrap try/catch for region: R(15:(8:(1:37)(26:30|(2:32|(0)(1:36))|40|(1:42)|43|82|44|45|88|46|47|90|48|84|49|50|92|51|52|78|53|54|80|55|56|57)|78|53|54|80|55|56|57)|82|44|45|88|46|47|90|48|84|49|50|92|51|52) */
    /* JADX WARN: Can't wrap try/catch for region: R(9:(8:(1:37)(26:30|(2:32|(0)(1:36))|40|(1:42)|43|82|44|45|88|46|47|90|48|84|49|50|92|51|52|78|53|54|80|55|56|57)|78|53|54|80|55|56|57)|90|48|84|49|50|92|51|52) */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x03cf, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x03d0, code lost:
    
        r3 = r0;
        r1 = r7;
        r4 = r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x03e7, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x03e8, code lost:
    
        r3 = r0;
        r1 = r7;
        r4 = r8;
     */
    /* JADX WARN: Removed duplicated region for block: B:26:0x012f  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x01c2  */
    /* JADX WARN: Type inference failed for: r50v1, types: [T, androidx.compose.ui.graphics.ImageBitmap] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final androidx.compose.ui.draw.DrawResult drawGenericBorder(androidx.compose.ui.draw.CacheDrawScope r69, final androidx.compose.ui.graphics.Brush r70, final androidx.compose.ui.graphics.Outline.Generic r71, boolean r72, float r73) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 1106
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.BorderModifierNode.drawGenericBorder(androidx.compose.ui.draw.CacheDrawScope, androidx.compose.ui.graphics.Brush, androidx.compose.ui.graphics.Outline$Generic, boolean, float):androidx.compose.ui.draw.DrawResult");
    }

    static final Unit drawGenericBorder$lambda$0(Outline.Generic $outline, Brush $brush, ContentDrawScope $this$onDrawWithContent) {
        $this$onDrawWithContent.drawContent();
        DrawScope.m5876drawPathGBMwjPU$default($this$onDrawWithContent, $outline.getPath(), $brush, 0.0f, null, null, 0, 60, null);
        return Unit.INSTANCE;
    }

    static final Unit drawGenericBorder$lambda$3(Rect $pathBounds, Ref.ObjectRef $cacheImageBitmap, long $pathBoundsSize, ColorFilter $colorFilter, ContentDrawScope $this$onDrawWithContent) {
        $this$onDrawWithContent.drawContent();
        ContentDrawScope $this$translate$iv = $this$onDrawWithContent;
        float left$iv = $pathBounds.getLeft();
        float top$iv = $pathBounds.getTop();
        $this$translate$iv.getDrawContext().getTransform().translate(left$iv, top$iv);
        try {
            DrawScope.m5870drawImageAZ2fEMs$default($this$translate$iv, (ImageBitmap) $cacheImageBitmap.element, 0L, $pathBoundsSize, 0L, 0L, 0.0f, null, $colorFilter, 0, 0, 890, null);
            $this$translate$iv.getDrawContext().getTransform().translate(-left$iv, -top$iv);
            return Unit.INSTANCE;
        } catch (Throwable th) {
            $this$translate$iv.getDrawContext().getTransform().translate(-left$iv, -top$iv);
            throw th;
        }
    }

    /* JADX INFO: renamed from: drawRoundRectBorder-JqoCqck, reason: not valid java name */
    private final DrawResult m302drawRoundRectBorderJqoCqck(CacheDrawScope $this$drawRoundRectBorder_u2dJqoCqck, final Brush brush, Outline.Rounded outline, final long topLeft, final long borderSize, final boolean fillArea, final float strokeWidth) {
        if (!RoundRectKt.isSimple(outline.getRoundRect())) {
            if (this.borderCache == null) {
                this.borderCache = new BorderCache(null, null, null, null, 15, null);
            }
            BorderCache borderCache = this.borderCache;
            Intrinsics.checkNotNull(borderCache);
            Path path = borderCache.obtainPath();
            final Path roundedRectPath = BorderKt.createRoundRectPath(path, outline.getRoundRect(), strokeWidth, fillArea);
            return $this$drawRoundRectBorder_u2dJqoCqck.onDrawWithContent(new Function1() { // from class: androidx.compose.foundation.BorderModifierNode$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return BorderModifierNode.drawRoundRectBorder_JqoCqck$lambda$1(roundedRectPath, brush, (ContentDrawScope) obj);
                }
            });
        }
        final long cornerRadius = outline.getRoundRect().m5118getTopLeftCornerRadiuskKHJgLs();
        final float halfStroke = strokeWidth / 2.0f;
        final Stroke borderStroke = new Stroke(strokeWidth, 0.0f, 0, 0, null, 30, null);
        return $this$drawRoundRectBorder_u2dJqoCqck.onDrawWithContent(new Function1() { // from class: androidx.compose.foundation.BorderModifierNode$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return BorderModifierNode.drawRoundRectBorder_JqoCqck$lambda$0(fillArea, brush, cornerRadius, halfStroke, strokeWidth, topLeft, borderSize, borderStroke, (ContentDrawScope) obj);
            }
        });
    }

    static final Unit drawRoundRectBorder_JqoCqck$lambda$0(boolean $fillArea, Brush $brush, long $cornerRadius, float $halfStroke, float $strokeWidth, long $topLeft, long $borderSize, Stroke $borderStroke, ContentDrawScope $this$onDrawWithContent) throws Throwable {
        $this$onDrawWithContent.drawContent();
        if ($fillArea) {
            DrawScope.m5882drawRoundRectZuiqVtQ$default($this$onDrawWithContent, $brush, 0L, 0L, $cornerRadius, 0.0f, null, null, 0, 246, null);
        } else {
            int bits$iv$iv$iv = (int) ($cornerRadius >> 32);
            if (Float.intBitsToFloat(bits$iv$iv$iv) < $halfStroke) {
                ContentDrawScope $this$clipRect_u2drOu3jXo$iv = $this$onDrawWithContent;
                long arg0$iv = $this$onDrawWithContent.mo5887getSizeNHjbRc();
                int bits$iv$iv$iv2 = (int) (arg0$iv >> 32);
                float right$iv = Float.intBitsToFloat(bits$iv$iv$iv2) - $strokeWidth;
                long arg0$iv2 = $this$onDrawWithContent.mo5887getSizeNHjbRc();
                int bits$iv$iv$iv3 = (int) (4294967295L & arg0$iv2);
                float bottom$iv = Float.intBitsToFloat(bits$iv$iv$iv3) - $strokeWidth;
                int clipOp$iv = ClipOp.INSTANCE.m5301getDifferencertfAjoo();
                DrawContext $this$withTransform_u24lambda_u240$iv$iv = $this$clipRect_u2drOu3jXo$iv.getDrawContext();
                long previousSize$iv$iv = $this$withTransform_u24lambda_u240$iv$iv.mo5808getSizeNHjbRc();
                $this$withTransform_u24lambda_u240$iv$iv.getCanvas().save();
                try {
                    DrawTransform $this$clipRect_rOu3jXo_u24lambda_u240$iv = $this$withTransform_u24lambda_u240$iv$iv.getTransform();
                    $this$clipRect_rOu3jXo_u24lambda_u240$iv.mo5811clipRectN_I0leg($strokeWidth, $strokeWidth, right$iv, bottom$iv, clipOp$iv);
                    try {
                        DrawScope.m5882drawRoundRectZuiqVtQ$default($this$clipRect_u2drOu3jXo$iv, $brush, 0L, 0L, $cornerRadius, 0.0f, null, null, 0, 246, null);
                        $this$withTransform_u24lambda_u240$iv$iv.getCanvas().restore();
                        $this$withTransform_u24lambda_u240$iv$iv.mo5809setSizeuvyYCjk(previousSize$iv$iv);
                    } catch (Throwable th) {
                        th = th;
                        $this$withTransform_u24lambda_u240$iv$iv.getCanvas().restore();
                        $this$withTransform_u24lambda_u240$iv$iv.mo5809setSizeuvyYCjk(previousSize$iv$iv);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } else {
                DrawScope.m5882drawRoundRectZuiqVtQ$default($this$onDrawWithContent, $brush, $topLeft, $borderSize, BorderKt.m301shrinkKibmq7A($cornerRadius, $halfStroke), 0.0f, $borderStroke, null, 0, 208, null);
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit drawRoundRectBorder_JqoCqck$lambda$1(Path $roundedRectPath, Brush $brush, ContentDrawScope $this$onDrawWithContent) {
        $this$onDrawWithContent.drawContent();
        DrawScope.m5876drawPathGBMwjPU$default($this$onDrawWithContent, $roundedRectPath, $brush, 0.0f, null, null, 0, 60, null);
        return Unit.INSTANCE;
    }

    @Override // androidx.compose.ui.node.SemanticsModifierNode
    public void applySemantics(SemanticsPropertyReceiver $this$applySemantics) {
        SemanticsPropertiesKt.setShape($this$applySemantics, this.shape);
    }
}
