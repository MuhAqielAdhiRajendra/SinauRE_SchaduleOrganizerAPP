package androidx.compose.foundation.style;

import androidx.autofill.HintConstants;
import androidx.collection.MutableIntList;
import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.runtime.CompositionLocal;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.RectangleShapeKt;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.TransformOrigin;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNodeKt;
import androidx.compose.ui.platform.InspectableValue;
import androidx.compose.ui.platform.ValueElement;
import androidx.compose.ui.text.ParagraphStyle;
import androidx.compose.ui.text.PlatformTextStyle;
import androidx.compose.ui.text.SpanStyle;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontSynthesis;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.intl.LocaleList;
import androidx.compose.ui.text.style.BaselineShift;
import androidx.compose.ui.text.style.Hyphens;
import androidx.compose.ui.text.style.LineBreak;
import androidx.compose.ui.text.style.LineHeightStyle;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextDirection;
import androidx.compose.ui.text.style.TextGeometricTransform;
import androidx.compose.ui.text.style.TextIndent;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.DpSize;
import androidx.compose.ui.unit.TextUnit;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.android.material.internal.ViewUtils;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;

/* JADX INFO: compiled from: ResolvedStyle.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000Ä\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\bB\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0002\b\u001d\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b,\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0001\u0018\u00002\u00020\u00012\u00020\u0002B\t\b\u0000¢\u0006\u0004\b\u0003\u0010\u0004J#\u0010Ä\u0001\u001a\u00020\u00062\u0007\u0010Å\u0001\u001a\u00020\u00002\t\b\u0002\u0010Æ\u0001\u001a\u00020\u0006H\u0000¢\u0006\u0003\bÇ\u0001J\u000f\u0010È\u0001\u001a\u00020\u0000H\u0000¢\u0006\u0003\bÉ\u0001J\u000f\u0010Ê\u0001\u001a\u00020\u0000H\u0000¢\u0006\u0003\bË\u0001J\u0019\u0010Ì\u0001\u001a\u00030Í\u00012\u0007\u0010Î\u0001\u001a\u00020\u0000H\u0000¢\u0006\u0003\bÏ\u0001J\u0019\u0010Ð\u0001\u001a\u00030Í\u00012\u0007\u0010Î\u0001\u001a\u00020\u0000H\u0000¢\u0006\u0003\bÑ\u0001J\u0010\u0010Ò\u0001\u001a\u00030Í\u0001H\u0000¢\u0006\u0003\bÓ\u0001J4\u0010Ô\u0001\u001a\u00030Í\u00012\b\u0010Õ\u0001\u001a\u00030Ö\u00012\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010×\u0001\u001a\u00030Ø\u00012\u0006\u0010\u000e\u001a\u00020\u000fH\u0000¢\u0006\u0003\bÙ\u0001J:\u0010Ú\u0001\u001a\u00030Í\u00012\b\u0010Õ\u0001\u001a\u00030Ö\u00012\b\u0010×\u0001\u001a\u00030Ø\u00012\u0006\u0010\u000e\u001a\u00020\u000f2\f\b\u0002\u0010Û\u0001\u001a\u0005\u0018\u00010Ü\u0001H\u0000¢\u0006\u0003\bÝ\u0001J\u0019\u0010Þ\u0001\u001a\u00030Í\u00012\u0007\u0010ß\u0001\u001a\u00020\u0000H\u0000¢\u0006\u0003\bà\u0001J\u001a\u0010á\u0001\u001a\u00030â\u00012\b\u0010ã\u0001\u001a\u00030â\u0001H\u0000¢\u0006\u0003\bä\u0001J\u0011\u0010å\u0001\u001a\n\u0012\u0005\u0012\u00030ç\u00010æ\u0001H\u0002J\u001b\u0010\u0012\u001a\u00030Í\u00012\b\u0010ñ\u0001\u001a\u00030ò\u0001H\u0016¢\u0006\u0005\bó\u0001\u0010\u0016J\u001b\u0010\u0017\u001a\u00030Í\u00012\b\u0010ñ\u0001\u001a\u00030ò\u0001H\u0016¢\u0006\u0005\bô\u0001\u0010\u0016J\u001b\u0010\u001a\u001a\u00030Í\u00012\b\u0010ñ\u0001\u001a\u00030ò\u0001H\u0016¢\u0006\u0005\bõ\u0001\u0010\u0016J\u001b\u0010\u001d\u001a\u00030Í\u00012\b\u0010ñ\u0001\u001a\u00030ò\u0001H\u0016¢\u0006\u0005\bö\u0001\u0010\u0016J\u001c\u0010÷\u0001\u001a\u00030Í\u00012\b\u0010ñ\u0001\u001a\u00030ò\u0001H\u0016¢\u0006\u0005\bø\u0001\u0010\u0016J\u001c\u0010ù\u0001\u001a\u00030Í\u00012\b\u0010ñ\u0001\u001a\u00030ò\u0001H\u0016¢\u0006\u0005\bú\u0001\u0010\u0016J\u001c\u0010û\u0001\u001a\u00030Í\u00012\b\u0010ñ\u0001\u001a\u00030ò\u0001H\u0016¢\u0006\u0005\bü\u0001\u0010\u0016J9\u0010û\u0001\u001a\u00030Í\u00012\b\u0010ý\u0001\u001a\u00030ò\u00012\u0007\u0010>\u001a\u00030ò\u00012\b\u0010þ\u0001\u001a\u00030ò\u00012\u0007\u0010D\u001a\u00030ò\u0001H\u0016¢\u0006\u0006\bÿ\u0001\u0010\u0080\u0002J'\u0010û\u0001\u001a\u00030Í\u00012\b\u0010\u0081\u0002\u001a\u00030ò\u00012\b\u0010\u0082\u0002\u001a\u00030ò\u0001H\u0016¢\u0006\u0006\b\u0083\u0002\u0010\u0084\u0002J\u001b\u0010 \u001a\u00030Í\u00012\b\u0010ñ\u0001\u001a\u00030ò\u0001H\u0016¢\u0006\u0005\b\u0085\u0002\u0010\u0016J\u001b\u0010#\u001a\u00030Í\u00012\b\u0010ñ\u0001\u001a\u00030ò\u0001H\u0016¢\u0006\u0005\b\u0086\u0002\u0010\u0016J\u001b\u0010&\u001a\u00030Í\u00012\b\u0010ñ\u0001\u001a\u00030ò\u0001H\u0016¢\u0006\u0005\b\u0087\u0002\u0010\u0016J\u001b\u0010)\u001a\u00030Í\u00012\b\u0010ñ\u0001\u001a\u00030ò\u0001H\u0016¢\u0006\u0005\b\u0088\u0002\u0010\u0016J\u001c\u0010\u0089\u0002\u001a\u00030Í\u00012\b\u0010ñ\u0001\u001a\u00030ò\u0001H\u0016¢\u0006\u0005\b\u008a\u0002\u0010\u0016J\u001c\u0010\u008b\u0002\u001a\u00030Í\u00012\b\u0010ñ\u0001\u001a\u00030ò\u0001H\u0016¢\u0006\u0005\b\u008c\u0002\u0010\u0016J\u001c\u0010\u008d\u0002\u001a\u00030Í\u00012\b\u0010ñ\u0001\u001a\u00030ò\u0001H\u0016¢\u0006\u0005\b\u008e\u0002\u0010\u0016J9\u0010\u008d\u0002\u001a\u00030Í\u00012\b\u0010ý\u0001\u001a\u00030ò\u00012\u0007\u0010>\u001a\u00030ò\u00012\b\u0010þ\u0001\u001a\u00030ò\u00012\u0007\u0010D\u001a\u00030ò\u0001H\u0016¢\u0006\u0006\b\u008f\u0002\u0010\u0080\u0002J'\u0010\u008d\u0002\u001a\u00030Í\u00012\b\u0010\u0081\u0002\u001a\u00030ò\u00012\b\u0010\u0082\u0002\u001a\u00030ò\u0001H\u0016¢\u0006\u0006\b\u0090\u0002\u0010\u0084\u0002J\u001b\u0010,\u001a\u00030Í\u00012\b\u0010ñ\u0001\u001a\u00030ò\u0001H\u0016¢\u0006\u0005\b\u0091\u0002\u0010\u0016J\u001a\u0010S\u001a\u00030Í\u00012\u0007\u0010ñ\u0001\u001a\u00020TH\u0016¢\u0006\u0005\b\u0092\u0002\u0010XJ\u0012\u0010Z\u001a\u00030Í\u00012\u0007\u0010ñ\u0001\u001a\u00020[H\u0016J%\u0010\u0093\u0002\u001a\u00030Í\u00012\u0007\u0010/\u001a\u00030ò\u00012\u0007\u0010\u0094\u0002\u001a\u00020TH\u0016¢\u0006\u0006\b\u0095\u0002\u0010\u0096\u0002J%\u0010\u0093\u0002\u001a\u00030Í\u00012\u0007\u0010/\u001a\u00030ò\u00012\u0007\u0010\u0097\u0002\u001a\u00020[H\u0016¢\u0006\u0006\b\u0098\u0002\u0010\u0099\u0002J\u001b\u0010/\u001a\u00030Í\u00012\b\u0010ñ\u0001\u001a\u00030ò\u0001H\u0016¢\u0006\u0005\b\u009a\u0002\u0010\u0016J\u001b\u00102\u001a\u00030Í\u00012\b\u0010ñ\u0001\u001a\u00030ò\u0001H\u0016¢\u0006\u0005\b\u009b\u0002\u0010\u0016J%\u0010\u009c\u0002\u001a\u00030Í\u00012\u0007\u0010/\u001a\u00030ò\u00012\u0007\u00102\u001a\u00030ò\u0001H\u0016¢\u0006\u0006\b\u009d\u0002\u0010\u0084\u0002J\u001c\u0010\u009c\u0002\u001a\u00030Í\u00012\b\u0010ñ\u0001\u001a\u00030ò\u0001H\u0016¢\u0006\u0005\b\u009e\u0002\u0010\u0016J\u001c\u0010\u009c\u0002\u001a\u00030Í\u00012\b\u0010ñ\u0001\u001a\u00030\u009f\u0002H\u0016¢\u0006\u0005\b \u0002\u0010XJ\u0012\u0010/\u001a\u00030Í\u00012\u0007\u0010¡\u0002\u001a\u00020\fH\u0016J\u0012\u00102\u001a\u00030Í\u00012\u0007\u0010¡\u0002\u001a\u00020\fH\u0016J\u001b\u0010;\u001a\u00030Í\u00012\b\u0010ñ\u0001\u001a\u00030ò\u0001H\u0016¢\u0006\u0005\b¢\u0002\u0010\u0016J\u001b\u0010>\u001a\u00030Í\u00012\b\u0010ñ\u0001\u001a\u00030ò\u0001H\u0016¢\u0006\u0005\b£\u0002\u0010\u0016J\u001b\u0010A\u001a\u00030Í\u00012\b\u0010ñ\u0001\u001a\u00030ò\u0001H\u0016¢\u0006\u0005\b¤\u0002\u0010\u0016J\u001b\u0010D\u001a\u00030Í\u00012\b\u0010ñ\u0001\u001a\u00030ò\u0001H\u0016¢\u0006\u0005\b¥\u0002\u0010\u0016J\u001b\u0010M\u001a\u00030Í\u00012\b\u0010ñ\u0001\u001a\u00030ò\u0001H\u0016¢\u0006\u0005\b¦\u0002\u0010\u0016J\u001b\u0010G\u001a\u00030Í\u00012\b\u0010ñ\u0001\u001a\u00030ò\u0001H\u0016¢\u0006\u0005\b§\u0002\u0010\u0016J\u001c\u0010¨\u0002\u001a\u00030Í\u00012\b\u0010\u009c\u0002\u001a\u00030\u009f\u0002H\u0016¢\u0006\u0005\b©\u0002\u0010XJ%\u0010¨\u0002\u001a\u00030Í\u00012\u0007\u0010/\u001a\u00030ò\u00012\u0007\u00102\u001a\u00030ò\u0001H\u0016¢\u0006\u0006\bª\u0002\u0010\u0084\u0002J\u001b\u0010P\u001a\u00030Í\u00012\b\u0010ñ\u0001\u001a\u00030ò\u0001H\u0016¢\u0006\u0005\b«\u0002\u0010\u0016J\u001b\u0010J\u001a\u00030Í\u00012\b\u0010ñ\u0001\u001a\u00030ò\u0001H\u0016¢\u0006\u0005\b¬\u0002\u0010\u0016J\u001c\u0010\u00ad\u0002\u001a\u00030Í\u00012\b\u0010\u009c\u0002\u001a\u00030\u009f\u0002H\u0016¢\u0006\u0005\b®\u0002\u0010XJ%\u0010\u00ad\u0002\u001a\u00030Í\u00012\u0007\u0010/\u001a\u00030ò\u00012\u0007\u00102\u001a\u00030ò\u0001H\u0016¢\u0006\u0006\b¯\u0002\u0010\u0084\u0002J\u0012\u0010w\u001a\u00030Í\u00012\u0007\u0010ñ\u0001\u001a\u00020\fH\u0016J\u0012\u0010z\u001a\u00030Í\u00012\u0007\u0010ñ\u0001\u001a\u00020\fH\u0016J\u0012\u0010}\u001a\u00030Í\u00012\u0007\u0010ñ\u0001\u001a\u00020\fH\u0016J\u0013\u0010°\u0002\u001a\u00030Í\u00012\u0007\u0010ñ\u0001\u001a\u00020\fH\u0016J\u0013\u0010\u0080\u0001\u001a\u00030Í\u00012\u0007\u0010ñ\u0001\u001a\u00020\fH\u0016J\u0013\u0010\u0083\u0001\u001a\u00030Í\u00012\u0007\u0010ñ\u0001\u001a\u00020\fH\u0016J\u001c\u0010±\u0002\u001a\u00030Í\u00012\u0007\u0010²\u0002\u001a\u00020\f2\u0007\u0010³\u0002\u001a\u00020\fH\u0016J\u001c\u0010±\u0002\u001a\u00030Í\u00012\b\u0010´\u0002\u001a\u00030µ\u0002H\u0016¢\u0006\u0005\b¶\u0002\u0010XJ\u0013\u0010\u0086\u0001\u001a\u00030Í\u00012\u0007\u0010ñ\u0001\u001a\u00020\fH\u0016J\u0013\u0010\u0089\u0001\u001a\u00030Í\u00012\u0007\u0010ñ\u0001\u001a\u00020\fH\u0016J\u0013\u0010\u008c\u0001\u001a\u00030Í\u00012\u0007\u0010ñ\u0001\u001a\u00020\fH\u0016J\u001c\u0010\u008f\u0001\u001a\u00030Í\u00012\b\u0010ñ\u0001\u001a\u00030\u0090\u0001H\u0016¢\u0006\u0005\b·\u0002\u0010XJ\u0012\u0010l\u001a\u00030Í\u00012\u0007\u0010ñ\u0001\u001a\u00020\u000fH\u0016J\u0013\u0010\u0096\u0001\u001a\u00030Í\u00012\u0007\u0010ñ\u0001\u001a\u00020\fH\u0016J\u001b\u0010¸\u0002\u001a\u00030Í\u00012\u0007\u0010\u0094\u0002\u001a\u00020TH\u0016¢\u0006\u0005\b¹\u0002\u0010XJ\u0013\u0010¸\u0002\u001a\u00030Í\u00012\u0007\u0010ñ\u0001\u001a\u00020[H\u0016J\u001b\u0010º\u0002\u001a\u00030Í\u00012\u0007\u0010ñ\u0001\u001a\u00020TH\u0016¢\u0006\u0005\b»\u0002\u0010XJ\u0013\u0010º\u0002\u001a\u00030Í\u00012\u0007\u0010ñ\u0001\u001a\u00020[H\u0016J\u0012\u0010q\u001a\u00030Í\u00012\u0007\u0010ñ\u0001\u001a\u00020rH\u0016J\u0014\u0010¼\u0002\u001a\u00030Í\u00012\b\u0010ñ\u0001\u001a\u00030Ö\u0001H\u0016J$\u0010¼\u0002\u001a\u00030Í\u00012\u000e\u0010½\u0002\u001a\t\u0012\u0004\u0012\u00020\f0¾\u00022\b\u0010ñ\u0001\u001a\u00030Ö\u0001H\u0016J4\u0010¼\u0002\u001a\u00030Í\u00012\u000e\u0010¿\u0002\u001a\t\u0012\u0004\u0012\u00020\f0¾\u00022\u000e\u0010À\u0002\u001a\t\u0012\u0004\u0012\u00020\f0¾\u00022\b\u0010ñ\u0001\u001a\u00030Ö\u0001H\u0016J\u0014\u0010Æ\u0002\u001a\u00030Í\u00012\b\u0010ñ\u0001\u001a\u00030Ï\u0002H\u0016J(\u0010Æ\u0002\u001a\u00030Í\u00012\u0016\u0010ñ\u0001\u001a\f\u0012\u0007\b\u0001\u0012\u00030Ï\u00020Ð\u0002\"\u00030Ï\u0002H\u0016¢\u0006\u0003\u0010Ñ\u0002J\u0014\u0010Ì\u0002\u001a\u00030Í\u00012\b\u0010ñ\u0001\u001a\u00030Ï\u0002H\u0016J(\u0010Ì\u0002\u001a\u00030Í\u00012\u0016\u0010ñ\u0001\u001a\f\u0012\u0007\b\u0001\u0012\u00030Ï\u00020Ð\u0002\"\u00030Ï\u0002H\u0016¢\u0006\u0003\u0010Ñ\u0002J\u0014\u0010Ò\u0002\u001a\u00030Í\u00012\b\u0010ñ\u0001\u001a\u00030â\u0001H\u0016J\u001b\u0010\u0099\u0001\u001a\u00030Í\u00012\u0007\u0010ñ\u0001\u001a\u00020TH\u0016¢\u0006\u0005\bì\u0002\u0010XJ\u0013\u0010\u009c\u0001\u001a\u00030Í\u00012\u0007\u0010ñ\u0001\u001a\u00020[H\u0016J\u0014\u0010è\u0002\u001a\u00030Í\u00012\b\u0010ñ\u0001\u001a\u00030é\u0002H\u0016J\u0014\u0010\u009f\u0001\u001a\u00030Í\u00012\b\u0010ñ\u0001\u001a\u00030 \u0001H\u0016J\u0014\u0010¥\u0001\u001a\u00030Í\u00012\b\u0010ñ\u0001\u001a\u00030¦\u0001H\u0016J\u001c\u0010«\u0001\u001a\u00030Í\u00012\b\u0010ñ\u0001\u001a\u00030¬\u0001H\u0016¢\u0006\u0005\bí\u0002\u0010XJ\u001c\u0010¯\u0001\u001a\u00030Í\u00012\b\u0010ñ\u0001\u001a\u00030¬\u0001H\u0016¢\u0006\u0005\bî\u0002\u0010XJ\u001c\u0010²\u0001\u001a\u00030Í\u00012\b\u0010ñ\u0001\u001a\u00030¬\u0001H\u0016¢\u0006\u0005\bï\u0002\u0010XJ\u001c\u0010µ\u0001\u001a\u00030Í\u00012\b\u0010ñ\u0001\u001a\u00030¶\u0001H\u0016¢\u0006\u0005\bð\u0002\u0010\u0016J\u001d\u0010º\u0001\u001a\u00030Í\u00012\b\u0010ñ\u0001\u001a\u00030»\u0001H\u0016¢\u0006\u0006\bñ\u0002\u0010¿\u0001J\u001d\u0010Ó\u0002\u001a\u00030Í\u00012\b\u0010ñ\u0001\u001a\u00030Ô\u0002H\u0016¢\u0006\u0006\bò\u0002\u0010¿\u0001J\u001d\u0010Ö\u0002\u001a\u00030Í\u00012\b\u0010ñ\u0001\u001a\u00030×\u0002H\u0016¢\u0006\u0006\bó\u0002\u0010¿\u0001J\u001d\u0010Ù\u0002\u001a\u00030Í\u00012\b\u0010ñ\u0001\u001a\u00030Ú\u0002H\u0016¢\u0006\u0006\bô\u0002\u0010¿\u0001J\u001d\u0010Ü\u0002\u001a\u00030Í\u00012\b\u0010ñ\u0001\u001a\u00030Ý\u0002H\u0016¢\u0006\u0006\bõ\u0002\u0010¿\u0001J\u0014\u0010ß\u0002\u001a\u00030Í\u00012\b\u0010ñ\u0001\u001a\u00030à\u0002H\u0016J\u001d\u0010å\u0002\u001a\u00030Í\u00012\b\u0010ñ\u0001\u001a\u00030æ\u0002H\u0016¢\u0006\u0006\bö\u0002\u0010¿\u0001Ju\u0010Û\u0001\u001a\u00030Í\u0001\"\u0005\b\u0000\u0010Â\u00022\u000f\u0010÷\u0002\u001a\n\u0012\u0005\u0012\u0003HÂ\u00020ø\u00022\b\u0010ñ\u0001\u001a\u00030Ö\u00012G\u0010ù\u0002\u001aB\u0012\u001e\u0012\u001c\u0012\u0005\u0012\u0003HÂ\u00020ø\u0002¢\u0006\u000f\bû\u0002\u0012\n\bü\u0002\u0012\u0005\b\b(÷\u0002\u0012\u0017\u0012\u00150Ü\u0001¢\u0006\u000f\bû\u0002\u0012\n\bü\u0002\u0012\u0005\b\b(Û\u0001\u0012\u0004\u0012\u00020\u000f0ú\u0002H\u0016J*\u0010ý\u0002\u001a\u00030Í\u00012\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010×\u0001\u001a\u00030Ø\u00012\u0006\u0010\u000e\u001a\u00020\u000fH\u0000¢\u0006\u0003\bþ\u0002J\u0010\u0010ÿ\u0002\u001a\u00030Í\u0001H\u0000¢\u0006\u0003\b\u0080\u0003J\u0012\u0010\u0081\u0003\u001a\u00020\t2\u0007\u0010\u0082\u0003\u001a\u00020\u0006H\u0002J%\u0010\u0083\u0003\u001a\u00030Í\u00012\u0007\u0010÷\u0002\u001a\u00020\u00062\u000f\u0010\u0084\u0003\u001a\n\u0012\u0005\u0012\u00030Í\u00010\u0085\u0003H\u0082\bJ&\u0010\u0083\u0003\u001a\u00030Í\u00012\u0007\u0010÷\u0002\u001a\u00020\u00062\u0007\u0010ù\u0002\u001a\u00020\u000f2\b\u0010Õ\u0001\u001a\u00030Ö\u0001H\u0002J\u000b\u0010\u0086\u0003\u001a\u00030Í\u0001H\u0082\bR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\n\u001a\u00020\u00068\u0000@\u0000X\u0081\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0012\u001a\u00020\fX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0017\u001a\u00020\fX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0014\"\u0004\b\u0019\u0010\u0016R\u001a\u0010\u001a\u001a\u00020\fX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0014\"\u0004\b\u001c\u0010\u0016R\u001a\u0010\u001d\u001a\u00020\fX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u0014\"\u0004\b\u001f\u0010\u0016R\u001a\u0010 \u001a\u00020\fX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u0014\"\u0004\b\"\u0010\u0016R\u001a\u0010#\u001a\u00020\fX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\u0014\"\u0004\b%\u0010\u0016R\u001a\u0010&\u001a\u00020\fX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010\u0014\"\u0004\b(\u0010\u0016R\u001a\u0010)\u001a\u00020\fX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010\u0014\"\u0004\b+\u0010\u0016R\u001a\u0010,\u001a\u00020\fX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010\u0014\"\u0004\b.\u0010\u0016R\u001a\u0010/\u001a\u00020\fX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u0010\u0014\"\u0004\b1\u0010\u0016R\u001a\u00102\u001a\u00020\fX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b3\u0010\u0014\"\u0004\b4\u0010\u0016R\u001a\u00105\u001a\u00020\fX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u0010\u0014\"\u0004\b7\u0010\u0016R\u001a\u00108\u001a\u00020\fX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b9\u0010\u0014\"\u0004\b:\u0010\u0016R\u001a\u0010;\u001a\u00020\fX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b<\u0010\u0014\"\u0004\b=\u0010\u0016R\u001a\u0010>\u001a\u00020\fX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b?\u0010\u0014\"\u0004\b@\u0010\u0016R\u001a\u0010A\u001a\u00020\fX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bB\u0010\u0014\"\u0004\bC\u0010\u0016R\u001a\u0010D\u001a\u00020\fX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bE\u0010\u0014\"\u0004\bF\u0010\u0016R\u001a\u0010G\u001a\u00020\fX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bH\u0010\u0014\"\u0004\bI\u0010\u0016R\u001a\u0010J\u001a\u00020\fX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bK\u0010\u0014\"\u0004\bL\u0010\u0016R\u001a\u0010M\u001a\u00020\fX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bN\u0010\u0014\"\u0004\bO\u0010\u0016R\u001a\u0010P\u001a\u00020\fX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bQ\u0010\u0014\"\u0004\bR\u0010\u0016R\u001c\u0010S\u001a\u00020TX\u0080\u000e¢\u0006\u0010\n\u0002\u0010Y\u001a\u0004\bU\u0010V\"\u0004\bW\u0010XR\u001c\u0010Z\u001a\u0004\u0018\u00010[X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\\\u0010]\"\u0004\b^\u0010_R\u001c\u0010`\u001a\u00020TX\u0080\u000e¢\u0006\u0010\n\u0002\u0010Y\u001a\u0004\ba\u0010V\"\u0004\bb\u0010XR\u001c\u0010c\u001a\u0004\u0018\u00010[X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bd\u0010]\"\u0004\be\u0010_R\u001c\u0010f\u001a\u00020TX\u0080\u000e¢\u0006\u0010\n\u0002\u0010Y\u001a\u0004\bg\u0010V\"\u0004\bh\u0010XR\u001c\u0010i\u001a\u0004\u0018\u00010[X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bj\u0010]\"\u0004\bk\u0010_R\u001a\u0010l\u001a\u00020\u000fX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bm\u0010n\"\u0004\bo\u0010pR\u001a\u0010q\u001a\u00020rX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bs\u0010t\"\u0004\bu\u0010vR\u001a\u0010w\u001a\u00020\fX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bx\u0010\u0014\"\u0004\by\u0010\u0016R\u001a\u0010z\u001a\u00020\fX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b{\u0010\u0014\"\u0004\b|\u0010\u0016R\u001a\u0010}\u001a\u00020\fX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b~\u0010\u0014\"\u0004\b\u007f\u0010\u0016R\u001d\u0010\u0080\u0001\u001a\u00020\fX\u0080\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0081\u0001\u0010\u0014\"\u0005\b\u0082\u0001\u0010\u0016R\u001d\u0010\u0083\u0001\u001a\u00020\fX\u0080\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0084\u0001\u0010\u0014\"\u0005\b\u0085\u0001\u0010\u0016R\u001d\u0010\u0086\u0001\u001a\u00020\fX\u0080\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0087\u0001\u0010\u0014\"\u0005\b\u0088\u0001\u0010\u0016R\u001d\u0010\u0089\u0001\u001a\u00020\fX\u0080\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u008a\u0001\u0010\u0014\"\u0005\b\u008b\u0001\u0010\u0016R\u001d\u0010\u008c\u0001\u001a\u00020\fX\u0080\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u008d\u0001\u0010\u0014\"\u0005\b\u008e\u0001\u0010\u0016R \u0010\u008f\u0001\u001a\u00030\u0090\u0001X\u0080\u000e¢\u0006\u0012\n\u0002\u0010Y\u001a\u0005\b\u0091\u0001\u0010V\"\u0005\b\u0092\u0001\u0010XR\u001d\u0010\u0093\u0001\u001a\u00020\fX\u0080\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0094\u0001\u0010\u0014\"\u0005\b\u0095\u0001\u0010\u0016R\u001d\u0010\u0096\u0001\u001a\u00020\fX\u0080\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0097\u0001\u0010\u0014\"\u0005\b\u0098\u0001\u0010\u0016R\u001f\u0010\u0099\u0001\u001a\u00020TX\u0080\u000e¢\u0006\u0012\n\u0002\u0010Y\u001a\u0005\b\u009a\u0001\u0010V\"\u0005\b\u009b\u0001\u0010XR\u001f\u0010\u009c\u0001\u001a\u0004\u0018\u00010[X\u0080\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u009d\u0001\u0010]\"\u0005\b\u009e\u0001\u0010_R\"\u0010\u009f\u0001\u001a\u0005\u0018\u00010 \u0001X\u0080\u000e¢\u0006\u0012\n\u0000\u001a\u0006\b¡\u0001\u0010¢\u0001\"\u0006\b£\u0001\u0010¤\u0001R\"\u0010¥\u0001\u001a\u0005\u0018\u00010¦\u0001X\u0080\u000e¢\u0006\u0012\n\u0000\u001a\u0006\b§\u0001\u0010¨\u0001\"\u0006\b©\u0001\u0010ª\u0001R \u0010«\u0001\u001a\u00030¬\u0001X\u0080\u000e¢\u0006\u0012\n\u0002\u0010Y\u001a\u0005\b\u00ad\u0001\u0010V\"\u0005\b®\u0001\u0010XR \u0010¯\u0001\u001a\u00030¬\u0001X\u0080\u000e¢\u0006\u0012\n\u0002\u0010Y\u001a\u0005\b°\u0001\u0010V\"\u0005\b±\u0001\u0010XR \u0010²\u0001\u001a\u00030¬\u0001X\u0080\u000e¢\u0006\u0012\n\u0002\u0010Y\u001a\u0005\b³\u0001\u0010V\"\u0005\b´\u0001\u0010XR!\u0010µ\u0001\u001a\u00030¶\u0001X\u0080\u000e¢\u0006\u0013\n\u0003\u0010¹\u0001\u001a\u0005\b·\u0001\u0010\u0014\"\u0005\b¸\u0001\u0010\u0016R#\u0010º\u0001\u001a\u00030»\u0001X\u0080\u000e¢\u0006\u0015\n\u0003\u0010À\u0001\u001a\u0006\b¼\u0001\u0010½\u0001\"\u0006\b¾\u0001\u0010¿\u0001R\u001f\u0010Á\u0001\u001a\u00020\u0006X\u0080\u000e¢\u0006\u0012\n\u0000\u001a\u0006\bÂ\u0001\u0010½\u0001\"\u0006\bÃ\u0001\u0010¿\u0001R\u001f\u0010è\u0001\u001a\n\u0012\u0005\u0012\u00030ç\u00010é\u00018VX\u0096\u0004¢\u0006\b\u001a\u0006\bê\u0001\u0010ë\u0001R\u0016\u0010×\u0001\u001a\u00020\f8VX\u0096\u0004¢\u0006\u0007\u001a\u0005\bì\u0001\u0010\u0014R\u0016\u0010í\u0001\u001a\u00020\f8VX\u0096\u0004¢\u0006\u0007\u001a\u0005\bî\u0001\u0010\u0014R\u0018\u0010Û\u0001\u001a\u00030Ü\u00018VX\u0096\u0004¢\u0006\b\u001a\u0006\bï\u0001\u0010ð\u0001R+\u0010Á\u0002\u001a\u0003HÂ\u0002\"\u0005\b\u0000\u0010Â\u0002*\n\u0012\u0005\u0012\u0003HÂ\u00020Ã\u00028VX\u0096\u0004¢\u0006\b\u001a\u0006\bÄ\u0002\u0010Å\u0002R\"\u0010Æ\u0002\u001a\u0005\u0018\u00010Ç\u0002X\u0080\u000e¢\u0006\u0012\n\u0000\u001a\u0006\bÈ\u0002\u0010É\u0002\"\u0006\bÊ\u0002\u0010Ë\u0002R\"\u0010Ì\u0002\u001a\u0005\u0018\u00010Ç\u0002X\u0080\u000e¢\u0006\u0012\n\u0000\u001a\u0006\bÍ\u0002\u0010É\u0002\"\u0006\bÎ\u0002\u0010Ë\u0002R\u0018\u0010Ó\u0002\u001a\u00030Ô\u00028@X\u0080\u0004¢\u0006\b\u001a\u0006\bÕ\u0002\u0010½\u0001R\u0018\u0010Ö\u0002\u001a\u00030×\u00028@X\u0080\u0004¢\u0006\b\u001a\u0006\bØ\u0002\u0010½\u0001R\u0018\u0010Ù\u0002\u001a\u00030Ú\u00028@X\u0080\u0004¢\u0006\b\u001a\u0006\bÛ\u0002\u0010½\u0001R\u0018\u0010Ü\u0002\u001a\u00030Ý\u00028@X\u0080\u0004¢\u0006\b\u001a\u0006\bÞ\u0002\u0010½\u0001R\u0018\u0010ß\u0002\u001a\u00030à\u00028@X\u0080\u0004¢\u0006\b\u001a\u0006\bá\u0002\u0010â\u0002R\u0016\u0010ã\u0002\u001a\u00020\u000f8@X\u0080\u0004¢\u0006\u0007\u001a\u0005\bä\u0002\u0010nR\u0018\u0010å\u0002\u001a\u00030æ\u00028@X\u0080\u0004¢\u0006\b\u001a\u0006\bç\u0002\u0010½\u0001R\u0018\u0010è\u0002\u001a\u00030é\u00028@X\u0080\u0004¢\u0006\b\u001a\u0006\bê\u0002\u0010ë\u0002R\u0018\u0010\u0087\u0003\u001a\u00020\u00068Â\u0002X\u0082\u0004¢\u0006\b\u001a\u0006\b\u0088\u0003\u0010½\u0001¨\u0006\u0089\u0003"}, d2 = {"Landroidx/compose/foundation/style/ResolvedStyle;", "Landroidx/compose/foundation/style/StyleScope;", "Landroidx/compose/ui/platform/InspectableValue;", "<init>", "()V", "compositeHash", "", "currentIndex", "indexStack", "Landroidx/collection/MutableIntList;", "flags", "_density", "", "_fontScale", "animating", "", "node", "Landroidx/compose/foundation/style/StyleOuterNode;", "contentPaddingStart", "getContentPaddingStart$foundation", "()F", "setContentPaddingStart$foundation", "(F)V", "contentPaddingEnd", "getContentPaddingEnd$foundation", "setContentPaddingEnd$foundation", "contentPaddingTop", "getContentPaddingTop$foundation", "setContentPaddingTop$foundation", "contentPaddingBottom", "getContentPaddingBottom$foundation", "setContentPaddingBottom$foundation", "externalPaddingStart", "getExternalPaddingStart$foundation", "setExternalPaddingStart$foundation", "externalPaddingEnd", "getExternalPaddingEnd$foundation", "setExternalPaddingEnd$foundation", "externalPaddingTop", "getExternalPaddingTop$foundation", "setExternalPaddingTop$foundation", "externalPaddingBottom", "getExternalPaddingBottom$foundation", "setExternalPaddingBottom$foundation", "borderWidth", "getBorderWidth$foundation", "setBorderWidth$foundation", "width", "getWidth$foundation", "setWidth$foundation", "height", "getHeight$foundation", "setHeight$foundation", "widthFraction", "getWidthFraction$foundation", "setWidthFraction$foundation", "heightFraction", "getHeightFraction$foundation", "setHeightFraction$foundation", "left", "getLeft$foundation", "setLeft$foundation", "top", "getTop$foundation", "setTop$foundation", "right", "getRight$foundation", "setRight$foundation", "bottom", "getBottom$foundation", "setBottom$foundation", "minHeight", "getMinHeight$foundation", "setMinHeight$foundation", "maxHeight", "getMaxHeight$foundation", "setMaxHeight$foundation", "minWidth", "getMinWidth$foundation", "setMinWidth$foundation", "maxWidth", "getMaxWidth$foundation", "setMaxWidth$foundation", "borderColor", "Landroidx/compose/ui/graphics/Color;", "getBorderColor-0d7_KjU$foundation", "()J", "setBorderColor-8_81llA$foundation", "(J)V", "J", "borderBrush", "Landroidx/compose/ui/graphics/Brush;", "getBorderBrush$foundation", "()Landroidx/compose/ui/graphics/Brush;", "setBorderBrush$foundation", "(Landroidx/compose/ui/graphics/Brush;)V", "backgroundColor", "getBackgroundColor-0d7_KjU$foundation", "setBackgroundColor-8_81llA$foundation", "backgroundBrush", "getBackgroundBrush$foundation", "setBackgroundBrush$foundation", "foregroundColor", "getForegroundColor-0d7_KjU$foundation", "setForegroundColor-8_81llA$foundation", "foregroundBrush", "getForegroundBrush$foundation", "setForegroundBrush$foundation", "clip", "getClip$foundation", "()Z", "setClip$foundation", "(Z)V", "shape", "Landroidx/compose/ui/graphics/Shape;", "getShape$foundation", "()Landroidx/compose/ui/graphics/Shape;", "setShape$foundation", "(Landroidx/compose/ui/graphics/Shape;)V", "alpha", "getAlpha$foundation", "setAlpha$foundation", "scaleX", "getScaleX$foundation", "setScaleX$foundation", "scaleY", "getScaleY$foundation", "setScaleY$foundation", "translationX", "getTranslationX$foundation", "setTranslationX$foundation", "translationY", "getTranslationY$foundation", "setTranslationY$foundation", "rotationX", "getRotationX$foundation", "setRotationX$foundation", "rotationY", "getRotationY$foundation", "setRotationY$foundation", "rotationZ", "getRotationZ$foundation", "setRotationZ$foundation", "transformOrigin", "Landroidx/compose/ui/graphics/TransformOrigin;", "getTransformOrigin-SzJe1aQ$foundation", "setTransformOrigin-__ExYCQ$foundation", "cameraDistance", "getCameraDistance$foundation", "setCameraDistance$foundation", "zIndex", "getZIndex$foundation", "setZIndex$foundation", "contentColor", "getContentColor-0d7_KjU$foundation", "setContentColor-8_81llA$foundation", "contentBrush", "getContentBrush$foundation", "setContentBrush$foundation", "fontFamily", "Landroidx/compose/ui/text/font/FontFamily;", "getFontFamily$foundation", "()Landroidx/compose/ui/text/font/FontFamily;", "setFontFamily$foundation", "(Landroidx/compose/ui/text/font/FontFamily;)V", "textIndent", "Landroidx/compose/ui/text/style/TextIndent;", "getTextIndent$foundation", "()Landroidx/compose/ui/text/style/TextIndent;", "setTextIndent$foundation", "(Landroidx/compose/ui/text/style/TextIndent;)V", "fontSize", "Landroidx/compose/ui/unit/TextUnit;", "getFontSize-XSAIIZE$foundation", "setFontSize--R2X_6o$foundation", "lineHeight", "getLineHeight-XSAIIZE$foundation", "setLineHeight--R2X_6o$foundation", "letterSpacing", "getLetterSpacing-XSAIIZE$foundation", "setLetterSpacing--R2X_6o$foundation", "baselineShift", "Landroidx/compose/ui/text/style/BaselineShift;", "getBaselineShift-y9eOQZs$foundation", "setBaselineShift-4Dl_Bck$foundation", "F", "lineBreak", "Landroidx/compose/ui/text/style/LineBreak;", "getLineBreak-rAG3T2k$foundation", "()I", "setLineBreak-CZqVlQI$foundation", "(I)V", "I", "textEnums", "getTextEnums$foundation", "setTextEnums$foundation", "diff", "other", "filterFlags", "diff$foundation", "copy", "copy$foundation", "copyInheritedStyles", "copyInheritedStyles$foundation", "copyInheritedStylesInto", "", TypedValues.AttributesType.S_TARGET, "copyInheritedStylesInto$foundation", "copyInto", "copyInto$foundation", "clear", "clear$foundation", "resolve", "style", "Landroidx/compose/foundation/style/Style;", "density", "Landroidx/compose/ui/unit/Density;", "resolve$foundation", "resolveForTesting", "state", "Landroidx/compose/foundation/style/StyleState;", "resolveForTesting$foundation", "applyInheritableStyles", "source", "applyInheritableStyles$foundation", "toTextStyle", "Landroidx/compose/ui/text/TextStyle;", "fallback", "toTextStyle$foundation", "valueElements", "", "Landroidx/compose/ui/platform/ValueElement;", "inspectableElements", "Lkotlin/sequences/Sequence;", "getInspectableElements", "()Lkotlin/sequences/Sequence;", "getDensity", "fontScale", "getFontScale", "getState", "()Landroidx/compose/foundation/style/StyleState;", "value", "Landroidx/compose/ui/unit/Dp;", "contentPaddingStart-0680j_4", "contentPaddingEnd-0680j_4", "contentPaddingTop-0680j_4", "contentPaddingBottom-0680j_4", "contentPaddingHorizontal", "contentPaddingHorizontal-0680j_4", "contentPaddingVertical", "contentPaddingVertical-0680j_4", "contentPadding", "contentPadding-0680j_4", "start", "end", "contentPadding-a9UjIt4", "(FFFF)V", "horizontal", "vertical", "contentPadding-YgX7TsA", "(FF)V", "externalPaddingStart-0680j_4", "externalPaddingEnd-0680j_4", "externalPaddingTop-0680j_4", "externalPaddingBottom-0680j_4", "externalPaddingHorizontal", "externalPaddingHorizontal-0680j_4", "externalPaddingVertical", "externalPaddingVertical-0680j_4", "externalPadding", "externalPadding-0680j_4", "externalPadding-a9UjIt4", "externalPadding-YgX7TsA", "borderWidth-0680j_4", "borderColor-8_81llA", "border", TypedValues.Custom.S_COLOR, "border-cXLIe8U", "(FJ)V", "brush", "border-D5KLDUw", "(FLandroidx/compose/ui/graphics/Brush;)V", "width-0680j_4", "height-0680j_4", "size", "size-YgX7TsA", "size-0680j_4", "Landroidx/compose/ui/unit/DpSize;", "size-EaSLcWc", "fraction", "left-0680j_4", "top-0680j_4", "right-0680j_4", "bottom-0680j_4", "minWidth-0680j_4", "minHeight-0680j_4", "minSize", "minSize-EaSLcWc", "minSize-YgX7TsA", "maxWidth-0680j_4", "maxHeight-0680j_4", "maxSize", "maxSize-EaSLcWc", "maxSize-YgX7TsA", "scale", "translation", "x", "y", TypedValues.CycleType.S_WAVE_OFFSET, "Landroidx/compose/ui/geometry/Offset;", "translation-k-4lQ0M", "transformOrigin-__ExYCQ", "background", "background-8_81llA", "foreground", "foreground-8_81llA", "animate", "spec", "Landroidx/compose/animation/core/AnimationSpec;", "toSpec", "fromSpec", "currentValue", "T", "Landroidx/compose/runtime/CompositionLocal;", "getCurrentValue", "(Landroidx/compose/runtime/CompositionLocal;)Ljava/lang/Object;", "dropShadow", "", "getDropShadow$foundation", "()Ljava/lang/Object;", "setDropShadow$foundation", "(Ljava/lang/Object;)V", "innerShadow", "getInnerShadow$foundation", "setInnerShadow$foundation", "Landroidx/compose/ui/graphics/shadow/Shadow;", "", "([Landroidx/compose/ui/graphics/shadow/Shadow;)V", "textStyle", "fontStyle", "Landroidx/compose/ui/text/font/FontStyle;", "getFontStyle-_-LCdwA$foundation", "textAlign", "Landroidx/compose/ui/text/style/TextAlign;", "getTextAlign-e0LSkKk$foundation", "textDirection", "Landroidx/compose/ui/text/style/TextDirection;", "getTextDirection-s_7X-co$foundation", "hyphens", "Landroidx/compose/ui/text/style/Hyphens;", "getHyphens-vmbZdU8$foundation", "fontWeight", "Landroidx/compose/ui/text/font/FontWeight;", "getFontWeight$foundation", "()Landroidx/compose/ui/text/font/FontWeight;", "isFontWeightSpecified", "isFontWeightSpecified$foundation", "fontSynthesis", "Landroidx/compose/ui/text/font/FontSynthesis;", "getFontSynthesis-GVVA2EU$foundation", "textDecoration", "Landroidx/compose/ui/text/style/TextDecoration;", "getTextDecoration$foundation", "()Landroidx/compose/ui/text/style/TextDecoration;", "contentColor-8_81llA", "fontSize--R2X_6o", "lineHeight--R2X_6o", "letterSpacing--R2X_6o", "baselineShift-4Dl_Bck", "lineBreak-CZqVlQI", "fontStyle-nzbMABs", "textAlign-aXe7zB0", "textDirection-Hejc4pk", "hyphens--3fSNIE", "fontSynthesis-6p3vJLY", "key", "Landroidx/compose/foundation/style/StyleStateKey;", "active", "Lkotlin/Function2;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "startResolve", "startResolve$foundation", "doneResolve", "doneResolve$foundation", "pushIndex", "index", "group", "block", "Lkotlin/Function0;", "skippedGroup", "currentCompositeHash", "getCurrentCompositeHash", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class ResolvedStyle implements StyleScope, InspectableValue {
    public static final int $stable = 0;
    private boolean animating;
    private Brush backgroundBrush;
    private Brush borderBrush;
    private float borderWidth;
    private boolean clip;
    private int compositeHash;
    private Brush contentBrush;
    private float contentPaddingBottom;
    private float contentPaddingEnd;
    private float contentPaddingStart;
    private float contentPaddingTop;
    private int currentIndex;
    private Object dropShadow;
    private float externalPaddingBottom;
    private float externalPaddingEnd;
    private float externalPaddingStart;
    private float externalPaddingTop;
    public int flags;
    private FontFamily fontFamily;
    private Brush foregroundBrush;
    private MutableIntList indexStack;
    private Object innerShadow;
    private StyleOuterNode node;
    private float rotationX;
    private float rotationY;
    private float rotationZ;
    private int textEnums;
    private TextIndent textIndent;
    private float translationX;
    private float translationY;
    private float zIndex;
    private float _density = 1.0f;
    private float _fontScale = 1.0f;
    private float width = Float.NaN;
    private float height = Float.NaN;
    private float widthFraction = Float.NaN;
    private float heightFraction = Float.NaN;
    private float left = Float.NaN;
    private float top = Float.NaN;
    private float right = Float.NaN;
    private float bottom = Float.NaN;
    private float minHeight = Float.NaN;
    private float maxHeight = Float.NaN;
    private float minWidth = Float.NaN;
    private float maxWidth = Float.NaN;
    private long borderColor = Color.INSTANCE.m5339getBlack0d7_KjU();
    private long backgroundColor = Color.INSTANCE.m5348getTransparent0d7_KjU();
    private long foregroundColor = Color.INSTANCE.m5349getUnspecified0d7_KjU();
    private Shape shape = RectangleShapeKt.getRectangleShape();
    private float alpha = 1.0f;
    private float scaleX = 1.0f;
    private float scaleY = 1.0f;
    private long transformOrigin = TransformOrigin.INSTANCE.m5726getCenterSzJe1aQ();
    private float cameraDistance = 1.0f;
    private long contentColor = Color.INSTANCE.m5349getUnspecified0d7_KjU();
    private long fontSize = TextUnit.INSTANCE.m8355getUnspecifiedXSAIIZE();
    private long lineHeight = TextUnit.INSTANCE.m8355getUnspecifiedXSAIIZE();
    private long letterSpacing = TextUnit.INSTANCE.m8355getUnspecifiedXSAIIZE();
    private float baselineShift = BaselineShift.INSTANCE.m7879getUnspecifiedy9eOQZs();
    private int lineBreak = LineBreak.INSTANCE.m7921getUnspecifiedrAG3T2k();

    /* JADX INFO: renamed from: getContentPaddingStart$foundation, reason: from getter */
    public final float getContentPaddingStart() {
        return this.contentPaddingStart;
    }

    public final void setContentPaddingStart$foundation(float f) {
        this.contentPaddingStart = f;
    }

    /* JADX INFO: renamed from: getContentPaddingEnd$foundation, reason: from getter */
    public final float getContentPaddingEnd() {
        return this.contentPaddingEnd;
    }

    public final void setContentPaddingEnd$foundation(float f) {
        this.contentPaddingEnd = f;
    }

    /* JADX INFO: renamed from: getContentPaddingTop$foundation, reason: from getter */
    public final float getContentPaddingTop() {
        return this.contentPaddingTop;
    }

    public final void setContentPaddingTop$foundation(float f) {
        this.contentPaddingTop = f;
    }

    /* JADX INFO: renamed from: getContentPaddingBottom$foundation, reason: from getter */
    public final float getContentPaddingBottom() {
        return this.contentPaddingBottom;
    }

    public final void setContentPaddingBottom$foundation(float f) {
        this.contentPaddingBottom = f;
    }

    /* JADX INFO: renamed from: getExternalPaddingStart$foundation, reason: from getter */
    public final float getExternalPaddingStart() {
        return this.externalPaddingStart;
    }

    public final void setExternalPaddingStart$foundation(float f) {
        this.externalPaddingStart = f;
    }

    /* JADX INFO: renamed from: getExternalPaddingEnd$foundation, reason: from getter */
    public final float getExternalPaddingEnd() {
        return this.externalPaddingEnd;
    }

    public final void setExternalPaddingEnd$foundation(float f) {
        this.externalPaddingEnd = f;
    }

    /* JADX INFO: renamed from: getExternalPaddingTop$foundation, reason: from getter */
    public final float getExternalPaddingTop() {
        return this.externalPaddingTop;
    }

    public final void setExternalPaddingTop$foundation(float f) {
        this.externalPaddingTop = f;
    }

    /* JADX INFO: renamed from: getExternalPaddingBottom$foundation, reason: from getter */
    public final float getExternalPaddingBottom() {
        return this.externalPaddingBottom;
    }

    public final void setExternalPaddingBottom$foundation(float f) {
        this.externalPaddingBottom = f;
    }

    /* JADX INFO: renamed from: getBorderWidth$foundation, reason: from getter */
    public final float getBorderWidth() {
        return this.borderWidth;
    }

    public final void setBorderWidth$foundation(float f) {
        this.borderWidth = f;
    }

    /* JADX INFO: renamed from: getWidth$foundation, reason: from getter */
    public final float getWidth() {
        return this.width;
    }

    public final void setWidth$foundation(float f) {
        this.width = f;
    }

    /* JADX INFO: renamed from: getHeight$foundation, reason: from getter */
    public final float getHeight() {
        return this.height;
    }

    public final void setHeight$foundation(float f) {
        this.height = f;
    }

    /* JADX INFO: renamed from: getWidthFraction$foundation, reason: from getter */
    public final float getWidthFraction() {
        return this.widthFraction;
    }

    public final void setWidthFraction$foundation(float f) {
        this.widthFraction = f;
    }

    /* JADX INFO: renamed from: getHeightFraction$foundation, reason: from getter */
    public final float getHeightFraction() {
        return this.heightFraction;
    }

    public final void setHeightFraction$foundation(float f) {
        this.heightFraction = f;
    }

    /* JADX INFO: renamed from: getLeft$foundation, reason: from getter */
    public final float getLeft() {
        return this.left;
    }

    public final void setLeft$foundation(float f) {
        this.left = f;
    }

    /* JADX INFO: renamed from: getTop$foundation, reason: from getter */
    public final float getTop() {
        return this.top;
    }

    public final void setTop$foundation(float f) {
        this.top = f;
    }

    /* JADX INFO: renamed from: getRight$foundation, reason: from getter */
    public final float getRight() {
        return this.right;
    }

    public final void setRight$foundation(float f) {
        this.right = f;
    }

    /* JADX INFO: renamed from: getBottom$foundation, reason: from getter */
    public final float getBottom() {
        return this.bottom;
    }

    public final void setBottom$foundation(float f) {
        this.bottom = f;
    }

    /* JADX INFO: renamed from: getMinHeight$foundation, reason: from getter */
    public final float getMinHeight() {
        return this.minHeight;
    }

    public final void setMinHeight$foundation(float f) {
        this.minHeight = f;
    }

    /* JADX INFO: renamed from: getMaxHeight$foundation, reason: from getter */
    public final float getMaxHeight() {
        return this.maxHeight;
    }

    public final void setMaxHeight$foundation(float f) {
        this.maxHeight = f;
    }

    /* JADX INFO: renamed from: getMinWidth$foundation, reason: from getter */
    public final float getMinWidth() {
        return this.minWidth;
    }

    public final void setMinWidth$foundation(float f) {
        this.minWidth = f;
    }

    /* JADX INFO: renamed from: getMaxWidth$foundation, reason: from getter */
    public final float getMaxWidth() {
        return this.maxWidth;
    }

    public final void setMaxWidth$foundation(float f) {
        this.maxWidth = f;
    }

    /* JADX INFO: renamed from: getBorderColor-0d7_KjU$foundation, reason: not valid java name and from getter */
    public final long getBorderColor() {
        return this.borderColor;
    }

    /* JADX INFO: renamed from: setBorderColor-8_81llA$foundation, reason: not valid java name */
    public final void m1443setBorderColor8_81llA$foundation(long j) {
        this.borderColor = j;
    }

    /* JADX INFO: renamed from: getBorderBrush$foundation, reason: from getter */
    public final Brush getBorderBrush() {
        return this.borderBrush;
    }

    public final void setBorderBrush$foundation(Brush brush) {
        this.borderBrush = brush;
    }

    /* JADX INFO: renamed from: getBackgroundColor-0d7_KjU$foundation, reason: not valid java name and from getter */
    public final long getBackgroundColor() {
        return this.backgroundColor;
    }

    /* JADX INFO: renamed from: setBackgroundColor-8_81llA$foundation, reason: not valid java name */
    public final void m1441setBackgroundColor8_81llA$foundation(long j) {
        this.backgroundColor = j;
    }

    /* JADX INFO: renamed from: getBackgroundBrush$foundation, reason: from getter */
    public final Brush getBackgroundBrush() {
        return this.backgroundBrush;
    }

    public final void setBackgroundBrush$foundation(Brush brush) {
        this.backgroundBrush = brush;
    }

    /* JADX INFO: renamed from: getForegroundColor-0d7_KjU$foundation, reason: not valid java name and from getter */
    public final long getForegroundColor() {
        return this.foregroundColor;
    }

    /* JADX INFO: renamed from: setForegroundColor-8_81llA$foundation, reason: not valid java name */
    public final void m1446setForegroundColor8_81llA$foundation(long j) {
        this.foregroundColor = j;
    }

    /* JADX INFO: renamed from: getForegroundBrush$foundation, reason: from getter */
    public final Brush getForegroundBrush() {
        return this.foregroundBrush;
    }

    public final void setForegroundBrush$foundation(Brush brush) {
        this.foregroundBrush = brush;
    }

    /* JADX INFO: renamed from: getClip$foundation, reason: from getter */
    public final boolean getClip() {
        return this.clip;
    }

    public final void setClip$foundation(boolean z) {
        this.clip = z;
    }

    /* JADX INFO: renamed from: getShape$foundation, reason: from getter */
    public final Shape getShape() {
        return this.shape;
    }

    public final void setShape$foundation(Shape shape) {
        this.shape = shape;
    }

    /* JADX INFO: renamed from: getAlpha$foundation, reason: from getter */
    public final float getAlpha() {
        return this.alpha;
    }

    public final void setAlpha$foundation(float f) {
        this.alpha = f;
    }

    /* JADX INFO: renamed from: getScaleX$foundation, reason: from getter */
    public final float getScaleX() {
        return this.scaleX;
    }

    public final void setScaleX$foundation(float f) {
        this.scaleX = f;
    }

    /* JADX INFO: renamed from: getScaleY$foundation, reason: from getter */
    public final float getScaleY() {
        return this.scaleY;
    }

    public final void setScaleY$foundation(float f) {
        this.scaleY = f;
    }

    /* JADX INFO: renamed from: getTranslationX$foundation, reason: from getter */
    public final float getTranslationX() {
        return this.translationX;
    }

    public final void setTranslationX$foundation(float f) {
        this.translationX = f;
    }

    /* JADX INFO: renamed from: getTranslationY$foundation, reason: from getter */
    public final float getTranslationY() {
        return this.translationY;
    }

    public final void setTranslationY$foundation(float f) {
        this.translationY = f;
    }

    /* JADX INFO: renamed from: getRotationX$foundation, reason: from getter */
    public final float getRotationX() {
        return this.rotationX;
    }

    public final void setRotationX$foundation(float f) {
        this.rotationX = f;
    }

    /* JADX INFO: renamed from: getRotationY$foundation, reason: from getter */
    public final float getRotationY() {
        return this.rotationY;
    }

    public final void setRotationY$foundation(float f) {
        this.rotationY = f;
    }

    /* JADX INFO: renamed from: getRotationZ$foundation, reason: from getter */
    public final float getRotationZ() {
        return this.rotationZ;
    }

    public final void setRotationZ$foundation(float f) {
        this.rotationZ = f;
    }

    /* JADX INFO: renamed from: getTransformOrigin-SzJe1aQ$foundation, reason: not valid java name and from getter */
    public final long getTransformOrigin() {
        return this.transformOrigin;
    }

    /* JADX INFO: renamed from: setTransformOrigin-__ExYCQ$foundation, reason: not valid java name */
    public final void m1450setTransformOrigin__ExYCQ$foundation(long j) {
        this.transformOrigin = j;
    }

    /* JADX INFO: renamed from: getCameraDistance$foundation, reason: from getter */
    public final float getCameraDistance() {
        return this.cameraDistance;
    }

    public final void setCameraDistance$foundation(float f) {
        this.cameraDistance = f;
    }

    /* JADX INFO: renamed from: getZIndex$foundation, reason: from getter */
    public final float getZIndex() {
        return this.zIndex;
    }

    public final void setZIndex$foundation(float f) {
        this.zIndex = f;
    }

    /* JADX INFO: renamed from: getContentColor-0d7_KjU$foundation, reason: not valid java name and from getter */
    public final long getContentColor() {
        return this.contentColor;
    }

    /* JADX INFO: renamed from: setContentColor-8_81llA$foundation, reason: not valid java name */
    public final void m1444setContentColor8_81llA$foundation(long j) {
        this.contentColor = j;
    }

    /* JADX INFO: renamed from: getContentBrush$foundation, reason: from getter */
    public final Brush getContentBrush() {
        return this.contentBrush;
    }

    public final void setContentBrush$foundation(Brush brush) {
        this.contentBrush = brush;
    }

    /* JADX INFO: renamed from: getFontFamily$foundation, reason: from getter */
    public final FontFamily getFontFamily() {
        return this.fontFamily;
    }

    public final void setFontFamily$foundation(FontFamily fontFamily) {
        this.fontFamily = fontFamily;
    }

    /* JADX INFO: renamed from: getTextIndent$foundation, reason: from getter */
    public final TextIndent getTextIndent() {
        return this.textIndent;
    }

    public final void setTextIndent$foundation(TextIndent textIndent) {
        this.textIndent = textIndent;
    }

    /* JADX INFO: renamed from: getFontSize-XSAIIZE$foundation, reason: not valid java name and from getter */
    public final long getFontSize() {
        return this.fontSize;
    }

    /* JADX INFO: renamed from: setFontSize--R2X_6o$foundation, reason: not valid java name */
    public final void m1445setFontSizeR2X_6o$foundation(long j) {
        this.fontSize = j;
    }

    /* JADX INFO: renamed from: getLineHeight-XSAIIZE$foundation, reason: not valid java name and from getter */
    public final long getLineHeight() {
        return this.lineHeight;
    }

    /* JADX INFO: renamed from: setLineHeight--R2X_6o$foundation, reason: not valid java name */
    public final void m1449setLineHeightR2X_6o$foundation(long j) {
        this.lineHeight = j;
    }

    /* JADX INFO: renamed from: getLetterSpacing-XSAIIZE$foundation, reason: not valid java name and from getter */
    public final long getLetterSpacing() {
        return this.letterSpacing;
    }

    /* JADX INFO: renamed from: setLetterSpacing--R2X_6o$foundation, reason: not valid java name */
    public final void m1447setLetterSpacingR2X_6o$foundation(long j) {
        this.letterSpacing = j;
    }

    /* JADX INFO: renamed from: getBaselineShift-y9eOQZs$foundation, reason: not valid java name and from getter */
    public final float getBaselineShift() {
        return this.baselineShift;
    }

    /* JADX INFO: renamed from: setBaselineShift-4Dl_Bck$foundation, reason: not valid java name */
    public final void m1442setBaselineShift4Dl_Bck$foundation(float f) {
        this.baselineShift = f;
    }

    /* JADX INFO: renamed from: getLineBreak-rAG3T2k$foundation, reason: not valid java name and from getter */
    public final int getLineBreak() {
        return this.lineBreak;
    }

    /* JADX INFO: renamed from: setLineBreak-CZqVlQI$foundation, reason: not valid java name */
    public final void m1448setLineBreakCZqVlQI$foundation(int i) {
        this.lineBreak = i;
    }

    /* JADX INFO: renamed from: getTextEnums$foundation, reason: from getter */
    public final int getTextEnums() {
        return this.textEnums;
    }

    public final void setTextEnums$foundation(int i) {
        this.textEnums = i;
    }

    public static /* synthetic */ int diff$foundation$default(ResolvedStyle resolvedStyle, ResolvedStyle resolvedStyle2, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = -1;
        }
        return resolvedStyle.diff$foundation(resolvedStyle2, i);
    }

    /* JADX WARN: Removed duplicated region for block: B:158:0x0213  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0132  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int diff$foundation(androidx.compose.foundation.style.ResolvedStyle r10, int r11) {
        /*
            Method dump skipped, instruction units count: 659
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.style.ResolvedStyle.diff$foundation(androidx.compose.foundation.style.ResolvedStyle, int):int");
    }

    public final ResolvedStyle copy$foundation() {
        ResolvedStyle it = new ResolvedStyle();
        copyInto$foundation(it);
        return it;
    }

    public final ResolvedStyle copyInheritedStyles$foundation() {
        ResolvedStyle it = new ResolvedStyle();
        copyInheritedStylesInto$foundation(it);
        return it;
    }

    public final void copyInheritedStylesInto$foundation(ResolvedStyle target) {
        target.contentColor = this.contentColor;
        target.contentBrush = this.contentBrush;
        target.fontFamily = this.fontFamily;
        target.textIndent = this.textIndent;
        target.fontSize = this.fontSize;
        target.lineHeight = this.lineHeight;
        target.letterSpacing = this.letterSpacing;
        target.baselineShift = this.baselineShift;
        target.lineBreak = this.lineBreak;
        target.textEnums = this.textEnums;
    }

    public final void copyInto$foundation(ResolvedStyle target) {
        target.flags = this.flags;
        target.left = this.left;
        target.top = this.top;
        target.right = this.right;
        target.bottom = this.bottom;
        target.minHeight = this.minHeight;
        target.maxHeight = this.maxHeight;
        target.minWidth = this.minWidth;
        target.maxWidth = this.maxWidth;
        target.contentPaddingStart = this.contentPaddingStart;
        target.contentPaddingEnd = this.contentPaddingEnd;
        target.contentPaddingTop = this.contentPaddingTop;
        target.contentPaddingBottom = this.contentPaddingBottom;
        target.externalPaddingStart = this.externalPaddingStart;
        target.externalPaddingEnd = this.externalPaddingEnd;
        target.externalPaddingTop = this.externalPaddingTop;
        target.externalPaddingBottom = this.externalPaddingBottom;
        target.borderWidth = this.borderWidth;
        target.shape = this.shape;
        target.alpha = this.alpha;
        target.scaleX = this.scaleX;
        target.scaleY = this.scaleY;
        target.translationX = this.translationX;
        target.translationY = this.translationY;
        target.rotationX = this.rotationX;
        target.rotationY = this.rotationY;
        target.rotationZ = this.rotationZ;
        target.transformOrigin = this.transformOrigin;
        target.zIndex = this.zIndex;
        target.cameraDistance = this.cameraDistance;
        target.borderColor = this.borderColor;
        target.borderBrush = this.borderBrush;
        target.backgroundColor = this.backgroundColor;
        target.backgroundBrush = this.backgroundBrush;
        target.foregroundBrush = this.foregroundBrush;
        target.dropShadow = this.dropShadow;
        target.innerShadow = this.innerShadow;
        target.clip = this.clip;
        target.width = this.width;
        target.height = this.height;
        target.widthFraction = this.widthFraction;
        target.heightFraction = this.heightFraction;
        copyInheritedStylesInto$foundation(target);
    }

    public final void clear$foundation() {
        ResolvedStyleKt.EmptyResolvedStyle.copyInto$foundation(this);
    }

    public final void resolve$foundation(Style style, StyleOuterNode node, Density density, boolean animating) {
        startResolve$foundation(node, density, animating);
        style.applyStyle(this);
        doneResolve$foundation();
    }

    public static /* synthetic */ void resolveForTesting$foundation$default(ResolvedStyle resolvedStyle, Style style, Density density, boolean z, StyleState styleState, int i, Object obj) {
        if ((i & 8) != 0) {
            styleState = null;
        }
        resolvedStyle.resolveForTesting$foundation(style, density, z, styleState);
    }

    public final void resolveForTesting$foundation(Style style, Density density, boolean animating, StyleState state) {
        this.currentIndex = 0;
        this.compositeHash = 0;
        this.node = new StyleOuterNode(state, style);
        this._density = density.get_density();
        this.animating = animating;
        style.applyStyle(this);
        doneResolve$foundation();
    }

    public final void applyInheritableStyles$foundation(ResolvedStyle source) {
        int sourceTextFlags = source.flags & 96;
        if (sourceTextFlags == 0) {
            return;
        }
        this.flags |= sourceTextFlags;
        long $this$takeOrElse_u2d_u2dOWjLjI$iv = source.contentColor;
        long other$iv = this.contentColor;
        if (!($this$takeOrElse_u2d_u2dOWjLjI$iv != 16)) {
            $this$takeOrElse_u2d_u2dOWjLjI$iv = other$iv;
        }
        this.contentColor = $this$takeOrElse_u2d_u2dOWjLjI$iv;
        Brush brush = source.contentBrush;
        if (brush == null) {
            brush = this.contentBrush;
        }
        this.contentBrush = brush;
        FontFamily fontFamily = source.fontFamily;
        if (fontFamily == null) {
            fontFamily = this.fontFamily;
        }
        this.fontFamily = fontFamily;
        TextIndent textIndent = source.textIndent;
        if (textIndent == null) {
            textIndent = this.textIndent;
        }
        this.textIndent = textIndent;
        long $this$takeOrElse_u2dNB67dxo$iv = source.fontSize;
        long other$iv2 = this.fontSize;
        if ((TextUnit.m8342getRawTypeimpl($this$takeOrElse_u2dNB67dxo$iv) == 0 ? 1 : 0) != 0) {
            $this$takeOrElse_u2dNB67dxo$iv = other$iv2;
        }
        this.fontSize = $this$takeOrElse_u2dNB67dxo$iv;
        long $this$takeOrElse_u2dNB67dxo$iv2 = source.lineHeight;
        long other$iv3 = this.lineHeight;
        if ((TextUnit.m8342getRawTypeimpl($this$takeOrElse_u2dNB67dxo$iv2) == 0 ? 1 : 0) != 0) {
            $this$takeOrElse_u2dNB67dxo$iv2 = other$iv3;
        }
        this.lineHeight = $this$takeOrElse_u2dNB67dxo$iv2;
        long $this$takeOrElse_u2dNB67dxo$iv3 = source.letterSpacing;
        long other$iv4 = this.letterSpacing;
        if (TextUnit.m8342getRawTypeimpl($this$takeOrElse_u2dNB67dxo$iv3) == 0) {
            $this$takeOrElse_u2dNB67dxo$iv3 = other$iv4;
        }
        this.letterSpacing = $this$takeOrElse_u2dNB67dxo$iv3;
        float $this$takeOrElse_u2dy00tBZM$iv = source.baselineShift;
        float other$iv5 = this.baselineShift;
        if (!BaselineShift.m7868equalsimpl0($this$takeOrElse_u2dy00tBZM$iv, BaselineShift.INSTANCE.m7879getUnspecifiedy9eOQZs())) {
            $this$takeOrElse_u2dy00tBZM$iv = other$iv5;
        }
        this.baselineShift = $this$takeOrElse_u2dy00tBZM$iv;
        int $this$takeOrElse_u2dw1xZEK0$iv = source.lineBreak;
        int other$iv6 = this.lineBreak;
        if (LineBreak.m7907equalsimpl0($this$takeOrElse_u2dw1xZEK0$iv, LineBreak.INSTANCE.m7921getUnspecifiedrAG3T2k())) {
            $this$takeOrElse_u2dw1xZEK0$iv = other$iv6;
        }
        this.lineBreak = $this$takeOrElse_u2dw1xZEK0$iv;
        int left$iv = this.textEnums;
        int right$iv = source.textEnums;
        int rightBits$iv$iv = right$iv & 3;
        int mask$iv$iv = ((~3) & left$iv) | (rightBits$iv$iv != 0 ? rightBits$iv$iv : left$iv);
        int rightBits$iv$iv2 = right$iv & 28;
        int mask$iv$iv2 = ((~28) & mask$iv$iv) | (rightBits$iv$iv2 != 0 ? rightBits$iv$iv2 : mask$iv$iv);
        int rightBits$iv$iv3 = right$iv & 112;
        int mask$iv$iv3 = ((~112) & mask$iv$iv2) | (rightBits$iv$iv3 != 0 ? rightBits$iv$iv3 : mask$iv$iv2);
        int rightBits$iv$iv4 = right$iv & ViewUtils.EDGE_TO_EDGE_FLAGS;
        int mask$iv$iv4 = ((~ViewUtils.EDGE_TO_EDGE_FLAGS) & mask$iv$iv3) | (rightBits$iv$iv4 != 0 ? rightBits$iv$iv4 : mask$iv$iv3);
        int rightBits$iv$iv5 = right$iv & 15360;
        int mask$iv$iv5 = ((~15360) & mask$iv$iv4) | (rightBits$iv$iv5 != 0 ? rightBits$iv$iv5 : mask$iv$iv4);
        int rightBits$iv$iv6 = right$iv & 134086656;
        this.textEnums = ((~134086656) & mask$iv$iv5) | (rightBits$iv$iv6 != 0 ? rightBits$iv$iv6 : mask$iv$iv5);
    }

    public final TextStyle toTextStyle$foundation(TextStyle fallback) {
        FontSynthesis fontSynthesisM7606getFontSynthesisZQGJjVo;
        TextDecoration textDecoration;
        int iM7616getTextDirections_7Xco;
        ResolvedStyle resolvedStyle = ResolvedStyleKt.EmptyResolvedStyle;
        long $this$takeOrElse_u2dDxMtmZc$iv = this.contentColor;
        long jM7603getColor0d7_KjU = ($this$takeOrElse_u2dDxMtmZc$iv > 16L ? 1 : ($this$takeOrElse_u2dDxMtmZc$iv == 16L ? 0 : -1)) != 0 ? $this$takeOrElse_u2dDxMtmZc$iv : fallback.m7603getColor0d7_KjU();
        long $this$takeOrElse_u2dDxMtmZc$iv2 = this.fontSize;
        long other$iv = fallback.m7604getFontSizeXSAIIZE();
        long j = !((TextUnit.m8342getRawTypeimpl($this$takeOrElse_u2dDxMtmZc$iv2) > 0L ? 1 : (TextUnit.m8342getRawTypeimpl($this$takeOrElse_u2dDxMtmZc$iv2) == 0L ? 0 : -1)) == 0) ? $this$takeOrElse_u2dDxMtmZc$iv2 : other$iv;
        FontWeight fontWeight$foundation = isFontWeightSpecified$foundation() ? getFontWeight$foundation() : fallback.getFontWeight();
        FontStyle fontStyleM7682boximpl = !FontStyle.m7685equalsimpl0(m1416getFontStyle_LCdwA$foundation(), resolvedStyle.m1416getFontStyle_LCdwA$foundation()) ? FontStyle.m7682boximpl(m1416getFontStyle_LCdwA$foundation()) : fallback.m7605getFontStyle4Lr2A7w();
        if (!FontSynthesis.m7696equalsimpl0(m1417getFontSynthesisGVVA2EU$foundation(), resolvedStyle.m1417getFontSynthesisGVVA2EU$foundation())) {
            fontSynthesisM7606getFontSynthesisZQGJjVo = FontSynthesis.m7693boximpl(m1417getFontSynthesisGVVA2EU$foundation());
        } else {
            fontSynthesisM7606getFontSynthesisZQGJjVo = fallback.m7606getFontSynthesisZQGJjVo();
        }
        FontFamily fontFamily = this.fontFamily;
        if (fontFamily == null) {
            fontFamily = fallback.getFontFamily();
        }
        String fontFeatureSettings = fallback.getFontFeatureSettings();
        long $this$takeOrElse_u2dNB67dxo$iv = this.letterSpacing;
        long other$iv2 = fallback.m7609getLetterSpacingXSAIIZE();
        long j2 = !((TextUnit.m8342getRawTypeimpl($this$takeOrElse_u2dNB67dxo$iv) > 0L ? 1 : (TextUnit.m8342getRawTypeimpl($this$takeOrElse_u2dNB67dxo$iv) == 0L ? 0 : -1)) == 0) ? $this$takeOrElse_u2dNB67dxo$iv : other$iv2;
        float $this$isSpecified$iv = this.baselineShift;
        BaselineShift baselineShiftM7865boximpl = !Float.isNaN($this$isSpecified$iv) ? BaselineShift.m7865boximpl(this.baselineShift) : fallback.m7602getBaselineShift5SSeXJ0();
        TextGeometricTransform textGeometricTransform = fallback.getTextGeometricTransform();
        LocaleList localeList = fallback.getLocaleList();
        long jM7601getBackground0d7_KjU = fallback.m7601getBackground0d7_KjU();
        if (!Intrinsics.areEqual(getTextDecoration$foundation(), resolvedStyle.getTextDecoration$foundation())) {
            textDecoration = getTextDecoration$foundation();
        } else {
            textDecoration = fallback.getTextDecoration();
        }
        Shadow shadow = fallback.getShadow();
        DrawStyle drawStyle = fallback.getDrawStyle();
        int iM1423getTextAligne0LSkKk$foundation = !TextAlign.m7999equalsimpl0(m1423getTextAligne0LSkKk$foundation(), resolvedStyle.m1423getTextAligne0LSkKk$foundation()) ? m1423getTextAligne0LSkKk$foundation() : fallback.m7614getTextAligne0LSkKk();
        if (!TextDirection.m8016equalsimpl0(m1424getTextDirections_7Xco$foundation(), resolvedStyle.m1424getTextDirections_7Xco$foundation())) {
            iM7616getTextDirections_7Xco = m1424getTextDirections_7Xco$foundation();
        } else {
            iM7616getTextDirections_7Xco = fallback.m7616getTextDirections_7Xco();
        }
        long $this$takeOrElse_u2dNB67dxo$iv2 = this.lineHeight;
        long other$iv3 = fallback.m7612getLineHeightXSAIIZE();
        long j3 = !(TextUnit.m8342getRawTypeimpl($this$takeOrElse_u2dNB67dxo$iv2) == 0) ? $this$takeOrElse_u2dNB67dxo$iv2 : other$iv3;
        TextIndent textIndent = this.textIndent;
        if (textIndent == null) {
            textIndent = fallback.getTextIndent();
        }
        TextIndent textIndent2 = textIndent;
        PlatformTextStyle platformStyle = fallback.getPlatformStyle();
        LineHeightStyle lineHeightStyle = fallback.getLineHeightStyle();
        int $this$takeOrElse_u2dw1xZEK0$iv = this.lineBreak;
        int other$iv4 = fallback.m7611getLineBreakrAG3T2k();
        TextStyle it = new TextStyle(jM7603getColor0d7_KjU, j, fontWeight$foundation, fontStyleM7682boximpl, fontSynthesisM7606getFontSynthesisZQGJjVo, fontFamily, fontFeatureSettings, j2, baselineShiftM7865boximpl, textGeometricTransform, localeList, jM7601getBackground0d7_KjU, textDecoration, shadow, drawStyle, iM1423getTextAligne0LSkKk$foundation, iM7616getTextDirections_7Xco, j3, textIndent2, platformStyle, lineHeightStyle, !LineBreak.m7907equalsimpl0($this$takeOrElse_u2dw1xZEK0$iv, LineBreak.INSTANCE.m7921getUnspecifiedrAG3T2k()) ? $this$takeOrElse_u2dw1xZEK0$iv : other$iv4, !Hyphens.m7891equalsimpl0(m1419getHyphensvmbZdU8$foundation(), resolvedStyle.m1419getHyphensvmbZdU8$foundation()) ? m1419getHyphensvmbZdU8$foundation() : fallback.m7608getHyphensvmbZdU8(), fallback.getTextMotion(), (DefaultConstructorMarker) null);
        return this.contentBrush != null ? TextStyle.m7584copyNs73l9s$default(it, this.contentBrush, 0.0f, 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 33554430, null) : it;
    }

    private final List<ValueElement> valueElements() {
        List $this$valueElements_u24lambda_u240 = new ArrayList();
        ResolvedStyle resolvedStyle = ResolvedStyleKt.EmptyResolvedStyle;
        if (!(resolvedStyle.contentPaddingStart == this.contentPaddingStart)) {
            valueElements$lambda$0$add($this$valueElements_u24lambda_u240, "contentPaddingStart", Float.valueOf(this.contentPaddingStart));
        }
        if (!(resolvedStyle.contentPaddingEnd == this.contentPaddingEnd)) {
            valueElements$lambda$0$add($this$valueElements_u24lambda_u240, "contentPaddingEnd", Float.valueOf(this.contentPaddingEnd));
        }
        if (!(resolvedStyle.contentPaddingTop == this.contentPaddingTop)) {
            valueElements$lambda$0$add($this$valueElements_u24lambda_u240, "contentPaddingTop", Float.valueOf(this.contentPaddingTop));
        }
        if (!(resolvedStyle.contentPaddingBottom == this.contentPaddingBottom)) {
            valueElements$lambda$0$add($this$valueElements_u24lambda_u240, "contentPaddingBottom", Float.valueOf(this.contentPaddingBottom));
        }
        if (!(resolvedStyle.externalPaddingStart == this.externalPaddingStart)) {
            valueElements$lambda$0$add($this$valueElements_u24lambda_u240, "externalPaddingStart", Float.valueOf(this.externalPaddingStart));
        }
        if (!(resolvedStyle.externalPaddingEnd == this.externalPaddingEnd)) {
            valueElements$lambda$0$add($this$valueElements_u24lambda_u240, "externalPaddingEnd", Float.valueOf(this.externalPaddingEnd));
        }
        if (!(resolvedStyle.externalPaddingTop == this.externalPaddingTop)) {
            valueElements$lambda$0$add($this$valueElements_u24lambda_u240, "externalPaddingTop", Float.valueOf(this.externalPaddingTop));
        }
        if (!(resolvedStyle.externalPaddingBottom == this.externalPaddingBottom)) {
            valueElements$lambda$0$add($this$valueElements_u24lambda_u240, "externalPaddingBottom", Float.valueOf(this.externalPaddingBottom));
        }
        if (!(resolvedStyle.borderWidth == this.borderWidth)) {
            valueElements$lambda$0$add($this$valueElements_u24lambda_u240, "borderWidth", Float.valueOf(this.borderWidth));
        }
        if (!(resolvedStyle.width == this.width)) {
            valueElements$lambda$0$add($this$valueElements_u24lambda_u240, "width", Float.valueOf(this.width));
        }
        if (!(resolvedStyle.height == this.height)) {
            valueElements$lambda$0$add($this$valueElements_u24lambda_u240, "height", Float.valueOf(this.height));
        }
        if (Float.floatToRawIntBits(resolvedStyle.widthFraction) != Float.floatToRawIntBits(this.widthFraction)) {
            valueElements$lambda$0$add($this$valueElements_u24lambda_u240, "widthFraction", Float.valueOf(this.widthFraction));
        }
        if (Float.floatToRawIntBits(resolvedStyle.heightFraction) != Float.floatToRawIntBits(this.heightFraction)) {
            valueElements$lambda$0$add($this$valueElements_u24lambda_u240, "heightFraction", Float.valueOf(this.heightFraction));
        }
        if (!(resolvedStyle.alpha == this.alpha)) {
            valueElements$lambda$0$add($this$valueElements_u24lambda_u240, "alpha", Float.valueOf(this.alpha));
        }
        if (!(resolvedStyle.scaleX == this.scaleX)) {
            valueElements$lambda$0$add($this$valueElements_u24lambda_u240, "scaleX", Float.valueOf(this.scaleX));
        }
        if (!(resolvedStyle.scaleY == this.scaleY)) {
            valueElements$lambda$0$add($this$valueElements_u24lambda_u240, "scaleY", Float.valueOf(this.scaleY));
        }
        if (!(resolvedStyle.translationX == this.translationX)) {
            valueElements$lambda$0$add($this$valueElements_u24lambda_u240, "translationX", Float.valueOf(this.translationX));
        }
        if (!(resolvedStyle.translationY == this.translationY)) {
            valueElements$lambda$0$add($this$valueElements_u24lambda_u240, "translationY", Float.valueOf(this.translationY));
        }
        if (!(resolvedStyle.rotationX == this.rotationX)) {
            valueElements$lambda$0$add($this$valueElements_u24lambda_u240, "rotationX", Float.valueOf(this.rotationX));
        }
        if (!(resolvedStyle.rotationY == this.rotationY)) {
            valueElements$lambda$0$add($this$valueElements_u24lambda_u240, "rotationY", Float.valueOf(this.rotationY));
        }
        if (!(resolvedStyle.rotationZ == this.rotationZ)) {
            valueElements$lambda$0$add($this$valueElements_u24lambda_u240, "rotationZ", Float.valueOf(this.rotationZ));
        }
        if (!TransformOrigin.m5720equalsimpl0(resolvedStyle.transformOrigin, this.transformOrigin)) {
            valueElements$lambda$0$add($this$valueElements_u24lambda_u240, "transformOrigin", TransformOrigin.m5713boximpl(this.transformOrigin));
        }
        if (!(resolvedStyle.zIndex == this.zIndex)) {
            valueElements$lambda$0$add($this$valueElements_u24lambda_u240, "zIndex", Float.valueOf(this.zIndex));
        }
        if (!(resolvedStyle.cameraDistance == this.cameraDistance)) {
            valueElements$lambda$0$add($this$valueElements_u24lambda_u240, "cameraDistance", Float.valueOf(this.cameraDistance));
        }
        if (!Color.m5314equalsimpl0(resolvedStyle.borderColor, this.borderColor)) {
            valueElements$lambda$0$add($this$valueElements_u24lambda_u240, "borderColor", Color.m5303boximpl(this.borderColor));
        }
        if (!Intrinsics.areEqual(resolvedStyle.borderBrush, this.borderBrush)) {
            valueElements$lambda$0$add($this$valueElements_u24lambda_u240, "borderBrush", this.borderBrush);
        }
        if (!Color.m5314equalsimpl0(resolvedStyle.backgroundColor, this.backgroundColor)) {
            valueElements$lambda$0$add($this$valueElements_u24lambda_u240, "backgroundColor", Color.m5303boximpl(this.backgroundColor));
        }
        if (!Intrinsics.areEqual(resolvedStyle.backgroundBrush, this.backgroundBrush)) {
            valueElements$lambda$0$add($this$valueElements_u24lambda_u240, "backgroundBrush", this.backgroundBrush);
        }
        if (!Intrinsics.areEqual(resolvedStyle.foregroundBrush, this.foregroundBrush)) {
            valueElements$lambda$0$add($this$valueElements_u24lambda_u240, "foregroundBrush", this.foregroundBrush);
        }
        if (resolvedStyle.clip != this.clip) {
            valueElements$lambda$0$add($this$valueElements_u24lambda_u240, "clip", Boolean.valueOf(this.clip));
        }
        if (!Intrinsics.areEqual(resolvedStyle.shape, this.shape)) {
            valueElements$lambda$0$add($this$valueElements_u24lambda_u240, "shape", this.shape);
        }
        long $this$isSpecified$iv = resolvedStyle.contentColor;
        if ($this$isSpecified$iv != 16) {
            valueElements$lambda$0$add($this$valueElements_u24lambda_u240, "contentColor", Color.m5303boximpl(this.contentColor));
        }
        if (!Intrinsics.areEqual(resolvedStyle.contentBrush, this.backgroundBrush)) {
            valueElements$lambda$0$add($this$valueElements_u24lambda_u240, "contentBrush", this.contentBrush);
        }
        if (!Intrinsics.areEqual(resolvedStyle.fontFamily, this.fontFamily)) {
            valueElements$lambda$0$add($this$valueElements_u24lambda_u240, "fontFamily", this.fontFamily);
        }
        if (!Intrinsics.areEqual(resolvedStyle.textIndent, this.textIndent)) {
            valueElements$lambda$0$add($this$valueElements_u24lambda_u240, "textIndent", this.textIndent);
        }
        if (!TextUnit.m8341equalsimpl0(resolvedStyle.fontSize, this.fontSize)) {
            valueElements$lambda$0$add($this$valueElements_u24lambda_u240, "fontSize", TextUnit.m8334boximpl(this.fontSize));
        }
        if (!TextUnit.m8341equalsimpl0(resolvedStyle.lineHeight, this.lineHeight)) {
            valueElements$lambda$0$add($this$valueElements_u24lambda_u240, "lineHeight", TextUnit.m8334boximpl(this.lineHeight));
        }
        if (!TextUnit.m8341equalsimpl0(resolvedStyle.letterSpacing, this.letterSpacing)) {
            valueElements$lambda$0$add($this$valueElements_u24lambda_u240, "letterSpacing", TextUnit.m8334boximpl(this.letterSpacing));
        }
        if (!BaselineShift.m7868equalsimpl0(resolvedStyle.baselineShift, this.baselineShift)) {
            valueElements$lambda$0$add($this$valueElements_u24lambda_u240, "baselineShift", BaselineShift.m7865boximpl(this.baselineShift));
        }
        if (!LineBreak.m7907equalsimpl0(resolvedStyle.lineBreak, this.lineBreak)) {
            valueElements$lambda$0$add($this$valueElements_u24lambda_u240, "lineBreak", LineBreak.m7901boximpl(this.lineBreak));
        }
        if (!TextAlign.m7999equalsimpl0(resolvedStyle.m1423getTextAligne0LSkKk$foundation(), m1423getTextAligne0LSkKk$foundation())) {
            valueElements$lambda$0$add($this$valueElements_u24lambda_u240, "textAlign", TextAlign.m7996boximpl(m1423getTextAligne0LSkKk$foundation()));
        }
        if (!TextDirection.m8016equalsimpl0(resolvedStyle.m1424getTextDirections_7Xco$foundation(), m1424getTextDirections_7Xco$foundation())) {
            valueElements$lambda$0$add($this$valueElements_u24lambda_u240, "textDirection", TextDirection.m8013boximpl(m1424getTextDirections_7Xco$foundation()));
        }
        if (!Hyphens.m7891equalsimpl0(resolvedStyle.m1419getHyphensvmbZdU8$foundation(), m1419getHyphensvmbZdU8$foundation())) {
            valueElements$lambda$0$add($this$valueElements_u24lambda_u240, "hyphens", Hyphens.m7888boximpl(m1419getHyphensvmbZdU8$foundation()));
        }
        if (!FontStyle.m7685equalsimpl0(resolvedStyle.m1416getFontStyle_LCdwA$foundation(), m1416getFontStyle_LCdwA$foundation())) {
            valueElements$lambda$0$add($this$valueElements_u24lambda_u240, "fontStyle", FontStyle.m7682boximpl(m1416getFontStyle_LCdwA$foundation()));
        }
        if (!Intrinsics.areEqual(resolvedStyle.getFontWeight$foundation(), getFontWeight$foundation())) {
            valueElements$lambda$0$add($this$valueElements_u24lambda_u240, "fontWeight", getFontWeight$foundation());
        }
        if (!FontSynthesis.m7696equalsimpl0(resolvedStyle.m1417getFontSynthesisGVVA2EU$foundation(), m1417getFontSynthesisGVVA2EU$foundation())) {
            valueElements$lambda$0$add($this$valueElements_u24lambda_u240, "fontSynthesis", FontSynthesis.m7693boximpl(m1417getFontSynthesisGVVA2EU$foundation()));
        }
        if (!Intrinsics.areEqual(resolvedStyle.getTextDecoration$foundation(), getTextDecoration$foundation())) {
            valueElements$lambda$0$add($this$valueElements_u24lambda_u240, "textDecoration", getTextDecoration$foundation());
        }
        return $this$valueElements_u24lambda_u240;
    }

    private static final boolean valueElements$lambda$0$add(List<ValueElement> list, String name, Object value) {
        return list.add(new ValueElement(name, value));
    }

    @Override // androidx.compose.ui.platform.InspectableValue
    public Sequence<ValueElement> getInspectableElements() {
        return CollectionsKt.asSequence(valueElements());
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: getDensity, reason: from getter */
    public float get_density() {
        return this._density;
    }

    @Override // androidx.compose.ui.unit.FontScaling
    /* JADX INFO: renamed from: getFontScale, reason: from getter */
    public float get_fontScale() {
        return this._fontScale;
    }

    @Override // androidx.compose.foundation.style.StyleScope
    public StyleState getState() {
        StyleOuterNode styleOuterNode = this.node;
        Intrinsics.checkNotNull(styleOuterNode);
        return styleOuterNode.get_state();
    }

    @Override // androidx.compose.foundation.style.StyleScope
    /* JADX INFO: renamed from: contentPaddingStart-0680j_4, reason: not valid java name */
    public void mo1395contentPaddingStart0680j_4(float value) {
        this.flags |= 1;
        this.contentPaddingStart = mo426roundToPx0680j_4(value);
    }

    @Override // androidx.compose.foundation.style.StyleScope
    /* JADX INFO: renamed from: contentPaddingEnd-0680j_4, reason: not valid java name */
    public void mo1393contentPaddingEnd0680j_4(float value) {
        this.flags |= 1;
        this.contentPaddingEnd = mo426roundToPx0680j_4(value);
    }

    @Override // androidx.compose.foundation.style.StyleScope
    /* JADX INFO: renamed from: contentPaddingTop-0680j_4, reason: not valid java name */
    public void mo1396contentPaddingTop0680j_4(float value) {
        this.flags |= 1;
        this.contentPaddingTop = mo426roundToPx0680j_4(value);
    }

    @Override // androidx.compose.foundation.style.StyleScope
    /* JADX INFO: renamed from: contentPaddingBottom-0680j_4, reason: not valid java name */
    public void mo1392contentPaddingBottom0680j_4(float value) {
        this.flags |= 1;
        this.contentPaddingBottom = mo426roundToPx0680j_4(value);
    }

    @Override // androidx.compose.foundation.style.StyleScope
    /* JADX INFO: renamed from: contentPaddingHorizontal-0680j_4, reason: not valid java name */
    public void mo1394contentPaddingHorizontal0680j_4(float value) {
        this.flags |= 1;
        float value2 = mo426roundToPx0680j_4(value);
        this.contentPaddingStart = value2;
        this.contentPaddingEnd = value2;
    }

    @Override // androidx.compose.foundation.style.StyleScope
    /* JADX INFO: renamed from: contentPaddingVertical-0680j_4, reason: not valid java name */
    public void mo1397contentPaddingVertical0680j_4(float value) {
        this.flags |= 1;
        float value2 = mo426roundToPx0680j_4(value);
        this.contentPaddingTop = value2;
        this.contentPaddingBottom = value2;
    }

    @Override // androidx.compose.foundation.style.StyleScope
    /* JADX INFO: renamed from: contentPadding-0680j_4, reason: not valid java name */
    public void mo1389contentPadding0680j_4(float value) {
        this.flags |= 1;
        float value2 = mo426roundToPx0680j_4(value);
        this.contentPaddingStart = value2;
        this.contentPaddingEnd = value2;
        this.contentPaddingTop = value2;
        this.contentPaddingBottom = value2;
    }

    @Override // androidx.compose.foundation.style.StyleScope
    /* JADX INFO: renamed from: contentPadding-a9UjIt4, reason: not valid java name */
    public void mo1391contentPaddinga9UjIt4(float start, float top, float end, float bottom) {
        this.flags |= 1;
        this.contentPaddingTop = mo426roundToPx0680j_4(top);
        this.contentPaddingEnd = mo426roundToPx0680j_4(end);
        this.contentPaddingBottom = mo426roundToPx0680j_4(bottom);
        this.contentPaddingStart = mo426roundToPx0680j_4(start);
    }

    @Override // androidx.compose.foundation.style.StyleScope
    /* JADX INFO: renamed from: contentPadding-YgX7TsA, reason: not valid java name */
    public void mo1390contentPaddingYgX7TsA(float horizontal, float vertical) {
        this.flags |= 1;
        float vertical2 = mo426roundToPx0680j_4(vertical);
        this.contentPaddingTop = vertical2;
        this.contentPaddingBottom = vertical2;
        float horizontal2 = mo426roundToPx0680j_4(horizontal);
        this.contentPaddingEnd = horizontal2;
        this.contentPaddingStart = horizontal2;
    }

    @Override // androidx.compose.foundation.style.StyleScope
    /* JADX INFO: renamed from: externalPaddingStart-0680j_4, reason: not valid java name */
    public void mo1404externalPaddingStart0680j_4(float value) {
        this.flags |= 8;
        this.externalPaddingStart = mo426roundToPx0680j_4(value);
    }

    @Override // androidx.compose.foundation.style.StyleScope
    /* JADX INFO: renamed from: externalPaddingEnd-0680j_4, reason: not valid java name */
    public void mo1402externalPaddingEnd0680j_4(float value) {
        this.flags |= 8;
        this.externalPaddingEnd = mo426roundToPx0680j_4(value);
    }

    @Override // androidx.compose.foundation.style.StyleScope
    /* JADX INFO: renamed from: externalPaddingTop-0680j_4, reason: not valid java name */
    public void mo1405externalPaddingTop0680j_4(float value) {
        this.flags |= 8;
        this.externalPaddingTop = mo426roundToPx0680j_4(value);
    }

    @Override // androidx.compose.foundation.style.StyleScope
    /* JADX INFO: renamed from: externalPaddingBottom-0680j_4, reason: not valid java name */
    public void mo1401externalPaddingBottom0680j_4(float value) {
        this.flags |= 8;
        this.externalPaddingBottom = mo426roundToPx0680j_4(value);
    }

    @Override // androidx.compose.foundation.style.StyleScope
    /* JADX INFO: renamed from: externalPaddingHorizontal-0680j_4, reason: not valid java name */
    public void mo1403externalPaddingHorizontal0680j_4(float value) {
        this.flags |= 8;
        float value2 = mo426roundToPx0680j_4(value);
        this.externalPaddingStart = value2;
        this.externalPaddingEnd = value2;
    }

    @Override // androidx.compose.foundation.style.StyleScope
    /* JADX INFO: renamed from: externalPaddingVertical-0680j_4, reason: not valid java name */
    public void mo1406externalPaddingVertical0680j_4(float value) {
        this.flags |= 8;
        float value2 = mo426roundToPx0680j_4(value);
        this.externalPaddingTop = value2;
        this.externalPaddingBottom = value2;
    }

    @Override // androidx.compose.foundation.style.StyleScope
    /* JADX INFO: renamed from: externalPadding-0680j_4, reason: not valid java name */
    public void mo1398externalPadding0680j_4(float value) {
        this.flags |= 8;
        float value2 = mo426roundToPx0680j_4(value);
        this.externalPaddingStart = value2;
        this.externalPaddingEnd = value2;
        this.externalPaddingTop = value2;
        this.externalPaddingBottom = value2;
    }

    @Override // androidx.compose.foundation.style.StyleScope
    /* JADX INFO: renamed from: externalPadding-a9UjIt4, reason: not valid java name */
    public void mo1400externalPaddinga9UjIt4(float start, float top, float end, float bottom) {
        this.flags |= 8;
        this.externalPaddingTop = mo426roundToPx0680j_4(top);
        this.externalPaddingEnd = mo426roundToPx0680j_4(end);
        this.externalPaddingBottom = mo426roundToPx0680j_4(bottom);
        this.externalPaddingStart = mo426roundToPx0680j_4(start);
    }

    @Override // androidx.compose.foundation.style.StyleScope
    /* JADX INFO: renamed from: externalPadding-YgX7TsA, reason: not valid java name */
    public void mo1399externalPaddingYgX7TsA(float horizontal, float vertical) {
        this.flags |= 8;
        float vertical2 = mo426roundToPx0680j_4(vertical);
        this.externalPaddingTop = vertical2;
        this.externalPaddingBottom = vertical2;
        float horizontal2 = mo426roundToPx0680j_4(horizontal);
        this.externalPaddingEnd = horizontal2;
        this.externalPaddingStart = horizontal2;
    }

    @Override // androidx.compose.foundation.style.StyleScope
    /* JADX INFO: renamed from: borderWidth-0680j_4, reason: not valid java name */
    public void mo1386borderWidth0680j_4(float value) {
        float width;
        this.flags = this.flags | 2 | 1;
        if (Dp.m8155equalsimpl0(value, Dp.INSTANCE.m8170getUnspecifiedD9Ej5fM())) {
            width = 0.0f;
        } else {
            width = Dp.m8155equalsimpl0(value, Dp.INSTANCE.m8168getHairlineD9Ej5fM()) ? 1.0f : (float) Math.ceil(this._density * value);
        }
        this.borderWidth = width;
    }

    @Override // androidx.compose.foundation.style.StyleScope
    /* JADX INFO: renamed from: borderColor-8_81llA, reason: not valid java name */
    public void mo1385borderColor8_81llA(long value) {
        this.flags |= 2;
        this.borderColor = value;
        this.borderBrush = null;
    }

    @Override // androidx.compose.foundation.style.StyleScope
    public void borderBrush(Brush value) {
        this.flags |= 2;
        this.borderBrush = value;
        this.borderColor = Color.INSTANCE.m5349getUnspecified0d7_KjU();
    }

    @Override // androidx.compose.foundation.style.StyleScope
    /* JADX INFO: renamed from: border-cXLIe8U, reason: not valid java name */
    public void mo1384bordercXLIe8U(float width, long color) {
        mo1386borderWidth0680j_4(width);
        mo1385borderColor8_81llA(color);
    }

    @Override // androidx.compose.foundation.style.StyleScope
    /* JADX INFO: renamed from: border-D5KLDUw, reason: not valid java name */
    public void mo1383borderD5KLDUw(float width, Brush brush) {
        mo1386borderWidth0680j_4(width);
        borderBrush(brush);
    }

    @Override // androidx.compose.foundation.style.StyleScope
    /* JADX INFO: renamed from: width-0680j_4, reason: not valid java name */
    public void mo1459width0680j_4(float value) {
        this.flags |= 8;
        this.width = this._density * value;
        this.widthFraction = Float.NaN;
    }

    @Override // androidx.compose.foundation.style.StyleScope
    /* JADX INFO: renamed from: height-0680j_4, reason: not valid java name */
    public void mo1426height0680j_4(float value) {
        this.flags |= 8;
        this.height = this._density * value;
        this.heightFraction = Float.NaN;
    }

    @Override // androidx.compose.foundation.style.StyleScope
    /* JADX INFO: renamed from: size-YgX7TsA, reason: not valid java name */
    public void mo1453sizeYgX7TsA(float width, float height) {
        this.flags |= 8;
        this.width = this._density * width;
        this.widthFraction = Float.NaN;
        this.height = this._density * height;
        this.heightFraction = Float.NaN;
    }

    @Override // androidx.compose.foundation.style.StyleScope
    /* JADX INFO: renamed from: size-0680j_4, reason: not valid java name */
    public void mo1451size0680j_4(float value) {
        this.flags |= 8;
        float size = this._density * value;
        this.width = size;
        this.widthFraction = Float.NaN;
        this.height = size;
        this.heightFraction = Float.NaN;
    }

    @Override // androidx.compose.foundation.style.StyleScope
    /* JADX INFO: renamed from: size-EaSLcWc, reason: not valid java name */
    public void mo1452sizeEaSLcWc(long value) {
        this.flags |= 8;
        this.width = DpSize.m8248getWidthD9Ej5fM(value) * this._density;
        this.widthFraction = Float.NaN;
        this.height = DpSize.m8246getHeightD9Ej5fM(value) * this._density;
        this.heightFraction = Float.NaN;
    }

    @Override // androidx.compose.foundation.style.StyleScope
    public void width(float fraction) {
        this.flags |= 8;
        this.widthFraction = fraction;
        this.width = Float.NaN;
    }

    @Override // androidx.compose.foundation.style.StyleScope
    public void height(float fraction) {
        this.flags |= 8;
        this.heightFraction = fraction;
        this.height = Float.NaN;
    }

    @Override // androidx.compose.foundation.style.StyleScope
    /* JADX INFO: renamed from: left-0680j_4, reason: not valid java name */
    public void mo1428left0680j_4(float value) {
        this.flags |= 8;
        this.left = this._density * value;
    }

    @Override // androidx.compose.foundation.style.StyleScope
    /* JADX INFO: renamed from: top-0680j_4, reason: not valid java name */
    public void mo1456top0680j_4(float value) {
        this.flags |= 8;
        this.top = this._density * value;
    }

    @Override // androidx.compose.foundation.style.StyleScope
    /* JADX INFO: renamed from: right-0680j_4, reason: not valid java name */
    public void mo1440right0680j_4(float value) {
        this.flags |= 8;
        this.right = this._density * value;
    }

    @Override // androidx.compose.foundation.style.StyleScope
    /* JADX INFO: renamed from: bottom-0680j_4, reason: not valid java name */
    public void mo1387bottom0680j_4(float value) {
        this.flags |= 8;
        this.bottom = this._density * value;
    }

    @Override // androidx.compose.foundation.style.StyleScope
    /* JADX INFO: renamed from: minWidth-0680j_4, reason: not valid java name */
    public void mo1439minWidth0680j_4(float value) {
        this.flags |= 8;
        this.minWidth = this._density * value;
    }

    @Override // androidx.compose.foundation.style.StyleScope
    /* JADX INFO: renamed from: minHeight-0680j_4, reason: not valid java name */
    public void mo1436minHeight0680j_4(float value) {
        this.flags |= 8;
        this.minHeight = this._density * value;
    }

    @Override // androidx.compose.foundation.style.StyleScope
    /* JADX INFO: renamed from: minSize-EaSLcWc, reason: not valid java name */
    public void mo1437minSizeEaSLcWc(long size) {
        mo1439minWidth0680j_4(DpSize.m8248getWidthD9Ej5fM(size));
        mo1436minHeight0680j_4(DpSize.m8246getHeightD9Ej5fM(size));
    }

    @Override // androidx.compose.foundation.style.StyleScope
    /* JADX INFO: renamed from: minSize-YgX7TsA, reason: not valid java name */
    public void mo1438minSizeYgX7TsA(float width, float height) {
        mo1439minWidth0680j_4(width);
        mo1436minHeight0680j_4(height);
    }

    @Override // androidx.compose.foundation.style.StyleScope
    /* JADX INFO: renamed from: maxWidth-0680j_4, reason: not valid java name */
    public void mo1435maxWidth0680j_4(float value) {
        this.flags |= 8;
        this.maxWidth = this._density * value;
    }

    @Override // androidx.compose.foundation.style.StyleScope
    /* JADX INFO: renamed from: maxHeight-0680j_4, reason: not valid java name */
    public void mo1432maxHeight0680j_4(float value) {
        this.flags |= 8;
        this.maxHeight = this._density * value;
    }

    @Override // androidx.compose.foundation.style.StyleScope
    /* JADX INFO: renamed from: maxSize-EaSLcWc, reason: not valid java name */
    public void mo1433maxSizeEaSLcWc(long size) {
        mo1435maxWidth0680j_4(DpSize.m8248getWidthD9Ej5fM(size));
        mo1432maxHeight0680j_4(DpSize.m8246getHeightD9Ej5fM(size));
    }

    @Override // androidx.compose.foundation.style.StyleScope
    /* JADX INFO: renamed from: maxSize-YgX7TsA, reason: not valid java name */
    public void mo1434maxSizeYgX7TsA(float width, float height) {
        mo1435maxWidth0680j_4(width);
        mo1432maxHeight0680j_4(height);
    }

    @Override // androidx.compose.foundation.style.StyleScope
    public void alpha(float value) {
        this.flags |= 4;
        this.alpha = value;
    }

    @Override // androidx.compose.foundation.style.StyleScope
    public void scaleX(float value) {
        this.flags |= 4;
        this.scaleX = value;
    }

    @Override // androidx.compose.foundation.style.StyleScope
    public void scaleY(float value) {
        this.flags |= 4;
        this.scaleY = value;
    }

    @Override // androidx.compose.foundation.style.StyleScope
    public void scale(float value) {
        this.flags |= 4;
        this.scaleX = value;
        this.scaleY = value;
    }

    @Override // androidx.compose.foundation.style.StyleScope
    public void translationX(float value) {
        this.flags |= 4;
        this.translationX = value;
    }

    @Override // androidx.compose.foundation.style.StyleScope
    public void translationY(float value) {
        this.flags |= 4;
        this.translationY = value;
    }

    @Override // androidx.compose.foundation.style.StyleScope
    public void translation(float x, float y) {
        this.flags |= 4;
        this.translationX = x;
        this.translationY = y;
    }

    @Override // androidx.compose.foundation.style.StyleScope
    /* JADX INFO: renamed from: translation-k-4lQ0M, reason: not valid java name */
    public void mo1458translationk4lQ0M(long offset) {
        this.flags |= 4;
        int bits$iv$iv$iv = (int) (offset >> 32);
        this.translationX = Float.intBitsToFloat(bits$iv$iv$iv);
        int bits$iv$iv$iv2 = (int) (4294967295L & offset);
        this.translationY = Float.intBitsToFloat(bits$iv$iv$iv2);
    }

    @Override // androidx.compose.foundation.style.StyleScope
    public void rotationX(float value) {
        this.flags |= 4;
        this.rotationX = value;
    }

    @Override // androidx.compose.foundation.style.StyleScope
    public void rotationY(float value) {
        this.flags |= 4;
        this.rotationY = value;
    }

    @Override // androidx.compose.foundation.style.StyleScope
    public void rotationZ(float value) {
        this.flags |= 4;
        this.rotationZ = value;
    }

    @Override // androidx.compose.foundation.style.StyleScope
    /* JADX INFO: renamed from: transformOrigin-__ExYCQ, reason: not valid java name */
    public void mo1457transformOrigin__ExYCQ(long value) {
        this.flags |= 4;
        this.transformOrigin = value;
    }

    @Override // androidx.compose.foundation.style.StyleScope
    public void clip(boolean value) {
        this.flags |= 4;
        this.clip = value;
    }

    @Override // androidx.compose.foundation.style.StyleScope
    public void zIndex(float value) {
        this.zIndex = value;
    }

    @Override // androidx.compose.foundation.style.StyleScope
    /* JADX INFO: renamed from: background-8_81llA, reason: not valid java name */
    public void mo1381background8_81llA(long color) {
        this.flags |= 2;
        this.backgroundColor = color;
        this.backgroundBrush = null;
    }

    @Override // androidx.compose.foundation.style.StyleScope
    public void background(Brush value) {
        this.flags |= 2;
        this.backgroundBrush = value;
        this.backgroundColor = Color.INSTANCE.m5349getUnspecified0d7_KjU();
    }

    @Override // androidx.compose.foundation.style.StyleScope
    /* JADX INFO: renamed from: foreground-8_81llA, reason: not valid java name */
    public void mo1410foreground8_81llA(long value) {
        this.flags |= 2;
        this.foregroundColor = value;
        this.foregroundBrush = null;
    }

    @Override // androidx.compose.foundation.style.StyleScope
    public void foreground(Brush value) {
        this.flags |= 2;
        this.foregroundBrush = value;
        this.foregroundColor = Color.INSTANCE.m5349getUnspecified0d7_KjU();
    }

    @Override // androidx.compose.foundation.style.StyleScope
    public void shape(Shape value) {
        this.flags = this.flags | 2 | 4;
        this.shape = value;
    }

    @Override // androidx.compose.foundation.style.StyleScope
    public void animate(Style value) {
        animate(ResolvedStyleKt.DefaultSpringSpec, ResolvedStyleKt.DefaultSpringSpec, value);
    }

    @Override // androidx.compose.foundation.style.StyleScope
    public void animate(AnimationSpec<Float> spec, Style value) {
        animate(spec, spec, value);
    }

    @Override // androidx.compose.foundation.style.StyleScope
    public void animate(AnimationSpec<Float> toSpec, AnimationSpec<Float> fromSpec, Style value) {
        this.flags |= 16;
        int index$iv = this.currentIndex;
        int effectiveKey$iv = 1318433304 ^ index$iv;
        this.compositeHash = ResolvedStyleKt.updateHashEnter(this.compositeHash, effectiveKey$iv);
        MutableIntList stack$iv = pushIndex(index$iv);
        this.currentIndex = 0;
        if (this.animating) {
            StyleScopeKt.apply(this, value);
        } else {
            StyleOuterNode node = this.node;
            Intrinsics.checkNotNull(node);
            StyleAnimations it = node.getAnimations();
            if (it == null) {
                it = new StyleAnimations(node);
                node.setAnimations$foundation(it);
            }
            it.record(this.compositeHash ^ this.currentIndex, value, toSpec, fromSpec);
        }
        MutableIntList this_$iv$iv$iv = stack$iv;
        this.currentIndex = stack$iv.removeAt(this_$iv$iv$iv._size - 1) + 1;
        this.compositeHash = ResolvedStyleKt.updateHashExit(this.compositeHash, effectiveKey$iv);
    }

    @Override // androidx.compose.runtime.CompositionLocalAccessorScope
    public <T> T getCurrentValue(CompositionLocal<T> compositionLocal) {
        StyleOuterNode styleOuterNode = this.node;
        Intrinsics.checkNotNull(styleOuterNode);
        return (T) CompositionLocalConsumerModifierNodeKt.currentValueOf(styleOuterNode, compositionLocal);
    }

    /* JADX INFO: renamed from: getDropShadow$foundation, reason: from getter */
    public final Object getDropShadow() {
        return this.dropShadow;
    }

    public final void setDropShadow$foundation(Object obj) {
        this.dropShadow = obj;
    }

    /* JADX INFO: renamed from: getInnerShadow$foundation, reason: from getter */
    public final Object getInnerShadow() {
        return this.innerShadow;
    }

    public final void setInnerShadow$foundation(Object obj) {
        this.innerShadow = obj;
    }

    @Override // androidx.compose.foundation.style.StyleScope
    public void dropShadow(androidx.compose.ui.graphics.shadow.Shadow value) {
        this.dropShadow = value;
        this.flags |= 2;
    }

    @Override // androidx.compose.foundation.style.StyleScope
    public void dropShadow(androidx.compose.ui.graphics.shadow.Shadow... value) {
        this.dropShadow = value;
        this.flags |= 2;
    }

    @Override // androidx.compose.foundation.style.StyleScope
    public void innerShadow(androidx.compose.ui.graphics.shadow.Shadow value) {
        this.innerShadow = value;
        this.flags |= 2;
    }

    @Override // androidx.compose.foundation.style.StyleScope
    public void innerShadow(androidx.compose.ui.graphics.shadow.Shadow... value) {
        this.innerShadow = value;
        this.flags |= 2;
    }

    @Override // androidx.compose.foundation.style.StyleScope
    public void textStyle(TextStyle value) {
        SpanStyle span = value.toSpanStyle();
        long $this$isSpecified$iv = span.m7514getColor0d7_KjU();
        if ($this$isSpecified$iv != 16) {
            mo1388contentColor8_81llA(span.m7514getColor0d7_KjU());
        }
        long $this$isSpecified$iv2 = span.getFontSize();
        if ((TextUnit.m8342getRawTypeimpl($this$isSpecified$iv2) == 0 ? 1 : 0) == 0) {
            mo1407fontSizeR2X_6o(span.getFontSize());
        }
        long $this$isSpecified$iv3 = span.getLetterSpacing();
        if ((TextUnit.m8342getRawTypeimpl($this$isSpecified$iv3) == 0 ? 1 : 0) == 0) {
            mo1429letterSpacingR2X_6o(span.getLetterSpacing());
        }
        Brush it = span.getBrush();
        if (it != null) {
            contentBrush(it);
        }
        FontStyle fontStyle = span.getFontStyle();
        if (fontStyle != null) {
            mo1408fontStylenzbMABs(fontStyle.m7688unboximpl());
        }
        BaselineShift baselineShift = span.getBaselineShift();
        if (baselineShift != null) {
            float it2 = baselineShift.m7871unboximpl();
            if (!Float.isNaN(it2)) {
                mo1382baselineShift4Dl_Bck(it2);
            }
        }
        FontWeight it3 = span.getFontWeight();
        if (it3 != null) {
            fontWeight(it3);
        }
        TextDecoration it4 = span.getTextDecoration();
        if (it4 != null) {
            textDecoration(it4);
        }
        FontSynthesis fontSynthesis = span.getFontSynthesis();
        if (fontSynthesis != null) {
            mo1409fontSynthesis6p3vJLY(fontSynthesis.m7701unboximpl());
        }
        ParagraphStyle p = value.toParagraphStyle();
        TextIndent it5 = p.getTextIndent();
        if (it5 != null) {
            textIndent(it5);
        }
        long $this$isSpecified$iv4 = p.getLineHeight();
        if (!(TextUnit.m8342getRawTypeimpl($this$isSpecified$iv4) == 0)) {
            mo1431lineHeightR2X_6o(p.getLineHeight());
        }
        int $this$isSpecified$iv5 = p.getLineBreak();
        if (!LineBreak.m7907equalsimpl0($this$isSpecified$iv5, LineBreak.INSTANCE.m7921getUnspecifiedrAG3T2k())) {
            mo1430lineBreakCZqVlQI(p.getLineBreak());
        }
        int $this$isSpecified$iv6 = p.getHyphens();
        int $this$isSpecified$iv7 = $this$isSpecified$iv6 != 0 ? 1 : 0;
        if ($this$isSpecified$iv7 != 0) {
            mo1427hyphens3fSNIE(p.getHyphens());
        }
        int $this$isSpecified$iv8 = p.getTextDirection();
        int $this$isSpecified$iv9 = $this$isSpecified$iv8 != 0 ? 1 : 0;
        if ($this$isSpecified$iv9 != 0) {
            mo1455textDirectionHejc4pk(p.getTextDirection());
        }
        int $this$isSpecified$iv10 = p.getTextAlign();
        if ($this$isSpecified$iv10 != 0) {
            mo1454textAlignaXe7zB0(p.getTextAlign());
        }
    }

    /* JADX INFO: renamed from: getFontStyle-_-LCdwA$foundation, reason: not valid java name */
    public final int m1416getFontStyle_LCdwA$foundation() {
        int $this$getBits$iv = this.textEnums;
        return ((($this$getBits$iv & 3) >> 0) & 1) == 1 ? FontStyle.INSTANCE.m7691getItalic_LCdwA() : FontStyle.INSTANCE.m7692getNormal_LCdwA();
    }

    /* JADX INFO: renamed from: getTextAlign-e0LSkKk$foundation, reason: not valid java name */
    public final int m1423getTextAligne0LSkKk$foundation() {
        TextAlign.Companion companion = TextAlign.INSTANCE;
        int $this$getBits$iv = this.textEnums;
        return companion.m8010valueOfIgVj0fw(($this$getBits$iv & 28) >> 2);
    }

    /* JADX INFO: renamed from: getTextDirection-s_7X-co$foundation, reason: not valid java name */
    public final int m1424getTextDirections_7Xco$foundation() {
        TextDirection.Companion companion = TextDirection.INSTANCE;
        int $this$getBits$iv = this.textEnums;
        return companion.m8026valueOfE8nx0Ws(($this$getBits$iv & 112) >> 4);
    }

    /* JADX INFO: renamed from: getHyphens-vmbZdU8$foundation, reason: not valid java name */
    public final int m1419getHyphensvmbZdU8$foundation() {
        Hyphens.Companion companion = Hyphens.INSTANCE;
        int $this$getBits$iv = this.textEnums;
        return companion.m7898valueOfkPa1_AA(($this$getBits$iv & ViewUtils.EDGE_TO_EDGE_FLAGS) >> 8);
    }

    public final FontWeight getFontWeight$foundation() {
        int $this$getBits$iv = this.textEnums;
        return new FontWeight(($this$getBits$iv & 134086656) >> 17);
    }

    public final boolean isFontWeightSpecified$foundation() {
        int $this$getBits$iv = this.textEnums;
        return (($this$getBits$iv & 134086656) >> 17) != 0;
    }

    /* JADX INFO: renamed from: getFontSynthesis-GVVA2EU$foundation, reason: not valid java name */
    public final int m1417getFontSynthesisGVVA2EU$foundation() {
        FontSynthesis.Companion companion = FontSynthesis.INSTANCE;
        int $this$getBits$iv = this.textEnums;
        return companion.m7706valueOf9CiegCU((($this$getBits$iv & 15360) >> 10) & 7);
    }

    public final TextDecoration getTextDecoration$foundation() {
        TextDecoration.Companion companion = TextDecoration.INSTANCE;
        int $this$getBits$iv = this.textEnums;
        return companion.valueOf((($this$getBits$iv & 114688) >> 14) & 3);
    }

    @Override // androidx.compose.foundation.style.StyleScope
    /* JADX INFO: renamed from: contentColor-8_81llA, reason: not valid java name */
    public void mo1388contentColor8_81llA(long value) {
        this.flags |= 64;
        this.contentColor = value;
        this.contentBrush = null;
    }

    @Override // androidx.compose.foundation.style.StyleScope
    public void contentBrush(Brush value) {
        this.flags |= 64;
        this.contentBrush = value;
        this.contentColor = Color.INSTANCE.m5349getUnspecified0d7_KjU();
    }

    @Override // androidx.compose.foundation.style.StyleScope
    public void textDecoration(TextDecoration value) {
        this.flags |= 64;
        int bits = value.getMask() | 4;
        this.textEnums |= bits << 14;
    }

    @Override // androidx.compose.foundation.style.StyleScope
    public void fontFamily(FontFamily value) {
        this.flags |= 32;
        this.fontFamily = value;
    }

    @Override // androidx.compose.foundation.style.StyleScope
    public void textIndent(TextIndent value) {
        this.flags |= 32;
        this.textIndent = value;
    }

    @Override // androidx.compose.foundation.style.StyleScope
    /* JADX INFO: renamed from: fontSize--R2X_6o, reason: not valid java name */
    public void mo1407fontSizeR2X_6o(long value) {
        this.flags |= 32;
        this.fontSize = value;
    }

    @Override // androidx.compose.foundation.style.StyleScope
    /* JADX INFO: renamed from: lineHeight--R2X_6o, reason: not valid java name */
    public void mo1431lineHeightR2X_6o(long value) {
        this.flags |= 32;
        this.lineHeight = value;
    }

    @Override // androidx.compose.foundation.style.StyleScope
    /* JADX INFO: renamed from: letterSpacing--R2X_6o, reason: not valid java name */
    public void mo1429letterSpacingR2X_6o(long value) {
        this.flags |= 32;
        this.letterSpacing = value;
    }

    @Override // androidx.compose.foundation.style.StyleScope
    /* JADX INFO: renamed from: baselineShift-4Dl_Bck, reason: not valid java name */
    public void mo1382baselineShift4Dl_Bck(float value) {
        this.flags |= 32;
        this.baselineShift = value;
    }

    @Override // androidx.compose.foundation.style.StyleScope
    /* JADX INFO: renamed from: lineBreak-CZqVlQI, reason: not valid java name */
    public void mo1430lineBreakCZqVlQI(int value) {
        this.flags |= 32;
        this.lineBreak = value;
    }

    @Override // androidx.compose.foundation.style.StyleScope
    /* JADX INFO: renamed from: fontStyle-nzbMABs, reason: not valid java name */
    public void mo1408fontStylenzbMABs(int value) {
        this.flags |= 32;
        int $this$setBits$iv = this.textEnums;
        int value$iv = value | 2;
        this.textEnums = ((~3) & $this$setBits$iv) | ((value$iv << 0) & 3);
    }

    @Override // androidx.compose.foundation.style.StyleScope
    /* JADX INFO: renamed from: textAlign-aXe7zB0, reason: not valid java name */
    public void mo1454textAlignaXe7zB0(int value) {
        this.flags |= 32;
        int $this$setBits$iv = this.textEnums;
        this.textEnums = ((~28) & $this$setBits$iv) | ((value << 2) & 28);
    }

    @Override // androidx.compose.foundation.style.StyleScope
    /* JADX INFO: renamed from: textDirection-Hejc4pk, reason: not valid java name */
    public void mo1455textDirectionHejc4pk(int value) {
        this.flags |= 32;
        int $this$setBits$iv = this.textEnums;
        this.textEnums = ((~112) & $this$setBits$iv) | ((value << 4) & 112);
    }

    @Override // androidx.compose.foundation.style.StyleScope
    /* JADX INFO: renamed from: hyphens--3fSNIE, reason: not valid java name */
    public void mo1427hyphens3fSNIE(int value) {
        this.flags |= 32;
        int $this$setBits$iv = this.textEnums;
        this.textEnums = ((~ViewUtils.EDGE_TO_EDGE_FLAGS) & $this$setBits$iv) | ((value << 8) & ViewUtils.EDGE_TO_EDGE_FLAGS);
    }

    @Override // androidx.compose.foundation.style.StyleScope
    public void fontWeight(FontWeight value) {
        this.flags |= 32;
        int $this$setBits$iv = this.textEnums;
        int value$iv = value.getWeight();
        this.textEnums = ((~134086656) & $this$setBits$iv) | ((value$iv << 17) & 134086656);
    }

    @Override // androidx.compose.foundation.style.StyleScope
    /* JADX INFO: renamed from: fontSynthesis-6p3vJLY, reason: not valid java name */
    public void mo1409fontSynthesis6p3vJLY(int value) {
        this.flags |= 32;
        int bits = (value & 7) | 8;
        this.textEnums |= bits << 10;
    }

    @Override // androidx.compose.foundation.style.StyleScope
    public <T> void state(StyleStateKey<T> key, Style value, Function2<? super StyleStateKey<T>, ? super StyleState, Boolean> active) {
        group(key.hashCode(), active.invoke(key, getState()).booleanValue(), value);
    }

    public final void startResolve$foundation(StyleOuterNode node, Density density, boolean animating) {
        this.currentIndex = 0;
        this.compositeHash = 0;
        this.node = node;
        this._density = density.get_density();
        this.animating = animating;
    }

    public final void doneResolve$foundation() {
        this.node = null;
        this.animating = false;
    }

    private final MutableIntList pushIndex(int index) {
        MutableIntList mutableIntList = this.indexStack;
        if (mutableIntList == null) {
            ResolvedStyle $this$pushIndex_u24lambda_u240 = this;
            MutableIntList newStack = new MutableIntList(0, 1, null);
            $this$pushIndex_u24lambda_u240.indexStack = newStack;
            mutableIntList = newStack;
        }
        MutableIntList $this$pushIndex_u24lambda_u241 = mutableIntList;
        $this$pushIndex_u24lambda_u241.add(index);
        return mutableIntList;
    }

    private final void group(int key, Function0<Unit> block) {
        int index = this.currentIndex;
        int effectiveKey = key ^ index;
        this.compositeHash = ResolvedStyleKt.updateHashEnter(this.compositeHash, effectiveKey);
        MutableIntList stack = pushIndex(index);
        this.currentIndex = 0;
        block.invoke();
        MutableIntList this_$iv$iv = stack;
        this.currentIndex = stack.removeAt(this_$iv$iv._size - 1) + 1;
        this.compositeHash = ResolvedStyleKt.updateHashExit(this.compositeHash, effectiveKey);
    }

    private final void group(int key, boolean active, Style style) {
        if (!active) {
            this.currentIndex++;
            return;
        }
        int index$iv = this.currentIndex;
        int effectiveKey$iv = key ^ index$iv;
        this.compositeHash = ResolvedStyleKt.updateHashEnter(this.compositeHash, effectiveKey$iv);
        MutableIntList stack$iv = pushIndex(index$iv);
        this.currentIndex = 0;
        style.applyStyle(this);
        MutableIntList this_$iv$iv$iv = stack$iv;
        this.currentIndex = stack$iv.removeAt(this_$iv$iv$iv._size - 1) + 1;
        this.compositeHash = ResolvedStyleKt.updateHashExit(this.compositeHash, effectiveKey$iv);
    }

    private final void skippedGroup() {
        this.currentIndex++;
    }

    private final int getCurrentCompositeHash() {
        return this.compositeHash ^ this.currentIndex;
    }
}
