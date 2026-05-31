package androidx.compose.ui.graphics.drawscope;

import androidx.compose.ui.geometry.CornerRadius;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.geometry.SizeKt;
import androidx.compose.ui.graphics.BlendMode;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.FilterQuality;
import androidx.compose.ui.graphics.ImageBitmap;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.PathEffect;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.layer.GraphicsLayer;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.DpRect;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.IntSizeKt;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: DrawScope.kt */
/* JADX INFO: loaded from: classes12.dex */
@DrawScopeMarker
@Metadata(d1 = {"\u0000À\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\bg\u0018\u0000 u2\u00020\u0001:\u0001uJg\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00072\u0006\u0010\u0016\u001a\u00020\u00072\b\b\u0002\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010\u0019\u001a\u00020\u001a2\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\b\b\u0003\u0010\u001d\u001a\u00020\u00182\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\b\u0002\u0010 \u001a\u00020!H&¢\u0006\u0004\b\"\u0010#Jg\u0010\u0011\u001a\u00020\u00122\u0006\u0010$\u001a\u00020%2\u0006\u0010\u0015\u001a\u00020\u00072\u0006\u0010\u0016\u001a\u00020\u00072\b\b\u0002\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010\u0019\u001a\u00020\u001a2\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\b\b\u0003\u0010\u001d\u001a\u00020\u00182\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\b\u0002\u0010 \u001a\u00020!H&¢\u0006\u0004\b&\u0010'JU\u0010(\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010)\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0003\u0010\u001d\u001a\u00020\u00182\b\b\u0002\u0010*\u001a\u00020+2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\b\u0002\u0010 \u001a\u00020!H&¢\u0006\u0004\b,\u0010-JU\u0010(\u001a\u00020\u00122\u0006\u0010$\u001a\u00020%2\b\b\u0002\u0010)\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0003\u0010\u001d\u001a\u00020\u00182\b\b\u0002\u0010*\u001a\u00020+2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\b\u0002\u0010 \u001a\u00020!H&¢\u0006\u0004\b.\u0010/JK\u00100\u001a\u00020\u00122\u0006\u00101\u001a\u0002022\b\b\u0002\u0010)\u001a\u00020\u00072\b\b\u0003\u0010\u001d\u001a\u00020\u00182\b\b\u0002\u0010*\u001a\u00020+2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\b\u0002\u0010 \u001a\u00020!H&¢\u0006\u0004\b3\u00104Ji\u00100\u001a\u00020\u00122\u0006\u00101\u001a\u0002022\b\b\u0002\u00105\u001a\u0002062\b\b\u0002\u00107\u001a\u0002082\b\b\u0002\u00109\u001a\u0002062\b\b\u0002\u0010:\u001a\u0002082\b\b\u0003\u0010\u001d\u001a\u00020\u00182\b\b\u0002\u0010*\u001a\u00020+2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\b\u0002\u0010 \u001a\u00020!H'¢\u0006\u0004\b;\u0010<Js\u00100\u001a\u00020\u00122\u0006\u00101\u001a\u0002022\b\b\u0002\u00105\u001a\u0002062\b\b\u0002\u00107\u001a\u0002082\b\b\u0002\u00109\u001a\u0002062\b\b\u0002\u0010:\u001a\u0002082\b\b\u0003\u0010\u001d\u001a\u00020\u00182\b\b\u0002\u0010*\u001a\u00020+2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\b\u0002\u0010 \u001a\u00020!2\b\b\u0002\u0010=\u001a\u00020>H\u0016¢\u0006\u0004\b?\u0010@J_\u0010A\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010)\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010B\u001a\u00020C2\b\b\u0003\u0010\u001d\u001a\u00020\u00182\b\b\u0002\u0010*\u001a\u00020+2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\b\u0002\u0010 \u001a\u00020!H&¢\u0006\u0004\bD\u0010EJ_\u0010A\u001a\u00020\u00122\u0006\u0010$\u001a\u00020%2\b\b\u0002\u0010)\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010B\u001a\u00020C2\b\b\u0002\u0010*\u001a\u00020+2\b\b\u0003\u0010\u001d\u001a\u00020\u00182\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\b\u0002\u0010 \u001a\u00020!H&¢\u0006\u0004\bF\u0010GJU\u0010H\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010I\u001a\u00020\u00182\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0003\u0010\u001d\u001a\u00020\u00182\b\b\u0002\u0010*\u001a\u00020+2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\b\u0002\u0010 \u001a\u00020!H&¢\u0006\u0004\bJ\u0010KJU\u0010H\u001a\u00020\u00122\u0006\u0010$\u001a\u00020%2\b\b\u0002\u0010I\u001a\u00020\u00182\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0003\u0010\u001d\u001a\u00020\u00182\b\b\u0002\u0010*\u001a\u00020+2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\b\u0002\u0010 \u001a\u00020!H&¢\u0006\u0004\bL\u0010MJU\u0010N\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010)\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0003\u0010\u001d\u001a\u00020\u00182\b\b\u0002\u0010*\u001a\u00020+2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\b\u0002\u0010 \u001a\u00020!H&¢\u0006\u0004\bO\u0010-JU\u0010N\u001a\u00020\u00122\u0006\u0010$\u001a\u00020%2\b\b\u0002\u0010)\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0003\u0010\u001d\u001a\u00020\u00182\b\b\u0002\u0010*\u001a\u00020+2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\b\u0002\u0010 \u001a\u00020!H&¢\u0006\u0004\bP\u0010/Jm\u0010Q\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010R\u001a\u00020\u00182\u0006\u0010S\u001a\u00020\u00182\u0006\u0010T\u001a\u00020U2\b\b\u0002\u0010)\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0003\u0010\u001d\u001a\u00020\u00182\b\b\u0002\u0010*\u001a\u00020+2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\b\u0002\u0010 \u001a\u00020!H&¢\u0006\u0004\bV\u0010WJm\u0010Q\u001a\u00020\u00122\u0006\u0010$\u001a\u00020%2\u0006\u0010R\u001a\u00020\u00182\u0006\u0010S\u001a\u00020\u00182\u0006\u0010T\u001a\u00020U2\b\b\u0002\u0010)\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0003\u0010\u001d\u001a\u00020\u00182\b\b\u0002\u0010*\u001a\u00020+2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\b\u0002\u0010 \u001a\u00020!H&¢\u0006\u0004\bX\u0010YJI\u0010Z\u001a\u00020\u00122\u0006\u0010[\u001a\u00020\\2\u0006\u0010$\u001a\u00020%2\b\b\u0003\u0010\u001d\u001a\u00020\u00182\b\b\u0002\u0010*\u001a\u00020+2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\b\u0002\u0010 \u001a\u00020!H&¢\u0006\u0004\b]\u0010^JI\u0010Z\u001a\u00020\u00122\u0006\u0010[\u001a\u00020\\2\u0006\u0010\u0013\u001a\u00020\u00142\b\b\u0003\u0010\u001d\u001a\u00020\u00182\b\b\u0002\u0010*\u001a\u00020+2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\b\u0002\u0010 \u001a\u00020!H&¢\u0006\u0004\b_\u0010`Jm\u0010a\u001a\u00020\u00122\f\u0010b\u001a\b\u0012\u0004\u0012\u00020\u00070c2\u0006\u0010d\u001a\u00020e2\u0006\u0010$\u001a\u00020%2\b\b\u0002\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010\u0019\u001a\u00020\u001a2\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\b\b\u0003\u0010\u001d\u001a\u00020\u00182\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\b\u0002\u0010 \u001a\u00020!H&¢\u0006\u0004\bf\u0010gJm\u0010a\u001a\u00020\u00122\f\u0010b\u001a\b\u0012\u0004\u0012\u00020\u00070c2\u0006\u0010d\u001a\u00020e2\u0006\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010\u0019\u001a\u00020\u001a2\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\b\b\u0003\u0010\u001d\u001a\u00020\u00182\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\b\u0002\u0010 \u001a\u00020!H&¢\u0006\u0004\bh\u0010iJ6\u0010j\u001a\u00020\u0012*\u00020k2\b\b\u0002\u0010\n\u001a\u0002082\u0017\u0010l\u001a\u0013\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00120m¢\u0006\u0002\bnH\u0016¢\u0006\u0004\bo\u0010pJ\u001b\u0010q\u001a\u00020\u000b*\u00020\u000b2\u0006\u0010r\u001a\u00020\u0007H\u0002¢\u0006\u0004\bs\u0010tR\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0014\u0010\u0006\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u00020\u000b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\tR\u0012\u0010\r\u001a\u00020\u000eX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006vÀ\u0006\u0003"}, d2 = {"Landroidx/compose/ui/graphics/drawscope/DrawScope;", "Landroidx/compose/ui/unit/Density;", "drawContext", "Landroidx/compose/ui/graphics/drawscope/DrawContext;", "getDrawContext", "()Landroidx/compose/ui/graphics/drawscope/DrawContext;", "center", "Landroidx/compose/ui/geometry/Offset;", "getCenter-F1C5BW0", "()J", "size", "Landroidx/compose/ui/geometry/Size;", "getSize-NH-jbRc", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "getLayoutDirection", "()Landroidx/compose/ui/unit/LayoutDirection;", "drawLine", "", "brush", "Landroidx/compose/ui/graphics/Brush;", "start", "end", "strokeWidth", "", "cap", "Landroidx/compose/ui/graphics/StrokeCap;", "pathEffect", "Landroidx/compose/ui/graphics/PathEffect;", "alpha", "colorFilter", "Landroidx/compose/ui/graphics/ColorFilter;", "blendMode", "Landroidx/compose/ui/graphics/BlendMode;", "drawLine-1RTmtNc", "(Landroidx/compose/ui/graphics/Brush;JJFILandroidx/compose/ui/graphics/PathEffect;FLandroidx/compose/ui/graphics/ColorFilter;I)V", TypedValues.Custom.S_COLOR, "Landroidx/compose/ui/graphics/Color;", "drawLine-NGM6Ib0", "(JJJFILandroidx/compose/ui/graphics/PathEffect;FLandroidx/compose/ui/graphics/ColorFilter;I)V", "drawRect", "topLeft", "style", "Landroidx/compose/ui/graphics/drawscope/DrawStyle;", "drawRect-AsUm42w", "(Landroidx/compose/ui/graphics/Brush;JJFLandroidx/compose/ui/graphics/drawscope/DrawStyle;Landroidx/compose/ui/graphics/ColorFilter;I)V", "drawRect-n-J9OG0", "(JJJFLandroidx/compose/ui/graphics/drawscope/DrawStyle;Landroidx/compose/ui/graphics/ColorFilter;I)V", "drawImage", "image", "Landroidx/compose/ui/graphics/ImageBitmap;", "drawImage-gbVJVH8", "(Landroidx/compose/ui/graphics/ImageBitmap;JFLandroidx/compose/ui/graphics/drawscope/DrawStyle;Landroidx/compose/ui/graphics/ColorFilter;I)V", "srcOffset", "Landroidx/compose/ui/unit/IntOffset;", "srcSize", "Landroidx/compose/ui/unit/IntSize;", "dstOffset", "dstSize", "drawImage-9jGpkUE", "(Landroidx/compose/ui/graphics/ImageBitmap;JJJJFLandroidx/compose/ui/graphics/drawscope/DrawStyle;Landroidx/compose/ui/graphics/ColorFilter;I)V", "filterQuality", "Landroidx/compose/ui/graphics/FilterQuality;", "drawImage-AZ2fEMs", "(Landroidx/compose/ui/graphics/ImageBitmap;JJJJFLandroidx/compose/ui/graphics/drawscope/DrawStyle;Landroidx/compose/ui/graphics/ColorFilter;II)V", "drawRoundRect", "cornerRadius", "Landroidx/compose/ui/geometry/CornerRadius;", "drawRoundRect-ZuiqVtQ", "(Landroidx/compose/ui/graphics/Brush;JJJFLandroidx/compose/ui/graphics/drawscope/DrawStyle;Landroidx/compose/ui/graphics/ColorFilter;I)V", "drawRoundRect-u-Aw5IA", "(JJJJLandroidx/compose/ui/graphics/drawscope/DrawStyle;FLandroidx/compose/ui/graphics/ColorFilter;I)V", "drawCircle", "radius", "drawCircle-V9BoPsw", "(Landroidx/compose/ui/graphics/Brush;FJFLandroidx/compose/ui/graphics/drawscope/DrawStyle;Landroidx/compose/ui/graphics/ColorFilter;I)V", "drawCircle-VaOC9Bg", "(JFJFLandroidx/compose/ui/graphics/drawscope/DrawStyle;Landroidx/compose/ui/graphics/ColorFilter;I)V", "drawOval", "drawOval-AsUm42w", "drawOval-n-J9OG0", "drawArc", "startAngle", "sweepAngle", "useCenter", "", "drawArc-illE91I", "(Landroidx/compose/ui/graphics/Brush;FFZJJFLandroidx/compose/ui/graphics/drawscope/DrawStyle;Landroidx/compose/ui/graphics/ColorFilter;I)V", "drawArc-yD3GUKo", "(JFFZJJFLandroidx/compose/ui/graphics/drawscope/DrawStyle;Landroidx/compose/ui/graphics/ColorFilter;I)V", "drawPath", "path", "Landroidx/compose/ui/graphics/Path;", "drawPath-LG529CI", "(Landroidx/compose/ui/graphics/Path;JFLandroidx/compose/ui/graphics/drawscope/DrawStyle;Landroidx/compose/ui/graphics/ColorFilter;I)V", "drawPath-GBMwjPU", "(Landroidx/compose/ui/graphics/Path;Landroidx/compose/ui/graphics/Brush;FLandroidx/compose/ui/graphics/drawscope/DrawStyle;Landroidx/compose/ui/graphics/ColorFilter;I)V", "drawPoints", "points", "", "pointMode", "Landroidx/compose/ui/graphics/PointMode;", "drawPoints-F8ZwMP8", "(Ljava/util/List;IJFILandroidx/compose/ui/graphics/PathEffect;FLandroidx/compose/ui/graphics/ColorFilter;I)V", "drawPoints-Gsft0Ws", "(Ljava/util/List;ILandroidx/compose/ui/graphics/Brush;FILandroidx/compose/ui/graphics/PathEffect;FLandroidx/compose/ui/graphics/ColorFilter;I)V", "record", "Landroidx/compose/ui/graphics/layer/GraphicsLayer;", "block", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "record-JVtK1S4", "(Landroidx/compose/ui/graphics/layer/GraphicsLayer;JLkotlin/jvm/functions/Function1;)V", "offsetSize", TypedValues.CycleType.S_WAVE_OFFSET, "offsetSize-PENXr5M", "(JJ)J", "Companion", "ui-graphics"}, k = 1, mv = {2, 1, 0}, xi = 48)
public interface DrawScope extends Density {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    /* JADX INFO: renamed from: drawArc-illE91I */
    void mo5784drawArcillE91I(Brush brush, float startAngle, float sweepAngle, boolean useCenter, long topLeft, long size, float alpha, DrawStyle style, ColorFilter colorFilter, int blendMode);

