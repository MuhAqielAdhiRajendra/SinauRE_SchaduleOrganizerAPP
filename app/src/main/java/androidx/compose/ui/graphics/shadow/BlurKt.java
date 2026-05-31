package androidx.compose.ui.graphics.shadow;

import android.graphics.BlurMaskFilter;
import androidx.compose.ui.graphics.Paint;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Metadata;

/* JADX INFO: compiled from: Blur.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000&\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001aC\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u0010\b\u0002\u0010\u0006\u001a\n\u0018\u00010\u0007j\u0004\u0018\u0001`\b2\b\b\u0002\u0010\t\u001a\u00020\nH\u0000¢\u0006\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"configureShadow", "Landroidx/compose/ui/graphics/Paint;", TypedValues.Custom.S_COLOR, "Landroidx/compose/ui/graphics/Color;", "blendMode", "Landroidx/compose/ui/graphics/BlendMode;", "blurFilter", "Landroid/graphics/BlurMaskFilter;", "Landroidx/compose/ui/graphics/shadow/BlurFilter;", "style", "Landroidx/compose/ui/graphics/PaintingStyle;", "configureShadow-FoewPVk", "(Landroidx/compose/ui/graphics/Paint;JILandroid/graphics/BlurMaskFilter;I)Landroidx/compose/ui/graphics/Paint;", "ui-graphics"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class BlurKt {
    /* JADX INFO: renamed from: configureShadow-FoewPVk, reason: not valid java name */
    public static final Paint m6021configureShadowFoewPVk(Paint $this$configureShadow_u2dFoewPVk, long color, int blendMode, BlurMaskFilter blurFilter, int style) {
        $this$configureShadow_u2dFoewPVk.mo5189setColor8_81llA(color);
        $this$configureShadow_u2dFoewPVk.mo5188setBlendModes9anfk8(blendMode);
        $this$configureShadow_u2dFoewPVk.mo5193setStylek9PVt8s(style);
        Blur_androidKt.setBlurFilter($this$configureShadow_u2dFoewPVk, blurFilter);
        return $this$configureShadow_u2dFoewPVk;
    }
}
