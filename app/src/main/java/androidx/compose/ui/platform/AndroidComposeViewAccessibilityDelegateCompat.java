package androidx.compose.ui.platform;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.os.SystemClock;
import android.os.Trace;
import android.text.SpannableString;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import androidx.collection.ArraySet;
import androidx.collection.IntIntMapKt;
import androidx.collection.IntList;
import androidx.collection.IntListKt;
import androidx.collection.IntObjectMap;
import androidx.collection.IntObjectMapKt;
import androidx.collection.IntSet;
import androidx.collection.IntSetKt;
import androidx.collection.MutableIntIntMap;
import androidx.collection.MutableIntObjectMap;
import androidx.collection.MutableIntSet;
import androidx.collection.MutableObjectIntMap;
import androidx.collection.ScatterSet;
import androidx.collection.SparseArrayCompat;
import androidx.compose.ui.AndroidComposeUiFlags;
import androidx.compose.ui.R;
import androidx.compose.ui.focus.FocusDirection;
import androidx.compose.ui.geometry.InlineClassHelperKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.RectKt;
import androidx.compose.ui.graphics.AndroidPath;
import androidx.compose.ui.graphics.Outline;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LayoutCoordinatesKt;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.HitTestResult;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.NodeKind;
import androidx.compose.ui.node.Owner;
import androidx.compose.ui.node.OwnerSnapshotObserver;
import androidx.compose.ui.platform.AccessibilityIterators;
import androidx.compose.ui.semantics.AccessibilityAction;
import androidx.compose.ui.semantics.CustomAccessibilityAction;
import androidx.compose.ui.semantics.ProgressBarRangeInfo;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.semantics.ScrollAxisRange;
import androidx.compose.ui.semantics.SemanticsActions;
import androidx.compose.ui.semantics.SemanticsConfiguration;
import androidx.compose.ui.semantics.SemanticsConfigurationKt;
import androidx.compose.ui.semantics.SemanticsNode;
import androidx.compose.ui.semantics.SemanticsNodeKt;
import androidx.compose.ui.semantics.SemanticsNodeWithAdjustedBounds;
import androidx.compose.ui.semantics.SemanticsNode_androidKt;
import androidx.compose.ui.semantics.SemanticsOwnerKt;
import androidx.compose.ui.semantics.SemanticsProperties;
import androidx.compose.ui.semantics.SemanticsPropertyKey;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.platform.AndroidAccessibilitySpannableString_androidKt;
import androidx.compose.ui.text.platform.URLSpanCache;
import androidx.compose.ui.unit.IntRect;
import androidx.compose.ui.unit.IntRectKt;
import androidx.compose.ui.unit.IntSizeKt;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.compose.ui.util.ListUtilsKt;
import androidx.compose.ui.viewinterop.AndroidViewHolder;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.app.NotificationCompat;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.core.view.accessibility.AccessibilityManagerCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.accessibility.AccessibilityNodeProviderCompat;
import androidx.lifecycle.Lifecycle;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import kotlin.Deprecated;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelKt;

