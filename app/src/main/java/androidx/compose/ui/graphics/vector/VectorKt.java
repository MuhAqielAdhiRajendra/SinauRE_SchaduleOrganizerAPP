package androidx.compose.ui.graphics.vector;

import androidx.compose.ui.graphics.BlendMode;
import androidx.compose.ui.graphics.BlendModeColorFilter;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.PathFillType;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.StrokeJoin;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: Vector.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000f\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\u001a(\u0010(\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0017\u0010)\u001a\u0013\u0012\u0004\u0012\u00020+\u0012\u0004\u0012\u00020,0*¢\u0006\u0002\b-H\u0086\b\u001a\u0016\u0010.\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\b\u0010/\u001a\u0004\u0018\u00010\u0001\u001a\u001b\u00100\u001a\u000201*\u00020!2\u0006\u00102\u001a\u00020!H\u0000¢\u0006\u0004\b3\u00104\u001a\u0013\u00105\u001a\u00020!*\u00020!H\u0000¢\u0006\u0004\b6\u00107\u001a\u000e\u00108\u001a\u000201*\u0004\u0018\u000109H\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0003X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0003X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0003X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0003X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0007\u001a\u00020\u0003X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\b\u001a\u00020\u0003X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\t\u001a\u00020\u0003X\u0086T¢\u0006\u0002\n\u0000\"\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u000e\u0010\u000f\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0010\u001a\u00020\u0003X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0011\u001a\u00020\u0003X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0012\u001a\u00020\u0003X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0013\u001a\u00020\u0003X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0014\u001a\u00020\u0003X\u0086T¢\u0006\u0002\n\u0000\"\u0013\u0010\u0015\u001a\u00020\u0016¢\u0006\n\n\u0002\u0010\u0019\u001a\u0004\b\u0017\u0010\u0018\"\u0013\u0010\u001a\u001a\u00020\u001b¢\u0006\n\n\u0002\u0010\u0019\u001a\u0004\b\u001c\u0010\u0018\"\u0013\u0010\u001d\u001a\u00020\u001e¢\u0006\n\n\u0002\u0010\u0019\u001a\u0004\b\u001f\u0010\u0018\"\u0013\u0010 \u001a\u00020!¢\u0006\n\n\u0002\u0010$\u001a\u0004\b\"\u0010#\"\u0013\u0010%\u001a\u00020&¢\u0006\n\n\u0002\u0010\u0019\u001a\u0004\b'\u0010\u0018¨\u0006:"}, d2 = {"DefaultGroupName", "", "DefaultRotation", "", "DefaultPivotX", "DefaultPivotY", "DefaultScaleX", "DefaultScaleY", "DefaultTranslationX", "DefaultTranslationY", "EmptyPath", "", "Landroidx/compose/ui/graphics/vector/PathNode;", "getEmptyPath", "()Ljava/util/List;", "DefaultPathName", "DefaultStrokeLineWidth", "DefaultStrokeLineMiter", "DefaultTrimPathStart", "DefaultTrimPathEnd", "DefaultTrimPathOffset", "DefaultStrokeLineCap", "Landroidx/compose/ui/graphics/StrokeCap;", "getDefaultStrokeLineCap", "()I", "I", "DefaultStrokeLineJoin", "Landroidx/compose/ui/graphics/StrokeJoin;", "getDefaultStrokeLineJoin", "DefaultTintBlendMode", "Landroidx/compose/ui/graphics/BlendMode;", "getDefaultTintBlendMode", "DefaultTintColor", "Landroidx/compose/ui/graphics/Color;", "getDefaultTintColor", "()J", "J", "DefaultFillType", "Landroidx/compose/ui/graphics/PathFillType;", "getDefaultFillType", "PathData", "block", "Lkotlin/Function1;", "Landroidx/compose/ui/graphics/vector/PathBuilder;", "", "Lkotlin/ExtensionFunctionType;", "addPathNodes", "pathStr", "rgbEqual", "", "other", "rgbEqual--OWjLjI", "(JJ)Z", "toOpaque", "toOpaque-8_81llA", "(J)J", "tintableWithAlphaMask", "Landroidx/compose/ui/graphics/ColorFilter;", "ui"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class VectorKt {
    public static final String DefaultGroupName = "";
    public static final String DefaultPathName = "";
    public static final float DefaultPivotX = 0.0f;
    public static final float DefaultPivotY = 0.0f;
    public static final float DefaultRotation = 0.0f;
    public static final float DefaultScaleX = 1.0f;
    public static final float DefaultScaleY = 1.0f;
    public static final float DefaultStrokeLineMiter = 4.0f;
    public static final float DefaultStrokeLineWidth = 0.0f;
    public static final float DefaultTranslationX = 0.0f;
    public static final float DefaultTranslationY = 0.0f;
    public static final float DefaultTrimPathEnd = 1.0f;
    public static final float DefaultTrimPathOffset = 0.0f;
    public static final float DefaultTrimPathStart = 0.0f;
    private static final List<PathNode> EmptyPath = CollectionsKt.emptyList();
    private static final int DefaultStrokeLineCap = StrokeCap.INSTANCE.m5687getButtKaPHkGw();
    private static final int DefaultStrokeLineJoin = StrokeJoin.INSTANCE.m5698getMiterLxFBmk8();
    private static final int DefaultTintBlendMode = BlendMode.INSTANCE.m5251getSrcIn0nO6VwU();
    private static final long DefaultTintColor = Color.INSTANCE.m5348getTransparent0d7_KjU();
    private static final int DefaultFillType = PathFillType.INSTANCE.m5610getNonZeroRgk1Os();

    public static final List<PathNode> getEmptyPath() {
        return EmptyPath;
    }

    public static final int getDefaultStrokeLineCap() {
        return DefaultStrokeLineCap;
    }

    public static final int getDefaultStrokeLineJoin() {
        return DefaultStrokeLineJoin;
    }

    public static final int getDefaultTintBlendMode() {
        return DefaultTintBlendMode;
    }

    public static final long getDefaultTintColor() {
        return DefaultTintColor;
    }

    public static final int getDefaultFillType() {
        return DefaultFillType;
    }

    public static final List<PathNode> PathData(Function1<? super PathBuilder, Unit> function1) {
        PathBuilder $this$PathData_u24lambda_u240 = new PathBuilder();
        function1.invoke($this$PathData_u24lambda_u240);
        return $this$PathData_u24lambda_u240.getNodes();
    }

    public static final List<PathNode> addPathNodes(String pathStr) {
        if (pathStr == null) {
            return EmptyPath;
        }
        return new PathParser().parsePathString(pathStr).toNodes();
    }

    /* JADX INFO: renamed from: rgbEqual--OWjLjI, reason: not valid java name */
    public static final boolean m6061rgbEqualOWjLjI(long $this$rgbEqual_u2d_u2dOWjLjI, long other) {
        if (Color.m5319getRedimpl($this$rgbEqual_u2d_u2dOWjLjI) == Color.m5319getRedimpl(other)) {
            if (Color.m5318getGreenimpl($this$rgbEqual_u2d_u2dOWjLjI) == Color.m5318getGreenimpl(other)) {
                if (Color.m5316getBlueimpl($this$rgbEqual_u2d_u2dOWjLjI) == Color.m5316getBlueimpl(other)) {
                    return true;
                }
            }
        }
        return false;
    }

    /* JADX INFO: renamed from: toOpaque-8_81llA, reason: not valid java name */
    public static final long m6062toOpaque8_81llA(long $this$toOpaque_u2d8_81llA) {
        if (Color.m5315getAlphaimpl($this$toOpaque_u2d8_81llA) == 1.0f) {
            return $this$toOpaque_u2d8_81llA;
        }
        return Color.m5311copywmQWz5c($this$toOpaque_u2d8_81llA, (14 & 1) != 0 ? Color.m5315getAlphaimpl($this$toOpaque_u2d8_81llA) : 1.0f, (14 & 2) != 0 ? Color.m5319getRedimpl($this$toOpaque_u2d8_81llA) : 0.0f, (14 & 4) != 0 ? Color.m5318getGreenimpl($this$toOpaque_u2d8_81llA) : 0.0f, (14 & 8) != 0 ? Color.m5316getBlueimpl($this$toOpaque_u2d8_81llA) : 0.0f);
    }

    public static final boolean tintableWithAlphaMask(ColorFilter $this$tintableWithAlphaMask) {
        return $this$tintableWithAlphaMask instanceof BlendModeColorFilter ? BlendMode.m5222equalsimpl0(((BlendModeColorFilter) $this$tintableWithAlphaMask).getBlendMode(), BlendMode.INSTANCE.m5251getSrcIn0nO6VwU()) || BlendMode.m5222equalsimpl0(((BlendModeColorFilter) $this$tintableWithAlphaMask).getBlendMode(), BlendMode.INSTANCE.m5253getSrcOver0nO6VwU()) : $this$tintableWithAlphaMask == null;
    }
}
