package androidx.compose.ui.viewinterop;

import android.content.Context;
import android.graphics.Region;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowInsets;
import androidx.autofill.HintConstants;
import androidx.compose.runtime.ComposeNodeLifecycleCallback;
import androidx.compose.runtime.CompositionContext;
import androidx.compose.ui.ComposeUiFlags;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.DrawModifierKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.graphics.AndroidCanvas_androidKt;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.RectHelper_androidKt;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.input.nestedscroll.NestedScrollDispatcher;
import androidx.compose.ui.input.nestedscroll.NestedScrollModifierKt;
import androidx.compose.ui.input.pointer.PointerInteropFilter_androidKt;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LayoutCoordinatesKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.OnGloballyPositionedModifierKt;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.NodeCoordinator;
import androidx.compose.ui.node.Owner;
import androidx.compose.ui.node.OwnerScope;
import androidx.compose.ui.node.OwnerSnapshotObserver;
import androidx.compose.ui.platform.AndroidComposeView;
import androidx.compose.ui.platform.NestedScrollInteropConnectionKt;
import androidx.compose.ui.platform.WindowRecomposer_androidKt;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.DensityKt;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntOffsetKt;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.Velocity;
import androidx.compose.ui.unit.VelocityKt;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.graphics.Insets;
import androidx.core.view.NestedScrollingParent3;
import androidx.core.view.NestedScrollingParentHelper;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsAnimationCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewTreeLifecycleOwner;
import androidx.savedstate.SavedStateRegistryOwner;
import androidx.savedstate.ViewTreeSavedStateRegistryOwner;
import androidx.savedstate.serialization.ClassDiscriminatorModeKt;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: AndroidViewHolder.android.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\u0080\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0015\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\r\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0011\u0018\u0000 º\u00012\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u0005:\u0002º\u0001B9\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011¢\u0006\u0004\b\u0012\u0010\u0013J\u000e\u0010\u0016\u001a\n\u0018\u00010\u000fj\u0004\u0018\u0001`\u0017J\b\u0010d\u001a\u00020eH\u0016J\b\u0010f\u001a\u00020\u001aH\u0016J\b\u0010g\u001a\u00020\u001aH\u0016J\b\u0010h\u001a\u00020\u001aH\u0016J\u0018\u0010i\u001a\u00020\u001a2\u0006\u0010j\u001a\u00020\u000b2\u0006\u0010k\u001a\u00020\u000bH\u0014J\u0006\u0010l\u001a\u00020\u001aJ0\u0010m\u001a\u00020\u001a2\u0006\u0010n\u001a\u00020!2\u0006\u0010o\u001a\u00020\u000b2\u0006\u0010p\u001a\u00020\u000b2\u0006\u0010q\u001a\u00020\u000b2\u0006\u0010r\u001a\u00020\u000bH\u0014J\n\u0010s\u001a\u0004\u0018\u00010tH\u0016J\u0010\u0010u\u001a\u00020\u001a2\u0006\u0010v\u001a\u00020!H\u0016J\b\u0010w\u001a\u00020\u001aH\u0014J\b\u0010x\u001a\u00020\u001aH\u0014J\u001e\u0010y\u001a\u0004\u0018\u00010z2\b\u0010\\\u001a\u0004\u0018\u00010J2\b\u0010{\u001a\u0004\u0018\u00010|H\u0017J\u0018\u0010}\u001a\u00020\u001a2\u0006\u0010~\u001a\u00020\u000f2\u0006\u0010\u007f\u001a\u00020\u000fH\u0016J%\u0010\u0080\u0001\u001a\u00020!2\u0006\u0010~\u001a\u00020\u000f2\t\u0010\u0081\u0001\u001a\u0004\u0018\u00010|2\u0007\u0010\u0082\u0001\u001a\u00020!H\u0016J\u0007\u0010\u0083\u0001\u001a\u00020\u001aJ\u0012\u0010\u0084\u0001\u001a\u00020\u001a2\u0007\u0010\u0085\u0001\u001a\u00020\u000bH\u0014J\u0015\u0010\u0086\u0001\u001a\u00020!2\n\u0010\u0087\u0001\u001a\u0005\u0018\u00010\u0088\u0001H\u0016J$\u0010\u008d\u0001\u001a\u00020\u000b2\u0007\u0010\u008e\u0001\u001a\u00020\u000b2\u0007\u0010\u008f\u0001\u001a\u00020\u000b2\u0007\u0010\u0090\u0001\u001a\u00020\u000bH\u0002J\t\u0010\u0091\u0001\u001a\u00020!H\u0016J+\u0010\u0092\u0001\u001a\u00020!2\u0006\u0010~\u001a\u00020\u000f2\u0006\u0010\u007f\u001a\u00020\u000f2\u0007\u0010\u0093\u0001\u001a\u00020\u000b2\u0007\u0010\u0094\u0001\u001a\u00020\u000bH\u0016J\t\u0010\u0095\u0001\u001a\u00020\u000bH\u0016J+\u0010\u0096\u0001\u001a\u00020\u001a2\u0006\u0010~\u001a\u00020\u000f2\u0006\u0010\u007f\u001a\u00020\u000f2\u0007\u0010\u0093\u0001\u001a\u00020\u000b2\u0007\u0010\u0094\u0001\u001a\u00020\u000bH\u0016J\u001a\u0010\u0097\u0001\u001a\u00020\u001a2\u0006\u0010\u007f\u001a\u00020\u000f2\u0007\u0010\u0094\u0001\u001a\u00020\u000bH\u0016JG\u0010\u0098\u0001\u001a\u00020\u001a2\u0006\u0010\u007f\u001a\u00020\u000f2\u0007\u0010\u0099\u0001\u001a\u00020\u000b2\u0007\u0010\u009a\u0001\u001a\u00020\u000b2\u0007\u0010\u009b\u0001\u001a\u00020\u000b2\u0007\u0010\u009c\u0001\u001a\u00020\u000b2\u0007\u0010\u0094\u0001\u001a\u00020\u000b2\u0007\u0010\u009d\u0001\u001a\u00020JH\u0016J>\u0010\u0098\u0001\u001a\u00020\u001a2\u0006\u0010\u007f\u001a\u00020\u000f2\u0007\u0010\u0099\u0001\u001a\u00020\u000b2\u0007\u0010\u009a\u0001\u001a\u00020\u000b2\u0007\u0010\u009b\u0001\u001a\u00020\u000b2\u0007\u0010\u009c\u0001\u001a\u00020\u000b2\u0007\u0010\u0094\u0001\u001a\u00020\u000bH\u0016J5\u0010\u009e\u0001\u001a\u00020\u001a2\u0006\u0010\u007f\u001a\u00020\u000f2\u0007\u0010\u009f\u0001\u001a\u00020\u000b2\u0007\u0010 \u0001\u001a\u00020\u000b2\u0007\u0010\u009d\u0001\u001a\u00020J2\u0007\u0010\u0094\u0001\u001a\u00020\u000bH\u0016J.\u0010¡\u0001\u001a\u00020!2\u0006\u0010\u007f\u001a\u00020\u000f2\b\u0010¢\u0001\u001a\u00030£\u00012\b\u0010¤\u0001\u001a\u00030£\u00012\u0007\u0010\u009d\u0001\u001a\u00020!H\u0016J%\u0010¥\u0001\u001a\u00020!2\u0006\u0010\u007f\u001a\u00020\u000f2\b\u0010¢\u0001\u001a\u00030£\u00012\b\u0010¤\u0001\u001a\u00030£\u0001H\u0016J\t\u0010¦\u0001\u001a\u00020!H\u0016J\u001a\u0010§\u0001\u001a\u00020O2\u0007\u0010¨\u0001\u001a\u00020\u000f2\u0006\u0010N\u001a\u00020OH\u0016J\u0011\u0010©\u0001\u001a\u00020O2\u0006\u0010N\u001a\u00020OH\u0002J\u0014\u0010ª\u0001\u001a\u00030«\u00012\b\u0010¬\u0001\u001a\u00030«\u0001H\u0002J\u008e\u0001\u0010\u00ad\u0001\u001a\u0003H®\u0001\"\u0005\b\u0000\u0010®\u00012\u0007\u0010\u0018\u001a\u0003H®\u00012k\u0010¯\u0001\u001af\u0012\u0015\u0012\u00130\u000b¢\u0006\u000e\b±\u0001\u0012\t\b²\u0001\u0012\u0004\b\b(o\u0012\u0015\u0012\u00130\u000b¢\u0006\u000e\b±\u0001\u0012\t\b²\u0001\u0012\u0004\b\b(p\u0012\u0015\u0012\u00130\u000b¢\u0006\u000e\b±\u0001\u0012\t\b²\u0001\u0012\u0004\b\b(q\u0012\u0015\u0012\u00130\u000b¢\u0006\u000e\b±\u0001\u0012\t\b²\u0001\u0012\u0004\b\b(r\u0012\u0005\u0012\u0003H®\u00010°\u0001H\u0082\b¢\u0006\u0003\u0010³\u0001J3\u0010´\u0001\u001a\u00030µ\u0001*\u00030µ\u00012\u0007\u0010¶\u0001\u001a\u00020\u000b2\u0007\u0010·\u0001\u001a\u00020\u000b2\u0007\u0010¸\u0001\u001a\u00020\u000b2\u0007\u0010¹\u0001\u001a\u00020\u000bH\u0002R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R0\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00192\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0019@DX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u000e\u0010 \u001a\u00020!X\u0082\u000e¢\u0006\u0002\n\u0000R0\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00192\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0019@DX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u001d\"\u0004\b$\u0010\u001fR0\u0010%\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00192\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0019@DX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\u001d\"\u0004\b'\u0010\u001fR$\u0010)\u001a\u00020(2\u0006\u0010\u0018\u001a\u00020(@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R(\u0010.\u001a\u0010\u0012\u0004\u0012\u00020(\u0012\u0004\u0012\u00020\u001a\u0018\u00010/X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u00101\"\u0004\b2\u00103R$\u00105\u001a\u0002042\u0006\u0010\u0018\u001a\u000204@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u00107\"\u0004\b8\u00109R(\u0010:\u001a\u0010\u0012\u0004\u0012\u000204\u0012\u0004\u0012\u00020\u001a\u0018\u00010/X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b;\u00101\"\u0004\b<\u00103R(\u0010>\u001a\u0004\u0018\u00010=2\b\u0010\u0018\u001a\u0004\u0018\u00010=@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b?\u0010@\"\u0004\bA\u0010BR(\u0010D\u001a\u0004\u0018\u00010C2\b\u0010\u0018\u001a\u0004\u0018\u00010C@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bE\u0010F\"\u0004\bG\u0010HR\u000e\u0010I\u001a\u00020JX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010K\u001a\u00020LX\u0082\u000e¢\u0006\u0004\n\u0002\u0010MR\u0010\u0010N\u001a\u0004\u0018\u00010OX\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010P\u001a\u0018\u0012\u0006\u0012\u0004\u0018\u00010Q\u0012\u0004\u0012\u00020\u001a\u0018\u00010/j\u0004\u0018\u0001`RX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010S\u001a\u00020T8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bU\u0010VR\u0014\u0010W\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010X\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R(\u0010Y\u001a\u0010\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\u001a\u0018\u00010/X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bZ\u00101\"\u0004\b[\u00103R\u000e\u0010\\\u001a\u00020JX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010]\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010^\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010_\u001a\u00020`X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010a\u001a\u00020!X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010b\u001a\u00020!8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bb\u0010cR\u0015\u0010\u0089\u0001\u001a\u00030\u008a\u0001¢\u0006\n\n\u0000\u001a\u0006\b\u008b\u0001\u0010\u008c\u0001¨\u0006»\u0001"}, d2 = {"Landroidx/compose/ui/viewinterop/AndroidViewHolder;", "Landroid/view/ViewGroup;", "Landroidx/core/view/NestedScrollingParent3;", "Landroidx/compose/runtime/ComposeNodeLifecycleCallback;", "Landroidx/compose/ui/node/OwnerScope;", "Landroidx/core/view/OnApplyWindowInsetsListener;", "context", "Landroid/content/Context;", "parentContext", "Landroidx/compose/runtime/CompositionContext;", "compositeKeyHash", "", "dispatcher", "Landroidx/compose/ui/input/nestedscroll/NestedScrollDispatcher;", "view", "Landroid/view/View;", "owner", "Landroidx/compose/ui/node/Owner;", "<init>", "(Landroid/content/Context;Landroidx/compose/runtime/CompositionContext;ILandroidx/compose/ui/input/nestedscroll/NestedScrollDispatcher;Landroid/view/View;Landroidx/compose/ui/node/Owner;)V", "getView", "()Landroid/view/View;", "getInteropView", "Landroidx/compose/ui/viewinterop/InteropView;", "value", "Lkotlin/Function0;", "", "update", "getUpdate", "()Lkotlin/jvm/functions/Function0;", "setUpdate", "(Lkotlin/jvm/functions/Function0;)V", "hasUpdateBlock", "", "reset", "getReset", "setReset", "release", "getRelease", "setRelease", "Landroidx/compose/ui/Modifier;", "modifier", "getModifier", "()Landroidx/compose/ui/Modifier;", "setModifier", "(Landroidx/compose/ui/Modifier;)V", "onModifierChanged", "Lkotlin/Function1;", "getOnModifierChanged$ui", "()Lkotlin/jvm/functions/Function1;", "setOnModifierChanged$ui", "(Lkotlin/jvm/functions/Function1;)V", "Landroidx/compose/ui/unit/Density;", "density", "getDensity", "()Landroidx/compose/ui/unit/Density;", "setDensity", "(Landroidx/compose/ui/unit/Density;)V", "onDensityChanged", "getOnDensityChanged$ui", "setOnDensityChanged$ui", "Landroidx/lifecycle/LifecycleOwner;", "lifecycleOwner", "getLifecycleOwner", "()Landroidx/lifecycle/LifecycleOwner;", "setLifecycleOwner", "(Landroidx/lifecycle/LifecycleOwner;)V", "Landroidx/savedstate/SavedStateRegistryOwner;", "savedStateRegistryOwner", "getSavedStateRegistryOwner", "()Landroidx/savedstate/SavedStateRegistryOwner;", "setSavedStateRegistryOwner", "(Landroidx/savedstate/SavedStateRegistryOwner;)V", "position", "", "size", "Landroidx/compose/ui/unit/IntSize;", "J", "insets", "Landroidx/core/view/WindowInsetsCompat;", "bringIntoViewRequester", "Landroidx/compose/ui/geometry/Rect;", "Landroidx/compose/ui/viewinterop/BringIntoViewRequester;", "snapshotObserver", "Landroidx/compose/ui/node/OwnerSnapshotObserver;", "getSnapshotObserver", "()Landroidx/compose/ui/node/OwnerSnapshotObserver;", "runUpdate", "runInvalidate", "onRequestDisallowInterceptTouchEvent", "getOnRequestDisallowInterceptTouchEvent$ui", "setOnRequestDisallowInterceptTouchEvent$ui", "location", "lastWidthMeasureSpec", "lastHeightMeasureSpec", "nestedScrollingParentHelper", "Landroidx/core/view/NestedScrollingParentHelper;", "isDrawing", "isValidOwnerScope", "()Z", "getAccessibilityClassName", "", "onReuse", "onDeactivate", "onRelease", "onMeasure", "widthMeasureSpec", "heightMeasureSpec", "remeasure", "onLayout", "changed", "l", "t", "r", "b", "getLayoutParams", "Landroid/view/ViewGroup$LayoutParams;", "requestDisallowInterceptTouchEvent", "disallowIntercept", "onAttachedToWindow", "onDetachedFromWindow", "invalidateChildInParent", "Landroid/view/ViewParent;", "dirty", "Landroid/graphics/Rect;", "onDescendantInvalidated", "child", TypedValues.AttributesType.S_TARGET, "requestChildRectangleOnScreen", "rectangle", "immediate", "invalidateOrDefer", "onWindowVisibilityChanged", "visibility", "gatherTransparentRegion", "region", "Landroid/graphics/Region;", "layoutNode", "Landroidx/compose/ui/node/LayoutNode;", "getLayoutNode", "()Landroidx/compose/ui/node/LayoutNode;", "obtainMeasureSpec", "min", "max", "preferred", "shouldDelayChildPressedState", "onStartNestedScroll", "axes", ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY, "getNestedScrollAxes", "onNestedScrollAccepted", "onStopNestedScroll", "onNestedScroll", "dxConsumed", "dyConsumed", "dxUnconsumed", "dyUnconsumed", "consumed", "onNestedPreScroll", "dx", "dy", "onNestedFling", "velocityX", "", "velocityY", "onNestedPreFling", "isNestedScrollingEnabled", "onApplyWindowInsets", "v", "insetToLayoutPosition", "insetBounds", "Landroidx/core/view/WindowInsetsAnimationCompat$BoundsCompat;", "bounds", "insetValue", "T", "block", "Lkotlin/Function4;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "(Ljava/lang/Object;Lkotlin/jvm/functions/Function4;)Ljava/lang/Object;", "inset", "Landroidx/core/graphics/Insets;", "left", "top", "right", "bottom", "Companion", "ui"}, k = 1, mv = {2, 1, 0}, xi = 48)
public class AndroidViewHolder extends ViewGroup implements NestedScrollingParent3, ComposeNodeLifecycleCallback, OwnerScope, OnApplyWindowInsetsListener {
    private Function1<? super Rect, Unit> bringIntoViewRequester;
    private final int compositeKeyHash;
    private Density density;
    private final NestedScrollDispatcher dispatcher;
    private boolean hasUpdateBlock;
    private WindowInsetsCompat insets;
    private boolean isDrawing;
    private int lastHeightMeasureSpec;
    private int lastWidthMeasureSpec;
    private final LayoutNode layoutNode;
    private LifecycleOwner lifecycleOwner;
    private final int[] location;
    private Modifier modifier;
    private final NestedScrollingParentHelper nestedScrollingParentHelper;
    private Function1<? super Density, Unit> onDensityChanged;
    private Function1<? super Modifier, Unit> onModifierChanged;
    private Function1<? super Boolean, Unit> onRequestDisallowInterceptTouchEvent;
    private final Owner owner;
    private final int[] position;
    private Function0<Unit> release;
    private Function0<Unit> reset;
    private final Function0<Unit> runInvalidate;
    private final Function0<Unit> runUpdate;
    private SavedStateRegistryOwner savedStateRegistryOwner;
    private long size;
    private Function0<Unit> update;
    private final View view;
    public static final int $stable = 8;
    private static final Function1<AndroidViewHolder, Unit> OnCommitAffectingUpdate = AndroidViewHolder$Companion$OnCommitAffectingUpdate$1.INSTANCE;

    public AndroidViewHolder(Context context, CompositionContext compositionContext, int i, NestedScrollDispatcher nestedScrollDispatcher, View view, Owner owner) {
        super(context);
        this.compositeKeyHash = i;
        this.dispatcher = nestedScrollDispatcher;
        this.view = view;
        this.owner = owner;
        if (compositionContext != null) {
            WindowRecomposer_androidKt.setCompositionContext(this, compositionContext);
        }
        setSaveFromParentEnabled(false);
        addView(this.view);
        ViewCompat.setWindowInsetsAnimationCallback(this, new WindowInsetsAnimationCompat.Callback() { // from class: androidx.compose.ui.viewinterop.AndroidViewHolder.2
            {
                super(1);
            }

            @Override // androidx.core.view.WindowInsetsAnimationCompat.Callback
            public WindowInsetsAnimationCompat.BoundsCompat onStart(WindowInsetsAnimationCompat animation, WindowInsetsAnimationCompat.BoundsCompat bounds) {
                return AndroidViewHolder.this.insetBounds(bounds);
            }

            @Override // androidx.core.view.WindowInsetsAnimationCompat.Callback
            public WindowInsetsCompat onProgress(WindowInsetsCompat insets, List<WindowInsetsAnimationCompat> runningAnimations) {
                return AndroidViewHolder.this.insetToLayoutPosition(insets);
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(this, this);
        this.update = new Function0<Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidViewHolder$update$1
            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
            }
        };
        this.reset = new Function0<Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidViewHolder$reset$1
            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
            }
        };
        this.release = new Function0<Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidViewHolder$release$1
            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
            }
        };
        this.modifier = Modifier.INSTANCE;
        this.density = DensityKt.Density$default(1.0f, 0.0f, 2, null);
        this.position = new int[2];
        this.size = IntSize.INSTANCE.m8326getZeroYbymL2g();
        this.runUpdate = new Function0<Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidViewHolder$runUpdate$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                if (this.this$0.hasUpdateBlock && this.this$0.isAttachedToWindow() && this.this$0.getView().getParent() == this.this$0) {
                    OwnerSnapshotObserver this_$iv = this.this$0.getSnapshotObserver();
                    AndroidViewHolder androidViewHolder = this.this$0;
                    Function1 onChanged$iv = AndroidViewHolder.OnCommitAffectingUpdate;
                    this_$iv.observer.observeReads(androidViewHolder, onChanged$iv, this.this$0.getUpdate());
                }
            }
        };
        this.runInvalidate = new Function0<Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidViewHolder$runInvalidate$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                this.this$0.getLayoutNode().invalidateLayer$ui();
            }
        };
        this.location = new int[2];
        this.lastWidthMeasureSpec = Integer.MIN_VALUE;
        this.lastHeightMeasureSpec = Integer.MIN_VALUE;
        this.nestedScrollingParentHelper = new NestedScrollingParentHelper(this);
        final AndroidViewHolder androidViewHolder = this;
        byte b = 0 == true ? 1 : 0;
        final LayoutNode layoutNode = new LayoutNode(false, b, 3, null);
        layoutNode.setInteropViewFactoryHolder$ui(this);
        final Modifier modifierThen = OnGloballyPositionedModifierKt.onGloballyPositioned(DrawModifierKt.drawBehind(PointerInteropFilter_androidKt.pointerInteropFilter(SemanticsModifierKt.semantics(NestedScrollModifierKt.nestedScroll(Modifier.INSTANCE, AndroidViewHolder_androidKt.NoOpScrollConnection, androidViewHolder.dispatcher), true, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidViewHolder$layoutNode$1$coreModifier$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                invoke2(semanticsPropertyReceiver);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(SemanticsPropertyReceiver $this$semantics) {
            }
        }), androidViewHolder), new Function1<DrawScope, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidViewHolder$layoutNode$1$coreModifier$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope) {
                invoke2(drawScope);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(DrawScope $this$drawBehind) {
                AndroidViewHolder androidViewHolder2 = this.$this_run;
                LayoutNode layoutNode2 = layoutNode;
                AndroidViewHolder androidViewHolder3 = this;
                Canvas canvas = $this$drawBehind.getDrawContext().getCanvas();
                if (androidViewHolder2.getView().getVisibility() != 8) {
                    androidViewHolder2.isDrawing = true;
                    Owner owner2 = layoutNode2.getOwner();
                    AndroidComposeView androidComposeView = owner2 instanceof AndroidComposeView ? (AndroidComposeView) owner2 : null;
                    if (androidComposeView != null) {
                        androidComposeView.drawAndroidView(androidViewHolder3, AndroidCanvas_androidKt.getNativeCanvas(canvas));
                    }
                    androidViewHolder2.isDrawing = false;
                }
            }
        }), new Function1<LayoutCoordinates, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidViewHolder$layoutNode$1$coreModifier$3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(LayoutCoordinates layoutCoordinates) {
                invoke2(layoutCoordinates);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(LayoutCoordinates it) {
                WindowInsets translatedInsets;
                AndroidViewHolder_androidKt.layoutAccordingTo(this.$this_run, layoutNode);
                this.$this_run.owner.onInteropViewLayoutChange(this.$this_run);
                int previousX = this.$this_run.position[0];
                int previousY = this.$this_run.position[1];
                this.$this_run.getView().getLocationOnScreen(this.$this_run.position);
                long oldSize = this.$this_run.size;
                this.$this_run.size = it.mo6791getSizeYbymL2g();
                WindowInsetsCompat previouslyDispatchedInsets = this.$this_run.insets;
                if (previouslyDispatchedInsets != null) {
                    if ((previousX == this.$this_run.position[0] && previousY == this.$this_run.position[1] && IntSize.m8319equalsimpl0(oldSize, this.$this_run.size)) || (translatedInsets = this.$this_run.insetToLayoutPosition(previouslyDispatchedInsets).toWindowInsets()) == null) {
                        return;
                    }
                    this.$this_run.getView().dispatchApplyWindowInsets(translatedInsets);
                }
            }
        }).then(new BringIntoViewElement(new Function1<Function1<? super Rect, ? extends Unit>, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidViewHolder$layoutNode$1$coreModifier$4
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Function1<? super Rect, ? extends Unit> function1) {
                invoke2((Function1<? super Rect, Unit>) function1);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Function1<? super Rect, Unit> function1) {
                this.$this_run.bringIntoViewRequester = function1;
            }
        }));
        layoutNode.setCompositeKeyHash(androidViewHolder.compositeKeyHash);
        layoutNode.setModifier(androidViewHolder.modifier.then(modifierThen));
        androidViewHolder.onModifierChanged = new Function1<Modifier, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidViewHolder$layoutNode$1$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Modifier modifier) {
                invoke2(modifier);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Modifier it) {
                layoutNode.setModifier(it.then(modifierThen));
            }
        };
        layoutNode.setDensity(androidViewHolder.density);
        androidViewHolder.onDensityChanged = new Function1<Density, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidViewHolder$layoutNode$1$2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Density density) {
                invoke2(density);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Density it) {
                layoutNode.setDensity(it);
            }
        };
        layoutNode.setOnAttach$ui(new Function1<Owner, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidViewHolder$layoutNode$1$3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Owner owner2) {
                invoke2(owner2);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Owner owner2) {
                AndroidComposeView androidComposeView = owner2 instanceof AndroidComposeView ? (AndroidComposeView) owner2 : null;
                if (androidComposeView != null) {
                    androidComposeView.addAndroidView(this.$this_run, layoutNode);
                }
                if (this.$this_run.getView().getParent() != this.$this_run) {
                    this.$this_run.addView(this.$this_run.getView());
                }
            }
        });
        layoutNode.setOnDetach$ui(new Function1<Owner, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidViewHolder$layoutNode$1$4
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Owner owner2) {
                invoke2(owner2);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Owner owner2) {
                if (ComposeUiFlags.isViewFocusFixEnabled && this.$this_run.hasFocus()) {
                    owner2.getFocusOwner().clearFocus(true);
                }
                AndroidComposeView androidComposeView = owner2 instanceof AndroidComposeView ? (AndroidComposeView) owner2 : null;
                if (androidComposeView != null) {
                    androidComposeView.removeAndroidView(this.$this_run);
                }
                this.$this_run.removeAllViewsInLayout();
            }
        });
        layoutNode.setMeasurePolicy(new MeasurePolicy() { // from class: androidx.compose.ui.viewinterop.AndroidViewHolder$layoutNode$1$5
            @Override // androidx.compose.ui.layout.MeasurePolicy
            /* JADX INFO: renamed from: measure-3p2s80s */
            public MeasureResult mo39measure3p2s80s(MeasureScope $this$measure_u2d3p2s80s, List<? extends Measurable> list, long constraints) {
                if (this.$this_run.getChildCount() == 0) {
                    return MeasureScope.layout$default($this$measure_u2d3p2s80s, Constraints.m8105getMinWidthimpl(constraints), Constraints.m8104getMinHeightimpl(constraints), null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidViewHolder$layoutNode$1$5$measure$1
                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                            invoke2(placementScope);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(Placeable.PlacementScope $this$layout) {
                        }
                    }, 4, null);
                }
                if (Constraints.m8105getMinWidthimpl(constraints) != 0) {
                    this.$this_run.getChildAt(0).setMinimumWidth(Constraints.m8105getMinWidthimpl(constraints));
                }
                if (Constraints.m8104getMinHeightimpl(constraints) != 0) {
                    this.$this_run.getChildAt(0).setMinimumHeight(Constraints.m8104getMinHeightimpl(constraints));
                }
                AndroidViewHolder androidViewHolder2 = this.$this_run;
                AndroidViewHolder androidViewHolder3 = this.$this_run;
                int iM8105getMinWidthimpl = Constraints.m8105getMinWidthimpl(constraints);
                int iM8103getMaxWidthimpl = Constraints.m8103getMaxWidthimpl(constraints);
                ViewGroup.LayoutParams layoutParams = this.$this_run.getLayoutParams();
                Intrinsics.checkNotNull(layoutParams);
                int iObtainMeasureSpec = androidViewHolder3.obtainMeasureSpec(iM8105getMinWidthimpl, iM8103getMaxWidthimpl, layoutParams.width);
                AndroidViewHolder androidViewHolder4 = this.$this_run;
                int iM8104getMinHeightimpl = Constraints.m8104getMinHeightimpl(constraints);
                int iM8102getMaxHeightimpl = Constraints.m8102getMaxHeightimpl(constraints);
                ViewGroup.LayoutParams layoutParams2 = this.$this_run.getLayoutParams();
                Intrinsics.checkNotNull(layoutParams2);
                androidViewHolder2.measure(iObtainMeasureSpec, androidViewHolder4.obtainMeasureSpec(iM8104getMinHeightimpl, iM8102getMaxHeightimpl, layoutParams2.height));
                int measuredWidth = this.$this_run.getMeasuredWidth();
                int measuredHeight = this.$this_run.getMeasuredHeight();
                final AndroidViewHolder androidViewHolder5 = this.$this_run;
                final LayoutNode layoutNode2 = layoutNode;
                return MeasureScope.layout$default($this$measure_u2d3p2s80s, measuredWidth, measuredHeight, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidViewHolder$layoutNode$1$5$measure$2
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                        invoke2(placementScope);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(Placeable.PlacementScope $this$layout) {
                        AndroidViewHolder_androidKt.layoutAccordingTo(androidViewHolder5, layoutNode2);
                    }
                }, 4, null);
            }

            @Override // androidx.compose.ui.layout.MeasurePolicy
            public int minIntrinsicWidth(IntrinsicMeasureScope $this$minIntrinsicWidth, List<? extends IntrinsicMeasurable> list, int height) {
                return intrinsicWidth(height);
            }

            @Override // androidx.compose.ui.layout.MeasurePolicy
            public int maxIntrinsicWidth(IntrinsicMeasureScope $this$maxIntrinsicWidth, List<? extends IntrinsicMeasurable> list, int height) {
                return intrinsicWidth(height);
            }

            private final int intrinsicWidth(int height) {
                AndroidViewHolder androidViewHolder2 = this.$this_run;
                int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
                AndroidViewHolder androidViewHolder3 = this.$this_run;
                ViewGroup.LayoutParams layoutParams = this.$this_run.getLayoutParams();
                Intrinsics.checkNotNull(layoutParams);
                androidViewHolder2.measure(iMakeMeasureSpec, androidViewHolder3.obtainMeasureSpec(0, height, layoutParams.height));
                return this.$this_run.getMeasuredWidth();
            }

            @Override // androidx.compose.ui.layout.MeasurePolicy
            public int minIntrinsicHeight(IntrinsicMeasureScope $this$minIntrinsicHeight, List<? extends IntrinsicMeasurable> list, int width) {
                return intrinsicHeight(width);
            }

            @Override // androidx.compose.ui.layout.MeasurePolicy
            public int maxIntrinsicHeight(IntrinsicMeasureScope $this$maxIntrinsicHeight, List<? extends IntrinsicMeasurable> list, int width) {
                return intrinsicHeight(width);
            }

            private final int intrinsicHeight(int width) {
                AndroidViewHolder androidViewHolder2 = this.$this_run;
                AndroidViewHolder androidViewHolder3 = this.$this_run;
                ViewGroup.LayoutParams layoutParams = this.$this_run.getLayoutParams();
                Intrinsics.checkNotNull(layoutParams);
                androidViewHolder2.measure(androidViewHolder3.obtainMeasureSpec(0, width, layoutParams.width), View.MeasureSpec.makeMeasureSpec(0, 0));
                return this.$this_run.getMeasuredHeight();
            }
        });
        this.layoutNode = layoutNode;
    }

    public final View getView() {
        return this.view;
    }

    /* JADX INFO: renamed from: getInteropView, reason: from getter */
    public final View getView() {
        return this.view;
    }

    public final Function0<Unit> getUpdate() {
        return this.update;
    }

    protected final void setUpdate(Function0<Unit> function0) {
        this.update = function0;
        this.hasUpdateBlock = true;
        this.runUpdate.invoke();
    }

    public final Function0<Unit> getReset() {
        return this.reset;
    }

    protected final void setReset(Function0<Unit> function0) {
        this.reset = function0;
    }

    public final Function0<Unit> getRelease() {
        return this.release;
    }

    protected final void setRelease(Function0<Unit> function0) {
        this.release = function0;
    }

    public final Modifier getModifier() {
        return this.modifier;
    }

    public final void setModifier(Modifier value) {
        if (value != this.modifier) {
            this.modifier = value;
            Function1<? super Modifier, Unit> function1 = this.onModifierChanged;
            if (function1 != null) {
                function1.invoke(value);
            }
        }
    }

    public final Function1<Modifier, Unit> getOnModifierChanged$ui() {
        return this.onModifierChanged;
    }

    public final void setOnModifierChanged$ui(Function1<? super Modifier, Unit> function1) {
        this.onModifierChanged = function1;
    }

    public final Density getDensity() {
        return this.density;
    }

    public final void setDensity(Density value) {
        if (value != this.density) {
            this.density = value;
            Function1<? super Density, Unit> function1 = this.onDensityChanged;
            if (function1 != null) {
                function1.invoke(value);
            }
        }
    }

    public final Function1<Density, Unit> getOnDensityChanged$ui() {
        return this.onDensityChanged;
    }

    public final void setOnDensityChanged$ui(Function1<? super Density, Unit> function1) {
        this.onDensityChanged = function1;
    }

    public final LifecycleOwner getLifecycleOwner() {
        return this.lifecycleOwner;
    }

    public final void setLifecycleOwner(LifecycleOwner value) {
        if (value != this.lifecycleOwner) {
            this.lifecycleOwner = value;
            ViewTreeLifecycleOwner.set(this, value);
        }
    }

    public final SavedStateRegistryOwner getSavedStateRegistryOwner() {
        return this.savedStateRegistryOwner;
    }

    public final void setSavedStateRegistryOwner(SavedStateRegistryOwner value) {
        if (value != this.savedStateRegistryOwner) {
            this.savedStateRegistryOwner = value;
            ViewTreeSavedStateRegistryOwner.set(this, value);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final OwnerSnapshotObserver getSnapshotObserver() {
        boolean value$iv = isAttachedToWindow();
        if (!value$iv) {
            InlineClassHelperKt.throwIllegalStateException("Expected AndroidViewHolder to be attached when observing reads.");
        }
        return this.owner.getSnapshotObserver();
    }

    public final Function1<Boolean, Unit> getOnRequestDisallowInterceptTouchEvent$ui() {
        return this.onRequestDisallowInterceptTouchEvent;
    }

    public final void setOnRequestDisallowInterceptTouchEvent$ui(Function1<? super Boolean, Unit> function1) {
        this.onRequestDisallowInterceptTouchEvent = function1;
    }

    @Override // androidx.compose.ui.node.OwnerScope
    public boolean isValidOwnerScope() {
        return isAttachedToWindow();
    }

    @Override // android.view.ViewGroup, android.view.View
    public CharSequence getAccessibilityClassName() {
        return getClass().getName();
    }

    @Override // androidx.compose.runtime.ComposeNodeLifecycleCallback
    public void onReuse() {
        if (this.view.getParent() != this) {
            addView(this.view);
        } else {
            this.reset.invoke();
        }
    }

    @Override // androidx.compose.runtime.ComposeNodeLifecycleCallback
    public void onDeactivate() {
        this.reset.invoke();
        removeAllViewsInLayout();
    }

    @Override // androidx.compose.runtime.ComposeNodeLifecycleCallback
    public void onRelease() {
        this.release.invoke();
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (this.view.getParent() != this) {
            setMeasuredDimension(View.MeasureSpec.getSize(widthMeasureSpec), View.MeasureSpec.getSize(heightMeasureSpec));
            return;
        }
        if (this.view.getVisibility() == 8) {
            setMeasuredDimension(0, 0);
            return;
        }
        this.view.measure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(this.view.getMeasuredWidth(), this.view.getMeasuredHeight());
        this.lastWidthMeasureSpec = widthMeasureSpec;
        this.lastHeightMeasureSpec = heightMeasureSpec;
    }

    public final void remeasure() {
        if (this.lastWidthMeasureSpec == Integer.MIN_VALUE || this.lastHeightMeasureSpec == Integer.MIN_VALUE) {
            return;
        }
        measure(this.lastWidthMeasureSpec, this.lastHeightMeasureSpec);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        this.view.layout(0, 0, r - l, b - t);
    }

    @Override // android.view.View
    public ViewGroup.LayoutParams getLayoutParams() {
        ViewGroup.LayoutParams layoutParams = this.view.getLayoutParams();
        if (layoutParams != null) {
            return layoutParams;
        }
        return new ViewGroup.LayoutParams(-1, -1);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        Function1<? super Boolean, Unit> function1 = this.onRequestDisallowInterceptTouchEvent;
        if (function1 != null) {
            function1.invoke(Boolean.valueOf(disallowIntercept));
        }
        super.requestDisallowInterceptTouchEvent(disallowIntercept);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.runUpdate.invoke();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        getSnapshotObserver().clear$ui(this);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    @Deprecated(message = "Super method is deprecated")
    public ViewParent invalidateChildInParent(int[] location, android.graphics.Rect dirty) {
        super.invalidateChildInParent(location, dirty);
        invalidateOrDefer();
        return null;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onDescendantInvalidated(View child, View target) {
        super.onDescendantInvalidated(child, target);
        invalidateOrDefer();
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean requestChildRectangleOnScreen(View child, android.graphics.Rect rectangle, boolean immediate) {
        Function1<? super Rect, Unit> function1 = this.bringIntoViewRequester;
        if (function1 != null) {
            function1.invoke(rectangle != null ? RectHelper_androidKt.toComposeRect(rectangle) : null);
            return true;
        }
        return true;
    }

    public final void invalidateOrDefer() {
        if (this.isDrawing) {
            View view = this.view;
            final Function0<Unit> function0 = this.runInvalidate;
            view.postOnAnimation(new Runnable() { // from class: androidx.compose.ui.viewinterop.AndroidViewHolder$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    function0.invoke();
                }
            });
            return;
        }
        this.layoutNode.invalidateLayer$ui();
    }

    @Override // android.view.View
    protected void onWindowVisibilityChanged(int visibility) {
        super.onWindowVisibilityChanged(visibility);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean gatherTransparentRegion(Region region) {
        if (region == null) {
            return true;
        }
        getLocationInWindow(this.location);
        region.op(this.location[0], this.location[1], this.location[0] + getWidth(), this.location[1] + getHeight(), Region.Op.DIFFERENCE);
        return true;
    }

    public final LayoutNode getLayoutNode() {
        return this.layoutNode;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int obtainMeasureSpec(int min, int max, int preferred) {
        if (preferred >= 0 || min == max) {
            return View.MeasureSpec.makeMeasureSpec(RangesKt.coerceIn(preferred, min, max), 1073741824);
        }
        if (preferred == -2 && max != Integer.MAX_VALUE) {
            return View.MeasureSpec.makeMeasureSpec(max, Integer.MIN_VALUE);
        }
        if (preferred == -1 && max != Integer.MAX_VALUE) {
            return View.MeasureSpec.makeMeasureSpec(max, 1073741824);
        }
        return View.MeasureSpec.makeMeasureSpec(0, 0);
    }

    @Override // android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return true;
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public boolean onStartNestedScroll(View child, View target, int axes, int type) {
        return ((axes & 2) == 0 && (axes & 1) == 0) ? false : true;
    }

    @Override // android.view.ViewGroup, androidx.core.view.NestedScrollingParent
    public int getNestedScrollAxes() {
        return this.nestedScrollingParentHelper.getNestedScrollAxes();
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public void onNestedScrollAccepted(View child, View target, int axes, int type) {
        this.nestedScrollingParentHelper.onNestedScrollAccepted(child, target, axes, type);
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public void onStopNestedScroll(View target, int type) {
        this.nestedScrollingParentHelper.onStopNestedScroll(target, type);
    }

    @Override // androidx.core.view.NestedScrollingParent3
    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type, int[] consumed) {
        if (isNestedScrollingEnabled()) {
            NestedScrollDispatcher nestedScrollDispatcher = this.dispatcher;
            float x$iv = AndroidViewHolder_androidKt.toComposeOffset(dxConsumed);
            float y$iv = AndroidViewHolder_androidKt.toComposeOffset(dyConsumed);
            long v1$iv$iv = Float.floatToRawIntBits(x$iv);
            long v2$iv$iv = Float.floatToRawIntBits(y$iv);
            long jM5060constructorimpl = Offset.m5060constructorimpl((v1$iv$iv << 32) | (v2$iv$iv & 4294967295L));
            float x$iv2 = AndroidViewHolder_androidKt.toComposeOffset(dxUnconsumed);
            float y$iv2 = AndroidViewHolder_androidKt.toComposeOffset(dyUnconsumed);
            long v1$iv$iv2 = Float.floatToRawIntBits(x$iv2);
            long v2$iv$iv2 = Float.floatToRawIntBits(y$iv2);
            long jM5060constructorimpl2 = Offset.m5060constructorimpl((v1$iv$iv2 << 32) | (v2$iv$iv2 & 4294967295L));
            int $i$f$Offset = AndroidViewHolder_androidKt.toNestedScrollSource(type);
            long consumedByParent = nestedScrollDispatcher.m6501dispatchPostScrollDzOQY0M(jM5060constructorimpl, jM5060constructorimpl2, $i$f$Offset);
            int bits$iv$iv$iv = (int) (consumedByParent >> 32);
            consumed[0] = NestedScrollInteropConnectionKt.composeToViewOffset(Float.intBitsToFloat(bits$iv$iv$iv));
            int bits$iv$iv$iv2 = (int) (consumedByParent & 4294967295L);
            consumed[1] = NestedScrollInteropConnectionKt.composeToViewOffset(Float.intBitsToFloat(bits$iv$iv$iv2));
        }
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {
        if (isNestedScrollingEnabled()) {
            NestedScrollDispatcher nestedScrollDispatcher = this.dispatcher;
            float x$iv = AndroidViewHolder_androidKt.toComposeOffset(dxConsumed);
            float y$iv = AndroidViewHolder_androidKt.toComposeOffset(dyConsumed);
            long v1$iv$iv = Float.floatToRawIntBits(x$iv);
            long v2$iv$iv = Float.floatToRawIntBits(y$iv);
            long jM5060constructorimpl = Offset.m5060constructorimpl((v1$iv$iv << 32) | (v2$iv$iv & 4294967295L));
            float x$iv2 = AndroidViewHolder_androidKt.toComposeOffset(dxUnconsumed);
            float y$iv2 = AndroidViewHolder_androidKt.toComposeOffset(dyUnconsumed);
            long v1$iv$iv2 = Float.floatToRawIntBits(x$iv2);
            long v2$iv$iv2 = Float.floatToRawIntBits(y$iv2);
            long jM5060constructorimpl2 = Offset.m5060constructorimpl((v1$iv$iv2 << 32) | (4294967295L & v2$iv$iv2));
            int $i$f$Offset = AndroidViewHolder_androidKt.toNestedScrollSource(type);
            nestedScrollDispatcher.m6501dispatchPostScrollDzOQY0M(jM5060constructorimpl, jM5060constructorimpl2, $i$f$Offset);
        }
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed, int type) {
        if (isNestedScrollingEnabled()) {
            NestedScrollDispatcher nestedScrollDispatcher = this.dispatcher;
            float x$iv = AndroidViewHolder_androidKt.toComposeOffset(dx);
            float y$iv = AndroidViewHolder_androidKt.toComposeOffset(dy);
            long v1$iv$iv = Float.floatToRawIntBits(x$iv);
            long v2$iv$iv = Float.floatToRawIntBits(y$iv);
            long jM5060constructorimpl = Offset.m5060constructorimpl((v1$iv$iv << 32) | (v2$iv$iv & 4294967295L));
            int $i$f$Offset = AndroidViewHolder_androidKt.toNestedScrollSource(type);
            long consumedByParent = nestedScrollDispatcher.m6503dispatchPreScrollOzD1aCk(jM5060constructorimpl, $i$f$Offset);
            int bits$iv$iv$iv = (int) (consumedByParent >> 32);
            consumed[0] = NestedScrollInteropConnectionKt.composeToViewOffset(Float.intBitsToFloat(bits$iv$iv$iv));
            int bits$iv$iv$iv2 = (int) (consumedByParent & 4294967295L);
            consumed[1] = NestedScrollInteropConnectionKt.composeToViewOffset(Float.intBitsToFloat(bits$iv$iv$iv2));
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, androidx.core.view.NestedScrollingParent
    public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {
        if (!isNestedScrollingEnabled()) {
            return false;
        }
        long viewVelocity = VelocityKt.Velocity(AndroidViewHolder_androidKt.toComposeVelocity(velocityX), AndroidViewHolder_androidKt.toComposeVelocity(velocityY));
        BuildersKt__Builders_commonKt.launch$default(this.dispatcher.getCoroutineScope(), null, null, new AnonymousClass1(consumed, this, viewVelocity, null), 3, null);
        return false;
    }

    /* JADX INFO: renamed from: androidx.compose.ui.viewinterop.AndroidViewHolder$onNestedFling$1, reason: invalid class name */
    /* JADX INFO: compiled from: AndroidViewHolder.android.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.ui.viewinterop.AndroidViewHolder$onNestedFling$1", f = "AndroidViewHolder.android.kt", i = {}, l = {634, 636}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ boolean $consumed;
        final /* synthetic */ long $viewVelocity;
        int label;
        final /* synthetic */ AndroidViewHolder this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(boolean z, AndroidViewHolder androidViewHolder, long j, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$consumed = z;
            this.this$0 = androidViewHolder;
            this.$viewVelocity = j;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$consumed, this.this$0, this.$viewVelocity, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    boolean z = this.$consumed;
                    AndroidViewHolder androidViewHolder = this.this$0;
                    if (!z) {
                        this.label = 1;
                        Object objM6500dispatchPostFlingRZ2iAVY = androidViewHolder.dispatcher.m6500dispatchPostFlingRZ2iAVY(Velocity.INSTANCE.m8399getZero9UxMQ8M(), this.$viewVelocity, this);
                        if (objM6500dispatchPostFlingRZ2iAVY == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        $result = objM6500dispatchPostFlingRZ2iAVY;
                        ((Velocity) $result).getPackedValue();
                        return Unit.INSTANCE;
                    }
                    this.label = 2;
                    Object objM6500dispatchPostFlingRZ2iAVY2 = androidViewHolder.dispatcher.m6500dispatchPostFlingRZ2iAVY(this.$viewVelocity, Velocity.INSTANCE.m8399getZero9UxMQ8M(), this);
                    if (objM6500dispatchPostFlingRZ2iAVY2 == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    $result = objM6500dispatchPostFlingRZ2iAVY2;
                    ((Velocity) $result).getPackedValue();
                    return Unit.INSTANCE;
                case 1:
                    ResultKt.throwOnFailure($result);
                    ((Velocity) $result).getPackedValue();
                    return Unit.INSTANCE;
                case 2:
                    ResultKt.throwOnFailure($result);
                    ((Velocity) $result).getPackedValue();
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, androidx.core.view.NestedScrollingParent
    public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
        if (!isNestedScrollingEnabled()) {
            return false;
        }
        long toBeConsumed = VelocityKt.Velocity(AndroidViewHolder_androidKt.toComposeVelocity(velocityX), AndroidViewHolder_androidKt.toComposeVelocity(velocityY));
        BuildersKt__Builders_commonKt.launch$default(this.dispatcher.getCoroutineScope(), null, null, new C03261(toBeConsumed, null), 3, null);
        return false;
    }

    /* JADX INFO: renamed from: androidx.compose.ui.viewinterop.AndroidViewHolder$onNestedPreFling$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: AndroidViewHolder.android.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.ui.viewinterop.AndroidViewHolder$onNestedPreFling$1", f = "AndroidViewHolder.android.kt", i = {}, l = {645}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class C03261 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ long $toBeConsumed;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03261(long j, Continuation<? super C03261> continuation) {
            super(2, continuation);
            this.$toBeConsumed = j;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return AndroidViewHolder.this.new C03261(this.$toBeConsumed, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03261) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    this.label = 1;
                    if (AndroidViewHolder.this.dispatcher.m6502dispatchPreFlingQWom1Mo(this.$toBeConsumed, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Unit.INSTANCE;
        }
    }

    @Override // android.view.View
    public boolean isNestedScrollingEnabled() {
        return this.view.isNestedScrollingEnabled();
    }

    @Override // androidx.core.view.OnApplyWindowInsetsListener
    public WindowInsetsCompat onApplyWindowInsets(View v, WindowInsetsCompat insets) {
        this.insets = new WindowInsetsCompat(insets);
        return insetToLayoutPosition(insets);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final WindowInsetsCompat insetToLayoutPosition(WindowInsetsCompat insets) {
        WindowInsetsCompat windowInsetsCompatInset;
        if (!insets.hasInsets()) {
            return insets;
        }
        NodeCoordinator coordinates$iv = this.layoutNode.getInnerCoordinator$ui();
        if (!coordinates$iv.isAttached()) {
            return insets;
        }
        long topLeft$iv = IntOffsetKt.m8295roundk4lQ0M(LayoutCoordinatesKt.positionInRoot(coordinates$iv));
        int $this$fastCoerceAtLeast$iv$iv = IntOffset.m8278getXimpl(topLeft$iv);
        if ($this$fastCoerceAtLeast$iv$iv < 0) {
            $this$fastCoerceAtLeast$iv$iv = 0;
        }
        int $this$fastCoerceAtLeast$iv$iv2 = IntOffset.m8279getYimpl(topLeft$iv);
        if ($this$fastCoerceAtLeast$iv$iv2 < 0) {
            $this$fastCoerceAtLeast$iv$iv2 = 0;
        }
        long arg0$iv$iv = LayoutCoordinatesKt.findRootCoordinates(coordinates$iv).mo6791getSizeYbymL2g();
        int rootWidth$iv = (int) (arg0$iv$iv >> 32);
        int rootHeight$iv = (int) (arg0$iv$iv & 4294967295L);
        long arg0$iv$iv2 = coordinates$iv.mo6791getSizeYbymL2g();
        int width$iv = (int) (arg0$iv$iv2 >> 32);
        int height$iv = (int) (arg0$iv$iv2 & 4294967295L);
        float x$iv$iv = width$iv;
        float y$iv$iv = height$iv;
        long v1$iv$iv$iv = Float.floatToRawIntBits(x$iv$iv);
        int width$iv2 = Float.floatToRawIntBits(y$iv$iv);
        long v2$iv$iv$iv = width$iv2;
        long bottomRight$iv = IntOffsetKt.m8295roundk4lQ0M(coordinates$iv.mo6794localToRootMKHz9U(Offset.m5060constructorimpl((v1$iv$iv$iv << 32) | (v2$iv$iv$iv & 4294967295L))));
        int $this$fastCoerceAtLeast$iv$iv3 = rootWidth$iv - IntOffset.m8278getXimpl(bottomRight$iv);
        if ($this$fastCoerceAtLeast$iv$iv3 < 0) {
            $this$fastCoerceAtLeast$iv$iv3 = 0;
        }
        int $this$fastCoerceAtLeast$iv$iv4 = rootHeight$iv - IntOffset.m8279getYimpl(bottomRight$iv);
        if ($this$fastCoerceAtLeast$iv$iv4 < 0) {
            $this$fastCoerceAtLeast$iv$iv4 = 0;
        }
        if ($this$fastCoerceAtLeast$iv$iv == 0 && $this$fastCoerceAtLeast$iv$iv2 == 0 && $this$fastCoerceAtLeast$iv$iv3 == 0 && $this$fastCoerceAtLeast$iv$iv4 == 0) {
            windowInsetsCompatInset = insets;
        } else {
            int l = $this$fastCoerceAtLeast$iv$iv;
            int t = $this$fastCoerceAtLeast$iv$iv2;
            int r = $this$fastCoerceAtLeast$iv$iv3;
            int b = $this$fastCoerceAtLeast$iv$iv4;
            windowInsetsCompatInset = insets.inset(l, t, r, b);
        }
        return windowInsetsCompatInset;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final WindowInsetsAnimationCompat.BoundsCompat insetBounds(WindowInsetsAnimationCompat.BoundsCompat bounds) {
        WindowInsetsAnimationCompat.BoundsCompat boundsCompat;
        NodeCoordinator coordinates$iv = this.layoutNode.getInnerCoordinator$ui();
        if (!coordinates$iv.isAttached()) {
            return bounds;
        }
        long topLeft$iv = IntOffsetKt.m8295roundk4lQ0M(LayoutCoordinatesKt.positionInRoot(coordinates$iv));
        int $this$fastCoerceAtLeast$iv$iv = IntOffset.m8278getXimpl(topLeft$iv);
        if ($this$fastCoerceAtLeast$iv$iv < 0) {
            $this$fastCoerceAtLeast$iv$iv = 0;
        }
        int $this$fastCoerceAtLeast$iv$iv2 = IntOffset.m8279getYimpl(topLeft$iv);
        if ($this$fastCoerceAtLeast$iv$iv2 < 0) {
            $this$fastCoerceAtLeast$iv$iv2 = 0;
        }
        long arg0$iv$iv = LayoutCoordinatesKt.findRootCoordinates(coordinates$iv).mo6791getSizeYbymL2g();
        int rootWidth$iv = (int) (arg0$iv$iv >> 32);
        int rootHeight$iv = (int) (arg0$iv$iv & 4294967295L);
        long arg0$iv$iv2 = coordinates$iv.mo6791getSizeYbymL2g();
        int width$iv = (int) (arg0$iv$iv2 >> 32);
        int height$iv = (int) (arg0$iv$iv2 & 4294967295L);
        float x$iv$iv = width$iv;
        float y$iv$iv = height$iv;
        long v1$iv$iv$iv = Float.floatToRawIntBits(x$iv$iv);
        long v2$iv$iv$iv = Float.floatToRawIntBits(y$iv$iv);
        long bottomRight$iv = IntOffsetKt.m8295roundk4lQ0M(coordinates$iv.mo6794localToRootMKHz9U(Offset.m5060constructorimpl((v1$iv$iv$iv << 32) | (v2$iv$iv$iv & 4294967295L))));
        int $this$fastCoerceAtLeast$iv$iv3 = rootWidth$iv - IntOffset.m8278getXimpl(bottomRight$iv);
        if ($this$fastCoerceAtLeast$iv$iv3 < 0) {
            $this$fastCoerceAtLeast$iv$iv3 = 0;
        }
        int $this$fastCoerceAtLeast$iv$iv4 = rootHeight$iv - IntOffset.m8279getYimpl(bottomRight$iv);
        if ($this$fastCoerceAtLeast$iv$iv4 < 0) {
            $this$fastCoerceAtLeast$iv$iv4 = 0;
        }
        if ($this$fastCoerceAtLeast$iv$iv == 0 && $this$fastCoerceAtLeast$iv$iv2 == 0 && $this$fastCoerceAtLeast$iv$iv3 == 0 && $this$fastCoerceAtLeast$iv$iv4 == 0) {
            boundsCompat = bounds;
        } else {
            int l = $this$fastCoerceAtLeast$iv$iv;
            int t = $this$fastCoerceAtLeast$iv$iv2;
            int r = $this$fastCoerceAtLeast$iv$iv3;
            int b = $this$fastCoerceAtLeast$iv$iv4;
            boundsCompat = new WindowInsetsAnimationCompat.BoundsCompat(inset(bounds.getLowerBound(), l, t, r, b), inset(bounds.getUpperBound(), l, t, r, b));
        }
        return boundsCompat;
    }

    private final <T> T insetValue(T value, Function4<? super Integer, ? super Integer, ? super Integer, ? super Integer, ? extends T> block) {
        NodeCoordinator coordinates = this.layoutNode.getInnerCoordinator$ui();
        if (!coordinates.isAttached()) {
            return value;
        }
        long topLeft = IntOffsetKt.m8295roundk4lQ0M(LayoutCoordinatesKt.positionInRoot(coordinates));
        int $this$fastCoerceAtLeast$iv = IntOffset.m8278getXimpl(topLeft);
        if ($this$fastCoerceAtLeast$iv < 0) {
            $this$fastCoerceAtLeast$iv = 0;
        }
        int $this$fastCoerceAtLeast$iv2 = IntOffset.m8279getYimpl(topLeft);
        if ($this$fastCoerceAtLeast$iv2 < 0) {
            $this$fastCoerceAtLeast$iv2 = 0;
        }
        long arg0$iv = LayoutCoordinatesKt.findRootCoordinates(coordinates).mo6791getSizeYbymL2g();
        int rootWidth = (int) (arg0$iv >> 32);
        int rootHeight = (int) (arg0$iv & 4294967295L);
        long arg0$iv2 = coordinates.mo6791getSizeYbymL2g();
        int width = (int) (arg0$iv2 >> 32);
        int height = (int) (arg0$iv2 & 4294967295L);
        float x$iv = width;
        float y$iv = height;
        long v1$iv$iv = Float.floatToRawIntBits(x$iv);
        long v2$iv$iv = Float.floatToRawIntBits(y$iv);
        long bottomRight = IntOffsetKt.m8295roundk4lQ0M(coordinates.mo6794localToRootMKHz9U(Offset.m5060constructorimpl((v1$iv$iv << 32) | (4294967295L & v2$iv$iv))));
        int $this$fastCoerceAtLeast$iv3 = rootWidth - IntOffset.m8278getXimpl(bottomRight);
        if ($this$fastCoerceAtLeast$iv3 < 0) {
            $this$fastCoerceAtLeast$iv3 = 0;
        }
        int $this$fastCoerceAtLeast$iv4 = rootHeight - IntOffset.m8279getYimpl(bottomRight);
        if ($this$fastCoerceAtLeast$iv4 < 0) {
            $this$fastCoerceAtLeast$iv4 = 0;
        }
        if ($this$fastCoerceAtLeast$iv == 0 && $this$fastCoerceAtLeast$iv2 == 0 && $this$fastCoerceAtLeast$iv3 == 0 && $this$fastCoerceAtLeast$iv4 == 0) {
            return value;
        }
        return block.invoke(Integer.valueOf($this$fastCoerceAtLeast$iv), Integer.valueOf($this$fastCoerceAtLeast$iv2), Integer.valueOf($this$fastCoerceAtLeast$iv3), Integer.valueOf($this$fastCoerceAtLeast$iv4));
    }

    private final Insets inset(Insets $this$inset, int left, int top, int right, int bottom) {
        int $this$fastCoerceAtLeast$iv = $this$inset.left - left;
        if ($this$fastCoerceAtLeast$iv < 0) {
            $this$fastCoerceAtLeast$iv = 0;
        }
        int minimumValue$iv = $this$inset.top;
        int $this$fastCoerceAtLeast$iv2 = minimumValue$iv - top;
        if ($this$fastCoerceAtLeast$iv2 < 0) {
            $this$fastCoerceAtLeast$iv2 = 0;
        }
        int minimumValue$iv2 = $this$inset.right;
        int $this$fastCoerceAtLeast$iv3 = minimumValue$iv2 - right;
        if ($this$fastCoerceAtLeast$iv3 < 0) {
            $this$fastCoerceAtLeast$iv3 = 0;
        }
        int minimumValue$iv3 = $this$inset.bottom;
        int $this$fastCoerceAtLeast$iv4 = minimumValue$iv3 - bottom;
        if ($this$fastCoerceAtLeast$iv4 < 0) {
            $this$fastCoerceAtLeast$iv4 = 0;
        }
        return Insets.of($this$fastCoerceAtLeast$iv, $this$fastCoerceAtLeast$iv2, $this$fastCoerceAtLeast$iv3, $this$fastCoerceAtLeast$iv4);
    }
}
