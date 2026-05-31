package androidx.compose.ui.graphics;

import android.graphics.Shader;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.util.MathHelpersKt;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Brush.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u00004\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0000\u001a2\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\b\u001a\u00020\tH\u0000\u001a8\u0010\n\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\u00042\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\u00042\u000e\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\u00042\u0006\u0010\b\u001a\u00020\tH\u0000\u001a2\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\t0\u00042\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\t0\u00042\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\u00042\u0006\u0010\b\u001a\u00020\tH\u0000\u001a'\u0010\f\u001a\u00020\r2\u0006\u0010\u0006\u001a\u00020\r2\u0006\u0010\u0007\u001a\u00020\r2\u0006\u0010\b\u001a\u00020\tH\u0000¢\u0006\u0004\b\u000e\u0010\u000f\u001a\u0017\u0010\u0010\u001a\u00020\u00012\n\u0010\u0011\u001a\u00060\u0012j\u0002`\u0013¢\u0006\u0002\u0010\u0014¨\u0006\u0015"}, d2 = {"toShaderBrush", "Landroidx/compose/ui/graphics/ShaderBrush;", "Landroidx/compose/ui/graphics/Brush;", "lerpColorList", "", "Landroidx/compose/ui/graphics/Color;", "left", "right", "t", "", "lerpNullableFloatList", "lerpFloatList", "lerpSafe", "Landroidx/compose/ui/geometry/Offset;", "lerpSafe-Wko1d7g", "(JJF)J", "ShaderBrush", "shader", "Landroid/graphics/Shader;", "Landroidx/compose/ui/graphics/Shader;", "(Landroid/graphics/Shader;)Landroidx/compose/ui/graphics/ShaderBrush;", "ui-graphics"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class BrushKt {
    public static final ShaderBrush toShaderBrush(Brush $this$toShaderBrush) {
        if ($this$toShaderBrush instanceof ShaderBrush) {
            return (ShaderBrush) $this$toShaderBrush;
        }
        if (!($this$toShaderBrush instanceof SolidColor)) {
            throw new NoWhenBranchMatchedException();
        }
        Brush brushM5268verticalGradient8A3gB4$default = Brush.Companion.m5268verticalGradient8A3gB4$default(Brush.INSTANCE, CollectionsKt.listOf((Object[]) new Color[]{Color.m5303boximpl(((SolidColor) $this$toShaderBrush).getValue()), Color.m5303boximpl(((SolidColor) $this$toShaderBrush).getValue())}), 0.0f, 0.0f, 0, 14, (Object) null);
        Intrinsics.checkNotNull(brushM5268verticalGradient8A3gB4$default, "null cannot be cast to non-null type androidx.compose.ui.graphics.ShaderBrush");
        return (ShaderBrush) brushM5268verticalGradient8A3gB4$default;
    }

    public static final List<Color> lerpColorList(List<Color> list, List<Color> list2, float t) {
        int iMax = Math.max(list.size(), list2.size());
        ArrayList arrayList = new ArrayList(iMax);
        for (int i = 0; i < iMax; i++) {
            int it = i;
            int l = Math.min(it, list.size() - 1);
            int r = Math.min(it, list2.size() - 1);
            arrayList.add(Color.m5303boximpl(ColorKt.m5364lerpjxsXWHM(list.get(l).m5323unboximpl(), list2.get(r).m5323unboximpl(), t)));
        }
        return arrayList;
    }

    public static final List<Float> lerpNullableFloatList(List<Float> list, List<Float> list2, float t) {
        if (list2 == null || list == null) {
            return null;
        }
        return lerpFloatList(list, list2, t);
    }

    public static final List<Float> lerpFloatList(List<Float> list, List<Float> list2, float t) {
        int iMax = Math.max(list.size(), list2.size());
        ArrayList arrayList = new ArrayList(iMax);
        for (int i = 0; i < iMax; i++) {
            int it = i;
            int l = Math.min(it, list.size() - 1);
            int r = Math.min(it, list2.size() - 1);
            arrayList.add(Float.valueOf(MathHelpersKt.lerp(list.get(l).floatValue(), list2.get(r).floatValue(), t)));
        }
        return arrayList;
    }

    /* JADX INFO: renamed from: lerpSafe-Wko1d7g, reason: not valid java name */
    public static final long m5281lerpSafeWko1d7g(long left, long right, float t) {
        long v$iv = (left & androidx.compose.ui.geometry.InlineClassHelperKt.DualFloatInfinityBase) ^ androidx.compose.ui.geometry.InlineClassHelperKt.DualFloatInfinityBase;
        if (((v$iv - androidx.compose.ui.geometry.InlineClassHelperKt.Uint64Low32) & (-9223372034707292160L)) == 0) {
            long v$iv2 = androidx.compose.ui.geometry.InlineClassHelperKt.DualFloatInfinityBase ^ (right & androidx.compose.ui.geometry.InlineClassHelperKt.DualFloatInfinityBase);
            if (((v$iv2 - androidx.compose.ui.geometry.InlineClassHelperKt.Uint64Low32) & (-9223372034707292160L)) == 0) {
                return OffsetKt.m5091lerpWko1d7g(left, right, t);
            }
        }
        return t < 0.5f ? left : right;
    }

    public static final ShaderBrush ShaderBrush(final Shader shader) {
        return new ShaderBrush() { // from class: androidx.compose.ui.graphics.BrushKt.ShaderBrush.1
            @Override // androidx.compose.ui.graphics.ShaderBrush
            /* JADX INFO: renamed from: createShader-uvyYCjk, reason: not valid java name */
            public Shader mo5282createShaderuvyYCjk(long size) {
                return shader;
            }
        };
    }
}