    /* JADX INFO: renamed from: drawArc-yD3GUKo */
    void mo5785drawArcyD3GUKo(long color, float startAngle, float sweepAngle, boolean useCenter, long topLeft, long size, float alpha, DrawStyle style, ColorFilter colorFilter, int blendMode);

    /* JADX INFO: renamed from: drawCircle-V9BoPsw */
    void mo5786drawCircleV9BoPsw(Brush brush, float radius, long center, float alpha, DrawStyle style, ColorFilter colorFilter, int blendMode);

    /* JADX INFO: renamed from: drawCircle-VaOC9Bg */
    void mo5787drawCircleVaOC9Bg(long color, float radius, long center, float alpha, DrawStyle style, ColorFilter colorFilter, int blendMode);

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Prefer usage of drawImage that consumes an optional FilterQuality parameter", replaceWith = @ReplaceWith(expression = "drawImage(image, srcOffset, srcSize, dstOffset, dstSize, alpha, style, colorFilter, blendMode, FilterQuality.Low)", imports = {"androidx.compose.ui.graphics.drawscope", "androidx.compose.ui.graphics.FilterQuality"}))
    /* JADX INFO: renamed from: drawImage-9jGpkUE */
    /* synthetic */ void mo5788drawImage9jGpkUE(ImageBitmap image, long srcOffset, long srcSize, long dstOffset, long dstSize, float alpha, DrawStyle style, ColorFilter colorFilter, int blendMode);

