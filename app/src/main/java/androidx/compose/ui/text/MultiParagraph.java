package androidx.compose.ui.text;

import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.graphics.AndroidPath_androidKt;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.font.DelegatingFontLoaderForDeprecatedUsage_androidKt;
import androidx.compose.ui.text.font.Font;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.internal.InlineClassHelperKt;
import androidx.compose.ui.text.platform.AndroidMultiParagraphDraw_androidKt;
import androidx.compose.ui.text.style.ResolvedTextDirection;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextOverflow;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Density;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Ref;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: MultiParagraph.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000à\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u001b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0014\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0016\b\u0007\u0018\u00002\u00020\u0001B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bB-\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\f\u001a\u00020\r¢\u0006\u0004\b\n\u0010\u000eB-\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000f\u001a\u00020\u0010¢\u0006\u0004\b\n\u0010\u0011B[\b\u0017\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u0012\u0006\u0010\u0014\u001a\u00020\u0015\u0012\u0014\b\u0002\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\u00180\u0017\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\u0006\u0010\u001a\u001a\u00020\u001b\u0012\u0006\u0010\u001c\u001a\u00020\u001d¢\u0006\u0004\b\n\u0010\u001eB[\b\u0017\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u0012\u0006\u0010\u0014\u001a\u00020\u0015\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\u0006\u0010\u001a\u001a\u00020\u001b\u0012\u0006\u0010\u001f\u001a\u00020 \u0012\u0014\b\u0002\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\u00180\u0017\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\f\u001a\u00020\r¢\u0006\u0004\b\n\u0010!B[\b\u0017\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u0012\u0006\u0010\u0014\u001a\u00020\u0015\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u001a\u001a\u00020\u001b\u0012\u0006\u0010\u001f\u001a\u00020 \u0012\u0014\b\u0002\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\u00180\u0017\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\f\u001a\u00020\r¢\u0006\u0004\b\n\u0010\"B[\b\u0016\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u0012\u0006\u0010\u0014\u001a\u00020\u0015\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u001a\u001a\u00020\u001b\u0012\u0006\u0010\u001f\u001a\u00020 \u0012\u0014\b\u0002\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\u00180\u0017\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010#J9\u0010B\u001a\u00020C2\u0006\u0010D\u001a\u00020E2\b\b\u0002\u0010F\u001a\u00020G2\n\b\u0002\u0010H\u001a\u0004\u0018\u00010I2\n\b\u0002\u0010J\u001a\u0004\u0018\u00010KH\u0007¢\u0006\u0004\bL\u0010MJM\u0010B\u001a\u00020C2\u0006\u0010D\u001a\u00020E2\b\b\u0002\u0010F\u001a\u00020G2\n\b\u0002\u0010H\u001a\u0004\u0018\u00010I2\n\b\u0002\u0010J\u001a\u0004\u0018\u00010K2\n\b\u0002\u0010N\u001a\u0004\u0018\u00010O2\b\b\u0002\u0010P\u001a\u00020Q¢\u0006\u0004\bR\u0010SJU\u0010B\u001a\u00020C2\u0006\u0010D\u001a\u00020E2\u0006\u0010T\u001a\u00020U2\b\b\u0002\u0010V\u001a\u00020\u00102\n\b\u0002\u0010H\u001a\u0004\u0018\u00010I2\n\b\u0002\u0010J\u001a\u0004\u0018\u00010K2\n\b\u0002\u0010N\u001a\u0004\u0018\u00010O2\b\b\u0002\u0010P\u001a\u00020Q¢\u0006\u0004\bW\u0010XJ\u0016\u0010Y\u001a\u00020Z2\u0006\u0010[\u001a\u00020\u00072\u0006\u0010\\\u001a\u00020\u0007J\u000e\u0010]\u001a\u00020\u00072\u0006\u0010^\u001a\u00020\u0010J\u0015\u0010_\u001a\u00020\u00072\u0006\u0010`\u001a\u00020a¢\u0006\u0004\bb\u0010cJ%\u0010d\u001a\u00020e2\u0006\u0010f\u001a\u00020<2\u0006\u0010g\u001a\u00020h2\u0006\u0010i\u001a\u00020j¢\u0006\u0004\bk\u0010lJ\u000e\u0010m\u001a\u00020<2\u0006\u0010n\u001a\u00020\u0007J'\u0010o\u001a\u00020p2\u0006\u0010q\u001a\u00020e2\u0006\u0010r\u001a\u00020p2\b\b\u0001\u0010s\u001a\u00020\u0007¢\u0006\u0004\bt\u0010uJ\u0016\u0010v\u001a\u00020\u00102\u0006\u0010n\u001a\u00020\u00072\u0006\u0010w\u001a\u00020\rJ\u000e\u0010x\u001a\u00020y2\u0006\u0010n\u001a\u00020\u0007J\u000e\u0010z\u001a\u00020y2\u0006\u0010n\u001a\u00020\u0007J\u0015\u0010{\u001a\u00020e2\u0006\u0010n\u001a\u00020\u0007¢\u0006\u0004\b|\u0010}J\u000e\u0010~\u001a\u00020<2\u0006\u0010n\u001a\u00020\u0007J\u000e\u0010\u007f\u001a\u00020\u00072\u0006\u0010n\u001a\u00020\u0007J\u0010\u0010\u0080\u0001\u001a\u00020\u00102\u0007\u0010\u0081\u0001\u001a\u00020\u0007J\u0010\u0010\u0082\u0001\u001a\u00020\u00102\u0007\u0010\u0081\u0001\u001a\u00020\u0007J\u0010\u0010\u0083\u0001\u001a\u00020\u00102\u0007\u0010\u0081\u0001\u001a\u00020\u0007J\u0010\u0010\u0084\u0001\u001a\u00020\u00102\u0007\u0010\u0081\u0001\u001a\u00020\u0007J\u0010\u0010\u0085\u0001\u001a\u00020\u00102\u0007\u0010\u0081\u0001\u001a\u00020\u0007J\u0010\u0010\u0086\u0001\u001a\u00020\u00102\u0007\u0010\u0081\u0001\u001a\u00020\u0007J\u0010\u0010\u0087\u0001\u001a\u00020\u00102\u0007\u0010\u0081\u0001\u001a\u00020\u0007J\u0010\u0010\u0088\u0001\u001a\u00020\u00072\u0007\u0010\u0081\u0001\u001a\u00020\u0007J\u001b\u0010\u0089\u0001\u001a\u00020\u00072\u0007\u0010\u0081\u0001\u001a\u00020\u00072\t\b\u0002\u0010\u008a\u0001\u001a\u00020\rJ\u0010\u0010\u008b\u0001\u001a\u00020\r2\u0007\u0010\u0081\u0001\u001a\u00020\u0007J\u0011\u0010\u008c\u0001\u001a\u00020C2\u0006\u0010n\u001a\u00020\u0007H\u0002J\u0011\u0010\u008d\u0001\u001a\u00020C2\u0006\u0010n\u001a\u00020\u0007H\u0002J\u0012\u0010\u008e\u0001\u001a\u00020C2\u0007\u0010\u0081\u0001\u001a\u00020\u0007H\u0002R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'R\u0014\u0010\u0012\u001a\u00020\u00138BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b(\u0010)R\u0011\u0010*\u001a\u00020\u00108F¢\u0006\u0006\u001a\u0004\b+\u0010,R\u0011\u0010-\u001a\u00020\u00108F¢\u0006\u0006\u001a\u0004\b.\u0010,R\u0011\u0010/\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b0\u00101R\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b2\u0010,R\u0011\u00103\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b4\u0010,R\u0011\u00105\u001a\u00020\u00108F¢\u0006\u0006\u001a\u0004\b6\u0010,R\u0011\u00107\u001a\u00020\u00108F¢\u0006\u0006\u001a\u0004\b8\u0010,R\u0011\u00109\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b:\u0010'R\u0019\u0010;\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010<0\u0017¢\u0006\b\n\u0000\u001a\u0004\b=\u0010>R\u001a\u0010?\u001a\b\u0012\u0004\u0012\u00020@0\u0017X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\bA\u0010>¨\u0006\u008f\u0001"}, d2 = {"Landroidx/compose/ui/text/MultiParagraph;", "", "intrinsics", "Landroidx/compose/ui/text/MultiParagraphIntrinsics;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "maxLines", "", "overflow", "Landroidx/compose/ui/text/style/TextOverflow;", "<init>", "(Landroidx/compose/ui/text/MultiParagraphIntrinsics;JIILkotlin/jvm/internal/DefaultConstructorMarker;)V", "ellipsis", "", "(Landroidx/compose/ui/text/MultiParagraphIntrinsics;JIZLkotlin/jvm/internal/DefaultConstructorMarker;)V", "width", "", "(Landroidx/compose/ui/text/MultiParagraphIntrinsics;IZF)V", "annotatedString", "Landroidx/compose/ui/text/AnnotatedString;", "style", "Landroidx/compose/ui/text/TextStyle;", "placeholders", "", "Landroidx/compose/ui/text/AnnotatedString$Range;", "Landroidx/compose/ui/text/Placeholder;", "density", "Landroidx/compose/ui/unit/Density;", "resourceLoader", "Landroidx/compose/ui/text/font/Font$ResourceLoader;", "(Landroidx/compose/ui/text/AnnotatedString;Landroidx/compose/ui/text/TextStyle;Ljava/util/List;IZFLandroidx/compose/ui/unit/Density;Landroidx/compose/ui/text/font/Font$ResourceLoader;)V", "fontFamilyResolver", "Landroidx/compose/ui/text/font/FontFamily$Resolver;", "(Landroidx/compose/ui/text/AnnotatedString;Landroidx/compose/ui/text/TextStyle;FLandroidx/compose/ui/unit/Density;Landroidx/compose/ui/text/font/FontFamily$Resolver;Ljava/util/List;IZ)V", "(Landroidx/compose/ui/text/AnnotatedString;Landroidx/compose/ui/text/TextStyle;JLandroidx/compose/ui/unit/Density;Landroidx/compose/ui/text/font/FontFamily$Resolver;Ljava/util/List;IZLkotlin/jvm/internal/DefaultConstructorMarker;)V", "(Landroidx/compose/ui/text/AnnotatedString;Landroidx/compose/ui/text/TextStyle;JLandroidx/compose/ui/unit/Density;Landroidx/compose/ui/text/font/FontFamily$Resolver;Ljava/util/List;IILkotlin/jvm/internal/DefaultConstructorMarker;)V", "getIntrinsics", "()Landroidx/compose/ui/text/MultiParagraphIntrinsics;", "getMaxLines", "()I", "getAnnotatedString", "()Landroidx/compose/ui/text/AnnotatedString;", "minIntrinsicWidth", "getMinIntrinsicWidth", "()F", "maxIntrinsicWidth", "getMaxIntrinsicWidth", "didExceedMaxLines", "getDidExceedMaxLines", "()Z", "getWidth", "height", "getHeight", "firstBaseline", "getFirstBaseline", "lastBaseline", "getLastBaseline", "lineCount", "getLineCount", "placeholderRects", "Landroidx/compose/ui/geometry/Rect;", "getPlaceholderRects", "()Ljava/util/List;", "paragraphInfoList", "Landroidx/compose/ui/text/ParagraphInfo;", "getParagraphInfoList$ui_text", "paint", "", "canvas", "Landroidx/compose/ui/graphics/Canvas;", TypedValues.Custom.S_COLOR, "Landroidx/compose/ui/graphics/Color;", "shadow", "Landroidx/compose/ui/graphics/Shadow;", "decoration", "Landroidx/compose/ui/text/style/TextDecoration;", "paint-RPmYEkk", "(Landroidx/compose/ui/graphics/Canvas;JLandroidx/compose/ui/graphics/Shadow;Landroidx/compose/ui/text/style/TextDecoration;)V", "drawStyle", "Landroidx/compose/ui/graphics/drawscope/DrawStyle;", "blendMode", "Landroidx/compose/ui/graphics/BlendMode;", "paint-LG529CI", "(Landroidx/compose/ui/graphics/Canvas;JLandroidx/compose/ui/graphics/Shadow;Landroidx/compose/ui/text/style/TextDecoration;Landroidx/compose/ui/graphics/drawscope/DrawStyle;I)V", "brush", "Landroidx/compose/ui/graphics/Brush;", "alpha", "paint-hn5TExg", "(Landroidx/compose/ui/graphics/Canvas;Landroidx/compose/ui/graphics/Brush;FLandroidx/compose/ui/graphics/Shadow;Landroidx/compose/ui/text/style/TextDecoration;Landroidx/compose/ui/graphics/drawscope/DrawStyle;I)V", "getPathForRange", "Landroidx/compose/ui/graphics/Path;", "start", "end", "getLineForVerticalPosition", "vertical", "getOffsetForPosition", "position", "Landroidx/compose/ui/geometry/Offset;", "getOffsetForPosition-k-4lQ0M", "(J)I", "getRangeForRect", "Landroidx/compose/ui/text/TextRange;", "rect", "granularity", "Landroidx/compose/ui/text/TextGranularity;", "inclusionStrategy", "Landroidx/compose/ui/text/TextInclusionStrategy;", "getRangeForRect-8-6BmAI", "(Landroidx/compose/ui/geometry/Rect;ILandroidx/compose/ui/text/TextInclusionStrategy;)J", "getBoundingBox", TypedValues.CycleType.S_WAVE_OFFSET, "fillBoundingBoxes", "", "range", "array", "arrayStart", "fillBoundingBoxes-8ffj60Q", "(J[FI)[F", "getHorizontalPosition", "usePrimaryDirection", "getParagraphDirection", "Landroidx/compose/ui/text/style/ResolvedTextDirection;", "getBidiRunDirection", "getWordBoundary", "getWordBoundary--jx7JFs", "(I)J", "getCursorRect", "getLineForOffset", "getLineLeft", "lineIndex", "getLineRight", "getLineTop", "getLineBaseline", "getLineBottom", "getLineHeight", "getLineWidth", "getLineStart", "getLineEnd", "visibleEnd", "isLineEllipsized", "requireIndexInRange", "requireIndexInRangeInclusiveEnd", "requireLineIndexInRange", "ui-text"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class MultiParagraph {
    public static final int $stable = 8;
    private final boolean didExceedMaxLines;
    private final float height;
    private final MultiParagraphIntrinsics intrinsics;
    private final int lineCount;
    private final int maxLines;
    private final List<ParagraphInfo> paragraphInfoList;
    private final List<Rect> placeholderRects;
    private final float width;

    public /* synthetic */ MultiParagraph(AnnotatedString annotatedString, TextStyle textStyle, long j, Density density, FontFamily.Resolver resolver, List list, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(annotatedString, textStyle, j, density, resolver, (List<AnnotatedString.Range<Placeholder>>) list, i, i2);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Constructor with `ellipsis: Boolean` is deprecated, pass TextOverflow instead")
    public /* synthetic */ MultiParagraph(AnnotatedString annotatedString, TextStyle textStyle, long j, Density density, FontFamily.Resolver resolver, List list, int i, boolean z, DefaultConstructorMarker defaultConstructorMarker) {
        this(annotatedString, textStyle, j, density, resolver, (List<AnnotatedString.Range<Placeholder>>) list, i, z);
    }

    public /* synthetic */ MultiParagraph(MultiParagraphIntrinsics multiParagraphIntrinsics, long j, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(multiParagraphIntrinsics, j, i, i2);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Constructor with `ellipsis: Boolean` is deprecated, pass TextOverflow instead")
    public /* synthetic */ MultiParagraph(MultiParagraphIntrinsics multiParagraphIntrinsics, long j, int i, boolean z, DefaultConstructorMarker defaultConstructorMarker) {
        this(multiParagraphIntrinsics, j, i, z);
    }

    private MultiParagraph(MultiParagraphIntrinsics intrinsics, long constraints, int maxLines, int overflow) {
        List<Rect> listPlus;
        int index$iv$iv;
        ParagraphInfo $this$lambda_u241_u240;
        Rect it;
        this.intrinsics = intrinsics;
        this.maxLines = maxLines;
        int i = 0;
        boolean value$iv = Constraints.m8105getMinWidthimpl(constraints) == 0 && Constraints.m8104getMinHeightimpl(constraints) == 0;
        if (!value$iv) {
            InlineClassHelperKt.throwIllegalArgumentException("Setting Constraints.minWidth and Constraints.minHeight is not supported, these should be the default zero values instead.");
        }
        float currentHeight = 0.0f;
        int currentLineCount = 0;
        boolean didExceedMaxLines = false;
        List paragraphInfoList = new ArrayList();
        List<ParagraphIntrinsicInfo> infoList$ui_text = this.intrinsics.getInfoList$ui_text();
        int index = 0;
        int size = infoList$ui_text.size();
        while (index < size) {
            ParagraphIntrinsicInfo paragraphInfo = infoList$ui_text.get(index);
            Paragraph paragraph = ParagraphKt.m7456ParagraphczeNHc(paragraphInfo.getIntrinsics(), ConstraintsKt.Constraints$default(0, Constraints.m8103getMaxWidthimpl(constraints), 0, Constraints.m8098getHasBoundedHeightimpl(constraints) ? RangesKt.coerceAtLeast(Constraints.m8102getMaxHeightimpl(constraints) - ParagraphKt.ceilToInt(currentHeight), i) : Constraints.m8102getMaxHeightimpl(constraints), 5, null), this.maxLines - currentLineCount, overflow);
            float paragraphTop = currentHeight;
            float paragraphBottom = currentHeight + paragraph.getHeight();
            currentHeight = paragraphBottom;
            int startLineIndex = currentLineCount;
            int endLineIndex = startLineIndex + paragraph.getLineCount();
            currentLineCount = endLineIndex;
            paragraphInfoList.add(new ParagraphInfo(paragraph, paragraphInfo.getStartIndex(), paragraphInfo.getEndIndex(), startLineIndex, endLineIndex, paragraphTop, paragraphBottom));
            if (paragraph.getDidExceedMaxLines() || (endLineIndex == this.maxLines && index != CollectionsKt.getLastIndex(this.intrinsics.getInfoList$ui_text()))) {
                didExceedMaxLines = true;
                break;
            } else {
                index++;
                i = 0;
            }
        }
        this.height = currentHeight;
        this.lineCount = currentLineCount;
        this.didExceedMaxLines = didExceedMaxLines;
        this.paragraphInfoList = paragraphInfoList;
        this.width = Constraints.m8103getMaxWidthimpl(constraints);
        ArrayList target$iv = new ArrayList(paragraphInfoList.size());
        int index$iv$iv2 = 0;
        int size2 = paragraphInfoList.size();
        while (index$iv$iv2 < size2) {
            Object item$iv$iv = paragraphInfoList.get(index$iv$iv2);
            ParagraphInfo $this$lambda_u241_u2402 = (ParagraphInfo) item$iv$iv;
            List<Rect> placeholderRects = $this$lambda_u241_u2402.getParagraph().getPlaceholderRects();
            Iterable target$iv2 = new ArrayList(placeholderRects.size());
            List<Rect> list = placeholderRects;
            int size3 = list.size();
            float currentHeight2 = currentHeight;
            int index$iv$iv3 = 0;
            while (index$iv$iv3 < size3) {
                Object item$iv$iv2 = list.get(index$iv$iv3);
                int i2 = size3;
                ArrayList arrayList = (Collection) target$iv2;
                List<Rect> list2 = list;
                Rect it2 = (Rect) item$iv$iv2;
                if (it2 != null) {
                    index$iv$iv = index$iv$iv3;
                    $this$lambda_u241_u240 = $this$lambda_u241_u2402;
                    it = $this$lambda_u241_u240.toGlobal(it2);
                } else {
                    index$iv$iv = index$iv$iv3;
                    $this$lambda_u241_u240 = $this$lambda_u241_u2402;
                    it = null;
                }
                arrayList.add(it);
                $this$lambda_u241_u2402 = $this$lambda_u241_u240;
                list = list2;
                index$iv$iv3 = index$iv$iv + 1;
                size3 = i2;
            }
            Iterable list$iv = (List) target$iv2;
            CollectionsKt.addAll(target$iv, list$iv);
            index$iv$iv2++;
            currentHeight = currentHeight2;
        }
        ArrayList arrayList2 = target$iv;
        if (arrayList2.size() < this.intrinsics.getPlaceholders().size()) {
            ArrayList arrayList3 = arrayList2;
            int size4 = this.intrinsics.getPlaceholders().size() - arrayList2.size();
            ArrayList arrayList4 = new ArrayList(size4);
            for (int i3 = 0; i3 < size4; i3++) {
                arrayList4.add(null);
            }
            listPlus = CollectionsKt.plus((Collection) arrayList3, (Iterable) arrayList4);
        } else {
            listPlus = arrayList2;
        }
        this.placeholderRects = listPlus;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ MultiParagraph(MultiParagraphIntrinsics multiParagraphIntrinsics, long j, int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        int i4;
        int iM8060getClipgIe3tQ8;
        if ((i3 & 4) == 0) {
            i4 = i;
        } else {
            i4 = Integer.MAX_VALUE;
        }
        if ((i3 & 8) == 0) {
            iM8060getClipgIe3tQ8 = i2;
        } else {
            iM8060getClipgIe3tQ8 = TextOverflow.INSTANCE.m8060getClipgIe3tQ8();
        }
        this(multiParagraphIntrinsics, j, i4, iM8060getClipgIe3tQ8, (DefaultConstructorMarker) null);
    }

    public final MultiParagraphIntrinsics getIntrinsics() {
        return this.intrinsics;
    }

    public final int getMaxLines() {
        return this.maxLines;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ MultiParagraph(MultiParagraphIntrinsics multiParagraphIntrinsics, long j, int i, boolean z, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        int i3;
        boolean z2;
        if ((i2 & 4) == 0) {
            i3 = i;
        } else {
            i3 = Integer.MAX_VALUE;
        }
        if ((i2 & 8) == 0) {
            z2 = z;
        } else {
            z2 = false;
        }
        this(multiParagraphIntrinsics, j, i3, z2, (DefaultConstructorMarker) null);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    private MultiParagraph(MultiParagraphIntrinsics intrinsics, long constraints, int maxLines, boolean ellipsis) {
        TextOverflow.Companion companion = TextOverflow.INSTANCE;
        this(intrinsics, constraints, maxLines, ellipsis ? companion.m8061getEllipsisgIe3tQ8() : companion.m8060getClipgIe3tQ8(), (DefaultConstructorMarker) null);
    }

    public /* synthetic */ MultiParagraph(MultiParagraphIntrinsics multiParagraphIntrinsics, int i, boolean z, float f, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(multiParagraphIntrinsics, (i2 & 2) != 0 ? Integer.MAX_VALUE : i, (i2 & 4) != 0 ? false : z, f);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    @Deprecated(message = "MultiParagraph that takes maximum allowed width is deprecated, pass constraints instead.", replaceWith = @ReplaceWith(expression = "MultiParagraph(intrinsics, Constraints(maxWidth = ceil(width).toInt()), maxLines, ellipsis)", imports = {"kotlin.math.ceil", "androidx.compose.ui.unit.Constraints"}))
    public MultiParagraph(MultiParagraphIntrinsics intrinsics, int maxLines, boolean ellipsis, float width) {
        long jConstraints$default = ConstraintsKt.Constraints$default(0, ParagraphKt.ceilToInt(width), 0, 0, 13, null);
        TextOverflow.Companion companion = TextOverflow.INSTANCE;
        this(intrinsics, jConstraints$default, maxLines, ellipsis ? companion.m8061getEllipsisgIe3tQ8() : companion.m8060getClipgIe3tQ8(), (DefaultConstructorMarker) null);
    }

    public /* synthetic */ MultiParagraph(AnnotatedString annotatedString, TextStyle textStyle, List list, int i, boolean z, float f, Density density, Font.ResourceLoader resourceLoader, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(annotatedString, textStyle, (List<AnnotatedString.Range<Placeholder>>) ((i2 & 4) != 0 ? CollectionsKt.emptyList() : list), (i2 & 8) != 0 ? Integer.MAX_VALUE : i, (i2 & 16) != 0 ? false : z, f, density, resourceLoader);
    }

    @Deprecated(message = "Font.ResourceLoader is deprecated, use fontFamilyResolver instead", replaceWith = @ReplaceWith(expression = "MultiParagraph(annotatedString, style, placeholders, maxLines, ellipsis, width, density, fontFamilyResolver)", imports = {}))
    public MultiParagraph(AnnotatedString annotatedString, TextStyle style, List<AnnotatedString.Range<Placeholder>> list, int maxLines, boolean ellipsis, float width, Density density, Font.ResourceLoader resourceLoader) {
        MultiParagraphIntrinsics multiParagraphIntrinsics = new MultiParagraphIntrinsics(annotatedString, style, list, density, DelegatingFontLoaderForDeprecatedUsage_androidKt.createFontFamilyResolver(resourceLoader));
        TextOverflow.Companion companion = TextOverflow.INSTANCE;
        this(multiParagraphIntrinsics, ConstraintsKt.Constraints$default(0, ParagraphKt.ceilToInt(width), 0, 0, 13, null), maxLines, ellipsis ? companion.m8061getEllipsisgIe3tQ8() : companion.m8060getClipgIe3tQ8(), (DefaultConstructorMarker) null);
    }

    public /* synthetic */ MultiParagraph(AnnotatedString annotatedString, TextStyle textStyle, float f, Density density, FontFamily.Resolver resolver, List list, int i, boolean z, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(annotatedString, textStyle, f, density, resolver, (List<AnnotatedString.Range<Placeholder>>) ((i2 & 32) != 0 ? CollectionsKt.emptyList() : list), (i2 & 64) != 0 ? Integer.MAX_VALUE : i, (i2 & 128) != 0 ? false : z);
    }

    @Deprecated(message = "MultiParagraph that takes maximum allowed width is deprecated, pass constraints instead.", replaceWith = @ReplaceWith(expression = "MultiParagraph(annotatedString, style, Constraints(maxWidth = ceil(width).toInt()), density, fontFamilyResolver, placeholders, maxLines, ellipsis)", imports = {"kotlin.math.ceil", "androidx.compose.ui.unit.Constraints"}))
    public MultiParagraph(AnnotatedString annotatedString, TextStyle style, float width, Density density, FontFamily.Resolver fontFamilyResolver, List<AnnotatedString.Range<Placeholder>> list, int maxLines, boolean ellipsis) {
        MultiParagraphIntrinsics multiParagraphIntrinsics = new MultiParagraphIntrinsics(annotatedString, style, list, density, fontFamilyResolver);
        TextOverflow.Companion companion = TextOverflow.INSTANCE;
        this(multiParagraphIntrinsics, ConstraintsKt.Constraints$default(0, ParagraphKt.ceilToInt(width), 0, 0, 13, null), maxLines, ellipsis ? companion.m8061getEllipsisgIe3tQ8() : companion.m8060getClipgIe3tQ8(), (DefaultConstructorMarker) null);
    }

    public /* synthetic */ MultiParagraph(AnnotatedString annotatedString, TextStyle textStyle, long j, Density density, FontFamily.Resolver resolver, List list, int i, boolean z, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(annotatedString, textStyle, j, density, resolver, (i2 & 32) != 0 ? CollectionsKt.emptyList() : list, (i2 & 64) != 0 ? Integer.MAX_VALUE : i, (i2 & 128) != 0 ? false : z, (DefaultConstructorMarker) null);
    }

    private MultiParagraph(AnnotatedString annotatedString, TextStyle style, long constraints, Density density, FontFamily.Resolver fontFamilyResolver, List<AnnotatedString.Range<Placeholder>> list, int maxLines, boolean ellipsis) {
        MultiParagraphIntrinsics multiParagraphIntrinsics = new MultiParagraphIntrinsics(annotatedString, style, list, density, fontFamilyResolver);
        TextOverflow.Companion companion = TextOverflow.INSTANCE;
        this(multiParagraphIntrinsics, constraints, maxLines, ellipsis ? companion.m8061getEllipsisgIe3tQ8() : companion.m8060getClipgIe3tQ8(), (DefaultConstructorMarker) null);
    }

    public /* synthetic */ MultiParagraph(AnnotatedString annotatedString, TextStyle textStyle, long j, Density density, FontFamily.Resolver resolver, List list, int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(annotatedString, textStyle, j, density, resolver, (i3 & 32) != 0 ? CollectionsKt.emptyList() : list, (i3 & 64) != 0 ? Integer.MAX_VALUE : i, (i3 & 128) != 0 ? TextOverflow.INSTANCE.m8060getClipgIe3tQ8() : i2, (DefaultConstructorMarker) null);
    }

    private MultiParagraph(AnnotatedString annotatedString, TextStyle style, long constraints, Density density, FontFamily.Resolver fontFamilyResolver, List<AnnotatedString.Range<Placeholder>> list, int maxLines, int overflow) {
        this(new MultiParagraphIntrinsics(annotatedString, style, list, density, fontFamilyResolver), constraints, maxLines, overflow, (DefaultConstructorMarker) null);
    }

    private final AnnotatedString getAnnotatedString() {
        return this.intrinsics.getAnnotatedString();
    }

    public final float getMinIntrinsicWidth() {
        return this.intrinsics.getMinIntrinsicWidth();
    }

    public final float getMaxIntrinsicWidth() {
        return this.intrinsics.getMaxIntrinsicWidth();
    }

    public final boolean getDidExceedMaxLines() {
        return this.didExceedMaxLines;
    }

    public final float getWidth() {
        return this.width;
    }

    public final float getHeight() {
        return this.height;
    }

    public final float getFirstBaseline() {
        if (this.paragraphInfoList.isEmpty()) {
            return 0.0f;
        }
        return this.paragraphInfoList.get(0).getParagraph().getFirstBaseline();
    }

    public final float getLastBaseline() {
        if (this.paragraphInfoList.isEmpty()) {
            return 0.0f;
        }
        ParagraphInfo $this$_get_lastBaseline__u24lambda_u240 = (ParagraphInfo) CollectionsKt.last((List) this.paragraphInfoList);
        return $this$_get_lastBaseline__u24lambda_u240.toGlobalYPosition($this$_get_lastBaseline__u24lambda_u240.getParagraph().getLastBaseline());
    }

    public final int getLineCount() {
        return this.lineCount;
    }

    public final List<Rect> getPlaceholderRects() {
        return this.placeholderRects;
    }

    public final List<ParagraphInfo> getParagraphInfoList$ui_text() {
        return this.paragraphInfoList;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Use the new paint function that takes canvas as the only required parameter.")
    /* JADX INFO: renamed from: paint-RPmYEkk, reason: not valid java name */
    public final /* synthetic */ void m7438paintRPmYEkk(Canvas canvas, long color, Shadow shadow, TextDecoration decoration) {
        canvas.save();
        List<ParagraphInfo> list = this.paragraphInfoList;
        int size = list.size();
        for (int index$iv = 0; index$iv < size; index$iv++) {
            Object item$iv = list.get(index$iv);
            ParagraphInfo it = (ParagraphInfo) item$iv;
            Paragraph.m7441paintLG529CI$default(it.getParagraph(), canvas, color, shadow, decoration, null, 0, 48, null);
            canvas.translate(0.0f, it.getParagraph().getHeight());
        }
        canvas.restore();
    }

    /* JADX INFO: renamed from: paint-LG529CI, reason: not valid java name */
    public final void m7437paintLG529CI(Canvas canvas, long color, Shadow shadow, TextDecoration decoration, DrawStyle drawStyle, int blendMode) {
        canvas.save();
        List<ParagraphInfo> list = this.paragraphInfoList;
        int size = list.size();
        for (int index$iv = 0; index$iv < size; index$iv++) {
            Object item$iv = list.get(index$iv);
            ParagraphInfo it = (ParagraphInfo) item$iv;
            it.getParagraph().mo7393paintLG529CI(canvas, color, shadow, decoration, drawStyle, blendMode);
            canvas.translate(0.0f, it.getParagraph().getHeight());
        }
        canvas.restore();
    }

    /* JADX INFO: renamed from: paint-hn5TExg, reason: not valid java name */
    public final void m7439painthn5TExg(Canvas canvas, Brush brush, float alpha, Shadow shadow, TextDecoration decoration, DrawStyle drawStyle, int blendMode) {
        AndroidMultiParagraphDraw_androidKt.m7828drawMultiParagraph7AXcY_I(this, canvas, brush, alpha, shadow, decoration, drawStyle, blendMode);
    }

    public final Path getPathForRange(final int start, final int end) {
        boolean value$iv = (start >= 0 && start <= end) && end <= getAnnotatedString().getText().length();
        if (!value$iv) {
            InlineClassHelperKt.throwIllegalArgumentException("Start(" + start + ") or End(" + end + ") is out of range [0.." + getAnnotatedString().getText().length() + "), or start > end!");
        }
        if (start == end) {
            return AndroidPath_androidKt.Path();
        }
        final Path path = AndroidPath_androidKt.Path();
        MultiParagraphKt.m7440findParagraphsByRangeSbBc2M(this.paragraphInfoList, TextRangeKt.TextRange(start, end), new Function1() { // from class: androidx.compose.ui.text.MultiParagraph$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MultiParagraph.getPathForRange$lambda$1(path, start, end, (ParagraphInfo) obj);
            }
        });
        return path;
    }

    static final Unit getPathForRange$lambda$1(Path $path, int $start, int $end, ParagraphInfo paragraphInfo) {
        Path.m5597addPathUv8p0NA$default($path, paragraphInfo.toGlobal(paragraphInfo.getParagraph().getPathForRange(paragraphInfo.toLocalIndex($start), paragraphInfo.toLocalIndex($end))), 0L, 2, null);
        return Unit.INSTANCE;
    }

    public final int getLineForVerticalPosition(float vertical) {
        int paragraphIndex = MultiParagraphKt.findParagraphByY(this.paragraphInfoList, vertical);
        ParagraphInfo $this$getLineForVerticalPosition_u24lambda_u240 = this.paragraphInfoList.get(paragraphIndex);
        if ($this$getLineForVerticalPosition_u24lambda_u240.getLength() == 0) {
            return $this$getLineForVerticalPosition_u24lambda_u240.getStartLineIndex();
        }
        return $this$getLineForVerticalPosition_u24lambda_u240.toGlobalLineIndex($this$getLineForVerticalPosition_u24lambda_u240.getParagraph().getLineForVerticalPosition($this$getLineForVerticalPosition_u24lambda_u240.toLocalYPosition(vertical)));
    }

    /* JADX INFO: renamed from: getOffsetForPosition-k-4lQ0M, reason: not valid java name */
    public final int m7434getOffsetForPositionk4lQ0M(long position) {
        int bits$iv$iv$iv = (int) (4294967295L & position);
        int paragraphIndex = MultiParagraphKt.findParagraphByY(this.paragraphInfoList, Float.intBitsToFloat(bits$iv$iv$iv));
        ParagraphInfo $this$getOffsetForPosition_k_4lQ0M_u24lambda_u240 = this.paragraphInfoList.get(paragraphIndex);
        if ($this$getOffsetForPosition_k_4lQ0M_u24lambda_u240.getLength() == 0) {
            return $this$getOffsetForPosition_k_4lQ0M_u24lambda_u240.getStartIndex();
        }
        return $this$getOffsetForPosition_k_4lQ0M_u24lambda_u240.toGlobalIndex($this$getOffsetForPosition_k_4lQ0M_u24lambda_u240.getParagraph().mo7389getOffsetForPositionk4lQ0M($this$getOffsetForPosition_k_4lQ0M_u24lambda_u240.m7449toLocalMKHz9U(position)));
    }

    /* JADX INFO: renamed from: getRangeForRect-8-6BmAI, reason: not valid java name */
    public final long m7435getRangeForRect86BmAI(Rect rect, int granularity, TextInclusionStrategy inclusionStrategy) {
        int firstParagraph = MultiParagraphKt.findParagraphByY(this.paragraphInfoList, rect.getTop());
        if (this.paragraphInfoList.get(firstParagraph).getBottom() >= rect.getBottom() || firstParagraph == CollectionsKt.getLastIndex(this.paragraphInfoList)) {
            ParagraphInfo $this$getRangeForRect_8_6BmAI_u24lambda_u240 = this.paragraphInfoList.get(firstParagraph);
            return ParagraphInfo.m7447toGlobalxdX6G0$default($this$getRangeForRect_8_6BmAI_u24lambda_u240, $this$getRangeForRect_8_6BmAI_u24lambda_u240.getParagraph().mo7391getRangeForRect86BmAI($this$getRangeForRect_8_6BmAI_u24lambda_u240.toLocal(rect), granularity, inclusionStrategy), false, 1, null);
        }
        int lastParagraph = MultiParagraphKt.findParagraphByY(this.paragraphInfoList, rect.getBottom());
        long startRange = TextRange.INSTANCE.m7578getZerod9O1mEE();
        while (TextRange.m7566equalsimpl0(startRange, TextRange.INSTANCE.m7578getZerod9O1mEE()) && firstParagraph <= lastParagraph) {
            ParagraphInfo $this$getRangeForRect_8_6BmAI_u24lambda_u241 = this.paragraphInfoList.get(firstParagraph);
            startRange = ParagraphInfo.m7447toGlobalxdX6G0$default($this$getRangeForRect_8_6BmAI_u24lambda_u241, $this$getRangeForRect_8_6BmAI_u24lambda_u241.getParagraph().mo7391getRangeForRect86BmAI($this$getRangeForRect_8_6BmAI_u24lambda_u241.toLocal(rect), granularity, inclusionStrategy), false, 1, null);
            firstParagraph++;
        }
        if (TextRange.m7566equalsimpl0(startRange, TextRange.INSTANCE.m7578getZerod9O1mEE())) {
            return TextRange.INSTANCE.m7578getZerod9O1mEE();
        }
        long endRange = TextRange.INSTANCE.m7578getZerod9O1mEE();
        while (TextRange.m7566equalsimpl0(endRange, TextRange.INSTANCE.m7578getZerod9O1mEE()) && firstParagraph <= lastParagraph) {
            ParagraphInfo $this$getRangeForRect_8_6BmAI_u24lambda_u242 = this.paragraphInfoList.get(lastParagraph);
            endRange = ParagraphInfo.m7447toGlobalxdX6G0$default($this$getRangeForRect_8_6BmAI_u24lambda_u242, $this$getRangeForRect_8_6BmAI_u24lambda_u242.getParagraph().mo7391getRangeForRect86BmAI($this$getRangeForRect_8_6BmAI_u24lambda_u242.toLocal(rect), granularity, inclusionStrategy), false, 1, null);
            lastParagraph--;
        }
        return TextRange.m7566equalsimpl0(endRange, TextRange.INSTANCE.m7578getZerod9O1mEE()) ? startRange : TextRangeKt.TextRange(TextRange.m7573getStartimpl(startRange), TextRange.m7568getEndimpl(endRange));
    }

    public final Rect getBoundingBox(int offset) {
        requireIndexInRange(offset);
        int paragraphIndex = MultiParagraphKt.findParagraphByIndex(this.paragraphInfoList, offset);
        ParagraphInfo $this$getBoundingBox_u24lambda_u240 = this.paragraphInfoList.get(paragraphIndex);
        return $this$getBoundingBox_u24lambda_u240.toGlobal($this$getBoundingBox_u24lambda_u240.getParagraph().getBoundingBox($this$getBoundingBox_u24lambda_u240.toLocalIndex(offset)));
    }

    /* JADX INFO: renamed from: fillBoundingBoxes-8ffj60Q, reason: not valid java name */
    public final float[] m7433fillBoundingBoxes8ffj60Q(final long range, final float[] array, int arrayStart) {
        requireIndexInRange(TextRange.m7571getMinimpl(range));
        requireIndexInRangeInclusiveEnd(TextRange.m7570getMaximpl(range));
        final Ref.IntRef currentArrayStart = new Ref.IntRef();
        currentArrayStart.element = arrayStart;
        final Ref.FloatRef currentHeight = new Ref.FloatRef();
        MultiParagraphKt.m7440findParagraphsByRangeSbBc2M(this.paragraphInfoList, range, new Function1() { // from class: androidx.compose.ui.text.MultiParagraph$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MultiParagraph.fillBoundingBoxes_8ffj60Q$lambda$0(range, array, currentArrayStart, currentHeight, (ParagraphInfo) obj);
            }
        });
        return array;
    }

    static final Unit fillBoundingBoxes_8ffj60Q$lambda$0(long $range, float[] $array, Ref.IntRef $currentArrayStart, Ref.FloatRef $currentHeight, ParagraphInfo paragraphInfo) {
        int paragraphStart = paragraphInfo.getStartIndex() > TextRange.m7571getMinimpl($range) ? paragraphInfo.getStartIndex() : TextRange.m7571getMinimpl($range);
        int paragraphEnd = paragraphInfo.getEndIndex() < TextRange.m7570getMaximpl($range) ? paragraphInfo.getEndIndex() : TextRange.m7570getMaximpl($range);
        long finalRange = TextRangeKt.TextRange(paragraphInfo.toLocalIndex(paragraphStart), paragraphInfo.toLocalIndex(paragraphEnd));
        paragraphInfo.getParagraph().mo7387fillBoundingBoxes8ffj60Q(finalRange, $array, $currentArrayStart.element);
        int currentArrayEnd = $currentArrayStart.element + (TextRange.m7569getLengthimpl(finalRange) * 4);
        for (int arrayIndex = $currentArrayStart.element; arrayIndex < currentArrayEnd; arrayIndex += 4) {
            int i = arrayIndex + 1;
            $array[i] = $array[i] + $currentHeight.element;
            int i2 = arrayIndex + 3;
            $array[i2] = $array[i2] + $currentHeight.element;
        }
        $currentArrayStart.element = currentArrayEnd;
        $currentHeight.element += paragraphInfo.getParagraph().getHeight();
        return Unit.INSTANCE;
    }

    public final float getHorizontalPosition(int offset, boolean usePrimaryDirection) {
        int paragraphIndex;
        requireIndexInRangeInclusiveEnd(offset);
        int length = getAnnotatedString().length();
        List<ParagraphInfo> list = this.paragraphInfoList;
        if (offset == length) {
            paragraphIndex = CollectionsKt.getLastIndex(list);
        } else {
            paragraphIndex = MultiParagraphKt.findParagraphByIndex(list, offset);
        }
        ParagraphInfo $this$getHorizontalPosition_u24lambda_u240 = this.paragraphInfoList.get(paragraphIndex);
        return $this$getHorizontalPosition_u24lambda_u240.getParagraph().getHorizontalPosition($this$getHorizontalPosition_u24lambda_u240.toLocalIndex(offset), usePrimaryDirection);
    }

    public final ResolvedTextDirection getParagraphDirection(int offset) {
        int paragraphIndex;
        requireIndexInRangeInclusiveEnd(offset);
        int length = getAnnotatedString().length();
        List<ParagraphInfo> list = this.paragraphInfoList;
        if (offset == length) {
            paragraphIndex = CollectionsKt.getLastIndex(list);
        } else {
            paragraphIndex = MultiParagraphKt.findParagraphByIndex(list, offset);
        }
        ParagraphInfo $this$getParagraphDirection_u24lambda_u240 = this.paragraphInfoList.get(paragraphIndex);
        return $this$getParagraphDirection_u24lambda_u240.getParagraph().getParagraphDirection($this$getParagraphDirection_u24lambda_u240.toLocalIndex(offset));
    }

    public final ResolvedTextDirection getBidiRunDirection(int offset) {
        int paragraphIndex;
        requireIndexInRangeInclusiveEnd(offset);
        int length = getAnnotatedString().length();
        List<ParagraphInfo> list = this.paragraphInfoList;
        if (offset == length) {
            paragraphIndex = CollectionsKt.getLastIndex(list);
        } else {
            paragraphIndex = MultiParagraphKt.findParagraphByIndex(list, offset);
        }
        ParagraphInfo $this$getBidiRunDirection_u24lambda_u240 = this.paragraphInfoList.get(paragraphIndex);
        return $this$getBidiRunDirection_u24lambda_u240.getParagraph().getBidiRunDirection($this$getBidiRunDirection_u24lambda_u240.toLocalIndex(offset));
    }

    /* JADX INFO: renamed from: getWordBoundary--jx7JFs, reason: not valid java name */
    public final long m7436getWordBoundaryjx7JFs(int offset) {
        int paragraphIndex;
        requireIndexInRangeInclusiveEnd(offset);
        int length = getAnnotatedString().length();
        List<ParagraphInfo> list = this.paragraphInfoList;
        if (offset == length) {
            paragraphIndex = CollectionsKt.getLastIndex(list);
        } else {
            paragraphIndex = MultiParagraphKt.findParagraphByIndex(list, offset);
        }
        ParagraphInfo $this$getWordBoundary__jx7JFs_u24lambda_u240 = this.paragraphInfoList.get(paragraphIndex);
        return $this$getWordBoundary__jx7JFs_u24lambda_u240.m7448toGlobalxdX6G0($this$getWordBoundary__jx7JFs_u24lambda_u240.getParagraph().mo7392getWordBoundaryjx7JFs($this$getWordBoundary__jx7JFs_u24lambda_u240.toLocalIndex(offset)), false);
    }

    public final Rect getCursorRect(int offset) {
        int paragraphIndex;
        requireIndexInRangeInclusiveEnd(offset);
        int length = getAnnotatedString().length();
        List<ParagraphInfo> list = this.paragraphInfoList;
        if (offset == length) {
            paragraphIndex = CollectionsKt.getLastIndex(list);
        } else {
            paragraphIndex = MultiParagraphKt.findParagraphByIndex(list, offset);
        }
        ParagraphInfo $this$getCursorRect_u24lambda_u240 = this.paragraphInfoList.get(paragraphIndex);
        return $this$getCursorRect_u24lambda_u240.toGlobal($this$getCursorRect_u24lambda_u240.getParagraph().getCursorRect($this$getCursorRect_u24lambda_u240.toLocalIndex(offset)));
    }

    public final int getLineForOffset(int offset) {
        int paragraphIndex;
        if (offset >= getAnnotatedString().length()) {
            paragraphIndex = CollectionsKt.getLastIndex(this.paragraphInfoList);
        } else if (offset < 0) {
            paragraphIndex = 0;
        } else {
            paragraphIndex = MultiParagraphKt.findParagraphByIndex(this.paragraphInfoList, offset);
        }
        ParagraphInfo $this$getLineForOffset_u24lambda_u240 = this.paragraphInfoList.get(paragraphIndex);
        return $this$getLineForOffset_u24lambda_u240.toGlobalLineIndex($this$getLineForOffset_u24lambda_u240.getParagraph().getLineForOffset($this$getLineForOffset_u24lambda_u240.toLocalIndex(offset)));
    }

    public final float getLineLeft(int lineIndex) {
        requireLineIndexInRange(lineIndex);
        int paragraphIndex = MultiParagraphKt.findParagraphByLineIndex(this.paragraphInfoList, lineIndex);
        ParagraphInfo $this$getLineLeft_u24lambda_u240 = this.paragraphInfoList.get(paragraphIndex);
        return $this$getLineLeft_u24lambda_u240.getParagraph().getLineLeft($this$getLineLeft_u24lambda_u240.toLocalLineIndex(lineIndex));
    }

    public final float getLineRight(int lineIndex) {
        requireLineIndexInRange(lineIndex);
        int paragraphIndex = MultiParagraphKt.findParagraphByLineIndex(this.paragraphInfoList, lineIndex);
        ParagraphInfo $this$getLineRight_u24lambda_u240 = this.paragraphInfoList.get(paragraphIndex);
        return $this$getLineRight_u24lambda_u240.getParagraph().getLineRight($this$getLineRight_u24lambda_u240.toLocalLineIndex(lineIndex));
    }

    public final float getLineTop(int lineIndex) {
        requireLineIndexInRange(lineIndex);
        int paragraphIndex = MultiParagraphKt.findParagraphByLineIndex(this.paragraphInfoList, lineIndex);
        ParagraphInfo $this$getLineTop_u24lambda_u240 = this.paragraphInfoList.get(paragraphIndex);
        return $this$getLineTop_u24lambda_u240.toGlobalYPosition($this$getLineTop_u24lambda_u240.getParagraph().getLineTop($this$getLineTop_u24lambda_u240.toLocalLineIndex(lineIndex)));
    }

    public final float getLineBaseline(int lineIndex) {
        requireLineIndexInRange(lineIndex);
        int paragraphIndex = MultiParagraphKt.findParagraphByLineIndex(this.paragraphInfoList, lineIndex);
        ParagraphInfo $this$getLineBaseline_u24lambda_u240 = this.paragraphInfoList.get(paragraphIndex);
        return $this$getLineBaseline_u24lambda_u240.toGlobalYPosition($this$getLineBaseline_u24lambda_u240.getParagraph().getLineBaseline($this$getLineBaseline_u24lambda_u240.toLocalLineIndex(lineIndex)));
    }

    public final float getLineBottom(int lineIndex) {
        requireLineIndexInRange(lineIndex);
        int paragraphIndex = MultiParagraphKt.findParagraphByLineIndex(this.paragraphInfoList, lineIndex);
        ParagraphInfo $this$getLineBottom_u24lambda_u240 = this.paragraphInfoList.get(paragraphIndex);
        return $this$getLineBottom_u24lambda_u240.toGlobalYPosition($this$getLineBottom_u24lambda_u240.getParagraph().getLineBottom($this$getLineBottom_u24lambda_u240.toLocalLineIndex(lineIndex)));
    }

    public final float getLineHeight(int lineIndex) {
        requireLineIndexInRange(lineIndex);
        int paragraphIndex = MultiParagraphKt.findParagraphByLineIndex(this.paragraphInfoList, lineIndex);
        ParagraphInfo $this$getLineHeight_u24lambda_u240 = this.paragraphInfoList.get(paragraphIndex);
        return $this$getLineHeight_u24lambda_u240.getParagraph().getLineHeight($this$getLineHeight_u24lambda_u240.toLocalLineIndex(lineIndex));
    }

    public final float getLineWidth(int lineIndex) {
        requireLineIndexInRange(lineIndex);
        int paragraphIndex = MultiParagraphKt.findParagraphByLineIndex(this.paragraphInfoList, lineIndex);
        ParagraphInfo $this$getLineWidth_u24lambda_u240 = this.paragraphInfoList.get(paragraphIndex);
        return $this$getLineWidth_u24lambda_u240.getParagraph().getLineWidth($this$getLineWidth_u24lambda_u240.toLocalLineIndex(lineIndex));
    }

    public final int getLineStart(int lineIndex) {
        requireLineIndexInRange(lineIndex);
        int paragraphIndex = MultiParagraphKt.findParagraphByLineIndex(this.paragraphInfoList, lineIndex);
        ParagraphInfo $this$getLineStart_u24lambda_u240 = this.paragraphInfoList.get(paragraphIndex);
        return $this$getLineStart_u24lambda_u240.toGlobalIndex($this$getLineStart_u24lambda_u240.getParagraph().getLineStart($this$getLineStart_u24lambda_u240.toLocalLineIndex(lineIndex)));
    }

    public static /* synthetic */ int getLineEnd$default(MultiParagraph multiParagraph, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z = false;
        }
        return multiParagraph.getLineEnd(i, z);
    }

    public final int getLineEnd(int lineIndex, boolean visibleEnd) {
        requireLineIndexInRange(lineIndex);
        int paragraphIndex = MultiParagraphKt.findParagraphByLineIndex(this.paragraphInfoList, lineIndex);
        ParagraphInfo $this$getLineEnd_u24lambda_u240 = this.paragraphInfoList.get(paragraphIndex);
        return $this$getLineEnd_u24lambda_u240.toGlobalIndex($this$getLineEnd_u24lambda_u240.getParagraph().getLineEnd($this$getLineEnd_u24lambda_u240.toLocalLineIndex(lineIndex), visibleEnd));
    }

    public final boolean isLineEllipsized(int lineIndex) {
        requireLineIndexInRange(lineIndex);
        int paragraphIndex = MultiParagraphKt.findParagraphByLineIndex(this.paragraphInfoList, lineIndex);
        ParagraphInfo $this$isLineEllipsized_u24lambda_u240 = this.paragraphInfoList.get(paragraphIndex);
        return $this$isLineEllipsized_u24lambda_u240.getParagraph().isLineEllipsized(lineIndex);
    }

    private final void requireIndexInRange(int offset) {
        boolean value$iv = false;
        if (offset >= 0 && offset < getAnnotatedString().getText().length()) {
            value$iv = true;
        }
        if (value$iv) {
            return;
        }
        InlineClassHelperKt.throwIllegalArgumentException("offset(" + offset + ") is out of bounds [0, " + getAnnotatedString().length() + ')');
    }

    private final void requireIndexInRangeInclusiveEnd(int offset) {
        boolean value$iv = false;
        if (offset >= 0 && offset <= getAnnotatedString().getText().length()) {
            value$iv = true;
        }
        if (value$iv) {
            return;
        }
        InlineClassHelperKt.throwIllegalArgumentException("offset(" + offset + ") is out of bounds [0, " + getAnnotatedString().length() + ']');
    }

    private final void requireLineIndexInRange(int lineIndex) {
        boolean value$iv = false;
        if (lineIndex >= 0 && lineIndex < this.lineCount) {
            value$iv = true;
        }
        if (value$iv) {
            return;
        }
        InlineClassHelperKt.throwIllegalArgumentException("lineIndex(" + lineIndex + ") is out of bounds [0, " + this.lineCount + ')');
    }
}
