package androidx.compose.animation;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimatableKt;
import androidx.compose.animation.core.AnimationKt;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.animation.core.ArcAnimationSpec;
import androidx.compose.animation.core.ArcMode;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.animation.core.SnapSpec;
import androidx.compose.animation.core.TargetBasedAnimation;
import androidx.compose.animation.core.TweenSpec;
import androidx.compose.animation.core.VectorConvertersKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.RectKt;
import androidx.compose.ui.graphics.AndroidPaint_androidKt;
import androidx.compose.ui.graphics.AndroidPath_androidKt;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.ClipOp;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.Paint;
import androidx.compose.ui.graphics.PaintingStyle;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.PathEffect;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.graphics.TileMode;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawContext;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.graphics.drawscope.DrawTransform;
import androidx.compose.ui.graphics.drawscope.Stroke;
import androidx.compose.ui.text.PlatformTextStyle;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextMeasurer;
import androidx.compose.ui.text.TextPainterKt;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontSynthesis;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.intl.LocaleList;
import androidx.compose.ui.text.style.BaselineShift;
import androidx.compose.ui.text.style.LineHeightStyle;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextGeometricTransform;
import androidx.compose.ui.text.style.TextIndent;
import androidx.compose.ui.text.style.TextMotion;
import androidx.compose.ui.text.style.TextOverflow;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.view.PointerIconCompat;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: LookaheadAnimationVisualDebugHelper.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0082\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0001\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020(H\u0000¢\u0006\u0002\b)J\u0015\u0010*\u001a\u00020&2\u0006\u0010'\u001a\u00020(H\u0000¢\u0006\u0002\b+J\u001f\u0010,\u001a\u00020&2\u0006\u0010-\u001a\u00020\f2\u0006\u0010.\u001a\u00020\u0013H\u0000¢\u0006\u0004\b/\u00100J\u0011\u00101\u001a\u00020&*\u000202H\u0000¢\u0006\u0002\b3J\u001b\u00104\u001a\u00020&*\u0002022\u0006\u00105\u001a\u00020!H\u0000¢\u0006\u0004\b6\u00107J\u0017\u00108\u001a\u00020!2\u0006\u00109\u001a\u00020\u0001H\u0000¢\u0006\u0004\b:\u0010;J?\u0010<\u001a\u00020&*\u0002022\u0006\u0010=\u001a\u00020!2\u0006\u0010>\u001a\u00020\n2\u0006\u0010?\u001a\u00020\u00062\u0006\u00109\u001a\u00020\u00012\n\b\u0002\u0010@\u001a\u0004\u0018\u00010AH\u0000¢\u0006\u0004\bB\u0010CJ_\u0010D\u001a\u00020&*\u0002022\u0006\u0010=\u001a\u00020!2\u0006\u0010E\u001a\u00020\f2\u0006\u0010F\u001a\u00020G2\u0006\u0010H\u001a\u00020I2\u0006\u0010J\u001a\u00020\f2\u0006\u0010>\u001a\u00020\n2\u0006\u0010?\u001a\u00020\u00062\u0006\u00109\u001a\u00020\u00012\n\b\u0002\u0010@\u001a\u0004\u0018\u00010AH\u0000¢\u0006\u0004\bK\u0010LJC\u0010M\u001a\u00020&*\u0002022\u0006\u0010N\u001a\u00020!2\u0006\u0010>\u001a\u00020\n2\u0006\u00109\u001a\u00020\u00012\u0006\u0010O\u001a\u00020P2\u0006\u0010@\u001a\u00020A2\u0006\u0010?\u001a\u00020\u0006H\u0000¢\u0006\u0004\bQ\u0010RJ;\u0010S\u001a\u00020&*\u0002022\u0006\u0010T\u001a\u00020!2\u0006\u0010>\u001a\u00020\n2\u0006\u00109\u001a\u00020\u00012\u0006\u0010@\u001a\u00020A2\u0006\u0010?\u001a\u00020\u0006H\u0000¢\u0006\u0004\bU\u0010VJ\u0017\u0010W\u001a\u00020\f2\u0006\u0010X\u001a\u00020\u0006H\u0002¢\u0006\u0004\bY\u0010ZJ\u0010\u0010[\u001a\u00020&2\u0006\u0010\\\u001a\u00020\u0006H\u0002J5\u0010]\u001a\u00020&2\f\u0010^\u001a\b\u0012\u0004\u0012\u00020I0_2\u0006\u0010`\u001a\u00020I2\u0006\u0010a\u001a\u00020I2\b\b\u0002\u0010b\u001a\u00020IH\u0000¢\u0006\u0002\bcR\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u000b\u001a\u00020\fX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0011\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001c\u0010\u0012\u001a\u00020\u0013X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0011\u001a\u0004\b\u0014\u0010\u000e\"\u0004\b\u0015\u0010\u0010R\u001c\u0010\u0016\u001a\u00020\fX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0011\u001a\u0004\b\u0017\u0010\u000e\"\u0004\b\u0018\u0010\u0010R\u0011\u0010\u0019\u001a\u00020\u001a¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\u001d\u001a\u00020\u001a¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001cR\u001d\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020!0 ¢\u0006\u000e\n\u0000\u0012\u0004\b\"\u0010\u0003\u001a\u0004\b#\u0010$¨\u0006d"}, d2 = {"Landroidx/compose/animation/LookaheadAnimationVisualDebugHelper;", "", "<init>", "()V", "reverseProgress", "Landroidx/compose/animation/core/Animatable;", "", "Landroidx/compose/animation/core/AnimationVector1D;", "restartProgress", "isProgressAnimationRunning", "", "sharedTransitionScopeOffset", "Landroidx/compose/ui/geometry/Offset;", "getSharedTransitionScopeOffset-F1C5BW0", "()J", "setSharedTransitionScopeOffset-k-4lQ0M", "(J)V", "J", "sharedTransitionScopeSize", "Landroidx/compose/ui/unit/IntSize;", "getSharedTransitionScopeSize-YbymL2g", "setSharedTransitionScopeSize-ozmzZPI", "debugOffset", "getDebugOffset-F1C5BW0", "setDebugOffset-k-4lQ0M", "debugPath", "Landroidx/compose/ui/graphics/Path;", "getDebugPath", "()Landroidx/compose/ui/graphics/Path;", "centerPath", "getCenterPath", "colors", "", "Landroidx/compose/ui/graphics/Color;", "getColors$annotations", "getColors", "()Ljava/util/List;", "onAttach", "", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "onAttach$animation", "onDetach", "onDetach$animation", "updateDrawingCoordinates", "offsetInSharedTransitionScope", "sizeOfSharedTransitionScope", "updateDrawingCoordinates-CowoxoA$animation", "(JJ)V", "drawGlobalVisualizations", "Landroidx/compose/ui/graphics/drawscope/ContentDrawScope;", "drawGlobalVisualizations$animation", "drawOverlay", "overlayColor", "drawOverlay-4WTKRHQ$animation", "(Landroidx/compose/ui/graphics/drawscope/ContentDrawScope;J)V", "chooseColor", "key", "chooseColor-vNxB06k$animation", "(Ljava/lang/Object;)J", "drawInactiveVisualizations", "animationColor", "isShowKeyLabelEnabled", "strokeWidth", "textMeasurer", "Landroidx/compose/ui/text/TextMeasurer;", "drawInactiveVisualizations-3IgeMak$animation", "(Landroidx/compose/ui/graphics/drawscope/ContentDrawScope;JZFLjava/lang/Object;Landroidx/compose/ui/text/TextMeasurer;)V", "drawLocalVisualizations", "targetOffset", "targetSize", "Landroidx/compose/ui/geometry/Size;", "currentRect", "Landroidx/compose/ui/geometry/Rect;", "center", "drawLocalVisualizations-0XenJco$animation", "(Landroidx/compose/ui/graphics/drawscope/ContentDrawScope;JJJLandroidx/compose/ui/geometry/Rect;JZFLjava/lang/Object;Landroidx/compose/ui/text/TextMeasurer;)V", "drawMultipleMatchesElement", "multipleMatchesColor", "numMatches", "", "drawMultipleMatchesElement-sW7UJKQ$animation", "(Landroidx/compose/ui/graphics/drawscope/ContentDrawScope;JZLjava/lang/Object;ILandroidx/compose/ui/text/TextMeasurer;F)V", "drawUnmatchedElement", "unmatchedColor", "drawUnmatchedElement-3IgeMak$animation", "(Landroidx/compose/ui/graphics/drawscope/ContentDrawScope;JZLjava/lang/Object;Landroidx/compose/ui/text/TextMeasurer;F)V", "findPositionAlongPerimeter", "distanceTraveled", "findPositionAlongPerimeter-tuRUvjQ", "(F)J", "calculatePathCenter", "diamondWidth", "calculatePath", "spec", "Landroidx/compose/animation/core/FiniteAnimationSpec;", "current", TypedValues.AttributesType.S_TARGET, "initialVelocity", "calculatePath$animation", "animation"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class LookaheadAnimationVisualDebugHelper {
    public static final int $stable = 8;
    private boolean isProgressAnimationRunning;
    private final Animatable<Float, AnimationVector1D> reverseProgress = AnimatableKt.Animatable$default(0.0f, 0.0f, 2, null);
    private final Animatable<Float, AnimationVector1D> restartProgress = AnimatableKt.Animatable$default(0.0f, 0.0f, 2, null);
    private long sharedTransitionScopeOffset = Offset.INSTANCE.m5084getZeroF1C5BW0();
    private long sharedTransitionScopeSize = IntSize.INSTANCE.m8326getZeroYbymL2g();
    private long debugOffset = Offset.INSTANCE.m5084getZeroF1C5BW0();
    private final Path debugPath = AndroidPath_androidKt.Path();
    private final Path centerPath = AndroidPath_androidKt.Path();
    private final List<Color> colors = CollectionsKt.listOf((Object[]) new Color[]{Color.m5303boximpl(ColorKt.Color(4293542709L)), Color.m5303boximpl(ColorKt.Color(4294086695L)), Color.m5303boximpl(ColorKt.Color(4291905755L)), Color.m5303boximpl(ColorKt.Color(4282549748L)), Color.m5303boximpl(ColorKt.Color(4282038458L))});

    public static /* synthetic */ void getColors$annotations() {
    }

    /* JADX INFO: renamed from: getSharedTransitionScopeOffset-F1C5BW0, reason: not valid java name and from getter */
    public final long getSharedTransitionScopeOffset() {
        return this.sharedTransitionScopeOffset;
    }

    /* JADX INFO: renamed from: setSharedTransitionScopeOffset-k-4lQ0M, reason: not valid java name */
    public final void m130setSharedTransitionScopeOffsetk4lQ0M(long j) {
        this.sharedTransitionScopeOffset = j;
    }

    /* JADX INFO: renamed from: getSharedTransitionScopeSize-YbymL2g, reason: not valid java name and from getter */
    public final long getSharedTransitionScopeSize() {
        return this.sharedTransitionScopeSize;
    }

    /* JADX INFO: renamed from: setSharedTransitionScopeSize-ozmzZPI, reason: not valid java name */
    public final void m131setSharedTransitionScopeSizeozmzZPI(long j) {
        this.sharedTransitionScopeSize = j;
    }

    /* JADX INFO: renamed from: getDebugOffset-F1C5BW0, reason: not valid java name and from getter */
    public final long getDebugOffset() {
        return this.debugOffset;
    }

    /* JADX INFO: renamed from: setDebugOffset-k-4lQ0M, reason: not valid java name */
    public final void m129setDebugOffsetk4lQ0M(long j) {
        this.debugOffset = j;
    }

    public final Path getDebugPath() {
        return this.debugPath;
    }

    public final Path getCenterPath() {
        return this.centerPath;
    }

    public final List<Color> getColors() {
        return this.colors;
    }

    public final void onAttach$animation(CoroutineScope coroutineScope) {
        if (!this.isProgressAnimationRunning) {
            this.isProgressAnimationRunning = true;
            BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new LookaheadAnimationVisualDebugHelper$onAttach$1(this, null), 3, null);
            BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new LookaheadAnimationVisualDebugHelper$onAttach$2(this, null), 3, null);
        }
    }

    public final void onDetach$animation(CoroutineScope coroutineScope) {
        if (this.isProgressAnimationRunning) {
            this.isProgressAnimationRunning = false;
            BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new LookaheadAnimationVisualDebugHelper$onDetach$1(this, null), 3, null);
        }
    }

    /* JADX INFO: renamed from: updateDrawingCoordinates-CowoxoA$animation, reason: not valid java name */
    public final void m132updateDrawingCoordinatesCowoxoA$animation(long offsetInSharedTransitionScope, long sizeOfSharedTransitionScope) {
        this.sharedTransitionScopeOffset = offsetInSharedTransitionScope;
        this.sharedTransitionScopeSize = sizeOfSharedTransitionScope;
    }

    public final void drawGlobalVisualizations$animation(ContentDrawScope $this$drawGlobalVisualizations) {
        int i = ((int) (this.sharedTransitionScopeSize >> 32)) * 2;
        int $i$f$unpackInt2 = (int) (this.sharedTransitionScopeSize & 4294967295L);
        float animatedDistance = (i + ($i$f$unpackInt2 * 2)) * this.restartProgress.getValue().floatValue();
        long perimeterCenter = m119findPositionAlongPerimetertuRUvjQ(animatedDistance);
        List gradientColors = CollectionsKt.listOf((Object[]) new Color[]{Color.m5303boximpl(ColorKt.Color(4293542709L)), Color.m5303boximpl(ColorKt.Color(4282549748L)), Color.m5303boximpl(ColorKt.Color(4281641043L)), Color.m5303boximpl(ColorKt.Color(4294687748L)), Color.m5303boximpl(ColorKt.Color(4293542709L))});
        Brush perimeterBrush = Brush.INSTANCE.m5275radialGradientP_VxKs((List<Color>) gradientColors, (8 & 2) != 0 ? Offset.INSTANCE.m5083getUnspecifiedF1C5BW0() : perimeterCenter, (8 & 4) != 0 ? Float.POSITIVE_INFINITY : 2000.0f, (8 & 8) != 0 ? TileMode.INSTANCE.m5708getClamp3opZhB0() : 0);
        ContentDrawScope $this$drawIntoCanvas$iv = $this$drawGlobalVisualizations;
        Canvas canvas = $this$drawIntoCanvas$iv.getDrawContext().getCanvas();
        Paint paint = AndroidPaint_androidKt.Paint();
        perimeterBrush.mo5258applyToPq9zytI($this$drawGlobalVisualizations.mo5887getSizeNHjbRc(), paint, 1.0f);
        paint.mo5193setStylek9PVt8s(PaintingStyle.INSTANCE.m5595getStrokeTiuSbCo());
        paint.setStrokeWidth(($this$drawGlobalVisualizations.mo432toPx0680j_4(Dp.m8150constructorimpl(8)) * this.reverseProgress.getValue().floatValue()) + $this$drawGlobalVisualizations.mo432toPx0680j_4(Dp.m8150constructorimpl(4)));
        canvas.save();
        int bits$iv$iv$iv = (int) (this.sharedTransitionScopeOffset >> 32);
        float f = -Float.intBitsToFloat(bits$iv$iv$iv);
        int bits$iv$iv$iv2 = (int) (this.sharedTransitionScopeOffset & 4294967295L);
        canvas.translate(f, -Float.intBitsToFloat(bits$iv$iv$iv2));
        canvas.drawRect(0.0f, 0.0f, (int) (this.sharedTransitionScopeSize >> 32), (int) (this.sharedTransitionScopeSize & 4294967295L), paint);
        canvas.restore();
    }

    /* JADX INFO: renamed from: drawOverlay-4WTKRHQ$animation, reason: not valid java name */
    public final void m124drawOverlay4WTKRHQ$animation(ContentDrawScope $this$drawOverlay_u2d4WTKRHQ, long overlayColor) {
        DrawScope.m5881drawRectnJ9OG0$default($this$drawOverlay_u2d4WTKRHQ, overlayColor, 0L, 0L, 0.0f, null, null, 0, 126, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: chooseColor-vNxB06k$animation, reason: not valid java name */
    public final long m120chooseColorvNxB06k$animation(Object key) {
        if (LookaheadAnimationVisualDebugHelperKt.keyToColor.contains(key)) {
            V v = LookaheadAnimationVisualDebugHelperKt.keyToColor.get(key);
            Intrinsics.checkNotNull(v);
            return ((Color) v).m5323unboximpl();
        }
        if (LookaheadAnimationVisualDebugHelperKt.colorIndex >= this.colors.size()) {
            LookaheadAnimationVisualDebugHelperKt.colorIndex = 0;
        }
        long currentColor = this.colors.get(LookaheadAnimationVisualDebugHelperKt.colorIndex).m5323unboximpl();
        LookaheadAnimationVisualDebugHelperKt.colorIndex++;
        LookaheadAnimationVisualDebugHelperKt.keyToColor.set(key, Color.m5303boximpl(currentColor));
        return currentColor;
    }

    /* JADX INFO: renamed from: drawInactiveVisualizations-3IgeMak$animation$default, reason: not valid java name */
    public static /* synthetic */ void m117drawInactiveVisualizations3IgeMak$animation$default(LookaheadAnimationVisualDebugHelper lookaheadAnimationVisualDebugHelper, ContentDrawScope contentDrawScope, long j, boolean z, float f, Object obj, TextMeasurer textMeasurer, int i, Object obj2) throws Throwable {
        TextMeasurer textMeasurer2;
        if ((i & 16) == 0) {
            textMeasurer2 = textMeasurer;
        } else {
            textMeasurer2 = null;
        }
        lookaheadAnimationVisualDebugHelper.m121drawInactiveVisualizations3IgeMak$animation(contentDrawScope, j, z, f, obj, textMeasurer2);
    }

    /* JADX INFO: renamed from: drawInactiveVisualizations-3IgeMak$animation, reason: not valid java name */
    public final void m121drawInactiveVisualizations3IgeMak$animation(ContentDrawScope $this$drawInactiveVisualizations_u2d3IgeMak, long animationColor, boolean isShowKeyLabelEnabled, float strokeWidth, Object key, TextMeasurer textMeasurer) throws Throwable {
        long jColor;
        float highlightWidth = strokeWidth * 2.0f;
        if (!Color.m5314equalsimpl0(animationColor, Color.INSTANCE.m5349getUnspecified0d7_KjU())) {
            jColor = animationColor;
        } else {
            DrawScope.m5881drawRectnJ9OG0$default($this$drawInactiveVisualizations_u2d3IgeMak, Color.INSTANCE.m5350getWhite0d7_KjU(), 0L, 0L, 0.0f, new Stroke(highlightWidth, 0.0f, 0, 0, null, 30, null), null, 0, 110, null);
            jColor = ColorKt.Color(4288323750L);
        }
        long chosenColor = jColor;
        DrawScope.m5881drawRectnJ9OG0$default($this$drawInactiveVisualizations_u2d3IgeMak, chosenColor, 0L, 0L, 0.0f, new Stroke(strokeWidth, 0.0f, 0, 0, null, 30, null), null, 0, 110, null);
        if (isShowKeyLabelEnabled && textMeasurer != null) {
            String string = key.toString();
            long sp = TextUnitKt.getSp(18);
            long jM5350getWhite0d7_KjU = Color.INSTANCE.m5350getWhite0d7_KjU();
            TextLayoutResult textLayoutResult = textMeasurer.m7548measurewNUYSr0(string, (PointerIconCompat.TYPE_GRAB & 2) != 0 ? TextStyle.INSTANCE.getDefault() : new TextStyle(chosenColor, sp, (FontWeight) null, (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, Color.m5311copywmQWz5c(jM5350getWhite0d7_KjU, (14 & 1) != 0 ? Color.m5315getAlphaimpl(jM5350getWhite0d7_KjU) : 0.6f, (14 & 2) != 0 ? Color.m5319getRedimpl(jM5350getWhite0d7_KjU) : 0.0f, (14 & 4) != 0 ? Color.m5318getGreenimpl(jM5350getWhite0d7_KjU) : 0.0f, (14 & 8) != 0 ? Color.m5316getBlueimpl(jM5350getWhite0d7_KjU) : 0.0f), (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, 0L, (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16775164, (DefaultConstructorMarker) null), (PointerIconCompat.TYPE_GRAB & 4) != 0 ? TextOverflow.INSTANCE.m8060getClipgIe3tQ8() : 0, (PointerIconCompat.TYPE_GRAB & 8) != 0, (PointerIconCompat.TYPE_GRAB & 16) != 0 ? Integer.MAX_VALUE : 0, (PointerIconCompat.TYPE_GRAB & 32) != 0 ? ConstraintsKt.Constraints$default(0, 0, 0, 0, 15, null) : 0L, (PointerIconCompat.TYPE_GRAB & 64) != 0 ? textMeasurer.defaultLayoutDirection : null, (PointerIconCompat.TYPE_GRAB & 128) != 0 ? textMeasurer.defaultDensity : null, (PointerIconCompat.TYPE_GRAB & 256) != 0 ? textMeasurer.defaultFontFamilyResolver : null, (PointerIconCompat.TYPE_GRAB & 512) != 0 ? false : false);
            long v1$iv$iv = Float.floatToRawIntBits(10.0f);
            long v2$iv$iv = Float.floatToRawIntBits(10.0f);
            TextPainterKt.m7558drawTextd8rzKo($this$drawInactiveVisualizations_u2d3IgeMak, textLayoutResult, (250 & 2) != 0 ? Color.INSTANCE.m5349getUnspecified0d7_KjU() : 0L, (250 & 4) != 0 ? Offset.INSTANCE.m5084getZeroF1C5BW0() : Offset.m5060constructorimpl((v1$iv$iv << 32) | (v2$iv$iv & 4294967295L)), (250 & 8) != 0 ? Float.NaN : 0.0f, (250 & 16) != 0 ? null : null, (250 & 32) != 0 ? null : null, (250 & 64) == 0 ? null : null, (250 & 128) != 0 ? DrawScope.INSTANCE.m5889getDefaultBlendMode0nO6VwU() : 0);
        }
    }

    /* JADX INFO: renamed from: drawLocalVisualizations-0XenJco$animation$default, reason: not valid java name */
    public static /* synthetic */ void m118drawLocalVisualizations0XenJco$animation$default(LookaheadAnimationVisualDebugHelper lookaheadAnimationVisualDebugHelper, ContentDrawScope contentDrawScope, long j, long j2, long j3, Rect rect, long j4, boolean z, float f, Object obj, TextMeasurer textMeasurer, int i, Object obj2) throws Throwable {
        TextMeasurer textMeasurer2;
        if ((i & 256) == 0) {
            textMeasurer2 = textMeasurer;
        } else {
            textMeasurer2 = null;
        }
        lookaheadAnimationVisualDebugHelper.m122drawLocalVisualizations0XenJco$animation(contentDrawScope, j, j2, j3, rect, j4, z, f, obj, textMeasurer2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r19v1, types: [androidx.compose.ui.graphics.drawscope.DrawScope] */
    /* JADX WARN: Type inference failed for: r19v3 */
    /* JADX WARN: Type inference failed for: r19v4, types: [androidx.compose.ui.graphics.drawscope.DrawScope] */
    /* JADX WARN: Type inference failed for: r3v7, types: [androidx.compose.ui.graphics.drawscope.DrawScope] */
    /* JADX WARN: Type inference failed for: r3v8 */
    /* JADX WARN: Type inference failed for: r3v9, types: [float] */
    /* JADX INFO: renamed from: drawLocalVisualizations-0XenJco$animation, reason: not valid java name */
    public final void m122drawLocalVisualizations0XenJco$animation(ContentDrawScope $this$drawLocalVisualizations_u2d0XenJco, long animationColor, long targetOffset, long targetSize, Rect currentRect, long center, boolean isShowKeyLabelEnabled, float strokeWidth, Object key, TextMeasurer textMeasurer) throws Throwable {
        ContentDrawScope $this$translate$iv;
        ContentDrawScope $this$translate$iv2;
        float left$iv;
        float f;
        float $this$translate$iv3;
        float left$iv2;
        float top$iv;
        long jM120chooseColorvNxB06k$animation;
        float left$iv3;
        float top$iv2;
        float $this$translate$iv4;
        float left$iv4;
        if (Color.m5314equalsimpl0(animationColor, Color.INSTANCE.m5348getTransparent0d7_KjU())) {
            return;
        }
        float highlightWidth = strokeWidth * 2.0f;
        if (Color.m5314equalsimpl0(animationColor, Color.INSTANCE.m5349getUnspecified0d7_KjU())) {
            $this$translate$iv = $this$drawLocalVisualizations_u2d0XenJco;
            DrawScope.m5881drawRectnJ9OG0$default($this$translate$iv, Color.INSTANCE.m5350getWhite0d7_KjU(), 0L, 0L, 0.0f, new Stroke(highlightWidth, 0.0f, 0, 0, null, 30, null), null, 0, 110, null);
            $this$translate$iv2 = $this$drawLocalVisualizations_u2d0XenJco;
            int bits$iv$iv$iv = (int) (targetOffset >> 32);
            float fIntBitsToFloat = Float.intBitsToFloat(bits$iv$iv$iv);
            int bits$iv$iv$iv2 = (int) (currentRect.m5103getTopLeftF1C5BW0() >> 32);
            left$iv = fIntBitsToFloat - Float.intBitsToFloat(bits$iv$iv$iv2);
            int bits$iv$iv$iv3 = (int) (targetOffset & 4294967295L);
            float fIntBitsToFloat2 = Float.intBitsToFloat(bits$iv$iv$iv3);
            f = 0.5f;
            int bits$iv$iv$iv4 = (int) (currentRect.m5103getTopLeftF1C5BW0() & 4294967295L);
            $this$translate$iv3 = fIntBitsToFloat2 - Float.intBitsToFloat(bits$iv$iv$iv4);
            $this$translate$iv2.getDrawContext().getTransform().translate(left$iv, $this$translate$iv3);
            try {
                DrawScope.m5881drawRectnJ9OG0$default($this$translate$iv2, Color.INSTANCE.m5350getWhite0d7_KjU(), 0L, targetSize, 0.0f, new Stroke(highlightWidth, 0.0f, 0, 0, null, 30, null), null, 0, 106, null);
                $this$translate$iv2.getDrawContext().getTransform().translate(-left$iv, -$this$translate$iv3);
                $this$translate$iv2 = $this$drawLocalVisualizations_u2d0XenJco;
                int bits$iv$iv$iv5 = (int) (targetOffset >> 32);
                float fIntBitsToFloat3 = Float.intBitsToFloat(bits$iv$iv$iv5);
                int bits$iv$iv$iv6 = (int) (currentRect.m5103getTopLeftF1C5BW0() >> 32);
                float fIntBitsToFloat4 = fIntBitsToFloat3 - Float.intBitsToFloat(bits$iv$iv$iv6);
                int bits$iv$iv$iv7 = (int) (this.debugOffset >> 32);
                left$iv = fIntBitsToFloat4 - Float.intBitsToFloat(bits$iv$iv$iv7);
                int bits$iv$iv$iv8 = (int) (targetOffset & 4294967295L);
                float fIntBitsToFloat5 = Float.intBitsToFloat(bits$iv$iv$iv8);
                int bits$iv$iv$iv9 = (int) (currentRect.m5103getTopLeftF1C5BW0() & 4294967295L);
                float fIntBitsToFloat6 = fIntBitsToFloat5 - Float.intBitsToFloat(bits$iv$iv$iv9);
                int bits$iv$iv$iv10 = (int) (this.debugOffset & 4294967295L);
                $this$translate$iv3 = fIntBitsToFloat6 - Float.intBitsToFloat(bits$iv$iv$iv10);
                $this$translate$iv2.getDrawContext().getTransform().translate(left$iv, $this$translate$iv3);
                int bits$iv$iv$iv11 = (int) (targetSize >> 32);
                try {
                    float left$iv5 = Float.intBitsToFloat(bits$iv$iv$iv11) * 0.5f;
                    int bits$iv$iv$iv12 = (int) (targetSize & 4294967295L);
                    float top$iv3 = Float.intBitsToFloat(bits$iv$iv$iv12) * 0.5f;
                    $this$translate$iv2.getDrawContext().getTransform().translate(left$iv5, top$iv3);
                    try {
                        try {
                            top$iv = top$iv3;
                            left$iv2 = left$iv5;
                        } catch (Throwable th) {
                            th = th;
                            top$iv = top$iv3;
                            left$iv2 = left$iv5;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        left$iv2 = left$iv5;
                        top$iv = top$iv3;
                    }
                    try {
                        DrawScope.m5877drawPathLG529CI$default($this$translate$iv2, this.debugPath, Color.INSTANCE.m5350getWhite0d7_KjU(), 0.0f, new Stroke(highlightWidth, 0.0f, 0, 0, PathEffect.Companion.dashPathEffect$default(PathEffect.INSTANCE, new float[]{20.0f, 10.0f}, 0.0f, 2, null), 14, null), null, 0, 52, null);
                        $this$translate$iv2.getDrawContext().getTransform().translate(-left$iv2, -top$iv);
                        $this$translate$iv2.getDrawContext().getTransform().translate(-left$iv, -$this$translate$iv3);
                        calculatePathCenter(3.5f * strokeWidth);
                        $this$translate$iv2 = $this$drawLocalVisualizations_u2d0XenJco;
                        int bits$iv$iv$iv13 = (int) (center >> 32);
                        left$iv = Float.intBitsToFloat(bits$iv$iv$iv13);
                        int bits$iv$iv$iv14 = (int) (center & 4294967295L);
                        $this$translate$iv3 = Float.intBitsToFloat(bits$iv$iv$iv14);
                        $this$translate$iv2.getDrawContext().getTransform().translate(left$iv, $this$translate$iv3);
                        try {
                            DrawScope.m5877drawPathLG529CI$default($this$translate$iv2, this.centerPath, Color.INSTANCE.m5350getWhite0d7_KjU(), 0.0f, null, null, 0, 60, null);
                            $this$translate$iv2.getDrawContext().getTransform().translate(-left$iv, -$this$translate$iv3);
                            jM120chooseColorvNxB06k$animation = m120chooseColorvNxB06k$animation(key);
                        } finally {
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        try {
                            $this$translate$iv2.getDrawContext().getTransform().translate(-left$iv2, -top$iv);
                            throw th;
                        } catch (Throwable th4) {
                            th = th4;
                            throw th;
                        }
                    }
                } catch (Throwable th5) {
                    th = th5;
                }
            } finally {
            }
        } else {
            f = 0.5f;
            jM120chooseColorvNxB06k$animation = animationColor;
        }
        long chosenColor = jM120chooseColorvNxB06k$animation;
        DrawScope.m5881drawRectnJ9OG0$default($this$drawLocalVisualizations_u2d0XenJco, chosenColor, 0L, 0L, 0.0f, new Stroke(strokeWidth, 0.0f, 0, 0, null, 30, null), null, 0, 110, null);
        $this$translate$iv2 = $this$drawLocalVisualizations_u2d0XenJco;
        int bits$iv$iv$iv15 = (int) (targetOffset >> 32);
        float fIntBitsToFloat7 = Float.intBitsToFloat(bits$iv$iv$iv15);
        int bits$iv$iv$iv16 = (int) (currentRect.m5103getTopLeftF1C5BW0() >> 32);
        left$iv = fIntBitsToFloat7 - Float.intBitsToFloat(bits$iv$iv$iv16);
        int bits$iv$iv$iv17 = (int) (targetOffset & 4294967295L);
        float fIntBitsToFloat8 = Float.intBitsToFloat(bits$iv$iv$iv17);
        int bits$iv$iv$iv18 = (int) (currentRect.m5103getTopLeftF1C5BW0() & 4294967295L);
        $this$translate$iv3 = fIntBitsToFloat8 - Float.intBitsToFloat(bits$iv$iv$iv18);
        $this$translate$iv2.getDrawContext().getTransform().translate(left$iv, $this$translate$iv3);
        try {
        } catch (Throwable th6) {
            th = th6;
        }
        try {
            DrawScope.m5881drawRectnJ9OG0$default($this$translate$iv2, chosenColor, 0L, targetSize, 0.0f, new Stroke(strokeWidth, 0.0f, 0, 0, null, 30, null), null, 0, 106, null);
            $this$translate$iv2.getDrawContext().getTransform().translate(-left$iv, -$this$translate$iv3);
            int bits$iv$iv$iv19 = (int) (targetOffset >> 32);
            float fIntBitsToFloat9 = Float.intBitsToFloat(bits$iv$iv$iv19);
            int bits$iv$iv$iv20 = (int) (currentRect.m5103getTopLeftF1C5BW0() >> 32);
            float fIntBitsToFloat10 = fIntBitsToFloat9 - Float.intBitsToFloat(bits$iv$iv$iv20);
            int bits$iv$iv$iv21 = (int) (this.debugOffset >> 32);
            left$iv = fIntBitsToFloat10 - Float.intBitsToFloat(bits$iv$iv$iv21);
            int bits$iv$iv$iv22 = (int) (targetOffset & 4294967295L);
            float fIntBitsToFloat11 = Float.intBitsToFloat(bits$iv$iv$iv22);
            int bits$iv$iv$iv23 = (int) (currentRect.m5103getTopLeftF1C5BW0() & 4294967295L);
            float fIntBitsToFloat12 = fIntBitsToFloat11 - Float.intBitsToFloat(bits$iv$iv$iv23);
            int bits$iv$iv$iv24 = (int) (this.debugOffset & 4294967295L);
            float top$iv4 = fIntBitsToFloat12 - Float.intBitsToFloat(bits$iv$iv$iv24);
            $this$translate$iv2 = $this$drawLocalVisualizations_u2d0XenJco;
            $this$translate$iv2.getDrawContext().getTransform().translate(left$iv, top$iv4);
            int bits$iv$iv$iv25 = (int) (targetSize >> 32);
            try {
                left$iv3 = Float.intBitsToFloat(bits$iv$iv$iv25) * f;
                int bits$iv$iv$iv26 = (int) (targetSize & 4294967295L);
                top$iv2 = Float.intBitsToFloat(bits$iv$iv$iv26) * f;
                $this$translate$iv2.getDrawContext().getTransform().translate(left$iv3, top$iv2);
            } catch (Throwable th7) {
                th = th7;
                $this$translate$iv = $this$translate$iv2;
                ?? $this$translate$iv5 = top$iv4;
            }
            try {
                try {
                    try {
                        $this$translate$iv3 = top$iv2;
                        left$iv = left$iv3;
                    } catch (Throwable th8) {
                        th = th8;
                        $this$translate$iv4 = top$iv2;
                        left$iv4 = left$iv3;
                    }
                } catch (Throwable th9) {
                    th = th9;
                    $this$translate$iv4 = top$iv2;
                    left$iv4 = left$iv3;
                }
                try {
                    DrawScope.m5877drawPathLG529CI$default($this$translate$iv2, this.debugPath, chosenColor, 0.0f, new Stroke(strokeWidth, 0.0f, 0, 0, PathEffect.Companion.dashPathEffect$default(PathEffect.INSTANCE, new float[]{20.0f, 10.0f}, 0.0f, 2, null), 14, null), null, 0, 52, null);
                    $this$translate$iv2.getDrawContext().getTransform().translate(-left$iv, -top$iv4);
                    calculatePathCenter(3.0f * strokeWidth);
                    $this$translate$iv2 = $this$drawLocalVisualizations_u2d0XenJco;
                    int bits$iv$iv$iv27 = (int) (center >> 32);
                    left$iv = Float.intBitsToFloat(bits$iv$iv$iv27);
                    int bits$iv$iv$iv28 = (int) (center & 4294967295L);
                    $this$translate$iv3 = Float.intBitsToFloat(bits$iv$iv$iv28);
                    $this$translate$iv2.getDrawContext().getTransform().translate(left$iv, $this$translate$iv3);
                    try {
                        DrawScope.m5877drawPathLG529CI$default($this$translate$iv2, this.centerPath, chosenColor, 0.0f, null, null, 0, 60, null);
                        if (!isShowKeyLabelEnabled || textMeasurer == null) {
                            return;
                        }
                        String string = key.toString();
                        long sp = TextUnitKt.getSp(18);
                        long jM5350getWhite0d7_KjU = Color.INSTANCE.m5350getWhite0d7_KjU();
                        TextLayoutResult textLayoutResult = textMeasurer.m7548measurewNUYSr0(string, (PointerIconCompat.TYPE_GRAB & 2) != 0 ? TextStyle.INSTANCE.getDefault() : new TextStyle(chosenColor, sp, (FontWeight) null, (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, Color.m5311copywmQWz5c(jM5350getWhite0d7_KjU, (14 & 1) != 0 ? Color.m5315getAlphaimpl(jM5350getWhite0d7_KjU) : 0.6f, (14 & 2) != 0 ? Color.m5319getRedimpl(jM5350getWhite0d7_KjU) : 0.0f, (14 & 4) != 0 ? Color.m5318getGreenimpl(jM5350getWhite0d7_KjU) : 0.0f, (14 & 8) != 0 ? Color.m5316getBlueimpl(jM5350getWhite0d7_KjU) : 0.0f), (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, 0L, (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16775164, (DefaultConstructorMarker) null), (PointerIconCompat.TYPE_GRAB & 4) != 0 ? TextOverflow.INSTANCE.m8060getClipgIe3tQ8() : 0, (PointerIconCompat.TYPE_GRAB & 8) != 0, (PointerIconCompat.TYPE_GRAB & 16) != 0 ? Integer.MAX_VALUE : 0, (PointerIconCompat.TYPE_GRAB & 32) != 0 ? ConstraintsKt.Constraints$default(0, 0, 0, 0, 15, null) : 0L, (PointerIconCompat.TYPE_GRAB & 64) != 0 ? textMeasurer.defaultLayoutDirection : null, (PointerIconCompat.TYPE_GRAB & 128) != 0 ? textMeasurer.defaultDensity : null, (PointerIconCompat.TYPE_GRAB & 256) != 0 ? textMeasurer.defaultFontFamilyResolver : null, (PointerIconCompat.TYPE_GRAB & 512) != 0 ? false : false);
                        long v1$iv$iv = Float.floatToRawIntBits(10.0f);
                        long v2$iv$iv = Float.floatToRawIntBits(10.0f);
                        TextPainterKt.m7558drawTextd8rzKo($this$drawLocalVisualizations_u2d0XenJco, textLayoutResult, (250 & 2) != 0 ? Color.INSTANCE.m5349getUnspecified0d7_KjU() : 0L, (250 & 4) != 0 ? Offset.INSTANCE.m5084getZeroF1C5BW0() : Offset.m5060constructorimpl((v1$iv$iv << 32) | (v2$iv$iv & 4294967295L)), (250 & 8) != 0 ? Float.NaN : 0.0f, (250 & 16) != 0 ? null : null, (250 & 32) != 0 ? null : null, (250 & 64) == 0 ? null : null, (250 & 128) != 0 ? DrawScope.INSTANCE.m5889getDefaultBlendMode0nO6VwU() : 0);
                        $this$translate$iv2 = $this$drawLocalVisualizations_u2d0XenJco;
                        int bits$iv$iv$iv29 = (int) (targetOffset >> 32);
                        float fIntBitsToFloat13 = Float.intBitsToFloat(bits$iv$iv$iv29);
                        int bits$iv$iv$iv30 = (int) (currentRect.m5103getTopLeftF1C5BW0() >> 32);
                        left$iv = fIntBitsToFloat13 - Float.intBitsToFloat(bits$iv$iv$iv30);
                        int bits$iv$iv$iv31 = (int) (targetOffset & 4294967295L);
                        float fIntBitsToFloat14 = Float.intBitsToFloat(bits$iv$iv$iv31);
                        int bits$iv$iv$iv32 = (int) (currentRect.m5103getTopLeftF1C5BW0() & 4294967295L);
                        $this$translate$iv3 = fIntBitsToFloat14 - Float.intBitsToFloat(bits$iv$iv$iv32);
                        $this$translate$iv2.getDrawContext().getTransform().translate(left$iv, $this$translate$iv3);
                        try {
                            long v1$iv$iv2 = Float.floatToRawIntBits(10.0f);
                            long v2$iv$iv2 = Float.floatToRawIntBits(10.0f);
                            TextPainterKt.m7558drawTextd8rzKo($this$translate$iv2, textLayoutResult, (250 & 2) != 0 ? Color.INSTANCE.m5349getUnspecified0d7_KjU() : 0L, (250 & 4) != 0 ? Offset.INSTANCE.m5084getZeroF1C5BW0() : Offset.m5060constructorimpl((v1$iv$iv2 << 32) | (v2$iv$iv2 & 4294967295L)), (250 & 8) != 0 ? Float.NaN : 0.0f, (250 & 16) != 0 ? null : null, (250 & 32) != 0 ? null : null, (250 & 64) == 0 ? null : null, (250 & 128) != 0 ? DrawScope.INSTANCE.m5889getDefaultBlendMode0nO6VwU() : 0);
                        } finally {
                        }
                    } finally {
                    }
                } catch (Throwable th10) {
                    th = th10;
                    throw th;
                }
            } catch (Throwable th11) {
                th = th11;
                throw th;
            }
        } catch (Throwable th12) {
            th = th12;
            throw th;
        }
    }

    /* JADX INFO: renamed from: drawMultipleMatchesElement-sW7UJKQ$animation, reason: not valid java name */
    public final void m123drawMultipleMatchesElementsW7UJKQ$animation(ContentDrawScope $this$drawMultipleMatchesElement_u2dsW7UJKQ, long multipleMatchesColor, boolean isShowKeyLabelEnabled, Object key, int numMatches, TextMeasurer textMeasurer, float strokeWidth) throws Throwable {
        String emoji;
        float highlightWidth = strokeWidth * 2.0f;
        DrawScope.m5881drawRectnJ9OG0$default($this$drawMultipleMatchesElement_u2dsW7UJKQ, Color.INSTANCE.m5350getWhite0d7_KjU(), 0L, 0L, 0.0f, new Stroke(highlightWidth, 0.0f, 0, 0, null, 30, null), null, 0, 110, null);
        DrawScope.m5881drawRectnJ9OG0$default($this$drawMultipleMatchesElement_u2dsW7UJKQ, multipleMatchesColor, 0L, 0L, 0.0f, new Stroke(strokeWidth, 0.0f, 0, 0, null, 30, null), null, 0, 110, null);
        if (isShowKeyLabelEnabled) {
            switch (numMatches) {
                case 2:
                    emoji = "2️⃣";
                    break;
                case 3:
                    emoji = "3️⃣";
                    break;
                case 4:
                    emoji = "4️⃣";
                    break;
                case 5:
                    emoji = "5️⃣";
                    break;
                case 6:
                    emoji = "6️⃣";
                    break;
                case 7:
                    emoji = "7️⃣";
                    break;
                case 8:
                    emoji = "8️⃣";
                    break;
                case 9:
                    emoji = "9️⃣";
                    break;
                default:
                    emoji = "> 9️⃣";
                    break;
            }
            TextLayoutResult textLayoutResult = textMeasurer.m7548measurewNUYSr0(key + ": " + emoji + " matches", (PointerIconCompat.TYPE_GRAB & 2) != 0 ? TextStyle.INSTANCE.getDefault() : new TextStyle(Color.INSTANCE.m5350getWhite0d7_KjU(), TextUnitKt.getSp(22), FontWeight.INSTANCE.getBold(), (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, Color.m5311copywmQWz5c(multipleMatchesColor, (14 & 1) != 0 ? Color.m5315getAlphaimpl(multipleMatchesColor) : 0.8f, (14 & 2) != 0 ? Color.m5319getRedimpl(multipleMatchesColor) : 0.0f, (14 & 4) != 0 ? Color.m5318getGreenimpl(multipleMatchesColor) : 0.0f, (14 & 8) != 0 ? Color.m5316getBlueimpl(multipleMatchesColor) : 0.0f), (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, 0L, (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16775160, (DefaultConstructorMarker) null), (PointerIconCompat.TYPE_GRAB & 4) != 0 ? TextOverflow.INSTANCE.m8060getClipgIe3tQ8() : 0, (PointerIconCompat.TYPE_GRAB & 8) != 0, (PointerIconCompat.TYPE_GRAB & 16) != 0 ? Integer.MAX_VALUE : 0, (PointerIconCompat.TYPE_GRAB & 32) != 0 ? ConstraintsKt.Constraints$default(0, 0, 0, 0, 15, null) : 0L, (PointerIconCompat.TYPE_GRAB & 64) != 0 ? textMeasurer.defaultLayoutDirection : null, (PointerIconCompat.TYPE_GRAB & 128) != 0 ? textMeasurer.defaultDensity : null, (PointerIconCompat.TYPE_GRAB & 256) != 0 ? textMeasurer.defaultFontFamilyResolver : null, (PointerIconCompat.TYPE_GRAB & 512) != 0 ? false : false);
            long v1$iv$iv = Float.floatToRawIntBits(10.0f);
            long v2$iv$iv = Float.floatToRawIntBits(10.0f);
            TextPainterKt.m7558drawTextd8rzKo($this$drawMultipleMatchesElement_u2dsW7UJKQ, textLayoutResult, (250 & 2) != 0 ? Color.INSTANCE.m5349getUnspecified0d7_KjU() : 0L, (250 & 4) != 0 ? Offset.INSTANCE.m5084getZeroF1C5BW0() : Offset.m5060constructorimpl((v1$iv$iv << 32) | (v2$iv$iv & 4294967295L)), (250 & 8) != 0 ? Float.NaN : 0.0f, (250 & 16) != 0 ? null : null, (250 & 32) != 0 ? null : null, (250 & 64) == 0 ? null : null, (250 & 128) != 0 ? DrawScope.INSTANCE.m5889getDefaultBlendMode0nO6VwU() : 0);
        }
    }

    /* JADX INFO: renamed from: drawUnmatchedElement-3IgeMak$animation, reason: not valid java name */
    public final void m125drawUnmatchedElement3IgeMak$animation(ContentDrawScope $this$drawUnmatchedElement_u2d3IgeMak, long unmatchedColor, boolean isShowKeyLabelEnabled, Object key, TextMeasurer textMeasurer, float strokeWidth) throws Throwable {
        DrawContext $this$withTransform_u24lambda_u240$iv$iv;
        float highlightWidth = strokeWidth * 2.0f;
        DrawScope.m5881drawRectnJ9OG0$default($this$drawUnmatchedElement_u2d3IgeMak, Color.INSTANCE.m5350getWhite0d7_KjU(), 0L, 0L, 0.0f, new Stroke(highlightWidth, 0.0f, 0, 0, null, 30, null), null, 0, 110, null);
        DrawScope.m5881drawRectnJ9OG0$default($this$drawUnmatchedElement_u2d3IgeMak, unmatchedColor, 0L, 0L, 0.0f, new Stroke(strokeWidth, 0.0f, 0, 0, null, 30, null), null, 0, 110, null);
        ContentDrawScope $this$clipRect_u2drOu3jXo_u24default$iv = $this$drawUnmatchedElement_u2d3IgeMak;
        int bits$iv$iv$iv$iv = (int) ($this$clipRect_u2drOu3jXo_u24default$iv.mo5887getSizeNHjbRc() >> 32);
        float right$iv = Float.intBitsToFloat(bits$iv$iv$iv$iv);
        int bits$iv$iv$iv$iv2 = (int) ($this$clipRect_u2drOu3jXo_u24default$iv.mo5887getSizeNHjbRc() & 4294967295L);
        float bottom$iv = Float.intBitsToFloat(bits$iv$iv$iv$iv2);
        int iM5302getIntersectrtfAjoo = ClipOp.INSTANCE.m5302getIntersectrtfAjoo();
        DrawContext $this$withTransform_u24lambda_u240$iv$iv2 = $this$clipRect_u2drOu3jXo_u24default$iv.getDrawContext();
        long previousSize$iv$iv = $this$withTransform_u24lambda_u240$iv$iv2.mo5808getSizeNHjbRc();
        $this$withTransform_u24lambda_u240$iv$iv2.getCanvas().save();
        try {
            DrawTransform $this$clipRect_rOu3jXo_u24lambda_u240$iv = $this$withTransform_u24lambda_u240$iv$iv2.getTransform();
            $this$clipRect_rOu3jXo_u24lambda_u240$iv.mo5811clipRectN_I0leg(0.0f, 0.0f, right$iv, bottom$iv, iM5302getIntersectrtfAjoo);
            try {
                int bits$iv$iv$iv = (int) ($this$clipRect_u2drOu3jXo_u24default$iv.mo5887getSizeNHjbRc() >> 32);
                try {
                    float w = Float.intBitsToFloat(bits$iv$iv$iv);
                    int bits$iv$iv$iv2 = (int) ($this$clipRect_u2drOu3jXo_u24default$iv.mo5887getSizeNHjbRc() & 4294967295L);
                    float h = Float.intBitsToFloat(bits$iv$iv$iv2);
                    float x = -h;
                    while (x < w) {
                        DrawContext $this$withTransform_u24lambda_u240$iv$iv3 = $this$withTransform_u24lambda_u240$iv$iv2;
                        try {
                            long jM5311copywmQWz5c = Color.m5311copywmQWz5c(unmatchedColor, (14 & 1) != 0 ? Color.m5315getAlphaimpl(unmatchedColor) : 0.3f, (14 & 2) != 0 ? Color.m5319getRedimpl(unmatchedColor) : 0.0f, (14 & 4) != 0 ? Color.m5318getGreenimpl(unmatchedColor) : 0.0f, (14 & 8) != 0 ? Color.m5316getBlueimpl(unmatchedColor) : 0.0f);
                            float y$iv = h;
                            float x$iv = x;
                            long v1$iv$iv = Float.floatToRawIntBits(x$iv);
                            float h2 = h;
                            float x2 = x;
                            long v2$iv$iv = Float.floatToRawIntBits(y$iv);
                            long jM5060constructorimpl = Offset.m5060constructorimpl((v1$iv$iv << 32) | (v2$iv$iv & 4294967295L));
                            float x$iv2 = x2 + h2;
                            long v1$iv$iv2 = Float.floatToRawIntBits(x$iv2);
                            long v2$iv$iv2 = Float.floatToRawIntBits(0.0f);
                            DrawScope.m5873drawLineNGM6Ib0$default($this$clipRect_u2drOu3jXo_u24default$iv, jM5311copywmQWz5c, jM5060constructorimpl, Offset.m5060constructorimpl((v1$iv$iv2 << 32) | (v2$iv$iv2 & 4294967295L)), strokeWidth, 0, null, 0.0f, null, 0, 496, null);
                            x = x2 + (4.0f * strokeWidth);
                            $this$withTransform_u24lambda_u240$iv$iv2 = $this$withTransform_u24lambda_u240$iv$iv3;
                            h = h2;
                        } catch (Throwable th) {
                            th = th;
                            $this$withTransform_u24lambda_u240$iv$iv = $this$withTransform_u24lambda_u240$iv$iv3;
                            $this$withTransform_u24lambda_u240$iv$iv.getCanvas().restore();
                            $this$withTransform_u24lambda_u240$iv$iv.mo5809setSizeuvyYCjk(previousSize$iv$iv);
                            throw th;
                        }
                    }
                    DrawContext $this$withTransform_u24lambda_u240$iv$iv4 = $this$withTransform_u24lambda_u240$iv$iv2;
                    $this$withTransform_u24lambda_u240$iv$iv4.getCanvas().restore();
                    $this$withTransform_u24lambda_u240$iv$iv4.mo5809setSizeuvyYCjk(previousSize$iv$iv);
                    if (isShowKeyLabelEnabled) {
                        TextLayoutResult textLayoutResult = textMeasurer.m7548measurewNUYSr0(key + ": 0️⃣ matches", (PointerIconCompat.TYPE_GRAB & 2) != 0 ? TextStyle.INSTANCE.getDefault() : new TextStyle(Color.INSTANCE.m5350getWhite0d7_KjU(), TextUnitKt.getSp(22), FontWeight.INSTANCE.getBold(), (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, Color.m5311copywmQWz5c(unmatchedColor, (14 & 1) != 0 ? Color.m5315getAlphaimpl(unmatchedColor) : 0.8f, (14 & 2) != 0 ? Color.m5319getRedimpl(unmatchedColor) : 0.0f, (14 & 4) != 0 ? Color.m5318getGreenimpl(unmatchedColor) : 0.0f, (14 & 8) != 0 ? Color.m5316getBlueimpl(unmatchedColor) : 0.0f), (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, 0L, (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16775160, (DefaultConstructorMarker) null), (PointerIconCompat.TYPE_GRAB & 4) != 0 ? TextOverflow.INSTANCE.m8060getClipgIe3tQ8() : 0, (PointerIconCompat.TYPE_GRAB & 8) != 0, (PointerIconCompat.TYPE_GRAB & 16) != 0 ? Integer.MAX_VALUE : 0, (PointerIconCompat.TYPE_GRAB & 32) != 0 ? ConstraintsKt.Constraints$default(0, 0, 0, 0, 15, null) : 0L, (PointerIconCompat.TYPE_GRAB & 64) != 0 ? textMeasurer.defaultLayoutDirection : null, (PointerIconCompat.TYPE_GRAB & 128) != 0 ? textMeasurer.defaultDensity : null, (PointerIconCompat.TYPE_GRAB & 256) != 0 ? textMeasurer.defaultFontFamilyResolver : null, (PointerIconCompat.TYPE_GRAB & 512) != 0 ? false : false);
                        long v1$iv$iv3 = Float.floatToRawIntBits(10.0f);
                        long v2$iv$iv3 = Float.floatToRawIntBits(10.0f);
                        TextPainterKt.m7558drawTextd8rzKo($this$drawUnmatchedElement_u2d3IgeMak, textLayoutResult, (250 & 2) != 0 ? Color.INSTANCE.m5349getUnspecified0d7_KjU() : 0L, (250 & 4) != 0 ? Offset.INSTANCE.m5084getZeroF1C5BW0() : Offset.m5060constructorimpl((v1$iv$iv3 << 32) | (v2$iv$iv3 & 4294967295L)), (250 & 8) != 0 ? Float.NaN : 0.0f, (250 & 16) != 0 ? null : null, (250 & 32) != 0 ? null : null, (250 & 64) == 0 ? null : null, (250 & 128) != 0 ? DrawScope.INSTANCE.m5889getDefaultBlendMode0nO6VwU() : 0);
                    }
                } catch (Throwable th2) {
                    th = th2;
                    $this$withTransform_u24lambda_u240$iv$iv = $this$withTransform_u24lambda_u240$iv$iv2;
                }
            } catch (Throwable th3) {
                th = th3;
                $this$withTransform_u24lambda_u240$iv$iv = $this$withTransform_u24lambda_u240$iv$iv2;
            }
        } catch (Throwable th4) {
            th = th4;
            $this$withTransform_u24lambda_u240$iv$iv = $this$withTransform_u24lambda_u240$iv$iv2;
        }
    }

    /* JADX INFO: renamed from: findPositionAlongPerimeter-tuRUvjQ, reason: not valid java name */
    private final long m119findPositionAlongPerimetertuRUvjQ(float distanceTraveled) {
        float width = (int) (this.sharedTransitionScopeSize >> 32);
        float height = (int) (this.sharedTransitionScopeSize & 4294967295L);
        if (distanceTraveled <= width) {
            long v1$iv$iv = Float.floatToRawIntBits(distanceTraveled);
            long v2$iv$iv = Float.floatToRawIntBits(0.0f);
            return Offset.m5060constructorimpl((v1$iv$iv << 32) | (4294967295L & v2$iv$iv));
        }
        if (distanceTraveled <= width + height) {
            float y$iv = distanceTraveled - width;
            long v1$iv$iv2 = Float.floatToRawIntBits(width);
            long v2$iv$iv2 = Float.floatToRawIntBits(y$iv);
            return Offset.m5060constructorimpl((v1$iv$iv2 << 32) | (4294967295L & v2$iv$iv2));
        }
        if (distanceTraveled > (width * 2.0f) + height) {
            float y$iv2 = ((height * 2.0f) + (2.0f * width)) - distanceTraveled;
            long v1$iv$iv3 = Float.floatToRawIntBits(0.0f);
            long v2$iv$iv3 = Float.floatToRawIntBits(y$iv2);
            return Offset.m5060constructorimpl((v1$iv$iv3 << 32) | (4294967295L & v2$iv$iv3));
        }
        float x$iv = ((2.0f * width) + height) - distanceTraveled;
        long v1$iv$iv4 = Float.floatToRawIntBits(x$iv);
        long v2$iv$iv4 = Float.floatToRawIntBits(height);
        return Offset.m5060constructorimpl((v1$iv$iv4 << 32) | (4294967295L & v2$iv$iv4));
    }

    private final void calculatePathCenter(float diamondWidth) {
        this.centerPath.rewind();
        Path $this$calculatePathCenter_u24lambda_u240 = this.centerPath;
        $this$calculatePathCenter_u24lambda_u240.moveTo(0.0f, -diamondWidth);
        $this$calculatePathCenter_u24lambda_u240.lineTo(diamondWidth, 0.0f);
        $this$calculatePathCenter_u24lambda_u240.lineTo(0.0f, diamondWidth);
        $this$calculatePathCenter_u24lambda_u240.lineTo(-diamondWidth, 0.0f);
        $this$calculatePathCenter_u24lambda_u240.close();
    }

    public static /* synthetic */ void calculatePath$animation$default(LookaheadAnimationVisualDebugHelper lookaheadAnimationVisualDebugHelper, FiniteAnimationSpec finiteAnimationSpec, Rect rect, Rect rect2, Rect rect3, int i, Object obj) {
        Rect rectM5107Rect3MmeM6k;
        if ((i & 8) == 0) {
            rectM5107Rect3MmeM6k = rect3;
        } else {
            long v1$iv$iv = Float.floatToRawIntBits(0.0f);
            long v2$iv$iv = Float.floatToRawIntBits(0.0f);
            rectM5107Rect3MmeM6k = RectKt.m5107Rect3MmeM6k(Offset.m5060constructorimpl((v1$iv$iv << 32) | (4294967295L & v2$iv$iv)), 0.0f);
        }
        lookaheadAnimationVisualDebugHelper.calculatePath$animation(finiteAnimationSpec, rect, rect2, rectM5107Rect3MmeM6k);
    }

    public final void calculatePath$animation(FiniteAnimationSpec<Rect> spec, Rect current, Rect target, Rect initialVelocity) {
        Rect rect;
        Rect rect2;
        long j;
        long j2;
        char c;
        TargetBasedAnimation pathAnim;
        Rect startValue;
        this.debugPath.rewind();
        if ((spec instanceof TweenSpec) || (spec instanceof SnapSpec)) {
            rect = current;
            rect2 = target;
            j = -9223372034707292160L;
            j2 = 4294967295L;
            c = ' ';
        } else if ((spec instanceof ArcAnimationSpec) && ArcMode.m201equalsimpl0(((ArcAnimationSpec) spec).getMode(), ArcMode.INSTANCE.m207getArcLinear9TMq4())) {
            rect = current;
            rect2 = target;
            j = -9223372034707292160L;
            j2 = 4294967295L;
            c = ' ';
        } else {
            TargetBasedAnimation pathAnim2 = AnimationKt.TargetBasedAnimation(spec, VectorConvertersKt.getVectorConverter(Rect.INSTANCE), current, target, initialVelocity);
            long durationNanos = pathAnim2.getDurationNanos();
            Rect startValue2 = (Rect) pathAnim2.getValueFromNanos(0L);
            int i = 0;
            while (true) {
                long playTime = durationNanos - ((long) (durationNanos * (i / (400 - 1))));
                Rect rectAtTime = (Rect) pathAnim2.getValueFromNanos(playTime);
                long point = rectAtTime.m5098getCenterF1C5BW0();
                Path path = this.debugPath;
                if (i == 0) {
                    startValue = startValue2;
                    int bits$iv$iv$iv = (int) (point >> 32);
                    pathAnim = pathAnim2;
                    int bits$iv$iv$iv2 = (int) (point & 4294967295L);
                    path.moveTo(Float.intBitsToFloat(bits$iv$iv$iv), Float.intBitsToFloat(bits$iv$iv$iv2));
                } else {
                    pathAnim = pathAnim2;
                    startValue = startValue2;
                    int bits$iv$iv$iv3 = (int) (point >> 32);
                    int bits$iv$iv$iv4 = (int) (point & 4294967295L);
                    path.lineTo(Float.intBitsToFloat(bits$iv$iv$iv3), Float.intBitsToFloat(bits$iv$iv$iv4));
                }
                if (i == 400) {
                    this.debugPath.mo5205translatek4lQ0M(Offset.m5060constructorimpl(startValue.m5098getCenterF1C5BW0() ^ (-9223372034707292160L)));
                    this.debugOffset = Offset.m5072minusMKHz9U(target.m5098getCenterF1C5BW0(), startValue.m5098getCenterF1C5BW0());
                    return;
                } else {
                    i++;
                    pathAnim2 = pathAnim;
                    startValue2 = startValue;
                }
            }
        }
        Path path2 = this.debugPath;
        int bits$iv$iv$iv5 = (int) (rect.m5098getCenterF1C5BW0() >> c);
        float fIntBitsToFloat = Float.intBitsToFloat(bits$iv$iv$iv5);
        int bits$iv$iv$iv6 = (int) (rect.m5098getCenterF1C5BW0() & j2);
        path2.moveTo(fIntBitsToFloat, Float.intBitsToFloat(bits$iv$iv$iv6));
        Path path3 = this.debugPath;
        int bits$iv$iv$iv7 = (int) (rect2.m5098getCenterF1C5BW0() >> c);
        float fIntBitsToFloat2 = Float.intBitsToFloat(bits$iv$iv$iv7);
        int bits$iv$iv$iv8 = (int) (rect2.m5098getCenterF1C5BW0() & j2);
        path3.lineTo(fIntBitsToFloat2, Float.intBitsToFloat(bits$iv$iv$iv8));
        this.debugPath.mo5205translatek4lQ0M(Offset.m5060constructorimpl(rect.m5098getCenterF1C5BW0() ^ j));
        this.debugOffset = Offset.m5072minusMKHz9U(rect2.m5098getCenterF1C5BW0(), rect.m5098getCenterF1C5BW0());
    }
}