    /* JADX INFO: renamed from: drawImage-gbVJVH8 */
    void mo5790drawImagegbVJVH8(ImageBitmap image, long topLeft, float alpha, DrawStyle style, ColorFilter colorFilter, int blendMode);

    /* JADX INFO: renamed from: drawLine-1RTmtNc */
    void mo5791drawLine1RTmtNc(Brush brush, long start, long end, float strokeWidth, int cap, PathEffect pathEffect, float alpha, ColorFilter colorFilter, int blendMode);

    /* JADX INFO: renamed from: drawLine-NGM6Ib0 */
    void mo5792drawLineNGM6Ib0(long color, long start, long end, float strokeWidth, int cap, PathEffect pathEffect, float alpha, ColorFilter colorFilter, int blendMode);

    /* JADX INFO: renamed from: drawOval-AsUm42w */
    void mo5793drawOvalAsUm42w(Brush brush, long topLeft, long size, float alpha, DrawStyle style, ColorFilter colorFilter, int blendMode);

    /* JADX INFO: renamed from: drawOval-n-J9OG0 */
    void mo5794drawOvalnJ9OG0(long color, long topLeft, long size, float alpha, DrawStyle style, ColorFilter colorFilter, int blendMode);

    /* JADX INFO: renamed from: drawPath-GBMwjPU */
    void mo5795drawPathGBMwjPU(Path path, Brush brush, float alpha, DrawStyle style, ColorFilter colorFilter, int blendMode);

    /* JADX INFO: renamed from: drawPath-LG529CI */
    void mo5796drawPathLG529CI(Path path, long color, float alpha, DrawStyle style, ColorFilter colorFilter, int blendMode);

    /* JADX INFO: renamed from: drawPoints-F8ZwMP8 */
    void mo5797drawPointsF8ZwMP8(List<Offset> points, int pointMode, long color, float strokeWidth, int cap, PathEffect pathEffect, float alpha, ColorFilter colorFilter, int blendMode);

    /* JADX INFO: renamed from: drawPoints-Gsft0Ws */
    void mo5798drawPointsGsft0Ws(List<Offset> points, int pointMode, Brush brush, float strokeWidth, int cap, PathEffect pathEffect, float alpha, ColorFilter colorFilter, int blendMode);

    /* JADX INFO: renamed from: drawRect-AsUm42w */
    void mo5799drawRectAsUm42w(Brush brush, long topLeft, long size, float alpha, DrawStyle style, ColorFilter colorFilter, int blendMode);

    /* JADX INFO: renamed from: drawRect-n-J9OG0 */
    void mo5800drawRectnJ9OG0(long color, long topLeft, long size, float alpha, DrawStyle style, ColorFilter colorFilter, int blendMode);

    /* JADX INFO: renamed from: drawRoundRect-ZuiqVtQ */
    void mo5801drawRoundRectZuiqVtQ(Brush brush, long topLeft, long size, long cornerRadius, float alpha, DrawStyle style, ColorFilter colorFilter, int blendMode);

    /* JADX INFO: renamed from: drawRoundRect-u-Aw5IA */
    void mo5802drawRoundRectuAw5IA(long color, long topLeft, long size, long cornerRadius, DrawStyle style, float alpha, ColorFilter colorFilter, int blendMode);

    DrawContext getDrawContext();

    LayoutDirection getLayoutDirection();

