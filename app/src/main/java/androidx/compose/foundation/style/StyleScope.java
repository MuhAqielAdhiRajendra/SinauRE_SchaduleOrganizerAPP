package androidx.compose.foundation.style;

import androidx.autofill.HintConstants;
import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.runtime.CompositionLocalAccessorScope;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.shadow.Shadow;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextIndent;
import androidx.compose.ui.unit.Density;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: StyleScope.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000ò\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b+\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u001e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bw\u0018\u00002\u00020\u00012\u00020\u0002J\u0017\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH&¢\u0006\u0004\b\u000b\u0010\fJ\u0017\u0010\r\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH&¢\u0006\u0004\b\u000e\u0010\fJ\u0017\u0010\u000f\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH&¢\u0006\u0004\b\u0010\u0010\fJ\u0017\u0010\u0011\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH&¢\u0006\u0004\b\u0012\u0010\fJ\u0017\u0010\u0013\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH&¢\u0006\u0004\b\u0014\u0010\fJ\u0017\u0010\u0015\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH&¢\u0006\u0004\b\u0016\u0010\fJ\u0017\u0010\u0017\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH&¢\u0006\u0004\b\u0018\u0010\fJ/\u0010\u0017\u001a\u00020\b2\u0006\u0010\u0019\u001a\u00020\n2\u0006\u0010\u001a\u001a\u00020\n2\u0006\u0010\u001b\u001a\u00020\n2\u0006\u0010\u001c\u001a\u00020\nH&¢\u0006\u0004\b\u001d\u0010\u001eJ\u001f\u0010\u0017\u001a\u00020\b2\u0006\u0010\u001f\u001a\u00020\n2\u0006\u0010 \u001a\u00020\nH&¢\u0006\u0004\b!\u0010\"J\u0017\u0010#\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH&¢\u0006\u0004\b$\u0010\fJ\u0017\u0010%\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH&¢\u0006\u0004\b&\u0010\fJ\u0017\u0010'\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH&¢\u0006\u0004\b(\u0010\fJ\u0017\u0010)\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH&¢\u0006\u0004\b*\u0010\fJ\u0017\u0010+\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH&¢\u0006\u0004\b,\u0010\fJ\u0017\u0010-\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH&¢\u0006\u0004\b.\u0010\fJ\u0017\u0010/\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH&¢\u0006\u0004\b0\u0010\fJ/\u0010/\u001a\u00020\b2\u0006\u0010\u0019\u001a\u00020\n2\u0006\u0010\u001a\u001a\u00020\n2\u0006\u0010\u001b\u001a\u00020\n2\u0006\u0010\u001c\u001a\u00020\nH&¢\u0006\u0004\b1\u0010\u001eJ\u001f\u0010/\u001a\u00020\b2\u0006\u0010\u001f\u001a\u00020\n2\u0006\u0010 \u001a\u00020\nH&¢\u0006\u0004\b2\u0010\"J\u0017\u00103\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH&¢\u0006\u0004\b4\u0010\fJ\u0017\u00105\u001a\u00020\b2\u0006\u0010\t\u001a\u000206H&¢\u0006\u0004\b7\u00108J\u0010\u00109\u001a\u00020\b2\u0006\u0010\t\u001a\u00020:H&J\u001f\u0010;\u001a\u00020\b2\u0006\u0010<\u001a\u00020\n2\u0006\u0010=\u001a\u000206H&¢\u0006\u0004\b>\u0010?J\u001f\u0010;\u001a\u00020\b2\u0006\u0010<\u001a\u00020\n2\u0006\u0010@\u001a\u00020:H&¢\u0006\u0004\bA\u0010BJ\u0017\u0010<\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH&¢\u0006\u0004\bC\u0010\fJ\u0017\u0010D\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH&¢\u0006\u0004\bE\u0010\fJ\u001f\u0010F\u001a\u00020\b2\u0006\u0010<\u001a\u00020\n2\u0006\u0010D\u001a\u00020\nH&¢\u0006\u0004\bG\u0010\"J\u0017\u0010F\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH&¢\u0006\u0004\bH\u0010\fJ\u0017\u0010F\u001a\u00020\b2\u0006\u0010\t\u001a\u00020IH&¢\u0006\u0004\bJ\u00108J\u0012\u0010<\u001a\u00020\b2\b\b\u0001\u0010K\u001a\u00020LH&J\u0012\u0010D\u001a\u00020\b2\b\b\u0001\u0010K\u001a\u00020LH&J\u0017\u0010M\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH&¢\u0006\u0004\bN\u0010\fJ\u0017\u0010\u001a\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH&¢\u0006\u0004\bO\u0010\fJ\u0017\u0010P\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH&¢\u0006\u0004\bQ\u0010\fJ\u0017\u0010\u001c\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH&¢\u0006\u0004\bR\u0010\fJ\u0017\u0010S\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH&¢\u0006\u0004\bT\u0010\fJ\u0017\u0010U\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH&¢\u0006\u0004\bV\u0010\fJ\u0017\u0010W\u001a\u00020\b2\u0006\u0010F\u001a\u00020IH&¢\u0006\u0004\bX\u00108J\u001f\u0010W\u001a\u00020\b2\u0006\u0010<\u001a\u00020\n2\u0006\u0010D\u001a\u00020\nH&¢\u0006\u0004\bY\u0010\"J\u0017\u0010Z\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH&¢\u0006\u0004\b[\u0010\fJ\u0017\u0010\\\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH&¢\u0006\u0004\b]\u0010\fJ\u0017\u0010^\u001a\u00020\b2\u0006\u0010F\u001a\u00020IH&¢\u0006\u0004\b_\u00108J\u001f\u0010^\u001a\u00020\b2\u0006\u0010<\u001a\u00020\n2\u0006\u0010D\u001a\u00020\nH&¢\u0006\u0004\b`\u0010\"J\u0012\u0010a\u001a\u00020\b2\b\b\u0001\u0010\t\u001a\u00020LH&J\u0012\u0010b\u001a\u00020\b2\b\b\u0001\u0010\t\u001a\u00020LH&J\u0012\u0010c\u001a\u00020\b2\b\b\u0001\u0010\t\u001a\u00020LH&J\u0012\u0010d\u001a\u00020\b2\b\b\u0001\u0010\t\u001a\u00020LH&J\u0012\u0010e\u001a\u00020\b2\b\b\u0001\u0010\t\u001a\u00020LH&J\u0012\u0010f\u001a\u00020\b2\b\b\u0001\u0010\t\u001a\u00020LH&J\u001c\u0010g\u001a\u00020\b2\b\b\u0001\u0010h\u001a\u00020L2\b\b\u0001\u0010i\u001a\u00020LH&J\u0017\u0010g\u001a\u00020\b2\u0006\u0010j\u001a\u00020kH&¢\u0006\u0004\bl\u00108J\u0010\u0010m\u001a\u00020\b2\u0006\u0010\t\u001a\u00020LH&J\u0010\u0010n\u001a\u00020\b2\u0006\u0010\t\u001a\u00020LH&J\u0010\u0010o\u001a\u00020\b2\u0006\u0010\t\u001a\u00020LH&J\u0017\u0010p\u001a\u00020\b2\u0006\u0010\t\u001a\u00020qH&¢\u0006\u0004\br\u00108J\u0012\u0010s\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020tH&J\u0012\u0010u\u001a\u00020\b2\b\b\u0001\u0010\t\u001a\u00020LH&J\u0017\u0010v\u001a\u00020\b2\u0006\u0010=\u001a\u000206H&¢\u0006\u0004\bw\u00108J\u0010\u0010v\u001a\u00020\b2\u0006\u0010\t\u001a\u00020:H&J\u0017\u0010x\u001a\u00020\b2\u0006\u0010\t\u001a\u000206H&¢\u0006\u0004\by\u00108J\u0010\u0010x\u001a\u00020\b2\u0006\u0010\t\u001a\u00020:H&J\u0010\u0010z\u001a\u00020\b2\u0006\u0010\t\u001a\u00020{H&J\u0010\u0010|\u001a\u00020\b2\u0006\u0010\t\u001a\u00020}H&J!\u0010|\u001a\u00020\b2\u0012\u0010\t\u001a\n\u0012\u0006\b\u0001\u0012\u00020}0~\"\u00020}H&¢\u0006\u0002\u0010\u007fJ\u0011\u0010\u0080\u0001\u001a\u00020\b2\u0006\u0010\t\u001a\u00020}H&J\"\u0010\u0080\u0001\u001a\u00020\b2\u0012\u0010\t\u001a\n\u0012\u0006\b\u0001\u0012\u00020}0~\"\u00020}H&¢\u0006\u0002\u0010\u007fJ\u0012\u0010\u0081\u0001\u001a\u00020\b2\u0007\u0010\t\u001a\u00030\u0082\u0001H&J\"\u0010\u0081\u0001\u001a\u00020\b2\u000e\u0010\u0083\u0001\u001a\t\u0012\u0004\u0012\u00020L0\u0084\u00012\u0007\u0010\t\u001a\u00030\u0082\u0001H&J2\u0010\u0081\u0001\u001a\u00020\b2\u000e\u0010\u0085\u0001\u001a\t\u0012\u0004\u0012\u00020L0\u0084\u00012\u000e\u0010\u0086\u0001\u001a\t\u0012\u0004\u0012\u00020L0\u0084\u00012\u0007\u0010\t\u001a\u00030\u0082\u0001H&J\u0012\u0010\u0087\u0001\u001a\u00020\b2\u0007\u0010\t\u001a\u00030\u0088\u0001H&J\u0019\u0010\u0089\u0001\u001a\u00020\b2\u0006\u0010\t\u001a\u000206H&¢\u0006\u0005\b\u008a\u0001\u00108J\u0011\u0010\u008b\u0001\u001a\u00020\b2\u0006\u0010\t\u001a\u00020:H&J\u0012\u0010\u008c\u0001\u001a\u00020\b2\u0007\u0010\t\u001a\u00030\u008d\u0001H&J\u0012\u0010\u008e\u0001\u001a\u00020\b2\u0007\u0010\t\u001a\u00030\u008f\u0001H&J\u0012\u0010\u0090\u0001\u001a\u00020\b2\u0007\u0010\t\u001a\u00030\u0091\u0001H&J\u001a\u0010\u0092\u0001\u001a\u00020\b2\u0007\u0010\t\u001a\u00030\u0093\u0001H&¢\u0006\u0005\b\u0094\u0001\u00108J\u001a\u0010\u0095\u0001\u001a\u00020\b2\u0007\u0010\t\u001a\u00030\u0093\u0001H&¢\u0006\u0005\b\u0096\u0001\u00108J\u001a\u0010\u0097\u0001\u001a\u00020\b2\u0007\u0010\t\u001a\u00030\u0093\u0001H&¢\u0006\u0005\b\u0098\u0001\u00108J\u001a\u0010\u0099\u0001\u001a\u00020\b2\u0007\u0010\t\u001a\u00030\u009a\u0001H&¢\u0006\u0005\b\u009b\u0001\u0010\fJ\u0012\u0010\u009c\u0001\u001a\u00020\b2\u0007\u0010\t\u001a\u00030\u009d\u0001H&J\u001b\u0010\u009e\u0001\u001a\u00020\b2\u0007\u0010\t\u001a\u00030\u009f\u0001H&¢\u0006\u0006\b \u0001\u0010¡\u0001J\u001b\u0010¢\u0001\u001a\u00020\b2\u0007\u0010\t\u001a\u00030£\u0001H&¢\u0006\u0006\b¤\u0001\u0010¡\u0001J\u001b\u0010¥\u0001\u001a\u00020\b2\u0007\u0010\t\u001a\u00030¦\u0001H&¢\u0006\u0006\b§\u0001\u0010¡\u0001J\u001b\u0010¨\u0001\u001a\u00020\b2\u0007\u0010\t\u001a\u00030©\u0001H&¢\u0006\u0006\bª\u0001\u0010¡\u0001J\u001b\u0010«\u0001\u001a\u00020\b2\u0007\u0010\t\u001a\u00030¬\u0001H&¢\u0006\u0006\b\u00ad\u0001\u0010¡\u0001J\u001b\u0010®\u0001\u001a\u00020\b2\u0007\u0010\t\u001a\u00030¯\u0001H&¢\u0006\u0006\b°\u0001\u0010¡\u0001Jp\u0010\u0003\u001a\u00020\b\"\u0005\b\u0000\u0010±\u00012\u000f\u0010²\u0001\u001a\n\u0012\u0005\u0012\u0003H±\u00010³\u00012\u0007\u0010\t\u001a\u00030\u0082\u00012E\u0010´\u0001\u001a@\u0012\u001e\u0012\u001c\u0012\u0005\u0012\u0003H±\u00010³\u0001¢\u0006\u000f\b¶\u0001\u0012\n\b·\u0001\u0012\u0005\b\b(²\u0001\u0012\u0015\u0012\u00130\u0004¢\u0006\u000e\b¶\u0001\u0012\t\b·\u0001\u0012\u0004\b\b(\u0003\u0012\u0004\u0012\u00020t0µ\u0001H&R\u0012\u0010\u0003\u001a\u00020\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006\u0082\u0001\u0002¸\u0001ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006¹\u0001À\u0006\u0001"}, d2 = {"Landroidx/compose/foundation/style/StyleScope;", "Landroidx/compose/runtime/CompositionLocalAccessorScope;", "Landroidx/compose/ui/unit/Density;", "state", "Landroidx/compose/foundation/style/StyleState;", "getState", "()Landroidx/compose/foundation/style/StyleState;", "contentPaddingStart", "", "value", "Landroidx/compose/ui/unit/Dp;", "contentPaddingStart-0680j_4", "(F)V", "contentPaddingEnd", "contentPaddingEnd-0680j_4", "contentPaddingTop", "contentPaddingTop-0680j_4", "contentPaddingBottom", "contentPaddingBottom-0680j_4", "contentPaddingHorizontal", "contentPaddingHorizontal-0680j_4", "contentPaddingVertical", "contentPaddingVertical-0680j_4", "contentPadding", "contentPadding-0680j_4", "start", "top", "end", "bottom", "contentPadding-a9UjIt4", "(FFFF)V", "horizontal", "vertical", "contentPadding-YgX7TsA", "(FF)V", "externalPaddingStart", "externalPaddingStart-0680j_4", "externalPaddingEnd", "externalPaddingEnd-0680j_4", "externalPaddingTop", "externalPaddingTop-0680j_4", "externalPaddingBottom", "externalPaddingBottom-0680j_4", "externalPaddingHorizontal", "externalPaddingHorizontal-0680j_4", "externalPaddingVertical", "externalPaddingVertical-0680j_4", "externalPadding", "externalPadding-0680j_4", "externalPadding-a9UjIt4", "externalPadding-YgX7TsA", "borderWidth", "borderWidth-0680j_4", "borderColor", "Landroidx/compose/ui/graphics/Color;", "borderColor-8_81llA", "(J)V", "borderBrush", "Landroidx/compose/ui/graphics/Brush;", "border", "width", TypedValues.Custom.S_COLOR, "border-cXLIe8U", "(FJ)V", "brush", "border-D5KLDUw", "(FLandroidx/compose/ui/graphics/Brush;)V", "width-0680j_4", "height", "height-0680j_4", "size", "size-YgX7TsA", "size-0680j_4", "Landroidx/compose/ui/unit/DpSize;", "size-EaSLcWc", "fraction", "", "left", "left-0680j_4", "top-0680j_4", "right", "right-0680j_4", "bottom-0680j_4", "minWidth", "minWidth-0680j_4", "minHeight", "minHeight-0680j_4", "minSize", "minSize-EaSLcWc", "minSize-YgX7TsA", "maxWidth", "maxWidth-0680j_4", "maxHeight", "maxHeight-0680j_4", "maxSize", "maxSize-EaSLcWc", "maxSize-YgX7TsA", "alpha", "scaleX", "scaleY", "scale", "translationX", "translationY", "translation", "x", "y", TypedValues.CycleType.S_WAVE_OFFSET, "Landroidx/compose/ui/geometry/Offset;", "translation-k-4lQ0M", "rotationX", "rotationY", "rotationZ", "transformOrigin", "Landroidx/compose/ui/graphics/TransformOrigin;", "transformOrigin-__ExYCQ", "clip", "", "zIndex", "background", "background-8_81llA", "foreground", "foreground-8_81llA", "shape", "Landroidx/compose/ui/graphics/Shape;", "dropShadow", "Landroidx/compose/ui/graphics/shadow/Shadow;", "", "([Landroidx/compose/ui/graphics/shadow/Shadow;)V", "innerShadow", "animate", "Landroidx/compose/foundation/style/Style;", "spec", "Landroidx/compose/animation/core/AnimationSpec;", "toSpec", "fromSpec", "textStyle", "Landroidx/compose/ui/text/TextStyle;", "contentColor", "contentColor-8_81llA", "contentBrush", "textDecoration", "Landroidx/compose/ui/text/style/TextDecoration;", "fontFamily", "Landroidx/compose/ui/text/font/FontFamily;", "textIndent", "Landroidx/compose/ui/text/style/TextIndent;", "fontSize", "Landroidx/compose/ui/unit/TextUnit;", "fontSize--R2X_6o", "lineHeight", "lineHeight--R2X_6o", "letterSpacing", "letterSpacing--R2X_6o", "baselineShift", "Landroidx/compose/ui/text/style/BaselineShift;", "baselineShift-4Dl_Bck", "fontWeight", "Landroidx/compose/ui/text/font/FontWeight;", "fontStyle", "Landroidx/compose/ui/text/font/FontStyle;", "fontStyle-nzbMABs", "(I)V", "textAlign", "Landroidx/compose/ui/text/style/TextAlign;", "textAlign-aXe7zB0", "textDirection", "Landroidx/compose/ui/text/style/TextDirection;", "textDirection-Hejc4pk", "lineBreak", "Landroidx/compose/ui/text/style/LineBreak;", "lineBreak-CZqVlQI", "hyphens", "Landroidx/compose/ui/text/style/Hyphens;", "hyphens--3fSNIE", "fontSynthesis", "Landroidx/compose/ui/text/font/FontSynthesis;", "fontSynthesis-6p3vJLY", "T", "key", "Landroidx/compose/foundation/style/StyleStateKey;", "active", "Lkotlin/Function2;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "Landroidx/compose/foundation/style/ResolvedStyle;", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
public interface StyleScope extends CompositionLocalAccessorScope, Density {
    void alpha(float value);

    void animate(AnimationSpec<Float> toSpec, AnimationSpec<Float> fromSpec, Style value);

    void animate(AnimationSpec<Float> spec, Style value);

    void animate(Style value);

    void background(Brush value);

    /* JADX INFO: renamed from: background-8_81llA */
    void mo1381background8_81llA(long color);

    /* JADX INFO: renamed from: baselineShift-4Dl_Bck */
    void mo1382baselineShift4Dl_Bck(float value);

    /* JADX INFO: renamed from: border-D5KLDUw */
    void mo1383borderD5KLDUw(float width, Brush brush);

    /* JADX INFO: renamed from: border-cXLIe8U */
    void mo1384bordercXLIe8U(float width, long color);

    void borderBrush(Brush value);

    /* JADX INFO: renamed from: borderColor-8_81llA */
    void mo1385borderColor8_81llA(long value);

    /* JADX INFO: renamed from: borderWidth-0680j_4 */
    void mo1386borderWidth0680j_4(float value);

    /* JADX INFO: renamed from: bottom-0680j_4 */
    void mo1387bottom0680j_4(float value);

    void clip(boolean value);

    void contentBrush(Brush value);

    /* JADX INFO: renamed from: contentColor-8_81llA */
    void mo1388contentColor8_81llA(long value);

    /* JADX INFO: renamed from: contentPadding-0680j_4 */
    void mo1389contentPadding0680j_4(float value);

    /* JADX INFO: renamed from: contentPadding-YgX7TsA */
    void mo1390contentPaddingYgX7TsA(float horizontal, float vertical);

    /* JADX INFO: renamed from: contentPadding-a9UjIt4 */
    void mo1391contentPaddinga9UjIt4(float start, float top, float end, float bottom);

    /* JADX INFO: renamed from: contentPaddingBottom-0680j_4 */
    void mo1392contentPaddingBottom0680j_4(float value);

    /* JADX INFO: renamed from: contentPaddingEnd-0680j_4 */
    void mo1393contentPaddingEnd0680j_4(float value);

    /* JADX INFO: renamed from: contentPaddingHorizontal-0680j_4 */
    void mo1394contentPaddingHorizontal0680j_4(float value);

    /* JADX INFO: renamed from: contentPaddingStart-0680j_4 */
    void mo1395contentPaddingStart0680j_4(float value);

    /* JADX INFO: renamed from: contentPaddingTop-0680j_4 */
    void mo1396contentPaddingTop0680j_4(float value);

    /* JADX INFO: renamed from: contentPaddingVertical-0680j_4 */
    void mo1397contentPaddingVertical0680j_4(float value);

    void dropShadow(Shadow value);

    void dropShadow(Shadow... value);

    /* JADX INFO: renamed from: externalPadding-0680j_4 */
    void mo1398externalPadding0680j_4(float value);

    /* JADX INFO: renamed from: externalPadding-YgX7TsA */
    void mo1399externalPaddingYgX7TsA(float horizontal, float vertical);

    /* JADX INFO: renamed from: externalPadding-a9UjIt4 */
    void mo1400externalPaddinga9UjIt4(float start, float top, float end, float bottom);

    /* JADX INFO: renamed from: externalPaddingBottom-0680j_4 */
    void mo1401externalPaddingBottom0680j_4(float value);

    /* JADX INFO: renamed from: externalPaddingEnd-0680j_4 */
    void mo1402externalPaddingEnd0680j_4(float value);

    /* JADX INFO: renamed from: externalPaddingHorizontal-0680j_4 */
    void mo1403externalPaddingHorizontal0680j_4(float value);

    /* JADX INFO: renamed from: externalPaddingStart-0680j_4 */
    void mo1404externalPaddingStart0680j_4(float value);

    /* JADX INFO: renamed from: externalPaddingTop-0680j_4 */
    void mo1405externalPaddingTop0680j_4(float value);

    /* JADX INFO: renamed from: externalPaddingVertical-0680j_4 */
    void mo1406externalPaddingVertical0680j_4(float value);

    void fontFamily(FontFamily value);

    /* JADX INFO: renamed from: fontSize--R2X_6o */
    void mo1407fontSizeR2X_6o(long value);

    /* JADX INFO: renamed from: fontStyle-nzbMABs */
    void mo1408fontStylenzbMABs(int value);

    /* JADX INFO: renamed from: fontSynthesis-6p3vJLY */
    void mo1409fontSynthesis6p3vJLY(int value);

    void fontWeight(FontWeight value);

    void foreground(Brush value);

    /* JADX INFO: renamed from: foreground-8_81llA */
    void mo1410foreground8_81llA(long value);

    StyleState getState();

    void height(float fraction);

    /* JADX INFO: renamed from: height-0680j_4 */
    void mo1426height0680j_4(float value);

    /* JADX INFO: renamed from: hyphens--3fSNIE */
    void mo1427hyphens3fSNIE(int value);

    void innerShadow(Shadow value);

    void innerShadow(Shadow... value);

    /* JADX INFO: renamed from: left-0680j_4 */
    void mo1428left0680j_4(float value);

    /* JADX INFO: renamed from: letterSpacing--R2X_6o */
    void mo1429letterSpacingR2X_6o(long value);

    /* JADX INFO: renamed from: lineBreak-CZqVlQI */
    void mo1430lineBreakCZqVlQI(int value);

    /* JADX INFO: renamed from: lineHeight--R2X_6o */
    void mo1431lineHeightR2X_6o(long value);

    /* JADX INFO: renamed from: maxHeight-0680j_4 */
    void mo1432maxHeight0680j_4(float value);

    /* JADX INFO: renamed from: maxSize-EaSLcWc */
    void mo1433maxSizeEaSLcWc(long size);

    /* JADX INFO: renamed from: maxSize-YgX7TsA */
    void mo1434maxSizeYgX7TsA(float width, float height);

    /* JADX INFO: renamed from: maxWidth-0680j_4 */
    void mo1435maxWidth0680j_4(float value);

    /* JADX INFO: renamed from: minHeight-0680j_4 */
    void mo1436minHeight0680j_4(float value);

    /* JADX INFO: renamed from: minSize-EaSLcWc */
    void mo1437minSizeEaSLcWc(long size);

    /* JADX INFO: renamed from: minSize-YgX7TsA */
    void mo1438minSizeYgX7TsA(float width, float height);

    /* JADX INFO: renamed from: minWidth-0680j_4 */
    void mo1439minWidth0680j_4(float value);

    /* JADX INFO: renamed from: right-0680j_4 */
    void mo1440right0680j_4(float value);

    void rotationX(float value);

    void rotationY(float value);

    void rotationZ(float value);

    void scale(float value);

    void scaleX(float value);

    void scaleY(float value);

    void shape(Shape value);

    /* JADX INFO: renamed from: size-0680j_4 */
    void mo1451size0680j_4(float value);

    /* JADX INFO: renamed from: size-EaSLcWc */
    void mo1452sizeEaSLcWc(long value);

    /* JADX INFO: renamed from: size-YgX7TsA */
    void mo1453sizeYgX7TsA(float width, float height);

    <T> void state(StyleStateKey<T> key, Style value, Function2<? super StyleStateKey<T>, ? super StyleState, Boolean> active);

    /* JADX INFO: renamed from: textAlign-aXe7zB0 */
    void mo1454textAlignaXe7zB0(int value);

    void textDecoration(TextDecoration value);

    /* JADX INFO: renamed from: textDirection-Hejc4pk */
    void mo1455textDirectionHejc4pk(int value);

    void textIndent(TextIndent value);

    void textStyle(TextStyle value);

    /* JADX INFO: renamed from: top-0680j_4 */
    void mo1456top0680j_4(float value);

    /* JADX INFO: renamed from: transformOrigin-__ExYCQ */
    void mo1457transformOrigin__ExYCQ(long value);

    void translation(float x, float y);

    /* JADX INFO: renamed from: translation-k-4lQ0M */
    void mo1458translationk4lQ0M(long offset);

    void translationX(float value);

    void translationY(float value);

    void width(float fraction);

    /* JADX INFO: renamed from: width-0680j_4 */
    void mo1459width0680j_4(float value);

    void zIndex(float value);

    static /* synthetic */ void clip$default(StyleScope styleScope, boolean z, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: clip");
        }
        if ((i & 1) != 0) {
            z = true;
        }
        styleScope.clip(z);
    }
}
