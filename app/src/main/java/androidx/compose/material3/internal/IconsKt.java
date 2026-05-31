package androidx.compose.material3.internal;

import androidx.autofill.HintConstants;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.SolidColor;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.StrokeJoin;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.graphics.vector.PathBuilder;
import androidx.compose.ui.graphics.vector.VectorKt;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: Icons.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000>\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\u001a*\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0017\u0010\u0004\u001a\u0013\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\b\u0007H\u0082\b\u001a4\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\t2\u0017\u0010\u0004\u001a\u0013\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\b\u0007H\u0082\b\u001aK\u0010\n\u001a\u00020\u0006*\u00020\u00062\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\u0017\u0010\u0010\u001a\u0013\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00120\u0005¢\u0006\u0002\b\u0007H\u0082\b¢\u0006\u0004\b\u0013\u0010\u0014\"\u000e\u0010\u0015\u001a\u00020\fX\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"materialIcon", "Landroidx/compose/ui/graphics/vector/ImageVector;", HintConstants.AUTOFILL_HINT_NAME, "", "block", "Lkotlin/Function1;", "Landroidx/compose/ui/graphics/vector/ImageVector$Builder;", "Lkotlin/ExtensionFunctionType;", "autoMirror", "", "materialPath", "fillAlpha", "", "strokeAlpha", "pathFillType", "Landroidx/compose/ui/graphics/PathFillType;", "pathBuilder", "Landroidx/compose/ui/graphics/vector/PathBuilder;", "", "materialPath-YwgOQQI", "(Landroidx/compose/ui/graphics/vector/ImageVector$Builder;FFILkotlin/jvm/functions/Function1;)Landroidx/compose/ui/graphics/vector/ImageVector$Builder;", "MaterialIconDimension", "material3"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class IconsKt {
    private static final float MaterialIconDimension = 24.0f;

    private static final ImageVector materialIcon(String name, Function1<? super ImageVector.Builder, ImageVector.Builder> function1) {
        return function1.invoke(new ImageVector.Builder(name, Dp.m8150constructorimpl(24.0f), Dp.m8150constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 224, null)).build();
    }

    static /* synthetic */ ImageVector materialIcon$default(String name, boolean autoMirror, Function1 block, int i, Object obj) {
        boolean autoMirror2;
        if ((i & 2) == 0) {
            autoMirror2 = autoMirror;
        } else {
            autoMirror2 = false;
        }
        return ((ImageVector.Builder) block.invoke(new ImageVector.Builder(name, Dp.m8150constructorimpl(24.0f), Dp.m8150constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, autoMirror2, 96, null))).build();
    }

    private static final ImageVector materialIcon(String name, boolean autoMirror, Function1<? super ImageVector.Builder, ImageVector.Builder> function1) {
        return function1.invoke(new ImageVector.Builder(name, Dp.m8150constructorimpl(24.0f), Dp.m8150constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, autoMirror, 96, null)).build();
    }

    /* JADX INFO: renamed from: materialPath-YwgOQQI$default, reason: not valid java name */
    static /* synthetic */ ImageVector.Builder m3451materialPathYwgOQQI$default(ImageVector.Builder $this$materialPath_u2dYwgOQQI_u24default, float fillAlpha, float strokeAlpha, int pathFillType, Function1 pathBuilder, int i, Object obj) {
        float fillAlpha2 = (i & 1) != 0 ? 1.0f : fillAlpha;
        float strokeAlpha2 = (i & 2) != 0 ? 1.0f : strokeAlpha;
        int pathFillType2 = (i & 4) != 0 ? VectorKt.getDefaultFillType() : pathFillType;
        Brush fill$iv = new SolidColor(Color.INSTANCE.m5339getBlack0d7_KjU(), null);
        int strokeLineCap$iv = StrokeCap.INSTANCE.m5687getButtKaPHkGw();
        int strokeLineJoin$iv = StrokeJoin.INSTANCE.m5697getBevelLxFBmk8();
        PathBuilder $this$PathData_u24lambda_u240$iv$iv = new PathBuilder();
        pathBuilder.invoke($this$PathData_u24lambda_u240$iv$iv);
        return ImageVector.Builder.m6044addPathoIyEayM$default($this$materialPath_u2dYwgOQQI_u24default, $this$PathData_u24lambda_u240$iv$iv.getNodes(), pathFillType2, "", fill$iv, fillAlpha2, null, strokeAlpha2, 1.0f, strokeLineCap$iv, strokeLineJoin$iv, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
    }

    /* JADX INFO: renamed from: materialPath-YwgOQQI, reason: not valid java name */
    private static final ImageVector.Builder m3450materialPathYwgOQQI(ImageVector.Builder $this$materialPath_u2dYwgOQQI, float fillAlpha, float strokeAlpha, int pathFillType, Function1<? super PathBuilder, Unit> function1) {
        Brush fill$iv = new SolidColor(Color.INSTANCE.m5339getBlack0d7_KjU(), null);
        int strokeLineCap$iv = StrokeCap.INSTANCE.m5687getButtKaPHkGw();
        int strokeLineJoin$iv = StrokeJoin.INSTANCE.m5697getBevelLxFBmk8();
        PathBuilder $this$PathData_u24lambda_u240$iv$iv = new PathBuilder();
        function1.invoke($this$PathData_u24lambda_u240$iv$iv);
        return ImageVector.Builder.m6044addPathoIyEayM$default($this$materialPath_u2dYwgOQQI, $this$PathData_u24lambda_u240$iv$iv.getNodes(), pathFillType, "", fill$iv, fillAlpha, null, strokeAlpha, 1.0f, strokeLineCap$iv, strokeLineJoin$iv, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
    }
}