    /* JADX INFO: compiled from: DrawScope.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    public static final class DefaultImpls {
        @Deprecated
        /* JADX INFO: renamed from: roundToPx--R2X_6o, reason: not valid java name */
        public static int m5915roundToPxR2X_6o(DrawScope $this, long $receiver) {
            return DrawScope.super.mo425roundToPxR2X_6o($receiver);
        }

        @Deprecated
        /* JADX INFO: renamed from: roundToPx-0680j_4, reason: not valid java name */
        public static int m5916roundToPx0680j_4(DrawScope $this, float $receiver) {
            return DrawScope.super.mo426roundToPx0680j_4($receiver);
        }

        @Deprecated
        /* JADX INFO: renamed from: toDp-GaN1DYA, reason: not valid java name */
        public static float m5917toDpGaN1DYA(DrawScope $this, long $receiver) {
            return DrawScope.super.mo427toDpGaN1DYA($receiver);
        }

        @Deprecated
        /* JADX INFO: renamed from: toDp-u2uoSUM, reason: not valid java name */
        public static float m5918toDpu2uoSUM(DrawScope $this, float $receiver) {
            return DrawScope.super.mo428toDpu2uoSUM($receiver);
        }

        @Deprecated
        /* JADX INFO: renamed from: toDp-u2uoSUM, reason: not valid java name */
        public static float m5919toDpu2uoSUM(DrawScope $this, int $receiver) {
            return DrawScope.super.mo429toDpu2uoSUM($receiver);
        }

        @Deprecated
        /* JADX INFO: renamed from: toDpSize-k-rfVVM, reason: not valid java name */
        public static long m5920toDpSizekrfVVM(DrawScope $this, long $receiver) {
            return DrawScope.super.mo430toDpSizekrfVVM($receiver);
        }

        @Deprecated
        /* JADX INFO: renamed from: toPx--R2X_6o, reason: not valid java name */
        public static float m5921toPxR2X_6o(DrawScope $this, long $receiver) {
            return DrawScope.super.mo431toPxR2X_6o($receiver);
        }

        @Deprecated
        /* JADX INFO: renamed from: toPx-0680j_4, reason: not valid java name */
        public static float m5922toPx0680j_4(DrawScope $this, float $receiver) {
            return DrawScope.super.mo432toPx0680j_4($receiver);
        }

        @Deprecated
        public static Rect toRect(DrawScope $this, DpRect $receiver) {
            return DrawScope.super.toRect($receiver);
        }

        @Deprecated
        /* JADX INFO: renamed from: toSize-XkaWNTQ, reason: not valid java name */
        public static long m5923toSizeXkaWNTQ(DrawScope $this, long $receiver) {
            return DrawScope.super.mo433toSizeXkaWNTQ($receiver);
        }

        @Deprecated
        /* JADX INFO: renamed from: toSp-0xMU5do, reason: not valid java name */
        public static long m5924toSp0xMU5do(DrawScope $this, float $receiver) {
            return DrawScope.super.mo434toSp0xMU5do($receiver);
        }

        @Deprecated
        /* JADX INFO: renamed from: toSp-kPz2Gy4, reason: not valid java name */
        public static long m5925toSpkPz2Gy4(DrawScope $this, float $receiver) {
            return DrawScope.super.mo435toSpkPz2Gy4($receiver);
        }

        @Deprecated
        /* JADX INFO: renamed from: toSp-kPz2Gy4, reason: not valid java name */
        public static long m5926toSpkPz2Gy4(DrawScope $this, int $receiver) {
            return DrawScope.super.mo436toSpkPz2Gy4($receiver);
        }

        @Deprecated
        /* JADX INFO: renamed from: getCenter-F1C5BW0, reason: not valid java name */
        public static long m5911getCenterF1C5BW0(DrawScope $this) {
            return DrawScope.super.mo5886getCenterF1C5BW0();
        }

        @Deprecated
        /* JADX INFO: renamed from: getSize-NH-jbRc, reason: not valid java name */
        public static long m5912getSizeNHjbRc(DrawScope $this) {
            return DrawScope.super.mo5887getSizeNHjbRc();
        }

        @Deprecated
        /* JADX INFO: renamed from: drawImage-AZ2fEMs, reason: not valid java name */
        public static void m5896drawImageAZ2fEMs(DrawScope $this, ImageBitmap image, long srcOffset, long srcSize, long dstOffset, long dstSize, float alpha, DrawStyle style, ColorFilter colorFilter, int blendMode, int filterQuality) {
            DrawScope.super.mo5789drawImageAZ2fEMs(image, srcOffset, srcSize, dstOffset, dstSize, alpha, style, colorFilter, blendMode, filterQuality);
        }

