package androidx.compose.ui.semantics;

import androidx.autofill.HintConstants;
import androidx.compose.ui.autofill.ContentDataType;
import androidx.compose.ui.autofill.ContentType;
import androidx.compose.ui.autofill.FillableData;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.state.ToggleableState;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.input.ImeAction;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.navigation.compose.DialogNavigator;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Function;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.reflect.KProperty;

/* JADX INFO: compiled from: SemanticsProperties.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\u0082\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0010\u001a\u0013\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001H\u0002¢\u0006\u0002\u0010\u0002\u001a\u001d\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0004\"\u0004\b\u0000\u0010\u00012\u0006\u0010\u0005\u001a\u00020\u0006H\u0080\b\u001a=\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0004\"\u0004\b\u0000\u0010\u00012\u0006\u0010\u0005\u001a\u00020\u00062\u001e\b\b\u0010\u0007\u001a\u0018\u0012\u0006\u0012\u0004\u0018\u0001H\u0001\u0012\u0004\u0012\u0002H\u0001\u0012\u0006\u0012\u0004\u0018\u0001H\u00010\bH\u0080\b\u001a-\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\n0\u0004\"\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\u0005\u001a\u00020\u0006H\u0082\b\u001a\n\u0010!\u001a\u00020\"*\u00020\u000f\u001a\n\u0010#\u001a\u00020\"*\u00020\u000f\u001a\n\u0010(\u001a\u00020\"*\u00020\u000f\u001a\f\u0010A\u001a\u00020\"*\u00020\u000fH\u0007\u001a\n\u0010B\u001a\u00020\"*\u00020\u000f\u001a\n\u0010j\u001a\u00020\"*\u00020\u000f\u001a\n\u0010k\u001a\u00020\"*\u00020\u000f\u001a\u000b\u0010À\u0001\u001a\u00020\"*\u00020\u000f\u001a\u0014\u0010Á\u0001\u001a\u00020\"*\u00020\u000f2\u0007\u0010Â\u0001\u001a\u00020\u0006\u001a#\u0010Ã\u0001\u001a\u00020\"*\u00020\u000f2\u0016\u0010Ä\u0001\u001a\u0011\u0012\u0005\u0012\u00030Æ\u0001\u0012\u0005\u0012\u00030Ç\u00010Å\u0001\u001a\u000b\u0010Ó\u0001\u001a\u00020\"*\u00020\u000f\u001a8\u0010Ü\u0001\u001a\u00020\"*\u00020\u000f2\u000b\b\u0002\u0010Ý\u0001\u001a\u0004\u0018\u00010\u00062\u001e\u0010Þ\u0001\u001a\u0019\u0012\f\u0012\n\u0012\u0005\u0012\u00030à\u00010ß\u0001\u0012\u0004\u0012\u00020\f\u0018\u00010Å\u0001\u001a*\u0010á\u0001\u001a\u00020\"*\u00020\u000f2\u000b\b\u0002\u0010Ý\u0001\u001a\u0004\u0018\u00010\u00062\u0010\u0010Þ\u0001\u001a\u000b\u0012\u0004\u0012\u00020\f\u0018\u00010â\u0001\u001a*\u0010ã\u0001\u001a\u00020\"*\u00020\u000f2\u000b\b\u0002\u0010Ý\u0001\u001a\u0004\u0018\u00010\u00062\u0010\u0010Þ\u0001\u001a\u000b\u0012\u0004\u0012\u00020\f\u0018\u00010â\u0001\u001aW\u0010ä\u0001\u001a\u00020\"*\u00020\u000f2\u000b\b\u0002\u0010Ý\u0001\u001a\u0004\u0018\u00010\u00062=\u0010Þ\u0001\u001a8\u0012\u0015\u0012\u00130X¢\u0006\u000e\bå\u0001\u0012\t\b\u0005\u0012\u0005\b\b(æ\u0001\u0012\u0015\u0012\u00130X¢\u0006\u000e\bå\u0001\u0012\t\b\u0005\u0012\u0005\b\b(ç\u0001\u0012\u0004\u0012\u00020\f\u0018\u00010\b\u001aK\u0010è\u0001\u001a\u00020\"*\u00020\u000f28\u0010Þ\u0001\u001a3\b\u0001\u0012\u0016\u0012\u00140é\u0001¢\u0006\u000e\bå\u0001\u0012\t\b\u0005\u0012\u0005\b\b(ê\u0001\u0012\f\u0012\n\u0012\u0005\u0012\u00030é\u00010ë\u0001\u0012\u0007\u0012\u0005\u0018\u00010Æ\u00010\b¢\u0006\u0003\u0010ì\u0001\u001a/\u0010í\u0001\u001a\u00020\"*\u00020\u000f2\u000b\b\u0002\u0010Ý\u0001\u001a\u0004\u0018\u00010\u00062\u0015\u0010Þ\u0001\u001a\u0010\u0012\u0005\u0012\u00030Ç\u0001\u0012\u0004\u0012\u00020\f0Å\u0001\u001a2\u0010î\u0001\u001a\u00020\"*\u00020\u000f2\u000b\b\u0002\u0010Ý\u0001\u001a\u0004\u0018\u00010\u00062\u0016\u0010Þ\u0001\u001a\u0011\u0012\u0004\u0012\u00020u\u0012\u0004\u0012\u00020\f\u0018\u00010Å\u0001H\u0007\u001a0\u0010ï\u0001\u001a\u00020\"*\u00020\u000f2\u000b\b\u0002\u0010Ý\u0001\u001a\u0004\u0018\u00010\u00062\u0016\u0010Þ\u0001\u001a\u0011\u0012\u0004\u0012\u00020Q\u0012\u0004\u0012\u00020\f\u0018\u00010Å\u0001\u001a0\u0010ð\u0001\u001a\u00020\"*\u00020\u000f2\u000b\b\u0002\u0010Ý\u0001\u001a\u0004\u0018\u00010\u00062\u0016\u0010Þ\u0001\u001a\u0011\u0012\u0004\u0012\u00020X\u0012\u0004\u0012\u00020\f\u0018\u00010Å\u0001\u001a/\u0010y\u001a\u00020\"*\u00020\u000f2\u000b\b\u0002\u0010Ý\u0001\u001a\u0004\u0018\u00010\u00062\u0016\u0010Þ\u0001\u001a\u0011\u0012\u0004\u0012\u00020u\u0012\u0004\u0012\u00020\f\u0018\u00010Å\u0001\u001a/\u0010~\u001a\u00020\"*\u00020\u000f2\u000b\b\u0002\u0010Ý\u0001\u001a\u0004\u0018\u00010\u00062\u0016\u0010Þ\u0001\u001a\u0011\u0012\u0004\u0012\u00020u\u0012\u0004\u0012\u00020\f\u0018\u00010Å\u0001\u001a0\u0010ñ\u0001\u001a\u00020\"*\u00020\u000f2\u000b\b\u0002\u0010Ý\u0001\u001a\u0004\u0018\u00010\u00062\u0016\u0010Þ\u0001\u001a\u0011\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f\u0018\u00010Å\u0001\u001a*\u0010ò\u0001\u001a\u00020\"*\u00020\u000f2\u000b\b\u0002\u0010Ý\u0001\u001a\u0004\u0018\u00010\u00062\u0010\u0010Þ\u0001\u001a\u000b\u0012\u0004\u0012\u00020\f\u0018\u00010â\u0001\u001a0\u0010ó\u0001\u001a\u00020\"*\u00020\u000f2\u000b\b\u0002\u0010Ý\u0001\u001a\u0004\u0018\u00010\u00062\u0016\u0010Þ\u0001\u001a\u0011\u0012\u0004\u0012\u00020u\u0012\u0004\u0012\u00020\f\u0018\u00010Å\u0001\u001a=\u0010ô\u0001\u001a\u00020\"*\u00020\u000f2\b\u0010õ\u0001\u001a\u00030\u0097\u00012\u000b\b\u0002\u0010Ý\u0001\u001a\u0004\u0018\u00010\u00062\u0010\u0010Þ\u0001\u001a\u000b\u0012\u0004\u0012\u00020\f\u0018\u00010â\u0001¢\u0006\u0006\bö\u0001\u0010÷\u0001\u001a,\u0010ø\u0001\u001a\u00020\"*\u00020\u000f2\u000b\b\u0002\u0010Ý\u0001\u001a\u0004\u0018\u00010\u00062\u0010\u0010Þ\u0001\u001a\u000b\u0012\u0004\u0012\u00020\f\u0018\u00010â\u0001H\u0007\u001aq\u0010ù\u0001\u001a\u00020\"*\u00020\u000f2\u000b\b\u0002\u0010Ý\u0001\u001a\u0004\u0018\u00010\u00062W\u0010Þ\u0001\u001aR\u0012\u0016\u0012\u00140Ç\u0001¢\u0006\u000e\bå\u0001\u0012\t\b\u0005\u0012\u0005\b\b(û\u0001\u0012\u0016\u0012\u00140Ç\u0001¢\u0006\u000e\bå\u0001\u0012\t\b\u0005\u0012\u0005\b\b(ü\u0001\u0012\u0015\u0012\u00130\f¢\u0006\u000e\bå\u0001\u0012\t\b\u0005\u0012\u0005\b\b(ý\u0001\u0012\u0004\u0012\u00020\f\u0018\u00010ú\u0001\u001a*\u0010þ\u0001\u001a\u00020\"*\u00020\u000f2\u000b\b\u0002\u0010Ý\u0001\u001a\u0004\u0018\u00010\u00062\u0010\u0010Þ\u0001\u001a\u000b\u0012\u0004\u0012\u00020\f\u0018\u00010â\u0001\u001a*\u0010ÿ\u0001\u001a\u00020\"*\u00020\u000f2\u000b\b\u0002\u0010Ý\u0001\u001a\u0004\u0018\u00010\u00062\u0010\u0010Þ\u0001\u001a\u000b\u0012\u0004\u0012\u00020\f\u0018\u00010â\u0001\u001a*\u0010\u0080\u0002\u001a\u00020\"*\u00020\u000f2\u000b\b\u0002\u0010Ý\u0001\u001a\u0004\u0018\u00010\u00062\u0010\u0010Þ\u0001\u001a\u000b\u0012\u0004\u0012\u00020\f\u0018\u00010â\u0001\u001a*\u0010\u0081\u0002\u001a\u00020\"*\u00020\u000f2\u000b\b\u0002\u0010Ý\u0001\u001a\u0004\u0018\u00010\u00062\u0010\u0010Þ\u0001\u001a\u000b\u0012\u0004\u0012\u00020\f\u0018\u00010â\u0001\u001a*\u0010\u0082\u0002\u001a\u00020\"*\u00020\u000f2\u000b\b\u0002\u0010Ý\u0001\u001a\u0004\u0018\u00010\u00062\u0010\u0010Þ\u0001\u001a\u000b\u0012\u0004\u0012\u00020\f\u0018\u00010â\u0001\u001a*\u0010\u0083\u0002\u001a\u00020\"*\u00020\u000f2\u000b\b\u0002\u0010Ý\u0001\u001a\u0004\u0018\u00010\u00062\u0010\u0010Þ\u0001\u001a\u000b\u0012\u0004\u0012\u00020\f\u0018\u00010â\u0001\u001a*\u0010\u0084\u0002\u001a\u00020\"*\u00020\u000f2\u000b\b\u0002\u0010Ý\u0001\u001a\u0004\u0018\u00010\u00062\u0010\u0010Þ\u0001\u001a\u000b\u0012\u0004\u0012\u00020\f\u0018\u00010â\u0001\u001a*\u0010\u0085\u0002\u001a\u00020\"*\u00020\u000f2\u000b\b\u0002\u0010Ý\u0001\u001a\u0004\u0018\u00010\u00062\u0010\u0010Þ\u0001\u001a\u000b\u0012\u0004\u0012\u00020\f\u0018\u00010â\u0001\u001a*\u0010\u0086\u0002\u001a\u00020\"*\u00020\u000f2\u000b\b\u0002\u0010Ý\u0001\u001a\u0004\u0018\u00010\u00062\u0010\u0010Þ\u0001\u001a\u000b\u0012\u0004\u0012\u00020\f\u0018\u00010â\u0001\u001a*\u0010\u0087\u0002\u001a\u00020\"*\u00020\u000f2\u000b\b\u0002\u0010Ý\u0001\u001a\u0004\u0018\u00010\u00062\u0010\u0010Þ\u0001\u001a\u000b\u0012\u0004\u0012\u00020\f\u0018\u00010â\u0001\u001a*\u0010\u0088\u0002\u001a\u00020\"*\u00020\u000f2\u000b\b\u0002\u0010Ý\u0001\u001a\u0004\u0018\u00010\u00062\u0010\u0010Þ\u0001\u001a\u000b\u0012\u0004\u0012\u00020\f\u0018\u00010â\u0001\u001a*\u0010\u0089\u0002\u001a\u00020\"*\u00020\u000f2\u000b\b\u0002\u0010Ý\u0001\u001a\u0004\u0018\u00010\u00062\u0010\u0010Þ\u0001\u001a\u000b\u0012\u0006\u0012\u0004\u0018\u00010X0â\u0001\"(\u0010\u000e\u001a\u00020\u0006*\u00020\u000f2\u0006\u0010\r\u001a\u00020\u00068F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013\"/\u0010\u0015\u001a\u00020\u0006*\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u00068F@FX\u0086\u008e\u0002¢\u0006\u0012\u001a\u0004\b\u0018\u0010\u0011\"\u0004\b\u0019\u0010\u0013*\u0004\b\u0016\u0010\u0017\"/\u0010\u001b\u001a\u00020\u001a*\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u001a8F@FX\u0086\u008e\u0002¢\u0006\u0012\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 *\u0004\b\u001c\u0010\u0017\"/\u0010$\u001a\u00020\u0006*\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u00068F@FX\u0086\u008e\u0002¢\u0006\u0012\u001a\u0004\b&\u0010\u0011\"\u0004\b'\u0010\u0013*\u0004\b%\u0010\u0017\"/\u0010*\u001a\u00020)*\u00020\u000f2\u0006\u0010\u0014\u001a\u00020)8F@FX\u0086\u008e\u0002¢\u0006\u0012\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/*\u0004\b+\u0010\u0017\"/\u00100\u001a\u00020\f*\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\f8F@FX\u0086\u008e\u0002¢\u0006\u0012\u001a\u0004\b2\u00103\"\u0004\b4\u00105*\u0004\b1\u0010\u0017\"5\u00106\u001a\u00020\f*\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\f8F@FX\u0087\u008e\u0002¢\u0006\u0018\u0012\u0004\b7\u00108\u001a\u0004\b6\u00103\"\u0004\b:\u00105*\u0004\b9\u0010\u0017\"/\u0010;\u001a\u00020\f*\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\f8F@FX\u0086\u008e\u0002¢\u0006\u0012\u001a\u0004\b;\u00103\"\u0004\b=\u00105*\u0004\b<\u0010\u0017\"/\u0010>\u001a\u00020\f*\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\f8F@FX\u0086\u008e\u0002¢\u0006\u0012\u001a\u0004\b>\u00103\"\u0004\b@\u00105*\u0004\b?\u0010\u0017\"/\u0010D\u001a\u00020C*\u00020\u000f2\u0006\u0010\u0014\u001a\u00020C8F@FX\u0086\u008e\u0002¢\u0006\u0012\u001a\u0004\bF\u0010G\"\u0004\bH\u0010I*\u0004\bE\u0010\u0017\"/\u0010K\u001a\u00020J*\u00020\u000f2\u0006\u0010\u0014\u001a\u00020J8F@FX\u0086\u008e\u0002¢\u0006\u0012\u001a\u0004\bM\u0010N\"\u0004\bO\u0010P*\u0004\bL\u0010\u0017\"/\u0010R\u001a\u00020Q*\u00020\u000f2\u0006\u0010\u0014\u001a\u00020Q8F@FX\u0086\u008e\u0002¢\u0006\u0012\u001a\u0004\bT\u0010U\"\u0004\bV\u0010W*\u0004\bS\u0010\u0017\"/\u0010Y\u001a\u00020X*\u00020\u000f2\u0006\u0010\u0014\u001a\u00020X8F@FX\u0086\u008e\u0002¢\u0006\u0012\u001a\u0004\b[\u0010\\\"\u0004\b]\u0010^*\u0004\bZ\u0010\u0017\"/\u0010`\u001a\u00020_*\u00020\u000f2\u0006\u0010\u0014\u001a\u00020_8F@FX\u0086\u008e\u0002¢\u0006\u0012\u001a\u0004\bb\u0010c\"\u0004\bd\u0010e*\u0004\ba\u0010\u0017\"/\u0010f\u001a\u00020_*\u00020\u000f2\u0006\u0010\u0014\u001a\u00020_8F@FX\u0086\u008e\u0002¢\u0006\u0012\u001a\u0004\bh\u0010c\"\u0004\bi\u0010e*\u0004\bg\u0010\u0017\"/\u0010m\u001a\u00020l*\u00020\u000f2\u0006\u0010\u0014\u001a\u00020l8F@FX\u0086\u008e\u0002¢\u0006\u0012\u001a\u0004\bo\u0010-\"\u0004\bp\u0010/*\u0004\bn\u0010\u0017\"/\u0010q\u001a\u00020\u0006*\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u00068F@FX\u0086\u008e\u0002¢\u0006\u0012\u001a\u0004\bs\u0010\u0011\"\u0004\bt\u0010\u0013*\u0004\br\u0010\u0017\"(\u0010v\u001a\u00020u*\u00020\u000f2\u0006\u0010\r\u001a\u00020u8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bw\u0010x\"\u0004\by\u0010z\"/\u0010{\u001a\u00020u*\u00020\u000f2\u0006\u0010\u0014\u001a\u00020u8F@FX\u0086\u008e\u0002¢\u0006\u0012\u001a\u0004\b}\u0010x\"\u0004\b~\u0010z*\u0004\b|\u0010\u0017\"1\u0010\u007f\u001a\u00020\f*\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\f8F@FX\u0086\u008e\u0002¢\u0006\u0014\u001a\u0004\b\u007f\u00103\"\u0005\b\u0081\u0001\u00105*\u0005\b\u0080\u0001\u0010\u0017\"3\u0010\u0082\u0001\u001a\u00020u*\u00020\u000f2\u0006\u0010\u0014\u001a\u00020u8F@FX\u0086\u008e\u0002¢\u0006\u0015\u001a\u0005\b\u0084\u0001\u0010x\"\u0005\b\u0085\u0001\u0010z*\u0005\b\u0083\u0001\u0010\u0017\"3\u0010\u0086\u0001\u001a\u00020u*\u00020\u000f2\u0006\u0010\u0014\u001a\u00020u8F@FX\u0086\u008e\u0002¢\u0006\u0015\u001a\u0005\b\u0088\u0001\u0010x\"\u0005\b\u0089\u0001\u0010z*\u0005\b\u0087\u0001\u0010\u0017\"7\u0010\u008b\u0001\u001a\u00030\u008a\u0001*\u00020\u000f2\u0007\u0010\u0014\u001a\u00030\u008a\u00018F@FX\u0086\u008e\u0002¢\u0006\u0017\u001a\u0006\b\u008d\u0001\u0010\u008e\u0001\"\u0006\b\u008f\u0001\u0010\u0090\u0001*\u0005\b\u008c\u0001\u0010\u0017\";\u0010\u0091\u0001\u001a\u0005\u0018\u00010\u008a\u0001*\u00020\u000f2\t\u0010\u0014\u001a\u0005\u0018\u00010\u008a\u00018F@FX\u0086\u008e\u0002¢\u0006\u0017\u001a\u0006\b\u0093\u0001\u0010\u0094\u0001\"\u0006\b\u0095\u0001\u0010\u0096\u0001*\u0005\b\u0092\u0001\u0010\u0017\"<\u0010\u0098\u0001\u001a\u00030\u0097\u0001*\u00020\u000f2\u0007\u0010\u0014\u001a\u00030\u0097\u00018G@GX\u0087\u008e\u0002¢\u0006\u001c\u0012\u0005\b\u0099\u0001\u00108\u001a\u0005\b\u009b\u0001\u0010-\"\u0005\b\u009c\u0001\u0010/*\u0005\b\u009a\u0001\u0010\u0017\"3\u0010\u009d\u0001\u001a\u00020\f*\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\f8F@FX\u0086\u008e\u0002¢\u0006\u0015\u001a\u0005\b\u009f\u0001\u00103\"\u0005\b \u0001\u00105*\u0005\b\u009e\u0001\u0010\u0017\"7\u0010¢\u0001\u001a\u00030¡\u0001*\u00020\u000f2\u0007\u0010\u0014\u001a\u00030¡\u00018F@FX\u0086\u008e\u0002¢\u0006\u0017\u001a\u0006\b¤\u0001\u0010¥\u0001\"\u0006\b¦\u0001\u0010§\u0001*\u0005\b£\u0001\u0010\u0017\"7\u0010©\u0001\u001a\u00030¨\u0001*\u00020\u000f2\u0007\u0010\u0014\u001a\u00030¨\u00018F@FX\u0086\u008e\u0002¢\u0006\u0017\u001a\u0006\b«\u0001\u0010¬\u0001\"\u0006\b\u00ad\u0001\u0010®\u0001*\u0005\bª\u0001\u0010\u0017\"7\u0010°\u0001\u001a\u00030¯\u0001*\u00020\u000f2\u0007\u0010\u0014\u001a\u00030¯\u00018F@FX\u0086\u008e\u0002¢\u0006\u0017\u001a\u0006\b²\u0001\u0010³\u0001\"\u0006\b´\u0001\u0010µ\u0001*\u0005\b±\u0001\u0010\u0017\"7\u0010·\u0001\u001a\u00030¶\u0001*\u00020\u000f2\u0007\u0010\u0014\u001a\u00030¶\u00018F@FX\u0086\u008e\u0002¢\u0006\u0017\u001a\u0006\b¹\u0001\u0010º\u0001\"\u0006\b»\u0001\u0010¼\u0001*\u0005\b¸\u0001\u0010\u0017\"3\u0010½\u0001\u001a\u00020\f*\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\f8F@FX\u0086\u008e\u0002¢\u0006\u0015\u001a\u0005\b½\u0001\u00103\"\u0005\b¿\u0001\u00105*\u0005\b¾\u0001\u0010\u0017\"5\u0010È\u0001\u001a\u00030Ç\u0001*\u00020\u000f2\u0007\u0010\u0014\u001a\u00030Ç\u00018F@FX\u0086\u008e\u0002¢\u0006\u0015\u001a\u0005\bÊ\u0001\u0010-\"\u0005\bË\u0001\u0010/*\u0005\bÉ\u0001\u0010\u0017\"7\u0010Í\u0001\u001a\u00030Ì\u0001*\u00020\u000f2\u0007\u0010\u0014\u001a\u00030Ì\u00018F@FX\u0086\u008e\u0002¢\u0006\u0017\u001a\u0006\bÏ\u0001\u0010Ð\u0001\"\u0006\bÑ\u0001\u0010Ò\u0001*\u0005\bÎ\u0001\u0010\u0017\"E\u0010Ö\u0001\u001a\n\u0012\u0005\u0012\u00030Õ\u00010Ô\u0001*\u00020\u000f2\u000e\u0010\u0014\u001a\n\u0012\u0005\u0012\u00030Õ\u00010Ô\u00018F@FX\u0086\u008e\u0002¢\u0006\u0017\u001a\u0006\bØ\u0001\u0010Ù\u0001\"\u0006\bÚ\u0001\u0010Û\u0001*\u0005\b×\u0001\u0010\u0017¨\u0006\u008a\u0002"}, d2 = {"throwSemanticsGetNotSupported", "T", "()Ljava/lang/Object;", "AccessibilityKey", "Landroidx/compose/ui/semantics/SemanticsPropertyKey;", HintConstants.AUTOFILL_HINT_NAME, "", "mergePolicy", "Lkotlin/Function2;", "ActionPropertyKey", "Landroidx/compose/ui/semantics/AccessibilityAction;", "Lkotlin/Function;", "", "value", "contentDescription", "Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;", "getContentDescription", "(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)Ljava/lang/String;", "setContentDescription", "(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;Ljava/lang/String;)V", "<set-?>", "stateDescription", "getStateDescription$delegate", "(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)Ljava/lang/Object;", "getStateDescription", "setStateDescription", "Landroidx/compose/ui/semantics/ProgressBarRangeInfo;", "progressBarRangeInfo", "getProgressBarRangeInfo$delegate", "getProgressBarRangeInfo", "(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)Landroidx/compose/ui/semantics/ProgressBarRangeInfo;", "setProgressBarRangeInfo", "(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;Landroidx/compose/ui/semantics/ProgressBarRangeInfo;)V", "heading", "", "textEntryKey", "paneTitle", "getPaneTitle$delegate", "getPaneTitle", "setPaneTitle", "disabled", "Landroidx/compose/ui/semantics/LiveRegionMode;", "liveRegion", "getLiveRegion$delegate", "getLiveRegion", "(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)I", "setLiveRegion-hR3wRGc", "(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;I)V", "focused", "getFocused$delegate", "getFocused", "(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)Z", "setFocused", "(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;Z)V", "isContainer", "isContainer$annotations", "(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)V", "isContainer$delegate", "setContainer", "isTraversalGroup", "isTraversalGroup$delegate", "setTraversalGroup", "isSensitiveData", "isSensitiveData$delegate", "setSensitiveData", "invisibleToUser", "hideFromAccessibility", "Landroidx/compose/ui/autofill/ContentType;", "contentType", "getContentType$delegate", "getContentType", "(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)Landroidx/compose/ui/autofill/ContentType;", "setContentType", "(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;Landroidx/compose/ui/autofill/ContentType;)V", "Landroidx/compose/ui/autofill/ContentDataType;", "contentDataType", "getContentDataType$delegate", "getContentDataType", "(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)Landroidx/compose/ui/autofill/ContentDataType;", "setContentDataType", "(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;Landroidx/compose/ui/autofill/ContentDataType;)V", "Landroidx/compose/ui/autofill/FillableData;", "fillableData", "getFillableData$delegate", "getFillableData", "(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)Landroidx/compose/ui/autofill/FillableData;", "setFillableData", "(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;Landroidx/compose/ui/autofill/FillableData;)V", "", "traversalIndex", "getTraversalIndex$delegate", "getTraversalIndex", "(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)F", "setTraversalIndex", "(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;F)V", "Landroidx/compose/ui/semantics/ScrollAxisRange;", "horizontalScrollAxisRange", "getHorizontalScrollAxisRange$delegate", "getHorizontalScrollAxisRange", "(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)Landroidx/compose/ui/semantics/ScrollAxisRange;", "setHorizontalScrollAxisRange", "(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;Landroidx/compose/ui/semantics/ScrollAxisRange;)V", "verticalScrollAxisRange", "getVerticalScrollAxisRange$delegate", "getVerticalScrollAxisRange", "setVerticalScrollAxisRange", "popup", DialogNavigator.NAME, "Landroidx/compose/ui/semantics/Role;", "role", "getRole$delegate", "getRole", "setRole-kuIjeqM", "testTag", "getTestTag$delegate", "getTestTag", "setTestTag", "Landroidx/compose/ui/text/AnnotatedString;", "text", "getText", "(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)Landroidx/compose/ui/text/AnnotatedString;", "setText", "(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;Landroidx/compose/ui/text/AnnotatedString;)V", "textSubstitution", "getTextSubstitution$delegate", "getTextSubstitution", "setTextSubstitution", "isShowingTextSubstitution", "isShowingTextSubstitution$delegate", "setShowingTextSubstitution", "inputText", "getInputText$delegate", "getInputText", "setInputText", "editableText", "getEditableText$delegate", "getEditableText", "setEditableText", "Landroidx/compose/ui/text/TextRange;", "textSelectionRange", "getTextSelectionRange$delegate", "getTextSelectionRange", "(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)J", "setTextSelectionRange-FDrldGo", "(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;J)V", "textCompositionRange", "getTextCompositionRange$delegate", "getTextCompositionRange", "(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)Landroidx/compose/ui/text/TextRange;", "setTextCompositionRange-psREZIo", "(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;Landroidx/compose/ui/text/TextRange;)V", "Landroidx/compose/ui/text/input/ImeAction;", "imeAction", "getImeAction$annotations", "getImeAction$delegate", "getImeAction", "setImeAction-4L7nppU", "selected", "getSelected$delegate", "getSelected", "setSelected", "Landroidx/compose/ui/semantics/CollectionInfo;", "collectionInfo", "getCollectionInfo$delegate", "getCollectionInfo", "(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)Landroidx/compose/ui/semantics/CollectionInfo;", "setCollectionInfo", "(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;Landroidx/compose/ui/semantics/CollectionInfo;)V", "Landroidx/compose/ui/semantics/CollectionItemInfo;", "collectionItemInfo", "getCollectionItemInfo$delegate", "getCollectionItemInfo", "(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)Landroidx/compose/ui/semantics/CollectionItemInfo;", "setCollectionItemInfo", "(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;Landroidx/compose/ui/semantics/CollectionItemInfo;)V", "Landroidx/compose/ui/state/ToggleableState;", "toggleableState", "getToggleableState$delegate", "getToggleableState", "(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)Landroidx/compose/ui/state/ToggleableState;", "setToggleableState", "(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;Landroidx/compose/ui/state/ToggleableState;)V", "Landroidx/compose/ui/semantics/InputTextSuggestionState;", "inputTextSuggestionState", "getInputTextSuggestionState$delegate", "getInputTextSuggestionState", "(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)Landroidx/compose/ui/semantics/InputTextSuggestionState;", "setInputTextSuggestionState", "(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;Landroidx/compose/ui/semantics/InputTextSuggestionState;)V", "isEditable", "isEditable$delegate", "setEditable", HintConstants.AUTOFILL_HINT_PASSWORD, "error", "description", "indexForKey", "mapping", "Lkotlin/Function1;", "", "", "maxTextLength", "getMaxTextLength$delegate", "getMaxTextLength", "setMaxTextLength", "Landroidx/compose/ui/graphics/Shape;", "shape", "getShape$delegate", "getShape", "(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)Landroidx/compose/ui/graphics/Shape;", "setShape", "(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;Landroidx/compose/ui/graphics/Shape;)V", "selectableGroup", "", "Landroidx/compose/ui/semantics/CustomAccessibilityAction;", "customActions", "getCustomActions$delegate", "getCustomActions", "(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)Ljava/util/List;", "setCustomActions", "(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;Ljava/util/List;)V", "getTextLayoutResult", "label", "action", "", "Landroidx/compose/ui/text/TextLayoutResult;", "onClick", "Lkotlin/Function0;", "onLongClick", "scrollBy", "Lkotlin/ParameterName;", "x", "y", "scrollByOffset", "Landroidx/compose/ui/geometry/Offset;", TypedValues.CycleType.S_WAVE_OFFSET, "Lkotlin/coroutines/Continuation;", "(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;Lkotlin/jvm/functions/Function2;)V", "scrollToIndex", "onAutofillText", "onFillData", "setProgress", "showTextSubstitution", "clearTextSubstitution", "insertTextAtCursor", "onImeAction", "imeActionType", "onImeAction-9UiTYpY", "(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;ILjava/lang/String;Lkotlin/jvm/functions/Function0;)V", "performImeAction", "setSelection", "Lkotlin/Function3;", "startIndex", "endIndex", "relativeToOriginalText", "copyText", "cutText", "pasteText", "expand", "collapse", "dismiss", "requestFocus", "pageUp", "pageDown", "pageLeft", "pageRight", "getScrollViewportLength", "ui"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class SemanticsPropertiesKt {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {new MutablePropertyReference1Impl(SemanticsPropertiesKt.class, "stateDescription", "getStateDescription(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)Ljava/lang/String;", 1), new MutablePropertyReference1Impl(SemanticsPropertiesKt.class, "progressBarRangeInfo", "getProgressBarRangeInfo(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)Landroidx/compose/ui/semantics/ProgressBarRangeInfo;", 1), new MutablePropertyReference1Impl(SemanticsPropertiesKt.class, "paneTitle", "getPaneTitle(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)Ljava/lang/String;", 1), new MutablePropertyReference1Impl(SemanticsPropertiesKt.class, "liveRegion", "getLiveRegion(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)I", 1), new MutablePropertyReference1Impl(SemanticsPropertiesKt.class, "focused", "getFocused(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)Z", 1), new MutablePropertyReference1Impl(SemanticsPropertiesKt.class, "isContainer", "isContainer(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)Z", 1), new MutablePropertyReference1Impl(SemanticsPropertiesKt.class, "isTraversalGroup", "isTraversalGroup(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)Z", 1), new MutablePropertyReference1Impl(SemanticsPropertiesKt.class, "isSensitiveData", "isSensitiveData(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)Z", 1), new MutablePropertyReference1Impl(SemanticsPropertiesKt.class, "contentType", "getContentType(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)Landroidx/compose/ui/autofill/ContentType;", 1), new MutablePropertyReference1Impl(SemanticsPropertiesKt.class, "contentDataType", "getContentDataType(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)Landroidx/compose/ui/autofill/ContentDataType;", 1), new MutablePropertyReference1Impl(SemanticsPropertiesKt.class, "fillableData", "getFillableData(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)Landroidx/compose/ui/autofill/FillableData;", 1), new MutablePropertyReference1Impl(SemanticsPropertiesKt.class, "traversalIndex", "getTraversalIndex(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)F", 1), new MutablePropertyReference1Impl(SemanticsPropertiesKt.class, "horizontalScrollAxisRange", "getHorizontalScrollAxisRange(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)Landroidx/compose/ui/semantics/ScrollAxisRange;", 1), new MutablePropertyReference1Impl(SemanticsPropertiesKt.class, "verticalScrollAxisRange", "getVerticalScrollAxisRange(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)Landroidx/compose/ui/semantics/ScrollAxisRange;", 1), new MutablePropertyReference1Impl(SemanticsPropertiesKt.class, "role", "getRole(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)I", 1), new MutablePropertyReference1Impl(SemanticsPropertiesKt.class, "testTag", "getTestTag(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)Ljava/lang/String;", 1), new MutablePropertyReference1Impl(SemanticsPropertiesKt.class, "textSubstitution", "getTextSubstitution(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)Landroidx/compose/ui/text/AnnotatedString;", 1), new MutablePropertyReference1Impl(SemanticsPropertiesKt.class, "isShowingTextSubstitution", "isShowingTextSubstitution(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)Z", 1), new MutablePropertyReference1Impl(SemanticsPropertiesKt.class, "inputText", "getInputText(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)Landroidx/compose/ui/text/AnnotatedString;", 1), new MutablePropertyReference1Impl(SemanticsPropertiesKt.class, "editableText", "getEditableText(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)Landroidx/compose/ui/text/AnnotatedString;", 1), new MutablePropertyReference1Impl(SemanticsPropertiesKt.class, "textSelectionRange", "getTextSelectionRange(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)J", 1), new MutablePropertyReference1Impl(SemanticsPropertiesKt.class, "textCompositionRange", "getTextCompositionRange(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)Landroidx/compose/ui/text/TextRange;", 1), new MutablePropertyReference1Impl(SemanticsPropertiesKt.class, "imeAction", "getImeAction(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)I", 1), new MutablePropertyReference1Impl(SemanticsPropertiesKt.class, "selected", "getSelected(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)Z", 1), new MutablePropertyReference1Impl(SemanticsPropertiesKt.class, "collectionInfo", "getCollectionInfo(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)Landroidx/compose/ui/semantics/CollectionInfo;", 1), new MutablePropertyReference1Impl(SemanticsPropertiesKt.class, "collectionItemInfo", "getCollectionItemInfo(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)Landroidx/compose/ui/semantics/CollectionItemInfo;", 1), new MutablePropertyReference1Impl(SemanticsPropertiesKt.class, "toggleableState", "getToggleableState(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)Landroidx/compose/ui/state/ToggleableState;", 1), new MutablePropertyReference1Impl(SemanticsPropertiesKt.class, "inputTextSuggestionState", "getInputTextSuggestionState(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)Landroidx/compose/ui/semantics/InputTextSuggestionState;", 1), new MutablePropertyReference1Impl(SemanticsPropertiesKt.class, "isEditable", "isEditable(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)Z", 1), new MutablePropertyReference1Impl(SemanticsPropertiesKt.class, "maxTextLength", "getMaxTextLength(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)I", 1), new MutablePropertyReference1Impl(SemanticsPropertiesKt.class, "shape", "getShape(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)Landroidx/compose/ui/graphics/Shape;", 1), new MutablePropertyReference1Impl(SemanticsPropertiesKt.class, "customActions", "getCustomActions(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)Ljava/util/List;", 1)};

    static {
        SemanticsProperties.INSTANCE.getStateDescription();
        SemanticsProperties.INSTANCE.getProgressBarRangeInfo();
        SemanticsProperties.INSTANCE.getPaneTitle();
        SemanticsProperties.INSTANCE.getLiveRegion();
        SemanticsProperties.INSTANCE.getFocused();
        SemanticsProperties.INSTANCE.getIsContainer();
        SemanticsProperties.INSTANCE.getIsTraversalGroup();
        SemanticsProperties.INSTANCE.getIsSensitiveData();
        SemanticsProperties.INSTANCE.getContentType();
        SemanticsProperties.INSTANCE.getContentDataType();
        SemanticsProperties.INSTANCE.getFillableData();
        SemanticsProperties.INSTANCE.getTraversalIndex();
        SemanticsProperties.INSTANCE.getHorizontalScrollAxisRange();
        SemanticsProperties.INSTANCE.getVerticalScrollAxisRange();
        SemanticsProperties.INSTANCE.getRole();
        SemanticsProperties.INSTANCE.getTestTag();
        SemanticsProperties.INSTANCE.getTextSubstitution();
        SemanticsProperties.INSTANCE.getIsShowingTextSubstitution();
        SemanticsProperties.INSTANCE.getInputText();
        SemanticsProperties.INSTANCE.getEditableText();
        SemanticsProperties.INSTANCE.getTextSelectionRange();
        SemanticsProperties.INSTANCE.getTextCompositionRange();
        SemanticsProperties.INSTANCE.getImeAction();
        SemanticsProperties.INSTANCE.getSelected();
        SemanticsProperties.INSTANCE.getCollectionInfo();
        SemanticsProperties.INSTANCE.getCollectionItemInfo();
        SemanticsProperties.INSTANCE.getToggleableState();
        SemanticsProperties.INSTANCE.getInputTextSuggestionState();
        SemanticsProperties.INSTANCE.getIsEditable();
        SemanticsProperties.INSTANCE.getMaxTextLength();
        SemanticsProperties.INSTANCE.getShape();
        SemanticsActions.INSTANCE.getCustomActions();
    }

    @Deprecated(message = "Pass the ImeAction to onImeAction instead.")
    public static /* synthetic */ void getImeAction$annotations(SemanticsPropertyReceiver semanticsPropertyReceiver) {
    }

    @Deprecated(message = "Use `isTraversalGroup` instead.", replaceWith = @ReplaceWith(expression = "isTraversalGroup", imports = {}))
    public static /* synthetic */ void isContainer$annotations(SemanticsPropertyReceiver semanticsPropertyReceiver) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final <T> T throwSemanticsGetNotSupported() {
        throw new UnsupportedOperationException("You cannot retrieve a semantics property directly - use one of the SemanticsConfiguration.getOr* methods instead");
    }

    public static final <T> SemanticsPropertyKey<T> AccessibilityKey(String name) {
        return new SemanticsPropertyKey<>(name, true);
    }

    public static final <T> SemanticsPropertyKey<T> AccessibilityKey(String name, Function2<? super T, ? super T, ? extends T> function2) {
        return new SemanticsPropertyKey<>(name, true, function2, null, 8, null);
    }

    private static final <T extends Function<? extends Boolean>> SemanticsPropertyKey<AccessibilityAction<T>> ActionPropertyKey(String name) {
        Function2 mergePolicy$iv = AnonymousClass1.INSTANCE;
        return new SemanticsPropertyKey<>(name, true, mergePolicy$iv, null, 8, null);
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: renamed from: androidx.compose.ui.semantics.SemanticsPropertiesKt$ActionPropertyKey$1, reason: invalid class name */
    /* JADX INFO: compiled from: SemanticsProperties.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\u0010\u0000\u001a\n\u0012\u0004\u0012\u0002H\u0002\u0018\u00010\u0001\"\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\u0010\u0005\u001a\n\u0012\u0004\u0012\u0002H\u0002\u0018\u00010\u00012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "Landroidx/compose/ui/semantics/AccessibilityAction;", "T", "Lkotlin/Function;", "", "parentValue", "childValue", "invoke"}, k = 3, mv = {2, 1, 0}, xi = 48)
    public static final class AnonymousClass1<T> extends Lambda implements Function2<AccessibilityAction<T>, AccessibilityAction<T>, AccessibilityAction<T>> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(2);
        }

        @Override // kotlin.jvm.functions.Function2
        public final AccessibilityAction<T> invoke(AccessibilityAction<T> accessibilityAction, AccessibilityAction<T> accessibilityAction2) {
            String label;
            Function action;
            if (accessibilityAction == null || (label = accessibilityAction.getLabel()) == null) {
                label = accessibilityAction2.getLabel();
            }
            if (accessibilityAction == null || (action = accessibilityAction.getAction()) == null) {
                action = accessibilityAction2.getAction();
            }
            return new AccessibilityAction<>(label, action);
        }
    }

    public static final String getContentDescription(SemanticsPropertyReceiver $this$contentDescription) {
        return (String) throwSemanticsGetNotSupported();
    }

    public static final void setContentDescription(SemanticsPropertyReceiver $this$contentDescription, String value) {
        $this$contentDescription.set(SemanticsProperties.INSTANCE.getContentDescription(), CollectionsKt.listOf(value));
    }

    public static final String getStateDescription(SemanticsPropertyReceiver $this$stateDescription) {
        return SemanticsProperties.INSTANCE.getStateDescription().getValue($this$stateDescription, $$delegatedProperties[0]);
    }

    public static final void setStateDescription(SemanticsPropertyReceiver $this$stateDescription, String str) {
        SemanticsProperties.INSTANCE.getStateDescription().setValue($this$stateDescription, $$delegatedProperties[0], str);
    }

    public static final ProgressBarRangeInfo getProgressBarRangeInfo(SemanticsPropertyReceiver $this$progressBarRangeInfo) {
        return SemanticsProperties.INSTANCE.getProgressBarRangeInfo().getValue($this$progressBarRangeInfo, $$delegatedProperties[1]);
    }

    public static final void setProgressBarRangeInfo(SemanticsPropertyReceiver $this$progressBarRangeInfo, ProgressBarRangeInfo progressBarRangeInfo) {
        SemanticsProperties.INSTANCE.getProgressBarRangeInfo().setValue($this$progressBarRangeInfo, $$delegatedProperties[1], progressBarRangeInfo);
    }

    public static final void heading(SemanticsPropertyReceiver $this$heading) {
        $this$heading.set(SemanticsProperties.INSTANCE.getHeading(), Unit.INSTANCE);
    }

    public static final void textEntryKey(SemanticsPropertyReceiver $this$textEntryKey) {
        $this$textEntryKey.set(SemanticsProperties.INSTANCE.getTextEntryKey(), Unit.INSTANCE);
    }

    public static final String getPaneTitle(SemanticsPropertyReceiver $this$paneTitle) {
        return SemanticsProperties.INSTANCE.getPaneTitle().getValue($this$paneTitle, $$delegatedProperties[2]);
    }

    public static final void setPaneTitle(SemanticsPropertyReceiver $this$paneTitle, String str) {
        SemanticsProperties.INSTANCE.getPaneTitle().setValue($this$paneTitle, $$delegatedProperties[2], str);
    }

    public static final void disabled(SemanticsPropertyReceiver $this$disabled) {
        $this$disabled.set(SemanticsProperties.INSTANCE.getDisabled(), Unit.INSTANCE);
    }

    public static final int getLiveRegion(SemanticsPropertyReceiver $this$liveRegion) {
        return SemanticsProperties.INSTANCE.getLiveRegion().getValue($this$liveRegion, $$delegatedProperties[3]).getValue();
    }

    /* JADX INFO: renamed from: setLiveRegion-hR3wRGc, reason: not valid java name */
    public static final void m7361setLiveRegionhR3wRGc(SemanticsPropertyReceiver $this$liveRegion, int i) {
        SemanticsProperties.INSTANCE.getLiveRegion().setValue($this$liveRegion, $$delegatedProperties[3], LiveRegionMode.m7327boximpl(i));
    }

    public static final boolean getFocused(SemanticsPropertyReceiver $this$focused) {
        return SemanticsProperties.INSTANCE.getFocused().getValue($this$focused, $$delegatedProperties[4]).booleanValue();
    }

    public static final void setFocused(SemanticsPropertyReceiver $this$focused, boolean z) {
        SemanticsProperties.INSTANCE.getFocused().setValue($this$focused, $$delegatedProperties[4], Boolean.valueOf(z));
    }

    public static final boolean isContainer(SemanticsPropertyReceiver $this$isContainer) {
        return SemanticsProperties.INSTANCE.getIsContainer().getValue($this$isContainer, $$delegatedProperties[5]).booleanValue();
    }

    public static final void setContainer(SemanticsPropertyReceiver $this$isContainer, boolean z) {
        SemanticsProperties.INSTANCE.getIsContainer().setValue($this$isContainer, $$delegatedProperties[5], Boolean.valueOf(z));
    }

    public static final boolean isTraversalGroup(SemanticsPropertyReceiver $this$isTraversalGroup) {
        return SemanticsProperties.INSTANCE.getIsTraversalGroup().getValue($this$isTraversalGroup, $$delegatedProperties[6]).booleanValue();
    }

    public static final void setTraversalGroup(SemanticsPropertyReceiver $this$isTraversalGroup, boolean z) {
        SemanticsProperties.INSTANCE.getIsTraversalGroup().setValue($this$isTraversalGroup, $$delegatedProperties[6], Boolean.valueOf(z));
    }

    public static final boolean isSensitiveData(SemanticsPropertyReceiver $this$isSensitiveData) {
        return SemanticsProperties.INSTANCE.getIsSensitiveData().getValue($this$isSensitiveData, $$delegatedProperties[7]).booleanValue();
    }

    public static final void setSensitiveData(SemanticsPropertyReceiver $this$isSensitiveData, boolean z) {
        SemanticsProperties.INSTANCE.getIsSensitiveData().setValue($this$isSensitiveData, $$delegatedProperties[7], Boolean.valueOf(z));
    }

    @Deprecated(message = "Use `hideFromAccessibility()` instead.", replaceWith = @ReplaceWith(expression = "hideFromAccessibility()", imports = {}))
    public static final void invisibleToUser(SemanticsPropertyReceiver $this$invisibleToUser) {
        $this$invisibleToUser.set(SemanticsProperties.INSTANCE.getInvisibleToUser(), Unit.INSTANCE);
    }

    public static final void hideFromAccessibility(SemanticsPropertyReceiver $this$hideFromAccessibility) {
        $this$hideFromAccessibility.set(SemanticsProperties.INSTANCE.getHideFromAccessibility(), Unit.INSTANCE);
    }

    public static final ContentType getContentType(SemanticsPropertyReceiver $this$contentType) {
        return SemanticsProperties.INSTANCE.getContentType().getValue($this$contentType, $$delegatedProperties[8]);
    }

    public static final void setContentType(SemanticsPropertyReceiver $this$contentType, ContentType contentType) {
        SemanticsProperties.INSTANCE.getContentType().setValue($this$contentType, $$delegatedProperties[8], contentType);
    }

    public static final ContentDataType getContentDataType(SemanticsPropertyReceiver $this$contentDataType) {
        return SemanticsProperties.INSTANCE.getContentDataType().getValue($this$contentDataType, $$delegatedProperties[9]);
    }

    public static final void setContentDataType(SemanticsPropertyReceiver $this$contentDataType, ContentDataType contentDataType) {
        SemanticsProperties.INSTANCE.getContentDataType().setValue($this$contentDataType, $$delegatedProperties[9], contentDataType);
    }

    public static final FillableData getFillableData(SemanticsPropertyReceiver $this$fillableData) {
        return SemanticsProperties.INSTANCE.getFillableData().getValue($this$fillableData, $$delegatedProperties[10]);
    }

    public static final void setFillableData(SemanticsPropertyReceiver $this$fillableData, FillableData fillableData) {
        SemanticsProperties.INSTANCE.getFillableData().setValue($this$fillableData, $$delegatedProperties[10], fillableData);
    }

    public static final float getTraversalIndex(SemanticsPropertyReceiver $this$traversalIndex) {
        return SemanticsProperties.INSTANCE.getTraversalIndex().getValue($this$traversalIndex, $$delegatedProperties[11]).floatValue();
    }

    public static final void setTraversalIndex(SemanticsPropertyReceiver $this$traversalIndex, float f) {
        SemanticsProperties.INSTANCE.getTraversalIndex().setValue($this$traversalIndex, $$delegatedProperties[11], Float.valueOf(f));
    }

    public static final ScrollAxisRange getHorizontalScrollAxisRange(SemanticsPropertyReceiver $this$horizontalScrollAxisRange) {
        return SemanticsProperties.INSTANCE.getHorizontalScrollAxisRange().getValue($this$horizontalScrollAxisRange, $$delegatedProperties[12]);
    }

    public static final void setHorizontalScrollAxisRange(SemanticsPropertyReceiver $this$horizontalScrollAxisRange, ScrollAxisRange scrollAxisRange) {
        SemanticsProperties.INSTANCE.getHorizontalScrollAxisRange().setValue($this$horizontalScrollAxisRange, $$delegatedProperties[12], scrollAxisRange);
    }

    public static final ScrollAxisRange getVerticalScrollAxisRange(SemanticsPropertyReceiver $this$verticalScrollAxisRange) {
        return SemanticsProperties.INSTANCE.getVerticalScrollAxisRange().getValue($this$verticalScrollAxisRange, $$delegatedProperties[13]);
    }

    public static final void setVerticalScrollAxisRange(SemanticsPropertyReceiver $this$verticalScrollAxisRange, ScrollAxisRange scrollAxisRange) {
        SemanticsProperties.INSTANCE.getVerticalScrollAxisRange().setValue($this$verticalScrollAxisRange, $$delegatedProperties[13], scrollAxisRange);
    }

    public static final void popup(SemanticsPropertyReceiver $this$popup) {
        $this$popup.set(SemanticsProperties.INSTANCE.getIsPopup(), Unit.INSTANCE);
    }

    public static final void dialog(SemanticsPropertyReceiver $this$dialog) {
        $this$dialog.set(SemanticsProperties.INSTANCE.getIsDialog(), Unit.INSTANCE);
    }

    public static final int getRole(SemanticsPropertyReceiver $this$role) {
        return SemanticsProperties.INSTANCE.getRole().getValue($this$role, $$delegatedProperties[14]).getValue();
    }

    /* JADX INFO: renamed from: setRole-kuIjeqM, reason: not valid java name */
    public static final void m7362setRolekuIjeqM(SemanticsPropertyReceiver $this$role, int i) {
        SemanticsProperties.INSTANCE.getRole().setValue($this$role, $$delegatedProperties[14], Role.m7336boximpl(i));
    }

    public static final String getTestTag(SemanticsPropertyReceiver $this$testTag) {
        return SemanticsProperties.INSTANCE.getTestTag().getValue($this$testTag, $$delegatedProperties[15]);
    }

    public static final void setTestTag(SemanticsPropertyReceiver $this$testTag, String str) {
        SemanticsProperties.INSTANCE.getTestTag().setValue($this$testTag, $$delegatedProperties[15], str);
    }

    public static final AnnotatedString getText(SemanticsPropertyReceiver $this$text) {
        return (AnnotatedString) throwSemanticsGetNotSupported();
    }

    public static final void setText(SemanticsPropertyReceiver $this$text, AnnotatedString value) {
        $this$text.set(SemanticsProperties.INSTANCE.getText(), CollectionsKt.listOf(value));
    }

    public static final AnnotatedString getTextSubstitution(SemanticsPropertyReceiver $this$textSubstitution) {
        return SemanticsProperties.INSTANCE.getTextSubstitution().getValue($this$textSubstitution, $$delegatedProperties[16]);
    }

    public static final void setTextSubstitution(SemanticsPropertyReceiver $this$textSubstitution, AnnotatedString annotatedString) {
        SemanticsProperties.INSTANCE.getTextSubstitution().setValue($this$textSubstitution, $$delegatedProperties[16], annotatedString);
    }

    public static final boolean isShowingTextSubstitution(SemanticsPropertyReceiver $this$isShowingTextSubstitution) {
        return SemanticsProperties.INSTANCE.getIsShowingTextSubstitution().getValue($this$isShowingTextSubstitution, $$delegatedProperties[17]).booleanValue();
    }

    public static final void setShowingTextSubstitution(SemanticsPropertyReceiver $this$isShowingTextSubstitution, boolean z) {
        SemanticsProperties.INSTANCE.getIsShowingTextSubstitution().setValue($this$isShowingTextSubstitution, $$delegatedProperties[17], Boolean.valueOf(z));
    }

    public static final AnnotatedString getInputText(SemanticsPropertyReceiver $this$inputText) {
        return SemanticsProperties.INSTANCE.getInputText().getValue($this$inputText, $$delegatedProperties[18]);
    }

    public static final void setInputText(SemanticsPropertyReceiver $this$inputText, AnnotatedString annotatedString) {
        SemanticsProperties.INSTANCE.getInputText().setValue($this$inputText, $$delegatedProperties[18], annotatedString);
    }

    public static final AnnotatedString getEditableText(SemanticsPropertyReceiver $this$editableText) {
        return SemanticsProperties.INSTANCE.getEditableText().getValue($this$editableText, $$delegatedProperties[19]);
    }

    public static final void setEditableText(SemanticsPropertyReceiver $this$editableText, AnnotatedString annotatedString) {
        SemanticsProperties.INSTANCE.getEditableText().setValue($this$editableText, $$delegatedProperties[19], annotatedString);
    }

    public static final long getTextSelectionRange(SemanticsPropertyReceiver $this$textSelectionRange) {
        return SemanticsProperties.INSTANCE.getTextSelectionRange().getValue($this$textSelectionRange, $$delegatedProperties[20]).getPackedValue();
    }

    /* JADX INFO: renamed from: setTextSelectionRange-FDrldGo, reason: not valid java name */
    public static final void m7364setTextSelectionRangeFDrldGo(SemanticsPropertyReceiver $this$textSelectionRange, long j) {
        SemanticsProperties.INSTANCE.getTextSelectionRange().setValue($this$textSelectionRange, $$delegatedProperties[20], TextRange.m7561boximpl(j));
    }

    public static final TextRange getTextCompositionRange(SemanticsPropertyReceiver $this$textCompositionRange) {
        return SemanticsProperties.INSTANCE.getTextCompositionRange().getValue($this$textCompositionRange, $$delegatedProperties[21]);
    }

    /* JADX INFO: renamed from: setTextCompositionRange-psREZIo, reason: not valid java name */
    public static final void m7363setTextCompositionRangepsREZIo(SemanticsPropertyReceiver $this$textCompositionRange, TextRange textRange) {
        SemanticsProperties.INSTANCE.getTextCompositionRange().setValue($this$textCompositionRange, $$delegatedProperties[21], textRange);
    }

    @Deprecated(message = "Pass the ImeAction to onImeAction instead.")
    public static final int getImeAction(SemanticsPropertyReceiver $this$imeAction) {
        return SemanticsProperties.INSTANCE.getImeAction().getValue($this$imeAction, $$delegatedProperties[22]).getValue();
    }

    @Deprecated(message = "Pass the ImeAction to onImeAction instead.")
    /* JADX INFO: renamed from: setImeAction-4L7nppU, reason: not valid java name */
    public static final void m7360setImeAction4L7nppU(SemanticsPropertyReceiver $this$imeAction, int i) {
        SemanticsProperties.INSTANCE.getImeAction().setValue($this$imeAction, $$delegatedProperties[22], ImeAction.m7738boximpl(i));
    }

    public static final boolean getSelected(SemanticsPropertyReceiver $this$selected) {
        return SemanticsProperties.INSTANCE.getSelected().getValue($this$selected, $$delegatedProperties[23]).booleanValue();
    }

    public static final void setSelected(SemanticsPropertyReceiver $this$selected, boolean z) {
        SemanticsProperties.INSTANCE.getSelected().setValue($this$selected, $$delegatedProperties[23], Boolean.valueOf(z));
    }

    public static final CollectionInfo getCollectionInfo(SemanticsPropertyReceiver $this$collectionInfo) {
        return SemanticsProperties.INSTANCE.getCollectionInfo().getValue($this$collectionInfo, $$delegatedProperties[24]);
    }

    public static final void setCollectionInfo(SemanticsPropertyReceiver $this$collectionInfo, CollectionInfo collectionInfo) {
        SemanticsProperties.INSTANCE.getCollectionInfo().setValue($this$collectionInfo, $$delegatedProperties[24], collectionInfo);
    }

    public static final CollectionItemInfo getCollectionItemInfo(SemanticsPropertyReceiver $this$collectionItemInfo) {
        return SemanticsProperties.INSTANCE.getCollectionItemInfo().getValue($this$collectionItemInfo, $$delegatedProperties[25]);
    }

    public static final void setCollectionItemInfo(SemanticsPropertyReceiver $this$collectionItemInfo, CollectionItemInfo collectionItemInfo) {
        SemanticsProperties.INSTANCE.getCollectionItemInfo().setValue($this$collectionItemInfo, $$delegatedProperties[25], collectionItemInfo);
    }

    public static final ToggleableState getToggleableState(SemanticsPropertyReceiver $this$toggleableState) {
        return SemanticsProperties.INSTANCE.getToggleableState().getValue($this$toggleableState, $$delegatedProperties[26]);
    }

    public static final void setToggleableState(SemanticsPropertyReceiver $this$toggleableState, ToggleableState toggleableState) {
        SemanticsProperties.INSTANCE.getToggleableState().setValue($this$toggleableState, $$delegatedProperties[26], toggleableState);
    }

    public static final InputTextSuggestionState getInputTextSuggestionState(SemanticsPropertyReceiver $this$inputTextSuggestionState) {
        return SemanticsProperties.INSTANCE.getInputTextSuggestionState().getValue($this$inputTextSuggestionState, $$delegatedProperties[27]);
    }

    public static final void setInputTextSuggestionState(SemanticsPropertyReceiver $this$inputTextSuggestionState, InputTextSuggestionState inputTextSuggestionState) {
        SemanticsProperties.INSTANCE.getInputTextSuggestionState().setValue($this$inputTextSuggestionState, $$delegatedProperties[27], inputTextSuggestionState);
    }

    public static final boolean isEditable(SemanticsPropertyReceiver $this$isEditable) {
        return SemanticsProperties.INSTANCE.getIsEditable().getValue($this$isEditable, $$delegatedProperties[28]).booleanValue();
    }

    public static final void setEditable(SemanticsPropertyReceiver $this$isEditable, boolean z) {
        SemanticsProperties.INSTANCE.getIsEditable().setValue($this$isEditable, $$delegatedProperties[28], Boolean.valueOf(z));
    }

    public static final void password(SemanticsPropertyReceiver $this$password) {
        $this$password.set(SemanticsProperties.INSTANCE.getPassword(), Unit.INSTANCE);
    }

    public static final void error(SemanticsPropertyReceiver $this$error, String description) {
        $this$error.set(SemanticsProperties.INSTANCE.getError(), description);
    }

    public static final void indexForKey(SemanticsPropertyReceiver $this$indexForKey, Function1<Object, Integer> function1) {
        $this$indexForKey.set(SemanticsProperties.INSTANCE.getIndexForKey(), function1);
    }

    public static final int getMaxTextLength(SemanticsPropertyReceiver $this$maxTextLength) {
        return SemanticsProperties.INSTANCE.getMaxTextLength().getValue($this$maxTextLength, $$delegatedProperties[29]).intValue();
    }

    public static final void setMaxTextLength(SemanticsPropertyReceiver $this$maxTextLength, int i) {
        SemanticsProperties.INSTANCE.getMaxTextLength().setValue($this$maxTextLength, $$delegatedProperties[29], Integer.valueOf(i));
    }

    public static final Shape getShape(SemanticsPropertyReceiver $this$shape) {
        return SemanticsProperties.INSTANCE.getShape().getValue($this$shape, $$delegatedProperties[30]);
    }

    public static final void setShape(SemanticsPropertyReceiver $this$shape, Shape shape) {
        SemanticsProperties.INSTANCE.getShape().setValue($this$shape, $$delegatedProperties[30], shape);
    }

    public static final void selectableGroup(SemanticsPropertyReceiver $this$selectableGroup) {
        $this$selectableGroup.set(SemanticsProperties.INSTANCE.getSelectableGroup(), Unit.INSTANCE);
    }

    public static final List<CustomAccessibilityAction> getCustomActions(SemanticsPropertyReceiver $this$customActions) {
        return SemanticsActions.INSTANCE.getCustomActions().getValue($this$customActions, $$delegatedProperties[31]);
    }

    public static final void setCustomActions(SemanticsPropertyReceiver $this$customActions, List<CustomAccessibilityAction> list) {
        SemanticsActions.INSTANCE.getCustomActions().setValue($this$customActions, $$delegatedProperties[31], list);
    }

    public static /* synthetic */ void getTextLayoutResult$default(SemanticsPropertyReceiver semanticsPropertyReceiver, String str, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        getTextLayoutResult(semanticsPropertyReceiver, str, function1);
    }

    public static final void getTextLayoutResult(SemanticsPropertyReceiver $this$getTextLayoutResult, String label, Function1<? super List<TextLayoutResult>, Boolean> function1) {
        $this$getTextLayoutResult.set(SemanticsActions.INSTANCE.getGetTextLayoutResult(), new AccessibilityAction(label, function1));
    }

    public static /* synthetic */ void onClick$default(SemanticsPropertyReceiver semanticsPropertyReceiver, String str, Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        onClick(semanticsPropertyReceiver, str, function0);
    }

    public static final void onClick(SemanticsPropertyReceiver $this$onClick, String label, Function0<Boolean> function0) {
        $this$onClick.set(SemanticsActions.INSTANCE.getOnClick(), new AccessibilityAction(label, function0));
    }

    public static /* synthetic */ void onLongClick$default(SemanticsPropertyReceiver semanticsPropertyReceiver, String str, Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        onLongClick(semanticsPropertyReceiver, str, function0);
    }

    public static final void onLongClick(SemanticsPropertyReceiver $this$onLongClick, String label, Function0<Boolean> function0) {
        $this$onLongClick.set(SemanticsActions.INSTANCE.getOnLongClick(), new AccessibilityAction(label, function0));
    }

    public static /* synthetic */ void scrollBy$default(SemanticsPropertyReceiver semanticsPropertyReceiver, String str, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        scrollBy(semanticsPropertyReceiver, str, function2);
    }

    public static final void scrollBy(SemanticsPropertyReceiver $this$scrollBy, String label, Function2<? super Float, ? super Float, Boolean> function2) {
        $this$scrollBy.set(SemanticsActions.INSTANCE.getScrollBy(), new AccessibilityAction(label, function2));
    }

    public static final void scrollByOffset(SemanticsPropertyReceiver $this$scrollByOffset, Function2<? super Offset, ? super Continuation<? super Offset>, ? extends Object> function2) {
        $this$scrollByOffset.set(SemanticsActions.INSTANCE.getScrollByOffset(), function2);
    }

    public static /* synthetic */ void scrollToIndex$default(SemanticsPropertyReceiver semanticsPropertyReceiver, String str, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        scrollToIndex(semanticsPropertyReceiver, str, function1);
    }

    public static final void scrollToIndex(SemanticsPropertyReceiver $this$scrollToIndex, String label, Function1<? super Integer, Boolean> function1) {
        $this$scrollToIndex.set(SemanticsActions.INSTANCE.getScrollToIndex(), new AccessibilityAction(label, function1));
    }

    public static /* synthetic */ void onAutofillText$default(SemanticsPropertyReceiver semanticsPropertyReceiver, String str, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        onAutofillText(semanticsPropertyReceiver, str, function1);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use onFillData instead", replaceWith = @ReplaceWith(expression = "onFillData", imports = {}))
    public static final void onAutofillText(SemanticsPropertyReceiver $this$onAutofillText, String label, Function1<? super AnnotatedString, Boolean> function1) {
        $this$onAutofillText.set(SemanticsActions.INSTANCE.getOnAutofillText(), new AccessibilityAction(label, function1));
    }

    public static /* synthetic */ void onFillData$default(SemanticsPropertyReceiver semanticsPropertyReceiver, String str, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        onFillData(semanticsPropertyReceiver, str, function1);
    }

    public static final void onFillData(SemanticsPropertyReceiver $this$onFillData, String label, Function1<? super FillableData, Boolean> function1) {
        $this$onFillData.set(SemanticsActions.INSTANCE.getOnFillData(), new AccessibilityAction(label, function1));
    }

    public static /* synthetic */ void setProgress$default(SemanticsPropertyReceiver semanticsPropertyReceiver, String str, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        setProgress(semanticsPropertyReceiver, str, function1);
    }

    public static final void setProgress(SemanticsPropertyReceiver $this$setProgress, String label, Function1<? super Float, Boolean> function1) {
        $this$setProgress.set(SemanticsActions.INSTANCE.getSetProgress(), new AccessibilityAction(label, function1));
    }

    public static /* synthetic */ void setText$default(SemanticsPropertyReceiver semanticsPropertyReceiver, String str, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        setText(semanticsPropertyReceiver, str, function1);
    }

    public static final void setText(SemanticsPropertyReceiver $this$setText, String label, Function1<? super AnnotatedString, Boolean> function1) {
        $this$setText.set(SemanticsActions.INSTANCE.getSetText(), new AccessibilityAction(label, function1));
    }

    public static /* synthetic */ void setTextSubstitution$default(SemanticsPropertyReceiver semanticsPropertyReceiver, String str, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        setTextSubstitution(semanticsPropertyReceiver, str, function1);
    }

    public static final void setTextSubstitution(SemanticsPropertyReceiver $this$setTextSubstitution, String label, Function1<? super AnnotatedString, Boolean> function1) {
        $this$setTextSubstitution.set(SemanticsActions.INSTANCE.getSetTextSubstitution(), new AccessibilityAction(label, function1));
    }

    public static /* synthetic */ void showTextSubstitution$default(SemanticsPropertyReceiver semanticsPropertyReceiver, String str, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        showTextSubstitution(semanticsPropertyReceiver, str, function1);
    }

    public static final void showTextSubstitution(SemanticsPropertyReceiver $this$showTextSubstitution, String label, Function1<? super Boolean, Boolean> function1) {
        $this$showTextSubstitution.set(SemanticsActions.INSTANCE.getShowTextSubstitution(), new AccessibilityAction(label, function1));
    }

    public static /* synthetic */ void clearTextSubstitution$default(SemanticsPropertyReceiver semanticsPropertyReceiver, String str, Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        clearTextSubstitution(semanticsPropertyReceiver, str, function0);
    }

    public static final void clearTextSubstitution(SemanticsPropertyReceiver $this$clearTextSubstitution, String label, Function0<Boolean> function0) {
        $this$clearTextSubstitution.set(SemanticsActions.INSTANCE.getClearTextSubstitution(), new AccessibilityAction(label, function0));
    }

    public static /* synthetic */ void insertTextAtCursor$default(SemanticsPropertyReceiver semanticsPropertyReceiver, String str, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        insertTextAtCursor(semanticsPropertyReceiver, str, function1);
    }

    public static final void insertTextAtCursor(SemanticsPropertyReceiver $this$insertTextAtCursor, String label, Function1<? super AnnotatedString, Boolean> function1) {
        $this$insertTextAtCursor.set(SemanticsActions.INSTANCE.getInsertTextAtCursor(), new AccessibilityAction(label, function1));
    }

    /* JADX INFO: renamed from: onImeAction-9UiTYpY$default, reason: not valid java name */
    public static /* synthetic */ void m7359onImeAction9UiTYpY$default(SemanticsPropertyReceiver semanticsPropertyReceiver, int i, String str, Function0 function0, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            str = null;
        }
        m7358onImeAction9UiTYpY(semanticsPropertyReceiver, i, str, function0);
    }

    /* JADX INFO: renamed from: onImeAction-9UiTYpY, reason: not valid java name */
    public static final void m7358onImeAction9UiTYpY(SemanticsPropertyReceiver $this$onImeAction_u2d9UiTYpY, int imeActionType, String label, Function0<Boolean> function0) {
        $this$onImeAction_u2d9UiTYpY.set(SemanticsProperties.INSTANCE.getImeAction(), ImeAction.m7738boximpl(imeActionType));
        $this$onImeAction_u2d9UiTYpY.set(SemanticsActions.INSTANCE.getOnImeAction(), new AccessibilityAction(label, function0));
    }

    public static /* synthetic */ void performImeAction$default(SemanticsPropertyReceiver semanticsPropertyReceiver, String str, Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        performImeAction(semanticsPropertyReceiver, str, function0);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use `SemanticsPropertyReceiver.onImeAction` instead.", replaceWith = @ReplaceWith(expression = "onImeAction(imeActionType = ImeAction.Default, label = label, action = action)", imports = {"androidx.compose.ui.semantics.onImeAction", "androidx.compose.ui.text.input.ImeAction"}))
    public static final void performImeAction(SemanticsPropertyReceiver $this$performImeAction, String label, Function0<Boolean> function0) {
        $this$performImeAction.set(SemanticsActions.INSTANCE.getOnImeAction(), new AccessibilityAction(label, function0));
    }

    public static /* synthetic */ void setSelection$default(SemanticsPropertyReceiver semanticsPropertyReceiver, String str, Function3 function3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        setSelection(semanticsPropertyReceiver, str, function3);
    }

    public static final void setSelection(SemanticsPropertyReceiver $this$setSelection, String label, Function3<? super Integer, ? super Integer, ? super Boolean, Boolean> function3) {
        $this$setSelection.set(SemanticsActions.INSTANCE.getSetSelection(), new AccessibilityAction(label, function3));
    }

    public static /* synthetic */ void copyText$default(SemanticsPropertyReceiver semanticsPropertyReceiver, String str, Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        copyText(semanticsPropertyReceiver, str, function0);
    }

    public static final void copyText(SemanticsPropertyReceiver $this$copyText, String label, Function0<Boolean> function0) {
        $this$copyText.set(SemanticsActions.INSTANCE.getCopyText(), new AccessibilityAction(label, function0));
    }

    public static /* synthetic */ void cutText$default(SemanticsPropertyReceiver semanticsPropertyReceiver, String str, Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        cutText(semanticsPropertyReceiver, str, function0);
    }

    public static final void cutText(SemanticsPropertyReceiver $this$cutText, String label, Function0<Boolean> function0) {
        $this$cutText.set(SemanticsActions.INSTANCE.getCutText(), new AccessibilityAction(label, function0));
    }

    public static /* synthetic */ void pasteText$default(SemanticsPropertyReceiver semanticsPropertyReceiver, String str, Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        pasteText(semanticsPropertyReceiver, str, function0);
    }

    public static final void pasteText(SemanticsPropertyReceiver $this$pasteText, String label, Function0<Boolean> function0) {
        $this$pasteText.set(SemanticsActions.INSTANCE.getPasteText(), new AccessibilityAction(label, function0));
    }

    public static /* synthetic */ void expand$default(SemanticsPropertyReceiver semanticsPropertyReceiver, String str, Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        expand(semanticsPropertyReceiver, str, function0);
    }

    public static final void expand(SemanticsPropertyReceiver $this$expand, String label, Function0<Boolean> function0) {
        $this$expand.set(SemanticsActions.INSTANCE.getExpand(), new AccessibilityAction(label, function0));
    }

    public static /* synthetic */ void collapse$default(SemanticsPropertyReceiver semanticsPropertyReceiver, String str, Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        collapse(semanticsPropertyReceiver, str, function0);
    }

    public static final void collapse(SemanticsPropertyReceiver $this$collapse, String label, Function0<Boolean> function0) {
        $this$collapse.set(SemanticsActions.INSTANCE.getCollapse(), new AccessibilityAction(label, function0));
    }

    public static /* synthetic */ void dismiss$default(SemanticsPropertyReceiver semanticsPropertyReceiver, String str, Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        dismiss(semanticsPropertyReceiver, str, function0);
    }

    public static final void dismiss(SemanticsPropertyReceiver $this$dismiss, String label, Function0<Boolean> function0) {
        $this$dismiss.set(SemanticsActions.INSTANCE.getDismiss(), new AccessibilityAction(label, function0));
    }

    public static /* synthetic */ void requestFocus$default(SemanticsPropertyReceiver semanticsPropertyReceiver, String str, Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        requestFocus(semanticsPropertyReceiver, str, function0);
    }

    public static final void requestFocus(SemanticsPropertyReceiver $this$requestFocus, String label, Function0<Boolean> function0) {
        $this$requestFocus.set(SemanticsActions.INSTANCE.getRequestFocus(), new AccessibilityAction(label, function0));
    }

    public static /* synthetic */ void pageUp$default(SemanticsPropertyReceiver semanticsPropertyReceiver, String str, Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        pageUp(semanticsPropertyReceiver, str, function0);
    }

    public static final void pageUp(SemanticsPropertyReceiver $this$pageUp, String label, Function0<Boolean> function0) {
        $this$pageUp.set(SemanticsActions.INSTANCE.getPageUp(), new AccessibilityAction(label, function0));
    }

    public static /* synthetic */ void pageDown$default(SemanticsPropertyReceiver semanticsPropertyReceiver, String str, Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        pageDown(semanticsPropertyReceiver, str, function0);
    }

    public static final void pageDown(SemanticsPropertyReceiver $this$pageDown, String label, Function0<Boolean> function0) {
        $this$pageDown.set(SemanticsActions.INSTANCE.getPageDown(), new AccessibilityAction(label, function0));
    }

    public static /* synthetic */ void pageLeft$default(SemanticsPropertyReceiver semanticsPropertyReceiver, String str, Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        pageLeft(semanticsPropertyReceiver, str, function0);
    }

    public static final void pageLeft(SemanticsPropertyReceiver $this$pageLeft, String label, Function0<Boolean> function0) {
        $this$pageLeft.set(SemanticsActions.INSTANCE.getPageLeft(), new AccessibilityAction(label, function0));
    }

    public static /* synthetic */ void pageRight$default(SemanticsPropertyReceiver semanticsPropertyReceiver, String str, Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        pageRight(semanticsPropertyReceiver, str, function0);
    }

    public static final void pageRight(SemanticsPropertyReceiver $this$pageRight, String label, Function0<Boolean> function0) {
        $this$pageRight.set(SemanticsActions.INSTANCE.getPageRight(), new AccessibilityAction(label, function0));
    }

    public static /* synthetic */ void getScrollViewportLength$default(SemanticsPropertyReceiver semanticsPropertyReceiver, String str, Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        getScrollViewportLength(semanticsPropertyReceiver, str, function0);
    }

    public static final void getScrollViewportLength(SemanticsPropertyReceiver $this$getScrollViewportLength, String label, final Function0<Float> function0) {
        $this$getScrollViewportLength.set(SemanticsActions.INSTANCE.getGetScrollViewportLength(), new AccessibilityAction(label, new Function1<List<Float>, Boolean>() { // from class: androidx.compose.ui.semantics.SemanticsPropertiesKt.getScrollViewportLength.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(List<Float> list) {
                boolean z;
                Float viewport = function0.invoke();
                if (viewport == null) {
                    z = false;
                } else {
                    list.add(viewport);
                    z = true;
                }
                return Boolean.valueOf(z);
            }
        }));
    }
}
