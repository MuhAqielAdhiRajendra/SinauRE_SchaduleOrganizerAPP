package androidx.compose.ui.text;

import androidx.autofill.HintConstants;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.runtime.saveable.SaverKt;
import androidx.compose.runtime.saveable.SaverScope;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.LinkAnnotation;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontSynthesis;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.intl.Locale;
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
import androidx.compose.ui.text.style.TextMotion;
import androidx.compose.ui.unit.TextUnit;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.compose.ui.unit.TextUnitType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Savers.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\u0080\u0003\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001aM\u0010\u0000\u001a\u00020\u0001\"\u0014\b\u0000\u0010\u0002*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0003\"\u0004\b\u0001\u0010\u0004\"\b\b\u0002\u0010\u0005*\u00020\u00012\b\u0010\u0006\u001a\u0004\u0018\u0001H\u00042\u0006\u0010\u0007\u001a\u0002H\u00022\u0006\u0010\b\u001a\u00020\tH\u0000¢\u0006\u0002\u0010\n\u001aP\u0010\u000b\u001a\u0004\u0018\u0001H\f\"\u0014\b\u0000\u0010\u0002*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0003\"\u0004\b\u0001\u0010\u0004\"\b\b\u0002\u0010\u0005*\u00020\u0001\"\u0006\b\u0003\u0010\f\u0018\u00012\b\u0010\u0006\u001a\u0004\u0018\u0001H\u00052\u0006\u0010\u0007\u001a\u0002H\u0002H\u0080\b¢\u0006\u0002\u0010\r\u001ay\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u000f\"\u0004\b\u0000\u0010\u0004\"\b\b\u0001\u0010\u0005*\u00020\u00012.\u0010\u0000\u001a*\u0012\u0004\u0012\u00020\t\u0012\u0013\u0012\u0011H\u0004¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0006\u0012\u0006\u0012\u0004\u0018\u0001H\u00050\u0010¢\u0006\u0002\b\u00132#\u0010\u000b\u001a\u001f\u0012\u0013\u0012\u0011H\u0005¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0006\u0012\u0006\u0012\u0004\u0018\u0001H\u00040\u0014H\u0002\u001a\u001f\u0010\u0000\u001a\u0004\u0018\u0001H\u0002\"\u0004\b\u0000\u0010\u00022\b\u0010\u0006\u001a\u0004\u0018\u0001H\u0002H\u0000¢\u0006\u0002\u0010\u0015\u001a\"\u0010\u000b\u001a\u0004\u0018\u0001H\f\"\u0006\b\u0000\u0010\f\u0018\u00012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001H\u0080\b¢\u0006\u0002\u0010\u0015\" \u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00010\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"(\u0010\u001a\u001a\u001c\u0012\u0012\u0012\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u001c0\u001b\u0012\u0004\u0012\u00020\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\"(\u0010\u001d\u001a\u0016\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u001c\u0012\u0004\u0012\u00020\u00010\u0003X\u0082\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u001e\u0010\u001f\"\u001a\u0010 \u001a\u000e\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\" \u0010\"\u001a\u000e\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020\u00010\u0003X\u0082\u0004¢\u0006\b\n\u0000\u0012\u0004\b$\u0010\u001f\"\u001a\u0010%\u001a\u000e\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\"\u001a\u0010'\u001a\u000e\u0012\u0004\u0012\u00020(\u0012\u0004\u0012\u00020\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\" \u0010)\u001a\u000e\u0012\u0004\u0012\u00020*\u0012\u0004\u0012\u00020\u00010\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b+\u0010\u0019\" \u0010,\u001a\u000e\u0012\u0004\u0012\u00020-\u0012\u0004\u0012\u00020\u00010\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b.\u0010\u0019\" \u0010/\u001a\u000e\u0012\u0004\u0012\u000200\u0012\u0004\u0012\u00020\u00010\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b1\u0010\u0019\"$\u00102\u001a\u000e\u0012\u0004\u0012\u000203\u0012\u0004\u0012\u00020\u00010\u0003*\u0002048@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b5\u00106\"\u001a\u00107\u001a\u000e\u0012\u0004\u0012\u000203\u0012\u0004\u0012\u00020\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\"$\u00102\u001a\u000e\u0012\u0004\u0012\u000208\u0012\u0004\u0012\u00020\u00010\u0003*\u0002098@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b5\u0010:\"\u001a\u0010;\u001a\u000e\u0012\u0004\u0012\u000208\u0012\u0004\u0012\u00020\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\"$\u00102\u001a\u000e\u0012\u0004\u0012\u00020<\u0012\u0004\u0012\u00020\u00010\u0003*\u00020=8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b5\u0010>\"\u001a\u0010?\u001a\u000e\u0012\u0004\u0012\u00020<\u0012\u0004\u0012\u00020\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\"$\u00102\u001a\u000e\u0012\u0004\u0012\u00020@\u0012\u0004\u0012\u00020\u00010\u0003*\u00020A8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b5\u0010B\"\u001a\u0010C\u001a\u000e\u0012\u0004\u0012\u00020@\u0012\u0004\u0012\u00020\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\"$\u00102\u001a\u000e\u0012\u0004\u0012\u00020D\u0012\u0004\u0012\u00020\u00010\u0003*\u00020E8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b5\u0010F\"\u001a\u0010G\u001a\u000e\u0012\u0004\u0012\u00020D\u0012\u0004\u0012\u00020\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\"$\u00102\u001a\u000e\u0012\u0004\u0012\u00020H\u0012\u0004\u0012\u00020\u00010\u0003*\u00020I8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b5\u0010J\"\u001a\u0010K\u001a\u000e\u0012\u0004\u0012\u00020H\u0012\u0004\u0012\u00020\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\"$\u00102\u001a\u000e\u0012\u0004\u0012\u00020L\u0012\u0004\u0012\u00020\u00010\u0003*\u00020M8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b5\u0010N\"\u001a\u0010O\u001a\u000e\u0012\u0004\u0012\u00020L\u0012\u0004\u0012\u00020\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\"$\u00102\u001a\u000e\u0012\u0004\u0012\u00020P\u0012\u0004\u0012\u00020\u00010\u0003*\u00020Q8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b5\u0010R\"\u001a\u0010S\u001a\u000e\u0012\u0004\u0012\u00020P\u0012\u0004\u0012\u00020\u00010\u000fX\u0082\u0004¢\u0006\u0002\n\u0000\"$\u00102\u001a\u000e\u0012\u0004\u0012\u00020T\u0012\u0004\u0012\u00020\u00010\u0003*\u00020U8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b5\u0010V\"\u001a\u0010W\u001a\u000e\u0012\u0004\u0012\u00020T\u0012\u0004\u0012\u00020\u00010\u000fX\u0082\u0004¢\u0006\u0002\n\u0000\"$\u00102\u001a\u000e\u0012\u0004\u0012\u00020X\u0012\u0004\u0012\u00020\u00010\u0003*\u00020Y8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b5\u0010Z\"\u001a\u0010[\u001a\u000e\u0012\u0004\u0012\u00020X\u0012\u0004\u0012\u00020\u00010\u000fX\u0082\u0004¢\u0006\u0002\n\u0000\"$\u00102\u001a\u000e\u0012\u0004\u0012\u00020\\\u0012\u0004\u0012\u00020\u00010\u0003*\u00020]8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b5\u0010^\"\u001a\u0010_\u001a\u000e\u0012\u0004\u0012\u00020\\\u0012\u0004\u0012\u00020\u00010\u000fX\u0082\u0004¢\u0006\u0002\n\u0000\"$\u00102\u001a\u000e\u0012\u0004\u0012\u00020`\u0012\u0004\u0012\u00020\u00010\u0003*\u00020a8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b5\u0010b\" \u0010c\u001a\u000e\u0012\u0004\u0012\u00020`\u0012\u0004\u0012\u00020\u00010\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\bd\u0010\u0019\"$\u00102\u001a\u000e\u0012\u0004\u0012\u00020e\u0012\u0004\u0012\u00020\u00010\u0003*\u00020f8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b5\u0010g\" \u0010h\u001a\u000e\u0012\u0004\u0012\u00020e\u0012\u0004\u0012\u00020\u00010\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\bi\u0010\u0019\"$\u00102\u001a\u000e\u0012\u0004\u0012\u00020j\u0012\u0004\u0012\u00020\u00010\u0003*\u00020k8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b5\u0010l\"\u001a\u0010m\u001a\u000e\u0012\u0004\u0012\u00020j\u0012\u0004\u0012\u00020\u00010\u000fX\u0082\u0004¢\u0006\u0002\n\u0000\"$\u00102\u001a\u000e\u0012\u0004\u0012\u00020n\u0012\u0004\u0012\u00020\u00010\u0003*\u00020o8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b5\u0010p\" \u0010q\u001a\u000e\u0012\u0004\u0012\u00020n\u0012\u0004\u0012\u00020\u00010\u000fX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\br\u0010s\"$\u00102\u001a\u000e\u0012\u0004\u0012\u00020t\u0012\u0004\u0012\u00020\u00010\u0003*\u00020u8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b5\u0010v\"\u001a\u0010w\u001a\u000e\u0012\u0004\u0012\u00020t\u0012\u0004\u0012\u00020\u00010\u000fX\u0082\u0004¢\u0006\u0002\n\u0000\"$\u00102\u001a\u000e\u0012\u0004\u0012\u00020x\u0012\u0004\u0012\u00020\u00010\u0003*\u00020y8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b5\u0010z\"\u001a\u0010{\u001a\u000e\u0012\u0004\u0012\u00020x\u0012\u0004\u0012\u00020\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\"$\u00102\u001a\u000e\u0012\u0004\u0012\u00020|\u0012\u0004\u0012\u00020\u00010\u0003*\u00020}8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b5\u0010~\"\u001a\u0010\u007f\u001a\u000e\u0012\u0004\u0012\u00020|\u0012\u0004\u0012\u00020\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\"'\u00102\u001a\u000f\u0012\u0005\u0012\u00030\u0080\u0001\u0012\u0004\u0012\u00020\u00010\u0003*\u00030\u0081\u00018@X\u0080\u0004¢\u0006\u0007\u001a\u0005\b5\u0010\u0082\u0001\"\u001c\u0010\u0083\u0001\u001a\u000f\u0012\u0005\u0012\u00030\u0080\u0001\u0012\u0004\u0012\u00020\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\"'\u00102\u001a\u000f\u0012\u0005\u0012\u00030\u0084\u0001\u0012\u0004\u0012\u00020\u00010\u0003*\u00030\u0085\u00018BX\u0082\u0004¢\u0006\u0007\u001a\u0005\b5\u0010\u0086\u0001\"\u001c\u0010\u0087\u0001\u001a\u000f\u0012\u0005\u0012\u00030\u0084\u0001\u0012\u0004\u0012\u00020\u00010\u000fX\u0082\u0004¢\u0006\u0002\n\u0000\"'\u00102\u001a\u000f\u0012\u0005\u0012\u00030\u0088\u0001\u0012\u0004\u0012\u00020\u00010\u0003*\u00030\u0089\u00018BX\u0082\u0004¢\u0006\u0007\u001a\u0005\b5\u0010\u008a\u0001\"\u001c\u0010\u008b\u0001\u001a\u000f\u0012\u0005\u0012\u00030\u0088\u0001\u0012\u0004\u0012\u00020\u00010\u000fX\u0082\u0004¢\u0006\u0002\n\u0000\"'\u00102\u001a\u000f\u0012\u0005\u0012\u00030\u008c\u0001\u0012\u0004\u0012\u00020\u00010\u0003*\u00030\u008d\u00018BX\u0082\u0004¢\u0006\u0007\u001a\u0005\b5\u0010\u008e\u0001\"\u001c\u0010\u008f\u0001\u001a\u000f\u0012\u0005\u0012\u00030\u008c\u0001\u0012\u0004\u0012\u00020\u00010\u000fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0090\u0001"}, d2 = {"save", "", "T", "Landroidx/compose/runtime/saveable/Saver;", "Original", "Saveable", "value", "saver", "scope", "Landroidx/compose/runtime/saveable/SaverScope;", "(Ljava/lang/Object;Landroidx/compose/runtime/saveable/Saver;Landroidx/compose/runtime/saveable/SaverScope;)Ljava/lang/Object;", "restore", "Result", "(Ljava/lang/Object;Landroidx/compose/runtime/saveable/Saver;)Ljava/lang/Object;", "NonNullValueClassSaver", "Landroidx/compose/ui/text/NonNullValueClassSaver;", "Lkotlin/Function2;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "Lkotlin/ExtensionFunctionType;", "Lkotlin/Function1;", "(Ljava/lang/Object;)Ljava/lang/Object;", "AnnotatedStringSaver", "Landroidx/compose/ui/text/AnnotatedString;", "getAnnotatedStringSaver", "()Landroidx/compose/runtime/saveable/Saver;", "AnnotationRangeListSaver", "", "Landroidx/compose/ui/text/AnnotatedString$Range;", "AnnotationRangeSaver", "getAnnotationRangeSaver$annotations", "()V", "VerbatimTtsAnnotationSaver", "Landroidx/compose/ui/text/VerbatimTtsAnnotation;", "UrlAnnotationSaver", "Landroidx/compose/ui/text/UrlAnnotation;", "getUrlAnnotationSaver$annotations", "LinkSaver", "Landroidx/compose/ui/text/LinkAnnotation$Url;", "ClickableSaver", "Landroidx/compose/ui/text/LinkAnnotation$Clickable;", "ParagraphStyleSaver", "Landroidx/compose/ui/text/ParagraphStyle;", "getParagraphStyleSaver", "SpanStyleSaver", "Landroidx/compose/ui/text/SpanStyle;", "getSpanStyleSaver", "TextLinkStylesSaver", "Landroidx/compose/ui/text/TextLinkStyles;", "getTextLinkStylesSaver", "Saver", "Landroidx/compose/ui/text/style/TextDecoration;", "Landroidx/compose/ui/text/style/TextDecoration$Companion;", "getSaver", "(Landroidx/compose/ui/text/style/TextDecoration$Companion;)Landroidx/compose/runtime/saveable/Saver;", "TextDecorationSaver", "Landroidx/compose/ui/text/style/TextGeometricTransform;", "Landroidx/compose/ui/text/style/TextGeometricTransform$Companion;", "(Landroidx/compose/ui/text/style/TextGeometricTransform$Companion;)Landroidx/compose/runtime/saveable/Saver;", "TextGeometricTransformSaver", "Landroidx/compose/ui/text/style/TextIndent;", "Landroidx/compose/ui/text/style/TextIndent$Companion;", "(Landroidx/compose/ui/text/style/TextIndent$Companion;)Landroidx/compose/runtime/saveable/Saver;", "TextIndentSaver", "Landroidx/compose/ui/text/font/FontWeight;", "Landroidx/compose/ui/text/font/FontWeight$Companion;", "(Landroidx/compose/ui/text/font/FontWeight$Companion;)Landroidx/compose/runtime/saveable/Saver;", "FontWeightSaver", "Landroidx/compose/ui/text/style/BaselineShift;", "Landroidx/compose/ui/text/style/BaselineShift$Companion;", "(Landroidx/compose/ui/text/style/BaselineShift$Companion;)Landroidx/compose/runtime/saveable/Saver;", "BaselineShiftSaver", "Landroidx/compose/ui/text/TextRange;", "Landroidx/compose/ui/text/TextRange$Companion;", "(Landroidx/compose/ui/text/TextRange$Companion;)Landroidx/compose/runtime/saveable/Saver;", "TextRangeSaver", "Landroidx/compose/ui/graphics/Shadow;", "Landroidx/compose/ui/graphics/Shadow$Companion;", "(Landroidx/compose/ui/graphics/Shadow$Companion;)Landroidx/compose/runtime/saveable/Saver;", "ShadowSaver", "Landroidx/compose/ui/graphics/Color;", "Landroidx/compose/ui/graphics/Color$Companion;", "(Landroidx/compose/ui/graphics/Color$Companion;)Landroidx/compose/runtime/saveable/Saver;", "ColorSaver", "Landroidx/compose/ui/text/style/TextAlign;", "Landroidx/compose/ui/text/style/TextAlign$Companion;", "(Landroidx/compose/ui/text/style/TextAlign$Companion;)Landroidx/compose/runtime/saveable/Saver;", "TextAlignSaver", "Landroidx/compose/ui/text/style/TextDirection;", "Landroidx/compose/ui/text/style/TextDirection$Companion;", "(Landroidx/compose/ui/text/style/TextDirection$Companion;)Landroidx/compose/runtime/saveable/Saver;", "TextDirectionSaver", "Landroidx/compose/ui/text/style/Hyphens;", "Landroidx/compose/ui/text/style/Hyphens$Companion;", "(Landroidx/compose/ui/text/style/Hyphens$Companion;)Landroidx/compose/runtime/saveable/Saver;", "HyphensSaver", "Landroidx/compose/ui/text/font/FontStyle;", "Landroidx/compose/ui/text/font/FontStyle$Companion;", "(Landroidx/compose/ui/text/font/FontStyle$Companion;)Landroidx/compose/runtime/saveable/Saver;", "FontStyleSaver", "getFontStyleSaver", "Landroidx/compose/ui/text/font/FontSynthesis;", "Landroidx/compose/ui/text/font/FontSynthesis$Companion;", "(Landroidx/compose/ui/text/font/FontSynthesis$Companion;)Landroidx/compose/runtime/saveable/Saver;", "FontSynthesisSaver", "getFontSynthesisSaver", "Landroidx/compose/ui/unit/TextUnit;", "Landroidx/compose/ui/unit/TextUnit$Companion;", "(Landroidx/compose/ui/unit/TextUnit$Companion;)Landroidx/compose/runtime/saveable/Saver;", "TextUnitSaver", "Landroidx/compose/ui/unit/TextUnitType;", "Landroidx/compose/ui/unit/TextUnitType$Companion;", "(Landroidx/compose/ui/unit/TextUnitType$Companion;)Landroidx/compose/runtime/saveable/Saver;", "TextUnitTypeSaver", "getTextUnitTypeSaver", "()Landroidx/compose/ui/text/NonNullValueClassSaver;", "Landroidx/compose/ui/geometry/Offset;", "Landroidx/compose/ui/geometry/Offset$Companion;", "(Landroidx/compose/ui/geometry/Offset$Companion;)Landroidx/compose/runtime/saveable/Saver;", "OffsetSaver", "Landroidx/compose/ui/text/intl/LocaleList;", "Landroidx/compose/ui/text/intl/LocaleList$Companion;", "(Landroidx/compose/ui/text/intl/LocaleList$Companion;)Landroidx/compose/runtime/saveable/Saver;", "LocaleListSaver", "Landroidx/compose/ui/text/intl/Locale;", "Landroidx/compose/ui/text/intl/Locale$Companion;", "(Landroidx/compose/ui/text/intl/Locale$Companion;)Landroidx/compose/runtime/saveable/Saver;", "LocaleSaver", "Landroidx/compose/ui/text/style/LineHeightStyle;", "Landroidx/compose/ui/text/style/LineHeightStyle$Companion;", "(Landroidx/compose/ui/text/style/LineHeightStyle$Companion;)Landroidx/compose/runtime/saveable/Saver;", "LineHeightStyleSaver", "Landroidx/compose/ui/text/style/LineHeightStyle$Alignment;", "Landroidx/compose/ui/text/style/LineHeightStyle$Alignment$Companion;", "(Landroidx/compose/ui/text/style/LineHeightStyle$Alignment$Companion;)Landroidx/compose/runtime/saveable/Saver;", "LineHeightStyleAlignmentSaver", "Landroidx/compose/ui/text/style/LineHeightStyle$Trim;", "Landroidx/compose/ui/text/style/LineHeightStyle$Trim$Companion;", "(Landroidx/compose/ui/text/style/LineHeightStyle$Trim$Companion;)Landroidx/compose/runtime/saveable/Saver;", "LineHeightStyleTrimSaver", "Landroidx/compose/ui/text/style/LineHeightStyle$Mode;", "Landroidx/compose/ui/text/style/LineHeightStyle$Mode$Companion;", "(Landroidx/compose/ui/text/style/LineHeightStyle$Mode$Companion;)Landroidx/compose/runtime/saveable/Saver;", "LineHeightStyleModeSaver", "ui-text"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class SaversKt {
    private static final Saver<AnnotatedString, Object> AnnotatedStringSaver = SaverKt.Saver(new Function2() { // from class: androidx.compose.ui.text.SaversKt$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            AnnotatedString annotatedString = (AnnotatedString) obj2;
            return CollectionsKt.arrayListOf(SaversKt.save(annotatedString.getText()), SaversKt.save(annotatedString.getAnnotations$ui_text(), SaversKt.AnnotationRangeListSaver, (SaverScope) obj));
        }
    }, new Function1() { // from class: androidx.compose.ui.text.SaversKt$$ExternalSyntheticLambda11
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return SaversKt.AnnotatedStringSaver$lambda$1(obj);
        }
    });
    private static final Saver<List<AnnotatedString.Range<? extends Object>>, Object> AnnotationRangeListSaver = SaverKt.Saver(new Function2() { // from class: androidx.compose.ui.text.SaversKt$$ExternalSyntheticLambda22
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return SaversKt.AnnotationRangeListSaver$lambda$0((SaverScope) obj, (List) obj2);
        }
    }, new Function1() { // from class: androidx.compose.ui.text.SaversKt$$ExternalSyntheticLambda33
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return SaversKt.AnnotationRangeListSaver$lambda$1(obj);
        }
    });
    private static final Saver<AnnotatedString.Range<? extends Object>, Object> AnnotationRangeSaver = SaverKt.Saver(new Function2() { // from class: androidx.compose.ui.text.SaversKt$$ExternalSyntheticLambda44
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return SaversKt.AnnotationRangeSaver$lambda$0((SaverScope) obj, (AnnotatedString.Range) obj2);
        }
    }, new Function1() { // from class: androidx.compose.ui.text.SaversKt$$ExternalSyntheticLambda55
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return SaversKt.AnnotationRangeSaver$lambda$1(obj);
        }
    });
    private static final Saver<VerbatimTtsAnnotation, Object> VerbatimTtsAnnotationSaver = SaverKt.Saver(new Function2() { // from class: androidx.compose.ui.text.SaversKt$$ExternalSyntheticLambda58
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return SaversKt.save(((VerbatimTtsAnnotation) obj2).getVerbatim());
        }
    }, new Function1() { // from class: androidx.compose.ui.text.SaversKt$$ExternalSyntheticLambda59
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return SaversKt.VerbatimTtsAnnotationSaver$lambda$1(obj);
        }
    });
    private static final Saver<UrlAnnotation, Object> UrlAnnotationSaver = SaverKt.Saver(new Function2() { // from class: androidx.compose.ui.text.SaversKt$$ExternalSyntheticLambda60
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return SaversKt.save(((UrlAnnotation) obj2).getUrl());
        }
    }, new Function1() { // from class: androidx.compose.ui.text.SaversKt$$ExternalSyntheticLambda61
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return SaversKt.UrlAnnotationSaver$lambda$1(obj);
        }
    });
    private static final Saver<LinkAnnotation.Url, Object> LinkSaver = SaverKt.Saver(new Function2() { // from class: androidx.compose.ui.text.SaversKt$$ExternalSyntheticLambda1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            LinkAnnotation.Url url = (LinkAnnotation.Url) obj2;
            return CollectionsKt.arrayListOf(SaversKt.save(url.getUrl()), SaversKt.save(url.getStyles(), SaversKt.TextLinkStylesSaver, (SaverScope) obj));
        }
    }, new Function1() { // from class: androidx.compose.ui.text.SaversKt$$ExternalSyntheticLambda2
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return SaversKt.LinkSaver$lambda$1(obj);
        }
    });
    private static final Saver<LinkAnnotation.Clickable, Object> ClickableSaver = SaverKt.Saver(new Function2() { // from class: androidx.compose.ui.text.SaversKt$$ExternalSyntheticLambda3
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            LinkAnnotation.Clickable clickable = (LinkAnnotation.Clickable) obj2;
            return CollectionsKt.arrayListOf(SaversKt.save(clickable.getTag()), SaversKt.save(clickable.getStyles(), SaversKt.TextLinkStylesSaver, (SaverScope) obj));
        }
    }, new Function1() { // from class: androidx.compose.ui.text.SaversKt$$ExternalSyntheticLambda4
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return SaversKt.ClickableSaver$lambda$1(obj);
        }
    });
    private static final Saver<ParagraphStyle, Object> ParagraphStyleSaver = SaverKt.Saver(new Function2() { // from class: androidx.compose.ui.text.SaversKt$$ExternalSyntheticLambda5
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            SaverScope saverScope = (SaverScope) obj;
            ParagraphStyle paragraphStyle = (ParagraphStyle) obj2;
            return CollectionsKt.arrayListOf(SaversKt.save(TextAlign.m7996boximpl(paragraphStyle.getTextAlign()), SaversKt.getSaver(TextAlign.INSTANCE), saverScope), SaversKt.save(TextDirection.m8013boximpl(paragraphStyle.getTextDirection()), SaversKt.getSaver(TextDirection.INSTANCE), saverScope), SaversKt.save(TextUnit.m8334boximpl(paragraphStyle.getLineHeight()), SaversKt.getSaver(TextUnit.INSTANCE), saverScope), SaversKt.save(paragraphStyle.getTextIndent(), SaversKt.getSaver(TextIndent.INSTANCE), saverScope), SaversKt.save(paragraphStyle.getPlatformStyle(), Savers_androidKt.getSaver(PlatformParagraphStyle.INSTANCE), saverScope), SaversKt.save(paragraphStyle.getLineHeightStyle(), SaversKt.getSaver(LineHeightStyle.INSTANCE), saverScope), SaversKt.save(LineBreak.m7901boximpl(paragraphStyle.getLineBreak()), Savers_androidKt.getSaver(LineBreak.INSTANCE), saverScope), SaversKt.save(Hyphens.m7888boximpl(paragraphStyle.getHyphens()), SaversKt.getSaver(Hyphens.INSTANCE), saverScope), SaversKt.save(paragraphStyle.getTextMotion(), Savers_androidKt.getSaver(TextMotion.INSTANCE), saverScope));
        }
    }, new Function1() { // from class: androidx.compose.ui.text.SaversKt$$ExternalSyntheticLambda6
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return SaversKt.ParagraphStyleSaver$lambda$1(obj);
        }
    });
    private static final Saver<SpanStyle, Object> SpanStyleSaver = SaverKt.Saver(new Function2() { // from class: androidx.compose.ui.text.SaversKt$$ExternalSyntheticLambda7
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            SaverScope saverScope = (SaverScope) obj;
            SpanStyle spanStyle = (SpanStyle) obj2;
            return CollectionsKt.arrayListOf(SaversKt.save(Color.m5303boximpl(spanStyle.m7514getColor0d7_KjU()), SaversKt.getSaver(Color.INSTANCE), saverScope), SaversKt.save(TextUnit.m8334boximpl(spanStyle.getFontSize()), SaversKt.getSaver(TextUnit.INSTANCE), saverScope), SaversKt.save(spanStyle.getFontWeight(), SaversKt.getSaver(FontWeight.INSTANCE), saverScope), SaversKt.save(spanStyle.getFontStyle(), SaversKt.getSaver(FontStyle.INSTANCE), saverScope), SaversKt.save(spanStyle.getFontSynthesis(), SaversKt.getSaver(FontSynthesis.INSTANCE), saverScope), SaversKt.save(-1), SaversKt.save(spanStyle.getFontFeatureSettings()), SaversKt.save(TextUnit.m8334boximpl(spanStyle.getLetterSpacing()), SaversKt.getSaver(TextUnit.INSTANCE), saverScope), SaversKt.save(spanStyle.getBaselineShift(), SaversKt.getSaver(BaselineShift.INSTANCE), saverScope), SaversKt.save(spanStyle.getTextGeometricTransform(), SaversKt.getSaver(TextGeometricTransform.INSTANCE), saverScope), SaversKt.save(spanStyle.getLocaleList(), SaversKt.getSaver(LocaleList.INSTANCE), saverScope), SaversKt.save(Color.m5303boximpl(spanStyle.getBackground()), SaversKt.getSaver(Color.INSTANCE), saverScope), SaversKt.save(spanStyle.getTextDecoration(), SaversKt.getSaver(TextDecoration.INSTANCE), saverScope), SaversKt.save(spanStyle.getShadow(), SaversKt.getSaver(Shadow.INSTANCE), saverScope));
        }
    }, new Function1() { // from class: androidx.compose.ui.text.SaversKt$$ExternalSyntheticLambda8
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return SaversKt.SpanStyleSaver$lambda$1(obj);
        }
    });
    private static final Saver<TextLinkStyles, Object> TextLinkStylesSaver = SaverKt.Saver(new Function2() { // from class: androidx.compose.ui.text.SaversKt$$ExternalSyntheticLambda9
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            SaverScope saverScope = (SaverScope) obj;
            TextLinkStyles textLinkStyles = (TextLinkStyles) obj2;
            return CollectionsKt.arrayListOf(SaversKt.save(textLinkStyles.getStyle(), SaversKt.SpanStyleSaver, saverScope), SaversKt.save(textLinkStyles.getFocusedStyle(), SaversKt.SpanStyleSaver, saverScope), SaversKt.save(textLinkStyles.getHoveredStyle(), SaversKt.SpanStyleSaver, saverScope), SaversKt.save(textLinkStyles.getPressedStyle(), SaversKt.SpanStyleSaver, saverScope));
        }
    }, new Function1() { // from class: androidx.compose.ui.text.SaversKt$$ExternalSyntheticLambda10
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return SaversKt.TextLinkStylesSaver$lambda$1(obj);
        }
    });
    private static final Saver<TextDecoration, Object> TextDecorationSaver = SaverKt.Saver(new Function2() { // from class: androidx.compose.ui.text.SaversKt$$ExternalSyntheticLambda12
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return Integer.valueOf(((TextDecoration) obj2).getMask());
        }
    }, new Function1() { // from class: androidx.compose.ui.text.SaversKt$$ExternalSyntheticLambda13
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return SaversKt.TextDecorationSaver$lambda$1(obj);
        }
    });
    private static final Saver<TextGeometricTransform, Object> TextGeometricTransformSaver = SaverKt.Saver(new Function2() { // from class: androidx.compose.ui.text.SaversKt$$ExternalSyntheticLambda14
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            TextGeometricTransform textGeometricTransform = (TextGeometricTransform) obj2;
            return CollectionsKt.arrayListOf(Float.valueOf(textGeometricTransform.getScaleX()), Float.valueOf(textGeometricTransform.getSkewX()));
        }
    }, new Function1() { // from class: androidx.compose.ui.text.SaversKt$$ExternalSyntheticLambda15
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return SaversKt.TextGeometricTransformSaver$lambda$1(obj);
        }
    });
    private static final Saver<TextIndent, Object> TextIndentSaver = SaverKt.Saver(new Function2() { // from class: androidx.compose.ui.text.SaversKt$$ExternalSyntheticLambda16
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            SaverScope saverScope = (SaverScope) obj;
            TextIndent textIndent = (TextIndent) obj2;
            return CollectionsKt.arrayListOf(SaversKt.save(TextUnit.m8334boximpl(textIndent.getFirstLine()), SaversKt.getSaver(TextUnit.INSTANCE), saverScope), SaversKt.save(TextUnit.m8334boximpl(textIndent.getRestLine()), SaversKt.getSaver(TextUnit.INSTANCE), saverScope));
        }
    }, new Function1() { // from class: androidx.compose.ui.text.SaversKt$$ExternalSyntheticLambda17
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return SaversKt.TextIndentSaver$lambda$1(obj);
        }
    });
    private static final Saver<FontWeight, Object> FontWeightSaver = SaverKt.Saver(new Function2() { // from class: androidx.compose.ui.text.SaversKt$$ExternalSyntheticLambda18
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return Integer.valueOf(((FontWeight) obj2).getWeight());
        }
    }, new Function1() { // from class: androidx.compose.ui.text.SaversKt$$ExternalSyntheticLambda19
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return SaversKt.FontWeightSaver$lambda$1(obj);
        }
    });
    private static final Saver<BaselineShift, Object> BaselineShiftSaver = SaverKt.Saver(new Function2() { // from class: androidx.compose.ui.text.SaversKt$$ExternalSyntheticLambda20
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return Float.valueOf(((BaselineShift) obj2).m7871unboximpl());
        }
    }, new Function1() { // from class: androidx.compose.ui.text.SaversKt$$ExternalSyntheticLambda21
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return SaversKt.BaselineShiftSaver$lambda$1(obj);
        }
    });
    private static final Saver<TextRange, Object> TextRangeSaver = SaverKt.Saver(new Function2() { // from class: androidx.compose.ui.text.SaversKt$$ExternalSyntheticLambda23
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            TextRange textRange = (TextRange) obj2;
            return CollectionsKt.arrayListOf(SaversKt.save(Integer.valueOf(TextRange.m7573getStartimpl(textRange.getPackedValue()))), SaversKt.save(Integer.valueOf(TextRange.m7568getEndimpl(textRange.getPackedValue()))));
        }
    }, new Function1() { // from class: androidx.compose.ui.text.SaversKt$$ExternalSyntheticLambda24
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return SaversKt.TextRangeSaver$lambda$1(obj);
        }
    });
    private static final Saver<Shadow, Object> ShadowSaver = SaverKt.Saver(new Function2() { // from class: androidx.compose.ui.text.SaversKt$$ExternalSyntheticLambda25
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            SaverScope saverScope = (SaverScope) obj;
            Shadow shadow = (Shadow) obj2;
            return CollectionsKt.arrayListOf(SaversKt.save(Color.m5303boximpl(shadow.getColor()), SaversKt.getSaver(Color.INSTANCE), saverScope), SaversKt.save(Offset.m5057boximpl(shadow.getOffset()), SaversKt.getSaver(Offset.INSTANCE), saverScope), SaversKt.save(Float.valueOf(shadow.getBlurRadius())));
        }
    }, new Function1() { // from class: androidx.compose.ui.text.SaversKt$$ExternalSyntheticLambda26
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return SaversKt.ShadowSaver$lambda$1(obj);
        }
    });
    private static final NonNullValueClassSaver<Color, Object> ColorSaver = NonNullValueClassSaver(new Function2<SaverScope, Color, Object>() { // from class: androidx.compose.ui.text.SaversKt$ColorSaver$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(SaverScope saverScope, Color color) {
            return m7502invoke4WTKRHQ(saverScope, color.m5323unboximpl());
        }

        /* JADX INFO: renamed from: invoke-4WTKRHQ, reason: not valid java name */
        public final Object m7502invoke4WTKRHQ(SaverScope $this$NonNullValueClassSaver, long it) {
            if (it == 16) {
                return false;
            }
            return Integer.valueOf(ColorKt.m5367toArgb8_81llA(it));
        }
    }, new Function1<Object, Color>() { // from class: androidx.compose.ui.text.SaversKt$ColorSaver$2
        @Override // kotlin.jvm.functions.Function1
        /* JADX INFO: renamed from: invoke-ijrfgN4, reason: not valid java name and merged with bridge method [inline-methods] */
        public final Color invoke(Object it) {
            if (Intrinsics.areEqual(it, (Object) false)) {
                return Color.m5303boximpl(Color.INSTANCE.m5349getUnspecified0d7_KjU());
            }
            Intrinsics.checkNotNull(it, "null cannot be cast to non-null type kotlin.Int");
            return Color.m5303boximpl(ColorKt.Color(((Integer) it).intValue()));
        }
    });
    private static final NonNullValueClassSaver<TextAlign, Object> TextAlignSaver = NonNullValueClassSaver(new Function2() { // from class: androidx.compose.ui.text.SaversKt$$ExternalSyntheticLambda27
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return Integer.valueOf(((TextAlign) obj2).m8002unboximpl());
        }
    }, new Function1() { // from class: androidx.compose.ui.text.SaversKt$$ExternalSyntheticLambda28
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return SaversKt.TextAlignSaver$lambda$1(obj);
        }
    });
    private static final NonNullValueClassSaver<TextDirection, Object> TextDirectionSaver = NonNullValueClassSaver(new Function2() { // from class: androidx.compose.ui.text.SaversKt$$ExternalSyntheticLambda29
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return Integer.valueOf(((TextDirection) obj2).m8019unboximpl());
        }
    }, new Function1() { // from class: androidx.compose.ui.text.SaversKt$$ExternalSyntheticLambda30
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return SaversKt.TextDirectionSaver$lambda$1(obj);
        }
    });
    private static final NonNullValueClassSaver<Hyphens, Object> HyphensSaver = NonNullValueClassSaver(new Function2() { // from class: androidx.compose.ui.text.SaversKt$$ExternalSyntheticLambda31
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return Integer.valueOf(((Hyphens) obj2).m7894unboximpl());
        }
    }, new Function1() { // from class: androidx.compose.ui.text.SaversKt$$ExternalSyntheticLambda32
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return SaversKt.HyphensSaver$lambda$1(obj);
        }
    });
    private static final Saver<FontStyle, Object> FontStyleSaver = SaverKt.Saver(new Function2() { // from class: androidx.compose.ui.text.SaversKt$$ExternalSyntheticLambda34
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return SaversKt.save(Integer.valueOf(((FontStyle) obj2).m7688unboximpl()));
        }
    }, new Function1() { // from class: androidx.compose.ui.text.SaversKt$$ExternalSyntheticLambda35
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return SaversKt.FontStyleSaver$lambda$1(obj);
        }
    });
    private static final Saver<FontSynthesis, Object> FontSynthesisSaver = SaverKt.Saver(new Function2() { // from class: androidx.compose.ui.text.SaversKt$$ExternalSyntheticLambda36
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return Integer.valueOf(((FontSynthesis) obj2).m7701unboximpl());
        }
    }, new Function1() { // from class: androidx.compose.ui.text.SaversKt$$ExternalSyntheticLambda37
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return SaversKt.FontSynthesisSaver$lambda$1(obj);
        }
    });
    private static final NonNullValueClassSaver<TextUnit, Object> TextUnitSaver = NonNullValueClassSaver(new Function2() { // from class: androidx.compose.ui.text.SaversKt$$ExternalSyntheticLambda38
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return SaversKt.TextUnitSaver$lambda$0((SaverScope) obj, (TextUnit) obj2);
        }
    }, new Function1() { // from class: androidx.compose.ui.text.SaversKt$$ExternalSyntheticLambda39
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return SaversKt.TextUnitSaver$lambda$1(obj);
        }
    });
    private static final NonNullValueClassSaver<TextUnitType, Object> TextUnitTypeSaver = NonNullValueClassSaver(new Function2() { // from class: androidx.compose.ui.text.SaversKt$$ExternalSyntheticLambda40
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return SaversKt.TextUnitTypeSaver$lambda$0((SaverScope) obj, (TextUnitType) obj2);
        }
    }, new Function1() { // from class: androidx.compose.ui.text.SaversKt$$ExternalSyntheticLambda41
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return SaversKt.TextUnitTypeSaver$lambda$1(obj);
        }
    });
    private static final NonNullValueClassSaver<Offset, Object> OffsetSaver = NonNullValueClassSaver(new Function2() { // from class: androidx.compose.ui.text.SaversKt$$ExternalSyntheticLambda42
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return SaversKt.OffsetSaver$lambda$0((SaverScope) obj, (Offset) obj2);
        }
    }, new Function1() { // from class: androidx.compose.ui.text.SaversKt$$ExternalSyntheticLambda43
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return SaversKt.OffsetSaver$lambda$1(obj);
        }
    });
    private static final Saver<LocaleList, Object> LocaleListSaver = SaverKt.Saver(new Function2() { // from class: androidx.compose.ui.text.SaversKt$$ExternalSyntheticLambda45
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return SaversKt.LocaleListSaver$lambda$0((SaverScope) obj, (LocaleList) obj2);
        }
    }, new Function1() { // from class: androidx.compose.ui.text.SaversKt$$ExternalSyntheticLambda46
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return SaversKt.LocaleListSaver$lambda$1(obj);
        }
    });
    private static final Saver<Locale, Object> LocaleSaver = SaverKt.Saver(new Function2() { // from class: androidx.compose.ui.text.SaversKt$$ExternalSyntheticLambda47
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ((Locale) obj2).toLanguageTag();
        }
    }, new Function1() { // from class: androidx.compose.ui.text.SaversKt$$ExternalSyntheticLambda48
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return SaversKt.LocaleSaver$lambda$1(obj);
        }
    });
    private static final Saver<LineHeightStyle, Object> LineHeightStyleSaver = SaverKt.Saver(new Function2() { // from class: androidx.compose.ui.text.SaversKt$$ExternalSyntheticLambda49
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            SaverScope saverScope = (SaverScope) obj;
            LineHeightStyle lineHeightStyle = (LineHeightStyle) obj2;
            return CollectionsKt.arrayListOf(SaversKt.save(LineHeightStyle.Alignment.m7962boximpl(lineHeightStyle.getAlignment()), SaversKt.getSaver(LineHeightStyle.Alignment.INSTANCE), saverScope), SaversKt.save(LineHeightStyle.Trim.m7983boximpl(lineHeightStyle.getTrim()), SaversKt.getSaver(LineHeightStyle.Trim.INSTANCE), saverScope), SaversKt.save(LineHeightStyle.Mode.m7973boximpl(lineHeightStyle.getMode()), SaversKt.getSaver(LineHeightStyle.Mode.INSTANCE), saverScope));
        }
    }, new Function1() { // from class: androidx.compose.ui.text.SaversKt$$ExternalSyntheticLambda50
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return SaversKt.LineHeightStyleSaver$lambda$1(obj);
        }
    });
    private static final NonNullValueClassSaver<LineHeightStyle.Alignment, Object> LineHeightStyleAlignmentSaver = NonNullValueClassSaver(new Function2() { // from class: androidx.compose.ui.text.SaversKt$$ExternalSyntheticLambda51
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return Float.valueOf(((LineHeightStyle.Alignment) obj2).getTopRatio());
        }
    }, new Function1() { // from class: androidx.compose.ui.text.SaversKt$$ExternalSyntheticLambda52
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return SaversKt.LineHeightStyleAlignmentSaver$lambda$1(obj);
        }
    });
    private static final NonNullValueClassSaver<LineHeightStyle.Trim, Object> LineHeightStyleTrimSaver = NonNullValueClassSaver(new Function2() { // from class: androidx.compose.ui.text.SaversKt$$ExternalSyntheticLambda53
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return Integer.valueOf(((LineHeightStyle.Trim) obj2).getValue());
        }
    }, new Function1() { // from class: androidx.compose.ui.text.SaversKt$$ExternalSyntheticLambda54
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return SaversKt.LineHeightStyleTrimSaver$lambda$1(obj);
        }
    });
    private static final NonNullValueClassSaver<LineHeightStyle.Mode, Object> LineHeightStyleModeSaver = NonNullValueClassSaver(new Function2() { // from class: androidx.compose.ui.text.SaversKt$$ExternalSyntheticLambda56
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return Integer.valueOf(((LineHeightStyle.Mode) obj2).getValue());
        }
    }, new Function1() { // from class: androidx.compose.ui.text.SaversKt$$ExternalSyntheticLambda57
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return SaversKt.LineHeightStyleModeSaver$lambda$1(obj);
        }
    });

    /* JADX INFO: compiled from: Savers.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[AnnotationType.values().length];
            try {
                iArr[AnnotationType.Paragraph.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[AnnotationType.Span.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[AnnotationType.VerbatimTts.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[AnnotationType.Url.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[AnnotationType.Link.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr[AnnotationType.Clickable.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                iArr[AnnotationType.String.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private static /* synthetic */ void getAnnotationRangeSaver$annotations() {
    }

    private static /* synthetic */ void getUrlAnnotationSaver$annotations() {
    }

    public static final <T extends Saver<Original, Saveable>, Original, Saveable> Object save(Original original, T t, SaverScope scope) {
        Object objSave;
        if (original == null || (objSave = t.save(scope, original)) == null) {
            return false;
        }
        return objSave;
    }

    public static final /* synthetic */ <T extends Saver<Original, Saveable>, Original, Saveable, Result> Result restore(Saveable saveable, T t) {
        if ((Intrinsics.areEqual((Object) saveable, (Object) false) && !(t instanceof NonNullValueClassSaver)) || saveable == null) {
            return null;
        }
        Result result = (Result) t.restore(saveable);
        Intrinsics.reifiedOperationMarker(1, "Result");
        return result;
    }

    private static final <Original, Saveable> NonNullValueClassSaver<Original, Saveable> NonNullValueClassSaver(final Function2<? super SaverScope, ? super Original, ? extends Saveable> function2, final Function1<? super Saveable, ? extends Original> function1) {
        return new NonNullValueClassSaver<Original, Saveable>() { // from class: androidx.compose.ui.text.SaversKt.NonNullValueClassSaver.1
            @Override // androidx.compose.runtime.saveable.Saver
            public Saveable save(SaverScope $this$save, Original original) {
                return function2.invoke($this$save, original);
            }

            @Override // androidx.compose.runtime.saveable.Saver
            public Original restore(Saveable value) {
                return function1.invoke(value);
            }
        };
    }

    public static final <T> T save(T t) {
        return t;
    }

    public static final /* synthetic */ <Result> Result restore(Object obj) {
        if (obj == null) {
            return null;
        }
        Intrinsics.reifiedOperationMarker(1, "Result");
        return (Result) obj;
    }

    public static final Saver<AnnotatedString, Object> getAnnotatedStringSaver() {
        return AnnotatedStringSaver;
    }

    static final AnnotatedString AnnotatedStringSaver$lambda$1(Object it) {
        Intrinsics.checkNotNull(it, "null cannot be cast to non-null type kotlin.collections.List<kotlin.Any?>");
        List list = (List) it;
        Object value$iv = list.get(1);
        Saver<List<AnnotatedString.Range<? extends Object>>, Object> saver = AnnotationRangeListSaver;
        List<AnnotatedString.Range<? extends Object>> listRestore = ((!Intrinsics.areEqual(value$iv, (Object) false) || (saver instanceof NonNullValueClassSaver)) && value$iv != null) ? saver.restore(value$iv) : null;
        Object value$iv2 = list.get(0);
        String str = value$iv2 != null ? (String) value$iv2 : null;
        Intrinsics.checkNotNull(str);
        return new AnnotatedString(listRestore, str);
    }

    static final Object AnnotationRangeListSaver$lambda$0(SaverScope $this$Saver, List it) {
        List target$iv = new ArrayList(it.size());
        int size = it.size();
        for (int index$iv$iv = 0; index$iv$iv < size; index$iv$iv++) {
            Object item$iv$iv = it.get(index$iv$iv);
            AnnotatedString.Range range = (AnnotatedString.Range) item$iv$iv;
            target$iv.add(save(range, AnnotationRangeSaver, $this$Saver));
        }
        List $this$fastMap$iv = target$iv;
        return $this$fastMap$iv;
    }

    static final List AnnotationRangeListSaver$lambda$1(Object it) {
        Intrinsics.checkNotNull(it, "null cannot be cast to non-null type kotlin.collections.List<kotlin.Any>");
        List list = (List) it;
        ArrayList target$iv = new ArrayList(list.size());
        int index$iv$iv = 0;
        int size = list.size();
        while (index$iv$iv < size) {
            Object item$iv$iv = list.get(index$iv$iv);
            ArrayList arrayList = target$iv;
            Saver<AnnotatedString.Range<? extends Object>, Object> saver = AnnotationRangeSaver;
            List list2 = list;
            AnnotatedString.Range<? extends Object> range = null;
            if ((!Intrinsics.areEqual(item$iv$iv, (Object) false) || (saver instanceof NonNullValueClassSaver)) && item$iv$iv != null) {
                Object it$iv = saver.restore(item$iv$iv);
                range = (AnnotatedString.Range) it$iv;
            }
            Intrinsics.checkNotNull(range);
            arrayList.add(range);
            index$iv$iv++;
            list = list2;
        }
        return target$iv;
    }

    static final Object AnnotationRangeSaver$lambda$0(SaverScope $this$Saver, AnnotatedString.Range it) {
        AnnotationType marker;
        Object item;
        Object item2 = it.getItem();
        if (item2 instanceof ParagraphStyle) {
            marker = AnnotationType.Paragraph;
        } else if (item2 instanceof SpanStyle) {
            marker = AnnotationType.Span;
        } else if (item2 instanceof VerbatimTtsAnnotation) {
            marker = AnnotationType.VerbatimTts;
        } else if (item2 instanceof UrlAnnotation) {
            marker = AnnotationType.Url;
        } else if (item2 instanceof LinkAnnotation.Url) {
            marker = AnnotationType.Link;
        } else if (item2 instanceof LinkAnnotation.Clickable) {
            marker = AnnotationType.Clickable;
        } else {
            if (!(item2 instanceof StringAnnotation)) {
                throw new UnsupportedOperationException();
            }
            marker = AnnotationType.String;
        }
        switch (WhenMappings.$EnumSwitchMapping$0[marker.ordinal()]) {
            case 1:
                Object item3 = it.getItem();
                Intrinsics.checkNotNull(item3, "null cannot be cast to non-null type androidx.compose.ui.text.ParagraphStyle");
                item = save((ParagraphStyle) item3, ParagraphStyleSaver, $this$Saver);
                break;
            case 2:
                Object item4 = it.getItem();
                Intrinsics.checkNotNull(item4, "null cannot be cast to non-null type androidx.compose.ui.text.SpanStyle");
                item = save((SpanStyle) item4, SpanStyleSaver, $this$Saver);
                break;
            case 3:
                Object item5 = it.getItem();
                Intrinsics.checkNotNull(item5, "null cannot be cast to non-null type androidx.compose.ui.text.VerbatimTtsAnnotation");
                item = save((VerbatimTtsAnnotation) item5, VerbatimTtsAnnotationSaver, $this$Saver);
                break;
            case 4:
                Object item6 = it.getItem();
                Intrinsics.checkNotNull(item6, "null cannot be cast to non-null type androidx.compose.ui.text.UrlAnnotation");
                item = save((UrlAnnotation) item6, UrlAnnotationSaver, $this$Saver);
                break;
            case 5:
                Object item7 = it.getItem();
                Intrinsics.checkNotNull(item7, "null cannot be cast to non-null type androidx.compose.ui.text.LinkAnnotation.Url");
                item = save((LinkAnnotation.Url) item7, LinkSaver, $this$Saver);
                break;
            case 6:
                Object item8 = it.getItem();
                Intrinsics.checkNotNull(item8, "null cannot be cast to non-null type androidx.compose.ui.text.LinkAnnotation.Clickable");
                item = save((LinkAnnotation.Clickable) item8, ClickableSaver, $this$Saver);
                break;
            case 7:
                Object item9 = it.getItem();
                Intrinsics.checkNotNull(item9, "null cannot be cast to non-null type androidx.compose.ui.text.StringAnnotation");
                item = save(((StringAnnotation) item9).m7527unboximpl());
                break;
            default:
                throw new NoWhenBranchMatchedException();
        }
        return CollectionsKt.arrayListOf(save(marker), item, save(Integer.valueOf(it.getStart())), save(Integer.valueOf(it.getEnd())), save(it.getTag()));
    }

    static final AnnotatedString.Range AnnotationRangeSaver$lambda$1(Object obj) {
        Integer num;
        Integer num2;
        String str;
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.collections.List<kotlin.Any>");
        List list = (List) obj;
        Object obj2 = list.get(0);
        ParagraphStyle paragraphStyleRestore = null;
        String str2 = null;
        clickableRestore = null;
        LinkAnnotation.Clickable clickableRestore = null;
        urlRestore = null;
        LinkAnnotation.Url urlRestore = null;
        urlAnnotationRestore = null;
        UrlAnnotation urlAnnotationRestore = null;
        verbatimTtsAnnotationRestore = null;
        VerbatimTtsAnnotation verbatimTtsAnnotationRestore = null;
        spanStyleRestore = null;
        SpanStyle spanStyleRestore = null;
        paragraphStyleRestore = null;
        AnnotationType annotationType = obj2 != null ? (AnnotationType) obj2 : null;
        Intrinsics.checkNotNull(annotationType);
        Object obj3 = list.get(2);
        if (obj3 == null) {
            num = null;
        } else {
            num = (Integer) obj3;
        }
        Intrinsics.checkNotNull(num);
        int iIntValue = num.intValue();
        Object obj4 = list.get(3);
        if (obj4 == null) {
            num2 = null;
        } else {
            num2 = (Integer) obj4;
        }
        Intrinsics.checkNotNull(num2);
        int iIntValue2 = num2.intValue();
        Object obj5 = list.get(4);
        if (obj5 == null) {
            str = null;
        } else {
            str = (String) obj5;
        }
        Intrinsics.checkNotNull(str);
        switch (WhenMappings.$EnumSwitchMapping$0[annotationType.ordinal()]) {
            case 1:
                Object obj6 = list.get(1);
                Saver<ParagraphStyle, Object> saver = ParagraphStyleSaver;
                if ((!Intrinsics.areEqual(obj6, (Object) false) || (saver instanceof NonNullValueClassSaver)) && obj6 != null) {
                    paragraphStyleRestore = saver.restore(obj6);
                }
                Intrinsics.checkNotNull(paragraphStyleRestore);
                return new AnnotatedString.Range(paragraphStyleRestore, iIntValue, iIntValue2, str);
            case 2:
                Object obj7 = list.get(1);
                Saver<SpanStyle, Object> saver2 = SpanStyleSaver;
                if ((!Intrinsics.areEqual(obj7, (Object) false) || (saver2 instanceof NonNullValueClassSaver)) && obj7 != null) {
                    spanStyleRestore = saver2.restore(obj7);
                }
                Intrinsics.checkNotNull(spanStyleRestore);
                return new AnnotatedString.Range(spanStyleRestore, iIntValue, iIntValue2, str);
            case 3:
                Object obj8 = list.get(1);
                Saver<VerbatimTtsAnnotation, Object> saver3 = VerbatimTtsAnnotationSaver;
                if ((!Intrinsics.areEqual(obj8, (Object) false) || (saver3 instanceof NonNullValueClassSaver)) && obj8 != null) {
                    verbatimTtsAnnotationRestore = saver3.restore(obj8);
                }
                Intrinsics.checkNotNull(verbatimTtsAnnotationRestore);
                return new AnnotatedString.Range(verbatimTtsAnnotationRestore, iIntValue, iIntValue2, str);
            case 4:
                Object obj9 = list.get(1);
                Saver<UrlAnnotation, Object> saver4 = UrlAnnotationSaver;
                if ((!Intrinsics.areEqual(obj9, (Object) false) || (saver4 instanceof NonNullValueClassSaver)) && obj9 != null) {
                    urlAnnotationRestore = saver4.restore(obj9);
                }
                Intrinsics.checkNotNull(urlAnnotationRestore);
                return new AnnotatedString.Range(urlAnnotationRestore, iIntValue, iIntValue2, str);
            case 5:
                Object obj10 = list.get(1);
                Saver<LinkAnnotation.Url, Object> saver5 = LinkSaver;
                if ((!Intrinsics.areEqual(obj10, (Object) false) || (saver5 instanceof NonNullValueClassSaver)) && obj10 != null) {
                    urlRestore = saver5.restore(obj10);
                }
                Intrinsics.checkNotNull(urlRestore);
                return new AnnotatedString.Range(urlRestore, iIntValue, iIntValue2, str);
            case 6:
                Object obj11 = list.get(1);
                Saver<LinkAnnotation.Clickable, Object> saver6 = ClickableSaver;
                if ((!Intrinsics.areEqual(obj11, (Object) false) || (saver6 instanceof NonNullValueClassSaver)) && obj11 != null) {
                    clickableRestore = saver6.restore(obj11);
                }
                Intrinsics.checkNotNull(clickableRestore);
                return new AnnotatedString.Range(clickableRestore, iIntValue, iIntValue2, str);
            case 7:
                Object obj12 = list.get(1);
                if (obj12 != null) {
                    str2 = (String) obj12;
                }
                Intrinsics.checkNotNull(str2);
                return new AnnotatedString.Range(StringAnnotation.m7521boximpl(StringAnnotation.m7522constructorimpl(str2)), iIntValue, iIntValue2, str);
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    static final VerbatimTtsAnnotation VerbatimTtsAnnotationSaver$lambda$1(Object it) {
        String str = it != null ? (String) it : null;
        Intrinsics.checkNotNull(str);
        return new VerbatimTtsAnnotation(str);
    }

    static final UrlAnnotation UrlAnnotationSaver$lambda$1(Object it) {
        String str = it != null ? (String) it : null;
        Intrinsics.checkNotNull(str);
        return new UrlAnnotation(str);
    }

    static final LinkAnnotation.Url LinkSaver$lambda$1(Object it) {
        Intrinsics.checkNotNull(it, "null cannot be cast to non-null type kotlin.collections.List<kotlin.Any?>");
        List list = (List) it;
        Object value$iv = list.get(0);
        String str = value$iv != null ? (String) value$iv : null;
        Intrinsics.checkNotNull(str);
        String url = str;
        Object value$iv2 = list.get(1);
        Saver<TextLinkStyles, Object> saver = TextLinkStylesSaver;
        TextLinkStyles stylesOrNull = ((!Intrinsics.areEqual(value$iv2, (Object) false) || (saver instanceof NonNullValueClassSaver)) && value$iv2 != null) ? saver.restore(value$iv2) : null;
        return new LinkAnnotation.Url(url, stylesOrNull, null, 4, null);
    }

    static final LinkAnnotation.Clickable ClickableSaver$lambda$1(Object it) {
        Intrinsics.checkNotNull(it, "null cannot be cast to non-null type kotlin.collections.List<kotlin.Any?>");
        List list = (List) it;
        Object value$iv = list.get(0);
        String tag = value$iv != null ? (String) value$iv : null;
        Intrinsics.checkNotNull(tag);
        Object value$iv2 = list.get(1);
        Saver<TextLinkStyles, Object> saver = TextLinkStylesSaver;
        TextLinkStyles stylesOrNull = ((!Intrinsics.areEqual(value$iv2, (Object) false) || (saver instanceof NonNullValueClassSaver)) && value$iv2 != null) ? saver.restore(value$iv2) : null;
        return new LinkAnnotation.Clickable(tag, stylesOrNull, null);
    }

    public static final Saver<ParagraphStyle, Object> getParagraphStyleSaver() {
        return ParagraphStyleSaver;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: ModVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r2v0 androidx.compose.ui.text.ParagraphStyle, still in use, count: 3, list:
          (r2v0 androidx.compose.ui.text.ParagraphStyle) from 0x016e: MOVE (r18v0 androidx.compose.ui.text.ParagraphStyle) = (r2v0 androidx.compose.ui.text.ParagraphStyle)
          (r2v0 androidx.compose.ui.text.ParagraphStyle) from 0x0163: MOVE (r18v1 androidx.compose.ui.text.ParagraphStyle) = (r2v0 androidx.compose.ui.text.ParagraphStyle) (LINE:711)
          (r2v0 androidx.compose.ui.text.ParagraphStyle) from 0x0157: MOVE (r18v3 androidx.compose.ui.text.ParagraphStyle) = (r2v0 androidx.compose.ui.text.ParagraphStyle)
        	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:162)
        	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:127)
        	at jadx.core.utils.InsnRemover.unbindInsn(InsnRemover.java:91)
        	at jadx.core.utils.InsnRemover.addAndUnbind(InsnRemover.java:57)
        	at jadx.core.dex.visitors.ModVisitor.removeStep(ModVisitor.java:463)
        	at jadx.core.dex.visitors.ModVisitor.visit(ModVisitor.java:97)
        */
    static final androidx.compose.ui.text.ParagraphStyle ParagraphStyleSaver$lambda$1(java.lang.Object r19) {
        /*
            Method dump skipped, instruction units count: 429
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.text.SaversKt.ParagraphStyleSaver$lambda$1(java.lang.Object):androidx.compose.ui.text.ParagraphStyle");
    }

    public static final Saver<SpanStyle, Object> getSpanStyleSaver() {
        return SpanStyleSaver;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: ModVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r2v0 androidx.compose.ui.text.SpanStyle, still in use, count: 2, list:
          (r2v0 androidx.compose.ui.text.SpanStyle) from 0x0140: MOVE (r16v4 androidx.compose.ui.text.SpanStyle) = (r2v0 androidx.compose.ui.text.SpanStyle)
          (r2v0 androidx.compose.ui.text.SpanStyle) from 0x0138: MOVE (r16v8 androidx.compose.ui.text.SpanStyle) = (r2v0 androidx.compose.ui.text.SpanStyle)
        	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:162)
        	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:127)
        	at jadx.core.utils.InsnRemover.unbindInsn(InsnRemover.java:91)
        	at jadx.core.utils.InsnRemover.addAndUnbind(InsnRemover.java:57)
        	at jadx.core.dex.visitors.ModVisitor.removeStep(ModVisitor.java:463)
        	at jadx.core.dex.visitors.ModVisitor.visit(ModVisitor.java:97)
        */
    static final androidx.compose.ui.text.SpanStyle SpanStyleSaver$lambda$1(java.lang.Object r27) {
        /*
            Method dump skipped, instruction units count: 645
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.text.SaversKt.SpanStyleSaver$lambda$1(java.lang.Object):androidx.compose.ui.text.SpanStyle");
    }

    public static final Saver<TextLinkStyles, Object> getTextLinkStylesSaver() {
        return TextLinkStylesSaver;
    }

    static final TextLinkStyles TextLinkStylesSaver$lambda$1(Object it) {
        Intrinsics.checkNotNull(it, "null cannot be cast to non-null type kotlin.collections.List<kotlin.Any?>");
        List list = (List) it;
        Object value$iv = list.get(0);
        Saver<SpanStyle, Object> saver = SpanStyleSaver;
        SpanStyle pressedStyleOrNull = null;
        SpanStyle styleOrNull = ((!Intrinsics.areEqual(value$iv, (Object) false) || (saver instanceof NonNullValueClassSaver)) && value$iv != null) ? saver.restore(value$iv) : null;
        Object value$iv2 = list.get(1);
        Saver<SpanStyle, Object> saver2 = SpanStyleSaver;
        SpanStyle focusedStyleOrNull = ((!Intrinsics.areEqual(value$iv2, (Object) false) || (saver2 instanceof NonNullValueClassSaver)) && value$iv2 != null) ? saver2.restore(value$iv2) : null;
        Object value$iv3 = list.get(2);
        Saver<SpanStyle, Object> saver3 = SpanStyleSaver;
        SpanStyle hoveredStyleOrNull = ((!Intrinsics.areEqual(value$iv3, (Object) false) || (saver3 instanceof NonNullValueClassSaver)) && value$iv3 != null) ? saver3.restore(value$iv3) : null;
        Object value$iv4 = list.get(3);
        Saver<SpanStyle, Object> saver4 = SpanStyleSaver;
        if ((!Intrinsics.areEqual(value$iv4, (Object) false) || (saver4 instanceof NonNullValueClassSaver)) && value$iv4 != null) {
            pressedStyleOrNull = saver4.restore(value$iv4);
        }
        return new TextLinkStyles(styleOrNull, focusedStyleOrNull, hoveredStyleOrNull, pressedStyleOrNull);
    }

    public static final Saver<TextDecoration, Object> getSaver(TextDecoration.Companion $this$Saver) {
        return TextDecorationSaver;
    }

    static final TextDecoration TextDecorationSaver$lambda$1(Object it) {
        Intrinsics.checkNotNull(it, "null cannot be cast to non-null type kotlin.Int");
        return new TextDecoration(((Integer) it).intValue());
    }

    public static final Saver<TextGeometricTransform, Object> getSaver(TextGeometricTransform.Companion $this$Saver) {
        return TextGeometricTransformSaver;
    }

    static final TextGeometricTransform TextGeometricTransformSaver$lambda$1(Object it) {
        Intrinsics.checkNotNull(it, "null cannot be cast to non-null type kotlin.collections.List<kotlin.Float>");
        List list = (List) it;
        return new TextGeometricTransform(((Number) list.get(0)).floatValue(), ((Number) list.get(1)).floatValue());
    }

    public static final Saver<TextIndent, Object> getSaver(TextIndent.Companion $this$Saver) {
        return TextIndentSaver;
    }

    static final TextIndent TextIndentSaver$lambda$1(Object it) {
        Intrinsics.checkNotNull(it, "null cannot be cast to non-null type kotlin.collections.List<kotlin.Any>");
        List list = (List) it;
        Object value$iv = list.get(0);
        Saver<TextUnit, Object> saver = getSaver(TextUnit.INSTANCE);
        TextUnit textUnitRestore = null;
        TextUnit textUnitRestore2 = ((!Intrinsics.areEqual(value$iv, (Object) false) || (saver instanceof NonNullValueClassSaver)) && value$iv != null) ? saver.restore(value$iv) : null;
        Intrinsics.checkNotNull(textUnitRestore2);
        long packedValue = textUnitRestore2.getPackedValue();
        Object value$iv2 = list.get(1);
        Saver<TextUnit, Object> saver2 = getSaver(TextUnit.INSTANCE);
        if ((!Intrinsics.areEqual(value$iv2, (Object) false) || (saver2 instanceof NonNullValueClassSaver)) && value$iv2 != null) {
            textUnitRestore = saver2.restore(value$iv2);
        }
        Intrinsics.checkNotNull(textUnitRestore);
        return new TextIndent(packedValue, textUnitRestore.getPackedValue(), null);
    }

    public static final Saver<FontWeight, Object> getSaver(FontWeight.Companion $this$Saver) {
        return FontWeightSaver;
    }

    static final FontWeight FontWeightSaver$lambda$1(Object it) {
        Intrinsics.checkNotNull(it, "null cannot be cast to non-null type kotlin.Int");
        return new FontWeight(((Integer) it).intValue());
    }

    public static final Saver<BaselineShift, Object> getSaver(BaselineShift.Companion $this$Saver) {
        return BaselineShiftSaver;
    }

    static final BaselineShift BaselineShiftSaver$lambda$1(Object it) {
        Intrinsics.checkNotNull(it, "null cannot be cast to non-null type kotlin.Float");
        return BaselineShift.m7865boximpl(BaselineShift.m7866constructorimpl(((Float) it).floatValue()));
    }

    public static final Saver<TextRange, Object> getSaver(TextRange.Companion $this$Saver) {
        return TextRangeSaver;
    }

    static final TextRange TextRangeSaver$lambda$1(Object it) {
        Intrinsics.checkNotNull(it, "null cannot be cast to non-null type kotlin.collections.List<kotlin.Any>");
        List list = (List) it;
        Object value$iv = list.get(0);
        Object it$iv = value$iv != null ? (Integer) value$iv : null;
        Intrinsics.checkNotNull(it$iv);
        int iIntValue = ((Number) it$iv).intValue();
        Object value$iv2 = list.get(1);
        Object it$iv2 = value$iv2 != null ? (Integer) value$iv2 : null;
        Intrinsics.checkNotNull(it$iv2);
        return TextRange.m7561boximpl(TextRangeKt.TextRange(iIntValue, ((Number) it$iv2).intValue()));
    }

    public static final Saver<Shadow, Object> getSaver(Shadow.Companion $this$Saver) {
        return ShadowSaver;
    }

    static final Shadow ShadowSaver$lambda$1(Object it) {
        Intrinsics.checkNotNull(it, "null cannot be cast to non-null type kotlin.collections.List<kotlin.Any>");
        List list = (List) it;
        Object value$iv = list.get(0);
        Saver<Color, Object> saver = getSaver(Color.INSTANCE);
        Color colorRestore = ((!Intrinsics.areEqual(value$iv, (Object) false) || (saver instanceof NonNullValueClassSaver)) && value$iv != null) ? saver.restore(value$iv) : null;
        Intrinsics.checkNotNull(colorRestore);
        long jM5323unboximpl = colorRestore.m5323unboximpl();
        Object value$iv2 = list.get(1);
        Saver<Offset, Object> saver2 = getSaver(Offset.INSTANCE);
        Offset offsetRestore = ((!Intrinsics.areEqual(value$iv2, (Object) false) || (saver2 instanceof NonNullValueClassSaver)) && value$iv2 != null) ? saver2.restore(value$iv2) : null;
        Intrinsics.checkNotNull(offsetRestore);
        long jM5078unboximpl = offsetRestore.m5078unboximpl();
        Object value$iv3 = list.get(2);
        Object it$iv = value$iv3 != null ? (Float) value$iv3 : null;
        Intrinsics.checkNotNull(it$iv);
        return new Shadow(jM5323unboximpl, jM5078unboximpl, ((Number) it$iv).floatValue(), null);
    }

    public static final Saver<Color, Object> getSaver(Color.Companion $this$Saver) {
        return ColorSaver;
    }

    public static final Saver<TextAlign, Object> getSaver(TextAlign.Companion $this$Saver) {
        return TextAlignSaver;
    }

    static final TextAlign TextAlignSaver$lambda$1(Object it) {
        Intrinsics.checkNotNull(it, "null cannot be cast to non-null type kotlin.Int");
        return TextAlign.m7996boximpl(TextAlign.m7997constructorimpl(((Integer) it).intValue()));
    }

    public static final Saver<TextDirection, Object> getSaver(TextDirection.Companion $this$Saver) {
        return TextDirectionSaver;
    }

    static final TextDirection TextDirectionSaver$lambda$1(Object it) {
        Intrinsics.checkNotNull(it, "null cannot be cast to non-null type kotlin.Int");
        return TextDirection.m8013boximpl(TextDirection.m8014constructorimpl(((Integer) it).intValue()));
    }

    public static final Saver<Hyphens, Object> getSaver(Hyphens.Companion $this$Saver) {
        return HyphensSaver;
    }

    static final Hyphens HyphensSaver$lambda$1(Object it) {
        Intrinsics.checkNotNull(it, "null cannot be cast to non-null type kotlin.Int");
        return Hyphens.m7888boximpl(Hyphens.m7889constructorimpl(((Integer) it).intValue()));
    }

    public static final Saver<FontStyle, Object> getSaver(FontStyle.Companion $this$Saver) {
        return FontStyleSaver;
    }

    public static final Saver<FontStyle, Object> getFontStyleSaver() {
        return FontStyleSaver;
    }

    static final FontStyle FontStyleSaver$lambda$1(Object it) {
        Intrinsics.checkNotNull(it, "null cannot be cast to non-null type kotlin.Int");
        return FontStyle.m7682boximpl(FontStyle.m7683constructorimpl(((Integer) it).intValue()));
    }

    public static final Saver<FontSynthesis, Object> getSaver(FontSynthesis.Companion $this$Saver) {
        return FontSynthesisSaver;
    }

    public static final Saver<FontSynthesis, Object> getFontSynthesisSaver() {
        return FontSynthesisSaver;
    }

    static final FontSynthesis FontSynthesisSaver$lambda$1(Object it) {
        Intrinsics.checkNotNull(it, "null cannot be cast to non-null type kotlin.Int");
        return FontSynthesis.m7693boximpl(FontSynthesis.m7694constructorimpl(((Integer) it).intValue()));
    }

    public static final Saver<TextUnit, Object> getSaver(TextUnit.Companion $this$Saver) {
        return TextUnitSaver;
    }

    static final Object TextUnitSaver$lambda$0(SaverScope $this$NonNullValueClassSaver, TextUnit it) {
        if (it == null ? false : TextUnit.m8341equalsimpl0(it.getPackedValue(), TextUnit.INSTANCE.m8355getUnspecifiedXSAIIZE())) {
            return (Serializable) false;
        }
        return CollectionsKt.arrayListOf(save(Float.valueOf(TextUnit.m8344getValueimpl(it.getPackedValue()))), save(TextUnitType.m8369boximpl(TextUnit.m8343getTypeUIouoOA(it.getPackedValue())), getSaver(TextUnitType.INSTANCE), $this$NonNullValueClassSaver));
    }

    static final TextUnit TextUnitSaver$lambda$1(Object it) {
        if (Intrinsics.areEqual(it, (Object) false)) {
            return TextUnit.m8334boximpl(TextUnit.INSTANCE.m8355getUnspecifiedXSAIIZE());
        }
        Intrinsics.checkNotNull(it, "null cannot be cast to non-null type kotlin.collections.List<kotlin.Any>");
        List list = (List) it;
        Object value$iv = list.get(0);
        TextUnitType textUnitTypeRestore = null;
        Object it$iv = value$iv != null ? (Float) value$iv : null;
        Intrinsics.checkNotNull(it$iv);
        float fFloatValue = ((Number) it$iv).floatValue();
        Object value$iv2 = list.get(1);
        Saver<TextUnitType, Object> saver = getSaver(TextUnitType.INSTANCE);
        if ((!Intrinsics.areEqual(value$iv2, (Object) false) || (saver instanceof NonNullValueClassSaver)) && value$iv2 != null) {
            textUnitTypeRestore = saver.restore(value$iv2);
        }
        Intrinsics.checkNotNull(textUnitTypeRestore);
        return TextUnit.m8334boximpl(TextUnitKt.m8356TextUnitanM5pPY(fFloatValue, textUnitTypeRestore.getType()));
    }

    public static final Saver<TextUnitType, Object> getSaver(TextUnitType.Companion $this$Saver) {
        return TextUnitTypeSaver;
    }

    public static final NonNullValueClassSaver<TextUnitType, Object> getTextUnitTypeSaver() {
        return TextUnitTypeSaver;
    }

    static final Object TextUnitTypeSaver$lambda$0(SaverScope $this$NonNullValueClassSaver, TextUnitType it) {
        long type = it.getType();
        if (TextUnitType.m8372equalsimpl0(type, TextUnitType.INSTANCE.m8376getEmUIouoOA())) {
            return 0;
        }
        return TextUnitType.m8372equalsimpl0(type, TextUnitType.INSTANCE.m8377getSpUIouoOA()) ? 1 : false;
    }

    static final TextUnitType TextUnitTypeSaver$lambda$1(Object it) {
        return Intrinsics.areEqual(it, (Object) 0) ? TextUnitType.m8369boximpl(TextUnitType.INSTANCE.m8376getEmUIouoOA()) : Intrinsics.areEqual(it, (Object) 1) ? TextUnitType.m8369boximpl(TextUnitType.INSTANCE.m8377getSpUIouoOA()) : TextUnitType.m8369boximpl(TextUnitType.INSTANCE.m8378getUnspecifiedUIouoOA());
    }

    public static final Saver<Offset, Object> getSaver(Offset.Companion $this$Saver) {
        return OffsetSaver;
    }

    /* JADX WARN: Multi-variable type inference failed */
    static final Object OffsetSaver$lambda$0(SaverScope $this$NonNullValueClassSaver, Offset it) {
        if (it == null ? false : Offset.m5065equalsimpl0(it.m5078unboximpl(), Offset.INSTANCE.m5083getUnspecifiedF1C5BW0())) {
            return (Serializable) false;
        }
        long arg0$iv = it.m5078unboximpl();
        int bits$iv$iv$iv = (int) (arg0$iv >> 32);
        long arg0$iv2 = it.m5078unboximpl();
        int bits$iv$iv$iv2 = (int) (4294967295L & arg0$iv2);
        return CollectionsKt.arrayListOf(save(Float.valueOf(Float.intBitsToFloat(bits$iv$iv$iv))), save(Float.valueOf(Float.intBitsToFloat(bits$iv$iv$iv2))));
    }

    static final Offset OffsetSaver$lambda$1(Object it) {
        if (Intrinsics.areEqual(it, (Object) false)) {
            return Offset.m5057boximpl(Offset.INSTANCE.m5083getUnspecifiedF1C5BW0());
        }
        Intrinsics.checkNotNull(it, "null cannot be cast to non-null type kotlin.collections.List<kotlin.Any?>");
        List list = (List) it;
        Object value$iv = list.get(0);
        Object it$iv = value$iv != null ? (Float) value$iv : null;
        Intrinsics.checkNotNull(it$iv);
        float x$iv = ((Number) it$iv).floatValue();
        Object value$iv2 = list.get(1);
        Object it$iv2 = value$iv2 != null ? (Float) value$iv2 : null;
        Intrinsics.checkNotNull(it$iv2);
        float y$iv = ((Number) it$iv2).floatValue();
        long v1$iv$iv = Float.floatToRawIntBits(x$iv);
        long v2$iv$iv = Float.floatToRawIntBits(y$iv);
        return Offset.m5057boximpl(Offset.m5060constructorimpl((v1$iv$iv << 32) | (4294967295L & v2$iv$iv)));
    }

    public static final Saver<LocaleList, Object> getSaver(LocaleList.Companion $this$Saver) {
        return LocaleListSaver;
    }

    static final Object LocaleListSaver$lambda$0(SaverScope $this$Saver, LocaleList it) {
        List<Locale> localeList = it.getLocaleList();
        List target$iv = new ArrayList(localeList.size());
        int size = localeList.size();
        for (int index$iv$iv = 0; index$iv$iv < size; index$iv$iv++) {
            Object item$iv$iv = localeList.get(index$iv$iv);
            Locale locale = (Locale) item$iv$iv;
            target$iv.add(save(locale, getSaver(Locale.INSTANCE), $this$Saver));
        }
        List $this$fastMap$iv = target$iv;
        return $this$fastMap$iv;
    }

    static final LocaleList LocaleListSaver$lambda$1(Object it) {
        Locale locale;
        Intrinsics.checkNotNull(it, "null cannot be cast to non-null type kotlin.collections.List<kotlin.Any>");
        List list = (List) it;
        ArrayList target$iv = new ArrayList(list.size());
        int index$iv$iv = 0;
        int size = list.size();
        while (index$iv$iv < size) {
            Object item$iv$iv = list.get(index$iv$iv);
            ArrayList arrayList = target$iv;
            Saver<Locale, Object> saver = getSaver(Locale.INSTANCE);
            List list2 = list;
            if (Intrinsics.areEqual(item$iv$iv, (Object) false) && !(saver instanceof NonNullValueClassSaver)) {
                locale = null;
            } else if (item$iv$iv != null) {
                Object it$iv = saver.restore(item$iv$iv);
                locale = (Locale) it$iv;
            } else {
                locale = null;
            }
            Intrinsics.checkNotNull(locale);
            arrayList.add(locale);
            index$iv$iv++;
            list = list2;
        }
        return new LocaleList(target$iv);
    }

    public static final Saver<Locale, Object> getSaver(Locale.Companion $this$Saver) {
        return LocaleSaver;
    }

    static final Locale LocaleSaver$lambda$1(Object it) {
        Intrinsics.checkNotNull(it, "null cannot be cast to non-null type kotlin.String");
        return new Locale((String) it);
    }

    public static final Saver<LineHeightStyle, Object> getSaver(LineHeightStyle.Companion $this$Saver) {
        return LineHeightStyleSaver;
    }

    static final LineHeightStyle LineHeightStyleSaver$lambda$1(Object it) {
        Intrinsics.checkNotNull(it, "null cannot be cast to non-null type kotlin.collections.List<kotlin.Any>");
        List list = (List) it;
        Object value$iv = list.get(0);
        Saver<LineHeightStyle.Alignment, Object> saver = getSaver(LineHeightStyle.Alignment.INSTANCE);
        LineHeightStyle.Alignment alignmentRestore = ((!Intrinsics.areEqual(value$iv, (Object) false) || (saver instanceof NonNullValueClassSaver)) && value$iv != null) ? saver.restore(value$iv) : null;
        Intrinsics.checkNotNull(alignmentRestore);
        float topRatio = alignmentRestore.getTopRatio();
        Object value$iv2 = list.get(1);
        Saver<LineHeightStyle.Trim, Object> saver2 = getSaver(LineHeightStyle.Trim.INSTANCE);
        LineHeightStyle.Trim trimRestore = ((!Intrinsics.areEqual(value$iv2, (Object) false) || (saver2 instanceof NonNullValueClassSaver)) && value$iv2 != null) ? saver2.restore(value$iv2) : null;
        Intrinsics.checkNotNull(trimRestore);
        int value = trimRestore.getValue();
        Object value$iv3 = list.get(2);
        Saver<LineHeightStyle.Mode, Object> saver3 = getSaver(LineHeightStyle.Mode.INSTANCE);
        LineHeightStyle.Mode modeRestore = ((!Intrinsics.areEqual(value$iv3, (Object) false) || (saver3 instanceof NonNullValueClassSaver)) && value$iv3 != null) ? saver3.restore(value$iv3) : null;
        Intrinsics.checkNotNull(modeRestore);
        return new LineHeightStyle(topRatio, value, modeRestore.getValue(), null);
    }

    private static final Saver<LineHeightStyle.Alignment, Object> getSaver(LineHeightStyle.Alignment.Companion $this$Saver) {
        return LineHeightStyleAlignmentSaver;
    }

    static final LineHeightStyle.Alignment LineHeightStyleAlignmentSaver$lambda$1(Object it) {
        Intrinsics.checkNotNull(it, "null cannot be cast to non-null type kotlin.Float");
        return LineHeightStyle.Alignment.m7962boximpl(LineHeightStyle.Alignment.m7963constructorimpl(((Float) it).floatValue()));
    }

    private static final Saver<LineHeightStyle.Trim, Object> getSaver(LineHeightStyle.Trim.Companion $this$Saver) {
        return LineHeightStyleTrimSaver;
    }

    static final LineHeightStyle.Trim LineHeightStyleTrimSaver$lambda$1(Object it) {
        Intrinsics.checkNotNull(it, "null cannot be cast to non-null type kotlin.Int");
        return LineHeightStyle.Trim.m7983boximpl(LineHeightStyle.Trim.m7984constructorimpl(((Integer) it).intValue()));
    }

    private static final Saver<LineHeightStyle.Mode, Object> getSaver(LineHeightStyle.Mode.Companion $this$Saver) {
        return LineHeightStyleModeSaver;
    }

    static final LineHeightStyle.Mode LineHeightStyleModeSaver$lambda$1(Object it) {
        Intrinsics.checkNotNull(it, "null cannot be cast to non-null type kotlin.Int");
        return LineHeightStyle.Mode.m7973boximpl(LineHeightStyle.Mode.m7974constructorimpl(((Integer) it).intValue()));
    }
}