        @Deprecated
        /* JADX INFO: renamed from: record-JVtK1S4, reason: not valid java name */
        public static void m5913recordJVtK1S4(DrawScope $this, GraphicsLayer $receiver, long size, Function1<? super DrawScope, Unit> function1) {
            DrawScope.super.mo5888recordJVtK1S4($receiver, size, function1);
        }
    }

    /* JADX INFO: renamed from: getCenter-F1C5BW0, reason: not valid java name */
    default long mo5886getCenterF1C5BW0() {
        return SizeKt.m5147getCenteruvyYCjk(getDrawContext().mo5808getSizeNHjbRc());
    }

    /* JADX INFO: renamed from: getSize-NH-jbRc, reason: not valid java name */
    default long mo5887getSizeNHjbRc() {
        return getDrawContext().mo5808getSizeNHjbRc();
    }

    /* JADX INFO: renamed from: drawLine-1RTmtNc$default, reason: not valid java name */
    static /* synthetic */ void m5872drawLine1RTmtNc$default(DrawScope drawScope, Brush brush, long j, long j2, float f, int i, PathEffect pathEffect, float f2, ColorFilter colorFilter, int i2, int i3, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: drawLine-1RTmtNc");
        }
        drawScope.mo5791drawLine1RTmtNc(brush, j, j2, (i3 & 8) != 0 ? 0.0f : f, (i3 & 16) != 0 ? Stroke.INSTANCE.m5958getDefaultCapKaPHkGw() : i, (i3 & 32) != 0 ? null : pathEffect, (i3 & 64) != 0 ? 1.0f : f2, (i3 & 128) != 0 ? null : colorFilter, (i3 & 256) != 0 ? INSTANCE.m5889getDefaultBlendMode0nO6VwU() : i2);
    }

    /* JADX INFO: renamed from: drawLine-NGM6Ib0$default, reason: not valid java name */
    static /* synthetic */ void m5873drawLineNGM6Ib0$default(DrawScope drawScope, long j, long j2, long j3, float f, int i, PathEffect pathEffect, float f2, ColorFilter colorFilter, int i2, int i3, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: drawLine-NGM6Ib0");
        }
        drawScope.mo5792drawLineNGM6Ib0(j, j2, j3, (i3 & 8) != 0 ? 0.0f : f, (i3 & 16) != 0 ? Stroke.INSTANCE.m5958getDefaultCapKaPHkGw() : i, (i3 & 32) != 0 ? null : pathEffect, (i3 & 64) != 0 ? 1.0f : f2, (i3 & 128) != 0 ? null : colorFilter, (i3 & 256) != 0 ? INSTANCE.m5889getDefaultBlendMode0nO6VwU() : i2);
    }

    /* JADX INFO: renamed from: drawRect-AsUm42w$default, reason: not valid java name */
    static /* synthetic */ void m5880drawRectAsUm42w$default(DrawScope drawScope, Brush brush, long j, long j2, float f, DrawStyle drawStyle, ColorFilter colorFilter, int i, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: drawRect-AsUm42w");
        }
        long jM5084getZeroF1C5BW0 = (i2 & 2) != 0 ? Offset.INSTANCE.m5084getZeroF1C5BW0() : j;
        drawScope.mo5799drawRectAsUm42w(brush, jM5084getZeroF1C5BW0, (i2 & 4) != 0 ? drawScope.m5884offsetSizePENXr5M(drawScope.mo5887getSizeNHjbRc(), jM5084getZeroF1C5BW0) : j2, (i2 & 8) != 0 ? 1.0f : f, (i2 & 16) != 0 ? Fill.INSTANCE : drawStyle, (i2 & 32) != 0 ? null : colorFilter, (i2 & 64) != 0 ? INSTANCE.m5889getDefaultBlendMode0nO6VwU() : i);
    }

    /* JADX INFO: renamed from: drawRect-n-J9OG0$default, reason: not valid java name */
    static /* synthetic */ void m5881drawRectnJ9OG0$default(DrawScope drawScope, long j, long j2, long j3, float f, DrawStyle drawStyle, ColorFilter colorFilter, int i, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: drawRect-n-J9OG0");
        }
        long jM5084getZeroF1C5BW0 = (i2 & 2) != 0 ? Offset.INSTANCE.m5084getZeroF1C5BW0() : j2;
        drawScope.mo5800drawRectnJ9OG0(j, jM5084getZeroF1C5BW0, (i2 & 4) != 0 ? drawScope.m5884offsetSizePENXr5M(drawScope.mo5887getSizeNHjbRc(), jM5084getZeroF1C5BW0) : j3, (i2 & 8) != 0 ? 1.0f : f, (i2 & 16) != 0 ? Fill.INSTANCE : drawStyle, (i2 & 32) != 0 ? null : colorFilter, (i2 & 64) != 0 ? INSTANCE.m5889getDefaultBlendMode0nO6VwU() : i);
    }

    /* JADX INFO: renamed from: drawImage-gbVJVH8$default, reason: not valid java name */
    static /* synthetic */ void m5871drawImagegbVJVH8$default(DrawScope drawScope, ImageBitmap imageBitmap, long j, float f, DrawStyle drawStyle, ColorFilter colorFilter, int i, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: drawImage-gbVJVH8");
        }
        drawScope.mo5790drawImagegbVJVH8(imageBitmap, (i2 & 2) != 0 ? Offset.INSTANCE.m5084getZeroF1C5BW0() : j, (i2 & 4) != 0 ? 1.0f : f, (i2 & 8) != 0 ? Fill.INSTANCE : drawStyle, (i2 & 16) != 0 ? null : colorFilter, (i2 & 32) != 0 ? INSTANCE.m5889getDefaultBlendMode0nO6VwU() : i);
    }

    /* JADX INFO: renamed from: drawImage-9jGpkUE$default, reason: not valid java name */
    static /* synthetic */ void m5869drawImage9jGpkUE$default(DrawScope drawScope, ImageBitmap imageBitmap, long j, long j2, long j3, long j4, float f, DrawStyle drawStyle, ColorFilter colorFilter, int i, int i2, Object obj) {
        long jM8316constructorimpl;
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: drawImage-9jGpkUE");
        }
        long jM8289getZeronOccac = (i2 & 2) != 0 ? IntOffset.INSTANCE.m8289getZeronOccac() : j;
        if ((i2 & 4) != 0) {
            int width$iv = imageBitmap.getWidth();
            int height$iv = imageBitmap.getHeight();
            jM8316constructorimpl = IntSize.m8316constructorimpl((((long) width$iv) << 32) | (((long) height$iv) & 4294967295L));
        } else {
            jM8316constructorimpl = j2;
        }
        drawScope.mo5788drawImage9jGpkUE(imageBitmap, jM8289getZeronOccac, jM8316constructorimpl, (i2 & 8) != 0 ? IntOffset.INSTANCE.m8289getZeronOccac() : j3, (i2 & 16) != 0 ? jM8316constructorimpl : j4, (i2 & 32) != 0 ? 1.0f : f, (i2 & 64) != 0 ? Fill.INSTANCE : drawStyle, (i2 & 128) != 0 ? null : colorFilter, (i2 & 256) != 0 ? INSTANCE.m5889getDefaultBlendMode0nO6VwU() : i);
    }

    /* JADX INFO: renamed from: drawImage-AZ2fEMs$default, reason: not valid java name */
    static /* synthetic */ void m5870drawImageAZ2fEMs$default(DrawScope drawScope, ImageBitmap imageBitmap, long j, long j2, long j3, long j4, float f, DrawStyle drawStyle, ColorFilter colorFilter, int i, int i2, int i3, Object obj) {
        long jM8316constructorimpl;
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: drawImage-AZ2fEMs");
        }
        long jM8289getZeronOccac = (i3 & 2) != 0 ? IntOffset.INSTANCE.m8289getZeronOccac() : j;
        if ((i3 & 4) != 0) {
            int width$iv = imageBitmap.getWidth();
            int height$iv = imageBitmap.getHeight();
            jM8316constructorimpl = IntSize.m8316constructorimpl((((long) width$iv) << 32) | (((long) height$iv) & 4294967295L));
        } else {
            jM8316constructorimpl = j2;
        }
        drawScope.mo5789drawImageAZ2fEMs(imageBitmap, jM8289getZeronOccac, jM8316constructorimpl, (i3 & 8) != 0 ? IntOffset.INSTANCE.m8289getZeronOccac() : j3, (i3 & 16) != 0 ? jM8316constructorimpl : j4, (i3 & 32) != 0 ? 1.0f : f, (i3 & 64) != 0 ? Fill.INSTANCE : drawStyle, (i3 & 128) != 0 ? null : colorFilter, (i3 & 256) != 0 ? INSTANCE.m5889getDefaultBlendMode0nO6VwU() : i, (i3 & 512) != 0 ? INSTANCE.m5890getDefaultFilterQualityfv9h1I() : i2);
    }

    /* JADX INFO: renamed from: drawImage-AZ2fEMs */
    default void mo5789drawImageAZ2fEMs(ImageBitmap image, long srcOffset, long srcSize, long dstOffset, long dstSize, float alpha, DrawStyle style, ColorFilter colorFilter, int blendMode, int filterQuality) {
        m5870drawImageAZ2fEMs$default(this, image, srcOffset, srcSize, dstOffset, dstSize, alpha, style, colorFilter, blendMode, 0, 512, null);
    }

    /* JADX INFO: renamed from: drawRoundRect-ZuiqVtQ$default, reason: not valid java name */
    static /* synthetic */ void m5882drawRoundRectZuiqVtQ$default(DrawScope drawScope, Brush brush, long j, long j2, long j3, float f, DrawStyle drawStyle, ColorFilter colorFilter, int i, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: drawRoundRect-ZuiqVtQ");
        }
        long jM5084getZeroF1C5BW0 = (i2 & 2) != 0 ? Offset.INSTANCE.m5084getZeroF1C5BW0() : j;
        drawScope.mo5801drawRoundRectZuiqVtQ(brush, jM5084getZeroF1C5BW0, (i2 & 4) != 0 ? drawScope.m5884offsetSizePENXr5M(drawScope.mo5887getSizeNHjbRc(), jM5084getZeroF1C5BW0) : j2, (i2 & 8) != 0 ? CornerRadius.INSTANCE.m5040getZerokKHJgLs() : j3, (i2 & 16) != 0 ? 1.0f : f, (i2 & 32) != 0 ? Fill.INSTANCE : drawStyle, (i2 & 64) != 0 ? null : colorFilter, (i2 & 128) != 0 ? INSTANCE.m5889getDefaultBlendMode0nO6VwU() : i);
    }

    /* JADX INFO: renamed from: drawRoundRect-u-Aw5IA$default, reason: not valid java name */
    static /* synthetic */ void m5883drawRoundRectuAw5IA$default(DrawScope drawScope, long j, long j2, long j3, long j4, DrawStyle drawStyle, float f, ColorFilter colorFilter, int i, int i2, Object obj) {
        DrawScope drawScope2;
        long jM5884offsetSizePENXr5M;
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: drawRoundRect-u-Aw5IA");
        }
        long jM5084getZeroF1C5BW0 = (i2 & 2) != 0 ? Offset.INSTANCE.m5084getZeroF1C5BW0() : j2;
        if ((i2 & 4) != 0) {
            drawScope2 = drawScope;
            jM5884offsetSizePENXr5M = drawScope2.m5884offsetSizePENXr5M(drawScope.mo5887getSizeNHjbRc(), jM5084getZeroF1C5BW0);
        } else {
            drawScope2 = drawScope;
            jM5884offsetSizePENXr5M = j3;
        }
        drawScope2.mo5802drawRoundRectuAw5IA(j, jM5084getZeroF1C5BW0, jM5884offsetSizePENXr5M, (i2 & 8) != 0 ? CornerRadius.INSTANCE.m5040getZerokKHJgLs() : j4, (i2 & 16) != 0 ? Fill.INSTANCE : drawStyle, (i2 & 32) != 0 ? 1.0f : f, (i2 & 64) != 0 ? null : colorFilter, (i2 & 128) != 0 ? INSTANCE.m5889getDefaultBlendMode0nO6VwU() : i);
    }

    /* JADX INFO: renamed from: drawCircle-V9BoPsw$default, reason: not valid java name */
    static /* synthetic */ void m5867drawCircleV9BoPsw$default(DrawScope drawScope, Brush brush, float f, long j, float f2, DrawStyle drawStyle, ColorFilter colorFilter, int i, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: drawCircle-V9BoPsw");
        }
        drawScope.mo5786drawCircleV9BoPsw(brush, (i2 & 2) != 0 ? Size.m5136getMinDimensionimpl(drawScope.mo5887getSizeNHjbRc()) / 2.0f : f, (i2 & 4) != 0 ? drawScope.mo5886getCenterF1C5BW0() : j, (i2 & 8) != 0 ? 1.0f : f2, (i2 & 16) != 0 ? Fill.INSTANCE : drawStyle, (i2 & 32) != 0 ? null : colorFilter, (i2 & 64) != 0 ? INSTANCE.m5889getDefaultBlendMode0nO6VwU() : i);
    }

    /* JADX INFO: renamed from: drawCircle-VaOC9Bg$default, reason: not valid java name */
    static /* synthetic */ void m5868drawCircleVaOC9Bg$default(DrawScope drawScope, long j, float f, long j2, float f2, DrawStyle drawStyle, ColorFilter colorFilter, int i, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: drawCircle-VaOC9Bg");
        }
        drawScope.mo5787drawCircleVaOC9Bg(j, (i2 & 2) != 0 ? Size.m5136getMinDimensionimpl(drawScope.mo5887getSizeNHjbRc()) / 2.0f : f, (i2 & 4) != 0 ? drawScope.mo5886getCenterF1C5BW0() : j2, (i2 & 8) != 0 ? 1.0f : f2, (i2 & 16) != 0 ? Fill.INSTANCE : drawStyle, (i2 & 32) != 0 ? null : colorFilter, (i2 & 64) != 0 ? INSTANCE.m5889getDefaultBlendMode0nO6VwU() : i);
    }

    /* JADX INFO: renamed from: drawOval-AsUm42w$default, reason: not valid java name */
    static /* synthetic */ void m5874drawOvalAsUm42w$default(DrawScope drawScope, Brush brush, long j, long j2, float f, DrawStyle drawStyle, ColorFilter colorFilter, int i, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: drawOval-AsUm42w");
        }
        long jM5084getZeroF1C5BW0 = (i2 & 2) != 0 ? Offset.INSTANCE.m5084getZeroF1C5BW0() : j;
        drawScope.mo5793drawOvalAsUm42w(brush, jM5084getZeroF1C5BW0, (i2 & 4) != 0 ? drawScope.m5884offsetSizePENXr5M(drawScope.mo5887getSizeNHjbRc(), jM5084getZeroF1C5BW0) : j2, (i2 & 8) != 0 ? 1.0f : f, (i2 & 16) != 0 ? Fill.INSTANCE : drawStyle, (i2 & 32) != 0 ? null : colorFilter, (i2 & 64) != 0 ? INSTANCE.m5889getDefaultBlendMode0nO6VwU() : i);
    }

    /* JADX INFO: renamed from: drawOval-n-J9OG0$default, reason: not valid java name */
    static /* synthetic */ void m5875drawOvalnJ9OG0$default(DrawScope drawScope, long j, long j2, long j3, float f, DrawStyle drawStyle, ColorFilter colorFilter, int i, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: drawOval-n-J9OG0");
        }
        long jM5084getZeroF1C5BW0 = (i2 & 2) != 0 ? Offset.INSTANCE.m5084getZeroF1C5BW0() : j2;
        drawScope.mo5794drawOvalnJ9OG0(j, jM5084getZeroF1C5BW0, (i2 & 4) != 0 ? drawScope.m5884offsetSizePENXr5M(drawScope.mo5887getSizeNHjbRc(), jM5084getZeroF1C5BW0) : j3, (i2 & 8) != 0 ? 1.0f : f, (i2 & 16) != 0 ? Fill.INSTANCE : drawStyle, (i2 & 32) != 0 ? null : colorFilter, (i2 & 64) != 0 ? INSTANCE.m5889getDefaultBlendMode0nO6VwU() : i);
    }

    /* JADX INFO: renamed from: drawArc-illE91I$default, reason: not valid java name */
    static /* synthetic */ void m5865drawArcillE91I$default(DrawScope drawScope, Brush brush, float f, float f2, boolean z, long j, long j2, float f3, DrawStyle drawStyle, ColorFilter colorFilter, int i, int i2, Object obj) {
        DrawScope drawScope2;
        long jM5884offsetSizePENXr5M;
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: drawArc-illE91I");
        }
        long jM5084getZeroF1C5BW0 = (i2 & 16) != 0 ? Offset.INSTANCE.m5084getZeroF1C5BW0() : j;
        if ((i2 & 32) != 0) {
            drawScope2 = drawScope;
            jM5884offsetSizePENXr5M = drawScope2.m5884offsetSizePENXr5M(drawScope.mo5887getSizeNHjbRc(), jM5084getZeroF1C5BW0);
        } else {
            drawScope2 = drawScope;
            jM5884offsetSizePENXr5M = j2;
        }
        drawScope2.mo5784drawArcillE91I(brush, f, f2, z, jM5084getZeroF1C5BW0, jM5884offsetSizePENXr5M, (i2 & 64) != 0 ? 1.0f : f3, (i2 & 128) != 0 ? Fill.INSTANCE : drawStyle, (i2 & 256) != 0 ? null : colorFilter, (i2 & 512) != 0 ? INSTANCE.m5889getDefaultBlendMode0nO6VwU() : i);
    }

    /* JADX INFO: renamed from: drawArc-yD3GUKo$default, reason: not valid java name */
    static /* synthetic */ void m5866drawArcyD3GUKo$default(DrawScope drawScope, long j, float f, float f2, boolean z, long j2, long j3, float f3, DrawStyle drawStyle, ColorFilter colorFilter, int i, int i2, Object obj) {
        DrawScope drawScope2;
        long jM5884offsetSizePENXr5M;
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: drawArc-yD3GUKo");
        }
        long jM5084getZeroF1C5BW0 = (i2 & 16) != 0 ? Offset.INSTANCE.m5084getZeroF1C5BW0() : j2;
        if ((i2 & 32) != 0) {
            drawScope2 = drawScope;
            jM5884offsetSizePENXr5M = drawScope2.m5884offsetSizePENXr5M(drawScope.mo5887getSizeNHjbRc(), jM5084getZeroF1C5BW0);
        } else {
            drawScope2 = drawScope;
            jM5884offsetSizePENXr5M = j3;
        }
        drawScope2.mo5785drawArcyD3GUKo(j, f, f2, z, jM5084getZeroF1C5BW0, jM5884offsetSizePENXr5M, (i2 & 64) != 0 ? 1.0f : f3, (i2 & 128) != 0 ? Fill.INSTANCE : drawStyle, (i2 & 256) != 0 ? null : colorFilter, (i2 & 512) != 0 ? INSTANCE.m5889getDefaultBlendMode0nO6VwU() : i);
    }

    /* JADX INFO: renamed from: drawPath-LG529CI$default, reason: not valid java name */
    static /* synthetic */ void m5877drawPathLG529CI$default(DrawScope drawScope, Path path, long j, float f, DrawStyle drawStyle, ColorFilter colorFilter, int i, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: drawPath-LG529CI");
        }
        drawScope.mo5796drawPathLG529CI(path, j, (i2 & 4) != 0 ? 1.0f : f, (i2 & 8) != 0 ? Fill.INSTANCE : drawStyle, (i2 & 16) != 0 ? null : colorFilter, (i2 & 32) != 0 ? INSTANCE.m5889getDefaultBlendMode0nO6VwU() : i);
    }

    /* JADX INFO: renamed from: drawPath-GBMwjPU$default, reason: not valid java name */
    static /* synthetic */ void m5876drawPathGBMwjPU$default(DrawScope drawScope, Path path, Brush brush, float f, DrawStyle drawStyle, ColorFilter colorFilter, int i, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: drawPath-GBMwjPU");
        }
        drawScope.mo5795drawPathGBMwjPU(path, brush, (i2 & 4) != 0 ? 1.0f : f, (i2 & 8) != 0 ? Fill.INSTANCE : drawStyle, (i2 & 16) != 0 ? null : colorFilter, (i2 & 32) != 0 ? INSTANCE.m5889getDefaultBlendMode0nO6VwU() : i);
    }

    /* JADX INFO: renamed from: drawPoints-F8ZwMP8$default, reason: not valid java name */
    static /* synthetic */ void m5878drawPointsF8ZwMP8$default(DrawScope drawScope, List list, int i, long j, float f, int i2, PathEffect pathEffect, float f2, ColorFilter colorFilter, int i3, int i4, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: drawPoints-F8ZwMP8");
        }
        drawScope.mo5797drawPointsF8ZwMP8(list, i, j, (i4 & 8) != 0 ? 0.0f : f, (i4 & 16) != 0 ? StrokeCap.INSTANCE.m5687getButtKaPHkGw() : i2, (i4 & 32) != 0 ? null : pathEffect, (i4 & 64) != 0 ? 1.0f : f2, (i4 & 128) != 0 ? null : colorFilter, (i4 & 256) != 0 ? INSTANCE.m5889getDefaultBlendMode0nO6VwU() : i3);
    }

    /* JADX INFO: renamed from: drawPoints-Gsft0Ws$default, reason: not valid java name */
    static /* synthetic */ void m5879drawPointsGsft0Ws$default(DrawScope drawScope, List list, int i, Brush brush, float f, int i2, PathEffect pathEffect, float f2, ColorFilter colorFilter, int i3, int i4, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: drawPoints-Gsft0Ws");
        }
        drawScope.mo5798drawPointsGsft0Ws(list, i, brush, (i4 & 8) != 0 ? 0.0f : f, (i4 & 16) != 0 ? StrokeCap.INSTANCE.m5687getButtKaPHkGw() : i2, (i4 & 32) != 0 ? null : pathEffect, (i4 & 64) != 0 ? 1.0f : f2, (i4 & 128) != 0 ? null : colorFilter, (i4 & 256) != 0 ? INSTANCE.m5889getDefaultBlendMode0nO6VwU() : i3);
    }

    /* JADX INFO: renamed from: record-JVtK1S4$default, reason: not valid java name */
    static /* synthetic */ void m5885recordJVtK1S4$default(DrawScope drawScope, GraphicsLayer graphicsLayer, long j, Function1 function1, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: record-JVtK1S4");
        }
        if ((i & 1) != 0) {
            j = IntSizeKt.m8332toIntSizeuvyYCjk(drawScope.mo5887getSizeNHjbRc());
        }
        drawScope.mo5888recordJVtK1S4(graphicsLayer, j, function1);
    }

    /* JADX INFO: renamed from: record-JVtK1S4, reason: not valid java name */
    default void mo5888recordJVtK1S4(GraphicsLayer $this$record_u2dJVtK1S4, long size, final Function1<? super DrawScope, Unit> function1) {
        $this$record_u2dJVtK1S4.m5981recordmLhObY(this, getLayoutDirection(), size, new Function1<DrawScope, Unit>() { // from class: androidx.compose.ui.graphics.drawscope.DrawScope$record$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope) {
                invoke2(drawScope);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(DrawScope $this$record) {
                DrawScope $this$draw_u2dymL40Pk$iv = this.this$0;
                Density density$iv = $this$record.getDrawContext().getDensity();
                LayoutDirection layoutDirection$iv = $this$record.getDrawContext().getLayoutDirection();
                Canvas canvas$iv = $this$record.getDrawContext().getCanvas();
                long size$iv = $this$record.getDrawContext().mo5808getSizeNHjbRc();
                GraphicsLayer graphicsLayer$iv = $this$record.getDrawContext().getGraphicsLayer();
                Function1<DrawScope, Unit> function12 = function1;
                Density prevDensity$iv = $this$draw_u2dymL40Pk$iv.getDrawContext().getDensity();
                LayoutDirection prevLayoutDirection$iv = $this$draw_u2dymL40Pk$iv.getDrawContext().getLayoutDirection();
                Canvas prevCanvas$iv = $this$draw_u2dymL40Pk$iv.getDrawContext().getCanvas();
                long prevSize$iv = $this$draw_u2dymL40Pk$iv.getDrawContext().mo5808getSizeNHjbRc();
                GraphicsLayer prevLayer$iv = $this$draw_u2dymL40Pk$iv.getDrawContext().getGraphicsLayer();
                DrawContext $this$draw_ymL40Pk_u24lambda_u240$iv = $this$draw_u2dymL40Pk$iv.getDrawContext();
                $this$draw_ymL40Pk_u24lambda_u240$iv.setDensity(density$iv);
                $this$draw_ymL40Pk_u24lambda_u240$iv.setLayoutDirection(layoutDirection$iv);
                $this$draw_ymL40Pk_u24lambda_u240$iv.setCanvas(canvas$iv);
                $this$draw_ymL40Pk_u24lambda_u240$iv.mo5809setSizeuvyYCjk(size$iv);
                $this$draw_ymL40Pk_u24lambda_u240$iv.setGraphicsLayer(graphicsLayer$iv);
                canvas$iv.save();
                try {
                    function12.invoke($this$draw_u2dymL40Pk$iv);
                } finally {
                    canvas$iv.restore();
                    DrawContext $this$draw_ymL40Pk_u24lambda_u241$iv = $this$draw_u2dymL40Pk$iv.getDrawContext();
                    $this$draw_ymL40Pk_u24lambda_u241$iv.setDensity(prevDensity$iv);
                    $this$draw_ymL40Pk_u24lambda_u241$iv.setLayoutDirection(prevLayoutDirection$iv);
                    $this$draw_ymL40Pk_u24lambda_u241$iv.setCanvas(prevCanvas$iv);
                    $this$draw_ymL40Pk_u24lambda_u241$iv.mo5809setSizeuvyYCjk(prevSize$iv);
                    $this$draw_ymL40Pk_u24lambda_u241$iv.setGraphicsLayer(prevLayer$iv);
                }
            }
        });
    }

    /* JADX INFO: renamed from: offsetSize-PENXr5M, reason: not valid java name */
    private default long m5884offsetSizePENXr5M(long $this$offsetSize_u2dPENXr5M, long offset) {
        int bits$iv$iv$iv = (int) ($this$offsetSize_u2dPENXr5M >> 32);
        int bits$iv$iv$iv2 = (int) (offset >> 32);
        float width$iv = Float.intBitsToFloat(bits$iv$iv$iv) - Float.intBitsToFloat(bits$iv$iv$iv2);
        int bits$iv$iv$iv3 = (int) ($this$offsetSize_u2dPENXr5M & 4294967295L);
        int bits$iv$iv$iv4 = (int) (offset & 4294967295L);
        float height$iv = Float.intBitsToFloat(bits$iv$iv$iv3) - Float.intBitsToFloat(bits$iv$iv$iv4);
        long v1$iv$iv = Float.floatToRawIntBits(width$iv);
        long v2$iv$iv = Float.floatToRawIntBits(height$iv);
        return Size.m5128constructorimpl((v1$iv$iv << 32) | (4294967295L & v2$iv$iv));
    }

    /* JADX INFO: compiled from: DrawScope.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0013\u0010\u0004\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\t\u001a\u00020\n¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u000b\u0010\u0007¨\u0006\f"}, d2 = {"Landroidx/compose/ui/graphics/drawscope/DrawScope$Companion;", "", "<init>", "()V", "DefaultBlendMode", "Landroidx/compose/ui/graphics/BlendMode;", "getDefaultBlendMode-0nO6VwU", "()I", "I", "DefaultFilterQuality", "Landroidx/compose/ui/graphics/FilterQuality;", "getDefaultFilterQuality-f-v9h1I", "ui-graphics"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        private static final int DefaultBlendMode = BlendMode.INSTANCE.m5253getSrcOver0nO6VwU();
        private static final int DefaultFilterQuality = FilterQuality.INSTANCE.m5413getLowfv9h1I();

        private Companion() {
        }

        /* JADX INFO: renamed from: getDefaultBlendMode-0nO6VwU, reason: not valid java name */
        public final int m5889getDefaultBlendMode0nO6VwU() {
            return DefaultBlendMode;
        }

        /* JADX INFO: renamed from: getDefaultFilterQuality-f-v9h1I, reason: not valid java name */
        public final int m5890getDefaultFilterQualityfv9h1I() {
            return DefaultFilterQuality;
        }
    }
}
