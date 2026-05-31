package androidx.compose.ui.draw;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.graphics.BlendMode;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.GraphicsLayerScopeKt;
import androidx.compose.ui.graphics.RectangleShapeKt;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.shadow.Shadow;
import androidx.compose.ui.unit.Dp;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: Shadow.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000H\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a/\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u0007¢\u0006\u0004\b\b\u0010\t\u001aC\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u000bH\u0007¢\u0006\u0004\b\r\u0010\u000e\u001a\u001c\u0010\u000f\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0000\u001a\u00020\u0010H\u0007\u001a-\u0010\u000f\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0017\u0010\u0011\u001a\u0013\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00140\u0012¢\u0006\u0002\b\u0015H\u0007\u001a\u001c\u0010\u0016\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0000\u001a\u00020\u0010H\u0007\u001a-\u0010\u0016\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0017\u0010\u0011\u001a\u0013\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00140\u0012¢\u0006\u0002\b\u0015H\u0007\u001a\f\u0010\u0018\u001a\u00020\u0014*\u00020\u0019H\u0002¨\u0006\u001a"}, d2 = {"shadow", "Landroidx/compose/ui/Modifier;", "elevation", "Landroidx/compose/ui/unit/Dp;", "shape", "Landroidx/compose/ui/graphics/Shape;", "clip", "", "shadow-ziNgDLE", "(Landroidx/compose/ui/Modifier;FLandroidx/compose/ui/graphics/Shape;Z)Landroidx/compose/ui/Modifier;", "ambientColor", "Landroidx/compose/ui/graphics/Color;", "spotColor", "shadow-s4CzXII", "(Landroidx/compose/ui/Modifier;FLandroidx/compose/ui/graphics/Shape;ZJJ)Landroidx/compose/ui/Modifier;", "dropShadow", "Landroidx/compose/ui/graphics/shadow/Shadow;", "block", "Lkotlin/Function1;", "Landroidx/compose/ui/draw/DropShadowScope;", "", "Lkotlin/ExtensionFunctionType;", "innerShadow", "Landroidx/compose/ui/draw/InnerShadowScope;", "resetShadow", "Landroidx/compose/ui/draw/ShadowScope;", "ui"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class ShadowKt {
    /* JADX INFO: renamed from: shadow-ziNgDLE$default, reason: not valid java name */
    public static /* synthetic */ Modifier m4913shadowziNgDLE$default(Modifier modifier, float f, Shape shape, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            shape = RectangleShapeKt.getRectangleShape();
        }
        if ((i & 4) != 0) {
            int $this$dp$iv = Dp.m8149compareTo0680j_4(f, Dp.m8150constructorimpl(0));
            z = $this$dp$iv > 0;
        }
        return m4912shadowziNgDLE(modifier, f, shape, z);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Replace with shadow which accepts ambientColor and spotColor parameters", replaceWith = @ReplaceWith(expression = "Modifier.shadow(elevation, shape, clip, DefaultShadowColor, DefaultShadowColor)", imports = {"androidx.compose.ui.draw"}))
    /* JADX INFO: renamed from: shadow-ziNgDLE, reason: not valid java name */
    public static final /* synthetic */ Modifier m4912shadowziNgDLE(Modifier $this$shadow_u2dziNgDLE, float elevation, Shape shape, boolean clip) {
        return m4910shadows4CzXII($this$shadow_u2dziNgDLE, elevation, shape, clip, GraphicsLayerScopeKt.getDefaultShadowColor(), GraphicsLayerScopeKt.getDefaultShadowColor());
    }

    /* JADX INFO: renamed from: shadow-s4CzXII$default, reason: not valid java name */
    public static /* synthetic */ Modifier m4911shadows4CzXII$default(Modifier modifier, float f, Shape shape, boolean z, long j, long j2, int i, Object obj) {
        boolean z2;
        Shape rectangleShape = (i & 2) != 0 ? RectangleShapeKt.getRectangleShape() : shape;
        if ((i & 4) != 0) {
            int $this$dp$iv = Dp.m8149compareTo0680j_4(f, Dp.m8150constructorimpl(0));
            z2 = $this$dp$iv > 0;
        } else {
            z2 = z;
        }
        return m4910shadows4CzXII(modifier, f, rectangleShape, z2, (i & 8) != 0 ? GraphicsLayerScopeKt.getDefaultShadowColor() : j, (i & 16) != 0 ? GraphicsLayerScopeKt.getDefaultShadowColor() : j2);
    }

    /* JADX INFO: renamed from: shadow-s4CzXII, reason: not valid java name */
    public static final Modifier m4910shadows4CzXII(Modifier $this$shadow_u2ds4CzXII, float elevation, Shape shape, boolean clip, long ambientColor, long spotColor) {
        int $this$dp$iv = Dp.m8149compareTo0680j_4(elevation, Dp.m8150constructorimpl(0));
        if ($this$dp$iv > 0 || clip) {
            return $this$shadow_u2ds4CzXII.then(new ShadowGraphicsLayerElement(elevation, shape, clip, ambientColor, spotColor, null));
        }
        return $this$shadow_u2ds4CzXII;
    }

    public static final Modifier dropShadow(Modifier $this$dropShadow, Shape shape, Shadow shadow) {
        return $this$dropShadow.then(new SimpleDropShadowElement(shape, shadow));
    }

    public static final Modifier dropShadow(Modifier $this$dropShadow, Shape shape, Function1<? super DropShadowScope, Unit> function1) {
        return $this$dropShadow.then(new BlockDropShadowElement(shape, function1));
    }

    public static final Modifier innerShadow(Modifier $this$innerShadow, Shape shape, Shadow shadow) {
        return $this$innerShadow.then(new SimpleInnerShadowElement(shape, shadow));
    }

    public static final Modifier innerShadow(Modifier $this$innerShadow, Shape shape, Function1<? super InnerShadowScope, Unit> function1) {
        return $this$innerShadow.then(new BlockInnerShadowElement(shape, function1));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void resetShadow(ShadowScope $this$resetShadow) {
        $this$resetShadow.setRadius(0.0f);
        $this$resetShadow.setSpread(0.0f);
        $this$resetShadow.mo4832setOffsetk4lQ0M(Offset.INSTANCE.m5084getZeroF1C5BW0());
        $this$resetShadow.mo4831setColor8_81llA(Color.INSTANCE.m5339getBlack0d7_KjU());
        $this$resetShadow.setBrush(null);
        $this$resetShadow.setAlpha(1.0f);
        $this$resetShadow.mo4830setBlendModes9anfk8(BlendMode.INSTANCE.m5253getSrcOver0nO6VwU());
    }
}