/* JADX INFO: compiled from: AndroidComposeViewAccessibilityDelegateCompat.android.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000þ\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0014\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u001a\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0001\u0018\u0000 \u009d\u00022\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004:\n\u009d\u0002\u009e\u0002\u009f\u0002 \u0002¡\u0002B\u000f\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\b\u0010-\u001a\u00020.H\u0002J\u0010\u0010z\u001a\u00020.2\u0006\u0010\u0005\u001a\u00020{H\u0016J\u0010\u0010|\u001a\u00020.2\u0006\u0010\u0005\u001a\u00020{H\u0016J\u0010\u0010}\u001a\u00020.2\u0006\u0010~\u001a\u00020\u0016H\u0016J\u0010\u0010\u007f\u001a\u00020.2\u0006\u0010~\u001a\u00020\u0016H\u0016J.\u0010\u0080\u0001\u001a\u00020\u00162\u0007\u0010\u0081\u0001\u001a\u00020\u00162\u0007\u0010\u0082\u0001\u001a\u00020\f2\b\u0010\u0083\u0001\u001a\u00030\u0084\u0001H\u0000¢\u0006\u0006\b\u0085\u0001\u0010\u0086\u0001J<\u0010\u0080\u0001\u001a\u00020\u00162\f\u0010]\u001a\b\u0012\u0004\u0012\u00020_0^2\u0007\u0010\u0081\u0001\u001a\u00020\u00162\u0007\u0010\u0082\u0001\u001a\u00020\f2\b\u0010\u0083\u0001\u001a\u00030\u0084\u0001H\u0002¢\u0006\u0006\b\u0087\u0001\u0010\u0088\u0001J\t\u0010\u0089\u0001\u001a\u00020\u0016H\u0002J\u0014\u0010\u008a\u0001\u001a\u0004\u0018\u00010F2\u0007\u0010\u008b\u0001\u001a\u00020\fH\u0002J\u000b\u0010\u008c\u0001\u001a\u0004\u0018\u00010FH\u0002J\u0013\u0010\u008d\u0001\u001a\u00030\u008e\u00012\u0007\u0010\u008f\u0001\u001a\u00020_H\u0002J2\u0010\u0090\u0001\u001a\u00030\u008e\u00012\b\u0010\u0091\u0001\u001a\u00030\u0092\u00012\b\u0010\u0093\u0001\u001a\u00030\u0092\u00012\b\u0010\u0094\u0001\u001a\u00030\u0092\u00012\b\u0010\u0095\u0001\u001a\u00030\u0092\u0001H\u0002J%\u0010\u0096\u0001\u001a\u00020.2\u0007\u0010\u008b\u0001\u001a\u00020\f2\u0007\u0010\u0097\u0001\u001a\u00020F2\b\u0010\u0098\u0001\u001a\u00030\u0099\u0001H\u0002J\u001c\u0010\u009a\u0001\u001a\u00020.2\b\u0010\u008f\u0001\u001a\u00030\u0099\u00012\u0007\u0010\u0097\u0001\u001a\u00020FH\u0002J\u0017\u0010\u009b\u0001\u001a\u00020.*\u00020F2\b\u0010\u008f\u0001\u001a\u00030\u0099\u0001H\u0002J\u0011\u0010\u009c\u0001\u001a\u0005\u0018\u00010\u009d\u0001*\u00030\u009e\u0001H\u0002J\u001c\u0010\u009f\u0001\u001a\u00020.2\b\u0010\u008f\u0001\u001a\u00030\u0099\u00012\u0007\u0010\u0097\u0001\u001a\u00020FH\u0002J\u0012\u0010 \u0001\u001a\u00020\u00162\u0007\u0010\u008b\u0001\u001a\u00020\fH\u0002J\u0012\u0010¡\u0001\u001a\u00020\u00162\u0007\u0010\u008b\u0001\u001a\u00020\fH\u0002JA\u0010¢\u0001\u001a\u00020\u00162\u0007\u0010\u008b\u0001\u001a\u00020\f2\u0007\u0010£\u0001\u001a\u00020\f2\u000b\b\u0002\u0010¤\u0001\u001a\u0004\u0018\u00010\f2\u0011\b\u0002\u0010¥\u0001\u001a\n\u0012\u0004\u0012\u00020n\u0018\u00010+H\u0002¢\u0006\u0003\u0010¦\u0001J\u0012\u0010§\u0001\u001a\u00020\u00162\u0007\u0010¨\u0001\u001a\u00020\u0015H\u0002J\u001b\u0010©\u0001\u001a\u00020\u00152\u0007\u0010\u008b\u0001\u001a\u00020\f2\u0007\u0010£\u0001\u001a\u00020\fH\u0003JD\u0010ª\u0001\u001a\u00020\u00152\u0007\u0010\u008b\u0001\u001a\u00020\f2\t\u0010«\u0001\u001a\u0004\u0018\u00010\f2\t\u0010¬\u0001\u001a\u0004\u0018\u00010\f2\t\u0010\u00ad\u0001\u001a\u0004\u0018\u00010\f2\t\u0010®\u0001\u001a\u0004\u0018\u00010OH\u0002¢\u0006\u0003\u0010¯\u0001J\u0012\u0010°\u0001\u001a\u00020\u00162\u0007\u0010\u008b\u0001\u001a\u00020\fH\u0002J'\u0010±\u0001\u001a\u00020\u00162\u0007\u0010\u008b\u0001\u001a\u00020\f2\u0007\u0010²\u0001\u001a\u00020\f2\n\u0010³\u0001\u001a\u0005\u0018\u00010´\u0001H\u0002J\u000e\u0010µ\u0001\u001a\u00020\u0016*\u00030\u0099\u0001H\u0003J\u000e\u0010¶\u0001\u001a\u00020\u0016*\u00030\u0099\u0001H\u0002J,\u0010·\u0001\u001a\u00030\u0084\u0001*\u00030\u0099\u00012\b\u0010¸\u0001\u001a\u00030\u0099\u00012\b\u0010¹\u0001\u001a\u00030\u0084\u0001H\u0002¢\u0006\u0006\bº\u0001\u0010»\u0001J,\u0010¼\u0001\u001a\u00030\u0084\u0001*\u00030\u0099\u00012\b\u0010¸\u0001\u001a\u00030\u0099\u00012\b\u0010½\u0001\u001a\u00030\u0084\u0001H\u0002¢\u0006\u0006\b¾\u0001\u0010»\u0001J0\u0010¿\u0001\u001a\u00020.2\u0007\u0010\u008b\u0001\u001a\u00020\f2\u0007\u0010\u0097\u0001\u001a\u00020F2\u0007\u0010À\u0001\u001a\u00020n2\n\u0010³\u0001\u001a\u0005\u0018\u00010´\u0001H\u0002J(\u0010Ã\u0001\u001a\u00030Ä\u00012\b\u0010\u008f\u0001\u001a\u00030\u0099\u00012\b\u0010Å\u0001\u001a\u00030\u008e\u00012\b\u0010Æ\u0001\u001a\u00030Ç\u0001H\u0002J\u0019\u0010È\u0001\u001a\u00030Ä\u0001*\u00030\u008e\u00012\b\u0010Å\u0001\u001a\u00030\u008e\u0001H\u0002J\"\u0010É\u0001\u001a\u0005\u0018\u00010Ê\u00012\n\u0010Ë\u0001\u001a\u0005\u0018\u00010\u0099\u00012\b\u0010Ì\u0001\u001a\u00030Ä\u0001H\u0002J,\u0010Í\u0001\u001a\u00030Î\u0001*\u00030Ç\u00012\b\u0010Ï\u0001\u001a\u00030Ð\u00012\b\u0010Ñ\u0001\u001a\u00030Ò\u0001H\u0002¢\u0006\u0006\bÓ\u0001\u0010Ô\u0001J%\u0010Õ\u0001\u001a\u0005\u0018\u00010\u008e\u0001*\u00030Î\u00012\b\u0010Ö\u0001\u001a\u00030\u0092\u00012\b\u0010×\u0001\u001a\u00030\u0092\u0001H\u0002J\u0011\u0010Ø\u0001\u001a\u0005\u0018\u00010Ù\u0001*\u00030Î\u0001H\u0002J%\u0010Ú\u0001\u001a\u0005\u0018\u00010Û\u0001*\u00030Î\u00012\b\u0010Ö\u0001\u001a\u00030\u0092\u00012\b\u0010×\u0001\u001a\u00030\u0092\u0001H\u0002J'\u0010Õ\u0001\u001a\u00030\u008e\u0001*\u00030Ä\u00012\n\b\u0002\u0010Ö\u0001\u001a\u00030\u0092\u00012\n\b\u0002\u0010×\u0001\u001a\u00030\u0092\u0001H\u0002J\u0019\u0010Ü\u0001\u001a\u00020\u00162\b\u0010¨\u0001\u001a\u00030Ý\u0001H\u0000¢\u0006\u0003\bÞ\u0001J#\u0010ß\u0001\u001a\u00020\f2\b\u0010à\u0001\u001a\u00030\u0092\u00012\b\u0010á\u0001\u001a\u00030\u0092\u0001H\u0001¢\u0006\u0003\bâ\u0001J\u0012\u0010ã\u0001\u001a\u00020.2\u0007\u0010\u008b\u0001\u001a\u00020\fH\u0002J\u0013\u0010ä\u0001\u001a\u00030å\u00012\u0007\u0010æ\u0001\u001a\u00020{H\u0016J4\u0010ç\u0001\u001a\u0005\u0018\u0001Hè\u0001\"\t\b\u0000\u0010è\u0001*\u00020O2\n\u0010®\u0001\u001a\u0005\u0018\u0001Hè\u00012\t\b\u0001\u0010Ï\u0001\u001a\u00020\fH\u0002¢\u0006\u0003\u0010é\u0001J\u000f\u0010ì\u0001\u001a\u00020.H\u0000¢\u0006\u0003\bí\u0001J\u0013\u0010î\u0001\u001a\u00020.H\u0080@¢\u0006\u0006\bï\u0001\u0010ð\u0001J\u0018\u0010ñ\u0001\u001a\u00020.2\u0007\u0010ò\u0001\u001a\u00020WH\u0000¢\u0006\u0003\bó\u0001J\u0012\u0010ô\u0001\u001a\u00020.2\u0007\u0010ò\u0001\u001a\u00020WH\u0002J\u0012\u0010õ\u0001\u001a\u00020.2\u0007\u0010ò\u0001\u001a\u00020WH\u0002J\u001b\u0010ö\u0001\u001a\u00020.2\u0007\u0010ò\u0001\u001a\u00020W2\u0007\u0010÷\u0001\u001a\u00020cH\u0002J\t\u0010ø\u0001\u001a\u00020.H\u0002J\t\u0010ù\u0001\u001a\u00020.H\u0002J\u0018\u0010ú\u0001\u001a\u00020.2\r\u0010û\u0001\u001a\b\u0012\u0004\u0012\u00020_0^H\u0002J\"\u0010\u0080\u0002\u001a\u00020\u00162\u0007\u0010\u0081\u0002\u001a\u00020\f2\u000e\u0010\u0082\u0002\u001a\t\u0012\u0005\u0012\u00030þ\u00010+H\u0002J\u0013\u0010\u0083\u0002\u001a\u00020.2\b\u0010\u0084\u0002\u001a\u00030þ\u0001H\u0002J&\u0010\u0085\u0002\u001a\u00020.2\u0007\u0010\u0086\u0002\u001a\u00020\f2\u0007\u0010¤\u0001\u001a\u00020\f2\t\u0010\u0087\u0002\u001a\u0004\u0018\u00010nH\u0002J\u001c\u0010\u0088\u0002\u001a\u00020.2\b\u0010\u0089\u0002\u001a\u00030\u0099\u00012\u0007\u0010\u008a\u0002\u001a\u00020vH\u0002J\u0012\u0010\u008b\u0002\u001a\u00020\f2\u0007\u0010\u0081\u0002\u001a\u00020\fH\u0002J.\u0010\u008c\u0002\u001a\u00020\u00162\b\u0010\u008f\u0001\u001a\u00030\u0099\u00012\u0007\u0010\u008d\u0002\u001a\u00020\f2\u0007\u0010\u008e\u0002\u001a\u00020\u00162\u0007\u0010\u008f\u0002\u001a\u00020\u0016H\u0002J\u0012\u0010\u0090\u0002\u001a\u00020.2\u0007\u0010\u0086\u0002\u001a\u00020\fH\u0002J.\u0010\u0091\u0002\u001a\u00020\u00162\b\u0010\u008f\u0001\u001a\u00030\u0099\u00012\u0007\u0010\u0092\u0002\u001a\u00020\f2\u0007\u0010\u0093\u0002\u001a\u00020\f2\u0007\u0010\u0094\u0002\u001a\u00020\u0016H\u0002J\u0013\u0010\u0095\u0002\u001a\u00020\f2\b\u0010\u008f\u0001\u001a\u00030\u0099\u0001H\u0002J\u0013\u0010\u0096\u0002\u001a\u00020\f2\b\u0010\u008f\u0001\u001a\u00030\u0099\u0001H\u0002J\u0013\u0010\u0097\u0002\u001a\u00020\u00162\b\u0010\u008f\u0001\u001a\u00030\u0099\u0001H\u0002J!\u0010\u0098\u0002\u001a\u0005\u0018\u00010\u0099\u00022\n\u0010\u008f\u0001\u001a\u0005\u0018\u00010\u0099\u00012\u0007\u0010\u008d\u0002\u001a\u00020\fH\u0002J\u0017\u0010\u009a\u0002\u001a\u0004\u0018\u00010n2\n\u0010\u008f\u0001\u001a\u0005\u0018\u00010\u0099\u0001H\u0002J\u0011\u0010\u009b\u0002\u001a\u0005\u0018\u00010\u009e\u0001*\u00030\u009c\u0002H\u0002R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR$\u0010\u000b\u001a\u00020\f8\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R0\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00160\u00148\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u0017\u0010\u000e\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u000e\u0010\u001c\u001a\u00020\u001dX\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010\u001f\u001a\u00020\u00162\u0006\u0010\u001e\u001a\u00020\u0016@@X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u001a\u0010$\u001a\u00020%X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)R\u0016\u0010*\u001a\n\u0012\u0004\u0012\u00020,\u0018\u00010+X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010/\u001a\b\u0012\u0004\u0012\u00020,0+8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b0\u00101R\u0014\u00102\u001a\u00020\u00168@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b3\u0010!R\u0014\u00104\u001a\u00020\u00168BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b4\u0010!R\u001e\u00105\u001a\u0004\u0018\u00010\u0016X\u0080\u000e¢\u0006\u0010\n\u0002\u0010:\u001a\u0004\b6\u00107\"\u0004\b8\u00109R\u000e\u0010;\u001a\u00020<X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010=\u001a\u0004\u0018\u00010<8BX\u0082\u0004¢\u0006\f\u0012\u0004\b>\u0010\u000e\u001a\u0004\b?\u0010@R\u0012\u0010A\u001a\u00060BR\u00020\u0000X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010C\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010D\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010E\u001a\u0004\u0018\u00010FX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010G\u001a\u0004\u0018\u00010FX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010H\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010I\u001a\b\u0012\u0004\u0012\u00020K0JX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010L\u001a\b\u0012\u0004\u0012\u00020K0JX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010M\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020O0N0NX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010P\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020O0Q0NX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010R\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010S\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0004\n\u0002\u0010TR\u0014\u0010U\u001a\b\u0012\u0004\u0012\u00020W0VX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010X\u001a\b\u0012\u0004\u0012\u00020.0YX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010Z\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010[\u001a\u0004\u0018\u00010\\X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010]\u001a\b\u0012\u0004\u0012\u00020_0^8BX\u0082\u000e¢\u0006\b\n\u0000\u001a\u0004\b`\u0010aR\u000e\u0010b\u001a\u00020cX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010d\u001a\u00020eX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bf\u0010g\"\u0004\bh\u0010iR\u001a\u0010j\u001a\u00020eX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bk\u0010g\"\u0004\bl\u0010iR\u0014\u0010m\u001a\u00020nX\u0080D¢\u0006\b\n\u0000\u001a\u0004\bo\u0010pR\u0014\u0010q\u001a\u00020nX\u0080D¢\u0006\b\n\u0000\u001a\u0004\br\u0010pR\u000e\u0010s\u001a\u00020tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010u\u001a\b\u0012\u0004\u0012\u00020v0JX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010w\u001a\u00020vX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010x\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010y\u001a\u00020eX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u008d\u0001\u001a\u00030\u008e\u0001*\u00020F8BX\u0082\u0004¢\u0006\b\u001a\u0006\bÁ\u0001\u0010Â\u0001R\u0010\u0010ê\u0001\u001a\u00030ë\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010ü\u0001\u001a\n\u0012\u0005\u0012\u00030þ\u00010ý\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010ÿ\u0001\u001a\u000f\u0012\u0005\u0012\u00030þ\u0001\u0012\u0004\u0012\u00020.0\u0014X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006¢\u0002"}, d2 = {"Landroidx/compose/ui/platform/AndroidComposeViewAccessibilityDelegateCompat;", "Landroidx/core/view/AccessibilityDelegateCompat;", "Landroid/view/View$OnAttachStateChangeListener;", "Landroid/view/accessibility/AccessibilityManager$AccessibilityStateChangeListener;", "Landroid/view/accessibility/AccessibilityManager$TouchExplorationStateChangeListener;", "view", "Landroidx/compose/ui/platform/AndroidComposeView;", "<init>", "(Landroidx/compose/ui/platform/AndroidComposeView;)V", "getView", "()Landroidx/compose/ui/platform/AndroidComposeView;", "hoveredVirtualViewId", "", "getHoveredVirtualViewId$ui$annotations", "()V", "getHoveredVirtualViewId$ui", "()I", "setHoveredVirtualViewId$ui", "(I)V", "onSendAccessibilityEvent", "Lkotlin/Function1;", "Landroid/view/accessibility/AccessibilityEvent;", "", "getOnSendAccessibilityEvent$ui$annotations", "getOnSendAccessibilityEvent$ui", "()Lkotlin/jvm/functions/Function1;", "setOnSendAccessibilityEvent$ui", "(Lkotlin/jvm/functions/Function1;)V", "accessibilityManager", "Landroid/view/accessibility/AccessibilityManager;", "value", "accessibilityForceEnabledForTesting", "getAccessibilityForceEnabledForTesting$ui", "()Z", "setAccessibilityForceEnabledForTesting$ui", "(Z)V", "SendRecurringAccessibilityEventsIntervalMillis", "", "getSendRecurringAccessibilityEventsIntervalMillis$ui", "()J", "setSendRecurringAccessibilityEventsIntervalMillis$ui", "(J)V", "_enabledServices", "", "Landroid/accessibilityservice/AccessibilityServiceInfo;", "resetEnabledAccessibilityServiceList", "", "enabledServices", "getEnabledServices", "()Ljava/util/List;", "isEnabled", "isEnabled$ui", "isTouchExplorationEnabled", "requestFromAccessibilityToolForTesting", "getRequestFromAccessibilityToolForTesting$ui", "()Ljava/lang/Boolean;", "setRequestFromAccessibilityToolForTesting$ui", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "legacyMainHandler", "Landroid/os/Handler;", "handler", "getHandler$annotations", "getHandler", "()Landroid/os/Handler;", "nodeProvider", "Landroidx/compose/ui/platform/AndroidComposeViewAccessibilityDelegateCompat$ComposeAccessibilityNodeProvider;", "accessibilityFocusedVirtualViewId", "focusedVirtualViewId", "currentlyAccessibilityFocusedANI", "Landroidx/core/view/accessibility/AccessibilityNodeInfoCompat;", "currentlyFocusedANI", "sendingFocusAffectingEvent", "pendingHorizontalScrollEvents", "Landroidx/collection/MutableIntObjectMap;", "Landroidx/compose/ui/semantics/ScrollAxisRange;", "pendingVerticalScrollEvents", "actionIdToLabel", "Landroidx/collection/SparseArrayCompat;", "", "labelToActionId", "Landroidx/collection/MutableObjectIntMap;", "accessibilityCursorPosition", "previousTraversedNode", "Ljava/lang/Integer;", "subtreeChangedLayoutNodes", "Landroidx/collection/ArraySet;", "Landroidx/compose/ui/node/LayoutNode;", "boundsUpdateChannel", "Lkotlinx/coroutines/channels/Channel;", "currentSemanticsNodesInvalidated", "pendingTextTraversedEvent", "Landroidx/compose/ui/platform/AndroidComposeViewAccessibilityDelegateCompat$PendingTextTraversedEvent;", "currentSemanticsNodes", "Landroidx/collection/IntObjectMap;", "Landroidx/compose/ui/semantics/SemanticsNodeWithAdjustedBounds;", "getCurrentSemanticsNodes", "()Landroidx/collection/IntObjectMap;", "paneDisplayed", "Landroidx/collection/MutableIntSet;", "idToBeforeMap", "Landroidx/collection/MutableIntIntMap;", "getIdToBeforeMap$ui", "()Landroidx/collection/MutableIntIntMap;", "setIdToBeforeMap$ui", "(Landroidx/collection/MutableIntIntMap;)V", "idToAfterMap", "getIdToAfterMap$ui", "setIdToAfterMap$ui", "ExtraDataTestTraversalBeforeVal", "", "getExtraDataTestTraversalBeforeVal$ui", "()Ljava/lang/String;", "ExtraDataTestTraversalAfterVal", "getExtraDataTestTraversalAfterVal$ui", "urlSpanCache", "Landroidx/compose/ui/text/platform/URLSpanCache;", "previousSemanticsNodes", "Landroidx/compose/ui/platform/SemanticsNodeCopy;", "previousSemanticsRoot", "checkingForSemanticsChanges", "drawingOrder", "onViewAttachedToWindow", "Landroid/view/View;", "onViewDetachedFromWindow", "onAccessibilityStateChanged", "enabled", "onTouchExplorationStateChanged", "canScroll", "vertical", "direction", "position", "Landroidx/compose/ui/geometry/Offset;", "canScroll-0AR0LA0$ui", "(ZIJ)Z", "canScroll-moWRBKg", "(Landroidx/collection/IntObjectMap;ZIJ)Z", "isRequestFromAccessibilityTool", "createNodeInfo", "virtualViewId", "emptyNodeInfoOrNull", "boundsInScreen", "Landroid/graphics/Rect;", "node", "toBoundsInScreen", "left", "", "top", "right", "bottom", "populateAccessibilityNodeInfoProperties", "info", "semanticsNode", "Landroidx/compose/ui/semantics/SemanticsNode;", "setContentInvalid", "setInvisibleIfEmptyBounds", "toSpannableString", "Landroid/text/SpannableString;", "Landroidx/compose/ui/text/AnnotatedString;", "setText", "isAccessibilityFocused", "requestAccessibilityFocus", "sendEventForVirtualView", "eventType", "contentChangeType", "contentDescription", "(IILjava/lang/Integer;Ljava/util/List;)Z", "sendEvent", NotificationCompat.CATEGORY_EVENT, "createEvent", "createTextSelectionChangedEvent", "fromIndex", "toIndex", "itemCount", "text", "(ILjava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/CharSequence;)Landroid/view/accessibility/AccessibilityEvent;", "clearAccessibilityFocus", "performActionHelper", "action", "arguments", "Landroid/os/Bundle;", "legacyScrollOntoScreen", "scrollOntoScreen", "adjustForReversedScrollingAndRtl", "scrollableAncestor", TypedValues.CycleType.S_WAVE_OFFSET, "adjustForReversedScrollingAndRtl-RE3cj74", "(Landroidx/compose/ui/semantics/SemanticsNode;Landroidx/compose/ui/semantics/SemanticsNode;J)J", "scrollDxDyForNodeVisible", "offsetAdjustment", "scrollDxDyForNodeVisible-RE3cj74", "addExtraDataToAccessibilityNodeInfoHelper", "extraDataKey", "getBoundsInScreen", "(Landroidx/core/view/accessibility/AccessibilityNodeInfoCompat;)Landroid/graphics/Rect;", "getShapeBounds", "Landroidx/compose/ui/geometry/Rect;", "nodeBoundsInScreen", "shape", "Landroidx/compose/ui/graphics/Shape;", "toBoundsRelativeToNodeBounds", "toScreenCoords", "Landroid/graphics/RectF;", "textNode", "bounds", "createOutline", "Landroidx/compose/ui/graphics/Outline;", "size", "Landroidx/compose/ui/geometry/Size;", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "createOutline-12SF9DM", "(Landroidx/compose/ui/graphics/Shape;JLandroidx/compose/ui/unit/LayoutDirection;)Landroidx/compose/ui/graphics/Outline;", "toAndroidRect", "leftOffset", "topOffset", "toCornerArray", "", "toRegion", "Landroid/graphics/Region;", "dispatchHoverEvent", "Landroid/view/MotionEvent;", "dispatchHoverEvent$ui", "hitTestSemanticsAt", "x", "y", "hitTestSemanticsAt$ui", "updateHoveredVirtualView", "getAccessibilityNodeProvider", "Landroidx/core/view/accessibility/AccessibilityNodeProviderCompat;", "host", "trimToSize", "T", "(Ljava/lang/CharSequence;I)Ljava/lang/CharSequence;", "semanticsChangeChecker", "Ljava/lang/Runnable;", "onSemanticsChange", "onSemanticsChange$ui", "boundsUpdatesEventLoop", "boundsUpdatesEventLoop$ui", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onLayoutChange", "layoutNode", "onLayoutChange$ui", "notifySubtreeAccessibilityStateChangedIfNeeded", "sendTypeViewScrolledAccessibilityEvent", "sendSubtreeChangeAccessibilityEvents", "subtreeChangedSemanticsNodesIds", "checkForSemanticsChanges", "updateSemanticsNodesCopyAndPanes", "sendSemanticsPropertyChangeEvents", "newSemanticsNodes", "scrollObservationScopes", "", "Landroidx/compose/ui/platform/ScrollObservationScope;", "scheduleScrollEventIfNeededLambda", "registerScrollingId", "id", "oldScrollObservationScopes", "scheduleScrollEventIfNeeded", "scrollObservationScope", "sendPaneChangeEvents", "semanticsNodeId", "title", "sendAccessibilitySemanticsStructureChangeEvents", "newNode", "oldNode", "semanticsNodeIdToAccessibilityVirtualNodeId", "traverseAtGranularity", "granularity", "forward", "extendSelection", "sendPendingTextTraversedAtGranularityEvent", "setAccessibilitySelection", "start", "end", "traversalMode", "getAccessibilitySelectionStart", "getAccessibilitySelectionEnd", "isAccessibilitySelectionExtendable", "getIteratorForGranularity", "Landroidx/compose/ui/platform/AccessibilityIterators$TextSegmentIterator;", "getIterableTextForAccessibility", "getTextForTextField", "Landroidx/compose/ui/semantics/SemanticsConfiguration;", "Companion", "PendingTextTraversedEvent", "ComposeAccessibilityNodeProvider", "Api24Impl", "Api29Impl", "ui"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class AndroidComposeViewAccessibilityDelegateCompat extends AccessibilityDelegateCompat implements View.OnAttachStateChangeListener, AccessibilityManager.AccessibilityStateChangeListener, AccessibilityManager.TouchExplorationStateChangeListener {
    public static final int AccessibilityCursorPositionUndefined = -1;
    public static final int AccessibilitySliderStepsCount = 20;
    public static final int CONTENT_CHANGE_TYPE_CHECKED = 8192;
    public static final String ClassName = "android.view.View";
    public static final String ExtraDataIdKey = "androidx.compose.ui.semantics.id";
    public static final String ExtraDataShapeRectCornersKey = "androidx.compose.ui.semantics.shapeCorners";
    public static final String ExtraDataShapeRectKey = "androidx.compose.ui.semantics.shapeRect";
    public static final String ExtraDataShapeRegionKey = "androidx.compose.ui.semantics.shapeRegion";
    public static final int ExtraDataShapeTypeGeneric = 2;
    public static final String ExtraDataShapeTypeKey = "androidx.compose.ui.semantics.shapeType";
    public static final int ExtraDataShapeTypeRectangle = 0;
    public static final int ExtraDataShapeTypeRounded = 1;
    public static final String ExtraDataTestTagKey = "androidx.compose.ui.semantics.testTag";
    public static final int InvalidId = Integer.MIN_VALUE;
    public static final String LogTag = "AccessibilityDelegate";
    public static final int ParcelSafeTextLength = 100000;
    public static final String TextClassName = "android.widget.TextView";
    public static final String TextFieldClassName = "android.widget.EditText";
    public static final long TextTraversedEventTimeoutMillis = 1000;
    private final String ExtraDataTestTraversalAfterVal;
    private final String ExtraDataTestTraversalBeforeVal;
    private long SendRecurringAccessibilityEventsIntervalMillis;
    private List<? extends AccessibilityServiceInfo> _enabledServices;
    private int accessibilityCursorPosition;
    private int accessibilityFocusedVirtualViewId;
    private boolean accessibilityForceEnabledForTesting;
    private final android.view.accessibility.AccessibilityManager accessibilityManager;
    private SparseArrayCompat<SparseArrayCompat<CharSequence>> actionIdToLabel;
    private final Channel<Unit> boundsUpdateChannel;
    private boolean checkingForSemanticsChanges;
    private IntObjectMap<SemanticsNodeWithAdjustedBounds> currentSemanticsNodes;
    private boolean currentSemanticsNodesInvalidated;
    private AccessibilityNodeInfoCompat currentlyAccessibilityFocusedANI;
    private AccessibilityNodeInfoCompat currentlyFocusedANI;
    private final MutableIntIntMap drawingOrder;
    private int focusedVirtualViewId;
    private MutableIntIntMap idToAfterMap;
    private MutableIntIntMap idToBeforeMap;
    private SparseArrayCompat<MutableObjectIntMap<CharSequence>> labelToActionId;
    private final Handler legacyMainHandler;
    private ComposeAccessibilityNodeProvider nodeProvider;
    private MutableIntSet paneDisplayed;
    private final MutableIntObjectMap<ScrollAxisRange> pendingHorizontalScrollEvents;
    private PendingTextTraversedEvent pendingTextTraversedEvent;
    private final MutableIntObjectMap<ScrollAxisRange> pendingVerticalScrollEvents;
    private MutableIntObjectMap<SemanticsNodeCopy> previousSemanticsNodes;
    private SemanticsNodeCopy previousSemanticsRoot;
    private Integer previousTraversedNode;
    private Boolean requestFromAccessibilityToolForTesting;
    private final Function1<ScrollObservationScope, Unit> scheduleScrollEventIfNeededLambda;
    private final List<ScrollObservationScope> scrollObservationScopes;
    private final Runnable semanticsChangeChecker;
    private boolean sendingFocusAffectingEvent;
    private final ArraySet<LayoutNode> subtreeChangedLayoutNodes;
    private final URLSpanCache urlSpanCache;
    private final AndroidComposeView view;
    public static final int $stable = 8;
    private static final IntList AccessibilityActionsResourceIds = IntListKt.intListOf(R.id.accessibility_custom_action_0, R.id.accessibility_custom_action_1, R.id.accessibility_custom_action_2, R.id.accessibility_custom_action_3, R.id.accessibility_custom_action_4, R.id.accessibility_custom_action_5, R.id.accessibility_custom_action_6, R.id.accessibility_custom_action_7, R.id.accessibility_custom_action_8, R.id.accessibility_custom_action_9, R.id.accessibility_custom_action_10, R.id.accessibility_custom_action_11, R.id.accessibility_custom_action_12, R.id.accessibility_custom_action_13, R.id.accessibility_custom_action_14, R.id.accessibility_custom_action_15, R.id.accessibility_custom_action_16, R.id.accessibility_custom_action_17, R.id.accessibility_custom_action_18, R.id.accessibility_custom_action_19, R.id.accessibility_custom_action_20, R.id.accessibility_custom_action_21, R.id.accessibility_custom_action_22, R.id.accessibility_custom_action_23, R.id.accessibility_custom_action_24, R.id.accessibility_custom_action_25, R.id.accessibility_custom_action_26, R.id.accessibility_custom_action_27, R.id.accessibility_custom_action_28, R.id.accessibility_custom_action_29, R.id.accessibility_custom_action_30, R.id.accessibility_custom_action_31);
    private int hoveredVirtualViewId = Integer.MIN_VALUE;
    private Function1<? super AccessibilityEvent, Boolean> onSendAccessibilityEvent = new Function1<AccessibilityEvent, Boolean>() { // from class: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$onSendAccessibilityEvent$1
        {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public final Boolean invoke(AccessibilityEvent it) {
            return Boolean.valueOf(this.this$0.getView().getParent().requestSendAccessibilityEvent(this.this$0.getView(), it));
        }
    };

    private static /* synthetic */ void getHandler$annotations() {
    }

    public static /* synthetic */ void getHoveredVirtualViewId$ui$annotations() {
    }

    public static /* synthetic */ void getOnSendAccessibilityEvent$ui$annotations() {
    }

    public AndroidComposeViewAccessibilityDelegateCompat(AndroidComposeView view) {
        this.view = view;
        Object systemService = this.view.getContext().getSystemService("accessibility");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.view.accessibility.AccessibilityManager");
        this.accessibilityManager = (android.view.accessibility.AccessibilityManager) systemService;
        this.SendRecurringAccessibilityEventsIntervalMillis = 100L;
        this.legacyMainHandler = new Handler(Looper.getMainLooper());
        this.nodeProvider = new ComposeAccessibilityNodeProvider();
        this.accessibilityFocusedVirtualViewId = Integer.MIN_VALUE;
        this.focusedVirtualViewId = Integer.MIN_VALUE;
        int i = 0;
        int i2 = 1;
        DefaultConstructorMarker defaultConstructorMarker = null;
        this.pendingHorizontalScrollEvents = new MutableIntObjectMap<>(i, i2, defaultConstructorMarker);
        this.pendingVerticalScrollEvents = new MutableIntObjectMap<>(i, i2, defaultConstructorMarker);
        this.actionIdToLabel = new SparseArrayCompat<>(i, i2, defaultConstructorMarker);
        this.labelToActionId = new SparseArrayCompat<>(i, i2, defaultConstructorMarker);
        this.accessibilityCursorPosition = -1;
        this.subtreeChangedLayoutNodes = new ArraySet<>(i, i2, defaultConstructorMarker);
        this.boundsUpdateChannel = ChannelKt.Channel$default(1, null, null, 6, null);
        this.currentSemanticsNodesInvalidated = true;
        this.currentSemanticsNodes = IntObjectMapKt.intObjectMapOf();
        this.paneDisplayed = new MutableIntSet(i, i2, defaultConstructorMarker);
        this.idToBeforeMap = new MutableIntIntMap(i, i2, defaultConstructorMarker);
        this.idToAfterMap = new MutableIntIntMap(i, i2, defaultConstructorMarker);
        this.ExtraDataTestTraversalBeforeVal = "android.view.accessibility.extra.EXTRA_DATA_TEST_TRAVERSALBEFORE_VAL";
        this.ExtraDataTestTraversalAfterVal = "android.view.accessibility.extra.EXTRA_DATA_TEST_TRAVERSALAFTER_VAL";
        this.urlSpanCache = new URLSpanCache();
        this.previousSemanticsNodes = IntObjectMapKt.mutableIntObjectMapOf();
        this.previousSemanticsRoot = new SemanticsNodeCopy(this.view.getSemanticsOwner().getUnmergedRootSemanticsNode(), IntObjectMapKt.intObjectMapOf());
        this.drawingOrder = IntIntMapKt.mutableIntIntMapOf();
        this.view.addOnAttachStateChangeListener(this);
        this.semanticsChangeChecker = new Runnable() { // from class: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                AndroidComposeViewAccessibilityDelegateCompat.semanticsChangeChecker$lambda$0(this.f$0);
            }
        };
        this.scrollObservationScopes = new ArrayList();
        this.scheduleScrollEventIfNeededLambda = new Function1<ScrollObservationScope, Unit>() { // from class: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$scheduleScrollEventIfNeededLambda$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ScrollObservationScope scrollObservationScope) {
                invoke2(scrollObservationScope);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ScrollObservationScope it) {
                this.this$0.scheduleScrollEventIfNeeded(it);
            }
        };
    }

    public final AndroidComposeView getView() {
        return this.view;
    }

    /* JADX INFO: renamed from: getHoveredVirtualViewId$ui, reason: from getter */
    public final int getHoveredVirtualViewId() {
        return this.hoveredVirtualViewId;
    }

    public final void setHoveredVirtualViewId$ui(int i) {
        this.hoveredVirtualViewId = i;
    }

    public final Function1<AccessibilityEvent, Boolean> getOnSendAccessibilityEvent$ui() {
        return this.onSendAccessibilityEvent;
    }

    public final void setOnSendAccessibilityEvent$ui(Function1<? super AccessibilityEvent, Boolean> function1) {
        this.onSendAccessibilityEvent = function1;
    }

    /* JADX INFO: renamed from: getAccessibilityForceEnabledForTesting$ui, reason: from getter */
    public final boolean getAccessibilityForceEnabledForTesting() {
        return this.accessibilityForceEnabledForTesting;
    }

    public final void setAccessibilityForceEnabledForTesting$ui(boolean value) {
        this.accessibilityForceEnabledForTesting = value;
        this.currentSemanticsNodesInvalidated = true;
    }

    /* JADX INFO: renamed from: getSendRecurringAccessibilityEventsIntervalMillis$ui, reason: from getter */
    public final long getSendRecurringAccessibilityEventsIntervalMillis() {
        return this.SendRecurringAccessibilityEventsIntervalMillis;
    }

    public final void setSendRecurringAccessibilityEventsIntervalMillis$ui(long j) {
        this.SendRecurringAccessibilityEventsIntervalMillis = j;
    }

    private final void resetEnabledAccessibilityServiceList() {
        this._enabledServices = null;
    }

    private final List<AccessibilityServiceInfo> getEnabledServices() {
        List list = this._enabledServices;
        if (list == null) {
            List<AccessibilityServiceInfo> enabledAccessibilityServiceList = this.accessibilityManager.getEnabledAccessibilityServiceList(-1);
            this._enabledServices = enabledAccessibilityServiceList;
            return enabledAccessibilityServiceList;
        }
        return list;
    }

    public final boolean isEnabled$ui() {
        return this.accessibilityForceEnabledForTesting || (this.accessibilityManager.isEnabled() && !getEnabledServices().isEmpty());
    }

    private final boolean isTouchExplorationEnabled() {
        return this.accessibilityForceEnabledForTesting || (this.accessibilityManager.isEnabled() && this.accessibilityManager.isTouchExplorationEnabled());
    }

    /* JADX INFO: renamed from: getRequestFromAccessibilityToolForTesting$ui, reason: from getter */
    public final Boolean getRequestFromAccessibilityToolForTesting() {
        return this.requestFromAccessibilityToolForTesting;
    }

    public final void setRequestFromAccessibilityToolForTesting$ui(Boolean bool) {
        this.requestFromAccessibilityToolForTesting = bool;
    }

    private final Handler getHandler() {
        if (AndroidComposeUiFlags.isViewBasedSemanticsHandlerEnabled) {
            return this.view.getHandler();
        }
        return this.legacyMainHandler;
    }

    /* JADX INFO: compiled from: AndroidComposeViewAccessibilityDelegateCompat.android.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\f\b\u0002\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0010R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015¨\u0006\u0016"}, d2 = {"Landroidx/compose/ui/platform/AndroidComposeViewAccessibilityDelegateCompat$PendingTextTraversedEvent;", "", "node", "Landroidx/compose/ui/semantics/SemanticsNode;", "action", "", "granularity", "fromIndex", "toIndex", "traverseTime", "", "<init>", "(Landroidx/compose/ui/semantics/SemanticsNode;IIIIJ)V", "getNode", "()Landroidx/compose/ui/semantics/SemanticsNode;", "getAction", "()I", "getGranularity", "getFromIndex", "getToIndex", "getTraverseTime", "()J", "ui"}, k = 1, mv = {2, 1, 0}, xi = 48)
    private static final class PendingTextTraversedEvent {
        private final int action;
        private final int fromIndex;
        private final int granularity;
        private final SemanticsNode node;
        private final int toIndex;
        private final long traverseTime;

        public PendingTextTraversedEvent(SemanticsNode node, int action, int granularity, int fromIndex, int toIndex, long traverseTime) {
            this.node = node;
            this.action = action;
            this.granularity = granularity;
            this.fromIndex = fromIndex;
            this.toIndex = toIndex;
            this.traverseTime = traverseTime;
        }

        public final SemanticsNode getNode() {
            return this.node;
        }

        public final int getAction() {
            return this.action;
        }

        public final int getGranularity() {
            return this.granularity;
        }

        public final int getFromIndex() {
            return this.fromIndex;
        }

        public final int getToIndex() {
            return this.toIndex;
        }

        public final long getTraverseTime() {
            return this.traverseTime;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final IntObjectMap<SemanticsNodeWithAdjustedBounds> getCurrentSemanticsNodes() {
        if (this.currentSemanticsNodesInvalidated) {
            this.currentSemanticsNodesInvalidated = false;
            this.currentSemanticsNodes = SemanticsOwnerKt.getAllUncoveredSemanticsNodesToIntObjectMap(this.view.getSemanticsOwner(), -1, new Function1<SemanticsNode, Boolean>() { // from class: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$currentSemanticsNodes$1
                @Override // kotlin.jvm.functions.Function1
                public final Boolean invoke(SemanticsNode it) {
                    return Boolean.valueOf(SemanticsNode_androidKt.isAccessibilityIgnoredLink(it));
                }
            });
            if (isEnabled$ui()) {
                AndroidComposeViewAccessibilityDelegateCompat_androidKt.setTraversalValues(this.currentSemanticsNodes, this.idToBeforeMap, this.idToAfterMap, this.view.getContext().getResources());
            }
        }
        return this.currentSemanticsNodes;
    }

    /* JADX INFO: renamed from: getIdToBeforeMap$ui, reason: from getter */
    public final MutableIntIntMap getIdToBeforeMap() {
        return this.idToBeforeMap;
    }

    public final void setIdToBeforeMap$ui(MutableIntIntMap mutableIntIntMap) {
        this.idToBeforeMap = mutableIntIntMap;
    }

    /* JADX INFO: renamed from: getIdToAfterMap$ui, reason: from getter */
    public final MutableIntIntMap getIdToAfterMap() {
        return this.idToAfterMap;
    }

    public final void setIdToAfterMap$ui(MutableIntIntMap mutableIntIntMap) {
        this.idToAfterMap = mutableIntIntMap;
    }

    /* JADX INFO: renamed from: getExtraDataTestTraversalBeforeVal$ui, reason: from getter */
    public final String getExtraDataTestTraversalBeforeVal() {
        return this.ExtraDataTestTraversalBeforeVal;
    }

    /* JADX INFO: renamed from: getExtraDataTestTraversalAfterVal$ui, reason: from getter */
    public final String getExtraDataTestTraversalAfterVal() {
        return this.ExtraDataTestTraversalAfterVal;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewAttachedToWindow(View view) {
        if (this.accessibilityManager.isEnabled()) {
            resetEnabledAccessibilityServiceList();
        }
        this.accessibilityManager.addAccessibilityStateChangeListener(this);
        this.accessibilityManager.addTouchExplorationStateChangeListener(this);
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewDetachedFromWindow(View view) {
        Handler handler = getHandler();
        Intrinsics.checkNotNull(handler);
        handler.removeCallbacks(this.semanticsChangeChecker);
        this.accessibilityManager.removeAccessibilityStateChangeListener(this);
        this.accessibilityManager.removeTouchExplorationStateChangeListener(this);
    }

    @Override // android.view.accessibility.AccessibilityManager.AccessibilityStateChangeListener
    public void onAccessibilityStateChanged(boolean enabled) {
        resetEnabledAccessibilityServiceList();
    }

    @Override // android.view.accessibility.AccessibilityManager.TouchExplorationStateChangeListener
    public void onTouchExplorationStateChanged(boolean enabled) {
        resetEnabledAccessibilityServiceList();
    }

    /* JADX INFO: renamed from: canScroll-0AR0LA0$ui, reason: not valid java name */
    public final boolean m7228canScroll0AR0LA0$ui(boolean vertical, int direction, long position) {
        if (!Intrinsics.areEqual(Looper.getMainLooper().getThread(), Thread.currentThread())) {
            return false;
        }
        return m7225canScrollmoWRBKg(getCurrentSemanticsNodes(), vertical, direction, position);
    }

    /* JADX INFO: renamed from: canScroll-moWRBKg, reason: not valid java name */
    private final boolean m7225canScrollmoWRBKg(IntObjectMap<SemanticsNodeWithAdjustedBounds> currentSemanticsNodes, boolean vertical, int direction, long position) {
        SemanticsPropertyKey<ScrollAxisRange> horizontalScrollAxisRange;
        int i;
        ScrollAxisRange scrollRange;
        if (Offset.m5065equalsimpl0(position, Offset.INSTANCE.m5083getUnspecifiedF1C5BW0())) {
            return false;
        }
        long v$iv = 9223372034707292159L & position;
        if ((((InlineClassHelperKt.DualLoadedSignificand + v$iv) & (-9223372034707292160L)) == 0 ? 1 : 0) == 0) {
            return false;
        }
        if (vertical) {
            horizontalScrollAxisRange = SemanticsProperties.INSTANCE.getVerticalScrollAxisRange();
        } else {
            if (vertical) {
                throw new NoWhenBranchMatchedException();
            }
            horizontalScrollAxisRange = SemanticsProperties.INSTANCE.getHorizontalScrollAxisRange();
        }
        boolean foundNode = false;
        Object[] v$iv2 = currentSemanticsNodes.values;
        int $i$f$forEachIndexed = 0;
        long[] m$iv$iv = currentSemanticsNodes.metadata;
        int lastIndex$iv$iv = m$iv$iv.length - 2;
        int i$iv$iv = 0;
        if (0 <= lastIndex$iv$iv) {
            while (true) {
                long slot$iv$iv = m$iv$iv[i$iv$iv];
                boolean foundNode2 = foundNode;
                int $i$f$forEachIndexed2 = $i$f$forEachIndexed;
                long $this$maskEmptyOrDeleted$iv$iv$iv = ((~slot$iv$iv) << 7) & slot$iv$iv & (-9187201950435737472L);
                if ($this$maskEmptyOrDeleted$iv$iv$iv == -9187201950435737472L) {
                    foundNode = foundNode2;
                } else {
                    int i2 = 8;
                    int bitCount$iv$iv = 8 - ((~(i$iv$iv - lastIndex$iv$iv)) >>> 31);
                    int j$iv$iv = 0;
                    while (j$iv$iv < bitCount$iv$iv) {
                        long value$iv$iv$iv = slot$iv$iv & 255;
                        int $i$f$isFull = value$iv$iv$iv < 128 ? 1 : 0;
                        if ($i$f$isFull == 0) {
                            i = i2;
                        } else {
                            int index$iv$iv = (i$iv$iv << 3) + j$iv$iv;
                            SemanticsNodeWithAdjustedBounds node = (SemanticsNodeWithAdjustedBounds) v$iv2[index$iv$iv];
                            i = i2;
                            if (IntRectKt.toRect(node.getAdjustedBounds()).m5094containsk4lQ0M(position) && (scrollRange = (ScrollAxisRange) SemanticsConfigurationKt.getOrNull(node.getSemanticsNode().getUnmergedConfig(), horizontalScrollAxisRange)) != null) {
                                int actualDirection = scrollRange.getReverseScrolling() ? -direction : direction;
                                if (direction == 0 && scrollRange.getReverseScrolling()) {
                                    actualDirection = -1;
                                }
                                if (actualDirection < 0) {
                                    if (scrollRange.getValue().invoke().floatValue() > 0.0f) {
                                        foundNode2 = true;
                                    }
                                } else if (scrollRange.getValue().invoke().floatValue() < scrollRange.getMaxValue().invoke().floatValue()) {
                                    foundNode2 = true;
                                }
                            }
                        }
                        slot$iv$iv >>= i;
                        j$iv$iv++;
                        i2 = i;
                    }
                    if (bitCount$iv$iv != i2) {
                        return foundNode2;
                    }
                    foundNode = foundNode2;
                }
                if (i$iv$iv == lastIndex$iv$iv) {
                    break;
                }
                i$iv$iv++;
                $i$f$forEachIndexed = $i$f$forEachIndexed2;
            }
        }
        return foundNode;
    }

    private final boolean isRequestFromAccessibilityTool() {
        Boolean bool = this.requestFromAccessibilityToolForTesting;
        if (Intrinsics.areEqual((Object) bool, (Object) true)) {
            return true;
        }
        if (Intrinsics.areEqual((Object) bool, (Object) false)) {
            return false;
        }
        return AccessibilityManagerCompat.isRequestFromAccessibilityTool(this.accessibilityManager);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public final AccessibilityNodeInfoCompat createNodeInfo(int virtualViewId) {
        if (this.view.getComposeViewContext().getLifecycleOwner().getLifecycleRegistry().getState() == Lifecycle.State.DESTROYED) {
            return emptyNodeInfoOrNull();
        }
        SemanticsNodeWithAdjustedBounds semanticsNodeWithAdjustedBounds = getCurrentSemanticsNodes().get(virtualViewId);
        if (semanticsNodeWithAdjustedBounds == null) {
            return emptyNodeInfoOrNull();
        }
        SemanticsNode semanticsNode = semanticsNodeWithAdjustedBounds.getSemanticsNode();
        boolean zAreEqual = Intrinsics.areEqual(SemanticsConfigurationKt.getOrNull(semanticsNode.getConfig(), SemanticsProperties.INSTANCE.getIsSensitiveData()), (Object) true);
        if (zAreEqual && !isRequestFromAccessibilityTool()) {
            return null;
        }
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompatObtain = AccessibilityNodeInfoCompat.obtain();
        accessibilityNodeInfoCompatObtain.setAccessibilityDataSensitive(zAreEqual);
        if (virtualViewId == -1) {
            ViewParent parentForAccessibility = this.view.getParentForAccessibility();
            accessibilityNodeInfoCompatObtain.setParent(parentForAccessibility instanceof View ? (View) parentForAccessibility : null);
        } else {
            SemanticsNode parent = semanticsNode.getParent();
            Integer numValueOf = parent != null ? Integer.valueOf(parent.getId()) : null;
            if (numValueOf != null) {
                int iIntValue = numValueOf.intValue();
                if (iIntValue == this.view.getSemanticsOwner().getUnmergedRootSemanticsNode().getId()) {
                    iIntValue = -1;
                }
                accessibilityNodeInfoCompatObtain.setParent(this.view, iIntValue);
            } else {
                androidx.compose.ui.internal.InlineClassHelperKt.throwIllegalStateExceptionForNullCheck("semanticsNode " + virtualViewId + " has null parent");
                throw new KotlinNothingValueException();
            }
        }
        accessibilityNodeInfoCompatObtain.setSource(this.view, virtualViewId);
        accessibilityNodeInfoCompatObtain.setBoundsInScreen(boundsInScreen(semanticsNodeWithAdjustedBounds));
        populateAccessibilityNodeInfoProperties(virtualViewId, accessibilityNodeInfoCompatObtain, semanticsNode);
        return accessibilityNodeInfoCompatObtain;
    }

    private final AccessibilityNodeInfoCompat emptyNodeInfoOrNull() {
        if (!this.accessibilityManager.isEnabled()) {
            return AccessibilityNodeInfoCompat.obtain();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Rect boundsInScreen(SemanticsNodeWithAdjustedBounds node) {
        IntRect boundsInRoot = node.getAdjustedBounds();
        return toBoundsInScreen(boundsInRoot.getLeft(), boundsInRoot.getTop(), boundsInRoot.getRight(), boundsInRoot.getBottom());
    }

    private final Rect toBoundsInScreen(float left, float top, float right, float bottom) {
        AndroidComposeView androidComposeView = this.view;
        long v1$iv$iv = Float.floatToRawIntBits(left);
        long v2$iv$iv = Float.floatToRawIntBits(top);
        long topLeftInScreen = androidComposeView.mo6732localToScreenMKHz9U(Offset.m5060constructorimpl((v1$iv$iv << 32) | (v2$iv$iv & 4294967295L)));
        AndroidComposeView androidComposeView2 = this.view;
        long v1$iv$iv2 = Float.floatToRawIntBits(right);
        long v2$iv$iv2 = Float.floatToRawIntBits(bottom);
        long bottomRightInScreen = androidComposeView2.mo6732localToScreenMKHz9U(Offset.m5060constructorimpl((v1$iv$iv2 << 32) | (v2$iv$iv2 & 4294967295L)));
        int bits$iv$iv$iv = (int) (topLeftInScreen >> 32);
        int bits$iv$iv$iv2 = (int) (bottomRightInScreen >> 32);
        int iFloor = (int) Math.floor(Math.min(Float.intBitsToFloat(bits$iv$iv$iv), Float.intBitsToFloat(bits$iv$iv$iv2)));
        int bits$iv$iv$iv3 = (int) (topLeftInScreen & 4294967295L);
        int bits$iv$iv$iv4 = (int) (bottomRightInScreen & 4294967295L);
        int iFloor2 = (int) Math.floor(Math.min(Float.intBitsToFloat(bits$iv$iv$iv3), Float.intBitsToFloat(bits$iv$iv$iv4)));
        int bits$iv$iv$iv5 = (int) (topLeftInScreen >> 32);
        int bits$iv$iv$iv6 = (int) (bottomRightInScreen >> 32);
        int iCeil = (int) Math.ceil(Math.max(Float.intBitsToFloat(bits$iv$iv$iv5), Float.intBitsToFloat(bits$iv$iv$iv6)));
        int bits$iv$iv$iv7 = (int) (topLeftInScreen & 4294967295L);
        int bits$iv$iv$iv8 = (int) (bottomRightInScreen & 4294967295L);
        return new Rect(iFloor, iFloor2, iCeil, (int) Math.ceil(Math.max(Float.intBitsToFloat(bits$iv$iv$iv7), Float.intBitsToFloat(bits$iv$iv$iv8))));
    }

    /* JADX WARN: Removed duplicated region for block: B:153:0x046d  */
    /* JADX WARN: Removed duplicated region for block: B:261:0x0763  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void populateAccessibilityNodeInfoProperties(int r37, androidx.core.view.accessibility.AccessibilityNodeInfoCompat r38, androidx.compose.ui.semantics.SemanticsNode r39) {
        /*
            Method dump skipped, instruction units count: 3170
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat.populateAccessibilityNodeInfoProperties(int, androidx.core.view.accessibility.AccessibilityNodeInfoCompat, androidx.compose.ui.semantics.SemanticsNode):void");
    }

    private static final boolean populateAccessibilityNodeInfoProperties$canScrollForward(ScrollAxisRange $this$populateAccessibilityNodeInfoProperties_u24canScrollForward) {
        return ($this$populateAccessibilityNodeInfoProperties_u24canScrollForward.getValue().invoke().floatValue() < $this$populateAccessibilityNodeInfoProperties_u24canScrollForward.getMaxValue().invoke().floatValue() && !$this$populateAccessibilityNodeInfoProperties_u24canScrollForward.getReverseScrolling()) || ($this$populateAccessibilityNodeInfoProperties_u24canScrollForward.getValue().invoke().floatValue() > 0.0f && $this$populateAccessibilityNodeInfoProperties_u24canScrollForward.getReverseScrolling());
    }

    private static final boolean populateAccessibilityNodeInfoProperties$canScrollBackward(ScrollAxisRange $this$populateAccessibilityNodeInfoProperties_u24canScrollBackward) {
        return ($this$populateAccessibilityNodeInfoProperties_u24canScrollBackward.getValue().invoke().floatValue() > 0.0f && !$this$populateAccessibilityNodeInfoProperties_u24canScrollBackward.getReverseScrolling()) || ($this$populateAccessibilityNodeInfoProperties_u24canScrollBackward.getValue().invoke().floatValue() < $this$populateAccessibilityNodeInfoProperties_u24canScrollBackward.getMaxValue().invoke().floatValue() && $this$populateAccessibilityNodeInfoProperties_u24canScrollBackward.getReverseScrolling());
    }

    private final void setContentInvalid(SemanticsNode node, AccessibilityNodeInfoCompat info) {
        if (node.getUnmergedConfig().contains(SemanticsProperties.INSTANCE.getError())) {
            info.setContentInvalid(true);
            info.setError((CharSequence) SemanticsConfigurationKt.getOrNull(node.getUnmergedConfig(), SemanticsProperties.INSTANCE.getError()));
        }
    }

    private final void setInvisibleIfEmptyBounds(AccessibilityNodeInfoCompat $this$setInvisibleIfEmptyBounds, SemanticsNode node) {
        if (node.getTouchBoundsInRoot().isEmpty()) {
            $this$setInvisibleIfEmptyBounds.setVisibleToUser(false);
        }
    }

    private final SpannableString toSpannableString(AnnotatedString $this$toSpannableString) {
        FontFamily.Resolver fontFamilyResolver = this.view.getFontFamilyResolver();
        return (SpannableString) trimToSize(AndroidAccessibilitySpannableString_androidKt.toAccessibilitySpannableString($this$toSpannableString, this.view.getDensity(), fontFamilyResolver, this.urlSpanCache), ParcelSafeTextLength);
    }

    private final void setText(SemanticsNode node, AccessibilityNodeInfoCompat info) {
        AnnotatedString infoText = AndroidComposeViewAccessibilityDelegateCompat_androidKt.getInfoText(node);
        info.setText(infoText != null ? toSpannableString(infoText) : null);
    }

    private final boolean isAccessibilityFocused(int virtualViewId) {
        return this.accessibilityFocusedVirtualViewId == virtualViewId;
    }

    private final boolean requestAccessibilityFocus(int virtualViewId) {
        if (!isTouchExplorationEnabled() || isAccessibilityFocused(virtualViewId)) {
            return false;
        }
        if (this.accessibilityFocusedVirtualViewId != Integer.MIN_VALUE) {
            sendEventForVirtualView$default(this, this.accessibilityFocusedVirtualViewId, 65536, null, null, 12, null);
        }
        this.accessibilityFocusedVirtualViewId = virtualViewId;
        this.view.invalidate();
        sendEventForVirtualView$default(this, virtualViewId, 32768, null, null, 12, null);
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ boolean sendEventForVirtualView$default(AndroidComposeViewAccessibilityDelegateCompat androidComposeViewAccessibilityDelegateCompat, int i, int i2, Integer num, List list, int i3, Object obj) {
        if ((i3 & 4) != 0) {
            num = null;
        }
        if ((i3 & 8) != 0) {
            list = null;
        }
        return androidComposeViewAccessibilityDelegateCompat.sendEventForVirtualView(i, i2, num, list);
    }

    private final boolean sendEventForVirtualView(int virtualViewId, int eventType, Integer contentChangeType, List<String> contentDescription) {
        if (virtualViewId != Integer.MIN_VALUE && isEnabled$ui()) {
            AccessibilityEvent event = createEvent(virtualViewId, eventType);
            if (contentChangeType != null) {
                event.setContentChangeTypes(contentChangeType.intValue());
            }
            if (contentDescription != null) {
                event.setContentDescription(ListUtilsKt.fastJoinToString$default(contentDescription, ",", null, null, 0, null, null, 62, null));
            }
            return sendEvent(event);
        }
        return false;
    }

    private final boolean sendEvent(AccessibilityEvent event) {
        if (!isEnabled$ui()) {
            return false;
        }
        if (event.getEventType() == 2048 || event.getEventType() == 32768) {
            this.sendingFocusAffectingEvent = true;
        }
        try {
            return this.onSendAccessibilityEvent.invoke(event).booleanValue();
        } finally {
            this.sendingFocusAffectingEvent = false;
        }
    }

    private final AccessibilityEvent createEvent(int virtualViewId, int eventType) {
        SemanticsNodeWithAdjustedBounds it;
        AccessibilityEvent event = AccessibilityEvent.obtain(eventType);
        event.setEnabled(true);
        event.setClassName(ClassName);
        event.setPackageName(this.view.getContext().getPackageName());
        event.setSource(this.view, virtualViewId);
        if (isEnabled$ui() && (it = getCurrentSemanticsNodes().get(virtualViewId)) != null) {
            event.setPassword(it.getSemanticsNode().getUnmergedConfig().contains(SemanticsProperties.INSTANCE.getPassword()));
            AccessibilityEventCompat.setAccessibilityDataSensitive(event, Intrinsics.areEqual(SemanticsConfigurationKt.getOrNull(it.getSemanticsNode().getUnmergedConfig(), SemanticsProperties.INSTANCE.getIsSensitiveData()), (Object) true));
        }
        return event;
    }

    private final AccessibilityEvent createTextSelectionChangedEvent(int virtualViewId, Integer fromIndex, Integer toIndex, Integer itemCount, CharSequence text) {
        AccessibilityEvent $this$createTextSelectionChangedEvent_u24lambda_u240 = createEvent(virtualViewId, 8192);
        if (fromIndex != null) {
            int it = fromIndex.intValue();
            $this$createTextSelectionChangedEvent_u24lambda_u240.setFromIndex(it);
        }
        if (toIndex != null) {
            int it2 = toIndex.intValue();
            $this$createTextSelectionChangedEvent_u24lambda_u240.setToIndex(it2);
        }
        if (itemCount != null) {
            int it3 = itemCount.intValue();
            $this$createTextSelectionChangedEvent_u24lambda_u240.setItemCount(it3);
        }
        if (text != null) {
            $this$createTextSelectionChangedEvent_u24lambda_u240.getText().add(text);
        }
        return $this$createTextSelectionChangedEvent_u24lambda_u240;
    }

    private final boolean clearAccessibilityFocus(int virtualViewId) {
        if (!isAccessibilityFocused(virtualViewId)) {
            return false;
        }
        this.accessibilityFocusedVirtualViewId = Integer.MIN_VALUE;
        this.currentlyAccessibilityFocusedANI = null;
        this.view.invalidate();
        sendEventForVirtualView$default(this, virtualViewId, 65536, null, null, 12, null);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v10, types: [java.lang.Boolean] */
    /* JADX WARN: Type inference failed for: r1v138 */
    /* JADX WARN: Type inference failed for: r1v139 */
    /* JADX WARN: Type inference failed for: r1v25, types: [java.util.List, kotlin.jvm.internal.DefaultConstructorMarker] */
    /* JADX WARN: Type inference failed for: r1v33 */
    public final boolean performActionHelper(int virtualViewId, int action, Bundle arguments) {
        SemanticsNode semanticsNode;
        AccessibilityAction accessibilityAction;
        Function0 function0;
        boolean z;
        boolean z2;
        Float f;
        float f2;
        float fIntBitsToFloat;
        CharSequence charSequence;
        List list;
        SemanticsNodeWithAdjustedBounds semanticsNodeWithAdjustedBounds = getCurrentSemanticsNodes().get(virtualViewId);
        if (semanticsNodeWithAdjustedBounds != null && (semanticsNode = semanticsNodeWithAdjustedBounds.getSemanticsNode()) != null) {
            if (Intrinsics.areEqual(SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsProperties.INSTANCE.getIsSensitiveData()), (Object) true) && !isRequestFromAccessibilityTool()) {
                return false;
            }
            switch (action) {
                case 64:
                    break;
                case 128:
                    break;
                case 256:
                case 512:
                    if (arguments != null) {
                    }
                    break;
                case 16384:
                    AccessibilityAction accessibilityAction2 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsActions.INSTANCE.getCopyText());
                    if (accessibilityAction2 != null && (r1 = (Function0) accessibilityAction2.getAction()) != null) {
                        break;
                    }
                    break;
                case 131072:
                    boolean accessibilitySelection = setAccessibilitySelection(semanticsNode, arguments != null ? arguments.getInt(AccessibilityNodeInfoCompat.ACTION_ARGUMENT_SELECTION_START_INT, -1) : -1, arguments != null ? arguments.getInt(AccessibilityNodeInfoCompat.ACTION_ARGUMENT_SELECTION_END_INT, -1) : -1, false);
                    if (accessibilitySelection) {
                        sendEventForVirtualView$default(this, semanticsNodeIdToAccessibilityVirtualNodeId(semanticsNode.getId()), 0, null, null, 12, null);
                    }
                    break;
                default:
                    if (AndroidComposeViewAccessibilityDelegateCompat_androidKt.enabled(semanticsNode)) {
                        ?? r1 = 0;
                        r1 = 0;
                        switch (action) {
                            case 1:
                                if (this.view.isInTouchMode()) {
                                    this.view.requestFocusFromTouch();
                                }
                                AccessibilityAction accessibilityAction3 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsActions.INSTANCE.getRequestFocus());
                                if (accessibilityAction3 != null && (r2 = (Function0) accessibilityAction3.getAction()) != null) {
                                    break;
                                }
                                break;
                            case 2:
                                if (Intrinsics.areEqual(SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsProperties.INSTANCE.getFocused()), (Object) true)) {
                                    this.view.getFocusOwner().mo4959clearFocusI7lrPNg(false, true, true, FocusDirection.INSTANCE.m4949getExitdhqQ8s());
                                }
                                break;
                            case 16:
                                AccessibilityAction accessibilityAction4 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsActions.INSTANCE.getOnClick());
                                if (accessibilityAction4 != null && (function0 = (Function0) accessibilityAction4.getAction()) != null) {
                                    r1 = (Boolean) function0.invoke();
                                }
                                ?? r10 = r1;
                                sendEventForVirtualView$default(this, virtualViewId, 1, null, null, 12, null);
                                if (r10 != 0) {
                                }
                                break;
                            case 32:
                                AccessibilityAction accessibilityAction5 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsActions.INSTANCE.getOnLongClick());
                                if (accessibilityAction5 != null && (r0 = (Function0) accessibilityAction5.getAction()) != null) {
                                }
                                break;
                            case 4096:
                            case 8192:
                            case android.R.id.accessibilityActionScrollUp:
                            case android.R.id.accessibilityActionScrollLeft:
                            case android.R.id.accessibilityActionScrollDown:
                            case android.R.id.accessibilityActionScrollRight:
                                boolean z3 = action == 4096;
                                boolean z4 = action == 8192;
                                boolean z5 = action == 16908345;
                                boolean z6 = action == 16908347;
                                boolean z7 = action == 16908344;
                                boolean z8 = action == 16908346;
                                boolean z9 = z5 || z6 || z3 || z4;
                                boolean z10 = z7 || z8 || z3 || z4;
                                if (z3 || z4) {
                                    z = true;
                                    ProgressBarRangeInfo progressBarRangeInfo = (ProgressBarRangeInfo) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsProperties.INSTANCE.getProgressBarRangeInfo());
                                    z2 = false;
                                    AccessibilityAction accessibilityAction6 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsActions.INSTANCE.getSetProgress());
                                    if (progressBarRangeInfo != null && accessibilityAction6 != null) {
                                        float fCoerceAtLeast = RangesKt.coerceAtLeast(progressBarRangeInfo.getRange().getEndInclusive().floatValue(), progressBarRangeInfo.getRange().getStart().floatValue());
                                        float fCoerceAtMost = RangesKt.coerceAtMost(progressBarRangeInfo.getRange().getStart().floatValue(), progressBarRangeInfo.getRange().getEndInclusive().floatValue());
                                        float steps = progressBarRangeInfo.getSteps() > 0 ? (fCoerceAtLeast - fCoerceAtMost) / (progressBarRangeInfo.getSteps() + 1) : (fCoerceAtLeast - fCoerceAtMost) / 20.0f;
                                        if (z4) {
                                            steps = -steps;
                                        }
                                        Function1 function1 = (Function1) accessibilityAction6.getAction();
                                        if (function1 != null) {
                                        }
                                        break;
                                    }
                                } else {
                                    z = true;
                                    z2 = false;
                                }
                                long jM5101getSizeNHjbRc = LayoutCoordinatesKt.boundsInParent(semanticsNode.getLayoutInfo().getCoordinates()).m5101getSizeNHjbRc();
                                Float scrollViewportLength = SemanticsUtils_androidKt.getScrollViewportLength(semanticsNode.getUnmergedConfig());
                                AccessibilityAction accessibilityAction7 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsActions.INSTANCE.getScrollBy());
                                if (accessibilityAction7 != null) {
                                    ScrollAxisRange scrollAxisRange = (ScrollAxisRange) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsProperties.INSTANCE.getHorizontalScrollAxisRange());
                                    if (scrollAxisRange == null || !z9) {
                                        f = scrollViewportLength;
                                        f2 = 0.0f;
                                    } else {
                                        if (scrollViewportLength != null) {
                                            fIntBitsToFloat = scrollViewportLength.floatValue();
                                            f = scrollViewportLength;
                                            f2 = 0.0f;
                                        } else {
                                            f = scrollViewportLength;
                                            f2 = 0.0f;
                                            fIntBitsToFloat = Float.intBitsToFloat((int) (jM5101getSizeNHjbRc >> 32));
                                        }
                                        if (z5 || z4) {
                                            fIntBitsToFloat = -fIntBitsToFloat;
                                        }
                                        if (scrollAxisRange.getReverseScrolling()) {
                                            fIntBitsToFloat = -fIntBitsToFloat;
                                        }
                                        if (AndroidComposeViewAccessibilityDelegateCompat_androidKt.isRtl(semanticsNode) && (z5 || z6)) {
                                            fIntBitsToFloat = -fIntBitsToFloat;
                                        }
                                        if (performActionHelper$canScroll(scrollAxisRange, fIntBitsToFloat)) {
                                            if ((semanticsNode.getUnmergedConfig().contains(SemanticsActions.INSTANCE.getPageLeft()) || semanticsNode.getUnmergedConfig().contains(SemanticsActions.INSTANCE.getPageRight())) ? z : z2) {
                                                AccessibilityAction accessibilityAction8 = fIntBitsToFloat > f2 ? (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsActions.INSTANCE.getPageRight()) : (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsActions.INSTANCE.getPageLeft());
                                                if (accessibilityAction8 != null && (r1 = (Function0) accessibilityAction8.getAction()) != null) {
                                                }
                                            } else {
                                                Function2 function2 = (Function2) accessibilityAction7.getAction();
                                                if (function2 != null) {
                                                }
                                            }
                                            break;
                                        }
                                    }
                                    ScrollAxisRange scrollAxisRange2 = (ScrollAxisRange) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsProperties.INSTANCE.getVerticalScrollAxisRange());
                                    if (scrollAxisRange2 != null && z10) {
                                        float fFloatValue = f != null ? f.floatValue() : Float.intBitsToFloat((int) (jM5101getSizeNHjbRc & 4294967295L));
                                        if (z7 || z4) {
                                            fFloatValue = -fFloatValue;
                                        }
                                        if (scrollAxisRange2.getReverseScrolling()) {
                                            fFloatValue = -fFloatValue;
                                        }
                                        if (performActionHelper$canScroll(scrollAxisRange2, fFloatValue)) {
                                            if ((semanticsNode.getUnmergedConfig().contains(SemanticsActions.INSTANCE.getPageUp()) || semanticsNode.getUnmergedConfig().contains(SemanticsActions.INSTANCE.getPageDown())) ? z : z2) {
                                                AccessibilityAction accessibilityAction9 = fFloatValue > f2 ? (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsActions.INSTANCE.getPageDown()) : (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsActions.INSTANCE.getPageUp());
                                                if (accessibilityAction9 != null && (r15 = (Function0) accessibilityAction9.getAction()) != null) {
                                                }
                                            } else {
                                                Function2 function22 = (Function2) accessibilityAction7.getAction();
                                                if (function22 != null) {
                                                }
                                            }
                                        }
                                    }
                                    break;
                                }
                                break;
                            case 32768:
                                AccessibilityAction accessibilityAction10 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsActions.INSTANCE.getPasteText());
                                if (accessibilityAction10 != null && (r1 = (Function0) accessibilityAction10.getAction()) != null) {
                                    break;
                                }
                                break;
                            case 65536:
                                AccessibilityAction accessibilityAction11 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsActions.INSTANCE.getCutText());
                                if (accessibilityAction11 != null && (r1 = (Function0) accessibilityAction11.getAction()) != null) {
                                    break;
                                }
                                break;
                            case 262144:
                                AccessibilityAction accessibilityAction12 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsActions.INSTANCE.getExpand());
                                if (accessibilityAction12 != null && (r1 = (Function0) accessibilityAction12.getAction()) != null) {
                                    break;
                                }
                                break;
                            case 524288:
                                AccessibilityAction accessibilityAction13 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsActions.INSTANCE.getCollapse());
                                if (accessibilityAction13 != null && (r1 = (Function0) accessibilityAction13.getAction()) != null) {
                                    break;
                                }
                                break;
                            case 1048576:
                                AccessibilityAction accessibilityAction14 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsActions.INSTANCE.getDismiss());
                                if (accessibilityAction14 != null && (r1 = (Function0) accessibilityAction14.getAction()) != null) {
                                    break;
                                }
                                break;
                            case 2097152:
                                String string = arguments != null ? arguments.getString(AccessibilityNodeInfoCompat.ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE) : null;
                                AccessibilityAction accessibilityAction15 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsActions.INSTANCE.getSetText());
                                if (accessibilityAction15 != null && (r3 = (Function1) accessibilityAction15.getAction()) != null) {
                                    break;
                                }
                                break;
                            case android.R.id.accessibilityActionShowOnScreen:
                                if (AndroidComposeUiFlags.isAccessibilityShowOnScreenNestedScrollingEnabled) {
                                }
                                break;
                            case android.R.id.accessibilityActionSetProgress:
                                if (arguments != null && arguments.containsKey(AccessibilityNodeInfoCompat.ACTION_ARGUMENT_PROGRESS_VALUE) && (accessibilityAction = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsActions.INSTANCE.getSetProgress())) != null && (r2 = (Function1) accessibilityAction.getAction()) != null) {
                                    break;
                                }
                                break;
                            case android.R.id.accessibilityActionPageUp:
                                AccessibilityAction accessibilityAction16 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsActions.INSTANCE.getPageUp());
                                if (accessibilityAction16 != null && (r2 = (Function0) accessibilityAction16.getAction()) != null) {
                                    break;
                                }
                                break;
                            case android.R.id.accessibilityActionPageDown:
                                AccessibilityAction accessibilityAction17 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsActions.INSTANCE.getPageDown());
                                if (accessibilityAction17 != null && (r2 = (Function0) accessibilityAction17.getAction()) != null) {
                                    break;
                                }
                                break;
                            case android.R.id.accessibilityActionPageLeft:
                                AccessibilityAction accessibilityAction18 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsActions.INSTANCE.getPageLeft());
                                if (accessibilityAction18 != null && (r2 = (Function0) accessibilityAction18.getAction()) != null) {
                                    break;
                                }
                                break;
                            case android.R.id.accessibilityActionPageRight:
                                AccessibilityAction accessibilityAction19 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsActions.INSTANCE.getPageRight());
                                if (accessibilityAction19 != null && (r2 = (Function0) accessibilityAction19.getAction()) != null) {
                                    break;
                                }
                                break;
                            case android.R.id.accessibilityActionImeEnter:
                                AccessibilityAction accessibilityAction20 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsActions.INSTANCE.getOnImeAction());
                                if (accessibilityAction20 != null && (r1 = (Function0) accessibilityAction20.getAction()) != null) {
                                    break;
                                }
                                break;
                            default:
                                SparseArrayCompat<CharSequence> sparseArrayCompat = this.actionIdToLabel.get(virtualViewId);
                                if (sparseArrayCompat != null && (charSequence = sparseArrayCompat.get(action)) != null && (list = (List) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsActions.INSTANCE.getCustomActions())) != null) {
                                    int size = list.size();
                                    for (int i = 0; i < size; i++) {
                                        CustomAccessibilityAction customAccessibilityAction = (CustomAccessibilityAction) list.get(i);
                                        if (Intrinsics.areEqual(customAccessibilityAction.getLabel(), charSequence)) {
                                        }
                                        break;
                                    }
                                    break;
                                }
                                break;
                        }
                    }
                    break;
            }
            return false;
        }
        return false;
    }

    private static final boolean performActionHelper$canScroll(ScrollAxisRange $this$performActionHelper_u24canScroll, float amount) {
        return (amount < 0.0f && $this$performActionHelper_u24canScroll.getValue().invoke().floatValue() > 0.0f) || (amount > 0.0f && $this$performActionHelper_u24canScroll.getValue().invoke().floatValue() < $this$performActionHelper_u24canScroll.getMaxValue().invoke().floatValue());
    }

    @Deprecated(message = "This method is deprecated. Use scrollOntoScreen instead.")
    private final boolean legacyScrollOntoScreen(SemanticsNode $this$legacyScrollOntoScreen) {
        Function2 function2;
        SemanticsConfiguration unmergedConfig$ui;
        SemanticsConfiguration unmergedConfig$ui2;
        SemanticsNode scrollableAncestor = $this$legacyScrollOntoScreen.getParent();
        AccessibilityAction scrollAction = (scrollableAncestor == null || (unmergedConfig$ui2 = scrollableAncestor.getUnmergedConfig()) == null) ? null : (AccessibilityAction) SemanticsConfigurationKt.getOrNull(unmergedConfig$ui2, SemanticsActions.INSTANCE.getScrollBy());
        while (scrollableAncestor != null && scrollAction == null) {
            scrollableAncestor = scrollableAncestor.getParent();
            scrollAction = (scrollableAncestor == null || (unmergedConfig$ui = scrollableAncestor.getUnmergedConfig()) == null) ? null : (AccessibilityAction) SemanticsConfigurationKt.getOrNull(unmergedConfig$ui, SemanticsActions.INSTANCE.getScrollBy());
        }
        if (scrollableAncestor == null) {
            androidx.compose.ui.geometry.Rect $this$legacyScrollOntoScreen_u24lambda_u240 = $this$legacyScrollOntoScreen.getBoundsInRoot();
            Rect rect = new Rect((int) Math.floor($this$legacyScrollOntoScreen_u24lambda_u240.getLeft()), (int) Math.floor($this$legacyScrollOntoScreen_u24lambda_u240.getTop()), MathKt.roundToInt((float) Math.ceil($this$legacyScrollOntoScreen_u24lambda_u240.getRight())), MathKt.roundToInt((float) Math.ceil($this$legacyScrollOntoScreen_u24lambda_u240.getBottom())));
            return this.view.requestRectangleOnScreen(rect);
        }
        androidx.compose.ui.geometry.Rect viewportInParent = LayoutCoordinatesKt.boundsInParent(scrollableAncestor.getLayoutInfo().getCoordinates());
        LayoutCoordinates parentLayoutCoordinates = scrollableAncestor.getLayoutInfo().getCoordinates().getParentLayoutCoordinates();
        long parentInRoot = parentLayoutCoordinates != null ? LayoutCoordinatesKt.positionInRoot(parentLayoutCoordinates) : Offset.INSTANCE.m5084getZeroF1C5BW0();
        androidx.compose.ui.geometry.Rect viewport = viewportInParent.m5105translatek4lQ0M(parentInRoot);
        androidx.compose.ui.geometry.Rect target = RectKt.m5108Recttz77jQw($this$legacyScrollOntoScreen.m7353getPositionInRootF1C5BW0(), IntSizeKt.m8333toSizeozmzZPI($this$legacyScrollOntoScreen.m7356getSizeYbymL2g()));
        ScrollAxisRange xScrollState = (ScrollAxisRange) SemanticsConfigurationKt.getOrNull(scrollableAncestor.getUnmergedConfig(), SemanticsProperties.INSTANCE.getHorizontalScrollAxisRange());
        ScrollAxisRange yScrollState = (ScrollAxisRange) SemanticsConfigurationKt.getOrNull(scrollableAncestor.getUnmergedConfig(), SemanticsProperties.INSTANCE.getVerticalScrollAxisRange());
        float dx = legacyScrollOntoScreen$scrollDelta(target.getLeft() - viewport.getLeft(), target.getRight() - viewport.getRight());
        if (xScrollState != null && xScrollState.getReverseScrolling()) {
            dx = -dx;
        }
        if (AndroidComposeViewAccessibilityDelegateCompat_androidKt.isRtl($this$legacyScrollOntoScreen)) {
            dx = -dx;
        }
        float dy = legacyScrollOntoScreen$scrollDelta(target.getTop() - viewport.getTop(), target.getBottom() - viewport.getBottom());
        if (yScrollState != null && yScrollState.getReverseScrolling()) {
            dy = -dy;
        }
        return (scrollAction == null || (function2 = (Function2) scrollAction.getAction()) == null || !((Boolean) function2.invoke(Float.valueOf(dx), Float.valueOf(dy))).booleanValue()) ? false : true;
    }

    private static final float legacyScrollOntoScreen$scrollDelta(float a, float b) {
        if (Math.signum(a) == Math.signum(b)) {
            return Math.abs(a) < Math.abs(b) ? a : b;
        }
        return 0.0f;
    }

    private final boolean scrollOntoScreen(SemanticsNode $this$scrollOntoScreen) {
        boolean retval;
        boolean z;
        SemanticsConfiguration unmergedConfig$ui;
        SemanticsConfiguration unmergedConfig$ui2;
        SemanticsNode scrollableAncestor = $this$scrollOntoScreen.getParent();
        AccessibilityAction scrollAction = (scrollableAncestor == null || (unmergedConfig$ui2 = scrollableAncestor.getUnmergedConfig()) == null) ? null : (AccessibilityAction) SemanticsConfigurationKt.getOrNull(unmergedConfig$ui2, SemanticsActions.INSTANCE.getScrollBy());
        while (scrollAction == null && scrollableAncestor != null) {
            scrollableAncestor = scrollableAncestor.getParent();
            scrollAction = (scrollableAncestor == null || (unmergedConfig$ui = scrollableAncestor.getUnmergedConfig()) == null) ? null : (AccessibilityAction) SemanticsConfigurationKt.getOrNull(unmergedConfig$ui, SemanticsActions.INSTANCE.getScrollBy());
        }
        if (scrollableAncestor == null) {
            androidx.compose.ui.geometry.Rect $this$scrollOntoScreen_u24lambda_u240 = $this$scrollOntoScreen.getBoundsInRoot();
            Rect rect = new Rect((int) Math.floor($this$scrollOntoScreen_u24lambda_u240.getLeft()), (int) Math.floor($this$scrollOntoScreen_u24lambda_u240.getTop()), MathKt.roundToInt((float) Math.ceil($this$scrollOntoScreen_u24lambda_u240.getRight())), MathKt.roundToInt((float) Math.ceil($this$scrollOntoScreen_u24lambda_u240.getBottom())));
            return this.view.requestRectangleOnScreen(rect);
        }
        boolean retval2 = false;
        long accumulatedOffset = Offset.INSTANCE.m5084getZeroF1C5BW0();
        while (scrollableAncestor != null) {
            AccessibilityAction scrollAction2 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(scrollableAncestor.getUnmergedConfig(), SemanticsActions.INSTANCE.getScrollBy());
            if (scrollAction2 != null) {
                long offset = m7227scrollDxDyForNodeVisibleRE3cj74($this$scrollOntoScreen, scrollableAncestor, accumulatedOffset);
                long scrollOffset = m7224adjustForReversedScrollingAndRtlRE3cj74($this$scrollOntoScreen, scrollableAncestor, offset);
                Function2 function2 = (Function2) scrollAction2.getAction();
                if (function2 != null) {
                    int bits$iv$iv$iv = (int) (scrollOffset >> 32);
                    int bits$iv$iv$iv2 = (int) (scrollOffset & 4294967295L);
                    boolean zBooleanValue = ((Boolean) function2.invoke(Float.valueOf(Float.intBitsToFloat(bits$iv$iv$iv)), Float.valueOf(Float.intBitsToFloat(bits$iv$iv$iv2)))).booleanValue();
                    retval = true;
                    z = zBooleanValue;
                    if (!z && !retval2) {
                        retval = false;
                    }
                    accumulatedOffset = Offset.m5072minusMKHz9U(accumulatedOffset, offset);
                    retval2 = retval;
                } else {
                    retval = true;
                }
                if (!z) {
                    retval = false;
                }
                accumulatedOffset = Offset.m5072minusMKHz9U(accumulatedOffset, offset);
                retval2 = retval;
            }
            scrollableAncestor = scrollableAncestor.getParent();
        }
        return retval2;
    }

    /* JADX INFO: renamed from: adjustForReversedScrollingAndRtl-RE3cj74, reason: not valid java name */
    private final long m7224adjustForReversedScrollingAndRtlRE3cj74(SemanticsNode $this$adjustForReversedScrollingAndRtl_u2dRE3cj74, SemanticsNode scrollableAncestor, long offset) {
        if (Offset.m5065equalsimpl0(offset, Offset.INSTANCE.m5084getZeroF1C5BW0())) {
            return offset;
        }
        int bits$iv$iv$iv = (int) (offset >> 32);
        float finalX = Float.intBitsToFloat(bits$iv$iv$iv);
        int bits$iv$iv$iv2 = (int) (offset & 4294967295L);
        float finalY = Float.intBitsToFloat(bits$iv$iv$iv2);
        ScrollAxisRange xScrollState = (ScrollAxisRange) SemanticsConfigurationKt.getOrNull(scrollableAncestor.getUnmergedConfig(), SemanticsProperties.INSTANCE.getHorizontalScrollAxisRange());
        if (xScrollState != null && xScrollState.getReverseScrolling()) {
            finalX = -finalX;
        }
        if (AndroidComposeViewAccessibilityDelegateCompat_androidKt.isRtl($this$adjustForReversedScrollingAndRtl_u2dRE3cj74)) {
            finalX = -finalX;
        }
        ScrollAxisRange yScrollState = (ScrollAxisRange) SemanticsConfigurationKt.getOrNull(scrollableAncestor.getUnmergedConfig(), SemanticsProperties.INSTANCE.getVerticalScrollAxisRange());
        if (yScrollState != null && yScrollState.getReverseScrolling()) {
            finalY = -finalY;
        }
        float y$iv = finalY;
        float x$iv = finalX;
        long v1$iv$iv = Float.floatToRawIntBits(x$iv);
        long v2$iv$iv = Float.floatToRawIntBits(y$iv);
        return Offset.m5060constructorimpl((v1$iv$iv << 32) | (v2$iv$iv & 4294967295L));
    }

    /* JADX INFO: renamed from: scrollDxDyForNodeVisible-RE3cj74, reason: not valid java name */
    private final long m7227scrollDxDyForNodeVisibleRE3cj74(SemanticsNode $this$scrollDxDyForNodeVisible_u2dRE3cj74, SemanticsNode scrollableAncestor, long offsetAdjustment) {
        androidx.compose.ui.geometry.Rect viewportInParent = LayoutCoordinatesKt.boundsInParent(scrollableAncestor.getLayoutInfo().getCoordinates());
        LayoutCoordinates parentLayoutCoordinates = scrollableAncestor.getLayoutInfo().getCoordinates().getParentLayoutCoordinates();
        long parentInRoot = parentLayoutCoordinates != null ? LayoutCoordinatesKt.positionInRoot(parentLayoutCoordinates) : Offset.INSTANCE.m5084getZeroF1C5BW0();
        androidx.compose.ui.geometry.Rect viewport = viewportInParent.m5105translatek4lQ0M(parentInRoot);
        androidx.compose.ui.geometry.Rect target = RectKt.m5108Recttz77jQw(Offset.m5073plusMKHz9U($this$scrollDxDyForNodeVisible_u2dRE3cj74.m7353getPositionInRootF1C5BW0(), offsetAdjustment), IntSizeKt.m8333toSizeozmzZPI($this$scrollDxDyForNodeVisible_u2dRE3cj74.m7356getSizeYbymL2g()));
        float dx = scrollDxDyForNodeVisible_RE3cj74$scrollDelta(target.getLeft() - viewport.getLeft(), target.getRight() - viewport.getRight());
        float dy = scrollDxDyForNodeVisible_RE3cj74$scrollDelta(target.getTop() - viewport.getTop(), target.getBottom() - viewport.getBottom());
        long v1$iv$iv = Float.floatToRawIntBits(dx);
        long v1$iv$iv2 = Float.floatToRawIntBits(dy);
        long v2$iv$iv = (v1$iv$iv << 32) | (v1$iv$iv2 & 4294967295L);
        return Offset.m5060constructorimpl(v2$iv$iv);
    }

    private static final float scrollDxDyForNodeVisible_RE3cj74$scrollDelta(float a, float b) {
        if (Math.signum(a) == Math.signum(b)) {
            return Math.abs(a) < Math.abs(b) ? a : b;
        }
        return 0.0f;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void addExtraDataToAccessibilityNodeInfoHelper(int virtualViewId, AccessibilityNodeInfoCompat info, String extraDataKey, Bundle arguments) {
        SemanticsNode node;
        int i;
        int j$iv$iv;
        float[] corners;
        String str = extraDataKey;
        SemanticsNodeWithAdjustedBounds semanticsNodeWithAdjustedBounds = getCurrentSemanticsNodes().get(virtualViewId);
        if (semanticsNodeWithAdjustedBounds == null || (node = semanticsNodeWithAdjustedBounds.getSemanticsNode()) == null) {
            return;
        }
        String text = getIterableTextForAccessibility(node);
        if (Intrinsics.areEqual(str, this.ExtraDataTestTraversalBeforeVal)) {
            int it = this.idToBeforeMap.getOrDefault(virtualViewId, -1);
            if (it != -1) {
                info.getExtras().putInt(str, it);
            }
            return;
        }
        if (Intrinsics.areEqual(str, this.ExtraDataTestTraversalAfterVal)) {
            int it2 = this.idToAfterMap.getOrDefault(virtualViewId, -1);
            if (it2 != -1) {
                info.getExtras().putInt(str, it2);
            }
            return;
        }
        if (node.getUnmergedConfig().contains(SemanticsActions.INSTANCE.getGetTextLayoutResult()) && arguments != null && Intrinsics.areEqual(str, AccessibilityNodeInfoCompat.EXTRA_DATA_TEXT_CHARACTER_LOCATION_KEY)) {
            int positionInfoStartIndex = arguments.getInt(AccessibilityNodeInfoCompat.EXTRA_DATA_TEXT_CHARACTER_LOCATION_ARG_START_INDEX, -1);
            int positionInfoLength = arguments.getInt(AccessibilityNodeInfoCompat.EXTRA_DATA_TEXT_CHARACTER_LOCATION_ARG_LENGTH, -1);
            if (positionInfoLength > 0 && positionInfoStartIndex >= 0) {
                if (positionInfoStartIndex < (text != null ? text.length() : Integer.MAX_VALUE)) {
                    TextLayoutResult textLayoutResult = SemanticsUtils_androidKt.getTextLayoutResult(node.getUnmergedConfig());
                    if (textLayoutResult == null) {
                        return;
                    }
                    List boundingRects = new ArrayList();
                    for (int i2 = 0; i2 < positionInfoLength; i2++) {
                        if (positionInfoStartIndex + i2 >= textLayoutResult.getLayoutInput().getText().length()) {
                            boundingRects.add(null);
                        } else {
                            androidx.compose.ui.geometry.Rect bounds = textLayoutResult.getBoundingBox(positionInfoStartIndex + i2);
                            RectF boundsOnScreen = toScreenCoords(node, bounds);
                            boundingRects.add(boundsOnScreen);
                        }
                    }
                    List $this$toTypedArray$iv = boundingRects;
                    info.getExtras().putParcelableArray(str, (Parcelable[]) $this$toTypedArray$iv.toArray(new RectF[0]));
                    return;
                }
            }
            Log.e(LogTag, "Invalid arguments for accessibility character locations");
            return;
        }
        if (node.getUnmergedConfig().contains(SemanticsProperties.INSTANCE.getTestTag()) && arguments != null && Intrinsics.areEqual(str, ExtraDataTestTagKey)) {
            String testTag = (String) SemanticsConfigurationKt.getOrNull(node.getUnmergedConfig(), SemanticsProperties.INSTANCE.getTestTag());
            if (testTag != null) {
                info.getExtras().putCharSequence(str, testTag);
                return;
            }
            return;
        }
        if (Intrinsics.areEqual(str, ExtraDataIdKey)) {
            info.getExtras().putInt(str, node.getId());
            return;
        }
        if (Intrinsics.areEqual(str, ExtraDataShapeTypeKey)) {
            Shape shape = (Shape) SemanticsConfigurationKt.getOrNull(node.getUnmergedConfig(), SemanticsProperties.INSTANCE.getShape());
            if (shape != null) {
                androidx.compose.ui.geometry.Rect shapeBounds = getShapeBounds(node, getBoundsInScreen(info), shape);
                Outline outline = m7226createOutline12SF9DM(shape, shapeBounds.m5101getSizeNHjbRc(), node.getLayoutInfo().getLayoutDirection());
                if (outline instanceof Outline.Rectangle) {
                    info.getExtras().putInt(ExtraDataShapeTypeKey, 0);
                    info.getExtras().putParcelable(ExtraDataShapeRectKey, toAndroidRect(outline, shapeBounds.getLeft(), shapeBounds.getTop()));
                } else if (outline instanceof Outline.Rounded) {
                    info.getExtras().putInt(ExtraDataShapeTypeKey, 1);
                    info.getExtras().putParcelable(ExtraDataShapeRectKey, toAndroidRect(outline, shapeBounds.getLeft(), shapeBounds.getTop()));
                    info.getExtras().putFloatArray(ExtraDataShapeRectCornersKey, toCornerArray(outline));
                } else {
                    if (!(outline instanceof Outline.Generic)) {
                        throw new NoWhenBranchMatchedException();
                    }
                    info.getExtras().putInt(ExtraDataShapeTypeKey, 2);
                    info.getExtras().putParcelable(ExtraDataShapeRegionKey, toRegion(outline, shapeBounds.getLeft(), shapeBounds.getTop()));
                }
            }
            return;
        }
        int i3 = 1;
        if (Intrinsics.areEqual(str, ExtraDataShapeRectKey)) {
            Shape shape2 = (Shape) SemanticsConfigurationKt.getOrNull(node.getUnmergedConfig(), SemanticsProperties.INSTANCE.getShape());
            if (shape2 != null) {
                androidx.compose.ui.geometry.Rect shapeBounds2 = getShapeBounds(node, getBoundsInScreen(info), shape2);
                Rect rect = toAndroidRect(m7226createOutline12SF9DM(shape2, shapeBounds2.m5101getSizeNHjbRc(), node.getLayoutInfo().getLayoutDirection()), shapeBounds2.getLeft(), shapeBounds2.getTop());
                if (rect != null) {
                    info.getExtras().putParcelable(ExtraDataShapeRectKey, rect);
                }
            }
            return;
        }
        if (Intrinsics.areEqual(str, ExtraDataShapeRectCornersKey)) {
            Shape shape3 = (Shape) SemanticsConfigurationKt.getOrNull(node.getUnmergedConfig(), SemanticsProperties.INSTANCE.getShape());
            if (shape3 != null && (corners = toCornerArray(m7226createOutline12SF9DM(shape3, getShapeBounds(node, getBoundsInScreen(info), shape3).m5101getSizeNHjbRc(), node.getLayoutInfo().getLayoutDirection()))) != null) {
                info.getExtras().putFloatArray(ExtraDataShapeRectCornersKey, corners);
            }
            return;
        }
        if (Intrinsics.areEqual(str, ExtraDataShapeRegionKey)) {
            Shape shape4 = (Shape) SemanticsConfigurationKt.getOrNull(node.getUnmergedConfig(), SemanticsProperties.INSTANCE.getShape());
            if (shape4 != null) {
                androidx.compose.ui.geometry.Rect shapeBounds3 = getShapeBounds(node, getBoundsInScreen(info), shape4);
                Region region = toRegion(m7226createOutline12SF9DM(shape4, shapeBounds3.m5101getSizeNHjbRc(), node.getLayoutInfo().getLayoutDirection()), shapeBounds3.getLeft(), shapeBounds3.getTop());
                if (region != null) {
                    info.getExtras().putParcelable(ExtraDataShapeRegionKey, region);
                }
            }
            return;
        }
        ScatterSet<SemanticsPropertyKey<?>> accessibilityExtraKeys$ui = node.getUnmergedConfig().getAccessibilityExtraKeys$ui();
        if (accessibilityExtraKeys$ui == null) {
            return;
        }
        Object[] elements$iv = accessibilityExtraKeys$ui.elements;
        long[] m$iv$iv = accessibilityExtraKeys$ui.metadata;
        int lastIndex$iv$iv = m$iv$iv.length - 2;
        int i$iv$iv = 0;
        if (0 > lastIndex$iv$iv) {
            return;
        }
        while (true) {
            long slot$iv$iv = m$iv$iv[i$iv$iv];
            SemanticsNode node2 = node;
            long $this$maskEmptyOrDeleted$iv$iv$iv = ((~slot$iv$iv) << 7) & slot$iv$iv & (-9187201950435737472L);
            if ($this$maskEmptyOrDeleted$iv$iv$iv != -9187201950435737472L) {
                int i4 = 8;
                int bitCount$iv$iv = 8 - ((~(i$iv$iv - lastIndex$iv$iv)) >>> 31);
                int j$iv$iv2 = 0;
                while (j$iv$iv2 < bitCount$iv$iv) {
                    long value$iv$iv$iv = slot$iv$iv & 255;
                    int $i$f$isFull = value$iv$iv$iv < 128 ? i3 : 0;
                    if ($i$f$isFull != 0) {
                        int index$iv$iv = (i$iv$iv << 3) + j$iv$iv2;
                        SemanticsPropertyKey key = (SemanticsPropertyKey) elements$iv[index$iv$iv];
                        i = i4;
                        String extraKey = key.getAccessibilityExtraKey();
                        if (Intrinsics.areEqual(extraKey, str)) {
                            Object value = SemanticsConfigurationKt.getOrNull(node2.getUnmergedConfig(), key);
                            if (value instanceof Serializable) {
                                j$iv$iv = j$iv$iv2;
                                info.getExtras().putSerializable(extraKey, (Serializable) value);
                            } else {
                                j$iv$iv = j$iv$iv2;
                                if (!(value instanceof Parcelable)) {
                                    throw new IllegalStateException("Accessibility extra values must be either Serializable or Parcelable.");
                                }
                                info.getExtras().putParcelable(extraKey, (Parcelable) value);
                            }
                        } else {
                            j$iv$iv = j$iv$iv2;
                        }
                    } else {
                        i = i4;
                        j$iv$iv = j$iv$iv2;
                    }
                    slot$iv$iv >>= i;
                    j$iv$iv2 = j$iv$iv + 1;
                    str = extraDataKey;
                    i4 = i;
                    i3 = 1;
                }
                if (bitCount$iv$iv != i4) {
                    return;
                }
            }
            if (i$iv$iv == lastIndex$iv$iv) {
                return;
            }
            i$iv$iv++;
            str = extraDataKey;
            node = node2;
            i3 = 1;
        }
    }

    private final Rect getBoundsInScreen(AccessibilityNodeInfoCompat $this$boundsInScreen) {
        Rect boundsInScreen = new Rect();
        $this$boundsInScreen.getBoundsInScreen(boundsInScreen);
        return boundsInScreen;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:70:0x017d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final androidx.compose.ui.geometry.Rect getShapeBounds(androidx.compose.ui.semantics.SemanticsNode r43, android.graphics.Rect r44, final androidx.compose.ui.graphics.Shape r45) {
        /*
            Method dump skipped, instruction units count: 443
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat.getShapeBounds(androidx.compose.ui.semantics.SemanticsNode, android.graphics.Rect, androidx.compose.ui.graphics.Shape):androidx.compose.ui.geometry.Rect");
    }

    private final androidx.compose.ui.geometry.Rect toBoundsRelativeToNodeBounds(Rect $this$toBoundsRelativeToNodeBounds, Rect nodeBoundsInScreen) {
        float leftOffset = $this$toBoundsRelativeToNodeBounds.left - nodeBoundsInScreen.left;
        float topOffset = $this$toBoundsRelativeToNodeBounds.top - nodeBoundsInScreen.top;
        return new androidx.compose.ui.geometry.Rect(leftOffset, topOffset, $this$toBoundsRelativeToNodeBounds.width() + leftOffset, $this$toBoundsRelativeToNodeBounds.height() + topOffset);
    }

    private final RectF toScreenCoords(SemanticsNode textNode, androidx.compose.ui.geometry.Rect bounds) {
        if (textNode == null) {
            return null;
        }
        androidx.compose.ui.geometry.Rect boundsInRoot = bounds.m5105translatek4lQ0M(textNode.m7353getPositionInRootF1C5BW0());
        androidx.compose.ui.geometry.Rect textNodeBoundsInRoot = textNode.getBoundsInRoot();
        androidx.compose.ui.geometry.Rect visibleBounds = boundsInRoot.overlaps(textNodeBoundsInRoot) ? boundsInRoot.intersect(textNodeBoundsInRoot) : null;
        if (visibleBounds == null) {
            return null;
        }
        AndroidComposeView androidComposeView = this.view;
        float x$iv = visibleBounds.getLeft();
        float y$iv = visibleBounds.getTop();
        long v1$iv$iv = Float.floatToRawIntBits(x$iv);
        long v2$iv$iv = Float.floatToRawIntBits(y$iv);
        long topLeftInScreen = androidComposeView.mo6732localToScreenMKHz9U(Offset.m5060constructorimpl((v1$iv$iv << 32) | (v2$iv$iv & 4294967295L)));
        AndroidComposeView androidComposeView2 = this.view;
        float x$iv2 = visibleBounds.getRight();
        float y$iv2 = visibleBounds.getBottom();
        long v1$iv$iv2 = Float.floatToRawIntBits(x$iv2);
        long v2$iv$iv2 = Float.floatToRawIntBits(y$iv2);
        long bottomRightInScreen = androidComposeView2.mo6732localToScreenMKHz9U(Offset.m5060constructorimpl((v1$iv$iv2 << 32) | (v2$iv$iv2 & 4294967295L)));
        int bits$iv$iv$iv = (int) (topLeftInScreen >> 32);
        int bits$iv$iv$iv2 = (int) (bottomRightInScreen >> 32);
        float fMin = Math.min(Float.intBitsToFloat(bits$iv$iv$iv), Float.intBitsToFloat(bits$iv$iv$iv2));
        int bits$iv$iv$iv3 = (int) (topLeftInScreen & 4294967295L);
        int bits$iv$iv$iv4 = (int) (bottomRightInScreen & 4294967295L);
        float fMin2 = Math.min(Float.intBitsToFloat(bits$iv$iv$iv3), Float.intBitsToFloat(bits$iv$iv$iv4));
        int bits$iv$iv$iv5 = (int) (topLeftInScreen >> 32);
        int bits$iv$iv$iv6 = (int) (bottomRightInScreen >> 32);
        float fMax = Math.max(Float.intBitsToFloat(bits$iv$iv$iv5), Float.intBitsToFloat(bits$iv$iv$iv6));
        int bits$iv$iv$iv7 = (int) (topLeftInScreen & 4294967295L);
        int bits$iv$iv$iv8 = (int) (bottomRightInScreen & 4294967295L);
        return new RectF(fMin, fMin2, fMax, Math.max(Float.intBitsToFloat(bits$iv$iv$iv7), Float.intBitsToFloat(bits$iv$iv$iv8)));
    }

    /* JADX INFO: renamed from: createOutline-12SF9DM, reason: not valid java name */
    private final Outline m7226createOutline12SF9DM(Shape $this$createOutline_u2d12SF9DM, long size, LayoutDirection layoutDirection) {
        return $this$createOutline_u2d12SF9DM.mo342createOutlinePq9zytI(size, layoutDirection, this.view.getDensity());
    }

    private final Rect toAndroidRect(Outline $this$toAndroidRect, float leftOffset, float topOffset) {
        if (($this$toAndroidRect instanceof Outline.Rectangle) || ($this$toAndroidRect instanceof Outline.Rounded)) {
            return toAndroidRect($this$toAndroidRect.getRect(), leftOffset, topOffset);
        }
        return null;
    }

    private final float[] toCornerArray(Outline $this$toCornerArray) {
        if (!($this$toCornerArray instanceof Outline.Rounded)) {
            return null;
        }
        long arg0$iv = ((Outline.Rounded) $this$toCornerArray).getRoundRect().m5118getTopLeftCornerRadiuskKHJgLs();
        int bits$iv$iv$iv = (int) (arg0$iv >> 32);
        long arg0$iv2 = ((Outline.Rounded) $this$toCornerArray).getRoundRect().m5118getTopLeftCornerRadiuskKHJgLs();
        int bits$iv$iv$iv2 = (int) (arg0$iv2 & 4294967295L);
        long arg0$iv3 = ((Outline.Rounded) $this$toCornerArray).getRoundRect().m5119getTopRightCornerRadiuskKHJgLs();
        int bits$iv$iv$iv3 = (int) (arg0$iv3 >> 32);
        long arg0$iv4 = ((Outline.Rounded) $this$toCornerArray).getRoundRect().m5119getTopRightCornerRadiuskKHJgLs();
        int bits$iv$iv$iv4 = (int) (arg0$iv4 & 4294967295L);
        long arg0$iv5 = ((Outline.Rounded) $this$toCornerArray).getRoundRect().m5117getBottomRightCornerRadiuskKHJgLs();
        int bits$iv$iv$iv5 = (int) (arg0$iv5 >> 32);
        long arg0$iv6 = ((Outline.Rounded) $this$toCornerArray).getRoundRect().m5117getBottomRightCornerRadiuskKHJgLs();
        int bits$iv$iv$iv6 = (int) (arg0$iv6 & 4294967295L);
        long arg0$iv7 = ((Outline.Rounded) $this$toCornerArray).getRoundRect().m5116getBottomLeftCornerRadiuskKHJgLs();
        int bits$iv$iv$iv7 = (int) (arg0$iv7 >> 32);
        long arg0$iv8 = ((Outline.Rounded) $this$toCornerArray).getRoundRect().m5116getBottomLeftCornerRadiuskKHJgLs();
        int bits$iv$iv$iv8 = (int) (arg0$iv8 & 4294967295L);
        return new float[]{Float.intBitsToFloat(bits$iv$iv$iv), Float.intBitsToFloat(bits$iv$iv$iv2), Float.intBitsToFloat(bits$iv$iv$iv3), Float.intBitsToFloat(bits$iv$iv$iv4), Float.intBitsToFloat(bits$iv$iv$iv5), Float.intBitsToFloat(bits$iv$iv$iv6), Float.intBitsToFloat(bits$iv$iv$iv7), Float.intBitsToFloat(bits$iv$iv$iv8)};
    }

    private final Region toRegion(Outline $this$toRegion, float leftOffset, float topOffset) {
        if ($this$toRegion instanceof Outline.Generic) {
            Region boundingRectangle = new Region(toAndroidRect$default(this, ((Outline.Generic) $this$toRegion).getRect().translate(leftOffset, topOffset), 0.0f, 0.0f, 3, null));
            Region $this$toRegion_u24lambda_u240 = new Region();
            Path $this$asAndroidPath$iv = ((Outline.Generic) $this$toRegion).getPath();
            if ($this$asAndroidPath$iv instanceof AndroidPath) {
                android.graphics.Path $this$toRegion_u24lambda_u240_u240 = ((AndroidPath) $this$asAndroidPath$iv).getInternalPath();
                $this$toRegion_u24lambda_u240_u240.offset(leftOffset, topOffset);
                $this$toRegion_u24lambda_u240.setPath($this$toRegion_u24lambda_u240_u240, boundingRectangle);
                return $this$toRegion_u24lambda_u240;
            }
            throw new UnsupportedOperationException("Unable to obtain android.graphics.Path");
        }
        return null;
    }

    static /* synthetic */ Rect toAndroidRect$default(AndroidComposeViewAccessibilityDelegateCompat androidComposeViewAccessibilityDelegateCompat, androidx.compose.ui.geometry.Rect rect, float f, float f2, int i, Object obj) {
        if ((i & 1) != 0) {
            f = 0.0f;
        }
        if ((i & 2) != 0) {
            f2 = 0.0f;
        }
        return androidComposeViewAccessibilityDelegateCompat.toAndroidRect(rect, f, f2);
    }

    private final Rect toAndroidRect(androidx.compose.ui.geometry.Rect $this$toAndroidRect, float leftOffset, float topOffset) {
        return new Rect((int) ($this$toAndroidRect.getLeft() + leftOffset), (int) ($this$toAndroidRect.getTop() + topOffset), (int) ($this$toAndroidRect.getRight() + leftOffset), (int) ($this$toAndroidRect.getBottom() + topOffset));
    }

    public final boolean dispatchHoverEvent$ui(MotionEvent event) {
        if (!isTouchExplorationEnabled()) {
            return false;
        }
        switch (event.getAction()) {
            case 7:
            case 9:
                int virtualViewId = hitTestSemanticsAt$ui(event.getX(), event.getY());
                boolean handled = this.view.getAndroidViewsHandler$ui().dispatchGenericMotionEvent(event);
                updateHoveredVirtualView(virtualViewId);
                if (virtualViewId == Integer.MIN_VALUE) {
                }
                break;
            case 10:
                if (this.hoveredVirtualViewId != Integer.MIN_VALUE) {
                    updateHoveredVirtualView(Integer.MIN_VALUE);
                }
                break;
        }
        return false;
    }

    public final int hitTestSemanticsAt$ui(float x, float y) {
        Owner.measureAndLayout$default(this.view, false, 1, null);
        HitTestResult hitSemanticsEntities = new HitTestResult();
        LayoutNode root = this.view.getRoot();
        long v1$iv$iv = Float.floatToRawIntBits(x);
        long v2$iv$iv = Float.floatToRawIntBits(y);
        LayoutNode.m7006hitTestSemantics6fMxITs$ui$default(root, Offset.m5060constructorimpl((v1$iv$iv << 32) | (v2$iv$iv & 4294967295L)), hitSemanticsEntities, 0, false, 12, null);
        for (int i = CollectionsKt.getLastIndex(hitSemanticsEntities); -1 < i; i--) {
            LayoutNode layoutNode = DelegatableNodeKt.requireLayoutNode(hitSemanticsEntities.get(i));
            AndroidViewHolder androidView = this.view.getAndroidViewsHandler$ui().getLayoutNodeToHolder().get(layoutNode);
            if (androidView != null) {
                return Integer.MIN_VALUE;
            }
            if (layoutNode.getNodes().m7060hasH91voCI$ui(NodeKind.m7100constructorimpl(8))) {
                int virtualViewId = semanticsNodeIdToAccessibilityVirtualNodeId(layoutNode.getSemanticsId());
                SemanticsNode semanticsNode = SemanticsNodeKt.SemanticsNode(layoutNode, false);
                if (SemanticsOwnerKt.isImportantForAccessibility(semanticsNode) && !SemanticsNode_androidKt.isAccessibilityIgnoredLink(semanticsNode)) {
                    return virtualViewId;
                }
            }
        }
        return Integer.MIN_VALUE;
    }

    private final void updateHoveredVirtualView(int virtualViewId) {
        if (this.hoveredVirtualViewId == virtualViewId) {
            return;
        }
        int previousVirtualViewId = this.hoveredVirtualViewId;
        this.hoveredVirtualViewId = virtualViewId;
        sendEventForVirtualView$default(this, virtualViewId, 128, null, null, 12, null);
        sendEventForVirtualView$default(this, previousVirtualViewId, 256, null, null, 12, null);
    }

    @Override // androidx.core.view.AccessibilityDelegateCompat
    public AccessibilityNodeProviderCompat getAccessibilityNodeProvider(View host) {
        return this.nodeProvider;
    }

    private final <T extends CharSequence> T trimToSize(T text, int size) {
        boolean z = true;
        if (!(size > 0)) {
            throw new IllegalArgumentException("size should be greater than 0".toString());
        }
        int i = size;
        if (text != null && text.length() != 0) {
            z = false;
        }
        if (z || text.length() <= size) {
            return text;
        }
        if (Character.isHighSurrogate(text.charAt(size - 1)) && Character.isLowSurrogate(text.charAt(size))) {
            i = size - 1;
        }
        T t = (T) text.subSequence(0, i);
        Intrinsics.checkNotNull(t, "null cannot be cast to non-null type T of androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat.trimToSize");
        return t;
    }

    static final void semanticsChangeChecker$lambda$0(AndroidComposeViewAccessibilityDelegateCompat this$0) {
        Trace.beginSection("measureAndLayout");
        try {
            Owner.measureAndLayout$default(this$0.view, false, 1, null);
            Unit unit = Unit.INSTANCE;
            Trace.endSection();
            Trace.beginSection("checkForSemanticsChanges");
            try {
                this$0.checkForSemanticsChanges();
                Unit unit2 = Unit.INSTANCE;
                Trace.endSection();
                this$0.checkingForSemanticsChanges = false;
            } finally {
            }
        } finally {
        }
    }

    public final void onSemanticsChange$ui() {
        this.currentSemanticsNodesInvalidated = true;
        Handler localHandler = getHandler();
        if (isEnabled$ui() && !this.checkingForSemanticsChanges && localHandler != null) {
            this.checkingForSemanticsChanges = true;
            localHandler.post(this.semanticsChangeChecker);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:22:0x006e A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x007d A[Catch: all -> 0x00e6, TryCatch #0 {all -> 0x00e6, blocks: (B:24:0x0075, B:26:0x007d, B:28:0x0086, B:30:0x0091, B:31:0x00a5, B:34:0x00b2, B:35:0x00b9), top: B:47:0x0075 }] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00dd  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /* JADX WARN: Type inference failed for: r3v0, types: [int] */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v10 */
    /* JADX WARN: Type inference failed for: r3v12 */
    /* JADX WARN: Type inference failed for: r3v13 */
    /* JADX WARN: Type inference failed for: r3v2, types: [androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat] */
    /* JADX WARN: Type inference failed for: r3v8 */
    /* JADX WARN: Type inference failed for: r3v9 */
    /* JADX WARN: Type inference failed for: r5v3 */
    /* JADX WARN: Type inference failed for: r5v4, types: [androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat] */
    /* JADX WARN: Type inference failed for: r5v7 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:38:0x00d8 -> B:20:0x0062). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object boundsUpdatesEventLoop$ui(kotlin.coroutines.Continuation<? super kotlin.Unit> r12) {
        /*
            Method dump skipped, instruction units count: 254
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat.boundsUpdatesEventLoop$ui(kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final void onLayoutChange$ui(LayoutNode layoutNode) {
        this.currentSemanticsNodesInvalidated = true;
        if (!isEnabled$ui()) {
            return;
        }
        notifySubtreeAccessibilityStateChangedIfNeeded(layoutNode);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void notifySubtreeAccessibilityStateChangedIfNeeded(LayoutNode layoutNode) {
        if (this.subtreeChangedLayoutNodes.add(layoutNode)) {
            this.boundsUpdateChannel.mo10436trySendJP2dKIU(Unit.INSTANCE);
        }
    }

    private final void sendTypeViewScrolledAccessibilityEvent(LayoutNode layoutNode) {
        if (!layoutNode.isAttached() || this.view.getAndroidViewsHandler$ui().getLayoutNodeToHolder().containsKey(layoutNode)) {
            return;
        }
        int id = layoutNode.getSemanticsId();
        ScrollAxisRange pendingHorizontalScroll = this.pendingHorizontalScrollEvents.get(id);
        ScrollAxisRange pendingVerticalScroll = this.pendingVerticalScrollEvents.get(id);
        if (pendingHorizontalScroll == null && pendingVerticalScroll == null) {
            return;
        }
        AccessibilityEvent event = createEvent(id, 4096);
        if (pendingHorizontalScroll != null) {
            event.setScrollX((int) pendingHorizontalScroll.getValue().invoke().floatValue());
            event.setMaxScrollX((int) pendingHorizontalScroll.getMaxValue().invoke().floatValue());
        }
        if (pendingVerticalScroll != null) {
            event.setScrollY((int) pendingVerticalScroll.getValue().invoke().floatValue());
            event.setMaxScrollY((int) pendingVerticalScroll.getMaxValue().invoke().floatValue());
        }
        sendEvent(event);
    }

    private final void sendSubtreeChangeAccessibilityEvents(LayoutNode layoutNode, MutableIntSet subtreeChangedSemanticsNodesIds) {
        SemanticsConfiguration config;
        LayoutNode it;
        if (layoutNode.isAttached() && !this.view.getAndroidViewsHandler$ui().getLayoutNodeToHolder().containsKey(layoutNode)) {
            LayoutNode layoutNodeFindClosestParentNode = layoutNode.getNodes().m7060hasH91voCI$ui(NodeKind.m7100constructorimpl(8)) ? layoutNode : AndroidComposeViewAccessibilityDelegateCompat_androidKt.findClosestParentNode(layoutNode, new Function1<LayoutNode, Boolean>() { // from class: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$sendSubtreeChangeAccessibilityEvents$semanticsNode$1
                @Override // kotlin.jvm.functions.Function1
                public final Boolean invoke(LayoutNode it2) {
                    return Boolean.valueOf(it2.getNodes().m7060hasH91voCI$ui(NodeKind.m7100constructorimpl(8)));
                }
            });
            if (layoutNodeFindClosestParentNode == null || (config = layoutNodeFindClosestParentNode.getSemanticsConfiguration()) == null) {
                return;
            }
            if (!config.getIsMergingSemanticsOfDescendants() && (it = AndroidComposeViewAccessibilityDelegateCompat_androidKt.findClosestParentNode(layoutNodeFindClosestParentNode, new Function1<LayoutNode, Boolean>() { // from class: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat.sendSubtreeChangeAccessibilityEvents.1
                @Override // kotlin.jvm.functions.Function1
                public final Boolean invoke(LayoutNode it2) {
                    SemanticsConfiguration semanticsConfiguration = it2.getSemanticsConfiguration();
                    boolean z = false;
                    if (semanticsConfiguration != null && semanticsConfiguration.getIsMergingSemanticsOfDescendants()) {
                        z = true;
                    }
                    return Boolean.valueOf(z);
                }
            })) != null) {
                layoutNodeFindClosestParentNode = it;
            }
            if (layoutNodeFindClosestParentNode != null) {
                int id = layoutNodeFindClosestParentNode.getSemanticsId();
                if (subtreeChangedSemanticsNodesIds.add(id)) {
                    sendEventForVirtualView$default(this, semanticsNodeIdToAccessibilityVirtualNodeId(id), 2048, 1, null, 8, null);
                }
            }
        }
    }

    private final void checkForSemanticsChanges() {
        Trace.beginSection("sendAccessibilitySemanticsStructureChangeEvents");
        try {
            if (isEnabled$ui()) {
                sendAccessibilitySemanticsStructureChangeEvents(this.view.getSemanticsOwner().getUnmergedRootSemanticsNode(), this.previousSemanticsRoot);
            }
            Unit unit = Unit.INSTANCE;
            Trace.endSection();
            Trace.beginSection("sendSemanticsPropertyChangeEvents");
            try {
                sendSemanticsPropertyChangeEvents(getCurrentSemanticsNodes());
                Unit unit2 = Unit.INSTANCE;
                Trace.endSection();
                Trace.beginSection("updateSemanticsNodesCopyAndPanes");
                try {
                    updateSemanticsNodesCopyAndPanes();
                    Unit unit3 = Unit.INSTANCE;
                } finally {
                }
            } finally {
            }
        } finally {
        }
    }

    private final void updateSemanticsNodesCopyAndPanes() {
        long j;
        long $this$maskEmptyOrDeleted$iv$iv$iv;
        int $i$f$forEach;
        int[] k$iv;
        Object[] v$iv;
        IntObjectMap<SemanticsNodeWithAdjustedBounds> intObjectMap;
        int j$iv$iv;
        int $i$f$forEach2;
        int[] k$iv2;
        Object[] v$iv2;
        IntObjectMap<SemanticsNodeWithAdjustedBounds> intObjectMap2;
        int i;
        int j$iv$iv2;
        long j2;
        SemanticsConfiguration unmergedConfig;
        MutableIntSet toRemove = new MutableIntSet(0, 1, null);
        IntSet this_$iv = this.paneDisplayed;
        int[] k$iv3 = this_$iv.elements;
        long[] m$iv$iv = this_$iv.metadata;
        int lastIndex$iv$iv = m$iv$iv.length - 2;
        int i$iv$iv = 0;
        long j3 = 255;
        int i2 = 8;
        if (0 <= lastIndex$iv$iv) {
            while (true) {
                long slot$iv$iv = m$iv$iv[i$iv$iv];
                $this$maskEmptyOrDeleted$iv$iv$iv = 128;
                long $this$maskEmptyOrDeleted$iv$iv$iv2 = ((~slot$iv$iv) << 7) & slot$iv$iv & (-9187201950435737472L);
                if ($this$maskEmptyOrDeleted$iv$iv$iv2 != -9187201950435737472L) {
                    int bitCount$iv$iv = 8 - ((~(i$iv$iv - lastIndex$iv$iv)) >>> 31);
                    int j$iv$iv3 = 0;
                    while (j$iv$iv3 < bitCount$iv$iv) {
                        long value$iv$iv$iv = slot$iv$iv & j3;
                        if (value$iv$iv$iv < 128) {
                            j2 = j3;
                            int id = k$iv3[(i$iv$iv << 3) + j$iv$iv3];
                            i = i2;
                            SemanticsNodeWithAdjustedBounds semanticsNodeWithAdjustedBounds = getCurrentSemanticsNodes().get(id);
                            SemanticsNode currentNode = semanticsNodeWithAdjustedBounds != null ? semanticsNodeWithAdjustedBounds.getSemanticsNode() : null;
                            if (currentNode != null) {
                                j$iv$iv2 = j$iv$iv3;
                                if (!currentNode.getUnmergedConfig().contains(SemanticsProperties.INSTANCE.getPaneTitle())) {
                                }
                            } else {
                                j$iv$iv2 = j$iv$iv3;
                            }
                            toRemove.add(id);
                            SemanticsNodeCopy semanticsNodeCopy = this.previousSemanticsNodes.get(id);
                            sendPaneChangeEvents(id, 32, (semanticsNodeCopy == null || (unmergedConfig = semanticsNodeCopy.getUnmergedConfig()) == null) ? null : (String) SemanticsConfigurationKt.getOrNull(unmergedConfig, SemanticsProperties.INSTANCE.getPaneTitle()));
                        } else {
                            i = i2;
                            j$iv$iv2 = j$iv$iv3;
                            j2 = j3;
                        }
                        slot$iv$iv >>= i;
                        j$iv$iv3 = j$iv$iv2 + 1;
                        j3 = j2;
                        i2 = i;
                    }
                    j = j3;
                    if (bitCount$iv$iv != i2) {
                        break;
                    }
                } else {
                    j = j3;
                }
                if (i$iv$iv == lastIndex$iv$iv) {
                    break;
                }
                i$iv$iv++;
                j3 = j;
                i2 = 8;
            }
        } else {
            j = 255;
            $this$maskEmptyOrDeleted$iv$iv$iv = 128;
        }
        this.paneDisplayed.removeAll(toRemove);
        this.previousSemanticsNodes.clear();
        IntObjectMap<SemanticsNodeWithAdjustedBounds> currentSemanticsNodes = getCurrentSemanticsNodes();
        int $i$f$forEach3 = 0;
        int[] k$iv4 = currentSemanticsNodes.keys;
        Object[] v$iv3 = currentSemanticsNodes.values;
        IntObjectMap<SemanticsNodeWithAdjustedBounds> intObjectMap3 = currentSemanticsNodes;
        long[] m$iv$iv2 = intObjectMap3.metadata;
        int lastIndex$iv$iv2 = m$iv$iv2.length - 2;
        int i$iv$iv2 = 0;
        if (0 <= lastIndex$iv$iv2) {
            while (true) {
                long slot$iv$iv2 = m$iv$iv2[i$iv$iv2];
                MutableIntSet toRemove2 = toRemove;
                IntObjectMap<SemanticsNodeWithAdjustedBounds> intObjectMap4 = currentSemanticsNodes;
                if ((((~slot$iv$iv2) << 7) & slot$iv$iv2 & (-9187201950435737472L)) != -9187201950435737472L) {
                    int bitCount$iv$iv2 = 8 - ((~(i$iv$iv2 - lastIndex$iv$iv2)) >>> 31);
                    int j$iv$iv4 = 0;
                    while (j$iv$iv4 < bitCount$iv$iv2) {
                        long value$iv$iv$iv2 = slot$iv$iv2 & j;
                        if (value$iv$iv$iv2 < $this$maskEmptyOrDeleted$iv$iv$iv) {
                            int index$iv$iv = (i$iv$iv2 << 3) + j$iv$iv4;
                            j$iv$iv = j$iv$iv4;
                            int j$iv$iv5 = k$iv4[index$iv$iv];
                            SemanticsNodeWithAdjustedBounds value = (SemanticsNodeWithAdjustedBounds) v$iv3[index$iv$iv];
                            $i$f$forEach2 = $i$f$forEach3;
                            k$iv2 = k$iv4;
                            if (value.getSemanticsNode().getUnmergedConfig().contains(SemanticsProperties.INSTANCE.getPaneTitle()) && this.paneDisplayed.add(j$iv$iv5)) {
                                sendPaneChangeEvents(j$iv$iv5, 16, (String) value.getSemanticsNode().getUnmergedConfig().get(SemanticsProperties.INSTANCE.getPaneTitle()));
                            }
                            v$iv2 = v$iv3;
                            intObjectMap2 = intObjectMap3;
                            this.previousSemanticsNodes.set(j$iv$iv5, new SemanticsNodeCopy(value.getSemanticsNode(), getCurrentSemanticsNodes()));
                        } else {
                            j$iv$iv = j$iv$iv4;
                            $i$f$forEach2 = $i$f$forEach3;
                            k$iv2 = k$iv4;
                            v$iv2 = v$iv3;
                            intObjectMap2 = intObjectMap3;
                        }
                        slot$iv$iv2 >>= 8;
                        j$iv$iv4 = j$iv$iv + 1;
                        v$iv3 = v$iv2;
                        $i$f$forEach3 = $i$f$forEach2;
                        k$iv4 = k$iv2;
                        intObjectMap3 = intObjectMap2;
                    }
                    $i$f$forEach = $i$f$forEach3;
                    k$iv = k$iv4;
                    v$iv = v$iv3;
                    intObjectMap = intObjectMap3;
                    if (bitCount$iv$iv2 != 8) {
                        break;
                    }
                } else {
                    $i$f$forEach = $i$f$forEach3;
                    k$iv = k$iv4;
                    v$iv = v$iv3;
                    intObjectMap = intObjectMap3;
                }
                if (i$iv$iv2 == lastIndex$iv$iv2) {
                    break;
                }
                i$iv$iv2++;
                toRemove = toRemove2;
                currentSemanticsNodes = intObjectMap4;
                v$iv3 = v$iv;
                $i$f$forEach3 = $i$f$forEach;
                k$iv4 = k$iv;
                intObjectMap3 = intObjectMap;
            }
        }
        this.previousSemanticsRoot = new SemanticsNodeCopy(this.view.getSemanticsOwner().getUnmergedRootSemanticsNode(), getCurrentSemanticsNodes());
    }

    /* JADX WARN: Removed duplicated region for block: B:141:0x058f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void sendSemanticsPropertyChangeEvents(androidx.collection.IntObjectMap<androidx.compose.ui.semantics.SemanticsNodeWithAdjustedBounds> r75) {
        /*
            Method dump skipped, instruction units count: 2382
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat.sendSemanticsPropertyChangeEvents(androidx.collection.IntObjectMap):void");
    }

    private final boolean registerScrollingId(int id, List<ScrollObservationScope> oldScrollObservationScopes) {
        ScrollObservationScope newScope;
        boolean newlyObservingScroll = false;
        ScrollObservationScope oldScope = SemanticsUtils_androidKt.findById(oldScrollObservationScopes, id);
        if (oldScope != null) {
            newScope = oldScope;
        } else {
            newlyObservingScroll = true;
            newScope = new ScrollObservationScope(id, this.scrollObservationScopes, null, null, null, null);
        }
        this.scrollObservationScopes.add(newScope);
        return newlyObservingScroll;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void scheduleScrollEventIfNeeded(final ScrollObservationScope scrollObservationScope) {
        if (!scrollObservationScope.isValidOwnerScope()) {
            return;
        }
        OwnerSnapshotObserver this_$iv = this.view.getSnapshotObserver();
        Function1<ScrollObservationScope, Unit> function1 = this.scheduleScrollEventIfNeededLambda;
        Function0<Unit> function0 = new Function0<Unit>() { // from class: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat.scheduleScrollEventIfNeeded.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* JADX WARN: Removed duplicated region for block: B:22:0x0062  */
            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final void invoke2() {
                /*
                    Method dump skipped, instruction units count: 297
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat.AnonymousClass1.invoke2():void");
            }
        };
        this_$iv.observer.observeReads(scrollObservationScope, function1, function0);
    }

    private final void sendPaneChangeEvents(int semanticsNodeId, int contentChangeType, String title) {
        AccessibilityEvent event = createEvent(semanticsNodeIdToAccessibilityVirtualNodeId(semanticsNodeId), 32);
        event.setContentChangeTypes(contentChangeType);
        if (title != null) {
            event.getText().add(title);
        }
        sendEvent(event);
    }

    private final void sendAccessibilitySemanticsStructureChangeEvents(SemanticsNode newNode, SemanticsNodeCopy oldNode) {
        MutableIntSet newChildren;
        MutableIntSet newChildren2;
        int i;
        MutableIntSet newChildren3 = IntSetKt.mutableIntSetOf();
        List<SemanticsNode> replacedChildren$ui = newNode.getReplacedChildren$ui();
        int size = replacedChildren$ui.size();
        for (int index$iv = 0; index$iv < size; index$iv++) {
            Object item$iv = replacedChildren$ui.get(index$iv);
            SemanticsNode child = (SemanticsNode) item$iv;
            IntObjectMap<SemanticsNodeWithAdjustedBounds> currentSemanticsNodes = getCurrentSemanticsNodes();
            int key$iv = child.getId();
            if (currentSemanticsNodes.containsKey(key$iv)) {
                if (!oldNode.getChildren().contains(child.getId())) {
                    notifySubtreeAccessibilityStateChangedIfNeeded(newNode.getLayoutNode());
                    return;
                }
                newChildren3.add(child.getId());
            }
        }
        IntSet this_$iv = oldNode.getChildren();
        int $i$f$forEach = 0;
        int[] k$iv = this_$iv.elements;
        long[] m$iv$iv = this_$iv.metadata;
        int lastIndex$iv$iv = m$iv$iv.length - 2;
        int i$iv$iv = 0;
        if (0 <= lastIndex$iv$iv) {
            while (true) {
                long slot$iv$iv = m$iv$iv[i$iv$iv];
                IntSet this_$iv2 = this_$iv;
                int $i$f$forEach2 = $i$f$forEach;
                if ((((~slot$iv$iv) << 7) & slot$iv$iv & (-9187201950435737472L)) == -9187201950435737472L) {
                    newChildren = newChildren3;
                } else {
                    int i2 = 8;
                    int bitCount$iv$iv = 8 - ((~(i$iv$iv - lastIndex$iv$iv)) >>> 31);
                    int j$iv$iv = 0;
                    while (j$iv$iv < bitCount$iv$iv) {
                        long value$iv$iv$iv = 255 & slot$iv$iv;
                        if (!(value$iv$iv$iv < 128)) {
                            newChildren2 = newChildren3;
                            i = i2;
                        } else {
                            int index$iv$iv = (i$iv$iv << 3) + j$iv$iv;
                            i = i2;
                            if (!newChildren3.contains(k$iv[index$iv$iv])) {
                                notifySubtreeAccessibilityStateChangedIfNeeded(newNode.getLayoutNode());
                                return;
                            }
                            newChildren2 = newChildren3;
                        }
                        slot$iv$iv >>= i;
                        j$iv$iv++;
                        i2 = i;
                        newChildren3 = newChildren2;
                    }
                    newChildren = newChildren3;
                    if (bitCount$iv$iv != i2) {
                        break;
                    }
                }
                if (i$iv$iv == lastIndex$iv$iv) {
                    break;
                }
                i$iv$iv++;
                this_$iv = this_$iv2;
                $i$f$forEach = $i$f$forEach2;
                newChildren3 = newChildren;
            }
        }
        List<SemanticsNode> replacedChildren$ui2 = newNode.getReplacedChildren$ui();
        int size2 = replacedChildren$ui2.size();
        for (int index$iv2 = 0; index$iv2 < size2; index$iv2++) {
            Object item$iv2 = replacedChildren$ui2.get(index$iv2);
            SemanticsNode child2 = (SemanticsNode) item$iv2;
            SemanticsNodeCopy previousNode = this.previousSemanticsNodes.get(child2.getId());
            if (previousNode != null) {
                IntObjectMap<SemanticsNodeWithAdjustedBounds> currentSemanticsNodes2 = getCurrentSemanticsNodes();
                int key$iv2 = child2.getId();
                if (currentSemanticsNodes2.containsKey(key$iv2)) {
                    sendAccessibilitySemanticsStructureChangeEvents(child2, previousNode);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int semanticsNodeIdToAccessibilityVirtualNodeId(int id) {
        if (id == this.view.getSemanticsOwner().getUnmergedRootSemanticsNode().getId()) {
            return -1;
        }
        return id;
    }

    private final boolean traverseAtGranularity(SemanticsNode node, int granularity, boolean forward, boolean extendSelection) {
        AccessibilityIterators.TextSegmentIterator iterator;
        int selectionEnd;
        int selectionStart;
        int id = node.getId();
        Integer num = this.previousTraversedNode;
        if (num == null || id != num.intValue()) {
            this.accessibilityCursorPosition = -1;
            this.previousTraversedNode = Integer.valueOf(node.getId());
        }
        String text = getIterableTextForAccessibility(node);
        String str = text;
        if ((str == null || str.length() == 0) || (iterator = getIteratorForGranularity(node, granularity)) == null) {
            return false;
        }
        int current = getAccessibilitySelectionEnd(node);
        if (current == -1) {
            current = forward ? 0 : text.length();
        }
        int[] range = forward ? iterator.following(current) : iterator.preceding(current);
        if (range == null) {
            return false;
        }
        int segmentStart = range[0];
        int segmentEnd = range[1];
        if (extendSelection && isAccessibilitySelectionExtendable(node)) {
            selectionStart = getAccessibilitySelectionStart(node);
            if (selectionStart == -1) {
                selectionStart = forward ? segmentStart : segmentEnd;
            }
            selectionEnd = forward ? segmentEnd : segmentStart;
        } else {
            selectionEnd = forward ? segmentEnd : segmentStart;
            selectionStart = selectionEnd;
        }
        int action = forward ? 256 : 512;
        this.pendingTextTraversedEvent = new PendingTextTraversedEvent(node, action, granularity, segmentStart, segmentEnd, SystemClock.uptimeMillis());
        setAccessibilitySelection(node, selectionStart, selectionEnd, true);
        return true;
    }

    private final void sendPendingTextTraversedAtGranularityEvent(int semanticsNodeId) {
        PendingTextTraversedEvent it = this.pendingTextTraversedEvent;
        if (it != null) {
            if (semanticsNodeId != it.getNode().getId()) {
                return;
            }
            if (SystemClock.uptimeMillis() - it.getTraverseTime() <= 1000) {
                AccessibilityEvent event = createEvent(semanticsNodeIdToAccessibilityVirtualNodeId(it.getNode().getId()), 131072);
                event.setFromIndex(it.getFromIndex());
                event.setToIndex(it.getToIndex());
                event.setAction(it.getAction());
                event.setMovementGranularity(it.getGranularity());
                event.getText().add(getIterableTextForAccessibility(it.getNode()));
                sendEvent(event);
            }
        }
        this.pendingTextTraversedEvent = null;
    }

    private final boolean setAccessibilitySelection(SemanticsNode node, int start, int end, boolean traversalMode) {
        String text;
        int i;
        if (node.getUnmergedConfig().contains(SemanticsActions.INSTANCE.getSetSelection()) && AndroidComposeViewAccessibilityDelegateCompat_androidKt.enabled(node)) {
            Function3 function3 = (Function3) ((AccessibilityAction) node.getUnmergedConfig().get(SemanticsActions.INSTANCE.getSetSelection())).getAction();
            if (function3 != null) {
                return ((Boolean) function3.invoke(Integer.valueOf(start), Integer.valueOf(end), Boolean.valueOf(traversalMode))).booleanValue();
            }
            return false;
        }
        if ((start == end && end == this.accessibilityCursorPosition) || (text = getIterableTextForAccessibility(node)) == null) {
            return false;
        }
        if (start >= 0 && start == end && end <= text.length()) {
            i = start;
        } else {
            i = -1;
        }
        this.accessibilityCursorPosition = i;
        boolean nonEmptyText = text.length() > 0;
        AccessibilityEvent event = createTextSelectionChangedEvent(semanticsNodeIdToAccessibilityVirtualNodeId(node.getId()), nonEmptyText ? Integer.valueOf(this.accessibilityCursorPosition) : null, nonEmptyText ? Integer.valueOf(this.accessibilityCursorPosition) : null, nonEmptyText ? Integer.valueOf(text.length()) : null, text);
        sendEvent(event);
        sendPendingTextTraversedAtGranularityEvent(node.getId());
        return true;
    }

    private final int getAccessibilitySelectionStart(SemanticsNode node) {
        if (!node.getUnmergedConfig().contains(SemanticsProperties.INSTANCE.getContentDescription()) && node.getUnmergedConfig().contains(SemanticsProperties.INSTANCE.getTextSelectionRange())) {
            return TextRange.m7573getStartimpl(((TextRange) node.getUnmergedConfig().get(SemanticsProperties.INSTANCE.getTextSelectionRange())).getPackedValue());
        }
        return this.accessibilityCursorPosition;
    }

    private final int getAccessibilitySelectionEnd(SemanticsNode node) {
        if (!node.getUnmergedConfig().contains(SemanticsProperties.INSTANCE.getContentDescription()) && node.getUnmergedConfig().contains(SemanticsProperties.INSTANCE.getTextSelectionRange())) {
            return TextRange.m7568getEndimpl(((TextRange) node.getUnmergedConfig().get(SemanticsProperties.INSTANCE.getTextSelectionRange())).getPackedValue());
        }
        return this.accessibilityCursorPosition;
    }

    private final boolean isAccessibilitySelectionExtendable(SemanticsNode node) {
        return !node.getUnmergedConfig().contains(SemanticsProperties.INSTANCE.getContentDescription()) && node.getUnmergedConfig().contains(SemanticsProperties.INSTANCE.getEditableText());
    }

    private final AccessibilityIterators.TextSegmentIterator getIteratorForGranularity(SemanticsNode node, int granularity) {
        AccessibilityIterators.AbstractTextSegmentIterator iterator;
        TextLayoutResult textLayoutResult;
        if (node == null) {
            return null;
        }
        String text = getIterableTextForAccessibility(node);
        String str = text;
        if (str == null || str.length() == 0) {
            return null;
        }
        switch (granularity) {
            case 1:
                iterator = AccessibilityIterators.CharacterTextSegmentIterator.INSTANCE.getInstance(this.view.getContext().getResources().getConfiguration().locale);
                ((AccessibilityIterators.CharacterTextSegmentIterator) iterator).initialize(text);
                break;
            case 2:
                iterator = AccessibilityIterators.WordTextSegmentIterator.INSTANCE.getInstance(this.view.getContext().getResources().getConfiguration().locale);
                ((AccessibilityIterators.WordTextSegmentIterator) iterator).initialize(text);
                break;
            case 4:
            case 16:
                if (!node.getUnmergedConfig().contains(SemanticsActions.INSTANCE.getGetTextLayoutResult()) || (textLayoutResult = SemanticsUtils_androidKt.getTextLayoutResult(node.getUnmergedConfig())) == null) {
                    return null;
                }
                if (granularity == 4) {
                    iterator = AccessibilityIterators.LineTextSegmentIterator.INSTANCE.getInstance();
                    ((AccessibilityIterators.LineTextSegmentIterator) iterator).initialize(text, textLayoutResult);
                } else {
                    iterator = AccessibilityIterators.PageTextSegmentIterator.INSTANCE.getInstance();
                    ((AccessibilityIterators.PageTextSegmentIterator) iterator).initialize(text, textLayoutResult, node);
                }
                break;
                break;
            case 8:
                iterator = AccessibilityIterators.ParagraphTextSegmentIterator.INSTANCE.getInstance();
                iterator.initialize(text);
                break;
            default:
                return null;
        }
        return iterator;
    }

    private final String getIterableTextForAccessibility(SemanticsNode node) {
        AnnotatedString annotatedString;
        if (node == null) {
            return null;
        }
        if (node.getUnmergedConfig().contains(SemanticsProperties.INSTANCE.getContentDescription())) {
            return ListUtilsKt.fastJoinToString$default((List) node.getUnmergedConfig().get(SemanticsProperties.INSTANCE.getContentDescription()), ",", null, null, 0, null, null, 62, null);
        }
        if (node.getUnmergedConfig().contains(SemanticsProperties.INSTANCE.getEditableText())) {
            AnnotatedString textForTextField = getTextForTextField(node.getUnmergedConfig());
            if (textForTextField != null) {
                return textForTextField.getText();
            }
            return null;
        }
        List list = (List) SemanticsConfigurationKt.getOrNull(node.getUnmergedConfig(), SemanticsProperties.INSTANCE.getText());
        if (list == null || (annotatedString = (AnnotatedString) CollectionsKt.firstOrNull(list)) == null) {
            return null;
        }
        return annotatedString.getText();
    }

    private final AnnotatedString getTextForTextField(SemanticsConfiguration $this$getTextForTextField) {
        return (AnnotatedString) SemanticsConfigurationKt.getOrNull($this$getTextForTextField, SemanticsProperties.INSTANCE.getEditableText());
    }

    /* JADX INFO: compiled from: AndroidComposeViewAccessibilityDelegateCompat.android.kt */
    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\"\u0010\b\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u00072\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016J*\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016J\u0012\u0010\u0012\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0013\u001a\u00020\u0007H\u0016¨\u0006\u0014"}, d2 = {"Landroidx/compose/ui/platform/AndroidComposeViewAccessibilityDelegateCompat$ComposeAccessibilityNodeProvider;", "Landroidx/core/view/accessibility/AccessibilityNodeProviderCompat;", "<init>", "(Landroidx/compose/ui/platform/AndroidComposeViewAccessibilityDelegateCompat;)V", "createAccessibilityNodeInfo", "Landroidx/core/view/accessibility/AccessibilityNodeInfoCompat;", "virtualViewId", "", "performAction", "", "action", "arguments", "Landroid/os/Bundle;", "addExtraDataToAccessibilityNodeInfo", "", "info", "extraDataKey", "", "findFocus", "focus", "ui"}, k = 1, mv = {2, 1, 0}, xi = 48)
    private final class ComposeAccessibilityNodeProvider extends AccessibilityNodeProviderCompat {
        public ComposeAccessibilityNodeProvider() {
        }

        @Override // androidx.core.view.accessibility.AccessibilityNodeProviderCompat
        public AccessibilityNodeInfoCompat createAccessibilityNodeInfo(int virtualViewId) {
            AccessibilityNodeInfoCompat it = AndroidComposeViewAccessibilityDelegateCompat.this.createNodeInfo(virtualViewId);
            AndroidComposeViewAccessibilityDelegateCompat androidComposeViewAccessibilityDelegateCompat = AndroidComposeViewAccessibilityDelegateCompat.this;
            if (androidComposeViewAccessibilityDelegateCompat.sendingFocusAffectingEvent) {
                if (virtualViewId == androidComposeViewAccessibilityDelegateCompat.accessibilityFocusedVirtualViewId) {
                    androidComposeViewAccessibilityDelegateCompat.currentlyAccessibilityFocusedANI = it;
                }
                if (virtualViewId == androidComposeViewAccessibilityDelegateCompat.focusedVirtualViewId) {
                    androidComposeViewAccessibilityDelegateCompat.currentlyFocusedANI = it;
                }
            }
            return it;
        }

        @Override // androidx.core.view.accessibility.AccessibilityNodeProviderCompat
        public boolean performAction(int virtualViewId, int action, Bundle arguments) {
            return AndroidComposeViewAccessibilityDelegateCompat.this.performActionHelper(virtualViewId, action, arguments);
        }

        @Override // androidx.core.view.accessibility.AccessibilityNodeProviderCompat
        public void addExtraDataToAccessibilityNodeInfo(int virtualViewId, AccessibilityNodeInfoCompat info, String extraDataKey, Bundle arguments) {
            AndroidComposeViewAccessibilityDelegateCompat.this.addExtraDataToAccessibilityNodeInfoHelper(virtualViewId, info, extraDataKey, arguments);
        }

        @Override // androidx.core.view.accessibility.AccessibilityNodeProviderCompat
        public AccessibilityNodeInfoCompat findFocus(int focus) {
            switch (focus) {
                case 1:
                    if (AndroidComposeViewAccessibilityDelegateCompat.this.focusedVirtualViewId == Integer.MIN_VALUE) {
                        return null;
                    }
                    return createAccessibilityNodeInfo(AndroidComposeViewAccessibilityDelegateCompat.this.focusedVirtualViewId);
                case 2:
                    return createAccessibilityNodeInfo(AndroidComposeViewAccessibilityDelegateCompat.this.accessibilityFocusedVirtualViewId);
                default:
                    throw new IllegalArgumentException("Unknown focus type: " + focus);
            }
        }
    }

    /* JADX INFO: compiled from: AndroidComposeViewAccessibilityDelegateCompat.android.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÃ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0007¨\u0006\n"}, d2 = {"Landroidx/compose/ui/platform/AndroidComposeViewAccessibilityDelegateCompat$Api24Impl;", "", "<init>", "()V", "addSetProgressAction", "", "info", "Landroidx/core/view/accessibility/AccessibilityNodeInfoCompat;", "semanticsNode", "Landroidx/compose/ui/semantics/SemanticsNode;", "ui"}, k = 1, mv = {2, 1, 0}, xi = 48)
    private static final class Api24Impl {
        public static final Api24Impl INSTANCE = new Api24Impl();

        private Api24Impl() {
        }

        @JvmStatic
        public static final void addSetProgressAction(AccessibilityNodeInfoCompat info, SemanticsNode semanticsNode) {
            AccessibilityAction it;
            if (AndroidComposeViewAccessibilityDelegateCompat_androidKt.enabled(semanticsNode) && (it = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsActions.INSTANCE.getSetProgress())) != null) {
                info.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(android.R.id.accessibilityActionSetProgress, it.getLabel()));
            }
        }
    }

    /* JADX INFO: compiled from: AndroidComposeViewAccessibilityDelegateCompat.android.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÃ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0007¨\u0006\n"}, d2 = {"Landroidx/compose/ui/platform/AndroidComposeViewAccessibilityDelegateCompat$Api29Impl;", "", "<init>", "()V", "addPageActions", "", "info", "Landroidx/core/view/accessibility/AccessibilityNodeInfoCompat;", "semanticsNode", "Landroidx/compose/ui/semantics/SemanticsNode;", "ui"}, k = 1, mv = {2, 1, 0}, xi = 48)
    private static final class Api29Impl {
        public static final Api29Impl INSTANCE = new Api29Impl();

        private Api29Impl() {
        }

        @JvmStatic
        public static final void addPageActions(AccessibilityNodeInfoCompat info, SemanticsNode semanticsNode) {
            Role role = (Role) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsProperties.INSTANCE.getRole());
            if (AndroidComposeViewAccessibilityDelegateCompat_androidKt.enabled(semanticsNode)) {
                if (!(role == null ? false : Role.m7339equalsimpl0(role.getValue(), Role.INSTANCE.m7344getCarouselo7Vup1c()))) {
                    AccessibilityAction it = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsActions.INSTANCE.getPageUp());
                    if (it != null) {
                        info.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(android.R.id.accessibilityActionPageUp, it.getLabel()));
                    }
                    AccessibilityAction it2 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsActions.INSTANCE.getPageDown());
                    if (it2 != null) {
                        info.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(android.R.id.accessibilityActionPageDown, it2.getLabel()));
                    }
                    AccessibilityAction it3 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsActions.INSTANCE.getPageLeft());
                    if (it3 != null) {
                        info.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(android.R.id.accessibilityActionPageLeft, it3.getLabel()));
                    }
                    AccessibilityAction it4 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsActions.INSTANCE.getPageRight());
                    if (it4 != null) {
                        info.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(android.R.id.accessibilityActionPageRight, it4.getLabel()));
                    }
                }
            }
        }
    }
}
